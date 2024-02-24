package com.rs.game.npc.combat.impl;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

public class Edimmu extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 20290 };
	}

	@Override
	public int attack(final NPC npc, final Entity target) { //melee
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
				npc.setForceTargetDistance(64);
				npc.setForceFollowClose(false);
		if (Utils.getRandom(0) == 0) {
			int size = npc.getSize();
			npc.setNextAnimation(new Animation(defs.getAttackEmote()));
			delayHit(
					npc,
					1,
					target,
					getMeleeHit(
							npc,
							getRandomMaxHit(npc, defs.getMaxHit(),
									NPCCombatDefinitions.MELEE, target)));
		

			return defs.getAttackDelay();
		}
		if (Utils.getRandom(3) == 0) { //Heal
			npc.setNextAnimation(new Animation(defs.getAttackEmote()));
			WorldTasksManager.schedule(new WorldTask() {
				@Override
				public void run() {
					delayHit(
							npc,
							1,
							target,
							getMeleeHit(
									npc,
									getRandomMaxHit(npc, defs.getMaxHit(),
											NPCCombatDefinitions.MELEE, target)));
					npc.heal(80);
				//	return defs.getAttackDelay();
			
			
				}
				
			}
			
		);
		}
		else { // Magic
			WorldTasksManager.schedule(new WorldTask() {
				@Override
				public void run() {
					npc.setNextAnimation(new Animation(defs.getAttackEmote()));
					delayHit(
							npc,
							1,
							target,
							getMagicHit(
									npc,
									getRandomMaxHit(npc, defs.getMaxHit(),
											NPCCombatDefinitions.MAGE, target)));
			
				
				}
				}
			);
		return defs.getAttackDelay();

}
		return defs.getAttackDelay();
	}
}