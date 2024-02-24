package com.rs.game.minigames;

import com.rs.game.Animation;
import com.rs.game.player.Player;
import com.rs.game.player.content.event.season.SeasonEvent;
import com.rs.utils.Utils;

/**
 * Represents the chest on which the key is used.
 * @author 'Corey 2010 <MobbyGFX96@hotmail.co.uk>
 */

public class DcrystalChest {
	
	private static final int[] DCHEST_REWARDS = { 29425, 985, 2677, 987, 10176, 9470, 15484, 12140, 20077, 15259, 14808, 19335, 19337, 19338, 19339, 19340, 21776, 19336, 6571, 3265, 3204, 6739, 8971, 8970, 8969, 8968, 8967, 8966, 15503, 15505, 15507, 15509 };
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
			p.getInventory().addItem(995, Utils.random(82300));
			if (p.ckeyperk == true) {
				p.getInventory().addItem(DCHEST_REWARDS[Utils.random(getLength() - 1)], 1);
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
			p.getInventory().addItem(DCHEST_REWARDS[Utils.random(getLength() - 1)], 1);
			p.sendMessage("You find some treasure in the chest.");
			p.ckeyused ++;
			p.Drypoints += 50;
			SeasonEvent.HandleActivity(p, "Crystal Keys", 20);
			p.sendMessage("You gain 50 Harmony points for opening this chest.");
			//UpdateActivities.Activities(p, null , 7, 0, 0);
		
		}
	}
	
	public static int getLength() {
		return DCHEST_REWARDS.length;
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