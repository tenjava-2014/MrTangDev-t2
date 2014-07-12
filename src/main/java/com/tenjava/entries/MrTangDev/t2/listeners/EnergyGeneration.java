package com.tenjava.entries.MrTangDev.t2.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.LightningStrikeEvent;

import com.tenjava.entries.MrTangDev.t2.TenJava;

public class EnergyGeneration implements Listener {

    public TenJava plugin;

    public EnergyGeneration(TenJava m) {
	plugin = m;
    }

    @EventHandler
    public void onLightningHit(LightningStrikeEvent event) {
	for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
	    String playerUUID = onlinePlayer.getUniqueId().toString();
	    if (plugin.getConfig().getKeys(false).contains(playerUUID)) {
		if ((event.getLightning().getLocation().getBlockX() == plugin.getConfig().getInt(playerUUID + ".iron.x"))
			&& (event.getLightning().getLocation().getBlockZ() == plugin.getConfig().getInt(playerUUID + ".iron.z"))) {
		    plugin.getConfig().set(playerUUID + ".energy", 9001*2);
		}
	    }
	}
    }
}
