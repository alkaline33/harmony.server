package com.rs.game.npc.playersystems;

import com.discord.Discord;
import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.utils.Colors;
import com.rs.utils.Utils;

/**
 * @author Mr Joopz
 */
public class GlobalDropTable {

	/**
	 * TODO - add osrs items, add barrows & dwarf cannon set function - super box to
	 * be same with better chance
	 */

	private static final int COMMON[][] = { { 2360, 30 }, { 568, 35 }, { 1392, 80 }, { 995, 10000000 }, { 990, 5 }, { 1434, 1 }, { 1305, 1 }, { 1249, 1 }, { 1149, 1 }, { 384, 100 }, { 372, 100 }, { 378, 100 }, { 360, 100 }, { 10034, 400 }, { 1518, 90 }, { 1516, 90 }, { 1632, 50 }, { 1624, 50 }, { 1622, 50 }, { 1620, 50 }, { 1618, 50 }, { 8783, 100 }, { 8779, 100 }, { 961, 100 }, { 1437, 1000 }, { 44, 150 }, { 11212, 500 }, { 537, 30 }, { 533, 30 }, { 2362, 30 }, { 448, 40 }, { 450, 40 }, { 454, 1000 }, };
	private static final int UNCOMMON[][] = { { 2364, 50 }, { 29361, 2 }, { 11235, 1 }, { 4151, 1 }, { 6585, 1 }, { 2, 2000 }, { 995, 20000000 }, { 5304, 5 }, { 5303, 5 }, { 5302, 5 }, { 5301, 5 }, { 5300, 5 }, { 5299, 5 }, { 5298, 5 }, { 5297, 5 }, { 5296, 5 }, { 5295, 5 }, { 5288, 5 }, { 5289, 5 }, { 5316, 5 }, { 5315, 5 }, { 5314, 5 }, { 29678, 1 }, { 9245, 1000 }, { 2486, 50 }, { 220, 50 }, { 218, 50 }, { 216, 50 }, { 214, 50 }, { 212, 50 }, { 210, 50 }, { 208, 50 }, { 10176, 75 }, { 15271, 100 }, { 396, 100 }, { 390, 100 }, { 1514, 90 }, { 18831, 30 }, { 452, 50 }, };
	private static final int RARE[][] = { { 6571, 1 }, { 995, 100000000 }, { 25202, 2 }, { 10176, 250 }, { 11335, 1 }, { 29287, 1 } };
	private static int reward, r, c, q, vr;

	public static void drop(Player p, int x, int y, int z) {
		r = Utils.random(400);
		switch (r) {
		/**
		 * Rare Table
		 */
		case 300:
			c = Utils.random(RARE[0][1]);
			r = Utils.random(RARE.length);
			q = RARE[r][1];
			p.sendMessage(Colors.brown + "Rare Drop Table: You have received " + q + " x " + ItemDefinitions.getItemDefinitions(RARE[r][0]).getName() + "!");
			World.addGroundItem(new Item(RARE[r][0], q), new WorldTile(x, y, z), p, false, 180, true);
			Discord.sendDropMessage("Rare Drop Table: " + p.getDisplayName() + " have received " + q + " x " + ItemDefinitions.getItemDefinitions(RARE[r][0]).getName() + "!", p.getDisplayName());
			World.sendIconWorldMessage(Colors.orange + "Rare Drop Table: " + p.getDisplayName() + " has received " + q + " x " + ItemDefinitions.getItemDefinitions(RARE[r][0]).getName() + "!", false);
			reward = RARE[r][0];
			break;
		/**
		 * Common Table
		 */
		default:
			if (Utils.random(50) == 0) {
				c = Utils.random(UNCOMMON[0][1]);
				r = Utils.random(UNCOMMON.length);
				q = UNCOMMON[r][1];
				p.sendMessage(Colors.brown + "Rare Drop Table: You have received " + q + " x " + ItemDefinitions.getItemDefinitions(UNCOMMON[r][0]).getName() + "!");
				World.addGroundItem(new Item(UNCOMMON[r][0], q), new WorldTile(x, y, z), p, false, 180, true);
				reward = UNCOMMON[r][0];
				break;
			} else {
			c = Utils.random(COMMON[0][1]);
			r = Utils.random(COMMON.length);
			q = COMMON[r][1];
				p.sendMessage(Colors.brown + "Rare Drop Table: You have received " + q + " x " + ItemDefinitions.getItemDefinitions(COMMON[r][0]).getName() + "!");

			World.addGroundItem(new Item(COMMON[r][0], q), new WorldTile(x, y, z), p, false, 180, true);
			reward = COMMON[r][0];
			break;
			}
		}
	}

	private static String getGrammar() {
		if (q == 1) {
			return sw("a") || sw("u") || sw("o") ? "an" : "a";
		}
		return q + "";
	}

	private static boolean sw(String n) {
		return getNameForItem().startsWith(n);
	}

	private static String getNameForItem() {
		switch (reward) {
		case 995:
			return COMMON[r][1] == 1 ? "coin" : "coins";
		case 1061:
			return "pair of leather boots";
		case 592:
			return "ash";
		case 563:
			return "law runes";
		case 561:
			return "nature runes";
		case 1329:
			return "mithril scimitar";
		case 1315:
			return "mithril two handed sword";
		}
		return ItemDefinitions.getItemDefinitions(reward).getName().toLowerCase();
	}
}