package com.rs.game.npc.others;

import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;

@SuppressWarnings("serial")
public class TheRaptor extends NPC {

	public TheRaptor(int id, WorldTile tile, int mapAreaNameHash,
			boolean canBeAttackFromOutOfArea, boolean spawned) {
		super(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		setForceMultiArea(true);
		setForceMultiAttacked(true);
		setCapDamage(800);
		setForceTargetDistance(64);
		setForceFollowClose(false);
		setCanFreezeThis(false);
		setLureDelay(0);
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
		return 0.9;
	}
	
	@Override
	public double getRangePrayerMultiplier() {
		return 0.1;
	}
	
	@Override
	public double getMagePrayerMultiplier() {
		return 0.1;
	}

}
