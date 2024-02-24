package com.rs.game.player.actions;

import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.game.player.content.Pots.Pot;

public class PotionDecanting {

	public static int VIAL = 229;
	public static int FLASK = 23191;

	public static Pot getPot(int id) {
		for (Pot pot : Pot.values())
			for (int potionId : pot.id) {
				if (id == potionId)
					return pot;
			}
		return null;
	}

	public static int getDoses(Pot pot, Item item) {
		for (int i = pot.id.length - 1; i >= 0; i--) {
			if (pot.id[i] == item.getId())
				return pot.id.length - i;
		}
		return 0;
	}

	public static boolean mixPot(Player player, Item fromItem, Item toItem, int fromSlot, int toSlot) {
		if (fromItem.getId() == VIAL || toItem.getId() == VIAL) {
			Pot pot = getPot(fromItem.getId() == VIAL ? toItem.getId() : fromItem.getId());

			if (pot == null || pot.isFlask())
				return false;

			if (fromSlot == toSlot) {
				return false;
			}

			int doses = getDoses(pot, fromItem.getId() == VIAL ? toItem : fromItem);
			if (doses == 1) {
				player.getInventory().switchItem(fromSlot, toSlot);
				return true;
			}
			int vialDoses = doses / 2;
			doses -= vialDoses;
			player.getInventory().getItems().set(fromItem.getId() == VIAL ? toSlot : fromSlot, new Item(pot.getIdForDoses(doses), 1));
			player.getInventory().getItems().set(fromItem.getId() == VIAL ? fromSlot : toSlot, new Item(pot.getIdForDoses(vialDoses), 1));
			player.getInventory().refresh(fromSlot);
			player.getInventory().refresh(toSlot);
			return true;
		}
		Pot pot = getPot(fromItem.getId());
		if (pot == null)
			return false;

		if (fromSlot == toSlot) {
			return false;
		}

		int doses2 = getDoses(pot, toItem);
		if (doses2 == 0 || doses2 == pot.getMaxDoses())
			return false;

		int doses1 = getDoses(pot, fromItem);
		doses2 += doses1;
		doses1 = doses2 > pot.getMaxDoses() ? doses2 - pot.getMaxDoses() : 0;
		doses2 -= doses1;

		if (doses1 == 0 && pot.isFlask())
			player.getInventory().deleteItem(fromSlot, fromItem);
		else {
			player.getInventory().getItems().set(fromSlot, new Item(doses1 > 0 ? pot.getIdForDoses(doses1) : VIAL, 1));
			player.getInventory().refresh(fromSlot);
		}
		player.getInventory().getItems().set(toSlot, new Item(pot.getIdForDoses(doses2), 1));
		player.getInventory().refresh(toSlot);
		return true;
	}
}