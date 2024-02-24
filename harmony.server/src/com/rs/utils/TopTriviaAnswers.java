package com.rs.utils;

import java.io.File;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;

import com.rs.game.player.Player;

public final class TopTriviaAnswers implements Serializable {

	private static final long serialVersionUID = 5403480618483552509L;

	private String username;
	private int answers;

	private static TopTriviaAnswers[] waves;

	private static final String PATH = "data/serfiles/TopTriviaAnswers.ser";

	public TopTriviaAnswers(Player player) {
		username = player.getUsername();
		answers = player.getAnswers();
	}

	public static void init() {
		File file = new File(PATH);
		if (file.exists()) {
			try {
				waves = (TopTriviaAnswers[]) SerializableFilesManager
						.loadSerializedFile(file);
				return;
			} catch (Throwable e) {
				Logger.handle(e);
			}
		}
		waves = new TopTriviaAnswers[300];
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
		for (int i = 10; i < 310; i++) {
			player.getPackets().sendIComponentText(275, i, "");
		}
		for (int i = 0; i < 300; i++) {
			if (waves[i] == null) {
				break;
			}
			String text;
			if (i >= 0 && i <= 2) {
				text = "<col=0066ff>";
			} else if (i <= 9) {
				text = "<col=ff0000>";
			} else if (i <= 49) {
				text = "<col=38610B>";
			} else {
				text = "<col=000000>";
			}
			player.getPackets().sendIComponentText(275,i + 10,text
									+ "Top "
									+ (i + 1)
									+ " - "
									+ Utils.formatPlayerNameForDisplay(waves[i].username)
									+ " - Trivia Answers: " + waves[i].answers);
		}
		player.getPackets().sendIComponentText(275, 1,
				"Most Trivia Answers");
		player.getInterfaceManager().sendInterface(275);
	}

	public static void sort() {
		Arrays.sort(waves, new Comparator<TopTriviaAnswers>() {
			@Override
			public int compare(TopTriviaAnswers arg0, TopTriviaAnswers arg1) {
				if (arg0 == null) {
					return 1;
				}
				if (arg1 == null) {
					return -1;
				}
				if (arg0.answers < arg1.answers) {
					return 1;
				} else if (arg0.answers > arg1.answers) {
					return -1;
				} else {
					return 0;
				}
			}

		});
	}

	public static void checkRank(Player player) {
		int answers = player.getAnswers();
		for (int i = 0; i < waves.length; i++) {
			TopTriviaAnswers rank = waves[i];
			if (rank == null) {
				break;
			}
			if (rank.username.equalsIgnoreCase(player.getUsername())) {
				waves[i] = new TopTriviaAnswers(player);
				sort();
				return;
			}
		}
		for (int i = 0; i < waves.length; i++) {
			TopTriviaAnswers rank = waves[i];
			if (rank == null) {
				waves[i] = new TopTriviaAnswers(player);
				sort();
				return;
			}
		}
		for (int i = 0; i < waves.length; i++) {
			if (waves[i].answers < answers) {
				waves[i] = new TopTriviaAnswers(player);
				sort();
				return;
			}
		}
	}

}
