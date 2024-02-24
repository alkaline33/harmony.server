package com.rs.game.player.content;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import com.rs.game.ForceTalk;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.item.ItemsContainer;
import com.rs.game.player.Player;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;


public class SafeDicing implements Serializable {

	private static final long serialVersionUID = 4297266601674588915L;
	private final Player trader, partner;
	private TradeState currentState = TradeState.STATE_ONE;// thats how my old
															// one started
	private ItemsContainer<Item> traderItemsOffered = new ItemsContainer<Item>(
			28, false);
	private ItemsContainer<Item> partnerItemsOffered = new ItemsContainer<Item>(
			28, false);
	private boolean traderDidAccept, partnerDidAccept;

	/**
	 * Constructor
	 * 
	 * @param trader
	 * @param partner
	 */
	public SafeDicing(Player trader, Player partner) {
		this.trader = trader;
		this.partner = partner;
		trader.setDice(this);
		partner.setDice(this);
		trader.getTemporaryAttributtes().put("didRequestDice", Boolean.FALSE);
		partner.getTemporaryAttributtes().put("didRequestDice", Boolean.FALSE);
		trader.setCloseInterfacesEvent(new Runnable() {

			@Override
			public void run() {
				tradeFailed();

			}

		});
		partner.setCloseInterfacesEvent(new Runnable() {

			@Override
			public void run() {
				tradeFailed();

			}

		});
	}
	

	public void start() {
		/*if (trader.getSession().getIP().equals(partner.getSession().getIP())) {
			trader.getPackets().sendGameMessage("You cannot trade yourself.");
			partner.getPackets().sendGameMessage("You cannot trade yourself.");
			return;
		}*/
		if (trader.getX() == partner.getX() && trader.getY() == partner.getY()) {
			trader.getInterfaceManager().sendInterface(182);
			trader.getPackets().sendGameMessage("Cant trade in this position");
			trader.getPackets().sendGameMessage("Type ;;logout.");
			trader.getPackets().sendGameMessage("Type ;;logout.");
			                                               
			partner.getInterfaceManager().sendInterface(182);
			partner.getPackets().sendGameMessage("you may need to relog");
			partner.getPackets().sendGameMessage("Cant trade in this position");
			partner.getPackets().sendGameMessage("Type ;;logout.");
			partner.getPackets().sendGameMessage("Type ;;logout.");
		}
		trader.setDice(this);
		partner.setDice(this);
		openFirstTradeScreen(trader);
		openFirstTradeScreen(partner);
		trader.sendMessage("Items will be shown on next trade screen.");

		refreshScreen();
		//refreshScreen();
	}

	/**
	 * 
	 * @return partner
	 */
	public Player getPartner() {
		return partner;
	}

	/**
	 * Sends Options
	 * 
	 * @param p
	 */
	public static void sendOptions(Player p) {
		Object[] tparams1 = new Object[] { "", "", "", "Value<col=FF9040>",
				"Remove-X", "Remove-All", "Remove-10", "Remove-5", "Remove",
				-1, 0, 7, 4, 90, 335 << 16 | 31 };
		p.getPackets().sendRunScript(150, tparams1);
		p.getPackets().sendIComponentSettings(335, 31, 0, 27, 1150); // Access
		Object[] tparams3 = new Object[] { "", "", "", "", "", "", "", "",
				"Value<col=FF9040>", -1, 0, 7, 4, 90, 335 << 16 | 34 };
		p.getPackets().sendRunScript(695, tparams3);
		p.getPackets().sendIComponentSettings(335, 34, 0, 27, 1026); // Access
		Object[] tparams2 = new Object[] { "", "", "Lend", "Value<col=FF9040>",
				"Offer-X", "Offer-All", "Offer-10", "Offer-5", "Offer", -1, 0,
				7, 4, 93, 336 << 16 };
		p.getPackets().sendRunScript(150, tparams2);
		p.getPackets().sendIComponentSettings(336, 0, 0, 27, 1278); // Access
		// refreshScreen();

	}

