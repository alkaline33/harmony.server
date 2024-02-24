package com.rs.game.player.content;

import java.util.HashMap;
import java.util.Map;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.player.Player;
import com.rs.utils.Utils;

/**
 * @author Hydrix
 */

public class ItemDyeRemover {
	
	public final static int BLOOD = 29168;
	public final static int ACID = 29805;
	public final static int CLOTH = 3188;
	
	public enum Dyes {
		
		/**
		 * ORIGINAL, ACID, BLOOD
		 */
		
		DRYGORE_MACE(29972, 29809, 29152),
		DRYGORE_OFF_MACE(29969, 29808, 29151),
		DRYGORE_RAPIER(29971, 29811, 29154),
		DRYGORE_OFF_RAPIER(29968, 29810, 29153),
		DRYGORE_LONGSWORD(29973, 29813, 29156),
		DRYGORE_OFF_LONGSWORD(29970, 29812, 29155),
		ASCENSION_CROSSBOW(29967, 29806, 29149),
		ASCENSION_OFF_CROSSBOW(29918, 29807, 29150),
		SEIMIC_WAND(29944, 29815, 29158),
		SEISMIC_SINGULARITY(29943, 29814, 29157),
		
		TORVA_FULLHELM(20135, 29824, 29166),
		TORVA_PLATEBODY(20139, 29825, 29167),
		TORVA_PLATELEGS(20143, 29823, 29165),
		PERNIX_COWL(20147, 29821, 29163),
		PERNIX_BODY(20151, 29822, 29164),
		PERNIX_LEGS(20155, 29820, 29162),
		VIRTUS_MASK(20159, 29818, 29160),
		VIRTUS_BODY(20163, 29819, 29161),
		VIRTUS_LEGS(20167, 29817, 29159),
		
		MALEVOLENT_HELM(29940, 28951, 28957),
		MALEVOLENT_BODY(29941, 28952, 28958),
		MALEVOLENT_LEGS(29939, 28950, 28956),	
		TECTONIC_HELM(29846, 28949, 28955),
		TECTONIC_BODY(29844, 28947, 28953),
		TECTONIC_LEGS(29845, 28948, 28954),		
		SIRENIC_HELM(29890, 28946, 29297),
		SIRENIC_BODY(29889, 28945, 29296),
		SIRENIC_LEGS(29888, 28944, 29295)
		
		;
		
		

		public static Map<Integer, Dyes> dyes = new HashMap<Integer, Dyes>();

		public static Dyes forId(int id) {
			
			return dyes.get(id);
		}

		static {
			for (Dyes dye : Dyes.values()) {
				dyes.put(dye.acidId, dye);
				dyes.put(dye.bloodId, dye);
			}
		}

		private int originalId;
		private int acidId;
		private int bloodId;
		
		Dyes(int originalId, int acidId, int bloodId) {
			this.originalId = originalId;
			this.acidId = acidId;
			this.bloodId = bloodId;
		}

		public int getOriginalId() {
			return originalId;
		}
		
		public int getBloodId() {
			return bloodId;
		}
		
		public int getAcidId() {
			return acidId;
		}
	}
	
	public static boolean dyeItem(Player player, int itemUsed, int usedWith) {
		int dyeId = -1, originalId = -1;
		int dyeIdRecover = 0;
		if (usedWith == CLOTH && Dyes.forId(itemUsed) != null) {
			dyeId = usedWith;
			originalId = itemUsed;
		} else if (itemUsed == CLOTH && Dyes.forId(usedWith) != null) {
			dyeId = itemUsed;
			originalId = usedWith;
		}
		if (dyeId == -1 || originalId == -1) {
			return false;
		}
		Dyes dye = Dyes.forId(originalId);
		if (dyeId == ACID && dye.getAcidId() == -1) {
			return false;
		} else if (dyeId == BLOOD && dye.getBloodId() == -1) {
			return false;
		}
		String itemName = ItemDefinitions.getItemDefinitions(originalId).getName();
		if (itemName.contains("(acid)")) {
			dyeIdRecover = ACID;
		}
		if (itemName.contains("(blood)")) {
			dyeIdRecover = BLOOD;
		}
		int r;
		r = Utils.random(10);
		if (player.dyeRecover > 0) {
			player.dyeRecover -= 1;
			r = 1;
			player.sendMessage("<col=00ff00>Your dye buff has helped recover your dye!");
		}
	if (r < 5) {
		player.sendMessage("<col=00ff00>Your dye was recovered!");
		player.getInventory().addItem(dyeIdRecover, 1);
	} else {
		player.sendMessage("<col=ff0000>Your dye was lost!");
	}
	player.sendMessage("Your item has been returned back to it's original state.");
	player.getInventory().deleteItem(dyeId, 1);
	player.getInventory().deleteItem(originalId, 1);
	if (dyeId == CLOTH) {
		player.getInventory().addItem(dye.getOriginalId(), 1);
	}
		return true;
	}

}
