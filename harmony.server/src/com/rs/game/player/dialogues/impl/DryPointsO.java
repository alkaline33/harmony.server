package com.rs.game.player.dialogues.impl;

import com.rs.game.player.dialogues.Dialogue;

public class DryPointsO extends Dialogue {

	public DryPointsO() {
	}

	@Override
	public void start() {
		stage = 1;
		sendOptionsDialogue("Remember Inventory space!", "100 Blue Charms (4K)",
				"Santa Hat (3M)");

	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == 1) {
			if (componentId == OPTION_1) {
				player.getDialogueManager().startDialogue("DryPoints");
				}
			} else if (componentId == OPTION_2) {
				if (!player.donator == true) {
					player.getDialogueManager().startDialogue("DryPointsD");
				}
		        end();
			}
		}
		

	@Override
	public void finish() {
	}

}
