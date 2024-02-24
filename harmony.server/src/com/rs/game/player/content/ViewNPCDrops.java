package com.rs.game.player.content;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.cache.loaders.NPCDefinitions;
import com.rs.game.item.Item;
import com.rs.game.npc.Drop;
import com.rs.game.player.Player;
import com.rs.utils.NPCDrops;
import com.rs.utils.Utils;

/**
 * @author Jake Displays a list of all the NPC's item drops
 */
public class ViewNPCDrops {

	/**
	 * Displays the interface
	 */
	public static void sendInterface(Player player, int npcId) {
		Drop[] drops = getSortedList(player, NPCDrops.getDrops(npcId));

		if (drops == null) {
			player.out("The " + NPCDefinitions.getNPCDefinitions(npcId).name + " doesn't have any drops to offer.");
			return;
		}

		player.getInterfaceManager().sendInterface(671);
		player.getPackets().sendIComponentText(671, 14, "Drops for " + NPCDefinitions.getNPCDefinitions(npcId).name + ".");

		player.getNpcDrops().clear();

		for (int i = 0; i < drops.length; i++) {
			if (i > 30) // Maximum of 30 items on the interface
				break;
			player.getNpcDrops().add(new Item(drops[i].getItemId(), ItemDefinitions.getItemDefinitions(drops[i].getItemId()).isStackable() ? drops[i].getMaxAmount() : 1));
		}
		player.getPackets().sendItems(530, player.getNpcDrops());

		player.getPackets().sendUnlockIComponentOptionSlots(671, 27, 0, 530, 0, 1, 2);
		player.getPackets().sendInterSetItemsOptionsScript(671, 27, 530, 6, 5, "View", "Price Check", "Examine");

		player.getTemporaryAttributtes().put("drops", npcId);

		player.setCloseInterfacesEvent(new Runnable() {
			@Override
			public void run() {
				player.getTemporaryAttributtes().remove("drops");
			}
		});
	}

	/**
	 * Contains all the different sorting options
	 */
	private static Object[] sortTypes = new Object[] { "highest drop rate", "lowest drop rate", "highest market value", "lowest market value" };

	/**
	 * Modifies the sorting orders for the drops
	 */
	public static void changeSortType(Player player, int npcId) {
		if (player.getDropSortingType() == 3)
			player.setDropSortingType(0);
		else
			player.setDropSortingType(player.getDropSortingType() + 1);

		player.getDialogueManager().startDialogue("ItemMessage", "You are now sorting by <col=FF0000>" + sortTypes[player.getDropSortingType()] + "</col>.");
		sendInterface(player, npcId);
	}

	/**
	 * Sorts the drop list
	 */
	private static Drop[] getSortedList(Player player, Drop[] drops) {
		/*
		 * Populate DropList
		 */
		List<Drop> dropList = new ArrayList<Drop>();
		for (int i = 0; i < drops.length; i++) {
			dropList.add(drops[i]);
		}
		switch (player.getDropSortingType()) {
		case 0: // Sort Drop List by highest drop rate
			Collections.sort(dropList, (s1, s2) -> Double.compare(s2.getRate(), s1.getRate()));
			break;
		case 1: // Sort Drop List by lowest drop rate
			Collections.sort(dropList, (s1, s2) -> Double.compare(s1.getRate(), s2.getRate()));
			break;
		case 2: // Sort by highest market value
			Collections.sort(dropList, (s1, s2) -> Integer.compare(ItemDefinitions.getItemDefinitions(s2.getItemId()).getValue(), ItemDefinitions.getItemDefinitions(s1.getItemId()).getValue()));
			break;
		case 3: // Sort by lowest market value
			Collections.sort(dropList, (s1, s2) -> Integer.compare(ItemDefinitions.getItemDefinitions(s1.getItemId()).getValue(), ItemDefinitions.getItemDefinitions(s2.getItemId()).getValue()));
			break;
		}

		return dropList.toArray(drops);
	}

	/**
	 * View Item Option Displays Items Drop Rate Percentage
	 */
	public static void sendDropRateMessage(Player player, Item item, int npcId, int slotId) {
		player.getDialogueManager().startDialogue("ItemMessage", "<col=FF0000>" + item.getDefinitions().getName() + "</col> has a <col=FF0000>" + getDropRate(item.getId(), npcId) + "%</col> chance of being dropped by the " + NPCDefinitions.getNPCDefinitions(npcId).name + ".");
	}

	/**
	 * Sends Price Check
	 */
	public static void sendPriceCheckMessage(Player player, Item item) {
		int value = item.getDefinitions().getValue();
		player.getDialogueManager().startDialogue("ItemMessage", "The " + item.getDefinitions().getName() + " has a market value of <col=FF0000>" + Utils.format(value) + "</col> coins.", 946);
	}

	/**
	 * Returns the drop rate of an item
	 */
	public static double getDropRate(int itemId, int npcId) {
		Drop[] drops = NPCDrops.getDrops(npcId);

		for (int i = 0; i < drops.length; i++) {
			if (drops[i].getItemId() == itemId) {
				return drops[i].getRate();
			}
		}
		return 0;
	}

}