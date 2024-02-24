package com.rs.game.npc.combat.impl;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.Graphics;
import com.rs.game.Hit;
import com.rs.game.Hit.HitLook;
import com.rs.game.World;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.utils.Utils;

/**
 * 
 * 
 * @author Mr_Joopz
 *
 */

public class Sliske extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 14262 };
	}

	@Override
	public int attack(final NPC npc, final Entity target) {
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		npc.setForceMultiArea(true);
		npc.setForceMultiAttacked(true);
		npc.setCapDamage(800);
		npc.setCantFollowUnderCombat(true);
		npc.setForceFollowClose(false);
		int attackStyle = Utils.getRandom(15);
		//final Player player = (Player) target;
		Hit damage1 = getMeleeHit(npc, 10000);
		
		
		/**
		 * Special attack that stops players from damaging until the vials have been added
		 */
		if (attackStyle == 0) {
			if (target instanceof NPC) {
				target.applyHit(damage1);
			}
			npc.setNextAnimation(new Animation(11373));
			target.applyHit(new Hit(npc,
					Utils.getRandom(250),
					HitLook.REGULAR_DAMAGE));
			target.applyHit(new Hit(npc,
					Utils.getRandom(100),
					HitLook.POISON_DAMAGE));
			target.getPoison().isPoisoned();
			target.applyHit(new Hit(npc,
					Utils.getRandom(100),
					HitLook.DESEASE_DAMAGE));
			return defs.getAttackDelay();
		} else if (attackStyle == 1 || attackStyle == 5) {
			if (target instanceof NPC) {
				target.applyHit(damage1);
			}
			npc.setNextAnimation(new Animation(11365));
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
					defs.getMaxHit(), NPCCombatDefinitions.MAGE, target);
			npc.heal(damage);
			return defs.getAttackDelay();
		}
		/**
		 * Basic melee attack
		 */
		if (attackStyle == 2 || attackStyle == 3 || attackStyle == 4 || attackStyle == 15 || attackStyle == 13 || attackStyle == 12) { // normal Melee move
			if (target instanceof NPC) {
				target.applyHit(damage1);
			}
			npc.setNextAnimation(new Animation(defs.getAttackEmote()));
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
			if (target instanceof NPC) {
				target.applyHit(damage1);
			}
			npc.setNextAnimation(new Animation(defs.getAttackEmote()));
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
			if (target instanceof NPC) {
				target.applyHit(damage1);
			}
			npc.setNextAnimation(new Animation(defs.getAttackEmote()));
			target.setNextGraphics(new Graphics(741));
			//target.moveLocation(target.getChunkX() - 1, target.getChunkY() - 1, 0);
			target.addFreezeDelay(600);
			target.setNextGraphics(new Graphics(506));	
			npc.heal(target.getHitpoints() / 2);
			delayHit(
					npc,
					defs.getAttackDelay(),
					target,
					getMagicHit(
							npc,
							getRandomMaxHit(npc, defs.getMaxHit(),
									NPCCombatDefinitions.MAGE, target)));
			return defs.getAttackDelay();
			

		}
		return defs.getAttackDelay();
	}
	
}
