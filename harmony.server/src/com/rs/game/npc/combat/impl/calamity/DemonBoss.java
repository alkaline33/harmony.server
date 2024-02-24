package com.rs.game.npc.combat.impl.calamity;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

public class DemonBoss extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 30209 };
	}

	@Override
	public int attack(final NPC npc, final Entity target) { //melee
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
				npc.setForceMultiArea(true);
				npc.setForceMultiAttacked(true);
				npc.setForceTargetDistance(64);
				npc.setForceFollowClose(false);
		if (Utils.getRandom(4) == 0) {
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
		if (Utils.getRandom(3) == 0) { //range hit
			npc.setNextAnimation(new Animation(defs.getAttackEmote()));
			WorldTasksManager.schedule(new WorldTask() {
				@Override
				public void run() {
					delayHit(
							npc,
							1,
							target,
							getRangeHit(
									npc,
									getRandomMaxHit(npc, defs.getMaxHit(),
											NPCCombatDefinitions.RANGE, target)));
				//	return defs.getAttackDelay();
			
			
				}
				///return defs.getAttackDelay();
			}
		);
		
			
		
		if (Utils.getRandom(2) == 0) {
			WorldTasksManager.schedule(new WorldTask() {
				@Override
				public void run() {
					npc.setNextAnimation(new Animation(defs.getAttackEmote()));
					delayHit(
							npc,
							1,
							target,
							getRangeHit(
									npc,
									getRandomMaxHit(npc, defs.getMaxHit(),
											NPCCombatDefinitions.RANGE, target)));
			
				
				}
				}
			);
		} else { // heals
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
}
		return defs.getAttackDelay();
	}
}