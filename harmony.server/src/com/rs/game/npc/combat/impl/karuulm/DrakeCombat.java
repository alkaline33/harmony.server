package com.rs.game.npc.combat.impl.karuulm;


import java.util.ArrayList;
import java.util.List;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.Hit;
import com.rs.game.Hit.HitLook;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.npc.others.karuulm.Drake;
import com.rs.game.player.Player;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

/**
 * 
 * @author Mr_Joopz @ Rune-server
 *
 */
public class DrakeCombat extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 39612 };
	}

	public void SpecialAttack(final NPC npc, final Entity target) {
		List<String> tiles3 = new ArrayList<String>();
		ArrayList<Integer> distances = new ArrayList<Integer>();
		ArrayList<Entity> possibleTargets3 = npc.getPossibleTargets();
		for (Entity t : possibleTargets3) {
			if (tiles3.contains(target.getX() + " " + target.getY() + " " + target.getPlane())) {
				continue;
			}
			tiles3.add(target.getX() + " " + target.getY() + " " + target.getPlane());
			((Player) target).getPackets().sendProjectile(null, new WorldTile(npc), new WorldTile(target), 498, 30, 30, 1, 0, 0, 0, 0);
			distances.add(Utils.getDistance(target, npc));
		}
		for (int i = 0; i < tiles3.size(); i++) {
			String s = tiles3.get(i);
			WorldTile tile = new WorldTile(Integer.valueOf(s.split("\\s")[0]), Integer.valueOf(s.split("\\s")[1]), Integer.valueOf(s.split("\\s")[2]));
			WorldTasksManager.schedule(new WorldTask() {
				@Override
				public void run() {
					ArrayList<Entity> possibleTargets3 = npc.getPossibleTargets();
					for (Entity t : possibleTargets3) {
						if (target.getX() == tile.getX() && target.getY() == tile.getY()) {
							target.applyHit(new Hit(npc, Utils.random(60, 80), HitLook.REGULAR_DAMAGE));
							target.applyHit(new Hit(npc, Utils.random(60, 80), HitLook.REGULAR_DAMAGE));
							target.applyHit(new Hit(npc, Utils.random(60, 80), HitLook.REGULAR_DAMAGE));
							target.applyHit(new Hit(npc, Utils.random(60, 80), HitLook.REGULAR_DAMAGE));
						}
					}
				}

			}, distances.get(i) - 1);
		}
		// return 7;
	}

	public void getAttackStyle(final NPC npc, final Entity target) {

		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		int style = Utils.random(2);
		npc.setNextAnimation(new Animation(defs.getAttackEmote()));
		switch (style) {
		default:
			if (target.withinDistance(npc, 4)) {
				delayHit(npc, 0, target, getMeleeHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.MELEE, target)));
			} else {
				delayHit(npc, 0, target, getRangeHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.RANGE, target)));
			}
			break;
		case 1:
			delayHit(npc, 2, target, getRangeHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.RANGE, target)));
			break;

		}
	}

	@Override
	public int attack(final NPC npc, final Entity target) {
		Drake npcs = (Drake) npc;
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		int count = npcs.getCount();
		npc.setNextAnimation(new Animation(defs.getAttackEmote()));
		// if (target.tile
		switch (count) {
		default:
			npcs.setCount(npcs.getCount() + 1);
			getAttackStyle(npc, target);
			break;
		case 7:
			npcs.setCount(0);
			SpecialAttack(npc, target);
			npc.setNextAnimation(new Animation(38275));
			/**
			 * projectile special like anivia
			 */
			break;
		}
		return defs.getAttackDelay();
	}
}
