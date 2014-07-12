package com.tenjava.entries.MrTangDev.t2.listeners;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.tenjava.entries.MrTangDev.t2.TenJava;

public class GeneratorStart implements Listener {

    public TenJava plugin;

    public GeneratorStart(TenJava m) {
	plugin = m;
    }

    @EventHandler
    public void onGeneratorStart(PlayerInteractEvent event) {
	Player player = event.getPlayer();
	if ((event.getAction() == Action.RIGHT_CLICK_BLOCK) && (event.getClickedBlock().getType() == Material.STONE_BUTTON)) {
	    Location blockLoc = event.getClickedBlock().getLocation();
	    String playerUUID = player.getUniqueId().toString();
	    if ((blockLoc.getBlockX() == plugin.getConfig().getInt(playerUUID + ".x"))
		    && (blockLoc.getBlockY() == plugin.getConfig().getInt(playerUUID + ".y"))
		    && (blockLoc.getBlockZ() == plugin.getConfig().getInt(playerUUID + ".z"))) {
		if (player.getLevel() >= 30) {
		    player.setLevel(player.getLevel() - 30);
		    player.sendMessage(ChatColor.DARK_GREEN + "You started up the energy generator...");
		    player.playSound(player.getLocation(), Sound.AMBIENCE_THUNDER, 1, 10);

		    plugin.getConfig().set(playerUUID + ".isActive", true);

		    ArrayList<Player> nearbyPlayers = new ArrayList<Player>();
		    for (Entity nearbyEntity : player.getNearbyEntities(50, 50, 50)) {
			if (nearbyEntity instanceof Player) {
			    Player nearbyPlayer = (Player) nearbyEntity;
			    nearbyPlayers.add(nearbyPlayer);
			}
		    }

		    for (Player someNearbyPlayer : nearbyPlayers) {
			someNearbyPlayer.sendMessage(ChatColor.DARK_GRAY + "What was that sound...?");
			someNearbyPlayer.playSound(someNearbyPlayer.getLocation(), Sound.AMBIENCE_THUNDER, 1, 10);
		    }
		} else {
		    player.sendMessage(ChatColor.RED + "You need at least 30 levels to use this.");
		}
	    }
	}
    }

    /**
     * This will make some energy
     */
    public void generatorAction() {
	Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
	    @Override
	    public void run() {
		for (Player player : Bukkit.getOnlinePlayers()) {
		    if (plugin.getConfig().getKeys(false).contains(player.getUniqueId().toString())) {
			if (plugin.getConfig().getBoolean(player.getUniqueId().toString() + ".isActive")) {

			    int locX = plugin.getConfig().getInt(player.getUniqueId().toString() + ".x");
			    int locY = plugin.getConfig().getInt(player.getUniqueId().toString() + ".y");
			    int locZ = plugin.getConfig().getInt(player.getUniqueId().toString() + ".z");
			    String world = plugin.getConfig().getString(player.getUniqueId().toString() + ".world");

			    Random random = new Random();
			    int x = random.nextInt(10) + locX;
			    int y = locY;
			    int z = random.nextInt(10) + locZ;
			    Location rLoc = new Location(Bukkit.getWorld(world), x, y, z);
			    Bukkit.getWorld(world).strikeLightning(rLoc); //Mostly for the "attracts lightning" effect.

			    int energyLevel = plugin.getConfig().getInt(player.getUniqueId().toString() + ".energy");
			    plugin.getConfig().set(player.getUniqueId().toString() + ".energy", energyLevel + 1);
			}
		    }
		}
	    }
	}, 100l, 300l);
    }
}
