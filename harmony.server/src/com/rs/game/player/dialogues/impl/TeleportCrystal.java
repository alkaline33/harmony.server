package com.rs.game.player.dialogues.impl;

import com.rs.game.WorldTile;
import com.rs.game.minigames.CastleWars;
import com.rs.game.player.Skills;
import com.rs.game.player.content.Magic;
import com.rs.game.player.controlers.FightCaves;
import com.rs.game.player.controlers.FightKiln;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.utils.Utils;

public class TeleportCrystal extends Dialogue {

	@Override
	public void start() {
		stage = 1;
		sendOptionsDialogue("Teleport Options", "Home", "Combat Training",
				"Skilling Locations", "Bossing Teleports", "Next Page");

	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == 1) {
			if (componentId == OPTION_1) { // home
				if (player.getAttackedByDelay() + 10000 > Utils
						.currentTimeMillis()) {
					player.getPackets()
							.sendGameMessage(
									"You can't home teleport until 10 seconds after the end of combat.");
				}
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2143, 5540, 3));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				player.getControlerManager().forceStop();
				player.getControlerManager().removeControlerWithoutCheck();
				//player.getControlerManager().startControler("HomeControler");
				stage = 2;
			} else if (componentId == OPTION_2) { // Combat Training
				sendOptionsDialogue("Combat Training", "Rock Crabs", "Yaks",
						"Cows", "Frost Dragons", "DKS");
				stage = 3;
			} else if (componentId == OPTION_3) { // Skilling locations
				sendOptionsDialogue("Skilling Locations", "Woodcutting",
						"Thieving", "Summoning", "Hunter", "Next Page");
				stage = 4;
			} else if (componentId == OPTION_4) { // Bossing Teleports
				sendOptionsDialogue("Bossing Teleports", "King Black Dragon",
						"Grotworm Cave", "Kalphite Queen",
						"Corporeal Beast", "Tormented Demons");
				stage = 5;
			} else if (componentId == OPTION_5) {
				sendOptionsDialogue("Teleport Options", "Minigames",
						"Slayer Areas", "PVP", "Red Sandstone",
						"Living Rock Caverns");
				stage = 6;
			}

			/**
			 * Combat Training Teleports
			 */

		} else if (stage == 3) {
			if (componentId == OPTION_1) { // Rock Crabs
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2705,
						3716, 0));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				player.getControlerManager().forceStop();
				player.getControlerManager().removeControlerWithoutCheck();
			} else if (componentId == OPTION_2) { // Yaks
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2324,
						3803, 0));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				player.getControlerManager().forceStop();
				player.getControlerManager().removeControlerWithoutCheck();
			} else if (componentId == OPTION_3) { // Cows
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3251,
						3266, 0));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				player.getControlerManager().forceStop();
				player.getControlerManager().removeControlerWithoutCheck();
			} else if (componentId == OPTION_4) { // Frost Dragons
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(1298,
						4510, 0));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				player.getControlerManager().forceStop();
				player.getControlerManager().removeControlerWithoutCheck();
			} else if (componentId == OPTION_5) { // Daggonaths
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2907,
						4449, 0));
				player.sendMessage("Fixing soon");
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				player.getControlerManager().forceStop();
			}

			/**
			 * Skilling Teleports
			 */

		} else if (stage == 4) {
			if (componentId == OPTION_1) { // Woodcutting
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2725,
						3491, 0));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				player.getControlerManager().forceStop();
				player.getControlerManager().removeControlerWithoutCheck();
			} else if (componentId == OPTION_2) { // Thieve
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2662,
						3303, 0));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				player.getControlerManager().forceStop();
				player.getControlerManager().removeControlerWithoutCheck();
			} else if (componentId == OPTION_3) { // Summoning
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2333, 10015, 0));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				player.getControlerManager().forceStop();
				player.getControlerManager().removeControlerWithoutCheck();
			} else if (componentId == OPTION_4) { // Hunter
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2525,
						2915, 0));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				player.getControlerManager().forceStop();
				player.getControlerManager().removeControlerWithoutCheck();
			} else if (componentId == OPTION_5) {
				sendOptionsDialogue("Skilling Locations", "Gnome Agility",
						"Mining", "Fishing", "Barbarian Outpost",
						"Runecrafting Abyss");
				stage = 10;
			}

			/**
			 * Skilling Page 2
			 */

		} else if (stage == 10) {
			if (componentId == OPTION_1) { // Gnome Agility
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2468,
						3437, 0));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				player.getControlerManager().forceStop();
				player.getControlerManager().removeControlerWithoutCheck();
			} else if (componentId == OPTION_2) { // Mining
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3298,
						3283, 0));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				player.getControlerManager().forceStop();
				player.getControlerManager().removeControlerWithoutCheck();
			} else if (componentId == OPTION_3) { // Fishing Area with custom
													// spots
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2599,
						3421, 0));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				player.getControlerManager().forceStop();
				player.getControlerManager().removeControlerWithoutCheck();
			} else if (componentId == OPTION_4) { // Barbarian Agility Outpost
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2552,
						3563, 0));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				player.getControlerManager().forceStop();
				player.getControlerManager().removeControlerWithoutCheck();
			} else if (componentId == OPTION_5) { // Abyss
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3040,
						4842, 0));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				player.getControlerManager().forceStop();
				player.getControlerManager().removeControlerWithoutCheck();
			}

			/**
			 * Bossing Teleports
			 */

		} else if (stage == 5) {
			if (componentId == OPTION_1) { // King Black Dragon
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3067,
						10255, 0));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				player.getControlerManager().forceStop();
				player.getControlerManager().removeControlerWithoutCheck();
			} else if (componentId == OPTION_2) { // GrotWorm
				if (player.getSkills().getLevelForXp(Skills.SLAYER) < 10) {
					player.getPackets().sendGameMessage(
							"You need a Slayer level of 10 to enter.");
				} else {
					Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(1206,
							6372, 0));
					player.getInterfaceManager().closeChatBoxInterface();
					player.getInterfaceManager().closeOverlay(true);
					player.getControlerManager().forceStop();
					player.getControlerManager().removeControlerWithoutCheck();
					return;
				}
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				player.getControlerManager().forceStop();
				player.getControlerManager().removeControlerWithoutCheck();
				//player.getControlerManager().startControler(
				//		"QueenBlackDragonControler");
			} else if (componentId == OPTION_3) { // Kalphite Queen
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3508,
						9493, 0));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				player.getControlerManager().forceStop();
				player.getControlerManager().removeControlerWithoutCheck();
			} else if (componentId == OPTION_4) { // Corp
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2966,
						4383, 2));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				player.getControlerManager().forceStop();
				player.getControlerManager().removeControlerWithoutCheck();
			} else if (componentId == OPTION_5) { // Tormented Demons
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2562,
						5739, 0));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				player.getControlerManager().forceStop();
				player.getControlerManager().removeControlerWithoutCheck();
			}

			/**
			 * Godwars Dungeon Teleport
			 */

		} else if (stage == 6) {
			if (componentId == OPTION_1) {
				sendOptionsDialogue("Minigames", "Godwars Dungeon",
						"Warriors Guild", "Barrows", "Dominion Tower",
						"Next Page");
				stage = 7;

				/**
				 * Slayer Teleports
				 */

			} else if (componentId == OPTION_2) {
				sendOptionsDialogue("Slayer Areas", "Slayer Tower",
						"Ganodermic Beasts", "Glacors", "Jadinkos", "None");
				stage = 9;

				/**
				 * PVP Teleports
				 */

			} else if (componentId == OPTION_3) {
				sendOptionsDialogue("PVP", "Mage Bank", "Multi Area", "Wests",
						"Easts", "Revenants");
				stage = 11;

				/**
				 * Red Sandstone
				 */

			} else if (componentId == OPTION_4) {
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2589,
						2881, 0));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				player.getControlerManager().forceStop();
				player.getControlerManager().removeControlerWithoutCheck();

			} else if (componentId == OPTION_5) {
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3656,
						5113, 0));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				player.getControlerManager().forceStop();
				player.getControlerManager().removeControlerWithoutCheck();
			}

			/**
			 * Godwars Dungeon Teleports
			 */

		} else if (stage == 7) {
			if (componentId == OPTION_1) {
				sendOptionsDialogue("Godwars Dungeon", "Nex", "Bandos",
						"Armadyl", "Saradomin", "Zamorak");
				stage = 8;

				/**
				 * The RuneSpan
				 */

			} else if (componentId == OPTION_2) { // Warriors Guild
				warriorsGuild(2871, 3542, 0);
				player.getInterfaceManager().closeChatBoxInterface();

			} else if (componentId == OPTION_3) { // Barrows
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3565,
						3289, 0));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				player.getControlerManager().forceStop();
				player.getControlerManager().removeControlerWithoutCheck();

			} else if (componentId == OPTION_4) { // Dominion Tower
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3366,
						3083, 0));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				player.getControlerManager().forceStop();
				player.getControlerManager().removeControlerWithoutCheck();

			} else if (componentId == OPTION_5) {
				sendOptionsDialogue("Minigames", "Fight Kiln", "Fight Caves",
						"Fight Pits", "Castle Wars", "More Options");
				stage = 12;
			}

			/**
			 * Godwars Dungeon Teleports
			 */

		} else if (stage == 8) {
			if (componentId == OPTION_1) { // Nex
				teleportPlayer(2904, 5203, 0);
				player.getInterfaceManager().closeChatBoxInterface();

			} else if (componentId == OPTION_2) { // Bandos
				teleportPlayer(2870, 5363, 2);
				player.getInterfaceManager().closeChatBoxInterface();

			} else if (componentId == OPTION_3) { // Armadyl
				teleportPlayer(2838, 5297, 2);
				player.getInterfaceManager().closeChatBoxInterface();

			} else if (componentId == OPTION_4) { // Saradomin
				teleportPlayer(2898, 5266, 0);
				player.getInterfaceManager().closeChatBoxInterface();

			} else if (componentId == OPTION_5) { // Zamorak
				teleportPlayer(2925, 5330, 2);
				player.getInterfaceManager().closeChatBoxInterface();
			}

			/**
			 * Slayer Teleports
			 */

		} else if (stage == 9) {
			if (componentId == OPTION_1) { // Slayer Tower
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3429,
						3534, 0));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				player.getControlerManager().forceStop();
				player.getControlerManager().removeControlerWithoutCheck();

			} else if (componentId == OPTION_2) { // Ganodermic Beast
				if (player.getSkills().getLevelForXp(Skills.SLAYER) < 90) {
					player.getPackets()
							.sendGameMessage(
									"You need a slayer level of 90 to kill Ganodermic Beasts.");
					player.getInterfaceManager().closeChatBoxInterface();
					player.getInterfaceManager().closeOverlay(true);
					player.getControlerManager().forceStop();
					player.getControlerManager().removeControlerWithoutCheck();
					return;
				}
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(4654,
						5485, 0));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				player.getControlerManager().forceStop();
				player.getControlerManager().removeControlerWithoutCheck();

			} else if (componentId == OPTION_3) { // Glacor
				if (player.getSkills().getLevelForXp(Skills.SLAYER) < 90) {
					player.getPackets().sendGameMessage(
							"You need a slayer level of 90 to kill Glacors.");
					player.getInterfaceManager().closeChatBoxInterface();
					player.getInterfaceManager().closeOverlay(true);
					player.getControlerManager().forceStop();
					player.getControlerManager().removeControlerWithoutCheck();
					return;
				}
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(4182,
						5731, 0));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				player.getControlerManager().forceStop();
				player.getControlerManager().removeControlerWithoutCheck();

			} else if (componentId == OPTION_4) { // Jadinko
				if (player.getSkills().getLevelForXp(Skills.SLAYER) < 85) {
					player.getPackets().sendGameMessage(
							"You need a slayer level of 85 to kill Jadinkos.");
					player.getInterfaceManager().closeChatBoxInterface();
					player.getInterfaceManager().closeOverlay(true);
					player.getControlerManager().forceStop();
					player.getControlerManager().removeControlerWithoutCheck();
					return;
				}
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3013,
						9273, 0));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				player.getControlerManager().forceStop();
				player.getControlerManager().removeControlerWithoutCheck();

			} else if (componentId == OPTION_5) { // None
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				player.getControlerManager().forceStop();
				player.getControlerManager().removeControlerWithoutCheck();
			}

			/**
			 * Minigames Page 2
			 */

		} else if (stage == 12) {
			if (componentId == OPTION_1) { // Fight Kiln
				Magic.sendNormalTeleportSpell(player, 0, 0, FightKiln.OUTSIDE);
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				player.getControlerManager().forceStop();
				player.getControlerManager().removeControlerWithoutCheck();

			} else if (componentId == OPTION_2) { // Fight Caves
				Magic.sendNormalTeleportSpell(player, 0, 0, FightCaves.OUTSIDE);
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				player.getControlerManager().forceStop();
				player.getControlerManager().removeControlerWithoutCheck();

			} else if (componentId == OPTION_3) { // Fight Pits
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(4608,
						5061, 0));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				player.getControlerManager().forceStop();
				player.getControlerManager().removeControlerWithoutCheck();

			} else if (componentId == OPTION_4) { // Castle Wars
				Magic.sendNormalTeleportSpell(player, 0, 0, CastleWars.LOBBY);
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				player.getControlerManager().forceStop();
				player.getControlerManager().removeControlerWithoutCheck();

			} else if (componentId == OPTION_5) {
				sendOptionsDialogue("Minigames", "Clan Wars",
						"Recipe For Disaster");
				stage = 15;
			}

		} else if (stage == 15) {
			if (componentId == OPTION_1) { // Clan Wars
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2993,
						9679, 0));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				player.getControlerManager().forceStop();
				player.getControlerManager().removeControlerWithoutCheck();

			} else if (componentId == OPTION_2) {
				player.getControlerManager().startControler(
						"RecipeForDisaster", 1);
				player.getInterfaceManager().closeChatBoxInterface();
			}

			/**
			 * PVP
			 */

		} else if (stage == 11) {
			if (componentId == OPTION_1) { // Mage Bank
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2538,
						4715, 0));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				player.getControlerManager().forceStop();
				player.getControlerManager().removeControlerWithoutCheck();
				player.getControlerManager().startControler("Wilderness");

			} else if (componentId == OPTION_2) { // Multi
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3240,
						3611, 0));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				player.getControlerManager().forceStop();
				player.getControlerManager().removeControlerWithoutCheck();
				player.getControlerManager().startControler("Wilderness");

			} else if (componentId == OPTION_3) { // Wests
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2984,
						3596, 0));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				player.getControlerManager().forceStop();
				player.getControlerManager().removeControlerWithoutCheck();
				player.getControlerManager().startControler("Wilderness");

			} else if (componentId == OPTION_4) { // Easts
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3360,
						3658, 0));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				player.getControlerManager().forceStop();
				player.getControlerManager().removeControlerWithoutCheck();
				player.getControlerManager().startControler("Wilderness");

			} else if (componentId == OPTION_5) { // Revenants
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3078,
						10058, 0));
				player.getControlerManager().startControler("Wilderness");
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				player.getControlerManager().forceStop();
				player.getControlerManager().removeControlerWithoutCheck();

			} else if (componentId == OPTION_5) { // Cancel
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				player.getControlerManager().forceStop();
				player.getControlerManager().removeControlerWithoutCheck();
			}
		}
}

	@Override
	public void finish() {
	}

	private void teleportPlayer(int x, int y, int z) {
		player.setNextWorldTile(new WorldTile(x, y, z));
		player.stopAll();
		player.getControlerManager().startControler("GodWars");
	}

	private void warriorsGuild(int x, int y, int z) {
		player.setNextWorldTile(new WorldTile(x, y, z));
		player.stopAll();
		player.getControlerManager().startControler("WGuildControler");
	}

}
