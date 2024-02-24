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
import com.rs.game.item.Item;
import com.rs.game.npc.fightcaves.FightCavesNPC;
import com.rs.game.npc.fightcaves.TzKekCaves;
import com.rs.game.npc.others.TOGNpc;
import com.rs.game.npc.tog.Tog;
import com.rs.game.player.content.event.season.SeasonEvent;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Logger;
import com.rs.utils.Utils;

public class TrialOfTheGods extends Controler {

	public static final WorldTile OUTSIDE = new WorldTile(3244, 3196, 0);

	private static final int TALKER = 251;

	private static final int[] MUSICS = {1088, 1082, 1086};

	public void playMusic() {
		player.getMusicsManager().playMusic(selectedMusic);
	}

	private final int[][] WAVES = {
			{1265}
			,{51,1265}
			,{2881,2882,2883}
			,{1158}
			,{14301}
			,{110}
			,{2745}
			,{6260}
			,{110}
			,{6222}
			,{110}
			,{6247}
			,{110,110}
			,{6203}
			,{110}
			,{9463,110,9463,9463,110}
			,{50,50}
			,{110,110,110,110}
			,{6260,6260}
			,{110,110,110,110}
			,{6222,6222}
			,{110,110,110,110}
			,{6203,6247}
			
	};


	private int[] boundChuncks;
	private Stages stage;
	private boolean logoutAtEnd;
	private boolean login;
	public boolean spawned;
	public int selectedMusic;

/*	public static void enterFightCaves(Player player) {
		/*if(player.getFamiliar() != null || player.getPet() != null || Summoning.hasPouch(player) || Pets.hasPet(player)) {
			player.getDialogueManager().startDialogue("SimpleNPCMessage", TALKER, "No Kimit-Zil in the pits! This is a fight for YOU, not your friends!");
			return;
		}
		player.getControlerManager().startControler("FightCavesControler", 1); //start at wave 1
	}*/

