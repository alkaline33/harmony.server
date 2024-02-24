package com.rs.game.npc.combat.impl.polypore;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.ForceTalk;
import com.rs.game.Graphics;
import com.rs.game.Hit;
import com.rs.game.Hit.HitLook;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.player.Player;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

public class Grifalopine extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { "Grifolapine" };
	}

	private boolean canInteract = true;
	public void Jump(final NPC npc, final Entity target) {
	     this.canInteract = false;
		talk(npc, "Raaargh!");
		npc.faceEntity(target);
		npc.addWalkSteps(target.getX(), target.getY() + 1);
		WorldTasksManager.schedule(new WorldTask() {
			int loop;

			@Override
			public void run() {
				if (loop == 2) 
				if (!isDistant(npc, target)) {
					specialAttack(npc, target);
					canInteract = true;
				}
				loop++;
			}
		}, 0, 1);
	}
	public void chat(NPC npc, Entity target) {
		if (Utils.random(3) == 0) 
			talk(npc, "Krrr!");
		((Player) target).getPackets().sendGameMessage("The creature infests you with its toxic fungus");
	}
	
	public void specialAttack(final NPC npc, final Entity target) {
		move(npc, 15484);
		chat(npc, target);
		WorldTasksManager.schedule(new WorldTask() {
			int loop;

			@Override
			public void run() {
				if (loop == 1) {
					target.applyHit(
						new Hit(target, Utils.random(20, 300), HitLook.POISON_DAMAGE));
					
				}
				if (isDistant(npc, target)) {
					stop();
				}
				loop++;
			}
		}, 0, 1);
	}
	public void doSpikeAttack(NPC npc, Entity target) {
		move(npc, 15487);
		target.applyHit(new Hit(target, Utils.random(90, 300), HitLook.REGULAR_DAMAGE));
	}



	@Override
	public int attack(final NPC npc, final Entity target) {
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
			if (isDistant(npc, target)) {
				if (canInteract)
					if (Utils.random(2) == 0)
				Jump(npc, target);
			}
		int attackStyle = Utils.getRandom(4);
		if (attackStyle == 0 || attackStyle == 1) {
			if(isDistant(npc,target))
				doSpikeAttack(npc, target);
		}
			else {
				if (!isDistant(npc, target))
				specialAttack(npc, target);
			}
		if (attackStyle == 2) {
			if(canInteract)
			if (!isDistant(npc, target))
			specialAttack(npc, target);
		 if (attackStyle == 3) {
			if (!isDistant(npc, target))
			if(canInteract)
			specialAttack(npc, target);
		 }
		} else if (attackStyle == 4) {
			Jump(npc, target);
			specialAttack(npc, target);
		}
		return defs.getAttackDelay();
	}
	
{
	
}

	
	public boolean isDistant(NPC npc, Entity target) {
		int size = npc.getSize();
		int dX = target.getX() - npc.getX();
		int dY = target.getY() - npc.getY();
	if (dX > size || dX < -1 || dY > size
			|| dY < -1) {
		return true;
		
	}
	return false;
	}
	public void talk(NPC npc, String text) {
		npc.setNextForceTalk(new ForceTalk(text));
	}
	public void move(NPC npc, int animation) {
		npc.setNextAnimation(new Animation(animation));
	}
	public void gfx(NPC npc, int gfx) {
		npc.setNextGraphics(new Graphics(15491));
	}
}
