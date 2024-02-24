package com.rs.game.player.dialogues.impl;

import com.rs.game.Animation;
import com.rs.game.player.Skills;
import com.rs.game.player.dialogues.Dialogue;

public class MalevolentCreation extends Dialogue {

	private int npcId = 805;

	@Override
	public void start() {
		//npcId = (Integer) parameters[0];
		sendNPCDialogue(
				npcId,
				9827,
				"Hey "
						+ player.getUsername()
						+ ", I'm the Master Crafter. I must assit you with this energy you behold, what shall we make?");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			if (player.getSkills().getLevelForXp(Skills.CRAFTING) < 99) {
				player.getPackets().sendGameMessage("You need a crafting level of 99 to craft Malevolent items.");
				end();
				return;
			}
			sendOptionsDialogue("Options. WARNING These items degrade to dust after 10 hours.", 
					"Malevolent Helmet",
					"Malevolent Body",
					"Malevolent Legs");
			stage = 2;
		} else if (stage == 2) {
			if (componentId == OPTION_1) {
				if(player.getInventory().containsItem(29942, 14)) {
					player.setNextAnimation(new Animation(885));
						player.getInventory().deleteItem(29942, 14);
						player.getInventory().addItem(29940, 1);
						player.getSkills().addXp(Skills.CRAFTING, 200);
						player.sendMessage("You craft the energy into a helmet!");
						player.malhelm = true;
				end();
			} else {
				end();
			}
			/*if (componentId == OPTION_5) {
				sendNPCDialogue(npcId, 9827,
						"You currently have " + player.getLoyaltyPoints()
								+ " Loyalty Points.");
				stage = 3;
			}*/
			} else if (stage == 2) {
			if (componentId == OPTION_3) {
				if(player.getInventory().containsItem(29942, 28)) {
					player.setNextAnimation(new Animation(885));
						player.getInventory().deleteItem(29942, 28);
						player.getInventory().addItem(29939, 1);
						player.getSkills().addXp(Skills.CRAFTING, 210);
						player.sendMessage("You craft the energy into some greaves!");
						player.mallegs = true;
				end();
				} else {
					end();
				}
			} else if (stage == 2) {
			if (componentId == OPTION_2) {
				if(player.getInventory().containsItem(29942, 42)) {
					player.setNextAnimation(new Animation(885));
						player.getInventory().deleteItem(29942, 42);
						player.getInventory().addItem(29941, 1);
						player.getSkills().addXp(Skills.CRAFTING, 220);
						player.sendMessage("You craft the energy into a cuirass!");
						player.malbody = true;
						end();
				} else {
					end();
				}
			}
		} else if (stage == 3) {
			end();
		}
	}
}
	
	}

	@Override
	public void finish() {

	}

}