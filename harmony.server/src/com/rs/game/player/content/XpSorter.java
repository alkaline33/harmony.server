package com.rs.game.player.content;

import java.util.concurrent.TimeUnit;

import com.rs.Settings;
import com.rs.cores.CoresManager;
import com.rs.utils.Logger;

public class XpSorter {
	
	public static void UpdateServerXpSort() {
		CoresManager.slowExecutor.scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {
				try {
					//Settings.serverxp += 1000000000;
					if (Settings.serverxp >= 10) {
						Settings.serverxp -= 10;
						Settings.serverxp10 ++;
					}
					if (Settings.serverxp10 >= 10) {
						Settings.serverxp10 -= 10;
						Settings.serverxp100 ++;
					}
					if (Settings.serverxp100 >= 10) {
						Settings.serverxp100 -= 10;
						Settings.serverxp1k ++;
					}
					if (Settings.serverxp1k >= 10) {
						Settings.serverxp1k -= 10;
						Settings.serverxp10k ++;
					}
					if (Settings.serverxp10k >= 10) {
						Settings.serverxp10k -= 10;
						Settings.serverxp100k ++;
					}
					if (Settings.serverxp100k >= 10) {
						Settings.serverxp100k -= 10;
						Settings.serverxp1m ++;
					}
					if (Settings.serverxp1m >= 10) {
						Settings.serverxp1m -= 10;
						Settings.serverxp10m ++;
					}
					if (Settings.serverxp10m >= 10) {
						Settings.serverxp10m -= 10;
						Settings.serverxp100m ++;
					}
					if (Settings.serverxp100m >= 10) {
						Settings.serverxp100m -= 10;
						Settings.serverxp1b ++;
					}
			
				} catch (Throwable e) {
					Logger.handle(e);
				}

			}
		}, 1, 1, TimeUnit.NANOSECONDS);
	}

}
