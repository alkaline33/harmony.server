package com.rs.game.player.dialogues.impl;

import com.rs.game.player.dialogues.Dialogue;

public class Runecoiner extends Dialogue {

	public Runecoiner() {
	}

	@Override
	public void start() {
		stage = 1;
		sendOptionsDialogue("Set the max value of drops you want to turn into Runecoins.", "1 million", "5 million", "10 million", "50 million", "Everything");

	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == 1) {
			if (componentId == OPTION_1) {
				player.runecoinervalue = 1000000;
				end();
			} else if (componentId == OPTION_2) {
				player.runecoinervalue = 5000000;
				end();
			} else if (componentId == OPTION_3) {
				player.runecoinervalue = 10000000;
				end();
			} else if (componentId == OPTION_4) {
				player.runecoinervalue = 50000000;
				end();
			} else {
				player.runecoinervalue = Integer.MAX_VALUE;
				end();
			}
		} else if (stage == 4) {

			end();
		}
	}

	@Override
	public void finish() {
	}

}
