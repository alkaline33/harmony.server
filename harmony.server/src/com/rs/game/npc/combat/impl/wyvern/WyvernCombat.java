package com.rs.game.npc.combat.impl.wyvern;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.player.Player;
import com.rs.utils.Colors;
import com.rs.utils.Utils;

/**
 * 
 * @author Mr_Joopz @ Rune-server
 *
 */
public class WyvernCombat extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 38793, 38794, 38795 };
	}

	/**
	 * 
	 * Grabs Attack style
	 */

	public void getAttackStyle(final NPC npc, final Entity target) {
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		final Player player = target instanceof Player ? (Player) target : null;
		if (Utils.random(10) == 0) {
				npc.setNextAnimation(new Animation(37654));
				int damage = npc.getId() == 38793 ? 100 : 440;
				delayHit(npc, 1, target, getRegularHit(npc, Utils.random(100, damage)));
			player.sendMessage(Colors.cyan + "The Wyverns icy breath causes you serious damage!");
		} else {
		if (target.withinDistance(npc, 1)) {
				delayHit(npc, 0, target, getMeleeHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.MELEE, target)));
			npc.setNextAnimation(new Animation(37658));
		} else {
				delayHit(npc, 0, target, getRangeHit(npc, getRandomMaxHit(npc, 100, NPCCombatDefinitions.RANGE, target)));
			npc.setNextAnimation(new Animation(defs.getAttackEmote()));
		}
		}
	}

	@Override
	public int attack(final NPC npc, final Entity target) {
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		getAttackStyle(npc, target);
		return defs.getAttackDelay();
	}
}
