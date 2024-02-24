package com.rs.game.player.content.raids.gulega;

import com.rs.game.WorldTile;
import com.rs.game.player.controlers.Controler;

public class GulegaWaitingC extends Controler {
	
	/**
	 * 
	 * Author Mr Joopz
	 * 
	 */
		

	private int team;

	@Override
	public void start() {
		team = (int) getArguments()[0];
		//sendInterfaces();
	}

	// You can't leave just like that!

	public void leave() {
		player.getPackets().closeInterface(
				player.getInterfaceManager().hasRezizableScreen() ?  34 : 0);
		GulegaWaiting.removeWaitingPlayer(player, team);
	}

	@Override
	public void sendInterfaces() {
		player.getInterfaceManager().sendTab(
				player.getInterfaceManager().hasRezizableScreen() ?  34 : 0, 57);
	}

	@Override
	public boolean processButtonClick(int interfaceId, int componentId,
			int slotId, int packetId) {
		return true;
	}

	@Override
	public boolean canEquip(int slotId, int itemId) {
		return true;
	}

	@Override
	public boolean sendDeath() {
		leave();
		removeControler();
		return true;
	}

	@Override
	public boolean logout() {
		player.setLocation(new WorldTile(GulegaWaiting.LOBBY, 0));
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
		leave();
		removeControler();
		player.getInventory().reset();
		player.getEquipment().reset();
			return true;
	}*/

	@Override
	public void magicTeleported(int type) {
		leave();
		removeControler();
	}

	@Override
	public void forceClose() {
		leave();
		removeControler();
	}
}
