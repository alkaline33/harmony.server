package com.rs.game.player.dialogues.confirmations;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.player.content.RecycleCentreMain;
import com.rs.game.player.dialogues.Dialogue;

public class RecycleConfirmation extends Dialogue {


	@Override
	public void start() {
		int itemId = (int) parameters[0];
		sendOptionsDialogue("Are you sure you want to exchange this <col=ff0000>" + ItemDefinitions.getItemDefinitions(itemId).getName() + "</col>?", "Yes!", "No thanks.");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		int itemId = (int) parameters[0];
		if (stage == -1) {
			if (componentId == OPTION_1) {
				RecycleCentreMain.ExchangeItem(player, itemId);
				player.getActionManager().addActionDelay(2);
				end();
				stage = 3;
				return;
			} else {
				end();
				stage = 3;
			}
		} else if (stage == 2) {
			if (componentId == OPTION_2) {
				stage = 3;
				end();
				return;
			}
		} else if (stage == 3) {
			end();
		}

	}

	@Override
	public void finish() {

	}

}