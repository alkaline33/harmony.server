package com.rs.game.npc.combat.impl.theatreofblood;


import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.ForceTalk;
import com.rs.game.Graphics;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.npc.theatreofblood.Ramokee;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

/**
 * 
 * @author Mr_Joopz @ Rune-server
 *
 */
public class RamokeeCombat extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 13000, 13014 };
	}

	/**
	 * 
	 * Grabs Attack style
	 */

	public void getAttackStyle(final NPC npc, final Entity target) {

		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		if (npc.getId() == 13000) {
			delayHit(npc, 0, target, getMagicHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.MAGE, target)));
			npc.setNextAnimation(new Animation(defs.getAttackEmote()));
		} else if (npc.getId() == 13014) {
			delayHit(npc, 0, target, getMeleeHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.MELEE, target)));
			npc.setNextAnimation(new Animation(defs.getAttackEmote()));
		}
	}

	@Override
	public int attack(final NPC npc, final Entity target) {
		Ramokee ramokee = (Ramokee) npc;
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		int count = ramokee.getCount();
		// System.out.println("" + count);
		switch (count) {
		case 52:
			ramokee.setCount(count = 1);
			if (target.getY() == ramokee.targety && target.getX() == ramokee.targetx) {
				WorldTasksManager.schedule(new WorldTask() {
					int loop;

					@Override
					public void run() {
						if (loop >= 0) {
							if (target.getHitpoints() < 1) {
								stop();
							}
							if (target.getY() != ramokee.targety || target.getX() != ramokee.targetx) {
								stop();
							}
							if (npc.getId() == 13014 && npc.getHitpoints() >= 5000) {
								stop();
							}
							npc.setNextForceTalk(new ForceTalk("Siphon!"));
							delayHit(npc, 1, target, getRegularHit(npc, 50));
							ramokee.heal(500);
							target.setNextGraphics(new Graphics(1592));
						}
						loop++;
					}
				}, 0, 1);
			}
			break;
		default:
			if (npc.getId() == 13000 && npc.getHitpoints() < 5000) {
				npc.transformIntoNPC(13014);
			}
			// ramokee.setCount(count += 1);
			int pool = Utils.random(0, 10);
			if (pool == 3) {
				ramokee.targetx = target.getX();
				ramokee.targety = target.getY();
				target.addFreezeDelay(5000);
				ramokee.setCount(52);
			} else {
				getAttackStyle(npc, target);
			}
			break;
		}
		return defs.getAttackDelay();
	}
}
