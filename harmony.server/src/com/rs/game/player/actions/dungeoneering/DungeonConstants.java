package com.rs.game.player.actions.dungeoneering;

import com.rs.Settings;
import com.rs.game.WorldTile;
import com.rs.game.player.Player;

public class DungeonConstants {

	public static WorldTile DAMONHEIM_LOBBY = new WorldTile(3450, 3725, 0), DAMONHEIM_FLOOR = new WorldTile(3461, 3721, 1), DAMONHEIM_STAIRS_SLIDEDOWN = new WorldTile(3454, 3724, 0);

	public enum Room {
		START, BOSS;
	}

	public static int[][] FROZEN_ROOMS = new int[][] { { 14, 624 }, { 18, 532 }, { 28, 624 } };//1-75
	public static int[][] FROZEN_ROOMS_LOWER = new int[][] { { 14, 624 }, { 18, 532 }, { 28, 624 } };//75-105
	public static int[][] FROZEN_ROOMS_MEDIUM = new int[][] { { 14, 624 }, { 18, 532 }, { 28, 624 } };//110-125
	public static int[][] FROZEN_ROOMS_HIGH = new int[][] { { 14, 624 }, { 18, 532 }, { 28, 624 } };//125-138


	// 1 //2 //3 //4 //5				//low //lower //medium //high !Floors
	public static int[] BOSS = new int[] { 9952, 9953, 9955, 9959 };

	

	public static void getDungTokens(Player player) {
		if (player.getSkills().getCombatLevelWithSummoning() == 138) {
			player.dungpoints += (Settings.XP_RATE) / 11;
		} else if (player.getSkills().getCombatLevelWithSummoning() > 125) {
			player.dungpoints += (Settings.XP_RATE) / 9;
		} else if (player.getSkills().getCombatLevelWithSummoning() > 85) {
			player.dungpoints += (Settings.XP_RATE) / 8;
		} else if (player.getSkills().getCombatLevelWithSummoning() > 3) {
			player.dungpoints += (Settings.XP_RATE) / 7;
		}

	}
}