package com.rs.game.player.dialogues.confirmations;

import com.rs.game.player.dialogues.Dialogue;

public class TogConfirmation extends Dialogue {

	private int npcId = 1;

	@Override
	public void start() {
		// npcId = (Integer) parameters[0];
		sendNPCDialogue(npcId, 9827, "Hello, " + player.getUsername()
				+ ". This minigame requires level 92 slayer to complete and contains high level bosses.");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			sendOptionsDialogue("Are you sure you wish to enter?", "Yes!", "No thanks.");
			stage = 2;
		} else if (stage == 2) {
			if (componentId == OPTION_1) {
				player.getControlerManager().startControler("TrialOfTheGods");
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