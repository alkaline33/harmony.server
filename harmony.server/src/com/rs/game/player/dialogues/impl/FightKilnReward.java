package com.rs.game.player.dialogues.impl;

import com.rs.game.player.dialogues.Dialogue;

/**
 * Setting a skill level.
 * 
 * @author Raghav
 * 
 */
public class FightKilnReward extends Dialogue {

	int npcId;

	@Override
	public void start() {
		npcId = (Integer) parameters[0];
		if (player.getEquipment().wearingArmour()) {
			sendDialogue( "Please remove your armour first.");
			stage = -2;
		}else
			sendOptionsDialogue("Choose a skill",
					"Jad Pet", "Tokhaar-Kal-Mej",
					"Tokhaar-Kal-Xil", "Tokhaar-Kal-Ket");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			if (componentId == OPTION_1) {
				player.getInventory().addItem(21512, 1);
				end();
			} else if (componentId == OPTION_2) {
				player.getInventory().addItem(29856, 1);
				end();
			} else if (componentId == OPTION_3) {
				player.getInventory().addItem(29855, 1);
				end();
			} else if (componentId == OPTION_4) {
				player.getInventory().addItem(23659, 1);
				end();
			}
		}
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub

	}

}
