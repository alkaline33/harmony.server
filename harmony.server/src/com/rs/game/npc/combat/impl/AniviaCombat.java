package com.rs.game.npc.combat.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
import com.rs.game.npc.others.Anivia;
import com.rs.game.player.Player;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.net.decoders.handlers.ButtonHandler;
import com.rs.utils.Utils;

/**
 * 
 * @author Mr_Joopz @ Rune-server
 *
 */
public class AniviaCombat extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 30081, 30082 };
		// to have best basic table ingame * drop the new amulets
	}

	@Override
	public int attack(final NPC npc, final Entity target) {
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		if (npc.getHitpoints() < 2001 && npc.getId() == 30081) {
			Anivia anivia = (Anivia) npc;
			anivia.transformIntoNPC(30082);
			anivia.setCount(0);
			
		}
		if (npc.getId() == 30082) {
			Anivia anivia = (Anivia) npc;
			anivia.setCount(+ 1);
			if (anivia.getCount() >= 15) {
				anivia.transformIntoNPC(30081);
			}
				anivia.setHitpoints(4000);
		}
		else if (npc.getId() == 30081) {
		switch (Utils.getRandom(9)) {
		

		case 0:
		case 1:
			npc.setNextAnimation(new Animation(defs.getAttackEmote()));																// MELEE
			delayHit(npc, 0, target,
					getMeleeHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.MELEE, target)));
			break;
		

		case 2:
		case 3:
			npc.setNextAnimation(new Animation(defs.getAttackEmote()));																		// RANGE
			delayHit(npc, 0, target,
					getRangeHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.RANGE, target)));
			break;
		

		case 4:
		case 5:
			npc.setNextAnimation(new Animation(defs.getAttackEmote()));																		// MAGIC
			delayHit(npc, 0, target,
					getMagicHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.MAGE, target)));
			break;
		
			
			
		case 6:
				npc.setNextAnimation(new Animation(defs.getAttackEmote()));
	             npc.setNextForceTalk(new ForceTalk("Flash frost!"));
	             List<String> tiles3 = new ArrayList<String>();
	     		ArrayList<Integer> distances = new ArrayList<Integer>();
	     		ArrayList<Entity> possibleTargets3 = npc.getPossibleTargets();
	         	for (Entity t : possibleTargets3) {
	    			if (tiles3.contains(t.getX() + " " + t.getY() + " " + t.getPlane())) {
						continue;
					}
	    			tiles3.add(t.getX() + " " + t.getY() + " " + t.getPlane());
	    			((Player) t).getPackets().sendProjectile(null, new WorldTile(npc), new WorldTile(t), 2119, 30, 30, 1, 0, 0,
	    					0, 0);
	    			distances.add(Utils.getDistance(t, npc));
	    		}
	    		for (int i = 0; i < tiles3.size(); i++) {
	    			String s = tiles3.get(i);
	    			WorldTile tile = new WorldTile(Integer.valueOf(s.split("\\s")[0]), Integer.valueOf(s.split("\\s")[1]),
	    					Integer.valueOf(s.split("\\s")[2]));
	    			WorldTasksManager.schedule(new WorldTask() {
	    				@Override
	    				public void run() {
	    					ArrayList<Entity> possibleTargets3 = npc.getPossibleTargets();
	    					for (Entity t : possibleTargets3) {
	    						if (t.getX() == tile.getX() && t.getY() == tile.getY()) {
									t.applyHit(new Hit(npc, 750, HitLook.REGULAR_DAMAGE));
								}
	    					}
	    				}

	    			}, distances.get(i) - 1);
	    		}
	    		return 7;
	    		
		case 7: // frost bite = AOE bleed
	  			  npc.setNextForceTalk(new ForceTalk("Frost bite!"));
	  			  npc.setNextAnimation(new Animation(defs.getAttackEmote()));
	  	     		ArrayList<Entity> possibleTargets4 = npc.getPossibleTargets();
	  	         	for (Entity t : possibleTargets4) {
	  				if (t instanceof Player) {
	  					Player p = (Player) t;
	  					//((Player) t).sendMessage("b");
	  					WorldTasksManager.schedule(new WorldTask() {
	  						int loop;

	  						@Override
	  						public void run() {
	  							if (loop == 0) {
	  								if (t.getHitpoints() < 1) {
	  									stop();
	  								}
	  								npc.setNextAnimation(new Animation(defs.getAttackEmote()));
	  								delayHit(npc, 1, t, getRegularHit(npc, 160));
	  							} else if (loop == 2) {
	  								if (t.getHitpoints() < 1) {
	  									stop();
	  								}
	  								npc.setNextAnimation(new Animation(defs.getAttackEmote()));
	  								delayHit(npc, 1, t, getRegularHit(npc, 120));
	  							} else if (loop == 4) {
	  								if (t.getHitpoints() < 1) {
	  									stop();
	  								}
	  								npc.setNextAnimation(new Animation(defs.getAttackEmote()));
	  								delayHit(npc, 1, t, getRegularHit(npc, 80));
	  							} else if (loop == 5) {
	  								if (t.getHitpoints() < 1) {
	  									stop();
	  								}
	  								npc.setNextAnimation(new Animation(defs.getAttackEmote()));
	  								delayHit(npc, 1, t, getRegularHit(npc, 80));
	  							} else if (loop == 6) {
	  								if (t.getHitpoints() < 1) {
	  									stop();
	  								}
	  								npc.setNextAnimation(new Animation(defs.getAttackEmote()));
	  								delayHit(npc, 1, t, getRegularHit(npc, 60));
	  							} else if (loop == 7) {
	  								if (t.getHitpoints() < 1) {
	  									stop();
	  								}
	  								npc.setNextAnimation(new Animation(defs.getAttackEmote()));
	  								delayHit(npc, 1, t, getRegularHit(npc, 240));
	  							} else if (loop == 8) {
	  								stop();
	  								
	  							}
	  							loop++;
	  						}
	  					}, 0, 1);
	  				}
	  			}
	  			break;
		
		case 8: // crystallize = barrage for 15 sec and remove wep

			  npc.setNextForceTalk(new ForceTalk("Crystallize!"));
			  npc.setNextAnimation(new Animation(defs.getAttackEmote()));
			  ArrayList<Entity> possibleTargets = npc.getPossibleTargets();
			   List<String> tiles = new ArrayList<String>();
	         	for (Entity t : possibleTargets) {
				if (t instanceof Player) {
					Player p = (Player) t;
					if (p.getEquipment().getAmuletId() == 29504 && p.aniviaamuletcharges > 0 || p.getEquipment().getAmuletId() == 29502 && p.aniviaamuletcharges > 0) {
						p.getPackets().sendGameMessage("Your amulet has caused you to dodge the freeze attack.");
						p.aniviaamuletcharges --;
						break;
					}
					t.addFreezeDelay(15000);
					World.sendGraphics(null, new Graphics (1587), new WorldTile(t.getX(), t.getY(), 0));
					//t.setNextGraphics(new Graphics (1587));
					if (((Player) t).getInventory().hasFreeSlots() == true) {
						ButtonHandler.sendRemove((Player) t, 3);
					}
					//break;
		
				
				}
				//break;
		}
	    break;
		case 9: // glacia storm == AOE of ice & stat
										// drain & prayer drain
			  npc.setNextForceTalk(new ForceTalk("Glacia storm!"));
			  npc.setNextAnimation(new Animation(defs.getAttackEmote()));
			  ArrayList < Entity > possibleTargets1 = npc.getPossibleTargets();
              final HashMap < String, int[] > tiles1 = new HashMap < String, int[] > ();
              for (Entity t: possibleTargets1) {
				if (t instanceof Player) {
					Player p = (Player) t;
					String key = t.getX() + "_" + t.getY();
					if (!tiles1.containsKey(t.getX() + "_" + t.getY())) {
						tiles1.put(key, new int[] { t.getX(), t.getY() });
						World.sendGraphics(null, new Graphics(1014), new WorldTile(t.getX(), t.getY(), 0));
						World.spawnTemporaryObject(new WorldObject(12748, 10, 0, t.getX(), t.getY(), 0), 2400);
						((Player) t).getPrayer().drainPrayerOnHalf();
						((Player) t).getSkills().drainLevel(1, 10);
						((Player) t).getSkills().drainLevel(2, 10);
						((Player) t).getSkills().drainLevel(0, 10);
						((Player) t).getSkills().drainLevel(4, 10);
						((Player) t).getSkills().drainLevel(5, 10);
						((Player) t).sendMessage("Anivia has drained your stats.");

						WorldTasksManager.schedule(new WorldTask() {

							@Override
							public void run() {
								ArrayList<Entity> possibleTargets = npc.getPossibleTargets();
								for (int[] tile : tiles1.values()) {
									for (Entity t : possibleTargets) {
										if (t.getX() > tile[0] - 2 && t.getX() < tile[0] + 2 && t.getY() > tile[1] - 2
												&& t.getY() < tile[1] + 2) {
											t.applyHit(
													new Hit(npc, Utils.getRandom(100) + 800, HitLook.REGULAR_DAMAGE));
										}
									}
								}
								
							}

							
						}, 3);
						//break;
					}
					break;
					
				}
				
			}
             
		}
		}
		return defs.getAttackDelay();
	}
}
