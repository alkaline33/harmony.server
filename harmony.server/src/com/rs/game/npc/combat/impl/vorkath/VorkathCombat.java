package com.rs.game.npc.combat.impl.vorkath;


import java.util.ArrayList;
import java.util.List;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.Graphics;
import com.rs.game.Hit;
import com.rs.game.Hit.HitLook;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.npc.others.vorkath.Vorkath;
import com.rs.game.player.Player;
import com.rs.game.player.content.Combat;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

/**
 * 
 * @author Mr_Joopz @ Rune-server
 *
 */
public class VorkathCombat extends CombatScript {

	int x = 0;
	int y = 0;

	private List<WorldObject> acidPools = new ArrayList<WorldObject>();
	
	@Override
	public Object[] getKeys() {
		return new Object[] { 39061 };
	}

	/**
	 * Chuck pool
	 */

	private void FireBallAttack(NPC npc, Entity target) {
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		int x = target.getX();
		int y = target.getY();
		List<String> tiles3 = new ArrayList<String>();
		ArrayList<Integer> distances = new ArrayList<Integer>();
		ArrayList<Entity> possibleTargets3 = npc.getPossibleTargets();
		for (Entity t : possibleTargets3) {
			if (tiles3.contains(target.getX() + " " + target.getY() + " " + target.getPlane())) {
				continue;
			}

			tiles3.add(target.getX() + " " + target.getY() + " " + target.getPlane());
			((Player) t).getPackets().sendProjectile(null, new WorldTile(npc), new WorldTile(t), 1155, 141, 16, 1, 0, 0, 0, 0);
			distances.add(Utils.getDistance(target, npc));
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
							if (target.getX() != tile.getX() || target.getY() != tile.getY()) {
								World.sendGraphics(npc, new Graphics(1154), new WorldTile(tile.getX(), tile.getY(), 0));
								stop();
							}
							if (loop >= 1) {

								if (target.getX() == tile.getX() && target.getY() == tile.getY()) {
									target.applyHit(new Hit(npc, Utils.random(1210), HitLook.REGULAR_DAMAGE));
									World.sendGraphics(npc, new Graphics(1154), new WorldTile(x, y, target.getPlane()));
									stop();
								}
							}
							loop++;
						}
					}, 0, 1);

				}

			}, distances.get(i) - 1);
		}
		return;
	}

	/**
	 * Rapid fire
	 */

	private void RapidFireAttack(NPC npc, Entity target) {
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		int x = target.getX();
		int y = target.getY();
		List<String> tiles3 = new ArrayList<String>();
		ArrayList<Integer> distances = new ArrayList<Integer>();
		ArrayList<Entity> possibleTargets3 = npc.getPossibleTargets();
		for (Entity t : possibleTargets3) {
			if (tiles3.contains(target.getX() + " " + target.getY() + " " + target.getPlane())) {
				continue;
			}

			tiles3.add(target.getX() + " " + target.getY() + " " + target.getPlane());
			((Player) t).getPackets().sendProjectile(null, new WorldTile(npc), new WorldTile(t), 2735, 141, 16, 20, 0, 0, 0, 0);
			distances.add(Utils.getDistance(target, npc) / 2);
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
							if (target.getX() != tile.getX() || target.getY() != tile.getY()) {
								// World.sendGraphics(npc, new Graphics(1154), new WorldTile(tile.getX(),
								// tile.getY(), 0));
								stop();
							}
							if (loop >= 0) {

								if (target.getX() == tile.getX() && target.getY() == tile.getY()) {
									target.applyHit(new Hit(npc, Utils.random(300, 400), HitLook.REGULAR_DAMAGE));
									World.sendGraphics(npc, new Graphics(1154), new WorldTile(x, y, target.getPlane()));
									stop();
								}
							}
							loop++;
						}
					}, 0, 1);

				}

			}, distances.get(i) - 1);
		}
		return;
	}

	/**
	 * 
	 * Grabs special attack
	 */

	public void getSpecialAttack(final NPC npc, final Entity target) {
		Vorkath vorkath = (Vorkath) npc;
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		if (Utils.random(3) == 0) {
			vorkath.setCount(0);
			target.addFreezeDelay(5000);
			World.spawnNPC(30219, new WorldTile(target.getX() + 1 + Utils.random(3), target.getY() + 1 + Utils.random(3), target.getPlane()), -1, true, true);
		} else {
			vorkath.setCount(50);
			acidPools.clear();
			World.spawnTemporaryObject(new WorldObject(14801, 10, 0, new WorldTile(vorkath.getX(), vorkath.getY(), 0)), 22000, true);
			World.spawnTemporaryObject(new WorldObject(14801, 10, 0, new WorldTile(vorkath.getX() + 3, vorkath.getY(), 0)), 22000, true);
			World.spawnTemporaryObject(new WorldObject(14801, 10, 0, new WorldTile(vorkath.getX(), vorkath.getY() + 3, 0)), 22000, true);
			World.spawnTemporaryObject(new WorldObject(14801, 10, 0, new WorldTile(vorkath.getX() + 3, vorkath.getY() + 3, 0)), 22000, true);
			World.spawnTemporaryObject(new WorldObject(14801, 10, 0, new WorldTile(vorkath.getX() - 3, vorkath.getY() - 3, 0)), 22000, true);
			World.spawnTemporaryObject(new WorldObject(14801, 10, 0, new WorldTile(vorkath.getX(), vorkath.getY(), 0)), 22000, true);
			World.spawnTemporaryObject(new WorldObject(14801, 10, 0, new WorldTile(vorkath.getX() + 4, vorkath.getY(), 0)), 22000, true);
			World.spawnTemporaryObject(new WorldObject(14801, 10, 0, new WorldTile(vorkath.getX(), vorkath.getY() + 4, 0)), 22000, true);
			World.spawnTemporaryObject(new WorldObject(14801, 10, 0, new WorldTile(vorkath.getX() + 4, vorkath.getY() + 4, 0)), 22000, true);
			World.spawnTemporaryObject(new WorldObject(14801, 10, 0, new WorldTile(vorkath.getX() - 4, vorkath.getY() - 4, 0)), 22000, true);
			acidPools.add(new WorldObject(14801, 0, 0, new WorldTile(vorkath.getX(), vorkath.getY(), target.getPlane())));
			acidPools.add(new WorldObject(14801, 0, 0, new WorldTile(vorkath.getX() + 4, vorkath.getY(), target.getPlane())));
			acidPools.add(new WorldObject(14801, 0, 0, new WorldTile(vorkath.getX(), vorkath.getY() + 4, target.getPlane())));
			acidPools.add(new WorldObject(14801, 0, 0, new WorldTile(vorkath.getX() + 4, vorkath.getY() + 4, target.getPlane())));
			acidPools.add(new WorldObject(14801, 0, 0, new WorldTile(vorkath.getX() - 4, vorkath.getY() - 4, target.getPlane())));
			acidPools.add(new WorldObject(14801, 0, 0, new WorldTile(vorkath.getX(), vorkath.getY(), target.getPlane())));
			acidPools.add(new WorldObject(14801, 0, 0, new WorldTile(vorkath.getX() + 3, vorkath.getY(), target.getPlane())));
			acidPools.add(new WorldObject(14801, 0, 0, new WorldTile(vorkath.getX(), vorkath.getY() + 3, target.getPlane())));
			acidPools.add(new WorldObject(14801, 0, 0, new WorldTile(vorkath.getX() + 3, vorkath.getY() + 3, target.getPlane())));
			acidPools.add(new WorldObject(14801, 0, 0, new WorldTile(vorkath.getX() - 3, vorkath.getY() - 3, target.getPlane())));
			/**
			 * quickfire - 300-400 damage per hit
			 */
		}
		
	}


	/**
	 * 
	 * Grabs Attack style
	 */

	public void getAttackStyle(final NPC npc, final Entity target) {

		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		final Player player = target instanceof Player ? (Player) target : null;
		int damage = Utils.getRandom(720);
		if (Utils.random(3) == 0) {
			delayHit(npc, 1, target, getRangeHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.RANGE, target)));
			World.sendProjectile(npc, target, 679, 41, 16, 41, 0, 0, 10);
			npc.setNextAnimation(new Animation(defs.getAttackEmote()));
		} else if (Utils.random(3) == 1) {
			delayHit(npc, 1, target, getMagicHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.MAGE, target)));
			World.sendProjectile(npc, target, 500, 41, 16, 41, 0, 0, 10);
			// World.sendProjectile(npc, target, 1825, 41, 16, 41, 0, 16, 0);
			npc.setNextAnimation(new Animation(defs.getAttackEmote()));
		} else if (Utils.random(3) == 2) {
			FireBallAttack(npc, target);
			npc.setNextAnimation(new Animation(37960));
			// World.sendProjectile(npc, target, 1155, 141, 16, 1, 35, 16, 0);

		} else {
			if (Utils.random(3) == 0) {
				if (Combat.hasAntiDragProtection(target) || player != null && (player.getPrayer().usingPrayer(0, 17) || player.getPrayer().usingPrayer(1, 7))) {
					damage = 0;
				}
				if (player != null && player.getFireImmune() > Utils.currentTimeMillis()) {
					if (damage != 0) {
						damage = Utils.getRandom(164);
					}
				} else if (damage == 0) {
					damage = Utils.getRandom(164);
				} else if (player != null) {
					player.getPackets().sendGameMessage("You are hit by Vorkath's fiery breath!", true);
				}
				delayHit(npc, 2, target, getRegularHit(npc, damage));
				World.sendProjectile(npc, target, 393, 34, 16, 30, 35, 16, 0);
				npc.setNextAnimation(new Animation(defs.getAttackEmote()));
			} else if (Utils.random(3) == 1) {
				if (Combat.hasAntiDragProtection(target) || player != null && (player.getPrayer().usingPrayer(0, 17) || player.getPrayer().usingPrayer(1, 7))) {
					damage = 0;
				}
				if (player != null && player.getFireImmune() > Utils.currentTimeMillis()) {
					if (damage != 0) {
						damage = Utils.getRandom(164);
					}
				} else if (damage == 0) {
					damage = Utils.getRandom(164);
				} else if (player != null) {
					player.getPackets().sendGameMessage("You are hit by Vorkath's poisonous breath!", true);
				}
				if (!target.getPoison().isPoisoned()) {
					target.getPoison().makePoisoned(100);
				}
				delayHit(npc, 2, target, getRegularHit(npc, damage));
				World.sendProjectile(npc, target, 394, 34, 16, 30, 35, 16, 0);
				npc.setNextAnimation(new Animation(defs.getAttackEmote()));
			} else {
				if (Combat.hasAntiDragProtection(target) || player != null && (player.getPrayer().usingPrayer(0, 17) || player.getPrayer().usingPrayer(1, 7))) {
					damage = 0;
				}
				if (player != null && player.getFireImmune() > Utils.currentTimeMillis()) {
					if (damage != 0) {
						damage = Utils.getRandom(164);
					}
				} else if (damage == 0) {
					damage = Utils.getRandom(164);
				} else if (player != null) {
					player.getPackets().sendGameMessage("You are hit by Vorkath's fiery breath!", true);
				}
				delayHit(npc, 2, target, getRegularHit(npc, damage));
				World.sendProjectile(npc, target, 395, 34, 16, 30, 35, 16, 0);
				npc.setNextAnimation(new Animation(defs.getAttackEmote()));
			}
		}
	}

	@Override
	public int attack(final NPC npc, final Entity target) {
		Vorkath vorkath = (Vorkath) npc;
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		npc.setForceMultiArea(true);
		npc.setForceMultiAttacked(true);
		npc.setCapDamage(1500);
		int count = vorkath.getCount();
		switch (count) {

		default:
			// System.out.println(count);
			getAttackStyle(npc, target);
			vorkath.setCount(count += 1);
			break;
		case 6:
			getSpecialAttack(npc, target);
			break;
		case 50:
		case 51:
		case 52:
		case 53:
		case 54:
		case 55:
		case 56:
		case 57:
		case 58:
		case 59:
		case 60:
		case 61:
		case 62:
		case 63:
		case 64:
		case 65:
		case 66:
		case 67:
		case 68:
		case 69:
		case 70:
		case 71:
		case 72:
		case 73:
		case 74:
		case 75:
			for (WorldObject acidPool : acidPools) {
				if (acidPool == null) {
					continue;
				}
				if (target.matches(acidPool)) {// player under one of the acidPools
					target.applyHit(new Hit(npc, Utils.random(200), HitLook.POISON_DAMAGE));
					if (!target.getPoison().isPoisoned()) {
						target.getPoison().makePoisoned(60);
					}
				}
			}
			npc.setNextAnimation(new Animation(defs.getAttackEmote()));
			RapidFireAttack(npc, target);
			vorkath.setCount(count += 1);
			break;
		case 76:
			npc.setNextAnimation(new Animation(defs.getAttackEmote()));
			vorkath.setCount(0);
			break;
		}
		if (count < 50) {

			return defs.getAttackDelay();
		} else {
			return 2;
		}
	}
}
