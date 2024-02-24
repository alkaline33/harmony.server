package com.rs.game.player.dialogues.impl;

import com.rs.game.player.dialogues.Dialogue;

public class DeathChat extends Dialogue {
	
	public static final int GRIM = 8977;
	
	private int stage;

	@Override
	public void start() {
		sendNPCDialogue(GRIM, 9790, "Who dares to disturb me?!?! ");
		stage = -1;
	}

	@Override
	public void run(int interfaceId, int componentId) {
		switch(stage) {
		case -1:
			sendNPCDialogue(GRIM, 9790, "Well? Who are you?!?!!?");
			break;
		case 0:
			sendPlayerDialogue(9765, "Erm... Hi? I was taken here after getting OWN3D! :'( *scared*");
			break;
		case 1:
			sendNPCDialogue(GRIM, 9760, "You're weak and not worth my time. Next time you die, you will end up in your home town.. Away from me :@");
			break;
		case 2:
			sendPlayerDialogue(9845, "Erm thank you I guess?");
			break;
		case 3:
			sendNPCDialogue(GRIM, 9827, "Before you go.. Let me run you through a few things.");
			break;
		case 4:
			sendPlayerDialogue(9845, "Go ahead.. *cough* <col=FF0000> SKINLESS PRICK </col> *cough*");
			break;
		case 5:
			sendNPCDialogue(GRIM, 9790, "What did you call me?!? Oh.. Nevermind child, let's get on with this.");
			break;
		case 6:
			sendNPCDialogue(GRIM, 9827, "When you die you will lose all but 3-4 items unless you're in the wild like a baws and then you may lose all of your items like a noob.");
			break;
		case 7:
			sendNPCDialogue(GRIM, 9845, "If you're outside of dangerzones you may leave behind a gravestone that will have a set time for you to recover your items before they become mine! :D");
			break;
		case 8:
			sendPlayerDialogue(9827, "You're not having my stuff without fighing for it bro?");
			break;
		case 9:
			sendNPCDialogue(GRIM, 9790, "I OWN YOU! I will do as I wish child... Lewts ftw <3");
			break;
		case 10:
			sendPlayerDialogue(9760, "I think your find that guthix owns me.. MATEY!");
			break;
		case 11:
			sendNPCDialogue(GRIM, 9845, "Guthix is a fag that sleeps around... Anyway, let's move on...");
			break;
		case 12:
			sendNPCDialogue(GRIM, 9845, "I think I've covered everything that you newbs need to know, be gone with you child.");
			break;
		case 13:
			player.iDied = 1;
			player.unlock(); //unlocks player
			player.getPackets().sendBlackOut(0);
			player.getInterfaceManager().sendCombatStyles();
			player.getCombatDefinitions().sendUnlockAttackStylesButtons();
			player.getInterfaceManager().sendTaskSystem();
			player.getInterfaceManager().sendSkills();
			player.getInterfaceManager().sendInventory();
			player.getInventory().unlockInventoryOptions();
			player.getInterfaceManager().sendEquipment();
			player.getInterfaceManager().sendPrayerBook();
			player.getPrayer().unlockPrayerBookButtons();
			player.getInterfaceManager().sendMagicBook();
			player.getInterfaceManager().sendEmotes();
			player.getEmotesManager().unlockEmotesBook();
			end();
			break;
		}
		stage++;
	}

	@Override
	public void finish() {
		
	}
}
