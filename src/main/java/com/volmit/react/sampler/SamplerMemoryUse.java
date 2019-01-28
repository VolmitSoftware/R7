package com.volmit.react.sampler;

import com.volmit.react.Config;
import com.volmit.react.React;
import com.volmit.react.api.IReactorTimer;
import com.volmit.react.api.RSampler;
import com.volmit.react.util.C;
import com.volmit.react.util.Platform;
import com.volmit.react.util.Scales;
import com.volmit.volume.lang.format.F;

public class SamplerMemoryUse extends RSampler implements IReactorTimer
{
	private long lastMemoryUsed;
	private long actualUsage;
	private long actualGarbage;
	private long lastCollection;

	public SamplerMemoryUse()
	{
		super("mem-use");
		setInterval(Scales.scale(Config.REACT_MONITORING_QUALITY, 0, 20));
	}

	@Override
	public void sample()
	{
		setValue(actualUsage);
	}

	@Override
	public void onReactorCompute()
	{
		long memUse = Platform.MEMORY.usedMemory();

		if(lastMemoryUsed > memUse)
		{
			actualUsage = memUse;
			lastCollection = lastMemoryUsed - memUse;
		}

		else
		{
			actualGarbage = memUse - actualUsage;
		}

		lastMemoryUsed = memUse;
	}

	public long getLastMemoryUsed()
	{
		return lastMemoryUsed;
	}

	public long getActualUsage()
	{
		return actualUsage;
	}

	public long getActualGarbage()
	{
		return actualGarbage;
	}

	public long getLastCollection()
	{
		return lastCollection;
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

		if(React.sampleSVC.getSampler("mem-use-percent").getValue() > 0.75)
		{
			form = C.UNDERLINE;
		}

		return C.GOLD + "" + form + get();
	}
}
