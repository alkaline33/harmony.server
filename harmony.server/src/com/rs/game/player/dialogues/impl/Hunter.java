package com.rs.game.player.dialogues.impl;

import com.rs.Settings;
import com.rs.cache.loaders.NPCDefinitions;
import com.rs.game.player.dialogues.Dialogue;

public class Hunter extends Dialogue {

	private int npcId;

	@Override
	public void start() {
		if (Settings.ECONOMY) {
			player.getPackets().sendGameMessage("Mr.Ex is in no mood to talk to you.");
			end();
			return;
		}
		npcId = (Integer) parameters[0];
		sendEntityDialogue(SEND_2_TEXT_CHAT,
				new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
						"Hello, I can teleport you to hunter,",
						" would you like to?" }, IS_NPC, npcId, 9827);
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			sendEntityDialogue(SEND_1_TEXT_CHAT,
					new String[] { player.getDisplayName(), "Sure, why not." },
					IS_PLAYER, player.getIndex(), 9827);
			stage = 1;
		} else if (stage == 1) {
			sendOptionsDialogue("Where would you like to go?", "Teleport me to hunter.", "Nevermid");
			stage = 2;
		} else if (stage == 2) {
			if (componentId == OPTION_1)
				player.getControlerManager().startControler("Falconry");
				player.teleportPlayer(2376, 3599, 0); 
				player.getInventory().addItem(10024, 1);
			if (componentId == OPTION_2)
				player.getInterfaceManager().closeChatBoxInterface();
				
		}
	

	}
		

	@Override
	public void finish() {

	}
}
