package com.rs.net.decoders;

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

public class GetRekt {
	
	private static final File file = new File("data/Serfiles/BannedMacs.ser");
	private static String[] bannedMacs;
	
	public static void init() {
		try {
			if (file.exists()) {
				bannedMacs = loadFile();
				return;
			}
			bannedMacs = new String[1000];
			storeFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static boolean addMacBan(String address) {
		if (isMacBanned(address))
			return false;
		for (int i = 0; i < bannedMacs.length; i++) {
			if (bannedMacs[i] == null) {
				bannedMacs[i] = address;
				storeFile();
				break;
			}
		}
		return false;
	}
	
	public static boolean removeMac(String address) {
		for (int i = 0; i < bannedMacs.length; i++) {
			if (bannedMacs[i] != null && bannedMacs[i].equalsIgnoreCase(address)) {
				bannedMacs[i] = null;
				storeFile();
				return true;
 			}
		}
		return false;
	}
	
	public static boolean isMacBanned(String address) {
		for (String s : bannedMacs) {
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
			out.writeObject(bannedMacs);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
