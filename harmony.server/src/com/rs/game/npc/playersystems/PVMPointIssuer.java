package com.rs.game.npc.playersystems;

import com.rs.Settings;
import com.rs.game.World;
import com.rs.game.item.Item;
import com.rs.game.npc.NPC;
import com.rs.game.player.Player;
import com.rs.game.player.content.event.season.SeasonEvent;
import com.rs.utils.Utils;

/**
 * Handles issuing of Player v. Monster points
 * @author Kryeus / John J. Woloszyk
 * @date 10.29.2017
 */

public class PVMPointIssuer {

	
	public static void processKill(Player p, NPC n, int x, int y, int z)
	{
		int currentPlayerPoints = p.getDryPoints();
		int npcID = n.getId();
		int hp = n.getMaxHitpoints() / 100;
		int rdt = 500000 / n.getMaxHitpoints();
		int elitekey = 1000000 / n.getMaxHitpoints();
		for (int bosspet : Settings.PETS_WITH_PERKS) {
			if (p.getPetManager().getNpcId() == bosspet && p.getPetPerk().getContainer().contains(new Item(29386))) {
				elitekey = elitekey / 10 * 9;
			}
		}
		if (elitekey <= 20) {
			elitekey = 20;
		}
		if (rdt < 50) {
			rdt = 50;
		}
		if (hp < 1) {
			hp = 1;
		}
		if (Utils.random(0, 5) <= 1 && !World.TheCalamity(p) && Settings.HWEEN) {
			p.getInventory().addItemDrop(29319, Utils.random(hp));
		}
		if (Utils.random(0, elitekey) == 0 && !World.TheCalamity(p)) {
			if (p.getInventory().hasFreeSlots()) {
				p.getInventory().addItem(29425, 1);
			} else {
				p.getBank().addItem(29425, 1, true);
				p.sendMessage("Your Elite Crystal Key was sent to your bank as you had no inventory space!");
			}
		}
		if (Settings.eventdropeventtokens > 0 && n.getMaxHitpoints() > 100) {
			p.getInventory().addItemDrop(29226, Utils.random(n.getMaxHitpoints() / 100));
		}
		if (Utils.random(0, rdt) == 0) {
			if (n.getId() == 16385 || n.getName().equalsIgnoreCase("zulrah")) {
				GlobalDropTable.drop(p, p.getX(), p.getY(), p.getPlane());
			} else {
				GlobalDropTable.drop(p, x, y, z);
			}
		}
//		if (n.getName().equalsIgnoreCase("Wildywyrm")) {
//			SeasonEvent.HandleActivity(p, "Wilderness Bosses", 30);
//		} else if (n.getName().equalsIgnoreCase("Aquatic wyrm")) {
//			SeasonEvent.HandleActivity(p, "Wilderness Bosses", 35);
//		} else if (n.getName().equalsIgnoreCase("Sunfreet")) {
//			SeasonEvent.HandleActivity(p, "Wilderness Bosses", 50);
//		} else if (n.getName().equalsIgnoreCase("Sliske")) {
//			SeasonEvent.HandleActivity(p, "Wilderness Bosses", 50);
//		} else if (n.getName().equalsIgnoreCase("Sirenic the spider")) {
//			SeasonEvent.HandleActivity(p, "Wilderness Bosses", 40);
//		} else if (n.getName().equalsIgnoreCase("callisto")) {
//			SeasonEvent.HandleActivity(p, "Wilderness Bosses", 20);
//		} else if (n.getName().equalsIgnoreCase("venenatis")) {
//			SeasonEvent.HandleActivity(p, "Wilderness Bosses", 20);
//		} else if (n.getName().equalsIgnoreCase("chaos fanatic")) {
//			SeasonEvent.HandleActivity(p, "Wilderness Bosses", 20);
//		} else if (n.getName().equalsIgnoreCase("crazy archaeologist")) {
//			SeasonEvent.HandleActivity(p, "Wilderness Bosses", 20);
//		} else if (n.getName().equalsIgnoreCase("vet'ion")) {
//			SeasonEvent.HandleActivity(p, "Wilderness Bosses", 20);
//		} else if (n.getName().equalsIgnoreCase("scorpia")) {
//			SeasonEvent.HandleActivity(p, "Wilderness Bosses", 20);
//		}
		if (World.CatacombsOSRS(p)) {
			int shardtotem = 300 - n.getMaxHitpoints() / 2 / 2;
			if (shardtotem < 50) {
				shardtotem = 50;
			}
			if (Utils.random(0, shardtotem) == 0) {
				if (p.getInventory().hasFreeSlots()) {
					p.getInventory().addItem(29325, 1);
					p.sendMessage("You find an Ancient Shard.");
				} else {
					p.getBank().addItem(29325, 1, true);
					p.sendMessage("Your Ancient Shard was sent to your bank as you had no inventory space!");
				}
			}
			if (Utils.random(0, shardtotem) == 15) {
				if (p.getInventory().hasFreeSlots()) {
					p.getInventory().addItem(29324, 1);
					p.sendMessage("You find a Dark Totem Base.");
				} else {
					p.getBank().addItem(29324, 1, true);
					p.sendMessage("Your Dark Totem Base was sent to your bank as you had no inventory space!");
				}
			}
			if (Utils.random(0, shardtotem) == 30) {
				if (p.getInventory().hasFreeSlots()) {
					p.getInventory().addItem(29323, 1);
					p.sendMessage("You find a Dark Totem Middle.");
				} else {
					p.getBank().addItem(29323, 1, true);
					p.sendMessage("Your Dark Totem Middle was sent to your bank as you had no inventory space!");
				}
			}
			if (Utils.random(0, shardtotem) == 45) {
				if (p.getInventory().hasFreeSlots()) {
					p.getInventory().addItem(29322, 1);
					p.sendMessage("You find a Dark Totem Top.");
				} else {
					p.getBank().addItem(29322, 1, true);
					p.sendMessage("Your Dark Totem Top was sent to your bank as you had no inventory space!");
				}
			}
		}
		int extrap = 0;
		for (int bosspet : Settings.PETS_WITH_PERKS) {
			if (p.getPetManager().getNpcId() == bosspet && p.getPetPerk().getContainer().contains(new Item(29389))) {
				extrap = hp / 20;
				p.Drypoints = currentPlayerPoints + extrap;
				currentPlayerPoints = p.getDryPoints();
				extrap = 0;
			}
			if (p.getPetManager().getNpcId() == bosspet && p.getPetPerk().getContainer().contains(new Item(29388))) {
				extrap = extrap + hp / 14;
				p.Drypoints = currentPlayerPoints + extrap;
				currentPlayerPoints = p.getDryPoints();
				extrap = 0;
			}
			if (p.getPetManager().getNpcId() == bosspet && p.getPetPerk().getContainer().contains(new Item(29387))) {
				extrap = extrap + hp / 10;
				p.Drypoints = currentPlayerPoints + extrap;
				currentPlayerPoints = p.getDryPoints();
				extrap = 0;
			}
		}
		if (Settings.EVENT_SPOTLIGHT.equalsIgnoreCase("Double Harmony points")) {
			p.Drypoints = currentPlayerPoints + hp;
		}
		p.Drypoints = currentPlayerPoints + hp;
		p.sendMessage("You now have " + p.Drypoints + " Harmony points!");
//
	}

}
