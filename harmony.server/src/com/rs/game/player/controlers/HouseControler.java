package com.rs.game.player.controlers;

import java.util.concurrent.TimeUnit;

import com.rs.cores.CoresManager;
import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.Graphics;
import com.rs.game.Hit;
import com.rs.game.Hit.HitLook;
import com.rs.game.NewForceMovement;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.npc.NPC;
import com.rs.game.npc.construction.ServantNPC;
import com.rs.game.player.Equipment;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.Bonfire;
import com.rs.game.player.actions.Cooking;
import com.rs.game.player.actions.Cooking.Cookables;
import com.rs.game.player.actions.SitBench;
import com.rs.game.player.actions.SitChair;
import com.rs.game.player.actions.SitThrone;
import com.rs.game.player.content.construction.House;
import com.rs.game.player.content.construction.HouseConstants;
import com.rs.game.player.content.construction.HouseConstants.Builds;
import com.rs.game.player.content.construction.HouseConstants.HObject;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.game.world.timers.OverloadTimer;
import com.rs.utils.Logger;
import com.rs.utils.Utils;

public class HouseControler extends Controler {

	public static boolean handleDoor(Player player, WorldObject object,
			long timer) {
		WorldObject openedDoor = new WorldObject(object.getId(),
				object.getType(), object.getRotation() + 1, object.getX(),
				object.getY(), object.getPlane());
		if (object.getId() == 13345) {
			openedDoor.setId(13344);
		} else if (object.getId() == 13344) {
			openedDoor.setId(13345);
			openedDoor.setRotation(object.getRotation() - 1);
		} else if (object.getId() == 13347) {
			openedDoor.setId(13346);
		} else if (object.getId() == 13346) {
			openedDoor.setId(13347);
			openedDoor.setRotation(object.getRotation() - 1);
		} else if (object.getId() == 13349) {
			openedDoor.setId(13348);
		} else if (object.getId() == 13348) {
			openedDoor.setId(13349);
			openedDoor.setRotation(object.getRotation() - 1);
		}
		/*
		 * if (object.getRotation() == 0) openedDoor.moveLocation(-1, 0, 0);
		 * else if (object.getRotation() == 1) openedDoor.moveLocation(0, 1, 0);
		 * else if (object.getRotation() == 2) openedDoor.moveLocation(1, 0, 0);
		 * else if (object.getRotation() == 3) openedDoor.moveLocation(0, -1,
		 * 0);
		 */
		if (World.removeTemporaryObject(object, timer, true)) {
			player.faceObject(openedDoor);
			World.spawnTemporaryObject(openedDoor, timer, true, object);
			return true;
		}
		return false;
	}

	// private HObject ring;

	private boolean insideCombatRing = false;
	private HObject ring;

	private House house;

	@Override
	public boolean canAttack(Entity target) {
		return true;
	}

	@Override
	public boolean canDropItem(Item item) {
		if (house.isBuildMode()) {
			player.getPackets().sendGameMessage(
					"You cannot drop items while in building mode.");
			return false;
		}
		return true;
	}

	@Override
	public boolean canEquip(int slotId, int itemId) {
		if (insideCombatRing) {
			if (ring == HouseConstants.HObject.BOXING_RING) {
				if (slotId == Equipment.SLOT_WEAPON) {
					if (itemId != 7671 && itemId != 7673) {
						player.sm("You can't use this weapon in here.");
						return false;
					}
				} else {
					player.sm("You can't this in here.");
					return false;
				}
			} else if (ring == HouseConstants.HObject.FENCING_RING) {
				if (slotId != Equipment.SLOT_WEAPON) {
					player.sm("You can't equip this in here.");
					return false;
				}
			}
		}
		return true;
	}
	
	@Override
	public boolean processNPCClick1(NPC npc) {
		if (npc instanceof ServantNPC) {
			npc.faceEntity(player);
			if (!house.isOwner(player)) {
				player.getDialogueManager().startDialogue("SimpleNPCMessage", npc.getId(), "Sorry, I only serve my master.");
				return false;
			}
			player.getDialogueManager().startDialogue("NewServantD", npc, false);
			return false;
		}
		return true;
	}

	@Override
	public boolean processNPCClick2(NPC npc) {
		if (npc instanceof ServantNPC) {
			npc.faceEntity(player);
			if (!house.isOwner(player)) {
				player.getDialogueManager().startDialogue("SimpleNPCMessage", npc.getId(), "The servant ignores your request.");
				return false;
			}
			if (player.isSuperDonator()) {
				player.getBank().openBank();
				return false;
			}
			player.getDialogueManager().startDialogue("NewServantD", npc, true);
			return false;
		}
		return true;
	}

