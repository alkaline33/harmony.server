package com.rs.game.player.dialogues.impl;

import com.rs.game.player.Skills;
import com.rs.game.player.dialogues.Dialogue;


public class Dungeoneering extends Dialogue {

	public Dungeoneering() {
		
	}

	@Override
	public void start() {
		stage = 1;
		sendOptionsDialogue("Pick a Dungeoneering Reward", "Chaotic Rapier (200K)",
				"Chaotic Maul (200K)", "Chaotic Staff (200K)", "Chaotic Crossbow (200K)",
				"Next Page");

	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == 1) {
			if (componentId == OPTION_1) {
				if (player.dungpoints >= 200000 && player.getInventory().getFreeSlots() > 0) {
				player.getInventory().addItem(18350, 1);
				player.dungpoints -= 200000;
				player.getInventory().refresh();
				player.getInterfaceManager().closeChatBoxInterface();
				player.getPackets().sendGameMessage("We're aware that the chaotic rapier says (broken). Please ignore it, it doesn't effect it in any way.");
				end();
			    }else {
				player.getPackets().sendGameMessage("Please make sure you have the correct amount of points and free inventory space.");
				end();
				}
			} else if (componentId == OPTION_2) {
				if (player.dungpoints >= 200000) {
				player.getInventory().addItem(18353, 1);
				player.dungpoints -= 200000;
				player.getInventory().refresh();
				player.getInterfaceManager().closeChatBoxInterface();
				end();
			    }else {
					player.getPackets().sendGameMessage("Please make sure you have the correct amount of points and free inventory space.");
				end();
				}
			} else if (componentId == OPTION_3) {
				if (player.dungpoints >= 200000 && player.getInventory().getFreeSlots() > 0) {
				player.getInventory().addItem(18355, 1);
				player.dungpoints -= 200000;
				player.getInventory().refresh();
				player.getInterfaceManager().closeChatBoxInterface();
				end();
			    }else {
					player.getPackets().sendGameMessage("Please make sure you have the correct amount of points and free inventory space.");
				end();
				}
			} else if (componentId == OPTION_4) {
				if (player.dungpoints >= 200000 && player.getInventory().getFreeSlots() > 0) {
				player.getInventory().addItem(18357, 1);
				player.dungpoints -= 200000;
				player.getInventory().refresh();
				player.getInterfaceManager().closeChatBoxInterface();
				end();
			    }else {
					player.getPackets().sendGameMessage("Please make sure you have the correct amount of points and free inventory space.");
				end();
				}
			} else if (componentId == OPTION_5) {
				sendOptionsDialogue("Pick a Dungeoneering Reward",
						"Chaotic Longsword(200k)", "Arcane Stream Necklace (50K)",
						"Ring of Vigour (75K)", "Farseer kiteshield (200K)", "Next Page.");
				stage = 4;
			}
		} else if (stage == 4) {
			if (componentId == OPTION_1) {	
				if (player.dungpoints >= 200000 && player.getInventory().getFreeSlots() > 0) {
				player.getInventory().addItem(18352, 1);
				player.dungpoints -= 200000;
				player.getInventory().refresh();
				player.getInterfaceManager().closeChatBoxInterface();
				player.getPackets().sendGameMessage("We're aware that the chaotic longsword says (broken). Please ignore it, it doesn't effect it in any way.");
				end();
			    }else {
					player.getPackets().sendGameMessage("Please make sure you have the correct amount of points and free inventory space.");
				end();
				}
			} else if (componentId == OPTION_2) {
				if (player.dungpoints >= 50000 && player.getInventory().getFreeSlots() > 0) {
				player.getInventory().addItem(18335, 1);
				player.dungpoints -= 50000;
				player.getInventory().refresh();
				player.getInterfaceManager().closeChatBoxInterface();
				end();
			    }else {
					player.getPackets().sendGameMessage("Please make sure you have the correct amount of points and free inventory space.");
				end();
				}
			} else if (componentId == OPTION_3) {
				if (player.dungpoints >= 75000 && player.getInventory().getFreeSlots() > 0) {
				player.getInventory().addItem(19669, 1);
				player.dungpoints -= 75000;
				player.getInventory().refresh();
				player.getInterfaceManager().closeChatBoxInterface();
				end();
			    }else {
			    	player.getPackets().sendGameMessage("Please make sure you have the correct amount of points and free inventory space.");
				end();
				}
			} else if (componentId == OPTION_4) {
				if (player.dungpoints >= 200000 && player.getInventory().getFreeSlots() > 0) {
				player.getInventory().addItem(18363, 1);
				player.dungpoints -= 200000;
				player.getInventory().refresh();
				player.getInterfaceManager().closeChatBoxInterface();
				end();
			    }else {
			    	player.getPackets().sendGameMessage("Please make sure you have the correct amount of points and free inventory space.");
				end();
				}
			} else if (componentId == OPTION_5) {
				sendOptionsDialogue("Pick a Dungeoneering Reward",
						"Chaotic KiteShield(200k)", "Eagle-eye Kiteshield (200K)",
						"Mercenary Gloves (100K)", "Bone Crusher (100K)", "Next page.");
				stage = 5;
			}
		} else if (stage == 5) {
			if (componentId == OPTION_1) {	
				if (player.dungpoints >= 200000 && player.getInventory().getFreeSlots() > 0) {
				player.getInventory().addItem(18359, 1);
				player.dungpoints -= 200000;
				player.getInventory().refresh();
				player.getInterfaceManager().closeChatBoxInterface();
				end();
			    }else {
			    	player.getPackets().sendGameMessage("Please make sure you have the correct amount of points and free inventory space.");
				end();
				}
			} else if (componentId == OPTION_2) {
				if (player.dungpoints >= 200000 && player.getInventory().getFreeSlots() > 0) {
				player.getInventory().addItem(18361, 1);
				player.dungpoints -= 200000;
				player.getInventory().refresh();
				player.getInterfaceManager().closeChatBoxInterface();
				end();
			    }else {
			    	player.getPackets().sendGameMessage("Please make sure you have the correct amount of points and free inventory space.");
				end();
				}
			} else if (componentId == OPTION_3) {
				if (player.dungpoints >= 100000 && player.getInventory().getFreeSlots() > 0) {
				player.getInventory().addItem(18347, 1);
				player.dungpoints -= 100000;
				player.getInventory().refresh();
				player.getInterfaceManager().closeChatBoxInterface();
				end();
			    }else {
			    	player.getPackets().sendGameMessage("Please make sure you have the correct amount of points and free inventory space.");
				end();
				}
			}	else if (componentId == OPTION_4) {
					if (player.dungpoints >= 100000 && player.getInventory().getFreeSlots() > 0) {
					player.getInventory().addItem(18337, 1);
					player.dungpoints -= 100000;
					player.getInventory().refresh();
					player.getInterfaceManager().closeChatBoxInterface();
					end();
				    }else {
				    	player.getPackets().sendGameMessage("Please make sure you have the correct amount of points and free inventory space.");
					end();
					}
			}	else if (componentId == OPTION_5) {
				sendOptionsDialogue("Pick a Dungeoneering Reward",
						"(99)Dungeoneering Cape (200k)", "(120)Dungeoneering Cape (120k)",
						"Charm Collector (300k)", "Sneakerpeeper Pet (1M)", "Next");
				stage = 6;
			}
		} else if (stage == 6) {
			if (componentId == OPTION_1) {	
				if (player.getSkills().getLevelForXp(Skills.DUNGEONEERING) < 99) {
					player.getPackets().sendGameMessage(
							"You need a dungeoneering level of 99 to buy this cape.");
					end();
					return;
				} else
				if (player.dungpoints >= 200000 && player.getInventory().getFreeSlots() > 0) {
				player.getInventory().addItem(18509, 1);
				player.dungpoints -= 200000;
				player.getInventory().refresh();
				player.getInterfaceManager().closeChatBoxInterface();
				end();
			    }else {
			    	player.getPackets().sendGameMessage("Please make sure you have the correct amount of points and free inventory space.");
				end();
				}
			} else if (componentId == OPTION_2) {
				if (player.getSkills().getLevelForXp(Skills.DUNGEONEERING) < 120) {
					player.getPackets().sendGameMessage(
							"You need a dungeoneering level of 120 to buy this cape.");
					end();
					return;
				} else
				if (player.dungpoints >= 120000 && player.getInventory().getFreeSlots() > 0) {
				player.getInventory().addItem(19709, 1);
				player.dungpoints -= 120000;
				player.getInventory().refresh();
				player.getInterfaceManager().closeChatBoxInterface();
				end();
			    }else {
			    	player.getPackets().sendGameMessage("Please make sure you have the correct amount of points and free inventory space.");
				end();
				}
			} else if (componentId == OPTION_3) {
				if (player.dungpoints >= 300000 && player.getSkills().getLevelForXp(Skills.SUMMONING) > 69 && player.getInventory().getFreeSlots() > 0) {
					//player.getInventory().addItem(18337, 1);
					player.dungpoints -= 300000;
					player.charmc = true;
					player.charmcb = true;
					player.charmcc = true;
					player.charmcgg = true;
					player.charmcg = true;
					player.sendMessage("<col=000000>You now have the ability to collect charms without picking them up.");
				player.getInventory().refresh();
				player.getInterfaceManager().closeChatBoxInterface();
				end();
			    }else {
			    	player.getPackets().sendGameMessage("Please make sure you have the correct amount of points and free inventory space.");
				end();
				}
			}	else if (componentId == OPTION_4) {
				 //if (player.getSkills().getLevelForXp(Skills.DUNGEONEERING) != 120) {
				if (player.dungpoints >= 1000000 && player.getSkills().getLevelForXp(Skills.DUNGEONEERING) > 112 && player.getInventory().getFreeSlots() > 0) {	
					player.getInventory().addItem(19894, 1);
					player.dungpoints -= 1000000;
					player.getInventory().refresh();
					player.getInterfaceManager().closeChatBoxInterface();
					end();
				    }else {
					player.getPackets().sendGameMessage("You need 1,000,000 dungeoneering points and 113+ dungeoneering to purchase this.");
					end();
				    }
		}	else if (componentId == OPTION_5) {
			sendOptionsDialogue("Pick a Dungeoneering Reward",
					"Chaotic Spike (200k)", "Bar Bag (300k)", "Nevermind");
			stage = 7;
		}
	} else if (stage == 7) {
		if (componentId == OPTION_1) {	
			if (player.dungpoints >= 200000 && player.getInventory().getFreeSlots() > 0) {
			player.getInventory().addItem(27068, 1);
			player.dungpoints -= 200000;
			player.getInventory().refresh();
			player.getInterfaceManager().closeChatBoxInterface();
			end();
		    }else {
		    	player.getPackets().sendGameMessage("Please make sure you have the correct amount of points and free inventory space.");
			end();
			}
		} else if (componentId == OPTION_2) {
			if (player.dungpoints >= 300000 && player.getInventory().getFreeSlots() > 0) {
				player.getInventory().addItem(29793, 1);
				player.dungpoints -= 300000;
				player.getInventory().refresh();
				player.getInterfaceManager().closeChatBoxInterface();
				end();
			    }else {
			    	player.getPackets().sendGameMessage("Please make sure you have the correct amount of points and free inventory space.");
				end();
			    }
		}else if (componentId == OPTION_3) {
			end();
		}
		}
		
	
			
	}
			
	
	
		

	@Override
	public void finish() {
	}

}
