package com.rs.game.player.controlers;

import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import com.rs.Settings;
import com.rs.cores.CoresManager;
import com.rs.game.Animation;
import com.rs.game.RegionBuilder;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.npc.fightcaves.TzKekCaves;
import com.rs.game.npc.others.SlayerSurvivalNPC;
import com.rs.game.player.Skills;
import com.rs.game.player.content.event.season.SeasonEvent;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Logger;
import com.rs.utils.Utils;

public class SlayerSurvival extends Controler {

	public static final WorldTile OUTSIDE = new WorldTile(Settings.RESPAWN_PLAYER_LOCATION);

	private final int[][] WAVES = { { 1648, 1648, 1648 }, { 117, 117, 117 }, { 1637, 1637, 1637 }, { 1624, 1624, 1624 }, { 1612, 1612, 1612 }, { 1604, 1604, 1604 }, { 1643, 1643, 1643 }, { 1618, 1618, 1618 }, { 90, 90, 90, 90, 90 }, { 78, 78, 78 }, { 111, 111, 111 }, { 134 }, { 5361, 5361, 5361 }, { 1610, 1610, 1610 }, { 119, 119 }, { 127, 127 }, { 1626, 1626, 1626 }, { 1613, 1613, 1613 }, { 110, 110, 110 }, { 1590 }, { 84, 84 }, { 1615, 1615, 1615 }, { 30027 }, { 2783, 2783, 2783 }, { 14696, 14696 }, { 5362, 5362 }, { 941, 54, 55, }, { 50 }, { 112, 112, 112 }, { 29985, 29986 }, { 4357, 4357 }, { 9463, 9463 }, { 1338, 1338, 1338 }, { 31498, 31499, 31498 }, { 1600, 1600, 1600 }, { 4381, 4381, 4381 }, { 1631, 1631, 1631 }, { 1620, 1620, 1620 }, { 1633, 1633, 1633 }, { 1616, 1616, 1616 },
			{ 1608, 1608 }, { 9172 }, { 51, 51 }, { 1591, 1592 }, { 49, 49, 49, 49, 49 }, { 1593, 1593, 1593, 1593, 1593 }, { 82, 83, 82, 83 }, { 38794 }, { 38793 }, { 38792 }, { 38794, 38793, 38792, 38795 }, { 14301 } };

	private int[] boundChuncks;
	private Stages stage;
	private boolean logoutAtEnd;
	private boolean login;
	public boolean spawned;
	public int selectedMusic;

	// public static void enterFightCaves(Player player) {
	// if (player.getFamiliar() != null || player.getPet() != null ||
	// Summoning.hasPouch(player) || Pets.hasPet(player)) {
	// player.getDialogueManager().startDialogue("SimpleNPCMessage", 9085, "Not pets
	// allowed here!");
	// return;
	// }
	// player.hptracker = false;
	//
	// player.getControlerManager().startControler("SlayerSurvivalControler", 1); //
	// start at wave 1
	//
	// }

	public static enum Stages {
		LOADING, RUNNING, DESTROYING
	}

	@Override
	public void start() {
		loadCave(false);
	}

	@Override
	public boolean processButtonClick(int interfaceId, int componentId, int slotId, int packetId) {
		if (stage != Stages.RUNNING) {
			return false;
		}
		if (interfaceId == 182 && (componentId == 6 || componentId == 13)) {
			if (!logoutAtEnd) {
				logoutAtEnd = true;
				player.getPackets().sendGameMessage("<col=ff0000>You will be logged out automatically at the end of this wave.");
				player.getPackets().sendGameMessage("<col=ff0000>If you log out sooner, you will have to repeat this wave.");
			} else {
				player.forceLogout();
			}
			return false;
		}
		return true;
	}

	/**
	 * return process normaly
	 */
	@Override
	public boolean processObjectClick1(WorldObject object) {
		if (object.getId() == 9357) {
			if (stage != Stages.RUNNING) {
				return false;
			}
			exitCave(1);
			return false;
		}
		return true;
	}

	/*
	 * return false so wont remove script
	 */
	@Override
	public boolean login() {
		loadCave(true);
		return false;
	}

	public void loadCave(final boolean login) {
		this.login = login;
		stage = Stages.LOADING;
		player.lock(); // locks player
		CoresManager.slowExecutor.execute(new Runnable() {
			@Override
			public void run() {
				// finds empty map bounds
				boundChuncks = RegionBuilder.findEmptyChunkBound(4, 4);
				// copys real map into the empty map
				// 552 640
				RegionBuilder.copyAllPlanesMap(386, 490, boundChuncks[0], boundChuncks[1], 4);
				// selects a music
				// selectedMusic = MUSICS[Utils.random(MUSICS.length)];
				player.setNextWorldTile(!login ? getWorldTile(9, 9) : getWorldTile(9, 9));
				// 1delay because player cant walk while teleing :p, + possible issues avoid
				WorldTasksManager.schedule(new WorldTask() {
					@Override
					public void run() {
						if (!login) {
							WorldTile walkTo = getWorldTile(9, 9);
							player.addWalkSteps(walkTo.getX(), walkTo.getY());
						}
						player.getDialogueManager().startDialogue("SimpleNPCMessage", 9085, "Goodluck!");
						player.setForceMultiArea(true);
						// playMusic();
						player.unlock(); // unlocks player
						stage = Stages.RUNNING;
					}

				}, 1);
				if (!login) {
					/*
					 * lets stress less the worldthread, also fastexecutor used for mini stuff
					 */
					CoresManager.fastExecutor.schedule(new TimerTask() {

						@Override
						public void run() {
							if (stage != Stages.RUNNING) {
								return;
							}
							try {
								startWave();
							} catch (Throwable t) {
								Logger.handle(t);
							}
						}
					}, 6000);
				}
			}
		});
	}

