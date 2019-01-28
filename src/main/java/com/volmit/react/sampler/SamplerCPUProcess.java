package com.volmit.react.sampler;

import com.volmit.react.Config;
import com.volmit.react.api.RAveragedSampler;
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
		System.out.println(get());
	}

	@Override
	public String format(double v)
	{
		return F.pc(v, getAccuracy());
	}
}
