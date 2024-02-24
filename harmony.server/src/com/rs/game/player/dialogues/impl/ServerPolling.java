package com.rs.game.player.dialogues.impl;

import com.rs.game.World;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.utils.HasVotedOnPoll;
import com.rs.utils.ServerPollNo;

public class ServerPolling extends Dialogue {
	
	/**
	 * Author Mr_Joopz
	 */

	private int npcId = 1206;
	private String Poll = "Should we remove the Fire/Kiln cape from the donation store?";
	//When changing poll, 
	//remove haspolled, serverpollyes/no serfiles!

	@Override
	public void start() {
/*		if (HasVotedOnPoll.HasPolled(player.getMacAddress())) { //When changing poll, remove haspolled macs!
			sendNPCDialogue(npcId, 9827, "You have already voted on this poll!");
			stage = 3;
			return;
		}*/
		sendItemDialogue(1050, 1, "Hey " + player.getDisplayName() + ", I'm Paul. "
				+ "There is currently a poll activate: <col=ff0000>"            +Poll+"");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			sendOptionsDialogue(""+Poll+"", "Yes", "No", "I don't wish to vote right now.");
			stage = 2;
		} else if (stage == 2) {
			if (componentId == OPTION_1) {
				player.sendMessage("You have voted <col=00ff00>Yes.");
				HasVotedOnPoll.addPolled(player.getMacAddress());
				end();
				return;
				
			}
			if (componentId == OPTION_2) {
				player.sendMessage("You have voted <col=ff0000>No.");
				ServerPollNo.addPolled(1);
				//player.sendMessage(ServerPollNo.HasPolled(1));
				end();
				return;
			}
			if (componentId == OPTION_3) {
				end();
			}
		}
		if (stage == 3) {
			//sendPlayerDialogue(9827, "Sad times.");
			end();
		}
	}

	@Override
	public void finish() {

	}

}