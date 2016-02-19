package com.counterstrike.main;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class clickEvent implements Listener{

	@EventHandler
	public void playerInteract(PlayerInteractEvent e){
		if (Utils.getState(e.getPlayer().getUniqueId())) {
			/* the event Player is in config mode (i.e. spawnpoint setting mode) */
			if(e.getAction() == Action.LEFT_CLICK_BLOCK){
				/* event is left click, set blue spawn coords */
				Utils.getArenaByName(Utils.getArenaState(e.getPlayer().getUniqueId())).addBluePoint(e.getClickedBlock().getLocation().add(0, 0, 1), e.getPlayer().getWorld());
				e.getPlayer().sendMessage("Blue spawnpoint added in: "+e.getClickedBlock().getLocation());
			}if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
				/* event is right click, set red spawn coords */
				Utils.getArenaByName(Utils.getArenaState(e.getPlayer().getUniqueId())).addRedPoint(e.getClickedBlock().getLocation().add(0, 0, 1), e.getPlayer().getWorld());
				e.getPlayer().sendMessage("Blue spawnpoint added in: "+e.getClickedBlock().getLocation());
			}
			e.setCancelled(true);
		}
	}
}
