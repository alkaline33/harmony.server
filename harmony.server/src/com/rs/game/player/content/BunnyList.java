package com.rs.game.player.content;

import com.rs.game.player.Player;

public class BunnyList {
	
	public static void OpenList(Player player) {
		player.getInterfaceManager().sendInterface(124);
		player.getPackets().sendIComponentText(124, 1, "Locations of Eggs for Easter Bunny;");
		player.getPackets().sendIComponentText(124, 2, "<col=00ff00>Aubury in Varrock.<br><col=00ff00>Silif in Falador.<br><col=00ff00>Gjalp in Edgeville.<br><col=00ff00>Dunstan in Burthorpe.<br><col=00ff00>Ranael in Al Kharid.");
		return;
	}

}
