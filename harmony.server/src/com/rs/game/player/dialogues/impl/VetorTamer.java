package com.rs.game.player.dialogues.impl;

import com.rs.game.player.dialogues.Dialogue;

public class VetorTamer extends Dialogue {

	private int npcId = 885;

	@Override
	public void start() {
		// npcId = (Integer) parameters[0];
		sendEntityDialogue(IS_NPC, "Pet Tamer", 885, 9827, "Hello " + player.getDisplayName() + ", what would you like to discuss?");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		switch (stage) {
		case -1:
			stage = 0;
			sendOptionsDialogue(SEND_DEFAULT_OPTIONS_TITLE, "Pet Perks", "Pets", "Nothing");
			break;
		case 0:
			switch (componentId) {
			case 11:
				player.getDialogueManager().startDialogue("VeterinarianD");
				break;
			case 13:
				player.getDialogueManager().startDialogue("PetTamer");
				break;
			default:
				end();
				break;
			}
			break;
		default:
			end();
			break;
		}

	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub

	}

}
