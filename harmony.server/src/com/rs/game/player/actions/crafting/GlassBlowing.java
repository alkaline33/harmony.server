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

public class GlassBlowing extends Action {

	public static final Item PIPE = new Item(1785);
	public static final Item GLASS = new Item(1775);
	public static final int MOLTENGLASS[] = { 1775};
	public static final int PRODUCTS[][] = { { 1919, 4527, 4522, 229, 6667,567,4542,10973, }};
	public final Animation CRAFT_ANIMATION = new Animation(884);
	private int quantity;
	private GlassblowingData data;

	public GlassBlowing(GlassblowingData data, int quantity) {
		this.data = data;
		this.quantity = quantity;
	}

	public static int getIndex(Player player) {
		int glass = (Integer) player.getTemporaryAttributtes().get("glassType");
		if (glass == MOLTENGLASS[0])
			return 0;
		if (glass == MOLTENGLASS[1])
			return 1;
		return -1;
	}

	public static boolean handleItemOnItem(Player player, Item itemUsed, Item usedWith) {
		for (int i = 0; i < MOLTENGLASS.length; i++) {
			if (itemUsed.getId() == MOLTENGLASS[i] || usedWith.getId() == MOLTENGLASS[i]) {
				player.getTemporaryAttributtes().put("glassType", MOLTENGLASS[i]);
				int index = getIndex(player);
				if (index == -1)
					return true;
					player.getDialogueManager().startDialogue("GlassblowingD", GlassblowingData.forId(PRODUCTS[index][0]),
							GlassblowingData.forId(PRODUCTS[index][1]), GlassblowingData.forId(PRODUCTS[index][2]),
							GlassblowingData.forId(PRODUCTS[index][3]), GlassblowingData.forId(PRODUCTS[index][4]),
							GlassblowingData.forId(PRODUCTS[index][5]), GlassblowingData.forId(PRODUCTS[index][6]),
							GlassblowingData.forId(PRODUCTS[index][7]));
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
			player.sendMessage("You don't have enough amount of molten glass in your inventory.");
			return false;
		}
		if (!player.getInventory().getItems().containsOne(GLASS)) {
			player.sendMessage("You need some molten glass to do this.");
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
		//player.getInventory().removeItems(GLASS);
		player.sendMessage("You shape the molten glass.");
		if (quantity <= 0)
			return -1;
		player.setNextAnimation(CRAFT_ANIMATION);
		return 3;
	}

	@Override
	public boolean start(Player player) {
		if (!checkAll(player))
			return false;
		setActionDelay(player, 1);
		player.setNextAnimation(CRAFT_ANIMATION);
		return true;
	}

	@Override
	public void stop(final Player player) {
		setActionDelay(player, 3);
	}

	public enum GlassblowingData {

		BEER_GLASS(1775, 1, 1919, 1, 17.5),
		CANDLE_LANTERN(1775, 1, 4527, 4, 19),
		OIL_LAMP(1775, 1, 4522, 12, 25),
		VIAL(1775, 1, 229, 33, 35),
		FISH_BOWL(1775, 1, 6667, 42, 42.5),
		UNPOWERED_ORB(1775, 1, 567, 46, 52.5),
		LANTERN_LENS(1775, 1, 4542, 49, 55),
		LIGHT_ORB(1775, 1, 10973, 87, 70),
		;

		private static Map<Integer, GlassblowingData> glassItems = new HashMap<Integer, GlassblowingData>();

		static {
			for (GlassblowingData glass : GlassblowingData.values()) {
				glassItems.put(glass.finalProduct, glass);
			}
		}

		private int glassId, glassAmount, finalProduct, requiredLevel;
		private double experience;
		private String name;

		private GlassblowingData(int glassId, int glassAmount, int finalProduct, int requiredLevel, double experience) {
			this.glassId = glassId;
			this.glassAmount = glassAmount;
			this.finalProduct = finalProduct;
			this.requiredLevel = requiredLevel;
			this.experience = experience;
			this.name = ItemDefinitions.getItemDefinitions(getFinalProduct()).getName().replace("", "");
		}

		public static GlassblowingData forId(int id) {
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