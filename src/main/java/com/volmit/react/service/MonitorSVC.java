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
		String t = "";
		boolean m = i.player().isSneaking();
		SampleSVC s = React.sampleSVC;
		//@builder
		t += " " + (!m ? s.getSampler("tps") :
			s.getSampler("tick-server") + " " + C.RESET + s.getSampler("tick-server-percent"));
		t += " " + (!m ? s.getSampler("mem-use") + "" + C.RESET + C.DARK_GRAY + " (" + s.getSampler("mem-use-percent") + C.RESET + "" + C.DARK_GRAY + ")"  :
			s.getSampler("tick-server") + " " + C.RESET + s.getSampler("tick-server-percent"));
		t += " " + (!m ? s.getSampler("tps") : s.getSampler("tick-server") + " " + C.RESET + s.getSampler("tick-server-percent"));
		t += " " + (!m ? s.getSampler("tps") : s.getSampler("tick-server") + " " + C.RESET + s.getSampler("tick-server-percent"));
		t += " " + (!m ? s.getSampler("tps") : s.getSampler("tick-server") + " " + C.RESET + s.getSampler("tick-server-percent"));
		t += " " + (!m ? s.getSampler("tps") : s.getSampler("tick-server") + " " + C.RESET + s.getSampler("tick-server-percent"));
		t += " " + (!m ? s.getSampler("tps") : s.getSampler("tick-server") + " " + C.RESET + s.getSampler("tick-server-percent"));
		//@done
		return t.substring(1).trim();
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
