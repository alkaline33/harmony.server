package com.rs.game.npc.combat.impl;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.ForceTalk;
import com.rs.game.Graphics;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.utils.Utils;

public class NecroLord extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 11751 };
	}

	@Override
	public int attack(final NPC npc, final Entity target) {
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		if (Utils.getRandom(4) == 0) {
			switch (Utils.getRandom(4)) {
			case 0:
				npc.setNextForceTalk(new ForceTalk(
						"Your mother is fat!"));
				break;
			case 1:
				npc.setNextForceTalk(new ForceTalk("To the power of veteranity, you should be better than this!"));
				break;
			case 2:
				npc.setNextForceTalk(new ForceTalk(
						"Even if you find my teasure, you need more! Muhahaha!"));
				break;
			case 3:
				npc.setNextForceTalk(new ForceTalk("Put on some tunes and slay me with passion!"));
				break;
			case 4:
				npc.setNextForceTalk(new ForceTalk(""+target.getDisplayName()+", you're pathetic!"));
				break;
			}
		}
		if (Utils.getRandom(1) == 0) { // mage magical attack
			npc.setNextAnimation(new Animation(1995));
			npc.setNextGraphics(new Graphics (2009));
			for (Entity t : npc.getPossibleTargets()) {
				int damage = getRandomMaxHit(npc, defs.getMaxHit(),
						NPCCombatDefinitions.MAGE, t);
				if (damage > 0) {
					delayHit(npc, 1, t, getMagicHit(npc, damage));
					t.setNextGraphics(new Graphics(2006));
					t.addFreezeDelay(10000, true);
					return defs.getAttackDelay();
				}
			
			}
		}
			else if (Utils.getRandom(2) == 0 || Utils.getRandom(3) == 0) { // Range attack
				npc.setNextAnimation(new Animation(17163));
				for (Entity t : npc.getPossibleTargets()) {
					int damage = getRandomMaxHit(npc, defs.getMaxHit(),
							NPCCombatDefinitions.RANGE, t);
					if (damage > 0) {
						delayHit(npc, 1, t, getRangeHit(npc, damage));
						t.setNextGraphics(new Graphics(1287));
						return defs.getAttackDelay();
					}
				}
			

		} else { // melee attack
			if (!target.withinDistance(npc, 2)) {
				npc.setNextAnimation(new Animation(17163));
				delayHit(
						npc,
						0,
						target,
						getRangeHit(
								npc,
								getRandomMaxHit(npc, defs.getMaxHit(),
										NPCCombatDefinitions.RANGE, target)));
				target.setNextGraphics(new Graphics(1287));
				return defs.getAttackDelay();
			} else {
			npc.setNextAnimation(new Animation(defs.getAttackEmote()));
			target.getPoison().makePoisoned(202);
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
		}
		return defs.getAttackDelay();
	}
}
