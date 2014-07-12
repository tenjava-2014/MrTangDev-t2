package com.tenjava.entries.MrTangDev.t2.listeners;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.tenjava.entries.MrTangDev.t2.TenJava;

public class GeneratorStart implements Listener {

    public TenJava plugin;

    public GeneratorStart(TenJava m) {
	plugin = m;
    }

    @EventHandler
    public void onGeneratorStart(PlayerInteractEvent event) {
	Player player = event.getPlayer();
	if (event.getClickedBlock().getType() == Material.STONE_BUTTON) {
	    Location blockLoc = event.getClickedBlock().getLocation();
	    if ((blockLoc.getBlockX() == plugin.getConfig().getInt("x"))
		    && (blockLoc.getBlockY() == plugin.getConfig().getInt("y"))
		    && (blockLoc.getBlockZ() == plugin.getConfig().getInt("z"))) {

		player.sendMessage(ChatColor.DARK_GREEN + "You started the energy generator...");
		player.playSound(player.getLocation(), Sound.AMBIENCE_THUNDER, 1, 10);
		player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 20, 10));

		ArrayList<Player> nearbyPlayers = new ArrayList<Player>();
		for (Entity nearbyEntity : player.getNearbyEntities(50, 50, 50)) {
		    if (nearbyEntity instanceof Player) {
			Player nearbyPlayer = (Player) nearbyEntity;
			nearbyPlayers.add(nearbyPlayer);
		    }
		}

		for (Player someNearbyPlayer : nearbyPlayers) {
		    someNearbyPlayer.sendMessage(ChatColor.DARK_GRAY + "What is this sound...");
		    someNearbyPlayer.playSound(someNearbyPlayer.getLocation(), Sound.AMBIENCE_THUNDER, 1, 10);
		    someNearbyPlayer.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 20, 10));
		}
	    }
	}
    }
}
