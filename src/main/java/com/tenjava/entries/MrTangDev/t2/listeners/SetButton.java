package com.tenjava.entries.MrTangDev.t2.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import com.tenjava.entries.MrTangDev.t2.TenJava;

public class SetButton implements Listener {

    public TenJava plugin;

    public SetButton(TenJava m) {
	plugin = m;
    }

    @EventHandler
    public void onPlaceButton(BlockPlaceEvent event) {
	Player player = event.getPlayer();
	if ((event.getBlock().getType() == Material.STONE_BUTTON) && (plugin.buttonCmd.playerList.contains(player.getName()))) {

	    Location buttonLoc = event.getBlock().getLocation();

	    int x = buttonLoc.getBlockX();
	    int y = buttonLoc.getBlockY();
	    int z = buttonLoc.getBlockZ();

	    plugin.getConfig().set("x", x);
	    plugin.getConfig().set("y", y);
	    plugin.getConfig().set("z", z);
	    plugin.saveConfig();
	    
	    player.sendMessage(ChatColor.GREEN + "Created a generator starter");
	    player.getWorld().playSound(buttonLoc, Sound.FIZZ, 1, 10);
	}
    }

}
