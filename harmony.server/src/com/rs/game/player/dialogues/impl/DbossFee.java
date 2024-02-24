package com.rs.game.player.dialogues.impl;

import com.rs.Settings;
import com.rs.game.WorldTile;
import com.rs.game.player.content.Magic;
import com.rs.game.player.dialogues.Dialogue;

public class DbossFee extends Dialogue {

	private int npcId = 10141;
	@Override
	public void start() {
		//npcId = (Integer) parameters[0];
		sendNPCDialogue(
				npcId,
				9827,
				"You think it's that easy, "
						+ player.getDisplayName()
						+ "? If you even think you're worthy enough, you need to share some gold first!");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			int fee = 50000 * player.dbossfee;
			sendOptionsDialogue("Entry Fee", 
					"Pay "+fee+"",
					"Leave");
			stage = 2;
		} else if (stage == 2) {
			if (componentId == OPTION_1) {
				int fee = 50000 * player.dbossfee;
				if (player.getInventory().containsItem(995, fee)) {
					player.getInventory().deleteItem(995, fee);
					Settings.GpSyncAmount += fee;
					Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3388, 3517, 0));
					if (player.dbossfee < 4) {
						player.dbossfee ++;
					}
				stage = 3;
					return;
				} else {
					player.sendMessage("You need "+fee+" coins in your inventory to fight Bal'lak!");
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