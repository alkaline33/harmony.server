package com.rs.game.player.interfaces;

import com.discord.Discord;
import com.rs.Settings;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.player.Player;
import com.rs.utils.Colors;
import com.rs.utils.ShopsHandler;
import com.rs.utils.Utils;

/**
 * 
 * @author Mr_Joopz
 *
 */

public class StaffDeskInterface {

	private static int INTERID = 3016;
	private static int STAFFTOKEN = 29224;

	public static void OpenInterface(Player player) {
		player.getInterfaceManager().sendInterface(INTERID);
		player.getPackets().sendIComponentText(INTERID, 19, "Npc drop event tokens - 30 mins");
		player.getPackets().sendIComponentText(INTERID, 18, "Hits are doubled - 30 mins");
		player.getPackets().sendIComponentText(INTERID, 20, "Give all 2 Squeal of Fortune spins");
		player.getPackets().sendIComponentText(INTERID, 21, "Give all 1 Deathtouched dart");
		player.getPackets().sendIComponentText(INTERID, 22, "Give all 1 Elite crystal key");
		player.getPackets().sendIComponentText(INTERID, 23, "Give all 1 Mystery chest");
		player.getPackets().sendIComponentText(INTERID, 24, "10% drop rate boost (30 mins)");
		player.getPackets().sendIComponentText(INTERID, 25, "Activate 1 hour double experience");
		player.getPackets().sendIComponentText(INTERID, 26, "Double raids caskets - 60 mins");
		player.getPackets().sendIComponentText(INTERID, 27, "No damage in PvM - 30 mins");
		player.getPackets().sendIComponentText(INTERID, 28, "Double skilling resources - 30 mins");
		player.getPackets().sendIComponentText(INTERID, 29, "Increase boss pet chance - 60 mins");
		player.getPackets().sendIComponentText(INTERID, 30, "Spawn Party demon");
		player.getPackets().sendIComponentText(INTERID, 31, "Restock Shops");
	}

	public static void OpenInterfaceEconomic(Player player) {
		long gpeco = 0;
		long richest = 0;
		String name = "";
		for (Player p : World.getPlayers()) {
			if (p.getUsername().equalsIgnoreCase("jack")) {
				continue;
			}
			gpeco += p.calculateNetworth();
			if (p.calculateNetworth() > richest) {
				richest = p.calculateNetworth();
				name = p.getDisplayName();
			}
		}
		player.getPackets().sendHideIComponent(INTERID, 32, true);
		player.getPackets().sendHideIComponent(INTERID, 33, false);
		player.getPackets().sendIComponentText(INTERID, 34, "" + Colors.green + "GP SYNC: " + Utils.formatGP(Settings.GpSyncAmount) + "");
		player.getPackets().sendIComponentText(INTERID, 35, "" + Colors.green + "XP: " + Utils.formatGP(Settings.serverxp) + "");
		player.getPackets().sendIComponentText(INTERID, 36, "" + Colors.green + "KILLS: " + Settings.monsterskilled + "");
		player.getPackets().sendIComponentText(INTERID, 40, "Online Player Worth: " + Colors.green + "" + Utils.formatGP(gpeco) + "");
		player.getPackets().sendIComponentText(INTERID, 41, "Richest Player Online: " + name + " is worth: " + Colors.green + "" + Utils.formatGP(richest) + "");
	}

