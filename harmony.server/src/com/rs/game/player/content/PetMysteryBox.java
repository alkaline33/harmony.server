package com.rs.game.player.content;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.World;
import com.rs.game.player.Player;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Colors;
import com.rs.utils.Utils;

public class PetMysteryBox {

	private static final int PETS[][] = { { 29861, 1 }, { 29851, 1 }, { 29797, 1 }, { 12496, 1 }, { 12469, 1 }, { 12471, 1 }, { 12473, 1 }, { 12475, 1 }, { 13335, 1 }, { 12481, 1 }, { 14533, 1 }, };
	private static final int COMMON[][] = { { 29401, 1 }, { 29400, 1 }, { 29398, 1 }, { 29397, 1 }, { 29395, 1 }, { 29394, 1 }, { 29392, 1 }, { 29391, 1 }, { 29389, 1 }, { 29388, 1 }, { 29386, 1 }, { 29385, 1 }, { 29384, 1 } };
	private static final int RARE[][] = { { 29274, 1 }, { 29121, 1 }, { 29122, 1 }, { 29273, 1 }, { 29272, 1 }, { 29271, 1 }, { 29399, 1 }, { 29396, 1 }, { 29393, 1 }, { 29390, 1 }, { 29387, 1 }, };
	// need add kodai wand
	private static int reward, r, c, q, vr;

	private static void handle(Player p) {
		// if (p.getLockDelay() > 0)
		// return;
		p.getInventory().deleteItem(29383, 1);
		p.getPackets().sendSound(98, 0, 1);

		WorldTasksManager.schedule(new WorldTask() {
			int loop;

			@Override
			public void run() {
				if (loop == 0) {
					p.lock();
					p.getInterfaceManager().sendInterface(926);
					p.getPackets().sendIComponentText(926, 43, "Pet Perk Mystery Box");
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
				} else if (loop >= 1 && loop < 4) {
					reward(p, loop);
					p.getPackets().sendItemOnIComponent(926, 44, reward, 1);
				} else if (loop == 4) {
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
		r = Utils.random(10);
		switch (r) {
		case 2:
		case 6:
			r = Utils.random(RARE.length);
			q = RARE[r][1];
			reward = RARE[r][0];
			if (loop == 4) {
				p.getInventory().addItemDrop(RARE[r][0], q);
				p.sm("You open the box and find " + getGrammar() + " " + getNameForItem() + " .");
				World.sendIconWorldMessage(Colors.yellow + "" + p.getDisplayName() + " has found a " + getNameForItem() + " inside a Pet Mystery box!", false);
			}
			break;
		default:
			c = Utils.random(COMMON[0][1]);
			r = Utils.random(COMMON.length);
			q = COMMON[r][1];
			reward = COMMON[r][0];
			if (loop == 4) {
				p.getInventory().addItemDrop(COMMON[r][0], COMMON[r][0] == 995 ? c : q);
				p.sm("You open the box and find " + getGrammar() + " " + getNameForItem() + ".");
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
		case 29383:
			handle(p);
			return true;
		}
		return false;
	}
}