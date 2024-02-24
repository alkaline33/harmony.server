
package com.rs.game.player.content;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.item.Item;
import com.rs.game.player.Equipment;
import com.rs.game.player.Player;
import com.rs.game.player.QuestManager.Quests;
import com.rs.game.player.Skills;
import com.rs.game.player.interfaces.AchievementDiaryInter;
import com.rs.utils.Colors;

public class ItemConstants {

	public static int getDegradeItemWhenWear(int id) {
		// pvp armors
		if (id == 13958 || id == 13961 || id == 13964 || id == 13967
				|| id == 13970 || id == 13973 || id == 13858 || id == 13861
				|| id == 13864 || id == 13867 || id == 13870 || id == 13873
				|| id == 13876 || id == 13884 || id == 13887 || id == 13890
				|| id == 13893 || id == 13896 || id == 13899 || id == 13902
				|| id == 13905 || id == 13908 || id == 13911 || id == 13914
				|| id == 13917 || id == 13920 || id == 13923 || id == 13926
				|| id == 13929 || id == 13932 || id == 13935 || id == 13938
				|| id == 13941 || id == 13944 || id == 13947 || id == 13950
				|| id == 13958
				|| id == 30005 || id == 30008 || id == 30011
				|| id == 28608 || id == 28611 || id == 28614
				|| id == 29854 || id == 31189 || id == 31203) {
			return id + 2; // if you wear it it becomes corrupted LOL
		}
		return -1;
	}

	// return amt of charges
	public static int getItemDefaultCharges(int id) {
		// pvp armors
		if (id == 13910 || id == 13913 || id == 13916 || id == 13919
				|| id == 13922 || id == 13925 || id == 13928 || id == 13931
				|| id == 13934 || id == 13937 || id == 13940 || id == 13943
				|| id == 13946 || id == 13949 || id == 13952) {
			return 7200;
		}
		//lucky items
		if (id == 23679 || id == 23680|| id == 23681|| id == 23682|| id == 23683
				|| id == 23684|| id == 23685|| id == 23686|| id == 23687|| id == 23688
				|| id == 23689|| id == 23690|| id == 23691|| id == 23692|| id == 23693
				|| id == 23694|| id == 23695|| id == 23696|| id == 23697|| id == 23698
				|| id == 23699|| id == 23700) {
			return 30800;
		}
		if (id == 29953 || id == 29952 || id == 29951 || id == 29950 || id == 29949 || id == 29948 || id == 29947 || id == 29946 || id == 29945) {
			return 30000;
		}
		if (id == 13960 || id == 13963 || id == 13966 || id == 13969
				|| id == 13972 || id == 13975) {
			return 7200;
		}
		if (id == 13860 || id == 13863 || id == 13866 || id == 13869
				|| id == 13872 || id == 13875 || id == 13878 || id == 13886
				|| id == 13889 || id == 13892 || id == 13895 || id == 13898
				|| id == 13901 || id == 13904 || id == 13907 || id == 13960) {
			return 18400; // 2hour
		}
		//L90 gear
		if (id == 29733 || id == 29734 || id == 29145 || id == 29735 || id == 29736
				|| id == 29737 || id == 29730 || id == 29723 || id == 29726 || id == 29267 || id == 29251 || id == 29738 || id == 29739 || id == 29740) {
			return 30800; //8 hours
		}
		// nex armors
		if (id == 20137 || id == 20141 || id == 20145 || id == 20149
				|| id == 20153 || id == 20157 || id == 20161 || id == 20165
				|| id == 20169 || id == 20173
				|| id == 24975 || id == 24978 || id == 24981 || id == 24987
				|| id == 24990 || id == 24984) {
			return 36000;
		}
		if (id == 30010 || id == 30007 || id == 30013
			|| id == 29859 || id == 29862
			|| id == 28610 || id == 31191 || id == 28616  || id == 28613 || id == 31205) {
			return 43200;
		}
		if (id == 16753 || id == 16863 || id == 17235 || id == 17257|| id == 16709|| id == 17787) {
			return 50000; //10 hrs
		}
		
		return -1;
	}

	// return what id it degrades to, -1 for disapear which is default so we
	// dont add -1
	public static int getItemDegrade(int id) {
		if (id == 11285) {
			return 11283;
		}
		// nex armors
		if (id == 20137 || id == 20141 || id == 20145 || id == 20149
				|| id == 20153 || id == 20157 || id == 20161 || id == 20165
				|| id == 20169 || id == 20173 || id == 24975 || id == 24978 || id == 24981 || id == 24987
				|| id == 24990 || id == 24984) {
			return id + 1;
		} else if (id == 29738) {
		return id = 29746;
	} else if (id == 29739) {
		return id = 29747;
	} else if (id == 29740) {
		return id = 29748;
	} else if (id == 29737) {
		return id = 29745;
	} else if (id == 29735) {
		return id = 29743;
	} else if (id == 29736) {
		return id = 29744;
	} else if (id == 29734) {
		return id = 29742;
	} else if (id == 29145) {
		return id = 29144;
	} else if (id == 29733) {
		return id = 29741;
	} else if (id == 29730) {
		return id = 29729;
	} else if (id == 29726) {
		return id = 29725;
		} else if (id == 29267) {
			return id = 29266;
		} else if (id == 29251) {
			return id = 29250;
	}  else if (id == 29723) {
		return id = 29722;
	}
		return -1;
	}

	public static int getDegradeItemWhenCombating(int id) {
		// nex armors
	if (id == 20135 || id == 20139 || id == 20143 || id == 20147
				|| id == 20151 || id == 20155 || id == 20159 || id == 20163
				|| id == 20167 || id == 20171) {
			return id + 2;
	} else if (id == 24986 || id == 24989 || id == 24983 || id == 24980 || id == 24977 || id == 24974) {
		return id +1;
		} else if (id == 29888) {
			return id = 29738;
		} else if (id == 29889) {
			return id = 29739;
		} else if (id == 29890) {
			return id = 29740;
		} else if (id == 29846) {
			return id = 29737;
		} else if (id == 29845) {
			return id = 29735;
		} else if (id == 29844) {
			return id = 29736;
		} else if (id == 29941) {
			return id = 29734;
		} else if (id == 29940) {
			return id = 29145;
		} else if (id == 29939) {
			return id = 29733;
		}  else if (id == 29731) {
			return id = 29730;
		} else if (id == 29727) {
			return id = 29726;
		} else if (id == 29268) {
			return id = 29267;
		} else if (id == 29252) {
			return id = 29251;
		}  else if (id == 29724) {
			return id = 29723;
		}
			
		return -1;
}

	public static boolean itemDegradesWhileHit(int id) {
		if (id == 2550) {
			return true;
		}
		return false;
	}

	public static boolean itemDegradesWhileWearing(int id) {
		String name = ItemDefinitions.getItemDefinitions(id).getName()
				.toLowerCase();
		if (name.contains("c. dragon") || name.contains("corrupt dragon")
				|| name.contains("vesta's") || name.contains("statius'")
				|| name.contains("morrigan's") || name.contains("zuriel's")) {
			return true;
		}
		return false;
	}

