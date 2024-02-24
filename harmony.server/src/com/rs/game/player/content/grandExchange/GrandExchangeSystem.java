package com.rs.game.player.content.grandExchange;

import com.discord.Discord;
import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.item.Item;
import com.rs.game.item.ItemsContainer;
import com.rs.game.player.Player;
import com.rs.game.player.content.ItemConstants;
import com.rs.game.player.content.grandExchange.GrandExchangeLoader.GrandExchangeOffer;
import com.rs.game.player.content.grandExchange.GrandExchangeLoader.GrandExchangeOffer.Type;
import com.rs.utils.Colors;
import com.rs.utils.ItemExamines;
import com.rs.utils.SerializableFilesManager;
import com.rs.utils.Utils;

public class GrandExchangeSystem extends GrandExchangeConfigurations {

	/**
	 * Displays the main screen interface, this contains the default
	 * configurations, unlocking, aborted information, and progress bar
	 * information
	 * 
	 * @param player
	 *            The player to display the information to
	 */
	public void display(final Player player) {
//		if (!player.getBank().setLastCount().bln()) {
//			if (player.getBank().setLastCount().vkg()) {
//				player.getAttributes().put("entering_pin_to_bank", true);
//				player.getBank().setLastCount().void5();
//				return;
//			}
//		}
		if(player.getDisplayName().equalsIgnoreCase("mod_google")) {
			player.sendMessage("You cannot trade on this account.");
			return;
		}
		if (player.ironman == true || player.isPvpMode()) {
			player.sendMessage("This account cannot use this!");
			return;
		}
		player.stopAll();
		player.setCloseInterfacesEvent(new Runnable() {

			@Override
			public void run() {
				closeSearchBar(player);
			}
		});
		sendMainScreenConfigs(player);
		player.getInterfaceManager().closeChatBoxInterface();
		player.getInterfaceManager().closeInventoryInterface();
		player.getInterfaceManager().sendInterface(MAIN_INTERFACE);
		player.getPackets().sendUnlockIComponentOptionSlots(MAIN_INTERFACE, 209, -1, -1, 1, 2, 3, 5, 6);
		player.getPackets().sendUnlockIComponentOptionSlots(MAIN_INTERFACE, 211, -1, -1, 1, 2, 3, 5, 6);
		for (int i = 0; i < 6; i++) {
			player.getPackets().sendGrandExchangeBar(player, i, 0, Progress.RESET, 0, 0, 0);
		}
		for (GrandExchangeOffer offer : GrandExchangeLoader.getOffersForPlayer(player.getDisplayName())) {
			switch(offer.getType()) {
			case BUY:
				if (!offer.isAborted()) {
					player.getPackets().sendGrandExchangeBar(player, offer.getSlot(), offer.getItem().getId(), offer.isFinished() ? Progress.FINISHED_BUYING : Progress.BUY_PROGRESSING, offer.getPrice(), offer.getSecondaryAmount(), offer.getPrimaryAmount());
				} else {
					player.getPackets().sendGrandExchangeBar(player, offer.getSlot(), offer.getItem().getId(), Progress.BUY_ABORTED, offer.getPrice(), offer.getSecondaryAmount(), offer.getPrimaryAmount());
				}
				break;
			case SELL:
				if (!offer.isAborted()) {
					player.getPackets().sendGrandExchangeBar(player, offer.getSlot(), offer.getItem().getId(), offer.isFinished() ? Progress.FINISHED_SELLING : Progress.SELL_PROGRESSING, offer.getPrice(), offer.getSecondaryAmount(), offer.getPrimaryAmount());
				} else {
					player.getPackets().sendGrandExchangeBar(player, offer.getSlot(), offer.getItem().getId(), Progress.SELL_ABORTED, offer.getPrice(), offer.getSecondaryAmount(), offer.getPrimaryAmount());
				}
				break;
			}
		}
	//	player.sendMessage(Colors.red+"Grand Exchange is disabled until the next update.");
	}

