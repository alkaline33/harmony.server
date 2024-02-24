package com.rs.game.player.dialogues.impl;

import com.rs.game.World;
import com.rs.game.player.dialogues.Dialogue;

public class Ocellus extends Dialogue {

	public Ocellus() {
	}

	
	
	@Override
	public void start() {
		stage = 1;
		sendEntityDialogue(IS_NPC, "Ocellus", 17143, 9827, "Hello young chap! I can combine all ascension signets with a dragon crossbow to make an ascension crossbow.");

	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == 1) {
			stage = 2;
			sendOptionsDialogue("Creation", "Main-hand Ascension bow",
					"Off-hand Ascension bow","Nevermind");
		} else if (stage == 2) {
			if (componentId == OPTION_1) {
				if (player.getInventory().containsItem(28457, 1) && player.getInventory().containsItem(28458, 1)
						&& player.getInventory().containsItem(28459, 1)&& player.getInventory().containsItem(28460, 1)
						&& player.getInventory().containsItem(28461, 1)&& player.getInventory().containsItem(28462, 1)
						&& player.getInventory().containsItem(25917, 1)) {
					player.getInventory().deleteItem(28457, 1);
					player.getInventory().deleteItem(28458, 1);
					player.getInventory().deleteItem(28459, 1);
					player.getInventory().deleteItem(28460, 1);
					player.getInventory().deleteItem(28461, 1);
					player.getInventory().deleteItem(28462, 1);
					player.getInventory().deleteItem(25917, 1);
					player.getInventory().addItem(28437, 1);
					player.getPackets().sendGameMessage("Ocellus has combined your goods and left you with a powerful crossbow!");
					World.sendWorldMessage("<col=ff0000>"+player.getDisplayName()+" has just created an Ascension crossbow!", false);
					end();
					return;
				}
				else {
					player.getPackets().sendGameMessage("You need all 6 ascension signets & a main-hand dragon crossbow to do this."); //game message if you do not have 100 shard
					end();
				}
			} else if (componentId == OPTION_2) {
				if (player.getInventory().containsItem(28457, 1) && player.getInventory().containsItem(28458, 1)
						&& player.getInventory().containsItem(28459, 1)&& player.getInventory().containsItem(28460, 1)
						&& player.getInventory().containsItem(28461, 1)&& player.getInventory().containsItem(28462, 1)
						&& player.getInventory().containsItem(25894, 1)) {
					player.getInventory().deleteItem(28457, 1);
					player.getInventory().deleteItem(28458, 1);
					player.getInventory().deleteItem(28459, 1);
					player.getInventory().deleteItem(28460, 1);
					player.getInventory().deleteItem(28461, 1);
					player.getInventory().deleteItem(28462, 1);
					player.getInventory().deleteItem(25894, 1);
					player.getInventory().addItem(28441, 1);
					player.getPackets().sendGameMessage("Ocellus has combined your goods and left you with a powerful crossbow!");
					World.sendWorldMessage("<col=ff0000>"+player.getDisplayName()+" has just created an Ascension crossbow!", false);
					end();
					return;
				}
				else {
					player.getPackets().sendGameMessage("You need all 6 ascension signets & an off-hand dragon crossbow to do this."); //game message if you do not have 100 shard
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
