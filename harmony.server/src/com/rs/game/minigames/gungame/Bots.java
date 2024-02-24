package com.rs.game.minigames.gungame;

import java.util.Map.Entry;

import com.rs.game.Hit;
import com.rs.game.Hit.HitLook;
import com.rs.game.World;
import com.rs.game.npc.NPC;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.utils.Utils;
import com.rs.utils.BotRank;

/*
*Author: Jens19963
*/

public class Bots extends GunGameBoss {
	
	public static void clearInventory(Player player) {
		player.getInventory().deleteItem(BANDAGES, 28);
		player.getInventory().deleteItem(BLOOD_RUNE, Integer.MAX_VALUE);
		player.getInventory().deleteItem(DEATH_RUNE, Integer.MAX_VALUE);
		player.getInventory().deleteItem(WATER_RUNE, Integer.MAX_VALUE);
		player.getInventory().deleteItem(POT_1, 28);
		player.getInventory().deleteItem(POT_2, 28);
	}
	
	public static boolean killAllEvents() {
		for (Entry<Integer, NPC> npcs : npcList.entrySet()) {
			if (npcs.getValue() == null)
				continue;
			npcs.getValue().reset();
			npcs.getValue().finish();
		}
		wave = 0;
		GunGameBoss.reset();
		GunGameBoss.finish();
		GunGameBoss = null;
		sendResetCamera();
		players.clear();
		npcList.clear();
		return true;
	}
	