	@Override
	public boolean processItemOnNPC(NPC npc, Item item) {
		if (npc instanceof ServantNPC) {
			npc.faceEntity(player);
			if (!house.isOwner(player)) {
				player.getDialogueManager().startDialogue("SimpleNPCMessage", npc.getId(), "The servant ignores your request.");
				return false;
			}
			player.getDialogueManager().startDialogue("ItemOnServantD", npc, item.getId(), house.getServant().isSawmill());
			return false;
		}
		return false;
	}

	@Override
	public boolean canHit(Entity entity) {
		return true;
	}

	private HObject checkRing(int objectId) {
		HObject[] rings = HouseConstants.Builds.COMBAT_RING.getPieces();
		for (HObject ring : rings) {
			for (int id : ring.getIds()) {
				if (id == objectId) {
					return ring;
				}
			}
		}
		return null;
	}

	@Override
	public boolean checkWalkStep(final int lastX, final int lastY, int nextX,
			int nextY) {
		WorldObject tile = World.getObject(new WorldTile(nextX, nextY, player.getPlane()), 22);
	//	WorldObject lastTile = World.getObject(new WorldTile(lastX, lastY, player.getPlane()), 22);
		if(tile != null) {
		if (tile.getId() == 13332 || tile.getId() == 13331) {
			player.getAppearence().setRenderEmote(152, true);
		}
		} else {
			//if(lastTile != null)
				player.getAppearence().setRenderEmote(-1, true);
		}
		return !house.isSky(nextX, nextY, player.getPlane());
	}

	// shouldnt happen
	@Override
	public void forceClose() {
		player.setLocation(2545, 3094, 0);
		house.leaveHouse(player, House.LOGGED_OUT);
	}

	public House getHouse() {
		return house;
	}

	@Override
	public boolean processItemOnObject(WorldObject object, Item item) {
		/*if (object.getId() >= HouseConstants.HObject.OAK_PET_HOUSE.getId() && object.getId() <= HouseConstants.HObject.NATURAL_PET_HOUSE.getId()) {
			if (Pets.forId(item.getId()) != null) {
				player.getDialogueManager().startDialogue("StorePet", house, StorePet.ITEM, item.getId());
			} else
				player.sm("That is not a pet...");
			return false;
		}*/
		if (object.getId() == HouseConstants.HObject.CLAY_FIREPLACE.getId()
				|| object.getId() == HouseConstants.HObject.STONE_FIREPLACE
						.getId()
				|| object.getId() == HouseConstants.HObject.MARBLE_FIREPLACE
						.getId()) {
			if (item.getId() != 1511) {
				player.getPackets()
						.sendGameMessage(
								"Only ordinary logs can be used to light the fireplace.");
				return false;
			}
			if (!player.getInventory().containsItem(590, 1)) {
				player.getPackets().sendGameMessage(
						"You do not have the required items to light this.");
				return false;
			}
			player.lock(2);
			player.setNextAnimation(new Animation(3658));
			player.getSkills().addXp(Skills.FIREMAKING, 40);
			WorldObject objectR = new WorldObject(object);
			objectR.setId(object.getId() + 1);
			// wiki says: If you light a fire in your fireplace, then change the
			// graphic settings, the fire will disappear meaning its not realy
			// spawned
			for (Player player : house.getPlayers()) {
				player.getPackets().sendSpawnedObject(objectR);
			}
			return false;
		} else if (HouseConstants.Builds.STOVE.containsObject(object)) {
			Cookables cook = Cooking.isCookingSkill(item);
			if (cook != null) {
				player.getDialogueManager().startDialogue("CookingD", cook,
						object);
				return false;
			}
			player.getDialogueManager()
					.startDialogue(
							"SimpleMessage",
							"You can't cook that on a "
									+ (object.getDefinitions().name
											.equals("Fire") ? "fire" : "range")
									+ ".");
			return false;
		}
		return true;
	}

	// shouldnt happen but lets imagine somehow in a server restart
	@Override
	public boolean login() {
		player.setNextWorldTile(new WorldTile(2545, 3094, 0));
		removeControler();
		return false; // remove controller manualy since i dont want to call
						// forceclose
	}

	@Override
	public boolean logout() {
		player.setLocation(2545, 3094, 0);
		house.leaveHouse(player, House.LOGGED_OUT);
		return false; // leave house method removes controller already
	}

	@Override
	public void magicTeleported(int type) {
		player.sendMessage("Please leave your house via the portal first!");
		return;

	}


