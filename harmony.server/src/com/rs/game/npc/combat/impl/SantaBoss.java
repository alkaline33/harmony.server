package com.rs.game.npc.combat.impl;

import java.util.ArrayList;
import java.util.HashMap;

import com.rs.game.Entity;
import com.rs.game.Graphics;
import com.rs.game.Hit;
import com.rs.game.Hit.HitLook;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.player.Player;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

public class SantaBoss extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 1552, 30205 };
	}

	@Override
	public int attack(final NPC npc, final Entity target) {
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
				npc.setForceMultiArea(true);
				npc.setForceMultiAttacked(true);
				npc.setCapDamage(2500);
				npc.setForceTargetDistance(64);
				npc.setForceFollowClose(false);
			
		
			if (Utils.getRandom(1) == 0) { // 
				delayHit(
						npc,
						3,
						target,
						getMagicHit(
								npc,
								getRandomMaxHit(npc, defs.getMaxHit(),
										NPCCombatDefinitions.MAGE, target)));
				final int hits = 0;
				ArrayList<Entity> possibleTargets = npc.getPossibleTargets();
				final HashMap<String, int[]> tiles = new HashMap<String, int[]>();
				for (Entity t : possibleTargets) {
					if (t instanceof Player) {
						Player p = (Player) t;
				String key = t.getX() + "_" + t.getY();
				if (!tiles.containsKey(t.getX() + "_" + t.getY())) {
					tiles.put(key, new int[] { t.getX(), t.getY() });
						World.spawnTemporaryObject(new WorldObject(66006,
								10, 0, p.getX(), p.getY(), 0), 2400);
				
				
				WorldTasksManager.schedule(new WorldTask() {
			
					@Override
					public void run() {
						ArrayList<Entity> possibleTargets = npc
								.getPossibleTargets();
						for (int[] tile : tiles.values()) {
							npc.setNextGraphics(new Graphics(3032));
							World.sendGraphics(null, new Graphics(364),
									new WorldTile(tile[0], tile[1], 0));
							for (Entity t : possibleTargets)
								if (t.getX() > tile[0] - 2 && t.getX() < tile[0] + 2
										&& t.getY() > tile[1] - 2 && t.getY() < tile[1] + 2)
									t.applyHit(new Hit(npc,
											Utils.getRandom(200) + 300,
											HitLook.REGULAR_DAMAGE));
							
						}
					}

				}, 5);
		
		return defs.getAttackDelay();
	}
		
		return 0;
}
}
			}	
		return 0;
	}
}
