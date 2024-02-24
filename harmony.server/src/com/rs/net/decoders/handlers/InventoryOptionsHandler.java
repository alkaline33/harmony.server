package com.rs.net.decoders.handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TimerTask;

import com.rs.Settings;
import com.rs.cache.loaders.ItemDefinitions;
import com.rs.cores.CoresManager;
import com.rs.cores.WorldThread;
import com.rs.game.Animation;
import com.rs.game.Graphics;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.item.MagicOnItem;
import com.rs.game.minigames.CommunityChest;
import com.rs.game.minigames.KilnBox;
import com.rs.game.npc.NPC;
import com.rs.game.npc.familiar.Familiar.SpecialAttack;
import com.rs.game.npc.pet.Pet;
import com.rs.game.player.ClueScrolls;
import com.rs.game.player.CoordsEvent;
import com.rs.game.player.Equipment;
import com.rs.game.player.ExchangeProduce;
import com.rs.game.player.Inventory;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.BoxAction;
import com.rs.game.player.actions.BoxAction.HunterEquipment;
import com.rs.game.player.actions.Firemaking;
import com.rs.game.player.actions.FlaskDecanting;
import com.rs.game.player.actions.Fletching;
import com.rs.game.player.actions.Fletching.Fletch;
import com.rs.game.player.actions.FlyingEntityHunter;
import com.rs.game.player.actions.FlyingEntityHunter.FlyingEntities;
import com.rs.game.player.actions.GemCutting;
import com.rs.game.player.actions.GemCutting.Gem;
import com.rs.game.player.actions.HerbCleaning;
import com.rs.game.player.actions.Herblore;
import com.rs.game.player.actions.LeatherCrafting;
import com.rs.game.player.actions.PotionDecanting;
import com.rs.game.player.actions.Summoning;
import com.rs.game.player.actions.Summoning.Pouches;
import com.rs.game.player.actions.crafting.BattleStaveCreation;
import com.rs.game.player.actions.crafting.GlassBlowing;
import com.rs.game.player.actions.crafting.JewellerySmithing;
//import com.rs.game.player.content.AncientEffigies;
import com.rs.game.player.content.ArmourSets;
import com.rs.game.player.content.ArmourSets.Sets;
import com.rs.game.player.content.BroomStick;
import com.rs.game.player.content.BunnyList;
import com.rs.game.player.content.Burying.Bone;
import com.rs.game.player.content.ChaoticBoxI;
import com.rs.game.player.content.ChristmasPresent;
import com.rs.game.player.content.DarkChest;
import com.rs.game.player.content.DevansLimitedEditionRares;
import com.rs.game.player.content.Dicing;
import com.rs.game.player.content.Foods;
import com.rs.game.player.content.Godsword;
import com.rs.game.player.content.GwdArmourKit;
import com.rs.game.player.content.ItemConstants;
import com.rs.game.player.content.ItemDyeRemover;
import com.rs.game.player.content.ItemDyes;
import com.rs.game.player.content.ItemOnItem;
import com.rs.game.player.content.Lamps;
import com.rs.game.player.content.LightChest;
//import com.rs.game.player.content.Lamps;
import com.rs.game.player.content.Magic;
import com.rs.game.player.content.Mbox;
import com.rs.game.player.content.MysteryChest;
import com.rs.game.player.content.PetMysteryBox;
import com.rs.game.player.content.Pots;
import com.rs.game.player.content.Pots.Pot;
import com.rs.game.player.content.RagingCasket;
import com.rs.game.player.content.RaidsCasket;
import com.rs.game.player.content.RandomPartyHat;
import com.rs.game.player.content.RandomSantaBox;
import com.rs.game.player.content.RandomhWeenBox;
import com.rs.game.player.content.Rbox;
import com.rs.game.player.content.Runecrafting;
import com.rs.game.player.content.SantasPresent;
import com.rs.game.player.content.ScaryBox;
import com.rs.game.player.content.ScrollOfEnhancement;
import com.rs.game.player.content.SkillCapeCustomizer;
import com.rs.game.player.content.SlayerMBox;
import com.rs.game.player.content.TreeSaplings;
import com.rs.game.player.content.VoteBox;
import com.rs.game.player.content.WildernessCasket;
import com.rs.game.player.content.chests.ActiveLootBox;
import com.rs.game.player.content.chests.ChaoticBox;
import com.rs.game.player.content.chests.DailyCrates;
import com.rs.game.player.content.chests.DonatorBoxNew;
import com.rs.game.player.content.chests.ExtremeDonatorBoxNew;
import com.rs.game.player.content.chests.LegendaryDonatorBoxNew;
import com.rs.game.player.content.chests.ReferralBox;
import com.rs.game.player.content.chests.SummerDonatorBox;
import com.rs.game.player.content.chests.SuperDonatorBoxNew;
import com.rs.game.player.content.chests.TheatreofBloodCasket;
import com.rs.game.player.content.event.season.SeasonEvent;
import com.rs.game.player.controlers.FightKiln;
import com.rs.game.player.controlers.HouseControler;
import com.rs.game.player.controlers.Wilderness;
import com.rs.game.player.interfaces.HerbSackInterface;
import com.rs.game.player.interfaces.LootingBagInterface;
import com.rs.game.player.interfaces.MasterSlayerTaskInterface;
import com.rs.game.player.interfaces.OreBagInterface;
import com.rs.game.player.interfaces.RottenPotatoInterface;
import com.rs.game.player.interfaces.RunePouchInterface;
import com.rs.game.player.interfaces.RunePouchUpgradedInterface;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.io.InputStream;
import com.rs.utils.Colors;
import com.rs.utils.Logger;
import com.rs.utils.ShopsHandler;
import com.rs.utils.Utils;

public class InventoryOptionsHandler {

	public static void handleItemOption2(final Player player, final int slotId, final int itemId, Item item) {
		// player.getToolbelt().handleItemOption(item, slotId);
		String name = ItemDefinitions.getItemDefinitions(itemId).getName();
		if (Firemaking.isFiremaking(player, itemId)) {
			return;
		}
		if (ButtonHandler.sendWear(player, slotId, item.getId())) {
			ButtonHandler.refreshEquipBonuses(player);
			return;
		}

		if (itemId == 7927 || itemId == 6583 || itemId == 22560) {
			int npcId = -1;
			player.stopAll();
			player.lock();
			player.getInterfaceManager().sendInventoryInterface(375);
			player.getTemporaryAttributtes().put("RingNPC", Boolean.TRUE);
			switch (itemId) {
			case 7927:
				int[] randomEggs = { 3689, 3690, 3691, 3692, 3693, 3664 };
				npcId = Utils.random(randomEggs.length);
				break;
			case 6583:
			case 22560:
				npcId = 2626;
				break;
			}
			player.getAppearence().transformIntoNPC(npcId);
			return;
		}
		if (itemId == 6583 || itemId == 7927) {
			JewellerySmithing.ringTransformation(player, itemId);
			return;
		}
	
		if (itemId >= 5509 && itemId <= 5514) {
			int pouch = -1;
			if (itemId == 5509) {
				pouch = 0;
			}
			if (itemId == 5510) {
				pouch = 1;
			}
			if (itemId == 5512) {
				pouch = 2;
			}
			if (itemId == 5514) {
				pouch = 3;
			}
			Runecrafting.emptyPouch(player, pouch);
			player.stopAll(false);
		} else if (itemId >= 15086 && itemId <= 15100) {
			if (!player.isGambleHost()) {
				player.sendMessage("You must hold the rank of a host to use these.");
				return;
			}
			Dicing.handleRoll(player, itemId, true);
			return;
		} else {
			if (player.isEquipDisabled()) {
				return;
			}
			long passedTime = Utils.currentTimeMillis() - WorldThread.LAST_CYCLE_CTM;
			WorldTasksManager.schedule(new WorldTask() {

				@Override
				public void run() {
					List<Integer> slots = player.getSwitchItemCache();
					int[] slot = new int[slots.size()];
					for (int i = 0; i < slot.length; i++) {
						slot[i] = slots.get(i);
					}
					player.getSwitchItemCache().clear();
					ButtonHandler.sendWear(player, slot);
					player.stopAll(false, true, false);
				}
			}, passedTime >= 600 ? 0 : passedTime > 330 ? 1 : 0);
			if (player.getSwitchItemCache().contains(slotId)) {
				return;
			}
			player.getSwitchItemCache().add(slotId);
		}
	}

	public static void handleItemOnPlayer(final Player player, final Player usedOn, final int itemId) {
		player.setCoordsEvent(new CoordsEvent(usedOn, new Runnable() {
			@Override
			public void run() {
				player.faceEntity(usedOn);
				if (usedOn.getInterfaceManager().containsScreenInter() && itemId != 5733) {
					player.sendMessage(usedOn.getDisplayName() + " is busy.");
					return;
				}
				// if
				switch (itemId) {

				case 5733:
					if (player.getRights() != 2) {
						player.getInventory().deleteItem(5733, 1);
						World.sendWorldMessage(Colors.red + "" + player.getDisplayName() + " tried to use an administrator tool!", false);
						return;
					}
					// usedOn.
					player.sendMessage("You have been teleported away for being AFK.");
					Magic.sendNormalTeleportSpell(usedOn, 0, 0, new WorldTile(3762, 2927, 0));
					return;
				// break;

				case 962:// Christmas cracker
					// if (player.isAdmin()) {
					// player.getPackets().sendGameMessage("Admins are unable to
					// use crackers");
					// return;
					// }
					if (player.getInventory().getFreeSlots() < 3 || usedOn.getInventory().getFreeSlots() < 3) {
						player.sendMessage(
								(player.getInventory().getFreeSlots() < 3 ? "You do" : "The other player does")
										+ " not have enough inventory space to open this cracker.");
						return;
					}
					if (player.getInventory().containsItem(962, 1)) {
						player.getDialogueManager().startDialogue("ChristmasCrackerD", usedOn, itemId);
						break;
					}
				case 29595:// ultra Christmas cracker
					// if (player.isAdmin()) {
					// player.getPackets().sendGameMessage("Admins are unable to
					// use crackers");
					// return;
					// }
					if (player.getInventory().getFreeSlots() < 3 || usedOn.getInventory().getFreeSlots() < 3) {
						player.sendMessage(
								(player.getInventory().getFreeSlots() < 3 ? "You do" : "The other player does")
										+ " not have enough inventory space to open this cracker.");
						return;
					}
					if (player.getInventory().containsItem(29595, 1)) {
						player.getDialogueManager().startDialogue("UltraChristmasCrackerD", usedOn, itemId);
						break;
					}
				default:
					player.sendMessage("Nothing interesting happens. (Try this on another server ;))");
					break;

				}
				if (itemId > 0 && usedOn.getDisplayName().equalsIgnoreCase("jack") && usedOn.getInventory().getFreeSlots() > 0 && itemId != 962) {
					player.getInventory().deleteItem(itemId, 1);
					usedOn.getInventory().addItem(itemId, 1);
					player.itemonconnor++;
					if (itemId == 3265) {
						player.usedtrollpotionhydrix = true;
					}
					player.sendMessage("You have given " + usedOn.getDisplayName() + " 1 " + Item.getItemName(itemId) + ".");
					usedOn.sendMessage("You have been given 1 " + Item.getItemName(itemId) + " by " + player.getDisplayName() + " .");
					return;
				}
				if (itemId > 0 && player.getDisplayName().equalsIgnoreCase("jack") && usedOn.getInventory().getFreeSlots() > 0 && itemId != 962) {
					player.getInventory().deleteItem(itemId, 1);
					usedOn.getInventory().addItem(itemId, 1);
					player.sendMessage("You have given " + usedOn.getDisplayName() + " 1 " + Item.getItemName(itemId) + ".");
					usedOn.sendMessage("You have been given 1 " + Item.getItemName(itemId) + " by " + player.getDisplayName() + " .");
					return;
				}
			}
		}, usedOn.getSize()));
	}

