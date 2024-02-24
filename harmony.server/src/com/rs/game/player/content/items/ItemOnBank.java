package com.rs.game.player.content.items;

import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.game.player.content.ItemConstants;
import com.rs.utils.Colors;




public class ItemOnBank {


	public static boolean handleNote(Player player, Item item) {
		/**
		 * Variables
		 */
		int toNote = item.getDefinitions().getCertId();
		int amount = player.getInventory().getNumerOf(item.getId());

		/**
		 * Checks
		 */
//		if (item.getDefinitions().isStackable() && item.getDefinitions().isUnNoted() || !ItemConstants.isTradeable(item)) {
//			player.sendMessage(Colors.red + "You cannot note/unnote " + item.getName() + ".");
//			return false;
//			
//		}
//		
		/**
		 * Methods
		 */
		player.getInventory().deleteItem(item.getId(), amount);
		//player.getInventory().addItemDrop(toNote);
		return true;
		
	}
}
	
