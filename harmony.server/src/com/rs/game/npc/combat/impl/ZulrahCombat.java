package com.rs.game.npc.combat.impl;


import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.npc.zulrah.Zulrah;
import com.rs.utils.Utils;

/**
 * 
 * @author Mr_Joopz @ Rune-server
 *
 */
public class ZulrahCombat extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 16387, 16388, 16389 }; 
	}
	
	/**
	 * 
	 * Grabs Attack style
	 */
	
	public void getAttackStyle(final NPC npc, final Entity target) {

		
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		if (npc.getId() == 16387) {
			delayHit(npc, 0, target,
					getRangeHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.RANGE, target)));
			World.sendProjectile(npc, target, defs.getAttackProjectile(), 41, 16, 41, 35, 16, 0);
		//	World.sendProjectile(npc, target,  defs.getAttackProjectile(), 34, 16, 30, 35, 16, 0);
		} else if (npc.getId() == 16388) {
			delayHit(npc, 5, target,
					getMeleeHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.MELEE, target)));
		} else if (npc.getId() == 16389) {
			int type = Utils.random(1, 5);
			if (type > 1) {
			delayHit(npc, 0, target,
					getMagicHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.MAGE, target)));
				World.sendProjectile(npc, target, defs.getAttackProjectile(), 41, 16, 41, 35, 16, 0);
			} else {
				delayHit(npc, 0, target,
						getRangeHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.RANGE, target)));
			}	
		}
	}

	public void getRandomLocation(final NPC npc) {
		/**
		 * Needs finishing
		 */
		int location = Utils.random(1, 4);
		switch (location) {
		case 1:
			int style = Utils.random(1, 2);
			switch (style) {
			case 1:
				break;
			case 2:
				break;
			}
			//west
			break;
		case 2:
			//south
			break;
		case 3: 
			//east
			break;
		case 4:
			//north
			break;
		}
		npc.setNextWorldTile(new WorldTile (npc.getX() - 9, npc.getY(), npc.getPlane()));
	}

	
	@Override
	public int attack(final NPC npc, final Entity target) {
		Zulrah zulrah = (Zulrah) npc;
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		npc.setForceMultiArea(true);
		npc.setForceMultiAttacked(true);
		npc.setCapDamage(1000);
		npc.setForceTargetDistance(64);
		npc.setCantFollowUnderCombat(true);
		npc.setForceFollowClose(false);
		int count = zulrah.getCount();
			switch (count) {
			case 0:
			case 1:
			case 2:
			case 3:
				//World.spawnTemporaryObject(new WorldObject(2988,
					//	10, 0, npc.getX() + 4,  npc.getY() - 1, 0), 20000);
				npc.setNextAnimation(new Animation(defs.getAttackEmote()));
				getAttackStyle(npc, target);
				target.getPoison().makePoisoned(200);
				zulrah.setCount(count += 1);
				break;
			case 4:
				zulrah.setCapDamage(0);
				npc.setNextAnimation(new Animation(17846));//Down
				zulrah.setCount(count += 1);
			break;
			case 5:
				npc.transformIntoNPC(16388);
				npc.setNextAnimation(new Animation(17847)); //Up
			zulrah.setCapDamage(1000);
				zulrah.setCount(count += 1);
				break;
			case 6:
			case 7:
			case 8:
			case 9:
				npc.setNextAnimation(new Animation(defs.getAttackEmote()));
				getAttackStyle(npc, target);
				zulrah.setCount(count += 1);
				break;
			case 10:
				zulrah.setCapDamage(0);
				npc.setNextAnimation(new Animation(17846));//Down
				zulrah.setCount(count += 1);
			break;
			case 11:
			zulrah.setCapDamage(1000);
				npc.transformIntoNPC(16389);
				npc.setNextAnimation(new Animation(17847)); //Up
				zulrah.setCount(count += 1);
				break;
			case 12:
			case 13:
			case 14:
			case 15:
				npc.setNextAnimation(new Animation(defs.getAttackEmote()));
				getAttackStyle(npc, target);
				zulrah.setCount(count += 1);
				break;
			case 16:
				zulrah.setCapDamage(0);
				npc.setNextAnimation(new Animation(17846));//Down
				npc.setNextWorldTile(new WorldTile (npc.getX() - 9, npc.getY(), npc.getPlane()));
				zulrah.setCount(count += 1);
			break;
			case 17:
			zulrah.setCapDamage(1000);
				npc.transformIntoNPC(16387);
				npc.setNextAnimation(new Animation(17847)); //Up
				zulrah.setCount(count += 1);
				break;
			case 18:
				World.spawnNPC(16390, new WorldTile(target.getX()+1, target.getY(), target.getPlane()), -1, true, true);
				npc.setNextAnimation(new Animation(defs.getAttackEmote()));
				getAttackStyle(npc, target);
				zulrah.setCount(count += 1);
				break;
			case 19:
				World.spawnNPC(16390, new WorldTile(target.getX()+1, target.getY(), target.getPlane()), -1, true, true);
				npc.setNextAnimation(new Animation(defs.getAttackEmote()));
				getAttackStyle(npc, target);
				zulrah.setCount(count += 1);
				break;
			case 20:
				npc.setNextAnimation(new Animation(defs.getAttackEmote()));
				getAttackStyle(npc, target);
				zulrah.setCount(count += 1);
				break;
			case 21:
				World.spawnNPC(16390, new WorldTile(target.getX()+1, target.getY(), target.getPlane()), -1, true, true);
				npc.setNextAnimation(new Animation(defs.getAttackEmote()));
				getAttackStyle(npc, target);
				zulrah.setCount(count += 1);
				break;
			case 22:
				zulrah.setCapDamage(0);
				npc.setNextWorldTile(new WorldTile (npc.getX() + 9, npc.getY(), npc.getPlane()));
				npc.setNextAnimation(new Animation(17846));//Down
				zulrah.setCount(count += 1);
			break;
			case 23:
			zulrah.setCapDamage(1000);
				npc.transformIntoNPC(16389);
				npc.setNextAnimation(new Animation(17847)); //Up
				zulrah.setCount(count += 1);
				break;
			case 24:
			case 25:
			case 26:
			case 27:
				npc.setNextAnimation(new Animation(defs.getAttackEmote()));
				getAttackStyle(npc, target);
				zulrah.setCount(count += 1);
				break;
			case 28:
				zulrah.setCapDamage(0);
				npc.setNextAnimation(new Animation(17846));//Down
				zulrah.setCount(count += 1);
				break;
			case 29:
			zulrah.setCapDamage(1000);
				npc.transformIntoNPC(16387);
				npc.setNextAnimation(new Animation(17847)); //Up
				zulrah.setCount(0);
				break;
				
				/**
				 * Todo - Add more spawn locations
				 */
				
				
				
		}
			return defs.getAttackDelay();
	}
}
