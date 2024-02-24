package com.rs.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class YoutuberReferral2 {
	
/**
 * 
 * Author Mr Joopz
 * 
 */
	
	private static final File file = new File("data/serfiles/youtuberreferral2.ser");
	private static String[] claimedMacs;
	
	public static void init() {
		try {
			if (file.exists()) {
				claimedMacs = loadFile();
				return;
			}
			claimedMacs = new String[1000];
			storeFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static boolean addMacClaimed(String address) {
		if (HasClaimed(address)) {
			return false;
		}
		for (int i = 0; i < claimedMacs.length; i++) {
			if (claimedMacs[i] == null) {
				claimedMacs[i] = address;
				storeFile();
				break;
			}
		}
		return false;
	}
	
	public static boolean removeClaimedMac(String address) {
		for (int i = 0; i < claimedMacs.length; i++) {
			if (claimedMacs[i] != null && claimedMacs[i].equalsIgnoreCase(address)) {
				claimedMacs[i] = null;
				storeFile();
				return true;
 			}
		}
		return false;
	}
	
	public static boolean HasClaimed(String address) {
		for (String s : claimedMacs) {
			if (s!= null && s.equalsIgnoreCase(address)) {
				return true;
			}
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
			out.writeObject(claimedMacs);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
