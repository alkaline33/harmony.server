package com.rs.game.player.dialogues.impl;

import com.rs.game.player.content.DailyChallenges;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.utils.ShopsHandler;

public class DailyChallengeD extends Dialogue {

	/**
	 * Author @ Mr_Joopz
	 */

	public DailyChallengeD() {
	}

	@Override
	public void start() {
		stage = 1;
		sendOptionsDialogue("What do you need?", "What is my current daily challenge?", "Can you show me your shop?", "Reroll Challenge.",
				"Nevermind");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == 1) {
			if (componentId == OPTION_1) {
				DailyChallenges.CurrentChallenge(player);
				end();
			} else if (componentId == OPTION_2) {
				ShopsHandler.openShop(player, 115);
				end();
			} else if (componentId == OPTION_3) {
				if (player.getRights() >= 2) 
					DailyChallenges.GrabChallenge(player);
				
				if (player.challengeamount > 0 && player.dailyrerollchallenge != true) {
				DailyChallenges.GrabChallenge(player);
				player.dailyrerollchallenge = true;
				end();
				} else {
					player.sendMessage("You have already used your reroll today!");
					end();
				}
			}
		} else {
			end();
		}
	}

	@Override
	public void finish() {
	}

}
