package com.rs.game.player.interfaces.containers;

import java.io.Serializable;

import com.rs.game.item.Item;
import com.rs.game.item.ItemsContainer;
import com.rs.game.player.Player;

public class BoxInterface1 implements Serializable {

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
	public BoxInterface1() {
		container = new ItemsContainer<Item>(1, true);
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

}
