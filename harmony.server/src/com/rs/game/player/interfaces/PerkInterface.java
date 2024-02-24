package com.rs.game.player.interfaces;

import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.utils.Colors;
public class PerkInterface {

	private static final int containerKey = 264;
	static final int[] HIDECOMPONENTS = {81,85,89,93,97,101,105,109,113,117,121,125,129,133,137,141,145,149,153,157,161,165,169,173,177,
	181,185,189,193,197,201,205,209,213,217,221,225,229,233,237,241,245,249,253,257,261};
	private static final int INTER = 3071;

	public static void OpenInterface(Player player) {
		int tokenAmount = player.getInventory().getNumberOf(29370);
		player.getInterfaceManager().sendInterface(INTER);
		player.perkcomponent = 0;
		player.getPackets().sendIComponentText(INTER, 16, "Perk Management");
		player.getPackets().sendIComponentText(INTER, 32, "Tokens:");
		player.getPackets().sendIComponentText(INTER, 33, "" + tokenAmount);
		player.getPackets().sendIComponentText(INTER, 46, "Select a perk for information!");
		player.getPackets().sendIComponentText(INTER, 61, "");
		player.getPackets().sendIComponentText(INTER, 51, "0");
		player.getPackets().sendIComponentText(INTER, 56, "Tokens can be purchased at ::store");
		handlePerkColor(player, 68);
		handlePerkColor(player, 72);
		handlePerkColor(player, 76);
		handlePerkColor(player, 80);
		//player.getPackets().sendIComponentText(INTER, 68, player.alwaysAdzeActive ? Colors.green+"Always Adze" : !player.alwaysAdzeActive ? Colors.red+"Always Adze" : "Always Adze");
		//player.getPackets().sendIComponentText(INTER, 72, "Healing Blade");
		for (int hidecomponent : HIDECOMPONENTS) {
			player.getPackets().sendHideIComponent(INTER, hidecomponent, true);
		}
		player.getPackets().sendItems(containerKey, player.getPerk().getContainer());
	}

