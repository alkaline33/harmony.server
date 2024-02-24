package com.rs.game.world.timers;

import com.rs.game.player.Player;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;

public class AntifireTimer {

	private static SecondsTimer Timer = new SecondsTimer();

	public static void addTime(Player p, int seconds) {
		if (p.getPotTimers() == true) {
		Timer.start(seconds);
		startTime(p);
		}
	}

	public static void startTime(final Player p) {
		WorldTasksManager.schedule(new WorldTask() {
			@Override
			public void run() {
				if (p.isDead()) {
					p.getPackets().sendHideIComponent(3000, 2, true);
					p.getPackets().sendIComponentText(3000, 7, "");
					stop();
				}
				if (Timer.secondsRemaining() > 0)
					p.getPackets().sendIComponentText(3000, 7, "" + AntifireTimer.getTime(p) + "");
				p.getPackets().sendHideIComponent(3000, 2, false);
				if (Timer.secondsRemaining() == 0) {
					p.getPackets().sendHideIComponent(3000, 2, true);
					p.getPackets().sendIComponentText(3000, 7, "");
					stop();
				}
			}
		}, 0, 1);
	}

	public static String getTime(Player player) {
		return Timer.secondsRemaining() > 60 ? Timer.secondsRemaining() / 60 + "m" : Timer.secondsRemaining() + "s";
	}
}