	@Override
	public void process() {
		if (!insideCombatRing) {
			if (player.getPlane() == 0 && !player.isCanPvp()) {
				player.setCanPvp(true);
				player.setForceMultiArea(true);
			} else if (player.getPlane() != 0 && player.isCanPvp()) {
				player.setCanPvp(false);
				player.setForceMultiArea(false);
			}
		}
		final WorldObject trap = World.getObject(new WorldTile(player.getX(),
				player.getY(), player.getPlane()), 22);
		if (trap != null) { // 75
			// player.addWalkSteps(trap.getX(), trap.getY(), 100, false);
			// if(Utils.random(player.getSkills().getLevel(17)) >= 40) {
			if (trap.getId() == 13361) {
				player.setNextGraphics(new Graphics(615));
				player.applyHit(new Hit(player, Utils.random(100),
						HitLook.MELEE_DAMAGE));
				player.resetWalkSteps();
				World.removeObject(trap);
				CoresManager.slowExecutor.schedule(new Runnable() {
					@Override
					public void run() {
						try {
							World.spawnObject(trap);
						} catch (Throwable e) {
							Logger.handle(e);
						}
					}

				}, 5000, TimeUnit.MILLISECONDS);
				// return false;
			} else if (trap.getId() == 13362) {
				player.setNextGraphics(new Graphics(616));
				player.applyHit(new Hit(player, Utils.random(200),
						HitLook.MELEE_DAMAGE));
				player.resetWalkSteps();
				World.removeObject(trap);
				CoresManager.slowExecutor.schedule(new Runnable() {
					@Override
					public void run() {
						try {
							World.spawnObject(trap);
						} catch (Throwable e) {
							Logger.handle(e);
						}
					}

				}, 5000, TimeUnit.MILLISECONDS);
				// return false;
			} else if (trap.getId() == 13363) {
				player.setNextGraphics(new Graphics(617));
				player.setNextGraphics(new Graphics(618));
				player.resetWalkSteps();
				player.lock(5);
				World.removeObject(trap);
				CoresManager.slowExecutor.schedule(new Runnable() {
					@Override
					public void run() {
						try {
							World.spawnObject(trap);
						} catch (Throwable e) {
							Logger.handle(e);
						}
					}

				}, 5000, TimeUnit.MILLISECONDS);
				// return false;
			} else if (trap.getId() == 13364) {
				player.getSkills().drainLevel(17, 20);
				player.setNextGraphics(new Graphics(620));
				player.resetWalkSteps();
				World.removeObject(trap);
				CoresManager.slowExecutor.schedule(new Runnable() {
					@Override
					public void run() {
						try {
							World.spawnObject(trap);
						} catch (Throwable e) {
							Logger.handle(e);
						}
					}

				}, 5000, TimeUnit.MILLISECONDS);
				// return false;
			} else if (trap.getId() == 13365) {
				player.setNextGraphics(new Graphics(621));
				player.resetWalkSteps();
				World.removeObject(trap);
				CoresManager.slowExecutor.schedule(new Runnable() {
					@Override
					public void run() {
						try {
							World.spawnObject(trap);
						} catch (Throwable e) {
							Logger.handle(e);
						}
					}

				}, 5000, TimeUnit.MILLISECONDS);
				// return false;
				// }
			} else if(trap.getId() == 13685) {
				
			}
		}
	}


