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

public class TectonicTheBeast extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 10106 };
	}

	@Override
	public int attack(final NPC npc, final Entity target) { //melee
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
				npc.setCapDamage(1000);
				npc.setForceTargetDistance(64);
				npc.setForceFollowClose(true);
		if (Utils.getRandom(4) == 0) {
			int size = npc.getSize();
			npc.setNextAnimation(new Animation(13001));
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
			npc.setNextAnimation(new Animation(13002));
			npc.setNextGraphics(new Graphics(3191));
			WorldTasksManager.schedule(new WorldTask() {
				@Override
				public void run() {
					target.setNextGraphics(new Graphics(3192));
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
		
			
		
		if (Utils.getRandom(2) == 0) { // Heals
			WorldTasksManager.schedule(new WorldTask() {
				@Override
				public void run() {
					npc.setNextAnimation(new Animation(defs.getAttackEmote()));
					target.setNextGraphics(new Graphics(3203));
				
					delayHit(
							npc,
							1,
							target,
							getMagicHit(
									npc,
									getRandomMaxHit(npc, defs.getMaxHit() + 50,
											NPCCombatDefinitions.MAGE, target)));
			
				
				}
				}
			);
		} else { // heals
			target.setNextGraphics(new Graphics(3203));
			npc.heal(300);
			npc.setNextForceTalk(new ForceTalk(
					"I heal from your fear!"));
			return defs.getAttackDelay();
		}
		return defs.getAttackDelay();
	}
		return 0;
}
}