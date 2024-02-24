package com.rs.game.player.dialogues.impl;

import com.rs.Settings;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.player.Skills;
import com.rs.game.player.SlayerTask;
import com.rs.game.player.SlayerTask.Master;
import com.rs.game.player.dialogues.Dialogue;

public class LegendarySlayerMaster extends Dialogue {

	int npcId = 7780;

	@Override
	public void start() {
			sendEntityDialogue(IS_NPC, "Sumona", npcId, 9827,
					"Hello adventurer. As a reminder, any task I assign will provide 50% bonus slayer experience. What can I help you with?");
		}

	@Override
	public void run(int interfaceId, int componentId) {
		switch (stage) {
		case -1:
			if (!player.isTalkedWithKuradal()) {
				stage = 0;
				sendPlayerDialogue(9827, "Who are you?");
			} else {
				stage = 8;
				sendPlayerDialogue(9827,
						"I would like to ask something about my task.");
			}
			break;
		case 0:
			stage = 1;
			sendEntityDialogue(
					IS_NPC,
					"Sumona",
					npcId,
					9827,
					"I'm a slayer master. I give out slayer tasks to legendary warriors around the world of Harmony.");
			break;
		case 1:
			stage = 3;
			sendPlayerDialogue(9827, "Can I get assigned a slayer task?");
			break;
		case 2:
			stage = 3;
			sendEntityDialogue(IS_NPC, "Sumona", npcId, 9827,
					"How do you know my name?! I guess someone leaked it, Oh well...");
			break;
		case 3:
			if (player.getTask() == null) {
				SlayerTask.random(player, Master.SUMONA);
				sendEntityDialogue(IS_NPC, "Sumona", npcId, 9827,
						"Excellent, you're doing great. Your new task is to kill "
								+ player.getTask().getTaskAmount() + " "
								+ player.getTask().getName().toLowerCase()
								+ "s.");
				player.setTalkedWithKuradal();
				stage = 7;
			} else {
				sendEntityDialogue(IS_NPC, "Sumona", npcId, 9827,
						"You're still hunting " + player.getTask().getName().toLowerCase() + "s; come back when you've finished your task.");
				player.setTalkedWithKuradal();
				stage = 7;
			}
			break;
		case 4:
			stage = 5;
			sendPlayerDialogue(9827, "Can you show me where i can kill these?");
			break;
		case 5:
			stage = 6;
			sendEntityDialogue(IS_NPC, "Sumona", npcId, 9827,
					"Well.. You may find them all over, but soon i will be opening my dungeon for you to enter!");
			break;
		case 6:
			stage = 7;
			sendPlayerDialogue(9827,
					"Okay no problem i try find them! Maybe there is a guide on Harmony forums.");
			break;
		case 7: /* Offical end of Dialogue */
			end();
			break;
		case 8:
			stage = 9;
			sendEntityDialogue(IS_NPC, "Sumona", npcId, 9827,
					"What is it, adventurer?");
			break;
		case 9:
			stage = 10;
			sendOptionsDialogue(SEND_DEFAULT_OPTIONS_TITLE,
					"I have finished my task!", "Can you change my task?",
					"I don't remember my task.", "Can I view the slayer shop?", "Can you teleport me to your dungeon?");
			break;
		case 10:
			switch (componentId) {
			case OPTION_1:
				stage = 11;
				sendPlayerDialogue(9827,
						"I have completed the task you assigned me!");
				break;
			case OPTION_2:
				stage = 12;
				sendPlayerDialogue(9827,
						"Can you change my slayer task?");
				break;
			case OPTION_3:
				stage = 21;
				sendPlayerDialogue(9827,
						"I don't remember my slayer task, could you remind me?");
				break;
			case OPTION_4:
				player.getInterfaceManager().sendInterface(164);
				player.getInterfaceManager().sendSlayerTab();
				end();
				break;
			case OPTION_5:
				if (!player.isLegendaryDonator()) {
					player.sendMessage("Stop abusing.");
					return;
				}
				player.setNextWorldTile(new WorldTile(2796, 9285, 0));
				player.getInterfaceManager().closeChatBoxInterface();
				end();
				break;
			}
			break;
		case 11:
			stage = 13;
			if (player.getTask() == null) {
				sendEntityDialogue(IS_NPC, "Sumona", npcId, 9827,
						"Very good, you are a true warrior! Would you like to have a new task?");
			} else {
				sendEntityDialogue(IS_NPC, "Sumona", npcId, 9827,
						"No you haven't, foolish warrior.");
				stage = 7;
			}
			break;
		case 12:
			stage = 14;
			sendEntityDialogue(IS_NPC, "Sumona", npcId, 9827,
					"Okay, I can assign a new task for a fee of 500k. If you're 99 slayer, it's free.");
			break;
		case 13:
			stage = 15;
			sendOptionsDialogue(SEND_DEFAULT_OPTIONS_TITLE, "Yes", "No thanks");
			break;
		case 14:
			if (player.getSkills().getLevelForXp(Skills.SLAYER) > 98) {
				sendPlayerDialogue(9827, "I've already mastered the skill.");
				stage = 25;
				break;
			} else {
			stage = 16;
			sendOptionsDialogue("How do you wish to pay to skip this task?",
						"500k Cash", "Nevermind.");
			break;
			}
		case 15:
			switch (componentId) {
			case OPTION_1:
				stage = 17;
				sendPlayerDialogue(9827, "Yes please");
				break;
			case OPTION_2:
				stage = 24;
				sendPlayerDialogue(9827, "Yes please");
				break;
			case OPTION_3:
				stage = 25;
				sendPlayerDialogue(9827, "Yes please.");
				break;
			case OPTION_4:
				stage = 18;
				sendPlayerDialogue(9827, "No thanks mate.");
				break;
			}
			break;
		case 16:
			switch (componentId) {
			case OPTION_1:
				stage = 19;
				sendPlayerDialogue(9827, "I'll pay you 500k gold.");
				break;
			case OPTION_2:
				stage = 7;
				sendPlayerDialogue(9827, "No thanks");
				break;
			}
			break;
		case 17:
			
			SlayerTask.random(player, Master.SUMONA);
			sendEntityDialogue(IS_NPC, "SUMONA", npcId, 9827,
					"Excellent, you're doing great. Your new task is to kill "
							+ player.getTask().getTaskAmount() + " "
							+ player.getTask().getName().toLowerCase()
							+ "s.");
			stage = 7;
			break;
		case 18:
			sendEntityDialogue(IS_NPC, "SUMONA", npcId, 9827,
					"Okay, see you later my warrior.");
			stage = 7;
			break;
		case 19:
			
			if (player.getMoneyPouch().getCoinAmount() >= 500000) {
				player.coinamount -= World.CommitzoClanZone(player) ? 0 : 500000;
				Settings.GpSyncAmount += World.CommitzoClanZone(player) ? 0 : 500000;
				player.refreshMoneyPouch();
				SlayerTask.random(player, Master.SUMONA);
				sendEntityDialogue(IS_NPC, "SUMONA", npcId, 9827,
						"Excellent, you're doing great. Your new task is to kill "
								+ player.getTask().getTaskAmount() + " "
								+ player.getTask().getName().toLowerCase()
								+ "s.");
				stage = 7;
			} else {
				sendEntityDialogue(IS_NPC, "SUMONA", 7780, 9827,
						"You don't have 500k gold, come back later!");
				stage = 7;
			}
			break;
			case 25:
			if (player.getSkills().getLevelForXp(Skills.SLAYER) > 98) { 
				SlayerTask.random(player, Master.SUMONA);
				sendEntityDialogue(IS_NPC, "SUMONA", 7780, 9827,
						"Excellent, you're doing great. Your new task is to kill "
							+ player.getTask().getTaskAmount() + " "
							+ player.getTask().getName().toLowerCase()
							+ "s.");
				stage = 7;
			} else {
				sendEntityDialogue(IS_NPC, "SUMONA", 7780, 9827,
						"You need 99 slayer to use this.");
				stage = 7;
			}
			break;
		case 24:
			if (player.getInventory().containsItem(13663, 1)) {
				player.getInventory().deleteItem(13663, 1);
				SlayerTask.random(player, Master.SUMONA);
				sendEntityDialogue(IS_NPC, "SUMONA", npcId, 9827,
						"Excellent, you're doing great. Your new task is to kill "
								+ player.getTask().getTaskAmount() + " "
								+ player.getTask().getName().toLowerCase()
								+ "s.");
				stage = 7;
			} else {
				sendEntityDialogue(IS_NPC, "SUMONA", 7780, 9827,
						"You dont have any tickets, come back later!");
				stage = 7;
			}
			break;
		case 20:
			sendEntityDialogue(
					IS_NPC,
					"SUMONA",
					npcId,
					9827,
					"Im the LEGENDARY Slayermaster in Harmony and i think 500k is a good price but okay.");
			stage = 7;
			break;
		case 21:
			sendEntityDialogue(IS_NPC, "SUMONA", npcId, 9827,
							"You're still hunting " + player.getTask().getName().toLowerCase() + "s; come back when you've finished your task.");
			stage = 7;
			break;
		case 22:
			sendEntityDialogue(IS_NPC, "SUMONA", npcId, 9827,
					"Your slayertask is to kill "
							+ player.getTask().getTaskAmount() + " "
							+ player.getTask().getName().toLowerCase()
							+ "s.. Please remember your task next time.");
			stage = 23;
			break;
		case 23:
			sendPlayerDialogue(9827,
					"Sorry SUMONA, I will remember my task...");
			stage = 7;
			break;
		}
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub

	}

}