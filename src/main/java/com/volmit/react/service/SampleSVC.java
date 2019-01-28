package com.volmit.react.service;

import com.volmit.react.React;
import com.volmit.react.api.IReactorTimer;
import com.volmit.react.api.ISampler;
import com.volmit.react.api.RSVC;
import com.volmit.react.sampler.SamplerServerTick;
import com.volmit.react.sampler.SamplerTPS;
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
		registerSampler(new SamplerTPS());
		registerSampler(new SamplerServerTick());
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
