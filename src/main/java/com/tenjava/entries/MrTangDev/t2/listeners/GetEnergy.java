package com.tenjava.entries.MrTangDev.t2.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.tenjava.entries.MrTangDev.t2.TenJava;

public class GetEnergy implements Listener {

    public TenJava plugin;

    public GetEnergy(TenJava m) {
	plugin = m;
    }

    @EventHandler
    public void onEnergyCollect(PlayerInteractEvent event) {
	Player player = event.getPlayer();
	if ((event.getAction() == Action.RIGHT_CLICK_BLOCK) && (event.getClickedBlock().getType() == Material.STONE_BUTTON)) {
	    Location blockLoc = event.getClickedBlock().getLocation();
	    String playerUUID = player.getUniqueId().toString();
	    if ((blockLoc.getBlockX() == plugin.getConfig().getInt(playerUUID + ".x"))
		    && (blockLoc.getBlockY() == plugin.getConfig().getInt(playerUUID + ".y"))
		    && (blockLoc.getBlockZ() == plugin.getConfig().getInt(playerUUID + ".z"))) {
		plugin.getConfig().set(playerUUID + ".energy", 0);
		int energy = plugin.getConfig().getInt(playerUUID + ".energy");
		if (energy > 9001) {
		    player.getWorld().createExplosion(blockLoc, 5);
		    player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20*60, 5));
		    player.setSaturation(20);
		    player.setLevel(9001);
		    player.sendMessage(ChatColor.GREEN + "YOU ARE SUPER-SAIYAN!");
		} else {
		    player.setLevel(player.getLevel() + (energy/2));
		    player.setSaturation(20);
		    player.sendMessage(ChatColor.GREEN + "You have collected the energy from your generator!");
		}
	    }
	}
    }
}
