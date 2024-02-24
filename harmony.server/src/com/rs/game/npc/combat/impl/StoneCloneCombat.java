/*package com.rs.game.npc.combat.impl;

import com.rs.game.Entity;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.npc.vorago.StoneClone;
import com.rs.game.player.Player;
import com.rs.game.player.content.Combat;

public class StoneCloneCombat extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 17158, 17159, 17160 };// melee,range,mage
	}

	private StoneClone clone;

	@Override
	public int attack(NPC npc, Entity target) {
		clone = (StoneClone) npc;
		Player player = (Player) target;
		int combatType = clone.getId() == 17160 ? NPCCombatDefinitions.MAGE
				: clone.getId() == 17159 ? NPCCombatDefinitions.RANGE : NPCCombatDefinitions.MELEE;
		int damage = getRandomMaxHit(npc, 250, NPCCombatDefinitions.MELEE, target);
		delayHit(npc, 1, target, combatType == NPCCombatDefinitions.MELEE ? getMeleeHit(npc, damage)
				: combatType == NPCCombatDefinitions.RANGE ? getRangeHit(npc, damage) : getMagicHit(npc, damage));
		return 3;
	}

}*/