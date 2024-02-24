package com.rs.game.player.dialogues.confirmations;

import com.rs.game.WorldTile;
import com.rs.game.player.content.Magic;
import com.rs.game.player.dialogues.Dialogue;

public class LavaWyrmConfirmation extends Dialogue {

	private int npcId = 1;

	@Override
	public void start() {
		// npcId = (Integer) parameters[0];
		sendNPCDialogue(npcId, 9827, "Hello, " + player.getUsername()
				+ ". This monster is in the wilderness and you will lose items upon death.");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			sendOptionsDialogue("Are you sure you wish to enter?", "Yes!", "No thanks.");
			stage = 2;
		} else if (stage == 2) {
			if (componentId == OPTION_1) {
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3061, 3825, 0));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				player.getControlerManager().startControler("Wilderness");
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