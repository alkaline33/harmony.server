/*
 * package com.rs.game.npc.combat.impl;
 * 
 * import com.rs.game.Animation; import com.rs.game.Entity; import
 * com.rs.game.npc.NPC; import com.rs.game.npc.combat.CombatScript; import
 * com.rs.game.npc.combat.NPCCombatDefinitions; import
 * com.rs.game.tasks.WorldTask; import com.rs.game.tasks.WorldTasksManager;
 * import com.rs.utils.Utils;
 * 
 * public class DryaxionMinion extends CombatScript {
 * 
 * @Override public Object[] getKeys() { return new Object[] { 15147, 15149,
 * 15151, 15153 }; }
 * 
 * @Override public int attack(final NPC npc, final Entity target) { //melee
 * final NPCCombatDefinitions defs = npc.getCombatDefinitions();
 * npc.setForceMultiArea(true); npc.setForceMultiAttacked(true);
 * npc.setCapDamage(1500); npc.setForceTargetDistance(64);
 * npc.setForceFollowClose(false);
 * 
 * if (Utils.getRandom(4) == 0) { int size = npc.getSize();
 * npc.setNextAnimation(new Animation(defs.getAttackEmote())); delayHit( npc, 1,
 * target, getMeleeHit( npc, getRandomMaxHit(npc, defs.getMaxHit(),
 * NPCCombatDefinitions.MELEE, target)));
 * 
 * return defs.getAttackDelay(); }
 * 
 * if (Utils.getRandom(3) == 0) { //Range hit WorldTasksManager.schedule(new
 * WorldTask() {
 * 
 * @Override public void run() { npc.setNextAnimation(new
 * Animation(defs.getAttackEmote())); delayHit( npc, 1, target, getMagicHit(
 * npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.MAGE,
 * target)));
 * 
 * 
 * 
 * } } );
 * 
 * return defs.getAttackDelay();
 * 
 * } return 0; } }
 */