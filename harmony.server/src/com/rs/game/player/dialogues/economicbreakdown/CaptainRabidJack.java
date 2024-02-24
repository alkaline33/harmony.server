package com.rs.game.player.dialogues.economicbreakdown;

import com.rs.Settings;
import com.rs.game.WorldTile;
import com.rs.game.player.Skills;
import com.rs.game.player.content.Magic;
import com.rs.game.player.dialogues.Dialogue;


public class CaptainRabidJack extends Dialogue {

	int npcId = 7847;
	public int starter = 0;
	

	@Override
	public void start() {
			sendPlayerDialogue(9827, "Captain! Are you okay? Where is your team?");
				
		}

	@Override
	public void run(int interfaceId, int componentId) {
		switch(stage) {
		case -1:
			stage = 0;
			sendEntityDialogue(IS_NPC, "Captain Rabid Jack", npcId, 9827, "I lost them, a powerful being, twice the size of us killed them all.");
			break;
		case 0:
			sendPlayerDialogue(9827, "Oh no, that is terrible!");
			stage = 1;
			break;
		case 1:
			sendEntityDialogue(IS_NPC, "Captain Rabid Jack", npcId, 9827, "Oh my.... He is getting stronger as time proceeds.");
			stage = 2;
		break;
		case 2:
			sendPlayerDialogue(9827, "What do you mean?");
			stage = 3;
			break;
		case 3:
			sendEntityDialogue(IS_NPC, "Captain Rabid Jack", npcId, 9827, "He has many forms and many phases. He holds access to a mysterious power...");
			stage = 4;
		break;
		case 4:
			stage = 5;
			sendEntityDialogue(IS_NPC, "Captain Rabid Jack", npcId, 9827, "Melee, range & Magic. We suspect they're weapons, but we can't get close enough to find out.");
			break;
		case 5:
			stage = 6;
			sendEntityDialogue(IS_NPC, "Captain Rabid Jack", npcId, 9827, "One day.. They will come to mainland and doom will spread. I'll see you soon.");
			
			break;
		case 6:
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(Settings.RESPAWN_PLAYER_LOCATION));
			player.ecobdpart = 5;
			player.completedecobreakdownquest = true;
			player.getInterfaceManager().sendInterface(1244);
			player.getPackets().sendIComponentText(275, 27, "Economic Breakdown");
			player.getPackets().sendGlobalString(359, "<br>Congratulations!</br> <br>You were given.</br> <br>2 spins on the Squeal Of Fortune!</br> <br>Some Slayer Experience</br> <br>50% off all boss instances.</br><br>5M coins.</br>");
			player.spins+=2;
			player.getSkills().addXpLamp(Skills.SLAYER, 10000);
			player.getBank().addItem(5022, 5, true);
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
