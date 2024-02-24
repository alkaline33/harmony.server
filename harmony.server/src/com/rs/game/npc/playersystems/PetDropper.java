package com.rs.game.npc.playersystems;

import com.discord.Discord;
import com.rs.Settings;
import com.rs.game.World;
import com.rs.game.npc.NPC;
import com.rs.game.player.Player;
import com.rs.utils.Utils;

/**
 *
 * @author Kryeus / John J. Woloszyk
 * @date 10.29.2017
 * 
 */

public class PetDropper {
	
	static Player p;
	static NPC n;
	static int r;
	public static void processKill(Player p, NPC n)
	{
		PetDropper.p = p;
		PetDropper.n = n;
		int npcID = n.getId();
		switch(npcID) {
		case 11983:
		case 12041:
		case 12088:
			amigo();
			break;
		case 2783:
			darkbeast();
			break;
		case 13955:
			raptor();
			break;
		case 51:
			frosty();
			break;
		case 1610:
		case 1827:
			garg();
			break;
		case 30082: anivia(); break;
		case 1552: badsanta(); break;
		case 15222:
			sunfreet();
			break;
		case 16385:
			kraken();
			break;
		case 39615:
		case 39619:
		case 39620:
		case 39621:
			Hydra();
			break;
		case 16387:
		case 16388:
		case 16389:
			zulrah();
			break;
		case 29981:
			Celestia();
			break;
		case 30163:
			CorruptVerac();
			break;
		case 30165:
			CorruptKaril();
			break;
		case 30167:
			CorruptAhrim();
			break;
		case 10141:
			ballak();
			break;
		case 3340:
			mole();
			break;
		case 39061:
			vorkath();
			break;
		case 37615: scorpia(); break;
		case 13450: nex(); break;
		case 30000: vorago(); break;
		case 11872: thunderous(); break;
		case 50: kbd(); break;
		case 10106: tectonic(); break;
		case 38286:
			skotizo();
			break;
		case 37504:
			venenatis();
			break;
		case 15174: dryax(); break;
		case 8133: corp(); break;
		case 14301: glacor(); break;
		case 6260: bandos(); break;
		case 6222: armadyl(); break;
		case 6203: zamorak(); break;
		case 6247: saradomin(); break;
		case 37503:
			callisto();
			break;
		case 37612:
			vetion();
			break;
		case 36862:
			cerberus();
			break;
		case 30085: awyrm(); break;
		case 12900:
			drygon();
			break;
		case 31499:
			smokedevil();
			break;
		case 36886:
		case 36890:
		case 36891:
			sire();
			break;
		
		default: break;
		}
	
	}
	
	
	private static void cerberus() {
		if (p.gotcerberuspet != true) {
			if (p.petperk == true) {
				r = Utils.random(562);
			} else {
				r = Utils.random(750);
			}
			if (Settings.eventbosspetchance > 0) {
				r /= 2;
			}
			switch (r) {
			case 0:
				World.sendWorldMessage("<img=7><col=ff8c38>News: " + p.getDisplayName() + " has received Hellpuppy pet.", false);
				Discord.sendSimpleMessage("NEWS: " + p.getDisplayName() + " has found a Hellpuppy pet!");
				p.gotcerberuspet = true;
				p.sendMessage("<col=330000>Congratulations! Please visit the Pet Tamer at home to get your pet.</col>");
				break;
			default:
				break;
			}

		}
	}
	
	private static void callisto() {
		if (p.gotcallistopet != true) {
			if (p.petperk == true) {
				r = Utils.random(562);
			} else {
				r = Utils.random(750);
			}
			if (Settings.eventbosspetchance > 0) {
				r /= 2;
			}
			switch (r) {
			case 0:
				World.sendWorldMessage("<img=7><col=ff8c38>News: " + p.getDisplayName() + " has received Callisto cub pet.", false);
				Discord.sendSimpleMessage("NEWS: " + p.getDisplayName() + " has found a Callisto pet!");
				p.gotcallistopet = true;
				p.sendMessage("<col=330000>Congratulations! Please visit the Pet Tamer at home to get your pet.</col>");
				break;
			default:
				break;
			}

		}
	}

