//package com.rs.game.player.actions.itemcreation;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import com.rs.game.player.Player;
//import com.rs.game.player.Skills;
//
///**
// * Handles item on item.
// *
// * @author Raghav
// *
// */
//public class ItemCreation {
//
//	/**
//	 * An enum containing all the data.
//	 *
//	 * @author Raghav
//	 *
//	 */
//	public enum ItemOnItem {
//
//		ELYSIAN_SPIRIT_SHIELD(13750, 13736, 13742, Skills.PRAYER, 90, 10, Skills.SMITHING, 85, 10);
//
//		/**
//		 * A hasmap to store all the data.
//		 */
//		private static Map<Integer, ItemOnItem> itemOnItems = new HashMap<Integer, ItemOnItem>();
//
//		/**
//		 * Gets the data from mapping.
//		 * @param itemId The item used id.
//		 * @return The {@code ItemOnItem} {@code Object}, or {@code Null} if the data is nonexistent.
//		 */
//		public static ItemOnItem forId(int itemId) {
//			return itemOnItems.get(itemId);
//		}
//
//		/**
//		 * Populating the map.
//		 */
//		static {
//			for (ItemOnItem itemOnItem : ItemOnItem.values()) {
//				itemOnItems.put(itemOnItem.getItem1(), itemOnItem);
//			}
//		}
//
//		/**
//		 * The item to be used on item2.
//		 */
//		private final int item1;
//
//		/**
//		 * The item to be used on item1.
//		 */
//		private final int item2;
//
//		/**
//		 * The new item which player is going to make by combining item1 and item2.
//		 */
//		private final int item3;
//
//		/**
//		 * If player can make this item.
//		 */
//		private final int[] skillRequirement;
//
//		/**
//		 * Constructs a new {@code ItemOnItem} {@code Object}.
//		 *
//		 * @param item1 The item to be used on item2.
//		 * @param item2 The item to be used on item1.
//		 * @param item3 The new item which player is going to make by combining item1 and item2.
//		 * @param skillRequirement Skill Id, Required level, Exp in that skill.
//		 */
//		ItemOnItem(int item1, int item2, int item3, int...skillRequirement) {
//			this.item1 = item1;
//			this.item2 = item2;
//			this.item3 = item3;
//			this.skillRequirement = skillRequirement;
//		}
//
//		/**
//		 * Gets the first item.
//		 * @return item1
//		 */
//		public int getItem1() {
//			return item1;
//		}
//
//		/**
//		 * Gets the 2nd item.
//		 * @return item2
//		 */
//		public int getItem2() {
//			return item2;
//		}
//
//		/**
//		 * Gets the 3rd item.
//		 * @return item3
//		 */
//		public int getItem3() {
//			return item3;
//		}
//
//		/**
//		 * Gets skill requirments..
//		 * @return skillRequirement
//		 */
//		public int[] getSkillRequirement() {
//			return skillRequirement;
//		}
//	}
//
//	/**
//	 * Handles the item on item action.
//	 *
//	 * @param player The player.
//	 * @param itemOnItem The itemOnItem.
//	 * @param usedWith The item used.
//	 * @param itemUsed The item used.
//	 */
//	public static void handleItemOnItem(Player player, ItemOnItem itemOnItem, int usedWith, int itemUsed) {
//		int[] skillStuff = itemOnItem.getSkillRequirement();
//		if (player.getSkills().getLevel(skillStuff[0]) >= skillStuff[1]) {
//			if (player.getSkills().getLevel(skillStuff[3]) >= skillStuff[4]) {
//				if (player.getInventory().contains(itemUsed) && player.getInventory().contains(usedWith)) {
//				player.getInventory().deleteItem(usedWith, 1);
//				player.getInventory().deleteItem(itemUsed, 1);
//				player.getInventory().addItem(itemOnItem.getItem3(), 1);
//				player.getSkills().addXp(skillStuff[0], skillStuff[2]);
//			} else
//				player.getPackets().sendGameMessage("You need a " + Skills.SKILL_NAME[skillStuff[3]] + " level of " + Skills.SKILL_NAME[skillStuff[4]] + " to make this.");
//		} else
//			player.getPackets().sendGameMessage("You need a " + Skills.SKILL_NAME[skillStuff[0]] + " level of " + skillStuff[1] + " to make this.");
//	}
//	}
//
//}