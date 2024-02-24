package com.rs.game.player.dialogues.impl;

import com.rs.Settings;
import com.rs.game.player.dialogues.Dialogue;

public class DoubleXpLord extends Dialogue {

	private int npcId = 4949;
	@Override
	public void start() {
		//npcId = (Integer) parameters[0];
		sendNPCDialogue(
				npcId,
				9827,
				"Arggg! "
						+ player.getDisplayName()
						+ ", Looking to gain some extra experience, are we?");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			int fee = 175000000;
			sendOptionsDialogue("60 Minutes Double Xp", 
					"Pay 175M",
					"No, thanks.");
			stage = 2;
		} else if (stage == 2) {
			if (componentId == OPTION_1) {
				int fee = 175000000;
				if (player.getInventory().containsItem(995, fee)) {
					player.getInventory().deleteItem(995, fee);
					Settings.GpSyncAmount += fee;
					player.d60mxp1 += 60;
				stage = 3;
					return;
				} else {
					player.sendMessage("You need "+fee+" coins in your inventory to purchase 60 minutes of x2 xp!");
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
}

	@Override
	public void finish() {

	}

}