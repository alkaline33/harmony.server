package com.rs.game.player.content.grandExchange;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.rs.game.World;
import com.rs.game.item.Item;
import com.rs.game.player.Player;

public class GrandExchangeLoader {

	public static void initialize() {
		List<GrandExchangeOffer> offerList = load();
		if (offerList == null) {
			offerList = new ArrayList<GrandExchangeOffer>();
		}
		ListIterator<GrandExchangeOffer> it = offerList.listIterator();
		while(it.hasNext()) {
			GrandExchangeOffer offer = it.next();
			if (offer.getPrimaryAmount() == 0
					&& offer.getSecondaryAmount() == 0
					&& offer.getExtraCash() == 0) {
				//System.out.println(offer + " had 0 amount so removed");
				it.remove();
			}
		}
		save(offerList);
		for (GrandExchangeOffer offer : offerList) {
			offers.add(offer);
		}
		logger.info("Completely loaded " + offers.size() + " grand exchange offers.");
	}

	public static void addOffer(GrandExchangeOffer offer) {
		offers.add(offer);
		save(offers);
	}

	public static void removeOffer(GrandExchangeOffer offer) {
		ListIterator<GrandExchangeOffer> it = offers.listIterator();
		while(it.hasNext()) {
			GrandExchangeOffer o = it.next();
			if (o.equals(offer)) {
				it.remove();
			}
		}
		save(offers);
	}

	public static void saveOffer(GrandExchangeOffer offer) {
		ListIterator<GrandExchangeOffer> it = offers.listIterator();
		while(it.hasNext()) {
			GrandExchangeOffer o = it.next();
			if (o.equals(offer)) {
				it.remove();
				offers.add(offer);
			}
		}
		save(offers);
	}

	/**
	 * Gets the offers by the player
	 * @param owner The player who you are getting the offers of
	 * @return A {@code List} {@code Object} with all of the players offers
	 */
	public static List<GrandExchangeOffer> getOffersForPlayer(String owner) {
		List<GrandExchangeOffer> playerOffers = new ArrayList<GrandExchangeOffer>();
		ListIterator<GrandExchangeOffer> it = offers.listIterator();
		while(it.hasNext()) {
			GrandExchangeOffer offer = it.next();
			if (offer.getOwner().equals(owner)) {
				playerOffers.add(offer);
			}
		}
		return playerOffers;
	}

	/**
	 * Where the data will save/read from
	 * @return An {@code String} object of the location
	 */
	private static String getFileLocation() {
		return "data/grandExchange.json";
	}

