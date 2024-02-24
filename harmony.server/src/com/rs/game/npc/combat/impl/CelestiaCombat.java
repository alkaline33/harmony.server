package com.rs.game.npc.combat.impl;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.ForceTalk;
import com.rs.game.Graphics;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.npc.others.Celestia;
import com.rs.game.player.Player;
import com.rs.utils.Utils;

/**
 * 
 * @author Mr_Joopz @ Rune-server
 *
 */
public class CelestiaCombat extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 29981 };
	}
	
	@Override
	public int attack(final NPC npc, final Entity target) {
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		Celestia celestia = (Celestia) npc;
		if (celestia.getCount() > 4) {
			celestia.setCount(0);
		}
		/**
		 * Phase 1
		 */
		if (npc.getHitpoints() >= 7000 && npc.getHitpoints() <= 10000) {
			switch (celestia.getCount()) {
			case 0:
				npc.setNextAnimation(new Animation(3236));
				if (!target.getPoison().isPoisoned()) {
					target.getPoison().makePoisoned(300);
				}
				delayHit(npc, 0, target,
						getRangeHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.RANGE, target)));
				celestia.setCount(celestia.getCount()+1);
				
				break;
			case 1:
				npc.setNextAnimation(new Animation(711));
				npc.setNextGraphics(new Graphics (108));
				delayHit(npc, 0, target,
						getMagicHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.MAGE, target)));
				celestia.setCount(celestia.getCount()+1);
				break;
			case 2:
				npc.setNextAnimation(new Animation(defs.getAttackEmote()));
				delayHit(npc,3,target,getMeleeHit(npc,getRandomMaxHit(npc, 700,NPCCombatDefinitions.MELEE,target)));
				celestia.setCount(0);
				break;
			}
			return defs.getAttackDelay();
		}
		/**
		 * Phase 2
		 */
		if (npc.getHitpoints() >= 6000 && npc.getHitpoints() < 7000) {
					switch (celestia.getCount()) {
					case 0:
						npc.setNextForceTalk(new ForceTalk ("Aid me with your power!"));
					       if (target instanceof Player) {
                               Player p = (Player) target;
                               p.getPrayer()
                                   .drainPrayerOnHalf();
					       }
					       npc.setNextAnimation(new Animation(3236));
						delayHit(npc, 0, target,
								getRangeHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.RANGE, target)));
						
						celestia.setCount(celestia.getCount()+1);
						break;
					case 1:
						npc.setNextForceTalk(new ForceTalk ("I shall drain you of all protection!"));
					       if (target instanceof Player) {
                               Player p = (Player) target;
                               p.getPrayer()
                                   .drainPrayerOnHalf();
                               if (p.getOverloadDelay() > 1) {
                               p.setOverloadDelay(p.getOverloadDelay() / 2);
                               }
                           }
						npc.setNextAnimation(new Animation(789));
						delayHit(npc, 0, target,
								getMagicHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.MAGE, target)));
						celestia.setCount(celestia.getCount()+1);
						break;
					case 2:
						npc.setNextForceTalk(new ForceTalk ("Your gods can't protect you now!"));
					       if (target instanceof Player) {
                               Player p = (Player) target;
                               p.getPrayer()
                                   .drainPrayerOnHalf();
                           }
					       npc.setNextAnimation(new Animation(3236));
						delayHit(npc, 0, target,
								getRangeHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.RANGE, target)));
						celestia.setCount(0);
						break;
			}
			return defs.getAttackDelay();
		}
		/**
		 * Phase 3
		 */
		if (npc.getHitpoints() >= 3000 && npc.getHitpoints() < 6000) {
			switch (celestia.getCount()) {
			case 0:
				npc.setNextAnimation(new Animation(3236));
				delayHit(npc,3,target,getRangeHit(npc,getRandomMaxHit(npc, 900,NPCCombatDefinitions.RANGE,target)));
				celestia.setCount(celestia.getCount()+1);
				break;
			case 1:
				npc.setNextAnimation(new Animation(711));
				npc.setNextGraphics(new Graphics (108));
				delayHit(npc, 0, target,
						getMagicHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.MAGE, target)));
				celestia.setCount(celestia.getCount()+1);
				break;
			case 2:
				npc.setNextAnimation(new Animation(defs.getAttackEmote()));
				delayHit(npc,3,target,getMeleeHit(npc,getRandomMaxHit(npc, 900,NPCCombatDefinitions.MELEE,target)));
				celestia.setCount(0);
				break;
				}
			return defs.getAttackDelay();
			}
		/**
		 * Phase 4
		 */
		if (npc.getHitpoints() < 3000) {
			switch (celestia.getCount()) {
			case 0:
				npc.setNextAnimation(new Animation(3236));
				delayHit(npc,3,target,getRangeHit(npc,getRandomMaxHit(npc, 10000,NPCCombatDefinitions.RANGE,target)));
				celestia.setCount(celestia.getCount()+1);
				break;
			case 1:
				npc.setNextAnimation(new Animation(711));
				npc.setNextGraphics(new Graphics (108));
				delayHit(npc,4,target,getMagicHit(npc,getRandomMaxHit(npc, 10000,NPCCombatDefinitions.MAGE,target)));
				celestia.setCount(celestia.getCount()+1);
				break;
			case 2:
				int style = Utils.random(2);
				switch (style) {
				case 0:
					npc.setNextAnimation(new Animation(defs.getAttackEmote()));
					delayHit(npc,3,target,getMeleeHit(npc,getRandomMaxHit(npc, 7000,NPCCombatDefinitions.MELEE,target)));
					celestia.setCount(0);
					break;
				case 1:
					npc.setNextAnimation(new Animation(711));
					npc.setNextGraphics(new Graphics (108));
					delayHit(npc,4,target,getMagicHit(npc,getRandomMaxHit(npc, 10000,NPCCombatDefinitions.MAGE,target)));
					celestia.setCount(0);
					break;
				case 2:
					npc.setNextAnimation(new Animation(3236));
					delayHit(npc,3,target,getRangeHit(npc,getRandomMaxHit(npc, 10000,NPCCombatDefinitions.RANGE,target)));
					celestia.setCount(0);
					break;
				}
				}
			return defs.getAttackDelay();
			}
		return defs.getAttackDelay();
	}
	}

	
