package com.rs.game.player.content;

import java.util.Random;

import com.rs.game.World;
import com.rs.game.player.Player;

public class Tips {

	public static int stringId = -1;

	private static String strings [] = {
		"<col=b7b82b>Did you know you can drink from the fountain at home to restore your stats?</col>",
		"<col=b7b82b>Voting every 12 hours not only helps the server, but provides great rewards! (::vote)</col>",
		"<col=b7b82b>We have a brand new website! Check it out at www.harmonyrsps.com (::website)</col>",
		"<col=b7b82b>Join the server Discord by typing ::discord!</col>",
		"<col=b7b82b>You can find teleport options in the quest tab and with Max at home!</col>",
		"<col=b7b82b>Talk to the Shop Hub in the bank at home for easy access to shops!</col>",
		"<col=b7b82b>Consider getting a bank pin! Speak to a banker to set it up!</col>",
		"<col=b7b82b>Did you know you can zoom by holding ctrl and using the mousewheel?</col>",
		"<col=b7b82b>Killing the boss currently on spotlight provides improved drop rates!</col>",
		"<col=b7b82b>You can claim your votes by doing ::reward or ::voted!</col>",
		"<col=b7b82b>Enjoy Double XP every Friday to Sunday! Get those gains!</col>",
		"<col=b7b82b>You can use coins on the fountain at home to activate Double XP!</col>",
		"<col=b7b82b>You can click on a skill in the skill tab for easy access to teleports!</col>",
		"<col=b7b82b>We highly suggest using OpenGL settings for the best graphical experience!</col>"
	};

	public static int random() {
		int random = 0;
		Random rand = new Random();
		random = rand.nextInt(strings.length);
		return random;
	}
	
	public static void run() {
		int r = random();
		stringId = r;
		for (Player p : World.getPlayers()) {
			if (p == null)
				continue;
			if (!p.isTipsOff()) {
				p.getPackets().sendGameMessage("<img=6><col=b7b82b>News:</col> " + strings[r]);
			}
		}
	}
}