	/**
	 * Populates a list with all of the data in the {@link #getFileLocation()}
	 * 
	 * @return The list with data
	 */
	public static List<GrandExchangeOffer> load() {
		List<GrandExchangeOffer> autospawns = null;
		String json = null;
		try {
			File file = new File(getFileLocation());
			if (!file.exists()) {
				return null;
			}
			FileReader reader = new FileReader(file);
			char[] chars = new char[(int) file.length()];
			reader.read(chars);
			json = new String(chars);
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		autospawns = gson.fromJson(json, new TypeToken<List<GrandExchangeOffer>>() {
		}.getType());
		return autospawns;
	}

	/**
	 * Saves the list to the file
	 * 
	 * @param spawns
	 *            The list to save
	 */
	public static void save(List<GrandExchangeOffer> spawns) {
		try {
			FileWriter fw = new FileWriter(getFileLocation());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(gson.toJson(spawns));
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * The list of offers
	 * @return
	 */
	public static List<GrandExchangeOffer> getOffers() {
		Collections.reverse(offers);
		return offers;
	}

	/**
	 * The gson instance
	 */
	private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

	private static Logger logger = Logger.getLogger(GrandExchangeLoader.class.getName());

	public static final List<GrandExchangeOffer> offers = new ArrayList<GrandExchangeOffer>();

	public static class GrandExchangeOffer {

		/**
		 * The constructor for the grand exchange offer
		 * 
		 * @param owner
		 *            The owner of the offer
		 * @param slot
		 *            The slot of the offer
		 * @param item
		 *            The {@code Item} {@code Object} of the offer
		 * @param amount
		 *            The amount of the item you wish to exchange
		 * @param type
		 *            The {@code Type} enum value of the offer
		 */
		public GrandExchangeOffer(String owner, int slot, Item item, int amount, int price, Type type) {
			this.owner = owner;
			this.slot = slot;
			this.item = item;
			primaryAmount = amount;
			setSecondaryAmount(0);
			setPrice(price);
			this.type = type;
		}

		public String getOwner() {
			return owner;
		}

		public Item getItem() {
			return item;
		}

		/**
		 * The amount the offer was set to buy/sell for. This value does not change!
		 * @return
		 */
		public int getPrimaryAmount() {
			return primaryAmount;
		}

		/**
		 * The amount of the item that has been bought/sold so far. This value changes when the offer updates
		 * @return
		 */
		public int getSecondaryAmount() {
			return secondaryAmount;
		}

		/**
		 * Sets the amount of the item that has been bought/sold so far.
		 * @param secondaryAmount The amount to set it to.
		 */
		public void setSecondaryAmount(int secondaryAmount) {
			this.secondaryAmount = secondaryAmount;
		}

		public Type getType() {
			return type;
		}

		public boolean isFinished() {
			return secondaryAmount == getPrimaryAmount();
		}
		
		public int getSlot() {
			return slot;
		}

		public int getPrice() {
			return price;
		}
		
		public void setPrice(int price) {
			long price2 = (long) primaryAmount * price;
			if (price2 > Integer.MAX_VALUE || price2 == Integer.MAX_VALUE
					|| price2 >= Integer.MAX_VALUE || price2 < 0 || price <= 0) {
				//System.out.println("Attempted to set a bad price.");
				return;
			}
			this.price = price;
		}
		
		public void setPrimaryAmount(int primaryAmount) {
			long price = (long) primaryAmount * (long) this.price;
			if (price > Integer.MAX_VALUE || price == Integer.MAX_VALUE
					|| price >= Integer.MAX_VALUE || price < 0 || price <= 0) {
				//System.out.println("Attempted to set a bad amount.");
				return;
			}
			this.primaryAmount = primaryAmount;
		}
		
		public void forcePrimaryAmount(int primaryAmount) {
			this.primaryAmount = primaryAmount;
		}

		/**
		 * The extra cash from the offer, this is incremented if while
		 * exchanging, either player is overpaying
		 * 
		 * @return
		 */
		public int getExtraCash() {
			return extraCash;
		}

		public void setExtraCash(int extraCash) {
			this.extraCash = extraCash;
		}
		
		@Override
		public boolean equals(Object o) {
			GrandExchangeOffer o2 = (GrandExchangeOffer) o;
			return owner.equals(o2.getOwner()) && item.getId() == o2.getItem().getId() && slot == o2.getSlot() && price == o2.getPrice() && primaryAmount == o2.getPrimaryAmount() && type == o2.getType();
		}

		public enum Type {
			BUY, SELL
		}

		@Override
		public String toString() {
			return "[owner=" + owner + ", type=" + type + ", slot=" + slot + ", price=" + price + ", primaryAmount=" + getPrimaryAmount() + ", secondaryAmount=" + secondaryAmount + "]";
		}

		public void notifyUpdated() {
			Player player = World.getPlayerByDisplayName(owner);
			if (player != null) {
				if (player.getInterfaceManager().containsInterface(105) || player.getInterfaceManager().containsTab(105)) {
					GrandExchangeSystem.get().display(player);
				}
				player.sendMessage("One or more of your grand exchange offers have been updated!");
				
			}
		}

		public boolean isAborted() {
			if (isFinished()) {
				return false;
			}
			return aborted;
		}

		public void setAborted(boolean aborted) {
			this.aborted = aborted;
		}
		

		public boolean isProcessing() {
			return processing;
		}

		public void setProcessing(boolean processing) {
			this.processing = processing;
		}

		private final String owner;
		private final Item item;
		private final int slot;
		private int price;
		private int primaryAmount;
		private int secondaryAmount;
		private int extraCash;
		private final Type type;
		private boolean aborted;
		private boolean processing;
	}
}
