package com.rs.game.player.dialogues.impl;

import com.rs.game.Animation;
import com.rs.game.player.Skills;
import com.rs.game.player.dialogues.Dialogue;

public class GanoCraft extends Dialogue {

	private int npcId = 805;

	@Override
	public void start() {
		//npcId = (Integer) parameters[0];
		if (player.getSkills().getLevelForXp(Skills.CRAFTING) < 90) {
			player.getPackets().sendGameMessage("You need a crafting level of 90 to craft Ganodermic items.");
			end();
			return;
		}
		sendOptionsDialogue("What would you like to make?", 
				"Ganodermic Visor (500 flakes)",
				"Ganodermic Poncho (5000 flakes)",
				"Ganodermic Leggings (1500 flakes)");
		stage = 2;
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == 2) {
			if (componentId == OPTION_1) {
				if(player.getInventory().containsItem(22451, 500)) {
					player.setNextAnimation(new Animation(885));
						player.getInventory().deleteItem(22451, 500);
						player.getInventory().addItem(22482, 1);
						player.getSkills().addXp(Skills.CRAFTING, 160);
						player.sendMessage("You craft the flakes into a visor!");
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
				if (componentId == OPTION_2) {
					if(player.getInventory().containsItem(22451, 5000)) {
						player.setNextAnimation(new Animation(885));
							player.getInventory().deleteItem(22451, 5000);
							player.getInventory().addItem(22490, 1);
							player.getSkills().addXp(Skills.CRAFTING, 160);
							player.sendMessage("You craft the flakes into a poncho!");
					end();
					}
			} else if (stage == 2) {
			if (componentId == OPTION_3) {
				if(player.getInventory().containsItem(22451, 1500)) {
					player.setNextAnimation(new Animation(885));
						player.getInventory().deleteItem(22451, 1500);
						player.getInventory().addItem(22486, 1);
						player.getSkills().addXp(Skills.CRAFTING, 160);
						player.sendMessage("You craft the flakes into a set of leggings!");
				end();
			}
		} else if (stage == 3) {
			end();
		}
	}
}
	
		}
	}

	@Override
	public void finish() {

	}

}