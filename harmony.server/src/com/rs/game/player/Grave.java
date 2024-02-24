package com.rs.game.player;

import java.io.Serializable;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.item.Item;
import com.rs.game.item.ItemsContainer;

public class Grave implements Serializable {

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
	 * Constructor
	 */
	public Grave() {
		container = new ItemsContainer<Item>(42, true);
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
			// if (item.getId() < 554 || item.getId() > 566 && item.getId() != 9075)
			// return false;

		}
		for (Item item : items) {
			if (!container.contains(item)) {
				if (!(container.freeSlots() >= items.length)) {
					player.sendMessage("<col=ff0000>Your Grave is currently full. Your stuff has been dropped!</col>");
					return false;
				}
			}
		}
		for (Item item : items) {
			container.add(item);
			// player.getInventory().deleteItem(item.getId(), item.getAmount());
			// player.sendMessage(Colors.red + "Item added to rune pouch: " +
			// item.getAmount() + " x " + item.getName() + "");
		}

		return true;
	}

	public long checkValue() {
		long value = 0;
		for (Item item : getContainer().getItems()) {

			if (item == null)
				continue;
			ItemDefinitions def = item.getDefinitions();
			value += def.getValue() * item.getAmount();
		}
		if (player.hustlerperk)
			value /= 2;
		return value;
	}
	
	/**
	 * Deposits all the chest contents to their bank.
	 * 
	 * @return {@value true} if the items were deposited.
	 */
	public boolean depositChestToBank() {
		for (Item item : getContainer().getItems()) {

			if (item == null)
				continue;

			ItemDefinitions def = item.getDefinitions();

			if (def.isNoted() && def.getCertId() != -1)
				item.setId(def.getCertId());

			player.getBank().addItem(item, true);
		}
		container.clear();
		return true;
	}

}
