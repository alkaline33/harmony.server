package com.rs.game.player.dialogues.quests.thedwarvendiscovery;

import com.rs.game.player.Skills;
import com.rs.game.player.dialogues.Dialogue;


public class HeadMiner1 extends Dialogue {

	int npcId = 15231;
	public int starter = 0;
	

	@Override
	public void start() {
		if (player.getSkills().getLevel(Skills.MINING) < 95) {
			player.getDialogueManager().startDialogue("SimpleMessage", "You need 95 mining to start this quest.");
			return;
		} else {
		//npcId = (Integer) parameters[0];
			sendPlayerDialogue(9790, "Hello sir, how are you?");
				}
		}

	@Override
	public void run(int interfaceId, int componentId) {
		switch(stage) {
		case -1:
			stage = 0;
			sendEntityDialogue(IS_NPC, "Head Dwarf", npcId, 9790, "Hello son. We've found a very strange rock that we cannot mine.");
			break;
		case 0:
			sendOptionsDialogue("Help the dwarf?", "I could help you.", "No.");
			stage = 1;
			break;
		case 1:
			if(componentId == OPTION_2) {
				sendPlayerDialogue(9827, "Okay i'll leave it for now.");
				end();
			}else {
				stage = 2;
				sendPlayerDialogue(9790, "I could help you.");
				player.starteddwarvenquest = true;
				player.dwarvenpart = 1;
			}
		case 2:
			stage = 3;
			sendEntityDialogue(IS_NPC, "Head Dwarf", npcId, 9790, "Fantastic! Go down the hole next to me and your find it!");
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
