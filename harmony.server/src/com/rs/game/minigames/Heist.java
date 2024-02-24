package com.rs.game.minigames;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.TimerTask;

import com.rs.Settings;
import com.rs.cores.CoresManager;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.player.Equipment;
import com.rs.game.player.Player;
import com.rs.game.player.actions.Summoning;
import com.rs.game.player.content.event.season.SeasonEvent;
import com.rs.game.player.content.pet.Pets;
import com.rs.game.player.controlers.heist.HeistPlaying;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.HeistBags;
import com.rs.utils.HeistGames;

/**
 * 
 * @author Connor
 *
 */

public final class Heist {
	//public static final int CW_TICKET = 4067;
	public static final int SARADOMIN = 0;
	public static final int ZAMORAK = 1;
	private static final int GUTHIX = 2;
	@SuppressWarnings("unchecked")
	private static final List<Player>[] waiting = new List[2];
	@SuppressWarnings("unchecked")
	private static final List<Player>[] playing = new List[2];
	private static int[] seasonWins = new int[2];
	public static final WorldTile LOBBY = new WorldTile(3506, 3313, 0),
			SARA_WAITING = new WorldTile(3140, 3093, 0),
			ZAMO_WAITING = new WorldTile(3081, 3121, 0),
			SARA_BASE = new WorldTile(3463, 3321, 0),
			ZAMO_BASE = new WorldTile(3520, 3276, 0);

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

	public static void viewScoreBoard(Player player) {
		player.getInterfaceManager().sendChatBoxInterface(55);
		player.getPackets().sendIComponentText(55, 1,
				"Saradomin: " + seasonWins[SARADOMIN]);
		player.getPackets().sendIComponentText(55, 2,
				"Zamorak: " + seasonWins[ZAMORAK]);
	}

	public static int getPowerfullestTeam() {
		int zamorak = waiting[ZAMORAK].size() + playing[ZAMORAK].size();
		int saradomin = waiting[SARADOMIN].size() + playing[SARADOMIN].size();
		if (saradomin == zamorak) {
			return GUTHIX;
		}
		if (zamorak > saradomin) {
			return ZAMORAK;
		}
		return SARADOMIN;
	}

	public static void joinPortal(Player player, int team) {
		if (player.getEquipment().getHatId() != -1
				|| player.getEquipment().getCapeId() != -1) {
			player.getPackets().sendGameMessage(
					"You cannot wear hats, capes or helms in the arena.");
			return;
		}
		if (player.getFamiliar() != null || player.getPet() != null || Summoning.hasPouch(player) || Pets.hasPet(player)) {
			player.getDialogueManager().startDialogue("SimpleMessage", "Sorry, pets aren't permitted here.!");
			player.sendMessage("<col=ff0000>You cannot bring familiars in this minigame.");
			return;
		}
		if (player.getSkills().getCombatLevel() < 100) {
			player.sendMessage("You must have a combat level of at least 100 to do this heist.");
			return;
		}
		for (Item item : player.getInventory().getItems().getItems()) {
			if (item == null) {
				continue;
		/*	if (Food.forId(item.getId()) != null) {
				player.getPackets().sendGameMessage("You cannot bring food into the arena.");
				return;
			}*/
			}
		}
		int powerfullestTeam = getPowerfullestTeam();
		if (team == GUTHIX) {
			team = powerfullestTeam == ZAMORAK ? SARADOMIN : ZAMORAK;
		} else if (team == powerfullestTeam) {
			if (team == ZAMORAK) {
				player.getPackets()
				.sendGameMessage(
						"The Zamorak team is powerful enough already! Guthix demands balance - join the Saradomin team instead!");
			} else if (team == SARADOMIN) {
				player.getPackets()
				.sendGameMessage(
						"The Saradomin team is powerful enough already! Guthix demands balance - join the Zamorak team instead!");
			}
			return;
		}
		player.lock(2);
		waiting[team].add(player);
		player.hptracker = false;
		player.getCombatDefinitions().setSpellBook(0);
		setCape(player, new Item(team == ZAMORAK ? 4042 : 4041));
		setHood(player, new Item(team == ZAMORAK ? 4515 : 4513));
		player.getControlerManager().startControler("HeistWaiting", team);
		player.setNextWorldTile(new WorldTile(team == ZAMORAK ? ZAMO_WAITING
				: SARA_WAITING, 1));
		//player.getMusicsManager().playMusic(318); // temp testing else 5
		if (playingGame == null && waiting[team].size() >= 2) {
			// 2players to
			// start
			createPlayingGame();
		}
		else {
			refreshTimeLeft(player);
		// You cannot take non-combat items into the arena
		}
	}

