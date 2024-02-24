package com.rs.game.player.dialogues.impl;

import com.rs.Settings;
import com.rs.game.WorldTile;
import com.rs.game.player.content.Magic;
import com.rs.game.player.dialogues.Dialogue;

public class KkFee extends Dialogue {

	private int npcId = 29993;
	@Override
	public void start() {
		//npcId = (Integer) parameters[0];
		sendNPCDialogue(
				npcId,
				9827,
				"Oiii! "
						+ player.getUsername()
						+ ", If you wanna fight me you're gonna have to cough up some cash!");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			int fee = 150000 * player.kkfee;
			sendOptionsDialogue("Entry Fee", 
					"Pay "+fee+"",
					"Leave");
			stage = 2;
		} else if (stage == 2) {
			if (componentId == OPTION_1) {
				int fee = 150000 * player.kkfee;
				if (player.getMoneyPouch().getCoinAmount() >= fee) {
					player.coinamount -= fee;
					Settings.GpSyncAmount += fee;
					player.refreshMoneyPouch();
					Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3362, 2791, 0));
					if (player.kkfee < 4) {
						player.kkfee ++;
					}
					
				stage = 3;
					return;
				} else {
					player.sendMessage("You need " + fee + " coins in your money pouch to fight kalphite king!");
					end();
					stage = 3;
				}
			} else if (stage == 2) {
			if (componentId == OPTION_2) {
				stage = 3;
				end();
					return;
			}
		} else if (stage == 3) {
			end();
		}

		}
	}


	@Override
	public void finish() {

	}

}