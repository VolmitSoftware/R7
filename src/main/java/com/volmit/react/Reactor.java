package com.volmit.react;

import com.volmit.react.api.IReactorTimer;
import com.volmit.react.util.Scales;
import com.volmit.react.util.TM;
import com.volmit.volume.lang.collections.GSet;
import com.volmit.volume.lang.queue.ChronoLatch;

public class Reactor extends Thread
{
	private final GSet<IReactorTimer> timers;
	private final GSet<Runnable> ticks;
	private TM timer;
	private TM timerActive;
	private ChronoLatch latch;
	private volatile double time;

	public Reactor()
	{
		ticks = new GSet<>();
		latch = new ChronoLatch(50);
		timer = new TM();
		timerActive = new TM();
		timers = new GSet<>();
		setName("Reactor");
		setPriority(Scales.scale(Config.REACT_MONITORING_QUALITY, MIN_PRIORITY, NORM_PRIORITY));
	}

	public void addReactorTimer(IReactorTimer r)
	{
		timers.add(r);
	}

	@Override
	public void run()
	{
		while(!interrupted())
		{
			try
			{
				time = timer.markReset();
				timerActive.set();
				Thread.sleep(1);

				for(IReactorTimer i : timers)
				{
					try
					{
						i.onReactorCompute();
					}

					catch(Throwable e)
					{
						e.printStackTrace();
					}
				}

				if(latch.flip())
				{
					for(Runnable i : ticks)
					{
						try
						{
							i.run();
						}

						catch(Throwable e)
						{
							e.printStackTrace();
						}
					}
				}
			}

			catch(InterruptedException e)
			{
				break;
			}

			catch(Throwable e)
			{
				e.printStackTrace();
			}
		}
	}

	public double lastTick()
	{
		return timerActive.mark();
	}

	public double getInterval()
	{
		return time;
	}

	public void registerTicked(Runnable r)
	{
		ticks.add(r);
	}
}
