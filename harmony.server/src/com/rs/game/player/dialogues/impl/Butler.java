package com.rs.game.player.dialogues.impl;

import com.rs.game.player.dialogues.Dialogue;

public class Butler extends Dialogue {

	int npcId;

	@Override
	public void start() {
		npcId = (Integer) parameters[0];
		sendNPCDialogue(npcId, 9827, "Good day Sir, if you have any noted planks in your inventory, I can convert the noted planks into un-noted planks, Would you like me to convert some for you?" );
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			stage = 0;
			sendOptionsDialogue("Would you like some planks converted?",
					"Could convert these regular planks please.",
					"Could convert these Oak planks please.",
					"Could convert these Teak planks please.");
		} else if (stage == 0) {
			if (componentId == OPTION_1) {
				if (player.getInventory().containsItem(961, 27) && player.getInventory().getFreeSlots() > 26) {
					player.getInventory().deleteItem(961, 27);
					player.getInventory().addItem(960, 27);
				} 
				end();
			} else if (componentId == OPTION_2) {
				if (player.getInventory().containsItem(8779, 27) && player.getInventory().getFreeSlots() > 26) {
					player.getInventory().deleteItem(8779, 27);
					player.getInventory().addItem(8778, 27);
				} 
				end();
			} else if (componentId == OPTION_3) {
				if (player.getInventory().containsItem(8781, 27) && player.getInventory().getFreeSlots() > 26) {
					player.getInventory().deleteItem(8781, 27);
					player.getInventory().addItem(8780, 27);
				} 
				end();
			}
			end();
		}
	}

	@Override
	public void finish() {

	}

}
