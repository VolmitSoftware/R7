package com.volmit.react.command;

import com.volmit.react.Gate;
import com.volmit.volume.bukkit.command.Command;
import com.volmit.volume.bukkit.command.VolumeSender;

public class CReact extends ReactCommand
{
	@Command
	public CMonitor cMonitor;

	public CReact()
	{
		super("react", "re");
	}

	@Override
	public boolean handle(VolumeSender s, String[] args)
	{
		Gate.msg(s, "Hello");
		Gate.notifSuccess(s.player(), "Hello");

		return true;
	}
}
