package com.rs.game.player.interfaces;

import com.rs.game.World;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.content.event.season.SeasonEvent;
import com.rs.utils.Colors;
import com.rs.utils.ShopsHandler;
import com.rs.utils.Utils;

/**
 * 
 * @author Mr_Joopz
 *
 */

public class AchievementDiaryInter {

	static int INTER = 3027;

	public static void OpenInterface(Player player) {
		/**
		 * Pre update checks
		 */
		int seals = player.RaptorgetDropLog().getContainer().getNumberOf(28975);
		player.raptorseals = seals;
		if (player.getInventory().contains(29216) && player.dropboostimade != true)
			player.dropboostimade = true;

		if (player.chaoticupgraded != true && player.getInventory().contains(29410)
				&& player.getInventory().contains(29409) && player.getInventory().contains(29408)
				&& player.getInventory().contains(29407) && player.getInventory().contains(29406)
				&& player.getInventory().contains(29357) && player.getInventory().contains(29294)
				&& player.getInventory().contains(29293) && player.getInventory().contains(29292)
				&& player.getInventory().contains(29291))
			player.chaoticupgraded = true;

		if (player.elitevoidmade != true && player.getInventory().contains(19785)
				&& player.getInventory().contains(19786))
			player.elitevoidmade = true;

		int pointvary = player.isHeroic() ? 200 : player.isHard() ? 175 : player.isAverage() ? 150 : 100;
		player.achievementdiarypage = 1;
		player.achievementdiaryslot = 1;
		player.getInterfaceManager().sendInterface(INTER);
		player.getPackets().sendIComponentText(INTER, 16, "<col=8A2BE2>Easy Achievement Diary");
		/**
		 * Diary
		 */
		player.getPackets().sendIComponentText(INTER, 34,
				"" + (player.easycchestclaimed ? Colors.green : Colors.red) + "Crystal Chest");
		player.getPackets().sendIComponentText(INTER, 38,
				"" + (player.easycluelessclaimed ? Colors.green : Colors.red) + "Clueless");
		player.getPackets().sendIComponentText(INTER, 46,
				"" + (player.easymobsclaimed ? Colors.green : Colors.red) + "Mobs");
		player.getPackets().sendIComponentText(INTER, 47,
				"" + (player.easyrockyclaimed ? Colors.green : Colors.red) + "Rocky");
		player.getPackets().sendIComponentText(INTER, 48,
				"" + (player.easyborkclaimed ? Colors.green : Colors.red) + "Boooork");
		player.getPackets().sendIComponentText(INTER, 51,
				"" + (player.easybosskillerclaimed ? Colors.green : Colors.red) + "Boss Killer");
		player.getPackets().sendIComponentText(INTER, 52,
				"" + (player.easygiantkillerclaimed ? Colors.green : Colors.red) + "Giant Killer");
		player.getPackets().sendIComponentText(INTER, 29,
				"" + (player.easyslayerapprenticeclaimed ? Colors.green : Colors.red) + "Slayer Apprentice");
		player.getPackets().sendIComponentText(INTER, 54,
				"" + (player.easybrothersclaimed ? Colors.green : Colors.red) + "Brothers");
		player.getPackets().sendIComponentText(INTER, 65,
				"" + (player.easyvoterclaimed ? Colors.green : Colors.red) + "Voter");
		player.getPackets().sendIComponentText(INTER, 66,
				"" + (player.easyattackclaimed ? Colors.green : Colors.red) + "Attack");
		player.getPackets().sendIComponentText(INTER, 67,
				"" + (player.easystrengthclaimed ? Colors.green : Colors.red) + "Strength");
		player.getPackets().sendIComponentText(INTER, 68,
				"" + (player.easydefenceclaimed ? Colors.green : Colors.red) + "Defence");
		player.getPackets().sendIComponentText(INTER, 69,
				"" + (player.easyhpclaimed ? Colors.green : Colors.red) + "Hitpoints");
		player.getPackets().sendIComponentText(INTER, 70,
				"" + (player.easyrangedclaimed ? Colors.green : Colors.red) + "Ranged");
		player.getPackets().sendIComponentText(INTER, 71,
				"" + (player.easymagicclaimed ? Colors.green : Colors.red) + "Magic");
		player.getPackets().sendIComponentText(INTER, 72,
				"" + (player.easyprayerclaimed ? Colors.green : Colors.red) + "Prayer");
		player.getPackets().sendIComponentText(INTER, 73,
				"" + (player.easysummoningclaimed ? Colors.green : Colors.red) + "Summoning");
		player.getPackets().sendIComponentText(INTER, 74,
				"" + (player.easyslayerclaimed ? Colors.green : Colors.red) + "Slayer");
		player.getPackets().sendIComponentText(INTER, 75,
				"" + (player.easyrcclaimed ? Colors.green : Colors.red) + "Runecrafting");
		player.getPackets().sendIComponentText(INTER, 76,
				"" + (player.easyconstructionclaimed ? Colors.green : Colors.red) + "Construction");
		player.getPackets().sendIComponentText(INTER, 77,
				"" + (player.easydungeoneeringclaimed ? Colors.green : Colors.red) + "Dungeoneering");
		player.getPackets().sendIComponentText(INTER, 78,
				"" + (player.easyagilityclaimed ? Colors.green : Colors.red) + "Agility");
		player.getPackets().sendIComponentText(INTER, 79,
				"" + (player.easyherbloreclaimed ? Colors.green : Colors.red) + "Herblore");
		player.getPackets().sendIComponentText(INTER, 80,
				"" + (player.easythievingclaimed ? Colors.green : Colors.red) + "Thieving");
		player.getPackets().sendIComponentText(INTER, 81,
				"" + (player.easycraftingclaimed ? Colors.green : Colors.red) + "Crafting");
		player.getPackets().sendIComponentText(INTER, 82,
				"" + (player.easyfletchingclaimed ? Colors.green : Colors.red) + "Fletching");
		player.getPackets().sendIComponentText(INTER, 83,
				"" + (player.easyhunterclaimed ? Colors.green : Colors.red) + "Hunter");
		player.getPackets().sendIComponentText(INTER, 84,
				"" + (player.easyminingclaimed ? Colors.green : Colors.red) + "Mining");
		player.getPackets().sendIComponentText(INTER, 85,
				"" + (player.easysmithingclaimed ? Colors.green : Colors.red) + "Smithing");
		player.getPackets().sendIComponentText(INTER, 86,
				"" + (player.easyfishingclaimed ? Colors.green : Colors.red) + "Fishing");
		player.getPackets().sendIComponentText(INTER, 87,
				"" + (player.easycookingclaimed ? Colors.green : Colors.red) + "Cooking");
		player.getPackets().sendIComponentText(INTER, 88,
				"" + (player.easyfiremakingclaimed ? Colors.green : Colors.red) + "Firemaking");
		player.getPackets().sendIComponentText(INTER, 89,
				"" + (player.easywoodcuttingclaimed ? Colors.green : Colors.red) + "Woodcutting");
		player.getPackets().sendIComponentText(INTER, 90,
				"" + (player.easyfarmingclaimed ? Colors.green : Colors.red) + "Farming");

		/**
		 * Description + progress
		 */
		player.getPackets().sendIComponentText(INTER, 40,
				"Open 10 Regular Crystal chests. <br><br>Progress " + player.ckeyused + "/10");
		/**
		 * Rewards
		 */
		player.getPackets().sendIComponentText(INTER, 95,
				"<col=ff0000>Rewards will vary based on your<br><col=ff0000> experience mode.");

		player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>1 Diary Point");
		player.getSpareContainer().getContainer().clear();
		player.getSpareContainer().getContainer().add(new Item(29425, 1));
		player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
		player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
		player.getSpareContainer().getContainer().shift();
		player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
		return;

	}

	public static void OpenMasterPage(Player player) {
		int pointvary = player.isHeroic() ? 1000 : player.isHard() ? 850 : player.isAverage() ? 700 : 550;
		player.achievementdiarypage = 5;
		player.achievementdiaryslot = 1;
		player.getInterfaceManager().sendInterface(INTER);
		player.getPackets().sendIComponentText(INTER, 16, "<col=8A2BE2>Master Achievement Diary");
		/**
		 * Diary
		 */
		player.getPackets().sendIComponentText(INTER, 34,
				"" + (player.mastercchestclaimed ? Colors.green : Colors.red) + "Crystal King");
		player.getPackets().sendIComponentText(INTER, 38,
				"" + (player.mastercluesclaimed ? Colors.green : Colors.red) + "Master Clue");
		player.getPackets().sendIComponentText(INTER, 46,
				"" + (player.mastermobsclaimed ? Colors.green : Colors.red) + "Mob King");
		player.getPackets().sendIComponentText(INTER, 47,
				"" + (player.masterbossesclaimed ? Colors.green : Colors.red) + "the Boss");
		player.getPackets().sendIComponentText(INTER, 48,
				"" + (player.masterraidsclaimed ? Colors.green : Colors.red) + "King of Raids");
		player.getPackets().sendIComponentText(INTER, 51,
				"" + (player.masterragingclaimed ? Colors.green : Colors.red) + "0 to 100");
		player.getPackets().sendIComponentText(INTER, 52,
				"" + (player.masterheistclaimed ? Colors.green : Colors.red) + "Stolen Goods");
		player.getPackets().sendIComponentText(INTER, 29,
				"" + (player.masterslayertargetsclaimed ? Colors.green : Colors.red) + "Death");
		player.getPackets().sendIComponentText(INTER, 54,
				"" + (player.masterrecycleclaimed ? Colors.green : Colors.red) + "Recycle Master");
		player.getPackets().sendIComponentText(INTER, 65,
				"" + (player.masterwildernessclaimed ? Colors.green : Colors.red) + "Deep & Dark");
		player.getPackets().sendIComponentText(INTER, 66,
				"" + (player.masterdyedclaimed ? Colors.green : Colors.red) + "I Dyed");
		player.getPackets().sendIComponentText(INTER, 67,
				"" + (player.masterdungclaimed ? Colors.green : Colors.red) + "Why?");
		player.getPackets().sendIComponentText(INTER, 68,
				"" + (player.masterptclaimed ? Colors.green : Colors.red) + "I Am Known");
		player.getPackets().sendIComponentText(INTER, 69,
				"" + (player.mastervoteclaimed ? Colors.green : Colors.red) + "#1 Voter");
		player.getPackets().sendIComponentText(INTER, 70,
				"" + (player.mastersealclaimed ? Colors.green : Colors.red) + "Seal It");
		player.getPackets().sendIComponentText(INTER, 71,
				"" + (player.mastercelestiaclaimed ? Colors.green : Colors.red) + "Defender");
		player.getPackets().sendIComponentText(INTER, 72,
				"" + (player.masterraptorclaimed ? Colors.green : Colors.red) + "Rap God");
		player.getPackets().sendIComponentText(INTER, 73,
				"" + (player.mastertrollclaimed ? Colors.green : Colors.red) + "Trolled");
		player.getPackets().sendIComponentText(INTER, 74,
				"" + (player.masterwellclaimed ? Colors.green : Colors.red) + "Rich Boi");
		player.getPackets().sendIComponentText(INTER, 75,
				"" + (player.mastercalaclaimed ? Colors.green : Colors.red) + "Brain Dead");
		player.getPackets().sendIComponentText(INTER, 76,
				"" + (player.mastertrollpotclaimed ? Colors.green : Colors.red) + "Haha...");
		player.getPackets().sendIComponentText(INTER, 77,
				"" + (player.masterblinkclaimed ? Colors.green : Colors.red) + "Blinked");
		player.getPackets().sendIComponentText(INTER, 78,
				"" + (player.masterseasonclaimed ? Colors.green : Colors.red) + "Seasonal");
		player.getPackets().sendIComponentText(INTER, 79,
				"" + (player.masterthclaimed ? Colors.green : Colors.red) + "Pirate");
		player.getPackets().sendHideIComponent(INTER, 80, true);
		player.getPackets().sendHideIComponent(INTER, 81, true);
		player.getPackets().sendHideIComponent(INTER, 82, true);
		player.getPackets().sendHideIComponent(INTER, 83, true);
		player.getPackets().sendHideIComponent(INTER, 84, true);
		player.getPackets().sendHideIComponent(INTER, 85, true);
		player.getPackets().sendHideIComponent(INTER, 86, true);
		player.getPackets().sendHideIComponent(INTER, 87, true);
		player.getPackets().sendHideIComponent(INTER, 88, true);
		player.getPackets().sendHideIComponent(INTER, 89, true);
		player.getPackets().sendHideIComponent(INTER, 90, true);

		/**
		 * Description + progress
		 */
		player.getPackets().sendIComponentText(INTER, 40,
				"Open 500 Regular Crystal chests. <br><br>Progress " + player.ckeyused + "/500");
		/**
		 * Rewards
		 */
		player.getPackets().sendIComponentText(INTER, 95,
				"<col=ff0000>Rewards will vary based on your<br><col=ff0000> experience mode.");

		player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>5 Diary Point");
		player.getSpareContainer().getContainer().clear();
		player.getSpareContainer().getContainer().add(new Item(29425, 5));
		player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
		player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
		player.getSpareContainer().getContainer().shift();
		player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
		return;

	}

