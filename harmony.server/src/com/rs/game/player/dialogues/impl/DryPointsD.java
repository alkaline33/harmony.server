package com.rs.game.player.dialogues.impl;

import com.rs.game.player.dialogues.Dialogue;

public class DryPointsD extends Dialogue {

	public DryPointsD() {
	}

	@Override
	public void start() {
		stage = 1;
		sendOptionsDialogue("Remember Inventory space!", "100 Blue Charms (4K)",
				"Santa Hat (3M)", "100 Crimson Charms (2.5K)", "Hati Cloak (50K)",
				"Nothing");

	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == 1) {
			if (componentId == OPTION_1) {
				if (player.Drypoints >= 4000) {
				player.getInventory().addItem(12163, 100);
				player.Drypoints -= 4000;
				player.getInventory().refresh();
				player.getInterfaceManager().closeChatBoxInterface();
				end();
			    }else {
				player.getPackets().sendGameMessage("you dont have enough PvM points.");
				end();
				}
			} else if (componentId == OPTION_2) {
				if (player.Drypoints >= 3000000) {
					player.getInventory().addItem(1050, 1);
					player.Drypoints -= 3000000;
					player.getInventory().refresh();
					player.getInterfaceManager().closeChatBoxInterface();
					end();
				    }else {
					player.getPackets().sendGameMessage("you dont have enough PvM points.");
					end();
					}
			} else if (componentId == OPTION_3) {
				if (player.Drypoints >= 2500) {
					player.getInventory().addItem(12160, 100);
					player.Drypoints -= 2500;
					player.getInventory().refresh();
					player.getInterfaceManager().closeChatBoxInterface();
					end();
				    }else {
					player.getPackets().sendGameMessage("you dont have enough PvM points.");
					end();
					}
			} else if (componentId == OPTION_4) {
				if (player.Drypoints >= 50000) {
					player.getInventory().addItem(23028, 1);
					player.Drypoints -= 50000;
					player.getInventory().refresh();
					player.getInterfaceManager().closeChatBoxInterface();
					end();
				    }else {
					player.getPackets().sendGameMessage("you dont have enough PvM points.");
					end();
					}
			} else if (componentId == OPTION_5) {
				end();
			/*	sendOptionsDialogue("Remember Inventory space!",
						"Void Knight Top (8k)", "Void Knight Robe (8k)",
						"Void Knight Gloves (6K)", "All 3 Void Knight Helmets (12k)", "Next Page.");
				stage = 4;*/
			}
		} else if (stage == 4) {
			if (componentId == OPTION_1) {	
				if (player.Drypoints >= 8000) {
					player.getInventory().addItem(8839, 1);
					player.Drypoints -= 8000;
					player.getInventory().refresh();
					player.getInterfaceManager().closeChatBoxInterface();
					end();
				    }else {
					player.getPackets().sendGameMessage("you dont have enough PvM points.");
					end();
					}
			} else if (componentId == OPTION_2) {
				if (player.Drypoints >= 8000) {
					player.getInventory().addItem(8840, 1);
					player.Drypoints -= 8000;
					player.getInventory().refresh();
					player.getInterfaceManager().closeChatBoxInterface();
					end();
				    }else {
					player.getPackets().sendGameMessage("you dont have enough PvM points.");
					end();
					}
			} else if (componentId == OPTION_3) {
				if (player.Drypoints >= 6000) {
					player.getInventory().addItem(8842, 1);
					player.Drypoints -= 6000;
					player.getInventory().refresh();
					player.getInterfaceManager().closeChatBoxInterface();
					end();
				    }else {
					player.getPackets().sendGameMessage("you dont have enough PvM points.");
					end();
					}
			} else if (componentId == OPTION_4) {
				if (player.Drypoints >= 12000) {
					player.getInventory().addItem(11663, 1);
					player.getInventory().addItem(11664, 1);
					player.getInventory().addItem(11665, 1);
					player.Drypoints -= 12000;
					player.getInventory().refresh();
					player.getInterfaceManager().closeChatBoxInterface();
					end();
				    }else {
					player.getPackets().sendGameMessage("you dont have enough PvM points.");
					end();
					}
			} else if (componentId == OPTION_5) {
				sendOptionsDialogue("Other Stuff",
						"Barrows Gloves(6k)", "Amulet of Ranging (11.5K)",
						"Master Wand (14K)", " Glory (t) (unlimited uses) (5K)", "Nevermind.");
				stage = 5;
			}
		} else if (stage == 5) {
			if (componentId == OPTION_1) {	
				if (player.Drypoints >= 6000) {
					player.getInventory().addItem(7462, 1);
					player.Drypoints -= 6000;
					player.getInventory().refresh();
					player.getInterfaceManager().closeChatBoxInterface();
					end();
				    }else {
					player.getPackets().sendGameMessage("you dont have enough PvM points.");
					end();
					}
			} else if (componentId == OPTION_2) {
				if (player.Drypoints >= 11500) {
					player.getInventory().addItem(15126, 1);
					player.Drypoints -= 11500;
					player.getInventory().refresh();
					player.getInterfaceManager().closeChatBoxInterface();
					end();
				    }else {
					player.getPackets().sendGameMessage("you dont have enough PvM points.");
					end();
					}
			} else if (componentId == OPTION_3) {
				if (player.Drypoints >= 14000) {
					player.getInventory().addItem(6914, 1);
					player.Drypoints -= 14000;
					player.getInventory().refresh();
					player.getInterfaceManager().closeChatBoxInterface();
					end();
				    }else {
					player.getPackets().sendGameMessage("you dont have enough PvM points.");
					end();
					}
			}	else if (componentId == OPTION_4) {
				if (player.Drypoints >= 5000) {
					player.getInventory().addItem(10354, 1);
					player.Drypoints -= 5000;
					player.getInventory().refresh();
					player.getInterfaceManager().closeChatBoxInterface();
					end();
				    }else {
					player.getPackets().sendGameMessage("you dont have enough PvM points.");
					end();
					}
			}	else if (componentId == OPTION_5) {
					end();
					}
		        end();
			}
		}
		

	@Override
	public void finish() {
	}

}
