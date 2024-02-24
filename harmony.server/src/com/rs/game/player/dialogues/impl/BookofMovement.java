package com.rs.game.player.dialogues.impl;

import com.rs.game.player.EmotesManager;
import com.rs.game.player.dialogues.Dialogue;

public class BookofMovement extends Dialogue {

	@Override
	public void start() {
		//npcId = (Integer) parameters[0];
	sendOptionsDialogue("Choose an animation to unlock", "Sky Jump Teleport", "Assassin Teleport", "Break dance emote", "Backflip emote", "Next Page");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			if (componentId == OPTION_1) {
				if (player.skyjumpteleunlocked == true) {
					player.sendMessage("You have already unlocked this.");
					end();
					return;
				}
				if(player.getInventory().containsItem(29690, 1)) {
						player.getInventory().deleteItem(29690, 1);
						player.skyjumpteleunlocked = true;
						player.sendMessage("Visit the animation expert to activate.");
				end();
				}
			} else 
				if (componentId == OPTION_2) {
					if (player.assassinteleunlocked == true) {
						player.sendMessage("You have already unlocked this.");
						end();
						return;
					}
					if(player.getInventory().containsItem(29690, 1)) {
						player.getInventory().deleteItem(29690, 1);
						player.assassinteleunlocked = true;
						player.sendMessage("Visit the animation expert to activate.");
					end();
					}
		} else 
			if (componentId == OPTION_3) {
				if (player.breakdanceemoteunlocked == true) {
					player.sendMessage("You have already unlocked this.");
					end();
					return;
				}
				if(player.getInventory().containsItem(29690, 1)) {
					player.getInventory().deleteItem(29690, 1);
					player.breakdanceemoteunlocked = true;
					player.sendMessage("Visit the emotes tab to use.");
				end();
				}
			} else 
				if (componentId == OPTION_4) {
					if (player.backflipemoteunlocked == true) {
						player.sendMessage("You have already unlocked this.");
						end();
						return;
					}
					if(player.getInventory().containsItem(29690, 1)) {
						player.getInventory().deleteItem(29690, 1);
						player.backflipemoteunlocked = true;
						player.sendMessage("Visit the emotes tab to use.");
					end();
					}
				}
		
		else if (componentId == OPTION_5) {
					sendOptionsDialogue("Choose an animation to unlock", "Roundhouse woodcutting animation", "Chi blast mining animation", "Samurai cooking animation", "Nothing");
		stage =2;
		}
	}
			else if (stage == 2) {
			if (componentId == OPTION_1) {
				if (player.wcskillfuanimunlocked == true) {
					player.sendMessage("You have already unlocked this.");
					end();
					return;
				}
				if(player.getInventory().containsItem(29690, 1)) {
						player.getInventory().deleteItem(29690, 1);
						player.wcskillfuanimunlocked = true;
						player.sendMessage("Visit the animation expert to activate.");
				end();
				}
			}
			
			else if (componentId == OPTION_2) {
				if (player.miningskillfuanimunlocked == true) {
					player.sendMessage("You have already unlocked this.");
					end();
					return;
				}
				if(player.getInventory().containsItem(29690, 1)) {
						player.getInventory().deleteItem(29690, 1);
						player.miningskillfuanimunlocked = true;
						player.sendMessage("Visit the animation expert to activate.");
				end();
				}	
			}
			else if (componentId == OPTION_3) {
				if (player.cookskillfuanimunlocked == true) {
					player.sendMessage("You have already unlocked this.");
					end();
					return;
				}
				if(player.getInventory().containsItem(29690, 1)) {
						player.getInventory().deleteItem(29690, 1);
						player.cookskillfuanimunlocked = true;
						player.sendMessage("Visit the animation expert to activate.");
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