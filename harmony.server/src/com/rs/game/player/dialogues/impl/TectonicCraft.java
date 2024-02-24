package com.rs.game.player.dialogues.impl;

import com.rs.game.player.dialogues.Dialogue;

public class TectonicCraft extends Dialogue {

	public TectonicCraft() {
	}

	@Override
	public void start() {
		stage = 1;
		sendOptionsDialogue("Which item?", "Tectonic Helm(14)",
				"Tectonic Body(42)", "Tectonic Legs(28)", "Nothing");

	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == 1) {
			if (componentId == OPTION_1) {
				if (player.getInventory().containsItem(29843, 14)) {
					player.getInventory().deleteItem(29843, 14);
					player.getInventory().addItem(29846, 1);
					player.getPackets().sendGameMessage("You create a Tectonic Helm");
					end();
					return;
				}
				else {
					player.getPackets().sendGameMessage("You need 14 Tectonic energy to do this."); //game message if you do not have 100 shard
					end();
				}
			} else if (componentId == OPTION_2) {
				if (player.getInventory().containsItem(29843, 42)) {
					player.getInventory().deleteItem(29843, 42);
					player.getInventory().addItem(29844, 1);
					player.getPackets().sendGameMessage("You create a Tectonic Body");
					end();
					return;
				}
				else {
					player.getPackets().sendGameMessage("You need 42 Tectonic energy to do this."); //game message if you do not have 100 shard
					end();
				}
			} else if (componentId == OPTION_3) {
				if (player.getInventory().containsItem(29843, 28)) {
					player.getInventory().deleteItem(29843, 28);
					player.getInventory().addItem(29845, 1);
					player.getPackets().sendGameMessage("You create a set of Tectonic Legs");
					end();
					return;
				}
				else {
					player.getPackets().sendGameMessage("You need 28 Tectonic energy to do this."); 
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
