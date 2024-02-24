/*package com.rs.game.npc.vorago;

import java.util.ArrayList;
import java.util.Map;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.player.Player;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

@SuppressWarnings("serial")
public class StoneClone extends NPC {

	private transient Vorago vorago;
	private transient Player target;
	private static long BAR_CYCLE;
	private transient Map<Integer, Long> cooldowns;

	public StoneClone(int id, WorldTile tile, int mapAreaNameHash, boolean canBeAttackFromOutOfArea, Vorago vorago,
			Player target) {
		super(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea);
		this.vorago = vorago;
		this.target = target;
		getCombat().setTarget(target);
		this.setForceMultiArea(true);//cuz there is no multi areas in rs3 this happens when converting to 718
		setName("Stone " + target.getDisplayName());
		if (id == 17158)
			setForceFollowClose(true);
	}

	@Override
	public ArrayList<Entity> getPossibleTargets() {
		ArrayList<Entity> possibleTarget = new ArrayList<Entity>();
		if (target != null && !target.isDead())
			possibleTarget.add(target);
		return possibleTarget;
	}

	@Override
	public boolean checkAgressivity() {
		ArrayList<Entity> possibleTarget = getPossibleTargets();
		if (!possibleTarget.isEmpty()) {
			Entity target = possibleTarget.get(Utils.random(possibleTarget.size()));
			setTarget(target);
			target.setAttackedBy(target);
			target.setFindTargetDelay(Utils.currentTimeMillis() + 10000);
			return true;
		}
		if (target == null || target.isDead())
			sendDeath(null);
		return false;
	}

	@Override
	public void sendDeath(final Entity source) {
		final NPCCombatDefinitions defs = getCombatDefinitions();
		resetWalkSteps();
		getCombat().removeTarget();
		setNextAnimation(null);
		if (!isDead())
			setHitpoints(0);
		final int deathDelay = defs.getDeathDelay() - (getId() == 50 ? 2 : 1);
		StoneClone thisNPC = this;
		WorldTasksManager.schedule(new WorldTask() {
			int loop;

			@Override
			public void run() {
				if (loop == 0) {
					setNextAnimation(new Animation(defs.getDeathEmote()));
				} else if (loop >= deathDelay) {
					if (source != null) {
						if (source instanceof Player)
							((Player) source).getControlerManager().processNPCDeath(thisNPC);
					}
					reset();
					finish();
					stop();
				}
				loop++;
			}
		}, 0, 1);
	}

	public Player getTarget() {
		return target;
	}

	public Vorago getVorago() {
		return vorago;
	}
}*/