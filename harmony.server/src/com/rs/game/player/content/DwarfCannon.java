package com.rs.game.player.content;

import java.io.Serializable;

import com.rs.game.Animation;
import com.rs.game.Hit;
import com.rs.game.Hit.HitLook;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.npc.NPC;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Colors;
import com.rs.utils.Utils;


public class DwarfCannon implements Serializable {

	private static final long serialVersionUID = 3173899548160709203L;

	public int[] CANNON = { 6, 7, 8, 9 };
	public int[] ITEMS = { 6, 8, 10, 12, 2 };
	public int[] GOLD_CANNON = { 29406, 29398, 29401, 29402 };
	public int[] GOLD_ITEMS = { 20494, 20495, 20496, 20497, 2 };
	public int[] ROYAL_CANNON = { 29408, 29403, 29404, 29405 };
	public int[] ROYAL_ITEMS = { 20498, 20499, 20500, 20501, 2 };
	private WorldObject lastObject;
	private int cannonBalls = 0;
	private boolean first = true;
	private Player player;
	private boolean hasCannon = false;
	private boolean isFiring = false;
	private int cannonDirection;
	private boolean firstFire = true;
	private boolean loadedOnce = false;
	private boolean rotating;
	private String owner;
	private boolean settingUp = false;
	private WorldObject object;

	/**
	 * Initializes the player variable
	 *
	 * @param player
	 */
	public DwarfCannon(Player player) {
		this.setPlayer(player);
	}


	/**
	 * Checks the players location.
	 */

	public static boolean isAtHome(WorldTile tile) {
		return (tile.getX() >= 5437 && tile.getX() <= 5566 && tile.getY() <= 4480 && tile.getY() >= 4350);
	}
	public void checkLocation() {
		if (player.getRights() >= 15) {
			cannonSetup();
		}
		if (World.CatacombsOSRS(player) && player.dailydwarfcatacombfee != true) {
			player.sendMessage(Colors.red + "You must pay a daily fee of 5 million coins to use this here! Click on the Altar to pay your fee.");
			return;
		}
		if (World.isNoCannonArea(getPlayer()) || World.isPvpArea(getPlayer())) {
			getPlayer().sendMessage("You cannot setup a cannon here");
			return;
		} else if (!World.isMultiArea(getPlayer())) {
			getPlayer().sendMessage("You can only setup a cannon in a multi area.");
			return;
			//} else if (player.completedDwarfCannonQuest == true) {
			//	cannonSetup();
		} else {
			cannonSetup();
			getPlayer().sendMessage("Do not place the cannon in the same spot as previous, it will cause an error.");
		}
	}

	public void checkGoldLocation() {
		if (player.getRights() >= 15) {
			cannonSetupGold();
		}
		if (World.CatacombsOSRS(player) && player.dailydwarfcatacombfee != true) {
			player.sendMessage(Colors.red + "You must pay a daily fee of 5 million coins to use this here! Click on the Altar to pay your fee.");
			return;
		}
		if (World.isNoCannonArea(getPlayer()) || World.isPvpArea(getPlayer())) {
			getPlayer().sendMessage("You cannot setup a cannon here");
			return;
		} else if (!World.isMultiArea(getPlayer())) {
			getPlayer().sendMessage("You can only setup a cannon in a multi area.");
			return;
		} else {
			cannonSetupGold();
		}
	}
	public void checkRoyalLocation() {
		if (player.getRights() >= 15) {
			cannonSetupGold();
		}
		if (World.CatacombsOSRS(player) && player.dailydwarfcatacombfee != true) {
			player.sendMessage(Colors.red + "You must pay a daily fee of 5 million coins to use this here! Click on the Altar to pay your fee.");
			return;
		}
		if (World.isNoCannonArea(getPlayer()) || World.isPvpArea(getPlayer())) {
			getPlayer().sendMessage("You cannot setup a cannon here");
			return;
		} else if (!World.isMultiArea(getPlayer())) {
			getPlayer().sendMessage("You can only setup a cannon in a multi area.");
			return;
		} else {
			cannonSetupRoyal();
		}
	}

	/**
	 * Checks if player lost cannon
	 */

