package com.rs.utils;

import com.rs.game.npc.pet.Pet;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;

public class PetExamine {

	public static void Examine(Player player, Player owner, Pet pet) {
		int Id = pet.getId();
		switch (Id) {
		case 7412:
			player.getPackets().sendNPCMessage(0, 52152219, pet, owner.getDisplayName() + " has " + Utils.getFormattedNumber(owner.AngryEasterBunnyKills) + " Angry Easter Bunny kills!");
			break;
		case 37296:
			player.getPackets().sendNPCMessage(0, 52152219, pet, owner.getDisplayName() + " has opened a total of " + Utils.getFormattedNumber(owner.cluescompleted) + " Clue Scrolls!");
			break;
		case 29980:
			player.getPackets().sendNPCMessage(0, 52152219, pet, owner.getDisplayName() + " has obtained a total of " + Utils.getFormattedNumber((int) owner.runecoinsobtained) + " Runecoins!");
			break;
		case 6702:
			player.getPackets().sendNPCMessage(0, 52152219, pet, owner.getDisplayName() + " has " + Utils.getFormattedNumber((int) owner.getSkills().getXp(Skills.FISHING)) + " Fishing experience!");
			break;
		case 30091:
			player.getPackets().sendNPCMessage(0, 52152219, pet, owner.getDisplayName() + " has " + Utils.getFormattedNumber((int) owner.getSkills().getXp(Skills.FIREMAKING)) + " Firemaking experience!");
			break;
		case 30090:
			player.getPackets().sendNPCMessage(0, 52152219, pet, owner.getDisplayName() + " has " + Utils.getFormattedNumber((int) owner.getSkills().getXp(Skills.COOKING)) + " Cooking experience!");
			break;
		case 30094:
			player.getPackets().sendNPCMessage(0, 52152219, pet, owner.getDisplayName() + " has " + Utils.getFormattedNumber((int) owner.getSkills().getXp(Skills.WOODCUTTING)) + " Woodcutting experience!");
			break;
		case 30089:
			player.getPackets().sendNPCMessage(0, 52152219, pet, owner.getDisplayName() + " has " + Utils.getFormattedNumber((int) owner.getSkills().getXp(Skills.MINING)) + " Mining experience!");
			break;
		case 30093:
			player.getPackets().sendNPCMessage(0, 52152219, pet, owner.getDisplayName() + " has " + Utils.getFormattedNumber((int) owner.getSkills().getXp(Skills.HUNTER)) + " Hunter experience!");
			break;
		case 30217:
			player.getPackets().sendNPCMessage(0, 52152219, pet, owner.getDisplayName() + " has completed " + Utils.getFormattedNumber(owner.theatreofbloodcompleted) + " Theatre of Blood raids!");
			break;
		case 30154:
			player.getPackets().sendNPCMessage(0, 52152219, pet, owner.getDisplayName() + " has completed " + Utils.getFormattedNumber(owner.slayersurvivalcompleted) + " Slayer Survival games!");
			break;
		case 30148:
			player.getPackets().sendNPCMessage(0, 52152219, pet, owner.getDisplayName() + " has " + Utils.getFormattedNumber(owner.BossKills()) + " total Boss kills!");
			break;
		case 30157:
			player.getPackets().sendNPCMessage(0, 52152219, pet, owner.getDisplayName() + " has completed a total of " + Utils.getFormattedNumber(owner.TotalRaidKills()) + " raids!");
			break;
		case 39517:
		case 39518:
		case 39519:
		case 39520:
			player.getPackets().sendNPCMessage(0, 52152219, pet, owner.getDisplayName() + " has " + Utils.getFormattedNumber(owner.HydraBossKills) + " Alchemical Hydra kills!");
			break;
		case 30156:
			player.getPackets().sendNPCMessage(0, 52152219, pet, owner.getDisplayName() + " has " + Utils.getFormattedNumber(owner.ZulrahKills) + " Zulrah kills!");
			break;
		case 37640:
			player.getPackets().sendNPCMessage(0, 52152219, pet, owner.getDisplayName() + " has " + Utils.getFormattedNumber(owner.KrakenKills) + " Kraken kills!");
			break;
		case 36536:
		case 36537:
			player.getPackets().sendNPCMessage(0, 52152219, pet, owner.getDisplayName() + " has " + Utils.getFormattedNumber(owner.VetionKills) + " Vet'ion kills!");
			break;
		case 39025:
			player.getPackets().sendNPCMessage(0, 52152219, pet, owner.getDisplayName() + " has " + Utils.getFormattedNumber(owner.VorkathKills) + " Vorkath kills!");
			break;
		case 29977:
			player.getPackets().sendNPCMessage(0, 52152219, pet, owner.getDisplayName() + " has " + Utils.getFormattedNumber(owner.CelestiaKills) + " Celestia, Defender of Sliske kills!");
			break;
		case 30019:
			player.getPackets().sendNPCMessage(0, 52152219, pet, owner.getDisplayName() + " has " + Utils.getFormattedNumber(owner.Dboss) + " Bal'lak kills!");
			break;
		case 30164:
		case 30166:
		case 30168:
			player.getPackets().sendNPCMessage(0, 52152219, pet, owner.getDisplayName() + " has killed " + Utils.getFormattedNumber(owner.CorruptedBrotherKills) + " Corrupted Brothers!");
			break;
		case 30170:
			player.getPackets().sendNPCMessage(0, 52152219, pet, owner.getDisplayName() + " has killed the Raptor " + Utils.getFormattedNumber(owner.RaptorKills) + " times!");
			break;
		case 29994:
			player.getPackets().sendNPCMessage(0, 52152219, pet, owner.getDisplayName() + " has " + Utils.getFormattedNumber(owner.GlacorKills) + " Glacor kills!");
			break;
		case 30150:
			player.getPackets().sendNPCMessage(0, 52152219, pet, owner.getDisplayName() + " has " + Utils.getFormattedNumber(owner.AssassinKills) + " Assassin kills!");
			break;
		case 31495:
			player.getPackets().sendNPCMessage(0, 52152219, pet, owner.getDisplayName() + " has " + Utils.getFormattedNumber(owner.VenenatisKills) + " Venenatis kills!");
			break;
		case 36547:
			player.getPackets().sendNPCMessage(0, 52152219, pet, owner.getDisplayName() + " has " + Utils.getFormattedNumber(owner.ScorpiaKills) + " Scorpia kills!");
			break;
		case 36558:
			player.getPackets().sendNPCMessage(0, 52152219, pet, owner.getDisplayName() + " has " + Utils.getFormattedNumber(owner.CallistoKills) + " Callisto kills!");
			break;
		case 38671:
			player.getPackets().sendNPCMessage(0, 52152219, pet, owner.getDisplayName() + " has " + Utils.getFormattedNumber(owner.SkotizoKills) + " Skotizo kills!");
			break;
		case 30029:
			player.getPackets().sendNPCMessage(0, 52152219, pet, owner.getDisplayName() + " has " + Utils.getFormattedNumber(owner.PdemonKills) + " Party Demon kills!");
			break;
		case 30001:
			player.getPackets().sendNPCMessage(0, 52152219, pet, owner.getDisplayName() + " has " + Utils.getFormattedNumber(owner.CorpKills) + " Corporeal Beast kills!");
			break;
		case 29999:
			player.getPackets().sendNPCMessage(0, 52152219, pet, owner.getDisplayName() + " has " + Utils.getFormattedNumber(owner.VoragoKills) + " Vorago kills!");
			break;
		case 13637:
			player.getPackets().sendNPCMessage(0, 52152219, pet, owner.getDisplayName() + " has " + Utils.getFormattedNumber(owner.SantaKills) + " bad Santa kills!");
			break;
		case 30003:
			player.getPackets().sendNPCMessage(0, 52152219, pet, owner.getDisplayName() + " has " + Utils.getFormattedNumber(owner.BandosKills) + " General Graador kills!");
			break;
		case 30005:
			player.getPackets().sendNPCMessage(0, 52152219, pet, owner.getDisplayName() + " has " + Utils.getFormattedNumber(owner.SaradominKills) + " Commander Zilyana kills!");
			break;
		case 30006:
			player.getPackets().sendNPCMessage(0, 52152219, pet, owner.getDisplayName() + " has " + Utils.getFormattedNumber(owner.ZamorakKills) + " K'ril Tsutsaroth kills!");
			break;
		case 30004:
			player.getPackets().sendNPCMessage(0, 52152219, pet, owner.getDisplayName() + " has " + Utils.getFormattedNumber(owner.ArmadylKills) + " Kree'arra kills!");
			break;
		case 29998:
			player.getPackets().sendNPCMessage(0, 52152219, pet, owner.getDisplayName() + " has " + Utils.getFormattedNumber(owner.NexKills) + " Nex kills!");
			break;
		case 29997:
			player.getPackets().sendNPCMessage(0, 52152219, pet, owner.getDisplayName() + " has " + Utils.getFormattedNumber(owner.WwyrmKills) + " WildyWyrm kills!");
			break;
		case 30086:
			player.getPackets().sendNPCMessage(0, 52152219, pet, owner.getDisplayName() + " has " + Utils.getFormattedNumber(owner.AwyrmKills) + " Aquatic Wyrm kills!");
			break;
		case 29996:
			player.getPackets().sendNPCMessage(0, 52152219, pet, owner.getDisplayName() + " has " + Utils.getFormattedNumber(owner.KbdKills) + " King Black Dragon kills!");
			break;
		case 30017:
			player.getPackets().sendNPCMessage(0, 52152219, pet, owner.getDisplayName() + " has " + Utils.getFormattedNumber(owner.SunfreetKills) + " Sunfreet kills!");
			break;
		case 30077:
			player.getPackets().sendNPCMessage(0, 52152219, pet, owner.getDisplayName() + " has " + Utils.getFormattedNumber(owner.ThunderousKills) + " Thunderous kills!");
			break;
		case 30023:
			player.getPackets().sendNPCMessage(0, 52152219, pet, owner.getDisplayName() + " has " + Utils.getFormattedNumber(owner.DrygonKills) + " Hope Devourer kills!");
			break;
		case 30084:
			player.getPackets().sendNPCMessage(0, 52152219, pet, owner.getDisplayName() + " has " + Utils.getFormattedNumber(owner.AniviaKills) + " Anivia kills!");
			break;
		case 38519:
			player.getPackets().sendNPCMessage(0, 52152219, pet, owner.getDisplayName() + " has completed " + Utils.getFormattedNumber(owner.osrsraidscompleted) + " Chambers of Xeric raids!");
			break;
		case 31964:
			player.getPackets().sendNPCMessage(0, 52152219, pet, owner.getDisplayName() + " has " + Utils.getFormattedNumber(owner.CerberusKills) + " Cerberus kills!");
			break;
		case 36883:
			player.getPackets().sendNPCMessage(0, 52152219, pet, owner.getDisplayName() + " has " + Utils.getFormattedNumber(owner.SireKills) + " Abyssal Sire kills!");
			break;
		default:
			player.getPackets().sendNPCMessage(0, 15263739, pet, NPCExamines.getExamine(pet));
			break;
		}
		return;

	}

}
