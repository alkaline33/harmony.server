package com.rs.game.player.interfaces;

import com.rs.game.player.Player;
import com.rs.game.player.SlayerTask;
import com.rs.game.player.SlayerTask.Master;
import com.rs.utils.Colors;

/**
 * 
 * @author Mr_Joopz
 *
 */

public class MasterSlayerTaskInterface {

	private static int INTER = 3014;

	public static void OpenInterface(Player player) {
		player.getInterfaceManager().sendInterface(INTER);
		/**
		 * Left side
		 */
		player.getPackets().sendIComponentText(INTER, 7, "Abyssal Demon");
		player.getPackets().sendIComponentText(INTER, 8, "Nechrael");
		player.getPackets().sendIComponentText(INTER, 10, "Dust Devil");
		player.getPackets().sendIComponentText(INTER, 11, "Ice Strykewyrm");
		player.getPackets().sendIComponentText(INTER, 12, "Jungle Strykewyrm");
		player.getPackets().sendIComponentText(INTER, 13, "Desert Strykewyrm");
		player.getPackets().sendIComponentText(INTER, 14, "Iron Dragon");
		player.getPackets().sendIComponentText(INTER, 15, "Steel Dragon");
		player.getPackets().sendIComponentText(INTER, 16, "Bronze Dragon");
		player.getPackets().sendIComponentText(INTER, 17, "Mithril Dragon");
		player.getPackets().sendIComponentText(INTER, 18, "Adamantite Dragon");
		player.getPackets().sendIComponentText(INTER, 19, "Runite Dragon");
		player.getPackets().sendIComponentText(INTER, 20, "Green Dragon");
		player.getPackets().sendIComponentText(INTER, 21, "Blue Dragon");
		player.getPackets().sendIComponentText(INTER, 22, "Red Dragon");
		player.getPackets().sendIComponentText(INTER, 23, "Black Dragon");
		player.getPackets().sendIComponentText(INTER, 24, "Frost Dragon");
		player.getPackets().sendIComponentText(INTER, 25, "Dark Beast");
		player.getPackets().sendIComponentText(INTER, 26, "Hellhound");
		player.getPackets().sendIComponentText(INTER, 27, "Jelly");
		player.getPackets().sendIComponentText(INTER, 28, "Hydra");
		player.getPackets().sendIComponentText(INTER, 29, "Alchemical Hydra");
		player.getPackets().sendIComponentText(INTER, 30, "Drake");
		player.getPackets().sendIComponentText(INTER, 57, "Wyrm");
		/**
		 * Right side
		 */
		player.getPackets().sendIComponentText(INTER, 9, "Gargoyle");
		player.getPackets().sendIComponentText(INTER, 31, "Kraken");
		player.getPackets().sendIComponentText(INTER, 32, "Fire Giant");
		player.getPackets().sendIComponentText(INTER, 33, "Aberrant Spectre");
		player.getPackets().sendIComponentText(INTER, 34, "Black Demon");
		player.getPackets().sendIComponentText(INTER, 35, "Ankou");
		player.getPackets().sendIComponentText(INTER, 36, "Dagannoth");
		player.getPackets().sendIComponentText(INTER, 37, "Ganodermic Beast");
		player.getPackets().sendIComponentText(INTER, 38, "Smoke Devil");
		player.getPackets().sendIComponentText(INTER, 39, "Eddimu");
		player.getPackets().sendIComponentText(INTER, 40, "Glacor");
		player.getPackets().sendIComponentText(INTER, 41, "Cerberus");
		player.getPackets().sendIComponentText(INTER, 42, "Callisto");
		player.getPackets().sendIComponentText(INTER, 43, "Abyssal Sire");
		player.getPackets().sendIComponentText(INTER, 44, "Lizardman Shaman");
		player.getPackets().sendIComponentText(INTER, 45, "Zulrah");
		player.getPackets().sendIComponentText(INTER, 46, "General Graardor");
		player.getPackets().sendIComponentText(INTER, 47, "Kree'arra");
		player.getPackets().sendIComponentText(INTER, 48, "K'ril Tsutsaroth");
		player.getPackets().sendIComponentText(INTER, 49, "Commander Zilyana");
		player.getPackets().sendIComponentText(INTER, 50, "Ancient Wyvern");
		player.getPackets().sendIComponentText(INTER, 51, "Long-tailed Wyvern");
		player.getPackets().sendIComponentText(INTER, 52, "Spitting Wyvern");
		player.getPackets().sendIComponentText(INTER, 53, "Taloned Wyvern");

	}

