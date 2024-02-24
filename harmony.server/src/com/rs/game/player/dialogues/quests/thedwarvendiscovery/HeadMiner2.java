package com.rs.game.player.dialogues.quests.thedwarvendiscovery;

import com.rs.game.player.Skills;
import com.rs.game.player.dialogues.Dialogue;


public class HeadMiner2 extends Dialogue {

	int npcId = 15231;
	public int starter = 0;
	

	@Override
	public void start() {
			sendPlayerDialogue(9790, "The wizard gave me a teleport crystal but it doesn't seem to work.");
				}

	@Override
	public void run(int interfaceId, int componentId) {
		switch(stage) {
		case -1:
			stage = 0;
			sendEntityDialogue(IS_NPC, "Head Dwarf", npcId, 9790, "Ah yes, that's because i need to give it the location.");
			break;
		case 0:
			sendItemDialogue(29554, 1, "You give the Dwarf the crystal.");
			player.getInventory().deleteItem(29554, 1234);
			stage = 1;
			break;
		case 1:
			sendItemDialogue(29554, 1, "The dwarf gives you back the crystal");
			player.getInventory().addItem(29553, 1);
			player.dwarvenpart = 4;
			stage = 2;
			break;
		case 2:
			stage = 3;
			sendEntityDialogue(IS_NPC, "Head Dwarf", npcId, 9790, "All done, it should work now!");
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
