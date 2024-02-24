package com.rs.game.npc.combat.impl;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.Hit;
import com.rs.game.Hit.HitLook;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.combat.NPCCombatDefinitions;

public class YWildyWyrm extends CombatScript {
	
	int attackcount;

	@Override
	public Object[] getKeys() {
		return new Object[] { 30026 };
	}

	@Override
	public int attack(final NPC npc, final Entity target) {
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
			npc.setNextAnimation(new Animation(defs.getAttackEmote()));
			for (Entity t : npc.getPossibleTargets()) {
				attackcount ++;
				if (attackcount >= 5) {
					npc.sendDeath(npc);
				}
				t.applyHit(new Hit(npc, (125),
						HitLook.REGULAR_DAMAGE));
			}
		
		return defs.getAttackDelay();
			
			}
}
