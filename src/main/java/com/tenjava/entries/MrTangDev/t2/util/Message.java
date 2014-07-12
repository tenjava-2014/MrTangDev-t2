package com.tenjava.entries.MrTangDev.t2.util;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.tenjava.entries.MrTangDev.t2.TenJava;

public class Message {

    public TenJava plugin;
    
    public Message(TenJava m) {
	plugin = m;
    }
    
    /**
     * Sends no-permission message
     * 
     * @param sender Message receiver
     */
    public void noPerm(CommandSender sender) {
	sender.sendMessage(ChatColor.RED + "You do not have permission for that!");
    }
    
    /**
     * Sends player-only message
     * 
     * @param sender Message receiver
     */
    public void playerOnly(CommandSender sender) {
	sender.sendMessage(ChatColor.RED + "This command is player-only!");
    }
}
