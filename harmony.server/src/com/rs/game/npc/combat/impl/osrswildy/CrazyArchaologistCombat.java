package com.rs.game.npc.combat.impl.osrswildy;


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
import com.rs.game.npc.others.osrswildy.CrazyArchaeologist;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

/**
 * 
 * @author Mr_Joopz @ Rune-server
 *
 */
public class CrazyArchaologistCombat extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 37618 };
	}

	private void Talk(NPC npc) {
		switch (Utils.getRandom(6)) {
		case 0:
			npc.setNextForceTalk(new ForceTalk("I'm Bellock - respect me!"));
			break;
		case 1:
			npc.setNextForceTalk(new ForceTalk("Get off my site!"));
			break;
		case 2:
			npc.setNextForceTalk(new ForceTalk("No-one messes with Bellock's dig!"));
			break;
		case 3:
			npc.setNextForceTalk(new ForceTalk("These ruins are mine!"));
			break;
		case 4:
			npc.setNextForceTalk(new ForceTalk("Taste my knowledge!"));
			break;
		default:
			npc.setNextForceTalk(new ForceTalk("You belong in a museum!"));
			break;
		}
	}

	private void specialAttack(NPC npc, Entity target) {

		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		npc.setNextForceTalk(new ForceTalk("Rain of knowledge!"));
		// npc.setNextAnimation(new Animation());
		int x = target.getX();
		int y = target.getY();
		List<String> tiles3 = new ArrayList<String>();
		ArrayList<Integer> distances = new ArrayList<Integer>();
		ArrayList<Entity> possibleTargets3 = npc.getPossibleTargets();
		for (Entity t : possibleTargets3) {
			if (tiles3.contains(t.getX() + " " + t.getY() + " " + t.getPlane())) {
				continue;
			}
			World.sendProjectile(npc, t, 140, 80, 30, 40, 20, 5, 0);
			World.sendProjectile(npc, new WorldTile(t.getX() + 1, t.getY(), 0), 140, 80, 30, 40, 20, 5, 0);
			World.sendProjectile(npc, new WorldTile(t.getX() - 1, t.getY(), 0), 140, 80, 30, 40, 20, 5, 0);

			World.sendProjectile(npc, new WorldTile(t.getX(), t.getY() - 1, 0), 140, 80, 30, 40, 20, 5, 0);
			World.sendProjectile(npc, new WorldTile(t.getX() + 1, t.getY() - 1, 0), 140, 80, 30, 40, 20, 5, 0);
			World.sendProjectile(npc, new WorldTile(t.getX() - 1, t.getY() - 1, 0), 140, 80, 30, 40, 20, 5, 0);

			World.sendProjectile(npc, new WorldTile(t.getX() + 1, t.getY() + 1, 0), 140, 80, 30, 40, 20, 5, 0);
			World.sendProjectile(npc, new WorldTile(t.getX(), t.getY() + 1, 0), 140, 80, 30, 40, 20, 5, 0);
			World.sendProjectile(npc, new WorldTile(t.getX() - 1, t.getY() + 1, 0), 140, 80, 30, 40, 20, 5, 0);
			tiles3.add(t.getX() + " " + t.getY() + " " + t.getPlane());
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
								if (t.getX() != tile.getX() || t.getY() != tile.getY()) {
									stop();
								}
								if (t.getX() == tile.getX() && t.getY() == tile.getY()) {
									t.applyHit(new Hit(npc, 240, HitLook.REGULAR_DAMAGE));
									t.setNextGraphics(new Graphics(659));
								} else if (t.getX() == tile.getX() + 1 && t.getY() == tile.getY()) {
									t.applyHit(new Hit(npc, 240, HitLook.REGULAR_DAMAGE));
									t.setNextGraphics(new Graphics(659));
								} else if (t.getX() == tile.getX() - 1 && t.getY() == tile.getY()) {
									t.applyHit(new Hit(npc, 240, HitLook.REGULAR_DAMAGE));
									t.setNextGraphics(new Graphics(659));
								} else if (t.getX() == tile.getX() && t.getY() - 1 == tile.getY()) {
									t.applyHit(new Hit(npc, 240, HitLook.REGULAR_DAMAGE));
									t.setNextGraphics(new Graphics(659));
								} else if (t.getX() == tile.getX() + 1 && t.getY() - 1 == tile.getY()) {
									t.applyHit(new Hit(npc, 240, HitLook.REGULAR_DAMAGE));
									t.setNextGraphics(new Graphics(659));
								} else if (t.getX() == tile.getX() - 1 && t.getY() - 1 == tile.getY()) {
									t.applyHit(new Hit(npc, 240, HitLook.REGULAR_DAMAGE));
									t.setNextGraphics(new Graphics(659));
								} else if (t.getX() == tile.getX() && t.getY() + 1 == tile.getY()) {
									t.applyHit(new Hit(npc, 240, HitLook.REGULAR_DAMAGE));
									t.setNextGraphics(new Graphics(659));
								} else if (t.getX() == tile.getX() + 1 && t.getY() + 1 == tile.getY()) {
									t.applyHit(new Hit(npc, 240, HitLook.REGULAR_DAMAGE));
									t.setNextGraphics(new Graphics(659));
								} else if (t.getX() == tile.getX() - 1 && t.getY() + 1 == tile.getY()) {
									t.applyHit(new Hit(npc, 240, HitLook.REGULAR_DAMAGE));
									t.setNextGraphics(new Graphics(659));
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
		int style = Utils.random(6);
		switch (style) {
		case 1:
			npc.setNextAnimation(new Animation(defs.getAttackEmote()));
			specialAttack(npc, target);
			break;
		default:
			Talk(npc);
			if (target.withinDistance(npc, 1)) {
				npc.setNextAnimation(new Animation(422));
				delayHit(npc, 0, target, getMeleeHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.MELEE, target)));
			} else {
				npc.setNextAnimation(new Animation(defs.getAttackEmote()));
				delayHit(npc, 0, target, getRangeHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.RANGE, target)));
			}
			break;
		}
	}

	@Override
	public int attack(final NPC npc, final Entity target) {
		CrazyArchaeologist crazya = (CrazyArchaeologist) npc;
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		getAttackStyle(npc, target);
		return defs.getAttackDelay();
	}
}
