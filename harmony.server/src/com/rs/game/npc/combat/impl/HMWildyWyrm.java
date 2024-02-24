package com.rs.game.npc.combat.impl;

import java.util.ArrayList;

import com.rs.game.Entity;
import com.rs.game.ForceTalk;
import com.rs.game.World;
import com.rs.game.Animation;
import com.rs.game.Graphics;
import com.rs.game.Hit;
import com.rs.game.WorldTile;
import com.rs.game.Hit.HitLook;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.player.Player;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

/**
 * The combat script of the HC WildyWyrm.
 * 
 * @author Mr_Joopz
 * 
 *
 */
public class HMWildyWyrm extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 30025 };
	}

	@Override
	public int attack(NPC npc, Entity target) {
		if (Utils.getRandom(10) == 0)
			npc.setNextForceTalk(new ForceTalk("You cannot be helped now!"));
		NPCCombatDefinitions cdefs = npc.getCombatDefinitions();
		npc.setCapDamage(1000);
		int distanceX = target.getX() - npc.getX(), 
			distanceY = target.getY() - npc.getY(),
			size = npc.getSize();
		if (npc.hits == 6) {
			World.spawnNPC(30026, new WorldTile(2993, 3894, 0), -1, true, true);
			npc.setNextForceTalk(new ForceTalk(
					"Get them, my child!"));
			npc.hits ++;
		}
		if (npc.hits == 35) {
			World.spawnNPC(30026, new WorldTile(2993, 3894, 0), -1, true, true);
			World.spawnNPC(30026, new WorldTile(2993, 3892, 0), -1, true, true);
		//	World.spawnNPC(30026, new WorldTile(2993, 3890, 0), -1, true, true);
			npc.setNextForceTalk(new ForceTalk(
					"Get them, my children!"));
			npc.hits ++;
		}
		boolean inMeleeDistance = !(distanceX > size || distanceX < -1 || distanceY > size || distanceY < -1);
		switch(Utils.getRandom(!inMeleeDistance ? 1 : 4)) {
		case 0:
		case 1:
			sendMagicAttack(npc, 12794);
			break;
		case 2:
			target.getPoison().makePoisoned(200);
			break;
		case 3:
		case 4:
			sendMeleeAttack(npc, target, cdefs.getAttackEmote());
			break;
		}
		if (Utils.getRandom(3) == 0)
			sendRangedAttack(npc, 12794);
		return cdefs.getAttackDelay();
	}
	
	/**
	 * Sends a melee attack.
	 * @param npc The WildyWyrm.
	 * @param target The target.
	 * @param emote The emote Id.
	 */
	private void sendMeleeAttack(NPC npc, Entity target, int emote) {
		npc.setNextAnimation(new Animation(emote));
		npc.hits ++;
		delayHit(npc, 0, target, getMeleeHit(npc, getRandomMaxHit(npc, 
				npc.getCombatDefinitions().getMaxHit(), NPCCombatDefinitions.MELEE, target)));
	}
	
	/**
	 * Sends the magic attack.
	 * @param npc The WildyWyrm.
	 * @param emote The emote Id.
	 */
	private void sendMagicAttack(NPC npc, int emote) {
		final ArrayList<Entity> possibleTargets = npc.getPossibleTargets();
		final int requiredAmount = possibleTargets.size() > 5 ? 5 : possibleTargets.size();
		ArrayList<Entity> targets = new ArrayList<Entity>(requiredAmount);
		while(targets.size() < requiredAmount) {
			Entity e = possibleTargets.get(Utils.random(possibleTargets.size()));
			if (e != null && !targets.contains(e))
				targets.add(e);
		}
		npc.setNextAnimation(new Animation(emote));
		for (final Entity target : targets) {
			if (target == null)
				continue;
			World.sendProjectile(npc, target, 58, 50, 25, 45, 35, 16, 0);
			npc.hits ++;
			final int hitAmount = 1 + (Utils.getRandom(1) == 1 ? Utils.getRandom(1) : 0);
			ArrayList<Hit> hits = new ArrayList<Hit>(hitAmount);
			hits.add(new Hit(npc, getRandomMaxHit(npc, 650, NPCCombatDefinitions.MAGE, target), HitLook.MAGIC_DAMAGE));
			if (hitAmount > 1) {
				hits.add(new Hit(npc, Utils.random(50, 325), HitLook.DESEASE_DAMAGE));
				if (target instanceof Player) {
					WorldTasksManager.schedule(new WorldTask() {
						@Override
						public void run() {
							((Player) target).getPackets().sendGameMessage(
									"<col=ff9501>The Wildywyrm's magical attack has caused horrible damage to you. " +
									"It seems that you're burned.");
						}
					}, 1);
				}
			}
			delayHit(npc, 1, target, hits.toArray(new Hit[hitAmount]));
		}
	}
	
	/**
	 * Sends the ranging attack.
	 * @param npc The WildyWyrm.
	 * @param emote The emote Id.
	 */
	private void sendRangedAttack(NPC npc, int emote) {
		npc.setNextAnimation(new Animation(emote));
		for (final Entity e : npc.getPossibleTargets()) {
			if (e != null && e.withinDistance(npc.getMiddleWorldTile(), 4) && Utils.getRandom(1) == 0) {
				e.setNextGraphics(new Graphics(3002));
				npc.hits ++;
				delayHit(npc, 1, e, getRangeHit(npc, getRandomMaxHit(
						npc, 550, NPCCombatDefinitions.RANGE, e)));
				if (e instanceof Player)
					((Player) e).getPackets().sendGameMessage(
							"<col=ff0000>You have been damaged by the Wildywyrm's ranged attack.");
			}
		}
	}
}