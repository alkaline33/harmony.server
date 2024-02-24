package com.rs.game.npc.combat.impl.dryvssuns;

import java.util.ArrayList;
import java.util.HashMap;

import com.rs.game.Animation;
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

public class DumbfoundOgre extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 30063 };
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
				int size = npc.getSize();
				npc.setNextAnimation(new Animation(defs.getAttackEmote()));
				target.setNextGraphics(new Graphics (369));
				target.addFreezeDelay(10000, true);
				delayHit(
						npc,
						1,
						target,
						getMagicHit(
								npc,
								getRandomMaxHit(npc, defs.getMaxHit(),
										NPCCombatDefinitions.MAGE, target)));
			

				return defs.getAttackDelay();
			}
		
				
		return 0;
	}
}
