package com.volmit.react.service;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.volmit.react.Config;
import com.volmit.react.Gate;
import com.volmit.react.React;
import com.volmit.react.api.RSVC;
import com.volmit.react.api.ReactPlayer;
import com.volmit.react.util.IStorageEngine;
import com.volmit.react.util.JSONReactPlayerStorageEngine;
import com.volmit.volume.lang.collections.GList;
import com.volmit.volume.lang.collections.GMap;

public class PlayerSVC extends RSVC
{
	private IStorageEngine<UUID, ReactPlayer> engine;
	private GMap<UUID, ReactPlayer> players;
	private GList<UUID> save;
	private GList<UUID> load;

	public PlayerSVC()
	{
		super(true, 5, 100);
		players = new GMap<>();
		save = new GList<>();
	}

	public GList<ReactPlayer> getPlayers()
	{
		return players.v();
	}

	public boolean hasData(Player p)
	{
		return Gate.canHavePlayerData(p) && players.contains(p.getUniqueId());
	}

	@EventHandler
	public void on(PlayerJoinEvent e)
	{
		if(Gate.canHavePlayerData(e.getPlayer()))
		{
			requestLoad(e.getPlayer().getUniqueId());
		}
	}

	@EventHandler
	public void on(PlayerQuitEvent e)
	{
		requestSave(e.getPlayer().getUniqueId());
	}

	@Override
	public void onStart()
	{
		if(Config.REACT_STORAGE_DATABASE_ENABLED)
		{
			// TODO database react player storage engine
		}

		else
		{
			engine = new JSONReactPlayerStorageEngine(React.instance.getDataFolder("data", "player"));
		}

		for(Player i : Bukkit.getOnlinePlayers())
		{
			if(Gate.canHavePlayerData(i))
			{
				requestLoad(i.getUniqueId());
			}
		}
	}

	@Override
	public void onStop()
	{
		for(Player i : Bukkit.getOnlinePlayers())
		{
			if(Gate.canHavePlayerData(i))
			{
				saveNow(i.getUniqueId());
			}
		}
	}

	@Override
	public void onTick()
	{
		while(!save.isEmpty())
		{
			saveNow(save.pop());
		}

		while(!load.isEmpty())
		{
			loadNow(load.pop());
		}
	}

	public void saveNow(UUID p)
	{
		if(players.containsKey(p))
		{
			engine.save(p, players.get(p));
		}
	}

	public void loadNow(UUID p)
	{
		if(!engine.exists(p))
		{
			engine.save(p, new ReactPlayer(p));
		}

		players.put(p, engine.load(p));
	}

	public void requestSave(UUID p)
	{
		save.add(p);
	}

	public void requestLoad(UUID p)
	{
		load.add(p);
	}
}
