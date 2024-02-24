/*package com.rs.game.npc.combat.impl;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.npc.vorago.Scopulus;
import com.rs.game.player.Player;
import com.rs.game.player.content.Combat;
import com.rs.utils.Utils;

public class ScopulusCombat extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 17185 };
	}

	@Override
	public int attack(NPC npc, Entity target) {
		Scopulus scop = (Scopulus) npc;
		NPCCombatDefinitions def = scop.getCombatDefinitions();
		scop.setNextAnimation(new Animation(def.getAttackEmote()));

		for (Player player : scop.getVorago().getVoragoInstance().getPlayersOnBattle()) {
			if (player == null || player.isDead() || !Utils.isOnRange(scop, player, scop.isEnraged() ? 2 : 1))
				continue;
			int damage = getMaxHit(scop, 5600, Combat.MELEE_TYPE, player);
			damage *= scop.isEnraged() ? 1.2 : 1;
			delayHit(scop, 0, player, getMeleeHit(npc, damage));
		}
		return scop.isEnraged() ? 2 : 4;
	}
}*/