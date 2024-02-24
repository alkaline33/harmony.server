package com.rs.game.player.interfaces;

import com.rs.game.player.Player;
import com.rs.utils.ShopsHandler;

public class RewardsCustomInterface {


public static boolean handleButtons(Player player, int componentId) {
	switch (componentId) {
		case 1: //Teleport location button handler.
			player.getInterfaceManager().closeInterfaceCustom();
			return true;
		case 3:
			player.getPackets().sendInterSetItemsOptionsScript(3001, 3, 0, 40, 40, "");
			return true;
		}
	return false;
}

}
