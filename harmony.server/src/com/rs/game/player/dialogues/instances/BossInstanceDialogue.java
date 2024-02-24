package com.rs.game.player.dialogues.instances;

import com.rs.game.player.dialogues.Dialogue;

public class BossInstanceDialogue extends Dialogue {

	public static final int NPC_ID = 3807;

	private int stage;

	@Override
	public void start() {
		sendOptionsDialogue("Select an option",
				"I'd like to create a boss instance",
				"I'd like to join a boss instance.", "What is a boss instance?");
		stage = 0;
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == 0) {
			if (componentId == OPTION_1) {
				player.getPackets().sendRunScript(109, "Enter the name of the boss:");
				player.getTemporaryAttributtes().put("create-instance-name", true);
				end();
			} else if (componentId == OPTION_2) {
				player.getPackets().sendRunScript(109, "Enter the owner's name:");
				player.getTemporaryAttributtes().put("join-instance-name", true);
				end();
			} else if (componentId == OPTION_3) {

				stage = 1;
				sendPlayerDialogue(9827, "What is this place?");
			} 
		} else if (stage == 1) {
			stage = 2;
			sendNPCDialogue(NPC_ID, 9827, "This is where you can have your own private room.");
		} else if (stage == 2) {
			stage = 3;
			sendNPCDialogue(NPC_ID, 9827, "Only people on your friends list can join you.");
		} else if (stage == 3) {
			stage = 4;
			sendNPCDialogue(NPC_ID, 9827, "You get an hour without being crashed!");
		} else if (stage == 4) {
			stage = 5;
			sendNPCDialogue(NPC_ID, 9827, "The following can be used;");
		} else if (stage == 5) {
			stage = 6;
			sendNPCDialogue(NPC_ID, 9827, "corp, bandos, sara, zammy, arma, bandos, kk, thunderous and kbd.");
		} else
			end();
	}

	@Override
	public void finish() {

	}

}