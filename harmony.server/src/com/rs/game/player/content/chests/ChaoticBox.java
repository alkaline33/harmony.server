package com.rs.game.player.content.chests;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.player.Player;
import com.rs.utils.Utils;


public class ChaoticBox {

	/**
	 * TODO - add osrs items, add barrows & dwarf cannon set function - super box to
	 * be same with better chance
	 */

	private static final int COMMON[][] = { { 18349, 1 }, { 18351, 1 }, { 18353, 1 }, { 18355, 1 }, { 18357, 1 }, { 18359, 1 }, { 29515, 1 }, { 29516, 1 }, { 29517, 1 }, { 18363, 1 },{ 18361, 1 },};
	private static int reward, r, c, q, vr;

	private static void handle(Player p) {
		r = Utils.random(51);
		int roll = r;
		p.getInventory().deleteItem(29006, 1);

		switch (r) {
		default:
			c = Utils.random(COMMON[0][1]);
			r = Utils.random(COMMON.length);
			q = COMMON[r][1];
			p.getInventory().addItem(COMMON[r][0], COMMON[r][0] == 995 ? c : q);
			reward = COMMON[r][0];
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

	public static boolean isBox(int itemId, Player p) {
		switch (itemId) {
		case 29006:
			handle(p);
			return true;
		}
		return false;
	}
}