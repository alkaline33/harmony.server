package com.rs.game.player.dialogues.easter;

import com.rs.game.WorldTile;
import com.rs.game.player.content.Magic;
import com.rs.game.player.dialogues.Dialogue;


public class EasterBunnyD extends Dialogue {

	int npcId = 9687;
	public int starter = 0;
	

	@Override
	public void start() {
		if (player.finishedeasterevent) {
			 Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(1562, 4507, 0));
		} else
		if (player.spokentobunny == true) {
			stage = 10;
			sendEntityDialogue(IS_NPC, "Easter Bunny", npcId, 9827, "Hello "+player.getDisplayName()+", do you have my eggs?");
		} else
		if (player.spokentobunny == false) {
		sendEntityDialogue(IS_NPC, "Easter Bunny", npcId, 9827, "Hello "+player.getDisplayName()+", do you think you"
				+ " could help me?");
		}
		
		
	}

	@Override
	public void run(int interfaceId, int componentId) {
		switch(stage) {
		/**
		 * Start of Easter quest
		 */
		case -1:
			stage = 0;
			sendOptionsDialogue(SEND_DEFAULT_OPTIONS_TITLE, "Sure", "No");
			break;
		case 0:
			if(componentId == OPTION_2) {
				stage = 1;
				sendPlayerDialogue(9760, "No, sorry.");
			}else {
				stage = 2;
				sendPlayerDialogue(9803, "Sure, what do you need?");
			}
			break;
		case 1:
			stage = -2;
			sendEntityDialogue(IS_NPC, "Easter Bunny", npcId, 9762, "Oh okay..");
			break;
		case 2:
			stage = 3;
			sendEntityDialogue(IS_NPC, "Easter Bunny", npcId, 9804, "I'm missing 5 Eggs, but i can't leave to get them because i have to prepare for Easter! Would you please get them for me?");
			break;
		case 3:
			stage = 4;
			sendOptionsDialogue(SEND_DEFAULT_OPTIONS_TITLE, "Yes of course!", "No i don't have the time.");
			break;
		case 4:
			if(componentId == OPTION_2) {
				sendPlayerDialogue(9827, "No i don't have the time.");
				end();
			}else {
				stage = 5;
				sendPlayerDialogue(9803, "Yes of course, just let me know what you need.");
			}
			break;
		case 5:
			stage = 6;
			sendEntityDialogue(IS_NPC, "Easter Bunny", npcId, 9850, "I need you to get Eggs from 5 of my friends. I have wrote them on a scroll for you.");
			break;
		case 6:
			stage = 8;
			sendItemDialogue(29675, 1, "Easter Bunny hands you a scroll of names.");
			player.spokentobunny = true;
			player.getInventory().addItemDrop(29675, 1);
			break;
		case 8:
			stage = 9;
			sendEntityDialogue(IS_NPC, "Easter Bunny", npcId, 9850, "I will see you soon!");
			break;
		case 9:
			end();
			break;
			/**
			 * Returning the eggs
			 */
		case 10:
			if (player.getInventory().contains(12640) && player.getInventory().contains(12641) && player.getInventory().contains(12639) && player.getInventory().contains(12638) && player.getInventory().contains(12644)) {
				stage = 11;
				sendPlayerDialogue(9760, "Yes i think so.");
			break;
			} else {
				stage = 9;
			sendPlayerDialogue(9760, "No, sorry.");
			break;
			}
		case 11:
			stage = 12;
			sendPlayerDialogue(9760, "Here you go, Mr Bunny!");
			break;
		case 12:
			stage = 13;
			sendItemDialogue(12644, 1, "You hand the Easter Bunny the eggs.");
			break;
		case 13:
			stage = 9;
			sendEntityDialogue(IS_NPC, "Easter Bunny", npcId, 9850, "Thank you so much "+player.getDisplayName()+"! I have given you a pet bunny & the title *the Egg Collector*. You can also now access my hideout for further rewards, just speak to me to access the hideout.");
			player.getInventory().deleteItem(12640, 2000);
			player.getInventory().deleteItem(12641, 2000);
			player.getInventory().deleteItem(12639, 2000);
			player.getInventory().deleteItem(12638, 2000);
			player.getInventory().deleteItem(12644, 2000);
			player.getInventory().deleteItem(29675, 2000);
			player.getInventory().addItemDrop(29558, 1);
			player.easter2019title = true;
			player.finishedeasterevent = true;
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
