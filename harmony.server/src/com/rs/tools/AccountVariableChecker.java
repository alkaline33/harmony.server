package com.rs.tools;

import java.io.File;
import java.io.IOException;

import com.rs.cache.Cache;
import com.rs.game.EntityList;
import com.rs.game.World;
import com.rs.game.player.Player;
import com.rs.utils.SerializableFilesManager;
import com.rs.utils.Utils;

public class AccountVariableChecker {

	public static void main(String[] args) {
		try {
			Cache.init();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//System.out.println("Enter Item ID:");
		//Scanner in = new Scanner(System.in);
		// int variable = donatedamount;
		//Item i = new Item(itemID);
		//String itemName = i.getDefinitions().getName();
		System.out.println("Searching accounts...");
		File[] chars = new File("data/characters").listFiles();
		EntityList<Player> p1 = World.getPlayers();
		long counter =0;
		for (File acc : chars) {
				try {
					Player player = (Player) SerializableFilesManager
							.loadSerializedFile(acc);
					try {
					// if (player.donationvalue < 500)
					// continue;
					// System.out.println(acc.getName() + " has donated $" +
					// Utils.format(player.donationvalue) + " !");
					System.out.println(acc.getName() + " has " + Utils.format(player.money) + " coins in money pouch!");
					counter += player.getMoneyPouch().getCoinAmount();
					// counter += player.
					} catch(Exception e) {
						
					}
					
				} catch (Throwable e) {
				}
			}
		System.out.println("Done searching.");
		//}
		System.out.println("Final Total Result: " + Utils.format(counter) + "");
	}
}