package com.rs.game.player.dialogues.impl;

import com.rs.game.player.dialogues.Dialogue;
import com.rs.utils.Utils;

public class SkillingTask extends Dialogue {

	int npcId = 30052;
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
		if (player.WoodcuttingTask == true || player.FishingTask == true || player.MiningTask == true || player.CraftingTask == true
				|| player.AgilityTask == true || player.CookingTask == true 
				&& player.WoodcuttingTaskMagic > 0 || player.WoodcuttingTaskMaple > 0 || player.WoodcuttingTaskYew > 0 || player.FishingTaskShark > 0
				|| player.FishingTaskRocktail > 0 || player.FishingTaskLobster > 0 || player.MiningTaskAddy > 0 || player.MiningTaskRune > 0
				|| player.MiningTaskMith > 0 || player.CraftingTaskDiamond > 0 || player.CraftingTaskOnyx > 0 || player.CraftingTaskDragonstone > 0
				|| player.AgilityTaskBarb > 0 || player.AgilityTaskBarb > 0 || player.CookingTaskLobster > 0 || player.CookingTaskRocktail > 0 
				|| player.CookingTaskShark > 0) {
			player.getDialogueManager().startDialogue("SimpleMessage", "You already have a task active");
		
		}
		else {
		//npcId = (Integer) parameters[0];
		sendNPCDialogue(npcId, 9827, "Hello. How can i help you?");
			}
		}

	@Override
	public void run(int interfaceId, int componentId) {
		switch(stage) {
		case -1:
			stage = 20;
			sendOptionsDialogue(SEND_DEFAULT_OPTIONS_TITLE, "Can i get a new task please",  "What rewards can i get?");
			break;
		case 20:
			if (componentId == OPTION_1) {
				stage = 21;
				sendPlayerDialogue(9827, "I need a task please.");
		} else if(componentId == OPTION_2) {
				stage = 22;
				sendPlayerDialogue(9827, "What can i get from doing this?");
			}
			break;
		case 21:
			stage = 24;
			sendNPCDialogue(npcId, 9827, "Of course. Your task is...");
			break;
		case 22:
			stage = 23;
			sendNPCDialogue(npcId, 9827, "You can gain extra experience and skilling points for my store.");
			break;
		case 23:
			stage = 7;
			sendNPCDialogue(npcId, 9827, "These points can be used for raw materials and cosmetics.");
			break;
		case 24:
			task = Utils.random(5);
	    	switch (task) {
	    	case 0:
	    		stage = 7;
	    		player.WoodcuttingTask = true;
	    		woodcut = Utils.random(2);
		    	switch (woodcut) {
		    	case 0:
		    		player.WoodcuttingTaskMagic = Utils.random(150) + 1;
		    		player.wctmagic = true;
		    		sendNPCDialogue(npcId, 9827, "Okay so your task is... to cut "+player.WoodcuttingTaskMagic+" Magic Trees");
		    		break;
		    	case 1:
		    		player.WoodcuttingTaskYew = Utils.random(150)  + 1;
		    		player.wctyews = true;
		    		sendNPCDialogue(npcId, 9827, "Okay so your task is... to cut "+player.WoodcuttingTaskYew+" Yew Trees");
		    		break;
		    	default:
		    		player.WoodcuttingTaskMaple = Utils.random(150)  + 1;
		    		player.wctmaples = true;
		    		sendNPCDialogue(npcId, 9827, "Okay so your task is... to cut "+player.WoodcuttingTaskMaple+" Maple Trees");
		    		break;
		    	}
	    		break;
	    	case 1:
	    		stage = 7;
	    		//sendNPCDialogue(npcId, 9827, "Okay so your task is...");
	    		player.FishingTask = true;
	    		fishing = Utils.random(2);
		    	switch (fishing) {
		    	case 0:
		    		player.FishingTaskShark = Utils.random(150)  + 1;
		    		player.ftshark = true;
		    		sendNPCDialogue(npcId, 9827, "Okay so your task is... to catch "+player.FishingTaskShark+" Sharks");
		    		break;
		    	case 1:
		    		player.FishingTaskRocktail = Utils.random(150)  + 1;
		    		player.ftrocktail = true;
		    		sendNPCDialogue(npcId, 9827, "Okay so your task is... to catch "+player.FishingTaskRocktail+" Rocktails");
		    		break;
		    	default:
		    		player.FishingTaskLobster = Utils.random(150)  + 1;
		    		player.ftlobster = true;
		    		sendNPCDialogue(npcId, 9827, "Okay so your task is... to catch "+player.FishingTaskLobster+" Lobsters");
		    		break;
		    	}
	    		break;
	    	case 2:
	    		stage = 7;
	    	//	sendNPCDialogue(npcId, 9827, "Okay so your task is...");
	    		player.MiningTask = true;
	    		mining = Utils.random(2);
		    	switch (mining) {
		    	case 0:
		    		player.MiningTaskAddy = Utils.random(150)  + 1;
		    		player.mtaddy = true;
		    		sendNPCDialogue(npcId, 9827, "Okay so your task is... to mine "+player.MiningTaskAddy+" Adamantite ore");
		    		break;
		    	case 1:
		    		player.MiningTaskRune = Utils.random(150)  + 1;
		    		player.mtrune = true;
		    		sendNPCDialogue(npcId, 9827, "Okay so your task is... to mine "+player.MiningTaskRune+" Runite ore");
		    		break;
		    	default:
		    		player.MiningTaskMith = Utils.random(150)  + 1;
		    		player.mtmith = true;
		    		sendNPCDialogue(npcId, 9827, "Okay so your task is... to mine "+player.MiningTaskMith+" Mithril ore");
		    		break;
		    	}
	    		break;
	    	case 3:
	    		stage = 7;
	   // 		sendNPCDialogue(npcId, 9827, "Okay so your task is...");
	    		player.CraftingTask = true;
	    		crafting = Utils.random(2);
		    	switch (crafting) {
		    	case 0:
		    		player.CraftingTaskDiamond = Utils.random(150)  + 1;
		    		player.ctdiamond = true;
		    		sendNPCDialogue(npcId, 9827, "Okay so your task is... to craft "+player.CraftingTaskDiamond+" Diamonds");
		    		break;
		    	case 1:
		    		player.CraftingTaskOnyx = Utils.random(2)  + 1;
		    		player.ctonyx = true;
		    		sendNPCDialogue(npcId, 9827, "Okay so your task is... to craft "+player.CraftingTaskOnyx+" Onyx");
		    		break;
		    	default:
		    		player.CraftingTaskDragonstone = Utils.random(150)  + 1;
		    		player.ctdragonstone = true;
		    		sendNPCDialogue(npcId, 9827, "Okay so your task is... to mine "+player.CraftingTaskDragonstone+" Dragonstones");
		    		break;
		    	}
	    		break;
	    	case 4:
	    		stage = 7;
	    		player.AgilityTask = true;
	    		agility = Utils.random(1);
		    	switch (agility) {
		    	case 0:
		    		player.AgilityTaskBarb = Utils.random(150)  + 1;
		    		player.atbarb = true;
		    		sendNPCDialogue(npcId, 9827, "Okay so your task is... to run "+player.AgilityTaskBarb+" Barbarian agility laps.");
		    		break;
		    	default:
		    		player.AgilityTaskGnome = Utils.random(150)  + 1;
		    		player.atgnome = true;
		    		sendNPCDialogue(npcId, 9827, "Okay so your task is... to run "+player.AgilityTaskGnome+" Gnome agility laps.");
		    		break;
		    	}
	    		break;
	    	default:
	    		stage = 7;
	    		player.CookingTask = true;
	    		cooking = Utils.random(2);
		    	switch (cooking) {
		    	case 0:
		    		player.CookingTaskRocktail = Utils.random(150)  + 1;
		    		player.ctrocktail = true;
		    		sendNPCDialogue(npcId, 9827, "Okay so your task is... to cook "+player.CookingTaskRocktail+" Rocktails");
		    		break;
		    	case 1:
		    		player.CookingTaskShark = Utils.random(150)  + 1;
		    		player.ctshark = true;
		    		sendNPCDialogue(npcId, 9827, "Okay so your task is... to cook "+player.CookingTaskShark+" Sharks");
		    		break;
		    	default:
		    		player.CookingTaskLobster = Utils.random(150)  + 1;
		    		player.ctlobster = true;
		    		sendNPCDialogue(npcId, 9827, "Okay so your task is... to cook "+player.CookingTaskLobster+" Lobsters");
		    		break;
		    	}
	    		break;
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
