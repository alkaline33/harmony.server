package com.rs.game.minigames;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.rs.game.item.Item;
import com.rs.game.item.ItemsContainer;
import com.rs.game.player.Player;
import com.rs.utils.Utils;

/**
 * Represents the Queen Black Dragon.
 * @author Emperor
 *
 */
public final class GoldChest {

	/**
	 * The rewards (average ratio: 28,5).
	 */
	
	private transient Player attacker;
	
	private static final int[][] REWARDS = {
		{1149, 1, 1, 57}, //Dragon helm
		{1305, 1, 1, 29}, //Dragon longsword
		{1215, 1, 1, 32}, //Dragon dagger
		{1249, 1, 1, 17}, //Dragon spear
		{24365, 1, 1, 12}, //Dragon kiteshield
		{560, 500, 500, 31}, //Death rune
		{565, 500, 500, 30}, //Blood rune
		{566, 20, 100, 21}, //Soul rune
		{9342, 150, 150, 12}, //Onyx bolts
		{7936, 1480, 3500, 16}, //Pure essence
		{453, 300, 580, 18}, //Coal
		{449, 50, 50, 16}, //Adamantite 
		{451, 30, 100, 13}, //Runite ore
		{5316, 1, 1, 21}, //Magic seed
		{5300, 5, 5, 18}, //Snapdragon seed
		{5321, 3, 3, 26}, //Watermelon seed
		{5304, 1, 5, 8}, //Torstol seed
		{371, 200, 200, 24}, //Raw swordfish
		{15272, 1, 9, 53}, //Rocktail
		{6689, 1, 10, 57}, //Saradomin brew (2)
		{3028, 1, 10, 34}, //Super restore (2)
		{995, 3000, 200000, 35}, //Coins
		{1513, 90, 120, 38}, //Magic logs
		{1515, 150, 500, 53}, //Yew logs
		{219, 10, 10, 19}, //Grimy torstol
		{2485, 50, 50, 18}, //Grimy lantadyme
		{1631, 1, 9, 35}, //Uncut dragonstone
		{2366, 1, 1, 21}, //Shield left half
		{9194, 30, 30, 17}, //Onyx bolt tips
		{24346, 1, 1, 20}, //Royal torsion spring
		{24344, 1, 1, 20}, //Royal sight
		{24342, 1, 1, 20}, //Royal frame
		{24340, 1, 1, 20}, //Royal bolt stabiliser
		{24352, 1, 1, 5}, //Dragonbone upgrade kit
		{11286, 1, 1, 4}, //Draconic visage current: 850
	};

	/**
	 * The rewards container.
	 */
	private final static ItemsContainer<Item> rewards = new ItemsContainer<>(10, true);

	/**
	 * The last amount of hitpoints.
	 */

	/**
	 * Opens the reward chest.
	 * @param replace If the chest should be replaced with an opened one.
	 */
	public void openGoldChest() {
		attacker.getInterfaceManager().sendInterface(1284);
		attacker.getPackets().sendInterSetItemsOptionsScript(1284, 7, 100, 8, 3, "Take", "Bank", "Discard", "Examine");
		attacker.getPackets().sendUnlockIComponentOptionSlots(1284, 7, 0, 10, 0, 1, 2, 3);
		prepareRewards();
		attacker.getPackets().sendItems(100, rewards);
	}

	/**
	 * Prepares the rewards.
	 */
	public static void prepareRewards() {
		rewards.add(new Item(536, 5));
		rewards.add(new Item(24374, 1 + Utils.random(6)));
		rewards.add(new Item(24336, 50 + Utils.random(51)));
		List<Item> rewardTable = new ArrayList<Item>();
		for (int[] reward : REWARDS) {
			Item item = new Item(reward[0], reward[1] + Utils.random(reward[2] - reward[1]));
			for (int i = 0; i < reward[3]; i++) {
				rewardTable.add(item);
			}
		}
		Collections.shuffle(rewardTable);
		for (int i = 0; i < 1 + Utils.random(3); i++) {
			rewards.add(rewardTable.get(Utils.random(rewardTable.size())));
		}
	}

	/**
	 * Gets the rewards.
	 * @return The rewards.
	 */
	public ItemsContainer<Item> getRewards() {
		return rewards;
	}

}