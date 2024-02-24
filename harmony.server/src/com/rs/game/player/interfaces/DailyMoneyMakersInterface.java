package com.rs.game.player.interfaces;

import com.rs.Settings;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.game.player.actions.Summoning;
import com.rs.game.player.content.Magic;
import com.rs.game.player.content.pet.Pets;

/**
 * 
 * @author Mr_Joopz
 *
 */

public class DailyMoneyMakersInterface {


	public static void OpenInterface(Player player) {
		player.getInterfaceManager().sendInterface(3006);
		player.getPackets().sendIComponentText(3006, 73, "Rewards:");
		player.getPackets().sendIComponentText(3006, 26, "Daily Money Makers");
		player.dailymoneymakercomponent = 1;
		player.slayertelecomponent = 0;
		/**
		 * Right hand side tab
		 */
		player.getPackets().sendIComponentText(3006, 11, "Hill Giants");
		player.getPackets().sendIComponentText(3006, 34, "Kill 75 hill giants");
		player.getPackets().sendIComponentText(3006, 33, "Difficulty: Easy");
		player.getPackets().sendIComponentText(3006, 111, "");
		player.getPackets().sendIComponentText(3006, 116, "Progress: " + player.dailyhillgiants + "/75");
		player.getPackets().sendIComponentText(3006, 117, "Rewards:<br>1x Crystal key & 5M coins.");
		/**
		 * Left top
		 */
		player.getPackets().sendIComponentText(3006, 28, "Hill Giants");
		player.getPackets().sendIComponentText(3006, 99, "Barrows");
		player.getPackets().sendIComponentText(3006, 100, "Slayer");
		player.getPackets().sendIComponentText(3006, 101, "Farming");
		player.getPackets().sendIComponentText(3006, 102, "Potions");
		player.getPackets().sendIComponentText(3006, 103, "Clue Scrolls");
		player.getPackets().sendIComponentText(3006, 104, "Chambers of Xeric");
		player.getPackets().sendIComponentText(3006, 105, "Wildy Tasks");
		player.getPackets().sendIComponentText(3006, 106, "Training");
		player.getPackets().sendIComponentText(3006, 107, "Theatre of Blood");
		player.getPackets().sendIComponentText(3006, 108, "Runecoins");
		player.getPackets().sendIComponentText(3006, 109, "");
		player.getPackets().sendIComponentText(3006, 110, "");
		player.getPackets().sendIComponentText(3006, 118, "");
		player.getPackets().sendIComponentText(3006, 119, "");
		player.getPackets().sendIComponentText(3006, 120, "");
		player.getPackets().sendIComponentText(3006, 121, "");
		player.getPackets().sendIComponentText(3006, 122, "");
		player.getPackets().sendIComponentText(3006, 123, "");
		player.getPackets().sendIComponentText(3006, 124, "");
		player.getPackets().sendIComponentText(3006, 125, "");
		player.getPackets().sendIComponentText(3006, 126, "");
		player.getPackets().sendIComponentText(3006, 127, "");
		player.getPackets().sendIComponentText(3006, 128, "");
		player.getPackets().sendIComponentText(3006, 129, "");
		player.getPackets().sendIComponentText(3006, 130, "");
		player.getPackets().sendIComponentText(3006, 131, "");
		player.getPackets().sendIComponentText(3006, 132, "");
		player.getPackets().sendIComponentText(3006, 133, "");
		player.getPackets().sendIComponentText(3006, 134, "");
		player.getPackets().sendIComponentText(3006, 135, "");
		player.getPackets().sendIComponentText(3006, 136, "");
		/**
		 * Left bottom
		 */
		player.getDmm().getContainer().clear();
		player.getDmm().getContainer().add(new Item(989, 1));
		player.getDmm().getContainer().add(new Item(995, 5000000));
		player.getPackets().sendInterSetItemsOptionsScript(3006, 72, 100, 8, 3, "Examine");
		player.getPackets().sendUnlockIComponentOptionSlots(3006, 72, 0, 10, 0, 1, 2, 3);
		player.getDmm().getContainer().shift();
		player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
	}

