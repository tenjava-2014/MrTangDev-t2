package com.tenjava.entries.MrTangDev.t2;

import org.bukkit.plugin.java.JavaPlugin;

import com.tenjava.entries.MrTangDev.t2.listeners.GeneratorStart;

public class TenJava extends JavaPlugin {
    
    public static TenJava plugin;
    
    public GeneratorStart gsListener = new GeneratorStart(this);
    
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
