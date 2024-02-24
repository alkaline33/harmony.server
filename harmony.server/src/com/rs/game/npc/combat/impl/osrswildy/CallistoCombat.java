package com.rs.game.npc.combat.impl.osrswildy;


import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.npc.others.osrswildy.Callisto;
import com.rs.game.player.Player;
import com.rs.utils.Utils;

/**
 * 
 * @author Mr_Joopz @ Rune-server
 *
 */
public class CallistoCombat extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 37503 };
	}

	public void getAttackStyle(final NPC npc, final Entity target) {

		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		int style = Utils.random(3);
		npc.setNextAnimation(new Animation(defs.getAttackEmote()));
		switch (style) {
		default:
			delayHit(npc, 0, target, getMeleeHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.MELEE, target)));
			break;
		}
	}

	@Override
	public int attack(final NPC npc, final Entity target) {
		Callisto callisto = (Callisto) npc;
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		npc.setForceMultiArea(true);
		npc.setForceMultiAttacked(true);
		npc.setCantFollowUnderCombat(true);
		npc.setForceFollowClose(false);
		int count = callisto.getCount();
		switch (count) {
		case 14:
			int attack = Utils.getRandom(3);
			switch (attack) {
			case 2:
				npc.setNextAnimation(new Animation(defs.getAttackEmote()));
				for (Entity e : npc.getPossibleTargets()) {
					if (e instanceof Player) {
						Player player = (Player) e;
						player.setPrayerDelay(Utils.getRandom(5) + 5);
						player.getPackets()
								.sendGameMessage(
										"Callisto stuns you!");
					}
				}
				delayHit(npc, 0, target, getMeleeHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.MELEE, target)));
				target.addFreezeDelay(5000);
				callisto.setCount(count = 0);
				break;
			default:
				npc.setNextAnimation(new Animation(4925));
				delayHit(npc, 0, target, getMagicHit(npc, getRandomMaxHit(npc, 600, NPCCombatDefinitions.MAGE, target)));
				target.addFreezeDelay(5000);
				callisto.setCount(count = 0);
				break;
			}
			break;
		default:
			callisto.setCount(count += 1);
			getAttackStyle(npc, target);
			break;
		}
		return defs.getAttackDelay();
	}
}
