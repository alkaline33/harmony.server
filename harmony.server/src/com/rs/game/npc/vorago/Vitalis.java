/*package com.rs.game.npc.vorago;

import java.util.List;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.npc.familiar.Familiar;
import com.rs.game.player.Player;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

@SuppressWarnings("serial")
public class Vitalis extends NPC {

	private transient Vorago vorago;

	public Vitalis(int id, WorldTile tile, int mapAreaNameHash, boolean canBeAttackFromOutOfArea, Vorago vorago) {
		super(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea);
		this.vorago = vorago;
		setForceFollowClose(true);
		this.setForceMultiArea(true);
		setIntelligentRouteFinder(true);
	}

	@Override
	public boolean isBoundImmune() {
		return true;
	}

	@Override
	public boolean canWalkNPC(int toX, int toY) {
		int size = getSize();
		for (int regionId : getMapRegionsIds()) {
			List<Integer> npcIndexes = World.getRegion(regionId).getNPCsIndexes();
			if (npcIndexes != null && npcIndexes.size() < 100 ) {
				for (int npcIndex : npcIndexes) {
					NPC target = World.getNPCs().get(npcIndex);
					if (target == null || target == this || target.isDead() || target.hasFinished()
							|| target.getPlane() != getPlane() || target instanceof Familiar
							|| target instanceof Vorago)
						continue;
					int targetSize = target.getSize();
					// npc is under this target so skip checking it
					if (Utils.colides(this, target))
						continue;
					WorldTile tile = new WorldTile(target);
					// has to be checked aswell, cuz other one assumes npc will
					// manage to move no matter what
					if (Utils.colides(toX, toY, size, tile.getX(), tile.getY(), targetSize))
						return false;
					if (target.getNextWalkDirection() != -1) {
						tile.moveLocation(Utils.DIRECTION_DELTA_X[target.getNextWalkDirection()],
								Utils.DIRECTION_DELTA_Y[target.getNextWalkDirection()], 0);
						if (target.getNextRunDirection() != -1)
							tile.moveLocation(Utils.DIRECTION_DELTA_X[target.getNextRunDirection()],
									Utils.DIRECTION_DELTA_Y[target.getNextRunDirection()], 0);
						// target is at x,y
						if (Utils.colides(toX, toY, size, tile.getX(), tile.getY(), targetSize))
							return false;
					}
				}
			}
		}
		return true;
	}

	@Override
	public boolean isPoisonImmune() {
		return true;
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
		Vitalis thisNPC = this;
		WorldTasksManager.schedule(new WorldTask() {
			int loop;

			@Override
			public void run() {
				if (loop == 0) {
					setNextAnimation(new Animation(defs.getDeathEmote()));
				} else if (loop >= deathDelay) {
					if (source != null && (source instanceof Player))
						((Player) source).getControlerManager().processNPCDeath(thisNPC);
					vorago.sendVitalisDeath(thisNPC);
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