package com.rs.game.player.content;

import com.rs.game.player.Player;
import com.rs.utils.Colors;
import com.rs.utils.Utils;

public class DailyChallenges {

	/**
	 * 
	 * @Author Connor
	 */

	private static void Reward(Player player) {
		player.challengepoints += 10;
		if (player.getInventory().hasFreeSlots()) {
		player.getInventory().addItem(23713, 1);
		} else {
			player.getBank().addItem(23713,  1, true);
		}
		player.challengeid = 0;
		player.sendMessage(Colors.orange + "You've received 10 Daily challenge points. You now have "
				+ player.challengepoints + " points.");
		return;
	}

	public static void CurrentChallenge(Player player) {
		switch (player.challengeid) {
		/*
		 * Prayer
		 */
		case 1:
			player.sendMessage(
					Colors.orange + "Update: Your daily task is to bury " + player.challengeamount + " Big bones");
			break;
		case 2:
			player.sendMessage(
					Colors.orange + "Update: Your daily task is to bury " + player.challengeamount + " Dragon bones");
			break;
		case 3:
			player.sendMessage(Colors.orange + "Update: Your daily task is to bury " + player.challengeamount
					+ " Superior dragon bones");
			break;
		/*
		 * Runecrafting
		 */
		case 4:
			player.sendMessage(
					Colors.orange + "Update: Your daily task is to craft " + player.challengeamount + " Air runes");
			break;
		case 5:
			player.sendMessage(
					Colors.orange + "Update: Your daily task is to craft " + player.challengeamount + " Death runes");
			break;
		case 6:
			player.sendMessage(
					Colors.orange + "Update: Your daily task is to craft " + player.challengeamount + " Soul runes");

			break;
		/*
		 * Construction
		 */
		case 7:
			player.sendMessage(Colors.orange + "Update: Your daily task is to build " + player.challengeamount
					+ " objects in your house.");
			break;
		case 8:
			player.sendMessage(Colors.orange + "Update: Your daily task is to build " + player.challengeamount
					+ " objects in your house.");
			break;
		case 9:
			player.sendMessage(Colors.orange + "Update: Your daily task is to build " + player.challengeamount
					+ " objects in your house.");
			break;
		/*
		 * Dungeoneering
		 */
		case 10:
			player.sendMessage(Colors.orange + "Update: Your daily task is to complete " + player.challengeamount
					+ " Easy dungeons");
			break;
		case 11:
			player.sendMessage(Colors.orange + "Update: Your daily task is to complete " + player.challengeamount
					+ " Medium dungeons");
			break;
		case 12:
			player.sendMessage(Colors.orange + "Update: Your daily task is to complete " + player.challengeamount
					+ " Hard dungeons");
			break;
		/*
		 * Agility
		 */
		case 13:
			player.sendMessage(Colors.orange + "Update: Your daily task is to complete " + player.challengeamount
					+ " Gnome agility runs");
			break;
		case 14:
			player.sendMessage(Colors.orange + "Update: Your daily task is to complete " + player.challengeamount
					+ " Wilderness agility runs");
			break;
		case 15:
			player.sendMessage(Colors.orange + "Update: Your daily task is to complete " + player.challengeamount
					+ " Barbarian advanced agility runs");
			break;
		/*
		 * Herblore
		 */
		case 16:
			player.sendMessage(
					Colors.orange + "Update: Your daily task is to clean " + player.challengeamount + " Grimy Ranarr");
			break;
		case 17:
			player.sendMessage(Colors.orange + "Update: Your daily task is to clean " + player.challengeamount
					+ " Grimy Lantadyme");
			break;
		case 18:
			player.sendMessage(
					Colors.orange + "Update: Your daily task is to clean " + player.challengeamount + " Grimy Torstol");
			break;
		/*
		 * Thieving
		 */
		case 19:
			player.sendMessage(
					Colors.orange + "Update: Your daily task is to steal " + player.challengeamount + " Cowhide");
			break;
		case 20:
			player.sendMessage(
					Colors.orange + "Update: Your daily task is to steal " + player.challengeamount + " Silk");
			break;
		case 21:
			player.sendMessage(
					Colors.orange + "Update: Your daily task is to steal " + player.challengeamount + " Gold rings");
			break;
		/*
		 * Crafting
		 */
		case 22:
			player.sendMessage(
					Colors.orange + "Update: Your daily task is to cut " + player.challengeamount + " uncut Ruby");
			break;
		case 23:
			player.sendMessage(Colors.orange + "Update: Your daily task is to cut " + player.challengeamount
					+ " uncut Dragonstone");
			break;
		case 24:
			player.sendMessage(
					Colors.orange + "Update: Your daily task is to cut " + player.challengeamount + " uncut Onyx");
			break;
		/*
		 * Fletching
		 */
		case 25:
			player.sendMessage(Colors.orange + "Update: Your daily task is to string " + player.challengeamount
					+ " Oak shieldbows");
			break;
		case 26:
			player.sendMessage(Colors.orange + "Update: Your daily task is to string " + player.challengeamount
					+ " Yew shieldbows");
			break;
		case 27:
			player.sendMessage(Colors.orange + "Update: Your daily task is to string " + player.challengeamount
					+ " Magic shieldbows");
			break;
		/*
		 * Slayer
		 */
		case 28:
			player.sendMessage(Colors.orange + "Update: Your daily task is to complete " + player.challengeamount
					+ " Slayer task");
			break;
		case 29:
			player.sendMessage(Colors.orange + "Update: Your daily task is to complete " + player.challengeamount
					+ " Slayer task");
			break;
		case 30:
			player.sendMessage(Colors.orange + "Update: Your daily task is to complete " + player.challengeamount
					+ " Slayer task");
			break;
		/*
		 * Hunter
		 */
		case 31:
			player.sendMessage(
					Colors.orange + "Update: Your daily task is to hunt " + player.challengeamount + " Crimson swift");
			break;
		case 32:
			player.sendMessage(Colors.orange + "Update: Your daily task is to hunt " + player.challengeamount
					+ " Red chinchompas");
			break;
		case 33:
			player.sendMessage(
					Colors.orange + "Update: Your daily task is to hunt " + player.challengeamount + " Falconry prey");
			break;
		/*
		 * Mining
		 */
		case 34:
			player.sendMessage(
					Colors.orange + "Update: Your daily task is to mine " + player.challengeamount + " Iron ore");
			break;
		case 35:
			player.sendMessage(
					Colors.orange + "Update: Your daily task is to mine " + player.challengeamount + " Adamantite ore");
			break;
		case 36:
			player.sendMessage(
					Colors.orange + "Update: Your daily task is to mine " + player.challengeamount + " Runite ore");
			break;
		/*
		 * Smithing
		 */
		case 37:
			player.sendMessage(Colors.orange + "Update: Your daily task is to smith " + player.challengeamount
					+ " Steel platebodies");
			break;
		case 38:
			player.sendMessage(Colors.orange + "Update: Your daily task is to smith " + player.challengeamount
					+ " Mithril platebodies");
			break;
		case 39:
			player.sendMessage(Colors.orange + "Update: Your daily task is to smith " + player.challengeamount
					+ " Rune platebodies");
			break;

		/*
		 * Fishing
		 */
		case 40:
			player.sendMessage(
					Colors.orange + "Update: Your daily task is to catch " + player.challengeamount + " Shrimp");
			break;
		case 41:
			player.sendMessage(
					Colors.orange + "Update: Your daily task is to catch " + player.challengeamount + " Lobster");
			break;
		case 42:
			player.sendMessage(
					Colors.orange + "Update: Your daily task is to catch " + player.challengeamount + " Shark");// great
																												// white
																												// should
																												// count
																												// too
			break;
		/*
		 * Cooking
		 */
		case 43:
			player.sendMessage(
					Colors.orange + "Update: Your daily task is to cook " + player.challengeamount + " Tuna");
			break;
		case 44:
			player.sendMessage(
					Colors.orange + "Update: Your daily task is to cook " + player.challengeamount + " Swordfish");
			break;
		case 45:
			player.sendMessage(
					Colors.orange + "Update: Your daily task is to cook " + player.challengeamount + " Shark");
			break;
		/*
		 * Firemaking
		 */
		case 46:
			player.sendMessage(
					Colors.orange + "Update: Your daily task is to light " + player.challengeamount + " Oak logs");
			break;
		case 47:
			player.sendMessage(
					Colors.orange + "Update: Your daily task is to light " + player.challengeamount + " Maple logs");
			break;
		case 48:
			player.sendMessage(
					Colors.orange + "Update: Your daily task is to light " + player.challengeamount + " Magic logs");
			break;
		/*
		 * Woodcutting
		 */
		case 49:
			player.sendMessage(
					Colors.orange + "Update: Your daily task is to chop " + player.challengeamount + " Oak logs");
			break;
		case 50:
			player.sendMessage(
					Colors.orange + "Update: Your daily task is to chop " + player.challengeamount + " Willow logs");
			break;
		case 51:
			player.sendMessage(
					Colors.orange + "Update: Your daily task is to chop " + player.challengeamount + " Magic logs");
			break;

		/*
		 * Farming
		 */
		case 52:
			player.sendMessage(
					Colors.orange + "Update: Your daily task is to harvest " + player.challengeamount + " Grimy Guam");
			break;
		case 53:
			player.sendMessage(
					Colors.orange + "Update: Your daily task is to harvest " + player.challengeamount + " Grimy Irit");
			break;
		case 54:
			player.sendMessage(Colors.orange + "Update: Your daily task is to harvest " + player.challengeamount
					+ " Grimy Torstol");
			break;
		default:
			player.sendMessage(Colors.orange + "You don't have an active daily challenge right now!");
			break;
		}

	}

