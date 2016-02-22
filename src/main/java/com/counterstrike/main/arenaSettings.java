package com.counterstrike.main;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.UUID;

public class arenaSettings {
	HashSet<UUID> redTeam = new HashSet<UUID>();
	HashSet<UUID> blueTeam = new HashSet<UUID>();
	HashSet<UUID> players = new HashSet<UUID>();
	String name;
	private boolean started = false;
	private World arenaWorld;
	private ArrayList<Location> redPoint;
	private ArrayList<Location> bluePoint;
	private int playerCount = 0;

	public String getName(){ return name; }

	public void setName(String n) {
		name = n;
	}

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

	public void addPlayerCount(UUID puid) {
		playerCount++;
		players.add(puid);
	}

	public void decPlayerCount(UUID puid) {
		playerCount--;
		players.remove(puid);
	}

	public int getPlayerCount() {
		return playerCount;
	}

	public void arenaStart() {
		Iterator iterator = players.iterator();
		int counter = 0;
		while (iterator.hasNext()) {
			if (counter <= playerCount / 2) {
				redTeam.add((UUID) iterator.next());
			}
			else blueTeam.add((UUID) iterator.next());
			counter += 1;
		}

		iterator = redTeam.iterator();
		while (iterator.hasNext()) {
			((Player) iterator.next()).teleport(getRandomRed());
		}

		iterator = blueTeam.iterator();
		while (iterator.hasNext()) {
			((Player) iterator.next()).teleport(getRandomBlue());
		}

		started = true;
	}
}
