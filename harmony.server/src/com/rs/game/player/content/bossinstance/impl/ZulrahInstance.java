package com.rs.game.player.content.bossinstance.impl;

import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.npc.zulrah.Zulrah;
import com.rs.game.player.Player;
import com.rs.game.player.content.bossinstance.BossInstance;

public class ZulrahInstance extends BossInstance {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 710030224169548661L;
	private static final int HEIGHT = 0;

	public ZulrahInstance(Player owner) {
		super(new WorldTile(2241, 3041, HEIGHT), new WorldTile(2301, 3113, HEIGHT), HEIGHT, 11, 11, owner);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void start() {
		NPC zul = new Zulrah(16387, getWorldTile(26, 32), -1, true, false);
		zul.setForceMultiArea(true);
		for (NPC n : World.getNPCs()) {
			if (n.getRegionId() == zul.getRegionId() && n.getId() != zul.getId()) {
				System.out.println("Instance Manager: Wrong npc was detected inside instance & was removed!");
				n.finish();
			}
		}
	}

	@Override
	public void uponEnter(Player player) {
		//System.out.println("11233");
		player.setNextWorldTile(getWorldTile(32, 32));
		player.setForceMultiArea(true);
		
		//WorldTile destination = getWorldTile(11, 11);
		//player.setNextWorldTile(destination);
		// player.getCombatDefinitions().resetSpells(true);
	}

	@Override
	public void uponExit(Player player) {
		  player.setForceMultiArea(false);
		 // BossInstanceManager.SINGLETON.remove(this); // End
	}

}