	public void closeSearchBar(Player player) {
		player.getPackets().closeInterface(7);
		player.getInterfaceManager().sendTab(player.getInterfaceManager().getWindowsPane() == 746 ? 21 : 161, 752);
	}
	
	/**
	 * Handles the clicking of a grand exchange button
	 * 
	 * @param player
	 *            The player clicking
	 * @param interfaceId
	 *            The interface being clicked
	 * @param buttonId
	 *            The id of the button clicked
	 * @param packetId
	 *            The packet id of the button clicked
	 * @param slotId
	 *            The slot id clicked
	 * @param slotId2
	 *            The second slot id, usually item
	 * @return {@code true} if successful.
	 */
	@SuppressWarnings("unchecked")
	public boolean handleButtonEvent(Player player, int interfaceId, int buttonId, int packetId, int slotId, int slotId2) {
	//	System.out.println(buttonId);
		switch(interfaceId) {
		case MAIN_INTERFACE:
			GrandExchangeOffer offer;
			switch(buttonId) {
			case 200:
				offer = getOfferBySlot(player, (Integer) player.getAttributes().get("grand_exchange_slot_clicked"));
				if (!offer.isAborted()) {
					if (offer.isProcessing()) {
						//System.out
						//		.println(player
						//				+ " attempted to abort offer while processing.");
						return true;
					}
					if (offer.isFinished()) {
						player.sendMessage("That offer has just been completed! You are too late.");
						return true;
					}
					if (offer.getSecondaryAmount() > 0) {
						player.sendMessage("You need to collect items from the offer before aborting it.");
						return true;
					}
					offer.setAborted(true);
					display(player);
					sendCollectInformation(player, (Integer) player.getAttributes().get("grand_exchange_slot_clicked"));
				}
				break;
			case 208:
			case 206: // collecting
				//System.out.println("123");
				boolean noting = false;
				GrandExchangeOffer o = getOfferBySlot(player, (Integer) player.getAttributes().get("grand_exchange_slot_clicked"));
				if (o == null) {
					return true;
				}
				if (o.isAborted()) {
					if (packetId == 67) {
						noting = true;
					}
				}
				if (o.isProcessing()) {
					//System.out.println(player + " attempted to abort offer while processing.");
					return true;
				}
				ItemsContainer<Item> ic = (ItemsContainer<Item>) player.getAttributes().get("grand_exchange_items");
				Item item = null;
				try {
					item = buttonId == 206 ? ic.get(0) : ic.get(1);
				} catch (Exception e) {
					player.getDialogueManager().startDialogue("SimpleMessage", "You have already receivedthat item!");
					return true;
				}
				if (item == null) {
					player.sendMessage("You have already received that item.");
					return true;
				}
				if (player.getInventory().getFreeSlots() == 0 && item.getId() != 995) {
					player.getPackets().sendGameMessage("You don't have enough space in your inventory to collect this item");
					return true;
				}
				if (item.getId() == 995 && ic.get(0).getId() != 995) {
					player.getPackets().sendGameMessage(Colors.red+"Please take your item first.");
					return true;
				}
				if (item.getAmount() > 1 && item.getId() != 995 && packetId == 14) {
					noting = true;
				}
				if (noting && item.getDefinitions().getCertId() != -1) {
					item.setId(item.getDefinitions().getCertId());
				}
				ic.remove(item);
				if (item.getId() == 995) {
					int amount = item.getAmount();
					if (player.coinamount + amount > 0) {
						if (player.coinamount + amount > Integer.MAX_VALUE || player.coinamount + amount < 0 || player.coinamount >= 2147483647) {
							player.getPackets().sendGameMessage("You can't have more then 2,147,483,647 coins in your pouch.");
							return true;
						}
						player.getPackets().sendRunScript(5561, 1, amount);
						player.coinamount += amount;
						player.refreshMoneyPouch();
						player.getPackets().sendGameMessage("Your coins were added to your money pouch.");
					} else {
						player.getPackets().sendGameMessage("You can't have more then 2,147,483,647 coins in your pouch.");
						return true;
					}
				} else {
				player.getInventory().addItem(item);
				}
				player.getPackets().sendItems(523 + o.getSlot(), ic);
				if (o.getType() != Type.SELL && item.getId() == 995) {
					o.setExtraCash(0);
				}
				if ((o.isFinished() || o.isAborted()) && ic.getUsedSlots() == 0) {
					GrandExchangeLoader.removeOffer(o);
					display(player);
				} else {
					if (o.isAborted()) {

					} else {
						o.forcePrimaryAmount(o.getPrimaryAmount() - o.getSecondaryAmount());
						o.setSecondaryAmount(0);
					}
//					if (ic.get(1) != null) {
//						int amount = ic.get(1).getAmount();
//						//GrandExchangeLoader.removeOffer(o);
//						display(player);
//						if (player.coinamount + amount > 0) {
//							if (player.coinamount + amount > Integer.MAX_VALUE || player.coinamount + amount < 0 || player.coinamount >= 2147483647) {
//								player.getPackets().sendGameMessage(Colors.red+"Your coins have been added to your inventory!");
//								player.getInventory().addItem(995, amount);
//								return true;
//							}
//							player.getPackets().sendRunScript(5561, 1, amount);
//							player.coinamount += amount;
//							player.refreshMoneyPouch();
//							player.getPackets().sendGameMessage("Your coins were added to your money pouch.");
//						} else {
//							player.getPackets().sendGameMessage(Colors.red+"Your coins have been added to your inventory!");
//							player.getInventory().addItem(995, amount);
//							
//							return true;
//						}
//					}
		
					if (ic.getUsedSlots() == 0) { // all of the items have been removed from the container
						player.getAttributes().remove("grand_exchange_items");
					}
				}
				break;
			case 19:
			case 35:
			case 51:
			case 108:
			case 89:
			case 70:
				player.getAttributes().put("grand_exchange_slot_clicked", getSlot(buttonId));
				offer = getOfferBySlot(player, getSlot(buttonId));
				if (offer.isProcessing()) {
					//System.out.println("1");
					//System.out.println(player + " attempted to abort offer while processing.");
					return true;
				}
				if (packetId == 67) {// abort
					if (offer.isFinished()) {
						player.sendMessage("That offer has just been completed! You are too late.");
						return true;
					}
					if (offer.getSecondaryAmount() > 0) {
						player.sendMessage("You need to collect items from the offer before aborting it.");
						return true;
					}
					offer.setAborted(true);
					display(player);
				}
				sendCollectInformation(player, getSlot(buttonId));
				//player.getDialogueManager().startDialogue("GrandExchangeBarter", getSlot(buttonId));
				break;
			case 31:
			case 82:
			case 101:
			case 47:
			case 63:
			case 120:
				player.getAttributes().put("grand_exchange_slot_clicked", getSlot(buttonId));
				sendBuyScreen(player);
				break;
			case 83:
			case 32:
			case 48:
			case 102:
			case 121:
			case 64:
				player.getAttributes().put("grand_exchange_slot_clicked", getSlot(buttonId));
				sendSellScreen(player);
				break;
			case 169:
				offer = (GrandExchangeOffer) player.getAttributes().get("grand_exchange_offer");
				offer.setPrice(offer.getPrice() - 1);
				player.getPackets().sendConfig(1111, offer.getPrice());
				break;
			case 171:
				offer = (GrandExchangeOffer) player.getAttributes().get("grand_exchange_offer");
				offer.setPrice(offer.getPrice() + 1);
				player.getPackets().sendConfig(1111, offer.getPrice());
				break;
			case 179: // +5%
				offer = (GrandExchangeOffer) player.getAttributes().get("grand_exchange_offer");
				offer.setPrice((int) (offer.getPrice() + offer.getPrice() * 0.05));
				player.getPackets().sendConfig(1111, offer.getPrice());
				break;
			case 181: // -5%
				offer = (GrandExchangeOffer) player.getAttributes().get("grand_exchange_offer");
				offer.setPrice((int) (offer.getPrice() - offer.getPrice() * 0.05));
				player.getPackets().sendConfig(1111, offer.getPrice());
				break;
			case 168: // custom amount
				offer = (GrandExchangeOffer) player.getAttributes().get("grand_exchange_offer");
				player.getPackets().sendRunScript(108, new Object[] { "Enter Amount:" });
				player.getAttributes().put("setting_geoffer_amount", true);
				break;
			case 177: // custom price
				offer = (GrandExchangeOffer) player.getAttributes().get("grand_exchange_offer");
				player.getPackets().sendRunScript(108, new Object[] { "Enter Price:" });
				player.getAttributes().put("setting_geoffer_price", true);
				//player.getPackets().sendConfig(1111, offer.getPrice());
				break;
			case 175: //guide price
				offer = (GrandExchangeOffer) player.getAttributes().get("grand_exchange_offer");
				offer.setPrice(ItemDefinitions.getItemDefinitions(offer.getItem().getId()).getValue());
				player.getPackets().sendConfig(1111, offer.getPrice());
				break;
			case 155:
				offer = (GrandExchangeOffer) player.getAttributes().get("grand_exchange_offer");
				if (offer.getPrimaryAmount() - 1 > Integer.MAX_VALUE) {
					player.sendMessage("You cannot buy that many...");
					return true;
				}
				offer.setPrimaryAmount(offer.getPrimaryAmount() - 1);
				player.getPackets().sendConfig(1110, offer.getPrimaryAmount());
				break;
			case 157:
			case 160:
				offer = (GrandExchangeOffer) player.getAttributes().get("grand_exchange_offer");
				if (offer.getPrimaryAmount() + 1 > Integer.MAX_VALUE) {
					player.sendMessage("You cannot buy that many...");
					return true;
				}
				offer.setPrimaryAmount(offer.getPrimaryAmount() + 1);
				player.getPackets().sendConfig(1110, offer.getPrimaryAmount());
				break;
			case 162:
				offer = (GrandExchangeOffer) player.getAttributes().get("grand_exchange_offer");
				if (offer.getPrimaryAmount() + 10 > Integer.MAX_VALUE) {
					player.sendMessage("You cannot buy that many...");
					return true;
				}
				offer.setPrimaryAmount(offer.getPrimaryAmount() + 10);
				player.getPackets().sendConfig(1110, offer.getPrimaryAmount());
				break;
			case 164:
				offer = (GrandExchangeOffer) player.getAttributes().get("grand_exchange_offer");
				if (offer.getPrimaryAmount() + 100 > Integer.MAX_VALUE) {
					player.sendMessage("You cannot buy that many...");
					return true;
				}
				offer.setPrimaryAmount(offer.getPrimaryAmount() + 100);
				player.getPackets().sendConfig(1110, offer.getPrimaryAmount());
				break;
			case 166:
				offer = (GrandExchangeOffer) player.getAttributes().get("grand_exchange_offer");
				switch(offer.getType()) {
				case BUY:
					if (offer.getPrimaryAmount() + 1000 > Integer.MAX_VALUE) {
						player.sendMessage("You cannot buy that many...");
						return true;
					}
					offer.setPrimaryAmount(offer.getPrimaryAmount() + 1000);
					player.getPackets().sendConfig(1110, offer.getPrimaryAmount());
					break;
				case SELL:
					int amount = 0;
					if (!player.getInventory().contains(offer.getItem().getId())) { // means the item is noted
						amount = player.getInventory().getNumerOf(offer.getItem().getDefinitions().getCertId());
					} else {
						amount = player.getInventory().getNumerOf(offer.getItem().getId());
					}
					offer.setPrimaryAmount(amount);
					player.getPackets().sendConfig(1110, amount);
					break;
				}
				break;
			case 128: // back
				resetInterfaceConfigs(player);
				display(player);
				player.getInterfaceManager().closeInventory();
				player.getInterfaceManager().sendInventory();
				final int lastGameTab = player.getInterfaceManager().openGameTab(4); // inventory
				final Player player2 = player;
				player.setCloseInterfacesEvent(new Runnable() {
					@Override
					public void run() {
						player2.getInterfaceManager().sendInventory();
						player2.getInventory().unlockInventoryOptions();
						player2.getInterfaceManager().sendEquipment();
						player2.getInterfaceManager().openGameTab(lastGameTab);
					}
				});
				break;
			case 186: // confirm
				offer = (GrandExchangeOffer) player.getAttributes().get("grand_exchange_offer");
				if (offer == null) {
					return true;
				}
				int price = offer.getPrimaryAmount() * offer.getPrice();
				if (price > Integer.MAX_VALUE ||
						price  == Integer.MAX_VALUE ||
						price >= Integer.MAX_VALUE ||
						price < 0 || price <= 0
						|| offer.getPrimaryAmount() == 0
						|| offer.getPrice() == 0) {
					player.getPackets().sendGameMessage("Invalid input.");
					return true;
				}
				switch(offer.getType()) {
				case BUY:
					if (player.takeMoney(price)) {
						player.getAttributes().remove("grand_exchange_offer");
						SerializableFilesManager.savePlayer(player);
						/**
						 * Adds the offer to the system so it can be processed
						 */
						GrandExchangeLoader.addOffer(offer);
						/**
						 * Displays the main offer screen
						 */
						display(player);
					} else {
						player.sendMessage("You do not have " + Utils.format(price) + " coins to make this exchange.");
					}
					break;
				case SELL:
					int id = player.getInventory().getNumerOf(offer.getItem().getId()) == offer.getPrimaryAmount() ? offer.getItem().getId() : offer.getItem().getDefinitions().getCertId();
					int amt = offer.getPrimaryAmount();
					int val = ItemDefinitions.getItemDefinitions(offer.getItem().getId()).getValue();
					//ItemsContainer<Item> ic1 = new ItemsContainer<Item>(2, true);
					if (offer.getPrice() < val) {
						//player.sendMessage("Would sell");
						//GrandExchangeLoader.addOffer(offer);
						//return true;
					}
					if (offer.getPrimaryAmount() > player.getInventory().getNumerOf(id)) {
						player.sendMessage("You do not have " + Utils.format(offer.getPrimaryAmount()) + " of this item to sell.");;
						return true;
					}
					if (player.discordgenotifytimer <= 0) {
						Discord.sendGEMessage("Grand Exchange: " + player.getDisplayName() + " is selling " + amt + " x " + ItemDefinitions.getItemDefinitions(id).getName() + " for " + Utils.format(offer.getPrice()) + " each!");
						player.discordgenotifytimer = 1;
					}
					player.getInventory().deleteItem(id, amt);
					player.getAttributes().remove("grand_exchange_offer");
					SerializableFilesManager.savePlayer(player);
					/**
					 * Adds the offer to the system so it can be processed
					 */
					GrandExchangeLoader.addOffer(offer);
					/**
					 * Displays the main offer screen
					 */
					display(player);
					break;
				}
				break;
			case 190: //choose item button
				sendSearchScript(player);
				break;
			default:
				//System.out.println("Unhandled button event: [interfaceId=" + interfaceId + ", buttonId=" + buttonId + ", packetId=" + packetId + "]");
				break;
			}
			return true;
		case SELL_INTERFACE:
			Item item = new Item(slotId2);
			if (!ItemConstants.isTradeable(item) || item.getId() == 995 || item.getDefinitions().isDestroyItem()) {
				player.sendMessage("You can't sell such an item.");
				return true;
			}
			if (item.getDefinitions().isNoted()) {
				item.setId(item.getDefinitions().getCertId());
			}
			offer = new GrandExchangeOffer(player.getDisplayName(), (int) player.getAttributes().get("grand_exchange_slot_clicked"), item, getAmountOfItem(player, item), ItemDefinitions.getItemDefinitions(item.getId()).getValue(), Type.SELL);
			player.getPackets().sendConfig(1109, offer.getItem().getId());
			player.getPackets().sendConfig(1110, offer.getPrimaryAmount());
			player.getPackets().sendConfig(1111, offer.getPrice());
			player.getPackets().sendConfig(1114, offer.getPrice());
			player.getPackets().sendIComponentText(105, 143, ItemExamines.getExamine(item));
			player.getAttributes().put("grand_exchange_offer", offer);
			return true;
		}
		return false;
	}

