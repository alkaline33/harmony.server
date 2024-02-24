package com.rs.game.player.content;

import java.io.Serializable;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.item.Item;
import com.rs.game.item.ItemsContainer;
import com.rs.game.player.Player;
import com.rs.utils.Colors;

public class PetPerk implements Serializable {

	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = -8752068490060348315L;

	/**
	 * Items in the chest currently.
	 */
	private ItemsContainer<Item> container;

	/**
	 * The player
	 */
	private Player player;

	/**
	 * If this chest is collecting loot.
	 */
	private boolean collecting;

	/**
	 * Constructor
	 */
	public PetPerk() {
		//System.out.println(player.haspetperkextra);
		container = new ItemsContainer<Item>(3, true);
		collecting = true;
	}

	/**
	 * Sets the player.
	 * 
	 * @param player
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

	/**
	 * Get the chest container.
	 * 
	 * @return
	 */
	public ItemsContainer<Item> getContainer() {
		return container;
	}

	/**
	 * Adds loot to the chest container.
	 * 
	 * @param items
	 *            {@link Item} the loot.
	 * @return {@value true} if loot was added to chest container.
	 */
	public boolean add(Item... items) {
		// Checks for drops that can't be collected by this chest.
		for (Item item : items) {
			// for (int id : DROPPED_ITEMS) {
			if (item.getId() != 29376 && item.getId() != 29271 && item.getId() != 29122 && item.getId() != 29001 && item.getId() != 29121 && item.getId() != 29248 && item.getId() != 29272 && item.getId() != 28916 && item.getId() != 29273 && item.getId() != 29274) {
				if (item.getId() < 29384 || item.getId() > 29401) {
					return false;
				}
			}

		}
		for (Item item : items) {
			if (!container.contains(item)) {
				if (!(container.freeSlots() >= items.length)) {
					player.sendMessage("<col=ff0000>Your pet is currently full. Please empty it to add additional perks.</col>");
					return false;
				}
			}
		}
		for (Item item : items) {
			container.add(item);
			player.getInventory().deleteItem(item);
			player.sendMessage(Colors.red + "Item added to pet perk: " + item.getAmount() + " x " + item.getName() + "");
		}

		return true;
	}

	/**
	 * Deposits all the chest contents to their bank.
	 * 
	 * @return {@value true} if the items were deposited.
	 */
	public boolean depositChestToBank() {
		for (Item item : getContainer().getItems()) {

			if (item == null) {
				continue;
			}

			ItemDefinitions def = item.getDefinitions();

			if (def.isNoted() && def.getCertId() != -1) {
				item.setId(def.getCertId());
			}

			player.getBank().addItem(item, true);
		}
		container.clear();
		return true;
	}

}
