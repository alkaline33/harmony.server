package com.rs.game.player.content;

import com.rs.Settings;
import com.rs.game.World;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.utils.Utils;

public class WellOfFortuneV2 {

	public static void handleWell(Player player, int value, Item item) {
		int target = 120000000;
		int amountleft1 = target - Settings.DONATED_TO_WELL;
		if (!player.getInventory().containsItem(995, value ) || value < 2) {
			player.sendMessage("<col=FF0000>Sorry. You don't have that many coins or you've entered an invalid amount.");
			return;
		}
		if (player.isWeekend()) {
			player.sendMessage("<col=ff0000> This cannot be used on the weekend!");
			return;
		}
		if (value > amountleft1) {
			player.sendMessage("<col=FF0000>You don't need to donate this much. You only need to donate</col> "+ amountleft1);
			return;
		}
		if (Settings.WELLDOUBLE == true) {
			player.sendMessage("Double xp is already active, this is not needed!");
			return;
		}
		if (Settings.WELLTIMER > 0) {
			player.sendMessage("<col=ff0000>The well is currently in cooldown with "+ Utils.format(Settings.WELLTIMER)+" minutes left.");
			return;
		}
		player.getInventory().deleteItem(995, value);
		Settings.GpSyncAmount += value;
		player.totalDonated += value;
		Settings.DONATED_TO_WELL += value;
		int amountleft = target - Settings.DONATED_TO_WELL;
		player.sendMessage("<col=0000FF>You've donated a total of <col=66FF33>"
				+ Utils.format(player.getTotalDonatedToWell()) + "</col> to the Well of Good Fortune!");
		player.sendMessage("<col=ff0000> A total of " + Utils.format(amountleft)
				+ " is left to be donated to the Well of Fortune for server x2 xp!");
		if (Settings.DONATED_TO_WELL >= target) {
			// ADD SERVER DOUBLE XP HERE
			Settings.DONATED_TO_WELL = 0;
			Settings.WELLDOUBLE = true;
			Settings.WELLTIMER = 120;
			World.runWellTimer();
			World.sendWorldMessage("<col=00ff00>Double xp is now active for 1 hour!", false);
			
		}
	}
}