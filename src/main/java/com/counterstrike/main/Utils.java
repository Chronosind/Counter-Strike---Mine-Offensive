package com.counterstrike.main;

import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

public class Utils {

	static Random rng = new Random();
	private static HashMap<String, arenaSettings> arenas = new HashMap<String, arenaSettings>();
	private static HashMap<UUID, String> state = new HashMap<UUID, String>();
	private static HashMap<UUID, String> gameState = new HashMap<UUID, String>();
	final static int minPlayers = 2;
	final int countDown = 180; /* Seconds */

	public static arenaSettings getArenaByName(String name) {
		return arenas.get(name);
	}

	public static void addArena(String name, arenaSettings set) {
		arenas.put(name, set);
	}

	public static void removeArena(String name) {
		arenas.get(name).clear();
		arenas.remove(name);
	}

	public static boolean getState(UUID uuid) {
		return state.get(uuid) != null;
	}

	public static String getArenaState(UUID uuid) {
		return state.get(uuid);
	}

	public static void setState(UUID uuid, String set) {
		state.put(uuid, set);
	}

	public static String getGameState(UUID uuid) {
		return gameState.get(uuid);
	}

	public static void setGameState(UUID uuid, String aname) {
		gameState.put(uuid, aname);
	}
}
