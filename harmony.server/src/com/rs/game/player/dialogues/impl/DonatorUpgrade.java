package com.rs.game.player.dialogues.impl;

import com.rs.game.player.dialogues.Dialogue;

public class DonatorUpgrade extends Dialogue {

	public DonatorUpgrade() {
	}

	@Override
	public void start() {
		stage = 1;
		sendOptionsDialogue("Select Upgrade", "Super Donator", "Extreme Donator", "Ultimate Donator", "Legendary Donator", "Next Page");

	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == 1) {
			if (componentId == OPTION_1) {
				if (player.donator == true && player.donationvalue >= 25) {
					player.donator = false;
					player.superDonator = true;
					player.getPackets().sendGameMessage("You have been rewarded super donator, thank you.");
					end();
					return;
				}
				else {
					player.getPackets().sendGameMessage("You need to be a normal donator and have donated $25 for this rank.");
					end();
				}
			} else if (componentId == OPTION_2) {
				if (player.superDonator == true && player.donationvalue >= 50) {
					player.donator = false;
					player.superDonator = false;
					player.extremeDonator = true;
					player.getPackets().sendGameMessage("You have been rewarded extreme donator, thank you.");
					end();
					return;
				}
				else {
					player.getPackets().sendGameMessage("You need to be a super donator and have donated $50 for this rank.");
					end();
				}
			} else if (componentId == OPTION_3) {
				if (player.extremeDonator == true && player.donationvalue >= 100) {
					player.donator = false;
					player.superDonator = false;
					player.extremeDonator = false;
					player.ultimateDonator = true;
					player.getPackets().sendGameMessage("You have been rewarded ultimate donator, thank you.");
					end();
					return;
				} else {
					player.getPackets().sendGameMessage("You need to be an extreme donator and have donated $100 for this rank.");
					end();
				}
			} else if (componentId == OPTION_4) {
				if (player.ultimateDonator == true && player.donationvalue >= 250) {
					player.donator = false;
					player.superDonator = false;
					player.extremeDonator = false;
					player.ultimateDonator = false;
					player.legendaryDonator = true;
					player.getPackets().sendGameMessage("You have been rewarded legendary donator, thank you.");
					end();
					return;
				}
				else {
					player.getPackets().sendGameMessage("You need to be an Ultimate donator and have donated $250 for this rank.");
					end();
				}
			} else if (componentId == OPTION_5) {
				sendOptionsDialogue("Select Upgrade", "V.I.P", "Sponsor", "Nothing");
				stage = 6;
			}
			} else if (stage == 6) {
			if (componentId == OPTION_1) {
				if (player.legendaryDonator == true && player.donationvalue >= 500) {
					player.donator = false;
					player.superDonator = false;
					player.extremeDonator = false;
					player.ultimateDonator = false;
					player.legendaryDonator = false;
					player.vipDonator = true;
					player.getPackets().sendGameMessage("You have been rewarded V.I.P Status, thank you.");
					end();
					return;
				}
				else {
					player.getPackets().sendGameMessage("You need to be an legendary donator and have donated $500 for this rank.");
					end();
					}
				} else if (componentId == OPTION_2) {
					if (player.vipDonator == true && player.donationvalue >= 1000) {
						player.donator = false;
						player.superDonator = false;
						player.extremeDonator = false;
						player.ultimateDonator = false;
						player.legendaryDonator = false;
						player.vipDonator = false;
						player.sponsorDonator = true;
						player.getPackets().sendGameMessage("You have been rewarded Sponsor Status, thank you.");
						end();
						return;
					}
					else {
						player.getPackets().sendGameMessage("You need to be an VIP donator and have donated $1000 for this rank.");
						end();
						}
				}
				
			}
		}
		
		

	@Override
	public void finish() {
	}

}
