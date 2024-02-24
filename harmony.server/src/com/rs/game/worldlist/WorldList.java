package com.rs.game.worldlist;

import java.util.HashMap;

public class WorldList {

	public static final HashMap<Integer, WorldEntry> WORLDS = new HashMap<Integer, WorldEntry>();

	//String activity, String ip, int countryId, String countryName, boolean members
	public static void init() {
		WORLDS.put(1, new WorldEntry("Yo", "127.0.0.1", 38, "Canada", true));
		WORLDS.put(2, new WorldEntry("Hoe Free", "127.0.0.1", 161, "Netherlands", false));
	}

	public static WorldEntry getWorld(int worldId) {
		return WORLDS.get(worldId);
	}

}