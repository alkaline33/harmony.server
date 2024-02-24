package com.rs.game.player.dialogues.impl;
import com.rs.game.player.dialogues.Dialogue;

public class SilverHawk extends Dialogue {

	@Override
	public void start() {
		sendOptionsDialogue("Do you wish to dissemble your boots for 50 silverhawk feathers?", "Yes.", "No.");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (componentId == OPTION_1) {
			if (player.getInventory().contains(29922)) {
				player.getInventory().deleteItem(29922, 1);
				player.getInventory().addItem(10176, 50);
				player.sendMessage("You have exchanged your boots for 50 feathers.");
				end();
			} else {
				end();
			}
		}
		end();
	}

	@Override
	public void finish() {

	}

}
