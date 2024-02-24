package com.rs.game.player.controlers;

import com.rs.Settings;
import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.player.actions.Summoning.Pouches;
import com.rs.game.player.content.Magic;

public class Dryaxions extends Controler {
	
	public static final WorldTile OUTSIDE_AREA = new WorldTile(2354, 3688, 0);

	@Override
	public void start() {
		int itemid = hasFamiliarPouch();
		//if (!player.isExtremeDonator()) {
		//	player.getDialogueManager().startDialogue("SimpleMessage", "You have to be an extreme donator to enter this minigame.");
		//	player.getControlerManager().removeControlerWithoutCheck();
		//	return;
		if (hasFollower()) {
			player.getDialogueManager().startDialogue("SimpleMessage", "You cannot take a familiar into here.");
			player.getControlerManager().removeControlerWithoutCheck();
			return;
		} else if (itemid != -1) {
			player.getDialogueManager().startDialogue("SimpleMessage", "You cannot take " + ItemDefinitions.getItemDefinitions(itemid).getName().toLowerCase() + " into here.");
			player.getControlerManager().removeControlerWithoutCheck();
			return;
		}
		//player.setNextGraphics(new Graphics(3310));
		//player.setNextGraphics(new Graphics(3311));
		Magic.sendDemonTeleportSpell(player, 0, 0, new WorldTile(3192, 9824, 0));
	}

//	@Override
	//public boolean login() {
	//	moved();
	//	return false;
	//}
	
	@Override
	public boolean login() {
		return true;
	}

	@Override
	public boolean logout() {
		endActivity(true);
		player.setLocation(OUTSIDE_AREA);
		return true;
	}

	@Override
	public void forceClose() {
		endActivity(false);
	}
	
	@Override
	public boolean processMagicTeleport(WorldTile toTile) {
		if (World.isMiningAreaBad(player)) {
			player.getPackets().sendGameMessage(
					"A mysterious force prevents you from teleporting.");
			return false;
		}
		return true;

	}

	@Override
	public boolean processItemTeleport(WorldTile toTile) {
		if (World.isMiningAreaBad(player)) {
			player.getPackets().sendGameMessage(
					"A mysterious force prevents you from teleporting.");
			return false;
		}
		return true;
	}

	@Override
	public boolean processObjectTeleport(WorldTile toTile) {
		if (World.isMiningAreaBad(player)) {
			player.getPackets().sendGameMessage(
					"A mysterious force prevents you from teleporting.");
			return false;
		}
		return true;
	}
	
	private void endActivity(boolean logout) {
		if (!logout && !player.isDead()) { 
			if (player.getAttackedBy() != null) {
				player.getDialogueManager().startDialogue("SimpleMessage", "You cannot escape while you're under attack!");
				return;
			}
		}
		player.setForceMultiArea(false);
		player.getControlerManager().removeControlerWithoutCheck();
		player.setNextWorldTile(OUTSIDE_AREA);
		player.getInterfaceManager().sendMagicBook();
		player.getInterfaceManager().sendEmotes();
	

				}
	
	
	/**
	 * Checks if the player's inventory contains familiar pouches.
	 * @return The item id.
	 */
	private int hasFamiliarPouch() {
		for (int i = 0; i < 28; i++) {
			Item item = player.getInventory().getItem(i);
			if (item != null && Pouches.forId(item.getId()) != null) {
				return item.getId();
			}
		}
		for (int i = 0; i < 12; i++) {
			Item item = player.getEquipment().getItem(i);
			if (item != null && Pouches.forId(item.getId()) != null) {
				return item.getId();
			}
		}
		//TODO: Pets maybe?
		return -1;
	}
	
	/**
	 * If the player has a follower.
	 * @return {@code True} if so.
	 */
	private boolean hasFollower() {
		return player.getFamiliar() != null || player.getPet() != null;
	}
	
	
	public boolean endCon() {
		if (World.isMiningAreaBad(null) == false);
		player.getControlerManager().forceStop();
		return true;
	}

}
