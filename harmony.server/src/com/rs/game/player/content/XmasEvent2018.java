package com.rs.game.player.content;

import com.rs.game.player.Player;

public class XmasEvent2018 {

	public static void HandleMainQuestPresent(int x, int y, Player player) {
		if (x == 3188 && y == 3401) {
			if (player.getInventory().contains(29280) || player.getBank().containsItem(29280)) {
				player.sendMessage("You have already collected the present for Santa!");
				return;
			}
			player.sendMessage("You find a present. You should take this to Santa once you have all 5!");
			player.getInventory().addItem(29280, 1);
			return;
		} else if (x == 3055 && y == 3362) {
			if (player.getInventory().contains(29279) || player.getBank().containsItem(29279)) {
				player.sendMessage("You have already collected the present for Santa!");
				return;
			}
			player.sendMessage("You find a present. You should take this to Santa once you have all 5!");
			player.getInventory().addItem(29279, 1);
			return;
		} else if (x == 3048 && y == 3235) {
			if (player.getInventory().contains(29278) || player.getBank().containsItem(29278)) {
				player.sendMessage("You have already collected the present for Santa!");
				return;
			}
			player.sendMessage("You find a present. You should take this to Santa once you have all 5!");
			player.getInventory().addItem(29278, 1);
			return;
		} else if (x == 3220 && y == 3248) {
			if (player.getInventory().contains(29277) || player.getBank().containsItem(29277)) {
				player.sendMessage("You have already collected the present for Santa!");
				return;
			}
			player.sendMessage("You find a present. You should take this to Santa once you have all 5!");
			player.getInventory().addItem(29277, 1);
			return;
		} else if (x == 2151 && y == 3868) {
			if (player.getInventory().contains(29276) || player.getBank().containsItem(29276)) {
				player.sendMessage("You have already collected the present for Santa!");
				return;
			}
			player.sendMessage("You find a present. You should take this to Santa once you have all 5!");
			player.getInventory().addItem(29276, 1);
			return;
		} else {
			player.sendMessage("This present isn't yours!");
		}
	}

}
