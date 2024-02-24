package com.rs.game.player.dialogues.impl;

import com.rs.game.player.dialogues.Dialogue;
import com.rs.utils.ShopsHandler;

public class DryaxionsRift extends Dialogue {

	private int npcId = 7765;

	@Override
	public void start() {
		//npcId = (Integer) parameters[0];
		sendNPCDialogue(
				npcId,
				9845,
				"Oi! Stop right there child! What do you want with the rift");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			sendOptionsDialogue("Xuan", 
					"What is this rift?",
					"What is dryaxions?",
					"Why can't I go in?",
					"Could I see your reward shop please");
			stage = 2;
		} else if (stage == 2) {
			if (componentId == OPTION_1) {
				sendNPCDialogue(npcId, 9845, "This rift is the most powerful of it's kind and will take you to a wealthy dimension!");
				stage = 3;
			}
			if (componentId == OPTION_4) {
				ShopsHandler.openShop(player, 22);
				end();
			}
			if (componentId == OPTION_3) {
				sendNPCDialogue(npcId, 9845,
						"The rift only opens every 4ish hours for a period of 3 minutes. Attempting to enter when it's closed would kill you.");
				stage = 3;
			}
			if (componentId == OPTION_2) {
				sendNPCDialogue(npcId, 9845,
						"Dryaxions involves mining and fishing and gives the player an opportunity to gain great experience and make some money. You can die during the minigame!");
				stage = 3;
			}
		//} else if (stage == 4) {
		//	if (componentId == OPTION_1) {
		//		sendNPCDialogue(npcId, 9845,
		//				"It's not all good though. If you stay in there for too long you will suffer and lose all of your items gained during the process. Goodluck!");
		//		stage = 3;
		//		end();
		//	}
		} else if (stage == 3) {
			end();
		}
}

	@Override
	public void finish() {

	}

}