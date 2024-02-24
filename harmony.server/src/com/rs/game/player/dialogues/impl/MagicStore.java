package com.rs.game.player.dialogues.impl;

import com.rs.game.player.dialogues.Dialogue;
import com.rs.utils.ShopsHandler;

public class MagicStore extends Dialogue {

	@Override
	public void start() {
		sendOptionsDialogue("Which store would you like to view?", "Runes", "Equipment");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			if (componentId == OPTION_1) {
				ShopsHandler.openShop(player, 3);
				end();
				stage = 3;
				return;
			} else {
				ShopsHandler.openShop(player, 124);
				end();
				stage = 3;
			}
		} else if (stage == 3) {
			end();
		}

	}

	@Override
	public void finish() {

	}

}