package com.rs.game.player.content.agility;

import com.rs.game.Animation;
import com.rs.game.ForceMovement;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.content.DailyChallenges;
import com.rs.game.player.content.event.season.SeasonEvent;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

public class GnomeAgility {

	public static void climbDownGnomeTreeBranch(final Player player) {
		if (player.getX() < 2483) {
			return;
		}
		player.useStairs(828, new WorldTile(2487, 3421, 0), 1, 2, "You climbed the tree branch succesfully.");
		WorldTasksManager.schedule(new WorldTask() {
			@Override
			public void run() {
				if (getGnomeStage(player) == 3) {
					setGnomeStage(player, 4);
				}
				player.getSkills().addXp(Skills.AGILITY, 5);
			}
		}, 1);
	}

	public static void climbGnomeObstacleNet(final Player player) {
		if (player.getY() != 3426) {
			return;
		}
		player.sendMessage("You climb the netting.");
		player.useStairs(828, new WorldTile(player.getX(), 3423, 1), 1, 2);
		WorldTasksManager.schedule(new WorldTask() {
			@Override
			public void run() {
				if (getGnomeStage(player) == 0) {
					setGnomeStage(player, 1);
				}
				player.getSkills().addXp(Skills.AGILITY, 7.5);
			}
		}, 1);
	}

	public static void climbGnomeObstacleNet2(final Player player) {
		if (player.getY() != 3425) {
			return;
		}
		player.sendMessage("You climb the netting.");
		player.useStairs(828, new WorldTile(player.getX(), player.getY() == 3425 ? 3428 : 3425, 0), 1, 2);
		WorldTasksManager.schedule(new WorldTask() {
			@Override
			public void run() {
				if (getGnomeStage(player) == 4) {
					setGnomeStage(player, 5);
				}
				player.getSkills().addXp(Skills.AGILITY, 7.5);
			}
		}, 1);
	}

	public static void climbUpGnomeTreeBranch(final Player player) {
		if (player.getX() >= 2478) {
			return;
		}
		player.sendMessage("You climb the tree...");
		player.useStairs(828, new WorldTile(2473, 3420, 2), 1, 2, "... to the plantaform above.");
		WorldTasksManager.schedule(new WorldTask() {
			@Override
			public void run() {
				if (getGnomeStage(player) == 1) {
					setGnomeStage(player, 2);
				}
				player.getSkills().addXp(Skills.AGILITY, 5);
			}
		}, 1);
	}

	public static void climbUpTree(final Player player) {
		if (!Agility.hasLevel(player, 85)) {
			return;
		}
		player.sendMessage("You climb the tree.");
		player.useStairs(828, new WorldTile(player.getX(), 3420, 3), 1, 2);
		WorldTasksManager.schedule(new WorldTask() {
			@Override
			public void run() {
				if (getGnomeStage(player) == 1) {
					setGnomeStage(player, 2);
				}
				player.getSkills().addXp(Skills.AGILITY, 25);
			}
		}, 1);
	}

	public static void enterGnomePipe(final Player player, int objectX, int objectY) {
		if (Utils.currentTimeMillis() - player.getLastAnimationEnd() < 600) {
			return;
		}
		player.lock();
		player.addWalkSteps(objectX, objectY == 3431 ? 3437 : 3430, -1, false);
		player.sendMessage("You pulled yourself through the pipes.");
		WorldTasksManager.schedule(new WorldTask() {
			boolean secondloop;

			@Override
			public void run() {
				if (!secondloop) {
					secondloop = true;
					player.getAppearence().setRenderEmote(295);
				} else {
					player.getAppearence().setRenderEmote(-1);
					player.getSkills().addXp(Skills.AGILITY, 7.5);
					if (getGnomeStage(player) == 5) {
						removeGnomeStage(player);
						player.getInventory().addItem(995, 10000);
						player.getInventory().addItem(2996, Utils.random(1, 5));
						if (player.challengeid == 13 && player.challengeid > 0) {
							DailyChallenges.UpdateChallenge(player);
						}
						SeasonEvent.HandleActivity(player, "Agility", 10);
						player.getSkills().addXp(Skills.AGILITY, 39);
					}
					player.unlock();
					stop();
				}
			}
		}, 0, 6);
	}

