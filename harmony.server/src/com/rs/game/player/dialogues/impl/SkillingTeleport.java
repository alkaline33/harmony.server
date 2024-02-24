package com.rs.game.player.dialogues.impl;

import com.rs.Settings;
import com.rs.cache.loaders.NPCDefinitions;
import com.rs.game.WorldTile;
import com.rs.game.player.Skills;
import com.rs.game.player.content.Magic;
import com.rs.game.player.dialogues.Dialogue;

public class SkillingTeleport extends Dialogue {

	private int npcId;

	@Override
	public void start() {
		if (Settings.ECONOMY) {
			player.getPackets().sendGameMessage("Max is in no mood to talk to you.");
			end();
			return;
		}
		npcId = (Integer) parameters[0];
		sendEntityDialogue(SEND_2_TEXT_CHAT,
				new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
						"Hello, I can teleport you to all skilling & training locations in Harmony,",
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
			sendOptionsDialogue("Where would you like to go?", "Mining",
					"Woodcutting", "Smithing", "Hunter", "More Options");
			stage = 2;
		} else if (stage == 2) {
			if (componentId == OPTION_1)
				teleportPlayer(3297, 3301, 0);
			else if (componentId == OPTION_2)
				teleportPlayer(2726, 3477, 0);
			else if (componentId == OPTION_3)
				teleportPlayer(1751, 5289, 1);
			else if (componentId == OPTION_4)
				teleportPlayer(2557, 2930, 0);
			else if (componentId == OPTION_5) {
				stage = 3;
				sendOptionsDialogue("Where would you like to go?",
						"Duel Arena.", "Gnome Agility.", "Barbarian Agility.",
						"Construction", "More Options");
			}
		} else if (stage == 3) {
			if (componentId == OPTION_1) {
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3366,
						3276, 0));
				player.getControlerManager().startControler("DuelControler");
			} else if (componentId == OPTION_2)
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2470,
						3436, 0));
			else if (componentId == OPTION_3)
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2544,
						3569, 0));
			else if (componentId == OPTION_4)
			player.sendMessage("Teleport to Yanille and enter the portal for Construction");
			else if (componentId == OPTION_5) {
				stage = 4;
				sendOptionsDialogue("Where would you like to go?", "Yaks", "Goblins & Guards", "Cows", "Mans", "Next page");
			}
		}else if (stage == 4) {
			if (componentId == OPTION_1)
				teleportPlayer(2326, 3794, 0);
			else if (componentId == OPTION_2)
				teleportPlayer(3183, 3246, 0);
			else if (componentId == OPTION_3)
				teleportPlayer(3260, 3272, 0);
			else if (componentId == OPTION_4)
				teleportPlayer(3097, 3509, 0);
			else if (componentId == OPTION_5) {
				stage = 5;
				sendOptionsDialogue("Where would you like to go?", "Farming", "Slayer tower", "Glacors", "Runecrafting", "Nevermind");
			}
		}else if (stage == 5) {
			if (componentId == OPTION_1)
				teleportPlayer(2253, 3293, 0);
		if (componentId == OPTION_2)
				teleportPlayer(3412, 3538, 0);
	if (componentId == OPTION_3)
				if (player.getSkills().getLevelForXp(Skills.SLAYER) < 50) {
					player.getPackets().sendGameMessage(
							"You need a slayer level of 50 to kill Glacors.");
					end();
					return;
				} else
				teleportPlayer(4182, 5731, 0);
				if (componentId == OPTION_4)
				teleportPlayer(3040, 4843, 0);
				} else if (componentId == OPTION_5) {
					
			player.getInterfaceManager().closeChatBoxInterface();
			//end();
			}
		}
	
		

		
			
	

	private void teleportPlayer(int x, int y, int z) {
		player.setNextWorldTile(new WorldTile(x, y, z));
		player.stopAll();
		player.getControlerManager().startControler("null");
	}

	@Override
	public void finish() {

	}
}
