package com.rs.game.npc.others.karuulm;

import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;

@SuppressWarnings("serial")
public class RegularHydra extends NPC {

	public RegularHydra(int id, WorldTile tile, int mapAreaNameHash, boolean canBeAttackFromOutOfArea, boolean spawned) {
		super(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		setCapDamage(2000);
	}

	int count;
	int magecount;

	int x = 0;
	int y = 0;

	public int getHydraX() {
		return x;
	}

	public int getHydraY() {
		return y;
	}

	public int setX(int X) {
		return x = X;
	}

	public int setY(int Y) {
		return y = Y;
	}

	public int getCount() {
		return count;
	}

	public int setCount(int Count) {
		return count = Count;
	}

	public int getMageCount() {
		return magecount;
	}

	public int setMageCount(int MageCount) {
		return magecount = MageCount;
	}

}