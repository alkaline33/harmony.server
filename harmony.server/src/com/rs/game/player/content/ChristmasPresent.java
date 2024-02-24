package com.rs.game.player.content;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.World;
import com.rs.game.player.Player;
import com.rs.utils.Utils;

/**
 * @author Mr_Joopz @rune-server.org
 */
public class ChristmasPresent {

    private static final int COMMON[][] = {{995, 1000000},};
    private static final int UNCOMMON[][] = {{14595, 1},{14602, 1},{14603, 1},{14605, 1}};
    private static final int RARE[][] = {{26518, 1},{26517, 1}};
    private static final int VERY_RARE[][] = {{30412, 1}, {1050, 1}};
    private static int reward, r, c, q;

    private static void handle(Player p) {
        r = Utils.random(90);
        p.getInventory().deleteItem(33606, 1);

        switch (r) {
        case 12:
        case 30:
        case 75:
        case 66:
        case 88:
        case 91:
        	 if (p.snowmanc == true) {
        		  r = Utils.random(RARE.length);
                  q = RARE[r][1];
                  p.getInventory().addItem(RARE[r][0], q);
                  reward = RARE[r][0];
                  p.sm("You open the present and find " + getGrammar() + " " + getNameForItem() + ", great.");
                  break;
        	 } else{
        	 p.sm("You open the present and find the snowman outfit override!");
             World.sendWorldMessage(p.getDisplayName()+" has found the snowman outfit override!", false);
             p.snowmanc = true;
             break;
        	 }
            case 1:
                r = Utils.random(VERY_RARE.length);
                q = VERY_RARE[r][1];
                p.getInventory().addItem(VERY_RARE[r][0], q);
                reward = VERY_RARE[r][0];
                p.sm("You open the present and find " + getGrammar() + " " + getNameForItem() + ", amazing!");
                World.sendWorldMessage(p.getDisplayName()+" found a "+getNameForItem()+" inside a christmas present!", false);
                break;
            case 2:
            case 3:
            case 25:
            case 100:
            case 92:
            case 56:
            case 44:
            case 14:
                r = Utils.random(RARE.length);
                q = RARE[r][1];
                p.getInventory().addItem(RARE[r][0], q);
                reward = RARE[r][0];
                p.sm("You open the present and find " + getGrammar() + " " + getNameForItem() + ", great.");
                break;
            case 4:
            case 5:
            case 6:
            case 29:
            case 11:
            case 13:
            case 18:
            case 96:
            case 51:
            case 61:
            case 71:
            case 82:
            case 86:
            case 83:
                r = Utils.random(UNCOMMON.length);
                q = UNCOMMON[r][1];
                p.getInventory().addItem(UNCOMMON[r][0], q);
                reward = UNCOMMON[r][0];
                p.sm("You open the present and find " + getGrammar() + " " + getNameForItem() + ", that's ok.");
                break;
            default:
                c = Utils.random(COMMON[0][1]);
                r = Utils.random(COMMON.length);
                q = COMMON[r][1];
                p.getInventory().addItem(COMMON[r][0], COMMON[r][0] == (995) ? c : q);
                reward = COMMON[r][0];
                p.sm((String) "You open the present and find " + (COMMON[r][0] == (995) ? c : getGrammar()) + " "
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
            case 33606:
                handle(p);
                return true;
        }
        return false;
    }
}