package com.rs.game.player.content;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.player.Player;
import com.rs.utils.Utils;

/**
 * @author HerBrightSkies @rune-server.org
 */
public class ChaoticBoxI {

	/**
	 * TODO - add osrs items, add barrows & dwarf cannon set function - super box to
	 * be same with better chance
	 */

	private static final int COMMON[][] = { { 29410, 1 }, { 29409, 1 }, { 29408, 1 }, { 29407, 1 }, { 29406, 1 }, { 29357, 1 } };
	// private static final int VERY_RARE[][] = { { 14484, 1 }, { 29472, 1 }, {
	// 29459, 1 }, { 29436, 1 }, { 29435, 1 }, { 29434, 100 }, { 29433, 1 }, {
	// 29432, 1 }, { 29431, 1 }, { 29430, 1 }, { 29429, 1 }, { 29428, 1 } };
	// need add kodai wand, armour, other rewards
	private static int reward, r, c, q, vr;

	private static void handle(Player p) {
		r = Utils.random(51);
		int roll = r;
		p.getInventory().deleteItem(29339, 1);

		switch (r) {
		default:
			c = Utils.random(COMMON[0][1]);
			r = Utils.random(COMMON.length);
			q = COMMON[r][1];
			p.getInventory().addItem(COMMON[r][0], COMMON[r][0] == (995) ? c : q);
			reward = COMMON[r][0];
			// p.sendMessage("You needed to roll a 0 to land a rare item. You rolled a " +
			// roll + ".");
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
		case 29339:
			handle(p);
			return true;
		}
		return false;
	}
}