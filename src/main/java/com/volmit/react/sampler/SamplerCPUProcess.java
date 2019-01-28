package com.volmit.react.sampler;

import com.volmit.react.Config;
import com.volmit.react.api.RAveragedSampler;
import com.volmit.react.util.C;
import com.volmit.react.util.Platform;
import com.volmit.react.util.Scales;
import com.volmit.volume.lang.format.F;

public class SamplerCPUProcess extends RAveragedSampler
{
	public SamplerCPUProcess()
	{
		super("cpu-process", 3);
		setInterval(Scales.scale(Config.REACT_MONITORING_QUALITY, 2, 25));
	}

	@Override
	public void sample()
	{
		setValue(Platform.CPU.getLiveProcessCPULoad());
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

		if(getValue() > 0.65)
		{
			form = C.UNDERLINE;
		}

		return C.GREEN + "" + form + get();
	}
}
