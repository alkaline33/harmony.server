package com.rs.game.player.content;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.World;
import com.rs.game.player.Player;
import com.rs.utils.Utils;

/**
 * @author HerBrightSkies @rune-server.org
 */
public class VoteBox {

    private static final int COMMON[][] = {{989, 2}, {5295, 2}, {204, 20}, {220, 20}, {5300, 2}, {5304, 2}};
    private static final int UNCOMMON[][] = {{2577, 1}, {2581, 1}, {29069, 3}, {29922, 1}};
    private static final int RARE[][] = {{15486, 1}, {6585, 1}, {29532, 1}, {29352, 1}};
    private static int reward, r, c, q;

    private static void handle(Player p) {
        r = Utils.random(149);
        p.getInventory().deleteItem(29596, 1);
        p.getPackets().sendSound(98, 0, 1);

        switch (r) {
            case 1:
                r = Utils.random(RARE.length);
                q = RARE[r][1];
                p.getInventory().addItem(RARE[r][0], q);
                reward = RARE[r][0];
                p.sm("You open the box and find " + getGrammar() + " " + getNameForItem() + ".");
                World.sendWorldMessage("<img=7><col=ff0000>" + p.getDisplayName() + " has found " + getGrammar() + " " + getNameForItem() + " in a Vote Box!", false);
                break;
            case 2:
            case 3:
            case 4:
                r = Utils.random(UNCOMMON.length);
                q = UNCOMMON[r][1];
                p.getInventory().addItem(UNCOMMON[r][0], q);
                reward = UNCOMMON[r][0];
                p.sm("You open the box and find " + getGrammar() + " " + getNameForItem() + ".");
                break;
            default:
                c = Utils.random(COMMON[0][1]);
                r = Utils.random(COMMON.length);
                q = COMMON[r][1];
                p.getInventory().addItem(COMMON[r][0], COMMON[r][0] == (995) ? c : q);
                reward = COMMON[r][0];
                p.sm((String) "You open the box and find " + (COMMON[r][0] == (995) ? c : getGrammar()) + " "
                        + getNameForItem() + ", " + (COMMON[r][0] == (995) ? "sweet!" : "."));
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
            case 29596:
                handle(p);
                return true;
        }
        return false;
    }
}