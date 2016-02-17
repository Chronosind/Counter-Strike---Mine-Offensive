package com.counterstrike.main;

import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

public class Utils {

	Random rng;

	private HashMap<String, arenaSettings> arenas = new HashMap<String, arenaSettings>();
	private HashMap<UUID, String> state = new HashMap<UUID, String>();

	public Utils(){
		rng = new Random();
	}

	public arenaSettings getArenaByName(String name){
		return arenas.get(name);
	}

	public void addArena(String name, arenaSettings set){
		arenas.put(name, set);
	}

	public void removeArena(String name){
		arenas.get(name).clear();
		arenas.remove(name);
	}

	public boolean getState(UUID uuid){
		return state.get(uuid) != null;
	}

	public String getArenaState(UUID uuid){
		return state.get(uuid);
	}

	public void setState(UUID uuid, String set){
		state.put(uuid, set);
	}
}
