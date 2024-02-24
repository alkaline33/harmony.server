package com.rs.game.player.dialogues.impl;

import com.rs.Settings;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.game.player.quests.impl.EasterEvent;

/**
 * Queen of Snow - Easter Event
 * 
 **/

public class QueenOfSnow extends Dialogue {

	private int npcId;

	@Override
	public void start() {
		npcId = (Integer) parameters[0];
		if (player.startedEasterEvent == false) {
			sendPlayerDialogue(9827, "Are you in need of any help?");
		} else if (player.inProgressEasterEvent == true) {
			sendNPCDialogue(npcId, 9827, "Have you retrieved the keys?");
			stage = 20;
		} else if (player.completedEasterEvent == true) {
			sendNPCDialogue(npcId, 9827,"Happy Easter!");
			stage = 10;
		}
	}


	@Override
	public void run(int interfaceId, int componentId) {
		switch (stage) {
		case -1:
			sendNPCDialogue(npcId, 9827, "First thing first, Happy easter!");
			stage = 0;
			break;
		case 0:
			sendOptionsDialogue(SEND_DEFAULT_OPTIONS_TITLE,
					"Oh, yeah! Happy Easter!",
					"Merry Christmas!",
					"Nevermind.");
			stage = 1;
			break;
		case 1:
			if (componentId == OPTION_1) {
				sendPlayerDialogue(9827, "Oh, my apologies! Happy Easter!");
				stage = 2;
			} else if (componentId == OPTION_2) {
				sendPlayerDialogue(9827, "Merry Christmas!");
				stage = 15;
			} else if (componentId == OPTION_3) {
				sendPlayerDialogue(9827, "Nevermind.");
				stage = 18;
			}
			break;
		case 2:
			sendNPCDialogue(npcId, 9827, "Why thank you. Since you asked, it does seem that I am in need of assistance.");
			stage = 3;
			break;
		case 3:
			sendNPCDialogue(npcId, 9827, "The keys of Spring have been stolen by Evil Trees so now, snow is covering "+Settings.SERVER_NAME+".");
			stage = 4;
			break;
		case 4:
			sendNPCDialogue(npcId, 9827, "These keys unlock a magical book which when opened, shines a warming light that melts snow");
			stage = 5;
			break;
		case 5:
			sendNPCDialogue(npcId, 9827, "and causes flowers to blossom every year. Without these keys, we would spend many more months cold!");
			stage = 6;
			break;
		case 6:
			sendNPCDialogue(npcId, 9827, "These Evil Trees have now spread out all over "+Settings.SERVER_NAME+".",
					"Could you get these keys back for me?");
			stage = 7;
			break;
		case 7:
			sendOptionsDialogue(SEND_DEFAULT_OPTIONS_TITLE,
					"Of course!",
					"No thanks.");
			stage = 8;
			break;
		case 8:
			if (componentId == OPTION_1) {
				sendPlayerDialogue(9827, "Of Course!");
				stage = 11;
			} else if (componentId == OPTION_2) {
				sendPlayerDialogue(9827, "No thanks.");
				stage = 9;
			}
			break;
		case 9:
			sendNPCDialogue(npcId, 9827, "Well if you aren't interested in saving Spring, please leave in the east portal.");
			stage = 10;
			break;
		case 10:
			end();
			break;
		case 11:
			sendNPCDialogue(npcId, 9827, "Thank you, I wish you the best of luck!");
			EasterEvent.handleProgressQuest(player);
			stage = 12;
			break;
		case 12:
			end();
			break;
		case 15:
			sendNPCDialogue(npcId, 9827, "What? This isn't Christmas...");
			stage = 16;
			break;
		case 16:
			sendNPCDialogue(npcId, 9827, "It's Easter!!");
			stage = 17;
			break;
		case 17:
			end();
			break;
		case 18:
			end();
			break;
		case 19:
			end();
			break;
		case 20:
			if (!player.getInventory().containsItem(20730, 1)
			|| !player.getInventory().containsItem(20731, 1)
			|| !player.getInventory().containsItem(20732, 1)) {
			sendPlayerDialogue(9827, "I have not retrieved the keys yet.");
			stage = 21;
		} else if (player.getInventory().containsItem(20730, 1)
				|| player.getInventory().containsItem(20731, 1)
				|| player.getInventory().containsItem(20732, 1)) {
			sendPlayerDialogue(9827,
					"I have retrieved the Keys of Spring. Those Evil Tree's were a rough bunch!");
			stage = 23;
		}
			break;
		case 21:
			sendNPCDialogue(npcId, 9827, "Well please hurry. The Keys of Spring are made up of a Stone key, a Water key, and a Cloud key.");
			stage = 21;
			break;
		case 23:
			sendNPCDialogue(npcId, 9827, "Give them here, Spring doesn't last forever you know!");
			stage = 24;
			break;
		case 24:
			sendDialogue("You give the Keys of Spring to the Queen of Snow.");
			player.getInventory().deleteItem(20730, 1);
			player.getInventory().deleteItem(20731, 1);
			player.getInventory().deleteItem(20732, 1);
			stage = 25;
			break;
		case 25:
			sendNPCDialogue(npcId, 9827, "Thank you for finding the keys, here is your reward.");
			stage = 26;
			break;
		case 26:
			EasterEvent.handleQuestComplete(player);
			EasterEvent.handleQuestCompleteInterface(player);
			end();
			break;
		}
	}
	public void finish() {
	}
}