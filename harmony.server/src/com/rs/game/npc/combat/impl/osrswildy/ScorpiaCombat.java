package com.rs.game.npc.combat.impl.osrswildy;


import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.npc.others.osrswildy.Scorpia;
import com.rs.utils.Utils;

/**
 * 
 * @author Mr_Joopz @ Rune-server
 *
 */
public class ScorpiaCombat extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 37615 };
	}

	public void getAttackStyle(final NPC npc, final Entity target) {
		Scorpia scorpia = (Scorpia) npc;
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		int style = Utils.random(6);
		switch (style) {
		default:
			for (NPC n : World.getNPCs()) {
				if (n == null || n.hasFinished() || n.getId() != 37617)
					continue;
				if (World.Scorpia(n)) {
					// System.out.println("HEALING");
					scorpia.heal(40);
					scorpia.minionheals++;
				}
				if (scorpia.minionheals >= 40)
					n.finish();
			}
			if (Utils.random(1, 4) == 2) {
				target.getPoison().makePoisoned(200);
			}
			npc.setNextAnimation(new Animation(defs.getAttackEmote()));
			delayHit(npc, 0, target, getMeleeHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.MELEE, target)));
			break;
		}
	}

	@Override
	public int attack(final NPC npc, final Entity target) {
		Scorpia scorpia = (Scorpia) npc;
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		if (scorpia.getHitpoints() <= scorpia.getMaxHitpoints() / 2 && scorpia.spawnedminions != true) {
			World.spawnNPC(37617, new WorldTile(target.getX() + 1, target.getY(), 0), -1, true, true);
			World.spawnNPC(37617, new WorldTile(target.getX() - 1, target.getY(), 0), -1, true, true);
			scorpia.spawnedminions = true;
		}
		getAttackStyle(npc, target);
		return defs.getAttackDelay();
	}
}
