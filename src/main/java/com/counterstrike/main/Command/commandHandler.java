package com.counterstrike.main.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class commandHandler implements CommandExecutor{

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(label.equalsIgnoreCase("cs")){
            if(sender instanceof Player) {
                Player p = (Player) sender;
                if (args[0].equalsIgnoreCase("join")) {
                    if(!args[1].isEmpty()) {
                        String arenaName = args[1];
                        p.sendMessage("You have joined arena "+arenaName+"!");
                    }
                } else if (args[0].equalsIgnoreCase("leave")) {
                    p.sendMessage("You left the game.");
                }
            }
            else sender.sendMessage("This command can only be ran by player!");
            return true;
        }
        return false;
    }
}
