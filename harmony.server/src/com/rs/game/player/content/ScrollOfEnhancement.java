package com.rs.game.player.content;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.Animation;
import com.rs.game.player.Player;
import com.rs.utils.Colors;

public class ScrollOfEnhancement {

	/**
	 * @author Connor
	 */

	private static int SCROLL = 19670;

	public static void handleScroll(Player player, int usedWith) {
		if (player.getInventory().contains(usedWith)) {
			switch (usedWith) {

			case 2572:
				player.scrollofenhancementsused++;
				player.setNextAnimation(new Animation(1280));
				player.getInventory().deleteItem(SCROLL, 1);
				player.getInventory().deleteItem(usedWith, 1);
				player.getInventory().addItem(29427, 1);
				player.sendMessage(Colors.gray + "You have enhanced your " + ItemDefinitions.getItemDefinitions(usedWith).getName() + ".");
				break;
			case 20054:
				player.scrollofenhancementsused++;
				player.setNextAnimation(new Animation(1280));
				player.getInventory().deleteItem(SCROLL, 1);
				player.getInventory().deleteItem(usedWith, 1);
				player.getInventory().addItem(20052, 1);
				player.sendMessage(Colors.gray + "You have enhanced your " + ItemDefinitions.getItemDefinitions(usedWith).getName() + ".");
				break;
			case 15492:
				if (player.slayerPoints < 500) {
					player.sendMessage("You must have 500 slayer points to do this.");
					break;
				}
				player.scrollofenhancementsused++;
				player.setNextAnimation(new Animation(1280));
				player.getInventory().deleteItem(SCROLL, 1);
				player.getInventory().deleteItem(usedWith, 1);
				player.slayerPoints -= 500;
				player.getInventory().addItem(15497, 1);
				player.sendMessage(Colors.gray + "You have enhanced your " + ItemDefinitions.getItemDefinitions(usedWith).getName() + ".");
				break;
			default:
				player.sendMessage(Colors.red + "This item cannot be enhanced with this scroll.");
				break;

			}
		}
	}

}
