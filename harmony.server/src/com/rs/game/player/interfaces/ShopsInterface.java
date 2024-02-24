package com.rs.game.player.interfaces;

import com.rs.game.player.Player;
import com.rs.utils.Colors;
import com.rs.utils.ShopsHandler;

public class ShopsInterface {
	
	/**
	 * 
	 * @author Connor
	 */

	
	public static void sendInterface(Player player) {
		int INTER = 72;
		player.getInterfaceManager().sendInterface(INTER);
		player.getPackets().sendIComponentText(INTER, 55,
				""+Colors.white+"Shops");
		player.getPackets().sendIComponentText(INTER, 31, "General Store");
		player.getPackets().sendIComponentText(INTER, 36,
				"Magic Store");
		player.getPackets().sendIComponentText(INTER, 32,
				"Weapon Store");
		player.getPackets().sendIComponentText(INTER, 37,
				"Range Store");
		player.getPackets().sendIComponentText(INTER, 33,
				"Range Store 2");
		player.getPackets().sendIComponentText(INTER, 38,
				"Skilling Store");
		player.getPackets().sendIComponentText(INTER, 34,
				"Armour Store");
		player.getPackets().sendIComponentText(INTER, 39, "Potions & Flasks");
		player.getPackets().sendIComponentText(INTER, 35,
				"Miscellaneous");
		player.getPackets().sendIComponentText(INTER, 40,
				"Next Page");
	
}


public static boolean handleButtons(Player player, int componentId) {
	int npcId = 0;
	switch (componentId) {
		case 68: //Teleport location button handler.
			ShopsHandler.openShop(player, 1);
			player.getTemporaryAttributtes().remove("shopsinterface");
			return true;
		case 73:
			player.getInterfaceManager().closeInterfaceCustom();
			player.getDialogueManager().startDialogue("MagicStore");
			player.getTemporaryAttributtes().remove("shopsinterface");
			return true;
		case 67:
			ShopsHandler.openShop(player, 4);
			player.getTemporaryAttributtes().remove("shopsinterface");
			return true;
		case 72:
			ShopsHandler.openShop(player, 5);
			player.getTemporaryAttributtes().remove("shopsinterface");
			return true;
		case 66:
			ShopsHandler.openShop(player, 19);
			player.getTemporaryAttributtes().remove("shopsinterface");
			return true;
		case 71:
			ShopsHandler.openShop(player, 6);
			player.getTemporaryAttributtes().remove("shopsinterface");
			return true;
		case 65:
			ShopsHandler.openShop(player, 7);
			player.getTemporaryAttributtes().remove("shopsinterface");
			return true;
		case 70:
			ShopsHandler.openShop(player, 8);
			player.getTemporaryAttributtes().remove("shopsinterface");
			return true;
		case 64:
			ShopsHandler.openShop(player, 9);
			player.getTemporaryAttributtes().remove("shopsinterface");
			return true;
		case 69:
			player.getTemporaryAttributtes().remove("shopsinterface");
			player.getInterfaceManager().closeInterfaceCustom();
			ShopsInterface2.sendInterface(player);
			player.getTemporaryAttributtes().put("shopsinterface1", "true");
			return true;
		}
	return false;
}

}