	@Override
	public boolean processObjectClick1(final WorldObject object) {
		if (object.getId() == HouseConstants.HObject.EXIT_PORTAL.getId()) {
			house.leaveHouse(player, House.KICKED);
		} else if (HouseConstants.Builds.LEVER.containsObject(object)) {
				if (!house.isOwner(player)) {
					player.getPackets().sendGameMessage("You can only do that in your own house.");
					return false;
				} else if (house.isBuildMode()) {
					player.getPackets().sendGameMessage("You cannot do this while in building mode.");
					return false;
				}
				house.pullLeverChallengeMode(object);
			 }
		else if (object.getId() >= HouseConstants.HObject.OAK_LECTURN.getId() && object.getId() <= HouseConstants.HObject.MAHOGANY_DEMON_LECTURN.getId()) {
			player.getInterfaceManager().sendInterface(400);
		}
		/*else if (object.getId() >= HouseConstants.HObject.OAK_PET_HOUSE.getId() && object.getId() <= HouseConstants.HObject.NATURAL_PET_HOUSE.getId()) {
			player.getDialogueManager().startDialogue("StorePet", house, StorePet.OBJECT, -1);
		}*/else if (object.getId() == HouseConstants.HObject.WOODEN_LARDER.getId()) {
			player.getTemporaryAttributtes().put("LarderType", 1);
			player.getDialogueManager().startDialogue("HouseLarder");
		}
		else if (object.getId() == HouseConstants.HObject.OAK_LARDER.getId()) {
			player.getTemporaryAttributtes().put("LarderType", 2);
			player.getDialogueManager().startDialogue("HouseLarder");
		} else if (HouseConstants.Builds.TRAPDOOR.containsObject(object)) {
			house.climbTrapdoor(player, object, false);
		} else if (HouseConstants.Builds.LADDER.containsObject(object)) {
			house.climbTrapdoor(player, object, true);
		}
		else if (object.getId() == HouseConstants.HObject.TEAK_LARDER.getId()) {
			player.getTemporaryAttributtes().put("LarderType", 3);
			player.getDialogueManager().startDialogue("HouseLarder");
		}
		else if (object.getId() == HouseConstants.HObject.WOODEN_SHELVES_1.getId()) {
			player.getTemporaryAttributtes().put("ShelfType", 1);
			player.getDialogueManager().startDialogue("HouseShelves");
		}
		else if (object.getId() == HouseConstants.HObject.WOODEN_SHELVES_2.getId()) {
			player.getTemporaryAttributtes().put("ShelfType", 2);
			player.getDialogueManager().startDialogue("HouseShelves");
		}
		else if (object.getId() == HouseConstants.HObject.WOODEN_SHELVES_3.getId()) {
			player.getTemporaryAttributtes().put("ShelfType", 3);
			player.getDialogueManager().startDialogue("HouseShelves");
		}
		else if (object.getId() == HouseConstants.HObject.OAK_SHELVES_1.getId()) {
			player.getTemporaryAttributtes().put("ShelfType", 4);
			player.getDialogueManager().startDialogue("HouseShelves");
		}
		else if (object.getId() == HouseConstants.HObject.OAK_SHELVES_2.getId()) {
			player.getTemporaryAttributtes().put("ShelfType", 5);
			player.getDialogueManager().startDialogue("HouseShelves");
		}
		else if (object.getId() == HouseConstants.HObject.TEAK_SHELVES_1.getId()) {
			player.getTemporaryAttributtes().put("ShelfType", 6);
			player.getDialogueManager().startDialogue("HouseShelves");
		}
		else if (object.getId() == HouseConstants.HObject.TEAK_SHELVES_2.getId()) {
			player.getTemporaryAttributtes().put("ShelfType", 7);
			player.getDialogueManager().startDialogue("HouseShelves");
		}
		else if (object.getId() == HouseConstants.HObject.BANK_CHEST.getId()) {
			player.getBank().openBank();
		}
		else if (object.getId() == HouseConstants.HObject.ORNATE_POOL.getId()) {
			player.heal(player.getMaxHitpoints());
			player.getPoison().reset();
			player.getCombatDefinitions().resetSpecialAttack();
			player.getPrayer().restorePrayer(990);
			if (player.isExtremeDonator()) {
				OverloadTimer.addTime(player);
				player.sendMessage("You have been restored, cured & overkilled!");
				if (player.potionperk == true) {
					player.setOverkillDelay(1002);
				} else {
					player.setOverkillDelay(501);
				}
			} else {
			OverloadTimer.addTime(player);
				player.sendMessage("You have been restored, cured & overloaded!");
			if (player.potionperk == true) {
				player.setOverloadDelay(1002);
			} else {
				player.setOverloadDelay(501);
			}

			}
			player.setNextAnimation(new Animation(3170));
			player.setNextGraphics(new Graphics(560));
		}
		else if (object.getId() >= HouseConstants.HObject.INSCENCE_BURNER.getId() && object.getId() <= HouseConstants.HObject.MARBLE_BURNER.getId()) {
			if (player.getInventory().containsItem(590, 1) && player.getInventory().containsItem(251, 1)) {
			WorldTasksManager.schedule(new WorldTask() {
				int loop;
				@Override
				public void run() {
					if (house == null || house.getPlayers().size() == 0) {
						stop();
						return;
					}
					if (loop == 0) {
						player.lock(1);
						player.sm("You attempt to light the "+object.getDefinitions().name.toLowerCase()+"...");
						player.setNextAnimation(new Animation(3687));
					} else if (loop == 1) {
						player.getInventory().deleteItem(251, 1);
						house.litBurners++;
						World.removeObject(new WorldObject(object.getId(), object.getType(), object.getRotation(), object.getX(), object.getY(), object.getPlane()));
						World.spawnObject(new WorldObject(object.getId() + 1, object.getType(), object.getRotation(), object.getX(), object.getY(), object.getPlane()));
						player.sm("It catches alight.");
					} else if (loop == 130){
						house.litBurners--;
						if (house.litBurners < 0) {
							house.litBurners = 0;
						}
						
						World.removeObject(new WorldObject(object.getId() + 1, object.getType(), object.getRotation(), object.getX(), object.getY(), object.getPlane()));
						World.spawnObject(new WorldObject(object.getId(), object.getType(), object.getRotation(), object.getX(), object.getY(), object.getPlane()));
						stop();
					}
					loop++;
					
				}
			}, 0, 1);
			} else {
				player.sm("You need a tinderbox and a clean marrentill to light this.");
			}
		}
		else if (object.getId() >= HouseConstants.HObject.WOODEN_TORCHES.getId() && object.getId() <= HouseConstants.HObject.GOLD_CANDLESTICK.getId()) {
			if (player.getInventory().containsItem(590, 1)) {
			WorldTasksManager.schedule(new WorldTask() {
				int loop;
				@Override
				public void run() {
					if (house == null || house.getPlayers().size() == 0) {
						stop();
						return;
					}
					if (loop == 0) {
						player.lock(1);
						player.sm("You attempt to light the "+object.getDefinitions().name.toLowerCase()+"...");
						player.setNextAnimation(new Animation(3687));
					} else if (loop == 1) {
						World.removeObject(new WorldObject(object.getId(), object.getType(), object.getRotation(), object.getX(), object.getY(), object.getPlane()));
						World.spawnObject(new WorldObject(object.getId() + 1, object.getType(), object.getRotation(), object.getX(), object.getY(), object.getPlane()));
						player.sm("It catches alight.");
					} else if (loop == 130){
						World.removeObject(new WorldObject(object.getId() + 1, object.getType(), object.getRotation(), object.getX(), object.getY(), object.getPlane()));
						World.spawnObject(new WorldObject(object.getId(), object.getType(), object.getRotation(), object.getX(), object.getY(), object.getPlane()));
						stop();
					}
					loop++;
					
				}
			}, 0, 1);
			} else {
				player.sm("You need a tinderbox to light this.");
			}
		}
		else if (object.getId() >= HouseConstants.HObject.CLAY_FIREPLACE.getId() && object.getId() <= HouseConstants.HObject.MARBLE_FIREPLACE.getId()) {
			if (player.getInventory().containsItem(590, 1) && player.getInventory().containsItem(1511, 1)) {
				WorldTasksManager.schedule(new WorldTask() {
					int loop;
					@Override
					public void run() {
						if (house == null || house.getPlayers().size() == 0) {
							stop();
							return;
						}
						if (loop == 0) {
							player.lock(1);
							player.sm("You attempt to light the "+object.getDefinitions().name.toLowerCase()+"...");
							player.setNextAnimation(new Animation(3658));
						} else if (loop == 1) {
							player.getInventory().deleteItem(1511, 1);
							World.removeObject(new WorldObject(object.getId(), object.getType(), object.getRotation(), object.getX(), object.getY(), object.getPlane()));
							World.spawnObject(new WorldObject(object.getId() + 1, object.getType(), object.getRotation(), object.getX(), object.getY(), object.getPlane()));
							player.sm("It catches alight.");
							stop();
						}
						loop++;
						
					}
				}, 0, 1);
				} else {
					player.sm("You need a tinderbox and some logs to light this.");
				}
		}
		else if (object.getId() >= HouseConstants.HObject.OAK_ALTAR.getId() && object.getId() <= HouseConstants.HObject.GILDED_ALTAR.getId()){
			if (object.getDefinitions().containsOption(0, "Pray") || object.getDefinitions().containsOption(0, "Pray-at")) {
				final int maxPrayer = player.getPrayer().maxPrayerPoints();
				if (player.getPrayer().getPrayerpoints() < maxPrayer) {
					player.lock(5);
					player.getPackets().sendGameMessage(
							"You pray to the gods...", true);
					player.setNextAnimation(new Animation(645));
					WorldTasksManager.schedule(new WorldTask() {
						@Override
						public void run() {
							player.getPrayer().restorePrayer(
									maxPrayer);
							player.getPackets()
							.sendGameMessage(
									"...and recharged your prayer.",
									true);
						}
					}, 2);
				} else {
					player.getPackets().sendGameMessage(
							"You already have full prayer.");
				}
			}
		}
		else if (object.getId() == HouseConstants.HObject.MINIOBELISK.getId()) {
			if (object.getDefinitions().containsOption(0, "Renew-points")) {
				int summonLevel = player.getSkills().getLevelForXp(Skills.SUMMONING);
				if(player.getSkills().getLevel(Skills.SUMMONING) < summonLevel) {
					player.lock(3);
					player.setNextAnimation(new Animation(8502));
					player.getSkills().set(Skills.SUMMONING, summonLevel);
					player.getPackets().sendGameMessage(
							"You have recharged your Summoning points.", true);
				} else {
					player.getPackets().sendGameMessage("You already have full Summoning points.");
				}
			}
		}
		else if (object.getId() == HouseConstants.HObject.GLORY.getId()) {
			player.getDialogueManager().startDialogue("Transportation",
					"Edgeville", new WorldTile(3087, 3496, 0),
					"Karamja", new WorldTile(2918, 3176, 0),
					"Draynor Village", new WorldTile(3105, 3251, 0),
					"Al Kharid", new WorldTile(3293, 3163, 0), null);
		}
		else if (object.getId() >= HouseConstants.HObject.CRUDE_WOODEN_CHAIR
				.getId()
				&& object.getId() <= HouseConstants.HObject.MAHOGANY_ARMCHAIR
						.getId()) {
			int chair = object.getId()
					- HouseConstants.HObject.CRUDE_WOODEN_CHAIR.getId();
			player.getActionManager().setAction(
					new SitChair(player, chair, object));
		} else if (object.getId() >= HouseConstants.HObject.OAK_THRONE.getId() && object.getId() <= HouseConstants.HObject.DEMONIC_THRONE.getId()) {
			int throne = object.getId()
					- HouseConstants.HObject.OAK_THRONE.getId();
			player.getActionManager().setAction(
					new SitThrone(player, throne, object));
		} else if (object.getId() >= HouseConstants.HObject.WOOD_BENCH.getId() && object.getId() <= HouseConstants.HObject.GILDED_BENCH.getId()) {
			int bench = object.getId()
					- HouseConstants.HObject.WOOD_BENCH.getId();
			player.getActionManager().setAction(
					new SitBench(player, bench, object));
		} else if (object.getId() == HouseConstants.HObject.SCRYING_POOL.getId()) {//as a test
			player.getDialogueManager().startDialogue("CreatePortal");
		} else if (HouseConstants.Builds.BOOKCASE.containsObject(object)) {
			player.getPackets().sendGameMessage(
					"You search the bookcase but find nothing.");
		} else if (HouseConstants.Builds.STAIRCASE.containsObject(object)
				|| HouseConstants.Builds.STAIRCASE_DOWN.containsObject(object)) {
			if (object.getDefinitions().getOption(1).equals("Climb")) {
				player.getDialogueManager().startDialogue("ClimbHouseStairD",
						object);
			} else {
				house.climbStaircase(
						object,
						object.getDefinitions().getOption(1).equals("Climb-up"),
						player);
			}
		} else if (object.getId() == HouseConstants.HObject.DUNGEON_ENTRACE
				.getId()) {
			house.climbStaircase(object, object.getDefinitions().getOption(1)
					.equals("Climb-down"), player);
		} else if (object.getId() == HouseConstants.HObject.OAK_DOOR.getIds()[0]
				|| object.getId() == HouseConstants.HObject.OAK_DOOR.getIds()[1]) {
			player.sm("You attempt to open the door...");
			WorldTasksManager.schedule(new WorldTask() {
				@Override
				public void run() {
					stop();
					if (Utils.random(5) == 1) {
						player.sm("You manage to pry the door open for a moment. Quick! Get through!");
						handleDoor(player, object, 3000);
					} else {
						player.sm("The door is locked, you couldnt get it open.");
					}
				}
			}, 0, 2);
		} else if (object.getId() == HouseConstants.HObject.STEEL_DOOR.getIds()[0]
				|| object.getId() == HouseConstants.HObject.STEEL_DOOR.getIds()[1]) {
			player.sm("You attempt to open the door...");
			WorldTasksManager.schedule(new WorldTask() {
				@Override
				public void run() {
					stop();
					if (Utils.random(10) == 1) {
						player.sm("You manage to pry the door open for a moment. Quick! Get through!");
						handleDoor(player, object, 3000);
					} else {
						player.sm("The door is locked, you couldnt get it open.");
					}
				}
			}, 0, 2);
		} else if (object.getId() == HouseConstants.HObject.MARBLE_DOOR
				.getIds()[0]
				|| object.getId() == HouseConstants.HObject.MARBLE_DOOR
						.getIds()[1]) {
			player.sm("You attempt to open the door...");
			WorldTasksManager.schedule(new WorldTask() {
				@Override
				public void run() {
					stop();
					if (Utils.random(15) == 1) {
						player.sm("You manage to pry the door open for a moment. Quick! Get through!");
						handleDoor(player, object, 3000);
					} else {
						player.sm("The door is locked, you couldnt get it open.");
					}
				}
			}, 0, 2);
		} else if (HouseConstants.Builds.WEAPONS_RACK.containsObject(object)) {
			player.getDialogueManager().startDialogue("WeaponsRack");
		} else if (HouseConstants.Builds.COMBAT_RING.containsObject(object)) {
			if (checkRing(object.getId()) == HouseConstants.HObject.BOXING_RING) {
				if (player.getEquipment().wearingArmourNoWeapon()) {
					player.sm("Please take all your armour off before entering this ring.");
					return false;
				} else if (player.getEquipment().hasWeapon()
						&& !(player.getEquipment()
								.getItem(Equipment.SLOT_WEAPON).getId() == 7671 || player
								.getEquipment().getItem(Equipment.SLOT_WEAPON)
								.getId() == 7673)) {
					player.sm("Please remove any weapons, boxing gloves are allowed.");
					return false;
				}
			} else if (checkRing(object.getId()) == HouseConstants.HObject.FENCING_RING) {
				if (player.getEquipment().wearingArmourNoWeapon()) {
					player.sm("Please take all your armour off before entering this ring.");
					return false;
				}
			}
			player.faceObject(object);
			// WorldTile array
			final WorldTile[] tile = new WorldTile[] {
					insideCombatRing ? new WorldTile(player.getX() + 1,
							player.getY(), player.getPlane())
							: new WorldTile(player.getX() - 1, player.getY(),
									player.getPlane()),
					insideCombatRing ? new WorldTile(player.getX(),
							player.getY() - 1, player.getPlane())
							: new WorldTile(player.getX(), player.getY() + 1,
									player.getPlane()),
					insideCombatRing ? new WorldTile(player.getX() - 1,
							player.getY(), player.getPlane())
							: new WorldTile(player.getX() + 1, player.getY(),
									player.getPlane()),
					insideCombatRing ? new WorldTile(player.getX(),
							player.getY() + 1, player.getPlane())
							: new WorldTile(player.getX(), player.getY() - 1,
									player.getPlane()) };

			if (World.getObject(tile[object.getRotation()], 22) == null
					&& !insideCombatRing) {
				player.sm("You can't get in from here.");
				return false;
			}
			player.lock();
			player.setNextFaceWorldTile(tile[object.getRotation()]);
			WorldTasksManager.schedule(new WorldTask() {
				private int stage;

				@Override
				public void run() {
					if (stage == 0) {
						player.setNextAnimation(new Animation(839));
						player.setNextForceMovement(new NewForceMovement(
								player, 1, tile[object.getRotation()], 2,
								player.getFaceWorldTile(tile[object
										.getRotation()])));
					} else if (stage == 2) {
						player.setNextWorldTile(tile[object.getRotation()]);
						ring = checkRing(object.getId());
						insideCombatRing = !insideCombatRing;
						player.setCanPvp(insideCombatRing);
						player.setForceMultiArea(insideCombatRing);
						stop();
						player.unlock();
					}
					stage++;
				}
			}, 0, 0);
		}
		return false;
	}

