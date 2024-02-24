package com.rs.game.player.content;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.World;
import com.rs.game.player.Player;
import com.rs.utils.Utils;

/**
 * @author HerBrightSkies @rune-server.org
 */
public class ScaryBox {

	private static final int COMMON[][] = { { 22321, 1 }, { 29664, 1 }, { 29665, 1 }, { 29666, 1 }, { 29667, 1 }, { 29657, 1 }, { 29663, 1 }, { 29658, 1 }, { 29659, 1 }, { 29660, 1 }, { 29661, 1 }, { 11789, 1 }, 
    	{9920, 1}, {1959, 1}};
   // private static final int UNCOMMON[][] = {{15486, 1}, {2577, 1}, {2581, 1}};
   // private static final int RARE[][] = {{2572, 1}, {19784, 1}};
    private static int reward, r, c, q;

    private static void handle(Player p) {
   //     r = Utils.random(10);
        p.getInventory().deleteItem(29662, 1);
        p.getPackets().sendSound(98, 0, 1);

        switch (r) {
            default:
                c = Utils.random(COMMON[0][1]);
                r = Utils.random(COMMON.length);
                q = COMMON[r][1];
			p.getInventory().addItemDrop(COMMON[r][0], COMMON[r][0] == (995) ? c : q);
                reward = COMMON[r][0];
                World.sendWorldMessage("<col=660066>"+p.getDisplayName()+" has found a <col=000000>"+getNameForItem()+"</col><col=660066> in a Scary Box!", false);
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
            case 29662:
                handle(p);
                return true;
        }
        return false;
    }
}