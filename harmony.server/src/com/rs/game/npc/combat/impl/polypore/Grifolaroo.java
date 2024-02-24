package com.rs.game.npc.combat.impl.polypore;
import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.ForceMovement;
import com.rs.game.ForceTalk;
import com.rs.game.Graphics;
import com.rs.game.Hit;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.Hit.HitLook;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.player.Player;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;
/**
 * 
 * @author taylor
 *
 */
public class Grifolaroo extends CombatScript {

	
	@Override
	public Object[] getKeys() {
		return new Object[] { "Grifolaroo" };
	}
	
   /**
     * makes da bitch jump.
     * @param npc
     * @param target
     */
	private boolean canInteract = true;
	public void Jump(final NPC npc, final Entity target) {
	     this.canInteract = false;
		npc.setNextAnimation(new Animation(15491));
		talk(npc, "Hup!");
		npc.faceEntity(target);
		npc.setNextForceMovement(new ForceMovement(npc, 1, 
			new WorldTile(target.getX(), target.getY() + 1, target.getPlane()), 2,
				target.getDirection() == 0 ? 
						ForceMovement.NORTH : target.getDirection() == 1 ? ForceMovement.EAST :
							target.getDirection() == 2 ? ForceMovement.SOUTH : ForceMovement.EAST));
		npc.setNextWorldTile(new WorldTile(target.getX(), target.getY() + 1, target.getPlane()));
		canInteract = true;
	}
	
	public void specialAttack(final NPC npc, final Entity target) {
		move(npc, 15492);
		talk(npc, "Krrr!");
		((Player) target).getPackets().sendGameMessage("The creature infests you with its toxic fungus");
		WorldTasksManager.schedule(new WorldTask() {
			int loop;

			@Override
			public void run() {
				if (loop == 1) {
					target.applyHit(
						new Hit(target, Utils.random(20, 150), HitLook.POISON_DAMAGE));
					
				}
				if (isDistant(npc, target)) {
					stop();
				}
				loop++;
			}
		}, 0, 1);
	}
	public void doMagicAttack(NPC npc, Entity target) {
		move(npc, 15495);
		delayHit(npc, 1,target, getMagicHit(npc,getRandomMaxHit(npc, 500,NPCCombatDefinitions.MAGE, target)));
		World.sendProjectile(npc, target, 2035, 50, 32, 30, 50, 0, 0);
	}



	@Override
	public int attack(final NPC npc, final Entity target) {
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
			if (isDistant(npc, target)) {
				if (canInteract)
					if (Utils.random(4) == 0)
				Jump(npc, target);
			}
		int attackStyle = Utils.getRandom(4);
		if (attackStyle == 0 || attackStyle == 1) {
				doMagicAttack(npc, target);
		}
			else {
				if (!isDistant(npc, target))
					if (canInteract)
				specialAttack(npc, target);
			}
		if (attackStyle == 2) {
			if(canInteract)
			if (!isDistant(npc, target))
			specialAttack(npc, target);
		} else if (attackStyle == 3) {
			if (!isDistant(npc, target))
			if(canInteract)
			npc.setNextAnimation(new Animation(15492));
			delayHit(npc, 1, target, getMeleeHit(npc, getRandomMaxHit(npc, 550,NPCCombatDefinitions.MELEE, target)));
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
