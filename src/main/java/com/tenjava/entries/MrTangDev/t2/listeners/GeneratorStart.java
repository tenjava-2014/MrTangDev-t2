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
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

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
		int x = random.nextInt(loc.getBlockX()) + 3;
		int y = random.nextInt(loc.getBlockY()) + 3;
		int z = random.nextInt(loc.getBlockZ()) + 3;
		Location rLoc = new Location(world, x, y, z);
		world.strikeLightning(rLoc);
	    }
	}, 20l, 100l);
    }

    @EventHandler
    public void onGeneratorStart(PlayerInteractEvent event) {
	Player player = event.getPlayer();
	if ((event.getClickedBlock().getType() == Material.STONE_BUTTON) && (event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
	    Location blockLoc = event.getClickedBlock().getLocation();
	    if ((blockLoc.getBlockX() == plugin.getConfig().getInt("x"))
		    && (blockLoc.getBlockY() == plugin.getConfig().getInt("y"))
		    && (blockLoc.getBlockZ() == plugin.getConfig().getInt("z"))) {

		player.sendMessage(ChatColor.DARK_GREEN + "You started the energy generator...");
		player.playSound(player.getLocation(), Sound.AMBIENCE_THUNDER, 1, 10);
		player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 60, 10));

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
