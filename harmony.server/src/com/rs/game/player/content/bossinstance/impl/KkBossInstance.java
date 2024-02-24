package com.rs.game.player.content.bossinstance.impl;

import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.player.Player;
import com.rs.game.player.content.bossinstance.BossInstance;

public class KkBossInstance extends BossInstance {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 710030224169548661L;
	private static final int HEIGHT = 0;

	public KkBossInstance(Player owner) {
		super(new WorldTile(3347, 2780, HEIGHT), new WorldTile(3366, 2784, HEIGHT), HEIGHT, 4, 4, owner);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void start() {
	//	World.spawnNPC(6260, getWorldTile(10, 11), -1, true, false);
		NPC npc = new NPC(29993, getWorldTile(10, 11), -1, true, false);
		for (NPC n : World.getNPCs()) {
			if (n.getRegionId() == npc.getRegionId() && n.getId() != npc.getId()) {
				System.out.println("Instance Manager: Wrong npc was detected inside instance & was removed!");
				n.finish();
			}
		}
		npc.setForceMultiArea(true);
	}

	@Override
	public void uponEnter(Player player) {
		WorldTile destination = getWorldTile(10, 10);
		
		player.setNextWorldTile(destination);
		// player.getCombatDefinitions().resetSpells(true);
	}

	@Override
	public void uponExit(Player player) {
		  player.setForceMultiArea(false);
	}

}