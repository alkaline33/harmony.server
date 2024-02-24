package com.rs.game.player.content;

import java.util.Random;

import com.rs.game.Animation;
import com.rs.game.Graphics;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.player.Player;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

public class WildernessTeleports {

	/**
	 * @author Jake | Santa Hat @Rune-Server
	 */

	public enum Locations {

		LOCATION_1(3154, 3158, 3618, 3622, new WorldTile(3156, 3620, 0), 65619), LOCATION_2(3217, 3221, 3654, 3658, new WorldTile(3219, 3656, 0), 65620), LOCATION_3(3033, 3037, 3730, 3734, new WorldTile(3035, 3732, 0), 65617), LOCATION_4(3104, 3108, 3792, 3796, new WorldTile(3106, 3794, 0), 65618), LOCATION_5(2978, 2982, 3864, 3868, new WorldTile(2980, 3866, 0), 65616), LOCATION_6(3305, 3309, 3914, 3918, new WorldTile(3307, 3916, 0), 65621);

		private int topLeftX, bottomRightX, bottomRightY, topLeftY;
		private WorldTile location;
		private int objectId;

		Locations(int topLeftX, int bottomRightX, int bottomRightY, int topLeftY, WorldTile location, int objectId) {
			this.topLeftX = topLeftX;
			this.bottomRightX = bottomRightX;
			this.bottomRightY = bottomRightY;
			this.topLeftY = topLeftY;
			this.location = location;
			this.objectId = objectId;
		}

		public int getTopLeftX() {
			return topLeftX;
		}

		public int getBottomRightX() {
			return bottomRightX;
		}

		public int getBottomRightY() {
			return bottomRightY;
		}

		public int getTopLeftY() {
			return topLeftY;
		}

		public WorldTile getLocation() {
			return location;
		}

		public int getObjectId() {
			return objectId;
		}
	}

	/**
	 * Pre-Teleport - 4 Second wait until the player is teleported
	 */
	public static void preTeleport(Player player, final WorldObject object) {
		player.out("You activate the obelisk and hear a faint rumbling sound.");
		WorldTasksManager.schedule(new WorldTask() {
			int timer;

			@Override
			public void run() {
				if (timer == 4) {
					handleObelisk(object);
					stop();
				}
				timer++;
			}
		}, 0, 1);
	}

	/**
	 * Teleports all of the players standing on the Obelisk
	 */
	private static void handleObelisk(WorldObject object) {
		final Locations loc = randomLocation();
		for (Locations location : Locations.values()) {
			if (location.getObjectId() == object.getId()) {
				for (final Player p : World.getPlayers()) {
					if (isOnObelisk(p, location)) {
						WorldTasksManager.schedule(new WorldTask() {
							int timer;

							@Override
							public void run() {
								p.setNextGraphics(new Graphics(661));
								p.setNextAnimation(new Animation(8939));
								if (timer == 1) {
									p.setNextWorldTile(new WorldTile(Utils.random(loc.getLocation().getX() - 1, loc.getLocation().getX() + 1), Utils.random(loc.getLocation().getY() - 1, loc.getLocation().getY() + 1), 0));
									p.setNextAnimation(new Animation(8941));
									stop();
								}
								timer++;
							}
						}, 0, 1);
					}
				}
			}
		}
	}

	/**
	 * Generates a random location from the Locations enum
	 */
	private static Locations randomLocation() {
		int pick = new Random().nextInt(Locations.values().length);
		return Locations.values()[pick];
	}

	/**
	 * Checks if the player is within the Obelisk
	 */
	private static boolean isOnObelisk(WorldTile tile, Locations ob) {
		return (tile.getX() >= ob.getTopLeftX() && tile.getX() <= ob.getBottomRightX() && tile.getY() >= ob.getBottomRightY() && tile.getY() <= ob.getTopLeftY());
	}

}