	public static void HandleButtons(Player player, int componentId, int slotId) {
		// System.out.println("componentId " + componentId + "");
		Item item = player.getDmm().getContainer().get(slotId);
		switch (componentId) {
		case 72:
			player.sendMessage(item.getDefinitions().getExamine());
			break;
		case 31:
			HandleTeleport(player);
			break;
		case 28:
			OpenInterface(player);
			break;
		case 99:
			player.dailymoneymakercomponent = 2;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(3006, 11, "Barrows");
			player.getPackets().sendIComponentText(3006, 34, "Loot 3 barrows chests");
			player.getPackets().sendIComponentText(3006, 33, "Difficulty: Medium");
			player.getPackets().sendIComponentText(3006, 111, "");
			player.getPackets().sendIComponentText(3006, 116, "Progress: " + player.dailybarrowschest + "/3");
			player.getPackets().sendIComponentText(3006, 117, "Rewards:<br>1x PvM key [level 2] &<br>1x Mystery box.");
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(29541, 1));
			player.getDmm().getContainer().add(new Item(6199, 1));
			player.getPackets().sendInterSetItemsOptionsScript(3006, 72, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3006, 72, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 100:
			player.dailymoneymakercomponent = 3;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(3006, 11, "Slayer");
			player.getPackets().sendIComponentText(3006, 34, "Complete 2 slayer tasks");
			player.getPackets().sendIComponentText(3006, 33, "Difficulty: Medium");
			player.getPackets().sendIComponentText(3006, 111, "");
			player.getPackets().sendIComponentText(3006, 116, "Progress: " + player.dailyslayertask + "/2");
			player.getPackets().sendIComponentText(3006, 117, "Rewards:<br>1x Mystery box & 5M coins.");
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(6199, 1));
			player.getDmm().getContainer().add(new Item(995, 5000000));
			player.getPackets().sendInterSetItemsOptionsScript(3006, 72, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3006, 72, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 101:
			player.dailymoneymakercomponent = 4;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(3006, 11, "Farming");
			player.getPackets().sendIComponentText(3006, 34, "Harvest 10 herbs");
			player.getPackets().sendIComponentText(3006, 33, "Difficulty: Easy");
			player.getPackets().sendIComponentText(3006, 111, "");
			player.getPackets().sendIComponentText(3006, 116, "Progress: " + player.dailyfarming + "/10");
			player.getPackets().sendIComponentText(3006, 117, "Rewards:<br>10x Torstol seed &<br>1x Crystal key.");
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(5304, 10));
			player.getDmm().getContainer().add(new Item(989, 1));
			player.getPackets().sendInterSetItemsOptionsScript(3006, 72, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3006, 72, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 102:
			player.dailymoneymakercomponent = 5;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(3006, 11, "Potions");
			player.getPackets().sendIComponentText(3006, 34, "Make 150 potions");
			player.getPackets().sendIComponentText(3006, 33, "Difficulty: Easy");
			player.getPackets().sendIComponentText(3006, 111, "");
			player.getPackets().sendIComponentText(3006, 116, "Progress: " + player.dailypotions + "/150");
			player.getPackets().sendIComponentText(3006, 117, "Rewards:<br>15x Grimy ranarr, torstol,<br>lantadyme & 5M coins.");
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(207, 15));
			player.getDmm().getContainer().add(new Item(219, 15));
			player.getDmm().getContainer().add(new Item(2485, 15));
			player.getDmm().getContainer().add(new Item(995, 5000000));
			player.getPackets().sendInterSetItemsOptionsScript(3006, 72, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3006, 72, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 103:
			player.dailymoneymakercomponent = 6;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(3006, 11, "Clue Scrolls");
			player.getPackets().sendIComponentText(3006, 34, "Complete 5 clue scrolls");
			player.getPackets().sendIComponentText(3006, 33, "Difficulty: Medium");
			player.getPackets().sendIComponentText(3006, 111, "");
			player.getPackets().sendIComponentText(3006, 116, "Progress: " + player.dailycluescrolls + "/5");
			player.getPackets().sendIComponentText(3006, 117, "Rewards:<br>20M coins & Mystery chest.");
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(995, 20000000));
			player.getDmm().getContainer().add(new Item(29426, 1));
			player.getPackets().sendInterSetItemsOptionsScript(3006, 72, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3006, 72, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 104:
			player.dailymoneymakercomponent = 7;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(3006, 11, "Raids");
			player.getPackets().sendIComponentText(3006, 34, "Complete 2 Xeric raids");
			player.getPackets().sendIComponentText(3006, 33, "Difficulty: Hard");
			player.getPackets().sendIComponentText(3006, 111, "");
			player.getPackets().sendIComponentText(3006, 116, "Progress: " + player.dailyxericraids + "/2");
			player.getPackets().sendIComponentText(3006, 117, "Rewards:<br>2x Crystal key & Raids casket.");
			/**
			 * Left bottom
			 */

			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(989, 2));
			player.getDmm().getContainer().add(new Item(29438, 1));
			player.getPackets().sendInterSetItemsOptionsScript(3006, 72, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3006, 72, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 105:
			player.dailymoneymakercomponent = 8;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(3006, 11, "Wildy Tasks");
			player.getPackets().sendIComponentText(3006, 34, "Complete 3 Wildy Tasks");
			player.getPackets().sendIComponentText(3006, 33, "Difficulty: Medium");
			player.getPackets().sendIComponentText(3006, 111, "");
			player.getPackets().sendIComponentText(3006, 116, "Progress: " + player.dailywildytasks + "/3");
			player.getPackets().sendIComponentText(3006, 117, "Rewards:<br>2x Wilderness Casket & 5M coins.");
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(29369, 2));
			player.getDmm().getContainer().add(new Item(995, 5000000));
			player.getPackets().sendInterSetItemsOptionsScript(3006, 72, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3006, 72, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 106:
			player.dailymoneymakercomponent = 9;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(3006, 11, "Training");
			player.getPackets().sendIComponentText(3006, 34, "Kill 50 Noobs of Zamorak");
			player.getPackets().sendIComponentText(3006, 33, "Difficulty: Easy");
			player.getPackets().sendIComponentText(3006, 111, "");
			player.getPackets().sendIComponentText(3006, 116, "Progress: " + player.dailynoobzamorak + "/50");
			player.getPackets().sendIComponentText(3006, 117, "Rewards:<br>5x Starter key & 5M coins.");
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(29355, 5));
			player.getDmm().getContainer().add(new Item(995, 5000000));
			player.getPackets().sendInterSetItemsOptionsScript(3006, 72, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3006, 72, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 107:
			player.dailymoneymakercomponent = 10;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(3006, 11, "Raids");
			player.getPackets().sendIComponentText(3006, 34, "Complete 2 Theatre of Blood <br>raids");
			player.getPackets().sendIComponentText(3006, 33, "<br>Difficulty: Hard");
			player.getPackets().sendIComponentText(3006, 111, "");
			player.getPackets().sendIComponentText(3006, 116, "Progress: " + player.dailytheatreofblood + "/2");
			player.getPackets().sendIComponentText(3006, 117, "Rewards:<br>1x Theatre of Blood casket.");
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(29328, 1));
			player.getPackets().sendInterSetItemsOptionsScript(3006, 72, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3006, 72, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 108:
			player.dailymoneymakercomponent = 11;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(3006, 11, "Runecoins");
			player.getPackets().sendIComponentText(3006, 34, "Obtain 1,000 Runecoins");
			player.getPackets().sendIComponentText(3006, 33, "<br>Difficulty: Hard");
			player.getPackets().sendIComponentText(3006, 111, "");
			player.getPackets().sendIComponentText(3006, 116, "Progress: " + player.dailyrunecoins + "/2");
			player.getPackets().sendIComponentText(3006, 117, "Rewards:<br>1x Deathtouched dart <br>& 25M coins.");
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(25202, 1));
			player.getDmm().getContainer().add(new Item(995, 25000000));
			player.getPackets().sendInterSetItemsOptionsScript(3006, 72, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3006, 72, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		}

	}

