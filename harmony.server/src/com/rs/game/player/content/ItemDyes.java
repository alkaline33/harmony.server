package com.rs.game.player.content;

import java.util.HashMap;
import java.util.Map;

import com.rs.Settings;
import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.player.Player;
import com.rs.utils.Colors;

/**
 * @author Hydrix
 */

public class ItemDyes {
	
	public final static int BLOOD = 29168;
	public final static int ACID = 29805;
	
	public enum Dyes {
		
		/**
		 * ORIGINAL, ACID, BLOOD.
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
		MALEVOLENT_HELM(29940, 28951, 28957),
		MALEVOLENT_BODY(29941, 28952, 28958),
		MALEVOLENT_LEGS(29939, 28950, 28956),	
		TECTONIC_HELM(29846, 28949, 28955),
		TECTONIC_BODY(29844, 28947, 28953),
		TECTONIC_LEGS(29845, 28948, 28954),		
		SIRENIC_HELM(29890, 28946, 29297),
		SIRENIC_BODY(29889, 28945, 29296),
		SIRENIC_LEGS(29888, 28944, 29295),
		
		TORVA_FULLHELM(20135, 29824, 29166),
		TORVA_PLATEBODY(20139, 29825, 29167),
		TORVA_PLATELEGS(20143, 29823, 29165),
		PERNIX_COWL(20147, 29821, 29163),
		PERNIX_BODY(20151, 29822, 29164),
		PERNIX_LEGS(20155, 29820, 29162),
		VIRTUS_MASK(20159, 29818, 29160),
		VIRTUS_BODY(20163, 29819, 29161),
		VIRTUS_LEGS(20167, 29817, 29159);

		public static Map<Integer, Dyes> dyes = new HashMap<Integer, Dyes>();

		public static Dyes forId(int id) {
			
			return dyes.get(id);
		}

		static {
			for (Dyes dye : Dyes.values()) {
				dyes.put(dye.originalId, dye);
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
		if ((usedWith == BLOOD || usedWith == ACID) && Dyes.forId(itemUsed) != null) {
			dyeId = usedWith;
			originalId = itemUsed;
		} else if ((itemUsed == BLOOD || itemUsed == ACID) && Dyes.forId(usedWith) != null) {
			dyeId = itemUsed;
			originalId = usedWith;
		}
		if (dyeId == -1 || originalId == -1) {
			return false;
		}
		if (ItemDefinitions.getItemDefinitions(originalId).getName().startsWith("Sirenic") || ItemDefinitions.getItemDefinitions(originalId).getName().startsWith("Malevolent")
				 || ItemDefinitions.getItemDefinitions(originalId).getName().startsWith("Tectonic")) {
			if (!player.getInventory().containsItem(995, 500000000)) {
				player.sendMessage(Colors.red+"To dye Malevolent, Sirenic or Tectonic you need 500M coins in your inventory!");
				return false;
			}
			player.getInventory().deleteItem(995, 500000000);
			Settings.GpSyncAmount += 500000000;
			player.dyesusedt90 ++;
		}
		Dyes dye = Dyes.forId(originalId);
		if (dyeId == ACID && dye.getAcidId() == -1) {
			return false;
		} else if (dyeId == BLOOD && dye.getBloodId() == -1) {
			return false;
		}
		player.getInventory().deleteItem(dyeId, 1);
		player.getInventory().deleteItem(originalId, 1);
		if (dyeId == ACID) {
			player.getInventory().addItem(dye.getAcidId(), 1);
		} else if (dyeId == BLOOD) {
			player.getInventory().addItem(dye.getBloodId(), 1);
		}
		return true;
	}

}
