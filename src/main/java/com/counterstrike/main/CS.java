package com.counterstrike.main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;


public class CS extends JavaPlugin {

	public void onEnable(){
		PluginDescriptionFile pdfFile = getDescription();
		Logger logger = Logger.getLogger("CSL");


		Bukkit.getServer().getPluginCommand("cs").setExecutor(new commandHandler());
		Bukkit.getServer().getPluginManager().registerEvents(new clickEvent(), this);
		
		logger.info(pdfFile.getName() + "Has been enabled!(V." + pdfFile.getVersion() + ")");
	}
	public void onDisable(){
		PluginDescriptionFile pdfFile = getDescription();
		Logger logger = Logger.getLogger("CSL");
		
		logger.info(pdfFile.getName() + "Has been disabled!(V." + pdfFile.getVersion() + ")");
	}

 }
