package com.rs.game.npc.combat.impl;

import java.util.ArrayList;
import java.util.HashMap;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.Graphics;
import com.rs.game.Hit;
import com.rs.game.WorldTile;
import com.rs.game.Hit.HitLook;
import com.rs.game.World;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.player.Player;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

public class DBoss extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 10141 };
	}

	@Override
	public int attack(final NPC npc, final Entity target) { //melee
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
				npc.setForceMultiArea(true);
				npc.setForceMultiAttacked(true);
				npc.setCapDamage(999);
				npc.setForceTargetDistance(64);
				npc.setForceFollowClose(false);
			
		if (Utils.getRandom(4) == 0) {
			int size = npc.getSize();
			npc.setNextAnimation(new Animation(14380));
			delayHit(
					npc,
					1,
					target,
					getMeleeHit(
							npc,
							getRandomMaxHit(npc, defs.getMaxHit(),
									NPCCombatDefinitions.MELEE, target)));

			return defs.getAttackDelay();
		}
		
		if (Utils.getRandom(3) == 0) { //Range hit
			npc.setNextGraphics(new Graphics(-1));
			WorldTasksManager.schedule(new WorldTask() {
				
				@Override
				public void run() {
					npc.setNextAnimation(new Animation(14380));
					target.setNextGraphics(new Graphics(2783));
					delayHit(
							npc,
							1,
							target,
							getRangeHit(
									npc,
									getRandomMaxHit(npc, defs.getMaxHit(),
											NPCCombatDefinitions.RANGE, target)));
			
			
				}
			}
		);
		
			if (Utils.getRandom(2) == 0) { // 
				final int hits = 0;
				ArrayList<Entity> possibleTargets = npc.getPossibleTargets();
				final HashMap<String, int[]> tiles = new HashMap<String, int[]>();
				for (Entity t : possibleTargets) {
					if (t instanceof Player) {
						Player p = (Player) t;
				String key = t.getX() + "_" + t.getY();
				if (!tiles.containsKey(t.getX() + "_" + t.getY())) {
					tiles.put(key, new int[] { t.getX(), t.getY() });
					World.sendProjectile(npc, new WorldTile(t.getX(), t.getY(),
							npc.getPlane()), 1900, 34, 0, 30, 35, 16, 0);
				
				WorldTasksManager.schedule(new WorldTask() {
			
					@Override
					public void run() {
						ArrayList<Entity> possibleTargets = npc
								.getPossibleTargets();
						for (int[] tile : tiles.values()) {
							npc.setNextGraphics(new Graphics(2820));
							World.sendGraphics(null, new Graphics(1909),
									new WorldTile(tile[0], tile[1], 0));
							for (Entity t : possibleTargets)
							//	if (t.getX() == tile[0] && t.getY() == tile[1])
									t.applyHit(new Hit(npc,
											Utils.getRandom(100) + 200,
											HitLook.REGULAR_DAMAGE));
						}
						stop();
					}

				}, 5);
		
	
		} else { // Melee - Whip Attack
			npc.setNextAnimation(new Animation(defs.getAttackEmote()));
			delayHit(
					npc,
					0,
					target,
					getMeleeHit(
							npc,
							getRandomMaxHit(npc, defs.getMaxHit(),
									NPCCombatDefinitions.MAGE, target)));
		}
		return defs.getAttackDelay();
	}
		
		return 0;
}
}
		}
		return 0;
	}
}
