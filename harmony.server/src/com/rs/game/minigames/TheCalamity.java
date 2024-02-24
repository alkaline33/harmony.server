package com.rs.game.minigames;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.TimerTask;

import com.rs.Settings;
import com.rs.cores.CoresManager;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.player.Player;
import com.rs.game.player.actions.Summoning;
import com.rs.game.player.content.bossinstance.BossInstanceManager;
import com.rs.game.player.content.pet.Pets;
import com.rs.game.player.controlers.calamity.CalamityPlaying;
import com.rs.game.player.dialogues.impl.CastleWarsScoreboard;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;

public final class TheCalamity {
	public static final int THEREAPERS = 0;
	public static final int THEGODS = 1;
	private static final int EITHER = 2;
	@SuppressWarnings("unchecked")
	private static final List<Player>[] waiting = new List[2];
	@SuppressWarnings("unchecked")
	private static final List<Player>[] playing = new List[2];
	private static int[] seasonWins = new int[2];
	public static final WorldTile LOBBY = new WorldTile(2832, 3858, 0),
			THEREAPERS_WAITING = new WorldTile(2827, 3849, 0),
			THEGODS_WAITING = new WorldTile(2827, 3849, 0),
			THEGODS_BASE = new WorldTile(120, 4312, 0),
			THEREAPERS_BASE = new WorldTile(116, 4312, 0);
	
	/**
	 * Author - Mr_Joopz - 2015
	 */

	private static PlayingGame playingGame;

	static {
		init();
	}

	public static void init() {
		for (int i = 0; i < waiting.length; i++) {
			waiting[i] = Collections.synchronizedList(new LinkedList<Player>());
		}
		for (int i = 0; i < playing.length; i++) {
			playing[i] = Collections.synchronizedList(new LinkedList<Player>());
		}
	}

	public static int getPowerfullestTeam() {
		int thegods = waiting[THEGODS].size() + playing[THEGODS].size();
		int thereapers = waiting[THEREAPERS].size() + playing[THEREAPERS].size();
		if (thereapers == thegods) {
			return EITHER;
		}
		if (thegods > thereapers) {
			return THEGODS;
		}
		return THEREAPERS;
	}

	public static void joinPortal(Player player, int team) {
		if (player.getSkills().getCombatLevel() < 100) {
			player.getDialogueManager().startDialogue("SimpleMessage", "You need at least 100 combat level to enter this minigame.");
			return;
		} else if (player.getInventory().getFreeSlots() < 28 || player.getEquipment().wearingArmour()) {
			player.getDialogueManager().startDialogue("SimpleMessage", "You cannot take items into this minigame!");
			return;
		} else if (!player.canSpawn() || World.isNopeArea(player) || player.getControlerManager().getControler() != null || BossInstanceManager.SINGLETON.inInstance(player)) {
			player.getDialogueManager().startDialogue("SimpleMessage", "You cannot this minigame at the moment.");
			return;
		} else if (player.getFamiliar() != null || player.getPet() != null || Summoning.hasPouch(player) || Pets.hasPet(player)) {
				player.getDialogueManager().startDialogue("SimpleMessage", "Sorry, pets aren't permitted here.!");
				return;
}
		if (waiting[THEGODS].size() >= 3 && waiting[THEREAPERS].size() >= 3) {
			player.sendMessage("Sorry but the teams are currently full. Try again later.");
			return;
		}
		if (playing[THEGODS].size() >= 1 || playing[THEREAPERS].size() >= 1) {
			player.sendMessage("Sorry but there is currently a game in progress. Watch out for a new game announcement.");
			player.sendMessage("Or return within "+playingGame.minutesLeft+" minutes.");
			return;
		}
		player.hptracker = false;
		int powerfullestTeam = getPowerfullestTeam();
		if (team == EITHER) {
			team = powerfullestTeam == THEGODS ? THEREAPERS : THEGODS;
		} else if (team == powerfullestTeam) {
			if (team == THEGODS || waiting[team].size() >= 1) {
				player.getPackets()
				.sendGameMessage(
						"The THEGODS team is powerful enough already!");
			} else if (team == THEREAPERS || waiting[team].size() >= 1) {
				player.getPackets()
				.sendGameMessage(
						"The THEREAPERS team is powerful enough already!");
			}
			return;
		}
		player.lock(2);
		waiting[team].add(player);
		player.getCombatDefinitions().setSpellBook(0);
		player.getControlerManager().startControler("CalamityWaiting", team);
		player.setNextWorldTile(new WorldTile(team == THEGODS ? THEGODS_WAITING
				: THEREAPERS_WAITING, 1));
		player.getMusicsManager().playMusic(318); // temp testing else 5
		if (playingGame == null && waiting[team].size() >= 1) {
			// 2players to
			// start
			createPlayingGame();
		}
		else {
			refreshTimeLeft(player);
		// You cannot take non-combat items into the arena
		}
	}

