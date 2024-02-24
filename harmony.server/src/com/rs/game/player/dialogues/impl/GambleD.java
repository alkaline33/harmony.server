package com.rs.game.player.dialogues.impl;

import com.rs.cache.loaders.NPCDefinitions;
import com.rs.game.minigames.Gamble;
import com.rs.game.player.dialogues.Dialogue;

public class GambleD extends Dialogue {

	private int npcId;

	@Override
	public void start() {
		//npcId = (Integer) parameters[0];
		sendEntityDialogue(SEND_2_TEXT_CHAT, new String[] { NPCDefinitions.getNPCDefinitions(npcId).name, "Hello, would you like to Gamble?" }, IS_NPC, npcId, 9827);
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			sendOptionsDialogue("Would you like to Gamble?", "Yes", "No");
			stage = 1;
		} else if (stage == 1) {
			if (componentId == OPTION_1) {
				if (player.coinamount < 5000000 && player.getMoneyPouch().usingPouch || player.getInventory().getItems().getNumberOf(995) < 5000000 && !player.getMoneyPouch().usingPouch) { 
					player.getDialogueManager().startDialogue("SimpleMessage", "You do not have enough money to Gamble");
				}
					else
				sendPlayerDialogue( 9827, "I would like to Gamble." );
				 Gamble.Gamble2(player);
				stage = 2;
			} else {
				end();
		 if (stage == 2) {
			 Gamble.Gamble2(player);
			}
		 }
			}
		}
		

	@Override
	public void finish() {

	}
}