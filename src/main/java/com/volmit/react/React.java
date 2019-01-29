package com.volmit.react;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

import com.volmit.react.api.IService;
import com.volmit.react.command.CReact;
import com.volmit.react.service.MonitorSVC;
import com.volmit.react.service.PlayerSVC;
import com.volmit.react.service.SampleSVC;
import com.volmit.react.util.TICK;
import com.volmit.react.util.nms.Catalyst;
import com.volmit.react.util.nms.NMP;
import com.volmit.react.util.nms.NMSVersion;
import com.volmit.volume.bukkit.VolumePlugin;
import com.volmit.volume.bukkit.command.Command;
import com.volmit.volume.bukkit.command.CommandTag;
import com.volmit.volume.bukkit.pawn.Start;
import com.volmit.volume.bukkit.pawn.Stop;
import com.volmit.volume.lang.collections.GSet;

@CommandTag("&9[&8&lReact&r&9]&7: ")
public class React extends VolumePlugin
{
	public static React instance;
	public static SampleSVC sampleSVC;
	public static MonitorSVC monitorSVC;
	public static PlayerSVC playerSVC;
	public static Thread serverThread;
	public static Reactor reactor;
	private GSet<IService> services;

	@Command
	public CReact cReact;

	@Start
	public void onStart()
	{
		serverThread = Thread.currentThread();
		instance = this;
		initNMS();
		startReactor();
		services = new GSet<>();
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> tick(false), 0, 0);
		registerServices();
		startServices();
		instance = (React) Bukkit.getPluginManager().getPlugin("React");
	}

	@Stop
	public void onStop()
	{
		Bukkit.getScheduler().cancelTasks(instance);
		stopServices();
		stopReactor();
		stopNMS();
	}

	private void registerServices()
	{
		services.add(sampleSVC = new SampleSVC());
		services.add(playerSVC = new PlayerSVC());
		services.add(monitorSVC = new MonitorSVC());
	}

	private void stopNMS()
	{
		Catalyst.host.stop();
	}

	private void initNMS()
	{
		Catalyst.host.start();
		NMP.host = Catalyst.host;
		NMSVersion v = NMSVersion.current();

		if(v != null)
		{
			getLogger().info("Selected " + NMSVersion.current().name());
		}

		else
		{
			getLogger().info("Could not find a suitable binder for this server version!");
		}
	}

	private void stopReactor()
	{
		try
		{
			reactor.interrupt();
		}

		catch(Throwable e)
		{

		}
	}

	private void startServices()
	{
		for(IService i : services)
		{
			i.start();
			register(i);
		}
	}

	private void stopServices()
	{
		for(IService i : services)
		{
			i.stop();
		}
	}

	private void tick(boolean async)
	{
		if(!async)
		{
			instance = this;
			TICK.tick();
			verifyReactor();
		}

		for(IService i : services)
		{
			if(i.isAsync() == async && TICK.v(i.getTargetRate()))
			{
				try
				{
					i.tick();
				}

				catch(Throwable e)
				{
					e.printStackTrace();
				}
			}
		}
	}

	private void verifyReactor()
	{
		if(!isReactorRunning())
		{
			restartReactor();
		}
	}

	private void restartReactor()
	{
		stopReactor();
		startReactor();
	}

	private void startReactor()
	{
		reactor = new Reactor();
		reactor.start();
		reactor.registerTicked(() -> tick(true));

		if(sampleSVC != null)
		{
			sampleSVC.rereg();
		}
	}

	private boolean isReactorRunning()
	{
		return reactor.lastTick() < 100;
	}

	public void register(Listener i)
	{
		Bukkit.getPluginManager().registerEvents(i, this);
	}

	public void unregister(Listener i)
	{
		HandlerList.unregisterAll(i);
	}
}
