package com.rs.game.player.dialogues.quests.thedwarvendiscovery;

import com.rs.game.player.Skills;
import com.rs.game.player.dialogues.Dialogue;


public class HeadMiner3 extends Dialogue {

	int npcId = 15231;
	public int starter = 0;
	

	@Override
	public void start() {
			sendPlayerDialogue(9790, "Here, i have the ore.");
				}

	@Override
	public void run(int interfaceId, int componentId) {
		switch(stage) {
		case -1:
			stage = 0;
			sendEntityDialogue(IS_NPC, "Head Dwarf", npcId, 9790, "Superb! Pass it here.");
			break;
		case 0:
			sendItemDialogue(29551, 1, "You give the Dwarf the ore.");
			player.getInventory().deleteItem(29551, 1);
			stage = 1;
			break;
		case 1:
			sendEntityDialogue(IS_NPC, "Head Dwarf", npcId, 9790, "Well isn't that just extraordinary! Thanks for the help chap!");
			player.dwarvenpart = 5;
			player.completeddwarvenquest = true;
			stage = 2;
			break;
		case 2:
				player.getInterfaceManager().sendInterface(1244);
				player.getPackets().sendIComponentText(275, 27, "The Dwarven Discovery");
				player.getPackets().sendGlobalString(359, "<br>Congratulations!</br> <br>You were given.</br> <br>A spin on the Squeal Of Fortune!</br> <br>Some Mining Experience</br> <br>Access to a new mining area and ore</br>");
		player.spins++;
		player.getSkills().addXpLamp(Skills.MINING, 5000);
		stage = 3;
		break;
		case 3:
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
