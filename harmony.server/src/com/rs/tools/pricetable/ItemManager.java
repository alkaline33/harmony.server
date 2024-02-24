package com.rs.tools.pricetable;

import java.io.IOException;

import com.rs.cache.Cache;
import com.rs.cache.loaders.ItemDefinitions;
import com.rs.utils.Utils;

public class ItemManager {

	public static ItemManager[] values;

	public static void inits() {
		try {
			Cache.init();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		values = new ItemManager[50000];
		initArray();
	}

	private static void initArray() {
		for(int i = 0; i<= Utils.getItemDefinitionsSize(); i++)
		{
			values[i] = new ItemManager();
			values[i].itemId = i;
			values[i].itemName = ItemDefinitions.getItemDefinitions(i).name;
			values[i].value = ItemDefinitions.getItemDefinitions(i).value;
		}
		
		System.out.println("Inited " + values.length + "items.");
		
	}

	public static boolean update(int itemId, int newValue) {
		try {
		ItemDefinitions defs = ItemDefinitions.getItemDefinitions(itemId);
		defs.setValue(newValue);
		defs.write();
		updateRefTable();
		return true;
		}
		catch(Exception ex){
			return false;
		}
		
	}
	public static boolean updateStackable(int itemId, int newValue) {
		try {
		ItemDefinitions defs = ItemDefinitions.getItemDefinitions(itemId);
		defs.setStackable(newValue);
		defs.write();
		updateRefTable();
		return true;
		}
		catch(Exception ex){
			return false;
		}
		
	}

	public static int getPrice(int itemId) {
		return ItemDefinitions.getItemDefinitions(itemId).value;
	}

	public int itemId;
	public int value;
	public String itemName;
	
	public ItemManager(int itemid, int value, String name) {
		this.itemId = itemid;
		this.value = value;
		this.itemName = name;
	}

	public ItemManager() {
		// TODO Auto-generated constructor stub
	}

	public static String getName(int i) {
		return ItemDefinitions.getItemDefinitions(i).name;
	}

	public static int getStackable(int i) {
		return ItemDefinitions.getItemDefinitions(i).stackable;
	}

	public static boolean updateName(int itemId, String newValue) {
		try {
		ItemDefinitions defs = ItemDefinitions.getItemDefinitions(itemId);
		defs.name = newValue;
		defs.write();
		updateRefTable();
		return true;
		}
		catch(Exception ex){
			return false;
		}
		
	}
	
	public static void updateRefTable()
	{
		Cache.getStore().getIndexes()[19].rewriteTable();
	}

}
