package com.rs.game.player.content;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.player.Player;
import com.rs.utils.Utils;

/**
 * @author HerBrightSkies @rune-server.org
 */
public class Mbox {

    private static final int COMMON[][] = {{11235, 2}, {6571, 2}, {989, 2}};
    private static final int UNCOMMON[][] = {{15486, 1}, {2577, 1}, {2581, 1}};
    private static final int RARE[][] = {{2572, 1}, {19784, 1}};
    private static int reward, r, c, q;

    private static void handle(Player p) {
        r = Utils.random(10);
        p.getInventory().deleteItem(6199, 1);
        p.getPackets().sendSound(98, 0, 1);

        switch (r) {
            case 2:
            case 1:
                r = Utils.random(RARE.length);
                q = RARE[r][1];
                p.getInventory().addItem(RARE[r][0], q);
                reward = RARE[r][0];
                p.sm("You open the box and find " + getGrammar() + " " + getNameForItem() + ", great.");
                break;
            case 3:
            case 4:
            case 5:
                r = Utils.random(UNCOMMON.length);
                q = UNCOMMON[r][1];
                p.getInventory().addItem(UNCOMMON[r][0], q);
                reward = UNCOMMON[r][0];
                p.sm("You open the box and find " + getGrammar() + " " + getNameForItem() + ", that's ok.");
                break;
            default:
                c = Utils.random(COMMON[0][1]);
                r = Utils.random(COMMON.length);
                q = COMMON[r][1];
                p.getInventory().addItem(COMMON[r][0], COMMON[r][0] == (995) ? c : q);
                reward = COMMON[r][0];
                p.sm((String) "You open the box and find " + (COMMON[r][0] == (995) ? c : getGrammar()) + " "
                        + getNameForItem() + ", " + (COMMON[r][0] == (995) ? "sweet!" : "better luck next time."));
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
            case 6199:
                handle(p);
                return true;
        }
        return false;
    }
}