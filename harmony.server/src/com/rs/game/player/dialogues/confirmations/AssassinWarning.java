package com.rs.game.player.dialogues.confirmations;

import com.rs.game.WorldTile;
import com.rs.game.player.content.FriendChatsManager;
import com.rs.game.player.content.Magic;
import com.rs.game.player.dialogues.Dialogue;

public class AssassinWarning extends Dialogue {

	private int npcId = 1;
	@Override
	public void start() {
		//npcId = (Integer) parameters[0];
		sendNPCDialogue(
				npcId,
				9827,
				"Hello, "
						+ player.getUsername()
						+ ". This boss is extremely powerful & will likely kill you within seconds.");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			sendOptionsDialogue("Are you sure you wish to proceed?",
					"Yes!",
					"No thanks.");
			stage = 2;
		} else if (stage == 2) {
			if (componentId == OPTION_1) {
				FriendChatsManager.joinChat("harmony", player);
				FriendChatsManager.refreshChat(player);
				if (!player.getCurrentFriendChat().getOwnerDisplayName().equalsIgnoreCase("Harmony")) {
					player.sendMessage("<col=ff0000>You must be in friends chat *Harmony* to take part in this.");
					end();
					return;
				}
				Magic.sendDrygonTeleportSpell(player, 0, 0, new WorldTile(3515, 9837, 0));
				if (player.lootshareEnabled() != false) {
					player.getActionManager().addActionDelay(7);
					player.toggleLootShareIcy();
				}
						player.getInterfaceManager().closeChatBoxInterface();
						player.getInterfaceManager().closeOverlay(true);
				stage = 3;
					return;
				} else {
					end();
					stage = 3;
				}
			} else if (stage == 2) {
			if (componentId == OPTION_2) {
				stage = 3;
				end();
					return;
			}
		} else if (stage == 3) {
			end();
		}

	}

	@Override
	public void finish() {

	}

}