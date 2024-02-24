package com.rs.game.player.dialogues.impl;

import com.rs.Settings;
import com.rs.game.player.dialogues.Dialogue;

public class WildBank extends Dialogue {

	private int npcId = 501;

	@Override
	public void start() {
		//npcId = (Integer) parameters[0];
		sendNPCDialogue(
				npcId,
				9827,
				"Hey "
						+ player.getUsername()
						+ ", I'm the Wild Skilling Bank. You must pay 150,000 coins to use this bank.");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			sendOptionsDialogue("Pay Fee?", 
					"Yes",
					"No");
			stage = 2;
		} else if (stage == 2) {
			if (componentId == OPTION_1) {
				if (player.getMoneyPouch().getCoinAmount() < 150000) {
					player.sendMessage("You must have atleast 150k in your money pouch to pay the fee!");
					player.getInterfaceManager().closeInterfaceCustom();
					end();
					return;
				}
				player.coinamount -= 150000;
				Settings.GpSyncAmount += 150000;
				player.refreshMoneyPouch();
					player.getBank().openBank();
					end();
					return;
		} else if (stage == 2) {
			if (componentId == OPTION_2) {
			end();
			}
		}
		}
}
	

	@Override
	public void finish() {

	}

}