package com.counterstrike.main;

import java.util.logging.Logger;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import com.counterstrike.main.Command.Joingame;
import com.counterstrike.main.Command.Leavegame;


public class CS extends JavaPlugin {
	
	public void onEnable(){
		PluginDescriptionFile pdfFile = getDescription();
		Logger logger = Logger.getLogger("CSL");
		
		getCommand("join").setExecutor(new Joingame());
		getCommand("leave").setExecutor(new Leavegame());
		
		logger.info(pdfFile.getName() + "Has been enabled!(V." + pdfFile.getVersion() + ")");
	}
	public void onDisable(){
		PluginDescriptionFile pdfFile = getDescription();
		Logger logger = Logger.getLogger("CSL");
		
		logger.info(pdfFile.getName() + "Has been disabled!(V." + pdfFile.getVersion() + ")");
	}

 }
