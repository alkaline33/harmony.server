package com.rs.game.npc.combat.impl.karuulm;


import java.util.ArrayList;
import java.util.List;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.Hit;
import com.rs.game.Hit.HitLook;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.npc.others.karuulm.RegularHydra;
import com.rs.utils.Utils;

/**
 * 
 * @author Mr_Joopz @ Rune-server
 *
 */
public class RegularHydraCombat extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 39609 };
	}

	private List<WorldObject> acidPools = new ArrayList<WorldObject>();;
	
	/**
	 * poison splat
	 */

	@Override
	public int attack(final NPC npc, final Entity target) {
		RegularHydra hydra = (RegularHydra) npc;
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		int count = hydra.getCount();
		int magecount = hydra.getMageCount();
		npc.setNextAnimation(new Animation(defs.getAttackEmote()));
		for (WorldObject acidPool : acidPools) {
			if (acidPool == null) {
				continue;
			}

			if (target.matches(acidPool)) {// player under one of the acidPools
				target.applyHit(new Hit(npc, 40, HitLook.POISON_DAMAGE));
				if (!target.getPoison().isPoisoned()) {
					target.getPoison().makePoisoned(40);
				}
		 }

		}
		switch (count) {
		case 0:
		case 1:
		case 2:
			hydra.setCount(hydra.getCount() + 1);
			delayHit(npc, 1, target, getMagicHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.MAGE, target)));
			break;
		case 3:
		case 4:
		case 5:
			hydra.setCount(hydra.getCount() + 1);
			delayHit(npc, 1, target, getRangeHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.RANGE, target)));
			break;
		default:
			hydra.setCount(0);
			acidPools.clear();
			int x = Utils.random(1, 4);
			int y = Utils.random(1, 4);
			if (x == y) {
				x ++;
			}
			if (!World.containsObject(new WorldTile (target.getX(), target.getY(), target.getPlane()))) {
				acidPools.add(new WorldObject(14801, 0, 0, new WorldTile(target.getX(), target.getY(), target.getPlane())));
				World.spawnTemporaryObject(new WorldObject(14801, 10, 0, new WorldTile(target.getX(), target.getY(), 0)), 22000, true);
			}
			if (!World.containsObject(new WorldTile (target.getX() + x, target.getY() + y, target.getPlane()))) {
				acidPools.add(new WorldObject(14801, 0, 0, new WorldTile(target.getX() + x, target.getY() + y, target.getPlane())));
				World.spawnTemporaryObject(new WorldObject(14801, 10, 0, new WorldTile(target.getX() + x, target.getY() + y, 0)), 22000, true);
			}
			if (!World.containsObject(new WorldTile (target.getX() + y, target.getY() + x, target.getPlane()))) {
				acidPools.add(new WorldObject(14801, 0, 0, new WorldTile(target.getX() + y, target.getY() + x, target.getPlane())));
				World.spawnTemporaryObject(new WorldObject(14801, 10, 0, new WorldTile(target.getX() + y, target.getY() + x, 0)), 22000, true);
			}
			/**
			 * special
			 */
			break;
		}
		return defs.getAttackDelay();
	}
}
