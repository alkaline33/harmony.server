package com.rs.game.player.dialogues.impl;

import com.rs.game.WorldTile;
import com.rs.game.player.Skills;
import com.rs.game.player.content.Magic;
import com.rs.game.player.dialogues.Dialogue;

public class AmuletOfEternalGloryD extends Dialogue {

	@Override
	public void start() {
		sendOptionsDialogue("Where would you like to go?", "Bandos", "Saradomin", "Armadyl", "Zamorak", "Sheaf Island (1 per day)");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			if (componentId == OPTION_1) {
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2868, 5354, 0));
				end();
			} else if (componentId == OPTION_2) {
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2922, 5251, 0));
					end();
			} else if (componentId == OPTION_3) {
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2832, 5299, 0));
					end();
			} else if (componentId == OPTION_4) {
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2925, 5330, 0));
				end();
			} else if (componentId == OPTION_5) {
				if (player.getSkills().getLevelForXp(Skills.MINING) < 99 || player.getSkills().getLevelForXp(Skills.RUNECRAFTING) < 99 || player.getSkills().getLevelForXp(Skills.FARMING) < 99 || player.getSkills().getLevelForXp(Skills.HERBLORE) < 99 || player.getSkills().getLevelForXp(Skills.FISHING) < 99 || player.getSkills().getLevelForXp(Skills.COOKING) < 99) {
					player.sendMessage("You need maximum knowledge in mining, runecrafting, fishing, cooking, farming and herblore to use this.");
					end();
					return;
				}
				if (player.dailysheafglory >= 1) {
					player.sendMessage("You have used up your daily teleport!");
					end();
					return;
				}
				player.dailysheafglory++;
				Magic.sendDrygonTeleportSpell(player, 0, 0, new WorldTile(2200, 3825, 0));
				end();
			}
		}

	}

	@Override
	public void finish() {

	}

}