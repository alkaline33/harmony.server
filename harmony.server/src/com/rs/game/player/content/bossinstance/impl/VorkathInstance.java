package com.rs.game.player.content.bossinstance.impl;

import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.npc.others.vorkath.Vorkath;
import com.rs.game.player.Player;
import com.rs.game.player.content.bossinstance.BossInstance;

public class VorkathInstance extends BossInstance {

	/**
	 * 
	 */
	private static final long serialVersionUID = 710030224169548661L;
	private static final int HEIGHT = 0;

	/**
	 * worldtile (west to east / south to north.)
	 * 
	 * @param owner
	 */

	public VorkathInstance(Player owner) {
		super(new WorldTile(2240, 4032, HEIGHT), new WorldTile(2303, 4095, HEIGHT), HEIGHT, 11, 11, owner);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void start() {
		NPC vorkath = new Vorkath(39061, getWorldTile(29, 28), -1, true, false);
		for (NPC n : World.getNPCs()) {
			if (n.getRegionId() == vorkath.getRegionId() && n.getId() != vorkath.getId()) {
				System.out.println("Instance Manager: Wrong npc was detected inside instance & was removed!");
				n.finish();
			}
		}
		vorkath.setForceMultiArea(true);

	}

	@Override
	public void uponEnter(Player player) {
		player.setNextWorldTile(getWorldTile(32, 22));
		player.setForceMultiArea(true);
	}

	@Override
	public void uponExit(Player player) {
		player.setForceMultiArea(false);
	}

}