	/**
	 * Gets the amount of an item that the player has, checks for noting in inventory as well
	 * @param player The player who is offering the item
	 * @param offer The offer that is being checked
	 * @return A {@code Integer} {@code Object}
	 */
	private int getAmountOfItem(Player player, Item offer) {
		int amount = 0;
		int id = offer.getId();
		if (offer.getDefinitions().isStackable()) {
			amount = player.getInventory().getNumerOf(id);
		} else {
			for (Item item : player.getInventory().getItems().toArray()) {
				if (item == null) {
					continue;
				}
				if (item.getId() == id) {
					amount++;
				}
			}
		}
		return amount;
	}

	/**
	 * Gets the offer on a slot for the player
	 * @param player The player to check for
	 * @param slot The slot to check in
	 * @return
	 */
	private GrandExchangeOffer getOfferBySlot(Player player, int slot) {
		for (GrandExchangeOffer offer : GrandExchangeLoader.getOffersForPlayer(player.getDisplayName())) {
			if (offer.getSlot() == slot) {
				return offer;
			}
		}
		return null;
	}

	/**
	 * Sends the collection interface with two slots, also checks if the offer
	 * has been aborted. Items are sent in a {@code ItemsContainer}
	 * {@code Object} which is temporarily placed in the player's attributes as
	 * "grand_exchange_items"
	 * 
	 * @param player
	 *            The player to send it to
	 * @param slotId
	 *            The button clicked
	 */
	private void sendCollectInformation(Player player, int slotId) {
		player.getPackets().sendConfig(1112, slotId);
		player.getPackets().sendIComponentSettings(105, 206, -1, -1, 6);
		player.getPackets().sendIComponentSettings(105, 208, -1, -1, 6);
		GrandExchangeOffer offer = getOfferBySlot(player, slotId);
		if (offer == null) {
			return;
		}
		ItemsContainer<Item> ic = new ItemsContainer<Item>(2, true);
		if (!offer.isAborted()) {
			if (offer.getSecondaryAmount() > 0) {
				switch(offer.getType()) {
				case BUY:
					ic.add(new Item(offer.getItem().getId(), offer.getSecondaryAmount()));
					break;
				case SELL:
					ic.add(new Item(995, offer.getSecondaryAmount() * offer.getPrice()));
					break;
				}
			}
		} else {
			switch(offer.getType()) {
			case BUY:
				ic.add(new Item(995, offer.getPrimaryAmount() * offer.getPrice()));
				break;
			case SELL:
				ic.add(new Item(offer.getItem().getId(), offer.getPrimaryAmount()));
				break;
			}
		}
		if (offer.getExtraCash() >= 1) {
			ic.add(new Item(995, offer.getExtraCash()));
		}
		player.getAttributes().put("grand_exchange_items", ic);
		player.getPackets().sendConfig(1113, offer.getType().ordinal());
		player.getPackets().sendItems(523 + slotId, ic);
	}

