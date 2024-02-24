package com.rs.game.npc.playersystems;

import com.rs.Settings;
import com.rs.game.World;
import com.rs.game.npc.NPC;
import com.rs.game.player.Player;
import com.rs.game.player.content.WildernessTasks;
import com.rs.game.player.content.event.season.SeasonEvent;
import com.rs.game.player.controlers.Wilderness;
import com.rs.utils.Colors;

/**
 *
 * @author Kryeus / John J. Woloszyk
 * @date 10.29.2017
 * 
 */
public class KillCounter
{

	static Player p;
	static NPC n;
	public static void processKill(Player p, NPC n)
	{
		KillCounter.p = p;
		KillCounter.n = n;
		int npcID = n.getId();
		if (p.challengeid == 10 && p.challengeamount > 0 && n.getId() == 37503 && Wilderness.isAtWild(p)) {
			WildernessTasks.UpdateChallenge(p);
		}
		if (p.challengeid == 11 && p.challengeamount > 0 && n.getId() == 3334 && Wilderness.isAtWild(p)) {
			WildernessTasks.UpdateChallenge(p);
		}
		if (p.challengeid == 12 && p.challengeamount > 0 && n.getId() == 30085 && Wilderness.isAtWild(p)) {
			WildernessTasks.UpdateChallenge(p);
		}
		if (p.challengeid == 13 && p.challengeamount > 0 && n.getId() == 15222 && Wilderness.isAtWild(p)) {
			WildernessTasks.UpdateChallenge(p);
		}
		if (p.challengeid == 14 && p.challengeamount > 0 && n.getId() == 14262 && Wilderness.isAtWild(p)) {
			WildernessTasks.UpdateChallenge(p);
		}
		if (p.challengeid == 15 && p.challengeamount > 0 && n.getId() == 30008 && Wilderness.isAtWild(p)) {
			WildernessTasks.UpdateChallenge(p);
		}
		if (p.challengeid == 16 && p.challengeamount > 0 && n.getName().equalsIgnoreCase("Lesser demon") && Wilderness.isAtWild(p)) {
			WildernessTasks.UpdateChallenge(p);
		}
		if (p.challengeid == 17 && p.challengeamount > 0 && n.getName().equalsIgnoreCase("Hellhound") && Wilderness.isAtWild(p)) {
			WildernessTasks.UpdateChallenge(p);
		}
		if (p.challengeid == 18 && p.challengeamount > 0 && n.getName().equalsIgnoreCase("Ankou") && Wilderness.isAtWild(p)) {
			WildernessTasks.UpdateChallenge(p);
		}
		if (p.challengeid == 20 && p.challengeamount > 0 && n.getName().equalsIgnoreCase("Venenatis") && Wilderness.isAtWild(p)) {
			WildernessTasks.UpdateChallenge(p);
		}
		if (p.challengeid == 21 && p.challengeamount > 0 && n.getName().equalsIgnoreCase("Vet'ion") && Wilderness.isAtWild(p)) {
			WildernessTasks.UpdateChallenge(p);
		}
//		if (n.getName().equalsIgnoreCase(Settings.BOSS_SPOTLIGHT)) {
//			SeasonEvent.HandleActivity(p, "Boss Spotlight", 0);
//		}
		switch(npcID) {
			case 6213: p.gwdkc += p.gwdkcdouble ? 2 : 1; gwdkcMsg(); break;
			case 63: p.kill20drspiders++; kcMsg(); break;
		case 29985:
			p.runedragkills++;
			kcMsg();
			break;
		case 30067:
		case 30066:
		case 30065:
			p.Demigodkills++;
			kcMsg();
			break;
		case 37618:
			p.CrazyArcKills++;
			kcMsg();
			break;
		case 13712:
			p.BigtrollKills++;
			kcMsg();
			break;
		case 8349:
			p.TdsKills++;
			kcMsg();
			break;
		case 30163:
		case 30165:
		case 30167:
			//SeasonEvent.HandleActivity(p, "Corrupted Brothers", 0);
			p.CorruptedBrotherKills++;
			kcMsg();
			break;
		case 3340:
			p.GmoleKills++;
			kcMsg();
			break;
		case 37612:
			p.VetionKills++;
			kcMsg();
			break;
		case 13955:
			p.RaptorKills++;
			kcMsg();
			break;
		case 29975:
			p.AngryEasterBunnyKills++;
			kcMsg();
			break;
		case 37615:
			p.ScorpiaKills++;
			kcMsg();
			break;
		case 39061:
			p.VorkathKills++;
			kcMsg();
			break;
		case 38286:
			p.SkotizoKills++;
			kcMsg();
			break;
		case 37504:
			p.VenenatisKills++;
			kcMsg();
			break;
		case 36862:
			p.CerberusKills++;
			kcMsg();
			break;
		case 11983:
		case 12041:
		case 12088:
			p.the3amigosKills++;
			amigokcMsg();
			break;	
		case 12878:
			p.BlinkKills++;
			kcMsg();
			break;
		case 37766:
			p.LizardmanKills++;
			kcMsg();
			break;
		case 29981:
			p.CelestiaKills++;
			kcMsg();
			break;
		case 1265:
			p.rockcrabKills++;
			break;
		case 37503:
			p.CallistoKills++;
			kcMsg();
			break;
		case 37619:
			p.FanaticKills++;
			kcMsg();
			break;
		case 39615:
		case 39619:
		case 39620:
		case 39621:
			p.HydraBossKills++;
			kcMsg();
			break;
		/*case 453:
			p.dailynoobzamorak++;
			if (p.dailynoobzamorak == 50) {
				p.sendMessage(Colors.lightGray + "<img=6>You have completed the Daily Money Maker: Kill 50 Noobs of Zamorak! Your rewards have been placed in your bank.");
				p.getBank().addItem(29355, 5, true);
				p.getBank().addItem(5022, 5, true);
			}
			break;*/
		/*case 117:
			p.dailyhillgiants++;
			p.hillgiantKills++;
			if (p.dailyhillgiants == 75) {
				p.sendMessage(Colors.lightGray + "<img=6>You have completed the Daily Money Maker: Kill 75 Hill giants! Your rewards have been placed in your bank.");
				p.getBank().addItem(989, 1, true);
				p.getBank().addItem(5022, 5, true);
			}
			break;*/
			case 29993: p.KkingKills++; kcMsg(); break;
			case 14281: p.zkills++; p.zpoints++; kcMsg(); break;
			case 16385: p.KrakenKills++; kcMsg(); break;
			case 16388:
			case 16387:
			case 16389:	p.ZulrahKills++; kcMsg(); break;
			case 30082: case 30081: p.AniviaKills++; kcMsg(); break;
		case 36886:
		case 36890:
		case 36891:
			p.SireKills++;
			kcMsg();
			break;
		case 30011:
			p.Rot6Points += 6;
			p.Rot6Kills += 6;
			rotsMsg();
			break;
			case 9911: p.dunggkills++; dungMsg(); break;
			case 8596: p.AvaKills++; kcMsg(); break;
			case 3334: p.WwyrmKills++; kcMsg(); break;
			case 12900: p.DrygonKills++; kcMsg(); break;
			case 1552: p.SantaKills++; kcMsg(); break;
			case 13450: p.NexKills++; kcMsg(); break;
		case 30000:
		case 30009:
			p.VoragoKills++;
			kcMsg();
			break;
			case 11872: p.ThunderousKills++; kcMsg(); break;
			case 50: p.KbdKills++; kcMsg(); break;
			case 9463: p.IstrykerKills++; kcMsg(); break;
			case 9465: p.DstrykerKills++; kcMsg(); break;
			case 9467: p.JstrykerKills++; kcMsg(); break;
			case 7134: p.BorkKills++; kcMsg(); break;
			case 30025: p.HmWildyWyrmKills++; kcMsg(); break;
			case 30085: p.AwyrmKills++; kcMsg(); break;
			case 11751: p.NecroLordKills++; kcMsg(); break;
			case 14262: p.SliskeKills++; kcMsg(); break;
			case 30078:
			case 30079:
			case 30080: p.TrioRaidKills++; raidTsk(); kcMsg(); break;
			case 30087: p.GulegaRaidKills++; kcMsg(); break;
			case 8281: p.BalanceElementalKills++; balEle(); break;
			case 30008: p.SirenicKills++; kcMsg(); break;
			case 10141: p.Dboss++; kcMsg(); break;
			case 9752: p.GazerKills++; kcMsg(); break;
			case 15509: p.QbdKills++; kcMsg(); break;
			case 1160: p.KqKills++; kcMsg(); break;
			case 8133: p.CorpKills++; kcMsg(); break;
			case 15222: p.SunfreetKills++; kcMsg(); break;
			/*
			 * case 15174: p.DryaxKills++; kcMsg(); break;
			 */
			case 14301: p.GlacorKills++; kcMsg(); break;
		case 6260:
			p.BandosKills++;
			p.bandos++;
			p.gwdkills++;
			kcMsg();
			break;
		case 6222:
			p.ArmadylKills++;
			p.armadyl++;
			p.gwdkills++;
			kcMsg();
			break;
		case 1961:
		case 1966:
			p.templeoflightmummykills++;
			kcMsg();
			break;
		case 6203:
			p.ZamorakKills++;
			p.zamorak++;
			p.gwdkills++;
			kcMsg();
			break;
		case 6247:
			p.SaradominKills++;
			p.saradomin++;
			p.gwdkills++;
			kcMsg();
			break;
			default:
				break;
		}
		boolean cowCheck = n.getName().equalsIgnoreCase("cow");
		if(cowCheck) {
			p.kill25cows++;
		}
		
	}

