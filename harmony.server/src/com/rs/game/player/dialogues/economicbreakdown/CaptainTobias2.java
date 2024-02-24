package com.rs.game.player.dialogues.economicbreakdown;

import com.rs.game.WorldTile;
import com.rs.game.player.Skills;
import com.rs.game.player.content.Magic;
import com.rs.game.player.dialogues.Dialogue;


public class CaptainTobias2 extends Dialogue {

	int npcId = 376;
	public int starter = 0;
	

	@Override
	public void start() {	
			sendPlayerDialogue(9827, "Captain. The King has allowed ships to take double the load now per trip.");
				
		}

	@Override
	public void run(int interfaceId, int componentId) {
		switch(stage) {
		case -1:
			stage = 0;
			sendEntityDialogue(IS_NPC, "Captain Tobias", npcId, 9827, "That is fantastic news, however i fear it won't last.");
			break;
		case 0:
			sendPlayerDialogue(9827, "Why would you fear that?");
			stage = 1;
			break;
		case 1:
			sendEntityDialogue(IS_NPC, "Captain Tobias", npcId, 9827, "I'm afraid one of my ships hasn't returned for days. The cost could make Tax increase by 30% to replace it!");
			stage = 2;
		break;
		case 2:
			sendPlayerDialogue(9827, "Maybe i could help?");
			stage = 3;
			break;
		case 3:
			sendEntityDialogue(IS_NPC, "Captain Tobias", npcId, 9827, "Yes. I can send you to the island where my men sent the last signal?");
			stage = 4;
		break;
		case 4:
			stage = 5;
			sendOptionsDialogue("Travel to the island?", "Yes", "No");
			break;
		case 5:
			if(componentId == OPTION_2) {
				sendPlayerDialogue(9827, "No thanks.");
				end();
			}else {
				stage = 6;
				sendPlayerDialogue(9827, "Take me!");
				
			}
			break;
		case 6:
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2817, 3291, 0));
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