	public static void HandleButtons(Player player, int componentId) {
		switch (componentId) {
		/**
		 * Non specific buttons
		 */
		case 1:
			player.getInterfaceManager().closeInterfaceCustom();
			break;
		case 42:
			OpenInterfaceEconomic(player);
			break;
		/**
		 * Page 1
		 */
		case 4:
			if (!player.getInventory().contains(STAFFTOKEN)) {
				player.sendMessage(Colors.red + "Please ask Connor for a token to perform this action.");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			/**
			 * Npc drop event tokens
			 */
			Settings.eventdropeventtokens = 15;
			World.sendWorldMessage("<col=ff0000>[News] Npc's are dropping Event tokens for 30 minutes!", false);
			player.getInterfaceManager().closeInterfaceCustom();
				Discord.sendEventsMessage("@here Npc's are dropping Event tokens for 30 minutes!");
			player.getInventory().deleteItem(STAFFTOKEN, 1);
			break;
		case 5:
			if (!player.getInventory().contains(STAFFTOKEN)) {
				player.sendMessage(Colors.red + "Please ask Connor for a token to perform this action.");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			/**
			 * Double hits
			 */
			Settings.eventdoublehits = 15;
			World.sendWorldMessage("<col=ff0000>[News] All player hits against monsters are doubled for 30 minutes!", false);
				Discord.sendEventsMessage("@here All player hits against monsters are doubled for 30 minutes!");
			player.getInterfaceManager().closeInterfaceCustom();
			player.getInventory().deleteItem(STAFFTOKEN, 1);
			break;
		case 6:
			if (!player.getInventory().contains(STAFFTOKEN)) {
				player.sendMessage(Colors.red + "Please ask Connor for a token to perform this action.");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			for (Player p : World.getPlayers()) {
				p.spins += 2;
				p.sendMessage("<col=00ff00>" + player.getDisplayName() + " has given you two free spins!");
			}
			player.getInterfaceManager().closeInterfaceCustom();
			player.getInventory().deleteItem(STAFFTOKEN, 1);
			break;
		case 7:
			if (!player.getInventory().contains(STAFFTOKEN)) {
				player.sendMessage(Colors.red + "Please ask Connor for a token to perform this action.");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			for (Player p : World.getPlayers()) {
				p.getBank().addItem(25202, 1, true);
				p.sendMessage("<col=00ff00>" + player.getDisplayName() + " has added a Deathtouched dart to your bank!");
			}
			player.getInventory().deleteItem(STAFFTOKEN, 1);
			player.getInterfaceManager().closeInterfaceCustom();
			break;
		case 8:
			if (!player.getInventory().contains(STAFFTOKEN)) {
				player.sendMessage(Colors.red + "Please ask Connor for a token to perform this action.");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			for (Player p : World.getPlayers()) {
				p.getBank().addItem(29425, 1, true);
				p.sendMessage("<col=00ff00>" + player.getDisplayName() + " has added an Elite crystal key to your bank!");
			}
			player.getInventory().deleteItem(STAFFTOKEN, 1);
			player.getInterfaceManager().closeInterfaceCustom();
			break;
		case 9:
			if (!player.getInventory().contains(STAFFTOKEN)) {
				player.sendMessage(Colors.red + "Please ask Connor for a token to perform this action.");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			for (Player p : World.getPlayers()) {
				p.getBank().addItem(29426, 1, true);
				p.sendMessage("<col=00ff00>" + player.getDisplayName() + " has added a Mystery chest to your bank!");
			}
			player.getInventory().deleteItem(STAFFTOKEN, 1);
			player.getInterfaceManager().closeInterfaceCustom();
			break;
		case 10:
			if (!player.getInventory().contains(STAFFTOKEN)) {
				player.sendMessage(Colors.red + "Please ask Connor for a token to perform this action.");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			World.sendWorldMessage("<col=ff0000>[News] 30 minutes of 10% drop rate buff will activate shortly!", false);
			Settings.amountdonated = 25;
				Discord.sendEventsMessage("@here Drop rate has been increased for 30 minutes!");
			player.getInventory().deleteItem(STAFFTOKEN, 1);
			player.getInterfaceManager().closeInterfaceCustom();
			break;
		case 11:
			if (!player.getInventory().contains(STAFFTOKEN)) {
				player.sendMessage(Colors.red + "Please ask Connor for a token to perform this action.");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			Settings.DONATED_TO_WELL = 0;
			Settings.WELLDOUBLE = true;
			Settings.WELLTIMER = 120;
			World.runWellTimer();
			World.sendIconWorldMessage("<col=00ff00>Double xp is now active for 1 hour!", false);
			Discord.sendEventsMessage("@here Double experience is active for 1 hour!");
			player.getInventory().deleteItem(STAFFTOKEN, 1);
			player.getInterfaceManager().closeInterfaceCustom();
			break;
		case 12:
			if (!player.getInventory().contains(STAFFTOKEN)) {
				player.sendMessage(Colors.red + "Please ask Connor for a token to perform this action.");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			/**
			 * Npc drop event tokens
			 */
			Settings.eventdoublecaskets = 30;
			World.sendWorldMessage("<col=ff0000>[News] All OSRS raids are rewarding double caskets for 60 minutes & unlimited party cap!", false);
			player.getInterfaceManager().closeInterfaceCustom();
			Discord.sendEventsMessage("@here All OSRS raids are rewarding double caskets for 60 minutes & unlimited party cap!");
			player.getInventory().deleteItem(STAFFTOKEN, 1);
			break;
		case 13:
			if (!player.getInventory().contains(STAFFTOKEN)) {
				player.sendMessage(Colors.red + "Please ask Connor for a token to perform this action.");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			/**
			 * Spotlight double drops
			 */
			Settings.eventspotlightdouble = 15;
			World.sendWorldMessage("<col=ff0000>[News] All players are protected against PvM damage for 30 minutes!", false);
			player.getInterfaceManager().closeInterfaceCustom();
			Discord.sendEventsMessage("@here All players are protected against PvM damage for 30 minutes!");
			player.getInventory().deleteItem(STAFFTOKEN, 1);
			break;
		case 14:
			if (!player.getInventory().contains(STAFFTOKEN)) {
				player.sendMessage(Colors.red + "Please ask Connor for a token to perform this action.");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			/**
			 * Spotlight double drops
			 */
			Settings.eventdoubleskillingresources = 15;
			World.sendWorldMessage("<col=ff0000>[News] All gathering skills will provide double resources for 30 minutes!", false);
			player.getInterfaceManager().closeInterfaceCustom();
			Discord.sendEventsMessage("@here All gathering skills will provide double resources for 30 minutes!");
			player.getInventory().deleteItem(STAFFTOKEN, 1);
			break;
		case 15:
			if (!player.getInventory().contains(STAFFTOKEN)) {
				player.sendMessage(Colors.red + "Please ask Connor for a token to perform this action.");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			/**
			 * Spotlight double drops
			 */
			Settings.eventbosspetchance = 30;
			World.sendWorldMessage("<col=ff0000>[News] All boss pets are twice as likely to drop for 60 minutes!", false);
			player.getInterfaceManager().closeInterfaceCustom();
			Discord.sendEventsMessage("@here All boss pets are twice as likely to drop for 60 minutes!");
			player.getInventory().deleteItem(STAFFTOKEN, 1);
			break;
		case 16:
			if (player.getRights() < 2) {
				player.sendMessage(Colors.red + "This is for Administrator use only!");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			WorldTile worldTile1 = new WorldTile(3239, 9867, 0); // spawns
			World.spawnNPC(15581, worldTile1, -1, true, true);
			Settings.canteletopdemon = true;
			World.sendWorldMessage("<col=660033> The Party Demon has arrived!</col>", false);
			Discord.sendEventsMessage("@here Party demon has spawned! Come join in!");
			player.getInterfaceManager().closeInterfaceCustom();
			break;
		case 17:
			ShopsHandler.loadUnpackedShops();
			World.sendWorldMessage("<col=660033>"+player.getDisplayName()+" has just restocked all shops!</col>", false);
			player.getInterfaceManager().closeInterfaceCustom();
			break;
		}

	}
}