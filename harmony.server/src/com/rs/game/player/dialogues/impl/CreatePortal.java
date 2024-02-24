package com.rs.game.player.dialogues.impl;

import com.rs.game.player.dialogues.Dialogue;

public class CreatePortal extends Dialogue {

	//int stage = 0;
	
	@Override
	public void start() {
			sendOptionsDialogue("Select the portal you would like to change.",
					"Left Portal.", "Center Portal", "Right Portal", "Never mind.");
			stage = 2;
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == 2) {
			end();
		}
	}
		/*if (componentId == OPTION_1) {
			stage = 3;
			sendOptionsDialogue("Left Portal.",
					"Varrock.", "Lumbridge.", "Falador.", "Camelot.", "Yanille");
		} else if (componentId == OPTION_2) {
			stage = 4;
			sendOptionsDialogue("Center Portal.",
					"Varrock.", "Lumbridge.", "Falador.", "Camelot.", "Yanille");
		} else if (componentId == OPTION_3) {
			stage = 5;
			sendOptionsDialogue("Right Portal",
					"Varrock.", "Lumbridge.", "Falador.", "Camelot.", "Yanille");
		}  else if (componentId == OPTION_4) {
			end();
		}
		} else if (stage == 3) {
			if (componentId == OPTION_1) {
				player.portalTele1 = 1;
			
				end();
			} else if (componentId == OPTION_2) {
				player.portalTele1 = 2;
			
				end();
			} else if (componentId == OPTION_3) {
				player.portalTele1 = 3;
			
				end();
			} else if (componentId == OPTION_4) {
				player.portalTele1 = 4;
	
				end();
			} else if (componentId == OPTION_5) {
				player.portalTele1 = 5;
	
				end();
			}
		} else if (stage == 4) {
			if (componentId == OPTION_1) {
				player.portalTele2 = 6;
		
				end();
			} else if (componentId == OPTION_2) {
				player.portalTele2 = 7;
				
				end();
			} else if (componentId == OPTION_3) {
				player.portalTele2 = 8;
				
				end();
			} else if (componentId == OPTION_4) {
				player.portalTele2 = 9;
				
				end();
			} else if (componentId == OPTION_5) {
				player.portalTele2 = 10;
				
				end();
			}
		} else if (stage == 5) {
			if (componentId == OPTION_1) {
				player.portalTele3 = 11;

				end();
			} else if (componentId == OPTION_2) {
				player.portalTele3 = 12;
	
				end();
			} else if (componentId == OPTION_3) {
				player.portalTele3 = 13;
			
				end();
			} else if (componentId == OPTION_4) {
				player.portalTele3 = 14;
			
				end();
			} else if (componentId == OPTION_5) {
				player.portalTele3 = 15;
				
				end();
			}
		}
		//end();
	}*/

	@Override
	public void finish() {

	}

}
