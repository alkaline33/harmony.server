package com.rs.utils;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.util.*;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.rs.game.npc.Drop;

public class NPCDrops {
	private final static String UNPACKED_PATH = "data/npcs/drops/";
	static String PACKED_PATH = "data/npcs/packedDrops.d";

	private static HashMap<Integer, Drop[]> npcDrops;

	public static void init() {
		loadPackedNPCDrops();
	}


	public static void initPacked() {
		loadPackeddDrops();
	}
	public static Drop[] getDrops(int npcId) {
		return npcDrops.get(npcId);
	}

	private Map<Integer, ArrayList<Drop>> dropMapx = null;

	public Map<Integer, ArrayList<Drop>> getDropArray() {

		if (dropMapx == null)
			dropMapx = new LinkedHashMap<>();
		for (int i : npcDrops.keySet()) {
			ArrayList<Drop> temp = new ArrayList<Drop>();
			Collections.addAll(temp, npcDrops.get(i));
			dropMapx.put(i, temp);
		}

		return dropMapx;
	}

	public void insertDrop(int npcID, Drop d) {
		loadPackedNPCDrops();
		Drop[] oldDrop = npcDrops.get(npcID);
		if (oldDrop == null) {
			npcDrops.put(npcID, new Drop[] { d });
		} else {
			int length = oldDrop.length;
			Drop[] destination = new Drop[length + 1];
			System.arraycopy(oldDrop, 0, destination, 0, length);
			destination[length] = d;
			npcDrops.put(npcID, destination);
		}
	}

	private static void loadPackedNPCDrops() {
		npcDrops = new HashMap<>(Objects.requireNonNull(new File(UNPACKED_PATH).listFiles()).length);
		HashMap<Integer, HashMap<String, String>> settings = new HashMap<>();
		for (File file : Objects.requireNonNull(new File(UNPACKED_PATH).listFiles())) {
			try (FileReader fileReader = new FileReader(file)) {

				JsonObject reader = new JsonParser().parse(fileReader).getAsJsonObject();

				Set<Map.Entry<String, JsonElement>> entrySet = reader.entrySet();
				settings.put(Integer.parseInt(file.getName().replace(".json", "")), new HashMap<>());
				for (Map.Entry<String, JsonElement> entry : entrySet) {
					settings.get(Integer.parseInt(file.getName().replace(".json", ""))).put(entry.getKey(), reader.get(entry.getKey()).toString().replace("\"", ""));
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		for (Map.Entry<Integer, HashMap<String, String>> mods : settings.entrySet()) {
			int npcId = mods.getKey();
			List<Drop> dropList = new ArrayList<>(mods.getValue().entrySet().size());
			for (Map.Entry<String, String> drops : mods.getValue().entrySet()) {
				String[] drop = drops.getValue().split(",");
				int itemId = Integer.parseInt(drops.getKey());
				double rate = Double.parseDouble(drop[0]);
				int minAmount = Integer.parseInt(drop[1]);
				int maxAmount = Integer.parseInt(drop[2]);
				boolean rare = Boolean.parseBoolean(drop[3]);
				Drop d = new Drop(itemId, rate, minAmount, maxAmount, rare);
				dropList.add(d);
			}
			npcDrops.put(npcId, dropList.toArray(new Drop[0]));
		}
	}

	private static void loadPackeddDrops(){
		try {
			RandomAccessFile in = new RandomAccessFile(PACKED_PATH, "r");
			FileChannel channel = in.getChannel();
			ByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0,
					channel.size());
			int dropSize = buffer.getShort() & 0xffff;
			npcDrops = new HashMap<>(dropSize);
			for (int i = 0; i < dropSize; i++) {
				int npcId = buffer.getShort() & 0xffff;
				Drop[] drops = new Drop[buffer.getShort() & 0xffff];
				for (int d = 0; d < drops.length; d++) {
					if (buffer.get() == 0)
						drops[d] = new Drop(buffer.getShort() & 0xffff,
								buffer.getDouble(), buffer.getInt(),
								buffer.getInt(), false);
					else
						drops[d] = new Drop(0, 0, 0, 0, true);

				}
				npcDrops.put(npcId, drops);
			}
			channel.close();
			in.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public HashMap<Integer, Drop[]> getDropMap() {
		return npcDrops;
	}
}