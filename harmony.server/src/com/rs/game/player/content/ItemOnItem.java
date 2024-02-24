package com.rs.game.player.content;

import java.util.HashMap;
import java.util.Map;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.player.Player;
import com.rs.utils.Colors;

public class ItemOnItem {
	
	public enum Items {
		/**
		 * KIT, ORIGINAL ITEM, RESULT
		 */
		DRAGON_HELM_SP(19354, 11335, 19341),
		DRAGON_PLATELEGS_SP(19356, 4087, 19343),
		DRAGON_SQ_SP(19360, 1187, 19345),
		DRAGON_PLATEBODY_SP(19358, 14479, 19342),
		DRAGON_HELM(19346, 11335, 19336),
		DRAGON_PLATELEGS(19348, 4087 ,19338),
		DRAGON_SQ(19352, 1187, 19340),
		DRAGON_PLATEBODY(19358, 14479, 19337),
		DRAGON_CLAWS(29800, 14484, 29801),
		DRAGON_SCIMITAR_OR(29040, 4587, 29039),
		DRAGON_BOOTS_G(29038, 11732, 29037),
		ARMADYL_GODSWORD_OR(29036, 11694, 29035),
		BANDOS_GODSWORD_OR(29034, 11696, 29031),
		ZAMORAK_GODSWORD_OR(29033, 11700, 29030),
		SARADOMIN_GODSWORD_OR(29032, 11698, 29029),
		RUNE_DEFENDER_T(29024, 8850, 29025),
		FURY_KIT(19333, 6585, 19335);
		
	
	public static Map<Integer, Items> items = new HashMap<Integer, Items>();

	public static Items forId(int id) {
		
		return items.get(id);
	}

	static {
		for (Items item : Items.values()) {
			items.put(item.originalId, item);
		}
	}

	private int originalId;
	private int secondid;
	private int resultid;
	
	Items(int originalId, int secondid, int resultid) {
		this.originalId = originalId;
		this.secondid = secondid;
		this.resultid = resultid;
	}

	public int getOriginalId() {
		return originalId;
	}
	
	public int getSecondId() {
		return secondid;
	}
	
	public int getResultId() {
		return resultid;
	}
}
	
	public static boolean ItemonItem(Player player, int itemUsed, int usedWith) {
		int originalId = -1, secondId = -1;
		if (Items.forId(itemUsed) != null) {
			originalId = itemUsed;
			secondId = usedWith;
		}
		if (!player.getInventory().contains(itemUsed) || !player.getInventory().contains(usedWith)) {
			player.sendMessage(Colors.red+"Woops! Looks like you don't have all the required items to do this!");
			return false;
		}
		if (originalId == -1 || secondId == -1) {
			return false;
		}
		Items item = Items.forId(originalId);
		if (item.getSecondId() != secondId) {
			return false;
		}
		if (item.getOriginalId() == -1) {
			return false;
		}
		player.getInventory().deleteItem(originalId, 1);
		player.getInventory().deleteItem(secondId, 1);
		player.getInventory().addItem(item.getResultId(), 1);
		player.sendMessage(Colors.cyan+"You combine the "+ItemDefinitions.getItemDefinitions(originalId).getName()+" with the "+ItemDefinitions.getItemDefinitions(secondId).getName()+" to make a "+ItemDefinitions.getItemDefinitions(item.getResultId()).getName()+"!");
		return true;
	}

}
