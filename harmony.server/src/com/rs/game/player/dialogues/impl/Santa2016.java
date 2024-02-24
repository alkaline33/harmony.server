package com.rs.game.player.dialogues.impl;

import com.rs.game.player.content.hasClaimedSanta;
import com.rs.game.player.dialogues.Dialogue;

public class Santa2016 extends Dialogue {

	int npcId = 9400;
	public int starter = 0;

	@Override
	public void start() {
		String claimedMac = player.getMacAddress();
		if (hasClaimedSanta.HasClaimed(claimedMac)) {
			player.sendMessage("You have already claimed your reward from santa today, come back tomorrow!");
			return;
		}
		if (player.spoketosanta == true) {
			player.sendMessage("You have already claimed your reward from santa today, come back tomorrow!");
			return;
		}
		if (player.getInventory().getFreeSlots() < 1) {
			player.sendMessage("Please have a free slot in your inventory first.");
			return;
		}

		sendEntityDialogue(IS_NPC, "Santa", npcId, 9827, "Ho ho ho. Have i got something special for you indeed!");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		switch (stage) {
		case -1:
			stage = 0;
			sendOptionsDialogue(SEND_DEFAULT_OPTIONS_TITLE, "What is it Santa?!", "I don't care.");
			break;
		case 0:
			if (componentId == OPTION_2) {
				stage = 1;
				sendPlayerDialogue(9790, "Woopty do, i don't really care.");
			} else {
				stage = 2;
				sendPlayerDialogue(9845, "Please, tell me Santa! I've been good all year long!");
			}
			break;
		case 1:
			stage = -2;
			sendEntityDialogue(IS_NPC, "Santa", npcId, 9827, "Children....");
			break;
		case 2:
			stage = 3;
			sendEntityDialogue(IS_NPC, "Santa", npcId, 9827, "Ho, ho, ho. That you have not.");
			break;
		case 3:
			stage = 4;
			sendItemDialogue(33606, 1, "Santa hands you a present.");
			hasClaimedSanta.addClaimSanta(player.getMacAddress());
			player.getInventory().addItem(33606, 1);
			player.spoketosanta = true;
			break;
		case 4:
			end();
			break;
		default:
			end();
			break;
		}
	}

	@Override
	public void finish() {

	}

}
