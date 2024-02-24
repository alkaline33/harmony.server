package com.rs.utils;

import java.io.File;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;

import com.rs.game.World;
import com.rs.game.player.Player;

public final class MaxPlayersOnline implements Serializable {

	private static final long serialVersionUID = 5403480618483552509L;

	private static int amount;

	private static MaxPlayersOnline[] playersmax;

	private static final String PATH = "data/serfiles/MostPlayersOnline.ser";

	public MaxPlayersOnline(Player player) {
		amount = World.getPlayers().size1();
	}

	public static void init() {
		File file = new File(PATH);
		if (file.exists())
			try {
				playersmax = (MaxPlayersOnline[]) SerializableFilesManager
						.loadSerializedFile(file);
				return;
			} catch (Throwable e) {
				Logger.handle(e);
			}
		//playersmax = MaxPlayersOnline.;
	}
	
	public static void showRanks(Player player) {
		for (int i = 10; i < 310; i++)
			player.getPackets().sendIComponentText(275, i, "");
		for (int i = 0; i < 300; i++) {
			if (playersmax[i] == null)
				break;
			String text = null;
			if (i == 0)
				text = "<col=0066ff>";
			player.getPackets().sendIComponentText(275,i + 10,text
									+ "Top "
									+ (i + 1)
									+ " - "
									+ " - Highest Wave: " + playersmax[i].amount);
		}
		player.getPackets().sendIComponentText(275, 1,
				"The Calamity Highest Wave's Achieved");
		player.getInterfaceManager().sendInterface(275);
	}
	
	public static void checkRank(Player player) {
		//int bestwave = player.calamitybestwave;
		for (int i = 0; i < playersmax.length; i++) {
			MaxPlayersOnline rank = playersmax[i];
			if (rank == null)
				break;
				playersmax[i] = new MaxPlayersOnline(player);
				sort();
				return;
		}
		for (int i = 0; i < playersmax.length; i++) {
			MaxPlayersOnline rank = playersmax[i];
			if (rank == null) {
				playersmax[i] = new MaxPlayersOnline(player);
				sort();
				return;
			}
		}
		for (int i = 0; i < playersmax.length; i++) {
			if (playersmax[i].amount < amount) {
				playersmax[i] = new MaxPlayersOnline(player);
				sort();
				return;
			}
		}
	}

	public static final void save() {
		try {
			SerializableFilesManager.storeSerializableClass(playersmax, new File(
					PATH));
		} catch (Throwable e) {
			Logger.handle(e);
		}
	}
	
	public static int Amount() {
		return playersmax.length;
		
	}

	public static void sort() {
		Arrays.sort(playersmax, new Comparator<MaxPlayersOnline>() {
			@Override
			public int compare(MaxPlayersOnline arg0, MaxPlayersOnline arg1) {
				if (arg0 == null)
					return 1;
				if (arg1 == null)
					return -1;
				if (arg0.amount < arg1.amount)
					return 1;
				else if (arg0.amount > arg1.amount)
					return -1;
				else
					return 0;
			}

		});
	}

}
