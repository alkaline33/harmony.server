package com.rs.game.player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.rs.game.item.Item;
import com.rs.game.player.content.ItemConstants;

public class GearPresets implements Serializable {

	private static final long serialVersionUID = -5872243601484954245L;
	public transient Player player;
	private List<Gear> gears;

	public GearPresets() {
		gears = new ArrayList<Gear>();
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public void saveGear(String name) {
		Item[] equipmentItems = new Item[15];
		for (int i = 0; i < equipmentItems.length; i++) {// current equipment
			Item item = player.getEquipment().getItem(i);
			if (item == null) {
				continue;
			}
			equipmentItems[i] = item;
		}
		Item[] inventoryItems = new Item[28];
		for (int i = 0; i < inventoryItems.length; i++) {// current inventory
			Item item = player.getInventory().getItem(i);
			if (item == null) {
				continue;
			}
			inventoryItems[i] = item;
		}
		boolean ancientCurses = player.getPrayer().isAncientCurses();
		int spellBook = player.getCombatDefinitions().getSpellBook();
		gears.add(new Gear(name, equipmentItems, inventoryItems, ancientCurses, spellBook));
		player.getPackets().sendGameMessage("Saved gear by the name of " + name + " .");
	}

	public void loadGear(int gearIndex) {
		Gear gear = gears.get(gearIndex); // findGearByName(name);
		if (gear == null) {
			return;
		}
		int usedSlots = 28 - player.getInventory().getFreeSlots();
		int spaceUsed = player.getBank().getBankSize() + usedSlots + player.getEquipment().getItems().getUsedSlots();
		if (spaceUsed >= 517) {
			player.sendMessage("<col=ff0000>It seems your bank is full and preventing the preset from banking your items, please clear your inventory or make space in your bank.");
			return;
		}
		player.getBank().depositAllEquipment(false);
		player.getBank().depositAllInventory(false);
		if (player.getInventory().getFreeSlots() < 28) {
			player.sendMessage("<col=ff0000>It seems your bank is full and preventing the preset from banking your items, please clear your inventory or make space in your bank.");
			return;
		}
		if (player.getEquipment().wearingArmour()) {
			player.sendMessage("<col=ff0000>It seems your bank is full and preventing the preset from banking your items, please clear your inventory or make space in your bank.");
			return;
		}
		List<Item> missingItems = new ArrayList<Item>();
		List<Item> wrongAmountItems = new ArrayList<Item>();
		List<Item> enoghhSpaceItems = new ArrayList<Item>();
		List<Item> enoughRequirmentsItems = new ArrayList<Item>();
		for (int i = 0; i < 15; i++) {
			Item item = gear.getEquipmentItems()[i];
			if (item == null) {
				continue;
			}
			
			if (!player.getBank().containsItem(item.getId(), 1)) {
				missingItems.add(item);
				continue;
			} else if (player.getBank().containsItem(item.getId(), 1) && cantWearItem(item)) {
				enoughRequirmentsItems.add(item);
				continue;
			} else if (player.getBank().containsItem(item.getId(), 1)
					&& player.getBank().getItem(item.getId()).getAmount() < item.getAmount()) {
				Item bankItem = player.getBank().getItem(item.getId());
				player.getEquipment().getItems().set(i, new Item(item.getId(), bankItem.getAmount()));
				player.getEquipment().refresh(i);
				player.getBank().removeItem(player.getBank().getItemSlot(bankItem.getId()), bankItem.getAmount(), true,
						false);
				wrongAmountItems.add(new Item(item.getId(), item.getAmount() - bankItem.getAmount()));
				continue;
			} else {
				player.getEquipment().getItems().set(i, item);
				player.getEquipment().refresh(i);
				player.getBank().removeItem(player.getBank().getItemSlot(item.getId()), item.getAmount(), true, false);
			}
		}
		player.getAppearence().generateAppearenceData();
		for (int i = 0; i < 28; i++) {
			Item item = gear.getInventoryItems()[i];
			if (item == null) {
				continue;
			}
			boolean noted = item.getDefinitions().isNoted();
			if (!player.getBank().containsItem(noted ? item.getDefinitions().getCertId() : item.getId(), 1)) {
				missingItems.add(item);
				continue;
			} else if (player.getBank().containsItem(noted ? item.getDefinitions().getCertId() : item.getId(), 1)
					&& player.getBank().getItem(noted ? item.getDefinitions().getCertId() : item.getId())
							.getAmount() < item.getAmount()) {
				Item bankItem = player.getBank().getItem(noted ? item.getDefinitions().getCertId() : item.getId());
				if (player.getInventory().addItem(new Item(item.getId(), bankItem.getAmount()))) {
					player.getBank().removeItem(
							player.getBank().getItemSlot(noted ? item.getDefinitions().getCertId() : item.getId()),
							bankItem.getAmount(), true, false);
					wrongAmountItems.add(new Item(item.getId(), item.getAmount() - bankItem.getAmount()));
					continue;
				}
				enoghhSpaceItems.add(new Item(item.getId(), item.getAmount() - bankItem.getAmount()));
			} else {
				if (player.getInventory().addItem(item)) {
					player.getBank().removeItem(
							player.getBank().getItemSlot(noted ? item.getDefinitions().getCertId() : item.getId()),
							item.getAmount(), true, false);
					continue;
				}
				enoghhSpaceItems.add(new Item(item.getId(), item.getAmount()));
			}
		}
		//player.getPrayer().setPrayerBook(gear.isAncientCurses());
	//	player.getCombatDefinitions().setSpellBook(gear.getSpellBook());
		int num = 0;
		if (missingItems.size() > 0) {
			player.getPackets()
					.sendGameMessage("<col=FF0000>Here is a list of items that we couldn't find in your bank:\n</col>");
			num = 1;
			for (int i = 0; i < missingItems.size(); i++) {
				Item item = missingItems.get(i);
				if (item == null) {
					continue;
				}
				player.getPackets().sendGameMessage("<col=FF0000>" + num + ") " + item.getName() + " .</col>");
				num++;
			}
		}
		if (enoughRequirmentsItems.size() > 0) {
			player.getPackets().sendGameMessage(
					"<col=00FFFF>Here is a list of items that you don't have enough requirment to wear:\n</col>");
			num = 1;
			for (int i = 0; i < enoughRequirmentsItems.size(); i++) {
				Item item = enoughRequirmentsItems.get(i);
				if (item == null) {
					continue;
				}
				player.getPackets().sendGameMessage("<col=00FFFF>" + num + ") " + item.getName() + " .</col>");
				num++;
			}
		}
		if (enoghhSpaceItems.size() > 0) {
			player.getPackets().sendGameMessage("<col=FFFF00>there were not enough space for these items:\n</col>");
			num = 1;
			for (int i = 0; i < enoghhSpaceItems.size(); i++) {
				Item item = enoghhSpaceItems.get(i);
				if (item == null) {
					continue;
				}
				player.getPackets().sendGameMessage("<col=FFFF00>" + num + ") " + item.getName() + ", amount missing = "
						+ item.getAmount() + " .</col>");
				num++;
			}
		}
		if (wrongAmountItems.size() > 0) {
			player.getPackets()
					.sendGameMessage("<col=FFFF00>We couldn't take the correct amount for these items:\n</col>");
			num = 1;
			for (int i = 0; i < wrongAmountItems.size(); i++) {
				Item item = wrongAmountItems.get(i);
				if (item == null) {
					continue;
				}
				player.getPackets().sendGameMessage("<col=FFFF00>" + num + ") " + item.getName() + ", amount missing = "
						+ item.getAmount() + " .</col>");
				num++;
			}
		}
	}

	public boolean cantWearItem(Item item) {
		if (!ItemConstants.canWear(item, player)) {
			return true;
		}
		HashMap<Integer, Integer> requiriments = item.getDefinitions().getWearingSkillRequiriments();
		boolean hasRequiriments = true;
		if (requiriments != null) {
			for (int skillId : requiriments.keySet()) {
				if (skillId > 24 || skillId < 0) {
					continue;
				}
				int level = requiriments.get(skillId);
				if (level < 0 || level > 120) {
					continue;
				}
				if (player.getSkills().getLevelForXp(skillId) < level) {
					hasRequiriments = false;
				}
			}
		}
		if (!hasRequiriments) {
			return true;
		}
		return false;
	}

	public Gear findGearByName(String name) {
		for (int i = 0; i < gears.size(); i++) {
			Gear gear = gears.get(i);
			if (gear == null) {
				continue;
			}
			if (gear.getName().equalsIgnoreCase(name)) {
				player.getPackets().sendGameMessage("found gear");
				return gear;
			}
		}
		return null;
	}

	public boolean removeGear(int index) {
		gears.remove(index);
		return true;
	}

	public boolean removeGearByName(String name) {
		Gear gear = findGearByName(name);
		if (gear == null) {
			return false;
		}
		return gears.remove(gear);
	}

	public List<Gear> getGears() {
		return gears;
	}

	public static final class Gear implements Serializable {

		private static final long serialVersionUID = 1043684233443861865L;

		private String name;
		private Item[] equipmentItems;
		private Item[] inventoryItems;
		private boolean ancientCurses;
		private int spellBook;

		public Gear(String name, Item[] equipmentItems, Item[] inventoryItems, boolean ancientCurses, int spellBook) {
			setName(name);
			setEquipmentItems(equipmentItems);
			setInventoryItems(inventoryItems);
			setAncientCurses(ancientCurses);
			setSpellBook(spellBook);
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Item[] getEquipmentItems() {
			return equipmentItems;
		}

		public void setEquipmentItems(Item[] equipmentItems) {
			this.equipmentItems = equipmentItems;
		}

		public boolean isAncientCurses() {
			return ancientCurses;
		}

		public void setAncientCurses(boolean ancientCurses) {
			this.ancientCurses = ancientCurses;
		}

		public Item[] getInventoryItems() {
			return inventoryItems;
		}

		public void setInventoryItems(Item[] inventoryItems) {
			this.inventoryItems = inventoryItems;
		}

		public int getSpellBook() {
			return spellBook;
		}

		public void setSpellBook(int spellBook) {
			this.spellBook = spellBook;
		}

	}
}
