package com.rs.game.player.dialogues.impl;

import com.rs.Settings;
import com.rs.game.World;
import com.rs.game.player.Skills;
import com.rs.game.player.SlayerTask;
import com.rs.game.player.SlayerTask.Master;
import com.rs.game.player.dialogues.Dialogue;

public class BossSlayer extends Dialogue {

	int npcId;

	@Override
	public void start() {
		if (!player.isTalkedWithKuradal()) {
			sendEntityDialogue(IS_NPC, "Death", 14387, 9827,
					"Hello warrior, What can i do for you?");
		} else {
			sendEntityDialogue(IS_NPC, "Death", 14387, 9827,
					"You're back my soul... What do you want now?");
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
						"I would like to ask something about my Task.");
			}
			break;
		case 0:
			stage = 1;
			sendEntityDialogue(
					IS_NPC,
					"Death",
					14387,
					9827,
					"Im the boss slayer master of Harmony! I give out slayer tasks to players around the world of Harmony that have 99 slayer.");
			break;
		case 1:
			stage = 2;
			sendPlayerDialogue(9827, "Okay, give me a task then Death!");
			break;
		case 2:
			stage = 3;
			sendEntityDialogue(IS_NPC, "Death", 14387, 9827,
					"My name is common around this earth, i see.");
			break;
		case 3:
			if (player.getTask() == null) {
				SlayerTask.random(player, Master.DEATH);
				sendEntityDialogue(IS_NPC, "Death", 14387, 9827,
						"Your task is to kill "
								+ player.getTask().getTaskAmount() + " "
								+ player.getTask().getName().toLowerCase()
								+ "s..");
				player.setTalkedWithKuradal();
				stage = 7;
			} else {
				sendEntityDialogue(IS_NPC, "Death", 14387, 9827,
						"You forgot? You already have a slayertask from me! ",
						"Your task is to kill "
								+ player.getTask().getTaskAmount() + " "
								+ player.getTask().getName().toLowerCase()
								+ "s.");
				player.setTalkedWithKuradal();
				stage = 4;
			}
			break;
		case 4:
			stage = 5;
			sendPlayerDialogue(9827, "Can you show me where i can kill these?");
			break;
		case 5:
			stage = 6;
			sendEntityDialogue(IS_NPC, "Death", 14387, 9827,
					"Sorry i dont have time today but maybe next time.");
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
			sendEntityDialogue(IS_NPC, "Death", 14387, 9827,
					"Okay, what can i do for you?");
			break;
		case 9:
			stage = 10;
			sendOptionsDialogue(SEND_DEFAULT_OPTIONS_TITLE,
					"I have finished my Task!", "Can you change my Task?",
					"I dont remember my Task.", "Open the slayer shop.");
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
						"I would like to have a new task from you...");
				break;
			case OPTION_3:
				stage = 21;
				sendPlayerDialogue(9827,
						"Ermmm, i dont really remember the task you gave me.");
				break;
			case OPTION_4:
				player.getInterfaceManager().sendInterface(164);
				player.getInterfaceManager().sendSlayerTab();
				end();
				break;
			}
			break;
		case 11:
			stage = 13;
			if (player.getTask() == null) {
				sendEntityDialogue(IS_NPC, "Death", 14387, 9827,
						"Very good, you are a true warrior! Would you like to have a new task?");
			} else {
				sendEntityDialogue(IS_NPC, "Death", 14387, 9827,
						"No you haven't foolish warrior.");
				stage = 7;
			}
			break;
		case 12:
			stage = 14;
			sendEntityDialogue(IS_NPC, "Death", 14387, 9827,
					"Okay, i can assign a new task for a fee of 500k or 1 skip ticket.");
			break;
		case 13:
			stage = 15;
			sendOptionsDialogue(SEND_DEFAULT_OPTIONS_TITLE, "Yes", "No thanks");
			break;
		case 14:
			stage = 16;
			sendOptionsDialogue("How do you wish to pay for your task skip.",
					"500K Cash", "One Skip Ticket!", "Actually I don't");
			break;
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
				stage = 24;
				sendPlayerDialogue(9827, "I'll trade you one spin ticket.");
				break;
			case OPTION_3:
				stage = 20;
				sendPlayerDialogue(9827, "No thanks");
				break;
			}
			break;
		case 17:
			SlayerTask.random(player, Master.DEATH);
			sendEntityDialogue(IS_NPC, "Death", 14387, 9827,
					"Your new slayertask is to kill "
							+ player.getTask().getTaskAmount() + " "
							+ player.getTask().getName().toLowerCase() + "s..");
			stage = 7;
			break;
		case 18:
			sendEntityDialogue(IS_NPC, "Death", 14387, 9827,
					"Okay, see you later my warrior.");
			stage = 7;
			break;
		case 19:
			if (player.getMoneyPouch().getCoinAmount() >= 500000) {
				player.coinamount -= World.CommitzoClanZone(player) ? 0 : 500000;
				Settings.GpSyncAmount += World.CommitzoClanZone(player) ? 0 : 500000;
				player.refreshMoneyPouch();
				SlayerTask.random(player, Master.DEATH);
				sendEntityDialogue(IS_NPC, "Death", 14387, 9827,
						"Your new slayertask is to kill "
								+ player.getTask().getTaskAmount() + " "
								+ player.getTask().getName().toLowerCase()
								+ "s..");
				stage = 7;
			} else {
				sendEntityDialogue(IS_NPC, "Death", 14387, 9827,
						"You dont have 500k gold, come back later!");
				stage = 7;
			}
			break;
		case 24:
			if (player.getInventory().containsItem(13663, 1)) {
				player.getInventory().deleteItem(13663, 1);
				SlayerTask.random(player, Master.DEATH);
				sendEntityDialogue(IS_NPC, "Death", 14387, 9827,
						"Your new slayertask is to kill "
								+ player.getTask().getTaskAmount() + " "
								+ player.getTask().getName().toLowerCase()
								+ "s..");
				stage = 7;
			} else {
				sendEntityDialogue(IS_NPC, "Death", 14387, 9827,
						"You dont have any tickets, come back later!");
				stage = 7;
			}
			break;
		case 25:
			if (player.getSkills().getLevelForXp(Skills.SLAYER) > 98) {
				SlayerTask.random(player, Master.DEATH);
				sendEntityDialogue(IS_NPC, "Death", 14387, 9827,
						"Your new slayertask is to kill "
								+ player.getTask().getTaskAmount() + " "
								+ player.getTask().getName().toLowerCase()
								+ "s..");
				stage = 7;
			} else {
				sendEntityDialogue(IS_NPC, "Death", 14387, 9827,
						"You need 99 slayer to use this.");
				stage = 7;
			}
			break;
		case 20:
			sendEntityDialogue(
					IS_NPC,
					"Death",
					14387,
					9827,
					"Im the only Boss Slayermaster in Harmony and i think 500k is a good price but okay.");
			stage = 7;
			break;
		case 21:
			sendEntityDialogue(IS_NPC, "Death", 14387, 9827,
					"Lmao are you serious, Okay your task is to kill....");
			stage = 22;
			break;
		case 22:
			sendEntityDialogue(IS_NPC, "Death", 14387, 9827,
					"Your slayertask is to kill "
							+ player.getTask().getTaskAmount() + " "
							+ player.getTask().getName().toLowerCase()
							+ "s.. Please remember your task next time.");
			stage = 23;
			break;
		case 23:
			sendPlayerDialogue(9827,
					"Sorry Death, I will remember my task...");
			stage = 7;
			break;
		}
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub

	}

}