	private static void vetion() {
		if (p.gotvetionpet != true) {
			if (p.petperk == true) {
				r = Utils.random(562);
			} else {
				r = Utils.random(750);
			}
			if (Settings.eventbosspetchance > 0) {
				r /= 2;
			}
			switch (r) {
			case 0:
				World.sendWorldMessage("<img=7><col=ff8c38>News: " + p.getDisplayName() + " has received Vet'ion jr pet.", false);
				Discord.sendSimpleMessage("NEWS: " + p.getDisplayName() + " has found a Vet'ion jr pet!");
				p.gotvetionpet = true;
				p.sendMessage("<col=330000>Congratulations! Please visit the Pet Tamer at home to get your pet.</col>");
				break;
			default:
				break;
			}

		}
	}

	private static void anivia() {
		if (p.gotaniviapet != true)
		{
			if (p.petperk == true ) {
				r = Utils.random(562 );
			} else {
				r = Utils.random(750 );
			}
			if (Settings.eventbosspetchance > 0) {
				r /= 2;
			}
			switch (r) {
			case 0:
				World.sendWorldMessage("<img=7><col=ff8c38>News: " + p.getDisplayName() + " has received Baby Anivia pet.", false);
				Discord.sendSimpleMessage("NEWS: " + p.getDisplayName() + " has found an Anivia pet!");
				p.gotaniviapet = true;
				p.sendMessage("<col=330000>Congratulations! Please visit the Pet Tamer at home to get your pet.</col>");
				break;
			default:
				break;
			}
		
		}
	}
	
	private static void frosty() {
		if (p.gotfrostypet != true)
		{
			if (p.petperk == true ) {
				r = Utils.random(750);
			} else {
				r = Utils.random(1000);
			}
			if (Settings.eventbosspetchance > 0) {
				r /= 2;
			}
			switch (r) {
			case 0:
				World.sendWorldMessage("<img=7><col=ff8c38>News: " + p.getDisplayName() + " has received Frosty pet.", false);
				Discord.sendSimpleMessage("NEWS: " + p.getDisplayName() + " has found a Frosty pet!");
				p.gotfrostypet = true;
				p.sendMessage("<col=330000>Congratulations! Please visit the Pet Tamer at home to get your pet.</col>");
				break;
			default:
				break;
			}
		
		}
	}
	
	private static void garg() {
		if (p.gotgargpet != true)
		{
			if (p.petperk == true ) {
				r = Utils.random(750);
			} else {
				r = Utils.random(1000);
			}
			if (Settings.eventbosspetchance > 0) {
				r /= 2;
			}
			switch (r) {
			case 0:
				World.sendWorldMessage("<img=7><col=ff8c38>News: " + p.getDisplayName() + " has received Garg pet.", false);
				Discord.sendSimpleMessage("NEWS: " + p.getDisplayName() + " has found a Garg pet!");
				p.gotgargpet = true;
				p.sendMessage("<col=330000>Congratulations! Please visit the Pet Tamer at home to get your pet.</col>");
				break;
			default:
				break;
			}
		
		}
	}
	
	private static void darkbeast() {
		if (p.gotdarkfeastpet != true)
		{
			if (p.petperk == true ) {
				r = Utils.random(750);
			} else {
				r = Utils.random(1000);
			}
			if (Settings.eventbosspetchance > 0) {
				r /= 2;
			}
			switch (r) {
			case 0:
				World.sendWorldMessage("<img=7><col=ff8c38>News: " + p.getDisplayName() + " has received Dark Feast pet.", false);
				Discord.sendSimpleMessage("NEWS: " + p.getDisplayName() + " has found a Dark Feast pet!");
				p.gotdarkfeastpet = true;
				p.sendMessage("<col=330000>Congratulations! Please visit the Pet Tamer at home to get your pet.</col>");
				break;
			default:
				break;
			}
		
		}
	}
	
