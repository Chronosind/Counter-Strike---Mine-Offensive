package com.counterstrike.main;

import java.util.HashMap;
import java.util.Random;

public class Utils {

	Random rng;

	HashMap<String, arenaSettings> arenas = new HashMap<String, arenaSettings>();

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
}
