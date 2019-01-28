package com.volmit.react.sampler;

import com.volmit.react.api.RAveragedSampler;
import com.volmit.react.util.C;
import com.volmit.volume.lang.format.F;

public class SamplerMemoryAllocatedPerTick extends RAveragedSampler
{
	public SamplerMemoryAllocatedPerTick()
	{
		super("maht", 7);
		setInterval(0);
	}

	@Override
	public void sample()
	{
		setValue(((SamplerMemoryUse) getSampler("mem-use")).getAllocatedPerTick());
	}

	@Override
	public String format(double v)
	{
		return F.fileSize((long) v) + "/tick";
	}

	@Override
	public String getTag()
	{
		C form = C.BOLD;

		if(getValue() > 45)
		{
			form = C.UNDERLINE;
		}

		return C.GOLD + "" + form + get();
	}
}
