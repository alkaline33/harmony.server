package com.rs.net.decoders.handlers;

import java.util.Calendar;
import java.util.HashMap;
import java.util.TimerTask;

import com.rs.Settings;
import com.rs.cache.loaders.ItemDefinitions;
import com.rs.content.skills.SummoningScroll;
import com.rs.cores.CoresManager;
import com.rs.game.Animation;
import com.rs.game.ForceTalk;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.minigames.Crucible;
import com.rs.game.minigames.PuroPuro;
import com.rs.game.minigames.duel.DuelControler;
import com.rs.game.npc.familiar.Familiar;
import com.rs.game.npc.familiar.Familiar.SpecialAttack;
import com.rs.game.player.EmotesManager;
import com.rs.game.player.Equipment;
import com.rs.game.player.Inventory;
import com.rs.game.player.Player;
import com.rs.game.player.PlayerOwnedShop;
import com.rs.game.player.QuestManager.Quests;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.FightPitsViewingOrb;
import com.rs.game.player.actions.HomeTeleport;
import com.rs.game.player.actions.Rest;
import com.rs.game.player.actions.Smithing.ForgingInterface;
import com.rs.game.player.actions.Summoning;
import com.rs.game.player.actions.crafting.JewellerySmithing;
import com.rs.game.player.actions.magic.EnchantBolt;
import com.rs.game.player.actions.magic.EnchantBolt.Bolt;
import com.rs.game.player.content.CosmeticsHandler;
import com.rs.game.player.content.ItemConstants;
import com.rs.game.player.content.Magic;
import com.rs.game.player.content.PlayerLook;
import com.rs.game.player.content.Runecrafting;
import com.rs.game.player.content.Shop;
import com.rs.game.player.content.SkillCapeCustomizer;
import com.rs.game.player.content.SkillsDialogue;
import com.rs.game.player.content.ViewNPCDrops;
import com.rs.game.player.content.clans.ClansManager;
import com.rs.game.player.content.construction.House;
import com.rs.game.player.content.construction.House.RoomReference;
import com.rs.game.player.content.construction.HouseConstants.Builds;
import com.rs.game.player.content.grandExchange.GrandExchangeLoader;
import com.rs.game.player.content.grandExchange.GrandExchangeSystem;
import com.rs.game.player.content.interfaces.NpcDropViewer;
import com.rs.game.player.content.interfaces.ViewProfile;
import com.rs.game.player.content.interfaces.teleports.TeleportsInterface;
import com.rs.game.player.content.interfaces.titles.TitlesManager;
import com.rs.game.player.controlers.HouseControler;
import com.rs.game.player.controlers.Wilderness;
import com.rs.game.player.dialogues.impl.LevelUp;
import com.rs.game.player.dialogues.impl.Transportation;
import com.rs.game.player.interfaces.AchievementDiaryInter;
import com.rs.game.player.interfaces.BossTeleportInterface;
import com.rs.game.player.interfaces.DailyMoneyMakersInterface;
import com.rs.game.player.interfaces.DropLogInterface;
import com.rs.game.player.interfaces.LootBoxInterface;
import com.rs.game.player.interfaces.MasterSlayerTaskInterface;
import com.rs.game.player.interfaces.PerkInterface;
import com.rs.game.player.interfaces.PetClaimInterface;
import com.rs.game.player.interfaces.ResearchTableInterfaceMain;
import com.rs.game.player.interfaces.RewardsCustomInterface;
import com.rs.game.player.interfaces.RottenPotatoInterface;
import com.rs.game.player.interfaces.ShopsInterface;
import com.rs.game.player.interfaces.ShopsInterface2;
import com.rs.game.player.interfaces.ShopsInterface3;
import com.rs.game.player.interfaces.SkillTeleportInterface;
import com.rs.game.player.interfaces.SlayerTeleportInterface;
import com.rs.game.player.interfaces.StaffDeskInterface;
import com.rs.game.player.interfaces.StarterGuideInterface;
import com.rs.game.player.interfaces.StarterInterface;
import com.rs.game.player.interfaces.TitlesInterface;
import com.rs.game.player.interfaces.UpdateInterface;
import com.rs.game.player.interfaces.WorldEventInformationInterface;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.io.InputStream;
import com.rs.net.decoders.WorldPacketsDecoder;
import com.rs.utils.Colors;
import com.rs.utils.ItemExamines;
import com.rs.utils.Logger;
import com.rs.utils.ShopsHandler;
import com.rs.utils.Utils;

public class ButtonHandler {

	Item item;

	public static String sendLoanItemExamine(int slotId2) {
		return ItemExamines.getExamine(slotId2);
	}

