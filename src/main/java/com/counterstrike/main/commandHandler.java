package com.counterstrike.main;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class commandHandler implements CommandExecutor{

	private Utils utils;
	public commandHandler(Utils u){ utils = u; }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		/* TODO: Clean up this mess */
        if(label.equalsIgnoreCase("cs")){
            if(sender instanceof Player) {
				/* Sender is player */
                Player p = (Player) sender;
                if (args[0].equalsIgnoreCase("join")) {
					/* Sub-command is join (i.e. /cs join) */
                    if(!args[1].isEmpty()) {
						/* Arena name is specified */
                        String arenaName = args[1];
                        if(utils.getArenaByName(arenaName) != null) {
							/* There is an arena with the name specified */
							p.sendMessage("You have joined arena " + arenaName + "!");
						}
						else p.sendMessage("The arena you specified doesn't exist.");
                    }
                }
				else if (args[0].equalsIgnoreCase("leave")) {
					/* Sub-command is leave (i.e. /cs leave) */
                    p.sendMessage("You left the game.");
                }
				else if(args[0].equalsIgnoreCase("create")){
					/* Sub-command is create (i.e. /cs create) */
					if(!args[1].isEmpty()){
						/* Argument 1 is not null, for arena name */
						arenaSettings as = new arenaSettings(utils);
						utils.addArena(args[1], as);
					}
					else {
						/* Arena name is not specified */
						p.sendMessage("Please specify arena name.");
					}
				}
				else if(args[0].equalsIgnoreCase("config")){
					/* Sub-command is config */
					if(p.hasPermission("csmo.config")) {
						/* Player has config permission */
						if(!utils.getState(p.getUniqueId())) {
							/* Player is not in config mode */
							if (!args[1].isEmpty()) {
								if (utils.getArenaByName(args[1]) != null) {
									/* There is an actual arena by that name */
									if(!utils.getArenaState(p.getUniqueId()).equals(args[1])){
										/* The arena name is different from the ones he/she's setting */
										p.sendMessage("You are now setting arena "+args[1]);
									}
									utils.setState(p.getUniqueId(), args[1]);
									p.sendMessage("Right click to set red spawnpoint, left click to set blue spawnpoint");
									p.sendMessage("Spawnpoint is automatically set on block coordinate with z+1");
								}
							}
							else {
								/* Arena name is not specified */
								p.sendMessage("Please specify arena name.");
							}
						}
						else{
							/* set config mode to false */
							utils.setState(p.getUniqueId(), null);
						}
					}
					else {
					/* Player does not have config permission */
						p.sendMessage("You don't have permission to do that");
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