	private static void amigo() {
		if (p.gotamigopet != true)
		{
			if (p.petperk == true ) {
				r = Utils.random(750);
			} else {
				r = Utils.random(1000);
			}
			if (Settings.eventbosspetchance > 0) {
				r /= 2;
			}
			switch (r) {
			case 0:
				World.sendWorldMessage("<img=7><col=ff8c38>News: " + p.getDisplayName() + " has received the Amigo pet.", false);
				Discord.sendSimpleMessage("NEWS: " + p.getDisplayName() + " has found the Amigo pet!");
				p.gotamigopet = true;
				p.sendMessage("<col=330000>Congratulations! Please visit the Pet Tamer at home to get your pet.</col>");
				break;
			default:
				break;
			}
		
		}
	}
	
	private static void raptor() {
		if (p.gotraptorpet != true)
		{
			if (p.petperk == true ) {
				r = Utils.random(3750);
			} else {
				r = Utils.random(5000);
			}
			if (Settings.eventbosspetchance > 0) {
				r /= 2;
			}
			switch (r) {
			case 0:
				World.sendWorldMessage("<img=7><col=ff8c38>News: " + p.getDisplayName() + " has received a Mini Raptor pet.", false);
				Discord.sendSimpleMessage("NEWS: " + p.getDisplayName() + " has found a Mini Raptor pet!");
				p.gotraptorpet = true;
				p.sendMessage("<col=330000>Congratulations! Please visit the Pet Tamer at home to get your pet.</col>");
				break;
			default:
				break;
			}
		
		}
	}
	
	private static void drygon() {
		if (p.gotdrygonpet != true) {
			if (p.petperk) {
				r = Utils.random(300);
			} else {
				r = Utils.random(400);
			}
			if (Settings.eventbosspetchance > 0) {
				r /= 2;
			}
			switch (r) {
			case 0:
				World.sendWorldMessage(
						"<img=7><col=ff8c38>News: " + p.getDisplayName() + " has received a Hope Devourer pet.", false);
				Discord.sendSimpleMessage("NEWS: " + p.getDisplayName() + " has found a Hope Devourer!");
				p.gotdrygonpet = true;
				p.sendMessage("<col=330000>Congratulations! Please visit the Pet Tamer at home to get your pet.</col>");
				break;
			default:
				break;
			}
		}
	}
	
	private static void smokedevil() {
		if (p.gotsmokedevilpet != true) {
			if (p.petperk) {
				r = Utils.random(750);
			} else {
				r = Utils.random(1000);
			}
			if (Settings.eventbosspetchance > 0) {
				r /= 2;
			}
			switch (r) {
			case 0:
				World.sendWorldMessage(
						"<img=7><col=ff8c38>News: " + p.getDisplayName() + " has received Smoke Devil pet.", false);
				Discord.sendSimpleMessage("NEWS: " + p.getDisplayName() + " has found a Smoke Devil pet!");
				p.gotsmokedevilpet = true;
				p.sendMessage("<col=330000>Congratulations! Please visit the Pet Tamer at home to get your pet.</col>");
				break;
			default:
				break;
			}
		}
	}

	private static void sire() {
		if (p.gotsirepet != true) {
			if (p.petperk) {
				r = Utils.random(750);
			} else {
				r = Utils.random(1000);
			}
			if (Settings.eventbosspetchance > 0) {
				r /= 2;
			}
			switch (r) {
			case 0:
				World.sendWorldMessage("<img=7><col=ff8c38>News: " + p.getDisplayName() + " has received an Abyssal Orphan pet.", false);
				Discord.sendSimpleMessage("NEWS: " + p.getDisplayName() + " has found an Abyssal Orphan pet!");
				p.gotsirepet = true;
				p.sendMessage("<col=330000>Congratulations! Please visit the Pet Tamer at home to get your pet.</col>");
				break;
			default:
				break;
			}
		}
	}

	

