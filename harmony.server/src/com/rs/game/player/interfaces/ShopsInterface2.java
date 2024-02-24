package com.rs.game.player.interfaces;

import com.rs.game.WorldTile;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.content.Magic;
import com.rs.utils.Colors;
import com.rs.utils.ShopsHandler;

public class ShopsInterface2 {
	
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
				"Construction");
		player.getPackets().sendIComponentText(INTER, 36,
				"Achievement Capes");
		player.getPackets().sendIComponentText(INTER, 32,
				"Achievement Capes 2");
		player.getPackets().sendIComponentText(INTER, 37,
				"Fishing Supplies");
		player.getPackets().sendIComponentText(INTER, 33,
				"Herblore Supplies");
		player.getPackets().sendIComponentText(INTER, 38,
				"Crafting Supplies");
		player.getPackets().sendIComponentText(INTER, 34,
				"Summoning Shop");
		player.getPackets().sendIComponentText(INTER, 39,
				"Summoning Shop 2");
		player.getPackets().sendIComponentText(INTER, 35,
				"Summoning Scrolls");
		player.getPackets().sendIComponentText(INTER, 40,
				"Next Page");
	
}


public static boolean handleButtons(Player player, int componentId) {
	int npcId = 0;
	switch (componentId) {
	case 68: //Teleport location button handler.
		ShopsHandler.openShop(player, 10);
		player.getTemporaryAttributtes().remove("shopsinterface1");
		return true;
	case 73:
		ShopsHandler.openShop(player, 11);
		player.getTemporaryAttributtes().remove("shopsinterface1");
		return true;
	case 67:
		ShopsHandler.openShop(player, 12);
		player.getTemporaryAttributtes().remove("shopsinterface1");
		return true;
	case 72:
		ShopsHandler.openShop(player, 13);
		player.getTemporaryAttributtes().remove("shopsinterface1");
		return true;
	case 66:
		ShopsHandler.openShop(player, 14);
		player.getTemporaryAttributtes().remove("shopsinterface1");
		return true;
	case 71:
		ShopsHandler.openShop(player, 15);
		player.getTemporaryAttributtes().remove("shopsinterface1");
		return true;
	case 65:
		ShopsHandler.openShop(player, 16);
		player.getTemporaryAttributtes().remove("shopsinterface1");
		return true;
	case 70:
		ShopsHandler.openShop(player, 21);
		player.getTemporaryAttributtes().remove("shopsinterface1");
		return true;
	case 64:
		ShopsHandler.openShop(player, 17);
		player.getTemporaryAttributtes().remove("shopsinterface1");
		return true;
	case 69:
		player.getTemporaryAttributtes().remove("shopsinterface1");
		player.getInterfaceManager().closeInterfaceCustom();
		ShopsInterface3.sendInterface(player);
		player.getTemporaryAttributtes().put("shopsinterface2", "true");
		return true;
		}
	return false;
}

}
