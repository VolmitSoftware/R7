package com.volmit.react.inventory;

import java.util.function.BiFunction;

import org.bukkit.entity.Player;

import com.volmit.react.React;

public class AnvilText
{
	public static void getText(Player p, String def, RString s)
	{
		try
		{
			new AnvilGUI(React.instance, p, def, new BiFunction<Player, String, String>()
			{
				@Override
				public String apply(Player t, String u)
				{
					s.onComplete(u);
					t.closeInventory();

					return "";
				}
			});
		}

		catch(Exception e)
		{

		}
	}
}