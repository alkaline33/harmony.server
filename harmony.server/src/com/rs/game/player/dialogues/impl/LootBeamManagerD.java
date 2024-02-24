package com.rs.game.player.dialogues.impl;

import com.rs.game.player.dialogues.Dialogue;

public class LootBeamManagerD extends Dialogue {

	@Override
	public void start() {
		sendNPCDialogue(6537, 9827, "Hello there "+player.getDisplayName()+", would you like to set your loot beam?");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
		sendOptionsDialogue("What would you like to do?", "Change my lootbeam value", "Change my lootbeam");
		stage = 0;
		} else if (stage == 0) {
			if (componentId == OPTION_1) {
				sendPlayerDialogue(9827, "Change my lootbeam value please");
				stage = 1;
			}
			if (componentId == OPTION_2) {
				sendPlayerDialogue(9827, "Change my lootbeam please");
				stage = 4;
			}
		} else if (stage == 1) {
			sendOptionsDialogue("Pick A Loot Beam Price", "100K", "300K", "750K", "1 Million", "5 Million");
			stage = 2;
		} else if (stage == 4) {
			sendOptionsDialogue("Pick A Loot Beam", "Rainbow", "Cursed Angel", "Snow Cloud", "Golden Birds", "Nevermind");
			stage = 5;
		} else if (stage == 5) {
			if (componentId == OPTION_1) {
				if (player.LootBeam2 != true) {
					sendNPCDialogue(6537, 9827, "You must unlock this lootbeam first!");
					stage = 3;
					return;
				}
				sendNPCDialogue(6537, 9827, "Your lootbeam is now set to the Rainbow.");
				player.LooTbeam = 1;
				stage = 3;
			}
			if (componentId == OPTION_2) {
				sendNPCDialogue(6537, 9827, "Your lootbeam is now set to the Cursed Angel.");
				player.LooTbeam = 0;
				stage = 3;
			}
			if (componentId == OPTION_3) {
				if (player.LootBeam3 != true) {
					sendNPCDialogue(6537, 9827, "You must unlock this lootbeam first!");
					stage = 3;
					return;
				}
				sendNPCDialogue(6537, 9827, "Your lootbeam is now set to the Snow Cloud.");
				player.LooTbeam = 2;
				stage = 3;
			}
			if (componentId == OPTION_4) {
				if (player.LootBeam4 != true) {
					sendNPCDialogue(6537, 9827, "You must unlock this lootbeam first!");
					stage = 3;
					return;
				}
				sendNPCDialogue(6537, 9827, "Your lootbeam is now set to the Golden Birds.");
				player.LooTbeam = 3;
				stage = 3;
			}
			if (componentId == OPTION_5) {
				sendPlayerDialogue(9827, "Nevermind");
				stage = 3;
			}
		} else if (stage == 2) {
			if (componentId == OPTION_1) {
				sendNPCDialogue(6537, 9827, "Your loot beam is now set to; 100K.");
				player.setLootBeam = 100000;
				stage = 3;
			}
			if (componentId == OPTION_2) {
				sendNPCDialogue(6537, 9827, "Your loot beam is now set to; 300K.");
				player.setLootBeam = 300000;
				stage = 3;
			}
			if (componentId == OPTION_3) {
				sendNPCDialogue(6537, 9827, "Your loot beam is now set to; 750K.");
				player.setLootBeam = 750000;
				stage = 3;
			}
			if (componentId == OPTION_4) {
				sendNPCDialogue(6537, 9827, "Your loot beam is now set to; 1 Million.");
				player.setLootBeam = 1000000;
				stage = 3;
			}
			if (componentId == OPTION_5) {
				sendNPCDialogue(6537, 9827, "Your loot beam is now set to; 5 Million.");
				player.setLootBeam = 5000000;
				stage = 3;
			}
		} else if (stage == 3) {
			end();
		}
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub
		
	}
	
}