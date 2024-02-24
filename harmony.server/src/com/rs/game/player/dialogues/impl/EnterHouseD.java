package com.rs.game.player.dialogues.impl;

import com.rs.game.player.dialogues.Dialogue;

public class EnterHouseD extends Dialogue {

	@Override
	public void start() {
			sendOptionsDialogue("What would you like to do?",
					"Go to your house.", "Go to your house (Building mode).", "Go to a friend's house.", "Never mind.");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		switch (componentId) {
		case OPTION_1:
		if (player.hasHouse) {
			player.getHouse().setBuildMode(false);
			player.getHouse().enterMyHouse();
			end();
		} else {
			player.getDialogueManager().startDialogue("SimpleMessage", "You must purchase a house in order to do this, You can buy a property from any Harmony Estate Agent.");
		}
		break;
		case OPTION_2:
			if (player.hasHouse) {
				player.getHouse().setBuildMode(true);
				player.getHouse().enterMyHouse();
				end();
		} else {
			player.getDialogueManager().startDialogue("SimpleMessage", "You must purchase a house in order to do this, You can buy a property from any Harmony Estate Agent.");
		}
			break;
		case OPTION_3:
			end();
			if (player.isIronman()) {
				player.sm("You may not visit the houses of other players in ironman mode, however, other players may still visit yours.");
				return;
			}
            player.getTemporaryAttributtes().put("teleto_house", true);
            player.getPackets().sendRunScript(109, "Enter Friends Name:");
		break;
		case OPTION_4:
			end();
			break;
		}
	}

	@Override
	public void finish() {

	}

}
