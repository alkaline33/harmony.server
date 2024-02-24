package com.rs.game.player.content.bossinstance.impl;

import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.player.Player;
import com.rs.game.player.content.bossinstance.BossInstance;

public class DungBossMed extends BossInstance {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 710030224169548661L;
	private static final int HEIGHT = 0;

	public DungBossMed(Player owner) {
		super(new WorldTile(3502, 9830, HEIGHT), new WorldTile(3517, 9848, HEIGHT), HEIGHT, 3, 3, owner);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void start() {
		NPC npc = new NPC(9766, getWorldTile(9, 8), -1, true, false);
		for (NPC n : World.getNPCs()) {
			if (n.getRegionId() == npc.getRegionId() && n.getId() != npc.getId()) {
				System.out.println("Instance Manager: Wrong npc was detected inside instance & was removed!");
				n.finish();
			}
		}
	}

	@Override
	public void uponEnter(Player player) {
		WorldTile destination = getWorldTile(9, 10);
		
		player.setNextWorldTile(destination);
		// player.getCombatDefinitions().resetSpells(true);
	}

	@Override
	public void uponExit(Player player) {
	}

}