package com.rs.utils;

import com.rs.game.StarterMap;
import com.rs.game.player.Player;

public class Starter {

	public static final int MAX_STARTER_COUNT = 1;
	
	private static int amount = 100000;

	public static void appendStarter(Player player) {
		String ip = player.getSession().getIP();
		int count = StarterMap.getSingleton().getCount(ip);
		player.getStarter = true;
		if (count >= MAX_STARTER_COUNT) {
			return;
		}
		
		player.getInventory().addItem(995, amount);

		player.getHintIconsManager().removeUnsavedHintIcon();
		player.getMusicsManager().reset();
		player.getCombatDefinitions().setAutoRelatie(false);
		player.getCombatDefinitions().refreshAutoRelatie();
		StarterMap.getSingleton().addIP(ip);
	}
}