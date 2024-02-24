package com.rs.game.player.dialogues.impl;

import com.rs.game.player.content.WildernessTasks;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.utils.Colors;

public class WildernessTasksD extends Dialogue {

	/**
	 * Author @ Mr_Joopz
	 */

	public WildernessTasksD() {
	}

	@Override
	public void start() {
		stage = 1;
		sendOptionsDialogue("What do you need?", "What is my current Wilderness task?", "I need a new task", "Re-roll Task.", "Nevermind");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == 1) {
			if (componentId == OPTION_1) {
				WildernessTasks.CurrentChallenge(player);
				end();
			} else if (componentId == OPTION_2) {
				if (player.challengeamount > 0) {
					player.sendMessage(Colors.red + "You already have a task active! Complete it or wait till it resets.");
					end();
				} else {
				WildernessTasks.GrabChallenge(player);
				end();
				}
			} else if (componentId == OPTION_3) {
				if (player.challengeamount > 0 && player.wildtaskrerollchallenge > 0) {
					WildernessTasks.GrabChallenge(player);
					player.wildtaskrerollchallenge--;
					end();
				} else {
					player.sendMessage("You have already used your re-roll's today!");
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
