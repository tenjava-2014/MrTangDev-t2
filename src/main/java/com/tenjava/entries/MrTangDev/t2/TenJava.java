package com.tenjava.entries.MrTangDev.t2;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.tenjava.entries.MrTangDev.t2.commands.CheckEnergy;
import com.tenjava.entries.MrTangDev.t2.commands.GeneratorButton;
import com.tenjava.entries.MrTangDev.t2.listeners.GeneratorStart;
import com.tenjava.entries.MrTangDev.t2.listeners.SetButton;
import com.tenjava.entries.MrTangDev.t2.util.Message;

public class TenJava extends JavaPlugin {
    
    public static TenJava plugin;
    
    public Message msg = new Message(this);
    
    public GeneratorButton buttonCmd = new GeneratorButton(this);
    public CheckEnergy checkCmd = new CheckEnergy(this);
    
    public GeneratorStart gsListener = new GeneratorStart(this);
    public SetButton set = new SetButton(this);
    
    @Override
    public void onEnable() {
	plugin = this;
	
	getCommand("createbutton").setExecutor(buttonCmd);
	getCommand("checkenergy").setExecutor(checkCmd);
	
	plugin.gsListener.generatorAction();
	
	registerEvents();
    }
    
    @Override
    public void onDisable() {
	plugin = null;
    }
    
    public void registerEvents() {
	PluginManager pm = Bukkit.getServer().getPluginManager();
	pm.registerEvents(gsListener, plugin);
	pm.registerEvents(set, plugin);
    }
}
