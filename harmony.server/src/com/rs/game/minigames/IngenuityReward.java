package com.rs.game.minigames;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.World;
import com.rs.game.player.Player;
import com.rs.game.player.content.event.season.SeasonEvent;
import com.rs.utils.Utils;

/**
 * @author Mr_joopz @rune-server.org
 */
public class IngenuityReward {

    private static final int COMMON[][] = {{2364, 15},{2362, 21},{1514, 25},{1516, 34},{15271, 16},{384, 28},{10176, 40},{537, 30},{8783, 60},{270, 100},{1632, 100},{452, 20},{450, 25}};
    private static final int UNCOMMON[][] = {{23741, 1},{23745, 15},{23717, 1},{23721, 1},{23769, 1},{23774, 1},{23778, 1},{23814, 1},{23782, 1},{23810, 1},{23790, 1},{23806, 1},{23802, 1},{23798, 1},{23794, 1},{23786, 1},{23725, 1},{23729, 1},{23733, 1},{23737, 1},{23749, 1},{23753, 1},{23757, 1},{23761, 1},{23765, 1},};
    private static final int RARE[][] = {{14808, 1},{995, 10000000},{990, 5},{2677, 1},{12844, 1},{2922, 1},{2902, 1},{2932, 1},{2942, 1},{3799, 1},{6857, 1},{6859, 1},{6861, 1},{6863, 1},{7053, 1},{21445, 1},{7535, 1},{2406, 1},{7449, 1},{732, 1}};
    private static final int VRARE[][] = {{995, 50000000},{995, 10000000},{995, 20000000},{995, 30000000},{995, 40000000},{990, 10}};
    private static int reward, r, c, q;
    private static int pet;

    private static void handle(Player p) {
    if (p.canlootingenuitychest != true) {
    	p.sendMessage("The chest refuses to open.");
    	return;
    }
    
  
    	if (p.getInventory().getFreeSlots() < 4) {
    		p.sendMessage("You need at least 3 free inventory slots to use this chest");
    		return;
    	}
    	  if (p.ingenuitychestslooted > 50) {
    	    	pet = Utils.random(150);
    	    	switch (pet) {
    	    	case 45:
    	    		 p.sm("Congratulations you found a Ingenuity Jr.");
    	             World.sendWorldMessage("<img=7><col=ff8c38>News: "+p.getDisplayName()+" has found a Ingenuity Jr pet inside the Ingenuity chest!", false);
    	            p.getInventory().addItem(29704, 1);
    	            if (p.ingenuityjrachieved < 1) {
    	            	p.ingenuityjrachieved = p.ingenuitychestslooted;
    	            }
    	             break;
    	    	case 90:
    	   		 p.sm("Congratulations you found a Redeemer.");
    	            World.sendWorldMessage("<img=7><col=ff8c38>News: "+p.getDisplayName()+" has found a Redeemer pet inside the Ingenuity chest!", false);
    	           p.getInventory().addItem(29703, 1);
    	           if (p.redeemerachieved < 1) {
    	           	p.redeemerachieved = p.ingenuitychestslooted;
    	           }
    	            break;
    	             default:
    	            	 break;
    	    	}
    	    }
        r = Utils.random(50);
        p.canlootingenuitychest = false;
		p.ingenuitychestslooted++;
		//SeasonEvent.HandleActivity(p, "Ingenuity", 0);
        switch (r) {
        case 1:
        	r = Utils.random(VRARE.length);
            q = VRARE[r][1];
            p.getInventory().addItem(VRARE[r][0], q);
            reward = VRARE[r][0];
            p.sm("You open the chest and find " + getGrammar() + " " + getNameForItem() + ", amazing.");
            World.sendWorldMessage("<img=7><col=ff8c38>News: "+p.getDisplayName()+" has found " + getGrammar() + " " + getNameForItem() + " inside the Ingenuity chest!", false);
            break;
            case 2:
            case 3:
            case 8:
            case 36:
            case 24:
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
            case 31:
            case 47:
            case 22:
            case 18:
            case 29:
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
                p.getInventory().addItem(COMMON[r][0], COMMON[r][0] == 995 ? c : q);
                reward = COMMON[r][0];
                p.sm("You open the chest and find " + (COMMON[r][0] == 995 ? c : getGrammar()) + " "
                        + getNameForItem() + ", " + (COMMON[r][0] == 995 ? "sweet!" : "Well done"));
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
                return COMMON[r][1] == 1 ? "coin" : "coins";
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
		if (objectId == 3274) {
		handle(p);
			return true;
		}
		return false;
	}
}