package com.volmit.react.api;

/**
 * A sampler which uses rolling averages
 *
 * @author cyberpwn
 *
 */
public interface IAveragedSampler
{
	/**
	 * Get the raw value, averaged
	 *
	 * @return the averaged value
	 */
	public double getAveragedValue();

	/**
	 * Get the count of values used for calculating an averaged value
	 *
	 * @return the average count
	 */
	public int getAverageSize();

	/**
	 * Set the average size
	 *
	 * @param size
	 *            the size
	 */
	public void setAverageSize(int size);
}
