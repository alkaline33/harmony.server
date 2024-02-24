package com.rs.game.player.dialogues.impl.dungeoneering;

import com.rs.game.WorldTile;
import com.rs.game.player.dialogues.Dialogue;

public class LowDungTut extends Dialogue {

	int npcId = 945;
	public int starter = 0;

	@Override
	public void start() {
		// player.lock();
		sendEntityDialogue(IS_NPC, "Guide", 945, 9827, "Hello " + player.getDisplayName() + ". Looks like you haven't done this yet. Allow me to give you a very quick tutorial.");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		switch (stage) {
		case -1:
			stage = 0;
			sendEntityDialogue(IS_NPC, "Guide", 945, 9827, "Let's go East...");
			break;
		case 0:
			stage = 1;
			player.setNextWorldTile(new WorldTile(3498, 9709, 0));
			sendEntityDialogue(IS_NPC, "Guide", 945, 9827, "Kill one of these Warriors & you will obtain some Instructions.");
			break;
		case 1:
			stage = 2;
			player.setNextWorldTile(new WorldTile(3483, 9713, 0));
			sendEntityDialogue(IS_NPC, "Guide", 945, 9827, "Now open these chests until you find a Key Part. You may not find it right away! Give it a few clicks until you do.");
			break;
		case 2:
			stage = 3;
			player.setNextWorldTile(new WorldTile(3485, 9714, 0));
			sendEntityDialogue(IS_NPC, "Guide", 945, 9827, "Use that Key Part you just found on the coffin, you will get another part. Combine those into a key!");
			break;
		case 3:
			stage = 4;
			player.setNextWorldTile(new WorldTile(3493, 9710, 0));
			sendEntityDialogue(IS_NPC, "Guide", 945, 9827, "Now simply go through this door & head north & go through the bronze door. Fight the boss & repeat.");
			player.lowdungtut = true;
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