	public void lostCannon() {
		if (player.hasSetupCannon == true) {
			if (player.getInventory().getFreeSlots() < 4) {
				if (retainsCannonBalls())
					player.getBank().addItem(2, getCannonBalls(), true);
				player.getBank().addItem(6, 1, true);
				player.getBank().addItem(8, 1, true);
				player.getBank().addItem(10, 1, true);
				player.getBank().addItem(12, 1, true);
				player.hasSetupCannon = false;
				player.sm("<col=1B9EE0>Your cannon has been added to your bank.");
			} else {
				if (retainsCannonBalls())
					player.getInventory().addItem(2, getCannonBalls());
				player.getInventory().addItem(6, 1);
				player.getInventory().addItem(8, 1);
				player.getInventory().addItem(10, 1);
				player.getInventory().addItem(12, 1);
				player.hasSetupCannon = false;
				player.sm("<col=1B9EE0>Your cannon has been placed in your inventory.");
			}
		}
	}

	public void lostGoldCannon() {
		if (player.hasSetupGoldCannon == true) {
			if (player.getInventory().getFreeSlots() < 4) {
				if (retainsCannonBalls())
					player.getBank().addItem(2, getCannonBalls(), true);
				player.getBank().addItem(20494, 1, true);
				player.getBank().addItem(20495, 1, true);
				player.getBank().addItem(20496, 1, true);
				player.getBank().addItem(20497, 1, true);
				player.hasSetupGoldCannon = false;
				player.sm("<col=1B9EE0>Your cannon has been added to your bank.");
			} else {
				if (retainsCannonBalls())
					player.getInventory().addItem(2, getCannonBalls());
				player.getInventory().addItem(20494, 1);
				player.getInventory().addItem(20495, 1);
				player.getInventory().addItem(20496, 1);
				player.getInventory().addItem(20497, 1);
				player.hasSetupGoldCannon = false;
				player.sm("<col=1B9EE0>Your cannon has been placed in your inventory.");
			}
		}
	}

	public void lostRoyalCannon() {
		if (player.hasSetupRoyalCannon == true) {
			if (player.getInventory().getFreeSlots() < 4) {
				if (retainsCannonBalls())
					player.getBank().addItem(2, getCannonBalls(), true);
				player.getBank().addItem(20498, 1, true);
				player.getBank().addItem(20499, 1, true);
				player.getBank().addItem(20500, 1, true);
				player.getBank().addItem(20501, 1, true);
				player.hasSetupRoyalCannon = false;
				player.sm("<col=1B9EE0>Your cannon has been added to your bank.");
			} else {
				if (retainsCannonBalls())
					player.getInventory().addItem(2, getCannonBalls());
				player.getInventory().addItem(20498, 1);
				player.getInventory().addItem(20499, 1);
				player.getInventory().addItem(20500, 1);
				player.getInventory().addItem(20501, 1);
				player.hasSetupRoyalCannon = false;
				player.sm("<col=1B9EE0>Your cannon has been placed in your inventory.");
			}
		}
	}

