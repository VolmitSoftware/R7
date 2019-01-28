package com.volmit.react.sampler;

import com.volmit.react.Config;
import com.volmit.react.api.RSampler;
import com.volmit.react.util.C;
import com.volmit.react.util.Scales;
import com.volmit.volume.lang.format.F;

public class SamplerMemoryFreePercent extends RSampler
{
	public SamplerMemoryFreePercent()
	{
		super("mem-free-percent");
		setInterval(Scales.scale(Config.REACT_MONITORING_QUALITY, 0, 20));
	}

	@Override
	public void sample()
	{
		setValue(getSampler("mem-free").getValue() / getSampler("mem-max").getValue());
	}

	@Override
	public String format(double v)
	{
		return F.pc(v, getAccuracy());
	}

	@Override
	public String getTag()
	{
		C form = C.BOLD;

		if(getValue() < 0.25)
		{
			form = C.UNDERLINE;
		}

		return C.GOLD + "" + form + get();
	}
}
