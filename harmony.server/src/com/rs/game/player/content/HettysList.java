package com.rs.game.player.content;

import com.rs.game.player.Player;

public class HettysList {
	
	public static void OpenList(Player player) {
		player.getInterfaceManager().sendInterface(124);
		player.getPackets().sendIComponentText(124, 1, "Items that Hetty requires.");
		player.getPackets().sendIComponentText(124, 2, "<col=ff0000>A bottle of blood,          "
				+ "          A spider's web, "
				+ "                      A skeleton's leg,"
				+ "                    An old skull.</col>");
		return;
	}

}
