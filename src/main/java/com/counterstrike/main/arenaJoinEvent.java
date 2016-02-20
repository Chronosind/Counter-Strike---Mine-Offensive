package com.counterstrike.main;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;

public class arenaJoinEvent extends Event {

	private static final HandlerList handlers = new HandlerList();

	public arenaJoinEvent(arenaSettings as, Plugin p) {
		as.addPlayerCount();
		if (as.getPlayerCount() > Utils.minPlayers) {
			countdown.startCountdown(p, as);
		}
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
}