	public WorldTile getSpawnTile() {
		switch (Utils.random(5)) {
		case 0:
			return getWorldTile(10, 10);
		case 1:
			return getWorldTile(9, 10);
		case 2:
			return getWorldTile(9, 8);
		default:
			return getWorldTile(10, 8);
		}
	}

	@Override
	public void moved() {
		if (stage != Stages.RUNNING || !login) {
			return;
		}
		login = false;
		setWaveEvent();
	}

	public void startWave() {
		int currentWave = getCurrentWave();
		if (currentWave >= WAVES.length) {
			win();
			return;
		}
		player.getInterfaceManager().sendTab(player.getInterfaceManager().hasRezizableScreen() ? 11 : 0, 316);
		player.getPackets().sendConfig(639, currentWave);
		if (stage != Stages.RUNNING) {
			return;
		}
		for (int id : WAVES[currentWave - 0]) {
			if (id == 2736) {
				new TzKekCaves(id, getSpawnTile());
			} else {
				new SlayerSurvivalNPC(id, getSpawnTile());
			}
		}
		spawned = true;
	}

	public void win() {
		if (stage != Stages.RUNNING) {
			return;
		}
		exitCave(4);
	}

	public void nextWave() {
		setCurrentWave(getCurrentWave() + 1);
		if (logoutAtEnd) {
			player.forceLogout();
			return;
		}
		player.getSkills().addXp(Skills.SLAYER, 10 * getCurrentWave());
		setWaveEvent();
	}

	public void setWaveEvent() {

		CoresManager.fastExecutor.schedule(new TimerTask() {

			@Override
			public void run() {
				try {
					if (stage != Stages.RUNNING) {
						return;
					}
					startWave();
				} catch (Throwable e) {
					Logger.handle(e);
				}
			}
		}, 600);
	}

	@Override
	public void process() {
		if (spawned) {
			List<Integer> npcs = World.getRegion(player.getRegionId()).getNPCsIndexes();
			if (npcs == null || npcs.isEmpty()) {
				spawned = false;
				nextWave();
			}
		}
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
					player.getPackets().sendGameMessage("You have been defeated!");
				} else if (loop == 3) {
					player.reset();
					exitCave(1);
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
	public void magicTeleported(int type) {
		exitCave(2);
	}

	/*
	 * logout or not. if didnt logout means lost, 0 logout, 1, normal, 2 tele
	 */
	public void exitCave(int type) {
		stage = Stages.DESTROYING;
		WorldTile outside = new WorldTile(OUTSIDE, 2); // radomizes alil
		if (type == 0 || type == 2) {
			removeControler();
			player.setNextWorldTile(outside);
		} else {
			player.setForceMultiArea(false);
			player.getPackets().closeInterface(player.getInterfaceManager().hasRezizableScreen() ? 11 : 0);
			if (type == 1 || type == 4) {
				player.setNextWorldTile(outside);
				if (type == 4) {
					player.reset();
					player.getDialogueManager().startDialogue("SimpleNPCMessage", 9085, "Well done!");
					player.getPackets().sendGameMessage("You were victorious! You have gained 5 slayer survival points!");
					if (player.slayersurvivalcompleted < 1) {
						World.sendWorldMessage("<col=993300>" + player.getDisplayName() + " has just completed slayer survival for the first time!", true);
					}
					player.slayersurvivalcompleted++;
					//SeasonEvent.HandleActivity(player, "Slayer Survival", 450);
					player.slayersurvivalpoints += Settings.EVENT_SPOTLIGHT.contains("minigame") ? 10 : 5;
				} else if (getCurrentWave() == 1) {
					player.getDialogueManager().startDialogue("SimpleNPCMessage", 9085, "Well I suppose you tried... better luck next time.");
				} else {
					player.getDialogueManager().startDialogue("SimpleNPCMessage", 9085, "You lost, pathetic!");
				}
			}
			removeControler();
		}
		/*
		 * 1200 delay because of leaving
		 */
		CoresManager.slowExecutor.schedule(new Runnable() {
			@Override
			public void run() {
				RegionBuilder.destroyMap(boundChuncks[0], boundChuncks[1], 8, 8);
			}
		}, 1200, TimeUnit.MILLISECONDS);
	}

	/*
	 * gets worldtile inside the map
	 */
	public WorldTile getWorldTile(int mapX, int mapY) {
		return new WorldTile(boundChuncks[0] * 8 + mapX, boundChuncks[1] * 8 + mapY, 0);
	}

	/*
	 * return false so wont remove script
	 */
	@Override
	public boolean logout() {
		/*
		 * only can happen if dungeon is loading and system update happens
		 */
		if (stage != Stages.RUNNING) {
			return false;
		}
		exitCave(0);
		return false;

	}

	public int getCurrentWave() {
		if (getArguments() == null || getArguments().length == 0) {
			return 0;
		}
		return (Integer) getArguments()[0];
	}

	public void setCurrentWave(int wave) {
		if (getArguments() == null || getArguments().length == 0) {
			setArguments(new Object[1]);
		}
		getArguments()[0] = wave;
	}

	@Override
	public void forceClose() {
		/*
		 * shouldnt happen
		 */
		if (stage != Stages.RUNNING) {
			return;
		}
		exitCave(2);
	}
}