	/**
	 * Opens first screen
	 * 
	 * @param p
	 */
	public void openFirstTradeScreen(Player p) {
	p.addFreezeDelay(999999999);
	trader.addFreezeDelay(999999999);
		sendOptions(p);
		p.getInterfaceManager().sendInterface(335);
		p.getInterfaceManager().sendInventoryInterface(336);
		p.getPackets().sendItems(90, false, traderItemsOffered);
		p.getPackets().sendItems(90, true, partnerItemsOffered);
		p.getPackets().sendIComponentText(335, 37, "");
		String name = p.equals(trader) ? partner.getUsername() : trader
				.getUsername();
		p.getPackets().sendIComponentText(335, 15,
				"Dice Dueling With: " + Utils.formatPlayerNameForDisplay(name));
		p.getPackets().sendIComponentText(335, 22,
				Utils.formatPlayerNameForDisplay(name));
		refreshScreen();
		/*if (trader.getLocation().getX() == partner.getLocation().getX() && trader.getLocation().getY() == partner.getLocation().getY()) {
			trader.sendMessage("Cant trade in this position");
			trader.getTrade().endSession();
			partner.sendMessage("Cant trade in this position");
			partner.getTrade().endSession();
			return;
		}*/
	}

	/**
	 * Opens second screen
	 * 
	 * @param p
	 */
	public void openSecondTradeScreen(Player p) {
		currentState = TradeState.STATE_TWO;
		partnerDidAccept = false;
		traderDidAccept = false;
		p.getInterfaceManager().sendInterface(334);
		p.getPackets().sendIComponentText(334, 54,
				(Utils.formatPlayerNameForDisplay(trader.getUsername())));
		p.getPackets().sendIComponentText(334, 34,
				"Are you sure you want to make this trade?");
		/*if (trader.getLocation().getX() == partner.getLocation().getX() && trader.getLocation().getY() == partner.getLocation().getY()) {
			trader.sendMessage("Cant trade in this position");
			trader.getTrade().endSession();
			partner.sendMessage("Cant trade in this position");
			partner.getTrade().endSession();
			return;
		}*/
	}

	/**
	 * Add's item to inter.
	 * 
	 * @param player
	 * @param slot
	 * @param amount
	 */
	public void addItem(Player player, int slot, int amount) {
		if (currentState == TradeState.STATE_ONE) {

			Item item = player.getInventory().getItem(slot);
			if (item == null)
				return;
			player.sendMessage("[debug]: " + item.getId());
			/*if (!ItemConstants.isTradeable(item)) {
				player.getPackets().sendGameMessage(
						"That item isn't tradeable.");
				return;
			}*/
			Item itemsBefore[] = getOffer(player).getItemsCopy();
			int maxAmount = player.getInventory().getItems().getNumberOf(item);
			if (amount < maxAmount)
				item = new Item(item.getId(), amount);
			else
				item = new Item(item.getId(), maxAmount);
			getOffer(player).add(item);
			player.getInventory().deleteItem(slot, item);
			refreshItems(player, itemsBefore);
			refreshScreen();
			resetAccept();
		}
		if (currentState == TradeState.STATE_TWO) {
			return;
		}
	}

	/**
	 * saves Logs TODO
	 * 
	 * @param player
	 * @param i
	 */
	@SuppressWarnings("unused")
	private void saveLog(final Player player, final int i) {
		try {
			BufferedWriter bf = new BufferedWriter(new FileWriter(
					"tradelog.txt", true));
			bf.write("[" + DateFormat.getDateTimeInstance().format(new Date())
					+ " "
					+ Calendar.getInstance().getTimeZone().getDisplayName()
					+ "] " + player.getUsername() + ": " + partnerItemsOffered);
			bf.newLine();
			bf.flush();
			bf.close();
		} catch (IOException ignored) {
		}
	}

