package com.rs.game.player.interfaces;

import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.content.Magic;

/**
 * 
 * @author Mr_Joopz
 *
 */

public class SlayerTeleportInterface {
	
	public static void OpenInterface(Player player) {
		player.getInterfaceManager().sendInterface(3006);
		player.getPackets().sendIComponentText(3006, 73, "Notable Drops:");
		player.getPackets().sendIComponentText(3006, 26, "Slayer teleports");
		player.dailymoneymakercomponent = 0;
		player.slayertelecomponent = 1;
		/**
		 * Right hand side tab
		 */
		player.getPackets().sendIComponentText(3006, 11, "Slayer Tower");
		player.getPackets().sendIComponentText(3006, 34, "Hitpoints: n/a");
		player.getPackets().sendIComponentText(3006, 33, "Combat Level: n/a");
		player.getPackets().sendIComponentText(3006, 111, "Difficulty: Easy");
		player.getPackets().sendIComponentText(3006, 116, "Attack: All Styles");
		player.getPackets().sendIComponentText(3006, 117, "Base Experience: n/a");
		/**
		 * Left top
		 */
		player.getPackets().sendIComponentText(3006, 28, "Slayer Tower");
		player.getPackets().sendIComponentText(3006, 99, "Ganodermic Beast");
		player.getPackets().sendIComponentText(3006, 100, "Jadinkos");
		player.getPackets().sendIComponentText(3006, 101, "Chaos Tunnels");
		// player.getPackets().sendIComponentText(3006, 102, "Corporeal Beast");
		player.getPackets().sendIComponentText(3006, 102, "Ancient Caverns");
		player.getPackets().sendIComponentText(3006, 103, "Dragon Dungeon");
		player.getPackets().sendIComponentText(3006, 104, "Taverly Dungeon");
		player.getPackets().sendIComponentText(3006, 105, "Kuradals Brothers");
		player.getPackets().sendIComponentText(3006, 106, "Rune & Adamant dragons");
		player.getPackets().sendIComponentText(3006, 107, "Strykewyrms");
		player.getPackets().sendIComponentText(3006, 108, "Smoke devils");
		player.getPackets().sendIComponentText(3006, 109, "Lizardman Shaman");
		player.getPackets().sendIComponentText(3006, 110, "Fremennik Dungeon");
		player.getPackets().sendIComponentText(3006, 118, "Cave Horrors");
		player.getPackets().sendIComponentText(3006, 119, "Brimhaven Dungeon");
		player.getPackets().sendIComponentText(3006, 120, "Catacombs of Kourend");
		player.getPackets().sendIComponentText(3006, 121, "Wyvern Cave");
		player.getPackets().sendIComponentText(3006, 122, "Karuulm Slayer");
		player.getPackets().sendIComponentText(3006, 123, "Glacors");
		player.getPackets().sendIComponentText(3006, 124, "");
		player.getPackets().sendIComponentText(3006, 125, "");
		// player.getPackets().sendIComponentText(3006, 127, "Sirenic the Spider");
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
		player.getPackets().sendIComponentText(3006, 137, "");
		// player.getPackets().sendIComponentText(3006, 138, "Ingenuity");
		// player.getPackets().sendIComponentText(3006, 108, "");
		/**
		 * Left bottom
		 */
		player.getDmm().getContainer().clear();
		player.getDmm().getContainer().add(new Item(4151, 1));
		player.getDmm().getContainer().add(new Item(11732, 1));
		player.getDmm().getContainer().add(new Item(4153, 1));
		player.getDmm().getContainer().add(new Item(11235, 1));
		player.getPackets().sendInterSetItemsOptionsScript(3006, 72, 100, 8, 3, "Examine");
		player.getPackets().sendUnlockIComponentOptionSlots(3006, 72, 0, 10, 0, 1, 2, 3);
		player.getDmm().getContainer().shift();
		player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
	}



