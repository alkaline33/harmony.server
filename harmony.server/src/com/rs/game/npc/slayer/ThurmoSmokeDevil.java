package com.rs.game.npc.slayer;

import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;

@SuppressWarnings("serial")
public class ThurmoSmokeDevil extends NPC {

	public ThurmoSmokeDevil(int id, WorldTile tile, int mapAreaNameHash, boolean canBeAttackFromOutOfArea,
			boolean spawned) {
		super(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
	}

	@Override
	public double getMagePrayerMultiplier() {
		return 0.5;
	}

}