	@Override
	public boolean processObjectClick2(final WorldObject object) {
		if (object.getId() == HouseConstants.HObject.EXIT_PORTAL.getId()) {
			house.switchLock(player);
		} else if (HouseConstants.Builds.STAIRCASE.containsObject(object)
				|| HouseConstants.Builds.STAIRCASE_DOWN.containsObject(object)) {
			house.climbStaircase(object, true, player);
		} else if (object.getId() == HouseConstants.HObject.OAK_DOOR.getIds()[0]
				|| object.getId() == HouseConstants.HObject.OAK_DOOR.getIds()[1]) {
			player.sm("You pick lock the door");
			WorldTasksManager.schedule(new WorldTask() {
				@Override
				public void run() {
					stop();
					if (player.getSkills().getLevel(17) >= 55
							&& Utils.random(1) == 0) {
						player.sm("The locks seem to slip, and the door opens for a moment. Quick! Get through!");
						handleDoor(player, object, 5000);
					} else {
						player.sm("The locks built into the door are too strong. You failed opening them.");
					}
				}
			}, 0, 2);
		} else if (object.getId() == HouseConstants.HObject.STEEL_DOOR.getIds()[0]
				|| object.getId() == HouseConstants.HObject.STEEL_DOOR.getIds()[1]) {
			player.sm("You attempt to open the door...");
			WorldTasksManager.schedule(new WorldTask() {
				@Override
				public void run() {
					stop();
					if (player.getSkills().getLevel(17) >= 65
							&& Utils.random(1) == 0) {
						player.sm("The locks seem to slip, and the door opens for a moment. Quick! Get through!");
						handleDoor(player, object, 5000);
					} else {
						player.sm("The locks built into the door are too strong. You failed opening them.");
					}
				}
			}, 0, 2);
		} else if (object.getId() == HouseConstants.HObject.MARBLE_DOOR
				.getIds()[0]
				|| object.getId() == HouseConstants.HObject.MARBLE_DOOR
						.getIds()[1]) {
			player.sm("You attempt to open the door...");
			WorldTasksManager.schedule(new WorldTask() {
				@Override
				public void run() {
					stop();
					if (player.getSkills().getLevel(17) >= 75
							&& Utils.random(1) == 0) {
						player.sm("The locks seem to slip, and the door opens for a moment. Quick! Get through!");
						handleDoor(player, object, 5000);
					} else {
						player.sm("The locks built into the door are too strong. You failed opening them.");
					}
				}
			}, 0, 2);
		}
		return false;
		
	}
	

