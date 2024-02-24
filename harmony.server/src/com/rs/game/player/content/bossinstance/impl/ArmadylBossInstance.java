package com.rs.game.player.content.bossinstance.impl;

import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.npc.godwars.armadyl.KreeArra;
import com.rs.game.player.Player;
import com.rs.game.player.content.bossinstance.BossInstance;

public class ArmadylBossInstance extends BossInstance {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 710030224169548661L;
	private static final int HEIGHT = 0;

	public ArmadylBossInstance(Player owner) {
		super(new WorldTile(2816, 5294, HEIGHT), new WorldTile(2837, 5308, HEIGHT), HEIGHT, 16, 16, owner);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void start() {
		NPC npc = new KreeArra(6222, getWorldTile(10, 11), -1, true, false);
		for (NPC n : World.getNPCs()) {
			if (n.getRegionId() == npc.getRegionId() && n.getId() != npc.getId()) {
				System.out.println("Instance Manager: Wrong npc was detected inside instance & was removed!");
				n.finish();
			}
		}
		npc.setForceMultiArea(true);
		NPC minion = new NPC(6223, getWorldTile(11, 11), -1, true, false);
		NPC minion2 = new NPC(6225, getWorldTile(10, 10), -1, true, false);
		NPC minion3 = new NPC(6227, getWorldTile(10, 10), -1, true, false);
		minion.setForceMultiArea(true);
		minion2.setForceMultiArea(true);
		minion3.setForceMultiArea(true);
	}

	@Override
	public void uponEnter(Player player) {
		WorldTile destination = getWorldTile(10, 10);
		
		player.setNextWorldTile(destination);
	}

	@Override
	public void uponExit(Player player) {
		  player.setForceMultiArea(false);
	}

}