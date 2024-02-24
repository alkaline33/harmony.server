/*package com.rs.game.npc.vorago;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.player.Player;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;

@SuppressWarnings("serial")
public class Scopulus extends NPC {

	private transient Vorago vorago;

	public Scopulus(int id, WorldTile tile, int mapAreaNameHash, boolean canBeAttackFromOutOfArea, Vorago vorago) {
		super(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea);
		this.vorago = vorago;
		setForceFollowClose(true);
		this.setForceMultiArea(true);
		setIntelligentRouteFinder(true);
	}

	@Override
	public boolean isStunImmune() {
		return true;
	}

	public boolean isEnraged() {
		return vorago.getScopuliCount() < 2;
	}

	@Override
	public void sendDeath(Entity source) {
		final NPCCombatDefinitions defs = getCombatDefinitions();
		resetWalkSteps();
		getCombat().removeTarget();
		setNextAnimation(null);
		if (!isDead())
			setHitpoints(0);
		final int deathDelay = defs.getDeathDelay() - (getId() == 50 ? 2 : 1);
		Scopulus thisNPC = this;
		WorldTasksManager.schedule(new WorldTask() {
			int loop;

			@Override
			public void run() {
				if (loop == 0) {
					setNextAnimation(new Animation(defs.getDeathEmote()));
				} else if (loop >= deathDelay) {
					if (source instanceof Player)
						((Player) source).getControlerManager().processNPCDeath(thisNPC);
					vorago.sendScopulusDeath(thisNPC);
					reset();
					finish();
					stop();
				}
				loop++;
			}
		}, 0, 1);
	}

	public Vorago getVorago() {
		return vorago;
	}
}*/