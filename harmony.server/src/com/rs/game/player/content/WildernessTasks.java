package com.rs.game.player.content;

import com.rs.game.player.Player;
import com.rs.utils.Colors;
import com.rs.utils.Utils;

public class WildernessTasks {

	/**
	 * 
	 * @Author Connor
	 */

	private static void Reward(Player player) {
		player.wildernesstaskcompleted++;
		//player.dailywildytasks++;
		/*if (player.dailywildytasks == 3) {
			player.sendMessage(Colors.lightGray + "<img=6>You have completed the Daily Money Maker: Complete 3 Wilderness Tasks! Your rewards have been placed in your bank.");
			player.getBank().addItem(5022, 5, true);
			player.getBank().addItem(29369, 2, true);
		}*/
		if (player.getInventory().hasFreeSlots()) {
			player.getInventory().addItem(29369, 1);
		} else {
			player.getBank().addItem(29369, 1, true);
		}
		player.challengeid = 0;
		player.sendMessage(Colors.orange + "You've completed your wilderness task! You have now completed " + player.wildernesstaskcompleted + " wilderness tasks.");
		return;
	}

	public static void CurrentChallenge(Player player) {
		switch (player.challengeid) {
		/*
		 * Mining
		 */
		case 1:
			player.sendMessage(Colors.orange + "Update: Your wilderness task is to mine " + player.challengeamount + " Coal");
			break;
		case 2:
			player.sendMessage(Colors.orange + "Update: Your wilderness task is to mine " + player.challengeamount + " Mithril");
			break;
		case 3:
			player.sendMessage(Colors.orange + "Update: Your wilderness task is to mine " + player.challengeamount + " Runite");
			break;
		/*
		 * Woodcutting
		 */
		case 4:
			player.sendMessage(Colors.orange + "Update: Your wilderness task is to chop " + player.challengeamount + " Regular logs");
			break;
		case 5:
			player.sendMessage(Colors.orange + "Update: Your wilderness task is to chop " + player.challengeamount + " Maple logs");
			break;
		case 6:
			player.sendMessage(Colors.orange + "Update: Your wilderness task is to chop " + player.challengeamount + " Magic logs");

			break;
		/*
		 * Fishing
		 */
		case 7:
			player.sendMessage(Colors.orange + "Update: Your wilderness task is to catch " + player.challengeamount + " Shrimp.");
			break;
		case 8:
			player.sendMessage(Colors.orange + "Update: Your wilderness task is to catch " + player.challengeamount + " Anchovies.");
			break;
		case 9:
			player.sendMessage(Colors.orange + "Update: Your wilderness task is to catch " + player.challengeamount + " Rocktails.");
			break;
		/*
		 * Boss
		 */
		case 10:
			player.sendMessage(Colors.orange + "Update: Your wilderness task is to kill " + player.challengeamount + " Callisto");
			break;
		case 11:
			player.sendMessage(Colors.orange + "Update: Your wilderness task is to kill " + player.challengeamount + " WildyWyrm");
			break;
		case 12:
			player.sendMessage(Colors.orange + "Update: Your wilderness task is to kill " + player.challengeamount + " Aquatic Wyrm");
			break;
		case 13:
			player.sendMessage(Colors.orange + "Update: Your wilderness task is to kill " + player.challengeamount + " Sunfreet");
			break;
		case 14:
			player.sendMessage(Colors.orange + "Update: Your wilderness task is to kill " + player.challengeamount + " Sliske");
			break;
		case 15:
			player.sendMessage(Colors.orange + "Update: Your wilderness task is to kill " + player.challengeamount + " Sirenic the Spider");
			break;
		/*
		 * Monster
		 */
		case 16:
			player.sendMessage(Colors.orange + "Update: Your wilderness task is to kill " + player.challengeamount + " Lesser demon");
			break;
		case 17:
			player.sendMessage(Colors.orange + "Update: Your wilderness task is to kill " + player.challengeamount + " Hellhound");
			break;
		case 18:
			player.sendMessage(Colors.orange + "Update: Your wilderness task is to kill " + player.challengeamount + " Ankou");
			break;

		/**
		 * More bosses
		 */
		case 19:
			player.sendMessage(Colors.orange + "Update: Your wilderness task is to kill " + player.challengeamount + " Venenatis");
			break;

		case 20:
			player.sendMessage(Colors.orange + "Update: Your wilderness task is to kill " + player.challengeamount + " Vet'ion");
			break;
		}

	}

