package com.rs.game.player.dialogues.dyes;

import com.rs.game.player.dialogues.Dialogue;


public class Acid extends Dialogue {

	public Acid() {
	}

	@Override
	public void start() {
		stage = 1;
		sendOptionsDialogue("CANNOT BE UNDONE", "Drygore Longsword",
				"Drygore Rapier", "Drygore Mace", "Next Page");

	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == 1) {
			if (componentId == OPTION_1) {
				if (player.getInventory().containsItem(29805, 1) && player.getInventory().containsItem(29973, 1)) {
					player.getInventory().deleteItem(29805, 1);
					player.getInventory().deleteItem(29973, 1);
					player.getInventory().addItem(29813, 1);
					player.getPackets().sendGameMessage("The acid dye is added to your item.");
					end();
					return;
				}
				else {
					player.getPackets().sendGameMessage("You need an acid dye and the respectable item to do this."); //game message if you do not have 100 shard
					end();
				}
			} else if (componentId == OPTION_2) {
				if (player.getInventory().containsItem(29805, 1) && player.getInventory().containsItem(29971, 1)) {
					player.getInventory().deleteItem(29805, 1);
					player.getInventory().deleteItem(29971, 1);
					player.getInventory().addItem(29811, 1);
					player.getPackets().sendGameMessage("The acid dye is added to your item.");
					end();
					return;
				}
				else {
					player.getPackets().sendGameMessage("You need an acid dye and the respectable item to do this."); //game message if you do not have 100 shard
					end();
				}
			} else if (componentId == OPTION_3) {
				if (player.getInventory().containsItem(29805, 1) && player.getInventory().containsItem(29972, 1)) {
					player.getInventory().deleteItem(29805, 1);
					player.getInventory().deleteItem(29972, 1);
					player.getInventory().addItem(29809, 1);
					player.getPackets().sendGameMessage("The acid dye is added to your item.");
					end();
					return;
				}
				else {
					player.getPackets().sendGameMessage("You need an acid dye and the respectable item to do this."); //game message if you do not have 100 shard
					end();
				}
			} else if (componentId == OPTION_4) {
				sendOptionsDialogue("CANNOT BE UNDONE",
						"OH-Drygore Longsword", "OH-Drygore Rapier", "OH-Drygore Mace", "Next");
				stage = 4;
			}
		} else if (stage == 4) {
			if (componentId == OPTION_1) {	
				if (player.getInventory().containsItem(29805, 1) && player.getInventory().containsItem(29970, 1)) {
					player.getInventory().deleteItem(29805, 1);
					player.getInventory().deleteItem(29970, 1);
					player.getInventory().addItem(29812, 1);
					player.getPackets().sendGameMessage("The acid dye is added to your item.");
					end();
					return;
				}
				else {
					player.getPackets().sendGameMessage("You need an acid dye and the respectable item to do this."); //game message if you do not have 100 shard
					end();
				}
			} else if (componentId == OPTION_2) {
				if (player.getInventory().containsItem(29805, 1) && player.getInventory().containsItem(29968, 1)) {
					player.getInventory().deleteItem(29805, 1);
					player.getInventory().deleteItem(29968, 1);
					player.getInventory().addItem(29810, 1);
					player.getPackets().sendGameMessage("The acid dye is added to your item.");
					end();
					return;
				}
				else {
					player.getPackets().sendGameMessage("You need an acid dye and the respectable item to do this."); //game message if you do not have 100 shard
					end();
				}
			} else if (componentId == OPTION_3) {
				if (player.getInventory().containsItem(29805, 1) && player.getInventory().containsItem(29969, 1)) {
					player.getInventory().deleteItem(29805, 1);
					player.getInventory().deleteItem(29969, 1);
					player.getInventory().addItem(29808, 1);
					player.getPackets().sendGameMessage("The acid dye is added to your item.");
					end();
					return;
				}
				else {
					player.getPackets().sendGameMessage("You need an acid dye and the respectable item to do this."); //game message if you do not have 100 shard
					end();
				}
			} else if (componentId == OPTION_4) {
				sendOptionsDialogue("CANNOT BE UNDONE",
						"Torva helmet", "Torva Platebody", "Torva Platelegs", "Next");
				stage = 5;
			}
		} else if (stage == 5) {
			if (componentId == OPTION_1) {	
				if (player.getInventory().containsItem(29805, 1) && player.getInventory().containsItem(20135, 1)) {
					player.getInventory().deleteItem(29805, 1);
					player.getInventory().deleteItem(20135, 1);
					player.getInventory().addItem(29824, 1);
					player.getPackets().sendGameMessage("The acid dye is added to your item.");
					end();
					return;
				}
				else {
					player.getPackets().sendGameMessage("You need an acid dye and the respectable item to do this."); //game message if you do not have 100 shard
					end();
				}
			} else if (componentId == OPTION_2) {
				if (player.getInventory().containsItem(29805, 1) && player.getInventory().containsItem(20139, 1)) {
					player.getInventory().deleteItem(29805, 1);
					player.getInventory().deleteItem(20139, 1);
					player.getInventory().addItem(29825, 1);
					player.getPackets().sendGameMessage("The acid dye is added to your item.");
					end();
					return;
				}
				else {
					player.getPackets().sendGameMessage("You need an acid dye and the respectable item to do this."); //game message if you do not have 100 shard
					end();
				}
			} else if (componentId == OPTION_3) {
				if (player.getInventory().containsItem(29805, 1) && player.getInventory().containsItem(20143, 1)) {
					player.getInventory().deleteItem(29805, 1);
					player.getInventory().deleteItem(20143, 1);
					player.getInventory().addItem(29823, 1);
					player.getPackets().sendGameMessage("The acid dye is added to your item.");
					end();
					return;
				}
				else {
					player.getPackets().sendGameMessage("You need an acid dye and the respectable item to do this."); //game message if you do not have 100 shard
					end();
				}
			} else if (componentId == OPTION_4) {
				sendOptionsDialogue("CANNOT BE UNDONE",
						"Virtus mask", "Virtus robe top", "Virtus robe bottom", "Next");
				stage = 6;
			}
		} else if (stage == 6) {
			if (componentId == OPTION_1) {	
				if (player.getInventory().containsItem(29805, 1) && player.getInventory().containsItem(20159, 1)) {
					player.getInventory().deleteItem(29805, 1);
					player.getInventory().deleteItem(20159, 1);
					player.getInventory().addItem(29818, 1);
					player.getPackets().sendGameMessage("The acid dye is added to your item.");
					end();
					return;
				}
				else {
					player.getPackets().sendGameMessage("You need an acid dye and the respectable item to do this."); //game message if you do not have 100 shard
					end();
				}
			} else if (componentId == OPTION_2) {
				if (player.getInventory().containsItem(29805, 1) && player.getInventory().containsItem(20163, 1)) {
					player.getInventory().deleteItem(29805, 1);
					player.getInventory().deleteItem(20163, 1);
					player.getInventory().addItem(29819, 1);
					player.getPackets().sendGameMessage("The acid dye is added to your item.");
					end();
					return;
				}
				else {
					player.getPackets().sendGameMessage("You need an acid dye and the respectable item to do this."); //game message if you do not have 100 shard
					end();
				}
			} else if (componentId == OPTION_3) {
				if (player.getInventory().containsItem(29805, 1) && player.getInventory().containsItem(20167, 1)) {
					player.getInventory().deleteItem(29805, 1);
					player.getInventory().deleteItem(20167, 1);
					player.getInventory().addItem(29817, 1);
					player.getPackets().sendGameMessage("The acid dye is added to your item.");
					end();
					return;
				}
				else {
					player.getPackets().sendGameMessage("You need an acid dye and the respectable item to do this."); //game message if you do not have 100 shard
					end();
				}
			} else if (componentId == OPTION_4) {
				sendOptionsDialogue("CANNOT BE UNDONE",
						"Pernix cowl", "Pernix body", "Pernix chaps", "Next");
				stage = 7;
			}
		} else if (stage == 7) {
			if (componentId == OPTION_1) {	
				if (player.getInventory().containsItem(29805, 1) && player.getInventory().containsItem(20147, 1)) {
					player.getInventory().deleteItem(29805, 1);
					player.getInventory().deleteItem(20147, 1);
					player.getInventory().addItem(29821, 1);
					player.getPackets().sendGameMessage("The acid dye is added to your item.");
					end();
					return;
				}
				else {
					player.getPackets().sendGameMessage("You need an acid dye and the respectable item to do this."); //game message if you do not have 100 shard
					end();
				}
			} else if (componentId == OPTION_2) {
				if (player.getInventory().containsItem(29805, 1) && player.getInventory().containsItem(20151, 1)) {
					player.getInventory().deleteItem(29805, 1);
					player.getInventory().deleteItem(20151, 1);
					player.getInventory().addItem(29822, 1);
					player.getPackets().sendGameMessage("The acid dye is added to your item.");
					end();
					return;
				}
				else {
					player.getPackets().sendGameMessage("You need an acid dye and the respectable item to do this."); //game message if you do not have 100 shard
					end();
				}
			} else if (componentId == OPTION_3) {
				if (player.getInventory().containsItem(29805, 1) && player.getInventory().containsItem(20155, 1)) {
					player.getInventory().deleteItem(29805, 1);
					player.getInventory().deleteItem(20155, 1);
					player.getInventory().addItem(29820, 1);
					player.getPackets().sendGameMessage("The acid dye is added to your item.");
					end();
					return;
				}
				else {
					player.getPackets().sendGameMessage("You need an acid dye and the respectable item to do this."); //game message if you do not have 100 shard
					end();
				}
			} else if (componentId == OPTION_4) {
				sendOptionsDialogue("CANNOT BE UNDONE",
						"Ascension crossbow", "OH-Ascension crossbow", "Devious crossbow", "Next");
				stage = 8;
			}
		} else if (stage == 8) {
			if (componentId == OPTION_1) {	
				if (player.getInventory().containsItem(29805, 1) && player.getInventory().containsItem(29967, 1)) {
					player.getInventory().deleteItem(29805, 1);
					player.getInventory().deleteItem(29967, 1);
					player.getInventory().addItem(29806, 1);
					player.getPackets().sendGameMessage("The acid dye is added to your item.");
					end();
					return;
				}
				else {
					player.getPackets().sendGameMessage("You need an acid dye and the respectable item to do this."); //game message if you do not have 100 shard
					end();
				}
			} else if (componentId == OPTION_2) {
				if (player.getInventory().containsItem(29805, 1) && player.getInventory().containsItem(29918, 1)) {
					player.getInventory().deleteItem(29805, 1);
					player.getInventory().deleteItem(29918, 1);
					player.getInventory().addItem(29807, 1);
					player.getPackets().sendGameMessage("The acid dye is added to your item.");
					end();
					return;
				}
				else {
					player.getPackets().sendGameMessage("You need an acid dye and the respectable item to do this."); //game message if you do not have 100 shard
					end();
				}
			} else if (componentId == OPTION_3) {
				if (player.getInventory().containsItem(29805, 1) && player.getInventory().containsItem(29916, 1)) {
					player.getInventory().deleteItem(29805, 1);
					player.getInventory().deleteItem(29916, 1);
					player.getInventory().addItem(29816, 1);
					player.getPackets().sendGameMessage("The acid dye is added to your item.");
					end();
					return;
				}
				else {
					player.getPackets().sendGameMessage("You need an acid dye and the respectable item to do this."); //game message if you do not have 100 shard
					end();
				}
			} else if (componentId == OPTION_4) {
				sendOptionsDialogue("CANNOT BE UNDONE",
						"Seismic wand", "Seismic singularity", "End");
				stage = 9;
			}
		} else if (stage == 9) {
			if (componentId == OPTION_1) {	
				if (player.getInventory().containsItem(29805, 1) && player.getInventory().containsItem(29944, 1)) {
					player.getInventory().deleteItem(29805, 1);
					player.getInventory().deleteItem(29944, 1);
					player.getInventory().addItem(29815, 1);
					player.getPackets().sendGameMessage("The acid dye is added to your item.");
					end();
					return;
				}
				else {
					player.getPackets().sendGameMessage("You need an acid dye and the respectable item to do this."); //game message if you do not have 100 shard
					end();
				}
			} else if (componentId == OPTION_2) {
				if (player.getInventory().containsItem(29805, 1) && player.getInventory().containsItem(29943, 1)) {
					player.getInventory().deleteItem(29805, 1);
					player.getInventory().deleteItem(29943, 1);
					player.getInventory().addItem(29814, 1);
					player.getPackets().sendGameMessage("The acid dye is added to your item.");
					end();
					return;
				}
				else {
					player.getPackets().sendGameMessage("You need an acid dye and the respectable item to do this."); //game message if you do not have 100 shard
					end();
				}
			} else if (componentId == OPTION_3) {
					end();
				}
			}
		}
		

	@Override
	public void finish() {
	}

}
