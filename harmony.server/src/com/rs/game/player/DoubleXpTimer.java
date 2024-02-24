// package com.rs.game.player;
//
// import java.util.TimerTask;
// import com.rs.cores.CoresManager;
//
// public class DoubleXpTimer {
//
// private transient Player player;
//
// public DoubleXpTimer(Player player) {
// this.player = player;
// }
// public void startTimer() {
//
//
// CoresManager.fastExecutor.schedule(new TimerTask() {
//
// int timer = 60;
// @Override
// public void run() {
// if (timer == 1) {
// if (player.d60mxp1 == 0) {
// timer--;
// return;
// }
// if (!player.isWeekend()) {
// player.d60mxp1 -= 1;
// }
// timer = 60;
// }
// if (timer > 0) {
// timer--;
// }
// }
// }, 0L, 1000L);
// }
// }