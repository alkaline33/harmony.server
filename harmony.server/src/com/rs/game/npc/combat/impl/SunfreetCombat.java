package com.rs.game.npc.combat.impl;


import java.util.ArrayList;
import java.util.List;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.Graphics;
import com.rs.game.Hit;
import com.rs.game.Hit.HitLook;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.npc.others.Sunfreet;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

/**
 * 
 * @author Mr_Joopz @ Rune-server
 *
 */
public class SunfreetCombat extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 15222 };
	}

	/**
	 * Chuck pool
	 */

	private void poolAttack(NPC npc, Entity target) {

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

								World.sendGraphics(npc, new Graphics(540), new WorldTile(x, y, target.getPlane()));
								if (target.getX() == tile.getX() && target.getY() == tile.getY()) {
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
	 * 
	 * Grabs Attack style
	 */

	public void getAttackStyle(final NPC npc, final Entity target) {
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		int attackstyle = Utils.random(12);
			 if (attackstyle >= 0 && attackstyle <= 2) {
				delayHit(npc, 1, target, getRangeHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.RANGE, target)));
				npc.setNextAnimation(new Animation(defs.getAttackEmote()));
				World.sendProjectile(npc, target, 1224, 41, 16, 41, 35, 16, 0);
			} else if (attackstyle >= 5 && attackstyle <= 7) {
				delayHit(npc, 1, target, getMagicHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.MAGE, target)));
				npc.setNextAnimation(new Animation(16311));
				World.sendProjectile(npc, target, 1213, 41, 16, 41, 35, 16, 0);
			} else if (attackstyle == 11 && npc.getHitpoints() > 3000) {
				/**
				 * Special Attack
				 */
				npc.setNextAnimation(new Animation(16318));
				for (Entity t : npc.getPossibleTargets()) {
					if (!t.withinDistance(npc, 18)) {
						continue;
					}
					WorldTasksManager.schedule(new WorldTask() {
						int loop;

						@Override
						public void run() {
							if (loop == 2) {
								t.setNextGraphics(new Graphics(3002));
								t.setNextGraphics(new Graphics(3003));
								npc.setNextAnimation(new Animation(16317));
								npc.heal(Utils.random(300));
								World.sendProjectile(npc, t, 3004, 60, 32, 50, 50, 0, 0);
								delayHit(npc, 0, t, getMagicHit(npc, Utils.random(300)));
								delayHit(npc, 0, t, getMeleeHit(npc, Utils.random(300)));
								stop();
							}
							loop++;
						}
					}, 0, 1);
					break;
				}
				
			} else {
				if (target.withinDistance(npc, 5)) {
					delayHit(npc, 0, target, getMeleeHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.MELEE, target)));
					npc.setNextAnimation(new Animation(defs.getAttackEmote()));
				} else {
					delayHit(npc, 1, target, getRangeHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.RANGE, target)));
					npc.setNextAnimation(new Animation(defs.getAttackEmote()));
					World.sendProjectile(npc, target, 1224, 41, 16, 41, 35, 16, 0);
				}
			}
		
	}

	@Override
	public int attack(final NPC npc, final Entity target) {
		Sunfreet sunfreet = (Sunfreet) npc;
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		getAttackStyle(npc, target);
		return defs.getAttackDelay();
	}
}
