package com.rs.game.player.dialogues.impl;

import com.rs.game.player.dialogues.Dialogue;

public class EmptyConfirmation extends Dialogue {

	/**
	 * 
	 * Author Mr_Joopz
	 * 
	 */

	public EmptyConfirmation() {
	}

	@Override
	public void start() {
		stage = 1;
		sendOptionsDialogue("Are you sure you wish to empty your inventory?", "Yes", "No");

	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == 1) {
			if (componentId == OPTION_1) {
				player.getInventory().reset();
				end();

			} else {
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				end();
			}
		}
	}

	@Override
	public void finish() {
	}

}
