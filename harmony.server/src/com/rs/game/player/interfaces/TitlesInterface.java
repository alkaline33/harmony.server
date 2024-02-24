package com.rs.game.player.interfaces;

import com.rs.game.World;
import com.rs.game.player.Player;

public class TitlesInterface {
	
	/**
	 * Author @Mr_Joopz
	 */
	
	private static int INTER = 3020;
	
	public static void SendInterface(Player player) {
		/**
		 * Initial interface being opened.
		 */
		player.getInterfaceManager().sendInterface(INTER);
		
		player.getPackets().sendIComponentText(INTER, 7, "<col=FFFF00>the Completionist</col>");
		player.getPackets().sendIComponentText(INTER, 12, "<col=580000>Maxed</br>");
		player.getPackets().sendIComponentText(INTER, 13, "<col=00FFFF>the Defeater</col>");
		player.getPackets().sendIComponentText(INTER, 14, "<col=33CC66>Sigil</col>");
		player.getPackets().sendIComponentText(INTER, 15, "<col=993300>of Zamorak</col>");
		player.getPackets().sendIComponentText(INTER, 16, "<col=006600>of Bandos</col>");
		player.getPackets().sendIComponentText(INTER, 17, "<col=0066CC>of Saradomin</col>");
		player.getPackets().sendIComponentText(INTER, 18, "<col=6666FF>of Armadyl</col>");
		player.getPackets().sendIComponentText(INTER, 19, "<col=CC0000>of Christmas</col>");
		player.getPackets().sendIComponentText(INTER, 20, "<col=CC0099>The Dreamer </col>");
		player.getPackets().sendIComponentText(INTER, 21, "<col=FF0000><shad=600000>Final Boss</shad></col>");
		player.getPackets().sendIComponentText(INTER, 22, "<col=F16F0F><shad=600000>Insane Final Boss</shad></col>");
		player.getPackets().sendIComponentText(INTER, 23, "<col=006666>Warmonger</col>");
		player.getPackets().sendIComponentText(INTER, 24, "<col=800080>Lieutenant Commander</col>");
		player.getPackets().sendIComponentText(INTER, 25, "<col=D00000>Sun killer</col>");
		player.getPackets().sendIComponentText(INTER, 26, "<col=00FFFF><shad=FFFFFF>defender of Harmony</col></shad>");
		player.getPackets().sendIComponentText(INTER, 28, "<col=FFFF00><shad=FF00FF>the Thunderous</col></shad>");
		player.getPackets().sendIComponentText(INTER, 29, "<col=EEEA93>the Light</col>");
		player.getPackets().sendIComponentText(INTER, 30, "<col=58388D>Mummified</col>");
		player.getPackets().sendIComponentText(INTER, 31, "Xp Mode Title");
		player.getPackets().sendIComponentText(INTER, 32, "<col=0FF1E7>Recycled</col>");
		player.getPackets().sendIComponentText(INTER, 33, "<col=F1650F>the Regenerated</col>");
		player.getPackets().sendIComponentText(INTER, 34, "<col=1DF10F>Eco-friendly</col>");
		player.getPackets().sendIComponentText(INTER, 35, "<col=DC0FF1>Biodegradable</col>");
		player.getPackets().sendIComponentText(INTER, 36, "<col=42F10F><shad=FFFFFF>the Toxic</shad></col>");
		player.getPackets().sendIComponentText(INTER, 37, "<col=E622E9><shad=000000>Experience</col><col=22E92E> Master</shad></col>");
		player.getPackets().sendIComponentText(INTER, 38, ",<col=22E92E> Defender of Sliske");
		player.getPackets().sendIComponentText(INTER, 39, "<col=582E2E>the Egg Collector</col>");
		player.getPackets().sendIComponentText(INTER, 40, "<col=fafed3><shad=5a4c3e>the Bunny Killer</shad></col>");
		player.getPackets().sendIComponentText(INTER, 41, "<col=6E2C00><shad=ffffff>Raids <col=B70FEB>master</shad></col>");
		player.getPackets().sendIComponentText(INTER, 42, "<col=ff0000>the Blood</shad></col>");
		player.getPackets().sendIComponentText(INTER, 43, "<col=84EB0F>of the Chambers</shad></col>");
		player.getPackets().sendIComponentText(INTER, 44, "<col=EB840F>Raider </shad></col>");
		player.getPackets().sendIComponentText(INTER, 45, "");
		
		/**
		 * Information at the bottom
		 */
		
		player.getPackets().sendIComponentText(INTER, 9, "This title is obtained when you meet the requirements for the completionist cape.");
	}
	
