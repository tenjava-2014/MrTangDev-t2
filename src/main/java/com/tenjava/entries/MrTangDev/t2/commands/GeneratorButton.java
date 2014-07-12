package com.tenjava.entries.MrTangDev.t2.commands;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.tenjava.entries.MrTangDev.t2.TenJava;

public class GeneratorButton implements CommandExecutor {

    public TenJava plugin;

    public GeneratorButton(TenJava m) {
	plugin = m;
    }

    public ArrayList<String> playerList = new ArrayList<String>();
    
    //Gives you a button, allows you to create a generator
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String args[]) {
	if (cmd.getName().equalsIgnoreCase("createbutton")) {
	    if (sender instanceof Player) {
		Player player = (Player) sender;
		if (player.hasPermission("energy.create") || player.isOp()) {
		    playerList.add(player.getName());
		    
		    player.getInventory().addItem(new ItemStack(Material.STONE_BUTTON));
		    player.sendMessage(ChatColor.YELLOW + "Place a stone button on an Iron Block to create the energy generator.");
		    player.sendMessage(ChatColor.YELLOW + "You will need a lot of experience to start it up...");
		    return true;
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
