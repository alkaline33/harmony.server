package com.rs.game.player.dialogues.impl;

import com.rs.game.player.dialogues.Dialogue;

public class ValentineHeart extends Dialogue {

	public ValentineHeart() {
	}

	@Override
	public void start() {
		stage = 1;
		sendOptionsDialogue("Choose an override", "Cupid Bow (2000)",
				"Cupid Wings (1250)", "Nevermind");

	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == 1) {
			if (componentId == OPTION_1) {
				if (player.getInventory().containsItem(29537, 2000)) {
					player.getInventory().deleteItem(29537, 2000);
					player.cupidbowc = true;
					end();
					return;
				}
				else {
					player.getPackets().sendGameMessage("You need 2000 valentine hearts to do this."); //game message if you do not have 100 shard
					end();
				}
			} else if (componentId == OPTION_2) {
				if (player.getInventory().containsItem(29537, 1250)) {
					player.getInventory().deleteItem(29537, 1250);
					player.cupidwingsc = true;
					end();
					return;
				}
				else {
					player.getPackets().sendGameMessage("You need 1250 valentine hearts to do this."); //game message if you do not have 100 shard
					end();
				}
			} else if (componentId == OPTION_3) {
				
					end();
				}
		}
	}
		

	@Override
	public void finish() {
	}

}
