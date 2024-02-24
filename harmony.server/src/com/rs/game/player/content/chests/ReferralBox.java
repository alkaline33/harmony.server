package com.rs.game.player.content.chests;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.Animation;
import com.rs.game.player.Player;
import com.rs.utils.Utils;

public class ReferralBox {

	private static final int COMMON[][] = { { 4151, 1 }, { 11846, 1 }, { 11848, 1 }, { 11850, 1 }, { 11852, 1 }, { 11854, 1 }, { 11856, 1 }, { 20072, 1 }
	, { 6585, 1 }, { 6568, 1 }, { 11730, 1 }, { 13887, 1 }, { 13893, 1 }, { 13899, 1 }, { 13905, 1 }, { 13884, 1 }, { 13890, 1 }, { 13896, 1 }
	, { 13902, 1 }, { 13870, 1 }, { 13873, 1 }, { 13876, 1 }, { 13879, 100 }, { 13858, 1 }, { 13861, 1 }, { 13864, 1 }, { 13867, 1 }, { 2572, 1 }
	, { 7462, 1 }, { 10551, 1 }, { 18349, 1 }, { 18351, 1 }, { 18353, 1 }, { 18355, 1 }, { 18357, 1 }, { 18359, 1 }, { 18361, 1 }, { 18363, 1 }
	, { 10887, 1 }, { 15492, 1 }, { 23679, 1 }, { 23680, 1 }, { 23681, 1 }, { 23682, 1 }, { 23683, 1 }, { 23685, 1 }, { 23686, 1 }, { 23687, 1 }
	, { 23688, 1 }, { 23697, 1 }, { 23698, 1 }, { 23699, 1 }, { 23700, 1 }};
	private static int reward, r, c, q, vr;

	private static void handle(Player p) {
		if (!p.getInventory().hasFreeSlots()) {
			p.sendMessage("You need some inventory space first!");
			return;
		}
		p.getInventory().deleteItem(29117, 1);
		p.setNextAnimation(new Animation(547));
		reward(p);

	}

	private static void reward(Player p) {
			c = Utils.random(COMMON[0][1]);
			r = Utils.random(COMMON.length);
			q = COMMON[r][1];
			reward = COMMON[r][0];
			p.getInventory().addItemDrop(COMMON[r][0], q);
			return;
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
		case 29117:
			handle(p);
			return true;
		}
		return false;
	}
}