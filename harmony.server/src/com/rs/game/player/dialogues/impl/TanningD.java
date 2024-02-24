package com.rs.game.player.dialogues.impl;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.Action;
import com.rs.game.player.content.SkillsDialogue;
import com.rs.game.player.content.SkillsDialogue.ItemNameFilter;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;

public class TanningD extends Dialogue {

	public static final int[][] TANNING_PRICES = {
			{ 0, 3, 15, 20, 20, 20, 20, 20, 20 },
			{ 2, 5, 25, 45, 45, 45, 45, 45, 45 } };
	public static final int[] INGREDIENT = { 1739, 1739, 6287, 1753, 1751,
			1749, 1747, 24372 };
	public static final int[] PRODUCT = { 1741, 1743, 6289, 1745, 2505, 2507,
			2509, 24374 };
	public static final int[] LEVELS = { 1, 28, 45, 45, 57, 66, 73, 79, 87 };
	private boolean isCanfis;
	private int npcId;

	@Override
	public void start() {
		npcId = (int) parameters[0];
		isCanfis = npcId == 1041;
		SkillsDialogue
				.sendSkillsDialogue(
						player,
						SkillsDialogue.MAKE,
						"Choose how many you wish to make, then<br>click on the chosen item to begin.",
						28, PRODUCT, new SkillsDialogue.ItemNameFilter() {
							@Override
							public String rename(String name) {
								int id = 0;
								for (int i = 0; i < 24375; i++) {
									if (ItemDefinitions.getItemDefinitions(i)
											.getName().equals(name)) {
										id = i;
										break;
									}
								}
								if (id == 0)
									return null;
								if (id == 1741)
									return "Soft leather<br>0 coins";
								else if (id == 1743)
									return "Hard leather<br>3 coins";
								else if (id == 6287)
									return "Snakeskin<br>15 coins";
								else if (id == 6289)
									return "Snakeskin<br>20 coins";
								else if (id == 1745)
									return "Green dragon leather<br>20 coins";
								else if (id == 2505)
									return "Blue dragon leather<br>20 coins";
								else if (id == 2507)
									return "Red dragon leather<br>20 coins";
								else if (id == 2509)
									return "Black dragon leather<br>20 coins";
								else if (id == 24374)
									return "Royal dragon leather<br>20 coins";
								return null;
							}
						});
	}

	@Override
	public void run(int interfaceId, final int componentId) {
		final int componentIndex = SkillsDialogue.getItemSlot(componentId);
		if (componentIndex > INGREDIENT.length) {
			end();
			return;
		}
		player.getActionManager().setAction(new Action() {

			int ticks,
					price = TANNING_PRICES[isCanfis ? 1 : 0][componentIndex];

			@Override
			public boolean start(final Player player) {
				int leatherAmount = player.getInventory().getNumberOf(
						INGREDIENT[componentIndex]);
				if (leatherAmount == 0) {
					end();
					WorldTasksManager.schedule(new WorldTask() {

						@Override
						public void run() {
							player.getDialogueManager()
									.startDialogue(
											"SimpleNPCMessage",
											npcId,
											"You must bring me at least one "
													+ ItemDefinitions
															.getItemDefinitions(
																	INGREDIENT[componentIndex])
															.getName()
															.toLowerCase()
													+ " in order to tan "
													+ ItemDefinitions
															.getItemDefinitions(
																	PRODUCT[componentIndex])
															.getName()
															.toLowerCase()
													+ ".");
						}
					});
					return false;
				}
				final int levelReq = LEVELS[componentIndex];
				if (player.getSkills().getLevel(Skills.CRAFTING) < levelReq) {
					end();
					WorldTasksManager.schedule(new WorldTask() {

						@Override
						public void run() {
							player.getDialogueManager()
									.startDialogue(
											"SimpleNPCMessage",
											npcId,
											"Young traveler! You aren't skilled enough to make this yet. Come back when you have a Crafting level of at least "
													+ levelReq + ".");
						}
					});
					return false;
				}
				int requestedAmount = SkillsDialogue.getQuantity(player);
				int maximumQuantity = price == 0 ? requestedAmount : player
						.getInventory().getNumerOf(995) / price;
				if (maximumQuantity > 0) {
					if (requestedAmount > maximumQuantity)
						requestedAmount = maximumQuantity;
					if (requestedAmount > leatherAmount)
						requestedAmount = leatherAmount;
					ticks = requestedAmount;
					return true;
				}
				return false;
			}

			@Override
			public boolean process(Player player) {
				if (player.getInventory().getNumerOf(995) < price) {
					end();
					player.getDialogueManager().startDialogue(
							"SimpleNPCMessage",
							npcId,
							"Oh no, it looks like you've ran out of coins. Come back later once you have "
									+ price + " coins.");
					// todo npc dialouge here
					return false;
				}
				return ticks > 0;
			}

			@Override
			public int processWithDelay(Player player) {
				ticks--;
				player.getInventory().deleteItem(
						new Item(INGREDIENT[componentIndex], 1));
				player.getInventory().addItem(
						new Item(PRODUCT[componentIndex], 1));
				if (price != 0) {
					player.getInventory().removeItemMoneyPouch(
							new Item(995, price));
					player.getPackets().sendRunScript(5561, 0, price);
				}
				return 0;
			}

			@Override
			public void stop(Player player) {
				setActionDelay(player, 3);
			}
		});
		end();
	}

	@Override
	public void finish() {
	}
}