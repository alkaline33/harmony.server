package com.rs.game.player.dialogues.impl;

import com.rs.game.player.Skills;
import com.rs.game.player.dialogues.Dialogue;


public class ConTutorial2 extends Dialogue {



	@Override
	public void start() {
		sendEntityDialogue(IS_NPC, "Construction Tutorial", 4247, 9827,
				"Congratulations "+player.getDisplayName()+"!", 
				"You've started your journey to 99 construction!");
	}
	@Override
	public void run(int interfaceId, int componentId) {
		switch (stage) {
		case -1:
			stage = 0;
			sendEntityDialogue(IS_NPC, "Construction Tutorial", 4247, 9827, "Allow me to take them items off your hands and reward you with some xp!");
			break;
		case 0:
			stage = 2;
			player.unlock();
			player.getInventory().deleteItem(9625, 200);
			player.getInventory().deleteItem(19636, 200);
			player.getSkills().addXp(Skills.CONSTRUCTION, 577);
			player.contut = 0;
			player.contutdone = true;

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
