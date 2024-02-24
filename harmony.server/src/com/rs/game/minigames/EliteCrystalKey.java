package com.rs.game.minigames;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.Animation;
import com.rs.game.World;
import com.rs.game.player.Player;
import com.rs.game.player.content.event.season.SeasonEvent;
import com.rs.utils.Colors;
import com.rs.utils.Utils;

public class EliteCrystalKey {

	private static final int COMMON[][] = { { 11230, 500 }, { 208, 10 }, { 3114, 5 }, { 210, 10 }, { 212, 10 }, { 214, 10 }, { 216, 10 }, { 218, 10 }, { 220, 10 }, { 2486, 10 }, { 1520, 15 }, { 1516, 15 }, { 1514, 15 }, { 5313, 2 }, { 5314, 2 }, { 5315, 2 }, { 5316, 2 }, { 5297, 20 }, { 5298, 2 }, { 5299, 2 }, { 5300, 2 }, { 5301, 2 }, { 5302, 2 }, { 5303, 2 }, { 5304, 2 }, { 1618, 15 }, { 1620, 15 }, { 1622, 15 }, { 1624, 15 }, { 1626, 15 }, { 537, 7 }, { 18831, 7 }, { 448, 7 }, { 450, 7 }, { 452, 7 }, { 537, 7 }, { 2, 50 }, { 2669, 1 }, { 2671, 1 }, { 2673, 1 }, { 2675, 1 }, { 2653, 1 }, { 2655, 1 }, { 2657, 1 }, { 2659, 1 }, { 2661, 1 }, { 2663, 1 }, { 2665, 1 }, { 2667, 1 }, { 15503, 1 }, { 15505, 1 }, { 15507, 1 }, { 15509, 1 }, { 15511, 1 }, { 990, 2 }, { 7462, 1 } };
	private static final int RARE[][] = { { 29541, 2 }, { 29540, 1 }, { 15441, 1 }, { 15442, 1 }, { 15443, 1 }, { 15444, 1 }, { 3114, 10 }, { 29654, 1 }, { 11286, 1 }, { 29774, 1 }, { 29772, 1 }, { 29773, 1 }, { 29678, 4 }, { 6199, 1 }, { 29426, 1 }, { 6739, 1 }, { 15259, 1 }, { 6571, 1 }, { 6571, 1 }, { 4151, 1 }, { 4151, 1 }, { 2577, 1 }, { 14484, 1 }, { 19670, 1 }, { 11846, 1 }, { 11848, 1 }, { 11850, 1 }, { 11852, 1 }, { 11854, 1 }, { 11856, 1 }, { 21787, 1 }, { 21790, 1 }, { 21793, 1 } };
	private static int reward, r, c, q, vr;
	public static final int KEY = 29425;
	public static final int Animation = 881;

	/**
	 * If the player can open the chest.
	 */
	public static boolean canOpen(Player p) {
		if (p.getInventory().containsItem(KEY, 1) && p.getInventory().getFreeSlots() > 2) {
			return true;
		} else {
			p.sendMessage("<col=FFFF00>This chest is locked. You must also have 3 free spaces.</col>");
			return false;
		}
	}

	/**
	 * When the player searches the chest.
	 */
	public static void searchChest(final Player p) {
		// if (p.getLockDelay() > 0)
		// return;
		p.getInventory().deleteItem(KEY, 1);
		p.setNextAnimation(new Animation(Animation));
		p.eliteckeyused++;
		p.Drypoints += 100;
		SeasonEvent.HandleActivity(p, "Crystal Keys", 40);
		p.sendMessage("You gain 100 Harmony points for opening this chest.");
		reward(p);

	}

	private static void reward(Player p) {
		r = Utils.random(20);
		switch (r) {
		case 3:
			r = Utils.random(RARE.length);
			q = RARE[r][1];
			reward = RARE[r][0];
			p.getInventory().addItem(RARE[r][0], q);
			p.sm("You open the chest and find " + getGrammar() + " " + getNameForItem() + ".");
			World.sendWorldMessage(Colors.gray + "[News] " + p.getDisplayName() + " has obtained " + Utils.format(q) + " x  " + getNameForItem() + " from an Elite crystal key!", false);

			break;
		default:
			c = Utils.random(COMMON[0][1]);
			r = Utils.random(COMMON.length);
			q = COMMON[r][1];
			reward = COMMON[r][0];
			p.getInventory().addItem(COMMON[r][0], COMMON[r][0] == 995 ? c : q);
			p.sm("You open the chest and find " + (COMMON[r][0] == 995 ? c : getGrammar()) + " " + getNameForItem() + ", " + (COMMON[r][0] == 995 ? "." : "."));

			c = Utils.random(COMMON[0][1]);
			r = Utils.random(COMMON.length);
			q = COMMON[r][1];
			reward = COMMON[r][0];
			p.getInventory().addItem(COMMON[r][0], COMMON[r][0] == 995 ? c : q);
			p.sm("You open the chest and find " + (COMMON[r][0] == 995 ? c : getGrammar()) + " " + getNameForItem() + ", " + (COMMON[r][0] == 995 ? "." : "."));

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