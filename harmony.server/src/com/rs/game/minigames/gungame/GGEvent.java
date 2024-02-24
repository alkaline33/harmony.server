package com.rs.game.minigames.gungame;

import java.util.TimerTask;
import java.util.Map.Entry;

import com.rs.Settings;
import com.rs.cores.CoresManager;
import com.rs.game.Animation;
import com.rs.game.ForceTalk;
import com.rs.game.Graphics;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.player.Player;
import com.rs.utils.Utils;

/*
*Author: Jens19963
*/

public class GGEvent extends Bots {

	public static void startInvasion() {
		CoresManager.fastExecutor.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				if (players.isEmpty()) {
					System.out.println("Playerlist empty in gungame. Ending event.");
					killAllEvents();
					this.cancel();
					return;
				}
				if (npcList.size() < 50) {
					GGEvent.spawnNextWave();
				}
			}
		}, START_DELAY, SPAWN_DELAY); // 8 seconds
	}
	
	public static void spawnNextWave() {
		int x, y;
		wave++;
		GunGameBoss.setNextForceTalk(new ForceTalk(Bots.messages[Utils.random(Bots.messages.length - 1)]));
		GunGameBoss.setNextGraphics(new Graphics(246));
			x = Utils.random(10) < 5 ? center.getX() + Utils.random(5) : center.getX() - Utils.random(5);
			y = Utils.random(10) < 5 ? center.getY() + Utils.random(0) : center.getY() - Utils.random(5);
		NPC npc = new NPC(npcId, new WorldTile(x ,y, 0), -1, true, true);
		npc.setAtMultiArea(true);
		npc.setForceMultiArea(true);
		npc.setForceMultiAttacked(true);
		npc.setForceAgressive(true);
		npc.setNextGraphics(new Graphics(246));
		npc.setCombatLevel(117);
		npcList.put(wave, npc);
		npcList.get(wave).setName("Bot <col=FFFFFF>"+Settings.BOTS[Utils.random(5)]+"</col>");
		sendSpawnSound();
		send("<col=FF0000>Wave "+wave+" inbound!");
		GunGameBoss.faceEntity(npc);
	}
	
	public static boolean runEventCheck() {
		if (players.isEmpty()) {
			killAllEvents();
			return false;
		}
		return true;
	}
	
	public static void sendSpawnSound() {
		for (Entry<Integer, Player> plr : players.entrySet()) {
			Player pl = plr.getValue();
			if (pl == null)
				continue;
			pl.getPackets().sendSound(6, 0, 1);
		}
	}
	
}
