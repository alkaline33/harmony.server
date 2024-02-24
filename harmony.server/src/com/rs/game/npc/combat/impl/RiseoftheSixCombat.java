package com.rs.game.npc.combat.impl;


import java.util.ArrayList;
import java.util.List;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.ForceTalk;
import com.rs.game.Graphics;
import com.rs.game.Hit;
import com.rs.game.Hit.HitLook;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.npc.others.ROT6;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

/**
 * 
 * @author Mr_Joopz @ Rune-server
 *
 */
public class RiseoftheSixCombat extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 30011, 30012, 30013, 30014, 30015, 30016 };
	}

	/**
	 * Ahrim attack
	 */

	private void AhrimAttack(NPC npc, Entity target) {

		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		int x = target.getX();
		int y = target.getY();
		List<String> tiles3 = new ArrayList<String>();
		ArrayList<Integer> distances = new ArrayList<Integer>();
		ArrayList<Entity> possibleTargets3 = npc.getPossibleTargets();
		for (Entity t : possibleTargets3) {
			if (tiles3.contains(target.getX() + " " + target.getY() + " " + target.getPlane())) {
				continue;
			}

			tiles3.add(target.getX() + " " + target.getY() + " " + target.getPlane());
			distances.add(Utils.getDistance(target, npc));
		}

		for (int i = 0; i < tiles3.size(); i++) {

			String s = tiles3.get(i);
			WorldTile tile = new WorldTile(Integer.valueOf(s.split("\\s")[0]), Integer.valueOf(s.split("\\s")[1]), Integer.valueOf(s.split("\\s")[2]));
			WorldTasksManager.schedule(new WorldTask() {
				@Override
				public void run() {
					WorldTasksManager.schedule(new WorldTask() {
						int loop;

						@Override
						public void run() {
							if (target.getX() != tile.getX() || target.getY() != tile.getY()) {
								stop();
							}
							if (loop >= 0) {

								World.sendGraphics(npc, new Graphics(3189), new WorldTile(x, y, target.getPlane()));
								if (target.getX() == tile.getX() && target.getY() == tile.getY()) {
									npc.setNextAnimation(new Animation(defs.getAttackEmote()));
									target.applyHit(new Hit(npc, Utils.random(100), HitLook.REGULAR_DAMAGE));
								}
							}
							loop++;
						}
					}, 0, 1);

				}

			}, distances.get(i) - 1);
		}
		return;
	}

	/**
	 * Karil attack
	 */

	private void KarilAttack(NPC npc, Entity target) {

		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		int x = target.getX();
		int y = target.getY();
		List<String> tiles3 = new ArrayList<String>();
		ArrayList<Integer> distances = new ArrayList<Integer>();
		ArrayList<Entity> possibleTargets3 = npc.getPossibleTargets();
		for (Entity t : possibleTargets3) {
			if (tiles3.contains(target.getX() + " " + target.getY() + " " + target.getPlane())) {
				continue;
			}

			tiles3.add(target.getX() + " " + target.getY() + " " + target.getPlane());
			distances.add(Utils.getDistance(target, npc));
		}

		for (int i = 0; i < tiles3.size(); i++) {

			String s = tiles3.get(i);
			WorldTile tile = new WorldTile(Integer.valueOf(s.split("\\s")[0]), Integer.valueOf(s.split("\\s")[1]), Integer.valueOf(s.split("\\s")[2]));
			WorldTasksManager.schedule(new WorldTask() {
				@Override
				public void run() {
					WorldTasksManager.schedule(new WorldTask() {
						int loop;

						@Override
						public void run() {
							if (target.getX() != tile.getX() || target.getY() != tile.getY()) {
								stop();
							}
							if (loop >= 0) {

								World.sendGraphics(npc, new Graphics(3174), new WorldTile(x, y, target.getPlane()));
								if (target.getX() == tile.getX() && target.getY() == tile.getY()) {
									npc.setNextAnimation(new Animation(defs.getAttackEmote()));
									target.applyHit(new Hit(npc, Utils.random(200), HitLook.REGULAR_DAMAGE));
								}
							}
							loop++;
						}
					}, 0, 1);

				}

			}, distances.get(i) - 1);
		}
		return;
	}

	/**
	 * 
	 * Grabs Attack style
	 */

	/**
	 * 
	 * In order ->
	 * 30016 - standrad melee
	 * 30015 - Standard melee, high defence
	 * 30013 - heals % every hit
	 * 30012 - melee, lower hp = higher hit
	 * 30014 - chance to rapid fire and if player doesn't move, continuous hit
	 * 30011 - Says suffer with no voice! Then if player hasn't moved, begins a choke (lock player & loop damage)
	 */

	public void getAttackStyle(final NPC npc, final Entity target) {
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		ROT6 rot6 = (ROT6) npc;
		if (npc.getId() == 30016) {
			npc.setNextAnimation(new Animation(defs.getAttackEmote()));
			delayHit(npc, 0, target, getMeleeHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.MELEE, target)));
		}
		else if (npc.getId() == 30015) {
			npc.setNextAnimation(new Animation(defs.getAttackEmote()));
			delayHit(npc, 0, target, getMeleeHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.MELEE, target)));
		}
		else if (npc.getId() == 30013) {
			npc.setNextAnimation(new Animation(defs.getAttackEmote()));
			delayHit(npc, 0, target, getMeleeHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.MELEE, target)));
			int chance = Utils.random(1, 4);
			if (chance == 1) {
			int heal = Utils.random(240);
			rot6.heal(heal);
			}
		}
		else if (npc.getId() == 30012) {
			npc.setNextAnimation(new Animation(defs.getAttackEmote()));
			delayHit(npc, 0, target, getMeleeHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.MELEE, target)));
		} else if (npc.getId() == 30014) {
			npc.setNextAnimation(new Animation(defs.getAttackEmote()));
			int style = Utils.random(1, 3);
			if (style == 1) {
				KarilAttack(npc, target);
			} else {
				delayHit(npc, 0, target, getRangeHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.RANGE, target)));
			}
		}
		else if (npc.getId() == 30011) {
			npc.setNextAnimation(new Animation(defs.getAttackEmote()));
			int style = Utils.random(1, 5);
			if (style == 1) {
				AhrimAttack(npc, target);
				target.addFreezeDelay(5000);
				rot6.setNextForceTalk(new ForceTalk("You shall suffer with no voice!"));
			} else {
				delayHit(npc, 0, target, getMagicHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.MAGE, target)));
			}
		}
	}

	@Override
	public int attack(final NPC npc, final Entity target) {
		ROT6 rot6 = (ROT6) npc;
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		npc.setCapDamage(1000);
		npc.setForceTargetDistance(64);
		int count = rot6.getCount();
		switch (count) {
		default:
			if (rot6.getHitpoints() <= 15000 && rot6.getId() == 30016) {
				rot6.transformIntoNPC(30015);
			} else if (rot6.getHitpoints() <= 12000 && rot6.getId() == 30015) {
				rot6.transformIntoNPC(30013);
			} else if (rot6.getHitpoints() <= 9000 && rot6.getId() == 30013) {
				rot6.transformIntoNPC(30012);
			} else if (rot6.getHitpoints() <= 6000 && rot6.getId() == 30012) {
				rot6.transformIntoNPC(30014);
			} else if (rot6.getHitpoints() <= 3000 && rot6.getId() == 30014) {
				rot6.transformIntoNPC(30011);
			}
			getAttackStyle(npc, target);
			break;
		}
		return defs.getAttackDelay();
	}
}
