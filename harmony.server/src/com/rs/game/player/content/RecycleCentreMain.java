package com.rs.game.player.content;

import com.rs.Settings;
import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.Animation;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.game.player.content.event.season.SeasonEvent;
import com.rs.utils.Colors;

public class RecycleCentreMain {

	public final static int RUNECOIN = 29256;

	public static int PriceCheck(int itemUsed) {
		String name = ItemDefinitions.getItemDefinitions(itemUsed).getName();
		if (name.contains("bolts") || name.contains("coins") || name.equalsIgnoreCase("1b ticket") || name.equalsIgnoreCase("dragon hatchet") || name.equalsIgnoreCase("dragon pickaxe") || name.equalsIgnoreCase("drygore longsword shard") || name.equalsIgnoreCase("drygore rapier shard") || name.equalsIgnoreCase("drygore mace shard") || name.equalsIgnoreCase("1m ticket") || name.equalsIgnoreCase("2b ticket")) {
			return 0;
		}

		if (name.equalsIgnoreCase("onyx") || name.equalsIgnoreCase("uncut onyx")) {
			return 10;
		} else if (name.equalsIgnoreCase("silverhawk feather")) {
			return 1;
		} else if (name.equalsIgnoreCase("bandos boots") || name.equalsIgnoreCase("odium ward") || name.equalsIgnoreCase("ranger boots") || name.equalsIgnoreCase("malediction ward") || name.equalsIgnoreCase("zamorakian spear") || name.equalsIgnoreCase("dragonfire shield") || name.equalsIgnoreCase("malevolent energy") || name.equalsIgnoreCase("tectonic energy") || name.equalsIgnoreCase("sirenic scale")) {
			return 60;
		} else if (name.equalsIgnoreCase("Holy scroll") || name.equalsIgnoreCase("manic scroll") || name.equalsIgnoreCase("mythical scroll") || name.equalsIgnoreCase("spellbinding scroll") || name.equalsIgnoreCase("berserker scroll") || name.equalsIgnoreCase("scroll of protection")) {
			return 90;
		} else if (name.contains("godsword") || name.contains("Occult") || name.contains("Pet Perk") || name.contains("Spectral")) {
			return 120;
		} else if (name.equalsIgnoreCase("brutal whip") || name.equalsIgnoreCase("primordial boots") || name.equalsIgnoreCase("pegasian boots") || name.equalsIgnoreCase("eternal boots") || name.contains("Kraken tent") || name.contains("Abyssal tent")) {
			return 180;
		} else if (name.equalsIgnoreCase("zenyte shard") || name.contains("of Orison") || name.contains("of Protection") || name.contains("of Brutality") || name.contains("of suffering") || name.contains("of torture") || name.contains("of anguish") || name.contains("Tormented bracelet")) {
			return 200;
		} else if (name.equalsIgnoreCase("Dragon claws") || name.equalsIgnoreCase("abyssal dagger") || name.contains("blowpipe") || name.contains("Trident of") || name.contains("Toxic staff of")) {
			return 380;
		} else if (name.contains("sigil") && !name.contains("Spectral")) {
			return 300;
		} else if (name.contains("Torva") || name.equalsIgnoreCase("acid dye") || name.contains("Third-age") || name.equalsIgnoreCase("abyssal bludgeon") || name.contains("Pernix") || name.contains("Virtus") || name.contains("Zaryte")) {
			return 450;
		} else if (name.contains("spirit shield") && !name.contains("Spectral") && !name.equalsIgnoreCase("Spirit shield") && !name.equalsIgnoreCase("Blessed spirit shield")) {
			return 550;
		} else if (name.equalsIgnoreCase("Malevolent kiteshield") || name.equalsIgnoreCase("vengeful kiteshield") || name.contains("exquisite longsword") || name.equalsIgnoreCase("merciless kiteshield")) {
			return 600;
		} else if (name.equalsIgnoreCase("Donator ring") || name.equalsIgnoreCase("hydrix") || name.contains("Donator gloves") || name.contains("Donator boots") || name.contains("Donator cloak")) {
			return 850;
		} else if (name.contains("Dragon warhammer")) {
			return 900;
		} else if (name.contains("of Immense power") || name.contains("Drygore") || name.contains("Ascension") || name.startsWith("Noxious") || name.contains("Seismic") || name.contains("Tectonic") || name.contains("Malevolent") || name.contains("Sirenic")) {
			return 1500;
		} else if (name.equalsIgnoreCase("twisted bow") || name.equalsIgnoreCase("scythe of vitur") || name.equalsIgnoreCase("elder maul") || name.equalsIgnoreCase("sanguinesti staff")) {
			return 4000;
		} else if (name.contains("Ancestral") || name.contains("Justiciar") || name.equalsIgnoreCase("ghrazi rapier") || name.equalsIgnoreCase("avernic defender") || name.equalsIgnoreCase("kodai wand")) {
			return 2500;
		} else if (name.equalsIgnoreCase("new crystal bow") || name.equalsIgnoreCase("crystal shield full") || name.equalsIgnoreCase("dragon sq shield")) {
			return 0;
		}

		switch (ItemDefinitions.getItemDefinitions(itemUsed).getId()) {
			case 4151: //whip
			case 21369: //whip vine
				return 25;
			case 21371: //abyssal vine whip
			case 21372: //abyssal vine whip
			case 21373: //abyssal vine whip
			case 21374: //abyssal vine whip
			case 21375: //abyssal vine whip
				return 50;

		}

		return ItemDefinitions.getItemDefinitions(itemUsed).getValue() / 500000;
	}

