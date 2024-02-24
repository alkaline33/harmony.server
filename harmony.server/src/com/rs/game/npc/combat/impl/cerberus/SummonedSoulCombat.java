package com.rs.game.npc.combat.impl.cerberus;


import com.rs.game.Entity;
import com.rs.game.World;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.combat.NPCCombatDefinitions;

/**
 * 
 * @author Mr_Joopz @ Rune-server
 *
 */
public class SummonedSoulCombat extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 36867, 36868, 36869 };
	}

	public void getAttackStyle(final NPC npc, final Entity target) {
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		switch (npc.getId()) {
		default:
			delayHit(npc, 2, target, getMeleeHit(npc, 300));
			break;
		case 36867:

			delayHit(npc, 2, target, getRangeHit(npc, 300));
			World.sendProjectile(npc, target, defs.getAttackProjectile(), 41, 16, 41, 35, 16, 0);

			break;
		case 36868:

			delayHit(npc, 2, target, getMagicHit(npc, 300));
			World.sendProjectile(npc, target, defs.getAttackProjectile(), 41, 16, 41, 35, 16, 0);
			break;
		}
	}

	@Override
	public int attack(final NPC npc, final Entity target) {
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		npc.setForceMultiArea(true);
		npc.setForceMultiAttacked(true);
		getAttackStyle(npc, target);
		return defs.getAttackDelay();
	}
}
