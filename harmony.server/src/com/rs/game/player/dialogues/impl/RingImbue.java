package com.rs.game.player.dialogues.impl;

import com.rs.game.Animation;
import com.rs.game.Graphics;
import com.rs.game.player.Skills;
import com.rs.game.player.dialogues.Dialogue;

public class RingImbue extends Dialogue {

	private int npcId = 805;

	@Override
	public void start() {
		//npcId = (Integer) parameters[0];
		sendNPCDialogue(
				npcId,
				9827,
				"Hey "
						+ player.getUsername()
						+ ", I'm the Master Crafter. I must assit you with this kit you behold, what shall we make?");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			/*if (player.getSkills().getLevelForXp(Skills.CRAFTING) < 90) {
				player.getPackets().sendGameMessage("You need a crafting level of 90 to craft onyx items.");
				end();
				return;
			}*/
			sendOptionsDialogue("What would you like to Imbue?", 
					"Seers Ring",
					"Archers Ring",
					"Warrior Ring",
					"Berserker Ring");
			stage = 2;
		} else if (stage == 2) {
			if (componentId == OPTION_1) {
				if (player.getSkills().getLevelForXp(Skills.MAGIC) < 90 ||  player.getSkills().getLevelForXp(Skills.RUNECRAFTING) < 85) {
					player.getPackets().sendGameMessage("You need a magic level of 90 and a runecrafting level of 85 to imbue this ring!");
					end();
					return;
				}
				if(player.getInventory().containsItem(29965, 1) && player.getInventory().containsItem(6731, 1)) {
					   player.setNextAnimation(new Animation(713));
				        player.setNextGraphics(new Graphics(113));
						player.getInventory().deleteItem(6731, 1);
						player.getInventory().deleteItem(29965, 1);
						player.getInventory().addItem(15018, 1);
						player.getSkills().addXp(Skills.RUNECRAFTING, 320);
						player.getSkills().addXp(Skills.MAGIC, 160);
						player.imbuekitused ++;
						player.sendMessage("You imbue the seer's ring.");
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
				if (player.getSkills().getLevelForXp(Skills.RANGE) < 90 ||  player.getSkills().getLevelForXp(Skills.FLETCHING) < 85) {
					player.getPackets().sendGameMessage("You need a range level of 90 and a fletching level of 85 to imbue this ring!");
					end();
					return;
				}
				if(player.getInventory().containsItem(29965, 1) && player.getInventory().containsItem(6733, 1)) {
					   player.setNextAnimation(new Animation(713));
				        player.setNextGraphics(new Graphics(113));
						player.getInventory().deleteItem(6733, 1);
						player.getInventory().deleteItem(29965, 1);
						player.getInventory().addItem(15019, 1);
						player.getSkills().addXp(Skills.FLETCHING, 160);
						player.getSkills().addXp(Skills.RANGE, 160);
						player.imbuekitused ++;
						player.sendMessage("You imbue the archer's ring.");
				end();
			}
			} else if (stage == 2) {
			if (componentId == OPTION_3) {
				if (player.getSkills().getLevelForXp(Skills.ATTACK) < 90 ||  player.getSkills().getLevelForXp(Skills.CRAFTING) < 85) {
					player.getPackets().sendGameMessage("You need a attack level of 90 and a crafting level of 85 to imbue this ring!");
					end();
					return;
				}
				if(player.getInventory().containsItem(29965, 1) && player.getInventory().containsItem(6735, 1)) {
					   player.setNextAnimation(new Animation(713));
				        player.setNextGraphics(new Graphics(113));
						player.getInventory().deleteItem(6735, 1);
						player.getInventory().deleteItem(29965, 1);
						player.getInventory().addItem(15020, 1);
						player.getSkills().addXp(Skills.CRAFTING, 160);
						player.getSkills().addXp(Skills.ATTACK, 160);
						player.imbuekitused ++;
						player.sendMessage("You imbue the warrior's ring.");
				end();
			}
			} else if (stage == 2) {
			if (componentId == OPTION_4) {
				if (player.getSkills().getLevelForXp(Skills.STRENGTH) < 90 ||  player.getSkills().getLevelForXp(Skills.SMITHING) < 85) {
					player.getPackets().sendGameMessage("You need a strength level of 90 and a smithing level of 85 to imbue this ring!");
					end();
					return;
				}
				if(player.getInventory().containsItem(29965, 1) && player.getInventory().containsItem(6737, 1)) {
					   player.setNextAnimation(new Animation(713));
				        player.setNextGraphics(new Graphics(113));
						player.getInventory().deleteItem(6737, 1);
						player.getInventory().deleteItem(29965, 1);
						player.getInventory().addItem(15220, 1);
						player.getSkills().addXp(Skills.SMITHING, 160);
						player.getSkills().addXp(Skills.STRENGTH, 160);
						player.imbuekitused ++;
						player.sendMessage("You imbue the berserker's ring.");
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