	public static boolean ValueItem(Player player, int itemUsed) {
		/**
		 * Price checks the item
		 */
		int itemvalue = PriceCheck(itemUsed);
		for (int bosspet : Settings.PETS_WITH_PERKS) {
			if (player.getPetManager().getNpcId() == bosspet && player.getPetPerk().getContainer().contains(new Item(29248))) {
				// System.out.println("got");
				itemvalue *= 1.10;
			}
		}
		if (itemUsed == -1) {
			player.sendMessage(Colors.red + "This item cannot be recycled!");
			return false;
		}
		if (itemvalue < 1) {
			player.sendMessage(Colors.red + "This item cannot be recycled!");
			return false;
		}
		if (!ItemConstants.isTradeable(new Item(itemUsed))) {
			player.sendMessage(Colors.red + "This item cannot be recycled!");
			return false;
		}
		player.sendMessage(Colors.cyan + "Your " + ItemDefinitions.getItemDefinitions(itemUsed).getName() + " can be recycled for " + itemvalue + " Runecoins.");
		return true;
	}

	public static boolean ExchangeItem(Player player, int itemUsed) {
		if (player.getActionManager().getActionDelay() > 0) {
			return false;
		}

		int itemvalue = PriceCheck(itemUsed);
		for (int bosspet : Settings.PETS_WITH_PERKS) {
			if (player.getPetManager().getNpcId() == bosspet && player.getPetPerk().getContainer().contains(new Item(29248))) {
				// System.out.println("got");
				itemvalue *= 1.10;
			}
		}
		if (itemUsed == -1) {
			player.sendMessage(Colors.red + "This item cannot be recycled!");
			return false;
		}
		if (itemvalue < 1) {
			player.sendMessage(Colors.red + "This item cannot be recycled!");
			return false;
		}
		if (!player.getInventory().contains(itemUsed)) {
			player.sendMessage(Colors.red + "The item is no longer in your inventory.");
			return false;
		}
		if (!ItemConstants.isTradeable(new Item(itemUsed))) {
			player.sendMessage(Colors.red + "This item cannot be recycled!");
			return false;
		}
		player.setNextAnimation(new Animation(4873));
		player.getInventory().deleteItem(itemUsed, 1);
		if (player.runecoinsobtained < 300 && player.runecoinsobtained + itemvalue >= 300) {
			World.sendIconWorldMessage("" + player.getDisplayName() + " has just unlocked the title *<col=0FF1E7>Recycled</col>*!", false);
		}
		if (player.runecoinsobtained < 1500 && player.runecoinsobtained + itemvalue >= 1500) {
			World.sendIconWorldMessage("" + player.getDisplayName() + " has just unlocked the title *<col=F1650F>the Regenerated</col>*!", false);
		}
		if (player.runecoinsobtained < 5000 && player.runecoinsobtained + itemvalue >= 5000) {
			World.sendIconWorldMessage("" + player.getDisplayName() + " has just unlocked the title *<col=1DF10F>Eco-friendly</col>*!", false);
		}
		if (player.runecoinsobtained < 7500 && player.runecoinsobtained + itemvalue >= 7500) {
			World.sendIconWorldMessage("" + player.getDisplayName() + " has just unlocked the title *<col=DC0FF1>Biodegradeable</col>*!", false);
		}
		if (player.runecoinsobtained < 10000 && player.runecoinsobtained + itemvalue >= 10000) {
			World.sendIconWorldMessage("" + player.getDisplayName() + " has just unlocked the title *<col=42F10F><shad=FFFFFF>the Toxic</col></shad>*!", false);
		}
		if (player.dailyrunecoins < 1000 && player.dailyrunecoins + itemvalue >= 1000) {
			player.sendMessage(Colors.lightGray + "<img=6>You have completed the Daily Money Maker: Obtain 1,000 Runecoins! Your rewards have been placed in your bank.");
			player.getBank().addItem(25202, 1, true);
			player.getBank().addItem(5022, 25, true);
		}
		SeasonEvent.HandleActivity(player, "Recycle Centre", itemvalue);
		player.dailyrunecoins += itemvalue;
		player.runecoinsobtained += itemvalue;
		Settings.GpSyncAmount += ItemDefinitions.getItemDefinitions(itemUsed).getValue();
		player.sendMessage(Colors.cyan + "You exchange your " + ItemDefinitions.getItemDefinitions(itemUsed).getName() + " for " + itemvalue + " Runecoins.");
		player.printRecycleCentre(player, "Exchanged: " + ItemDefinitions.getItemDefinitions(itemUsed).getName() + " for " + itemvalue + " Runecoins.");
		player.getInventory().addItem(RUNECOIN, itemvalue);
		return true;
	}


