package com.rs.game.npc.combat.impl;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.ForceTalk;
import com.rs.game.Graphics;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.utils.Utils;

public class BlinkCalamity extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 12877 };
	}

	@Override
	public int attack(final NPC npc, final Entity target) {
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		if (Utils.getRandom(4) == 0) {
			switch (Utils.getRandom(4)) {
			case 0:
				npc.setNextForceTalk(new ForceTalk(
						"You will not survive this!"));
				break;
			case 1:
				npc.setNextForceTalk(new ForceTalk("Professor sheaf has nothing on my inventions!"));
				break;
			case 2:
				npc.setNextForceTalk(new ForceTalk(
						"Even if you kill me, your friends will end you!"));
				break;
			case 3:
				npc.setNextForceTalk(new ForceTalk("Power is knowledge!"));
				break;
			case 4:
				npc.setNextForceTalk(new ForceTalk(""+target.getDisplayName()+" will die tonight!"));
				break;
			}
		}
		if (Utils.getRandom(1) == 0) { // mage magical attack
			npc.setNextAnimation(new Animation(1979));
			for (Entity t : npc.getPossibleTargets()) {
				int damage = getRandomMaxHit(npc, defs.getMaxHit(),
						NPCCombatDefinitions.MAGE, t);
				if (damage > 0) {
					delayHit(npc, 1, t, getMagicHit(npc, damage));
					t.setNextGraphics(new Graphics(1207));
				}
			
			}
		}
			else if (Utils.getRandom(2) == 0 || Utils.getRandom(3) == 0) { // Range attack
				npc.setNextAnimation(new Animation(17193));
				for (Entity t : npc.getPossibleTargets()) {
					int damage = getRandomMaxHit(npc, defs.getMaxHit(),
							NPCCombatDefinitions.RANGE, t);
					if (damage > 0) {
						delayHit(npc, 1, t, getRangeHit(npc, damage));
						t.setNextGraphics(new Graphics(1264));
					}
				}
			

		} else { // melee attack
			if (!target.withinDistance(npc, 2)) {
				npc.setNextAnimation(new Animation(17193));
				delayHit(
						npc,
						0,
						target,
						getRangeHit(
								npc,
								getRandomMaxHit(npc, defs.getMaxHit(),
										NPCCombatDefinitions.RANGE, target)));
			} else {
			npc.setNextAnimation(new Animation(defs.getAttackEmote()));
			delayHit(
					npc,
					0,
					target,
					getMeleeHit(
							npc,
							getRandomMaxHit(npc, defs.getMaxHit(),
									NPCCombatDefinitions.MELEE, target)));
		}
		}
		return defs.getAttackDelay();
	}
}
