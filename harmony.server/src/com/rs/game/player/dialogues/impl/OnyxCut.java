package com.rs.game.player.dialogues.impl;

import com.rs.game.Animation;
import com.rs.game.player.Skills;
import com.rs.game.player.dialogues.Dialogue;

public class OnyxCut extends Dialogue {

	private int npcId = 805;

	@Override
	public void start() {
		//npcId = (Integer) parameters[0];
		sendNPCDialogue(
				npcId,
				9827,
				"Hey "
						+ player.getUsername()
						+ ", I'm the Master Crafter. I must assit you with this gem you behold, what shall we make?");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			if (player.getSkills().getLevelForXp(Skills.CRAFTING) < 90) {
				player.getPackets().sendGameMessage("You need a crafting level of 90 to craft onyx items.");
				end();
				return;
			}
			sendOptionsDialogue("What would you like to make?", 
					"Amulet Of Fury",
					"Onyx Ring",
					"Onyx Necklace",
					"Onyx Bracelet");
			stage = 2;
		} else if (stage == 2) {
			if (componentId == OPTION_1) {
				if(player.getInventory().containsItem(6573, 1)) {
					player.setNextAnimation(new Animation(885));
						player.getInventory().deleteItem(6573, 1);
						player.getInventory().addItem(6585, 1);
						player.getSkills().addXp(Skills.CRAFTING, 160);
						player.sendMessage("You cut the onyx into an amazing Amulet");
						player.onyxcuts ++;
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
				if(player.getInventory().containsItem(6573, 1)) {
					player.setNextAnimation(new Animation(885));
						player.getInventory().deleteItem(6573, 1);
						player.getInventory().addItem(6577, 1);
						player.getSkills().addXp(Skills.CRAFTING, 160);
						player.sendMessage("You cut the onyx into an amazing Necklace");
						player.onyxcuts ++;
				end();
			}
			} else if (stage == 2) {
			if (componentId == OPTION_4) {
				if(player.getInventory().containsItem(6573, 1)) {
					player.setNextAnimation(new Animation(885));
						player.getInventory().deleteItem(6573, 1);
						player.getInventory().addItem(11130, 1);
						player.getSkills().addXp(Skills.CRAFTING, 160);
						player.sendMessage("You cut the onyx into an amazing Bracelet");
						player.onyxcuts ++;
				end();
			}
			} else if (stage == 2) {
			if (componentId == OPTION_2) {
				if(player.getInventory().containsItem(6573, 1)) {
					player.setNextAnimation(new Animation(885));
						player.getInventory().deleteItem(6573, 1);
						player.getInventory().addItem(6575, 1);
						player.getSkills().addXp(Skills.CRAFTING, 160);
						player.sendMessage("You cut the onyx into an amazing Ring");
						player.onyxcuts ++;
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