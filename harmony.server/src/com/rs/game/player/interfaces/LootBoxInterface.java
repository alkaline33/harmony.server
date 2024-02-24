package com.rs.game.player.interfaces;

import java.util.Objects;

import com.rs.Settings;
import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.utils.Colors;
import com.rs.utils.Utils;

public class LootBoxInterface {
	
	/**
	 * Author @Mr_Joopz
	 */
	
	private static int INTER = 3025;
	
	public static void SendInterface(Player player, int value) {
		values = value;
		/**
		 * Initial interface being opened.
		 */
		player.getInterfaceManager().sendInterface(INTER);
		player.getPackets().sendIComponentText(3025, 31, "You can either choose to claim 1 of the 3 items below for an additional <col=ff0000>"+value+"</col> donator tokens or you can choose Random Pick which will randomly claim 1 of the 3.");
	}
	
	static int values;
	
	public static void HandleButtons(Player p, int componentId) {
		/**
		 * Handles clicking on the interface.
		 */
		int freeSlots = p.getInventory().getFreeSlots();
		if (Settings.DEBUG) {
		System.out.println("Component ID: "+componentId);
		}
		switch (componentId) {
		case 24:
			/**
			 * Slot 1
			 */
			if (!p.getInventory().containsItem(29370, values) && values > 0) {
				p.sendMessage(Colors.red+"You do not have enough donator tokens on you to do this!");
				return;
			}
			for (Item i : p.getBoxI1().getContainer().getItemsCopy()) {
				if (Objects.isNull(i)) {
					continue;
				}
				if (!ItemDefinitions.getItemDefinitions(i.getId()).isStackable() && p.getInventory().getFreeSlots() < p.getBoxI1().getContainer().getNumberOf(i.getId())) {
					continue;
				}
				if (ItemDefinitions.getItemDefinitions(i.getId()).isStackable() && !p.getInventory().hasFreeSlots()) {
					continue;
				}

				if (p.getInventory().addItem(i)) {
					p.getBoxI1().getContainer().remove(i);
					p.sendMessage(Colors.green+"You have won... "+i.getAmount()+" x "+i.getName()+"!");
				} else {
					p.getBoxI1().getContainer().remove(new Item(i.getId(), freeSlots));
					p.sendMessage(Colors.green+"You have won... "+i.getAmount()+" x "+i.getName()+"!");
				}
			}
			if (values > 0) {
				p.getInventory().deleteItem(29370, values);
			}
			p.getInterfaceManager().closeInterfaceCustom();
			p.getBoxI1().getContainer().clear();
			p.getBoxI2().getContainer().clear();
			p.getBoxI3().getContainer().clear();
			p.unlock();
			break;
		case 26:
			/**
			 * Slot 2
			 */
			if (!p.getInventory().containsItem(29370, values) && values > 0) {
				p.sendMessage(Colors.red+"You do not have enough donator tokens on you to do this!");
				return;
			}
			for (Item i : p.getBoxI2().getContainer().getItemsCopy()) {
				if (Objects.isNull(i)) {
					continue;
				}
				if (!ItemDefinitions.getItemDefinitions(i.getId()).isStackable() && p.getInventory().getFreeSlots() < p.getBoxI2().getContainer().getNumberOf(i.getId())) {
					continue;
				}
				if (ItemDefinitions.getItemDefinitions(i.getId()).isStackable() && !p.getInventory().hasFreeSlots()) {
					continue;
				}

				if (p.getInventory().addItem(i)) {
					p.getBoxI2().getContainer().remove(i);
					p.sendMessage(Colors.green+"You have won... "+i.getAmount()+" x "+i.getName()+"!");
				} else {
					p.getBoxI2().getContainer().remove(new Item(i.getId(), freeSlots));
					p.sendMessage(Colors.green+"You have won... "+i.getAmount()+" x "+i.getName()+"!");
				}
			}
			if (values > 0) {
				p.getInventory().deleteItem(29370, values);
			}
			p.getInterfaceManager().closeInterfaceCustom();
			p.getBoxI1().getContainer().clear();
			p.getBoxI2().getContainer().clear();
			p.getBoxI3().getContainer().clear();
			p.unlock();
			break;
		case 28:
			/**
			 * Slot 3
			 */
			if (!p.getInventory().containsItem(29370, values) && values > 0) {
				p.sendMessage(Colors.red+"You do not have enough donator tokens on you to do this!");
				return;
			}
			for (Item i : p.getBoxI3().getContainer().getItemsCopy()) {
				if (Objects.isNull(i)) {
					continue;
				}
				if (!ItemDefinitions.getItemDefinitions(i.getId()).isStackable() && p.getInventory().getFreeSlots() < p.getBoxI3().getContainer().getNumberOf(i.getId())) {
					continue;
				}
				if (ItemDefinitions.getItemDefinitions(i.getId()).isStackable() && !p.getInventory().hasFreeSlots()) {
					continue;
				}

				if (p.getInventory().addItem(i)) {
					p.getBoxI3().getContainer().remove(i);
					p.sendMessage(Colors.green+"You have won... "+i.getAmount()+" x "+i.getName()+"!");
				} else {
					p.getBoxI3().getContainer().remove(new Item(i.getId(), freeSlots));
					p.sendMessage(Colors.green+"You have won... "+i.getAmount()+" x "+i.getName()+"!");
				}
			}
			if (values > 0) {
				p.getInventory().deleteItem(29370, values);
			}
			p.getInterfaceManager().closeInterfaceCustom();
			p.getBoxI1().getContainer().clear();
			p.getBoxI2().getContainer().clear();
			p.getBoxI3().getContainer().clear();
			p.unlock();
			break;
		case 30:
			/**
			 * Random Pick
			 */
			int random = Utils.random(3);
			switch (random) {
				case 1:
					for (Item i : p.getBoxI1().getContainer().getItemsCopy()) {
						if (Objects.isNull(i)) {
							continue;
						}
						if (!ItemDefinitions.getItemDefinitions(i.getId()).isStackable() && p.getInventory().getFreeSlots() < p.getBoxI1().getContainer().getNumberOf(i.getId())) {
							continue;
						}
						if (ItemDefinitions.getItemDefinitions(i.getId()).isStackable() && !p.getInventory().hasFreeSlots()) {
							continue;
						}

						if (p.getInventory().addItem(i)) {
							p.getBoxI1().getContainer().remove(i);
							p.sendMessage(Colors.green+"You have won... "+i.getAmount()+" x "+i.getName()+"!");
						} else {
							p.getBoxI1().getContainer().remove(new Item(i.getId(), freeSlots));
							p.sendMessage(Colors.green+"You have won... "+i.getAmount()+" x "+i.getName()+"!");
						}
					}
					p.getInterfaceManager().closeInterfaceCustom();
					p.getBoxI1().getContainer().clear();
					p.getBoxI2().getContainer().clear();
					p.getBoxI3().getContainer().clear();
					p.unlock();
					break;
				case 2:
					for (Item i : p.getBoxI2().getContainer().getItemsCopy()) {
						if (Objects.isNull(i)) {
							continue;
						}
						if (!ItemDefinitions.getItemDefinitions(i.getId()).isStackable() && p.getInventory().getFreeSlots() < p.getBoxI2().getContainer().getNumberOf(i.getId())) {
							continue;
						}
						if (ItemDefinitions.getItemDefinitions(i.getId()).isStackable() && !p.getInventory().hasFreeSlots()) {
							continue;
						}

						if (p.getInventory().addItem(i)) {
							p.getBoxI2().getContainer().remove(i);
							p.sendMessage(Colors.green+"You have won... "+i.getAmount()+" x "+i.getName()+"!");
						} else {
							p.getBoxI2().getContainer().remove(new Item(i.getId(), freeSlots));
							p.sendMessage(Colors.green+"You have won... "+i.getAmount()+" x "+i.getName()+"!");
						}
					}
					p.getInterfaceManager().closeInterfaceCustom();
					p.getBoxI1().getContainer().clear();
					p.getBoxI2().getContainer().clear();
					p.getBoxI3().getContainer().clear();
					p.unlock();
				break;
				default:
					for (Item i : p.getBoxI3().getContainer().getItemsCopy()) {
						if (Objects.isNull(i)) {
							continue;
						}
						if (!ItemDefinitions.getItemDefinitions(i.getId()).isStackable() && p.getInventory().getFreeSlots() < p.getBoxI3().getContainer().getNumberOf(i.getId())) {
							continue;
						}
						if (ItemDefinitions.getItemDefinitions(i.getId()).isStackable() && !p.getInventory().hasFreeSlots()) {
							continue;
						}

						if (p.getInventory().addItem(i)) {
							p.getBoxI3().getContainer().remove(i);
							p.sendMessage(Colors.green+"You have won... "+i.getAmount()+" x "+i.getName()+"!");
						} else {
							p.getBoxI3().getContainer().remove(new Item(i.getId(), freeSlots));
							p.sendMessage(Colors.green+"You have won... "+i.getAmount()+" x "+i.getName()+"!");
						}
					}
					p.getInterfaceManager().closeInterfaceCustom();
					p.getBoxI1().getContainer().clear();
					p.getBoxI2().getContainer().clear();
					p.getBoxI3().getContainer().clear();
					p.unlock();
					break;
			}
			break;
			default:
				break;
		}
	}
	
	

}
