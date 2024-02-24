package com.rs.game.npc.vorago;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.Hit;
import com.rs.game.Hit.HitLook;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;

@SuppressWarnings("serial")
public class VoragoEnraged extends NPC {

	public VoragoEnraged(int id, WorldTile tile, int mapAreaNameHash,
			boolean canBeAttackFromOutOfArea, boolean spawned) {
		super(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		setLureDelay(0);
		setCapDamage(1000);
	}
	
	
        
        @Override
    	public double getMagePrayerMultiplier() {
    		return 0.5;
    	}

    	@Override
    	public double getRangePrayerMultiplier() {
    		return 0.5;
    	}

    	@Override
    	public double getMeleePrayerMultiplier() {
    		return 0.5;
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
    					drop();
    					setNextAnimation(new Animation(defs.getDeathEmote()));
    					transformIntoNPC(30009);
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

    	@Override
    	public void handleIngoingHit(Hit hit) {
    		if (hit.getLook() != HitLook.MELEE_DAMAGE
    				&& hit.getLook() != HitLook.RANGE_DAMAGE
    				&& hit.getLook() != HitLook.MAGIC_DAMAGE) {
				return;
			}
    		super.handleIngoingHit(hit);
    		if (hit.getSource() != null) {
    			int recoil = (int) (hit.getDamage() * 0.2);
    			if (recoil > 0) {
					hit.getSource().applyHit(
    						new Hit(this, recoil, HitLook.REFLECTED_DAMAGE));
				}
    		}
    	}

}