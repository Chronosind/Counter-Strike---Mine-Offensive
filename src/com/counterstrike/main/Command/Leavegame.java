package com.counterstrike.main.Command;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Leavegame implements CommandExecutor{

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, 
			String[] args) {
		if (!(sender instanceof Player)) {
			   sender.sendMessage("You must to be a player to execute this command !");
			   return false;
		   }
		Player player = (Player) sender;
		
		Location l = new Location(player.getWorld(), 0, 64, 0);
		player.sendMessage(ChatColor.RED + "You leave the match !");
		player.teleport(l);
		return true;
	}

}
