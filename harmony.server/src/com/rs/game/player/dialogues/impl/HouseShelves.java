package com.rs.game.player.dialogues.impl;

import com.rs.game.Animation;
import com.rs.game.player.dialogues.Dialogue;

public class HouseShelves extends Dialogue {
	
	/**
	 *@author Harrison / Hc747
	 */
	final int larderEmote = 3611; //incorrect, but i can't find the proper one.

	@Override
	public void start() {
		int noober = (Integer) player.getTemporaryAttributtes().get("ShelfType");
		if (noober == 1) {
			sendOptionsDialogue("Which item do you desire?", "Tea kettle.", "Teapot.", "Clay cup.");//wooden 1
		} else if (noober == 2) {
			sendOptionsDialogue("Which item do you desire?", "Tea kettle.", "Teapot.", "Clay cup.", "Beer glass.");//wooden 2
		} else if (noober == 3) {
			sendOptionsDialogue("Which item do you desire?", "Tea kettle.", "Teapot.", "Porcelain cups.", "Beer glass.", "Cake tin.");//wooden 3
		} else if (noober == 4) {
			sendOptionsDialogue("Which item do you desire?", "Tea kettle.", "Teapot.", "Clay cup.", "Beer glass.", "Bowl.");//oak 1
		} else if (noober == 5) {
			sendOptionsDialogue("Which item do you desire?", "Tea kettle.", "Teapot.", "Porcelain cup.", "Beer glass.", "Next.");//oak 2
		} else if (noober == 6) {
			sendOptionsDialogue("Which item do you desire?", "Tea kettle.", "Teapot.", "Porcelain cup.", "Beer glass.", "Next.");//teak 1
		} else if (noober == 7) {
			sendOptionsDialogue("Which item do you desire?", "Tea kettle.", "Teapot.", "Porcelain cup.", "Beer glass.", "Next.");//teak 2
		}
	}

	@Override
	public void run(int interfaceId, int componentId) {
		int noober = (Integer) player.getTemporaryAttributtes().get("ShelfType");
		switch (stage) {
		case -1:
		switch (componentId) {
		case OPTION_1:
			player.lock(1);
			player.setNextAnimation(new Animation(larderEmote));
			player.getInventory().addItem(7688, 1);//kettle - correct
			end();
			break;
		case OPTION_2:
			player.lock(1);
			player.setNextAnimation(new Animation(larderEmote));
			if (noober == 1 || noober == 2 || noober == 4) {
				player.getInventory().addItem(7702, 1);//teapot - clay
			} else if (noober == 3 || noober == 5 || noober == 6) {
				player.getInventory().addItem(7714, 1);//teapot - porcelain
			} else {
				player.getInventory().addItem(7726, 1);//teapot - gilded
			}
			end();
			break;
		case OPTION_3:
			player.lock(1);
			player.setNextAnimation(new Animation(larderEmote));
			if (noober == 1 || noober == 2 || noober == 4) {
				player.getInventory().addItem(7728, 1);//cup - clay
			} else if (noober == 3 || noober == 5 || noober == 6) {
				player.getInventory().addItem(7732, 1);//cup - porcelain
			} else {
				player.getInventory().addItem(7735, 1);//cup - gilded
			}
			end();
			break;
		case OPTION_4:
			player.lock(1);
			player.setNextAnimation(new Animation(larderEmote));
			player.getInventory().addItem(1919, 1);	//beer glass - correct only 2 - 7 have option 4;
			end();
			break;
		case OPTION_5:
			if (noober == 3) {
				player.lock(1);
				player.setNextAnimation(new Animation(larderEmote));
				player.getInventory().addItem(1887, 1);//cake tin
				end();
			} else if (noober == 4) {
				player.lock(1);
				player.setNextAnimation(new Animation(larderEmote));
				player.getInventory().addItem(1923, 1);//bowl
				end();
			} else if (noober == 5) {
				stage = 1;
				sendOptionsDialogue("Which item do you desire?", "Bowl.", "Cake tin.", "Previous.");//oak 2
			} else if (noober == 6) {
				stage = 1;
				sendOptionsDialogue("Which item do you desire?", "Bowl.", "Pie dish.", "Empty pot.", "Previous.");//teak 1
			} else if (noober == 7) {
				stage = 1;
				sendOptionsDialogue("Which item do you desire?", "Bowl.", "Pie dish.", "Empty pot.", "Chef's hat.", "Previous.");//teak 2
			}
			break;
		}
		break;
		case 1:
			switch (componentId) {
			case OPTION_1:
				player.lock(1);
				player.setNextAnimation(new Animation(larderEmote));
				player.getInventory().addItem(1923, 1);//bowl
				end();
				break;
			case OPTION_2:
				player.lock(1);
				player.setNextAnimation(new Animation(larderEmote));
				if (noober == 5) {
					player.getInventory().addItem(1887, 1);//cake tin
				} else if (noober == 6 || noober == 7) {
					player.getInventory().addItem(2313, 1);//pie dish
				}
				end();
				break;
			case OPTION_3:
				if (noober == 5) {
					stage = -1;
					sendOptionsDialogue("Which item do you desire?", "Tea kettle.", "Teapot.", "Porcelain cup.", "Beer glass.", "Next.");//oak 2
				} else if (noober == 6 || noober == 7) {
				player.lock(1);
				player.setNextAnimation(new Animation(larderEmote));
				player.getInventory().addItem(1931, 1);//empty pot
				end();
				}
				break;
			case OPTION_4:
				if (noober == 6) {
					stage = -1;
					sendOptionsDialogue("Which item do you desire?", "Tea kettle.", "Teapot.", "Porcelain cup.", "Beer glass.", "Next.");//teak 1
				} else if (noober == 7) {
				player.lock(1);
				player.setNextAnimation(new Animation(larderEmote));
				player.getInventory().addItem(1949, 1);//chef's hat
				end();
				}
				break;
			case OPTION_5:
				stage = -1;
				sendOptionsDialogue("Which item do you desire?", "Tea kettle.", "Teapot.", "Porcelain cup.", "Beer glass.", "Next.");//teak 2
				break;
			}
			break;
		}
	}

	@Override
	public void finish() {

	}

}