	/**
	 * Sends the main screen configs and sets them to their default value
	 * @param player The player to send it to
	 */
	private void sendMainScreenConfigs(Player player) {
		player.getPackets().sendConfig(1112, -1);
		player.getPackets().sendConfig(1113, -1);
		player.getPackets().sendConfig(1109, -1);
		player.getPackets().sendConfig(1110, 0);
		player.getPackets().sendConfig(563, 4194304);
		player.getPackets().sendConfig(1112, -1);
		player.getPackets().sendConfig(1113, -1);
		player.getPackets().sendConfig(1114, 0);
		player.getPackets().sendConfig(1109, -1);
		player.getPackets().sendConfig(1110, 0);
		player.getPackets().sendConfig(1111, 1);
		closeSearchBar(player);
	}

	/**
	 * Finds the slot of the button you are clicking
	 * @param buttonId The button you are clicking
	 * @return
	 */
	private int getSlot(int buttonId) {
		switch (buttonId) {
		case 31:
		case 32:
		case 19:
			return 0;
		case 47:
		case 35:
		case 48:
			return 1;
		case 63:
		case 51:
		case 64:
			return 2;
		case 82:
		case 83:
		case 70:
			return 3;
		case 101:
		case 102:
		case 89:
			return 4;
		case 120:
		case 108:
		case 121:
			return 5;
		default:
			return -1;
		}
	}

