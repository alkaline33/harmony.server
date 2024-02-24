package com.rs.game.player.dialogues.impl;

import com.rs.cache.loaders.NPCDefinitions;
import com.rs.game.player.controlers.PlayerWars;
import com.rs.game.player.dialogues.Dialogue;

public class PlayerWarDialogue extends Dialogue {

	private int npcId;

	@Override
	public void start() {
		npcId = (Integer) 11508;
		sendEntityDialogue(SEND_2_TEXT_CHAT,
				new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
						"Hello, I can teleport you to the Player War minigame, a wave based",
						"activity that truly tests your endurance.", " Would you like me to teleport you now?" }, IS_NPC, npcId, 9827);
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			sendOptionsDialogue("Start a Player War game?", "Yes",
					"No");
			stage = 2;
		} else if (stage == 2) {
			if (componentId == OPTION_1)
				PlayerWars.enterPlayerWars(player);
			else if (componentId == OPTION_2)
				player.getInterfaceManager().closeChatBoxInterface();
			//end();
			}
		}

	@Override
	public void finish() {

	}
}
