package com.rs.game.npc.combat.impl.theatreofblood;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.npc.theatreofblood.Justiciar;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

public class JusticiarCombat extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 30145 };
	}

	@Override
	public int attack(final NPC npc, final Entity target) {
		Justiciar justiciar = (Justiciar) npc;
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		npc.setForceMultiArea(true);
		npc.setForceMultiAttacked(true);
		npc.setCapDamage(1000);
		npc.setForceTargetDistance(64);
		int attackstyle = Utils.random(6);
		switch (attackstyle) {
		default:
			npc.setNextAnimation(new Animation(defs.getAttackEmote()));
			delayHit(npc, 0, target, getMeleeHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.MELEE, target)));
			delayHit(npc, 0, target, getMeleeHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.MELEE, target)));
			delayHit(npc, 0, target, getMeleeHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.MELEE, target)));
			break;
		case 2:
				justiciar.targetx = target.getX();
				justiciar.targety = target.getY();
				WorldTasksManager.schedule(new WorldTask() {
					int loop;

					@Override
					public void run() {
						if (loop >= 0) {
							if (target.getHitpoints() < 1) {
								stop();
							}
							if (target.getY() != justiciar.targety || target.getX() != justiciar.targetx) {
								stop();
							}
						if (loop == 0) {
							npc.setNextAnimation(new Animation(4841));
						}
						if (loop == 2) {
								if (target.getY() == justiciar.targety && target.getX() == justiciar.targetx) {
								delayHit(npc, 0, target, getRegularHit(npc, Utils.random(10000)));
								}
							}
						}
						loop++;
					}
				}, 0, 1);
			break;
			}
		return defs.getAttackDelay();
		// break;
	}
}
