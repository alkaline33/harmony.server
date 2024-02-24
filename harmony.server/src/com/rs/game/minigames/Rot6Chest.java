package com.rs.game.minigames;

import com.rs.game.Animation;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.utils.Colors;
import com.rs.utils.Utils;



public class Rot6Chest {
	
	private static final int[] CHEST_REWARDS = {13663, 5316, 1079, 1215, 11732, 15332, 3122, 1149, 8786, 3265, 1712, 526, 1513, 4716, 4718, 4720, 4722, 4753, 4755, 4757, 4759, 4708,
			4710, 4712, 4714, 4745, 4747, 4749, 4751, 4732, 4734, 4736, 4738, 4724, 4726, 4728, 4730};
	public static final int Animation = 881;
	private static int freekey;
	
	/**
	 * If the player can open the chest.
	 */
	public static boolean canOpen(Player player){
		if (player.Rot6Points < 6) {
			player.sendMessage(Colors.red+"You need 6 Rise Of The Six points to use this, you only have "+player.Rot6Points+" points.");
			return false;
		}
		if (player.getInventory().getFreeSlots() < 3) {
			player.sendMessage("You require at least 3 free inventory spaces.");
			return false;
		}
		return true;
	}
	
	/**
	 * When the player searches the chest.
	 */
	public static void searchChest(final Player p){
		if (canOpen(p)){
			p.sendMessage("You open the chest...");
			p.Rot6Points -= 6;
			p.setNextAnimation(new Animation(Animation));
			p.getInventory().addItemDrop(CHEST_REWARDS[Utils.random(getLength() - 1)], 1);
			p.getInventory().addItemDrop(29942, 1);
			p.Rot6getDropLog().getContainer().add(new Item(29942, 1));
			if (Utils.random(50) == 0) {
				p.sendMessage("Woah! You find an ancient shield.");
				switch (Utils.random(3)) {
				case 0:
					p.getInventory().addItemDrop(29934, 1);
					p.Rot6getDropLog().getContainer().add(new Item(29934, 1));
					break;
				case 1:
					p.getInventory().addItemDrop(29933, 1);
					p.Rot6getDropLog().getContainer().add(new Item(29933, 1));
					break;
				default:
					p.getInventory().addItemDrop(29932, 1);
					p.Rot6getDropLog().getContainer().add(new Item(29932, 1));
					break;
				}
			} else {
			p.getInventory().addItem(CHEST_REWARDS[Utils.random(getLength() - 1)], 1);
			p.sendMessage("You find some regular treasure in the chest.");
			}
		}
	}
	
	public static int getLength() {
		return CHEST_REWARDS.length;
	}
	
}