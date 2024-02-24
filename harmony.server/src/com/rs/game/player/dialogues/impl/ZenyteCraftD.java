package com.rs.game.player.dialogues.impl;

import com.rs.game.player.dialogues.Dialogue;

public class ZenyteCraftD extends Dialogue {

	public ZenyteCraftD() {
	}

	@Override
	public void start() {
		stage = 1;
		sendOptionsDialogue("Which item?", "Ring of suffering",
				"Amulet of torture", "Necklace of anguish", "Tormented bracelet");

	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == 1) {
			if (componentId == OPTION_1) {
				if (player.getInventory().containsItem(29592, 1)) {
					player.getInventory().deleteItem(29592, 1);
					player.getInventory().addItem(29591, 1);
					player.getPackets().sendGameMessage("You create a ring of suffering");
					end();
					return;
				}
				else {
					player.getPackets().sendGameMessage("You need a Zenyte shard to do this."); //game message if you do not have 100 shard
					end();
				}
			} else if (componentId == OPTION_2) {
				if (player.getInventory().containsItem(29592, 1)) {
					player.getInventory().deleteItem(29592, 1);
					player.getInventory().addItem(29590, 1);
					player.getPackets().sendGameMessage("You create an Amulet of torture");
					end();
					return;
				}
				else {
					player.getPackets().sendGameMessage("You need a Zenyte shard to do this."); //game message if you do not have 100 shard
					end();
				}
			} else if (componentId == OPTION_3) {
				if (player.getInventory().containsItem(29592, 1)) {
					player.getInventory().deleteItem(29592, 1);
					player.getInventory().addItem(29589, 1);
					player.getPackets().sendGameMessage("You create a Necklace of anguish");
					end();
					return;
				}
				else {
					player.getPackets().sendGameMessage("You need a Zenyte shard to do this."); 
					end();
				}
			} else if (componentId == OPTION_4) {
				if (player.getInventory().containsItem(29592, 1)) {
					player.getInventory().deleteItem(29592, 1);
					player.getInventory().addItem(29588, 1);
					player.getPackets().sendGameMessage("You create a Tormented bracelet");
					end();
					return;
				}
				else {
					player.getPackets().sendGameMessage("You need a Zenyte shard to do this."); 
					end();
				}
			}
			}
		}
		

	@Override
	public void finish() {
	}

}