	public static boolean handleButtons(Player player, int componentId) {
		int npcId = 0;
		switch (componentId) {
		/**
		 * Left hand side
		 */
		case 4:
			player.getInterfaceManager().closeInterfaceCustom();
			return true;
		case 7:
			player.getInterfaceManager().closeInterfaceCustom();
			player.masterslayertaskcomponent = false;
			SlayerTask.notrandom(player, Master.KURADAL, 0);
			player.sendMessage(Colors.salmon + "Your slayer task is to kill " + player.getTask().getTaskAmount() + " " + player.getTask().getName() + "");
			return true;
		case 8:
			player.getInterfaceManager().closeInterfaceCustom();
			player.masterslayertaskcomponent = false;
			SlayerTask.notrandom(player, Master.KURADAL, 2);
			player.sendMessage(Colors.salmon + "Your slayer task is to kill " + player.getTask().getTaskAmount() + " " + player.getTask().getName() + "");
			return true;
		case 10:
			player.getInterfaceManager().closeInterfaceCustom();
			player.masterslayertaskcomponent = false;
			SlayerTask.notrandom(player, Master.KURADAL, 3);
			player.sendMessage(Colors.salmon + "Your slayer task is to kill " + player.getTask().getTaskAmount() + " " + player.getTask().getName() + "");
			return true;
		case 11:
			player.getInterfaceManager().closeInterfaceCustom();
			player.masterslayertaskcomponent = false;
			SlayerTask.notrandom(player, Master.KURADAL, 4);
			player.sendMessage(Colors.salmon + "Your slayer task is to kill " + player.getTask().getTaskAmount() + " " + player.getTask().getName() + "");
			return true;
		case 12:
			player.getInterfaceManager().closeInterfaceCustom();
			player.masterslayertaskcomponent = false;
			SlayerTask.notrandom(player, Master.KURADAL, 5);
			player.sendMessage(Colors.salmon + "Your slayer task is to kill " + player.getTask().getTaskAmount() + " " + player.getTask().getName() + "");
			return true;
		case 13:
			player.getInterfaceManager().closeInterfaceCustom();
			player.masterslayertaskcomponent = false;
			SlayerTask.notrandom(player, Master.KURADAL, 6);
			player.sendMessage(Colors.salmon + "Your slayer task is to kill " + player.getTask().getTaskAmount() + " " + player.getTask().getName() + "");
			return true;
		case 14:
			player.getInterfaceManager().closeInterfaceCustom();
			player.masterslayertaskcomponent = false;
			SlayerTask.notrandom(player, Master.KURADAL, 7);
			player.sendMessage(Colors.salmon + "Your slayer task is to kill " + player.getTask().getTaskAmount() + " " + player.getTask().getName() + "");
			return true;
		case 15:
			player.getInterfaceManager().closeInterfaceCustom();
			player.masterslayertaskcomponent = false;
			SlayerTask.notrandom(player, Master.KURADAL, 8);
			player.sendMessage(Colors.salmon + "Your slayer task is to kill " + player.getTask().getTaskAmount() + " " + player.getTask().getName() + "");
			return true;
		case 16:
			player.getInterfaceManager().closeInterfaceCustom();
			player.masterslayertaskcomponent = false;
			SlayerTask.notrandom(player, Master.KURADAL, 49);
			player.sendMessage(Colors.salmon + "Your slayer task is to kill " + player.getTask().getTaskAmount() + " " + player.getTask().getName() + "");
			return true;
		case 17:
			player.getInterfaceManager().closeInterfaceCustom();
			player.masterslayertaskcomponent = false;
			SlayerTask.notrandom(player, Master.KURADAL, 11);
			player.sendMessage(Colors.salmon + "Your slayer task is to kill " + player.getTask().getTaskAmount() + " " + player.getTask().getName() + "");
			return true;
		case 18:
			player.getInterfaceManager().closeInterfaceCustom();
			player.masterslayertaskcomponent = false;
			SlayerTask.notrandom(player, Master.KURADAL, 14);
			player.sendMessage(Colors.salmon + "Your slayer task is to kill " + player.getTask().getTaskAmount() + " " + player.getTask().getName() + "");
			return true;
		case 19:
			player.getInterfaceManager().closeInterfaceCustom();
			player.masterslayertaskcomponent = false;
			SlayerTask.notrandom(player, Master.KURADAL, 13);
			player.sendMessage(Colors.salmon + "Your slayer task is to kill " + player.getTask().getTaskAmount() + " " + player.getTask().getName() + "");
			return true;
		case 20:
			player.getInterfaceManager().closeInterfaceCustom();
			player.masterslayertaskcomponent = false;
			SlayerTask.notrandom(player, Master.KURADAL, 10);
			player.sendMessage(Colors.salmon + "Your slayer task is to kill " + player.getTask().getTaskAmount() + " " + player.getTask().getName() + "");
			return true;
		case 21:
			player.getInterfaceManager().closeInterfaceCustom();
			player.masterslayertaskcomponent = false;
			SlayerTask.notrandom(player, Master.KURADAL, 19);
			player.sendMessage(Colors.salmon + "Your slayer task is to kill " + player.getTask().getTaskAmount() + " " + player.getTask().getName() + "");
			return true;
		case 22:
			player.getInterfaceManager().closeInterfaceCustom();
			player.masterslayertaskcomponent = false;
			SlayerTask.notrandom(player, Master.KURADAL, 50);
			player.sendMessage(Colors.salmon + "Your slayer task is to kill " + player.getTask().getTaskAmount() + " " + player.getTask().getName() + "");
			return true;
		case 23:
			player.getInterfaceManager().closeInterfaceCustom();
			player.masterslayertaskcomponent = false;
			SlayerTask.notrandom(player, Master.KURADAL, 9);
			player.sendMessage(Colors.salmon + "Your slayer task is to kill " + player.getTask().getTaskAmount() + " " + player.getTask().getName() + "");
			return true;
		case 24:
			player.getInterfaceManager().closeInterfaceCustom();
			player.masterslayertaskcomponent = false;
			SlayerTask.notrandom(player, Master.KURADAL, 25);
			player.sendMessage(Colors.salmon + "Your slayer task is to kill " + player.getTask().getTaskAmount() + " " + player.getTask().getName() + "");
			return true;
		case 25:
			player.getInterfaceManager().closeInterfaceCustom();
			player.masterslayertaskcomponent = false;
			SlayerTask.notrandom(player, Master.KURADAL, 27);
			player.sendMessage(Colors.salmon + "Your slayer task is to kill " + player.getTask().getTaskAmount() + " " + player.getTask().getName() + "");
			return true;
		case 26:
			player.getInterfaceManager().closeInterfaceCustom();
			player.masterslayertaskcomponent = false;
			SlayerTask.notrandom(player, Master.KURADAL, 26);
			player.sendMessage(Colors.salmon + "Your slayer task is to kill " + player.getTask().getTaskAmount() + " " + player.getTask().getName() + "");
			return true;
		case 27:
			player.getInterfaceManager().closeInterfaceCustom();
			player.masterslayertaskcomponent = false;
			SlayerTask.notrandom(player, Master.KURADAL, 15);
			player.sendMessage(Colors.salmon + "Your slayer task is to kill " + player.getTask().getTaskAmount() + " " + player.getTask().getName() + "");
			break;
		/**
		 * Right hand side
		 */
		case 9:
			player.getInterfaceManager().closeInterfaceCustom();
			player.masterslayertaskcomponent = false;
			SlayerTask.notrandom(player, Master.KURADAL, 24);
			player.sendMessage(Colors.salmon + "Your slayer task is to kill " + player.getTask().getTaskAmount() + " " + player.getTask().getName() + "");
			break;
		case 31:
			player.getInterfaceManager().closeInterfaceCustom();
			player.masterslayertaskcomponent = false;
			SlayerTask.notrandom(player, Master.KURADAL, 1);
			player.sendMessage(Colors.salmon + "Your slayer task is to kill " + player.getTask().getTaskAmount() + " " + player.getTask().getName() + "");
			break;
		case 32:
			player.getInterfaceManager().closeInterfaceCustom();
			player.masterslayertaskcomponent = false;
			SlayerTask.notrandom(player, Master.KURADAL, 17);
			player.sendMessage(Colors.salmon + "Your slayer task is to kill " + player.getTask().getTaskAmount() + " " + player.getTask().getName() + "");
			break;
		case 33:
			player.getInterfaceManager().closeInterfaceCustom();
			player.masterslayertaskcomponent = false;
			SlayerTask.notrandom(player, Master.KURADAL, 18);
			player.sendMessage(Colors.salmon + "Your slayer task is to kill " + player.getTask().getTaskAmount() + " " + player.getTask().getName() + "");
			break;
		case 34:
			player.getInterfaceManager().closeInterfaceCustom();
			player.masterslayertaskcomponent = false;
			SlayerTask.notrandom(player, Master.KURADAL, 20);
			player.sendMessage(Colors.salmon + "Your slayer task is to kill " + player.getTask().getTaskAmount() + " " + player.getTask().getName() + "");
			break;
		case 35:
			player.getInterfaceManager().closeInterfaceCustom();
			player.masterslayertaskcomponent = false;
			SlayerTask.notrandom(player, Master.KURADAL, 44);
			player.sendMessage(Colors.salmon + "Your slayer task is to kill " + player.getTask().getTaskAmount() + " " + player.getTask().getName() + "");
			break;
		case 36:
			player.getInterfaceManager().closeInterfaceCustom();
			player.masterslayertaskcomponent = false;
			SlayerTask.notrandom(player, Master.KURADAL, 45);
			player.sendMessage(Colors.salmon + "Your slayer task is to kill " + player.getTask().getTaskAmount() + " " + player.getTask().getName() + "");
			break;
		case 37:
			player.getInterfaceManager().closeInterfaceCustom();
			player.masterslayertaskcomponent = false;
			SlayerTask.notrandom(player, Master.KURADAL, 30);
			player.sendMessage(Colors.salmon + "Your slayer task is to kill " + player.getTask().getTaskAmount() + " " + player.getTask().getName() + "");
			break;
		case 38:
			player.getInterfaceManager().closeInterfaceCustom();
			player.masterslayertaskcomponent = false;
			SlayerTask.notrandom(player, Master.KURADAL, 35);
			player.sendMessage(Colors.salmon + "Your slayer task is to kill " + player.getTask().getTaskAmount() + " " + player.getTask().getName() + "");
			break;
		case 39:
			player.getInterfaceManager().closeInterfaceCustom();
			player.masterslayertaskcomponent = false;
			SlayerTask.notrandom(player, Master.KURADAL, 28);
			player.sendMessage(Colors.salmon + "Your slayer task is to kill " + player.getTask().getTaskAmount() + " " + player.getTask().getName() + "");
			break;
		case 40:
			player.getInterfaceManager().closeInterfaceCustom();
			player.masterslayertaskcomponent = false;
			SlayerTask.notrandom(player, Master.KURADAL, 12);
			player.sendMessage(Colors.salmon + "Your slayer task is to kill " + player.getTask().getTaskAmount() + " " + player.getTask().getName() + "");
			break;
		case 41:
			player.getInterfaceManager().closeInterfaceCustom();
			player.masterslayertaskcomponent = false;
			SlayerTask.notrandom(player, Master.KURADAL, 51);
			player.sendMessage(Colors.salmon + "Your slayer task is to kill " + player.getTask().getTaskAmount() + " " + player.getTask().getName() + "");
			break;
		case 42:
			player.getInterfaceManager().closeInterfaceCustom();
			player.masterslayertaskcomponent = false;
			SlayerTask.notrandom(player, Master.KURADAL, 52);
			player.sendMessage(Colors.salmon + "Your slayer task is to kill " + player.getTask().getTaskAmount() + " " + player.getTask().getName() + "");
			break;
		case 43:
			player.getInterfaceManager().closeInterfaceCustom();
			player.masterslayertaskcomponent = false;
			SlayerTask.notrandom(player, Master.KURADAL, 38);
			player.sendMessage(Colors.salmon + "Your slayer task is to kill " + player.getTask().getTaskAmount() + " " + player.getTask().getName() + "");
			break;
		case 44:
			player.getInterfaceManager().closeInterfaceCustom();
			player.masterslayertaskcomponent = false;
			SlayerTask.notrandom(player, Master.KURADAL, 37);
			player.sendMessage(Colors.salmon + "Your slayer task is to kill " + player.getTask().getTaskAmount() + " " + player.getTask().getName() + "");
			break;
		case 45:
			player.getInterfaceManager().closeInterfaceCustom();
			player.masterslayertaskcomponent = false;
			SlayerTask.notrandom(player, Master.KURADAL, 36);
			player.sendMessage(Colors.salmon + "Your slayer task is to kill " + player.getTask().getTaskAmount() + " " + player.getTask().getName() + "");
			break;
		case 46:
			player.getInterfaceManager().closeInterfaceCustom();
			player.masterslayertaskcomponent = false;
			SlayerTask.notrandom(player, Master.KURADAL, 31);
			player.sendMessage(Colors.salmon + "Your slayer task is to kill " + player.getTask().getTaskAmount() + " " + player.getTask().getName() + "");
			break;
		case 47:
			player.getInterfaceManager().closeInterfaceCustom();
			player.masterslayertaskcomponent = false;
			SlayerTask.notrandom(player, Master.KURADAL, 53);
			player.sendMessage(Colors.salmon + "Your slayer task is to kill " + player.getTask().getTaskAmount() + " " + player.getTask().getName() + "");
			break;
		case 48:
			player.getInterfaceManager().closeInterfaceCustom();
			player.masterslayertaskcomponent = false;
			SlayerTask.notrandom(player, Master.KURADAL, 33);
			player.sendMessage(Colors.salmon + "Your slayer task is to kill " + player.getTask().getTaskAmount() + " " + player.getTask().getName() + "");
			break;
		case 49:
			player.getInterfaceManager().closeInterfaceCustom();
			player.masterslayertaskcomponent = false;
			SlayerTask.notrandom(player, Master.KURADAL, 32);
			player.sendMessage(Colors.salmon + "Your slayer task is to kill " + player.getTask().getTaskAmount() + " " + player.getTask().getName() + "");
			break;
		case 28:
			player.getInterfaceManager().closeInterfaceCustom();
			player.masterslayertaskcomponent = false;
			SlayerTask.notrandom(player, Master.KURADAL, 65);
			player.sendMessage(Colors.salmon + "Your slayer task is to kill " + player.getTask().getTaskAmount() + " " + player.getTask().getName() + "");
			break;
		case 29:
			player.getInterfaceManager().closeInterfaceCustom();
			player.masterslayertaskcomponent = false;
			SlayerTask.notrandom(player, Master.DEATH, 30);
			player.sendMessage(Colors.salmon + "Your slayer task is to kill " + player.getTask().getTaskAmount() + " " + player.getTask().getName() + "");
			break;
		case 30:
			player.getInterfaceManager().closeInterfaceCustom();
			player.masterslayertaskcomponent = false;
			SlayerTask.notrandom(player, Master.KURADAL, 63);
			player.sendMessage(Colors.salmon + "Your slayer task is to kill " + player.getTask().getTaskAmount() + " " + player.getTask().getName() + "");
			break;
		case 57:
			player.getInterfaceManager().closeInterfaceCustom();
			player.masterslayertaskcomponent = false;
			SlayerTask.notrandom(player, Master.KURADAL, 64);
			player.sendMessage(Colors.salmon + "Your slayer task is to kill " + player.getTask().getTaskAmount() + " " + player.getTask().getName() + "");
			break;
		case 50:
			player.getInterfaceManager().closeInterfaceCustom();
			player.masterslayertaskcomponent = false;
			SlayerTask.notrandom(player, Master.KURADAL, 62);
			player.sendMessage(Colors.salmon + "Your slayer task is to kill " + player.getTask().getTaskAmount() + " " + player.getTask().getName() + "");
			break;
		case 51:
			player.getInterfaceManager().closeInterfaceCustom();
			player.masterslayertaskcomponent = false;
			SlayerTask.notrandom(player, Master.KURADAL, 59);
			player.sendMessage(Colors.salmon + "Your slayer task is to kill " + player.getTask().getTaskAmount() + " " + player.getTask().getName() + "");
			break;
		case 52:
			player.getInterfaceManager().closeInterfaceCustom();
			player.masterslayertaskcomponent = false;
			SlayerTask.notrandom(player, Master.KURADAL, 61);
			player.sendMessage(Colors.salmon + "Your slayer task is to kill " + player.getTask().getTaskAmount() + " " + player.getTask().getName() + "");
			break;
		case 53:
			player.getInterfaceManager().closeInterfaceCustom();
			player.masterslayertaskcomponent = false;
			SlayerTask.notrandom(player, Master.KURADAL, 60);
			player.sendMessage(Colors.salmon + "Your slayer task is to kill " + player.getTask().getTaskAmount() + " " + player.getTask().getName() + "");
			break;
		}
		return false;
	}
}