package com.volmit.react.sched;

public abstract class S implements Runnable
{
	public S()
	{
		J.s(this);
	}

	public S(int delay)
	{
		J.s(this, delay);
	}
}
