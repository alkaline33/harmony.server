package com.rs.game.player.content.raids;

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
import com.rs.game.player.content.pet.Pets;
import com.rs.game.player.content.raids.trioboss.TrioPlaying;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

public final class TheTrioWaiting {
	public static final int TRIO1 = 0;
	public static final int TRIO2 = 1;
	private static final int EITHER = 2;
	@SuppressWarnings("unchecked")
	private static final List<Player>[] waiting = new List[2];
	@SuppressWarnings("unchecked")
	private static final List<Player>[] playing = new List[2];
	private static int[] seasonWins = new int[2];
	public static final WorldTile LOBBY = new WorldTile(Settings.RESPAWN_PLAYER_LOCATION),
			TRIO2_WAITING = new WorldTile(2810, 10266, 0),
			TRIO1_WAITING = new WorldTile(2810, 10266, 0),
			TRIO1_BASE = new WorldTile(2910, 9904, 0),
			TRIO2_BASE = new WorldTile(2910, 9904, 0);
	
	/**
	 * Author - Mr_Joopz - 2015
	 */

	private static PlayingGame playingGame;

	static {
		init();
	}

	public static void init() {
		for (int i = 0; i < waiting.length; i++)
			waiting[i] = Collections.synchronizedList(new LinkedList<Player>());
		for (int i = 0; i < playing.length; i++)
			playing[i] = Collections.synchronizedList(new LinkedList<Player>());
	}

	public static int getPowerfullestTeam() {
		int thetrio1 = waiting[TRIO1].size() + playing[TRIO1].size();
		int thetrio2 = waiting[TRIO2].size() + playing[TRIO2].size();
		if (thetrio2 == thetrio1)
			return EITHER;
		if (thetrio1 > thetrio2)
			return TRIO1;
		return TRIO2;
	}

	public static void joinPortal(Player player, int team) {
		if (player.BossKills() < 100) {
			player.getDialogueManager().startDialogue("SimpleMessage", "You need at least 100 boss kills to access this.");
			player.sendMessage("You must have at least 100 boss kills to participate in this raid.");
			return;
		} else if (player.getFamiliar() != null || player.getPet() != null || Summoning.hasPouch(player) || Pets.hasPet(player)) {
				player.getDialogueManager().startDialogue("SimpleMessage", "Sorry, pets aren't permitted here.!");
				player.sendMessage("No familiars are allowed here.");
				return;
		}
		if (player.raidsfee < 1)
			player.raidsfee = 1;
		int fee = 500000 * player.raidsfee;
		if (player.getMoneyPouch().getCoinAmount() < fee) {
			player.sendMessage("You must have " + Utils.format(fee) + " coins in your money pouch to pay the entrance fee!");
			return;
		}
		if (waiting[TRIO1].size() + waiting[TRIO2].size() >= 3) {
			player.sendMessage("Sorry but the teams are currently full. Try again later.");
			return;
		}
		if (playing[TRIO1].size() >= 1 || playing[TRIO2].size() >= 1) {
			player.sendMessage("Sorry but there is currently a game in progress.");
			return;
		}
		player.sendMessage("You've done "+player.raidedtimestoday+" raids today.");
		int powerfullestTeam = getPowerfullestTeam();
		if (team == EITHER) {
			team = powerfullestTeam == TRIO1 ? TRIO2 : TRIO1;
		} else if (team == powerfullestTeam) {
			if (team == TRIO1 || waiting[team].size() >= 3)
				player.getPackets()
				.sendGameMessage(
						"This team is powerful enough already!");
			else if (team == TRIO2 || waiting[team].size() >= 1)
				player.getPackets()
				.sendGameMessage(
						"This team is powerful enough already!");
			return;
		}
		player.coinamount -= fee;
		Settings.GpSyncAmount += fee;
		player.refreshMoneyPouch();
		player.sendMessage("You pay a non-refundable fee of " + Utils.format(fee) + " coins to enter.");
		player.lock(2);
		waiting[team].add(player);
		player.getCombatDefinitions().setSpellBook(0);
		player.getControlerManager().startControler("TrioWaitingC", team);
		player.setNextWorldTile(new WorldTile(team == TRIO1 ? TRIO1_WAITING
				: TRIO2_WAITING, 1));
		player.getMusicsManager().playMusic(318); // temp testing else 5
		if (playingGame == null && waiting[TRIO1].size() + waiting[TRIO2].size() >= 3) // at least
			createPlayingGame();
		else
			refreshTimeLeft(player);
	}

	public static void createPlayingGame() {
		playingGame = new PlayingGame();
		CoresManager.fastExecutor
		.scheduleAtFixedRate(playingGame, 60000, 60000);
		refreshAllPlayersTime();
	}

