// package com.rs.game.player.content.instances;
//
// import com.rs.cores.CoresManager;
// import com.rs.game.RegionBuilder;
// import com.rs.game.WorldTile;
// import com.rs.game.player.Player;
// import com.rs.game.player.content.FadingScreen;
// import com.rs.game.tasks.WorldTask;
// import com.rs.game.tasks.WorldTasksManager;
// import com.rs.game.player.controlers.instances.CorporealBeastC;
/// **
// *
// * @author Mr_Joopz
// *
// */
// public class CorporealBeast {
//
// public static int[] boundChunk;
//
// public static boolean endtime;
//
// public enum Stages {
// LOADING, RUNNING, DESTROYING;
// }
//
// public CorporealBeast (final Player player) {
// setOwner(true);
// loadMap(player);
// }
//
// public void loadMap(final Player player) {
// final long time = FadingScreen.fade(player);
// player.lock();
// CoresManager.slowExecutor.execute(new Runnable() {
// @Override
// public void run() {
// boundChunk = RegionBuilder.findEmptyChunkBound(8, 8);
// RegionBuilder.copyAllPlanesMap(279, 583, boundChunk[0], boundChunk[1], 40);
// player.setForceMultiArea(true);
// addToInstance(player);
//
// }
// });
// FadingScreen.unfade(player, time, new Runnable() {
// @Override
// public void run() {
// CorporealBeastC.addNpcToInstance(player, null, null);
// player.getControlerManager().startControler("CorporealBeastC");
// player.unlock();
// }
// });
// WorldTasksManager.schedule(new WorldTask() {
// @Override
// public void run() {
// player.unlock();
// }
// }, 1);
// }
//
// public static void joinInstance(final Player player, final Player target) {
// final long time = FadingScreen.fade(player);
// WorldTasksManager.schedule(new WorldTask() {
//
// @Override
// public void run() {
// try {
// player.getTemporaryAttributtes().put("target_instance", Boolean.FALSE);
// FadingScreen.unfade(player, time, new Runnable() {
// @Override
// public void run() {
// player.setNextWorldTile(new WorldTile(target.getLastWorldTile()));
// target.getPackets().sendGameMessage(player.getDisplayName() + " has joined
// your Instance.");
// player.setForceMultiArea(true);
// player.unlock();
// }
// });
//
// } catch (Throwable e) {
// e.printStackTrace();
// }
// }
// });
// }
//
// public static void DestroyInstance(final boolean logout, final Player player)
// {
// player.setNextWorldTile(new WorldTile(2593, 5593, 0));
// RegionBuilder.destroyMap(boundChunk[0], boundChunk[1], 40, 40);
// player.getControlerManager().forceStop();
// player.instanceseconds = 0;
// player.instanceminutes = 0;
// player.instancenoone = false;
// player.instanceclanonly = false;
// player.instancerespawnseconds = 0;
// player.setForceMultiArea(false);
// player.npcspawninstanceseconds = 0;
// player.sendMessage("You will have to relog before using another instance.");
// }
//
// public Object getOwner(final Player player) {
// return player.getTemporaryAttributtes().get("owner_of_instance");
// }
//
// public void removeOwner(Player owner) {
// owner.getTemporaryAttributtes().get("owner_of_instance".equals(null));
// }
//
// private static void addToInstance(final Player player) {
// player.setNextFaceWorldTile(new WorldTile(player.getLastWorldTile()));
// player.setNextWorldTile(new WorldTile(getBaseX() + 41, getBaseY() + 31, 0));
// }
//
// public static void leaveInstance(Player player) {
// player.getControlerManager().forceStop();
// player.getPackets().sendGameMessage("You leave the Instance.");
// DestroyInstance(false, player);
// endtime = true;
// }
//
// public static int getBaseX() {
// return boundChunk[0] << 3;
// }
//
// public static int getBaseY() {
// return boundChunk[1] << 3;
// }
//
// public static boolean setOwner(boolean bool) {
// return bool;
// }
// }