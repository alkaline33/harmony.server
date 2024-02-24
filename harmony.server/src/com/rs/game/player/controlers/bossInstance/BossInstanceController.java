/*package com.rs.game.player.controlers.bossInstance;

import com.rs.game.Animation;
import com.rs.game.WorldTile;
import com.rs.game.map.bossInstance.BossInstance;
import com.rs.game.map.bossInstance.BossInstanceHandler;
import com.rs.game.map.bossInstance.BossInstanceHandler.Boss;
import com.rs.game.player.MusicsManager;
import com.rs.game.player.controlers.Controler;
import com.rs.game.player.controlers.events.DeathEvent;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

public class BossInstanceController extends Controler {

	private BossInstance instance;

	@Override
	public void start() {
		instance = (BossInstance) getArguments()[0]; // cant save this as not
														// serializable would
														// null :L
		getArguments()[0] = instance.getBoss(); // gotta save reference for
												// which instance im at
	}

	@Override
	public void process() {
		if (instance != null && Utils.currentWorldCycle() % 120 == 0)
			instance.playMusic(player);
	}

	@Override
	public boolean login() {
		// shouldnt happen but better be safe
		if (getArguments() == null || getArguments().length == 0)
			return true;
		Boss boss = (Boss) getArguments()[0];
		instance = BossInstanceHandler.joinInstance(player, boss, "", true);
		return instance == null; // if failed. remove
	}

	public BossInstance getInstance() {
		return instance;
	}

	@Override
	public boolean sendDeath() {
		player.lock(8);
		player.stopAll();
		WorldTasksManager.schedule(new WorldTask() {
			int loop;

			@Override
			public void run() {
				if (loop == 0) {
					player.setNextAnimation(new Animation(836));
				} else if (loop == 1) {
					player.getPackets().sendGameMessage("Oh dear, you have died.");
				} else if (loop == 3) {
					instance.leaveInstance(player, BossInstance.DIED);
					removeControler();
					player.reset();
					player.setNextWorldTile(instance.getBoss().getOutsideTile());

				} else if (loop == 4) {
					// player.getMusicsManager().playMusicEffect(MusicsManager.DEATH_MUSIC_EFFECT);
					stop();
				}
				loop++;
			}
		}, 0, 1);
		return false;
	}

	@Override
	public boolean logout() {
		instance.leaveInstance(player, BossInstance.LOGGED_OUT);
		if (!instance.isPublic())
			removeControler();
		return false; // false. it will remove script normaly if needed
	}

	// controller stopped for some reason or in purpose
	@Override
	public void forceClose() {
		if (instance != null)
			instance.leaveInstance(player, BossInstance.EXITED);
	}

	@Override
	public void magicTeleported(int type) {
		instance.leaveInstance(player, BossInstance.TELEPORTED);
		removeControler();
	}
}*/