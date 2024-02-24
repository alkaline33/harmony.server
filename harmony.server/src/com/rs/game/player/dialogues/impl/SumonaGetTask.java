package com.rs.game.player.dialogues.impl;

import com.rs.Settings;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.player.Skills;
import com.rs.game.player.SlayerTask;
import com.rs.game.player.SlayerTask.Master;
import com.rs.game.player.content.Magic;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.utils.ShopsHandler;

public class SumonaGetTask extends Dialogue {

	int npcId;

	@Override
	public void start() {
		if (player.getTask() == null) {
			if (player.getSkills().getLevelForXp(Skills.SLAYER) < 50) {
				SlayerTask.random(player, Master.SUMONA);
				sendEntityDialogue(IS_NPC, "Sumona", 7780, 9827,
						"Excellent, you're doing great. Your new task is to kill "
								+ player.getTask().getTaskAmount() + " "
								+ player.getTask().getName().toLowerCase()
								+ "s.");
				player.setTalkedWithKuradal();
				stage = 7;
				//player.sendMessage("1");
			} else if (player.getSkills().getLevelForXp(Skills.SLAYER) >= 50 && player.getSkills().getLevelForXp(Skills.SLAYER) < 80) {
				SlayerTask.random(player, Master.SUMONA);
				sendEntityDialogue(IS_NPC, "Sumona", 7780, 9827,
						"Excellent, you're doing great. Your new task is to kill "
								+ player.getTask().getTaskAmount() + " "
								+ player.getTask().getName().toLowerCase()
								+ "s.");
				player.setTalkedWithKuradal();
				stage = 7;
				//player.sendMessage("2");
			} else if (player.getSkills().getLevelForXp(Skills.SLAYER) >= 80) {
			SlayerTask.random(player, Master.SUMONA);
			sendEntityDialogue(IS_NPC, "Sumona", 7780, 9827,
					"Excellent, you're doing great. Your new task is to kill "
							+ player.getTask().getTaskAmount() + " "
							+ player.getTask().getName().toLowerCase()
							+ "s.");
			player.setTalkedWithKuradal();
			stage = 7;
			//player.sendMessage("3");
			}
		} else {
			sendEntityDialogue(IS_NPC, "Sumona", 7780, 9827,
					"You're still hunting " + player.getTask().getName().toLowerCase() + "s; come back when you've finished your task.");
			player.setTalkedWithKuradal();
			stage = 7;
		}
	}

	@Override
	public void run(int interfaceId, int componentId) {
		switch (stage) {
		case 7: /* Offical end of Dialogue */
			end();
			break;
		}
	}
	

	@Override
	public void finish() {
		// TODO Auto-generated method stub

	}

}