	public static boolean itemDegradesWhileCombating(int id) {
		String name = ItemDefinitions.getItemDefinitions(id).getName()
				.toLowerCase();
		// nex armors
		if (name.contains("torva") || name.contains("pernix")
				|| name.contains("virtux") || name.contains("zaryte") || name.contains("sirenic") || name.contains("tectonic") || name.contains("malevolent")) {
			return true;
		}
		return false;
	}
	
	public static int getAssassinModels(int slot, boolean male) {
		switch (slot) {

			case Equipment.SLOT_HANDS:
				return male ? 2300 : 2300;
			case Equipment.SLOT_FEET:
				return male ? 16009 : 16011;
			case Equipment.SLOT_LEGS:
				return male ? 66540 : 66924;
			case Equipment.SLOT_CHEST:
				return male ? 66650 : 67035;
			case Equipment.SLOT_HAT:
				return male ? 187 : 363;
			case Equipment.SLOT_CAPE:
				return male ? 165548 : 165548;
			case Equipment.SLOT_WEAPON:
				return male ? 27855 : 27855;
		}
		return -1;
	}
	
	public static int getGreenTs(int slot, boolean male) {
		switch (slot) {

		case Equipment.SLOT_FEET:
			return male ? 71804 : 71862;
		case Equipment.SLOT_CAPE:
			return male ? 58 : 58;
			case Equipment.SLOT_HANDS:
				return male ? 66368 : 66368;
			case Equipment.SLOT_LEGS:
				return male ? 66553 : 66927;
			case Equipment.SLOT_CHEST:
				return male ? 66653 : 67042;
			case Equipment.SLOT_HAT:
				return male ? 66430 : 66811;
		}
		return -1;
	}
	
	public static int getRedTs(int slot, boolean male) {
		switch (slot) {

		case Equipment.SLOT_FEET:
			return male ? 71804 : 71862;
		case Equipment.SLOT_CAPE:
			return male ? 58 : 58;
		case Equipment.SLOT_HANDS:
			return male ? 66369 : 66369;
		case Equipment.SLOT_LEGS:
			return male ? 66540 : 66924;
		case Equipment.SLOT_CHEST:
			return male ? 66650 : 67035;
		case Equipment.SLOT_HAT:
			return male ? 66427 : 66816;
		}
		return -1;
	}

