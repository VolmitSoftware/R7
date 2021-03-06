package com.volmit.react.api;

import org.bukkit.event.Listener;

/**
 * Represents a react service
 *
 * @author cyberpwn
 *
 */
public interface IService extends Listener
{
	/**
	 * Called when the service is started
	 */
	public void start();

	/**
	 * Called when the service is stopped
	 */
	public void stop();

	/**
	 * Called when the service is ticked
	 */
	public void tick();

	/**
	 * Called when the service has failed
	 */
	public void fail();

	/**
	 * Called when the service is failing
	 *
	 * @return true if it is
	 */
	public boolean isFailing();

	/**
	 * Is this service ticked async
	 *
	 * @return true if it is
	 */
	public boolean isAsync();

	/**
	 * Get the target tick rate
	 *
	 * @return the target tick rate
	 */
	public int getTargetRate();

	/**
	 * Get the slowest possible tick rate this service should ever be ticked at
	 *
	 * @return the minimum rate
	 */
	public int getMinimumRate();
}