	public static void HandleTeleport(Player player) {
		switch (player.dailymoneymakercomponent) {
		case 1:
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3116, 9845, 0));
			break;
		case 2:
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3565, 3289, 0));
			break;
		case 3:
			player.getInterfaceManager().closeInterfaceCustom();
			player.SlayerGemTeleport();
			break;
		case 4:
			player.getInterfaceManager().closeInterfaceCustom();
			player.getDialogueManager().startDialogue("FarmingTeleports");
			break;
		case 5:
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(Settings.RESPAWN_PLAYER_LOCATION));
			break;
		case 6:
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(Settings.RESPAWN_PLAYER_LOCATION));
			break;
		case 7:
			if (player.BossKills() < 100) {
				player.sendMessage("You much have at least 100 boss kills to access this raid.");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			if (player.getFamiliar() != null || player.getPet() != null || Summoning.hasPouch(player) || Pets.hasPet(player)) {
				player.sendMessage("You cannot bring a familiar to this boss.");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			if (!player.getInventory().containsItem(995, 1000000)) {
				player.sendMessage("Please have 1,000,000 coins in your inventory in order to pay a fee!");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			player.osrsraiddamagepoints = 0;
			player.getInventory().deleteItem(995, 1000000);
			Settings.GpSyncAmount += 1000000;
			player.sendMessage("<col=ff0000> It is highly advised that you choose your team carefully as using more than 5 people will leaving people behind when you progress.");
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3307, 5195, 0));
			player.getInterfaceManager().closeInterfaceCustom();
			break;
		case 8:
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(Settings.RESPAWN_PLAYER_LOCATION));
			break;
		case 9:
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2896, 2727, 0));
			break;
		case 10:
			if (player.BossKills() < 100) {
				player.sendMessage("You much have at least 100 boss kills to access this raid.");

				return;
			}
			if (player.getFamiliar() != null || player.getPet() != null || Summoning.hasPouch(player) || Pets.hasPet(player)) {
				player.sendMessage("You cannot bring a familiar to this boss.");
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);

				return;
			}
			if (!player.getInventory().containsItem(995, 2000000)) {
				player.sendMessage("Please have 2,000,000 coins in your inventory in order to pay a fee!");
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);

				return;
			}
			player.theatreofblooddamagepoints = 0;
			player.tobkilledboss1 = false;
			player.tobkilledboss2 = false;
			player.tobkilledboss3 = false;
			player.getInventory().deleteItem(995, 2000000);
			Settings.GpSyncAmount += 2000000;
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2578, 9650, 0));
			player.getInterfaceManager().closeChatBoxInterface();
			player.getInterfaceManager().closeOverlay(true);
			break;
		case 11:
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2862, 2579, 0));
			player.getInterfaceManager().closeChatBoxInterface();
			player.getInterfaceManager().closeOverlay(true);
			break;
		}
	}

	/**
	 * Container
	 */

	private static final int containerKey = 100;

}