package com.rs.game.player.dialogues.impl;

import com.rs.game.player.dialogues.Dialogue;
import com.rs.game.player.interfaces.PerkInterface;
import com.rs.utils.ShopsHandler;

public class UpgradeArmour extends Dialogue {

	public UpgradeArmour() {
	}

	@Override
	public void start() {
		stage = 1;
		sendOptionsDialogue("Select an option", "Open Shop", "Open Perk Management", "Nevermind");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == 1) {
			if (componentId == OPTION_1) {
				ShopsHandler.openShop(player, 119);
				end();
			} else if (componentId == OPTION_2) {
				PerkInterface.OpenInterface(player);
				end();
			} else if (componentId == OPTION_3) {
				end();
			}
		} else {
			end();
		}
	}

	@Override
	public void finish() {
	}

}
