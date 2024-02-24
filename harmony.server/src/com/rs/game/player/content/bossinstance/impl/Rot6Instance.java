package com.rs.game.player.content.bossinstance.impl;

import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.npc.others.ROT6;
import com.rs.game.player.Player;
import com.rs.game.player.content.bossinstance.BossInstance;

public class Rot6Instance extends BossInstance {

	/**
	 * 
	 */
	private static final long serialVersionUID = 710030224169548661L;
	private static final int HEIGHT = 0;

	public Rot6Instance(Player owner) {
		super(new WorldTile(3604, 3330, HEIGHT), new WorldTile(3628, 3353, HEIGHT), HEIGHT, 11, 11, owner);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void start() {
		NPC rot6 = new ROT6(30016, getWorldTile(18, 11), -1, true, false);
		for (NPC n : World.getNPCs()) {
			if (n.getRegionId() == rot6.getRegionId() && n.getId() != rot6.getId()) {
				System.out.println("Instance Manager: Wrong npc was detected inside instance & was removed!");
				n.finish();
			}
		}

	}

	@Override
	public void uponEnter(Player player) {
		// System.out.println("11233");
		player.setNextWorldTile(getWorldTile(16, 11));

		// WorldTile destination = getWorldTile(11, 11);
		// player.setNextWorldTile(destination);
		// player.getCombatDefinitions().resetSpells(true);
	}

	@Override
	public void uponExit(Player player) {
		// BossInstanceManager.SINGLETON.remove(this); // End
	}

}