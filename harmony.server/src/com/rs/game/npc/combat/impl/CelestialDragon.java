package com.rs.game.npc.combat.impl;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.Graphics;
import com.rs.game.World;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.player.Player;
import com.rs.game.player.content.Combat;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

public class CelestialDragon extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { "Runite dragon" };
	}

	@Override
	public int attack(NPC npc, Entity target) {
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		final Player player = target instanceof Player ? (Player) target : null;
		npc.setCapDamage(1000);
		int damage;
		switch (Utils.getRandom(15)) {
		case 0: // Melee
		case 12:
		case 13:
		case 14:
		case 15:
		case 16:
			if (npc.withinDistance(target, 3)) {
				damage = getRandomMaxHit(npc, defs.getMaxHit(),
						NPCCombatDefinitions.MELEE, target);
				npc.setNextAnimation(new Animation(defs.getAttackEmote()));
				delayHit(npc, 0, target, getMeleeHit(npc, damage));
			} else {
				damage = Utils.getRandom(650);
				if (Combat.hasAntiDragProtection(target)
						|| (player != null && (player.getPrayer().usingPrayer(
								0, 17) || player.getPrayer().usingPrayer(1, 7)))) {
					damage = 0;
					player.getPackets()
							.sendGameMessage(
									"Your "
											+ (Combat
													.hasAntiDragProtection(target) ? "shield"
													: "prayer")
											+ " absorbs most of the dragon's breath!",
									true);
				} else if ((!Combat.hasAntiDragProtection(target)
						|| !player.getPrayer().usingPrayer(0, 17) || !player
						.getPrayer().usingPrayer(1, 7))
						&& player.getFireImmune() > Utils.currentTimeMillis()) {
					damage = Utils.getRandom(164);
					player.getPackets().sendGameMessage(
							"Your potion absorbs most of the dragon's breath!",
							true);
				}
				npc.setNextAnimation(new Animation(defs.getAttackEmote()));
				World.sendProjectile(npc, target, 3835, 28, 16, 35, 35, 16, 0);
				delayHit(npc, 1, target, getRegularHit(npc, damage));
			}
			break;
		case 4:
		case 5:
		case 7:
		case 8:
		case 9:
		case 1: // Dragon breath
			if (npc.withinDistance(target, 3)) {
				damage = Utils.getRandom(650);
				if (Combat.hasAntiDragProtection(target)
						|| (player != null && (player.getPrayer().usingPrayer(
								0, 17) || player.getPrayer().usingPrayer(1, 7)))) {
					damage = 0;
					player.getPackets()
							.sendGameMessage(
									"Your "
											+ (Combat
													.hasAntiDragProtection(target) ? "shield"
													: "prayer")
											+ " absorbs most of the dragon's breath!",
									true);
				} else if ((!Combat.hasAntiDragProtection(target)
						|| !player.getPrayer().usingPrayer(0, 17) || !player
						.getPrayer().usingPrayer(1, 7))
						&& player.getFireImmune() > Utils.currentTimeMillis()) {
					damage = Utils.getRandom(164);
					player.getPackets()
							.sendGameMessage(
									"Your potion fully protects you from the heat of the dragon's breath!",
									true);
				}
				npc.setNextAnimation(new Animation(13160));
				npc.setNextGraphics(new Graphics(2465));
				delayHit(npc, 1, target, getRegularHit(npc, damage));
			} else {
				damage = Utils.getRandom(650);
				if (Combat.hasAntiDragProtection(target)
						|| (player != null && (player.getPrayer().usingPrayer(
								0, 17) || player.getPrayer().usingPrayer(1, 7)))) {
					damage = 0;
					player.getPackets()
							.sendGameMessage(
									"Your "
											+ (Combat
													.hasAntiDragProtection(target) ? "shield"
													: "prayer")
											+ " absorbs most of the dragon's breath!",
									true);
				} else if ((!Combat.hasAntiDragProtection(target)
						|| !player.getPrayer().usingPrayer(0, 17) || !player
						.getPrayer().usingPrayer(1, 7))
						&& player.getFireImmune() > Utils.currentTimeMillis()) {
					damage = Utils.getRandom(164);
					player.getPackets()
							.sendGameMessage(
									"Your potion fully protects you from the heat of the dragon's breath!",
									true);
				}
				npc.setNextAnimation(new Animation(13160));
				World.sendProjectile(npc, target, 3835, 28, 16, 35, 35, 16, 0);
				delayHit(npc, 1, target, getRegularHit(npc, damage));
			}
			break;
		case 2: // timestop?
			npc.setNextAnimation(new Animation(13160));
			npc.setCapDamage(0);
			player.addFreezeDelay(10000);
			player.stopAll();
			player.getPackets().sendGameMessage(
					"You have been frozen in time.");
			WorldTasksManager.schedule(new WorldTask() {
				int loop;

				@Override
				public void run() {
					if (loop == 0) {
						npc.setNextAnimation(new Animation(defs.getAttackEmote()));
						delayHit(npc, 1, target, getRegularHit(npc, 100));
					} else if (loop == 2) {
						npc.setNextAnimation(new Animation(defs.getAttackEmote()));
						delayHit(npc, 1, target, getRegularHit(npc, 75));
					} else if (loop == 4) {
						npc.setNextAnimation(new Animation(defs.getAttackEmote()));
						delayHit(npc, 1, target, getRegularHit(npc, 50));
					} else if (loop == 5) {
						npc.setCapDamage(1000);
						stop();
					}
					loop++;
				}
			}, 0, 1);
		

			break;
		case 6:
		case 10:
		case 11:
		case 3: // mage hit
			damage = Utils.getRandom(250);
			npc.setNextAnimation(new Animation(defs.getAttackEmote()));
			World.sendProjectile(npc, target, 369, 28, 16, 35, 35, 16, 0);
			delayHit(npc, 1, target, getMagicHit(npc, damage));
			break;
		}
		return defs.getAttackDelay();
	}

}