	public static void HandleButtons(Player player, int componentId, int slotId) {
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
			player.slayertelecomponent = 2;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(3006, 11, "Ganodermic Beasts");
			player.getPackets().sendIComponentText(3006, 34, "Hitpoints: 1250");
			player.getPackets().sendIComponentText(3006, 33, "Combat Level: 280");
			player.getPackets().sendIComponentText(3006, 111, "Difficulty: Easy");
			player.getPackets().sendIComponentText(3006, 116, "Attack: Magic");
			player.getPackets().sendIComponentText(3006, 117, "Base Experience: 500");
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(22451, 500));
			player.getDmm().getContainer().add(new Item(22494, 1));
			player.getDmm().getContainer().add(new Item(6199, 1));
			player.getDmm().getContainer().add(new Item(29374, 1));
			player.getPackets().sendInterSetItemsOptionsScript(3006, 72, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3006, 72, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 100:
			player.slayertelecomponent = 3;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(3006, 11, "Jadinko baby");
			player.getPackets().sendIComponentText(3006, 34, "Hitpoints: 1270");
			player.getPackets().sendIComponentText(3006, 33, "Combat Level: 90");
			player.getPackets().sendIComponentText(3006, 111, "Difficulty: Easy");
			player.getPackets().sendIComponentText(3006, 116, "Attack: Melee");
			player.getPackets().sendIComponentText(3006, 117, "Base Experience: 190");
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(21369, 1));
			player.getDmm().getContainer().add(new Item(29374, 1));
			player.getPackets().sendInterSetItemsOptionsScript(3006, 72, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3006, 72, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 101:
			player.slayertelecomponent = 4;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(3006, 11, "Chaos Tunnels");
			player.getPackets().sendIComponentText(3006, 34, "Hitpoints: n/a");
			player.getPackets().sendIComponentText(3006, 33, "Combat Level: n/a");
			player.getPackets().sendIComponentText(3006, 111, "Difficulty: Medium");
			player.getPackets().sendIComponentText(3006, 116, "Attack: All styles");
			player.getPackets().sendIComponentText(3006, 117, "Base Experience: n/a");
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(3140, 1));
			player.getDmm().getContainer().add(new Item(4087, 1));
			player.getDmm().getContainer().add(new Item(11286, 1));
			player.getDmm().getContainer().add(new Item(29374, 1));
			player.getPackets().sendInterSetItemsOptionsScript(3006, 72, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3006, 72, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 102:
			player.slayertelecomponent = 5;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(3006, 11, "Ancient Caverns");
			player.getPackets().sendIComponentText(3006, 34, "Hitpoints: n/a");
			player.getPackets().sendIComponentText(3006, 33, "Combat Level: n/a");
			player.getPackets().sendIComponentText(3006, 111, "Difficulty: Medium");
			player.getPackets().sendIComponentText(3006, 116, "Attack: All styles");
			player.getPackets().sendIComponentText(3006, 117, "Base Experience: n/a");
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(11286, 1));
			player.getDmm().getContainer().add(new Item(11335, 1));
			player.getDmm().getContainer().add(new Item(29721, 1));
			player.getDmm().getContainer().add(new Item(29374, 1));
			player.getPackets().sendInterSetItemsOptionsScript(3006, 72, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3006, 72, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 103:
			player.slayertelecomponent = 6;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(3006, 11, "Dragon Dungeon");
			player.getPackets().sendIComponentText(3006, 34, "Hitpoints: n/a");
			player.getPackets().sendIComponentText(3006, 33, "Combat Level: n/a");
			player.getPackets().sendIComponentText(3006, 111, "Difficulty: Easy");
			player.getPackets().sendIComponentText(3006, 116, "Attack: All styles");
			player.getPackets().sendIComponentText(3006, 117, "Base Experience: n/a");
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(11286, 1));
			player.getDmm().getContainer().add(new Item(29374, 1));
			player.getPackets().sendInterSetItemsOptionsScript(3006, 72, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3006, 72, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 104:
			player.slayertelecomponent = 7;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(3006, 11, "Taverly Dungeon");
			player.getPackets().sendIComponentText(3006, 34, "Hitpoints: n/a");
			player.getPackets().sendIComponentText(3006, 33, "Combat Level: n/a");
			player.getPackets().sendIComponentText(3006, 111, "Difficulty: Easy");
			player.getPackets().sendIComponentText(3006, 116, "Attack: All Styles");
			player.getPackets().sendIComponentText(3006, 117, "Base Experience: n/a");
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(2677, 1));
			player.getDmm().getContainer().add(new Item(29374, 1));
			player.getPackets().sendInterSetItemsOptionsScript(3006, 72, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3006, 72, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 105:
			player.slayertelecomponent = 8;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(3006, 11, "Kuradal Brothers");
			player.getPackets().sendIComponentText(3006, 34, "Hitpoints: 10000");
			player.getPackets().sendIComponentText(3006, 33, "Combat Level: 138");
			player.getPackets().sendIComponentText(3006, 111, "Difficulty: Hard - <col=ff0000>PVP AREA</col>");
			player.getPackets().sendIComponentText(3006, 116, "Attack: Magic & Melee");
			player.getPackets().sendIComponentText(3006, 117, "Base Experience: 8000");
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(29679, 1));
			player.getDmm().getContainer().add(new Item(29678, 1));
			player.getDmm().getContainer().add(new Item(4153, 1));
			player.getDmm().getContainer().add(new Item(11235, 1));
			player.getDmm().getContainer().add(new Item(29374, 1));
			player.getPackets().sendInterSetItemsOptionsScript(3006, 72, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3006, 72, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 106:
			player.slayertelecomponent = 9;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(3006, 11, "Rune & Adamant dragons");
			player.getPackets().sendIComponentText(3006, 34, "Hitpoints: 3000 - 4200");
			player.getPackets().sendIComponentText(3006, 33, "Combat Level: 395 - 444");
			player.getPackets().sendIComponentText(3006, 111, "Difficulty: Hard");
			player.getPackets().sendIComponentText(3006, 116, "Attack: All styles");
			player.getPackets().sendIComponentText(3006, 117, "Base Experience: 235 - 247");
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(3140, 1));
			player.getDmm().getContainer().add(new Item(4087, 1));
			player.getDmm().getContainer().add(new Item(11335, 1));
			player.getDmm().getContainer().add(new Item(2677, 1));
			player.getDmm().getContainer().add(new Item(11283, 1));
			player.getDmm().getContainer().add(new Item(989, 1));
			player.getDmm().getContainer().add(new Item(29800, 1));
			player.getDmm().getContainer().add(new Item(14484, 1));
			player.getDmm().getContainer().add(new Item(29374, 1));
			player.getPackets().sendInterSetItemsOptionsScript(3006, 72, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3006, 72, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 107:
			player.slayertelecomponent = 10;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(3006, 11, "Strykewyrm");
			player.getPackets().sendIComponentText(3006, 34, "Hitpoints: 3000");
			player.getPackets().sendIComponentText(3006, 33, "Combat Level: 210");
			player.getPackets().sendIComponentText(3006, 111, "Difficulty: Medium");
			player.getPackets().sendIComponentText(3006, 116, "Attack: Melee");
			player.getPackets().sendIComponentText(3006, 117, "Base Experience: 225");
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(15486, 1));
			player.getDmm().getContainer().add(new Item(15488, 1));
			player.getDmm().getContainer().add(new Item(15490, 1));
			player.getDmm().getContainer().add(new Item(29374, 1));
			player.getPackets().sendInterSetItemsOptionsScript(3006, 72, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3006, 72, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 108:
			player.slayertelecomponent = 11;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(3006, 11, "Smoke Devils");
			player.getPackets().sendIComponentText(3006, 34, "Hitpoints: 1850-2400");
			player.getPackets().sendIComponentText(3006, 33, "Combat Level: 160-301");
			player.getPackets().sendIComponentText(3006, 111, "Difficulty: Medium");
			player.getPackets().sendIComponentText(3006, 116, "Attack: Magic");
			player.getPackets().sendIComponentText(3006, 117, "Base Experience: 140");
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(29451, 1));
			player.getDmm().getContainer().add(new Item(29450, 1));
			player.getDmm().getContainer().add(new Item(3140, 1));
			player.getDmm().getContainer().add(new Item(2677, 1));
			player.getDmm().getContainer().add(new Item(989, 1));
			player.getDmm().getContainer().add(new Item(29374, 1));
			player.getPackets().sendInterSetItemsOptionsScript(3006, 72, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3006, 72, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 109:
			player.slayertelecomponent = 12;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(3006, 11, "Lizardman Shaman");
			player.getPackets().sendIComponentText(3006, 34, "Hitpoints: 1500");
			player.getPackets().sendIComponentText(3006, 33, "Combat Level: 150");
			player.getPackets().sendIComponentText(3006, 111, "Difficulty: Medium");
			player.getPackets().sendIComponentText(3006, 116, "Attack: Melee");
			player.getPackets().sendIComponentText(3006, 117, "Base Experience: 157.5");
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(29440, 1));
			player.getDmm().getContainer().add(new Item(2677, 1));
			player.getDmm().getContainer().add(new Item(29374, 1));
			player.getPackets().sendInterSetItemsOptionsScript(3006, 72, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3006, 72, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 110:
			player.slayertelecomponent = 13;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(3006, 11, "Fremennik Dungeon");
			player.getPackets().sendIComponentText(3006, 34, "Hitpoints: n/a");
			player.getPackets().sendIComponentText(3006, 33, "Combat Level: n/a");
			player.getPackets().sendIComponentText(3006, 111, "Difficulty: Easy");
			player.getPackets().sendIComponentText(3006, 116, "Attack: All Styles");
			player.getPackets().sendIComponentText(3006, 117, "Base Experience: n/a");
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(1249, 1));
			player.getDmm().getContainer().add(new Item(987, 1));
			player.getDmm().getContainer().add(new Item(985, 1));
			player.getDmm().getContainer().add(new Item(13290, 1));
			player.getDmm().getContainer().add(new Item(29374, 1));
			player.getPackets().sendInterSetItemsOptionsScript(3006, 72, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3006, 72, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 118:
			player.slayertelecomponent = 14;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(3006, 11, "Cave Horror");
			player.getPackets().sendIComponentText(3006, 34, "Hitpoints: 550");
			player.getPackets().sendIComponentText(3006, 33, "Combat Level: 80");
			player.getPackets().sendIComponentText(3006, 111, "Difficulty: Easy");
			player.getPackets().sendIComponentText(3006, 116, "Attack: Melee");
			player.getPackets().sendIComponentText(3006, 117, "Base Experience: 55");
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(8901, 1));
			player.getDmm().getContainer().add(new Item(29374, 1));
			player.getPackets().sendInterSetItemsOptionsScript(3006, 72, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3006, 72, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 119:
			player.slayertelecomponent = 15;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(3006, 11, "Brimhaven Dungeon");
			player.getPackets().sendIComponentText(3006, 34, "Hitpoints: n/a");
			player.getPackets().sendIComponentText(3006, 33, "Combat Level: n/a");
			player.getPackets().sendIComponentText(3006, 111, "Difficulty: Medium");
			player.getPackets().sendIComponentText(3006, 116, "Attack: All styles");
			player.getPackets().sendIComponentText(3006, 117, "Base Experience: n/a");
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(11286, 1));
			player.getDmm().getContainer().add(new Item(29374, 1));
			player.getPackets().sendInterSetItemsOptionsScript(3006, 72, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3006, 72, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 120:
			player.slayertelecomponent = 16;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(3006, 11, "Catacombs");
			player.getPackets().sendIComponentText(3006, 34, "Hitpoints: n/a");
			player.getPackets().sendIComponentText(3006, 33, "Combat Level: n/a");
			player.getPackets().sendIComponentText(3006, 111, "Difficulty: Medium");
			player.getPackets().sendIComponentText(3006, 116, "Attack: All styles");
			player.getPackets().sendIComponentText(3006, 117, "Base Experience: n/a");
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(4151, 1));
			player.getDmm().getContainer().add(new Item(11286, 1));
			player.getDmm().getContainer().add(new Item(3140, 1));
			player.getDmm().getContainer().add(new Item(2677, 1));
			player.getDmm().getContainer().add(new Item(989, 1));
			player.getDmm().getContainer().add(new Item(29374, 1));
			player.getPackets().sendInterSetItemsOptionsScript(3006, 72, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3006, 72, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 121:
			player.slayertelecomponent = 17;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(3006, 11, "Wyvern Cave");
			player.getPackets().sendIComponentText(3006, 34, "Hitpoints: 2000-3000");
			player.getPackets().sendIComponentText(3006, 33, "Combat Level: 139-210");
			player.getPackets().sendIComponentText(3006, 111, "Difficulty: Medium");
			player.getPackets().sendIComponentText(3006, 116, "Attack: All styles");
			player.getPackets().sendIComponentText(3006, 117, "Base Experience: n/a");
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(29195, 1));