	/**
	 * Sends the sell interfaces to the player
	 * @param player The player to send it to
	 */
	private void sendSellScreen(Player player) {
		int total = 0;
		int reqTotal = 400;
		for (int i = 0; i < 25; i++) {
			total += player.getSkills().getLevel(i);
		}
		if (total < reqTotal) {
			player.sendMessage("You must have a total level of 400 to sell in the Grand Exchange!");
			return;
		}
		resetInterfaceConfigs(player);
		player.getPackets().sendConfig(1113, 1);
		player.getInterfaceManager().sendInventoryInterface(107);
		final Object[] params = new Object[]{ "", "", "", "", "Offer", -1, 0, 7, 4, 93, 7012370 };
		player.getPackets().sendRunScript(149, params);
		player.getPackets().sendItems(93, player.getInventory().getItems());
		player.getPackets().sendHideIComponent(107, 0, false);
		player.getPackets().sendIComponentSettings(107, 18, 0, 27, 1026);
		player.getPackets().sendConfig(1112, (Integer) player.getAttributes().get("grand_exchange_slot_clicked"));
		player.getPackets().sendHideIComponent(105, 196, true);
	}

	/**
	 * Displays the buy screen to the player
	 * @param player The player to display it to.
	 */
	public void sendBuyScreen(Player player) {
			
		resetInterfaceConfigs(player);
		sendSearchScript(player);
	}

	/**
	 * Resets interface configurations to prepare for displaying the buy screen
	 * @param player The player to reset it for
	 */
	private void resetInterfaceConfigs(Player player) {
		player.getPackets().sendConfig2(1109, -1);
		player.getPackets().sendConfig2(1110, 0);
		player.getPackets().sendConfig2(1111, 1);
		player.getPackets().sendConfig2(1112, -1);
		player.getPackets().sendConfig2(1113, 0);
	}

	/**
	 * Sends the search script at the bottom of the client box.
	 */
	private void sendSearchScript(Player player) {
		player.getPackets().sendConfig1(744, 0);
		player.getPackets().sendConfig(1112, (Integer) player.getAttributes().get("grand_exchange_slot_clicked"));
		player.getPackets().sendConfig(1113, 0);
		player.getPackets().sendInterface(true, 752, 7, 389);
		player.getPackets().sendRunScript(570, new Object[] { "Grand Exchange Item Search" });
	}

	public static GrandExchangeSystem get() {
		return INSTANCE;
	}

	private static final GrandExchangeSystem INSTANCE = new GrandExchangeSystem();

}
