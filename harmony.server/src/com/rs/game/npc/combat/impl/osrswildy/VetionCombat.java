package com.rs.game.npc.combat.impl.osrswildy;


import java.util.ArrayList;
import java.util.List;

import com.rs.Settings;
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
import com.rs.game.npc.others.osrswildy.Vetion;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

/**
 * 
 * @author Mr_Joopz @ Rune-server
 *
 */
public class VetionCombat extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 37611, 37612 };
	}

	private void specialAttack(NPC npc, Entity target) {

		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		int x = target.getX();
		int y = target.getY();
		List<String> tiles3 = new ArrayList<String>();
		ArrayList<Integer> distances = new ArrayList<Integer>();
		ArrayList<Entity> possibleTargets3 = npc.getPossibleTargets();
		for (Entity t : possibleTargets3) {
			if (tiles3.contains(t.getX() + " " + t.getY() + " " + t.getPlane())) {
				continue;
			}
			World.sendProjectile(npc, target, 1231, 80, 30, 40, 20, 5, 0);
			World.sendProjectile(npc, new WorldTile(target.getX() + Utils.random(1, 3), target.getY(), 0), 1231, 80, 30, 40, 20, 5, 0);
			World.sendProjectile(npc, new WorldTile(target.getX() - Utils.random(1, 3), target.getY(), 0), 1231, 80, 30, 40, 20, 5, 0);
			tiles3.add(t.getX() + " " + target.getY() + " " + target.getPlane());
			distances.add(Utils.getDistance(t, npc));
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
							for (Entity t : possibleTargets3) {
								if (target.getX() != tile.getX() || target.getY() != tile.getY()) {
									stop();
								}
								if (t.getX() == tile.getX() && t.getY() == tile.getY()) {
									target.applyHit(new Hit(npc, 300, HitLook.REGULAR_DAMAGE));
									target.setNextGraphics(new Graphics(665));
								}
							}
							stop();
						}
					}, 0, 1);

				}

			}, distances.get(i) - 1);
		}
		return;
	}


	public void getAttackStyle(final NPC npc, final Entity target) {

		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		int style = Utils.random(10);
		switch (style) {
		case 6:
			/**
			 * Shockwave
			 */
			npc.setNextAnimation(new Animation(5507));
			for (Entity e : npc.getPossibleTargets()) {
				if (e.withinDistance(npc, 3)) {
					e.applyHit(new Hit(npc, Utils.random(300, 450), HitLook.REGULAR_DAMAGE));
				}
			}
			break;
		case 1:
		case 2:
		case 3:
			npc.setNextAnimation(new Animation(defs.getAttackEmote()));
			specialAttack(npc, target);
			break;
		default:
			npc.setNextAnimation(new Animation(defs.getAttackEmote()));
			delayHit(npc, 0, target, getMeleeHit(npc, getRandomMaxHit(npc, 300, NPCCombatDefinitions.MELEE, target)));
			break;
		}
	}

	@Override
	public int attack(final NPC npc, final Entity target) {
		Vetion vetion = (Vetion) npc;
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		if (npc.getId() == 37611 && npc.getHitpoints() < npc.getMaxHitpoints() / 2 && vetion.getMinionSpawn() == false) {
			vetion.setMinionSpawn(true);
			npc.setNextForceTalk(new ForceTalk("Kill, my pets!"));
			npc.setCapDamage(0);
			World.spawnNPC(37613, new WorldTile(npc.getX() - 2, npc.getY(), 0), -1, true, true);
			World.spawnNPC(37613, new WorldTile(npc.getX() + 2, npc.getY(), 0), -1, true, true);
		}
		if (Settings.skeletonhellhound >= 2 && npc.getCapDamage() == 0)
			npc.setCapDamage(1000);
		getAttackStyle(npc, target);
		return defs.getAttackDelay();
	}
}
