package com.rs.game.player.dialogues.impl;

import com.rs.game.player.dialogues.Dialogue;
import com.rs.game.player.content.clans.clancitadels.ClanCitadel;

/**
 * 
 * @author Josh'
 *
 */
public class CitadelLeaveD extends Dialogue {

	@Override
	public void start() {
		sendPlayerDialogue(9827, "'My time among these clans coming to an end, I returned to the surface world.'");
		
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			sendOptionsDialogue("Are you sure you would like to leave this Citadel?", "Yes, I wan't to leave.", "No, I wan't to stay!");
			stage = 1;
		}
		else if (stage == 1) {
			ClanCitadel.leaveCitadel(player);
			end();
		}
		
	}

	@Override
	public void finish() {
		
	}
	
	

}