	private static void badsanta() {
		if (p.gotbadsantapet != true) {
			if (p.petperk == true ){
				r = Utils.random(2400 );
			} else {
				r = Utils.random(3000 );
			}
			if (Settings.eventbosspetchance > 0) {
				r /= 2;
			}
			switch (r) {
			case 0:
				World.sendWorldMessage("<img=7><col=ff8c38>News: " + p.getDisplayName() + " has received Bad Santa pet.", false);
				Discord.sendSimpleMessage("NEWS: " + p.getDisplayName() + " has found a Bad Santa pet!");
				p.gotbadsantapet = true;
				p.sendMessage("<col=330000>Congratulations! Please visit the Pet Tamer at home to get your pet.</col>");
				break;
			default:
				break;
			}
		}
	}

	private static void nex() {
		if (p.gotnexpet != true) {
			if (p.petperk == true){
				r = Utils.random(185 );
			} else {
				r = Utils.random(250 );
			}
			if (Settings.eventbosspetchance > 0) {
				r /= 2;
			}
			switch (r) {
			case 0:
				World.sendWorldMessage("<img=7><col=ff8c38>News: " + p.getDisplayName() + " has received Nex pet.", false);
				Discord.sendSimpleMessage("NEWS: " + p.getDisplayName() + " has found a Nex pet!");
				p.gotnexpet = true;
				p.sendMessage("<col=330000>Congratulations! Please visit the Pet Tamer at home to get your pet.</col>");
				break;
			default:
				break;
			}
		}
	}
	
	private static void vorago() {
		if (p.gotvoragopet != true) {
			
			if (p.petperk == true ){
				r = Utils.random(105 );
			} else {
				r = Utils.random(150 );
			}
			if (Settings.eventbosspetchance > 0) {
				r /= 2;
			}
			switch (r) {
			case 0:
				World.sendWorldMessage("<img=7><col=ff8c38>News: " + p.getDisplayName() + " has received Vorago pet.", false);
				Discord.sendSimpleMessage("NEWS: " + p.getDisplayName() + " has found a Vorago pet!");
				p.gotvoragopet = true;
				p.sendMessage("<col=330000>Congratulations! Please visit the Pet Tamer at home to get your pet.</col>");
				break;
			default:
				break;
			}
		}
	}
	
	private static void thunderous() {
		if (p.gotthunderouspet != true) {
			if (p.petperk == true ){
				r = Utils.random(750 );
			} else {
				r = Utils.random(1000 );
			}
			if (Settings.eventbosspetchance > 0) {
				r /= 2;
			}
			switch (r) {
			case 0:
				World.sendWorldMessage("<img=7><col=ff8c38>News: " + p.getDisplayName() + " has received Yk'Lagor the Thunderous pet.", false);
				Discord.sendSimpleMessage("NEWS: " + p.getDisplayName() + " has found a Yk'Lagor the Thunderous pet!");
				p.gotthunderouspet = true;
				p.sendMessage("<col=330000>Congratulations! Please visit the Pet Tamer at home to get your pet.</col>");
				break;
			default:
				break;
			}
		}
	}
	
	private static void kbd() {
		if (p.gotkbdpet != true) {
			if (p.petperk == true ){
				r = Utils.random(375 );
			} else {
				r = Utils.random(500 );
			}
			if (Settings.eventbosspetchance > 0) {
				r /= 2;
			}
			switch (r) {
			case 0:
				World.sendWorldMessage("<img=7><col=ff8c38>News: " + p.getDisplayName() + " has received King Black Dragon pet.", false);
				Discord.sendSimpleMessage("NEWS: " + p.getDisplayName() + " has found a King Black Dragon pet!");
				p.gotkbdpet = true;
				p.sendMessage("<col=330000>Congratulations! Please visit the Pet Tamer at home to get your pet.</col>");
				break;
			default:
				break;
			}
		}
	}
	
