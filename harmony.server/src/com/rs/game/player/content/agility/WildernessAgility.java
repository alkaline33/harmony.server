package com.rs.game.player.content.agility;

import com.rs.game.Animation;
import com.rs.game.ForceMovement;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.content.DailyChallenges;
import com.rs.game.player.content.event.season.SeasonEvent;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;

public class WildernessAgility {

	/*
	 * Author Bandoswhips
	 */

	public static void GateWalk(final Player player, final WorldObject object) {
		if(!Agility.hasLevel(player, 52)) {
			return;
		}
		if(player.getY() != object.getY()) {
			player.addWalkSteps(2998, 3916, 0, false);
			player.lock(2);
			WorldTasksManager.schedule(new WorldTask() {

				@Override
				public void run() {
					GateWalkEnd(player, object);
				}
			}, 1);
		} else {
			GateWalkEnd(player, object);
		}
	}

	private static void GateWalkEnd(final Player player, WorldObject object) {
		player.getPackets().sendGameMessage("You walk carefully across the path...", true);
		player.lock(17);
		player.setNextAnimation(new Animation(9908));
		final WorldTile toTile = new WorldTile(object.getX(), 3931, object.getPlane());
		player.setNextForceMovement(new ForceMovement(player, 0, toTile, 16, ForceMovement.NORTH));
		WorldTasksManager.schedule(new WorldTask() {

			@Override
			public void run() {
				player.setNextWorldTile(toTile);
				player.getPackets().sendGameMessage("... and make it safely to the other side.", true);
			}

		}, 15);
	}

	public static void enterObstaclePipe(final Player player, int objectX, int objectY) {
		if(!Agility.hasLevel(player, 52)) {
			return;
		}
		final boolean running = player.getRun();
		player.setRunHidden(false);
		player.lock(14);
		player.addWalkSteps(objectX, objectY == 3938 ? 3950 : 3937, -1, false);
		player.getPackets().sendGameMessage(
				"You pulled yourself through the pipes.", true);
		WorldTasksManager.schedule(new WorldTask() {
			boolean secondloop;

			@Override
			public void run() {
				if (!secondloop) {
					secondloop = true;
					player.getAppearence().setRenderEmote(295);
				} else {
					player.getAppearence().setRenderEmote(-1);
					setWildernessStage(player, 0);
					player.setRunHidden(running);
					player.getSkills().addXp(Skills.AGILITY, 12.5);
					stop();
				}
			}
		}, 0, 6);
	}

	public static void swingOnRopeSwing(final Player player, WorldObject object) {
		if(!Agility.hasLevel(player, 52)) {
			return;
		}
		if(player.getY() != 3953) {
			 player.getPackets().sendGameMessage("You'll need to get closer to make this jump.");
			 return;
		}
		player.lock(4);
		player.setNextAnimation(new Animation(751));
		World.sendObjectAnimation(player, object, new Animation(497));
		final WorldTile toTile = new WorldTile(object.getX(), 3958, object.getPlane());
		player.setNextForceMovement(new ForceMovement(player, 1, toTile, 3, ForceMovement.NORTH));
		player.getSkills().addXp(Skills.AGILITY, 22);
		player.getPackets().sendGameMessage("You skilfully swing across.", true);
		WorldTasksManager.schedule(new WorldTask() {

			@Override
			public void run() {
				player.setNextWorldTile(toTile);
				if (getWildernessStage(player) == 0) {
					setWildernessStage(player, 1);
				}
				player.getSkills().addXp(Skills.AGILITY, 20);
			}

		}, 1);
	}

	/*
	 * Stepping Stone Method by Cjay0091
	 */
	public static void crossSteppingPalletes(final Player player, final WorldObject object) {
		if (player.getY() != object.getY()) {
			return;
		}
		player.lock(12);
		WorldTasksManager.schedule(new WorldTask() {

			int x;

			@Override
			public void run() {
				if (x++ == 6) {
					stop();
					return;
				}
				final WorldTile toTile = new WorldTile(3002 - x, player.getY(), player.getPlane());
				player.setNextForceMovement(new ForceMovement(toTile, 1, ForceMovement.WEST));
				player.setNextAnimation(new Animation(741));
				WorldTasksManager.schedule(new WorldTask() {

					@Override
					public void run() {
						player.setNextWorldTile(toTile);
					}
				}, 0);
			}
		}, 2, 1);
		player.getSkills().addXp(Skills.AGILITY, 20);
		if (getWildernessStage(player) == 1) {
			setWildernessStage(player, 2);
		}
	}

	public static void walkLog(final Player player) {
		if (player.getX() != 3002 || player.getY() != 3945) {
			player.addWalkSteps(3002, 3945, -1, false);
		}
		final boolean running = player.getRun();
		player.setRunHidden(false);
		player.lock(10);
		player.addWalkSteps(2994, 3945, -1, false);
		player.getPackets().sendGameMessage(
				"You walk carefully across the log...", true);
		WorldTasksManager.schedule(new WorldTask() {
			boolean secondloop;

			@Override
			public void run() {
				if (!secondloop) {
					secondloop = true;
					player.getAppearence().setRenderEmote(155);
				} else {
					player.getAppearence().setRenderEmote(-1);
					player.setRunHidden(running);
					player.getSkills().addXp(Skills.AGILITY, 20);
					player.getPackets().sendGameMessage(
							"... and make it safely to the other side.", true);
					stop();
					if (getWildernessStage(player) == 2) {
						setWildernessStage(player, 3);
					}
				}
			}
		}, 0, 6);
	}

	public static void climbCliff(final Player player, WorldObject object) {
		if (player.getY() != 3939) {
			return;
		}
		player.lock(8);
		player.setNextAnimation(new Animation(3378));
		final WorldTile toTile = new WorldTile(player.getX(), 3935, 0);

		player.getPackets().sendGameMessage("You climb up the rock.", true);
		WorldTasksManager.schedule(new WorldTask() {
			@Override
			public void run() {
				player.setNextWorldTile(toTile);
				player.setNextAnimation(new Animation(-1));
				player.getAppearence().setRenderEmote(-1);
				stop();
				if (getWildernessStage(player) == 3) {
					removeWildernessStage(player);
					player.getSkills().addXp(Skills.AGILITY, 498.9);
					player.getInventory().addItem(995, 35000);
					if (player.challengeid == 14 && player.challengeid > 0) {
						DailyChallenges.UpdateChallenge(player);
					}
					SeasonEvent.HandleActivity(player, "Agility", 25);
					player.lapsrun++;

					 if (player.silverhawks >= 1 && player.getEquipment().getBootsId() == 29922) {
							player.getSkills().addXp(Skills.AGILITY, 230);
							player.silverhawks -= 1;
						}

				}
			}
		}, 6);
	}

	public static void removeWildernessStage(Player player) {
		player.getTemporaryAttributtes().remove("WildernessCourse");
	}

	public static void setWildernessStage(Player player, int stage) {
		player.getTemporaryAttributtes().put("WildernessCourse", stage);
	}

	public static int getWildernessStage(Player player) {
		Integer stage = (Integer) player.getTemporaryAttributtes().get(
				"WildernessCourse");
		if (stage == null) {
			return -1;
		}
		return stage;
	}
}