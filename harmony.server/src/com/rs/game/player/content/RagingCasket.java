package com.rs.game.player.content;

import com.discord.Discord;
import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.World;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.utils.Colors;
import com.rs.utils.Utils;

public class RagingCasket {

	private static final int COMMON[][] = { { 995, 3500000 }, { 995, 3500000 }, { 1514, 35 }, { 10176, 35 }, { 1520, 35 }, { 384, 35 }, { 15271, 35 }, { 332, 35 }, { 360, 35 }, { 11212, 35 }, { 886, 35 }, { 888, 35 }, { 890, 35 }, { 892, 35 }, { 537, 35 }, { 18831, 35 }, { 1437, 35 }, { 961, 35 }, { 8783, 35 }, { 8781, 35 }, { 2486, 35 }, { 220, 35 }, { 218, 35 }, { 206, 35 }, { 208, 35 }, { 210, 35 }, { 212, 35 }, { 214, 35 }, { 216, 35 }, { 1618, 35 }, { 1620, 35 }, { 1622, 35 }, { 1624, 35 }, { 1746, 35 }, { 2506, 35 }, { 2508, 35 }, { 2510, 35 }, { 314, 350 }, { 452, 35 }, { 439, 35 }, { 441, 35 }, { 443, 35 }, { 445, 35 }, { 448, 35 }, { 450, 35 }, { 454, 35 }, { 2364, 35 }, { 2352, 35 }, { 2354, 35 }, { 2356, 35 }, { 2358, 35 }, { 2360, 35 }, { 2362, 35 }, { 5304, 35 }, { 5315, 35 },
			{ 5321, 35 }, { 5316, 5 }, { 5294, 35 }, { 5295, 5 }, { 5296, 35 }, { 5297, 35 }, { 5298, 35 }, { 5299, 35 }, { 5300, 35 }, { 5301, 35 }, { 5302, 35 }, { 5303, 35 }, { 2435, 35 }, { 3025, 35 }, { 6686, 35 } };
	private static final int VERY_RARE[][] = { { 29367, 1 }, { 29358, 1 }, { 29359, 1 }, { 29360, 1 } };
	private static int reward, r, c, q, vr;

	private static void handle(Player p) {
		if (!p.getInventory().hasFreeSlots()) {
			p.sendMessage("You need some inventory space first!");
			return;
		}
		p.ragingcasketcount++;
		p.getInventory().deleteItem(29363, 1);
		p.getPackets().sendSound(98, 0, 1);
		reward(p);

	}

	private static void reward(Player p) {
		r = Utils.random(100);
		switch (r) {
		case 1:
			r = Utils.random(VERY_RARE.length);
			q = VERY_RARE[r][1];
			reward = VERY_RARE[r][0];
			p.getInventory().addItemDrop(VERY_RARE[r][0], q);
			World.sendIconWorldMessage(Colors.cyan + "" + p.getDisplayName() + " has found a " + getNameForItem() + " inside a Raging Chest!", false);
			Discord.sendSimpleMessage("NEWS: " + p.getDisplayName() + " has received a " + getNameForItem() + " from a Raging Chest!");
			p.TolgetDropLog().getContainer().add(new Item(reward, 1));
			break;
		default:
			c = Utils.random(COMMON[0][1]);
			r = Utils.random(COMMON.length);
			q = COMMON[r][1];
			reward = COMMON[r][0];
			p.getInventory().addItemDrop(COMMON[r][0], Utils.random(5, q));
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
		case 29363:
			handle(p);
			return true;
		}
		return false;
	}
}