	/**
	 * Cannon setup
	 */
	public void cannonSetup() {
		if (hasCannon()) {
			player.getPackets().sendGameMessage(
					"You already have a cannon setup.");
			return;
		}
		if (!getPlayer().getInventory().containsItems(
				new Item[] { new Item(ITEMS[0]), new Item(ITEMS[1]),
						new Item(ITEMS[2]), new Item(ITEMS[3]) })) {
			player.getPackets()
					.sendGameMessage(
							"You do not have all the required items to set up the Dwarf Multi-Cannon.");
			return;
		}
		getPlayer().lock();
		getPlayer().setNextAnimation(new Animation(827));
		setCannon(true);
		setSettingUp(true);
		WorldTasksManager.schedule(new WorldTask() {
			int count = 0;

			@Override
			public void run() {
				switch (count) {
					case 0:
						setLastObject(new WorldObject(CANNON[1], 10, 0, player));
						World.spawnObject(getLastObject(), false);
						player.getPackets().sendGameMessage(
								"You place the cannon base on the ground...");
						getPlayer().getInventory().deleteItem(ITEMS[0], 1);
						break;
					case 1:
						World.removeObject(getLastObject(), false);
						World.getRegion(getLastObject().getRegionId())
								.removeObject(getLastObject());
						setLastObject(new WorldObject(CANNON[2], 10, 0, player));
						World.spawnObject(getLastObject(), false);
						player.getPackets().sendGameMessage("You add the stand...");
						getPlayer().getInventory().deleteItem(ITEMS[1], 1);
						break;
					case 2:
						World.removeObject(getLastObject(), false);
						World.getRegion(getLastObject().getRegionId())
								.removeObject(getLastObject());
						setLastObject(new WorldObject(CANNON[3], 10, 0, player));
						World.spawnObject(getLastObject(), false);
						player.getPackets()
								.sendGameMessage("You add the barrel...");
						getPlayer().getInventory().deleteItem(ITEMS[2], 1);
						break;
					case 3:
						World.removeObject(getLastObject(), false);
						World.getRegion(getLastObject().getRegionId())
								.removeObject(getLastObject());
						setLastObject(new WorldObject(CANNON[0], 10, 0, player));
						World.spawnObject(getLastObject(), false);
						setObject(getLastObject());
						setSettingUp(false);
						player.getPackets().sendGameMessage(
								"You add the furnace...");
						getPlayer().getInventory().deleteItem(ITEMS[3], 1);
						getPlayer().unlock();
						player.hasSetupCannon = true;
						this.stop();
						break;
				}
				getPlayer().setNextAnimation(new Animation(827));
				count++;
			}

		}, 0, 1);
	}

	public void cannonSetupRoyal() {
		if (hasCannon()) {
			player.getPackets().sendGameMessage(
					"You already have a cannon setup.");
			return;
		}
		if (!getPlayer().getInventory().containsItems(
				new Item[] { new Item(ROYAL_ITEMS[0]),
						new Item(ROYAL_ITEMS[1]), new Item(ROYAL_ITEMS[2]),
						new Item(ROYAL_ITEMS[3]) })) {
			player.getPackets()
					.sendGameMessage(
							"You do not have all the required items to set up the Dwarf Multi-Cannon.");
			return;
		}
		getPlayer().lock();
		getPlayer().setNextAnimation(new Animation(827));
		setCannon(true);
		setSettingUp(true);
		WorldTasksManager.schedule(new WorldTask() {
			int count = 0;

			@Override
			public void run() {
				switch (count) {
					case 0:
						setLastObject(new WorldObject(ROYAL_CANNON[1], 10, 0,
								player));
						World.spawnObject(getLastObject(), false);
						player.getPackets().sendGameMessage(
								"You place the royal cannon base on the ground...");
						getPlayer().getInventory().deleteItem(ROYAL_ITEMS[0], 1);
						break;
					case 1:
						World.removeObject(getLastObject(), false);
						World.getRegion(getLastObject().getRegionId())
								.removeObject(getLastObject());
						setLastObject(new WorldObject(ROYAL_CANNON[2], 10, 0,
								player));
						World.spawnObject(getLastObject(), false);
						player.getPackets().sendGameMessage("You add the stand...");
						getPlayer().getInventory().deleteItem(ROYAL_ITEMS[1], 1);
						break;
					case 2:
						World.removeObject(getLastObject(), false);
						World.getRegion(getLastObject().getRegionId())
								.removeObject(getLastObject());
						setLastObject(new WorldObject(ROYAL_CANNON[3], 10, 0,
								player));
						World.spawnObject(getLastObject(), false);
						player.getPackets()
								.sendGameMessage("You add the barrel...");
						getPlayer().getInventory().deleteItem(ROYAL_ITEMS[2], 1);
						break;
					case 3:
						World.removeObject(getLastObject(), false);
						World.getRegion(getLastObject().getRegionId())
								.removeObject(getLastObject());
						setLastObject(new WorldObject(ROYAL_CANNON[0], 10, 0,
								player));
						World.spawnObject(getLastObject(), false);
						setObject(getLastObject());
						setSettingUp(false);
						player.getPackets().sendGameMessage(
								"You add the furnace...");
						getPlayer().getInventory().deleteItem(ROYAL_ITEMS[3], 1);
						getPlayer().unlock();
						player.hasSetupRoyalCannon = true;
						this.stop();
						break;
				}
				getPlayer().setNextAnimation(new Animation(827));
				count++;
			}

		}, 0, 1);
	}

