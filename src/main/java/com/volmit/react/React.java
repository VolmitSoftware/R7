package com.volmit.react;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class React extends JavaPlugin
{
	public React()
	{
		super();
	}

	@Override
	public void onLoad()
	{

	}

	@Override
	public void onEnable()
	{
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> tick(), 0, 0);
	}

	@Override
	public void onDisable()
	{

	}

	private void tick()
	{
		try
		{

		}

		catch(Throwable e)
		{
			e.printStackTrace();
		}
	}
}
