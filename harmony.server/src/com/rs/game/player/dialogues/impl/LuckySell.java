package com.rs.game.player.dialogues.impl;

import com.rs.game.player.dialogues.Dialogue;
import com.rs.utils.Utils;

public class LuckySell extends Dialogue {

	private int npcId = 29999;
	@Override
	public void start() {
		//npcId = (Integer) parameters[0];
		sendNPCDialogue(
				npcId,
				9827,
				"Hello, "
						+ player.getUsername()
						+ " I can cash up a lucky item, but you must only have one in your inventory.");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			sendOptionsDialogue("Cash up 1 lucky item?", 
					"Yes",
					"No");
			stage = 2;
		} else if (stage == 2) {
			if (componentId == OPTION_1) {
				int[] RandomItems = {4151, 6571, 11235, 15486, 989};
				  int i = Utils.getRandom(RandomItems.length);
				if (player.getInventory().containsOneItem(RandomItems[i])) {
					player.getInventory().deleteItem(RandomItems[i], 1);
				}
				//player.getInventory().deleteItem(RandomItems[i], 1);
				stage = 3;
				//end();
				player.sendMessage("Your item has been cashed up for 30M coins.");
				//	return;
				} else {
					player.sendMessage("You need to have only 1 lucky item in your inventory to cash out.");
					end();
					stage = 3;
					return;
				}
			} else if (stage == 2) {
			if (componentId == OPTION_2) {
				stage = 3;
				end();
					return;
			}
		} else if (stage == 3) {
			end();
		}

	}


	@Override
	public void finish() {

	}

}