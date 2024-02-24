package com.rs.game.player.content;

import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.player.Player;
import com.rs.utils.Colors;
import com.rs.utils.Utils;

public class TreasureHunt {

	/**
	 * TODO - Gives items , item been found, item loot
	 */

	/**
	 * Locations of spawn
	 */
	public final static WorldTile[] LOCATION = { new WorldTile(2809, 3352, 0), // Home
			new WorldTile(2901, 2732, 0), // Train
			new WorldTile(3571, 3311, 0), // Barrows
			new WorldTile(3118, 9863, 0), // hill giants
			new WorldTile(2903, 4460, 0), // DKS
			new WorldTile(2732, 3474, 0), // Woodcutting
			new WorldTile(2650, 3294, 0), // Thieving
			new WorldTile(2524, 2918, 0), // Hunter
			new WorldTile(2471, 3423, 1), // Agility
			new WorldTile(3301, 3317, 0), // Mining
			new WorldTile(2593, 3427, 0), // Fishing
			new WorldTile(2875, 2968, 0), // Shilo Village
			new WorldTile(3039, 4830, 0), // Abyss
			new WorldTile(2886, 4849, 0), // Essence mine
			new WorldTile(2834, 3423, 0), // Charging orbs
			new WorldTile(3689, 5145, 0), // LRC
			new WorldTile(3164, 3470, 0), // Grand exchange
			new WorldTile(3440, 3554, 0), // Slayer Tower
			new WorldTile(2876, 9810, 0), // Tav Dung
			new WorldTile(2907, 9737, 0), // Tav dung
			new WorldTile(2164, 3867, 0), // Astral rune altar
			new WorldTile(3090, 3470, 0), // Edgeville
			new WorldTile(3042, 3375, 0), // Party Room
			new WorldTile(3222, 3427, 0), // Varrock
	};
	
	/**
	 * Spawning of Chest
	 */
	public static void spawnChest() {
		/**
		 * Grabs location -//
		 */
		WorldTile lastTile = LOCATION[Utils.random(0, 22)];

		/**
		 * Spawns chest
		 */
		World.spawnTemporaryObject(new WorldObject(29577, 10, 0, lastTile), 1800000, true);
		System.out.println("Treasure Hunt: Spawned at " + lastTile.getX() + " : " + lastTile.getY());
		World.sendIconWorldMessage(Colors.cyan+"Treasure Hunt: A treasure hunt has started! Good luck!", false);
		if (lastTile.getX() == 2809 && lastTile.getY() == 3352) {
			World.sendIconWorldMessage(Colors.cyan+"Treasure Hunt Hint: Entrana", false);
		} else if (lastTile.getX() == 2901 && lastTile.getY() == 2732) {
			World.sendIconWorldMessage(Colors.cyan+"Treasure Hunt Hint: Just starting out...", false);
		} else if (lastTile.getX() == 3571 && lastTile.getY() == 3311) {
			World.sendIconWorldMessage(Colors.cyan+"Treasure Hunt Hint: Hills everywhere", false);
		} else if (lastTile.getX() == 3118 && lastTile.getY() == 9863) {
			World.sendIconWorldMessage(Colors.cyan+"Treasure Hunt Hint: Brass key", false);
		} else if (lastTile.getX() == 2903 && lastTile.getY() == 4460) {
			World.sendIconWorldMessage(Colors.cyan+"Treasure Hunt Hint: Maybe I could get a ring...", false);
		} else if (lastTile.getX() == 2732 && lastTile.getY() == 3474) {
			World.sendIconWorldMessage(Colors.cyan+"Treasure Hunt Hint: Chop chop!", false);
		} else if (lastTile.getX() == 2650 && lastTile.getY() == 3294) {
			World.sendIconWorldMessage(Colors.cyan+"Treasure Hunt Hint: Thiefs!", false);
		} else if (lastTile.getX() == 2524 && lastTile.getY() == 2918) {
			World.sendIconWorldMessage(Colors.cyan+"Treasure Hunt Hint: Come on, get in the trap!", false);
		} else if (lastTile.getX() == 2471 && lastTile.getY() == 3423) {
			World.sendIconWorldMessage(Colors.cyan+"Treasure Hunt Hint: Balance...", false);
		} else if (lastTile.getX() == 3301 && lastTile.getY() == 3317) {
			World.sendIconWorldMessage(Colors.cyan+"Treasure Hunt Hint: Smashing!", false);
		} else if (lastTile.getX() == 2593 && lastTile.getY() == 3427) {
			World.sendIconWorldMessage(Colors.cyan+"Treasure Hunt Hint: Don't disturb the water!", false);
		} else if (lastTile.getX() == 2875 && lastTile.getY() == 2968) {
			World.sendIconWorldMessage(Colors.cyan+"Treasure Hunt Hint: In the jungle", false);
		} else if (lastTile.getX() == 3039 && lastTile.getY() == 4830) {
			World.sendIconWorldMessage(Colors.cyan+"Treasure Hunt Hint: Into the Abyss", false);
		} else if (lastTile.getX() == 2886 && lastTile.getY() == 4849) {
			World.sendIconWorldMessage(Colors.cyan+"Treasure Hunt Hint: The pure... essence", false);
		} else if (lastTile.getX() == 2834 && lastTile.getY() == 3423) {
			World.sendIconWorldMessage(Colors.cyan+"Treasure Hunt Hint: Air, Water, Earth, Fire", false);
		} else if (lastTile.getX() == 3689 && lastTile.getY() == 5145) {
			World.sendIconWorldMessage(Colors.cyan+"Treasure Hunt Hint: The rocks appear to be... living", false);
		} else if (lastTile.getX() == 3164 && lastTile.getY() == 3470) {
			World.sendIconWorldMessage(Colors.cyan+"Treasure Hunt Hint: The best market in Gielinor!", false);
		} else if (lastTile.getX() == 3440 && lastTile.getY() == 3554) {
			World.sendIconWorldMessage(Colors.cyan+"Treasure Hunt Hint: Kuradal would love this place!", false);
		} else if (lastTile.getX() == 2876 && lastTile.getY() == 9810) {
			World.sendIconWorldMessage(Colors.cyan+"Treasure Hunt Hint: Black Knights meet here? In a dungeon...?", false);
		} else if (lastTile.getX() == 2907 && lastTile.getY() == 9737) {
			World.sendIconWorldMessage(Colors.cyan+"Treasure Hunt Hint: Black Knights meet here? In a dungeon...?", false);
		} else if (lastTile.getX() == 2164 && lastTile.getY() == 3867) {
			World.sendIconWorldMessage(Colors.cyan+"Treasure Hunt Hint: Baba Yaga", false);
		} else if (lastTile.getX() == 3090 && lastTile.getY() == 3470) {
			World.sendIconWorldMessage(Colors.cyan+"Treasure Hunt Hint: An amulet of glory will get you here.", false);
		} else if (lastTile.getX() == 3042 && lastTile.getY() == 3375) {
			World.sendIconWorldMessage(Colors.cyan+"Treasure Hunt Hint: Drop party!", false);
		} else if (lastTile.getX() == 3222 && lastTile.getY() == 3427) {
			World.sendIconWorldMessage(Colors.cyan+"Treasure Hunt Hint: In the city with a White Tree Patch", false);
		}
	}
		