	public static void HandleButtons(Player player, int componentId) {
		/**
		 * Handles clicking on the interface.
		 */
		switch (componentId) {
		case 7:
			SendInterface(player);
			if (player.comped == true) {
				player.getAppearence().setTitle(34);
				player.getPackets().sendGameMessage("Title acquired.", true);
			}
			break;
		case 12:
			if (player.maxed == true) {
				player.getAppearence().setTitle(80);
				player.getPackets().sendGameMessage("Title acquired.", true);
			}
			player.getPackets().sendIComponentText(INTER, 9, "This title is obtained when you have all 99s.");
			break;
		case 13:
			if (player.VoragoKills >= 1) {
				player.getAppearence().setTitle(32);
				player.getPackets().sendGameMessage("Title acquired.", true);
			}
			player.getPackets().sendIComponentText(INTER, 9, "This title is obtained when you killed Vorago.");
			break;
		case 14:
			if (player.divine == true) {
				player.getAppearence().setTitle(813700);
				player.getPackets().sendGameMessage("Title acquired.", true);
			}
			player.getPackets().sendIComponentText(INTER, 9, "This title is obtained when you have obtained the divine sigil as a drop.");
			break;
		case 15:
			if (player.zamorak >= 50) {
				player.getAppearence().setTitle(38);
				player.getPackets().sendGameMessage("Title acquired.", true);
			}
			player.getPackets().sendIComponentText(INTER, 9, "This title is obtained when you have killed Zamorak 50 times.");
			break;
		case 16:
			if (player.bandos >= 50) {
				player.getAppearence().setTitle(35);
				player.getPackets().sendGameMessage("Title acquired.", true);
			}
			player.getPackets().sendIComponentText(INTER, 9, "This title is obtained when you have killed Bandos 50 times.");
			break;
		case 17:
			if (player.saradomin >= 50) {
				player.getAppearence().setTitle(36);
				player.getPackets().sendGameMessage("Title acquired.", true);
			}
			player.getPackets().sendIComponentText(INTER, 9, "This title is obtained when you have killed Saradomin 50 times.");
			break;
		case 18:
			if (player.armadyl >= 50) {
				player.getAppearence().setTitle(37);
				player.getPackets().sendGameMessage("Title acquired.", true);
			}
			player.getPackets().sendIComponentText(INTER, 9, "This title is obtained when you have killed Armadyl 50 times.");
			break;
		case 19:
			if (player.santatitle == true) {
				player.getAppearence().setTitle(56);
				player.getPackets().sendGameMessage("Title acquired.", true);
			}
			player.getPackets().sendIComponentText(INTER, 9, "This title is obtained when you have killed Bad Santa.");
			break;
		case 20:
			if (player.dreamcut >= 1000) {
				player.getAppearence().setTitle(33);
				player.getPackets().sendGameMessage("Title acquired.", true);
			}
			player.getPackets().sendIComponentText(INTER, 9, "This title is obtained when you have chopped 1,000 dream logs.");
			break;
		case 21:
			if (player.FinalBossTitle(player)) {
				if (player.fbtitle == false) {
					World.sendWorldMessage("<col=FF0000><shad=600000>" + player.getDisplayName() + " has just unlocked the *Final Boss* title!", true);
					player.fbtitle = true;
				}
				player.getAppearence().setTitle(66);
				player.getPackets().sendGameMessage("Title acquired.", true);
			}
			player.getPackets().sendIComponentText(INTER, 9, "This title is obtained when you have completed the requirements stated in the chat box.");
			player.sendMessage("<col==FF0000>You need a total of 4,000 boss kills and atleast 50 in the following bosses;");
			player.sendMessage("<col=FF0000>Corporeal beast, KBD, QBD, Bork, Nex, Glacors, Rise of the Six, Bandos, Saradomin, Zamorak, Armadyl, WildyWyrm, Aquatic Wyrm, Demi Gods,");
			player.sendMessage("<col=FF0000>Vorago, AOD, Gazer, Bad Santa, Dryax, Hope Devourer, Kalphite King, Zulrah, Necrolord, Thunderous, Sunfreet, Anivia, Sliske, Giant Mole, 3 Amigo's,");
			player.sendMessage("<col=FF0000>Hydra, Celestia, KQ, Kraken, Sire, Cerberus, Sirenic, Callisto, Venenatis, Chaos Fanatic, Crazy Archaeologist, Scorpia, Vet'ion, Chambers of Xeric, Theatre of Blood, Skotizo & Lizardman!");
			break;
		case 22:
			if (player.InsaneFinalBossTitle(player)) {
				if (player.insanefbtitle == false) {
					World.sendWorldMessage("<col=F16F0F><shad=600000>" + player.getDisplayName() + " has just unlocked the *Insane Final Boss* title!", true);
					player.insanefbtitle = true;
				}
				player.getAppearence().setTitle(67);
				player.getPackets().sendGameMessage("Title acquired.", true);
			}
			player.getPackets().sendIComponentText(INTER, 9, "This title is obtained when you have completed the requirements stated in the chat box.");
			player.sendMessage("<col==FF0000>You need a total of 5,000 boss kills and atleast 100 in the following bosses;");
			player.sendMessage("<col=FF0000>Corporeal beast, KBD, QBD, Bork, Nex, Glacors, Rise of the Six, Bandos, Saradomin, Zamorak, Armadyl, WildyWyrm, Aquatic Wyrm, Demi Gods,");
			player.sendMessage("<col=FF0000>Vorago, AOD, Gazer, Bad Santa, Dryax, Hope Devourer, Kalphite King, Zulrah, Necrolord, Thunderous, Sunfreet, Anivia, Sliske, Giant Mole, 3 Amigo's");
			player.sendMessage("<col=FF0000>Hydra, Celestia, KQ, Kraken, Sire, Cerberus, Sirenic, Callisto, Venenatis, Chaos Fanatic, Crazy Archaeologist, Scorpia, Vet'ion, Chambers of Xeric, Theatre of Blood, Skotizo & Lizardman!");
			break;
		case 23:
			if (player.BossKills() >= 1500) {
				player.getAppearence().setTitle(22906);
				player.getPackets().sendGameMessage("Title acquired.", true);
			}
			player.getPackets().sendIComponentText(INTER, 9, "This title is obtained when you have 1,500 boss kills.");
			break;
		case 24:
			if (player.playdays >= 25) {
				player.getAppearence().setTitle(22905);
				player.getPackets().sendGameMessage("Title acquired.", true);
			}
			player.getPackets().sendIComponentText(INTER, 9, "This title is obtained when you have 25 days playtime.");
			break;
		case 25:
			if (player.SunfreetKills >= 1000) {
				player.getAppearence().setTitle(22904);
				player.getPackets().sendGameMessage("Title acquired.", true);
			}
			player.getPackets().sendIComponentText(INTER, 9, "This title is obtained when you have killed Sunfreet 1,000 times.");
			break;
		case 26:
			if (player.BossKills() >= 15000) {
				player.getAppearence().setTitle(204);
				player.getPackets().sendGameMessage("Title acquired.", true);
			}
			player.getPackets().sendIComponentText(INTER, 9, "This title is obtained when you have 15,000 boss kills.");
			break;
		case 28:
			if (player.thunderous == true) {
				player.getAppearence().setTitle(55);
				player.getPackets().sendGameMessage("Title acquired.", true);
			}
			player.getPackets().sendIComponentText(INTER, 9, "This title is obtained when you have obtained it as a drop from Thunderous.");
			break;
		case 29:
			if (player.pillaroflighttouched >= 500) {
				player.getAppearence().setTitle(206);
				player.getPackets().sendGameMessage("Title acquired.", true);
			}
			player.getPackets().sendIComponentText(INTER, 9, "This title is obtained when you have looted 500 pillars of light.");
			break;
		case 30:
			if (player.templeoflightmummykills >= 200) {
				player.getAppearence().setTitle(22907);
				player.getPackets().sendGameMessage("Title acquired.", true);
			}
			player.getPackets().sendIComponentText(INTER, 9, "This title is obtained when you have killed 200 Mummies inside the Temple of Light.");
			break;
		case 31:
			if (player.isIronman()) {
			player.getAppearence().setTitle(9861);
			} else if (player.isEasy()) {
				player.getAppearence().setTitle(69);
			} else if (player.isAverage()) {
				player.getAppearence().setTitle(70);
			} else if (player.isHard()) {
				player.getAppearence().setTitle(1610);
			} else if (player.isHeroic()) {
				player.getAppearence().setTitle(58);
			}
			player.sendMessage("Title acquired.");
			player.getPackets().sendIComponentText(INTER, 9, "This title is set based on your game/xp mode.");
			break;
		case 32:
			if (player.runecoinsobtained >= 300) {
				player.getAppearence().setTitle(22908);
				player.getPackets().sendGameMessage("Title acquired.", true);
			}
			player.getPackets().sendIComponentText(INTER, 9, "This title is obtained when you have obtained 300 Runecoins via the recycle centre.");
			break;
		case 33:
			if (player.runecoinsobtained >= 1500) {
				player.getAppearence().setTitle(22909);
				player.getPackets().sendGameMessage("Title acquired.", true);
			}
			player.getPackets().sendIComponentText(INTER, 9, "This title is obtained when you have obtained 1,500 Runecoins via the recycle centre.");
			break;
		case 34:
			if (player.runecoinsobtained >= 5000) {
				player.getAppearence().setTitle(22910);
				player.getPackets().sendGameMessage("Title acquired.", true);
			}
			player.getPackets().sendIComponentText(INTER, 9, "This title is obtained when you have obtained 5,000 Runecoins via the recycle centre.");
			break;
		case 35:
			if (player.runecoinsobtained >= 7500) {
				player.getAppearence().setTitle(22911);
				player.getPackets().sendGameMessage("Title acquired.", true);
			}
			player.getPackets().sendIComponentText(INTER, 9, "This title is obtained when you have obtained 7,500 Runecoins via the recycle centre.");
			break;
		case 36:
			if (player.runecoinsobtained >= 10000) {
				player.getAppearence().setTitle(22912);
				player.getPackets().sendGameMessage("Title acquired.", true);
			}
			player.getPackets().sendIComponentText(INTER, 9, "This title is obtained when you have obtained 10,000 Runecoins via the recycle centre.");
			break;
		case 37:
			String totalxp = player.getSkills().getTotalXp(player);
			if (totalxp.equalsIgnoreCase("9000000000")) {
				player.getAppearence().setTitle(22914);
				player.getPackets().sendGameMessage("Title acquired.", true);
			}
			player.getPackets().sendIComponentText(INTER, 9, "This title is obtained when you have 9,000,000,000 total experience.");
			break;
		case 38:
			if (player.defenderofslisketitle == true) {
				player.getAppearence().setTitle(22915);
				player.getPackets().sendGameMessage("Title acquired.", true);
			}
			player.getPackets().sendIComponentText(INTER, 9, "This title is obtained when you have obtained it as a drop from Celestia, Defender of Sliske.");
			break;
		case 39:
			if (player.finishedeasterevent == true) {
				player.getAppearence().setTitle(22918);
				player.getPackets().sendGameMessage("Title acquired.", true);
			}
			player.getPackets().sendIComponentText(INTER, 9, "This title was obtained during the 2019 Easter event.");
			break;
		case 40:
			if (player.angrybunnytitle == true) {
				player.getAppearence().setTitle(22919);
				player.getPackets().sendGameMessage("Title acquired.", true);
			}
			player.getPackets().sendIComponentText(INTER, 9, "This title is obtained when you have obtained it as a drop from the Angry Easter Bunny.");
			break;
		case 41:
			if (player.theatreofbloodcompleted >= 500 && player.osrsraidscompleted >= 500) {
				player.getAppearence().setTitle(22920);
				player.getPackets().sendGameMessage("Title acquired.", true);
			}
			player.getPackets().sendIComponentText(INTER, 9, "This title is obtained when you have completed 500 of each OSRS raid.");
			break;
		case 42:
			if (player.theatreofbloodcompleted >= 500) {
				player.getAppearence().setTitle(22921);
				player.getPackets().sendGameMessage("Title acquired.", true);
			}
			player.getPackets().sendIComponentText(INTER, 9, "This title is obtained when you have completed 500 Theatre of Blood raids.");
			break;
		case 43:
			if (player.osrsraidscompleted >= 500) {
				player.getAppearence().setTitle(22922);
				player.getPackets().sendGameMessage("Title acquired.", true);
			}
			player.getPackets().sendIComponentText(INTER, 9, "This title is obtained when you have completed 500 Chambers of Xeric raids.");
			break;
		case 44:
			if (player.TotalOSRSRaids() >= 100) {
				player.getAppearence().setTitle(22923);
				player.getPackets().sendGameMessage("Title acquired.", true);
			}
			player.getPackets().sendIComponentText(INTER, 9, "This title is obtained when you have completed 100 combined total OSRS raids.");
			break;
			
			default:
				break;
		}
	}

}
