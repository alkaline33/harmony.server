package com.rs.game.player.content.chests;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.Animation;
import com.rs.game.player.Player;
import com.rs.utils.Utils;

public class StarterChest {

	private static final int COMMON[][] = { { 995, 50000 }, { 995, 100000 }, { 995, 200000 } };
	private static final int VERY_RARE[][] = { { 2572, 1 }, { 6568, 1 }, { 29425, 1 } };// noob armour
	private static int reward, r, c, q, vr;

	private static void handle(Player p) {
		if (!p.getInventory().hasFreeSlots()) {
			p.sendMessage("You need some inventory space first!");
			return;
		}
		p.getInventory().deleteItem(29355, 1);
		p.setNextAnimation(new Animation(536));
		reward(p);

	}

	private static void reward(Player p) {
		r = Utils.random(10);
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
		case 29355:
			handle(p);
			return true;
		}
		return false;
	}
}