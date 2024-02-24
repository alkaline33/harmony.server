package com.rs.game.npc.combat.impl.osrswildy;


import java.util.ArrayList;
import java.util.List;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.ForceTalk;
import com.rs.game.Hit;
import com.rs.game.Hit.HitLook;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.npc.others.osrswildy.ChaosFanatic;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

/**
 * 
 * @author Mr_Joopz @ Rune-server
 *
 */
public class ChaosFanaticCombat extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 37619 };
	}

	private void Talk(NPC npc) {
		switch (Utils.getRandom(6)) {
		case 0:
			npc.setNextForceTalk(new ForceTalk("Burn!"));
			break;
		case 1:
			npc.setNextForceTalk(new ForceTalk("WEUGH!"));
			break;
		case 2:
			npc.setNextForceTalk(new ForceTalk("Devilish Oxen Roll!"));
			break;
		case 3:
			npc.setNextForceTalk(new ForceTalk("All your wilderness are belong to them!"));
			break;
		case 4:
			npc.setNextForceTalk(new ForceTalk("AhehHeheuhHhahueHuUEehEahAH"));
			break;
		default:
			npc.setNextForceTalk(new ForceTalk("I shall call him squidgy and he shall be my squidgy!"));
			break;
		}
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
			World.sendProjectile(npc, t, 970, 80, 30, 40, 20, 5, 0);
			World.sendProjectile(npc, new WorldTile(t.getX() + 1, t.getY(), 0), 970, 80, 30, 40, 20, 5, 0);
			World.sendProjectile(npc, new WorldTile(t.getX() - 1, t.getY(), 0), 970, 80, 30, 40, 20, 5, 0);
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
									t.applyHit(new Hit(npc, 310, HitLook.REGULAR_DAMAGE));
								} else if (t.getX() == tile.getX() + 1 && t.getY() == tile.getY()) {
									t.applyHit(new Hit(npc, 310, HitLook.REGULAR_DAMAGE));
								} else if (t.getX() == tile.getX() - 1 && t.getY() == tile.getY()) {
									t.applyHit(new Hit(npc, 310, HitLook.REGULAR_DAMAGE));
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
		npc.setNextAnimation(new Animation(defs.getAttackEmote()));
		Talk(npc);
		switch (style) {
		case 1:
			specialAttack(npc, target);
			break;
		default:
			World.sendProjectile(npc, target, 1003, 41, 16, 41, 0, 16, 0);
			delayHit(npc, 0, target, getMagicHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.MAGE, target)));
			break;
		}
	}

	@Override
	public int attack(final NPC npc, final Entity target) {
		ChaosFanatic chaosfanatic = (ChaosFanatic) npc;
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		getAttackStyle(npc, target);
		return defs.getAttackDelay();
	}
}
