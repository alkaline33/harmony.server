package com.rs.game.player.interfaces;

import com.rs.Settings;
import com.rs.game.World;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.utils.Colors;
import com.rs.utils.Utils;

/**
 * 
 * @author Mr_Joopz
 *
 */

public class ResearchTableInterfaceMain {

	private static final int containerKey = 100;
	private static int INTER = 3018;

	public static void OpenInterface(Player player) {
		player.getInterfaceManager().sendInterface(INTER);
		player.researchtablecomponent = 1;
		/**
		 * Right hand side tab
		 */
		player.getPackets().sendIComponentText(INTER, 60, "Chaotic Rapier (i)<br><br>Upgrade: +11 stab bonus & +19 strength bonus.<br><br>Success Rate: 100%");
		player.getSpareContainer().getContainer().clear();
		player.getSpareContainer().getContainer().add(new Item(29410, 1));
		player.getPackets().sendInterSetItemsOptionsScript(INTER, 55, 101, 8, 3, "Examine");
		player.getPackets().sendUnlockIComponentOptionSlots(INTER, 55, 0, 10, 0, 1, 2, 3);
		player.getSpareContainer().getContainer().shift();
		player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
		/**
		 * Left top
		 */
		player.getPackets().sendIComponentText(INTER, 21, "Chaotic Rapier (i)");// chaotic rapier + brutal whip
		player.getPackets().sendIComponentText(INTER, 22, "Chaotic Longsword (i)"); // chaotic long + abyssal tentacle
		player.getPackets().sendIComponentText(INTER, 23, "Chaotic Maul (i)"); // chaotic maul + armadyl godsword
		player.getPackets().sendIComponentText(INTER, 24, "Chaotic Crossbow (i)"); // chaotic crossbow + blowpipe
		player.getPackets().sendIComponentText(INTER, 25, "Chaotic Staff (i)"); // Chaotic staff + staff of the dead
		player.getPackets().sendIComponentText(INTER, 26, "Chaotic Spear (i)"); // chaotic spear + Jar of miasma
		player.getPackets().sendIComponentText(INTER, 27, "Farseer Kiteshield (i)");
		player.getPackets().sendIComponentText(INTER, 28, "Fighter Torso (i)"); // Fighter Torso - 10% chance
		player.getPackets().sendIComponentText(INTER, 29, "Fighter Hat (i)");// Fighter hat - 10% chance
		player.getPackets().sendIComponentText(INTER, 30, "Dragon Warhammer (i)");// dwh + wild warhammer - 50% chance
		player.getPackets().sendIComponentText(INTER, 31, "Community Key");
		player.getPackets().sendIComponentText(INTER, 32, "Tyrannical Ring (i)");
		player.getPackets().sendIComponentText(INTER, 33, "Pet Perk [10% drop rate]");
		player.getPackets().sendIComponentText(INTER, 34, "Treasonous Ring (i)");
		player.getPackets().sendIComponentText(INTER, 35, "Loop half of a key");
		player.getPackets().sendIComponentText(INTER, 36, "Ring of the gods (i)");
		//player.getPackets().sendIComponentText(INTER, 36, "Offhand Chaotic Rapier (i)");
		//player.getPackets().sendIComponentText(INTER, 37, "Offhand Chaotic Longsword (i)");
		//player.getPackets().sendIComponentText(INTER, 38, "Offhand Chaotic Crossbow (i)");
		player.getPackets().sendIComponentText(INTER, 37, "Elite Void");
		//player.getPackets().sendIComponentText(INTER, 38, "Corrupted Slayer Helmet");
		player.getPackets().sendIComponentText(INTER, 38, "Ring of Immense power");
		player.getPackets().sendIComponentText(INTER, 39, "Amulet of Immense power");
		player.getPackets().sendIComponentText(INTER, 40, "Drop Boost Emblem (i)");
		player.getPackets().sendIComponentText(INTER, 41, "Enhanced Excalibur");
		player.getPackets().sendIComponentText(INTER, 42, "Zaros Godsword");
		player.getPackets().sendIComponentText(INTER, 43, "Seren Godbow"); // reserved for staff of sliske
		player.getPackets().sendIComponentText(INTER, 44, "Staff of Sliske"); // reserved for seren godbow
		player.getPackets().sendIComponentText(INTER, 45, "Ancient Slayer Helmet");
		//player.getPackets().sendIComponentText(INTER, 46, "Amalgam Cristallum Boots");
		player.getPackets().sendIComponentText(INTER, 46, "Donator Gloves (i)");
		player.getPackets().sendIComponentText(INTER, 47, "Donator Boots (i)");
		player.getPackets().sendIComponentText(INTER, 48, "");
		player.getPackets().sendIComponentText(INTER, 49, "Pharaoh Nemes");
		player.getPackets().sendIComponentText(INTER, 50, "Pharaoh Tunic");
		player.getPackets().sendIComponentText(INTER, 51, "Pharaoh Shendyt");
		//player.getPackets().sendIComponentText(INTER, 94, "Donator Boots (i)");
		//player.getPackets().sendIComponentText(INTER, 95, "Apex Hood");
		//player.getPackets().sendIComponentText(INTER, 96, "Apex Robe Top");
		//player.getPackets().sendIComponentText(INTER, 97, "Apex Robe Bottom");
		//player.getPackets().sendIComponentText(INTER, 98, "Superior Apex Hood");
		player.getPackets().sendIComponentText(INTER, 94, "");
		player.getPackets().sendIComponentText(INTER, 95, "");
		player.getPackets().sendIComponentText(INTER, 96, "");
		player.getPackets().sendIComponentText(INTER, 97, "");
		player.getPackets().sendIComponentText(INTER, 98, "");
		player.getPackets().sendIComponentText(INTER, 99, "");
		player.getPackets().sendIComponentText(INTER, 100, "");
		/**
		 * Left bottom
		 */
		player.getDmm().getContainer().clear();
		player.getDmm().getContainer().add(new Item(995, 10000000));
		player.getDmm().getContainer().add(new Item(29869, 1));
		player.getDmm().getContainer().add(new Item(18349, 1));
		player.getPackets().sendInterSetItemsOptionsScript(INTER, 53, 100, 3, 3, "Examine");
		player.getPackets().sendUnlockIComponentOptionSlots(INTER, 53, 0, 10, 0, 1, 2, 3);
		player.getDmm().getContainer().shift();
		player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
	}

