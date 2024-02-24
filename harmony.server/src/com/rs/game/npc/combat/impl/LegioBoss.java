package com.rs.game.npc.combat.impl;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.ForceTalk;
import com.rs.game.Graphics;
import com.rs.game.Hit;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.npc.others.AscensionDungeon;
import com.rs.game.npc.others.Legio;

public class LegioBoss extends CombatScript {

    @Override
    public Object[] getKeys() {
	return new Object[] { 17149, 17150, 17151, 17152, 17153, 17154 };
    }

    @Override
    public int attack(NPC npc, Entity target) {
	if (npc instanceof Legio) {
	    Legio leg = (Legio) npc;
	    int rate = 3;
	    if (leg.getOrdinal() == 1) {
		if (leg.getHealthStage() == 1)
		    rate = 4;
		else if (leg.getHealthStage() == 2)
		    rate = 5;
		else if (leg.getHealthStage() == 3)
		    rate = 200;
	    }
	    if (leg.getAttackStage() % rate == 0) {
		Hit damage = getMagicHit(npc, getRandomMaxHit(npc, 430, NPCCombatDefinitions.MAGE, target));
		leg.setNextAnimation(new Animation(20277));
		leg.setNextGraphics(new Graphics(3977));
	//	delayHitGfx(leg, World.sendProjectileNew(leg, target, 3978, 30, 30, 15, 4, 3, 3).getEndTime() / 30,
		//	new Graphics(3979), target, damage);
	    } else {
		leg.setNextAnimation(new Animation(20260));
		leg.setNextGraphics(new Graphics(3975));
		final WorldTile targetPosition = new WorldTile(target.getX(), target.getY(), target.getPlane());
		if (leg.getOrdinal() == 0) {
		    if (leg.getHealthStage() == 0)
			AscensionDungeon.handleLightning(leg, targetPosition, 150, 250);
		    else if (leg.getHealthStage() == 1)
			AscensionDungeon.handleLightning(leg, targetPosition, 180, 270);
		    else if (leg.getHealthStage() == 2)
			AscensionDungeon.handleLightning(leg, targetPosition, 200, 280);
		    else if (leg.getHealthStage() == 3)
			AscensionDungeon.handleLightning(leg, targetPosition, 220, 300);
		} else if (leg.getOrdinal() == 5) {
		    AscensionDungeon.handleLightning(leg, targetPosition, 150, 250, true);
		} else {
		    AscensionDungeon.handleLightning(leg, targetPosition, 150, 250);
		}
	    }
	    leg.incrementAttackStage();
	} else {
	    npc.setNextForceTalk(new ForceTalk("I'm not REAL! memes."));
	}
	return 4;
    }


}