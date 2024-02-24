//package com.rs.game.player.content;
//
//import com.rs.cache.loaders.ItemDefinitions;
//import com.rs.game.World;
//import com.rs.game.player.Player;
//import com.rs.utils.Colors;
//import com.rs.utils.Utils;
//
///**
// * @author HerBrightSkies @rune-server.org
// */
//public class Dbox {
//
//	/**
//	 * TODO - add osrs items, add barrows & dwarf cannon set function - super box to be same with better chance
//	 */
//
//	private static final int COMMON[][] = { { 5022, 25 }, { 25202, 3 }, { 29654, 1 }, { 2581, 1 }, { 2577, 1 }, { 5022, 45 }, { 19670, 1 }, { 14484, 1 }, { 29306, 1 }, { 29310, 1 }, { 29869, 1 }, { 22360, 1 }, { 5022, 50 }, { 11694, 1 }, { 11700, 1 }, { 11696, 1 }, { 11698, 1 }, { 5022, 75 }, { 5022, 100 }, { 29798, 1 }, { 21787, 1 }, { 21793, 1 }, { 21790, 1 }, { 22482, 1 }, { 22486, 1 }, { 22490, 1 }, { 22494, 1 }, { 20171, 1 }, { 20167, 1 }, { 20163, 1 }, { 20159, 1 }, { 20155, 1 }, { 20151, 1 }, { 20147, 1 }, { 20143, 1 }, { 20139, 1 }, { 20135, 1 }, { 11718, 1 }, { 11726, 1 }, { 11720, 1 }, { 11722, 1 }, { 11724, 1 }, { 13752, 1 }, { 5022, 30 }, { 6570, 1 } };
//	// private static final int UNCOMMON[][] = { };
//	private static final int VERY_RARE[][] = { { 29413, 1 }, { 29372, 1 }, { 29373, 1 }, { 7806, 1 }, { 7808, 1 }, { 7809, 1 }, { 29447, 1 }, { 29469, 1 }, { 29468, 1 }, { 29456, 1 }, { 5022, 200 }, { 29461, 1 }, { 13746, 1 }, { 13748, 1 }, { 13750, 1 } };
//	private static final int ULTRA_RARE[][] = { { 29459, 1 }, { 29334, 1 }, { 29337, 1 }, { 29472, 1 }, { 29428, 1 } };
//    private static int reward, r, c, q, vr;
//
//	private static void handle(Player p) {
//		// if (p.getLockDelay() > 0)
//		// return;
//		p.getInventory().deleteItem(29870, 1);
//		p.getPackets().sendSound(98, 0, 1);
//		reward(p);
//
//    }
//
//	private static void reward(Player p) {
//		r = Utils.random(15);
//		switch (r) {
//		case 1:
//			vr = Utils.random(0, 100);
//			if (vr == 0) {
//				r = Utils.random(ULTRA_RARE.length);
//				q = ULTRA_RARE[r][1];
//				reward = ULTRA_RARE[r][0];
//
//					p.getInventory().addItemDrop(ULTRA_RARE[r][0], q);
//				p.sm("You open the box and find " + getGrammar() + " " + getNameForItem() + "!");
//				World.sendWorldMessage(Colors.darkRed + "Donator Box:<img=14>" + p.getDisplayName() + " found " + Utils.format(q) + " x " + getNameForItem() + " in a Donator box as an Ultra rare reward!", false);
//
//			} else {
//				r = Utils.random(VERY_RARE.length);
//				q = VERY_RARE[r][1];
//				reward = VERY_RARE[r][0];
//
//					p.getInventory().addItemDrop(VERY_RARE[r][0], q);
//				p.sm("You open the box and find " + getGrammar() + " " + getNameForItem() + "!");
//
//			}
//			break;
//
//		default:
//			c = Utils.random(COMMON[0][1]);
//			r = Utils.random(COMMON.length);
//			q = COMMON[r][1];
//			reward = COMMON[r][0];
//
//				p.getInventory().addItemDrop(COMMON[r][0], COMMON[r][0] == 995 ? c : q);
//				p.sm("You open the box and find " + getGrammar() + " " + getNameForItem() + ".");
//
//			break;
//		}
//	}
//
//    private static String getGrammar() {
//        if (q == 1) {
//            return sw("a") || sw("u") || sw("o") ? "an" : "a";
//        }
//        return q + "";
//    }
//
//    private static boolean sw(String n) {
//        return getNameForItem().startsWith(n);
//    }
//
//    private static String getNameForItem() {
//        switch (reward) {
//            case 995:
//                return COMMON[r][1] == 1 ? "coin" : "coins";
//            case 1061:
//                return "pair of leather boots";
//            case 592:
//                return "ash";
//            case 563:
//                return "law runes";
//            case 561:
//                return "nature runes";
//            case 1329:
//                return "mithril scimitar";
//            case 1315:
//                return "mithril two handed sword";
//        }
//        return ItemDefinitions.getItemDefinitions(reward).getName().toLowerCase();
//    }
//
//    public static boolean isBox(int itemId, Player p) {
//        switch (itemId) {
//            case 29870:
//                handle(p);
//                return true;
//        }
//        return false;
//    }
//}