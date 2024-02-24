/*package com.rs.game.map.bossInstance.impl;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.ForceMovement;
import com.rs.game.Graphics;
import com.rs.game.Hit;
import com.rs.game.Projectile;
import com.rs.game.Hit.HitLook;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.map.bossInstance.BossInstance;
import com.rs.game.map.bossInstance.InstanceSettings;
import com.rs.game.npc.vorago.Vorago;
import com.rs.game.npc.vorago.VoragoFace;
import com.rs.game.player.Player;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

public class VoragoInstance extends BossInstance {

	public static WorldTile PHASE_1 = new WorldTile(3104, 6112, 0), PHASE_2 = new WorldTile(3040, 6048, 0),
			PHASE_3 = new WorldTile(3104, 6048, 0), PHASE_4 = new WorldTile(3040, 5984, 0),
			PHASE_5 = new WorldTile(3104, 5984, 0);

	private transient List<Player> acceptedChallenge;
	private transient List<Player> playersOnBattle;
	private transient VoragoFace voragoFace;
	private transient Vorago vorago;
	private transient WorldObject jumpGap;
	private transient WorldObject exitSphere;
	public boolean startAttack;
	public boolean[] arttributes = new boolean[1];// 0 - Jumping Started
	public int START_HIT_DAMAGE = 2000;

	public VoragoInstance(Player owner, InstanceSettings settings) {
		super(owner, settings);
		acceptedChallenge = new CopyOnWriteArrayList<Player>();
		playersOnBattle = new CopyOnWriteArrayList<Player>();
	}

	public void sendChallenge(Player challenger) {
		startAttack = true;
		sendStartAttack();
		acceptedChallenge.add(challenger);
		challenger.getPackets()
				.sendGameMessage("<col=EE7600>Vorago accepted your challenge and begins to charge a massive attack.");
		for (Player reciever : getPlayers()) {
			if (reciever == null || reciever == challenger || !reciever.hasSpokenToVorago()
					|| !isInChallengeArea(reciever) || reciever.getInterfaceManager().containsScreenInter())
				continue;
			reciever.lock();
			reciever.stopAll();
			reciever.getDialogueManager().startDialogue(new Dialogue() {
				@Override
				public void start() {
					this.sendOptionsDialogue(
							challenger.getDisplayName()
									+ " HAS CHALLENGED VORAGO<br>ARE YOU WILLING TO FACE THE TEST WITH THEM?",
							"Yes", "No");
				}

				@Override
				public void run(int interfaceId, int componentId) {
					if (componentId == OPTION_1) {
						acceptedChallenge.add(player);
						player.getPackets().sendGameMessage(
								"<col=EE7600>Vorago accepted your challenge and begins to charge a massive attack.");
					}
					end();
				}

				@Override
				public void finish() {
					player.unlock();
				}
			});
		}
	}

	private void sendStartAttack() {
		WorldTasksManager.schedule(new WorldTask() {
			int count = 0;

			@Override
			public void run() {
				if (acceptedChallenge.isEmpty()) {
					stop();
					return;
				}
				if (count == 16) {
					World.sendGraphics(voragoFace, new Graphics(4033, 1, 1, 2), getTile(new WorldTile(3040, 6122, 0)));
				} else if (count == 17) {
					int damage = (START_HIT_DAMAGE / acceptedChallenge.size());
					for (Player player : acceptedChallenge) {
						if (player == null)
							continue;
						player.faceEntity(voragoFace);
						if (damage >= player.getHitpoints())
							player.sendDeath(voragoFace);
						else {
							WorldTile toTile = getTile(
									new WorldTile(3030 + Utils.random(8), 6118 - Utils.random(3), 0));
							player.setNextForceMovement(new ForceMovement(toTile, 1, ForceMovement.NORTH));
							player.setNextAnimation(new Animation(20338));
							player.setNextGraphics(new Graphics(4034));
							player.applyHit(new Hit(voragoFace, damage, HitLook.REGULAR_DAMAGE));
						}
					}
				} else if (count == 18) {
					if (startAttack && acceptedChallenge.size() > 0)
						enterBattle();
					stop();
				}
				count++;
			}
		}, 0, 1);
	}

	public void enterBattle() {
		voragoFace.setBattleInGoing(true);
		VoragoInstance instance = this;
		WorldTasksManager.schedule(new WorldTask() {
			int count = 0;

			@Override
			public void run() {
				switch (count) {
				case 0:
					for (Player player : acceptedChallenge) {
						if (player == null || player.isDead())
							continue;
						player.setNextWorldTile(randomSpawnTile(player, getVoragoSpawnLocation(1), 5));
						player.setNextAnimation(new Animation(20401));
						player.setForceMultiArea(true);
						acceptedChallenge.remove(player);
						playersOnBattle.add(player);
					}
					break;
				case 9:
					if (playersOnBattle.isEmpty()) {
						voragoFace.setBattleInGoing(false);
						stop();
						break;
					}
					vorago = new Vorago(17182, getVoragoSpawnLocation(1), -1, true, instance);
					vorago.setNextAnimation(new Animation(20367));
					break;
				case 10:
					stop();
					break;
				}
				count++;
			}
		}, 0, 1);
	}

	public WorldTile randomSpawnTile(Entity target, WorldTile checkTile, int randomizeSize) {
		WorldTile teleTile = null;
		// attemps to randomize tile by 4x4 area
		int size = target == null ? 1 : target.getSize();
		for (int trycount = 0; trycount < 10; trycount++) {
			teleTile = new WorldTile(checkTile, randomizeSize);
			if (!ignoreTile(teleTile) || World.isTileFree(checkTile.getPlane(), teleTile.getX(), teleTile.getY(), size))
				break;
			teleTile = checkTile;
		}
		return teleTile;
	}

	public boolean ignoreTile(WorldTile tile) {
		if (vorago != null) {
			int phase = vorago.getPhase();
			WorldTile voragoL = getVoragoSpawnLocation(phase);
			WorldTile center = new WorldTile(voragoL.getX() + 2, voragoL.getY() + 2, voragoL.getPlane());
			boolean insidePhaseArea = phase != 5
					? (tile.getX() >= (center.getX() - 12) && tile.getX() <= (center.getX() + 11)
							&& tile.getY() >= (center.getY() - 12) && tile.getY() <= (center.getY() + 11))
					: ((tile.getX() >= (center.getX() - 13) && tile.getX() <= (center.getX() + 10)
							&& tile.getY() >= (center.getY() - 2) && tile.getY() <= (center.getY() + 2)));
			return !insidePhaseArea;
		}
		return false;
	}

	public WorldTile getSimilarCoords(WorldTile entity, int nextphase) {
		switch (nextphase) {
		case 2:
			return new WorldTile(entity.getX() - 64, entity.getY() - 64, 0);
		case 3:
			return new WorldTile(entity.getX() + 64, entity.getY(), 0);
		case 4:
			return new WorldTile(entity.getX() - 64, entity.getY() - 64, 0);
		}
		WorldTile checkTile = new WorldTile(3095, 5985, 0);
		WorldTile teleTile = null;
		for (int trycount = 0; trycount < 10; trycount++) {
			teleTile = new WorldTile(checkTile, 2);
			if (World.isTileFree(checkTile.getPlane(), teleTile.getX(), teleTile.getY(), 1))
				break;
			teleTile = checkTile;
		}
		return getTile(teleTile);
	}

	public WorldTile getVoragoSpawnLocation(int phase) {
		switch (phase) {
		case 1:
			return getTile(new WorldTile(3102, 6110, 0));
		case 2:
			return getTile(new WorldTile(3038, 6046, 0));
		case 3:
			return getTile(new WorldTile(3102, 6046, 0));
		case 4:
			return getTile(new WorldTile(3038, 5982, 0));
		case 5:
		default:
			return getTile(new WorldTile(3102, 5982, 0));
		}
	}

	@Override
	public int[] getMapSize() {
		return new int[] { 2, 3 };
	}

	@Override
	public int[] getMapPos() {
		return new int[] { 376, 744 };
	}

	@Override
	public void loadMapInstance() {
		voragoFace = new VoragoFace(17162, getTile(new WorldTile(3038, 6128, 0)), -1, false, this);
		jumpGap = new WorldObject(84825, 10, 0, getTile(new WorldTile(3099, 6123, 0)));
		World.spawnObject(jumpGap);
	}

	public Vorago getVorago() {
		return vorago;
	}

	public VoragoFace getVoragoFace() {
		return voragoFace;
	}

	public List<Player> getAcceptedChallenge() {
		return acceptedChallenge;
	}

	public List<Player> getPlayersOnBattle() {
		return playersOnBattle;
	}

	public boolean ChallengeStarted() {
		return acceptedChallenge.size() != 0;
	}

	public boolean isInChallengeArea(Player player) {
		return (player.getX() >= (voragoFace.getX() - 19) && player.getX() <= (voragoFace.getX() + 19)
				&& player.getY() >= (voragoFace.getY() - 14) && player.getY() <= (voragoFace.getY() + 9));
	}

	
	 * public boolean isInBoreHoleArea(Player player) { return (player.getX() >=
	 * 3012 && player.getX() <= 3062 && player.getY() >= 6087 && player.getY()
	 * <= 6133); }
	 

	public void removePlayer(Player player, int type) {
		if (acceptedChallenge.contains(player))
			acceptedChallenge.remove(player);
		if (type != BossInstance.LOGGED_OUT)
			this.startAttack = false;
		if (isPublic()) {
			if (type == BossInstance.LOGGED_OUT) {
				getPlayers().remove(player);
			} else if (type != BossInstance.DIED)
				leaveInstance(player, type);
		} else {
			player.getControlerManager().removeControlerWithoutCheck();
			if (type != BossInstance.DIED)
				leaveInstance(player, type);
		}
	}

	public void removePlayerFromBattle(Player player, int type) {
		playersOnBattle.remove(player);
		player.setForceMultiArea(false);
		if (isPublic()) {
			if (type == BossInstance.LOGGED_OUT) {
				player.setLocation(randomSpawnTile(player, getTile(new WorldTile(3047, 6121, 0)), 2));
			} else if (type != BossInstance.DIED) {
				player.setNextWorldTile(randomSpawnTile(player, getTile(new WorldTile(3047, 6121, 0)), 2));
			}
		} else {
			if (type == BossInstance.LOGGED_OUT) {
				player.getControlerManager().removeControlerWithoutCheck();
				leaveInstance(player, type);
			} else if (type != BossInstance.DIED) {
				player.setNextWorldTile(randomSpawnTile(player, getTile(new WorldTile(3047, 6121, 0)), 2));
			}
		}
		if (playersOnBattle.isEmpty()) {
			if (vorago != null) {
				vorago.setFinished(true);
				vorago = null;
			}
			if (exitSphere != null) {
				World.removeObject(exitSphere);
				exitSphere = null;
			}
			voragoFace.setBattleInGoing(false);
		}
	}

	public void finishPhase(Player player) {
		vorago.setPhaseProgress(
				vorago.getPhase() == 1 ? 1 : vorago.getPhase() == 2 ? 5 : vorago.getPhase() == 4 ? 3 : 0);
		vorago.sendDeath(player);
	}

	public boolean playerIsOnBattle(Player player) {
		if (voragoFace == null || playersOnBattle == null)
			return false;
		return voragoFace.isBattleInGoing() && playersOnBattle.contains(player);
	}

	@Override
	public void finish() {
		if (!isPublic()) {
			World.removeNPC(voragoFace);
			World.removeObject(jumpGap);
		}
		super.finish();
	}

	public static void sendMessage(Player player, int border, String message) {
		player.getPackets().sendGameMessage(message);
	}

	public static void sendMessage(Player player, int border, String message, boolean sendGameMessage) {
		if (sendGameMessage)
			player.getPackets().sendGameMessage(message);
	}

	public void spawnExitSphere() {
		Projectile projectile = World.sendProjectileNew(getTile(new WorldTile(3112, 5984, 0)),
				getTile(new WorldTile(3105, 5985, 0)), 4029, 0, 30, 10, 0.2, 30, 1);
		int cycleTime = Utils.projectileTimeToCycles(projectile.getEndTime()) - 1;
		exitSphere = new WorldObject(84960, 10, 0, getTile(new WorldTile(3105, 5985, 0)));
		WorldTasksManager.schedule(new WorldTask() {
			@Override
			public void run() {
				World.spawnObject(exitSphere);
			}
		}, cycleTime);
	}

	public void handleExitSphereClick(Player player) {
		player.lock();
		Projectile projectile = World.sendProjectileNew(getTile(new WorldTile(3105, 5985, 0)), player, 4029, 0, 20, 10,
				0.5, 30, 1);
		int cycleTime = Utils.projectileTimeToCycles(projectile.getEndTime()) - 1;
		WorldTasksManager.schedule(new WorldTask() {
			@Override
			public void run() {
				removePlayerFromBattle(player, BossInstance.EXITED);
				player.unlock();
			}
		}, cycleTime);
	}
}*/