	public static void HandleButtons(Player player, int componentId, int slotId) {
		Item item = player.getDmm().getContainer().get(slotId);
		switch (componentId) {
		//case 55:
		case 53:
			player.sendMessage(item.getDefinitions().getExamine());
			break;
		case 17:
			HandleCreation(player);
			break;
		case 21:
			OpenInterface(player);
			break;
		case 22:
			player.researchtablecomponent = 2;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(INTER, 60, "Chaotic Longsword (i)<br><br>Upgrade: +6 slash bonus & +8 strength bonus.<br><br>Success Rate: 100%");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(29409, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 55, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 55, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(995, 10000000));
			player.getDmm().getContainer().add(new Item(18351, 1));
			player.getDmm().getContainer().add(new Item(29470, 1));
					player.getPackets().sendInterSetItemsOptionsScript(INTER, 53, 100, 3, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 53, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 23:
			player.researchtablecomponent = 3;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(INTER, 60, "Chaotic Maul (i)<br><br>Upgrade: +20 bonus & +10 strength bonus.<br><br>Success Rate: 100%");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(29408, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 55, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 55, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(995, 10000000));
			player.getDmm().getContainer().add(new Item(18353, 1));
			player.getDmm().getContainer().add(new Item(11694, 1));
					player.getPackets().sendInterSetItemsOptionsScript(INTER, 53, 100, 3, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 53, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 24:
			player.researchtablecomponent = 4;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(INTER, 60, "Chaotic Crossbow (i)<br><br>Upgrade: +30 range bonus.<br><br>Success Rate: 100%");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(29406, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 55, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 55, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(995, 10000000));
			player.getDmm().getContainer().add(new Item(18357, 1));
			player.getDmm().getContainer().add(new Item(25037, 1));
					player.getPackets().sendInterSetItemsOptionsScript(INTER, 53, 100, 3, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 53, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 25:
			player.researchtablecomponent = 5;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(INTER, 60, "Chaotic Staff (i)<br><br>Upgrade: +7 magic bonus.<br><br>Success Rate: 100%");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(29407, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 55, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 55, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(995, 10000000));
			player.getDmm().getContainer().add(new Item(18355, 1));
			player.getDmm().getContainer().add(new Item(29454, 1));
					player.getPackets().sendInterSetItemsOptionsScript(INTER, 53, 100, 3, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 53, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 26:
			player.researchtablecomponent = 12;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(INTER, 60, "Chaotic Spear (i)<br><br>Upgrade: +20 stab bonus & +20 strength bonus.<br><br>Success Rate: 100%");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(29357, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 55, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 55, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(995, 10000000));
			player.getDmm().getContainer().add(new Item(29512, 1));
			player.getDmm().getContainer().add(new Item(29443, 1));
					player.getPackets().sendInterSetItemsOptionsScript(INTER, 53, 100, 3, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 53, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 27:
			player.researchtablecomponent = 19;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(INTER, 60, "Farseer Kitshield (i)<br><br>Upgrade: +4 magic bonus & +5 prayer bonus. Removes defence stats.<br><br>Success Rate: 100%");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(29291, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 55, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 55, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(995, 10000000));
			player.getDmm().getContainer().add(new Item(18363, 1));
			player.getDmm().getContainer().add(new Item(29289, 1));
					player.getPackets().sendInterSetItemsOptionsScript(INTER, 53, 100, 3, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 53, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 28:
			player.researchtablecomponent = 6;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(INTER, 60, "Fighter torso (i)<br><br>Upgrade: +2 strength bonus.<br><br>Success Rate: <col=ff0000>10%</col>");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(29405, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 55, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 55, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(995, 1000000));
			player.getDmm().getContainer().add(new Item(10551, 1));
					player.getPackets().sendInterSetItemsOptionsScript(INTER, 53, 100, 3, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 53, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 29:
			player.researchtablecomponent = 7;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(INTER, 60, "Fight Hat (i)<br><br>Upgrade: +2 strength bonus.<br><br>Success Rate: <col=ff0000>10%</col>");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(29404, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 55, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 55, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(995, 1000000));
			player.getDmm().getContainer().add(new Item(10548, 1));
					player.getPackets().sendInterSetItemsOptionsScript(INTER, 53, 100, 3, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 53, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 30:
			player.researchtablecomponent = 8;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(INTER, 60, "Dragon Warhammer (i)<br><br>Upgrade: +5 crush bonus & +6 strength bonus.<br><br>Success Rate: 100%");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(29303, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 55, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 55, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(995, 100000000));
			player.getDmm().getContainer().add(new Item(29440, 1));
			player.getDmm().getContainer().add(new Item(29562, 1));
					player.getPackets().sendInterSetItemsOptionsScript(INTER, 53, 100, 3, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 53, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 31:
			player.researchtablecomponent = 9;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(INTER, 60, "Community Key<br><br>Success Rate: 25%");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(29654, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 55, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 55, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(995, 1000000));
			player.getDmm().getContainer().add(new Item(29655, 1));
					player.getPackets().sendInterSetItemsOptionsScript(INTER, 53, 100, 3, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 53, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 32:
			player.researchtablecomponent = 10;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(INTER, 60, "Tyrannical Ring (i)<br><br>Upgrade: +4 crush bonus, +4 crush defence & +4 strength bonus.<br><br>Success Rate: 100%");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(29381, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 55, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 55, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(995, 5000000));
			player.getDmm().getContainer().add(new Item(29382, 1));
			player.getDmm().getContainer().add(new Item(6737, 1));
					player.getPackets().sendInterSetItemsOptionsScript(INTER, 53, 100, 3, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 53, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 33:
			player.researchtablecomponent = 11;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(INTER, 60, "Pet Perk [10%]<br><br>Upgrade: single slot 10% drop rate boost.<br><br>Success Rate: 100%");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(29376, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 55, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 55, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(995, 50000000));
			player.getDmm().getContainer().add(new Item(29399, 2));
			player.getDmm().getContainer().add(new Item(29400, 1));
					player.getPackets().sendInterSetItemsOptionsScript(INTER, 53, 100, 3, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 53, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 34:
			player.researchtablecomponent = 13;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(INTER, 60, "Treasonous Ring (i)<br><br>Upgrade: +4 stab bonus, +4 stab defence & +4 strength bonus.<br><br>Success Rate: 100%");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(29315, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 55, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 55, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(995, 5000000));
			player.getDmm().getContainer().add(new Item(29316, 1));
			player.getDmm().getContainer().add(new Item(6737, 1));
					player.getPackets().sendInterSetItemsOptionsScript(INTER, 53, 100, 3, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 53, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 35:
			player.researchtablecomponent = 14;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(INTER, 60, "Loop half of a key<br><br>If successful this will turn your tooth into a loop.<br><br>Success Rate: <col=ff0000>50%</col>");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(987, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 55, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 55, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(985, 1));
					player.getPackets().sendInterSetItemsOptionsScript(INTER, 53, 100, 3, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 53, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 36:
			player.researchtablecomponent = 15;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(INTER, 60, "Ring of the gods (i)<br><br>Upgrade: +4 prayer bonus.<br><br>Success Rate: 100%");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(29301, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 55, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 55, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(995, 5000000));
			player.getDmm().getContainer().add(new Item(6735, 1));
			player.getDmm().getContainer().add(new Item(29302, 1));
					player.getPackets().sendInterSetItemsOptionsScript(INTER, 53, 100, 3, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 53, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		
		case 37:
			player.researchtablecomponent = 20;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(INTER, 60, "Elite Void<br><br>Upgrade: Elite version.<br><br>Success Rate: 100%");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(19785, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 55, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 55, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(995, 100000000));
			player.getDmm().getContainer().add(new Item(8839, 1));
			player.getDmm().getContainer().add(new Item(8840, 1));
					player.getPackets().sendInterSetItemsOptionsScript(INTER, 53, 100, 3, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 53, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 38:
			player.researchtablecomponent = 22;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(INTER, 60, "ROIP<br><br>Upgrade: +12 attack, +12 defence, +6 absorb, +12 strength, +12 prayer & +1% magic damage.<br><br>Success Rate: 100%");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(29268, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 55, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 55, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(995, 1000000000));
			player.getDmm().getContainer().add(new Item(29727, 1));
			player.getDmm().getContainer().add(new Item(15220, 2));
			player.getDmm().getContainer().add(new Item(15019, 2));
			player.getDmm().getContainer().add(new Item(15018, 2));
			player.getDmm().getContainer().add(new Item(15020, 2));
			player.getDmm().getContainer().add(new Item(2573, 20));
			player.getDmm().getContainer().add(new Item(29315, 1));
			player.getDmm().getContainer().add(new Item(29381, 1));
			player.getDmm().getContainer().add(new Item(29301, 1));
					player.getPackets().sendInterSetItemsOptionsScript(INTER, 53, 100, 4, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 53, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 39:
			player.researchtablecomponent = 23;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(INTER, 60, "AOIP<br><br>Upgrade: +14 attack, +14 defence, +2 absorb, +14 strength, +14 prayer & +7% magic damage.<br><br>Success Rate: 100%");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(29252, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 55, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 55, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(995, 1000000000));
			player.getDmm().getContainer().add(new Item(29249, 1));
			player.getDmm().getContainer().add(new Item(29731, 1));
			player.getDmm().getContainer().add(new Item(29724, 1));
			player.getDmm().getContainer().add(new Item(29590, 1));
			player.getDmm().getContainer().add(new Item(29565, 1));
			player.getDmm().getContainer().add(new Item(29564, 1));
			player.getDmm().getContainer().add(new Item(29563, 1));
			player.getDmm().getContainer().add(new Item(6586, 20));
					player.getPackets().sendInterSetItemsOptionsScript(INTER, 53, 100, 3, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 53, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 40:
			player.researchtablecomponent = 24;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(INTER, 60, "Drop Boost Emblem (i)<br><br>Upgrade: On task the emblem will provide 9% drop rate boost.<br><br>Success Rate: 100%");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(29216, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 55, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 55, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(995, 250000000));
			player.getDmm().getContainer().add(new Item(1720, 1));
			player.getDmm().getContainer().add(new Item(29325, 250));
			player.getDmm().getContainer().add(new Item(29954, 10));
					player.getPackets().sendInterSetItemsOptionsScript(INTER, 53, 100, 3, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 53, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 41:
			player.researchtablecomponent = 25;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(INTER, 60, "Enhanced Excalibur<br><br>Upgrade: Increased stats & healing special attack.<br><br>Success Rate: 100%");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(14632, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 55, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 55, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(995, 35000000));
			player.getDmm().getContainer().add(new Item(35, 1));
			player.getDmm().getContainer().add(new Item(19670, 1));
					player.getPackets().sendInterSetItemsOptionsScript(INTER, 53, 100, 3, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 53, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 42:
			player.researchtablecomponent = 26;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(INTER, 60, "Zaros Godsword.<br><br>Success Rate: 100%");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(29545, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 55, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 55, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(995, 500000000));
			player.getDmm().getContainer().add(new Item(29203, 1));
			player.getDmm().getContainer().add(new Item(29204, 1));
			player.getDmm().getContainer().add(new Item(29205, 1));
					player.getPackets().sendInterSetItemsOptionsScript(INTER, 53, 100, 3, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 53, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 43:
			player.researchtablecomponent = 27;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(INTER, 60, "Seren Godbow.<br><br>Success Rate: 100%");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(29547, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 55, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 55, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(995, 500000000));
			player.getDmm().getContainer().add(new Item(29183, 1));
			player.getDmm().getContainer().add(new Item(29432, 1));
			player.getDmm().getContainer().add(new Item(29255, 1));
			player.getDmm().getContainer().add(new Item(29256, 5000));
					player.getPackets().sendInterSetItemsOptionsScript(INTER, 53, 100, 3, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 53, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 44:
			player.researchtablecomponent = 28;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(INTER, 60, "Staff of Sliske.<br><br>Success Rate: 100%");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(29546, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 55, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 55, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(995, 500000000));
			player.getDmm().getContainer().add(new Item(29127, 1));
			player.getDmm().getContainer().add(new Item(29126, 1));
			player.getDmm().getContainer().add(new Item(29125, 1));
					player.getPackets().sendInterSetItemsOptionsScript(INTER, 53, 100, 3, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 53, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 45:
			player.researchtablecomponent = 29;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(INTER, 60, "Ancient Slayer Helmet<br><br>Upgrade: +2.5% Damage/Accuracy, giving a total of 20%.<br><br>Success Rate: 100%");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(29103, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 55, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 55, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(15497, 1));
			player.getDmm().getContainer().add(new Item(29367, 1));
			player.getDmm().getContainer().add(new Item(29325, 250));
					player.getPackets().sendInterSetItemsOptionsScript(INTER, 53, 100, 3, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 53, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 46:
			player.researchtablecomponent = 31;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(INTER, 60, "Donator Gloves (i)<br><br>Upgrade: +3 Attack & Defence bonuses & +2% Magic Damage.<br><br>Success Rate: 100%");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(28981, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 55, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 55, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(28982, 1));
			player.getDmm().getContainer().add(new Item(29925, 1));
			player.getDmm().getContainer().add(new Item(29370, 10000));
					player.getPackets().sendInterSetItemsOptionsScript(INTER, 53, 100, 3, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 53, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 47:
			player.researchtablecomponent = 32;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(INTER, 60, "Donator Boots (i)<br><br>Upgrade: +3 Attack & Defence bonuses & +5 Prayer.<br><br>Success Rate: 100%");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(28980, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 55, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 55, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(28982, 1));
			player.getDmm().getContainer().add(new Item(29788, 1));
			player.getDmm().getContainer().add(new Item(29370, 10000));
					player.getPackets().sendInterSetItemsOptionsScript(INTER, 53, 100, 3, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 53, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 49:
			player.researchtablecomponent = 33;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(INTER, 60, "Pharaoh Nemes<br><br>Upgrade: Level 92 hybrid hood.<br><br>Success Rate: 100%");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(29603, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 55, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 55, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(28994, 1));
			player.getDmm().getContainer().add(new Item(28997, 1));
			player.getDmm().getContainer().add(new Item(29000, 1));
			player.getDmm().getContainer().add(new Item(28975, 3));//seals
			player.getDmm().getContainer().add(new Item(29940, 1));
			player.getDmm().getContainer().add(new Item(29890, 1));
			player.getDmm().getContainer().add(new Item(29846, 1));
			player.getDmm().getContainer().add(new Item(995, 1000000000));
					player.getPackets().sendInterSetItemsOptionsScript(INTER, 53, 100, 3, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 53, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 50:
			player.researchtablecomponent = 34;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(INTER, 60, "Pharaoh Tunic<br><br>Upgrade: Level 92 hybrid top.<br><br>Success Rate: 100%");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(29605, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 55, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 55, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(28993, 1));
			player.getDmm().getContainer().add(new Item(28996, 1));
			player.getDmm().getContainer().add(new Item(28999, 1));
			player.getDmm().getContainer().add(new Item(28975, 3));//seals
			player.getDmm().getContainer().add(new Item(29941, 1));
			player.getDmm().getContainer().add(new Item(29889, 1));
			player.getDmm().getContainer().add(new Item(29844, 1));
			player.getDmm().getContainer().add(new Item(995, 1000000000));
					player.getPackets().sendInterSetItemsOptionsScript(INTER, 53, 100, 3, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 53, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 51:
			player.researchtablecomponent = 35;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(INTER, 60, "Pharaoh Shendyt<br><br>Upgrade: Level 92 hybrid bottoms.<br><br>Success Rate: 100%");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(29604, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 55, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 55, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(28992, 1));
			player.getDmm().getContainer().add(new Item(28995, 1));
			player.getDmm().getContainer().add(new Item(28998, 1));
			player.getDmm().getContainer().add(new Item(28975, 3));//seals
			player.getDmm().getContainer().add(new Item(29845, 1));
			player.getDmm().getContainer().add(new Item(29888, 1));
			player.getDmm().getContainer().add(new Item(29939, 1));
			player.getDmm().getContainer().add(new Item(995, 1000000000));
					player.getPackets().sendInterSetItemsOptionsScript(INTER, 53, 100, 3, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 53, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
			
		/*case 51:
			player.researchtablecomponent = 36;
			/**
			 * Right hand side tab
			 */
			/*player.getPackets().sendIComponentText(INTER, 60, "Superior Apex Hood<br><br>Upgrade: BIS helmet, +66 hitpoints.<br><br>Success Rate: 100%");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(28928, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 55, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 55, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(29940, 1));
			player.getDmm().getContainer().add(new Item(29890, 1));
			player.getDmm().getContainer().add(new Item(29846, 1));
			player.getDmm().getContainer().add(new Item(28977, 1));
			player.getDmm().getContainer().add(new Item(995, 100000000));
					player.getPackets().sendInterSetItemsOptionsScript(INTER, 53, 100, 3, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 53, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 99:
			player.researchtablecomponent = 37;
			player.getPackets().sendIComponentText(INTER, 60, "Superior Apex Robe Top<br><br>Upgrade: BIS top, +200 hitpoints.<br><br>Success Rate: 100%");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(28929, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 55, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 55, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(29941, 1));
			player.getDmm().getContainer().add(new Item(29889, 1));
			player.getDmm().getContainer().add(new Item(29844, 1));		
			player.getDmm().getContainer().add(new Item(28978, 1));
			player.getDmm().getContainer().add(new Item(995, 100000000));
					player.getPackets().sendInterSetItemsOptionsScript(INTER, 53, 100, 3, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 53, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 100:
			player.researchtablecomponent = 38;
			player.getPackets().sendIComponentText(INTER, 60, "Superior Apex Robe Bottoms<br><br>Upgrade: BIS bottoms, +134 hitpoints.<br><br>Success Rate: 100%");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(28927, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 55, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 55, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(29845, 1));
			player.getDmm().getContainer().add(new Item(29888, 1));
			player.getDmm().getContainer().add(new Item(29939, 1));		
			player.getDmm().getContainer().add(new Item(28976, 1));
			player.getDmm().getContainer().add(new Item(995, 100000000));
					player.getPackets().sendInterSetItemsOptionsScript(INTER, 53, 100, 3, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 53, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;*/
		}
		


	}

	public static void HandleCreation(Player player) {
		switch (player.researchtablecomponent) {
		case 1:
			if (player.getMoneyPouch().getCoinAmount() < 10000000) {
				player.sendMessage(Colors.red + "You must have 10M coins in your money pouch to pay the fee!");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			if (!player.getInventory().contains(18349) || !player.getInventory().contains(29869)) {
				player.sendMessage(Colors.red + "You do not have the required items to research this item!");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			player.coinamount -= 10000000;
			Settings.GpSyncAmount += 10000000;
			player.refreshMoneyPouch();
			player.getInventory().deleteItem(18349, 1);
			player.getInventory().deleteItem(29869, 1);
			player.getInventory().addItem(29410, 1);
			World.sendIconWorldMessage(Colors.rcyan + "" + player.getDisplayName() + " has just created a Chaotic Rapier (i)!", false);
			if (player.chaoticupgraded != true)
				player.chaoticupgraded = true;
			break;
		case 2:
			if (player.getMoneyPouch().getCoinAmount() < 10000000) {
				player.sendMessage(Colors.red + "You must have 10M coins in your money pouch to pay the fee!");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			if (!player.getInventory().contains(18351) || !player.getInventory().contains(29470)) {
				player.sendMessage(Colors.red + "You do not have the required items to research this item!");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			player.coinamount -= 10000000;
			Settings.GpSyncAmount += 10000000;
			player.refreshMoneyPouch();
			player.getInventory().deleteItem(29470, 1);
			player.getInventory().deleteItem(18351, 1);
			player.getInventory().addItem(29409, 1);
			World.sendIconWorldMessage(Colors.rcyan + "" + player.getDisplayName() + " has just created a Chaotic Longsword (i)!", false);
			if (player.chaoticupgraded != true)
				player.chaoticupgraded = true;
			break;
		case 3:
			if (player.getMoneyPouch().getCoinAmount() < 10000000) {
				player.sendMessage(Colors.red + "You must have 10M coins in your money pouch to pay the fee!");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			if (!player.getInventory().contains(18353) || !player.getInventory().contains(11694)) {
				player.sendMessage(Colors.red + "You do not have the required items to research this item!");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			player.coinamount -= 10000000;
			Settings.GpSyncAmount += 10000000;
			player.refreshMoneyPouch();
			player.getInventory().deleteItem(11694, 1);
			player.getInventory().deleteItem(18353, 1);
			player.getInventory().addItem(29408, 1);
			World.sendIconWorldMessage(Colors.rcyan + "" + player.getDisplayName() + " has just created a Chaotic Maul (i)!", false);
			if (player.chaoticupgraded != true)
				player.chaoticupgraded = true;
			break;
		case 4:
			if (player.getMoneyPouch().getCoinAmount() < 10000000) {
				player.sendMessage(Colors.red + "You must have 10M coins in your money pouch to pay the fee!");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			if (!player.getInventory().contains(18357) || !player.getInventory().contains(25037)) {
				player.sendMessage(Colors.red + "You do not have the required items to research this item!");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			player.coinamount -= 10000000;
			Settings.GpSyncAmount += 10000000;
			player.refreshMoneyPouch();
			player.getInventory().deleteItem(18357, 1);
			player.getInventory().deleteItem(25037, 1);
			player.getInventory().addItem(29406, 1);
			World.sendIconWorldMessage(Colors.rcyan + "" + player.getDisplayName() + " has just created a Chaotic Crossbow (i)!", false);
			if (player.chaoticupgraded != true)
				player.chaoticupgraded = true;
			break;
		case 5:
			if (player.getMoneyPouch().getCoinAmount() < 10000000) {
				player.sendMessage(Colors.red + "You must have 10M coins in your money pouch to pay the fee!");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			if (!player.getInventory().contains(18355) || !player.getInventory().contains(29454)) {
				player.sendMessage(Colors.red + "You do not have the required items to research this item!");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			player.coinamount -= 10000000;
			Settings.GpSyncAmount += 10000000;
			player.refreshMoneyPouch();
			player.getInventory().deleteItem(18355, 1);
			player.getInventory().deleteItem(29454, 1);
			player.getInventory().addItem(29407, 1);
			World.sendIconWorldMessage(Colors.rcyan + "" + player.getDisplayName() + " has just created a Chaotic Staff (i)!", false);
			if (player.chaoticupgraded != true)
				player.chaoticupgraded = true;
			break;
		case 6:
			if (player.getMoneyPouch().getCoinAmount() < 1000000) {
				player.sendMessage(Colors.red + "You must have 1M coins in your money pouch to pay the fee!");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			if (!player.getInventory().contains(10551)) {
				player.sendMessage(Colors.red + "You do not have the required items to research this item!");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			player.coinamount -= 1000000;
			Settings.GpSyncAmount += 1000000;
			player.refreshMoneyPouch();
			player.getInventory().deleteItem(10551, 1);
			if (Utils.random(0, 10) == 0) {
				player.getInventory().addItem(29405, 1);
				World.sendIconWorldMessage(Colors.rcyan + "" + player.getDisplayName() + " has just created a Fighter Torso (i)!", false);
			} else {
				player.sendMessage(Colors.red + "You failed to research a Fight Torso (i)!");
			}
			break;
		case 7:
			if (player.getMoneyPouch().getCoinAmount() < 1000000) {
				player.sendMessage(Colors.red + "You must have 1M coins in your money pouch to pay the fee!");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			if (!player.getInventory().contains(10548)) {
				player.sendMessage(Colors.red + "You do not have the required items to research this item!");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			player.coinamount -= 1000000;
			Settings.GpSyncAmount += 1000000;
			player.refreshMoneyPouch();
			player.getInventory().deleteItem(10548, 1);
			if (Utils.random(0, 10) == 0) {
				player.getInventory().addItem(29404, 1);
				World.sendIconWorldMessage(Colors.rcyan + "" + player.getDisplayName() + " has just created a Fighter Hat (i)!", false);
			} else {
				player.sendMessage(Colors.red + "You failed to research a Fight Hat (i)!");
			}
			break;
		case 8:
			if (player.getMoneyPouch().getCoinAmount() < 100000000) {
				player.sendMessage(Colors.red + "You must have 100M coins in your money pouch to pay the fee!");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			if (!player.getInventory().contains(29440) || !player.getInventory().contains(29562)) {
				player.sendMessage(Colors.red + "You do not have the required items to research this item!");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			player.coinamount -= 100000000;
			Settings.GpSyncAmount += 100000000;
			player.refreshMoneyPouch();
			player.getInventory().deleteItem(29440, 1);
			player.getInventory().deleteItem(29562, 1);
			player.getInventory().addItem(29303, 1);
			World.sendIconWorldMessage(Colors.rcyan + "" + player.getDisplayName() + " has just created a Dragon Warhammer (i)!", false);
			break;
		case 9:
			if (player.getMoneyPouch().getCoinAmount() < 1000000) {
				player.sendMessage(Colors.red + "You must have 1M coins in your money pouch to pay the fee!");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			if (!player.getInventory().contains(29655)) {
				player.sendMessage(Colors.red + "You do not have the required items to research this item!");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			player.coinamount -= 1000000;
			Settings.GpSyncAmount += 1000000;
			player.refreshMoneyPouch();
			player.getInventory().deleteItem(29655, 1);
			if (Utils.random(0, 3) == 0) {
				player.getInventory().addItem(29654, 1);
				World.sendIconWorldMessage(Colors.rcyan + "" + player.getDisplayName() + " has just created a Community key!", false);
			} else {
				player.sendMessage(Colors.red + "You failed to research a Community key!");
			}
			break;
		case 10:
			if (player.getMoneyPouch().getCoinAmount() < 5000000) {
				player.sendMessage(Colors.red + "You must have 5M coins in your money pouch to pay the fee!");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			if (!player.getInventory().contains(6737) || !player.getInventory().contains(29382)) {
				player.sendMessage(Colors.red + "You do not have the required items to research this item!");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			player.coinamount -= 5000000;
			Settings.GpSyncAmount += 5000000;
			player.refreshMoneyPouch();
			player.getInventory().deleteItem(29382, 1);
			player.getInventory().deleteItem(6737, 1);
			player.getInventory().addItem(29381, 1);
			World.sendIconWorldMessage(Colors.rcyan + "" + player.getDisplayName() + " has just created a Tyrannical Ring (i)!", false);
			break;
		case 11:
			if (player.getMoneyPouch().getCoinAmount() < 50000000) {
				player.sendMessage(Colors.red + "You must have 50M coins in your money pouch to pay the fee!");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			if (!player.getInventory().containsItem(29399, 2) || !player.getInventory().contains(29400)) {
				player.sendMessage(Colors.red + "You do not have the required items to research this item!");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			player.coinamount -= 50000000;
			Settings.GpSyncAmount += 50000000;
			player.refreshMoneyPouch();
			player.getInventory().deleteItem(29399, 2);
			player.getInventory().deleteItem(29400, 1);
			player.getInventory().addItem(29376, 1);
			World.sendIconWorldMessage(Colors.rcyan + "" + player.getDisplayName() + " has just created a Pet Perk [10% drop rate]!", false);
			break;
		case 12:
			if (player.getMoneyPouch().getCoinAmount() < 10000000) {
				player.sendMessage(Colors.red + "You must have 10M coins in your money pouch to pay the fee!");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			if (!player.getInventory().contains(29512) || !player.getInventory().contains(29443)) {
				player.sendMessage(Colors.red + "You do not have the required items to research this item!");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			player.coinamount -= 10000000;
			Settings.GpSyncAmount += 10000000;
			player.refreshMoneyPouch();
			player.getInventory().deleteItem(29443, 1);
			player.getInventory().deleteItem(29512, 1);
			player.getInventory().addItem(29357, 1);
			World.sendIconWorldMessage(Colors.rcyan + "" + player.getDisplayName() + " has just created a Chaotic Spear (i)!", false);
			if (player.chaoticupgraded != true)
				player.chaoticupgraded = true;
			break;
		case 13:
			if (player.getMoneyPouch().getCoinAmount() < 5000000) {
				player.sendMessage(Colors.red + "You must have 5M coins in your money pouch to pay the fee!");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			if (!player.getInventory().contains(6737) || !player.getInventory().contains(29316)) {
				player.sendMessage(Colors.red + "You do not have the required items to research this item!");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			player.coinamount -= 5000000;
			Settings.GpSyncAmount += 5000000;
			player.refreshMoneyPouch();
			player.getInventory().deleteItem(29316, 1);
			player.getInventory().deleteItem(6737, 1);
			player.getInventory().addItem(29315, 1);
			World.sendIconWorldMessage(Colors.rcyan + "" + player.getDisplayName() + " has just created a Treasonous Ring (i)!", false);
			break;
		case 14:
			if (!player.getInventory().contains(985)) {
				player.sendMessage(Colors.red + "You do not have the required items to research this item!");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			player.getInventory().deleteItem(985, 1);
			if (Utils.random(0, 2) == 0) {
				player.getInventory().addItem(987, 1);
				// World.sendIconWorldMessage(Colors.rcyan + "" + player.getDisplayName() + "
				// has just created a Fighter Hat (i)!", false);
			} else {
				player.sendMessage(Colors.red + "You failed to research a Loop half of a key!");
			}
			break;
		case 15:
			if (player.getMoneyPouch().getCoinAmount() < 5000000) {
				player.sendMessage(Colors.red + "You must have 5M coins in your money pouch to pay the fee!");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			if (!player.getInventory().contains(6735) || !player.getInventory().contains(29302)) {
				player.sendMessage(Colors.red + "You do not have the required items to research this item!");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			player.coinamount -= 5000000;
			Settings.GpSyncAmount += 5000000;
			player.refreshMoneyPouch();
			player.getInventory().deleteItem(29302, 1);
			player.getInventory().deleteItem(6735, 1);
			player.getInventory().addItem(29301, 1);
			World.sendIconWorldMessage(Colors.rcyan + "" + player.getDisplayName() + " has just created a Ring of the gods (i)!", false);
			break;
		case 16:
			if (player.getMoneyPouch().getCoinAmount() < 10000000) {
				player.sendMessage(Colors.red + "You must have 10M coins in your money pouch to pay the fee!");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			if (!player.getInventory().contains(29290) || !player.getInventory().contains(29517)) {
				player.sendMessage(Colors.red + "You do not have the required items to research this item!");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			player.coinamount -= 10000000;
			Settings.GpSyncAmount += 10000000;
			player.refreshMoneyPouch();
			player.getInventory().deleteItem(29290, 1);
			player.getInventory().deleteItem(29517, 1);
			player.getInventory().addItem(29294, 1);
			World.sendIconWorldMessage(Colors.rcyan + "" + player.getDisplayName() + " has just created an Offhand Chaotic Rapier (i)!", false);
			break;
		case 17:
			if (player.getMoneyPouch().getCoinAmount() < 10000000) {
				player.sendMessage(Colors.red + "You must have 10M coins in your money pouch to pay the fee!");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			if (!player.getInventory().contains(29290) || !player.getInventory().contains(29516)) {
				player.sendMessage(Colors.red + "You do not have the required items to research this item!");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			player.coinamount -= 10000000;
			Settings.GpSyncAmount += 10000000;
			player.refreshMoneyPouch();
			player.getInventory().deleteItem(29290, 1);
			player.getInventory().deleteItem(29516, 1);
			player.getInventory().addItem(29293, 1);
			World.sendIconWorldMessage(Colors.rcyan + "" + player.getDisplayName() + " has just created an Offhand Chaotic Longsword (i)!", false);
			if (player.chaoticupgraded != true)
				player.chaoticupgraded = true;
			break;
		case 18:
			if (player.getMoneyPouch().getCoinAmount() < 10000000) {
				player.sendMessage(Colors.red + "You must have 10M coins in your money pouch to pay the fee!");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			if (!player.getInventory().contains(29288) || !player.getInventory().contains(29515)) {
				player.sendMessage(Colors.red + "You do not have the required items to research this item!");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			player.coinamount -= 10000000;
			Settings.GpSyncAmount += 10000000;
			player.refreshMoneyPouch();
			player.getInventory().deleteItem(29288, 1);
			player.getInventory().deleteItem(29515, 1);
			player.getInventory().addItem(29292, 1);
			World.sendIconWorldMessage(Colors.rcyan + "" + player.getDisplayName() + " has just created an Offhand Chaotic Crossbow (i)!", false);
			if (player.chaoticupgraded != true)
				player.chaoticupgraded = true;
			break;
		case 19:
			if (player.getMoneyPouch().getCoinAmount() < 10000000) {
				player.sendMessage(Colors.red + "You must have 10M coins in your money pouch to pay the fee!");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			if (!player.getInventory().contains(29289) || !player.getInventory().contains(18363)) {
				player.sendMessage(Colors.red + "You do not have the required items to research this item!");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			player.coinamount -= 10000000;
			Settings.GpSyncAmount += 10000000;
			player.refreshMoneyPouch();
			player.getInventory().deleteItem(29289, 1);
			player.getInventory().deleteItem(18363, 1);
			player.getInventory().addItem(29291, 1);
			World.sendIconWorldMessage(Colors.rcyan + "" + player.getDisplayName() + " has just created a Farseer Kiteshield (i)!", false);
			if (player.chaoticupgraded != true)
				player.chaoticupgraded = true;
			break;
		case 20:
			if (player.getMoneyPouch().getCoinAmount() < 100000000) {
				player.sendMessage(Colors.red + "You must have 100M coins in your money pouch to pay the fee!");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			if (!player.getInventory().contains(8839) || !player.getInventory().contains(8840)) {
				player.sendMessage(Colors.red + "You do not have the required items to research this item!");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			player.coinamount -= 100000000;
			Settings.GpSyncAmount += 100000000;
			player.refreshMoneyPouch();
			player.getInventory().deleteItem(8839, 1);
			player.getInventory().deleteItem(8840, 1);
			player.getInventory().addItem(19785, 1);
			player.getInventory().addItem(19786, 1);
			World.sendIconWorldMessage(Colors.rcyan + "" + player.getDisplayName() + " has just created an Elite void set!", false);
			if (player.elitevoidmade != true)
				player.elitevoidmade = true;
			break;
		case 21:
			if (player.getMoneyPouch().getCoinAmount() < 10000000) {
				player.sendMessage(Colors.red + "You must have 10M coins in your money pouch to pay the fee!");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			if (!player.getInventory().contains(29367) || !player.getInventory().contains(15497)) {
				player.sendMessage(Colors.red + "You do not have the required items to research this item!");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			if (player.getSlayerPoints() < 500) {
				player.sendMessage(Colors.red + "You need 500 slayer points to do this!");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			player.coinamount -= 10000000;
			player.slayerPoints -= 500;
			Settings.GpSyncAmount += 10000000;
			player.refreshMoneyPouch();
			player.getInventory().deleteItem(29367, 1);
			player.getInventory().deleteItem(15497, 1);
			player.getInventory().addItem(29275, 1);
			World.sendIconWorldMessage(Colors.rcyan + "" + player.getDisplayName() + " has just created a Corrupted slayer helmet!", false);
			break;
		case 22:
			if (player.getMoneyPouch().getCoinAmount() < 1000000000) {
				player.sendMessage(Colors.red + "You must have 1000M coins in your money pouch to pay the fee!");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			if (!player.getInventory().contains(29727) || !player.getInventory().contains(29315) || !player.getInventory().contains(29381) || !player.getInventory().contains(29301) || !player.getInventory().containsItem(2573, 20) || !player.getInventory().containsItem(15020, 2) || !player.getInventory().containsItem(15019, 2) || !player.getInventory().containsItem(15018, 2) || !player.getInventory().containsItem(15220, 2)) {
				player.sendMessage(Colors.red + "You do not have the required items to research this item!");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			player.coinamount -= 1000000000;
			Settings.GpSyncAmount += 1000000000;
			player.refreshMoneyPouch();
			player.getInventory().deleteItem(29727, 1);
			player.getInventory().deleteItem(29315, 1);
			player.getInventory().deleteItem(29381, 1);
			player.getInventory().deleteItem(29301, 1);
			player.getInventory().deleteItem(2573, 20);
			player.getInventory().deleteItem(15020, 2);
			player.getInventory().deleteItem(15019, 2);
			player.getInventory().deleteItem(15018, 2);
			player.getInventory().deleteItem(15220, 2);
			player.getInventory().addItem(29268, 1);
			World.sendIconWorldMessage(Colors.rcyan + "" + player.getDisplayName() + " has just created the Ring of Immense power!", false);
			break;
		case 23:
			if (player.getMoneyPouch().getCoinAmount() < 1000000000) {
				player.sendMessage(Colors.red + "You must have 1000M coins in your money pouch to pay the fee!");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			if (!player.getInventory().contains(29249) || !player.getInventory().contains(29731) || !player.getInventory().contains(29724) || !player.getInventory().contains(29590) || !player.getInventory().contains(29565) || !player.getInventory().contains(29563) || !player.getInventory().contains(29564) || !player.getInventory().containsItem(6586, 20)) {
				player.sendMessage(Colors.red + "You do not have the required items to research this item!");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			player.coinamount -= 1000000000;
			Settings.GpSyncAmount += 1000000000;
			player.refreshMoneyPouch();
			player.getInventory().deleteItem(29249, 1);
			player.getInventory().deleteItem(29731, 1);
			player.getInventory().deleteItem(29724, 1);
			player.getInventory().deleteItem(29590, 1);
			player.getInventory().deleteItem(29565, 1);
			player.getInventory().deleteItem(6586, 20);
			player.getInventory().deleteItem(29564, 2);
			player.getInventory().deleteItem(29563, 2);
			player.getInventory().addItem(29252, 1);
			World.sendIconWorldMessage(Colors.rcyan + "" + player.getDisplayName() + " has just created the Amulet of Immense power!", false);
			break;
		case 24:
			if (player.getMoneyPouch().getCoinAmount() < 250000000) {
				player.sendMessage(Colors.red + "You must have 250M coins in your money pouch to pay the fee!");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			if (!player.getInventory().contains(1720) || !player.getInventory().containsItem(29325, 250) || !player.getInventory().containsItem(29954, 10)) {
				player.sendMessage(Colors.red + "You do not have the required items to research this item!");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			player.coinamount -= 250000000;
			Settings.GpSyncAmount += 250000000;
			player.refreshMoneyPouch();
			player.getInventory().deleteItem(1720, 1);
			player.getInventory().deleteItem(29325, 250);
			player.getInventory().deleteItem(29954, 10);
			player.getInventory().addItem(29216, 1);
			World.sendIconWorldMessage(Colors.rcyan + "" + player.getDisplayName() + " has just created the Drop boost emblem (i)!", false);
			player.dropboostimade = true;
			break;
		case 25:
			if (player.getMoneyPouch().getCoinAmount() < 35000000) {
				player.sendMessage(Colors.red + "You must have 35M coins in your money pouch to pay the fee!");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			if (!player.getInventory().contains(35) || !player.getInventory().contains(19670)) {
				player.sendMessage(Colors.red + "You do not have the required items to research this item!");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			player.coinamount -= 35000000;
			Settings.GpSyncAmount += 35000000;
			player.refreshMoneyPouch();
			player.getInventory().deleteItem(35, 1);
			player.getInventory().deleteItem(19670, 1);
			player.getInventory().addItem(14632, 1);
			World.sendIconWorldMessage(Colors.rcyan + "" + player.getDisplayName() + " has just created the Enhanced Excalibur!", false);
			break;
		case 26:
			if (player.getMoneyPouch().getCoinAmount() < 500000000) {
				player.sendMessage(Colors.red + "You must have 500M coins in your money pouch to pay the fee!");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			if (!player.getInventory().contains(29203) || !player.getInventory().contains(29204) || !player.getInventory().contains(29205)) {
				player.sendMessage(Colors.red + "You do not have the required items to research this item!");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			player.coinamount -= 500000000;
			Settings.GpSyncAmount += 500000000;
			player.refreshMoneyPouch();
			player.getInventory().deleteItem(29203, 1);
			player.getInventory().deleteItem(29204, 1);
			player.getInventory().deleteItem(29205, 1);
			player.getInventory().addItem(29545, 1);
			World.sendIconWorldMessage(Colors.rcyan + "" + player.getDisplayName() + " has just created the Zaros Godsword!", false);
			break;
		case 27:
			if (player.getMoneyPouch().getCoinAmount() < 500000000) {
				player.sendMessage(Colors.red + "You must have 500M coins in your money pouch to pay the fee!");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			if (!player.getInventory().contains(29432) || !player.getInventory().contains(29255) || !player.getInventory().contains(29183) || !player.getInventory().containsItem(29256, 5000)) {
				player.sendMessage(Colors.red + "You do not have the required items to research this item!");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			player.coinamount -= 500000000;
			Settings.GpSyncAmount += 500000000;
			player.refreshMoneyPouch();
			player.getInventory().deleteItem(29432, 1);
			player.getInventory().deleteItem(29255, 1);
			player.getInventory().deleteItem(29183, 1);
			player.getInventory().deleteItem(29256, 5000);
			player.getInventory().addItem(29547, 1);
			World.sendIconWorldMessage(Colors.rcyan + "" + player.getDisplayName() + " has just created the Seren Godbow!", false);
			break;
		case 28:
			if (player.getMoneyPouch().getCoinAmount() < 500000000) {
				player.sendMessage(Colors.red + "You must have 500M coins in your money pouch to pay the fee!");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			if (!player.getInventory().contains(29127) || !player.getInventory().contains(29126) || !player.getInventory().contains(29125)) {
				player.sendMessage(Colors.red + "You do not have the required items to research this item!");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			player.coinamount -= 500000000;
			Settings.GpSyncAmount += 500000000;
			player.refreshMoneyPouch();
			player.getInventory().deleteItem(29127, 1);
			player.getInventory().deleteItem(29126, 1);
			player.getInventory().deleteItem(29125, 1);
			player.getInventory().addItem(29546, 1);
			World.sendIconWorldMessage(Colors.rcyan + "" + player.getDisplayName() + " has just created the Staff of Sliske!", false);
			break;
		case 29:
			if (player.getMoneyPouch().getCoinAmount() < 10000000) {
				player.sendMessage(Colors.red + "You must have 10M coins in your money pouch to pay the fee!");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			if (!player.getInventory().contains(29367) || !player.getInventory().contains(15497) || !player.getInventory().containsItem(29325, 250)) {
				player.sendMessage(Colors.red + "You do not have the required items to research this item!");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			if (player.getSlayerPoints() < 500) {
				player.sendMessage(Colors.red + "You need 500 slayer points to do this!");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			player.coinamount -= 10000000;
			player.refreshMoneyPouch();
			Settings.GpSyncAmount += 10000000;
			player.slayerPoints -= 500;
			player.getInventory().deleteItem(29367, 1);
			player.getInventory().deleteItem(15497, 1);
			player.getInventory().deleteItem(29325, 250);
			player.getInventory().addItemDrop(29103, 1);
			World.sendIconWorldMessage(Colors.rcyan + "" + player.getDisplayName() + " has just created an Ancient slayer helmet!", false);
			break;
		case 30:
			if (!player.getInventory().contains(28984) || !player.getInventory().contains(29569) || !player.getInventory().contains(29570) || !player.getInventory().contains(29571)) {
				player.sendMessage(Colors.red + "You do not have the required items to research this item!");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			player.getInventory().deleteItem(28984, 1);
			player.getInventory().deleteItem(29569, 1);
			player.getInventory().deleteItem(29570, 1);
			player.getInventory().deleteItem(29571, 1);
			player.getInventory().addItemDrop(28985, 1);
			World.sendIconWorldMessage(Colors.rcyan + "" + player.getDisplayName() + " has just created a pair of Amalgam cristallum boots!", false);
			break;
		case 31:
			if (!player.getInventory().contains(29925) || !player.getInventory().containsItem(29370, 10000) || !player.getInventory().contains(28982)) {
				player.sendMessage(Colors.red + "You do not have the required items to research this item!");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			player.getInventory().deleteItem(28982, 1);
			player.getInventory().deleteItem(29370, 10000);
			player.getInventory().deleteItem(29925, 1);
			player.getInventory().addItemDrop(28981, 1);
			World.sendIconWorldMessage(Colors.rcyan + "" + player.getDisplayName() + " has just created a pair of Donator gloves (i)!", false);
			break;
		case 32:
			if (!player.getInventory().contains(29788) || !player.getInventory().containsItem(29370, 10000)|| !player.getInventory().contains(28982)) {
				player.sendMessage(Colors.red + "You do not have the required items to research this item!");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			player.getInventory().deleteItem(28982, 1);
			player.getInventory().deleteItem(29370, 10000);
			player.getInventory().deleteItem(29788, 1);
			player.getInventory().addItemDrop(28980, 1);
			World.sendIconWorldMessage(Colors.rcyan + "" + player.getDisplayName() + " has just created a pair of Donator boots (i)!", false);
			break;
		case 33:
			if (player.getMoneyPouch().getCoinAmount() < 1000000000) {
				player.sendMessage(Colors.red + "You must have 1b coins in your money pouch to pay the fee!");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			if (!player.getInventory().contains(28994) || !player.getInventory().contains(29846) || !player.getInventory().contains(29890) || !player.getInventory().contains(29940) || !player.getInventory().contains(28997) || !player.getInventory().contains(29000) || !player.getInventory().containsItem(28975, 3)) {
				player.sendMessage(Colors.red + "You do not have the required items to research this item!");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			player.coinamount -= 1000000000;
			Settings.GpSyncAmount += 1000000000;
			player.refreshMoneyPouch();
			player.getInventory().deleteItem(28975, 3);
			player.getInventory().deleteItem(28994, 1);
			player.getInventory().deleteItem(28997, 1);
			player.getInventory().deleteItem(29000, 1);
			player.getInventory().deleteItem(29940, 1);
			player.getInventory().deleteItem(29890, 1);
			player.getInventory().deleteItem(29846, 1);
			player.getInventory().addItemDrop(29603, 1);
			World.sendIconWorldMessage(Colors.rcyan + "" + player.getDisplayName() + " has just created a Pharaoh nemes!", false);
			break;
		case 34:
			if (player.getMoneyPouch().getCoinAmount() < 1000000000) {
				player.sendMessage(Colors.red + "You must have 1b coins in your money pouch to pay the fee!");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			if (!player.getInventory().contains(28993) || !player.getInventory().contains(29844) || !player.getInventory().contains(29889) || !player.getInventory().contains(29941) || !player.getInventory().contains(28996) || !player.getInventory().contains(28999) || !player.getInventory().containsItem(28975, 3)) {
				player.sendMessage(Colors.red + "You do not have the required items to research this item!");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			player.coinamount -= 1000000000;
			Settings.GpSyncAmount += 1000000000;
			player.refreshMoneyPouch();
			player.getInventory().deleteItem(28975, 3);
			player.getInventory().deleteItem(28993, 1);
			player.getInventory().deleteItem(28996, 1);
			player.getInventory().deleteItem(28999, 1);
			player.getInventory().deleteItem(29941, 1);
			player.getInventory().deleteItem(29889, 1);
			player.getInventory().deleteItem(29844, 1);
			player.getInventory().addItemDrop(29605, 1);
			World.sendIconWorldMessage(Colors.rcyan + "" + player.getDisplayName() + " has just created a Pharaoh tunic!", false);
			break;
		case 35:
			if (player.getMoneyPouch().getCoinAmount() < 1000000000) {
				player.sendMessage(Colors.red + "You must have 1b coins in your money pouch to pay the fee!");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			if (!player.getInventory().contains(28992) || !player.getInventory().contains(29939) || !player.getInventory().contains(29888) || !player.getInventory().contains(29845) || !player.getInventory().contains(28995) || !player.getInventory().contains(28998) || !player.getInventory().containsItem(28975, 3)) {
				player.sendMessage(Colors.red + "You do not have the required items to research this item!");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			player.coinamount -= 1000000000;
			Settings.GpSyncAmount += 1000000000;
			player.refreshMoneyPouch();
			player.getInventory().deleteItem(28975, 3);
			player.getInventory().deleteItem(28992, 1);
			player.getInventory().deleteItem(28995, 1);
			player.getInventory().deleteItem(28998, 1);
			player.getInventory().deleteItem(29845, 1);
			player.getInventory().deleteItem(29888, 1);
			player.getInventory().deleteItem(29939, 1);
			player.getInventory().addItemDrop(29604, 1);
			World.sendIconWorldMessage(Colors.rcyan + "" + player.getDisplayName() + " has just created a Pharaoh shendyt!", false);
			break;			
		case 36:
			if (player.getMoneyPouch().getCoinAmount() < 1000000000) {
				player.sendMessage(Colors.red + "You must have 1b coins in your money pouch to pay the fee!");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			if (!player.getInventory().contains(29940) || !player.getInventory().contains(29890) || !player.getInventory().contains(29846) || !player.getInventory().contains(28977)) {
				player.sendMessage(Colors.red + "You do not have the required items to research this item!");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			player.coinamount -= 100000000;
			Settings.GpSyncAmount += 100000000;
			player.refreshMoneyPouch();		
			player.getInventory().deleteItem(29940, 1);
			player.getInventory().deleteItem(29890, 1);
			player.getInventory().deleteItem(29846, 1);
			player.getInventory().deleteItem(28977, 1);
			player.getInventory().addItemDrop(28928, 1);
			World.sendIconWorldMessage(Colors.rcyan + "" + player.getDisplayName() + " has just created a Superior Apex Hood!", false);
			break;			
		case 37:
			if (player.getMoneyPouch().getCoinAmount() < 100000000) {
				player.sendMessage(Colors.red + "You must have 100M coins in your money pouch to pay the fee!");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			if (!player.getInventory().contains(29941) || !player.getInventory().contains(29889) || !player.getInventory().contains(29844) || !player.getInventory().contains(28978)) {
				player.sendMessage(Colors.red + "You do not have the required items to research this item!");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			player.coinamount -= 100000000;
			Settings.GpSyncAmount += 100000000;
			player.refreshMoneyPouch();		
			player.getInventory().deleteItem(29941, 1);
			player.getInventory().deleteItem(29889, 1);
			player.getInventory().deleteItem(29844, 1);
			player.getInventory().deleteItem(28978, 1);
			player.getInventory().addItemDrop(28929, 1);
			World.sendIconWorldMessage(Colors.rcyan + "" + player.getDisplayName() + " has just created a Superior Apex Robe Top!", false);
			break;			
		case 38:
			if (player.getMoneyPouch().getCoinAmount() < 100000000) {
				player.sendMessage(Colors.red + "You must have 100M coins in your money pouch to pay the fee!");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			if (!player.getInventory().contains(29845) || !player.getInventory().contains(29888) || !player.getInventory().contains(29939) || !player.getInventory().contains(28976)) {
				player.sendMessage(Colors.red + "You do not have the required items to research this item!");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			player.coinamount -= 100000000;
			Settings.GpSyncAmount += 100000000;
			player.refreshMoneyPouch();		
			player.getInventory().deleteItem(29845, 1);
			player.getInventory().deleteItem(29888, 1);
			player.getInventory().deleteItem(29939, 1);
			player.getInventory().deleteItem(28976, 1);
			player.getInventory().addItemDrop(28927, 1);
			World.sendIconWorldMessage(Colors.rcyan + "" + player.getDisplayName() + " has just created a set of Superior Apex Robe Bottoms!", false);
			break;
		}
	}

}