package com.tenjava.entries.MrTangDev.t2.listeners;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
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

    public void startGenerator(final World world, final Location loc) {
	Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
	    @Override
	    public void run() {
		Random random = new Random();
		int x = random.nextInt(10) + loc.getBlockX();
		int y = random.nextInt(-1) + loc.getBlockY();
		int z = random.nextInt(10) + loc.getBlockZ();
		Location rLoc = new Location(world, x, y, z);
		world.strikeLightning(rLoc);
	    }
	}, 100l, 200l);
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

		    startGenerator(player.getWorld(), blockLoc);

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
}