	private static void gwdkcMsg() {
		p.sendMessage("Your GwD killcount is now " + p.gwdkc + "");
		
	}
	
	private static void amigokcMsg() {
		p.sendMessage("Your Amigo killcount is now " + p.the3amigosKills + "");
		
	}

	private static void kcMsg() {
		p.sendMessage("Your " + prettyPrintName() + " kill count is: " + prettyPrintNumber());
		if (prettyPrintNumber() == 100) {
			World.sendWorldMessage(Colors.cyan + "<img=6>" + p.getDisplayName() + " has just reached " + prettyPrintNumber() + " " + prettyPrintName() + " kills!", false);
			p.sendMessage(Colors.brown + "You have been awarded with 2000 Harmony points.");
			p.Drypoints += 2000;
		} else if (prettyPrintNumber() == 250) {
			World.sendWorldMessage(Colors.cyan + "<img=6>" + p.getDisplayName() + " has just reached " + prettyPrintNumber() + " " + prettyPrintName() + " kills!", false);
			p.sendMessage(Colors.brown + "You have been awarded with 5000 Harmony points.");
			p.Drypoints += 5000;
		} else if (prettyPrintNumber() == 500) {
			World.sendWorldMessage(Colors.cyan + "<img=6>" + p.getDisplayName() + " has just reached " + prettyPrintNumber() + " " + prettyPrintName() + " kills!", false);
			p.sendMessage(Colors.brown + "You have been awarded with 10000 Harmony points.");
			p.Drypoints += 10000;
		} else if (prettyPrintNumber() == 750) {
			World.sendWorldMessage(Colors.cyan + "<img=6>" + p.getDisplayName() + " has just reached " + prettyPrintNumber() + " " + prettyPrintName() + " kills!", false);
			p.sendMessage(Colors.brown + "You have been awarded with 20000 Harmony points.");
			p.Drypoints += 20000;
		} else if (prettyPrintNumber() == 1000) {
			World.sendWorldMessage(Colors.cyan + "<img=6>" + p.getDisplayName() + " has just reached " + prettyPrintNumber() + " " + prettyPrintName() + " kills!", false);
			p.sendMessage(Colors.brown + "You have been awarded with 50000 Harmony points.");
			p.Drypoints += 50000;
		} else if (prettyPrintNumber() == 1500) {
			World.sendWorldMessage(Colors.cyan + "<img=6>" + p.getDisplayName() + " has just reached " + prettyPrintNumber() + " " + prettyPrintName() + " kills!", false);
			p.sendMessage(Colors.brown + "You have been awarded with 60000 Harmony points.");
			p.Drypoints += 60000;
		} else if (prettyPrintNumber() == 2000) {
			World.sendWorldMessage(Colors.cyan + "<img=6>" + p.getDisplayName() + " has just reached " + prettyPrintNumber() + " " + prettyPrintName() + " kills!", false);
			p.sendMessage(Colors.brown + "You have been awarded with 75000 Harmony points.");
			p.Drypoints += 75000;
		} else if (prettyPrintNumber() == 2500) {
			World.sendWorldMessage(Colors.cyan + "<img=6>" + p.getDisplayName() + " has just reached " + prettyPrintNumber() + " " + prettyPrintName() + " kills!", false);
			p.sendMessage(Colors.brown + "You have been awarded with 100000 Harmony points.");
			p.Drypoints += 100000;
		} else if (prettyPrintNumber() == 3000) {
			World.sendWorldMessage(Colors.cyan + "<img=6>" + p.getDisplayName() + " has just reached " + prettyPrintNumber() + " " + prettyPrintName() + " kills!", false);
		} else if (prettyPrintNumber() == 4000) {
			World.sendWorldMessage(Colors.cyan + "<img=6>" + p.getDisplayName() + " has just reached " + prettyPrintNumber() + " " + prettyPrintName() + " kills!", false);
		} else if (prettyPrintNumber() == 5000) {
			World.sendWorldMessage(Colors.cyan + "<img=6>" + p.getDisplayName() + " has just reached " + prettyPrintNumber() + " " + prettyPrintName() + " kills!", false);
		} else if (prettyPrintNumber() == 6000) {
			World.sendWorldMessage(Colors.cyan + "<img=6>" + p.getDisplayName() + " has just reached " + prettyPrintNumber() + " " + prettyPrintName() + " kills!", false);
		} else if (prettyPrintNumber() == 7000) {
			World.sendWorldMessage(Colors.cyan + "<img=6>" + p.getDisplayName() + " has just reached " + prettyPrintNumber() + " " + prettyPrintName() + " kills!", false);
		} else if (prettyPrintNumber() == 8000) {
			World.sendWorldMessage(Colors.cyan + "<img=6>" + p.getDisplayName() + " has just reached " + prettyPrintNumber() + " " + prettyPrintName() + " kills!", false);
		} else if (prettyPrintNumber() == 9000) {
			World.sendWorldMessage(Colors.cyan + "<img=6>" + p.getDisplayName() + " has just reached " + prettyPrintNumber() + " " + prettyPrintName() + " kills!", false);
		} else if (prettyPrintNumber() == 10000) {
			World.sendWorldMessage(Colors.cyan + "<img=6>" + p.getDisplayName() + " has just reached " + prettyPrintNumber() + " " + prettyPrintName() + " kills!", false);
		}
	}
	

	
	private static String prettyPrintName()
	{
		int npcID = n.getId();
		switch(npcID)
		{
		case 30065:
		case 30066:
		case 30067:
			return "Demi God Trio";
		case 14281: return "Horde";
		case 30082: case 30081: return "Anivia";
		case 8596: return "Avatar of Destruction";
		case 3334: return "WildyWyrm";
		case 12900: return "Drygon";
		case 1552: return "Bad Santa";
		case 13450: return "Nex";
		case 30000:
		case 30009:
			return "Vorago";
		case 11872: return "Yk'Lagor the Thunderous";
		case 50: return "King Black Dragon";
		case 9463: return "Ice Strykewyrm";
		case 9465: return "Desert Strykewyrm";
		case 9467: return "Jungle Strykewyrm";
		case 7134: return "Bork";
		case 30025: return "WildyWyrm (Hard Mode)";
		case 30085: return "AquaticWyrm";
		case 11751: return "Necrolord";
		case 14262: return "Sliske";
		case 13955: return "Raptor";
		case 30078:
		case 30079:
		case 30080: return "Trio Raid";
		case 30008: return "Sirenic the Spider";
		case 10141: return "Donator Boss";
		case 9752: return "Night-Gazer";
		case 15509: return "Queen Black Dragon";
		case 1160: return "Kalphite Queen";
		case 8133: return "Corporeal Beast";
		case 15222: return "Sunfreet";
		case 10106: return "Tectonic the Beast";
		/*
		 * case 15174: return "Dryax";
		 */
		case 14301: return "Glacor";
		case 6260: return "General Graardor";
		case 6222: return "Kree'arra";
		case 6203: return "K'ril Tsutsaroth";
		case 6247: return "Commander Zilyana";
		case 17149:
		case 17150:
		case 17151:
		case 17152:
		case 17153:
		case 17154: return "Legio";
		case 1961:
		case 1966:
			return "Temple of Light Mummy";
		default: return n.getName();
		}
	}
	
