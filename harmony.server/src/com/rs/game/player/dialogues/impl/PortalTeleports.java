package com.rs.game.player.dialogues.impl;

import java.util.Calendar;

import com.rs.Settings;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.minigames.CastleWars;
import com.rs.game.minigames.Heist;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.Summoning;
import com.rs.game.player.content.Magic;
import com.rs.game.player.content.bossinstance.BossInstance;
import com.rs.game.player.content.bossinstance.BossInstanceManager;
import com.rs.game.player.content.bossinstance.impl.ZulrahInstance;
import com.rs.game.player.content.pet.Pets;
import com.rs.game.player.controlers.FightCaves;
import com.rs.game.player.controlers.FightKiln;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.game.player.interfaces.BossTeleportInterface;
import com.rs.game.player.interfaces.SkillTeleportInterface;
import com.rs.game.player.interfaces.SlayerTeleportInterface;
import com.rs.utils.Colors;
import com.rs.utils.ShopsHandler;
import com.rs.utils.Utils;

public class PortalTeleports extends Dialogue {

	@Override
	public void start() {
		stage = 1;
		sendOptionsDialogue("Teleport Options", "Recycle Centre", "Combat Training",
				"Skilling Locations", "Bossing Teleports", "Next Page");

	}
	@Override
	public void run(int interfaceId, int componentId) {
		if (player.getAttackedByDelay() + 10000 > Utils
				.currentTimeMillis()) {
			player.getDialogueManager().startDialogue("SimpleMessage", "You can't teleport until 10 seconds after the end of combat.");
			return;
		}
		if (stage == 1) {
			if (componentId == OPTION_1) { // recycle centre
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2863, 2579, 0));
				stage = 2;
			} else if (componentId == OPTION_2) { // Combat Training
				sendOptionsDialogue("Combat Training", "Train", "Barrows",
						"Hill Giants", "Frost Dragons", "DKS");
				stage = 3;
			} else if (componentId == OPTION_3) { // Skilling locations
				player.skilltelingcomponent = true;
				SkillTeleportInterface.sendInterface(player);
				end();
			} else if (componentId == OPTION_4) { // Bossing Teleports
				sendOptionsDialogue("Which kind of bossing", "Standard",
						"Raids", "Instances", "Prestige Bosses");
				stage = 22;
			} else if (componentId == OPTION_5) {
				sendOptionsDialogue("Teleport Options", "Minigames",
						"Slayer Areas", "PVP", "Dryaxion's Dungeon",
						"Prestige Zone");
				stage = 6;
			}
			
		} else if (stage == 22) {
			if (componentId == OPTION_1) { // standard bossing
				BossTeleportInterface.OpenInterface(player);
				end();
			} else if (componentId == OPTION_2) { // Raids
				sendOptionsDialogue("Raids Teleports", "Hybrid Trio", "Gulega", "Chambers of Xeric", "Theatre of Blood", "Nevermind");
				stage = 23;
			} else if (componentId == OPTION_3) {
				if (!World.isHomeArea(player)) {
					player.sendMessage(Colors.cyan + "You can only make an instance in the home area!");
					end();
					return;
				}
				if (player.instancedelay > 0) {
					player.sendMessage(Colors.cyan + "Woah! Slow down, you're attempting to join instances too quickly. Please wait another " + player.instancedelay + " seconds");
					end();
					return;
				}

				player.getDialogueManager().startDialogue("BossInstanceDialogue");
				player.sendMessage("Use one of these to enter an instance;");
				player.sendMessage("corp,bandos,sara,arma,zammy,kk,kbd,rots");
			} else if (componentId == OPTION_4) { // Raids
				if (player.prestigeTokens> 0) {
				player.getDialogueManager().startDialogue("PrestigeBossTeleporter");
			//	end();
				} else {
					player.sendMessage(Colors.cyan + "Woah! You aren't worth yet!");
					end();
				}
			}
			
		} else if (stage == 23) {
			if (componentId == OPTION_1) { // trio raid
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2403, 2841, 0));
			} else if (componentId == OPTION_2) { // Gulega Raid
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2403, 2841, 0));
			} else if (componentId == OPTION_3) { // OSRS
				if (player.BossKills() < 100) {
					player.sendMessage("You much have at least 100 boss kills to access this raid.");
					end();
					return;
				}
				if (player.getFamiliar() != null || player.getPet() != null || Summoning.hasPouch(player) || Pets.hasPet(player)) {
					player.sendMessage("You cannot bring a familiar to this boss.");
					player.getInterfaceManager().closeChatBoxInterface();
					player.getInterfaceManager().closeOverlay(true);
					end();
					return;
				}
				if (!player.getInventory().containsItem(995, 1000000) && !World.Level3Zone(player)) {
					player.sendMessage("Please have 1,000,000 coins in your inventory in order to pay a fee!");
					player.getInterfaceManager().closeChatBoxInterface();
					player.getInterfaceManager().closeOverlay(true);
					end();
					return;
				}
				player.osrsraiddamagepoints = 0;
				if (!World.Level3Zone(player)) {
				player.getInventory().deleteItem(995, 1000000);
				Settings.GpSyncAmount += 1000000;
				}
				player.sendMessage("<col=ff0000> It is highly advised that you choose your team carefully as using more than 5 people will leaving people behind when you progress.");
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3307, 5195, 0));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
			} else if (componentId == OPTION_4) {//theatre of blood
				if (player.BossKills() < 100) {
					player.sendMessage("You much have at least 100 boss kills to access this raid.");
					end();
					return;
				}
				if (player.getFamiliar() != null || player.getPet() != null || Summoning.hasPouch(player) || Pets.hasPet(player)) {
					player.sendMessage("You cannot bring a familiar to this boss.");
					player.getInterfaceManager().closeChatBoxInterface();
					player.getInterfaceManager().closeOverlay(true);
					end();
					return;
				}
				if (!player.getInventory().containsItem(995, 2000000) && !World.Level3Zone(player)) {
					player.sendMessage("Please have 2,000,000 coins in your inventory in order to pay a fee!");
					player.getInterfaceManager().closeChatBoxInterface();
					player.getInterfaceManager().closeOverlay(true);
					end();
					return;
				}
				player.theatreofblooddamagepoints = 0;
				// player.tobkilledboss1 = false;
				// player.tobkilledboss2 = false;
				// player.tobkilledboss3 = false;
				if (!World.Level3Zone(player)) {
				player.getInventory().deleteItem(995, 2000000);
				Settings.GpSyncAmount += 2000000;
				}
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2578, 9650, 0));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
			} else if (componentId == OPTION_5) {
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
			}

			/**
			 * Combat Training Teleports
			 */

		} else if (stage == 3) {
			if (componentId == OPTION_1) { // train
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2896, 2728, 0));
				player.sendMessage(Colors.red + "Here you can kill the Noobs of Zamorak for keys to get rewards from the chest or farmers for seeds.");
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				//player.getControlerManager().forceStop();
				//player.getControlerManager().removeControlerWithoutCheck();
			} else if (componentId == OPTION_2) { // Yaks
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3565,
						3289, 0));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				//player.getControlerManager().forceStop();
				//player.getControlerManager().removeControlerWithoutCheck();
			} else if (componentId == OPTION_3) { // HillGiants
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3117,
						9852, 0));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				//player.getControlerManager().forceStop();
				//player.getControlerManager().removeControlerWithoutCheck();
			} else if (componentId == OPTION_4) { // Frost Dragons
				if (player.getSkills().getLevel(Skills.DUNGEONEERING) < 85) {
					player.getPackets().sendGameMessage(
							"You need a Dungeoneering level of 85 to enter.");
					end();
				} else {
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(1298,
						4510, 0));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				}
			//player.getControlerManager().forceStop();
				//player.getControlerManager().removeControlerWithoutCheck();
			} else if (componentId == OPTION_5) { // Daggonaths
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2907,
						4449, 0));
				//player.sendMessage("Fixing soon");
				player.getInterfaceManager().closeChatBoxInterface();
				//player.getInterfaceManager().closeOverlay(true);
				//player.getControlerManager().forceStop();
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
				//player.getControlerManager().forceStop();
				//player.getControlerManager().removeControlerWithoutCheck();
			} else if (componentId == OPTION_2) { // Thieve
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2662,
						3303, 0));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				//player.getControlerManager().forceStop();
				//player.getControlerManager().removeControlerWithoutCheck();
			} else if (componentId == OPTION_3) { // Summoning
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2333, 10015, 0));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				//player.getControlerManager().forceStop();
				//player.getControlerManager().removeControlerWithoutCheck();
			} else if (componentId == OPTION_4) { // Hunter
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2525,
						2915, 0));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				//player.getControlerManager().forceStop();
				//player.getControlerManager().removeControlerWithoutCheck();
			} else if (componentId == OPTION_5) {
				sendOptionsDialogue("Skilling Locations", "Gnome Agility",
						"Mining", "Fishing", "Barbarian Outpost",
						"More..");
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
				//player.getControlerManager().forceStop();
				//player.getControlerManager().removeControlerWithoutCheck();
			} else if (componentId == OPTION_2) { // Mining
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3298,
						3283, 0));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				//player.getControlerManager().forceStop();
				//player.getControlerManager().removeControlerWithoutCheck();
			} else if (componentId == OPTION_3) { // Fishing Area with custom
													// spots
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2599,
						3421, 0));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				//player.getControlerManager().forceStop();
				//player.getControlerManager().removeControlerWithoutCheck();
			} else if (componentId == OPTION_4) { // Barbarian Agility Outpost
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2552,
						3563, 0));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				//player.getControlerManager().forceStop();
				//player.getControlerManager().removeControlerWithoutCheck();
			} else if (componentId == OPTION_5) { // NEXT
				sendOptionsDialogue("Skilling Locations", "Runecrafting Abyss",
						"Construction", "Farming", "Smithing",
						"Next");
				stage = 15;
			}
			
		} else if (stage == 15) {
			if (componentId == OPTION_1) { // ABYSS
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3040, 4842, 0));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				//player.getControlerManager().forceStop();
				//player.getControlerManager().removeControlerWithoutCheck();
			} else if (componentId == OPTION_2) { // CONSTRUCTION
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2542,
						3095, 0));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				//player.getControlerManager().forceStop();
				//player.getControlerManager().removeControlerWithoutCheck();
			} else if (componentId == OPTION_3) { // Farming
				sendOptionsDialogue("Farming Locations", "Taverly",
						"Falador", "Lmbridge", "Varrock",
						"More");
				stage = 90;
			} else if (componentId == OPTION_4) { // SMITHING
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(1751,
						5289, 1));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				//player.getControlerManager().forceStop();
				//player.getControlerManager().removeControlerWithoutCheck();
			} else if (componentId == OPTION_5) {
				sendOptionsDialogue("Skilling Locations", "Shilo Village",
						"Essence mine", "Nothing.");
				stage = 30;
				//end();
			}
		} else if (stage == 30) {
			if (componentId == OPTION_1) {//shilo village
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2823,
						2999, 0));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				//player.getControlerManager().forceStop();
				//player.getControlerManager().removeControlerWithoutCheck();
			} else if (componentId == OPTION_2) { // Essence mine
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2924,
						4819, 0));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				//player.getControlerManager().forceStop();
				//player.getControlerManager().removeControlerWithoutCheck();
				end();
			}
		} else if (stage == 90) {//fARMING TELEPORTS
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
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3194,
						3225, 0));
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
						"Draynor");
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
			}else if (componentId == OPTION_3) { // catheby
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2815,
						3462, 0));
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
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3053,
						3306, 0));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				end();
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
				//player.getControlerManager().forceStop();
				//player.getControlerManager().removeControlerWithoutCheck();
			} else if (componentId == OPTION_2) { // GrotWorm
				if (player.getSkills().getLevel(Skills.SLAYER) < 10) {
					player.getPackets().sendGameMessage(
							"You need a Slayer level of 10 to enter.");
				} else {
					Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(1206,
							6372, 0));
					player.getInterfaceManager().closeChatBoxInterface();
					player.getInterfaceManager().closeOverlay(true);
					//player.getControlerManager().forceStop();
					//player.getControlerManager().removeControlerWithoutCheck();
					return;
				}
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				//player.getControlerManager().forceStop();
				//player.getControlerManager().removeControlerWithoutCheck();
				//player.getControlerManager().startControler(
				//		"QueenBlackDragonControler");
			} else if (componentId == OPTION_3) { // Kalphite Queen
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3096,
						5539, 0));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				//player.getControlerManager().forceStop();
				//player.getControlerManager().removeControlerWithoutCheck();
			} else if (componentId == OPTION_4) { // Corp
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2966,
						4383, 2));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				//player.getControlerManager().forceStop();
				//player.getControlerManager().removeControlerWithoutCheck();
			} else if (componentId == OPTION_5) { // Tormented Demons
				//Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2562,
				//		5739, 0));
				sendOptionsDialogue("Bosses", "Tormented Demons", "Nex",
						"Glacors", "Rise Of The Six", "More Options");
				stage = 13;
				
			}
			
		} else if (stage == 13) {
			if (componentId == OPTION_1) { // TDS
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2562,
						5739, 0));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				//player.getControlerManager().forceStop();
				//player.getControlerManager().removeControlerWithoutCheck();
			} else if (componentId == OPTION_2) { // NEX
				teleportPlayer(2905, 5203, 0);
					player.getInterfaceManager().closeChatBoxInterface();
					player.getInterfaceManager().closeOverlay(true);
					//player.getControlerManager().forceStop();
				//	player.getControlerManager().removeControlerWithoutCheck();
			} else if (componentId == OPTION_3) { // GLACORS
				if (player.getSkills().getLevel(Skills.SLAYER) < 50) {
					player.getPackets().sendGameMessage(
							"You need a slayer level of 50 to kill Glacors.");
					player.getInterfaceManager().closeChatBoxInterface();
					player.getInterfaceManager().closeOverlay(true);
					//player.getControlerManager().forceStop();
					//player.getControlerManager().removeControlerWithoutCheck();
					return;
				}
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(4182,
						5731, 0));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				//player.getControlerManager().forceStop();
				//player.getControlerManager().removeControlerWithoutCheck();
			} else if (componentId == OPTION_4) { // Rise of the six
				if (!player.getInventory().contains(29481)) {
					player.sendMessage("You need a barrows Totem to access this. Normal Barrows drop them.");
					player.getInterfaceManager().closeChatBoxInterface();
					player.getInterfaceManager().closeOverlay(true);
					return;
				}
				if(player.getFamiliar() != null || player.getPet() != null || Summoning.hasPouch(player) || Pets.hasPet(player)) {
					player.sendMessage("You cannot bring a familiar to this boss.");
					player.getInterfaceManager().closeChatBoxInterface();
					player.getInterfaceManager().closeOverlay(true);
					return;
				}
				if (player.getSkills().getLevel(Skills.SLAYER) < 95) {
					player.sendMessage("You need a slayer level of 95 to access this minigame.");
					player.getInterfaceManager().closeChatBoxInterface();
					player.getInterfaceManager().closeOverlay(true);
					return;
				}
				player.getInventory().deleteItem(29481, 1);
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3620,
						3342, 0));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				//player.getControlerManager().forceStop();
				//player.getControlerManager().removeControlerWithoutCheck();
			} else if (componentId == OPTION_5) { // Tormented Demons
				//Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2562,
				//		5739, 0));
				sendOptionsDialogue("Bosses", "Bandos", "Zamorak",
						"Armadyl", "Saradomin", "More");
				stage = 14;
			}
				
			} else if (stage == 14) {
				if (componentId == OPTION_1) { // BANDOS
					if (player.gwdperk != true) {
						if (player.gwdkc < 20) {
							player.sendMessage("You need 20 GWD kc to enter this boss.");
							player.getInterfaceManager().closeChatBoxInterface();
							player.getInterfaceManager().closeOverlay(true);
							return;
						}
					}
					Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2868,
							5354, 0));
					if (player.gwdperk != true) {
						player.gwdkc -= 20;
					}
					player.getInterfaceManager().closeChatBoxInterface();
					player.getInterfaceManager().closeOverlay(true);
					//player.getControlerManager().forceStop();
					//player.getControlerManager().removeControlerWithoutCheck();
				} else if (componentId == OPTION_2) { // ZAMORAK
					if (player.gwdperk != true) {
						if (player.gwdkc < 20) {
							player.sendMessage("You need 20 GWD kc to enter this boss.");
							player.getInterfaceManager().closeChatBoxInterface();
							player.getInterfaceManager().closeOverlay(true);
							return;
						}
					}
					Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2925,
							5330, 0));
					if (player.gwdperk != true) {
						player.gwdkc -= 20;
					}
						player.getInterfaceManager().closeChatBoxInterface();
						player.getInterfaceManager().closeOverlay(true);
						//player.getControlerManager().forceStop();
						//player.getControlerManager().removeControlerWithoutCheck();
				} else if (componentId == OPTION_3) { // ARMADYL
					if (player.gwdperk != true) {
						if (player.gwdkc < 20) {
							player.sendMessage("You need 20 GWD kc to enter this boss.");
							player.getInterfaceManager().closeChatBoxInterface();
							player.getInterfaceManager().closeOverlay(true);
							return;
						}
					}
					Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2829,
							5302, 0));
					if (player.gwdperk != true) {
						player.gwdkc -= 20;
					}
					
					player.getInterfaceManager().closeChatBoxInterface();
					player.getInterfaceManager().closeOverlay(true);
					//player.getControlerManager().forceStop();
					//player.getControlerManager().removeControlerWithoutCheck();
				} else if (componentId == OPTION_4) { // SARADOMIN
					if (player.gwdperk != true) {
						if (player.gwdkc < 20) {
							player.sendMessage("You need 20 GWD kc to enter this boss.");
							player.getInterfaceManager().closeChatBoxInterface();
							player.getInterfaceManager().closeOverlay(true);
							return;
						}
					}
					Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2922,
							5251, 0));
					if (player.gwdperk != true) {
						player.gwdkc -= 20;
					}
					player.getInterfaceManager().closeChatBoxInterface();
					player.getInterfaceManager().closeOverlay(true);
					//player.getControlerManager().forceStop();
					//player.getControlerManager().removeControlerWithoutCheck();
				} else if (componentId == OPTION_5) { // Tormented Demons
					//Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2562,
					//		5739, 0));
					sendOptionsDialogue("Bosses", "WildyWyrm (<col=ff0000>PVP ZONE</col>)", "Vorago", "Ava Of Destruction", "Night-gazer", "Next Page");
					stage = 17;
					
				} else {
					player.sendMessage("<col=ff000>You need a killcount of 20+ to use these teleports. ");
					player.sendMessage("Your current killcount is "+player.gwdkc+"");
					player.getInterfaceManager().closeChatBoxInterface();
					player.getInterfaceManager().closeOverlay(true);
					end();
				}
			} else if (stage == 17) {
				if (componentId == OPTION_1) { // WildyWyrm
				//	Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3030,
					//		3829, 0));
				player.getDialogueManager().startDialogue("WildyWyrm");
				} else if (componentId == OPTION_2) { // vorago
					if (player.getSkills().getLevel(Skills.SLAYER) < 95) {
						player.getPackets()
								.sendGameMessage(
										"You need a slayer level of 95 to kill Vorago.");
						player.getInterfaceManager().closeChatBoxInterface();
						player.getInterfaceManager().closeOverlay(true);
						//player.getControlerManager().forceStop();
						//player.getControlerManager().removeControlerWithoutCheck();
						return;
					}
					player.getDialogueManager().startDialogue("Voragofee");
				} else if (componentId == OPTION_3) { // ava of destruction
					Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(1807,
							3212, 0));
					player.getInterfaceManager().closeChatBoxInterface();
					player.getInterfaceManager().closeOverlay(true);
						//player.getControlerManager().forceStop();
						//player.getControlerManager().removeControlerWithoutCheck();
				} else if (componentId == OPTION_4) { // gazer
					if(player.getFamiliar() != null || player.getPet() != null || Summoning.hasPouch(player) || Pets.hasPet(player)) {
						player.sendMessage("You cannot bring a familiar to this boss.");
						player.getInterfaceManager().closeChatBoxInterface();
						player.getInterfaceManager().closeOverlay(true);
						return;
					}
					Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2413,
							3529, 0));
					player.getInterfaceManager().closeChatBoxInterface();
					player.getInterfaceManager().closeOverlay(true);
						//player.getControlerManager().forceStop();
						//player.getControlerManager().removeControlerWithoutCheck();
				} else if (componentId == OPTION_5) {
					sendOptionsDialogue("Bosses", "Bad Santa", "Dryax", "Kalphite King", "Sirenic the Spider", "Next page");
					stage = 18;
				} else {
					end();
					player.getInterfaceManager().closeChatBoxInterface();
					player.getInterfaceManager().closeOverlay(true);
				}
			} else if (stage == 18) {
				if (componentId == OPTION_1) {
					Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2648,
							10425, 0));
					player.getInterfaceManager().closeChatBoxInterface();
					player.getInterfaceManager().closeOverlay(true);
				
			} else if (componentId == OPTION_2) { // dryax
			if (player.getInventory().containsOneItem(29866)) {
				player.getInventory().deleteItem(29866, 1);
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2770,
						2980, 0));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
			} else {
				player.sendMessage("<col=ff0000>You need a dryaxion key to enter this boss.");
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
			}
			} else if (componentId == OPTION_3) { // kk
				if(player.getFamiliar() != null || player.getPet() != null || Summoning.hasPouch(player) || Pets.hasPet(player)) {
					player.sendMessage("This boss does not currently support familiars or pets.");
					player.getInterfaceManager().closeChatBoxInterface();
					player.getInterfaceManager().closeOverlay(true);
					return;
				}
				player.getDialogueManager().startDialogue("KkFee");
			} else if (componentId == OPTION_4) {
				player.getDialogueManager().startDialogue("SirenicConfirmation");
			} else if (componentId == OPTION_5) {
				sendOptionsDialogue("Bosses", "Hope devourer", "Party Demon", "Zulrah", "Ingenuity" ,"Next page");
				stage = 19;
			}
			} else if (stage == 19) {
				if (componentId == OPTION_1) {
					Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2260,
							3602, 0));
					player.getInterfaceManager().closeChatBoxInterface();
					player.getInterfaceManager().closeOverlay(true);

				}else if (componentId == OPTION_2) {
					Calendar calendar = Calendar.getInstance();
					if (Settings.canteletopdemon != true) {
						player.sendMessage("You have no reason to go here yet.");
						end();
						return;
					}
					Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3249,
							9867, 0));
				if (Skills.getTotalLevel(player) < 1500) {
									player.getDialogueManager().startDialogue("SimpleMessage", "The Demon will not reward you for yout efforts.");
									end();
									return;
								}
					player.canlootpdemonchest = true;
				//	player.PdemonKills ++;
				//	player.sendMessage("Your Party Demon kill count is: "+player.PdemonKills+"");
					player.getInterfaceManager().closeChatBoxInterface();
					player.getInterfaceManager().closeOverlay(true);
					
				}else if (componentId == OPTION_3) {
					BossInstance instance = null;
					instance = new ZulrahInstance(player);
					BossInstanceManager.SINGLETON.add(instance);
					player.getInterfaceManager().closeChatBoxInterface();
					player.getInterfaceManager().closeOverlay(true);
				}else if (componentId == OPTION_4) {
					Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2591,
							3909, 0));
					player.sendMessage("NOTE: Only players with a total level of 1500 can loot the chest, however others can still gain experience.");
					player.getInterfaceManager().closeChatBoxInterface();
					player.getInterfaceManager().closeOverlay(true);

				} else if (componentId == OPTION_5) {
					sendOptionsDialogue("Bosses",
							"Necrolord",
							"Yk'Lagor the Thunderous",
							"Sunfreet",
							"Anivia",
							"Next");
					stage = 21;
				}
			} else if (stage == 21) {
				if (componentId == OPTION_1) {
				if (!player.isVeteran()) {
					player.getInterfaceManager().closeChatBoxInterface();
					player.getInterfaceManager().closeOverlay(true);
					player.sendMessage("You must be a veteran to access this boss!");
				return;
				}
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3812,
						3060, 0));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				}else if (componentId == OPTION_2) {
					Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2574,
							9500, 0));
					player.getInterfaceManager().closeChatBoxInterface();
					player.getInterfaceManager().closeOverlay(true);
					
				}else if (componentId == OPTION_3) {
					if (player.getSkills().getLevel(Skills.SLAYER) < 95) {
						player.getPackets()
								.sendGameMessage(
										"You need a slayer level of 95 to kill Sunfreet.");
						player.getInterfaceManager().closeChatBoxInterface();
						player.getInterfaceManager().closeOverlay(true);
						//player.getControlerManager().forceStop();
						//player.getControlerManager().removeControlerWithoutCheck();
						return;
					}
					if (player.getFamiliar() != null || player.getPet() != null || Summoning.hasPouch(player) || Pets.hasPet(player)) {
						player.getInterfaceManager().closeChatBoxInterface();
						player.getInterfaceManager().closeOverlay(true);
						player.sendMessage("Sorry, but you cannot take pets into this boss!");
						return;
					}
					Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3178,
							9766, 0));
					player.getInterfaceManager().closeChatBoxInterface();
					player.getInterfaceManager().closeOverlay(true);
				}else if (componentId == OPTION_4) {
					Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2788,
							3788, 0));
					player.getInterfaceManager().closeChatBoxInterface();
					player.getInterfaceManager().closeOverlay(true);

				} else if (componentId == OPTION_5) {
					sendOptionsDialogue("Bosses",
							"Sliske",
							"Kalphite Queen",
							"Kraken",
							"Nothing");
					stage = 25;
				}
			} else if (stage == 25) {
				if (componentId == OPTION_1) {
					player.getDialogueManager().startDialogue("SliskeConfirmation");
					 //sliske
				}else if (componentId == OPTION_2) {
					Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3508,
							9493, 0));
					player.getInterfaceManager().closeChatBoxInterface();
					player.getInterfaceManager().closeOverlay(true);
					
				}else if (componentId == OPTION_3) {
					Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3694,
							5803, 0));
					player.getInterfaceManager().closeChatBoxInterface();
					player.getInterfaceManager().closeOverlay(true);
				}else if (componentId == OPTION_4) {
					player.getInterfaceManager().closeChatBoxInterface();
					player.getInterfaceManager().closeOverlay(true);

				} else if (componentId == OPTION_5) {
					player.getInterfaceManager().closeChatBoxInterface();
					player.getInterfaceManager().closeOverlay(true);
				}
		} else if (stage == 6) {
			if (componentId == OPTION_1) {
				sendOptionsDialogue("Minigames", "The Calamity",
						"Warriors Guild", "Clan wars", "Dominion Tower",
						"Next Page");
				stage = 7;

				/**
				 * Slayer Teleports
				 */

			} else if (componentId == OPTION_2) {
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				SlayerTeleportInterface.OpenInterface(player);

				/**
				 * PVP Teleports
				 */

			} else if (componentId == OPTION_3) {
				sendOptionsDialogue("PVP", "Mage Bank", "Multi Area", "Wests",
						"Easts", "Revenants");
				stage = 11;

				/**
				 * Dicing
				 */

			} else if (componentId == OPTION_4) {
				if (player.getSkills().getLevel(Skills.SLAYER) < 50) {
					player.getPackets()
							.sendGameMessage(
									"You need a slayer level of 50 to kill Dryax's minions.");
					player.getInterfaceManager().closeChatBoxInterface();
					player.getInterfaceManager().closeOverlay(true);
					//player.getControlerManager().forceStop();
					//player.getControlerManager().removeControlerWithoutCheck();
					return;
				}
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2733,
						9688, 0));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);

			} else if (componentId == OPTION_5) {
				if (player.prestigeTokens < 1) {
					player.getPackets().sendGameMessage("You must have prestiged to use this!");
					player.getInterfaceManager().closeChatBoxInterface();
					player.getInterfaceManager().closeOverlay(true);
					return;
				}
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(1382, 3816, 0));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
			}
			/**
			 * Godwars Dungeon Teleports
			 */

		} else if (stage == 7) {
			if (componentId == OPTION_1) {
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2832, 3858, 0));
				//player.sendMessage("The Calamity is currently under beta testing. Sorry.");
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
			}

				/**
				 * The RuneSpan
				 */

			 else if (componentId == OPTION_2) { // Warriors Guild
				warriorsGuild(2871, 3542, 0);
				player.getInterfaceManager().closeChatBoxInterface();

			} else if (componentId == OPTION_3) { // CLANWARS
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2992,
						9678, 0));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				//player.getControlerManager().forceStop();
				//player.getControlerManager().removeControlerWithoutCheck();

			} else if (componentId == OPTION_4) { // Dominion Tower
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3366,
						3083, 0));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				//player.getControlerManager().forceStop();
				//player.getControlerManager().removeControlerWithoutCheck();

			} else if (componentId == OPTION_5) {
				sendOptionsDialogue("Minigames", "Fight Kiln", "Fight Caves",
						"Heist", "Castle Wars", "Next");
				stage = 12;
			}

			/**
			 * Godwars Dungeon Teleports
			 */

		} else if (stage == 8) {
			if (componentId == OPTION_1) { // Nex
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2904,
						5203, 0));
				player.getInterfaceManager().closeChatBoxInterface();

			} else if (componentId == OPTION_2 && player.gwdkc >= 20) { // Bandos
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2870,
						5363, 2));
				player.gwdkc -= 20;
				player.getInterfaceManager().closeChatBoxInterface();

			} else if (componentId == OPTION_3 && player.gwdkc >= 20) { // Armadyl
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2838,
						5297, 2));
				player.gwdkc -= 20;
				player.getInterfaceManager().closeChatBoxInterface();

			} else if (componentId == OPTION_4 && player.gwdkc >= 20) { // Saradomin
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2898,
						5266, 0));
				player.gwdkc -= 20;
				player.getInterfaceManager().closeChatBoxInterface();

			} else if (componentId == OPTION_5 && player.gwdkc >= 20) { // Zamorak
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2925,
						5330, 2));
				player.gwdkc -= 20;
				player.getInterfaceManager().closeChatBoxInterface();
			} else {
				player.sendMessage("<col=ff000>You need a killcount of 20+ to use these teleports. ");
				player.sendMessage("Your current killcount is "+player.gwdkc+"");
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				end();
			}

			/**
			 * Slayer Teleports
			 */

		} else if (stage == 9) {
			if (componentId == OPTION_1) { // Slayer Tower
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3423,
						3544, 0));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				//player.getControlerManager().forceStop();
				//player.getControlerManager().removeControlerWithoutCheck();

			} else if (componentId == OPTION_2) { // Ganodermic Beast
				if (player.getSkills().getLevel(Skills.SLAYER) < 90) {
					player.getPackets()
							.sendGameMessage(
									"You need a slayer level of 90 to kill Ganodermic Beasts.");
					player.getInterfaceManager().closeChatBoxInterface();
					player.getInterfaceManager().closeOverlay(true);
					//player.getControlerManager().forceStop();
					//player.getControlerManager().removeControlerWithoutCheck();
					return;
				}
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(4654,
						5485, 0));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				//player.getControlerManager().forceStop();
				//player.getControlerManager().removeControlerWithoutCheck();

			} else if (componentId == OPTION_3) { // Glacor
				if (player.getSkills().getLevel(Skills.SLAYER) < 50) {
					player.getPackets().sendGameMessage(
							"You need a slayer level of 50 to kill Glacors.");
					player.getInterfaceManager().closeChatBoxInterface();
					player.getInterfaceManager().closeOverlay(true);
				//	player.getControlerManager().forceStop();
				//	player.getControlerManager().removeControlerWithoutCheck();
					return;
				}
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(4182,
						5731, 0));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				//player.getControlerManager().forceStop();
				//player.getControlerManager().removeControlerWithoutCheck();

			} else if (componentId == OPTION_4) { // Jadinko
				if (player.getSkills().getLevel(Skills.SLAYER) < 85) {
					player.getPackets().sendGameMessage(
							"You need a slayer level of 85 to kill Jadinkos.");
					player.getInterfaceManager().closeChatBoxInterface();
					player.getInterfaceManager().closeOverlay(true);
					//player.getControlerManager().forceStop();
					//player.getControlerManager().removeControlerWithoutCheck();
					return;
				}
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3013,
						9273, 0));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				//player.getControlerManager().forceStop();
				//player.getControlerManager().removeControlerWithoutCheck();

			} else if (componentId == OPTION_5) {
				sendOptionsDialogue("Slayer Areas", "Chaos Tunnels", "Ancient Caverns",
						"Dragon Dungeon", "Taverly Dungeon", "Next page");
				stage = 16;
			}
			
		} else if (stage == 16) {
			if (componentId == OPTION_1) { // CHAOS TUNNELS
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3170,
						5513, 0));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				//player.getControlerManager().forceStop();
				//player.getControlerManager().removeControlerWithoutCheck();

			} else if (componentId == OPTION_2) { // ANCIENT CAVERNS
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2512,
						3515, 0));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				//player.getControlerManager().removeControlerWithoutCheck();
			} else if (componentId == OPTION_3) { // Dragon dungeon
				if (player.getSkills().getLevel(Skills.SLAYER) < 80 && player.getSkills().getLevel(Skills.DUNGEONEERING) < 105) {
					player.getPackets()
							.sendGameMessage(
									"You need a slayer level of 80 & a dungeoneering level of 105.");
					player.getInterfaceManager().closeChatBoxInterface();
					player.getInterfaceManager().closeOverlay(true);
					//player.getControlerManager().forceStop();
					//player.getControlerManager().removeControlerWithoutCheck();
					return;
				}
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2722,
						9774, 0));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				//player.getControlerManager().removeControlerWithoutCheck();

			} else if (componentId == OPTION_4) { // Taverly Dung
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2884,
						9805, 0));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				//player.getControlerManager().forceStop();
				//player.getControlerManager().removeControlerWithoutCheck();
		} else if (componentId == OPTION_5) { // NOTHIN
				sendOptionsDialogue("Slayer Areas", "(<col=ff0000>WILD)Kuradals Brothers", "Rune & Adamant dragons.",
						"Strykewyrms", "Smoke devils", "Nevermind");
			stage = 20;
		
		}
		} else if (stage == 20) {
			if (componentId == OPTION_1) { //kuradal bros
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3135,
						3889, 0));
				player.getControlerManager().startControler("Wilderness");
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				//player.getControlerManager().removeControlerWithoutCheck();
			}else if (componentId == OPTION_2) { // RUNE/ADDY DRAGS
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2131,
						3856, 0));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
			}else if (componentId == OPTION_3) { // Strykewyrms
				sendOptionsDialogue("Slayer Areas", "Ice Strykewyrms", "Jungle Strykewyrms","Desert Strykewyrms", "Nevermind");
				stage = 40;
				//player.getControlerManager().forceStop();
				//player.getControlerManager().removeControlerWithoutCheck();
			} else if (componentId == OPTION_4) { // smokme devils
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3662, 5770, 0));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
			}else if (componentId == OPTION_5) { // nevermind
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
			end();
				}
			/**
			 * Minigames Page 2
			 */

		} else if (stage == 12) {
			if (componentId == OPTION_1) { // Fight Kiln
				Magic.sendNormalTeleportSpell(player, 0, 0, FightKiln.OUTSIDE);
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				//player.getControlerManager().forceStop();
				//player.getControlerManager().removeControlerWithoutCheck();

			} else if (componentId == OPTION_2) { // Fight Caves
				Magic.sendNormalTeleportSpell(player, 0, 0, FightCaves.OUTSIDE);
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				//player.getControlerManager().removeControlerWithoutCheck();

			} else if (componentId == OPTION_3) { // Heist
			if (player.getFamiliar() != null || player.getPet() != null || Summoning.hasPouch(player) || Pets.hasPet(player)) {
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				player.sendMessage("Sorry, but you cannot take pets into this minigame!");
				return;
			}
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				Magic.sendNormalTeleportSpell(player, 0, 0, Heist.LOBBY);
				end();

			} else if (componentId == OPTION_4) { // Castle Wars
				Magic.sendNormalTeleportSpell(player, 0, 0, CastleWars.LOBBY);
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				//player.getControlerManager().forceStop();
				//player.getControlerManager().removeControlerWithoutCheck();

			} else if (componentId == OPTION_5) {
				stage = 50;
				sendOptionsDialogue("Minigames", "Falconry", "Lava flow", "Player Wars", "Duel Arena", "Next Page");
			//	player.getInterfaceManager().closeChatBoxInterface();
			//	player.getInterfaceManager().closeOverlay(true);
				
			}
		} else if (stage == 40) {
			if (componentId == OPTION_1) { // ice
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2378,
						3891, 0));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
			} else if (componentId == OPTION_2) { // jungle
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2461,
						2898, 0));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				
			} else if (componentId == OPTION_3) { // desert
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3376,
						3158, 0));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				//player.getControlerManager().removeControlerWithoutCheck();
			} else {
				end();
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
			}

		} else if (stage == 50) {
			if (componentId == OPTION_1) { // falconry
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2372,
						3624, 0));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				//player.getControlerManager().forceStop();
				//player.getControlerManager().removeControlerWithoutCheck();

			} else if (componentId == OPTION_2) { // lava flow
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2184,
						5664, 0));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				
			} else if (componentId == OPTION_3) { // lava flow
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3222, 3425, 0));
				player.sendMessage("Speak to the dwarf next to you to start the minigame!");

				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
			} else if (componentId == OPTION_4) { // duel arena
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3365, 3266, 0));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
			} else if (componentId == OPTION_5) {
				stage = 51;
				sendOptionsDialogue("Minigames", "Temple of Light", "Trial of the Gods", "Slayer Survival", "Nothing");
			}
			} else if (stage == 51) {
				if (componentId == OPTION_1) {
				if (player.templeoflightcharges < 1) {
					player.sendMessage(Colors.cyan + "You don't have any Temple of Light teleport charges!");
					player.getInterfaceManager().closeChatBoxInterface();
					player.getInterfaceManager().closeOverlay(true);
				} else {
					Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2438, 3315, 0));
					player.getInterfaceManager().closeChatBoxInterface();
					player.getInterfaceManager().closeOverlay(true);
				}
				} else if (componentId == OPTION_2) {
					if (player.getFamiliar() != null || player.getPet() != null || Summoning.hasPouch(player) || Pets.hasPet(player)) {
						player.sendMessage("Pets aren't allowed here!");
						player.getInterfaceManager().closeChatBoxInterface();
						player.getInterfaceManager().closeOverlay(true);
						return;
					}
					Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3244, 3197, 0));
				} else if (componentId == OPTION_3) {
					stage = 52;
					sendOptionsDialogue("Minigames", "Open Shop", "Start Slayer Survival", "Nothing");
				}
				} else if (stage == 52) {
					if (componentId == OPTION_1) {
				ShopsHandler.openShop(player, 127);
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
					} else if (componentId == OPTION_2) {
						Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3430, 3522, 0));
						player.getInterfaceManager().closeChatBoxInterface();
						player.getInterfaceManager().closeOverlay(true);
				
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
				//player.getControlerManager().forceStop();
				//player.getControlerManager().removeControlerWithoutCheck();
				player.getControlerManager().startControler("Wilderness");

			} else if (componentId == OPTION_2) { // Multi
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3240,
						3611, 0));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				//player.getControlerManager().forceStop();
				//player.getControlerManager().removeControlerWithoutCheck();
				player.getControlerManager().startControler("Wilderness");

			} else if (componentId == OPTION_3) { // Wests
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2984,
						3596, 0));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				//player.getControlerManager().forceStop();
				//player.getControlerManager().removeControlerWithoutCheck();
				player.getControlerManager().startControler("Wilderness");

			} else if (componentId == OPTION_4) { // Easts
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3360,
						3658, 0));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				//player.getControlerManager().forceStop();
				//player.getControlerManager().removeControlerWithoutCheck();
				player.getControlerManager().startControler("Wilderness");

			} else if (componentId == OPTION_5) { // revs
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3081,
						10057, 0));
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				player.getControlerManager().startControler("Wilderness");
				//player.getControlerManager().forceStop();
				//player.getControlerManager().removeControlerWithoutCheck();

			} else if (componentId == OPTION_5) { // Cancel
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				//player.getControlerManager().forceStop();
				//player.getControlerManager().removeControlerWithoutCheck();
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
