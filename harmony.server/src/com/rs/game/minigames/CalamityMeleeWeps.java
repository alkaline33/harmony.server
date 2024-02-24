package com.rs.game.minigames;

import com.rs.game.player.Player;
import com.rs.utils.Utils;

/**
 * Randomised provider for The Calamity
 * @author Mr_Joopz
 */

public class CalamityMeleeWeps {
	
	private static final int[] CalaWeps = {1203,1207,1209,1211,1213,1215,1323,1325,1327,1329,1331,1333,4587,1293,1295,1297,1299,1301,1303,1305
		,1309,1311,1313,1315,1317,1319,7158,3192,3194,3196,3198,3200,3202,3204,4151,4718,4726,4747,4755,18349,18353
		,14484,11694,11696,11698,11700,4153,10887,11730,11716,15403,21371,32649,7806,7808,7809,29971,29972,29973,
			1420, 1424, 1426, 1428, 1430, 1432, 1434, 1363, 1365, 1367, 1369, 1371, 1373, 1377, 1239, 1241, 1243, 1245, 1247, 1249, 29334, 29337, 1249 };
	
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