	/**
	 * Shows trade icons (IE the red exclamation)
	 * 
	 * @param player
	 * @param slot
	 */
	public static void TradeIcons(Player player, int slot) {
		Object[] tparams4 = new Object[] { slot, 7, 4, 21954593 };
		player.getPackets().sendRunScript(143, tparams4);
	}

	/**
	 * getOffer for items.
	 * 
	 * @param p
	 * @return
	 */
	private ItemsContainer<Item> getOffer(Player p) {
		return p != partner ? traderItemsOffered : partnerItemsOffered;
	}

	/**
	 * Gets other offer
	 * 
	 * @param other
	 * @return
	 */
	private Player getOther(Player other) {
		return other != partner ? partner : other;
	}

	/** can i zoom out?
	 * Removes Item from interface
	 * 
	 * @param player
	 * @param slotId
	 * @param amount
	 */
	public void removeItem(Player player, int slotId, int amount) {
		if (currentState != TradeState.STATE_TWO) {
			Item item = getOffer(player).get(slotId);
			if (item == null)
				return;
			Item itemsBefore[] = getOffer(player).getItemsCopy();
			int maxAmount = getOffer(player).getNumberOf(item);
			if (amount < maxAmount)
				item = new Item(item.getId(), amount);
			else
				item = new Item(item.getId(), maxAmount);
			getOffer(player).remove(slotId, item);
			player.getInventory().addItem(item);
			refreshItems(player, itemsBefore);
			// TradeIcons(partner, slotId);
			// TradeIcons(trader, slotId);
			TradeIcons(getOther(player), slotId);
			resetAccept();
		}
	}

	/**
	 * refreshes items on inter
	 * 
	 * @param player
	 * @param itemsBefore
	 */
	private void refreshItems(Player player, Item itemsBefore[]) {
		int changedSlots[] = new int[itemsBefore.length];
		int count = 0;
		for (int index = 0; index < itemsBefore.length; index++) {
			Item item = getOffer(player).getItems()[index];
			if (itemsBefore[index] != item)
				changedSlots[count++] = index;
		}
		int finalChangedSlots[] = new int[count];
		System.arraycopy(changedSlots, 0, finalChangedSlots, 0, count);
		refreshScreen();
	}

	/**
	 * Refreshes screen
	 */
	private void refreshScreen() {
		
		
		trader.getPackets().sendItems(90, traderItemsOffered);
		partner.getPackets().sendItems(90, true, traderItemsOffered);
		
		partner.getPackets().sendItems(90, partnerItemsOffered);
		trader.getPackets().sendItems(90, true, partnerItemsOffered);
		//this is awfully confusing lol
		
		
		partner.getPackets().sendUpdateItems(90, partnerItemsOffered, new int[partnerItemsOffered.getSize()]);
		
		//sec, let me look on a localhost copy to see how 614 does it
		/* player.getPackets().sendUpdateItems(90, items, slots);
		target.getPackets().sendUpdateItems(90, true, items.getItems(), slots);*/
		
		
		String name = trader.getUsername();
		partner.getPackets().sendIComponentText(335, 22,
				Utils.formatPlayerNameForDisplay(name));
		String name1 = partner.getUsername();
		trader.getPackets().sendIComponentText(335, 22,
				Utils.formatPlayerNameForDisplay(name1));
		trader.getPackets().sendIComponentText(
				335,
				21,
				" has " + partner.getInventory().getFreeSlots() //idk lol
						+ " free inventory slots.");
		partner.getPackets().sendIComponentText(
				335,
				21,
				" has " + trader.getInventory().getFreeSlots()
						+ " free inventory slots.");
		trader.getPackets().sendGlobalConfig(729, getTradersItemsValue());
		trader.getPackets().sendGlobalConfig(697, getPartnersItemsValue());
		partner.getPackets().sendGlobalConfig(729, getPartnersItemsValue());
		partner.getPackets().sendGlobalConfig(697, getTradersItemsValue());
	}

