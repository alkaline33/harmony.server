package com.rs.game.npc.combat.impl.xeric;


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
import com.rs.game.npc.xeric.Tekton;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

/**
 * 
 * @author Mr_Joopz @ Rune-server
 *
 */
public class TektonCombat extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 38540, 38542, 38544 };
	}

	/**
	 * 
	 * Grabs Attack style
	 */

	public void getAttackStyle(final NPC npc, final Entity target) {

		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		if (npc.getId() == 38542) {
			delayHit(npc, 0, target, getMeleeHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.MELEE, target)));
			npc.setNextAnimation(new Animation(defs.getAttackEmote()));
		}
		else if (npc.getId() == 38544) {
			delayHit(npc, 0, target, getMeleeHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.MELEE, target)));
			npc.setNextAnimation(new Animation(defs.getAttackEmote()));
		}
	}

	@Override
	public int attack(final NPC npc, final Entity target) {
		Tekton tekton = (Tekton) npc;
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		int count = tekton.getCount();
		// System.out.println("" + count);
		switch (count) {
		case 0:
			npc.transformIntoNPC(38542);
			npc.setNextWorldTile(new WorldTile(3277, 5265, npc.getPlane()));
			tekton.setCount(count += 1);
			break;
		case 50:
			tekton.setCount(count = 1);
			if (target.getY() == tekton.targety && target.getX() == tekton.targetx) {		
				target.applyHit(new Hit(npc, Utils.random(10000), HitLook.REGULAR_DAMAGE));
			}
			break;
		case 52:
			tekton.setCount(count = 1);
			if (target.getY() == tekton.targety && target.getX() == tekton.targetx || target.getY() == tekton.targety + 1 && target.getX() == tekton.targetx + 1 || target.getY() == tekton.targety && target.getX() == tekton.targetx + 1 || target.getY() == tekton.targety + 1 && target.getX() == tekton.targetx || target.getY() == tekton.targety - 1 && target.getX() == tekton.targetx - 1 || target.getY() == tekton.targety - 1 && target.getX() == tekton.targetx || target.getY() == tekton.targety && target.getX() == tekton.targetx - 1) {
				WorldTasksManager.schedule(new WorldTask() {
					int loop;

					@Override
					public void run() {
						if (loop == 0) {
							if (target.getHitpoints() < 1) {
								stop();
							}
							delayHit(npc, 1, target, getRegularHit(npc, 200));
							target.setNextGraphics(new Graphics(1463));
						} else if (loop == 2) {
							if (target.getHitpoints() < 1) {
								stop();
							}
							delayHit(npc, 1, target, getRegularHit(npc, 250));
							target.setNextGraphics(new Graphics(1463));
						} else if (loop == 4) {
							if (target.getHitpoints() < 1) {
								stop();
							}
							delayHit(npc, 1, target, getRegularHit(npc, 300));
							target.setNextGraphics(new Graphics(1463));
						} else if (loop == 5) {
							if (target.getHitpoints() < 1) {
								stop();
							}
							delayHit(npc, 1, target, getRegularHit(npc, 100));
							target.setNextGraphics(new Graphics(1463));
						} else if (loop == 6) {
							stop();

						}
						loop++;
					}
				}, 0, 1);
			}
			break;
		default:
			List<Integer> npcs = World.getRegion(npc.getRegionId()).getNPCsIndexes();
			if (npcs.size() <= 4) {
				npc.setCapDamage(1000);
			}
			tekton.setCount(count += 1);
			int pool = Utils.random(0, 10);
			if (npc.getId() == 38542 && npc.getHitpoints() < 10000) {
				npc.transformIntoNPC(38544);
				npc.setNextForceTalk(new ForceTalk("Turn them to ash!"));
				npc.setCapDamage(0);
				World.spawnNPC(7773, new WorldTile(3277, 5269, 0), -1, true, true);
				World.spawnNPC(7773, new WorldTile(3278, 5267, 0), -1, true, true);
				World.spawnNPC(7773, new WorldTile(3283, 5265, 0), -1, true, true);
			}
			if (pool == 0 && npc.getId() == 38544) {
				npc.setNextForceTalk(new ForceTalk("Burn!"));
				tekton.targetx = target.getX();
				tekton.targety = target.getY();
				tekton.setCount(52);
				npc.setNextAnimation(new Animation(37492));
			}
			if (pool == 3 && npc.getId() == 38544) {
				npc.setNextForceTalk(new ForceTalk("Finish them!"));
				tekton.targetx = target.getX();
				tekton.targety = target.getY();
				tekton.setCount(50);
				npc.setNextAnimation(new Animation(37492));
			} else {
				getAttackStyle(npc, target);
			}
			break;
		}
		return defs.getAttackDelay();
	}
}
