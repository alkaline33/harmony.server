package com.rs.game.npc.combat.impl;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.ForceTalk;
import com.rs.game.Graphics;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

public class CliffSlayerBoss extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 15114 };
	}

	@Override
	public int attack(final NPC npc, final Entity target) {
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		if (Utils.getRandom(1) == 0) { // MELEE
			npc.setNextAnimation(new Animation(1941));
			for (Entity t : npc.getPossibleTargets()) {
				int damage = getRandomMaxHit(npc, defs.getMaxHit(),
						NPCCombatDefinitions.MELEE, t);
				if (damage > 0) {
					delayHit(npc, 1, t, getMeleeHit(npc, damage));
				}
			
			}
		}
			else if (Utils.getRandom(2) == 0 || Utils.getRandom(3) == 0) { // Range attack
				npc.setNextAnimation(new Animation(1933));
				for (Entity t : npc.getPossibleTargets()) {
					int damage = getRandomMaxHit(npc, defs.getMaxHit(),
							NPCCombatDefinitions.RANGE, t);
					if (damage > 0) {
						delayHit(npc, 1, t, getRangeHit(npc, damage));
					}
				}
			

		} else { //special
			npc.setNextAnimation(new Animation(1931));
			WorldTasksManager.schedule(new WorldTask() {
				int loop;

				@Override
				public void run() {
					if (loop == 0) {
						npc.setNextAnimation(new Animation(1932));
						delayHit(npc, 1, target, getRegularHit(npc, 44));
					} else if (loop == 2) {
						npc.setNextAnimation(new Animation(1938));
						delayHit(npc, 1, target, getMeleeHit(npc, defs.getMaxHit()));
					} else if (loop == 4) {
						npc.setNextAnimation(new Animation(1932));
						delayHit(npc, 1, target, getRegularHit(npc, 44));
					} else if (loop == 5) {
						stop();
					}
					loop++;
				}
			}, 0, 1);
			npc.setNextAnimation(new Animation(1938));
			delayHit(
					npc,
					0,
					target,
					getMeleeHit(
							npc,
							getRandomMaxHit(npc, defs.getMaxHit(),
									NPCCombatDefinitions.MELEE, target)));
		}
		return defs.getAttackDelay();
	}
}
