package com.rs.game.player.dialogues.economicbreakdown;

import com.rs.game.player.Skills;
import com.rs.game.player.dialogues.Dialogue;


public class KingRoald extends Dialogue {

	int npcId = 5838;
	public int starter = 0;
	

	@Override
	public void start() {
		if (player.getSkills().getLevel(Skills.SLAYER) < 60) {
			player.getDialogueManager().startDialogue("SimpleMessage", "You need 60 slayer to start this quest.");
			return;
		}
			if (player.getSkills().getLevel(Skills.ATTACK) < 92) {
				player.getDialogueManager().startDialogue("SimpleMessage", "You need 92 attack to start this quest.");
				return;
			} else {
			sendPlayerDialogue(9827, "Good day my royal highness.");
				}
		}

	@Override
	public void run(int interfaceId, int componentId) {
		switch(stage) {
		case -1:
			stage = 0;
			sendEntityDialogue(IS_NPC, "King Roald", npcId, 9827, "Young warrior, an unusual visit i see. How may i be of assistance?");
			break;
		case 0:
			sendOptionsDialogue("Question", "Ask about boss instances cost", "Nothing");
			stage = 1;
			break;
		case 1:
			if(componentId == OPTION_2) {
				sendPlayerDialogue(9827, "Nevermind.");
				end();
			}else {
				stage = 2;
				sendPlayerDialogue(9827, "I ask on behalf of the Kingdom my lord.. Why is the cost of instances so high?");
				
			}
			break;
		case 2:
			stage = 3;
			sendEntityDialogue(IS_NPC, "King Roald", npcId, 9827, "I see, let me explain. Our economy is at breaking point, we're close to collapsing..");
			break;
		case 3:
			stage = 4;
			sendEntityDialogue(IS_NPC, "King Roald", npcId, 9827, "In order for us to survive we must keep the 25% tax on these instances.");
			break;
		case 4:
			stage = 5;
			sendPlayerDialogue(9827, "What if i could be of assistance. There must be something i could do?");
			break;
		case 5:
			stage = 6;
			sendEntityDialogue(IS_NPC, "King Roald", npcId, 9827, "I insist that you speak to Captain Tobias at Port Sarim.");
			break;
		case 6:
			stage = 7;
			sendEntityDialogue(IS_NPC, "King Roald", npcId, 9827, "Ask the Captain for the export/import documents to see if you can find the issue.");
			break;
		case 7:
			stage = 8;
			sendPlayerDialogue(9827, "Thank you my Lord.");
			player.startedecobreakdownquest = true;
			player.ecobdpart = 1;
			break;
		case 8:
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
