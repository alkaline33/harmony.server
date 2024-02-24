package com.rs.game.player.interfaces;

import com.rs.game.player.Player;
import com.rs.utils.Colors;
import com.rs.utils.ShopsHandler;

public class ShopsInterface3 {
	
	/**
	 * 
	 * @author Connor
	 */

	
	public static void sendInterface(Player player) {
		int INTER = 72;
		player.getInterfaceManager().sendInterface(INTER);
		player.getPackets().sendIComponentText(INTER, 55,
				""+Colors.white+"Shops");
		player.getPackets().sendIComponentText(INTER, 31,
				"Farming Store");
		player.getPackets().sendIComponentText(INTER, 36,
				"Runecrafting Store");
		player.getPackets().sendIComponentText(INTER, 32,
				"Hunter Store");
		player.getPackets().sendIComponentText(INTER, 37,
				"Master Capes");
		player.getPackets().sendIComponentText(INTER, 33,
				"Pure Store");
		player.getPackets().sendIComponentText(INTER, 38,
				"Vote Point Store");
		player.getPackets().sendIComponentText(INTER, 34,
				"Player Wars Store");
		player.getPackets().sendIComponentText(INTER, 39,
				"Harmony Point Store");
		player.getPackets().sendIComponentText(INTER, 35,
				"Ironman Store");
		player.getPackets().sendIComponentText(INTER, 40,
				"Ironman Store 2");
	
}


public static boolean handleButtons(Player player, int componentId) {
	int npcId = 0;
	switch (componentId) {
		case 68:
			ShopsHandler.openShop(player, 22);
			player.getTemporaryAttributtes().remove("shopsinterface1");
			return true;
		case 73:
			ShopsHandler.openShop(player, 23);
			player.getTemporaryAttributtes().remove("shopsinterface1");
			return true;
		case 67:
			ShopsHandler.openShop(player, 27);
			player.getTemporaryAttributtes().remove("shopsinterface1");
			return true;
		case 72:
			ShopsHandler.openShop(player, 30);
			player.getTemporaryAttributtes().remove("shopsinterface1");
			return true;
		case 66:
			ShopsHandler.openShop(player, 32);
			player.getTemporaryAttributtes().remove("shopsinterface1");
			return true;
		case 71:
			ShopsHandler.openShop(player, 108);
			player.getTemporaryAttributtes().remove("shopsinterface1");
			return true;
		case 65:
			ShopsHandler.openShop(player, 109);
			player.getTemporaryAttributtes().remove("shopsinterface1");
			return true;
		case 70:
			ShopsHandler.openShop(player, 110);
			player.getTemporaryAttributtes().remove("shopsinterface1");
			return true;
		case 64:
			ShopsHandler.openShop(player, 102);
			player.getTemporaryAttributtes().remove("shopsinterface1");
			return true;
		case 69:
			ShopsHandler.openShop(player, 116);
			player.getTemporaryAttributtes().remove("shopsinterface1");
			return true;
		default:
			player.getTemporaryAttributtes().remove("shopsinterface2");
		break;
		}
	
	return false;
}

}
