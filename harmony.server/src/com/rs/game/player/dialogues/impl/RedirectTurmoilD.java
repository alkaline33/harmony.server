package com.rs.game.player.dialogues.impl;

import com.rs.game.player.dialogues.Dialogue;
import com.rs.utils.Colors;

public class RedirectTurmoilD extends Dialogue {

	@Override
	public void start() {
			sendOptionsDialogue("Which style of turmoil do you want active?",
					"Melee", "Magic", "Ranged", "Nevermind.");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (componentId == OPTION_1) {
			player.sendMessage(Colors.red+"Your turmoil is now directing it's power to Melee.");
			player.turmoildirect = 0;
			end();		
		} else if (componentId == OPTION_2) {
			player.sendMessage(Colors.blue+"Your turmoil is now directing it's power to Magic.");
			player.turmoildirect = 1;
			end();	
		} else if (componentId == OPTION_3) {
			player.sendMessage(Colors.green+"Your turmoil is now directing it's power to Ranged.");
			player.turmoildirect = 2;
			end();	
		} else {
			end();
		}
	}

	@Override
	public void finish() {

	}

}
