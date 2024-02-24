package com.rs.game.player.dialogues.impl;

import com.rs.game.player.dialogues.Dialogue;
import com.rs.game.player.interfaces.PetPerkInterface;

public class VeterinarianD extends Dialogue {

	int npcId = 885;

	@Override
	public void start() {
		// npcId = (Integer) parameters[0];
		// sendNPCDialogue(npcId, 9827, "Hello " + player.getDisplayName() + ", how may
		// i help you?");
		sendEntityDialogue(IS_NPC, "Pet Tamer", 885, 9827, "Hey! What can i help you with?");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		switch (stage) {
		case -1:
			stage = 0;
			sendOptionsDialogue(SEND_DEFAULT_OPTIONS_TITLE, "What are Pet Perks?", "Can i see my current Pet Perks please.", "How can i add a new Pet Perk & what pets work?");
			break;
		case 0:
			switch (componentId) {
			case 11:
				sendNPCDialogue(npcId, 9827, "A pet perk is an item you can obtain from a Pet Mystery box or from various stores & use it on your pet to give them the perk displayed in it's name. " + "For example the item *Pet Perk [4% drop rate]* will give you a 4% drop rate boost & can stack with other perks, with a maximum of 3 at any time.");
				stage = 1;
				break;
			case 13:
				player.getInterfaceManager().sendInterface(new PetPerkInterface(player));
				end();
				break;
			case 4:
			default:
				sendNPCDialogue(npcId, 9827, "Use the Pet Perk item on any Boss or Donator Pet & it will add the perk for you.");
				stage = 1;
				break;
			}
			break;
		default:
			end();
			break;
		}

	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub

	}

}
