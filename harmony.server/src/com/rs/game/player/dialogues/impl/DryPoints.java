package com.rs.game.player.dialogues.impl;

import com.rs.game.player.dialogues.Dialogue;

public class DryPoints extends Dialogue {

	public DryPoints() {
	}

	@Override
	public void start() {
		stage = 1;
		sendOptionsDialogue("Remember Inventory space!", "Fury (or) (15k)",
				"Gnome goggles (3M)", "Onyx Ring (i) (250K)", "Faithful Shield (50K)",
				"Next Page");

	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == 1) {
			if (componentId == OPTION_1) {
				if (player.Drypoints >= 15000) {
				player.getInventory().addItem(19335, 1);
				player.Drypoints -= 15000;
				player.getInventory().refresh();
				player.getInterfaceManager().closeChatBoxInterface();
				end();
			    }else {
				player.getPackets().sendGameMessage("you dont have enough PvM points.");
				end();
				}
			} else if (componentId == OPTION_2) {
				if (player.Drypoints >= 3000000) {
					player.getInventory().addItem(9472, 1);
					player.Drypoints -= 3000000;
					player.getInventory().refresh();
					player.getInterfaceManager().closeChatBoxInterface();
					end();
				    }else {
					player.getPackets().sendGameMessage("you dont have enough PvM points.");
					end();
					}
			} else if (componentId == OPTION_3) {
				if (player.Drypoints >= 250000) {
					player.getInventory().addItem(15017, 1);
					player.Drypoints -= 250000;
					player.getInventory().refresh();
					player.getInterfaceManager().closeChatBoxInterface();
					end();
				    }else {
					player.getPackets().sendGameMessage("you dont have enough PvM points.");
					end();
					}
			} else if (componentId == OPTION_4) {
				if (player.Drypoints >= 50000) {
					player.getInventory().addItem(18747, 1);
					player.Drypoints -= 50000;
					player.getInventory().refresh();
					player.getInterfaceManager().closeChatBoxInterface();
					end();
				    }else {
					player.getPackets().sendGameMessage("you dont have enough PvM points.");
					end();
					}
			} else if (componentId == OPTION_5) {
				sendOptionsDialogue("Remember Inventory space!",
						"Void Knight Top (8k)", "Void Knight Robe (8k)",
						"Void Knight Gloves (6K)", "All 3 Void Knight Helmets (12k)", "Next Page.");
				stage = 4;
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
				sendOptionsDialogue("Remember Inventory space!",
						"Barrows Gloves(6k)", "Amulet of Ranging (11.5K)",
						"Master Wand (14K)", " Fighters Torso (9k)", "Nevermind.");
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
				if (player.Drypoints >= 9000) {
					player.getInventory().addItem(10551, 1);
					player.Drypoints -= 9000;
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
