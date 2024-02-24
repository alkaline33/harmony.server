package com.rs.game.player.content.raids;

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

public class HasRaidedTrio {
	
/**
 * 
 * Author Mr Joopz
 * 
 */
	
	private static final File file = new File("data/serfiles/hasraidedtrio.ser");
	private static String[] raidedMacs;
	
	public static void init() {
		try {
			if (file.exists()) {
				raidedMacs = loadFile();
				return;
			}
			raidedMacs = new String[1000];
			storeFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static boolean addMacRaid(String address) {
		if (HasRaided(address))
			return false;
		for (int i = 0; i < raidedMacs.length; i++) {
			if (raidedMacs[i] == null) {
				raidedMacs[i] = address;
				storeFile();
				break;
			}
		}
		return false;
	}
	
	public static boolean removeRaidMac(String address) {
		for (int i = 0; i < raidedMacs.length; i++) {
			if (raidedMacs[i] != null && raidedMacs[i].equalsIgnoreCase(address)) {
				raidedMacs[i] = null;
				storeFile();
				return true;
 			}
		}
		return false;
	}
	
	public static boolean HasRaided(String address) {
		for (String s : raidedMacs) {
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
			out.writeObject(raidedMacs);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
