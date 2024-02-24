package com.rs.game.world.timers;

import com.rs.game.player.Player;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;

public class OverloadedTimer {

	private static SecondsTimer overloadTime = new SecondsTimer();

	public static void addTime(Player p) {
		if (p.getPotTimers() == true) {
		if (p.potionperk != true) {
			overloadTime.start(600);
		} else {
			overloadTime.start(1200);
		}
		startTime(p);
		}
	}

	public static void startTime(final Player p) {
		WorldTasksManager.schedule(new WorldTask() {
			@Override
			public void run() {
				if (p.isDead()) {
					p.getPackets().sendHideIComponent(3000, 0, true);
					p.getPackets().sendIComponentText(3000, 5, "");
					stop();
				}
				if (overloadTime.secondsRemaining() > 0)
					p.getPackets().sendIComponentText(3000, 5, "" + OverloadedTimer.getTime(p) + "");
				p.getPackets().sendHideIComponent(3000, 0, false);
				if (overloadTime.secondsRemaining() == 0) {
					p.getPackets().sendHideIComponent(3000, 0, true);
					p.getPackets().sendIComponentText(3000, 5, "");
					stop();
				}
			}
		}, 0, 1);
	}

	public static String getTime(Player player) {
		return overloadTime.secondsRemaining() > 60 ? overloadTime.secondsRemaining() / 60 + "m" : overloadTime.secondsRemaining() + "s";
	}
}