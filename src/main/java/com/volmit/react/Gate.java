package com.volmit.react;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.volmit.react.util.nms.FrameType;
import com.volmit.react.util.nms.NMP;
import com.volmit.react.util.nms.NMSVersion;

public class Gate
{
	public static final String TAG = ChatColor.BLUE + "[" + ChatColor.DARK_GRAY + ChatColor.BOLD + "React" + ChatColor.RESET + ChatColor.BLUE + "]" + ChatColor.GRAY + ": ";
	public static final String TAG_NP = ChatColor.BLUE + "[" + ChatColor.DARK_GRAY + ChatColor.BOLD + "React" + ChatColor.RESET + ChatColor.BLUE + "]" + ChatColor.GRAY;

	public static void msg(CommandSender s, String message)
	{
		s.sendMessage(TAG + message);
	}

	public static void notifSuccess(Player p, String message)
	{
		notif(p, message, new ItemStack(Material.SLIME_BALL));
	}

	public static void notifWarn(Player p, String message)
	{
		notif(p, message, new ItemStack(Material.MAGMA_CREAM));
	}

	public static void notifFail(Player p, String message)
	{
		notif(p, message, new ItemStack(Material.BARRIER));
	}

	public static void notif(Player p, String message, ItemStack is)
	{
		if(NMSVersion.R1_9_2.betweenInclusive(NMSVersion.R1_13).contains(NMSVersion.current()))
		{
			NMP.MESSAGE.advance(p, is, TAG + "\n" + ChatColor.GRAY + message, FrameType.GOAL);
		}

		else
		{
			msg(p, message);
		}
	}
}
