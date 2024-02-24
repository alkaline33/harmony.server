package com.rs.game.player.dialogues.impl;

import java.io.IOException;

import com.rs.game.player.content.grandExchange.GrandExchangeSystem;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.game.player.interfaces.BuyGeInterface;
import com.rs.game.player.interfaces.SellGeInterface;


public class KeepSake extends Dialogue {

	public KeepSake() {
	}

	@Override
	public void start() {
		stage = 1;
		sendOptionsDialogue("Keepsake options", "Remove Keepsake cape",
				"Remove Keepsake hat", "Remove Keepsake body","Remove Keepsake legs", "Next");

	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == 1) {
			if (componentId == OPTION_1) {
			player.getInventory().addItem(player.keepsakecape, 1);
			player.keepsakecape = 0;
			player.sendMessage("Your keepsaked item has been placed in your inventory.");
			end();
			} else if (componentId == OPTION_2) {
				player.getInventory().addItem(player.keepsakehat, 1);
				player.keepsakehat = 0;
				player.sendMessage("Your keepsaked item has been placed in your inventory.");
				end();
			} else if (componentId == OPTION_3) {
				player.getInventory().addItem(player.keepsakebody, 1);
				player.keepsakebody = 0;
				player.sendMessage("Your keepsaked item has been placed in your inventory.");
				end();
			} else if (componentId == OPTION_4) {
				player.getInventory().addItem(player.keepsakelegs, 1);
				player.keepsakelegs = 0;
				player.sendMessage("Your keepsaked item has been placed in your inventory.");
				end();
			
		} else if (componentId == OPTION_5) {
			sendOptionsDialogue("Keepsake options", "Remove Keepsake weapon",
					"Remove Keepsake amulet", "Remove Keepsake shield","Remove Keepsake gloves", "Remove Keepsake boots");
			stage = 2;
		}
		} else if (stage == 2) {
			if (componentId == OPTION_1) {
			player.getInventory().addItem(player.keepsakeweapon, 1);
			player.keepsakeweapon = 0;
			player.sendMessage("Your keepsaked item has been placed in your inventory.");
			end();
			} else if (componentId == OPTION_2) {
				player.getInventory().addItem(player.keepsakeamulet, 1);
				player.keepsakeamulet = 0;
				player.sendMessage("Your keepsaked item has been placed in your inventory.");
				end();
			} else if (componentId == OPTION_3) {
				player.getInventory().addItem(player.keepsakeshield, 1);
				player.keepsakeshield = 0;
				player.sendMessage("Your keepsaked item has been placed in your inventory.");
				end();
			} else if (componentId == OPTION_4) {
				player.getInventory().addItem(player.keepsakegloves, 1);
				player.keepsakegloves = 0;
				player.sendMessage("Your keepsaked item has been placed in your inventory.");
				end();
			} else if (componentId == OPTION_5) {
				player.getInventory().addItem(player.keepsakeboots, 1);
				player.keepsakeboots = 0;
				player.sendMessage("Your keepsaked item has been placed in your inventory.");
				end();
			}
		
	}
	}
			
	@Override
	public void finish() {
	}

	}
	