	public static int getGnomeStage(Player player) {
		Integer stage = (Integer) player.getTemporaryAttributtes().get("GnomeCourse");
		if (stage == null) {
			return -1;
		}
		return stage;
	}

	public static void JumpDown(final Player player, WorldObject object) {
		if (Utils.currentTimeMillis() - player.getLastAnimationEnd() < 600) {
			return;
		}
		if (!Agility.hasLevel(player, 85)) {
			return;
		}
		player.lock();
		WorldTasksManager.schedule(new WorldTask() {
			boolean secondLoop;

			@Override
			public void run() {
				final WorldTile toTile = new WorldTile(2485, 3436, 0);
				if (!secondLoop) {
					player.setNextForceMovement(new ForceMovement(player, 0, toTile, 5, ForceMovement.NORTH));
					player.setNextAnimation(new Animation(2923));
					secondLoop = true;
				} else {
					player.setNextAnimation(new Animation(2924));
					player.setNextWorldTile(toTile);
					if (getGnomeStage(player) == 4) {
						
						player.getSkills().addXp(Skills.AGILITY, 605);
						player.getInventory().addItem(995, 20000);
						player.getInventory().addItem(2996, Utils.random(1, 8));
						SeasonEvent.HandleActivity(player, "Agility", 10);
						
						removeGnomeStage(player);
					} else {
						player.getSkills().addXp(Skills.AGILITY, 25);
					}
					player.unlock();
					stop();
				}
			}
		}, 0, 3);
	}

	public static void PreSwing(final Player player, final WorldObject object) {
		if (player.getY() >= 3422) {
			return;
		}
		player.lock();
		player.setNextAnimation(new Animation(11784));
		final WorldTile toTile = new WorldTile(player.getX(), 3421, object.getPlane());
		player.setNextForceMovement(new ForceMovement(player, 0, toTile, 2, ForceMovement.NORTH));
		WorldTasksManager.schedule(new WorldTask() {
			int stage;

			@Override
			public void run() {
				if (stage == 1) {
					player.setNextWorldTile(toTile);
					player.setNextAnimation(new Animation(11785));
					Swing(player, object);
					stop();
				}
				stage++;
			}
		}, 0, 1);
	}

	public static void removeGnomeStage(Player player) {
		player.getTemporaryAttributtes().remove("GnomeCourse");
	}

	public static void RunGnomeBoard(final Player player, final WorldObject object) {
		if (player.getX() != 2477 || player.getY() != 3418 || player.getPlane() != 3) {
			return;
		}
		player.lock(4);
		player.setNextAnimation(new Animation(2922));
		final WorldTile toTile = new WorldTile(2484, 3418, object.getPlane());
		player.setNextForceMovement(new ForceMovement(player, 1, toTile, 3, ForceMovement.EAST));
		player.getSkills().addXp(Skills.AGILITY, 25);
		player.sendMessage("You skillfully run across the Board");
		WorldTasksManager.schedule(new WorldTask() {
			@Override
			public void run() {
				player.setNextWorldTile(toTile);
				setGnomeStage(player, 3);
			}
		}, 1);
	}

	public static void setGnomeStage(Player player, int stage) {
		player.getTemporaryAttributtes().put("GnomeCourse", stage);
	}

	public static void Swing(final Player player, final WorldObject object) {
		if (!Agility.hasLevel(player, 85)) {
			return;
		}
		player.lock();
		final WorldTile toTile = new WorldTile(player.getX(), 3425, object.getPlane());
		player.setNextForceMovement(new ForceMovement(player, 0, toTile, 1, ForceMovement.NORTH));
		WorldTasksManager.schedule(new WorldTask() {
			int stage;

			@Override
			public void run() {
				if (stage == 0) {
					player.setNextAnimation(new Animation(11789));
					player.setNextWorldTile(toTile);
				} else if (stage == 1) {
					Swing1(player, object);
					stop();
				}
				stage++;
			}
		}, 0, 1);
	}

