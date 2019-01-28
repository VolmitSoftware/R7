package com.volmit.react.api;

public abstract class RSampler implements ISampler
{
	private volatile double value;
	private final String id;
	private String shortcode;
	private String displayName;
	private String description;
	private int accuracy;

	public RSampler(String id)
	{
		this.id = id;
	}

	/**
	 * Set the shortcode
	 *
	 * @param shortcode
	 *            the shortcode
	 */
	protected void setShortcode(String shortcode)
	{
		this.shortcode = shortcode;
	}

	/**
	 * Set the display name
	 *
	 * @param displayName
	 *            the display name
	 */
	protected void setDisplayName(String displayName)
	{
		this.displayName = displayName;
	}

	/**
	 * Set the description
	 *
	 * @param description
	 *            the description
	 */
	protected void setDescription(String description)
	{
		this.description = description;
	}

	@Override
	public String getId()
	{
		return id;
	}

	@Override
	public String getShortcode()
	{
		return shortcode;
	}

	@Override
	public String getDisplayName()
	{
		return displayName;
	}

	@Override
	public String getDescription()
	{
		return description;
	}

	@Override
	public abstract void sample();

	/**
	 * Format a value. Do not apply the short code, or apply any coloring. This is
	 * computed later.
	 *
	 * @param v
	 *            the value
	 * @return the formatted value.
	 */
	public abstract String format(double v);

	public void setValue(double v)
	{
		this.value = v;
	}

	@Override
	public double getValue()
	{
		return value;
	}

	@Override
	public String get()
	{
		return format(getValue());
	}

	@Override
	public void setAccuracy(int accuracy)
	{
		this.accuracy = accuracy;
	}

	@Override
	public int getAccuracy()
	{
		return accuracy;
	}
}
