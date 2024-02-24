package com.rs.game.npc.combat.impl;

import com.rs.Settings;
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

public class TrioBossCombat extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 30078, 30079, 30080 };
	}

	@Override
	public int attack(final NPC npc, final Entity target) { //melee
		if (Settings.TRIORAIDTASKACTIVE == true)
			npc.setCapDamage(0);
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
				npc.setForceMultiArea(true);
				npc.setForceMultiAttacked(true);
				npc.setCapDamage(1000);
				npc.setForceTargetDistance(64);
				npc.setForceFollowClose(false);
			
		if (Utils.getRandom(4) >= 0) {
			if (Settings.TRIORAIDTASKACTIVE == true)
			npc.setCapDamage(0);
			if (npc.getId() == 30078) {
				npc.setNextAnimation(new Animation(400));
				delayHit(
						npc,
						1,
						target,
						getMeleeHit(
								npc,
								getRandomMaxHit(npc, defs.getMaxHit(),
										NPCCombatDefinitions.MELEE, target)));
			} else if (npc.getId() == 30079) {
				npc.setNextAnimation(new Animation(4230));
				delayHit(
						npc,
						1,
						target,
						getRangeHit(
								npc,
								getRandomMaxHit(npc, defs.getMaxHit(),
										NPCCombatDefinitions.RANGE, target)));
			} else {
				npc.setNextAnimation(new Animation(1978));
				delayHit(
						npc,
						1,
						target,
						getMagicHit(
								npc,
								getRandomMaxHit(npc, defs.getMaxHit(),
										NPCCombatDefinitions.MAGE, target)));
			}
			return defs.getAttackDelay();
		}
		return 0;
}
}