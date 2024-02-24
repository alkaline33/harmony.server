package com.rs.utils;

import java.io.File;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;

import com.rs.game.player.Player;

public final class CalamityBestWave implements Serializable {

	private static final long serialVersionUID = 5403480618483552509L;

	private String username;
	private int bestwave;

	private static CalamityBestWave[] waves;

	private static final String PATH = "data/serfiles/CalamityTopWave.ser";

	public CalamityBestWave(Player player) {
		this.username = player.getUsername();
		this.bestwave = player.calamitybestwave;
	}

	public static void init() {
		File file = new File(PATH);
		if (file.exists())
			try {
				waves = (CalamityBestWave[]) SerializableFilesManager
						.loadSerializedFile(file);
				return;
			} catch (Throwable e) {
				Logger.handle(e);
			}
		waves = new CalamityBestWave[300];
	}

	public static final void save() {
		try {
			SerializableFilesManager.storeSerializableClass(waves, new File(
					PATH));
		} catch (Throwable e) {
			Logger.handle(e);
		}
	}

	public static void showRanks(Player player) {
		for (int i = 10; i < 310; i++)
			player.getPackets().sendIComponentText(275, i, "");
		for (int i = 0; i < 300; i++) {
			if (waves[i] == null)
				break;
			String text;
			if (i >= 0 && i <= 2)
				text = "<col=0066ff>";
			else if (i <= 9)
				text = "<col=ff0000>";
			else if (i <= 49)
				text = "<col=38610B>";
			else
				text = "<col=000000>";
			player.getPackets().sendIComponentText(275,i + 10,text
									+ "Top "
									+ (i + 1)
									+ " - "
									+ Utils.formatPlayerNameForDisplay(waves[i].username)
									+ " - Highest Wave: " + waves[i].bestwave);
		}
		player.getPackets().sendIComponentText(275, 1,
				"The Calamity Highest Wave's Achieved");
		player.getInterfaceManager().sendInterface(275);
	}

	public static void sort() {
		Arrays.sort(waves, new Comparator<CalamityBestWave>() {
			@Override
			public int compare(CalamityBestWave arg0, CalamityBestWave arg1) {
				if (arg0 == null)
					return 1;
				if (arg1 == null)
					return -1;
				if (arg0.bestwave < arg1.bestwave)
					return 1;
				else if (arg0.bestwave > arg1.bestwave)
					return -1;
				else
					return 0;
			}

		});
	}

	public static void checkRank(Player player) {
		int bestwave = player.calamitybestwave;
		for (int i = 0; i < waves.length; i++) {
			CalamityBestWave rank = waves[i];
			if (rank == null)
				break;
			if (rank.username.equalsIgnoreCase(player.getUsername())) {
				waves[i] = new CalamityBestWave(player);
				sort();
				return;
			}
		}
		for (int i = 0; i < waves.length; i++) {
			CalamityBestWave rank = waves[i];
			if (rank == null) {
				waves[i] = new CalamityBestWave(player);
				sort();
				return;
			}
		}
		for (int i = 0; i < waves.length; i++) {
			if (waves[i].bestwave < bestwave) {
				waves[i] = new CalamityBestWave(player);
				sort();
				return;
			}
		}
	}

}
