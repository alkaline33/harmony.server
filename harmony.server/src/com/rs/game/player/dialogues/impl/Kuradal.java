package com.rs.game.player.dialogues.impl;

import com.rs.Settings;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.player.Skills;
import com.rs.game.player.SlayerTask;
import com.rs.game.player.SlayerTask.Master;
import com.rs.game.player.content.Magic;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.utils.ShopsHandler;

public class Kuradal extends Dialogue {

	int npcId;

	@Override
	public void start() {
		if (!player.isTalkedWithKuradal()) {
			sendEntityDialogue(IS_NPC, "Kuradal", 9085, 9827,
					"Hello warrior, what can I do for you?");
		} else {
			sendEntityDialogue(IS_NPC, "Kuradal", 9085, 9827,
					"You're back warrior... what can I do for you?");
		}
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
					"Kuradal",
					9085,
					9827,
					"I'm a slayer master. I give out slayer tasks to warriors around the world of Harmony.");
			break;
		case 1:
			stage = 3;
			sendPlayerDialogue(9827, "Can I get assigned a slayer task?");
			break;
		case 3:
			if (player.getTask() == null) {
				if (player.getSkills().getLevelForXp(Skills.SLAYER) < 50) {
					SlayerTask.random(player, Master.KURADALNUB);
					sendEntityDialogue(IS_NPC, "Kuradal", 9085, 9827,
							"Your task is to kill "
							+ player.getTask().getTaskAmount() + " "
							+ player.getTask().getName().toLowerCase()
							+ "s.");
					player.setTalkedWithKuradal();
					//player.sendMessage("1");
					stage = 7;
				} else if (player.getSkills().getLevelForXp(Skills.SLAYER) >= 50 && player.getSkills().getLevelForXp(Skills.SLAYER) < 80) {
					SlayerTask.random(player, Master.KURADAL1);
					sendEntityDialogue(IS_NPC, "Kuradal", 9085, 9827,
							"Your task is to kill "
							+ player.getTask().getTaskAmount() + " "
							+ player.getTask().getName().toLowerCase()
							+ "s.");
					player.setTalkedWithKuradal();
					//player.sendMessage("2");
					stage = 7;
				} else if (player.getSkills().getLevelForXp(Skills.SLAYER) >= 80) {
				SlayerTask.random(player, Master.KURADAL);
				sendEntityDialogue(IS_NPC, "Kuradal", 9085, 9827,
						"Your task is to kill "
							+ player.getTask().getTaskAmount() + " "
							+ player.getTask().getName().toLowerCase()
							+ "s.");
				player.setTalkedWithKuradal();
				//player.sendMessage("3");
				stage = 7;
				}
			} else {
				sendEntityDialogue(IS_NPC, "Kuradal", 9085, 9827,
						"You're still hunting " + player.getTask().getName().toLowerCase() + "s; come back when you've finished your task.");
				player.setTalkedWithKuradal();
				stage = 7;
			}
			break;
		case 7: /* Offical end of Dialogue */
			end();
			break;
		case 8:
			stage = 9;
			sendEntityDialogue(IS_NPC, "Kuradal", 9085, 9827,
					"What is it, warrior?");
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
				ShopsHandler.openShop(player, 117);
				end();
				break;
			case OPTION_5:
				if (player.getSkills().getLevelForXp(Skills.SLAYER) < 70) {
					player.getPackets().sendGameMessage(
							"You need a slayer level of 70 to access this dungeon.");
					player.getInterfaceManager().closeChatBoxInterface();
					player.getInterfaceManager().closeOverlay(true);
					return;
				}
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(1660,
						5257, 0));
				end();
				break;
			}
			break;
		case 11:
			stage = 13;
			if (player.getTask() == null) {
				sendEntityDialogue(IS_NPC, "Kuradal", 9085, 9827,
						"Very good, you are a true warrior! Would you like a new task?");
			} else {
				sendEntityDialogue(IS_NPC, "Kuradal", 9085, 9827,
						"No you haven't, foolish warrior.");
				stage = 7;
			}
			break;
		case 12:
			stage = 14;
			sendEntityDialogue(IS_NPC, "Kuradal", 9085, 9827,
					"Okay, I can assign a new task for a fee of 200k or 1 Squeal of Fortune spin. If you're 99 slayer, it's free.");
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
						"200k Cash", "One Squeal of Fortune Spin!", "Actually I don't");
			break;
			}
		case 15:
			switch (componentId) {
			case OPTION_1:
				stage = 17;
				sendPlayerDialogue(9827, "Yes, please.");
				break;
			case OPTION_2:
				stage = 24;
				sendPlayerDialogue(9827, "Yes, please.");
				break;
			case OPTION_3:
				stage = 25;
				sendPlayerDialogue(9827, "Yes, please.");
				break;
			case OPTION_4:
				stage = 18;
				sendPlayerDialogue(9827, "No thanks.");
				break;
			}
			break;
		case 16:
			switch (componentId) {
			case OPTION_1:
				stage = 19;
				sendPlayerDialogue(9827, "I'll pay you 200k gold.");
				break;
			case OPTION_2:
				stage = 24;
				sendPlayerDialogue(9827, "I'll trade you one Squeal of Fortune spin.");
				break;
			case OPTION_3:
				stage = 20;
				sendPlayerDialogue(9827, "Nevermind");
				break;
			}
			break;
		case 17:
			if (player.getSkills().getLevelForXp(Skills.SLAYER) < 50) {
				SlayerTask.random(player, Master.KURADALNUB);
				sendEntityDialogue(IS_NPC, "Kuradal", 9085, 9827,
						"Excellent, you're doing great. Your new task is to kill "
							+ player.getTask().getTaskAmount() + " "
							+ player.getTask().getName().toLowerCase()
							+ "s.");
				player.setTalkedWithKuradal();
				//player.sendMessage("1");
				stage = 7;
			} else if (player.getSkills().getLevelForXp(Skills.SLAYER) >= 50 && player.getSkills().getLevelForXp(Skills.SLAYER) < 80) {
				SlayerTask.random(player, Master.KURADAL1);
				sendEntityDialogue(IS_NPC, "Kuradal", 9085, 9827,
						"Excellent, you're doing great. Your new task is to kill "
							+ player.getTask().getTaskAmount() + " "
							+ player.getTask().getName().toLowerCase()
							+ "s.");
				player.setTalkedWithKuradal();
				//player.sendMessage("2");
				stage = 7;
			} else if (player.getSkills().getLevelForXp(Skills.SLAYER) >= 80) {
			SlayerTask.random(player, Master.KURADAL);
			sendEntityDialogue(IS_NPC, "Kuradal", 9085, 9827,
					"Excellent, you're doing great. Your new task is to kill "
							+ player.getTask().getTaskAmount() + " "
							+ player.getTask().getName().toLowerCase()
							+ "s.");
			player.setTalkedWithKuradal();
		//	player.sendMessage("3");
			stage = 7;
			}
			break;
		case 18:
			sendEntityDialogue(IS_NPC, "Kuradal", 9085, 9827,
					"Okay, see you later my warrior.");
			stage = 7;
			break;
		case 19:
			player.refreshMoneyPouch();
			if (player.getMoneyPouch().getCoinAmount() >= 200000) {
				player.coinamount -= World.CommitzoClanZone(player) ? 0 : 200000;
				Settings.GpSyncAmount += World.CommitzoClanZone(player) ? 0 : 200000;
				player.refreshMoneyPouch();
				if (player.getSkills().getLevelForXp(Skills.SLAYER) < 50) {
					SlayerTask.random(player, Master.KURADALNUB);
					sendEntityDialogue(IS_NPC, "Kuradal", 9085, 9827,
							"Excellent, you're doing great. Your new task is to kill "
							+ player.getTask().getTaskAmount() + " "
							+ player.getTask().getName().toLowerCase()
							+ "s.");
					player.setTalkedWithKuradal();
				//	player.sendMessage("1");
					stage = 7;
				} else if (player.getSkills().getLevelForXp(Skills.SLAYER) >= 50 && player.getSkills().getLevelForXp(Skills.SLAYER) < 80) {
					SlayerTask.random(player, Master.KURADAL1);
					sendEntityDialogue(IS_NPC, "Kuradal", 9085, 9827,
							"Excellent, you're doing great. Your new task is to kill "
							+ player.getTask().getTaskAmount() + " "
							+ player.getTask().getName().toLowerCase()
							+ "s.");
					player.setTalkedWithKuradal();
				//	player.sendMessage("2");
					stage = 7;
				} else if (player.getSkills().getLevelForXp(Skills.SLAYER) >= 80) {
				SlayerTask.random(player, Master.KURADAL);
				sendEntityDialogue(IS_NPC, "Kuradal", 9085, 9827,
						"Excellent, you're doing great. Your new task is to kill "
							+ player.getTask().getTaskAmount() + " "
							+ player.getTask().getName().toLowerCase()
							+ "s.");
				player.setTalkedWithKuradal();
				//player.sendMessage("3");
				stage = 7;
				}
			} else {
				sendEntityDialogue(IS_NPC, "Kuradal", 9085, 9827,
						"You don't have 200k gold in your pouch, come back later!");
				stage = 7;
			}
			break;
		case 24:
			if (player.getSpins() > 0) {
				player.spins -= 1;
				if (player.getSkills().getLevelForXp(Skills.SLAYER) < 50) {
					SlayerTask.random(player, Master.KURADALNUB);
					sendEntityDialogue(IS_NPC, "Kuradal", 9085, 9827,
							"Excellent, you're doing great. Your new task is to kill "
							+ player.getTask().getTaskAmount() + " "
							+ player.getTask().getName().toLowerCase()
							+ "s.");
					player.setTalkedWithKuradal();
					stage = 7;
				} else if (player.getSkills().getLevelForXp(Skills.SLAYER) >= 50 && player.getSkills().getLevelForXp(Skills.SLAYER) < 80) {
					SlayerTask.random(player, Master.KURADAL1);
					sendEntityDialogue(IS_NPC, "Kuradal", 9085, 9827,
							"Excellent, you're doing great. Your new task is to kill "
							+ player.getTask().getTaskAmount() + " "
							+ player.getTask().getName().toLowerCase()
							+ "s.");
					player.setTalkedWithKuradal();
					//player.sendMessage("2");
					stage = 7;
				} else if (player.getSkills().getLevelForXp(Skills.SLAYER) >= 80) {
				SlayerTask.random(player, Master.KURADAL);
				sendEntityDialogue(IS_NPC, "Kuradal", 9085, 9827,
						"Excellent, you're doing great. Your new task is to kill "
							+ player.getTask().getTaskAmount() + " "
							+ player.getTask().getName().toLowerCase()
							+ "s.");
				player.setTalkedWithKuradal();
				//player.sendMessage("3");
				stage = 7;
				}
			} else {
				sendEntityDialogue(IS_NPC, "Kuradal", 9085, 9827,
						"You dont have any tickets, come back later!");
				stage = 7;
			}
			break;
		case 25:
			
			if (player.getSkills().getLevelForXp(Skills.SLAYER) > 98) { 
				SlayerTask.random(player, Master.KURADAL);
				sendEntityDialogue(IS_NPC, "Kuradal", 9085, 9827,
						"Excellent, you're doing great. Your new task is to kill "
							+ player.getTask().getTaskAmount() + " "
							+ player.getTask().getName().toLowerCase()
							+ "s.");
				stage = 7;
			} else {
				sendEntityDialogue(IS_NPC, "Kuradal", 9085, 9827,
						"You need 99 slayer to use this.");
				stage = 7;
			}
			break;
		case 20:
			sendEntityDialogue(
					IS_NPC,
					"Kuradal",
					9085,
					9827,
					"Very well.");
			stage = 7;
			break;
		case 21:
			sendEntityDialogue(IS_NPC, "Kuradal", 9085, 9827,
					"You're still hunting " + player.getTask().getName().toLowerCase() + "s; come back when you've finished your task.");
			stage = 7;
			break;
		}
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub

	}

}