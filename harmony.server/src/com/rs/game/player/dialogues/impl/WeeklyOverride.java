package com.rs.game.player.dialogues.impl;

import com.rs.game.World;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.utils.Utils;

public class WeeklyOverride extends Dialogue {
	
	/**
	 * Author Mr_Joopz
	 */

	private int npcId = 1972;
	private String itemNeeded = "Frost Dragon Bones"; // edit every Monday
	private int itemAmount = 100; // edit every Monday
	private int itemNeededID = 18831; // edit every Monday
	private String Reward = "Gothic"; // edit every Monday

	@Override
	public void start() {
		if (!player.isWeekend()) {
			sendNPCDialogue(npcId, 9827, "Sorry, but i do not need anything right now. I will at the weekend though!");
			stage = 3;
			return;
		}
		if (player.gothicc == true) { // edit every Monday
			sendNPCDialogue(npcId, 9827, "You already have this weeks override, try again next week!");
			stage = 3;
			return;
		}
		sendItemDialogue(itemNeededID, 1, "Hey " + player.getDisplayName() + ", I'm looking for " + Utils.format(itemAmount)
				+ " noted " + itemNeeded + ", in return i will give you a " + Reward + " override.");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			sendOptionsDialogue("Weekly", "I have the items!", "What is next weeks reward?", "Nevermind");
			stage = 2;
		} else if (stage == 2) {
			if (componentId == OPTION_1) {
				if (player.getInventory().containsItem(itemNeededID, itemAmount)) {
					stage = 3;
					player.getInventory().deleteItem(itemNeededID, itemAmount);
					player.gothicc = true;
					sendItemDialogue(itemNeededID, 500, "You hand over the "+itemNeeded+" and you're rewarded with the "+Reward+".");
					World.sendWorldMessage("<col=ff0000>"+player.getDisplayName()+" has just completed the weekly override task!", false);
				return;
				} else {
					stage = 3;
					sendNPCDialogue(npcId, 9827, "Haha! Nice try human. You do not have what i require.");
					return;
				}
			}
			if (componentId == OPTION_2) {
				stage = 3;
				sendNPCDialogue(npcId, 9827, "Wouldn't you like to know! Visit me next week and you shall find out!");
				return;
			}
			if (componentId == OPTION_3) {
				end();
			}
		}
		if (stage == 3) {
			sendPlayerDialogue(9827, "Sad times.");
			end();
		}
	}

	@Override
	public void finish() {

	}

}