	public void cannonSetupGold() {
		if (hasCannon()) {
			player.getPackets().sendGameMessage(
					"You already have a cannon setup.");
			return;
		}
		if (!getPlayer().getInventory().containsItems(
				new Item[] { new Item(GOLD_ITEMS[0]),
						new Item(GOLD_ITEMS[1]), new Item(GOLD_ITEMS[2]),
						new Item(GOLD_ITEMS[3]) })) {
			player.getPackets()
					.sendGameMessage(
							"You do not have all the required items to set up the Dwarf Multi-Cannon.");
			return;
		}
		getPlayer().lock();
		getPlayer().setNextAnimation(new Animation(827));
		setCannon(true);
		setSettingUp(true);
		WorldTasksManager.schedule(new WorldTask() {
			int count = 0;

			@Override
			public void run() {
				switch (count) {
					case 0:
						setLastObject(new WorldObject(GOLD_CANNON[1], 10, 0,
								player));
						World.spawnObject(getLastObject(), false);
						player.getPackets().sendGameMessage(
								"You place the royal cannon base on the ground...");
						getPlayer().getInventory().deleteItem(GOLD_ITEMS[0], 1);
						break;
					case 1:
						World.removeObject(getLastObject(), false);
						World.getRegion(getLastObject().getRegionId())
								.removeObject(getLastObject());
						setLastObject(new WorldObject(GOLD_CANNON[2], 10, 0,
								player));
						World.spawnObject(getLastObject(), false);
						player.getPackets().sendGameMessage("You add the stand...");
						getPlayer().getInventory().deleteItem(GOLD_ITEMS[1], 1);
						break;
					case 2:
						World.removeObject(getLastObject(), false);
						World.getRegion(getLastObject().getRegionId())
								.removeObject(getLastObject());
						setLastObject(new WorldObject(GOLD_CANNON[3], 10, 0,
								player));
						World.spawnObject(getLastObject(), false);
						player.getPackets()
								.sendGameMessage("You add the barrel...");
						getPlayer().getInventory().deleteItem(GOLD_ITEMS[2], 1);
						break;
					case 3:
						World.removeObject(getLastObject(), false);
						World.getRegion(getLastObject().getRegionId())
								.removeObject(getLastObject());
						setLastObject(new WorldObject(GOLD_CANNON[0], 10, 0,
								player));
						World.spawnObject(getLastObject(), false);
						setObject(getLastObject());
						setSettingUp(false);
						player.getPackets().sendGameMessage(
								"You add the furnace...");
						getPlayer().getInventory().deleteItem(GOLD_ITEMS[3], 1);
						getPlayer().unlock();
						player.hasSetupGoldCannon = true;
						this.stop();
						break;
				}
				getPlayer().setNextAnimation(new Animation(827));
				count++;
			}

		}, 0, 1);
	}

	/**
	 * Pre-rotation setup check
	 *
	 * @param object
	 */
	public void preRotationSetup(WorldObject object) {
		if (getObject() != object) {
			player.getPackets().sendGameMessage(
					"You are not the owner of this Dwarf Cannon.");
			return;
		}
		if (getCannonBalls() < 1) {
			player.getPackets()
					.sendGameMessage("Your cannon has no ammo left!");
			setFiring(false);
			setRotating(false);
			loadDwarfCannon(object);
			return;
		}
		if (isFirst() == false) {
			setFirst(true);
		}
		if (isRotating() == false) {
			setRotating(true);
			startRotation(object);
		} else {
			player.sm("You can only load it once.");
		}
	}

