package main.java.com.counterstrike.main;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class arenaJoinEvent extends Event {

	private static final HandlerList handlers = new HandlerList();

	public arenaJoinEvent(arenaSettings as) {
		as.addPlayerCount();
		if (as.getPlayerCount() > Utils.minPlayers) {
			/* TODO: Begin countdown */
		}
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
}