	private static int prettyPrintNumber()
	{
		int npcID = n.getId();
		switch(npcID)
		{
			case 6213: return p.gwdkc;
			case 63: return p.kill20drspiders;
		case 29985:
			return p.runedragkills;
		case 8349:
			return p.TdsKills;
		case 30065:
		case 30066:
		case 30067:
			return p.Demigodkills;
			case 29993: return p.KkingKills;
			case 14281: return p.zkills;
			case 30082: case 30081: return p.AniviaKills;
			case 18538:
			case 18540:
			case 18541:
			case 18543:
			case 18544:
			case 18545: return p.Rot6Kills;
			case 16388:
			case 16387:
			case 16389: return p.ZulrahKills;
		case 1961:
		case 1966:
			return p.templeoflightmummykills;
		case 36862:
			return p.CerberusKills;
		case 11983:
		case 12041:
		case 12088:
			return p.the3amigosKills;
		case 12878:
			return p.BlinkKills;
		case 38286:
			return p.SkotizoKills;
		case 37504:
			return p.VenenatisKills;
		case 37619:
			return p.FanaticKills;
		case 39615:
		case 39619:
		case 39620:
		case 39621:
			return p.HydraBossKills;
		case 37618:
			return p.CrazyArcKills;
		case 13712:
			return p.BigtrollKills;
		case 30163:
		case 30165:
		case 30167:
			return p.CorruptedBrotherKills;
		case 3340:
			return p.GmoleKills;
		case 37612:
			return p.VetionKills;
		case 29975:
			return p.AngryEasterBunnyKills;
		case 37615:
			return p.ScorpiaKills;
		case 39061:
			return p.VorkathKills;
		case 37766:
			return p.LizardmanKills;
		case 29981:
			return p.CelestiaKills;
		case 13955:
			return p.RaptorKills;
		case 37503:
			return p.CallistoKills;
		case 36886:
		case 36890:
		case 36891:
			return p.SireKills;
			case 16385: return p.KrakenKills;
			case 9911: return (int) p.dunggkills;
			case 8596: return p.AvaKills;
			case 3334: return p.WwyrmKills;
			case 12900: return p.DrygonKills;
			case 1552: return p.SantaKills;
			case 13450: return p.NexKills;
		case 30000:
		case 30009:
			return p.VoragoKills;
			case 11872: return p.ThunderousKills;
			case 50: return p.KbdKills;
			case 9463: return p.IstrykerKills;
			case 9465: return p.DstrykerKills;
			case 9467: return p.JstrykerKills;
			case 7134: return p.BorkKills;
			case 30025: return p.HmWildyWyrmKills;
			case 30085: return p.AwyrmKills;
			case 11751: return p.NecroLordKills;
			case 14262: return p.SliskeKills;
			case 30078:
			case 30079:
			case 30080: return p.TrioRaidKills;
			case 8281: return p.BalanceElementalKills;
			case 30008: return p.SirenicKills;
			case 10141: return p.Dboss;
			case 9752: return p.GazerKills;
			case 15509: return p.QbdKills;
			case 1160: return p.KqKills;
			case 8133: return p.CorpKills;
			case 15222: return p.SunfreetKills;
			/*
			 * case 15174: return p.DryaxKills;
			 */
			case 14301: return p.GlacorKills;
			case 6260: return p.BandosKills;
			case 6222: return p.ArmadylKills;
			case 6203: return p.ZamorakKills;
			case 6247: return p.SaradominKills;
			default:
				return 0;
		}
	}
	
	private static void raidTsk()
	{
		Settings.TRIORAIDTASKACTIVE = true;
		Settings.TRIOBUSH = true;
		Settings.TRIOLEVER = true;
		Settings.TRIOVINE = true;
	}


	private static void dungMsg()
	{
		p.sendMessage("Your HL Dung floor count is: " + p.dunggkills + "");
		
	}


	private static void balEle()
	{
		p.canattackbalancelemental = false;
		p.sendMessage("Your Balance Elemental kill count is: "+p.BalanceElementalKills+"");
		
	}
	
	private static void rotsMsg()
	{
		p.sendMessage("You have " + p.Rot6Points + " Rise Of The Six point(s), you need 6 for a chest.");
	}





	
	
	
}
