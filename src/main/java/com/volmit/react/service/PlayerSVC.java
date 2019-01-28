package com.volmit.react.service;

import java.util.UUID;

import com.volmit.react.Config;
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
	}

	@Override
	public void onStop()
	{

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
