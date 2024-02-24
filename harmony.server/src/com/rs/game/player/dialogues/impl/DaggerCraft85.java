package com.rs.game.player.dialogues.impl;

import com.rs.game.player.dialogues.Dialogue;

public class DaggerCraft85 extends Dialogue {

	public DaggerCraft85() {
	}

	@Override
	public void start() {
		stage = 1;
		sendOptionsDialogue("Daggers", "Fortem Dagger",
				"Curare Dagger", "Glacies Dagger", "Nevermind");

	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == 1) {
			if (componentId == OPTION_1) {
				if (player.getInventory().containsItem(29581, 1) && player.getInventory().containsItem(29582, 1)) {
					player.getInventory().deleteItem(29581, 1);
					player.getInventory().deleteItem(29582, 1);
					player.getInventory().addItem(29583, 1);
					end();
					return;
				}
				else {
					player.getPackets().sendGameMessage("You need an uncut Gloria and an unfinished dagger todo this."); //game message if you do not have 100 shard
					end();
				}
			} else if (componentId == OPTION_2) {
				if (player.getInventory().containsItem(29581, 1) && player.getInventory().containsItem(29582, 1)) {
					player.getInventory().deleteItem(29581, 1);
					player.getInventory().deleteItem(29582, 1);
					player.getInventory().addItem(29584, 1);
					end();
					return;
				}
				else {
					player.getPackets().sendGameMessage("You need an uncut Gloria and an unfinished dagger todo this."); //game message if you do not have 100 shard
					end();
				}
			} else if (componentId == OPTION_3) {
				if (player.getInventory().containsItem(29581, 1) && player.getInventory().containsItem(29582, 1)) {
					player.getInventory().deleteItem(29581, 1);
					player.getInventory().deleteItem(29582, 1);
					player.getInventory().addItem(29585, 1);
					end();
					return;
				}
				else {
					player.getPackets().sendGameMessage("You need an uncut Gloria and an unfinished dagger todo this."); //game message if you do not have 100 shard
					end();
				}
			} else if (componentId == OPTION_4) {
				end();
			}
		}
	}
		

	@Override
	public void finish() {
	}

}
