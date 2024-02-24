package com.rs.game.player.dialogues.impl;

import com.rs.Settings;
import com.rs.game.WorldTile;
import com.rs.game.player.dialogues.Dialogue;

public class Welcome extends Dialogue {

	int npcId = 945;
	public int starter = 0;
	

	@Override
	public void start() {
		//player.lock();
		sendEntityDialogue(IS_NPC, "Guide", 945, 9827, "Hello "+player.getDisplayName()+". Welcome to Harmony.");
			}

	@Override
	public void run(int interfaceId, int componentId) {
		switch(stage) {
		case -1:
			stage = 0;
			sendEntityDialogue(IS_NPC, "Guide", 945, 9827, "Harmony is a 2012 era server, with content from OSRS as well as RS3. The best of ALL worlds!");
			break;
		case 0:
			stage = 1;
			player.setNextWorldTile(new WorldTile(2847, 3351, 0));
			sendEntityDialogue(IS_NPC, "Guide", 945, 9827, "Here is the shop hub, where you can find almost every shop you will need to get started! Other shops and activities can be found throughout the area.");
			break;
		case 1:
			stage = 2;
			player.setNextWorldTile(new WorldTile(2844, 3347, 0));
			sendEntityDialogue(IS_NPC, "Guide", 945, 9827, "Here you will find an altar that can be right-clicked to change prayers & spells. You'll also see Max, who can teleport you around Harmony!");
			break;
		case 2:
			stage = 3;
			player.setNextWorldTile(new WorldTile(2842, 3350, 0));
			sendEntityDialogue(IS_NPC, "Guide", 945, 9827, "This is the research table, once you obtain higher leveled gear/various other items you can upgrade them for better stats & passive effects!");
			break;
		case 3:
			stage = 4;
			player.setNextWorldTile(new WorldTile(2859, 3336, 0));
			sendEntityDialogue(IS_NPC, "Guide", 945, 9827, "Here you can train the Thieving skill, which is great for starting cash!");
			break;
		case 4:
			stage = 5;
			player.setNextWorldTile(new WorldTile(2895, 2726, 0));
			sendEntityDialogue(IS_NPC, "Guide", 945, 9827, "This is the training area. Kill farmers for seeds & others for keys which can give great starting items & cash from the chest to the west!");
			break;
		case 5:
			stage = 6;
			player.setNextWorldTile(new WorldTile(2832, 3348, 0));
			sendEntityDialogue(IS_NPC, "Guide", 945, 9827, "This is the Recycle Center, using items on the machine can reward Runecoins, which are used to unlock various content & items.");
			break;
		case 6:
			stage = 7;
			player.setNextWorldTile(new WorldTile(Settings.RESPAWN_PLAYER_LOCATION));
			sendEntityDialogue(IS_NPC, "Guide", 945, 9827, "Barrows, crafting, thieving & daily money makers are the best ways to make some starter cash.");
			break;
		case 7:
			stage = 8;
			sendEntityDialogue(IS_NPC, "Guide", 945, 9827, "You can find all our teleports in the quest tab with a nice unique interface.");
			break;
		case 8:
			stage = 9;
			sendEntityDialogue(IS_NPC, "Guide", 945, 9827, "We highly suggest using OPENGL graphics for the best graphic experience, however should you experience issues, please use DirectX.");
			break;
		default:
			player.unlock();
			player.sendDefaultPlayersOptions();
			end();
			break;
		}
	}

	@Override
	public void finish() {

	}

}
