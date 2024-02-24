package com.rs.game.player.dialogues.impl;

import com.rs.game.player.dialogues.Dialogue;

public class AnimationExpert extends Dialogue {

	@Override
	public void start() {
		//npcId = (Integer) parameters[0];
	sendOptionsDialogue("Choose a teleport to activate", "Sky Jump Teleport", "Assassin Teleport","Next Page");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			if (componentId == OPTION_1) {
				if(player.skyjumpteleunlocked == true) {
						player.assassintele = false;
						player.gnometele = false;
						player.skyjumptele = true;
						player.sendMessage("It has been activated.");
				end();
				}
			}
				if (componentId == OPTION_2) {
					if(player.assassinteleunlocked == true) {
						player.assassintele = true;
						player.gnometele = false;
						player.skyjumptele = false;
						player.sendMessage("It has been activated.");
				end();
				}
			}
			if (componentId == OPTION_3) {
				sendOptionsDialogue("Choose an animation to activate", "Roundhouse woodcutting animation", "Chi blast mining animation", "Samurai cooking animation", "Nothing");
				stage =2;
				return;
				}
		}
			if (stage == 2) {
			if (componentId == OPTION_1) {
				if(player.wcskillfuanimunlocked == true) {
					player.wcskillfuanim = true;
					player.wcanim = false;
					player.sendMessage("It has been activated.");
			end();
			}
				}
				if (componentId == OPTION_2) {
				if(player.cookskillfuanimunlocked == true) {
					player.cookskillfuanim = true;
					player.sendMessage("It has been activated.");
			end();
			}
				}
				if (componentId == OPTION_3) {
				if(player.miningskillfuanimunlocked == true) {
					player.miningskillfuanim = true;
					player.minanim = false;
					player.sendMessage("It has been activated.");
			end();
			}
				}
				if (componentId == OPTION_4) {
				end();
				}

		}
	}
		

	@Override
	public void finish() {

	}

}