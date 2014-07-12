package com.tenjava.entries.MrTangDev.t2.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.tenjava.entries.MrTangDev.t2.TenJava;

public class CheckEnergy implements CommandExecutor {

    public TenJava plugin;

    public CheckEnergy(TenJava m) {
	plugin = m;
    }

    //Command for checking energy your generator has
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String args[]) {
	if (cmd.getName().equalsIgnoreCase("checkenergy")) {
	    if (sender instanceof Player) {
		Player player = (Player) sender;
		if (player.hasPermission("energy.check") || player.isOp()) {
		    String playerUUID = player.getUniqueId().toString();
		    if (plugin.getConfig().getKeys(false).contains(playerUUID)) {
			player.sendMessage(ChatColor.GREEN + "Your generators energy level is at: " 
				+ plugin.getConfig().getInt(playerUUID + ".energy"));
			return true;
		    } else {
			sender.sendMessage(ChatColor.RED + "You need a generator to check this.");
			return false;
		    }
		} else {
		    plugin.msg.noPerm(sender);
		    return false;
		}
	    } else {
		plugin.msg.playerOnly(sender);
		return false;
	    }
	}
	return false;
    }

}
