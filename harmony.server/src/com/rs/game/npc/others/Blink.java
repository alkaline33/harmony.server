package com.rs.game.npc.others;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;

/**
 * Dungeonnering boss, not actual combat script tho
 * 
 * @author cache[j].standAnimation = 14948; cache[j].walkAnimation = 14950;
 */
@SuppressWarnings("serial")
public class Blink extends NPC {

	// 12878
	public Blink(int id, WorldTile tile, int mapAreaNameHash,
			boolean canBeAttackFromOutOfArea, boolean spawned) {
		super(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		setForceMultiAttacked(true);
		setForceAgressive(true);
		setLureDelay(0);
		setRun(true);
		setCapDamage(1000);
		setCanFreezeThis(false);

	}

	@Override
	public double getMagePrayerMultiplier() {
		return 0.6;
	}
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
					drop();
					setNextAnimation(new Animation(defs.getDeathEmote()));
				} else if (loop >= defs.getDeathDelay()) {
					reset();
					//setLocation(getRespawnTile());
					finish();
					//setRespawnTask();
					stop();
				}
				loop++;
			}
		}, 0, 1);
	}
}