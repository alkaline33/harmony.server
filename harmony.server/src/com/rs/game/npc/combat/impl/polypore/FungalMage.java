package com.rs.game.npc.combat.impl.polypore;


import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.World;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.utils.Utils;

public class FungalMage extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { "Fungal mage" };
	}
	

	@Override
	public int attack(NPC npc, Entity target) {
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		int size = npc.getSize();
			int distanceX = target.getX() - npc.getX();
			int distanceY = target.getY() - npc.getY();
			if (distanceX < size && distanceX > -1 && distanceY < size
					&& distanceY > -1) {
				delayHit(npc,0,target,getRegularHit(npc,
								getRandomMaxHit(npc, defs.getMaxHit(),
										NPCCombatDefinitions.MELEE, target)));
			}
		int attackStyle = Utils.getRandom(4);
		if (attackStyle == 0 || attackStyle == 1) {
			if (distanceX > size || distanceX < -1 || distanceY > size
					|| distanceY < -1)
				move(npc, 15507);
			delayHit(npc, 1,target, getMagicHit(npc,getRandomMaxHit(npc, 650,NPCCombatDefinitions.MAGE, target)));
			World.sendProjectile(npc, target, 2035, 50, 32, 30, 50, 0, 0);
			return defs.getAttackDelay();
		}        //if they're far away, the beast will mage them
			else {
				move(npc, 15507);
				delayHit(npc, 1,target, getMagicHit(npc,getRandomMaxHit(npc, 650,NPCCombatDefinitions.MAGE, target)));
				World.sendProjectile(npc, target, 2035, 50, 32, 30, 50, 0, 0);
				return defs.getAttackDelay();
	}}
	public void move(NPC npc, int animation) {
		npc.setNextAnimation(new Animation(animation));
	}}

