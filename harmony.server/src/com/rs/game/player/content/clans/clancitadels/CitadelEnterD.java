package com.rs.game.player.content.clans.clancitadels;

import com.rs.game.player.dialogues.Dialogue;
import com.rs.game.player.content.clans.clancitadels.ClanCitadel;

/**
 * 
 * @author Josh'
 *
 */
public class CitadelEnterD extends Dialogue {

	@Override
	public void start() {
		sendDialogue("You have the option to join ones citadel, or create your", "own. What would you like to do?");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			sendOptionsDialogue("Select an Option", "Join a Citadel", "Join my own");
			stage = 1;
		} 
		else if (stage == 1) {
			if (componentId == OPTION_1) {
				end();
				player.getTemporaryAttributtes().put("target_citadel", Boolean.TRUE);
				player.getPackets().sendRunScript(109, "Enter Name:");
			} 
			else if (componentId == OPTION_2) {
				end();
				new ClanCitadel(player);
			}
		}
	}

	@Override
	public void finish() {
		
	}

}
