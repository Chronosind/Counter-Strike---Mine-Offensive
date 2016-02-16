package com.counterstrike.main.Command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Joingame implements CommandExecutor{

	public boolean onCommand(CommandSender sender, Command command, 
			String label, String[] args) {
		   if (!(sender instanceof Player)) {
			   sender.sendMessage("You must to be a player to execute this command !");
			   return false;
		   }
		Player player = (Player) sender;
		player.sendMessage(ChatColor.AQUA + "You entering CS Lobby!" + player.getName() + "!");
		return true;
	}	
}