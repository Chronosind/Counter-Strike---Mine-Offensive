package com.counterstrike.main;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class arenaLeaveEvent extends Event {

	private static final HandlerList handlers = new HandlerList();

	public arenaLeaveEvent(arenaSettings as) {
		as.decPlayerCount();
		if (as.getPlayerCount() < Utils.minPlayers && countdown.hasStarted(as.getName())) {
			countdown.cancelCountdown(as.getName());
		}
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
}
