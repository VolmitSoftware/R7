package com.volmit.react.api;

/**
 * A tick aware service able to know when it was last ticked
 *
 * @author cyberpwn
 *
 */
public interface IChronoService extends IService
{
	public int getTicksSinceLastTick();
}
