package com.rs.game.item;

import java.io.Serializable;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.cache.loaders.ItemsEquipIds;

/**
 * Represents a single item.
 * <p/>
 * 
 * @author Graham / edited by Dragonkk(Alex)
 */
public class Item implements Serializable {

	private static final long serialVersionUID = -6485003878697568087L;
	
	public static final int[] PETIDS = { 29760, 11951, 15420, 29907, 29905,29904 ,29906 ,29865 ,29864 ,29863 ,29862 ,29861 ,29860 ,29859,4671 ,29853 ,29851 ,29852 ,29850 ,29849 ,29848 ,29847 ,29842 ,29841,29786   };

	private short id;
	protected int amount;

	public int getId() {
		return id;
	}

	@Override
	public Item clone() {
		return new Item(id, amount);
	}

	public Item(int id) {
		this(id, 1);
	}

	public Item(int id, int amount) {
		this(id, amount, false);
	}

	public Item(int id, int amount, boolean amt0) {
		this.id = (short) id;
		this.amount = amount;
		if (this.amount <= 0 && !amt0) {
			this.amount = 1;
		}
	}

	public ItemDefinitions getDefinitions() {
		return ItemDefinitions.getItemDefinitions(id);
	}

	public int getEquipId() {
		return ItemsEquipIds.getEquipId(id);
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public void setId(int id) {
		this.id = (short) id;
	}

	public int getAmount() {
		return amount;
	}
	
	public static String getItemName(int itemId) {
		return ItemDefinitions.getItemDefinitions(itemId).getName().toLowerCase();
	}
	
	public String getName() {
		return getDefinitions().getName();
	}

}
