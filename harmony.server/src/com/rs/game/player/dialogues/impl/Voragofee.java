package com.rs.game.player.dialogues.impl;

import com.rs.Settings;
import com.rs.game.WorldTile;
import com.rs.game.player.content.Magic;
import com.rs.game.player.dialogues.Dialogue;

public class Voragofee extends Dialogue {

	private int npcId = 29999;
	@Override
	public void start() {
		//npcId = (Integer) parameters[0];
		sendNPCDialogue(
				npcId,
				9827,
				"Child of Harmony.."
						+ "It's very dangerous here. Please proceed with a team.");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			int fee = 150000 * player.voragofee;
			sendOptionsDialogue("Entry Fee", 
					"Pay "+fee+"",
					"Leave");
			stage = 2;
		} else if (stage == 2) {
			if (componentId == OPTION_1) {
				int fee = 150000 * player.voragofee;
				if (player.getMoneyPouch().getCoinAmount() >= fee) {
					player.coinamount -= fee;
					Settings.GpSyncAmount += fee;
					player.refreshMoneyPouch();
					Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3450, 9522, 0));
					if (player.voragofee < 4) {
						player.voragofee ++;
					}
					
				stage = 3;
					return;
				} else {
					player.sendMessage("You need " + fee + " coins in your money pouch to fight Vorago!");
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