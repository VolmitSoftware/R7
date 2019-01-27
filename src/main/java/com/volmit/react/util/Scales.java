package com.volmit.react.util;

public class Scales
{
	public static double scale(double percent, double min, double max)
	{
		return min + (percent * (max - min));
	}

	public static int scale(double percent, int min, int max)
	{
		return (int) (min + (percent * (max - min)));
	}
}
