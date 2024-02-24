package com.rs.game.npc.others;

import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;

@SuppressWarnings("serial")
public class Celestia extends NPC {

	public Celestia(int id, WorldTile tile, int mapAreaNameHash,
			boolean canBeAttackFromOutOfArea, boolean spawned) {
		super(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		setCapDamage(800);
		setForceTargetDistance(64);
		setForceFollowClose(false);
		setAtMultiArea(true);
		setForceMultiArea(true);
		setCanFreezeThis(false);
	}
	

	int count;

	public int getCount() {
		return count;
	}

	public int setCount(int Count) {
		return count = Count;
	}

	@Override
	public void processNPC() {
		super.processNPC();
		if (isDead()) {
			return;
		}
		int maxhp = getMaxHitpoints();
		if (maxhp > getHitpoints() && getPossibleTargets().isEmpty() && !isUnderCombat()) {
			setHitpoints(maxhp);
		}
	}

	@Override
	public double getMeleePrayerMultiplier() {
		return 0.1;
	}

}