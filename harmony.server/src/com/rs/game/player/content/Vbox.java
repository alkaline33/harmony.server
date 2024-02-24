package com.rs.game.player.content;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.player.Player;
import com.rs.utils.Utils;

/**
 * @author HerBrightSkies @rune-server.org
 */
public class Vbox {

    private static final int COMMON[][] = {{995, 1000000}, {995, 2000000},{18831, 5},{398, 25},{14808, 1},{6571, 1},{4151, 1},{24300, 1},{989, 1},{24155, 1}};
    private static final int UNCOMMON[][] = {{995, 3000000},{18831, 12},{398, 50},{990, 3},{24300, 2},{1555, 1},{1556, 1},{1557, 1},{1558, 1},{1559, 1},{1560, 1}};
    private static final int RARE[][] = {{995, 5000000},{18831, 40},{6666, 1},{398, 100},{19784, 1},{15454, 1},{15449, 1},{15459, 1},{15464, 1}};
    private static final int VERY_RARE[][] = {{995, 25000000},{1050, 1},{1053, 1},{398, 250},{1055, 1},{1057, 1},{1053, 1},{990, 30},{18831, 200}};
    private static int reward, r, c, q;

    private static void handle(Player p) {
        r = Utils.random(40);
       // p.getInventory().deleteItem(18768, 1);
        p.getPackets().sendSound(98, 0, 1);

        switch (r) {
            case 1:
                r = Utils.random(VERY_RARE.length);
                q = VERY_RARE[r][1];
                p.getInventory().addItem(VERY_RARE[r][0], q);
                reward = VERY_RARE[r][0];
                p.sm("You open the box and find " + getGrammar() + " " + getNameForItem() + ", amazing!");
                break;
            case 2:
            case 3:
            case 16:
            case 25:
            case 32:
                r = Utils.random(RARE.length);
                q = RARE[r][1];
                p.getInventory().addItem(RARE[r][0], q);
                reward = RARE[r][0];
                p.sm("You open the box and find " + getGrammar() + " " + getNameForItem() + ", great.");
                break;
            case 4:
            case 5:
            case 31:
            case 30:
            case 39:
            case 22:
            case 8:
            case 13:
            case 11:
            case 6:
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

    public static boolean isBox(Player p) { 
                handle(p);
                return true;
    }
}