package com.rs.game.player.dialogues.impl;

import com.rs.game.WorldTile;
import com.rs.game.player.content.Magic;
import com.rs.game.player.dialogues.Dialogue;


public class VoragoEntrance extends Dialogue {

	int npcId = 30000;
	public int starter = 0;
	

	@Override
	public void start() {
			sendEntityDialogue(IS_NPC, "Vorago", 30000, 9827, "Weak child, what do you want?");
			}
		

	@Override
	public void run(int interfaceId, int componentId) {
		switch(stage) {
		case -1:
			stage = 0;
			sendOptionsDialogue(SEND_DEFAULT_OPTIONS_TITLE, "Go in", "How many in a team?", "Nothing");
			break;
		case 0:
			if(componentId == OPTION_1) {
				stage = 1;
				sendPlayerDialogue(9827, "Let me in you lump of rock! I'm going to own you!");
			}else if(componentId == OPTION_2) {
				stage = 3;
				sendPlayerDialogue(9845, "How many people would you say i need to stand a chance?");
			} else {
				sendPlayerDialogue(9845, "Nevermind");
				stage = 13;
			}
			break;
		case 1:
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3543, 9506, 0));
stage = 13;
				break;
		case 3:
			sendEntityDialogue(IS_NPC, "Vorago", 30000, 9827, "To stand a chance?! At least 3 of the very best! But you can't defeat me child!");
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