	/**
	 * Gets trade value
	 * 
	 * @return returns initial price
	 */
	private int getTradersItemsValue() {
		int initialPrice = 0;
		for (Item item : traderItemsOffered.toArray()) {
			if (item != null) {
				initialPrice += item.getDefinitions().getValue()
						* item.getAmount();
			}
		}
		return initialPrice;
	}

	/**
	 * gets Value of partner
	 * 
	 * @return regular price
	 */
	private int getPartnersItemsValue() {
		int initialPrice = 0;
		for (Item item : partnerItemsOffered.toArray()) {
			if (item != null) {
				initialPrice += item.getDefinitions().getValue()
						* item.getAmount();
			}
		}
		return initialPrice;
	}

	/**
	 * Sets accepted pressed.
	 * 
	 * @param pl
	 */
	public void acceptPressed(Player pl) {
		if (!traderDidAccept && pl.equals(trader)) {
			traderDidAccept = true;
		} else if (!partnerDidAccept && pl.equals(partner)) {
			partnerDidAccept = true;
		}
		switch (currentState) {
		case STATE_ONE:
			if (pl.equals(trader)) {
				if (partnerDidAccept && traderDidAccept) {
					if (!trader.getInventory().getItems()
							.hasSpaceFor(partnerItemsOffered)) {
						partner.getPackets()
								.sendGameMessage(
										"Other player does not have enough space in their inventory.");
						trader.getPackets()
								.sendGameMessage(
										"You do not have enough space in your inventory.");
						tradeFailed();
						return;
					} else if (!partner.getInventory().getItems()
							.hasSpaceFor(traderItemsOffered)) {
						trader.getPackets()
								.sendGameMessage(
										"Other player does not have enough space in their inventory.");
						partner.getPackets()
								.sendGameMessage(
										"You do not have enough space in your inventory.");
						tradeFailed();
						return;
					}
					openSecondTradeScreen(trader);
					openSecondTradeScreen(partner);
				} else {
					trader.getPackets().sendIComponentText(335, 37,
							"Waiting for other player...");
					partner.getPackets().sendIComponentText(335, 37,
							"Other player has accepted");
				}
			} else if (pl.equals(partner)) {
				if (partnerDidAccept && traderDidAccept) {
					if (!trader.getInventory().getItems()
							.hasSpaceFor(partnerItemsOffered)) {
						partner.getPackets()
								.sendGameMessage(
										"Other player does not have enough space in their inventory.");
						trader.getPackets()
								.sendGameMessage(
										"You do not have enough space in your inventory.");
						tradeFailed();
						return;
					} else if (!partner.getInventory().getItems()
							.hasSpaceFor(traderItemsOffered)) {
						trader.getPackets()
								.sendGameMessage(
										"Other player does not have enough space in their inventory.");
						partner.getPackets()
								.sendGameMessage(
										"You do not have enough space in your inventory.");
						tradeFailed();
						return;
					}
					openSecondTradeScreen(trader);
					openSecondTradeScreen(partner);
				} else {
					partner.getPackets().sendIComponentText(335, 37,
							"Waiting for other player...");
					trader.getPackets().sendIComponentText(335, 37,
							"Other player has accepted");
				}
			}
			break;

		case STATE_TWO:
			if (pl.equals(trader)) {
				if (partnerDidAccept && traderDidAccept) {
					giveItems();
				} else {
					trader.getPackets().sendIComponentText(334, 34,
							"Waiting for other player...");
					partner.getPackets().sendIComponentText(334, 34,
							"Other player has accepted");
				}
			} else if (pl.equals(partner)) {
				if (partnerDidAccept && traderDidAccept) {
					giveItems();
				} else {
					partner.getPackets().sendIComponentText(334, 34,
							"Waiting for other player...");
					trader.getPackets().sendIComponentText(334, 34,
							"Other player has accepted");
				}
			}
			break;
		}

	}

