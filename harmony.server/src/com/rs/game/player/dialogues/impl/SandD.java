package com.rs.game.player.dialogues.impl;

import com.rs.game.WorldObject;
import com.rs.game.player.actions.mining.RedSandStone;
import com.rs.game.player.actions.mining.RedSandStone.Sandstone;
import com.rs.game.player.content.SkillsDialogue;
import com.rs.game.player.dialogues.Dialogue;

public class SandD extends Dialogue {

	private Sandstone sand;
	private WorldObject object;

	@Override
	public void start() {
		this.sand = (Sandstone) parameters[0];
		this.object = (WorldObject) parameters[1];

		SkillsDialogue.sendSkillsDialogue(player, SkillsDialogue.OFFER,
				"How many would you like to make?", player.getInventory()
						.getItems().getNumberOf(sand.getSand()),
				new int[] { sand.getSand().getId() }, null);
	}

	@Override
	public void run(int interfaceId, int componentId) {
		player.getActionManager().setAction(
				new RedSandStone(object, sand.getSand(), SkillsDialogue
						.getQuantity(player)));
		end();
	}

	@Override
	public void finish() {

	}

}