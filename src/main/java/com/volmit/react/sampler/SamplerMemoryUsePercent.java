package com.volmit.react.sampler;

import com.volmit.react.Config;
import com.volmit.react.api.RSampler;
import com.volmit.react.util.Scales;
import com.volmit.volume.lang.format.F;

public class SamplerMemoryUsePercent extends RSampler
{
	public SamplerMemoryUsePercent()
	{
		super("mem-use-percent");
		setInterval(Scales.scale(Config.REACT_MONITORING_QUALITY, 0, 20));
	}

	@Override
	public void sample()
	{
		setValue(getSampler("mem-use").getValue() / getSampler("mem-max").getValue());
	}

	@Override
	public String format(double v)
	{
		return F.pc(v, getAccuracy());
	}
}
