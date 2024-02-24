package com.rs.utils;

import java.io.File;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;

import com.rs.game.player.Player;

public final class PrestigeHS implements Serializable {

	private static final long serialVersionUID = 5403480618483552509L;

	private String username;
	private int toptok;

	private static PrestigeHS[] waves;

	private static final String PATH = "data/serfiles/PrestigeHS.ser";

	public PrestigeHS(Player player) {
		this.username = player.getUsername();
		this.toptok = player.prestigeTokens;
	}

	public static void init() {
		File file = new File(PATH);
		if (file.exists())
			try {
				waves = (PrestigeHS[]) SerializableFilesManager
						.loadSerializedFile(file);
				return;
			} catch (Throwable e) {
				Logger.handle(e);
			}
		waves = new PrestigeHS[300];
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
									+ " - Prestige Level: " + waves[i].toptok);
		}
		player.getPackets().sendIComponentText(275, 1,
				"Prestige Leaderboards.");
		player.getInterfaceManager().sendInterface(275);
	}

	public static void sort() {
		Arrays.sort(waves, new Comparator<PrestigeHS>() {
			@Override
			public int compare(PrestigeHS arg0, PrestigeHS arg1) {
				if (arg0 == null)
					return 1;
				if (arg1 == null)
					return -1;
				if (arg0.toptok < arg1.toptok)
					return 1;
				else if (arg0.toptok > arg1.toptok)
					return -1;
				else
					return 0;
			}

		});
	}

	public static void checkRank(Player player) {
		int bestwave = player.calamitybestwave;
		for (int i = 0; i < waves.length; i++) {
			PrestigeHS rank = waves[i];
			if (rank == null)
				break;
			if (rank.username.equalsIgnoreCase(player.getUsername())) {
				waves[i] = new PrestigeHS(player);
				sort();
				return;
			}
		}
		for (int i = 0; i < waves.length; i++) {
			PrestigeHS rank = waves[i];
			if (rank == null) {
				waves[i] = new PrestigeHS(player);
				sort();
				return;
			}
		}
		for (int i = 0; i < waves.length; i++) {
			if (waves[i].toptok < bestwave) {
				waves[i] = new PrestigeHS(player);
				sort();
				return;
			}
		}
	}

}
