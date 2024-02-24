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
import com.rs.game.npc.others.sire.Sire;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

/**
 * 
 * @author Mr_Joopz @ Rune-server
 *
 */
public class AbyssalSire extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 36886, 36890, 36891 };
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
		if (npc.getId() == 36890) {
			delayHit(npc, 0, target, getMeleeHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.MELEE, target)));
			npc.setNextAnimation(new Animation(defs.getAttackEmote()));
		}
	}

	@Override
	public int attack(final NPC npc, final Entity target) {
		Sire sire = (Sire) npc;
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		npc.setForceMultiArea(true);
		npc.setForceMultiAttacked(true);
		npc.setCapDamage(1000);
		// npc.setForceTargetDistance(64);
		// npc.setForceFollowClose(true);
		int count = sire.getCount();
		switch (count) {
		case 0:
			npc.transformIntoNPC(36890);
			npc.setNextWorldTile(new WorldTile(npc.getX(), npc.getY() - 14, npc.getPlane()));
			sire.setCount(count += 1);
			break;
		/**
		 * Bomb phase
		 */
		case 50:
			npc.transformIntoNPC(36890);
			sire.setCount(count += 1);
			World.spawnNPC(36918, new WorldTile(target.getX() + 1, target.getY(), target.getPlane()), -1, true, true);
			break;
		case 52:
			npc.setNextAnimation(new Animation(37098));
			sire.setCount(count += 1);
			World.spawnNPC(36918, new WorldTile(target.getX() + 1, target.getY(), target.getPlane()), -1, true, true);
			World.spawnNPC(36918, new WorldTile(target.getX() + 1, target.getY(), target.getPlane()), -1, true, true);
			break;
		case 53:
			target.setNextWorldTile(new WorldTile(npc.getX(), npc.getY(), npc.getPlane()));
			sire.setCount(count += 1);
			World.spawnNPC(36918, new WorldTile(target.getX() + 1, target.getY(), target.getPlane()), -1, true, true);
			World.spawnNPC(36918, new WorldTile(target.getX() + 1, target.getY(), target.getPlane()), -1, true, true);
			break;
		case 54:
			if (target.withinDistance(npc, 4)) {
				target.applyHit(new Hit(target, 720, HitLook.REGULAR_DAMAGE));
				sire.setCount(2);
			}
			break;
		case 49:
			sire.setCount(2);
			int pools = Utils.random(0, 4);
			if (pools == 0) {
				poolAttack(npc, target);
			}
			getAttackStyle(npc, target);

			break;
		default:
			sire.setCount(count += 1);
			int pool = Utils.random(0, 4);
			if (pool == 0) {
				poolAttack(npc, target);
			}
			getAttackStyle(npc, target);
			break;
		}
		return defs.getAttackDelay();
	}
}
