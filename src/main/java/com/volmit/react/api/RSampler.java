package com.volmit.react.api;

import com.volmit.react.React;
import com.volmit.react.util.M;

public abstract class RSampler implements ISampler
{
	private volatile double value;
	private final String id;
	private String shortcode;
	private String displayName;
	private String description;
	private int accuracy;
	private int interval;
	private boolean canHibernate;
	private long lastAccess;

	public RSampler(String id)
	{
		this.id = id;
		canHibernate = true;
		lastAccess = M.ms() - 10000000;
	}

	@Override
	public boolean isHibernating()
	{
		return canHibernate && M.ms() - lastAccess > interval * 50;
	}

	@Override
	public void setAllowHibernation(boolean b)
	{
		canHibernate = b;
	}

	@SuppressWarnings("unchecked")
	protected <T extends ISampler> T getSampler(String id)
	{
		return (T) React.sampleSVC.getSampler(id);
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
		lastAccess = M.ms();
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

	@Override
	public int getInterval()
	{
		return interval;
	}

	@Override
	public void setInterval(int interval)
	{
		this.interval = interval;
	}
}