	/**
	 * Starts the rotation after pre-setup
	 *
	 * @param object
	 */
	public void startRotation(final WorldObject object) {
		WorldTasksManager.schedule(new WorldTask() {
			int count = (hasLoadedOnce() == true ? getCannonDirection() : 0);

			@Override
			public void run() {
				if (isRotating() == false) {
					this.stop();
				} else if (isRotating() == true) {
					switch (count) {
						case 0:
							if (isFirst()) {
								setLoadedOnce(true);
								World.sendObjectAnimation(getPlayer(), object,
										new Animation(305));
							} else {
								World.sendObjectAnimation(getPlayer(), object,
										new Animation(303));
							}
							setCannonDirection(0);
							break;
						case 1:
							if (isFirst()) {
								World.sendObjectAnimation(getPlayer(), object,
										new Animation(307));
							} else {
								World.sendObjectAnimation(getPlayer(), object,
										new Animation(305));
							}
							setCannonDirection(1);
							break;
						case 2:
							if (isFirst()) {
								World.sendObjectAnimation(getPlayer(), object,
										new Animation(289));
							} else {
								World.sendObjectAnimation(getPlayer(), object,
										new Animation(307));
							}
							setCannonDirection(2);
							break;
						case 3:
							if (isFirst()) {
								World.sendObjectAnimation(getPlayer(), object,
										new Animation(184));
							} else {
								World.sendObjectAnimation(getPlayer(), object,
										new Animation(289));
							}
							setCannonDirection(3);
							break;
						case 4:
							if (isFirst()) {
								World.sendObjectAnimation(getPlayer(), object,
										new Animation(182));
							} else {
								World.sendObjectAnimation(getPlayer(), object,
										new Animation(184));
							}
							setCannonDirection(4);
							break;
						case 5:
							if (isFirst()) {
								World.sendObjectAnimation(getPlayer(), object,
										new Animation(178));
							} else {
								World.sendObjectAnimation(getPlayer(), object,
										new Animation(182));
							}
							setCannonDirection(5);
							break;
						case 6:
							if (isFirst()) {
								World.sendObjectAnimation(getPlayer(), object,
										new Animation(291));
							} else {
								World.sendObjectAnimation(getPlayer(), object,
										new Animation(178));
							}
							setCannonDirection(6);
							break;
						case 7:
							if (isFirst()) {
								World.sendObjectAnimation(getPlayer(), object,
										new Animation(303));
								setFirst(false);
							} else {
								World.sendObjectAnimation(getPlayer(), object,
										new Animation(291));
							}
							setCannonDirection(7);
							count = -1;
							break;
					}
				}
				count++;
				if (fireDwarfCannon(object)) {
					if (!retainsCannonBalls()) {
						player.getPackets().sendGameMessage(
								"Your cannon has ran out of ammo!");
						setFiring(false);
						setRotating(false);
						setLoadedOnce(false);
						setFirstFire(false);
						this.stop();
					}
				}
			}
		}, 0, 0);
	}

	public void PickupCannonType(WorldObject object) {
		if (getObject().getId() == 6) {
			pickUpDwarfCannon(0, getObject());
		} else if (getObject().getId() == 29408) {
			pickUpDwarfCannonRoyal(0, getObject());
		} else if (getObject().getId() == 29406) {
			pickUpDwarfCannonGold(0, getObject());
		}
	}

