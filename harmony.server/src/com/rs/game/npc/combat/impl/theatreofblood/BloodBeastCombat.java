package com.rs.game.npc.combat.impl.theatreofblood;


import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.ForceTalk;
import com.rs.game.Graphics;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.npc.theatreofblood.BloodBeast;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

/**
 * 
 * @author Mr_Joopz @ Rune-server
 *
 */
public class BloodBeastCombat extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 30216 };
	}

	/**
	 * 
	 * Grabs Attack style
	 */

	public void getAttackStyle(final NPC npc, final Entity target) {

		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		int style = Utils.random(20);
		switch (style) {
		case 0:
			delayHit(npc, 2, target, getMagicHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.MAGE, target)));
			World.sendProjectile(npc, target, 1320, 80, 30, 40, 20, 5, 0);
			npc.setNextAnimation(new Animation(defs.getAttackEmote()));
			break;
		case 15:
			delayHit(npc, 2, target, getRangeHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.RANGE, target)));
			World.sendProjectile(npc, target, 1371, 80, 30, 40, 20, 5, 0);
			npc.setNextAnimation(new Animation(defs.getAttackEmote()));
			break;
		default:
			delayHit(npc, 0, target, getMeleeHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.MELEE, target)));
			npc.setNextAnimation(new Animation(defs.getAttackEmote()));
			break;
		}
	}

	@Override
	public int attack(final NPC npc, final Entity target) {
		BloodBeast bloodbeast = (BloodBeast) npc;
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		int count = bloodbeast.getCount();
		// System.out.println("" + count);
		switch (count) {
		case 50:
			bloodbeast.setCount(count = 1);
			if (target.getY() == bloodbeast.targety && target.getX() == bloodbeast.targetx) {
				WorldTasksManager.schedule(new WorldTask() {
					int loop;

					@Override
					public void run() {
						if (loop >= 0) {
							if (target.getHitpoints() < 1) {
								stop();
							}
							if (target.getY() != bloodbeast.targety || target.getX() != bloodbeast.targetx) {
								stop();
							}
							npc.setNextForceTalk(new ForceTalk("Feel the wrath!!"));
							delayHit(npc, 1, target, getRegularHit(npc, Utils.random(50, 200)));
							target.setNextGraphics(new Graphics(1763));
						}
						loop++;
					}
				}, 0, 1);
			}
			break;
		case 52:
			bloodbeast.setCount(count = 1);
			if (target.getY() == bloodbeast.targety && target.getX() == bloodbeast.targetx || target.getY() == bloodbeast.targety + 1 && target.getX() == bloodbeast.targetx + 1 || target.getY() == bloodbeast.targety && target.getX() == bloodbeast.targetx + 1 || target.getY() == bloodbeast.targety + 1 && target.getX() == bloodbeast.targetx || target.getY() == bloodbeast.targety - 1 && target.getX() == bloodbeast.targetx - 1 || target.getY() == bloodbeast.targety - 1 && target.getX() == bloodbeast.targetx || target.getY() == bloodbeast.targety && target.getX() == bloodbeast.targetx - 1) {
				WorldTasksManager.schedule(new WorldTask() {
					int loop;

					@Override
					public void run() {
						if (loop == 0) {
							if (target.getHitpoints() < 1) {
								stop();
							}
							delayHit(npc, 1, target, getRegularHit(npc, 150));
							npc.heal(150);
							target.setNextGraphics(new Graphics(1603));
						} else if (loop == 1) {
							if (target.getHitpoints() < 1) {
								stop();
							}
							delayHit(npc, 1, target, getRegularHit(npc, 150));
							npc.heal(150);
							target.setNextGraphics(new Graphics(1603));
						} else if (loop == 2) {
							if (target.getHitpoints() < 1) {
								stop();
							}
							delayHit(npc, 1, target, getRegularHit(npc, 150));
							npc.heal(150);
							target.setNextGraphics(new Graphics(1603));
						} else if (loop == 3) {
							if (target.getHitpoints() < 1) {
								stop();
							}
							delayHit(npc, 1, target, getRegularHit(npc, 150));
							npc.heal(150);
							target.setNextGraphics(new Graphics(1603));
						} else if (loop == 4) {
							stop();

						}
						loop++;
					}
				}, 0, 1);
			}
			break;
		default:
			int pool = Utils.random(0, 25);
			if (pool == 7) {
				npc.setNextForceTalk(new ForceTalk("Defend me, my children!"));
				World.spawnNPC(30218, new WorldTile(2608, 9638, 0), -1, true, true);
				World.spawnNPC(30218, new WorldTile(2608, 9635, 0), -1, true, true);
				World.spawnNPC(30218, new WorldTile(2608, 9641, 0), -1, true, true);
				World.spawnNPC(30218, new WorldTile(2613, 9639, 0), -1, true, true);
			}
			if (pool == 0) {
				npc.setNextForceTalk(new ForceTalk("Bleeeeeeeed!"));
				bloodbeast.targetx = target.getX();
				bloodbeast.targety = target.getY();
				bloodbeast.setCount(52);
			}
			if (pool == 3) {
				npc.setNextForceTalk(new ForceTalk("Stay!"));
				bloodbeast.targetx = target.getX();
				bloodbeast.targety = target.getY();
				bloodbeast.setCount(50);
				target.addFreezeDelay(5000);
				npc.setNextAnimation(new Animation(defs.getAttackEmote()));
			} else {
				getAttackStyle(npc, target);
			}
			break;
		}
		return defs.getAttackDelay();
	}
}
