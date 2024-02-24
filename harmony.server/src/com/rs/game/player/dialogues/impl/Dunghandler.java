package com.rs.game.player.dialogues.impl;

import com.rs.Settings;
import com.rs.cache.loaders.NPCDefinitions;
import com.rs.game.ForceTalk;
import com.rs.game.WorldTile;
import com.rs.game.player.Skills;
import com.rs.game.player.content.Magic;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.utils.ShopsHandler;

public class Dunghandler extends Dialogue {

	private int npcId = 13768;

	@Override
	public void start() {
		if (Settings.ECONOMY) {
			player.getPackets().sendGameMessage("Mr.Ex is in no mood to talk to you.");
			end();
			return;
		}
		//npcId = (Integer) parameters[0];
		sendEntityDialogue(SEND_2_TEXT_CHAT,
				new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
						"Im Your Dungeoneering Master",
						"How may i help?" }, IS_NPC, npcId, 9827);
	}

	@Override
	
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			sendEntityDialogue(SEND_1_TEXT_CHAT,
					new String[] { player.getDisplayName(), "Okay." },
					IS_PLAYER, player.getIndex(), 9827);
			stage = 1;
		} else if (stage == 1) {
			sendOptionsDialogue("Options", "Open the shop.", "How much points do i have?", "Teleport me to Dungeoneering");
			stage = 2;
		} else if (stage == 2) {
			if (componentId == OPTION_1) {
			player.getInterfaceManager().closeChatBoxInterface();
			player.getInterfaceManager().closeOverlay(true);
			ShopsHandler.openShop(player, 107);
			}
		if (componentId == OPTION_2) {
				player.getInterfaceManager().closeChatBoxInterface();
			player.setNextForceTalk(new ForceTalk("<col=F8F8FF>I have ["+ player.dungpoints +"] Dungeoneering Points"));
			player.getInterfaceManager().closeChatBoxInterface();
			player.getInterfaceManager().closeOverlay(true);
		}
	 if (componentId == OPTION_3) {
					sendOptionsDialogue("Which level?", "Low (1-50)", "Medium (51-95)", "High (96-120)");
					stage = 3;
	 }
				
		 if (componentId == OPTION_4) {
			 Magic.sendAncientTeleportSpell(player, 0, 0, new WorldTile(3091, 3860, 0));
					player.getControlerManager().startControler("Wilderness");
					return;
		 }
		 } else if (stage == 3) {
				if (componentId == OPTION_1) {
					player.teleportPlayer(3476, 9705, 0);
				if (player.lowdungtut == true) {
					player.sendMessage("<col=ff0000> Guide: Kill a warrior for some instructions.");
					player.sendMessage("<col=ff0000> Guide: Search chest for a key part.");
					player.sendMessage("<col=ff0000> Guide: Use the key part on the coffin & combine the parts.");
					player.sendMessage("<col=ff0000> Guide: Proceed through the doors to the boss room.");
					player.getInterfaceManager().closeChatBoxInterface();
					player.getInterfaceManager().closeOverlay(true);
				} else {
					player.getDialogueManager().startDialogue("LowDungTut");
				}
				}
				else if (componentId == OPTION_2) {
				if (player.getSkills().getLevelForXp(Skills.DUNGEONEERING) < 51) {
				player.sendMessage("You must have at least 51 dungeoneering to access this dungeon.");
				return;
				}
				if (!player.getInventory().contains(13305)) {
					if (!player.getInventory().hasFreeSlots()) {
						player.sendMessage("Your inventory was full and we wasn't able to give you the crowbar.");
					}
					player.getInventory().addItem(13305, 1);
				}
				player.teleportPlayer(3478, 9845, 0);
				if (player.meddungtut == true) {
				player.sendMessage("<col=ff0000> Guide: Use the crowbar on the *Shelves* until you get a metal key.");
				player.sendMessage("<col=ff0000> Guide: Search the wall south. Proceed all the way south.");
				player.sendMessage("<col=ff0000> Guide: Kill the warrior for gloom and smash the boards north.");
				player.sendMessage("<col=ff0000> Guide: Proceed south again and head through the doors to the boss.");
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				} else {
					player.getDialogueManager().startDialogue("MedDungTut");
				}
				}
				else if (componentId == OPTION_3) {
				if (player.getSkills().getLevelForXp(Skills.DUNGEONEERING) < 96) {
				player.sendMessage("You must have at least 96 dungeoneering to access this dungeon.");
				return;
				}
				player.teleportPlayer(2838, 10104, 0);
				if (player.highdungtut == true) {
				player.sendMessage("<col=ff0000> Guide: Search the rack and then pickpocket Berry.");
				player.sendMessage("<col=ff0000> Guide: Use the damp cloth on the key. Go in the jail with *Wise old man*.");
					player.sendMessage("<col=ff0000> Guide: Use the key on the weird old man and then click the next jail door.");
				player.sendMessage("<col=ff0000> Guide: Use the hammer on Godric and proceed south to the boss.");
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				} else {
					player.getDialogueManager().startDialogue("HighDungTut");
				}
			}
				
		 
		}
	

	}
		

	@Override
	public void finish() {

	}
}
