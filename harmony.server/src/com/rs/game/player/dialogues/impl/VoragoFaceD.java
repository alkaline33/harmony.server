package com.rs.game.player.dialogues.impl;
/*package com.rs.game.player.dialogues;

import com.rs.game.map.bossInstance.impl.VoragoInstance;
import com.rs.game.player.dialogues.Dialogue;

public class VoragoFaceD extends Dialogue {

	private int npcId;
	private int STAGE;
	private VoragoInstance instance;
	private byte END_STAGE = 46;

	@Override
	public void start() {
		npcId = (int) this.parameters[0];
		STAGE = (int) this.parameters[1];
		instance = (VoragoInstance) this.parameters[2];
		switch (STAGE) {
		case 0:// npc click 1
			if (player.VoragoKills > 0) {
				stage = 1;
				sendNPCDialogue(npcId, NORMAL, player.getDisplayName() + " defeats me, and yet returns...");
			} else if (player.hasSpokenToVorago()) {
				stage = 1;
				sendNPCDialogue(npcId, NORMAL, player.getDisplayName() + " returns.");
			} else {
				stage = -1;
				sendNPCDialogue(npcId, NORMAL, "Hello stranger");
			}
			break;
		case 1:// npc click 2
			if (!player.hasSpokenToVorago()) {
				stage = END_STAGE;
				sendNPCDialogue(npcId, NORMAL,
						"So impatient you surfacers are. Speak to me first, for to engage in battle with me ill-prepared could mean your death.");
				return;
			} else if (instance.ChallengeStarted()) {
				if (instance.getAcceptedChallenge().contains(player)) {
					stage = END_STAGE;
					sendDialogue("You already accepted vorago challenge.");
					return;
				}
				stage = 43;
				sendOptionsDialogue("LOOKS LIKE THERE IS A TEST ONGOING TO DO YOU WANT TO JOIN?", "We fight.",
						"Not right now.");
			} else {
				stage = 42;
				sendNPCDialogue(npcId, NORMAL, "So, do we fight?");
				break;
			}
			break;
		}

	}

	@Override
	public void run(int interfaceId, int componentId) {
		switch (stage) {
		case -1:
			stage = 0;
			player.setSpokenToVorago(true);
			sendPlayerDialogue(NORMAL, "I'm no stranger. Call me " + player.getDisplayName() + ".");
			break;
		case 0:
			stage = 1;
			sendNPCDialogue(npcId, NORMAL, "So be it, " + player.getDisplayName()
					+ ". I am the Immoveable. The Enduring. You may call me Vorago.");
			break;
		case 1:
			stage = 2;
			sendOptionsDialogue(SEND_DEFAULT_OPTIONS_TITLE, "What are you?", "What are you doing here?",
					"I don't want to talk to you.");
			break;
		case 2:
			switch (componentId) {
			case OPTION_1:// what are you?
				stage = 3;
				sendPlayerDialogue(NORMAL, "What are you?");
				break;
			case OPTION_2:
				stage = 26;
				sendPlayerDialogue(NORMAL, "What are you doing here?");
				break;
			case OPTION_3:
				stage = 45;
				sendPlayerDialogue(NORMAL, "I don't want to talk to you.");
				break;
			}
			break;
		case 3:
			stage = 4;
			sendNPCDialogue(npcId, NORMAL,
					"I know only that I am of the earth, and the earth and I are one. More than those above see and know. I am here to ensure the continuation of that life.");
			break;
		case 4:
			stage = 5;
			sendPlayerDialogue(NORMAL,
					"I've heard of living rock creatures, not far from here. Are you of the same kind?");
			break;
		case 5:
			stage = 6;
			sendNPCDialogue(npcId, NORMAL,
					"They are known to me. I am not as they are, however. The earth soul and I are as one, and they are my wards.");
			break;
		case 6:
			stage = 7;
			sendOptionsDialogue(SEND_DEFAULT_OPTIONS_TITLE, "You talk like you don't know what you are.",
					"So where did you come from?", "Do you know much about gods?");
			break;
		case 7:
			switch (componentId) {
			case OPTION_1:
				stage = 8;
				sendPlayerDialogue(NORMAL, "You talk like you don't know what you are.");
				break;
			case OPTION_2:
				stage = 14;
				sendPlayerDialogue(NORMAL, "So where did you come from?");
				break;
			case OPTION_3:
				stage = 20;
				sendPlayerDialogue(NORMAL, "Do you know much about gods?");
				break;
			}
			break;
		case 8:
			stage = 9;
			sendNPCDialogue(npcId, NORMAL,
					"It is hard to describe in words that you will understand. I am one with the earth soul. It permeates me, and grants me the power to command the earth.");
			break;
		case 9:
			stage = 10;
			sendPlayerDialogue(NORMAL, "Command it? What do you mean by that?");
			break;
		case 10:
			stage = 11;
			sendNPCDialogue(npcId, NORMAL,
					"I feel the fury of the battles that have taken place on this good earth. I can command it to take shape and fight back, if necessary.");
			break;
		case 11:
			stage = 12;
			sendPlayerDialogue(NORMAL, "You could fight?");
			break;
		case 12:
			stage = 13;
			sendNPCDialogue(npcId, NORMAL,
					"Should I deem it necessary. Should something be of significant enough worth to warrant it, yes.");
			break;
		case 13:
			stage = 2;
			sendOptionsDialogue(SEND_DEFAULT_OPTIONS_TITLE, "What are you?", "What are you doing here?",
					"I don't want to talk to you.");
			break;
		case 14:
			stage = 15;
			sendNPCDialogue(npcId, NORMAL,
					"That is an unknown. I feel reawakened of late, as if the world has changed.");
			break;
		case 15:
			stage = 16;
			sendPlayerDialogue(NORMAL,
					"A lot has changed recently for us all. Do you remember anything before this reawakening?");
			break;
		case 16:
			stage = 17;
			sendNPCDialogue(npcId, NORMAL,
					"I remember taking shape at one point in my past, using the power of the earth to destroy. I was defeated by a powerful weapon.");
			break;
		case 17:
			stage = 18;
			sendPlayerDialogue(NORMAL, "Interesting. Where would that weapon be now?");
			break;
		case 18:
			stage = 19;
			sendNPCDialogue(npcId, NORMAL,
					"I have it. I may be stopped but not destroyed, I will simply reform in new earth once more. Besides, I hold the only weapon ever to defeat me within my rock itself.");
			break;
		case 19:
			stage = 2;
			sendOptionsDialogue(SEND_DEFAULT_OPTIONS_TITLE, "What are you?", "What are you doing here?",
					"I don't want to talk to you.");
			break;
		case 20:
			stage = 21;
			sendNPCDialogue(npcId, NORMAL, "They are known to me, yes.");
			break;
		case 21:
			stage = 22;
			sendPlayerDialogue(NORMAL, "Are you aligned to any?");
			break;
		case 22:
			stage = 23;
			sendNPCDialogue(npcId, NORMAL,
					"No. Their concerns are not mine. I seek only to defend the deep earth, and to repel those who would do it harm.");
			break;
		case 23:
			stage = 24;
			sendPlayerDialogue(NORMAL, "You'd fight them?");
			break;
		case 24:
			stage = 25;
			sendNPCDialogue(npcId, NORMAL,
					"I seek worthy opponents against which to test myself, to increase my strength and skill. If a god were to approach with such a purpose, it would no doubt be a worthy battle. If they came as conquerors or destroyers, though, then I would need to be prepared.");
			break;
		case 25:
			stage = 2;
			sendOptionsDialogue(SEND_DEFAULT_OPTIONS_TITLE, "What are you?", "What are you doing here?",
					"I don't want to talk to you.");
			break;
		case 26:
			stage = 27;
			sendNPCDialogue(npcId, NORMAL, "I am waiting.");
			break;
		case 27:
			stage = 28;
			sendPlayerDialogue(NORMAL, "Waiting for what?");
			break;
		case 28:
			stage = 29;
			sendNPCDialogue(npcId, NORMAL, "Those worthy of facing me in battle.");
			break;
		case 29:
			stage = 30;
			sendOptionsDialogue(SEND_DEFAULT_OPTIONS_TITLE, "Worthy of facing you?", "Can I face you?");
			break;
		case 30:
			switch (componentId) {
			case OPTION_1:
				stage = 31;
				sendPlayerDialogue(NORMAL, "Worthy of facing you?");
				break;
			case OPTION_2:
				stage = 35;
				sendPlayerDialogue(NORMAL, "Can I face you?");
				break;
			}
			break;
		case 31:
			stage = 32;
			sendNPCDialogue(npcId, NORMAL,
					"Yes. I must do battle with the strongest ones of this world, to defend against what is to come.");
			break;
		case 32:
			stage = 33;
			sendPlayerDialogue(NORMAL, "Who are the unworthy, then?");
			break;
		case 33:
			stage = 34;
			if (player.VoragoKills == 0)
				sendNPCDialogue(npcId, NORMAL,
						"To prepare for what is to come, I must fight at the peak of my strength. Those who cannot stand before my power are not worthy. To fight me would be a waste of their lives and of time.");
			else
				sendNPCDialogue(npcId, NORMAL, "You have defeated me in the past. Consider yourself worthy.");
			break;
		case 34:
			stage = 2;
			sendOptionsDialogue(SEND_DEFAULT_OPTIONS_TITLE, "What are you?", "What are you doing here?",
					"I don't want to talk to you.");
			break;
		case 35:
			stage = 36;
			sendNPCDialogue(npcId, NORMAL,
					"I offer a fair test to those who wish to challenge me - a test of resilience and resolve.");
			break;
		case 36:
			stage = 37;
			sendPlayerDialogue(NORMAL, "I'm listening.");
			break;
		case 37:
			stage = 38;
			sendNPCDialogue(npcId, NORMAL,
					"If you or a group of your allies can withstand my raw power, I will face you in combat inside the borehole below.");
			break;
		case 38:
			stage = 39;
			sendPlayerDialogue(NORMAL, "When you say 'raw power', how much are we talking about here?");
			break;
		case 39:
			stage = 40;
			sendNPCDialogue(npcId, NORMAL,
					"I won't hold back. Ensure anyone you bring with you has spoken to me about this as well before I take them with us into the borehole.");
			break;
		case 40:
			stage = 41;
			sendDialogue(
					"<col=ff0000>Challenging Vorago will initiate an attack</col>. After 20 seconds he will unleash 2000 damage across yourself and those who choose to accept and go with you into the borehole. Only players who have also spoken to Vorago this far will be offered the option to join a fight if you initiate a challenge.");
			break;
		case 41:
			stage = 42;
			sendNPCDialogue(npcId, NORMAL, "So, do we fight?");
			break;
		case 42:
			if (instance.ChallengeStarted()) {
				if (instance.getAcceptedChallenge().contains(player)) {
					stage = END_STAGE;
					sendDialogue("You already accepted vorago challenge.");
					return;
				}
				stage = 43;
				sendOptionsDialogue("LOOKS LIKE THERE IS A TEST ONGOING TO DO YOU WANT TO JOIN?", "We fight.",
						"Not right now.");
			} else {
				stage = 43;
				sendOptionsDialogue(SEND_DEFAULT_OPTIONS_TITLE, "We fight.", "Not right now.");
			}
			break;
		case 43:
			switch (componentId) {
			case OPTION_1:
				stage = END_STAGE;
				sendPlayerDialogue(NORMAL, "We fight.");
				if (instance.ChallengeStarted()) {
					instance.getAcceptedChallenge().add(player);
					player.getPackets().sendGameMessage(
							"<col=EE7600>Vorago accepted your challenge and begins to charge a massive attack.");
				} else {
					instance.sendChallenge(player);
				}
				break;
			case OPTION_2:
				stage = 44;
				sendPlayerDialogue(NORMAL, "Not right now.");
				break;
			}
			break;
		case 44:
			stage = END_STAGE;
			sendNPCDialogue(npcId, NORMAL, "A wise decision, " + player.getDisplayName() + ".");
			break;
		case 45:
			stage = END_STAGE;
			sendNPCDialogue(npcId, NORMAL, "It is wise of you to leave. Farewell.");
			break;
		case 46:
			end();
			break;
		}
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub

	}

}*/