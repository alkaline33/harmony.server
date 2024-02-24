package com.rs.game.player.dialogues.impl;

import com.rs.game.player.dialogues.Dialogue;

public class ConTutorial extends Dialogue {



	@Override
	public void start() {
		sendEntityDialogue(IS_NPC, "Construction Tutorial", 4247, 9827,
				"Hello "+player.getDisplayName()+"!", 
				"It seems that you haven't completed the construction tutorial..");
	}
	@Override
	public void run(int interfaceId, int componentId) {
		switch (stage) {
		case -1:
			stage = 0;
			sendEntityDialogue(IS_NPC, "Construction Tutorial", 4247, 9827, "Allow me to give you a quick insight on construction!");
			break;
		case 0:
			stage = 2;
			player.unlock();
			player.tutorialTeleport(2736, 3502, 0);
			player.lock();

			sendEntityDialogue(IS_NPC, "Construction Tutorial", 4247, 9827,"In order to start construction you need to speak to the Estate Agent.",
					"He will sell you 4 different types of houses depending on your construction level! ",
					"Obviously the higher level they're, the more it's going to break your bank!");
			break;
			
		case 2:	
			player.unlock();

			player.tutorialTeleport(2541, 3096, 0);
			player.lock();

			sendEntityDialogue(IS_NPC, "Construction Tutorial", 4247, 9827, "This kind sir will sell you construction materials at cheap prices! Some materials must be obtained by the player though.");
			stage = 3;
			break;
		case 3:
			sendEntityDialogue(IS_NPC, "Construction Tutorial", 4247, 9827, "Sadly I can't take you inside the houses because you haven't past the tutorial, but soon you will!");
			stage = 4;
			break;
		case 4:
			player.unlock();
			player.tutorialTeleport(3372, 3500, 0);
			player.lock();

			sendEntityDialogue(IS_NPC, "Construction Tutorial", 4247, 9827, "This is where you can obtain limestone which is only obtainable via player trade or if you mine it yourself. The shops don't sell this item!");
			stage = 5;
			break;
		case 5:
			if (player.getInventory().getFreeSlots() < 2) {
				player.sendMessage("You need two inventory spaces free to continue the tutorial.");
				player.unlock();
				end();
				return;
			}
			player.unlock();

			player.tutorialTeleport(2810, 3344, 0);
			player.getInventory().addItem(9625, 1);
			player.getInventory().addItem(8309, 1);
			player.contut = 1;
			player.lock();

			sendEntityDialogue(IS_NPC, "Construction Tutorial", 4247, 9827, "I've placed an untradable saw and a chair in your inventory, cut up the chair and we will speak again! You won't be able to move until you've done this.");
			stage = 6;
			break;
		case 6:
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
