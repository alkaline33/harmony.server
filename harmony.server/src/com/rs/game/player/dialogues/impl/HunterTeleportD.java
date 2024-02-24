package com.rs.game.player.dialogues.impl;

import com.rs.game.WorldTile;
import com.rs.game.player.content.Magic;
import com.rs.game.player.dialogues.Dialogue;

public class HunterTeleportD extends Dialogue {

	@Override
	public void start() {
		sendOptionsDialogue("Where would you like to train hunter?", "Main hunter area", "Falconry");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			if (componentId == OPTION_1) {
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2525, 2915, 0));
				end();
			} else if (componentId == OPTION_2) {
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2372, 3624, 0));
				end();
			}
		}
	}

	@Override
	public void finish() {

	}

}