package com.tenjava.entries.MrTangDev.t2.listeners;

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
	//if ((event.getLightning().getLocation().getBlockX() == ))
    }
}
