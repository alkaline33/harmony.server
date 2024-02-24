package com.rs.game.player;

import java.util.TimerTask;

import com.rs.Settings;
import com.rs.cores.CoresManager;

public class LoyaltyManager {
 
	private transient Player player;
	
	public LoyaltyManager(Player player) {
		this.player = player;
	}
	
	public void startTimer() {
	CoresManager.fastExecutor.schedule(new TimerTask() {
			int timer = 1800;
			//int value = 250;
			int value =  player.isSponsor() ? 1000 : player.isVIP() ? 850 : player.isLegendaryDonator() ? 750 : player.isUltimateDonator() ? 650 : player.isExtremeDonator() ? 550 : player.isSuperDonator() ? 450 : player.isDonator() ? 350 : 250;
			
			@Override
			public void run() {
				if (timer == 1) {
					if (Settings.EVENT_SPOTLIGHT.equalsIgnoreCase("Double loyalty points"))
						value = value * 2;
					if (!player.isAFK()) {
						player.setLoyaltyPoints(player.getLoyaltyPoints() + value);
					}

					timer = 1800;
					player.getPackets().sendGameMessage("<col=008000>You have received " + value + " loyalty points for playing for 30 minutes!");
					player.getPackets().sendGameMessage("<col=008000>You now have " + player.getLoyaltyPoints() + " Loyalty Points!");
				}
				if (timer > 0) {
					timer--;
				}
			}
		}, 0L, 1000L);
	}
}