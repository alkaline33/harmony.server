package com.rs.game.player.dialogues.properties;

import com.rs.game.World;
import com.rs.game.player.Player;
import com.rs.game.player.dialogues.Dialogue;

public class KaneProp extends Dialogue {

	private int npcId = 15584;

	@Override
	public void start() {
		//npcId = (Integer) parameters[0];
		sendNPCDialogue(
				npcId,
				9827,
				"Hello, "
						+ player.getUsername()
						+ ". This divine property belongs to Kane. Do you wish to ring the bell?");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			sendOptionsDialogue("Ring the bell?", 
					"Yes!",
					"No");
			stage = 2;
		} else if (stage == 2) {
			if (componentId == OPTION_1) {
				Player target = World.getPlayerByDisplayName("Kane");
				Player targetV = World.getPlayerByDisplayName(player.getDisplayName());
				if (!World.KaneProp(target)) {
					player.sendMessage("Sorry, Kane isn't home right now.");
					end();
					return;
				}
				target.sendMessage(""+player.getDisplayName()+" wants to enter your property. Allow them in?");
				target.sendMessage("::allowin");
				target.sendMessage("<col=ff0000> ::donotallowin");
				
				stage = 3;
				end();
					return;
				} else {
					end();
					stage = 3;
				}
			} else if (stage == 2) {
			if (componentId == OPTION_2) {
				stage = 3;
				end();
					return;
			}
		} else if (stage == 3) {
			end();
		}

	}

	@Override
	public void finish() {

	}

}