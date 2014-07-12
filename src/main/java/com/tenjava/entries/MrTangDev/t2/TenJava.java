package com.tenjava.entries.MrTangDev.t2;

import org.bukkit.plugin.java.JavaPlugin;

public class TenJava extends JavaPlugin {
    
    public static TenJava plugin;
    
    @Override
    public void onEnable() {
	plugin = this;
	System.out.println("doge says woof.");
    }
    
    @Override
    public void onDisable() {
	plugin = null;
    }
}
