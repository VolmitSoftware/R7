package com.volmit.react.sampler;

import com.volmit.react.api.RSampler;
import com.volmit.react.util.Platform;
import com.volmit.volume.lang.format.F;

public class SamplerMemoryAllocated extends RSampler
{
	public SamplerMemoryAllocated()
	{
		super("mem-allocated");
		setInterval(5);
	}

	@Override
	public void sample()
	{
		setValue(Platform.MEMORY.totalMemory());
	}

	@Override
	public String format(double v)
	{
		return F.fileSize((long) v);
	}
}
