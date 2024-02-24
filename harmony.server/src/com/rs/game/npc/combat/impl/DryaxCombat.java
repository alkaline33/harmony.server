/*
 * package com.rs.game.npc.combat.impl;
 * 
 * import java.util.ArrayList; import java.util.HashMap;
 * 
 * import com.rs.game.Animation; import com.rs.game.Entity; import
 * com.rs.game.ForceTalk; import com.rs.game.Graphics; import com.rs.game.Hit;
 * import com.rs.game.WorldObject; import com.rs.game.WorldTile; import
 * com.rs.game.Hit.HitLook; import com.rs.game.World; import
 * com.rs.game.npc.NPC; import com.rs.game.npc.combat.CombatScript; import
 * com.rs.game.npc.combat.NPCCombatDefinitions; import
 * com.rs.game.player.Player; import com.rs.game.tasks.WorldTask; import
 * com.rs.game.tasks.WorldTasksManager; import com.rs.utils.Utils;
 * 
 * public class DryaxCombat extends CombatScript {
 * 
 * @Override public Object[] getKeys() { return new Object[] { }; }
 * 
 * @Override public int attack(final NPC npc, final Entity target) { //melee
 * final NPCCombatDefinitions defs = npc.getCombatDefinitions(); if (npc.hits ==
 * 10) { World.spawnNPC(15153, new WorldTile(target.getX() - 1, target.getY() -
 * 1, 3), -1, true, true); World.spawnNPC(15153, new WorldTile(target.getX() +
 * 1, target.getY() + 1, 3), -1, true, true); npc.setNextForceTalk(new
 * ForceTalk( "Get them, my minions!")); } npc.setForceMultiArea(true);
 * npc.setForceMultiAttacked(true); npc.setCapDamage(1000);
 * npc.setForceTargetDistance(64); npc.setForceFollowClose(false); if
 * (Utils.getRandom(4) == 0) { int size = npc.getSize();
 * npc.setNextAnimation(new Animation(defs.getAttackEmote())); npc.hits ++;
 * delayHit( npc, 1, target, getMeleeHit( npc, getRandomMaxHit(npc,
 * defs.getMaxHit(), NPCCombatDefinitions.MELEE, target)));
 * 
 * return defs.getAttackDelay(); }
 * 
 * if (Utils.getRandom(3) == 0) { npc.setNextAnimation(new Animation(14221));
 * npc.setNextGraphics(new Graphics(2728)); WorldTasksManager.schedule(new
 * WorldTask() {
 * 
 * @Override public void run() {
 * 
 * target.setNextGraphics(new Graphics(2728)); npc.hits ++; delayHit( npc, 1,
 * target, getMagicHit( npc, getRandomMaxHit(npc, defs.getMaxHit(),
 * NPCCombatDefinitions.MAGE, target)));
 * 
 * 
 * 
 * } } );
 * 
 * if (Utils.getRandom(2) == 0) { // Heals WorldTasksManager.schedule(new
 * WorldTask() {
 * 
 * @Override public void run() { npc.heal(20); npc.hits ++;
 * npc.setNextForceTalk(new ForceTalk( "You cannot stop my healing!"));
 * 
 * } } ); if (Utils.getRandom(1) == 0) { // npc.setNextForceTalk(new ForceTalk(
 * "The floor is now mine!")); ArrayList<Entity> possibleTargets =
 * npc.getPossibleTargets(); final HashMap<String, int[]> tiles = new
 * HashMap<String, int[]>(); for (Entity t : possibleTargets) { if (t instanceof
 * Player) { Player p = (Player) t; String key = t.getX() + "_" + t.getY(); if
 * (!tiles.containsKey(t.getX() + "_" + t.getY())) { tiles.put(key, new int[] {
 * t.getX(), t.getY() }); World.spawnTemporaryObject(new WorldObject(2089, 10,
 * 0, t.getX(), t.getY(), 0), 2400);
 * 
 * 
 * WorldTasksManager.schedule(new WorldTask() {
 * 
 * @Override public void run() { ArrayList<Entity> possibleTargets = npc
 * .getPossibleTargets(); for (int[] tile : tiles.values()) {
 * npc.setNextGraphics(new Graphics(3032)); World.sendGraphics(null, new
 * Graphics(361), new WorldTile(tile[0], tile[1], 0)); for (Entity t :
 * possibleTargets) if (t.getX() == tile[0] && t.getY() == tile[1])
 * t.applyHit(new Hit(npc, Utils.getRandom(200) + 150, HitLook.REGULAR_DAMAGE));
 * } }
 * 
 * }, 5);
 * 
 * 
 * } else { // range - Whip Attack npc.setNextAnimation(new Animation(426));
 * npc.setNextGraphics(new Graphics(96)); npc.hits ++; delayHit( npc, 0, target,
 * getRangeHit( npc, getRandomMaxHit(npc, defs.getMaxHit(),
 * NPCCombatDefinitions.RANGE, target))); } return defs.getAttackDelay(); }
 * 
 * return 0; } } } } return 0; } }
 * 
 */