	@Override
	public boolean processObjectClick3(final WorldObject object) {
		if (HouseConstants.Builds.STAIRCASE.containsObject(object)
				|| HouseConstants.Builds.STAIRCASE_DOWN.containsObject(object)) {
			house.climbStaircase(object, false, player);
		} else if (object.getId() == HouseConstants.HObject.OAK_DOOR.getIds()[0]
				|| object.getId() == HouseConstants.HObject.OAK_DOOR.getIds()[1]) {
			player.sm("You pick lock the door");
			WorldTasksManager.schedule(new WorldTask() {
				@Override
				public void run() {
					stop();
					if (player.getSkills().getLevel(2) >= 55
							&& Utils.random(1) == 0) {
						player.sm("You use your strength to smash through the locks. Quick! Get through!");
						handleDoor(player, object, 5000);
					} else {
						player.sm("The locks built into the door are too strong for you to break through. You failed opening them.");
					}
				}
			}, 0, 2);
		} else if (object.getId() == HouseConstants.HObject.STEEL_DOOR.getIds()[0]
				|| object.getId() == HouseConstants.HObject.STEEL_DOOR.getIds()[1]) {
			player.sm("You attempt to open the door...");
			WorldTasksManager.schedule(new WorldTask() {
				@Override
				public void run() {
					stop();
					if (player.getSkills().getLevel(2) >= 65
							&& Utils.random(1) == 0) {
						player.sm("You use your strength to smash through the locks. Quick! Get through!");
						handleDoor(player, object, 5000);
					} else {
						player.sm("The locks built into the door are too strong for you to break through. You failed opening them.");
					}
				}
			}, 0, 2);
		} else if (object.getId() == HouseConstants.HObject.MARBLE_DOOR
				.getIds()[0]
				|| object.getId() == HouseConstants.HObject.MARBLE_DOOR
						.getIds()[1]) {
			player.sm("You attempt to open the door...");
			WorldTasksManager.schedule(new WorldTask() {
				@Override
				public void run() {
					stop();
					if (player.getSkills().getLevel(2) >= 75
							&& Utils.random(1) == 0) {
						player.sm("You use your strength to smash through the locks. Quick! Get through!");
						handleDoor(player, object, 5000);
					} else {
						player.sm("The locks built into the door are too strong for you to break through. You failed opening them.");
					}
				}
			}, 0, 2);
		}
		return false;
	}

