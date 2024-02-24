package com.rs.game.player.content;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

public class hasClaimedSanta {
	
/**
 * 
 * Author Mr Joopz
 * 
 */
	
	private static final File file = new File("data/serfiles/hasclaimedsanta.ser");
	private static String[] claimedSanta;
	
	public static void init() {
		try {
			if (file.exists()) {
				claimedSanta = loadFile();
				return;
			}
			claimedSanta = new String[1000];
			storeFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static boolean addClaimSanta(String address) {
		if (HasClaimed(address))
			return false;
		for (int i = 0; i < claimedSanta.length; i++) {
			if (claimedSanta[i] == null) {
				claimedSanta[i] = address;
				storeFile();
				break;
			}
		}
		return false;
	}
	
	public static boolean removeClaim(String address) {
		for (int i = 0; i < claimedSanta.length; i++) {
			if (claimedSanta[i] != null && claimedSanta[i].equalsIgnoreCase(address)) {
				claimedSanta[i] = null;
				storeFile();
				return true;
 			}
		}
		return false;
	}
	
	public static boolean HasClaimed(String address) {
		for (String s : claimedSanta) {
			if (s!= null && s.equalsIgnoreCase(address))
				return true;
		}
		return false;
	}
	
	
	@SuppressWarnings("unchecked")
	public static final String[]  loadFile() {
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
			return (String[]) in.readObject();
		} catch (Exception e) {
			return null;
		}
	}
	
	public static final void storeFile() {
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
			out.writeObject(claimedSanta);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