	public static enum Stages {
		LOADING,
		RUNNING,
		DESTROYING
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
		if(interfaceId == 182 && (componentId == 6 || componentId == 13)) {
			if(!logoutAtEnd) {
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
		if(object.getId() == 9357) {
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
		player.lock(); //locks player
		CoresManager.slowExecutor.execute(new Runnable() {
			@Override
			public void run() {
				//finds empty map bounds
				boundChuncks = RegionBuilder.findEmptyChunkBound(8, 8);
				//copys real map into the empty map
				//552 640
				RegionBuilder.copyAllPlanesMap(362, 485, boundChuncks[0], boundChuncks[1], 3);
				//World.spawnNPC(494, new addObject(2868, 2575), 0, false, EntityDirection.WEST);
				//selects a music
				selectedMusic = MUSICS[Utils.random(MUSICS.length)];
				player.setNextWorldTile(!login ? getWorldTile(9, 9) : getWorldTile(12, 12) );
				//1delay because player cant walk while teleing :p, + possible issues avoid
				WorldTasksManager.schedule(new WorldTask()  {
					@Override
					public void run() {
						if(!login) {
							WorldTile walkTo = getWorldTile(32, 32);
							// player.addWalkSteps(walkTo.getX(), walkTo.getY());
						}
						player.getDialogueManager().startDialogue("SimpleNPCMessage", TALKER, "You're on your own now.<br>Goodluck my young padawan!");
						player.setForceMultiArea(true);
						playMusic();
						player.unlock(); //unlocks player
						stage = Stages.RUNNING;
					}

				}, 1);
				if(!login) {
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
		switch(Utils.random(5)) {
		case 0:
			return getWorldTile(9, 9);
		case 1:
			return getWorldTile(10, 10);
		case 2:
			return getWorldTile(10, 9);
		case 3:
			return getWorldTile(9, 10);
		case 4:
		default:
			return getWorldTile(9, 9);
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
		if(currentWave > WAVES.length) {
			win();
			return;
		}
		player.getInterfaceManager().sendTab(player.getInterfaceManager().hasRezizableScreen() ? 11 : 0, 316);
		player.getPackets().sendConfig(639, currentWave);
		if (stage != Stages.RUNNING) {
			return;
		}
		for(int id : WAVES[currentWave-0]) {
			if (id == 2736) {
				new TzKekCaves(id, getSpawnTile());
			} else if (id == 9176) {
				new Tog(id, getSpawnTile(), this);
			} else {
				new TOGNpc(id, getSpawnTile());
			}
		}
		spawned = true;
	}
	
	

	public void spawnHealers() {
		if (stage != Stages.RUNNING) {
			return;
		}
		for (int i = 0; i < 4; i++) {
			new FightCavesNPC(2746, getSpawnTile());
		}
	}

	public void win() {
		if (stage != Stages.RUNNING) {
			return;
		}
		exitCave(4);
	}


	public void nextWave() {
		playMusic();
		setCurrentWave(getCurrentWave()+1);
		if(logoutAtEnd) {
			player.forceLogout();
			return;
		}
		setWaveEvent();
	}

	public void setWaveEvent() {
		if (getCurrentWave() == 22) {
			player.getDialogueManager().startDialogue("SimpleNPCMessage", TALKER, "Make sure you have inventory space, "+player.getUsername()+" or you won't get a reward!");
		}
		if (getCurrentWave() == 23) {
			//	player.getDialogueManager().startDialogue("SimpleNPCMessage", TALKER, "This is the last wave! You can do it "+player.getUsername()+"!!");
			win();
		}
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
		if(spawned) {
			List<Integer> npcs = World.getRegion(player.getRegionId()).getNPCsIndexes();
			if(npcs == null || npcs.isEmpty())  {
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
	 * logout or not. if didnt logout means lost, 0 logout, 1, normal,  2 tele
	 */
	public void exitCave(int type) {
		stage = Stages.DESTROYING;
		WorldTile outside = new WorldTile(OUTSIDE, 2); // radomizes alil
		if (type == 0 || type == 2) {
			removeControler();
			player.setNextWorldTile(outside);
		} else {
			player.setForceMultiArea(false);
			player.getPackets().closeInterface(player.getInterfaceManager().hasRezizableScreen() ?  11 : 0);
			if(type == 1 || type == 4) {
				player.setNextWorldTile(outside);
				if(type == 4) {
					player.togdone = true;
					player.reset();
					player.getDialogueManager().startDialogue("SimpleNPCMessage", TALKER, "Well.. I didn't expect this! Please accept this gift as a reward.");
					player.getPackets().sendGameMessage("You were victorious!!");
				//	SeasonEvent.HandleActivity(player, "Trial of the Gods", 0);
					if(!player.getInventory().addItem(29937, 1 )) {
						World.addGroundItem(new Item(29937, 1), new WorldTile(player), player, true, 180,true);
						World.addGroundItem(new Item(29936, Settings.EVENT_SPOTLIGHT.contains("minigame") ? 12000 : 6000), new WorldTile(player), player, true, 180,true);
					} else if (!player.getInventory().addItem(29936, Settings.EVENT_SPOTLIGHT.contains("minigame") ? 12000 : 6000)) {
						World.addGroundItem(new Item(29936, Settings.EVENT_SPOTLIGHT.contains("minigame") ? 12000 : 6000), new WorldTile(player), player, true, 180, true);
					}
					;
					World.addGroundItem(new Item(29917, 1000), new WorldTile(player), player, true, 180,true);
				} else if (getCurrentWave() == 1) {
					player.getDialogueManager().startDialogue("SimpleNPCMessage", TALKER, "Pha! Typical, another choob.");
				} else {
					//int tokkul = getCurrentWave() * 8032 / WAVES.length;
					//tokkul *= Settings.DROP_RATE; //10x more
					if (!player.getInventory().addItem(29936, 50)) {
						World.addGroundItem(new Item(29936, 50), new WorldTile(player), player, true, 180,true);
					}
					player.getDialogueManager().startDialogue("SimpleNPCMessage", TALKER, "Pha! Typical, another choob.");
					//TODO tokens
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
		return new WorldTile(boundChuncks[0]*8 + mapX, boundChuncks[1]*8 + mapY, 0);
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
