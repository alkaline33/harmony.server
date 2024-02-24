package com.rs.game.npc.combat.impl.cerberus;


import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.Graphics;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.npc.others.Cerberus;
import com.rs.utils.Utils;

/**
 * 
 * @author Mr_Joopz @ Rune-server
 *
 */
public class CerberusCombat extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 36862 };
	}

	public void getAttackStyle(final NPC npc, final Entity target) {

		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		int style = Utils.random(3);
		npc.setNextAnimation(new Animation(defs.getAttackEmote()));
		switch (style) {
		default:
		delayHit(npc, 0, target, getMeleeHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.MELEE, target)));
			break;
		case 1:
			delayHit(npc, 2, target, getRangeHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.RANGE, target)));
			// World.sendProjectile(npc, target, defs.getAttackProjectile(), 41, 16, 41, 35,
			// 16, 0);
			target.setNextGraphics(new Graphics(3191));
			break;
		case 2:
			delayHit(npc, 2, target, getMagicHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.MAGE, target)));
			target.setNextGraphics(new Graphics(3203));
			// World.sendProjectile(npc, target, 3394, 41, 16, 41, 35, 16, 0);
			break;
		}
	}

	@Override
	public int attack(final NPC npc, final Entity target) {
		Cerberus cerb = (Cerberus) npc;
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		npc.setForceMultiArea(true);
		npc.setForceMultiAttacked(true);
		npc.setCantFollowUnderCombat(true);
		npc.setForceFollowClose(false);
		int count = cerb.getCount();
		switch (count) {
		case 10:
			World.spawnNPC(36867, new WorldTile(npc.getX() + 1, npc.getY(), npc.getPlane()), -1, true, true);
			cerb.setCount(count += 1);
			break;
		case 12:
			World.spawnNPC(36868, new WorldTile(npc.getX() + 1, npc.getY(), npc.getPlane()), -1, true, true);
			cerb.setCount(count += 1);
			break;
		case 14:
			World.spawnNPC(36869, new WorldTile(npc.getX() + 1, npc.getY(), npc.getPlane()), -1, true, true);
			cerb.setCount(count = 0);
			break;
		default:
			cerb.setCount(count += 1);
			getAttackStyle(npc, target);
			break;
		}
		return defs.getAttackDelay();
	}
}
