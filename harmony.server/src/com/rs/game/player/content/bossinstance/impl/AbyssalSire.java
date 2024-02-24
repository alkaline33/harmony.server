package com.rs.game.player.content.bossinstance.impl;

import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.npc.others.sire.Sire;
import com.rs.game.player.Player;
import com.rs.game.player.content.bossinstance.BossInstance;

public class AbyssalSire extends BossInstance {

	/**
	 * 
	 */
	private static final long serialVersionUID = 710030224169548661L;
	private static final int HEIGHT = 0;

	public AbyssalSire(Player owner) {
		super(new WorldTile(2962, 4805, HEIGHT), new WorldTile(2998, 4863, HEIGHT), HEIGHT, 11, 11, owner);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void start() {
		NPC sire = new Sire(36886, getWorldTile(17, 54), -1, true, false);
		sire.setForceMultiArea(true);

	}

	@Override
	public void uponEnter(Player player) {
		player.setNextWorldTile(getWorldTile(20, 45));
		player.setForceMultiArea(true);
	}

	@Override
	public void uponExit(Player player) {
		player.setForceMultiArea(false);
	}

}