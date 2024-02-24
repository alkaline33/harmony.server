package com.rs.game.minigames;

import com.rs.game.player.Player;
import com.rs.utils.Utils;

/**
 * Randomised provider for The Calamity
 * @author Mr_Joopz
 */

public class CalamityMeleeArmour {
	
	private static final int[] CalaArmour = {1153,1157, 29123, 1159,1161,1163,11335,1115,1119,1121,1123,1125,1127,14479,1067,1069,1071,1073,1077,1079,4087,
		4121,4123,4125,4127,4129,4131,11732,7453,7455,7456,7457,7458,7459,7461,7462,1704,6585,1729,1731,1725,6585,1052,
		1191,1193,1195,1197,1199,1201,24365,4716,4720,4722,4724,4728,4730,4753,4757,4759,4745,4749,4751,11724,11726,11728,
			29941, 29940, 29939, 28621, 6735, 6737, 29785, 10551, 10548, 10828, 21787, 13896, 13887, 13893, 3751, 6524, 13736, 13740, 13744, 11283, 20135, 20139, 20143, 20143 };
	
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
			if (CalaArmour[Utils.random(getLength() - 1)] == 29123) {
				p.connorcapfound = true;
				p.sendMessage("You have found Connor's Cap!");
			}
		
		}
	
	public static int getLength() {
		return CalaArmour.length;
	}
	
}