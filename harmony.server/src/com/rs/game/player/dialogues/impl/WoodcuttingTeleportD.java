package com.rs.game.player.dialogues.impl;

import com.rs.game.WorldTile;
import com.rs.game.player.Skills;
import com.rs.game.player.content.Magic;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.utils.Colors;

public class WoodcuttingTeleportD extends Dialogue {

	public WoodcuttingTeleportD() {
	}

	@Override
	public void start() {
		stage = 1;
		sendOptionsDialogue("Teleports", "Woodcutting Area",
				"Woodcutting Guild");

	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == 1) {
			if (componentId == OPTION_1) {
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2725, 3491, 0));
				end();
			} else if (componentId == OPTION_2) {
				if (player.getSkills().getLevelForXp(Skills.WOODCUTTING) < 60) {
					player.sendMessage(Colors.red+"You must have a Woodcutting level of atleast 60 to access the Guild!");
					end();
				} else {
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(1588, 3488, 0));
				end();
				}
				}
		    }
		}
		

	@Override
	public void finish() {
	}

}
