package com.rs.game.player.dialogues.impl;

import com.rs.game.player.dialogues.Dialogue;

public class NewUser extends Dialogue {



	@Override
	public void start() {
		sendEntityDialogue(IS_NPC, "Mr Ex", 3709, 9827,
				"Histora is a server made by : <img=1>Jack.", 
				"In this server you can do many things! Anyways i'll show you now!");
	}
	@Override
	public void run(int interfaceId, int componentId) {
		switch (stage) {
		case -1:
			stage = 0;
			sendEntityDialogue(IS_NPC,"Mr Ex",3709,9827, "Lets start!");
			break;
		case 0:
			stage = 2;
			player.unlock();
			player.tutorialTeleport(2143, 5540, 3);
			player.lock();

			sendEntityDialogue(IS_NPC,"Mr Ex",3709,9827,"This is Home, the place where you will respawn.",
					"You can go to home by typing ::home. ",
					"Now were going further!");
			break;
			
		case 2:	
			player.unlock();

			player.tutorialTeleport(2144, 5545, 3);
			player.lock();

			sendEntityDialogue(IS_NPC,"Mr Ex",3709,9827, "And now for the teleportation part, by going to Me or Max right here", "We teleport you around the world of Harmony.");
			stage = 3;
			break;
		case 3:
			player.unlock();

			player.tutorialTeleport(2146, 5537, 3);
			player.lock();

			sendEntityDialogue(IS_NPC,"Mr Ex",3709,9827, "Then we are at the bank of Harmony!", "Here you can bank all your items.");
			stage = 4;
			break;
		case 4:
			player.unlock();

			player.tutorialTeleport(2153, 5541, 3);

			player.lock();

			sendEntityDialogue(IS_NPC,"Mr Ex",3709,9827,"This is the ''Magical fire''", "This fire is handy for cooking");
			stage = 5;
			break;
		case 5:
			player.unlock();

			player.tutorialTeleport(2152, 5534, 3);

			player.lock();

			sendEntityDialogue(IS_NPC,"Mr Ex",3709,9827,"This is where you can check the ingame adventures log, completionist cape reqs and teleport with Max.");
			stage = 6;
			break;
		case 6:
			player.unlock();

			player.tutorialTeleport(2136, 5532, 3);

			player.lock();

			sendEntityDialogue(IS_NPC,"Mr Ex",3709,9827,"Now we arrived at the shopping area, buy various equipment around this area!");
			stage = 7;
			break;
		case 7:
			player.unlock();

			player.tutorialTeleport(3367, 3276, 0);

			player.lock();

			sendEntityDialogue(IS_NPC,"Mr Ex",3709,9827,"This is the Duel arena", "Stake or friendly fights!");
			stage = 8;
			break;
		case 8:
			player.unlock();

			player.tutorialTeleport(2146, 5542, 3);
//
			player.lock();

			sendEntityDialogue(IS_NPC,"Mr Ex",3709,9827,"Here is your starter.", "You must set a new pin, or you cannont leave this area.");
			stage = 9;
			break;
		case 9:
			player.newuserdone = true;
			player.getDialogueManager().startDialogue("XpRates");
		//	player.getInterfaceManager().closeChatBoxInterface();
			//player.unlock();
			player.getStarter();
			//player.getInventory().addItem(995, 5000000);
			//player.getInventory().addItem(1725, 1);
			//player.getInventory().addItem(15273, 150);
			//player.getInventory().addItem(3105, 1);
			//player.getInventory().addItem(1323, 1);
			//player.getInventory().addItem(841, 1);
			//player.getInventory().addItem(884, 250);
			//player.getInventory().addItem(1351, 1);
			//player.getInventory().addItem(303, 1);
			//player.getInventory().addItem(318, 20);
			//player.getInventory().addItem(1512, 20);
			//player.getInventory().addItem(590, 1);
			//----
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
