package com.rs.game.player.content.bossinstance.impl;

import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.npc.godwars.bandos.GeneralGraardor;
import com.rs.game.player.Player;
import com.rs.game.player.content.bossinstance.BossInstance;

public class BandosBossInstance extends BossInstance {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 710030224169548661L;
	private static final int HEIGHT = 0;

	public BandosBossInstance(Player owner) {
		super(new WorldTile(2862, 5350, HEIGHT), new WorldTile(2876, 5369, HEIGHT), HEIGHT, 16, 16, owner);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void start() {
		NPC graador = new GeneralGraardor(6260, getWorldTile(10, 11), -1, true, false);
		for (NPC n : World.getNPCs()) {
			if (n.getRegionId() == graador.getRegionId() && n.getId() != graador.getId()) {
				System.out.println("Instance Manager: Wrong npc was detected inside instance & was removed!");
				n.finish();
			}
		}
		graador.setForceMultiArea(true);
		NPC minion = new NPC(6261, getWorldTile(11, 11), -1, true, false);
		NPC minion2 = new NPC(6263, getWorldTile(10, 10), -1, true, false);
		NPC minion3 = new NPC(6265, getWorldTile(10, 10), -1, true, false);
		minion.setForceMultiArea(true);
		minion2.setForceMultiArea(true);
		minion3.setForceMultiArea(true);
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