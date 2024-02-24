package com.rs.game.player.dialogues.impl;

import java.io.IOException;

import com.rs.game.player.content.grandExchange.GrandExchangeSystem;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.game.player.interfaces.BuyGeInterface;
import com.rs.game.player.interfaces.SellGeInterface;


public class GeInterfaces extends Dialogue {

	public GeInterfaces() {
	}

	@Override
	public void start() {
		stage = 1;
		sendOptionsDialogue("Grand Exchange Options", "Main Grand Exchange",
				"Buy Offers", "Sale Offers", "Nothing");

	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == 1) {
			if (componentId == OPTION_1) {
				GrandExchangeSystem.get().display(player);
					end();
					return;
				
			} else if (componentId == OPTION_2) {
				try {
					BuyGeInterface.sendInterface(player);
					end();
					return;
				} catch (IOException e) {
					player.sendMessage("An error has occured, please inform a staff member.");
					e.printStackTrace();
				}
			} else if (componentId == OPTION_3) {
				try {
					SellGeInterface.sendInterface(player);
					end();
					return;
				} catch (IOException e) {
					player.sendMessage("An error has occured, please inform a staff member.");
					e.printStackTrace();
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
	
