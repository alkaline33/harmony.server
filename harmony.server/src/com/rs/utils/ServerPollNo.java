package com.rs.utils;

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

public class ServerPollNo {
	
/**
 * 
 * Author Mr Joopz
 * 
 */
	
	private static final File file = new File("data/serfiles/pollno.ser");
	private static int[] no;
	
	public static void init() {
		try {
			if (file.exists()) {
				no = loadFile();
				return;
			}
			no = new int[1000];
			storeFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static boolean addPolled(int address) {
		if (HasPolled(address))
			return false;
		for (int i = 0; i < no.length; i++) {
			if (no[i] == 0) {
				no[i] = address;
				storeFile();
				break;
			}
		}
		return false;
	}
	
	public static boolean removePoll(String address) {
		for (int i = 0; i < no.length; i++) {
			if (no[i] != 0) {
				no[i] = 0;
				storeFile();
				return true;
 			}
		}
		return false;
	}
	
	public static boolean HasPolled(int address) {
		for (int s : no) {
			if (s!= 0)
				return true;
		}
		return false;
	}
	
	
	@SuppressWarnings("unchecked")
	public static final int[]  loadFile() {
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
			return (int[]) in.readObject();
		} catch (Exception e) {
			return null;
		}
	}
	
	public static final void storeFile() {
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
			out.writeObject(no);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