	public static void applyReward(Player player, int waveId) {
		player.setBotKillstreak(player.getBotKillstreak() + 1);
		player.setBotKills(player.getBotKills() + 1);
		player.getInterfaceManager().sendOverlay(1009, false);
		player.getPackets().sendIComponentText(1009, 0, "Bot Points: <col=FFFFFF>"+player.getBP()+"</col>");
		int killStreak = player.getBotKillstreak();
		int Botkills = player.getBotKills();
		
		int points = player.getBotKillstreak() > 10 ? 1 :
			player.getBotKillstreak() < 20 ? 1 :
			player.getBotKillstreak() < 30 ? 1 :
			player.getBotKillstreak() < 40 ? 1 : 1;
		
		player.setBP(player.getBP() + points);
		
		npcList.remove(waveId);
		
		if (Botkills == 1) {
			if (player.getInventory().getFreeSlots() == 0) {
				player.sendMessage("<col=FF0000>You didn't get an upgraded weapon as you have no free slots!");
			}
			player.getInventory().addItem(1277, 1);
			player.sendMessage("<col=FF0000>You've upgraded your weapon, check inventory.");
		}
		
		if (Botkills == 2) {
			player.getInventory().addItem(1291, 1);
			player.sendMessage("<col=FF0000>You've upgraded your weapon, check inventory.");
		}
		
		if (Botkills == 3) {
			player.getInventory().addItem(1321, 1);
			player.sendMessage("<col=FF0000>You've upgraded your weapon, check inventory.");
		}
		
		if (Botkills == 4) {
			player.getInventory().addItem(1203, 1);
			player.sendMessage("<col=FF0000>You've upgraded your weapon, check inventory.");
		}
		
		if (Botkills == 5) {
			player.getInventory().addItem(1279, 1);
			player.sendMessage("<col=FF0000>You've upgraded your weapon, check inventory.");
		}
		
		if (Botkills == 6) {
			player.getInventory().addItem(1293, 1);
			player.sendMessage("<col=FF0000>You've upgraded your weapon, check inventory.");
		}
		
		if (Botkills == 7) {
			player.getInventory().addItem(1323, 1);
			player.sendMessage("<col=FF0000>You've upgraded your weapon, check inventory.");
		}
		
		if (Botkills == 8) {
			player.getInventory().addItem(1207, 1);
			player.sendMessage("<col=FF0000>You've upgraded your weapon, check inventory.");
		}
		
		if (Botkills == 9) {
			player.getInventory().addItem(1281, 1);
			player.sendMessage("<col=FF0000>You've upgraded your weapon, check inventory.");
		}
		
		if (Botkills == 10) {
			player.getInventory().addItem(1295, 1);
			player.sendMessage("<col=FF0000>You've upgraded your weapon, check inventory.");
		}
		
		if (Botkills == 11) {
			player.getInventory().addItem(1325, 1);
			player.sendMessage("<col=FF0000>You've upgraded your weapon, check inventory.");
		}
		
		if (Botkills == 12) {
			player.getInventory().addItem(1217, 1);
			player.sendMessage("<col=FF0000>You've upgraded your weapon, check inventory.");
		}
		
		if (Botkills == 13) {
			player.getInventory().addItem(1283, 1);
			player.sendMessage("<col=FF0000>You've upgraded your weapon, check inventory.");
		}
		
		if (Botkills == 14) {
			player.getInventory().addItem(1297, 1);
			player.sendMessage("<col=FF0000>You've upgraded your weapon, check inventory.");
		}
		
		if (Botkills == 15) {
			player.getInventory().addItem(1327, 1);
			player.sendMessage("<col=FF0000>You've upgraded your weapon, check inventory.");
		}
		
		if (Botkills == 16) {
			player.getInventory().addItem(1209, 1);
			player.sendMessage("<col=FF0000>You've upgraded your weapon, check inventory.");
		}
		
		if (Botkills == 17) {
			player.getInventory().addItem(1285, 1);
			player.sendMessage("<col=FF0000>You've upgraded your weapon, check inventory.");
		}
		
		if (Botkills == 18) {
			player.getInventory().addItem(1299, 1);
			player.sendMessage("<col=FF0000>You've upgraded your weapon, check inventory.");
		}
		
		if (Botkills == 19) {
			player.getInventory().addItem(1329, 1);
			player.sendMessage("<col=FF0000>You've upgraded your weapon, check inventory.");
		}
		
		if (Botkills == 20) {
			player.getInventory().addItem(1211, 1);
			player.sendMessage("<col=FF0000>You've upgraded your weapon, check inventory.");
		}
		
		if (Botkills == 21) {
			player.getInventory().addItem(1287, 1);
			player.sendMessage("<col=FF0000>You've upgraded your weapon, check inventory.");
		}
		
		if (Botkills == 22) {
			player.getInventory().addItem(1301, 1);
			player.sendMessage("<col=FF0000>You've upgraded your weapon, check inventory.");
		}
		
		if (Botkills == 23) {
			player.getInventory().addItem(1331, 1);
			player.sendMessage("<col=FF0000>You've upgraded your weapon, check inventory.");
		}
		
		if (Botkills == 24) {
			player.getInventory().addItem(1213, 1);
			player.sendMessage("<col=FF0000>You've upgraded your weapon, check inventory.");
		}
		
		if (Botkills == 25) {
			player.getInventory().addItem(1289, 1);
			player.sendMessage("<col=FF0000>You've upgraded your weapon, check inventory.");
		}
		
		if (Botkills == 26) {
			player.getInventory().addItem(1303, 1);
			player.sendMessage("<col=FF0000>You've upgraded your weapon, check inventory.");
		}
		
		if (Botkills == 27) {
			player.getInventory().addItem(1333, 1);
			player.sendMessage("<col=FF0000>You've upgraded your weapon, check inventory.");
		}
		
		if (Botkills == 28) {
			player.getInventory().addItem(1215, 1);
			player.sendMessage("<col=FF0000>You've upgraded your weapon, check inventory.");
		}
		
		if (Botkills == 29) {
			player.getInventory().addItem(1249, 1);
			player.sendMessage("<col=FF0000>You've upgraded your weapon, check inventory.");
		}
		
		if (Botkills == 30) {
			player.getInventory().addItem(1305, 1);
			player.sendMessage("<col=FF0000>You've upgraded your weapon, check inventory.");
		}
		
		if (Botkills == 31) {
			player.getInventory().addItem(1377, 1);
			player.sendMessage("<col=FF0000>You've upgraded your weapon, check inventory.");
		}
		
		if (Botkills == 32) {
			player.getInventory().addItem(1434, 1);
			player.sendMessage("<col=FF0000>You've upgraded your weapon, check inventory.");
		}
		
		if (Botkills == 33) {
			player.getInventory().addItem(4153, 1);
			player.sendMessage("<col=FF0000>You've upgraded your weapon, check inventory.");
		}
		
		if (player.getBotKillstreak() > player.getMaxBotWave()) {
			player.setMaxBotWave(player.getBotKillstreak());
		}
		
		player.sendMessage("You've killed a total of "+player.getBotKillstreak()+" Bots.");
		//player.lastOnslaughtKill = Utils.currentTimeMillis();
	}
	
	public static void removePlayer(Player player) {
		players.remove(player.getIndex());
		clearInventory(player);
		player.getPackets().sendResetCamera();
		player.setBotKills(0);
		player.getControlerManager().removeControlerWithoutCheck();
	}
	
	private static String[] joinMessages = {
		"You won't keep your items when you die",
		"This'll be the end of you",
		"Welcome to my GunGame",
		"Here you shall fall like the rest",
	};
	
	public static String getJoinMessage(Player player) {
		return joinMessages[Utils.random(joinMessages.length - 1)] + ", "+player.getDisplayName()+"";
	}
	
	public static String[] messages = {
		"Arise my minion.. ARRIISSEEE!!!",
		"All of ye who enter, SHALL PARISH!",
		"Kill thine enemies!",
		"Come to life my minion! Arise!",
	};
	
	public static void sendResetCamera() {
		for (Entry<Integer, Player> plr : players.entrySet()) {
			Player pl = plr.getValue();
			if (pl == null)
				continue;
			pl.getPackets().sendResetCamera();
		}
	}
	
	public static void send(String message) {
		for (Entry<Integer, Player> plr : players.entrySet()) {
			Player player = plr.getValue();
			if (player == null)
				continue;
			player.sendMessage(message);
		}
	}
	
}
