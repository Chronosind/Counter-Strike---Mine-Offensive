package com.counterstrike.main;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;


public class CS extends JavaPlugin {

	public void onEnable(){
		PluginDescriptionFile pdfFile = getDescription();
		Logger logger = Logger.getLogger("CSL");

		Utils u = new Utils();

		Bukkit.getServer().getPluginCommand("cs").setExecutor(new commandHandler(u));
		
		logger.info(pdfFile.getName() + "Has been enabled!(V." + pdfFile.getVersion() + ")");
	}
	public void onDisable(){
		PluginDescriptionFile pdfFile = getDescription();
		Logger logger = Logger.getLogger("CSL");
		
		logger.info(pdfFile.getName() + "Has been disabled!(V." + pdfFile.getVersion() + ")");
	}

 }
