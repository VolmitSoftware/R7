package com.volmit.react.api;

/**
 * Represents a sampler
 *
 * @author cyberpwn
 *
 */
public interface ISampler
{
	/**
	 * Get the id of this sampler
	 *
	 * @return the identifier
	 */
	public String getId();

	/**
	 * Get the shortcode of this sampler
	 *
	 * @return the sampler shortcode i.e. TPS or MEM or CHKS
	 */
	public String getShortcode();

	/**
	 * Get the display name of this sampler
	 *
	 * @return the display name
	 */
	public String getDisplayName();

	/**
	 * Get the description of this sampler
	 *
	 * @return the description
	 */
	public String getDescription();

	/**
	 * Called when it is time to sample
	 */
	public void sample();

	/**
	 * Get the raw value of this sampler
	 *
	 * @return the value
	 */
	public double getValue();

	/**
	 * Get the formatted value of this sampler
	 *
	 * @return the formatted value
	 */
	public String get();
}
