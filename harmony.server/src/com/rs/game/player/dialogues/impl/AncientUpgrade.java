package com.rs.game.player.dialogues.impl;
/*package com.rs.game.player.dialogues;


public class AncientUpgrade extends Dialogue {

	public AncientUpgrade() {
	}

	@Override
	public void start() {
		stage = 1;
		sendOptionsDialogue("Torva", "Torva Full Helm",
				"Torva Platebody", "Torva Platelegs", "Next Page");

	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == 1) {
			if (componentId == OPTION_1) {
				if (player.getInventory().containsItem(20135, 1) && player.getInventory().containsItem(29902, 1)) {
					player.getInventory().deleteItem(20135, 1);
					player.getInventory().deleteItem(29902, 1);
					player.getInventory().addItem(29900, 1);
					player.getPackets().sendGameMessage("You have upgraded your Torva Full Helm to (Level 90).");
					end();
					return;
				}
				else {
					player.getPackets().sendGameMessage("You need a Torva Full Helm and an ancient upgrade kit to do this."); //game message if you do not have 100 shard
					end();
				}
			} else if (componentId == OPTION_2) {
				if (player.getInventory().containsItem(20139, 1) && player.getInventory().containsItem(29902, 1)) {
					player.getInventory().deleteItem(29902, 1);
					player.getInventory().deleteItem(20139, 1);
					player.getInventory().addItem(29899, 1);
					player.getPackets().sendGameMessage("You have upgraded your Torva Platebody to (Level 90).");
					end();
					return;
				}
				else {
					player.getPackets().sendGameMessage("You need a Torva Platebody and an ancient upgrade kit to do this."); //game message if you do not have 100 shard
					end();
				}
			} else if (componentId == OPTION_3) {
				if (player.getInventory().containsItem(20143, 1) && player.getInventory().containsItem(29902, 1)) {
					player.getInventory().deleteItem(20143, 1);
					player.getInventory().deleteItem(29902, 1);
					player.getInventory().addItem(29898, 1);
					player.getPackets().sendGameMessage("You have upgraded your Torva Platelegs to (Level 90).");
					end();
					return;
				}
				else {
					player.getPackets().sendGameMessage("You need a set of Torva Platelegs and an ancient upgrade kit to do this."); 
					end();
				}
			} else if (componentId == OPTION_4) {
				sendOptionsDialogue("Pernix",
						"Pernix Cowl", "Pernix Body", "Pernix Chaps", "Next Page");
				stage = 4;
			}
		} else if (stage == 4) {
			if (componentId == OPTION_1) {	
				if (player.getInventory().containsItem(20147, 1) && player.getInventory().containsItem(29902, 1)) {
					player.getInventory().deleteItem(20147, 1);
					player.getInventory().deleteItem(29902, 1);
					player.getInventory().addItem(29897, 1);
					player.getPackets().sendGameMessage("You have upgraded your Pernix Cowl to (Level 90).");
					end();
					return;
				}
				else {
					player.getPackets().sendGameMessage("You need a Pernix Cowl and an ancient upgrade kit to do this."); //game message if you do not have 100 shard
					end();
				}
			} else if (componentId == OPTION_2) {
				if (player.getInventory().containsItem(20151, 1) && player.getInventory().containsItem(29902, 1)) {
					player.getInventory().deleteItem(29902, 1);
					player.getInventory().deleteItem(20151, 1);
					player.getInventory().addItem(29896, 1);
					player.getPackets().sendGameMessage("You have upgraded your Pernix Body to (Level 90).");
					end();
					return;
				}
				else {
					player.getPackets().sendGameMessage("You need a Pernix Body and an ancient upgrade kit to do this."); //game message if you do not have 100 shard
					end();
				}
			} else if (componentId == OPTION_3) {
				if (player.getInventory().containsItem(20155, 1) && player.getInventory().containsItem(29902, 1)) {
					player.getInventory().deleteItem(29902, 1);
					player.getInventory().deleteItem(20155, 1);
					player.getInventory().addItem(29895, 1);
					player.getPackets().sendGameMessage("You have upgraded your Pernix Chaps to (Level 90).");
					end();
					return;
				}
				else {
					player.getPackets().sendGameMessage("You need a set of Pernix Chaps and an ancient upgrade kit to do this."); //game message if you do not have 100 shard
					end();
				}
			} else if (componentId == OPTION_4) {
				sendOptionsDialogue("Virtus",
						"Virtus Mask", "Virtus Robe Top", "Virtus Robe Legs", "End");
				stage = 5;
			}
		} else if (stage == 5) {
			if (componentId == OPTION_1) {	
				if (player.getInventory().containsItem(20159, 1) && player.getInventory().containsItem(29902, 1)) {
					player.getInventory().deleteItem(20159, 1);
					player.getInventory().deleteItem(29902, 1);
					player.getInventory().addItem(29894, 1);
					player.getPackets().sendGameMessage("You have upgraded your Virtus Mask to (Level 90).");
					end();
					return;
				}
				else {
					player.getPackets().sendGameMessage("You need a Virtus Mask and an ancient upgrade kit to do this."); //game message if you do not have 100 shard
					end();
				}
			} else if (componentId == OPTION_2) {
				if (player.getInventory().containsItem(20163, 1) && player.getInventory().containsItem(29902, 1)) {
					player.getInventory().deleteItem(29902, 1);
					player.getInventory().deleteItem(20163, 1);
					player.getInventory().addItem(29893, 1);
					player.getPackets().sendGameMessage("You have upgraded your Virtus Robe Top to (Level 90).");
					end();
					return;
				}
				else {
					player.getPackets().sendGameMessage("You need a Virtus Robe Top and an ancient upgrade kit to do this."); //game message if you do not have 100 shard
					end();
				}
			} else if (componentId == OPTION_3) {
				if (player.getInventory().containsItem(20167, 1) && player.getInventory().containsItem(29902, 1)) {
					player.getInventory().deleteItem(29902, 1);
					player.getInventory().deleteItem(20167, 1);
					player.getInventory().addItem(29892, 1);
					player.getPackets().sendGameMessage("You have upgraded your Virtus Robe Legs to (Level 90).");
					end();
					return;
				}
				else {
					player.getPackets().sendGameMessage("You need a set of Virtus Robe Legs and an ancient upgrade kit to do this."); //game message if you do not have 100 shard
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
*/