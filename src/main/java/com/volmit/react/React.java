package com.volmit.react;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.volmit.react.api.IService;
import com.volmit.react.service.SampleSVC;
import com.volmit.react.util.TICK;
import com.volmit.react.util.nms.Catalyst;
import com.volmit.react.util.nms.NMP;
import com.volmit.react.util.nms.NMSVersion;
import com.volmit.volume.lang.collections.GSet;

public class React extends JavaPlugin
{
	public static React instance;
	public static SampleSVC sampleSVC;
	public static Thread serverThread;
	public static Reactor reactor;
	private GSet<IService> services;

	public React()
	{
		super();
	}

	@Override
	public void onEnable()
	{
		serverThread = Thread.currentThread();
		instance = this;
		initNMS();
		startReactor();
		services = new GSet<>();
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> tick(false), 0, 0);
		registerServices();
		startServices();
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

	private void registerServices()
	{
		services.add(sampleSVC = new SampleSVC());
	}

	@Override
	public void onDisable()
	{
		Bukkit.getScheduler().cancelTasks(instance);
		stopServices();
		stopReactor();
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

	public void register(Listener i)
	{
		Bukkit.getPluginManager().registerEvents(i, this);
	}

	public void unregister(Listener i)
	{
		HandlerList.unregisterAll(i);
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
	}

	private boolean isReactorRunning()
	{
		return reactor.lastTick() < 100;
	}

}
