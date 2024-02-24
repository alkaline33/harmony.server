package com.rs.game.player.content.bossinstance.impl;

import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.npc.corp.CorporealBeast;
import com.rs.game.npc.others.The3Amigos;
import com.rs.game.player.Player;
import com.rs.game.player.content.bossinstance.BossInstance;

public class AmigoBossInstance extends BossInstance {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 710030224169548661L;
	private static final int HEIGHT = 0;

	public AmigoBossInstance(Player owner) {
		super(new WorldTile(571, 4539, HEIGHT), new WorldTile(596, 4564, HEIGHT), HEIGHT, 16, 16, owner);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void start() {
	//	World.spawnNPC(6260, getWorldTile(10, 11), -1, true, false);
		NPC npc = new The3Amigos(11983, getWorldTile(10, 11), -1, true, false);
		NPC npc1 = new The3Amigos(12041, getWorldTile(11, 11), -1, true, false);
		NPC npc2 = new The3Amigos(12088, getWorldTile(10, 12), -1, true, false);
		for (NPC n : World.getNPCs()) {
			if (n.getRegionId() == npc.getRegionId() && n.getId() != npc.getId() && n.getId() != npc1.getId() && n.getId() != npc2.getId()) {
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