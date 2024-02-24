package com.rs.game.player.dialogues.impl;

import com.rs.game.player.Skills;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.utils.Utils;

public class SkillingTaskRewards extends Dialogue {

	int npcId = 30051;
	private static int task;
	private static int woodcut;
	//private static int woodcutamount;
	private static int fishing;
//	private static int fishingamount;
	private static int agility;
	//private static int agilityamount;
	private static int mining;
//	private static int miningamount;
	private static int cooking;
	//private static int cookingamount;
	private static int crafting;
	//private static int craftingamount;
	

	@Override
	public void start() {

		//npcId = (Integer) parameters[0];
		sendNPCDialogue(npcId, 9827, "Hello buddy, how can i help you?");
			}
		

	@Override
	public void run(int interfaceId, int componentId) {
		switch(stage) {
		case -1:
			stage = 20;
			sendOptionsDialogue(SEND_DEFAULT_OPTIONS_TITLE, "Hand in assignment",  "Rewards");
			break;
		case 20:
			if (componentId == OPTION_1) {
				stage = 21;
				sendPlayerDialogue(9827, "Could i hand in an assignment please.");
		} else if(componentId == OPTION_2) {
				stage = 7;
				sendPlayerDialogue(9827, "Could i see the rewards please boss.");
			}
			break;
		case 21:
			stage = 23;
			sendNPCDialogue(npcId, 9827, "Of course, let me see what you've done...");
			break;
		case 22:
			//ADD SHOP CODE
			break;
		case 23:
			if (player.WoodcuttingTask != true && player.CookingTask != true && player.AgilityTask != true
					&& player.CraftingTask != true && player.FishingTask != true && player.MiningTask != true) {
				stage = 7;
				sendNPCDialogue(npcId, 9827, "Sorry buddy, it doesn't seem you have any tasks completed for me. "
						+ "Type ;;mycurrenttask to see your progress.");
			} else {
				if (player.WoodcuttingTaskYew == 0 && player.WoodcuttingTask == true && player.wctyews == true) {
					stage = 7;
					sendNPCDialogue(npcId, 9827, "I see you've had a task of cutting Yew logs. Heres a reward..");
					player.getSkills().addXp(Skills.WOODCUTTING, 1750);
					player.WoodcuttingTask = false;
					player.wctyews = false;
					player.SkillingTasksComplete ++;
					player.SkillTaskPoints ++;
				} else if (player.WoodcuttingTaskMagic == 0 && player.WoodcuttingTask == true && player.wctmagic == true) {
					stage = 7;
					sendNPCDialogue(npcId, 9827, "I see you've had a task of cutting Magic logs. Heres a reward..");
					player.getSkills().addXp(Skills.WOODCUTTING, 2500);
					player.WoodcuttingTask = false;
					player.wctmagic = false;
					player.SkillingTasksComplete ++;
					player.SkillTaskPoints ++;
				} else if (player.WoodcuttingTaskMaple == 0 && player.WoodcuttingTask == true && player.wctmaples == true) {
					stage = 7;
					sendNPCDialogue(npcId, 9827, "I see you've had a task of cutting Maple logs. Heres a reward..");
					player.getSkills().addXp(Skills.WOODCUTTING, 1000);
					player.WoodcuttingTask = false;
					player.wctmaples = false;
					player.SkillingTasksComplete ++;
					player.SkillTaskPoints ++;
				} else if (player.FishingTaskLobster == 0 && player.FishingTask == true && player.ftlobster == true) {
					stage = 7;
					sendNPCDialogue(npcId, 9827, "I see you've had a task of catching Lobsters. Heres a reward..");
					player.getSkills().addXp(Skills.FISHING, 900);
					player.FishingTask = false;
					player.ftlobster = false;
					player.SkillingTasksComplete ++;
					player.SkillTaskPoints ++;
				}else if (player.FishingTaskShark == 0 && player.FishingTask == true && player.ftshark == true) {
					stage = 7;
					sendNPCDialogue(npcId, 9827, "I see you've had a task of catching Sharks. Heres a reward..");
					player.getSkills().addXp(Skills.FISHING, 1100);
					player.FishingTask = false;
					player.ftshark = false;
					player.SkillingTasksComplete ++;
					player.SkillTaskPoints ++;
				}else if (player.FishingTaskRocktail == 0 && player.FishingTask == true && player.ftrocktail == true) {
					stage = 7;
					sendNPCDialogue(npcId, 9827, "I see you've had a task of catching Rocktails. Heres a reward..");
					player.getSkills().addXp(Skills.FISHING, 1600);
					player.FishingTask = false;
					player.ftrocktail = false;
					player.SkillingTasksComplete ++;
					player.SkillTaskPoints ++;
				}else if (player.MiningTaskAddy == 0 && player.MiningTask == true && player.mtaddy == true) {
					stage = 7;
					sendNPCDialogue(npcId, 9827, "I see you've had a task of mining Adamantite ore. Heres a reward..");
					player.getSkills().addXp(Skills.MINING, 950);
					player.MiningTask = false;
					player.mtaddy = false;
					player.SkillingTasksComplete ++;
					player.SkillTaskPoints ++;
				}else if (player.MiningTaskMith == 0 && player.MiningTask == true && player.mtmith == true) {
					stage = 7;
					sendNPCDialogue(npcId, 9827, "I see you've had a task of mining Mithril ore. Heres a reward..");
					player.getSkills().addXp(Skills.MINING, 800);
					player.MiningTask = false;
					player.mtmith = false;
					player.SkillingTasksComplete ++;
					player.SkillTaskPoints ++;
				}else if (player.MiningTaskRune == 0 && player.MiningTask == true && player.mtrune == true) {
					stage = 7;
					sendNPCDialogue(npcId, 9827, "I see you've had a task of mining Runite ore. Heres a reward..");
					player.getSkills().addXp(Skills.MINING, 1250);
					player.MiningTask = false;
					player.mtrune = false;
					player.SkillingTasksComplete ++;
					player.SkillTaskPoints ++;
				}else if (player.CraftingTaskDiamond == 0 && player.CraftingTask == true && player.ctdiamond == true) {
					stage = 7;
					sendNPCDialogue(npcId, 9827, "I see you've had a task of cutting Diamonds. Heres a reward..");
					player.getSkills().addXp(Skills.CRAFTING, 1075);
					player.CraftingTask = false;
					player.ctdiamond = false;
					player.SkillingTasksComplete ++;
					player.SkillTaskPoints ++;
				}else if (player.CraftingTaskDragonstone == 0 && player.CraftingTask == true && player.ctdragonstone == true) {
					stage = 7;
					sendNPCDialogue(npcId, 9827, "I see you've had a task of cutting Dragonstones. Heres a reward..");
					player.getSkills().addXp(Skills.CRAFTING, 1375);
					player.CraftingTask = false;
					player.ctdragonstone = false;
					player.SkillingTasksComplete ++;
					player.SkillTaskPoints ++;
				}else if (player.CraftingTaskOnyx == 0 && player.CraftingTask == true && player.ctonyx == true) {
					stage = 7;
					sendNPCDialogue(npcId, 9827, "I see you've had a task of cutting Onyx. Heres a reward..");
					player.getSkills().addXp(Skills.CRAFTING, 1675);
					player.CraftingTask = false;
					player.ctonyx = false;
					player.SkillingTasksComplete ++;
					player.SkillTaskPoints ++;
				}else if (player.AgilityTaskBarb == 0 && player.AgilityTask == true && player.atbarb == true) {
					stage = 7;
					sendNPCDialogue(npcId, 9827, "I see you've had a task of running laps at the Barbarian agility course"
							+ ". Heres a reward..");
					player.getSkills().addXp(Skills.AGILITY, 1150);
					player.AgilityTask = false;
					player.atbarb = false;
					player.SkillingTasksComplete ++;
					player.SkillTaskPoints ++;
				}else if (player.AgilityTaskGnome == 0 && player.AgilityTask == true && player.atgnome == true) {
					stage = 7;
					sendNPCDialogue(npcId, 9827, "I see you've had a task of running laps at the Gnome agility course"
							+ ". Heres a reward..");
					player.getSkills().addXp(Skills.AGILITY, 575);
					player.AgilityTask = false;
					player.atgnome = false;
					player.SkillingTasksComplete ++;
					player.SkillTaskPoints ++;
				}else if (player.CookingTaskLobster == 0 && player.CookingTask == true && player.ctlobster == true) {
					stage = 7;
					sendNPCDialogue(npcId, 9827, "I see you've had a task of cooking Lobsters. Heres a reward..");
					player.getSkills().addXp(Skills.COOKING, 1200);
					player.CookingTask = false;
					player.ctlobster = false;
					player.SkillingTasksComplete ++;
					player.SkillTaskPoints ++;
				}else if (player.CookingTaskRocktail == 0 && player.CookingTask == true && player.ctrocktail == true) {
					stage = 7;
					sendNPCDialogue(npcId, 9827, "I see you've had a task of cooking Rocktails. Heres a reward..");
					player.getSkills().addXp(Skills.COOKING, 2250);
					player.CookingTask = false;
					player.ctrocktail = false;
					player.SkillingTasksComplete ++;
					player.SkillTaskPoints ++;
				}else if (player.CookingTaskShark == 0 && player.CookingTask == true && player.ctshark == true) {
					stage = 7;
					sendNPCDialogue(npcId, 9827, "I see you've had a task of cooking Sharks. Heres a reward..");
					player.getSkills().addXp(Skills.COOKING, 2100);
					player.CookingTask = false;
					player.ctshark = false;
					player.SkillingTasksComplete ++;
					player.SkillTaskPoints ++;
				}
				
				
				else {
					stage = 7;
					sendNPCDialogue(npcId, 9827, "Either you need a new task or you haven't completed one yet.");
				}
			}
			break;
		case 7:
			player.unlock();
			end();
			break;
		default:
			end();
			break;
		}
	}

	@Override
	public void finish() {

	}

}
