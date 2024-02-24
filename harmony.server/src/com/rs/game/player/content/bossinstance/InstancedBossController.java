package com.rs.game.player.content.bossinstance;

import com.rs.Settings;
import com.rs.game.Animation;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.player.content.bossinstance.impl.DungBossHigh;
import com.rs.game.player.content.bossinstance.impl.DungBossLow;
import com.rs.game.player.content.bossinstance.impl.DungBossMed;
import com.rs.game.player.controlers.Controler;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;

public class InstancedBossController extends Controler {

	public static final String IDENTIFIER = "instancedBoss";

	private transient BossInstance instance;

	public InstancedBossController() {
	}

	@Override
	public boolean processMagicTeleport(WorldTile toTile) {
		instance.remove(player);
		 player.setForceMultiArea(false);
		return false;
	}

	@Override
	public boolean processItemTeleport(WorldTile toTile) {
		instance.remove(player);
		  player.setForceMultiArea(false);
		return false;
	}

	@Override
	public boolean processObjectTeleport(WorldTile toTile) {
		instance.remove(player);
		  player.setForceMultiArea(false);
		return false;
	}

	@Override
	public boolean logout() {
		player.setLocation(Settings.RESPAWN_PLAYER_LOCATION);
		instance.remove(player);
		player.setForceMultiArea(false);
		return true;
	}
	
	@Override
	public boolean sendDeath() {
		player.lock(7);
		player.stopAll();
		WorldTasksManager.schedule(new WorldTask() {
			int loop;

			@Override
			public void run() {
				if (loop == 0) {
					player.setNextAnimation(new Animation(836));
				} else if (loop == 1) {
					player.getPackets().sendGameMessage("You have been defeated!");
				} else if (loop == 2) {
					player.setNextWorldTile(new WorldTile(Settings.RESPAWN_PLAYER_LOCATION));
				} else if (loop == 3) {
					if (instance instanceof DungBossMed || instance instanceof DungBossLow || instance instanceof DungBossHigh) {
						// nothing needed
					} else {
						if (player.isHCIronman()) {
							player.hcironman = false;
							World.sendWorldMessage("<col=ff0000>" + player.getDisplayName() + " has died as a Hardcore Ironman!", false);
						}
					}
					player.reset();
					instance.remove(player);
					player.setForceMultiArea(false);
					player.setNextAnimation(new Animation(-1));
				} else if (loop == 4) {
					player.getPackets().sendMusicEffect(90);
					stop();
				}
				loop++;
			}
		}, 0, 1);
		return false;
	}
	
	@Override
	public void start() {

		this.instance = (BossInstance) getArguments()[0]; // BossInstance;
	}

}