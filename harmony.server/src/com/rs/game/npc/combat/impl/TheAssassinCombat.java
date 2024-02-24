package com.rs.game.npc.combat.impl;

import java.util.ArrayList;
import java.util.List;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.ForceTalk;
import com.rs.game.Hit;
import com.rs.game.Hit.HitLook;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.npc.others.TheAssassin;
import com.rs.game.player.Player;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

/**
 * 
 * @author Mr_Joopz @ Rune-server
 *
 */
public class TheAssassinCombat extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 30149 };
	}

	private void bleedAttack(NPC npc, Entity target) {
		TheAssassin assassin = (TheAssassin) npc;
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		int x = target.getX();
		int y = target.getY();
		List<String> tiles3 = new ArrayList<String>();
		ArrayList<Integer> distances = new ArrayList<Integer>();
		ArrayList<Entity> possibleTargets3 = npc.getPossibleTargets();

		for (Entity t : possibleTargets3) {
			if (t.withinDistance(npc, 3)) {
				t.applyHit(new Hit(npc, 800, HitLook.REGULAR_DAMAGE));
			} else {
				t.getPoison().makePoisoned(100);
				t.applyHit(new Hit(npc, 100, HitLook.REGULAR_DAMAGE));
			}
		}

	}

	/**
	 * Chuck pool
	 */

	private void poolAttack(NPC npc, Entity target) {
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		List<String> tiles3 = new ArrayList<String>();
		ArrayList<Integer> distances = new ArrayList<Integer>();
		ArrayList<Entity> possibleTargets3 = npc.getPossibleTargets();
		for (Entity t : possibleTargets3) {
			if (tiles3.contains(t.getX() + " " + t.getY() + " " + t.getPlane()))
				continue;
			tiles3.add(t.getX() + " " + t.getY() + " " + t.getPlane());
			((Player) t).getPackets().sendProjectile(null, new WorldTile(npc), new WorldTile(t), 2148, 30, 30, 1, 0, 0, 0, 0);
			distances.add(Utils.getDistance(t, npc));
		}
		for (int i = 0; i < tiles3.size(); i++) {
			String s = tiles3.get(i);
			WorldTile tile = new WorldTile(Integer.valueOf(s.split("\\s")[0]), Integer.valueOf(s.split("\\s")[1]), Integer.valueOf(s.split("\\s")[2]));
			WorldTasksManager.schedule(new WorldTask() {
				@Override
				public void run() {
					ArrayList<Entity> possibleTargets3 = npc.getPossibleTargets();
					for (Entity t : possibleTargets3) {
						if (t.getX() == tile.getX() && t.getY() == tile.getY())
							t.applyHit(new Hit(npc, 700, HitLook.REGULAR_DAMAGE));
					}
				}

			}, distances.get(i) - 1);
		}
	}

	/**
	 * 
	 * Grabs Attack style
	 */

	public void getAttackStyle(final NPC npc, final Entity target) {

		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		if (Utils.random(3) == 0) {
			List<String> tiles3 = new ArrayList<String>();
			ArrayList<Integer> distances = new ArrayList<Integer>();
			ArrayList<Entity> possibleTargets3 = npc.getPossibleTargets();
			for (Entity t : possibleTargets3) {
				delayHit(npc, 2, t, getRangeHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.RANGE, target)));
				npc.setNextAnimation(new Animation(10499));
			}
		} else {
		delayHit(npc, 2, target, getMeleeHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.MELEE, target)));
		npc.setNextAnimation(new Animation(defs.getAttackEmote()));
		}
	}

	@Override
	public int attack(final NPC npc, final Entity target) {
		TheAssassin assassin = (TheAssassin) npc;
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		npc.setForceMultiArea(true);
		npc.setForceMultiAttacked(true);
		npc.setCapDamage(1000);
		npc.setForceTargetDistance(64);
		int count = assassin.getCount();
		switch (count) {
		default:
			int pool = Utils.random(0, 10);
			if (pool == 0) {
				if (Utils.random(2) == 0) {
					bleedAttack(npc, target);
					assassin.setNextForceTalk(new ForceTalk("Destroy!"));
					assassin.setNextAnimation(new Animation(10502));
				} else {
					poolAttack(npc, target);
					assassin.setNextForceTalk(new ForceTalk("Die now! By the power of Zaros!"));
				assassin.setNextAnimation(new Animation(11993));
				}
			}
			getAttackStyle(npc, target);
			break;
		}
		return defs.getAttackDelay();
	}
}
