package com.volmit.react.sampler;

import com.volmit.react.api.RSampler;
import com.volmit.react.util.C;
import com.volmit.react.util.Platform;
import com.volmit.volume.lang.format.F;

public class SamplerMemoryMax extends RSampler
{
	public SamplerMemoryMax()
	{
		super("mem-max");
		setInterval(20000);
		sample();
	}

	@Override
	public void sample()
	{
		setValue(Platform.MEMORY.maxMemory());
	}

	@Override
	public String format(double v)
	{
		return F.fileSize((long) v);
	}

	@Override
	public String getTag()
	{
		C form = C.BOLD;
		return C.GOLD + "" + form + get();
	}
}
