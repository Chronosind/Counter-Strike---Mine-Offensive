package com.counterstrike.main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;


public class CS extends JavaPlugin {

	private Logger logger;

	public void onEnable(){
		logger = getLogger();


		Bukkit.getServer().getPluginCommand("cs").setExecutor(new commandHandler(this));
		Bukkit.getServer().getPluginManager().registerEvents(new clickEvent(), this);

		logger.info("Plugin successfully enabled!");
	}
	public void onDisable(){
	}

 }
