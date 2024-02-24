package com.rs.game.npc.combat.impl.karuulm;


import java.util.ArrayList;
import java.util.List;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.Graphics;
import com.rs.game.Hit;
import com.rs.game.Hit.HitLook;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.npc.others.karuulm.HydraBoss;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

/**
 * 
 * @author Mr_Joopz @ Rune-server
 *
 */
public class AlchemicalHydraCombat extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 39615, 39619, 39620, 39621 };
	}

	private List<WorldObject> acidPools = new ArrayList<WorldObject>();

	/**
	 * poison splat
	 */

	public void getAttackStyle(final NPC npc, final Entity target) {
		HydraBoss hydra = (HydraBoss) npc;
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		npc.setNextAnimation(new Animation(defs.getAttackEmote()));
		int count = hydra.getCount();
		/**
		 * Green - phase 1
		 */
		if (hydra.getId() == 39615) {
			switch (count) {
			case 0:
			case 1:
			case 2:
				/**
				 * Todo - Projectiles
				 */
				World.sendProjectile(npc, target, 1825, 41, 16, 41, 0, 16, 0);
				hydra.setCount(hydra.getCount() + 1);
				npc.setNextAnimation(new Animation(defs.getAttackEmote()));
				delayHit(npc, 0, target, getMagicHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.MAGE, target)));
				break;
			case 3:
			case 4:
			case 5:
				/**
				 * Todo - Projectiles
				 */
				hydra.setCount(hydra.getCount() + 1);
				npc.setNextAnimation(new Animation(defs.getAttackEmote()));
				delayHit(npc, 0, target, getRangeHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.RANGE, target)));
				break;
			default:
				/**
				 * special
				 */

				hydra.setCount(0);
				acidPools.clear();
				npc.setNextAnimation(new Animation(defs.getAttackEmote()));
				World.spawnTemporaryObject(new WorldObject(14801, 10, 0, new WorldTile(target.getX(), target.getY(), 0)), 22000, true);
				World.spawnTemporaryObject(new WorldObject(14801, 10, 0, new WorldTile(target.getX() + 1, target.getY(), 0)), 22000, true);
				World.spawnTemporaryObject(new WorldObject(14801, 10, 0, new WorldTile(target.getX(), target.getY() + 1, 0)), 22000, true);
				World.spawnTemporaryObject(new WorldObject(14801, 10, 0, new WorldTile(target.getX() + 1, target.getY() + 1, 0)), 22000, true);
				World.spawnTemporaryObject(new WorldObject(14801, 10, 0, new WorldTile(target.getX() - 1, target.getY() - 1, 0)), 22000, true);
				acidPools.add(new WorldObject(14801, 0, 0, new WorldTile(target.getX(), target.getY(), target.getPlane())));
				acidPools.add(new WorldObject(14801, 0, 0, new WorldTile(target.getX() + 1, target.getY(), target.getPlane())));
				acidPools.add(new WorldObject(14801, 0, 0, new WorldTile(target.getX(), target.getY() + 1, target.getPlane())));
				acidPools.add(new WorldObject(14801, 0, 0, new WorldTile(target.getX() + 1, target.getY() + 1, target.getPlane())));
				acidPools.add(new WorldObject(14801, 0, 0, new WorldTile(target.getX() - 1, target.getY() - 1, target.getPlane())));
				break;
			}
		} else if (hydra.getId() == 39619) {
			switch (count) {
			case 0:
			case 1:
			case 2:
				/**
				 * Todo - Projectiles
				 */
				hydra.setCount(hydra.getCount() + 1);
				npc.setNextAnimation(new Animation(defs.getAttackEmote()));
				delayHit(npc, 0, target, getMagicHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.MAGE, target)));
				break;
			case 3:
			case 4:
			case 5:
				/**
				 * Todo - Projectiles
				 */
				hydra.setCount(hydra.getCount() + 1);
				npc.setNextAnimation(new Animation(defs.getAttackEmote()));
				delayHit(npc, 0, target, getRangeHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.RANGE, target)));
				break;
			default:
				/**
				 * special
				 */
				if (hydra.getCount() >= 8) {
				hydra.setCount(0);
				} else {
					hydra.setCount(hydra.getCount() + 1);
				}
				acidPools.clear();
				npc.setNextAnimation(new Animation(defs.getAttackEmote()));
				World.sendProjectile(npc, new WorldTile(target.getX(), target.getY(), target.getPlane()), 1194, 41, 16, 1, 35, 16, 0);
				World.sendProjectile(npc, new WorldTile(target.getX() + 1, target.getY(), target.getPlane()), 1194, 41, 16, 5, 35, 16, 5);
				World.sendProjectile(npc, new WorldTile(target.getX() - 1, target.getY() + 2, target.getPlane()), 1194, 41, 16, 11, 35, 16, 10);
				acidPools.add(new WorldObject(14801, 0, 0, new WorldTile(target.getX(), target.getY(), target.getPlane())));
				acidPools.add(new WorldObject(14801, 0, 0, new WorldTile(target.getX() + 1, target.getY() , target.getPlane())));
				acidPools.add(new WorldObject(14801, 0, 0, new WorldTile(target.getX() - 1, target.getY()+2, target.getPlane())));
				break;
			}
			} else if (hydra.getId() == 39621) {
				switch (count) {
				case 0:
				case 2:
				case 4:
					/**
					 * Todo - Projectiles
					 */
					hydra.setCount(hydra.getCount() + 1);
					npc.setNextAnimation(new Animation(defs.getAttackEmote()));
					delayHit(npc, 0, target, getMagicHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.MAGE, target)));
					break;
				case 1:
				case 3:
				case 5:
					/**
					 * Todo - Projectiles
					 */
					hydra.setCount(hydra.getCount() + 1);
					npc.setNextAnimation(new Animation(defs.getAttackEmote()));
					delayHit(npc, 0, target, getRangeHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.RANGE, target)));
					break;
				default:
					/**
					 * special
					 */
					npc.setNextAnimation(new Animation(defs.getAttackEmote()));
					hydra.setCount(0);
					acidPools.clear();
					npc.setNextAnimation(new Animation(defs.getAttackEmote()));
					World.spawnTemporaryObject(new WorldObject(14801, 10, 0, new WorldTile(target.getX(), target.getY(), 0)), 22000, true);
					World.spawnTemporaryObject(new WorldObject(14801, 10, 0, new WorldTile(target.getX() + 1, target.getY(), 0)), 22000, true);
					World.spawnTemporaryObject(new WorldObject(14801, 10, 0, new WorldTile(target.getX(), target.getY() + 1, 0)), 22000, true);
					World.spawnTemporaryObject(new WorldObject(14801, 10, 0, new WorldTile(target.getX() + 1, target.getY() + 1, 0)), 22000, true);
					World.spawnTemporaryObject(new WorldObject(14801, 10, 0, new WorldTile(target.getX() - 1, target.getY() - 1, 0)), 22000, true);
					acidPools.add(new WorldObject(14801, 0, 0, new WorldTile(target.getX(), target.getY(), target.getPlane())));
					acidPools.add(new WorldObject(14801, 0, 0, new WorldTile(target.getX() + 1, target.getY(), target.getPlane())));
					acidPools.add(new WorldObject(14801, 0, 0, new WorldTile(target.getX(), target.getY() + 1, target.getPlane())));
					acidPools.add(new WorldObject(14801, 0, 0, new WorldTile(target.getX() + 1, target.getY() + 1, target.getPlane())));
					acidPools.add(new WorldObject(14801, 0, 0, new WorldTile(target.getX() - 1, target.getY() - 1, target.getPlane())));
					break;
				}
			} else if (hydra.getId() == 39620) {
			//	System.out.println(count);
				switch (count) {

				case 0:
				case 1:
				case 2:
					/**
					 * Todo - Projectiles
					 */
					hydra.setCount(hydra.getCount() + 1);
					npc.setNextAnimation(new Animation(defs.getAttackEmote()));
					delayHit(npc, 0, target, getMagicHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.MAGE, target)));
					break;
				case 3:
				case 4:
				case 5:
					/**
					 * Todo - Projectiles
					 */
					hydra.setCount(hydra.getCount() + 1);
					npc.setNextAnimation(new Animation(defs.getAttackEmote()));
					delayHit(npc, 0, target, getRangeHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.RANGE, target)));
					break;
				case 6:
					hydra.setForceWalk(new WorldTile (1367, 10267, 0));
					hydra.setCount(hydra.getCount() + 1);
					break;
				case 7:
					/**
					 * special
					 */
					target.setNextWorldTile(new WorldTile (1359, 10262, 0));
					acidPools.clear();
					npc.setNextAnimation(new Animation(defs.getAttackEmote()));
					hydra.setCount(hydra.getCount() + 1);
					World.spawnTemporaryObject(new WorldObject(66365, 10, 0, new WorldTile(1365, 10266, 0)), 22000, true);
					World.spawnTemporaryObject(new WorldObject(66365, 10, 0, new WorldTile(1365, 10265, 0)), 22000, true);
					World.spawnTemporaryObject(new WorldObject(66365, 10, 0, new WorldTile(1365, 10264, 0)), 22000, true);
					World.spawnTemporaryObject(new WorldObject(66365, 10, 0, new WorldTile(1365, 10263, 0)), 22000, true);
					World.spawnTemporaryObject(new WorldObject(66365, 10, 0, new WorldTile(1365, 10262, 0)), 22000, true);
					World.spawnTemporaryObject(new WorldObject(66365, 10, 0, new WorldTile(1365, 10261, 0)), 22000, true);
					World.spawnTemporaryObject(new WorldObject(66365, 10, 0, new WorldTile(1365, 10260, 0)), 22000, true);
					World.spawnTemporaryObject(new WorldObject(66365, 10, 0, new WorldTile(1365, 10259, 0)), 22000, true);
					World.spawnTemporaryObject(new WorldObject(66365, 10, 0, new WorldTile(1365, 10258, 0)), 22000, true);
					World.spawnTemporaryObject(new WorldObject(66365, 10, 0, new WorldTile(1365, 10257, 0)), 22000, true);
					acidPools.add(new WorldObject(66365, 0, 0, new WorldTile(1365, 10266, 0)));
					acidPools.add(new WorldObject(66365, 0, 0, new WorldTile(1365, 10265, 0)));
					acidPools.add(new WorldObject(66365, 0, 0, new WorldTile(1365, 10264, 0)));
					acidPools.add(new WorldObject(66365, 0, 0, new WorldTile(1365, 10263, 0)));
					acidPools.add(new WorldObject(66365, 0, 0, new WorldTile(1365, 10262, 0)));
					acidPools.add(new WorldObject(66365, 0, 0, new WorldTile(1365, 10261, 0)));
					acidPools.add(new WorldObject(66365, 0, 0, new WorldTile(1365, 10260, 0)));
					acidPools.add(new WorldObject(66365, 0, 0, new WorldTile(1365, 10259, 0)));
					acidPools.add(new WorldObject(66365, 0, 0, new WorldTile(1365, 10258, 0)));
					acidPools.add(new WorldObject(66365, 0, 0, new WorldTile(1365, 10257, 0)));

					World.spawnTemporaryObject(new WorldObject(66365, 10, 0, new WorldTile(1364, 10266, 0)), 22000, true);
					World.spawnTemporaryObject(new WorldObject(66365, 10, 0, new WorldTile(1364, 10265, 0)), 22000, true);
					World.spawnTemporaryObject(new WorldObject(66365, 10, 0, new WorldTile(1364, 10264, 0)), 22000, true);
					World.spawnTemporaryObject(new WorldObject(66365, 10, 0, new WorldTile(1364, 10263, 0)), 22000, true);
					World.spawnTemporaryObject(new WorldObject(66365, 10, 0, new WorldTile(1364, 10262, 0)), 22000, true);
					World.spawnTemporaryObject(new WorldObject(66365, 10, 0, new WorldTile(1364, 10261, 0)), 22000, true);
					World.spawnTemporaryObject(new WorldObject(66365, 10, 0, new WorldTile(1364, 10260, 0)), 22000, true);
					World.spawnTemporaryObject(new WorldObject(66365, 10, 0, new WorldTile(1364, 10259, 0)), 22000, true);
					World.spawnTemporaryObject(new WorldObject(66365, 10, 0, new WorldTile(1364, 10258, 0)), 22000, true);
					World.spawnTemporaryObject(new WorldObject(66365, 10, 0, new WorldTile(1364, 10257, 0)), 22000, true);
					acidPools.add(new WorldObject(66365, 0, 0, new WorldTile(1364, 10266, 0)));
					acidPools.add(new WorldObject(66365, 0, 0, new WorldTile(1364, 10265, 0)));
					acidPools.add(new WorldObject(66365, 0, 0, new WorldTile(1364, 10264, 0)));
					acidPools.add(new WorldObject(66365, 0, 0, new WorldTile(1364, 10263, 0)));
					acidPools.add(new WorldObject(66365, 0, 0, new WorldTile(1364, 10262, 0)));
					acidPools.add(new WorldObject(66365, 0, 0, new WorldTile(1364, 10261, 0)));
					acidPools.add(new WorldObject(66365, 0, 0, new WorldTile(1364, 10260, 0)));
					acidPools.add(new WorldObject(66365, 0, 0, new WorldTile(1364, 10259, 0)));
					acidPools.add(new WorldObject(66365, 0, 0, new WorldTile(1364, 10258, 0)));
					acidPools.add(new WorldObject(66365, 0, 0, new WorldTile(1364, 10257, 0)));
					
					World.spawnTemporaryObject(new WorldObject(66365, 10, 0, new WorldTile(1365, 10266, 0)), 22000, true);
					World.spawnTemporaryObject(new WorldObject(66365, 10, 0, new WorldTile(1364, 10266, 0)), 22000, true);
					World.spawnTemporaryObject(new WorldObject(66365, 10, 0, new WorldTile(1363, 10266, 0)), 22000, true);
					World.spawnTemporaryObject(new WorldObject(66365, 10, 0, new WorldTile(1362, 10266, 0)), 22000, true);
					World.spawnTemporaryObject(new WorldObject(66365, 10, 0, new WorldTile(1361, 10266, 0)), 22000, true);
					World.spawnTemporaryObject(new WorldObject(66365, 10, 0, new WorldTile(1360, 10266, 0)), 22000, true);
					World.spawnTemporaryObject(new WorldObject(66365, 10, 0, new WorldTile(1359, 10266, 0)), 22000, true);
					World.spawnTemporaryObject(new WorldObject(66365, 10, 0, new WorldTile(1358, 10266, 0)), 22000, true);
					World.spawnTemporaryObject(new WorldObject(66365, 10, 0, new WorldTile(1357, 10266, 0)), 22000, true);
					World.spawnTemporaryObject(new WorldObject(66365, 10, 0, new WorldTile(1356, 10266, 0)), 22000, true);
					acidPools.add(new WorldObject(66365, 0, 0, new WorldTile(1365, 10266, 0)));
					acidPools.add(new WorldObject(66365, 0, 0, new WorldTile(1364, 10266, 0)));
					acidPools.add(new WorldObject(66365, 0, 0, new WorldTile(1363, 10266, 0)));
					acidPools.add(new WorldObject(66365, 0, 0, new WorldTile(1362, 10266, 0)));
					acidPools.add(new WorldObject(66365, 0, 0, new WorldTile(1361, 10266, 0)));
					acidPools.add(new WorldObject(66365, 0, 0, new WorldTile(1360, 10266, 0)));
					acidPools.add(new WorldObject(66365, 0, 0, new WorldTile(1359, 10266, 0)));
					acidPools.add(new WorldObject(66365, 0, 0, new WorldTile(1358, 10266, 0)));
					acidPools.add(new WorldObject(66365, 0, 0, new WorldTile(1357, 10266, 0)));
					acidPools.add(new WorldObject(66365, 0, 0, new WorldTile(1356, 10266, 0)));
					
					World.spawnTemporaryObject(new WorldObject(66365, 10, 0, new WorldTile(1365, 10267, 0)), 22000, true);
					World.spawnTemporaryObject(new WorldObject(66365, 10, 0, new WorldTile(1364, 10267, 0)), 22000, true);
					World.spawnTemporaryObject(new WorldObject(66365, 10, 0, new WorldTile(1363, 10267, 0)), 22000, true);
					World.spawnTemporaryObject(new WorldObject(66365, 10, 0, new WorldTile(1362, 10267, 0)), 22000, true);
					World.spawnTemporaryObject(new WorldObject(66365, 10, 0, new WorldTile(1361, 10267, 0)), 22000, true);
					World.spawnTemporaryObject(new WorldObject(66365, 10, 0, new WorldTile(1360, 10267, 0)), 22000, true);
					World.spawnTemporaryObject(new WorldObject(66365, 10, 0, new WorldTile(1359, 10267, 0)), 22000, true);
					World.spawnTemporaryObject(new WorldObject(66365, 10, 0, new WorldTile(1358, 10267, 0)), 22000, true);
					World.spawnTemporaryObject(new WorldObject(66365, 10, 0, new WorldTile(1357, 10267, 0)), 22000, true);
					World.spawnTemporaryObject(new WorldObject(66365, 10, 0, new WorldTile(1356, 10267, 0)), 22000, true);
					acidPools.add(new WorldObject(66365, 0, 0, new WorldTile(1365, 10267, 0)));
					acidPools.add(new WorldObject(66365, 0, 0, new WorldTile(1364, 10267, 0)));
					acidPools.add(new WorldObject(66365, 0, 0, new WorldTile(1363, 10267, 0)));
					acidPools.add(new WorldObject(66365, 0, 0, new WorldTile(1362, 10267, 0)));
					acidPools.add(new WorldObject(66365, 0, 0, new WorldTile(1361, 10267, 0)));
					acidPools.add(new WorldObject(66365, 0, 0, new WorldTile(1360, 10267, 0)));
					acidPools.add(new WorldObject(66365, 0, 0, new WorldTile(1359, 10267, 0)));
					acidPools.add(new WorldObject(66365, 0, 0, new WorldTile(1358, 10267, 0)));
					acidPools.add(new WorldObject(66365, 0, 0, new WorldTile(1357, 10267, 0)));
					acidPools.add(new WorldObject(66365, 0, 0, new WorldTile(1356, 10267, 0)));
					break;
					default:
						npc.setNextAnimation(new Animation(defs.getAttackEmote()));
						hydra.setCount(hydra.getCount() + 1);
						break;
					case 20:
					case 21:
						hydra.setCount(0);
						break;
				case 8:
					npc.setNextAnimation(new Animation(defs.getAttackEmote()));
					hydra.setCount(count += 1);
					WorldTasksManager.schedule(new WorldTask() {
  						int loop;

  						@Override
  						public void run() {
  							if (loop == 0) {
  								if (target.getHitpoints() < 1) {
  									stop();
  								}
  								World.sendProjectile(npc, target, 1213, 34, 16, 30, 35, 16, 0);
  								npc.setNextAnimation(new Animation(defs.getAttackEmote()));
  								hydra.setTargetX(target.getX());
  								hydra.setTargetY(target.getY());
  							} else if (loop >= 2 && loop <= 16) {
  								if (target.getHitpoints() < 1) {
  									stop();
  								}
  								if (target.isDead()) {
  									stop();
  								}
  								if (hydra.getId() != 39620) {
									stop();
								}
  								if (target.getX() == hydra.getTargetX() && target.getY() == hydra.getTargetY()) {
  									delayHit(npc, 1, target, getRegularHit(npc, 260));
  								}
  								hydra.setTargetX(target.getX());
  								hydra.setTargetY(target.getY());
  								World.sendProjectile(npc, target, 1213, 34, 16, 30, 35, 16, 0);
  								npc.setNextAnimation(new Animation(defs.getAttackEmote()));
  							} else if (loop >= 17) {
  								stop();
  							}
  							loop++;
  						}
  					}, 0, 1);
					break;
  				}
			}
	}
	
	/**
	 * Rapid fire
	 */

	


	@Override
	public int attack(final NPC npc, final Entity target) {
		HydraBoss hydra = (HydraBoss) npc;
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		/**
		 * Animation cycle
		 */
//		WorldTasksManager.schedule(new WorldTask() {
//			int loop = 38250;
//
//			@Override
//			public void run() {
//				if (loop >= 0) {
//					npc.setNextAnimation(new Animation(-1));
//					npc.setNextAnimation(new Animation(loop));
//					System.out.println("ID:"+ loop);
//
//				} else if (loop == 38300) {
//					stop();
//				}
//				loop++;
//			}
//		}, 0, 1);
//		return 100;
		/**
		 * Handles phasing
		 */
		if (hydra.getHitpoints() <= 8250 && hydra.getId() == 39615) {
			hydra.transformIntoNPC(39619);
			hydra.setCount(0);
		} else if (hydra.getHitpoints() <= 5500 && hydra.getId() == 39619) {
			hydra.transformIntoNPC(39620);
			hydra.setCount(0);
		} else if (hydra.getHitpoints() <= 2750 && hydra.getId() == 39620) {
			hydra.transformIntoNPC(39621);
			hydra.setCount(0);
		}
		/**
		 * Checks to see if player is standing on a dangerous tile.
		 */
		for (WorldObject acidPool : acidPools) {
			if (acidPool == null) {
				continue;
			}

			if (target.matches(acidPool) && hydra.getId() == 39615) {// player under one of the acidPools
				target.applyHit(new Hit(npc, Utils.random(200), HitLook.POISON_DAMAGE));
				if (!target.getPoison().isPoisoned()) {
					target.getPoison().makePoisoned(60);
				}
			} else if (target.matches(acidPool) && hydra.getId() == 39619) {// player under one of the acidPools
				target.applyHit(new Hit(npc, Utils.random(200, 470), HitLook.MAGIC_DAMAGE));
				target.setNextGraphics(new Graphics(1194));
			} else if (target.matches(acidPool) && hydra.getId() == 39620) {// player under one of the acidPools
				target.applyHit(new Hit(npc, Utils.random(260, 520), HitLook.REGULAR_DAMAGE));
			} else if (target.matches(acidPool) && hydra.getId() == 39621) {// player under one of the acidPools
				target.applyHit(new Hit(npc, Utils.random(300), HitLook.POISON_DAMAGE));
				if (!target.getPoison().isPoisoned()) {
					target.getPoison().makePoisoned(90);
				}
			}

		}
		/**
		 * Grabs the current attack type
		 */
		getAttackStyle(npc, target);
		if (hydra.getCount() < 8) {
			return defs.getAttackDelay();
		} else {
			return 3;
		}
	}
}
