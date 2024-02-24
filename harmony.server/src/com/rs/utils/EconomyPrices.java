package com.rs.utils;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.item.Item;
import com.rs.game.player.content.ItemConstants;

public final class EconomyPrices {

	public static int getPrice(int itemId) {
		ItemDefinitions defs = ItemDefinitions.getItemDefinitions(itemId);
		if (defs.isNoted())
			itemId = defs.getCertId();
		else if (defs.isLended())
			itemId = defs.getLendId();
		if (!ItemConstants.isTradeable(new Item(itemId, 1)))
			return 0;
		if (itemId == 995) // TODO after here
			return 1;
		/*if (itemId == 5021)
			defs.setValue(2017);
		if (itemId == 618 || itemId == 25205)
			defs.stackable = 1;
		if (itemId == 1615 || itemId == 1616)
			defs.setValue(28000);
		if (itemId == 1601 || itemId == 1602)
			defs.setValue(5400);
		if (itemId == 1603 || itemId == 1604)
			defs.setValue(2800);
		if (itemId == 31724 ||itemId == 31723 ||itemId == 31722)
			defs.setValue(50000000);
		if (itemId == 4716 || itemId == 4717 || itemId == 4718 || itemId == 4719
				|| itemId == 4720 || itemId == 4721 || itemId == 4722 || itemId == 4723
				|| itemId == 4708 || itemId == 4709 || itemId == 4710 || itemId == 4711
				|| itemId == 4712 || itemId == 4713 || itemId == 4714 || itemId == 4715
				|| itemId == 4724 || itemId == 4725 || itemId == 4726 || itemId == 4727
				|| itemId == 4728 || itemId == 4729 || itemId == 4730 || itemId == 4731
				|| itemId == 4745 || itemId == 4746 || itemId == 4747 || itemId == 4748
				|| itemId == 4749 || itemId == 4750 || itemId == 4751 || itemId == 4752
				|| itemId == 4732 || itemId == 4733 || itemId == 4734 || itemId == 4735
				|| itemId == 4736 || itemId == 4737 || itemId == 4738 || itemId == 4739
				|| itemId == 4753 || itemId == 4754 || itemId == 4755 || itemId == 4756
				|| itemId == 4757 || itemId == 4758 || itemId == 4759 || itemId == 4760
				|| itemId == 25652 || itemId == 25653 || itemId == 25672 || itemId == 25673
				|| itemId == 30930 || itemId == 30931 || itemId == 30933 || itemId == 30934
				|| itemId == 30936 || itemId == 30937 || itemId == 30939 || itemId == 30940
				|| itemId == 31189 || itemId == 31190)
			defs.setValue(9000000);
		if (itemId == 32692 || itemId == 32693)
			defs.setValue(100000000);
		if (itemId == 33879 || itemId == 33882
				|| itemId == 33886 || itemId == 33889
				|| itemId == 28357)
			defs.setValue(50000000);
		if (itemId == 30014 || itemId == 30015
				|| itemId == 30018 || itemId == 30019
				|| itemId == 30022 || itemId == 30023)
			defs.setValue(150000000);
		if (itemId == 1038 || itemId == 1039
				|| itemId == 1040 || itemId == 1041
				|| itemId == 1042 || itemId == 1043
				|| itemId == 1044 || itemId == 1045
				|| itemId == 1046 || itemId == 1047
				|| itemId == 1048 || itemId == 1049)
			defs.setValue(2100000000);
		if (itemId == 34151 || itemId == 34152
				|| itemId == 34153 || itemId == 34154
				|| itemId == 34156 || itemId == 34157
				|| itemId == 34158 || itemId == 34155
				|| itemId == 34150)
			defs.setValue(60000000);
		if (itemId == 1050 || itemId == 1051
				|| itemId == 1053 || itemId == 1054
				|| itemId == 1055 || itemId == 1056
				|| itemId == 1057 || itemId == 1058
				|| itemId == 30412 || itemId == 30413)
			defs.setValue(900000000);
		if (itemId == 30005 || itemId == 30006
				|| itemId == 30007 || itemId == 30008
				|| itemId == 30009 || itemId == 30010
				 || itemId == 30011  || itemId == 30012
				 || itemId == 30013 
				 || itemId == 28608  || itemId == 28609
				 || itemId == 28610  || itemId == 28611
				 || itemId == 28612  || itemId == 28613
				 || itemId == 28614  || itemId == 28615
				 || itemId == 28616 || itemId == 29854
				 || itemId == 29855 || itemId == 29856
				 || itemId == 29857 || itemId == 29858
				 || itemId == 29859 || itemId == 29860
				 || itemId == 29861 || itemId == 29862)
			defs.setValue(150000000);
		if (itemId == 26579 || itemId == 26580
				 || itemId == 26581  || itemId == 26582
				 || itemId == 26583  || itemId == 26584
				 || itemId == 26585  || itemId == 26586
				 || itemId == 26587  || itemId == 26588
				 || itemId == 26589  || itemId == 26590
				 || itemId == 26591 || itemId == 26592
				 || itemId == 26593 || itemId == 26594
				 || itemId == 26595 || itemId == 26596
				 || itemId == 26597 || itemId == 26598
				 || itemId == 26599 || itemId == 26600
				 || itemId == 26601 || itemId == 26602
				 || itemId == 28617 || itemId == 28618
				 || itemId == 28619 || itemId == 28620
				 || itemId == 28621 || itemId == 28622
				 || itemId == 28623 || itemId == 28624
				 || itemId == 28437 || itemId == 28438
				 || itemId == 28439 || itemId == 28440
				 || itemId == 28441 || itemId == 28442
				 || itemId == 28443 || itemId == 28444)
			defs.setValue(300000000);
		if (itemId == 20135 || itemId == 20136
				|| itemId == 25654 || itemId == 25655
						|| itemId == 25664 || itemId == 25665
				|| itemId == 20139|| itemId == 20140
				|| itemId == 20143|| itemId == 20144
				|| itemId == 24977 || itemId == 24983
				|| itemId == 20147 || itemId == 20148
				|| itemId == 20151 || itemId == 20152
				|| itemId == 20155 || itemId == 20156
				|| itemId == 24974 || itemId == 24989
				|| itemId == 20159 || itemId == 20160
				|| itemId == 20163 || itemId == 20164
				|| itemId == 20167 || itemId == 20168
				|| itemId == 24980 || itemId == 24986
				|| itemId == 25654 || itemId == 25664
				|| itemId == 26334 || itemId == 26335
				|| itemId == 26336 || itemId == 26352
				|| itemId == 26353 || itemId == 26354)
			defs.setValue(125000000);
		if (itemId == 20171 || itemId == 20172)
			defs.setValue(50000000);
		if (itemId == 4151 || itemId == 4152)
			defs.setValue(5000000);
		if (itemId == 30825 || itemId == 30826 ||
				itemId == 30828 || itemId == 30829)
			defs.setValue(50000000);
		if (itemId == 21787 || itemId == 21788 ||
				itemId == 21790 || itemId == 21791 ||
				itemId == 21793 || itemId == 21794)
			defs.setValue(70000000);
		if (itemId == 1739 || itemId == 1740)
			defs.setValue(2500);
		if (itemId == 950 || itemId == 951)
			defs.setValue(5000);
		if (itemId == 199 || itemId == 200
				|| itemId == 201 || itemId == 202
				|| itemId == 203 || itemId == 204
				|| itemId == 205 || itemId == 206
				|| itemId == 207 || itemId == 208
				||itemId == 249|| itemId == 250||
				itemId == 251||itemId == 252||
				itemId == 253||
				itemId == 254||
				itemId == 255||
				itemId == 256||
				itemId == 257||
				itemId == 258)
			defs.setValue(3000);
		if (itemId == 209 || itemId == 210
				|| itemId == 211 || itemId == 212
				|| itemId == 213 || itemId == 214
				|| itemId == 215 || itemId == 216
				|| itemId == 217 || itemId == 218||
				itemId == 259||
				itemId == 260||
				itemId == 261||
				itemId == 262||
				itemId == 263||
				itemId == 264||
				itemId == 265||
				itemId == 266||
				itemId == 267||
				itemId == 268)
			defs.setValue(5000);
		if (itemId == 219 || itemId == 220
				||itemId == 270 || itemId == 269)
			defs.setValue(10000);
		if (itemId == 1635 || itemId == 1636)
			defs.setValue(15000);
		if (itemId == 1513 || itemId == 1514)
			defs.setValue(60000);
		if (itemId == 18830 || itemId == 18831)
			defs.setValue(300000);
		if (itemId == 5023)
			defs.setValue(2000000000);
		if (itemId == 5020)
			defs.setValue(1000000000);
		if (itemId == 5022)
			defs.setValue(1000000);
		if (itemId == 25205)
			defs.setValue(250000);
		if (itemId == 11724 || itemId == 11725 || itemId == 11726 || itemId == 11727
				||itemId == 11720 || itemId == 11721
				|| itemId == 11722 || itemId == 11723 || itemId == 25037 
				|| itemId == 25038 || itemId == 11702 || itemId == 11703
				|| itemId == 11694 || itemId == 11695 || itemId == 11696 || itemId == 11697
				|| itemId == 11698 || itemId == 11699 || itemId == 11700 || itemId == 11701
				|| itemId == 11704 || itemId == 11705 || itemId == 11706 || itemId == 11707
				|| itemId == 11708 || itemId == 11709 || itemId == 25037 || itemId == 25038)
			defs.setValue(70000000);
		if (itemId == 28457 || itemId == 28458 || itemId == 28459 || itemId == 28460 || itemId == 28461 || itemId == 28462)
			defs.setValue(10000000);
		if (itemId == 1038 || itemId == 1039 || itemId == 1040 
				|| itemId == 1041 || itemId == 1042 || itemId == 1043
				|| itemId == 1044 || itemId == 1045 || itemId == 1046
				|| itemId == 1047 || itemId == 1048 || itemId == 1049)
			defs.setValue(2147000000);
		if (itemId == 1050 || itemId == 1051)
			defs.setValue(1000000000);
		if (itemId == 13746 || itemId == 13747
				|| itemId == 13748 || itemId == 13749
				|| itemId == 13750 || itemId == 13751
				|| itemId == 13738 || itemId == 13739
				|| itemId == 13740 || itemId == 13741
				|| itemId == 13742 || itemId == 13743)
			defs.setValue(200000000);*/
		return defs.getValue(); // TODO get price from real item from saved
									// prices from ge
	}

	private EconomyPrices() {

	}
}
