package com.volmit.react.api;

import com.volmit.react.util.RollingAverage;

public abstract class RAveragedSampler extends RSampler implements IAveragedSampler
{
	private RollingAverage a;

	public RAveragedSampler(String id, int radius)
	{
		super(id);
	}

	@Override
	public double getAveragedValue()
	{
		return a.get();
	}

	@Override
	public int getAverageSize()
	{
		return a.size();
	}

	@Override
	public void setAverageSize(int size)
	{
		a = new RollingAverage(size);
	}

	@Override
	public void setValue(double v)
	{
		this.a.put(v);
		super.setValue(getAveragedValue());
	}

	@Override
	public abstract void sample();

	@Override
	public abstract String format(double v);
}
