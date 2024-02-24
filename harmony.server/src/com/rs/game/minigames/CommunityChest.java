package com.rs.game.minigames;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.World;
import com.rs.game.player.Player;
import com.rs.utils.Utils;

/**
 * @author Mr_joopz @rune-server.org
 */
public class CommunityChest {

	private static final int COMMON[][] = { { 29629, 1 }, { 1514, 75 }, { 1516, 100 }, { 452, 60 }, { 450, 80 },
			{ 454, 300 }, { 537, 50 }, { 384, 100 }, { 15271, 75 }, { 6572, 1 }, { 220, 50 }, { 5304, 7 }, { 218, 60 },
			{ 5303, 10 }, { 995, 25000000 }, { 25202, 1 }, { 29544, 1 }, { 990, 3 }, { 29542, 1 }, { 23713, 1 },
			{ 4151, 1 } };
	private static final int UNCOMMON[][] = { { 29541, 1 }, { 23714, 1 }, { 2364, 75 }, { 2362, 100 }, { 18831, 75 },
			{ 22207, 1 }, { 22209, 1 }, { 22211, 1 }, { 22213, 1 }, { 21258, 1 }, { 21260, 1 }, { 21262, 1 },
			{ 21264, 1 }, { 24108, 1 }, { 24110, 1 }, { 24112, 1 }, { 24114, 1 }, { 11283, 1 }, { 15701, 1 },
			{ 15702, 1 }, { 15703, 1 }, { 15704, 1 } };
	private static final int RARE[][] = { { 11698, 1 }, { 11700, 1 }, { 11730, 1 }, { 25037, 1 }, { 11716, 1 },
			{ 24998, 1 }, { 24995, 1 }, { 24992, 1 }, { 11722, 1 }, { 11720, 1 }, { 11718, 1 }, { 11728, 1 },
			{ 11726, 1 }, { 11724, 1 }, { 11696, 1 }, { 29540, 1 }, { 23715, 1 }, { 21787, 1 }, { 21790, 1 },
			{ 21793, 1 }, { 19308, 1 }, { 19311, 1 }, { 19314, 1 }, { 19317, 1 }, { 19320, 1 }, { 25202, 3 } };
	private static final int VRARE[][] = { { 14484, 1 }, { 11694, 1 }, { 20135, 1 }, { 20139, 1 }, { 20143, 1 },
			{ 24977, 1 }, { 24983, 1 }, { 20147, 1 }, { 20151, 1 }, { 20153, 1 }, { 24974, 1 }, { 24989, 1 },
			{ 20159, 1 }, { 20163, 1 }, { 20167, 1 }, { 24980, 1 }, { 24986, 1 }, { 29928, 1 }, { 20171, 1 },
			{ 25202, 5 } };
	private static int reward, r, c, q;

	private static void handle(Player p) {
		// System.out.println(arg0);
		if (!p.getInventory().contains(29654)) {
			p.sendMessage("You need a Community key to open this!");
			return;
		}
		if (p.getInventory().getFreeSlots() < 2) {
			p.sendMessage("You need at least 2 free inventory slots to use this chest");
			return;
		}
		p.getInventory().deleteItem(29655, 1);
		p.getInventory().deleteItem(29654, 1);
		r = Utils.random(25);
		switch (r) {
		case 1:
			r = Utils.random(VRARE.length);
			q = VRARE[r][1];
			p.getInventory().addItem(VRARE[r][0], q);
			reward = VRARE[r][0];
			p.sm("You open the chest and find " + getGrammar() + " " + getNameForItem() + ".");
			break;
		case 2:
		case 3:
			r = Utils.random(RARE.length);
			q = RARE[r][1];
			p.getInventory().addItem(RARE[r][0], q);
			reward = RARE[r][0];
			p.sm("You open the chest and find " + getGrammar() + " " + getNameForItem() + ".");
			break;
		case 4:
		case 5:
		case 6:
			r = Utils.random(UNCOMMON.length);
			q = UNCOMMON[r][1];
			p.getInventory().addItem(UNCOMMON[r][0], q);
			reward = UNCOMMON[r][0];
			p.sm("You open the chest and find " + getGrammar() + " " + getNameForItem() + ".");
			break;
		default:
			c = Utils.random(COMMON[0][1]);
			r = Utils.random(COMMON.length);
			q = COMMON[r][1];
			p.getInventory().addItem(COMMON[r][0], q);
			reward = COMMON[r][0];
			p.sm("You open the chest and find " + getGrammar() + " " + getNameForItem() + ".");
			break;
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
			return COMMON[r][1] == (1) ? "coin" : "coins";
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

	public static boolean handleObjects(int itemId, Player p) {
		switch (itemId) {
		case 29655:
			handle(p);
			return true;
		}
		return false;
	}
}