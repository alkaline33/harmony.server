package com.rs.game.player.dialogues.impl.ruesaltar;

import com.rs.game.player.Skills;
import com.rs.game.player.dialogues.Dialogue;


public class Rue extends Dialogue {

	int npcId = 8031;
	

	@Override
	public void start() {
		if (player.getSkills().getLevel(Skills.RUNECRAFTING) < 50) {
			player.getDialogueManager().startDialogue("SimpleMessage", "You do not have the correct requirements to start this quest.");
		} else if (player.completedruesaltar) {
				player.getDialogueManager().startDialogue("SimpleMessage", "You have no need to speak with Rue now.");
		} else if (player.ruesaltarprogress == 1) {
			if (!player.getInventory().contains(29012)) {
				stage = 12;
				sendNPCDialogue(npcId, 9827, "Hurry with that essence!");
				
			} else {
			stage = 8;
			sendPlayerDialogue(9827, "Hey Rue! I have a powerful essence for you!");
			}
		} else if (player.ruesaltarprogress == 2) {
			stage = 12;
			sendNPCDialogue(npcId, 9827, "Hurry along, we don't have much time!");
		} else if (player.ruesaltarprogress == 3) {
			stage = 14;
			sendPlayerDialogue(9827, "Rue, i did as you said, but it exploded in the altar!");
		} else {
		sendPlayerDialogue(9827, "Hello, are you okay?");
			}
		}

	@Override
	public void run(int interfaceId, int componentId) {
		switch(stage) {
		case -1:
			stage = 0;
			sendNPCDialogue(npcId, 9827, "No... I'm not! The altar, it's powerless, without it we are in deep trouble child! Deep, deep trouble!");
			break;
		case 0:
			stage = 2;
			sendPlayerDialogue(9827, "What altar? Can i help at all?");
			break;
		case 2:
			stage = 3;
			sendNPCDialogue(npcId, 9827, "The Ourania altar. You must've heard of it? It has no power, without it, we are surely doomed!");
			break;
		case 3:
			stage = 4;
			sendOptionsDialogue(SEND_DEFAULT_OPTIONS_TITLE, "Start Rue's altar quest", "Leave");
			break;
		case 4:
			if(componentId == OPTION_2) {
				sendPlayerDialogue(9827, "Okay i'll leave it for now.");
				end();
			}else {
				stage = 5;
				sendPlayerDialogue(9827, "I'm here to help, tell me what to do!");
				player.ruesaltarprogress = 1;
			}
			break;
		case 5:
			stage = 6;
			sendNPCDialogue(npcId, 9827, "You need to head to the essence mine & collect some powerless essence. Once you have that you must use it on the following altars...");
			break;
		case 6:
			stage = 7;
			sendNPCDialogue(npcId, 9827, "Air, Water, Earth, Fire, Body, Cosmic, Chaos, Astral, Nature, Law, Death & Blood. In that order! If you do it in any other order the essence will be destroyed!");
			break;
		case 7:
			stage = 12;
			sendPlayerDialogue(9827, "Easy enough, i'll be back shortly.");
			break;
			/**
			 * Second part
			 */
		case 8:
			stage = 9;
			sendNPCDialogue(npcId, 9827, "Oh my, let me see it!");
			break;
		case 9:
			stage = 10;
			sendItemDialogue(29012, 1, "You show the essence to Rue.");
			break;
		case 10:
			stage = 11;
			sendNPCDialogue(npcId, 9827, "Ah yes, this is exactly what i needed!");
			player.ruesaltarprogress = 2;
			break;
		case 11:
			stage = 13;
			sendNPCDialogue(npcId, 9827, "You must use this on the Altar in the Catacombs of Kourend. It would take me too long to roll my way there.");
			break;
		case 13:
			stage = 12;
			sendPlayerDialogue(9827, "Okay.");
			break;
		case 14:
			stage = 12;
			sendNPCDialogue(npcId, 9827, "Perfect, the fragments will fuel the Ourania altar. You have done a great thing today, i am forever in your debt. Please accept this reward as a token of my gratitude.");
			player.getInterfaceManager().sendInterface(1244);
			player.getPackets().sendIComponentText(275, 27, "Rue's Altar");
			player.getPackets().sendGlobalString(359, "<br>Congratulations!</br> <br>You were given.</br> <br>A spin on the Squeal Of Fortune!</br> <br>Some Runecrafting Experience</br> <br>Access to the ZMI altar.</br>");
			player.spins++;
			player.getSkills().addXpLamp(Skills.RUNECRAFTING, 2500);
			player.ruesaltarprogress = 4;
			player.completedruesaltar = true;
			break;
		
		case 12:
			//PlayerLook.openThessaliasMakeOver(player);
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