			player.getPackets().sendInterSetItemsOptionsScript(3006, 72, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3006, 72, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 122:
			player.slayertelecomponent = 18;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(3006, 11, "Karuulm Slayer Dungeon");
			player.getPackets().sendIComponentText(3006, 34, "Hitpoints: Varies");
			player.getPackets().sendIComponentText(3006, 33, "Combat Level: Varies");
			player.getPackets().sendIComponentText(3006, 111, "Difficulty: Varies");
			player.getPackets().sendIComponentText(3006, 116, "Attack: Varies");
			player.getPackets().sendIComponentText(3006, 117, "Base Experience: n/a");
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(29181, 1));
			player.getDmm().getContainer().add(new Item(29180, 1));
			player.getDmm().getContainer().add(new Item(29177, 1));
			player.getDmm().getContainer().add(new Item(29176, 1));
			player.getDmm().getContainer().add(new Item(29175, 1));
			player.getDmm().getContainer().add(new Item(29174, 1));
			player.getDmm().getContainer().add(new Item(29171, 1));
			player.getDmm().getContainer().add(new Item(29169, 1));

			player.getPackets().sendInterSetItemsOptionsScript(3006, 72, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3006, 72, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 123:
			player.slayertelecomponent = 19;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(3006, 11, "Glacor");
			player.getPackets().sendIComponentText(3006, 34, "Hitpoints: 5000");
			player.getPackets().sendIComponentText(3006, 33, "Combat Level: 475");
			player.getPackets().sendIComponentText(3006, 111, "Difficulty: Easy");
			player.getPackets().sendIComponentText(3006, 116, "Attack: Range & Magic");
			player.getPackets().sendIComponentText(3006, 117, "Base Experience: 329");
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(21787, 1));
			player.getDmm().getContainer().add(new Item(21790, 1));
			player.getDmm().getContainer().add(new Item(21793, 1));
			player.getDmm().getContainer().add(new Item(6199, 1));
			player.getDmm().getContainer().add(new Item(2677, 1));
			player.getDmm().getContainer().add(new Item(29541, 1));

