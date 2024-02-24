package com.rs.game.player.dialogues.impl;

import com.rs.game.Animation;
import com.rs.game.WorldTile;
import com.rs.game.player.Skills;
import com.rs.game.player.content.Magic;
import com.rs.game.player.dialogues.Dialogue;

public class HydrixCut extends Dialogue {

	private int npcId = 805;
	private int gemId = 29728;
	private int gem2Id = 6573;
	
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
			if (player.getSkills().getLevelForXp(Skills.CRAFTING) < 99) {
				player.getPackets().sendGameMessage("You need a crafting level of 99 to craft hydrix items.");
				end();
				return;
			}
			sendOptionsDialogue("What would you like to make?", 
					"Amulet of souls", "Ring of Death");
			stage = 2;
		} else if (stage == 2) {
			if (componentId == OPTION_1) {
				if(player.getInventory().containsItem(gemId, 1) && player.getInventory().containsItem(gem2Id, 1)) {
					player.setNextAnimation(new Animation(885));
						player.getInventory().deleteItem(gemId, 1);
						player.getInventory().deleteItem(gem2Id, 1);
						player.getInventory().addItem(29731, 1);
						player.getSkills().addXp(Skills.CRAFTING, 190);
						player.sendMessage("You cut the gem into an amulet of souls.");
				end();
				} else {
					player.sendMessage("You need a hydrix and an onyx.");
				}
				}else if (componentId == OPTION_2) {
					if(player.getInventory().containsItem(gemId, 1) && player.getInventory().containsItem(gem2Id, 1)) {
						player.setNextAnimation(new Animation(885));
							player.getInventory().deleteItem(gemId, 1);
							player.getInventory().deleteItem(gem2Id, 1);
							player.getInventory().addItem(29727, 1);
							player.getSkills().addXp(Skills.CRAFTING, 190);
							player.sendMessage("You cut the gem into a Ring of Death");
					end();
					} else {
						player.sendMessage("You need a hydrix and an onyx.");
					
					}
				}

		} else if (stage == 3) {
			end();
			}
		
	}

	@Override
	public void finish() {

	}

}