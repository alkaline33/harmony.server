package com.rs.game.player.content;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.player.Player;
import com.rs.utils.Utils;

/**
 * @author HerBrightSkies @rune-server.org
 */
public class Gchest {

    private static final int COMMON[][] = {{989, 1}, {2361, 10}, {2359, 13}, {1515, 31}, {23531, 1}};
	private static final int UNCOMMON[][] = { { 24342, 1 }, { 24340, 1 }, { 24346, 1 }, { 24344, 1 }, { 23752, 1 }, { 6199, 1 }, { 29866, 1 }, { 1513, 10 } };
    private static final int RARE[][] = {{2363, 10},{15271, 20}, {29866, 1}, {989, 4}}; //boss chest -black dye
    private static int reward, r, c, q;

    private static void handle(Player p) {
    	if (!p.getInventory().containsItem(2944, 1)) {
    		p.sendMessage("You need a golden key to use this chest!");
    		return;
    	}
        r = Utils.random(10);
        p.getInventory().deleteItem(2944, 1);
        p.getPackets().sendSound(98, 0, 1);

        switch (r) {
            case 2:
            case 1:
                r = Utils.random(RARE.length);
                q = RARE[r][1];
                p.getBank().addItem(RARE[r][0], q, true);
                reward = RARE[r][0];
               p.sm("<col=ff0000>You open the chest and find " + getGrammar() + " " + getNameForItem() + ", it has been added to your bank.</col>");
                break;
            case 3:
            case 4:
            case 5:
                r = Utils.random(UNCOMMON.length);
                q = UNCOMMON[r][1];
                p.getBank().addItem(UNCOMMON[r][0], q, true);
                reward = UNCOMMON[r][0];
               p.sm("<col=ff0000>You open the chest and find " + getGrammar() + " " + getNameForItem() + ", it has been added to your bank.</col>");
                break;
            default:
                c = Utils.random(COMMON[0][1]);
                r = Utils.random(COMMON.length);
                q = COMMON[r][1];
                p.getBank().addItem(COMMON[r][0], COMMON[r][0] == (995) ? c : q, true);
                reward = COMMON[r][0];
               p.sm((String) "<col=ff0000>You open the chest and find " + (COMMON[r][0] == (995) ? c : getGrammar()) + " "
                       + getNameForItem() + ", " + (COMMON[r][0] == (995) ? "it has been added to your bank." : "it has been added to your bank.</col>"));
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

    public static boolean isBox(int objectId, Player p) {
        switch (objectId) {
            case 378:
                handle(p);
                return true;
        }
        return false;
    }
}