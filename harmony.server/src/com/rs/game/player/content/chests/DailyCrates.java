package com.rs.game.player.content.chests;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.player.Player;
import com.rs.utils.Utils;

/**
 * @author Connor
 */
public class DailyCrates {

    private static final int CONSTRUCTION[][] = {{961, 100},{8779, 100},{8783, 100},{1539, 100},{8785, 2},{8787, 2},{3421, 2},{8791, 25},{435, 75}};
    private static final int HERBLORE[][] = {{222, 50},{224, 50},{226, 50},{9594, 50},{6694, 50},{12539, 200},{5973, 50},{232, 50},{240, 50},{6050, 50}};
    private static final int SUMMONING[][] = {{1938, 30},{9737, 30},{6287, 30},{8432, 30},{2151, 30},{1964, 30},{5954, 30},{1439, 30},{1441, 30},{1443, 30},{1445, 30},{10163, 30},{238, 30},{1116, 30},{1119, 30},{10819, 30}};
    private static final int FARMING[][] = {{5295, 10}, {5297, 10},{5298, 10},{5299, 10},{5300, 10},{5302, 10},{5301, 10},{5303, 10},{5304, 10},{5313, 6},{5314, 6},{5315, 6},{5316, 6}};
    private static final int CRAFTING[][] = {{1618, 100}, {1620, 100}, {1622, 100}, {1624, 100}, {6571, 1}, {1746, 50}, {2506, 50}, {2507, 50}, {2509, 50}, {6290, 50}};
    private static final int SMITHING[][] = {{448, 60},{450, 40},{452, 25},{454, 200},{2352, 50},{2360, 30},{2362, 20},{2364, 10}};
     private static int reward, r, c, q;

    private static void handleCon(Player p) {
        p.getInventory().deleteItem(25299, 1);
        p.getPackets().sendSound(98, 0, 1);
                r = Utils.random(CONSTRUCTION.length);
                q = CONSTRUCTION[r][1];
                p.getInventory().addItem(CONSTRUCTION[r][0], q);
                reward = CONSTRUCTION[r][0];
                p.sm("You open the box and find " + getGrammar() + " " + getNameForItem() + "!");
             return; 
    }
    private static void handleCraft(Player p) {
        p.getInventory().deleteItem(25301, 1);
        p.getPackets().sendSound(98, 0, 1);
                r = Utils.random(CRAFTING.length);
                q = CRAFTING[r][1];
                p.getInventory().addItem(CRAFTING[r][0], q);
                reward = CRAFTING[r][0];
                p.sm("You open the box and find " + getGrammar() + " " + getNameForItem() + "!");
             return; 
    }
    private static void handleFarm(Player p) {
        p.getInventory().deleteItem(25303, 1);
        p.getPackets().sendSound(98, 0, 1);
                r = Utils.random(FARMING.length);
                q = FARMING[r][1];
                p.getInventory().addItem(FARMING[r][0], q);
                reward = FARMING[r][0];
                p.sm("You open the box and find " + getGrammar() + " " + getNameForItem() + "!");
             return; 
    }
    private static void handleHerb(Player p) {
        p.getInventory().deleteItem(25305, 1);
        p.getPackets().sendSound(98, 0, 1);
                r = Utils.random(HERBLORE.length);
                q = HERBLORE[r][1];
                p.getInventory().addItem(HERBLORE[r][0], q);
                reward = HERBLORE[r][0];
                p.sm("You open the box and find " + getGrammar() + " " + getNameForItem() + "!");
             return; 
    }
    private static void handleSmith(Player p) {
        p.getInventory().deleteItem(25307, 1);
        p.getPackets().sendSound(98, 0, 1);
                r = Utils.random(SMITHING.length);
                q = SMITHING[r][1];
                p.getInventory().addItem(SMITHING[r][0], q);
                reward = SMITHING[r][0];
                p.sm("You open the box and find " + getGrammar() + " " + getNameForItem() + "!");
             return; 
    }
    private static void handleSumm(Player p) {
        p.getInventory().deleteItem(25309, 1);
        p.getPackets().sendSound(98, 0, 1);
                r = Utils.random(SUMMONING.length);
                q = SUMMONING[r][1];
                p.getInventory().addItem(SUMMONING[r][0], q);
                reward = SUMMONING[r][0];
                p.sm("You open the box and find " + getGrammar() + " " + getNameForItem() + "!");
             return; 
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
        return ItemDefinitions.getItemDefinitions(reward).getName().toLowerCase();
    }

    public static boolean isBox(int itemId, Player p) {
        switch (itemId) {
            case 25299:
            	handleCon(p);
                return true;
            case 25301:
            	handleCraft(p);
                return true;
            case 25303:
            	handleFarm(p);
                return true;
            case 25305:
            	handleHerb(p);
                return true;
            case 25307:
            	handleSmith(p);
                return true;
            case 25309:
            	handleSumm(p);
                return true;
        }
        return false;
    }
}