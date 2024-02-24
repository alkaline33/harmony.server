package com.rs.game.player.dialogues.impl;

import com.rs.game.player.dialogues.Dialogue;
import com.rs.utils.ShopsHandler;
import com.rs.utils.Utils;

public class RecyclingManagerD extends Dialogue {

	private int npcId = 4287;

	@Override
	public void start() {
		sendEntityDialogue(IS_NPC, "Gamfred", 4287, 9827, "Hello there, my name is Gamfred. I am the Manager here, how can i help?");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			sendOptionsDialogue("Gamfred", "What is going on here?", "Can i view your shop?", "How many Runecoins have i obtained so far?", "Nevermind");
			stage = 2;
		} else if (stage == 2) {
			if (componentId == OPTION_1) {
				sendEntityDialogue(IS_NPC, "Gamfred", npcId, 9827, "This is the Recycling centre! Players come here to exchange unwanted items for Runecoins. Runecoins can be used to purchase various rewards such as item upgrades, pets, consumables etc..");
				stage = 3;
			}
			else if (componentId == OPTION_2) {
				ShopsHandler.openShop(player, 123);
				end();
			} else if (componentId == OPTION_3) {
				sendEntityDialogue(IS_NPC, "Gamfred", npcId, 9827, "You have obtained a total of " + Utils.format(player.runecoinsobtained) + " Runecoins.");
				stage = 4;
			}
		} else if (stage == 3) {
			sendEntityDialogue(IS_NPC, "Gamfred", npcId, 9827, "You can check an items Runecoin value by examining it. You can use it on the recycling machine to exchange it for Runecoins. Not every item can be exchanged here, but we take most things that have material value.");
			stage = 4;
				}
		else if (stage == 4) {
			end();
		}
	}

	@Override
	public void finish() {

	}

}