package com.volmit.react.api;

public abstract class RSVC implements IChronoService
{
	private final boolean async;
	private final int target;
	private final int minimum;
	private int ticksSinceLastTick;
	private boolean failing;

	public RSVC(boolean async, int target, int minimum)
	{
		this.async = async;
		this.target = target;
		this.minimum = minimum;
	}

	public abstract void onStart();

	public abstract void onStop();

	public abstract void onTick();

	public void fail()
	{
		failing = true;
	}

	public void start()
	{
		onStart();
	}

	public void stop()
	{
		onStop();
	}

	public void tick()
	{
		onTick();
	}

	public boolean isAsync()
	{
		return async;
	}

	public int getTargetRate()
	{
		return target;
	}

	public int getMinimumRate()
	{
		return minimum;
	}

	public int getTicksSinceLastTick()
	{
		return ticksSinceLastTick;
	}

	public boolean isFailing()
	{
		return failing;
	}
}