	@Override
	public boolean processObjectClick4(WorldObject object) {
		if (HouseConstants.Builds.STAIRCASE.containsObject(object)
				|| HouseConstants.Builds.STAIRCASE_DOWN.containsObject(object)
				|| object.getId() == HouseConstants.HObject.DUNGEON_ENTRACE
						.getId()) {
			house.removeRoom();
		}
		return false;
	}

	/**
	 * return process normaly
	 */
	@Override
	public boolean processObjectClick5(WorldObject object) {
		if (object.getDefinitions().containsOption(4, "Build")) {
			if (!house.isOwner(player)) {
				player.getPackets().sendGameMessage(
						"You can only do that in your own house.");
				return false;
			}
			if (house.isDoor(object)) {
				house.openRoomCreationMenu(object);
			} else {
				for (Builds build : HouseConstants.Builds.values()) {
					if (build.containsId(object.getId())) {
						house.openBuildInterface(object, build);
						return false;
					}
				}
			}
		} else if (object.getDefinitions().containsOption(4, "Remove")) {
			if (!house.isOwner(player)) {
				player.getPackets().sendGameMessage(
						"You can only do that in your own house.");
				return false;
			} else if (insideCombatRing) {
				player.sm("You can only do this outside of a combat ring.");
				return false;
			}
			player.faceObject(object);
			house.openRemoveBuild(object);
		} else if (object.getDefinitions().containsOption(4, "Add-logs")) {
			Bonfire.addLogs(player, object);
		}
		return false;
	}

	@Override
	public boolean sendDeath() {
		player.lock(7);
		player.stopAll();
		WorldTasksManager.schedule(new WorldTask() {
			int loop;

			@Override
			public void run() {
				if (loop == 0) {
					player.setNextAnimation(new Animation(836));
				} else if (loop == 1) {
					player.getPackets().sendGameMessage(
							"You have been defeated!");
				} else if (loop == 3) {
					insideCombatRing = false;
					player.reset();
					house.teleportPlayer(player);
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
	public void start() {
		house = (House) getArguments()[0];
		getArguments()[0] = null; // its was gonna be saved unless somehow in a
									// server restart but lets be safe
		//if (player.getHouse() == null)
		//	this.house = (House) getArguments()[0];
		//	getArguments()[0] = null;
	}

}