package com.rs.game.player.controlers.heist;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.ForceTalk;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.minigames.Heist;
import com.rs.game.npc.NPC;
import com.rs.game.player.Equipment;
import com.rs.game.player.Inventory;
import com.rs.game.player.Player;
import com.rs.game.player.controlers.Controler;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;

public class HeistPlaying extends Controler {

	private int team;

	@Override
	public void start() {
		team = (int) getArguments()[0];
		sendInterfaces();
	}


	/*
	 * return process normaly
	 */
	@Override
	public boolean processButtonClick(int interfaceId, int componentId,
			int slotId, int packetId) {
		if (interfaceId == 387) {
			if (componentId == 9 || componentId == 6) {
				player.getPackets().sendGameMessage(
						"You can't remove your team's colours.");
				return false;
			}
			if (componentId == 15) {
				int weaponId = player.getEquipment().getWeaponId();
				if (weaponId == 23672) {
					player.getPackets().sendGameMessage(
							"You can't remove the cash bag.");
					return false;
				}
			}
		} else if (interfaceId == Inventory.INVENTORY_INTERFACE) {
			Item item = player.getInventory().getItem(slotId);
			if (item != null) {
				
			}
		}
		return true;
	}
	
	@Override
	public boolean canDropItem(Item item) {
		if (item.getDefinitions().getName().toLowerCase().contains("cash bag")) {
			player.getPackets().sendGameMessage("You cannot just drop a cash bag!");
			return false;
		}
		return true;
	}

	@Override
	public boolean canEquip(int slotId, int itemId) {
		if (slotId == Equipment.SLOT_CAPE || slotId == Equipment.SLOT_HAT) {
			player.getPackets().sendGameMessage(
					"You can't remove your team's colours.");
			return false;
		}
		if (slotId == Equipment.SLOT_WEAPON || slotId == Equipment.SLOT_SHIELD) {
			int weaponId = player.getEquipment().getWeaponId();
			if (weaponId == 23672) {
				player.getPackets().sendGameMessage(
						"You can't remove the cash bag.");
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean canAttack(Entity target) {
		if (target instanceof Player) {
			if (canHit(target))
				return true;
			player.getPackets().sendGameMessage("You can't attack your team.");
			return false;
		}
		return true;
	}



	@Override
	public boolean canHit(Entity target) {
		if (target instanceof NPC)
			return true;
		Player p2 = (Player) target;
		if (p2.getEquipment().getCapeId() == player.getEquipment().getCapeId())
			return false;
		return true;
	}

	// You can't leave just like that!

	public void leave() {
		player.getPackets().closeInterface(
				player.getInterfaceManager().hasRezizableScreen() ? 11 : 0);
		Heist.removePlayingPlayer(player, team);
	}

	@Override
	public void sendInterfaces() {
		player.getInterfaceManager().sendTab(
				player.getInterfaceManager().hasRezizableScreen() ?  11 : 0, 58);
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
					int weaponId = player.getEquipment().getWeaponId();
					if (weaponId == 23672) {
						Heist.setWeapon(player, null);
					} else {
						Player killer = player
								.getMostDamageReceivedSourcePlayer();
						if (killer != null)
							killer.increaseKillCount(player);
					}

					player.reset();
					player.setNextWorldTile(new WorldTile(
							team == Heist.ZAMORAK ? Heist.ZAMO_BASE
									: Heist.SARA_BASE, 0));
					player.setNextAnimation(new Animation(-1));
				} else if (loop == 4) {
					player.getPackets().sendMusicEffect(90);
					stop();
				}
				loop++;
			}
		}, 0, 1);
		return false;
	}

	@Override
	public boolean logout() {
		player.setLocation(new WorldTile(Heist.LOBBY, 0));
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

	@Override
	public boolean processObjectClick1(WorldObject object) {
		int id = object.getId();
		/*if (id == leaveid || id == leaveid) {
			removeControler();
			leave();
			return false;*/
			
	if (id == 24875) {
		if (player.getEquipment().hasWeapon() || player.getEquipment().hasShield()) {
			player.sendMessage("<col=ff0000> Your hands must be free to steal from the stack!");
			return false;
		}
		Heist.setWeapon(player, new Item(23672, 1));
		player.setNextForceTalk(new ForceTalk("I got the money bitchessssss!"));
		return true;//interface isnt updating
	}
	if (id == 2361) {
		if (player.getEquipment().getWeaponId() == 23672) {
			if (team != Heist.ZAMORAK) {
				player.sendMessage("This isn't your deposit chest.");
				return false;
			}
			//Heist.setWeapon(player, null);
			Heist.addScore(player, team, Heist.ZAMORAK);
			player.sendMessage("You have cashed in a bag for your team, well done!");
			player.heistcashbagsdeposit ++;
			return false;
		}
		return false;
	}
	if (id == 2191) {
		if (player.getEquipment().getWeaponId() == 23672) {
			if (team != Heist.SARADOMIN) {
				player.sendMessage("This isn't your deposit chest.");
				return false;
			}
			Heist.setWeapon(player, null);
			Heist.addScore(player, team, Heist.SARADOMIN);
			player.sendMessage("You have cashed in a bag for your team, well done!");
			player.heistcashbagsdeposit ++;
			return false;
		}
		
	}
			// under earth from basess
		
		return true;
	}

	@Override
	public boolean processObjectClick2(WorldObject object) {
		int id = object.getId();
		
		return true;
	}


	@Override
	public void magicTeleported(int type) {
		removeControler();
		leave();
	}

	@Override
	public void forceClose() {
		leave();
	}
}
