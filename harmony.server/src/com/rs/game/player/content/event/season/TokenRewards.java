package com.rs.game.player.content.event.season;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.World;
import com.rs.game.player.Player;
import com.rs.utils.Colors;
import com.rs.utils.Utils;

public class TokenRewards {

	/**
	 * Deactivate festive aura next season
	 */

	private static int TOKENS = SeasonEvent.TOKENS;

	private static final int COMMON[][] = { { 5022, 1 }, { 2360, 30}, { 29425, 1}, { 2717, 1}, { 29679, 1}, { 11230, 100}, { 29361, 1}, { 29542, 1}, { 10034, 100}, { 1632, 75}, { 5315, 5}, { 1516, 50}, { 1518, 75}, { 1520, 100}, { 318, 1}, { 384, 50}, { 7945, 75}, { 378, 100}, { 5022, 2 },{ 5022, 5 },{ 5022, 10 }, { 450, 50}, { 448, 75}, { 441, 100}, { 454, 500}, { 10176, 5}, { 10176, 10}, { 10176, 25}, { 24155, 1}};
	private static final int RARE[][] = { { 29922, 1}, { 29540, 1}, { 29371, 1}, { 6571, 1}, { 29481, 1}, { 6, 1}, { 8, 1}, { 10, 1}, { 12, 1}, { 2362, 20}, { 2364, 20},{ 25202, 5},{ 28935, 1},{ 12503, 1},{ 5022, 100},{ 5022, 50}, { 25202, 1}, { 18831, 50}, { 537, 50}, { 6572, 1}, { 5316, 5}, { 1514, 50}, { 15271, 50}, { 452, 50}};
	private static final int JACKPOT[][] = { {29836, 1}};
	private static final int SCROLLS[][] = { { 29347, 1 }, { 29346, 1 },{ 29111, 1 },{ 29112, 1 }, { 29220, 1 }, { 29345, 1 }, { 29344, 1 }, { 29343, 1 } };
	private static int reward, r, c, q, vr;

	public static void handle(Player p) {
	//	System.out.println("1");
		if (p.getInventory().getNumerOf(TOKENS) < 300) {
			return;
		}
		p.getInventory().deleteItem(TOKENS, 300);
		p.seasonalrewardsobtained++;
		r = Utils.random(25);
		switch (r) {
		case 3:
			if (Utils.random(20) == 0) {
				if (Utils.random(100) == 0) {
					r = Utils.random(JACKPOT.length);
					q = JACKPOT[r][1];
					p.getInventory().addItem(JACKPOT[r][0], q);
					reward = JACKPOT[r][0];
					p.sm("You open the box and find " + getGrammar() + " " + getNameForItem() + ".");
					World.sendIconWorldMessage(Colors.darkRed + "[News] " + p.getDisplayName() + " has obtained the Jackpot item, " + Utils.format(q) + " x  " + getNameForItem() + " from the " + SeasonEvent.SEASON + " Event!", false);
				} else {
				r = Utils.random(SCROLLS.length);
				q = SCROLLS[r][1];
				p.getInventory().addItem(SCROLLS[r][0], q);
				reward = SCROLLS[r][0];
				p.sm("You open the box and find " + getGrammar() + " " + getNameForItem() + ".");
				World.sendIconWorldMessage(Colors.darkRed + "[News] " + p.getDisplayName() + " has obtained " + Utils.format(q) + " x  " + getNameForItem() + " from the " + SeasonEvent.SEASON + " Event!", false);
				}
				} else {
			r = Utils.random(RARE.length);
			q = RARE[r][1];
			p.getInventory().addItem(RARE[r][0], q);
			reward = RARE[r][0];
			p.sm("You open the box and find " + getGrammar() + " " + getNameForItem() + ".");
			World.sendIconWorldMessage(Colors.darkRed + "[News] " + p.getDisplayName() + " has obtained " + Utils.format(q) + " x  " + getNameForItem() + " from the " + SeasonEvent.SEASON + " Event!", false);
			}
			break;
		default:
			c = Utils.random(COMMON[0][1]);
			r = Utils.random(COMMON.length);
			q = COMMON[r][1];
			p.getInventory().addItem(COMMON[r][0], COMMON[r][0] == 995 ? c : q);
			reward = COMMON[r][0];
			p.sm("You open the box and find " + (COMMON[r][0] == 995 ? c : getGrammar()) + " " + getNameForItem() + ", " + (COMMON[r][0] == 995 ? "." : "."));
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
}