package com.rs.game.player.dialogues.impl;

import com.rs.game.player.content.DailyChallenges;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.utils.ShopsHandler;

public class SlayerSurvivalD extends Dialogue {

	/**
	 * Author @ Mr_Joopz
	 */

	public SlayerSurvivalD() {
	}

	@Override
	public void start() {
		stage = 1;
		sendOptionsDialogue("Select an option", "Start Slayer Survival", "Slayer Survival Shop", "Nevermind");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == 1) {
			if (componentId == OPTION_1) {
				player.getControlerManager().startControler("SlayerSurvival");
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				end();
			} else if (componentId == OPTION_2) {
				ShopsHandler.openShop(player, 127);
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
