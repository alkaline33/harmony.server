// package com.rs.game.world.timers;
//
// import com.rs.Settings;
// import com.rs.game.World;
// import com.rs.game.WorldTile;
// import com.rs.game.player.Player;
// import com.rs.game.tasks.WorldTask;
// import com.rs.game.tasks.WorldTasksManager;
// import com.rs.utils.Colors;
//
// public class TempleofLight {
//
// private static SecondsTimer Timer = new SecondsTimer();
//
// public static void addTime(Player p, int seconds) {
// Timer.start(seconds);
// startTime(p);
// }
//
// public static void startTime(final Player p) {
// WorldTasksManager.schedule(new WorldTask() {
// @Override
// public void run() {
// if (p.isDead() || p.hasFinished()) {
// stop();
// }
// if (Timer.secondsRemaining() == 60)
// p.sendMessage(Colors.cyan+"1 minute remaining!");
// if (Timer.secondsRemaining() < 290 && !World.TempleofLight(p)) {
// p.setNextWorldTile(new WorldTile(Settings.RESPAWN_PLAYER_LOCATION));
// p.sendMessage(Colors.cyan + "It seems you escaped the area, please tell us
// how you did this!");
// stop();
// }
// if (Timer.secondsRemaining() == 0) {
// p.setNextWorldTile(new WorldTile(Settings.RESPAWN_PLAYER_LOCATION));
// p.sendMessage(Colors.cyan+"Your time in the Temple has finished!");
// stop();
// }
// }
// }, 0, 1);
// }
//
// public static String getTime(Player player) {
// return Timer.secondsRemaining() > 60 ? Timer.secondsRemaining() / 60 + "m" :
// Timer.secondsRemaining() + "s";
// }
// }