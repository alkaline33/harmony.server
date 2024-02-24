// package com.rs.game.player.content.bossinstance.impl;
//
// import com.rs.game.WorldTile;
// import com.rs.game.npc.NPC;
// import com.rs.game.player.Player;
// import com.rs.game.player.content.bossinstance.BossInstance;
//
// public class CerberusInstance extends BossInstance {
//
// /**
// *
// */
// private static final long serialVersionUID = 710030224169548661L;
// private static final int HEIGHT = 0;
//
// public CerberusInstance(Player owner) {
// super(new WorldTile(1216, 1216, HEIGHT), new WorldTile(1279, 1279, HEIGHT),
// HEIGHT, 11, 11, owner);
// // TODO Auto-generated constructor stub
// }
//
// @Override
// public void start() {
// // NPC zul = new Cerberus(16387, getWorldTile(26, 32), -1, true, false);
// // zul.setForceMultiArea(true);
// NPC npc = new NPC(36862, getWorldTile(22, 33), -1, true, false);
//
// }
//
// @Override
// public void uponEnter(Player player) {
// // System.out.println("11233");
// player.setNextWorldTile(getWorldTile(32, 32));
// player.setForceMultiArea(true);
//
// // WorldTile destination = getWorldTile(11, 11);
// // player.setNextWorldTile(destination);
// // player.getCombatDefinitions().resetSpells(true);
// }
//
// @Override
// public void uponExit(Player player) {
// player.setForceMultiArea(false);
// // BossInstanceManager.SINGLETON.remove(this); // End
// }
//
// }