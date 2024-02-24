package com.rs.game.npc.combat.impl.karuulm;


import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.utils.Utils;

/**
 * 
 * @author Mr_Joopz @ Rune-server
 *
 */
public class WyrmCombat extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 39610, 39611 };
	}

	@Override
	public int attack(final NPC npc, final Entity target) {
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		if (npc.getId() == 39610) {
			npc.transformIntoNPC(39611);
		}
		npc.setNextAnimation(new Animation(defs.getAttackEmote()));
		if (target.withinDistance(npc, 1)) {
			if (Utils.random(2) == 0) {
				delayHit(npc, 0, target, getMagicHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.MAGE, target)));
			} else {
				delayHit(npc, 0, target, getMeleeHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.MELEE, target)));
			}
		} else {
			delayHit(npc, 1, target, getMagicHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.MAGE, target)));
		}
		return defs.getAttackDelay();
	}
}