	public static void createPlayingGame() {
		playingGame = new PlayingGame();
		CoresManager.fastExecutor.scheduleAtFixedRate(playingGame, 60000, 60000);
		refreshAllPlayersTime();
	}

	public static void destroyPlayingGame() {
		playingGame.cancel();
		playingGame = null;
		refreshAllPlayersTime();
		leavePlayersSafely();
		World.sendWorldMessage("<col=ff0000> The Calamity now has space for a new game!", false);
	}

	public static void leavePlayersSafely() {
		leavePlayersSafely(-1);
	}

	public static void leavePlayersSafely(final int winner) {
		for (List<Player> element : playing) {
			for (final Player player : element) {
				player.lock(7);
				player.stopAll();
				Settings.CALANOENTER = false;
				player.doublekillpointscalamity = false;
				player.getInventory().reset();
				player.getEquipment().reset();
				player.getInventory().refresh();
				player.getEquipment().refresh();
				player.calamitykillpoints = 0;
			}
		}
		WorldTasksManager.schedule(new WorldTask() {
			@Override
			public void run() {
				for (int i = 0; i < playing.length; i++) {
					for (Player player : playing[i]
							.toArray(new Player[playing[i].size()])) {
						forceRemovePlayingPlayer(player);
						if (winner != -1) {
							if (winner == -2) {
								player.getPackets()
								.sendGameMessage("Well done!");
								player.getInventory().reset();
								player.getEquipment().reset();
								player.getInventory().refresh();
								player.getEquipment().refresh();
								player.doublekillpointscalamity = false;
								player.calamitykillpoints = 0;
								//player.getInventory().addItem(CW_TICKET, 1);
							} else if (winner == i) {
								player.getPackets().sendGameMessage("Well done!");
								player.getInventory().reset();
								player.getEquipment().reset();
								player.getInventory().refresh();
								player.getEquipment().refresh();
								player.doublekillpointscalamity = false;
								player.calamitykillpoints = 0;
								//player.getInventory().addItem(CW_TICKET, 2);
							} else {
								player.getPackets()
								.sendGameMessage("Well done!");
							}
							player.getInventory().reset();
							player.getEquipment().reset();
							player.getInventory().refresh();
							player.getEquipment().refresh();
							player.doublekillpointscalamity = false;
							player.calamitykillpoints = 0;
						}
					}
				}
			}
		}, 6);
	}

	// unused
	public static void forceRemoveWaitingPlayer(Player player) {
		player.getControlerManager().forceStop();
	}

	public static void removeWaitingPlayer(Player player, int team) {
		waiting[team].remove(player);
		player.getInventory().reset();
		player.getEquipment().reset();
		player.getInventory().refresh();
		player.getEquipment().refresh();
		player.calamitykillpoints = 0;
		player.setNextWorldTile(new WorldTile(LOBBY, 2));
		if (playingGame != null && waiting[team].size() == 0
				&& playing[team].size() == 0)
		 {
			destroyPlayingGame(); // cancels if 0 players playing/waiting on any
		// of the tea
		}
	}

	public static void refreshTimeLeft(Player player) {
		player.getPackets().sendConfig(380, playingGame == null ? 0 : playingGame.minutesLeft
						- (player.getControlerManager()
								.getControler() instanceof CalamityPlaying ? 3
										: 0));
	}

	public static void startGame() {
		for (int i = 0; i < waiting.length; i++) {
			for (Player player : waiting[i].toArray(new Player[waiting[i]
					.size()])) {
				joinPlayingGame(player, i);
			}
		}
	}

	public static void forceRemovePlayingPlayer(Player player) {
		player.getInventory().reset();
		player.getEquipment().reset();
		player.getInventory().refresh();
		player.getEquipment().refresh();
		player.doublekillpointscalamity = false;
		player.calamitykillpoints = 0;
		player.getControlerManager().forceStop();
		
	}

