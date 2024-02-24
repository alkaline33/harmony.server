package com.rs.game.player.dialogues.impl;

import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.player.Player;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;

public class StaffViewingOrb extends Dialogue {


	@Override
	public void start() {

		sendOptionsDialogue("Are you sure you want continue?</col>?", "Yes!", "No thanks.");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		String value = (String) parameters[0];
		Player target = null;
		if (stage == -1) {
			if (componentId == OPTION_1) {
				target = World.getPlayerByDisplayName(value);
				if (target == null) {
					player.sendMessage("This player isn't online!");
					end();
					return;
				}
				player.getAppearence().switchHidden();
				player.setNextWorldTile(target);
				player.lock();
				player.sendMessage("You watch on.....");
				player.staffviewingorb = "NULL";
				WorldTasksManager.schedule(new WorldTask() {
					int loop;

					@Override
					public void run() {
						if (loop == 30) {
							player.setNextWorldTile(new WorldTile(2655, 10072, 2));
							player.unlock();
							player.getAppearence().switchHidden();
							player.sendMessage("Your viewing session has expired.");
							stop();
						}
						loop++;
					}
				}, 0, 1);
				end();
				stage = 3;
				return;
			} else {
				end();
				stage = 3;
			}
		} else if (stage == 2) {
			if (componentId == OPTION_2) {
				stage = 3;
				end();
				return;
			}
		} else if (stage == 3) {
			end();
		}

	}

	@Override
	public void finish() {

	}

}