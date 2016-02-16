package com.counterstrike.main;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class commandHandler implements CommandExecutor{

	Utils utils;
	public commandHandler(Utils u){ utils = u; }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(label.equalsIgnoreCase("cs")){
            if(sender instanceof Player) {
				/* Sender is player */
                Player p = (Player) sender;
                if (args[0].equalsIgnoreCase("join")) {
					/* Sub-command is join (i.e. /cs join */
                    if(!args[1].isEmpty()) {
						/* Arena name is specified */
                        String arenaName = args[1];
                        if(utils.getArenaByName(arenaName) != null) {
							/* There is an arena with the name specified */
							p.sendMessage("You have joined arena " + arenaName + "!");
						}
						else p.sendMessage("The arena you specified doesn't exist.");
                    }
                } else if (args[0].equalsIgnoreCase("leave")) {
					/* Sub-command is leave (i.e. /cs leave */
                    p.sendMessage("You left the game.");
                }
            }
			/* Sender is not player (i.e. console) */
            else sender.sendMessage("This command can only be ran by player!");
            return true;
        }
        return false;
    }
}
