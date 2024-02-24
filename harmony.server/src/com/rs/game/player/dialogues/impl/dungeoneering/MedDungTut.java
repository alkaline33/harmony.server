package com.rs.game.player.dialogues.impl.dungeoneering;

import com.rs.game.WorldTile;
import com.rs.game.player.dialogues.Dialogue;

public class MedDungTut extends Dialogue {

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
			sendEntityDialogue(IS_NPC, "Guide", 945, 9827, "You should have a crowbar in your inventory, if not then you didn't have enough space!");
			break;
		case 0:
			stage = 1;
			player.setNextWorldTile(new WorldTile(3482, 9845, 0));
			sendEntityDialogue(IS_NPC, "Guide", 945, 9827, "Use the crowbar a few times on this shelf until you find a Metal Key!");
			break;
		case 1:
			stage = 2;
			player.setNextWorldTile(new WorldTile(3479, 9837, 0));
			sendEntityDialogue(IS_NPC, "Guide", 945, 9827, "Click this wall and then head all the way south!");
			break;
		case 2:
			stage = 3;
			player.setNextWorldTile(new WorldTile(3497, 9808, 0));
			sendEntityDialogue(IS_NPC, "Guide", 945, 9827, "Kill one of these warriors for some Gloom & head back north!");
			break;
		case 3:
			stage = 4;
			player.setNextWorldTile(new WorldTile(3483, 9832, 0));
			sendEntityDialogue(IS_NPC, "Guide", 945, 9827, "Smash these wooden boards with the Gloom & then head back south.");
			break;
		case 4:
			stage = 5;
			player.setNextWorldTile(new WorldTile(3501, 9812, 0));
			sendEntityDialogue(IS_NPC, "Guide", 945, 9827, "Go through these wooden doors, kill the boss & repeat.");
			player.meddungtut = true;
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
