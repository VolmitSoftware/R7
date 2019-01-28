package com.volmit.react.command;

import com.volmit.react.Gate;
import com.volmit.react.React;
import com.volmit.volume.bukkit.command.VolumeSender;

public class CMonitor extends ReactCommand
{
	public CMonitor()
	{
		super("monitor", "mon");
	}

	@Override
	public boolean handle(VolumeSender s, String[] args)
	{
		// TODO handle consoles

		if(Gate.canHavePlayerData(s.player()))
		{
			React.playerSVC.getPlayer(s.player().getUniqueId()).setMonitoring(React.playerSVC.getPlayer(s.player().getUniqueId()).isMonitoring());

			if(React.playerSVC.getPlayer(s.player().getUniqueId()).isMonitoring())
			{
				Gate.notifSuccess(s.player(), "Monitoring Enabled");
			}

			else
			{
				Gate.notifWarn(s.player(), "Monitoring Disabled");
			}
		}

		else
		{
			// TODO not allowed to monitor
		}

		return true;
	}
}
