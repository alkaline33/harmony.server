package com.rs.game.npc.others;

import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;

@SuppressWarnings("serial")
public class Kraken extends NPC {

	public Kraken(int id, WorldTile tile, int mapAreaNameHash,
			boolean canBeAttackFromOutOfArea, boolean spawned) {
		super(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		setLureDelay(0);
		setCapDamage(1000);
		setCantFollowUnderCombat(true);
	}
        
        @Override
    	public double getMagePrayerMultiplier() {
    		return 0.5;
    	}

}