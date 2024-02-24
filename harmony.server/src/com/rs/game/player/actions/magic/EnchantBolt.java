package com.rs.game.player.actions.magic;

import com.rs.game.Animation;
import com.rs.game.Graphics;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.Action;
import com.rs.game.player.content.Magic;


public class EnchantBolt extends Action {
	private static final int AIR_RUNE = 556, WATER_RUNE = 555, EARTH_RUNE = 557, FIRE_RUNE = 554, MIND_RUNE = 558,
			NATURE_RUNE = 561, DEATH_RUNE = 560, COSMIC_RUNE = 564, BLOOD_RUNE = 565, SOUL_RUNE = 566, LAW_RUNE = 563;
	private Bolt bolt;
	private int quantity;

	public EnchantBolt(Bolt bolt, int quantity) {
		this.bolt = bolt;
		this.quantity = quantity;
	}

	public static void enchant(Player player, Bolt bolt) {
		player.getActionManager().setAction(new EnchantBolt(bolt, 100));
	}

	public boolean checkAll(Player player) {
		if (!Magic.checkRunes(player, false, bolt.getRune1(), bolt.getAmt1(), bolt.getRune2(), bolt.getAmt2(),
				bolt.getRune3(), bolt.getAmt3())) {
			return false;
		}
		if (player.getInventory().getItems().getNumberOf(new Item(bolt.getBolts(), 1)) < 10) {
			player.getDialogueManager().startDialogue("SimpleMessage", "You need at least 10 bolts to do that.");
			return false;
		}
		if (player.getSkills().getLevel(Skills.MAGIC) < bolt.getLevelRequired()) {
			player.getDialogueManager().startDialogue("SimpleMessage",
					"You need a magic level of " + bolt.getLevelRequired() + " to enchant those bolts.");
			return false;
		}
		return true;
	}

	@Override
	public boolean process(Player player) {
		player.closeInterfaces();
		return checkAll(player);
	}

	@Override
	public int processWithDelay(Player player) {
		player.closeInterfaces();
		if (checkAll(player) && quantity > 0) {
			player.setNextAnimation(new Animation(4462));
			player.setNextGraphics(new Graphics(759));
			setActionDelay(player, 4);
			player.getInventory().deleteItem(bolt.getRune1(), bolt.getAmt1());
			player.getInventory().deleteItem(bolt.getRune2(), bolt.getAmt2());
			player.getInventory().deleteItem(bolt.getRune3(), bolt.getAmt3());
			player.getInventory().deleteItem(bolt.getBolts(), 10);
			player.getInventory().addItem(bolt.getEnchantedBolts(), 10);
			player.getSkills().addXp(Skills.MAGIC, bolt.getExp());
			quantity--;
		}
		return 0;
	}

	@Override
	public boolean start(Player player) {
		player.closeInterfaces();
		if (checkAll(player)) {
			return true;
		}
		return false;
	}

	@Override
	public void stop(final Player player) {
		setActionDelay(player, 4);
	}

	public enum Bolt {

		OPAL(879, 9236, 4, COSMIC_RUNE, 1, AIR_RUNE, 2, -1, -1, 9), SAPPHIRE(9337, 9240, 7, COSMIC_RUNE, 1, WATER_RUNE,
				1, MIND_RUNE, 1, 17), JADE(9335, 9237, 14, COSMIC_RUNE, 1, EARTH_RUNE, 2, -1, -1, 19), PEARL(880, 9238,
						24, COSMIC_RUNE, 1, WATER_RUNE, 2, -1, -1, 29), EMERALD(9338, 9241, 27, COSMIC_RUNE, 1,
								AIR_RUNE, 3, NATURE_RUNE, 1, 37), REDTOPAZ(9336, 9239, 29, COSMIC_RUNE, 1, FIRE_RUNE, 2,
										-1, -1, 33), RUBY(9339, 9242, 49, COSMIC_RUNE, 1, FIRE_RUNE, 5, BLOOD_RUNE, 1,
												59), DIAMOND(9340, 9243, 57, COSMIC_RUNE, 1, EARTH_RUNE, 10, LAW_RUNE,
														2, 67), DRAGONSTONE(9341, 9244, 68, COSMIC_RUNE, 1, EARTH_RUNE,
																15, SOUL_RUNE, 1, 78), ONYX(9342, 9245, 87, COSMIC_RUNE,
																		1, FIRE_RUNE, 20, DEATH_RUNE, 1, 97);

		private int bolts, enchantedBolts;
		private int levelRequired;
		private int rune1, rune2, rune3 = -1;
		private int amt1, amt2, amt3 = -1;
		private int experience;

		private Bolt(int bolts, int enchantedBolts, int levelRequired, int rune1, int amt1, int rune2, int amt2,
				int rune3, int amt3, int experience) {

			this.bolts = bolts;
			this.enchantedBolts = enchantedBolts;
			this.levelRequired = levelRequired;
			this.rune1 = rune1;
			this.rune2 = rune2;
			this.rune3 = rune3;
			this.amt1 = amt1;
			this.amt2 = amt2;
			this.amt3 = amt3;
			this.experience = experience;

		}

		public int getAmt1() {
			return amt1;
		}

		public int getAmt2() {
			return amt2;
		}

		public int getAmt3() {
			return amt3;
		}

		public int getBolts() {
			return bolts;
		}

		public int getEnchantedBolts() {
			return enchantedBolts;
		}

		public int getExp() {
			return experience;
		}

		public int getLevelRequired() {
			return levelRequired;
		}

		public int getRune1() {
			return rune1;
		}

		public int getRune2() {
			return rune2;
		}

		public int getRune3() {
			return rune3;
		}

	}
}