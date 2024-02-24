package com.rs.game.player.dialogues.impl;

import com.rs.Settings;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.utils.ShopsHandler;

public class Repair extends Dialogue {

	int npcId;

	@Override
	public void start() {
		sendEntityDialogue(SEND_2_TEXT_CHAT,
			new String[] {
				"I Can Fix it",
				"Hello there!",
				"How can i help you?",
		}, IS_NPC, 4455, 9843);
}
		@Override
	public void run(int interfaceId, int componentId) {
		switch(stage) {
		case -1:
			stage = 0;
			sendOptionsDialogue(SEND_DEFAULT_OPTIONS_TITLE, "Can you fix my armour?", "I would like to see what you have for sale.");
			break;
		case 0:
			if(componentId == OPTION_2) {
				stage = 1;
				sendPlayerDialogue(9827, "I would like to see what you have for sale.");
			}else {
				stage = 2;
				sendPlayerDialogue(9827, "Can you fix my armour?");
			}
			break;
		case 1:
			stage = -2;
			sendEntityDialogue(SEND_1_TEXT_CHAT,
					new String[] { "I Can Fix It",
							"Sure! lets have a look.",
							}, IS_NPC, 4455, 9827);
			ShopsHandler.openShop(player, 7);
			end();
			break;
		case 2:
			stage = 3;
			sendEntityDialogue(SEND_1_TEXT_CHAT,
					new String[] { "I Can Fix It",
							"Yeah i can fix prety much anything, what is it you need fixing?",
							}, IS_NPC, 4455, 9827);
			break;
		case 3:
			stage = 4;
			sendOptionsDialogue(SEND_DEFAULT_OPTIONS_TITLE, "Zaros armour.", "Pvp armour/weapons.", "Level 90 Equipment", "Other stuff");
			break;
		case 4:
			if(componentId == OPTION_1) {
				if(player.getInventory().containsItem(20138, 1) || player.getInventory().containsItem(24982, 1) || player.getInventory().containsItem(24988, 1) || player.getInventory().containsItem(24976, 1) || player.getInventory().containsItem(24991, 1) || player.getInventory().containsItem(24979, 1) || player.getInventory().containsItem(24985, 1) || player.getInventory().containsItem(20174, 1) || player.getInventory().containsItem(20142, 1) || player.getInventory().containsItem(20146, 1) || player.getInventory().containsItem(20150, 1) || player.getInventory().containsItem(20154, 1) || player.getInventory().containsItem(20158, 1) || player.getInventory().containsItem(20162, 1) || player.getInventory().containsItem(20166, 1) || player.getInventory().containsItem(20170, 1)) {
				stage = 5;
				sendPlayerDialogue(9827, "I have some ancient equipment that needs fixing, can you do it?");
				}else{
				stage = -2;
				sendPlayerDialogue(9827, "I don't seem to have the equipment on me at the moment. I'll come back once i have it.");
				}
			}else if(componentId == OPTION_2) {
				if(player.getInventory().containsItem(13889, 1) || player.getInventory().containsItem(13895, 1) || player.getInventory().containsItem(13901, 1) || player.getInventory().containsItem(13907, 1) || player.getInventory().containsItem(13860, 1) || player.getInventory().containsItem(13863, 1) || player.getInventory().containsItem(13866, 1) || player.getInventory().containsItem(13869, 1) || player.getInventory().containsItem(13872, 1) || player.getInventory().containsItem(13875, 1) || player.getInventory().containsItem(13878, 1) || player.getInventory().containsItem(13886, 1) || player.getInventory().containsItem(13892, 1) || player.getInventory().containsItem(13895, 1) || player.getInventory().containsItem(13898, 1) || player.getInventory().containsItem(13904, 1)) {
				stage = 9;
				sendPlayerDialogue(9827, "I have some equipment that needs fixing, can you do it?");
				}else{
				stage = -2;
				sendPlayerDialogue(9827, "I don't seem to have the equipment on me at the moment. I'll come back once i have it.");
				}
			}else if(componentId == OPTION_3) {
				if(player.getInventory().containsItem(29741, 1) || player.getInventory().containsItem(29144, 1) || player.getInventory().containsItem(29742, 1) || player.getInventory().containsItem(29743, 1) || player.getInventory().containsItem(29744, 1) || player.getInventory().containsItem(29745, 1) || player.getInventory().containsItem(29746, 1) || player.getInventory().containsItem(29747, 1) || player.getInventory().containsItem(29748, 1)) {
				stage = 13;
				sendPlayerDialogue(9827, "I have some equipment that needs fixing, can you do it?");
				}else{
				stage = -2;
				sendPlayerDialogue(9827, "I don't seem to have the equipment on me at the moment. I'll come back once i have it.");
				}
			}else if(componentId == OPTION_4) {
				if (player.getInventory().containsItem(29725, 1) || player.getInventory().containsItem(29722, 1) || player.getInventory().containsItem(29266, 1) || player.getInventory().containsItem(29250, 1) || player.getInventory().containsItem(29729, 1)) {
				stage = 13;
				sendPlayerDialogue(9827, "I have some equipment that needs fixing, can you do it?");
				}else{
				stage = -2;
				sendPlayerDialogue(9827, "I don't seem to have the equipment on me at the moment. I'll come back once i have it.");
				}
			break;
			}
			break;
			
		case 5:
			stage = 6;
					sendEntityDialogue(SEND_1_TEXT_CHAT,
					new String[] { "I Can Fix It",
							"This looks tricky, but i'm sure i can fix it. But it will cost you 10m a peice.",
							}, IS_NPC, 4455, 9827);
							break;
		case 6:
			stage = 7;
			sendOptionsDialogue(SEND_DEFAULT_OPTIONS_TITLE, "Sure no problem.", "That is way too much!");
			break;
		case 7:
			if(componentId == OPTION_1) {
				if (player.getInventory().containsItem(995, player.hustlerperk ? 5000000 : 10000000)) {
				stage = 8;
				sendPlayerDialogue(9827, "Sure no problem!");
				}else{
				stage = -2;
				sendPlayerDialogue(9827, "I don't seem to have any money on me at the moment i will be back once i get some.");
				}
			}else if(componentId == OPTION_2) {
				stage = -2;
				sendPlayerDialogue(9827, "That is way too much! No thank you.");
			}
			break;
		case 8:
			sendEntityDialogue(SEND_1_TEXT_CHAT,
					new String[] { "I Can Fix It",
							"There you go! As good as new.",
							}, IS_NPC, 4455, 9827);
							stage = -2;
			if (player.getInventory().containsItem(20174, 1) && player.getInventory().containsItem(995, player.hustlerperk ? 5000000 : 10000000)) {
				player.getInventory().deleteItem(995, player.hustlerperk ? 5000000 : 10000000);
				Settings.GpSyncAmount += player.hustlerperk ? 5000000 : 10000000;
     						    player.getInventory().deleteItem(20174, 1);
         						player.getInventory().addItem(20171, 1);
			} else if (player.getInventory().containsItem(20138, 1) && player.getInventory().containsItem(995, player.hustlerperk ? 5000000 : 10000000)) {
				player.getInventory().deleteItem(995, player.hustlerperk ? 5000000 : 10000000);
				Settings.GpSyncAmount += player.hustlerperk ? 5000000 : 10000000;
     						    player.getInventory().deleteItem(20138, 1);
         						player.getInventory().addItem(20135, 1);
			} else if (player.getInventory().containsItem(20142, 1) && player.getInventory().containsItem(995, player.hustlerperk ? 5000000 : 10000000)) {
				player.getInventory().deleteItem(995, player.hustlerperk ? 5000000 : 10000000);
				Settings.GpSyncAmount += player.hustlerperk ? 5000000 : 10000000;
     						    player.getInventory().deleteItem(20142, 1);
         						player.getInventory().addItem(20139, 1);
			} else if (player.getInventory().containsItem(20146, 1) && player.getInventory().containsItem(995, player.hustlerperk ? 5000000 : 10000000)) {
				player.getInventory().deleteItem(995, player.hustlerperk ? 5000000 : 10000000);
				Settings.GpSyncAmount += player.hustlerperk ? 5000000 : 10000000;
     						    player.getInventory().deleteItem(20146, 1);
         						player.getInventory().addItem(20143, 1);
			} else if (player.getInventory().containsItem(20150, 1) && player.getInventory().containsItem(995, player.hustlerperk ? 5000000 : 10000000)) {
				player.getInventory().deleteItem(995, player.hustlerperk ? 5000000 : 10000000);
				Settings.GpSyncAmount += player.hustlerperk ? 5000000 : 10000000;
     						    player.getInventory().deleteItem(20150, 1);
         						player.getInventory().addItem(20147, 1);
			} else if (player.getInventory().containsItem(20154, 1) && player.getInventory().containsItem(995, player.hustlerperk ? 5000000 : 10000000)) {
				player.getInventory().deleteItem(995, player.hustlerperk ? 5000000 : 10000000);
				Settings.GpSyncAmount += player.hustlerperk ? 5000000 : 10000000;
     						    player.getInventory().deleteItem(20154, 1);
         						player.getInventory().addItem(20151, 1);
			} else if (player.getInventory().containsItem(20158, 1) && player.getInventory().containsItem(995, player.hustlerperk ? 5000000 : 10000000)) {
				player.getInventory().deleteItem(995, player.hustlerperk ? 5000000 : 10000000);
				Settings.GpSyncAmount += player.hustlerperk ? 5000000 : 10000000;
     						    player.getInventory().deleteItem(20158, 1);
         						player.getInventory().addItem(20155, 1);
			} else if (player.getInventory().containsItem(20162, 1) && player.getInventory().containsItem(995, player.hustlerperk ? 5000000 : 10000000)) {
				player.getInventory().deleteItem(995, player.hustlerperk ? 5000000 : 10000000);
				Settings.GpSyncAmount += player.hustlerperk ? 5000000 : 10000000;
     						    player.getInventory().deleteItem(20162, 1);
         						player.getInventory().addItem(20159, 1);
			} else if (player.getInventory().containsItem(20166, 1) && player.getInventory().containsItem(995, player.hustlerperk ? 5000000 : 10000000)) {
				player.getInventory().deleteItem(995, player.hustlerperk ? 5000000 : 10000000);
				Settings.GpSyncAmount += player.hustlerperk ? 5000000 : 10000000;
     						    player.getInventory().deleteItem(20166, 1);
         						player.getInventory().addItem(20163, 1);
			} else if (player.getInventory().containsItem(20173, 1) && player.getInventory().containsItem(995, player.hustlerperk ? 5000000 : 10000000)) {
				player.getInventory().deleteItem(995, player.hustlerperk ? 5000000 : 10000000);
				Settings.GpSyncAmount += player.hustlerperk ? 5000000 : 10000000;
     						    player.getInventory().deleteItem(20173, 1);
         						player.getInventory().addItem(20171, 1);
			} else if (player.getInventory().containsItem(20170, 1) && player.getInventory().containsItem(995, player.hustlerperk ? 5000000 : 10000000)) {
				player.getInventory().deleteItem(995, player.hustlerperk ? 5000000 : 10000000);
				Settings.GpSyncAmount += player.hustlerperk ? 5000000 : 10000000;
     						    player.getInventory().deleteItem(20170, 1);
         						player.getInventory().addItem(20167, 1);
			} else if (player.getInventory().containsItem(24982, 1) && player.getInventory().containsItem(995, player.hustlerperk ? 5000000 : 10000000)) {
				player.getInventory().deleteItem(995, player.hustlerperk ? 5000000 : 10000000);
				Settings.GpSyncAmount += player.hustlerperk ? 5000000 : 10000000;
     						    player.getInventory().deleteItem(24982, 1);
         						player.getInventory().addItem(24980, 1);
			} else if (player.getInventory().containsItem(24988, 1) && player.getInventory().containsItem(995, player.hustlerperk ? 5000000 : 10000000)) {
				player.getInventory().deleteItem(995, player.hustlerperk ? 5000000 : 10000000);
				Settings.GpSyncAmount += player.hustlerperk ? 5000000 : 10000000;
     						    player.getInventory().deleteItem(24988, 1);
         						player.getInventory().addItem(24986, 1);
			} else if (player.getInventory().containsItem(24991, 1) && player.getInventory().containsItem(995, player.hustlerperk ? 5000000 : 10000000)) {
				player.getInventory().deleteItem(995, player.hustlerperk ? 5000000 : 10000000);
				Settings.GpSyncAmount += player.hustlerperk ? 5000000 : 10000000;
     						    player.getInventory().deleteItem(24991, 1);
         						player.getInventory().addItem(24989, 1);
			} else if (player.getInventory().containsItem(24976, 1) && player.getInventory().containsItem(995, player.hustlerperk ? 5000000 : 10000000)) {
				player.getInventory().deleteItem(995, player.hustlerperk ? 5000000 : 10000000);
				Settings.GpSyncAmount += player.hustlerperk ? 5000000 : 10000000;
     						    player.getInventory().deleteItem(24976, 1);
         						player.getInventory().addItem(24974, 1);
			} else if (player.getInventory().containsItem(24985, 1) && player.getInventory().containsItem(995, player.hustlerperk ? 5000000 : 10000000)) {
				player.getInventory().deleteItem(995, player.hustlerperk ? 5000000 : 10000000);
				Settings.GpSyncAmount += player.hustlerperk ? 5000000 : 10000000;
     						    player.getInventory().deleteItem(24985, 1);
         						player.getInventory().addItem(24983, 1);
			} else if (player.getInventory().containsItem(24979, 1) && player.getInventory().containsItem(995, player.hustlerperk ? 5000000 : 10000000)) {
				player.getInventory().deleteItem(995, player.hustlerperk ? 5000000 : 10000000);
				Settings.GpSyncAmount += player.hustlerperk ? 5000000 : 10000000;
     						    player.getInventory().deleteItem(24979, 1);
         						player.getInventory().addItem(24977, 1);
         						
							}
							
			break;
		case 9:
			stage = 10;
					sendEntityDialogue(SEND_1_TEXT_CHAT,
					new String[] { "I Can Fix Itr",
							"I fix these almost everyday. But it will cost you 10m a peice.",
							}, IS_NPC, 4455, 9827);
							break;
		case 13:
			stage = 14;
					sendEntityDialogue(SEND_1_TEXT_CHAT,
					new String[] { "I Can Fix Itr",
							"I fix these almost everyday. But it will cost you 35m per armour item, 10M per jewelry, 50M for t92 jewelry.",
							}, IS_NPC, 4455, 9827);
							break;
		case 10:
			stage = 11;
			sendOptionsDialogue(SEND_DEFAULT_OPTIONS_TITLE, "Sure no problem.", "That is way too much!");
			break;
		case 14:
			stage = 15;
			sendOptionsDialogue(SEND_DEFAULT_OPTIONS_TITLE, "Sure no problem.", "That is way too much!");
			break;
		case 11:
			if(componentId == OPTION_1) {
				if (player.getInventory().containsItem(995, player.hustlerperk ? 5000000 : 10000000)) {
				stage = 12;
				sendPlayerDialogue(9827, "Sure no problem!");
				}else{
				stage = -2;
				sendPlayerDialogue(9827, "I don't seem to have any money on me at the moment i will be back once i get some.");
				}
			}else if(componentId == OPTION_2) {
				stage = -2;
				sendPlayerDialogue(9827, "That is way too much! No thank you.");
			}
			break;
		case 15:
			if(componentId == OPTION_1) {
				if (player.getInventory().containsItem(995, player.hustlerperk ? 5000000 : 10000000)) {
				stage = 16;
				sendPlayerDialogue(9827, "Sure no problem!");
				}else{
				stage = -2;
				sendPlayerDialogue(9827, "I don't seem to have any money on me at the moment i will be back once i get some.");
				}
			}else if(componentId == OPTION_2) {
				stage = -2;
				sendPlayerDialogue(9827, "That is way too much! No thank you.");
			}
			break;
		case 12:
			sendEntityDialogue(SEND_1_TEXT_CHAT,
					new String[] { "I Can Fix It",
							"There you go! As good as new.",
							}, IS_NPC, 4455, 9827);
							stage = -2;
			if (player.getInventory().containsItem(13889, 1) && player.getInventory().containsItem(995, player.hustlerperk ? 5000000 : 10000000)) {
				player.getInventory().deleteItem(995, player.hustlerperk ? 5000000 : 10000000);
				Settings.GpSyncAmount += player.hustlerperk ? 5000000 : 10000000;
     						    player.getInventory().deleteItem(13889, 1);
         						player.getInventory().addItem(13887, 1);
			} else if (player.getInventory().containsItem(13895, 1) && player.getInventory().containsItem(995, player.hustlerperk ? 5000000 : 10000000)) {
				player.getInventory().deleteItem(995, player.hustlerperk ? 5000000 : 10000000);
				Settings.GpSyncAmount += player.hustlerperk ? 5000000 : 10000000;
     						    player.getInventory().deleteItem(13895, 1);
         						player.getInventory().addItem(13893, 1);
			} else if (player.getInventory().containsItem(13901, 1) && player.getInventory().containsItem(995, player.hustlerperk ? 5000000 : 10000000)) {
				player.getInventory().deleteItem(995, player.hustlerperk ? 5000000 : 10000000);
				Settings.GpSyncAmount += player.hustlerperk ? 5000000 : 10000000;
     						    player.getInventory().deleteItem(13901, 1);
         						player.getInventory().addItem(13899, 1);
			} else if (player.getInventory().containsItem(13907, 1) && player.getInventory().containsItem(995, player.hustlerperk ? 5000000 : 10000000)) {
				player.getInventory().deleteItem(995, player.hustlerperk ? 5000000 : 10000000);
				Settings.GpSyncAmount += player.hustlerperk ? 5000000 : 10000000;
     						    player.getInventory().deleteItem(13907, 1);
         						player.getInventory().addItem(13905, 1);
			} else if (player.getInventory().containsItem(13886, 1) && player.getInventory().containsItem(995, player.hustlerperk ? 5000000 : 10000000)) {
				player.getInventory().deleteItem(995, player.hustlerperk ? 5000000 : 10000000);
				Settings.GpSyncAmount += player.hustlerperk ? 5000000 : 10000000;
     						    player.getInventory().deleteItem(13886, 1);
         						player.getInventory().addItem(13884, 1);
			} else if (player.getInventory().containsItem(13892, 1) && player.getInventory().containsItem(995, player.hustlerperk ? 5000000 : 10000000)) {
				player.getInventory().deleteItem(995, player.hustlerperk ? 5000000 : 10000000);
				Settings.GpSyncAmount += player.hustlerperk ? 5000000 : 10000000;
     						    player.getInventory().deleteItem(13892, 1);
         						player.getInventory().addItem(13890, 1);
			} else if (player.getInventory().containsItem(13898, 1) && player.getInventory().containsItem(995, player.hustlerperk ? 5000000 : 10000000)) {
				player.getInventory().deleteItem(995, player.hustlerperk ? 5000000 : 10000000);
				Settings.GpSyncAmount += player.hustlerperk ? 5000000 : 10000000;
     						    player.getInventory().deleteItem(13898, 1);
         						player.getInventory().addItem(13896, 1);
			} else if (player.getInventory().containsItem(13904, 1) && player.getInventory().containsItem(995, player.hustlerperk ? 5000000 : 10000000)) {
				player.getInventory().deleteItem(995, player.hustlerperk ? 5000000 : 10000000);
				Settings.GpSyncAmount += player.hustlerperk ? 5000000 : 10000000;
     						    player.getInventory().deleteItem(13904, 1);
         						player.getInventory().addItem(13902, 1);
			} else if (player.getInventory().containsItem(13860, 1) && player.getInventory().containsItem(995, player.hustlerperk ? 5000000 : 10000000)) {
				player.getInventory().deleteItem(995, player.hustlerperk ? 5000000 : 10000000);
				Settings.GpSyncAmount += player.hustlerperk ? 5000000 : 10000000;
     						    player.getInventory().deleteItem(13860, 1);
         						player.getInventory().addItem(13858, 1);
			} else if (player.getInventory().containsItem(13863, 1) && player.getInventory().containsItem(995, player.hustlerperk ? 5000000 : 10000000)) {
				player.getInventory().deleteItem(995, player.hustlerperk ? 5000000 : 10000000);
				Settings.GpSyncAmount += player.hustlerperk ? 5000000 : 10000000;
     						    player.getInventory().deleteItem(13863, 1);
         						player.getInventory().addItem(13861, 1);
			} else if (player.getInventory().containsItem(13866, 1) && player.getInventory().containsItem(995, player.hustlerperk ? 5000000 : 10000000)) {
				player.getInventory().deleteItem(995, player.hustlerperk ? 5000000 : 10000000);
				Settings.GpSyncAmount += player.hustlerperk ? 5000000 : 10000000;
     						    player.getInventory().deleteItem(13866, 1);
         						player.getInventory().addItem(13864, 1);
			} else if (player.getInventory().containsItem(13869, 1) && player.getInventory().containsItem(995, player.hustlerperk ? 5000000 : 10000000)) {
				player.getInventory().deleteItem(995, player.hustlerperk ? 5000000 : 10000000);
				Settings.GpSyncAmount += player.hustlerperk ? 5000000 : 10000000;
     						    player.getInventory().deleteItem(13869, 1);
         						player.getInventory().addItem(13867, 1);
			} else if (player.getInventory().containsItem(13872, 1) && player.getInventory().containsItem(995, player.hustlerperk ? 5000000 : 10000000)) {
				player.getInventory().deleteItem(995, player.hustlerperk ? 5000000 : 10000000);
				Settings.GpSyncAmount += player.hustlerperk ? 5000000 : 10000000;
     						    player.getInventory().deleteItem(13872, 1);
         						player.getInventory().addItem(13870, 1);
			} else if (player.getInventory().containsItem(13875, 1) && player.getInventory().containsItem(995, player.hustlerperk ? 5000000 : 10000000)) {
				player.getInventory().deleteItem(995, player.hustlerperk ? 5000000 : 10000000);
				Settings.GpSyncAmount += player.hustlerperk ? 5000000 : 10000000;
     						    player.getInventory().deleteItem(13875, 1);
         						player.getInventory().addItem(13873, 1);
			} else if (player.getInventory().containsItem(13878, 1) && player.getInventory().containsItem(995, player.hustlerperk ? 5000000 : 10000000)) {
				player.getInventory().deleteItem(995, player.hustlerperk ? 5000000 : 10000000);
				Settings.GpSyncAmount += player.hustlerperk ? 5000000 : 10000000;
     						    player.getInventory().deleteItem(13878, 1);
         						player.getInventory().addItem(13876, 1);
							}
		case 16:
			sendEntityDialogue(SEND_1_TEXT_CHAT,
					new String[] { "I Can Fix It",
							"There you go! As good as new.",
							}, IS_NPC, 4455, 9827);
							stage = -2;
			if (player.getInventory().containsItem(29741, 1) && player.getInventory().containsItem(995, player.hustlerperk ? 17500000 : 35000000)) {
				player.getInventory().deleteItem(995, player.hustlerperk ? 17500000 : 35000000);
				Settings.GpSyncAmount += player.hustlerperk ? 17500000 : 35000000;
     						    player.getInventory().deleteItem(29741, 1);
         						player.getInventory().addItem(29939, 1);
			} else if (player.getInventory().containsItem(29742, 1) && player.getInventory().containsItem(995, player.hustlerperk ? 17500000 : 35000000)) {
				player.getInventory().deleteItem(995, player.hustlerperk ? 17500000 : 35000000);
				Settings.GpSyncAmount += player.hustlerperk ? 17500000 : 35000000;
     						    player.getInventory().deleteItem(29742, 1);
         						player.getInventory().addItem(29941, 1);
			} else if (player.getInventory().containsItem(29144, 1) && player.getInventory().containsItem(995, player.hustlerperk ? 17500000 : 35000000)) {
				player.getInventory().deleteItem(995, player.hustlerperk ? 17500000 : 35000000);
				Settings.GpSyncAmount += player.hustlerperk ? 17500000 : 35000000;
     						    player.getInventory().deleteItem(29144, 1);
         						player.getInventory().addItem(29940, 1);
			} else if (player.getInventory().containsItem(29743, 1) && player.getInventory().containsItem(995, player.hustlerperk ? 17500000 : 35000000)) {
				player.getInventory().deleteItem(995, player.hustlerperk ? 17500000 : 35000000);
				Settings.GpSyncAmount += player.hustlerperk ? 17500000 : 35000000;
     						    player.getInventory().deleteItem(29743, 1);
         						player.getInventory().addItem(29845, 1);
			} else if (player.getInventory().containsItem(29744, 1) && player.getInventory().containsItem(995, player.hustlerperk ? 17500000 : 35000000)) {
				player.getInventory().deleteItem(995, player.hustlerperk ? 17500000 : 35000000);
				Settings.GpSyncAmount += player.hustlerperk ? 17500000 : 35000000;
     						    player.getInventory().deleteItem(29744, 1);
         						player.getInventory().addItem(29844, 1);
			} else if (player.getInventory().containsItem(29745, 1) && player.getInventory().containsItem(995, player.hustlerperk ? 17500000 : 35000000)) {
				player.getInventory().deleteItem(995, player.hustlerperk ? 17500000 : 35000000);
				Settings.GpSyncAmount += player.hustlerperk ? 17500000 : 35000000;
     						    player.getInventory().deleteItem(29745, 1);
         						player.getInventory().addItem(29846, 1);
			} else if (player.getInventory().containsItem(29746, 1) && player.getInventory().containsItem(995, player.hustlerperk ? 17500000 : 35000000)) {
				player.getInventory().deleteItem(995, player.hustlerperk ? 17500000 : 35000000);
				Settings.GpSyncAmount += player.hustlerperk ? 17500000 : 35000000;
     						    player.getInventory().deleteItem(29746, 1);
         						player.getInventory().addItem(29888, 1);
			} else if (player.getInventory().containsItem(29747, 1) && player.getInventory().containsItem(995, player.hustlerperk ? 17500000 : 35000000)) {
				player.getInventory().deleteItem(995, player.hustlerperk ? 17500000 : 35000000);
				Settings.GpSyncAmount += player.hustlerperk ? 17500000 : 35000000;
     						    player.getInventory().deleteItem(29747, 1);
         						player.getInventory().addItem(29889, 1);
			} else if (player.getInventory().containsItem(29748, 1) && player.getInventory().containsItem(995, player.hustlerperk ? 17500000 : 35000000)) {
				player.getInventory().deleteItem(995, player.hustlerperk ? 17500000 : 35000000);
				Settings.GpSyncAmount += player.hustlerperk ? 17500000 : 35000000;
     						    player.getInventory().deleteItem(29748, 1);
         						player.getInventory().addItem(29890, 1);
			} else if (player.getInventory().containsItem(29722, 1) && player.getInventory().containsItem(995, player.hustlerperk ? 5000000 : 10000000)) {
				player.getInventory().deleteItem(995, player.hustlerperk ? 5000000 : 10000000);
				Settings.GpSyncAmount += player.hustlerperk ? 5000000 : 10000000;
     						    player.getInventory().deleteItem(29722, 1);
         						player.getInventory().addItem(29724, 1);
			} else if (player.getInventory().containsItem(29729, 1) && player.getInventory().containsItem(995, player.hustlerperk ? 5000000 : 10000000)) {
				player.getInventory().deleteItem(995, player.hustlerperk ? 5000000 : 10000000);
				Settings.GpSyncAmount += player.hustlerperk ? 5000000 : 10000000;
     						    player.getInventory().deleteItem(29729, 1);
         						player.getInventory().addItem(29731, 1);
			} else if (player.getInventory().containsItem(29725, 1) && player.getInventory().containsItem(995, player.hustlerperk ? 5000000 : 10000000)) {
				player.getInventory().deleteItem(995, player.hustlerperk ? 5000000 : 10000000);
				Settings.GpSyncAmount += player.hustlerperk ? 5000000 : 10000000;
     						    player.getInventory().deleteItem(29725, 1);
         						player.getInventory().addItem(29727, 1);
			} else if (player.getInventory().containsItem(29266, 1) && player.getInventory().containsItem(995, player.hustlerperk ? 25000000 : 50000000)) {
				player.getInventory().deleteItem(995, player.hustlerperk ? 25000000 : 50000000);
				Settings.GpSyncAmount += player.hustlerperk ? 25000000 : 50000000;
				player.getInventory().deleteItem(29266, 1);
				player.getInventory().addItem(29268, 1);
			} else if (player.getInventory().containsItem(29250, 1) && player.getInventory().containsItem(995, player.hustlerperk ? 25000000 : 50000000)) {
				player.getInventory().deleteItem(995, player.hustlerperk ? 25000000 : 50000000);
				Settings.GpSyncAmount += player.hustlerperk ? 25000000 : 50000000;
				player.getInventory().deleteItem(29250, 1);
				player.getInventory().addItem(29252, 1);
							}
							
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