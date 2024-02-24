package com.rs.game.player.dialogues.impl;

import com.rs.game.WorldTile;
import com.rs.game.player.content.Magic;
import com.rs.game.player.dialogues.Dialogue;

public class LeaveHouse extends Dialogue {


	@Override
	public void start() {
		sendOptionsDialogue("Select an option.",
				"Leave House.", "Reset Objects", "Remove Fireplace", "Build Altar", "Never mind.");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (componentId == OPTION_1) {
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2544, 3094, 0));
				end();
		} else if (componentId == OPTION_2) {
			if (player.inBuildMode == false) {
				player.getInterfaceManager().sendInterface(399);
			player.getControlerManager().startControler("HouseControler");
			} else {
				player.out("You cannot do this in build mode.");
			}
		end();
		} else if (componentId == OPTION_3) {
			player.firePlace = 0;
			end();
			player.out("Re-enter your house for the changes to take effect.");
		} else if (componentId == OPTION_4) {
			//player.getDialogueManager().startDialogue("MakeAltar");
			player.executeAltar();
		} else if (componentId == OPTION_5) {
		end();
		}
			end();
		}

	

	@Override
	public void finish() {

	}

}
