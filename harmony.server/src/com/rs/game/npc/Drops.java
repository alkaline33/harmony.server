package com.rs.game.npc;

import java.util.ArrayList;
import java.util.List;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.player.Player;
import com.rs.game.player.content.Combat;
import com.rs.utils.Utils;

public class Drops {

	public static final int ALWAYS = 0, COMMOM = 1, UNCOMMON = 2, RARE = 3, VERY_RARE = 4;
	// 100% always, 100% commum, 75% uncommum, 2% rare, 1% very rare
	// public static final double[] DROP_RATES = { 100.0, 100.0, 75.0, 2.0, 1.0
	// };

	// 2nd eco
	/*
	 * public static final double[] DROP_RATES = { 100.0, 90.0, 70.0, 0.5, 0.3
	 * };
	 */
	// boosted 20%
	public static final double[] DROP_RATES = { 100.0, 90.0, 70.0, 0.6, 0.36 };

	public static final int[] CHARMS = { 12158, 12159, 12160, 12163 };
	public static final Drop1[] RARE_DROP_TABLE = { new Drop1(1623, 1, 1), new Drop1(1621, 1, 1), new Drop1(1619, 1, 1),
			new Drop1(1617, 1, 1), new Drop1(1453, 1, 1), new Drop1(1462, 1, 1), new Drop1(987, 1, 1),
			new Drop1(985, 1, 1), new Drop1(995, 250, 1200), new Drop1(1247, 1, 1), new Drop1(830, 5, 5),
			new Drop1(1201, 1, 1), new Drop1(1319, 1, 1), new Drop1(1373, 1, 1), new Drop1(2366, 1, 1),
			new Drop1(1249, 1, 1), new Drop1(1149, 1, 1), new Drop1(563, 45, 45), new Drop1(563, 5, 50),
			new Drop1(561, 47, 77), new Drop1(566, 20, 20), new Drop1(565, 50, 50), new Drop1(892, 150, 150),
			new Drop1(443, 100, 100), new Drop1(995, 250, 1200) // again
			, new Drop1(1215, 1, 1), new Drop1(892, 150, 500) // again
			, new Drop1(9143, 200, 200), new Drop1(533, 151, 500), new Drop1(2999, 25, 250), new Drop1(258, 33, 33),
			new Drop1(3001, 30, 120), new Drop1(270, 10, 100), new Drop1(454, 150, 7500), new Drop1(450, 150, 800),
			new Drop1(7937, 100, 14500), new Drop1(1441, 25, 35), new Drop1(1443, 25, 36), new Drop1(1444, 1, 1),
			new Drop1(372, 125, 1000), new Drop1(383, 250, 500), new Drop1(5321, 3, 3), new Drop1(1631, 1, 1),
			new Drop1(1615, 1, 1), new Drop1(1391, 200, 200), new Drop1(574, 1000, 1000), new Drop1(570, 1000, 1000),
			new Drop1(451, 1, 100), new Drop1(2362, 1450, 7000), new Drop1(2364, 1, 150), new Drop1(5315, 1, 50),
			new Drop1(5316, 1, 6), new Drop1(5289, 10, 10), new Drop1(5304, 1, 31), new Drop1(5300, 1, 1),
			new Drop1(1516, 100, 4500), new Drop1(21620, 4, 4), new Drop1(9342, 150, 150), new Drop1(1216, 50, 50),
			new Drop1(20667, 1, 1), new Drop1(6686, 250, 250) };

	private boolean acessRareTable;
	private Drop1[][] drops;
	private Drop1[][] gearRareDrop1s;

	public Drops(boolean acessRareTable) {
		this.acessRareTable = acessRareTable;
		drops = new Drop1[VERY_RARE + 1][];
		gearRareDrop1s = new Drop1[VERY_RARE - RARE + 1][];

	}

	public List<Drop1> generateDrop1s(Player killer, double e) {
		List<Drop1> d = new ArrayList<Drop1>();
		boolean ringOfRecoil = hasRingOfWealth(killer);
		if (ringOfRecoil)
			e *= 1.01; // 1% extra chance
		if (drops[ALWAYS] != null) {
			for (Drop1 drop : drops[ALWAYS])
				d.add(drop);
		}
		for (int i = COMMOM; i <= VERY_RARE; i++) {
			Drop1 drop = getDrop1(i, e);
			if (drop != null) {
				if (i >= RARE && ringOfRecoil) {
					killer.getPackets().sendGameMessage("<col=ff7000>Your ring of wealth shines more brightly!", true);
					ringOfRecoil = false;
				}
				d.add(drop);
			}
		}
		if (acessRareTable && Utils.random((int) (3000 / e)) == 0) {
			Drop1 drop = getRareDrop1Table();
			if (drop.getItemId() != 20667 || ringOfRecoil)
				d.add(drop);
		}
		return d;
	}

	private boolean hasRingOfWealth(Player player) {
		return ItemDefinitions.getItemDefinitions(player.getEquipment().getRingId()).getName().toLowerCase()
				.contains("wealth");
	}

	public void setAcessRareTable(boolean t) {
		acessRareTable = t;
	}

	public void addCharms(List<Drop1> d, int size) {
		if (!d.isEmpty() && Utils.random(8 / size) == 0)
			d.add(new Drop1(CHARMS[Utils.random(CHARMS.length)], 1, size));
	}

	public Drop1 getRareDrop1Table() {
		return RARE_DROP_TABLE[Utils.random(RARE_DROP_TABLE.length)];
	}

	public Drop1[] getDrop1s(int rarity) {
		return drops[rarity];
	}

	public Drop1 getDrop1(int rarity, double e) {
		if (rarity >= RARE) {
			if (gearRareDrop1s[rarity - RARE] != null && gearRareDrop1s[rarity - RARE].length != 0
					&& Math.random() * 100 <= (DROP_RATES[rarity] * e))
				return gearRareDrop1s[rarity - RARE][Utils.random(gearRareDrop1s[rarity - RARE].length)];
		}
		if (drops[rarity] != null && drops[rarity].length != 0 && Math.random() * 100 <= (DROP_RATES[rarity] * e))
			return drops[rarity][Utils.random(drops[rarity].length)];
		return null;
	}

	public static boolean countsAsGear(int id) {
		return id == 13754 || id == 11286 || id == 21369 || id == 13746 || id == 13748 || id == 13750 || id == 13752;
	}

	public void addDrops(List<Drop1>[] dList) {
		for (int i = 0; i < dList.length; i++) {
			if (dList[i] == null)
				continue;
			if (i >= RARE) {
				ArrayList<Drop1> cleanedGear = new ArrayList<Drop1>();
				for (Drop1 drop : dList[i].toArray(new Drop1[dList[i].size()])) {
					if (countsAsGear(drop.getItemId())
							|| ItemDefinitions.getItemDefinitions(drop.getItemId()).isWearItem()) {
						cleanedGear.add(drop);
						dList[i].remove(drop);
					}
				}
				if (cleanedGear.size() > 0)
					gearRareDrop1s[i - RARE] = cleanedGear.toArray(new Drop1[cleanedGear.size()]);
			}
			drops[i] = dList[i].toArray(new Drop1[dList[i].size()]);
		}
	}
}