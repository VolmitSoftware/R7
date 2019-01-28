package com.volmit.react.sampler;

import java.lang.Thread.State;

import com.volmit.react.React;
import com.volmit.react.api.IReactorTimer;
import com.volmit.react.api.RAveragedSampler;
import com.volmit.react.util.TICK;
import com.volmit.react.util.TM;
import com.volmit.volume.lang.format.F;
import com.volmit.volume.math.M;

public class SamplerServerTick extends RAveragedSampler implements IReactorTimer
{
	private TM tm;
	private volatile double time;
	private volatile boolean waiting;

	public SamplerServerTick()
	{
		super("tick", 6);
		tm = new TM();
		setAccuracy(0);
		setInterval(0);
	}

	@Override
	public void sample()
	{
		setValue(M.clip(time, 0D, TICK.lms * 1.25));
	}

	@Override
	public String format(double v)
	{
		return F.f(v, getAccuracy());
	}

	@Override
	public void onReactorCompute()
	{
		State s = React.serverThread.getState();

		if(s.equals(State.WAITING) || s.equals(State.TIMED_WAITING))
		{
			if(!waiting)
			{
				waiting = true;
				time = tm.mark();
			}
		}

		else if(waiting)
		{
			waiting = false;
			tm.set();
		}
	}
}
