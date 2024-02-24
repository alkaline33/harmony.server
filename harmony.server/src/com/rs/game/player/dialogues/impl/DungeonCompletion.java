package com.rs.game.player.dialogues.impl;

import com.rs.Settings;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.dungeoneering.Dungeon;
import com.rs.game.player.dialogues.Dialogue;

public class DungeonCompletion extends Dialogue {

	Dungeon dungeon;

	@Override
	public void start() {
		dungeon = (Dungeon) parameters[0];
		if (dungeon != null)
			sendDialogue("You have completed the dungeon with " + dungeon.deaths + " deaths.", "You will be rewarded " + dungeon.getXpForDungeon() * Settings.XP_RATE + " xp for completing the dungeon.");
		stage = 1;
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == 1) {
			sendOptionsDialogue("Move on to the next dungeon?", "Yes", "No");
			stage = 2;
		} else if (stage == 2) {
			if (componentId == 11) {
				if (player.getDungeon() != null) {
					int amount = 0;
					if (dungeon.getDungType() == Dungeon.FROZEN)
						amount = 1025;
					else if (dungeon.getDungType() == Dungeon.FROZEN_LOWER)
						amount = 1550;
					else if (dungeon.getDungType() == Dungeon.FROZEN_MEDIUM)
						amount = 2175;
					else if (dungeon.getDungType() == Dungeon.FROZEN_HIGH)
						amount = 2880;
					player.getSkills().addXp(Skills.DUNGEONEERING, dungeon.getXpForDungeon());
					player.setNpcPoints(player.getdungpoints() + amount);
					player.getPackets().sendGameMessage("<col=B31C14>You recieve " + amount + " Dungeoneering Tokens.");
					player.reset(true);
					end();
					dungeon.end(false);
				}
			end();
			}
		}
	}

	@Override
	public void finish() {

	}
}