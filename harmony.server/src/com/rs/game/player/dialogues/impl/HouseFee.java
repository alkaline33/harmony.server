package com.rs.game.player.dialogues.impl;

import com.rs.game.player.dialogues.Dialogue;

public class HouseFee extends Dialogue {

	private int npcId = 4247;

	@Override
	public void start() {
		//npcId = (Integer) parameters[0];
		sendNPCDialogue(
				npcId,
				9827,
				"Hey "
						+ player.getUsername()
						+ ", I'm the Estate Agent. I'm in charge of the yanille houses! What can I do for you?");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			sendOptionsDialogue("Estate Agent", 
					"Sell me a Bronze house! (1.25M)",
					"Sell me a Mithril house! (5M)",
					"Sell me a Rune house! (10M)",
					"Sell me a Dragon house! (40M)");
			stage = 2;
		} else if (stage == 2) {
			if (componentId == OPTION_1) {
				if (player.bronzehouse >= 1) {
					sendNPCDialogue(
							npcId,
							9790,
							"Sir, stop! You've already purchased this property.");
				stage = 3;
					return;
				}
				if(player.getInventory().containsItem(995, 1250000)) {
						player.getInventory().deleteItem(995, 1250000);
						player.bronzehouse = 1;
				end();
			}
			if (componentId == OPTION_5) {
				sendNPCDialogue(npcId, 9827,
						"You currently have " + player.getLoyaltyPoints()
								+ " Loyalty Points.");
				stage = 3;
			}
			} else if (stage == 2) {
			if (componentId == OPTION_3) {
				if (player.runehouse >= 1) {
					sendNPCDialogue(
							npcId,
							9790,
							"Sir, stop! You've already purchased this property.");
				stage = 3;
					return;
				}
				if(player.getInventory().containsItem(995, 10000000)) {
						player.getInventory().deleteItem(995, 10000000);
						player.runehouse = 1;
				end();
			}
			} else if (stage == 2) {
			if (componentId == OPTION_4) {
				if (player.dragonhouse >= 1) {
					sendNPCDialogue(
							npcId,
							9790,
							"Sir, stop! You've already purchased this property.");
				stage = 3;
					return;
				}
				if(player.getInventory().containsItem(995, 40000000)) {
						player.getInventory().deleteItem(995, 40000000);
						player.dragonhouse = 1;
				end();
			}
			} else if (stage == 2) {
			if (componentId == OPTION_2) {
				if (player.mithrilhouse >= 1) {
					sendNPCDialogue(
							npcId,
							9790,
							"Sir, stop! You've already purchased this property.");
				stage = 3;
					return;
				}
				if(player.getInventory().containsItem(995, 5000000)) {
						player.getInventory().deleteItem(995, 5000000);
						player.mithrilhouse = 1;
				end();
			}
		} else if (stage == 3) {
			end();
		}
	}
}
	}
		}
	}

	@Override
	public void finish() {

	}

}