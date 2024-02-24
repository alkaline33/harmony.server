package com.rs.game.player.actions.crafting;

import com.rs.game.Animation;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.Action;
import com.rs.utils.Colors;
import com.rs.utils.Utils;

public class JewellerySmithing {

	public static final int INTERFACE = 675;

	private static final int[] MOLDS = { 1592, 1597, 1595, 11065 };

	private static final int[] GEMS = { 2357, 1607, 1605, 1603, 1601, 1615, 6573 };

	private static final int[][] ITEMS = { { 1635, 1637, 1639, 1641, 1643, 1645, 6575 },
			{ 1654, 1656, 1658, 1660, 1662, 1664, 11128 }, { 1673, 1675, 1677, 1679, 1681, 1683, 6585 },
			{ 11069, 11072, 11076, 11085, 11092, 11115, 11130 } };

	private static final int[] COMPONENTS_BASE = { 20, 39, 58, 77 };

	private static final double[][] EXPERIENCE = { { 15, 40, 55, 70, 85, 100, 115 }, { 20, 55, 60, 75, 90, 105, 120 },
			{ 25, 60, 65, 80, 95, 110, 125 }, { 30, 65, 70, 85, 100, 150, 165 } };

	private static final byte[][] LEVEL = { { 5, 20, 27, 34, 43, 55, 67 }, { 6, 22, 29, 40, 56, 72, 82 },
			{ 7, 23, 30, 42, 58, 74, 84 }, { 8, 24, 31, 50, 70, 80, 90 } };

	private static final int[] ONYX = { 6575, 11128, 6585, 11130 };
	private static final int[] EGG_IDS = { 3689, 3690, 3691, 3692, 3693, 3694 };

	public static void openInterface(Player player) {
		player.getInterfaceManager().sendInterface(INTERFACE);
		callCS2(player);
		for (int primaryIndex = 0; primaryIndex < MOLDS.length; primaryIndex++) {
			player.getPackets().sendIComponentText(INTERFACE, 16 + (primaryIndex * 19), "");
			for (int secondaryIndex = 0; secondaryIndex < ITEMS[primaryIndex].length; secondaryIndex++) {
				player.getPackets().sendItems((299 + (primaryIndex * 14) + secondaryIndex),
						new Item[] { new Item(ITEMS[primaryIndex][secondaryIndex]) });
			}
		}
	}

	private static void callCS2(Player player) {
		for (int primaryIndex = 0; primaryIndex < COMPONENTS_BASE.length; primaryIndex++) {
			for (int secondaryIndex = 0; secondaryIndex < ITEMS[primaryIndex].length; secondaryIndex++) {
				player.getPackets().sendInterSetItemsOptionsScript(INTERFACE,
						(COMPONENTS_BASE[primaryIndex] + secondaryIndex * 2),
						(299 + (primaryIndex * 14) + secondaryIndex), false, 6, 4, "Make - 1", "Make - 5", "Make - 10",
						"Make - All");
				player.getPackets().sendUnlockIComponentOptionSlots(INTERFACE,
						(COMPONENTS_BASE[primaryIndex] + secondaryIndex * 2), 0, 28, 0, 1, 2, 3, 4);
			}
		}
	}

	public static void handleButtonClick(Player player, int componentId, final int tick) {
		for (int primaryIndex = 0; primaryIndex < COMPONENTS_BASE.length; primaryIndex++) {
			for (int secondaryIndex = 0; secondaryIndex < ITEMS[primaryIndex].length; secondaryIndex++) {
				if (componentId == (COMPONENTS_BASE[primaryIndex] + secondaryIndex * 2)) {
					final int actionPrimaryIndex = primaryIndex, actionSecondaryIndex = secondaryIndex;
					player.closeInterfaces();
					player.getActionManager().setAction(new Action() {

						int ticks;

						@Override
						public boolean start(Player player) {
							this.ticks = tick;
							return process(player);
						}

						@Override
						public boolean process(Player player) {
							int level = LEVEL[actionPrimaryIndex][actionSecondaryIndex];
							if (player.getSkills().getLevel(Skills.CRAFTING) < level) {
								player.sendMessage("You need a Crafting level of " + level + ".");
								return false;
							} else if (!player.getInventory().containsItem(2357, 1)) {
								player.sendMessage("You need a gold bar in order to do this.");
								return false;
							} else if (actionSecondaryIndex != 0
									&& !player.getInventory().containsItem(GEMS[actionSecondaryIndex], 1)) {
								player.sendMessage("You are missing the required items in order to do this.");
								return false;
							}
							return true;
						}

						@Override
						public int processWithDelay(Player player) {
							player.setNextAnimation(new Animation(3243));
							player.getInventory().deleteItem(2357, 1);
							player.getInventory().deleteItem(GEMS[actionSecondaryIndex], 1);
							player.getInventory().addItem(actionSecondaryIndex == 6 ? ONYX[actionPrimaryIndex]
									: ITEMS[actionPrimaryIndex][actionSecondaryIndex], 1);
							player.getSkills().addXp(Skills.CRAFTING,
									EXPERIENCE[actionPrimaryIndex][actionSecondaryIndex]);
							ticks--;
							if (ticks <= 0)
								return -1;
							return 2;
						}

						@Override
						public void stop(Player player) {
							setActionDelay(player, 3);
						}
					});
				}
			}
		}
	}

	public static void ringTransformation(Player player, final int itemId) {
		if (player.getActionManager().getAction() != null) {
			player.sendMessage("Please finish what you are doing before transforming.");
			return;
		}
		player.getActionManager().setAction(new Action() {

			@Override
			public boolean start(Player player) {
				player.stopAll(true);
				player.getActionManager().forceStop();
				player.lock(3);
				int transformationId = EGG_IDS[Utils.random(EGG_IDS.length)];
				if (itemId == 6583)
					transformationId = 2626;
//				if (itemId == 41906)
//					transformationId = 2626;
				player.getAppearence().transformIntoNPC(transformationId);
				player.getInterfaceManager().sendInventoryInterface(375);
				return true;
			}

			@Override
			public boolean process(Player player) {
				return true;
			}

			@Override
			public int processWithDelay(Player player) {
				return 0;
			}

			@Override
			public void stop(Player player) {
				setActionDelay(player, 3);
				resetTransformation(player);
			}
		});
	}

	public static void resetTransformation(Player player) {
		player.lock(3);
		player.getInterfaceManager().closeInventoryInterface();
		player.getInventory().init();
		player.setNextAnimation(new Animation(14884));
		player.getAppearence().transformIntoNPC(-1);
	}
}