	private static void sunfreet() {
		if (p.gotsunfreetpet != true) {
			if (p.petperk == true) {
				r = Utils.random(300);
			} else {
				r = Utils.random(400);
			}
			if (Settings.eventbosspetchance > 0) {
				r /= 2;
			}
			switch (r) {
			case 0:
				World.sendWorldMessage("<img=7><col=ff8c38>News: " + p.getDisplayName() + " has received a Sunfreet pet.", false);
				p.gotsunfreetpet = true;
				p.sendMessage("<col=330000>Congratulations! Please visit the Pet Tamer at home to get your pet.</col>");
				break;
			default:
				break;
			}
		}
	}
	
	private static void Hydra() {
		if (p.gothydrapet != true) {
			if (p.petperk == true) {
				r = Utils.random(1500);
			} else {
				r = Utils.random(2000);
			}
			if (Settings.eventbosspetchance > 0) {
				r /= 2;
			}
			switch (r) {
			case 0:
				World.sendWorldMessage("<img=7><col=ff8c38>News: " + p.getDisplayName() + " has received an Ikkle hydra pet.", false);
				p.gothydrapet = true;
				p.sendMessage("<col=330000>Congratulations! Please visit the Pet Tamer at home to get your pet.</col>");
				break;
			default:
				break;
			}
		}
	}
	
	private static void Celestia() {
		if (p.gotcelestiapet != true) {
			if (p.petperk == true) {
				r = Utils.random(500);
			} else {
				r = Utils.random(375);
			}
			if (Settings.eventbosspetchance > 0) {
				r /= 2;
			}
			switch (r) {
			case 0:
				World.sendWorldMessage("<img=7><col=ff8c38>News: " + p.getDisplayName() + " has received an SkeeSkee pet.", false);
				p.gotcelestiapet = true;
				p.sendMessage("<col=330000>Congratulations! Please visit the Pet Tamer at home to get your pet.</col>");
				break;
			default:
				break;
			}
		}
	}
	
	private static void CorruptVerac() {
		if (p.gotcorruptveracpet != true) {
			if (p.petperk == true) {
				r = Utils.random(2000);
			} else {
				r = Utils.random(1500);
			}
			if (Settings.eventbosspetchance > 0) {
				r /= 2;
			}
			switch (r) {
			case 0:
				World.sendWorldMessage("<img=7><col=ff8c38>News: " + p.getDisplayName() + " has received a Warped Verac pet.", false);
				p.gotcorruptveracpet = true;
				p.sendMessage("<col=330000>Congratulations! Please visit the Pet Tamer at home to get your pet.</col>");
				break;
			default:
				break;
			}
		}
	}
	
	private static void CorruptAhrim() {
		if (p.gotcorruptahrimpet != true) {
			if (p.petperk == true) {
				r = Utils.random(2000);
			} else {
				r = Utils.random(1500);
			}
			if (Settings.eventbosspetchance > 0) {
				r /= 2;
			}
			switch (r) {
			case 0:
				World.sendWorldMessage("<img=7><col=ff8c38>News: " + p.getDisplayName() + " has received a Warped Ahrim pet.", false);
				p.gotcorruptahrimpet = true;
				p.sendMessage("<col=330000>Congratulations! Please visit the Pet Tamer at home to get your pet.</col>");
				break;
			default:
				break;
			}
		}
	}
	
	private static void CorruptKaril() {
		if (p.gotcorruptkarilpet != true) {
			if (p.petperk == true) {
				r = Utils.random(2000);
			} else {
				r = Utils.random(1500);
			}
			if (Settings.eventbosspetchance > 0) {
				r /= 2;
			}
			switch (r) {
			case 0:
				World.sendWorldMessage("<img=7><col=ff8c38>News: " + p.getDisplayName() + " has received a Warped Karil pet.", false);
				p.gotcorruptkarilpet = true;
				p.sendMessage("<col=330000>Congratulations! Please visit the Pet Tamer at home to get your pet.</col>");
				break;
			default:
				break;
			}
		}
	}

