package com.rs.game.npc.combat.impl.vorkath;

import com.rs.game.Entity;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.utils.Utils;

public class ZombifiedSpawn extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 30219 };
	}

	@Override
	public int attack(final NPC npc, final Entity target) {
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		npc.setForceMultiArea(true);
		npc.setForceMultiAttacked(true);
		npc.setCapDamage(1000);
		npc.setForceFollowClose(true);
		// npc.setForceTargetDistance(64);
		if (npc.withinDistance(target, 1)) {
			delayHit(npc, 0, target, getRegularHit(npc, Utils.random(200, 600)));
			npc.sendDeath(target);
		}
		return defs.getAttackDelay();
	}
}
