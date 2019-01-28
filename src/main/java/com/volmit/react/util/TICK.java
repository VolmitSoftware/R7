package com.volmit.react.util;

import com.volmit.volume.math.M;

public class TICK
{
	public static long tick = 0;

	public static boolean v(int interval)
	{
		return tick % M.clip(interval, 1, Integer.MAX_VALUE) == 0;
	}

	public static double getTPS(double milliseconds)
	{
		return Math.min(20D, 1000D / milliseconds);
	}
}
