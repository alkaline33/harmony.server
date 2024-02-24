package com.rs.game.npc.combat.impl;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.ForceTalk;
import com.rs.game.Hit;
import com.rs.game.Hit.HitLook;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.player.Player;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

/**
 * 
 * @author Mr_Joopz @ Rune-server
 *
 */
public class PrestigeBossCombat extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 13712 };
	}

	int count = 0;

	@Override
	public int attack(final NPC npc, final Entity target) {
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		npc.setForceMultiArea(true);
		npc.setForceMultiAttacked(true);
		npc.setCapDamage(800);
		npc.setForceTargetDistance(64);
		npc.setForceFollowClose(false);
		npc.setCantFollowUnderCombat(true);
		// int count = 0;
		if (count > 10)
			count = 0;
		/**
		 * Phase 1
		 */
		if (npc.getHitpoints() >= 8000 && npc.getHitpoints() <= 15000) {
			switch (count) {

			default:
				npc.setNextAnimation(new Animation(defs.getAttackEmote()));
				delayHit(npc, 0, target, getMeleeHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.MELEE, target)));
				count++;
				break;
			case 10:
				npc.setNextForceTalk(new ForceTalk("I will crush you human!"));
				npc.setNextAnimation(new Animation(defs.getAttackEmote()));
				WorldTasksManager.schedule(new WorldTask() {
					int loop;

					@Override
					public void run() {
						if (loop == 3) {
							if (target.withinDistance(npc, 2)) {
								target.applyHit(new Hit(npc, 900, HitLook.REGULAR_DAMAGE));
							} else {
								npc.setNextAnimation(new Animation(defs.getAttackEmote()));
								delayHit(npc, 4, target, getRangeHit(npc, getRandomMaxHit(npc, 100, NPCCombatDefinitions.RANGE, target)));
							}
						} else if (loop == 5) {
							stop();
						}
						loop++;// this would work?
					}
				}, 0, 1);
				count = 0;
				break;
			}
			return defs.getAttackDelay();
		}
		/**
		 * Phase 2
		 */
		if (npc.getHitpoints() < 8000) {
			switch (count) {
			default:
				npc.setNextAnimation(new Animation(defs.getAttackEmote()));
				delayHit(npc, 1, target, getMeleeHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.MELEE, target)));
				count++;
				break;
			case 8:
				if (target instanceof Player) {
					Player p = (Player) target;
					p.getPrayer().drainPrayer((Math.round(npc.getHitpoints() / 5)));
					p.setPrayerDelay(Utils.getRandom(5) + 5);
				}
				WorldTasksManager.schedule(new WorldTask() {
					int loop;

					@Override
					public void run() {
						if (loop == 1) {
							npc.setNextForceTalk(new ForceTalk("Argggggg!"));
							npc.setNextAnimation(new Animation(defs.getAttackEmote()));
							delayHit(npc, 1, target, getMeleeHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.MELEE, target)));
						} else if (loop == 2) {
							delayHit(npc, 1, target, getMeleeHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.MELEE, target)));
							delayHit(npc, 1, target, getMeleeHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.MELEE, target)));
						} else if (loop == 3) {
							delayHit(npc, 1, target, getMeleeHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.MELEE, target)));
							delayHit(npc, 1, target, getMeleeHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.MELEE, target)));
						} else if (loop == 4) {
							stop();
						}
						loop++;// this would work?
					}
				}, 0, 1);
				count = 0;
				break;
			case 4:
				npc.setNextForceTalk(new ForceTalk("Rain tears of the trolls!"));
				npc.setNextAnimation(new Animation(defs.getAttackEmote()));
				delayHit(npc, 1, target, getRangeHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.RANGE, target)));
				if (!target.getPoison().isPoisoned())
					target.getPoison().makePoisoned(75);
				count++;
				break;
			}
			return defs.getAttackDelay();
		}
		return defs.getAttackDelay();
	}
}

