package com.rs.game.player.dialogues.impl;

import com.rs.game.World;
import com.rs.game.player.dialogues.Dialogue;

public class Titles extends Dialogue {

	private int npcId = 6185;

	@Override
	public void start() {
		// npcId = (Integer) parameters[0];
		sendNPCDialogue(npcId, 9845, "Hey " + player.getUsername() + ", I'm Mr Title. I reward players with titles when they achieve, what can i do for you?");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			sendOptionsDialogue("Mr Title", "Show me the titles.", "Nothing");
			stage = 2;
		} else if (stage == 2) {
			if (componentId == OPTION_1) {
				sendOptionsDialogue("Reward Titles", "the Completionist", "Maxed", "the Defeater", "Divine", "Next Page");
				stage = 4;
			}
			if (componentId == OPTION_2) {
				end();
			}
		} else if (stage == 4) {
			if (componentId == OPTION_1) {
				if (player.comped == false) {
					sendNPCDialogue(npcId, 9827, "You need to have achieved the completionist cape to use this title.");
					stage = 3;
					return;
				}
				player.getAppearence().setTitle(34);
				end();
				stage = 3;
			}
			if (componentId == OPTION_2) {
				if (player.maxed == false) {
					sendNPCDialogue(npcId, 9827, "You need to have achieved the max cape to use this title.");
					stage = 3;
					return;
				}
				player.getAppearence().setTitle(80);
				end();
				stage = 3;
			}
			if (componentId == OPTION_3) {
				if (player.VoragoKills < 1) {
					sendNPCDialogue(npcId, 9827, "You need to have defeated vorago to use this title.");
					stage = 3;
					return;
				}
				player.getAppearence().setTitle(32);
				end();
				stage = 3;
			}
			if (componentId == OPTION_4) {
				if (player.divine == false) {
					sendNPCDialogue(npcId, 9827, "You need to have receivedthe divine sigil as a drop to use this title.");
					stage = 3;
					return;
				}
				player.getAppearence().setTitle(813700);
				end();
				stage = 3;
			}
			if (componentId == OPTION_5) {
				sendOptionsDialogue("Reward Titles", "of Zamorak", "of Bandos", "of Saradomin", "of Armadyl", "Next Page");
				stage = 5;
			}
		} else if (stage == 5) {
			if (componentId == OPTION_1) {
				if (player.zamorak < 50) {
					sendNPCDialogue(npcId, 9827, "You need to have killed the Zamorak boss atleast 50 times to use this title.");
					stage = 3;
					return;
				}
				player.getAppearence().setTitle(38);
				end();
				stage = 3;
			}
			if (componentId == OPTION_2) {
				if (player.bandos < 50) {
					sendNPCDialogue(npcId, 9827, "You need to have killed the Bandos boss atleast 50 times to use this title.");
					stage = 3;
					return;
				}
				player.getAppearence().setTitle(35);
				end();
				stage = 3;
			}
			if (componentId == OPTION_3) {
				if (player.saradomin < 50) {
					sendNPCDialogue(npcId, 9827, "You need to have killed the Saradomin boss atleast 50 times to use this title.");
					stage = 3;
					return;
				}
				player.getAppearence().setTitle(36);
				end();
				stage = 3;
			}
			if (componentId == OPTION_4) {
				if (player.armadyl < 50) {
					sendNPCDialogue(npcId, 9827, "You need to have killed the Armadyl boss atleast 50 times to use this title.");
					stage = 3;
					return;
				}
				player.getAppearence().setTitle(37);
				end();
				stage = 3;
			}
			if (componentId == OPTION_5) {
				sendOptionsDialogue("Reward Titles", "of Christmas", "the Dreamer", "Final Boss", "Insane Final Boss", "Next page");
				stage = 6;
			}
		} else if (stage == 6) {
			if (componentId == OPTION_1) {
				if (player.santatitle == false) {
					sendNPCDialogue(npcId, 9827, "You need to have killed Bad Santa to use this title.");
					stage = 3;
					return;
				}
				player.getAppearence().setTitle(56);
				end();
				stage = 3;
			}
			if (componentId == OPTION_2) {
				if (player.dreamcut >= 1000) {
					sendNPCDialogue(npcId, 9827, "You need to have cut 1000 dream logs to use this title.");
					stage = 3;
					return;
				}
				player.getAppearence().setTitle(33);
				end();
				stage = 3;
			}
			if (componentId == OPTION_3) {
				if (player.FinalBossTitle(player) == false) {
					sendNPCDialogue(npcId, 9827, "You need a total of 4000 boss kills and atleast 50 kills of the following bosses;");
					player.sendMessage("<col=FF0000>Corporeal beast, KBD, QBD, Bork, Nex, Glacors, Rise of the Six, Bandos, Saradomin, Zamorak, Armadyl, WildyWyrm, Aquatic Wyrm, Demi Gods,");
					player.sendMessage("<col=FF0000>Vorago, AOD, Gazer, Bad Santa, Dryax, Hope Devourer, Kalphite King, Zulrah, Necrolord, Thunderous, Sunfreet, Anivia, Sliske, Giant Mole,");
					player.sendMessage("<col=FF0000>Hydra, Celestia, KQ, Kraken, Sire, Cerberus, Sirenic, Callisto, Venenatis, Chaos Fanatic, Crazy Archaeologist, Scorpia, Vet'ion, Chambers of Xeric, Theatre of Blood, Skotizo & Lizardman!");
					stage = 3;
					return;
				}
				if (player.fbtitle == false) {
					World.sendWorldMessage("<col=FF0000><shad=600000>" + player.getDisplayName() + " has just unlocked the *Final Boss* title!", true);
					player.fbtitle = true;
				}
				player.getAppearence().setTitle(66);
				end();
				stage = 3;
			}
			if (componentId == OPTION_4) {
				if (player.InsaneFinalBossTitle(player) == false) {
					sendNPCDialogue(npcId, 9827, "You need a total of 5000 boss kills and atleast 100 kills of the following bosses;");
					player.sendMessage("<col=FF0000>Corporeal beast, KBD, QBD, Bork, Nex, Glacors, Rise of the Six, Bandos, Saradomin, Zamorak, Armadyl, WildyWyrm, Aquatic Wyrm, Demi Gods,");
					player.sendMessage("<col=FF0000>Vorago, AOD, Gazer, Bad Santa, Dryax, Hope Devourer, Kalphite King, Party Demon, Zulrah, Necrolord, Thunderous, Sunfreet, Anivia, Sliske");
					player.sendMessage("<col=FF0000>Hydra, Celestia, KQ, Kraken, Sire, Cerberus, Sirenic, Callisto, Venenatis, Chaos Fanatic, Crazy Archaeologist, Scorpia, Vet'ion, Chambers of Xeric, Theatre of Blood, Skotizo & Lizardman!");
					stage = 3;
					return;
				}
				if (player.insanefbtitle == false) {
					World.sendWorldMessage("<col=F16F0F><shad=600000>" + player.getDisplayName() + " has just unlocked the *Insane Final Boss* title!", true);
					player.fbtitle = true;
				}
				player.getAppearence().setTitle(67);
				end();
				stage = 3;
			}
			if (componentId == OPTION_5) {
				sendOptionsDialogue("Reward Titles", "Warmonger", "Lieutenant Commander", "Sun Killer", "Defender of Harmony", "Next Page");
				stage = 7;
			}
		} else if (stage == 7) {
			if (componentId == OPTION_1) {
				if (player.BossKills() < 1500) {
					sendNPCDialogue(npcId, 9827, "You need a total of 1,500 boss kills to use this title");
					stage = 3;
					return;
				}
				player.getAppearence().setTitle(22906);
				end();
				stage = 3;
			}
			if (componentId == OPTION_2) {
				if (player.playdays < 25) {
					sendNPCDialogue(npcId, 9827, "You need at least 25 days playtime to use this title.");
					stage = 3;
					return;
				}
				player.getAppearence().setTitle(22905);
				end();
				stage = 3;
			}
			if (componentId == OPTION_3) {
				if (player.SunfreetKills < 1000) {
					sendNPCDialogue(npcId, 9827, "You must have slain Sunfreet 1,000 times before using this title.");
					stage = 3;
					return;
				}
				player.getAppearence().setTitle(22904);
				end();
				stage = 3;
			}
			if (componentId == OPTION_4) {
				if (player.BossKills() < 15000) {
					sendNPCDialogue(npcId, 9827, "You need a total of 15,000 boss kills to use this title.");
					stage = 3;
					return;
				}
				player.getAppearence().setTitle(204);
				end();
				stage = 3;
			}
			if (componentId == OPTION_5) {
				sendOptionsDialogue("Reward Titles", "the Thunderous", "the Light", "Mummified", "Xp Mode Title", "Next Page");
				stage = 8;
			}
		} else if (stage == 9) {
			if (componentId == OPTION_1) {
				if (player.runecoinsobtained < 300) {
					sendNPCDialogue(npcId, 9827, "You need to have obtained 300 Runecoins from recycling items in the recycling centre to use this!");
					stage = 3;
					return;
				}
				player.getAppearence().setTitle(22908);
				end();
				stage = 3;
			} else if (componentId == OPTION_2) {
				if (player.runecoinsobtained < 1500) {
					sendNPCDialogue(npcId, 9827, "You need to have obtained 1,500 Runecoins from recycling items in the recycling centre to use this!");
					stage = 3;
					return;
				}
				player.getAppearence().setTitle(22909);
				end();
				stage = 3;
			} else if (componentId == OPTION_3) {
				if (player.runecoinsobtained < 5000) {
					sendNPCDialogue(npcId, 9827, "You need to have obtained 5,000 Runecoins from recycling items in the recycling centre to use this!");
					stage = 3;
					return;
				}
				player.getAppearence().setTitle(22910);
				end();
				stage = 3;
			} else if (componentId == OPTION_4) {
				if (player.runecoinsobtained < 7500) {
					sendNPCDialogue(npcId, 9827, "You need to have obtained 7,500 Runecoins from recycling items in the recycling centre to use this!");
					stage = 3;
					return;
				}
				player.getAppearence().setTitle(22911);
				end();
				stage = 3;
			} else if (componentId == OPTION_5) {
				sendOptionsDialogue("Reward Titles", "the Toxic", "Experience Master", ", Defender of Sliske", "the Egg Collector", "Next Page");// the Bunny Killer
				stage = 10;
			}
		} else if (stage == 10) {
			if (componentId == OPTION_1) {
				if (player.runecoinsobtained < 10000) {
					sendNPCDialogue(npcId, 9827, "You need to have obtained 10,000 Runecoins from recycling items in the recycling centre to use this!");
					stage = 3;
					return;
				}
				player.getAppearence().setTitle(22912);
				end();
				stage = 3;
			} else if (componentId == OPTION_2) {
				String totalxp = player.getSkills().getTotalXp(player);
				if (!totalxp.equalsIgnoreCase("9000000000")) {
					sendNPCDialogue(npcId, 9827, "You must have a total of 9,000,000,000 experience to use this title!");
					stage = 3;
					return;
				}
				player.getAppearence().setTitle(22914);
				end();
				stage = 3;
			} else if (componentId == OPTION_3) {
				if (player.defenderofslisketitle != true) {
					sendNPCDialogue(npcId, 9827, "You must have obtained the title from Celestia, Defender of Sliske first!");
					stage = 3;
					return;
				}
				player.getAppearence().setTitle(22915);
				end();
				stage = 3;
			} else if (componentId == OPTION_4) {
				if (player.finishedeasterevent != true) {
					sendNPCDialogue(npcId, 9827, "You must have collected all the eggs for the Easter bunny during the 2019 event to use this title.");
					stage = 3;
					return;
				}
				player.getAppearence().setTitle(22918);
				end();
				stage = 3;
			} else if (componentId == OPTION_5) {
				sendOptionsDialogue("Reward Titles", "the Bunny Killer", "Raids master", "the Blood", "of the Chambers", "Raider");// the Bunny Killer
				stage = 11;
			}
		} else if (stage == 11) {
			if (componentId == OPTION_1) {
				if (player.angrybunnytitle != true) {
					sendNPCDialogue(npcId, 9827, "You must have obtained this as a drop from the Angry Easter Bunny.");
					stage = 3;
					return;
				}
				player.getAppearence().setTitle(22919);
				end();
				stage = 3;
			} else if (componentId == OPTION_2) {
						if (player.theatreofbloodcompleted < 500 || player.osrsraidscompleted < 500) {
							sendNPCDialogue(npcId, 9827, "You must have atleast 500 Chambers of Xeric raids & Theatre of blood raids completed to use this.");
							stage = 3;
							return;
						}
						player.getAppearence().setTitle(22920);
						end();
						stage = 3;
			} else if (componentId == OPTION_3) {
				if (player.theatreofbloodcompleted < 500) {
					sendNPCDialogue(npcId, 9827, "You must have atleast 500 Theatre of blood raids completed to use this.");
					stage = 3;
					return;
				}
				player.getAppearence().setTitle(22921);
				end();
				stage = 3;
			} else if (componentId == OPTION_4) {
				if (player.osrsraidscompleted < 500) {
					sendNPCDialogue(npcId, 9827, "You must have atleast 500 Chambers of Xeric raids raids completed to use this.");
					stage = 3;
					return;
				}
				player.getAppearence().setTitle(22922);
				end();
				stage = 3;
			} else if (componentId == OPTION_5) {
				if (player.TotalOSRSRaids() < 100) {
					sendNPCDialogue(npcId, 9827, "You must have completed atleast 100 OSRS raids to use this.");
					stage = 3;
					return;
				}
				player.getAppearence().setTitle(22923);
				end();
				stage = 3;
			}
		} else if (stage == 8) {
			if (componentId == OPTION_1) {
				if (player.thunderous != true) {
					sendNPCDialogue(npcId, 9827, "You need to have obtained this as a drop from Yk'Lagor the Thunderous!");
					stage = 3;
					return;
				}
				player.getAppearence().setTitle(55);
				end();
				stage = 3;
			} else if (componentId == OPTION_2) {
				if (player.pillaroflighttouched < 500) {
					sendNPCDialogue(npcId, 9827, "You must have looted 500 Pillars of Lights to use this title.");
					stage = 3;
					return;
				}
				player.getAppearence().setTitle(206);
				end();
				stage = 3;
			} else if (componentId == OPTION_3) {
				if (player.templeoflightmummykills < 200) {
					sendNPCDialogue(npcId, 9827, "You must have killed 200 Mummies inside the Temple of Light to use this title.");
					stage = 3;
					return;
				}
				player.getAppearence().setTitle(22907);
				end();
				stage = 3;
			} else if (componentId == OPTION_4) {
				if (player.isEasy()) {
					player.getAppearence().setTitle(69);
				} else if (player.isAverage()) {
					player.getAppearence().setTitle(70);
				} else if (player.isHard()) {
					player.getAppearence().setTitle(1610);
				} else if (player.isIronman()) {
					player.getAppearence().setTitle(9861);
				} else if (player.isHeroic()) {
					player.getAppearence().setTitle(58);
				}
				// player.sendMessage("nope");
				end();
				stage = 3;
			} else if (componentId == OPTION_5) {
				sendOptionsDialogue("Reward Titles", "Recycled", "the Regenerated", "Eco-friendly", "Biodegradeable", "Next Page");
				stage = 9;
			}
		} else if (stage == 6) {
			if (componentId == OPTION_1) {
				if (player.satantitle == false) {
					sendNPCDialogue(npcId, 9827, "You need to have purchased this title from The Calamity!");
					stage = 3;
					return;
				}
				player.getAppearence().setTitle(200);
				end();
				stage = 3;
			}
			if (componentId == OPTION_2) {
				if (player.undeadtitle == false) {
					sendNPCDialogue(npcId, 9827, "You need to have purchased this title from The Calamity!");
					stage = 3;
					return;
				}
				player.getAppearence().setTitle(201);
				end();
				stage = 3;
			}
			if (componentId == OPTION_3) {
				if (player.osteologytitle == false) {
					sendNPCDialogue(npcId, 9827, "You need to have purchased this title from The Calamity!");
					stage = 3;
					return;
				}
				player.getAppearence().setTitle(202);
				end();
				stage = 3;
			}
			if (componentId == OPTION_4) {
				if (player.warriorcalatitle == false) {
					sendNPCDialogue(npcId, 9827, "You need to have purchased this title from The Calamity!");
					stage = 3;
					return;
				}
				player.getAppearence().setTitle(203);
				end();
				stage = 3;
			}
			if (componentId == OPTION_5) {
				end();
				stage = 3;
			}
		} else if (stage == 3) {
			end();
		}
	}

	@Override
	public void finish() {

	}

}