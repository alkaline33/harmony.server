package com.rs.game.player.dialogues.impl.dungeoneering;

import com.rs.game.WorldTile;
import com.rs.game.player.dialogues.Dialogue;

public class HighDungTut extends Dialogue {

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
			player.setNextWorldTile(new WorldTile(2831, 10097, 0));
			sendEntityDialogue(IS_NPC, "Guide", 945, 9827, "Search this rack a few times until you find a Grubby key.");
			break;
		case 0:
			stage = 1;
			player.setNextWorldTile(new WorldTile(2834, 10086, 0));
			sendEntityDialogue(IS_NPC, "Guide", 945, 9827, "Pickpocket Berry for a Damp Cloth & use that cloth on the Grubby key.");
			break;
		case 1:
			stage = 2;
			player.setNextWorldTile(new WorldTile(2832, 10083, 0));
			sendEntityDialogue(IS_NPC, "Guide", 945, 9827, "Use the key on the Weird Old Man & then unlock the jail cell with Godric inside.");
			break;
		case 2:
			stage = 3;
			sendEntityDialogue(IS_NPC, "Guide", 945, 9827, "Use the hammer on Godric & then head south to the exit, kill the boss & repeat.");
			player.highdungtut = true;
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
