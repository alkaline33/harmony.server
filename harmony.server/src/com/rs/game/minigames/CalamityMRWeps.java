package com.rs.game.minigames;

import com.rs.game.player.Player;
import com.rs.utils.Utils;

/**
 * Randomised provider for The Calamity
 * @author Mr_Joopz
 */

public class CalamityMRWeps {
	
	private static final int[] CalaWeps = {841,843,849,853,857,861,839,845,847,851,855,859,9174,9177
		,9179,9181,9183,9185,18357,24338,28437,28441,31733,1381,1383,1385,1387
		,4675,4710,6562,13867,15486,6908,6910,6912,6914,10150,28621 ,28617 ,22985,841,843,849,853,857,
			841, 843, 849, 853, 857, 841, 843, 849, 853, 25654, 18355, 857, 29472, 841 };
	
	public static void RandomWepPick(final Player p){
		if (p.calamitykillpoints < 100) {
			p.sendMessage("You need 100 points to use this, you have "+p.calamitykillpoints+".");
			return;
		} else if (p.getInventory().getFreeSlots() < 1) {
			p.sendMessage("You need at least 1 free inventory slot to use this!");
			return;
		} else 
			p.sendMessage("You find yourself a weapon.");
			p.calamitykillpoints -= 100;
			p.getInventory().addItem(CalaWeps[Utils.random(getLength() - 1)], 1);
		
		}
	
	public static int getLength() {
		return CalaWeps.length;
	}
	
}