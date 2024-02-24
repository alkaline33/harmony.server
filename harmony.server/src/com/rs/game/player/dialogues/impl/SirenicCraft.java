package com.rs.game.player.dialogues.impl;

import com.rs.game.player.dialogues.Dialogue;

public class SirenicCraft extends Dialogue {

	public SirenicCraft() {
	}

	@Override
	public void start() {
		stage = 1;
		sendOptionsDialogue("Which item?", "Sirenic Helm(14)",
				"Sirenic Body(42)", "Sirenic Legs(28)", "Nothing");

	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == 1) {
			if (componentId == OPTION_1) {
				if (player.getInventory().containsItem(29887, 14)) {
					player.getInventory().deleteItem(29887, 14);
					player.getInventory().addItem(29890, 1);
					player.getPackets().sendGameMessage("You create a Sirenic Helm");
					player.craftedsirenic++;
					end();
					return;
				}
				else {
					player.getPackets().sendGameMessage("You need 14 sirenic scales to do this."); //game message if you do not have 100 shard
					end();
				}
			} else if (componentId == OPTION_2) {
				if (player.getInventory().containsItem(29887, 42)) {
					player.getInventory().deleteItem(29887, 42);
					player.getInventory().addItem(29889, 1);
					player.getPackets().sendGameMessage("You create a Sirenic Body");
					player.craftedsirenic++;
					end();
					return;
				}
				else {
					player.getPackets().sendGameMessage("You need 42 sirenic scales to do this."); //game message if you do not have 100 shard
					end();
				}
			} else if (componentId == OPTION_3) {
				if (player.getInventory().containsItem(29887, 28)) {
					player.getInventory().deleteItem(29887, 28);
					player.getInventory().addItem(29888, 1);
					player.getPackets().sendGameMessage("You create a set of Sirenic Legs");
					player.craftedsirenic++;
					end();
					return;
				}
				else {
					player.getPackets().sendGameMessage("You need 28 sirenic scales to do this."); 
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
