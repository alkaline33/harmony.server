package com.rs.game.player.dialogues.quests.thedwarvendiscovery;

import com.rs.game.player.dialogues.Dialogue;


public class ProfessorOnglewip2 extends Dialogue {

	int npcId = 4585;
	public int starter = 0;
	

	@Override
	public void start() {
			sendPlayerDialogue(9790, "Hello, i have your letters!");
				
		}

	@Override
	public void run(int interfaceId, int componentId) {
		switch(stage) {
		case -1:
			stage = 0;
			sendEntityDialogue(IS_NPC, "Professor Onglewip", npcId, 9790, "Ah superb, pass them here!");
			break;
		case 0:
			sendItemDialogue(29555, 3, "You give Professor Onglewip the letters.");
			player.getInventory().deleteItem(29555, 2000);
			player.getInventory().deleteItem(29556, 2000);
			player.getInventory().deleteItem(29557, 2000);
			stage = 1;
			break;
		case 1:
			sendEntityDialogue(IS_NPC, "Professor Onglewip", npcId, 9790, "Thank you, heres your teleport crystal.");
			player.getInventory().addItem(29554, 1);
			player.dwarvenpart = 3;
			stage = 2;
			break;
		case 2:
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
