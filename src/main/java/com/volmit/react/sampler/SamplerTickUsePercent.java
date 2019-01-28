package com.volmit.react.sampler;

import com.volmit.react.api.RAveragedSampler;
import com.volmit.volume.lang.format.F;

public class SamplerTickUsePercent extends RAveragedSampler
{
	public SamplerTickUsePercent()
	{
		super("tick-server-percent", 5);
		setInterval(20);
	}

	@Override
	public void sample()
	{
		setValue(getSampler("tick-server").getValue() / 50D);
	}

	@Override
	public String format(double v)
	{
		return F.pc(v, getAccuracy());
	}
}
