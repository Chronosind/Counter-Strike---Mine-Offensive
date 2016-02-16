package com.counterstrike.main;

import org.bukkit.Location;
import org.bukkit.World;

import java.util.ArrayList;

public class arenaSettings {
	World arenaWorld;

	ArrayList<Location> redPoint;
	ArrayList<Location> bluePoint;

	Utils utils;

	public arenaSettings(Utils u){utils = u;}

	public Location getRandomRed(){
		return (redPoint.get(utils.rng.nextInt(bluePoint.size())));
	}

	public Location getRandomBlue(){
		return (bluePoint.get(utils.rng.nextInt(bluePoint.size())));
	}

	public void addRedPoint(Location loc, World w){
		redPoint.add(loc);
		arenaWorld = w;
	}

	public void addBluePoint(Location loc, World w){
		bluePoint.add(loc);
		arenaWorld = w;
	}

	public void clear(){
		arenaWorld = null;
		redPoint = null;
		bluePoint = null;
	}
}
