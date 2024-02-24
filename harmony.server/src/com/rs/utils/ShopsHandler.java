package com.rs.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.util.HashMap;

import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.game.player.content.Shop;

public class ShopsHandler {

	private static final HashMap<Integer, Shop> handledShops = new HashMap<Integer, Shop>();

	private static final String PACKED_PATH = "data/items/packedShops.s";
	private static final String UNPACKED_PATH = "data/items/unpackedShops.txt";

	public static void init() {
		if (new File(PACKED_PATH).exists()) {
			loadPackedShops();
		} else {
			loadUnpackedShops();
		}
	}

	public static void loadUnpackedShops() {
		Logger.log("ShopsHandler", "Packing shops...");
		try {
			BufferedReader in = new BufferedReader(
					new FileReader(UNPACKED_PATH));
			DataOutputStream out = new DataOutputStream(new FileOutputStream(
					PACKED_PATH));
			while (true) {
				String line = in.readLine();
				if (line == null) {
					break;
				}
				if (line.startsWith("//")) {
					continue;
				}
				String[] splitedLine = line.split(" - ", 3);
				if (splitedLine.length != 3) {
					throw new RuntimeException("Invalid list for shop line: "
							+ line);
				}
				String[] splitedInform = splitedLine[0].split(" ", 3);
				if (splitedInform.length != 3) {
					throw new RuntimeException("Invalid list for shop line: "
							+ line);
				}
				String[] splitedItems = splitedLine[2].split(" ");
				int key = Integer.valueOf(splitedInform[0]);
				int money = Integer.valueOf(splitedInform[1]);
				boolean generalStore = Boolean.valueOf(splitedInform[2]);
				Item[] items = new Item[splitedItems.length / 2];
				int count = 0;
				for (int i = 0; i < items.length; i++) {
					items[i] = new Item(Integer.valueOf(splitedItems[count++]),
							Integer.valueOf(splitedItems[count++]), true);
				}
				out.writeInt(key);
				writeAlexString(out, splitedLine[1]);
				out.writeShort(money);
				out.writeBoolean(generalStore);
				out.writeByte(items.length);
				for (Item item : items) {
					out.writeShort(item.getId());
					out.writeInt(item.getAmount());
				}
				addShop(key, new Shop(splitedLine[1], money, items,
						generalStore));
			}
			in.close();
			out.close();
		} catch (Throwable e) {
			Logger.handle(e);
		}
	}

	private static void loadPackedShops() {
		try {
			RandomAccessFile in = new RandomAccessFile(PACKED_PATH, "r");
			FileChannel channel = in.getChannel();
			ByteBuffer buffer = channel.map(MapMode.READ_ONLY, 0,
					channel.size());
			while (buffer.hasRemaining()) {
				int key = buffer.getInt();
				String name = readAlexString(buffer);
				int money = buffer.getShort() & 0xffff;
				boolean generalStore = buffer.get() == 1;
				Item[] items = new Item[buffer.get() & 0xff];
				for (int i = 0; i < items.length; i++) {
					items[i] = new Item(buffer.getShort() & 0xffff,
							buffer.getInt(), true);
				}
				addShop(key, new Shop(name, money, items, generalStore));
			}
			channel.close();
			in.close();
		} catch (Throwable e) {
			Logger.handle(e);
		}
	}

	public static String readAlexString(ByteBuffer buffer) {
		int count = buffer.get() & 0xfff;
		byte[] bytes = new byte[count];
		buffer.get(bytes, 0, count);
		return new String(bytes);
	}

	public static void writeAlexString(DataOutputStream out, String string)
			throws IOException {
		byte[] bytes = string.getBytes();
		out.writeByte(bytes.length);
		out.write(bytes);
	}

	public static void restoreShops() {
		for (Shop shop : handledShops.values()) {
			shop.restoreItems();
		}
	}

	public static boolean openShop(Player player, int key) {
		Shop shop = getShop(key);
		if ( key == 105 || key == 93 && player.ispvpmode != true) {
			player.sendMessage("Sorry but you don't need to access this shop");
			return false;
		}
		if (player.isIronman() && key != 96 && key != 134 && key != 135 && key != 115 && key != 130 && key != 133 && key != 132 && key != 131 && key != 123  && key != 128 && key != 127 && key != 125 && key != 126 && key != 97 && key != 98 && key != 99 && key != 100 && key != 101 && key != 117 && key != 122 && key != 119 && key != 113 && key != 116 && key != 115 && key != 17 && key != 10 && key != 114 && key != 110 && key != 109 && key != 108 && key != 103 && key != 106 && key != 107 && key != 28 && key != 94 && key != 102 && key != 104 && key != 31 && key != 11 && key != 12 && key != 33 && key != 16 && key != 27 && key != 30) {
			player.sendMessage("Sorry but ironmen can only use basic supply stores.");
			return false;
		}
		if (key == 116 || key == 102) {
			if (!player.isIronman()) {
			player.sendMessage("Sorry but this store is for ironmen only!");
			return false;
		}
		}
		if (player.isPvpMode() && key != 8 && key != 105 && key != 28 && key != 30 && key != 32 && key != 93) {
			player.sendMessage("Sorry but you don't need to access this shop");
			return false;
	}
		if (shop == null) {
			return false;
		}
		shop.addPlayer(player);
		return true;
	}

	public static Shop getShop(int key) {
		return handledShops.get(key);
	}

	public static void addShop(int key, Shop shop) {
		handledShops.put(key, shop);
	}
}
