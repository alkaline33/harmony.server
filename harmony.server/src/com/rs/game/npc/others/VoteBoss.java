package com.rs.game.npc.others;

import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;

@SuppressWarnings("serial")
public class VoteBoss extends NPC {

	public VoteBoss(int id, WorldTile tile, int mapAreaNameHash,
			boolean canBeAttackFromOutOfArea, boolean spawned) {
		super(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		setCapDamage(800);
		setForceTargetDistance(64);
		setForceFollowClose(false);
		setAtMultiArea(true);
		setForceMultiArea(true);
		setCanFreezeThis(false);
	}

	@Override
	public void processNPC() {
		super.processNPC();
		if (isDead()) {
			return;
		}
	}

	@Override
	public double getMeleePrayerMultiplier() {
		return 0.1;
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