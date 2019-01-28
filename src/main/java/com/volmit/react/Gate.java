package com.volmit.react;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.volmit.react.util.C;
import com.volmit.react.util.nms.FrameType;
import com.volmit.react.util.nms.NMP;
import com.volmit.react.util.nms.NMSVersion;
import com.volmit.volume.bukkit.command.VolumeSender;

public class Gate
{
	public static final String TAG = C.BLUE + "[" + C.DARK_GRAY + C.BOLD + "React" + C.RESET + C.BLUE + "]" + C.GRAY + ": ";
	public static final String TAG_NP = C.BLUE + "[" + C.DARK_GRAY + C.BOLD + "React" + C.RESET + C.BLUE + "]" + C.GRAY;

	public static boolean canHavePlayerData(Player p)
	{
		return p.hasPermission("react.use") || p.hasPermission("react.monitor");
	}

	public static void msg(CommandSender s, String message)
	{
		s.sendMessage((s instanceof VolumeSender ? "" : TAG) + message);
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
			NMP.MESSAGE.advance(p, is, TAG + "\n" + C.GRAY + message, FrameType.GOAL);
		}

		else
		{
			msg(p, message);
		}
	}
}
