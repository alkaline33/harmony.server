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

public class HasVotedOnPoll {
	
/**
 * 
 * Author Mr Joopz
 * 
 */
	
	private static final File file = new File("data/serfiles/hasvotedpoll.ser");
	private static String[] votedpoll;
	
	public static void init() {
		try {
			if (file.exists()) {
				votedpoll = loadFile();
				return;
			}
			votedpoll = new String[1000];
			storeFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static boolean addPolled(String address) {
		if (HasPolled(address))
			return false;
		for (int i = 0; i < votedpoll.length; i++) {
			if (votedpoll[i] == null) {
				votedpoll[i] = address;
				storeFile();
				break;
			}
		}
		return false;
	}
	
	public static boolean removePoll(String address) {
		for (int i = 0; i < votedpoll.length; i++) {
			if (votedpoll[i] != null && votedpoll[i].equalsIgnoreCase(address)) {
				votedpoll[i] = null;
				storeFile();
				return true;
 			}
		}
		return false;
	}
	
	public static boolean HasPolled(String address) {
		for (String s : votedpoll) {
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
			out.writeObject(votedpoll);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