	public static void HandleButtons(Player player, int componentId, int slotId) {
		if (player.getUsername().equalsIgnoreCase("Jack")) {
			player.sm("componentId: " + componentId);
		}
		Item item = player.getPerk().getContainer().get(slotId);
		switch (componentId) {
			case 21:
			case 25:
				player.sendMessage("Coming soon.");
				break;
			case 52:
				handlePurchase(player);
				break;
			case 65:
				player.perkcomponent = 2;
				player.getPackets().sendIComponentText(INTER, 46, "Always Adze");
				player.getPackets().sendIComponentText(INTER, 61, "Has a 100% chance to burn a log instantly when chopped, granting both Woodcutting and Firemaking experience!");

				player.getPackets().sendIComponentText(INTER, 51, "15,000");
				player.getPerk().getContainer().shift();
				player.getPackets().sendItems(containerKey, player.getPerk().getContainer());
				if (player.alwaysAdze && player.alwaysAdzeActive) {
					player.getPackets().sendIComponentText(INTER, 55, "Deactivate");
				} else if (player.alwaysAdze) {
					player.getPackets().sendIComponentText(INTER, 55, "Activate");
				} else {
					player.getPackets().sendIComponentText(INTER, 55, "Buy");
				}
				break;
			case 69:
				player.perkcomponent = 3;
				player.getPackets().sendIComponentText(INTER, 46, "Healing Blade");
				player.getPackets().sendIComponentText(INTER, 61, "Provides a 25% chance of activating upon a successful hit. When activated, heals hitpoints and recovers prayer points equal to 1/4 of your damage dealt.");

				player.getPackets().sendIComponentText(INTER, 51, "20,000");
				player.getPerk().getContainer().shift();
				player.getPackets().sendItems(containerKey, player.getPerk().getContainer());
				if (player.healingBlade && player.healingBladeActive) {
					player.getPackets().sendIComponentText(INTER, 55, "Deactivate");
				} else if (player.healingBlade) {
					player.getPackets().sendIComponentText(INTER, 55, "Activate");
				} else {
					player.getPackets().sendIComponentText(INTER, 55, "Buy");
				}
				break;
			case 73:
				player.perkcomponent = 4;
				player.getPackets().sendIComponentText(INTER, 46, "Twofold");
				player.getPackets().sendIComponentText(INTER, 61, "Provides a 33% chance of activating upon a successful hit. When activated, will hit your target 1 additional time.");

				player.getPackets().sendIComponentText(INTER, 51, "40,000");
				player.getPerk().getContainer().shift();
				player.getPackets().sendItems(containerKey, player.getPerk().getContainer());
				if (player.twofoldPerk && player.twofoldPerkActive) {
					player.getPackets().sendIComponentText(INTER, 55, "Deactivate");
				} else if (player.twofoldPerk) {
					player.getPackets().sendIComponentText(INTER, 55, "Activate");
				} else {
					player.getPackets().sendIComponentText(INTER, 55, "Buy");
				}
				break;
			case 77:
				player.perkcomponent = 5;
				player.getPackets().sendIComponentText(INTER, 46, "Straight to the Bank");
				player.getPackets().sendIComponentText(INTER, 61, "When active, this perk will send all drops from monsters straight to your bank.");

				player.getPackets().sendIComponentText(INTER, 51, "100,000");
				player.getPerk().getContainer().shift();
				player.getPackets().sendItems(containerKey, player.getPerk().getContainer());
				if (player.dropsBank && player.dropsBankActive) {
					player.getPackets().sendIComponentText(INTER, 55, "Deactivate");
				} else if (player.dropsBank) {
					player.getPackets().sendIComponentText(INTER, 55, "Activate");
				} else {
					player.getPackets().sendIComponentText(INTER, 55, "Buy");
				}
				break;
		}
		
	}
	
