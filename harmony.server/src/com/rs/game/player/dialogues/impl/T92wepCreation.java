package com.rs.game.player.dialogues.impl;

import com.rs.game.World;
import com.rs.game.player.dialogues.Dialogue;

public class T92wepCreation extends Dialogue {

	public T92wepCreation() {
	}

	@Override
	public void start() {
		stage = 1;
		sendOptionsDialogue("Choose an item", "Seren Godbow", "Staff of Sliske", "Zaros Godsword", "Nevermind");

	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == 1) {
			if (componentId == OPTION_1) {
				if (player.getInventory().containsItem(29533, 1) 
						&& player.getInventory().containsItem(29534, 1)
						&& player.getInventory().containsItem(29535, 1)
						&& player.getInventory().containsItem(29536, 1)) {
					player.getInventory().deleteItem(29533, 1);
					player.getInventory().deleteItem(29534, 1);
					player.getInventory().deleteItem(29535, 1);
					player.getInventory().deleteItem(29536, 1);
					player.getInventory().addItem(29547, 1);
					player.getPackets().sendGameMessage("You create the Seren godbow");
					World.sendWorldMessage("Epic News: <col=ff0000>"+player.getDisplayName()+" has created the Seren Godbow!", false);
					end();
					return;
				} else {
					player.getPackets().sendGameMessage(
							"You need all Raptor components & the Seren Component to create this.");
					end();
				}
			} else if (componentId == OPTION_2) {
				if (player.getInventory().containsItem(29533, 1) 
						&& player.getInventory().containsItem(29534, 1)
						&& player.getInventory().containsItem(29535, 1)
						&& player.getInventory().containsItem(22323, 1)) {
					player.getInventory().deleteItem(29533, 1);
					player.getInventory().deleteItem(29534, 1);
					player.getInventory().deleteItem(29535, 1);
					player.getInventory().deleteItem(22323, 1);
					player.getInventory().addItem(29546, 1);
					player.getPackets().sendGameMessage("You create the Staff of Sliske");
					World.sendWorldMessage("Epic News: <col=ff0000>"+player.getDisplayName()+" has created the Staff of Sliske!", false);
					end();
					return;
				} else {
					player.getPackets().sendGameMessage(
							"You need all Raptor components & the Sliske Mask to create this.");
					end();
				}
			} else if (componentId == OPTION_3) {
				if (player.getInventory().containsItem(29533, 1) 
						&& player.getInventory().containsItem(29534, 1)
						&& player.getInventory().containsItem(29535, 1)
						&& player.getInventory().containsItem(6821, 1)) {
					player.getInventory().deleteItem(29533, 1);
					player.getInventory().deleteItem(29534, 1);
					player.getInventory().deleteItem(29535, 1);
					player.getInventory().deleteItem(6821, 1);
					player.getInventory().addItem(29545, 1);
					player.getPackets().sendGameMessage("You create the Zaros Godsword");
					World.sendWorldMessage("Epic News: <col=ff0000>"+player.getDisplayName()+" has created the Zaros Godsword!", false);
					end();
					return;
				} else {
					player.getPackets().sendGameMessage(
							"You need all Raptor components & the Zaros Orb to create this.");
					end();
				}
			} else if (componentId == OPTION_4) {
				end();
				stage = 4;
			}
		} else if (stage == 4) {

			end();
		}
	}

	@Override
	public void finish() {
	}

}
