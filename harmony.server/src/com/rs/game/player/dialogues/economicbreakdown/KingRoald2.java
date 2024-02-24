package com.rs.game.player.dialogues.economicbreakdown;

import com.rs.game.player.Skills;
import com.rs.game.player.dialogues.Dialogue;


public class KingRoald2 extends Dialogue {

	int npcId = 5838;
	public int starter = 0;
	

	@Override
	public void start() {
			sendPlayerDialogue(9827, "Your highness.");
				}

	@Override
	public void run(int interfaceId, int componentId) {
		switch(stage) {
		case -1:
			stage = 0;
			sendEntityDialogue(IS_NPC, "King Roald", npcId, 9827, "Ah, young warrior. Have you come to a conclusion of this crisis?");
			break;
		case 0:
			sendOptionsDialogue("Answer", "You aren't exporting enough goods.", "Your ships can take twice the load."
					, "Your captain is useless", "I don't know.");
			stage = 1;
			break;
		case 1:
			if(componentId == OPTION_1) {
				sendPlayerDialogue(9827, "You aren't exporting enough goods, that's why your profits are low!");
				stage = 2;
				break;
			} else if(componentId == OPTION_2) {
					sendPlayerDialogue(9827, "Your ships can take twice the load they currently take.");
					stage = 3;
					break;
			} else if(componentId == OPTION_2) {
				sendPlayerDialogue(9827, "Your captain is useless, find a new one!");
				stage = 2;
				break;
			}else {
				sendPlayerDialogue(9827, "I don't know, sorry.");
				end();
				break;
				
			}
		case 2:
			sendEntityDialogue(IS_NPC, "King Roald", npcId, 9827, "That's ridiculous. That isn't the reason.");
			stage = 7;
			break;
		case 3:
			sendEntityDialogue(IS_NPC, "King Roald", npcId, 9827, "I see now, here take this document to the court to have them rule the new law.");
			stage = 4;
			break;
		case 4:
			sendItemDialogue(6949, 1, "King Roald hands you the order.");
			player.getInventory().addItem(6949, 1);
			player.ecobdpart = 3;
			stage = 7;
			break;
		case 7:
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
