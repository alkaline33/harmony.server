/*package com.rs.game.npc.vorago;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

import com.rs.Settings;
import com.rs.cache.loaders.AnimationDefinitions;
import com.rs.cache.loaders.ItemDefinitions;
import com.rs.cores.CoresManager;
import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.ForceMovement;
import com.rs.game.ForceTalk;
import com.rs.game.Graphics;
import com.rs.game.Hit;
import com.rs.game.Hit.HitLook;
import com.rs.game.Projectile;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.item.FloorItem;
import com.rs.game.item.Item;
import com.rs.game.map.bossInstance.impl.VoragoInstance;
import com.rs.game.npc.Drop;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.player.Equipment;
import com.rs.game.player.Player;
import com.rs.game.player.actions.PlayerCombat;
import com.rs.game.player.content.Combat;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.NPCDrops;
import com.rs.utils.Utils;

@SuppressWarnings("serial")
public class Vorago extends NPC {// 89150

	private transient VoragoInstance instance;
	private int phase, phaseProgress, attackProgress, damageWhileDown, bringHimPoints;
	private transient Player targetedPlayer;
	private transient CopyOnWriteArrayList<Player> phaseKillers;
	private WorldObject gravityField;
	public FloorItem[] weaponPieces;
	private long damageTime;
	private int losenWeaponPieceStage;
	private WorldObject[] ceilingCollapses;
	private WorldObject[] mists;
	private WorldObject[] teamSplitSquares;
	private int greenBombBounces;
	private transient Player greenBombP;
	private Scopulus[] scopuli;
	private StoneClone[] stoneClones;
	private Vitalis[] vitali;
	private TheEndBomb[] theEndBombs;
	private WorldTile theEndFaceTile;
	private int theEndStartIndex, theEndCycles, damageReduction;
	private List<WorldTile> unWalkAbleSpots;
	private WorldObject waterFall;
	public static int[] DEFENSIVE_ABILITY_IDS = { 4, 7, 10, 12, 14 };

	public static int[] VORAGOES = { 17182, 17183, 17184 };
	public WorldTile[] locations = { new WorldTile(3095, 6120, 0), new WorldTile(3104, 6120, 0),
			new WorldTile(3112, 6120, 0), new WorldTile(3095, 6111, 0), new WorldTile(3104, 6111, 0),
			new WorldTile(3112, 6111, 0), new WorldTile(3095, 6103, 0), new WorldTile(3104, 6103, 0),
			new WorldTile(3112, 6103, 0) };

	public Vorago(int id, WorldTile tile, int mapAreaNameHash, boolean canBeAttackFromOutOfArea,
			VoragoInstance instance) {
		super(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea);
		this.instance = instance;
		getTemporaryAttributtes().put("VoragoType", 1);
		setCantFollowUnderCombat(true);
		setForceFollowClose(false);
		setIntelligentRouteFinder(true);
		setNoDistanceCheck(true);
		setCapDamage(1200);
		setForceMultiArea(true);
		phase = 1;
		phaseProgress = 0;
		attackProgress = 0;
		phaseKillers = new CopyOnWriteArrayList<Player>();
		weaponPieces = new FloorItem[3];
		ceilingCollapses = new WorldObject[6];
		mists = new WorldObject[9];
		teamSplitSquares = new WorldObject[16];
		unWalkAbleSpots = new ArrayList<WorldTile>();
		theEndBombs = new TheEndBomb[3];
		scopuli = new Scopulus[2];
		stoneClones = new StoneClone[8];
		vitali = new Vitalis[100];
	}

	@Override
	public boolean canWalkNPC(int toX, int toY) {
		if (phase == 5)
			return false;
		if (getTemporaryAttributtes().get("BringHimDownClick") != null)
			return false;
		if (getTemporaryAttributtes().get("CantBeAttacked") != null || getTemporaryAttributtes().get("TheEnd") != null)
			return false;
		return super.canWalkNPC(toX, toY);
	}

	private int damageCheckCycles;

	@Override
	public void processEntity() {
		if (getTemporaryAttributtes().get("BringHimDownClick") != null)
			processBringHimDown();
		else if (getCombat().getTarget() != null && isCantFollowUnderCombat()
				&& Utils.colides(this, getCombat().getTarget())
				&& getTemporaryAttributtes().get("CantBeAttacked") == null
				&& getTemporaryAttributtes().get("TheEnd") == null) {
			if (!addWalkSteps(getCombat().getTarget().getX() + 1, getY())) {
				resetWalkSteps();
				if (!addWalkSteps(getCombat().getTarget().getX() - getSize(), getY())) {
					resetWalkSteps();
					if (!addWalkSteps(getX(), getCombat().getTarget().getY() + 1)) {
						resetWalkSteps();
						addWalkSteps(getX(), getCombat().getTarget().getY() - getSize());
					}
				}
			}
		} else if (phase == 5 && getHitpoints() != 0) {
			WorldTile voragoSpawnLocation = instance.getVoragoSpawnLocation(5);
			if (damageCheckCycles == 8) {
				int damage = getTemporaryAttributtes().get("damage") == null ? 0
						: (int) getTemporaryAttributtes().get("damage");
				if (damage != 0) {
					if (damage > 0) {// push players
						int nextX = getX() - 1;
						WorldTile nextVoragoTile = new WorldTile(nextX, getY(), getPlane());
						setNextWorldTile(nextVoragoTile);
						for (Player player : instance.getPlayersOnBattle()) {
							if (player == null || player.isDead())
								continue;
							if (Utils.colides(new WorldTile(nextX, getY(), getPlane()), player, getSize(),
									player.getSize())) {
								if (nextX == voragoSpawnLocation.getX() - 11) {
									player.getTemporaryAttributtes().put("InstantDeath", Boolean.TRUE);
									player.sendDeath(this);
								} else {
									WorldTile next = new WorldTile(player.getX() - 1, player.getY(), player.getPlane());
									player.setNextWorldTile(next);
								}
							}
						}
					} else if (damage < 0) {// push vorago
						int nextX = getX() + 1;
						WorldTile nextVoragoTile = new WorldTile(nextX, getY(), getPlane());
						if (nextX == voragoSpawnLocation.getX() + 9) {
							for (Player player : instance.getPlayersOnBattle()) {
								if (player == null || player.isDead())
									continue;
								player.getPackets()
										.sendGameMessage("Only the maul of omens will be enough to finish vorago.");
							}
						} else {
							setNextWorldTile(nextVoragoTile);
						}
					}
					getTemporaryAttributtes().put("damage", 0);
				}
				damageCheckCycles = 0;
			}
			damageCheckCycles++;
		}
		super.processEntity();
	}

	// win coords = 3110 5982 0 (spawnlocation + 8 in x) / death player coords =
	// 3091 5982 0(spawnlocation - 11 in x)

	private double damageRatio = 1.50;

	@Override
	public void handleIngoingHit(Hit hit) {
		if (targetedPlayer != null) {
			Hit newHit = new Hit(this, hit.getDamage(), hit.getLook());
			hit.setDamage(0);
			int damage = newHit.getDamage();
			if (damage != 0) {
				targetedPlayer.applyHit(newHit);
			}
			if (phase == 5) {
				int damageR = getTemporaryAttributtes().get("damage") == null ? 0
						: (int) getTemporaryAttributtes().get("damage");
				getTemporaryAttributtes().put("damage", damageR + (newHit.getDamage()));
			}
		}
		if (getTemporaryAttributtes().get("BringHimDownClick") != null) {
			damageWhileDown += hit.getDamage();
		}
		if (getTemporaryAttributtes().get("ReducedDamage") != null) {
			int damage = hit.getDamage() * 1 / 8;
			hit.setDamage(damage);
		}
		if (getTemporaryAttributtes().get("TheEnd") != null) {
			int damage = hit.getDamage() * 1 / damageReduction;
			hit.setDamage(damage);
		}
		if (((getHitpoints() - hit.getDamage()) < (getMaxHitpoints() * 0.05)) && !canFinishPhase()) {
			setHitpoints((int) (getMaxHitpoints() * 0.3));
		}
		if (((getHitpoints() - hit.getDamage()) <= 0) && canFinishPhase()) {
			sendDeath(hit.getSource());
		}
		if (phase == 5 && targetedPlayer == null) {
			int damage = getTemporaryAttributtes().get("damage") == null ? 0
					: (int) getTemporaryAttributtes().get("damage");
			getTemporaryAttributtes().put("damage", (damage - ((int) (hit.getDamage() / damageRatio))));
		}
		super.handleIngoingHit(hit);
	}

	@Override
	public Hit handleOutgoingHit(Hit hit, Entity target) {
		if (phase == 5) {
			int damage = getTemporaryAttributtes().get("damage") == null ? 0
					: (int) getTemporaryAttributtes().get("damage");
			getTemporaryAttributtes().put("damage", (damage + ((int) (hit.getDamage() * damageRatio))));
		}
		return hit;
	}

	@Override
	public void processHit(Hit hit) {
		if (isDead() || getTemporaryAttributtes().get("BringHimDownClick") == Boolean.TRUE)
			return;
		if (getTemporaryAttributtes().get("BringHimDownClick") == null)
			removeHitpoints(hit);
		getNextHits().add(hit);
	}

	@Override
	public void sendDeath(Entity source) {
		if (phase != 5) {
			if (!canFinishPhase())
				return;
			if (getHitpoints() == 0)
				return;
			setCantInteract(true);
			generatePhaseKiller();
			getCombat().removeTarget();
			resetWalkSteps();
			setNextFaceEntity(null);
			for (Player players : instance.getPlayersOnBattle()) {
				if (players == null || players.isDead())
					continue;
				players.resetCombat();
				players.resetWalkSteps();
				players.setAttackedBy(null);
				players.setFindTargetDelay(0);
			}
			reset(true);
			setHitpoints(0);
			getCombat().reset();
			finishPhase();
		}
	}

	public void sendRealDeath(Player player) {
		if (getHitpoints() == 0)
			return;
		generatePhaseKiller();
		for (Player players : instance.getPlayersOnBattle()) {
			if (players == null || players.isDead() || players == player)
				continue;
			players.lock();
			players.resetCombat();
			players.resetWalkSteps();
			players.VoragoKills++;
			WorldTile sendTile = new WorldTile(getX() - 1, getY(), getPlane());
			players.setNextWorldTile(
					new WorldTile(sendTile.getX() - Utils.random(5), sendTile.getY(), sendTile.getPlane()));
		}
		player.lock();
		player.resetCombat();
		player.resetWalkSteps();
		player.setNextWorldTile(getTile(new WorldTile(3109, 5984, 0)));
		player.faceEntity(this);
		setCantInteract(true);
		resetWalkSteps();
		resetCombat();
		final NPCCombatDefinitions defs = getCombatDefinitions();
		setHitpoints(0);
		setNextAnimation(null);
		final int deathDelay = defs.getDeathDelay() - 4;
		WorldTasksManager.schedule(new WorldTask() {
			int loop;

			@Override
			public void run() {
				if (loop == 0) {
					setNextAnimation(new Animation(defs.getDeathEmote()));
					setNextGraphics(new Graphics(4036));
					player.setNextAnimation(new Animation(20387));
					player.setHasMauledWeek(Settings.VORAGO_ROTATION);
					player.getInventory().deleteItem(28606, 1);
				} else if (loop >= deathDelay) {
					sendDrops();
					instance.spawnExitSphere();
					reset();
					finish();
					for (Player players : instance.getPlayersOnBattle()) {
						if (players == null || players.isDead())
							continue;
						players.unlock();
					}
					stop();
				}
				loop++;
			}
		}, 0, 1);
	}

	public void sendDrops() {
		Drop[][] DROPS = { NPCDrops.getDrops(getId()), NPCDrops.getDrops(getId()), NPCDrops.getDrops(getId()),
				NPCDrops.getDrops(getId()), NPCDrops.getDrops(getId()) };
		WorldTile[] dropLocations = { getTile(new WorldTile(3111, 5985, 0)), getTile(new WorldTile(3113, 5985, 0)),
				getTile(new WorldTile(3112, 5984, 0)), getTile(new WorldTile(3111, 5983, 0)),
				getTile(new WorldTile(3113, 5983, 0)) };
		System.out.println("SENT DROPS");
		for (int i = 0; i < 5; i++) {// probably from the length i guess
			Drop[] drops = DROPS[i];
			if (drops == null)
				continue;
			WorldTile loc = dropLocations[i];
			sendDrops(getPhaseKiller(i), drops, loc);
		}
	}

	public void sendDrops(Player player, Drop[] dropL, WorldTile location) {
		Drop[] possibleDrops = new Drop[dropL.length];
		int possibleDropsCount = 0;// omg can't find whats wrong lol looks
									// perfect to me
		for (Drop drop : dropL) {
			Item item = ItemDefinitions.getItemDefinitions(drop.getItemId()).isStackable()
					? new Item(drop.getItemId(),
							(drop.getMinAmount() * Settings.DROP_RATE)
									+ Utils.getRandom(drop.getExtraAmount() * Settings.DROP_RATE))
					: new Item(drop.getItemId(), drop.getMinAmount() + Utils.getRandom(drop.getExtraAmount()));

			if (drop.getRate() == 100)
				World.addGroundItem(item, location, player, false, 180, true);
			else {
				if ((Utils.getRandomDouble(99) + 1) <= drop.getRate() * 1.5)
					possibleDrops[possibleDropsCount++] = drop;
			}
		}

		if (possibleDropsCount > 0) {
			Drop drop = possibleDrops[Utils.getRandom(possibleDropsCount - 1)];
			Item item = ItemDefinitions.getItemDefinitions(drop.getItemId()).isStackable()
					? new Item(drop.getItemId(),
							(drop.getMinAmount() * Settings.DROP_RATE)
									+ Utils.getRandom(drop.getExtraAmount() * Settings.DROP_RATE))
					: new Item(drop.getItemId(), drop.getMinAmount() + Utils.getRandom(drop.getExtraAmount()));
			World.addGroundItem(item, location, player, false, 180, true);
			if (item.getName().contains("seismic"))
				World.sendWorldMessage(
						"<img=7><col=ff8c38>News: " + Utils.formatPlayerNameForDisplay(player.getUsername())
								+ " has received a " + item.getName() + " drop</col>",
						false);
		}
	}

	public Player getPhaseKiller(int index) {
		Player phaseKiller = phaseKillers.get(index);
		if (phaseKiller == null || !instance.playerIsOnBattle(phaseKiller)) {
			ArrayList<Player> available = new ArrayList<Player>();
			for (int i = 0; i < instance.getPlayersOnBattle().size(); i++) {
				Player player = instance.getPlayersOnBattle().get(i);
				if (player == null || player.isDead())
					continue;
				available.add(player);
			}
			Collections.shuffle(available);
			return available.get(Utils.random(available.size() - 1));
		}
		return phaseKiller;
	}

	public void generatePhaseKiller() {
		int phase = getPhase() - 1;
		int luckyType = Utils.random(3);
		Player player = null;
		switch (luckyType) {
		case 0:// By last agro
			player = (Player) getCombat().getTarget();
			break;
		case 1:// By DPS
			int lowestDamage = 0;
			int index = 0;
			for (int i = 0; i < instance.getPlayersOnBattle().size(); i++) {
				Player DPS = instance.getPlayersOnBattle().get(i);
				if (DPS == null || DPS.isDead())
					continue;
				int TotalDamage = DPS.getTemporaryAttributtes().get("TotalDamage") == null ? 0
						: (int) DPS.getTemporaryAttributtes().get("TotalDamage");
				if (TotalDamage > lowestDamage) {
					lowestDamage = TotalDamage;
					index = i;
				}
			}
			player = instance.getPlayersOnBattle().get(index);
			break;
		case 2:// By blue bombs
		case 3:
			int lowestAmount = 0;
			index = 0;
			for (int i = 0; i < instance.getPlayersOnBattle().size(); i++) {
				Player bombTank = instance.getPlayersOnBattle().get(i);
				if (bombTank == null || bombTank.isDead())
					continue;
				int blues = bombTank.getTemporaryAttributtes().get("BlueBombs") == null ? 0
						: (int) bombTank.getTemporaryAttributtes().get("BlueBombs");
				if (blues > lowestAmount) {
					lowestAmount = blues;
					index = i;
				}
			}
			player = instance.getPlayersOnBattle().get(index);
			break;
		}
		if (player == null) {// shouldn't happen but okay random
			ArrayList<Player> available = new ArrayList<Player>();
			for (int i = 0; i < instance.getPlayersOnBattle().size(); i++) {
				Player p = instance.getPlayersOnBattle().get(i);
				if (p == null || p.isDead())
					continue;
				available.add(p);
			}
			Collections.shuffle(available);
			player = available.get(Utils.random(available.size() - 1));
		}
		phaseKillers.add(phase, player);
		for (Player reset : instance.getPlayersOnBattle()) {
			if (reset == null || reset.isDead())
				continue;
			reset.getTemporaryAttributtes().remove("BlueBombs");
			reset.getTemporaryAttributtes().remove("TotalDamage");
		}
	}

	@Override
	public boolean isDead() {// TODO put a method in phase 5 to set dead
		return false;
	}

	@Override
	public boolean restoreHitPoints() {
		int maxHp = getMaxHitpoints();
		if (getHitpoints() < maxHp && getHitpoints() != 0) {
			setHitpoints(getHitpoints() + 1);
			return true;
		}
		return false;
	}

	@Override
	public boolean checkAgressivity() {
		if (getTemporaryAttributtes().get("BringHimDownClick") != null
				|| getTemporaryAttributtes().get("TheEnd") != null)
			return false;
		if (getHitpoints() == 0) {
			return false;
		}
		if (getTemporaryAttributtes().get("CantBeAttacked") != null) {
			if (Settings.VORAGO_ROTATION == 1 && phase == 3) {
				ArrayList<Entity> possibleTarget = getPossibleTargets();
				if (!possibleTarget.isEmpty()) {
					Entity target = possibleTarget.get(Utils.random(possibleTarget.size()));
					setTarget(target);
					target.setAttackedBy(target);
					target.setFindTargetDelay(Utils.currentTimeMillis() + 10000);
					return true;
				}
				return false;// ok thats why lol
			}
			return false;
		}
		if (getTemporaryAttributtes().get("jumping") != null)
			return false;
		ArrayList<Entity> possibleTarget = getPossibleTargets();
		if (!possibleTarget.isEmpty()) {
			Entity target = possibleTarget.get(Utils.random(possibleTarget.size()));
			setTarget(target);
			target.setAttackedBy(target);
			target.setFindTargetDelay(Utils.currentTimeMillis() + 10000);
			return true;
		}
		return false;
	}

	@Override
	public void setTarget(Entity entity) {
		if (getTemporaryAttributtes().get("TheEnd") != null)
			return;
		super.setTarget(entity);
	}

	public void finishPhase() {
		WorldTasksManager.schedule(new WorldTask() {
			int count = 0;

			@Override
			public void run() {
				switch (count) {
				case 0:
				case 5:
				case 11:
					jump();
					break;
				case 17:
					setNextAnimation(new Animation(20365));
					sendCrackGround();
					break;
				case 18:
					startNextPhase();
					stop();
					break;
				}
				count++;
			}
		}, 0, 1);
	}

	public void startNextPhase() {
		phase++;
		removeLeftOvers();
		for (Player player : instance.getPlayersOnBattle()) {
			if (player != null)
				player.setNextAnimation(new Animation(20402));
		}
		WorldTasksManager.schedule(new WorldTask() {
			int count = 0;

			@Override
			public void run() {
				switch (count) {
				case 1:
					for (Player player : instance.getPlayersOnBattle()) {
						if (player == null)
							continue;
						WorldTile tile = instance.getSimilarCoords(player, phase);
						player.setNextAnimation(new Animation(20401));
						player.setNextWorldTile(tile);
						if (phase == 5)
							player.getTemporaryAttributtes().put("PHASE_5_START", Boolean.TRUE);
					}
					moveWeaponPieces();
					break;
				case 4:
					if (Settings.VORAGO_ROTATION != 0)
						break;
					if (phase == 4)
						for (WorldObject ceiling : ceilingCollapses) {
							if (ceiling == null)
								continue;
							WorldTile tile = instance.getSimilarCoords(
									new WorldTile(ceiling.getX(), ceiling.getY(), ceiling.getPlane()), phase);
							ceiling.setLocation(tile);
							spawnCeilingCollapse(tile, ceiling);
						}
					break;
				case 10:
					WorldTile phaseStartLocation = instance.getVoragoSpawnLocation(phase);
					if (Settings.VORAGO_ROTATION == 0 && phase == 4)
						removeMiddleCeilingObject();
					sendCrashDownAttack(phaseStartLocation);
					if (phase == 5) {
						for (Player player : instance.getPlayersOnBattle()) {
							if (player == null || player.isDead())
								continue;
							player.getTemporaryAttributtes().remove("PHASE_5_START");
						}
					}
					break;
				case 11:
					stop();
					break;
				}
				count++;
			}
		}, 0, 1);
	}

	public void removeLeftOvers() {
		removeLeftOvers(false);
	}

	public void removeLeftOvers(boolean finished) {
		for (int i = 0; i < ceilingCollapses.length; i++) {
			WorldObject ceiling = ceilingCollapses[i];
			if (ceiling == null)
				continue;
			World.removeObject(ceiling);
			if (phase == 5 && !finished)
				ceilingCollapses[i] = null;
		}
		for (int i = 0; i < mists.length; i++) {
			WorldObject mist = mists[i];
			if (mist == null)
				continue;
			World.removeObject(mist);
			mists[i] = null;
		}
		for (int i = 0; i < theEndBombs.length; i++) {
			TheEndBomb bomb = theEndBombs[i];
			if (bomb == null)
				continue;
			World.removeObject(bomb);
			theEndBombs[i] = null;
		}
		for (int i = 0; i < stoneClones.length; i++) {
			StoneClone clone = stoneClones[i];
			if (clone == null || clone.isDead())
				continue;
			clone.sendDeath(null);
			stoneClones[i] = null;
		}
		for (int i = 0; i < vitali.length; i++) {
			Vitalis vitalis = vitali[i];
			if (vitalis == null || vitalis.isDead())
				continue;
			vitalis.sendDeath(null);
			vitali[i] = null;
		}
		unWalkAbleSpots.clear();
	}

	public void resetOnPhaseStart() {
		setHitpoints(getMaxHitpoints());
		reset();
		setCantInteract(false);
		setForceFollowClose(false);
		getCombat().setCombatDelay(0);
		phaseProgress = 0;
		attackProgress = 0;
		switch (phase) {
		case 2:
			getTemporaryAttributtes().put("VoragoType", 1);
			transform();
			break;
		case 3:
			switch (Settings.VORAGO_ROTATION) {
			case 1:// Scopulus
				getTemporaryAttributtes().put("VoragoType", 1);
				getTemporaryAttributtes().put("CantBeAttacked",
						"Magical Force prevents you from attacking vorago, Try killing his minions instead.");
				transform();
				break;
			case 0:// ceiling
			case 2:// Vitalis
			case 3:// Green Bomb
				getTemporaryAttributtes().put("VoragoType", 1);
				transform();
				break;
			case 4:// Team Split
			case 5:// The End
				getTemporaryAttributtes().put("CantBeAttackedOnPhaseStart", Boolean.TRUE);
				getTemporaryAttributtes().put("VoragoType", 1);
				transform();
				break;
			}
			break;
		case 4:
			getTemporaryAttributtes().put("CantBeAttackedOnPhaseStart", Boolean.TRUE);
			getTemporaryAttributtes().put("VoragoType", 1);
			transform();
			break;
		case 5:
			if (Settings.VORAGO_ROTATION == 4)
				getTemporaryAttributtes().put("CantBeAttackedOnPhaseStart", Boolean.TRUE);
			getTemporaryAttributtes().put("VoragoType", 1);
			transform();
			break;
		}
	}

	public void moveWeaponPieces() {
		for (int i = 0; i < 3; i++) {
			FloorItem weaponPiece = weaponPieces[i];
			if (weaponPiece == null)
				continue;
			World.removeGroundItem(weaponPiece);
			weaponPieces[i] = World.addWeaponPiece(new Item(weaponPiece.getId()),
					instance.getSimilarCoords(weaponPiece.getTile(), phase));
		}
	}

	public void transform() {
		int type = (int) getTemporaryAttributtes().get("VoragoType");
		switch (type) {
		case 0:
			setCantFollowUnderCombat(false);
			setForceFollowClose(true);
			break;
		case 1:
			setCantFollowUnderCombat(true);
			setForceFollowClose(false);
			break;
		case 2:
			setCantFollowUnderCombat(true);
			setForceFollowClose(false);
			break;
		}
		if (getId() != VORAGOES[type]) {
			setNextNPCTransformation(VORAGOES[type]);
			getWalkSteps().clear();
		}
	}

	public void sendCrashDownAttack(WorldTile crashDownTile) {
		resetOnPhaseStart();
		setNextWorldTile(crashDownTile);
		setNextAnimation(new Animation(20367, 20367, 20367, -1, 0));
		for (Player player : instance.getPlayersOnBattle()) {
			if (player == null || player.isDead() || !Utils.colides(player.getX(), player.getY(), player.getSize(),
					crashDownTile.getX(), crashDownTile.getY(), getSize()))
				continue;
			player.lock();
			player.setNextAnimation(new Animation(20338));
			WorldTile toTile = getPushedBackWorldTile(player, crashDownTile, getMoveDirection(player));
			player.setNextForceMovement(new ForceMovement(toTile, 1, getMoveDirection(player)));
			WorldTasksManager.schedule(new WorldTask() {
				@Override
				public void run() {
					player.unlock();
					player.setNextWorldTile(toTile);
				}
			}, (AnimationDefinitions.getAnimationDefinitions(20338).getEmoteClientCycles() / 30));
		}
	}

	public WorldTile getPushedBackWorldTile(WorldTile startLocation, WorldTile voragoLocation, int playerDirection) {
		int moveX = (voragoLocation.getX() - startLocation.getX()) + 5;
		int moveY = (voragoLocation.getY() - startLocation.getY()) + 5;
		switch (playerDirection) {
		case ForceMovement.NORTH_EAST:// north-east
			return new WorldTile(startLocation.getX() - moveX, startLocation.getY() - moveY, startLocation.getPlane());
		case ForceMovement.NORTH_WEST:// north-west
			return new WorldTile(startLocation.getX() + moveX, startLocation.getY() - moveY, startLocation.getPlane());
		case ForceMovement.SOUTH_WEST:// south-west
			return new WorldTile(startLocation.getX() + moveX, startLocation.getY() + moveY, startLocation.getPlane());
		case ForceMovement.SOUTH_EAST:// south-east
			return new WorldTile(startLocation.getX() - moveX, startLocation.getY() + moveY, startLocation.getPlane());
		case ForceMovement.WEST:// west
			return new WorldTile(startLocation.getX() + moveX, startLocation.getY(), startLocation.getPlane());
		case ForceMovement.EAST:// east
			return new WorldTile(startLocation.getX() - moveX, startLocation.getY(), startLocation.getPlane());
		case ForceMovement.NORTH:// north
			return new WorldTile(startLocation.getX(), startLocation.getY() - moveY, startLocation.getPlane());
		case ForceMovement.SOUTH:// south
		default:
			return new WorldTile(startLocation.getX(), startLocation.getY() + moveY, startLocation.getPlane());
		}
	}

	// north-west 0, south-east 0, north-east 1, south-west 1
	// Center phase 4 3038, 5982, 0

	// { -10, 10 } west east
	// { 10, -10 } north south

	public void sendWaterFallAttack() {
		if (getTemporaryAttributtes().get("CantBeAttackedOnPhaseStart") != null)
			getTemporaryAttributtes().remove("CantBeAttackedOnPhaseStart");
		WorldTile center = instance.getVoragoSpawnLocation(4);
		WorldTile waterFallLocation = new WorldTile(
				(Utils.random(2) == 0 ? (center.getX() - 10) : (center.getX() + 10)),
				(Utils.random(2) == 0 ? (center.getY() + 10) : (center.getY() - 10)), center.getPlane());
		boolean northWest = ((waterFallLocation.getX() == (center.getX() - 10))
				&& (waterFallLocation.getY() == (center.getY() + 10)));
		boolean southEast = ((waterFallLocation.getX() == (center.getX() + 10))
				&& (waterFallLocation.getY() == (center.getY() - 10)));
		boolean northEast = ((waterFallLocation.getX() == (center.getX() + 10))
				&& (waterFallLocation.getY() == (center.getY() + 10)));
		int rotation = northWest ? 0 : northEast ? 1 : southEast ? 2 : 3;// south-west
		waterFall = new WorldObject(84967, 11, rotation, waterFallLocation);
		List<WorldTile> safeLocations = new ArrayList<WorldTile>();
		int rot = rotation;
		int xstart = (rot == 2 || rot == 1) ? waterFallLocation.getX() : (waterFallLocation.getX() + 3);
		int ystart = (rot == 0 || rot == 1) ? (waterFallLocation.getY() + 3) : waterFallLocation.getY();
		for (int i = 0; i < 4; i++) {// this shit was hardest thing ever haha
			for (int j = 0; j < (4 - i); j++) {
				WorldTile tile = new WorldTile((rot == 2 || rot == 1) ? (xstart + j) : (xstart - j),
						(rot == 0 || rot == 1) ? (ystart - j) : (ystart + j), 1);
				safeLocations.add(tile);
			}
			xstart = (rot == 2 || rot == 1) ? (xstart + 1) : (xstart - 1);
		}
		if (Settings.VORAGO_ROTATION == 0)
			removeMiddleCeilingObject();
		setNextWorldTile(center);
		canBeAttackedByAutoRelatie();
		setNextAnimation(new Animation(20322));
		getTemporaryAttributtes().put("CantBeAttacked", "Vorago is invulnerable as he charges a massive fire attack!");
		getTemporaryAttributtes().put("waterfall", Boolean.TRUE);
		World.spawnObject(waterFall);
		for (Player player : instance.getPlayersOnBattle()) {
			if (player == null || player.isDead())
				continue;
			player.getPackets().sendGameMessage("Vorago's raw power disrupts your defensive abilities!");
			VoragoInstance.sendMessage(player, 1,
					"<col=ff0000>Vorago starts to charge all his power into a massive fire attack!");
		}
		for (int i = 0; i < stoneClones.length; i++) {
			StoneClone clone = stoneClones[i];
			if (clone == null)
				continue;
			clone.sendDeath(clone.getTarget());
		}
		Entity lastTarget = getCombat().getTarget();
		getCombat().removeTarget();
		cancelFaceEntityNoCheck();
		Vorago thisNPC = this;
		WorldTasksManager.schedule(new WorldTask() {
			int count = 0;

			@Override
			public void run() {
				if (getHitpoints() == 0) {
					stop();
					return;
				}
				switch (count) {
				case 7:
					setNextAnimation(new Animation(20323));
					break;
				case 8:
					setNextGraphics(new Graphics(4013));
					setNextAnimation(new Animation(20323));
					getTemporaryAttributtes().remove("CantBeAttacked");
					getTemporaryAttributtes().remove("waterfall");
					World.removeObject(waterFall);
					phaseProgress++;
					for (Player player : instance.getPlayersOnBattle()) {
						if (player == null || player.isDead())
							continue;
						if (!isSafe(new WorldTile(player.getX(), player.getY(), player.getPlane()), safeLocations))
							player.applyHit(new Hit(thisNPC, 900, HitLook.REGULAR_DAMAGE));
					}
					if (phaseProgress == 3) {
						WorldTile tile = instance.randomSpawnTile(null, instance.getVoragoSpawnLocation(phase), 5);
						weaponPieces[2] = World.addWeaponPiece(new Item(28604), tile);
					}
					waterFall = null;
					getCombat().setTarget(lastTarget);
					stop();
					break;
				}
				count++;
			}
		}, 0, 1);
	}

	@Override
	public void drop() {
		// nothing
	}

	public boolean isSafe(WorldTile player, List<WorldTile> safeLocations) {
		for (WorldTile checkTile : safeLocations) {
			if (checkTile == null)
				continue;
			if (checkTile.getX() == player.getX() && checkTile.getY() == player.getY())
				return true;
		}
		return false;
	}

	public boolean cantBeAutoRetaliated() {
		if (getTemporaryAttributtes().get("BringHimDownClick") != null
				|| getTemporaryAttributtes().get("CantBeAttackedOnPhaseStart") != null
				|| getTemporaryAttributtes().get("TheEnd") != null
				|| getTemporaryAttributtes().get("CantBeAttacked") != null)
			return true;
		return false;
	}

	public void spawnStoneClones() {
		double maxStoneClones = Math.ceil((double) getPlayerOnBattleCount() / 7.00);
		for (int i = 0; i < maxStoneClones; i++) {
			Player target = getRandomCloneTarget();
			if (target == null)
				continue;
			int combatType = target.getCombatDefinitions().getType(Equipment.SLOT_WEAPON);
			int id = combatType == Combat.MAGIC_TYPE ? 17160 : combatType == Combat.RANGE_TYPE ? 17159 : 17158;
			WorldTile spawnTile = instance.randomSpawnTile(null,
					new WorldTile(target.getX(), target.getY(), target.getPlane()), 4);
			stoneClones[i] = new StoneClone(id, spawnTile, -1, true, this, target);
			target.getHintIconsManager().addHintIcon(stoneClones[i], 0, -1, false);
		}
	}

	public void sendCloneDeath(Player player) {
		for (int i = 0; i < stoneClones.length; i++) {
			StoneClone clone = stoneClones[i];
			if (clone == null)
				continue;
			if (clone.getTarget() == player)
				stoneClones[i] = null;
		}
	}

	public Player getRandomCloneTarget() {
		List<Player> availableIndexes = new ArrayList<Player>();
		for (int i = 0; i < getVoragoInstance().getPlayersOnBattle().size(); i++) {
			Player player = getVoragoInstance().getPlayersOnBattle().get(i);
			if (player == null || player.isDead() || (player == getCombat().getTarget() && getPlayerOnBattleCount() > 1)
					|| hasClone(player))
				continue;
			availableIndexes.add(player);
		}
		return availableIndexes.isEmpty() ? null : availableIndexes.get(Utils.random(availableIndexes.size() - 1));
	}

	public boolean hasClone(Player player) {
		for (StoneClone stoneClone : stoneClones) {
			if (stoneClone == null || stoneClone.isDead())
				continue;
			if (stoneClone.getTarget() == player)
				return true;
		}
		return false;
	}

	public void sendBringHimDown() {
		getTemporaryAttributtes().put("BringHimDownClick", Boolean.TRUE);
		setCantDoDefenceEmote(true);
		setNextAnimation(new Animation(20382));
		damageTime = Utils.currentTimeMillis() + 180000;// 3mins
		for (Player player : instance.getPlayersOnBattle()) {
			if (player == null || player.isDead())
				continue;
			player.stopAll();
			player.faceEntity(this);
			player.getTemporaryAttributtes().put("BRINGHIM", Boolean.TRUE);
			player.setNextAnimation(getBringHimDownAnimation());
		}
	}

	public void processBringHimDown() {
		if (getBringHimPoints() >= 200 && getTemporaryAttributtes().get("BringHimDownClick") == Boolean.TRUE) {
			getTemporaryAttributtes().put("BringHimDownClick", Boolean.FALSE);
			setNextAnimation(new Animation(20383));
			WorldTasksManager.schedule(new WorldTask() {
				@Override
				public void run() {
					getTemporaryAttributtes().put("VoragoType", 2);
					transform();
					finishBringHimDown();
				}
			}, (AnimationDefinitions.getAnimationDefinitions(20383).getEmoteClientCycles() / 30));
			for (Player player : getVoragoInstance().getPlayersOnBattle()) {
				if (player == null)
					continue;
				Vorago thisNPC = this;
				player.getTemporaryAttributtes().remove("BRINGHIM");
				player.lock();
				player.setNextAnimation(new Animation(20400));
				WorldTasksManager.schedule(new WorldTask() {
					@Override
					public void run() {
						if (damageTime >= Utils.currentTimeMillis())
							player.applyHit(new Hit(thisNPC, 200, HitLook.REGULAR_DAMAGE));
						player.unlock();
					}
				}, (AnimationDefinitions.getAnimationDefinitions(20400).getEmoteClientCycles() / 30) + 1);
			}
		}
	}

	public void finishBringHimDown() {
		for (Player player : instance.getPlayersOnBattle()) {
			if (player == null || player.isDead())
				continue;
			VoragoInstance.sendMessage(player, 1,
					"<col=00ff00>Vorago stumbles! Deal as much damage as possible to loosen the next weapon piece!");
		}
		int damageRequired = 2000 * getPlayerOnBattleCount();
		WorldTasksManager.schedule(new WorldTask() {
			@Override
			public void run() {
				setBringHimPoints(0);
				setNextAnimation(new Animation(20362));
				WorldTasksManager.schedule(new WorldTask() {
					@Override
					public void run() {
						getTemporaryAttributtes().remove("BringHimDownClick");
						getTemporaryAttributtes().put("VoragoType", 1);
						transform();
						setCantSetTargetAutoRelatio(false);
						setCantDoDefenceEmote(false);
					}
				}, (AnimationDefinitions.getAnimationDefinitions(20362).getEmoteClientCycles() / 30));
				int finishType = (losenWeaponPieceStage >= 2 || damageWhileDown >= damageRequired) ? 2
						: (damageWhileDown < (damageRequired / 2)) ? 1 : 0;
				for (Player player : instance.getPlayersOnBattle()) {
					if (player == null || player.isDead())
						continue;
					VoragoInstance.sendMessage(player, 1, finishType == 2
							? "<col=00ff00>The weapon piece falls from Vorago's body!"
							: finishType == 1
									? "<col=ff0000>You didn't do enough damage in time. The weapon piece still looks secure."
									: "<col=FF6600>Your strong attacks helps loosen the weapon piece a little more.");
				}
				phaseProgress = finishType == 2 ? 5 : 1;
				if (finishType == 2) {
					WorldTile tile = instance.randomSpawnTile(null, instance.getVoragoSpawnLocation(phase), 5);
					weaponPieces[1] = World.addWeaponPiece(new Item(28602), tile);
				} else if (finishType == 0) {
					losenWeaponPieceStage++;
				}
				damageWhileDown = 0;
				stop();
			}
		}, 30);
	}

	public Animation getBringHimDownAnimation() {
		int points = getBringHimPoints();
		int index = 0;
		if (points >= 167 && points < 200)
			index = 5;
		else if (points >= 134 && points < 167)
			index = 4;
		else if (points >= 101 && points < 134)
			index = 3;
		else if (points >= 68 && points < 101)
			index = 2;
		else if (points >= 35 && points < 68)
			index = 1;
		return new Animation(20394 + index);
	}

	public int getPlayerOnBattleCount() {
		int count = 0;
		for (Player player : instance.getPlayersOnBattle()) {
			if (player == null || player.isDead())
				continue;
			count++;
		}
		return count;
	}

	public void jump() {
		WorldTile jumpLocation = getRandomUsableLocation(null, true);
		NPC thisNPC = this;
		WorldTasksManager.schedule(new WorldTask() {
			int count = 0;

			@Override
			public void run() {
				switch (count) {
				case 1:
					setNextFaceWorldTile(jumpLocation);
					setNextAnimation(new Animation(20365));
					break;
				case 2:
					World.sendGraphics(thisNPC, new Graphics(4037), jumpLocation);
					break;
				case 4:
					setNextAnimation(new Animation(20367));
					setNextWorldTile(new WorldTile(jumpLocation.getX() - 2, jumpLocation.getY() - 2, 0));
					for (Player player : instance.getPlayersOnBattle()) {
						if (player == null || player.isDead())
							continue;
						boolean underRago = Utils.colides(jumpLocation.getX() - 2, jumpLocation.getY() - 2,
								thisNPC.getSize(), player.getX(), player.getY(), player.getSize());
						int distance = Utils.getDistance(player, jumpLocation);
						int damage = underRago ? (distance == 0 ? 550 : (550 - (distance * 510)))
								: (distance > 5 ? 0 : (550 * 1 / distance == 0 ? 1 : distance));
						if (damage != 0) {
							if (damage < 150)
								damage = Utils.random(150, 300);
							player.applyHit(new Hit(thisNPC, damage, HitLook.REGULAR_DAMAGE));
						}
					}
					stop();
					break;
				}
				count++;
			}
		}, 0, 1);
	}

	// northEast +6 +6 northwest -6+6 southwest -6-6 southeast +6-6

	public void startTheEnd() {
		if (getTemporaryAttributtes().get("CantBeAttackedOnPhaseStart") != null)
			getTemporaryAttributtes().remove("CantBeAttackedOnPhaseStart");
		getTemporaryAttributtes().put("CantBeAttacked", "Vorago is unvulnerable as he charges a massive smash attack!");
		for (Player player : instance.getPlayersOnBattle()) {
			if (player == null || player.isDead())
				continue;
			player.resetCombat();
			VoragoInstance.sendMessage(player, 1,
					"<col=ff0000>Vorago prepares to suffocate all those who aren't contained!");
		}
		WorldTile voragoLocation = instance.getVoragoSpawnLocation(phase);
		WorldTile center = new WorldTile(voragoLocation.getX() + 2, voragoLocation.getY() + 2,
				voragoLocation.getPlane());
		WorldTile[] locations = {
				new WorldTile(voragoLocation.getX() + 7, voragoLocation.getY() + 7, voragoLocation.getPlane()),
				new WorldTile(voragoLocation.getX() - 6, voragoLocation.getY() + 6, voragoLocation.getPlane()),
				new WorldTile(voragoLocation.getX() - 6, voragoLocation.getY() - 6, voragoLocation.getPlane()),
				new WorldTile(voragoLocation.getX() + 6, voragoLocation.getY() - 6, voragoLocation.getPlane()) };
		theEndStartIndex = Utils.random(locations.length - 1);
		int objectStartIndex = (theEndStartIndex + 3) > (locations.length - 1) ? (theEndStartIndex - 1)
				: (theEndStartIndex + 3);
		int objectId = Utils.random(2) == 0 ? 95042 : 95043;
		int blueBombsAmount = (int) ((getPlayerOnBattleCount() * 0.7) < 5 ? 5 : (getPlayerOnBattleCount() * 0.7));
		int redBombsAmount = (int) ((getPlayerOnBattleCount() * 0.42) < 3 ? 3 : (getPlayerOnBattleCount() * 0.42));
		for (int i = 0; i < 3; i++) {
			int index = (objectStartIndex - i) < 0 ? ((objectStartIndex - i) == -1 ? 3 : 2) : (objectStartIndex - i);
			int id = i == 0 ? 95044 : i == 1 ? objectId : objectId == 95043 ? 95042 : 95043;
			WorldTile loc = new WorldTile(locations[index].getX(), locations[index].getY(),
					locations[index].getPlane());
			theEndBombs[i] = new TheEndBomb(id, 10, 0, loc, this,
					id == 95044 ? 1 : id == 95043 ? blueBombsAmount : redBombsAmount);
		}
		WorldTile faceTile = new WorldTile(
				theEndStartIndex == 0 ? locations[theEndStartIndex].getX() + 6
						: theEndStartIndex == 1 || theEndStartIndex == 2 ? locations[theEndStartIndex].getX() - 4
								: locations[theEndStartIndex].getX() + 7,
				theEndStartIndex == 0 ? locations[theEndStartIndex].getY() + 6
						: theEndStartIndex == 1 ? locations[theEndStartIndex].getY() + 7
								: locations[theEndStartIndex].getY() - 4,
				locations[theEndStartIndex].getPlane());
		setNextWorldTile(voragoLocation);
		cancelFaceEntityNoCheck();
		getCombat().removeTarget();
		theEndFaceTile = faceTile;
		setNextFaceWorldTile(theEndFaceTile);
		setNextAnimation(new Animation(25434, 25434, 25434, -1, 0));
		unWalkAbleSpots.clear();
		Vorago thisNPC = this;
		theEndCycles = 3;
		WorldTasksManager.schedule(new WorldTask() {
			int count = 0;

			@Override
			public void run() {
				if (getHitpoints() == 0 || instance.getPlayersOnBattle().isEmpty()) {
					stop();
					return;
				}
				if (getCombat().getTarget() != null && theEndCycles != 0) {
					getCombat().removeTarget();
					getCombat().reset();
					cancelFaceEntityNoCheck();
					setNextFaceWorldTile(theEndFaceTile);
				}
				if (count == 10) {
					for (int i = 0; i < theEndBombs.length; i++) {
						theEndBombs[i].setActive(false);
						World.spawnObject(theEndBombs[i]);
					}
					setNextForceTalk(new ForceTalk("...Za End!"));
					setNextAnimation(new Animation(25435));
					setCantDoDefenceEmote(true);
					getTemporaryAttributtes().remove("CantBeAttacked");
					getTemporaryAttributtes().put("TheEnd", Boolean.TRUE);
					damageReduction = 1;
					for (Player player : instance.getPlayersOnBattle()) {
						if (player == null || player.isDead())
							continue;
						if (isBetweenVoragoHands(player))
							player.getTemporaryAttributtes().remove("Suffocating");
						else
							player.getTemporaryAttributtes().put("Suffocating", 100);
					}
					for (int i = 0; i < 2; i++) {
						for (int j = 0; j < 15; j++) {
							WorldTile unWalkable = new WorldTile(
									i == 0 ? ((theEndStartIndex == 0 || theEndStartIndex == 2)
											? (theEndStartIndex == 0 ? (center.getX() + j) : (center.getX() - j))
											: center.getX())
											: ((theEndStartIndex == 0 || theEndStartIndex == 2) ? center.getX()
													: (theEndStartIndex == 1 ? (center.getX() - j)
															: (center.getX() + j))),
									i == 0 ? ((theEndStartIndex == 0 || theEndStartIndex == 2) ? center.getY()
											: (theEndStartIndex == 1 ? (center.getY() + j) : (center.getY() - j)))
											: ((theEndStartIndex == 0 || theEndStartIndex == 2) ? (theEndStartIndex == 0
													? (center.getY() + j) : (center.getY() - j)) : center.getY()),
									getPlane());
							unWalkAbleSpots.add(unWalkable);
						}
					}
				} else if (count == 16)
					setNextAnimation(new Animation(25436, 25436, 25436, -1, 0));
				else if (count == 18)
					unWalkAbleSpots.clear();
				else if (count == 19) {
					setNextAnimation(new Animation(25437));
					for (int i = 0; i < 2; i++) {
						for (int j = 0; j < 15; j++) {
							WorldTile unWalkable = new WorldTile(
									i == 0 ? ((theEndStartIndex == 0 || theEndStartIndex == 2)
											? (theEndStartIndex == 0 ? (center.getX() + j) : (center.getX() - j))
											: center.getX())
											: ((theEndStartIndex == 0 || theEndStartIndex == 2) ? (theEndStartIndex == 0
													? (center.getX() - j) : (center.getX() + j)) : (center.getX())),
									i == 0 ? ((theEndStartIndex == 0 || theEndStartIndex == 2) ? center.getY()
											: (theEndStartIndex == 1 ? (center.getY() + j) : (center.getY() - j)))
											: ((theEndStartIndex == 0 || theEndStartIndex == 2) ? (center.getY())
													: (theEndStartIndex == 1 ? (center.getY() - j)
															: (center.getY() + j))),
									getPlane());
							unWalkAbleSpots.add(unWalkable);
						}
					}
					TheEndBomb bomb = getBombBetweenVoragoHands();
					if (bomb != null)
						bomb.setActive(false);
				} else if (count == 27) {
					int nextFaceIndex = (theEndStartIndex + 1) > (locations.length - 1) ? (theEndStartIndex - 3)
							: (theEndStartIndex + 1);
					WorldTile nextFaceTile = new WorldTile(
							nextFaceIndex == 0 ? locations[nextFaceIndex].getX() + 6
									: nextFaceIndex == 1 || nextFaceIndex == 2 ? locations[nextFaceIndex].getX() - 4
											: locations[nextFaceIndex].getX() + 7,
							nextFaceIndex == 0 ? locations[nextFaceIndex].getY() + 6
									: nextFaceIndex == 1 ? locations[nextFaceIndex].getY() + 7
											: locations[nextFaceIndex].getY() - 4,
							locations[nextFaceIndex].getPlane());
					setNextAnimation(new Animation(25438, 25438, 25438, -1, 0));
					for (Player player : instance.getPlayersOnBattle()) {
						if (player == null || player.isDead() || !isBetweenVoragoHands(player))
							continue;
						player.lock(2);
						player.setNextAnimation(new Animation(20338));
						WorldTile pushTile = new WorldTile(
								nextFaceIndex == 0 || nextFaceIndex == 2 ? player.getX() : nextFaceTile.getX(),
								nextFaceIndex == 0 || nextFaceIndex == 2 ? nextFaceTile.getY() : player.getY(),
								player.getPlane());
						player.setNextWorldTile(pushTile);
						player.applyHit(new Hit(thisNPC, 250, HitLook.REGULAR_DAMAGE));
					}
					setNextFaceWorldTile(nextFaceTile);
					theEndFaceTile = nextFaceTile;
					unWalkAbleSpots.clear();
					for (int i = 0; i < 2; i++) {
						for (int j = 0; j < 15; j++) {
							WorldTile unWalkable = new WorldTile(
									i == 0 ? ((nextFaceIndex == 0 || nextFaceIndex == 2)
											? (nextFaceIndex == 0 ? (center.getX() + j) : (center.getX() - j))
											: center.getX())
											: ((nextFaceIndex == 0 || nextFaceIndex == 2) ? center.getX()
													: (nextFaceIndex == 1 ? (center.getX() - j) : (center.getX() + j))),
									i == 0 ? ((nextFaceIndex == 0 || nextFaceIndex == 2) ? center.getY()
											: (nextFaceIndex == 1 ? (center.getY() + j) : (center.getY() - j)))
											: ((nextFaceIndex == 0 || nextFaceIndex == 2)
													? (nextFaceIndex == 0 ? (center.getY() + j) : (center.getY() - j))
													: center.getY()),
									getPlane());
							unWalkAbleSpots.add(unWalkable);
						}
					}
				} else if (count == 28) {
					setNextAnimation(new Animation(25439));
					for (Player player : instance.getPlayersOnBattle()) {
						if (player == null || player.isDead())
							continue;
						if (isBetweenVoragoHands(player))
							player.getTemporaryAttributtes().remove("Suffocating");
						else if (player.getTemporaryAttributtes().get("Suffocating") == null)
							player.getTemporaryAttributtes().put("Suffocating", 100);
					}
					TheEndBomb bomb = getBombBetweenVoragoHands();
					if (bomb != null) {
						bomb.setActive(true);
						damageReduction = bomb.getId() == 95044 ? 4 : bomb.getId() == 95043 ? 3 : 2;
					}
				} else if (count == 34) {
					theEndCycles--;
					theEndStartIndex++;
					if (theEndCycles == 0) {
						setNextAnimation(new Animation(25440));
						getTemporaryAttributtes().remove("TheEnd");
						for (Player player : instance.getPlayersOnBattle()) {
							if (player == null || player.isDead())
								continue;
							player.getTemporaryAttributtes().remove("Suffocating");
						}
						setCantDoDefenceEmote(false);
						theEndFaceTile = null;
						damageReduction = 1;
						unWalkAbleSpots.clear();
						count = 36;
						return;
					}
					count = 16;
					return;
				} else if (count == 38) {
					launchRemainingBombs();
					stop();
				}
				count++;
			}
		}, 0, 0);
	}

	public boolean canMove(Player player, int nextX, int nextY) {
		if (unWalkAbleSpots == null || getHitpoints() == 0)
			return true;
		for (WorldTile checkTile : unWalkAbleSpots) {
			if (checkTile.getX() == nextX && checkTile.getY() == nextY)
				return false;
		}
		return true;
	}

	public TheEndBomb getBombById(int objectId) {
		for (int i = 0; i < theEndBombs.length; i++) {
			if (theEndBombs[i] == null)
				continue;
			if (theEndBombs[i].getId() == objectId)
				return theEndBombs[i];
		}
		return null;
	}

	public void launchRemainingBombs() {
		TheEndBomb redBomb = getBombById(95042);
		TheEndBomb blueBomb = getBombById(95043);
		TheEndBomb purpleBomb = getBombById(95044);
		int bluesWaves = blueBomb == null ? 0 : (blueBomb.getCharges() / getPlayerOnBattleCount());
		int redsWaves = redBomb == null ? 0 : (redBomb.getCharges() / getPlayerOnBattleCount());

		for (int i = 0; i < bluesWaves; i++) {
			Collections.shuffle(instance.getPlayersOnBattle());
			if (i == (bluesWaves - 1)) {
				World.removeObject(blueBomb);
			}
			WorldTasksManager.schedule(new WorldTask() {
				@Override
				public void run() {
					for (Player player : instance.getPlayersOnBattle()) {
						if (player == null || player.isDead())
							continue;
						if (blueBomb.decreaseCharges())
							sendBlueBombAttack(player, blueBomb, false);
					}
				}
			}, 2);
		}
		for (int i = 0; i < redsWaves; i++) {
			Collections.shuffle(instance.getPlayersOnBattle());
			if (i == (redsWaves - 1)) {
				World.removeObject(redBomb);
			}
			WorldTasksManager.schedule(new WorldTask() {
				@Override
				public void run() {
					for (Player player : instance.getPlayersOnBattle()) {
						if (player == null || player.isDead())
							continue;
						if (redBomb.decreaseCharges())
							sendRedBombAttack(player, redBomb);
					}
				}
			}, 2);
		}
		if (purpleBomb == null)
			return;
		if (purpleBomb.decreaseCharges())
			sendPurpleBombAttack();
		World.removeObject(purpleBomb);
	}

	private int purpleBombCounter;

	public void sendPurpleBombAttack() {
		TheEndBomb purpleBomb = phase == 5 ? null : getBombById(95044);
		purpleBombCounter = 0;
		Collections.shuffle(instance.getPlayersOnBattle());
		Vorago thisNPC = this;
		for (int i = 0; i < instance.getPlayersOnBattle().size(); i++) {
			Player player = instance.getPlayersOnBattle().get(i);
			if (player == null || player.isDead())
				continue;
			long startTime = Utils.currentTimeMillis();
			long arriveTime = startTime + (55000 + (25000 * purpleBombCounter));
			WorldTile fromLocation = purpleBomb != null ? purpleBomb
					: new WorldTile(player.getX() - 4, player.getY(), player.getPlane());
			double speed = Utils.getProjectileSpeed(fromLocation, player, 5, 20, startTime, arriveTime);
			Projectile projectile = World.sendProjectileNew(fromLocation, player, 5326, 5, 20, 10, speed, 0, 0);
			int cycleTime = Utils.projectileTimeToCycles(projectile.getEndTime()) - 2;
			player.getAppearence().transformIntoNPC(17158);
			CoresManager.fastExecutor.scheduleAtFixedRate(new TimerTask() {
				int count = cycleTime;
				int startPercentage = 0;

				@Override
				public void run() {
					VoragoInstance.sendMessage(player, 1, "<col=ff0000>Detonation in: " + count, true);
					int perc = (int) (((double) (startPercentage / (double) cycleTime)) * 100);
					player.getTemporaryAttributtes().put("PurpleBomb", perc);
					startPercentage++;
					count--;
					if (count == 0) {
						player.getAppearence().transformIntoNPC(-1);
						CombatScript.delayHit(thisNPC, 0, player, new Hit(thisNPC, 200, HitLook.REGULAR_DAMAGE));
						player.setNextGraphics(new Graphics(4024));
						for (Player p2 : instance.getPlayersOnBattle()) {
							if (p2 == null || p2.isDead() || p2 == player || !Utils.isOnRange(player, p2, 1))
								continue;
							CombatScript.delayHit(thisNPC, 0, p2, new Hit(thisNPC, 200, HitLook.REGULAR_DAMAGE));
							p2.setNextGraphics(new Graphics(4024));
						}
						cancel();
					}
				}

			}, 0, 1000);
			purpleBombCounter++;
		}
	}

	public void handleBombClick(Player player, WorldObject object) {
		if (getHitpoints() == 0)
			return;
		TheEndBomb bomb = (TheEndBomb) object;
		if (bomb == null)
			return;
		if (getTemporaryAttributtes().get("TheEnd") == null || !bomb.isActive()) {
			VoragoInstance.sendMessage(player, 1, "Vorago isn't focusing on making that bomb active right now.");
			return;
		}
		switch (bomb.getId()) {
		case 95042:// red bomb
			if (bomb.decreaseCharges())
				sendRedBombAttack(player, bomb);
			break;
		case 95043:// blue bomb
			if (bomb.decreaseCharges())
				sendBlueBombAttack(player, bomb, true);
			break;
		case 95044:// purple bomb
			if (bomb.decreaseCharges())
				sendPurpleBombAttack();
			break;
		}
	}

	public boolean isBetweenVoragoHands(WorldTile tile) {
		if (theEndFaceTile == null)
			return false;
		WorldTile cornerLocation = new WorldTile(theEndFaceTile.getX(), theEndFaceTile.getY(),
				theEndFaceTile.getPlane());
		WorldTile center = new WorldTile(getX() + 2, getY() + 2, getPlane());
		int smallestX = center.getX() > cornerLocation.getX() ? cornerLocation.getX() : center.getX();
		int largestX = center.getX() < cornerLocation.getX() ? cornerLocation.getX() : center.getX();
		int smallestY = center.getY() > cornerLocation.getY() ? cornerLocation.getY() : center.getY();
		int largestY = center.getY() < cornerLocation.getY() ? cornerLocation.getY() : center.getY();
		return isInSquare(tile, smallestX, largestX, smallestY, largestY);
	}

	public TheEndBomb getBombBetweenVoragoHands() {
		if (theEndFaceTile == null)
			return null;
		WorldTile cornerLocation = new WorldTile(theEndFaceTile.getX(), theEndFaceTile.getY(),
				theEndFaceTile.getPlane());
		WorldTile center = new WorldTile(getX() + 2, getY() + 2, getPlane());
		int smallestX = center.getX() > cornerLocation.getX() ? cornerLocation.getX() : center.getX();
		int largestX = center.getX() < cornerLocation.getX() ? cornerLocation.getX() : center.getX();
		int smallestY = center.getY() > cornerLocation.getY() ? cornerLocation.getY() : center.getY();
		int largestY = center.getY() < cornerLocation.getY() ? cornerLocation.getY() : center.getY();
		int[] bombObjectIds = { 95042, 95043, 95044 };
		for (int i = 0; i < bombObjectIds.length; i++) {
			for (int x = smallestX; x < largestX; x++) {
				for (int y = smallestY; y < largestY; y++) {
					TheEndBomb bomb = (TheEndBomb) World.getObjectWithId(new WorldTile(x, y, cornerLocation.getPlane()),
							bombObjectIds[i]);
					if (bomb == null)
						continue;
					return bomb;
				}
			}
		}
		return null;
	}

	public void sendBlueBombAttack(Player player, WorldTile fromLocation, boolean resetDefinsive) {
		if (resetDefinsive) {
		}
		long startTime = Utils.currentTimeMillis();
		long arriveTime = startTime + (15000);// arrives after 3 seconds
		double speed = Utils.getProjectileSpeed(fromLocation, player, 5, 20, startTime, arriveTime);
		Projectile projectile = World.sendProjectileNew(fromLocation, player, 4016, 5, 20, 10, speed, 0, 0);
		int cycleTime = Utils.projectileTimeToCycles(projectile.getEndTime()) - 1;
		Vorago thisNPC = this;
		CoresManager.fastExecutor.schedule(new TimerTask() {

			@Override
			public void run() {
				int damage = CombatScript.getMaxHit(thisNPC, 500, NPCCombatDefinitions.MAGE, player);
				player.setNextGraphics(new Graphics(4017));
				CombatScript.delayHit(thisNPC, 0, player, CombatScript.getMagicHit(thisNPC, damage));
			}
		}, (cycleTime * 1000) - 950);
	}

	public void sendRedBombAttack(Player player, WorldTile fromLocation) {
		long startTime = Utils.currentTimeMillis();
		long arriveTime = startTime + (20000);// arrives after 4.2 seconds
		double speed = Utils.getProjectileSpeed(fromLocation, player, 5, 20, startTime, arriveTime);
		Projectile projectile = World.sendProjectileNew(fromLocation, player, 4023, 5, 20, 10, speed, 0, 0);
		int cycleTime = Utils.projectileTimeToCycles(projectile.getEndTime()) - 1;
		Vorago thisNPC = this;
		CoresManager.fastExecutor.schedule(new TimerTask() {

			@Override
			public void run() {
				for (Player p : instance.getPlayersOnBattle()) {
					if (p == null || p.isDead() || Utils.getDistance(p, player) > 2)
						continue;
					int damage = 200 + (getPlayersNearby(player, 3) * 100);
					p.setNextGraphics(new Graphics(4024));
					World.sendGraphics(null, new Graphics(3522), new WorldTile(p.getX(), p.getY(), p.getPlane()));
					CombatScript.delayHit(thisNPC, 0, p, new Hit(thisNPC, damage, HitLook.REGULAR_DAMAGE));
				}
			}
		}, (cycleTime * 1000) - 950);
	}

	// north 0 - east - 1 south - 2 west - 3

	public void sendTeamSplit() {
		if (getTemporaryAttributtes().get("CantBeAttackedOnPhaseStart") != null)
			getTemporaryAttributtes().remove("CantBeAttackedOnPhaseStart");
		setNextAnimation(new Animation(20322));
		getTemporaryAttributtes().put("CantBeAttacked", "Vorago is invulnerable as he charges a massive fire attack!");
		WorldTile greenSpawnLocation = getRandomUsableLocation();
		WorldTile redSpawnLocation = getRandomUsableLocation(greenSpawnLocation);
		ArrayList<WorldTile> redSafeLocations = new ArrayList<WorldTile>();
		ArrayList<WorldTile> greenSafeLocations = new ArrayList<WorldTile>();
		int startYGreen = greenSpawnLocation.getY() - 2;
		int startYRed = redSpawnLocation.getY() - 2;
		int index = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				WorldTile loc = (index >= 0 && index < 8)
						? new WorldTile(redSpawnLocation.getX() + i, startYRed + j, redSpawnLocation.getPlane())
						: new WorldTile(greenSpawnLocation.getX() + ((i >= 2) ? (i - 2) : i), startYGreen + j,
								greenSpawnLocation.getPlane());
				int rot = (i == 0 || i == 2) ? ((j == 0) ? 0 : (j == 1 || j == 2) ? 3 : 2)
						: ((j == 0) ? 0 : (j == 1 || j == 2) ? 1 : 2);
				if ((j == 1 || j == 2) && (index >= 0 && index < 8))
					redSafeLocations.add(loc);
				else if ((j == 1 || j == 2) && (index >= 8))
					greenSafeLocations.add(loc);
				teamSplitSquares[index] = new WorldObject((index >= 0 && index < 8) ? 88882 : 88881, 22, rot, loc);
				World.spawnObject(teamSplitSquares[index]);
				index++;
			}
		}
		ArrayList<Player> players = new ArrayList<Player>();
		int playersCount = 0;
		for (Player player : instance.getPlayersOnBattle()) {
			if (player == null || player.isDead())
				continue;
			player.getTemporaryAttributtes().remove("TeamSplitRed");
			players.add(player);
			playersCount++;
		}
		Collections.shuffle(players);
		int playersInTeam = 0;
		boolean redTeam = Utils.random(2) == 0;
		for (int i = 0; i < playersCount; i += 2) {
			Player player = players.get(i);
			if (player == null || player.isDead())
				continue;
			player.getTemporaryAttributtes().put("TeamSplitRed", redTeam);
			player.getPackets().sendGameMessage("Vorago is charging a massive attack. You are in team "
					+ (redTeam ? "<col=ff0000>RED</col>" : "<col=00ff00>GREEN</col>") + ".");
			playersInTeam++;
		}
		int playersLeft = (playersCount - playersInTeam);// ok now will work you
															// can test from now
															// on and tell me if
															// you happen to
															// find any problem
															// i will fix for
															// you free ofc, thx
															// dude, :D
		for (int i = playersLeft; i > 0; i--) {
			for (Player player : players) {
				if (player == null || player.isDead() || player.getTemporaryAttributtes().get("TeamSplitRed") != null)
					continue;
				player.getTemporaryAttributtes().put("TeamSplitRed", !redTeam);
				player.getPackets().sendGameMessage("Vorago is charging a massive attack. You are in team "
						+ (!redTeam ? "<col=00ff00>GREEN</col>" : "<col=ff0000>RED</col>") + ".");
				break;
			}
		}
		for (Player player : instance.getPlayersOnBattle()) {
			if (player == null || player.isDead())
				continue;
			player.getPackets().sendGameMessage("Vorago's raw power disrupts your defensive abilities!");
			VoragoInstance.sendMessage(player, 1,
					"<col=ff0000>Vorago starts to charge all his power into a massive fire attack!");
		}
		Entity lastTarget = getCombat().getTarget();
		getCombat().removeTarget();
		cancelFaceEntityNoCheck();
		Vorago thisNPC = this;
		WorldTasksManager.schedule(new WorldTask() {
			int count = 0;

			@Override
			public void run() {
				if (getHitpoints() == 0) {
					stop();
					return;
				}
				switch (count) {
				case 12:
					setNextAnimation(new Animation(20323));
					break;
				case 13:
					setNextAnimation(new Animation(20323));
					setNextGraphics(new Graphics(4013));
					setNextFaceEntity(
							lastTarget == null ? getRandomTarget(new WorldTile(thisNPC), null, 24) : lastTarget);
					getTemporaryAttributtes().remove("CantBeAttacked");
					for (Player player : instance.getPlayersOnBattle()) {
						if (player == null || player.isDead())
							continue;
						boolean isInRedTeam = (boolean) player.getTemporaryAttributtes().get("TeamSplitRed");
						if ((isInRedTeam && isSafe(new WorldTile(player.getX(), player.getY(), player.getPlane()),
								redSafeLocations))
								|| (!isInRedTeam
										&& isSafe(new WorldTile(player.getX(), player.getY(), player.getPlane()),
												greenSafeLocations)))
							continue;
						player.applyHit(new Hit(thisNPC, 800, HitLook.REGULAR_DAMAGE));
						player.getTemporaryAttributtes().remove("TeamSplitRed");
					}
					for (Player player : instance.getPlayersOnBattle()) {
						if (player == null || player.isDead())
							continue;
						player.getAppearence().setGlowRed(false);
					}
					getCombat().setTarget(lastTarget);
					break;
				case 14:
					for (int i = 0; i < teamSplitSquares.length; i++) {
						WorldObject square = teamSplitSquares[i];
						if (World.containsObjectWithId(new WorldTile(square), 88882)
								|| World.containsObjectWithId(new WorldTile(square), 88881))
							World.removeObject(square);
						teamSplitSquares[i] = null;
					}
					stop();
					break;
				}
				count++;
			}
		}, 0, 0);
	}

	public void sendGreenBomb() {
		setNextAnimation(new Animation(20371));
		setNextGraphics(new Graphics(4268));
		WorldTile voragoSquare = phase == 5 ? this : getVoragoSquare();
		WorldTile mistLocation = phase == 5 ? getTile(new WorldTile(3092, 5984, 0)) : getRandomUsableLocation();
		int distance = Utils.getDistance(voragoSquare, mistLocation);
		double speed = 0.04 + (distance / 24.00);
		Projectile projectile = World.sendProjectileNew(this, mistLocation, 4269, 90, 0, 10, speed, 0, 0);
		int cycleTime = Utils.projectileTimeToCycles(projectile.getEndTime()) - 1;
		Vorago thisNPC = this;
		greenBombP = null;
		WorldTasksManager.schedule(new WorldTask() {
			@Override
			public void run() {
				if (phase != 5) {
					int index = getAvailableMistIndex();
					if (index != -1) {
						mists[index] = new WorldObject(87322, 10, 0, new WorldTile(mistLocation.getX() - 5,
								mistLocation.getY() - 3, mistLocation.getPlane()));
						World.spawnObject(mists[index]);
					}
				}
				for (Player player : instance.getPlayersOnBattle()) {
					if (player == null || player.isDead())
						continue;
					player.getTemporaryAttributtes().remove("GreenBombed");
				}
				greenBombBounces = (phase == 3) ? 5 : 4;
				greenBombP = getRandomTarget(mistLocation, null, 24);
				VoragoInstance.sendMessage(greenBombP, 1,
						"<col=ff0000>Vorago has sent a green bomb after you. Run!</col>");
				int distance = Utils.getDistance(mistLocation, new WorldTile(greenBombP));
				double speed = 0.02 + (distance / 24.00);
				int angle = (int) Math
						.round(Math
								.toDegrees(Math.atan2(
										(mistLocation.getX() * 2 + 1) - (greenBombP.getX() * 2 + greenBombP.getSize()),
										(mistLocation.getY() * 2 + 1) - (greenBombP.getY() * 2 + greenBombP.getSize())))
						/ 45d) & 0x7;
				Projectile projectile = World.sendProjectileNew(mistLocation, new WorldTile(greenBombP), 4269, 0, 10,
						10, speed, angle, 0);
				int cycleTime = Utils.projectileTimeToCycles(projectile.getEndTime()) - 1;
				WorldTasksManager.schedule(new WorldTask() {
					int count = 0;
					int startTime = cycleTime;

					@Override
					public void run() {
						if (greenBombBounces == 0 || getHitpoints() == 0) {
							greenBombP = null;
							for (Player player : instance.getPlayersOnBattle()) {
								if (player == null || player.isDead())
									continue;
								player.getTemporaryAttributtes().remove("GreenBombed");
							}
							stop();
							return;
						}
						if (count == startTime) {
							if (getPlayersNearby(greenBombP, 2) == 0
									|| (greenBombP.getTemporaryAttributtes().get("GreenBombed") != null
											&& greenBombBounces != 1)) {
								greenBombP.setNextGraphics(new Graphics(4270));
								greenBombP.applyHit(new Hit(thisNPC, 100, HitLook.REGULAR_DAMAGE));
								if (greenBombP.getCombatDefinitions().isAutoRelatie() && !greenBombP.hasWalkSteps())
									greenBombP.getActionManager().setAction(new PlayerCombat(thisNPC));
								if (phase == 3)
									attackProgress = attackProgress < 10 ? 6 : 16;
								greenBombBounces = 0;
								greenBombP.getTemporaryAttributtes().remove("GreenBombed");
								greenBombP = null;
								stop();
								return;
							}
							for (Player player : instance.getPlayersOnBattle()) {
								if (player == null || player.isDead() || !Utils.isOnRange(player, greenBombP, 0)
										|| greenBombBounces == 1)
									continue;
								player.applyHit(new Hit(thisNPC, 100, HitLook.REGULAR_DAMAGE));
								int perc = player.getCombatDefinitions().getSpecialAttackPercentage();
								if (perc != 0)
									player.getCombatDefinitions().desecreaseSpecialAttack(perc < 10 ? (10 - perc) : 10);
							}
							Player p2 = getRandomTarget(new WorldTile(greenBombP), greenBombP, 2);
							int distance = Utils.getDistance(new WorldTile(greenBombP), new WorldTile(p2));
							double speed = 0.02 + (distance / 24.00);
							int angle = (int) Math.round(Math
									.toDegrees(Math.atan2((greenBombP.getX() * 2 + greenBombP.getSize())
											- (p2.getX() * 2 + p2.getSize()),
									(greenBombP.getY() * 2 + greenBombP.getSize()) - (p2.getY() * 2 + p2.getSize())))
									/ 45d) & 0x7;
							Projectile projectile = World.sendProjectileNew(new WorldTile(greenBombP),
									new WorldTile(p2), 4269, 10, 30, 10, speed, angle, 0);
							int cycleTime = Utils.projectileTimeToCycles(projectile.getEndTime()) - 1;
							count = 0;
							startTime = cycleTime;
							greenBombP.getTemporaryAttributtes().put("GreenBombed", Boolean.TRUE);
							greenBombP = p2;
							greenBombBounces--;
							return;
						}
						count++;
					}
				}, 0, 0);
			}
		}, cycleTime);
	}

	public boolean isUnderMist(Player player) {
		for (WorldObject mist : mists) {
			if (mist == null)
				continue;
			WorldTile centerM = new WorldTile(mist.getX() + 5, mist.getY() + 3, mist.getY());
			if (player.getX() >= (centerM.getX() - 4) && player.getX() <= (centerM.getX() + 4)
					&& player.getY() >= (centerM.getY() - 4) && player.getY() <= (centerM.getY() + 4))
				return true;
		}
		return false;
	}

	public int getPlayersNearby(Player target, int withinDistance) {
		int count = 0;
		for (Player player : instance.getPlayersOnBattle()) {
			if (player == null || player.isDead() || Utils.getDistance(target, player) > withinDistance
					|| player == target)
				continue;
			count++;
		}
		return count > 5 ? 5 : count;
	}

	public Player getRandomTarget(WorldTile tile, Player exception, int maxDistance) {
		List<Player> availablePlayers = new ArrayList<Player>();
		for (int i = 0; i < instance.getPlayersOnBattle().size(); i++) {
			Player player = instance.getPlayersOnBattle().get(i);
			if (player == null || player.isDead() || (exception != null && player == exception)
					|| Utils.getDistance(tile, player) > maxDistance)
				continue;
			availablePlayers.add(player);
		}
		return availablePlayers.isEmpty() ? null : availablePlayers.get(Utils.random(availablePlayers.size() - 1));
	}

	public int getAvailableMistIndex() {
		for (int i = 0; i < mists.length; i++) {
			if (mists[i] == null)
				return i;
		}
		return -1;
	}

	public boolean sendVitalisOrb() {
		if (phase == 5 && !World.checkWalkStep(getPlane(), getX() - 5, getY() + 2, 1, 1))
			return false;
		setNextAnimation(new Animation(20328));
		setNextGraphics(new Graphics(4026));
		WorldTile closestVitalisSpawn = phase == 5 ? new WorldTile(getX() - 5, getY() + 2, getPlane())
				: getVitalisSpawnLocation();
		Projectile projectile = World.sendProjectileNew(this, closestVitalisSpawn, 4027, 0, 0, 10, 0.3, 0, 0);
		int cycleTime = Utils.projectileTimeToCycles(projectile.getEndTime()) - 1;
		Vorago thisNPC = this;
		WorldTasksManager.schedule(new WorldTask() {
			int vitalisCount = phase == 5 ? 3 : 5;

			@Override
			public void run() {
				World.sendGraphics(null, new Graphics(4047), closestVitalisSpawn);
				for (Player player : instance.getPlayersOnBattle()) {
					if (player == null || player.isDead()
							|| !isInSquare(player, closestVitalisSpawn.getX() - 2, closestVitalisSpawn.getX() + 2,
									closestVitalisSpawn.getY() - 2, closestVitalisSpawn.getY() + 2))
						continue;
					vitalisCount--;
					CombatScript.delayHit(thisNPC, 0, player, new Hit(thisNPC, 200, HitLook.REGULAR_DAMAGE));
					player.setNextGraphics(new Graphics(4028));
				}
				for (int i = 0; i < vitalisCount; i++) {
					WorldTile spawnTile = instance.randomSpawnTile(null, closestVitalisSpawn, phase == 5 ? 1 : 2);
					int index = getAvailableIndex();
					if (index == -1)
						continue;
					vitali[index] = new Vitalis(17157, spawnTile, -1, true, thisNPC);
					vitali[index].setNextAnimation(new Animation(20381));
				}
			}
		}, cycleTime);
		return true;
	}

	public void sendVitalisDeath(Vitalis vitalis) {
		for (int i = 0; i < vitali.length; i++) {
			if (vitali[i] == vitalis)
				vitali[i] = null;
		}
	}

	public int getAvailableIndex() {
		for (int i = 0; i < vitali.length; i++) {
			if (vitali[i] == null)
				return i;
		}
		return -1;
	}

	private boolean isInSquare(WorldTile tile, int smallestX, int largestX, int smallestY, int largestY) {
		return (tile.getX() >= smallestX && tile.getX() <= largestX && tile.getY() >= smallestY
				&& tile.getY() <= largestY);
	}

	private WorldTile getVitalisSpawnLocation() {
		WorldTile[] jumpLocationsPhase = new WorldTile[locations.length];
		for (int i = 0; i < jumpLocationsPhase.length; i++) {
			switch (phase) {
			case 3:
				jumpLocationsPhase[i] = getTile(
						new WorldTile(locations[i].getX(), locations[i].getY() - 64, locations[i].getPlane()));
				break;
			case 4:
				jumpLocationsPhase[i] = getTile(
						new WorldTile(locations[i].getX() - 64, locations[i].getY() - 128, locations[i].getPlane()));
				break;
			}
		}
		WorldTile voragoSquare = getVoragoSquare();
		List<WorldTile> closestLocations = new ArrayList<WorldTile>();
		if (voragoSquare.getX() == jumpLocationsPhase[4].getX()
				&& voragoSquare.getY() == jumpLocationsPhase[4].getY()) {
			closestLocations.add(jumpLocationsPhase[1]);
			closestLocations.add(jumpLocationsPhase[2]);
			closestLocations.add(jumpLocationsPhase[5]);
		} else {
			for (int i = 0; i < jumpLocationsPhase.length; i++) {
				WorldTile close = jumpLocationsPhase[i];
				if (close.getX() == voragoSquare.getX() && close.getY() == voragoSquare.getY())
					continue;
				if (Utils.getDistance(close, voragoSquare) > 12)
					continue;
				closestLocations.add(close);
			}
		}
		return closestLocations.get(Utils.random(closestLocations.size() - 1));
	}

	public WorldTile getVoragoSquare() {
		WorldTile[] jumpLocationsPhase = new WorldTile[locations.length];
		for (int i = 0; i < jumpLocationsPhase.length; i++) {
			switch (phase) {
			case 3:
				jumpLocationsPhase[i] = getTile(
						new WorldTile(locations[i].getX(), locations[i].getY() - 64, locations[i].getPlane()));
				break;
			case 4:
				jumpLocationsPhase[i] = getTile(
						new WorldTile(locations[i].getX() - 64, locations[i].getY() - 128, locations[i].getPlane()));
				break;
			}
		}
		for (int i = 0; i < jumpLocationsPhase.length; i++) {
			WorldTile center = jumpLocationsPhase[i];
			if (getX() >= (center.getX() - 3) && getX() <= (center.getX() + 3) && getY() >= (center.getY() - 3)
					&& getY() <= (center.getY() + 3))
				return center;
		}
		return null;
	}

	public void spawnScopuli() {
		WorldTile center = instance.getVoragoSpawnLocation(phase);
		WorldTile spawnLocation = new WorldTile(center.getX() - 2, center.getY() - 4, center.getPlane());
		for (int i = 0; i < scopuli.length; i++) {
			scopuli[i] = new Scopulus(17185,
					i == 0 ? spawnLocation
							: new WorldTile(spawnLocation.getX() + 10, spawnLocation.getY(), spawnLocation.getPlane()),
					-1, true, this);
		}
	}

	public void sendScopulusDeath(Scopulus scop) {
		int index = 0;
		for (int i = 0; i < scopuli.length; i++) {
			if (scopuli[i] == scop)
				scopuli[i] = null;
			else
				index = i;
		}
		if (getScopuliCount() == 1) {
			Scopulus aliveScop = scopuli[index];
			if (aliveScop != null && !aliveScop.isDead()) {
				aliveScop.setNextForceTalk(new ForceTalk("RAAAAAAAAGGGGHHH"));
				aliveScop.heal(15000);
			}
		}
		if (getScopuliCount() == 0)
			sendDeath(null);
	}

	public int getScopuliCount() {
		int count = 0;
		for (int i = 0; i < scopuli.length; i++) {
			if (scopuli[i] == null || scopuli[i].isDead())
				continue;
			count++;
		}
		return count;
	}

	public boolean canSendCeilingCollapse() {
		int count = 0;
		for (WorldObject ceiling : ceilingCollapses) {
			if (ceiling == null)
				continue;
			count++;
		}
		return count < 6;
	}

	public boolean sendCeilingCollapse() {
		if (!canSendCeilingCollapse())
			return false;
		setNextAnimation(new Animation(20369));
		WorldTile usableLocation = getRandomUsableLocation();
		WorldTile jumpLocation = getRandomUsableLocation(usableLocation);
		WorldTile ceilingLocation = new WorldTile(usableLocation.getX() - 5, usableLocation.getY() - 3,
				usableLocation.getPlane());
		WorldTasksManager.schedule(new WorldTask() {
			int count = 0;

			@Override
			public void run() {
				if (getHitpoints() == 0) {
					stop();
					return;
				}
				switch (count) {
				case 3:
					World.sendGraphics(null, new Graphics(4025), usableLocation);
					break;
				case 5:
					setNextFaceWorldTile(jumpLocation);
					setNextAnimation(new Animation(20365));
					break;
				case 7:
					spawnCeilingCollapse(ceilingLocation);
					setNextAnimation(new Animation(20367));
					setNextWorldTile(new WorldTile(jumpLocation.getX() - 2, jumpLocation.getY() - 2, 0));
					break;
				case 9:
					for (Player player : instance.getPlayersOnBattle()) {
						if (player == null || player.isDead() || !isStuckUnderRock(player, null))
							continue;
						player.lock(1);
						player.setNextWorldTile(new WorldTile(getX(), getY(), getPlane()));
					}
					stop();
					break;
				}
				count++;
			}

		}, 0, 1);
		return true;
	}

	private void spawnCeilingCollapse(WorldTile ceilingLocation) {
		spawnCeilingCollapse(ceilingLocation, null);
	}

	public void spawnCeilingCollapse(WorldTile ceilingLocation, WorldObject ceiling) {
		World.spawnObject(new WorldObject(84963, 10, 3, ceilingLocation));
		Vorago thisNPC = this;
		WorldTasksManager.schedule(new WorldTask() {
			@Override
			public void run() {
				World.removeObject(World.getObjectWithId(ceilingLocation, 84963));
				if (ceiling == null)
					for (int i = 0; i < ceilingCollapses.length; i++) {
						if (ceilingCollapses[i] == null) {
							ceilingCollapses[i] = new WorldObject(84962, 10, 3, ceilingLocation);
							World.spawnObject(ceilingCollapses[i]);
							break;
						}
					}
				else
					World.spawnObject(ceiling);
				int underRock = getPlayersUnderRock(ceilingLocation);
				int damage = (underRock == 0) ? 0 : (800 / underRock);
				if (damage == 0)
					return;
				for (Player player : instance.getPlayersOnBattle()) {
					if (player == null || player.isDead() || !isStuckUnderRock(player, ceilingLocation))
						continue;
					player.setNextAnimation(new Animation(20338));
					WorldTile toTile = findBestLocation(new WorldTile(player), ceilingLocation);
					if (toTile != null) {
						player.setNextForceMovement(new ForceMovement(toTile, 1, getMoveDirection(player)));
						player.setNextWorldTile(toTile);
					}
					player.applyHit(new Hit(thisNPC, damage, HitLook.REGULAR_DAMAGE));
				}
			}
		}, 0);
	}

	public WorldTile findBestLocation(WorldTile playerL, WorldTile ceilingL) {
		ArrayList<WorldTile> unCheckedTeleTiles = new ArrayList<WorldTile>();
		for (int x = (ceilingL.getX() - 1); x <= ceilingL.getX() + 10; x++) {
			for (int y = (ceilingL.getY() - 1); y <= ceilingL.getY() + 10; y++) {
				unCheckedTeleTiles.add(new WorldTile(x, y, ceilingL.getPlane()));
			}
		}
		ArrayList<WorldTile> checkedTeleTiles = new ArrayList<WorldTile>();
		for (WorldTile checkTile : unCheckedTeleTiles) {
			if (instance.ignoreTile(checkTile)
					|| !World.isTileFree(checkTile.getPlane(), checkTile.getX(), checkTile.getY(), 1))
				continue;
			checkedTeleTiles.add(checkTile);
		}
		int farestDistance = Integer.MAX_VALUE;
		int index = 0;
		for (int i = 0; i < checkedTeleTiles.size(); i++) {
			WorldTile checkedTile = checkedTeleTiles.get(i);
			int distance = Utils.getDistance(checkedTile, playerL);
			if (distance < farestDistance) {
				index = i;
				farestDistance = distance;
			}
		}
		return checkedTeleTiles.isEmpty() ? null : checkedTeleTiles.get(index);
	}

	// (3104, 6111, 0)

	public void removeMiddleCeilingObject() {
		WorldTile middleCeilingLocation = getTile(new WorldTile(3035, 5980, 0));
		if (getCeilingIndex(middleCeilingLocation) == -1)
			return;
		WorldObject middleCeiling = ceilingCollapses[getCeilingIndex(middleCeilingLocation)];
		if (middleCeiling == null)
			return;
		World.removeObject(middleCeiling);
		ceilingCollapses[getCeilingIndex(middleCeilingLocation)] = null;
	}

	public boolean isStuckUnderRock(Player player, WorldTile location) {
		if (location != null) {
			if (player.getX() >= (location.getX() + 1) && player.getX() <= (location.getX() + 8)
					&& player.getY() >= (location.getY() + 1) && player.getY() <= (location.getY() + 8))
				return true;
			return false;
		}
		for (WorldObject ceiling : ceilingCollapses) {
			if (ceiling == null)
				continue;
			if (player.getX() >= (ceiling.getX() + 1) && player.getX() <= (ceiling.getX() + 8)
					&& player.getY() >= (ceiling.getY() + 1) && player.getY() <= (ceiling.getY() + 8))
				return true;
		}
		return false;
	}

	public int getPlayersUnderRock(WorldTile ceilingLocation) {
		int count = 0;
		for (Player player : instance.getPlayersOnBattle()) {
			if (player == null || player.isDead() || !isStuckUnderRock(player, ceilingLocation))
				continue;
			count++;
		}
		return count;
	}

	public int getCeilingIndex(WorldTile tile) {
		for (int i = 0; i < ceilingCollapses.length; i++) {
			WorldObject ceiling = ceilingCollapses[i];
			if (ceiling == null)
				continue;
			if (ceiling.getX() == tile.getX() && ceiling.getY() == tile.getY() && ceiling.getPlane() == tile.getPlane())
				return i;
		}
		return -1;
	}

	public void sendCrackGround() {
		int[] xstart = { 3090, 3026, 3090, 3026 };
		int[] ystart = { 6098, 6034, 6034, 5970 };
		if ((phase - 1) == 4)
			return;
		if ((phase - 1) < 0 || (phase - 1) > Math.min(xstart.length, ystart.length))
			return;
		World.spawnObjectTemporary(
				new WorldObject(84873, 10, 1, getTile(new WorldTile(xstart[phase - 1], ystart[phase - 1], 0))), 5000);
		World.spawnObjectTemporary(
				new WorldObject(84871, 10, 1, getTile(new WorldTile(xstart[phase - 1], ystart[phase - 1] + 9, 0))),
				5000);
		World.spawnObjectTemporary(
				new WorldObject(84873, 10, 2, getTile(new WorldTile(xstart[phase - 1], ystart[phase - 1] + 18, 0))),
				5000);
		World.spawnObjectTemporary(
				new WorldObject(84871, 10, 0, getTile(new WorldTile(xstart[phase - 1] + 9, ystart[phase - 1], 0))),
				5000);
		World.spawnObjectTemporary(
				new WorldObject(84869, 10, 0, getTile(new WorldTile(xstart[phase - 1] + 9, ystart[phase - 1] + 9, 0))),
				5000);
		World.spawnObjectTemporary(
				new WorldObject(84871, 10, 2, getTile(new WorldTile(xstart[phase - 1] + 9, ystart[phase - 1] + 18, 0))),
				5000);
		World.spawnObjectTemporary(
				new WorldObject(84873, 10, 0, getTile(new WorldTile(xstart[phase - 1] + 18, ystart[phase - 1], 0))),
				5000);
		World.spawnObjectTemporary(
				new WorldObject(84871, 10, 3, getTile(new WorldTile(xstart[phase - 1] + 18, ystart[phase - 1] + 9, 0))),
				5000);
		World.spawnObjectTemporary(new WorldObject(84873, 10, 3,
				getTile(new WorldTile(xstart[phase - 1] + 18, ystart[phase - 1] + 18, 0))), 5000);
	}

	public void spawnGravityField() {
		if (phaseProgress == 5)
			return;
		if (gravityField != null) {
			World.removeObject(gravityField);
		}
		WorldTile wrongLocation = locations[Utils.random(8)];
		WorldTile spawnTile = instance
				.getTile(new WorldTile(wrongLocation.getX() - 64, wrongLocation.getY() - 64, wrongLocation.getPlane()));
		gravityField = new WorldObject(84959, 10, 1, spawnTile);
		World.spawnObject(gravityField);
	}

	public void fireGravityField(Player player) {
		if (player.isLocked())
			return;
		if (player.getTemporaryAttributtes().get("FiringGravityField") != null)
			return;
		if (!Utils.isOnRange(new WorldTile(gravityField.getX(), gravityField.getY(), gravityField.getPlane()), this, 7,
				1, getSize())) {
			player.getPackets().sendGameMessage("<col=ff0000>Vorago is not close enough!</col>");
			return;
		}
		player.getTemporaryAttributtes().put("FiringGravityField", Boolean.TRUE);
		player.lock(3);
		Projectile projectile = World.sendProjectileNew(new WorldTile(gravityField.getX(), gravityField.getY(), 0),
				this, 4029, 0, 30, 10, 0.5, 30, 1);
		int cycleTime = Utils.projectileTimeToCycles(projectile.getEndTime()) - 1;
		World.removeObject(gravityField);
		phaseProgress++;
		gravityField = null;
		WorldTasksManager.schedule(new WorldTask() {
			@Override
			public void run() {
				setNextGraphics(new Graphics(4030));
				player.getTemporaryAttributtes().remove("FiringGravityField");
				if (phaseProgress == 4) {
					sendBringHimDown();
				}
			}
		}, cycleTime);
	}

	@Override
	public void setFinished(boolean finished) {
		removeLeftOvers();
		super.setFinished(finished);
	}

	public WorldTile getRandomUsableLocation() {
		return getRandomUsableLocation(null);
	}

	public WorldTile getRandomUsableLocation(WorldTile exception) {
		return getRandomUsableLocation(exception, false);
	}

	public WorldTile getRandomUsableLocation(WorldTile exception, boolean atPhaseFinish) {
		WorldTile[] locs = new WorldTile[9];
		for (int i = 0; i < locations.length; i++) {
			switch (phase) {
			case 1:
				locs[i] = getTile(locations[i]);
				break;
			case 2:
				locs[i] = getTile(
						new WorldTile(locations[i].getX() - 64, locations[i].getY() - 64, locations[i].getPlane()));
				break;
			case 3:
				locs[i] = getTile(
						new WorldTile(locations[i].getX(), locations[i].getY() - 64, locations[i].getPlane()));
				break;
			case 4:
				locs[i] = getTile(
						new WorldTile(locations[i].getX() - 64, locations[i].getY() - 128, locations[i].getPlane()));
				break;
			}
		}
		List<WorldTile> availableLocations = new ArrayList<WorldTile>();
		if (phase == 5) {
			WorldTile[] locationsPhase5 = { getTile(new WorldTile(3092, 5985, 0)),
					getTile(new WorldTile(3094, 5985, 0)), getTile(new WorldTile(3104, 5985, 0)),
					getTile(new WorldTile(3106, 5985, 0)) };
			for (int i = 0; i < locationsPhase5.length; i++) {
				WorldTile checkLocation = locationsPhase5[i];
				if ((exception != null && exception.matches(checkLocation)) || getX() < checkLocation.getX())
					continue;
				availableLocations.add(checkLocation);
			}
		} else {
			for (int i = 0; i < locs.length; i++) {
				WorldTile checkLocation = locs[i];
				if (!atPhaseFinish && ((World
						.containsObjectWithId(new WorldTile(checkLocation.getX() - 5, checkLocation.getY() - 3,
								checkLocation.getPlane()), Settings.VORAGO_ROTATION == 0 ? 84962 : 87322)
						&& getAvailableMistIndex() != -1) || (exception != null && checkLocation.matches(exception))))
					continue;
				availableLocations.add(checkLocation);
			}
		}
		return availableLocations.get(Utils.random(availableLocations.size() - 1));
	}

	public int getMoveDirection(Player player) {
		switch (player.getDirection()) {
		case 10240:// north-east
			return ForceMovement.NORTH_EAST;
		case 6144:// north-west
			return ForceMovement.NORTH_WEST;
		case 2048:// south-west
			return ForceMovement.SOUTH_WEST;
		case 14336:// south-east
			return ForceMovement.SOUTH_EAST;
		case 4096:// west
			return ForceMovement.WEST;
		case 12288:// east
			return ForceMovement.EAST;
		case 8192:// north
			return ForceMovement.NORTH;
		case 0:// south
		default:
			return ForceMovement.SOUTH;
		}
	}

	public boolean canFinishPhase() {
		switch (phase) {
		case 1:
			return phaseProgress == 1;
		case 2:
			return phaseProgress == 5;
		case 3:
			return phaseProgress == 0;
		case 4:
			return phaseProgress == 3;
		case 5:
			return false;
		}
		return false;
	}

	public int getPhase() {
		return phase;
	}

	public void setPhase(int phase) {
		this.phase = phase;
	}

	public int getPhaseProgress() {
		return phaseProgress;
	}

	public void setPhaseProgress(int phaseProgress) {
		this.phaseProgress = phaseProgress;
	}

	public int getAttackProgress() {
		return attackProgress;
	}

	public void setAttackProgress(int attackProgress) {
		this.attackProgress = attackProgress;
	}

	public WorldObject[] getCeilingColapses() {
		return ceilingCollapses;
	}

	public WorldObject getGravityField() {
		return gravityField;
	}

	public Player getTargetedPlayer() {
		return targetedPlayer;
	}

	public void setTargetedPlayer(Player targetedPlayer) {
		this.targetedPlayer = targetedPlayer;
	}

	public int getBringHimPoints() {
		return bringHimPoints;
	}

	public void setBringHimPoints(int bringHimPoints) {
		this.bringHimPoints = bringHimPoints;
	}

	public WorldTile getTile(WorldTile tile) {
		return instance.getTile(tile);
	}

	public VoragoInstance getVoragoInstance() {
		return instance;
	}
}*/