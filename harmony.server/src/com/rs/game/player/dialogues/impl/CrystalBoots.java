package com.rs.game.player.dialogues.impl;

import com.rs.Settings;
import com.rs.game.player.Skills;
import com.rs.game.player.dialogues.Dialogue;

public class CrystalBoots extends Dialogue {

	/**
	 * 
	 * Author Mr_Joopz
	 * 
	 */

	public CrystalBoots() {
	}

	@Override
	public void start() {
		stage = 1;
		sendOptionsDialogue("Which boots would you like to upgrade?", "Ragefires", "Glaivens", "Steadfast", "None");

	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == 1) {
			if (componentId == OPTION_1) {
				if (player.getInventory().containsItem(21793, 1) && player.getInventory().containsItem(10506, 1)
						&& player.getInventory().containsItem(995, 200000000)) {
					player.getInventory().deleteItem(21793, 1);
					player.getInventory().deleteItem(10506, 1);
					player.getInventory().deleteItem(995, 200000000);
					Settings.GpSyncAmount += 200000000;
					player.getInventory().addItem(29570, 1);
					player.getSkills().addXp(Skills.CRAFTING, 1000);
					player.getPackets().sendGameMessage("You imbue the Crystals into the boots.");
					end();
					return;
				} else {
					player.getPackets().sendGameMessage(
							"You need 200M coins, a crystal shard and a pair of Ragefire boots to do this.");
					end();
				}
			} else if (componentId == OPTION_2) {
				if (player.getInventory().containsItem(21790, 1) && player.getInventory().containsItem(10506, 1)
						&& player.getInventory().containsItem(995, 200000000)) {
					player.getInventory().deleteItem(21790, 1);
					player.getInventory().deleteItem(10506, 1);
					Settings.GpSyncAmount += 200000000;
					player.getInventory().deleteItem(995, 200000000);
					player.getInventory().addItem(29569, 1);
					player.getSkills().addXp(Skills.CRAFTING, 1000);
					player.getPackets().sendGameMessage("You imbue the Crystals into the boots.");
					end();
					return;
				} else {
					player.getPackets().sendGameMessage(
							"You need 200M coins, a crystal shard and a pair of Ragefire boots to do this.");
					end();
				}
			} else if (componentId == OPTION_3) {
				if (player.getInventory().containsItem(21787, 1) && player.getInventory().containsItem(10506, 1)
						&& player.getInventory().containsItem(995, 200000000)) {
					player.getInventory().deleteItem(21787, 1);
					player.getInventory().deleteItem(10506, 1);
					Settings.GpSyncAmount += 200000000;
					player.getInventory().deleteItem(995, 200000000);
					player.getInventory().addItem(29571, 1);
					player.getSkills().addXp(Skills.CRAFTING, 1000);
					player.getPackets().sendGameMessage("You imbue the Crystals into the boots.");
					end();
					return;
				} else {
					player.getPackets().sendGameMessage(
							"You need 200M coins, a crystal shard and a pair of Ragefire boots to do this.");
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
