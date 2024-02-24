package com.rs.game.npc.combat.impl;

import java.util.ArrayList;
import java.util.HashMap;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.ForceTalk;
import com.rs.game.Graphics;
import com.rs.game.Hit;
import com.rs.game.Hit.HitLook;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.npc.others.KalphiteKing;
import com.rs.game.npc.others.sire.Sire;
import com.rs.game.player.Player;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

public class KalphiteKingCombat extends CombatScript {


	@Override
	public Object[] getKeys() {
		return new Object[] { 29993 };
	}

	@Override
	public int attack(final NPC npc, final Entity target) { // melee
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		KalphiteKing kk = (KalphiteKing) npc;
		if (kk.getCount() == 1) {
			NPC npc1 = new NPC(1155, new WorldTile(target.getX() + 1, target.getY() + 1, 0), -1, true, true);
			NPC npc2 = new NPC(1155, new WorldTile(target.getX() + 1, target.getY() + 1, 0), -1, true, true);
			NPC npc3 = new NPC(1155, new WorldTile(target.getX() + 1, target.getY() + 1, 0), -1, true, true);
			NPC npc4 = new NPC(1155, new WorldTile(target.getX() + 1, target.getY() + 1, 0), -1, true, true);
			NPC npc5 = new NPC(1155, new WorldTile(target.getX() + 1, target.getY() + 1, 0), -1, true, true);
			npc1.setForceMultiArea(true);
			npc2.setForceMultiArea(true);
			npc3.setForceMultiArea(true);
			npc4.setForceMultiArea(true);
			npc5.setForceMultiArea(true);
			kk.setCount(kk.getCount() + 1);
			npc.setNextForceTalk(new ForceTalk("Proceed!"));
		}
		if (Utils.getRandom(4) == 0) {
			int size = npc.getSize();
			npc.setNextAnimation(new Animation(defs.getAttackEmote()));
			kk.setCount(kk.getCount() + 1);
			delayHit(npc, 1, target,
					getMeleeHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.MELEE, target)));

			return defs.getAttackDelay();
		}

		if (Utils.getRandom(5) == 0) {
			npc.setNextGraphics(new Graphics(306));
			WorldTasksManager.schedule(new WorldTask() {

				@Override
				public void run() {
					npc.setNextAnimation(new Animation(defs.getAttackEmote()));
					target.setNextGraphics(new Graphics(306));
					kk.setCount(kk.getCount() + 1);
					delayHit(npc, 1, target, getRangeHit(npc,
							getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.RANGE, target)));
				}
			});
			return defs.getAttackDelay();
		}
		if (Utils.getRandom(3) == 0) {
			npc.setNextGraphics(new Graphics(2751));
			WorldTasksManager.schedule(new WorldTask() {

				@Override
				public void run() {
					npc.setNextAnimation(new Animation(defs.getAttackEmote()));
					target.setNextGraphics(new Graphics(2728));
					kk.setCount(kk.getCount() + 1);
					delayHit(npc, 1, target, getMagicHit(npc,
							getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.MAGE, target)));

				}
			});
			return defs.getAttackDelay();
		}
		if (Utils.getRandom(2) == 0) { // Freeze 1hit
			WorldTasksManager.schedule(new WorldTask() {
				@Override
				public void run() {
					kk.setCount(kk.getCount() + 1);
					npc.setNextForceTalk(new ForceTalk("Feel the power of a king!"));
					target.addFreezeDelay(5000);
					target.getPoison().makePoisoned(100);

				}
				
			});
			return defs.getAttackDelay();
		}
		if (Utils.getRandom(1) == 0) { //
			npc.setNextForceTalk(new ForceTalk("The power is mine!"));
			target.addFreezeDelay(5000);
			npc.setNextGraphics(new Graphics(3043));
			target.setNextGraphics(new Graphics (550));
			WorldTasksManager.schedule(new WorldTask() {
				int loop;

				@Override
				public void run() {
					if (loop == 2) {
						target.applyHit(new Hit(npc, target.hasKKShield() ? 350 : 10000,
								HitLook.REGULAR_DAMAGE));
						
					} else if (loop > 2) {
						stop();
					}
					loop++;
				}
			}, 0, 1);		
			return defs.getAttackDelay();
		}
//			World.sendGraphics(null, new Graphics(550), target);
//			ArrayList<Entity> possibleTargets = npc.getPossibleTargets();
//			final HashMap<String, int[]> tiles = new HashMap<String, int[]>();
//			for (Entity t : possibleTargets) {
//				if (t instanceof Player) {
//					Player p = (Player) t;
//					String key = t.getX() + "_" + t.getY();
//					if (!tiles.containsKey(t.getX() + "_" + t.getY())) {
//						tiles.put(key, new int[] { t.getX(), t.getY() });
//						World.spawnTemporaryObject(new WorldObject(3000, 10, 0, t.getX(), t.getY(), 0), 5000);
//
//						WorldTasksManager.schedule(new WorldTask() {
//
//							@Override
//							public void run() {
//								ArrayList<Entity> possibleTargets = npc.getPossibleTargets();
//								for (int[] tile : tiles.values()) {
//									npc.setNextGraphics(new Graphics(3043));
//									World.sendGraphics(null, new Graphics(902), new WorldTile(tile[0], tile[1], 0));
//									for (Entity t : possibleTargets) {
//										if (t.getX() == tile[0] && t.getY() == tile[1]) {
//											t.applyHit(new Hit(npc, target.hasKKShield() ? 350 : 10000,
//													HitLook.REGULAR_DAMAGE));
//											
//										}
//										
//									}
//								}
							//}

					
			
		return defs.getAttackDelay();
	}
}
