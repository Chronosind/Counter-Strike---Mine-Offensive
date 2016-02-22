package com.counterstrike.main;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.Set;

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
				if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
					p.sendMessage("COUNTER-STRIKE: MINE-OFFENSIVE V. " + plugin.getDescription().getVersion());
					p.sendMessage("-------------------------");
					p.sendMessage("/cs or /cs help - Show this help page");
					p.sendMessage("/cs join <arena> - Joins an arena");
					p.sendMessage("/cs leave - Leave current arena");
					p.sendMessage("/cs status - Check current arena status");
				}
				else {
					if(args.length == 1) {
						if (args[0].equalsIgnoreCase("leave")) {
						/* Sub-command is leave (i.e. /cs leave) */
							if (Utils.getGameState(p.getUniqueId()) != null && !Utils.getArenaByName(Utils.getGameState(p.getUniqueId())).hasStarted()) {
								p.sendMessage("You left the game.");
								arenaLeaveEvent ale = new arenaLeaveEvent(Utils.getArenaByName(Utils.getGameState(p.getUniqueId())));
								Bukkit.getServer().getPluginManager().callEvent(ale);
								Utils.setGameState(p.getUniqueId(), null);
							}
							else {
								p.sendMessage("You are not in any game, or your game has already started!");
							}
						}
						else if (args[0].equalsIgnoreCase("status")) {
							String arena = Utils.getGameState(p.getUniqueId());
							p.sendMessage("Current arena: " + ((arena == null) ? "NONE" : arena));
						}
						else if (args[0].equalsIgnoreCase("list")) {
							p.sendMessage("Arena list: ---");
							for (String n : (Set<String>) Utils.getArenas().keySet()) {
								p.sendMessage(n);
							}
							p.sendMessage("---");
						}
					}
					else if (args.length == 2) {
						if (args[0].equalsIgnoreCase("join")) {
						/* Sub-command is join (i.e. /cs join) */
							if (Utils.getGameState(p.getUniqueId()) != null) Utils.setGameState(p.getUniqueId(), null);
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
						}
						else if (args[0].equalsIgnoreCase("create")) {
							/* Sub-command is create (i.e. /cs create) */
							if (p.hasPermission("csmo.create")) {
								arenaSettings as = new arenaSettings();
								Utils.addArena(args[1], as);
							}
						}
						else if (args[0].equalsIgnoreCase("remove")) {
							if (p.hasPermission("csmo.remove")) {
								Utils.removeArena(args[1]);
							}
						}
						else if (args[0].equalsIgnoreCase("config")) {
							/* Sub-command is config */
							if (p.hasPermission("csmo.config")) {
							/* Player has config permission */
								if (!Utils.getState(p.getUniqueId())) {
								/* Player is not in config mode */
									if (Utils.getArenaByName(args[1]) != null) {
									/* There is an actual arena by that name */
										p.sendMessage("You are now setting arena " + args[1]);
										Utils.setState(p.getUniqueId(), args[1]);
										p.sendMessage("Right click to set red spawnpoint, left click to set blue spawnpoint");
										p.sendMessage("Spawnpoint is automatically set on block coordinate with z+1");
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
					else incorrectSyntax(p);
				}
			}
			/* Sender is not player (i.e. console) */
			else sender.sendMessage("This command can only be ran by player!");
			return true;
		}
		return false;
	}

	private void incorrectSyntax(Player p) {
		p.sendMessage("Incorrect syntax! Refer to /cs help for usage.");
	}
}