	public static void Swing1(final Player player, final WorldObject object) {
		if (!Agility.hasLevel(player, 85)) {
			return;
		}
		player.lock();
		final WorldTile NextTile = new WorldTile(player.getX(), 3429, object.getPlane());
		player.setNextForceMovement(new ForceMovement(player, 2, NextTile, 3, ForceMovement.NORTH));
		WorldTasksManager.schedule(new WorldTask() {
			int stage;

			@Override
			public void run() {
				if (stage == 3) {
					player.setNextWorldTile(NextTile);
					Swing2(player, object);
					stop();
				}
				stage++;
			}
		}, 0, 1);
	}

	public static void Swing2(final Player player, final WorldObject object) {
		if (!Agility.hasLevel(player, 85)) {
			return;
		}
		player.lock();
		final WorldTile LastTile = new WorldTile(player.getX(), 3432, object.getPlane());
		player.setNextForceMovement(new ForceMovement(player, 0, LastTile, 1, ForceMovement.NORTH));
		WorldTasksManager.schedule(new WorldTask() {
			int stage;

			@Override
			public void run() {
				if (stage == 2) {
					player.setNextWorldTile(LastTile);
					if (getGnomeStage(player) == 3) {
						setGnomeStage(player, 4);
					}
					player.unlock();
					stop();
				}
				stage++;
			}
		}, 0, 1);
	}

	public static void walkBackGnomeRope(final Player player) {
		if (player.getX() != 2483 || player.getY() != 3420 || player.getPlane() != 2) {
			return;
		}
		player.setRunHidden(false);
		player.lock();
		player.addWalkSteps(2477, 3420, -1, false);
		WorldTasksManager.schedule(new WorldTask() {
			boolean secondloop;

			@Override
			public void run() {
				if (!secondloop) {
					secondloop = true;
					player.getAppearence().setRenderEmote(155);
				} else {
					player.getAppearence().setRenderEmote(-1);
					player.getSkills().addXp(Skills.AGILITY, 3.75);
					player.sendMessage("You passed the obstacle succesfully.");
					player.unlock();
					stop();
				}
			}
		}, 0, 5);
	}

	public static void walkGnomeLog(final Player player) {
		if (player.getX() != 2474 || player.getY() != 3436) {
			return;
		}
		player.lock();
		player.addWalkSteps(2474, 3429, -1, false);
		player.sendMessage("You walk carefully across the slippery log...");
		WorldTasksManager.schedule(new WorldTask() {
			boolean secondloop;

			@Override
			public void run() {
				if (!secondloop) {
					secondloop = true;
					player.getAppearence().setRenderEmote(155);
				} else {
					player.getAppearence().setRenderEmote(-1);
					setGnomeStage(player, 0);
					player.getSkills().addXp(Skills.AGILITY, 7.5);
					player.sendMessage("... and make it safely to the other side.");
					player.unlock();
					stop();
				}
			}
		}, 0, 6);
	}

	public static void walkGnomeRope(final Player player) {
		if (player.getX() != 2477 || player.getY() != 3420 || player.getPlane() != 2) {
			return;
		}
		player.lock();
		player.addWalkSteps(2483, 3420, -1, false);
		WorldTasksManager.schedule(new WorldTask() {
			boolean secondloop;

			@Override
			public void run() {
				if (!secondloop) {
					secondloop = true;
					player.getAppearence().setRenderEmote(155);
				} else {
					player.getAppearence().setRenderEmote(-1);
					if (getGnomeStage(player) == 2) {
						setGnomeStage(player, 3);
					}
					player.getSkills().addXp(Skills.AGILITY, 7.5);
					player.sendMessage("You passed the obstacle succesfully.");
					player.unlock();
					stop();
				}
			}
		}, 0, 5);
	}
}
