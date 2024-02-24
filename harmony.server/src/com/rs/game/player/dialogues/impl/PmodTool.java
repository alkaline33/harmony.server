package com.rs.game.player.dialogues.impl;

import com.rs.Settings;
import com.rs.cache.loaders.NPCDefinitions;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.minigames.CastleWars;
import com.rs.game.player.Skills;
import com.rs.game.player.content.DonatorZone;
import com.rs.game.player.content.Magic;
import com.rs.game.player.controlers.FightCaves;
import com.rs.game.player.controlers.FightKiln;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.utils.PkRank;
import com.rs.utils.Utils;

public class PmodTool extends Dialogue {
	
	/*
	 * Author = Username.. / joopz
	 */

	private int npcId = 11583;

	@Override
	public void start() {
		if (Settings.ECONOMY) {
			player.getPackets().sendGameMessage("Pmod is in no mood to talk to you.");
			end();
			return;
		}
		//npcId = (Integer) parameters[0];
		sendEntityDialogue(SEND_2_TEXT_CHAT,
				new String[] { NPCDefinitions.getNPCDefinitions(1).name,
						"Good day to you " + Utils.formatPlayerNameForDisplay(player.getUsername()) + "! Serving you at my best.",
						" What command will it be sir?" }, IS_NPC, npcId, 9845);
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (player.rights <= 0) {
			World.sendWorldMessage("<img=7><col=ff0000>News: "+player.getDisplayName()+" Has just tried to use a Moderator item!", false);
			player.getInventory().deleteItem(5733, 2147000000);
		//	player.forceLogout();
			return;
		}
		if (stage == -1) {
			sendEntityDialogue(SEND_1_TEXT_CHAT,
					new String[] { player.getDisplayName(), "Show me them my good friend!" },
					IS_PLAYER, player.getIndex(), 9845);
			stage = 1;
		} else if (stage == 1) {
			sendOptionsDialogue("Moderator Commands!", "COMING SOON", "COMMING SOON", "COMING SOON", "COMING SOON", "More...");
			stage = 2;
		} else if (stage == 2) {
			if (componentId == OPTION_1)
			player.getInventory().addItem(714, 1);
			else if (componentId == OPTION_2)
			player.getInventory().addItem(715, 1);
			else if (componentId == OPTION_3)
			World.sendWorldMessage("<col=990000><img=7>The next Harmony update will be in 1 hour.", false);
			else if (componentId == OPTION_4)
				player.getInventory().addItem(624, 1);
			else if (componentId == OPTION_5) {
				stage = 3;
				sendOptionsDialogue("Administrator Commands!",
						"Spawn Pring", "God Mode", "StaffZone",
						"Hidden Mode", "More Options");
			}
		} else if (stage == 3) {
			if (componentId == OPTION_1) {
				player.getInventory().addItem(773, 1);
			} else if (componentId == OPTION_2)
				player.getInventory().addItem(717, 1);
			else if (componentId == OPTION_3)
				player.setNextWorldTile(new WorldTile(2140, 5535, 3));
			else if (componentId == OPTION_4)
				player.getInventory().addItem(718, 1);
			else if (componentId == OPTION_5) {
				stage = 4;
				sendOptionsDialogue("Administrator Commands!",
						"DonatorZone", "PvP Ranks", "Bank",
						"ChillZone", "More Options");
			}
		} else if (stage == 4) {
			if (componentId == OPTION_1) {
				DonatorZone.enterDonatorzone(player);
			} else if (componentId == OPTION_2) {
				PkRank.showRanks(player);
			} else if (componentId == OPTION_3)
				player.getBank().openBank();
			else if (componentId == OPTION_4) {
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(4380,
						5912, 0));
				player.getControlerManager().startControler("Wilderness");
			} else if (componentId == OPTION_5) {
				stage = 5;
				sendOptionsDialogue("Where would you like to go?",
						"Easts (PvP)", "BrimHaven", "Corp", "Feldip hills",
						"More Options");
			}
		} else if (stage == 5) {
			if (componentId == OPTION_1) {
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3360,
						3658, 0));
				player.getControlerManager().startControler("Wilderness");
			} else if (componentId == OPTION_2)
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2709,
						9464, 0));
			else if (componentId == OPTION_3)
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2966,
						4383, 2));
			else if (componentId == OPTION_4) {
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2570,
						2916, 0));
			} else if (componentId == OPTION_5) {
				stage = 6;
				sendOptionsDialogue("Where would you like to go?",
						"Zamorak", "Armadyl", "Castle Wars", "Trivia",
						"More Options");
			}
		} else if (stage == 6) {
			if (componentId == OPTION_1)
				teleportPlayer(2925, 5330, 0);
			else if (componentId == OPTION_2)
				teleportPlayer(2838, 5297, 0);
			else if (componentId == OPTION_3)
				Magic.sendNormalTeleportSpell(player, 0, 0, CastleWars.LOBBY);
			else if (componentId == OPTION_4)
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2647, 9378, 0));
			else if (componentId == OPTION_5) {
				sendOptionsDialogue("Where would you like to go?",
						"Kalphite Queen", "Fight Caves", "Fight Kiln", "Queen Black Dragon",
						"Back to the beginning");
				stage = 7;
			}
		} else if (stage == 7) {
			if (componentId == OPTION_1) 
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3226, 3108, 0));
			else if (componentId == OPTION_2)
				Magic.sendNormalTeleportSpell(player, 0, 0, FightCaves.OUTSIDE);
			else if (componentId == OPTION_3)
				Magic.sendNormalTeleportSpell(player, 0, 0, FightKiln.OUTSIDE);
			else if (componentId == OPTION_4) {
				end();
				if (player.getSkills().getLevelForXp(Skills.SUMMONING) < 60) {
					player.getPackets().sendGameMessage("You need a summoning level of 60 to go through this portal.");
					return;
				}
				player.getControlerManager().startControler("QueenBlackDragonControler");
			}
			/*else if (componentId == 2)
				teleportPlayer(2838, 5297, 2);
			else if (componentId == 3)
				Magic.sendNormalTeleportSpell(player, 0, 0, CastleWars.LOBBY);
			else if (componentId == 4)
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2647, 9378, 0));*/
			else if (componentId == OPTION_5) {
				sendOptionsDialogue("Administrator Commands!", "Spawn Nex Sets", "Zombie Chaos", "Update in 1 hour", "Master", "More...");
				stage = 2;
			}
		}
	}

	private void teleportPlayer(int x, int y, int z) {
		player.setNextWorldTile(new WorldTile(x, y, z));
		player.stopAll();
		player.getControlerManager().startControler("GodWars");
	}

	@Override
	public void finish() {

	}
}
