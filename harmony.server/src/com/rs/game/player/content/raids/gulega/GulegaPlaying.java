package com.rs.game.player.content.raids.gulega;

import java.util.List;
import java.util.TimerTask;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.npc.NPC;
import com.rs.game.player.controlers.Controler;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;

public class GulegaPlaying extends Controler {
	
	/**
	 * 
	 * Author Mr Joopz
	 * 
	 */
		

	private int team;
	public boolean spawned;

	@Override
	public void start() {
		//System.out.println("d");
		team = (int) getArguments()[0];
	}

	/*
	 * return process normaly
	 */
	@Override
	public boolean processButtonClick(int interfaceId, int componentId,
			int slotId, int packetId) {
		return true;
	}
	
	@Override
	public boolean canDropItem(Item item) {
		return true;
	}

	@Override
	public boolean canEquip(int slotId, int itemId) {
		return true;
	}

	@Override
	public boolean canAttack(Entity target) {
		return true;
	}

	@Override
	public boolean processItemOnNPC(NPC npc, Item item) {
		return true;
	}

	@Override
	public boolean canHit(Entity target) {
		return true;
	}

	// You can't leave just like that!

	public void leave() {
		player.getPackets().closeInterface(
				player.getInterfaceManager().hasRezizableScreen() ? 11 : 0);
		GulegaWaiting.removePlayingPlayer(player, team);
	}

	@Override
	public boolean sendDeath() {
		WorldTasksManager.schedule(new WorldTask() {
			int loop;

			@Override
			public void run() {
				if (loop == 0) {
					player.setNextAnimation(new Animation(836));
				} else if (loop == 1) {
					player.getPackets().sendGameMessage(
							"Oh dear, you have died.");
				} else if (loop == 3) {
					}
					player.setNextWorldTile(new WorldTile(
							team == GulegaWaiting.TRIO1 ? GulegaWaiting.LOBBY
									: GulegaWaiting.LOBBY, 1));
					player.setNextAnimation(new Animation(-1));
					removeControler();
					player.reset();
					leave();
					stop();
					loop++;
			}
			
		}, 0, 1);
		return false;
	}

	@Override
	public boolean logout() {
		player.setLocation(new WorldTile(GulegaWaiting.LOBBY, 0));
		leave();
		return true;
	}

	@Override
	public boolean processMagicTeleport(WorldTile toTile) {
		player.getDialogueManager().startDialogue("SimpleMessage",
				"You can't leave just like that!");
		return false;
	}

	@Override
	public boolean processItemTeleport(WorldTile toTile) {
		player.getDialogueManager().startDialogue("SimpleMessage",
				"You can't leave just like that!");
		return false;
	}

	@Override
	public boolean processObjectTeleport(WorldTile toTile) {
		player.getDialogueManager().startDialogue("SimpleMessage",
				"You can't leave just like that!");
		return false;
	}

	/*@Override
	public boolean processObjectClick1(WorldObject object) {
		int id = object.getId();
			removeControler();
			leave();
			player.getInventory().reset();
			player.getEquipment().reset();
			player.calamitykillpoints = 0;
			return true;
	}

	@Override
	public boolean processObjectClick2(WorldObject object) {
		int id = object.getId();
		return true;
	}*/

	@Override
	public void magicTeleported(int type) {
		removeControler();
		leave();
	}

	@Override
	public void forceClose() {
		leave();
		removeControler();
	}
}
