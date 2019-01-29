package com.volmit.react.sampler;

import com.volmit.react.Config;
import com.volmit.react.api.RAveragedSampler;
import com.volmit.react.util.C;
import com.volmit.react.util.Scales;
import com.volmit.volume.lang.format.F;

public class SamplerMemoryAllocatedPerSecond extends RAveragedSampler
{
	public SamplerMemoryAllocatedPerSecond()
	{
		super("mahs", 5);
		setInterval(Scales.scale(Config.REACT_MONITORING_QUALITY, 0, 8));
	}

	@Override
	public void sample()
	{
		setValue(((SamplerMemoryUse) getSampler("mem-use")).getAllocatedPerSecond());
	}

	@Override
	public String format(double v)
	{
		return F.fileSize((long) v) + "/s";
	}

	@Override
	public String getTag()
	{
		C form = C.BOLD;

		if(getValue() > 650000000)
		{
			form = C.UNDERLINE;
		}

		return C.GOLD + "" + form + get();
	}
}
