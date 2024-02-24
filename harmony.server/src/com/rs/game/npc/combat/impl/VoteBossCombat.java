package com.rs.game.npc.combat.impl;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.ForceTalk;
import com.rs.game.Graphics;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.npc.others.VoteBoss;
import com.rs.utils.Utils;

/**
 * 
 * @author Mr_Joopz - The God
 *
 */
public class VoteBossCombat extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 1 };
	}

	@Override
	public int attack(final NPC npc, final Entity target) {
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		VoteBoss voteboss = (VoteBoss) npc;
		int attackstyle = Utils.random(3);
		switch (attackstyle) {
		/**
		 * Melee
		 */
		case 0:
			npc.setNextAnimation(new Animation(8989));
			npc.setNextForceTalk(new ForceTalk ("Feel my strength!"));
			delayHit(npc, 3, target, getMeleeHit(npc, getRandomMaxHit(npc, 10000, NPCCombatDefinitions.MELEE, target)));
			break;
		/**
		 * Magic
		 */
		case 1:
			npc.setNextAnimation(new Animation(708));
			npc.setNextGraphics(new Graphics(108));
			npc.setNextForceTalk(new ForceTalk ("Mystic power!"));
			delayHit(npc, 3, target, getMagicHit(npc, getRandomMaxHit(npc, 10000, NPCCombatDefinitions.MAGE, target)));
			break;
		/**
		 * Ranged
		 */
		default:
			npc.setNextAnimation(new Animation(9943));
			npc.setNextForceTalk(new ForceTalk ("No hiding from me!"));
			delayHit(npc, 3, target, getRangeHit(npc, getRandomMaxHit(npc, 10000, NPCCombatDefinitions.RANGE, target)));
			break;
		}
		return defs.getAttackDelay();
	}
}
