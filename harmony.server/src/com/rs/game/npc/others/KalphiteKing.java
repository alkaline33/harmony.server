package com.rs.game.npc.others;

import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;

@SuppressWarnings("serial")
public class KalphiteKing extends NPC {

	public KalphiteKing(int id, WorldTile tile, int mapAreaNameHash, boolean canBeAttackFromOutOfArea, boolean spawned) {
		super(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		setForceMultiArea(true);
		setForceMultiAttacked(true);
		setCapDamage(1000);
		setForceTargetDistance(64);
		setForceFollowClose(false);
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
		if (isDead())
			return;
		int maxhp = getMaxHitpoints();
		if (maxhp > getHitpoints() && getPossibleTargets().isEmpty() && !isUnderCombat())
			setHitpoints(maxhp);
	}

}
