package com.rs.game.player.dialogues.impl;

import com.rs.Settings;
import com.rs.game.player.dialogues.Dialogue;

public class Clan extends Dialogue {

	int npcId;

	@Override
	public void start() {
		//npcId = (Integer) parameters[0];
		sendNPCDialogue(npcId, 9827, "Welcome to the clan system of "+Settings.SERVER_NAME+"!" );
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			stage = 0;
			sendOptionsDialogue("What would you like to say?",
					"I'd like to create a clan.",
					"I'd like to visit a clan citadel.",
					"I'd like to know more about clans.");
		} else if (stage == 0) {
			if (componentId == OPTION_1) {
                              end();
					player.getDialogueManager().startDialogue("ClanCreation");
			} else if (componentId == OPTION_2) {
				end();
					player.getDialogueManager().startDialogue("CitadelEnterD");
			} else if (componentId == OPTION_3) {
				stage = 1;
				sendPlayerDialogue( 9827, "I'd like to know more about clans." );
			} else
				end();
		} else if (stage == 1) {
			stage = 2;
			sendNPCDialogue(npcId, 9827, "What would you like to know?" );
		} else if (stage == 2) {
			stage = 3;
			sendOptionsDialogue("What would you like to say?",
					"Why should I create a clan?",
					"How do I send messages to my clan mates?");
		} else if (stage == 3) {
			if (componentId == OPTION_1) {
				stage = 4;
				sendPlayerDialogue( 9827, "Why should I create a clan?" );
			} else if (componentId == OPTION_2) {
				stage = 5;
				sendPlayerDialogue( 9827, "How do I send messages to my clan mates??" );
			} else
				end();
		} else if (stage == 4) {
			stage = -2;
			sendNPCDialogue(npcId, 9827, "You will get access to the clan citadel",
					"and you will be able to fight in clan wars");
		} else if (stage == 5) {
			stage = -2;
			sendNPCDialogue(npcId, 9827, "To send a message to your clan mates",
					"use the symbol //.");
		} else
			end();
	}

	@Override
	public void finish() {

	}

}
