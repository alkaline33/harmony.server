package com.rs.game.player.content.event.season;

import com.rs.Settings;
import com.rs.game.World;
import com.rs.game.player.Player;
import com.rs.utils.Colors;
import com.rs.utils.Utils;

public class SeasonEvent {

	public static int TOKENS = 29348;

	public static String SEASON = "Autumn";

	static final String[] ACTIVITY = { "Heist", "Theatre of Blood", "Mining", "Fishing", "Agility", "Calamity", "Recycle Centre", 
			"Clue Scrolls", "Crystal Keys", "Dominion Tower", "Player Wars", "Donating & Voting" };


	public static void ExchangeTokens(Player player, int itemId) {
		if (itemId == TOKENS) {
			if (player.getInventory().getNumerOf(TOKENS) < 300) {
				player.sendMessage(Colors.red + "You need 300 tokens to exchange them for a reward!");
				return;
			} else if (player.getInventory().getFreeSlots() < 4) {
				player.sendMessage(Colors.red + "You need at least 4 free inventory slots to do this!");
				return;
			} else {
				TokenRewards.handle(player);
			}
		}
	}

	/**
	 * 
	 * @Author Connor
	 */

	public static void GrabEvent() {
		String currentevent = Settings.SEASON_EVENT;
		Settings.SEASON_EVENT = ACTIVITY[Utils.random(ACTIVITY.length)];
		if (Settings.SEASON_EVENT.equalsIgnoreCase(currentevent)) {
			GrabEvent();
			System.out.println("" + SEASON + " Event: Same event was rolled. Event was re-rolled.");
			return;
		}
		World.sendWorldMessage(Colors.orange + "" + SEASON + " Event: " + Settings.SEASON_EVENT + "!", false);
		return;
	}

	public static void HandleActivity(Player player, String activity, int value) {
		if (!activity.equalsIgnoreCase(Settings.SEASON_EVENT)) {
			return;
		}
		int randomchance = Utils.random(1500);
		if (randomchance == 0) {
			World.sendWorldMessage(Colors.green + "" + SEASON + " Event: " + player.getDisplayName() + " has found 1,000 " + SEASON + " Tokens!", false);
			player.getBank().addItem(TOKENS, 1000, true);
		}
		
		switch (Settings.SEASON_EVENT) {
		case "Mining":
			if (value < 1)
				value = 1;
			player.getBank().addItem(TOKENS, value, true);
			player.getPackets().sendGameMessage(Colors.green + ""+ value + " " + SEASON + " Tokens have been added to your bank!", true);
			break;
		case "Fishing":
			if (value > 5)
				value = 5;
			player.getBank().addItem(TOKENS, value, true);
			player.getPackets().sendGameMessage(Colors.green + ""+ value + " " + SEASON + " Tokens have been added to your bank!", true);
			break;
		case "Player Wars":
			player.getBank().addItem(TOKENS, value, true);
			player.getPackets().sendGameMessage(Colors.green + ""+ value + " " + SEASON + " Tokens have been added to your bank!", true);
			break;			
		case "Theatre of Blood":
			player.getBank().addItem(TOKENS, value, true);
			player.getPackets().sendGameMessage(Colors.green + ""+ value + " " + SEASON + " Tokens have been added to your bank!", true);
			break;
		case "Agility":
			player.getBank().addItem(TOKENS, value, true);
			player.getPackets().sendGameMessage(Colors.green + ""+ value + " " + SEASON + " Tokens have been added to your bank!", true);
			break;
		case "Calamity":
			player.getBank().addItem(TOKENS, value, true);
			player.getPackets().sendGameMessage(Colors.green + ""+ value + " " + SEASON + " Tokens have been added to your bank!", true);
			break;
		case "Dominion Tower":
			player.getBank().addItem(TOKENS, value, true);
			player.getPackets().sendGameMessage(Colors.green + ""+ value + " " + SEASON + " Tokens have been added to your bank!", true);
			break;
		case "Crystal Keys":
			player.getBank().addItem(TOKENS, value, true);
			player.getPackets().sendGameMessage(Colors.green + ""+ value + " " + SEASON + " Tokens have been added to your bank!", true);
			break;
		case "Clue Scrolls":
			player.getBank().addItem(TOKENS, 35, true);
			player.getPackets().sendGameMessage(Colors.green + "35 " + SEASON + " Tokens have been added to your bank!", true);
			break;
		case "Recycle Centre":
			player.getBank().addItem(TOKENS, 2 * value, true);
			player.getPackets().sendGameMessage(Colors.green + "" + 1 * value + " " + SEASON + " Tokens have been added to your bank!", true);
			break;
		case "Heist"://2000,600,300
			player.getBank().addItem(TOKENS, value, true);
			player.getPackets().sendGameMessage(Colors.green +""+value+" "+ SEASON + " Tokens have been added to your bank!", true);
			break;
		case "Donating & Voting":
			player.getBank().addItem(TOKENS, value, true);
			player.getPackets().sendGameMessage(Colors.green +""+value+" "+ SEASON + " Tokens have been added to your bank!", true);
			break;
		
		}
	}

}
