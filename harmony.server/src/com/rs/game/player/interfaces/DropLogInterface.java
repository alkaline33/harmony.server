package com.rs.game.player.interfaces;

import com.rs.game.player.Player;

/**
 * 
 * @author Mr_Joopz
 *
 */

public class DropLogInterface {

	public static void OpenInterface(Player player) {
		player.getInterfaceManager().sendInterface(3015);
		player.getPackets().sendIComponentText(3015, 2, "Collection Log");
		/**
		 * Left top
		 */
		player.getPackets().sendIComponentText(3015, 7, "Corporeal Beast");
		player.getPackets().sendIComponentText(3015, 8, "King Black Dragon");
		player.getPackets().sendIComponentText(3015, 9, "Bork");
		player.getPackets().sendIComponentText(3015, 10, "Tormented Demons");
		player.getPackets().sendIComponentText(3015, 11, "Nex");
		player.getPackets().sendIComponentText(3015, 12, "Glacors");
		player.getPackets().sendIComponentText(3015, 13, "Rise of the Six");
		player.getPackets().sendIComponentText(3015, 14, "Bandos");
		player.getPackets().sendIComponentText(3015, 15, "Zamorak");
		player.getPackets().sendIComponentText(3015, 16, "Armadyl");
		player.getPackets().sendIComponentText(3015, 17, "Saradomin");
		player.getPackets().sendIComponentText(3015, 18, "WildyWyrm");
		player.getPackets().sendIComponentText(3015, 19, "Hardmode Wyrm");
		player.getPackets().sendIComponentText(3015, 20, "Aquatic Wyrm");
		player.getPackets().sendIComponentText(3015, 21, "Vorago");
		player.getPackets().sendIComponentText(3015, 22, "AOD");
		player.getPackets().sendIComponentText(3015, 23, "Night-Gazer");
		player.getPackets().sendIComponentText(3015, 24, "Bad Santa");
		player.getPackets().sendIComponentText(3015, 25, "Dryax");
		player.getPackets().sendIComponentText(3015, 26, "Kalphite King");
		player.getPackets().sendIComponentText(3015, 27, "Hope Devourer");
		player.getPackets().sendIComponentText(3015, 28, "Party Demon");
		player.getPackets().sendIComponentText(3015, 29, "Zulrah");
		player.getPackets().sendIComponentText(3015, 30, "Necrolord");
		player.getPackets().sendIComponentText(3015, 31, "Thundeorus");
		player.getPackets().sendIComponentText(3015, 32, "Sunfreet");
		player.getPackets().sendIComponentText(3015, 33, "Anivia");
		player.getPackets().sendIComponentText(3015, 34, "Sliske");
		player.getPackets().sendIComponentText(3015, 35, "Kraken");
		player.getPackets().sendIComponentText(3015, 36, "Abyssal Sire");
		player.getPackets().sendIComponentText(3015, 37, "Cerberus");
		player.getPackets().sendIComponentText(3015, 38, "Sirenic");
		player.getPackets().sendIComponentText(3015, 39, "Callisto");
		player.getPackets().sendIComponentText(3015, 40, "Venenatis");
		player.getPackets().sendIComponentText(3015, 41, "Chaos Fanatic");
		player.getPackets().sendIComponentText(3015, 42, "Archaeologist");
		player.getPackets().sendIComponentText(3015, 43, "Scorpia");
		player.getPackets().sendIComponentText(3015, 44, "Vet'ion");
		player.getPackets().sendIComponentText(3015, 45, "Xeric raids");
		player.getPackets().sendIComponentText(3015, 46, "Blood raids");
		player.getPackets().sendIComponentText(3015, 47, "Temple of Light");
		player.getPackets().sendIComponentText(3015, 48, "Lizardman");
		player.getPackets().sendIComponentText(3015, 49, "Smoke Devil");
		player.getPackets().sendIComponentText(3015, 50, "Blink");
		player.getPackets().sendIComponentText(3015, 54, "Vorkath");
		player.getPackets().sendIComponentText(3015, 55, "Alchemical Hydra");
		player.getPackets().sendIComponentText(3015, 56, "Celestia");
		player.getPackets().sendIComponentText(3015, 57, "the Raptor");
		player.getPackets().sendIComponentText(3015, 58, "The 3 Amigos");
		/**
		 * Left bottom
		 */
		player.getPackets().sendInterSetItemsOptionsScript(3015, 53, 100, 8, 3, "Examine");
		player.getPackets().sendUnlockIComponentOptionSlots(3015, 53, 0, 10, 0, 1, 2, 3);
		player.getDropLogContainer().getContainer().shift();
		player.getPackets().sendItems(100, player.getCorpDropLog().getContainer());
	}

