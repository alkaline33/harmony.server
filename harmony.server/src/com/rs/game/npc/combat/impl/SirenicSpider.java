package com.rs.game.npc.combat.impl;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.utils.Utils;

public class SirenicSpider extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 30008 };
	}

	@Override
	public int attack(final NPC npc, final Entity target) { //melee
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
				npc.setForceMultiArea(true);
				npc.setForceMultiAttacked(true);
				npc.setCapDamage(800);
				npc.setForceTargetDistance(64);
				npc.setForceFollowClose(false);
				int attackStyle = Utils.getRandom(1);
				if (attackStyle == 0) {
			npc.setNextAnimation(new Animation(defs.getAttackEmote()));
			target.getPoison().makePoisoned(90);
			delayHit(
					npc,
					0,
					target,
					getMagicHit(
							npc,
							getRandomMaxHit(npc, (300) + 300,
									NPCCombatDefinitions.MAGE, target)));
			return defs.getAttackDelay();
				} else if (attackStyle == 1) {
					npc.setNextAnimation(new Animation(defs.getAttackEmote()));
					delayHit(
							npc,
							0,
							target,
							getMeleeHit(
									npc,
									getRandomMaxHit(npc, defs.getMaxHit(),
											NPCCombatDefinitions.MELEE, target)));
					return defs.getAttackDelay();
				}
		
		return defs.getAttackDelay();

	}
}
