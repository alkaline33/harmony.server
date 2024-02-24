package com.rs.game.npc.others.karuulm;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.Hit;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;

@SuppressWarnings("serial")
public class HydraBoss extends NPC {

	public HydraBoss(int id, WorldTile tile, int mapAreaNameHash, boolean canBeAttackFromOutOfArea, boolean spawned) {
		super(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		setCapDamage(2000);
		setForceFollowClose(true);
	}

	int count;

	// WorldObject bluevent = new WorldObject(29321, count, count, count, count,
	// count);
	// WorldTile greenvent = new WorldTile(1, 1, 0);
	// WorldTile redvent = new WorldTile(1, 1, 0);
	
	int targetX;
	int targetY;
	
	public int getTargetX() {
		return targetX;
	}

	public int setTargetX(int targetx) {
		return targetX = targetx;
	}

	public int getTargetY() {
		return targetY;
	}

	public int setTargetY(int targety) {
		return targetY = targety;
	}
	
	public int getCount() {
		return count;
	}

	public int setCount(int Count) {
		return count = Count;
	}

	@Override
	public void handleIngoingHit(Hit hit) {
		super.handleIngoingHit(hit);
		if (hit.getDamage() > 0 && hit.getDamage() < getHitpoints()) {
			if (getId() == 39615 && !withinDistance(new WorldTile(1371, 10263, 0), 3)) {
				hit.setDamage(hit.getDamage() / 3);
			} else if (getId() == 39619 && !withinDistance(new WorldTile(1371, 10272, 0), 3)) {
				hit.setDamage(hit.getDamage() / 3);
			} else if (getId() == 39620 && !withinDistance(new WorldTile(1362, 10272, 0), 3)) {
				hit.setDamage(hit.getDamage() / 3);
			}
		}
	}
//
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
					transformIntoNPC(39615);
				} else if (loop >= defs.getDeathDelay()) {
					reset();
					setLocation(getRespawnTile());
					finish();
					setRespawnTask();
					stop();
				}
				loop++;
			}
		}, 0, 1);
	}


}