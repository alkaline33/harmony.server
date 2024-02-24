package com.rs.game.player.dialogues.hween;

import com.rs.game.player.Skills;
import com.rs.game.player.dialogues.Dialogue;


public class Hetty extends Dialogue {

	int npcId = 307;
	public int starter = 0;
	

	@Override
	public void start() {
		if (player.getInventory().getFreeSlots() < 2) {
			player.getDialogueManager().startDialogue("SimpleMessage", "You need at least 2 free inventory slots.");
		}
		if (player.canspeaktohettyagain == true) {
			stage = 10;
			sendEntityDialogue(IS_NPC, "Hetty", npcId, 9827, "Hello "+player.getDisplayName()+", do you have my items?");
		} 
		if (player.canspeaktohettyagain1 == true) {
			stage = 13;
			sendPlayerDialogue(9827, "Hey Hetty. I drew blood from the sword for the bottle, but it went into your cauldron.");
		} 
		if (player.spokentohetty == false) {
		sendEntityDialogue(IS_NPC, "Hetty", npcId, 9827, "Hello "+player.getDisplayName()+", do you think you"
				+ " could help me?");
		}
		
		
	}

	@Override
	public void run(int interfaceId, int componentId) {
		switch(stage) {
		case -1:
			stage = 0;
			sendOptionsDialogue(SEND_DEFAULT_OPTIONS_TITLE, "Sure", "No");
			break;
		case 0:
			if(componentId == OPTION_2) {
				stage = 1;
				sendPlayerDialogue(9760, "No, sorry.");
			}else {
				stage = 2;
				sendPlayerDialogue(9803, "Sure, what do you need?");
			}
			break;
		case 1:
			stage = -2;
			sendEntityDialogue(IS_NPC, "Hetty", npcId, 9762, "Oh okay..");
			break;
		case 2:
			stage = 3;
			sendEntityDialogue(IS_NPC, "Hetty", npcId, 9804, "My lord is coming to visit soon and i need help recovering a few items!"
					+ " Could you please help?");
			break;
		case 3:
			stage = 4;
			sendOptionsDialogue(SEND_DEFAULT_OPTIONS_TITLE, "Yes of course!", "No i don't have the time.");
			break;
		case 4:
			if(componentId == OPTION_2) {
				sendPlayerDialogue(9827, "No i don't have the time.");
				end();
			}else {
				stage = 5;
				sendPlayerDialogue(9803, "Yes of course, just let me know what you need.");
			}
			break;
		case 5:
			stage = 6;
			sendEntityDialogue(IS_NPC, "Hetty", npcId, 9850, "I need you to go in with a broomstick"
					+ " and find me the items. I've wrote the items on a scroll for you. The broomstick "
					+ "can teleport you in.");
			break;
		case 6:
			stage = 7;
			sendItemDialogue(29675, 1, "Hetty hands you a scroll which contains everything she needs for the arrival of her Lord.");
			player.getInventory().addItem(29675, 1);
			break;
		case 7:
			stage = 8;
			sendItemDialogue(14057, 1, "Hetty hands you a broomstick. This can be used to teleport to the castle safely and clean up mess.");
			player.getInventory().addItem(14057, 1);
			player.spokentohetty = true;
			break;
		case 8:
			stage = 9;
			sendEntityDialogue(IS_NPC, "Hetty", npcId, 9850, "Okay. Goodluck and thank you. I will see you soon!");
			break;
		case 9:
			end();
			break;
		case 10:
			
			if (player.getInventory().contains(29674) && player.getInventory().contains(29673) && player.getInventory().contains(29671) && player.getInventory().contains(3064)) {
				stage = 11;
				sendPlayerDialogue(9760, "I have most of them, but i couldn't get the blood and i found this rusty sword.");
			break;
			
			} else {
				stage = 9;
			sendPlayerDialogue(9760, "No, sorry.");
			break;
			}
		case 11:
			stage = 12;
			sendEntityDialogue(IS_NPC, "Hetty", npcId, 9850, "Here, take this super cool potion like thing and it will clean the sword.");
			break;
		case 12:
			stage = 9;
			sendItemDialogue(111, 1, "Hetty hands a super cool potion like thing. Use it on the rusty sword to clean it!");
			player.getInventory().addItem(29670, 1);
			player.canspeaktohettyagain = false;
			player.canfindbroomitems = false;
			break;
		case 13:
			stage = 14;
			sendEntityDialogue(IS_NPC, "Hetty", npcId, 9850, "As i expected. The Lord is close. Here i'll get the blood into a bottle.");
			break;
		case 14:
			stage = 15;
			sendItemDialogue(29669, 1, "Hetty hands you a bottle of blood.");
			player.getInventory().addItem(29669, 1);
			player.hasfinishedtalkingtohetty = true;
			player.canspeaktohettyagain1 = false;
			break;
		case 15:
			stage = 9;
			sendEntityDialogue(IS_NPC, "Hetty", npcId, 9850, "Use the bottle of blood on my cauldron. It will then be complete.");
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
