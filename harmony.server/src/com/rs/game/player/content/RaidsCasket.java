package com.rs.game.player.content;

import com.discord.Discord;
import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.World;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.utils.Colors;
import com.rs.utils.Utils;

/**
 * @author HerBrightSkies @rune-server.org
 */
public class RaidsCasket {

	/**
	 * TODO - add osrs items, add barrows & dwarf cannon set function - super box to
	 * be same with better chance
	 */

	private static final int COMMON[][] = { { 2677, 1 }, { 560, 3100 }, { 565, 1000 }, { 566, 1000 }, { 892, 1000 }, { 11212, 926 }, { 208, 164 }, { 210, 168 }, { 212, 154 }, { 214, 123 }, { 3052, 131 }, { 216, 119 }, { 2486, 146 }, { 218, 216 }, { 220, 153 }, { 5022, 3 }, { 454, 1000 }, { 445, 153 }, { 448, 1000 }, { 450, 129 }, { 452, 87 }, { 1624, 142 }, { 1622, 223 }, { 1620, 124 }, { 1618, 153 } };
	private static final int VERY_RARE[][] = { { 14484, 1 }, { 29472, 1 }, { 29459, 1 }, { 29436, 1 }, { 29435, 1 }, { 29434, 100 }, { 29433, 1 }, { 29432, 1 }, { 29431, 1 }, { 29430, 1 }, { 29429, 1 }, { 29428, 1 } };
	// need add kodai wand, armour, other rewards
	private static int reward, r, c, q, vr;

	private static void handle(Player p) {
		if (p.getInventory().getFreeSlots() < 3) {
			p.sendMessage(Colors.green + "You must have at least 3 free inventory spaces to open this!");
			return;
		}
		r = Utils.random(75);
		int roll = r;
		p.getInventory().deleteItem(29438, 1);
        switch (r) {
		case 0:
            r = Utils.random(VERY_RARE.length);
            q = VERY_RARE[r][1];
			p.getInventory().addItemDrop(VERY_RARE[r][0], q);
            reward = VERY_RARE[r][0];
            p.sm("You open the box and find " + getGrammar() + " " + getNameForItem() + "!");
			World.sendWorldMessage("<col=ff0000>" + p.getDisplayName() + " has found " + getGrammar() + " " + getNameForItem() + " in a Xeric casket!", false);
			Discord.sendSimpleMessage("NEWS: " + p.getDisplayName() + " has received a " + getNameForItem() + " from a Xeric casket!");
			p.XericgetDropLog().getContainer().add(new Item(reward, 1));
			break;
            default:
                c = Utils.random(COMMON[0][1]);
                r = Utils.random(COMMON.length);
                q = COMMON[r][1];
			p.getInventory().addItemDrop(COMMON[r][0], COMMON[r][0] == 995 ? c : q);
                reward = COMMON[r][0];
			p.sendMessage("You needed to roll a 0 to land a rare item. You rolled a " + roll + ".");
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
		case 29438:
			handle(p);
			return true;
		}
		return false;
	}
}