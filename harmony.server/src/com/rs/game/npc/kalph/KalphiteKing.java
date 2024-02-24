// package com.rs.game.npc.kalph;
//
// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.List;
//
// import com.rs.game.Animation;
// import com.rs.game.Entity;
// import com.rs.game.Graphics;
// import com.rs.game.Hit;
// import com.rs.game.Hit.HitLook;
// import com.rs.game.World;
// import com.rs.game.WorldTile;
// import com.rs.game.npc.NPC;
// import com.rs.game.npc.combat.NPCCombatDefinitions;
// import com.rs.game.npc.pet.Pet;
// import com.rs.game.player.Player;
// import com.rs.game.tasks.WorldTask;
// import com.rs.game.tasks.WorldTasksManager;
// import com.rs.utils.Utils;
//
// @SuppressWarnings("serial")
// public class KalphiteKing extends NPC {
//
// public KalphiteKing(int id, WorldTile tile, int mapAreaNameHash,
// boolean canBeAttackFromOutOfArea, boolean spawned) {
// super(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
// setForceAgressive(true);
// setCapDamage(1250);
// aboveGround = true;
// auraTicks = 0;
// state = CombatState.values()[IDS.indexOf(id)];
// }
//
// public boolean aboveGround;
// public CombatState state;
//
// private long lastSwitch;
// private int movesUntilSpecial;
// private static int spawnCount;
// private boolean usedImmortality;
// private boolean useStunDartNext;
// private int specialMovesUse;
// private boolean specificSpecial;
//
// private static final List<Integer> IDS = Arrays.asList(16697, 16698, 16699);
// private static final int[] TRANSFORMATION_GRAPHICS = {3750, 3749, 3751};
//
// public enum CombatState {
// MELEE, MAGIC, RANGED;
//
// public final int getNPCIdForState() {
// return IDS.get(ordinal());
// }
//
// public final int getTransformationGraphicId() {
// return TRANSFORMATION_GRAPHICS[ordinal()];
// }
//
// public static final CombatState getNextState(CombatState state) {
// return state == RANGED ? MELEE : values()[state.ordinal()+1];
// }
// }
//
// public void transform() {
// getTemporaryAttributtes().put("last_transformed", Utils.currentTimeMillis());
// state = CombatState.getNextState(state);
// transformIntoNPC(state.getNPCIdForState());
// setNextGraphics(new Graphics(state.getTransformationGraphicId()));
// }
//
// public boolean canTransform() {
// final Object last_transformed =
// getTemporaryAttributtes().get("last_transformed");
// if (last_transformed != null && last_transformed instanceof Long)
// return (long) last_transformed + 60000 < Utils.currentTimeMillis();
// return true;
// }
//
// private static boolean siphon;
// private static int auraTicks;
// private static boolean attackedSomeone;
//
// public static void useAura() {
// siphon = Utils.random(2) == 1;
// attackedSomeone = false;
// auraTicks = 4;
// }
//
//
// public boolean canBury() {
// final Object last_buried = getTemporaryAttributtes().get("last_buried");
// if (last_buried != null && last_buried instanceof Long)
// return (long) last_buried + 30000 < Utils.currentTimeMillis();
// return aboveGround;
// }
//
// //TODO make kalphite minion class l8r
// //TODO add instance support
// //TODO in process method check for players under and add to passive damage
// list for stomp ability
// //public ArrayList<KalphiteMinion> minions = new
// ArrayList<KalphiteMinion>();//this will be for minion support & instance
// support
// //private boolean spawnedMinions;
//
// @Override
// public void handleIngoingHit(Hit hit) {
// if (!aboveGround) {
// return;
// }
// if (auraTicks > 0 && siphon)
// hit.setHealHit();
// super.handleIngoingHit(hit);
// }
//
// @Override
// public void sendDeath(Entity source) {
// final NPCCombatDefinitions defs = getCombatDefinitions();
// resetWalkSteps();
// getCombat().removeTarget();
// setNextAnimation(null);
// transformIntoNPC(IDS.get(0));//melee
// WorldTasksManager.schedule(new WorldTask() {
// int loop;
//
// @Override
// public void run() {
// if (loop == 0) {
// setNextAnimation(new Animation(defs.getDeathEmote()));
// } else if (loop >= defs.getDeathDelay()) {
// drop();
// reset();
// spawnCount = 0;
// setLocation(getRespawnTile());
// finish();
// setRespawnTask();
// stop();
// }
// loop++;
// }
// }, 0, 1);
// }
//
// @Override
// public ArrayList<Entity> getPossibleTargets() {
// final ArrayList<Entity> possibleTargets = new ArrayList<Entity>();
// for (final int regionId : getMapRegionsIds()) {
// final List<Integer> playerIndexes =
// World.getRegion(regionId).getPlayerIndexes();
// if (playerIndexes != null) {
// for (final int playerIndex : playerIndexes) {
// final Player player = World.getPlayers().get(playerIndex);
// if (player == null
// || player.isDead()
// || player.hasFinished()
// || !player.isRunning()
// || !player.withinDistance(this, 64))
// continue;
// if (!possibleTargets.contains(player))
// possibleTargets.add(player);
// //if(getHPPercentage() < 25 && spawnCount == 0)
// // battleCry();
// /*if(auraTicks == 1) {
// auraTicks = 0;
// if(!attackedSomeone)
// heal(24000);*/
// if (auraTicks > 0){
// auraTicks--;
// setNextGraphics(new Graphics(siphon ? 3737: 3736));
// }
// }
// }
// final List<Integer> npcIndexes = World.getRegion(regionId).getNPCsIndexes();
// if (npcIndexes != null) {
// for (final int npcIndex : npcIndexes) {
// final NPC npc = World.getNPCs().get(npcIndex);
// if (npc == null || npc == this || npc instanceof Pet || npc.hasFinished() ||
// npc.isDead() || !npc.withinDistance(this, 64)
// || !npc.getDefinitions().hasAttackOption())
// continue;
// if (!possibleTargets.contains(npc))
// possibleTargets.add(npc);
// }
// }
// }
// return possibleTargets;
// }
//
// public int getHPPercentage() {
// return getHitpoints() * 100 / getMaxHitpoints();
// }
//
// public WorldTile getSpawnTile() {
// switch(Utils.random(5)) {
// case 0:
// return new WorldTile(2965, 1760, 0);
// case 1:
// return new WorldTile(2968, 1756, 0);
// case 2:
// return new WorldTile(2965, 1767, 0);
// case 3:
// return new WorldTile(2977, 1768, 0);
// case 4:
// return new WorldTile(2981, 1752, 0);
// default:
// return new WorldTile(2987, 1760, 0);
// }
// }
//
// public void battleCry() {
// setNextAnimation(new Animation(19462));
// spawnCount++;
// for(Entity t : getPossibleTargets()) { //battle cry
// if(Utils.isOnRange(this, t, 3)) { //if nearby when he uses it u get dmg
// t.applyHit(new Hit(this, Utils.random(1280)+1, HitLook.REGULAR_DAMAGE));
// t.setFreezeDelay(5); //stuns fo 3 sec
// }
// }
//
// WorldTasksManager.schedule(new WorldTask() {
//
// @Override
// public void run() {
// for(int i = 0; i < 6; i++) {
// NPC minion = World.spawnNPC(16706, getSpawnTile(), +1, true, true);
// minion.setForceTargetDistance(64);
// minion.setNextAnimation(new Animation(19492));
// minion.setNextGraphics(new Graphics(3748));
// for(Entity target : minion.getPossibleTargets())
// if(Utils.colides(minion, target))
// target.applyHit(new Hit(minion, Utils.random(400, 401),
// HitLook.MELEE_DAMAGE));
// }
// }
//
// }, 5);
// }
//
// }
