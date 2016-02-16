package com.counterstrike.main;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import com.counterstrike.main.Command.commandHandler;


public class CS extends JavaPlugin {

	public void onEnable(){
		PluginDescriptionFile pdfFile = getDescription();
		Logger logger = Logger.getLogger("CSL");

		Bukkit.getServer().getPluginCommand("cs").setExecutor(new commandHandler());
		
		logger.info(pdfFile.getName() + "Has been enabled!(V." + pdfFile.getVersion() + ")");
	}
	public void onDisable(){
		PluginDescriptionFile pdfFile = getDescription();
		Logger logger = Logger.getLogger("CSL");
		
		logger.info(pdfFile.getName() + "Has been disabled!(V." + pdfFile.getVersion() + ")");
	}

 }
