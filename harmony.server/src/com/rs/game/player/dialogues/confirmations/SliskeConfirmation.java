package com.rs.game.player.dialogues.confirmations;

import com.rs.game.WorldTile;
import com.rs.game.player.content.Magic;
import com.rs.game.player.dialogues.Dialogue;

public class SliskeConfirmation extends Dialogue {

	private int npcId = 1;
	@Override
	public void start() {
		//npcId = (Integer) parameters[0];
		sendNPCDialogue(
				npcId,
				9827,
				"Hello, "
						+ player.getUsername()
						+ ". This boss is in the wilderness. Upon death you will lose your items.");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			sendOptionsDialogue("Are you sure you wish to proceed?", 
					"Yes!",
					"No thanks.");
			stage = 2;
		} else if (stage == 2) {
			if (componentId == OPTION_1) {
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3365,
								3887, 0));
						player.getInterfaceManager().closeChatBoxInterface();
						player.getInterfaceManager().closeOverlay(true);
						player.getControlerManager().startControler("Wilderness");
					//	player.sendMessage("Every week sliske can be damaged with a different godsword.");
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