	public static void removePlayingPlayer(Player player, int team) {
		playing[team].remove(player);
		player.reset();
		// remove the items
		int weaponId = player.getEquipment().getWeaponId();
		player.closeInterfaces();
		player.getInventory().reset();
		player.getEquipment().reset();
		player.getInventory().refresh();
		player.getEquipment().refresh();
		player.calamitykillpoints = 0;
		player.doublekillpointscalamity = false;
		player.getHintIconsManager().removeUnsavedHintIcon();
		player.getMusicsManager().reset();
		player.setNextWorldTile(new WorldTile(LOBBY, 2));
		if (playingGame != null && waiting[team].size() == 0
				&& playing[THEREAPERS].size() == 0 && playing[THEGODS].size() == 0)
		 {
			destroyPlayingGame(); // cancels if 0 players playing/waiting on any
		// of the tea
		}
	}

	public static void joinPlayingGame(Player player, int team) {
		playingGame.refresh(player);
		waiting[team].remove(player);
		player.getControlerManager().removeControlerWithoutCheck();
		player.getPackets().closeInterface(player.getInterfaceManager().hasRezizableScreen() ?  34 : 0);
		playing[team].add(player);
		player.getControlerManager().startControler("CalamityPlaying", team);
		player.setNextWorldTile(new WorldTile(team == THEGODS ? THEGODS_BASE
				: THEREAPERS_BASE, 1));
		Settings.CALANOENTER = true;
	}

	public static void endGame(int winner) {
		leavePlayersSafely(winner);
	}

	public static void refreshAllPlayersTime() {
		for (List<Player> element : waiting) {
			for (Player player : element) {
				refreshTimeLeft(player);
			}
		}
		for (int i = 0; i < playing.length; i++) {
			for (Player player : playing[i]) {
				player.getMusicsManager().playMusic(i == THEGODS ? 845 : 314);
				refreshTimeLeft(player);
			}
		}
	}

	public static void refreshAllPlayersPlaying() {
		for (List<Player> element : playing) {
			for (Player player : element) {
				playingGame.refresh(player);
			}
		}
	}

	public static void addHintIcon(int team, Player target) {
		for (Player player : playing[team]) {
			player.getHintIconsManager().addHintIcon(target, 0, -1, false);
		}
	}

	public static void removeHintIcon(int team) {
		for (Player player : playing[team]) {
			player.getHintIconsManager().removeUnsavedHintIcon();
		}
	}

	private static class PlayingGame extends TimerTask {

		private static final int SAFE = 0, TAKEN = 1, DROPPED = 2;
		private int minutesLeft;
		private int[] score;
		private int[] flagStatus;
		private int[] barricadesCount;
	//	private final LinkedList<WorldObject> spawnedObjects = new LinkedList<WorldObject>();
		//private final LinkedList<CastleWarBarricade> barricades = new LinkedList<CastleWarBarricade>();

		public PlayingGame() {
			reset();
		}

		public void reset() {
			minutesLeft = 3; // temp testing else 5
			for (NPC n : World.getNPCs()) {
				if (n == null || n.getId() < 30200 || n.getId() > 30215 || n.getId() == 12878) {
					continue;
				}
				n.sendDeath(n);
			}
			//score = new int[2];
		}

		public void refresh(Player player) {
		}

		@Override
		public void run() {
			minutesLeft--;
			if (minutesLeft == 3) {
				reset();
			} else if (minutesLeft == 0) {
				minutesLeft = 180;
				startGame();
			} else if (minutesLeft > 1 && Settings.CALANOENTER != true) {
				startGame();
			}
			refreshAllPlayersTime();
		}
	}

	public static void handleInterfaces(Player player, int interfaceId,
			int componentId) {
		if (interfaceId == 55) {
			if (componentId == 9) {
				player.closeInterfaces();
			}
		}
	}

	public static boolean handleObjects(Player player, int objectId) {
		if (objectId == 4484) { // scoreboard
			player.getDialogueManager().startDialogue(
					new CastleWarsScoreboard());
			return true;
		}
		//if (objectId == 4388) {
		//	joinPortal(player, THEGODS);
		//	return true;
		//}
		if (objectId == 42031) {
//			player.sendMessage("Calamity is currently unavailble.");
			joinPortal(player, EITHER);
			return true;
		}
		//if (objectId == 4387) {
		//	joinPortal(player, THEREAPERS);
		//	return true;
		//}
		return false;
	}

	public static List<Player>[] getPlaying() {
		return playing;
	}
}
