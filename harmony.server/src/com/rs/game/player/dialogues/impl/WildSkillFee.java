package com.rs.game.player.dialogues.impl;

import com.rs.Settings;
import com.rs.game.WorldTile;
import com.rs.game.player.dialogues.Dialogue;

public class WildSkillFee extends Dialogue {

	private int npcId = 9032;

	@Override
	public void start() {
		//npcId = (Integer) parameters[0];
		sendNPCDialogue(
				npcId,
				9827,
				"Hey "
						+ player.getUsername()
						+ ", I'm the Wild Skilling Guard. You must pay 100,000 coins to enter here.");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			sendOptionsDialogue("Pay Fee?", 
					"Yes",
					"No");
			stage = 2;
		} else if (stage == 2) {
			if (componentId == OPTION_1) {
				if(player.getInventory().containsItem(995, 100000)) {		
					player.getInventory().deleteItem(995, 100000);
					Settings.GpSyncAmount += 100000;
					sendNPCDialogue(
							npcId,
							9790,
							"I will teleport you in.");
					player.setNextWorldTile(new WorldTile(3187, 3940, 0));
					player.enterwskilling ++;
					end();
					return;
			} else {
				sendNPCDialogue(
						npcId,
						9790,
						"You need 100,000 coins in your inventory to enter!.");
				end();
			}
		} else if (stage == 2) {
			if (componentId == OPTION_2) {
			end();
			}
		}
		}
}
	

	@Override
	public void finish() {

	}

}