package com.rs.game.player.content.chests;

import com.discord.Discord;
import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.World;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.utils.Colors;
import com.rs.utils.Utils;

public class TheatreofBloodCasket {



	private static final int COMMON[][] = { { 246, 57 }, { 1128, 4 }, { 5289, 3 }, { 5315, 3 }, { 1776, 234 }, { 560, 595 }, { 565, 600 }, { 1392, 17 }, { 450, 50 }, { 452, 50 }, { 454, 400 }, { 445, 250 }, { 208, 27 }, { 210, 55 }, { 212, 35 }, { 214, 48 }, { 218, 48 }, { 3052, 34 }, { 216, 49 }, { 2486, 24 }, { 220, 22 }, };
	private static final int VERY_RARE[][] = { { 29335, 1 }, { 29334, 1 }, { 29333, 1 }, { 29332, 1 }, { 29331, 1 }, { 29330, 1 }, { 29337, 1 }, { 2677, 1 }, { 29335, 1 } };
	// need add kodai wand, armour, other rewards
	private static int reward, r, c, q, vr;

	private static void handle(Player p) {
		if (p.getInventory().getFreeSlots() < 3) {
			p.sendMessage(Colors.green + "You must have at least 3 free inventory spaces to open this!");
			return;
		}
		r = Utils.random(75);
		int roll = r;
		p.getInventory().deleteItem(29328, 1);

        switch (r) {
		case 0:
            r = Utils.random(VERY_RARE.length);
            q = VERY_RARE[r][1];
            p.getInventory().addItem(VERY_RARE[r][0], q);
            reward = VERY_RARE[r][0];
            p.sm("You open the box and find " + getGrammar() + " " + getNameForItem() + "!");
			World.sendWorldMessage("<col=ff0000>" + p.getDisplayName() + " has found " + getGrammar() + " " + getNameForItem() + " in a Theatre of Blood casket!", false);
			Discord.sendSimpleMessage("NEWS: " + p.getDisplayName() + " has received a " + getNameForItem() + " from a Theatre of Blood casket!");
			if (reward != 2677) {
				p.BloodgetDropLog().getContainer().add(new Item(reward, 1));
			}
			break;
            default:
                c = Utils.random(COMMON[0][1]);
                r = Utils.random(COMMON.length);
                q = COMMON[r][1];
                p.getInventory().addItem(COMMON[r][0], COMMON[r][0] == 995 ? c : q);
                reward = COMMON[r][0];
			c = Utils.random(COMMON[0][1]);
			r = Utils.random(COMMON.length);
			q = COMMON[r][1];
			p.getInventory().addItem(COMMON[r][0], COMMON[r][0] == 995 ? c : q);
			reward = COMMON[r][0];
			c = Utils.random(COMMON[0][1]);
			r = Utils.random(COMMON.length);
			q = COMMON[r][1];
			p.getInventory().addItem(COMMON[r][0], COMMON[r][0] == 995 ? c : q);
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
		case 29328:
			handle(p);
			return true;
		}
		return false;
	}
}