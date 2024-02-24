package com.rs.game.player.dialogues.impl;

import com.rs.game.player.dialogues.Dialogue;
import com.rs.utils.ShopsHandler;

public class Xuans extends Dialogue {

	private int npcId = 13727;

	@Override
	public void start() {
		//npcId = (Integer) parameters[0];
		sendNPCDialogue(
				npcId,
				9845,
				"Hey "
						+ player.getUsername()
						+ ", I'm Xuan. I'm in charge of the Loyalty Programme! What can I do for you?");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			sendOptionsDialogue("Xuan", 
					"Show me your Aura Shop!",
					"How many Loyalty Points do I have?",
					"Show me your new Aura Shop!",
					"Could I choose a free title please?",
					"Could I choose a loyalty point title please?");
			stage = 2;
		} else if (stage == 2) {
			if (componentId == OPTION_1) {
				ShopsHandler.openShop(player, 31);
				sendNPCDialogue(npcId, 9845, "Come back soon! You have "
						+ player.getLoyaltyPoints() + " Loyalty Points left.");
				end();
			}
			if (componentId == OPTION_5) {
				sendOptionsDialogue("Loyalty Point Titles", 
						"Harmony addict (2.5k)",
						"Sexy (2k)",
						"The Dictator (3k)",
						"The Baws (3k)",
						"More Titles..");
				stage = 5;
			}
			if (componentId == OPTION_3) {
				ShopsHandler.openShop(player, 33);
				end();
			}
			if (componentId == OPTION_4) {
				sendOptionsDialogue("Free Titles", 
						"Sir",
						"Lord",
						"Duderino",
						"Hellraiser",
						"Next page");
				stage = 4;
			}
			if (componentId == OPTION_2) {
				sendNPCDialogue(npcId, 9845,
						"You currently have " + player.getLoyaltyPoints()
								+ " Loyalty Points.");
				stage = 3;
			}
		} else if (stage == 7) {
			if (componentId == OPTION_1) {
				player.getAppearence().setTitle(701);
				end();
				stage = 3;
			}
			if (componentId == OPTION_2 && player.ishard == true) {
				player.getAppearence().setTitle(1610);
				end();
				stage = 3;
			} else {
				player.sendMessage("You need to have hard mode activated to use this title.");
			}
		} else if (stage == 4) {
			if (componentId == OPTION_1) {
				player.getAppearence().setTitle(5);
				end();
				stage = 3;
			}
			if (componentId == OPTION_5) {
				sendOptionsDialogue("Free Titles", 
						"Player",
						"Hardcore",
						//"Duderino",
						//"Hellraiser",
						"Nothing");
				stage = 7;
			}
			if (componentId == OPTION_3) {
				player.getAppearence().setTitle(7);
				end();
				stage = 3;
			}
			if (componentId == OPTION_4) {
				player.getAppearence().setTitle(9);
				end();
				stage = 3;
			}
			if (componentId == OPTION_2) {
				player.getAppearence().setTitle(6);
				end();
				stage = 3;
			}
		} else if (stage == 5) {
			if (componentId == OPTION_1) {
				if (player.Loyaltypoints <= 2500) {
					sendNPCDialogue(
							npcId,
							9827,
							"I'm sorry but it seems you don't have enough loyalty points for this title.");
				stage = 3;
					return;
				}
				player.Loyaltypoints -= 2500;
				player.getAppearence().setTitle(61);
				end();
				stage = 3;
			}
			if (componentId == OPTION_5) {
				sendOptionsDialogue("Loyalty Point Titles", 
						"IpBanned(2k)",
						"Skiller (2.5k)",
						"I love (3k)",
						"No-mates (3k)",
						"Loyalty Master (25k)");
				stage = 6;
			}
			if (componentId == OPTION_3) {
				if (player.Loyaltypoints <= 3000) {
					sendNPCDialogue(
							npcId,
							9827,
							"I'm sorry but it seems you don't have enough loyalty points for this title.");
				stage = 3;
					return;
				}
		player.Loyaltypoints -= 3000;
		player.getAppearence().setTitle(63);
		end();
		stage = 3;
	}
						
			if (componentId == OPTION_4) {
				if (player.Loyaltypoints <= 3000) {
					sendNPCDialogue(
							npcId,
							9827,
							"I'm sorry but it seems you don't have enough loyalty points for this title.");
				stage = 3;
					return;
				}
				player.Loyaltypoints -= 3000;
				player.getAppearence().setTitle(64);
				end();
				stage = 3;
			}
			if (componentId == OPTION_2) {
				if (player.Loyaltypoints <= 2000) {
					sendNPCDialogue(
							npcId,
							9827,
							"I'm sorry but it seems you don't have enough loyalty points for this title.");
				stage = 3;
					return;
				}
				player.Loyaltypoints -= 2000;
				player.getAppearence().setTitle(62);
				end();
				stage = 3;
			}
		} else if (stage == 6) {
			if (componentId == OPTION_1) {
				if (player.Loyaltypoints <= 2000) {
					sendNPCDialogue(
							npcId,
							9827,
							"I'm sorry but it seems you don't have enough loyalty points for this title.");
				stage = 3;
					return;
				}
				player.Loyaltypoints -= 2500;
				player.getAppearence().setTitle(706);
				end();
				stage = 3;
			}
			if (componentId == OPTION_5) {
				if (player.Loyaltypoints <= 25000) {
					sendNPCDialogue(
							npcId,
							9827,
							"I'm sorry but it seems you don't have enough loyalty points for this title.");
				stage = 3;
					return;
				}
				player.Loyaltypoints -= 25000;
				player.getAppearence().setTitle(703);
				end();
				stage = 3;
			}
			if (componentId == OPTION_3) {
				if (player.Loyaltypoints <= 3000) {
					sendNPCDialogue(
							npcId,
							9827,
							"I'm sorry but it seems you don't have enough loyalty points for this title.");
				stage = 3;
					return;
				}
		player.Loyaltypoints -= 3000;
		player.getAppearence().setTitle(704);
		end();
		stage = 3;
	}
						
			if (componentId == OPTION_4) {
				if (player.Loyaltypoints <= 3000) {
					sendNPCDialogue(
							npcId,
							9827,
							"I'm sorry but it seems you don't have enough loyalty points for this title.");
				stage = 3;
					return;
				}
				player.Loyaltypoints -= 3000;
				player.getAppearence().setTitle(50);
				end();
				stage = 3;
			}
			if (componentId == OPTION_2) {
				if (player.Loyaltypoints <= 2500) {
					sendNPCDialogue(
							npcId,
							9827,
							"I'm sorry but it seems you don't have enough loyalty points for this title.");
				stage = 3;
					return;
				}
				player.Loyaltypoints -= 2500;
				player.getAppearence().setTitle(705);
				end();
				stage = 3;
			}
		} else if (stage == 3) {
			end();
		}
	}


	@Override
	public void finish() {

	}

}