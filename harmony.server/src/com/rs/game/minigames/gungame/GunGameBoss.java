package com.rs.game.minigames.gungame;

import java.util.concurrent.ConcurrentHashMap;

import com.rs.Settings;
import com.rs.game.Animation;
import com.rs.game.ForceTalk;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.player.Player;
import com.rs.game.player.content.Magic;
import com.rs.game.player.content.Pots;
import com.rs.game.player.controlers.Controler;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.BotRank;
import com.rs.utils.Utils;

/*
*Author: Jens19963
*/

public class GunGameBoss extends Controler {

	protected static ConcurrentHashMap<Integer, Player> players = new ConcurrentHashMap<Integer, Player>();
	protected static ConcurrentHashMap<Integer, NPC> npcList = new ConcurrentHashMap<Integer, NPC>();
	
	protected static boolean endAll = false;// kills all the events
	protected static WorldTile center = new WorldTile(3498, 3633, 0); // center of the arena
	protected static NPC GunGameBoss;
	protected static int START_DELAY = 10000; // 10 seconds
	protected static int SPAWN_DELAY = 15000; // 15 seconds
	public static int wave = 0;
	protected static int npcId = 8164;
	protected static int BANDAGES = 15272;
	
	protected static int DEATH_RUNE = 560;
	protected static int BLOOD_RUNE = 565;
	protected static int WATER_RUNE = 555;
	protected static int RUNE_AMOUNT = 20;
	protected static int BANDAGE_AMOUNT = 20;

	protected static int POT_1 = 15308; //extreme atk pot
	protected static int POT_2 = 15312; //extreme str flask
	protected static int POTS_AMOUNT = 1;
	
	@Override
	public void start() {
		player.getPrayer().resetStatAdjustments();
		player.sendMessage("<col=DB0000>Welcome to Harmony GunGame! Bots will begin spawning every 15 seconds");
		player.sendMessage("<col=DB0000>either until everyone's dead or has left the minigame.");
		player.sendMessage("<col=DB0000>Please make sure you have inventory space for upgraded weapons.");
		Magic.sendAncientTeleportSpell(player, 0, 0, new WorldTile(3499, 3621, 0));
		player.getInterfaceManager().sendOverlay(1009, false);
		player.getPackets().sendIComponentText(1009, 0, "Bot Points: <col=FFFFFF>"+player.getBP()+"</col>");
		players.put(player.getIndex(), player);
		player.getInventory().addItem(15272, BANDAGE_AMOUNT);
		player.getInventory().addItem(POT_1, 1);
		player.getInventory().addItem(POT_2, 1);
		player.getInventory().addItem(1075, 1);
		player.getInventory().addItem(1117, 1);
		player.getInventory().addItem(1155, 1);
		player.getInventory().addItem(1189, 1);
		player.getInventory().addItem(1205, 1);
		Bots.send("<col=008F00>"+player.getDisplayName()+" has joined Harmony GunGame!");
		
		if (GunGameBoss == null) {
			GunGameBoss = new NPC(892, center, -1, true, true);
			GGEvent.startInvasion();
		} else {
			GunGameBoss.setNextForceTalk(new ForceTalk(Bots.getJoinMessage(player)));
		}
		
		//Pots.r(player);
		player.getPrayer().drainPrayer(player.getPrayer().getPrayerpoints());
	}
	
	@Override
	public boolean processMagicTeleport(WorldTile tile) {
		player.sendMessage("You are not allowed to teleport out of here. Only way out is death!");
		return false;
	}
	
	@Override
	public boolean logout() {
		Bots.removePlayer(player);
		player.getEquipment().reset();
		player.getInventory().reset();
		Bots.send(player.getDisplayName()+" has left the GunGame!");
		player.setLocation(new WorldTile(Settings.RESPAWN_PLAYER_LOCATION));
		return true;
	}
	
	@Override
	public boolean sendDeath() {
		WorldTasksManager.schedule(new WorldTask() {
			int loop;
			@Override
			public void run() {
				if (loop == 0) {
					player.setNextAnimation(new Animation(836));
					player.setWildernessSkull();
				} else if (loop == 1) {
					World.sendWorldMessage("<col=FF3A00>[GunGame] "+player.getDisplayName()+" was killed by a bot, "+player.getDisplayName()+" has slayed "+player.getBotKillstreak()+" Bots total!", false);
					player.setBotKillstreak(0);
					player.getPackets().sendResetCamera();
				} else if (loop == 3) {
					player.getEquipment().reset();
					player.getInventory().reset();
					player.reset();
					player.setNextWorldTile(Settings.RESPAWN_PLAYER_LOCATION);
					player.setNextAnimation(new Animation(-1));
				} else if (loop == 4) {
					Bots.removePlayer(player);
					player.getPackets().sendMusicEffect(90);
					stop();
				}
				loop++;
			}
		}, 0, 1);
		return true;
	}
	
	public static boolean sendSound = false;
	
	@Override
	public void process() {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
