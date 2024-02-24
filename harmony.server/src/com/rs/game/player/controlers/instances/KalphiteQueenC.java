// package com.rs.game.player.controlers.instances;
//
// import java.util.List;
//
//
// import java.util.concurrent.TimeUnit;
//
// import com.rs.cores.CoresManager;
// import com.rs.game.World;
// import com.rs.game.WorldTile;
// import com.rs.game.npc.NPC;
// import com.rs.game.player.Player;
// import com.rs.game.player.content.instances.KalphiteQueen;
// import com.rs.game.player.controlers.Controler;
// import com.rs.utils.Logger;
//
//
//
//
/// **
// *
// * @author Mr_Joopz
// *
// */
//
// public class KalphiteQueenC extends Controler {
//
// @Override
// public void start() {
// setArguments(new Object[] { 0, 0, 0, 0, 0, 0 });
// sendInterfaces();
// player.instanceminutes = 0;
// player.instanceseconds = 0;
// player.instancekilltime = 0;
// player.npcspawninstanceseconds = 0;
// }
//
// @Override
// public boolean logout() {
// KalphiteQueen.leaveInstance(player);
// remove();
// removeControler();
// return false; // so doesnt remove script
// }
//
// public static void addNpcToInstance(final Player player, final KalphiteQueen
// kalphitequeen, final NPC npc) {
// CoresManager.slowExecutor.scheduleWithFixedDelay(new Runnable() {
//
// @Override
// public void run() {
// try {
// if (kalphitequeen.endtime == true) {
// player.instancerespawnseconds = 0;
// player.instanceminutes = 0;
// player.instanceseconds = 0;
// player.npcspawninstanceseconds = 0;
// player.instancenoone = false;
// player.instanceclanonly = false;
// kalphitequeen.endtime = false;
// player.allowedinstance = false;
// player.sendMessage("Your instance has ended!");
// return;
// }
// List<Integer> npcs = World.getRegion(player.getRegionId()).getNPCsIndexes();
// // players = World.getRegionId()).
// if(npcs == null || npcs.isEmpty()) {
// if (player.npcspawned == true) {
// player.sendMessage("<col=ff0000>Your last kill took
// "+player.instancekilltime+" seconds.");
// player.npcspawninstanceseconds = 0;
// }
// player.npcspawned = false;
// }
// if (player.instanceminutes >= 60) {
// KalphiteQueen.leaveInstance(player);
//
// }
// player.instanceseconds ++;
// player.npcspawninstanceseconds ++;
// player.instancekilltime ++;
// if (player.instanceseconds >= 60) {
// player.instanceseconds -= 60;
// player.instanceminutes += 1;
// }
// if (player.instancerespawnseconds == player.npcspawninstanceseconds &&
// player.npcspawned == false) {
// World.spawnNPC(1158, new WorldTile(KalphiteQueen.getBaseX() + 11,
// KalphiteQueen.getBaseY() + 21, 0),-1, true,
// true);
// player.npcspawned = true;
// player.instancekilltime = 0;
// }
// } catch (Throwable e) {
// Logger.handle(e);
// }
//
// }
// }, 1, 1, TimeUnit.SECONDS);
// }
//
// @Override
// public boolean sendDeath() {
// remove();
// removeControler();
// removeAssets();
// KalphiteQueen.leaveInstance(player);
// return true;
// }
//
// @Override
// public void magicTeleported(int type) {
// remove();
// removeControler();
// removeAssets();
// KalphiteQueen.leaveInstance(player);
// }
//
// @Override
// public void forceClose() {
// remove();
// removeControler();
// removeAssets();
// KalphiteQueen.leaveInstance(player);
// }
//
// public void removeAssets() {
// player.instancerespawnseconds = 0;
// player.instanceminutes = 0;
// player.instanceseconds = 0;
// player.npcspawninstanceseconds = 0;
// player.instancenoone = false;
// player.instanceclanonly = false;
// KalphiteQueen.endtime = false;
// player.allowedinstance = false;
// }
//
// public void remove() {
// player.getPackets().closeInterface(
// player.getInterfaceManager().hasRezizableScreen() ? 34 : 8);
// }
//
// }
