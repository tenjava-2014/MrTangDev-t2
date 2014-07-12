package com.tenjava.entries.MrTangDev.t2.util;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class Message {

    public void noPerm(CommandSender sender) {
	sender.sendMessage(ChatColor.RED + "You do not have permission for that!");
    }
    
}
