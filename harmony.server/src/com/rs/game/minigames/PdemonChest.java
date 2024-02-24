package com.rs.game.minigames;

import com.discord.Discord;
import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.World;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.utils.Utils;

/**
 * @author Mr_joopz @rune-server.org
 */
public class PdemonChest {

    private static final int COMMON[][] = {{537, 10}, {2362, 43}, {2364, 31}, {1516, 62}, {1514, 52}, {6686, 50}, {3025, 50}, {1128, 18}, {1392, 32}};
    private static final int UNCOMMON[][] = {{452, 50}, {20429, 3}, {10176, 40}, {450, 61}, {15271, 113}, {995, 10000000}};
    private static final int RARE[][] = {{990, 5}, {7462, 1}, {995, 40000000}, {18831, 75}, {2572, 1}, {29728, 1}, {13899, 1}, {13905, 1}, {13902, 1}};
    private static final int VRARE[][] = {{995, 100000000},{29710, 1},{29711, 1},{29712, 1},{29713, 1},{29714, 1},{29715, 1}};
    private static int reward, r, c, q;
    private static int pet;

    private static void handle(Player p) {   	
    if (p.canlootpdemonchest != true) {
    	p.sendMessage("The chest refuses to open.");
    	return;
    }
    if (p.PdemonKills > 25 && p.gotpdemonpet != true) {
    	pet = Utils.random(50);
    	switch (pet) {
    	case 45:
    		 p.sm("Congratulations you found a Disco Demon.");
             World.sendWorldMessage("<img=7><col=ff8c38>News: "+p.getDisplayName()+" has found a Disco Demon inside the Party demons chest!", false);
				Discord.sendSimpleMessage("NEWS: " + p.getDisplayName() + " has found a Disco Demon inside the Party demons chest!");
            p.getInventory().addItem(29706, 1);
            p.gotpdemonpet = true;
             break;
             default:
            	 break;
    	}
    }
    	if (p.getInventory().getFreeSlots() < 4) {
    		p.sendMessage("You need at least 3 free inventory slots to use this chest");
    		return;
		}
        r = Utils.random(25);
		p.getInventory().addItem(29226, Utils.random(1000, 5000));
        p.canlootpdemonchest = false;
		p.PdemonKills++;
        switch (r) {
        case 1:
        	r = Utils.random(VRARE.length);
            q = VRARE[r][1];
            p.getInventory().addItem(VRARE[r][0], q);
            reward = VRARE[r][0];
            p.sm("You open the chest and find " + getGrammar() + " " + getNameForItem() + ", amazing.");
            World.sendWorldMessage("<img=7><col=ff8c38>News: "+p.getDisplayName()+" has found " + getGrammar() + " " + getNameForItem() + " inside the Party demons chest!", false);
			Discord.sendSimpleMessage("NEWS: " + p.getDisplayName() + " has found a " + getNameForItem() + " inside the Party demons chest!");
			p.PDgetDropLog().getContainer().add(new Item(reward, 1));
            break;
            case 2:
            case 3:
            case 8:
                r = Utils.random(RARE.length);
                q = RARE[r][1];
                p.getInventory().addItem(RARE[r][0], q);
                reward = RARE[r][0];
                p.sm("You open the chest and find " + getGrammar() + " " + getNameForItem() + ", great.");
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 9:
            case 10:
            case 0:
                r = Utils.random(UNCOMMON.length);
                q = UNCOMMON[r][1];
                p.getInventory().addItem(UNCOMMON[r][0], q);
                reward = UNCOMMON[r][0];
                p.sm("You open the chest and find " + getGrammar() + " " + getNameForItem() + ", cool.");
                break;
          default:
                c = Utils.random(COMMON[0][1]);
                r = Utils.random(COMMON.length);
                q = COMMON[r][1];
                p.getInventory().addItem(COMMON[r][0], COMMON[r][0] == (995) ? c : q);
                reward = COMMON[r][0];
                p.sm((String) "You open the chest and find " + (COMMON[r][0] == (995) ? c : getGrammar()) + " "
                        + getNameForItem() + ", " + (COMMON[r][0] == (995) ? "sweet!" : "Well done"));
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

    public static boolean handleObjects(Player p, int objectId) {
		if (objectId == 2079) {
		handle(p);
			return true;
		}
		return false;
	}
}