package com.volmit.react.util;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

import com.volmit.react.api.ReactPlayer;
import com.volmit.volume.lang.io.VIO;
import com.volmit.volume.lang.json.JSONException;
import com.volmit.volume.lang.json.JSONObject;

public class JSONReactPlayerStorageEngine implements IStorageEngine<UUID, ReactPlayer>
{
	private File folder;

	/**
	 * Create a json react playerdata engine
	 *
	 * @param folder
	 *            the folder to contain the playerdata
	 */
	public JSONReactPlayerStorageEngine(File folder)
	{
		this.folder = folder;
		folder.mkdirs();
	}

	@Override
	public ReactPlayer load(UUID key)
	{
		try
		{
			return UniversalParser.fromJSON(new JSONObject(VIO.readAll(new File(folder, key.toString()))), ReactPlayer.class);
		}

		catch(IllegalArgumentException | IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException | SecurityException | JSONException | IOException e)
		{
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void save(UUID key, ReactPlayer val)
	{
		try
		{
			VIO.writeAll(new File(folder, key.toString()), UniversalParser.toJSON(val));
		}

		catch(IllegalArgumentException | IllegalAccessException | IOException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public boolean exists(UUID key)
	{
		return new File(folder, key.toString()).exists();
	}

	@Override
	public void delete(UUID key)
	{
		new File(folder, key.toString()).delete();
	}
}
