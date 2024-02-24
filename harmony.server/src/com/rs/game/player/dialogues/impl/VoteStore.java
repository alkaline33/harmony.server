package com.rs.game.player.dialogues.impl;

import com.rs.game.player.content.Vbox;
import com.rs.game.player.dialogues.Dialogue;


public class VoteStore extends Dialogue {

	public VoteStore() {
	}

	@Override
	public void start() {
		stage = 1;
		sendOptionsDialogue("Vote Point Rewards", "Random reward (12)",
				"2M Coins (1)", "1 Hour x2 Xp (5)", "Flaming skull (21)",
				"Keepsake Key (26)");

	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == 1) {
			if (componentId == OPTION_1) {
				if (player.VotePoint >= 12) {
					
				player.VotePoint -= 12;
				Vbox.isBox(player);
				player.getInventory().refresh();
				player.getInterfaceManager().closeChatBoxInterface();
				end();
			    }else {
				player.getPackets().sendGameMessage("You dont have enough Vote points.");
				end();
				}
			} else if (componentId == OPTION_2) {
				if (player.VotePoint >= 1) {
					player.getBank().addItem(5022, 2, true);
					player.VotePoint -= 1;
					player.getInventory().refresh();
					player.getInterfaceManager().closeChatBoxInterface();
					end();
				    }else {
					player.getPackets().sendGameMessage("You dont have enough Vote points.");
					end();
					}
			} else if (componentId == OPTION_3) {
				if (player.VotePoint >= 5) {
					player.VotePoint -= 5;
					player.getInventory().addItem(14808, 1);
					player.getInventory().refresh();
					player.getInterfaceManager().closeChatBoxInterface();
					player.sendMessage("You have been rewarded a Double xp token.");
					end();
				    }else {
					player.getPackets().sendGameMessage("You dont have enough Vote points.");
					end();
					}
			} else if (componentId == OPTION_4) {
				if (player.VotePoint >= 21) {
					player.VotePoint -= 21;
					player.getInventory().addItem(24437, 1);
					player.getInventory().refresh();
					player.getInterfaceManager().closeChatBoxInterface();
					player.sendMessage("You have unlocked the Flaming skull.");
					end();
				    }else {
					player.getPackets().sendGameMessage("You dont have enough Vote points or you have already unlocked this override.");
					end();
					}
			} else if (componentId == OPTION_5) {
				if (player.VotePoint >= 26) {
					player.VotePoint -= 26;
					player.getBank().addItem(29544, 1, true);
					player.getInventory().refresh();
					player.getInterfaceManager().closeChatBoxInterface();
					player.sendMessage("A Keepsake key has been added to your bank.");
					end();
				    }else {
					player.getPackets().sendGameMessage("You dont have enough Vote points.");
					end();
					}
			}
		}
	}
		

	@Override
	public void finish() {
	}

}
