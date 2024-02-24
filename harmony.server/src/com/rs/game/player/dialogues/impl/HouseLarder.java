package com.rs.game.player.dialogues.impl;

import com.rs.game.Animation;
import com.rs.game.player.dialogues.Dialogue;

public class HouseLarder extends Dialogue {
	
	/**
	 *@author Harrison / Hc747
	 */
	final int larderEmote = 3611; //incorrect, but i can't find the proper one... checked 300 or so

	@Override
	public void start() {
		int noober = (Integer) player.getTemporaryAttributtes().get("LarderType");
		if (noober == 1) {
				sendOptionsDialogue("Which ingredient do you desire?", "Tea leaves.", "Bucket of milk.");
		} else if (noober == 2) {
			sendOptionsDialogue("Which ingredient do you desire?", "Tea leaves.", "Bucket of milk.", "Eggs.", "Flour.");
		} else if (noober == 3) {
			sendOptionsDialogue("Which ingredient do you desire?", "Tea leaves.", "Bucket of milk.", "Eggs.", "Flour.", "Next.");
		}
	}

	@Override
	public void run(int interfaceId, int componentId) {
		switch (stage) {
		case -1:
		switch (componentId) {
		case OPTION_1:
			player.lock(1);
			player.setNextAnimation(new Animation(larderEmote));
			player.getInventory().addItem(7738, 1);
			end();
			break;
		case OPTION_2:
			player.lock(1);
			player.setNextAnimation(new Animation(larderEmote));
			player.getInventory().addItem(1927, 1);
			end();
			break;
		case OPTION_3:
			player.lock(1);
			player.setNextAnimation(new Animation(larderEmote));
			player.getInventory().addItem(1944, 1);
			end();
			break;
		case OPTION_4:
			player.lock(1);
			player.setNextAnimation(new Animation(larderEmote));
			player.getInventory().addItem(1933, 1);
			end();
			break;
		case OPTION_5:
			stage = 1;
			sendOptionsDialogue("Which ingredient do you desire?", "Potatoes.", "Garlic.", "Onions", "Cheese.", "Previous.");
			break;
		}
		break;
		case 1:
			switch (componentId) {
			case OPTION_1:
				player.lock(1);
				player.setNextAnimation(new Animation(larderEmote));
				player.getInventory().addItem(1942, 1);
				end();
				break;
			case OPTION_2:
				player.lock(1);
				player.setNextAnimation(new Animation(larderEmote));
				player.getInventory().addItem(1550, 1);
				end();
				break;
			case OPTION_3:
				player.lock(1);
				player.setNextAnimation(new Animation(larderEmote));
				player.getInventory().addItem(1957, 1);
				end();
				break;
			case OPTION_4:
				player.lock(1);
				player.setNextAnimation(new Animation(larderEmote));
				player.getInventory().addItem(1985, 1);
				end();
				break;
			case OPTION_5:
				stage = -1;
				sendOptionsDialogue("Which ingredient do you desire?", "Tea leaves.", "Bucket of milk.", "Eggs.", "Flour.", "Next.");
				break;
			}
			break;
		}
	}

	@Override
	public void finish() {

	}

}
