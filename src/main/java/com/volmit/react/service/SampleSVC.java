package com.volmit.react.service;

import com.volmit.react.React;
import com.volmit.react.api.IReactorTimer;
import com.volmit.react.api.ISampler;
import com.volmit.react.api.RSVC;
import com.volmit.react.sampler.SamplerCPUProcess;
import com.volmit.react.sampler.SamplerCPUTotal;
import com.volmit.react.sampler.SamplerMemoryAllocated;
import com.volmit.react.sampler.SamplerMemoryAllocatedPercent;
import com.volmit.react.sampler.SamplerMemoryFree;
import com.volmit.react.sampler.SamplerMemoryFreePercent;
import com.volmit.react.sampler.SamplerMemoryMax;
import com.volmit.react.sampler.SamplerMemoryUse;
import com.volmit.react.sampler.SamplerMemoryUsePercent;
import com.volmit.react.sampler.SamplerTickUse;
import com.volmit.react.sampler.SamplerTickUsePercent;
import com.volmit.react.sampler.SamplerTicksPerSecond;
import com.volmit.react.util.TICK;
import com.volmit.volume.lang.collections.GMap;

public class SampleSVC extends RSVC
{
	private GMap<String, ISampler> samplers;

	public SampleSVC()
	{
		super(true, 0, 0);
		samplers = new GMap<>();
	}

	public ISampler getSampler(String id)
	{
		return samplers.get(id);
	}

	private void registerAll()
	{
		registerSampler(new SamplerCPUProcess());
		registerSampler(new SamplerCPUTotal());
		registerSampler(new SamplerTicksPerSecond());
		registerSampler(new SamplerTickUse());
		registerSampler(new SamplerTickUsePercent());
		registerSampler(new SamplerMemoryUse());
		registerSampler(new SamplerMemoryUsePercent());
		registerSampler(new SamplerMemoryFree());
		registerSampler(new SamplerMemoryMax());
		registerSampler(new SamplerMemoryAllocated());
		registerSampler(new SamplerMemoryFreePercent());
		registerSampler(new SamplerMemoryAllocatedPercent());
	}

	public void registerSampler(ISampler i)
	{
		samplers.put(i.getId(), i);

		if(i instanceof IReactorTimer)
		{
			React.reactor.addReactorTimer((IReactorTimer) i);
		}

		React.instance.register(i);
	}

	@Override
	public void onStart()
	{
		registerAll();
	}

	@Override
	public void onStop()
	{

	}

	@Override
	public void onTick()
	{
		for(ISampler i : samplers.v())
		{
			if(TICK.v(i.getInterval()))
			{
				try
				{
					i.sample();
				}

				catch(Throwable e)
				{
					e.printStackTrace();
				}
			}
		}
	}
}
