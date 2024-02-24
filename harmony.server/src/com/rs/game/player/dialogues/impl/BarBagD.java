package com.rs.game.player.dialogues.impl;

import com.rs.game.player.dialogues.Dialogue;

public class BarBagD extends Dialogue {
	
	/**
	 * Author @ Mr_Joopz
	 */

	public BarBagD() {
	}

	@Override
	public void start() {
		stage = 1;
		sendOptionsDialogue("Bars", "Bronze bar",
				"Iron bar", "Steel bar", "Next Page");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == 1) {
			if (componentId == OPTION_1) {
				if (player.getInventory().contains(29793)) {
				    player.getTemporaryAttributtes().put("removebbarbag", Boolean.TRUE);
				    player.getPackets().sendRunScript(108, new Object[] { "Enter the amount you'd like to withdraw. You currently have: "+player.bbar+" bronze bars."});
					end();
					return;
			} else {
					player.getPackets().sendGameMessage("You need a bar bag in order to use this."); //game message if you do not have 100 shard
					end();
			}
		}
		 else if (componentId == OPTION_2) {
				if (player.getInventory().contains(29793)) {
				    player.getTemporaryAttributtes().put("removeibarbag", Boolean.TRUE);
				    player.getPackets().sendRunScript(108, new Object[] { "Enter the amount you'd like to withdraw. You currently have: "+player.ibar+" iron bars."});
					end();
					return;
			} else {
					player.getPackets().sendGameMessage("You need a bar bag in order to use this."); //game message if you do not have 100 shard
					end();
				}
			} else if (componentId == OPTION_3) {
				if (player.getInventory().contains(29793)) {
				    player.getTemporaryAttributtes().put("removesbarbag", Boolean.TRUE);
				    player.getPackets().sendRunScript(108, new Object[] { "Enter the amount you'd like to withdraw. You currently have: "+player.sbar+" steel bars."});
					end();
					return;
			} else {
					player.getPackets().sendGameMessage("You need a bar bag in order to use this."); //game message if you do not have 100 shard
					end();
				}
			} else if (componentId == OPTION_4) {
				sendOptionsDialogue("Bars",
						"Mithril bar", "Adamant bar", "Runite bar", "End");
				stage = 4;
			}
			
		} else if (stage == 4) {
			if (componentId == OPTION_1) {
				if (player.getInventory().contains(29793)) {
			    player.getTemporaryAttributtes().put("removembarbag", Boolean.TRUE);
			    player.getPackets().sendRunScript(108, new Object[] { "Enter the amount you'd like to withdraw. You currently have: "+player.mbar+" mithril bars."});
				end();
				return;
		} else {
				player.getPackets().sendGameMessage("You need a bar bag in order to use this."); //game message if you do not have 100 shard
				end();
				}
			} else if (componentId == OPTION_2) {
				if (player.getInventory().contains(29793)) {
				    player.getTemporaryAttributtes().put("removeabarbag", Boolean.TRUE);
				    player.getPackets().sendRunScript(108, new Object[] { "Enter the amount you'd like to withdraw. You currently have: "+player.abar+" adamant bars."});
					end();
					return;
			} else {
					player.getPackets().sendGameMessage("You need a bar bag in order to use this."); //game message if you do not have 100 shard
					end();
				}
			} else if (componentId == OPTION_3) {
				if (player.getInventory().contains(29793)) {
				    player.getTemporaryAttributtes().put("removerbarbag", Boolean.TRUE);
				    player.getPackets().sendRunScript(108, new Object[] { "Enter the amount you'd like to withdraw. You currently have: "+player.rbar+" runite bars."});
					end();
					return;
			} else {
					player.getPackets().sendGameMessage("You need a bar bag in order to use this."); //game message if you do not have 100 shard
					end();
				}
			} else if (componentId == OPTION_4) {
					end();
				}
		}
	}
		
		

	@Override
	public void finish() {
	}

}
