package com.rs.game.npc.combat.impl;

import java.util.ArrayList;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.ForceTalk;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.npc.others.TheRaptor;
import com.rs.game.player.Player;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

/**
 * 
 * @author Mr_Joopz @ Rune-server
 *
 */
public class TheRaptorCombat extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 13955 };
	}

	@Override
	public int attack(final NPC npc, final Entity target) {
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		TheRaptor raptor = (TheRaptor) npc;
		npc.setForceMultiArea(true);
		npc.setForceMultiAttacked(true);
		npc.setCapDamage(800);
		npc.setForceTargetDistance(64);
		npc.setForceFollowClose(false);
		int count = raptor.getCount();
		if (npc.getHitpoints() > 2000) {
			switch (count) {
			case 0:
			case 6:
				npc.setNextAnimation(new Animation(defs.getAttackEmote()));
				if (target.withinDistance(raptor, 2)) {
					delayHit(npc, 0, target, getMeleeHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.MELEE, target)));
				} else {
					raptor.heal(target.getHitpoints() / 5);
					raptor.setNextForceTalk(new ForceTalk("Heal me Seren!"));
				}
				raptor.setCount(count += 1);
				break;
			case 1:
			case 5:
			case 8:
				if (count >= 8) {
					raptor.setCount(0);
				} else {
					raptor.setCount(count += 1);
				}
				npc.setNextAnimation(new Animation(716));
				delayHit(npc, 0, target, getMagicHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.MAGE, target)));
				break;
			case 2:
			case 7:
			case 4:
				npc.setNextAnimation(new Animation(3128));
				delayHit(npc, 0, target, getRangeHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.RANGE, target)));
				raptor.setCount(count += 1);
				break;
			case 3:
				Player p = (Player) target;
				npc.setNextAnimation(new Animation(1056));
				raptor.setNextForceTalk(new ForceTalk("Your protection is my weapon!"));
				p.getPrayer().drainPrayer(p.getPrayer().getPrayerpoints() / 2);
				delayHit(npc, 1, target, getRegularHit(npc, p.getPrayer().getPrayerpoints() / 2));
				raptor.setCount(count += 1);
				break;
			}
		} else if (npc.getHitpoints() <= 2000) {
			switch (count) {
			case 0:
			case 6:
				
				if (target.withinDistance(raptor, 2)) {
					npc.setNextAnimation(new Animation(defs.getAttackEmote()));
					delayHit(npc, 0, target, getMeleeHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.MELEE, target)));
				} else {
					npc.setNextAnimation(new Animation(3128));
					delayHit(npc, 0, target, getRangeHit(npc, getRandomMaxHit(npc, 80, NPCCombatDefinitions.RANGE, target)));
					delayHit(npc, 0, target, getRangeHit(npc, getRandomMaxHit(npc, 80, NPCCombatDefinitions.MAGE, target)));
				}
				raptor.setCount(count += 1);
				break;
			case 1:
			case 5:

				npc.setNextAnimation(new Animation(716));
				delayHit(npc, 0, target, getMagicHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.MAGE, target)));
				raptor.setCount(count += 1);
				break;
			case 2:
			case 7:
				npc.setNextAnimation(new Animation(3128));
				delayHit(npc, 0, target, getRangeHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.RANGE, target)));
				raptor.setCount(count += 1);
				break;

			case 3:
			case 4:
			case 8:
				if (count >= 8) {
					count = 0;
				}
				ArrayList<Entity> possibleTargets4 = npc.getPossibleTargets();
				for (Entity t : possibleTargets4) {
					if (t instanceof Player) {
						Player p = (Player) t;
						raptor.setCount(count += 1);

						WorldTasksManager.schedule(new WorldTask() {
							int loop;

							@Override
							public void run() {

								if (loop == 0) {
									npc.setNextForceTalk(new ForceTalk("Infuse me Zaros!"));
									if (t.getHitpoints() < 1) {
										stop();
									}
									npc.setNextAnimation(new Animation(defs.getAttackEmote()));
									delayHit(npc, 1, t, getRegularHit(npc, Utils.getRandom(220)));
								} else if (loop == 2) {
									if (t.getHitpoints() < 1) {
										stop();
									}
									npc.setNextAnimation(new Animation(defs.getAttackEmote()));
									delayHit(npc, 1, t, getRegularHit(npc, Utils.getRandom(180)));
								} else if (loop == 4) {
									if (t.getHitpoints() < 1) {
										stop();
									}
									if (!target.getPoison().isPoisoned()) {
										npc.setNextForceTalk(new ForceTalk("Drain them Sliske!"));
										target.getPoison().makePoisoned(125);
									} else {
										npc.setNextForceTalk(new ForceTalk("By the power of Seren!"));
										target.setNextWorldTile(new WorldTile(npc.getX(), npc.getY(), npc.getPlane()));
										target.addFreezeDelay(10000);
									}
								} else if (loop == 5) {
									stop();

								}
								loop++;
							}
						}, 0, 1);
					}
				}
				break;
			}

		}
		return defs.getAttackDelay();
	}
}
