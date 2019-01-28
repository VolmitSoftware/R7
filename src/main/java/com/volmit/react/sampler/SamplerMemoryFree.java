package com.volmit.react.sampler;

import com.volmit.react.React;
import com.volmit.react.api.RSampler;
import com.volmit.react.util.Platform;
import com.volmit.volume.lang.format.F;

public class SamplerMemoryFree extends RSampler
{
	public SamplerMemoryFree()
	{
		super("mem-free");
		setInterval(20);
	}

	@Override
	public void sample()
	{
		setValue(Platform.MEMORY.maxMemory() - React.sampleSVC.getSampler("mem-use").getValue());
	}

	@Override
	public String format(double v)
	{
		return F.fileSize((long) v);
	}
}