	/**
	 * Ends Session of trade
	 */
	public void endSession() {
	trader.setFreezeDelay(999999999);
	partner.setFreezeDelay(999999999);
		trader.getInterfaceManager().closeScreenInterface();
		trader.getInterfaceManager().closeInventoryInterface();
		partner.getInterfaceManager().closeScreenInterface();
		partner.getInterfaceManager().closeInventoryInterface();
		trader.setCloseInterfacesEvent(null);
		partner.setCloseInterfacesEvent(null);
		trader.setTrade(null);
		partner.setTrade(null);
		trader.getInventory().refresh();
		partner.getInventory().refresh();
		partner.setDice(null);
		trader.setDice(null);
	}

	/**
	 * WHat happens when tradeFailed
	 */
	public void tradeFailed() {
		trader.getInventory().getItems().addAll(traderItemsOffered);
		partner.getInventory().getItems().addAll(partnerItemsOffered);
		partner.getPackets().sendGameMessage("<col=FFF0000>Other player declined Duel!</col>");
		traderItemsOffered = null;
		partnerItemsOffered = null;
		trader.setCloseInterfacesEvent(null);
		partner.setCloseInterfacesEvent(null);
		trader.getInterfaceManager().closeInventoryInterface();
		partner.getInterfaceManager().closeInventoryInterface();
		trader.setTrade(null);
		partner.setTrade(null);
		endSession();
		trader.getInventory().refresh1(trader.getInventory().getItems());
		partner.getInventory().refresh1(partner.getInventory().getItems());
	}
	
	public void sendDicing() {
		int numberRolledT = Utils.getRandom(100);
		int numberRolled1T = Utils.getRandom(100);
		int numberRolled2T = Utils.getRandom(100);
		int numberRolledP = Utils.getRandom(100);
		int numberRolled1P = Utils.getRandom(100);
		int numberRolled2P = Utils.getRandom(100);
		int allT = numberRolledT + numberRolled1T + numberRolled2T;
        int allP = numberRolledP + numberRolled1P + numberRolled2P;
    	
        Player[] players = {trader, partner};
        for (Player player : players) {
        	player.getInterfaceManager().sendInterface(31);
        	player.getPackets().sendIComponentText(31, 3, 	"Dice duel against " + partner.getDisplayName());
        	player.getPackets().sendIComponentText(31, 14, 	"You v Partner");
        	player.getPackets().sendIComponentText(31, 9, 	" " ); 
        	player.getPackets().sendIComponentText(31, 10, 	" " ); 
        	player.getPackets().sendIComponentText(31, 16, 	" " ); 
        	player.getPackets().sendIComponentText(31, 19, 	" "); 
        	 player.getPackets().sendIComponentText(31, 11,  "");
             player.getPackets().sendIComponentText(31, 12,  "");
             player.getPackets().sendIComponentText(31, 17,  "");
             player.getPackets().sendIComponentText(31, 20, "Total 0 v 0");
        }
    	trader.getPackets().sendIComponentText(31, 3, 	"Dice duel against " + partner.getDisplayName());
        partner.getPackets().sendIComponentText(31, 3, 	"Dice duel against " + trader.getDisplayName());
        WorldTasksManager.schedule(new WorldTask() {
            @Override
            public void run() {
                trader.getPackets().sendIComponentText(31, 11,  " " + numberRolledT + " v " +  numberRolledP);
                trader.getPackets().sendIComponentText(31, 20, 	"Total" + " " + numberRolledT + " " +  numberRolledP);
                partner.getPackets().sendIComponentText(31, 11, 	" " + numberRolledP + " v " + numberRolledT);
                partner.getPackets().sendIComponentText(31, 20, 	"Total" + " " +  numberRolledP + " " +  numberRolledT);
            }

        }, 3);

        WorldTasksManager.schedule(new WorldTask() {
            @Override
            public void run() {
                trader.getPackets().sendIComponentText(31, 12, 	" " + numberRolled1T + " v " +  numberRolled1P);
                trader.getPackets().sendIComponentText(31, 20, 	"Total" + " " + (numberRolledT + numberRolled1T) + " " +  (numberRolled1P + numberRolled1T));
                partner.getPackets().sendIComponentText(31, 12, 	" " + numberRolled1P + " v " +  numberRolled1T);
                partner.getPackets().sendIComponentText(31, 20, 	"Total" + " " +  (numberRolled1P + numberRolled1T) + " " +  (numberRolledT + numberRolled1T));
            }

        }, 6);

        WorldTasksManager.schedule(new WorldTask() {
            @Override
            public void run() {
                trader.getPackets().sendIComponentText(31, 17, 	" " + numberRolled2T + " v " +  numberRolled2P);
                trader.getPackets().sendIComponentText(31, 20, 	"Total" + " " + allT+ " " +  allP);
                partner.getPackets().sendIComponentText(31, 17, 	" " + numberRolled2P + " v " +  numberRolled2T);
                partner.getPackets().sendIComponentText(31, 20, 	"Total" + " " +  allP + " " +  allT);
                System.out.println(partner.getDisplayName() + " || " + trader.getDisplayName());
            	if (allP > allT) {
        			partner.getPackets().sendIComponentText(31, 19, 	"You win"  );
        			trader.getPackets().sendIComponentText(31, 19, 	"You lose" );
        			reward(partner);
        		
        		} else {
        			partner.getPackets().sendIComponentText(31, 19,  "You lose" );
        			trader.getPackets().sendIComponentText(31, 19,  "You win");
        			reward(trader);
        		}
            }

        }, 9);
    	
	}

