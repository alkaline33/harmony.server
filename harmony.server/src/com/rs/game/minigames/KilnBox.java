package com.rs.game.minigames;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.player.Player;
import com.rs.utils.Utils;

/**
 * @author HerBrightSkies @rune-server.org
 */
public class KilnBox {

    private static final int COMMON[][] = {{23659, 1}, {29855, 1}, {29856, 1}};
   // private static final int UNCOMMON[][] = {{15486, 1}, {2577, 1}, {2581, 1}};
   // private static final int RARE[][] = {{2572, 1}, {19784, 1}};
    private static int reward, r, c, q;

    private static void handle(Player p) {
   //     r = Utils.random(10);
        p.getInventory().deleteItem(29854, 1);
        p.getPackets().sendSound(98, 0, 1);

        switch (r) {
            default:
                c = Utils.random(COMMON[0][1]);
                r = Utils.random(COMMON.length);
                q = COMMON[r][1];
                p.getInventory().addItem(COMMON[r][0], COMMON[r][0] == (995) ? c : q);
                reward = COMMON[r][0];
                p.sm((String) "You open the box and find a " 
                        + getNameForItem() + "");
                break;
        }
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
            case 29854:
                handle(p);
                return true;
        }
        return false;
    }
}