package com.rs.game.player.dialogues.impl;

import com.rs.game.player.dialogues.Dialogue;

public class AdventureLog extends Dialogue {

	@Override
	public void start() {
		sendOptionsDialogue("Select an option.",
				"In-Game Highscores.", "Gwd Drop Log.", "Misc Drop Log.", "Kill Tracker.", "Online Adventures Log");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
		if (componentId == OPTION_1) {
            player.getTemporaryAttributtes().put("Highscores", true);
            player.getPackets().sendRunScript(109, "Enter Player's Name:");
		end();
		} else if (componentId == OPTION_2) {
            player.getTemporaryAttributtes().put("Gwd_Drop_Log", true);
            player.getPackets().sendRunScript(109, "Enter Player's Name:");
		end();
		} else if (componentId == OPTION_3) {
		player.getTemporaryAttributtes().put("Misc_Drop_Log", true);
	    player.getPackets().sendRunScript(109, "Enter Player's Name:");
		end();
		} else if (componentId == OPTION_4) {
			stage = 2;
			sendOptionsDialogue(SEND_DEFAULT_OPTIONS_TITLE, "Boss Killcount", "Other Monster Killcount");
	} else if (componentId == OPTION_5) {
	//	player.getPackets().sendOpenURL("http://drygonscape.co.uk/advlog/profile.php?name="+player.getUsername()+"");
		end();
	}
		}
	else if (stage == 2) {
		if (componentId == OPTION_1) {
			player.getTemporaryAttributtes().put("Kill_Tracker", true);
		    player.getPackets().sendRunScript(109, "Enter Player's Name:");
		    end();
		} else if (componentId == OPTION_2) {
			player.getTemporaryAttributtes().put("Kill_Tracker_Other", true);
		    player.getPackets().sendRunScript(109, "Enter Player's Name:");
		    end();
		}
	}
	}

	

	@Override
	public void finish() {

	}

}
