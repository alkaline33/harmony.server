package com.rs.game.minigames;

import com.rs.game.Animation;
import com.rs.game.player.Player;
import com.rs.game.player.content.event.season.SeasonEvent;
import com.rs.utils.Utils;

/**
 * Represents the chest on which the key is used.
 * @author 'Corey 2010 <MobbyGFX96@hotmail.co.uk>
 */

public class CrystalChest {
	
	private static final int[] CHEST_REWARDS = { 29542, 29425, 1127, 2677, 1113, 1079, 1149, 6585, 1712, 269, 10400, 10402, 10404, 10406, 10408, 10410, 10412, 10414, 10416, 10418, 10420, 10422, 10424, 10426, 10428, 10430, 10432, 10434, 10436, 10438, 2615, 2623, 2617, 2625, 2619, 2627, 2622, 2629, 987, 985, 2677 };
	public static final int[] KEY_HALVES = { 985, 987 };
	public static final int KEY = 989;
	public static final int Animation = 881;
	private static int freekey;
	
	/**
	 * Represents the key being made.
	 * Using tooth halves.
	 */
	public static void makeKey(Player p){
		if (p.getInventory().containsItem(toothHalf(), 1)
				&& p.getInventory().containsItem(loopHalf(), 1)){
			p.getInventory().deleteItem(toothHalf(), 1);
			p.getInventory().deleteItem(loopHalf(), 1);
			p.getInventory().addItem(KEY, 1);
			p.sendMessage("You succesfully make a crytal key.");
		}
	}
	
	/**
	 * If the player can open the chest.
	 */
	public static boolean canOpen(Player p){
		if(p.getInventory().containsItem(KEY, 1) && p.getInventory().getFreeSlots() > 2){
			return true;
		}else{
			p.sendMessage("<col=FFFF00>This chest is locked. You must also have 3 free spaces.</col>");
			return false;
		}
	}
	
	/**
	 * When the player searches the chest.
	 */
	public static void searchChest(final Player p){
		if (canOpen(p)){
			p.sendMessage("You unlock the chest with your key.");
			p.getInventory().deleteItem(KEY, 1);
			p.setNextAnimation(new Animation(Animation));
			p.getInventory().addItem(995, Utils.random(8230));
			if (p.ckeyperk == true) {
				p.getInventory().addItem(CHEST_REWARDS[Utils.random(getLength() - 1)], 1);
				p.sendMessage("Your crystal luck perk has granted you an extra reward!");
			}
			freekey = Utils.random(9);
	    	switch (freekey) {
	    	case 0:
	    		 p.sm("Congratulations you found an extra key.");
	            // World.sendWorldMessage("<img=7><col=ff8c38>News: "+p.getDisplayName()+" has found a Disco Demon inside the Party demons chest!", false);
	            p.getInventory().addItem(989, 1);
	    	
	             break;
	             default:
	            	 break;
	    	}
			p.getInventory().addItem(CHEST_REWARDS[Utils.random(getLength() - 1)], 1);
			p.sendMessage("You find some treasure in the chest.");
			p.ckeyused ++;
			p.Drypoints += 50;
			SeasonEvent.HandleActivity(p, "Crystal Keys", 20);
			p.sendMessage("You gain 50 Harmony points for opening this chest.");
		//	 UpdateActivities.Activities(p, null , 7, 0, 0);
		}
	}
	
	public static int getLength() {
		return CHEST_REWARDS.length;
	}
	
	/**
	 * Represents the toothHalf of the key.
	 */
	public static int toothHalf(){
		return KEY_HALVES[0];
	}
	
	/**
	 * Represent the loop half of the key.
	 */
	public static int loopHalf(){
		return KEY_HALVES[1];
	}
	
}