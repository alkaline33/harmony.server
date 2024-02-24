package com.rs.game.player.actions;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.Animation;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.content.DailyChallenges;

public class GemCutting extends Action {

	/**
	 * Enum for gems
	 * 
	 * @author Raghav
	 * 
	 */
	public enum Gem {
		OPAL(1625, 1609, 15.0, 1, 886),

		JADE(1627, 1611, 20, 13, 886),

		RED_TOPAZ(1629, 1613, 25, 16, 887),

		SAPPHIRE(1623, 1607, 50, 20, 888),

		EMERALD(1621, 1605, 67, 27, 889),

		RUBY(1619, 1603, 85, 34, 887),

		DIAMOND(1617, 1601, 107.5, 43, 890),

		DRAGONSTONE(1631, 1615, 137.5, 55, 885),
		
		//FURY(6573, 6575, 167.5, 67, 2717),

		ONYX(6571, 6573, 167.5, 67, 2717),

		// cut to tip
		CUT_OPAL(1609, 45, 15.0, 1, 886),

		CUT_JADE(1611, 9187, 20, 13, 886),

		CUT_RED_TOPAZ(1613, 9188, 25, 16, 887),

		CUT_SAPPHIRE(1607, 9189, 50, 20, 888),

		CUT_EMERALD(1605, 9190, 67, 27, 889),

		CUT_RUBY(1603, 9191, 85, 34, 887),

		CUT_DIAMOND(1601, 9192, 107.5, 43, 890),

		CUT_DRAGONSTONE(1615, 9193, 137.5, 55, 885), CUT_ONYX(6573, 9194, 167.5, 67, 2717);

		private double experience;
		private int levelRequired;
		private int uncut, cut;

		private int emote;

		private Gem(int uncut, int cut, double experience, int levelRequired,
				int emote) {
			this.uncut = uncut;
			this.cut = cut;
			this.experience = experience;
			this.levelRequired = levelRequired;
			this.emote = emote;
		}

		public int getLevelRequired() {
			return levelRequired;
		}

		public double getExperience() {
			return experience;
		}

		public int getUncut() {
			return uncut;
		}

		public int getCut() {
			return cut;
		}

		public int getEmote() {
			return emote;
		}

	}

	public static void cut(Player player, Gem gem) {
		//System.out.println("4");
		if (player.getInventory().getItems()
				.getNumberOf(new Item(gem.getUncut(), 1)) <= 1) {
			// 1 lets start
			player.getActionManager().setAction(new GemCutting(gem, 1));
		} else {
			player.getDialogueManager().startDialogue("GemCuttingD", gem);
		}
	}

	private Gem gem;
	private int quantity;

	public GemCutting(Gem gem, int quantity) {
		this.gem = gem;
		this.quantity = quantity;
	}

	public boolean checkAll(Player player) {
		//System.out.println("4");
		if (player.getSkills().getLevel(Skills.CRAFTING) < gem
				.getLevelRequired()) {
			player.getDialogueManager().startDialogue(
					"SimpleMessage",
					"You need a crafting level of " + gem.getLevelRequired()
					+ " to cut that gem.");
			return false;
		}
		if (!player.getInventory().containsOneItem(gem.getUncut())) {
			player.getDialogueManager().startDialogue(
					"SimpleMessage",
					"You don't have any "
							+ ItemDefinitions
							.getItemDefinitions(gem.getUncut())
							.getName().toLowerCase() + " to cut.");
			return false;
		}
		//System.out.println("5");
		return true;
	}

	@Override
	public boolean start(Player player) {
		if (checkAll(player)) {
			//System.out.println("3");
			setActionDelay(player, 1);
			player.setNextAnimation(new Animation(gem.getEmote()));
			return true;
		}
		return false;
	}

	@Override
	public boolean process(Player player) {
		return checkAll(player);
	}

	@Override
	public int processWithDelay(Player player) {
		//System.out.println("4");
		player.getInventory().deleteItem(gem.getUncut(), 1);
		if (ItemDefinitions.getItemDefinitions(gem.getCut()).isStackable()) {
			player.getInventory().addItem(gem.getCut(), 12);
		} else {
			if (player.getEquipment().getCapeId() == 29993) {
				player.getInventory().addItem(gem.getCut() + 1, 1);
			} else if (player.getEquipment().getCapeId() == 20771 && player.getSkills().getXp(Skills.CRAFTING) >= 104273167) {
				player.getInventory().addItem(gem.getCut() + 1, 1);
			} else {
			player.getInventory().addItem(gem.getCut(), 1);
			}
		}
			
		player.getSkills().addXp(Skills.CRAFTING, gem.getExperience());
		player.getPackets().sendGameMessage(
				"You cut the "
						+ ItemDefinitions.getItemDefinitions(gem.getUncut())
						.getName().toLowerCase() + ".", true);
		if (player.challengeid == 22 && player.challengeamount > 0 && gem.getUncut() == 1619) {
			DailyChallenges.UpdateChallenge(player);
		}
		if (player.challengeid == 23 && player.challengeamount > 0 && gem.getUncut() == 1631) {
			DailyChallenges.UpdateChallenge(player);
		}
		if (player.challengeid == 24 && player.challengeamount > 0 && gem.getUncut() == 6571) {
			DailyChallenges.UpdateChallenge(player);
		}
		quantity--;
		if (quantity <= 0) {
			return -1;
		}
		if (gem ==  Gem.ONYX) {
			if (player.CraftingTaskOnyx > 0) {
				player.CraftingTaskOnyx -= 1;
			}
		}
		if (gem ==  Gem.DRAGONSTONE) {
			if (player.CraftingTaskDragonstone > 0) {
				player.CraftingTaskDragonstone -= 1;
			}
		}
		if (gem ==  Gem.DIAMOND) {
			if (player.CraftingTaskDiamond > 0) {
				player.CraftingTaskDiamond -= 1;
			}
		}
		player.setNextAnimation(new Animation(gem.getEmote())); // start the
		// emote and add
		// 2 delay
		return 0;
	}

	@Override
	public void stop(final Player player) {
		setActionDelay(player, 3);
	}
}