	public static void UpdateChallenge(Player player) {
		if (player.challengeamount == 1) {
			player.sendMessage(Colors.orange + "Update: You've completed your Wilderness task!");
			//SeasonEvent.HandleActivity(player, "Wilderness tasks", 0);
			player.challengeamount -= 1;
			Reward(player);
			return;
		}
		player.challengeamount -= 1;
		// System.out.println(player.challengeid);
		switch (player.challengeid) {
		/*
		 * Prayer
		 */
		case 1:
			player.getPackets().sendGameMessage(Colors.orange + "Update: Your wilderness task is to mine " + player.challengeamount + " Coal", true);
			break;
		case 2:
			player.getPackets().sendGameMessage(Colors.orange + "Update: Your wilderness task is to mine " + player.challengeamount + " Mithril", true);
			break;
		case 3:
			player.getPackets().sendGameMessage(Colors.orange + "Update: Your wilderness task is to mine " + player.challengeamount + " Runite", true);
			break;
		/*
		 * Runecrafting
		 */
		case 4:
			player.getPackets().sendGameMessage(Colors.orange + "Update: Your wilderness task is to chop " + player.challengeamount + " Regular logs", true);
			break;
		case 5:
			player.getPackets().sendGameMessage(Colors.orange + "Update: Your wilderness task is to chop " + player.challengeamount + " Maple logs", true);
			break;
		case 6:
			player.getPackets().sendGameMessage(Colors.orange + "Update: Your wilderness task is to chop " + player.challengeamount + " Magic logs", true);
			break;
		/*
		 * Construction
		 */
		case 7:
			player.getPackets().sendGameMessage(Colors.orange + "Update: Your wilderness task is to catch " + player.challengeamount + " Shrimp.", true);
			break;
		case 8:
			player.getPackets().sendGameMessage(Colors.orange + "Update: Your wilderness task is to catch " + player.challengeamount + " Anchovies.", true);
			break;
		case 9:
			player.getPackets().sendGameMessage(Colors.orange + "Update: Your wilderness task is to catch " + player.challengeamount + " Rocktails.", true);
			break;
		/*
		 * Dungeoneering
		 */
		case 10:
			player.getPackets().sendGameMessage(Colors.orange + "Update: Your wilderness task is to kill " + player.challengeamount + " Callisto", true);
			break;
		case 11:
			player.getPackets().sendGameMessage(Colors.orange + "Update: Your wilderness task is to kill " + player.challengeamount + " WildyWyrm", true);
			break;
		case 12:
			player.getPackets().sendGameMessage(Colors.orange + "Update: Your wilderness task is to kill " + player.challengeamount + " Aquatic Wyrm", true);
			break;
		case 13:
			player.getPackets().sendGameMessage(Colors.orange + "Update: Your wilderness task is to kill " + player.challengeamount + " Sunfreet", true);
			break;
		case 14:
			player.getPackets().sendGameMessage(Colors.orange + "Update: Your wilderness task is to kill " + player.challengeamount + " Sliske", true);
			break;
		case 15:
			player.getPackets().sendGameMessage(Colors.orange + "Update: Your wilderness task is to kill " + player.challengeamount + " Sirenic the Spider", true);
			break;
		/*
		 * Herblore
		 */
		case 16:
			player.getPackets().sendGameMessage(Colors.orange + "Update: Your wilderness task is to kill " + player.challengeamount + " Lesser demon", true);
			break;
		case 17:
			player.getPackets().sendGameMessage(Colors.orange + "Update: Your wilderness task is to kill " + player.challengeamount + " Hellhound", true);
			break;
		case 18:
			player.getPackets().sendGameMessage(Colors.orange + "Update: Your wilderness task is to kill " + player.challengeamount + " Ankou", true);
			break;
		/*
		 * Thieving
		 */
		case 19:
			player.getPackets().sendGameMessage(Colors.orange + "Update: Your wilderness task is to open " + player.challengeamount + " Clue scrolls", true);
			break;

		/**
		 * More bosses
		 */

		case 20:
			player.getPackets().sendGameMessage(Colors.orange + "Update: Your wilderness task is to kill " + player.challengeamount + " Venenatis", true);
			break;

		case 21:
			player.getPackets().sendGameMessage(Colors.orange + "Update: Your wilderness task is to kill " + player.challengeamount + " Vet'ion", true);
			break;
		}

	}
	public static void GrabChallenge(Player player) {
		int pick;
		pick = Utils.random(1, 19);
		switch (pick) {
		/*
		 * Mining
		 */
		case 1:
			player.sendMessage(Colors.orange + "Your wilderness task is to mine 100 Coal");
			player.challengeid = pick;
			player.challengeamount = 100;
			break;
		case 2:
			player.sendMessage(Colors.orange + "Your wilderness task is to mine 60 Mithril");
			player.challengeid = pick;
			player.challengeamount = 60;
			break;
		case 3:
			player.sendMessage(Colors.orange + "Your wilderness task is to mine 40 Runite");
			player.challengeid = pick;
			player.challengeamount = 40;
			break;
		/*
		 * Woodcutting
		 */
		case 4:
			player.sendMessage(Colors.orange + "Your wilderness task is to chop 100 Regular logs");
			player.challengeid = pick;
			player.challengeamount = 100;
			break;
		case 5:
			player.sendMessage(Colors.orange + "Your wilderness task is to chop 60 Maple logs");
			player.challengeid = pick;
			player.challengeamount = 60;
			break;
		case 6:
			player.sendMessage(Colors.orange + "Your wilderness task is to chop 40 Magic logs");
			player.challengeid = pick;
			player.challengeamount = 40;
			break;
		/*
		 * Fishing
		 */
		case 7:
			player.sendMessage(Colors.orange + "Your wilderness task is to catch 100 Shrimp");
			player.challengeid = pick;
			player.challengeamount = 100;
			break;
		case 8:
			player.sendMessage(Colors.orange + "Your wilderness task is to catch 100 Anchovies");
			player.challengeid = pick;
			player.challengeamount = 100;
			break;
		case 9:
			player.sendMessage(Colors.orange + "Your wilderness task is to catch 70 Rocktails");
			player.challengeid = pick;
			player.challengeamount = 70;
			break;
		/*
		 * Boss
		 */
		case 10:// up to here
			player.sendMessage(Colors.orange + "Your wilderness task is to kill 10 Callisto");
			player.challengeid = pick;
			player.challengeamount = 10;
			break;
		case 11:
			player.sendMessage(Colors.orange + "Your wilderness task is to kill 8 Wildywyrm");
			player.challengeid = pick;
			player.challengeamount = 8;
			break;
		case 12:
			player.sendMessage(Colors.orange + "Your wilderness task is to kill 6 Aquatic Wyrm");
			player.challengeid = pick;
			player.challengeamount = 6;
			break;
		case 13:
			player.sendMessage(Colors.orange + "Your wilderness task is to kill 6 Sunfreet");
			player.challengeid = pick;
			player.challengeamount = 6;
			break;
		case 14:
			player.sendMessage(Colors.orange + "Your wilderness task is to kill 5 Sliske");
			player.challengeid = pick;
			player.challengeamount = 5;
			break;
		case 15:
			player.sendMessage(Colors.orange + "Your wilderness task is to kill 7 Sirenic the Spider");
			player.challengeid = pick;
			player.challengeamount = 7;
			break;
		/*
		 * Monster
		 */
		case 16:
			player.sendMessage(Colors.orange + "Your wilderness task is to kill 30 Lesser demon");
			player.challengeid = pick;
			player.challengeamount = 30;
			break;
		case 17:
			player.sendMessage(Colors.orange + "Your wilderness task is to kill 30 Hellhound");
			player.challengeid = pick;
			player.challengeamount = 30;
			break;
		case 18:
			player.sendMessage(Colors.orange + "Your wilderness task is to kill 30 Ankou");
			player.challengeid = pick;
			player.challengeamount = 30;
			break;
		/*
		 * misc
		 */
		case 19:
			player.sendMessage(Colors.orange + "Your wilderness task is to open 2 clue scrolls");
			player.challengeid = pick;
			player.challengeamount = 2;
			break;

		/**
		 * more bosses
		 */

		case 20:
			player.sendMessage(Colors.orange + "Your wilderness task is to kill 10 Venenatis");
			player.challengeid = pick;
			player.challengeamount = 10;
			break;

		case 21:
			player.sendMessage(Colors.orange + "Your wilderness task is to kill 10 Vet'ion");
			player.challengeid = pick;
			player.challengeamount = 10;
			break;
		}

	}
}