	/**
	 * Picks up and removes the dwarf cannon from the game
	 *
	 * @param stage
	 * @param object
	 */
	public void pickUpDwarfCannon(int stage, WorldObject object) {
		if (getObject() != object) {
			player.getPackets().sendGameMessage(
					"You are not the owner of this Dwarf Cannon.");
			return;
		}
		if (isSettingUp()) {
			player.getPackets()
					.sendGameMessage(
							"Please finish setting up your current cannon before picking it up.");
			return;
		}
		if (player.getInventory().getFreeSlots() < 5) {
			getPlayer().getBank().addItem(ITEMS[0], 1, true);
			getPlayer().getBank().addItem(ITEMS[1], 1, true);
			getPlayer().getBank().addItem(ITEMS[2], 1, true);
			getPlayer().getBank().addItem(ITEMS[3], 1, true);

			if (getCannonBalls() < 1) {
			} else {
				getPlayer().getInventory().addItem(2, cannonBalls);
			}
			getPlayer().setNextAnimation(new Animation(827));
			setCannonBalls(0);
			setRotating(false);
			setCannon(false);
			setFiring(false);
			setLoadedOnce(false);
			setFirst(false);
			setCannonDirection(0);
			setObject(null);
			World.removeObject(getLastObject(), true);
			player.hasSetupCannon = false;
		} else if (stage == 0 && player.getInventory().getFreeSlots() >= 5) {
			getPlayer().getInventory().addItem(ITEMS[0], 1);
			getPlayer().getInventory().addItem(ITEMS[1], 1);
			getPlayer().getInventory().addItem(ITEMS[2], 1);
			getPlayer().getInventory().addItem(ITEMS[3], 1);

			if (getCannonBalls() < 1) {
			} else {
				getPlayer().getInventory().addItem(2, cannonBalls);
			}
			getPlayer().setNextAnimation(new Animation(827));
			setCannonBalls(0);
			setRotating(false);
			setCannon(false);
			setFiring(false);
			setLoadedOnce(false);
			setFirst(false);
			setCannonDirection(0);
			setObject(null);
			World.removeObject(getLastObject(), true);
			player.hasSetupCannon = false;
		} else if (stage == 1 || stage == 2 || stage == 4) {

		}
	}

	public void pickUpDwarfCannonGold(int stage, WorldObject object) {
		if (getObject() != object) {
			player.getPackets().sendGameMessage(
					"You are not the owner of this Gold Dwarf Cannon.");
			return;
		}
		if (isSettingUp()) {
			player.getPackets()
					.sendGameMessage(
							"Please finish setting up your current gold cannon before picking it up.");
			return;
		}
		if (player.getInventory().getFreeSlots() < 5) {
			getPlayer().getBank().addItem(GOLD_ITEMS[0], 1, true);
			getPlayer().getBank().addItem(GOLD_ITEMS[1], 1, true);
			getPlayer().getBank().addItem(GOLD_ITEMS[2], 1, true);
			getPlayer().getBank().addItem(GOLD_ITEMS[3], 1, true);
			player.sendMessage(Colors.gray + "You didn't have enough inventory space so your dwarf cannon was recovered by your bank!");
			if (getCannonBalls() < 1) {

			} else {
				getPlayer().getInventory().addItem(2, cannonBalls);
			}
			getPlayer().setNextAnimation(new Animation(827));
			setCannonBalls(0);
			setRotating(false);
			setCannon(false);
			setFiring(false);
			setLoadedOnce(false);
			setFirst(false);
			setCannonDirection(0);
			setObject(null);
			World.removeObject(getLastObject(), true);
			player.hasSetupGoldCannon = false;
		} else if (stage == 0 && player.getInventory().getFreeSlots() >= 5) {
			getPlayer().getInventory().addItem(GOLD_ITEMS[0], 1);
			getPlayer().getInventory().addItem(GOLD_ITEMS[1], 1);
			getPlayer().getInventory().addItem(GOLD_ITEMS[2], 1);
			getPlayer().getInventory().addItem(GOLD_ITEMS[3], 1);
			player.sendMessage(Colors.gray + "You didn't have enough inventory space so your dwarf cannon was recovered by your bank!");
			if (getCannonBalls() < 1) {

			} else {
				getPlayer().getInventory().addItem(2, cannonBalls);
			}
			getPlayer().setNextAnimation(new Animation(827));
			setCannonBalls(0);
			setRotating(false);
			setCannon(false);
			setFiring(false);
			setLoadedOnce(false);
			setFirst(false);
			setCannonDirection(0);
			setObject(null);
			World.removeObject(getLastObject(), true);
			player.hasSetupGoldCannon = false;
		} else if (stage == 1 || stage == 2 || stage == 4) {

		}
	}

