package com.rs.game.player.interfaces;

import com.rs.Settings;
import com.rs.game.World;
import com.rs.game.player.Player;
import com.rs.game.player.content.BossSpotlight;
import com.rs.game.player.content.EventSpotlight;
import com.rs.game.player.content.event.season.SeasonEvent;
import com.rs.utils.Colors;
import com.rs.utils.Utils;

/**
 * 
 * @author Mr_Joopz
 *
 */

public class RottenPotatoInterface {

	private static int INTERID = 3011;

	public static void OpenInterface(Player player) {
		player.getInterfaceManager().sendInterface(INTERID);
		player.getPackets().sendIComponentText(INTERID, 19, "2 spins to world");
		player.getPackets().sendIComponentText(INTERID, 18, "10% drop rate boost (30 mins)");
		player.getPackets().sendIComponentText(INTERID, 20, "World Emote");
		player.getPackets().sendIComponentText(INTERID, 21, "Wipe my bank");
		player.getPackets().sendIComponentText(INTERID, 22, "Re-roll season event");
		player.getPackets().sendIComponentText(INTERID, 23, "Re-roll boss spotlight");
		player.getPackets().sendIComponentText(INTERID, 24, "Re-roll event spotlight");
		player.getPackets().sendIComponentText(INTERID, 25, "Deathtouched Dart to world");
		player.getPackets().sendIComponentText(INTERID, 26, "Elite key to world");
		player.getPackets().sendIComponentText(INTERID, 27, "Clue Scroll to world");
		player.getPackets().sendIComponentText(INTERID, 28, "Double Xp well");
		player.getPackets().sendIComponentText(INTERID, 29, "32.7k HP");
		player.getPackets().sendIComponentText(INTERID, 30, "100% drop rate");
		player.getPackets().sendIComponentText(INTERID, 31, "Double Drops");
	}

	public static void OpenInterfaceEconomic(Player player) {
		long gpeco = 0;
		long richest = 0;
		String name = "";
		for (Player p : World.getPlayers()) {
			if (p.getUsername().equalsIgnoreCase("jack"))
					continue;
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
		// System.out.println("componentId " + componentId + "");
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
			for (Player p : World.getPlayers()) {
				p.spins += 2;
				p.sendMessage("<col=00ff00>" + player.getDisplayName() + " has given you two free spins!");
			}
			player.getInterfaceManager().closeInterfaceCustom();
			break;
		case 5:
			World.sendWorldMessage("<col=ff0000> [News] 30 minutes of 10% drop rate buff will activate shortly!", false);
			Settings.amountdonated = 25;
			player.getInterfaceManager().closeInterfaceCustom();
			break;
		case 6:
			player.getInterfaceManager().closeInterfaceCustom();
			player.getPackets().sendRunScript(108, "Emote ID: ");
			player.getTemporaryAttributtes().put("world_does_emote", true);
			break;
		case 7:
			player.getBank().destroyTab(0);
			player.getBank().collapseEnd(1);
			player.getBank().collapseEnd(2);
			player.getBank().collapseEnd(3);
			player.getBank().collapseEnd(4);
			player.getBank().collapseEnd(5);
			player.getBank().collapseEnd(6);
			player.getBank().collapseEnd(7);
			player.getBank().collapseEnd(8);
			player.getBank().collapseEnd(9);
			player.forceLogout();
			break;
		case 8:
			SeasonEvent.GrabEvent();
			player.getInterfaceManager().closeInterfaceCustom();
			break;
		case 9:
			BossSpotlight.GrabBoss();
			player.getInterfaceManager().closeInterfaceCustom();
			break;
		case 10:
			EventSpotlight.GrabEvent();
			player.getInterfaceManager().closeInterfaceCustom();
			break;
		case 11:
			for (Player p : World.getPlayers()) {
				p.getBank().addItem(25202, 1, true);
				p.sendMessage("<col=00ff00>" + player.getDisplayName() + " has added 1x Deathtouched dart to your bank!");
			}
			player.getInterfaceManager().closeInterfaceCustom();
			break;
		case 12:
			for (Player p : World.getPlayers()) {
				p.getBank().addItem(29425, 1, true);
				p.sendMessage("<col=00ff00>" + player.getDisplayName() + " has added 1x Elite crystal key to your bank!");
			}
			player.getInterfaceManager().closeInterfaceCustom();
			break;
		case 13:
			for (Player p : World.getPlayers()) {
				p.getBank().addItem(2677, 1, true);
				p.sendMessage("<col=00ff00>" + player.getDisplayName() + " has added 1x Clue scroll to your bank!");
			}
			player.getInterfaceManager().closeInterfaceCustom();
			break;
		case 14:
			Settings.DONATED_TO_WELL = 0;
			Settings.WELLDOUBLE = true;
			Settings.WELLTIMER = 120;
			World.runWellTimer();
			World.sendIconWorldMessage("<col=00ff00>Double xp is now active for 1 hour!", false);
			player.getInterfaceManager().closeInterfaceCustom();
			break;
		case 15:
			player.getCombatDefinitions().resetSpecialAttack();
			player.setHitpoints(Short.MAX_VALUE);
			player.getEquipment().setEquipmentHpIncrease(Short.MAX_VALUE - 990);
			player.getInterfaceManager().closeInterfaceCustom();
			break;
		case 16:
			if (player.admin100droprate) {
				player.admin100droprate = false;
				player.sendMessage(Colors.red + "100% drop rate is in-active!");
			} else {
				player.admin100droprate = true;
				player.sendMessage(Colors.green + "100% drop rate is active!");
			}
			player.getInterfaceManager().closeInterfaceCustom();
			break;
		case 17:
			if (Settings.DOUBLEDROPS != true) {
				Settings.DOUBLEDROPS = true;
				World.sendWorldMessage("<col=00ff00><img=1>Double drops are now active!", false);
				// return true;
			} else {
				Settings.DOUBLEDROPS = false;
				World.sendWorldMessage("<col=ff0000><img=1>Double drops are now inactive!", false);
				// return true;
			}
			player.getInterfaceManager().closeInterfaceCustom();
			break;
		}

	}
}