package com.rs.game.player.actions.crafting;

import java.util.HashMap;
import java.util.Map;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.Animation;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.Action;
import com.rs.utils.Colors;
import com.rs.utils.Utils;

public class BattleStaveCreation extends Action {

	public static final Item STAVE = new Item(1391);
	public static final int ORBS[] = { 569,571,573,575};
	public static final int PRODUCTS[][] = { { 1393}, {1395}, {1397}, {1399}};
	public final Animation CRAFT_ANIMATION = new Animation(16446);
	private int quantity;
	private OrbData data;

	public BattleStaveCreation(OrbData data, int quantity) {
		this.data = data;
		this.quantity = quantity;
	}

	public static int getIndex(Player player) {
		int glass = (Integer) player.getTemporaryAttributtes().get("orbType");
		if (glass == ORBS[0])
			return 0;
		if (glass == ORBS[1])
			return 1;
		if (glass == ORBS[2])
			return 2;
		if (glass == ORBS[3])
			return 3;
		return -1;
	}

	public static boolean handleItemOnItem(Player player, Item itemUsed, Item usedWith) {
		for (int i = 0; i < ORBS.length; i++) {
			if (itemUsed.getId() == ORBS[i] || usedWith.getId() == ORBS[i]) {
				player.getTemporaryAttributtes().put("orbType", ORBS[i]);
				int index = getIndex(player);
				if (index == -1)
					return true;
					player.getDialogueManager().startDialogue("BattleStaffD", OrbData.forId(PRODUCTS[index][0]));
				return true;
			}
		}
		return false;
	}

	private boolean checkAll(Player player) {
		if (player.getInterfaceManager().containsScreenInter()
				|| player.getInterfaceManager().containsInventoryInter()) {
			player.sendMessage("Please finish what you're doing before doing this action.");
			return false;
		}
		if (data.getRequiredLevel() > player.getSkills().getLevel(Skills.CRAFTING)) {
			player.sendMessage("You need a crafting level of " + data.getRequiredLevel() + " to craft this item.");
			return false;
		}
		if (player.getInventory().getItems().getNumberOf(data.getGlassId()) < data.getGlassAmount()) {
			player.sendMessage("You don't have enough amount of orbs in your inventory.");
			return false;
		}
		if (!player.getInventory().getItems().containsOne(STAVE)) {
			player.sendMessage("You need a battle staff to do this!");
			return false;
		}
//		if (!player.getInventory().containsOneItem(PIPE.getId())) {
//			player.sendMessage("You need a glass blow pipe to do this.");
//			return false;
//		}
		if (!player.getInventory().containsOneItem(data.getGlassId())) {
			player.sendMessage("You've ran out of "
					+ ItemDefinitions.getItemDefinitions(data.getGlassId()).getName().toLowerCase() + ".");
			return false;
		}
		return true;
	}

	@Override
	public boolean process(Player player) {
		return checkAll(player);
	}

	@Override
	public int processWithDelay(Player player) {
		player.getInventory().deleteItem(data.getGlassId(), data.getGlassAmount());
		player.getInventory().addItem(data.getFinalProduct(), 1);
		player.getSkills().addXp(Skills.CRAFTING,
				data.getExperience() * 1);
		quantity--;
		player.getInventory().removeItems(STAVE);
		player.sendMessage("You attach the orb to the battle staff.");
		if (quantity <= 0)
			return -1;
		if (data.getGlassId() == 569)
		player.setNextAnimation(new Animation(16449));
		if (data.getGlassId() == 571)
			player.setNextAnimation(new Animation(16448));
		if (data.getGlassId() == 573)
			player.setNextAnimation(CRAFT_ANIMATION);
		if (data.getGlassId() == 575)
			player.setNextAnimation(new Animation(16447));
		return 3;
	}

	@Override
	public boolean start(Player player) {
		if (!checkAll(player))
			return false;
		setActionDelay(player, 1);
		if (data.getGlassId() == 569)
			player.setNextAnimation(new Animation(16449));
			if (data.getGlassId() == 571)
				player.setNextAnimation(new Animation(16448));
			if (data.getGlassId() == 573)
				player.setNextAnimation(CRAFT_ANIMATION);
			if (data.getGlassId() == 575)
				player.setNextAnimation(new Animation(16447));
		return true;
	}

	@Override
	public void stop(final Player player) {
		setActionDelay(player, 3);
	}

	public enum OrbData {

		WATER(571, 1, 1395, 54, 100),
		FIRE(569, 1, 1393, 62, 125),
		AIR(573, 1, 1397, 66, 137.5),
		EARTH(575, 1, 1399, 58, 112.5)
		;

		private static Map<Integer, OrbData> glassItems = new HashMap<Integer, OrbData>();

		static {
			for (OrbData glass : OrbData.values()) {
				glassItems.put(glass.finalProduct, glass);
			}
		}

		private int glassId, glassAmount, finalProduct, requiredLevel;
		private double experience;
		private String name;

		private OrbData(int glassId, int glassAmount, int finalProduct, int requiredLevel, double experience) {
			this.glassId = glassId;
			this.glassAmount = glassAmount;
			this.finalProduct = finalProduct;
			this.requiredLevel = requiredLevel;
			this.experience = experience;
			this.name = ItemDefinitions.getItemDefinitions(getFinalProduct()).getName().replace("", "");
		}

		public static OrbData forId(int id) {
			return glassItems.get(id);
		}

		public double getExperience() {
			return experience;
		}

		public int getFinalProduct() {
			return finalProduct;
		}

		public int getGlassAmount() {
			return glassAmount;
		}

		public int getGlassId() {
			return glassId;
		}

		public String getName() {
			return name;
		}

		public int getRequiredLevel() {
			return requiredLevel;
		}
	}

	
}