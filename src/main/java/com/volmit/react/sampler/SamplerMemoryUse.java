package com.volmit.react.sampler;

import com.volmit.react.React;
import com.volmit.react.api.IReactorTimer;
import com.volmit.react.api.RSampler;
import com.volmit.react.util.C;
import com.volmit.react.util.Platform;
import com.volmit.react.util.TICK;
import com.volmit.volume.lang.format.F;

public class SamplerMemoryUse extends RSampler implements IReactorTimer
{
	private long allocateda;
	private long allocatedb;
	private long allocatedPerSecond;
	private long allocatedPerTick;
	private long lastMemoryUsed;
	private long actualUsage;
	private long actualGarbage;
	private long lastCollection;

	public SamplerMemoryUse()
	{
		super("mem-use");
		setInterval(0);
	}

	@Override
	public void sample()
	{
		setValue(actualUsage);

		if(TICK.v(20))
		{
			allocatedPerSecond = allocateda;
			allocateda = 0;
		}

		allocatedPerTick = allocatedb;
		allocatedb = 0;
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
			allocateda += memUse - actualUsage;
			allocatedb += memUse - actualUsage;
		}

		lastMemoryUsed = memUse;
	}

	public long getAllocatedPerSecond()
	{
		return allocatedPerSecond;
	}

	public long getAllocatedPerTick()
	{
		return allocatedPerTick;
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
