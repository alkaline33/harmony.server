package com.rs.game.npc.combat.impl;

import java.util.ArrayList;
import java.util.HashMap;

import com.rs.Settings;
import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.ForceTalk;
import com.rs.game.Graphics;
import com.rs.game.Hit;
import com.rs.game.WorldObject;
import com.rs.game.Hit.HitLook;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.npc.familiar.Familiar;
import com.rs.game.player.Player;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

/**
 * 
 * 
 * @author Mr_Joopz
 *
 */

public class ConnorPW extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 30095 };
	}

	@Override
	public int attack(final NPC npc, final Entity target) {
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		npc.setForceMultiArea(true);
		npc.setForceMultiAttacked(true);
		npc.setCapDamage(200);
		int attackStyle = Utils.getRandom(2);
		//final Player player = (Player) target;
		//Hit damage1 = getMeleeHit(npc, 10000);
		
		
		/**
		 * Special attack that stops players from damaging until the vials have been added
		 */
		if (attackStyle == 0 ) {
			npc.setNextAnimation(new Animation(1978));
			npc.setNextGraphics(new Graphics(482));
			target.setNextGraphics(new Graphics(449));
		
			delayHit(
					npc,
					defs.getAttackDelay(),
					target,
					getMagicHit(
							npc,
							getRandomMaxHit(npc, defs.getMaxHit(),
									NPCCombatDefinitions.MAGE, target)));
			npc.heal(30);
			return defs.getAttackDelay();
		}
		/**
		 * Basic melee attack
		 */
		if (attackStyle == 1) { // normal Melee move
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
		} else if (attackStyle == 2) { // normal Ranged move
			npc.setNextAnimation(new Animation(10504));
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
			

		}
		return defs.getAttackDelay();
	}
	
}
