package com.tenjava.entries.MrTangDev.t2.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import com.tenjava.entries.MrTangDev.t2.TenJava;

public class GeneratorStart implements Listener {

    public TenJava plugin;
    
    public GeneratorStart(TenJava m) {
	plugin = m;
    }
    
    
    
    @EventHandler
    public void onLightningStart(PlayerInteractEvent event) {
	Player player = event.getPlayer();
	if (event.getClickedBlock().getType() == Material.STONE_BUTTON)
    }
}
