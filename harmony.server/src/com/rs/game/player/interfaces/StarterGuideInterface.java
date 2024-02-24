package com.rs.game.player.interfaces;

import com.rs.game.player.Player;
import com.rs.utils.Colors;

public class StarterGuideInterface {

	/**
	 * 
	 * @author Connor
	 */

	public static void sendInterface(Player player) {
		int INTER = 560;
		player.getInterfaceManager().sendInterface(INTER);
		player.getPackets().sendIComponentText(INTER, 14, "" + Colors.white + "Would you like to view the Starter Guide?");
		player.getPackets().sendIComponentText(INTER, 15, "Yes please!");
		player.getPackets().sendIComponentText(INTER, 16, "No thanks.");

	}

	public static boolean handleButtons(Player player, int componentId) {
		int npcId = 0;
		switch (componentId) {
		case 15:
			player.getPackets().sendOpenURL("tehpkscape.webs.com");
			player.getInterfaceManager().closeInterfaceCustom();
			return true;
		case 16:
			player.getInterfaceManager().closeInterfaceCustom();
			return true;
		}
		return false;
	}

}