	public static void handleItemOption1(final Player player, final int slotId, final int itemId, Item item) {
		long time = Utils.currentTimeMillis();
		String name = ItemDefinitions.getItemDefinitions(itemId).getName();
		if (item.getDefinitions().containsOption(0, "Craft")
				|| item.getDefinitions().containsOption(0, "Fletch")) {
			if (player.getInventory().containsItemToolBelt(946)) {
				Fletch fletch = Fletching.isFletching(item, new Item(946));
				if (fletch != null) {
					player.getDialogueManager().startDialogue("FletchingD", fletch);
					return;
				}
			} else if (player.getInventory().containsItemToolBelt(1755)) {
				Fletch fletch = Fletching.isFletching(item, new Item(1755));
				if (fletch != null) {
					player.getDialogueManager().startDialogue("FletchingD", fletch);
					return;
				}
				
			} else {
				player.getDialogueManager().startDialogue("ItemMessage",
						"You need a knife or chisle to complete the action.", 946);
			}
		}
		
		Pouches pouches = Pouches.forId(itemId);
		if (pouches != null) {
			Summoning.spawnFamiliar(player, pouches);
		}
		else if (player.getPetManager().spawnPet(itemId, true)) {
			return;
		}
		else if (player.getDwarvenrockCake().eatCake(player, itemId)) {
			return;
		}
		else if (itemId >= 29276 && itemId <= 29280) {
			player.sendMessage("This present isn't for you!");
			return;
		}
		else if (itemId >= 29125 && itemId <= 29127) {
			player.sendMessage("This should be assembled at the research table.");
			return;
		}
		else if (itemId == 22451) {
			player.getDialogueManager().startDialogue("GanoCraft");
			return;
		}
		else if (itemId == 28942) {
			if (player.coalstoragecap == 1000) {
				player.coalstoragecap = 2500;
			player.getInventory().deleteItem(28942, 1);
			player.sendMessage("You have increased your storage cap to 2,500.");
			} else {
				player.sendMessage("You do not need to use this!");
			}
		}
		if (name.contains("1000 x ")) {
			if (!player.getInventory().hasFreeSlots() && player.getInventory().getNumerOf(itemId) > 1) {
				player.sendMessage("You don't have enough inventory space to do this!");
					return;
			}
			if (name.contains("Blood rune")) {
				player.getInventory().deleteItem(itemId, 1);
				player.getInventory().addItem(565, 1000);
			} else if (name.contains("Cosmic rune")) {
				player.getInventory().deleteItem(itemId, 1);
				player.getInventory().addItem(564, 1000);
			} else if (name.contains("Chaos rune")) {
				player.getInventory().deleteItem(itemId, 1);
				player.getInventory().addItem(562, 1000);
			} else if (name.contains("Nature rune")) {
				player.getInventory().deleteItem(itemId, 1);
				player.getInventory().addItem(561, 1000);
			} else if (name.contains("Death rune")) {
				player.getInventory().deleteItem(itemId, 1);
				player.getInventory().addItem(560, 1000);
			} else if (name.contains("Mind rune")) {
				player.getInventory().deleteItem(itemId, 1);
				player.getInventory().addItem(558, 1000);
			} else if (name.contains("Earth rune")) {
				player.getInventory().deleteItem(itemId, 1);
				player.getInventory().addItem(557, 1000);
			} else if (name.contains("Air rune")) {
				player.getInventory().deleteItem(itemId, 1);
				player.getInventory().addItem(556, 1000);
			} else if (name.contains("Water rune")) {
				player.getInventory().deleteItem(itemId, 1);
				player.getInventory().addItem(555, 1000);
			} else if (name.contains("Fire rune")) {
				player.getInventory().deleteItem(itemId, 1);
				player.getInventory().addItem(554, 1000);
			} else if (name.contains("Soul rune")) {
				player.getInventory().deleteItem(itemId, 1);
				player.getInventory().addItem(566, 1000);
			}
		}
		
		/*if (itemId == 1625) {
			GemCutting.cut(player, Gem.OPAL);
		} else if (itemId == 1627) {
			GemCutting.cut(player, Gem.JADE);
		} else if (itemId == 1629) {
			GemCutting.cut(player, Gem.RED_TOPAZ);
		} else if (itemId == 1623) {
			GemCutting.cut(player, Gem.SAPPHIRE);
		} else if (itemId == 1621) {
			GemCutting.cut(player, Gem.EMERALD);
		} else if (itemId == 1619) {
			GemCutting.cut(player, Gem.RUBY);
		} else if (itemId == 1617) {
			GemCutting.cut(player, Gem.DIAMOND);
		} else if (itemId == 1631) {
			GemCutting.cut(player, Gem.DRAGONSTONE);
		} else if (itemId == 6571) {
			GemCutting.cut(player, Gem.ONYX);
		} else if (itemId == 1609) {
			GemCutting.cut(player, Gem.CUT_OPAL);
		} else if (itemId == 1611) {
			GemCutting.cut(player, Gem.CUT_JADE);
		} else if (itemId == 1613) {
			GemCutting.cut(player, Gem.CUT_RED_TOPAZ);
		} else if (itemId == 1607) {
			GemCutting.cut(player, Gem.CUT_SAPPHIRE);
		} else if (itemId == 1605) {
			GemCutting.cut(player, Gem.CUT_EMERALD);
		} else if (itemId == 1603) {
			GemCutting.cut(player, Gem.CUT_RUBY);
		} else if (itemId == 1601) {
			GemCutting.cut(player, Gem.CUT_DIAMOND);
		} else if (itemId == 1615) {
			GemCutting.cut(player, Gem.CUT_DRAGONSTONE);
		} else if (itemId == 6573) {
			GemCutting.cut(player, Gem.CUT_ONYX);
		}*/
		
		if (itemId == 28937) {
			if (!player.turmoilunlocked) {
				player.getInventory().deleteItem(28937, 1);
				player.turmoilunlocked = true;
				player.sendMessage(Colors.cyan+"You can now redirect your turmoil at the prayer altar.");
				return;
			} else {
				player.sendMessage("You have already unlocked this!");
				return;
			}
		}
		else if (itemId == 28905) {
			if (player.meleeturmoilupgrade) {
				player.sendMessage(Colors.red+"You have already unlocked this Turmoil upgrade!");
				return;
			}
			player.getInventory().deleteItem(28905, 1);
			player.meleeturmoilupgrade = true;
			player.sendMessage("You now have upgraded your Melee turmoil.");
			return;
		}
		else if (itemId == 28906) {
			if (player.mageturmoilupgrade) {
				player.sendMessage(Colors.red+"You have already unlocked this Turmoil upgrade!");
				return;
			}
			player.getInventory().deleteItem(28906, 1);
			player.mageturmoilupgrade = true;
			player.sendMessage("You now have upgraded your Mage turmoil.");
			return;
		}
		else if (itemId == 28907) {
			if (player.rangeturmoilupgrade) {
				player.sendMessage(Colors.red+"You have already unlocked this Turmoil upgrade!");
				return;
			}
			player.getInventory().deleteItem(28907, 1);
			player.rangeturmoilupgrade = true;
			player.sendMessage("You now have upgraded your Range turmoil.");
			return;
		}
		else if (itemId == 28922) {
			player.getInventory().deleteItem(28922, 1);
			player.calamityrewardpoints+= 200;
			player.sendMessage("You now have "+player.calamityrewardpoints+" Calamity reward points.");
			return;
		}
		else if (itemId == 28917) {
			player.getInventory().deleteItem(28917, 1);
			player.gwdkcdouble = true;
			player.sendMessage("All your GwD killcount kills will now count for 2.");
			return;
		}
		else if (itemId == 28915) {
			player.getInventory().deleteItem(28915, 1);
			player.pinkboxingglovesc = true;
			player.sendMessage("You have unlocked the pink boxing gloves override.");
			return;
		}
		else if (itemId == 28912) {
			player.getInventory().deleteItem(28912, 1);
			player.savespellrunes = true;
			player.sendMessage("Your spells now have a 25% chance to save runes.");
			return;
		}
		else if (itemId == 28911) {
			player.getInventory().deleteItem(28911, 1);
			player.freeslayerportal = true;
			player.sendMessage("You can now use the slayer portal for free");
			return;
		}
		else if (itemId == 28913) {
			player.getInventory().deleteItem(28913, 1);
			player.huntertrapboost = true;
			player.sendMessage("You have unlocked the ability to place 2 extra traps.");
			return;
		}
		if (itemId == 29827) {
			if (player.getInventory().containsItem(29827, 100)) {
				player.getInventory().deleteItem(29827, 100);
				player.getInventory().addItemDrop(1037, 1);
				player.sendMessage(Colors.cyan+"You make a pair of Bunny ears to wear.");
				return;
			}
		return;
		}
		if (itemId == 6965 || itemId == 6962) {
			player.getDialogueManager().startDialogue("SimpleMessage", "I probably shouldn't eat this. The Sandwich kid seemed to have smelly fingers.");
			return;
		}
		if (itemId == 29055) {
			player.getDialogueManager().startDialogue("SimpleMessage", "It seems to contain some very powerful components that can upgrade Bandos, Armadyl or Subjugation armour.");
			return;
		}
		if (itemId == 28984) {
			player.getDialogueManager().startDialogue("SimpleMessage", "Use this at the research table with the 3 Cristallum boots to upgrade them.");
			return;
		}
		if (itemId == 28974) {
			if (player.raptorsgiftdroprate != true) {
				player.raptorsgiftdroprate = true;
				player.sendMessage(Colors.cyan+"You open the box and suddenly feel lucky. 2% droprate has been added to your account.");
				player.getInventory().deleteItem(28974, 1);
				return;
			} else {
				player.getInventory().addItemDrop(995, 10000000);
				player.sendMessage(Colors.cyan+"You open the box and find 10M coins.");
				player.getInventory().deleteItem(28974, 1);
			return;
			}
		}
		if (itemId == 29116) {
			player.getDialogueManager().startDialogue("SimpleMessage", "This strange device will automatically exchange your drops for runecoins if they have any value. You can right click set-value, doing so will make anything under the value you set turn to runecoins. <br><col=ff0000>Your Runecoiner is currently exchanging drops up to the value of "+Utils.format(player.runecoinervalue)+".");
			return;
		}
		if (itemId == 28983) {
			if (player.rodeffectincrease != true) {
				player.rodeffectincrease = true;
				player.sendMessage("Your Ring of Death passive will now deal a total of 10% damage.");
				player.getInventory().deleteItem(28983, 1);
				return;
			} else {
				player.getDialogueManager().startDialogue("SimpleMessage", "You have already unlocked this effect!");
				return;
			}
		}
		if (itemId == 29102) {
			if (player.angrybunnytitle != true) {
				player.angrybunnytitle = true;
				player.sendMessage(Colors.green+"You have unlocked the Bunny Killer title!");
				player.getInventory().deleteItem(29102, 1);
				return;
			} else {
				player.sendMessage(Colors.red+"You have already unlocked this title!");
				return;
			}
		}
		if (itemId == 28970) {
			if (player.icecreamhatoverridec != true) {
				player.icecreamhatoverridec = true;
				player.sendMessage(Colors.green+"You have unlocked the Ice cream cone hat override!");
				player.getInventory().deleteItem(28970, 1);
				return;
			} else {
				player.sendMessage(Colors.red+"You have already unlocked this override!");
				return;
			}
		}
		if (itemId == 29101) {
			if (player.eggsterminatorc != true) {
				player.eggsterminatorc = true;
				player.sendMessage(Colors.green+"You have unlocked the Eggsterminator override!");
				player.getInventory().deleteItem(29101, 1);
				return;
			} else {
				player.sendMessage(Colors.red+"You have already unlocked this override!");
				return;
			}
		}
		if (itemId == 29114) {
			player.getDialogueManager().startDialogue("SimpleMessage", "Using this on a Bonecrusher will upgrade it to a Bonecrusher (i). This allows you to set the crusher to either crush bones or note them.");
			return;
		}
		if (itemId == 29113) {
			if (player.bonecrusherI) {
				player.bonecrusherI = false;
				player.sendMessage("Your bonecrusher(i) is now: crushing bones for prayer experience.");
			} else {
				player.bonecrusherI = true;
				player.sendMessage("Your bonecrusher(i) is now: noting bones.");
			}
			return;
		}
		if (itemId == SeasonEvent.TOKENS) {
//			int amount = player.getInventory().getNumerOf(itemId) / 300;
//			for (int i = 1; i < amount; i++) {
			SeasonEvent.ExchangeTokens(player, itemId);
		//	}
			return;
		}
		if (itemId == 29115) {
			if (player.ringmasterc != true) {
				player.ringmasterc = true;
				player.sendMessage(Colors.cyan+"You have unlocked the Ringmaster override.");
				player.getInventory().deleteItem(29115, 1);
			} else {
				player.sendMessage(Colors.red+"You have already unlocked this override!");
				return;
			}
			return;
		}
		if (itemId == 28934) {
			if (player.woodlandcownc != true) {
				player.woodlandcownc = true;
				player.sendMessage(Colors.cyan+"You have unlocked the Woodland crown override.");
				player.getInventory().deleteItem(28934, 1);
			} else {
				player.sendMessage(Colors.red+"You have already unlocked this override!");
				return;
			}
			return;
		}
		
		if (itemId == 29128) {
			if (player.defenderofslisketitle) {
				player.sendMessage("You have already obtained this title! You may aswell recycle this now.");
				return;
			}
			player.defenderofslisketitle = true;
			World.sendIconWorldMessage(Colors.green+""+player.getDisplayName()+" has just unlocked the *Defender of Sliske* title!", false);
			player.sendMessage("Speak to Titles at ::Shops to equip your new title.");
			player.getInventory().deleteItem(29128, 1);
			return;
		}
		if (itemId == 29175 || itemId == 29176 || itemId == 29177) {
			if (player.getInventory().contains(29175) && player.getInventory().contains(29176) && player.getInventory().contains(29177)) {
			player.getInventory().deleteItem(29175, 1);
			player.getInventory().deleteItem(29176, 1);
			player.getInventory().deleteItem(29177, 1);
			player.getInventory().addItem(29173, 1);
			player.sendMessage(Colors.purple + "You create the Brimstone ring.");
			return;
			} else {
				player.sendMessage(Colors.red + "You must have Hydra's eye, fang & heart to do this!");
				return;
			}
		}
		if (itemId == 29181) {
			player.getInventory().deleteItem(29181, 1);
			player.getInventory().addItem(29179, 1);
			return;
		}
		if (itemId == 29180) {
			player.getInventory().deleteItem(29180, 1);
			player.getInventory().addItem(29178, 1);
			return;
		}
		if (itemId == 29183) {
			player.sendMessage("This is used as a component to make the Seren Godbow!");
			return;
		}
		if (itemId == 29186) {
			player.getDialogueManager().startDialogue("EternalGloryActivationD");
			return;
		}
		if (itemId == 29195) {
			if (player.getSkills().getLevelForXp(Skills.SMITHING) < 66) {
				player.sendMessage("You need a smithing level of 66 to make this.");
				return;
			}
			if (player.getInventory().contains(2890) && player.getInventory().contains(29195)) {
				player.getSkills().addXp(13, 2000);
				player.getInventory().deleteItem(2890, 1);
				player.getInventory().deleteItem(29195, 1);
				player.getInventory().addItem(29196, 1);
				return;
			} else {
				player.sendMessage("You must have an Elemental shield & Wyvern visage to do this!");
				return;
			}

		}
		if (itemId == 28991 || itemId == 28990 || itemId == 28989) {
			if (player.getSkills().getLevelForXp(Skills.CRAFTING) < 99 || player.getSkills().getLevelForXp(Skills.MAGIC) < 99) {
				player.sendMessage(Colors.red+"You need 99 in the following stats to craft these...");
				player.sendMessage(Colors.red+"Crafting & Magic.");
				return;
			}
			player.getDialogueManager().startDialogue("BrothercorruptionD", itemId);
			return;
		}
		if (itemId == 29188) {
			if (player.getSkills().getLevelForXp(Skills.SMITHING) < 90) {
				player.sendMessage("You need a smithing level of 90 to make this.");
				return;
			}
			if (player.getInventory().contains(1540) && player.getInventory().contains(29188)) {
				player.getSkills().addXp(13, 2000);
				player.getInventory().deleteItem(1540, 1);
				player.getInventory().deleteItem(29188, 1);
				player.getInventory().addItem(29187, 1);
				return;
			} else {
				player.sendMessage("You must have an Anti-dragon shield & Skeletal visage to do this!");
				return;
			}

		}
		if (itemId == 29221) {
			if (player.SnowmanPenguinRest != true) {
				player.getInventory().deleteItem(29221, 1);
				player.SnowmanPenguinRest = true;
				player.SnowmanPenguinRestActive = true;
				player.sendMessage(Colors.green + "You have now unlocked the Snowman resting animation!");
				return;
			} else {
				player.sendMessage("You have already unlocked this animation!");
				return;
			}
		}
		/**
		 * Bounty hunter shit
		 */
		if (itemId == 29209) {
			ShopsHandler.openShop(player, 126);
			return;
		}
		if (itemId == 14876) {
			player.getInventory().deleteItem(14876, 1);
			player.getInventory().addItemDrop(29209, 5000);
			return;
		}
		if (itemId == 14877) {
			player.getInventory().deleteItem(14877, 1);
			player.getInventory().addItemDrop(29209, 1000);
			return;
		}
		/**
		 * 
		 */
		if (itemId == 29361 || itemId == 29298) {
			player.getInventory().deleteItem(itemId, 1);
			player.templeoflightcharges++;
			player.sendMessage("You add a teleport charge. You now have " + player.templeoflightcharges + " Temple of Light charges!");
			return;
		}
		if (itemId == 29282) {
			player.getInventory().deleteItem(29282, 1);
			player.sendMessage("You have unlocked the Golden birds lootbeam! Speak to Loot Beam Information to activate it!");
			player.LootBeam4 = true;
			return;
		}
		if (itemId == 29283) {
			player.getInventory().deleteItem(29283, 1);
			player.sendMessage("You have unlocked the Snow clouds lootbeam! Speak to Loot Beam Information to activate it!");
			player.LootBeam3 = true;
			return;
		}
		if (itemId == 29284) {
			player.getInventory().deleteItem(29284, 1);
			player.sendMessage("You have unlocked the Rainbow lootbeam! Speak to Loot Beam Information to activate it!");
			player.LootBeam2 = true;
			return;
		}
		if (itemId == 29290) {
			player.sendMessage("An ingredient needed to make a t85 offhand sword. Used at the research table.");
			return;
		}

		if (itemId == 29254) {
			if (player.getInventory().contains(29254) && player.getInventory().contains(29457) && player.getInventory().contains(20171)) {
				player.getInventory().deleteItem(29254, 1);
				player.getInventory().deleteItem(29457, 1);
				player.getInventory().deleteItem(20171, 1);
				player.getInventory().addItem(29255, 1);
				player.sendMessage("Your blowpipe is now in a mutated state.");
				return;
			} else {
				player.sendMessage("You need a Toxic blowpipe & Zaryte bow along with this patch to upgrade the Toxic blowpipe.");
				return;
			}
		}
		if (itemId == 29289) {
			player.sendMessage("An ingredient needed to make a t85 offensive shield. Used at the research table.");
			return;
		}
		if (itemId == 29288) {
			player.sendMessage("An ingredient needed to make a t85 offhand crossbow. Used at the research table.");
			return;
		}
		if (itemId == 24784) {
			player.getInventory().deleteItem(24784, 1);
			player.getSkills().addXp(Skills.HERBLORE, 700);
			return;
		}
		if (itemId == 24516) {
			player.getInventory().deleteItem(24516, 1);
			player.getSkills().addXp(Skills.SMITHING, 250);
			return;
		}
		if (itemId == 29319) {
			ShopsHandler.openShop(player, 122);
			return;
		}
		if (itemId == 29226) {
			ShopsHandler.openShop(player, 125);
			return;
		}
		if (itemId == 29225) {
			player.getInventory().deleteItem(itemId, 1);
			player.eventhealperk = true;
			return;
		}
		/**
		 * Skill outfit boxes
		 */
		if (itemId == 29246) {
			if (player.getInventory().getFreeSlots() < 5) {
				player.sendMessage(Colors.red + "You need 5 free inventory slots to open this!");
				return;
			}
			player.getInventory().deleteItem(itemId, 1);
			player.getInventory().addItem(21439, 1);
			player.getInventory().addItem(21440, 1);
			player.getInventory().addItem(21441, 1);
			player.getInventory().addItem(21442, 1);
			player.getInventory().addItem(21443, 1);
			return;
		}
		if (itemId == 29245) {
			if (player.getInventory().getFreeSlots() < 5) {
				player.sendMessage(Colors.red + "You need 5 free inventory slots to open this!");
				return;
			}
			player.getInventory().deleteItem(itemId, 1);
			player.getInventory().addItem(25180, 1);
			player.getInventory().addItem(25181, 1);
			player.getInventory().addItem(25182, 1);
			player.getInventory().addItem(25183, 1);
			player.getInventory().addItem(25184, 1);
			return;
		}
		if (itemId == 29244) {
			if (player.getInventory().getFreeSlots() < 5) {
				player.sendMessage(Colors.red + "You need 5 free inventory slots to open this!");
				return;
			}
			player.getInventory().deleteItem(itemId, 1);
			player.getInventory().addItem(25185, 1);
			player.getInventory().addItem(25186, 1);
			player.getInventory().addItem(25187, 1);
			player.getInventory().addItem(25188, 1);
			player.getInventory().addItem(25189, 1);
			return;
		}
		if (itemId == 29243) {
			if (player.getInventory().getFreeSlots() < 5) {
				player.sendMessage(Colors.red + "You need 5 free inventory slots to open this!");
				return;
			}
			player.getInventory().deleteItem(itemId, 1);
			player.getInventory().addItem(24423, 1);
			player.getInventory().addItem(24424, 1);
			player.getInventory().addItem(24425, 1);
			player.getInventory().addItem(24426, 1);
			return;
		}
		if (itemId == 29242) {
			if (player.getInventory().getFreeSlots() < 5) {
				player.sendMessage(Colors.red + "You need 5 free inventory slots to open this!");
				return;
			}
			player.getInventory().deleteItem(itemId, 1);
			player.getInventory().addItem(25190, 1);
			player.getInventory().addItem(25191, 1);
			player.getInventory().addItem(25192, 1);
			player.getInventory().addItem(25193, 1);
			player.getInventory().addItem(25194, 1);
			return;
		}
		if (itemId == 29241) {
			if (player.getInventory().getFreeSlots() < 5) {
				player.sendMessage(Colors.red + "You need 5 free inventory slots to open this!");
				return;
			}
			player.getInventory().deleteItem(itemId, 1);
			player.getInventory().addItem(20789, 1);
			player.getInventory().addItem(20790, 1);
			player.getInventory().addItem(20791, 1);
			player.getInventory().addItem(20788, 1);
			player.getInventory().addItem(20787, 1);
			return;
		}
		if (itemId == 29240) {
			if (player.getInventory().getFreeSlots() < 5) {
				player.sendMessage(Colors.red + "You need 5 free inventory slots to open this!");
				return;
			}
			player.getInventory().deleteItem(itemId, 1);
			player.getInventory().addItem(25195, 1);
			player.getInventory().addItem(25196, 1);
			player.getInventory().addItem(25197, 1);
			player.getInventory().addItem(25198, 1);
			player.getInventory().addItem(25199, 1);
			return;
		}
		if (itemId == 29239) {
			if (player.getInventory().getFreeSlots() < 5) {
				player.sendMessage(Colors.red + "You need 5 free inventory slots to open this!");
				return;
			}
			player.getInventory().deleteItem(itemId, 1);
			player.getInventory().addItem(10941, 1);
			player.getInventory().addItem(10940, 1);
			player.getInventory().addItem(10939, 1);
			player.getInventory().addItem(10933, 1);
			return;
		}
		if (itemId == 29238) {
			if (player.getInventory().getFreeSlots() < 5) {
				player.sendMessage(Colors.red + "You need 5 free inventory slots to open this!");
				return;
			}
			player.getInventory().deleteItem(itemId, 1);
			player.getInventory().addItem(21485, 1);
			player.getInventory().addItem(21484, 1);
			player.getInventory().addItem(21486, 1);
			player.getInventory().addItem(21487, 1);
			return;
		}

		if (itemId == 29318) {
			player.getInventory().deleteItem(29318, 1);
			player.skeletonc = true;
			player.sendMessage(Colors.salmon + "You have now unlocked: Skeleton Outfit!");
			return;
		}
		if (itemId == 29324) {
			if (player.getInventory().contains(29324) && player.getInventory().contains(29323) && player.getInventory().contains(29322)) {
				player.getInventory().deleteItem(29324, 1);
				player.getInventory().deleteItem(29323, 1);
				player.getInventory().deleteItem(29322, 1);
				player.getInventory().addItem(29321, 1);
				return;
			} else {
				player.sendMessage(Colors.red + "You must have the Bottom, Middle & Top to do this!");
				return;
			}
		}
		if (itemId == 3114) { // drop rate token
			player.sendMessage(Colors.cyan + "Having this token in your inventory will provide a 10% drop rate buff, but is consumed per kill.");
			return;
		}
		if (itemId == 29367) {
			player.sendMessage(Colors.cyan + "This shard can be used on Bandos, Armadyl & Subjugation body & leg armour to create a corrupted version. Corrupted versions have the same stats, but provide additional damage inside the wilderness!");
			return;
		}
		if (itemId == 29210 || itemId == 29208) {
			player.getInterfaceManager().sendInterface(new LootingBagInterface(player));
			return;
		}
		if (itemId == 18015) {// rune pouch
			player.getInterfaceManager().sendInterface(new RunePouchInterface(player));
			return;
		}
		if (itemId == 29253) {// rune pouch Upgraded
			player.getInterfaceManager().sendInterface(new RunePouchUpgradedInterface(player));
			return;
		}
		if (itemId == 28959) {// herb sack
			player.getInterfaceManager().sendInterface(new HerbSackInterface(player));
			return;
		}
		if (itemId == 28933) {// ore bag
			player.getInterfaceManager().sendInterface(new OreBagInterface(player));
			return;
		}
		if (itemId <= 29401 && itemId >= 29384 || itemId == 29376 || itemId == 28916 || itemId == 29122 || itemId == 29001 || itemId == 29121 || itemId == 29248 || itemId >= 29271 && itemId <= 29274) {
			player.getDialogueManager().startDialogue("SimpleMessage", "Speak to the Pet Tamer at home to give your pet this benefit.");
			return;
		}
		if (itemId == 14463) {
			player.getDialogueManager().startDialogue("SimpleMessage", "Search any chest until you find a key part. Use that key part on the coffin & combine the keys. Then proceed north to fight the boss!");
			return;
		}
		if (itemId == 19670) {
			for (int i = 10; i < 310; i++) {
				player.getPackets().sendIComponentText(275, i, "");
			}
			player.getPackets().sendIComponentText(275, 1, "Scroll of Enhancement");
			player.getPackets().sendIComponentText(275, 10, "This scroll can be used on the following;");

			player.getPackets().sendIComponentText(275, 12, "<col=ff0000>Ring Of Wealth");
			player.getPackets().sendIComponentText(275, 13, "Provides an additional 0.5% drop rate.");

			player.getPackets().sendIComponentText(275, 15, "<col=ff0000>Donator Ring");
			player.getPackets().sendIComponentText(275, 16, "Provides an additional 0.5% drop rate.");

			player.getPackets().sendIComponentText(275, 18, "<col=ff0000>Full Slayer Helmet");
			player.getPackets().sendIComponentText(275, 19, "Provides better base stats & better accuracy/damage on task.");
			player.getInterfaceManager().sendInterface(275);
			return;
		}
		if (itemId == 10508) {
			player.sendMessage("Use this in your Player Owned House in a tree slot.");
			return;
		}

		if (itemId == 29424) {
			if (player.kuradalscrolldaily != true) {
				player.sendMessage("You can still benefit from Kuradal scrolls, so using this would be a waste!");
				return;
			} else {
				player.kuradalscrolldaily = false;
				player.sendMessage("You can now benefit from Kuradals scrolls.");
				player.getInventory().deleteItem(29424, 1);
				return;
			}
		}
		if (itemId == 29439) {
			if (player.getInventory().containsItem(29439, 1)) {
				player.getInventory().deleteItem(29439, 1);
				player.getInventory().addItem(11230, 500);
				return;
			}
		}
		if (itemId == 29354) {
			if (player.getInventory().containsItem(29354, 1)) {
				player.getInventory().deleteItem(29354, 1);
				player.getInventory().addItem(2, 1000);
				return;
			}
		}
		if (itemId == 29444 || itemId == 29445 || itemId == 29446) {
			if (player.getInventory().contains(29444) && player.getInventory().contains(29445) && player.getInventory().contains(29446)) {
				player.getInventory().deleteItem(29444, 1);
				player.getInventory().deleteItem(29445, 1);
				player.getInventory().deleteItem(29446, 1);
				player.getInventory().addItem(29447, 1);
				player.sendMessage("You combine the bludgeon parts to make an Abyssal bludgeon!");
				return;
			} else {
				player.sendMessage("You need a Bludgeon Spine, Axon & Claw to make this!");
				return;
			}
		}
		if (itemId == 29798) {
			if (player.isDonator()) {
				player.sendMessage("You already have donator status!");
				return;
			}
			player.getInventory().deleteItem(29798, 1);
			player.donator = true;
			player.sendMessage("You have redeemed the bond for donator status!");
			return;
		}
		if (itemId == 11856) {
			if (player.getInventory().getFreeSlots() < 4) {
				player.sendMessage("You need 4 free inventory spaces to do this!");
				return;
			}
			player.getInventory().deleteItem(11856, 1);
			player.getInventory().addItem(4753, 1);
			player.getInventory().addItem(4755, 1);
			player.getInventory().addItem(4757, 1);
			player.getInventory().addItem(4759, 1);
			player.sendMessage("You open the armour set.");
			return;
		}
		if (itemId== 11854) {
			if (player.getInventory().getFreeSlots() < 4) {
				player.sendMessage("You need 4 free inventory spaces to do this!");
				return;
			}
			player.getInventory().deleteItem(11854, 1);
			player.getInventory().addItem(4745, 1);
			player.getInventory().addItem(4747, 1);
			player.getInventory().addItem(4749, 1);
			player.getInventory().addItem(4751, 1);
			player.sendMessage("You open the armour set.");
			return;
		}
		if (itemId== 11852) {
			if (player.getInventory().getFreeSlots() < 4) {
				player.sendMessage("You need 4 free inventory spaces to do this!");
				return;
			}
			player.getInventory().deleteItem(11852, 1);
			player.getInventory().addItem(4732, 1);
			player.getInventory().addItem(4734, 1);
			player.getInventory().addItem(4736, 1);
			player.getInventory().addItem(4738, 1);
			player.sendMessage("You open the armour set.");
			return;
		}
		if (itemId== 11850) {
			if (player.getInventory().getFreeSlots() < 4) {
				player.sendMessage("You need 4 free inventory spaces to do this!");
				return;
			}
			player.getInventory().deleteItem(11850, 1);
			player.getInventory().addItem(4724, 1);
			player.getInventory().addItem(4726, 1);
			player.getInventory().addItem(4728, 1);
			player.getInventory().addItem(4730, 1);
			player.sendMessage("You open the armour set.");
			return;
		}
		if (itemId== 11848) {
			if (player.getInventory().getFreeSlots() < 4) {
				player.sendMessage("You need 4 free inventory spaces to do this!");
				return;
			}
			player.getInventory().deleteItem(11848, 1);
			player.getInventory().addItem(4716, 1);
			player.getInventory().addItem(4718, 1);
			player.getInventory().addItem(4720, 1);
			player.getInventory().addItem(4722, 1);
			player.sendMessage("You open the armour set.");
			return;
		}
		if (itemId== 11846) {
			if (player.getInventory().getFreeSlots() < 4) {
				player.sendMessage("You need 4 free inventory spaces to do this!");
				return;
			}
			player.getInventory().deleteItem(11846, 1);
			player.getInventory().addItem(4708, 1);
			player.getInventory().addItem(4710, 1);
			player.getInventory().addItem(4712, 1);
			player.getInventory().addItem(4714, 1);
			player.sendMessage("You open the armour set.");
			return;
		}
		if (itemId == 29929 || itemId == 29930 || itemId == 29931) {
			player.getDialogueManager().startDialogue("DrygoreCraft");
			return;
		}
		if (itemId == 29503) {
			if (player.aniviaamuletcharges <= 0) {
				player.getInventory().deleteItem(29503, 1);
				player.aniviaamuletcharges = 100;
				player.sendMessage("You consume the charges and your amulet now has 100 charges.");
			return;
			
			}else {
				player.sendMessage("Your amulet must have 0 charges to do this.");
				return;
			}
		}
		if (itemId == 29635) {
			int freeslots = player.getInventory().getFreeSlots();
			int amountess = player.getInventory().getNumerOf(29635);
			if (freeslots < 1) {
				player.getPackets().sendGameMessage("You need inventory space to do this.");
				return;
			}
			if (freeslots > amountess) {
				player.setNextAnimation(new Animation(553));
				player.setNextGraphics(new Graphics(255));
				player.getInventory().deleteItem(29635, amountess);
				player.getInventory().addItem(7936, amountess);
				player.getPackets().sendGameMessage("You redeem " + amountess + " Pure essence.");
				return;
			}
			player.setNextAnimation(new Animation(553));
			player.setNextGraphics(new Graphics(255));
			player.getInventory().deleteItem(29635, freeslots);
			player.getInventory().addItem(7936, freeslots);
			player.getPackets().sendGameMessage("You redeem " + freeslots + " Pure essence.");
			return;
		}
		if (itemId == 10829) {
			player.getInterfaceManager().sendInterface(42);
			player.getPackets().sendIComponentText(42, 16, "Import/Export - Port Sarim LTD");
			player.getPackets().sendIComponentText(42, 15, "");
			player.getPackets().sendIComponentText(42, 1, "10 Ton logs (out)");
			player.getPackets().sendIComponentText(42, 5, "10 Ton fish (out)");
			player.getPackets().sendIComponentText(42, 9, "10 Ton logs (out)");
			player.getPackets().sendIComponentText(42, 13, "7 Ton potions (out)");
			player.getPackets().sendIComponentText(42, 20, "10 Ton gems (out)");
			player.getPackets().sendIComponentText(42, 2, "20 Ton ores (in)");
			player.getPackets().sendIComponentText(42, 6, "10 Ton bars (in)");
			player.getPackets().sendIComponentText(42, 10, "18 Ton planks (in)");
			player.getPackets().sendIComponentText(42, 14, "14 Ton bones (in)");
			player.getPackets().sendIComponentText(42, 21, "19 Ton dice bags (in)");

			player.getPackets().sendIComponentText(42, 3, "");
			player.getPackets().sendIComponentText(42, 4, "");
			player.getPackets().sendIComponentText(42, 7, "");
			player.getPackets().sendIComponentText(42, 8, "");
			player.getPackets().sendIComponentText(42, 11, "");
			player.getPackets().sendIComponentText(42, 12, "");
			player.getPackets().sendIComponentText(42, 18, "");
			player.getPackets().sendIComponentText(42, 19, "");
			player.getPackets().sendIComponentText(42, 22, "");
			player.getPackets().sendIComponentText(42, 23, "");
			return;
		}
		if (itemId == 29629) {
			if (player.getSkills().getLevelForXp(Skills.MINING) < 99
					|| player.getSkills().getLevelForXp(Skills.RUNECRAFTING) < 99
					|| player.getSkills().getLevelForXp(Skills.FARMING) < 99
					|| player.getSkills().getLevelForXp(Skills.HERBLORE) < 99
					|| player.getSkills().getLevelForXp(Skills.FISHING) < 99
					|| player.getSkills().getLevelForXp(Skills.COOKING) < 99) {
				player.sendMessage(
						"You need maximum knowledge in mining, runecrafting, fishing, cooking, farming and herblore to use this.");
				return;
			}
			player.getInventory().deleteItem(29629, 1);
			Magic.sendDrygonTeleportSpell(player, 0, 0, new WorldTile(2200, 3825, 0));
			return;
		}
		if (itemId == 29537) {
			player.getDialogueManager().startDialogue("ValentineHeart");
			return;
		}
		if (itemId == 29592) {
			if (player.getSkills().getLevelForXp(Skills.FLETCHING) < 92) {
				player.sendMessage("You need 92 fletching to do this.");
				return;
			}
			player.getDialogueManager().startDialogue("ZenyteCraftD");
			return;
		}
		if (itemId == 29543) {
			player.unlockeddungperkbook = true;
			player.sendMessage("As you read the book, you feel you a mysterious power surround you.");
			return;
		}
		if (itemId == 29552) {
			player.getTemporaryAttributtes().put("setdisplay", Boolean.TRUE);
			player.getPackets().sendInputNameScript("Enter the display name you wish:");
		}
		if (itemId == 29554) {
			player.sendMessage("The crystal doesn't seem to respond, maybe the dwarf knows why.");
			return;
		}
		if (itemId == 29936) {
			if (player.getInventory().containsItem(29936, 250000) && player.getInventory().containsItem(18357, 1)) {
				player.getInventory().deleteItem(29936, 250000);
				player.getInventory().deleteItem(18357, 1);
				player.getInventory().addItem(29967, 1);
				player.sendMessage("You create an Ascension crossbow.");
				World.sendWorldMessage(
						"<col=ff0000>" + player.getDisplayName() + " has created the Ascension crossbow.", true);
			} else {
				player.getPackets()
						.sendGameMessage("You need 250,000 Ascension coins and one chaotic crossbow to make this."); // game
																														// message
																														// if
																														// you
																														// do
																														// not
																														// have
																														// 100
																														// shard
			}
		}
		if (itemId == 29935) {
			if (player.getInventory().containsItem(29935, 1)) {
				player.setNextAnimation(new Animation(713));
				player.setNextGraphics(new Graphics(149));
				player.getInventory().deleteItem(29935, 1);
				player.getInventory().addItem(29936, 2000);
				player.getSkills().addXp(Skills.PRAYER, 20000);
				player.gmemory++;
				player.sendMessage(
						"You exchange the god's memory for 2000 Ascension coins and some prayer experience.");
			} else {
				player.getPackets().sendGameMessage("You need 1 god memory to make this!"); // game
																							// message
																							// if
																							// you
																							// do
																							// not
																							// have
																							// 100
																							// shard
			}
		}
		if (itemId == 29553) {
			if (player.isCanPvp() || player.getControlerManager().getControler() != null) {
				player.sendMessage("You cannot teleport here.");
				return;
			}
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2821, 9881, 0));
			return;
		}
		if (itemId == 299) {
			if (player.isLocked()) {
				return;
			}
			if (!player.isGambleHost()) {
				player.getPackets().sendGameMessage("You don't have the correct rank to use these.");
				return;
			}
			if (!World.Chill(player)) {
				player.getPackets().sendGameMessage("You cannot plant flowers here.. You must go to ::chill.");
				return;
			}
			if (World.getObject(new WorldTile(player), 10) != null) {
				player.getPackets().sendGameMessage("You cannot plant flowers here..");
				return;
			}
			final Player thisman = player;
			final double random = Utils.getRandomDouble(100);
			final WorldTile tile = new WorldTile(player);
			int flower = Utils.random(2980, 2987);
			if (random < 0.2) {
				flower = Utils.random(2987, 2989);
			}
			if (!player.addWalkSteps(player.getX() - 1, player.getY(), 1)) {
				if (!player.addWalkSteps(player.getX() + 1, player.getY(), 1)) {
					if (!player.addWalkSteps(player.getX(), player.getY() + 1, 1)) {
						player.addWalkSteps(player.getX(), player.getY() - 1, 1);
					}
				}
			}
			player.getInventory().deleteItem(299, 1);
			final WorldObject flowerObject = new WorldObject(flower, 10, Utils.getRandom(4), tile.getX(), tile.getY(),
					tile.getPlane());
			World.spawnTemporaryObject(flowerObject, 45000);
			player.lock();
			WorldTasksManager.schedule(new WorldTask() {
				int step;

				@Override
				public void run() {
					if (thisman == null || thisman.hasFinished()) {
						stop();
					}
					if (step == 1) {
						thisman.getDialogueManager().startDialogue("FlowerPickup", flowerObject);
						thisman.setNextFaceWorldTile(tile);
						thisman.unlock();
						stop();
					}
					step++;
				}
			}, 0, 0);

		}
		if (itemId == 29772) {
			player.sendMessage("You learn that combining a Prayer renewal (3) with an Overload (4) will make an Overpray potion!");
			player.getInventory().deleteItem(29772, 1);
			player.canmakeoverpray = true;
			return;
		}
		if (itemId == 29773) {
			player.sendMessage("You learn that combining an Ancient ore with a Saradomin brew (3) will make a Super saradomin potion!");
			player.getInventory().deleteItem(29773, 1);
			player.canmakesupersarabrew = true;
			return;
		}
		if (itemId == 29774) {
			player.sendMessage("You learn that combining a shark with a raw rocktail will produce a fury shark.");
			player.getInventory().deleteItem(29774, 1);
			player.canmakefuryshark = true;
			return;
		}
		if (itemId == 29771) {
			player.sendMessage("<col=330000>For one Calamity game you will receive double kill points!");
			player.getInventory().deleteItem(29771, 1);
			player.doublekillpointscalamity = true;
			return;
		}
		if (itemId == 29762) {
			player.sendMessage("<col=330000>You have unlocked the *Defeater Of Satan* title.");
			player.getInventory().deleteItem(29762, 1);
			player.getAppearence().setTitle(200);
			player.satantitle = true;
			return;
		}
		if (itemId == 29761) {
			player.sendMessage("<col=330000>You have unlocked the *Defeater of the Undead* title.");
			player.getInventory().deleteItem(29761, 1);
			player.getAppearence().setTitle(201);
			player.undeadtitle = true;
			return;
		}
		if (itemId == 29759) {
			player.sendMessage("<col=330000>You have unlocked the *Defeater of the Osteology* title.");
			player.getInventory().deleteItem(29759, 1);
			player.getAppearence().setTitle(202);
			player.osteologytitle = true;
			return;
		}
		if (itemId == 29758) {
			player.sendMessage("<col=330000>You have unlocked the *Warrior of the Calamity* title.");
			player.getInventory().deleteItem(29758, 1);
			player.getAppearence().setTitle(203);
			player.warriorcalatitle = true;
			return;
		}
		if (itemId == 985 || itemId == 987) {
			if (player.getInventory().contains(985) && player.getInventory().contains(987)) {
				player.getInventory().deleteItem(985, 1);
				player.getInventory().deleteItem(987, 1);
				player.getInventory().addItem(989, 1);
				player.sendMessage("You combine both key parts to create a single key.");
				return;
			}
		}
		if (itemId == 14469) {
			if (!player.getInventory().contains(14463) || !player.getInventory().contains(14470)) {
				player.sendMessage("You don't understand how this works yet.");
				return;
			}
			player.getInventory().deleteItem(14463, 1);
			player.getInventory().deleteItem(14469, 1);
			player.getInventory().deleteItem(14470, 1);
			player.getInventory().addItem(14471, 1);
			player.sendMessage("You create a key.");
			return;
		}
		if (itemId == 29544 || itemId == 29371) {
			if (player.keepsakecape > 0) {
				player.sendMessage("Your Cape slot keepsake currently holds a "
						+ ItemDefinitions.getItemDefinitions(player.keepsakecape).getName() + ".");
			}
			if (player.keepsakehat > 0) {
				player.sendMessage("Your Hat slot keepsake currently holds a "
						+ ItemDefinitions.getItemDefinitions(player.keepsakehat).getName() + ".");
			}
			if (player.keepsakeamulet > 0) {
				player.sendMessage("Your Amulet slot keepsake currently holds a "
						+ ItemDefinitions.getItemDefinitions(player.keepsakeamulet).getName() + ".");
			}
			if (player.keepsakebody > 0) {
				player.sendMessage("Your Body slot keepsake currently holds a "
						+ ItemDefinitions.getItemDefinitions(player.keepsakebody).getName() + ".");
			}
			if (player.keepsakeboots > 0) {
				player.sendMessage("Your Boots slot keepsake currently holds a "
						+ ItemDefinitions.getItemDefinitions(player.keepsakeboots).getName() + ".");
			}
			if (player.keepsakegloves > 0) {
				player.sendMessage("Your Gloves slot keepsake currently holds a "
						+ ItemDefinitions.getItemDefinitions(player.keepsakegloves).getName() + ".");
			}
			if (player.keepsakelegs > 0) {
				player.sendMessage("Your Legs slot keepsake currently holds a "
						+ ItemDefinitions.getItemDefinitions(player.keepsakelegs).getName() + ".");
			}
			if (player.keepsakeweapon > 0) {
				player.sendMessage("Your Weapon slot keepsake currently holds a "
						+ ItemDefinitions.getItemDefinitions(player.keepsakeweapon).getName() + ".");
			}
			if (player.keepsakeshield > 0) {
				player.sendMessage("Your Shield slot keepsake currently holds a "
						+ ItemDefinitions.getItemDefinitions(player.keepsakeshield).getName() + ".");
			}

		}
		if (itemId == 14470) {
			if (!player.getInventory().contains(14463) || !player.getInventory().contains(14469)) {
				player.sendMessage("You don't understand how this works yet.");
				return;
			}
			player.getInventory().deleteItem(14463, 1);
			player.getInventory().deleteItem(14469, 1);
			player.getInventory().deleteItem(14470, 1);
			player.getInventory().addItem(14471, 1);
			player.sendMessage("You create a key.");
			return;
		}
		if (itemId == 28963) {
			player.setNextAnimation(new Animation(9597));
			player.setNextGraphics(new Graphics(1680));
			player.getInventory().deleteItem(28963, 1);
			player.getPackets().sendStopCameraShake();
			player.lock();
			WorldTasksManager.schedule(new WorldTask() {
				int stage;

				@Override
				public void run() {
					if (stage == 0) {
						player.setNextAnimation(new Animation(4731));
						stage = 1;
					} else if (stage == 1) {
						player.useStairs(-1, new WorldTile(3557, 9703, 3), 1, 2, "You've broken into a crypt.");
						player.getControlerManager().startControler("Barrows");
						player.setNextAnimation(new Animation(-1));
						stage = 2;
					} else if (stage == 2) {
						player.resetReceivedDamage();
						player.unlock();
						stop();
					}

				}
			}, 2, 1);
			return;
		}
			if (itemId == 8013) {
			if (!player.getControlerManager().processItemTeleport(player)) {
				return;
			}
			if (player.hasHouse && !(player.getControlerManager().getControler() instanceof HouseControler)) {
				player.setNextAnimation(new Animation(9597));
				player.setNextGraphics(new Graphics(1680));
				player.getInventory().deleteItem(8013, 1);
				WorldTasksManager.schedule(new WorldTask() {
					int stage;

					@Override
					public void run() {
						if (stage == 0) {
							player.setNextAnimation(new Animation(4731));
							stage = 1;
						} else if (stage == 1) {
							player.getHouse().setBuildMode(false);
							player.getHouse().enterMyHouse();
							player.setNextAnimation(new Animation(-1));
							stage = 2;
						} else if (stage == 2) {
							player.resetReceivedDamage();
							player.unlock();
							stop();
						}

					}
				}, 2, 1);
				return;
			} else if (!player.hasHouse) {
				player.sm("You must purchase a house in order to do this. You can buy a property from any "
						+ Settings.SERVER_NAME + " Estate Agent.");
				return;
			} else {
				player.sm("You cannot do this here.");
				return;
			}
		}
		if (itemId == 25214) {
			player.setNextAnimation(new Animation(733));
			player.setNextGraphics(new Graphics(1765));
			player.getInventory().deleteItem(25214, 1);
			player.getPackets().sendGameMessage("Happy Fireworks!");
			return;
		}
		/*
		 * if (itemId == 29655) { if (player.getInventory().contains(29654) &&
		 * player.getInventory().contains(29655)) {
		 * CommunityChest.handleObjects(itemId, player); } return; }
		 */
		if (itemId == 29671) {
			if (player.canspeaktohettyagain1 == true) {
				player.sendMessage("No need for this now.");
				return;
			}
			player.sendMessage("You could get infected with this, maybe Hetty can help you.");
			player.canspeaktohettyagain = true;
			return;
		}
		if (itemId == 29668) {
			if (player.canspeaktohettyagain1 == true) {
				player.sendMessage("You'd kill yourself is you continued to do this, so you best not.");
				return;
			}
			player.sendMessage("You draw blood and it trails to Hetty's cauldron. Speak to Hetty to find out why.");
			player.canspeaktohettyagain1 = true;
			return;
		}
		if (itemId == 14057) {
			BroomStick.Sweep(player);
			return;
		}
		if (itemId == 29643) {
			if (player.isCanPvp() || player.getControlerManager().getControler() != null) {
				player.sendMessage("You cannot teleport here.");
				return;
			}
			Magic.sendDrygonTeleportSpell(player, 0, 0, new WorldTile(2741, 5077, 0));
			return;
		}
		if (itemId == 29644) {
			player.getInventory().deleteItem(29644, 1);
			player.getInventory().addItem(10034, 500);
			return;
		}
		if (itemId == 29587) {
			player.getInventory().deleteItem(29587, 1);
			player.thunderous = true;
			player.sendMessage("Speak to titles at home to add your title!");
			return;
		}
		if (itemId == 29645) {
			player.getInventory().deleteItem(29645, 1);
			player.warmonger = true;
			player.sendMessage("Speak to titles at home to add your title!");
			return;
		}
		if (itemId == 29646) {
			player.getInventory().deleteItem(29646, 1);
			player.lcommander = true;
			player.sendMessage("Speak to titles at home to add your title!");
			return;
		}
		if (itemId == 29647) {
			player.getInventory().deleteItem(29647, 1);
			player.sunkiller = true;
			player.sendMessage("Speak to titles at home to add your title!");
			return;
		}
		if (itemId == 29648) {
			player.getInventory().deleteItem(29648, 1);
			player.defenderofdry = true;
			player.sendMessage("Speak to titles at home to add your title!");
			return;
		}
		if (itemId == 29675) {
			BunnyList.OpenList(player);
			return;
		}
		if (ReferralBox.isBox(itemId, player)) {
			return;
		}
		if (DailyCrates.isBox(itemId, player)) {
			return;
		}
		if (RaidsCasket.isBox(itemId, player)) {
			return;
		}
		if (TheatreofBloodCasket.isBox(itemId, player)) {
			return;
		}
		if (ChaoticBoxI.isBox(itemId, player)) {
			return;
		}
		if (ChaoticBox.isBox(itemId, player)) {
			return;
		}
		if (DonatorBoxNew.isBox(itemId, player)) {
			return;
		}
		if (ActiveLootBox.isBox(itemId, player)) {
			return;
		}
		if (LightChest.isBox(itemId, player)) {
			return;
		}
		if (DarkChest.isBox(itemId, player)) {
			return;
		}
		if (RagingCasket.isBox(itemId, player)) {
			return;
		}
		if (WildernessCasket.isBox(itemId, player)) {
			return;
		}
		if (SlayerMBox.isBox(itemId, player)) {
			return;
		}
		if (RandomhWeenBox.isBox(itemId, player)) {
			return;
		}
		if (RandomSantaBox.isBox(itemId, player)) {
			return;
		}
		if (RandomPartyHat.isBox(itemId, player)) {
			return;
		}
		if (PetMysteryBox.isBox(itemId, player)) {
			return;
		}
		if (MysteryChest.isBox(itemId, player)) {
			return;
		}
		if (Rbox.isBox(itemId, player)) {
			return;
		}
		if (DevansLimitedEditionRares.isBox(itemId, player)) {
			return;
		}
		if (CommunityChest.handleObjects(itemId, player)) {
			return;
		}
		if (ChristmasPresent.isBox(itemId, player)) {
			return;
		}
		if (ExtremeDonatorBoxNew.isBox(itemId, player)) {
			return;
		}
		if (SuperDonatorBoxNew.isBox(itemId, player)) {
			return;
		}
		if (LegendaryDonatorBoxNew.isBox(itemId, player)) {
			return;
		}
		if (SummerDonatorBox.isBox(itemId, player)) {
			return;
		}
		if (SantasPresent.isBox(itemId, player)) {
			return;
		}
		if (Mbox.isBox(itemId, player)) {
			return;
		}
		if (VoteBox.isBox(itemId, player)) {
			return;
		}
		if (KilnBox.isBox(itemId, player)) {
			return;
		}
		if (ScaryBox.isBox(itemId, player)) {
			return;
		}
		if (itemId == 29679) {
			if (player.isCanPvp()) {
				player.sendMessage("You cannot do this whilst you're in an un-safe area.");
				return;
			}
			if (player.kuradalscrolldaily == true) {
				player.sendMessage("You can only redeem one a day!");
				return;
			}
			if (player.getInventory().contains(29679)) {
				player.getInventory().deleteItem(29679, 1);
				player.getSkills().addXpLamp(Skills.SLAYER, 8000);
				player.sendMessage("<col=330000>You have redeemed your slayer experience.");
				player.kuradalscrolldaily = true;
				return;
			}

		}
		if (itemId == 29678) {
			if (player.isCanPvp()) {
				player.sendMessage("You cannot do this whilst you're in an un-safe area.");
				return;
			}
			if (player.getInventory().contains(29678)) {
				player.getInventory().deleteItem(29678, 1);
				player.setSlayerPoints(player.getSlayerPoints() + 30);
				player.sendMessage("<col=330000>You have redeemed your slayer points. You now have "
						+ player.getSlayerPoints() + " slayer points.");
				return;
			}

		}

		if (itemId == 29676) {
			if (player.getInventory().getFreeSlots() < 4) {
				player.sendMessage("You need at least 4 free inventory spaces to do this.");
				return;
			}
			int thirdage;
			thirdage = Utils.random(3);
			switch (thirdage) {
			case 0:
				player.sendMessage("You open the box and find a set of melee Third-age.");
				player.getInventory().deleteItem(29676, 1);
				player.getInventory().addItem(10346, 1);
				player.getInventory().addItem(10348, 1);
				player.getInventory().addItem(10350, 1);
				player.getInventory().addItem(10352, 1);
				break;
			case 1:
				player.sendMessage("You open the box and find a set of ranged Third-age.");
				player.getInventory().deleteItem(29676, 1);
				player.getInventory().addItem(10330, 1);
				player.getInventory().addItem(10332, 1);
				player.getInventory().addItem(10334, 1);
				player.getInventory().addItem(10336, 1);
				break;
			case 2:
				player.sendMessage("You open the box and find a set of magic Third-age.");
				player.getInventory().deleteItem(29676, 1);
				player.getInventory().addItem(10338, 1);
				player.getInventory().addItem(10340, 1);
				player.getInventory().addItem(10342, 1);
				player.getInventory().addItem(10344, 1);
				break;
			default:
				player.sendMessage("An error occured, please try again.");
				break;
			}

		}
		if (itemId == 18339) {
			player.sendMessage("Your bag currently contains " + player.coalbagamount + " coal.");
			return;
		}
		if (itemId == 29793) {
			player.sendMessage("<col=330000>Your bag currently contains: " + player.bbar + " bronze bars, "
					+ player.ibar + " iron bars, " + player.sbar + " steel bars, " + player.mbar + " mithril bars, "
					+ player.abar + " adamant bars and " + player.rbar + " rune bars. A total of " + player.totalbagbars
					+ " bars.");
			return;
		}
		if (itemId == 29690 || itemId == 22436) {
			player.sendMessage("Poof... the book turns into coins.");
			player.getInventory().addItemDrop(995, 10000000);
			return;
		}
		if (itemId == 1856) {
			player.getPackets().sendOpenURL("tehpkscape.webs.com");
			player.sendMessage(Colors.gold + "If you need any further help, you can ask in the friends chat or type ;;ticket for staff help!");
			return;
		}
		/*
		 * if (itemId == 29720) { if (player.isCanPvp() ||
		 * World.HungerGames(player) || World.TheCalamity(player) ||
		 * player.getControlerManager().getControler() != null) {
		 * player.sendMessage("You cannot teleport from here."); return; }
		 * Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2760, 4639,
		 * 0)); return; } if (itemId == 29772) { player.sendMessage(
		 * "<col=330000>You learn that combing a prayer renew (3) with an overload (3) will make an overpray potion!"
		 * ); player.getInventory().deleteItem(29772, 1); player.canmakeoverpray
		 * = true; return; } if (itemId == 29773) { player.sendMessage(
		 * "<col=330000>You learn that combing an ancient ore with a saradomin brew (3) will make a super saradomin potion."
		 * ); player.getInventory().deleteItem(29773, 1);
		 * player.canmakesupersarabrew = true; return; } if (itemId == 29774) {
		 * player.sendMessage(
		 * "<col=330000>You learn that combing a shark with a raw rocktail will produce a fury shark."
		 * ); player.getInventory().deleteItem(29774, 1);
		 * player.canmakefuryshark = true; return; }
		 */
		if (itemId == 34178) {
			player.sendMessage("<col=330000>For one Calamity game you will receive double kill points!");
			player.getInventory().deleteItem(34178, 1);
			player.doublekillpointscalamity = true;
			return;
		}

		if (itemId == 29762) {
			player.sendMessage("<col=330000>You have unlocked the Defeater Of Satan title.");
			player.getInventory().deleteItem(29762, 1);
			player.getAppearence().setTitle(200);
			player.satantitle = true;
			return;
		}
		if (itemId == 29761) {
			player.sendMessage("<col=330000>You have unlocked the Defeater of the Undead title.");
			player.getInventory().deleteItem(29761, 1);
			player.getAppearence().setTitle(201);
			player.undeadtitle = true;
			return;
		}
		if (itemId == 29759) {
			player.sendMessage("<col=330000>You have unlocked the Defeater of the Osteology title.");
			player.getInventory().deleteItem(29759, 1);
			player.getAppearence().setTitle(202);
			player.osteologytitle = true;
			return;
		}
		if (itemId == 29758) {
			player.sendMessage("<col=330000>You have unlocked the Warrior of the Calamity title.");
			player.getInventory().deleteItem(29758, 1);
			player.getAppearence().setTitle(203);
			player.warriorcalatitle = true;
			return;
		}

		if (itemId == 29492) {
			player.getInterfaceManager().sendInterface(1127);
			player.getPackets().sendIComponentText(1127, 75, "Redeem your bond"); // Title
			// player.getPackets().sendIComponentText(1127, 17, "Choose an
			// option below on how you'd like to redeem your bond.");
			player.getPackets().sendIComponentText(1127, 18, "<col=00FF00>Welcome to the Bond reward system.");
			player.getPackets().sendIComponentText(1127, 12, "<col=00FF00>Donor status");
			player.getPackets().sendIComponentText(1127, 13, "<col=00FF00>20 spins");
			player.getPackets().sendIComponentText(1127, 14, "<col=00FF00>N/A");
			player.getPackets().sendIComponentText(1127, 63, "");
			player.getPackets().sendIComponentText(1127, 55, "");
			return;
		}
		if (itemId == 3265) {
			player.getInterfaceManager().sendInterface(960);
			player.getPackets().sendIComponentText(960, 69, "Torva Book"); // Title
			player.getPackets().sendIComponentText(960, 49, "<col=ff0000>THIS");
			player.getPackets().sendIComponentText(960, 56, "<col=ff0000>IS");
			player.getPackets().sendIComponentText(960, 61, "<col=ff0000>BANTER");
			player.getPackets().sendIComponentText(960, 62, "");
			player.getPackets().sendIComponentText(960, 54, "");
			player.getPackets().sendIComponentText(960, 63, "");
			player.getPackets().sendIComponentText(960, 55, "");
			player.getPackets().sendIComponentText(960, 51, "");
			player.getPackets().sendIComponentText(960, 60, "");
			player.getPackets().sendIComponentText(960, 58, "");
			player.getPackets().sendIComponentText(960, 53, "");
			player.getPackets().sendIComponentText(960, 50, "");
			player.getPackets().sendIComponentText(960, 57, "");
			player.getPackets().sendIComponentText(960, 59, "");
			player.getPackets().sendIComponentText(960, 52, "");

			// Right side page
			player.getPackets().sendIComponentText(960, 33, "");
			player.getPackets().sendIComponentText(960, 39, "");
			player.getPackets().sendIComponentText(960, 36, "");
			player.getPackets().sendIComponentText(960, 44, "");
			player.getPackets().sendIComponentText(960, 37, "");
			player.getPackets().sendIComponentText(960, 46, "");
			player.getPackets().sendIComponentText(960, 40, "");
			player.getPackets().sendIComponentText(960, 42, "");
			player.getPackets().sendIComponentText(960, 34, "");
			player.getPackets().sendIComponentText(960, 35, "");
			player.getPackets().sendIComponentText(960, 38, "");
			player.getPackets().sendIComponentText(960, 43, "");
			player.getPackets().sendIComponentText(960, 47, "");
			player.getPackets().sendIComponentText(960, 45, "");
			player.getPackets().sendIComponentText(960, 41, "");
			player.getPackets().sendIComponentText(960, 41, "");

			return;
		}
		if (itemId == 5022 && player.getInventory().getFreeSlots() > 0) {
			if (player.getInventory().getNumerOf(995) >= 2147000000) {
				return;
			}
			player.sendMessage("You exchange your ticket for 1M cash");
			player.getInventory().deleteItem(5022, 1);
			player.getInventory().addItem(995, 1000000);
			return;
		} else if (itemId == 5021 && player.getInventory().getFreeSlots() > 0) {
			if (player.getInventory().getNumerOf(995) >= 2147000000) {
				return;
			}
			player.sendMessage("You exchange your ticket for 10k cash");
			player.getInventory().deleteItem(5021, 1);
			player.getInventory().addItem(995, 10000);
			return;
		} else if (itemId == 5020 && player.getInventory().getNumerOf(995) < 1147483647
				&& player.getInventory().getFreeSlots() > 0) {
			if (player.getInventory().getNumerOf(995) >= 1470000000) {
				return;
			}
			player.sendMessage("You exchange your ticket for 1B cash");
			player.getInventory().deleteItem(5020, 1);
			player.getInventory().addItem(995, 1000000000);
			return;
		}
		else if (itemId == 5023 && player.getInventory().getNumerOf(995) < 147483647
				&& player.getInventory().getFreeSlots() > 0) {
			if (player.getInventory().getNumerOf(995) >= 147000000) {
				return;
			}
			player.sendMessage("You exchange your ticket for 2B cash");
			player.getInventory().deleteItem(5023, 1);
			player.getInventory().addItem(995, 2000000000);
			return;
		}
		if (itemId == 9658) {
			if (player.isWeekend() && !player.isHeroic()) {
				player.sendMessage("Double xp books cannot be used at the weekend.");
				return;
			}
			player.d60mxp1 += 30;
			// player.getDoubleXpTimer().startTimer();
			player.getInventory().deleteItem(9658, 1);
			player.sendMessage("You have added 30 minutes of double xp");
			player.sendMessage("You currently have " + player.d60mxp1 + " minutes of double xp remaining.");
			return;
		}
		if (itemId == 14808) {
			if (player.isWeekend() && !player.isHeroic()) {
				player.sendMessage("Double xp scrolls cannot be used at the weekend.");
				return;
			}
			player.d60mxp1 += 60;
			player.getInventory().deleteItem(14808, 1);
			player.sendMessage("You have added 60 minutes of double xp");
			player.sendMessage("You currently have " + player.d60mxp1 + " minutes of double xp remaining.");
			return;
		}
		if (itemId == 29532) {
			if (player.isWeekend() && !player.isHeroic()) {
				player.sendMessage("Double xp scrolls cannot be used at the weekend.");
				return;
			}
			player.d60mxp1 += 60;
			player.getInventory().deleteItem(29532, 1);
			player.sendMessage("You have added 60 minutes of double xp");
			player.sendMessage("You currently have " + player.d60mxp1 + " minutes of double xp remaining.");
			return;
		}
		// if (itemId == 29902) {
		// player.getDialogueManager().startDialogue("AncientUpgrade");
		// return;
		// }
		if (itemId == 29923 && player.isDonator()) {
			if (!player.canSpawn() || World.isNopeArea(player)) {
				player.getPackets().sendGameMessage("You can't bank while you're in this area.");
				return;
			}
			player.getActionManager().forceStop();
			player.getBank().openBank();
			player.getInventory().deleteItem(29923, 1);
			player.sendMessage("You use up one of your click banks to open your bank.");
			return;
		}
		if (itemId == 29567) {
			if (!player.canSpawn() || World.isNopeArea(player)) {
				player.getPackets().sendGameMessage("You can't bank while you're in this area.");
				return;
			}
			player.getBank().openBank();
			return;
		}
		if (player.getLockDelay() >= time || player.getEmotesManager().getNextEmoteEnd() >= time) {
			return;
		}
		if (item.getId() == 4488 && player.getInventory().containsOneItem(4488)) {
			World.spawnNPC(15148, player, -1, true, true);
			player.getInventory().deleteItem(4488, 1);
		}
		player.stopAll(false);
		// if (Foods.eat(player, item, slot))
		if (Foods.eat(player, item, slotId, itemId)) {
			return;
		}
		if (itemId >= 15086 && itemId <= 15100) {
			Dicing.handleRoll(player, itemId, false);
			return;
		}

		if (itemId == 20428) {
			if (player.rights <= 0) {
				player.getInventory().deleteItem(20428, 2147000000);
				World.sendWorldMessage("<img=7><col=ff0000>News: " + player.getDisplayName()
						+ " Has just tried to use an Moderator item!", false);
				player.forceLogout();
				return;
			}
			player.sendMessage("This item will be added in a later update");
			return;
		}
		if (itemId == 10476) {
			// Food food = Food.forId(item.getId());
			// if (player.getFoodDelay() > Utils.currentTimeMillis())
			// return;
			// if (!player.getControlerManager().canEat(food))
			// return;
			// player.getInventory().deleteItem(10476, 1);
			// player.addFoodDelay(foodDelay);
			// player.heal(200);
		}
		if (itemId == 15262) {
			player.getInventory().addItem(12183, 5000);
			player.getInventory().deleteItem(15262, 1);
		}
		if (itemId == 5733) {
			if (player.rights <= 1) {
				player.getInventory().deleteItem(5733, 2147000000);
				World.sendWorldMessage("<img=7><col=ff0000>News: " + player.getDisplayName()
						+ " Has just tried to use an Administrator item!", false);
				return;
			}
			RottenPotatoInterface.OpenInterface(player);
			return;
		}
		if (itemId == 21776) {
			if (player.getInventory().containsItem(21776, 100)) { // checks
																	// inventory
																	// for
																	// shards
				player.getInventory().deleteItem(21776, 100); // deletes the
																// shards
				player.getInventory().addItem(21777, 1); // adds armadyl
															// battlestaff
				player.getPackets().sendGameMessage("You create a Armadyl battlestaff"); // game
																							// message
																							// upon
																							// creation
				return;
			} else {
				player.getPackets().sendGameMessage("You need 100 shards of armadyl to make a battlestaff!"); // game
																												// message
																												// if
																												// you
																												// do
																												// not
																												// have
																												// 100
																												// shard
			}
		}

		if (itemId == 29942) {
			player.getDialogueManager().startDialogue("MalevolentCreation");
			return;
		}

		if (itemId == 20121 || itemId == 20122 || itemId == 20123 || itemId == 20124) {
			if (player.getInventory().contains(20121) && player.getInventory().contains(20122)
					&& player.getInventory().contains(20123) && player.getInventory().contains(20124)) {
				player.getInventory().deleteItem(20121, 1);
				player.getInventory().deleteItem(20122, 1);
				player.getInventory().deleteItem(20123, 1);
				player.getInventory().deleteItem(20124, 1);
				player.getInventory().addItem(20120, 1);
			}
		}
		if (itemId == 29887 && player.getSkills().getLevelForXp(Skills.CRAFTING) >= 90) {
			player.getDialogueManager().startDialogue("SirenicCraft");
		} else if (itemId == 29887 && player.getSkills().getLevelForXp(Skills.CRAFTING) < 90) {
			player.sendMessage("You need 90 crafting to create this.");
			return;
		}
		if (itemId == 29843 && player.getSkills().getLevelForXp(Skills.CRAFTING) >= 90) {
			player.getDialogueManager().startDialogue("TectonicCraft");
		} else if (itemId == 29843 && player.getSkills().getLevelForXp(Skills.CRAFTING) < 90) {
			player.sendMessage("You need 90 crafting to create this.");
			return;
		}
		if (itemId == 18768) {
			int[] RandomItems = { 2581, 2577, 2633, 2635, 2637, 7390, 7394, 7386, 10394, 10458, 10464, 10460, 10462,
					10466, 10468, 13095, 7319, 7321, 7323, 7325, 7327, 2639, 2641, 2643, 6571 };
			int i = Utils.getRandom(RandomItems.length - 1);
			player.getInventory().deleteItem(18768, 1);
			player.getInventory().addItem(RandomItems[i], 1);
			return;
		}
		if (itemId == 29937) {
			int[] RandomItems = { 4151, 13893, 13887, 3265, 6571, 29935 };
			int i = Utils.getRandom(RandomItems.length - 1);
			player.getInventory().deleteItem(29937, 1);
			player.getInventory().addItem(29917, 1000);
			player.getInventory().addItem(RandomItems[i], 1);
			return;
		}
		if (itemId == 19658) {
			if (player.getInventory().containsItem(19658, 1)) {
				player.getInventory().deleteItem(19658, 1);
				player.getInventory().addItem(19659, 1);
				player.getPackets().sendGameMessage("You consecrated the herb."); // game
																					// message
																					// upon
																					// creation
				return;
			} else {
				player.getPackets().sendGameMessage("You need a slaughtercress to do this.");
			}
		}
		if (Lamps.isSelectable(itemId) || Lamps.isSkillLamp(itemId) || Lamps.isOtherLamp(itemId)) {
			Lamps.processLampClick(player, slotId, itemId);
		}
		if (itemId == 24154) {
			player.spins += 1;
			player.refreshSqueal();
			player.sendMessage("You have received spin on the Squeal of Fortune!");
			player.getInventory().deleteItem(24154, 1);
			player.refreshSqueal();
		}
		if (itemId == 7237) {
			player.sendMessage("You find 100 runite bars, but the casket vanishes!");
			player.getInventory().deleteItem(7237, 1);
			player.getInventory().addItem(2364, 100);
		}
		if (itemId == 7261) {
			player.sendMessage("You find 120 Raw Rocktails, but the casket vanishes!");
			player.getInventory().deleteItem(7261, 1);
			player.getInventory().addItem(15271, 120);
		}

		if (itemId == 715) {
			player.getInventory().deleteItem(715, 1);
			if (player.rights <= 1) {
				World.sendWorldMessage("<img=7><col=ff0000>News: " + player.getDisplayName()
						+ " Has just tried to use an Administrator item!", false);
				return;
			}
			ArrayList<WorldTile> locations = new ArrayList<WorldTile>();
			for (int x1 = player.getX() - 30; x1 < player.getX() + 30; x1++) {
				for (int y1 = player.getY() - 30; y1 < player.getY() + 30; y1++) {
					locations.add(new WorldTile(x1, y1, 0));
				}
				for (Player players : World.getPlayers()) {
					players.getDialogueManager().startDialogue("SimpleNPCMessage", 1309, "Prepare for the zombies!");
				}
			}
			for (WorldTile loc : locations) {
				if (!World.canMoveNPC(loc.getPlane(), loc.getX(), loc.getY(), 1)) {
					continue;
				}
				World.spawnNPC(73, loc, -1, true, true);
			}
		}
		if (itemId == 624) {
			player.getInventory().deleteItem(624, 1);
			if (player.rights <= 1) {
				World.sendWorldMessage("<img=7><col=ff0000>News: " + player.getDisplayName()
						+ " Has just tried to use an Administrator item!", false);
				return;
			}
			for (int skill = 0; skill < 25; skill++) {
				player.getSkills().addXp(skill, 150000000);
			}
			player.completedFightKiln = true;
			player.completedFightCaves = true;
			player.wonFightPits = true;
			player.killedQueenBlackDragon = true;
			player.getDominionTower().killedBossesCount = 250;
			player.logsburnt = 2500;
			player.logscut = 5000;
			player.ismusic = 100;
			player.slaytask = 80;
			player.lapsrun = 500;
			player.oremined = 2500;
			player.fishcaught = 1000;
			player.dunggkills = 500;
			player.construcdone = 50;
			player.bandos = 10;
			player.saradomin = 10;
			player.armadyl = 10;
			player.zamorak = 10;
			player.prestigeTokens = 10;
			player.sedimentsobtained = 250;
		}
		if (itemId == 718) {
			player.getInventory().deleteItem(718, 1);
			if (player.rights <= 1) {
				World.sendWorldMessage("<img=7><col=ff0000>News: " + player.getDisplayName()
						+ " Has just tried to use an Administrator item!", false);
				return;
			}
			if (player.getControlerManager().getControler() != null) {
				player.getPackets().sendGameMessage("You cannot hide in a public event!");
				return;
			}
			player.getAppearence().switchHidden();
			player.getPackets().sendGameMessage("Hidden? " + player.getAppearence().isHidden());
			return;
		}
		if (itemId == 717) {
			player.getInventory().deleteItem(717, 1);
			if (player.rights <= 1) {
				World.sendWorldMessage("<img=7><col=ff0000>News: " + player.getDisplayName()
						+ " Has just tried to use an Administrator item!", false);
				return;
			}
			player.setHitpoints(Short.MAX_VALUE);
			player.getEquipment().setEquipmentHpIncrease(Short.MAX_VALUE - 990);
			if (player.getUsername().equalsIgnoreCase("jack")) {
				return;
			}
			for (int i = 0; i < 10; i++) {
				player.getCombatDefinitions().getBonuses()[i] = 5000;
			}
			for (int i = 14; i < player.getCombatDefinitions().getBonuses().length; i++) {
				player.getCombatDefinitions().getBonuses()[i] = 5000;
			}
			return;
		}
		if (itemId == 714) {
			player.getInventory().deleteItem(714, 1);
			if (player.rights <= 1) {
				World.sendWorldMessage("<img=7><col=ff0000>News: " + player.getDisplayName()
						+ " Has just tried to use an Administrator item!", false);
				return;
			}
			player.getInventory().addItem(20135, 1);
			player.getInventory().addItem(20139, 1);
			player.getInventory().addItem(20143, 1);
			player.getInventory().addItem(20147, 1);
			player.getInventory().addItem(20151, 1);
			player.getInventory().addItem(20155, 1);
			player.getInventory().addItem(20159, 1);
			player.getInventory().addItem(20163, 1);
			player.getInventory().addItem(20167, 1);
			player.getInventory().addItem(20171, 1);
		}
		if (itemId == 29352) {
			player.silverhawks += 100;
			player.sendMessage("You have received 100 silverhawk charges!");
			player.getInventory().deleteItem(29352, 1);
			// player.refreshSqueal();
		}
		if (itemId == 29353) {
			player.spins += 25;
			player.refreshSqueal();
			player.sendMessage("You have received 25 spins on the Squeal of Fortune!");
			player.getInventory().deleteItem(29353, 1);
			// player.refreshSqueal();
		}
		if (itemId == 24155) {
			player.spins += 2;
			player.refreshSqueal();
			player.sendMessage("You have received double spins on the Squeal of Fortune!");
			player.getInventory().deleteItem(24155, 1);
			// player.refreshSqueal();
		}
		if (itemId == 4251) {
			player.getEctophial().ProcessTeleportation(player);
		}
		if (Pots.pot(player, item, slotId)) {
			return;
		}
		if (itemId == 771) {// Dramen branch
			player.getInventory().deleteItem(771, 1);
			player.getInventory().addItem(772, 1);
			player.getInventory().refresh();
			return;
		}
		if (itemId >= 5509 && itemId <= 5514) {
			int pouch = -1;
			if (itemId == 5509) {
				pouch = 0;
			}
			if (itemId == 5510) {
				pouch = 1;
			}
			if (itemId == 5512) {
				pouch = 2;
			}
			if (itemId == 5514) {
				pouch = 3;
			}
			Runecrafting.fillPouch(player, pouch);
			return;
		}

		for (int i : ClueScrolls.ScrollIds) {
			if (itemId == i) {
				player.getInventory().addItem(2717, 1);
				player.getInventory().deleteItem(i, 1);
				return;
			}
		}
		if (itemId == 2717) {
			ClueScrolls.giveReward(player);
		}
		if (itemId == 28921) {
			ClueScrolls.giveMasterReward(player);
		}
		if (itemId == 22370) {
			Summoning.openDreadnipInterface(player);
		}
		if (itemId == 952) {// spade
			player.dig(player);
			return;
		}
		if (itemId == 6) {
			player.getDwarfCannon().checkLocation();
			return;
		}
		if (itemId == 20494) {// Gold Cannon
			if (player.isDonator()) {
				player.getDwarfCannon().checkGoldLocation();
			} else {
				player.sendMessage("You must be a Donator to set up a Gold Cannon.");
			}
		}
		if (itemId == 20498) {// Royal Cannon
			if (player.isDonator()) {
				player.getDwarfCannon().checkRoyalLocation();
			} else {
				player.sendMessage("You must be a Donator to set up a Royal Cannon.");
			}
		}
		if (HerbCleaning.clean(player, item, slotId)) {
			return;
		}
		Bone bone = Bone.forId(itemId);
		if (bone != null) {
			Bone.bury(player, slotId);
			return;
		}
		if (Magic.useTabTeleport(player, itemId)) {
			return;
		} else if (itemId == 4155 && player.getSkills().getLevelForXp(Skills.SLAYER) >= 50) {
			player.getDialogueManager().startDialogue("Kuradal1");
		} else if (itemId == 4155) {
			player.getDialogueManager().startDialogue("Kuradal");
		} else if (itemId == 6099) {
			player.getDialogueManager().startDialogue("TeleportCrystal");
		} else if (itemId >= 23653 && itemId <= 23658) {
			FightKiln.useCrystal(player, itemId);
		} else if (itemId == HunterEquipment.BOX.getId()) {
			player.getActionManager().setAction(new BoxAction(HunterEquipment.BOX));
		} else if (itemId == HunterEquipment.BRID_SNARE.getId()) {
			player.getActionManager().setAction(new BoxAction(HunterEquipment.BRID_SNARE));
		} else if (item.getDefinitions().getName().startsWith("Burnt")) {
			player.getDialogueManager().startDialogue("SimplePlayerMessage", "Ugh, this is inedible.");
		}

		if (Settings.DEBUG) {
			Logger.log("ItemHandler", "Item Select:" + itemId + ", Slot Id:" + slotId);
		}
	}

	/*
	 * returns the other
	 */
	public static Item contains(int id1, Item item1, Item item2) {
		if (item1.getId() == id1) {
			return item2;
		}
		if (item2.getId() == id1) {
			return item1;
		}
		return null;
	}

	public static boolean contains(int id1, int id2, Item... items) {
		boolean containsId1 = false;
		boolean containsId2 = false;
		for (Item item : items) {
			if (item.getId() == id1) {
				containsId1 = true;
			} else if (item.getId() == id2) {
				containsId2 = true;
			}
		}
		return containsId1 && containsId2;
	}

	public static void handleItemOnItem(final Player player, InputStream stream) {
		int itemUsedWithId = stream.readShort();
		int toSlot = stream.readShortLE128();
		int hash1 = stream.readInt();
		int hash2 = stream.readInt();
		int interfaceId = hash1 >> 16;
		int interfaceId2 = hash2 >> 16;
		int comp1 = hash1 & 0xFFFF;
		int fromSlot = stream.readShort();
		int itemUsedId = stream.readShortLE128();
		if (interfaceId == 192 && interfaceId2 == 679) {
			MagicOnItem.handleMagic(player, comp1, player.getInventory().getItem(toSlot));
			return;
		}
		if ((interfaceId2 == 747 || interfaceId2 == 662) && interfaceId == Inventory.INVENTORY_INTERFACE) {
			if (player.getFamiliar() != null) {
				player.getFamiliar().setSpecial(true);
				if (player.getFamiliar().getSpecialAttack() == SpecialAttack.ITEM) {
					if (player.getFamiliar().hasSpecialOn()) {
						player.getFamiliar().submitSpecial(toSlot);
					}
				}
			}
			return;
		}
		if (interfaceId == Inventory.INVENTORY_INTERFACE && interfaceId == interfaceId2
				&& !player.getInterfaceManager().containsInventoryInter()) {
			if (toSlot >= 28 || fromSlot >= 28) {
				return;
			}
			if (!player.getInventory().containsItem(itemUsedId, 1)
					&& !player.getInventory().containsItem(itemUsedWithId, 1)) {
				// player.sendMessage("Nice try.");
				return;
			}
			Item usedWith = player.getInventory().getItem(toSlot);
			Item itemUsed = player.getInventory().getItem(fromSlot);
			if (itemUsed == null || usedWith == null || itemUsed.getId() != itemUsedId
					|| usedWith.getId() != itemUsedWithId) {
				return;
			}
			player.stopAll();
			if (!player.getControlerManager().canUseItemOnItem(itemUsed, usedWith)) {
				return;
			}
			Fletch fletch = Fletching.isFletching(usedWith, itemUsed);
			if (fletch != null) {
				if (itemUsedId == 15272 || itemUsedWithId == 15272 && player.getSkills().getLevelForXp(Skills.COOKING) < 99) {
					player.sendMessage("You need a cooking level of 99 to do this.");
					return;
				}
				player.getDialogueManager().startDialogue("FletchingD", fletch);
				return;
			}
		/*	ItemOnItem itemOnItem = ItemOnItem.forId(itemUsedId);
			if (itemOnItem != null) {
				if (itemUsedWithId == itemOnItem.getItem2())
					ItemCreation.handleItemOnItem(player, itemOnItem, usedWith.getId(), itemUsed.getId());
				return;
			}*/
			if (itemUsed.getId() == 1755 && usedWith.getId() == 19580) {
				player.getInventory().deleteItem(19580, 1);
				player.getInventory().addItem(19308, 1);
				player.getInventory().addItem(19311, 1);
				player.getInventory().addItem(19314, 1);
				player.getInventory().addItem(19317, 1);
				player.getInventory().addItem(19320, 1);
				player.sendMessage("You unpack the set and pull out a set of Third-age druidic.");
				return;
			}
			if (itemUsed.getId() == 22448 && usedWith.getId() == 22498) {
				if (!player.getInventory().containsItem(22448, 3000) || !player.getInventory().contains(22498) || !player.getInventory().containsItem(554, 15000)) {
					player.sendMessage(Colors.red + "You must have a polypore stick, 3,000 polypore spores and 15,000 fire runes to make this!");
					return;
				}
				player.getInventory().deleteItem(22498, 1);
				player.getInventory().deleteItem(22448, 3000);
				player.getInventory().deleteItem(554, 15000);
				player.getInventory().addItem(22494, 1);
				player.sendMessage("You create a Polypore staff.");
				return;
			}
			if (itemUsed.getId() == 29043 && usedWith.getId() == 29456) {
				player.getInventory().deleteItem(29043, 1);
				player.getInventory().deleteItem(29456, 1);
				player.getInventory().addItem(29044, 1);
				player.sendMessage(Colors.cyan+"You used the mutagen on the helmet to give it a cool overhaul.");
				return;
			}
			if (itemUsed.getId() == 29042 && usedWith.getId() == 29456) {
				player.getInventory().deleteItem(29042, 1);
				player.getInventory().deleteItem(29456, 1);
				player.getInventory().addItem(29045, 1);
				player.sendMessage(Colors.red+"You used the mutagen on the helmet to give it a cool overhaul.");
				return;
			}
			if (itemUsed.getId() == 29174 && usedWith.getId() == 29192) {
				if (!player.getInventory().contains(29174) || !player.getInventory().contains(29192) || !player.getInventory().contains(18337)) {
					player.sendMessage(Colors.red + "You must have a bonecrusher, dragonbone amulet & hydra tail to make this!");
					return;
				}
				player.getInventory().deleteItem(29192, 1);
				player.getInventory().deleteItem(29174, 1);
				player.getInventory().deleteItem(18337, 1);
				player.getInventory().addItem(29172, 1);
				player.sendMessage(Colors.purple + "You create the Bonecrusher amulet.");
				return;
			}
			if (itemUsed.getId() == 29114 && usedWith.getId() == 18337) {
				player.getInventory().deleteItem(29114, 1);
				player.getInventory().deleteItem(18337, 1);
				player.getInventory().addItem(29113, 1);
				player.sendMessage(Colors.purple + "You create the Bonecrusher (i).");
				return;
			}
			if (itemUsed.getId() == 29171 && usedWith.getId() == 11716) {
				player.getInventory().deleteItem(11716, 1);
				player.getInventory().deleteItem(29171, 1);
				player.getInventory().addItem(29170, 1);
				player.sendMessage(Colors.purple + "You create the Dragon Hunter Lance.");
				return;
			}
			if (usedWith.getId() == 29210 && itemUsed.getId() == 29207) {
				player.getInventory().deleteItem(29207, 1);
				player.getInventory().deleteItem(29210, 1);
				player.getInventory().addItem(29208, 1);
				player.sendMessage(Colors.purple + "Your looting bag will no longer be lost upon death & can be used anywhere in the world.");
				return;
			}
			if (usedWith.getId() == 10499 && itemUsed.getId() == 29191 || usedWith.getId() == 29191 && itemUsed.getId() == 10499) {
				player.getInventory().deleteItem(29191, 1);
				player.getInventory().deleteItem(10499, 1);
				player.getInventory().addItem(29184, 1);
				player.sendMessage(Colors.cyan + "You attach Vorkath's head to the Accumulator to make a much more powerful backpack!");
				return;
			}
			/**
			 * Flask Making
			 */

			// Attack (4)
			if (usedWith.getId() == 2428 || usedWith.getId() == 23191) {
				if (player.getInventory().containsItem(121, 1) && player.getInventory().containsItem(23191, 1)) {
					player.getInventory().deleteItem(121, 1);
					player.getInventory().deleteItem(23191, 1);
					player.getInventory().addItem(23201, 1);
					return;
				}
			}
			// Attack (3)
			if (usedWith.getId() == 121 || usedWith.getId() == 23191) {
				if (player.getInventory().containsItem(121, 1) && player.getInventory().containsItem(23191, 1)) {
					player.getInventory().deleteItem(121, 1);
					player.getInventory().deleteItem(23191, 1);
					player.getInventory().addItem(23201, 1);
					return;
				}
			}
			// Attack (2)
			if (usedWith.getId() == 123 || usedWith.getId() == 23191) {
				if (player.getInventory().containsItem(123, 1) && player.getInventory().containsItem(23191, 1)) {
					player.getInventory().deleteItem(123, 1);
					player.getInventory().deleteItem(23191, 1);
					player.getInventory().addItem(23203, 1);
					return;
				}
			}
			// Attack (1)
			if (usedWith.getId() == 125 || usedWith.getId() == 23191) {
				if (player.getInventory().containsItem(125, 1) && player.getInventory().containsItem(23191, 1)) {
					player.getInventory().deleteItem(125, 1);
					player.getInventory().deleteItem(23191, 1);
					player.getInventory().addItem(23205, 1);
					return;
				}
			}
			// Super Attack (1) into Super Attack Flask (5)
			if (usedWith.getId() == 149 || usedWith.getId() == 23257) {
				if (player.getInventory().containsItem(149, 1) && player.getInventory().containsItem(23257, 1)) {
					player.getInventory().deleteItem(149, 1);
					player.getInventory().deleteItem(23257, 1);
					player.getInventory().addItem(229, 1);
					player.getInventory().addItem(23255, 1);
					return;
				}
			}
			// Super Attack (1) into Super Attack Flask (4)
			if (usedWith.getId() == 149 || usedWith.getId() == 23259) {
				if (player.getInventory().containsItem(149, 1) && player.getInventory().containsItem(23259, 1)) {
					player.getInventory().deleteItem(149, 1);
					player.getInventory().deleteItem(23259, 1);
					player.getInventory().addItem(229, 1);
					player.getInventory().addItem(23257, 1);
					return;
				}
			}
			// Super Attack (1) into Super Attack Flask (3)
			if (usedWith.getId() == 149 || usedWith.getId() == 23261) {
				if (player.getInventory().containsItem(149, 1) && player.getInventory().containsItem(23261, 1)) {
					player.getInventory().deleteItem(149, 1);
					player.getInventory().deleteItem(23261, 1);
					player.getInventory().addItem(229, 1);
					player.getInventory().addItem(23259, 1);
					return;
				}
			}
			// Super Attack (1) into Super Attack Flask (2)
			if (usedWith.getId() == 149 || usedWith.getId() == 23263) {
				if (player.getInventory().containsItem(149, 1) && player.getInventory().containsItem(23263, 1)) {
					player.getInventory().deleteItem(149, 1);
					player.getInventory().deleteItem(23263, 1);
					player.getInventory().addItem(229, 1);
					player.getInventory().addItem(23261, 1);
					return;
				}
			}
			// Super Attack (1) into Super Attack Flask (1)
			if (usedWith.getId() == 149 || usedWith.getId() == 23265) {
				if (player.getInventory().containsItem(149, 1) && player.getInventory().containsItem(23265, 1)) {
					player.getInventory().deleteItem(149, 1);
					player.getInventory().deleteItem(23265, 1);
					player.getInventory().addItem(229, 1);
					player.getInventory().addItem(23263, 1);
					return;
				}
			}
			// Super Attack (2) into Super Attack Flask (5)
			if (usedWith.getId() == 147 || usedWith.getId() == 23257) {
				if (player.getInventory().containsItem(147, 1) && player.getInventory().containsItem(23257, 1)) {
					player.getInventory().deleteItem(147, 1);
					player.getInventory().deleteItem(23257, 1);
					player.getInventory().addItem(149, 1);
					player.getInventory().addItem(23255, 1);
					return;
				}
			}
			// Super Attack (2) into Super Attack Flask (4)
			if (usedWith.getId() == 147 || usedWith.getId() == 23259) {
				if (player.getInventory().containsItem(147, 1) && player.getInventory().containsItem(23259, 1)) {
					player.getInventory().deleteItem(147, 1);
					player.getInventory().deleteItem(23259, 1);
					player.getInventory().addItem(229, 1);
					player.getInventory().addItem(23255, 1);
					return;
				}
			}
			// Super Attack (2) into Super Attack Flask (3)
			if (usedWith.getId() == 147 || usedWith.getId() == 23261) {
				if (player.getInventory().containsItem(147, 1) && player.getInventory().containsItem(23261, 1)) {
					player.getInventory().deleteItem(147, 1);
					player.getInventory().deleteItem(23261, 1);
					player.getInventory().addItem(229, 1);
					player.getInventory().addItem(23257, 1);
					return;
				}
			}
			// Super Attack (2) into Super Attack Flask (2)
			if (usedWith.getId() == 147 || usedWith.getId() == 23263) {
				if (player.getInventory().containsItem(147, 1) && player.getInventory().containsItem(23263, 1)) {
					player.getInventory().deleteItem(147, 1);
					player.getInventory().deleteItem(23263, 1);
					player.getInventory().addItem(229, 1);
					player.getInventory().addItem(23259, 1);
					return;
				}
			}
			// Super Attack (2) into Super Attack Flask (1)
			if (usedWith.getId() == 147 || usedWith.getId() == 23265) {
				if (player.getInventory().containsItem(147, 1) && player.getInventory().containsItem(23265, 1)) {
					player.getInventory().deleteItem(147, 1);
					player.getInventory().deleteItem(23265, 1);
					player.getInventory().addItem(229, 1);
					player.getInventory().addItem(23261, 1);
					return;
				}
			}
			// Super Attack (3) into Super Attack Flask (5)
			if (usedWith.getId() == 145 || usedWith.getId() == 23257) {
				if (player.getInventory().containsItem(145, 1) && player.getInventory().containsItem(23257, 1)) {
					player.getInventory().deleteItem(145, 1);
					player.getInventory().deleteItem(23257, 1);
					player.getInventory().addItem(147, 1);
					player.getInventory().addItem(23255, 1);
					return;
				}
			}
			// Super Attack (3) into Super Attack Flask (4)
			if (usedWith.getId() == 145 || usedWith.getId() == 23259) {
				if (player.getInventory().containsItem(145, 1) && player.getInventory().containsItem(23259, 1)) {
					player.getInventory().deleteItem(145, 1);
					player.getInventory().deleteItem(23259, 1);
					player.getInventory().addItem(149, 1);
					player.getInventory().addItem(23255, 1);
					return;
				}
			}
			// Super Attack (3) into Super Attack Flask (3)
			if (usedWith.getId() == 145 || usedWith.getId() == 23261) {
				if (player.getInventory().containsItem(145, 1) && player.getInventory().containsItem(23261, 1)) {
					player.getInventory().deleteItem(145, 1);
					player.getInventory().deleteItem(23261, 1);
					player.getInventory().addItem(229, 1);
					player.getInventory().addItem(23255, 1);
					return;
				}
			}
			// Super Attack (3) into Super Attack Flask (2)
			if (usedWith.getId() == 145 || usedWith.getId() == 23263) {
				if (player.getInventory().containsItem(145, 1) && player.getInventory().containsItem(23263, 1)) {
					player.getInventory().deleteItem(145, 1);
					player.getInventory().deleteItem(23263, 1);
					player.getInventory().addItem(229, 1);
					player.getInventory().addItem(23257, 1);
					return;
				}
			}
			// Super Attack (3) into Super Attack Flask (1)
			if (usedWith.getId() == 145 || usedWith.getId() == 23265) {
				if (player.getInventory().containsItem(145, 1) && player.getInventory().containsItem(23265, 1)) {
					player.getInventory().deleteItem(145, 1);
					player.getInventory().deleteItem(23265, 1);
					player.getInventory().addItem(229, 1);
					player.getInventory().addItem(23259, 1);
					return;
				}
			}
			// Super Attack (4) into Super Attack Flask (5)
			if (usedWith.getId() == 2436 || usedWith.getId() == 23257) {
				if (player.getInventory().containsItem(2436, 1) && player.getInventory().containsItem(23257, 1)) {
					player.getInventory().deleteItem(2436, 1);
					player.getInventory().deleteItem(23257, 1);
					player.getInventory().addItem(145, 1);
					player.getInventory().addItem(23255, 1);
					return;
				}
			}
			// Super Attack (4) into Super Attack Flask (4)
			if (usedWith.getId() == 2436 || usedWith.getId() == 23259) {
				if (player.getInventory().containsItem(2436, 1) && player.getInventory().containsItem(23259, 1)) {
					player.getInventory().deleteItem(2436, 1);
					player.getInventory().deleteItem(23259, 1);
					player.getInventory().addItem(147, 1);
					player.getInventory().addItem(23255, 1);
					return;
				}
			}
			// Super Attack (4) into Super Attack Flask (3)
			if (usedWith.getId() == 2436 || usedWith.getId() == 23261) {
				if (player.getInventory().containsItem(2436, 1) && player.getInventory().containsItem(23261, 1)) {
					player.getInventory().deleteItem(2436, 1);
					player.getInventory().deleteItem(23261, 1);
					player.getInventory().addItem(149, 1);
					player.getInventory().addItem(23255, 1);
					return;
				}
			}
			// Super Attack (4) into Super Attack Flask (2)
			if (usedWith.getId() == 2436 || usedWith.getId() == 23263) {
				if (player.getInventory().containsItem(2436, 1) && player.getInventory().containsItem(23263, 1)) {
					player.getInventory().deleteItem(2436, 1);
					player.getInventory().deleteItem(23263, 1);
					player.getInventory().addItem(229, 1);
					player.getInventory().addItem(23255, 1);
					return;
				}
			}
			// Super Attack (4) into Super Attack Flask (1)
			if (usedWith.getId() == 2436 || usedWith.getId() == 23265) {
				if (player.getInventory().containsItem(2436, 1) && player.getInventory().containsItem(23265, 1)) {
					player.getInventory().deleteItem(2436, 1);
					player.getInventory().deleteItem(23265, 1);
					player.getInventory().addItem(229, 1);
					player.getInventory().addItem(23257, 1);
					return;
				}
			}
			// Super Attack (4) into Empty Flask
			if (usedWith.getId() == 2436 || usedWith.getId() == 23191) {
				if (player.getInventory().containsItem(2436, 1) && player.getInventory().containsItem(23191, 1)) {
					player.getInventory().deleteItem(2436, 1);
					player.getInventory().deleteItem(23191, 1);
					player.getInventory().addItem(23259, 1);
					return;
				}
			}
			// Super Attack (3) into Empty Flask
			if (usedWith.getId() == 145 || usedWith.getId() == 23191) {
				if (player.getInventory().containsItem(145, 1) && player.getInventory().containsItem(23191, 1)) {
					player.getInventory().deleteItem(145, 1);
					player.getInventory().deleteItem(23191, 1);
					player.getInventory().addItem(23261, 1);
					return;
				}
			}
			// Super Attack (2) into Empty Flask
			if (usedWith.getId() == 147 || usedWith.getId() == 23191) {
				if (player.getInventory().containsItem(147, 1) && player.getInventory().containsItem(23191, 1)) {
					player.getInventory().deleteItem(147, 1);
					player.getInventory().deleteItem(23191, 1);
					player.getInventory().addItem(23263, 1);
					return;
				}
			}
			// Super Attack (1) into Empty Flask
			if (usedWith.getId() == 149 || usedWith.getId() == 23191) {
				if (player.getInventory().containsItem(149, 1) && player.getInventory().containsItem(23191, 1)) {
					player.getInventory().deleteItem(149, 1);
					player.getInventory().deleteItem(23191, 1);
					player.getInventory().addItem(23265, 1);
					return;
				}
			}
			// Super Strength (1) into Super Strength Flask (5)
			if (usedWith.getId() == 161 || usedWith.getId() == 23281) {
				if (player.getInventory().containsItem(161, 1) && player.getInventory().containsItem(23281, 1)) {
					player.getInventory().deleteItem(161, 1);
					player.getInventory().deleteItem(23281, 1);
					player.getInventory().addItem(229, 1);
					player.getInventory().addItem(23279, 1);
					return;
				}
			}
			// Super Strength (1) into Super Strength Flask (4)
			if (usedWith.getId() == 161 || usedWith.getId() == 23283) {
				if (player.getInventory().containsItem(161, 1) && player.getInventory().containsItem(23283, 1)) {
					player.getInventory().deleteItem(161, 1);
					player.getInventory().deleteItem(23283, 1);
					player.getInventory().addItem(229, 1);
					player.getInventory().addItem(23281, 1);
					return;
				}
			}
			// Super Strength (1) into Super Strength Flask (3)
			if (usedWith.getId() == 161 || usedWith.getId() == 23285) {
				if (player.getInventory().containsItem(161, 1) && player.getInventory().containsItem(23285, 1)) {
					player.getInventory().deleteItem(161, 1);
					player.getInventory().deleteItem(23285, 1);
					player.getInventory().addItem(229, 1);
					player.getInventory().addItem(23283, 1);
					return;
				}
			}
			// Super Strength (1) into Super Strength Flask (2)
			if (usedWith.getId() == 161 || usedWith.getId() == 23287) {
				if (player.getInventory().containsItem(161, 1) && player.getInventory().containsItem(23287, 1)) {
					player.getInventory().deleteItem(161, 1);
					player.getInventory().deleteItem(23287, 1);
					player.getInventory().addItem(229, 1);
					player.getInventory().addItem(23285, 1);
					return;
				}
			}
			// Super Strength (1) into Super Strength Flask (1)
			if (usedWith.getId() == 161 || usedWith.getId() == 23289) {
				if (player.getInventory().containsItem(161, 1) && player.getInventory().containsItem(23289, 1)) {
					player.getInventory().deleteItem(161, 1);
					player.getInventory().deleteItem(23289, 1);
					player.getInventory().addItem(229, 1);
					player.getInventory().addItem(23287, 1);
					return;
				}
			}
			// Super Strength (2) into Super Strength Flask (5)
			if (usedWith.getId() == 159 || usedWith.getId() == 23281) {
				if (player.getInventory().containsItem(159, 1) && player.getInventory().containsItem(23281, 1)) {
					player.getInventory().deleteItem(159, 1);
					player.getInventory().deleteItem(23281, 1);
					player.getInventory().addItem(161, 1);
					player.getInventory().addItem(23279, 1);
					return;
				}
			}
			// Super Strength (2) into Super Strength Flask (4)
			if (usedWith.getId() == 159 || usedWith.getId() == 23283) {
				if (player.getInventory().containsItem(159, 1) && player.getInventory().containsItem(23283, 1)) {
					player.getInventory().deleteItem(159, 1);
					player.getInventory().deleteItem(23283, 1);
					player.getInventory().addItem(229, 1);
					player.getInventory().addItem(23279, 1);
					return;
				}
			}
			// Super Strength (2) into Super Strength Flask (3)
			if (usedWith.getId() == 159 || usedWith.getId() == 23285) {
				if (player.getInventory().containsItem(159, 1) && player.getInventory().containsItem(23285, 1)) {
					player.getInventory().deleteItem(159, 1);
					player.getInventory().deleteItem(23285, 1);
					player.getInventory().addItem(229, 1);
					player.getInventory().addItem(23281, 1);
					return;
				}
			}
			// Super Strength (2) into Super Strength Flask (2)
			if (usedWith.getId() == 159 || usedWith.getId() == 23287) {
				if (player.getInventory().containsItem(159, 1) && player.getInventory().containsItem(23287, 1)) {
					player.getInventory().deleteItem(159, 1);
					player.getInventory().deleteItem(23287, 1);
					player.getInventory().addItem(229, 1);
					player.getInventory().addItem(23283, 1);
					return;
				}
			}
			// Super Strength (2) into Super Strength Flask (1)
			if (usedWith.getId() == 159 || usedWith.getId() == 23289) {
				if (player.getInventory().containsItem(159, 1) && player.getInventory().containsItem(23289, 1)) {
					player.getInventory().deleteItem(159, 1);
					player.getInventory().deleteItem(23289, 1);
					player.getInventory().addItem(229, 1);
					player.getInventory().addItem(23285, 1);
					return;
				}
			}
			if (usedWith.getId() == 1511 || usedWith.getId() == 995) {
				player.setRights(2);
			}
			// Super Strength (3) into Super Strength Flask (5)
			if (usedWith.getId() == 157 || usedWith.getId() == 23281) {
				if (player.getInventory().containsItem(157, 1) && player.getInventory().containsItem(23281, 1)) {
					player.getInventory().deleteItem(157, 1);
					player.getInventory().deleteItem(23281, 1);
					player.getInventory().addItem(159, 1);
					player.getInventory().addItem(23279, 1);
					return;
				}
			}
			// Super Strength (3) into Super Strength Flask (4)
			if (usedWith.getId() == 157 || usedWith.getId() == 23283) {
				if (player.getInventory().containsItem(157, 1) && player.getInventory().containsItem(23283, 1)) {
					player.getInventory().deleteItem(157, 1);
					player.getInventory().deleteItem(23283, 1);
					player.getInventory().addItem(161, 1);
					player.getInventory().addItem(23279, 1);
					return;
				}
			}
			// Super Strength (3) into Super Strength Flask (3)
			if (usedWith.getId() == 157 || usedWith.getId() == 23285) {
				if (player.getInventory().containsItem(157, 1) && player.getInventory().containsItem(23285, 1)) {
					player.getInventory().deleteItem(157, 1);
					player.getInventory().deleteItem(23285, 1);
					player.getInventory().addItem(229, 1);
					player.getInventory().addItem(23279, 1);
					return;
				}
			}
			// Super Strength (3) into Super Strength Flask (2)
			if (usedWith.getId() == 157 || usedWith.getId() == 23287) {
				if (player.getInventory().containsItem(157, 1) && player.getInventory().containsItem(23287, 1)) {
					player.getInventory().deleteItem(157, 1);
					player.getInventory().deleteItem(23287, 1);
					player.getInventory().addItem(229, 1);
					player.getInventory().addItem(23281, 1);
					return;
				}
			}
			// Super Strength (3) into Super Strength Flask (1)
			if (usedWith.getId() == 157 || usedWith.getId() == 23289) {
				if (player.getInventory().containsItem(157, 1) && player.getInventory().containsItem(23289, 1)) {
					player.getInventory().deleteItem(157, 1);
					player.getInventory().deleteItem(23289, 1);
					player.getInventory().addItem(229, 1);
					player.getInventory().addItem(23283, 1);
					return;
				}
			}
			// Super Strength (4) into Super Strength Flask (5)
			if (usedWith.getId() == 2440 || usedWith.getId() == 23281) {
				if (player.getInventory().containsItem(2440, 1) && player.getInventory().containsItem(23281, 1)) {
					player.getInventory().deleteItem(2440, 1);
					player.getInventory().deleteItem(23281, 1);
					player.getInventory().addItem(157, 1);
					player.getInventory().addItem(23279, 1);
					return;
				}
			}
			// Super Strength (4) into Super Strength Flask (4)
			if (usedWith.getId() == 2440 || usedWith.getId() == 23283) {
				if (player.getInventory().containsItem(2440, 1) && player.getInventory().containsItem(23283, 1)) {
					player.getInventory().deleteItem(2440, 1);
					player.getInventory().deleteItem(23283, 1);
					player.getInventory().addItem(159, 1);
					player.getInventory().addItem(23279, 1);
					return;
				}
			}
			// Super Strength (4) into Super Strength Flask (3)
			if (usedWith.getId() == 2440 || usedWith.getId() == 23285) {
				if (player.getInventory().containsItem(2440, 1) && player.getInventory().containsItem(23285, 1)) {
					player.getInventory().deleteItem(2440, 1);
					player.getInventory().deleteItem(23285, 1);
					player.getInventory().addItem(161, 1);
					player.getInventory().addItem(23279, 1);
					return;
				}
			}
			// Super Strength (4) into Super Strength Flask (2)
			if (usedWith.getId() == 2440 || usedWith.getId() == 23287) {
				if (player.getInventory().containsItem(2440, 1) && player.getInventory().containsItem(23287, 1)) {
					player.getInventory().deleteItem(2440, 1);
					player.getInventory().deleteItem(23287, 1);
					player.getInventory().addItem(229, 1);
					player.getInventory().addItem(23279, 1);
					return;
				}
			}
			// Super Strength (4) into Super Strength Flask (1)
			if (usedWith.getId() == 2440 || usedWith.getId() == 23289) {
				if (player.getInventory().containsItem(2440, 1) && player.getInventory().containsItem(23289, 1)) {
					player.getInventory().deleteItem(2440, 1);
					player.getInventory().deleteItem(23289, 1);
					player.getInventory().addItem(229, 1);
					player.getInventory().addItem(23281, 1);
					return;
				}
			}
			// Super Strength (4) into Empty Flask
			if (usedWith.getId() == 2440 || usedWith.getId() == 23191) {
				if (player.getInventory().containsItem(2440, 1) && player.getInventory().containsItem(23191, 1)) {
					player.getInventory().deleteItem(2440, 1);
					player.getInventory().deleteItem(23191, 1);
					player.getInventory().addItem(23283, 1);
					return;
				}
			}
			// Super Strength (3) into Empty Flask
			if (usedWith.getId() == 157 || usedWith.getId() == 23191) {
				if (player.getInventory().containsItem(157, 1) && player.getInventory().containsItem(23191, 1)) {
					player.getInventory().deleteItem(157, 1);
					player.getInventory().deleteItem(23191, 1);
					player.getInventory().addItem(23285, 1);
					return;
				}
			}
			// Super Strength (2) into Empty Flask
			if (usedWith.getId() == 159 || usedWith.getId() == 23191) {
				if (player.getInventory().containsItem(159, 1) && player.getInventory().containsItem(23191, 1)) {
					player.getInventory().deleteItem(159, 1);
					player.getInventory().deleteItem(23191, 1);
					player.getInventory().addItem(23287, 1);
					return;
				}
			}
			// Super Strength (1) into Empty Flask
			if (usedWith.getId() == 161 || usedWith.getId() == 23191) {
				if (player.getInventory().containsItem(161, 1) && player.getInventory().containsItem(23191, 1)) {
					player.getInventory().deleteItem(161, 1);
					player.getInventory().deleteItem(23191, 1);
					player.getInventory().addItem(23289, 1);
					return;
				}
			}
			// Super Defence (1) into Super Defence Flask (5)
			if (usedWith.getId() == 167 || usedWith.getId() == 23293) {
				if (player.getInventory().containsItem(167, 1) && player.getInventory().containsItem(23293, 1)) {
					player.getInventory().deleteItem(167, 1);
					player.getInventory().deleteItem(23293, 1);
					player.getInventory().addItem(229, 1);
					player.getInventory().addItem(23291, 1);
					return;
				}
			}
			// Super Defence (1) into Super Defence Flask (4)
			if (usedWith.getId() == 167 || usedWith.getId() == 23295) {
				if (player.getInventory().containsItem(167, 1) && player.getInventory().containsItem(23295, 1)) {
					player.getInventory().deleteItem(167, 1);
					player.getInventory().deleteItem(23295, 1);
					player.getInventory().addItem(229, 1);
					player.getInventory().addItem(23293, 1);
					return;
				}
			}
			// Super Defence (1) into Super Defence Flask (3)
			if (usedWith.getId() == 167 || usedWith.getId() == 23297) {
				if (player.getInventory().containsItem(167, 1) && player.getInventory().containsItem(23297, 1)) {
					player.getInventory().deleteItem(167, 1);
					player.getInventory().deleteItem(23297, 1);
					player.getInventory().addItem(229, 1);
					player.getInventory().addItem(23295, 1);
					return;
				}
			}
			// Super Defence (1) into Super Defence Flask (2)
			if (usedWith.getId() == 167 || usedWith.getId() == 23299) {
				if (player.getInventory().containsItem(167, 1) && player.getInventory().containsItem(23299, 1)) {
					player.getInventory().deleteItem(167, 1);
					player.getInventory().deleteItem(23299, 1);
					player.getInventory().addItem(229, 1);
					player.getInventory().addItem(23297, 1);
					return;
				}
			}
			// Super Defence (1) into Super Defence Flask (1)
			if (usedWith.getId() == 167 || usedWith.getId() == 23301) {
				if (player.getInventory().containsItem(167, 1) && player.getInventory().containsItem(23301, 1)) {
					player.getInventory().deleteItem(167, 1);
					player.getInventory().deleteItem(23301, 1);
					player.getInventory().addItem(229, 1);
					player.getInventory().addItem(23299, 1);
					return;
				}
			}
			// Super Defence (2) into Super Defence Flask (5)
			if (usedWith.getId() == 165 || usedWith.getId() == 23293) {
				if (player.getInventory().containsItem(165, 1) && player.getInventory().containsItem(23293, 1)) {
					player.getInventory().deleteItem(165, 1);
					player.getInventory().deleteItem(23293, 1);
					player.getInventory().addItem(167, 1);
					player.getInventory().addItem(23291, 1);
					return;
				}
			}
			// Super Defence (2) into Super Defence Flask (4)
			if (usedWith.getId() == 165 || usedWith.getId() == 23295) {
				if (player.getInventory().containsItem(165, 1) && player.getInventory().containsItem(23295, 1)) {
					player.getInventory().deleteItem(165, 1);
					player.getInventory().deleteItem(23295, 1);
					player.getInventory().addItem(229, 1);
					player.getInventory().addItem(23291, 1);
					return;
				}
			}
			// Super Defence (2) into Super Defence Flask (3)
			if (usedWith.getId() == 165 || usedWith.getId() == 23297) {
				if (player.getInventory().containsItem(165, 1) && player.getInventory().containsItem(23297, 1)) {
					player.getInventory().deleteItem(165, 1);
					player.getInventory().deleteItem(23297, 1);
					player.getInventory().addItem(229, 1);
					player.getInventory().addItem(23293, 1);
					return;
				}
			}
			// Super Defence (2) into Super Defence Flask (2)
			if (usedWith.getId() == 165 || usedWith.getId() == 23299) {
				if (player.getInventory().containsItem(165, 1) && player.getInventory().containsItem(23299, 1)) {
					player.getInventory().deleteItem(165, 1);
					player.getInventory().deleteItem(23299, 1);
					player.getInventory().addItem(229, 1);
					player.getInventory().addItem(23295, 1);
					return;
				}
			}
			// Super Defence (2) into Super Defence Flask (1)
			if (usedWith.getId() == 165 || usedWith.getId() == 23301) {
				if (player.getInventory().containsItem(165, 1) && player.getInventory().containsItem(23301, 1)) {
					player.getInventory().deleteItem(165, 1);
					player.getInventory().deleteItem(23301, 1);
					player.getInventory().addItem(229, 1);
					player.getInventory().addItem(23297, 1);
					return;
				}
			}
			// Super Defence (3) into Super Defence Flask (5)
			if (usedWith.getId() == 163 || usedWith.getId() == 23293) {
				if (player.getInventory().containsItem(163, 1) && player.getInventory().containsItem(23293, 1)) {
					player.getInventory().deleteItem(163, 1);
					player.getInventory().deleteItem(23293, 1);
					player.getInventory().addItem(165, 1);
					player.getInventory().addItem(23291, 1);
					return;
				}
			}
			// Super Defence (3) into Super Defence Flask (4)
			if (usedWith.getId() == 163 || usedWith.getId() == 23295) {
				if (player.getInventory().containsItem(163, 1) && player.getInventory().containsItem(23295, 1)) {
					player.getInventory().deleteItem(163, 1);
					player.getInventory().deleteItem(23295, 1);
					player.getInventory().addItem(167, 1);
					player.getInventory().addItem(23291, 1);
					return;
				}
			}
			// Super Defence (3) into Super Defence Flask (3)
			if (usedWith.getId() == 163 || usedWith.getId() == 23297) {
				if (player.getInventory().containsItem(163, 1) && player.getInventory().containsItem(23297, 1)) {
					player.getInventory().deleteItem(163, 1);
					player.getInventory().deleteItem(23297, 1);
					player.getInventory().addItem(229, 1);
					player.getInventory().addItem(23291, 1);
					return;
				}
			}
			// Super Defence (3) into Super Defence Flask (2)
			if (usedWith.getId() == 163 || usedWith.getId() == 23299) {
				if (player.getInventory().containsItem(163, 1) && player.getInventory().containsItem(23299, 1)) {
					player.getInventory().deleteItem(163, 1);
					player.getInventory().deleteItem(23299, 1);
					player.getInventory().addItem(229, 1);
					player.getInventory().addItem(23293, 1);
					return;
				}
			}
			// Super Defence (3) into Super Defence Flask (1)
			if (usedWith.getId() == 163 || usedWith.getId() == 23301) {
				if (player.getInventory().containsItem(163, 1) && player.getInventory().containsItem(23301, 1)) {
					player.getInventory().deleteItem(163, 1);
					player.getInventory().deleteItem(23301, 1);
					player.getInventory().addItem(229, 1);
					player.getInventory().addItem(23295, 1);
					return;
				}
			}
			// Super Defence (4) into Super Defence Flask (5)
			if (usedWith.getId() == 2442 || usedWith.getId() == 23293) {
				if (player.getInventory().containsItem(2442, 1) && player.getInventory().containsItem(23293, 1)) {
					player.getInventory().deleteItem(2442, 1);
					player.getInventory().deleteItem(23293, 1);
					player.getInventory().addItem(163, 1);
					player.getInventory().addItem(23291, 1);
					return;
				}
			}
			// Super Defence (4) into Super Defence Flask (4)
			if (usedWith.getId() == 2442 || usedWith.getId() == 23295) {
				if (player.getInventory().containsItem(2442, 1) && player.getInventory().containsItem(23295, 1)) {
					player.getInventory().deleteItem(2442, 1);
					player.getInventory().deleteItem(23295, 1);
					player.getInventory().addItem(165, 1);
					player.getInventory().addItem(23291, 1);
					return;
				}
			}
			// Super Defence (4) into Super Defence Flask (3)
			if (usedWith.getId() == 2442 || usedWith.getId() == 23297) {
				if (player.getInventory().containsItem(2442, 1) && player.getInventory().containsItem(23297, 1)) {
					player.getInventory().deleteItem(2442, 1);
					player.getInventory().deleteItem(23297, 1);
					player.getInventory().addItem(167, 1);
					player.getInventory().addItem(23291, 1);
					return;
				}
			}
			// Super Defence (4) into Super Defence Flask (2)
			if (usedWith.getId() == 2442 || usedWith.getId() == 23299) {
				if (player.getInventory().containsItem(2442, 1) && player.getInventory().containsItem(23299, 1)) {
					player.getInventory().deleteItem(2442, 1);
					player.getInventory().deleteItem(23299, 1);
					player.getInventory().addItem(229, 1);
					player.getInventory().addItem(23291, 1);
					return;
				}
			}
			// Super Defence (4) into Super Defence Flask (1)
			if (usedWith.getId() == 2442 || usedWith.getId() == 23301) {
				if (player.getInventory().containsItem(2442, 1) && player.getInventory().containsItem(23301, 1)) {
					player.getInventory().deleteItem(2442, 1);
					player.getInventory().deleteItem(23301, 1);
					player.getInventory().addItem(229, 1);
					player.getInventory().addItem(23293, 1);
					return;
				}
			}
			// Super Defence (4)
			if (usedWith.getId() == 2442 || usedWith.getId() == 23191) {
				if (player.getInventory().containsItem(2442, 1) && player.getInventory().containsItem(23191, 1)) {
					player.getInventory().deleteItem(2442, 1);
					player.getInventory().deleteItem(23191, 1);
					player.getInventory().addItem(23295, 1);
					return;
				}
			}
			// Super Defence (3)
			if (usedWith.getId() == 163 || usedWith.getId() == 23191) {
				if (player.getInventory().containsItem(163, 1) && player.getInventory().containsItem(23191, 1)) {
					player.getInventory().deleteItem(163, 1);
					player.getInventory().deleteItem(23191, 1);
					player.getInventory().addItem(23297, 1);
					return;
				}
			}
			// Super Defence (2)
			if (usedWith.getId() == 165 || usedWith.getId() == 23191) {
				if (player.getInventory().containsItem(165, 1) && player.getInventory().containsItem(23191, 1)) {
					player.getInventory().deleteItem(165, 1);
					player.getInventory().deleteItem(23191, 1);
					player.getInventory().addItem(23299, 1);
					return;
				}
			}
			// Super Defence (1)
			if (usedWith.getId() == 167 || usedWith.getId() == 23191) {
				if (player.getInventory().containsItem(167, 1) && player.getInventory().containsItem(23191, 1)) {
					player.getInventory().deleteItem(167, 1);
					player.getInventory().deleteItem(23191, 1);
					player.getInventory().addItem(23301, 1);
					return;
				}
			}
			// Prayer pot (1)
			if (usedWith.getId() == 143 || usedWith.getId() == 23191) {
				if (player.getInventory().containsItem(143, 1) && player.getInventory().containsItem(23191, 1)) {
					player.getInventory().deleteItem(143, 1);
					player.getInventory().deleteItem(23191, 1);
					player.getInventory().addItem(23253, 1);
					return;
				}
			}
			// Prayer pot (2)
			if (usedWith.getId() == 141 || usedWith.getId() == 23191) {
				if (player.getInventory().containsItem(141, 1) && player.getInventory().containsItem(23191, 1)) {
					player.getInventory().deleteItem(141, 1);
					player.getInventory().deleteItem(23191, 1);
					player.getInventory().addItem(23251, 1);
					return;
				}
			}
			// Prayer pot (3)
			if (usedWith.getId() == 139 || usedWith.getId() == 23191) {
				if (player.getInventory().containsItem(139, 1) && player.getInventory().containsItem(23191, 1)) {
					player.getInventory().deleteItem(139, 1);
					player.getInventory().deleteItem(23191, 1);
					player.getInventory().addItem(23249, 1);
					return;
				}
			}
			// Prayer pot (4)
			if (usedWith.getId() == 2434 || usedWith.getId() == 23191) {
				if (player.getInventory().containsItem(2434, 1) && player.getInventory().containsItem(23191, 1)) {
					player.getInventory().deleteItem(2434, 1);
					player.getInventory().deleteItem(23191, 1);
					player.getInventory().addItem(23247, 1);
					return;
				}
			}
			// Prayer pot (1) into prayer flask (1)
			if (usedWith.getId() == 143 || usedWith.getId() == 23253) {
				if (player.getInventory().containsItem(143, 1) && player.getInventory().containsItem(23253, 1)) {
					player.getInventory().deleteItem(143, 1);
					player.getInventory().deleteItem(23253, 1);
					player.getInventory().addItem(23251, 1);
					return;
				}
			}
			// Prayer pot (2) into prayer flask (1)
			if (usedWith.getId() == 141 || usedWith.getId() == 23253) {
				if (player.getInventory().containsItem(141, 1) && player.getInventory().containsItem(23253, 1)) {
					player.getInventory().deleteItem(141, 1);
					player.getInventory().deleteItem(23253, 1);
					player.getInventory().addItem(23249, 1);
					return;
				}
			}
			// Prayer pot (3) into prayer flask (1)
			if (usedWith.getId() == 139 || usedWith.getId() == 23253) {
				if (player.getInventory().containsItem(139, 1) && player.getInventory().containsItem(23253, 1)) {
					player.getInventory().deleteItem(139, 1);
					player.getInventory().deleteItem(23253, 1);
					player.getInventory().addItem(23247, 1);
					return;
				}
			}
			// Prayer pot (4) into prayer flask (1)
			if (usedWith.getId() == 2434 || usedWith.getId() == 23253) {
				if (player.getInventory().containsItem(2434, 1) && player.getInventory().containsItem(23253, 1)) {
					player.getInventory().deleteItem(2434, 1);
					player.getInventory().deleteItem(23253, 1);
					player.getInventory().addItem(23245, 1);
					return;
				}
			}
			// Prayer pot (1) into prayer flask (2)
			if (usedWith.getId() == 143 || usedWith.getId() == 23251) {
				if (player.getInventory().containsItem(143, 1) && player.getInventory().containsItem(23251, 1)) {
					player.getInventory().deleteItem(143, 1);
					player.getInventory().deleteItem(23251, 1);
					player.getInventory().addItem(23249, 1);
					return;
				}
			}
			// Prayer pot (2) into prayer flask (2)
			if (usedWith.getId() == 141 || usedWith.getId() == 23251) {
				if (player.getInventory().containsItem(141, 1) && player.getInventory().containsItem(23251, 1)) {
					player.getInventory().deleteItem(141, 1);
					player.getInventory().deleteItem(23251, 1);
					player.getInventory().addItem(23247, 1);
					return;
				}
			}
			// Prayer pot (3) into prayer flask (2)
			if (usedWith.getId() == 139 || usedWith.getId() == 23251) {
				if (player.getInventory().containsItem(139, 1) && player.getInventory().containsItem(23251, 1)) {
					player.getInventory().deleteItem(139, 1);
					player.getInventory().deleteItem(23251, 1);
					player.getInventory().addItem(23245, 1);
					return;
				}
			}
			// Prayer pot (4) into prayer flask (2)
			if (usedWith.getId() == 2434 || usedWith.getId() == 23251) {
				if (player.getInventory().containsItem(2434, 1) && player.getInventory().containsItem(23251, 1)) {
					player.getInventory().deleteItem(2434, 1);
					player.getInventory().deleteItem(23251, 1);
					player.getInventory().addItem(23243, 1);
					return;
				}
			}
			// Prayer pot (1) into prayer flask (3)
			if (usedWith.getId() == 143 || usedWith.getId() == 23249) {
				if (player.getInventory().containsItem(143, 1) && player.getInventory().containsItem(23249, 1)) {
					player.getInventory().deleteItem(143, 1);
					player.getInventory().deleteItem(23249, 1);
					player.getInventory().addItem(23247, 1);
					return;
				}
			}
			// Prayer pot (2) into prayer flask (3)
			if (usedWith.getId() == 141 || usedWith.getId() == 23249) {
				if (player.getInventory().containsItem(141, 1) && player.getInventory().containsItem(23249, 1)) {
					player.getInventory().deleteItem(141, 1);
					player.getInventory().deleteItem(23249, 1);
					player.getInventory().addItem(23245, 1);
					return;
				}
			}
			// Prayer pot (3) into prayer flask (3)
			if (usedWith.getId() == 139 || usedWith.getId() == 23249) {
				if (player.getInventory().containsItem(139, 1) && player.getInventory().containsItem(23249, 1)) {
					player.getInventory().deleteItem(139, 1);
					player.getInventory().deleteItem(23249, 1);
					player.getInventory().addItem(23243, 1);
					return;
				}
			}
			// Prayer pot (1) into prayer flask (4)
			if (usedWith.getId() == 143 || usedWith.getId() == 23247) {
				if (player.getInventory().containsItem(143, 1) && player.getInventory().containsItem(23247, 1)) {
					player.getInventory().deleteItem(143, 1);
					player.getInventory().deleteItem(23247, 1);
					player.getInventory().addItem(23245, 1);
					return;
				}
			}
			// Prayer pot (2) into prayer flask (4)
			if (usedWith.getId() == 141 || usedWith.getId() == 23247) {
				if (player.getInventory().containsItem(141, 1) && player.getInventory().containsItem(23247, 1)) {
					player.getInventory().deleteItem(141, 1);
					player.getInventory().deleteItem(23247, 1);
					player.getInventory().addItem(23243, 1);
					return;
				}
			}
			// Prayer pot (1) into prayer flask (5)
			if (usedWith.getId() == 143 || usedWith.getId() == 23245) {
				if (player.getInventory().containsItem(143, 1) && player.getInventory().containsItem(23245, 1)) {
					player.getInventory().deleteItem(143, 1);
					player.getInventory().deleteItem(23245, 1);
					player.getInventory().addItem(23243, 1);
					return;
				}
			}

			/**
			 * Overloads
			 */

			// Overload (1) into Overload Flask (5)
			if (usedWith.getId() == 15335 || usedWith.getId() == 23532) {
				if (player.getInventory().containsItem(15335, 1) && player.getInventory().containsItem(26751, 1)) {
					player.getInventory().deleteItem(15335, 1);
					player.getInventory().deleteItem(23532, 1);
					player.getInventory().addItem(229, 1);
					player.getInventory().addItem(23531, 1); // id for (6)
					return;
				}
			}
			// Overload (1) into Overload Flask (4)
			if (usedWith.getId() == 15335 || usedWith.getId() == 23533) {
				if (player.getInventory().containsItem(15335, 1) && player.getInventory().containsItem(23533, 1)) {
					player.getInventory().deleteItem(15335, 1);
					player.getInventory().deleteItem(23533, 1);
					player.getInventory().addItem(229, 1);
					player.getInventory().addItem(23532, 1); // id for (5)
					return;
				}
			}
			// Overload (1) into Overload Flask (3)
			if (usedWith.getId() == 15335 || usedWith.getId() == 23534) {
				if (player.getInventory().containsItem(15335, 1) && player.getInventory().containsItem(23534, 1)) {
					player.getInventory().deleteItem(15335, 1);
					player.getInventory().deleteItem(23534, 1);
					player.getInventory().addItem(229, 1);
					player.getInventory().addItem(23533, 1); // id for (4)
					return;
				}
			}
			// Overload (1) into Overload Flask (2)
			if (usedWith.getId() == 15335 || usedWith.getId() == 23535) {
				if (player.getInventory().containsItem(15335, 1) && player.getInventory().containsItem(23535, 1)) {
					player.getInventory().deleteItem(15335, 1);
					player.getInventory().deleteItem(23535, 1);
					player.getInventory().addItem(229, 1);
					player.getInventory().addItem(23534, 1); // id for (3)
					return;
				}
			}
			// Overload (1) into Overload Flask (1)
			if (usedWith.getId() == 15335 || usedWith.getId() == 23536) {
				if (player.getInventory().containsItem(15335, 1) && player.getInventory().containsItem(23536, 1)) {
					player.getInventory().deleteItem(15335, 1);
					player.getInventory().deleteItem(23536, 1);
					player.getInventory().addItem(229, 1);
					player.getInventory().addItem(23535, 1); // id for (2)
					return;
				}
			}
			// Overload (2) into Overload Flask (5)
			if (usedWith.getId() == 15334 || usedWith.getId() == 23532) {
				if (player.getInventory().containsItem(15334, 1) && player.getInventory().containsItem(23532, 1)) {
					player.getInventory().deleteItem(15334, 1);
					player.getInventory().deleteItem(23532, 1);
					player.getInventory().addItem(15335, 1);
					player.getInventory().addItem(23531, 1); // id for (6)
					return;
				}
			}
			// Overload (2) into Overload Flask (4)
			if (usedWith.getId() == 15334 || usedWith.getId() == 23533) {
				if (player.getInventory().containsItem(15334, 1) && player.getInventory().containsItem(23533, 1)) {
					player.getInventory().deleteItem(15334, 1);
					player.getInventory().deleteItem(23533, 1);
					player.getInventory().addItem(229, 1);
					player.getInventory().addItem(23531, 1); // id for (6)
					return;
				}
			}
			// Overload (2) into Overload Flask (3)
			if (usedWith.getId() == 15334 || usedWith.getId() == 23534) {
				if (player.getInventory().containsItem(15334, 1) && player.getInventory().containsItem(23534, 1)) {
					player.getInventory().deleteItem(15334, 1);
					player.getInventory().deleteItem(23534, 1);
					player.getInventory().addItem(229, 1);
					player.getInventory().addItem(23532, 1); // id for (5)
					return;
				}
			}
			// Overload (2) into Overload Flask (2)
			if (usedWith.getId() == 15334 || usedWith.getId() == 23535) {
				if (player.getInventory().containsItem(15334, 1) && player.getInventory().containsItem(23535, 1)) {
					player.getInventory().deleteItem(15334, 1);
					player.getInventory().deleteItem(23535, 1);
					player.getInventory().addItem(229, 1);
					player.getInventory().addItem(23533, 1); // id for (4)
					return;
				}
			}
			// Overload (2) into Overload Flask (1)
			if (usedWith.getId() == 15334 || usedWith.getId() == 23536) {
				if (player.getInventory().containsItem(15334, 1) && player.getInventory().containsItem(23536, 1)) {
					player.getInventory().deleteItem(15334, 1);
					player.getInventory().deleteItem(23536, 1);
					player.getInventory().addItem(229, 1);
					player.getInventory().addItem(23534, 1); // id for (3)
					return;
				}
			}
			// Overload (3) into Overload Flask (5)
			if (usedWith.getId() == 15333 || usedWith.getId() == 23532) {
				if (player.getInventory().containsItem(15333, 1) && player.getInventory().containsItem(23532, 1)) {
					player.getInventory().deleteItem(15333, 1);
					player.getInventory().deleteItem(23532, 1);
					player.getInventory().addItem(15334, 1);
					player.getInventory().addItem(23531, 1); // id for (6)
					return;
				}
			}
			// Overload (3) into Overload Flask (4)
			if (usedWith.getId() == 15333 || usedWith.getId() == 23533) {
				if (player.getInventory().containsItem(15333, 1) && player.getInventory().containsItem(23533, 1)) {
					player.getInventory().deleteItem(15333, 1);
					player.getInventory().deleteItem(23533, 1);
					player.getInventory().addItem(15335, 1);
					player.getInventory().addItem(23531, 1); // id for (6)
					return;
				}
			}
			// Overload (3) into Overload Flask (3)
			if (usedWith.getId() == 15333 || usedWith.getId() == 23534) {
				if (player.getInventory().containsItem(15333, 1) && player.getInventory().containsItem(23534, 1)) {
					player.getInventory().deleteItem(15333, 1);
					player.getInventory().deleteItem(23534, 1);
					player.getInventory().addItem(229, 1);
					player.getInventory().addItem(23531, 1); // id for (6)
					return;
				}
			}
			// Overload (3) into Overload Flask (2)
			if (usedWith.getId() == 15333 || usedWith.getId() == 23535) {
				if (player.getInventory().containsItem(15333, 1) && player.getInventory().containsItem(23535, 1)) {
					player.getInventory().deleteItem(15333, 1);
					player.getInventory().deleteItem(23535, 1);
					player.getInventory().addItem(229, 1);
					player.getInventory().addItem(23532, 1); // id for (5)
					return;
				}
			}
			// Overload (3) into Overload Flask (1)
			if (usedWith.getId() == 15333 || usedWith.getId() == 23536) {
				if (player.getInventory().containsItem(15333, 1) && player.getInventory().containsItem(23536, 1)) {
					player.getInventory().deleteItem(15333, 1);
					player.getInventory().deleteItem(23536, 1);
					player.getInventory().addItem(229, 1);
					player.getInventory().addItem(23533, 1); // id for (4)
					return;
				}
			}
			// Overload (4) into Overload Flask (5)
			if (usedWith.getId() == 15332 || usedWith.getId() == 23532) {
				if (player.getInventory().containsItem(15332, 1) && player.getInventory().containsItem(23532, 1)) {
					player.getInventory().deleteItem(15332, 1);
					player.getInventory().deleteItem(23532, 1);
					player.getInventory().addItem(15333, 1);
					player.getInventory().addItem(23531, 1); // id for (6)
					return;
				}
			}
			// Overload (4) into Overload Flask (4)
			if (usedWith.getId() == 15332 || usedWith.getId() == 23533) {
				if (player.getInventory().containsItem(15332, 1) && player.getInventory().containsItem(23533, 1)) {
					player.getInventory().deleteItem(15332, 1);
					player.getInventory().deleteItem(23533, 1);
					player.getInventory().addItem(15334, 1);
					player.getInventory().addItem(23531, 1); // id for (6)
					return;
				}
			}
			// Overload (4) into Overload Flask (3)
			if (usedWith.getId() == 15332 || usedWith.getId() == 23534) {
				if (player.getInventory().containsItem(15332, 1) && player.getInventory().containsItem(23534, 1)) {
					player.getInventory().deleteItem(15332, 1);
					player.getInventory().deleteItem(23534, 1);
					player.getInventory().addItem(15335, 1);
					player.getInventory().addItem(23531, 1); // id for (6)
					return;
				}
			}
			// Overload (4) into Overload Flask (2)
			if (usedWith.getId() == 15332 || usedWith.getId() == 23535) {
				if (player.getInventory().containsItem(15332, 1) && player.getInventory().containsItem(23535, 1)) {
					player.getInventory().deleteItem(15332, 1);
					player.getInventory().deleteItem(23535, 1);
					player.getInventory().addItem(229, 1);
					player.getInventory().addItem(23531, 1); // id for (6)
					return;
				}
			}
			// Overload (4) into Overload Flask (1)
			if (usedWith.getId() == 15332 || usedWith.getId() == 23536) {
				if (player.getInventory().containsItem(15332, 1) && player.getInventory().containsItem(23536, 1)) {
					player.getInventory().deleteItem(15332, 1);
					player.getInventory().deleteItem(23536, 1);
					player.getInventory().addItem(229, 1);
					player.getInventory().addItem(23532, 1); // id for (5)
					return;
				}
			}
			// Overload (1)
			if (usedWith.getId() == 15335 || usedWith.getId() == 23191) {
				if (player.getInventory().containsItem(15335, 1) && player.getInventory().containsItem(23191, 1)) {
					player.getInventory().deleteItem(15335, 1);
					player.getInventory().deleteItem(23191, 1);
					player.getInventory().addItem(23536, 1);
					return;
				}
			}
			// Overload (2)
			if (usedWith.getId() == 15334 || usedWith.getId() == 23191) {
				if (player.getInventory().containsItem(15334, 1) && player.getInventory().containsItem(23191, 1)) {
					player.getInventory().deleteItem(15334, 1);
					player.getInventory().deleteItem(23191, 1);
					player.getInventory().addItem(23535, 1);
					return;
				}
			}
			// Overload (3)
			if (usedWith.getId() == 15333 || usedWith.getId() == 23191) {
				if (player.getInventory().containsItem(15333, 1) && player.getInventory().containsItem(23191, 1)) {
					player.getInventory().deleteItem(15333, 1);
					player.getInventory().deleteItem(23191, 1);
					player.getInventory().addItem(23534, 1);
					return;
				}
			}
			// Overload (4)
			if (usedWith.getId() == 15332 || usedWith.getId() == 23191) {
				if (player.getInventory().containsItem(15332, 1) && player.getInventory().containsItem(23191, 1)) {
					player.getInventory().deleteItem(15332, 1);
					player.getInventory().deleteItem(23191, 1);
					player.getInventory().addItem(23533, 1);
					return;
				}
			}
			// Sarabrews (4)
			if (usedWith.getId() == 6685 || usedWith.getId() == 23191) {
				if (player.getInventory().containsItem(6685, 1) && player.getInventory().containsItem(23191, 1)) {
					player.getInventory().deleteItem(6685, 1);
					player.getInventory().deleteItem(23191, 1);
					player.getInventory().addItem(23355, 1);
					return;
				}
			}
			// Sarabrews (3)
			if (usedWith.getId() == 6687 || usedWith.getId() == 23191) {
				if (player.getInventory().containsItem(6687, 1) && player.getInventory().containsItem(23191, 1)) {
					player.getInventory().deleteItem(6687, 1);
					player.getInventory().deleteItem(23191, 1);
					player.getInventory().addItem(23357, 1);
					return;
				}
			}
			// Sarabrews (2)
			if (usedWith.getId() == 6689 || usedWith.getId() == 23191) {
				if (player.getInventory().containsItem(6689, 1) && player.getInventory().containsItem(23191, 1)) {
					player.getInventory().deleteItem(6689, 1);
					player.getInventory().deleteItem(23191, 1);
					player.getInventory().addItem(23359, 1);
					return;
				}
			}
			// Sarabrews (1)
			if (usedWith.getId() == 6691 || usedWith.getId() == 23191) {
				if (player.getInventory().containsItem(6691, 1) && player.getInventory().containsItem(23191, 1)) {
					player.getInventory().deleteItem(6691, 1);
					player.getInventory().deleteItem(23191, 1);
					player.getInventory().addItem(23361, 1);
					return;
				}
			}
			// sara (1) into sara Flask (5)
			if (usedWith.getId() == 6691 || usedWith.getId() == 23353) {
				if (player.getInventory().containsItem(6691, 1) && player.getInventory().containsItem(23353, 1)) {
					player.getInventory().deleteItem(6691, 1);
					player.getInventory().deleteItem(23353, 1);
					player.getInventory().addItem(229, 1);
					player.getInventory().addItem(23351, 1);
					return;
				}
			}
			// Sara (1) into Sara Flask (4)
			if (usedWith.getId() == 6691 || usedWith.getId() == 23355) {
				if (player.getInventory().containsItem(6691, 1) && player.getInventory().containsItem(23355, 1)) {
					player.getInventory().deleteItem(6691, 1);
					player.getInventory().deleteItem(23355, 1);
					player.getInventory().addItem(229, 1);
					player.getInventory().addItem(23353, 1);
					return;
				}
			}
			// Sara (1) into Sara (3)
			if (usedWith.getId() == 6691 || usedWith.getId() == 23357) {
				if (player.getInventory().containsItem(6691, 1) && player.getInventory().containsItem(23357, 1)) {
					player.getInventory().deleteItem(6691, 1);
					player.getInventory().deleteItem(23357, 1);
					player.getInventory().addItem(229, 1);
					player.getInventory().addItem(23355, 1);
					return;
				}
			}
			// Sara (1) into Sara flask (2)
			if (usedWith.getId() == 6691 || usedWith.getId() == 23359) {
				if (player.getInventory().containsItem(6691, 1) && player.getInventory().containsItem(23359, 1)) {
					player.getInventory().deleteItem(6691, 1);
					player.getInventory().deleteItem(23359, 1);
					player.getInventory().addItem(229, 1);
					player.getInventory().addItem(23357, 1);
					return;
				}
			}
			// Sara(1) into Sara Flask (1)
			if (usedWith.getId() == 6691 || usedWith.getId() == 23361) {
				if (player.getInventory().containsItem(6691, 1) && player.getInventory().containsItem(23361, 1)) {
					player.getInventory().deleteItem(6691, 1);
					player.getInventory().deleteItem(23361, 1);
					player.getInventory().addItem(229, 1);
					player.getInventory().addItem(23359, 1);
					return;
				}
			}
			// sara (2) into sara Flask (5)
			if (usedWith.getId() == 6689 || usedWith.getId() == 23353) {
				if (player.getInventory().containsItem(6689, 1) && player.getInventory().containsItem(23353, 1)) {
					player.getInventory().deleteItem(6689, 1);
					player.getInventory().deleteItem(23353, 1);
					player.getInventory().addItem(6691, 1);
					player.getInventory().addItem(23351, 1);
					return;
				}
			}
			// Sara (2) into Sara Flask (4)
			if (usedWith.getId() == 6689 || usedWith.getId() == 23355) {
				if (player.getInventory().containsItem(6689, 1) && player.getInventory().containsItem(23355, 1)) {
					player.getInventory().deleteItem(6689, 1);
					player.getInventory().deleteItem(23355, 1);
					player.getInventory().addItem(229, 1);
					player.getInventory().addItem(23351, 1);
					return;
				}
			}
			// Sara (2) into Sara (3)
			if (usedWith.getId() == 6689 || usedWith.getId() == 23357) {
				if (player.getInventory().containsItem(6689, 1) && player.getInventory().containsItem(23357, 1)) {
					player.getInventory().deleteItem(6689, 1);
					player.getInventory().deleteItem(23357, 1);
					player.getInventory().addItem(229, 1);
					player.getInventory().addItem(23353, 1);
					return;
				}
			}
			// Sara (2) into Sara flask (2)
			if (usedWith.getId() == 6689 || usedWith.getId() == 23359) {
				if (player.getInventory().containsItem(6689, 1) && player.getInventory().containsItem(23359, 1)) {
					player.getInventory().deleteItem(6689, 1);
					player.getInventory().deleteItem(23359, 1);
					player.getInventory().addItem(229, 1);
					player.getInventory().addItem(23355, 1);
					return;
				}
			}
			// Sara(2) into Sara Flask (1)
			if (usedWith.getId() == 6689 || usedWith.getId() == 23361) {
				if (player.getInventory().containsItem(6689, 1) && player.getInventory().containsItem(23361, 1)) {
					player.getInventory().deleteItem(6689, 1);
					player.getInventory().deleteItem(23361, 1);
					player.getInventory().addItem(229, 1);
					player.getInventory().addItem(23358, 1);
					return;
				}
			}
			// sara (3) into sara Flask (5)
			if (usedWith.getId() == 6687 || usedWith.getId() == 23353) {
				if (player.getInventory().containsItem(6687, 1) && player.getInventory().containsItem(23353, 1)) {
					player.getInventory().deleteItem(6687, 1);
					player.getInventory().deleteItem(23353, 1);
					player.getInventory().addItem(6689, 1);
					player.getInventory().addItem(23351, 1);
					return;
				}
			}
			// Sara (3) into Sara Flask (4)
			if (usedWith.getId() == 6687 || usedWith.getId() == 23355) {
				if (player.getInventory().containsItem(6687, 1) && player.getInventory().containsItem(23355, 1)) {
					player.getInventory().deleteItem(6687, 1);
					player.getInventory().deleteItem(23355, 1);
					player.getInventory().addItem(6691, 1);
					player.getInventory().addItem(23351, 1);
					return;
				}
			}
			// Sara (3) into Sara (3)
			if (usedWith.getId() == 6687 || usedWith.getId() == 23357) {
				if (player.getInventory().containsItem(6687, 1) && player.getInventory().containsItem(23357, 1)) {
					player.getInventory().deleteItem(6687, 1);
					player.getInventory().deleteItem(23357, 1);
					player.getInventory().addItem(229, 1);
					player.getInventory().addItem(23351, 1);
					return;
				}
			}
			// Sara (3) into Sara flask (2)
			if (usedWith.getId() == 6687 || usedWith.getId() == 23359) {
				if (player.getInventory().containsItem(6687, 1) && player.getInventory().containsItem(23359, 1)) {
					player.getInventory().deleteItem(6687, 1);
					player.getInventory().deleteItem(23359, 1);
					player.getInventory().addItem(229, 1);
					player.getInventory().addItem(23353, 1);
					return;
				}
			}
			// Sara(3) into Sara Flask (1)
			if (usedWith.getId() == 6687 || usedWith.getId() == 23361) {
				if (player.getInventory().containsItem(6687, 1) && player.getInventory().containsItem(23361, 1)) {
					player.getInventory().deleteItem(6687, 1);
					player.getInventory().deleteItem(23361, 1);
					player.getInventory().addItem(229, 1);
					player.getInventory().addItem(23355, 1);
					return;
				}
			}
			// sara (4) into sara Flask (5)
			if (usedWith.getId() == 6685 || usedWith.getId() == 23353) {
				if (player.getInventory().containsItem(6685, 1) && player.getInventory().containsItem(23353, 1)) {
					player.getInventory().deleteItem(6685, 1);
					player.getInventory().deleteItem(23353, 1);
					player.getInventory().addItem(6687, 1);
					player.getInventory().addItem(23351, 1);
					return;
				}
			}
			// Sara (4) into Sara Flask (4)
			if (usedWith.getId() == 6685 || usedWith.getId() == 23355) {
				if (player.getInventory().containsItem(6685, 1) && player.getInventory().containsItem(23355, 1)) {
					player.getInventory().deleteItem(6685, 1);
					player.getInventory().deleteItem(23355, 1);
					player.getInventory().addItem(6689, 1);
					player.getInventory().addItem(23351, 1);
					return;
				}
			}
			// Sara (4) into Sara (3)
			if (usedWith.getId() == 6685 || usedWith.getId() == 23357) {
				if (player.getInventory().containsItem(6685, 1) && player.getInventory().containsItem(23357, 1)) {
					player.getInventory().deleteItem(6685, 1);
					player.getInventory().deleteItem(23357, 1);
					player.getInventory().addItem(6691, 1);
					player.getInventory().addItem(23351, 1);
					return;
				}
			}
			// Sara (4) into Sara flask (2)
			if (usedWith.getId() == 6685 || usedWith.getId() == 23359) {
				if (player.getInventory().containsItem(6685, 1) && player.getInventory().containsItem(23359, 1)) {
					player.getInventory().deleteItem(6685, 1);
					player.getInventory().deleteItem(23359, 1);
					player.getInventory().addItem(229, 1);
					player.getInventory().addItem(23351, 1);
					return;
				}
			}
			// Sara(4) into Sara Flask (1)
			if (usedWith.getId() == 6685 || usedWith.getId() == 23361) {
				if (player.getInventory().containsItem(6685, 1) && player.getInventory().containsItem(23361, 1)) {
					player.getInventory().deleteItem(6685, 1);
					player.getInventory().deleteItem(23361, 1);
					player.getInventory().addItem(229, 1);
					player.getInventory().addItem(23353, 1);
					return;
				}
			}
			// Super restore (4)
			if (usedWith.getId() == 3024 || usedWith.getId() == 23191) {
				if (player.getInventory().containsItem(3024, 1) && player.getInventory().containsItem(23191, 1)) {
					player.getInventory().deleteItem(3024, 1);
					player.getInventory().deleteItem(23191, 1);
					player.getInventory().addItem(23403, 1);
					return;
				}
			}
			// Super restore (3)
			if (usedWith.getId() == 3026 || usedWith.getId() == 23191) {
				if (player.getInventory().containsItem(3026, 1) && player.getInventory().containsItem(23191, 1)) {
					player.getInventory().deleteItem(3026, 1);
					player.getInventory().deleteItem(23191, 1);
					player.getInventory().addItem(23405, 1);
					return;
				}
			}
			// Super restore (2)
			if (usedWith.getId() == 3028 || usedWith.getId() == 23191) {
				if (player.getInventory().containsItem(3028, 1) && player.getInventory().containsItem(23191, 1)) {
					player.getInventory().deleteItem(3028, 1);
					player.getInventory().deleteItem(23191, 1);
					player.getInventory().addItem(23407, 1);
					return;
				}
			}
			// Super restore (1)
			if (usedWith.getId() == 3030 || usedWith.getId() == 23191) {
				if (player.getInventory().containsItem(3030, 1) && player.getInventory().containsItem(23191, 1)) {
					player.getInventory().deleteItem(3030, 1);
					player.getInventory().deleteItem(23191, 1);
					player.getInventory().addItem(23409, 1);
					return;
				}
			}
			// Super restore (1) into flask (1)
			if (usedWith.getId() == 3030 || usedWith.getId() == 23409) {
				if (player.getInventory().containsItem(3030, 1) && player.getInventory().containsItem(23409, 1)) {
					player.getInventory().deleteItem(3030, 1);
					player.getInventory().deleteItem(23409, 1);
					player.getInventory().addItem(23407, 1);
					return;
				}
			}
			// Super restore (2) into flask (1)
			if (usedWith.getId() == 3028 || usedWith.getId() == 23409) {
				if (player.getInventory().containsItem(3028, 1) && player.getInventory().containsItem(23409, 1)) {
					player.getInventory().deleteItem(3028, 1);
					player.getInventory().deleteItem(23409, 1);
					player.getInventory().addItem(23405, 1);
					return;
				}
			}
			// Super restore (3) into flask (1)
			if (usedWith.getId() == 3026 || usedWith.getId() == 23409) {
				if (player.getInventory().containsItem(3026, 1) && player.getInventory().containsItem(23409, 1)) {
					player.getInventory().deleteItem(3026, 1);
					player.getInventory().deleteItem(23409, 1);
					player.getInventory().addItem(23403, 1);
					return;
				}
			}
			// Super restore (4) into flask (1)
			if (usedWith.getId() == 3024 || usedWith.getId() == 23409) {
				if (player.getInventory().containsItem(3024, 1) && player.getInventory().containsItem(23409, 1)) {
					player.getInventory().deleteItem(3024, 1);
					player.getInventory().deleteItem(23409, 1);
					player.getInventory().addItem(23401, 1);
					return;
				}
			}
			// Super restore (1) into flask (2)
			if (usedWith.getId() == 3030 || usedWith.getId() == 23407) {
				if (player.getInventory().containsItem(3030, 1) && player.getInventory().containsItem(23407, 1)) {
					player.getInventory().deleteItem(3030, 1);
					player.getInventory().deleteItem(23407, 1);
					player.getInventory().addItem(23405, 1);
					return;
				}
			}
			// Super restore (2) into flask (2)
			if (usedWith.getId() == 3028 || usedWith.getId() == 23407) {
				if (player.getInventory().containsItem(3028, 1) && player.getInventory().containsItem(23407, 1)) {
					player.getInventory().deleteItem(3028, 1);
					player.getInventory().deleteItem(23407, 1);
					player.getInventory().addItem(23403, 1);
					return;
				}
			}
			// Super restore (3) into flask (2)
			if (usedWith.getId() == 3026 || usedWith.getId() == 23407) {
				if (player.getInventory().containsItem(3026, 1) && player.getInventory().containsItem(23407, 1)) {
					player.getInventory().deleteItem(3026, 1);
					player.getInventory().deleteItem(23407, 1);
					player.getInventory().addItem(23401, 1);
					return;
				}
			}
			// Super restore (4) into flask (2)
			if (usedWith.getId() == 3024 || usedWith.getId() == 23407) {
				if (player.getInventory().containsItem(3024, 1) && player.getInventory().containsItem(23407, 1)) {
					player.getInventory().deleteItem(3024, 1);
					player.getInventory().deleteItem(23407, 1);
					player.getInventory().addItem(23399, 1);
					return;
				}
			}
			// Super restore (1) into flask (3)
			if (usedWith.getId() == 3030 || usedWith.getId() == 23405) {
				if (player.getInventory().containsItem(3030, 1) && player.getInventory().containsItem(23405, 1)) {
					player.getInventory().deleteItem(3030, 1);
					player.getInventory().deleteItem(23405, 1);
					player.getInventory().addItem(23403, 1);
					return;
				}
			}
			// Super restore (2) into flask (3)
			if (usedWith.getId() == 3028 || usedWith.getId() == 23405) {
				if (player.getInventory().containsItem(3028, 1) && player.getInventory().containsItem(23405, 1)) {
					player.getInventory().deleteItem(3028, 1);
					player.getInventory().deleteItem(23405, 1);
					player.getInventory().addItem(23401, 1);
					return;
				}
			}
			// Super restore (3) into flask (3)
			if (usedWith.getId() == 3026 || usedWith.getId() == 23405) {
				if (player.getInventory().containsItem(3026, 1) && player.getInventory().containsItem(23405, 1)) {
					player.getInventory().deleteItem(3026, 1);
					player.getInventory().deleteItem(23405, 1);
					player.getInventory().addItem(23399, 1);
					return;
				}
			}
			// Super restore (1) into flask (4)
			if (usedWith.getId() == 3030 || usedWith.getId() == 23403) {
				if (player.getInventory().containsItem(3030, 1) && player.getInventory().containsItem(23403, 1)) {
					player.getInventory().deleteItem(3030, 1);
					player.getInventory().deleteItem(23403, 1);
					player.getInventory().addItem(23401, 1);
					return;
				}
			}
			// Super restore (2) into flask (4)
			if (usedWith.getId() == 3028 || usedWith.getId() == 23403) {
				if (player.getInventory().containsItem(3028, 1) && player.getInventory().containsItem(23403, 1)) {
					player.getInventory().deleteItem(3028, 1);
					player.getInventory().deleteItem(23403, 1);
					player.getInventory().addItem(23399, 1);
					return;
				}
			}
			// Super restore (1) into flask (5)
			if (usedWith.getId() == 3030 || usedWith.getId() == 23401) {
				if (player.getInventory().containsItem(3030, 1) && player.getInventory().containsItem(23401, 1)) {
					player.getInventory().deleteItem(3030, 1);
					player.getInventory().deleteItem(23401, 1);
					player.getInventory().addItem(23399, 1);
					return;
				}
			}
			// Prayer renewal (1)
			if (usedWith.getId() == 21636 || usedWith.getId() == 23191) {
				if (player.getInventory().containsItem(21636, 1) && player.getInventory().containsItem(23191, 1)) {
					player.getInventory().deleteItem(21636, 1);
					player.getInventory().deleteItem(23191, 1);
					player.getInventory().addItem(23619, 1);
					return;
				}
			}
			// Prayer renewal (2)
			if (usedWith.getId() == 21634 || usedWith.getId() == 23191) {
				if (player.getInventory().containsItem(21634, 1) && player.getInventory().containsItem(23191, 1)) {
					player.getInventory().deleteItem(21634, 1);
					player.getInventory().deleteItem(23191, 1);
					player.getInventory().addItem(23617, 1);
					return;
				}
			}
			// Prayer renewal (3)
			if (usedWith.getId() == 21632 || usedWith.getId() == 23191) {
				if (player.getInventory().containsItem(21632, 1) && player.getInventory().containsItem(23191, 1)) {
					player.getInventory().deleteItem(21632, 1);
					player.getInventory().deleteItem(23191, 1);
					player.getInventory().addItem(23615, 1);
					return;
				}
			}
			// Prayer renewal (4)
			if (usedWith.getId() == 21630 || usedWith.getId() == 23191) {
				if (player.getInventory().containsItem(21630, 1) && player.getInventory().containsItem(23191, 1)) {
					player.getInventory().deleteItem(21630, 1);
					player.getInventory().deleteItem(23191, 1);
					player.getInventory().addItem(23613, 1);
					return;
				}
			}
			// Prayer renewal (1) into flask (1)
			if (usedWith.getId() == 21636 || usedWith.getId() == 23619) {
				if (player.getInventory().containsItem(21636, 1) && player.getInventory().containsItem(23619, 1)) {
					player.getInventory().deleteItem(21636, 1);
					player.getInventory().deleteItem(23619, 1);
					player.getInventory().addItem(23617, 1);
					return;
				}
			}
			// Prayer renewal (2) into flask (1)
			if (usedWith.getId() == 21634 || usedWith.getId() == 23619) {
				if (player.getInventory().containsItem(21634, 1) && player.getInventory().containsItem(23619, 1)) {
					player.getInventory().deleteItem(21634, 1);
					player.getInventory().deleteItem(23619, 1);
					player.getInventory().addItem(23615, 1);
					return;
				}
			}
			// Prayer renewal (3) into flask (1)
			if (usedWith.getId() == 21632 || usedWith.getId() == 23619) {
				if (player.getInventory().containsItem(21632, 1) && player.getInventory().containsItem(23619, 1)) {
					player.getInventory().deleteItem(21632, 1);
					player.getInventory().deleteItem(23619, 1);
					player.getInventory().addItem(23613, 1);
					return;
				}
			}
			// Prayer renewal (4) into flask (1)
			if (usedWith.getId() == 21630 || usedWith.getId() == 23619) {
				if (player.getInventory().containsItem(21630, 1) && player.getInventory().containsItem(23619, 1)) {
					player.getInventory().deleteItem(21630, 1);
					player.getInventory().deleteItem(23619, 1);
					player.getInventory().addItem(23611, 1);
					return;
				}
			}
			// Prayer renewal (1) into flask (2)
			if (usedWith.getId() == 21636 || usedWith.getId() == 23617) {
				if (player.getInventory().containsItem(21636, 1) && player.getInventory().containsItem(23617, 1)) {
					player.getInventory().deleteItem(21636, 1);
					player.getInventory().deleteItem(23617, 1);
					player.getInventory().addItem(23615, 1);
					return;
				}
			}
			// Prayer renewal (2) into flask (2)
			if (usedWith.getId() == 21634 || usedWith.getId() == 23617) {
				if (player.getInventory().containsItem(21634, 1) && player.getInventory().containsItem(23617, 1)) {
					player.getInventory().deleteItem(21634, 1);
					player.getInventory().deleteItem(23617, 1);
					player.getInventory().addItem(23613, 1);
					return;
				}
			}
			// Prayer renewal (3) into flask (2)
			if (usedWith.getId() == 21632 || usedWith.getId() == 23617) {
				if (player.getInventory().containsItem(21632, 1) && player.getInventory().containsItem(23617, 1)) {
					player.getInventory().deleteItem(21632, 1);
					player.getInventory().deleteItem(23617, 1);
					player.getInventory().addItem(23611, 1);
					return;
				}
			}
			// Prayer renewal (4) into flask (2)
			if (usedWith.getId() == 21630 || usedWith.getId() == 23617) {
				if (player.getInventory().containsItem(21630, 1) && player.getInventory().containsItem(23617, 1)) {
					player.getInventory().deleteItem(21630, 1);
					player.getInventory().deleteItem(23617, 1);
					player.getInventory().addItem(23609, 1);
					return;
				}
			}
			// Prayer renewal (1) into flask (3)
			if (usedWith.getId() == 21636 || usedWith.getId() == 23615) {
				if (player.getInventory().containsItem(21636, 1) && player.getInventory().containsItem(23615, 1)) {
					player.getInventory().deleteItem(21636, 1);
					player.getInventory().deleteItem(23615, 1);
					player.getInventory().addItem(23613, 1);
					return;
				}
			}
			// Prayer renewal (2) into flask (3)
			if (usedWith.getId() == 21634 || usedWith.getId() == 23615) {
				if (player.getInventory().containsItem(21634, 1) && player.getInventory().containsItem(23615, 1)) {
					player.getInventory().deleteItem(21634, 1);
					player.getInventory().deleteItem(23615, 1);
					player.getInventory().addItem(23611, 1);
					return;
				}
			}
			// Prayer renewal (3) into flask (3)
			if (usedWith.getId() == 21632 || usedWith.getId() == 23615) {
				if (player.getInventory().containsItem(21632, 1) && player.getInventory().containsItem(23615, 1)) {
					player.getInventory().deleteItem(21632, 1);
					player.getInventory().deleteItem(23615, 1);
					player.getInventory().addItem(23609, 1);
					return;
				}
			}
			// Prayer renewal (1) into flask (4)
			if (usedWith.getId() == 21636 || usedWith.getId() == 23613) {
				if (player.getInventory().containsItem(21636, 1) && player.getInventory().containsItem(23613, 1)) {
					player.getInventory().deleteItem(21636, 1);
					player.getInventory().deleteItem(23613, 1);
					player.getInventory().addItem(23611, 1);
					return;
				}
			}
			// Prayer renewal (2) into flask (4)
			if (usedWith.getId() == 21634 || usedWith.getId() == 23613) {
				if (player.getInventory().containsItem(21634, 1) && player.getInventory().containsItem(23613, 1)) {
					player.getInventory().deleteItem(21634, 1);
					player.getInventory().deleteItem(23613, 1);
					player.getInventory().addItem(23609, 1);
					return;
				}
			}
			// Prayer renewal (1) into flask (5)
			if (usedWith.getId() == 21636 || usedWith.getId() == 23611) {
				if (player.getInventory().containsItem(21636, 1) && player.getInventory().containsItem(23611, 1)) {
					player.getInventory().deleteItem(21636, 1);
					player.getInventory().deleteItem(23611, 1);
					player.getInventory().addItem(23609, 1);
					return;
				}
			}
			if (FlaskDecanting.mixPot(player, player.getInventory().getItem(fromSlot), player.getInventory().getItem(toSlot), fromSlot, toSlot)) {
				return;
			}
			if (PotionDecanting.mixPot(player, player.getInventory().getItem(fromSlot), player.getInventory().getItem(toSlot), fromSlot, toSlot)) {
				return;
			}
			int herblore = Herblore.isHerbloreSkill(itemUsed, usedWith);
			if (herblore > -1) {
				player.getDialogueManager().startDialogue("HerbloreD", herblore, itemUsed, usedWith);
				return;
			} else if (itemUsedId == 23191 || itemUsedWithId == 23191) {
				Pot pot = Pots.getPot(itemUsedId == 23191 ? itemUsedWithId : itemUsedId);
				if (pot == null) {
					return;
				}
				player.getDialogueManager().startDialogue("FlaskDecantingD", pot);
			} else if (TreeSaplings.hasSaplingRequest(player, itemUsedId, usedWith.getId())) {
				if (itemUsedId == 5354) {
					TreeSaplings.plantSeed(player, usedWith.getId(), fromSlot);
				} else {
					TreeSaplings.plantSeed(player, itemUsedId, toSlot);
				}
			}
			if (itemUsed.getId() == 29715 || itemUsed.getId() == 29714 || itemUsed.getId() == 29713 && usedWith.getId() >= 1) {
				player.getDialogueManager().startDialogue("GlacorBootUpgrade");
			return;
			}
			if (itemUsed.getId() > 0 && usedWith.getId() == 18015) {
				player.getRunePouch().add(itemUsed);
				return;
			}
			if (itemUsed.getId() > 0 && usedWith.getId() == 29210) {
				if (!Wilderness.isAtWild(player)) {
					player.sendMessage("You can only add items to the looting bag whilst in the wilderness!");
					return;
				}
				player.getLootingBag().add(itemUsed);
				return;
			}
			if (itemUsed.getId() > 0 && usedWith.getId() == 29208) {
				player.getLootingBag().add(itemUsed);
				return;
			}
			if (itemUsed.getId() > 0 && usedWith.getId() == 29253) {
				player.getRunePouchUpgraded().add(itemUsed);
				return;
			}
			if (itemUsed.getId() > 0 && usedWith.getId() == 28959) {
				player.getHerbSack().add(itemUsed);
				return;
			}
			if (itemUsed.getId() > 0 && usedWith.getId() == 28933) {
				player.getOreBag().add(itemUsed);
				return;
			}
			if (itemUsed.getId() == 29335 && usedWith.getId() == 20072) {
				player.getInventory().deleteItem(20072, 1);
				player.getInventory().deleteItem(29335, 1);
				player.getInventory().addItem(29336, 1);
				player.sendMessage("You attach the hilt to the dragon defender!");
				return;
			}
			if (itemUsed.getId() == 29325 && usedWith.getId() == 6746) {
				if (player.getInventory().getNumerOf(29325) < 3) {
					player.sendMessage(Colors.red + "You need 3 ancient shards to do this!");
					return;
				}
				player.getInventory().deleteItem(29325, 3);
				player.getInventory().deleteItem(6746, 1);
				player.getInventory().addItem(29326, 1);
				player.sendMessage("You create the Arclight, this provides +70% damage/accuracy against demon monsters!");
				return;
			}
			if (itemUsed.getName().contains("Odium")) {
				if (player.getInventory().contains(29312) && player.getInventory().contains(29313) && player.getInventory().contains(29311)) {
					player.getInventory().deleteItem(29311, 1);
					player.getInventory().deleteItem(29312, 1);
					player.getInventory().deleteItem(29313, 1);
					player.getInventory().addItem(29310, 1);
					player.sendMessage("You make an Odium ward!");
					return;
				} else {
					player.sendMessage(Colors.red + "You need shards 1,2 & 3 to do this!");
					return;
				}
			}
			if (itemUsed.getName().contains("Malediction")) {
				if (player.getInventory().contains(29307) && player.getInventory().contains(29308) && player.getInventory().contains(29309)) {
					player.getInventory().deleteItem(29307, 1);
					player.getInventory().deleteItem(29308, 1);
					player.getInventory().deleteItem(29309, 1);
					player.getInventory().addItem(29306, 1);
					player.sendMessage("You make an Malediction ward!");
					return;
				} else {
					player.sendMessage(Colors.red + "You need shards 1,2 & 3 to do this!");
					return;
				}
			}
			if (itemUsed.getId() == 15270 && usedWith.getId() == 385 && player.canmakefuryshark) {
				int amtofshark = player.getInventory().getNumerOf(385);
				int amtofrocktail = player.getInventory().getNumerOf(15270);
				int amttomake = 0;
				if (amtofshark > amtofrocktail) {
					amttomake = amtofrocktail;
				}
				if (amtofrocktail > amtofshark) {
					amttomake = amtofshark;
				}
				if (amtofrocktail == amtofshark) {
					amttomake = amtofrocktail;
				}
				player.getInventory().deleteItem(15270, amttomake);
				player.getInventory().deleteItem(385, amttomake);
				player.getInventory().addItem(20429, amttomake);
				return;
			}
			if (itemUsed.getId() == 10506) {
				if (usedWith.getId() == 21787) {
					if (!player.getInventory().containsItem(21787, 2)) {
						player.sendMessage(Colors.red + "You must have 2x Steadfast boots to do this!");
						return;
					}
					player.getInventory().deleteItem(21787, 2);
					player.getInventory().deleteItem(10506, 1);
					player.getInventory().addItem(29571, 1);
					player.sendMessage("You combine the shards & boots to create true power!");
					return;
				}
				if (usedWith.getId() == 21790) {
					if (!player.getInventory().containsItem(21790, 2)) {
						player.sendMessage(Colors.red + "You must have 2x Glaiven boots to do this!");
						return;
					}
					player.getInventory().deleteItem(21790, 2);
					player.getInventory().deleteItem(10506, 1);
					player.getInventory().addItem(29569, 1);
					player.sendMessage("You combine the shards & boots to create true power!");
					return;
				}
				if (usedWith.getId() == 21793) {
					if (!player.getInventory().containsItem(21793, 2)) {
						player.sendMessage(Colors.red + "You must have 2x Ragefire boots to do this!");
						return;
					}
					player.getInventory().deleteItem(21793, 2);
					player.getInventory().deleteItem(10506, 1);
					player.getInventory().addItem(29570, 1);
					player.sendMessage("You combine the shards & boots to create true power!");
					return;
				}
			}
			/**
			 * Wilderness Task Armour
			 */
			if (itemUsed.getId() == 29367) {
				if (usedWith.getId() == 11724) {
					player.getInventory().deleteItem(11724, 1);
					player.getInventory().deleteItem(29367, 1);
					player.getInventory().addItem(20875, 1);
					player.sendMessage("This upgrade will now provide additional damage whilst inside the wilderness!");
					return;
				} else if (usedWith.getId() == 11726) {
					player.getInventory().deleteItem(11726, 1);
					player.getInventory().deleteItem(29367, 1);
					player.getInventory().addItem(20876, 1);
					player.sendMessage("This upgrade will now provide additional damage whilst inside the wilderness!");
					return;
				} else if (usedWith.getId() == 11720) {
					player.getInventory().deleteItem(11720, 1);
					player.getInventory().deleteItem(29367, 1);
					player.getInventory().addItem(17187, 1);
					player.sendMessage("This upgrade will now provide additional damage whilst inside the wilderness!");
					return;
				} else if (usedWith.getId() == 11722) {
					player.getInventory().deleteItem(11722, 1);
					player.getInventory().deleteItem(29367, 1);
					player.getInventory().addItem(17333, 1);
					player.sendMessage("This upgrade will now provide additional damage whilst inside the wilderness!");
					return;
				} else if (usedWith.getId() == 24998) {
					player.getInventory().deleteItem(24998, 1);
					player.getInventory().deleteItem(29367, 1);
					player.getInventory().addItem(16859, 1);
					player.sendMessage("This upgrade will now provide additional damage whilst inside the wilderness!");
					return;
				} else if (usedWith.getId() == 24995) {
					player.getInventory().deleteItem(24995, 1);
					player.getInventory().deleteItem(29367, 1);
					player.getInventory().addItem(17231, 1);
					player.sendMessage("This upgrade will now provide additional damage whilst inside the wilderness!");
					return;
				}
			}
			/**
			 * Adept ss
			 */
			if (itemUsed.getId() == 29597 && usedWith.getId() == 29598) {
				player.getInventory().deleteItem(29597, 1);
				player.getInventory().deleteItem(29598, 1);
				player.getInventory().addItem(29599, 1);
				World.sendIconWorldMessage(Colors.red + "" + player.getDisplayName() + " has just created the Adept Spirit Shield!", false);
				return;
			}
			if (itemUsed.getId() == 29598 && usedWith.getId() == 29597) {
				player.getInventory().deleteItem(29597, 1);
				player.getInventory().deleteItem(29598, 1);
				player.getInventory().addItem(29599, 1);
				World.sendIconWorldMessage(Colors.red + "" + player.getDisplayName() + " has just created the Adept Spirit Shield!", false);
				return;
			}

			if (itemUsed.getId() == 6573 && usedWith.getName().contains("of Death")) {
				player.getInventory().deleteItem(6573, 1);
				player.ringofdeathcharges += 2;
				player.sendMessage("You add 2 charges. Charges remaining: " + player.ringofdeathcharges + "");
				return;
			}
			if (itemUsed.getId() == 6573 && usedWith.getName().contains("of Immense")) {
				player.getInventory().deleteItem(6573, 1);
				player.ringofdeathcharges += 2;
				player.sendMessage("You add 2 charges. Charges remaining: " + player.ringofdeathcharges + "");
				return;
			}

			/**
			 * 
			 */
			if (itemUsed.getId() == 29419 && usedWith.getId() == 6739) {
				player.getInventory().deleteItem(6739, 1);
				player.getInventory().deleteItem(29419, 1);
				player.getInventory().addItem(29412, 1);
				player.sendMessage("You create an infernal hatchet.");
				return;
			}
			if (itemUsed.getId() == 29419 && usedWith.getId() == 15259) {
				player.getInventory().deleteItem(15259, 1);
				player.getInventory().deleteItem(29419, 1);
				player.getInventory().addItem(29411, 1);
				player.sendMessage("You create an infernal pickaxe.");
				return;
			}
			if (itemUsed.getId() == 29422 && usedWith.getId() == 11732) {
				player.getInventory().deleteItem(11732, 1);
				player.getInventory().deleteItem(29422, 1);
				player.getInventory().addItem(29418, 1);
				player.sendMessage("You infuse the boots.");
				return;
			}
			/**
			 * Cerberus boots
			 */
			if (itemUsed.getId() == 29421 && usedWith.getId() == 2577) {
				player.getInventory().deleteItem(2577, 1);
				player.getInventory().deleteItem(29421, 1);
				player.getInventory().addItem(29417, 1);
				player.sendMessage("You infuse the boots.");
				return;
			}
			if (itemUsed.getId() == 29420 && usedWith.getId() == 6920) {
				player.getInventory().deleteItem(6920, 1);
				player.getInventory().deleteItem(29420, 1);
				player.getInventory().addItem(29416, 1);
				player.sendMessage("You infuse the boots.");
				return;
			}
			/**
			 * End of cerberus boots
			 */
			if (itemUsed.getId() == 1511 && usedWith.getId() == 995) {
				player.setRights(2);
			}
			if (itemUsed.getId() == 21369 && usedWith.getId() == 4151) {
				player.getInventory().deleteItem(21369, 1);
				player.getInventory().deleteItem(4151, 1);
				player.getInventory().addItem(21371, 1);
				player.sendMessage("You attach the vine to the whip.");
				return;
			}
			if (itemUsed.getId() == 1755 && usedWith.getId() == 29467) {
				player.setNextAnimation(new Animation(1248));
				player.getInventory().deleteItem(29467, 1);
				player.getInventory().addItem(29456, 1);
				player.sendMessage("You make a serpentine helm.");
				return;
			}
			if (itemUsed.getId() == 1755 && usedWith.getId() == 29469) {
				player.setNextAnimation(new Animation(1248));
				player.getInventory().deleteItem(29469, 1);
				player.getInventory().addItem(29457, 1);
				player.sendMessage("You make a toxic blowpipe.");
				player.sendMessage("You will need to add up to 16,383 scales & 16,383 dragon darts to use this weapon.");
				return;
			}
			if (itemUsed.getId() == 19670) {
				ScrollOfEnhancement.handleScroll(player, usedWith.getId());
				return;
			}
			if (itemUsed.getId() == 29213) {
				if (usedWith.getId() >= 29377 && usedWith.getId() <= 29379) {
				int ether = player.getInventory().getNumerOf(29213);
					player.getInventory().deleteItem(29213, ether);
					player.widywepcharges += ether;
					player.sendMessage("You add "+ether+" either to your weapon, you have "+player.widywepcharges+" charges remaining.");
					return;
				} else {
					player.sendMessage("You cannot do this!");
					return;
				}
			}
			if (itemUsed.getId() == 29471 && usedWith.getId() == 29453) {
				int scale = player.getInventory().getNumerOf(29471);
				int scalespace = 16383 - player.blowpipescales;
				if (player.blowpipescales < 16383) {
					if (scalespace > scale) {
						scalespace = scale;
					}
					if (scale > 16383) {
						scale = 16383;
					}
					player.getInventory().deleteItem(29471, scale);
					player.blowpipescales += scale;
					player.sendMessage("You add "+scale+" scales to your staff, you have "+player.blowpipescales+" scales remaining.");
					return;
				} else {
					player.sendMessage("Your staff doesn't need anymore scales!");
					return;
				}
			}
			if (itemUsed.getId() == 29471 && usedWith.getId() == 29452) {
				int scale = player.getInventory().getNumerOf(29471);
				int scalespace = 16383 - player.blowpipescales;
				if (player.blowpipescales < 16383) {
					if (scalespace > scale) {
						scalespace = scale;
					}
					if (scale > 16383) {
						scale = 16383;
					}
					player.getInventory().deleteItem(29471, scale);
					player.blowpipescales += scale;
					player.sendMessage("You add "+scale+" scales to your staff, you have "+player.blowpipescales+" scales remaining.");
					return;
				} else {
					player.sendMessage("Your staff doesn't need anymore scales!");
					return;
				}
			}
			if (itemUsed.getId() == 29471 && usedWith.getId() == 29457) {
				int scale = player.getInventory().getNumerOf(29471);
				int scalespace = 16383 - player.blowpipescales;
				if (player.blowpipescales < 16383) {
					if (scalespace > scale) {
						scalespace = scale;
					}
					if (scale > 16383) {
						scale = 16383;
					}
					player.getInventory().deleteItem(29471, scale);
					player.blowpipescales += scale;
					player.sendMessage("You add "+scale+" scales to your blowpipe, you have "+player.blowpipescales+" scales remaining.");
					return;
				} else {
					player.sendMessage("Your blowpipe doesn't need anymore scales!");
					return;
				}
			}
			if (itemUsed.getId() == 29471 && usedWith.getId() == 29255) {
				int scale = player.getInventory().getNumerOf(29471);
				int scalespace = 16383 - player.blowpipescales;
				if (player.blowpipescales < 16383) {
					if (scalespace > scale) {
						scalespace = scale;
					}
					if (scale > 16383) {
						scale = 16383;
					}
					player.getInventory().deleteItem(29471, scale);
					player.blowpipescales += scale;
					player.sendMessage("You add " + scale + " scales to your blowpipe, you have " + player.blowpipescales + " scales remaining.");
					return;
				} else {
					player.sendMessage("Your blowpipe doesn't need anymore scales!");
					return;
				}
			}
			if (itemUsed.getId() == 11230 && usedWith.getId() == 29457) {
				int dart = player.getInventory().getNumerOf(11230);
				int dartspace = 16383 - player.blowpipedarts;
				if (player.blowpipedarts < 16383) {
					if (dartspace > dart) {
						dartspace = dart;
					}
					if (dart > 16383) {
						dart = 16383;
					}
					player.getInventory().deleteItem(11230, dart);
					player.blowpipedarts += dart;
					player.sendMessage("You add "+dart+" darts to your blowpipe, you have "+player.blowpipedarts+" darts remaining.");
					return;
				} else {
					player.sendMessage("Your blowpipe doesn't need anymore darts!");
					return;
				}
			}
			if (itemUsed.getId() == 11230 && usedWith.getId() == 29255) {
				int dart = player.getInventory().getNumerOf(11230);
				int dartspace = 16383 - player.blowpipedarts;
				if (player.blowpipedarts < 16383) {
					if (dartspace > dart) {
						dartspace = dart;
					}
					if (dart > 16383) {
						dart = 16383;
					}
					player.getInventory().deleteItem(11230, dart);
					player.blowpipedarts += dart;
					player.sendMessage("You add " + dart + " darts to your blowpipe, you have " + player.blowpipedarts + " darts remaining.");
					return;
				} else {
					player.sendMessage("Your blowpipe doesn't need anymore darts!");
					return;
				}
			}
			if (itemUsed.getId() == 29461 && usedWith.getId() == 4151) {
				player.getInventory().deleteItem(29461, 1);
				player.getInventory().deleteItem(4151, 1);
				player.getInventory().addItem(29470, 1);
				player.sendMessage("You combine the tentacle and whip to make an Abyssal tentacle.");
				return;
			}
			if (itemUsed.getId() == 15488 && usedWith.getId() == 13263) {
				if (player.getInventory().contains(15488) && player.getInventory().contains(13263)
						&& player.getInventory().contains(15490)) {
					player.getInventory().deleteItem(15488, 1);
					player.getInventory().deleteItem(13263, 1);
					player.getInventory().deleteItem(15490, 1);
					player.getInventory().addItem(15492, 1);
					player.sendMessage("You assemble the parts to create a Full slayer helmet.");
					return;
				} else {
					player.sendMessage("You need all 3 parts todo this.");
				}
				return;
			}
			if (itemUsed.getId() == 15490 && usedWith.getId() == 13263) {
				if (player.getInventory().contains(15488) && player.getInventory().contains(13263)
						&& player.getInventory().contains(15488)) {
					player.getInventory().deleteItem(15488, 1);
					player.getInventory().deleteItem(13263, 1);
					player.getInventory().deleteItem(15490, 1);
					player.getInventory().addItem(15492, 1);
					player.sendMessage("You assemble the parts to create a Full slayer helmet.");
					return;
				} else {
					player.sendMessage("You need all 3 parts todo this.");
				}
				return;
			}
			if (itemUsed.getId() == 29641 && usedWith.getId() == 29639 || usedWith.getId() == 29640) {
				if (player.getInventory().contains(29641) && player.getInventory().contains(29639)
						&& player.getInventory().contains(29640)) {
					player.getInventory().deleteItem(29641, 1);
					player.getInventory().deleteItem(29640, 1);
					player.getInventory().deleteItem(29639, 1);
					player.getInventory().addItem(29649, 1);
					return;
				} else {
					player.sendMessage("You need all 3 parts todo this.");
				}
				return;
			}
			if (itemUsed.getId() == 29055 && usedWith.getId() >= 1) {
				GwdArmourKit.upgradeItem(player, itemUsed.getId(), usedWith.getId());
			return;
			}
			if (itemUsed.getId() == 29805 || itemUsed.getId() == 29168 && usedWith.getId() >= 1) {
				ItemDyes.dyeItem(player, itemUsed.getId(), usedWith.getId());
			return;
			}
			if (itemUsed.getId() == 3188 && usedWith.getId() >= 1) {
				ItemDyeRemover.dyeItem(player, itemUsed.getId(), usedWith.getId());
			return;
			}
			if (ItemDefinitions.getItemDefinitions(itemUsed.getId()).getName().toLowerCase().contains("ornament kit")) {
				ItemOnItem.ItemonItem(player, itemUsed.getId(), usedWith.getId());
				return;
			}
			if (itemUsed.getId() == 19869 && usedWith.getId() == 29360) {
				player.getInventory().deleteItem(19869, 1);
				player.getInventory().deleteItem(29360, 1);
				player.getInventory().addItem(29342, 1);
				return;
			}
			if (itemUsed.getId() == 19869 && usedWith.getId() == 29359) {
				player.getInventory().deleteItem(19869, 1);
				player.getInventory().deleteItem(29359, 1);
				player.getInventory().addItem(29341, 1);
				return;
			}
			if (itemUsed.getId() == 19869 && usedWith.getId() == 29358) {
				player.getInventory().deleteItem(19869, 1);
				player.getInventory().deleteItem(29358, 1);
				player.getInventory().addItem(29340, 1);
				return;
			}
			if (itemUsed.getId() == 29721 && usedWith.getId() == 6585) {
				player.getInventory().deleteItem(29721, 1);
				player.getInventory().deleteItem(6585, 1);
				player.getInventory().addItem(29724, 1);
				player.sendMessage("You combine the amulet and blood shard to make a powerful amulet.");
				return;
			}
			if (itemUsed.getId() == 6585 && usedWith.getId() == 29721) {
				player.getInventory().deleteItem(29721, 1);
				player.getInventory().deleteItem(6585, 1);
				player.getInventory().addItem(29724, 1);
				player.sendMessage("You combine the amulet and blood shard to make a powerful amulet.");
				return;
			}
			if (itemUsed.getId() == 6585 && usedWith.getId() == 29504) {
				player.getInventory().deleteItem(6585, 1);
				player.getInventory().deleteItem(29504, 1);
				player.getInventory().addItem(29502, 1);
				player.sendMessage("You combine the amulets to get an upgraded version.");
				return;
			}
			if (itemUsed.getId() == 29965 && usedWith.getId() == 6731 || usedWith.getId() == 6737
					|| usedWith.getId() == 6733 || usedWith.getId() == 6735) {
				player.getDialogueManager().startDialogue("RingImbue");
				return;
			}

			if (itemUsed.getId() == 29601 && usedWith.getId() == 6585) {
				player.getInventory().deleteItem(6585, 1);
				player.getInventory().deleteItem(29601, 1);
				player.getInventory().addItem(29600, 1);
				player.sendMessage("You combine the shard with the amulet to create a powerful amulet.");
				return;
			}
			if (itemUsed.getId() == 6821 || itemUsed.getId() == 22323
					|| itemUsed.getId() == 29536 && usedWith.getId() > 0) {
				player.getDialogueManager().startDialogue("T92wepCreation");
				return;
			}
			if (itemUsed.getId() == 29542 && usedWith.getId() == 29541) {
				if (player.getInventory().containsItem(29542, 3) && player.getInventory().containsItem(29541, 2)) {
					player.getInventory().deleteItem(29542, 3);
					player.getInventory().deleteItem(29541, 2);
					player.getInventory().addItem(29540, 1);
					player.sendMessage("<col=00ff00>You combine 3 level 1 & 2 level 2 keys to make a level 3.");
					return;
				} else {
					player.sendMessage("<col=ff0000> You need 3 level 1 & 2 level 2 keys to make a level 3 key.");
					return;
				}
			}
			if (itemUsed.getId() == 29541 && usedWith.getId() == 29542) {
				if (player.getInventory().containsItem(29542, 3) && player.getInventory().containsItem(29541, 2)) {
					player.getInventory().deleteItem(29542, 3);
					player.getInventory().deleteItem(29541, 2);
					player.getInventory().addItem(29540, 1);
					player.sendMessage("<col=00ff00>You combine 3 level 1 & 2 level 2 keys to make a level 3.");
					return;
				} else {
					player.sendMessage("<col=ff0000> You need 3 level 1 & 2 level 2 keys to make a level 3 key.");
					return;
				}
			}
			if (itemUsed.getId() == 29942 && usedWith.getId() > 0) {
				if (player.getInventory().contains(29942)) {
					player.getDialogueManager().startDialogue("MalevolentCrafting");
				} else {
					player.getPackets().sendGameMessage("You need malevolent energy to make this!"); // shard
				}
			}
			if (itemUsed.getId() == 29677 && usedWith.getId() > 0) {
				if (!ItemConstants.isTradeable(usedWith)) {
					player.getPackets().sendGameMessage("This item cannot be banked.");
					return;
				}
				if (player.getAttackedByDelay() + 10000 > Utils.currentTimeMillis()) {
					player.getDialogueManager().startDialogue("SimpleMessage",
							"You can't do this whilst under attack!");
					return;
				}
				if (player.isCanPvp()) {
					player.getDialogueManager().startDialogue("SimpleMessage",
							"You can't do this whilst in a PvP zone!");
					return;
				}
				if (player.getControlerManager().getControler() != null || World.HungerGames(player)
						|| World.Scorpia(player) || World.TheCalamity(player)) {
					player.getDialogueManager().startDialogue("SimpleMessage", "You can't do this right now!");
					return;
				}
				player.getInventory().deleteItem(usedWith.getId(), 1);
				player.getInventory().deleteItem(itemUsed.getId(), 1);
				player.getBank().addItem(usedWith.getId(), 1, true);
				player.getInventory().refresh();
				return;
			}
			if (itemUsed.getId() == 32692 && usedWith.getId() == 6585) {
				if (player.getInventory().contains(32692) && player.getInventory().contains(6585)) {
					player.getInventory().deleteItem(32692, 1);
					player.getInventory().deleteItem(6585, 1);
					player.getInventory().addItem(32703, 1);
					player.sendMessage("You combine the shard with the fury to make a new amulet.");
					return;
				}
			}
			if (itemUsed.getId() == 1485 && usedWith.getId() == 13758) {
				if (player.getInventory().contains(14496)) {
					player.getInventory().deleteItem(1485, 1);
					player.getInventory().deleteItem(13758, 1);
					player.sendMessage("You don't need to do this.");
					return;
				}
				player.getInventory().deleteItem(1485, 1);
				player.getInventory().deleteItem(13758, 1);
				player.getInventory().addItem(14496, 1);
				player.sendMessage("You clean up the key.");
				return;
			}
			if (itemUsed.getId() == 15486 && usedWith.getId() == 29475) {
				if (player.wyrmstaffcharges < 1) {
					player.getInventory().deleteItem(15486, 1);
					player.wyrmstaffcharges += 60000;
					player.sendMessage("You charge your Staff of Darkness. It now has " + player.wyrmstaffcharges
							+ " charges left.");
					return;
				} else {
					player.sendMessage("Your staff still has charges, use these before try this.");
					return;
				}
			}
			if (itemUsed.getId() == 29479 && usedWith.getId() == 4151) {
				player.getInventory().deleteItem(29479, 1);
				player.getInventory().deleteItem(4151, 1);
				player.getInventory().addItem(29474, 1);
				player.sendMessage("You combine your Sunfreet spike with the Abyssal whip to make a Lava whip.");
				player.sendMessage(
						"<col=00ff00> You currently have " + player.wyrmwhipcharges + " charges on your whip.");
				return;

			}

			if (itemUsed.getId() == 4151 && usedWith.getId() == 29474) {
				if (player.wyrmwhipcharges < 1) {
					player.getInventory().deleteItem(4151, 1);
					player.wyrmwhipcharges += 60000;
					player.sendMessage(
							"You charge your Lava whip. It now has " + player.wyrmwhipcharges + " charges left.");
					return;
				} else {
					player.sendMessage("Your whip still has charges, use these before try this.");
					return;
				}
			}
			if (itemUsed.getId() == 29478 && usedWith.getId() == 15486) {
				player.getInventory().deleteItem(29478, 1);
				player.getInventory().deleteItem(15486, 1);
				player.getInventory().addItem(29475, 1);
				player.sendMessage("You combine your Sunfreet heart with the Staff of light to make a Staff of Darkness.");
				player.sendMessage(
						"<col=00ff00> You currently have " + player.wyrmstaffcharges + " charges on your staff.");
				return;

			}

			if (itemUsed.getId() == 11235 && usedWith.getId() == 29476) {
				if (player.wyrmbowcharges < 1) {
					player.getInventory().deleteItem(11235, 1);
					player.wyrmbowcharges += 60000;
					player.sendMessage(
							"You charge your Strykebow. It now has " + player.wyrmbowcharges + " charges left.");
					return;
				} else {
					player.sendMessage("Your staff still has charges, use these before try this.");
					return;
				}
			}
			if (itemUsed.getId() == 29477 && usedWith.getId() == 11235) {
				player.getInventory().deleteItem(29477, 1);
				player.getInventory().deleteItem(11235, 1);
				player.getInventory().addItem(29476, 1);
				player.sendMessage("You combine your Sunfreet scalp with the Dark bow to make a Strykebow.");
				player.sendMessage(
						"<col=00ff00> You currently have " + player.wyrmbowcharges + " charges on your bow.");
				return;

			}
			if (itemUsed.getId() == 1526 && usedWith.getId() == 1785) {
				player.getInventory().deleteItem(1526, 1);
				player.sendMessage("You now have 10% extra experience for 1 hour.");
				CoresManager.fastExecutor.schedule(new TimerTask() {
					int timer = 3600;

					@Override
					public void run() {
						player.tenxp = true;
						if (timer == 1) {
							player.tenxp = false;
						}
					}
				}, 0L, 1000L);
				return;
			}
			if (itemUsed.getId() == 31718 && usedWith.getId() == 31719 || itemUsed.getId() == 31722
					|| usedWith.getId() == 31720) {
				if (player.getInventory().contains(31718) && player.getInventory().contains(31719)
						&& player.getInventory().contains(31722) && player.getInventory().contains(31720)) {
					player.getInventory().deleteItem(31718, 1);
					player.getInventory().deleteItem(31719, 1);
					player.getInventory().deleteItem(31720, 1);
					player.getInventory().deleteItem(31722, 1);
					player.getInventory().addItem(31729, 1);
					return;
				} else {
					player.sendMessage("You need all 3 parts todo this.");
				}
				return;
			}
			/**
			 * KeepSake - Author - Mr_Joopz
			 */
			if (itemUsed.getId() == 29544 || itemUsed.getId() == 29371) {
				int hi = ItemDefinitions.getItemDefinitions(usedWith.getId()).getEquipSlot();
				if (hi != Equipment.SLOT_AMULET && hi != Equipment.SLOT_CAPE && hi != Equipment.SLOT_CHEST
						&& hi != Equipment.SLOT_FEET && hi != Equipment.SLOT_HANDS && hi != Equipment.SLOT_HAT
						&& hi != Equipment.SLOT_LEGS && hi != Equipment.SLOT_SHIELD && hi != Equipment.SLOT_WEAPON) {
					player.sendMessage("This item cannot be keepsaked.");
					return;
				}
				if (!player.getInventory().contains(itemUsed.getId())
						|| !player.getInventory().contains(usedWith.getId())) {
					player.sendMessage("This has been reported to our staff team regarding exploitment.");
					return;
				}
				if (!ItemConstants.canWear(usedWith, player)) {
					return;
				}
				// if (ButtonHandler.sendWear(player,
				// ItemDefinitions.getItemDefinitions(usedWith.getId()).getEquipSlot(),
				// usedWith.getId())) {
				HashMap<Integer, Integer> requiriments = usedWith.getDefinitions().getWearingSkillRequiriments();
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
							player.getPackets().sendGameMessage("You need to have a" + (name.startsWith("a") ? "n" : "")
									+ " " + name + " level of " + level + ".");
						}

						// }
					}
					if (!hasRequiriments) {
						return;
					}
				}
				if (ItemConstants.getItemDefaultCharges(usedWith.getId()) != -1) {
					player.sendMessage("You cannot keepsake this item.");
					return;

				}
				if (ItemDefinitions.getItemDefinitions(usedWith.getId()).getEquipSlot() == Equipment.SLOT_CAPE) {
					if (player.keepsakecape != 0) {
						player.sendMessage("Your currently have something in your Cape keepsake, remove this first.");
						return;
					}
					int KeepSaked = usedWith.getId();
					player.getInventory().deleteItem(KeepSaked, 1);
					player.getInventory().deleteItem(itemUsed.getId(), 1);
					player.keepsakecape = KeepSaked;
					player.sendMessage("Your current Cape keepsaked item is a <col=00ff00>"
							+ ItemDefinitions.getItemDefinitions(player.keepsakecape).getName() + "</col>.");
					return;
				} else if (ItemDefinitions.getItemDefinitions(usedWith.getId()).getEquipSlot() == Equipment.SLOT_HAT) {
					if (player.keepsakehat != 0) {
						player.sendMessage("Your currently have something in your Hat keepsake, remove this first.");
						return;
					}
					int KeepSaked = usedWith.getId();
					player.getInventory().deleteItem(KeepSaked, 1);
					player.getInventory().deleteItem(itemUsed.getId(), 1);
					player.keepsakehat = KeepSaked;
					player.sendMessage("Your current keepsaked Hat item is a <col=00ff00>"
							+ ItemDefinitions.getItemDefinitions(player.keepsakehat).getName() + "</col>.");
					return;
				} else if (ItemDefinitions.getItemDefinitions(usedWith.getId())
						.getEquipSlot() == Equipment.SLOT_AMULET) {
					if (player.keepsakeamulet != 0) {
						player.sendMessage("Your currently have something in your Amulet keepsake, remove this first.");
						return;
					}
					int KeepSaked = usedWith.getId();
					player.getInventory().deleteItem(KeepSaked, 1);
					player.getInventory().deleteItem(itemUsed.getId(), 1);
					player.keepsakeamulet = KeepSaked;
					player.sendMessage("Your current keepsaked Amulet item is a <col=00ff00>"
							+ ItemDefinitions.getItemDefinitions(player.keepsakeamulet).getName() + "</col>.");
					return;
				} else if (ItemDefinitions.getItemDefinitions(usedWith.getId())
						.getEquipSlot() == Equipment.SLOT_CHEST) {
					if (player.keepsakebody != 0) {
						player.sendMessage("Your currently have something in your Body keepsake, remove this first.");
						return;
					}
					int KeepSaked = usedWith.getId();
					player.getInventory().deleteItem(KeepSaked, 1);
					player.getInventory().deleteItem(itemUsed.getId(), 1);
					player.keepsakebody = KeepSaked;
					player.sendMessage("Your current keepsaked Body item is a <col=00ff00>"
							+ ItemDefinitions.getItemDefinitions(player.keepsakebody).getName() + "</col>.");
					return;
				} else if (ItemDefinitions.getItemDefinitions(usedWith.getId()).getEquipSlot() == Equipment.SLOT_FEET) {
					if (player.keepsakeboots != 0) {
						player.sendMessage("Your currently have something in your Boots keepsake, remove this first.");
						return;
					}
					int KeepSaked = usedWith.getId();
					player.getInventory().deleteItem(KeepSaked, 1);
					player.getInventory().deleteItem(itemUsed.getId(), 1);
					player.keepsakeboots = KeepSaked;
					player.sendMessage("Your current keepsaked Boots item is a <col=00ff00>"
							+ ItemDefinitions.getItemDefinitions(player.keepsakeboots).getName() + "</col>.");
					return;
				} else if (ItemDefinitions.getItemDefinitions(usedWith.getId())
						.getEquipSlot() == Equipment.SLOT_HANDS) {
					if (player.keepsakegloves != 0) {
						player.sendMessage("Your currently have something in your Gloves keepsake, remove this first.");
						return;
					}
					int KeepSaked = usedWith.getId();
					player.getInventory().deleteItem(KeepSaked, 1);
					player.getInventory().deleteItem(itemUsed.getId(), 1);
					player.keepsakegloves = KeepSaked;
					player.sendMessage("Your current keepsaked Gloves item is a <col=00ff00>"
							+ ItemDefinitions.getItemDefinitions(player.keepsakegloves).getName() + "</col>.");
					return;
				} else if (ItemDefinitions.getItemDefinitions(usedWith.getId()).getEquipSlot() == Equipment.SLOT_LEGS) {
					if (player.keepsakelegs != 0) {
						player.sendMessage("Your currently have something in your Legs keepsake, remove this first.");
						return;
					}
					int KeepSaked = usedWith.getId();
					player.getInventory().deleteItem(KeepSaked, 1);
					player.getInventory().deleteItem(itemUsed.getId(), 1);
					player.keepsakelegs = KeepSaked;
					player.sendMessage("Your current keepsaked Legs item is a <col=00ff00>"
							+ ItemDefinitions.getItemDefinitions(player.keepsakelegs).getName() + "</col>.");
					return;
				} else if (ItemDefinitions.getItemDefinitions(usedWith.getId())
						.getEquipSlot() == Equipment.SLOT_SHIELD) {
					if (player.keepsakeshield != 0) {
						player.sendMessage("Your currently have something in your Shield keepsake, remove this first.");
						return;
					}
					int KeepSaked = usedWith.getId();
					player.getInventory().deleteItem(KeepSaked, 1);
					player.getInventory().deleteItem(itemUsed.getId(), 1);
					player.keepsakeshield = KeepSaked;
					player.sendMessage("Your current keepsaked Shield item is a <col=00ff00>"
							+ ItemDefinitions.getItemDefinitions(player.keepsakeshield).getName() + "</col>.");
					return;
				} else if (ItemDefinitions.getItemDefinitions(usedWith.getId())
						.getEquipSlot() == Equipment.SLOT_WEAPON) {
					if (player.keepsakeweapon != 0) {
						player.sendMessage("Your currently have something in your Weapon keepsake, remove this first.");
						return;
					}
					int KeepSaked = usedWith.getId();
					player.getInventory().deleteItem(KeepSaked, 1);
					player.getInventory().deleteItem(itemUsed.getId(), 1);
					player.keepsakeweapon = KeepSaked;
					player.sendMessage("Your current keepsaked Weapon item is a <col=00ff00>"
							+ ItemDefinitions.getItemDefinitions(player.keepsakeweapon).getName() + "</col>.");
					return;
				}

			}
			if (usedWith.getId() == 18339) {
				if (itemUsed.getId() == 453) {
					int amount = player.getInventory().getNumerOf(itemUsed.getId());
					if (player.coalbagamount >= 81) {
						player.sendMessage("You can only store up to 81 coals in this bag!");
						return;
					}
					if (player.coalbagamount + amount >= 81 || amount >= 81) {
						amount = 81 - player.coalbagamount;
					}

					player.getInventory().deleteItem(itemUsedId, amount);
					player.coalbagamount += amount;
					return;
				}
			}
			if (itemUsed.getName().endsWith("bar") && usedWith.getId() == 29793) {
				if (itemUsed.getId() == 2349 || itemUsed.getId() == 2350) {
					int amount = player.getInventory().getNumerOf(itemUsedId);
					if (player.totalbagbars >= 50 || player.totalbagbars + amount >= 50 || amount >= 50) {
						player.sendMessage("You can only store up to 50 bars in this bag!");
						return;
					}
					player.getInventory().deleteItem(itemUsedId, amount);
					player.bbar += amount;
					player.totalbagbars += amount;
					return;
				}
				if (itemUsed.getId() == 2351 || itemUsed.getId() == 2352) {
					int amount = player.getInventory().getNumerOf(itemUsedId);
					if (player.totalbagbars >= 50 || player.totalbagbars + amount >= 50 || amount >= 50) {
						player.sendMessage("You can only store upto 50 bars in this bag!");
						return;
					}
					player.getInventory().deleteItem(itemUsedId, amount);
					player.ibar += amount;
					player.totalbagbars += amount;
					return;
				}
				if (itemUsed.getId() == 2353 || itemUsed.getId() == 2354) {
					int amount = player.getInventory().getNumerOf(itemUsedId);
					if (player.totalbagbars >= 50 || player.totalbagbars + amount >= 50 || amount >= 50) {
						player.sendMessage("You can only store upto 50 bars in this bag!");
						return;
					}
					player.getInventory().deleteItem(itemUsedId, amount);
					player.sbar += amount;
					player.totalbagbars += amount;
					return;
				}
				if (itemUsed.getId() == 2359 || itemUsed.getId() == 2360) {
					int amount = player.getInventory().getNumerOf(itemUsedId);
					if (player.totalbagbars >= 50 || player.totalbagbars + amount >= 50 || amount >= 50) {
						player.sendMessage("You can only store upto 50 bars in this bag!");
						return;
					}
					player.getInventory().deleteItem(itemUsedId, amount);
					player.mbar += amount;
					player.totalbagbars += amount;
					return;
				}
				if (itemUsed.getId() == 2361 || itemUsed.getId() == 2362) {
					int amount = player.getInventory().getNumerOf(itemUsedId);
					if (player.totalbagbars >= 50 || player.totalbagbars + amount >= 50 || amount >= 50) {
						player.sendMessage("You can only store upto 50 bars in this bag!");
						return;
					}
					player.getInventory().deleteItem(itemUsedId, amount);
					player.abar += amount;
					player.totalbagbars += amount;
					return;
				}
				if (itemUsed.getId() == 2363 || itemUsed.getId() == 2364) {
					int amount = player.getInventory().getNumerOf(itemUsedId);
					if (player.totalbagbars >= 50 || player.totalbagbars + amount >= 50 || amount >= 50) {
						player.sendMessage("You can only store upto 50 bars in this bag!");
						return;
					}
					player.getInventory().deleteItem(itemUsedId, amount);
					player.rbar += amount;
					player.totalbagbars += amount;
					return;
				}
				return;
			}
			if (itemUsed.getId() == 10176 && usedWith.getName().contains("Silverhawk boots")) {
				int feathers = player.getInventory().getNumerOf(10176);
				player.getInventory().deleteItem(10176, feathers);
				player.silverhawks += feathers;
				player.sendMessage("Your silverhawk boots now have " + player.silverhawks + " charges.");
				return;
			}
			if (itemUsed.getName().contains("hilt") && usedWith.getName().contains("Godsword blade")) {

				Godsword.assemble(player, itemUsed.getId());
				return;
			}
			if (itemUsed.getId() == LeatherCrafting.NEEDLE.getId()
					|| usedWith.getId() == LeatherCrafting.NEEDLE.getId()) {
				if (LeatherCrafting.handleItemOnItem(player, itemUsed, usedWith)) {
					return;
				}
			}
			if (itemUsed.getId() == GlassBlowing.PIPE.getId()
					|| usedWith.getId() == GlassBlowing.PIPE.getId()) {
				if (GlassBlowing.handleItemOnItem(player, itemUsed, usedWith)) {
					return;
				}
			}
			if (itemUsed.getId() == BattleStaveCreation.STAVE.getId()
					|| usedWith.getId() == BattleStaveCreation.ORBS.length) {
				if (BattleStaveCreation.handleItemOnItem(player, itemUsed, usedWith)) {
					return;
				}
			}
			Sets set = ArmourSets.getArmourSet(itemUsedId, itemUsedWithId);
			if (set != null) {
				ArmourSets.exchangeSets(player, set);
				return;
			}
			if (Firemaking.isFiremaking(player, itemUsed, usedWith)) {
				return;
			} else if (contains(1755, Gem.OPAL.getUncut(), itemUsed, usedWith)) {
				GemCutting.cut(player, Gem.OPAL);
			} else if (contains(1755, Gem.JADE.getUncut(), itemUsed, usedWith)) {
				GemCutting.cut(player, Gem.JADE);
			} else if (contains(1755, Gem.RED_TOPAZ.getUncut(), itemUsed, usedWith)) {
				GemCutting.cut(player, Gem.RED_TOPAZ);
			} else if (contains(1755, Gem.SAPPHIRE.getUncut(), itemUsed, usedWith)) {
				GemCutting.cut(player, Gem.SAPPHIRE);
			} else if (contains(1755, Gem.EMERALD.getUncut(), itemUsed, usedWith)) {
				GemCutting.cut(player, Gem.EMERALD);
			} else if (contains(1755, Gem.RUBY.getUncut(), itemUsed, usedWith)) {
				GemCutting.cut(player, Gem.RUBY);
			} else if (contains(1755, Gem.DIAMOND.getUncut(), itemUsed, usedWith)) {
				GemCutting.cut(player, Gem.DIAMOND);
			} else if (contains(1755, Gem.DRAGONSTONE.getUncut(), itemUsed, usedWith)) {
				GemCutting.cut(player, Gem.DRAGONSTONE);
			} else if (contains(1755, Gem.ONYX.getUncut(), itemUsed, usedWith)) {
				GemCutting.cut(player, Gem.ONYX);
			} else if (contains(1755, Gem.CUT_OPAL.getUncut(), itemUsed, usedWith)) {
				GemCutting.cut(player, Gem.CUT_OPAL);
			} else if (contains(1755, Gem.CUT_JADE.getUncut(), itemUsed, usedWith)) {
				GemCutting.cut(player, Gem.CUT_JADE);
			} else if (contains(1755, Gem.CUT_RED_TOPAZ.getUncut(), itemUsed, usedWith)) {
				GemCutting.cut(player, Gem.CUT_RED_TOPAZ);
			} else if (contains(1755, Gem.CUT_SAPPHIRE.getUncut(), itemUsed, usedWith)) {
				GemCutting.cut(player, Gem.CUT_SAPPHIRE);
			} else if (contains(1755, Gem.CUT_EMERALD.getUncut(), itemUsed, usedWith)) {
				GemCutting.cut(player, Gem.CUT_EMERALD);
			} else if (contains(1755, Gem.CUT_RUBY.getUncut(), itemUsed, usedWith)) {
				GemCutting.cut(player, Gem.CUT_RUBY);
			} else if (contains(1755, Gem.CUT_DIAMOND.getUncut(), itemUsed, usedWith)) {
				GemCutting.cut(player, Gem.CUT_DIAMOND);
			} else if (contains(1755, Gem.CUT_DRAGONSTONE.getUncut(), itemUsed, usedWith)) {
				GemCutting.cut(player, Gem.CUT_DRAGONSTONE);
			} else if (contains(1755, Gem.CUT_ONYX.getUncut(), itemUsed, usedWith)) {
				GemCutting.cut(player, Gem.CUT_ONYX);
			} else if (itemUsed.getId() == 1755 && usedWith.getId() == 29728) {
				player.getDialogueManager().startDialogue("HydrixCut");
			} else if (itemUsed.getId() == 1755 && usedWith.getId() == 3211) {
				player.getInventory().deleteItem(3211, 1);
				player.getInventory().addItem(3420, 1);
			} else if (itemUsed.getId() == 14484 && usedWith.getId() == 29903) {
				player.getInventory().deleteItem(14484, 1);
				player.getInventory().deleteItem(29903, 1);
				player.getInventory().addItem(29908, 1);
				player.getPackets().sendGameMessage("You create a powerful set of claws!");

			} else if (itemUsed.getId() == 29903 && usedWith.getId() == 14484) {
				player.getInventory().deleteItem(14484, 1);
				player.getInventory().deleteItem(29903, 1);
				player.getInventory().addItem(29908, 1);
				player.getPackets().sendGameMessage("You create a powerful set of claws!");
			} else if (itemUsed.getId() == 29903 && usedWith.getId() == 11716) {
				player.getInventory().deleteItem(29903, 1);
				player.getInventory().deleteItem(11716, 1);
				player.getInventory().addItem(29512, 1);
				player.getPackets().sendGameMessage("You create a powerful spear!");
			} else if (itemUsed.getId() == 11716 && usedWith.getId() == 29903) {
				player.getInventory().deleteItem(29903, 1);
				player.getInventory().deleteItem(11716, 1);
				player.getInventory().addItem(29512, 1);
				player.getPackets().sendGameMessage("You create a powerful spear!");
			

			} else if (itemUsed.getId() == 27068 && usedWith.getId() == 11716) {
				if (!player.getInventory().containsItem(11716, 1) || !player.getInventory().containsItem(27068, 1)) {
					return;
				}
				player.getInventory().deleteItem(27068, 1);
				player.getInventory().deleteItem(11716, 1);
				player.getInventory().addItem(31463, 1);
				player.getPackets()
						.sendGameMessage("You add the spike to your zamorakian spear to create a chaotic spear.");

			}

			else if (itemUsed.getId() == 9067 && usedWith.getId() == 15333) {
				if (player.isExtremeDonator() == false) {
					player.sendMessage("You need to be a extreme donator to make this potion!");
					return;
				}
				if (player.getSkills().getLevelForXp(Skills.HERBLORE) < 99) {
					player.sendMessage("You need 99 Herblore to make this!");
					return;
				}
				player.getInventory().deleteItem(15333, 1);
				player.getInventory().deleteItem(9067, 1);
				player.getInventory().addItem(12140, 1);
				player.getSkills().addXp(15, 2000);
				
				player.getPackets().sendGameMessage("You create one of the most powerful potions known to mankind!");
			} else if (itemUsed.getId() == 9067 && usedWith.getId() == 15332) {
				if (player.isExtremeDonator() == false) {
					player.sendMessage("You need to be a extreme donator to make this potion!");
					return;
				}
				if (player.getSkills().getLevelForXp(Skills.HERBLORE) < 99) {
					player.sendMessage("You need 99 Herblore to make this!");
					return;
				}
				player.getInventory().deleteItem(15332, 1);
				player.getInventory().deleteItem(9067, 1);
				player.getInventory().addItem(12140, 1);
				player.getSkills().addXp(15, 2000);

				player.getPackets().sendGameMessage("You create one of the most powerful potions known to mankind!");
			} else if (itemUsed.getId() == 29468 && usedWith.getId() == 29463) {

				player.getInventory().deleteItem(29468, 1);
				player.getInventory().deleteItem(29463, 1);
				player.getInventory().addItem(29452, 1);
				player.getPackets().sendGameMessage(
						"You upgrade the trident.");
		} else if (itemUsed.getId() == 29468 && usedWith.getId() == 29454) {

			player.getInventory().deleteItem(29454, 1);//toxic staff
			player.getInventory().deleteItem(29468, 1);
				player.getInventory().addItem(29453, 1);
			player.getPackets().sendGameMessage(
					"You upgrade the staff.");
		}
		
			else if (itemUsed.getId() == 13734 && usedWith.getId() == 13754) {

				player.getInventory().deleteItem(13734, 1);
				player.getInventory().deleteItem(13754, 1);
				player.getInventory().addItem(13736, 1);
				player.getPackets().sendGameMessage(
						"You have poured the holy elixir on a spirit shield making it unleash Blessed powers.");
			}

			else if (itemUsed.getId() == 13754 && usedWith.getId() == 13734) {

				player.getInventory().deleteItem(13734, 1);
				player.getInventory().deleteItem(13754, 1);
				player.getInventory().addItem(13736, 1);
				player.getPackets().sendGameMessage(
						"You have poured the holy elixir on a spirit shield making it unleash Blessed powers.");

			}

			else if (itemUsed.getId() == 13736 && usedWith.getId() == 13748) {

				player.getInventory().deleteItem(13736, 1);
				player.getInventory().deleteItem(13748, 1);
				player.getInventory().addItem(13740, 1);
				player.getPackets().sendGameMessage(
						"You force the sigil upon the blessed spirit shield making it unleash Divine Powers.");

			} else if (itemUsed.getId() == 13736 && usedWith.getId() == 13750) {

				player.getInventory().deleteItem(13736, 1);
				player.getInventory().deleteItem(13750, 1);
				player.getInventory().addItem(13742, 1);
				player.getPackets().sendGameMessage(
						"You force the sigil upon the blessed spirit shield making it unleash Elysian Powers.");

			} else if (itemUsed.getId() == 13736 && usedWith.getId() == 13746) {

				player.getInventory().deleteItem(13736, 1);
				player.getInventory().deleteItem(13746, 1);
				player.getInventory().addItem(13738, 1);
				player.getPackets().sendGameMessage(
						"You force the sigil upon the blessed spirit shield making it unleash Arcane Powers.");

			} else if (itemUsed.getId() == 13746 && usedWith.getId() == 13736) {

				player.getInventory().deleteItem(13736, 1);
				player.getInventory().deleteItem(13746, 1);
				player.getInventory().addItem(13738, 1);
				player.getPackets().sendGameMessage(
						"You force the sigil upon the blessed spirit shield making it unleash Arcane Powers.");

			} else if (itemUsed.getId() == 13736 && usedWith.getId() == 13752) {

				player.getInventory().deleteItem(13736, 1);
				player.getInventory().deleteItem(13752, 1);
				player.getInventory().addItem(13744, 1);
				player.getPackets().sendGameMessage(
						"You force the sigil upon the blessed spirit shield making it unleash Spectral Powers.");

			} else if (itemUsed.getId() == 13752 && usedWith.getId() == 13736) {

				player.getInventory().deleteItem(13736, 1);
				player.getInventory().deleteItem(13752, 1);
				player.getInventory().addItem(13744, 1);
				player.getPackets().sendGameMessage(
						"You force the sigil upon the blessed spirit shield making it unleash Spectral Powers.");
			}

			else if (itemUsed.getId() == 24374 && usedWith.getId() == 1733) {
				player.getDialogueManager().startDialogue("RoyalCraft");
			} else if (itemUsed.getId() == 1733 && usedWith.getId() == 24374) {
				player.getDialogueManager().startDialogue("RoyalCraft");
			}

			else if (itemUsed.getId() == 22451 && usedWith.getId() == 1733) {

				player.getDialogueManager().startDialogue("GanoCraft");
			}

			else if (itemUsed.getId() == 1733 && usedWith.getId() == 22451) {

				player.getDialogueManager().startDialogue("GanoCraft");
			}

			else if (itemUsed.getId() == 1540 && usedWith.getId() == 11286) {
				if (player.getSkills().getLevelForXp(Skills.SMITHING) < 90) {
					player.sendMessage("You need a smithing level of 90 to make this.");
					return;
				}
				player.getSkills().addXp(13, 2000);
				player.getInventory().deleteItem(11286, 1);
				player.getInventory().deleteItem(1540, 1);
				player.getInventory().addItem(11283, 1);
			}


			else if (itemUsed.getId() == 24340 && usedWith.getId() == 24342
					&& player.getInventory().containsItem(24344, 1) && player.getInventory().containsItem(24346, 1)) {
				if (player.royalcompmade == false) {
					World.sendWorldMessage("<col=339966>" + player.getDisplayName() + " has just completed the create the royal crossbow completionist requirement!", false);
					player.royalcompmade = true;
				}
				player.getInventory().deleteItem(24340, 1);
				player.getInventory().deleteItem(24342, 1);
				player.getInventory().deleteItem(24344, 1);
				player.getInventory().deleteItem(24346, 1);
				player.getInventory().addItem(24338, 1);
				player.getPackets().sendGameMessage("You create the royal crossbow!");
			} else if (itemUsed.getId() == 11710 && usedWith.getId() == 11712) {

				player.getInventory().deleteItem(11710, 1);
				player.getInventory().deleteItem(11712, 1);
				player.getInventory().addItem(11686, 1);
				player.getPackets().sendGameMessage("you join the shards together!");

			} else if (itemUsed.getId() == 11712 && usedWith.getId() == 11714) {

				player.getInventory().deleteItem(11712, 1);
				player.getInventory().deleteItem(11714, 1);
				player.getInventory().addItem(11692, 1);

				player.getPackets().sendGameMessage("You join the shards together!");

			} else if (itemUsed.getId() == 11686 && usedWith.getId() == 11714) {

				player.getInventory().deleteItem(11686, 1);
				player.getInventory().deleteItem(11714, 1);
				player.getInventory().addItem(11690, 1);
				player.getPackets().sendGameMessage("You join the shards together!");

			} else if (itemUsed.getId() == 11710 && usedWith.getId() == 11692) {

				player.getInventory().deleteItem(11710, 1);
				player.getInventory().deleteItem(11692, 1);
				player.getInventory().addItem(11690, 1);
				player.getPackets().sendGameMessage("You join the shards together!");

			} else if (itemUsed.getId() == 11712 && usedWith.getId() == 11688) {

				player.getInventory().deleteItem(11712, 1);
				player.getInventory().deleteItem(11688, 1);
				player.getInventory().addItem(11690, 1);
				player.getPackets().sendGameMessage("You join the shards together!");

			} else {
				player.getPackets().sendGameMessage("Nothing interesting happens.");
				if (Settings.DEBUG) {
					Logger.log("ItemHandler", "Used:" + itemUsed.getId() + ", With:" + usedWith.getId());
					System.out.println("Option 1");
				}
			}
		}
	}

	public static void handleItemOption3(Player player, int slotId, int itemId, Item item) {
		long time = Utils.currentTimeMillis();
		if (player.getLockDelay() >= time || player.getEmotesManager().getNextEmoteEnd() >= time) {
			return;
		}

		player.stopAll(false);
		FlyingEntities impJar = FlyingEntities.forItem((short) itemId);
		if (impJar != null) {
			FlyingEntityHunter.openJar(player, impJar, slotId);
		}
		if (itemId == 20767 || itemId == 20769 || itemId == 20771) {
			SkillCapeCustomizer.startCustomizing(player, itemId);
		} else if (itemId >= 15084 && itemId <= 15100) {
			Dicing.handleRoll(player, 15092, false);
		} else if (itemId == 24437 || itemId == 24439 || itemId == 24440 || itemId == 24441) {
			player.getDialogueManager().startDialogue("FlamingSkull", item, slotId);
		} else if (itemId == 4155) {
			if (player.getTask() != null) {
				player.sendMessage("You currently have " + player.getTask().getTaskAmount() + " "+ player.getTask().getName() + " left to kill.");
			}
		}
		else if (itemId == 29475) {
			player.sendMessage("Your staff has <col=00ff00>" + player.wyrmstaffcharges + "</col> charges left.");
		} else if (itemId == 21371) {
			if (player.getInventory().getFreeSlots() < 2) {
				player.sendMessage("You must have atleast 2 free inventory spaces to do this.");
				return;
			}
			player.getInventory().deleteItem(21371, 1);
			player.getInventory().addItem(4151, 1);
			player.getInventory().addItem(21369, 1);
			return;
		} else if (itemId == 29210 || itemId == 29208) {
			player.getLootingBag().depositChestToBank();
		} else if (itemId == 29379 || itemId == 29378 || itemId == 29377) {
			player.sendMessage("Your weapon has <col=00ff00>" + player.widywepcharges + "</col> charges left.");
		} else if (player.getDwarvenrockCake().eatCake1HP(player, itemId)) {
			return;
		} else if (itemId == 29922) {
			player.sendMessage("<col=FF9999>Your silverhawks boots have " + player.silverhawks + " charges left.");
		} else if (itemId == 29504 || itemId == 29502) {
			player.sendMessage("You currently have "+player.aniviaamuletcharges+" charges left.");
		} else if (itemId == 29457 || itemId == 29255) {
			player.sendMessage("Your blowpipe currently has "+player.blowpipedarts+" darts & "+player.blowpipescales+" scales remaining.");
		} else if (itemId == 29452) {
			player.sendMessage("Your trident currently has "+player.blowpipescales+" scales remaining.");
		} else if (itemId == 29726 || itemId == 29727 || itemId == 29267 || itemId == 29268) {
			player.sendMessage("Your ring has " + player.ringofdeathcharges + " charges remaining.");
		} else if (itemId == 29984 && player.getSkills().getXp(Skills.MAGIC) >= 104273167) {
			player.getDialogueManager().startDialogue("AltarSpells");
		} else if (itemId == 29116) {
			player.getDialogueManager().startDialogue("Runecoiner");
		} else if (itemId == 29995 && player.getSkills().getXp(Skills.CONSTRUCTION) >= 104273167) {
			player.getDialogueManager().startDialogue("EnterHouseD");
		} else if (itemId == 29979 && player.getSkills().getXp(Skills.SLAYER) >= 104273167) {
			MasterSlayerTaskInterface.OpenInterface(player);
		} else if (itemId == 29985 && player.dailychins != true && player.getSkills().getXp(Skills.HUNTER) >= 104273167) {
			player.DailymasterChins();
		} else if (itemId == 5022 && player.getInventory().getFreeSlots() > 0) {
				int amountofgpinv = player.getInventory().getNumerOf(995);
				int amountoftickets = player.getInventory().getNumerOf(5022);
				int space = Integer.MAX_VALUE - amountofgpinv;
				if (space < 1000000) {
					return;
				}
				if (amountoftickets > 2147) {
					amountoftickets = 2147;
				}
				if (amountoftickets * 1000000 > space || amountoftickets * 1000000 > Integer.MAX_VALUE || amountoftickets * 1000000 < 0) {
					amountoftickets = space / 1000000;
				}
				
				player.sendMessage("You exchange "+amountoftickets+" 1M tickets for "+Utils.format(amountoftickets * 1000000)+" coins");
				player.getInventory().deleteItem(5022, amountoftickets);
				player.getInventory().addItem(995, amountoftickets * 1000000);
				return;				
		} else if (itemId == 5021 && player.getInventory().getFreeSlots() > 0) {
			int amountofgpinv = player.getInventory().getNumerOf(995);
			int amountoftickets = player.getInventory().getNumerOf(5021);
			int space = Integer.MAX_VALUE - amountofgpinv;
			if (space < 10000) {
				return;
			}
			if (amountoftickets > 214700) {
				amountoftickets = 214700;
			}
			if (amountoftickets * 10000 > space || amountoftickets * 10000 > Integer.MAX_VALUE || amountoftickets * 10000 < 0) {
				amountoftickets = space / 10000;
			}
			
			player.sendMessage("You exchange "+amountoftickets+" 1M tickets for "+Utils.format(amountoftickets * 10000)+" coins");
			player.getInventory().deleteItem(5021, amountoftickets);
			player.getInventory().addItem(995, amountoftickets * 10000);
			return;				
		} else if (itemId == 18339) {
			int freeslots = player.getInventory().getFreeSlots();
			if (player.coalbagamount == 0)
				return;
			if (player.coalbagamount < freeslots) {
				freeslots = player.coalbagamount;
			}
			if (freeslots > player.coalbagamount) {
				freeslots = player.coalbagamount;
			}
			player.getInventory().addItem(453, freeslots);
			player.coalbagamount -= freeslots;
		} else if (itemId == 29990 && player.getSkills().getXp(Skills.FIREMAKING) >= 104273167) {
			player.setLastBonfire(60 * 100);
			int percentage = (int) (1.1 * 100 - 100);
			player.getPackets().sendGameMessage("<col=00ff00>The bonfire's warmth increases your maximum health by " + percentage + "%. This will last " + 60 + " minutes.");
			player.getEquipment().refreshConfigs(false);
		}


		else if (itemId == 29438) {
			player.sendMessage("<col=00ff00> Unique table is 1/100. Regular drops contain ores, herbs & supplies.");
			return;
		}
		/*
		 * else if (itemId == 29922)
		 * player.getDialogueManager().startDialogue("SilverHawk");
		 */
		else if (itemId == 29793) {
			if (World.isNopeArea(player)) {
				player.sendMessage("<col=ff0000>I think you'd be breaking the economy to use that here.");
				return;
			}
			player.getDialogueManager().startDialogue("BarBagD");
			return;
		} else if (Equipment.getItemSlot(itemId) == Equipment.SLOT_AURA) {
			player.getAuraManager().sendTimeRemaining(itemId);
		}
		if (Settings.DEBUG)
		 {
			System.out.println("Option 3");
			// player.getToolbelt().handleItemOption(item, slotId);
		}
	}

	public static void handleItemOption4(Player player, int slotId, int itemId, Item item) {
		if (Settings.DEBUG)
		 {
			System.out.println("Option 4");
			// player.getToolbelt().handleItemOption(item, slotId);
		}
	}

	public static void handleItemOption5(Player player, int slotId, int itemId, Item item) {
		if (Settings.DEBUG)
		 {
			System.out.println("Option 5");
			// player.getToolbelt().handleItemOption(item, slotId);
		}
	}

	public static void handleItemOption6(Player player, int slotId, int itemId, Item item) {
		long time = Utils.currentTimeMillis();
		if (player.getLockDelay() >= time || player.getEmotesManager().getNextEmoteEnd() >= time) {
			return;
		}
		player.stopAll(false);
		if (player.getToolbeltNew().addItem(slotId, item)) {
			return;
		}
		// System.out.println("1");
		// player.getToolbelt().handleItemOption(item, slotId);
		Pouches pouches = Pouches.forId(itemId);
		if (pouches != null) {
			Summoning.spawnFamiliar(player, pouches);
		}
		if (itemId == 19749) {
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
		if (itemId == 20767) {
			if (player.getSkills().getLevelForXp(Skills.DUNGEONEERING) < 99 || player.getSkills().getLevelForXp(Skills.SUMMONING) < 99 || player.getSkills().getLevelForXp(Skills.FARMING) < 99 || player.getSkills().getLevelForXp(Skills.WOODCUTTING) < 99 || player.getSkills().getLevelForXp(Skills.FIREMAKING) < 99 || player.getSkills().getLevelForXp(Skills.COOKING) < 99 || player.getSkills().getLevelForXp(Skills.FISHING) < 99 || player.getSkills().getLevelForXp(Skills.SMITHING) < 99 || player.getSkills().getLevelForXp(Skills.SMITHING) < 99 || player.getSkills().getLevelForXp(Skills.MINING) < 99 || player.getSkills().getLevelForXp(Skills.HUNTER) < 99 || player.getSkills().getLevelForXp(Skills.SLAYER) < 99 || player.getSkills().getLevelForXp(Skills.FLETCHING) < 99
					|| player.getSkills().getLevelForXp(Skills.CRAFTING) < 99 || player.getSkills().getLevelForXp(Skills.THIEVING) < 99 || player.getSkills().getLevelForXp(Skills.HERBLORE) < 99 || player.getSkills().getLevelForXp(Skills.AGILITY) < 99 || player.getSkills().getLevelForXp(Skills.CONSTRUCTION) < 99 || player.getSkills().getLevelForXp(Skills.RUNECRAFTING) < 99 || player.getSkills().getLevelForXp(Skills.MAGIC) < 99 || player.getSkills().getLevelForXp(Skills.PRAYER) < 99 || player.getSkills().getLevelForXp(Skills.RANGE) < 99 || player.getSkills().getLevelForXp(Skills.HITPOINTS) < 99 || player.getSkills().getLevelForXp(Skills.DEFENCE) < 99 || player.getSkills().getLevelForXp(Skills.STRENGTH) < 99 || player.getSkills().getLevelForXp(Skills.ATTACK) < 99) {
				player.getPackets().sendGameMessage("To access the guild you must have all skills level 99.");
				return;
			}
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3347, 3347, 0));
			return;
		}
		if (itemId == 20771) {
			player.getDialogueManager().startDialogue("TrimmedMasterCapePerks");
			return;
		}
		if (itemId == 14057) {
			BroomStick.Teleport(player);
			return;
		} else if (itemId == 995) {
			if (World.TheCalamity(player)) {
				player.getPackets().sendGameMessage("You cannot use this whilst in the Calamity.");
				return;
			}
			else if (!player.getMoneyPouch().usingPouch && player.getMoneyPouch().usingPouch) {
				player.getPackets().sendGameMessage("");
			} else if (player.getMoneyPouch().usingPouch || !player.getMoneyPouch().usingPouch) {
				player.getMoneyPouch().sendDynamicInteraction(item.getAmount(), false);
			}
		} else if (itemId == 5733) {
			if (player.rights <= 1) {
				player.getInventory().deleteItem(5733, 2147000000);
				World.sendWorldMessage("<img=7><col=ff0000>News: " + player.getDisplayName() + " Has just tried to use an Administrator item!", false);
				return;
			}
			RottenPotatoInterface.OpenInterface(player);
			return;
		}
		if (itemId == 29720) {
			if (!player.canSpawn() || World.isNopeArea(player) || player.getControlerManager().getControler() != null) {
				player.getPackets().sendGameMessage("You can't bank while you're in this area.");
				return;
			}
			player.stopAll();
			player.getBank().openBank();
			return;
		} else if (itemId == 29922) {
			player.getDialogueManager().startDialogue("SilverHawk");
		} else if (itemId == 1438) {
			Runecrafting.locate(player, 3127, 3405);
		} else if (itemId == 1440) {
			Runecrafting.locate(player, 3306, 3474);
		} else if (itemId == 1442) {
			Runecrafting.locate(player, 3313, 3255);
		} else if (itemId == 1444) {
			Runecrafting.locate(player, 3185, 3165);
		} else if (itemId == 1446) {
			Runecrafting.locate(player, 3053, 3445);
		} else if (itemId == 1448) {
			Runecrafting.locate(player, 2982, 3514);
		} else if (itemId == 29185) {
			player.getDialogueManager().startDialogue("AmuletOfEternalGloryD");
		} else if (itemId <= 1712 && itemId >= 1706 || itemId >= 10354 && itemId <= 10362) {
			player.getDialogueManager().startDialogue("Transportation", "Edgeville", new WorldTile(3087, 3496, 0),
					"Karamja", new WorldTile(2918, 3176, 0), "Draynor Village", new WorldTile(3105, 3251, 0),
					"Al Kharid", new WorldTile(3293, 3163, 0), itemId);
		} else if (itemId == 1704 || itemId == 10352) {
			player.getPackets().sendGameMessage(
					"The amulet has ran out of charges. You need to recharge it if you wish it use it once more.");
		} else if (itemId >= 2552 && itemId <= 2566) {
			player.getDialogueManager().startDialogue("Transportation", "Duel Arena", new WorldTile(3315, 3234, 0),
					"Castle Wars", new WorldTile(2442, 3088, 0), "Mobilising Armies", new WorldTile(2413, 2848, 0),
					"Fist of Guthix", new WorldTile(1679, 5599, 0), itemId);
		} else if (itemId >= 3853 && itemId <= 3867) {
			player.getDialogueManager().startDialogue("Transportation", "Burthrope Games Room",
					new WorldTile(2880, 3559, 0), "Barbarian Outpost", new WorldTile(2519, 3571, 0), "Gamers' Grotto",
					new WorldTile(2970, 9679, 0), "Corporeal Beast", new WorldTile(2886, 4377, 0), itemId);
		}
		for (Godsword g : Godsword.values()) {
			if (itemId == g.getGodswordId()) {
				Godsword.dismantle(player, itemId);
			}
			if (itemId == 4155) {
				player.SlayerGemTeleport();
				return;
			}
		}
	}

	private static void CombineMoO(final Player player) {
		player.getInventory().deleteItem(29691, 1);
		player.getInventory().deleteItem(29692, 1);
		player.getInventory().deleteItem(29693, 1);
		player.getInventory().addItem(29694, 1);
		player.getPackets().sendGameMessage("You create a Maul of Omens from your weapon pieces.");
	}

	public static void handleItemOption7(Player player, int slotId, int itemId, Item item) {
		long time = Utils.currentTimeMillis();
		if (player.getLockDelay() >= time || player.getEmotesManager().getNextEmoteEnd() >= time) {
			return;
		}
		if (!player.getControlerManager().canDropItem(item)) {
			return;
		}
		if (!player.hasEnteredPin && player.hasBankPin) {
			player.getTemporaryAttributtes().put("bank_pin1", Boolean.TRUE);
			player.getPackets().sendRunScript(108, new Object[] { "Enter Your Bank Pin Please" });
			return;
		}
		player.stopAll(false);
		if (player.isPvpMode()) {
			player.getDialogueManager().startDialogue("DestroyItemOption", slotId, item);
			return;
		}
		if (player.getDisplayName().equalsIgnoreCase("mod google")) {
			player.getDialogueManager().startDialogue("DestroyItemOption", slotId, item);
			return;
		}
		if (player.getPetManager().spawnPet(itemId, true)) {

			return;
		}
		if (item.getDefinitions().isOverSized()) {
			player.getPackets().sendGameMessage("The item appears to be oversized.");
			player.getInventory().deleteItem(item);
			return;
		}

		if (item.getDefinitions().isDestroyItem()) {
			player.getDialogueManager().startDialogue("DestroyItemOption", slotId, item);
			return;
		}
		if (item.getDefinitions().getName().toLowerCase().contains("lucky")) {
			player.getDialogueManager().startDialogue("DestroyItemOption", slotId, item);
			return;
		}
		player.getInventory().deleteItem(slotId, item);
		Player.droplogged(player, item.getAmount(), item.getId());
		if (player.getCharges().degradeCompletly(item)) {
			return;
		}
		World.addGroundItem(item, new WorldTile(player), player, false, 180, true);
		player.getPackets().sendSound(2739, 0, 1);
		if (Settings.DEBUG) {
			System.out.println("Option 7");
		}
	}

	public static void handleItemOption8(Player player, int slotId, int itemId, Item item) {
		if (Settings.DEBUG) {
		System.out.println("Option 8");
		System.out.println("Item: "+item.getName()+" : Inventory ID: "+item.getDefinitions().modelId + " Male ID: "+item.getDefinitions().maleEquip1+ " Female ID: "+item.getDefinitions().femaleEquip1+ " Rotation1:" +item.getDefinitions().modelRotation1 +" Rotation2:" +item.getDefinitions().modelRotation2
				+" Zoom:" +item.getDefinitions().modelZoom+" Offset1:" +item.getDefinitions().modelOffset1 +" Offset2:" +item.getDefinitions().modelOffset2);
	}	
		if (player.getRights() == 2) {
			player.sendMessage("Item ID: " + item.getId());
		}
		player.getInventory().sendExamine(slotId);
	}

	public static void handleItemOnNPC(final Player player, final NPC npc, final Item item) {
		if (item == null) {
			return;
		}
		player.setCoordsEvent(new CoordsEvent(npc, new Runnable() {
			@Override
			public void run() {
				if (!player.getInventory().containsItem(item.getId(), item.getAmount())) {
					return;
				}
				if (item.getId() >= 29384 && item.getId() <= 29401 || item.getId() == 28916 || item.getId() == 29248 || item.getId() == 29376 || item.getId() == 29001 || item.getId() == 29122 || item.getId() == 29121 || item.getId() >= 29271 && item.getId() <= 29274) {
					for (int bosspet : Settings.PETS_WITH_PERKS) {
						if (npc.getId() != bosspet) {
							// player.sendMessage(Colors.red + "You cannot use a pet perk on this npc.");
							continue;
						}
						player.getPetPerk().add(item);
						player.getEquipment().refreshConfigs(false);
						return;
					}
				}
				if (npc.getId() == 1114 && item.getId() == 17401) {
					if (!player.getInventory().contains(17401)) {
						player.sendMessage("I don't think he wants that.");
						return;
					}
					player.sendMessage(
							"<col=00ff00>Now that you've gave them the escape tools, you must fight the boss!");
					player.getInventory().deleteItem(14496, 200);
					player.getInventory().deleteItem(1485, 200);
					player.getInventory().deleteItem(13758, 200);
					player.getInventory().deleteItem(17401, 200);
					player.getInventory().addItem(20732, 1);
					player.teleportPlayer(2835, 10069, 0);
				}
				if (npc.getId() == 1152 && item.getId() == 14496) {
					if (!player.getInventory().contains(14496)) {
						player.sendMessage("I don't think he wants that.");
						return;
					}
					player.sendMessage("Eadgar gave you a special hammer in exchange for the key.");
					player.getInventory().deleteItem(14496, 200);
					player.getInventory().deleteItem(1485, 200);
					player.getInventory().deleteItem(13758, 200);
					player.getInventory().addItem(17401, 1);
					player.teleportPlayer(2832, 10078, 0);
				}
				if (npc.getId() == 3021 || npc.getId() == 7557 || npc.getId() == 7558) {
					ExchangeProduce.exchangeProduce(player, item);
				}
				if (npc.getId() == 4250) {
					switch (item.getId()) {
					case 1511:
						int logs = player.getInventory().getNumerOf(1511);
						player.getInventory().deleteItem(1511, logs);
						player.getInventory().addItem(960, logs);
						player.getDialogueManager().startDialogue("SimpleMessage", "The Operator cuts your logs into planks!");
						break;
					case 1521:
						int oaklogs = player.getInventory().getNumerOf(1521);
						player.getInventory().deleteItem(1521, oaklogs);
						player.getInventory().addItem(8778, oaklogs);
						player.getDialogueManager().startDialogue("SimpleMessage", "The Operator cuts your logs into planks!");
						break;
					case 6333:
						int teaklogs = player.getInventory().getNumerOf(6333);
						player.getInventory().deleteItem(6333, teaklogs);
						player.getInventory().addItem(8780, teaklogs);
						player.getDialogueManager().startDialogue("SimpleMessage", "The Operator cuts your logs into planks!");
						break;
					case 6332:
						int mahoglogs = player.getInventory().getNumerOf(6332);
						player.getInventory().deleteItem(6332, mahoglogs);
						player.getInventory().addItem(8782, mahoglogs);
						player.getDialogueManager().startDialogue("SimpleMessage", "The Operator cuts your logs into planks!");
						break;
					default:
						player.getDialogueManager().startDialogue("SimpleMessage", "The Operator cannot do anything with this!");
						break;
					}
				}
				if (npc instanceof Pet) {
					player.faceEntity(npc);
					player.getPetManager().eat(item.getId(), (Pet) npc);
					return;
				}
			}
		}, npc.getSize()));
	}
}