	public static void UpdateChallenge(Player player) {
		if (player.challengeamount == 1) {
			player.sendMessage(Colors.orange + "Update: You've completed your daily task!");
			player.challengeamount -= 1;
			Reward(player);
			player.totalchallengescompleted++;
			return;
		}
		player.challengeamount -= 1;
		//System.out.println(player.challengeid);
		switch (player.challengeid) {
		/*
		 * Prayer
		 */
		case 1:
			player.sendMessage(
					Colors.orange + "Update: Your daily task is to bury " + player.challengeamount + " Big bones");
			break;
		case 2:
			player.sendMessage(
					Colors.orange + "Update: Your daily task is to bury " + player.challengeamount + " Dragon bones");
			break;
		case 3:
			player.sendMessage(Colors.orange + "Update: Your daily task is to bury " + player.challengeamount
					+ " Superior dragon bones");
			break;
		/*
		 * Runecrafting
		 */
		case 4:
			player.sendMessage(
					Colors.orange + "Update: Your daily task is to craft " + player.challengeamount + " Air runes");
			break;
		case 5:
			player.sendMessage(
					Colors.orange + "Update: Your daily task is to craft " + player.challengeamount + " Death runes");
			break;
		case 6:
			player.sendMessage(
					Colors.orange + "Update: Your daily task is to craft " + player.challengeamount + " Soul runes");

			break;
		/*
		 * Construction
		 */
		case 7:
			player.sendMessage(Colors.orange + "Update: Your daily task is to build " + player.challengeamount
					+ " objects in your house.");
			break;
		case 8:
			player.sendMessage(Colors.orange + "Update: Your daily task is to build " + player.challengeamount
					+ " objects in your house.");
			break;
		case 9:
			player.sendMessage(Colors.orange + "Update: Your daily task is to build " + player.challengeamount
					+ " objects in your house.");
			break;
		/*
		 * Dungeoneering
		 */
		case 10:
			player.sendMessage(Colors.orange + "Update: Your daily task is to complete " + player.challengeamount
					+ " Easy dungeons");
			break;
		case 11:
			player.sendMessage(Colors.orange + "Update: Your daily task is to complete " + player.challengeamount
					+ " Medium dungeons");
			break;
		case 12:
			player.sendMessage(Colors.orange + "Update: Your daily task is to complete " + player.challengeamount
					+ " Hard dungeons");
			break;
		/*
		 * Agility
		 */
		case 13:
			player.sendMessage(Colors.orange + "Update: Your daily task is to complete " + player.challengeamount
					+ " Gnome agility runs");
			break;
		case 14:
			player.sendMessage(Colors.orange + "Update: Your daily task is to complete " + player.challengeamount
					+ " Wilderness agility runs");
			break;
		case 15:
			player.sendMessage(Colors.orange + "Update: Your daily task is to complete " + player.challengeamount
					+ " Barbarian advanced agility runs");
			break;
		/*
		 * Herblore
		 */
		case 16:
			player.sendMessage(
					Colors.orange + "Update: Your daily task is to clean " + player.challengeamount + " Grimy Ranarr");
			break;
		case 17:
			player.sendMessage(Colors.orange + "Update: Your daily task is to clean " + player.challengeamount
					+ " Grimy Lantadyme");
			break;
		case 18:
			player.sendMessage(
					Colors.orange + "Update: Your daily task is to clean " + player.challengeamount + " Grimy Torstol");
			break;
		/*
		 * Thieving
		 */
		case 19:
			player.sendMessage(
					Colors.orange + "Update: Your daily task is to steal " + player.challengeamount + " Cowhide");
			break;
		case 20:
			player.sendMessage(
					Colors.orange + "Update: Your daily task is to steal " + player.challengeamount + " Silk");
			break;
		case 21:
			player.sendMessage(
					Colors.orange + "Update: Your daily task is to steal " + player.challengeamount + " Gold rings");
			break;
		/*
		 * Crafting
		 */
		case 22:
			player.sendMessage(
					Colors.orange + "Update: Your daily task is to cut " + player.challengeamount + " uncut Ruby");
			break;
		case 23:
			player.sendMessage(Colors.orange + "Update: Your daily task is to cut " + player.challengeamount
					+ " uncut Dragonstone");
			break;
		case 24:
			player.sendMessage(
					Colors.orange + "Update: Your daily task is to cut " + player.challengeamount + " uncut Onyx");
			break;
		/*
		 * Fletching
		 */
		case 25:
			player.sendMessage(Colors.orange + "Update: Your daily task is to string " + player.challengeamount
					+ " Oak shieldbows");
			break;
		case 26:
			player.sendMessage(Colors.orange + "Update: Your daily task is to string " + player.challengeamount
					+ " Yew shieldbows");
			break;
		case 27:
			player.sendMessage(Colors.orange + "Update: Your daily task is to string " + player.challengeamount
					+ " Magic shieldbows");
			break;
		/*
		 * Slayer
		 */
		case 28:
			player.sendMessage(Colors.orange + "Update: Your daily task is to complete " + player.challengeamount
					+ " Slayer task");
			break;
		case 29:
			player.sendMessage(Colors.orange + "Update: Your daily task is to complete " + player.challengeamount
					+ " Slayer task");
			break;
		case 30:
			player.sendMessage(Colors.orange + "Update: Your daily task is to complete " + player.challengeamount
					+ " Slayer task");
			break;
		/*
		 * Hunter
		 */
		case 31:
			player.sendMessage(
					Colors.orange + "Update: Your daily task is to hunt " + player.challengeamount + " Crimson swift");
			break;
		case 32:
			player.sendMessage(Colors.orange + "Update: Your daily task is to hunt " + player.challengeamount
					+ " Red chinchompas");
			break;
		case 33:
			player.sendMessage(
					Colors.orange + "Update: Your daily task is to hunt " + player.challengeamount + " Falconry prey");
			break;
		/*
		 * Mining
		 */
		case 34:
			player.sendMessage(
					Colors.orange + "Update: Your daily task is to mine " + player.challengeamount + " Iron ore");
			break;
		case 35:
			player.sendMessage(
					Colors.orange + "Update: Your daily task is to mine " + player.challengeamount + " Adamantite ore");
			break;
		case 36:
			player.sendMessage(
					Colors.orange + "Update: Your daily task is to mine " + player.challengeamount + " Runite ore");
			break;
		/*
		 * Smithing
		 */
		case 37:
			player.sendMessage(Colors.orange + "Update: Your daily task is to smith " + player.challengeamount
					+ " Steel platebodies");
			break;
		case 38:
			player.sendMessage(Colors.orange + "Update: Your daily task is to smith " + player.challengeamount
					+ " Mithril platebodies");
			break;
		case 39:
			player.sendMessage(Colors.orange + "Update: Your daily task is to smith " + player.challengeamount
					+ " Rune platebodies");
			break;

		/*
		 * Fishing
		 */
		case 40:
			player.sendMessage(
					Colors.orange + "Update: Your daily task is to catch " + player.challengeamount + " Shrimp");
			break;
		case 41:
			player.sendMessage(
					Colors.orange + "Update: Your daily task is to catch " + player.challengeamount + " Lobster");
			break;
		case 42:
			player.sendMessage(
					Colors.orange + "Update: Your daily task is to catch " + player.challengeamount + " Shark");// great
																												// white
																												// should
																												// count
																												// too
			break;
		/*
		 * Cooking
		 */
		case 43:
			player.sendMessage(
					Colors.orange + "Update: Your daily task is to cook " + player.challengeamount + " Tuna");
			break;
		case 44:
			player.sendMessage(
					Colors.orange + "Update: Your daily task is to cook " + player.challengeamount + " Swordfish");
			break;
		case 45:
			player.sendMessage(
					Colors.orange + "Update: Your daily task is to cook " + player.challengeamount + " Shark");
			break;
		/*
		 * Firemaking
		 */
		case 46:
			player.sendMessage(
					Colors.orange + "Update: Your daily task is to light " + player.challengeamount + " Oak logs");
			break;
		case 47:
			player.sendMessage(
					Colors.orange + "Update: Your daily task is to light " + player.challengeamount + " Maple logs");
			break;
		case 48:
			player.sendMessage(
					Colors.orange + "Update: Your daily task is to light " + player.challengeamount + " Magic logs");
			break;
		/*
		 * Woodcutting
		 */
		case 49:
			player.sendMessage(
					Colors.orange + "Update: Your daily task is to chop " + player.challengeamount + " Oak logs");
			break;
		case 50:
			player.sendMessage(
					Colors.orange + "Update: Your daily task is to chop " + player.challengeamount + " Willow logs");
			break;
		case 51:
			player.sendMessage(
					Colors.orange + "Update: Your daily task is to chop " + player.challengeamount + " Magic logs");
			break;

		/*
		 * Farming
		 */
		case 52:
			player.sendMessage(
					Colors.orange + "Update: Your daily task is to harvest " + player.challengeamount + " Grimy Guam");
			break;
		case 53:
			player.sendMessage(
					Colors.orange + "Update: Your daily task is to harvest " + player.challengeamount + " Grimy Irit");
			break;
		case 54:
			player.sendMessage(Colors.orange + "Update: Your daily task is to harvest " + player.challengeamount
					+ " Grimy Torstol");
			break;
		}

	}

	
	public static void UpdateRCChallenge(Player player, int amount) {
		player.challengeamount -= amount;
		if (player.challengeamount <= 1) {
			player.sendMessage(Colors.orange + "Update: You've completed your daily task!");
			player.challengeamount = 0;
			Reward(player);
			player.totalchallengescompleted++;
			return;
		}
		//System.out.println(player.challengeid);
		switch (player.challengeid) {
		/*
		 * Runecrafting
		 */
		case 4:
			player.sendMessage(
					Colors.orange + "Update: Your daily task is to craft " + player.challengeamount + " Air runes");
			break;
		case 5:
			player.sendMessage(
					Colors.orange + "Update: Your daily task is to craft " + player.challengeamount + " Death runes");
			break;
		case 6:
			player.sendMessage(
					Colors.orange + "Update: Your daily task is to craft " + player.challengeamount + " Soul runes");

			break;
		}

	}

