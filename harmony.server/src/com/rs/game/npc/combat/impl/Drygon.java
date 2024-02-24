package com.rs.game.npc.combat.impl;

import java.util.ArrayList;
import java.util.HashMap;

import com.rs.Settings;
import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.ForceTalk;
import com.rs.game.Graphics;
import com.rs.game.Hit;
import com.rs.game.Hit.HitLook;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.npc.familiar.Familiar;
import com.rs.game.player.Player;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Colors;
import com.rs.utils.Utils;

/**
 * 
 * 
 * @author Mr_Joopz
 *
 */

public class Drygon extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 12900 };
	}

	@Override
	public int attack(final NPC npc, final Entity target) {
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		npc.setForceMultiArea(true);
		npc.setForceMultiAttacked(true);
		int attackStyle = Utils.getRandom(15);
		//final Player player = (Player) target;
		Hit damage1 = getMeleeHit(npc, 10000);
		
		
		/**
		 * Special attack that stops players from damaging until the vials have been added
		 */
		if (attackStyle == 0) {
			ArrayList<Entity> possibleTargets = npc.getPossibleTargets();
			final HashMap<String, int[]> tiles = new HashMap<String, int[]>();
			for (Entity t : possibleTargets) {
				if (t instanceof Player) {
					Player p = (Player) t;
					p.sendMessage(Colors.orange + "Hope Devourer won't take any damage until you put 3 vials of water on the Lava Crater!");
				}
				String key = t.getX() + "_" + t.getY();
				npc.setNextAnimation(new Animation(14459));
				if (!tiles.containsKey(t.getX() + "_" + t.getY())) {
					tiles.put(key, new int[] { t.getX(), t.getY() });
						npc.setCapDamage(0);
						Settings.WaterAdded = 1;
					npc.setNextForceTalk(new ForceTalk("3 vials of pure water is all that save you now!"));

						World.spawnTemporaryObject(new WorldObject(224,
							10, 0, t.getX(), t.getY(), 0), 1400);
						World.spawnTemporaryObject(new WorldObject(20232,
							10, 0, 2265, 3603, 0), 15000);
				}
			}
			WorldTasksManager.schedule(new WorldTask() {
				@Override
				public void run() {
					ArrayList<Entity> possibleTargets = npc
							.getPossibleTargets();
					for (int[] tile : tiles.values()) {

						World.sendGraphics(null, new Graphics(1903),
								new WorldTile(tile[0], tile[1], 0));
						for (Entity t : possibleTargets)
							if (t.getX() == tile[0] && t.getY() == tile[1])
								t.applyHit(new Hit(npc,
										200,
										HitLook.REGULAR_DAMAGE));
					}
					stop();
				}

			}, 5);
			return defs.getAttackDelay();
			/**
			 * Healing attack
			 */
		} else if (attackStyle == 1 || attackStyle == 5) {
			if (target instanceof Familiar) {
				target.applyHit(damage1);
			}
			if (Settings.WaterAdded > 0 && Settings.WaterAdded < 3) {
				npc.setCapDamage(0);
			} else if (Settings.WaterAdded == 0 || Settings.WaterAdded > 3) {
				npc.setCapDamage(700);
			}
			npc.setNextAnimation(new Animation(14460));
			npc.setNextGraphics(new Graphics(444));
			target.setNextGraphics(new Graphics(482));
		
			delayHit(
					npc,
					defs.getAttackDelay(),
					target,
					getRangeHit(
							npc,
							getRandomMaxHit(npc, defs.getMaxHit(),
									NPCCombatDefinitions.RANGE, target)));
			int damage = getRandomMaxHit(npc,
					defs.getMaxHit(), NPCCombatDefinitions.RANGE, target);
			npc.heal(damage * 3 + 300);
			return defs.getAttackDelay();
		}
		/**
		 * Basic melee attack
		 */
		if (attackStyle == 2 || attackStyle == 3 || attackStyle == 4 || attackStyle == 15 || attackStyle == 13 || attackStyle == 12) { // normal Melee move
			if (target instanceof Familiar) {
				target.applyHit(damage1);
			}
			if (Settings.WaterAdded > 0 && Settings.WaterAdded < 3) {
				npc.setCapDamage(0);
			} else if (Settings.WaterAdded == 0 || Settings.WaterAdded >= 3) {
				npc.setCapDamage(700);
			}
			npc.setNextAnimation(new Animation(14456));
			delayHit(
					npc,
					defs.getAttackDelay(),
					target,
					getMeleeHit(
							npc,
							getRandomMaxHit(npc, defs.getMaxHit(),
									NPCCombatDefinitions.MELEE, target)));
			return defs.getAttackDelay();
			/**
			 * Basic ranged attack
			 */
		} else if (attackStyle == 6 || attackStyle == 7 || attackStyle == 8 || attackStyle == 10 || attackStyle == 14) { // normal Ranged move
			if (target instanceof Familiar) {
				target.applyHit(damage1);
			}
			if (Settings.WaterAdded > 0 && Settings.WaterAdded < 3) {
				npc.setCapDamage(0);
			} else if (Settings.WaterAdded == 0 || Settings.WaterAdded >= 3) {
				npc.setCapDamage(700);
			}
			npc.setNextAnimation(new Animation(14457));
			delayHit(
					npc,
					defs.getAttackDelay(),
					target,
					getRangeHit(
							npc,
							getRandomMaxHit(npc, defs.getMaxHit(),
									NPCCombatDefinitions.RANGE, target)));
			World.sendProjectile(npc, target, 1936, 34, 16, 30, 35, 16, 0);
			return defs.getAttackDelay();
			/**
			 * Prayer attack
			 */
		} else if (attackStyle == 9 || attackStyle == 11) {
			if (target instanceof Familiar) {
				target.applyHit(damage1);
			}
			if (Settings.WaterAdded > 0 && Settings.WaterAdded < 3) {
				npc.setCapDamage(0);
			} else if (Settings.WaterAdded == 0 || Settings.WaterAdded >= 3) {
				npc.setCapDamage(700);
			}
			npc.setNextAnimation(new Animation(14458));
			target.setNextGraphics(new Graphics(741));
			//target.moveLocation(target.getChunkX() - 1, target.getChunkY() - 1, 0);
			target.addFreezeDelay(600);
			npc.setNextGraphics(new Graphics(499));	
			target.applyHit(new Hit(npc,
					Utils.getRandom(600),
					HitLook.REGULAR_DAMAGE));
			final int damage = 600;
	
		//	target.getPrayer().drainPrayer((Math.round(damage / 10)));
		//	player.setPrayerDelay(Utils.getRandom(5) + 5);
			return defs.getAttackDelay();

		}
		return defs.getAttackDelay();
	}
	
}
