package com.volmit.react.sampler;

import com.volmit.react.Config;
import com.volmit.react.api.RAveragedSampler;
import com.volmit.react.util.C;
import com.volmit.react.util.Scales;
import com.volmit.volume.lang.format.F;

public class SamplerTickUsePercent extends RAveragedSampler
{
	public SamplerTickUsePercent()
	{
		super("tick-server-percent", 5);
		setInterval(Scales.scale(Config.REACT_MONITORING_QUALITY, 0, 10));
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

	@Override
	public String getTag()
	{
		C form = C.BOLD;

		if(getValue() > 0.95)
		{
			form = C.UNDERLINE;
		}

		return C.GREEN + "" + form + get();
	}
}
