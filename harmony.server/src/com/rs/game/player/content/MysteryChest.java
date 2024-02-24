package com.rs.game.player.content;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.World;
import com.rs.game.player.Player;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Colors;
import com.rs.utils.Utils;

public class MysteryChest {
	
	/**
	 * Common - skilling supplies & cosmetic like black g etc, cannon balls etc
	 * Uncommon - High end skilling such as onyx, frost bones etc Rare - Barrows,
	 * occuly necklace, elite crystal key x2, crystal key x5, dwarf cannon etc
	 */

	private static final int COMMON[][] = { { 208, 100 }, { 210, 100 }, { 212, 100 }, { 214, 100 }, { 216, 100 }, { 218, 100 }, { 220, 100 }, { 2486, 100 }, { 1520, 150 }, { 1516, 150 }, { 1514, 150 }, { 5313, 10 }, { 5314, 10 }, { 5315, 10 }, { 5316, 10 }, { 5297, 10 }, { 5298, 10 }, { 5299, 10 }, { 5300, 10 }, { 5301, 10 }, { 5302, 10 }, { 5303, 10 }, { 5304, 10 }
			, { 1618, 125 }, { 1620, 125 }, { 1622, 125 }, { 1624, 125 }, { 1626, 125 }, { 537, 75 }, { 18831, 75 }, { 448, 75 }, { 450, 75 }, { 452, 75 }, { 537, 75 }, { 2, 500 }, { 6571, 1 }, { 6, 1 }, { 8, 1 }, { 10, 1 }, { 12, 1 }
			, { 2669, 1 }, { 2671, 1 }, { 2673, 1 }, { 2675, 1 }, { 2653, 1 }, { 2655, 1 }, { 2657, 1 }, { 2659, 1 }, { 2661, 1 }, { 2663, 1 }, { 2665, 1 }, { 2667, 1 }, { 15503, 1 }, { 15505, 1 }, { 15507, 1 }, { 15509, 1 }, { 15511, 1 }
			, { 19341, 1 }, { 19342, 1 }, { 19343, 1 }, { 19336, 1 }, { 19337, 1 }, { 990, 5 }, { 19338, 1 }, { 19340, 1 }, { 19345, 1 }, { 4151, 1 }, { 10548, 1 }, { 10551, 1 }, { 7462, 1 } };
	private static final int RARE[][] = { { 13744, 1 }, { 13738, 1 }, { 13740, 1 }, { 13742, 1 }, { 29425, 3 }, { 29594, 1 }, { 29592, 1 }, { 29441, 1 }, { 29447, 1 }, { 29440, 1 }, { 29869, 1 }, { 2577, 1 }, { 7806, 1 }, { 7808, 1 }, { 7809, 1 }, { 20171, 1 }, { 24986, 1 }, { 24980, 1 }, { 20167, 1 }, { 20163, 1 }, { 20159, 1 }, { 24989, 1 }, { 20147, 1 }, { 20151, 1 }, { 20155, 1 }, { 24974, 1 }, { 24977, 1 }, { 24983, 1 }, { 20135, 1 }, { 20139, 1 }, { 20143, 1 }, { 29450, 1 }, { 14484, 1 }, { 19670, 1 }, { 11846, 1 }, { 11848, 1 }, { 11850, 1 }, { 11852, 1 }, { 11854, 1 }, { 11856, 1 }, { 21787, 1 }, { 21790, 1 }, { 21793, 1 }, { 29713, 1 }, { 29714, 1 }, { 29715, 1 } };
    private static int reward, r, c, q, vr;

    private static void handle(Player p) {
		// if (p.getLockDelay() > 0)
		// return;
        p.getInventory().deleteItem(29426, 1);
        p.getPackets().sendSound(98, 0, 1);
		WorldTasksManager.schedule(new WorldTask() {
			int loop;

			@Override
			public void run() {
				if (loop == 0) {
					p.lock();
					p.getInterfaceManager().sendInterface(926);
					p.getPackets().sendIComponentText(926, 43, "Mystery Chest");
					p.getPackets().sendIComponentText(926, 18, "");
					p.getPackets().sendIComponentText(926, 19, "");
					p.getPackets().sendIComponentText(926, 20, "");
					p.getPackets().sendIComponentText(926, 21, "");
					p.getPackets().sendIComponentText(926, 22, "");
					p.getPackets().sendIComponentText(926, 23, "");
					p.getPackets().sendIComponentText(926, 24, "");
					p.getPackets().sendIComponentText(926, 25, "");
					p.getPackets().sendIComponentText(926, 26, "");
					p.getPackets().sendIComponentText(926, 27, "");
					p.getPackets().sendIComponentText(926, 28, "");
					p.getPackets().sendIComponentText(926, 29, "");
					p.getPackets().sendIComponentModel(926, 44, 1050);
				} else if (loop >= 1 && loop < 10) {
					reward(p, loop);
					p.getPackets().sendItemOnIComponent(926, 44, reward, 1);
				} else if (loop == 10) {
					reward(p, loop);
					p.getPackets().sendItemOnIComponent(926, 44, reward, 1);
					p.unlock();
					stop();

				}
				loop++;
				// System.out.println(loop);
			}
		}, 0, 1);

	}

	private static void reward(Player p, int loop) {
		r = Utils.random(24);
        switch (r) {
        case 3:
            r = Utils.random(RARE.length);
            q = RARE[r][1];
			reward = RARE[r][0];
			if (loop == 10) {
				p.getInventory().addItemDrop(RARE[r][0], q);
            p.sm("You open the box and find " + getGrammar() + " " + getNameForItem() + ".");
			World.sendWorldMessage(Colors.darkRed + "[News] " + p.getDisplayName() + " has obtained " + Utils.format(q) + " x  " + getNameForItem() + " from a Mystery Chest!", false);
			}
            break;
            default:
                c = Utils.random(COMMON[0][1]);
                r = Utils.random(COMMON.length);
                q = COMMON[r][1];
			reward = COMMON[r][0];
			if (loop == 10) {
				p.getInventory().addItemDrop(COMMON[r][0], COMMON[r][0] == 995 ? c : q);
                p.sm("You open the box and find " + (COMMON[r][0] == 995 ? c : getGrammar()) + " "
                        + getNameForItem() + ", " + (COMMON[r][0] == 995 ? "." : "."));
			}
			c = Utils.random(COMMON[0][1]);
			r = Utils.random(COMMON.length);
			q = COMMON[r][1];
			reward = COMMON[r][0];
			if (loop == 10) {
				p.getInventory().addItemDrop(COMMON[r][0], COMMON[r][0] == 995 ? c : q);
			p.sm("You open the box and find " + (COMMON[r][0] == 995 ? c : getGrammar()) + " " + getNameForItem() + ", " + (COMMON[r][0] == 995 ? "." : "."));
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
            case 29426:
                handle(p);
                return true;
        }
        return false;
    }
}