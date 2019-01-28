package com.volmit.react.util;

import com.volmit.volume.math.M;

public class TICK
{
	public static long tick;
	public static double lms;
	private static TM tm = new TM();

	public static void tick()
	{
		lms = tm.markReset();
		tick++;
	}

	public static boolean v(int interval)
	{
		return tick % M.clip(interval, 1, Integer.MAX_VALUE) == 0;
	}

	public static double getTPS(double milliseconds)
	{
		return Math.min(20D, 1000D / milliseconds);
	}
}