	public static void handleButtons(final Player player, InputStream stream, int packetId) {
		int interfaceHash = stream.readIntV2();
		int interfaceId = interfaceHash >> 16;
		if (Utils.getInterfaceDefinitionsSize() <= interfaceId) {
			// hack, or server error or client error
			// player.getSession().getChannel().close();
			return;
		}
		if (player.isDead() || !player.getInterfaceManager().containsInterface(interfaceId)) {
			return;
		}
		final int componentId = interfaceHash - (interfaceId << 16);
		if (componentId != 65535 && Utils.getInterfaceDefinitionsComponentsSize(interfaceId) <= componentId) {
			// hack, or server error or client error
			// player.getSession().getChannel().close();
			return;
		}
		final int slotId2 = stream.readUnsignedShort128();
		final int slotId = stream.readUnsignedShortLE128();
		if (!player.getControlerManager().processButtonClick(interfaceId, componentId, slotId, packetId)) {
			return;
		}
		if (player.getInterfaceManager().getRSInterface() != null && player.getInterfaceManager().getRSInterface().getId() == interfaceId) {
			final int itemIdd = stream.readInt();
			player.getInterfaceManager().getRSInterface().onClick(componentId, packetId, itemIdd, slotId);
			return;
		}
		if(interfaceId == 3061 || interfaceId == 3060 || interfaceId == 3062) {
			TeleportsInterface.handleButtons(player, componentId,slotId);
			return;
		}
		if (interfaceId == 560) {
			StarterGuideInterface.handleButtons(player, componentId);
			return;
		}
		if (interfaceId == 3026) {
			StarterInterface.HandleButtons(player, componentId);
			return;
		}
		if (interfaceId == 3221) {
			NpcDropViewer.handelButtons(player, componentId);
			return;
		}
		if (interfaceId == 3090) {
			ViewProfile.handleButtons(player, interfaceId, componentId);
		}
		if (interfaceId == 3034) {
			TitlesManager.handleButtons(player, componentId, packetId);
			return;
		}
		if (interfaceId == 3025) {
			LootBoxInterface.HandleButtons(player, componentId);
			return;
		}
		if (interfaceId == 3008) {
			UpdateInterface.HandleButtons(player, componentId);
			return;
		}
		if (interfaceId == 3015) {
			DropLogInterface.HandleButtons(player, componentId);
			return;
		}
		if (interfaceId == 3020) {
			TitlesInterface.HandleButtons(player, componentId);
			return;
		}
		if (interfaceId == 3011) {
			RottenPotatoInterface.HandleButtons(player, componentId);
			return;
		}
		if (interfaceId == 3017) {
			if (player.petclaimpage == 2) {
			PetClaimInterface.HandleButtonsPage2(player, componentId);
			return;
			} else if (player.petclaimpage == 3) {
				PetClaimInterface.HandleButtonsPage3(player, componentId);
				return;
				} else {
				PetClaimInterface.HandleButtonsPage1(player, componentId);
				return;
			}
		}
		if (interfaceId == 3016) {
			StaffDeskInterface.HandleButtons(player, componentId);
			return;
		}
		if (interfaceId == 540) {
			if (componentId == 69) {
				PuroPuro.confirmPuroSelection(player);
			} else if (componentId == 71) {
				ShopsHandler.openShop(player, 32);
			} else {
				PuroPuro.handlePuroInterface(player, componentId);
			}
			return;
		}
		if (interfaceId == 190 && componentId == 15) {
			if (slotId == 68) {
				if (player.spokeToWarrior == false && player.spokeToShamus == false) {
					player.getInterfaceManager().sendInterface(275);
					player.getPackets().sendIComponentText(275, 1, "Lost-City Quest");
					player.getPackets().sendIComponentText(275, 10, "");
					player.getPackets().sendIComponentText(275, 11, "Speak to the Warrior north west of the entrance to Lumbridge Swamp");
					player.getPackets().sendIComponentText(275, 12, "<u>Requirements</u>");
					player.getPackets().sendIComponentText(275, 13, "<col=ffff00>31 Crafting, 36 Woodcutting</col>");
					player.getPackets().sendIComponentText(275, 14, "-=-Tips-=-");
					player.getPackets().sendIComponentText(275, 15,
							"Use the 'Quests & Minigames' teleport at home to start the quest.");
					player.getPackets().sendIComponentText(275, 16,
							"The lodestone works, remember to take full use of it.");
					player.getPackets().sendIComponentText(275, 17,
							"You will need the skills required to complete the quest");
					player.getPackets().sendIComponentText(275, 18,
							"The Monk Of Entrana removes everything in your inventory.");
					player.getPackets().sendIComponentText(275, 19, "");
					player.getPackets().sendIComponentText(275, 20, "");
				} else if (player.spokeToWarrior == true && player.spokeToShamus == false) {
					player.getInterfaceManager().sendInterface(275);
					player.getPackets().sendIComponentText(275, 1, "Lost-City Quest");
					player.getPackets().sendIComponentText(275, 10, "");
					player.getPackets().sendIComponentText(275, 11,
							"Shamus appears to be in one of the trees around this location.");
					player.getPackets().sendIComponentText(275, 12,
							"The Warrior told me the tree displays 'Chop Tree'");
					player.getPackets().sendIComponentText(275, 13, "");
					player.getPackets().sendIComponentText(275, 14, "-=-Tips-=-");
					player.getPackets().sendIComponentText(275, 15,
							"Use the 'Quests & Minigames' teleport at home to start the quest.");
					player.getPackets().sendIComponentText(275, 16,
							"The lodestone works, remember to take full use of it.");
					player.getPackets().sendIComponentText(275, 17,
							"You will need the skills required to complete the quest");
					player.getPackets().sendIComponentText(275, 18,
							"The Monk Of Entrana removes everything in your inventory.");
					player.getPackets().sendIComponentText(275, 19, "");
					player.getPackets().sendIComponentText(275, 20, "");
				} else if (player.spokeToWarrior == true && player.spokeToShamus == true
						&& player.spokeToMonk != true) {
					player.getInterfaceManager().sendInterface(275);
					player.getPackets().sendIComponentText(275, 1, "Lost-City Quest");
					player.getPackets().sendIComponentText(275, 10, "");
					player.getPackets().sendIComponentText(275, 11,
							"I should go find the Monk of Entrana, Who is located at Port Sarim.");
					player.getPackets().sendIComponentText(275, 12, "");
					player.getPackets().sendIComponentText(275, 13, "");
					player.getPackets().sendIComponentText(275, 14, "-=-Tips-=-");
					player.getPackets().sendIComponentText(275, 15,
							"Use the 'Quest Tab' teleports to your advantage.");
					player.getPackets().sendIComponentText(275, 16,
							"The lodestone works, remember to take full use of it.");
					player.getPackets().sendIComponentText(275, 17,
							"You will need the skills required to complete the quest");
					player.getPackets().sendIComponentText(275, 18,
							"The Monk Of Entrana removes everything in your inventory.");
					player.getPackets().sendIComponentText(275, 19, "");
					player.getPackets().sendIComponentText(275, 20, "");
				} else if (player.spokeToWarrior == true && player.spokeToShamus == true
						&& player.spokeToMonk == true) {
					player.getInterfaceManager().sendInterface(275);
					player.getPackets().sendIComponentText(275, 1, "Lost-City Quest");
					player.getPackets().sendIComponentText(275, 10,
							"The other side of Entrana is a ladder which leads to a cave");
					player.getPackets().sendIComponentText(275, 11,
							"I should go down the ladder and search for the dramen tree.");
					player.getPackets().sendIComponentText(275, 12,
							"In order to chop the dramen tree I must have a axe.");
					player.getPackets().sendIComponentText(275, 13, "The zombies must drop a axe of some sort.");
					player.getPackets().sendIComponentText(275, 14, "-=-Tips-=-");
					player.getPackets().sendIComponentText(275, 15,
							"Use the 'Quest Tab' teleports to your advantage.");
					player.getPackets().sendIComponentText(275, 16,
							"The lodestone works, remember to take full use of it.");
					player.getPackets().sendIComponentText(275, 17,
							"You will need the skills required to complete the quest");
					player.getPackets().sendIComponentText(275, 18,
							"The Monk Of Entrana removes everything in your inventory.");
					player.getPackets().sendIComponentText(275, 19, "");
					player.getPackets().sendIComponentText(275, 20, "");
				} else if (player.spokeToWarrior == true && player.spokeToShamus == true && player.spokeToMonk == true
						&& player.lostCity >= 1) {
					player.getInterfaceManager().sendInterface(275);
					player.getPackets().sendIComponentText(275, 1, "Lost-City Quest");
					player.getPackets().sendIComponentText(275, 10, "");
					player.getPackets().sendIComponentText(275, 11, "");
					player.getPackets().sendIComponentText(275, 12, "Congratulations Quest Complete!");
					player.getPackets().sendIComponentText(275, 13, "");
					player.getPackets().sendIComponentText(275, 14, "");
					player.getPackets().sendIComponentText(275, 15, "");
					player.getPackets().sendIComponentText(275, 16, "");
					player.getPackets().sendIComponentText(275, 17, "");
					player.getPackets().sendIComponentText(275, 18, "");
					player.getPackets().sendIComponentText(275, 19, "");
					player.getPackets().sendIComponentText(275, 20, "");
				}
			}
		}
		if (interfaceId == 3018) {
			ResearchTableInterfaceMain.HandleButtons(player, componentId, slotId);
			return;
		}
		if (interfaceId == 3071) {
			PerkInterface.HandleButtons(player, componentId, slotId);
			return;
		}
		if (interfaceId == 3002) {
			BossTeleportInterface.HandleButtons(player, componentId, slotId);
			return;
		}
		if (interfaceId == 3027) {
			AchievementDiaryInter.HandleButtons(player, componentId, slotId);
			return;
		}
		if (interfaceId == 3006) {
			if (player.slayertelecomponent > 0) {
				SlayerTeleportInterface.HandleButtons(player, componentId, slotId);
			return;
			} else if (player.dailymoneymakercomponent > 0) {
				DailyMoneyMakersInterface.HandleButtons(player, componentId, slotId);
				return;
			}
			return;
		}
		if (interfaceId == 3014) {
			MasterSlayerTaskInterface.handleButtons(player, componentId);
			return;
		}
		if (interfaceId == 583 && player.skilltelingcomponent == true) {
			SkillTeleportInterface.handleButtons(player, componentId);
			return;
		}
		if (interfaceId == 72) {
			if (player.getTemporaryAttributtes().containsKey("shopsinterface")) {
				ShopsInterface.handleButtons(player, componentId);
				return;
			} else if (player.getTemporaryAttributtes().containsKey("shopsinterface1")) {
				ShopsInterface2.handleButtons(player, componentId);
				return;
			} else if (player.getTemporaryAttributtes().containsKey("shopsinterface2")) {
				ShopsInterface3.handleButtons(player, componentId);
				return;
			}
		}
		if (interfaceId == 3001) {
			RewardsCustomInterface.handleButtons(player, componentId);
		}
		if (interfaceId == 675) {
			// JewellerySmithing.handleButtonClick(player, componentId, packetId
			// == 14 ? 1 : packetId == 67 ? 5 : 10);
			JewellerySmithing.handleButtonClick(player, componentId,
					packetId == 14 ? 1 : packetId == 67 ? 5 : packetId == 5 ? 10 : packetId == 55 ? 14 : 0);
			return;
		}
		if (interfaceId == 1083) {
			switch (componentId) {
			case 387:
				player.getInterfaceManager().sendInterface(1083);
				player.getPackets().sendIComponentText(1083, 136, "Crude Chair");
				player.getPackets().sendIComponentText(1083, 137, "Lvl 1");
				player.getPackets().sendIComponentText(1083, 132, "Wooden Chair");
				player.getPackets().sendIComponentText(1083, 133, "Lvl 8");
				player.getPackets().sendIComponentText(1083, 128, "Oak Chair");
				player.getPackets().sendIComponentText(1083, 129, "Lvl 19");
				player.getPackets().sendIComponentText(1083, 124, "Teak Armchair");
				player.getPackets().sendIComponentText(1083, 125, "Lvl 35");
				player.getPackets().sendIComponentText(1083, 85, "Click on the option you would like to make.");
				player.getPackets().sendIComponentText(1083, 87,
						"Then click the button 'Learn' to create the object you selected.");
				player.getPackets().sendIComponentText(1083, 89, "");
				player.getPackets().sendIComponentText(1083, 120, "");
				player.getPackets().sendIComponentText(1083, 116, "");
				player.getPackets().sendIComponentText(1083, 112, "");
				player.getPackets().sendIComponentText(1083, 108, "");
				player.getPackets().sendIComponentText(1083, 434, "");
				player.getPackets().sendIComponentText(1083, 445, "");
				player.getPackets().sendIComponentText(1083, 456, "");
			case 74:
			case 69:
			case 64:
			}
			// if (interfaceId == 402 && componentId == 93) {
			// player.getRoomConstruction().buildRoom(Room.PARLOUR,
			// componentId);
			// }
		}

		if (interfaceId == 190 && componentId == 15) {
			if (slotId == 68) {
				player.getInterfaceManager().sendInterface(275);
				player.getPackets().sendIComponentText(275, 1, "Lost-City Quest");
				player.getPackets().sendIComponentText(275, 10, "");
				player.getPackets().sendIComponentText(275, 11, "Speak to the Warrior south east of the entrance to Lumbridge Swamp");
				player.getPackets().sendIComponentText(275, 12, "<u>Requirements</u>");
				player.getPackets().sendIComponentText(275, 13, "<col=ffff00>31 Crafting, 36 Woodcutting</col>");
				player.getPackets().sendIComponentText(275, 14, "-=-Tips-=-");
				player.getPackets().sendIComponentText(275, 15,
						"Use the 'Quest Tab' teleports to your advantage.");
				player.getPackets().sendIComponentText(275, 16,
						"The lodestone works, remember to take full use of it.");
				player.getPackets().sendIComponentText(275, 17,
						"You will need the skills required to complete the quest");
				player.getPackets().sendIComponentText(275, 18,
						"The Monk Of Entrana removes everything in your inventory.");
				player.getPackets().sendIComponentText(275, 19, "");
				player.getPackets().sendIComponentText(275, 20, "");
			}
		}

		// Clan Stuff

		// squeal
		if (GrandExchangeSystem.get().handleButtonEvent(player, interfaceId, componentId, packetId, slotId, slotId2)) {
			return;
		}
		if (!player.getControlerManager().processButtonClick(interfaceId, componentId, slotId, packetId)) {
			return;
		}
		if (interfaceId == 109) {
			if (componentId == 58) {
				if (packetId == 32) {
					player.getPackets().sendGameMessage(sendLoanItemExamine(slotId2));
					return;
				}
				// player.getTrade().handleCollectButton(player);
			}
		}
		if (interfaceId == 1127) {
			if (componentId == 25) {
				player.donator = true;
				player.getInventory().deleteItem(29492, 1);
				World.sendWorldMessage("<col=00CC66>" + player.getDisplayName()
						+ " has used a bond and been rewarded with a donator status.", false);
				player.closeInterfaces();
				return;
			} else if (componentId == 38) {
				player.spins += 20;
				player.getInventory().deleteItem(29492, 1);
				World.sendWorldMessage("<col=00CC66>" + player.getDisplayName()
						+ " has used a bond and been rewarded with 20 Squeal of Fortune spins.", false);
				player.closeInterfaces();
				return;
			}
		}
		if (interfaceId == 411) {

			if (componentId == 13) {
				if (player.getInventory().contains(29718)) {
					player.getInventory().deleteItem(29718, 1);
					player.getInventory().addItem(29717, 1);
					player.sendMessage("Your donator cape was successfully downgraded.");
					return;
				} else if (player.getInventory().contains(29719)) {
					player.getInventory().deleteItem(29719, 1);
					player.getInventory().addItem(29717, 1);
					player.sendMessage("Your donator cape was successfully downgraded.");
					return;
				} else if (player.getInventory().contains(29720)) {
					player.getInventory().deleteItem(29720, 1);
					player.getInventory().addItem(29717, 1);
					player.sendMessage("Your donator cape was successfully downgraded.");
					return;

				} else {
					player.sendMessage("You don't have the required capes to do this.");
					return;
				}
			}
			if (componentId == 22) {
				if (!player.isSuperDonator()) {
					player.sendMessage("Sorry, you need super donator status to do this.");
					return;
				}
				if (player.getInventory().contains(29717) && player.getInventory().containsItem(995, 10000000)) {
					player.getInventory().deleteItem(29717, 1);
					player.getInventory().deleteItem(995, 10000000);
					Settings.GpSyncAmount += 10000000;
					player.getInventory().addItem(29718, 1);
					player.sendMessage("Your donator cape was successfully upgraded.");
					return;
				} else if (player.getInventory().contains(29719) && player.getInventory().containsItem(995, 10000000)) {
					player.getInventory().deleteItem(29719, 1);
					player.getInventory().deleteItem(995, 10000000);
					Settings.GpSyncAmount += 10000000;
					player.getInventory().addItem(29718, 1);
					player.sendMessage("Your donator cape was successfully downgraded.");
					return;
				} else if (player.getInventory().contains(29720) && player.getInventory().containsItem(995, 10000000)) {
					player.getInventory().deleteItem(29720, 1);
					player.getInventory().deleteItem(995, 10000000);
					Settings.GpSyncAmount += 10000000;
					player.getInventory().addItem(29718, 1);
					player.sendMessage("Your donator cape was successfully downgraded.");
					return;

				} else {
					player.sendMessage("You need the correct cape and 10M coins to use this option.");
					return;
				}
			}
			if (componentId == 31) {
				if (!player.isExtremeDonator()) {
					player.sendMessage("Sorry, you need extreme donator status to do this.");
					return;
				}
				if (player.getInventory().contains(29717) && player.getInventory().containsItem(995, 10000000)) {
					player.getInventory().deleteItem(29717, 1);
					player.getInventory().deleteItem(995, 10000000);
					Settings.GpSyncAmount += 10000000;
					player.getInventory().addItem(29719, 1);
					player.sendMessage("Your donator cape was successfully upgraded.");
					return;
				} else if (player.getInventory().contains(29718) && player.getInventory().containsItem(995, 10000000)) {
					player.getInventory().deleteItem(29718, 1);
					player.getInventory().deleteItem(995, 10000000);
					Settings.GpSyncAmount += 10000000;
					player.getInventory().addItem(29719, 1);
					player.sendMessage("Your donator cape was successfully upgraded.");
					return;
				} else if (player.getInventory().contains(29720) && player.getInventory().containsItem(995, 10000000)) {
					player.getInventory().deleteItem(29720, 1);
					player.getInventory().deleteItem(995, 10000000);
					Settings.GpSyncAmount += 10000000;
					player.getInventory().addItem(29719, 1);
					player.sendMessage("Your donator cape was successfully downgraded.");
					return;

				} else {
					player.sendMessage("You need the correct cape and 10M coins to use this option.");
					return;
				}
			}
			if (componentId == 40) {
				if (!player.isLegendaryDonator()) {
					player.sendMessage("Sorry, you need legendary donator status to do this.");
					return;
				}
				if (player.getInventory().contains(29717) && player.getInventory().containsItem(995, 40000000)) {
					player.getInventory().deleteItem(29717, 1);
					player.getInventory().deleteItem(995, 40000000);
					Settings.GpSyncAmount += 40000000;
					player.getInventory().addItem(29720, 1);
					player.sendMessage("Your donator cape was successfully upgraded.");
					return;
				} else if (player.getInventory().contains(29718) && player.getInventory().containsItem(995, 40000000)) {
					player.getInventory().deleteItem(29718, 1);
					player.getInventory().deleteItem(995, 40000000);
					Settings.GpSyncAmount += 40000000;
					player.getInventory().addItem(29720, 1);
					player.sendMessage("Your donator cape was successfully upgraded.");
					return;
				} else if (player.getInventory().contains(29719) && player.getInventory().containsItem(995, 40000000)) {
					player.getInventory().deleteItem(29719, 1);
					player.getInventory().deleteItem(995, 40000000);
					Settings.GpSyncAmount += 40000000;
					player.getInventory().addItem(29720, 1);
					player.sendMessage("Your donator cape was successfully upgraded.");
					return;

				} else {
					player.sendMessage("You need the correct cape and 40M coins to use this option.");
					return;
				}
			}
		}

		if (interfaceId == 1253 || interfaceId == 1252 || interfaceId == 1139) {
			if (interfaceId == 1253 || interfaceId == 1252 || interfaceId == 1139) {
				player.getSquealOfFortune().processClick(packetId, interfaceId, componentId, slotId, slotId2);
			}
		}

		if (interfaceId == 548 || interfaceId == 746) {
			if (interfaceId == 548 && componentId == 148 || interfaceId == 746 && componentId == 199) {
				if (player.getInterfaceManager().containsScreenInter()
						|| player.getInterfaceManager().containsInventoryInter()) {
					// TODO cant open sound
					player.getPackets()
							.sendGameMessage("Please finish what you're doing before opening the world map.");
					return;
				}
				// world map open
				player.getPackets().sendWindowsPane(755, 0);
				int posHash = player.getX() << 14 | player.getY();
				player.getPackets().sendGlobalConfig(622, posHash); // map open
				// center
				// pos
				player.getPackets().sendGlobalConfig(674, posHash); // player
				// position
			} else if (interfaceId == 548 && componentId == 17 || interfaceId == 746 && componentId == 54) {
				if (packetId == WorldPacketsDecoder.ACTION_BUTTON1_PACKET) {
					player.getSkills().switchXPDisplay();
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET) {
					player.getSkills().switchXPPopup();
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON3_PACKET) {
					player.getSkills().setupXPCounter();
				}
			} else if (interfaceId == 746 && componentId == 207 || interfaceId == 548 && componentId == 159) {
				if (packetId == WorldPacketsDecoder.ACTION_BUTTON1_PACKET) {
					if (player.getInterfaceManager().containsScreenInter()) {
						player.getPackets()
								.sendGameMessage("Please finish what you're doing before opening the price checker.");
						return;
					}
					player.getMoneyPouch().switchPouch();
					player.getPackets().sendRunScript(5560, player.getMoneyPouch().getCoinAmount());
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET && !player.isCanPvp()) {
					if (player.hasBankPin && !player.hasEnteredPin) {
						player.getTemporaryAttributtes().put("bank_pin1", Boolean.TRUE);
						player.getPackets().sendRunScript(108, new Object[] { "Enter Your Bank Pin Please" });
					}
					else if (!player.getInventory().hasFreeSlots() && !player.getInventory().contains(995)) {
							player.sendMessage(Colors.red+"You must have inventory space to do this!");

					} else {
						player.getTemporaryAttributtes().put("remove_X_money", 995);
						player.getTemporaryAttributtes().put("remove_money", Boolean.TRUE);
						player.getPackets().sendRunScript(108, new Object[] { "Your money pouch contains "
								+ Utils.format(player.coinamount) + " coins." + " How many would you like to withdraw?" });
					}
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON3_PACKET) {
					player.getMoneyPouch().sendExamine();
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON4_PACKET) {
					if (player.getInterfaceManager().containsScreenInter()) {
						player.getPackets()
								.sendGameMessage("Please finish what you're doing before opening the price checker.");
						return;
					}
					player.stopAll();
					player.getPriceCheckManager().openPriceCheck();
				}
			}
		} else if (interfaceId == 34) {// notes interface
			/*
			 * switch(componentId) { case 35: case 37: case 39: case 41: Note
			 * current = (Note) player.getTemporaryAttributtes().get("curNote");
			 * current.setColour(componentId - (34 + (componentId == 35 ? 0 :
			 * componentId + 1))); player.getNotes().refresh();
			 * player.getPackets().sendHideIComponent(34, 16, true); break; case
			 * 3: player.getPackets().sendRunScript(109, new Object[] {
			 * "Please enter the note text." });
			 * player.getTemporaryAttributtes().put("entering_note",
			 * Boolean.TRUE); break; case 9: switch(packetId) { case
			 * WorldPacketsDecoder.ACTION_BUTTON1_PACKET: Note note =
			 * player.getNotes().getNotes().get(slotId); if
			 * (player.getTemporaryAttributtes().get("curNote") == note) {
			 * player.getTemporaryAttributtes().remove("curNote");
			 * player.getPackets().sendConfig(1439, -1); return; } else {
			 * player.getTemporaryAttributtes().put("curNote", note);
			 * player.getPackets().sendConfig(1439, slotId); } break; case
			 * WorldPacketsDecoder.ACTION_BUTTON2_PACKET:
			 * player.getTemporaryAttributtes().put("curNote",
			 * player.getNotes().getNotes().get(slotId));
			 * player.getPackets().sendRunScript(109, new Object[] {
			 * "Please edit the note text." });
			 * player.getTemporaryAttributtes().put("editing_note",
			 * Boolean.TRUE); player.getPackets().sendConfig(1439, slotId);
			 * break; case WorldPacketsDecoder.ACTION_BUTTON3_PACKET:
			 * player.getTemporaryAttributtes().put("curNote",
			 * player.getNotes().getNotes().get(slotId));
			 * player.getPackets().sendHideIComponent(34, 16, false);
			 * player.getPackets().sendConfig(1439, slotId); break; } break;
			 * case 8: Note note = (Note)
			 * player.getTemporaryAttributtes().get("curNote");
			 * player.getNotes().remove(note); break; }
			 */

		} else if (interfaceId == 182) {
			if (player.getInterfaceManager().containsInventoryInter()) {
				return;
			}
			if (componentId == 6 || componentId == 13) {
				if (!player.hasFinished()) {
					player.logout(componentId == 6);
				}
			}
		} else if (interfaceId == 1165) {
			// if (componentId == 22)
			// Summoning.closeDreadnipInterface(player);
		} else if (interfaceId == 880) {
			if (componentId >= 7 && componentId <= 19) {
				Familiar.setLeftclickOption(player, (componentId - 7) / 2);
			} else if (componentId == 21) {
				Familiar.confirmLeftOption(player);
			} else if (componentId == 25) {
				Familiar.setLeftclickOption(player, 7);
			}
		} else if (interfaceId == 1089) {
			if (componentId == 30) {
				player.getTemporaryAttributtes().put("clanflagselection", slotId);
			} else if (componentId == 26) {
				Integer flag = (Integer) player.getTemporaryAttributtes().remove("clanflagselection");
				player.stopAll();
				if (flag != null) {
					ClansManager.setClanFlagInterface(player, flag);
				}
			}
		} else if (interfaceId == 1096) {
			if (componentId == 41) {
				ClansManager.viewClammateDetails(player, slotId);
			} else if (componentId == 94) {
				ClansManager.switchGuestsInChatCanEnterInterface(player);
			} else if (componentId == 95) {
				ClansManager.switchGuestsInChatCanTalkInterface(player);
			} else if (componentId == 96) {
				ClansManager.switchRecruitingInterface(player);
			} else if (componentId == 97) {
				ClansManager.switchClanTimeInterface(player);
			} else if (componentId == 124) {
				ClansManager.openClanMottifInterface(player);
			} else if (componentId == 131) {
				ClansManager.openClanMottoInterface(player);
			} else if (componentId == 240) {
				ClansManager.setTimeZoneInterface(player, -720 + slotId * 10);
			} else if (componentId == 262) {
				player.getTemporaryAttributtes().put("editclanmatejob", slotId);
			} else if (componentId == 276) {
				player.getTemporaryAttributtes().put("editclanmaterank", slotId);
			} else if (componentId == 309) {
				ClansManager.kickClanmate(player);
			} else if (componentId == 318) {
				ClansManager.saveClanmateDetails(player);
			} else if (componentId == 290) {
				ClansManager.setWorldIdInterface(player, slotId);
			} else if (componentId == 297) {
				ClansManager.openForumThreadInterface(player);
			} else if (componentId == 346) {
				ClansManager.openNationalFlagInterface(player);
			} else if (componentId == 113) {
				ClansManager.showClanSettingsClanMates(player);
			} else if (componentId == 120) {
				ClansManager.showClanSettingsSettings(player);
			} else if (componentId == 386) {
				ClansManager.showClanSettingsPermissions(player);
			} else if (componentId >= 395 && componentId <= 475) {
				int selectedRank = (componentId - 395) / 8;
				if (selectedRank == 10) {
					selectedRank = 125;
				} else if (selectedRank > 5) {
					selectedRank = 100 + selectedRank - 6;
				}
				ClansManager.selectPermissionRank(player, selectedRank);
			} else if (componentId == 489) {
				ClansManager.selectPermissionTab(player, 1);
			} else if (componentId == 498) {
				ClansManager.selectPermissionTab(player, 2);
			} else if (componentId == 506) {
				ClansManager.selectPermissionTab(player, 3);
			} else if (componentId == 514) {
				ClansManager.selectPermissionTab(player, 4);
			} else if (componentId == 522) {
				ClansManager.selectPermissionTab(player, 5);
			}
		} else if (interfaceId == 1105) {
			if (componentId == 63 || componentId == 66) {
				ClansManager.setClanMottifTextureInterface(player, componentId == 66, slotId);
			} else if (componentId == 35) {
				ClansManager.openSetMottifColor(player, 0);
			} else if (componentId == 80) {
				ClansManager.openSetMottifColor(player, 1);
			} else if (componentId == 92) {
				ClansManager.openSetMottifColor(player, 2);
			} else if (componentId == 104) {
				ClansManager.openSetMottifColor(player, 3);
			} else if (componentId == 120) {
				player.stopAll();
			} else if (componentId == 189) {
				player.getPackets().sendHideIComponent(1105, 35, false);
				player.getPackets().sendHideIComponent(1105, 36, false);
				player.getPackets().sendHideIComponent(1105, 37, false);
				player.getPackets().sendHideIComponent(1105, 37, false);
				player.getPackets().sendHideIComponent(1105, 38, false);
				player.getPackets().sendHideIComponent(1105, 39, false);
				player.getPackets().sendHideIComponent(1105, 43, false);
				player.getPackets().sendHideIComponent(1105, 44, false);
				player.getPackets().sendHideIComponent(1105, 45, false);
			} else if (componentId == 177) {
				player.getPackets().sendHideIComponent(1105, 62, false);
				player.getPackets().sendHideIComponent(1105, 63, false);
				player.getPackets().sendHideIComponent(1105, 69, false);
			} else if (componentId == 35) {
				ClansManager.openSetMottifColor(player, 0);
			} else if (componentId == 80) {
				ClansManager.openSetMottifColor(player, 1);
			} else if (componentId == 92) {
				ClansManager.openSetMottifColor(player, 2);
			} else if (componentId == 104) {
				ClansManager.openSetMottifColor(player, 3);// try
			} else if (componentId == 120) {
				player.stopAll();
			}
		} else if (interfaceId == 1110) {
			if (componentId == 82) {
				ClansManager.joinClanChatChannel(player);
			} else if (componentId == 75) {
				ClansManager.openClanDetails(player);
			} else if (componentId == 78) {
				ClansManager.openClanSettings(player);
			} else if (componentId == 91) {
				ClansManager.joinGuestClanChat(player);
			} else if (componentId == 95) {
				ClansManager.banPlayer(player);
			} else if (componentId == 99) {
				ClansManager.unbanPlayer(player);
			} else if (componentId == 11) {
				ClansManager.unbanPlayer(player, slotId);
			} else if (componentId == 109) {
				ClansManager.leaveClan(player);
			}
		} else if (interfaceId == 662) {
			if (player.getFamiliar() == null) {
				if (player.getPet() == null) {
					return;
				}
				if (componentId == 49) {
					player.getPet().call();
				} else if (componentId == 51) {
					player.getDialogueManager().startDialogue("DismissD");
				}
				return;
			}
			if (componentId == 49) {
				player.getFamiliar().call();
				player.getFamiliar().setName("" + player.getDisplayName() + "'s Familiar");
			} else if (componentId == 51) {
				player.getDialogueManager().startDialogue("DismissD");
			} else if (componentId == 67) {
				player.getFamiliar().takeBob();
			} else if (componentId == 69) {
				player.getFamiliar().renewFamiliar();
			} else if (componentId == 74) {
				if (player.getFamiliar().getSpecialAttack() == SpecialAttack.CLICK) {
					player.getFamiliar().setSpecial(true);
				}
				if (player.getFamiliar().hasSpecialOn()) {
					player.getFamiliar().submitSpecial(player);
				}
			}
		} else if (interfaceId == 747) {
			if (componentId == 8) {
				Familiar.selectLeftOption(player);
			} else if (player.getPet() != null) {
				if (componentId == 11 || componentId == 20) {
					player.getPet().call();
				} else if (componentId == 12 || componentId == 21) {
					player.getDialogueManager().startDialogue("DismissD");
				} else if (componentId == 10 || componentId == 19) {
					player.getPet().sendFollowerDetails();
				}
			} else if (player.getFamiliar() != null) {
				if (componentId == 11 || componentId == 20) {
					player.getFamiliar().call();
					player.getFamiliar().setName("" + player.getDisplayName() + "'s Familiar");
				} else if (componentId == 12 || componentId == 21) {
					player.getDialogueManager().startDialogue("DismissD");
				} else if (componentId == 13 || componentId == 22) {
					player.getFamiliar().takeBob();
				} else if (componentId == 14 || componentId == 23) {
					player.getFamiliar().renewFamiliar();
				} else if (componentId == 19 || componentId == 10) {
					player.getFamiliar().sendFollowerDetails();
				} else if (componentId == 18) {
					if (player.getFamiliar().getSpecialAttack() == SpecialAttack.CLICK) {
						player.getFamiliar().setSpecial(true);
					}
					if (player.getFamiliar().hasSpecialOn()) {
						player.getFamiliar().submitSpecial(player);
					}
				}
			}
		} else if (interfaceId == 309) {
			PlayerLook.handleHairdresserSalonButtons(player, componentId, slotId);
		} else if (interfaceId == 729) {
			PlayerLook.handleThessaliasMakeOverButtons(player, componentId, slotId);
		} else if (interfaceId == 187) {
			if (componentId == 1) {
				if (packetId == WorldPacketsDecoder.ACTION_BUTTON1_PACKET) {
					player.getMusicsManager().playAnotherMusic(slotId / 2);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET) {
					player.getMusicsManager().sendHint(slotId / 2);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON3_PACKET) {
					player.getMusicsManager().addToPlayList(slotId / 2);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON4_PACKET) {
					player.getMusicsManager().removeFromPlayList(slotId / 2);
				}
			} else if (componentId == 4) {
				player.getMusicsManager().addPlayingMusicToPlayList();
			} else if (componentId == 10) {
				player.getMusicsManager().switchPlayListOn();
			} else if (componentId == 11) {
				player.getMusicsManager().clearPlayList();
			} else if (componentId == 13) {
				player.getMusicsManager().switchShuffleOn();
			}
		} else if (interfaceId == 590 && componentId == 8 || interfaceId == 464) {
			player.getEmotesManager()
					.useBookEmote(interfaceId == 464 ? componentId : EmotesManager.getId(slotId, packetId));
		} else if (interfaceId == 192) {
			if (World.StarterArea(player)) {
				player.sendMessage("Please claim your starter first.");
				return;
			}
			if (componentId == 2) {
				player.getCombatDefinitions().switchDefensiveCasting();
			} else if (componentId == 7) {
				player.getCombatDefinitions().switchShowCombatSpells();
			} else if (componentId == 9) {
				player.getCombatDefinitions().switchShowTeleportSkillSpells();
			} else if (componentId == 11) {
				player.getCombatDefinitions().switchShowMiscallaneousSpells();
			} else if (componentId == 13) {
				player.getCombatDefinitions().switchShowSkillSpells();
			} else if (componentId >= 15 & componentId <= 17) {
				player.getCombatDefinitions().setSortSpellBook(componentId - 15);
			} else {
				Magic.processNormalSpell(player, componentId, packetId);
			}
		} else if (interfaceId == 334) {
			if (player.getDice() != null) {
				if (componentId == 22) {
					player.closeInterfaces();
				} else if (componentId == 21) {
					player.getDice().acceptPressed(player);
				}
			} else {
				if (componentId == 22) {
					player.closeInterfaces();
				} else if (componentId == 21) {
					player.getTrade().accept(false);
				}
			}
		} else if (interfaceId == 335) {
			if (player.getDice() != null) {
				if (componentId == 18) {
					player.getDice().acceptPressed(player);
				} else if (componentId == 20) {
					player.closeInterfaces();
				} else if (componentId == 32) {
					if (packetId == WorldPacketsDecoder.ACTION_BUTTON1_PACKET) {
						player.getDice().removeItem(player, slotId, 1);
					} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET) {
						player.getDice().removeItem(player, slotId, 5);
					} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON3_PACKET) {
						player.getDice().removeItem(player, slotId, 10);
					} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON4_PACKET) {
						player.getDice().removeItem(player, slotId, Integer.MAX_VALUE);
					} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON5_PACKET) {
						player.getTemporaryAttributtes().put("trade_item_X_Slot", slotId);
						player.getTemporaryAttributtes().put("trade_isRemove", Boolean.TRUE);
						player.getPackets().sendRunScript(108, new Object[] { "Enter Amount:" });
					} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON8_PACKET) {
						player.getPackets().sendGameMessage(ItemExamines.getExamine(new Item(slotId2)));
					}
				} else if (componentId == 35) {
					if (packetId == WorldPacketsDecoder.ACTION_BUTTON8_PACKET) {
						player.getPackets().sendGameMessage(ItemExamines.getExamine(new Item(slotId2)));
					}
				}
			} else {
				if (componentId == 18) {
					player.getTrade().accept(true);
				} else if (componentId == 20) {
					player.closeInterfaces();
				} else if (componentId == 32) {
					if (packetId == WorldPacketsDecoder.ACTION_BUTTON1_PACKET) {
						player.getTrade().removeItem(slotId, 1);
					} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET) {
						player.getTrade().removeItem(slotId, 5);
					} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON3_PACKET) {
						player.getTrade().removeItem(slotId, 10);
					} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON4_PACKET) {
						player.getTrade().removeItem(slotId, Integer.MAX_VALUE);
					} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON5_PACKET) {
						player.getTemporaryAttributtes().put("trade_item_X_Slot", slotId);
						player.getTemporaryAttributtes().put("trade_isRemove", Boolean.TRUE);
						player.getPackets().sendRunScript(108, new Object[] { "Enter Amount:" });
					} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON9_PACKET) {
						player.getTrade().sendValue(slotId, false);
					} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON8_PACKET) {
						player.getTrade().sendExamine(slotId, false);
					}
				} else if (componentId == 35) {
					if (packetId == WorldPacketsDecoder.ACTION_BUTTON1_PACKET) {
						player.getTrade().sendValue(slotId, true);
					} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON8_PACKET) {
						player.getTrade().sendExamine(slotId, true);
					}
				}
			}
		} else if (interfaceId == 336) {
			if (player.getDice() != null) {
				if (componentId == 0) {
					if (packetId == WorldPacketsDecoder.ACTION_BUTTON1_PACKET) {
						player.getDice().addItem(player, slotId, 1);
					} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET) {
						player.getDice().addItem(player, slotId, 5);
					} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON3_PACKET) {
						player.getDice().addItem(player, slotId, 10);
					} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON4_PACKET) {
						player.getDice().addItem(player, slotId, Integer.MAX_VALUE);
					} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON5_PACKET) {
						player.getTemporaryAttributtes().put("trade_item_X_Slot", slotId);
						player.getTemporaryAttributtes().remove("trade_isRemove");
						player.getPackets().sendRunScript(108, new Object[] { "Enter Amount:" });
					} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON8_PACKET) {
						player.getInventory().sendExamine(slotId);
					}
				}
			} else {
				if (componentId == 0) {
					if (packetId == WorldPacketsDecoder.ACTION_BUTTON1_PACKET) {
						player.getTrade().addItem(slotId, 1);
					} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET) {
						player.getTrade().addItem(slotId, 5);
					} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON3_PACKET) {
						player.getTrade().addItem(slotId, 10);
					} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON4_PACKET) {
						player.getTrade().addItem(slotId, Integer.MAX_VALUE);
					} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON5_PACKET) {
						player.getTemporaryAttributtes().put("trade_item_X_Slot", slotId);
						player.getTemporaryAttributtes().remove("trade_isRemove");
						player.getPackets().sendRunScript(108, new Object[] { "Enter Amount:" });
					} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON9_PACKET) {
						player.getTrade().sendValue(slotId);
					} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON8_PACKET) {
						player.getInventory().sendExamine(slotId);
					}
				}
			}
		} else if (interfaceId == 300) {
			ForgingInterface.handleIComponents(player, componentId);
		} else if (interfaceId == 206) {
			if (componentId == 15) {
				if (packetId == WorldPacketsDecoder.ACTION_BUTTON1_PACKET) {
					player.getPriceCheckManager().removeItem(slotId, 1);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET) {
					player.getPriceCheckManager().removeItem(slotId, 5);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON3_PACKET) {
					player.getPriceCheckManager().removeItem(slotId, 10);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON4_PACKET) {
					player.getPriceCheckManager().removeItem(slotId, Integer.MAX_VALUE);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON5_PACKET) {
					player.getTemporaryAttributtes().put("pc_item_X_Slot", slotId);
					player.getTemporaryAttributtes().put("pc_isRemove", Boolean.TRUE);
					player.getPackets().sendRunScript(108, new Object[] { "Enter Amount:" });
				}
			}
		} else if (interfaceId == 672) {
			if (componentId == 19) {
				SummoningScroll.sendInterface(player);
			}
			if (componentId == 16) {
				if (packetId == WorldPacketsDecoder.ACTION_BUTTON1_PACKET) {
					Summoning.createPouch(player, slotId2, 1);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET) {
					Summoning.createPouch(player, slotId2, 5);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON3_PACKET) {
					Summoning.createPouch(player, slotId2, 10);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON4_PACKET) {
					Summoning.createPouch(player, slotId2, Integer.MAX_VALUE);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON5_PACKET) {
					Summoning.createPouch(player, slotId2, 28);// x
					player.getPackets().sendGameMessage("You currently need "
							+ ItemDefinitions.getItemDefinitions(slotId2).getCreateItemRequirements());
				}

			}
		} else if (interfaceId == 207) {
			if (componentId == 0) {
				if (packetId == WorldPacketsDecoder.ACTION_BUTTON1_PACKET) {
					player.getPriceCheckManager().addItem(slotId, 1);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET) {
					player.getPriceCheckManager().addItem(slotId, 5);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON3_PACKET) {
					player.getPriceCheckManager().addItem(slotId, 10);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON4_PACKET) {
					player.getPriceCheckManager().addItem(slotId, Integer.MAX_VALUE);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON5_PACKET) {
					player.getTemporaryAttributtes().put("pc_item_X_Slot", slotId);
					player.getTemporaryAttributtes().remove("pc_isRemove");
					player.getPackets().sendRunScript(108, new Object[] { "Enter Amount:" });
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON9_PACKET) {
					player.getInventory().sendExamine(slotId);
				}
			}
		} else if (interfaceId == 665) {
			if (player.getFamiliar() == null || player.getFamiliar().getBob() == null) {
				return;
			}
			if (componentId == 0) {
				if (packetId == WorldPacketsDecoder.ACTION_BUTTON1_PACKET) {
					player.getFamiliar().getBob().addItem(slotId, 1);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET) {
					player.getFamiliar().getBob().addItem(slotId, 5);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON3_PACKET) {
					player.getFamiliar().getBob().addItem(slotId, 10);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON4_PACKET) {
					player.getFamiliar().getBob().addItem(slotId, Integer.MAX_VALUE);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON5_PACKET) {
					player.getTemporaryAttributtes().put("bob_item_X_Slot", slotId);
					player.getTemporaryAttributtes().remove("bob_isRemove");
					player.getPackets().sendRunScript(108, new Object[] { "Enter Amount:" });
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON9_PACKET) {
					player.getInventory().sendExamine(slotId);
				}
			}

		} else if (interfaceId == 161) { // assignment interface
			player.getPackets().sendIComponentText(161, 21, "Buy");// bars
			player.getPackets().sendIComponentText(164, 20, "" + player.getSlayerPoints());// SLAY
																							// POINTS
			player.getPackets().sendIComponentText(161, 19, "" + player.getSlayerPoints()); // SLAY
																							// POINTS
			player.getPackets().sendIComponentText(163, 18, "" + player.getSlayerPoints()); // SLAY
																							// POINTS
			player.getPackets().sendIComponentText(163, 26, "Used to create level 90 jewelry.");
			player.getPackets().sendIComponentText(163, 23, "Hydrix Gem"); // buy
																			// ring
																			// or
																			// something
			player.getPackets().sendIComponentText(163, 25, "N/a");
			player.getPackets().sendIComponentText(163, 29, "600 points.");
			player.getPackets().sendIComponentText(163, 30, "2000 points");
			player.getPackets().sendIComponentText(163, 31, "350 points.");
			player.getPackets().sendIComponentText(163, 22, "n/a"); // pets
			player.getPackets().sendIComponentText(163, 27, "If equiped, receive a damage increase during a task.");
			player.getPackets().sendIComponentText(163, 24, "Buy Slayer Helmet"); // buy
																					// slay
																					// helm
			player.getPackets().sendIComponentText(164, 24, "Slayer Experience"); // next
																					// to
																					// slayer
																					// icon
			player.getPackets().sendIComponentText(164, 26, "Ring Of Wealth"); // next
																				// to
																				// ring
																				// icon
			player.getPackets().sendIComponentText(164, 28, "All God Capes"); // 500x
																				// all
																				// runes
			player.getPackets().sendIComponentModel(164, 29, 2603);
			player.getPackets().sendIComponentText(164, 37, "Enchanted Gem"); // 500x
																				// all
																				// bolts
			player.getPackets().sendIComponentModel(164, 38, 2528);
			player.getPackets().sendIComponentText(164, 39, "Mystery Box"); // 500x
																			// all
																			// arrows
			player.getPackets().sendIComponentModel(164, 40, 2426);
			player.getPackets().sendIComponentText(164, 32, "500 points"); // BUY
																			// XP
																			// POINTS
			player.getPackets().sendIComponentText(164, 33, "60 points"); // BUY
																			// RING
																			// PTS
			player.getPackets().sendIComponentText(164, 34, "1 point"); // BUY
																			// DART
																			// PTS
			player.getPackets().sendIComponentText(164, 35, "400 points"); // BUY
																			// BOLT
																			// PTS
			player.getPackets().sendIComponentText(164, 36, "250 points"); // BUY
																			// ARROW
																			// PTS

			switch (componentId) {
			case 14:
				player.getInterfaceManager().sendInterface(163);
				break;
			case 15:
				player.getInterfaceManager().sendInterface(164);
				break;

			}
		} else if (interfaceId == 163) { // learn interface
			player.getPackets().sendIComponentText(163, 20, "Buy");// bars
			player.getPackets().sendIComponentText(164, 20, "" + player.getSlayerPoints());// SLAY
																							// POINTS
			player.getPackets().sendIComponentText(161, 19, "" + player.getSlayerPoints()); // SLAY
																							// POINTS
			player.getPackets().sendIComponentText(163, 18, "" + player.getSlayerPoints()); // SLAY
																							// POINTS
			player.getPackets().sendIComponentText(163, 26, "Used to create level 90 jewelry.");
			player.getPackets().sendIComponentText(163, 23, "Hydrix Gem"); // buy
																			// ring
																			// or
																			// something
			player.getPackets().sendIComponentText(163, 25, "N/A");
			player.getPackets().sendIComponentText(163, 29, "600 points.");
			player.getPackets().sendIComponentText(163, 30, "2000 points");
			player.getPackets().sendIComponentText(163, 31, "350 points.");
			player.getPackets().sendIComponentText(163, 22, "N/A"); // pets
			player.getPackets().sendIComponentText(163, 27, "If equiped, receive a damage increase during a task.");
			player.getPackets().sendIComponentText(163, 24, "Buy Slayer Helmet"); // buy
																					// slay
																					// helm
			player.getPackets().sendIComponentText(164, 24, "Slayer Experience"); // next
																					// to
																					// slayer
																					// icon
			player.getPackets().sendIComponentText(164, 26, "Ring Of Wealth"); // next
																				// to
																				// ring
																				// icon
			player.getPackets().sendIComponentText(164, 28, "All God Capes"); // 500x
																				// all
																				// runes
			player.getPackets().sendIComponentModel(164, 29, 2603);
			player.getPackets().sendIComponentText(164, 37, "Enchanted Gem"); // 500x
																				// all
																				// bolts
			player.getPackets().sendIComponentModel(164, 38, 2528);
			player.getPackets().sendIComponentText(164, 39, "Mystery Box"); // 500x
																			// all
																			// arrows
			player.getPackets().sendIComponentModel(164, 40, 2426);
			player.getPackets().sendIComponentText(164, 32, "500 points"); // BUY
																			// XP
																			// POINTS
			player.getPackets().sendIComponentText(164, 33, "60 points"); // BUY
																			// RING
																			// PTS
			player.getPackets().sendIComponentText(164, 34, "1 point"); // BUY
																			// DART
																			// PTS
			player.getPackets().sendIComponentText(164, 35, "400 points"); // BUY
																			// BOLT
																			// PTS
			player.getPackets().sendIComponentText(164, 36, "250 points"); // BUY
																			// ARROW
																			// PTS
			switch (componentId) {
			case 30:
			case 35:
			case 23:
				if (player.getSlayerPoints() > 2000 && player.getInventory().hasFreeSlots()) {
					player.setSlayerPoints(player.getSlayerPoints() - 2000);
					player.getInventory().addItem(29728, 1);
					player.sendMessage("You bought a Hydrix gem for 2000 slayer points.");
					player.getInterfaceManager().sendInterface(164);
				} else {
					player.sendMessage("You need at least 2000 slayer points to buy a Hydrix gem.");

				}

				break;
			   case 22:
	            case 29:
	            case 33:
	            	//player.sendMessage("Yes");
	            	player.getInterfaceManager().sendInterface(378);
	            	 player.getPackets().sendIComponentText(378, 81, "Pets"); // highlined
	            	 player.getPackets().sendIComponentText(378, 89, "Slayer Points"); // points name
	            	 player.getPackets().sendIComponentText(378, 79, ""+player.getSlayerPoints()+""); // points amount
	            	 
	            	 player.getPackets().sendIComponentText(378, 83, "Pup"); // pet
	            	 player.getPackets().sendIComponentText(378, 90, "A pet Bat."); // pet info
	            	 player.getPackets().sendItemOnIComponent(378, 103, -1, 1);
	            	 player.getPackets().sendItemOnIComponent(378, 104, 18900, 1);
	            	 
	            	 player.getPackets().sendIComponentText(378, 87, "Irony"); // pet
	            	 player.getPackets().sendIComponentText(378, 99, "A pet Iron Dragon."); // pet info
	            	 player.getPackets().sendItemOnIComponent(378, 105, 29701, 1);
	            	 
	            	 player.getPackets().sendIComponentText(378, 88, "Steels"); // pet
	            	 player.getPackets().sendIComponentText(378, 100, "A pet Steel Dragon."); // pet info
	            	 player.getPackets().sendItemOnIComponent(378, 101, 29700, 1);
	            	 
	            	 player.getPackets().sendIComponentText(378, 84, "Dusty"); // pet
	            	 player.getPackets().sendIComponentText(378, 91, "A pet Dust Devil."); // pet info
	            	 player.getPackets().sendIComponentModel(378, 92, 5076);
	            	 
	            	 player.getPackets().sendIComponentText(378, 85, "Edi"); // pet
	            	 player.getPackets().sendIComponentText(378, 97, "A pet Eddimu."); // pet info
	            	 
	            	 player.getPackets().sendIComponentText(378, 86, "Freezy"); // pet
	            	 player.getPackets().sendIComponentText(378, 98, "A pet Ice Strykewyrm."); // pet info
	            	 
	            	break;

			case 32:
				if (player.getSlayerPoints() > 350) {
					player.setSlayerPoints(player.getSlayerPoints() - 350);
					player.getInventory().addItem(13263, 1);
					player.sendMessage("You bought a slayer helm for 350 slayer points.");
					player.getInterfaceManager().sendInterface(163);
				} else {
					player.sendMessage("You need at least 350 slayer points to buy a slayer helm.");

				}
				break;
			case 15:
				player.getInterfaceManager().sendInterface(164);
				break;
			case 14:
				player.getInterfaceManager().sendInterface(161);
				break;
			}
			if (interfaceId == 549) {
				player.stopAll();
				WorldTile destTile = null;
				setarmor(player);
				player.closeInterfaces();
				if (componentId == 31) {
					player.closeInterfaces();
				} else if (componentId == 33) {
					setarmor(player);
					player.closeInterfaces();
				}
				if (componentId == 35) {
				}
				player.stopAll();
				setarmor(player);
				WorldTile destTile1 = null;
			} else {
				// player.getPackets().sendGameMessage("CI: " + componentId);

			}
		} else if (interfaceId == 164) { // buy interface

			player.getPackets().sendIComponentText(164, 22, "Buy");// bars
			player.getPackets().sendIComponentText(161, 19, "" + player.getSlayerPoints()); // SLAY
																							// POINTS
			player.getPackets().sendIComponentText(163, 18, "" + player.getSlayerPoints()); // SLAY
																							// POINTS
			player.getPackets().sendIComponentText(163, 26, "Used to create level 90 jewelry.");
			player.getPackets().sendIComponentText(163, 23, "Hydrix Gem"); // buy
																			// ring
																			// or
																			// something
			player.getPackets().sendIComponentText(163, 25, "Pets");
			player.getPackets().sendIComponentText(163, 29, "600 points.");
			player.getPackets().sendIComponentText(163, 30, "2000 points");
			player.getPackets().sendIComponentText(163, 31, "350 points.");
			player.getPackets().sendIComponentText(163, 22, "Pets"); // pets
			player.getPackets().sendIComponentText(163, 27, "If equiped, receive a damage increase during a task.");
			player.getPackets().sendIComponentText(163, 24, "Buy Slayer Helmet"); // buy
																					// slay
																					// helm
			player.getPackets().sendIComponentText(164, 24, "Slayer Experience"); // next
																					// to
																					// slayer
																					// icon
			player.getPackets().sendIComponentText(164, 26, "Ring Of Wealth"); // next
																				// to
																				// ring
																				// icon
			player.getPackets().sendIComponentText(164, 28, "All God Capes"); // 500x
																				// all
																				// runes
			player.getPackets().sendIComponentModel(164, 29, 2603);
			player.getPackets().sendIComponentText(164, 37, "Enchanted Gem"); // 500x
																				// all
																				// bolts
			player.getPackets().sendIComponentModel(164, 38, 2528);
			player.getPackets().sendIComponentText(164, 39, "Mystery Box"); // 500x
																			// all
																			// arrows
			player.getPackets().sendIComponentModel(164, 40, 2426);
			player.getPackets().sendIComponentText(164, 32, "500 points"); // BUY
																			// XP
																			// POINTS
			player.getPackets().sendIComponentText(164, 33, "60 points"); // BUY
																			// RING
																			// PTS
			player.getPackets().sendIComponentText(164, 34, "1 point"); // BUY
																			// DART
																			// PTS
			player.getPackets().sendIComponentText(164, 35, "400 points"); // BUY
																			// BOLT
																			// PTS
			player.getPackets().sendIComponentText(164, 36, "250 points"); // BUY
																			// ARROW
																			// PTS
			switch (componentId) {
			case 25:
			case 24: // buy XP
				if (player.getSlayerPoints() > 500) {
					player.setSlayerPoints(player.getSlayerPoints() - 500);
					player.getSkills().addXp(Skills.SLAYER, 22000); // put
																	// amount of
																	// XP here
					player.sendMessage("You bought Slayer Experience for 500 slayer points.");
					player.getInterfaceManager().sendInterface(164);
				} else {
					player.sendMessage("You need at least 500 slayer points to buy Slayer Experience.");

				}
				break;
			case 26:
			case 27: // buy row
				if (player.getSlayerPoints() >= 60) {
					player.setSlayerPoints(player.getSlayerPoints() - 60);
					player.getInventory().addItem(2572, 1);
					player.sendMessage("You bought a ring of wealth for 60 slayer points.");
					player.getInterfaceManager().sendInterface(164);
				} else {
					player.sendMessage("You need at least 60 slayer points to buy a ring of wealth.");
					break;
				}
				break;
			case 28:
			case 29: // God Capes
				if (player.getSlayerPoints() > 250) {
					player.setSlayerPoints(player.getSlayerPoints() - 250);
					player.getInventory().addItem(2413, 1);
					player.getInventory().addItem(2412, 1);
					player.getInventory().addItem(2414, 1);
					player.sendMessage("You bought a god cape set for 250 slayer points.");
					player.getInterfaceManager().sendInterface(164);
				} else {
					player.sendMessage("You need at least 250 slayer points to buy a god cape set.");

				}
				break;
			case 30:
			case 35:
			case 23:
				if (player.getSlayerPoints() > 2000) {
					player.setSlayerPoints(player.getSlayerPoints() - 2000);
					player.getInventory().addItem(31853, 1);
					player.sendMessage("You bought a Hydrix gem for 2000 slayer points.");
					player.getInterfaceManager().sendInterface(164);
				} else {
					player.sendMessage("You need at least 2000 slayer points to buy a Hydrix gem.");

				}
				// case 28:

			case 39:
				break;
			case 37:
				if (player.getSlayerPoints() >= 1) {
					player.setSlayerPoints(player.getSlayerPoints() - 1);
					// player.getInventory().addItem(2413, 1);
					player.getBank().addItem(4155, 1, true);
					// player.getInventory().addItem(2414, 1);
					player.sendMessage("You bought an Enchanted gem for 1 slayer point.");
					player.getInterfaceManager().sendInterface(164);
				} else {
					player.sendMessage("You need at least 1 slayer point to buy an Enchanted gem.");
				}
				break;
			case 40:
				if (player.getSlayerPoints() >= 400) {
					player.setSlayerPoints(player.getSlayerPoints() - 400);
					// player.getInventory().addItem(2413, 1);
					player.getBank().addItem(6199, 1, true);
					// player.getInventory().addItem(2414, 1);
					player.sendMessage("You bought a Mystery box for 400 slayer points.");
					player.getInterfaceManager().sendInterface(164);
				} else {
					player.sendMessage("You need at least 400 slayer points to buy a Mystery box.");
				}
				break;
			case 16:
				player.getInterfaceManager().sendInterface(163);
				break;
			case 17:
				player.getInterfaceManager().sendInterface(161);
				break;
			}
		} else if (interfaceId == 378) {
			switch (componentId) {
			case 76: // pup
				if (player.getSlayerPoints() >= 600) {
					player.getInventory().addItem(29698, 1);
					player.slayerPoints -= 600;
					player.getPackets().sendGameMessage("You bought the Pup!");
					player.gotpugpet = true;
				} else {
					player.getPackets().sendGameMessage("You need atleast 600 points to buy one!");
				}
				break;
			case 74: // Edi
				if (player.getSlayerPoints() >= 600) {
					player.getInventory().addItem(29702, 1);
					player.slayerPoints -= 600;
					player.gotedipet = true;
					player.getPackets().sendGameMessage("You bought the Edi!");
				} else {
					player.getPackets().sendGameMessage("You need atleast 600 points to buy one!");
				}
				break;
			case 75: // Freezy
				if (player.getSlayerPoints() >= 600) {
					player.getInventory().addItem(29699, 1);
					player.slayerPoints -= 600;
					player.gotfreezypet = true;
					player.getPackets().sendGameMessage("You bought the Freezy!");
				} else {
					player.getPackets().sendGameMessage("You need atleast 600 points to buy one!");
				}
				break;
			case 73: // Dusty
				if (player.getSlayerPoints() >= 600) {
					player.getInventory().addItem(29697, 1);
					player.slayerPoints -= 600;
					player.gotdustypet = true;
					player.getPackets().sendGameMessage("You bought the Dusty!");
				} else {
					player.getPackets().sendGameMessage("You need atleast 600 points to buy one!");
				}
				break;
			case 78: // Steels
				if (player.getSlayerPoints() >= 600) {
					player.getInventory().addItem(29700, 1);
					player.slayerPoints -= 600;
					player.gotsdragpet = true;
					player.getPackets().sendGameMessage("You bought a Steels!");
				} else {
					player.getPackets().sendGameMessage("You need atleast 600 points to buy one!");
				}
				break;
			case 77: // Irony
				if (player.getSlayerPoints() >= 600) {
					player.getInventory().addItem(29701, 1);
					player.slayerPoints -= 600;
					player.gotidragpet = true;
					player.getPackets().sendGameMessage("You bought an Irony!");
				} else {
					player.getPackets().sendGameMessage("You need atleast 600 points to buy one!");
				}
				break;
			}

			// INTERFACE PK
		} else if (interfaceId == 1226) {
			player.getPackets().sendIComponentText(1226, 2, "You're missing out!");
			player.getPackets().sendIComponentText(1226, 3,
					"Looks like you aren't a donator! Ever wanted to own your own armour set, have a chilled skilling zone or even bank while skilling? Now is your chance!");
			player.getPackets().sendIComponentText(1226, 4, "Support Harmony now!");
			if (componentId == 12) {
				// player.closeInterfaces();
				player.getPackets().sendOpenURL("www.harmonyrsps.com/store.html"); //store button
			}
			if (componentId == 19) {
				player.sendMessage("We're sorry you aren't interested. Enjoy the server!");
				player.closeInterfaces();
			}
		} else if (interfaceId == 1297) {
			player.getInterfaceManager().sendInterface(1297);
			player.getPackets().sendIComponentText(1297, 249, "Donators Store");
			player.getPackets().sendIComponentText(1297, 124, "Information");
			player.getPackets().sendIComponentText(1297, 125, "Price");
			player.getPackets().sendIComponentText(1297, 273, "Shop 1");
			player.getPackets().sendIComponentText(1297, 266, "Shop 2");
			player.getPackets().sendIComponentText(1297, 259, "Shop 3");
			player.getPackets().sendIComponentText(1297, 88, "New woodcutting animation");
			player.getPackets().sendIComponentText(1297, 154, "<col=00FF00>5M</col>");
			player.getPackets().sendIComponentText(1297, 144, "New mining animation");
			player.getPackets().sendIComponentText(1297, 155, "<col=00FF00>5M</col>");
			player.getPackets().sendIComponentText(1297, 145, "Gnome teleport animation");
			player.getPackets().sendIComponentText(1297, 156, "<col=FF00FF>10M</col>");
			player.getPackets().sendIComponentText(1297, 146, "Funny death animation");
			player.getPackets().sendIComponentText(1297, 157, "<col=ff0000>20M</col>");
			player.getPackets().sendIComponentText(1297, 147, "New throwing weapon animation");
			player.getPackets().sendIComponentText(1297, 158, "<col=FF00FF>10M</col>");
			player.getPackets().sendIComponentText(1297, 148, "Old woodcutting animation");
			player.getPackets().sendIComponentText(1297, 159, "<col=FFFF00>Free</col>");
			player.getPackets().sendIComponentText(1297, 149, "Old mining animation");
			player.getPackets().sendIComponentText(1297, 160, "<col=FFFF00>Free</col>");
			player.getPackets().sendIComponentText(1297, 150, "Old teleport animation");
			player.getPackets().sendIComponentText(1297, 161, "<col=FFFF00>Free</col>");
			player.getPackets().sendIComponentText(1297, 151, "Old death animation");
			player.getPackets().sendIComponentText(1297, 162, "<col=FFFF00>Free</col>");
			player.getPackets().sendIComponentText(1297, 152, "Old Throwing weapon animation");
			player.getPackets().sendIComponentText(1297, 163, "<col=FFFF00>Free</col>");
			if (componentId == 271) {
				ShopsHandler.openShop(player, 28);
			}
			if (componentId == 264) {
				player.getInterfaceManager().sendInterface(1312);
				player.getPackets().sendIComponentText(1312, 27, "Shops");
				player.getPackets().sendIComponentText(1312, 38, "Weaponry");
				player.getPackets().sendIComponentText(1312, 46, "Misc");
				player.getPackets().sendIComponentText(1312, 54, "Pure Store");
				player.getPackets().sendIComponentText(1312, 62, "Armoury");
				player.getPackets().sendIComponentText(1312, 70, "Runecrafting supplies");
				player.getPackets().sendIComponentText(1312, 78, "Crafting store");
				player.getPackets().sendIComponentText(1312, 86, "Ranged Armour");
				player.getPackets().sendIComponentText(1312, 94, "Ranged Weaponry");
				player.getPackets().sendIComponentText(1312, 102, "Magic Store");
			}
			if (componentId == 257) {
				player.getInterfaceManager().sendInterface(825);
				player.getPackets().sendIComponentText(825, 29, "Donators Store 3");
				player.getPackets().sendIComponentText(825, 66, "Temporary animations");
				player.getPackets().sendIComponentText(825, 67,
						"These animations are temporary effects only, they will reset when you log out.                                             All purchases are done by ingame coins and are not refundable.");
				// player.getPackets().sendIComponentText(825, 69, "All
				// purchases are done by ingame coins and are not refundable.");
				player.getPackets().sendIComponentText(825, 54, "Coins in inventory");
				player.getPackets().sendIComponentText(825, 55, "" + player.getInventory().getNumerOf(995) + "");
				player.getPackets().sendIComponentText(825, 95, "Skip walk");
				player.getPackets().sendIComponentText(825, 96, "5M");
				player.getPackets().sendIComponentText(825, 99, "Posh walk");
				player.getPackets().sendIComponentText(825, 100, "5M");
				player.getPackets().sendIComponentText(825, 103, "Floating walk");
				player.getPackets().sendIComponentText(825, 104, "5M");
				player.getPackets().sendIComponentText(825, 107, "Flying walk");
				player.getPackets().sendIComponentText(825, 108, "8M");
				player.getPackets().sendIComponentText(825, 111, "Spastic walk");
				player.getPackets().sendIComponentText(825, 112, "10M");
				player.getPackets().sendIComponentText(825, 115, "March walk");
				player.getPackets().sendIComponentText(825, 116, "10M");
				player.getPackets().sendIComponentText(825, 119, "Sexy walk");
				player.getPackets().sendIComponentText(825, 120, "10M");
				player.getPackets().sendIComponentText(825, 123, "Hanging walk");
				player.getPackets().sendIComponentText(825, 124, "50M");
				player.getPackets().sendIComponentText(825, 127, "Flip item Rest");
				player.getPackets().sendIComponentText(825, 128, "50M");
				player.getPackets().sendIComponentText(825, 131, "Empty");
				player.getPackets().sendIComponentText(825, 132, "Empty");
				player.getPackets().sendIComponentText(825, 134, "Empty");
				player.getPackets().sendIComponentText(825, 135, "Empty");
				player.getPackets().sendIComponentText(825, 138, "Empty");
				player.getPackets().sendIComponentText(825, 139, "Empty");

			}
			if (componentId == 53) {
				if (player.getInventory().containsItem(995, 5000000) && player.wcanim == false) {
					player.getInventory().deleteItem(995, 5000000);
					Settings.GpSyncAmount += 5000000;
					player.wcanim = true;
					player.sendMessage("When woodcutting you will now use a new animation!");
					player.closeInterfaces();
				} else {
					player.sendMessage("You need 5,000,000 coins to purchase this.");
					player.closeInterfaces();
				}
			}
			if (componentId == 54) {
				if (player.getInventory().containsItem(995, 5000000) && player.minanim == false) {
					player.getInventory().deleteItem(995, 5000000);
					Settings.GpSyncAmount += 5000000;
					player.minanim = true;
					player.sendMessage("When mining you will now use a new animation!");
					player.closeInterfaces();
				} else {
					player.sendMessage("You need 5,000,000 coins to purchase this.");
					player.closeInterfaces();
				}
			}
			if (componentId == 55) {
				if (player.getInventory().containsItem(995, 10000000) && player.gnometele == false) {
					player.getInventory().deleteItem(995, 10000000);
					Settings.GpSyncAmount += 10000000;
					player.gnometele = true;
					player.sendMessage("When teleporting home you will now use a new animation!");
					player.closeInterfaces();
				} else {
					player.sendMessage("You need 10,000,000 coins to purchase this.");
					player.closeInterfaces();
				}
			}
			if (componentId == 56) {
				if (player.getInventory().containsItem(995, 20000000) && player.dieemote == false) {
					player.getInventory().deleteItem(995, 20000000);
					Settings.GpSyncAmount += 20000000;
					player.dieemote = true;
					player.sendMessage("When you die you will now use a new animation!");
					player.closeInterfaces();
				} else {
					player.sendMessage("You need 20,000,000 coins to purchase this.");
					player.closeInterfaces();
				}
			}
			if (componentId == 57) {
				if (player.getInventory().containsItem(995, 10000000) && player.dartanim == false) {
					player.getInventory().deleteItem(995, 10000000);
					Settings.GpSyncAmount += 10000000;
					player.dartanim = true;
					player.sendMessage("When you use a throwing weapon you will now use a new animation!");
					player.closeInterfaces();
				} else {
					player.sendMessage("You need 10,000,000 coins to purchase this.");
					player.closeInterfaces();
				}
			}
			if (componentId == 58) {
				if (player.wcanim == true) {
					player.wcanim = false;
					player.sendMessage("When woodcutting, you will now use an old animation.");
					player.closeInterfaces();
				} else {
					player.sendMessage("You don't need to do this.");
					player.closeInterfaces();
				}
			}
			if (componentId == 59) {
				if (player.minanim == true) {
					player.minanim = false;
					player.sendMessage("When mining, you will now use an old animation.");
					player.closeInterfaces();
				} else {
					player.sendMessage("You don't need to do this.");
					player.closeInterfaces();
				}
			}
			if (componentId == 60) {
				if (player.gnometele == true) {
					player.gnometele = false;
					player.sendMessage("When teleporting home, you will now use an old animation.");
					player.closeInterfaces();
				} else {
					player.sendMessage("You don't need to do this.");
					player.closeInterfaces();
				}
			}
			if (componentId == 61) {
				if (player.dieemote == true) {
					player.dieemote = false;
					player.sendMessage("When you die, you will now use an old animation.");
					player.closeInterfaces();
				} else {
					player.sendMessage("You don't need to do this.");
					player.closeInterfaces();
				}
			}
			if (componentId == 62) {
				if (player.dartanim == true) {
					player.dartanim = false;
					player.sendMessage("When using thrown items, you will now use an old animation.");
					player.closeInterfaces();
				} else {
					player.sendMessage("You don't need to do this.");
					player.closeInterfaces();
				}

			}

		} else if (interfaceId == 825) {
			player.getPackets().sendIComponentText(825, 29, "Donators Store 3");
			player.getPackets().sendIComponentText(825, 66, "Temporary animations");
			player.getPackets().sendIComponentText(825, 67,
					"The walking animations are temporary effects only, they will reset when you log out. Resting emotes are forever.                                             All purchases are done by ingame coins and are not refundable.");
			// player.getPackets().sendIComponentText(825, 69, "All purchases
			// are done by ingame coins and are not refundable.");
			player.getPackets().sendIComponentText(825, 54, "Coins in inventory");
			player.getPackets().sendIComponentText(825, 55, "" + player.getInventory().getNumerOf(995) + "");
			player.getPackets().sendIComponentText(825, 95, "Skip walk");
			player.getPackets().sendIComponentText(825, 96, "5M");
			player.getPackets().sendIComponentText(825, 99, "Posh walk");
			player.getPackets().sendIComponentText(825, 100, "5M");
			player.getPackets().sendIComponentText(825, 103, "Floating walk");
			player.getPackets().sendIComponentText(825, 104, "5M");
			player.getPackets().sendIComponentText(825, 107, "Flying walk");
			player.getPackets().sendIComponentText(825, 108, "8M");
			player.getPackets().sendIComponentText(825, 111, "Spastic walk");
			player.getPackets().sendIComponentText(825, 112, "10M");
			player.getPackets().sendIComponentText(825, 115, "March walk");
			player.getPackets().sendIComponentText(825, 116, "10M");
			player.getPackets().sendIComponentText(825, 119, "Sexy walk");
			player.getPackets().sendIComponentText(825, 120, "10M");
			player.getPackets().sendIComponentText(825, 123, "Hanging walk");
			player.getPackets().sendIComponentText(825, 124, "50M");
			player.getPackets().sendIComponentText(825, 127, "Flip item Rest");
			player.getPackets().sendIComponentText(825, 128, "50M");
			player.getPackets().sendIComponentText(825, 131, "Empty");
			player.getPackets().sendIComponentText(825, 132, "Empty");
			player.getPackets().sendIComponentText(825, 134, "Empty");
			player.getPackets().sendIComponentText(825, 135, "Empty");
			player.getPackets().sendIComponentText(825, 138, "Empty");
			player.getPackets().sendIComponentText(825, 139, "Empty");
			if (componentId == 93 && player.getInventory().containsItem(995, 5000000)) {
				player.closeInterfaces();
				player.getAppearence().setRenderEmote(594);
				player.getInventory().deleteItem(995, 5000000);
				Settings.GpSyncAmount += 5000000;
			}
			if (componentId == 97 && player.getInventory().containsItem(995, 5000000)) {
				player.getAppearence().setRenderEmote(1073);
				player.getInventory().deleteItem(995, 5000000);
				Settings.GpSyncAmount += 5000000;
				player.closeInterfaces();
			}
			if (componentId == 101 && player.getInventory().containsItem(995, 5000000)) {
				player.getAppearence().setRenderEmote(871);
				player.getInventory().deleteItem(995, 5000000);
				Settings.GpSyncAmount += 5000000;
				player.closeInterfaces();
			}
			if (componentId == 105 && player.getInventory().containsItem(995, 8000000)) {
				player.getAppearence().setRenderEmote(913);
				player.getInventory().deleteItem(995, 8000000);
				Settings.GpSyncAmount += 8000000;
				player.closeInterfaces();
			}
			if (componentId == 109 && player.getInventory().containsItem(995, 10000000)) {
				player.getAppearence().setRenderEmote(1074);
				player.getInventory().deleteItem(995, 10000000);
				Settings.GpSyncAmount += 10000000;
				player.closeInterfaces();
			}
			if (componentId == 113 && player.getInventory().containsItem(995, 10000000)) {
				player.getAppearence().setRenderEmote(1075);
				player.getInventory().deleteItem(995, 10000000);
				Settings.GpSyncAmount += 10000000;
				player.closeInterfaces();
			}
			if (componentId == 117 && player.getInventory().containsItem(995, 10000000)) {
				player.getAppearence().setRenderEmote(1076);
				player.getInventory().deleteItem(995, 10000000);
				Settings.GpSyncAmount += 10000000;
				player.closeInterfaces();
			}
			if (componentId == 121 && player.getInventory().containsItem(995, 50000000)) {
				player.getAppearence().setRenderEmote(685);
				player.getInventory().deleteItem(995, 50000000);
				Settings.GpSyncAmount += 10000000;
				player.closeInterfaces();
			}
			if (componentId == 125 && player.getInventory().containsItem(995, 50000000)) {
				player.ZREST = true;
				player.getInventory().deleteItem(995, 50000000);
				Settings.GpSyncAmount += 10000000;
				player.closeInterfaces();
			}

		} else if (interfaceId == 1312) {
			player.getPackets().sendIComponentText(1312, 27, "Shops");
			player.getPackets().sendIComponentText(1312, 38, "Weaponry");
			player.getPackets().sendIComponentText(1312, 46, "Misc");
			player.getPackets().sendIComponentText(1312, 54, "Pure Store");
			player.getPackets().sendIComponentText(1312, 62, "Armoury");
			player.getPackets().sendIComponentText(1312, 70, "Runecrafting supplies");
			player.getPackets().sendIComponentText(1312, 78, "Crafting store");
			player.getPackets().sendIComponentText(1312, 86, "Ranged Armour");
			player.getPackets().sendIComponentText(1312, 94, "Ranged Weaponry");
			player.getPackets().sendIComponentText(1312, 102, "Magic Store");
			if (componentId == 35) {
				// player.closeInterfaces();
				ShopsHandler.openShop(player, 4);
			}
			if (componentId == 43) {
				// player.closeInterfaces();
				ShopsHandler.openShop(player, 9);
			}
			if (componentId == 51) {
				ShopsHandler.openShop(player, 32);
				// player.closeInterfaces();
			}
			if (componentId == 59) {
				ShopsHandler.openShop(player, 7);
				// player.closeInterfaces();
			}
			if (componentId == 67) {
				ShopsHandler.openShop(player, 23);
				// player.closeInterfaces();
			}
			if (componentId == 75) {
				ShopsHandler.openShop(player, 15);
				// player.closeInterfaces();
			}
			if (componentId == 83) {
				ShopsHandler.openShop(player, 19);
				// player.closeInterfaces();
			}
			if (componentId == 91) {
				ShopsHandler.openShop(player, 5);
				// player.closeInterfaces();
			}
			if (componentId == 99) {
				ShopsHandler.openShop(player, 3);
				// player.closeInterfaces();
			}

		} else if (interfaceId == 72) {

			switch (componentId) {
			case 71:
				if (player.startedecobreakdownquest != true) {
					player.getInterfaceManager().sendInterface(275);
					player.getPackets().sendIComponentText(275, 1, "Economic Breakdown Quest");
					player.getPackets().sendIComponentText(275, 10, "");
					player.getPackets().sendIComponentText(275, 11, "Speak to the King in Varrock.");
					player.getPackets().sendIComponentText(275, 12, "<u>Requirements</u>");
					player.getPackets().sendIComponentText(275, 13, "60 Slayer.");
					player.getPackets().sendIComponentText(275, 14, "92 Attack.");
					player.getPackets().sendIComponentText(275, 15, "");
					player.getPackets().sendIComponentText(275, 16,
							"The lodestone works, remember to take full use of it.");
					player.getPackets().sendIComponentText(275, 17, "");
					player.getPackets().sendIComponentText(275, 18, "");
					player.getPackets().sendIComponentText(275, 19, "");
					player.getPackets().sendIComponentText(275, 20, "");
					break;
				} else if (player.ecobdpart == 1) {
					player.getInterfaceManager().sendInterface(275);
					player.getPackets().sendIComponentText(275, 1, "Economic Breakdown Quest");
					player.getPackets().sendIComponentText(275, 10, "");
					player.getPackets().sendIComponentText(275, 11,
							"Speak to Captain Tobias at Port Sarim about export/import documents.");
					player.getPackets().sendIComponentText(275, 12, "");
					player.getPackets().sendIComponentText(275, 13, "");
					player.getPackets().sendIComponentText(275, 14,
							"");
					player.getPackets().sendIComponentText(275, 15, "");
					player.getPackets().sendIComponentText(275, 16, "");
					player.getPackets().sendIComponentText(275, 17, "");
					player.getPackets().sendIComponentText(275, 18, "");
					player.getPackets().sendIComponentText(275, 19, "");
					player.getPackets().sendIComponentText(275, 20, "");
					break;
				} else if (player.ecobdpart == 2) {
					player.getInterfaceManager().sendInterface(275);
					player.getPackets().sendIComponentText(275, 1, "Economic Breakdown Quest");
					player.getPackets().sendIComponentText(275, 10, "");
					player.getPackets().sendIComponentText(275, 11,
							"Discover the problem with the documents and tell the King what it is.");
					player.getPackets().sendIComponentText(275, 12, "");
					player.getPackets().sendIComponentText(275, 13, "");
					player.getPackets().sendIComponentText(275, 14,
							"");
					player.getPackets().sendIComponentText(275, 15, "");
					player.getPackets().sendIComponentText(275, 16, "");
					player.getPackets().sendIComponentText(275, 17, "");
					player.getPackets().sendIComponentText(275, 18, "");
					player.getPackets().sendIComponentText(275, 19, "");
					player.getPackets().sendIComponentText(275, 20, "");
					break;
				} else if (player.ecobdpart == 3) {
					player.getInterfaceManager().sendInterface(275);
					player.getPackets().sendIComponentText(275, 1, "Economic Breakdown Quest");
					player.getPackets().sendIComponentText(275, 10, "");
					player.getPackets().sendIComponentText(275, 11,
							"Take the order to the court in Seer's Village.");
					player.getPackets().sendIComponentText(275, 12, "");
					player.getPackets().sendIComponentText(275, 13, "");
					player.getPackets().sendIComponentText(275, 14,
							"");
					player.getPackets().sendIComponentText(275, 15, "");
					player.getPackets().sendIComponentText(275, 16, "");
					player.getPackets().sendIComponentText(275, 17, "");
					player.getPackets().sendIComponentText(275, 18, "");
					player.getPackets().sendIComponentText(275, 19, "");
					player.getPackets().sendIComponentText(275, 20, "");
					break;
				} else if (player.ecobdpart == 4) {
					player.getInterfaceManager().sendInterface(275);
					player.getPackets().sendIComponentText(275, 1, "Economic Breakdown Quest");
					player.getPackets().sendIComponentText(275, 10, "");
					player.getPackets().sendIComponentText(275, 11,
							"Head back to Captain Tobias and inform him about the new law.");
					player.getPackets().sendIComponentText(275, 12, "");
					player.getPackets().sendIComponentText(275, 13, "Then find the dungeon to find his team.");
					player.getPackets().sendIComponentText(275, 14,
							"");
					player.getPackets().sendIComponentText(275, 15, "");
					player.getPackets().sendIComponentText(275, 16, "");
					player.getPackets().sendIComponentText(275, 17, "");
					player.getPackets().sendIComponentText(275, 18, "");
					player.getPackets().sendIComponentText(275, 19, "");
					player.getPackets().sendIComponentText(275, 20, "");
					break;
				} else if (player.ecobdpart == 5) {
					player.getInterfaceManager().sendInterface(275);
					player.getPackets().sendIComponentText(275, 1, "Economic Breakdown Quest");
					player.getPackets().sendIComponentText(275, 10, "");
					player.getPackets().sendIComponentText(275, 11,
							"Quest complete.");
					player.getPackets().sendIComponentText(275, 12, "");
					player.getPackets().sendIComponentText(275, 13, "");
					player.getPackets().sendIComponentText(275, 14,
							"");
					player.getPackets().sendIComponentText(275, 15, "");
					player.getPackets().sendIComponentText(275, 16, "");
					player.getPackets().sendIComponentText(275, 17, "");
					player.getPackets().sendIComponentText(275, 18, "");
					player.getPackets().sendIComponentText(275, 19, "");
					player.getPackets().sendIComponentText(275, 20, "");
					break;
				}
			case 70:
				if (player.ruesaltarprogress == 0) {
					player.getInterfaceManager().sendInterface(275);
					player.getPackets().sendIComponentText(275, 1, "Rue's Altar Quest");
					player.getPackets().sendIComponentText(275, 10, "");
					player.getPackets().sendIComponentText(275, 11, "Speak to Rue in the Runecrafting abyss");
					player.getPackets().sendIComponentText(275, 12, "<u>Requirements</u>");
					player.getPackets().sendIComponentText(275, 13, "50 Runecrafting.");
					player.getPackets().sendIComponentText(275, 14, "-=-Tips-=-");
					player.getPackets().sendIComponentText(275, 15, "Use the 'Quest guide'");
					player.getPackets().sendIComponentText(275, 16,
							"The lodestone works, remember to take full use of it.");
					player.getPackets().sendIComponentText(275, 17, "");
					player.getPackets().sendIComponentText(275, 18, "");
					player.getPackets().sendIComponentText(275, 19, "");
					player.getPackets().sendIComponentText(275, 20, "");
					break;
				} else if (player.ruesaltarprogress == 1) {
					player.getInterfaceManager().sendInterface(275);
					player.getPackets().sendIComponentText(275, 1, "Rue's Altar Quest");
					player.getPackets().sendIComponentText(275, 10, "");
					player.getPackets().sendIComponentText(275, 11,
							"Rue has asked you to head to the Essence mine<br> to collect some powerless essence. <br> With this essence you should use it on each Altar<br> to gain it's power in the following order;<br>"
							+ "<br><col=00ff00>Air, Water, Earth, Fire, Body, Cosmic, Chaos, Astral, Nature, Law,<br><col=00ff00> Death & Blood.</col>");
					player.getPackets().sendIComponentText(275, 12, "");
					player.getPackets().sendIComponentText(275, 13, "");
					player.getPackets().sendIComponentText(275, 14,
							"");
					player.getPackets().sendIComponentText(275, 15, "");
					player.getPackets().sendIComponentText(275, 16, "");
					player.getPackets().sendIComponentText(275, 17, "");
					player.getPackets().sendIComponentText(275, 18, "");
					player.getPackets().sendIComponentText(275, 19, "");
					player.getPackets().sendIComponentText(275, 20, "");
					break;
				} else if (player.ruesaltarprogress == 2) {
					player.getInterfaceManager().sendInterface(275);
					player.getPackets().sendIComponentText(275, 1, "Rue's Altar Quest");
					player.getPackets().sendIComponentText(275, 10, "");
					player.getPackets().sendIComponentText(275, 11, "Rue has asked you to offer the powerful essence to the Altar<br> in the Catacombs of Kourend.");
					player.getPackets().sendIComponentText(275, 12, "");
					player.getPackets().sendIComponentText(275, 13, "");
					player.getPackets().sendIComponentText(275, 14, "");
					player.getPackets().sendIComponentText(275, 15, "");
					player.getPackets().sendIComponentText(275, 16, "");
					player.getPackets().sendIComponentText(275, 17, "");
					player.getPackets().sendIComponentText(275, 18, "");
					player.getPackets().sendIComponentText(275, 19, "");
					player.getPackets().sendIComponentText(275, 20, "");
					break;
				} else if (player.ruesaltarprogress == 3) {
					player.getInterfaceManager().sendInterface(275);
					player.getPackets().sendIComponentText(275, 1, "Rue's Altar Quest");
					player.getPackets().sendIComponentText(275, 10, "");
					player.getPackets().sendIComponentText(275, 11,
							"The essence has exploded,<br> you should probably speak to Rue about this.");
					player.getPackets().sendIComponentText(275, 12, "");
					player.getPackets().sendIComponentText(275, 13, "");
					player.getPackets().sendIComponentText(275, 14, "");
					player.getPackets().sendIComponentText(275, 15, "");
					player.getPackets().sendIComponentText(275, 16, "");
					player.getPackets().sendIComponentText(275, 17, "");
					player.getPackets().sendIComponentText(275, 18, "");
					player.getPackets().sendIComponentText(275, 19, "");
					player.getPackets().sendIComponentText(275, 20, "");
					break;
				} else if (player.ruesaltarprogress == 4) {
					player.getInterfaceManager().sendInterface(275);
					player.getPackets().sendIComponentText(275, 1, "Rue's Altar Quest");
					player.getPackets().sendIComponentText(275, 10, "");
					player.getPackets().sendIComponentText(275, 11, "Quest Complete.");
					player.getPackets().sendIComponentText(275, 12, "");
					player.getPackets().sendIComponentText(275, 13,
							"");
					player.getPackets().sendIComponentText(275, 14, "");
					player.getPackets().sendIComponentText(275, 15, "");
					player.getPackets().sendIComponentText(275, 16, "");
					player.getPackets().sendIComponentText(275, 17, "");
					player.getPackets().sendIComponentText(275, 18, "");
					player.getPackets().sendIComponentText(275, 19, "");
					player.getPackets().sendIComponentText(275, 20, "");
					break;
				} else if (player.dwarvenpart == 5) {
					player.getInterfaceManager().sendInterface(275);
					player.getPackets().sendIComponentText(275, 1, "Rue's Altar Quest");
					player.getPackets().sendIComponentText(275, 10, "");
					player.getPackets().sendIComponentText(275, 11, "Quest complete.");
					player.getPackets().sendIComponentText(275, 12, "");
					player.getPackets().sendIComponentText(275, 13, "");
					player.getPackets().sendIComponentText(275, 14, "");
					player.getPackets().sendIComponentText(275, 15, "");
					player.getPackets().sendIComponentText(275, 16, "");
					player.getPackets().sendIComponentText(275, 17, "");
					player.getPackets().sendIComponentText(275, 18, "");
					player.getPackets().sendIComponentText(275, 19, "");
					player.getPackets().sendIComponentText(275, 20, "");
					break;
				}
			case 72:
				if (player.starteddwarvenquest != true) {
					player.getInterfaceManager().sendInterface(275);
					player.getPackets().sendIComponentText(275, 1, "The Dwarven Discovery Quest");
					player.getPackets().sendIComponentText(275, 10, "");
					player.getPackets().sendIComponentText(275, 11, "Speak to the Head dwarf at the Dwarven mine.");
					player.getPackets().sendIComponentText(275, 12, "<u>Requirements</u>");
					player.getPackets().sendIComponentText(275, 13, "95 mining.");
					player.getPackets().sendIComponentText(275, 14, "-=-Tips-=-");
					player.getPackets().sendIComponentText(275, 15, "Use the 'Quest guide'");
					player.getPackets().sendIComponentText(275, 16,
							"The lodestone works, remember to take full use of it.");
					player.getPackets().sendIComponentText(275, 17, "");
					player.getPackets().sendIComponentText(275, 18, "");
					player.getPackets().sendIComponentText(275, 19, "");
					player.getPackets().sendIComponentText(275, 20, "");
					break;
				} else if (player.dwarvenpart == 1) {
					player.getInterfaceManager().sendInterface(275);
					player.getPackets().sendIComponentText(275, 1, "The Dwarven Discovery Quest");
					player.getPackets().sendIComponentText(275, 10, "");
					player.getPackets().sendIComponentText(275, 11,
							"The head dwarf has asked you to go down the hole,");
					player.getPackets().sendIComponentText(275, 12, "but you cannot fit.");
					player.getPackets().sendIComponentText(275, 13, "");
					player.getPackets().sendIComponentText(275, 14,
							"You should go to a Tower of Wizards and fine some help!");
					player.getPackets().sendIComponentText(275, 15, "");
					player.getPackets().sendIComponentText(275, 16, "");
					player.getPackets().sendIComponentText(275, 17, "");
					player.getPackets().sendIComponentText(275, 18, "");
					player.getPackets().sendIComponentText(275, 19, "");
					player.getPackets().sendIComponentText(275, 20, "");
					break;
				} else if (player.dwarvenpart == 2) {
					player.getInterfaceManager().sendInterface(275);
					player.getPackets().sendIComponentText(275, 1, "The Dwarven Discovery Quest");
					player.getPackets().sendIComponentText(275, 10, "");
					player.getPackets().sendIComponentText(275, 11, "Professor Onglewip has asked for some help.");
					player.getPackets().sendIComponentText(275, 12, "You should head to the following places;");
					player.getPackets().sendIComponentText(275, 13, "");
					player.getPackets().sendIComponentText(275, 14, "Speak to Iffie in Varrock.");
					player.getPackets().sendIComponentText(275, 15, "Speak to Charlie the cook in Brimhaven.");
					player.getPackets().sendIComponentText(275, 16, "Speak to Raneal in Al Kharid.");
					player.getPackets().sendIComponentText(275, 17, "");
					player.getPackets().sendIComponentText(275, 18, "");
					player.getPackets().sendIComponentText(275, 19, "");
					player.getPackets().sendIComponentText(275, 20, "");
					break;
				} else if (player.dwarvenpart == 3) {
					player.getInterfaceManager().sendInterface(275);
					player.getPackets().sendIComponentText(275, 1, "The Dwarven Discovery Quest");
					player.getPackets().sendIComponentText(275, 10, "");
					player.getPackets().sendIComponentText(275, 11,
							"Professor Onglewip has given you a teleport crystal.");
					player.getPackets().sendIComponentText(275, 12, "The crystal doesn't seem to respond.");
					player.getPackets().sendIComponentText(275, 13, "");
					player.getPackets().sendIComponentText(275, 14, "Speak to the head dwarf, he may be able to help.");
					player.getPackets().sendIComponentText(275, 15, "");
					player.getPackets().sendIComponentText(275, 16, "");
					player.getPackets().sendIComponentText(275, 17, "");
					player.getPackets().sendIComponentText(275, 18, "");
					player.getPackets().sendIComponentText(275, 19, "");
					player.getPackets().sendIComponentText(275, 20, "");
					break;
				} else if (player.dwarvenpart == 4) {
					player.getInterfaceManager().sendInterface(275);
					player.getPackets().sendIComponentText(275, 1, "The Dwarven Discovery Quest");
					player.getPackets().sendIComponentText(275, 10, "");
					player.getPackets().sendIComponentText(275, 11, "The head dwarf has fixed the crystal.");
					player.getPackets().sendIComponentText(275, 12, "");
					player.getPackets().sendIComponentText(275, 13,
							"Head over to the mine and grab him some of that special ore.");
					player.getPackets().sendIComponentText(275, 14, "");
					player.getPackets().sendIComponentText(275, 15, "");
					player.getPackets().sendIComponentText(275, 16, "");
					player.getPackets().sendIComponentText(275, 17, "");
					player.getPackets().sendIComponentText(275, 18, "");
					player.getPackets().sendIComponentText(275, 19, "");
					player.getPackets().sendIComponentText(275, 20, "");
					break;
				} else if (player.dwarvenpart == 5) {
					player.getInterfaceManager().sendInterface(275);
					player.getPackets().sendIComponentText(275, 1, "The Dwarven Discovery Quest");
					player.getPackets().sendIComponentText(275, 10, "");
					player.getPackets().sendIComponentText(275, 11, "Quest complete.");
					player.getPackets().sendIComponentText(275, 12, "");
					player.getPackets().sendIComponentText(275, 13, "");
					player.getPackets().sendIComponentText(275, 14, "");
					player.getPackets().sendIComponentText(275, 15, "");
					player.getPackets().sendIComponentText(275, 16, "");
					player.getPackets().sendIComponentText(275, 17, "");
					player.getPackets().sendIComponentText(275, 18, "");
					player.getPackets().sendIComponentText(275, 19, "");
					player.getPackets().sendIComponentText(275, 20, "");
					break;
				}
			case 73:

				if (player.spokeToWarrior == false && player.spokeToShamus == false) {
					player.getInterfaceManager().sendInterface(275);
					player.getPackets().sendIComponentText(275, 1, "Lost-City Quest");
					player.getPackets().sendIComponentText(275, 10, "");
					player.getPackets().sendIComponentText(275, 11, "Speak to the Warrior south east of the entrance to Lumbridge Swamp");
					player.getPackets().sendIComponentText(275, 12, "<u>Requirements</u>");
					player.getPackets().sendIComponentText(275, 13, "<col=ffff00>31 Crafting, 36 Woodcutting</col>");
					player.getPackets().sendIComponentText(275, 14, "-=-Tips-=-");
					player.getPackets().sendIComponentText(275, 15,
							"Use the 'Quest Tab' teleports to your advantage.");
					player.getPackets().sendIComponentText(275, 16,
							"The lodestone works, remember to take full use of it.");
					player.getPackets().sendIComponentText(275, 17,
							"You will need the skills required to complete the quest");
					player.getPackets().sendIComponentText(275, 18,
							"The Monk Of Entrana removes everything in your inventory.");
					player.getPackets().sendIComponentText(275, 19, "");
					player.getPackets().sendIComponentText(275, 20, "");
					break;
				} else if (player.spokeToWarrior == true && player.spokeToShamus == false) {
					player.getInterfaceManager().sendInterface(275);
					player.getPackets().sendIComponentText(275, 1, "Lost-City Quest");
					player.getPackets().sendIComponentText(275, 10, "");
					player.getPackets().sendIComponentText(275, 11,
							"Shamus appears to be in one of the trees around this location.");
					player.getPackets().sendIComponentText(275, 12,
							"The Warrior told me the tree displays 'Chop Tree'");
					player.getPackets().sendIComponentText(275, 13, "");
					player.getPackets().sendIComponentText(275, 14, "-=-Tips-=-");
					player.getPackets().sendIComponentText(275, 15,
							"Use the 'Quest Tab' teleports to your advantage.");
					player.getPackets().sendIComponentText(275, 16,
							"The lodestone works, remember to take full use of it.");
					player.getPackets().sendIComponentText(275, 17,
							"You will need the skills required to complete the quest");
					player.getPackets().sendIComponentText(275, 18,
							"The Monk Of Entrana removes everything in your inventory.");
					player.getPackets().sendIComponentText(275, 19, "");
					player.getPackets().sendIComponentText(275, 20, "");
					break;
				} else if (player.spokeToWarrior == true && player.spokeToShamus == true
						&& player.spokeToMonk != true) {
					player.getInterfaceManager().sendInterface(275);
					player.getPackets().sendIComponentText(275, 1, "Lost-City Quest");
					player.getPackets().sendIComponentText(275, 10, "");
					player.getPackets().sendIComponentText(275, 11,
							"I should go find the Monk of Entrana, Who is located at Port Sarim.");
					player.getPackets().sendIComponentText(275, 12, "");
					player.getPackets().sendIComponentText(275, 13, "");
					player.getPackets().sendIComponentText(275, 14, "-=-Tips-=-");
					player.getPackets().sendIComponentText(275, 15,
							"Use the 'Quest Tab' teleports to your advantage.");
					player.getPackets().sendIComponentText(275, 16,
							"The lodestone works, remember to take full use of it.");
					player.getPackets().sendIComponentText(275, 17,
							"You will need the skills required to complete the quest");
					player.getPackets().sendIComponentText(275, 18,
							"The Monk Of Entrana removes everything in your inventory.");
					player.getPackets().sendIComponentText(275, 19, "");
					player.getPackets().sendIComponentText(275, 20, "");
					break;
				} else if (player.spokeToWarrior == true && player.spokeToShamus == true
						&& player.spokeToMonk == true && player.lostCity == 0) {
					player.getInterfaceManager().sendInterface(275);
					player.getPackets().sendIComponentText(275, 1, "Lost-City Quest");
					player.getPackets().sendIComponentText(275, 10,
							"The other side of Entrana is a ladder which leads to a cave");
					player.getPackets().sendIComponentText(275, 11,
							"I should go down the ladder and search for the dramen tree.");
					player.getPackets().sendIComponentText(275, 12,
							"In order to chop the dramen tree I must have a axe.");
					player.getPackets().sendIComponentText(275, 13, "The zombies must drop a axe of some sort.");
					player.getPackets().sendIComponentText(275, 14, "-=-Tips-=-");
					player.getPackets().sendIComponentText(275, 15,
							"Use the 'Quest Tab' teleports to your advantage.");
					player.getPackets().sendIComponentText(275, 16,
							"The lodestone works, remember to take full use of it.");
					player.getPackets().sendIComponentText(275, 17,
							"You will need the skills required to complete the quest");
					player.getPackets().sendIComponentText(275, 18,
							"The Monk Of Entrana removes everything in your inventory.");
					player.getPackets().sendIComponentText(275, 19, "");
					player.getPackets().sendIComponentText(275, 20, "");
					break;
				} else if (player.lostCity >= 1) {
					player.sendMessage(""+player.lostCity);
					player.getInterfaceManager().sendInterface(1244);
					player.getPackets().sendIComponentText(1244, 27, "Lost-City Quest");
					player.getPackets().sendGlobalString(359,
							"<br>Congratulations you have completed the quest; Lost City</br> <br>Such a pain, but it's over now :)</br> <br>You received some Dungeoneering xp.</br>");

					break;

				}
			}
		} else if (interfaceId == 583) {
			switch (componentId) {
			case 50:
				if (player.getHpTracker() != true) {
					player.hptracker = true;
					player.getPackets().sendIComponentText(583, 50, "Hp Tracker "
							+ (player.hptracker == true ? "<col=00FF00>ON</col>" : "<col=ff0000>OFF</col>" + ""));
					player.sendMessage("Your hp tracker is now "
							+ (player.getHpTracker() == true ? "<col=00FF00>ON</col>" : "<col=ff0000>OFF</col>" + ""));
					break;
				} else {
					player.hptracker = false;
					player.getPackets().sendIComponentText(583, 50, "Hp Tracker "
							+ (player.hptracker == true ? "<col=00FF00>ON</col>" : "<col=ff0000>OFF</col>" + ""));
					player.sendMessage("Your hp tracker is now "
							+ (player.getHpTracker() == true ? "<col=00FF00>ON</col>" : "<col=ff0000>OFF</col>" + ""));
					break;
				}
			case 51:
				player.setTipsOff(!player.isTipsOff());
				player.getPackets().sendGameMessage("You have turned random server tips "
						+ (player.isTipsOff() ? "<col=00FF00>ON</col>" : "<col=ff0000>OFF</col>" + ""));
				player.getPackets().sendIComponentText(583, 51,
						"Server tips " + (player.isTipsOff() ? "<col=00FF00>ON</col>" : "<col=ff0000>OFF</col>" + ""));
				break;
			case 52:
				player.setYellOff(!player.isYellOff());
				player.getPackets().sendGameMessage("You have turned "
						+ (player.isYellOff() ? "<col=ff0000>OFF</col>" : "<col=00FF00>ON</col>") + " yell.");
				player.getPackets().sendIComponentText(583, 52,
						"Yell " + (player.isYellOff() ? "<col=ff0000>OFF</col>" : "<col=00FF00>ON</col>" + ""));
				break;
			case 53:
				player.setXpLocked(player.isXpLocked() ? false : true);
				player.getPackets()
						.sendGameMessage("You have "
								+ (player.isXpLocked() ? "<col=ff0000>LOCKED</col>" : "<col=00FF00>UNLOCKED</col>")
								+ " your xp.");
				player.getPackets().sendIComponentText(583, 53, "Your experience gain is "
						+ (player.isXpLocked() ? "<col=ff0000>LOCKED</col>" : "<col=00FF00>UNLOCKED</col>"));
				break;
			case 54:
				if (player.getDisplayDrops() != true) {
					player.displaydrops = true;
					player.getPackets().sendIComponentText(583, 54, "Display drops "
							+ (player.displaydrops == true ? "<col=00FF00>ON</col>" : "<col=ff0000>OFF</col>" + ""));
					player.sendMessage("Display drops is now "
							+ (player.getDisplayDrops() == true ? "<col=00FF00>ON</col>" : "<col=ff0000>OFF</col>" + ""));
					break;
				} else {
					player.displaydrops = false;
					player.getPackets().sendIComponentText(583, 54, "Display drops "
							+ (player.displaydrops == true ? "<col=00FF00>ON</col>" : "<col=ff0000>OFF</col>" + ""));
					player.sendMessage("Display drops is now "
							+ (player.getDisplayDrops() == true ? "<col=00FF00>ON</col>" : "<col=ff0000>OFF</col>" + ""));
					break;
				}
				case 55:
					if (player.getPotTimers() != true) {
						player.pottimers = true;
						player.getPackets().sendIComponentText(583, 55, "Potion timers "
								+ (player.pottimers == true ? "<col=00FF00>ON</col>" : "<col=ff0000>OFF</col>" + ""));
						player.sendMessage("Potion timers are now "
								+ (player.getPotTimers() == true ? "<col=00FF00>ON</col>" : "<col=ff0000>OFF</col>" + ""));
						break;
					} else {
						player.pottimers = false;
						player.getPackets().sendIComponentText(583, 55, "Potion timers "
								+ (player.pottimers == true ? "<col=00FF00>ON</col>" : "<col=ff0000>OFF</col>" + ""));
						player.sendMessage("Potion timers are now "
								+ (player.getPotTimers() == true ? "<col=00FF00>ON</col>" : "<col=ff0000>OFF</col>" + ""));
						break;

				}
				case 56:
					if (player.instanceclosed != true) {
						player.instanceclosed = true;
						player.getPackets().sendIComponentText(583, 56, "Instance entry "
								+ (player.instanceclosed == true ? "<col=00FF00>OPEN</col>" : "<col=ff0000>CLOSED</col>" + ""));
						player.sendMessage("Instance entry is now "
								+ (player.instanceclosed == true ? "<col=00FF00>OPEN</col>" : "<col=ff0000>CLOSED</col>" + ""));
						break;
					} else {
						player.instanceclosed = false;
						player.getPackets().sendIComponentText(583, 56, "Instance entry "
								+ (player.instanceclosed == true ? "<col=00FF00>OPEN</col>" : "<col=ff0000>CLOSED</col>" + ""));
						player.sendMessage("Instance entry is now "
								+ (player.instanceclosed == true ? "<col=00FF00>OPEN</col>" : "<col=ff0000>CLOSED</col>" + ""));
						break;

				}
				case 57:
					if (player.safealchemy != true) {
						player.safealchemy = true;
						player.getPackets().sendIComponentText(583, 57, "Safe Alchemy "
								+ (player.safealchemy == true ? "<col=ff0000>OFF</col>" : "<col=00FF00>ON</col>" + ""));
						player.sendMessage("Safe Alchemy is now "
								+ (player.safealchemy == true ? "<col=ff0000>OFF</col>" : "<col=00FF00>ON</col>" + ""));
						break;
					} else {
						player.safealchemy = false;
						player.getPackets().sendIComponentText(583, 57, "Safe Alchemy "
								+ (player.safealchemy == true ? "<col=ff0000>OFF</col>" : "<col=00FF00>ON</col>" + ""));
						player.sendMessage("Safe Alchemy is now "
								+ (player.safealchemy == true ? "<col=ff0000>OFF</col>" : "<col=00FF00>ON</col>" + ""));
						break;

				}
				case 58:
					player.getDialogueManager().startDialogue("LootBeamManagerD");
					player.getPackets().sendIComponentText(583, 58, "Loot Beam value: <col=00ff00>"+Utils.format(player.setLootBeam)+"</col>");
					break;
				case 59:
					if (player.publicProfile) {
						player.publicProfile = false;
						player.sendMessage("You have set your profile to private.");
					} else {
						player.publicProfile = true;
						player.sendMessage("You have set your profile to public.");
					}
					player.getPackets().sendIComponentText(583, 59, "Allow Profile Viewing: "+ (player.publicProfile == true ? "<col=00FF00>ON</col>" : "<col=ff0000>OFF</col>"));
					break;


			}

		} else if (interfaceId == 1284 || interfaceId == 628) {
			// System.out.println("1");
			if (player.getTemporaryAttributtes().get("POS") != null) {
				PlayerOwnedShop shop = (PlayerOwnedShop) player.getTemporaryAttributtes().get("POS");
				if (shop.getPlayer() != null) {
					shop.handleButtonClick(player, interfaceId, componentId, slotId, slotId2, packetId);
				}
				return;
			}

		} else if (interfaceId == 1156) {

				switch (componentId) {

				case 158:
				if (player.getDominionTower().getKilledBossesCount() < 250) {
					player.sendMessage("You must have atleast 250 DT boss kills to buy these.");
						return;
					}
					player.getInventory().addItem(22360, 1);
					break;
				case 162:
				if (player.getDominionTower().getKilledBossesCount() < 250) {
					player.sendMessage("You must have atleast 250 DT boss kills to buy these.");
						return;
					}
					player.getInventory().addItem(22368, 1);
					break;
					case 166:
				if (player.getDominionTower().getKilledBossesCount() < 250) {
					player.sendMessage("You must have atleast 250 DT boss kills to buy these.");
							return;
						}
						player.getInventory().addItem(22364, 1);
						break;
				
			}
		} else if (interfaceId == 432) {
					switch (componentId) {
					case 14:
						EnchantBolt.enchant(player, Bolt.OPAL);
						break;
					case 29:
						EnchantBolt.enchant(player, Bolt.SAPPHIRE);
						break;
					case 18:
						EnchantBolt.enchant(player, Bolt.JADE);
						break;
					case 22:
						EnchantBolt.enchant(player, Bolt.PEARL);
						break;
					case 32:
						EnchantBolt.enchant(player, Bolt.EMERALD);
						break;
					case 26:
						EnchantBolt.enchant(player, Bolt.REDTOPAZ);
						break;
					case 35:
						EnchantBolt.enchant(player, Bolt.RUBY);
						break;
					case 38:
						EnchantBolt.enchant(player, Bolt.DIAMOND);
						break;
					case 41:
						EnchantBolt.enchant(player, Bolt.DRAGONSTONE);
						break;
					case 44:
						EnchantBolt.enchant(player, Bolt.ONYX);
						break;
					}

		} else if (interfaceId == 506) {

			switch (componentId) {


			case 2: //teleports
				if (World.TempleofLight(player) || World.TheCalamity(player)) {
					player.sendMessage(Colors.red + "You cannot do this here.");
					break;
				}
				if (Wilderness.isAtWild(player)) {
					player.sendMessage(Colors.red + "You cannot do this here. If you're below 20 use ::home.");
					break;
				}
				if (DuelControler.isAtDuelArena(player)) {
					player.sendMessage(Colors.red + "Please leave the building before doing this!");
					break;
				}
				if (player.getLastTeleInter() == 0) {
					TeleportsInterface.sendBossesInterface(player);
				} else if (player.getLastTeleInter() == 1) {
					TeleportsInterface.sendMonstersInterface(player);
				} else if (player.getLastTeleInter() == 2) {
					TeleportsInterface.sendRaidsInterface(player);
				} else if (player.getLastTeleInter() == 3) {
					TeleportsInterface.sendMinigamesInterface(player);
				} else if (player.getLastTeleInter() == 4) {
					TeleportsInterface.sendSkillingInterface(player);
				} else if (player.getLastTeleInter() == 5) {
					TeleportsInterface.sendAreaInterface(player);
				}
				//DailyMoneyMakersInterface.OpenInterface(player);
				// TopVoters.checkRank(player);
				// TopVoters.showRanks(player);
				break;
			case 4:// drop viewer
				//player.getInterfaceManager().sendInterface(3221);
				//NpcDropViewer.sendDrops(player, 1615);
				NpcDropViewer.openInterface(player);
				break;
			case 14:
				PetClaimInterface.OpenInterface(player);
				break;
			case 6:// Quests
				player.getTemporaryAttributtes().remove("shopsinterface");
				player.getInterfaceManager().sendInterface(72);
				player.getPackets().sendIComponentText(72, 55, "Harmony Quests");
				player.getPackets().sendIComponentText(72, 31, "");
				player.getPackets().sendIComponentText(72, 36, "Lost City");
				player.getPackets().sendIComponentText(72, 32, "");
				player.getPackets().sendIComponentText(72, 33, "");
				player.getPackets().sendIComponentText(72, 34, "");
				player.getPackets().sendIComponentText(72, 35, "");
				player.getPackets().sendIComponentText(72, 33, "");
				player.getPackets().sendIComponentText(72, 37, "The Dwarven Discovery");
				player.getPackets().sendIComponentText(72, 38, "Economic Breakdown");
				player.getPackets().sendIComponentText(72, 39, "Rue's Altar");
				player.getPackets().sendIComponentText(72, 40, "");
				break;
				
			case 18:
				TitlesManager.sendInterface(player);
				//TitlesInterface.SendInterface(player);
				break;

			case 10: // Killcounts
				WorldEventInformationInterface.SendInterface(player);
				return;
			case 8:// Perks
				player.getInterfaceManager().sendInterface(959);
				for (int i = 0; i < 54; i++) {
					player.getPackets().sendIComponentText(959, i, "");
				}
				player.getPackets().sendIComponentText(959, 5, "<col=8A2BE2>Perks! Green is active, red is inactive.");

				player.getPackets().sendIComponentText(959, 30, "" + (player.ddxp == true ? "<col=00ff00>" : "<col=ff0000>") + "Double Experience perk");
				player.getPackets().sendIComponentText(959, 31, "" + (player.avaperk == true ? "<col=00ff00>" : "<col=ff0000>") + "Hidden Ava perk");
				player.getPackets().sendIComponentText(959, 32, "" + (player.ckeyperk == true ? "<col=00ff00>" : "<col=ff0000>") + "Crystal Luck perk");
				player.getPackets().sendIComponentText(959, 33, "" + (player.gwdperk == true ? "<col=00ff00>" : "<col=ff0000>") + "Free Entry perk");
				player.getPackets().sendIComponentText(959, 34, "" + (player.dungperk == true ? "<col=00ff00>" : "<col=ff0000>") + "Master of Dungeons perk");
				player.getPackets().sendIComponentText(959, 35, "" + (player.petperk == true ? "<col=00ff00>" : "<col=ff0000>") + "Pet Hunter perk");
				player.getPackets().sendIComponentText(959, 36, "" + (player.farmingperk == true ? "<col=00ff00>" : "<col=ff0000>") + "Green Fingers perk");
				player.getPackets().sendIComponentText(959, 37, "" + (player.prayerperk == true ? "<col=00ff00>" : "<col=ff0000>") + "Blessed perk");
				player.getPackets().sendIComponentText(959, 38, "" + (player.potionperk == true ? "<col=00ff00>" : "<col=ff0000>") + "One True Potion perk");
				player.getPackets().sendIComponentText(959, 39, "" + (player.fishingperk == true ? "<col=00ff00>" : "<col=ff0000>") + "Smelly Fish perk");
				player.getPackets().sendIComponentText(959, 40, "" + (player.miningperk == true ? "<col=00ff00>" : "<col=ff0000>") + "Quarrymaster perk");
				player.getPackets().sendIComponentText(959, 41, "" + (player.axeperk == true ? "<col=00ff00>" : "<col=ff0000>") + "Axe Master perk");
				player.getPackets().sendIComponentText(959, 42, "" + (player.cookingperk == true ? "<col=00ff00>" : "<col=ff0000>") + "Master Chef perk");
				player.getPackets().sendIComponentText(959, 43, "" + (player.antifireperk == true ? "<col=00ff00>" : "<col=ff0000>") + "Antifire perk");
				player.getPackets().sendIComponentText(959, 44, "" + (player.runenergyperk == true ? "<col=00ff00>" : "<col=ff0000>") + "Stamina Boost perk");
				player.getPackets().sendIComponentText(959, 45, "" + (player.playerwarsperk == true ? "<col=00ff00>" : "<col=ff0000>") + "Player Wars perk");
				player.getPackets().sendIComponentText(959, 46, "" + (player.eventhealperk == true ? "<col=00ff00>" : "<col=ff0000>") + "Food heals 110% perk");
				player.getPackets().sendIComponentText(959, 47, "" + (player.hustlerperk == true ? "<col=00ff00>" : "<col=ff0000>") + "Hustler perk");
				player.getPackets().sendIComponentText(959, 48, "" + (player.superchargedperk == true ? "<col=00ff00>" : "<col=ff0000>") + "Supercharged perk");
				player.getPackets().sendIComponentText(959, 49, "" + (player.coincollectorperk == true ? "<col=00ff00>" : "<col=ff0000>") + "Coin Collector perk");
				player.getPackets().sendIComponentText(959, 50, "" + (player.feelsfamiliarperk == true ? "<col=00ff00>" : "<col=ff0000>") + "Feels Familiar perk");
				player.getPackets().sendIComponentText(959, 51, "" + (player.rodeffectincrease == true ? "<col=00ff00>" : "<col=ff0000>") + "ROD damage increase perk");
				player.sendMessage("You have the following Daily perks active:");
				if (player.dailyperkamount >= 1) {
					player.sendMessage(Colors.green + "+0.5% Drop rate bonus.");
				}
				if (player.dailyperkamount >= 2) {
					player.sendMessage(Colors.green + "+5% Experience bonus.");
				}
				if (player.dailyperkamount >= 3) {
					player.sendMessage(Colors.green + "+1.5% Drop rate bonus.");
				}
				if (player.dailyperkamount >= 4) {
					player.sendMessage(Colors.green + "+5% Experience bonus.");
				}
				if (player.dailyperkamount >= 5) {
					player.sendMessage(Colors.green + "+1.5% Drop rate bonus.");
				}
				if (player.dailyperkamount >= 6) {
					player.sendMessage(Colors.green + "+2.5% Drop rate bonus.");
				}
				if (player.dailyperkamount >= 7) {
					player.sendMessage(Colors.green + "Free boss instances.");
				}
				break;
			case 12:// POS
				CosmeticsHandler.openCosmeticsHandler(player);
				break;
			case 16:// settings
				player.skilltelingcomponent = false;
				player.getInterfaceManager().sendInterface(583);
				for (int i = 0; i < 82; i++) {
					player.getPackets().sendIComponentText(583, i, "");
				}
				player.getPackets().sendIComponentText(583, 14, "Harmony Settings");
				player.getPackets().sendIComponentText(583, 50, "Hp Tracker "
						+ (player.hptracker == true ? "<col=00FF00>ON</col>" : "<col=ff0000>OFF</col>" + ""));
				player.getPackets().sendIComponentText(583, 51,
						"Server tips " + (player.isTipsOff() ? "<col=00FF00>OFF</col>" : "<col=ff0000>ON</col>" + ""));
				player.getPackets().sendIComponentText(583, 52,
						"Yell " + (player.isYellOff() ? "<col=ff0000>OFF</col>" : "<col=00FF00>ON</col>" + ""));
				player.getPackets().sendIComponentText(583, 53, "Your experience gain is "
						+ (player.isXpLocked() ? "<col=ff0000>LOCKED</col>" : "<col=00FF00>UNLOCKED</col>"));
				player.getPackets().sendIComponentText(583, 54, "Display drops is currently "
						+ (player.displaydrops == true ? "<col=00FF00>ON</col>" : "<col=ff0000>OFF</col>"));
				player.getPackets().sendIComponentText(583, 55, "Potion timers are currently " + (player.pottimers == true ? "<col=00FF00>ON</col>" : "<col=ff0000>OFF</col>"));
				player.getPackets().sendIComponentText(583, 56, "Instance entry is currently " + (player.instanceclosed == true ? "<col=00FF00>OPEN</col>" : "<col=ff0000>CLOSED</col>"));
				player.getPackets().sendIComponentText(583, 57, "Safe Alchemy is currently " + (player.safealchemy == true ? "<col=ff0000>OFF</col>" : "<col=00FF00>ON</col>"));
				player.getPackets().sendIComponentText(583, 58, "Loot Beam value: "+Utils.format(player.setLootBeam)+"");
				player.getPackets().sendIComponentText(583, 59, "Allow Profile Viewing: "+ (player.publicProfile == true ? "<col=00FF00>ON</col>" : "<col=ff0000>OFF</col>"));
				break;
			}
			if (interfaceId == 375) {
				player.getInterfaceManager().closeInventoryInterface();
				player.unlock();
				player.getAppearence().transformIntoNPC(-1);
				player.getTemporaryAttributtes().remove("RingNPC");
				player.setNextAnimation(new Animation(14884));
				return;
			}
		} else if (interfaceId == 394 || interfaceId == 396) {
			if (componentId == 11) {
				if (player.getHouse() == null
						|| !(player.getControlerManager().getControler() instanceof HouseControler)) {
					return;
				}
				if (player.getTemporaryAttributtes().get("GetSlotBuild") != null) {
					player.getHouse().buildDungeonStairs(slotId,
							(Builds) player.getTemporaryAttributtes().get("GetSlotBuild"),
							(RoomReference) player.getTemporaryAttributtes().get("GetNewRoom"));
				}
				player.getHouse().build(slotId);
			}
		} else if (interfaceId == 398) {
			if (componentId == 19) {
				player.getInterfaceManager().sendSettings();
			} else if (componentId == 15 || componentId == 1) {
				if (player.getHouse() == null) {
					return;
				}
				player.getHouse().setBuildMode(componentId == 15);
			} else if (componentId == 25 || componentId == 26) {
				if (player.getHouse() == null) {
					return;
				}
				player.getHouse().setArriveInPortal(componentId == 25);
			} else if (componentId == 27) {
				if (player.getHouse() == null
						|| !(player.getControlerManager().getControler() instanceof HouseControler)) {
					return;
				}
				player.getHouse().expelGuests();
			} else if (componentId == 29) {
				if (player.getHouse() == null
						|| !(player.getControlerManager().getControler() instanceof HouseControler)) {
					return;
				}
				House.leaveHouse(player);
			}
		} else if (interfaceId == 402) {
			if (componentId >= 93 && componentId <= 115) {
				player.getHouse().createRoom(componentId - 93);
			}
		} else if (interfaceId == 1019) {
			switch (componentId) {
			case 9:
				DropLogInterface.OpenInterface(player);
				break;
			case 16:// cosmetics
				// CosmeticsHandler.openCosmeticsHandler(player);
				// player.sendMessage("This is currently disabled.");
//				WealthyPlayers.checkRank(player);
//				WealthyPlayers.showRanks(player);
			/*	if (!player.isDonator()) {
					return;
				}
				String currency = player.getCurrencyFormat(player.calculateNetworth());
				player.setNextForceTalk(new ForceTalk("My bank value is " + Utils.format(player.calculateNetworth())
						+ " *<col=00ff00>" + currency + "</col> * GP based on the price checker</col>"));
				player.sendMessage("<col=ff0000>Your bank value is " + Utils.format(player.calculateNetworth()) + " *<col=000000>"
						+ currency + "</col>* GP based on the price checker</col>");*/
				player.getInterfaceManager().sendInterface(275);
						for (int i = 0; i < 310; i++) {
							player.getPackets().sendIComponentText(275, i, "");
						}
						player.getPackets().sendIComponentText(275, 1, "Completionist Cape Requirements");
						player.getPackets().sendIComponentText(275, 10, "");
						player.getPackets().sendIComponentText(275, 11,
								"" + (Skills.getTotalLevel(player) == 2496 ? "<str>" : "")
										+ "<shad=DA1010>Achieve 2496 total level");
						player.getPackets().sendIComponentText(275, 12, "" + (player.logsburnt >= 2500 ? "<str>" : "")
								+ "<shad=DA1010>Burn 2500 magic logs : " + player.logsburnt + "");
						player.getPackets().sendIComponentText(275, 13, "" + (player.oremined >= 500 ? "<str>" : "")
								+ "<shad=DA1010>Mine 500 runite ore : " + player.oremined + "");
						player.getPackets().sendIComponentText(275, 14, "" + (player.logscut >= 2500 ? "<str>" : "")
								+ "<shad=DA1010>Cut 2500 yew logs : " + player.logscut + "");
						player.getPackets().sendIComponentText(275, 15, "" + (player.fishcaught >= 1000 ? "<str>" : "")
								+ "<shad=DA1010>Catch 1000 rocktails : " + player.fishcaught + "");
						player.getPackets().sendIComponentText(275, 16,
								"" + (player.lapsrun >= 500 ? "<str>" : "")
										+ "<shad=DA1010>Complete 500 advanced barbarian agility course laps : "
										+ player.lapsrun + "");
						player.getPackets().sendIComponentText(275, 17, "" + (player.slaytask >= 80 ? "<str>" : "")
								+ "<shad=DA1010>Complete 80 slayer tasks : " + player.slaytask + "");
						player.getPackets().sendIComponentText(275, 18,
								"" + (player.heistgamesplayed >= 3 ? "<str>" : "")
										+ "<shad=DA1010>Complete 3 Heists : " + player.heistgamesplayed + "");
						player.getPackets().sendIComponentText(275, 19, "" + (player.barsmelt >= 10 ? "<str>" : "")
								+ "<shad=DA1010>Smelt 10 ancient bars : " + player.barsmelt + "");
						player.getPackets().sendIComponentText(275, 20,
								"" + (player.getDominionTower().killedBossesCount >= 100 ? "<str>" : "")
										+ "<shad=DA1010>Kill 100 DT bosses : "
										+ player.getDominionTower().killedBossesCount + "");
						player.getPackets().sendIComponentText(275, 21,
								"" + (player.killedQueenBlackDragon ? "<str>" : "") + "<shad=DA1010>Kill the QBD.");
						player.getPackets().sendIComponentText(275, 22, "" + (player.completedFightKiln ? "<str>" : "")
								+ "<shad=DA1010>Complete the Fight Kiln.");
						player.getPackets().sendIComponentText(275, 23, "" + (player.completedFightCaves ? "<str>" : "")
								+ "<shad=DA1010>Complete the Fight Caves.");
						player.getPackets().sendIComponentText(275, 24,
								"" + (player.wonFightPits ? "<str>" : "") + "<shad=DA1010>Win the FightPits.");
						player.getPackets().sendIComponentText(275, 25, "" + (player.bandos >= 10 ? "<str>" : "")
								+ "<shad=DA1010>Kill 10 Bandos bosses : " + player.bandos + "");
						player.getPackets().sendIComponentText(275, 26, "" + (player.saradomin >= 10 ? "<str>" : "")
								+ "<shad=DA1010>Kill 10 Saradomin bosses : " + player.saradomin + "");
						player.getPackets().sendIComponentText(275, 27, "" + (player.zamorak >= 10 ? "<str>" : "")
								+ "<shad=DA1010>Kill 10 Zamorak bosses : " + player.zamorak + "");
						player.getPackets().sendIComponentText(275, 28, "" + (player.armadyl >= 10 ? "<str>" : "")
								+ "<shad=DA1010>Kill 10 Armadyl bosses : " + player.armadyl + "");
						player.getPackets().sendIComponentText(275, 29, "" + (AchievementDiaryInter.CompletedAllDiarys(player) ? "<str>" : "") + "<shad=DA1010>Complete Achievement Diaries to Elite.");
						player.getPackets().sendIComponentText(275, 30,
								"" + (player.royalcompmade ? "<str>" : "") + "<shad=DA1010>Create the royal crossbow.");
						player.getPackets().sendIComponentText(275, 31,
								"" + (player.CompletedAllQuests() ? "<str>" : "") + "<shad=DA1010>Completed all Quests.");
						player.getPackets().sendIComponentText(275,
										32, ""
												+ (player.isaverage || player.ishard || player.isheroic
														|| player.isIronman() ? "<str>" : "")
												+ "<shad=DA1010>Use any xp rate other than easy.");
						player.getPackets().sendIComponentText(275, 33, "" + (player.GazerKills >= 20 ? "<str>" : "")
								+ "<shad=DA1010>Kill 20 Night-Gazer bosses : " + player.GazerKills + "");
						player.getPackets().sendIComponentText(275, 34, "" + (player.cluescompleted >= 3 ? "<str>" : "")
								+ "<shad=DA1010>Complete atleast 3 clue scrolls : " + player.cluescompleted + "");
						player.getPackets().sendIComponentText(275, 36, "" + (player.pwamountcompleted >= 1 ? "<str>" : "")
								+ "<shad=DA1010>Complete Player wars atleast once : " + player.pwamountcompleted + "");
						player.getPackets().sendIComponentText(275, 38,
								"------------------------------------------------------------------------------------------------------- ");
						player.getPackets().sendIComponentText(275, 39,
								"<col=00FF00>Trimmed Completionist Cape Requirements!");
						player.getPackets().sendIComponentText(275, 40, "" + (player.ismusic >= 100 ? "<str>" : "")
								+ "<shad=00FF00>Discover 100 music tracks : " + player.ismusic + "");
						player.getPackets().sendIComponentText(275, 41, "" + (player.dunggkills >= 250 ? "<str>" : "")
								+ "<shad=00FF00>Complete 250 HL dung floors : " + player.dunggkills + "");
						player.getPackets().sendIComponentText(275, 42,
								"" + (player.getQuestManager().completedQuest(Quests.NOMADS_REQUIEM) ? "<str>" : "")
										+ "<shad=00FF00>Kill Nomad in his chamber.");
						player.getPackets().sendIComponentText(275, 43,
								"" + (player.isDonator() ? "<str>" : "") + "<shad=00FF00>Purchase Donator rank.");
						player.getPackets().sendIComponentText(275, 44, "" + (player.prestigeTokens >= 1 ? "<str>" : "")
								+ "<shad=00FF00>Prestige once : " + player.prestigeTokens + "");
						player.getPackets().sendIComponentText(275, 45,
								"" + (player.getDominionTower().killedBossesCount >= 250 ? "<str>" : "")
										+ "<shad=00FF00>Kill 250 DT Bosses : "
										+ player.getDominionTower().killedBossesCount + "");
						player.getPackets().sendIComponentText(275, 46, "" + (player.VoragoKills > 0 ? "<str>" : "")
								+ "<shad=00FF00>Unlock *The Defeater* title.");
						player.getPackets().sendIComponentText(275, 47,
								"" + (player.divine ? "<str>" : "") + "<shad=00FF00>Unlock the *Sigil* title.");
						player.getPackets().sendIComponentText(275, 48, "" + (player.kilnruns >= 3 ? "<str>" : "")
								+ "<shad=00FF00>Complete the fight kiln atleast 3 times : " + player.kilnruns + "");
						player.getPackets().sendIComponentText(275, 49,
								"" + (player.fbtitle ? "<str>" : "") + "<shad=00FF00>Unlock the *Final Boss* title.");
						player.getPackets().sendIComponentText(275, 50, "" + (player.Dboss >= 20 ? "<str>" : "")
								+ "<shad=00FF00>Kill 20 Donator bosses : " + player.Dboss + "");
						player.getPackets().sendIComponentText(275, 51,
								"" + (player.calamitytotalwavescomplete >= 1000 ? "<str>" : "")
										+ "<shad=00FF00>Complete 1,000 Calamity waves : "
										+ player.calamitytotalwavescomplete + "");
						player.getPackets().sendIComponentText(275, 52, "" + (AchievementDiaryInter.CompletedAllDiarysMaster(player) ? "<str>" : "") + "<shad=00FF00>Complete Achievement Diaries to Master.");
				break;

			case 18:// diary
				AchievementDiaryInter.OpenInterface(player);
				break;
			}

		} else if (interfaceId == 671) {
			if (player.getTemporaryAttributtes().get("drops") == Boolean.FALSE) {
				if (player.getFamiliar() == null || player.getFamiliar().getBob() == null) {
					return;
				}
			}
			if (componentId == 27) {
				if (packetId == WorldPacketsDecoder.ACTION_BUTTON1_PACKET) {

					if (player.getTemporaryAttributtes().get("drops") != null) {
						ViewNPCDrops.sendDropRateMessage(player, player.getNpcDrops().get(slotId), (int) player.getTemporaryAttributtes().get("drops"), slotId);
					} else {
						player.getFamiliar().getBob().removeItem(slotId, 1);
					}

				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET) {

					if (player.getTemporaryAttributtes().get("drops") != null) {
						ViewNPCDrops.sendPriceCheckMessage(player, player.getNpcDrops().get(slotId));
					} else {
						player.getFamiliar().getBob().removeItem(slotId, 5);
					}

				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON3_PACKET) {

					if (player.getTemporaryAttributtes().get("drops") != null) {
						player.getDialogueManager().startDialogue("Item Message", ItemExamines.getExamine(player.getNpcDrops().get(slotId)));
					} else {
						player.getFamiliar().getBob().removeItem(slotId, 10);
					}

				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON4_PACKET) {
					player.getFamiliar().getBob().removeItem(slotId, Integer.MAX_VALUE);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON5_PACKET) {
					player.getTemporaryAttributtes().put("bob_item_X_Slot", slotId);
					player.getTemporaryAttributtes().put("bob_isRemove", Boolean.TRUE);
					player.getPackets().sendRunScript(108, new Object[] { "Enter Amount:" });
				}
			} else if (componentId == 29) {
				if (player.getTemporaryAttributtes().get("drops") != null) {
					ViewNPCDrops.changeSortType(player, (int) player.getTemporaryAttributtes().get("drops"));
				} else {
					player.getFamiliar().takeBob();
				}
			}
		} else if (interfaceId == 916) {
			SkillsDialogue.handleSetQuantityButtons(player, componentId);
		} else if (interfaceId == 193) {
			if (componentId == 5) {
				player.getCombatDefinitions().switchShowCombatSpells();
			} else if (componentId == 7) {
				player.getCombatDefinitions().switchShowTeleportSkillSpells();
			} else if (componentId >= 9 && componentId <= 11) {
				player.getCombatDefinitions().setSortSpellBook(componentId - 9);
			} else if (componentId == 18) {
				player.getCombatDefinitions().switchDefensiveCasting();
			} else {
				Magic.processAncientSpell(player, componentId, packetId);
			}
		} else if (interfaceId == 430) {
			if (componentId == 5) {
				player.getCombatDefinitions().switchShowCombatSpells();
			} else if (componentId == 7) {
				player.getCombatDefinitions().switchShowTeleportSkillSpells();
			} else if (componentId == 9) {
				player.getCombatDefinitions().switchShowMiscallaneousSpells();
			} else if (componentId >= 11 & componentId <= 13) {
				player.getCombatDefinitions().setSortSpellBook(componentId - 11);
			} else if (componentId == 20) {
				player.getCombatDefinitions().switchDefensiveCasting();
			} else {
				Magic.processLunarSpell(player, componentId, packetId);
			}
		} else if (interfaceId == 261) {
			if (player.getInterfaceManager().containsInventoryInter()) {
				return;
			}
			if (componentId == 22) {
				if (player.getInterfaceManager().containsScreenInter()) {
					player.getPackets().sendGameMessage(
							"Please close the interface you have open before setting your graphic options.");
					return;
				}
				player.stopAll();
				player.getInterfaceManager().sendInterface(742);
			} else if (componentId == 12) {
				player.switchAllowChatEffects();
			} else if (componentId == 13) { // chat setup
				player.getInterfaceManager().sendSettings(982);
			} else if (componentId == 14) {
				player.switchMouseButtons();
			} else if (componentId == 24) {
				player.getInterfaceManager().sendSettings(429);
			} else if (componentId == 26) {
				player.getAdventurersLog().open();
			}
			switch (componentId) {
			case 6:

				player.getInterfaceManager().sendInterface(1158);

				player.getPackets().sendIComponentText(1158, 74, "Server Information");
				player.getPackets().sendIComponentText(1158, 8, "1");
				player.getPackets().sendIComponentText(1158, 13, "2");
				player.getPackets().sendIComponentText(1158, 18, "3");
				player.getPackets().sendIComponentText(1158, 23, "4");
				player.getPackets().sendIComponentText(1158, 28, "5");
				player.getPackets().sendIComponentText(1158, 33, "6");
				player.getPackets().sendIComponentText(1158, 38, "7");
				player.getPackets().sendIComponentText(1158, 43, "8");
				player.getPackets().sendIComponentText(1158, 48, "9");
				player.getPackets().sendIComponentText(1158, 53, "10");
				player.getPackets().sendIComponentText(1158, 9, "Server Name and Revision");
				player.getPackets().sendIComponentText(1158, 10, "" + Settings.SERVER_NAME + ". Revision 742/OSRS.");
				player.getPackets().sendIComponentText(1158, 14, "Server Uptime");
				player.getPackets().sendIComponentText(1158, 15, "" + Settings.days + " days, " + Settings.hours
						+ " hours, " + Settings.minutes + " minutes and " + Settings.seconds + ", seconds.");
				player.getPackets().sendIComponentText(1158, 19, "Total GE Offers");
				player.getPackets().sendIComponentText(1158, 20, "" + GrandExchangeLoader.offers.size() + "");
				player.getPackets().sendIComponentText(1158, 24, "Xp Gained");
				player.getPackets().sendIComponentText(1158, 25, "" + Settings.serverxp + "");
				player.getPackets().sendIComponentText(1158, 29, "Gold Added");
				player.getPackets().sendIComponentText(1158, 30, "" + Settings.goldenter + " Gold value");
				player.getPackets().sendIComponentText(1158, 34, "99s Obtained");
				player.getPackets().sendIComponentText(1158, 35, "" + Settings.levelups + " 99s gained");
				player.getPackets().sendIComponentText(1158, 39, "Monsters Killed");
				player.getPackets().sendIComponentText(1158, 40, "" + Settings.monsterskilled + " Monsters slayed");
				// player.getPackets().sendIComponentText(1158, 44, "");
				// player.getPackets().sendIComponentText(1158, 45, "");
				player.getPackets().sendIComponentText(1158, 44, "Server Time/Date");
				player.getPackets().sendIComponentText(1158, 45, "" + Calendar.getInstance().getTime() + "");
				player.getPackets().sendIComponentText(1158, 49, "Donation drop rate buff:");
				player.getPackets().sendIComponentText(1158, 50, "$" + Settings.amountdonated + "/25");
				break;
			}
		} else if (interfaceId == 429) {
			if (componentId == 18) {
				player.getInterfaceManager().sendSettings();
			}
		} else if (interfaceId == 623) {
			player.getAdventurersLog().handleButtons(componentId);
		} else if (interfaceId == 982) {
			if (componentId == 5) {
				player.getInterfaceManager().sendSettings();
			} else if (componentId == 41) {
				player.setPrivateChatSetup(player.getPrivateChatSetup() == 0 ? 1 : 0);
			} else if (componentId >= 17 && componentId <= 36) {
				player.setClanChatSetup(componentId - 17);
			} else if (componentId >= 49 && componentId <= 66) {
				player.setPrivateChatSetup(componentId - 48);
			} else if (componentId >= 72 && componentId <= 91) {
				player.setFriendChatSetup(componentId - 72);
			}
		} else if (interfaceId == 271) {
			WorldTasksManager.schedule(new WorldTask() {
				@Override
				public void run() {
					if (componentId == 8 || componentId == 42) {
						player.getPrayer().switchPrayer(slotId);
					} else if (componentId == 43 && player.getPrayer().isUsingQuickPrayer()) {
						player.getPrayer().switchSettingQuickPrayer();
					}
				}
			});
		} else if (interfaceId == 320) {
			if (player.isLocked()) {
				player.sendMessage(Colors.red + "Please finish what you're doing first!");
				return;
			}
			player.stopAll();
			int lvlupSkill = -1;
			int skillMenu = -1;
			switch (componentId) {
			case 150: // Attack
				TeleportsInterface.sendMonstersInterface(player);
				break;
			case 9: // Strength
				TeleportsInterface.sendMonstersInterface(player);
				break;
			case 22: // Defence
				TeleportsInterface.sendMonstersInterface(player);
				break;
			case 40: // Ranged
				TeleportsInterface.sendMonstersInterface(player);
				break;
			case 58: // Prayer
				if (player.getTemporaryAttributtes().remove("leveledUp[5]") != Boolean.TRUE) {
					skillMenu = 7;
					player.getPackets().sendConfig(965, 7);
				} else {
					lvlupSkill = 5;
					player.getPackets().sendConfig(1230, 60);
				}
				break;
			case 71: // Magic
				TeleportsInterface.sendMonstersInterface(player);
				break;
			case 84: // Runecrafting
				TeleportsInterface.sendRunecraftingInterface(player);
				break;
			case 102: // Construction
				TeleportsInterface.sendConstructionInterface(player);
				break;
			case 145: // Hitpoints
				TeleportsInterface.sendMonstersInterface(player);
				break;
			case 15: // Agility
				TeleportsInterface.sendAgilityInterface(player);
				break;
			case 28: // Herblore
				skillMenu = 9;
				if (player.getTemporaryAttributtes().remove("leveledUp[15]") != Boolean.TRUE) {
					player.getPackets().sendConfig(965, 9);
				} else {
					lvlupSkill = 15;
					player.getPackets().sendConfig(1230, 75);
				}
				break;
			case 46: // Thieving
				TeleportsInterface.sendThievingInterface(player);
				break;
			case 64: // Crafting
				TeleportsInterface.sendCraftingInterface(player);
				break;
			case 77: // Fletching
				skillMenu = 19;
				if (player.getTemporaryAttributtes().remove("leveledUp[9]") != Boolean.TRUE) {
					player.getPackets().sendConfig(965, 19);
				} else {
					lvlupSkill = 9;
					player.getPackets().sendConfig(1230, 665);
				}
				break;
			case 90: // Slayer
				TeleportsInterface.sendSlayerInterface(player);
				break;
			case 108: // Hunter
				TeleportsInterface.sendHunterInterface(player);
				break;
			case 140: // Mining
				TeleportsInterface.sendMiningInterface(player);
				break;
			case 135: // Smithing
				TeleportsInterface.sendSmithingInterface(player);
				break;
			case 34: // Fishing
				TeleportsInterface.sendFishingInterface(player);
				break;
			case 52: // Cooking
				skillMenu = 16;
				if (player.getTemporaryAttributtes().remove("leveledUp[7]") != Boolean.TRUE) {
					player.getPackets().sendConfig(965, 16);
				} else {
					lvlupSkill = 7;
					player.getPackets().sendConfig(1230, 641);
				}
				break;
			case 130: // Firemaking
				skillMenu = 17;
				if (player.getTemporaryAttributtes().remove("leveledUp[11]") != Boolean.TRUE) {
					player.getPackets().sendConfig(965, 17);
				} else {
					lvlupSkill = 11;
					player.getPackets().sendConfig(1230, 649);
				}
				break;
			case 125: // Woodcutting
				TeleportsInterface.sendWoodcuttingInterface(player);
				break;
			case 96: // Farming
				TeleportsInterface.sendFarmingInterface(player);
				break;
			case 114: // Summoning
				TeleportsInterface.sendSummoningInterface(player);
				break;
			case 120: // Dung
				TeleportsInterface.sendDungeoneeringInterface(player);
				break;
			case 128: // Dung
				TeleportsInterface.sendDungeoneeringInterface(player);
				break;

			}
			/*
			 * player.getInterfaceManager().sendInterface( lvlupSkill != -1 ?
			 * 741 : 499);
			 */
			//player.getInterfaceManager().sendScreenInterface(317, 1218);
			//player.getPackets().sendInterface(false, 1218, 1, 1217); // seems to
																		// fix
			if (lvlupSkill != -1) {
				LevelUp.switchFlash(player, lvlupSkill, false);
			}
			if (skillMenu != -1) {
				player.getTemporaryAttributtes().put("skillMenu", skillMenu);
			}
		} else if (interfaceId == 1218) {
			if (componentId >= 33 && componentId <= 55 || componentId == 120 || componentId == 151
					|| componentId == 189)
			 {
				player.getPackets().sendInterface(false, 1218, 1, 1217); // seems
																			// to
																			// fix
			}
		} else if (interfaceId == 499) {
			int skillMenu = -1;
			if (player.getTemporaryAttributtes().get("skillMenu") != null) {
				skillMenu = (Integer) player.getTemporaryAttributtes().get("skillMenu");
			}
			if (componentId >= 10 && componentId <= 25) {
				player.getPackets().sendConfig(965, (componentId - 10) * 1024 + skillMenu);
			} else if (componentId == 29) {
				// close inter
				player.stopAll();
			}

		} else if (interfaceId == 387) {
			if (player.getInterfaceManager().containsInventoryInter()) {
				return;
			}
			if (componentId == 41) {
				// player.getToolbelt().openInter();
				player.getInterfaceManager().sendInterface(1178);
			}
			if (componentId == 21) {
				if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET) {
					int hatId = player.getEquipment().getHatId();
					int shieldId = player.getEquipment().getShieldId();

					if (shieldId == 19749) {
						if (player.fallyshieldprayertime > 0) {
							player.sendMessage("You cannot do this again so soon!");
							return;
						} else {
							player.getPrayer().restorePrayer(990);
							player.fallyshieldprayertime = 10;
							player.sendMessage("Your shield restores your prayer points!");
							return;
						}
					}
				}
			}
			if (componentId == 6) {
				if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET) {
					int hatId = player.getEquipment().getHatId();
					int shieldId = player.getEquipment().getShieldId();
					if (hatId == 24437 || hatId == 24439 || hatId == 24440 || hatId == 24441) {
						player.getDialogueManager().startDialogue("FlamingSkull",
								player.getEquipment().getItem(Equipment.SLOT_HAT), -1);
						return;
					}
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON1_PACKET) {
					ButtonHandler.sendRemove(player, Equipment.SLOT_HAT);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON8_PACKET) {
					player.getEquipment().sendExamine(Equipment.SLOT_HAT);
				}
			} else if (componentId == 9) {
				if (packetId == WorldPacketsDecoder.ACTION_BUTTON5_PACKET) {
					int capeId = player.getEquipment().getCapeId();
					if (capeId == 20769 || capeId == 29628 || capeId == 20771) {
						SkillCapeCustomizer.startCustomizing(player, capeId);
					}
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON3_PACKET) {
					int capeId = player.getEquipment().getCapeId();
					if (capeId == 29719 || capeId == 29720) {
						if (!player.canSpawn() || World.isNopeArea(player)
								|| player.getControlerManager().getControler() != null) {
							player.getPackets().sendGameMessage("You can't bank while you're in this area.");
							return;
						}
						player.stopAll();
						player.getBank().openBank();
						return;
					}
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON4_PACKET) {
					int capeId = player.getEquipment().getCapeId();
					if (capeId == 29720) {
						if (player.dcapedart != true) {
							int timeleft = 24 - player.playhours;
							int timeleftmin = 1440 - player.Playpoints;
							if (player.playhours == 23) {
								player.sendMessage(
										"You can claim your next set of spins in " + timeleftmin + " minutes.");
								return;
							}
							player.sendMessage("You can claim your next set of spins in " + timeleft + " hours.");
							return;
						}
						player.dcapedart = false;
						player.spins += 4;
						player.sendMessage("You have been rewarded with 3 spins.");
						return;
					}
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON3_PACKET) {
					int capeId = player.getEquipment().getCapeId();
					if (capeId == 20767) {
						if (player.isCanPvp() || player.getControlerManager().getControler() != null
								|| player.getAttackedByDelay() + 10000 > Utils.currentTimeMillis()) {
							player.sendMessage("You cannot use this teleport right now.");
							return;
						}
						Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2276, 3315, 1));
					}
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON4_PACKET) {
					int capeId = player.getEquipment().getCapeId();
					if (capeId == 20769 || capeId == 20771) {
						if (player.isCanPvp() || player.getControlerManager().getControler() != null
								|| player.getAttackedByDelay() + 10000 > Utils.currentTimeMillis()) {
							player.sendMessage("You cannot use this teleport right now.");
							return;
						}
						Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2276, 3315, 1));
					}
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET) {
					int capeId = player.getEquipment().getCapeId();
					if (capeId == 20767) {
						SkillCapeCustomizer.startCustomizing(player, capeId);
					}
					if (capeId == 29718 || capeId == 29719 || capeId == 29720) {
						if (player.isCanPvp() || World.Scorpia(player) || World.HungerGames(player) || World.TheCalamity(player)
								|| player.getControlerManager().getControler() != null) {
							player.sendMessage("You cannot teleport from here.");
							return;
						}
						Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3369, 9640, 0));
						return;
					}
				//	SkillCapeCustomizer.startCustomizing(player, capeId);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON1_PACKET) {
					ButtonHandler.sendRemove(player, Equipment.SLOT_CAPE);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON8_PACKET) {
					player.getEquipment().sendExamine(Equipment.SLOT_CAPE);
				}
			} else if (componentId == 12) {
				if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET) {
					int amuletId = player.getEquipment().getAmuletId();
					if (amuletId == 29185) {
						player.getDialogueManager().startDialogue("AmuletOfEternalGloryD");
					}
					if (amuletId <= 1712 && amuletId >= 1706 || amuletId >= 10354 && amuletId <= 10361) {
						if (Magic.sendItemTeleportSpell(player, true, Transportation.EMOTE, Transportation.GFX, 4,
								new WorldTile(3087, 3496, 0))) {
							Item amulet = player.getEquipment().getItem(Equipment.SLOT_AMULET);
							if (amulet != null) {
								if (amuletId >= 10354 && amuletId <= 10361) {
									amulet.setId(amulet.getId() + 2);
								} else if (amuletId >= 1706 && amuletId <= 1712) {
									amulet.setId(amulet.getId() - 2);
								}
								player.getEquipment().refresh(Equipment.SLOT_AMULET);
							}
						}
					} else if (amuletId == 1704 || amuletId == 10352) {
						player.getPackets().sendGameMessage(
								"The amulet has ran out of charges. You need to recharge it if you wish it use it once more.");
					}
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON3_PACKET) {
					int amuletId = player.getEquipment().getAmuletId();
					if (amuletId <= 1712 && amuletId >= 1706 || amuletId >= 10354 && amuletId <= 10361) {
						if (Magic.sendItemTeleportSpell(player, true, Transportation.EMOTE, Transportation.GFX, 4,
								new WorldTile(2918, 3176, 0))) {
							Item amulet = player.getEquipment().getItem(Equipment.SLOT_AMULET);
							if (amulet != null) {
								if (amuletId >= 10354 && amuletId <= 10361) {
									amulet.setId(amulet.getId() + 2);
								} else if (amuletId >= 1706 && amuletId <= 1712) {
									amulet.setId(amulet.getId() - 2);
								}
								player.getEquipment().refresh(Equipment.SLOT_AMULET);
							}

						}
					}
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON4_PACKET) {
					int amuletId = player.getEquipment().getAmuletId();
					if (amuletId <= 1712 && amuletId >= 1706 || amuletId >= 10354 && amuletId <= 10361) {
						if (Magic.sendItemTeleportSpell(player, true, Transportation.EMOTE, Transportation.GFX, 4,
								new WorldTile(3105, 3251, 0))) {
							Item amulet = player.getEquipment().getItem(Equipment.SLOT_AMULET);
							if (amulet != null) {
								if (amuletId >= 10354 && amuletId <= 10361) {
									amulet.setId(amulet.getId() + 2);
								} else if (amuletId >= 1706 && amuletId <= 1712) {
									amulet.setId(amulet.getId() - 2);
								}
								player.getEquipment().refresh(Equipment.SLOT_AMULET);
							}
						}
					}
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON5_PACKET) {
					int amuletId = player.getEquipment().getAmuletId();
					if (amuletId <= 1712 && amuletId >= 1706 || amuletId >= 10354 && amuletId <= 10361) {
						if (Magic.sendItemTeleportSpell(player, true, Transportation.EMOTE, Transportation.GFX, 4,
								new WorldTile(3293, 3163, 0))) {
							Item amulet = player.getEquipment().getItem(Equipment.SLOT_AMULET);
							if (amulet != null) {
								if (amuletId >= 10354 && amuletId <= 10361) {
									amulet.setId(amulet.getId() + 2);
								} else if (amuletId >= 1706 && amuletId <= 1712) {
									amulet.setId(amulet.getId() - 2);
								}
								player.getEquipment().refresh(Equipment.SLOT_AMULET);
							}
						}
					}
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON1_PACKET) {
					ButtonHandler.sendRemove(player, Equipment.SLOT_AMULET);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON8_PACKET) {
					player.getEquipment().sendExamine(Equipment.SLOT_AMULET);
				}
			} else if (componentId == 15) {
				if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET) {
					int weaponId = player.getEquipment().getWeaponId();
					if (weaponId == 15484) {
						player.getInterfaceManager().gazeOrbOfOculus();
					}
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON1_PACKET) {
					ButtonHandler.sendRemove(player, Equipment.SLOT_WEAPON);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON8_PACKET) {
					player.getEquipment().sendExamine(Equipment.SLOT_WEAPON);
				}
			} else if (componentId == 18) {
				ButtonHandler.sendRemove(player, Equipment.SLOT_CHEST);
			} else if (componentId == 21) {
				int shieldId = player.getEquipment().getShieldId();
				if (packetId == WorldPacketsDecoder.ACTION_BUTTON1_PACKET) {
					ButtonHandler.sendRemove(player, Equipment.SLOT_SHIELD);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON8_PACKET) {// examin
																					// TODO

				}
			} else if (componentId == 24) {
				ButtonHandler.sendRemove(player, Equipment.SLOT_LEGS);
			} else if (componentId == 27) {
				ButtonHandler.sendRemove(player, Equipment.SLOT_HANDS);
			} else if (componentId == 30) {
				ButtonHandler.sendRemove(player, Equipment.SLOT_FEET);
			} else if (componentId == 33) {
				ButtonHandler.sendRemove(player, Equipment.SLOT_RING);
			} else if (componentId == 36) {
				ButtonHandler.sendRemove(player, Equipment.SLOT_ARROWS);
			} else if (componentId == 45) {
				if (packetId == WorldPacketsDecoder.ACTION_BUTTON4_PACKET) {
					ButtonHandler.sendRemove(player, Equipment.SLOT_AURA);
					player.getAuraManager().removeAura();
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON8_PACKET) {
					player.getEquipment().sendExamine(Equipment.SLOT_AURA);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET) {
					player.getAuraManager().activate();
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON3_PACKET) {
					player.getAuraManager().sendAuraRemainingTime();
				}
			} else if (componentId == 40) {
				player.stopAll();
				player.getInterfaceManager().sendInterface(17);
			} else if (componentId == 37) {
				openEquipmentBonuses(player, false);
			}
		} else if (interfaceId == 449) {
			if (componentId == 1) {
				Shop shop = (Shop) player.getTemporaryAttributtes().get("Shop");
				if (shop == null) {
					return;
				}
				shop.sendInventory(player);
			} else if (componentId == 21) {
				Shop shop = (Shop) player.getTemporaryAttributtes().get("Shop");
				if (shop == null) {
					return;
				}
				Integer slot = (Integer) player.getTemporaryAttributtes().get("ShopSelectedSlot");
				if (slot == null) {
					return;
				}
				if (packetId == WorldPacketsDecoder.ACTION_BUTTON1_PACKET) {
					shop.buy(player, slot, 1);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET) {
					shop.buy(player, slot, 5);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON3_PACKET) {
					shop.buy(player, slot, 10);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON4_PACKET) {
					shop.buy(player, slot, 50);
					//System.out.println("1");
				}

			}

		} else if (interfaceId == 1265) {
			// System.out.println(packetId);
			Shop shop = (Shop) player.getTemporaryAttributtes().get("Shop");
			if (shop == null) {
				return;
			}
			Integer slot = (Integer) player.getTemporaryAttributtes().get("ShopSelectedSlot");
			boolean isBuying = player.getTemporaryAttributtes().get("shop_buying") != null;
			if (componentId == 20) {
				player.getTemporaryAttributtes().put("ShopSelectedSlot", slotId);
				// System.out.println(packetId);
				if (packetId == WorldPacketsDecoder.ACTION_BUTTON1_PACKET) {
					shop.sendInfo(player, slotId, isBuying);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET) {
					shop.handleShop(player, slotId, 1);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON3_PACKET) {
					shop.handleShop(player, slotId, 5);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON4_PACKET) {
					shop.handleShop(player, slotId, 10);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON5_PACKET) {
					shop.handleShop(player, slotId, 50);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON6_PACKET) {
					player.getPackets().sendGameMessage("You aren't allowed to buy all of that item.");
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON9_PACKET) {
					shop.handleShop(player, slotId, 500);
					// System.out.println("1");
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON8_PACKET) {
					shop.sendExamine(player, slotId);
				}
			} else if (componentId == 201) {
				if (slot == null) {
					return;
				/*
				 * if (isBuying) shop.buy(player, slot, shop.getAmount()); else
				 * { shop.sell(player, slot, shop.getAmount());
				 * player.getPackets().sendConfig(2563, 0);
				 * player.getPackets().sendConfig(2565, 1); // this is to update
				 * the tab. } } else if (componentId == 208) {
				 * //shop.setAmount(player, shop.getAmount() + 5); //} else if
				 * (componentId == 15) { //shop.setAmount(player,
				 * shop.getAmount() + 1); //} else if (componentId == 214) { //
				 * if (shop.getAmount() > 1) //shop.setAmount(player,
				 * shop.getAmount() - 1); //} else if (componentId == 217) { //
				 * if (shop.getAmount() > 1) //shop.setAmount(player,
				 * shop.getAmount() - 5);
				 */
				}
			} else if (componentId == 220) {
				shop.setAmount(player, 1);
			} else if (componentId == 211) {
				if (slot == null) {
					return;
				}
				shop.setAmount(player, isBuying ? shop.getMainStock()[slot].getAmount()
						: player.getInventory().getItems().getItems()[slot].getAmount());
			} else if (componentId == 29) {
				player.getPackets().sendConfig(2561, 93);
				player.getTemporaryAttributtes().remove("shop_buying");
				shop.setAmount(player, 1);
			} else if (componentId == 28) {
				player.getTemporaryAttributtes().put("shop_buying", true);
				shop.setAmount(player, 1);
			}
		} else if (interfaceId == 1266) {
			if (componentId == 0) {
				if (packetId == WorldPacketsDecoder.ACTION_BUTTON9_PACKET) {
					player.getInventory().sendExamine(slotId);
				} else {
					Shop shop = (Shop) player.getTemporaryAttributtes().get("Shop");
					if (shop == null) {
						return;
					}
					player.getPackets().sendConfig(2563, slotId);
					if (packetId == WorldPacketsDecoder.ACTION_BUTTON1_PACKET) {
						shop.sendValue(player, slotId);
					} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET) {
						shop.sell(player, slotId, 1);
					} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON3_PACKET) {
						shop.sell(player, slotId, 5);
					} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON4_PACKET) {
						shop.sell(player, slotId, 10);
					} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON5_PACKET) {
						shop.sell(player, slotId, 50);
					}
				}
			}
		} else if (interfaceId == 734) {
			player.getFairyRing().handleButtons(interfaceId, componentId);

		} else if (interfaceId == 640) {
			if (componentId == 18 || componentId == 22) {
				player.getTemporaryAttributtes().put("WillDuelFriendly", true);
				player.getPackets().sendConfig(283, 67108864);
			} else if (componentId == 19 || componentId == 21) {
				player.getTemporaryAttributtes().put("WillDuelFriendly", false);
				player.getPackets().sendConfig(283, 134217728);
			} else if (componentId == 20) {
				DuelControler.challenge(player);
			}
		} else if (interfaceId == 650) {
			if (componentId == 15) {
				player.stopAll();
				player.setNextWorldTile(new WorldTile(2974, 4384, player.getPlane()));
				player.getControlerManager().startControler("CorpBeastControler");
			} else if (componentId == 16) {
				player.closeInterfaces();
			}
		} else if (interfaceId == 667) {
			if (componentId == 14) {
				if (slotId >= 14) {
					return;
				}
				Item item = player.getEquipment().getItem(slotId);
				if (item == null) {
					return;
				}
				if (packetId == 3) {
					player.getPackets().sendGameMessage(ItemExamines.getExamine(item));
				} else if (packetId == 216) {
					sendRemove(player, slotId);
					ButtonHandler.refreshEquipBonuses(player);
				}
			} else if (componentId == 46 && player.getTemporaryAttributtes().remove("Banking") != null) {
				if (player.bankis2 != true) {
					player.getBank().openBank();
				} else if (player.bankis2) {
					player.getBank2().openBank();
				}
			}

		} else if (interfaceId == 670) {
			if (componentId == 0) {
				if (slotId >= player.getInventory().getItemsContainerSize()) {
					return;
				}
				Item item = player.getInventory().getItem(slotId);
				if (item == null) {
					return;
				}
				if (packetId == WorldPacketsDecoder.ACTION_BUTTON1_PACKET) {
					if (sendWear(player, slotId, item.getId())) {
						ButtonHandler.refreshEquipBonuses(player);
					}
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON4_PACKET) {
					player.getInventory().sendExamine(slotId);
				}
			}
		} else if (interfaceId == Inventory.INVENTORY_INTERFACE) { // inventory
			if (componentId == 0) {
				if (slotId > 27 || player.getInterfaceManager().containsInventoryInter()) {
					return;
				}
				Item item = player.getInventory().getItem(slotId);
				if (item == null || item.getId() != slotId2) {
					return;
				}
				if (packetId == WorldPacketsDecoder.ACTION_BUTTON1_PACKET) {
					InventoryOptionsHandler.handleItemOption1(player, slotId, slotId2, item);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET) {
					InventoryOptionsHandler.handleItemOption2(player, slotId, slotId2, item);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON3_PACKET) {
					InventoryOptionsHandler.handleItemOption3(player, slotId, slotId2, item);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON4_PACKET) {
					InventoryOptionsHandler.handleItemOption4(player, slotId, slotId2, item);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON5_PACKET) {
					InventoryOptionsHandler.handleItemOption5(player, slotId, slotId2, item);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON6_PACKET) {
					InventoryOptionsHandler.handleItemOption6(player, slotId, slotId2, item);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON7_PACKET) {
					InventoryOptionsHandler.handleItemOption7(player, slotId, slotId2, item);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON8_PACKET) {
					InventoryOptionsHandler.handleItemOption8(player, slotId, slotId2, item);
				}
			}
		} else if (interfaceId == 742) {
			if (componentId == 46) {
				player.stopAll();
			}
		} else if (interfaceId == 743) {
			if (componentId == 20) {
				player.stopAll();
			}
		} else if (interfaceId == 741) {
			if (componentId == 9) {
				player.stopAll();
			}
		} else if (interfaceId == 749) {
			if (componentId == 4) {
				if (packetId == WorldPacketsDecoder.ACTION_BUTTON1_PACKET) {
					player.getPrayer().switchQuickPrayers();
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET) {
					player.getPrayer().switchSettingQuickPrayer();
				}
			}
		} else if (interfaceId == 750) {
			if (componentId == 4) {
				if (packetId == WorldPacketsDecoder.ACTION_BUTTON1_PACKET) {
					player.toogleRun(player.isResting() ? false : true);
					if (player.isResting()) {
						player.stopAll();
					}
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET) {
					if (player.isResting()) {
						player.stopAll();
						return;
					}
					long currentTime = Utils.currentTimeMillis();
					if (player.getEmotesManager().getNextEmoteEnd() >= currentTime) {
						player.getPackets().sendGameMessage("You can't rest while perfoming an emote.");
						return;
					}
					if (player.getLockDelay() >= currentTime) {
						player.getPackets().sendGameMessage("You can't rest while perfoming an action.");
						return;
					}
					player.stopAll();
					player.getActionManager().setAction(new Rest());
				}
			}
		} else if (interfaceId == 11) {
			if (componentId == 17 && player.bankis2 != true) {
				if (packetId == WorldPacketsDecoder.ACTION_BUTTON1_PACKET) {
					player.getBank().depositItem(slotId, 1, false);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET) {
					player.getBank().depositItem(slotId, 5, false);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON3_PACKET) {
					player.getBank().depositItem(slotId, 10, false);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON4_PACKET) {
					player.getBank().depositItem(slotId, Integer.MAX_VALUE, false);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON5_PACKET) {
					player.getTemporaryAttributtes().put("bank_item_X_Slot", slotId);
					player.getTemporaryAttributtes().remove("bank_isWithdraw");
					player.getPackets().sendRunScript(108, new Object[] { "Enter Amount:" });
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON9_PACKET) {
					player.getInventory().sendExamine(slotId);
				}
			} else if (componentId == 17 && player.bankis2) {
				if (packetId == WorldPacketsDecoder.ACTION_BUTTON1_PACKET) {
					player.getBank2().depositItem(slotId, 1, false);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET) {
					player.getBank2().depositItem(slotId, 5, false);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON3_PACKET) {
					player.getBank2().depositItem(slotId, 10, false);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON4_PACKET) {
					player.getBank2().depositItem(slotId, Integer.MAX_VALUE, false);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON5_PACKET) {
					player.getTemporaryAttributtes().put("bank_item_X_Slot", slotId);
					player.getTemporaryAttributtes().remove("bank_isWithdraw");
					player.getPackets().sendRunScript(108, new Object[] { "Enter Amount:" });
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON9_PACKET) {
					player.getInventory().sendExamine(slotId);
				}
			} else if (componentId == 18 && player.bankis2 != true) {
				player.getBank().depositAllInventory(false);
			} else if (componentId == 18 && player.bankis2) {
				player.getBank2().depositAllInventory(false);
			} else if (componentId == 20 && player.bankis2 != true) {
				player.getBank().depositAllEquipment(false);
			} else if (componentId == 20 && player.bankis2) {
				player.getBank2().depositAllEquipment(false);
			}
		} else if (interfaceId == 762) {
			if (componentId == 15 && player.bankis2 != true) {
				player.getBank().switchInsertItems();
			}
			if (componentId == 15 && player.bankis2) {
				player.getBank2().switchInsertItems();
			} else if (componentId == 19 && player.bankis2 != true) {
				player.getBank().switchWithdrawNotes();
			} else if (componentId == 19 && player.bankis2) {
				player.getBank2().switchWithdrawNotes();
			} else if (componentId == 33 && player.bankis2 != true) {
				player.getBank().depositAllInventory(true);
			} else if (componentId == 33 && player.bankis2) {
				player.getBank2().depositAllInventory(true);
			} else if (componentId == 37 && player.bankis2 != true) {
				player.getBank().depositAllEquipment(true);
			} else if (componentId == 37 && player.bankis2) {
				player.getBank2().depositAllEquipment(true);
			} else if (componentId == 46) {
				player.closeInterfaces();
				player.getInterfaceManager().sendInterface(767);
				player.setCloseInterfacesEvent(new Runnable() {
					@Override
					public void run() {
						if (player.bankis2) {
							player.getBank2().openBank();
						} else {
						player.getBank().openBank();
						}
					}
				});
			} else if (componentId >= 46 && componentId <= 64) {
				int tabId = 9 - (componentId - 46) / 2;
				if (packetId == WorldPacketsDecoder.ACTION_BUTTON1_PACKET && player.bankis2 != true) {
					player.getBank().setCurrentTab(tabId);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON1_PACKET && player.bankis2) {
					player.getBank2().setCurrentTab(tabId);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET && player.bankis2 != true) {
					player.getBank().collapse(tabId);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET && player.bankis2) {
					player.getBank2().collapse(tabId);
				}
			} else if (componentId == 95 && player.bankis2 != true) {
				if (packetId == WorldPacketsDecoder.ACTION_BUTTON1_PACKET) {
					player.getBank().withdrawItem(slotId, 1);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET) {
					player.getBank().withdrawItem(slotId, 5);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON3_PACKET) {
					player.getBank().withdrawItem(slotId, 10);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON4_PACKET) {
					player.getBank().withdrawLastAmount(slotId);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON5_PACKET) {
					player.getTemporaryAttributtes().put("bank_item_X_Slot", slotId);
					player.getTemporaryAttributtes().put("bank_isWithdraw", Boolean.TRUE);
					player.getPackets().sendRunScript(108, new Object[] { "Enter Amount:" });
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON9_PACKET) {
					player.getBank().withdrawItem(slotId, Integer.MAX_VALUE);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON6_PACKET) {
					player.getBank().withdrawItemButOne(slotId);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON8_PACKET) {
					player.getBank().sendExamine(slotId);
				}
			} else if (componentId == 95 && player.bankis2) {
				if (packetId == WorldPacketsDecoder.ACTION_BUTTON1_PACKET) {
					player.getBank2().withdrawItem(slotId, 1);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET) {
					player.getBank2().withdrawItem(slotId, 5);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON3_PACKET) {
					player.getBank2().withdrawItem(slotId, 10);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON4_PACKET) {
					player.getBank2().withdrawLastAmount(slotId);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON5_PACKET) {
					player.getTemporaryAttributtes().put("bank_item_X_Slot", slotId);
					player.getTemporaryAttributtes().put("bank_isWithdraw", Boolean.TRUE);
					player.getPackets().sendRunScript(108, new Object[] { "Enter Amount:" });
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON9_PACKET) {
					player.getBank2().withdrawItem(slotId, Integer.MAX_VALUE);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON6_PACKET) {
					player.getBank2().withdrawItemButOne(slotId);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON8_PACKET) {
					player.getBank2().sendExamine(slotId);
				}

			} else if (componentId == 119) {
				openEquipmentBonuses(player, true);
			}
		} else if (interfaceId == 763) {
			if (componentId == 0 && player.bankis2 != true) {
				if (packetId == WorldPacketsDecoder.ACTION_BUTTON1_PACKET) {
					player.getBank().depositItem(slotId, 1, true);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET) {
					player.getBank().depositItem(slotId, 5, true);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON3_PACKET) {
					player.getBank().depositItem(slotId, 10, true);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON4_PACKET) {
					player.getBank().depositLastAmount(slotId);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON5_PACKET) {
					player.getTemporaryAttributtes().put("bank_item_X_Slot", slotId);
					player.getTemporaryAttributtes().remove("bank_isWithdraw");
					player.getPackets().sendRunScript(108, new Object[] { "Enter Amount:" });
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON9_PACKET) {
					player.getBank().depositItem(slotId, Integer.MAX_VALUE, true);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON8_PACKET) {
					player.getInventory().sendExamine(slotId);
				}
			} else if (componentId == 0 && player.bankis2) {
				if (packetId == WorldPacketsDecoder.ACTION_BUTTON1_PACKET) {
					player.getBank2().depositItem(slotId, 1, true);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET) {
					player.getBank2().depositItem(slotId, 5, true);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON3_PACKET) {
					player.getBank2().depositItem(slotId, 10, true);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON4_PACKET) {
					player.getBank2().depositLastAmount(slotId);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON5_PACKET) {
					player.getTemporaryAttributtes().put("bank_item_X_Slot", slotId);
					player.getTemporaryAttributtes().remove("bank_isWithdraw");
					player.getPackets().sendRunScript(108, new Object[] { "Enter Amount:" });
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON9_PACKET) {
					player.getBank2().depositItem(slotId, Integer.MAX_VALUE, true);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON8_PACKET) {
					player.getInventory().sendExamine(slotId);
				}
			}
		} else if (interfaceId == 767) {
			if (componentId == 10) {
				if (player.bankis2 != true) {
					player.getBank().openBank();
				} else if (player.bankis2) {
					player.getBank2().openBank();
				}
			}
		} else if (interfaceId == 1263) {
			player.getDialogueManager().continueDialogue(interfaceId, componentId);
		} else if (interfaceId == 884) {
			if (componentId == 4) {
				int weaponId = player.getEquipment().getWeaponId();
				if (player.hasInstantSpecial(weaponId)) {
					player.performInstantSpecial(weaponId);
					return;
				}
				submitSpecialRequest(player);
			} else if (componentId >= 7 && componentId <= 10) {
				player.getCombatDefinitions().setAttackStyle(componentId - 7);
			} else if (componentId == 11) {
				player.getCombatDefinitions().switchAutoRelatie();
			}
		} else if (interfaceId == 755) {
			if (componentId == 44) {
				player.getPackets().sendWindowsPane(player.getInterfaceManager().hasRezizableScreen() ? 746 : 548, 2);
			} else if (componentId == 42) {
				player.getHintIconsManager().removeAll();// TODO find hintIcon
															// index
				player.getPackets().sendConfig(1159, 1);
			}
		} else if (interfaceId == 20) {
			SkillCapeCustomizer.handleSkillCapeCustomizer(player, componentId);
		} else if (interfaceId == 1056) {
			if (componentId == 173) {
				player.getInterfaceManager().sendInterface(917);
			}
		} else if (interfaceId == 751) {
			if (componentId == 26) {
				if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET) {
					player.getFriendsIgnores().setPrivateStatus(0);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON3_PACKET) {
					player.getFriendsIgnores().setPrivateStatus(1);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON4_PACKET) {
					player.getFriendsIgnores().setPrivateStatus(2);
				}
			} else if (componentId == 32) {
				if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET) {
					player.setFilterGame(false);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON4_PACKET) {
					player.setFilterGame(true);
				}
			} else if (componentId == 29) {
				if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET) {
					player.setPublicStatus(0);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON3_PACKET) {
					player.setPublicStatus(1);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON4_PACKET) {
					player.setPublicStatus(2);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON5_PACKET) {
					player.setPublicStatus(3);
				}
			} else if (componentId == 0) {
				if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET) {
					player.getFriendsIgnores().setFriendsChatStatus(0);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON3_PACKET) {
					player.getFriendsIgnores().setFriendsChatStatus(1);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON4_PACKET) {
					player.getFriendsIgnores().setFriendsChatStatus(2);
				}
			} else if (componentId == 23) {
				if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET) {
					player.setClanStatus(0);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON3_PACKET) {
					player.setClanStatus(1);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON4_PACKET) {
					player.setClanStatus(2);
				}
			} else if (componentId == 20) {
				if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET) {
					player.setTradeStatus(0);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON3_PACKET) {
					player.setTradeStatus(1);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON4_PACKET) {
					player.setTradeStatus(2);
				}
			} else if (componentId == 14) {
				player.sendMessage(Colors.green+"You will be rewarded a loot box after playing for "+player.dailylootboxtimer+" minutes more!");
			} else if (componentId == 17) {
				if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET) {
					player.setAssistStatus(0);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON3_PACKET) {
					player.setAssistStatus(1);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON4_PACKET) {
					player.setAssistStatus(2);
				} else if (packetId == WorldPacketsDecoder.ACTION_BUTTON9_PACKET) {
					// ASSIST XP Earned/Time
				}
			}
		} else if (interfaceId == 1163 || interfaceId == 1164 || interfaceId == 1168 || interfaceId == 1170
				|| interfaceId == 1173) {
			player.getDominionTower().handleButtons(interfaceId, componentId);
		} else if (interfaceId == 900) {
			PlayerLook.handleMageMakeOverButtons(player, componentId);
		} else if (interfaceId == 1028) {
			PlayerLook.handleCharacterCustomizingButtons(player, componentId);
		} else if (interfaceId == 1108 || interfaceId == 1109) {
			player.getFriendsIgnores().handleFriendChatButtons(interfaceId, componentId, packetId);
		} else if (interfaceId == 1079) {
			player.closeInterfaces();
		} else if (interfaceId == 374) {
			if (componentId >= 5 && componentId <= 9) {
				player.setNextWorldTile(new WorldTile(FightPitsViewingOrb.ORB_TELEPORTS[componentId - 5]));
			} else if (componentId == 15) {
				player.stopAll();
			}
		} else if (interfaceId == 1092) {
			player.stopAll();
			WorldTile destTile = null;
			switch (componentId) {
			case 47:
				destTile = HomeTeleport.LUMBRIDGE_LODE_STONE;
				break;
			case 42:
				destTile = HomeTeleport.BURTHORPE_LODE_STONE;
				break;
			case 39:
				destTile = HomeTeleport.LUNAR_ISLE_LODE_STONE;
				break;
			case 7:
				destTile = HomeTeleport.BANDIT_CAMP_LODE_STONE;
				break;
			case 50:
				destTile = HomeTeleport.TAVERLY_LODE_STONE;
				break;
			case 40:
				destTile = HomeTeleport.ALKARID_LODE_STONE;
				break;
			case 51:
				destTile = HomeTeleport.VARROCK_LODE_STONE;
				break;
			case 45:
				destTile = HomeTeleport.EDGEVILLE_LODE_STONE;
				break;
			case 46:
				destTile = HomeTeleport.FALADOR_LODE_STONE;
				break;
			case 48:
				destTile = HomeTeleport.PORT_SARIM_LODE_STONE;
				break;
			case 44:
				destTile = HomeTeleport.DRAYNOR_VILLAGE_LODE_STONE;
				break;
			case 41:
				destTile = HomeTeleport.ARDOUGNE_LODE_STONE;
				break;
			case 43:
				destTile = HomeTeleport.CATHERBAY_LODE_STONE;
				break;
			case 52:
				destTile = HomeTeleport.YANILLE_LODE_STONE;
				break;
			case 49:
				destTile = HomeTeleport.SEERS_VILLAGE_LODE_STONE;
				break;
			}
			if (destTile != null) {
				player.getActionManager().setAction(new HomeTeleport(destTile));
			}
		} else if (interfaceId == 1214) {
			player.getSkills().handleSetupXPCounter(componentId);
		} else if (interfaceId == 1292) {
			if (componentId == 12) {
				Crucible.enterArena(player);
			} else if (componentId == 13) {
				player.closeInterfaces();
			}
		}
		if (Settings.DEBUG) {
			Logger.log("ButtonHandler", "InterfaceId " + interfaceId + ", componentId " + componentId + ", slotId "
					+ slotId + ", slotId2 " + slotId2 + ", PacketId: " + packetId);
		}
	}

	public static void sendRemove(Player player, int slotId) {
		if (slotId >= 15) {
			return;
		}
		player.stopAll(false, false);
		Item item = player.getEquipment().getItem(slotId);
		if (item == null || !player.getInventory().addItem(item.getId(), item.getAmount())) {
			return;
		}
		player.getEquipment().getItems().set(slotId, null);
		player.getEquipment().refresh(slotId);
		player.getAppearence().generateAppearenceData();
		if (Runecrafting.isTiara(item.getId())) {
			player.getPackets().sendConfig(491, 0);
		}
		if (slotId == 3) {
			player.getCombatDefinitions().desecreaseSpecialAttack(0);
		}
	}

	public static boolean sendWear(Player player, int slotId, int itemId) {
		if (player.hasFinished() || player.isDead()) {
			return false;
		}
		player.stopAll(false, false);
		Item item = player.getInventory().getItem(slotId);
		String itemName = item.getDefinitions() == null ? "" : item.getDefinitions().getName().toLowerCase();
		if (item == null || item.getId() != itemId || item.getId() == 15098) {
			return false;
		}
		if (item.getDefinitions().isNoted()
				|| !item.getDefinitions().isWearItem(player.getAppearence().isMale()) & itemId != 4084) {
			player.getPackets().sendGameMessage("You can't wear that.");
			return true;
		}
		for (String strings : Settings.DONATOR_ITEMS) {
			if (itemName.contains(strings) && !player.isDonator()) {
				player.getPackets().sendGameMessage("You need to be a donator to equip " + itemName + ".");
				return true;
			}
		}
		for (String strings : Settings.EXTREME_DONATOR_ITEMS) {
			if (itemName.contains(strings) && !player.isExtremeDonator()) {
				player.getPackets().sendGameMessage("You need to be a extreme donator to equip " + itemName + ".");
				return true;
			}
		}
		for (String strings : Settings.EARNED_ITEMS) {
			if (itemName.contains(strings) && player.getRights() <= 1) {
				player.getPackets().sendGameMessage("You must earn " + itemName + ".");
				return true;
			}
		}
		/*
		 * for (String strings : Settings.VOTE_REQUIRED_ITEMS) { if
		 * (itemName.toLowerCase().contains(strings) && !player.hasVoted()) {
		 * player.getPackets().sendGameMessage(
		 * "You need to vote to wear the prod item "+itemName+
		 * " for 24 hours, type ::vote to vote."); return true; } }
		 */
		int targetSlot = Equipment.getItemSlot(itemId);
		if (targetSlot == 15 || targetSlot == 17) {
			targetSlot = 13;
		}
		if (targetSlot == -1) {
			player.getPackets().sendGameMessage("You can't wear that.");
			return true;
		}
		if (!ItemConstants.canWear(item, player)) {
			return true;
		}
		boolean isTwoHandedWeapon = targetSlot == 3 && Equipment.isTwoHandedWeapon(item);
		if (isTwoHandedWeapon && !player.getInventory().hasFreeSlots() && player.getEquipment().hasShield()) {
			player.getPackets().sendGameMessage("Not enough free space in your inventory.");
			return true;
		}
		HashMap<Integer, Integer> requiriments = item.getDefinitions().getWearingSkillRequiriments();
		boolean hasRequiriments = true;
		if (requiriments != null) {
			for (int skillId : requiriments.keySet()) {
				if (skillId > 24 || skillId < 0) {
					continue;
				}
				int level = requiriments.get(skillId);
				if (level < 0 || level > 120) {
					continue;
				}
				if (player.getSkills().getLevelForXp(skillId) < level) {
					if (hasRequiriments) {
						player.getPackets().sendGameMessage("You are not high enough level to use this item.");
					}
					hasRequiriments = false;
					String name = Skills.SKILL_NAME[skillId].toLowerCase();
					player.getPackets().sendGameMessage("You need to have a" + (name.startsWith("a") ? "n" : "") + " "
							+ name + " level of " + level + ".");
				}

			}
		}
		if (!hasRequiriments) {
			return true;
		}
		if (!player.getControlerManager().canEquip(targetSlot, itemId)) {
			return false;
		}
		player.stopAll(false, false);
		player.getInventory().deleteItem(slotId, item);
		if (targetSlot == 3) {
			if (isTwoHandedWeapon && player.getEquipment().getItem(5) != null) {
				if (!player.getInventory().addItem(player.getEquipment().getItem(5).getId(),
						player.getEquipment().getItem(5).getAmount())) {
					player.getInventory().getItems().set(slotId, item);
					player.getInventory().refresh(slotId);
					return true;
				}
				player.getEquipment().getItems().set(5, null);
			}
		} else if (targetSlot == 5) {
			if (player.getEquipment().getItem(3) != null
					&& Equipment.isTwoHandedWeapon(player.getEquipment().getItem(3))) {
				if (!player.getInventory().addItem(player.getEquipment().getItem(3).getId(),
						player.getEquipment().getItem(3).getAmount())) {
					player.getInventory().getItems().set(slotId, item);
					player.getInventory().refresh(slotId);
					return true;
				}
				player.getEquipment().getItems().set(3, null);
			}

		}
		if (player.getEquipment().getItem(targetSlot) != null
				&& (itemId != player.getEquipment().getItem(targetSlot).getId()
						|| !item.getDefinitions().isStackable())) {
			if (player.getInventory().getItems().get(slotId) == null) {
				player.getInventory().getItems().set(slotId, new Item(player.getEquipment().getItem(targetSlot).getId(),
						player.getEquipment().getItem(targetSlot).getAmount()));
				player.getInventory().refresh(slotId);
			} else {
				player.getInventory().addItem(new Item(player.getEquipment().getItem(targetSlot).getId(),
						player.getEquipment().getItem(targetSlot).getAmount()));
			}
			player.getEquipment().getItems().set(targetSlot, null);
		}
		if (targetSlot == Equipment.SLOT_AURA) {
			player.getAuraManager().removeAura();
		}
		int oldAmt = 0;
		if (player.getEquipment().getItem(targetSlot) != null) {
			oldAmt = player.getEquipment().getItem(targetSlot).getAmount();
		}
		Item item2 = new Item(itemId, oldAmt + item.getAmount());
		player.getEquipment().getItems().set(targetSlot, item2);
		// player.sendMessage("" +
		// ItemDefinitions.getItemDefinitions(item2).maleEquip1);
		player.getEquipment().refresh(targetSlot, targetSlot == 3 ? 5 : targetSlot == 3 ? 0 : 3);
		player.getAppearence().generateAppearenceData();
		player.getPackets().sendSound(2240, 0, 1);
		if (targetSlot == 3) {
			player.getCombatDefinitions().desecreaseSpecialAttack(0);
		}
		player.getCharges().wear(targetSlot);
	//	System.out.println(Arrays.toString(item.getDefinitions().originalTextureColors));
		return true;
	}

	public static boolean sendWear2(Player player, int slotId, int itemId) {
		if (player.hasFinished() || player.isDead()) {
			return false;
		}
		player.stopAll(false, false);
		Item item = player.getInventory().getItem(slotId);
		if (item == null || item.getId() != itemId) {
			return false;
		}
		if ((itemId == 4565 || itemId == 4084) && player.getRights() != 2) {
			player.getPackets().sendGameMessage("You've to be a administrator to wear this item.");
			return true;
		}
		if (item.getDefinitions().isNoted()
				|| !item.getDefinitions().isWearItem(player.getAppearence().isMale()) && itemId != 4084) {
			player.getPackets().sendGameMessage("You can't wear that.");
			return false;
		}
		String itemName = item.getDefinitions() == null ? "" : item.getDefinitions().getName().toLowerCase();
		for (int i = 0; i < 24; i++) {
			if (itemName.contains("max") && player.getSkills().getLevel(i) != 99) {
				player.getPackets().sendGameMessage("You need to have all 99s to wear " + itemName + ".");
				return true;
			}
		}
		for (String strings : Settings.DONATOR_ITEMS) {
			if (itemName.contains(strings) && !player.isDonator()) {
				player.getPackets().sendGameMessage("You need to be a donator to equip " + itemName + ".");
				return false;
			}
		}
		for (String strings : Settings.EXTREME_DONATOR_ITEMS) {
			if (itemName.contains(strings) && !player.isExtremeDonator()) {
				player.getPackets().sendGameMessage("You need to be a extreme donator to equip " + itemName + ".");
				return true;
			}
		}
		for (String strings : Settings.EARNED_ITEMS) {
			if (itemName.contains(strings) && player.getRights() <= 1) {
				player.getPackets().sendGameMessage("You must earn " + itemName + ".");
				return true;
			}
		}
		/*
		 * for (String strings : Settings.VOTE_REQUIRED_ITEMS) { if
		 * (itemName.toLowerCase().contains(strings) && !player.hasVoted()) {
		 * player.getPackets().sendGameMessage(
		 * "You need to vote to wear the prod item "+itemName+
		 * " for 24 hours, type ::vote to vote."); return true; } }
		 */
		int targetSlot = Equipment.getItemSlot(itemId);
		// if(itemId == 4084)
		// targetSlot = 3;
		if (targetSlot == -1) {
			player.getPackets().sendGameMessage("You can't wear that.");
			return false;
		}
		if (!ItemConstants.canWear(item, player)) {
			return false;
		}
		boolean isTwoHandedWeapon = targetSlot == 3 && Equipment.isTwoHandedWeapon(item);
		if (isTwoHandedWeapon && !player.getInventory().hasFreeSlots() && player.getEquipment().hasShield()) {
			player.getPackets().sendGameMessage("Not enough free space in your inventory.");
			return false;
		}
		HashMap<Integer, Integer> requiriments = item.getDefinitions().getWearingSkillRequiriments();
		boolean hasRequiriments = true;
		if (requiriments != null) {
			for (int skillId : requiriments.keySet()) {
				if (skillId > 24 || skillId < 0) {
					continue;
				}
				int level = requiriments.get(skillId);
				if (level < 0 || level > 120) {
					continue;
				}
				if (player.getSkills().getLevelForXp(skillId) < level) {
					if (hasRequiriments) {
						player.getPackets().sendGameMessage("You are not high enough level to use this item.");
					}
					hasRequiriments = false;
					String name = Skills.SKILL_NAME[skillId].toLowerCase();
					player.getPackets().sendGameMessage("You need to have a" + (name.startsWith("a") ? "n" : "") + " "
							+ name + " level of " + level + ".");
				}

			}
		}
		if (!hasRequiriments) {
			return false;
		}
		if (!player.getControlerManager().canEquip(targetSlot, itemId)) {
			return false;
		}
		player.getInventory().getItems().remove(slotId, item);
		if (targetSlot == 3) {
			if (isTwoHandedWeapon && player.getEquipment().getItem(5) != null) {
				if (!player.getInventory().getItems().add(player.getEquipment().getItem(5))) {
					player.getInventory().getItems().set(slotId, item);
					return false;
				}
				player.getEquipment().getItems().set(5, null);
			}
		} else if (targetSlot == 5) {
			if (player.getEquipment().getItem(3) != null
					&& Equipment.isTwoHandedWeapon(player.getEquipment().getItem(3))) {
				if (!player.getInventory().getItems().add(player.getEquipment().getItem(3))) {
					player.getInventory().getItems().set(slotId, item);
					return false;
				}
				player.getEquipment().getItems().set(3, null);
			}

		}
		if (player.getEquipment().getItem(targetSlot) != null
				&& (itemId != player.getEquipment().getItem(targetSlot).getId()
						|| !item.getDefinitions().isStackable())) {
			if (player.getInventory().getItems().get(slotId) == null) {
				player.getInventory().getItems().set(slotId, new Item(player.getEquipment().getItem(targetSlot).getId(),
						player.getEquipment().getItem(targetSlot).getAmount()));
			} else {
				player.getInventory().getItems().add(new Item(player.getEquipment().getItem(targetSlot).getId(),
						player.getEquipment().getItem(targetSlot).getAmount()));
			}
			player.getEquipment().getItems().set(targetSlot, null);
		}
		if (targetSlot == Equipment.SLOT_AURA) {
			player.getAuraManager().removeAura();
		}
		int oldAmt = 0;
		if (player.getEquipment().getItem(targetSlot) != null) {
			oldAmt = player.getEquipment().getItem(targetSlot).getAmount();
		}
		Item item2 = new Item(itemId, oldAmt + item.getAmount());
		player.getEquipment().getItems().set(targetSlot, item2);
		player.getEquipment().refresh(targetSlot, targetSlot == 3 ? 5 : targetSlot == 3 ? 0 : 3);
		if (targetSlot == 3) {
			player.getCombatDefinitions().desecreaseSpecialAttack(0);
		}
		player.getCharges().wear(targetSlot);
		return true;
	}

	public static void submitSpecialRequest(final Player player) {
		CoresManager.fastExecutor.schedule(new TimerTask() {
			@Override
			public void run() {
				try {
					WorldTasksManager.schedule(new WorldTask() {
						@Override
						public void run() {
							player.getCombatDefinitions().switchUsingSpecialAttack();
						}
					}, 0);
				} catch (Throwable e) {
					Logger.handle(e);
				}
			}
		}, 200);
	}

	public static void sendWear(Player player, int[] slotIds) {
		if (player.hasFinished() || player.isDead()) {
			return;
		}
		boolean worn = false;
		Item[] copy = player.getInventory().getItems().getItemsCopy();
		for (int slotId : slotIds) {
			Item item = player.getInventory().getItem(slotId);
			if (item == null) {
				continue;
			}
			if (sendWear2(player, slotId, item.getId())) {
				worn = true;
			}
		}
		player.getInventory().refreshItems(copy);
		if (worn) {
			player.getAppearence().generateAppearenceData();
			player.getPackets().sendSound(2240, 0, 1);
		}
	}

	public static void openEquipmentBonuses(final Player player, final boolean banking) {
		player.stopAll();
		player.getInterfaceManager().sendInventoryInterface(670);
		player.getInterfaceManager().sendInterface(667);
		player.getPackets().sendConfigByFile(8348, 1);
		player.getPackets().sendConfigByFile(4894, banking ? 1 : 0);
		player.getPackets().sendItems(93, player.getInventory().getItems());
		player.getPackets().sendInterSetItemsOptionsScript(670, 0, 93, 4, 7, "Equip", "Compare", "Stats", "Examine");
		player.getPackets().sendUnlockIComponentOptionSlots(670, 0, 0, 27, 0, 1, 2, 3);
		player.getPackets().sendIComponentSettings(667, 9, 0, 13, 1030);
		refreshEquipBonuses(player);
		WorldTasksManager.schedule(new WorldTask() {
			@Override
			public void run() {
				player.getPackets().sendConfigByFile(8348, 1);
				player.getPackets().sendRunScriptBlank(2319);
			}
		});
		if (banking) {
			player.getTemporaryAttributtes().put("Banking", Boolean.TRUE);
			player.setCloseInterfacesEvent(new Runnable() {
				@Override
				public void run() {
					player.getTemporaryAttributtes().remove("Banking");
					player.getPackets().sendConfigByFile(4894, 0);
				}
			});
		}
	}

	public static void setarmor(Player player) {
		int itemId = player.getEquipment().getHatId();
		int itemId1 = player.getEquipment().getCapeId();
		int itemId2 = player.getEquipment().getAmuletId();
		int itemId3 = player.getEquipment().getWeaponId();
		int itemId4 = player.getEquipment().getShieldId();
		int itemId5 = player.getEquipment().getChestId();
		int itemId6 = player.getEquipment().getLegsId();
		int itemId7 = player.getEquipment().getGlovesId();
		int itemId8 = player.getEquipment().getBootsId();
		int itemId9 = player.getEquipment().getRingId();

		if (player.getEquipment().wearingArmour()) {
			ItemDefinitions def = ItemDefinitions.getItemDefinitions(itemId);
			ItemDefinitions def1 = ItemDefinitions.getItemDefinitions(itemId1);
			ItemDefinitions def2 = ItemDefinitions.getItemDefinitions(itemId2);
			ItemDefinitions def3 = ItemDefinitions.getItemDefinitions(itemId3);
			ItemDefinitions def4 = ItemDefinitions.getItemDefinitions(itemId4);
			ItemDefinitions def5 = ItemDefinitions.getItemDefinitions(itemId5);
			ItemDefinitions def6 = ItemDefinitions.getItemDefinitions(itemId6);
			ItemDefinitions def7 = ItemDefinitions.getItemDefinitions(itemId7);
			ItemDefinitions def8 = ItemDefinitions.getItemDefinitions(itemId8);
			ItemDefinitions def9 = ItemDefinitions.getItemDefinitions(itemId9);
			player.getAppearence().generateAppearenceData();
			player.setHelm(player.getEquipment().getHatId());
			// player.setAura(player.getEquipment().getAuraId());
			player.setCape(player.getEquipment().getCapeId());
			player.setNeck(player.getEquipment().getAmuletId());
			// player.setAmmo(player.getEquipment().getAmmoId());
			player.setSword(player.getEquipment().getWeaponId());
			player.setShield(player.getEquipment().getShieldId());
			player.setChest(player.getEquipment().getChestId());
			player.setLegs(player.getEquipment().getLegsId());
			player.setGloves(player.getEquipment().getGlovesId());
			player.setBoots(player.getEquipment().getBootsId());
			player.setRing(player.getEquipment().getRingId());
			player.getPackets().sendGameMessage("You have just saved your armour.");
			return;
		} else {
			player.getPackets().sendGameMessage("You need to be wearing some armour to save.");
			return;
		}
	}
	
	public static String getPrefix(Player player, int slotId) {
		int i = player.getCombatDefinitions().getBonuses()[slotId];
		String n = String.valueOf(i);
		return n.startsWith("-") || n.contentEquals("0") ? "<col=ff0000>" : "<col=00ff00>+";
	}


	public static void refreshEquipBonuses(Player player) {
		player.getPackets().sendGlobalConfig(779, player.getEquipment().getWeaponRenderEmote());
		//player.getPackets().refreshWeight();
		player.getPackets().sendIComponentText(667, 28,
				"Stab: " + getPrefix(player, 0) + player.getCombatDefinitions().getBonuses()[0]);
		player.getPackets().sendIComponentText(667, 29,
				"Slash: " + getPrefix(player, 1) + player.getCombatDefinitions().getBonuses()[1]);
		player.getPackets().sendIComponentText(667, 30,
				"Crush: " + getPrefix(player, 2) + player.getCombatDefinitions().getBonuses()[2]);
		player.getPackets().sendIComponentText(667, 31,
				"Magic: " + getPrefix(player, 3) + player.getCombatDefinitions().getBonuses()[3]);
		player.getPackets().sendIComponentText(667, 32,
				"Range: " + getPrefix(player, 4) + player.getCombatDefinitions().getBonuses()[4]);
		player.getPackets().sendIComponentText(667, 33,
				"Stab: " + getPrefix(player, 5) + player.getCombatDefinitions().getBonuses()[5]);
		player.getPackets().sendIComponentText(667, 34,
				"Slash: " + getPrefix(player, 6) + player.getCombatDefinitions().getBonuses()[6]);
		player.getPackets().sendIComponentText(667, 35,
				"Crush: " + getPrefix(player, 7) + player.getCombatDefinitions().getBonuses()[7]);
		player.getPackets().sendIComponentText(667, 36,
				"Magic: " + getPrefix(player, 8) + player.getCombatDefinitions().getBonuses()[8]);
		player.getPackets().sendIComponentText(667, 37,
				"Range: " + getPrefix(player, 9) + player.getCombatDefinitions().getBonuses()[9]);
		player.getPackets().sendIComponentText(667, 38,
				"Summoning: " + getPrefix(player, 10) + player.getCombatDefinitions().getBonuses()[10]);
		player.getPackets().sendIComponentText(667, 39,
				"Absorb Melee: " + getPrefix(player, 11) + player.getCombatDefinitions().getBonuses()[11] + "%");
		player.getPackets().sendIComponentText(667, 40,
				"Absorb Magic: " + getPrefix(player, 12) + player.getCombatDefinitions().getBonuses()[12] + "%");
		player.getPackets().sendIComponentText(667, 41,
				"Absorb Ranged: " + getPrefix(player, 13) + player.getCombatDefinitions().getBonuses()[13] + "%");
		player.getPackets().sendIComponentText(667, 42,
				"Strength: " + getPrefix(player, 14) + player.getCombatDefinitions().getBonuses()[14]);
		player.getPackets().sendIComponentText(667, 43,
				"Ranged Str: " + getPrefix(player, 15) + player.getCombatDefinitions().getBonuses()[15]);
		player.getPackets().sendIComponentText(667, 44,
				"Prayer: " + getPrefix(player, 16) + player.getCombatDefinitions().getBonuses()[16]);
		player.getPackets().sendIComponentText(667, 45,
				"Magic Damage: " + getPrefix(player, 17) + player.getCombatDefinitions().getBonuses()[17] + "%");
		player.getPackets().sendIComponentText(667, 22, "0 kg");
	}
}
