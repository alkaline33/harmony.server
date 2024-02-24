package com.rs.game.player.dialogues.impl;

import com.rs.game.player.content.PlayerLook;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.utils.Colors;

public class XpRates extends Dialogue {

	int npcId = 945;
	public int starter = 0;
	

	@Override
	public void start() {
		if (player.iseasy == true || player.isaverage == true || player.ishard == true || player.isheroic == true || player.isrealism == true) {
		//  Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(Settings.RESPAWN_PLAYER_LOCATION));
		  player.getDialogueManager().startDialogue("SimpleMessage", "You have already set your xp rates on this account.");
			
		} else {
		//npcId = (Integer) parameters[0];
		sendNPCDialogue(npcId, 9827, "Alright "+player.getDisplayName()+", Let's get your game mode set.");
			}
		}

	@Override
	public void run(int interfaceId, int componentId) {
		switch(stage) {
		case -1:
			stage = 20;
			sendOptionsDialogue("Game mode", "Regular mode",
					"Ironman mode", "Hardcore Ironman mode (1 life)", "Duo Ironman mode");
			break;
		case 20:
			if (componentId == OPTION_1) {
				stage = 21;
				sendPlayerDialogue(9827, "I'd like to play the way this game was originally made.");
		} else if(componentId == OPTION_2) {
				stage = 22;
				sendPlayerDialogue(9827, "I'd like to play the way as an ironman without player interaction..");
			} else if (componentId == OPTION_3) {
				stage = 40;
				sendPlayerDialogue(9827, "I'd like to play the way as an ironman without player interaction and 1 life!");
			}else if(componentId == OPTION_4) {
				stage = 22;
				player.ironmanduo = true;
				sendPlayerDialogue(9827, "I'd like to play as an ironman, but have a partner to trade.");
		}
			break;
		case 21:
			stage = 23;
			sendNPCDialogue(npcId, 9827, "Okay. Now please choose your xp rate.");
			break;
		case 40:
			stage = 23;
			player.ironman = true;
			player.hcironman = true;
			sendNPCDialogue(npcId, 9827, "If you die, you will lose your hardcore status & become a regular ironman!");
			break;
		case 22:
			stage = 23;
			player.ironman = true;
			if (!player.ironmanduo) {
				player.getAppearence().setTitle(9861);
				} else {
					player.getAppearence().setTitle(9862);
					player.sendMessage(Colors.red+"Right click a player to request partnership. ::acceptpartner to complete & ::mypartner to see who your partner is.");
				}
			sendNPCDialogue(npcId, 9827, "Okay. Please note this means NO player interaction, it's all up to you. Goodluck on xp.");
			break;
		case 23:
			stage = 0;
			sendOptionsDialogue(SEND_DEFAULT_OPTIONS_TITLE, "Heroic XP (+6% Drop rate, 3X xp)",  "Hardcore XP (+3% Drop rate, 25X xp)", "Normal XP (70X xp)", "Fast XP (125X xp)");
			break;
		case 0:
			if (componentId == OPTION_1) {
				stage = 11;
				sendPlayerDialogue(9827, "I'm the heroic kind! Hit me the grind!");
		} else if(componentId == OPTION_2) {
				stage = 1;
				sendPlayerDialogue(9827, "Let's go hardcore! Slow xp, please!");
			} else if(componentId == OPTION_3) {
				stage = 2;
				sendPlayerDialogue(9827, "Let's just go for an average rate please.");
			} else if(componentId == OPTION_4) {
				stage = 3;
				sendPlayerDialogue(9827, "I don't like the grind, let's take it... Easy!");
			}
			
			break;
		case 1:
			stage = 7;
			player.ishard = true;
			player.newuserdone = true;
			player.getStarter();
			player.unlock();
		//	Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(Settings.RESPAWN_PLAYER_LOCATION));
			PlayerLook.openThessaliasMakeOver(player);
			player.getDialogueManager().startDialogue("SimpleMessage", "If you have blackscreen, don't worry! It's just your client downloading our data! Check out ::starterguide first!");
			// end();
			break;
		case 2:
			stage = 7;
			player.isaverage = true;
			player.getStarter();
			player.unlock();
			//Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(Settings.RESPAWN_PLAYER_LOCATION));
			PlayerLook.openThessaliasMakeOver(player);
			player.getDialogueManager().startDialogue("SimpleMessage", "If you have blackscreen, don't worry! It's just your client downloading our data! Check out ::starterguide first!");
			// end();
			break;
		case 3:
			stage = 4;
			sendNPCDialogue(npcId, 9827, "What a sucker... Fine!");
			break;
		case 8:
			sendNPCDialogue(npcId, 9827, "Choosing this rank means you cannot interact with players! Xp rank = average.");
			stage = 9;
			break;
		case 4:
			sendNPCDialogue(npcId, 9827, "Choosing this rank will remove your to use the completionist cape, are you sure? ");
			stage = 5;
			break;
		case 5:
			sendOptionsDialogue(SEND_DEFAULT_OPTIONS_TITLE, "Yes I'm sure. ", "No. Let me choose another Xp Rate!");
			stage = 6;
			break;
		case 9:
			sendOptionsDialogue(SEND_DEFAULT_OPTIONS_TITLE, "Yes I'm sure. ", "No. Let me choose another Xp Rate!");
			stage = 10;
			break;
		case 11:
			sendOptionsDialogue(SEND_DEFAULT_OPTIONS_TITLE, "Yes I'm sure. ", "No. Let me choose another Xp Rate!");
			stage = 12;
			break;
		case 12:
			if(componentId == OPTION_1) {
				stage = 7;
				player.isheroic = true;
				player.newuserdone = true;
				player.getStarter();
				player.unlock();
			//	Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(Settings.RESPAWN_PLAYER_LOCATION));
				PlayerLook.openThessaliasMakeOver(player);
				player.getDialogueManager().startDialogue("SimpleMessage", "If you have blackscreen, don't worry! It's just your client downloading our data! Check out ::starterguide first!");
				// end();
				} else {
				stage = -1;
				sendPlayerDialogue(9827, "No thanks! Rather grind and make achievements! Take me Back!");
			}
			break;
		case 6:
			if(componentId == OPTION_1) {
				stage = 7;
				player.iseasy = true;
				player.newuserdone = true;
				player.getStarter();
				player.unlock();
				//Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(Settings.RESPAWN_PLAYER_LOCATION));
				PlayerLook.openThessaliasMakeOver(player);
				player.getDialogueManager().startDialogue("SimpleMessage", "If you have blackscreen, don't worry! It's just your client downloading our data! Check out ::starterguide first!");
				} else {
				stage = -1;
				sendPlayerDialogue(9827, "No thanks! Rather grind and make achievements! Take me Back!");
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