	public static void GrabChallenge(Player player) {
		int pick;
		pick = Utils.random(1, 54);
		switch (pick) {
		/*
		 * Prayer
		 */
		case 1:
			player.sendMessage(Colors.orange + "Your daily task is to bury 40 Big bones");
			player.challengeid = pick;
			player.challengeamount = 40;
			break;
		case 2:
			player.sendMessage(Colors.orange + "Your daily task is to bury 40 Dragon bones");
			player.challengeid = pick;
			player.challengeamount = 40;
			break;
		case 3:
			player.sendMessage(Colors.orange + "Your daily task is to bury 40 Superior dragon bones");
			player.challengeid = pick;
			player.challengeamount = 40;
			break;
		/*
		 * Runecrafting
		 */
		case 4:
			player.sendMessage(Colors.orange + "Your daily task is to craft 200 Air runes");
			player.challengeid = pick;
			player.challengeamount = 200;
			break;
		case 5:
			player.sendMessage(Colors.orange + "Your daily task is to craft 200 Death runes");
			player.challengeid = pick;
			player.challengeamount = 200;
			break;
		case 6:
			player.sendMessage(Colors.orange + "Your daily task is to craft 200 Soul runes");
			player.challengeid = pick;
			player.challengeamount = 200;
			break;
		/*
		 * Construction
		 */
		case 7:
			player.sendMessage(Colors.orange + "Your daily task is to make 20 objects inside your house");
			player.challengeid = pick;
			player.challengeamount = 20;
			break;
		case 8:
			player.sendMessage(Colors.orange + "Your daily task is to make 30 objects inside your house");
			player.challengeid = pick;
			player.challengeamount = 20;
			break;
		case 9:
			player.sendMessage(Colors.orange + "Your daily task is to make 40 objects inside your house");
			player.challengeid = pick;
			player.challengeamount = 20;
			break;
		/*
		 * Dungeoneering
		 */
		case 10:// up to here
			player.sendMessage(Colors.orange + "Your daily task is to complete 2 Easy dungeons");
			player.challengeid = pick;
			player.challengeamount = 2;
			break;
		case 11:
			player.sendMessage(Colors.orange + "Your daily task is to complete 2 Medium dungeons");
			player.challengeid = pick;
			player.challengeamount = 2;
			break;
		case 12:
			player.sendMessage(Colors.orange + "Your daily task is to complete 2 Hard dungeons");
			player.challengeid = pick;
			player.challengeamount = 2;
			break;
		/*
		 * Agility
		 */
		case 13:
			player.sendMessage(Colors.orange + "Your daily task is to complete 10 Gnome agility runs");
			player.challengeid = pick;
			player.challengeamount = 10;
			break;
		case 14:
			player.sendMessage(Colors.orange + "Your daily task is to complete 10 Wilderness agility runs");
			player.challengeid = pick;
			player.challengeamount = 10;
			break;
		case 15:
			player.sendMessage(Colors.orange + "Your daily task is to complete 10 Barbarian advanced agility runs");
			player.challengeid = pick;
			player.challengeamount = 10;
			break;
		/*
		 * Herblore
		 */
		case 16:
			player.sendMessage(Colors.orange + "Your daily task is to clean 120 Grimy Ranarr");
			player.challengeid = pick;
			player.challengeamount = 120;
			break;
		case 17:
			player.sendMessage(Colors.orange + "Your daily task is to clean 120 Grimy Lantadyme");
			player.challengeid = pick;
			player.challengeamount = 120;
			break;
		case 18:
			player.sendMessage(Colors.orange + "Your daily task is to clean 120 Grimy Torstol");
			player.challengeid = pick;
			player.challengeamount = 120;
			break;
		/*
		 * Thieving
		 */
		case 19:
			player.sendMessage(Colors.orange + "Your daily task is to steal 100 Cowhide");
			player.challengeid = pick;
			player.challengeamount = 100;
			break;
		case 20:
			player.sendMessage(Colors.orange + "Your daily task is to steal 100 Silk");
			player.challengeid = pick;
			player.challengeamount = 100;
			break;
		case 21:
			player.sendMessage(Colors.orange + "Your daily task is to steal 100 Gold rings (double doesn't count)");
			player.challengeid = pick;
			player.challengeamount = 100;
			break;
		/*
		 * Crafting
		 */
		case 22:
			player.sendMessage(Colors.orange + "Your daily task is to cut 120 uncut Ruby");
			player.challengeid = pick;
			player.challengeamount = 120;
			break;
		case 23:
			player.sendMessage(Colors.orange + "Your daily task is to cut 120 uncut Dragonstone");
			player.challengeid = pick;
			player.challengeamount = 120;
			break;
		case 24:
			player.sendMessage(Colors.orange + "Your daily task is to cut 2 uncut Onyx");
			player.challengeid = pick;
			player.challengeamount = 2;
			break;
		/*
		 * Fletching
		 */
		case 25:
			player.sendMessage(Colors.orange + "Your daily task is to string 120 Oak shieldbows");
			player.challengeid = pick;
			player.challengeamount = 120;
			break;
		case 26:
			player.sendMessage(Colors.orange + "Your daily task is to string 120 Yew shieldbows");
			player.challengeid = pick;
			player.challengeamount = 120;
			break;
		case 27:
			player.sendMessage(Colors.orange + "Your daily task is to string 120 Magic shieldbows");
			player.challengeid = pick;
			player.challengeamount = 120;
			break;
		/*
		 * Slayer
		 */
		case 28:
			player.sendMessage(Colors.orange + "Your daily task is to complete a Slayer task");
			player.challengeid = pick;
			player.challengeamount = 1;
			break;
		case 29:
			player.sendMessage(Colors.orange + "Your daily task is to complete 2 Slayer task");
			player.challengeid = pick;
			player.challengeamount = 2;
			break;
		case 30:
			player.sendMessage(Colors.orange + "Your daily task is to complete 3 Slayer task");
			player.challengeid = pick;
			player.challengeamount = 3;
			break;
		/*
		 * Hunter
		 */
		case 31:
			player.sendMessage(Colors.orange + "Your daily task is to hunt 25 Crimson swift");
			player.challengeid = pick;
			player.challengeamount = 25;
			break;
		case 32:
			player.sendMessage(Colors.orange + "Your daily task is to hunt 25 Red chinchompas");
			player.challengeid = pick;
			player.challengeamount = 25;
			break;
		case 33:
			player.sendMessage(Colors.orange + "Your daily task is to hunt 25 Falconry prey");
			player.challengeid = pick;
			player.challengeamount = 25;
			break;
		/*
		 * Mining
		 */
		case 34:
			player.sendMessage(Colors.orange + "Your daily task is to mine 80 Iron ore");
			player.challengeid = pick;
			player.challengeamount = 80;
			break;
		case 35:
			player.sendMessage(Colors.orange + "Your daily task is to mine 80 Adamantite ore");
			player.challengeid = pick;
			player.challengeamount = 80;
			break;
		case 36:
			player.sendMessage(Colors.orange + "Your daily task is to mine 80 Runite ore");
			player.challengeid = pick;
			player.challengeamount = 80;
			break;
		/*
		 * Smithing
		 */
		case 37:
			player.sendMessage(Colors.orange + "Your daily task is to smelt 20 Steel platebodies");
			player.challengeid = pick;
			player.challengeamount = 20;
			break;
		case 38:
			player.sendMessage(Colors.orange + "Your daily task is to smelt 20 Mithril platebodies");
			player.challengeid = pick;
			player.challengeamount = 20;
			break;
		case 39:
			player.sendMessage(Colors.orange + "Your daily task is to smelt 20 Rune platebodies");
			player.challengeid = pick;
			player.challengeamount = 20;
			break;

		/*
		 * Fishing
		 */
		case 40:
			player.sendMessage(Colors.orange + "Your daily task is to catch 60 Shrimp");
			player.challengeid = pick;
			player.challengeamount = 60;
			break;
		case 41:
			player.sendMessage(Colors.orange + "Your daily task is to catch 60 Lobster");
			player.challengeid = pick;
			player.challengeamount = 60;
			break;
		case 42:
			player.sendMessage(Colors.orange + "Your daily task is to catch 60 Shark");// great white should count too
			player.challengeid = pick;
			player.challengeamount = 60;
			break;
		/*
		 * Cooking
		 */
		case 43:
			player.sendMessage(Colors.orange + "Your daily task is to cook 60 Tuna");
			player.challengeid = pick;
			player.challengeamount = 60;
			break;
		case 44:
			player.sendMessage(Colors.orange + "Your daily task is to cook 60 Swordfish");
			player.challengeid = pick;
			player.challengeamount = 60;
			break;
		case 45:
			player.sendMessage(Colors.orange + "Your daily task is to cook 60 Shark");
			player.challengeid = pick;
			player.challengeamount = 60;
			break;
		/*
		 * Firemaking
		 */
		case 46:
			player.sendMessage(Colors.orange + "Your daily task is to light 120 Oak logs");
			player.challengeid = pick;
			player.challengeamount = 120;
			break;
		case 47:
			player.sendMessage(Colors.orange + "Your daily task is to light 120 Maple logs");
			player.challengeid = pick;
			player.challengeamount = 120;
			break;
		case 48:
			player.sendMessage(Colors.orange + "Your daily task is to light 120 Magic logs");
			player.challengeid = pick;
			player.challengeamount = 120;
			break;
		/*
		 * Woodcutting
		 */
		case 49:
			player.sendMessage(Colors.orange + "Your daily task is to chop 120 Oak logs");
			player.challengeid = pick;
			player.challengeamount = 120;
			break;
		case 50:
			player.sendMessage(Colors.orange + "Your daily task is to chop 120 Willow logs");
			player.challengeid = pick;
			player.challengeamount = 120;
			break;
		case 51:
			player.sendMessage(Colors.orange + "Your daily task is to chop 120 Magic logs");
			player.challengeid = pick;
			player.challengeamount = 120;
			break;

		/*
		 * Farming
		 */
		case 52:
			player.sendMessage(Colors.orange + "Your daily task is to Harvest 10 Grimy Guam");
			player.challengeid = pick;
			player.challengeamount = 10;
			break;
		case 53:
			player.sendMessage(Colors.orange + "Your daily task is to Harvest 10 Grimy Irit");
			player.challengeid = pick;
			player.challengeamount = 10;
			break;
		case 54:
			player.sendMessage(Colors.orange + "Your daily task is to Harvest 10 Grimy Torstol");
			player.challengeid = pick;
			player.challengeamount = 10;
			break;
		}

	}
}
