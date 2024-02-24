package com.rs.game.npc.others;

import java.util.ArrayList;
import java.util.List;
import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.Hit;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.Hit.HitLook;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.player.Player;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;

@SuppressWarnings("serial")
public class BalanceElemental extends NPC {

	public BalanceElemental(int id, WorldTile tile, int mapAreaNameHash,
			boolean canBeAttackFromOutOfArea, boolean spawned) {
		super(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		setLureDelay(0);
		setRun(false);
		setForceMultiAttacked(true);
	}
	
	
        @Override
	public ArrayList<Entity> getPossibleTargets() {
		ArrayList<Entity> possibleTarget = new ArrayList<Entity>();
		for (int regionId : getMapRegionsIds()) {
			List<Integer> playerIndexes = World.getRegion(regionId)
					.getPlayerIndexes();
			if (playerIndexes != null) {
				for (int npcIndex : playerIndexes) {
					Player player = World.getPlayers().get(npcIndex);
					if (player == null
							|| player.isDead()
							|| player.hasFinished()
							|| !player.isRunning()
							|| !player.withinDistance(this, 64)
							|| ((!isAtMultiArea() || !player.isAtMultiArea())
									&& player.getAttackedBy() != this && player
									.getAttackedByDelay() > System
									.currentTimeMillis())
							|| !clipedProjectile(player, false))
						continue;
					possibleTarget.add(player);
				}
			}
		}
		return possibleTarget;
	}
        
        @Override
    	public double getMagePrayerMultiplier() {
    		return 0.6;
    	}

    	@Override
    	public double getRangePrayerMultiplier() {
    		return 0.6;
    	}

    	@Override
    	public double getMeleePrayerMultiplier() {
    		return 0.6;
    	}

    	@Override
    	public void handleIngoingHit(Hit hit) {
    		if (hit.getLook() != HitLook.MELEE_DAMAGE
    				&& hit.getLook() != HitLook.RANGE_DAMAGE
    				&& hit.getLook() != HitLook.MAGIC_DAMAGE)
    			return;
    		super.handleIngoingHit(hit);
    		if (hit.getSource() != null) {
    			int recoil = (int) (hit.getDamage() * 0.4);
    			if (recoil > 0)
    				hit.getSource().applyHit(
    						new Hit(this, recoil, HitLook.REFLECTED_DAMAGE));
    		}
    	}

	/*
	 * gotta override else setRespawnTask override doesnt work
	 */
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
				
					setNextAnimation(new Animation(defs.getDeathEmote()));
				} else if (loop >= defs.getDeathDelay()) {
					drop();
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