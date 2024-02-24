package com.rs.game.player.dialogues.impl;

import com.rs.game.World;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.utils.Utils;

public class ThirdAgeCreate extends Dialogue {

	private int npcId = 6034;

	@Override
	public void start() {
		//npcId = (Integer) parameters[0];
		sendNPCDialogue(
				npcId,
				9827,
				"Ah.. hello "
						+ player.getUsername()
						+ ", How may i help you today?");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			if (player.getSkills().getLevelForXp(Skills.PRAYER) < 99) {
				player.getPackets().sendGameMessage("You need a prayer level of 99 to exchange third age.");
				end();
				return;
			}
			sendOptionsDialogue("Do you wish to exchange for a third age item?", 
					"Yes",
					"No, not really.");
			stage = 2;
		} else if (stage == 2) {
			if (componentId == OPTION_1) {
				if(player.getInventory().containsItem(29957, 1) && player.getInventory().containsItem(29958, 1) && player.getInventory().containsItem(29959, 1) && player.getInventory().containsItem(29960, 1) && player.getInventory().containsItem(29961, 1) && player.getInventory().containsItem(29962, 1) && player.getInventory().containsItem(29963, 1) && player.getInventory().containsItem(29964, 1) ) {
					int[] RandomItems = {10330, 10332, 10334, 10336, 10338, 10340, 10342, 10344, 10346, 10348, 10350, 10352};
					  int i = Utils.getRandom(RandomItems.length);
					player.getInventory().deleteItem(29957, 1); //corp
					player.getInventory().deleteItem(29958, 1); //nex
					player.getInventory().deleteItem(29959, 1); //bandos
					player.getInventory().deleteItem(29960, 1); //zamorak
					player.getInventory().deleteItem(29961, 1); //arma
					player.getInventory().deleteItem(29962, 1); //sara
					player.getInventory().deleteItem(29963, 1); //vorago
					player.getInventory().deleteItem(29964, 1); //kbd
			        player.getInventory().addItem(RandomItems[i], 1);
						for (Player players : World.getPlayers()) {
									if (players == null)
										continue;
						players.getPackets().sendGameMessage("<col=B247B2>"+ player.getDisplayName() +" has received a third-age item!</col>");
							}
						end();
						return;
					} else {
						player.sendMessage("You need to have all eight signets to create third age");
						end();
					}
			
			} else if (stage == 2) {
			if (componentId == OPTION_2) {
				end();
			}
		} else if (stage == 3) {
			end();
		}
	}
}
	

	@Override
	public void finish() {

	}

}