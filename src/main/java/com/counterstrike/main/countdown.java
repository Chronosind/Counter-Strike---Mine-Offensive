package com.counterstrike.main;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.HashMap;

public class countdown {
	private static BukkitScheduler bs;
	private static HashMap<String, Integer> taskId = new HashMap<String, Integer>();

	public static void startCountdown(Plugin p, final arenaSettings as){
		bs = Bukkit.getServer().getScheduler();
		int id = bs.scheduleSyncRepeatingTask(p, new Runnable() {
			int counter = Utils.countDown;
			public void run() {
				for(Player p : Bukkit.getServer().getOnlinePlayers()){
					if(Utils.getGameState(p.getUniqueId()).equals(as.getName())){
						p.setExp(counter/Utils.countDown);
					}
				}
				counter--;
				if(counter <= 0){
					bs.cancelTask(taskId.get(as.getName()));
					as.arenaStart();
				}
			}
		}, 0L, 20L);

		taskId.put(as.getName(), id);
	}

	public static void cancelCountdown(String arenaName){
		bs.cancelTask(taskId.get(arenaName));
	}

	public static boolean hasStarted(String arenaName) {
		return bs.isCurrentlyRunning(taskId.get(arenaName));
	}
}