	public static void HandleButtons(Player player, int componentId) {
		// System.out.println(componentId);
		switch (componentId) {
		case 4:
			player.getInterfaceManager().closeInterfaceCustom();
			break;
		case 7:
			player.getPackets().sendInterSetItemsOptionsScript(3015, 53, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3015, 53, 0, 10, 0, 1, 2, 3);
			player.getDropLogContainer().getContainer().shift();
			player.getPackets().sendItems(100, player.getCorpDropLog().getContainer());
			break;
		case 8:
			player.getPackets().sendInterSetItemsOptionsScript(3015, 53, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3015, 53, 0, 10, 0, 1, 2, 3);
			player.getDropLogContainer().getContainer().shift();
			player.getPackets().sendItems(100, player.KBDgetDropLog().getContainer());
			break;
		case 10:
			player.getPackets().sendInterSetItemsOptionsScript(3015, 53, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3015, 53, 0, 10, 0, 1, 2, 3);
			player.getDropLogContainer().getContainer().shift();
			player.getPackets().sendItems(100, player.TdsgetDropLog().getContainer());
			break;
		case 11:
			player.getPackets().sendInterSetItemsOptionsScript(3015, 53, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3015, 53, 0, 10, 0, 1, 2, 3);
			player.getDropLogContainer().getContainer().shift();
			player.getPackets().sendItems(100, player.NexgetDropLog().getContainer());
			break;
		case 12:
			player.getPackets().sendInterSetItemsOptionsScript(3015, 53, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3015, 53, 0, 10, 0, 1, 2, 3);
			player.getDropLogContainer().getContainer().shift();
			player.getPackets().sendItems(100, player.GlacorgetDropLog().getContainer());
			break;
		case 13:
			player.getPackets().sendInterSetItemsOptionsScript(3015, 53, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3015, 53, 0, 10, 0, 1, 2, 3);
			player.getDropLogContainer().getContainer().shift();
			player.getPackets().sendItems(100, player.Rot6getDropLog().getContainer());
			break;
		case 14:
			player.getPackets().sendInterSetItemsOptionsScript(3015, 53, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3015, 53, 0, 10, 0, 1, 2, 3);
			player.getDropLogContainer().getContainer().shift();
			player.getPackets().sendItems(100, player.BandosgetDropLog().getContainer());
			break;
		case 15:
			player.getPackets().sendInterSetItemsOptionsScript(3015, 53, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3015, 53, 0, 10, 0, 1, 2, 3);
			player.getDropLogContainer().getContainer().shift();
			player.getPackets().sendItems(100, player.ZamorakgetDropLog().getContainer());
			break;
		case 16:
			player.getPackets().sendInterSetItemsOptionsScript(3015, 53, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3015, 53, 0, 10, 0, 1, 2, 3);
			player.getDropLogContainer().getContainer().shift();
			player.getPackets().sendItems(100, player.ArmadylgetDropLog().getContainer());
			break;
		case 17:
			player.getPackets().sendInterSetItemsOptionsScript(3015, 53, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3015, 53, 0, 10, 0, 1, 2, 3);
			player.getDropLogContainer().getContainer().shift();
			player.getPackets().sendItems(100, player.SaradomingetDropLog().getContainer());
			break;
		case 18:
			player.getPackets().sendInterSetItemsOptionsScript(3015, 53, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3015, 53, 0, 10, 0, 1, 2, 3);
			player.getDropLogContainer().getContainer().shift();
			player.getPackets().sendItems(100, player.WildyWyrmgetDropLog().getContainer());
			break;
		case 19:
			player.getPackets().sendInterSetItemsOptionsScript(3015, 53, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3015, 53, 0, 10, 0, 1, 2, 3);
			player.getDropLogContainer().getContainer().shift();
			player.getPackets().sendItems(100, player.HmWildyWyrmgetDropLog().getContainer());
			break;
		case 20:
			player.getPackets().sendInterSetItemsOptionsScript(3015, 53, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3015, 53, 0, 10, 0, 1, 2, 3);
			player.getDropLogContainer().getContainer().shift();
			player.getPackets().sendItems(100, player.AquaticWyrmgetDropLog().getContainer());
			break;
		case 21:
			player.getPackets().sendInterSetItemsOptionsScript(3015, 53, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3015, 53, 0, 10, 0, 1, 2, 3);
			player.getDropLogContainer().getContainer().shift();
			player.getPackets().sendItems(100, player.VoragogetDropLog().getContainer());
			break;
		case 22:
			player.getPackets().sendInterSetItemsOptionsScript(3015, 53, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3015, 53, 0, 10, 0, 1, 2, 3);
			player.getDropLogContainer().getContainer().shift();
			player.getPackets().sendItems(100, player.AodgetDropLog().getContainer());
			break;
		case 23:
			player.getPackets().sendInterSetItemsOptionsScript(3015, 53, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3015, 53, 0, 10, 0, 1, 2, 3);
			player.getDropLogContainer().getContainer().shift();
			player.getPackets().sendItems(100, player.GazergetDropLog().getContainer());
			break;
		case 24:
			player.getPackets().sendInterSetItemsOptionsScript(3015, 53, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3015, 53, 0, 10, 0, 1, 2, 3);
			player.getDropLogContainer().getContainer().shift();
			player.getPackets().sendItems(100, player.SantagetDropLog().getContainer());
			break;
		case 25:
			player.getPackets().sendInterSetItemsOptionsScript(3015, 53, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3015, 53, 0, 10, 0, 1, 2, 3);
			player.getDropLogContainer().getContainer().shift();
			player.getPackets().sendItems(100, player.DryaxgetDropLog().getContainer());
			break;
		case 26:
			player.getPackets().sendInterSetItemsOptionsScript(3015, 53, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3015, 53, 0, 10, 0, 1, 2, 3);
			player.getDropLogContainer().getContainer().shift();
			player.getPackets().sendItems(100, player.KalphiteKinggetDropLog().getContainer());
			break;
		case 27:
			player.getPackets().sendInterSetItemsOptionsScript(3015, 53, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3015, 53, 0, 10, 0, 1, 2, 3);
			player.getDropLogContainer().getContainer().shift();
			player.getPackets().sendItems(100, player.HopegetDropLog().getContainer());
			break;
		case 28:
			player.getPackets().sendInterSetItemsOptionsScript(3015, 53, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3015, 53, 0, 10, 0, 1, 2, 3);
			player.getDropLogContainer().getContainer().shift();
			player.getPackets().sendItems(100, player.PDgetDropLog().getContainer());
			break;
		case 29:
			player.getPackets().sendInterSetItemsOptionsScript(3015, 53, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3015, 53, 0, 10, 0, 1, 2, 3);
			player.getDropLogContainer().getContainer().shift();
			player.getPackets().sendItems(100, player.ZulrahgetDropLog().getContainer());
			break;
		case 30:
			player.getPackets().sendInterSetItemsOptionsScript(3015, 53, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3015, 53, 0, 10, 0, 1, 2, 3);
			player.getDropLogContainer().getContainer().shift();
			player.getPackets().sendItems(100, player.NecrolordgetDropLog().getContainer());
			break;
		case 31:
			player.getPackets().sendInterSetItemsOptionsScript(3015, 53, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3015, 53, 0, 10, 0, 1, 2, 3);
			player.getDropLogContainer().getContainer().shift();
			player.getPackets().sendItems(100, player.ThunderousgetDropLog().getContainer());
			break;
		case 32:
			player.getPackets().sendInterSetItemsOptionsScript(3015, 53, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3015, 53, 0, 10, 0, 1, 2, 3);
			player.getDropLogContainer().getContainer().shift();
			player.getPackets().sendItems(100, player.SunfreetgetDropLog().getContainer());
			break;
		case 33:
			player.getPackets().sendInterSetItemsOptionsScript(3015, 53, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3015, 53, 0, 10, 0, 1, 2, 3);
			player.getDropLogContainer().getContainer().shift();
			player.getPackets().sendItems(100, player.AniviagetDropLog().getContainer());
			break;
		case 34:
			player.getPackets().sendInterSetItemsOptionsScript(3015, 53, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3015, 53, 0, 10, 0, 1, 2, 3);
			player.getDropLogContainer().getContainer().shift();
			player.getPackets().sendItems(100, player.SliskegetDropLog().getContainer());
			break;
		case 35:
			player.getPackets().sendInterSetItemsOptionsScript(3015, 53, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3015, 53, 0, 10, 0, 1, 2, 3);
			player.getDropLogContainer().getContainer().shift();
			player.getPackets().sendItems(100, player.KrakengetDropLog().getContainer());
			break;
		case 36:
			player.getPackets().sendInterSetItemsOptionsScript(3015, 53, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3015, 53, 0, 10, 0, 1, 2, 3);
			player.getDropLogContainer().getContainer().shift();
			player.getPackets().sendItems(100, player.SiregetDropLog().getContainer());
			break;
		case 37:
			player.getPackets().sendInterSetItemsOptionsScript(3015, 53, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3015, 53, 0, 10, 0, 1, 2, 3);
			player.getDropLogContainer().getContainer().shift();
			player.getPackets().sendItems(100, player.CerberusgetDropLog().getContainer());
			break;
		case 38:
			player.getPackets().sendInterSetItemsOptionsScript(3015, 53, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3015, 53, 0, 10, 0, 1, 2, 3);
			player.getDropLogContainer().getContainer().shift();
			player.getPackets().sendItems(100, player.SirenicgetDropLog().getContainer());
			break;
		case 39:
			player.getPackets().sendInterSetItemsOptionsScript(3015, 53, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3015, 53, 0, 10, 0, 1, 2, 3);
			player.getDropLogContainer().getContainer().shift();
			player.getPackets().sendItems(100, player.CallistogetDropLog().getContainer());
			break;
		case 40:
			player.getPackets().sendInterSetItemsOptionsScript(3015, 53, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3015, 53, 0, 10, 0, 1, 2, 3);
			player.getDropLogContainer().getContainer().shift();
			player.getPackets().sendItems(100, player.VenenatisgetDropLog().getContainer());
			break;
		case 41:
			player.getPackets().sendInterSetItemsOptionsScript(3015, 53, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3015, 53, 0, 10, 0, 1, 2, 3);
			player.getDropLogContainer().getContainer().shift();
			player.getPackets().sendItems(100, player.ChaosFanaticgetDropLog().getContainer());
			break;
		case 42:
			player.getPackets().sendInterSetItemsOptionsScript(3015, 53, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3015, 53, 0, 10, 0, 1, 2, 3);
			player.getDropLogContainer().getContainer().shift();
			player.getPackets().sendItems(100, player.ArchaeologistgetDropLog().getContainer());
			break;
		case 43:
			player.getPackets().sendInterSetItemsOptionsScript(3015, 53, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3015, 53, 0, 10, 0, 1, 2, 3);
			player.getDropLogContainer().getContainer().shift();
			player.getPackets().sendItems(100, player.ScorpiagetDropLog().getContainer());
			break;
		case 44:
			player.getPackets().sendInterSetItemsOptionsScript(3015, 53, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3015, 53, 0, 10, 0, 1, 2, 3);
			player.getDropLogContainer().getContainer().shift();
			player.getPackets().sendItems(100, player.VetiongetDropLog().getContainer());
			break;
		case 45:
			player.getPackets().sendInterSetItemsOptionsScript(3015, 53, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3015, 53, 0, 10, 0, 1, 2, 3);
			player.getDropLogContainer().getContainer().shift();
			player.getPackets().sendItems(100, player.XericgetDropLog().getContainer());
			break;
		case 46:
			player.getPackets().sendInterSetItemsOptionsScript(3015, 53, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3015, 53, 0, 10, 0, 1, 2, 3);
			player.getDropLogContainer().getContainer().shift();
			player.getPackets().sendItems(100, player.BloodgetDropLog().getContainer());
			break;
		case 47:
			player.getPackets().sendInterSetItemsOptionsScript(3015, 53, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3015, 53, 0, 10, 0, 1, 2, 3);
			player.getDropLogContainer().getContainer().shift();
			player.getPackets().sendItems(100, player.TolgetDropLog().getContainer());
			break;
		case 48:
			player.getPackets().sendInterSetItemsOptionsScript(3015, 53, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3015, 53, 0, 10, 0, 1, 2, 3);
			player.getDropLogContainer().getContainer().shift();
			player.getPackets().sendItems(100, player.LizardmangetDropLog().getContainer());
			break;
		case 49:
			player.getPackets().sendInterSetItemsOptionsScript(3015, 53, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3015, 53, 0, 10, 0, 1, 2, 3);
			player.getDropLogContainer().getContainer().shift();
			player.getPackets().sendItems(100, player.SmokeDevilgetDropLog().getContainer());
			break;
		case 50:
			player.getPackets().sendInterSetItemsOptionsScript(3015, 53, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3015, 53, 0, 10, 0, 1, 2, 3);
			player.getDropLogContainer().getContainer().shift();
			player.getPackets().sendItems(100, player.BlinkgetDropLog().getContainer());
			break;
		case 54:
			player.getPackets().sendInterSetItemsOptionsScript(3015, 53, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3015, 53, 0, 10, 0, 1, 2, 3);
			player.getDropLogContainer().getContainer().shift();
			player.getPackets().sendItems(100, player.VorkathgetDropLog().getContainer());
			break;
		case 55:
			player.getPackets().sendInterSetItemsOptionsScript(3015, 53, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3015, 53, 0, 10, 0, 1, 2, 3);
			player.getDropLogContainer().getContainer().shift();
			player.getPackets().sendItems(100, player.HydragetDropLog().getContainer());
			break;
		case 56:
			player.getPackets().sendInterSetItemsOptionsScript(3015, 53, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3015, 53, 0, 10, 0, 1, 2, 3);
			player.getDropLogContainer().getContainer().shift();
			player.getPackets().sendItems(100, player.CelestiagetDropLog().getContainer());
			break;
		case 57:
			player.getPackets().sendInterSetItemsOptionsScript(3015, 53, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3015, 53, 0, 10, 0, 1, 2, 3);
			player.getDropLogContainer().getContainer().shift();
			player.getPackets().sendItems(100, player.RaptorgetDropLog().getContainer());
			break;
		case 58:
			player.getPackets().sendInterSetItemsOptionsScript(3015, 53, 100, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(3015, 53, 0, 10, 0, 1, 2, 3);
			player.getDropLogContainer().getContainer().shift();
			player.getPackets().sendItems(100, player.getAmigoDropLog().getContainer());
			break;

		}
	}
}