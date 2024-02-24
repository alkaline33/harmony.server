package com.rs.game.player.dialogues.impl;

import com.rs.game.player.dialogues.Dialogue;

public class Santa extends Dialogue {

	int npcId = 8540;
	public int starter = 0;
	

	@Override
	public void start() {
		if (player.completedxmas2018 == true) {
			player.sendMessage("Santa doesn't require anymore help. You can gain more Christmas items from skilling & pvm.");
			return;
		}
		if (player.startedxmas2018 != true) {
		sendEntityDialogue(IS_NPC, "Santa", 8540, 9827, "Ho ho ho. I seem to have lost some gifts on my way! Will you help me recover them?");
		} else {
			stage = 6;
			sendEntityDialogue(IS_NPC, "Santa", 8540, 9827, "Ho ho ho. Do you have all my gifts?");
		}
	}
		

	@Override
	public void run(int interfaceId, int componentId) {
		switch(stage) {
		case -1:
			stage = 0;
			sendOptionsDialogue(SEND_DEFAULT_OPTIONS_TITLE, "Of course!", "No.");
			break;
		case 0:
			if(componentId == OPTION_2) {
				stage = 12;
				sendPlayerDialogue(9790, "No, bye.");
			}else {
				stage = 2;
				sendPlayerDialogue(9845, "Of course Santa! How can i help?");
			}
			break;
		case 1:
			stage = -2;
			sendEntityDialogue(IS_NPC, "Santa", 8540, 9827, "I lost 5 gifts on my way, i need you to get them for me.");
			break;
		case 2:
			stage = 3;
			sendEntityDialogue(IS_NPC, "Santa", 8540, 9827, "I lost presents whilst flying over; Varrock, Lumbridge, Falador,  Port Sarim & Lunar Isle!");
			break;
		case 3:
			stage = 4;
			sendOptionsDialogue(SEND_DEFAULT_OPTIONS_TITLE, "I'll get to it!", "Do it yourself.");
			break;
		case 4:
			if(componentId == OPTION_2) {
				sendPlayerDialogue(9827, "Do it yourself, bye.");
				end();
			}else {
				stage = 12;
				sendPlayerDialogue(9827, "Okay Santa, i'll be back shortly!");
				player.startedxmas2018 = true;
			}
			break;
		case 6:
			if (player.getInventory().contains(29280) && player.getInventory().contains(29279) && player.getInventory().contains(29278) && player.getInventory().contains(29277) && player.getInventory().contains(29276)) {
				stage = 7;
				sendPlayerDialogue(9827, "Yes i do!");
			} else {
				stage = 12;
				sendPlayerDialogue(9827, "Not yet Santa, i'll keep looking!");
			}
			break;
		case 7:
			player.getInventory().deleteItem(29280, 1);
			player.getInventory().deleteItem(29279, 1);
			player.getInventory().deleteItem(29278, 1);
			player.getInventory().deleteItem(29277, 1);
			player.getInventory().deleteItem(29276, 1);
			player.getInventory().addItem(14601, 1);
			player.getInventory().addItem(14602, 1);
			player.getInventory().addItem(14603, 1);
			player.getInventory().addItem(14605, 1);
			player.completedxmas2018 = true;
			stage = 12;
			sendEntityDialogue(IS_NPC, "Santa", 8540, 9827, "Thank you so much " + player.getDisplayName() + "! I have given you a Santa outfit as a token of my gratitude!");
			break;
		case 12:
			end();
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
