package com.rs.game.player.content;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.World;
import com.rs.game.player.Player;
import com.rs.utils.Colors;
import com.rs.utils.Utils;

/**
 * @author Mr Joopz @rune-server.org
 */
public class SantasPresent {

	private static final int COMMON[][] = { { 15426, 1 }, { 6861, 1 }, { 6860, 1 }, { 6858, 1 }, { 6856, 1 }, { 6857, 1 }, { 6859, 1 }, { 6861, 1 }, { 4671, 1 }, { 11951, 1 }, { 29625, 1 }, { 15420, 1 }, { 22985, 1 }, { 29857, 1 }, { 29858, 1 }, { 29875, 1 }, { 29876, 1 }, { 10507, 1 } };
	private static final int VERY_RARE[][] = { { 29617, 1 }, { 29607, 1 }, { 1050, 1 }, };
    private static int reward, r, c, q;

    private static void handle(Player p) {
		r = Utils.random(1000);
        p.getInventory().deleteItem(29606, 1);
        p.getPackets().sendSound(98, 0, 1);

        switch (r) {
            case 1:
                r = Utils.random(VERY_RARE.length);
                q = VERY_RARE[r][1];
                p.getInventory().addItem(VERY_RARE[r][0], q);
			World.sendIconWorldMessage(Colors.cyan + "" + p.getDisplayName() + " has just found a " + getNameForItem() + " inside Santa's present!", false);
                reward = VERY_RARE[r][0];
                p.sm("You open the box and find " + getGrammar() + " " + getNameForItem() + ". Happy Christmas!");
                break;
            default:
                c = Utils.random(COMMON[0][1]);
                r = Utils.random(COMMON.length);
                q = COMMON[r][1];
                p.getInventory().addItem(COMMON[r][0], COMMON[r][0] == (995) ? c : q);
                reward = COMMON[r][0];
                p.sm((String) "You open the box and find " + (COMMON[r][0] == (995) ? c : getGrammar()) + " "
                        + getNameForItem() + ", " + (COMMON[r][0] == (995) ? ". Happy Christmas!" : ". Happy Christmas!"));
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
            case 29606:
                handle(p);
                return true;
        }
        return false;
    }
}