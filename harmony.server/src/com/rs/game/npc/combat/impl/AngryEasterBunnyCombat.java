package com.rs.game.npc.combat.impl;

import java.util.ArrayList;
import java.util.List;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.ForceTalk;
import com.rs.game.Graphics;
import com.rs.game.Hit;
import com.rs.game.Hit.HitLook;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.player.Player;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

/**
 * 
 * @author Mr_Joopz @ Rune-server
 *
 */
public class AngryEasterBunnyCombat extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 29975 };
	}
	
	/**
	 * Special Attack
	 */
	
	private void specialAttack(NPC npc, Entity target) {

		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		int x = target.getX();
		int y = target.getY();
		List<String> tiles3 = new ArrayList<String>();
		ArrayList<Integer> distances = new ArrayList<Integer>();
		ArrayList<Entity> possibleTargets3 = npc.getPossibleTargets();
		for (Entity t : possibleTargets3) {
			if (tiles3.contains(t.getX() + " " + t.getY() + " " + t.getPlane())) {
				continue;
			}
			World.sendProjectile(npc, target, 977, 80, 30, 40, 20, 5, 0);
			World.sendProjectile(npc, new WorldTile(target.getX() + Utils.random(1, 3), target.getY(), 0), 978, 80, 30, 40, 20, 5, 0);
			World.sendProjectile(npc, new WorldTile(target.getX() - Utils.random(1, 3), target.getY(), 0), 979, 80, 30, 40, 20, 5, 0);
			tiles3.add(t.getX() + " " + target.getY() + " " + target.getPlane());
			distances.add(Utils.getDistance(t, npc));
		}

		for (int i = 0; i < tiles3.size(); i++) {

			String s = tiles3.get(i);
			WorldTile tile = new WorldTile(Integer.valueOf(s.split("\\s")[0]), Integer.valueOf(s.split("\\s")[1]), Integer.valueOf(s.split("\\s")[2]));
			WorldTasksManager.schedule(new WorldTask() {
				@Override
				public void run() {
					WorldTasksManager.schedule(new WorldTask() {
						int loop;

						@Override
						public void run() {
							for (Entity t : possibleTargets3) {
								if (target.getX() != tile.getX() || target.getY() != tile.getY()) {
									stop();
								}
								if (t.getX() == tile.getX() && t.getY() == tile.getY()) {
									target.applyHit(new Hit(npc, 300, HitLook.REGULAR_DAMAGE));
									target.setNextGraphics(new Graphics(1043));
								}
							}
							stop();
						}
					}, 0, 1);

				}

			}, distances.get(i) - 1);
		}
		return;
	}

	/**
	 * 
	 * Grabs Attack style
	 */

	public void getAttackStyle(final NPC npc, final Entity target) {
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		final Player player = target instanceof Player ? (Player) target : null;
		if (Utils.random(10) == 0) {
			specialAttack(npc, target);
			npc.setNextAnimation(new Animation(defs.getAttackEmote()));
			npc.setNextForceTalk(new ForceTalk ("CHOCOLATE!!!!!!!!!!!!!!!!"));
		} else {
		if (target.withinDistance(npc, 1)) {
				delayHit(npc, 0, target, getMeleeHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.MELEE, target)));
			npc.setNextAnimation(new Animation(defs.getAttackEmote()));
			
		} else {
			if (Utils.random(3) == 0) {
				delayHit(npc, 2, target, getRangeHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.RANGE, target)));
				npc.setNextAnimation(new Animation(defs.getAttackEmote()));
				World.sendProjectile(npc, target, 977, 41, 16, 41, 0, 16, 0);
			} else {
				delayHit(npc, 2, target, getMagicHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.MAGE, target)));
				npc.setNextAnimation(new Animation(defs.getAttackEmote()));
				World.sendProjectile(npc, target, 979, 41, 16, 41, 0, 16, 0);
			}
			
		}
		}
	}

	@Override
	public int attack(final NPC npc, final Entity target) {
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		getAttackStyle(npc, target);
		return defs.getAttackDelay();
	}
}
