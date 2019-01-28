package com.volmit.react.api;

import java.util.UUID;

public class ReactPlayer
{
	private UUID id;
	private boolean monitoring;

	public ReactPlayer()
	{
		id = UUID.randomUUID();
	}

	public ReactPlayer(UUID id)
	{
		this.id = id;
	}

	public UUID getId()
	{
		return id;
	}

	public void setId(UUID id)
	{
		this.id = id;
	}

	public boolean isMonitoring()
	{
		return monitoring;
	}

	public void setMonitoring(boolean monitoring)
	{
		this.monitoring = monitoring;
	}
}
