package com.rs.game.player.actions;

import com.rs.game.Animation;
import com.rs.game.Graphics;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.content.Magic;

public class SuperHeating {

	/**
	 * XP modifier by wearing items.
	 *
	 * @param player
	 *            The player.
	 * @return the XP modifier.
	 */
	private static double blacksmithSuit(Player player) {
		double xpBoost = 1.0;
		if (player.getEquipment().getHatId() == 25195)
			xpBoost *= 1.01;
		if (player.getEquipment().getChestId() == 25196)
			xpBoost *= 1.01;
		if (player.getEquipment().getLegsId() == 25197)
			xpBoost *= 1.01;
		if (player.getEquipment().getBootsId() == 25198)
			xpBoost *= 1.01;
		if (player.getEquipment().getGlovesId() == 25199)
			xpBoost *= 1.01;
		if (player.getEquipment().getHatId() == 25195 && player.getEquipment().getChestId() == 25196 && player.getEquipment().getLegsId() == 25197 && player.getEquipment().getBootsId() == 25198 && player.getEquipment().getGlovesId() == 25199)
			xpBoost *= 1.01;
		return xpBoost;
	}

	public static void process(Player player, int itemId, Item item) {
		double xpBoost = 1.00 * blacksmithSuit(player);
		if (player.isLocked())
			return;
		player.getInterfaceManager().openGameTab(7);
		if (item.getId() != 436 && item.getId() != 438 && item.getId() != 440 && item.getId() != 442 && item.getId() != 444 && item.getId() != 447 && item.getId() != 451 && item.getId() != 453 && item.getId() != 449) {
			player.sendMessage("You cannot superheat this item.");
			return;
		}
		if (player.getActionManager().getActionDelay() != 0)
			return;
		if (itemId == 436) {
			if (player.getInventory().containsItem(438, 1)) {
				if (Magic.checkSpellRequirements(player, 43, true, 554, 4, 561, 1)) {
					player.getInventory().deleteItem(436, 1);
					player.getInventory().deleteItem(438, 1);
					player.getInventory().addItem(2349, 1);
					player.getSkills().addXp(Skills.SMITHING, 6.2 * xpBoost);
					smelt(player);
				}
			} else {
				player.sendMessage("You will also need at least 1 tin ore to superheat this.");
				return;
			}
		}
		if (itemId == 438) {
			if (player.getInventory().containsItem(436, 1)) {
				if (Magic.checkSpellRequirements(player, 43, true, 554, 4, 561, 1)) {
					player.getInventory().deleteItem(436, 1);
					player.getInventory().deleteItem(438, 1);
					player.getInventory().addItem(2349, 1);
					player.getSkills().addXp(Skills.SMITHING, 6.2 * xpBoost);
					smelt(player);
				}
			} else {
				player.sendMessage("You will also need at least 1 copper ore to superheat this.");
				return;
			}
		}
		if (itemId == 440) {
			if (player.getSkills().getLevel(Skills.SMITHING) < 15) {
				player.sendMessage("You need at least a level of 15 smithing to superheat this.");
				return;
			} else if (player.getSkills().getLevel(Skills.SMITHING) >= 15 && player.getSkills().getLevel(Skills.SMITHING) < 30) {
				if (Magic.checkSpellRequirements(player, 43, true, 554, 4, 561, 1)) {
					player.getInventory().deleteItem(440, 1);
					player.getInventory().addItem(2351, 1);
					player.getSkills().addXp(Skills.SMITHING, 12.5 * xpBoost);
					smelt(player);
				}
			} else if (player.getSkills().getLevel(Skills.SMITHING) >= 30) {
				if (player.getInventory().containsItem(453, 1) && !player.getInventory().containsItem(440, 1)) {
					player.sendMessage("You will need at least 1 iron ore and 2 coal ore to superheat this.");
					return;
				} else if (player.getInventory().containsItem(453, 2) && player.getInventory().containsItem(440, 1)) {
					if (Magic.checkSpellRequirements(player, 43, true, 554, 4, 561, 1)) {
						player.getInventory().deleteItem(440, 1);
						player.getInventory().deleteItem(453, 2);
						player.getInventory().addItem(2353, 1);
						player.getSkills().addXp(Skills.SMITHING, 17.5 * xpBoost);
						smelt(player);
					}
				} else {
					if (Magic.checkSpellRequirements(player, 43, true, 554, 4, 561, 1)) {
						player.getInventory().deleteItem(440, 1);
						player.getInventory().addItem(2351, 1);
						player.getSkills().addXp(Skills.SMITHING, 12.5 * xpBoost);
						smelt(player);
					}
				}
			}
		}
		if (itemId == 453) {
			if (!player.getInventory().containsItem(453, 2)) {
				player.sendMessage("You will need at least 2 pieces of coal ore to start superheating.");
				return;
			}
			if (player.getInventory().containsItem(440, 1)) {
				if (Magic.checkSpellRequirements(player, 43, true, 554, 4, 561, 1)) {
					player.getInventory().deleteItem(440, 1);
					player.getInventory().deleteItem(453, 2);
					player.getInventory().addItem(2353, 1);
					player.getSkills().addXp(Skills.SMITHING, 17.5 * xpBoost); // IRON
					smelt(player);
				}
			} else if (player.getInventory().containsItem(447, 1) && player.getInventory().containsItem(453, 4)) {
				if (player.getSkills().getLevel(Skills.SMITHING) < 50) {
					player.sendMessage("You need at least a level of 50 smithing to superheat this.");
					return;
				}
				if (Magic.checkSpellRequirements(player, 43, true, 554, 4, 561, 1)) {
					player.getInventory().deleteItem(447, 1);
					player.getInventory().deleteItem(453, 4);
					player.getInventory().addItem(2359, 1);
					player.getSkills().addXp(Skills.SMITHING, 30 * xpBoost); // MITHRIL
					smelt(player);
				}
			} else if (player.getInventory().containsItem(449, 1) && player.getInventory().containsItem(453, 6)) {
				if (player.getSkills().getLevel(Skills.SMITHING) < 70) {
					player.sendMessage("You need at least a level of 70 smithing to superheat this.");
					return;
				}
				if (Magic.checkSpellRequirements(player, 43, true, 554, 4, 561, 1)) {
					player.getInventory().deleteItem(449, 1);
					player.getInventory().deleteItem(453, 6);
					player.getInventory().addItem(2361, 1);
					player.getSkills().addXp(Skills.SMITHING, 37.5 * xpBoost); // ADAMANT
					smelt(player);
				}
			} else if (player.getInventory().containsItem(451, 1) && player.getInventory().containsItem(453, 8)) {
				if (player.getSkills().getLevel(Skills.SMITHING) < 85) {
					player.sendMessage("You need at least a level of 85 smithing to superheat this.");
					return;
				}
				if (Magic.checkSpellRequirements(player, 43, true, 554, 4, 561, 1)) {
					player.getInventory().deleteItem(451, 1);
					player.getInventory().deleteItem(453, 8);
					player.getInventory().addItem(2363, 1);
					player.getSkills().addXp(Skills.SMITHING, 50 * xpBoost); // RUNE
					smelt(player);
				}
			} else {
				player.sendMessage("Insufficient ore amount.");
				return;
			}
		}
		if (itemId == 442) {
			if (player.getSkills().getLevel(Skills.SMITHING) < 20) {
				player.sendMessage("You need at least a level of 20 smithing to superheat this.");
				return;
			}
			if (Magic.checkSpellRequirements(player, 43, true, 554, 4, 561, 1)) {
				player.getInventory().deleteItem(442, 1);
				player.getInventory().addItem(2355, 1);
				player.getSkills().addXp(Skills.SMITHING, 13.5 * xpBoost); // SILVER
				smelt(player);
			}
		}
		if (itemId == 444) {
			if (player.getSkills().getLevel(Skills.SMITHING) < 40) {
				player.sendMessage("You need at least a level of 40 smithing to superheat this.");
				return;
			}
			if (Magic.checkSpellRequirements(player, 43, true, 554, 4, 561, 1)) {
				player.getInventory().deleteItem(444, 1);
				player.getInventory().addItem(2357, 1);
				// player.addBarsSmelt();
				player.getSkills().addXp(Skills.SMITHING, 22.5 * xpBoost); // GOLD
				smelt(player);
			}
		}
		if (itemId == 447) {
			if (player.getSkills().getLevel(Skills.SMITHING) < 50) {
				player.sendMessage("You need at least a level of 50 smithing to superheat this.");
				return;
			}
			if (!player.getInventory().containsItem(453, 4)) {
				player.sendMessage("You need at least 4 coal ore to superheat this.");
				return;
			}
			if (Magic.checkSpellRequirements(player, 43, true, 554, 4, 561, 1)) {
				player.getInventory().deleteItem(447, 1);
				player.getInventory().deleteItem(453, 4);
				player.getInventory().addItem(2359, 1);
				player.getSkills().addXp(Skills.SMITHING, 30 * xpBoost); // MITHRIL
				smelt(player);
			}
		}
		if (itemId == 449) {
			if (player.getSkills().getLevel(Skills.SMITHING) < 70) {
				player.sendMessage("You need at least a level of 70 smithing to superheat this.");
				return;
			}
			if (!player.getInventory().containsItem(453, 6)) {
				player.sendMessage("You need at least 6 coal ore to superheat this.");
				return;
			}
			if (Magic.checkSpellRequirements(player, 43, true, 554, 4, 561, 1)) {
				player.getInventory().deleteItem(449, 1);
				player.getInventory().deleteItem(453, 6);
				player.getInventory().addItem(2361, 1);
				player.getSkills().addXp(Skills.SMITHING, 37.5 * xpBoost); // ADAMANT
				smelt(player);
			}
		}
		if (itemId == 451) {
			if (player.getSkills().getLevel(Skills.SMITHING) < 85) {
				player.sendMessage("You need at least a level of 85 smithing to superheat this.");
				return;
			}
			if (!player.getInventory().containsItem(453, 8)) {
				player.sendMessage("You need at least 8 coal ore to superheat this.");
				return;
			}
			if (Magic.checkSpellRequirements(player, 43, true, 554, 4, 561, 1)) {
				player.getInventory().deleteItem(451, 1);
				player.getInventory().deleteItem(453, 8);
				player.getInventory().addItem(2363, 1);
				player.getSkills().addXp(Skills.SMITHING, 50 * xpBoost); // RUNE
				smelt(player);
			}
		}
	}

	private static void smelt(Player player) {
		player.getActionManager().setActionDelay(3);
		player.setNextAnimation(new Animation(725));
		player.setNextGraphics(new Graphics(148, 0, 100));
		player.getSkills().addXp(Skills.MAGIC, 200);
		// player.addBarsSmelt();
	}
}