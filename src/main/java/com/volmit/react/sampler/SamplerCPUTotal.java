package com.volmit.react.sampler;

import com.volmit.react.Config;
import com.volmit.react.api.RAveragedSampler;
import com.volmit.react.util.C;
import com.volmit.react.util.Platform;
import com.volmit.react.util.Scales;
import com.volmit.volume.lang.format.F;

public class SamplerCPUTotal extends RAveragedSampler
{
	public SamplerCPUTotal()
	{
		super("cpu-total", 3);
		setInterval(Scales.scale(Config.REACT_MONITORING_QUALITY, 10, 50));
	}

	@Override
	public void sample()
	{
		setValue(Platform.CPU.getCPULoad());
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

		if(getValue() > 0.85)
		{
			form = C.UNDERLINE;
		}

		return C.GREEN + "" + form + get();
	}
}
