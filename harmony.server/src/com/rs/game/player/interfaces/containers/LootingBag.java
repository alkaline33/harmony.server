package com.rs.game.player.interfaces.containers;

import java.io.Serializable;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.item.ItemsContainer;
import com.rs.game.player.Player;
import com.rs.game.player.content.ItemConstants;
import com.rs.utils.Colors;

public class LootingBag implements Serializable {

	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = -8752068490060348315L;

	/**
	 * Items in the chest currently.
	 */
	public ItemsContainer<Item> container;

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
	public LootingBag() {
		container = new ItemsContainer<Item>(28, false);
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
		for (Item item : items) {
			if (!ItemConstants.isTradeable(item)) {
				player.sendMessage(Colors.red + "You can only store tradeable items in the looting bag!");
				return false;
			}

		}
		if (player.getLootingBag().container.freeSlots() < 1) {
			player.sendMessage("<col=ff0000>Your Looting bag is currently full. Please empty it to add additional items.</col>");
			return false;
		} 
		for (Item item : items) {
			if (!container.contains(item)) {
				if (!(container.freeSlots() >= items.length)) {
					player.sendMessage("<col=ff0000>Your Looting bag is currently full. Please empty it to add additional items.</col>");
					return false;
				}
			}
		}
		for (Item item : items) {
			container.add(item);
			player.getInventory().deleteItem(item);
			player.sendMessage(Colors.red + "Item added to Looting bag: " + item.getAmount() + " x " + item.getName() + "");
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
			if (!World.isHomeArea(player)) {
				player.sendMessage(Colors.red + "You can only do this at the home area!");
				return false;
			}
			ItemDefinitions def = item.getDefinitions();

			if (def.isNoted() && def.getCertId() != -1) {
				item.setId(def.getCertId());
			}

			player.getBank().addItem(item, true);

		}
		player.sendMessage(Colors.green + "Your looting bag contents has been deposited to your bank.");
		container.clear();
		return true;
	}

	/**
	 * Drops all the contents on floor
	 * 
	 */
	public boolean dropContents(Player owner) {
		for (Item item : getContainer().getItems()) {
			if (item == null) {
				continue;
			}
			ItemDefinitions def = item.getDefinitions();
			World.addGroundItem(item, new WorldTile(player.getX()+1, player.getY(), player.getPlane()), player, false, 20, true);

		}
		container.clear();
		return true;
	}

}