	/**
	 * RuneCoiners
	 */

	public static boolean CheckRunecoiner(Player player, int dropId) {
		int itemvalue = PriceCheck(dropId);
		if (itemvalue > player.runecoinervalue) {
			return false;
		}
		if (dropId == -1) {
			return false;
		}
		if (itemvalue < 1) {
			return false;
		}
		if (!ItemConstants.isTradeable(new Item(dropId))) {
			return false;
		}
		return true;
	}

	public static boolean ExchangeRunecoiner(Player player, int dropId, int quantity, int x, int y) {
		int itemvalue = PriceCheck(dropId) * quantity;
		for (int bosspet : Settings.PETS_WITH_PERKS) {
			if (player.getPetManager().getNpcId() == bosspet && player.getPetPerk().getContainer().contains(new Item(29248))) {
				// System.out.println("got");
				itemvalue *= 1.10;
			}
		}
		if (player.runecoinsobtained < 300 && player.runecoinsobtained + itemvalue >= 300) {
			World.sendIconWorldMessage("" + player.getDisplayName() + " has just unlocked the title *<col=0FF1E7>Recycled</col>*!", false);
		}
		if (player.runecoinsobtained < 1500 && player.runecoinsobtained + itemvalue >= 1500) {
			World.sendIconWorldMessage("" + player.getDisplayName() + " has just unlocked the title *<col=F1650F>the Regenerated</col>*!", false);
		}
		if (player.runecoinsobtained < 5000 && player.runecoinsobtained + itemvalue >= 5000) {
			World.sendIconWorldMessage("" + player.getDisplayName() + " has just unlocked the title *<col=1DF10F>Eco-friendly</col>*!", false);
		}
		if (player.runecoinsobtained < 7500 && player.runecoinsobtained + itemvalue >= 7500) {
			World.sendIconWorldMessage("" + player.getDisplayName() + " has just unlocked the title *<col=DC0FF1>Biodegradeable</col>*!", false);
		}
		if (player.runecoinsobtained < 10000 && player.runecoinsobtained + itemvalue >= 10000) {
			World.sendIconWorldMessage("" + player.getDisplayName() + " has just unlocked the title *<col=42F10F><shad=FFFFFF>the Toxic</col></shad>*!", false);
		}
		if (player.dailyrunecoins <= 1000 && player.dailyrunecoins + itemvalue >= 1000) {
			player.sendMessage(Colors.lightGray + "<img=6>You have completed the Daily Money Maker: Obtain 1,000 Runecoins! Your rewards have been placed in your bank.");
			player.getBank().addItem(25202, 1, true);
			player.getBank().addItem(5022, 25, true);
		}
		SeasonEvent.HandleActivity(player, "Recycle Centre", itemvalue);
		player.dailyrunecoins += itemvalue;
		player.runecoinsobtained += itemvalue;
		Settings.GpSyncAmount += ItemDefinitions.getItemDefinitions(dropId).getValue();
		player.sendMessage(Colors.cyan + "Your "+ItemDefinitions.getItemDefinitions(dropId).getName()+" drop was exchanged for " + itemvalue + " Runecoins.");
		player.printRecycleCentre(player, "Exchanged: " + ItemDefinitions.getItemDefinitions(dropId).getName() + " for " + itemvalue + " Runecoins.");
		World.addGroundItem(new Item(RUNECOIN, itemvalue), new WorldTile(x, y, player.getPlane()), player, false, 180, true);
		//player.getInventory().addItem(RUNECOIN, itemvalue);
		return false;
	}


}
