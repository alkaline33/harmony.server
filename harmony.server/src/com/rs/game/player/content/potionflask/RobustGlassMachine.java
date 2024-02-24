package com.rs.game.player.content.potionflask;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.Animation;
import com.rs.game.World;
import com.rs.game.player.Player;
import com.rs.utils.Utils;

/**
 * @author Mr_joopz @rune-server.org
 */
public class RobustGlassMachine {

	public static int SANDSTONE = 23194;
	public static int FLASK = 23191;
	

    private static void handle(Player p) {   	
    	if (!p.getInventory().contains(SANDSTONE)) {
    		p.sendMessage("You must have at least 1 red sandstone to do this.");
    		return;
    	} else {
    		int amount = p.getInventory().getNumerOf(SANDSTONE);
    		if (amount >= 1) {
    			p.setNextAnimation(new Animation (884));
    			p.getInventory().deleteItem(SANDSTONE, amount);
    			p.getInventory().addItem(FLASK, amount);
    			p.sendMessage("You have created "+amount+" potion flasks.");
    			return;
    		} else {
    			p.sendMessage("An error has occured.");
    		}
    	}
    }

    
    public static boolean handleObjects(Player p, int objectId) {
		if (objectId == 67968) {
		handle(p);
			return true;
		}
		return false;
	}
}