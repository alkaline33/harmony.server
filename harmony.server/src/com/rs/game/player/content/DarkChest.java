package com.rs.game.player.content;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.player.Player;
import com.rs.utils.Utils;

public class DarkChest {

	private static final int COMMON[][] = { { 995, 200000 }, { 995, 500000 }, { 1514, 20 }, { 1522, 20 }, { 1520, 20 }, { 384, 20 }, { 15271, 20 }, { 332, 20 }, { 360, 20 }, { 11212, 50 }, { 886, 50 }, { 888, 50 }, { 890, 50 }, { 892, 50 }, { 537, 20 }, { 18831, 20 }, { 1437, 50 }, { 961, 20 }, { 8783, 20 }, { 8781, 20 }, { 2486, 20 }, { 220, 20 }, { 218, 20 }, { 206, 20 }, { 208, 20 }, { 210, 20 }, { 212, 20 }, { 214, 20 }, { 216, 20 }, { 1618, 20 }, { 1620, 20 }, { 1622, 20 }, { 1624, 20 }, { 1746, 20 }, { 2506, 20 }, { 2508, 20 }, { 2510, 20 }, { 314, 200 }, { 452, 20 }, { 439, 20 }, { 441, 20 }, { 443, 20 }, { 445, 20 }, { 448, 20 }, { 450, 20 }, { 454, 50 }, { 2364, 20 }, { 2352, 20 }, { 2354, 20 }, { 2356, 20 }, { 2358, 20 }, { 2360, 20 }, { 2362, 20 }, { 5304, 20 }, { 5315, 20 },
			{ 5316, 5 }, { 5294, 20 }, { 5295, 5 }, { 5296, 20 }, { 5297, 20 }, { 5298, 20 }, { 5299, 20 }, { 5300, 20 }, { 5301, 20 }, { 5302, 20 }, { 5303, 20 }, { 2435, 20 }, { 3025, 20 }, { 6686, 20 } };
	private static final int VERY_RARE[][] = { { 29541, 1 }, { 29363, 1 } };
	private static int reward, r, c, q, vr;

	private static void handle(Player p) {
		if (!p.getInventory().hasFreeSlots()) {
			p.sendMessage("You need some inventory space first!");
			return;
		}
		p.getInventory().deleteItem(29364, 1);
		p.getPackets().sendSound(98, 0, 1);
		reward(p);

	}

	private static void reward(Player p) {
		r = Utils.random(30);
		switch (r) {
		case 1:
			r = Utils.random(VERY_RARE.length);
			q = VERY_RARE[r][1];
			reward = VERY_RARE[r][0];
			p.getInventory().addItem(VERY_RARE[r][0], q);
			break;
		default:
			c = Utils.random(COMMON[0][1]);
			r = Utils.random(COMMON.length);
			q = COMMON[r][1];
			reward = COMMON[r][0];
			p.getInventory().addItem(COMMON[r][0], Utils.random(5, q));
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

	public static boolean isBox(int itemId, Player p) {
		switch (itemId) {
		case 29364:
			handle(p);
			return true;
		}
		return false;
	}
}