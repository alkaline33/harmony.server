package com.rs.game.npc.combat.impl;

import java.util.ArrayList;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.ForceTalk;
import com.rs.game.Graphics;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.utils.Utils;

public class VoragoCombatEnraged extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 30000 };
	}

	@Override
	public int attack(final NPC npc, final Entity target) {
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		npc.setCapDamage(1000);
		int size = npc.getSize();
		final ArrayList<Entity> possibleTargets = npc.getPossibleTargets();
		boolean stomp = false;
		boolean CanMaul = false;
		boolean isMauling = false;
		Entity targets[] = possibleTargets.toArray(new Entity[possibleTargets.size()]);
		Entity farthest = targets[0];
		int highest = Utils.getDistance(npc, targets[0]);
		int attackStyle = Utils.getRandom(2);
		for (int index = 1; index < targets.length; index ++) {//This is finding the farthest away player so the bombs only go to them
	        if (Utils.getDistance(npc, targets[index]) > highest) {
	            highest = Utils.getDistance(npc, targets[index]);
	           farthest = targets[index];
	        }
	    }
		if (npc.hits == 10) {
			npc.setNextForceTalk(new ForceTalk("Get them!"));
			World.spawnNPC(30002, new WorldTile(3556, 9504, 0), -1, true, true);
			World.spawnNPC(30002, new WorldTile(3556, 9505, 0), -1, true, true);
			npc.hits++;
		}
		for (Entity t : possibleTargets) {
			int distanceX = t.getX() - npc.getX();
			int distanceY = t.getY() - npc.getY();
			if (attackStyle == 0) {
				npc.hits ++;
				stomp = true;
				npc.setNextAnimation(new Animation(12196));
				delayHit(
						npc,
						0,
						t,
						getMeleeHit(
								npc,
								getRandomMaxHit(npc, defs.getMaxHit(),
										NPCCombatDefinitions.MELEE, t)));
				World.sendProjectile(npc, farthest, 2938, 85, 16, 40, 35, 16, 0);
				delayHit(
						npc,
						2,
						farthest,
						getMagicHit(
								npc,
								getRandomMaxHit(npc, 355,
										NPCCombatDefinitions.MAGE, farthest)));
				return defs.getAttackDelay();
			}
		}
		for (Entity t : possibleTargets) {
		if (attackStyle == 1) {
			npc.hits ++;
			npc.setNextAnimation(new Animation(12196));
			delayHit(
					npc,
					0,
					t,
					getRangeHit(
							npc,
							getRandomMaxHit(npc, defs.getMaxHit(),
									NPCCombatDefinitions.RANGE, t)));
			World.sendProjectile(npc, farthest, 2938, 85, 16, 40, 35, 16, 0);
			delayHit(
					npc,
					2,
					farthest,
					getMagicHit(
							npc,
							getRandomMaxHit(npc, 355,
									NPCCombatDefinitions.MAGE, farthest)));
			return defs.getAttackDelay();
		}
	}
		if (stomp) {
			npc.hits ++;
			npc.setNextGraphics(new Graphics(1834));
			delayHit(
					npc,
					0,
					target,
					getRegularHit(
							npc,
							getRandomMaxHit(npc, defs.getMaxHit(),
									NPCCombatDefinitions.MELEE, target)));
			return defs.getAttackDelay();
		}
		if (attackStyle == 2) {
			npc.hits ++;
			for (Entity t : npc.getPossibleTargets()) {
				npc.setNextAnimation(new Animation(12196));
				delayHit(npc, 1, t, getRegularHit(npc, getRandomMaxHit(npc, 350, NPCCombatDefinitions.SPECIAL, t)));
				World.sendProjectile(npc, t, 2939, 41, 16, 41, 35, 16, 0);
				return defs.getAttackDelay();
			}
		}
		return defs.getAttackDelay();
	}
}