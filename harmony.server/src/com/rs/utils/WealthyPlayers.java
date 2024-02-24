package com.rs.utils;

import java.io.File;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;

import com.rs.game.player.Player;

public final class WealthyPlayers implements Serializable {

	private static final long serialVersionUID = 5403480618483552509L;

	private String username;
	private long bankvalue;

	private static WealthyPlayers[] value;

	private static final String PATH = "data/serfiles/PlayersWealth.ser";

	public WealthyPlayers(Player player) {
		this.username = player.getUsername();
		this.bankvalue = player.calculateNetworth();
	}

	public static void init() {
		File file = new File(PATH);
		if (file.exists())
			try {
				value = (WealthyPlayers[]) SerializableFilesManager
						.loadSerializedFile(file);
				return;
			} catch (Throwable e) {
				Logger.handle(e);
			}
		value = new WealthyPlayers[300];
	}

	public static final void save() {
		try {
			SerializableFilesManager.storeSerializableClass(value, new File(
					PATH));
		} catch (Throwable e) {
			Logger.handle(e);
		}
	}

	public static void showRanks(Player player) {
		for (int i = 10; i < 310; i++)
			player.getPackets().sendIComponentText(275, i, "");
		for (int i = 0; i < 300; i++) {
			if (value[i] == null)
				break;
			String text;
			if (i >= 0 && i <= 2)
				text = "<col=00FFFF>";
			else if (i <= 9)
				text = "<col=0000FF>";
			else if (i <= 49)
				text = "<col=ff0000>";
			else
				text = "<col=000000>";
			player.getPackets().sendIComponentText(275,i + 10,text
									+ "# "
									+ (i + 1)
									+ " - "
									+ Utils.formatPlayerNameForDisplay(value[i].username)
									+ " - Value: <col=00ff00>" + Utils.format(value[i].bankvalue));
		}
		player.getPackets().sendIComponentText(275, 1,
				"<col=FF00FF>Wealthiest players in-game");
		player.sendMessage("These values are based on purely the price checker value.");
		player.getInterfaceManager().sendInterface(275);
	}

	public static void sort() {
		Arrays.sort(value, new Comparator<WealthyPlayers>() {
			@Override
			public int compare(WealthyPlayers arg0, WealthyPlayers arg1) {
				if (arg0 == null)
					return 1;
				if (arg1 == null)
					return -1;
				if (arg0.bankvalue < arg1.bankvalue)
					return 1;
				else if (arg0.bankvalue > arg1.bankvalue)
					return -1;
				else
					return 0;
			}

		});
	}

	public static void checkRank(Player player) {
		//int bestwave = player.calamitybestwave;
		for (int i = 0; i < value.length; i++) {
			WealthyPlayers rank = value[i];
			if (rank == null)
				break;
			if (rank.username.equalsIgnoreCase(player.getUsername())) {
				value[i] = new WealthyPlayers(player);
				sort();
				return;
			}
		}
		for (int i = 0; i < value.length; i++) {
			WealthyPlayers rank = value[i];
			if (rank == null) {
				value[i] = new WealthyPlayers(player);
				sort();
				return;
			}
		}
		for (int i = 0; i < value.length; i++) {
		
				value[i] = new WealthyPlayers(player);
				sort();
				return;
			
		}
	}

}
