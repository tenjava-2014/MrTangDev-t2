package com.tenjava.entries.MrTangDev.t2.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.tenjava.entries.MrTangDev.t2.TenJava;

public class GeneratorButton implements CommandExecutor {

public TenJava plugin;
    
    public GeneratorButton(TenJava m) {
	plugin = m;
    }
    
    
    //commit time
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String args[]) {
	if (cmd.getName().equalsIgnoreCase("createbutton")) {
	    if (sender instanceof Player) {
		Player player = (Player) sender;
		if (player.hasPermission("energy.create")) {
		    Location buttonLoc = player.getEyeLocation();
		    player.getWorld().getBlockAt(buttonLoc).setType(Material.STONE_BUTTON);
		    player.sendMessage(ChatColor.GREEN + "Created a generator starter");
		}
	    }
	}
	return false;
    }
    
}