	public static boolean canWear(Item item, Player player) {
		if(item.getId() == 20769 || item.getId() == 29628 || item.getId() == 20771) {
		if (player.getRights() >= 2) {
			return true;
			} else {
			  player.getSkills();
				if (Skills.getTotalLevel(player) != 2496) {
		            player.getPackets().sendGameMessage("To wear this cape you need level 99 in all skills: ");
		            for (int skill = 0; skill < 24; skill++) {
		                if (player.getSkills().getLevelForXp(skill) == 99) {
							continue;
						}
		                return false;
		                
		            }
				}
				if (!player.CompletedAllQuests()) {
					player.getPackets().sendGameMessage("You need to complete all quests to use this.");
			return false;
		}
				 if (player.getSkills().getLevelForXp(Skills.DUNGEONEERING) != 120) {
		                player.getPackets().sendGameMessage("To wear this cape you need level 120 in the following: ");
		                player.getPackets().sendGameMessage("Dungeoneering ");
		                return false;
		                
		            }
				if (!AchievementDiaryInter.CompletedAllDiarys(player)) {
						player.sendMessage("<col=ff0000>You must complete the Achievement diary before wearing this.");
						//end();
						return false;
					}
					if (player.heistgamesplayed < 3) {
						player.getPackets().sendGameMessage("You need to complete 3 Heists to use this cape.");
						return false;
					}
			if(!player.isCompletedFightKiln()) {
				player.getPackets().sendGameMessage("You need to complete at least once fight kiln minigame to use this cape.");
				return false;
				}
			if (player.pwamountcompleted < 1) {
				player.getPackets().sendGameMessage("You need to complete Player wars at least once to use this cape..");
				return false;
			}
			if(!player.isWonFightPits()) {
			player.getPackets().sendGameMessage("You need to win at least once fight pits minigame to use this cape.");
			return false;
			}
			if (!player.isKilledQueenBlackDragon()) {
				player.getPackets().sendGameMessage("You need to have killed the Queen Black Dragon atleast once to use this cape.");
				return false;
			}
			if (player.getDominionTower().getKilledBossesCount() <= 99) {
				player.getPackets().sendGameMessage("You need to have kill atleast 100 bosses in the Dominion tower to use this cape.");
				return false;
			}
			if(player.cluescompleted < 3) {
				player.getPackets().sendGameMessage("You need to have completed atleast 3 clue scrolls to use this cape.");
				return false;
			}
			if (player.LogsCut() <= 2499) {
				player.getPackets().sendGameMessage("You need to have chopped 5000 Yew logs to use this cape.");
				return false;
			}
			if (player.BarSmelt() < 10) {
				player.getPackets().sendGameMessage("You need to have smelted 10 ancient bars to use this cape.");
				return false;
			}
			if (player.iseasy) {
				player.getPackets().sendGameMessage("You need to have Average xp rates to use this cape");
				return false;
			}
		//	if (player.Prestigetokens() < 1) {
			//	player.getPackets().sendGameMessage("You need to have prestiged atleast once to use this cape.");
			//	return false;
			//}
			if (player.royalcompmade == false) {
				player.getPackets().sendGameMessage("You need to have created the royal crossbow to use this cape.");
				return false;
			}
			//if (player.SedimentsObtained() <= 249) {
			//	player.getPackets().sendGameMessage("You need to have obtained at least 250 Dryaxions Sediments to use this cape.");
			//	return false;
			//}
			if (player.LogsBurnt() <= 2499) {
				player.getPackets().sendGameMessage("You need to have burnt 2500 magic logs to use this cape.");
				return false;
			}
			if (player.OreMined() <= 499) {
				player.getPackets().sendGameMessage("You need to have mined 500 runite ore to use this cape.");
				return false;
			}
			if (player.LapsRun() <= 499) {
				player.getPackets().sendGameMessage("You need to have completed 500 advanced barbarian agility laps to use this cape.");
				return false;
			}
			if (player.SlayTask() <= 79) {
				player.getPackets().sendGameMessage("You need to have completed 80 slayer tasks to use this cape.");
				return false;
			}
			if (player.FishCaught() <= 999) {
				player.getPackets().sendGameMessage("You need to have caught 1000 rocktails to use this cape.");
				return false;
			}
		}
		}
		if(item.getId() == 20767 || item.getId() == 20768) {
			 if (player.getSkills().getLevelForXp(Skills.DUNGEONEERING) < 99 || player.getSkills().getLevelForXp(Skills.SUMMONING) < 99 || player.getSkills().getLevelForXp(Skills.FARMING) < 99
					 || player.getSkills().getLevelForXp(Skills.WOODCUTTING) < 99 || player.getSkills().getLevelForXp(Skills.FIREMAKING) < 99 || player.getSkills().getLevelForXp(Skills.COOKING) < 99
					 || player.getSkills().getLevelForXp(Skills.FISHING) < 99 || player.getSkills().getLevelForXp(Skills.SMITHING) < 99 || player.getSkills().getLevelForXp(Skills.SMITHING) < 99
					 || player.getSkills().getLevelForXp(Skills.MINING) < 99 || player.getSkills().getLevelForXp(Skills.HUNTER) < 99 || player.getSkills().getLevelForXp(Skills.SLAYER) < 99
					 || player.getSkills().getLevelForXp(Skills.FLETCHING) < 99 || player.getSkills().getLevelForXp(Skills.CRAFTING) < 99 || player.getSkills().getLevelForXp(Skills.THIEVING) < 99
					 || player.getSkills().getLevelForXp(Skills.HERBLORE) < 99 || player.getSkills().getLevelForXp(Skills.AGILITY) < 99 || player.getSkills().getLevelForXp(Skills.CONSTRUCTION) < 99
					 || player.getSkills().getLevelForXp(Skills.RUNECRAFTING) < 99 || player.getSkills().getLevelForXp(Skills.MAGIC) < 99 || player.getSkills().getLevelForXp(Skills.PRAYER) < 99
					 || player.getSkills().getLevelForXp(Skills.RANGE) < 99 || player.getSkills().getLevelForXp(Skills.HITPOINTS) < 99 || player.getSkills().getLevelForXp(Skills.DEFENCE) < 99
					 || player.getSkills().getLevelForXp(Skills.STRENGTH) < 99 || player.getSkills().getLevelForXp(Skills.ATTACK) < 99) {
	                player.getPackets().sendGameMessage("To wear this cape you need level 99 in all skills.");
	                return false;
	            }
		}
		if(item.getId() == 20771) {
				if (player.getRights() >= 2) {
				return true;
				} else {
			 player.getSkills();
				if (Skills.getTotalLevel(player) != 2496) {
		            player.getPackets().sendGameMessage("To wear this cape you need level 99 in all skills: ");
		            for (int skill = 0; skill < 24; skill++) {
		                if (player.getSkills().getLevelForXp(skill) == 99) {
							continue;
						}
		                return false;
		            }
				}
				 if (player.getSkills().getLevelForXp(Skills.DUNGEONEERING) != 120) {
		                player.getPackets().sendGameMessage("To wear this cape you need level 120 in the following: ");
		                player.getPackets().sendGameMessage("Dungeoneering ");
		                return false;
		            }
				if (!AchievementDiaryInter.CompletedAllDiarysMaster(player)) {
						player.sendMessage("<col=ff0000>You must complete the Achievement diary before wearing this.");
						//end();
						return false;
					}
				if (!player.CompletedAllQuests()) {
					player.getPackets().sendGameMessage("You need to complete all quests to use this.");
			return false;
		}
					if (player.calamitytotalwavescomplete < 1000) {
						player.getPackets().sendGameMessage("You need to have completed a least 1,000 waves at the Calamity to use this cape.");
						return false;
					}
					if (player.heistgamesplayed < 3) {
						player.getPackets().sendGameMessage("You need to complete 3 Heists to use this cape.");
						return false;
					}
					if (player.pwamountcompleted < 1) {
						player.getPackets().sendGameMessage("You need to complete Player wars at least once to use this cape..");
						return false;
					}
			if(!player.isCompletedFightKiln()) {
				player.getPackets().sendGameMessage("You need to complete at least once fight kiln minigame to use this cape.");
				return false;
			}
			if(!player.isWonFightPits()) {
				player.getPackets().sendGameMessage("You need to win at least once fight pits minigame to use this cape.");
				return false;
			}
			if (player.kilnruns < 3) {
				player.getPackets().sendGameMessage("You need to have completed the fight kiln atleast 3 times to use this cape.");
				return false;
			}
			if (player.iseasy) {
				player.getPackets().sendGameMessage("You need to have Average xp rates to use this cape");
				return false;
			}
			if (player.Dboss < 20) {
				player.getPackets().sendGameMessage("You need to have killed the Bal'lak boss atleast 20 times to use this cape");
				return false;
			}
			if (player.GazerKills < 20) {
				player.getPackets().sendGameMessage("You need to have killed the Night-Gazer boss atleast 20 times to use this cape");
				return false;
			}
			if(player.getBandos() < 10) {
				player.getPackets().sendGameMessage("You need to have killed bandos at least 10 times to use this cape.");
				return false;
			}
			if (player.BarSmelt() < 10) {
				player.getPackets().sendGameMessage("You need to have collected 10 ancient bars to use this cape.");
				return false;
			}
			if(player.getSaradomin() < 10) {
				player.getPackets().sendGameMessage("You need to have killed saradomin at least 10 times to use this cape.");
				return false;
			}
			if(player.getZamorak() < 10) {
				player.getPackets().sendGameMessage("You need to have killed Zamorak at least 10 times to use this cape.");
				return false;
			}
			if(player.getArmadyl() < 10) {
				player.getPackets().sendGameMessage("You need to have killed armadyl at least 10 times to use this cape.");
				return false;
			}
			if(player.DungKills() < 250) {
				player.getPackets().sendGameMessage("You need to have killed atleast 250 High Level dung bosses to use this cape.");
				return false;
			}
			if (player.royalcompmade == false) {
				player.getPackets().sendGameMessage("You need to have created the royal crossbow to use this cape.");
				return false;
			}
			if (player.fbtitle == false) {
				player.getPackets().sendGameMessage("You need to have unlocked the Final Boss title to use this cape.");
				return false;
			}
			if (!player.isKilledQueenBlackDragon()) {
				player.getPackets().sendGameMessage("You need to have killed the Queen Black Dragon atleast once to use this cape.");
				return false;
			}
			if (player.getDominionTower().getKilledBossesCount() < 250) {
				player.getPackets().sendGameMessage("You need to have kill atleast 250 bosses in the Dominion tower to use this cape.");
				return false;
			}
			if (player.LogsCut() < 2500) {
				player.getPackets().sendGameMessage("You need to have chopped 2500 Yew logs to use this cape.");
				return false;
			}
			if (player.Prestigetokens() < 1) {
				player.getPackets().sendGameMessage("You need to have prestiged atleast once to use this cape.");
				return false;
			}
		//	if (player.SedimentsObtained() <= 249) {
		//		player.getPackets().sendGameMessage("You need to have obtained at least 250 Dryaxions Sediments to use this cape.");
		//	return false;
			//}
			if (player.LogsBurnt() < 2500) {
				player.getPackets().sendGameMessage("You need to have burnt 2500 logs to use this cape.");
				return false;
			}
			if (player.OreMined() < 500) {
				player.getPackets().sendGameMessage("You need to have mined 500 runite ore to use this cape.");
				return false;
			}
			if (player.LapsRun() < 500) {
				player.getPackets().sendGameMessage("You need to have completed 500 advanced barbarian agility laps to use this cape.");
				return false;
			}
			if (player.SlayTask() < 80) {
				player.getPackets().sendGameMessage("You need to have completed 80 slayer tasks to use this cape.");
				return false;
			}
			if (player.IsMusic() < 100) {
			player.getPackets().sendGameMessage("You need to have obtained 100 songs to use this cape.");
			return false;
			}
				if (player.VoragoKills < 1) {
				player.getPackets().sendGameMessage("You need to have personally killed vorago to use this cape!");
				return false;
			}
			if(player.divine == false) {
				player.getPackets().sendGameMessage("You need to have unlocked the divine title to use this cape!");
				return false;
			}
			
				}
			}
		
		
//		 if (item.getId() == 6570
//				|| item.getId() == 10566
//				|| item.getId() == 10637) { //temporary
//			if(!player.isCompletedFightCaves() && !player.isPvpMode()) {
//				player.getPackets().sendGameMessage("You need to complete at least once fight cave minigame to use this cape.");
//				return false;
//			}
		/*}else if (item.getId() == 1305
				|| item.getId() == 4587
				|| item.getId() == 1434
				|| item.getId() == 5680
				|| item.getId() == 5698
				|| item.getId() == 1215
				|| item.getId() == 1231
				|| item.getId() == 3204) { //dragon swords
			//if(player.lostCity == 0){
				//player.getPackets().sendGameMessage("You need to complete The Lost City quest to wear this sword.");
				return false;
			}*/
		// }else if (item.getId() == 23659 || item.getId() == 29855 || item.getId() ==
		// 29856) {
		// if(!player.isCompletedFightKiln() && !player.isPvpMode()) {
		// player.getPackets().sendGameMessage("You need to complete at least once fight
		// kiln minigame to use this cape.");
		// return false;
		// }
		if (item.getName().startsWith("Sirenic")) {
			if (player.getSkills().getLevelForXp(Skills.RANGE) < 90 || player.getSkills().getLevelForXp(Skills.DEFENCE) < 90) {
				player.sendMessage(Colors.red+"You need 90 Range & 90 Defence to wear this!");
				return false;
			}
		}
		else if (item.getId() == 29336) {
			if (player.getSkills().getLevelForXp(Skills.ATTACK) < 70 || player.getSkills().getLevelForXp(Skills.DEFENCE) < 70) {
				player.sendMessage("You need to have an attack and defence level of 70 to use this item.");
				return false;
			}
		}
		else if (item.getName().startsWith("Tectonic")) {
			if (player.getSkills().getLevelForXp(Skills.MAGIC) < 90 || player.getSkills().getLevelForXp(Skills.DEFENCE) < 90) {
				player.sendMessage(Colors.red+"You need 90 Magic & 90 Defence to wear this!");
				return false;
			}
		}
		else if (item.getName().startsWith("Malevolent")) {
			if (player.getSkills().getLevelForXp(Skills.STRENGTH) < 90 || player.getSkills().getLevelForXp(Skills.DEFENCE) < 90) {
				player.sendMessage(Colors.red+"You need 90 Strength & 90 Defence to wear this!");
				return false;
			}
		}
		else if (item.getId() == 29997) {
			 if (player.getSkills().getXp(Skills.AGILITY) < 104273167 ) {
	                player.getPackets().sendGameMessage("To wear this cape you need 104,273,167 experience in the following: ");
	                player.getPackets().sendGameMessage("Agility ");
	                return false;
	            }
		}else if (item.getId() == 29996) {
			 if (player.getSkills().getXp(Skills.ATTACK) < 104273167 ) {
	                player.getPackets().sendGameMessage("To wear this cape you need 104,273,167 experience in the following: ");
	                player.getPackets().sendGameMessage("Attack ");
	                return false;
	            }
		}else if (item.getId() == 29995) {
			 if (player.getSkills().getXp(Skills.CONSTRUCTION) < 104273167 ) {
	                player.getPackets().sendGameMessage("To wear this cape you need 104,273,167 experience in the following: ");
	                player.getPackets().sendGameMessage("Construction ");
	                return false;
	            }
		}else if (item.getId() == 29994) {
			 if (player.getSkills().getXp(Skills.COOKING) < 104273167 ) {
	                player.getPackets().sendGameMessage("To wear this cape you need 104,273,167 experience in the following: ");
	                player.getPackets().sendGameMessage("Cooking ");
	                return false;
	            }
		}else if (item.getId() == 29993) {
			 if (player.getSkills().getXp(Skills.CRAFTING) < 104273167 ) {
	                player.getPackets().sendGameMessage("To wear this cape you need 104,273,167 experience in the following: ");
	                player.getPackets().sendGameMessage("Crafting ");
	                return false;
	            }
		}else if (item.getId() == 29992) {
			 if (player.getSkills().getXp(Skills.DEFENCE) < 104273167 ) {
	                player.getPackets().sendGameMessage("To wear this cape you need 104,273,167 experience in the following: ");
	                player.getPackets().sendGameMessage("Defence ");
	                return false;
	            }
		}else if (item.getId() == 29991) {
			 if (player.getSkills().getXp(Skills.FARMING) < 104273167 ) {
	                player.getPackets().sendGameMessage("To wear this cape you need 104,273,167 experience in the following: ");
	                player.getPackets().sendGameMessage("Farming ");
	                return false;
	            }
		}else if (item.getId() == 29990) {
			 if (player.getSkills().getXp(Skills.FIREMAKING) < 104273167 ) {
	                player.getPackets().sendGameMessage("To wear this cape you need 104,273,167 experience in the following: ");
	                player.getPackets().sendGameMessage("Firemaking ");
	                return false;
	            }
		}else if (item.getId() == 29989) {
			 if (player.getSkills().getXp(Skills.FISHING) < 104273167 ) {
	                player.getPackets().sendGameMessage("To wear this cape you need 104,273,167 experience in the following: ");
	                player.getPackets().sendGameMessage("Fishing ");
	                return false;
	            }
		}else if (item.getId() == 29988) {
			 if (player.getSkills().getXp(Skills.FLETCHING) < 104273167 ) {
	                player.getPackets().sendGameMessage("To wear this cape you need 104,273,167 experience in the following: ");
	                player.getPackets().sendGameMessage("Fletching ");
	                return false;
	            }
		}else if (item.getId() == 29987) {
			 if (player.getSkills().getXp(Skills.HERBLORE) < 104273167 ) {
	                player.getPackets().sendGameMessage("To wear this cape you need 104,273,167 experience in the following: ");
	                player.getPackets().sendGameMessage("Herblore ");
	                return false;
	            }
		}else if (item.getId() == 29986) {
			 if (player.getSkills().getXp(Skills.HITPOINTS) < 104273167 ) {
	                player.getPackets().sendGameMessage("To wear this cape you need 104,273,167 experience in the following: ");
	                player.getPackets().sendGameMessage("Constitution ");
	                return false;
	            }
		}else if (item.getId() == 29985) {
			 if (player.getSkills().getXp(Skills.HUNTER) < 104273167 ) {
	                player.getPackets().sendGameMessage("To wear this cape you need 104,273,167 experience in the following: ");
	                player.getPackets().sendGameMessage("Hunter ");
	                return false;
	            }
		}else if (item.getId() == 29984) {
			 if (player.getSkills().getXp(Skills.MAGIC) < 104273167 ) {
	                player.getPackets().sendGameMessage("To wear this cape you need 104,273,167 experience in the following: ");
	                player.getPackets().sendGameMessage("Magic ");
	                return false;
	            }
		}else if (item.getId() == 29983) {
			 if (player.getSkills().getXp(Skills.MINING) < 104273167 ) {
	                player.getPackets().sendGameMessage("To wear this cape you need 104,273,167 experience in the following: ");
	                player.getPackets().sendGameMessage("Mining ");
	                return false;
	            }
		}else if (item.getId() == 29982) {
			 if (player.getSkills().getXp(Skills.PRAYER) < 104273167 ) {
	                player.getPackets().sendGameMessage("To wear this cape you need 104,273,167 experience in the following: ");
	                player.getPackets().sendGameMessage("Prayer ");
	                return false;
	            }
		}else if (item.getId() == 29981) {
			 if (player.getSkills().getXp(Skills.RANGE) < 104273167 ) {
	                player.getPackets().sendGameMessage("To wear this cape you need 104,273,167 experience in the following: ");
	                player.getPackets().sendGameMessage("Range ");
	                return false;
	            }
		}else if (item.getId() == 29980) {
			 if (player.getSkills().getXp(Skills.RUNECRAFTING) < 104273167 ) {
	                player.getPackets().sendGameMessage("To wear this cape you need 104,273,167 experience in the following: ");
	                player.getPackets().sendGameMessage("Runecrafting ");
	                return false;
	            }
		}else if (item.getId() == 29979) {
			 if (player.getSkills().getXp(Skills.SLAYER) < 104273167 ) {
	                player.getPackets().sendGameMessage("To wear this cape you need 104,273,167 experience in the following: ");
	                player.getPackets().sendGameMessage("Slayer ");
	                return false;
	            }
		}else if (item.getId() == 29978) {
			 if (player.getSkills().getXp(Skills.SMITHING) < 104273167 ) {
	                player.getPackets().sendGameMessage("To wear this cape you need 104,273,167 experience in the following: ");
	                player.getPackets().sendGameMessage("Smithing ");
	                return false;
	            }
		}else if (item.getId() == 29977) {
			 if (player.getSkills().getXp(Skills.STRENGTH) < 104273167 ) {
	                player.getPackets().sendGameMessage("To wear this cape you need 104,273,167 experience in the following: ");
	                player.getPackets().sendGameMessage("Strength ");
	                return false;
	            }
		}else if (item.getId() == 29976) {
			 if (player.getSkills().getXp(Skills.SUMMONING) < 104273167 ) {
	                player.getPackets().sendGameMessage("To wear this cape you need 104,273,167 experience in the following: ");
	                player.getPackets().sendGameMessage("Summoning ");
	                return false;
	            }
		}else if (item.getId() == 29975) {
			 if (player.getSkills().getXp(Skills.THIEVING) < 104273167 ) {
	                player.getPackets().sendGameMessage("To wear this cape you need 104,273,167 experience in the following: ");
	                player.getPackets().sendGameMessage("Thieving ");
	                return false;
	            }
		}else if (item.getId() == 29974) {
			 if (player.getSkills().getXp(Skills.WOODCUTTING) < 104273167 ) {
	                player.getPackets().sendGameMessage("To wear this cape you need 104,273,167 experience in the following: ");
	                player.getPackets().sendGameMessage("Woodcutting ");
	                return false;
	            }
//		} else if (item.getId() == 22358 || item.getId() == 22359 || item.getId() == 22360 || item.getId() == 22361 || item.getId() == 22362 || item.getId() == 22363 || item.getId() == 22364 || item.getId() == 22365 || item.getId() == 22366 || item.getId() == 22367 || item.getId() == 22368 || item.getId() == 22369)  {
//				if (player.getDominionTower().getKilledBossesCount() < 250) {
//					player.getPackets().sendGameMessage("You need to have kill atleast 250 bosses in the Dominion tower to use these gloves.");
//					return false;
//				}
		} else if (item.getId() == 29973 || item.getId() == 29972 || item.getId() == 29971 || item.getId() == 29968 || item.getId() == 29969 || item.getId() == 29970 || item.getId() == 29808
				|| item.getId() == 29809 || item.getId() == 7806 || item.getId() == 7808 || item.getId() == 7809 || item.getId() == 29810 || item.getId() == 29811 || item.getId() == 29812 || item.getId() == 29813 || item.getId() == 29156 || item.getId() == 29154 || item.getId() == 29152) {
			if (player.getSkills().getLevelForXp(Skills.DUNGEONEERING) < 90 || player.getSkills().getLevelForXp(Skills.STRENGTH) < 90 || player.getSkills().getLevelForXp(Skills.ATTACK) < 90) {
						player.getPackets().sendGameMessage("You need 90 Attack/Strength & 90 Dungeoneering to use this.");
				return false;
			}
		} else if (item.getId() == 9813 || item.getId() == 9814)  {
			if (!player.CompletedAllQuests()) {
						player.getPackets().sendGameMessage("You need to complete all quests to use this.");
				return false;
			}
		} else if (item.getId() == 28357)  {
			if (player.getSkills().getLevelForXp(Skills.DEFENCE) < 85) {
						player.getPackets().sendGameMessage("You need 85 Defence to use this.");
				return false;
			}
		} else if (item.getId() == 29900 || item.getId() ==  29899 || item.getId() ==  29898 || item.getId() ==  29897 || item.getId() ==  29896 || item.getId() ==  29895 || item.getId() ==  29894
				|| item.getId() ==  29893 || item.getId() ==  29892 )  {
			if (player.getSkills().getLevelForXp(Skills.DEFENCE) < 90) {
						player.getPackets().sendGameMessage("You need 90 Defence to use this.");
				return false;
			}
		} else if (item.getId() == 29783)  {
			if (player.getSkills().getLevelForXp(Skills.DUNGEONEERING) < 90 || player.getSkills().getLevelForXp(Skills.MAGIC) < 90) {
						player.getPackets().sendGameMessage("You need 90 Magic & 90 Dungeoneering to use this.");
				return false;
			}
		} else if (item.getId() == 29546)  {
			if (player.getSkills().getLevelForXp(Skills.DUNGEONEERING) < 92 || player.getSkills().getLevelForXp(Skills.MAGIC) < 92) {
						player.getPackets().sendGameMessage("You need 92 Magic & 92 Dungeoneering to use this.");
				return false;
			}
		} else if (item.getId() == 29891)  {
			if (player.getSkills().getLevelForXp(Skills.PRAYER) < 80 || player.getSkills().getLevelForXp(Skills.DEFENCE) < 90) {
						player.getPackets().sendGameMessage("You need 80 Prayer & 90 Defence to use this.");
				return false;
			}
		} else if (item.getId() == 29470)  {
			if (player.getSkills().getLevelForXp(Skills.ATTACK) < 75) {
						player.getPackets().sendGameMessage("You need 75 attack to use this.");
				return false;
			}
		} else if (item.getId() == 29463)  {
			if (player.getSkills().getLevelForXp(Skills.MAGIC) < 75) {
						player.getPackets().sendGameMessage("You need 75 magic to use this.");
				return false;
			}
		} else if (item.getId() == 10828)  {
			if (player.getSkills().getLevelForXp(Skills.DEFENCE) <55) {
						player.getPackets().sendGameMessage("You need a level of 55 defence to use this.");
				return false;
			}
		} else if (item.getId() == 29571)  {
			if (player.getSkills().getLevelForXp(Skills.STRENGTH) < 85 || player.getSkills().getLevelForXp(Skills.DEFENCE) < 85) {
						player.getPackets().sendGameMessage("You need 85 Strength & Defence to use these.");
				return false;
			}
		} else if (item.getId() == 29570)  {
			if (player.getSkills().getLevelForXp(Skills.MAGIC) < 85 || player.getSkills().getLevelForXp(Skills.DEFENCE) < 85) {
						player.getPackets().sendGameMessage("You need 85 Magic & Defence to use these.");
				return false;
			}
		} else if (item.getId() == 29569)  {
			if (player.getSkills().getLevelForXp(Skills.RANGE) < 85 || player.getSkills().getLevelForXp(Skills.DEFENCE) < 85) {
						player.getPackets().sendGameMessage("You need 85 Range & Defence to use these.");
				return false;
			}
		} else if (item.getId() == 22348)  {
			if (player.getSkills().getLevelForXp(Skills.RANGE) < 85) {
						player.getPackets().sendGameMessage("You need 85 Range to use this.");
				return false;
			}
		} else if (item.getId() == 22347)  {
			if (player.getSkills().getLevelForXp(Skills.MAGIC) < 85) {
						player.getPackets().sendGameMessage("You need 85 Magic to use this.");
				return false;
			}
		} else if (item.getId() == 29649)  {
			if (player.getSkills().getLevelForXp(Skills.DUNGEONEERING) < 90 || player.getSkills().getLevelForXp(Skills.ATTACK) < 90|| player.getSkills().getLevelForXp(Skills.STRENGTH) < 90) {
						player.getPackets().sendGameMessage("You need 90 Attack/Strength & 90 Dungeoneering to use this.");
				return false;
			}
		} else if (item.getId() == 29545)  {
			if (player.getSkills().getLevelForXp(Skills.DUNGEONEERING) < 92 || player.getSkills().getLevelForXp(Skills.ATTACK) < 92|| player.getSkills().getLevelForXp(Skills.STRENGTH) < 92) {
						player.getPackets().sendGameMessage("You need 92 Attack/Strength & 92 Dungeoneering to use this.");
				return false;
			}
		} else if (item.getId() == 29967 || item.getId() == 29782 || item.getId() == 29938 || item.getId() == 29918 || item.getId() == 29806 || item.getId() == 29807 || item.getId() == 29149 || item.getId() == 29150) {
			if (player.getSkills().getLevelForXp(Skills.DUNGEONEERING) < 90 || player.getSkills().getLevelForXp(Skills.RANGE) < 90) {
						player.getPackets().sendGameMessage("You need 90 Range & 90 Dungeoneering to use this.");
				return false;
			}
		} else if (item.getId() == 29547)  {
			if (player.getSkills().getLevelForXp(Skills.DUNGEONEERING) < 92 || player.getSkills().getLevelForXp(Skills.RANGE) < 92) {
						player.getPackets().sendGameMessage("You need 92 Range & 92 Dungeoneering to use this.");
				return false;
			}
		} else if (item.getId() == 29605 || item.getId() == 29604 || item.getId() == 29603)  {
			if (player.getSkills().getLevelForXp(Skills.DEFENCE) < 85 || player.getSkills().getLevelForXp(Skills.RANGE) < 85) {
						player.getPackets().sendGameMessage("You need 85 Range & 85 Defence to use this.");
				return false;
			}
		} else if (item.getId() == 16753 || item.getId() == 16863 || item.getId() == 17235)  {
			if (player.getSkills().getLevelForXp(Skills.DEFENCE) < 90 || player.getSkills().getLevelForXp(Skills.MAGIC) < 90) {
						player.getPackets().sendGameMessage("You need 90 Magic & 90 Defence to use this.");
				return false;
			}
		} else if (item.getId() == 29939 || item.getId() == 29940 || item.getId() == 29941)  {
			if (player.getSkills().getLevelForXp(Skills.DEFENCE) < 90 || player.getSkills().getLevelForXp(Skills.STRENGTH) < 90) {
						player.getPackets().sendGameMessage("You need 90 Strength & 90 Defence to use this.");
				return false;
			}
		} else if (item.getId() == 29705)  {
			if (player.getSkills().getLevelForXp(Skills.STRENGTH) < 80 || player.getSkills().getLevelForXp(Skills.ATTACK) < 80) {
						player.getPackets().sendGameMessage("You need 80 Strength & 80 Attack to use this.");
				return false;
			}
		} else if (item.getId() == 29642)  {
			if (player.getSkills().getLevelForXp(Skills.STRENGTH) < 80 || player.getSkills().getLevelForXp(Skills.MAGIC) < 80 || player.getSkills().getLevelForXp(Skills.RANGE) < 80 || player.getSkills().getLevelForXp(Skills.ATTACK) < 80) {
						player.getPackets().sendGameMessage("You need 80 in magic, range, attack and strength to use this.");
				return false;
			}
		} else if (item.getId() == 29731 || item.getId() == 29730 || item.getId() == 29732 || item.getId() == 29727 || item.getId() == 29726 || item.getId() == 29724 || item.getId() == 29723)  {
			if (player.getSkills().getLevelForXp(Skills.DEFENCE) < 90 || player.getSkills().getLevelForXp(Skills.RANGE) < 90 || player.getSkills().getLevelForXp(Skills.MAGIC) < 90 || player.getSkills().getLevelForXp(Skills.STRENGTH) < 90 || player.getSkills().getLevelForXp(Skills.ATTACK) < 90) {
						player.getPackets().sendGameMessage("You need 90 in all combat stats, excluding summoning and prayer to use this.");
				return false;
			}
		} else if (item.getId() == 29890 || item.getId() == 29889 || item.getId() == 29888)  {
			if (player.getSkills().getLevelForXp(Skills.DEFENCE) < 90 || player.getSkills().getLevelForXp(Skills.RANGE) < 90) {
						player.getPackets().sendGameMessage("You need 90 Range & 90 Defence to use this.");
				return false;
			}
		} else if (item.getId() == 29943 || item.getId() == 29944 || item.getId() == 29844 || item.getId() == 29845 || item.getId() == 29846 || item.getId() == 29814 || item.getId() == 29815 || item.getId() == 29158 || item.getId() == 29157) {
			if (player.getSkills().getLevelForXp(Skills.DUNGEONEERING) < 90 || player.getSkills().getLevelForXp(Skills.MAGIC) < 90) {
						player.getPackets().sendGameMessage("You need 90 Magic & 90 Dungeoneering to use this.");
				return false;
			}
		} else if (item.getId() == 29966)  {
			if (player.getSkills().getLevelForXp(Skills.MAGIC) < 80 || player.getSkills().getLevelForXp(Skills.DEFENCE) < 80) {
						player.getPackets().sendGameMessage("You need 80 Magic & 80 Defence to use this.");
				return false;
			}
		} else if (item.getId() == 29617)  {
			if (player.getSkills().getLevelForXp(Skills.MAGIC) < 85 || player.getSkills().getLevelForXp(Skills.ATTACK) < 85) {
						player.getPackets().sendGameMessage("You need 85 Magic & 85 Attack to use this.");
				return false;
			}
		} else if (item.getId() == 29932 || item.getId() == 29933 || item.getId() == 29934)  {
			if (player.getSkills().getLevelForXp(Skills.DEFENCE) < 90) {
						player.getPackets().sendGameMessage("You need 90 Defence to use this.");
				return false;
			}
		} else if (item.getId() == 29622 || item.getId() == 29621 || item.getId() == 29620 || item.getId() == 29619 || item.getId() == 29618)  {
			if (player.getSkills().getLevelForXp(Skills.DEFENCE) < 85) {
						player.getPackets().sendGameMessage("You need 85 Defence to use this.");
				return false;
			}
		} else if (item.getId() == 29749 || item.getId() == 29750 || item.getId() == 29751)  {
			if (player.getSkills().getLevelForXp(Skills.RANGE) < 85 || player.getSkills().getLevelForXp(Skills.DEFENCE) < 85) {
						player.getPackets().sendGameMessage("You need 85 Range & 85 Defence to use this.");
				return false;
			}
				if (player.calamitytotalwavescomplete < 300) {
					player.getPackets().sendGameMessage("You need to have completed at least 300 Calamity waves to wear this.");
			return false;
			}
		} else if (item.getId() == 29752 || item.getId() == 29753 || item.getId() == 29754)  {
			if (player.getSkills().getLevelForXp(Skills.MAGIC) < 85 || player.getSkills().getLevelForXp(Skills.DEFENCE) < 85) {
						player.getPackets().sendGameMessage("You need 85 Magic & 85 Defence to use this.");
				return false;
			}
			if (player.calamitytotalwavescomplete < 300) {
				player.getPackets().sendGameMessage("You need to have completed at least 300 Calamity waves to wear this.");
		return false;
		}
		} else if (item.getId() == 29707 || item.getId() == 29710)  {
			if (player.getSkills().getLevelForXp(Skills.MAGIC) < 85 || player.getSkills().getLevelForXp(Skills.DEFENCE) < 85) {
						player.getPackets().sendGameMessage("You need 85 Magic & 85 Defence to use this.");
				return false;
			}
		} else if (item.getId() == 29708 || item.getId() == 29711)  {
			if (player.getSkills().getLevelForXp(Skills.RANGE) < 85 || player.getSkills().getLevelForXp(Skills.DEFENCE) < 85) {
						player.getPackets().sendGameMessage("You need 85 Range & 85 Defence to use this.");
				return false;
			}
		} else if (item.getId() == 29709 || item.getId() == 29712)  {
			if (player.getSkills().getLevelForXp(Skills.STRENGTH) < 85 || player.getSkills().getLevelForXp(Skills.DEFENCE) < 85) {
						player.getPackets().sendGameMessage("You need 85 Strength & 85 Defence to use this.");
				return false;
			}
		/*		if (player.calamitytotalwavescomplete < 800) {
					player.getPackets().sendGameMessage("You need to have completed at least 800 Calamity waves to wear this.");
			return false;
			}*/
		} else if (item.getId() == 29755 || item.getId() == 29756 || item.getId() == 29757)  {
			if (player.getSkills().getLevelForXp(Skills.STRENGTH) < 85 || player.getSkills().getLevelForXp(Skills.DEFENCE) < 85) {
						player.getPackets().sendGameMessage("You need 85 Strength & 85 Defence to use this.");
				return false;
			}
				if (player.calamitytotalwavescomplete < 300) {
					player.getPackets().sendGameMessage("You need to have completed at least 300 Calamity waves to wear this.");
			return false;
			}
		} else if (item.getId() == 29869)  {
			if (player.getSkills().getLevelForXp(Skills.ATTACK) < 85) {
						player.getPackets().sendGameMessage("You need 85 Attack to use this.");
				return false;
			}
		} else if (item.getId() == 8850)  {
			if (player.getSkills().getLevelForXp(Skills.DEFENCE) < 40) {
						player.getPackets().sendGameMessage("You need 40 Defence to use this.");
				return false;
			}
		} else if (item.getId() == 29908)  {
			if (player.getSkills().getLevelForXp(Skills.DUNGEONEERING) < 80 || player.getSkills().getLevelForXp(Skills.ATTACK) < 80) {
						player.getPackets().sendGameMessage("You need 80 Dungeoneering & 80 Attack to use this.");
				return false;
			}
		} else if (item.getId() >= 28976 && item.getId() <= 28978)  {
			if (player.prestigeTokens < 1) {
						player.getPackets().sendGameMessage("You must have prestiged to use this!");
				return false;
			}
		} else if (item.getId() == 14645
				|| item.getId() == 15433
				|| item.getId() == 15435
				|| item.getId() == 15432
				|| item.getId() == 15434) {
			if(!player.getQuestManager().completedQuest(Quests.NOMADS_REQUIEM)) {
				player.getPackets().sendGameMessage("You need to have completed Nomad's Requiem miniquest to use this cape.");
				return false;
			}
		}
		String itemName = item.getName();
		switch (item.getId()) {
		case 21790: //glaiven boots
			if (player.getSkills().getLevelForXp(Skills.DEFENCE) < 75 || player.getSkills().getLevelForXp(Skills.RANGE) < 75) {
				player.sendMessage("You need a defence and ranging level of 75 to wear this.");
				return false;
			}
			return true;
		case 6739: //dragon hatchet
		case 29435: //dragon harpoon
		case 29412: //infernal hatchet
			if (player.getSkills().getLevelForXp(Skills.ATTACK) < 60) {
				player.sendMessage("You need an attack level of 60 to wield this.");
				return false;
			}
			return true;
		}
		return true;
	}
	
