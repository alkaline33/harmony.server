package com.rs.game.player.dialogues.impl;

import com.rs.game.player.dialogues.Dialogue;
import com.rs.utils.HeistBags;
import com.rs.utils.HeistGames;
import com.rs.utils.ShopsHandler;

public class ChiefThiefRobin extends Dialogue {

	@Override
	public void start() {
		sendOptionsDialogue("Select an option.",
				"Open Rewards Shop", "Bags deposited Highscores", "Games played highscores");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
		if (componentId == OPTION_1) {
		ShopsHandler.openShop(player, 106);
		end();
		} else if (componentId == OPTION_2) {
        HeistBags.showRanks(player);
		end();
		} else if (componentId == OPTION_3) {
		HeistGames.checkRank(player);
		HeistGames.showRanks(player);
		end();
		}
	}
}

	

	@Override
	public void finish() {

	}

}
