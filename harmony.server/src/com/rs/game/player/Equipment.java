package com.rs.game.player;

import java.io.Serializable;

import com.rs.Settings;
import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.item.Item;
import com.rs.game.item.ItemsContainer;
import com.rs.game.minigames.duel.DuelControler;
import com.rs.game.player.actions.Bonfire;
import com.rs.utils.ItemExamines;

public final class Equipment implements Serializable {

	private static final long serialVersionUID = -4147163237095647617L;

	public static final byte SLOT_HAT = 0, SLOT_CAPE = 1, SLOT_AMULET = 2, SLOT_WEAPON = 3, SLOT_CHEST = 4, SLOT_SHIELD = 5, SLOT_LEGS = 7, SLOT_HANDS = 9, SLOT_FEET = 10, SLOT_RING = 12, SLOT_ARROWS = 13, SLOT_AURA = 14, SLOT_WINGS = 14;

	private ItemsContainer<Item> items;
	private ItemsContainer<Item> cosmeticItems;
	private boolean[] hiddenSlots;

	private transient Player player;
	private transient int equipmentHpIncrease;

	static final int[] DISABLED_SLOTS = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0 };

	public Equipment() {
		items = new ItemsContainer<Item>(15, false);
		cosmeticItems = new ItemsContainer<Item>(15, false);
		hiddenSlots = new boolean[15];
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public void init() {
		if (cosmeticItems == null) {
			cosmeticItems = new ItemsContainer<Item>(15, false);
		}
		if (hiddenSlots == null) {
			hiddenSlots = new boolean[15];
		}
		player.getPackets().sendItems(94, items);
		refresh(null);
	}

	public void refresh(int... slots) {
		if (slots != null) {
			if (player.getTemporaryAttributtes().get("Cosmetics") != null) {
				Item[] cosmetics = items.getItemsCopy();
				for (int i = 0; i < cosmetics.length; i++) {
					Item item = cosmetics[i];
					if (i == SLOT_AURA || i == SLOT_ARROWS || i == SLOT_RING) {
						continue;
					}
					if (item == null) {
						cosmetics[i] = new Item(0);
					}
				}
				// player.sendMessage(SLOT_SHIELD);
				player.getPackets().sendUpdateItems(94, cosmetics, slots);
			} else {
				player.getPackets().sendUpdateItems(94, items, slots);
			}
			player.getCombatDefinitions().checkAttackStyle();
		}
		player.getCombatDefinitions().refreshBonuses();
		refreshConfigs(slots == null);
	}

	public void reset() {
		items.reset();
		init();
	}

	public Item getItem(int slot) {
		return items.get(slot);
	}

	public void sendExamine(int slotId) {
		Item item = items.get(slotId);
		if (item == null) {
			return;
		}
		player.getPackets().sendGameMessage(ItemExamines.getExamine(item));
	}

	public long getEquipmentValue(Player player) {
		long value = 0;
		for (Item equipment : player.getInventory().getItems().toArray()) {
			if (equipment == null) {
				continue;
			}
			long amount = equipment.getAmount();
			value += equipment.getDefinitions().getValue() * amount;
		}
		return value;
	}

	public void refreshConfigs(boolean init) {
		double hpIncrease = 0;
		for (int index = 0; index < items.getSize(); index++) {
			Item item = items.get(index);
			if (item == null) {
				continue;
			}
			int id = item.getId();
			if (index == Equipment.SLOT_HAT) {
				if (id == 20135 || id == 20137 || id == 29824 || id == 29166 // torva
						|| id == 20147 || id == 20149 || id == 29821 || id == 29163 // pernix
						|| id == 20159 || id == 20161 || id == 29818 || id == 29160 // virtus
						|| id == 28928
				) {
					hpIncrease += 66;
				}
			}
			if (index == Equipment.SLOT_HAT) {
				if (id == 29603 || id == 29611 || id == 29559 // l85
				) {
					hpIncrease += 33;
				}
			} else if (index == Equipment.SLOT_HANDS) {
				if (id == 24980 || id == 24981 || id == 24977 || id == 24978 || id == 24974 || id == 24975) {
					// gloves
					hpIncrease += 25;
				}
			} else if (index == Equipment.SLOT_FEET) {
				if (id == 24983 || id == 24984 || id == 24989 || id == 24990 || id == 24986 || id == 24987) {
					// boots
					hpIncrease += 25;
				}

			} else if (index == Equipment.SLOT_CHEST) {
				if (id == 20139 || id == 20141 || id == 29825 || id == 29167 // torva
						|| id == 20151 || id == 20153 || id == 29822 || id == 29164 // pernix
						|| id == 20163 || id == 20165 || id == 29819 || id == 29161// virtus
						|| id == 28929
				) {
					hpIncrease += 200;
				}
			} else if (index == Equipment.SLOT_CHEST) {
				if (id == 29605 || id == 29609 || id == 29560 // l85
				) {
					hpIncrease += 100;
				}
			} else if (index == Equipment.SLOT_LEGS) {
				if (id == 20143 || id == 20145 || id == 29823 || id == 29165 // torva
						|| id == 20155 || id == 20157 || id == 29820 || id == 29162// pernix
						|| id == 20167 || id == 20169 || id == 29817 || id == 29159// virtus
						|| id == 28927
				) {
					hpIncrease += 134;
				}
			} else if (index == Equipment.SLOT_LEGS) {
				if (id == 29604 || id == 29610 || id == 29561 // l85
				) {
					hpIncrease += 67;
				}
			} else if (index == Equipment.SLOT_HAT) {
				if (id == 29900 // torva e
						|| id == 29897 // pernix e
						|| id == 29894 // virtus e
				) {
					hpIncrease += 73;
				}
			} else if (index == Equipment.SLOT_HAT) {
				if (id == 28357 // sliske helm
				) {
					hpIncrease += 100;
				}
			} else if (index == Equipment.SLOT_CHEST) {
				if (id == 29899 // torva
						|| id == 29896 // pernix
						|| id == 29893 // virtus
				) {
					hpIncrease += 220;
				}
			} else if (index == Equipment.SLOT_LEGS) {
				if (id == 29898 // torva
						|| id == 29895 // pernix
						|| id == 29892 // virtus
				) {
					hpIncrease += 147;
				}
			} else if (index == Equipment.SLOT_CAPE) {
				if (id == 29986) {
					hpIncrease += 120;
				} else if (id == 20771) {
					hpIncrease += 120;
				} else if (id == 28967) {
					hpIncrease += 50;
				}
			} else if (index == Equipment.SLOT_ARROWS) {
				if (id == 28968) {
					hpIncrease += 50;
				}
			} else if (index == Equipment.SLOT_RING) {
				if (id == 24926) {
					hpIncrease += 50;
			} else if (id == 28930) {
					hpIncrease += 65;
			} else if (id == 28909) {
				hpIncrease += 250;
			} else if (id == 28908) {
			hpIncrease += 200;
			}
		}

		}
		if (player.getLastBonfire() > 0) {
			int maxhp = player.getSkills().getLevel(Skills.HITPOINTS) * 10;
			hpIncrease += maxhp * Bonfire.getBonfireBoostMultiplier(player) - maxhp;
		}
		if (player.getHpBoostMultiplier() != 0) {
			int maxhp = player.getSkills().getLevel(Skills.HITPOINTS) * 10;
			hpIncrease += maxhp * player.getHpBoostMultiplier();
		}
		for (int bosspet : Settings.PETS_WITH_PERKS) {
		if (player.getPetManager().getNpcId() == bosspet && player.getPetPerk().getContainer().contains(new Item(29122))) {
			hpIncrease += 100;
		}
		}
		if (hpIncrease != equipmentHpIncrease) {
			equipmentHpIncrease = (int) hpIncrease;
			if (!init) {
				player.refreshHitPoints();
			}
		}
	}

	public static boolean hideArms(Item item) {
		String name = item.getName().toLowerCase();
		if
		// temp old graphics fix, but bugs alil new ones
		(name.contains("d'hide body") || name.contains("dragonhide body") || name.equals("stripy pirate shirt") || name.contains("chainbody") && (name.contains("iron") || name.contains("bronze") || name.contains("steel") || name.contains("black") || name.contains("mithril") || name.contains("adamant") || name.contains("rune") || name.contains("gold d'hide") || name.contains("white")) || name.equals("leather body") || name.equals("hardleather body") || name.contains("studded body")) {
			return false;
		}
		return item.getDefinitions().getEquipType() == 6;
	}

	public static boolean hideHair(Item item) {
		// name.equals("malevolent helmet")
		return item.getDefinitions().getEquipType() == 8;
	}

	public static boolean showBear(Item item) {
		String name = item.getName().toLowerCase();
		return !hideHair(item) || name.contains("horns") || name.contains("hat")  || name.contains("Easter") || name.contains("Serpentine") || name.contains("Serpentine") || name.contains("afro") || name.contains("cowl") || name.contains("tattoo") || name.contains("headdress") || name.contains("lotus hood") || name.contains("tetsu") || name.contains("lotus") || name.contains("seasinger") || name.contains("bearhead") || name.contains("lava") || name.contains("mask") && !name.contains("sirenic") && !name.contains("h'ween") || name.contains("helm") && !name.contains("full") || name.contains("hood") && !name.contains("reaper");
	}

	public static int getItemSlot(int itemId) {
		return ItemDefinitions.getItemDefinitions(itemId).getEquipSlot();
	}

	public static boolean isTwoHandedWeapon(Item item) {
		String name = item.getName().toLowerCase();
		if (name.contains("ahrim's")) {
			return false;
		}
		return item.getDefinitions().getEquipType() == 5;
	}

	public int getWeaponRenderEmote() {
		Item weapon = cosmeticItems.get(3) != null && isCanDisplayCosmetic() ? cosmeticItems.get(3) : items.get(3);
		if (weapon == null) {
			return 1426;
		}
		if (weapon.getId() == 34028) {
			return 3382;
		}
		return weapon.getDefinitions().getRenderAnimId();
	}

	/*
	 * public int getWeaponRenderEmote() { Item weapon = items.get(3); if (weapon ==
	 * null) return 1426; return weapon.getDefinitions().getRenderAnimId(); }
	 */

	public boolean hasShield() {
		return items.get(5) != null;
	}

	public int getWeaponId() {
		Item item = items.get(SLOT_WEAPON);
		if (item == null) {
			return -1;
		}
		if (item.getId() == 4566) {
			player.getPackets().sendPlayerOption("Wack", 5, true);
		}
		return item.getId();
	}

	public int getChestId() {
		Item item = items.get(SLOT_CHEST);
		if (item == null) {
			return -1;
		}
		return item.getId();
	}

	public int getHatId() {
		Item item = items.get(SLOT_HAT);
		if (item == null) {
			return -1;
		}
		return item.getId();
	}

	public int getShieldId() {
		Item item = items.get(SLOT_SHIELD);
		if (item == null) {
			return -1;
		}
		return item.getId();
	}

	public int getLegsId() {
		Item item = items.get(SLOT_LEGS);
		if (item == null) {
			return -1;
		}
		return item.getId();
	}

	public void removeAmmo(int ammoId, int ammount) {
		if (ammount == -1) {
			items.remove(SLOT_WEAPON, new Item(ammoId, 1));
			refresh(SLOT_WEAPON);
		} else {
			items.remove(SLOT_ARROWS, new Item(ammoId, ammount));
			refresh(SLOT_ARROWS);
		}
	}

	public int getAuraId() {
		Item item = items.get(SLOT_AURA);
		if (item == null) {
			return -1;
		}
		return item.getId();
	}

	public int getCapeId() {
		Item item = items.get(SLOT_CAPE);
		if (item == null) {
			return -1;
		}
		return item.getId();
	}

	public int getRingId() {
		Item item = items.get(SLOT_RING);
		if (item == null) {
			return -1;
		}
		return item.getId();
	}

	public int getAmmoId() {
		Item item = items.get(SLOT_ARROWS);
		if (item == null) {
			return -1;
		}
		return item.getId();
	}

	public void deleteItem(int itemId, int amount) {
		Item[] itemsBefore = items.getItemsCopy();
		items.remove(new Item(itemId, amount));
		refreshItems(itemsBefore);
	}

	public void refreshItems(Item[] itemsBefore) {
		int[] changedSlots = new int[itemsBefore.length];
		int count = 0;
		for (int index = 0; index < itemsBefore.length; index++) {
			if (itemsBefore[index] != items.getItems()[index]) {
				changedSlots[count++] = index;
			}
		}
		int[] finalChangedSlots = new int[count];
		System.arraycopy(changedSlots, 0, finalChangedSlots, 0, count);
		refresh(finalChangedSlots);
	}

	public int getBootsId() {
		Item item = items.get(SLOT_FEET);
		if (item == null) {
			return -1;
		}
		return item.getId();
	}

	public int getGlovesId() {
		Item item = items.get(SLOT_HANDS);
		if (item == null) {
			return -1;
		}
		return item.getId();
	}

	public ItemsContainer<Item> getItems() {
		return items;
	}

	public int getEquipmentHpIncrease() {
		return equipmentHpIncrease;
	}

	public void setEquipmentHpIncrease(int hp) {
		equipmentHpIncrease = hp;
	}

	public boolean wearingArmour() {
		return getItem(SLOT_HAT) != null || getItem(SLOT_CAPE) != null || getItem(SLOT_AMULET) != null || getItem(SLOT_WEAPON) != null || getItem(SLOT_CHEST) != null || getItem(SLOT_SHIELD) != null || getItem(SLOT_LEGS) != null || getItem(SLOT_HANDS) != null || getItem(SLOT_FEET) != null;
	}

	public int getAmuletId() {
		Item item = items.get(SLOT_AMULET);
		if (item == null) {
			return -1;
		}
		return item.getId();
	}

	public boolean hasTwoHandedWeapon() {
		Item weapon = items.get(SLOT_WEAPON);
		return weapon != null && isTwoHandedWeapon(weapon);
	}

	public boolean wearingArmourNoWeapon() {
		return getItem(SLOT_HAT) != null || getItem(SLOT_CAPE) != null || getItem(SLOT_AMULET) != null || getItem(SLOT_CHEST) != null || getItem(SLOT_SHIELD) != null || getItem(SLOT_LEGS) != null || getItem(SLOT_HANDS) != null || getItem(SLOT_FEET) != null;
	}

	public boolean hasWeapon() {
		return getItem(SLOT_WEAPON) != null;
	}
	
	public boolean hasAura() {
		return getItem(SLOT_AURA) != null;
	}

	public ItemsContainer<Item> getCosmeticItems() {
		return cosmeticItems;
	}

	public boolean[] getHiddenSlots() {
		return hiddenSlots;
	}

	public boolean isCanDisplayCosmetic() {
		if (player.getControlerManager().getControler() != null && player.getControlerManager().getControler() instanceof DuelControler) {
			return false;
		}
		return !player.isCanPvp();
	}

	private int costumeColor;

	public int getCostumeColor() {
		return costumeColor;
	}

	public void setCostumeColor(int costumeColor) {
		this.costumeColor = costumeColor;
		player.getAppearence().generateAppearenceData();
	}
}