	private static void ballak() {
		if (p.gotballakpet != true) {
			if (p.petperk == true) {
				r = Utils.random(300);
			} else {
				r = Utils.random(400);
			}
			if (Settings.eventbosspetchance > 0) {
				r /= 2;
			}
			switch (r) {
			case 0:
				World.sendWorldMessage("<img=7><col=ff8c38>News: " + p.getDisplayName() + " has received a Bal'lak pet.", false);
				p.gotballakpet = true;
				p.sendMessage("<col=330000>Congratulations! Please visit the Pet Tamer at home to get your pet.</col>");
				break;
			default:
				break;
			}
		}
	}

	private static void mole() {
		if (p.gotgmolepet != true) {
			if (p.petperk == true) {
				r = Utils.random(300);
			} else {
				r = Utils.random(400);
			}
			if (Settings.eventbosspetchance > 0) {
				r /= 2;
			}
			switch (r) {
			case 0:
				World.sendWorldMessage("<img=7><col=ff8c38>News: " + p.getDisplayName() + " has received a Giant mole pet.", false);
				p.gotgmolepet = true;
				p.sendMessage("<col=330000>Congratulations! Please visit the Pet Tamer at home to get your pet.</col>");
				break;
			default:
				break;
			}
		}
	}
	
	private static void zulrah() {
		if (p.gotzulrahpet != true) {
			if (p.petperk == true) {
				r = Utils.random(300);
			} else {
				r = Utils.random(400);
			}
			if (Settings.eventbosspetchance > 0) {
				r /= 2;
			}
			switch (r) {
			case 0:
				World.sendWorldMessage("<img=7><col=ff8c38>News: " + p.getDisplayName() + " has received a Zulrah pet.", false);
				p.gotzulrahpet = true;
				p.sendMessage("<col=330000>Congratulations! Please visit the Pet Tamer at home to get your pet.</col>");
				break;
			default:
				break;
			}
		}
	}
	
	private static void kraken() {
		if (p.gotkrakenpet != true) {
			if (p.petperk == true) {
				r = Utils.random(750);
			} else {
				r = Utils.random(1000);
			}
			if (Settings.eventbosspetchance > 0) {
				r /= 2;
			}
			switch (r) {
			case 0:
				World.sendWorldMessage("<img=7><col=ff8c38>News: " + p.getDisplayName() + " has received a Kraken pet.", false);
				p.gotkrakenpet = true;
				p.sendMessage("<col=330000>Congratulations! Please visit the Pet Tamer at home to get your pet.</col>");
				break;
			default:
				break;
			}
		}
	}

	private static void vorkath() {
		if (p.gotvorkipet != true) {
			if (p.petperk == true) {
				r = Utils.random(750);
			} else {
				r = Utils.random(1000);
			}
			if (Settings.eventbosspetchance > 0) {
				r /= 2;
			}
			switch (r) {
			case 0:
				World.sendWorldMessage("<img=7><col=ff8c38>News: " + p.getDisplayName() + " has received a Vorkath pet.", false);
				p.gotvorkipet = true;
				p.sendMessage("<col=330000>Congratulations! Please visit the Pet Tamer at home to get your pet.</col>");
				break;
			default:
				break;
			}
		}
	}

	private static void tectonic() {
		if (p.gottectonicbeastpet != true) {
			if (p.petperk == true ){
				r = Utils.random(233 );
			} else {
				r = Utils.random(300 );
			}
			if (Settings.eventbosspetchance > 0) {
				r /= 2;
			}
			switch (r) {
			case 0:
				World.sendWorldMessage("<img=7><col=ff8c38>News: " + p.getDisplayName() + " has received Tectonic Beast pet.", false);
				p.gottectonicbeastpet = true;
				p.sendMessage("<col=330000>Congratulations! Please visit the Pet Tamer at home to get your pet.</col>");
				break;
			default:
				break;
			}
		}
	}

	private static void skotizo() {
		if (p.gotskotizopet != true) {
			if (p.petperk == true) {
				r = Utils.random(48);
			} else {
				r = Utils.random(65);
			}
			if (Settings.eventbosspetchance > 0) {
				r /= 2;
			}
			switch (r) {
			case 0:
				World.sendWorldMessage("<img=7><col=ff8c38>News: " + p.getDisplayName() + " has received a Skotos pet.", false);
				Discord.sendSimpleMessage("NEWS: " + p.getDisplayName() + " has found a Skotos pet!");
				p.gotskotizopet = true;
				p.sendMessage("<col=330000>Congratulations! Please visit the Pet Tamer at home to get your pet.</col>");
				break;
			default:
				break;
			}
		}
	}

