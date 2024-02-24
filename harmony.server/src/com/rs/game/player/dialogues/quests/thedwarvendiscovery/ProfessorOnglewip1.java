package com.rs.game.player.dialogues.quests.thedwarvendiscovery;

import com.rs.game.player.dialogues.Dialogue;


public class ProfessorOnglewip1 extends Dialogue {

	int npcId = 4585;
	public int starter = 0;
	

	@Override
	public void start() {
			sendPlayerDialogue(9790, "Hello. Could you help me? I need a way into a Dwarven mine, but i can't fit.");
				
		}

	@Override
	public void run(int interfaceId, int componentId) {
		switch(stage) {
		case -1:
			stage = 0;
			sendEntityDialogue(IS_NPC, "Professor Onglewip", npcId, 9790, "Ah yes. I can help you, but first i'll need your help.");
			break;
		case 0:
			sendOptionsDialogue("Help the Professor?", "I'll help.", "No.");
			stage = 1;
			break;
		case 1:
			if(componentId == OPTION_2) {
				sendPlayerDialogue(9827, "Okay i'll leave it for now.");
				end();
			}else {
				stage = 2;
				sendPlayerDialogue(9790, "I sure could help you.");
				player.dwarvenpart = 2;
			}
		case 2:
			stage = 3;
			sendEntityDialogue(IS_NPC, "Professor Onglewip", npcId, 9790, "I need you to fetch me a letter from Varrock, Brimhaven & Al Kharid.");
			player.sendMessage("<col=ff0000> Speak to Iffie in Varrock, Charlie the cook in Brimhaven, Raneal in Al Kharid.");
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
