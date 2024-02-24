package com.rs.game.npc.combat.impl.osrswildy;


import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.World;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.npc.others.osrswildy.Venenatis;
import com.rs.game.player.Player;
import com.rs.utils.Utils;

/**
 * 
 * @author Mr_Joopz @ Rune-server
 *
 */
public class VenenatisCombat extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 37504 };
	}

	public void getAttackStyle(final NPC npc, final Entity target) {

		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		int style = Utils.random(6);
		switch (style) {
		case 1:
			npc.setNextAnimation(new Animation(5319));
			target.addFreezeDelay(5000);
			for (Entity e : npc.getPossibleTargets()) {
				if (e instanceof Player) {
					Player player = (Player) e;
					player.getPrayer().drainPrayer(100);
				}
			}
			delayHit(npc, 0, target, getMeleeHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.MELEE, target)));
			break;
		default:// gfx
			npc.setNextAnimation(new Animation(defs.getAttackEmote()));
			for (Entity e : npc.getPossibleTargets()) {
				World.sendProjectile(npc, e, 2719, 41, 16, 41, 0, 16, 0);
				if (!e.getPoison().isPoisoned()) {
					e.getPoison().makePoisoned(80);
				}
				delayHit(npc, 0, e, getMagicHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.MAGE, e)));
			}
			break;
		}
	}

	@Override
	public int attack(final NPC npc, final Entity target) {
		Venenatis venenatis = (Venenatis) npc;
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		getAttackStyle(npc, target);
		return defs.getAttackDelay();
	}
}
