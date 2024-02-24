/*package com.rs.game.npc.combat.impl;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.npc.vorago.Vitalis;
import com.rs.game.player.content.Combat;
import com.rs.utils.Utils;

public class VitalisCombat extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 17157 };
	}

	@Override//see why i asked for 70$ :s
	public int attack(NPC npc, Entity target) {
		Vitalis vita = (Vitalis) npc;
		if (!Utils.isOnRange(vita, target, 1))
			return 0;

		NPCCombatDefinitions def = vita.getCombatDefinitions();
		vita.setNextAnimation(new Animation(def.getAttackEmote()));

		int damage = getMaxHit(vita, 125, Combat.MELEE_TYPE, target);
		delayHit(vita, 0, target, getMeleeHit(npc, damage));

		return 8;
	}

}*/