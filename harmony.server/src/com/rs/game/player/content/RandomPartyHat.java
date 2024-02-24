package com.rs.game.player.content;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.player.Player;
import com.rs.utils.Utils;

/**
 * @author HerBrightSkies @rune-server.org
 */
public class RandomPartyHat {

	private static final int COMMON[][] = { { 29836, 1 }, { 29789, 1 }, { 29780, 1 }, { 29696, 1 }, { 29627, 1 }, { 29626, 1 }, { 1038, 1 }, { 1040, 1 }, { 1042, 1 }, { 1044, 1 }, { 1046, 1 }, { 1048, 1 } };
	private static int reward, r, c, q, vr;

	private static void handle(Player p) {
		p.getInventory().deleteItem(29372, 1);
		p.getPackets().sendSound(98, 0, 1);
		reward(p);

	}

	private static void reward(Player p) {
		r = Utils.random(10);
		switch (r) {
		default:
			c = Utils.random(COMMON[0][1]);
			r = Utils.random(COMMON.length);
			q = COMMON[r][1];
			reward = COMMON[r][0];
			// if (loop == 10) {
			p.getInventory().addItem(COMMON[r][0], q);
			p.sm("You open the box and find " + getGrammar() + " " + getNameForItem() + ".");
			// }
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
		case 29372:
			handle(p);
			return true;
		}
		return false;
	}
}