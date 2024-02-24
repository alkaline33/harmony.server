package com.rs.game.npc.zulrah;

import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
@SuppressWarnings("serial")
public class Snakeling extends NPC {

	public Snakeling(int id, WorldTile tile, int mapAreaNameHash,
			boolean canBeAttackFromOutOfArea, boolean spawned) {
		super(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		setLureDelay(0);
		setForceMultiArea(true);
	}
        
        @Override
    	public double getMeleePrayerMultiplier() {
    		return 1;
    	}

}