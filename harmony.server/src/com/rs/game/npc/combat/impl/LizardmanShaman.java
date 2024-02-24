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
import com.rs.game.npc.others.Lizardman;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

/**
 * 
 * @author Mr_Joopz @ Rune-server
 *
 */
public class LizardmanShaman extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 37766 };
	}

	/**
	 * Chuck pool
	 */

	private void acidAttack(NPC npc, Entity target) {

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

								World.sendGraphics(npc, new Graphics(999), new WorldTile(x, y, target.getPlane()));
								if (target.getX() == tile.getX() && target.getY() == tile.getY()) {
									target.getPoison().makePoisoned(100);
									target.applyHit(new Hit(npc, Utils.random(300), HitLook.REGULAR_DAMAGE));
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
			delayHit(npc, 0, target, getMeleeHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.MELEE, target)));
			npc.setNextAnimation(new Animation(defs.getAttackEmote()));
	}

	@Override
	public int attack(final NPC npc, final Entity target) {
		Lizardman lizard = (Lizardman) npc;
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		npc.setForceMultiArea(true);
		npc.setForceMultiAttacked(true);
		npc.setCapDamage(1000);
		npc.setForceTargetDistance(64);
		npc.setCantFollowUnderCombat(true);
		npc.setForceFollowClose(false);
		int count = lizard.getCount();
		switch (count) {
		case 8:
			npc.setNextAnimation(new Animation(37193));
			acidAttack(npc, target);
			lizard.setCount(count = 0);
			break;
		default:
			lizard.setCount(count += 1);
			getAttackStyle(npc, target);
			break;
		}
		return defs.getAttackDelay();
	}
}
