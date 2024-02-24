package com.rs.tools;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import com.rs.Settings;
import com.rs.cache.Cache;
import com.rs.game.EntityList;
import com.rs.game.World;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.utils.SerializableFilesManager;
import com.rs.utils.Utils;
import com.rs.game.player.content.AdventurersLog;
import com.rs.game.player.content.ItemFinderNews;
import com.rs.game.player.content.ItemSearch;

public class ItemFinder {


	public static void main(String[] args) {
		try {
			Cache.init();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Enter Item ID:");
		Scanner in = new Scanner(System.in);
		int itemID = in.nextInt();
		Item i = new Item(itemID);
		String itemName = i.getDefinitions().getName();
		System.out.println("Searching for item: "+itemName);
		File[] chars = new File("data/characters").listFiles();
		EntityList<Player> p1 = World.getPlayers();
		long counter =0;
		for (File acc : chars) {
				try {
					Player player = (Player) SerializableFilesManager
							.loadSerializedFile(acc);
					try {
						for (int id = 0; id < 30000; id++) {
							try {
								Item item = player.getBank().getItem(id);
								if (item == null)
									continue;
								//if (player.getRights() == 2)
								//	continue;
							
								if (id == itemID){
									//player.getBank().removeItem(29973);
									player.getBank().removeItem(player.getBank().getItemSlot(itemID), item.getAmount(), false ,false);
									//player.getBank().addItem(1050, item.getAmount(), true);2997
									counter += item.getAmount();
									System.out.println(acc.getName() + " has "+Utils.format(item.getAmount())+" :"+itemName);
								}
							} catch(Exception k) {
								k.printStackTrace();
							}
						}
					} catch(Exception e) {
						e.printStackTrace();
					}
					try {
						for (Item item : player.getInventory().getItems().getItems()) {
							if (item == null)
								continue;
							if (item.getId() == itemID) {
								counter += item.getAmount();
								System.out.println(acc.getName() + " has "+Utils.format(item.getAmount())+" :"+itemName);
							}
						}
					} catch(Exception e) {
						
					}
					try {
						for (Item item : player.getEquipment().getItems().getItems()) {
							if (item == null)
								continue;
							if (item.getId() == itemID){
								counter += item.getAmount();
								System.out.println(acc.getName() + " has "+Utils.format(item.getAmount())+" :"+itemName);	
							}
						}
					} catch(Exception e) {
						
					}
					
				} catch (Throwable e) {
				}
			}
		System.out.println("Done searching for item: "+itemName);
		//}
		System.out.println("There are " +Utils.format(counter) +" " +itemName+ "s ingame");
	}
}