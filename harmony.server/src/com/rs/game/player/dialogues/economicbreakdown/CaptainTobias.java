package com.rs.game.player.dialogues.economicbreakdown;

import com.rs.game.player.Skills;
import com.rs.game.player.dialogues.Dialogue;


public class CaptainTobias extends Dialogue {

	int npcId = 376;
	public int starter = 0;
	

	@Override
	public void start() {	
			sendPlayerDialogue(9827, "Captain. The King has sent me to retrieve export/import documents.");
				
		}

	@Override
	public void run(int interfaceId, int componentId) {
		switch(stage) {
		case -1:
			stage = 0;
			sendEntityDialogue(IS_NPC, "Captain Tobias", npcId, 9827, "Aye aye son. Here you go.");
			break;
		case 0:
			sendItemDialogue(10829, 1, "Captain hands you the documents.");
			player.getInventory().addItem(10829, 1);
			player.ecobdpart = 2;
			stage = 1;
			break;
		case 1:
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
