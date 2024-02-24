package com.rs.game.npc.others;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;

@SuppressWarnings("serial")
public class Skotizo extends NPC {

	public int targety;
	public int targetx;

	public Skotizo(int id, WorldTile tile, int mapAreaNameHash, boolean canBeAttackFromOutOfArea, boolean spawned) {
		super(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		setLureDelay(0);
		setForceMultiArea(true);
	}

	@Override
	public double getMeleePrayerMultiplier() {
		return 0.2;
	}

	@Override
	public void processNPC() {
		super.processNPC();
		if (isDead()) {
			return;
		}
		int maxhp = getMaxHitpoints();
		if (maxhp > getHitpoints() && getPossibleTargets().isEmpty() && !isUnderCombat()) {
			setHitpoints(maxhp);
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
					for (NPC bombs : World.getNPCs()) {
						if (bombs.getId() == 38287)
							bombs.finish();
					}
					// Magic.sendNormalTeleportSpell(source, 0, 0, new
					// WorldTile(Settings.RESPAWN_PLAYER_LOCATION));
					// source.setNextFaceWorldTile(new WorldTile(Settings.RESPAWN_PLAYER_LOCATION));
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