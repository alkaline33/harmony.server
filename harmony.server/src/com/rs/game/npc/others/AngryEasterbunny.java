package com.rs.game.npc.others;

import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;


/**
 * 
 * @author Mr_Joopz @ Rune-server
 *
 */

@SuppressWarnings("serial")
public class AngryEasterbunny extends NPC {

	public AngryEasterbunny(int id, WorldTile tile, int mapAreaNameHash,
			boolean canBeAttackFromOutOfArea, boolean spawned) {
		super(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		setForceMultiArea(true);
		setForceMultiAttacked(true);
		setCapDamage(2000);
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