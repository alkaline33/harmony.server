package com.rs.game.player.interfaces;

import com.rs.game.player.Player;

/**
 * 
 * @author Mr_Joopz
 *
 */

public class UpdateInterface {

	public static void SendInterface(Player player) {
		player.getInterfaceManager().sendInterface(712);
		player.getPackets().sendIComponentText(712, 1, "New Update Alert!");
		player.getPackets().sendIComponentText(712, 2, "We have a new ::home location! Check it out and give us feedback!"
				+ " Our tutorial has been updated and an AFK tree has been added south of the bank at home!");
		player.getPackets().sendIComponentText(712, 3, "~Harmony Developers");
		//player.getPackets().sendIComponentText(712, 6, "Magic kiln cape no longer degrades.");
		//player.getPackets().sendIComponentText(712, 7, "Changed Corrupted elite achievement.");
		//player.getPackets().sendIComponentText(712, 8, "For the rest of updates, click here.");



	}

	public static void HandleButtons(Player player, int id) {
		switch (id) {
		case 2:
			player.getInterfaceManager().closeInterfaceCustom();
			break;
		case 8:
			player.getPackets().sendOpenURL("www.harmonyrsps.com");
			break;
		}
	}

}
