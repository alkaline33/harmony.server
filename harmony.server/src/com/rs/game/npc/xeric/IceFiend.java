package com.rs.game.npc.xeric;

import com.rs.Settings;
import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.player.Player;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;

@SuppressWarnings("serial")
public class IceFiend extends NPC {

	public IceFiend(int id, WorldTile tile, int mapAreaNameHash, boolean canBeAttackFromOutOfArea, boolean spawned) {
		super(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		setLureDelay(0);
		setForceMultiArea(true);
	}

	int count;

	public boolean frozenyet;

	public int getCount() {
		return count;
	}

	public int setCount(int Count) {
		return count = Count;
	}

	@Override
	public double getRangePrayerMultiplier() {
		return 0.2;
	}

	/*
	 * gotta override else setRespawnTask override doesnt work
	 */
	@Override
	public void sendDeath(Entity source) {
		final NPCCombatDefinitions defs = getCombatDefinitions();
		resetWalkSteps();
		getCombat().removeTarget();
		setNextAnimation(null);
		WorldTasksManager.schedule(new WorldTask() {
			int loop;

			@Override
			public void run() {

				if (loop == 0) {
					//drop();
					for (Player player : World.getPlayers()) {
						if (!World.IceFiendRaids(player)) 
							continue;
						if (Settings.ICEBOSSOPEN > 4 && Settings.eventdoublecaskets == 0)
							continue;
						Settings.ICEBOSSOPEN++;
					}
					setNextAnimation(new Animation(defs.getDeathEmote()));
				} else if (loop >= defs.getDeathDelay()) {
					reset();
					setLocation(getRespawnTile());
					finish();
					// drop();
					// drop();
					setRespawnTask();
					stop();
				}
				loop++;
			}
		}, 0, 1);
	}

}