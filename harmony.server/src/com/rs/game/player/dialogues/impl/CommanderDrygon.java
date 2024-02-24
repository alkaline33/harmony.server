package com.rs.game.player.dialogues.impl;

import com.rs.game.player.Skills;
import com.rs.game.player.dialogues.Dialogue;


public class CommanderDrygon extends Dialogue {

	int npcId = 8925;
	public int starter = 0;
	

	@Override
	public void start() {
		
		sendEntityDialogue(IS_NPC, "Commander", npcId, 9765, "Soldier! We have a serious battle going on here! Get into battle and defend Harmony!");
			
		}

	@Override
	public void run(int interfaceId, int componentId) {
		switch(stage) {
		case -1:
			end();
			break;
		}
	}

	@Override
	public void finish() {

	}

}
