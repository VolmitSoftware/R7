package com.volmit.react.sampler;

import com.volmit.react.api.RSampler;
import com.volmit.volume.lang.format.F;

public class SamplerMemoryAllocatedPercent extends RSampler
{
	public SamplerMemoryAllocatedPercent()
	{
		super("mem-allocated-percent");
		setInterval(20);
	}

	@Override
	public void sample()
	{
		setValue(getSampler("mem-allocated").getValue() / getSampler("mem-max").getValue());
	}

	@Override
	public String format(double v)
	{
		return F.pc(v, getAccuracy());
	}
}
