package com.volmit.react.service;

import com.volmit.react.React;
import com.volmit.react.api.RSVC;
import com.volmit.react.api.ReactPlayer;
import com.volmit.react.util.nms.NMP;

public class MonitorSVC extends RSVC
{
	public MonitorSVC()
	{
		super(true, 0, 15);
	}

	@Override
	public void onStart()
	{
		for(ReactPlayer i : React.playerSVC.getPlayers())
		{
			if(i.isMonitoring())
			{
				tickMonitor(i);
			}
		}
	}

	private void tickMonitor(ReactPlayer i)
	{
		NMP.MESSAGE.action(i.player(), getMonitor(i));
	}

	private String getMonitor(ReactPlayer i)
	{
		return React.sampleSVC.getSampler("tps").get();
	}

	@Override
	public void onStop()
	{

	}

	@Override
	public void onTick()
	{

	}
}
