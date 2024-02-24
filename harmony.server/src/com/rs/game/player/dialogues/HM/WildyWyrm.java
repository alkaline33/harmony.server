package com.rs.game.player.dialogues.HM;

import com.rs.game.WorldTile;
import com.rs.game.player.content.Magic;
import com.rs.game.player.dialogues.Dialogue;


public class WildyWyrm extends Dialogue {

	public WildyWyrm() {
	}

	@Override
	public void start() {
		stage = 1;
		sendOptionsDialogue("Which Boss?", "Normal WildyWyrm",
				"Hardmode WildyWyrm", "Aquatic Wyrm");

	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == 1) {
			if (componentId == OPTION_1) {
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3202,
						3876, 0));
				player.getControlerManager().startControler("Wilderness");
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);	
				
			} else if (componentId == OPTION_2) {
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2994,
						3890, 0));
				player.getControlerManager().startControler("Wilderness");
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);	
			} else if (componentId == OPTION_3) {
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3062,
						3809, 0));
				player.getControlerManager().startControler("Wilderness");
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);	
				}
		}
	}
		

	@Override
	public void finish() {
	}

}
