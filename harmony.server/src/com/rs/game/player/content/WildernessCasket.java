package com.rs.game.player.content;

import com.discord.Discord;
import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.World;
import com.rs.game.player.Player;
import com.rs.utils.Colors;
import com.rs.utils.Utils;

public class WildernessCasket {

	private static final int COMMON[][] = { { 1392, 60 }, { 9242, 240 }, { 9245, 130 }, { 12163, 35 }, { 12160, 70 }, { 6571, 1 }, { 537, 20 }, { 23400, 50 }, { 1754, 40 }, { 1752, 40 }, { 1750, 40 }, { 1748, 40 }, { 995, 1000000 }, { 995, 1500000 }, { 995, 2000000 }, { 995, 2500000 }, { 2677, 1 }, { 990, 2 }, { 5313, 2 }, { 5314, 2 }, { 5315, 2 }, { 5316, 2 }, { 5295, 2 }, { 5297, 2 }, { 5298, 2 }, { 5299, 2 }, { 5300, 2 }, { 5301, 2 }, { 5302, 2 }, { 5303, 2 }, { 5304, 2 }, { 2360, 20 }, { 2362, 20 }, { 2364, 20 }, { 1514, 40 }, { 1516, 40 }, { 1518, 40 }, { 2, 200 } };
	private static final int VERY_RARE[][] = { { 29368, 1 }, { 29367, 1 }, { 29367, 1 }, { 29367, 1 } };
	private static int reward, r, c, q, vr;

	private static void handle(Player p) {
		p.getInventory().deleteItem(29369, 1);
		p.getPackets().sendSound(98, 0, 1);
		reward(p);

	}

	private static void reward(Player p) {
		r = Utils.random(120);
		switch (r) {
		case 1:
			r = Utils.random(VERY_RARE.length);
			q = VERY_RARE[r][1];
			reward = VERY_RARE[r][0];
			p.getInventory().addItemDrop(VERY_RARE[r][0], q);
			p.sm("You open the box and find " + getGrammar() + " " + getNameForItem() + "!");
			World.sendIconWorldMessage(Colors.brown + "" + p.getDisplayName() + " has found a " + getNameForItem() + " inside a Wilderness casket!", false);
			Discord.sendSimpleMessage("NEWS: " + p.getDisplayName() + " has received a " + getNameForItem() + " from a Wilderness casket!");
			break;
		default:
			c = Utils.random(COMMON[0][1]);
			r = Utils.random(COMMON.length);
			q = COMMON[r][1];
			reward = COMMON[r][0];
			// if (loop == 10) {
			p.getInventory().addItemDrop(COMMON[r][0], q);
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
		case 29369:
			handle(p);
			return true;
		}
		return false;
	}
}