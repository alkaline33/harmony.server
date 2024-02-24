package com.rs.game.npc.combat.impl.xeric;

import java.util.ArrayList;
import java.util.List;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.ForceTalk;
import com.rs.game.Hit;
import com.rs.game.Hit.HitLook;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.npc.xeric.IceFiend;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

/**
 * 
 * @author Mr_Joopz @ Rune-server
 *
 */
public class IceFiendCombat extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 9928 };
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
			World.spawnTemporaryObject(new WorldObject(57263, 10, 0, target.getX() - 1, target.getY(), 0), 5000);
			World.spawnTemporaryObject(new WorldObject(57263, 10, 0, target.getX() + 1, target.getY(), 0), 5000);
			World.spawnTemporaryObject(new WorldObject(57263, 10, 0, target.getX(), target.getY() - 1, 0), 5000);
			World.spawnTemporaryObject(new WorldObject(57263, 10, 0, target.getX(), target.getY() + 1, 0), 5000);
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
							if (loop == 1) {

								if (target.getX() == tile.getX() && target.getY() == tile.getY()) {
									target.applyHit(new Hit(npc, 500, HitLook.REGULAR_DAMAGE));
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
		World.sendProjectile(npc, target, 1017, 80, 30, 40, 20, 5, 0);
		delayHit(npc, 2, target, getRangeHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.RANGE, target)));
		npc.setNextAnimation(new Animation(defs.getAttackEmote()));
	}

	@Override
	public int attack(final NPC npc, final Entity target) {
		IceFiend fiend = (IceFiend) npc;
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		npc.setForceMultiArea(true);
		npc.setForceMultiAttacked(true);
		npc.setCapDamage(1000);
		npc.setForceTargetDistance(64);
		int count = fiend.getCount();
		switch (count) {
		default:
			int pool = Utils.random(0, 6);
			if (pool == 0) {
				if (fiend.getHitpoints() < 5000) {
				poolAttack(npc, target);
					fiend.setNextForceTalk(new ForceTalk("Freeze human!"));
				} else {
					target.addFreezeDelay(4000);
				}
			}
			getAttackStyle(npc, target);
			break;
		}
		return defs.getAttackDelay();
	}
}
