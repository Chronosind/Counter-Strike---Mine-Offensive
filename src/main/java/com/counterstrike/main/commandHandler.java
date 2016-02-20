package com.counterstrike.main;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class commandHandler implements CommandExecutor {

	Plugin plugin;
	public commandHandler(Plugin p){
		plugin = p;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		/* TODO: Clean up this mess */
		if (cmd.getName().equalsIgnoreCase("cs")) {
			if (sender instanceof Player) {
				/* Sender is player */
				Player p = (Player) sender;
				if (args.length == 0) {
					/* TODO: add help page or whatever. */
				}
				else {
					if(args.length == 1) {
						if (args[0].equalsIgnoreCase("leave")) {
							/* Sub-command is leave (i.e. /cs leave) */
							if (Utils.getGameState(p.getUniqueId()) != null) {
								p.sendMessage("You left the game.");
								arenaLeaveEvent ale = new arenaLeaveEvent(Utils.getArenaByName(Utils.getGameState(p.getUniqueId())));
								Bukkit.getServer().getPluginManager().callEvent(ale);
								Utils.setGameState(p.getUniqueId(), null);
							}
							else {
								p.sendMessage("You are not in any game!");
							}
						}
					}
					else {
						if (args[0].equalsIgnoreCase("join")) {
							/* Sub-command is join (i.e. /cs join) */
							if (args[1] != null) {
								/* Arena name is specified */
								String arenaName = args[1];
								arenaSettings as = Utils.getArenaByName(arenaName);
								if (as != null) {
									/* There is an arena with the name specified */
									if (Utils.getGameState(p.getUniqueId()) == null) {
										if (!as.hasStarted()) {
											arenaJoinEvent aje = new arenaJoinEvent(Utils.getArenaByName(arenaName), plugin);
											Bukkit.getServer().getPluginManager().callEvent(aje);
											p.sendMessage("You have joined arena " + arenaName + "!");
											p.sendMessage("You will be teleported to arena as soon as the game is started. Be patient!");
											Utils.setGameState(p.getUniqueId(), arenaName);
										}
										else p.sendMessage("That arena has already started, probably wait?");
									}
								}
								else p.sendMessage("The arena you specified doesn't exist.");
							}
						}
						else if (args[0].equalsIgnoreCase("create")) {
							/* Sub-command is create (i.e. /cs create) */
							if (args[1] != null) {
								/* Argument 1 is not null, for arena name */
								arenaSettings as = new arenaSettings();
								Utils.addArena(args[1], as);
							}
							else {
								/* Arena name is not specified */
								p.sendMessage("Please specify arena name.");
							}
						}
						else if (args[0].equalsIgnoreCase("config")) {
							/* Sub-command is config */
							if (p.hasPermission("csmo.config")) {
								/* Player has config permission */
								if (!Utils.getState(p.getUniqueId())) {
									/* Player is not in config mode */
									if (args[1] != null) {
										if (Utils.getArenaByName(args[1]) != null) {
											/* There is an actual arena by that name */
											p.sendMessage("You are now setting arena " + args[1]);
											Utils.setState(p.getUniqueId(), args[1]);
											p.sendMessage("Right click to set red spawnpoint, left click to set blue spawnpoint");
											p.sendMessage("Spawnpoint is automatically set on block coordinate with z+1");
										}
									}
									else {
										/* Arena name is not specified */
										p.sendMessage("Please specify arena name.");
									}
								}
								else {
									/* set config mode to false */
									Utils.setState(p.getUniqueId(), null);
								}
							}
							else {
								/* Player does not have config permission */
								p.sendMessage("You don't have permission to do that");
							}
						}
					}
				}
			}
			/* Sender is not player (i.e. console) */
			else sender.sendMessage("This command can only be ran by player!");
			return true;
		}
		return false;
	}
}
