package com.rs.game.player;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.World;
import com.rs.game.item.Item;
import com.rs.game.item.ItemsContainer;
import com.rs.game.player.content.ItemConstants;
import com.rs.net.decoders.WorldPacketsDecoder;
import com.rs.utils.ItemExamines;
import com.rs.utils.Utils;

/**
 * @author ARMAR X K1NG
 * 
 */
public class PlayerOwnedShop implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3420304334072174474L;

	public static int MAXIMUM_PLAYER_SHOP_ITEMS = 10;

	private transient Player player;

	private Map<Integer, Integer> storeItemsPrices;

	private ItemsContainer<Item> storeItems;

	private transient CopyOnWriteArrayList<Player> viewingPlayers;

	private boolean locked;

	public PlayerOwnedShop() {
		storeItems = new ItemsContainer<>(MAXIMUM_PLAYER_SHOP_ITEMS, true);
		storeItemsPrices = new HashMap<Integer, Integer>(MAXIMUM_PLAYER_SHOP_ITEMS);
	}

	public void openPlayerShop() {
		if (!World.isHomeArea(player)) {
			player.sendMessage("You can only do this whilst at home.");
			return;
		}
		
		player.getTemporaryAttributtes().put("POS", this);
		player.getInterfaceManager().sendInterface(1284);
		player.getInterfaceManager().sendInventoryInterface(628);
		player.getPackets().sendIComponentText(1284, 28, "My Shop");
		player.getPackets().sendIComponentText(1284, 46, "Shop is " + (locked ? "Locked" : "Unlocked"));
		player.getPackets().sendHideIComponent(1284, 8, true);
		player.getPackets().sendHideIComponent(1284, 9, true);
		player.getPackets().sendInterSetItemsOptionsScript(1284, 7, 100, 8, 3, "Value", "Edit Price", "Remove 1",
				"Remove 5", "Remove X", "Remove All", "Examine");
		player.getPackets().sendUnlockIComponentOptionSlots(1284, 7, 0, 10, 0, 1, 2, 3, 4, 5, 6);
		player.getPackets().sendIComponentSettings(628, 0, 0, 27, 1278);
		player.getPackets().sendUnlockIComponentOptionSlots(628, 0, 0, 27, 0, 1, 2, 3, 4, 5);
		player.getPackets().sendInterSetItemsOptionsScript(628, 0, 93, 4, 7, "Add 1", "Add 5", "Add 10", "Add X",
				"Add All", "Examine");
		player.getPackets().sendItems(100, storeItems);
		viewingPlayers.add(player);
		player.setCloseInterfacesEvent(new Runnable() {
			@Override
			public void run() {
				player.getTemporaryAttributtes().remove("POS");
				player.setCloseInterfacesEvent(null);
				player.closeInterfaces();
				viewingPlayers.remove(player);
			}
		});
	}

	public void showMyShopToPlayer(final Player customer) {
		customer.getTemporaryAttributtes().put("POS", this);
		customer.getInterfaceManager().sendInterface(1284);
		customer.getPackets().sendIComponentText(1284, 28, player.getDisplayName() + "'s Shop");
		customer.getPackets().sendHideIComponent(1284, 8, true);
		customer.getPackets().sendHideIComponent(1284, 9, true);
		customer.getPackets().sendHideIComponent(1284, 10, true);
		customer.getPackets().sendInterSetItemsOptionsScript(1284, 7, 100, 8, 3, "Value", "Buy 1", "Buy 5", "Buy X",
				"Buy All", "Examine");
		customer.getPackets().sendUnlockIComponentOptionSlots(1284, 7, 0, 10, 0, 1, 2, 3, 4, 5);
		customer.getPackets().sendItems(100, storeItems);
		viewingPlayers.add(customer);
		customer.setCloseInterfacesEvent(new Runnable() {
			@Override
			public void run() {
				customer.getTemporaryAttributtes().remove("POS");
				customer.setCloseInterfacesEvent(null);
				customer.closeInterfaces();
				viewingPlayers.remove(customer);
			}
		});
	}

	public boolean handleButtonClick(Player customer, int interfaceId, int componentId, int slotId, int slotId2,
			int packetId) {
		if (getPlayer().getUsername().equalsIgnoreCase(customer.getUsername())) {
			switch (interfaceId) {
			case 628:
				switch (packetId) {
				case WorldPacketsDecoder.ACTION_BUTTON1_PACKET:
				case WorldPacketsDecoder.ACTION_BUTTON2_PACKET:
				case WorldPacketsDecoder.ACTION_BUTTON3_PACKET:
				case WorldPacketsDecoder.ACTION_BUTTON5_PACKET:
					int amount = packetId == WorldPacketsDecoder.ACTION_BUTTON1_PACKET ? 1
							: packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET ? 5
									: packetId == WorldPacketsDecoder.ACTION_BUTTON3_PACKET ? 10
											: player.getInventory().getNumerOf(slotId2);
			/*		if (storeItems.getSize() >= MAXIMUM_PLAYER_SHOP_ITEMS) {
						player.sendMessage("nope");
						return false;
					}*/
					if (storeItems.containsOne(new Item(slotId2))) {
						addItem(slotId2, amount, storeItemsPrices.get(slotId2));
						return true;
					}
					player.getPackets().sendInputIntegerScript(true,"Choose price for the item: ");
					player.getTemporaryAttributtes().put("POS_ChoosePrice", new Object[] { slotId2, amount });
					return true;
				case WorldPacketsDecoder.ACTION_BUTTON4_PACKET:
					/*if (storeItems.getSize() >= MAXIMUM_PLAYER_SHOP_ITEMS) {
						player.sendMessage("nope");
						return false;
					}*/
					player.getPackets().sendInputIntegerScript(true,"Choose How many would you like to add: ");
					player.getTemporaryAttributtes().put("POS_ChooseAmount", slotId2);
					return true;
				case WorldPacketsDecoder.ACTION_BUTTON9_PACKET:
					player.getInventory().sendExamine(slotId);
					return true;
				}
				break;
			case 1284:
				if (componentId == 10) {
					toggleLocked();
					for (Player p : viewingPlayers) {
						if (p == null)
							continue;
						p.getPackets()
								.sendGameMessage("Shop is currently : " + (isLocked() ? "Locked" : "Unlocked") + " .");
					}
					return true;
				}
				switch (packetId) {
				case WorldPacketsDecoder.ACTION_BUTTON1_PACKET:
					int price = storeItemsPrices.get(slotId2);
					player.getPackets().sendGameMessage(
							"Current price for item : " + ItemDefinitions.getItemDefinitions(slotId2).getName()
									+ " is <col=00ffff>" + Utils.getFormattedNumber(price) + "</col> coins.");
					return true;
				case WorldPacketsDecoder.ACTION_BUTTON2_PACKET:
					player.getPackets().sendInputIntegerScript(true,"Choose new price for the item: ");
					player.getTemporaryAttributtes().put("POS_ChooseNewPrice", slotId2);
					return true;
				case WorldPacketsDecoder.ACTION_BUTTON3_PACKET:
				case WorldPacketsDecoder.ACTION_BUTTON4_PACKET:
				case WorldPacketsDecoder.ACTION_BUTTON9_PACKET:
					int quantity = packetId == WorldPacketsDecoder.ACTION_BUTTON3_PACKET ? 1
							: packetId == WorldPacketsDecoder.ACTION_BUTTON4_PACKET ? 5
									: storeItems.getNumberOf(slotId2);
					removeItem(slotId, quantity);
					return true;
				case WorldPacketsDecoder.ACTION_BUTTON5_PACKET:
					player.getPackets().sendInputIntegerScript(true,"Choose How many would you like to remove: ");
					player.getTemporaryAttributtes().put("POS_ChooseAmountRemove", slotId);
					return true;
				case WorldPacketsDecoder.ACTION_BUTTON6_PACKET:
					sendExamine(player, slotId);
					return true;
				}
			}
			return false;
		} else {
			if (player == null || player.isDead()) {
				customer.getPackets()
						.sendGameMessage("Seems like the owner of the shop went offline please try again later.");
				return false;
			}
			switch (packetId) {
			case WorldPacketsDecoder.ACTION_BUTTON1_PACKET:
				int price = storeItemsPrices.get(slotId2);
				customer.getPackets().sendGameMessage(
						"Current price for item : " + ItemDefinitions.getItemDefinitions(slotId2).getName()
								+ " is <col=00ffff>" + Utils.getFormattedNumber(price) + "</col> coins.");
				return true;
			case WorldPacketsDecoder.ACTION_BUTTON2_PACKET:
			case WorldPacketsDecoder.ACTION_BUTTON3_PACKET:
			case WorldPacketsDecoder.ACTION_BUTTON5_PACKET:
				if (isLocked()) {
					customer.getPackets().sendGameMessage("You can't buy items while the shop is locked.");
					return true;
				}
				int quantity = packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET ? 1
						: packetId == WorldPacketsDecoder.ACTION_BUTTON3_PACKET ? 5 : storeItems.getNumberOf(slotId2);
				buy(customer, slotId, quantity);
				return true;
			case WorldPacketsDecoder.ACTION_BUTTON4_PACKET:
				if (isLocked()) {
					customer.getPackets().sendGameMessage("You can't buy items while the shop is locked.");
					return true;
				}
				customer.getPackets().sendInputIntegerScript(true,"Choose How many would you like to buy: ");
				customer.getTemporaryAttributtes().put("POS_ChooseAmountBuy", slotId);
				return true;
			case WorldPacketsDecoder.ACTION_BUTTON9_PACKET:
				sendExamine(customer, slotId);
				return true;
			}
			return false;
		}
	}

	public boolean buy(Player customer, int slotId, int quantity) {
		Item item = storeItems.get(slotId);
		if (item == null)
			return false;
		if (item.getAmount() == 0) {
			customer.getPackets().sendGameMessage("There is no stock of that item at the moment.");
			return false;
		}
		int price = storeItemsPrices.get(item.getId());
		int amountCoins = customer.getInventory().getNumerOf(995);
		int maxQuantity = amountCoins / price;
		int buyQ = item.getAmount() > quantity ? quantity : item.getAmount();
		boolean enoughCoins = maxQuantity >= buyQ;
		if (!enoughCoins) {
			customer.getPackets().sendGameMessage(
					"You don't have enough " + ItemDefinitions.getItemDefinitions(995).getName().toLowerCase() + ".");
			buyQ = maxQuantity;
		} else if (quantity > buyQ)
			customer.getPackets().sendGameMessage("The shop has run out of stock.");
		if (item.getDefinitions().isStackable()) {
			if (customer.getInventory().getFreeSlots() < 1) {
				customer.getPackets().sendGameMessage("Not enough space in your inventory.");
				return false;
			}
		} else {
			int freeSlots = customer.getInventory().getFreeSlots();
			if (buyQ > freeSlots) {
				buyQ = freeSlots;
				customer.getPackets().sendGameMessage("Not enough space in your inventory.");
			}
		}
		if (buyQ != 0) {
			int totalPrice = price * buyQ;
			if (customer.getInventory().removeItemMoneyPouch(new Item(995, totalPrice))) {
				customer.getInventory().addItem(item.getId(), buyQ);
				player.getBank().addItem(995, totalPrice, true);
				player.getPackets()
						.sendGameMessage("<col=003399>"+customer.getDisplayName() + " has just bought " + buyQ + " X " + item.getName()
								+ " for " + Utils.getFormattedNumber(totalPrice)
								+ " coins the money has been added to your bank.");
				Item[] itemsBefore = storeItems.getItemsCopy();
				storeItems.remove(slotId, new Item(item.getId(), buyQ));
				if (!storeItems.containsOne(item))
					storeItemsPrices.remove(item.getId());
				refreshItems(itemsBefore);
				refreshShop();
			}
		}
		return true;
	}

	public static void sendExamine(Player player, int slotId) {
		PlayerOwnedShop POS = (PlayerOwnedShop) player.getTemporaryAttributtes().get("POS");
		if (POS == null)
			return;
		if (slotId >= POS.getStoreItems().getSize())
			return;
		Item item = POS.getStoreItems().get(slotId);
		if (item == null)
			return;
		player.getPackets().sendGameMessage(ItemExamines.getExamine(item));
	}

	public boolean addItem(int itemId, int amount, int price) {
		return addItem(new Item(itemId, amount), price);
	}

	public boolean addItem(Item item, int price) {
		if (storeItems.getFreeSlots() <= 0) {
			player.getPackets().sendGameMessage("Your shop is currently full.");
			return false;
		}
		if (!ItemConstants.isTradeable(item) || item.getId() == 995) {
			player.getPackets().sendGameMessage("That item cannot be added!");
			return false;
		}
		Item[] itemsBefore = storeItems.getItemsCopy();
		int invAmount = player.getInventory().getNumerOf(item.getId());
		int storeAmount = storeItems.getNumberOf(item);
		if (item.getAmount() > invAmount)
			item.setAmount(invAmount);
		if (invAmount != 0 && storeAmount + item.getAmount() <= 0) {
			item.setAmount(Integer.MAX_VALUE - storeAmount);
			player.getPackets().sendGameMessage("Not enough space in your store.");
		}
		if (item.getAmount() == 0)
			return false;
		if (!storeItems.add(item)) {
			player.getPackets().sendGameMessage("Not enough space in store.");
			return false;
		}
		player.getInventory().deleteItem(item);
		storeItemsPrices.put(item.getId(), price);
		refreshItems(itemsBefore);
		refreshShop();
		return true;
	}

	public boolean removeItem(int slotId, int quantity) {
		Item item = storeItems.get(slotId);
		if (item == null)
			return false;
		if (quantity == 0)
			return false;
		Item[] itemsBefore = storeItems.getItemsCopy();
		int maxAmount = storeItems.getNumberOf(item);
		item = new Item(item.getId(), quantity < maxAmount ? quantity : maxAmount);
		if (!player.getInventory().addItem(item))
			return false;
		storeItems.remove(slotId, item);
		if (!storeItems.containsOne(item))
			storeItemsPrices.remove(item.getId());
		refreshItems(itemsBefore);
		refreshShop();
		return true;
	}

	private void refreshItems(Item[] itemsBefore) {
		int[] changedSlots = new int[itemsBefore.length];
		int count = 0;
		for (int index = 0; index < itemsBefore.length; index++) {
			Item item = storeItems.getItems()[index];
			if (itemsBefore[index] != item) {
				changedSlots[count++] = index;
			}
		}
		int[] finalChangedSlots = new int[count];
		System.arraycopy(changedSlots, 0, finalChangedSlots, 0, count);
		refresh(finalChangedSlots);
	}

	private void refresh(int... slots) {
		for (Player player : viewingPlayers) {
			if (player == null)
				continue;
			player.getPackets().sendUpdateItems(100, storeItems, slots);
		}
	}

	public void refreshShop() {
		for (Player player : viewingPlayers) {
			if (player == null)
				continue;
			player.getPackets().sendItems(100, storeItems);
		}
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
		viewingPlayers = new CopyOnWriteArrayList<Player>();
	}

	public Map<Integer, Integer> getStoreItemsPrices() {
		return storeItemsPrices;
	}

	public ItemsContainer<Item> getStoreItems() {
		return storeItems;
	}

	public CopyOnWriteArrayList<Player> getViewingPlayers() {
		return viewingPlayers;
	}

	public boolean isLocked() {
		return locked || player == null;
	}

	public void toggleLocked() {
		this.locked = !locked;
		player.getPackets().sendIComponentText(1284, 46, "Shop is " + (locked ? "Locked" : "Unlocked"));
	}
}