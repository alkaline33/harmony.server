package com.rs.game.player.dialogues.impl;

import com.rs.game.player.dialogues.Dialogue;

public class GlacorBootUpgrade extends Dialogue {

	public GlacorBootUpgrade() {
	}

	@Override
	public void start() {
		stage = 1;
		sendEntityDialogue(IS_NPC, "Hans", 0, 9827, "Before proceeding, i must inform you that ocne you upgrade the boots, they will become untradeable!");

	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == 1) {
			stage = 2;
			sendOptionsDialogue("Upgrade", "Ragefires",
					"Glaivens", "Steadfast", "Nevermind");
		} else if (stage == 2) {
			if (componentId == OPTION_1) {
				if (player.getInventory().containsItem(29714, 1) && player.getInventory().containsItem(21793, 1)) {
					player.getInventory().deleteItem(29714, 1);
					player.getInventory().deleteItem(21793, 1);
					player.getInventory().addItem(29707, 1);
					player.getPackets().sendGameMessage("You upgrade your ragefire boots.");
					end();
					return;
				}
				else {
					player.getPackets().sendGameMessage("You need a pair of ragefire boots & a ragefire gland to do this."); //game message if you do not have 100 shard
					end();
				}
			} else if (componentId == OPTION_2) {
				if (player.getInventory().containsItem(29713, 1) && player.getInventory().containsItem(21790, 1)) {
					player.getInventory().deleteItem(29713, 1);
					player.getInventory().deleteItem(21790, 1);
					player.getInventory().addItem(29708, 1);
					player.getPackets().sendGameMessage("You upgrade your glaiven boots.");
					end();
					return;
				}
				else {
					player.getPackets().sendGameMessage("You need a pair of glaiven boots & a glaiven wing-tip to do this."); //game message if you do not have 100 shard
					end();
				}
			} else if (componentId == OPTION_3) {
				if (player.getInventory().containsItem(29715, 1) && player.getInventory().containsItem(21787, 1)) {
					player.getInventory().deleteItem(29715, 1);
					player.getInventory().deleteItem(21787, 1);
					player.getInventory().addItem(29709, 1);
					player.getPackets().sendGameMessage("You upgrade your steadfast boots.");
					end();
					return;
				}
				else {
					player.getPackets().sendGameMessage("You need a pair of steadfast boots & a steadfast scale to do this."); //game message if you do not have 100 shard
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
