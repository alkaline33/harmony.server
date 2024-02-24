package com.rs.game.player.dialogues.impl;

import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.player.Player;
import com.rs.game.player.content.Magic;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.utils.ShopsHandler;


public class ModTable extends Dialogue {

	public ModTable() {
	}

	@Override
	public void start() {
		stage = 1;
		sendOptionsDialogue("Staff Actions", "Staff Store",
				"Teleports", "Bank", "Restart Server",
				"Next Page");

	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == 1) {
			if (componentId == OPTION_1) {
				ShopsHandler.openShop(player, 90);
				end();
			} else if (componentId == OPTION_2) {
				player.getDialogueManager().startDialogue("PortalTeleports");
				end();
			} else if (componentId == OPTION_3) {
				player.getBank().openBank();
				end();
			} else if (componentId == OPTION_4 && player.getRights() >= 1) {
				World.safeShutdown(false, 120);
				Player.printRestartLog(player, componentId, player);
				end();
			} else if (componentId == OPTION_5) {
				sendOptionsDialogue("Staff Actions",
						"Secret Zone", "World Warning", "Max Cash", "Restock Shops", "End");
				stage = 4;
			}
		} else if (stage == 4) {
			if (componentId == OPTION_1) {	
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(4380,
						5912, 0));
					end();
			} else if (componentId == OPTION_2 && player.getRights() >= 1) {
			World.sendWorldMessage("[NEWS]<img=7><col=00FFFF>Moderator "+player.getDisplayName()+"</col>: <col=FF0000>This is a world warning to all players. If the current situation continues, it will be dealt with!</col> ", false);
			end();
			} else if (componentId == OPTION_3) {
					player.getPackets().sendGameMessage("PHAHAHAHA! You actually thought i'd give you free stuff? LOL");
					end();	
			} else if (componentId == OPTION_4) {
				ShopsHandler.loadUnpackedShops();
				World.sendWorldMessage(player.getDisplayName()+" has packed the shops.", true);
				end();
			} else if (componentId == OPTION_5) {
				end();;
			}
		}
	}
		

	@Override
	public void finish() {
	}

}