	public static boolean DyeItems(Item item) {
		switch (item.getId()) {
		case 29825:
		case 29824:
		case 29823:
		case 29822:
		case 29821:
		case 29820:
		case 29819:
		case 29818:
		case 29817:
		return false;
		default:
			return true;
		}
	}
	
	public static boolean noShopSell(Item item) {
		switch (item.getId()) {
		case 29760:
		case 1038:
		case 1039:
		case 1040:
		case 1041:
		case 1042:
		case 1043:
		case 10506:
		case 29572:
		case 1044:
		case 1045:
		case 1046:
		case 1047:
		case 1048:
		case 1049:
		case 1050:
		case 1051:
		case 1052:
		case 1053:
		case 1054:
		case 1055:
		case 1056:
		case 1057:
		case 1058:
		case 4566:
		case 962:
		case 963:
		case 1961:
		case 1962:
		case 1959:
		case 1960:
		case 981:
		case 982:
		case 13316: //staff item
		case 13317: //staff item
		case 19747: //staff item
		case 20084: //staff item
		case 20428: //MOD ITEM
		case 9005: //staff item
		case 22958:
		case 22959:
		case 22960:
		case 22961:
		case 22962:
		case 13536:
		case 13535:
		case 13534:
		case 13533:
		case 13532:
		case 13531:
		case 5020:
		case 5023:
		return false;
		default:
			return true;
		}
	}
	public static boolean isTradeable(Item item) {
		if (item.getDefinitions().isDestroyItem()
				|| item.getDefinitions().isLended()
				|| ItemConstants.getItemDefaultCharges(item.getId()) != -1) {
			return false;
		}
		if (item.getDefinitions().getName().toLowerCase().contains("flaming skull") || item.getDefinitions().getName().toLowerCase().contains("master cape")  || item.getDefinitions().getName().toLowerCase().contains("overloaded flask") || item.getDefinitions().getName().toLowerCase().contains("overpray flask") || item.getDefinitions().getName().toLowerCase().contains("overkill flask") || item.getDefinitions().getName().toLowerCase().contains("super saradomin brew flask") || item.getDefinitions().getName().toLowerCase().contains("clue scroll") || item.getDefinitions().getName().toLowerCase().contains("lucky ") || item.getDefinitions().getName().toLowerCase().contains("slayer helm") || item.getDefinitions().getName().toLowerCase().contains("xp lamp")) {
			return false;
		}
		switch (item.getId()) {
		case 29294:
		case 29003:
		case 7409:
		case 19889:
		case 28920:
		case 28960:
		case 19785:
		case 19786:
		case 13666:
		case 28929:
		case 28921:
		case 28928:
		case 28919:
		case 28904:
		case 28908:
		case 20787:
		case 20788:
		case 28917:
		case 28916:
		case 28915:
		case 28924:
		case 28923:
		case 20789:
		case 28922:
		case 20790:
		case 20791:
		case 28926:
		case 20792:
		case 24423:
		case 28925:
		case 24424:
		case 24425:
		case 24426:
		case 21439:
		case 28913:
		case 28911:
		case 28910:
		case 28912:
		case 28909:
		case 21440:
		case 21441:
		case 21442:
		case 21443:
		case 28927:
		case 28969:
		case 29002:
		case 28935:
		case 28930:
		case 29210:
		case 28975:
		case 28959:
		case 28991:
		case 25202:
		case 28932:
		case 29336:
		case 28974:
		case 28933:
		case 28990:
		case 28984:
		case 28982:
		case 28981:
		case 28979:
		case 28980:
		case 28989:
		case 28983:
		case 29001:
		case 29184:
		case 28988:
		case 28987:
		case 28986:
		case 29000:
		case 28999:
		case 28998:
		case 28997:
		case 28996:
		case 28995:
		case 28994:
		case 28993:
		case 28992:
		case 29008:
		case 29009:
		case 29043:
		case 29042:
		case 29006:
		case 29010:
		case 29044:
		case 29045:
		case 29055:
		case 29013:
		case 29012:
		case 29046:
		case 29827:
		case 29124:
		case 29106:
		case 29056:
		case 29105:
		case 29104:
		case 29103:
		case 29175:
		case 29176:
		case 29113:
		case 29177:
		case 29061:
		case 29222:
		case 29119:
		case 29118:
		case 29558:
		case 29141:
		case 29143:
		case 29128:
		case 29209:
		case 29353:
		case 29182:
		case 12496:
		case 29191:
		case 12469:
		case 29189:
		case 29185:
		case 12473:
		case 29197:
		case 29190:
		case 12475:
		case 12471:
		case 13335:
		case 12481:
		case 14533:
		case 29797:
		case 29206:
		case 29200:
		case 29293:
		case 29292:
		case 29253:
		case 29201:
		case 29291:
		case 29211:
		case 29212:
		case 29216:
		case 29208:
		case 1720:
		case 29224:
		case 29327:
		case 29221:
		case 29219:
		case 29218:
		case 18340:
		case 15420:
		case 29276:
		case 12076:
		case 18339:
		case 29247:
		case 29248:
		case 29277:
		case 29225:
		case 29226:
		case 29278:
		case 29255:
		case 29279:
		case 29280:
		case 29286:
		case 10933:
		case 10939:
		case 10940:
		case 10941:
		case 10945:
		case 18346:
		case 19675:
		case 29326:
		case 29320:
		case 29318:
		case 29415:
		case 29442:
		case 29767:
		case 2717:
		case 29298:
		case 29768:
		case 29317:
		case 29769:
		case 29770:
		case 29630:
		case 29631:
		case 29305:
		case 29632:
		case 29633:
		case 29380:
		case 29410:
		case 15352:
		case 15353:
		case 29325:
		case 29324:
		case 29323:
		case 29322:
		case 29321:
		case 29403:
		case 29409:
		case 29408:
		case 29407:
		case 29406:
		case 29405:
		case 29404:
		case 29449:
		case 29437:
		case 29525:
		case 29524:
		case 29523:
		case 29522:
		case 29521:
		case 29520:
		case 23194:
		case 29639:
		case 29640:
		case 29641:
		case 14057:
		case 29675:
		case 29526:
		case 3064:
		case 29674:
		case 29673:
		case 20121:
		case 20122:
		case 20123:
		case 20124:
		case 16754:
		case 16864:
		case 16930:
		case 17170:
		case 17236:
		case 18349: //crapier
		case 18350: //crapier
		case 18351: //clong
		case 18352: //clong
		case 18353: //cmaul
		case 18355: //cstaff
		case 18357: //cbow
		case 29702:
		case 29701:
		case 29700:
		case 29699:
		case 29698:
		case 29697:
		case 29703:
		case 29704:
		case 29706:
		case 29778:
		case 29776:
		case 29775:
		case 29847:
		case 29848:
		case 29849:
		case 29850:
		case 29796:
		case 29786:
		case 29851:
		case 29852:
		case 29842:
		case 29841:
		case 16687:
		case 29865:
		case 29853:
		case 29864:
		case 29863:
		case 29862:
		case 29861:
		case 29859:
		case 29860:
		case 29760:
		case 13536:
		case 13535:
		case 13534:
		case 13533:
		case 4671:
		case 13532:
		case 9067: //dream log
		case 11951: //snowman
		case 12140:
		case 12141:
		case 12142:
		case 12143:
		case 12144:
		case 12145:
		case 12146:
		case 12147:
		case 29904:
		case 29905:
		case 29906:
		case 29907:
		case 13531:
		case 13316: //staff item
		case 13317: //staff item
		case 19747: //staff item
		case 20084: //staff item
		case 20428: //MOD ITEM
		case 9005: //staff item
		case 22958:
		case 22959:
		case 22960:
		case 22961:
		case 22962:
		case 6570: //firecape
		case 6529: //tokkul
		case 23659: //tookhaar-kal
		case 20770: //comp hood
		case 20772: //comp hood
		case 20767: //maxcape
		case 20768: //max hood
		case 22370: //dreadnip
		case 15332:
		case 15333:
		case 15334:
		case 15335:
		case 23531:
		case 23532:
		case 23533:
		case 23534:
		case 23535:
		case 23536: //overloads
		case 6666://flippers
		case 8031: //Con bed
		case 8323: //Con curtains
		case 8325: //Con Fireplace
		case 8317: //Con Rug
		case 8054: //Con clock
		case 8366: //Con leaver
		case 22423: //Con ele balance
		case 18359: //ckite
		case 18361: //eagle
		case 18363: //farseerkite
		case 18335: //arcanestream
		case 19669: //ring of V
		case 18337: //Bcrusher
		case 18347: //mercGloves
		case 24154: //spin ticket
		case 24155: //D spin ticket
		case 19709: //Dungmaster
		case 8844:
		case 8845:
		case 8846:
		case 8847:
		case 8848:
		case 8849:
		case 8850:
		case 20072: //defenders
		case 12527: //charms
		case 23030: //baby troll
		case 773: //Admin Ring
		case 5733: //Admin Potato
		case 714: //Admin Item
		case 715: //Admin Item
		case 717: //Admin Item
		case 718: //Admin Item
		case 624: //Admin Item
		case 15098: //1-100 dice
		case 21512: //jad pet
		case 21452: //walking stick
		case 9625: //Crystal saw
		case 19636: //jointed log
		case 11788: //voice of doom
		case 19658: //herb
		case 19659: // herb
		case 7927: //easter Ring
		case 10729: //Easter ring
		case 33211:
		case 33210:
		case 33209:
		case 33208:
		case 33207:
		case 33206:
		case 33205:
		case 33204:
		case 33203:
		case 33202:
		case 33201:
		case 33200:
			
			return false;
		default:
			return true;
		}
	}
}
