package com.rs.game.npc.others.osrswildy;

import com.rs.Settings;
import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.ForceTalk;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;

@SuppressWarnings("serial")
public class Vetion extends NPC {

	public Vetion(int id, WorldTile tile, int mapAreaNameHash, boolean canBeAttackFromOutOfArea, boolean spawned) {
		super(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		setForceMultiArea(true);
		setForceMultiAttacked(true);
		setCantFollowUnderCombat(true);
	}

	boolean minionsspawned = false;

	public boolean getMinionSpawn() {
		return minionsspawned;
	}

	public boolean setMinionSpawn(boolean minionspawn) {
		return minionsspawned = minionspawn;
	}

	int count;

	public int getCount() {
		return count;
	}

	public int setCount(int Count) {
		return count = Count;
	}

	/*
	 * gotta override else setRespawnTask override doesnt work
	 */
	@Override
	public void sendDeath(Entity source) {
		final NPCCombatDefinitions defs = getCombatDefinitions();
		if (getId() == 37611) {
			setNextForceTalk(new ForceTalk("Do it again!!!"));
			transformIntoNPC(37612);
			heal(2550);
			return;
		}
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
					if (getId() == 37613)
						Settings.skeletonhellhound++;
				} else if (loop >= defs.getDeathDelay()) {
					reset();
					if (getId() != 37613) {
					setLocation(getRespawnTile());
						setRespawnTask();
						Settings.skeletonhellhound = 0;
						transformIntoNPC(37611);
					}
					finish();
					stop();
				}
				loop++;
			}
		}, 0, 1);
	}

}