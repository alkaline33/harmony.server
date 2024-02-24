package com.rs.game.player.dialogues.impl;

import com.rs.game.Animation;
import com.rs.game.player.Skills;
import com.rs.game.player.dialogues.Dialogue;

public class RoyalCraft extends Dialogue {

	private int npcId = 805;

	@Override
	public void start() {
		//npcId = (Integer) parameters[0];
		sendNPCDialogue(
				npcId,
				9827,
				"Hey "
						+ player.getUsername()
						+ ", I'm the Master Crafter. I must assit you with this leather you behold, what shall we make?");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			if (player.getSkills().getLevelForXp(Skills.CRAFTING) < 90) {
				player.getPackets().sendGameMessage("You need a crafting level of 90 to craft Royal items.");
				end();
				return;
			}
			sendOptionsDialogue("What would you like to make?", 
					"Royal Vambraces",
					"Royal Body",
					"Royal Legs");
			stage = 2;
		} else if (stage == 2) {
			if (componentId == OPTION_1) {
				if(player.getInventory().containsItem(24374, 1)) {
					player.setNextAnimation(new Animation(885));
						player.getInventory().deleteItem(24374, 1);
						player.getInventory().addItem(24376, 1);
						player.getSkills().addXp(Skills.CRAFTING, 160);
						player.sendMessage("You craft the flakes into a set of vambraces!");
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
				if(player.getInventory().containsItem(24374, 2)) {
					player.setNextAnimation(new Animation(885));
						player.getInventory().deleteItem(24374, 2);
						player.getInventory().addItem(24379, 1);
						player.getSkills().addXp(Skills.CRAFTING, 160);
						player.sendMessage("You craft the flakes into a set of chaps!");
				end();
			} else if (stage == 2) {
			if (componentId == OPTION_2) {
				if(player.getInventory().containsItem(24374, 3)) {
					player.setNextAnimation(new Animation(885));
						player.getInventory().deleteItem(24374, 3);
						player.getInventory().addItem(24382, 1);
						player.getSkills().addXp(Skills.CRAFTING, 160);
						player.sendMessage("You craft the flakes into a body!");
				end();
			}
		} else if (stage == 3) {
			end();
		} else {
			player.sendMessage("You don't have the required amount to craft this.");
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