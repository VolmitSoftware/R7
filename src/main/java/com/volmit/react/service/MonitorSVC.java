package com.volmit.react.service;

import com.volmit.react.React;
import com.volmit.react.api.RSVC;
import com.volmit.react.api.ReactPlayer;
import com.volmit.react.util.C;
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

	}

	private void tickMonitor(ReactPlayer i)
	{
		NMP.MESSAGE.action(i.player(), getMonitor(i));
	}

	private String getMonitor(ReactPlayer i)
	{
		String t = "";
		boolean m = i.player().isSneaking();
		SampleSVC s = React.sampleSVC;
		//@builder
		t += " " + (!m ? s.getSampler("tps").getTag() :
			s.getSampler("tick-server").getTag() + " " + C.RESET + s.getSampler("tick-server-percent").getTag());
		t += " " + (!m ? s.getSampler("mem-use").getTag() + "" + C.RESET + C.DARK_GRAY + " (" + s.getSampler("mem-use-percent").getTag() + C.RESET + "" + C.DARK_GRAY + ")"  :
			s.getSampler("maht").getTag());
		//@done

		return t.substring(1);
	}

	@Override
	public void onStop()
	{

	}

	@Override
	public void onTick()
	{
		for(ReactPlayer i : React.playerSVC.getPlayers())
		{
			if(i.isMonitoring())
			{
				tickMonitor(i);
			}
		}
	}
}
