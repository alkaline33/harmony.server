package com.rs.game.player.dialogues.impl;

import com.rs.game.minigames.GoldChest;
import com.rs.game.player.dialogues.Dialogue;

/**
 * Handles the Queen Black Dragon reward chest dialogue.
 * @author Emperor
 *
 */
public final class GoldChestD extends Dialogue {

	/**
	 * The NPC.
	 */
	private GoldChest npc;
	
	@Override
	public void start() {
		//npc = (GoldChest) parameters[2];
		super.sendDialogue("This strange device is covered in indecipherable script. It opens for you,", "displaying only a small sample of the objects it contains.");

	}

	@Override
	public void run(int interfaceId, int componentId) {
		npc.openGoldChest();
		super.end();
	}

	@Override
	public void finish() { }
	
}