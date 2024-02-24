package com.rs.game.player.dialogues.impl;

import com.rs.Settings;
import com.rs.game.Hit;
import com.rs.game.WorldTile;
import com.rs.game.Hit.HitLook;
import com.rs.game.player.dialogues.Dialogue;

public class StartDryaxions extends Dialogue {

	private int npcId = 11575;

	@Override
	public void start() {
		//npcId = (Integer) parameters[0];
		sendNPCDialogue(
				npcId,
				9765,
				"Thank goodness you're here Lord "
						+ player.getUsername()
						+ ". We can't fend off these monsters, please help us?");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			sendOptionsDialogue("Is your team ready?", 
					"Yes! Let's kick some evil ass!",
					//"Helllll, no dawggg! Evil FTW! ",
					"Helllll, no dawggg! Evil FTW!");
			stage = 2;
		} else if (stage == 2) {
			if (componentId == OPTION_1) {
				sendNPCDialogue(
						npcId,
						9845,
						"You're our hero "
								+ player.getUsername()
								+ "! Is your team assembled? If so goodluck!");
				stage = 4;
			}
			if (componentId == OPTION_2) {
				sendNPCDialogue(npcId, 9845,
						"Grow some balls "+ player.getUsername() +", we need you! Be gone with you!");
				stage = 6;
			}
			if (componentId == OPTION_3) {
				sendNPCDialogue(npcId, 9845,
						"You disgust me "+ player.getUsername() +"! Suffer you shall!");
				stage = 5;
		} else if (stage == 4) {
				sendOptionsDialogue("Help them defeat evil?", 
						"Yes! Send us in!",
						"Wait, not yet. Give us a second.");
				stage = 7;
		} else if (stage == 6) {
				 player.setNextWorldTile(new WorldTile(2340, 3693, 0));
				 player.applyHit(new Hit(player, (int) (player.getHitpoints() * 0.99), HitLook.REGULAR_DAMAGE, 15));
				end();
				stage = 3;
		} else if (stage == 5) {
				player.applyHit(new Hit(player, (int) (player.getHitpoints() * 0.15), HitLook.REGULAR_DAMAGE, 15));
				end();
				stage = 3;
			}
		} else if (stage == 7) {
			if (componentId == OPTION_1) {
				//Settings.DRYAXIONS_STAGES = 1;
				player.sendMessage("Dry = 1");
				end();
				stage = 3;
			}
			if (componentId == OPTION_2) {
				player.sendMessage("Dry = false");
				end();
				stage = 3;
		} else if (stage == 3) {
			end();
		}
		}
	
}


	@Override
	public void finish() {

	}

}