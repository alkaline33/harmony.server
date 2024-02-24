package com.rs.game.npc.godwars.npcvsnpc;

import java.util.ArrayList;
import java.util.List;

import com.rs.game.Entity;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.utils.Utils;

@SuppressWarnings("serial")
public class NpcvsNpc extends NPC {

	public NpcvsNpc(int id, WorldTile tile, int mapAreaNameHash,
			boolean canBeAttackFromOutOfArea, boolean spawned) {
		super(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
	}

	@Override
	public boolean checkAgressivity() {
		NPCCombatDefinitions defs = getCombatDefinitions();
		if (defs.getAgressivenessType() == NPCCombatDefinitions.PASSIVE)
			return false;
		ArrayList<Entity> possibleTarget = new ArrayList<Entity>();
		for (int regionId : getMapRegionsIds()) {
			List<Integer> npcsIndexes = World.getRegion(regionId)
					.getNPCsIndexes();
			if (npcsIndexes != null) {
				for (int npcIndex : npcsIndexes) {
					NPC npc = World.getNPCs().get(npcIndex);
					if (npc == null
							|| npc == this
							|| npc instanceof NpcvsNpc
							|| npc.isDead()
							|| npc.hasFinished()
							|| !npc.withinDistance(
									this,
									getCombatDefinitions().getAttackStyle() == NPCCombatDefinitions.MELEE ? 4
											: getCombatDefinitions()
											.getAttackStyle() == NPCCombatDefinitions.SPECIAL ? 16
													: 8)
													|| !npc.getDefinitions().hasAttackOption()
													|| ((!isAtMultiArea() || !npc.isAtMultiArea())
															&& npc.getAttackedBy() != this && npc
															.getAttackedByDelay() > Utils
															.currentTimeMillis())
															|| !clipedProjectile(npc, false))
						continue;
					possibleTarget.add(npc);
				}
			}
		}
		if (!possibleTarget.isEmpty()) {
			setTarget(possibleTarget
					.get(Utils.getRandom(possibleTarget.size() - 1)));
			return true;
		}
		return false;
	}
}
