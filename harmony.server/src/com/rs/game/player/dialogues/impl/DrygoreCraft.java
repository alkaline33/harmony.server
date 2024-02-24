package com.rs.game.player.dialogues.impl;

import com.rs.game.player.dialogues.Dialogue;

public class DrygoreCraft extends Dialogue {

	public DrygoreCraft() {
	}

	@Override
	public void start() {
		stage = 1;
		sendOptionsDialogue("Main Hands", "Drygore Longsword",
				"Drygore Rapier", "Drygore Mace", "Next Page");

	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == 1) {
			if (componentId == OPTION_1) {
				if (player.getInventory().containsItem(29929, 30) && player.getInventory().containsItem(18351, 1)) {
					player.getInventory().deleteItem(29929, 30);
					player.getInventory().deleteItem(18351, 1);
					player.getInventory().addItem(29973, 1);
					player.getPackets().sendGameMessage("You create a Drygore longsword");
					end();
					return;
				}
				else {
					player.getPackets().sendGameMessage("You need 30 drygore longsword shards & 1 chaotic longsword to make a drygore longsword!"); //game message if you do not have 100 shard
					end();
				}
			} else if (componentId == OPTION_2) {
				if (player.getInventory().containsItem(29931, 30) && player.getInventory().containsItem(18349, 1)) {
					player.getInventory().deleteItem(29931, 30);
					player.getInventory().deleteItem(18350, 1);
					player.getInventory().addItem(29971, 1);
					player.getPackets().sendGameMessage("You create a Drygore rapier");
					end();
					return;
				}
				else {
					player.getPackets().sendGameMessage("You need 30 drygore rapier shards & 1 chaotic rapier to make a drygore rapier!"); //game message if you do not have 100 shard
					end();
				}
			} else if (componentId == OPTION_3) {
				if (player.getInventory().containsItem(29930, 30) && player.getInventory().containsItem(18353, 1)) {
					player.getInventory().deleteItem(29930, 30);
					player.getInventory().deleteItem(18353, 1);
					player.getInventory().addItem(29972, 1);
					player.getPackets().sendGameMessage("You create a Drygore mace");
					end();
					return;
				}
				else {
					player.getPackets().sendGameMessage("You need 30 drygore mace shards & 1 chaotic maul to make a drygore mace!"); 
					end();
				}
			} else if (componentId == OPTION_4) {
				sendOptionsDialogue("Off-hands",
						"Drygore Longsword", "Drygore Rapier", "Drygore Mace", "End");
				stage = 4;
			}
		} else if (stage == 4) {
			if (componentId == OPTION_1) {	
				if (player.getInventory().containsItem(29929, 30)) {
					player.getInventory().deleteItem(29929, 30);
					player.getInventory().addItem(29970, 1);
					player.getPackets().sendGameMessage("You create a Drygore off-hand longsword");
					end();
					return;
				}
				else {
					player.getPackets().sendGameMessage("You need 30 drygore longsword shards to make a drygore off-hand longsword!"); //game message if you do not have 100 shard
					end();
				}
			} else if (componentId == OPTION_2) {
				if (player.getInventory().containsItem(29931, 30)) {
					player.getInventory().deleteItem(29931, 30);
					player.getInventory().addItem(29968, 1);
					player.getPackets().sendGameMessage("You create a Drygore off-hand rapier");
					end();
					return;
				}
				else {
					player.getPackets().sendGameMessage("You need 30 drygore rapier shards to make a drygore off-hand rapier!"); //game message if you do not have 100 shard
					end();
				}
			} else if (componentId == OPTION_3) {
				if (player.getInventory().containsItem(29930, 30)) {
					player.getInventory().deleteItem(29930, 30);
					player.getInventory().addItem(29969, 1);
					player.getPackets().sendGameMessage("You create a Drygore off-hand mace");
					end();
					return;
				}
				else {
					player.getPackets().sendGameMessage("You need 30 drygore mace shards to make a drygore off-hand mace!"); 
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