			player.getPackets().sendInterSetItemsOptionsScript(3006, 72, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3006, 72, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		}
		
	}
	
	
	
	public static void HandleTeleport(Player player) {
		switch (player.slayertelecomponent) {
		case 1:
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3423, 3544, 0));
			break;
		case 2:
			if (player.getSkills().getLevel(Skills.SLAYER) < 90) {
				player.getPackets().sendGameMessage("You need a slayer level of 90 to kill Ganodermic Beasts.");
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				player.getInterfaceManager().closeInterfaceCustom();
				// player.getControlerManager().forceStop();
				// player.getControlerManager().removeControlerWithoutCheck();
				return;
			}
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(4654, 5485, 0));
			break;
		case 3:
			if (player.getSkills().getLevel(Skills.SLAYER) < 80) {
				player.getPackets().sendGameMessage(
						"You need a slayer level of 80 to kill Jadinkos.");
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				// player.getControlerManager().forceStop();
				// player.getControlerManager().removeControlerWithoutCheck();
				return;
			}
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3013, 9273, 0));
			player.getInterfaceManager().closeChatBoxInterface();
			player.getInterfaceManager().closeOverlay(true);
			break;
		case 4:
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3170, 5513, 0));
			break;
		case 5:
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2512, 3515, 0));
			break;
		case 6:
			if (player.getSkills().getLevel(Skills.SLAYER) < 80 && player.getSkills().getLevel(Skills.DUNGEONEERING) < 105) {
				player.getPackets().sendGameMessage("You need a slayer level of 80 & a dungeoneering level of 105.");
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				player.getInterfaceManager().closeInterfaceCustom();
				// player.getControlerManager().forceStop();
				// player.getControlerManager().removeControlerWithoutCheck();
				return;
			}
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2722, 9774, 0));
			break;
		case 7:
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2884, 9805, 0));
			break;
		case 8:
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3135, 3889, 0));
			player.getControlerManager().startControler("Wilderness");
			break;
		case 9:
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2131, 3856, 0));
			break;
		case 10:
			player.getInterfaceManager().closeInterfaceCustom();
			player.getDialogueManager().startDialogue("StrykeWyrm");
			break;
		case 11:
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3662, 5770, 0));
			break;
		case 12:
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(1495, 3700, 0));
			break;
		case 13:
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2804, 10001, 0));
			break;
		case 14:
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3795, 9444, 0));
			break;
		case 15:
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2718, 9481, 0));
			break;
		case 16:
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(1666, 10049, 0));
			break;
		case 17:
			if (player.getSkills().getLevel(Skills.SLAYER) < 66) {
				player.getInterfaceManager().closeChatBoxInterface();
				player.getInterfaceManager().closeOverlay(true);
				player.getInterfaceManager().closeInterfaceCustom();
				player.sendMessage("You must have 66 slayer to access this cave!");
				return;
			}
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3614, 10279, 0));
			break;
		case 18:
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(1312, 10189, 0));
			break;
		case 19:
			if (player.getSkills().getLevel(Skills.SLAYER) < 50) {
				player.getPackets().sendGameMessage(
						"You need a slayer level of 50 to kill Glacors.");
				return;
			} else {
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(4182,
					5731, 0));
			}
			break;
		}

	}
	
	
	/**
	 * Container
	 */

	private static final int containerKey = 100;
	
	
	
	
}