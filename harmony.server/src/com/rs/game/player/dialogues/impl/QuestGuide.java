package com.rs.game.player.dialogues.impl;

import com.rs.game.player.Skills;
import com.rs.game.player.dialogues.Dialogue;

public class QuestGuide extends Dialogue {

	private int npcId = 949;

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
			if (player.getSkills().getLevelForXp(Skills.CRAFTING) < 0) {
				player.getPackets().sendGameMessage("You need a crafting level of 90 to craft onyx items.");
				end();
				return;
			}
			sendOptionsDialogue("Quest Teleports", 
					"The Rise Of Zark",
					"Lost City",
					"------",
					"More..");
			stage = 2;
		} else if (stage == 2) {
			if (componentId == OPTION_1) {
				player.teleportPlayer(2742, 3468, 0);
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
				end();
			}
			} else if (stage == 2) {
			if (componentId == OPTION_4) {
				end();
			}
			} else if (stage == 2) {
			if (componentId == OPTION_2) {
				player.teleportPlayer(3148, 3206, 0);
				end();
			}
		} else if (stage == 3) {
			end();
		}
	}


	@Override
	public void finish() {

	}

}