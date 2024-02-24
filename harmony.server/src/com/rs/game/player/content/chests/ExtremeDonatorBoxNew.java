package com.rs.game.player.content.chests;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.World;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.game.player.interfaces.LootBoxInterface;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Colors;
import com.rs.utils.Utils;

/**
 * @author Mr_Joopz
 */
public class ExtremeDonatorBoxNew {


	private static final int COMMON[][] = { { 29370, 2000 },{ 5022, 25 }, { 25202, 3 }, { 29654, 1 }, { 2581, 1 }, { 2577, 1 }, { 5022, 45 }, { 19670, 1 }, { 14484, 1 }, { 29306, 1 }, { 29310, 1 }, { 29869, 1 }, { 22360, 1 }, { 5022, 50 }, { 11694, 1 }, { 11700, 1 }, { 11696, 1 }, { 11698, 1 }, { 5022, 75 }, { 5022, 100 }, { 29798, 1 }, { 21787, 1 }, { 21793, 1 }, { 21790, 1 }, { 22482, 1 }, { 22486, 1 }, { 22490, 1 }, { 22494, 1 }, { 20171, 1 }, { 20167, 1 }, { 20163, 1 }, { 20159, 1 }, { 20155, 1 }, { 20151, 1 }, { 20147, 1 }, { 20143, 1 }, { 20139, 1 }, { 20135, 1 }, { 11718, 1 }, { 11726, 1 }, { 11720, 1 }, { 11722, 1 }, { 11724, 1 }, { 13752, 1 }, { 5022, 30 }, { 6570, 1 } };
	private static final int VERY_RARE[][] = { { 29370, 10000 },{ 29440, 1 },{ 1050, 1 },{ 1038, 1 },{ 1040, 1 },{ 1042, 1 },{ 1044, 1 },{ 1046, 1 },{ 1048, 1 },{ 29413, 1 }, { 29372, 1 }, { 29373, 1 }, { 7806, 1 }, { 7808, 1 }, { 7809, 1 }, { 29447, 1 }, { 29469, 1 }, { 29468, 1 }, { 29456, 1 }, { 5022, 200 }, { 29461, 1 }, { 13746, 1 }, { 13748, 1 }, { 13750, 1 } };
	private static final int ULTRA_RARE[][] = { { 29370, 30000 },{ 29910, 1 },{ 29459, 1 }, { 29334, 1 }, { 29337, 1 }, { 29472, 1 }, { 29428, 1 } };
    private static int reward, r, c, q, vr;

	private static void handle(Player p) {
		if (p.isLocked()) {
			return;
		}
		if (p.getInventory().getFreeSlots() < 2) {
			p.sendMessage(Colors.red+"Please make 2 free inventory slots before proceeding!");
			return;
		}
		if (!World.isHomeArea(p)) {
			p.sendMessage(Colors.red+"You must open this box within the home area!");
			return;
		}
		p.getInventory().deleteItem(28964, 1);
		p.lock();
		p.getPackets().sendSound(98, 0, 1);
		LootBoxInterface.SendInterface(p, 4000);
		p.getPackets().sendHideIComponent(3025, 9, true);
		p.getPackets().sendHideIComponent(3025, 23, true);
		p.getPackets().sendHideIComponent(3025, 25, true);
		p.getPackets().sendHideIComponent(3025, 27, true);
		p.getPackets().sendHideIComponent(3025, 29, true);
		WorldTasksManager.schedule(new WorldTask() {
			int loop;

			@Override
			public void run() {
				if (loop == 0) {
			
				} else if (loop == 1) {
					reward(p, loop);
				} else if (loop == 3) {
					reward(p, loop);
				} else if (loop == 5) {
					p.getPackets().sendHideIComponent(3025, 23, false);
					p.getPackets().sendHideIComponent(3025, 25, false);
					p.getPackets().sendHideIComponent(3025, 27, false);
					p.getPackets().sendHideIComponent(3025, 29, false);
					reward(p, loop);
					stop();

				}
				loop++;
			}
		}, 0, 1);

	}

