package com.rs.game.player.content.chests;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.World;
import com.rs.game.player.Player;
import com.rs.utils.Colors;
import com.rs.utils.Utils;

public class ActiveLootBox {

	/**
	 * @author Mr_Joopz
	 */

	private static final int COMMON[][] = {{990, 5}, {29542, 1}, {29541, 1}, {29540, 1}, {10176, 30}, {533, 100}, {1437, 250}, {5022, 1}, {1632, 20}, {1618, 35}, {1620, 50}
	, {1746, 50}, {2506, 50}, {10034, 35}, {441, 100}, {445, 100}, {448, 100}, {454, 200}, {322, 50}, {332, 50}, {360, 50}, {378, 50}, {7945, 50}
	, {1522, 50}, {1520, 50}, {6334, 50}, {1518, 50}, {8836, 50}
	, {200, 10}, {202, 10}, {204, 10}, {208, 10}, {210, 10}, {212, 10}, {214, 10}, {3050, 10}
	, {5313, 5}, {5314, 5}, {23717, 1}, {23721, 1}, {23725, 1}, {23729, 1}, {23733, 1}, {23737, 1}
	, {23741, 1}, {23745, 1}, {23749, 1}, {23753, 1}, {23757, 1}, {23761, 1}, {23765, 1}
	, {23769, 1}, {23774, 1}, {23778, 1}, {23782, 1}, {23786, 1}, {23790, 1}, {23794, 1}
	, {23798, 1}, {23802, 1}, {23806, 1}, {23810, 1}, {23814, 1}, {4587, 1}, {1305, 1}, {1215, 1}
	, {4087, 1}, {11230, 250}, {6694, 45}, {224, 45}
	, {29548, 200}};
	
	private static final int UNCOMMON[][] = {{537, 40}, {18831, 15}, {6816, 20}, {2510, 50}, {450, 50}, {452, 50}
	, {384, 70}, {396, 70}, {390, 70}, {15271, 70}, {1516, 75}, {1514, 75}, {3052, 10}, {216, 10}, {2486, 10}, {218, 10}, {220, 10}
	, {5315, 2}, {5316, 2}, {5022, 2}, {23714, 1}, {23718, 1}, {23722, 1}, {23726, 1}, {23730, 1}, {23734, 1}, {23738, 1}, {23742, 1}, {23746, 1}, {23750, 1}, {23754, 1}
	, {23758, 1}, {23762, 1}, {23766, 1}, {23770, 1}, {23775, 1}, {23779, 1}, {23783, 1}, {23787, 1}, {23791, 1}, {23795, 1}, {23799, 1}, {23803, 1}, {23807, 1}, {23811, 1}, {23815, 1}};
	
	private static final int VERY_RARE[][] = {{11724, 1}, {11722, 1}, {11726, 1}, {11728, 1}, {11720, 1}, {11718, 1}, {11716, 1}, {25028, 1}, {25031, 1}, {25034, 1}
	, {11726, 1}, {23715, 1}, {25202, 2}, {11720, 2}, {25022, 2}, {11718, 2}, {11728, 2}, {25025, 2}, {25016, 2}, {25010, 2}};

	private static int reward, r, c, q, vr;

	private static void handle(Player p) {
		if (!p.getInventory().hasFreeSlots()) {
			p.sendMessage("Please make some room in your inventory.");
			return;
		}
		r = Utils.random(10000);
		int roll = r;
		p.getInventory().deleteItem(28965, 1);
		if (r >= 4000) {
			c = Utils.random(COMMON[0][1]);
			r = Utils.random(COMMON.length);
			q = Utils.random(COMMON[r][1]);
			p.getInventory().addItem(COMMON[r][0], COMMON[r][0] == 995 ? c : q);
			reward = COMMON[r][0];
		} else if (r >= 500 && r < 4000) {
			r = Utils.random(UNCOMMON.length);
			q = Utils.random(UNCOMMON[r][1]);
			reward = UNCOMMON[r][0];
			p.getInventory().addItem(UNCOMMON[r][0], q);
		} else if (r == 0) {
			r = Utils.random(VERY_RARE.length);
			q = VERY_RARE[r][1];
			reward = VERY_RARE[r][0];
			p.getInventory().addItem(VERY_RARE[r][0], q);
			World.sendIconWorldMessage(Colors.orange+""+p.getDisplayName()+" has just found "+q+" x "+getNameForItem()+" from an Active Loot Box!", false);
		}
	}

	private static String getGrammar() {
		if (q == 1) {
			return sw("a") || sw("u") || sw("o") ? "an" : "a";
		}
		return q + "";
	}

	private static boolean sw(String n) {
		return getNameForItem().startsWith(n);
	}

	private static String getNameForItem() {
		switch (reward) {
		case 995:
			return COMMON[r][1] == 1 ? "coin" : "coins";
		}
		return ItemDefinitions.getItemDefinitions(reward).getName().toLowerCase();
	}

	public static boolean isBox(int itemId, Player p) {
			
		switch (itemId) {
		case 28965:
			handle(p);
			return true;
		}
		return false;
	}
}