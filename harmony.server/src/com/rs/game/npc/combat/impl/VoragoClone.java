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

public class VoragoClone extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 30002 };
	}

	@Override
	public int attack(final NPC npc, final Entity target) {
		for (NPC n :World.getNPCs()) {
			if (n == null || n.getId() != 30009 && n.getId() != 30000)
				continue;
			n.setCapDamage(10);
			n.heal(1000);
		}
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		int size = npc.getSize();
		npc.setCapDamage(1000);
		final ArrayList<Entity> possibleTargets = npc.getPossibleTargets();
		Entity targets[] = possibleTargets.toArray(new Entity[possibleTargets.size()]);
		Entity farthest = targets[0];
		int attackStyle = Utils.getRandom(2);
		for (Entity t : possibleTargets) {
			npc.setNextAnimation(new Animation(12196));
			delayHit(
					npc,
					0,
					t,
					getMeleeHit(
							npc,
							getRandomMaxHit(npc, defs.getMaxHit(),
									NPCCombatDefinitions.MELEE, t)));
		
		}
		return defs.getAttackDelay();
		
	}
}