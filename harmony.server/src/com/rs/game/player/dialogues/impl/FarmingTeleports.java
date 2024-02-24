package com.rs.game.player.dialogues.impl;

import com.rs.game.WorldTile;
import com.rs.game.player.content.Magic;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.utils.Utils;

public class FarmingTeleports extends Dialogue {
	
	@Override
	public void start() {
		stage = 90;
		sendOptionsDialogue("Farming Teleports", "Taverly", "Falador",
				"Lumbridge", "Varrock", "Next Page");

	}
	@Override
	public void run(int interfaceId, int componentId) {
		if (player.getAttackedByDelay() + 10000 > Utils
				.currentTimeMillis()) {
			player.getDialogueManager().startDialogue("SimpleMessage", "You can't teleport until 10 seconds after the end of combat.");
			return;
		}
		 if (stage == 90) {//fARMING TELEPORTS
				if (componentId == OPTION_1) { // tav
					Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2923, 3430, 0));
					player.getInterfaceManager().closeChatBoxInterface();
					player.getInterfaceManager().closeOverlay(true);
					//player.getControlerManager().forceStop();
					//player.getControlerManager().removeControlerWithoutCheck();
				} else if (componentId == OPTION_2) { // fally
					Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3004,
							3377, 0));
					player.getInterfaceManager().closeChatBoxInterface();
					player.getInterfaceManager().closeOverlay(true);
					//player.getControlerManager().forceStop();
					//player.getControlerManager().removeControlerWithoutCheck();
					end();
				}else if (componentId == OPTION_3) { // Lumbridge
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3195, 3234, 0));
					player.getInterfaceManager().closeChatBoxInterface();
					player.getInterfaceManager().closeOverlay(true);
					//player.getControlerManager().forceStop();
					//player.getControlerManager().removeControlerWithoutCheck();
					end();
				}else if (componentId == OPTION_4) { // Varrock
					Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3224,
							3454, 0));
					player.getInterfaceManager().closeChatBoxInterface();
					player.getInterfaceManager().closeOverlay(true);
					//player.getControlerManager().forceStop();
					//player.getControlerManager().removeControlerWithoutCheck();
					end();
				} else if (componentId == OPTION_5) { // Farming
					sendOptionsDialogue("Farming Locations", "Gnome",
							"Brimhaven", "Catherby", "Canifis",
							"Next Page");
					stage = 91;
				}
				
			} else if (stage == 91) {//fARMING TELEPORTS
				if (componentId == OPTION_1) { // gnome
					Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2435, 3427, 0));
					player.getInterfaceManager().closeChatBoxInterface();
					player.getInterfaceManager().closeOverlay(true);
					//player.getControlerManager().forceStop();
					//player.getControlerManager().removeControlerWithoutCheck();
				} else if (componentId == OPTION_2) { // brim
					Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2768,
							3215, 0));
					player.getInterfaceManager().closeChatBoxInterface();
					player.getInterfaceManager().closeOverlay(true);
					//player.getControlerManager().forceStop();
					//player.getControlerManager().removeControlerWithoutCheck();
					end();
				} else if (componentId == OPTION_3) { // catheby
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2807, 3464, 0));
					player.getInterfaceManager().closeChatBoxInterface();
					player.getInterfaceManager().closeOverlay(true);
					//player.getControlerManager().forceStop();
					//player.getControlerManager().removeControlerWithoutCheck();
					end();
				}else if (componentId == OPTION_4) { // Canafis
					Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3604,
							3529, 0));
					player.getInterfaceManager().closeChatBoxInterface();
					player.getInterfaceManager().closeOverlay(true);
					//player.getControlerManager().forceStop();
					//player.getControlerManager().removeControlerWithoutCheck();
					end();
				} else if (componentId == OPTION_5) { // outside fally
					sendOptionsDialogue("Draynor", "Draynor", "Ardougne");
					stage = 92;
				}
				
			} else if (stage == 92) {//fARMING TELEPORTS
				if (componentId == OPTION_1) { // Draynor
					Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3053,
							3306, 0));
					player.getInterfaceManager().closeChatBoxInterface();
					player.getInterfaceManager().closeOverlay(true);
				} else if (componentId == OPTION_2) { // ardougne
					Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2673,
							3374, 0));
					player.getInterfaceManager().closeChatBoxInterface();
					player.getInterfaceManager().closeOverlay(true);
					//player.getControlerManager().forceStop();
					//player.getControlerManager().removeControlerWithoutCheck();
					end();
				}
			}
		
}
	
	
	@Override
	public void finish() {
	}
}
