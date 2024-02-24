package com.rs.game.npc.others;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.Hit;
import com.rs.game.Hit.HitLook;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;


/**
 * 
 * @author Mr_Joopz @ Rune-server
 *
 */

@SuppressWarnings("serial")
public class Anivia extends NPC {

	public Anivia(int id, WorldTile tile, int mapAreaNameHash,
			boolean canBeAttackFromOutOfArea, boolean spawned) {
		super(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		setForceMultiArea(true);
		setForceMultiAttacked(true);
		setCapDamage(800);
		setForceTargetDistance(64);
		setForceFollowClose(false);
	}
	
	
	int count;

	public int getCount() {
		return count;
	}

	public int setCount(int Count) {
		return count = Count;
	}
	
  

    	@Override
    	public void handleIngoingHit(Hit hit) {
    		if (hit.getLook() != HitLook.MELEE_DAMAGE
    				&& hit.getLook() != HitLook.RANGE_DAMAGE
    				&& hit.getLook() != HitLook.MAGIC_DAMAGE) {
				return;
			}
    		super.handleIngoingHit(hit);
    		if (hit.getSource() != null) {
    			int recoil = (int) (hit.getDamage() * 0.1);
    			if (recoil > 0) {
					hit.getSource().applyHit(
    						new Hit(this, recoil, HitLook.REFLECTED_DAMAGE));
				}
    		}
    	}

	
    	@Override
		public void sendDeath(Entity source) {
    		final NPCCombatDefinitions defs = getCombatDefinitions();
    		resetWalkSteps();
    		getCombat().removeTarget();
    		setNextAnimation(null);
    		WorldTasksManager.schedule(new WorldTask() {
    			int loop;

    			@Override
    			public void run() {
    				
    				if (loop == 0) {
    					if (getId() == 30082) {
							transformIntoNPC(30081);
						}
    				}
    					else if (loop == 1) {
    					drop();
    					setNextAnimation(new Animation(defs.getDeathEmote()));
    					
    				} else if (loop >= defs.getDeathDelay()) {
    					reset();
    					setLocation(getRespawnTile());
    					finish();
    					setRespawnTask();
    					stop();
    				}
    				loop++;
    			}
    		}, 0, 1);
    	}

}