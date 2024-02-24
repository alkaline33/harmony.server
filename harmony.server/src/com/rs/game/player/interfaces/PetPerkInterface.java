package com.rs.game.player.interfaces;

import java.util.Objects;

import com.rs.game.item.Item;
import com.rs.game.player.Player;

/**
 * @author Connor
 */
public class PetPerkInterface extends RSInterface {

	// The player
	private Player player;

	// Container key for items container
	private static final int containerKey = 100;

	// Interface text
	private static final String title = "Pet Perks", bank = "Bank All", take = "Take All", abandon = "Abandon All";

	// Constructor
	public PetPerkInterface(Player player) {
		this.player = player;
	}

	@Override
	public void open() {
		player.getPackets().sendIComponentText(getId(), 28, title);
		player.getPackets().sendIComponentText(getId(), 43, take);
		player.getPackets().sendIComponentText(getId(), 42, abandon);
		player.getPackets().sendIComponentText(getId(), 4, bank);
		refreshContainer();
	}

	@Override
	public boolean onClick(int button, int packet, int itemId, int slotId) {
		Item item = player.getPetPerk().getContainer().get(slotId);
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
			player.getPetPerk().depositChestToBank();
			refreshContainer();
			break;

		case 9: // Abandon all
			// player.getDialogueManager().startDialogue("SophanemConfirmation"); // Confirm
			// destroy for items
			refreshContainer();
			break;

		case 10: // Take all
			if (player.getInventory().getFreeSlots() < 3) {
				player.sendMessage("You do not have enough inventory space to do this!");
				return false;
			}
			for (Item i : player.getPetPerk().getContainer().getItemsCopy()) {
				if (Objects.isNull(i)) {
					continue;
				}
				if (player.getInventory().addItem(i)) {
					player.getPetPerk().getContainer().remove(i);
				} else {
					player.getPetPerk().getContainer().remove(new Item(i.getId(), freeSlots));
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
		player.getPetPerk().getContainer().shift();
		player.getPackets().sendItems(containerKey, player.getPetPerk().getContainer());
	}

}