	public void pickUpDwarfCannonRoyal(int stage, WorldObject object) {
		if (getObject() != object) {
			player.getPackets().sendGameMessage(
					"You are not the owner of this Royale Dwarf Cannon.");
			return;
		}
		if (isSettingUp()) {
			player.getPackets()
					.sendGameMessage(
							"Please finish setting up your current royale cannon before picking it up.");
			return;
		}
		if (player.getInventory().getFreeSlots() < 5) {
			getPlayer().getBank().addItem(ROYAL_ITEMS[0], 1, true);
			getPlayer().getBank().addItem(ROYAL_ITEMS[1], 1, true);
			getPlayer().getBank().addItem(ROYAL_ITEMS[2], 1, true);
			getPlayer().getBank().addItem(ROYAL_ITEMS[3], 1, true);
			player.sendMessage(Colors.gray + "You didn't have enough inventory space so your dwarf cannon was recovered by your bank!");
			if (getCannonBalls() < 1) {

			} else {
				getPlayer().getInventory().addItem(2, cannonBalls);
			}
			getPlayer().setNextAnimation(new Animation(827));
			setCannonBalls(0);
			setRotating(false);
			setCannon(false);
			setFiring(false);
			setLoadedOnce(false);
			setFirst(false);
			setCannonDirection(0);
			setObject(null);
			World.removeObject(getLastObject(), true);
			player.hasSetupRoyalCannon = false;
		} else if (stage == 0 && player.getInventory().getFreeSlots() >= 5) {
			getPlayer().getInventory().addItem(ROYAL_ITEMS[0], 1);
			getPlayer().getInventory().addItem(ROYAL_ITEMS[1], 1);
			getPlayer().getInventory().addItem(ROYAL_ITEMS[2], 1);
			getPlayer().getInventory().addItem(ROYAL_ITEMS[3], 1);
			if (getCannonBalls() < 1) {

			} else {
				getPlayer().getInventory().addItem(2, cannonBalls);
			}
			getPlayer().setNextAnimation(new Animation(827));
			setCannonBalls(0);
			setRotating(false);
			setCannon(false);
			setFiring(false);
			setLoadedOnce(false);
			setFirst(false);
			setCannonDirection(0);
			setObject(null);
			World.removeObject(getLastObject(), true);
			player.hasSetupRoyalCannon = false;
		} else if (stage == 1 || stage == 2 || stage == 4) {

		}
	}

	/**
	 * Loads the Dwarf Multicannon with ammunition
	 */
	public void loadDwarfCannon(WorldObject object) {
		int ballsToAdd = 0;
		if (getObject() != object) {
			player.getPackets().sendGameMessage(
					"You are not the owner of this Dwarf Cannon.");
			return;
		}
		if (getCannonBalls() == 0
				&& getPlayer().getInventory().containsItem(2, 140)) {
			ballsToAdd = 140;
			getPlayer().getInventory().deleteItem(2, ballsToAdd);
			player.getPackets().sendGameMessage(
					"You load the cannon with " + ballsToAdd + " cannonball"
							+ (ballsToAdd == 1 ? "" : "s") + ".");
			this.cannonBalls += ballsToAdd;
			setObject(object);
		}
		if (getCannonBalls() < 140
				&& getCannonBalls() < 1
				&& getPlayer().getInventory().containsItem(2,
				140 - getCannonBalls())) {
			ballsToAdd = 140 - this.cannonBalls;
			getPlayer().getInventory().deleteItem(2, ballsToAdd);
			player.getPackets().sendGameMessage(
					"You load the cannon with " + ballsToAdd + " cannonball"
							+ (ballsToAdd == 1 ? "" : "s") + ".");
			this.cannonBalls += ballsToAdd;
			setObject(object);
		}
	}