	private static void venenatis() {
		if (p.gotvenenatispet != true) {
			if (p.petperk == true) {
				r = Utils.random(562);
			} else {
				r = Utils.random(750);
			}
			if (Settings.eventbosspetchance > 0) {
				r /= 2;
			}
			switch (r) {
			case 0:
				World.sendWorldMessage("<img=7><col=ff8c38>News: " + p.getDisplayName() + " has received Venenatis spiderling pet.", false);
				Discord.sendSimpleMessage("NEWS: " + p.getDisplayName() + " has found a Venenatis spiderling pet!");
				p.gotvenenatispet = true;
				p.sendMessage("<col=330000>Congratulations! Please visit the Pet Tamer at home to get your pet.</col>");
				break;
			default:
				break;
			}
		}
	}

	private static void scorpia() {
		if (p.gotscorpiapet != true) {
			if (p.petperk == true) {
				r = Utils.random(562);
			} else {
				r = Utils.random(750);
			}
			if (Settings.eventbosspetchance > 0) {
				r /= 2;
			}
			switch (r) {
			case 0:
				World.sendWorldMessage("<img=7><col=ff8c38>News: " + p.getDisplayName() + " has received Scorpia's offspring pet.", false);
				Discord.sendSimpleMessage("NEWS: " + p.getDisplayName() + " has found a Scorpia pet!");
				p.gotscorpiapet = true;
				p.sendMessage("<col=330000>Congratulations! Please visit the Pet Tamer at home to get your pet.</col>");
				break;
			default:
				break;
			}
		}
	}
	
	private static void dryax() {
		if (p.gotdryaxpet != true) {
			if (p.petperk == true ){
				r = Utils.random(900 );
			} else {
				r = Utils.random(1200 );
			}
			if (Settings.eventbosspetchance > 0) {
				r /= 2;
			}
			switch (r) {
			case 0:
				World.sendWorldMessage("<img=7><col=ff8c38>News: " + p.getDisplayName() + " has received a Dryax pet.", false);
				Discord.sendSimpleMessage("NEWS: " + p.getDisplayName() + " has found a Dryax pet!");
				p.gotdryaxpet = true;
				p.sendMessage("<col=330000>Congratulations! Please visit the Pet Tamer at home to get your pet.</col>");
				break;
			default:
				break;
			}
		}
	}

	private static void corp() {
		if (p.gotcorppet != true) {
			if (p.petperk == true ){
				r = Utils.random(450 );
			} else {
				r = Utils.random(600 );
			}
			if (Settings.eventbosspetchance > 0) {
				r /= 2;
			}
			switch (r) {
			case 0:
				World.sendWorldMessage("<img=7><col=ff8c38>News: " + p.getDisplayName() + " has received a Corporeal Beast pet.", false);
				Discord.sendSimpleMessage("NEWS: " + p.getDisplayName() + " has found a Corporeal Beast pet!");
				p.gotcorppet = true;
				p.sendMessage("<col=330000>Congratulations! Please visit the Pet Tamer at home to get your pet.</col>");
				break;
			default:
				break;
			}
		}
	}

	private static void glacor() {
		if (p.gotglacorpet != true) {
			if (p.petperk == true ){
				r = Utils.random(750 );
			} else {
				r = Utils.random(1000 );
			}
			if (Settings.eventbosspetchance > 0) {
				r /= 2;
			}
			switch (r) {
			case 0:
				World.sendWorldMessage("<img=7><col=ff8c38>News: " + p.getDisplayName() + " has received a Glacor pet.", false);
				Discord.sendSimpleMessage("NEWS: " + p.getDisplayName() + " has found a Glacor pet!");
				p.gotglacorpet = true;
				p.sendMessage("<col=330000>Congratulations! Please visit the Pet Tamer at home to get your pet.</col>");
				break;
			default:
				break;
			}
		}
		
	}
	
