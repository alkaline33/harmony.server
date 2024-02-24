package com.rs.game.player.content;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.World;
import com.rs.game.player.Player;
import com.rs.utils.Utils;

/**
 * @author mr_Joopz @rune-server.org
 */
public class Rbox {

    private static final int COMMON[][] = {{29911, 1}, {29877, 1}, {29834, 1}, {29795, 1}, {29790, 1}, {29781, 1}, {29695, 1}, {1050, 1}
    , {29909, 1}, {29835, 1}, {29794, 1}, {29791, 1}, {29779, 1}, {1053, 1}, {1055, 1}, {1057, 1}
    , {29910, 1}, {29882, 1}, {29836, 1}, {29789, 1}, {29780, 1}, {29696, 1}, {29627, 1}, {29626, 1}, {1038, 1}, {1040, 1}, {1042, 1}, {1044, 1}, {1046, 1}, {1048, 1}};

    private static int reward, r, c, q;

    private static void handle(Player p) {
   //     r = Utils.random(10);
        p.getInventory().deleteItem(29519, 1);
        p.getPackets().sendSound(98, 0, 1);

        switch (r) {
            default:
                c = Utils.random(COMMON[0][1]);
                r = Utils.random(COMMON.length);
                q = COMMON[r][1];
			p.getInventory().addItemDrop(COMMON[r][0], COMMON[r][0] == (995) ? c : q);
                reward = COMMON[r][0];
                p.sm((String) "You open the box and find a " 
                        + getNameForItem() + "");
                World.sendWorldMessage("<col=FA8072>"+p.getDisplayName()+" has found a "+getNameForItem()+" inside a rare box!", false);
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
            case 29519:
                handle(p);
                return true;
        }
        return false;
    }
}