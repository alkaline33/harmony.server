package com.rs.game.player.dialogues.confirmations;

import com.rs.game.player.dialogues.Dialogue;

public class EternalGloryActivationD extends Dialogue {

	@Override
	public void start() {
		sendOptionsDialogue("Activating this will make the amulet <col=ff0000>untradeable</col>! Do you wish to proceed?", "Yes!", "No thanks.");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			if (componentId == OPTION_1) {
			if (player.getInventory().contains(29186)) {
				player.getInventory().deleteItem(29186, 1);
				player.getInventory().addItem(29185, 1);
					player.sendMessage("You can now use your amulet to teleport to GwD without killcount & sheaf island (once per day)!");
					end();
				} else {
					player.sendMessage("You don't seem to have the amulet anymore.");
					end();
			}
			} else {
				end();
			}
		}
	}

	@Override
	public void finish() {

	}

}