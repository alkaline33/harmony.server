package com.rs.game.player.dialogues.impl;

import com.rs.game.player.Skills;
import com.rs.game.player.dialogues.Dialogue;

public class HouseTeleport extends Dialogue {

	public HouseTeleport() {
	}

	@Override
	public void start() {
		stage = 1;
		sendOptionsDialogue("What house would you like to visit?", "Bronze (1-25)",
				"Mithril (25-50)", "Rune (50-75)", "Dragon (75-99)");

	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == 1) {
			if (componentId == OPTION_1) {
				if (player.bronzehouse == 0) {
					player.getPackets().sendGameMessage("You need to have purchased this house from the estate agent.");
					player.getInterfaceManager().closeChatBoxInterface();
					return;
				}
				player.getControlerManager().startControler("HouseControler");
				player.getInterfaceManager().closeChatBoxInterface();
			} else if (componentId == OPTION_2) {
				if (player.getSkills().getLevelForXp(Skills.CONSTRUCTION) < 25) {
					player.getPackets().sendGameMessage("You need a construction level of 25 and bought a mithril house from the estate agent to go to this house.");
					player.getInterfaceManager().closeChatBoxInterface();
					return;
				}
				if (player.mithrilhouse == 0) {
					player.getPackets().sendGameMessage("You need to have purchased this house from the estate agent.");
					player.getInterfaceManager().closeChatBoxInterface();
					return;
				}
				player.getControlerManager().startControler("HouseControler2550");
				player.getInterfaceManager().closeChatBoxInterface();
			} else if (componentId == OPTION_3) {
				if (player.getSkills().getLevelForXp(Skills.CONSTRUCTION) < 50) {
					player.getPackets().sendGameMessage("You need a construction level of 50 to go this house and bought a rune house from the estate agent to go to this house.");
					player.getInterfaceManager().closeChatBoxInterface();
					return;
				}
				if (player.runehouse == 0) {
					player.getPackets().sendGameMessage("You need to have purchased this house from the estate agent.");
					player.getInterfaceManager().closeChatBoxInterface();
					return;
				}
				player.getControlerManager().startControler("HouseControler5075");
				player.getInterfaceManager().closeChatBoxInterface();
			} else if (componentId == OPTION_4) {
				if (player.getSkills().getLevelForXp(Skills.CONSTRUCTION) < 75) {
					player.getPackets().sendGameMessage("You need a construction level of 75 to go this house and bought a dragon house from the estate agent to go to this house.");
					player.getInterfaceManager().closeChatBoxInterface();
					return;
				}
				if (player.dragonhouse == 0) {
					player.getPackets().sendGameMessage("You need to have purchased this house from the estate agent.");
					player.getInterfaceManager().closeChatBoxInterface();
					return;
				}
				player.getControlerManager().startControler("HouseControler7590");
				player.getInterfaceManager().closeChatBoxInterface();
			}
		}
	}
@Override
public void finish() {
	}
}