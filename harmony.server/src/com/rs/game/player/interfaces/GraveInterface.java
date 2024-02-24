package com.rs.game.player.interfaces;

import java.util.Objects;

import com.rs.Settings;
import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.utils.Colors;
import com.rs.utils.Utils;

/**
 * @author Connor
 */
public class GraveInterface extends RSInterface {

	// The player
	private Player player;

	// Container key for items container
	private static final int containerKey = 100;

	// Interface text
	private static final String title = "Grave Contents", bank = "Bank All", take = "Take All", abandon = "Abandon All";

	// Constructor
	public GraveInterface(Player player) {
		this.player = player;
	}

	// int claimcost =
	// ItemDefinitions.getItemDefinitions(player.getGrave().getContainer().)

	@Override
	public void open() {
		player.getPackets().sendIComponentText(getId(), 28, title);
		player.getPackets().sendIComponentText(getId(), 43, take);
		player.getPackets().sendIComponentText(getId(), 42, abandon);
		player.getPackets().sendIComponentText(getId(), 4, bank);
		refreshContainer();
		if (player.getGrave().getContainer().getUsedSlots() > 28) {
			player.sendMessage(Colors.red + "You currently have more than 28 slots used in your grave. This means you cannot see all of your items.");
		}
	}

	@Override
	public boolean onClick(int button, int packet, int itemId, int slotId) {
		Item item = player.getGrave().getContainer().get(slotId);
		int freeSlots = player.getInventory().getFreeSlots();
		switch (button) {

		case 7: // Click item
			switch (packet) {

			case 5: // Discard
				player.sendMessage("Please use bank all or take all.");
				break;

			case 14: // Take
				player.sendMessage("Please use bank all or take all.");
				break;

			case 55: // Examine
				// player.sendMessage(ItemExamines.getExamine(item));
				break;

			case 67: // Bank
				player.sendMessage("Please use bank all or take all.");
				break;
			}
			break;

		case 8: // Bank all
			if (player.playdays < 1) {
				player.getGrave().depositChestToBank();
				refreshContainer();
				player.sendMessage(Colors.green+"As you're a new player, your items have been placed in your bank, free of charge.");
				return false;
			} else {
			if (player.getMoneyPouch().getCoinAmount() < player.getGrave().checkValue() / 20) {
				player.sendMessage(Colors.red + "You don't have enough gold to do this.");
				return false;
			}
			player.coinamount -= player.getGrave().checkValue() / 20;
			Settings.GpSyncAmount += player.getGrave().checkValue() / 20;
			player.refreshMoneyPouch();
			player.getGrave().depositChestToBank();
			refreshContainer();
			}
			break;

		case 9: // Abandon all
			// player.getDialogueManager().startDialogue("SophanemConfirmation"); // Confirm
			// destroy for items
			refreshContainer();
			break;

		case 10: // Take all
			if (player.playdays < 1) {
				player.getGrave().depositChestToBank();
				refreshContainer();
				player.sendMessage(Colors.green+"As you're a new player, your items have been placed in your bank, free of charge.");
				return false;
			}
			if (player.getMoneyPouch().getCoinAmount() < player.getGrave().checkValue() / 20) {
				player.sendMessage(Colors.red + "You need to have " + Utils.format(player.getGrave().checkValue() / 20) + " coins in your money pouch to pay your claim fee.");
				return false;
			}
			if (player.getGrave().getContainer().getUsedSlots() > player.getInventory().getFreeSlots()) {
				player.sendMessage("Please use the bank all option, you do not have enough inventory space!");
				return false;
			}
			player.coinamount -= player.getGrave().checkValue() / 20;
			Settings.GpSyncAmount += player.getGrave().checkValue() / 20;
			player.refreshMoneyPouch();
			for (Item i : player.getGrave().getContainer().getItemsCopy()) {
				if (Objects.isNull(i))
					continue;
				if (!ItemDefinitions.getItemDefinitions(i.getId()).isStackable() && player.getInventory().getFreeSlots() < player.getGrave().getContainer().getNumberOf(i.getId()))
					continue;
				if (ItemDefinitions.getItemDefinitions(i.getId()).isStackable() && !player.getInventory().hasFreeSlots())
					continue;

				if (player.getInventory().addItem(i)) {
					player.getGrave().getContainer().remove(i);
				} else {
					player.getGrave().getContainer().remove(new Item(i.getId(), freeSlots));
				}
			}
			refreshContainer();
			break;
		}
		return true;
	}

	@Override
	public void onClose() {

	}

	@Override
	public int getId() {
		return 1284;
	}

	/**
	 * Refreshes the container and shifts all the items together.
	 */
	private void refreshContainer() {
		player.getPackets().sendInterSetItemsOptionsScript(getId(), 7, 100, 8, 3, "Take", "Bank", "Discard", "Examine");
		player.getPackets().sendUnlockIComponentOptionSlots(getId(), 7, 0, 10, 0, 1, 2, 3);
		player.getGrave().getContainer().shift();
		player.getPackets().sendItems(containerKey, player.getGrave().getContainer());
	}

}
