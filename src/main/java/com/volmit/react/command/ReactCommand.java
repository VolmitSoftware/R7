package com.volmit.react.command;

import com.volmit.volume.bukkit.command.PawnCommand;

public abstract class ReactCommand extends PawnCommand
{
	public ReactCommand(String node, String... nodes)
	{
		super(node, nodes);
	}
}
