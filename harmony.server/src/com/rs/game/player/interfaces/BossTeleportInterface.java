package com.rs.game.player.interfaces;

import java.util.Calendar;

import com.rs.Settings;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.Summoning;
import com.rs.game.player.content.Magic;
import com.rs.game.player.content.bossinstance.BossInstance;
import com.rs.game.player.content.bossinstance.BossInstanceManager;
import com.rs.game.player.content.bossinstance.impl.AbyssalSire;
import com.rs.game.player.content.bossinstance.impl.Rot6Instance;
import com.rs.game.player.content.bossinstance.impl.VorkathInstance;
import com.rs.game.player.content.bossinstance.impl.ZulrahInstance;
import com.rs.game.player.content.pet.Pets;
import com.rs.utils.Colors;

/**
 * 
 * @author Mr_Joopz
 *
 */

public class BossTeleportInterface {
	
	private static final int containerKey = 100;

	public static void OpenInterface(Player player) {
		player.getInterfaceManager().sendInterface(3002);
		player.bosstelecomponent = 0;
		/**
		 * Right hand side tab
		 */
		player.getPackets().sendIComponentText(3002, 11, "Corporeal Beast");
		player.getPackets().sendIComponentText(3002, 34, "Hitpoints: 20000");
		player.getPackets().sendIComponentText(3002, 33, "Combat Level: 785");
		player.getPackets().sendIComponentText(3002, 111, "Difficulty: Hard");
		player.getPackets().sendIComponentText(3002, 116, "Attack: Melee & Magic");
		player.getPackets().sendIComponentText(3002, 117, "Killcount: " + player.CorpKills + "");
		player.getPackets().sendIComponentText(3002, 138, "Weakness: Melee");
		/**
		 * Left top
		 */
		player.getPackets().sendIComponentText(3002, 28, "Corporeal Beast");
		player.getPackets().sendIComponentText(3002, 99, "King Black Dragon");
		player.getPackets().sendIComponentText(3002, 100, "Queen Black Dragon");
		player.getPackets().sendIComponentText(3002, 101, "Bork");
		// player.getPackets().sendIComponentText(3002, 102, "Corporeal Beast");
		player.getPackets().sendIComponentText(3002, 102, "Giant Mole");
		player.getPackets().sendIComponentText(3002, 103, "Nex");
		player.getPackets().sendIComponentText(3002, 104, "Alchemical Hydra");
		player.getPackets().sendIComponentText(3002, 105, "Rise of the Six");
		player.getPackets().sendIComponentText(3002, 106, "Bandos");
		player.getPackets().sendIComponentText(3002, 107, "Zamorak");
		player.getPackets().sendIComponentText(3002, 108, "Armadyl");
		player.getPackets().sendIComponentText(3002, 109, "Saradomin");
		player.getPackets().sendIComponentText(3002, 110, "WildyWyrm's");
		player.getPackets().sendIComponentText(3002, 118, "Vorkath");
		player.getPackets().sendIComponentText(3002, 119, "Demi God Trio");
		player.getPackets().sendIComponentText(3002, 120, "Vorago");
		player.getPackets().sendIComponentText(3002, 121, "Avatar of Destruction");
		player.getPackets().sendIComponentText(3002, 122, "Night-Gazer");
		player.getPackets().sendIComponentText(3002, 123, "Bad Santa");
		player.getPackets().sendIComponentText(3002, 124, "Dryax");
		player.getPackets().sendIComponentText(3002, 125, "Kalphite King");
		// player.getPackets().sendIComponentText(3002, 127, "Sirenic the Spider");
		player.getPackets().sendIComponentText(3002, 126, "Hope Devourer");
		player.getPackets().sendIComponentText(3002, 127, "Party Demon");
		player.getPackets().sendIComponentText(3002, 128, "Zulrah");
		player.getPackets().sendIComponentText(3002, 129, "Necrolord");
		player.getPackets().sendIComponentText(3002, 130, "Yk'Lagor the Thunderous");
		player.getPackets().sendIComponentText(3002, 131, "Sunfreet");
		player.getPackets().sendIComponentText(3002, 132, "Anivia");
		player.getPackets().sendIComponentText(3002, 133, "Sliske");
		player.getPackets().sendIComponentText(3002, 134, "Kalphite Queen");
		player.getPackets().sendIComponentText(3002, 135, "Kraken");
		player.getPackets().sendIComponentText(3002, 136, "Abyssal Sire");
		player.getPackets().sendIComponentText(3002, 137, "Cerberus");
		player.getPackets().sendIComponentText(3002, 139, "Sirenic the Spider");
		player.getPackets().sendIComponentText(3002, 140, "Callisto");
		player.getPackets().sendIComponentText(3002, 141, "Venenatis");
		player.getPackets().sendIComponentText(3002, 142, "Chaos Fanatic");
		player.getPackets().sendIComponentText(3002, 143, "Crazy Archaeologist");
		player.getPackets().sendIComponentText(3002, 144, "Scorpia");
		player.getPackets().sendIComponentText(3002, 145, "Vet'ion");
		player.getPackets().sendIComponentText(3002, 146, "Tormented Demons");
		player.getPackets().sendIComponentText(3002, 147, "Angry Easter Bunny");
		player.getPackets().sendIComponentText(3002, 148, "The 3 Amigos");

		/**
		 * Left bottom
		 */
		player.getDmm().getContainer().clear();
		player.getDmm().getContainer().add(new Item(13738, 1));
		player.getDmm().getContainer().add(new Item(13740, 1));
		player.getDmm().getContainer().add(new Item(13742, 1));
		player.getDmm().getContainer().add(new Item(13744, 1));
		player.getDmm().getContainer().add(new Item(29527, 1));
		player.getDmm().getContainer().add(new Item(29599, 1));
		player.getDmm().getContainer().add(new Item(29891, 1));
		player.getPackets().sendInterSetItemsOptionsScript(3002, 72, 100, 8, 3, "Examine");
		player.getPackets().sendUnlockIComponentOptionSlots(3002, 72, 0, 10, 0, 1, 2, 3);
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
		case 84:
			// player.sendMessage("Divine Spirit Shield");
			break;
		case 31:
			HandleTeleport(player);
			break;
		case 28:
			OpenInterface(player);
			break;
		case 99:
			player.bosstelecomponent = 1;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(3002, 11, "King Black Dragon");
			player.getPackets().sendIComponentText(3002, 34, "Hitpoints: 2400");
			player.getPackets().sendIComponentText(3002, 33, "Combat Level: 276");
			player.getPackets().sendIComponentText(3002, 111, "Difficulty: Easy");
			player.getPackets().sendIComponentText(3002, 116, "Attack: Melee & Magic");
			player.getPackets().sendIComponentText(3002, 117, "Killcount: " + player.KbdKills + "");
			player.getPackets().sendIComponentText(3002, 138, "Weakness: Melee");
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(11286, 1));
			player.getDmm().getContainer().add(new Item(6199, 1));
			player.getDmm().getContainer().add(new Item(2677, 1));
			player.getDmm().getContainer().add(new Item(29541, 1));
			player.getPackets().sendInterSetItemsOptionsScript(3002, 72, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3002, 72, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;

		case 100:
			player.bosstelecomponent = 2;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(3002, 11, "Queen Black Dragon");
			player.getPackets().sendIComponentText(3002, 34, "Hitpoints: 10000");
			player.getPackets().sendIComponentText(3002, 33, "Combat Level: 2100");
			player.getPackets().sendIComponentText(3002, 111, "Difficulty: Medium");
			player.getPackets().sendIComponentText(3002, 116, "Attack: Melee & Range");
			player.getPackets().sendIComponentText(3002, 117, "Killcount: " + player.QbdKills + "");
			player.getPackets().sendIComponentText(3002, 138, "Weakness: Melee");
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(11286, 1));
			player.getDmm().getContainer().add(new Item(24365, 1));
			player.getDmm().getContainer().add(new Item(2677, 1));
			player.getDmm().getContainer().add(new Item(24342, 1));
			player.getDmm().getContainer().add(new Item(24340, 1));
			player.getDmm().getContainer().add(new Item(24344, 1));
			player.getDmm().getContainer().add(new Item(24346, 1));
			player.getPackets().sendInterSetItemsOptionsScript(3002, 72, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3002, 72, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 101:
			player.bosstelecomponent = 3;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(3002, 11, "Bork");
			player.getPackets().sendIComponentText(3002, 34, "Hitpoints: 3000");
			player.getPackets().sendIComponentText(3002, 33, "Combat Level: 267");
			player.getPackets().sendIComponentText(3002, 111, "Difficulty: Easy");
			player.getPackets().sendIComponentText(3002, 116, "Attack: Melee");
			player.getPackets().sendIComponentText(3002, 117, "Killcount: " + player.BorkKills + "");
			player.getPackets().sendIComponentText(3002, 138, "Weakness: Melee");
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(7462, 1));
			player.getDmm().getContainer().add(new Item(4151, 1));
			player.getDmm().getContainer().add(new Item(10551, 1));
			player.getDmm().getContainer().add(new Item(29541, 1));
			player.getPackets().sendInterSetItemsOptionsScript(3002, 72, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3002, 72, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 102:
			player.bosstelecomponent = 4;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(3002, 11, "Giant Mole");
			player.getPackets().sendIComponentText(3002, 34, "Hitpoints: 2000");
			player.getPackets().sendIComponentText(3002, 33, "Combat Level: 230");
			player.getPackets().sendIComponentText(3002, 111, "Difficulty: Easy");
			player.getPackets().sendIComponentText(3002, 116, "Attack: Melee");
			player.getPackets().sendIComponentText(3002, 117, "Killcount: " + player.GmoleKills + "");
			player.getPackets().sendIComponentText(3002, 138, "Weakness: Stab & Ranged");
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(7158, 1));
			player.getDmm().getContainer().add(new Item(19749, 1));
			player.getPackets().sendInterSetItemsOptionsScript(3002, 72, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3002, 72, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 103:
			player.bosstelecomponent = 5;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(3002, 11, "Nex");
			player.getPackets().sendIComponentText(3002, 34, "Hitpoints: 20000");
			player.getPackets().sendIComponentText(3002, 33, "Combat Level: 1001");
			player.getPackets().sendIComponentText(3002, 111, "Difficulty: Hard");
			player.getPackets().sendIComponentText(3002, 116, "Attack: All styles");
			player.getPackets().sendIComponentText(3002, 117, "Killcount: " + player.NexKills + "");
			player.getPackets().sendIComponentText(3002, 138, "Weakness: Ranged");
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(20135, 1));
			player.getDmm().getContainer().add(new Item(20139, 1));
			player.getDmm().getContainer().add(new Item(20143, 1));
			player.getDmm().getContainer().add(new Item(24977, 1));
			player.getDmm().getContainer().add(new Item(24983, 1));
			player.getDmm().getContainer().add(new Item(20147, 1));
			player.getDmm().getContainer().add(new Item(20151, 1));
			player.getDmm().getContainer().add(new Item(20155, 1));
			player.getDmm().getContainer().add(new Item(24974, 1));
			player.getDmm().getContainer().add(new Item(24989, 1));
			player.getDmm().getContainer().add(new Item(20159, 1));
			player.getDmm().getContainer().add(new Item(20163, 1));
			player.getDmm().getContainer().add(new Item(20167, 1));
			player.getDmm().getContainer().add(new Item(24986, 1));
			player.getDmm().getContainer().add(new Item(25066, 1));
			player.getDmm().getContainer().add(new Item(20171, 1));
			player.getDmm().getContainer().add(new Item(29928, 1));
			player.getDmm().getContainer().add(new Item(29654, 1));
			player.getPackets().sendInterSetItemsOptionsScript(3002, 72, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3002, 72, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 104:
			player.bosstelecomponent = 6;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(3002, 11, "Alchemical Hydra");
			player.getPackets().sendIComponentText(3002, 34, "Hitpoints: 11000");
			player.getPackets().sendIComponentText(3002, 33, "Combat Level: 426");
			player.getPackets().sendIComponentText(3002, 111, "Difficulty: Hard");
			player.getPackets().sendIComponentText(3002, 116, "Attack: All styles");
			player.getPackets().sendIComponentText(3002, 117, "Killcount: " + player.HydraBossKills + "");
			player.getPackets().sendIComponentText(3002, 138, "Weakness: Stab & Ranged");
			/**
			 * 
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(29171, 1));
			player.getDmm().getContainer().add(new Item(29174, 1));
			player.getDmm().getContainer().add(new Item(29175, 1));
			player.getDmm().getContainer().add(new Item(29176, 1));
			player.getDmm().getContainer().add(new Item(29177, 1));
			player.getDmm().getContainer().add(new Item(29169, 1));
			player.getPackets().sendInterSetItemsOptionsScript(3002, 72, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3002, 72, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 105:
			player.bosstelecomponent = 7;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(3002, 11, "Rise of the Six");
			player.getPackets().sendIComponentText(3002, 34, "Hitpoints: 18000");
			player.getPackets().sendIComponentText(3002, 33, "Combat Level: 121-196");
			player.getPackets().sendIComponentText(3002, 111, "Difficulty: Hard");
			player.getPackets().sendIComponentText(3002, 116, "Attack: All styles");
			player.getPackets().sendIComponentText(3002, 117, "Killcount: " + player.Rot6Kills + "");
			player.getPackets().sendIComponentText(3002, 138, "Weakness: No Weakness");
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(29932, 1));
			player.getDmm().getContainer().add(new Item(29933, 1));
			player.getDmm().getContainer().add(new Item(29934, 1));
			player.getDmm().getContainer().add(new Item(29942, 1));
			player.getPackets().sendInterSetItemsOptionsScript(3002, 72, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3002, 72, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 106:
			player.bosstelecomponent = 8;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(3002, 11, "Bandos");
			player.getPackets().sendIComponentText(3002, 34, "Hitpoints: 2540");
			player.getPackets().sendIComponentText(3002, 33, "Combat Level: 624");
			player.getPackets().sendIComponentText(3002, 111, "Difficulty: Medium");
			player.getPackets().sendIComponentText(3002, 116, "Attack: All styles");
			player.getPackets().sendIComponentText(3002, 117, "Killcount: " + player.BandosKills + "");
			player.getPackets().sendIComponentText(3002, 138, "Weakness: Magic");
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(11704, 1));
			player.getDmm().getContainer().add(new Item(11724, 1));
			player.getDmm().getContainer().add(new Item(11726, 1));
			player.getDmm().getContainer().add(new Item(6199, 1));
			player.getDmm().getContainer().add(new Item(2677, 1));
			player.getDmm().getContainer().add(new Item(29541, 1));
			player.getPackets().sendInterSetItemsOptionsScript(3002, 72, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3002, 72, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 107:
			player.bosstelecomponent = 9;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(3002, 11, "Zamorak");
			player.getPackets().sendIComponentText(3002, 34, "Hitpoints: 3000");
			player.getPackets().sendIComponentText(3002, 33, "Combat Level: 650");
			player.getPackets().sendIComponentText(3002, 111, "Difficulty: Medium");
			player.getPackets().sendIComponentText(3002, 116, "Attack: All styles");
			player.getPackets().sendIComponentText(3002, 117, "Killcount: " + player.ZamorakKills + "");
			player.getPackets().sendIComponentText(3002, 138, "Weakness: Magic");
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(11708, 1));
			player.getDmm().getContainer().add(new Item(24992, 1));
			player.getDmm().getContainer().add(new Item(24995, 1));
			player.getDmm().getContainer().add(new Item(24998, 1));
			player.getDmm().getContainer().add(new Item(11716, 1));
			player.getDmm().getContainer().add(new Item(29454, 1));
			player.getDmm().getContainer().add(new Item(2677, 1));
			player.getDmm().getContainer().add(new Item(29541, 1));
			player.getPackets().sendInterSetItemsOptionsScript(3002, 72, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3002, 72, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 108:
			player.bosstelecomponent = 10;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(3002, 11, "Armadyl");
			player.getPackets().sendIComponentText(3002, 34, "Hitpoints: 2550");
			player.getPackets().sendIComponentText(3002, 33, "Combat Level: 580");
			player.getPackets().sendIComponentText(3002, 111, "Difficulty: Medium");
			player.getPackets().sendIComponentText(3002, 116, "Attack: All styles");
			player.getPackets().sendIComponentText(3002, 117, "Killcount: " + player.ArmadylKills + "");
			player.getPackets().sendIComponentText(3002, 138, "Weakness: Ranged");
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(11702, 1));
			player.getDmm().getContainer().add(new Item(11718, 1));
			player.getDmm().getContainer().add(new Item(11720, 1));
			player.getDmm().getContainer().add(new Item(11722, 1));
			player.getDmm().getContainer().add(new Item(6199, 1));
			player.getDmm().getContainer().add(new Item(2677, 1));
			player.getDmm().getContainer().add(new Item(29541, 1));
			player.getPackets().sendInterSetItemsOptionsScript(3002, 72, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3002, 72, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 109:
			player.bosstelecomponent = 11;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(3002, 11, "Saradomin");
			player.getPackets().sendIComponentText(3002, 34, "Hitpoints: 2550");
			player.getPackets().sendIComponentText(3002, 33, "Combat Level: 596");
			player.getPackets().sendIComponentText(3002, 111, "Difficulty: Medium");
			player.getPackets().sendIComponentText(3002, 116, "Attack: All styles");
			player.getPackets().sendIComponentText(3002, 117, "Killcount: " + player.SaradominKills + "");
			player.getPackets().sendIComponentText(3002, 138, "Weakness: Melee");
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(11706, 1));
			player.getDmm().getContainer().add(new Item(11730, 1));
			player.getDmm().getContainer().add(new Item(25031, 1));
			player.getDmm().getContainer().add(new Item(25034, 1));
			player.getDmm().getContainer().add(new Item(25028, 1));
			player.getDmm().getContainer().add(new Item(25037, 1));
			player.getDmm().getContainer().add(new Item(2677, 1));
			player.getDmm().getContainer().add(new Item(6199, 1));
			player.getDmm().getContainer().add(new Item(29541, 1));
			player.getPackets().sendInterSetItemsOptionsScript(3002, 72, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3002, 72, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 110:
			player.bosstelecomponent = 12;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(3002, 11, "WildyWyrm");
			player.getPackets().sendIComponentText(3002, 34, "Hitpoints: 10000");
			player.getPackets().sendIComponentText(3002, 33, "Combat Level: 382");
			player.getPackets().sendIComponentText(3002, 111, "Difficulty: Medium <col=ff0000>PVP</col>");
			player.getPackets().sendIComponentText(3002, 116, "Attack: All 3");
			player.getPackets().sendIComponentText(3002, 117, "Killcount: " + player.WwyrmKills + "");
			player.getPackets().sendIComponentText(3002, 138, "Weakness: Melee");
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(15259, 1));
			player.getDmm().getContainer().add(new Item(13858, 1));
			player.getDmm().getContainer().add(new Item(13861, 1));
			player.getDmm().getContainer().add(new Item(13864, 1));
			player.getDmm().getContainer().add(new Item(13867, 1));
			player.getDmm().getContainer().add(new Item(29559, 1));
			player.getDmm().getContainer().add(new Item(29560, 1));
			player.getDmm().getContainer().add(new Item(29561, 1));
			player.getDmm().getContainer().add(new Item(29562, 1));
			player.getDmm().getContainer().add(new Item(2677, 1));
			player.getDmm().getContainer().add(new Item(29541, 1));
			player.getPackets().sendInterSetItemsOptionsScript(3002, 72, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3002, 72, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 118:
			player.bosstelecomponent = 13;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(3002, 11, "Vorkath");
			player.getPackets().sendIComponentText(3002, 34, "Hitpoints: 7500");
			player.getPackets().sendIComponentText(3002, 33, "Combat Level: 732");
			player.getPackets().sendIComponentText(3002, 111, "Difficulty: Hard");
			player.getPackets().sendIComponentText(3002, 116, "Attack: All 3");
			player.getPackets().sendIComponentText(3002, 117, "Killcount: " + player.VorkathKills + "");
			player.getPackets().sendIComponentText(3002, 138, "Weakness: Ranged");
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(29191, 1));
			player.getDmm().getContainer().add(new Item(29192, 1));
			player.getDmm().getContainer().add(new Item(29188, 1));
			player.getDmm().getContainer().add(new Item(29186, 1));
			player.getDmm().getContainer().add(new Item(29190, 1));
			player.getDmm().getContainer().add(new Item(2677, 1));
			player.getPackets().sendInterSetItemsOptionsScript(3002, 72, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3002, 72, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 119:
			player.bosstelecomponent = 14;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(3002, 11, "Demi God Trio");
			player.getPackets().sendIComponentText(3002, 34, "Hitpoints: 11000 (33000)");
			player.getPackets().sendIComponentText(3002, 33, "Combat Level: 138");
			player.getPackets().sendIComponentText(3002, 111, "Difficulty: Hard");
			player.getPackets().sendIComponentText(3002, 116, "Attack: All 3");
			player.getPackets().sendIComponentText(3002, 117, "Killcount: " + player.Demigodkills + "");
			player.getPackets().sendIComponentText(3002, 138, "Weakness: None");
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(29290, 1));
			player.getDmm().getContainer().add(new Item(29289, 1));
			player.getDmm().getContainer().add(new Item(29288, 1));
			player.getPackets().sendInterSetItemsOptionsScript(3002, 72, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3002, 72, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 120:
			player.bosstelecomponent = 15;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(3002, 11, "Vorago");
			player.getPackets().sendIComponentText(3002, 34, "Hitpoints: 60000");
			player.getPackets().sendIComponentText(3002, 33, "Combat Level: 2400-3170");
			player.getPackets().sendIComponentText(3002, 111, "Difficulty: Very Hard");
			player.getPackets().sendIComponentText(3002, 116, "Attack: All styles");
			player.getPackets().sendIComponentText(3002, 117, "Killcount: " + player.VoragoKills + "");
			player.getPackets().sendIComponentText(3002, 138, "Weakness: Magic");
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(6571, 1));
			player.getDmm().getContainer().add(new Item(13899, 1));
			player.getDmm().getContainer().add(new Item(29943, 1));
			player.getDmm().getContainer().add(new Item(29944, 1));
			player.getDmm().getContainer().add(new Item(29654, 1));
			player.getDmm().getContainer().add(new Item(2677, 1));
			player.getDmm().getContainer().add(new Item(6199, 1));
			player.getPackets().sendInterSetItemsOptionsScript(3002, 72, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3002, 72, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 121:
			player.bosstelecomponent = 16;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(3002, 11, "AOD");
			player.getPackets().sendIComponentText(3002, 34, "Hitpoints: 12000");
			player.getPackets().sendIComponentText(3002, 33, "Combat Level: 525");
			player.getPackets().sendIComponentText(3002, 111, "Difficulty: Easy");
			player.getPackets().sendIComponentText(3002, 116, "Attack: Melee");
			player.getPackets().sendIComponentText(3002, 117, "Killcount: " + player.AvaKills + "");
			player.getPackets().sendIComponentText(3002, 138, "Weakness: Melee");
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(14641, 1));
			player.getDmm().getContainer().add(new Item(14642, 1));
			player.getDmm().getContainer().add(new Item(14654, 1));
			player.getDmm().getContainer().add(new Item(14651, 1));
			player.getDmm().getContainer().add(new Item(29869, 1));
			player.getDmm().getContainer().add(new Item(2677, 1));
			player.getPackets().sendInterSetItemsOptionsScript(3002, 72, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3002, 72, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 122:
			player.bosstelecomponent = 17;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(3002, 11, "Night-Gazer");
			player.getPackets().sendIComponentText(3002, 34, "Hitpoints: 10000");
			player.getPackets().sendIComponentText(3002, 33, "Combat Level: 318");
			player.getPackets().sendIComponentText(3002, 111, "Difficulty: Medium");
			player.getPackets().sendIComponentText(3002, 116, "Attack: Melee & Range");
			player.getPackets().sendIComponentText(3002, 117, "Killcount: " + player.GazerKills + "");
			player.getPackets().sendIComponentText(3002, 138, "Weakness: Melee");
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(24354, 1));
			player.getDmm().getContainer().add(new Item(24355, 1));
			player.getDmm().getContainer().add(new Item(24356, 1));
			player.getDmm().getContainer().add(new Item(24357, 1));
			player.getDmm().getContainer().add(new Item(24358, 1));
			player.getDmm().getContainer().add(new Item(24359, 1));
			player.getDmm().getContainer().add(new Item(24360, 1));
			player.getDmm().getContainer().add(new Item(24361, 1));
			player.getDmm().getContainer().add(new Item(24362, 1));
			player.getDmm().getContainer().add(new Item(24363, 1));
			player.getPackets().sendInterSetItemsOptionsScript(3002, 72, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3002, 72, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 123:
			player.bosstelecomponent = 18;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(3002, 11, "Bad Santa");
			player.getPackets().sendIComponentText(3002, 34, "Hitpoints: 25000");
			player.getPackets().sendIComponentText(3002, 33, "Combat Level: 138");
			player.getPackets().sendIComponentText(3002, 111, "Difficulty: Medium");
			player.getPackets().sendIComponentText(3002, 116, "Attack: Mage");
			player.getPackets().sendIComponentText(3002, 117, "Killcount: " + player.SantaKills + "");
			player.getPackets().sendIComponentText(3002, 138, "Weakness: No Weakness");
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(29877, 1));
			player.getDmm().getContainer().add(new Item(10501, 1));
			player.getDmm().getContainer().add(new Item(2677, 1));
			player.getPackets().sendInterSetItemsOptionsScript(3002, 72, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3002, 72, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 124:
			player.bosstelecomponent = 19;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(3002, 11, "Dryax");
			player.getPackets().sendIComponentText(3002, 34, "Hitpoints: 30000");
			player.getPackets().sendIComponentText(3002, 33, "Combat Level: 138");
			player.getPackets().sendIComponentText(3002, 111, "Difficulty: Medium");
			player.getPackets().sendIComponentText(3002, 116, "Attack: Melee");
			player.getPackets().sendIComponentText(3002, 117, "Killcount: ");
			player.getPackets().sendIComponentText(3002, 138, "Weakness: Magic");
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(29866, 1));
			player.getDmm().getContainer().add(new Item(14484, 1));
			player.getDmm().getContainer().add(new Item(13754, 1));
			player.getDmm().getContainer().add(new Item(11286, 1));
			player.getDmm().getContainer().add(new Item(16753, 1));
			player.getDmm().getContainer().add(new Item(16863, 1));
			player.getDmm().getContainer().add(new Item(17235, 1));
			player.getDmm().getContainer().add(new Item(13902, 1));
			player.getPackets().sendInterSetItemsOptionsScript(3002, 72, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3002, 72, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 125:
			player.bosstelecomponent = 20;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(3002, 11, "Kalphite King");
			player.getPackets().sendIComponentText(3002, 34, "Hitpoints: 25000");
			player.getPackets().sendIComponentText(3002, 33, "Combat Level: 2500");
			player.getPackets().sendIComponentText(3002, 111, "Difficulty: Hard");
			player.getPackets().sendIComponentText(3002, 116, "Attack: All styles");
			player.getPackets().sendIComponentText(3002, 117, "Killcount: " + player.KkingKills + "");
			player.getPackets().sendIComponentText(3002, 138, "Weakness: Magic");
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(29931, 30));
			player.getDmm().getContainer().add(new Item(29930, 30));
			player.getDmm().getContainer().add(new Item(29929, 30));
			player.getDmm().getContainer().add(new Item(2677, 1));
			player.getPackets().sendInterSetItemsOptionsScript(3002, 72, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3002, 72, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 126:
			player.bosstelecomponent = 21;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(3002, 11, "Hope Devourer");
			player.getPackets().sendIComponentText(3002, 34, "Hitpoints: 65000");
			player.getPackets().sendIComponentText(3002, 33, "Combat Level: 534");
			player.getPackets().sendIComponentText(3002, 111, "Difficulty: Very Hard");
			player.getPackets().sendIComponentText(3002, 116, "Attack: All styles");
			player.getPackets().sendIComponentText(3002, 117, "Killcount: " + player.DrygonKills + "");
			player.getPackets().sendIComponentText(3002, 138, "Weakness: Melee");
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(7808, 1));
			player.getDmm().getContainer().add(new Item(7806, 1));
			player.getDmm().getContainer().add(new Item(7809, 1));
			player.getDmm().getContainer().add(new Item(6571, 1));
			player.getDmm().getContainer().add(new Item(29785, 1));
			player.getDmm().getContainer().add(new Item(29654, 1));
			player.getPackets().sendInterSetItemsOptionsScript(3002, 72, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3002, 72, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 127:
			player.bosstelecomponent = 22;
			/**
			 * 
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(3002, 11, "Party Demon");
			player.getPackets().sendIComponentText(3002, 34, "Hitpoints: 100000");
			player.getPackets().sendIComponentText(3002, 33, "Combat Level: 999");
			player.getPackets().sendIComponentText(3002, 111, "Difficulty: Hard");
			player.getPackets().sendIComponentText(3002, 116, "Attack: Magic");
			player.getPackets().sendIComponentText(3002, 117, "Killcount: " + player.PdemonKills + "");
			player.getPackets().sendIComponentText(3002, 138, "Weakness: Magic");
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(29713, 1));
			player.getDmm().getContainer().add(new Item(29714, 1));
			player.getDmm().getContainer().add(new Item(29715, 1));
			player.getDmm().getContainer().add(new Item(29710, 1));
			player.getDmm().getContainer().add(new Item(29711, 1));
			player.getDmm().getContainer().add(new Item(29712, 1));
			player.getPackets().sendInterSetItemsOptionsScript(3002, 72, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3002, 72, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 128:
			player.bosstelecomponent = 23;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(3002, 11, "Zulrah");
			player.getPackets().sendIComponentText(3002, 34, "Hitpoints: 5000");
			player.getPackets().sendIComponentText(3002, 33, "Combat Level: 725");
			player.getPackets().sendIComponentText(3002, 111, "Difficulty: Medium");
			player.getPackets().sendIComponentText(3002, 116, "Attack: All styles");
			player.getPackets().sendIComponentText(3002, 117, "Killcount: " + player.ZulrahKills + "");
			player.getPackets().sendIComponentText(3002, 138, "Weakness: No Weakness");
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(29469, 1));
			player.getDmm().getContainer().add(new Item(29468, 1));
			player.getDmm().getContainer().add(new Item(29467, 1));
			player.getDmm().getContainer().add(new Item(29465, 1));
			player.getDmm().getContainer().add(new Item(29043, 1));
			player.getDmm().getContainer().add(new Item(29042, 1));
			player.getPackets().sendInterSetItemsOptionsScript(3002, 72, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3002, 72, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 129:
			player.bosstelecomponent = 24;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(3002, 11, "Necrolord");
			player.getPackets().sendIComponentText(3002, 34, "Hitpoints: 16280");
			player.getPackets().sendIComponentText(3002, 33, "Combat Level: 495");
			player.getPackets().sendIComponentText(3002, 111, "Difficulty: Hard");
			player.getPackets().sendIComponentText(3002, 116, "Attack: Range & Magic");
			player.getPackets().sendIComponentText(3002, 117, "Killcount: " + player.NecroLordKills + "");
			player.getPackets().sendIComponentText(3002, 138, "Weakness: Melee");
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(25202, 1));
			player.getDmm().getContainer().add(new Item(20763, 1));
			player.getDmm().getContainer().add(new Item(20764, 1));
			player.getDmm().getContainer().add(new Item(24709, 1));
			player.getDmm().getContainer().add(new Item(24710, 1));
			player.getDmm().getContainer().add(new Item(6199, 1));
			player.getPackets().sendInterSetItemsOptionsScript(3002, 72, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3002, 72, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 130:
			player.bosstelecomponent = 25;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(3002, 11, "Yk'Lagor the Thunderous");
			player.getPackets().sendIComponentText(3002, 34, "Hitpoints: 12500");
			player.getPackets().sendIComponentText(3002, 33, "Combat Level: 141");
			player.getPackets().sendIComponentText(3002, 111, "Difficulty: Medium");
			player.getPackets().sendIComponentText(3002, 116, "Attack: Melee & Magic");
			player.getPackets().sendIComponentText(3002, 117, "Killcount: " + player.ThunderousKills + "");
			player.getPackets().sendIComponentText(3002, 138, "Weakness: Magic");
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(29594, 1));
			player.getDmm().getContainer().add(new Item(6199, 1));
			player.getDmm().getContainer().add(new Item(2677, 1));
			player.getDmm().getContainer().add(new Item(29587, 1));
			player.getDmm().getContainer().add(new Item(29592, 1));
			player.getDmm().getContainer().add(new Item(29654, 1));
			player.getPackets().sendInterSetItemsOptionsScript(3002, 72, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3002, 72, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 131:
			player.bosstelecomponent = 26;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(3002, 11, "Sunfreet");
			player.getPackets().sendIComponentText(3002, 34, "Hitpoints: 18000");
			player.getPackets().sendIComponentText(3002, 33, "Combat Level: 530");
			player.getPackets().sendIComponentText(3002, 111, "Difficulty : Hard <col=ff0000>PVP</col>");
			player.getPackets().sendIComponentText(3002, 116, "Attack: Melee & Magic");
			player.getPackets().sendIComponentText(3002, 117, "Killcount: " + player.SunfreetKills + "");
			player.getPackets().sendIComponentText(3002, 138, "Weakness: Melee");
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(6571, 1));
			player.getDmm().getContainer().add(new Item(21371, 1));
			player.getDmm().getContainer().add(new Item(9006, 1));
			player.getDmm().getContainer().add(new Item(29654, 1));
			player.getDmm().getContainer().add(new Item(29782, 1));
			player.getPackets().sendInterSetItemsOptionsScript(3002, 72, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3002, 72, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 132:
			player.bosstelecomponent = 27;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(3002, 11, "Anivia");
			player.getPackets().sendIComponentText(3002, 34, "Hitpoints: 22000");
			player.getPackets().sendIComponentText(3002, 33, "Combat Level: 102");
			player.getPackets().sendIComponentText(3002, 111, "Difficulty: Hard");
			player.getPackets().sendIComponentText(3002, 116, "Attack: Melee & Magic");
			player.getPackets().sendIComponentText(3002, 117, "Killcount: " + player.AniviaKills + "");
			player.getPackets().sendIComponentText(3002, 138, "Weakness: Melee");
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(29565, 1));
			player.getDmm().getContainer().add(new Item(29564, 1));
			player.getDmm().getContainer().add(new Item(29563, 1));
			player.getDmm().getContainer().add(new Item(29728, 1));
			player.getDmm().getContainer().add(new Item(6571, 1));
			player.getDmm().getContainer().add(new Item(2677, 1));
			player.getDmm().getContainer().add(new Item(14808, 1));
			player.getDmm().getContainer().add(new Item(6199, 1));
			player.getDmm().getContainer().add(new Item(29654, 1));
			player.getPackets().sendInterSetItemsOptionsScript(3002, 72, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3002, 72, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 133:
			player.bosstelecomponent = 28;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(3002, 11, "Sliske");
			player.getPackets().sendIComponentText(3002, 34, "Hitpoints: 15500");
			player.getPackets().sendIComponentText(3002, 33, "Combat Level: 666");
			player.getPackets().sendIComponentText(3002, 111, "Difficulty: Very Hard <col=ff0000>PVP</col>");
			player.getPackets().sendIComponentText(3002, 116, "Attack: All styles");
			player.getPackets().sendIComponentText(3002, 117, "Killcount: " + player.SliskeKills + "");
			player.getPackets().sendIComponentText(3002, 138, "Weakness: Melee");
			/**
			 * 
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(29649, 1));
			player.getDmm().getContainer().add(new Item(20786, 1));
			player.getDmm().getContainer().add(new Item(29680, 1));
			player.getDmm().getContainer().add(new Item(15259, 1));
			player.getDmm().getContainer().add(new Item(6739, 1));
			player.getPackets().sendInterSetItemsOptionsScript(3002, 72, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3002, 72, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 134:
			player.bosstelecomponent = 29;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(3002, 11, "Kalphite Queen");
			player.getPackets().sendIComponentText(3002, 34, "Hitpoints: 5100");
			player.getPackets().sendIComponentText(3002, 33, "Combat Level: 333");
			player.getPackets().sendIComponentText(3002, 111, "Difficulty: Easy");
			player.getPackets().sendIComponentText(3002, 116, "Attack: Range & Magic");
			player.getPackets().sendIComponentText(3002, 117, "Killcount: " + player.KqKills + "");
			player.getPackets().sendIComponentText(3002, 138, "Weakness: Melee");
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(7158, 1));
			player.getDmm().getContainer().add(new Item(3140, 1));
			player.getDmm().getContainer().add(new Item(29541, 1));
			player.getPackets().sendInterSetItemsOptionsScript(3002, 72, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3002, 72, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 135:
			player.bosstelecomponent = 30;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(3002, 11, "Kraken");
			player.getPackets().sendIComponentText(3002, 34, "Hitpoints: 2550");
			player.getPackets().sendIComponentText(3002, 33, "Combat Level: 291");
			player.getPackets().sendIComponentText(3002, 111, "Difficulty: Medium");
			player.getPackets().sendIComponentText(3002, 116, "Attack: Magic");
			player.getPackets().sendIComponentText(3002, 117, "Killcount: " + player.KrakenKills + "");
			player.getPackets().sendIComponentText(3002, 138, "Weakness: Magic");
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(29463, 1));
			player.getDmm().getContainer().add(new Item(29461, 1));
			player.getDmm().getContainer().add(new Item(29464, 1));
			player.getDmm().getContainer().add(new Item(2677, 1));
			player.getPackets().sendInterSetItemsOptionsScript(3002, 72, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3002, 72, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 136:
			player.bosstelecomponent = 31;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(3002, 11, "Abyssal Sire");
			player.getPackets().sendIComponentText(3002, 34, "Hitpoints: 4000");
			player.getPackets().sendIComponentText(3002, 33, "Combat Level: 350");
			player.getPackets().sendIComponentText(3002, 111, "Difficulty: Medium");
			player.getPackets().sendIComponentText(3002, 116, "Attack: Melee");
			player.getPackets().sendIComponentText(3002, 117, "Killcount: " + player.SireKills + "");
			player.getPackets().sendIComponentText(3002, 138, "Weakness: Melee");
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(29441, 1));
			player.getDmm().getContainer().add(new Item(29444, 1));
			player.getDmm().getContainer().add(new Item(29445, 1));
			player.getDmm().getContainer().add(new Item(29446, 1));
			player.getDmm().getContainer().add(new Item(4151, 1));
			player.getDmm().getContainer().add(new Item(29443, 1));
			player.getDmm().getContainer().add(new Item(2677, 1));
			player.getPackets().sendInterSetItemsOptionsScript(3002, 72, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3002, 72, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 137:
			player.bosstelecomponent = 32;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(3002, 11, "Cerberus");
			player.getPackets().sendIComponentText(3002, 34, "Hitpoints: 6000");
			player.getPackets().sendIComponentText(3002, 33, "Combat Level: 318");
			player.getPackets().sendIComponentText(3002, 111, "Difficulty: Medium");
			player.getPackets().sendIComponentText(3002, 116, "Attack: All Styles");
			player.getPackets().sendIComponentText(3002, 117, "Killcount: " + player.CerberusKills + "");
			player.getPackets().sendIComponentText(3002, 138, "Weakness: Melee");
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(29422, 1));
			player.getDmm().getContainer().add(new Item(29421, 1));
			player.getDmm().getContainer().add(new Item(29420, 1));
			player.getDmm().getContainer().add(new Item(29419, 1));
			player.getDmm().getContainer().add(new Item(989, 1));
			player.getDmm().getContainer().add(new Item(2677, 1));
			player.getPackets().sendInterSetItemsOptionsScript(3002, 72, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3002, 72, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 139:
			player.bosstelecomponent = 33;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(3002, 11, "Sirenic");
			player.getPackets().sendIComponentText(3002, 34, "Hitpoints: 18000");
			player.getPackets().sendIComponentText(3002, 33, "Combat Level: 285");
			player.getPackets().sendIComponentText(3002, 111, "Difficulty: Medium  <col=ff0000>PVP</col>");
			player.getPackets().sendIComponentText(3002, 116, "Attack: Magic");
			player.getPackets().sendIComponentText(3002, 117, "Killcount: " + player.SirenicKills + "");
			player.getPackets().sendIComponentText(3002, 138, "Weakness: Melee");
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(29887, 1));
			player.getDmm().getContainer().add(new Item(29541, 1));
			player.getPackets().sendInterSetItemsOptionsScript(3002, 72, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3002, 72, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 140:
			player.bosstelecomponent = 34;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(3002, 11, "Callisto");
			player.getPackets().sendIComponentText(3002, 34, "Hitpoints: 2550");
			player.getPackets().sendIComponentText(3002, 33, "Combat Level: 470");
			player.getPackets().sendIComponentText(3002, 111, "Difficulty: Medium  <col=ff0000>PVP</col>");
			player.getPackets().sendIComponentText(3002, 116, "Attack: Melee & Magic");
			player.getPackets().sendIComponentText(3002, 117, "Killcount: " + player.CallistoKills + "");
			player.getPackets().sendIComponentText(3002, 138, "Weakness: Melee");
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(29382, 1));
			player.getDmm().getContainer().add(new Item(2677, 1));
			player.getPackets().sendInterSetItemsOptionsScript(3002, 72, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3002, 72, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 141:
			player.bosstelecomponent = 35;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(3002, 11, "Venenatis");
			player.getPackets().sendIComponentText(3002, 34, "Hitpoints: 2550");
			player.getPackets().sendIComponentText(3002, 33, "Combat Level: 464");
			player.getPackets().sendIComponentText(3002, 111, "Difficulty: Medium  <col=ff0000>PVP</col>");
			player.getPackets().sendIComponentText(3002, 116, "Attack: Melee & Magic");
			player.getPackets().sendIComponentText(3002, 117, "Killcount: " + player.VenenatisKills + "");
			player.getPackets().sendIComponentText(3002, 138, "Weakness: Melee");
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(29316, 1));
			player.getDmm().getContainer().add(new Item(15259, 1));
			player.getDmm().getContainer().add(new Item(2677, 1));
			player.getPackets().sendInterSetItemsOptionsScript(3002, 72, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3002, 72, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 142:
			player.bosstelecomponent = 36;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(3002, 11, "Chaos Fanatic");
			player.getPackets().sendIComponentText(3002, 34, "Hitpoints: 2250");
			player.getPackets().sendIComponentText(3002, 33, "Combat Level: 202");
			player.getPackets().sendIComponentText(3002, 111, "Difficulty: Medium  <col=ff0000>PVP</col>");
			player.getPackets().sendIComponentText(3002, 116, "Attack: Magic");
			player.getPackets().sendIComponentText(3002, 117, "Killcount: " + player.FanaticKills + "");
			player.getPackets().sendIComponentText(3002, 138, "Weakness: Range");
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(29313, 1));
			player.getDmm().getContainer().add(new Item(29309, 1));
			player.getDmm().getContainer().add(new Item(2677, 1));
			player.getPackets().sendInterSetItemsOptionsScript(3002, 72, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3002, 72, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 143:
			player.bosstelecomponent = 37;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(3002, 11, "Crazy Archaeologist");
			player.getPackets().sendIComponentText(3002, 34, "Hitpoints: 2250");
			player.getPackets().sendIComponentText(3002, 33, "Combat Level: 204");
			player.getPackets().sendIComponentText(3002, 111, "Difficulty: Medium  <col=ff0000>PVP</col>");
			player.getPackets().sendIComponentText(3002, 116, "Attack: Range & Melee");
			player.getPackets().sendIComponentText(3002, 117, "Killcount: " + player.CrazyArcKills + "");
			player.getPackets().sendIComponentText(3002, 138, "Weakness: Melee");
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(29312, 1));
			player.getDmm().getContainer().add(new Item(29308, 1));
			player.getDmm().getContainer().add(new Item(2677, 1));
			player.getPackets().sendInterSetItemsOptionsScript(3002, 72, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3002, 72, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 144:
			player.bosstelecomponent = 38;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(3002, 11, "Scorpia");
			player.getPackets().sendIComponentText(3002, 34, "Hitpoints: 2000");
			player.getPackets().sendIComponentText(3002, 33, "Combat Level: 225");
			player.getPackets().sendIComponentText(3002, 111, "Difficulty: Medium  <col=ff0000>PVP</col>");
			player.getPackets().sendIComponentText(3002, 116, "Attack: Melee");
			player.getPackets().sendIComponentText(3002, 117, "Killcount: " + player.ScorpiaKills + "");
			player.getPackets().sendIComponentText(3002, 138, "Weakness: Magic");
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(29311, 1));
			player.getDmm().getContainer().add(new Item(29307, 1));
			player.getDmm().getContainer().add(new Item(2677, 1));
			player.getPackets().sendInterSetItemsOptionsScript(3002, 72, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3002, 72, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 145:
			player.bosstelecomponent = 39;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(3002, 11, "Vet'ion");
			player.getPackets().sendIComponentText(3002, 34, "Hitpoints: 2550");
			player.getPackets().sendIComponentText(3002, 33, "Combat Level: 454");
			player.getPackets().sendIComponentText(3002, 111, "Difficulty: Medium  <col=ff0000>PVP</col>");
			player.getPackets().sendIComponentText(3002, 116, "Attack: Melee & Magic");
			player.getPackets().sendIComponentText(3002, 117, "Killcount: " + player.VetionKills + "");
			player.getPackets().sendIComponentText(3002, 138, "Weakness: Crush");
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(29302, 1));
			player.getDmm().getContainer().add(new Item(15259, 1));
			player.getDmm().getContainer().add(new Item(2677, 1));
			player.getPackets().sendInterSetItemsOptionsScript(3002, 72, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3002, 72, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 146:
			player.bosstelecomponent = 40;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(3002, 11, "Tormented Demons");
			player.getPackets().sendIComponentText(3002, 34, "Hitpoints: 3260");
			player.getPackets().sendIComponentText(3002, 33, "Combat Level: 450");
			player.getPackets().sendIComponentText(3002, 111, "Difficulty: Medium");
			player.getPackets().sendIComponentText(3002, 116, "Attack: All styles");
			player.getPackets().sendIComponentText(3002, 117, "Killcount: " + player.TdsKills + "");
			player.getPackets().sendIComponentText(3002, 138, "Weakness: No Weakness");
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(14484, 1));
			player.getDmm().getContainer().add(new Item(14472, 1));
			player.getDmm().getContainer().add(new Item(14474, 1));
			player.getDmm().getContainer().add(new Item(14476, 1));
			player.getPackets().sendInterSetItemsOptionsScript(3002, 72, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3002, 72, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 147:
			player.bosstelecomponent = 41;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(3002, 11, "Angry Bunny");
			player.getPackets().sendIComponentText(3002, 34, "Hitpoints: 40,000");
			player.getPackets().sendIComponentText(3002, 33, "Combat Level: 2019");
			player.getPackets().sendIComponentText(3002, 111, "Difficulty: Medium");
			player.getPackets().sendIComponentText(3002, 116, "Attack: All styles");
			player.getPackets().sendIComponentText(3002, 117, "Killcount: " + player.AngryEasterBunnyKills + "");
			player.getPackets().sendIComponentText(3002, 138, "Weakness: No Weakness");
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(29102, 1));
			player.getDmm().getContainer().add(new Item(14728, 1));
			player.getDmm().getContainer().add(new Item(1961, 1));
			player.getDmm().getContainer().add(new Item(4566, 1));
			player.getDmm().getContainer().add(new Item(15673, 1));
			player.getDmm().getContainer().add(new Item(29101, 1));
			player.getDmm().getContainer().add(new Item(24149, 1));
			player.getDmm().getContainer().add(new Item(24150, 1));
			player.getPackets().sendInterSetItemsOptionsScript(3002, 72, 100, 6, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3002, 72, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		case 148:
			player.bosstelecomponent = 42;
			/**
			 * Right hand side tab
			 */
			player.getPackets().sendIComponentText(3002, 11, "The 3 Amigos");
			player.getPackets().sendIComponentText(3002, 34, "Hitpoints: 8,000 x 3");
			player.getPackets().sendIComponentText(3002, 33, "Combat Level: Varies");
			player.getPackets().sendIComponentText(3002, 111, "Difficulty: Hard");
			player.getPackets().sendIComponentText(3002, 116, "Attack: All styles");
			player.getPackets().sendIComponentText(3002, 117, "Killcount: " + player.the3amigosKills + "");
			player.getPackets().sendIComponentText(3002, 138, "Weakness: Combat Triangle");
			/**
			 * Left bottom
			 */
			player.getDmm().getContainer().clear();
			player.getDmm().getContainer().add(new Item(28905, 1));
			player.getDmm().getContainer().add(new Item(28906, 1));
			player.getDmm().getContainer().add(new Item(28907, 1));
			player.getDmm().getContainer().add(new Item(2677, 1));
			player.getPackets().sendInterSetItemsOptionsScript(3002, 72, 100, 6, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3002, 72, 0, 10, 0, 1, 2, 3);
			player.getDmm().getContainer().shift();
			player.getPackets().sendItems(containerKey, player.getDmm().getContainer());
			break;
		}
		
	}
	
	
	
	public static void HandleTeleport(Player player) {
		if (player.getControlerManager().getControler() != null) {
			player.sendMessage(Colors.red+"You cannot use this in this area.");
			return;
		}
		switch (player.bosstelecomponent) {
		case 0:
			if (player.getFamiliar() != null || player.getPet() != null || Summoning.hasPouch(player) || Pets.hasPet(player)) {
				player.sendMessage("You cannot bring a familiar to this boss.");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2966,
					4383, 2));
			break;
		case 1:
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3067,
					10255, 0));
			break;
		case 2:
			if (player.getSkills().getLevel(Skills.SLAYER) < 10) {
				player.getPackets().sendGameMessage(
						"You need a Slayer level of 10 to enter.");
				return;
			} else {
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(1199, 6499, 0));
			}
			break;
		case 3:
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3096,
					5539, 0));
			break;
		case 4:
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(1761, 5186, 0));
			break;
		case 5:
			player.stopAll();
			player.setNextWorldTile(new WorldTile(2905, 5203, 0));
			player.getControlerManager().startControler("GodWars");
			break;
		case 6:
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(1353, 10258, 0));
				break;

		case 7:
			if (!player.getInventory().contains(29481)) {
				player.sendMessage("You need a barrows Totem to access this. Normal Barrows drop them.");
				return;
			}
			if(player.getFamiliar() != null || player.getPet() != null || Summoning.hasPouch(player) || Pets.hasPet(player)) {
				player.sendMessage("You cannot bring a familiar to this boss.");
				return;
			}
			if (player.getSkills().getLevel(Skills.SLAYER) < 95) {
				player.sendMessage("You need a slayer level of 95 to access this minigame.");
				return;
			} else {
			player.getInventory().deleteItem(29481, 1);
				player.getInterfaceManager().closeInterfaceCustom();
				BossInstance instance = null;
				instance = new Rot6Instance(player);
				BossInstanceManager.SINGLETON.add(instance);
			}
			break;
		case 8:
			if (player.gwdperk != true && !Settings.EVENT_SPOTLIGHT.equalsIgnoreCase("Free entry to gwd")) {
				if (player.gwdkc < 20) {
					player.sendMessage("You need 20 GWD kc to enter this boss.");
					Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2927, 5294, 0));
					player.getInterfaceManager().closeChatBoxInterface();
					player.getInterfaceManager().closeOverlay(true);
					return;
				}
			}
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2868,
					5354, 0));
			if (player.gwdperk != true && !Settings.EVENT_SPOTLIGHT.equalsIgnoreCase("Free entry to gwd")) {
				player.gwdkc -= 20;
			}
			break;
		case 9:
			if (player.gwdperk != true && !Settings.EVENT_SPOTLIGHT.equalsIgnoreCase("Free entry to gwd")) {
				if (player.gwdkc < 20) {
					player.sendMessage("You need 20 GWD kc to enter this boss.");
					Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2927, 5294, 0));
					player.getInterfaceManager().closeChatBoxInterface();
					player.getInterfaceManager().closeOverlay(true);
					return;
				}
			}
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2925,
					5330, 0));
			if (player.gwdperk != true && !Settings.EVENT_SPOTLIGHT.equalsIgnoreCase("Free entry to gwd")) {
				player.gwdkc -= 20;
			}
			break;
		case 10:
			if (player.gwdperk != true && !Settings.EVENT_SPOTLIGHT.equalsIgnoreCase("Free entry to gwd")) {
				if (player.gwdkc < 20) {
					player.sendMessage("You need 20 GWD kc to enter this boss.");
					Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2927, 5294, 0));
					player.getInterfaceManager().closeChatBoxInterface();
					player.getInterfaceManager().closeOverlay(true);
					return;
				}
			}
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2832, 5299, 0));
			if (player.gwdperk != true && !Settings.EVENT_SPOTLIGHT.equalsIgnoreCase("Free entry to gwd")) {
				player.gwdkc -= 20;
			}
			break;
		case 11:
			if (player.gwdperk != true && !Settings.EVENT_SPOTLIGHT.equalsIgnoreCase("Free entry to gwd")) {
				if (player.gwdkc < 20) {
					player.sendMessage("You need 20 GWD kc to enter this boss.");
					Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2927, 5294, 0));
					player.getInterfaceManager().closeChatBoxInterface();
					player.getInterfaceManager().closeOverlay(true);
					return;
				}
			}
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2922,
					5251, 0));
			if (player.gwdperk != true && !Settings.EVENT_SPOTLIGHT.equalsIgnoreCase("Free entry to gwd")) {
				player.gwdkc -= 20;
			}
			break;
		case 12:
			player.getInterfaceManager().closeInterfaceCustom();
			player.getDialogueManager().startDialogue("WildyWyrm");
			break;
		case 13:
			if (player.getFamiliar() != null || Summoning.hasPouch(player)) {
				player.sendMessage("You cannot bring a familiar to this boss.");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			if (player.getMoneyPouch().getCoinAmount() < 100000) {
				player.sendMessage("You must have atleast 100k in your money pouch to pay the fee!");
				player.getInterfaceManager().closeInterfaceCustom();
				return;
			}
			player.coinamount -= 100000;
			Settings.GpSyncAmount += 100000;
			player.refreshMoneyPouch();
			BossInstance instance1 = null;
			player.getInterfaceManager().closeInterfaceCustom();
			instance1 = new VorkathInstance(player);
			BossInstanceManager.SINGLETON.add(instance1);
			break;
		case 14:
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2630, 5059, 0));
			break;
		case 15:
			if (player.getSkills().getLevel(Skills.SLAYER) < 95) {
				player.getPackets()
						.sendGameMessage(
								"You need a slayer level of 95 to kill Vorago.");
				return;
			} else {
			player.getInterfaceManager().closeInterfaceCustom();
			player.getDialogueManager().startDialogue("Voragofee");
			}
			break;
		case 16:
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(1807,
					3212, 0));
			break;
		case 17:
			if(player.getFamiliar() != null || player.getPet() != null || Summoning.hasPouch(player) || Pets.hasPet(player)) {
				player.sendMessage("You cannot bring a familiar to this boss.");
				return;
			} else {
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2413,
					3529, 0));
			}
			break;
		case 18:
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2648,
					10425, 0));
			break;
		case 19:
			if (player.getInventory().containsOneItem(29866)) {
				player.getInventory().deleteItem(29866, 1);
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2770,
						2980, 0));
				return;
			} else {
				player.sendMessage("<col=ff0000>You need a dryaxion key to enter this boss.");
			}
			break;
		case 20:
			if(player.getFamiliar() != null || player.getPet() != null || Summoning.hasPouch(player) || Pets.hasPet(player)) {
				player.sendMessage("This boss does not currently support familiars or pets.");
				return;
			} else {
			player.getInterfaceManager().closeInterfaceCustom();
			player.getDialogueManager().startDialogue("KkFee");
			}
			break;
		case 21:
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2260,
					3602, 0));
			break;
		case 22:
			Calendar calendar = Calendar.getInstance();
			if (Settings.canteletopdemon != true) {
				player.sendMessage("You have no reason to go here yet.");
				return;
			} else {
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3249,
					9867, 0));
				if (Skills.getTotalLevel(player) < 1500) {
							player.getDialogueManager().startDialogue("SimpleMessage", "The Demon will not reward you for yout efforts.");
							return;
						}
			player.canlootpdemonchest = true;
			}
			break;
		case 23:
			player.getInterfaceManager().closeInterfaceCustom();
			BossInstance instance = null;
			instance = new ZulrahInstance(player);
			BossInstanceManager.SINGLETON.add(instance);
			break;
		case 24:
			if (!player.isVeteran()) {
				player.sendMessage("You must be a veteran to access this boss!");
				return;
			} else {
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3812,
					3060, 0));
			}
			break;
		case 25:
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2574,
					9500, 0));
			break;
		case 26:
			if (player.getSkills().getLevel(Skills.SLAYER) < 95) {
				player.getPackets()
						.sendGameMessage(
								"You need a slayer level of 95 to kill Sunfreet.");
				return;
			} else {
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2983,
					3958, 0));
			player.getControlerManager().startControler("Wilderness");
			}
			break;
		case 27:
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2788,
					3788, 0));
			break;
		case 28:
			player.getInterfaceManager().closeInterfaceCustom();
			player.getDialogueManager().startDialogue("SliskeConfirmation");
			break;
		case 29:
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3508,
					9493, 0));
			break;
		case 30:
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3694,
					5803, 0));
			break;
		case 31:
			player.getInterfaceManager().closeInterfaceCustom();
			instance = new AbyssalSire(player);
			BossInstanceManager.SINGLETON.add(instance);
			break;
		case 32:
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(1240, 1229, 0));
			// player.sendMessage(Colors.red + "Unfortunately the HD causes issues here, so
			// please turn ground decorations off to avoid visible glitches.");
			break;
		case 33:
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3335, 3894, 0));
			player.sendMessage("Head south to find Sirenic the Spider!");
			player.getControlerManager().startControler("Wilderness");
			break;
		case 34:
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3298, 3846, 0));
			player.getControlerManager().startControler("Wilderness");
			break;
		case 35:
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3353, 3757, 0));
			player.getControlerManager().startControler("Wilderness");
			break;
		case 36:
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2979, 3850, 0));
			player.getControlerManager().startControler("Wilderness");
			break;
		case 37:
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2975, 3697, 0));
			player.getControlerManager().startControler("Wilderness");
			break;
		case 38:
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3245, 10349, 0));
			player.getControlerManager().startControler("Wilderness");
			break;
		case 39:
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3253, 3807, 0));
			player.getControlerManager().startControler("Wilderness");
			break;
		case 40:
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2562, 5739, 0));
			break;
		case 41:
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3179, 9766, 0));
			break;
		case 42:
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(584, 4552, 0));
			break;
		}
	}
	
	

	
	
	
	
}