	public static void HandleMasterRewards(Player player, int componentId) {
		int pointvary = player.isHeroic() ? 1000 : player.isHard() ? 850 : player.isAverage() ? 700 : 550;
		switch (player.achievementdiaryslot) {
		case 1:
			if (player.mastercchestclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.mastercchestclaimed != true && player.ckeyused >= 500) {
				player.getInventory().addItemDrop(29425, 5);
				player.diarypoints += 5;
				player.Drypoints += pointvary;
				player.mastercchestclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 2:
			if (player.mastercluesclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.mastercluesclaimed != true && player.cluescompleted >= 500) {
				player.getInventory().addItemDrop(28921, 1);
				player.diarypoints += 5;
				player.Drypoints += pointvary;
				player.mastercluesclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 3:
			if (player.mastermobsclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.mastermobsclaimed != true && player.TotalKills() >= 15000) {
				player.getInventory().addItemDrop(5022, 100);
				player.diarypoints += 5;
				player.Drypoints += pointvary;
				player.mastermobsclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 4:
			if (player.masterbossesclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.masterbossesclaimed != true && player.BossKills() >= 5000) {
				player.getInventory().addItemDrop(29540, 3);
				player.diarypoints += 5;
				player.Drypoints += pointvary;
				player.masterbossesclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 5:
			if (player.masterraidsclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.masterraidsclaimed != true && player.TotalOSRSRaids() >= 250) {
				player.getInventory().addItemDrop(25353, 1);
				player.diarypoints += 5;
				player.Drypoints += pointvary;
				player.masterraidsclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 6:
			if (player.masterragingclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.masterragingclaimed != true && player.ragingcasketcount >= 250) {
				player.getInventory().addItemDrop(29363, 3);
				player.diarypoints += 5;
				player.Drypoints += pointvary;
				player.masterragingclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 7:
			if (player.masterheistclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.masterheistclaimed != true && player.heistcashbagsdeposit >= 100) {
				player.getInventory().addItemDrop(5022, 30);
				player.diarypoints += 5;
				player.Drypoints += pointvary;
				player.masterheistclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 8:
			if (player.masterslayertargetsclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.masterslayertargetsclaimed != true && player.slayertargetkills >= 5000) {
				player.getInventory().addItemDrop(29374, 2);
				player.diarypoints += 5;
				player.Drypoints += pointvary;
				player.masterslayertargetsclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 9:
			if (player.masterrecycleclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.masterrecycleclaimed != true && player.runecoinsobtained >= 50000) {
				player.getInventory().addItemDrop(29256, 500);
				player.diarypoints += 5;
				player.Drypoints += pointvary;
				player.masterrecycleclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 10:
			if (player.masterwildernessclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.masterwildernessclaimed != true && player.wildernesstaskcompleted >= 100) {
				player.getInventory().addItemDrop(20806, 1);
				player.diarypoints += 5;
				player.Drypoints += pointvary;
				player.masterwildernessclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 11:
			if (player.masterdyedclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.masterdyedclaimed != true && player.dyesusedt90 >= 3) {
				player.getInventory().addItemDrop(29426, 1);
				player.diarypoints += 5;
				player.Drypoints += pointvary;
				player.masterdyedclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 12:
			if (player.masterdungclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.masterdungclaimed != true && player.dunggkills >= 500) {
				player.getInventory().addItemDrop(17143, 1);
				player.diarypoints += 5;
				player.Drypoints += pointvary;
				player.masterdungclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 13:
			if (player.masterptclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.masterptclaimed != true && player.isVeteran()) {
				player.getInventory().addItemDrop(25202, 10);
				player.diarypoints += 5;
				player.Drypoints += pointvary;
				player.masterptclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 14:
			if (player.mastervoteclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.mastervoteclaimed != true && player.getvotepoints() >= 250) {
				player.getInventory().addItemDrop(25202, 10);
				player.diarypoints += 5;
				player.Drypoints += pointvary;
				player.mastervoteclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 15:
			if (player.mastersealclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.mastersealclaimed != true && player.raptorseals >= 9) {
				player.getInventory().addItemDrop(29540, 2);
				player.diarypoints += 5;
				player.Drypoints += pointvary;
				player.mastersealclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 16:
			if (player.mastercelestiaclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.mastercelestiaclaimed != true && player.CelestiaKills >= 100) {
				player.getInventory().addItemDrop(19889, 1);
				player.diarypoints += 5;
				player.Drypoints += pointvary;
				player.mastercelestiaclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 17:
			if (player.masterraptorclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.masterraptorclaimed != true && player.RaptorKills >= 100) {
				player.getInventory().addItemDrop(28974, 1);
				player.diarypoints += 5;
				player.Drypoints += pointvary;
				player.masterraptorclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 18:
			if (player.mastertrollclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.mastertrollclaimed != true && player.BigtrollKills >= 100) {
				player.getInventory().addItemDrop(29654, 1);
				player.diarypoints += 5;
				player.Drypoints += pointvary;
				player.mastertrollclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 19:
			if (player.masterwellclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.masterwellclaimed != true && player.getTotalDonatedToWell() >= 2000000000) {
				player.getInventory().addItemDrop(29353, 1);
				player.diarypoints += 5;
				player.Drypoints += pointvary;
				player.masterwellclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 20:
			if (player.mastercalaclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.mastercalaclaimed != true && player.calamitybestwave >= 58) {
				player.getInventory().addItemDrop(28922, 2);
				player.diarypoints += 5;
				player.Drypoints += pointvary;
				player.mastercalaclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 21:
			if (player.mastertrollpotclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.mastertrollpotclaimed != true && player.usedtrollpotionhydrix) {
				player.getInventory().addItemDrop(3265, 2);
				player.diarypoints += 5;
				player.Drypoints += pointvary;
				player.mastertrollpotclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 22:
			if (player.masterblinkclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.masterblinkclaimed != true && player.BlinkKills >= 10) {
				player.getInventory().addItemDrop(990, 10);
				player.diarypoints += 5;
				player.Drypoints += pointvary;
				player.masterblinkclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 23:
			if (player.masterseasonclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.masterseasonclaimed != true && player.seasonalrewardsobtained >= 50) {
				player.getInventory().addItemDrop(SeasonEvent.TOKENS, 1200);
				player.diarypoints += 5;
				player.Drypoints += pointvary;
				player.masterseasonclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 24:
			if (player.masterthclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.masterthclaimed != true && player.treasurechestshunted >= 15) {
				player.getInventory().addItemDrop(28920, 1);
				player.diarypoints += 5;
				player.Drypoints += pointvary;
				player.masterthclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		default:
			player.sendMessage("Seems this didn't work? Please report this to staff.");
			break;

		}
//		if (player.claimNewMasterDiaryReward != true && CompletedMasterDiary(player)) {
//			World.sendIconWorldMessage(
//					Colors.gray + "" + player.getDisplayName() + " has just completed the Master achievement diary!",
//					false);
//			player.claimNewMasterDiaryReward = true;
//			return;
//		}
	}

	public static void HandleMasterButtons(Player player, int componentId) {
		int pointvary = player.isHeroic() ? 1000 : player.isHard() ? 850 : player.isAverage() ? 700 : 550;
		switch (componentId) {
		case 34:
			OpenMasterPage(player);
			break;
		case 42:
			HandleMasterRewards(player, componentId);
			break;
		/**
		 * Master Diary
		 */
		case 38:
			player.achievementdiaryslot = 2;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Open 500 Clue Scrolls. <br><br>Progress " + player.cluescompleted + "/500");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>5 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(28921, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 46:
			player.achievementdiaryslot = 3;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Kill 15,000 NPCs. <br><br>Progress " + player.TotalKills() + "/15000");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>5 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(5022, 100));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 47:
			player.achievementdiaryslot = 4;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Kill 5,000 Bosses. <br><br>Progress " + player.BossKills() + "/5000");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>5 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(29540, 3));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 48:
			player.achievementdiaryslot = 5;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Complete 250 OSRS raids. <br><br>Progress " + player.TotalOSRSRaids() + "/250");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>5 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(25353, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 51:
			player.achievementdiaryslot = 6;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Open 250 Raging Caskets. <br><br>Progress " + player.ragingcasketcount + "/250");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>5 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(29363, 3));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 52:
			player.achievementdiaryslot = 7;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Deposit 100 Heist Bags. <br><br>Progress " + player.heistcashbagsdeposit + "/100");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>5 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(5022, 30));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 29:
			player.achievementdiaryslot = 8;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Kill 5,000 Slayer Targets. <br><br>Progress " + player.slayertargetkills + "/5000");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>5 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(29374, 2));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 54:
			player.achievementdiaryslot = 9;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Obtain 50,000 Runecoins. <br><br>Progress " + player.runecoinsobtained + "/50000");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>5 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(29256, 500));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 65:
			player.achievementdiaryslot = 10;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Complete 100 Wilderness Tasks. <br><br>Progress " + player.wildernesstaskcompleted + "/100");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>5 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(20806, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 66:
			player.achievementdiaryslot = 11;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Use 3 Dyes on Level 90 Armour. <br><br>Progress " + player.dyesusedt90 + "/3");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>5 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(29426, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 67:
			player.achievementdiaryslot = 12;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Complete 500 High Level Dungeoneering Floors. <br><br>Progress " + player.dunggkills + "/500");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>5 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(17143, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 68:
			player.achievementdiaryslot = 13;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Achieve Veteran Status (12.5 days playtime). <br><br>Progress " + player.isVeteran() + "");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>5 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(25202, 10));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 69:
			player.achievementdiaryslot = 14;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Claim 250 Votes. <br><br>Progress " + player.getvotepoints() + "/250");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>5 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(25202, 10));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 70:
			player.achievementdiaryslot = 15;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Obtain 9 Raptor Seals. <br><br>Progress " + player.raptorseals + "/9");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>5 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(29540, 2));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 71:
			player.achievementdiaryslot = 16;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Kill Celestia 100 Times. <br><br>Progress " + player.CelestiaKills + "/100");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>5 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(19889, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 72:
			player.achievementdiaryslot = 17;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Kill the Raptor 100 Times. <br><br>Progress " + player.RaptorKills + "/100");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>5 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(28974, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 73:
			player.achievementdiaryslot = 18;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Kill the Huge Troll 100 Times. <br><br>Progress " + player.BigtrollKills + "/100");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>5 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(29654, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 74:
			player.achievementdiaryslot = 19;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40, "Donate 2B to the Well of Fortune. <br><br>Progress "
					+ Utils.formatGP(player.getTotalDonatedToWell()) + "/2B");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>5 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(29353, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 75:
			player.achievementdiaryslot = 20;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Reach Wave 58 in the Calamity. <br><br>Progress " + player.calamitybestwave + "/58");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>5 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(28922, 2));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 76:
			player.achievementdiaryslot = 21;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Use a Troll Potion on Harmony. <br><br>Progress " + player.usedtrollpotionhydrix + "");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>5 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(3265, 2));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 77:
			player.achievementdiaryslot = 22;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Kill Blink 10 Times. <br><br>Progress " + player.BlinkKills + "/10");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>5 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(990, 10));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 78:
			player.achievementdiaryslot = 23;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Exchange Seasonal Tokens for 50 Rewards. <br><br>Progress " + player.seasonalrewardsobtained
							+ "/50");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>5 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(SeasonEvent.TOKENS, 1200));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 79:
			player.achievementdiaryslot = 24;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Loot 15 Treasure Chest's During the Treasure Hunt. <br><br>Progress " + player.treasurechestshunted
							+ "/15");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>5 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(28920, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		}

	}

	public static void OpenElitePage(Player player) {
		int pointvary = player.isHeroic() ? 900 : player.isHard() ? 750 : player.isAverage() ? 600 : 450;
		player.achievementdiarypage = 4;
		player.achievementdiaryslot = 1;
		player.getInterfaceManager().sendInterface(INTER);
		player.getPackets().sendIComponentText(INTER, 16, "<col=8A2BE2>Elite Achievement Diary");
		/**
		 * Diary
		 */
		player.getPackets().sendIComponentText(INTER, 34,
				"" + (player.elitecchestclaimed ? Colors.green : Colors.red) + "Crystal Head");
		player.getPackets().sendIComponentText(INTER, 38,
				"" + (player.elitecluesclaimed ? Colors.green : Colors.red) + "King Clue");
		player.getPackets().sendIComponentText(INTER, 46,
				"" + (player.elitemobsclaimed ? Colors.green : Colors.red) + "Mob Dominator");
		player.getPackets().sendIComponentText(INTER, 47,
				"" + (player.elitebossesclaimed ? Colors.green : Colors.red) + "Boss Destroyer");
		player.getPackets().sendIComponentText(INTER, 48,
				"" + (player.elitexericclaimed ? Colors.green : Colors.red) + "Xeric Master");
		player.getPackets().sendIComponentText(INTER, 51,
				"" + (player.elitetobclaimed ? Colors.green : Colors.red) + "TOB Master");
		player.getPackets().sendIComponentText(INTER, 52,
				"" + (player.eliterunedragclaimed ? Colors.green : Colors.red) + "Rune Dragon Killer");
		player.getPackets().sendIComponentText(INTER, 29,
				"" + (player.eliteragingclaimed ? Colors.green : Colors.red) + "Infuriated");
		player.getPackets().sendIComponentText(INTER, 54,
				"" + (player.elitedtdclaimed ? Colors.green : Colors.red) + "Insta Kill!");
		player.getPackets().sendIComponentText(INTER, 65,
				"" + (player.elitekilnclaimed ? Colors.green : Colors.red) + "Kiln Boy");
		player.getPackets().sendIComponentText(INTER, 66,
				"" + (player.elitepwclaimed ? Colors.green : Colors.red) + "War");
		player.getPackets().sendIComponentText(INTER, 67,
				"" + (player.elitercclaimed ? Colors.green : Colors.red) + "Recycle Apprentice");
		player.getPackets().sendIComponentText(INTER, 68,
				"" + (player.eliteprestigeclaimed ? Colors.green : Colors.red) + "So Close");
		player.getPackets().sendIComponentText(INTER, 69,
				"" + (player.elitevoteclaimed ? Colors.green : Colors.red) + "Top Voter");
		player.getPackets().sendIComponentText(INTER, 70,
				"" + (player.elitethclaimed ? Colors.green : Colors.red) + "Goooooooold");
		player.getPackets().sendIComponentText(INTER, 71,
				"" + (player.eliteturmclaimed ? Colors.green : Colors.red) + "Turmoil");
		player.getPackets().sendIComponentText(INTER, 72,
				"" + (player.eliteptclaimed ? Colors.green : Colors.red) + "Never Leave");
		player.getPackets().sendIComponentText(INTER, 73,
				"" + (player.eliteslayertargetsclaimed ? Colors.green : Colors.red) + "King Slayer");
		player.getPackets().sendIComponentText(INTER, 74,
				"" + (player.elitecorruptedclaimed ? Colors.green : Colors.red) + "Max Power");
		player.getPackets().sendIComponentText(INTER, 75,
				"" + (player.elitepetclaimed ? Colors.green : Colors.red) + "Lil Guy");
		player.getPackets().sendIComponentText(INTER, 76,
				"" + (player.elitefoodhealclaimed ? Colors.green : Colors.red) + "Extra Heals");
		player.getPackets().sendIComponentText(INTER, 77,
				"" + (player.eliteconnorclaimed ? Colors.green : Colors.red) + "My Hat!");
		player.getPackets().sendIComponentText(INTER, 78,
				"" + (player.elitethunderousclaimed ? Colors.green : Colors.red) + "Thunderous");
		player.getPackets().sendIComponentText(INTER, 79,
				"" + (player.elitebunnykillerclaimed ? Colors.green : Colors.red) + "Fluffy");
		player.getPackets().sendIComponentText(INTER, 80,
				"" + (player.eliteassassinclaimed ? Colors.green : Colors.red) + "Rekt");
		player.getPackets().sendHideIComponent(INTER, 81, true);
		player.getPackets().sendHideIComponent(INTER, 82, true);
		player.getPackets().sendHideIComponent(INTER, 83, true);
		player.getPackets().sendHideIComponent(INTER, 84, true);
		player.getPackets().sendHideIComponent(INTER, 85, true);
		player.getPackets().sendHideIComponent(INTER, 86, true);
		player.getPackets().sendHideIComponent(INTER, 87, true);
		player.getPackets().sendHideIComponent(INTER, 88, true);
		player.getPackets().sendHideIComponent(INTER, 89, true);
		player.getPackets().sendHideIComponent(INTER, 90, true);

		/**
		 * Description + progress
		 */
		player.getPackets().sendIComponentText(INTER, 40,
				"Open 250 Regular Crystal chests. <br><br>Progress " + player.ckeyused + "/250");
		/**
		 * Rewards
		 */
		player.getPackets().sendIComponentText(INTER, 95,
				"<col=ff0000>Rewards will vary based on your<br><col=ff0000> experience mode.");

		player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>4 Diary Point");
		player.getSpareContainer().getContainer().clear();
		player.getSpareContainer().getContainer().add(new Item(29425, 4));
		player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
		player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
		player.getSpareContainer().getContainer().shift();
		player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
		return;

	}

	public static void HandleEliteRewards(Player player, int componentId) {
		int pointvary = player.isHeroic() ? 900 : player.isHard() ? 750 : player.isAverage() ? 600 : 450;
		switch (player.achievementdiaryslot) {
		case 1:
			if (player.elitecchestclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.elitecchestclaimed != true && player.ckeyused >= 250) {
				player.getInventory().addItemDrop(29425, 4);
				player.diarypoints += 4;
				player.Drypoints += pointvary;
				player.elitecchestclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 2:
			if (player.elitecluesclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.elitecluesclaimed != true && player.cluescompleted >= 150) {
				player.getInventory().addItemDrop(2677, 4);
				player.diarypoints += 4;
				player.Drypoints += pointvary;
				player.elitecluesclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 3:
			if (player.elitemobsclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.elitemobsclaimed != true && player.TotalKills() >= 5000) {
				player.getInventory().addItemDrop(29540, 1);
				player.diarypoints += 4;
				player.Drypoints += pointvary;
				player.elitemobsclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 4:
			if (player.elitebossesclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.elitebossesclaimed != true && player.BossKills() >= 1250) {
				player.getInventory().addItemDrop(29540, 1);
				player.diarypoints += 4;
				player.Drypoints += pointvary;
				player.elitebossesclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 5:
			if (player.elitexericclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.elitexericclaimed != true && player.osrsraidscompleted >= 50) {
				player.getInventory().addItemDrop(29438, 2);
				player.diarypoints += 4;
				player.Drypoints += pointvary;
				player.elitexericclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 6:
			if (player.elitetobclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.elitetobclaimed != true && player.theatreofbloodcompleted >= 50) {
				player.getInventory().addItemDrop(29328, 2);
				player.diarypoints += 4;
				player.Drypoints += pointvary;
				player.elitetobclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 7:
			if (player.eliterunedragclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.eliterunedragclaimed != true && player.runedragkills >= 150) {
				player.getInventory().addItemDrop(29651, 15);
				player.diarypoints += 4;
				player.Drypoints += pointvary;
				player.eliterunedragclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 8:
			if (player.eliteragingclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.eliteragingclaimed != true && player.ragingcasketcount >= 50) {
				player.getInventory().addItemDrop(29363, 2);
				player.diarypoints += 4;
				player.Drypoints += pointvary;
				player.eliteragingclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 9:
			if (player.elitedtdclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.elitedtdclaimed != true && player.deathtouchdartsused >= 10) {
				player.getInventory().addItemDrop(5022, 5);
				player.diarypoints += 4;
				player.Drypoints += pointvary;
				player.elitedtdclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 10:
			if (player.elitekilnclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.elitekilnclaimed != true && player.kilnruns >= 2) {
				player.getInventory().addItemDrop(29854, 1);
				player.diarypoints += 4;
				player.Drypoints += pointvary;
				player.elitekilnclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 11:
			if (player.elitepwclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.elitepwclaimed != true && player.pwamountcompleted >= 25) {
				player.getInventory().addItemDrop(29716, 1);
				player.diarypoints += 4;
				player.Drypoints += pointvary;
				player.elitepwclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 12:
			if (player.elitercclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.elitercclaimed != true && player.runecoinsobtained >= 25000) {
				player.getInventory().addItemDrop(29256, 250);
				player.diarypoints += 4;
				player.Drypoints += pointvary;
				player.elitercclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 13:
			if (player.eliteprestigeclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.eliteprestigeclaimed != true && player.getSkills().getTotalLevel() >= 2000) {
				player.getInventory().addItemDrop(23715, 1);
				player.diarypoints += 4;
				player.Drypoints += pointvary;
				player.eliteprestigeclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 14:
			if (player.elitevoteclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.elitevoteclaimed != true && player.getvotepoints() >= 84) {
				player.getInventory().addItemDrop(29532, 3);
				player.diarypoints += 4;
				player.Drypoints += pointvary;
				player.elitevoteclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 15:
			if (player.elitethclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.elitethclaimed != true && player.treasurechestshunted >= 1) {
				player.getInventory().addItemDrop(5022, 1);
				player.diarypoints += 4;
				player.Drypoints += pointvary;
				player.elitethclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 16:
			if (player.eliteturmclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.eliteturmclaimed != true && player.unlockedturmoilupgradeonce == true) {
				player.getInventory().addItemDrop(23750, 1);
				player.diarypoints += 4;
				player.Drypoints += pointvary;
				player.eliteturmclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 17:
			if (player.eliteptclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.eliteptclaimed != true && player.playdays >= 5) {
				player.getInventory().addItemDrop(28965, 2);
				player.diarypoints += 4;
				player.Drypoints += pointvary;
				player.eliteptclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 18:
			if (player.eliteslayertargetsclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.eliteslayertargetsclaimed != true && player.slayertargetkills >= 2500) {
				player.getInventory().addItemDrop(29374, 2);
				player.diarypoints += 4;
				player.Drypoints += pointvary;
				player.eliteslayertargetsclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 19:
			if (player.elitecorruptedclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.elitecorruptedclaimed != true && player.unlockedturmoilupgradeonce) {
				player.getInventory().addItemDrop(5022, 5);
				player.diarypoints += 4;
				player.Drypoints += pointvary;
				player.elitecorruptedclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 20:
			if (player.elitepetclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.elitepetclaimed != true && player.skillingpetspawned) {
				player.getInventory().addItemDrop(5022, 1);
				player.diarypoints += 4;
				player.Drypoints += pointvary;
				player.elitepetclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 21:
			if (player.elitefoodhealclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.elitefoodhealclaimed != true && player.eventhealperk) {
				player.getInventory().addItemDrop(7061, 250);
				player.diarypoints += 4;
				player.Drypoints += pointvary;
				player.elitefoodhealclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 22:
			if (player.eliteconnorclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.eliteconnorclaimed != true && player.connorcapfound) {
				player.getInventory().addItemDrop(23713, 1);
				player.diarypoints += 4;
				player.Drypoints += pointvary;
				player.eliteconnorclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 23:
			if (player.elitethunderousclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.elitethunderousclaimed != true && player.thunderous) {
				player.diarypoints += 4;
				player.Drypoints += pointvary;
				player.elitethunderousclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 24:
			if (player.elitebunnykillerclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.elitebunnykillerclaimed != true && player.angrybunnytitle) {
				player.diarypoints += 4;
				player.Drypoints += pointvary;
				player.elitebunnykillerclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 25:
			if (player.eliteassassinclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.eliteassassinclaimed != true && player.AssassinKills >= 3) {
				player.getInventory().addItemDrop(5022, 5);
				player.diarypoints += 4;
				player.Drypoints += pointvary;
				player.eliteassassinclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		default:
			player.sendMessage("Seems this didn't work? Please report this to staff.");
			break;

		}
//		if (player.claimNewEliteDiaryReward != true && CompletedEliteDiary(player)) {
//			World.sendIconWorldMessage(
//					Colors.gray + "" + player.getDisplayName() + " has just completed the Elite achievement diary!",
//					false);
//			player.claimNewEliteDiaryReward = true;
//			return;
//		}
	}

	public static void HandleEliteButtons(Player player, int componentId) {
		int pointvary = player.isHeroic() ? 900 : player.isHard() ? 750 : player.isAverage() ? 600 : 450;
		switch (componentId) {
		case 34:
			OpenElitePage(player);
			break;
		case 42:
			HandleEliteRewards(player, componentId);
			break;
		/**
		 * Easy Diary
		 */
		case 38:
			player.achievementdiaryslot = 2;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Open 150 Clue Scrolls. <br><br>Progress " + player.cluescompleted + "/150");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>4 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(2677, 4));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 46:
			player.achievementdiaryslot = 3;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Kill 5,000 NPCs. <br><br>Progress " + player.TotalKills() + "/5000");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>4 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(29540, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 47:
			player.achievementdiaryslot = 4;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Kill 1,250 Bosses. <br><br>Progress " + player.BossKills() + "/1250");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>4 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(29540, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 48:
			player.achievementdiaryslot = 5;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Complete 50 Chambers of Xeric raids. <br><br>Progress " + player.osrsraidscompleted + "/50");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>4 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(29438, 2));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 51:
			player.achievementdiaryslot = 6;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Complete 50 Theatre of Blood raids. <br><br>Progress " + player.theatreofbloodcompleted + "/50");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>4 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(29328, 2));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 52:
			player.achievementdiaryslot = 7;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Kill 150 Rune Dragons. <br><br>Progress " + player.runedragkills + "/150");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>4 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(29651, 15));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 29:
			player.achievementdiaryslot = 8;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Open 75 Raging Caskets. <br><br>Progress " + player.ragingcasketcount + "/75");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>4 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(29363, 2));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 54:
			player.achievementdiaryslot = 9;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Use 10 Deathtouched Darts. <br><br>Progress " + player.deathtouchdartsused + "/10");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>4 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(5022, 5));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 65:
			player.achievementdiaryslot = 10;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Complete 2 Fight Kilns. <br><br>Progress " + player.kilnruns + "/2");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>4 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(29854, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 66:
			player.achievementdiaryslot = 11;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Complete Player Wars 25 Times. <br><br>Progress " + player.pwamountcompleted + "/25");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>4 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(29716, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 67:
			player.achievementdiaryslot = 12;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Obtain 25,000 Runecoins. <br><br>Progress " + player.runecoinsobtained + "/25000");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>4 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(29256, 250));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 68:
			player.achievementdiaryslot = 13;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Achieve 2,000 Total level. <br><br>Progress " + player.getSkills().getTotalLevel() + "/2000");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>4 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(23715, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 69:
			player.achievementdiaryslot = 14;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Claim 84 Votes. <br><br>Progress " + player.getvotepoints() + "/84");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>4 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(29532, 3));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 70:
			player.achievementdiaryslot = 15;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Loot a Treasure Chest During the Treasure Hunt. <br><br>Progress " + player.treasurechestshunted
							+ "/1");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>4 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(5022, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 71:
			player.achievementdiaryslot = 16;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Unlock Ranged & Magic Turmoil. <br><br>Progress " + player.turmoilunlocked + "");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>4 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(23750, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 72:
			player.achievementdiaryslot = 17;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Achieve 5 Days Play Time. <br><br>Progress " + player.playdays + "/5");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>4 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(28965, 2));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 73:
			player.achievementdiaryslot = 18;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Kill 2,500 Slayer Targets. <br><br>Progress " + player.slayertargetkills + "/2500");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>4 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(29374, 2));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 74:
			player.achievementdiaryslot = 19;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Unlock atleast one Turmoil Upgrade. <br><br>Progress " + player.unlockedturmoilupgradeonce + "");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>4 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(5022, 5));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 75:
			player.achievementdiaryslot = 20;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Obtain and Spawn a Skilling Pet. <br><br>Progress " + player.skillingpetspawned + "");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>4 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(5022, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 76:
			player.achievementdiaryslot = 21;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Unlock 110% Food Heal Perk. <br><br>Progress " + player.eventhealperk + "");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>4 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(7061, 250));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 77:
			player.achievementdiaryslot = 22;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Find Connor's Cap in the Calamity. <br><br>Progress " + player.connorcapfound + "");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>4 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(23713, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 78:
			player.achievementdiaryslot = 23;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Unlock the Thunderous Title. <br><br>Progress " + player.thunderous + "");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>4 Diary Point");
			player.getSpareContainer().getContainer().clear();
			// player.getSpareContainer().getContainer().add(new Item(14479, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 79:
			player.achievementdiaryslot = 24;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Unlock the Bunny Killer Title. <br><br>Progress " + player.angrybunnytitle + "");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>4 Diary Point");
			player.getSpareContainer().getContainer().clear();
			// player.getSpareContainer().getContainer().add(new Item(13734, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 80:
			player.achievementdiaryslot = 25;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Kill the Assassin 3 Times. <br><br>Progress " + player.AssassinKills + "/3");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>4 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(5022, 5));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		}

	}

	public static void OpenHardPage(Player player) {
		int pointvary = player.isHeroic() ? 600 : player.isHard() ? 500 : player.isAverage() ? 400 : 300;
		player.achievementdiarypage = 3;
		player.achievementdiaryslot = 1;
		player.getInterfaceManager().sendInterface(INTER);
		player.getPackets().sendIComponentText(INTER, 16, "<col=8A2BE2>Hard Achievement Diary");
		/**
		 * Diary
		 */
		player.getPackets().sendIComponentText(INTER, 34,
				"" + (player.hardcchestclaimed ? Colors.green : Colors.red) + "Crystal Box");
		player.getPackets().sendIComponentText(INTER, 38,
				"" + (player.hardcluesclaimed ? Colors.green : Colors.red) + "Clue Master");
		player.getPackets().sendIComponentText(INTER, 46,
				"" + (player.hardmobsclaimed ? Colors.green : Colors.red) + "Mob Master");
		player.getPackets().sendIComponentText(INTER, 47,
				"" + (player.hardbossesclaimed ? Colors.green : Colors.red) + "Boss Slaughter");
		player.getPackets().sendIComponentText(INTER, 48,
				"" + (player.hardxericclaimed ? Colors.green : Colors.red) + "Xeric Noob");
		player.getPackets().sendIComponentText(INTER, 51,
				"" + (player.hardtobclaimed ? Colors.green : Colors.red) + "TOB Noob");
		player.getPackets().sendIComponentText(INTER, 52,
				"" + (player.hardtrioclaimed ? Colors.green : Colors.red) + "Trio Noob");
		player.getPackets().sendIComponentText(INTER, 29,
				"" + (player.hardenhancementclaimed ? Colors.green : Colors.red) + "Enhanced");
		player.getPackets().sendIComponentText(INTER, 54,
				"" + (player.hardfightcavesclaimed ? Colors.green : Colors.red) + "Fight!");
		player.getPackets().sendIComponentText(INTER, 65,
				"" + (player.hardrunecoinclaimed ? Colors.green : Colors.red) + "Recycle");
		player.getPackets().sendIComponentText(INTER, 66,
				"" + (player.hardvoteclaimed ? Colors.green : Colors.red) + "Votes 4 Dayz");
		player.getPackets().sendIComponentText(INTER, 67,
				"" + (player.hardraredropclaimed ? Colors.green : Colors.red) + "Juicy Loot");
		player.getPackets().sendIComponentText(INTER, 68,
				"" + (player.hardchaoticclaimed ? Colors.green : Colors.red) + "Big Guns");
		player.getPackets().sendIComponentText(INTER, 69,
				"" + (player.hardptclaimed ? Colors.green : Colors.red) + "No Life");
		player.getPackets().sendIComponentText(INTER, 70,
				"" + (player.hardslayertargetsclaimed ? Colors.green : Colors.red) + "Slayer Master");
		player.getPackets().sendIComponentText(INTER, 71,
				"" + (player.hardcalamityclaimed ? Colors.green : Colors.red) + "Give Us a Wave");
		player.getPackets().sendIComponentText(INTER, 72,
				"" + (player.hardheistclaimed ? Colors.green : Colors.red) + "Master Mind");
		player.getPackets().sendIComponentText(INTER, 73,
				"" + (player.hardelitevoidclaimed ? Colors.green : Colors.red) + "the Elite");
		player.getPackets().sendIComponentText(INTER, 74,
				"" + (player.harddropboostclaimed ? Colors.green : Colors.red) + "Boost Activated");
		player.getPackets().sendIComponentText(INTER, 75,
				"" + (player.hardbarrowsclaimed ? Colors.green : Colors.red) + "BroSki");
		player.getPackets().sendIComponentText(INTER, 76,
				"" + (player.harddailyclaimed ? Colors.green : Colors.red) + "Daily");
		player.getPackets().sendIComponentText(INTER, 77,
				"" + (player.hardtogclaimed ? Colors.green : Colors.red) + "Trial");
		player.getPackets().sendIComponentText(INTER, 78,
				"" + (player.hardrunebodyclaimed ? Colors.green : Colors.red) + "Blacksmith");
		player.getPackets().sendIComponentText(INTER, 79,
				"" + (player.hardcorpclimed ? Colors.green : Colors.red) + "Beast");
		player.getPackets().sendIComponentText(INTER, 80,
				"" + (player.hardxpwellclaimed ? Colors.green : Colors.red) + "Makin Gainz");
		player.getPackets().sendHideIComponent(INTER, 81, true);
		player.getPackets().sendHideIComponent(INTER, 82, true);
		player.getPackets().sendHideIComponent(INTER, 83, true);
		player.getPackets().sendHideIComponent(INTER, 84, true);
		player.getPackets().sendHideIComponent(INTER, 85, true);
		player.getPackets().sendHideIComponent(INTER, 86, true);
		player.getPackets().sendHideIComponent(INTER, 87, true);
		player.getPackets().sendHideIComponent(INTER, 88, true);
		player.getPackets().sendHideIComponent(INTER, 89, true);
		player.getPackets().sendHideIComponent(INTER, 90, true);

		/**
		 * Description + progress
		 */
		player.getPackets().sendIComponentText(INTER, 40,
				"Open 100 Regular Crystal chests. <br><br>Progress " + player.ckeyused + "/100");
		/**
		 * Rewards
		 */
		player.getPackets().sendIComponentText(INTER, 95,
				"<col=ff0000>Rewards will vary based on your<br><col=ff0000> experience mode.");

		player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>3 Diary Point");
		player.getSpareContainer().getContainer().clear();
		player.getSpareContainer().getContainer().add(new Item(29425, 3));
		player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
		player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
		player.getSpareContainer().getContainer().shift();
		player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
		return;

	}

	public static void HandleHardRewards(Player player, int componentId) {
		int pointvary = player.isHeroic() ? 600 : player.isHard() ? 500 : player.isAverage() ? 400 : 300;
		switch (player.achievementdiaryslot) {
		case 1:
			if (player.hardcchestclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.hardcchestclaimed != true && player.ckeyused >= 100) {
				player.getInventory().addItemDrop(29425, 3);
				player.diarypoints += 3;
				player.Drypoints += pointvary;
				player.hardcchestclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 2:
			if (player.hardcluesclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.hardcluesclaimed != true && player.cluescompleted >= 50) {
				player.getInventory().addItemDrop(2677, 3);
				player.diarypoints += 3;
				player.Drypoints += pointvary;
				player.hardcluesclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 3:
			if (player.hardmobsclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.hardmobsclaimed != true && player.TotalKills() >= 1000) {
				player.getInventory().addItemDrop(29541, 1);
				player.diarypoints += 3;
				player.Drypoints += pointvary;
				player.hardmobsclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 4:
			if (player.hardbossesclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.hardbossesclaimed != true && player.BossKills() >= 750) {
				player.getInventory().addItemDrop(29540, 1);
				player.diarypoints += 3;
				player.Drypoints += pointvary;
				player.hardbossesclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 5:
			if (player.hardxericclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.hardxericclaimed != true && player.osrsraidscompleted >= 10) {
				player.getInventory().addItemDrop(29438, 1);
				player.diarypoints += 3;
				player.Drypoints += pointvary;
				player.hardxericclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 6:
			if (player.hardtobclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.hardtobclaimed != true && player.theatreofbloodcompleted >= 10) {
				player.getInventory().addItemDrop(29328, 1);
				player.diarypoints += 3;
				player.Drypoints += pointvary;
				player.hardtobclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 7:
			if (player.hardtrioclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.hardtrioclaimed != true && player.TrioRaidKills >= 10) {
				player.getInventory().addItemDrop(29426, 1);
				player.diarypoints += 3;
				player.Drypoints += pointvary;
				player.hardtrioclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 8:
			if (player.hardenhancementclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.hardenhancementclaimed != true && player.scrollofenhancementsused >= 5) {
				player.getInventory().addItemDrop(6199, 1);
				player.diarypoints += 3;
				player.Drypoints += pointvary;
				player.hardenhancementclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 9:
			if (player.hardfightcavesclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.hardfightcavesclaimed != true && player.completedFightCaves) {
				player.getInventory().addItemDrop(6571, 1);
				player.diarypoints += 3;
				player.Drypoints += pointvary;
				player.hardfightcavesclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 10:
			if (player.hardrunecoinclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.hardrunecoinclaimed != true && player.runecoinsobtained >= 10000) {
				player.getInventory().addItemDrop(29256, 100);
				player.diarypoints += 3;
				player.Drypoints += pointvary;
				player.hardrunecoinclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 11:
			if (player.hardvoteclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.hardvoteclaimed != true && player.getvotepoints() >= 42) {
				player.getInventory().addItemDrop(29532, 2);
				player.diarypoints += 3;
				player.Drypoints += pointvary;
				player.hardvoteclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 12:
			if (player.hardraredropclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.hardraredropclaimed != true && player.raredropobtained >= 1) {
				player.getInventory().addItemDrop(5022, 1);
				player.diarypoints += 3;
				player.Drypoints += pointvary;
				player.hardraredropclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 13:
			if (player.hardchaoticclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.hardchaoticclaimed != true && player.chaoticupgraded) {
				player.getInventory().addItemDrop(23749, 1);
				player.diarypoints += 3;
				player.Drypoints += pointvary;
				player.hardchaoticclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 14:
			if (player.hardptclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.hardptclaimed != true && player.playdays >= 3) {
				player.getInventory().addItemDrop(23715, 1);
				player.diarypoints += 3;
				player.Drypoints += pointvary;
				player.hardptclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 15:
			if (player.hardslayertargetsclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.hardslayertargetsclaimed != true && player.slayertargetkills >= 1000) {
				player.getInventory().addItemDrop(29374, 1);
				player.diarypoints += 3;
				player.Drypoints += pointvary;
				player.hardslayertargetsclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 16:
			if (player.hardcalamityclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.hardcalamityclaimed != true && player.calamitybestwave >= 40) {
				player.getInventory().addItemDrop(28922, 1);
				player.diarypoints += 3;
				player.Drypoints += pointvary;
				player.hardcalamityclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 17:
			if (player.hardheistclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.hardheistclaimed != true && player.heistcashbagsdeposit >= 50) {
				player.getInventory().addItemDrop(5022, 5);
				player.diarypoints += 3;
				player.Drypoints += pointvary;
				player.hardheistclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 18:
			if (player.hardelitevoidclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.hardelitevoidclaimed != true && player.elitevoidmade) {
				player.getInventory().addItemDrop(23713, 1);
				player.diarypoints += 3;
				player.Drypoints += pointvary;
				player.hardelitevoidclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 19:
			if (player.harddropboostclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.harddropboostclaimed != true && player.dropboostimade) {
				player.getInventory().addItemDrop(5022, 10);
				player.diarypoints += 3;
				player.Drypoints += pointvary;
				player.harddropboostclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 20:
			if (player.hardbarrowsclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.hardbarrowsclaimed != true && player.barrowchestloot >= 50) {
				player.getInventory().addItemDrop(29481, 1);
				player.diarypoints += 3;
				player.Drypoints += pointvary;
				player.hardbarrowsclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 21:
			if (player.harddailyclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.harddailyclaimed != true && player.totalchallengescompleted >= 5) {
				player.getInventory().addItemDrop(23714, 1);
				player.diarypoints += 3;
				player.Drypoints += pointvary;
				player.harddailyclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 22:
			if (player.hardtogclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.hardtogclaimed != true && player.togdone) {
				player.getInventory().addItemDrop(29936, 10000);
				player.diarypoints += 3;
				player.Drypoints += pointvary;
				player.hardtogclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 23:
			if (player.hardrunebodyclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.hardrunebodyclaimed != true && player.runebodysmithed >= 100) {
				player.getInventory().addItemDrop(14479, 1);
				player.diarypoints += 3;
				player.Drypoints += pointvary;
				player.hardrunebodyclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 24:
			if (player.hardcorpclimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.hardcorpclimed != true && player.CorpKills >= 50) {
				player.getInventory().addItemDrop(13734, 1);
				player.diarypoints += 3;
				player.Drypoints += pointvary;
				player.hardcorpclimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 25:
			if (player.hardxpwellclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.hardxpwellclaimed != true && player.getTotalDonatedToWell() >= 200000000) {
				player.getInventory().addItemDrop(23713, 1);
				player.diarypoints += 3;
				player.Drypoints += pointvary;
				player.hardxpwellclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		default:
			player.sendMessage("Seems this didn't work? Please report this to staff.");
			break;

		}
//		if (player.claimNewHardDiaryReward != true && CompletedHardDiary(player)) {
//			World.sendIconWorldMessage(
//					Colors.gray + "" + player.getDisplayName() + " has just completed the Hard achievement diary!",
//					false);
//			player.claimNewHardDiaryReward = true;
//			return;
//		}
	}

	public static void HandleHardButtons(Player player, int componentId) {
		// System.out.println(componentId);
		int pointvary = player.isHeroic() ? 600 : player.isHard() ? 500 : player.isAverage() ? 400 : 300;
		switch (componentId) {
		case 34:
			OpenHardPage(player);
			break;
		case 42:
			HandleHardRewards(player, componentId);
			break;
		/**
		 * Easy Diary
		 */
		case 38:
			player.achievementdiaryslot = 2;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Open 50 Clue Scrolls. <br><br>Progress " + player.cluescompleted + "/50");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>3 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(2677, 3));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 46:
			player.achievementdiaryslot = 3;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Kill 1,000 NPCs. <br><br>Progress " + player.TotalKills() + "/1000");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>3 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(29541, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 47:
			player.achievementdiaryslot = 4;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Kill 750 Bosses. <br><br>Progress " + player.BossKills() + "/750");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>3 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(29540, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 48:
			player.achievementdiaryslot = 5;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Complete 10 Chambers of Xeric raids. <br><br>Progress " + player.osrsraidscompleted + "/10");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>3 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(29438, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 51:
			player.achievementdiaryslot = 6;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Complete 10 Theatre of Blood raids. <br><br>Progress " + player.theatreofbloodcompleted + "/10");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>3 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(29328, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 52:
			player.achievementdiaryslot = 7;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Complete 10 Trio raids. <br><br>Progress " + player.TrioRaidKills + "/10");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>3 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(29426, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 29:
			player.achievementdiaryslot = 8;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Use 5 Enhancement Scrolls. <br><br>Progress " + player.scrollofenhancementsused + "/5");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>3 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(6199, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 54:
			player.achievementdiaryslot = 9;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Complete the Fight Caves. <br><br>Progress " + player.isCompletedFightCaves() + "");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>3 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(6571, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 65:
			player.achievementdiaryslot = 10;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Obtain 10k Runecoins. <br><br>Progress " + player.runecoinsobtained + "/10000");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>3 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(29256, 100));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 66:
			player.achievementdiaryslot = 11;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Claim 42 Votes. <br><br>Progress " + player.getvotepoints() + "/42");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>3 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(29532, 2));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 67:
			player.achievementdiaryslot = 12;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Obtain a Rare Drop. <br><br>Progress " + player.raredropobtained + "/1");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>3 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(5022, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 68:
			player.achievementdiaryslot = 13;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Upgrade a Chaotic to (i). <br><br>Progress " + player.chaoticupgraded + "");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>3 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(23749, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 69:
			player.achievementdiaryslot = 14;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Achieve 3 day playtime. <br><br>Progress " + player.playdays + "/3");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>3 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(23715, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 70:
			player.achievementdiaryslot = 15;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Kill 1,000 slayer targets. <br><br>Progress " + player.slayertargetkills + "/1000");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>3 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(29374, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 71:
			player.achievementdiaryslot = 16;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Reach Wave 40 in Calamity. <br><br>Progress " + player.calamitybestwave + "/40");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>3 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(28922, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 72:
			player.achievementdiaryslot = 17;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Deposit 50 Bags in Heist. <br><br>Progress " + player.heistcashbagsdeposit + "/50");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>3 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(5022, 5));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 73:
			player.achievementdiaryslot = 18;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Research Elite Void at the Research Table. <br><br>Progress " + player.elitevoidmade + "");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>3 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(23713, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 74:
			player.achievementdiaryslot = 19;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Research Drop Boost Emblem (i) at the Research Table. <br><br>Progress " + player.dropboostimade
							+ "");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>3 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(5022, 10));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 75:
			player.achievementdiaryslot = 20;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Loot 50 Barrows Chests. <br><br>Progress " + player.barrowchestloot + "/50");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>3 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(29481, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 76:
			player.achievementdiaryslot = 21;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Complete 5 Daily Challenges. <br><br>Progress " + player.totalchallengescompleted + "/5");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>3 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(23714, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 77:
			player.achievementdiaryslot = 22;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Complete a Trial of the Gods Minigame. <br><br>Progress " + player.togdone + "");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>3 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(29936, 10000));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 78:
			player.achievementdiaryslot = 23;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Smith 100 Rune Platebody. <br><br>Progress " + player.runebodysmithed + "/100");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>3 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(14479, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 79:
			player.achievementdiaryslot = 24;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Kill 50 Corporeal Beast. <br><br>Progress " + player.CorpKills + "/50");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>3 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(13734, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 80:
			player.achievementdiaryslot = 25;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40, "Donate 200M to the Well of Fortune. <br><br>Progress "
					+ Utils.formatGP(player.getTotalDonatedToWell()) + "/200M");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>3 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(23713, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		}

	}

	public static void OpenMediumPage(Player player) {
		int pointvary = player.isHeroic() ? 400 : player.isHard() ? 350 : player.isAverage() ? 300 : 200;
		player.achievementdiarypage = 2;
		player.achievementdiaryslot = 1;
		player.getInterfaceManager().sendInterface(INTER);
		player.getPackets().sendIComponentText(INTER, 16, "<col=8A2BE2>Medium Achievement Diary");
		/**
		 * Diary
		 */
		player.getPackets().sendIComponentText(INTER, 34,
				"" + (player.mediumcchestclaimed ? Colors.green : Colors.red) + "Crystal Crate");
		player.getPackets().sendIComponentText(INTER, 38,
				"" + (player.mediumcluesclaimed ? Colors.green : Colors.red) + "Clued Up");
		player.getPackets().sendIComponentText(INTER, 46,
				"" + (player.mediummobsclaimed ? Colors.green : Colors.red) + "Mobster");
		player.getPackets().sendIComponentText(INTER, 47,
				"" + (player.mediumoverloadclaimed ? Colors.green : Colors.red) + "Overloaded");
		player.getPackets().sendIComponentText(INTER, 48,
				"" + (player.mediumpdclaimed ? Colors.green : Colors.red) + "Paaaaarty");
		player.getPackets().sendIComponentText(INTER, 51,
				"" + (player.mediumzulrahclaimed ? Colors.green : Colors.red) + "Im a snaaaaaaake");
		player.getPackets().sendIComponentText(INTER, 52,
				"" + (player.mediumsireclaimed ? Colors.green : Colors.red) + "Yes Sire");
		player.getPackets().sendIComponentText(INTER, 29,
				"" + (player.mediumkrakenclaimed ? Colors.green : Colors.red) + "Kraken Up");
		player.getPackets().sendIComponentText(INTER, 54,
				"" + (player.mediumlizardmanclaimed ? Colors.green : Colors.red) + "Lizard Boy");
		player.getPackets().sendIComponentText(INTER, 65,
				"" + (player.mediumbarrowsclaimed ? Colors.green : Colors.red) + "Old Skool");
		player.getPackets().sendIComponentText(INTER, 66,
				"" + (player.mediumbossesclaimed ? Colors.green : Colors.red) + "Boss Slayer");
		player.getPackets().sendIComponentText(INTER, 67,
				"" + (player.mediumvoteclaimed ? Colors.green : Colors.red) + "Democracy");
		player.getPackets().sendIComponentText(INTER, 68,
				"" + (player.mediumdungclaimed ? Colors.green : Colors.red) + "Dungeon Master");
		player.getPackets().sendIComponentText(INTER, 69,
				"" + (player.mediumplaytimeclaimed ? Colors.green : Colors.red) + "Addict");
		player.getPackets().sendIComponentText(INTER, 70,
				"" + (player.medium600claimed ? Colors.green : Colors.red) + "Noob");
		player.getPackets().sendIComponentText(INTER, 71,
				"" + (player.medium1000claimed ? Colors.green : Colors.red) + "Still Noob");
		player.getPackets().sendIComponentText(INTER, 72,
				"" + (player.medium1500claimed ? Colors.green : Colors.red) + "Not a Noob");
		player.getPackets().sendIComponentText(INTER, 73,
				"" + (player.mediumslayertargetsclaimed ? Colors.green : Colors.red) + "Slayer Graduate");
		player.getPackets().sendIComponentText(INTER, 74,
				"" + (player.mediumddefenderclaimed ? Colors.green : Colors.red) + "Defender");
		player.getPackets().sendIComponentText(INTER, 75,
				"" + (player.mediumcalamityclaimed ? Colors.green : Colors.red) + "Making Waves");
		player.getPackets().sendIComponentText(INTER, 76,
				"" + (player.mediumheistclaimed ? Colors.green : Colors.red) + "Robbery");
		player.getPackets().sendIComponentText(INTER, 77,
				"" + (player.mediumslayersurvivalclaimed ? Colors.green : Colors.red) + "Survived");
		player.getPackets().sendIComponentText(INTER, 78,
				"" + (player.mediumragingclaimed ? Colors.green : Colors.red) + "Raging");
		player.getPackets().sendIComponentText(INTER, 79,
				"" + (player.mediumimbueclaimed ? Colors.green : Colors.red) + "Imbue");
		player.getPackets().sendHideIComponent(INTER, 80, true);
		player.getPackets().sendHideIComponent(INTER, 81, true);
		player.getPackets().sendHideIComponent(INTER, 82, true);
		player.getPackets().sendHideIComponent(INTER, 83, true);
		player.getPackets().sendHideIComponent(INTER, 84, true);
		player.getPackets().sendHideIComponent(INTER, 85, true);
		player.getPackets().sendHideIComponent(INTER, 86, true);
		player.getPackets().sendHideIComponent(INTER, 87, true);
		player.getPackets().sendHideIComponent(INTER, 88, true);
		player.getPackets().sendHideIComponent(INTER, 89, true);
		player.getPackets().sendHideIComponent(INTER, 90, true);

		/**
		 * Description + progress
		 */
		player.getPackets().sendIComponentText(INTER, 40,
				"Open 25 Regular Crystal chests. <br><br>Progress " + player.ckeyused + "/25");
		/**
		 * Rewards
		 */
		player.getPackets().sendIComponentText(INTER, 95,
				"<col=ff0000>Rewards will vary based on your<br><col=ff0000> experience mode.");

		player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>1 Diary Point");
		player.getSpareContainer().getContainer().clear();
		player.getSpareContainer().getContainer().add(new Item(29425, 2));
		player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
		player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
		player.getSpareContainer().getContainer().shift();
		player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
		return;

	}

	public static void HandleMediumButtons(Player player, int componentId) {
		// System.out.println(componentId);
		int pointvary = player.isHeroic() ? 400 : player.isHard() ? 350 : player.isAverage() ? 300 : 200;
		switch (componentId) {
		case 34:
			OpenMediumPage(player);
			break;
		case 42:
			HandleMediumRewards(player, componentId);
			break;
		/**
		 * Easy Diary
		 */
		case 38:
			player.achievementdiaryslot = 2;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Open 15 Clue Scrolls. <br><br>Progress " + player.cluescompleted + "/15");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>2 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(2677, 2));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 46:
			player.achievementdiaryslot = 3;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Kill 250 NPCs. <br><br>Progress " + player.TotalKills() + "/250");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>2 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(29542, 2));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 47:
			player.achievementdiaryslot = 4;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Make 200 Overloads. <br><br>Progress " + player.overloadsmade + "/200");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>2 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(29069, 25));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 48:
			player.achievementdiaryslot = 5;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Loot 3 Party Demon chests. <br><br>Progress " + player.PdemonKills + "/3");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>2 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(29426, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 51:
			player.achievementdiaryslot = 6;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Kill 25 Zulrah. <br><br>Progress " + player.ZulrahKills + "/25");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>2 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(29655, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 52:
			player.achievementdiaryslot = 7;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Kill 25 Abyssal Sire. <br><br>Progress " + player.SireKills + "/25");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>2 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(5022, 4));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 29:
			player.achievementdiaryslot = 8;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Kill 25 Kraken. <br><br>Progress " + player.KrakenKills + "/25");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>2 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(21369, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 54:
			player.achievementdiaryslot = 9;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Kill 25 Lizardman. <br><br>Progress " + player.LizardmanKills + "/25");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>2 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(5022, 3));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 65:
			player.achievementdiaryslot = 10;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Loot 15 Barrows Chests. <br><br>Progress " + player.barrowchestloot + "/15");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>2 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(28963, 50));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 66:
			player.achievementdiaryslot = 11;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Kill 400 Bosses. <br><br>Progress " + player.BossKills() + "/400");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>2 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(6199, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 67:
			player.achievementdiaryslot = 12;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Claim 18 Votes. <br><br>Progress " + player.getvotepoints() + "/18");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>2 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(990, 3));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 68:
			player.achievementdiaryslot = 13;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40, "Obtain 120 Dungeoneering. <br><br>Progress "
					+ player.getSkills().getLevel(Skills.DUNGEONEERING) + "/120");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>2 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(24730, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 69:
			player.achievementdiaryslot = 14;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Achieve 1 day playtime. <br><br>Progress " + player.playdays + "/1");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>2 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(23715, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 70:
			player.achievementdiaryslot = 15;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Earn 600 Total Level. <br><br>Progress " + player.getSkills().getTotalLevel() + "/600");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>2 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(23713, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 71:
			player.achievementdiaryslot = 16;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Earn 1000 Total Level. <br><br>Progress " + player.getSkills().getTotalLevel() + "/1000");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>2 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(23713, 2));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 72:
			player.achievementdiaryslot = 17;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Earn 1500 Total Level. <br><br>Progress " + player.getSkills().getTotalLevel() + "/1500");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>2 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(23714, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 73:
			player.achievementdiaryslot = 18;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Kill 250 Slayer Targets. <br><br>Progress " + player.slayertargetkills + "/250");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>2 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(29374, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 74:
			player.achievementdiaryslot = 19;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Alch a Dragon Defender. <br><br>Progress " + player.alchedddefender + "");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>2 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(8851, 10000));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 75:
			player.achievementdiaryslot = 20;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Reach Wave 15 in Calamity. <br><br>Progress " + player.calamitybestwave + "/15");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>2 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(29771, 2));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 76:
			player.achievementdiaryslot = 21;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Deposit 10 Bags in Heist. <br><br>Progress " + player.heistcashbagsdeposit + "/10");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>2 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(5022, 5));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 77:
			player.achievementdiaryslot = 22;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Complete a Game of Slayer Survival. <br><br>Progress " + player.slayersurvivalcompleted + "/1");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>2 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(23778, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 78:
			player.achievementdiaryslot = 23;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Open 25 Raging Caskets. <br><br>Progress " + player.ragingcasketcount + "/25");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>2 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(29363, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 79:
			player.achievementdiaryslot = 24;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Use 5 Imbuing Kits. <br><br>Progress " + player.imbuekitused + "/5");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>2 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(15017, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		}
	}

	public static void HandleMediumRewards(Player player, int componentId) {
		int pointvary = player.isHeroic() ? 400 : player.isHard() ? 350 : player.isAverage() ? 300 : 200;
		switch (player.achievementdiaryslot) {
		case 1:
			if (player.mediumcchestclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.mediumcchestclaimed != true && player.ckeyused >= 25) {
				player.getInventory().addItemDrop(29425, 2);
				player.diarypoints += 2;
				player.Drypoints += pointvary;
				player.mediumcchestclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 2:
			if (player.mediumcluesclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.mediumcluesclaimed != true && player.cluescompleted >= 15) {
				player.getInventory().addItemDrop(2677, 2);
				player.diarypoints += 2;
				player.Drypoints += pointvary;
				player.mediumcluesclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 3:
			if (player.mediummobsclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.mediummobsclaimed != true && player.TotalKills() >= 250) {
				player.getInventory().addItemDrop(29542, 2);
				player.diarypoints += 2;
				player.Drypoints += pointvary;
				player.mediummobsclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 4:
			if (player.mediumoverloadclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.mediumoverloadclaimed != true && player.overloadsmade >= 200) {
				player.getInventory().addItemDrop(29069, 50);
				player.diarypoints += 2;
				player.Drypoints += pointvary;
				player.mediumoverloadclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 5:
			if (player.mediumpdclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.mediumpdclaimed != true && player.PdemonKills >= 3) {
				player.getInventory().addItemDrop(29426, 1);
				player.diarypoints += 2;
				player.Drypoints += pointvary;
				player.mediumpdclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 6:
			if (player.mediumzulrahclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.mediumzulrahclaimed != true && player.ZulrahKills >= 25) {
				player.getInventory().addItemDrop(29655, 1);
				player.diarypoints += 2;
				player.Drypoints += pointvary;
				player.mediumzulrahclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 7:
			if (player.mediumsireclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.mediumsireclaimed != true && player.SireKills >= 25) {
				player.getInventory().addItemDrop(5022, 4);
				player.diarypoints += 2;
				player.Drypoints += pointvary;
				player.mediumsireclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 8:
			if (player.mediumkrakenclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.mediumkrakenclaimed != true && player.KrakenKills >= 25) {
				player.getInventory().addItemDrop(21369, 1);
				player.diarypoints += 2;
				player.Drypoints += pointvary;
				player.mediumkrakenclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 9:
			if (player.mediumlizardmanclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.mediumlizardmanclaimed != true && player.LizardmanKills >= 25) {
				player.getInventory().addItemDrop(5022, 3);
				player.diarypoints += 2;
				player.Drypoints += pointvary;
				player.mediumlizardmanclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 10:
			if (player.mediumbarrowsclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.mediumbarrowsclaimed != true && player.barrowchestloot >= 15) {
				player.getInventory().addItemDrop(28963, 50);
				player.diarypoints += 2;
				player.Drypoints += pointvary;
				player.mediumbarrowsclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 11:
			if (player.mediumbossesclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.mediumbossesclaimed != true && player.BossKills() >= 400) {
				player.getInventory().addItemDrop(6199, 1);
				player.diarypoints += 2;
				player.Drypoints += pointvary;
				player.mediumbossesclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 12:
			if (player.mediumvoteclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.mediumvoteclaimed != true && player.getvotepoints() >= 18) {
				player.getInventory().addItemDrop(990, 3);
				player.diarypoints += 2;
				player.Drypoints += pointvary;
				player.mediumvoteclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 13:
			if (player.mediumdungclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.mediumdungclaimed != true && player.getSkills().getLevel(Skills.DUNGEONEERING) == 120) {
				player.getInventory().addItemDrop(24730, 1);
				player.diarypoints += 2;
				player.Drypoints += pointvary;
				player.mediumdungclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 14:
			if (player.mediumplaytimeclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.mediumplaytimeclaimed != true && player.playdays >= 1) {
				player.getInventory().addItemDrop(23715, 1);
				player.diarypoints += 2;
				player.Drypoints += pointvary;
				player.mediumplaytimeclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 15:
			if (player.medium600claimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.medium600claimed != true && player.getSkills().getTotalLevel() >= 600) {
				player.getInventory().addItemDrop(23713, 1);
				player.diarypoints += 2;
				player.Drypoints += pointvary;
				player.medium600claimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 16:
			if (player.medium1000claimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.medium1000claimed != true && player.getSkills().getTotalLevel() >= 1000) {
				player.getInventory().addItemDrop(23713, 2);
				player.diarypoints += 2;
				player.Drypoints += pointvary;
				player.medium1000claimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 17:
			if (player.medium1500claimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.medium1500claimed != true && player.getSkills().getTotalLevel() >= 1500) {
				player.getInventory().addItemDrop(23714, 1);
				player.diarypoints += 2;
				player.Drypoints += pointvary;
				player.medium1500claimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 18:
			if (player.mediumslayertargetsclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.mediumslayertargetsclaimed != true && player.slayertargetkills >= 250) {
				player.getInventory().addItemDrop(29374, 1);
				player.diarypoints += 2;
				player.Drypoints += pointvary;
				player.mediumslayertargetsclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 19:
			if (player.mediumddefenderclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.mediumddefenderclaimed != true && player.alchedddefender) {
				player.getInventory().addItemDrop(8851, 10000);
				player.diarypoints += 2;
				player.Drypoints += pointvary;
				player.mediumddefenderclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 20:
			if (player.mediumcalamityclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.mediumcalamityclaimed != true && player.calamitybestwave >= 15) {
				player.getInventory().addItemDrop(29771, 2);
				player.diarypoints += 2;
				player.Drypoints += pointvary;
				player.mediumcalamityclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 21:
			if (player.mediumheistclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.mediumheistclaimed != true && player.heistcashbagsdeposit >= 10) {
				player.getInventory().addItemDrop(5022, 5);
				player.diarypoints += 2;
				player.Drypoints += pointvary;
				player.mediumheistclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 22:
			if (player.mediumslayersurvivalclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.mediumslayersurvivalclaimed != true && player.slayersurvivalcompleted >= 1) {
				player.getInventory().addItemDrop(23778, 1);
				player.diarypoints += 2;
				player.Drypoints += pointvary;
				player.mediumslayersurvivalclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 23:
			if (player.mediumragingclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.mediumragingclaimed != true && player.ragingcasketcount >= 25) {
				player.getInventory().addItemDrop(29363, 1);
				player.diarypoints += 2;
				player.Drypoints += pointvary;
				player.mediumragingclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 24:
			if (player.mediumimbueclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.mediumimbueclaimed != true && player.imbuekitused >= 5) {
				player.getInventory().addItemDrop(15017, 1);
				player.diarypoints += 2;
				player.Drypoints += pointvary;
				player.mediumimbueclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		default:
			player.sendMessage("Seems this didn't work? Please report this to staff.");
			break;

		}
//		if (player.claimNewMediumDiaryReward != true && CompletedMediumDiary(player)) {
//			World.sendIconWorldMessage(
//					Colors.gray + "" + player.getDisplayName() + " has just completed the Medium achievement diary!",
//					false);
//			player.claimNewMediumDiaryReward = true;
//			return;
//		}
	}

	public static void HandleEasyRewards(Player player, int componentId) {
		int pointvary = player.isHeroic() ? 200 : player.isHard() ? 175 : player.isAverage() ? 150 : 100;
		switch (player.achievementdiaryslot) {
		case 1:
			if (player.easycchestclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.easycchestclaimed != true && player.ckeyused >= 10) {
				player.getInventory().addItemDrop(29425, 1);
				player.diarypoints++;
				player.Drypoints += pointvary;
				player.easycchestclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 2:
			if (player.easycluelessclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.easycluelessclaimed != true && player.cluescompleted >= 5) {
				player.getInventory().addItemDrop(5022, 2);
				player.diarypoints++;
				player.Drypoints += pointvary;
				player.easycluelessclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 3:
			if (player.easymobsclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.easymobsclaimed != true && player.TotalKills() >= 50) {
				player.getInventory().addItemDrop(5022, 2);
				player.diarypoints++;
				player.Drypoints += pointvary;
				player.easymobsclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 4:
			if (player.easyrockyclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.easyrockyclaimed != true && player.rockcrabKills >= 50) {
				player.getInventory().addItemDrop(3695, 1);
				player.diarypoints++;
				player.Drypoints += pointvary;
				player.easyrockyclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 5:
			if (player.easyborkclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.easyborkclaimed != true && player.BorkKills >= 25) {
				player.getInventory().addItemDrop(29541, 1);
				player.diarypoints++;
				player.Drypoints += pointvary;
				player.easyborkclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 6:
			if (player.easybosskillerclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.easybosskillerclaimed != true && player.BossKills() >= 40) {
				player.getInventory().addItemDrop(2677, 1);
				player.diarypoints++;
				player.Drypoints += pointvary;
				player.easybosskillerclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 7:
			if (player.easygiantkillerclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.easygiantkillerclaimed != true && player.hillgiantKills >= 20) {
				player.getInventory().addItemDrop(13666, 1);
				player.diarypoints++;
				player.Drypoints += pointvary;
				player.easygiantkillerclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 8:
			if (player.easyslayerapprenticeclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.easyslayerapprenticeclaimed != true && player.slayertargetkills >= 100) {
				player.getInventory().addItemDrop(29374, 1);
				player.diarypoints++;
				player.Drypoints += pointvary;
				player.easyslayerapprenticeclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 9:
			if (player.easybrothersclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.easybrothersclaimed != true && player.barrowchestloot >= 5) {
				player.getInventory().addItemDrop(29541, 1);
				player.diarypoints++;
				player.Drypoints += pointvary;
				player.easybrothersclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 10:
			if (player.easyvoterclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.easyvoterclaimed != true && player.getvotepoints() >= 6) {
				player.getInventory().addItemDrop(29532, 1);
				player.diarypoints++;
				player.Drypoints += pointvary;
				player.easyvoterclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 11:
			if (player.easyattackclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.easyattackclaimed != true && player.getSkills().getLevel(Skills.ATTACK) == 99) {
				player.getInventory().addItemDrop(5022, 2);
				player.diarypoints++;
				player.Drypoints += pointvary;
				player.easyattackclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 12:
			if (player.easystrengthclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.easystrengthclaimed != true && player.getSkills().getLevel(Skills.STRENGTH) == 99) {
				player.getInventory().addItemDrop(5022, 2);
				player.diarypoints++;
				player.Drypoints += pointvary;
				player.easystrengthclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 13:
			if (player.easydefenceclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.easydefenceclaimed != true && player.getSkills().getLevel(Skills.DEFENCE) == 99) {
				player.getInventory().addItemDrop(5022, 2);
				player.diarypoints++;
				player.Drypoints += pointvary;
				player.easydefenceclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 14:
			if (player.easyhpclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.easyhpclaimed != true && player.getSkills().getLevel(Skills.HITPOINTS) == 99) {
				player.getInventory().addItemDrop(5022, 2);
				player.diarypoints++;
				player.Drypoints += pointvary;
				player.easyhpclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 15:
			if (player.easyrangedclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.easyrangedclaimed != true && player.getSkills().getLevel(Skills.RANGE) == 99) {
				player.getInventory().addItemDrop(5022, 2);
				player.diarypoints++;
				player.Drypoints += pointvary;
				player.easyrangedclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 16:
			if (player.easymagicclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.easymagicclaimed != true && player.getSkills().getLevel(Skills.MAGIC) == 99) {
				player.getInventory().addItemDrop(5022, 2);
				player.diarypoints++;
				player.Drypoints += pointvary;
				player.easymagicclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 17:
			if (player.easyprayerclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.easyprayerclaimed != true && player.getSkills().getLevel(Skills.PRAYER) == 99) {
				player.getInventory().addItemDrop(5022, 2);
				player.diarypoints++;
				player.Drypoints += pointvary;
				player.easyprayerclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 18:
			if (player.easysummoningclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.easysummoningclaimed != true && player.getSkills().getLevel(Skills.SUMMONING) == 99) {
				player.getInventory().addItemDrop(5022, 2);
				player.diarypoints++;
				player.Drypoints += pointvary;
				player.easysummoningclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 19:
			if (player.easyslayerclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.easyslayerclaimed != true && player.getSkills().getLevel(Skills.SLAYER) == 99) {
				player.getInventory().addItemDrop(5022, 2);
				player.diarypoints++;
				player.Drypoints += pointvary;
				player.easyslayerclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 20:
			if (player.easyrcclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.easyrcclaimed != true && player.getSkills().getLevel(Skills.RUNECRAFTING) == 99) {
				player.getInventory().addItemDrop(5022, 2);
				player.diarypoints++;
				player.Drypoints += pointvary;
				player.easyrcclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 21:
			if (player.easyconstructionclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.easyconstructionclaimed != true
					&& player.getSkills().getLevel(Skills.CONSTRUCTION) == 99) {
				player.getInventory().addItemDrop(5022, 2);
				player.diarypoints++;
				player.Drypoints += pointvary;
				player.easyconstructionclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 22:
			if (player.easydungeoneeringclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.easydungeoneeringclaimed != true
					&& player.getSkills().getLevel(Skills.DUNGEONEERING) >= 99) {
				player.getInventory().addItemDrop(5022, 2);
				player.diarypoints++;
				player.Drypoints += pointvary;
				player.easydungeoneeringclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 23:
			if (player.easyagilityclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.easyagilityclaimed != true && player.getSkills().getLevel(Skills.AGILITY) == 99) {
				player.getInventory().addItemDrop(5022, 2);
				player.diarypoints++;
				player.Drypoints += pointvary;
				player.easyagilityclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 24:
			if (player.easyherbloreclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.easyherbloreclaimed != true && player.getSkills().getLevel(Skills.HERBLORE) == 99) {
				player.getInventory().addItemDrop(5022, 2);
				player.diarypoints++;
				player.Drypoints += pointvary;
				player.easyherbloreclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 25:
			if (player.easythievingclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.easythievingclaimed != true && player.getSkills().getLevel(Skills.THIEVING) == 99) {
				player.getInventory().addItemDrop(5022, 2);
				player.diarypoints++;
				player.Drypoints += pointvary;
				player.easythievingclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 26:
			if (player.easycraftingclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.easycraftingclaimed != true && player.getSkills().getLevel(Skills.CRAFTING) == 99) {
				player.getInventory().addItemDrop(5022, 2);
				player.diarypoints++;
				player.Drypoints += pointvary;
				player.easycraftingclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 27:
			if (player.easyfletchingclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.easyfletchingclaimed != true && player.getSkills().getLevel(Skills.FLETCHING) == 99) {
				player.getInventory().addItemDrop(5022, 2);
				player.diarypoints++;
				player.Drypoints += pointvary;
				player.easyfletchingclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 28:
			if (player.easyhunterclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.easyhunterclaimed != true && player.getSkills().getLevel(Skills.HUNTER) == 99) {
				player.getInventory().addItemDrop(5022, 2);
				player.diarypoints++;
				player.Drypoints += pointvary;
				player.easyhunterclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 29:
			if (player.easyminingclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.easyminingclaimed != true && player.getSkills().getLevel(Skills.MINING) == 99) {
				player.getInventory().addItemDrop(5022, 2);
				player.diarypoints++;
				player.Drypoints += pointvary;
				player.easyminingclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 30:
			if (player.easysmithingclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.easysmithingclaimed != true && player.getSkills().getLevel(Skills.SMITHING) == 99) {
				player.getInventory().addItemDrop(5022, 2);
				player.diarypoints++;
				player.Drypoints += pointvary;
				player.easysmithingclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 31:
			if (player.easyfishingclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.easyfishingclaimed != true && player.getSkills().getLevel(Skills.FISHING) == 99) {
				player.getInventory().addItemDrop(5022, 2);
				player.diarypoints++;
				player.Drypoints += pointvary;
				player.easyfishingclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 32:
			if (player.easycookingclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.easycookingclaimed != true && player.getSkills().getLevel(Skills.COOKING) == 99) {
				player.getInventory().addItemDrop(5022, 2);
				player.diarypoints++;
				player.Drypoints += pointvary;
				player.easycookingclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 33:
			if (player.easyfiremakingclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.easyfiremakingclaimed != true && player.getSkills().getLevel(Skills.FIREMAKING) == 99) {
				player.getInventory().addItemDrop(5022, 2);
				player.diarypoints++;
				player.Drypoints += pointvary;
				player.easyfiremakingclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 34:
			if (player.easywoodcuttingclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.easywoodcuttingclaimed != true && player.getSkills().getLevel(Skills.WOODCUTTING) == 99) {
				player.getInventory().addItemDrop(5022, 2);
				player.diarypoints++;
				player.Drypoints += pointvary;
				player.easywoodcuttingclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		case 35:
			if (player.easyfarmingclaimed == true) {
				player.sendMessage(Colors.red + "You have already claimed this reward!");
				break;
			} else if (player.easyfarmingclaimed != true && player.getSkills().getLevel(Skills.FARMING) == 99) {
				player.getInventory().addItemDrop(5022, 2);
				player.diarypoints++;
				player.Drypoints += pointvary;
				player.easyfarmingclaimed = true;
				player.sendMessage(Colors.green
						+ "Your rewards have been added to your inventory! If your inventory was full, check the floor!");
				break;
			} else {
				player.sendMessage(Colors.red + "You haven't completed this achievement yet!");
				break;
			}
		default:
			player.sendMessage("Seems this didn't work? Please report this to staff.");
			break;

		}
//		if (player.claimNewEasyDiaryReward != true && CompletedEasyDiary(player)) {
//			World.sendIconWorldMessage(
//					Colors.gray + "" + player.getDisplayName() + " has just completed the Easy achievement diary!",
//					false);
//			player.claimEasyDiaryReward = true;
//			return;
//		}
	}

	public static void HandleEasyButtons(Player player, int componentId) {
		int pointvary = player.isHeroic() ? 200 : player.isHard() ? 175 : player.isAverage() ? 150 : 100;

		switch (componentId) {
		case 34:
			OpenInterface(player);
			break;
		case 42:
			HandleEasyRewards(player, componentId);
			break;
		/**
		 * Easy Diary
		 */
		case 38:
			player.achievementdiaryslot = 2;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Open 5 Clue Scrolls. <br><br>Progress " + player.cluescompleted + "/5");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>1 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(5022, 2));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 46:
			player.achievementdiaryslot = 3;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Kill 50 NPCs. <br><br>Progress " + player.TotalKills() + "/50");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>1 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(5022, 2));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 47:
			player.achievementdiaryslot = 4;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Kill 50 Rock Crabs. <br><br>Progress " + player.rockcrabKills + "/50");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>1 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(3695, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 48:
			player.achievementdiaryslot = 5;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Kill 25 Bork. <br><br>Progress " + player.BorkKills + "/25");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>1 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(29541, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 51:
			player.achievementdiaryslot = 6;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Kill 40 Bosses. <br><br>Progress " + player.BossKills() + "/40");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>1 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(2677, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 52:
			player.achievementdiaryslot = 7;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Kill 20 Hill Giants. <br><br>Progress " + player.hillgiantKills + "/20");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>1 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(13666, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 29:
			player.achievementdiaryslot = 8;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Kill 100 Slayer Targets. <br><br>Progress " + player.slayertargetkills + "/100");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>1 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(29374, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 54:
			player.achievementdiaryslot = 9;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Loot 5 Barrows Chests. <br><br>Progress " + player.barrowchestloot + "/5");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>1 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(29541, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 65:
			player.achievementdiaryslot = 10;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Claim 6 Votes. <br><br>Progress " + player.getvotepoints() + "/6");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>1 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(29532, 1));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 66:
			player.achievementdiaryslot = 11;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Achieve Level 99 Attack. <br><br>Progress " + player.getSkills().getLevel(Skills.ATTACK) + "/99");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>1 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(5022, 2));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 67:
			player.achievementdiaryslot = 12;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40, "Achieve Level 99 Strength. <br><br>Progress "
					+ player.getSkills().getLevel(Skills.STRENGTH) + "/99");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>1 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(5022, 2));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 68:
			player.achievementdiaryslot = 13;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40, "Achieve Level 99 Defence. <br><br>Progress "
					+ player.getSkills().getLevel(Skills.DEFENCE) + "/99");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>1 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(5022, 2));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 69:
			player.achievementdiaryslot = 14;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40, "Achieve Level 99 Hitpoints. <br><br>Progress "
					+ player.getSkills().getLevel(Skills.HITPOINTS) + "/99");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>1 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(5022, 2));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 70:
			player.achievementdiaryslot = 15;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Achieve Level 99 Ranged. <br><br>Progress " + player.getSkills().getLevel(Skills.RANGE) + "/99");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>1 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(5022, 2));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 71:
			player.achievementdiaryslot = 16;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Achieve Level 99 Magic. <br><br>Progress " + player.getSkills().getLevel(Skills.MAGIC) + "/99");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>1 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(5022, 2));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 72:
			player.achievementdiaryslot = 17;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Achieve Level 99 Prayer. <br><br>Progress " + player.getSkills().getLevel(Skills.PRAYER) + "/99");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>1 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(5022, 2));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 73:
			player.achievementdiaryslot = 18;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40, "Achieve Level 99 Summoning. <br><br>Progress "
					+ player.getSkills().getLevel(Skills.SUMMONING) + "/99");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>1 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(5022, 2));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 74:
			player.achievementdiaryslot = 19;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Achieve Level 99 Slayer. <br><br>Progress " + player.getSkills().getLevel(Skills.SLAYER) + "/99");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>1 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(5022, 2));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 75:
			player.achievementdiaryslot = 20;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40, "Achieve Level 99 Runecrafting. <br><br>Progress "
					+ player.getSkills().getLevel(Skills.RUNECRAFTING) + "/99");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>1 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(5022, 2));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 76:
			player.achievementdiaryslot = 21;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40, "Achieve Level 99 Construction. <br><br>Progress "
					+ player.getSkills().getLevel(Skills.CONSTRUCTION) + "/99");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>1 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(5022, 2));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 77:
			player.achievementdiaryslot = 22;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40, "Achieve Level 99 Dungeoneering. <br><br>Progress "
					+ player.getSkills().getLevel(Skills.DUNGEONEERING) + "/99");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>1 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(5022, 2));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 78:
			player.achievementdiaryslot = 23;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40, "Achieve Level 99 Agility. <br><br>Progress "
					+ player.getSkills().getLevel(Skills.AGILITY) + "/99");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>1 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(5022, 2));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 79:
			player.achievementdiaryslot = 24;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40, "Achieve Level 99 Herblore. <br><br>Progress "
					+ player.getSkills().getLevel(Skills.HERBLORE) + "/99");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>1 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(5022, 2));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 80:
			player.achievementdiaryslot = 25;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40, "Achieve Level 99 Thieving. <br><br>Progress "
					+ player.getSkills().getLevel(Skills.THIEVING) + "/99");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>1 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(5022, 2));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 81:
			player.achievementdiaryslot = 26;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40, "Achieve Level 99 Crafting. <br><br>Progress "
					+ player.getSkills().getLevel(Skills.CRAFTING) + "/99");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>1 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(5022, 2));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 82:
			player.achievementdiaryslot = 27;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40, "Achieve Level 99 Fletching. <br><br>Progress "
					+ player.getSkills().getLevel(Skills.FLETCHING) + "/99");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>1 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(5022, 2));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 83:
			player.achievementdiaryslot = 28;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Achieve Level 99 Hunter. <br><br>Progress " + player.getSkills().getLevel(Skills.HUNTER) + "/99");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>1 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(5022, 2));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 84:
			player.achievementdiaryslot = 29;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40,
					"Achieve Level 99 Mining. <br><br>Progress " + player.getSkills().getLevel(Skills.MINING) + "/99");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>1 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(5022, 2));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 85:
			player.achievementdiaryslot = 30;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40, "Achieve Level 99 Smithing. <br><br>Progress "
					+ player.getSkills().getLevel(Skills.SMITHING) + "/99");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>1 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(5022, 2));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 86:
			player.achievementdiaryslot = 31;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40, "Achieve Level 99 Fishing. <br><br>Progress "
					+ player.getSkills().getLevel(Skills.FISHING) + "/99");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>1 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(5022, 2));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 87:
			player.achievementdiaryslot = 32;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40, "Achieve Level 99 Cooking. <br><br>Progress "
					+ player.getSkills().getLevel(Skills.COOKING) + "/99");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>1 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(5022, 2));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 88:
			player.achievementdiaryslot = 33;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40, "Achieve Level 99 Firemaking. <br><br>Progress "
					+ player.getSkills().getLevel(Skills.FIREMAKING) + "/99");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>1 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(5022, 2));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 89:
			player.achievementdiaryslot = 34;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40, "Achieve Level 99 Woodcutting. <br><br>Progress "
					+ player.getSkills().getLevel(Skills.WOODCUTTING) + "/99");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>1 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(5022, 2));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		case 90:
			player.achievementdiaryslot = 35;
			/**
			 * Description + progress
			 */
			player.getPackets().sendIComponentText(INTER, 40, "Achieve Level 99 Farming. <br><br>Progress "
					+ player.getSkills().getLevel(Skills.FARMING) + "/99");
			/**
			 * Rewards
			 */
			player.getPackets().sendIComponentText(INTER, 64, "" + pointvary + " Harmony Points<br><br>1 Diary Point");
			player.getSpareContainer().getContainer().clear();
			player.getSpareContainer().getContainer().add(new Item(5022, 2));
			player.getPackets().sendInterSetItemsOptionsScript(INTER, 92, 101, 8, 3, "Examine");
			player.getPackets().sendUnlockIComponentOptionSlots(INTER, 92, 0, 10, 0, 1, 2, 3);
			player.getSpareContainer().getContainer().shift();
			player.getPackets().sendItems(101, player.getSpareContainer().getContainer());
			break;
		}
	}

	public static void HandleButtons(Player player, int componentId, int slotId) {
		Item item = player.getSpareContainer().getContainer().get(slotId);
		if (componentId == 20) {
			OpenInterface(player);
		} else if (componentId == 22) {
			OpenMediumPage(player);
		} else if (componentId == 24) {
			OpenHardPage(player);
		} else if (componentId == 26) {
			OpenElitePage(player);
		} else if (componentId == 28) {
			OpenMasterPage(player);
		} else if (componentId == 98) {
			ShopsHandler.openShop(player, 134);
		} else if (componentId == 92) {
			player.sendMessage(item.getDefinitions().getExamine());
		}
		if (player.achievementdiarypage == 2) {
			HandleMediumButtons(player, componentId);
		} else if (player.achievementdiarypage == 3) {
			HandleHardButtons(player, componentId);
		} else if (player.achievementdiarypage == 4) {
			HandleEliteButtons(player, componentId);
		} else if (player.achievementdiarypage == 5) {
			HandleMasterButtons(player, componentId);
		} else {
			HandleEasyButtons(player, componentId);
			return;
		}
	}

	public static boolean CompletedEasyDiary(Player player) {
		if (player.easycchestclaimed == true && player.easycluelessclaimed == true && player.easymobsclaimed == true
				&& player.easyrockyclaimed && player.easyborkclaimed == true && player.easybosskillerclaimed == true
				&& player.easygiantkillerclaimed == true && player.easyslayerapprenticeclaimed == true
				&& player.easybrothersclaimed == true && player.easyvoterclaimed && player.easyattackclaimed == true
				&& player.easystrengthclaimed == true && player.easydefenceclaimed && player.easyhpclaimed
				&& player.easyrangedclaimed && player.easymagicclaimed && player.easyprayerclaimed
				&& player.easysummoningclaimed && player.easyslayerclaimed && player.easyrcclaimed
				&& player.easyconstructionclaimed && player.easydungeoneeringclaimed && player.easyagilityclaimed
				&& player.easyherbloreclaimed && player.easythievingclaimed && player.easycraftingclaimed
				&& player.easyfletchingclaimed && player.easyhunterclaimed && player.easyminingclaimed
				&& player.easysmithingclaimed && player.easyfishingclaimed && player.easycookingclaimed
				&& player.easyfiremakingclaimed && player.easywoodcuttingclaimed && player.easyfarmingclaimed)
			return true;
		return false;
	}

	public static boolean CompletedMediumDiary(Player player) {
		if (player.mediumcchestclaimed && player.mediumcluesclaimed && player.mediummobsclaimed
				&& player.mediumoverloadclaimed && player.mediumpdclaimed && player.mediumzulrahclaimed
				&& player.mediumsireclaimed && player.mediumkrakenclaimed && player.mediumlizardmanclaimed
				&& player.mediumbarrowsclaimed && player.mediumbossesclaimed && player.mediumvoteclaimed
				&& player.mediumdungclaimed && player.mediumplaytimeclaimed && player.medium600claimed
				&& player.medium1000claimed && player.medium1500claimed && player.mediumslayertargetsclaimed
				&& player.mediumddefenderclaimed && player.mediumcalamityclaimed && player.mediumheistclaimed
				&& player.mediumslayersurvivalclaimed && player.mediumragingclaimed && player.mediumimbueclaimed)
			return true;
		return false;
	}

	public static boolean CompletedHardDiary(Player player) {
		if (player.hardcchestclaimed && player.hardcluesclaimed && player.hardmobsclaimed && player.hardbossesclaimed
				&& player.hardxericclaimed && player.hardtobclaimed && player.hardtrioclaimed
				&& player.hardenhancementclaimed && player.hardfightcavesclaimed && player.hardrunecoinclaimed
				&& player.hardvoteclaimed && player.hardraredropclaimed && player.hardchaoticclaimed
				&& player.hardptclaimed && player.hardslayertargetsclaimed && player.hardcalamityclaimed
				&& player.hardheistclaimed && player.hardelitevoidclaimed && player.harddropboostclaimed
				&& player.hardbarrowsclaimed && player.harddailyclaimed && player.hardtogclaimed
				&& player.hardrunebodyclaimed && player.hardcorpclimed && player.hardxpwellclaimed)
			return true;
		return false;
	}

	public static boolean CompletedEliteDiary(Player player) {
		if (player.elitecchestclaimed && player.elitecluesclaimed && player.elitemobsclaimed
				&& player.elitebossesclaimed && player.elitexericclaimed && player.elitetobclaimed
				&& player.eliterunedragclaimed && player.eliteragingclaimed && player.elitedtdclaimed
				&& player.elitekilnclaimed && player.elitepwclaimed && player.elitercclaimed
				&& player.eliteprestigeclaimed && player.elitevoteclaimed && player.elitethclaimed
				&& player.eliteturmclaimed && player.eliteptclaimed && player.eliteslayertargetsclaimed
				&& player.elitecorruptedclaimed && player.elitepetclaimed && player.elitefoodhealclaimed
				&& player.eliteconnorclaimed && player.elitethunderousclaimed && player.elitebunnykillerclaimed
				&& player.eliteassassinclaimed)
			return true;
		return false;
	}

	public static boolean CompletedMasterDiary(Player player) {
		if (player.mastercchestclaimed && player.mastercluesclaimed && player.mastermobsclaimed
				&& player.masterbossesclaimed && player.masterraidsclaimed && player.masterragingclaimed
				&& player.masterheistclaimed && player.masterslayertargetsclaimed && player.masterrecycleclaimed
				&& player.masterwildernessclaimed && player.masterdyedclaimed && player.masterdungclaimed
				&& player.masterptclaimed && player.mastervoteclaimed && player.mastersealclaimed
				&& player.mastercelestiaclaimed && player.masterraptorclaimed && player.mastertrollclaimed
				&& player.masterwellclaimed && player.mastercalaclaimed && player.mastertrollpotclaimed
				&& player.masterblinkclaimed && player.masterseasonclaimed && player.masterthclaimed)
			return true;
		return false;
	}

	public static boolean CompletedAllDiarys(Player player) {
		if (CompletedHardDiary(player) && CompletedMediumDiary(player) && CompletedEasyDiary(player)
				&& CompletedEliteDiary(player))
			return true;
		return false;
	}

	public static boolean CompletedAllDiarysMaster(Player player) {
		if (CompletedHardDiary(player) && CompletedMediumDiary(player) && CompletedEasyDiary(player)
				&& CompletedEliteDiary(player) && CompletedMasterDiary(player))
			return true;
		return false;
	}
}