	public static void handlePurchase(Player player) {
		int tokenAmount = player.getInventory().getNumberOf(29370);
		switch (player.perkcomponent) {
			case 0:
				player.sendMessage("You must select a perk before attempting to purchase.");
				break;
			case 2:
				if (!player.getInventory().containsItem(29370, 15000) && !player.alwaysAdze) {
					player.sendMessage(Colors.red + "You do not have enough tokens to purchase this perk.");
					return;
				}
				if (!player.alwaysAdze) {
					player.getInventory().deleteItem(29370, 20000);
					player.alwaysAdze = true;
					player.alwaysAdzeActive = true;
					player.getPackets().sendIComponentText(INTER, 33, "" + tokenAmount);
					player.getPackets().sendIComponentText(INTER, 55, "Deactivate");
					player.sendMessage(Colors.green + "You have unlocked the perk: Always Adze.");
				} else if (player.alwaysAdzeActive) {
					player.alwaysAdzeActive = false;
					player.getPackets().sendIComponentText(INTER, 55, "Activate");
					player.sendMessage(Colors.red + "You have deactivated the perk: Always Adze.");
				} else {
					player.alwaysAdzeActive = true;
					player.getPackets().sendIComponentText(INTER, 55, "Deactivate");
					player.sendMessage(Colors.green + "You have activated the perk: Always Adze.");
				}
				handlePerkColor(player, 68);
				break;
			case 3:
				if (!player.getInventory().containsItem(29370, 20000)  && !player.healingBlade) {
					player.sendMessage(Colors.red + "You do not have enough tokens to purchase this perk.");
					return;
				}
				if (!player.healingBlade) {
					player.getInventory().deleteItem(29370, 20000);
					player.healingBlade = true;
					player.healingBladeActive = true;
					player.getPackets().sendIComponentText(INTER, 33, "" + tokenAmount);
					player.getPackets().sendIComponentText(INTER, 55, "Deactivate");
					player.sendMessage(Colors.green + "You have unlocked the perk: Healing Blade.");
				} else if (player.healingBladeActive) {
					player.healingBladeActive = false;
					player.getPackets().sendIComponentText(INTER, 55, "Activate");
					player.sendMessage(Colors.red + "You have deactivated the perk: Healing Blade.");
				} else {
					player.healingBladeActive = true;
					player.getPackets().sendIComponentText(INTER, 55, "Deactivate");
					player.sendMessage(Colors.green + "You have activated the perk: Healing Blade.");
				}
				handlePerkColor(player, 72);
				break;
			case 4:
				if (!player.getInventory().containsItem(29370, 40000) && !player.twofoldPerk) {
					player.sendMessage(Colors.red + "You do not have enough tokens to purchase this perk.");
					return;
				}
				if (!player.twofoldPerk) {
					player.getInventory().deleteItem(29370, 40000);
					player.twofoldPerk = true;
					player.twofoldPerkActive = true;
					player.getPackets().sendIComponentText(INTER, 33, "" + tokenAmount);
					player.getPackets().sendIComponentText(INTER, 55, "Deactivate");
					player.sendMessage(Colors.green + "You have unlocked the perk: Twofold.");
				} else if (player.twofoldPerkActive) {
					player.twofoldPerkActive = false;
					player.getPackets().sendIComponentText(INTER, 55, "Activate");
					player.sendMessage(Colors.red + "You have deactivated the perk: Twofold.");
				} else {
					player.twofoldPerkActive = true;
					player.getPackets().sendIComponentText(INTER, 55, "Deactivate");
					player.sendMessage(Colors.green + "You have activated the perk: Twofold.");
				}
				handlePerkColor(player, 76);
				break;
			case 5:
				if (!player.getInventory().containsItem(29370, 100000) && !player.dropsBank) {
					player.sendMessage(Colors.red + "You do not have enough tokens to purchase this perk.");
					return;
				}
				if (!player.dropsBank) {
					player.getInventory().deleteItem(29370, 100000);
					player.dropsBank = true;
					player.dropsBankActive = true;
					player.getPackets().sendIComponentText(INTER, 33, "" + tokenAmount);
					player.getPackets().sendIComponentText(INTER, 55, "Deactivate");
					player.sendMessage(Colors.green + "You have unlocked the perk: Straight to the Bank.");
				} else if (player.dropsBankActive) {
					player.dropsBankActive = false;
					player.getPackets().sendIComponentText(INTER, 55, "Activate");
					player.sendMessage(Colors.red + "You have deactivated the perk: Straight to the Bank.");
				} else {
					player.dropsBankActive = true;
					player.getPackets().sendIComponentText(INTER, 55, "Deactivate");
					player.sendMessage(Colors.green + "You have activated the perk: Straight to the Bank.");
				}
				handlePerkColor(player, 80);
				break;
			}
		}
		private static void handlePerkColor(Player player, int componentId) {
			switch (componentId) {
				case 68:
					player.getPackets().sendIComponentText(INTER, 68, !player.alwaysAdze ? "Always Adze" : player.alwaysAdzeActive ? Colors.green+"Always Adze" : Colors.red+"Always Adze");
					break;
				case 72:
					player.getPackets().sendIComponentText(INTER, 72, !player.healingBlade ? "Healing Blade" : player.healingBladeActive ? Colors.green+"Healing Blade" : Colors.red+"Healing Blade");
					break;
				case 76:
					player.getPackets().sendIComponentText(INTER, 76, !player.twofoldPerk ? "Twofold" : player.twofoldPerkActive ? Colors.green+"Twofold" : Colors.red+"Twofold");
					break;
				case 80:
					player.getPackets().sendIComponentText(INTER, 80, !player.dropsBank ? "Straight to the Bank" : player.dropsBankActive ? Colors.green+"Straight to the Bank" : Colors.red+"Straight to the Bank");
					break;
			}
		}
	}