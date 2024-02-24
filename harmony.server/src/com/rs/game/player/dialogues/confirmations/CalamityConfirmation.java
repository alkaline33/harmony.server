package com.rs.game.player.dialogues.confirmations;

import com.rs.game.minigames.TheCalamity;
import com.rs.game.player.dialogues.Dialogue;

public class CalamityConfirmation extends Dialogue {

	private int npcId = 1;
	@Override
	public void start() {
		//npcId = (Integer) parameters[0];
		sendNPCDialogue(
				npcId,
				9827,
				"Hello, "
						+ player.getUsername()
						+ ". This minigame involves many waves of monsters and point collecting. It is an UNSAFE minigame."
						+ "To leave, you must die. There is no other way out!");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			sendOptionsDialogue("Do you wish to proceed?", 
					"Yes!",
					"No thanks.");
			stage = 2;
		} else if (stage == 2) {
			if (componentId == OPTION_1) {
				TheCalamity.handleObjects(player, 42031);
				end();
				stage = 3;
					return;
				} else {
					end();
					stage = 3;
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