	public static void setHood(Player player, Item hood) {
		player.getEquipment().getItems().set(Equipment.SLOT_HAT, hood);
		player.getEquipment().refresh(Equipment.SLOT_HAT);
		player.getAppearence().generateAppearenceData();
	}

	public static void setCape(Player player, Item cape) {
		player.getEquipment().getItems().set(Equipment.SLOT_CAPE, cape);
		player.getEquipment().refresh(Equipment.SLOT_CAPE);
		player.getAppearence().generateAppearenceData();
	}

	public static void setWeapon(Player player, Item weapon) {
		player.getEquipment().getItems().set(Equipment.SLOT_WEAPON, weapon);
		player.getEquipment().refresh(Equipment.SLOT_WEAPON);
		player.getAppearence().generateAppearenceData();
	}

	public static void createPlayingGame() {
		playingGame = new PlayingGame();
		CoresManager.fastExecutor
		.scheduleAtFixedRate(playingGame, 60000, 60000);
		refreshAllPlayersTime();
	}

	public static void destroyPlayingGame() {
		playingGame.cancel();
		playingGame = null;
		refreshAllPlayersTime();
		leavePlayersSafely();
		World.sendWorldMessage("<col=ff0000> The Heist minigame is now open to play!", false);
	}

	public static void leavePlayersSafely() {
		leavePlayersSafely(-1);
	}

