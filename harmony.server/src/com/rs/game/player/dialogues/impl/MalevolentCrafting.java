package com.rs.game.player.dialogues.impl;

import com.rs.game.player.dialogues.Dialogue;

public class MalevolentCrafting extends Dialogue {

	public MalevolentCrafting() {
	}

	@Override
	public void start() {
		stage = 1;
		sendOptionsDialogue("<col=ff0000>THESE ITEMS DEGRADE</col>", "Malevolent Helm(14)",
				"Malevolent Body(42)", "Malevolent Legs(28)", "Nothing");

	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == 1) {
			if (componentId == OPTION_1) {
				if (player.getInventory().containsItem(29942, 14)) {
					player.getInventory().deleteItem(29942, 14);
					player.getInventory().addItem(29940, 1);
					player.getPackets().sendGameMessage("You create a Malevolent Helm");
					end();
					return;
				}
				else {
					player.getPackets().sendGameMessage("You need 14 Malevolent Energy to do this.");
					end();
				}
			} else if (componentId == OPTION_2) {
				if (player.getInventory().containsItem(29942, 42)) {
					player.getInventory().deleteItem(29942, 42);
					player.getInventory().addItem(29941, 1);
					player.getPackets().sendGameMessage("You create a Malevolent Body");
					end();
					return;
				}
				else {
					player.getPackets().sendGameMessage("You need 42 Malevolent Energy to do this.");
					end();
				}
			} else if (componentId == OPTION_3) {
				if (player.getInventory().containsItem(29942, 28)) {
					player.getInventory().deleteItem(29942, 28);
					player.getInventory().addItem(29939, 1);
					player.getPackets().sendGameMessage("You create a set of Malevolent Legs");
					end();
					return;
				}
				else {
					player.getPackets().sendGameMessage("You need 28 Malevolent Energy to do this."); 
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
