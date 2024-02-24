package com.rs.tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.rs.cache.Cache;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.utils.SerializableFilesManager;
import com.rs.utils.Utils;

public class PhatChecker {

    public static String[] SEARCH = { "glacey"};
    
    int totalblue = 0;
    int totalyellow = 0;
    int totalgreen = 0;
    int totalwhite = 0;
    int totalpurple = 0;
    int totalred = 0;

    public static int bluepartyhat, yellowpartyhat, greenpartyhat,
	    whitepartyhat, purplepartyhat, redpartyhat;

    public static void main(String[] args) {
	try {
	    Cache.init();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	System.out.println("Processing.");
	File[] chars = new File("data/playersaves/charactersphat/").listFiles();
	for (File acc : chars) {
	    if (Utils.invalidAccountName(acc.getName().replace(".p", ""))) {
		acc.delete();
		continue;
	    }
	    try {
		Player player = (Player) SerializableFilesManager
			.loadSerializedFile(acc);
		for (int id = 0; id < Utils.getItemDefinitionsSize(); id++) {
		    Item item = player.getBank().getItem(id);
		    if (item == null)
			continue;
		    String name = item.getDefinitions().getName().toLowerCase();
		    for (String string : SEARCH) {
			if (name.equalsIgnoreCase(string)) {
			    if (string.contains("glacey")) {
				redpartyhat += item.getAmount();
			    }
			    if (string.contains("blue")) {
				bluepartyhat += item.getAmount();
			    }
			    if (string.contains("yellow")) {
				yellowpartyhat += item.getAmount();
			    }
			    if (string.contains("purple")) {
				purplepartyhat += item.getAmount();
			    }
			    if (string.contains("white")) {
				whitepartyhat += item.getAmount();
			    }
			    if (string.contains("green")) {
				greenpartyhat += item.getAmount();
			    }
			}
		    }
		}
		for (Item item : player.getInventory().getItems().getItems()) {
		    if (item == null)
			continue;
		    if (item != null) {
			for (String string : SEARCH) {
			    if (item.getDefinitions().getName().toLowerCase()
				    .equalsIgnoreCase(string)) {
				if (string.contains("glacey")) {
				    redpartyhat += item.getAmount();
				}
				if (string.contains("blue")) {
				    bluepartyhat += item.getAmount();
				}
				if (string.contains("yellow")) {
				    yellowpartyhat += item.getAmount();
				}
				if (string.contains("purple")) {
				    purplepartyhat += item.getAmount();
				}
				if (string.contains("white")) {
				    whitepartyhat += item.getAmount();
				}
				if (string.contains("green")) {
				    greenpartyhat += item.getAmount();
				}
			    }
			}
		    }
		}
		if (redpartyhat > 0 || bluepartyhat > 0 || yellowpartyhat > 0
			|| purplepartyhat > 0 || whitepartyhat > 0
			|| greenpartyhat > 0) {
		    System.out
			    .println("--------------------------------------------------------------------------------------");
		    System.out.println(acc.getName() + " has " + redpartyhat
			    + " glacey, " + bluepartyhat + " Blue, "
			    + yellowpartyhat + ", Yellow, " + purplepartyhat
			    + " Purple, " + whitepartyhat + " White, "
			    + greenpartyhat + " green.");
		    System.out
			    .println("--------------------------------------------------------------------------------------");
		    saveLog(acc);
		}
		/*
		redpartyhat = 0;
		bluepartyhat = 0;
		yellowpartyhat = 0;
		purplepartyhat = 0;
		whitepartyhat = 0;
		greenpartyhat = 0;
		*/
		SerializableFilesManager.storeSerializableClass(player, acc);
	    } catch (Throwable e) {
		acc.delete();
		System.out.println("failed to load the account: "
			+ acc.getName() + " it was deleted in the process.");
	    }
	}
	System.out.println("done");
    }

    public static void saveLog(File account) {
	// TODO Auto-generated method stub
	try {
	    String FILE_PATH = "data/playersaves/phatdump/";
	    //BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH + account.getName() + ".txt", true));
	  BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH + "phatdump.txt", true));
	    writer.write(account.getName() + " has " + redpartyhat + " Red, "
		    + bluepartyhat + " Blue, " + yellowpartyhat + " Yellow, "
		    + purplepartyhat + " Purple, " + whitepartyhat + " White, "
		    + greenpartyhat + " green.");
	    writer.newLine();
	    writer.flush();
	    writer.close();
	} catch (IOException er) {
	    System.out.println("Failed to save data to document.");
	}
    }

}