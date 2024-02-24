package com.rs.game.player.content;


import com.rs.game.World;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.utils.Utils;


public class WellOfFortune {


	public static void handleWell(Player player, Item item) {
		int value = item.getDefinitions().getValue();
		int xpgained = value /1500000;
			if (item.getId() == 995) {
			player.sendMessage("<col=FF0000>Sorry. We don't accept coins right now.");
			return;
			}
			if (!player.getInventory().hasItem(item.getId())) {
				player.sendMessage("<col=ff0000>You actually have to have this item to do this.");
				return;
			}
			if (!ItemConstants.isTradeable(item) || item.getDefinitions().isDestroyItem() || value == 0) {
				player.sendMessage("<col=ff0000>You cannot donate items that have no value!");
				return;
			}
			if (value >= 1500000) {
				player.d60mxp1 += xpgained;
				player.sendMessage("Thank you. You have gained "+xpgained+" minutes of double xp!");
			}
			if (value >= 10000000) {
				World.sendWorldMessage("News: <col=660066>"+player.getDisplayName()+" has donated a "+item.getName()+" worth "+Utils.format(value)+" to the Well of Fortune!", false);
			}
		player.getInventory().deleteItem(item.getId(), 1);
		player.totalDonated += value;
		player.sendMessage("<col=660066>You have donated "+item.getName()+" at the value of</col><col=66FF33> "+Utils.format(value)+"");
		player.sendMessage("<col=0000FF>You've donated a total of <col=66FF33>"+Utils.format(player.getTotalDonatedToWell())+"</col> to the Well of Good Fortune!");
		}
	}