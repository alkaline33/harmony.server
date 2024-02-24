package com.rs.game.npc.combat.impl.dryvssuns;

import java.util.ArrayList;
import java.util.HashMap;

import com.rs.game.Entity;
import com.rs.game.Graphics;
import com.rs.game.Hit;
import com.rs.game.WorldObject;
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

public class DischargingOgre extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 30062 };
	}

	@Override
	public int attack(final NPC npc, final Entity target) {
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
				npc.setForceMultiArea(true);
				npc.setForceMultiAttacked(true);
				npc.setCapDamage(5000);
				npc.setForceTargetDistance(64);
				npc.setForceFollowClose(false);
			
		
			if (Utils.getRandom(1) == 0) { // 
				final int hits = 0;
				ArrayList<Entity> possibleTargets = npc.getPossibleTargets();
				final HashMap<String, int[]> tiles = new HashMap<String, int[]>();
				for (Entity t : possibleTargets) {
					if (t instanceof Player) {
						Player p = (Player) t;
				String key = t.getX() + "_" + t.getY();
				if (!tiles.containsKey(t.getX() + "_" + t.getY())) {
					tiles.put(key, new int[] { t.getX(), t.getY() });
						World.spawnTemporaryObject(new WorldObject(362,
								10, 0, p.getX(), p.getY(), 0), 1000);
				
				
				WorldTasksManager.schedule(new WorldTask() {
			
					@Override
					public void run() {
						ArrayList<Entity> possibleTargets = npc
								.getPossibleTargets();
						for (int[] tile : tiles.values()) {
							npc.setNextGraphics(new Graphics(3062));
							World.sendGraphics(null, new Graphics(371),
									new WorldTile(tile[0], tile[1], 0));
							for (Entity t : possibleTargets)
								if (t.getX() > tile[0] -4 && t.getX() < tile[0] + 4
										&& t.getY() > tile[1] - 4 && t.getY() < tile[1] + 4)
									t.applyHit(new Hit(npc,
											Utils.getRandom(200),
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
