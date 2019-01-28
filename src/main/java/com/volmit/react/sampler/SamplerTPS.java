package com.volmit.react.sampler;

import com.volmit.react.api.RAveragedSampler;
import com.volmit.react.util.TICK;
import com.volmit.volume.lang.format.F;

public class SamplerTPS extends RAveragedSampler
{
	public SamplerTPS()
	{
		super("tps", 6);
		setAccuracy(0);
		setInterval(0);
	}

	@Override
	public void sample()
	{
		double tps = TICK.getTPS(TICK.lms);
		setValue(tps);
	}

	@Override
	public String format(double v)
	{
		if(v > 19.5)
		{
			v = 20;
		}

		return F.f(v, getAccuracy());
	}
}
