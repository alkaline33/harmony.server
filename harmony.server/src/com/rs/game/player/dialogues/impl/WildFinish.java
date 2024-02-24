package com.rs.game.player.dialogues.impl;

import com.rs.game.WorldTile;
import com.rs.game.player.dialogues.Dialogue;

public class WildFinish extends Dialogue {

	private int npcId = 510;

	@Override
	public void start() {
		//npcId = (Integer) parameters[0];
		sendNPCDialogue(
				npcId,
				9827,
				"Hey "
						+ player.getUsername()
						+ ", I'm the Wild Skilling Guard. Do you wish to teleport out");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			sendOptionsDialogue("Teleport out?", 
					"Yes",
					"No");
			stage = 2;
		} else if (stage == 2) {
			if (componentId == OPTION_1) {
					player.setNextWorldTile(new WorldTile(3176, 3951, 0));
					end();
					return;
			} else {
				end();
			}
		} else if (stage == 2) {
			if (componentId == OPTION_2) {
			end();
			}
		}
		}
	

	@Override
	public void finish() {

	}

}