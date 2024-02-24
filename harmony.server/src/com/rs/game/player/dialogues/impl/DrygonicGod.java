package com.rs.game.player.dialogues.impl;

import com.rs.Settings;
import com.rs.game.player.dialogues.Dialogue;

public class DrygonicGod extends Dialogue {

	int npcId = 30064;

	@Override
	public void start() {
		sendNPCDialogue(npcId, 9827, "Lieutenant Commander, thank you for your service.  "
				+ "You clearly defended our honour enough to be here!"
				+ " What can i do for you?" );
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			stage = 0;
			sendOptionsDialogue("What would you like to say?",
					"What is this place?",
					"Why are you so much bigger than you were at Ingenuity?",
					"How long will the battle last?", 
					"Nothing.");
		} else if (stage == 0) {
			if (componentId == OPTION_1) {
				stage = 2;
				sendPlayerDialogue( 9827, "Where am i? Looks dead as hell." );
			} else if (componentId == OPTION_2) {
				stage = 3;
				sendPlayerDialogue( 9827, "You're like 50x the size of when i last saw you, why?" );
			} else if (componentId == OPTION_3) {
				stage = 4;
				sendPlayerDialogue( 9827, "When will the battle end?" );
			} else
				end();
		} else if (stage == 2) {
			sendNPCDialogue(npcId, 9827, "This is my stronghold. Impossible to come here, unless they support me. "
					+ "Through those tunnels are some Demi gods, goodluck defeating them.");
			stage = 5;
		} else if (stage == 3) {
			sendNPCDialogue(npcId, 9827, "This is the true me "+player.getDisplayName()+", I cannot walk your world in my full size"
					+ ", it can cause too much terror.");
	stage = 5;
		} else if (stage == 4) {
			sendNPCDialogue(npcId, 9827, "When you finish it.");
			stage = 5;
		} else if (stage == 5) {
			end();
		} else
			end();
	}

	@Override
	public void finish() {

	}

}