	public static void leavePlayersSafely(final int winner) {
		for (List<Player> element : playing) {
			for (final Player player : element) {
				player.lock(7);
				player.stopAll();
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
								.sendGameMessage("You draw. You have been rewarded 5 Heist points.");
								player.heiststorepoints += Settings.EVENT_SPOTLIGHT.contains("minigame") ? 10 : 5;
								player.heistgamesplayed ++;
								HeistGames.checkRank(player);
								SeasonEvent.HandleActivity(player, "Heist", 600);
							} else if (winner == i) {
								player.getPackets().sendGameMessage("You won. You have been rewarded 10 Heist points.");
								player.heiststorepoints += Settings.EVENT_SPOTLIGHT.contains("minigame") ? 20 : 10;
								player.heistgamesplayed ++;
								HeistGames.checkRank(player);
								SeasonEvent.HandleActivity(player, "Heist", 2000);
							} else {
								player.getPackets()
								.sendGameMessage("You lost. You have been rewarded 3 Heist points.");
								player.heiststorepoints += Settings.EVENT_SPOTLIGHT.contains("minigame") ? 6 : 3;
							player.heistgamesplayed ++;
							HeistGames.checkRank(player);
								SeasonEvent.HandleActivity(player, "Heist", 300);
							}
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
		if (player.getEquipment().getWeaponId() == 23672) {
			Heist.setWeapon(player, null);
		}
		setCape(player, null);
		setHood(player, null);
		player.setNextWorldTile(new WorldTile(LOBBY, 0));
		if (playingGame != null && waiting[team].size() == 0
				&& playing[team].size() == 0)
		 {
			destroyPlayingGame(); // cancels if 0 players playing/waiting on any
		// of the tea
		}
	}

	public static void refreshTimeLeft(Player player) {
		player.getPackets()
		.sendConfig(
				380,
				playingGame == null ? 0
						: playingGame.minutesLeft
						- (player.getControlerManager()
								.getControler() instanceof HeistPlaying ? 5
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
		player.getControlerManager().forceStop();
	}

	public static void removePlayingPlayer(Player player, int team) {
		playing[team].remove(player);
		player.reset();
		player.setCanPvp(false);
		// remove the items
		HeistBags.checkRank(player);
		setCape(player, null);
		setHood(player, null);
		if (player.getEquipment().getWeaponId() == 23672) {
			Heist.setWeapon(player, null);
		}
	/*	int weaponId = player.getEquipment().getWeaponId();
		if (weaponId == 4037 || weaponId == 4039) {
			Heist.setWeapon(player, null);
			Heist.dropFlag(player.getLastWorldTile(),
					weaponId == 4037 ? Heist.SARADOMIN
							: Heist.ZAMORAK);
		}*/
		player.closeInterfaces();
		player.getInventory().deleteItem(23672, Integer.MAX_VALUE); // cash bags

		player.getHintIconsManager().removeUnsavedHintIcon();
		player.getMusicsManager().reset();
		player.setNextWorldTile(new WorldTile(LOBBY, 0));
		if (playingGame != null && waiting[team].size() == 0
				&& playing[team].size() == 0)
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
		player.setCanPvp(true);
		player.getControlerManager().startControler("HeistPlaying", team);
		player.setNextWorldTile(new WorldTile(team == ZAMORAK ? ZAMO_BASE
				: SARA_BASE, 1));
	}

	public static void endGame(int winner) {
		if (winner != -2) {
			seasonWins[winner]++;
		}
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
				player.getMusicsManager().playMusic(i == ZAMORAK ? 845 : 314);
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

	public static void addScore(Player player, int team, int flagTeam) {
		if (playingGame == null) {
			return;
		}
		
		playingGame.addScore(player, team, flagTeam);
	
	}

	private static class PlayingGame extends TimerTask {

		private static final int SAFE = 0, TAKEN = 1, DROPPED = 2;
		private int minutesLeft;
		private int[] score;
		private int[] flagStatus;
		private int[] barricadesCount;
		private final LinkedList<WorldObject> spawnedObjects = new LinkedList<WorldObject>();
		//private final LinkedList<CastleWarBarricade> barricades = new LinkedList<CastleWarBarricade>();

		public PlayingGame() {
			reset();
		}

		public void reset() {
			minutesLeft = 1; // temp testing else 5
			score = new int[2];
			flagStatus = new int[2];
			barricadesCount = new int[2];

		}

	

		public void addScore(Player player, int team, int flagTeam) {
			setWeapon(player, null);
			score[team] += 1;
			refreshAllPlayersPlaying();
		}

		private void makeSafe(int flagTeam) {
			WorldObject flagStand = null;
			for (WorldObject object : spawnedObjects) {
				if (object.getId() == (flagTeam == SARADOMIN ? 4377 : 4378)) {
					flagStand = object;
					break;
				}
			}
			if (flagStand == null) {
				return;
			}
			World.destroySpawnedObject(flagStand, false);
			flagStatus[flagTeam] = SAFE;
			refreshAllPlayersPlaying();
		}

		public void refresh(Player player) {
			player.getPackets().sendConfigByFile(143, flagStatus[SARADOMIN]);
			player.getPackets().sendConfigByFile(145, score[SARADOMIN]);
			player.getPackets().sendConfigByFile(153, flagStatus[ZAMORAK]);
			player.getPackets().sendConfigByFile(155, score[ZAMORAK]);
		}

		@Override
		public void run() {
			minutesLeft--;
			if (minutesLeft == 5) {
				endGame(score[SARADOMIN] == score[ZAMORAK] ? -2
						: score[SARADOMIN] > score[ZAMORAK] ? SARADOMIN
								: ZAMORAK);
				reset();
			} else if (minutesLeft == 0) {
				minutesLeft = 15;
				startGame();
			} else if (minutesLeft > 6) {
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
/*		if (objectId == 4484) { // scoreboard
			player.getDialogueManager().startDialogue(
					new CastleWarsScoreboard());
			return true;
		}*/
		if (objectId == 16050) {
			joinPortal(player, GUTHIX);
			return true;
		}
		/*if (objectId == ID4PORTAL) {
			joinPortal(player, SARADOMIN);
			return true;
		}*/
		return false;
	}

	public static List<Player>[] getPlaying() {
		return playing;
	}
}
