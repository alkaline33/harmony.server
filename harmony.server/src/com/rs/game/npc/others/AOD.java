package com.rs.game.npc.others;

import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;

@SuppressWarnings("serial")
public class AOD extends NPC {

	public AOD(int id, WorldTile tile, int mapAreaNameHash,
			boolean canBeAttackFromOutOfArea, boolean spawned) {
		super(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		setLureDelay(0);
		setCapDamage(1000);
		setRun(false);
		setForceMultiAttacked(true);
	}
        @Override
    	public double getMagePrayerMultiplier() {
		return 0.5;
    	}

    	@Override
    	public double getRangePrayerMultiplier() {
		return 0.5;
    	}

    	@Override
    	public double getMeleePrayerMultiplier() {
		return 0.5;
    	}

}