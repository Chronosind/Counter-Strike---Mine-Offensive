package com.counterstrike.main;

import org.bukkit.Location;
import org.bukkit.World;

import java.util.ArrayList;

public class arenaSettings {
	private boolean started = false;
	private World arenaWorld;

	private ArrayList<Location> redPoint;
	private ArrayList<Location> bluePoint;

	private int playerCount = 0;

	String name;

	public void setName(String n){ name = n;}
	public String getName(){ return name; }

	public Location getRandomRed(){
		return (redPoint.get(Utils.rng.nextInt(bluePoint.size())));
	}

	public Location getRandomBlue(){
		return (bluePoint.get(Utils.rng.nextInt(bluePoint.size())));
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

	public World getArenaWorld(){
		return arenaWorld;
	}

	public boolean hasStarted() {
		return started;
	}

	public void addPlayerCount() {
		playerCount++;
	}

	public void decPlayerCount() {
		playerCount--;
	}

	public int getPlayerCount() {
		return playerCount;
	}
}
