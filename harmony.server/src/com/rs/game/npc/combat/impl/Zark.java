package com.rs.game.npc.combat.impl;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.ForceTalk;
import com.rs.game.World;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.utils.Utils;

public class Zark extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 9052 };
	}

	@Override
	public int attack(final NPC npc, final Entity target) {
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		if (Utils.getRandom(4) == 0) {
			switch (Utils.getRandom(10)) {
			case 0:
				npc.setNextForceTalk(new ForceTalk("You dare challenge me?"));
				//npc.playSound(3219, 2);
				break;
			case 1:
				npc.setNextForceTalk(new ForceTalk("arharhh!!"));
			//	npc.playSound(3209, 2);
				break;
			case 2:
				npc.setNextForceTalk(new ForceTalk("The power of Zark!"));
				break;
			case 3:
				npc.setNextForceTalk(new ForceTalk("The cold is my offence!"));
				break;
			}
		}
		if (Utils.getRandom(2) == 0) { // range magical attack
			npc.setNextAnimation(new Animation(defs.getAttackEmote()));
			for (Entity t : npc.getPossibleTargets()) {
				delayHit(
						npc,
						1,
						t,
						getRangeHit(
								npc,
								getRandomMaxHit(npc, 355,
										NPCCombatDefinitions.RANGE, t)));
				npc.setNextForceTalk(new ForceTalk("FEEL THE COLD RUN DOWN YOUR SPINE!"));
				World.sendProjectile(npc, t, 1200, 41, 16, 41, 35, 16, 0);
			}
		} else { // magic attack
			npc.setNextAnimation(new Animation(defs.getAttackEmote()));
			delayHit(
					npc,
					0,
					target,
					getMagicHit(
							npc,
							getRandomMaxHit(npc, defs.getMaxHit(),
									NPCCombatDefinitions.MAGE, target)));
		}
		return defs.getAttackDelay();
	}
}
