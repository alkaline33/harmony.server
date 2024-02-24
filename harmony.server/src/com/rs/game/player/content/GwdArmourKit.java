package com.rs.game.player.content;

import java.util.HashMap;
import java.util.Map;

import com.rs.game.player.Player;

/**
 * @author Hydrix
 */

public class GwdArmourKit {
	
	public final static int KIT = 29055;
	
	public enum Armours {
		
		
		BANDOS_CHEST(11724, 29051),
		BANDOS_LEGS(11726, 29050),
		ARMADYL_HELM(11718, 29054),
		ARMADYL_CHEST(11720, 29053),
		ARMADYL_LEGS(11722, 29052),
		SUBJUGATION_HAT(24992, 29049),
		SUBJUGATION_BODY(24995, 29048),
		SUBJUGATION_LEGS(24998, 29047);

		public static Map<Integer, Armours> armours = new HashMap<Integer, Armours>();

		public static Armours forId(int id) {
			
			return armours.get(id);
		}

		static {
			for (Armours armour : Armours.values()) {
				armours.put(armour.originalId, armour);
			}
		}

		private int originalId;
		private int upgradedId;
		
		Armours(int originalId, int upgradedId) {
			this.originalId = originalId;
			this.upgradedId = upgradedId;
		}

		public int getOriginalId() {
			return originalId;
		}
		
		public int getupgradedId() {
			return upgradedId;
		}
	}
	
	public static boolean upgradeItem(Player player, int itemUsed, int usedWith) {
		int kitId = -1, originalId = -1;
		if (usedWith == KIT && Armours.forId(itemUsed) != null) {
			kitId = usedWith;
			originalId = itemUsed;
		} else if (itemUsed == KIT && Armours.forId(usedWith) != null) {
			kitId = itemUsed;
			originalId = usedWith;
		}
		if (kitId == -1 || originalId == -1) {
			return false;
		}
		Armours armour = Armours.forId(originalId);
		if (kitId == KIT && armour.getupgradedId() == -1) {
			return false;
		}
		player.getInventory().deleteItem(kitId, 1);
		player.getInventory().deleteItem(originalId, 1);
		if (kitId == KIT) {
			player.getInventory().addItem(armour.getupgradedId(), 1);
			player.sendMessage("You apply to upgrade kit to the armour.");
		}
		return true;
	}

}
