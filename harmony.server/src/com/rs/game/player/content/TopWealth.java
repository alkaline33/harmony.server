package com.rs.game.player.content;

import java.util.Arrays;
import java.util.Comparator;

import com.rs.Settings;
import com.rs.game.World;
import com.rs.game.player.Player;
import com.rs.utils.Utils;

public class TopWealth {

	/**
	 * @author Jessica <jessica_alvorg@hotmail.com>
	 * @Project Septox 718
	 */
	
	public static void displayTopWealth(Player player) {

		/**
		 * Sends the interface
		 */
		player.getInterfaceManager().sendInterface(275);

		/**
		 * Removes all the current texts
		 */
		for (int i = 0; i <= 309; i++) {
			player.getPackets().sendIComponentText(275, i, "");
		}

		/**
		 * Sends the title & 1st line
		 */
		player.getPackets().sendIComponentText(275, 1, "<col=00ff00>Wealthiest Players in "+Settings.SERVER_NAME+"");

		player.getPackets().sendIComponentText(275, 10,
				"<img=5><col=ff00000>Based on store value of; Bank, Equipment, Inventory & Money pouch.<img=5>");

		Object[] playerObjects = World.getPlayers().toArray();
		Player[] allPlayers = new Player[playerObjects.length];

		for (int i = 0; i < allPlayers.length; i++) {
			allPlayers[i] = (Player) playerObjects[i];
		}

		Arrays.sort(allPlayers, new Comparator<Player>() {
			@Override
			public int compare(Player p1, Player p2) {

				long wealth1 = 0;
				long wealth2 = 0;

				if (p1 != null) {
					wealth1 = p1.getTotalWealth();
				}
				if (p2 != null) {
					wealth2 = p2.getTotalWealth();
				}

				long difference = wealth1 - wealth2;

				if (difference > 0) {
					return 1;
				} else if (difference < 0) {
					return -1;
				} else {
					return 0;
				}

			}
		});

		int topListSize = allPlayers.length;

		for (int i = 0; i < topListSize; i++) {
			Player p = allPlayers[allPlayers.length - i - 1];
			String line = " " + p.getDisplayName() + " has <col=00ff00>" + Utils.format(p.getTotalWealth())
					+ "</col> wealth.";
			player.getPackets().sendIComponentText(275, 11 + i, line);
		}

	}

}