	public void reward(Player player) {
		for (Item itemAtIndex : traderItemsOffered.toArray()) {
			if (itemAtIndex != null) {
				player.getInventory().addItem(itemAtIndex);
				System.out.println(itemAtIndex.getId());
			}
		}
		for (Item itemAtIndex : partnerItemsOffered.toArray()) {
			if (itemAtIndex != null) {
				player.getInventory().addItem(itemAtIndex);
				System.out.println(itemAtIndex.getId() + " - another player");
			}
		}
		
	}
       

     
        	
       
        
		

	

	/**
	 * Gives items to partner / other
	 */
	private void giveItems() {
		try {
			if (!trader.getInventory().getItems()
					.hasSpaceFor(partnerItemsOffered)) {
				partner.getPackets()
						.sendGameMessage(
								"Other player does not have enough space in their inventory.");
				trader.getPackets().sendGameMessage(
						"You do not have enough space in your inventory.");
				tradeFailed();
				return;
			} else if (!partner.getInventory().getItems()
					.hasSpaceFor(traderItemsOffered)) {
				trader.getPackets()
						.sendGameMessage(
								"Other player does not have enough space in their inventory.");
				partner.getPackets().sendGameMessage(
						"You do not have enough space in your inventory.");
				tradeFailed();
				return;
			}
			endSession();
			partner.getInventory().refresh();
			partner.getPackets().sendGameMessage("Your dice duel will be started, goodluck");
			trader.getInventory().refresh();
			trader.getPackets().sendGameMessage("Your dice duel will be started, goodluck");
		    sendDicing();
          
            	
           
            
			} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * gets items offered
	 * 
	 * @param p
	 * @return
	 */
	public ItemsContainer<Item> getPlayerItemsOffered(Player p) {
		return (p.equals(trader) ? traderItemsOffered : partnerItemsOffered);
	}

	public enum TradeState {
		STATE_ONE, STATE_TWO
	}

	/**
	 * resets the accept trade.
	 */
	public void resetAccept() {
		partnerDidAccept = traderDidAccept = false;
		switch (currentState) {
		case STATE_ONE:
			partner.getPackets().sendIComponentText(335, 37, "");
			trader.getPackets().sendIComponentText(335, 37, "");
			break;
		case STATE_TWO:
			partner.getPackets().sendIComponentText(334, 34, "");
			trader.getPackets().sendIComponentText(334, 34, "");
			break;
		}
	}

	/**
	 * Gets the state of trade
	 * 
	 * @return state
	 */
	public TradeState getState() {
		return currentState;
	}
}