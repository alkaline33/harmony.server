package com.rs.game.player;

import java.util.TimerTask;
import com.rs.cores.CoresManager;

public class RandomSpins {
 
	private transient Player player;
	
	public RandomSpins(Player player) {
		this.player = player;
	}
	
	public void startTimer() {
	CoresManager.fastExecutor.schedule(new TimerTask() {
			int timer = 10800;
			@Override
			public void run() {
				if (player.isDonator()) {
				if (timer == 1) {
					player.spins += 1;
					timer = 5400;
					player.getPackets().sendGameMessage("<col=008000>You have received 1 Spin for playing for 90 minutes!");
					player.refreshSqueal();
				}
				if (timer >= 0) {
					timer--;
				}
				}
				
			}
		}, 0L, 1000L);
	}
}