	/**
	 * Handling rewards & notification
	 */
	public static void SearchChest(Player player, int objectx, int objecty, int objectz) {
		if (World.containsObjectWithId(new WorldTile(objectx, objecty, objectz), 29578)) {
			player.sendMessage(Colors.red + "This chest has already been looted!");
			return;
		}
		World.spawnTemporaryObject(new WorldObject(29577 + 1, 10, 0, objectx, objecty, objectz), 6000, true);
		player.treasurechestshunted++;

		switch (Utils.random(1, 4)) {
		case 1:
			int coinsadded = Utils.random(1000000, 2500000);
			player.getInventory().addItemDrop(995, coinsadded);
			World.sendIconWorldMessage(Colors.cyan + "Treasure Hunt: " + player.getDisplayName() + " has found " + Utils.format(coinsadded) + " coins!", false);
			player.sendMessage("You open the treasure chest and find....");
			player.sendMessage(Colors.red + "" + Utils.format(coinsadded) + " coins!");
			break;
		case 2:
			int pointsadded = Utils.random(1000, 2500);
			player.Drypoints += pointsadded;
			World.sendIconWorldMessage(Colors.cyan + "Treasure Hunt: " + player.getDisplayName() + " has found " + Utils.format(pointsadded) + " Harmony points!", false);
			player.sendMessage("You open the treasure chest and find....");
			player.sendMessage(Colors.red + "" + Utils.format(pointsadded) + " Harmony points!");
			break;
		default:
			if (Utils.random(1000) == 0) {
				player.getInventory().addItemDrop(29281, 1);
				World.sendIconWorldMessage(Colors.cyan + "Treasure Hunt: " + player.getDisplayName() + " has found a Golden pirate hat!", false);
				player.sendMessage("You open the treasure chest and find....");
				player.sendMessage(Colors.red + "A Golden pirate hat!");
			} else {
				player.getInventory().addItemDrop(13357, 1);
			World.sendIconWorldMessage(Colors.cyan + "Treasure Hunt: " + player.getDisplayName() + " has found a useless dusty pirate hat!", false);
			player.sendMessage("You open the treasure chest and find....");
			player.sendMessage(Colors.red + "A useless dusty pirate hat!");
			}
			break;
		}

	}
}