	public static void destroyPlayingGame() {
		for (NPC n : World.getNPCs()) {
			if (n == null || n.getId() < 30078 || n.getId() > 30080)
				continue;
			n.sendDeath(n);
		}
		Settings.TRIOBUSH = false;
		Settings.TRIOLEVER = false;
		Settings.TRIORAIDTASKACTIVE = false;
		Settings.TRIOVINE = false;
		Settings.TRIOCANENTER = true;
		Settings.TRIONPCLOAD = false;
		playingGame.cancel();
		playingGame = null;
		refreshAllPlayersTime();
		leavePlayersSafely();
		World.sendWorldMessage("<col=ff0000> The Trio raid now has space for a new game!", false);
	}

	public static void leavePlayersSafely() {
		leavePlayersSafely(-1);
	}

	public static void leavePlayersSafely(final int winner) {
		for (int i = 0; i < playing.length; i++) {
			for (final Player player : playing[i]) {
				player.lock(7);
				player.stopAll();
			}
		}
		WorldTasksManager.schedule(new WorldTask() {
			@Override
			public void run() {
				for (int i = 0; i < playing.length; i++)
					for (Player player : playing[i]
							.toArray(new Player[playing[i].size()])) {
						forceRemovePlayingPlayer(player);
						if (winner != -1) {
							if (winner == -2) {
								player.getPackets()
								.sendGameMessage("");
								
							} else if (winner == i) {
								player.getPackets().sendGameMessage("");
								
							} else
								player.getPackets()
								.sendGameMessage("");
						
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
		player.setNextWorldTile(new WorldTile(LOBBY, 2));
		if (playingGame != null && waiting[team].size() == 0
				&& playing[team].size() == 0)
			destroyPlayingGame(); // cancels if 0 players playing/waiting on any
		// of the tea
	}

	public static void refreshTimeLeft(Player player) {
		player.getPackets().sendConfig(380, playingGame == null ? 0 : playingGame.minutesLeft
				- (player.getControlerManager()
						.getControler() instanceof TrioPlaying ? 10
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
		
	}

	public static void removePlayingPlayer(Player player, int team) {
		playing[team].remove(player);
		player.getHintIconsManager().removeUnsavedHintIcon();
		player.getMusicsManager().reset();
		player.setNextWorldTile(new WorldTile(LOBBY, 2));
		if (playingGame != null && waiting[team].size() == 0
				&& playing[TRIO2].size() == 0 && playing[TRIO1].size() == 0)
			destroyPlayingGame(); // cancels if 0 players playing/waiting on any
		
	}

	public static void joinPlayingGame(Player player, int team) {
		if (player.lootshareEnabled() == true) {
			player.toggleLootShare();
		}
		playingGame.refresh(player);
		waiting[team].remove(player);
		player.getControlerManager().removeControlerWithoutCheck();
		playing[team].add(player);
		player.raidedtimestoday ++;
		player.raidsfee++;
		player.getControlerManager().startControler("TrioPlaying", team);
		player.setNextWorldTile(new WorldTile(team == TRIO1 ? TRIO1_BASE
				: TRIO2_BASE, 1));
		if (team == 1) {
			List<Integer> npcs = World.getRegion(player.getRegionId()).getNPCsIndexes();
			if(npcs == null || npcs.isEmpty() || npcs.size() == 1)  {
				World.spawnNPC(30078, new WorldTile(2908, 9908, 0), -1, true);
				World.spawnNPC(30079, new WorldTile(2910, 9909, 0), -1, true);
				World.spawnNPC(30080, new WorldTile(2912, 9907, 0), -1, true);
			}
		}
		Settings.TRIOCANENTER = false;
	}

	public static void endGame(int winner) {
		leavePlayersSafely(winner);
	}

	public static void refreshAllPlayersTime() {
		for (int i = 0; i < waiting.length; i++)
			for (Player player : waiting[i])
				refreshTimeLeft(player);
		for (int i = 0; i < playing.length; i++)
			for (Player player : playing[i]) {
				player.getMusicsManager().playMusic(i == TRIO1 ? 845 : 314);
				refreshTimeLeft(player);
			}
	}

	public static void refreshAllPlayersPlaying() {
		for (int i = 0; i < playing.length; i++)
			for (Player player : playing[i])
				playingGame.refresh(player);
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
				if (n == null || n.getId() < 30078 || n.getId() > 30079 || n.getId() == 30080)
					continue;
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
			} else if (minutesLeft > 1) // adds ppl waiting on lobby
				startGame();
			refreshAllPlayersTime();
		}
	}

	public static void handleInterfaces(Player player, int interfaceId,
			int componentId) {
		if (interfaceId == 55) {
			if (componentId == 9)
				player.closeInterfaces();
		}
	}

	public static boolean handleObjects(Player player, int objectId) {
		//if (objectId == 4388) {
		//	joinPortal(player, THEGODS);
		//	return true;
		//}
		if (objectId == 1) {
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
