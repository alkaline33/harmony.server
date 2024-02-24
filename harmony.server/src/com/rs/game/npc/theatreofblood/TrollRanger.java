package com.rs.game.npc.theatreofblood;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.player.Player;
import com.rs.game.player.content.Magic;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;

@SuppressWarnings("serial")
public class TrollRanger extends NPC {

	public TrollRanger(int id, WorldTile tile, int mapAreaNameHash, boolean canBeAttackFromOutOfArea, boolean spawned) {
		super(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		setLureDelay(0);
		setForceMultiArea(true);
	}

	int count;

	public int getCount() {
		return count;
	}

	public int setCount(int Count) {
		return count = Count;
	}

	@Override
	public double getRangePrayerMultiplier() {
		return 1;
	}

	@Override
	public void processNPC() {
		super.processNPC();
		if (isDead())
			return;
		int maxhp = getMaxHitpoints();
		if (maxhp > getHitpoints() && getPossibleTargets().isEmpty() && !isUnderCombat())
			setHitpoints(maxhp);
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
					// drop();
					for (Player player : World.getPlayers()) {
						if (!World.TrollRaids(player))
							continue;
						player.tobkilledboss3 = true;
						Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2578, 9650, 0));
						// player.sendMessage("Confirmed");
					}
					setNextAnimation(new Animation(defs.getDeathEmote()));
				} else if (loop >= defs.getDeathDelay()) {
					reset();
					setLocation(getRespawnTile());
					finish();
					// drop();
					// drop();
					setRespawnTask();
					stop();
				}
				loop++;
			}
		}, 0, 1);
	}

}