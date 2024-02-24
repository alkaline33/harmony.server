package com.rs.game.minigames;

import com.rs.game.player.Player;
import com.rs.utils.Utils;

/**
 * Randomised provider for The Calamity
 * @author Mr_Joopz
 */

public class CalamityMRArmour {
	
	private static final int[] CalaArmour = {579,577, 29123,2579,4091,4093,4101,4103,4111,4113,4712,4714,
		6107,7398,2890,9729,13858,13861,13864,22458,22462,22466,3385,3387,3389,3391,14497,14499,14501,
		6916,6918,6920,6922,6924,13738,18333,18334,18335,2413,4708,4712,4714,21793,20159,20163,20167,
		22482,22486,22490,28608,26334,26335,26353,26354,26352,1169,1063,1095,1129,1131,1167,3749,13870,
		13873,13876,1099,1135,2493,2495,2497,2499,2501,2503,2577,2581,11718,11720,11722,21790,20147,20151,
		20155,24379,24382,24382};
	
	public static void RandomArmourPick(final Player p){
		if (p.calamitykillpoints < 60) {
			p.sendMessage("You need 60 points to use this, you have "+p.calamitykillpoints+".");
			return;
		} else if (p.getInventory().getFreeSlots() < 1) {
			p.sendMessage("You need at least 1 free inventory slot to use this!");
			return;
		} else {
			p.sendMessage("You find yourself an armour piece.");
		}
			p.calamitykillpoints -= 60;
			p.getInventory().addItem(CalaArmour[Utils.random(getLength() - 1)], 1);
		
		}
	
	public static int getLength() {
		return CalaArmour.length;
	}
	
}