	/**
	 * Fires the Dwarf MultiCannon
	 */
	public boolean fireDwarfCannon(WorldObject object) {
		boolean hit = false;
		if (getCannonBalls() == 0) {
			hit = false;
			setFiring(false);
			return false;
		}
		for (NPC n : World.getNPCs()) {
			int damage = Utils.getRandom(150);
			double combatXp = damage / 4;
			int distanceX = n.getX() - object.getX();
			int distanceY = n.getY() - object.getY();
			if (n == null || n.isDead()
					|| !n.getDefinitions().hasAttackOption()) {
				continue;
			}
			switch (getCannonDirection()) {
				case 0: // North
					if ((distanceY <= 8 && distanceY >= 0)
							&& (distanceX >= -1 && distanceX <= 1)) {
						hit = true;
					}
					break;
				case 1: // North East
					if ((distanceY <= 8 && distanceY >= 0)
							&& (distanceX <= 8 && distanceX >= 0)) {
						hit = true;
					}
					break;
				case 2: // East
					if ((distanceY <= 1 && distanceY >= -1)
							&& (distanceX <= 8 && distanceX >= 0)) {
						hit = true;
					}
					break;
				case 3: // South East
					if ((distanceY >= -8 && distanceY <= 0)
							&& (distanceX <= 8 && distanceX >= 0)) {
						hit = true;
					}
					break;
				case 4: // South
					if ((distanceY >= -8 && distanceY <= 0)
							&& (distanceX <= 1 && distanceX >= -1)) {
						hit = true;
					}
					break;
				case 5: // South West
					if ((distanceY >= -8 && distanceY <= 0)
							&& (distanceX >= -8 && distanceX <= 0)) {
						hit = true;
					}
					break;
				case 6: // West
					if ((distanceY >= -1 && distanceY <= 1)
							&& (distanceX >= -8 && distanceX <= 0)) {
						hit = true;
					}
					break;
				case 7: // North West
					if ((distanceY <= 8 && distanceY >= 0)
							&& (distanceX >= -8 && distanceX <= 0)) {
						hit = true;
					}
					break;
				default:
					hit = false;
					break;
			}
			if (hit) {
				this.cannonBalls -= 1;
				n.getCombat().setTarget(getPlayer());
				World.sendProjectile(getPlayer(), object, n, 53, 52, 52, 30, 0,
						0, 2);
				n.applyHit(new Hit(getPlayer(), damage, HitLook.CANNON_DAMAGE));
				getPlayer().getSkills().addXp(Skills.RANGE, combatXp / 2);
				return true;
			}
		}
		return false;
	}

	public void removeDwarfCannon() {
		// System.out.println("2");
		if (getLastObject() != null) {
			// System.out.println("3");
			setRotating(false);
			setCannon(false);
			setFiring(false);
			setLoadedOnce(false);
			setObject(null);
			setFirstFire(false);
			setCannonDirection(0);
			World.getRegion(getLastObject().getRegionId()).removeObject(
					getLastObject());
		}
	}

	public int getCannonDirection() {
		return cannonDirection;
	}

	public void setCannonDirection(int cannonDirection) {
		this.cannonDirection = cannonDirection;
	}

	public boolean hasLoadedOnce() {
		return loadedOnce;
	}

	public void setLoadedOnce(boolean loadedOnce) {
		this.loadedOnce = loadedOnce;
	}

	public boolean retainsCannonBalls() {
		return cannonBalls > 0;
	}

	/**
	 * @return the rotating
	 */
	public boolean isRotating() {
		return rotating;
	}

	/**
	 * @param rotating
	 *            the rotating to set
	 */
	public void setRotating(boolean rotating) {
		this.rotating = rotating;
	}

	public boolean hasCannon() {
		return hasCannon;
	}

	public void setCannon(boolean hasCannon) {
		this.hasCannon = hasCannon;
	}

	public boolean isFirst() {
		return first;
	}

	public void setFirst(boolean first) {
		this.first = first;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	/**
	 * @return the owner
	 */
	public String getOwner() {
		return owner;
	}

	/**
	 * @param owner
	 *            the owner to set
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}

	public boolean isFiring() {
		return isFiring;
	}

	public void setFiring(boolean isFiring) {
		this.isFiring = isFiring;
	}

	public boolean isFirstFire() {
		return firstFire;
	}

	public void setFirstFire(boolean firstFire) {
		this.firstFire = firstFire;
	}

	public WorldObject getLastObject() {
		return lastObject;
	}

	public void setLastObject(WorldObject lastObject) {
		this.lastObject = lastObject;
	}

	public boolean isSettingUp() {
		return settingUp;
	}

	public void setSettingUp(boolean settingUp) {
		this.settingUp = settingUp;
	}

	public int getCannonBalls() {
		return cannonBalls;
	}

	public void setCannonBalls(int cannonBalls) {
		this.cannonBalls = cannonBalls;
	}

	public WorldObject getObject() {
		return object;
	}

	public void setObject(WorldObject object) {
		this.object = object;
	}
}