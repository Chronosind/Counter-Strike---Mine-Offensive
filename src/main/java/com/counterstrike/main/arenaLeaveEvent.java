package com.counterstrike.main;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class arenaLeaveEvent extends Event {

	private static final HandlerList handlers = new HandlerList();

	public arenaLeaveEvent(arenaSettings as) {
		as.decPlayerCount();
		if (as.getPlayerCount() < Utils.minPlayers) {
			/* TODO: Cancel countdown */
		}
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
}
