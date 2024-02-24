package com.rs.game.player.dialogues.impl;

import com.rs.game.player.dialogues.Dialogue;
import com.rs.game.player.interfaces.PetClaimInterface;

public class PetTamer extends Dialogue {

	int npcId = 885;
	public int starter = 0;
	

	@Override
	public void start() {
			sendEntityDialogue(IS_NPC, "Pet Tamer", 885, 9827, "Ai there laddy. What can i help you with?");
			}
		

	@Override
	public void run(int interfaceId, int componentId) {
		switch(stage) {
		case -1:
			stage = 0;
			sendOptionsDialogue(SEND_DEFAULT_OPTIONS_TITLE, "Can i see the pet interface?", "How can i obtain a pet?", "Nothing");
			break;
		case 0:
			if(componentId == OPTION_1) {
				stage = 1;
				sendPlayerDialogue(9827, "I'd like to view the pet interface.");
			}else if(componentId == OPTION_2) {
				stage = 3;
				sendPlayerDialogue(9845, "How can i get myself a pet?");
			} else {
				sendPlayerDialogue(9845, "Nevermind");
				stage = 13;
			}
			break;
		case 1:
				PetClaimInterface.OpenInterface(player);
				stage = 13;
				break;
		case 3:
			sendEntityDialogue(IS_NPC, "Pet Tamer", 885, 9827, "Ai, well you can either donate, gain from a rare drop, gain from skilling, gain from the slayer store or from minigames!");
			stage = 4;
			break;
		case 4:
			sendEntityDialogue(IS_NPC, "Pet Tamer", 885, 9827, "The chances of these being dropped from monsters are on the ::discord, type !bosspets! Others you can find out yourself.");
				stage = 6;
			break;
		case 6:
			sendDialogue("You shake hands with the Pet Tamer, thank him and leave.");
				stage = 13;
		break;
		case 13:
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
