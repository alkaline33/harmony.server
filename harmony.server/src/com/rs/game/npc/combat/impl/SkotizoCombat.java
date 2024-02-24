package com.rs.game.npc.combat.impl;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.ForceTalk;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.npc.others.Skotizo;
import com.rs.utils.Utils;

public class SkotizoCombat extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 38286 };
	}

	@Override
	public int attack(final NPC npc, final Entity target) {
		Skotizo skotizo = (Skotizo) npc;
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		npc.setForceMultiArea(true);
		npc.setForceMultiAttacked(true);
		npc.setCapDamage(1000);
		int attackstyle = Utils.random(50);
		switch (attackstyle) {
		default:
			npc.setNextAnimation(new Animation(defs.getAttackEmote()));
			delayHit(npc, 0, target, getMeleeHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.MELEE, target)));
			break;
		case 2:
		case 7:
		case 15:
		case 18:
		case 25:
		case 32:
		case 49:
		case 42:
			npc.setNextAnimation(new Animation(defs.getAttackEmote()));
			delayHit(npc, 0, target, getMagicHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.MAGE, target)));
			break;
		case 5:
		case 11:
			npc.setNextForceTalk(new ForceTalk("Gar mulno ful raglo!"));
			World.spawnNPC(38287, new WorldTile(target.getX() + 1, target.getY(), 0), -1, true, true);
			World.spawnNPC(38287, new WorldTile(target.getX() - 1, target.getY(), 0), -1, true, true);
			World.spawnNPC(38287, new WorldTile(target.getX(), target.getY() + 1, 0), -1, true, true);
			break;
		}
		return defs.getAttackDelay();
		// break;
	}
}
