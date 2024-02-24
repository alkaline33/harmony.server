package com.rs.game.player.content;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.World;
import com.rs.game.player.Player;
import com.rs.utils.Utils;

/**
 * @author mr_Joopz @rune-server.org
 */
public class DevansLimitedEditionRares {

    private static final int COMMON[][] = {{29507, 1}, {29508, 1}, {29509, 1}, {29510, 1}, {29511, 1}};

    private static int reward, r, c, q;

    private static void handle(Player p) {
   //     r = Utils.random(10);
        p.getInventory().deleteItem(29505, 1);
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
                World.sendWorldMessage("<col=FA8072>"+p.getDisplayName()+" has found a "+getNameForItem()+" inside a limited edition rare box!", false);
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
            case 29505:
                handle(p);
                return true;
        }
        return false;
    }
}