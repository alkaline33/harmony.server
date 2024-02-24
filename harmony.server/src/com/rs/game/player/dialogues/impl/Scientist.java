package com.rs.game.player.dialogues.impl;

import com.rs.game.player.dialogues.Dialogue;

public class Scientist extends Dialogue {

	int npcId = 30024;
	public int starter = 0;
	

	@Override
	public void start() {
			sendEntityDialogue(IS_NPC, "Scientist", 30024, 9827, "Hello Adventurer. What can i help you with?");
			}
		

	@Override
	public void run(int interfaceId, int componentId) {
		switch(stage) {
		case -1:
			stage = 0;
			sendOptionsDialogue(SEND_DEFAULT_OPTIONS_TITLE, "Could you help me with this dangerous egg?", "You have an egg of mine.");
			break;
		case 0:
			if(componentId == OPTION_2) {
				stage = 1;
				sendPlayerDialogue(9790, "You didn't give back my egg after i gave it to you to look at!");
			}else {
				stage = 2;
				sendPlayerDialogue(9845, "I found this Devourer egg from the beast, but i'm sure it isn't safe.");
			}
			break;
		case 1:
			if (player.scientisthasegg == 0) {
				sendEntityDialogue(IS_NPC, "Scientist", 30024, 9827, "Yes i did! Go away scammer.");
				stage = 13;
				break;
			}
			stage = 14;
			sendEntityDialogue(IS_NPC, "Scientist", 30024, 9827, "Ah! Indeed i didn't. Here you go.");
			break;
		case 2:
			stage = 3;
			sendEntityDialogue(IS_NPC, "Scientist", 30024, 9827, "Give it here and let me take a look.");
			break;
		case 3:
			if (!player.getInventory().contains(29787)) {
			stage = 13;
			}
			stage = 4;
			sendOptionsDialogue(SEND_DEFAULT_OPTIONS_TITLE, "Give the egg to the scientist.", "Leave");
			break;
		case 4:
			if(componentId == OPTION_2) {
				sendPlayerDialogue(9827, "Nevermind then...");
				stage = 13;
			}else {
				stage = 6;
				sendPlayerDialogue(9827, "Here you go..");
			}
			break;
		case 6:
			sendDialogue("You hand the egg to the scientist.");
				stage = 7;
				player.getInventory().deleteItem(29787, 1);
				player.scientisthasegg ++;
		break;
		
		case 7:
			sendEntityDialogue(IS_NPC, "Scientist", 30024, 9827, "Interesting! The egg seems to be producing a lot of Devourer acid. This is deadly towards humans.");
			stage = 8;
			break;
		case 8:
			sendEntityDialogue(IS_NPC, "Scientist", 30024, 9827, "I'd need a sum of 50,000,000 coins to study this egg.");
			stage = 9;
			break;
		case 9:
			sendOptionsDialogue(SEND_DEFAULT_OPTIONS_TITLE, "Give the scientist 50M coins.", "Leave");
			stage = 10;
			break;
		case 10:
			if(componentId == OPTION_2) {
				sendPlayerDialogue(9827, "Nevermind then...");
				stage = 13;
			}else {
				if (!player.getInventory().containsItem(995, 50000000)) {
					end();
				}
				stage = 11;
				player.getInventory().deleteItem(995, 50000000);
				sendPlayerDialogue(9827, "Sure! Here you go!");
			}
			break;
		case 11:
			sendEntityDialogue(IS_NPC, "Scientist", 30024, 9827, "Ah! Well the acid wasn't really dangerous, i wiped it off with my hand, but thanks for the coins!");
			stage = 12;
			break;
		case 12:
			sendDialogue("The scientist hands the safe egg to you.");
				stage = 13;
				player.getInventory().addItem(29786, 1);
				player.scientisthasegg --;
		break;
		case 13:
			end();
			break;
		case 14:
			sendDialogue("The scientist hands the egg back to you.");
			stage = 13;
			player.getInventory().addItem(29787, 1);
			player.scientisthasegg --;
			break;
		default:
			end();
			break;
		}
	}

	@Override
	public void finish() {

	}

}
