package com.rs.game.npc.others.sire;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;

@SuppressWarnings("serial")
public class Sire extends NPC {

	public Sire(int id, WorldTile tile, int mapAreaNameHash, boolean canBeAttackFromOutOfArea, boolean spawned) {
		super(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		setLureDelay(0);
		setForceMultiArea(true);
	}

	int count;

	boolean bomb;

	public boolean getBomb() {
		return bomb;
	}

	public int getCount() {
		if (getHitpoints() < 1000 && getBomb() != true) {
			count = 50;
			bomb = true;
		}
		return count;
	}

	public int setCount(int Count) {
		return count = Count;
	}

	@Override
	public double getMeleePrayerMultiplier() {
		return 0.5;
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
					drop();
					setNextAnimation(new Animation(defs.getDeathEmote()));
					transformIntoNPC(36886);
					setCount(0);
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