	private static void reward(Player p, int loop) {
		r = Utils.random(45);
		switch (r) {
		case 1:
			vr = Utils.random(0, 90);
			if (vr == 0) {
				r = Utils.random(ULTRA_RARE.length);
				q = ULTRA_RARE[r][1];
				reward = ULTRA_RARE[r][0];
				if (loop == 1) {
					p.getBoxI1().getContainer().clear();
					p.getBoxI1().getContainer().add(new Item(ULTRA_RARE[r][0], q));
					p.getPackets().sendInterSetItemsOptionsScript(3025, 20, 0, 8, 3, "Examine");
					p.getPackets().sendUnlockIComponentOptionSlots(3025, 20, 0, 10, 0, 1, 2, 3);
					p.getBoxI1().getContainer().shift();
					p.getPackets().sendItems(100, p.getBoxI1().getContainer());
				} else if (loop == 3) {
					p.getBoxI2().getContainer().clear();
					p.getBoxI2().getContainer().add(new Item(ULTRA_RARE[r][0], q));
					p.getPackets().sendInterSetItemsOptionsScript(3025, 21, 101, 8, 3, "Examine");
					p.getPackets().sendUnlockIComponentOptionSlots(3025, 21, 0, 10, 0, 1, 2, 3);
					p.getBoxI2().getContainer().shift();
					p.getPackets().sendItems(101, p.getBoxI2().getContainer());
				} else if (loop == 5) {
					p.getBoxI3().getContainer().clear();
					p.getBoxI3().getContainer().add(new Item(ULTRA_RARE[r][0], q));
					p.getPackets().sendInterSetItemsOptionsScript(3025, 22, 102, 8, 3, "Examine");
					p.getPackets().sendUnlockIComponentOptionSlots(3025, 22, 0, 10, 0, 1, 2, 3);
					p.getBoxI3().getContainer().shift();
					p.getPackets().sendItems(102, p.getBoxI3().getContainer());
				}
			} else {
				r = Utils.random(VERY_RARE.length);
				q = VERY_RARE[r][1];
				reward = VERY_RARE[r][0];
				if (loop == 1) {
					p.getBoxI1().getContainer().clear();
					p.getBoxI1().getContainer().add(new Item(VERY_RARE[r][0], q));
					p.getPackets().sendInterSetItemsOptionsScript(3025, 20, 100, 8, 3, "Examine");
					p.getPackets().sendUnlockIComponentOptionSlots(3025, 20, 0, 10, 0, 1, 2, 3);
					p.getBoxI1().getContainer().shift();
					p.getPackets().sendItems(100, p.getBoxI1().getContainer());
				} else if (loop == 3) {
					p.getBoxI2().getContainer().clear();
					p.getBoxI2().getContainer().add(new Item(VERY_RARE[r][0], q));
					p.getPackets().sendInterSetItemsOptionsScript(3025, 21, 101, 8, 3, "Examine");
					p.getPackets().sendUnlockIComponentOptionSlots(3025, 21, 0, 10, 0, 1, 2, 3);
					p.getBoxI2().getContainer().shift();
					p.getPackets().sendItems(101, p.getBoxI2().getContainer());
				} else if (loop == 5) {
					p.getBoxI3().getContainer().clear();
					p.getBoxI3().getContainer().add(new Item(VERY_RARE[r][0], q));
					p.getPackets().sendInterSetItemsOptionsScript(3025, 22, 102, 8, 3, "Examine");
					p.getPackets().sendUnlockIComponentOptionSlots(3025, 22, 0, 10, 0, 1, 2, 3);
					p.getBoxI3().getContainer().shift();
					p.getPackets().sendItems(102, p.getBoxI3().getContainer());
				}
			}
			break;

		default:
			c = Utils.random(COMMON[0][1]);
			r = Utils.random(COMMON.length);
			q = COMMON[r][1];
			reward = COMMON[r][0];
			if (loop == 1) {
				p.getBoxI1().getContainer().clear();
				p.getBoxI1().getContainer().add(new Item(COMMON[r][0], q));
				p.getPackets().sendInterSetItemsOptionsScript(3025, 20, 100, 8, 3, "Examine");
				p.getPackets().sendUnlockIComponentOptionSlots(3025, 20, 0, 10, 0, 1, 2, 3);
				p.getBoxI1().getContainer().shift();
				p.getPackets().sendItems(100, p.getBoxI1().getContainer());
			} else if (loop == 3) {
				p.getBoxI2().getContainer().clear();
				p.getBoxI2().getContainer().add(new Item(COMMON[r][0], q));
				p.getPackets().sendInterSetItemsOptionsScript(3025, 21, 101, 8, 3, "Examine");
				p.getPackets().sendUnlockIComponentOptionSlots(3025, 21, 0, 10, 0, 1, 2, 3);
				p.getBoxI2().getContainer().shift();
				p.getPackets().sendItems(101, p.getBoxI2().getContainer());
			} else if (loop == 5) {
				p.getBoxI3().getContainer().clear();
				p.getBoxI3().getContainer().add(new Item(COMMON[r][0], q));
				p.getPackets().sendInterSetItemsOptionsScript(3025, 22, 102, 8, 3, "Examine");
				p.getPackets().sendUnlockIComponentOptionSlots(3025, 22, 0, 10, 0, 1, 2, 3);
				p.getBoxI3().getContainer().shift();
				p.getPackets().sendItems(102, p.getBoxI3().getContainer());
			}
			break;
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
            case 1061:
                return "pair of leather boots";
            case 592:
                return "ash";
            case 563:
                return "law runes";
            case 561:
                return "nature runes";
            case 1329:
                return "mithril scimitar";
            case 1315:
                return "mithril two handed sword";
        }
        return ItemDefinitions.getItemDefinitions(reward).getName().toLowerCase();
    }

    public static boolean isBox(int itemId, Player p) {
        switch (itemId) {
            case 28964:
                handle(p);
                return true;
        }
        return false;
    }
}