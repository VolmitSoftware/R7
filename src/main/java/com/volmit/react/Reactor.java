package com.volmit.react;

import com.volmit.react.util.Scales;
import com.volmit.volume.lang.collections.GSet;

public class Reactor extends Thread
{
	private final GSet<Runnable> timers;

	public Reactor()
	{
		timers = new GSet<>();
		setName("Reactor");
		setPriority(Scales.scale(Config.REACT_MONITORING_QUALITY, MIN_PRIORITY, NORM_PRIORITY));
	}

	public void addReactorTimer(Runnable r)
	{

	}

	@Override
	public void run()
	{
		while(!interrupted())
		{
			try
			{
				Thread.sleep(Scales.scale(Config.REACT_MONITORING_QUALITY, 1, 5));
			}

			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
}