	private static void bandos() {
		if (p.gotbandospet != true) {
			if (p.petperk == true ){
				r = Utils.random(600 );
			} else {
				r = Utils.random(800 );
			}
			if (Settings.eventbosspetchance > 0) {
				r /= 2;
			}
			switch (r) {
			case 0:
				World.sendWorldMessage("<img=7><col=ff8c38>News: " + p.getDisplayName() + " has received a Bandos pet.", false);
				Discord.sendSimpleMessage("NEWS: " + p.getDisplayName() + " has found a Bandos pet!");
				p.gotbandospet = true;
				p.sendMessage("<col=330000>Congratulations! Please visit the Pet Tamer at home to get your pet.</col>");
				break;
			default:
				break;
			}
			}
		
	}
	
	private static void armadyl() {
		if (p.gotarmadylpet != true) {
			if (p.petperk == true){
				r = Utils.random(600 );
			} else {
				r = Utils.random(800 );
			}
			if (Settings.eventbosspetchance > 0) {
				r /= 2;
			}
			switch (r) {
			case 0:
				World.sendWorldMessage("<img=7><col=ff8c38>News: " + p.getDisplayName() + " has received an Armadyl pet.", false);
				Discord.sendSimpleMessage("NEWS: " + p.getDisplayName() + " has found an Armadyl pet!");
				p.gotarmadylpet = true;
				p.sendMessage("<col=330000>Congratulations! Please visit the Pet Tamer at home to get your pet.</col>");
				break;
			default:
				break;
			}
		}
	}
	
	private static void zamorak() {
		if (p.gotzamorakpet != true) {
			if (p.petperk == true ){
				r = Utils.random(600 );
			} else {
				r = Utils.random(800 );
			}
			if (Settings.eventbosspetchance > 0) {
				r /= 2;
			}
			switch (r) {
			case 0:
				World.sendWorldMessage("<img=7><col=ff8c38>News: " + p.getDisplayName() + " has received a Zamorak pet.", false);
				Discord.sendSimpleMessage("NEWS: " + p.getDisplayName() + " has found a Zamorak pet!");
				p.gotzamorakpet = true;
				p.sendMessage("<col=330000>Congratulations! Please visit the Pet Tamer at home to get your pet.</col>");
				break;
			default:
				break;
			}
		}
		
	}
	
	private static void saradomin() {
		if (p.gotsaradominpet != true) {
			if (p.petperk == true ){
				r = Utils.random(600 );
			} else {
				r = Utils.random(800 );
			}
			if (Settings.eventbosspetchance > 0) {
				r /= 2;
			}
			switch (r) {
			case 0:
				World.sendWorldMessage("<img=7><col=ff8c38>News: " + p.getDisplayName() + " has received a Saradomin pet.", false);
				Discord.sendSimpleMessage("NEWS: " + p.getDisplayName() + " has found a Saradomin pet!");
				p.gotsaradominpet = true;
				p.sendMessage("<col=330000>Congratulations! Please visit the Pet Tamer at home to get your pet.</col>");
				break;
			default:
				break;
			}
		}
		
	}
	
	private static void awyrm() {
		if (p.gotawyrmpet != true) {
			if (p.petperk == true ){
				r = Utils.random(600 );
			} else {
				r = Utils.random(800 );
			}
			if (Settings.eventbosspetchance > 0) {
				r /= 2;
			}
			switch (r) {
			case 0:
				World.sendWorldMessage("<img=7><col=ff8c38>News: " + p.getDisplayName() + " has received an Aquatic wyrm pet.", false);
				Discord.sendSimpleMessage("NEWS: " + p.getDisplayName() + " has found an Aquatic Wyrm pet!");
				p.gotawyrmpet = true;
				p.sendMessage("<col=330000>Congratulations! Please visit the Pet Tamer at home to get your pet.</col>");
				break;
			default:
				break;
			}
		}
		
	}

}
