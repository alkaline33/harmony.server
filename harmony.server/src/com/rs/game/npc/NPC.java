package com.rs.game.npc;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

import com.rs.Settings;
import com.rs.cache.loaders.ItemDefinitions;
import com.rs.cache.loaders.NPCDefinitions;
import com.rs.cores.CoresManager;
import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.ForceTalk;
import com.rs.game.Graphics;
import com.rs.game.Hit;
import com.rs.game.Hit.HitLook;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.npc.combat.NPCCombat;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.npc.familiar.Familiar;
import com.rs.game.npc.playersystems.CalamityRewards;
import com.rs.game.npc.playersystems.DropLogRecorder;
import com.rs.game.npc.playersystems.DropTracker;
import com.rs.game.npc.playersystems.DungRewards;
import com.rs.game.npc.playersystems.KillCounter;
import com.rs.game.npc.playersystems.PVMPointIssuer;
import com.rs.game.npc.playersystems.PetDropper;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.FlyingEntityHunter.FlyingEntities;
import com.rs.game.player.actions.HerbCleaning;
import com.rs.game.player.content.Burying;
import com.rs.game.player.content.DailyChallenges;
import com.rs.game.player.content.FriendChatsManager;
import com.rs.game.player.content.Magic;
import com.rs.game.player.content.RecycleCentreMain;
import com.rs.game.player.content.event.season.SeasonEvent;
import com.rs.game.player.controlers.WGuildControler;
import com.rs.game.player.controlers.Wilderness;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Colors;
import com.rs.utils.Logger;
import com.rs.utils.MapAreas;
import com.rs.utils.NPCBonuses;
import com.rs.utils.NPCCombatDefinitionsL;
import com.rs.utils.NPCDrops;
import com.rs.utils.Utils;
import org.jetbrains.annotations.NotNull;

public class NPC extends Entity implements Serializable {

	private static final long serialVersionUID = -4794678936277614443L;

	private int id;
	private WorldTile respawnTile;
	private int mapAreaNameHash;
	private boolean canBeAttackFromOutOfArea;
	private boolean randomwalk;
	private int[] bonuses;
	// 0 stab, 1 slash, 2 crush,3 mage, 4 range, 5 stab
	// def, blahblah till 9
	private boolean spawned;
	private transient NPCCombat combat;
	public WorldTile forceWalk;
	private static int pet123a;
	private boolean intelligentRouteFinder;
	// pets
	// private static int hasgotsuperpet;
	private static int hasgotarmadylpet;
	private static int hasgotawyrmpet;
	private static int hasgotbandospet;
	private static int hasgotsaradominpet;
	private static int hasgotzamorakpet;
	private static int hasgotglacorpet;
	private static int hasgotcorpet;
	private static int hasgotsunfreetpet;
	private static int hasgotkbdpet;
	private static int hasgotlegioepet;
	private static int hasgotnexpet;
	private static int hasgotvoragopet;
	private static int hasgotbadsantapet;
	private static int hasgotdrygonpet;
	private static int hasgottectonicbeastpet;
	private static int hasgotdryaxpet;
	public static int hasgotthunderouspet;
	public static int hasgotautomatonpet;
	public static int hasgotqbdpet;
	public static int hasgotaniviapet;

	public long spawnedblinks;

	private long lastAttackedByTarget;
	private boolean cantInteract;
	private int capDamage;
	private int lureDelay;
	private boolean cantFollowUnderCombat;
	private boolean forceAgressive;
	private int forceTargetDistance;
	private boolean forceFollowClose;
	public boolean forceMultiAttacked;
	private boolean noDistanceCheck;

	// sliske
	public boolean bandosweek = false;
	public boolean zamorakweek = false;
	public boolean saradominweek = false;
	public boolean aramdylweek = false;

	// npc masks
	private transient Transformation nextTransformation;
	// name changing masks
	private String name;
	private transient boolean changedName;
	private int combatLevel;
	private transient boolean changedCombatLevel;
	private transient boolean locked;

	public NPC(int id, WorldTile tile, int mapAreaNameHash, boolean canBeAttackFromOutOfArea) {
		this(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, false);
	}

	/*
	 * creates and adds npc
	 */
	public NPC(int id, WorldTile tile, int mapAreaNameHash, boolean canBeAttackFromOutOfArea, boolean spawned) {
		super(tile);
		this.id = id;
		respawnTile = new WorldTile(tile);
		this.mapAreaNameHash = mapAreaNameHash;
		this.canBeAttackFromOutOfArea = canBeAttackFromOutOfArea;
		setSpawned(spawned);
		combatLevel = -1;
		setHitpoints(getMaxHitpoints());
		setDirection(getRespawnDirection());
		setRandomWalk((getDefinitions().walkMask & 0x2) != 0
				|| forceRandomWalk(id));
		bonuses = NPCBonuses.getBonuses(id);
		combat = new NPCCombat(this);
		capDamage = -1;
		lureDelay = 12000;
		// npc is inited on creating instance
		initEntity();
		World.addNPC(this);
		World.updateEntityRegion(this);
		// npc is started on creating instance
		loadMapRegions();
		checkMultiArea();
	}

	@Override
	public boolean needMasksUpdate() {
		return super.needMasksUpdate() || nextTransformation != null || changedCombatLevel || changedName;
	}

	public void transformIntoNPC(int id) {
		setNPC(id);
		nextTransformation = new Transformation(id);
	}

	public void setNPC(int id) {
		this.id = id;
		setBonuses();
	}

	@Override
	public void resetMasks() {
		super.resetMasks();
		nextTransformation = null;
		changedCombatLevel = false;
		changedName = false;
	}

	public int getMapAreaNameHash() {
		return mapAreaNameHash;
	}

	public void setCanBeAttackFromOutOfArea(boolean b) {
		canBeAttackFromOutOfArea = b;
	}

	public boolean canBeAttackFromOutOfArea() {
		return canBeAttackFromOutOfArea;
	}

	public NPCDefinitions getDefinitions() {
		return NPCDefinitions.getNPCDefinitions(id);
	}

	public NPCCombatDefinitions getCombatDefinitions() {
		return NPCCombatDefinitionsL.getNPCCombatDefinitions(id);
	}

	public static int dayOfWeek() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.DAY_OF_WEEK);
	}

	@Override
	public int getMaxHitpoints() {
		return getCombatDefinitions().getHitpoints();
	}

	public int getId() {
		return id;
	}
	

	public void processNPC() {
/*		if (id == 9085) {
			WorldTasksManager.schedule(new WorldTask() {
				int loop;
				int davespeak;

				@Override
				public void run() {
					if (loop >= 0) {
						davespeak = Utils.random(50);
						switch (davespeak) {
						case 0:
							setNextForceTalk(new ForceTalk("You can find Shops & other helpful NPC's up the stairs, behind me."));
							break;
						default:
							break;
						}

					}
					loop++;
				}
			}, 0, 10);
		}*/
		if (id == 15) {
			setName("<col=E18ADB>Lauren New-Years</col>");
			setCombatLevel(2017);
		}
		if (id == 100 || id == 313 || id == 14706 || id == 30171 || id == 30158 || id == 30159 || id == 30160 || id == 30161 || id == 29976 || id == 561 || id == 8031 || id == 3288 || id == 312 || id == 323 || id == 324 || id == 326 || id == 327 || id == 328 || id == 329 || id == 6267 || id == 29980 || id == 30151 || id == 4287 || id == 15012 || id == 30144 || id == 30141 || id == 30047 || id == 30056 || id == 30050 || id == 7891) {
			setCombatLevel(0);
		}
		if (id == 17261) {
			setCombatLevel(490);
		}
		if (id == 17261) {
			setName("Sister of Sliske");
		}
		if (id == 30050) {
			setName("<col=A8A8A8>Red h'ween mask</col>");
		}
		if (id == 6595) {
			setName("<col=00FF00>Donators Gravestone</col>");
		}
		if (id == 6589) {
			setName("<col=00FFFF>Super Donators Gravestone</col>");
		}
		if (id == 6601) {
			setName("<col=FF0000>Extreme Donators Gravestone</col>");
		}
		if (id == 8340) {
			setName("Upgrade Armour");
		}
		if (id == 510) {
			setName("Wild Guard");
		}
		if (id == 501) {
			setName("Wild Banker");
		}
		if (id == 653) {
			setName("<col=ff0000>Hunger Games</col>");
		}
		if (id == 6034) {
			setName("Third-age Expert");
		}
		if (id == 605) {
			setName("Teleport to blood altar");
		}
		if (id == 3002) {
			setName("Vote Point Store!");
		}
		if (id == 3000) {
			setName("Adventures Log");
		}
		if (id == 2996) {
			setName("Donator Harmony Point Store");
		}
		if (id == 13930) {
			setName("Ariane (Member Token Store)");
		}
		if (id == 3709) {
			setName("Mr Ex (Harmony Teleporter)");
		}
		if (id == 2938) {
			setName("PvP Point Store");
		}
		if (id == 566) {
			setName("<col=FF00FF>Staff Keeper");
		}
		if (id == 3705) {
			setName("Max (Harmony Teleporter #2)");
		}
		if (id == 4455) {
			setName("<shad=FF0099>I Can Fix It");
		}
		if (id == 6185) {
			setName("Reward Titles");
		}
		if (id == 6183) {
			setName("Mr Joopz");
		}
		if (id == 481) {
			setName("Misc");
		}
		if (id == 813) {
			setName("<col=00FFFF>Status Upgrade");
		}
		if (id == 711) {
			setName("Enter house");
		}
		if (id == 712) {
			setName("Leave house");
		}
		if (id == 786) {
			setName("Runecrafting Supplies");
		}
		if (id == 8864) {
			setName("Potions & Flasks");
		}
		if (id == 219) {
			setName("Fishing Supplies");
		}
		if (id == 5865) {
			setName("Construction Supplies");
		}
		if (id == 1834) {
			setName("Master Capes");
		}
		if (id == 713) {
			setName("Farming Store");
		}
		if (id == 1923) {
			setName("Ranged Armour Store");
		}
		if (id == 587) {
			setName("Herblore Supplies");
		}
		if (id == 805) {
			setName("Crafting Supplies");
		}
		if (id == 2253) {
			setName("Skillcape Shop");
		}
		if (id == 519) {
			setName("General Skilling");
		}
		if (id == 550) {
			setName("Range weapon Shop");
		}
		if (id == 7950) {
			setName("Weaponry");
		}
		if (id == 546) {
			setName("Magic Shop");
		}
		if (id == 549) {
			setName("Armoury");
		}
		if (id == 13768) {
			setName("Dungeoneering");
		}
		if (id == 3709) {
			setName("Wilderness Task");
		}
		if (id == 589) {
			setName("Connie");
		}
		if (id == 4731) {
			setName("Zik");
		}
		if (id == 2998) {
			setName("<col=00FFFF>Harmony Point Store</col>");
		}
		if (id == 13727) {
			setName("Loyalty Store");
		}
		if (id == 13251) {
			setName("<col=FF00FF>Prestige Master</col>");
		}
		if (id == 556) {
			setName("<col=FF00FF>Donators Shop</col>");
		}
		if (id == 12865) {
			setName("Blinker");
		}
		if (id == 12866) {
			setName("Blinking");
		}
		if (id == 12867) {
			setName("Blinkest");
		}
		if (id == 562) {
			setName("Pure Store");
		}
		if (id == 9052) {
			setName("Zark");
		}
		if (id == 14237) {
			setName("Gulega Raid");
		}
		if (id == 14241) {
			setName("Trio Raid");
		}
		if (id == 11270) {
			setName("Player Wars");
		}
		if (id == 453) {
			setName(Colors.red + "Noob of Zamorak");
		}
		if (id == 7891) {
			setName(Colors.cyan + "Max Hit Dummy");
		}
		if (id == 528) {
			setName("Shop Hub");
		}
		if (id == 30092) {
			setCombatLevel(0);
		}
		if (id == 1065) {
			setName("Bank Presets");
			setCombatLevel(0);
		}
			if (id == 1206) {
				setName("Polling Station");
			}
		if (id == 1972) {
			setName("Weekly Cosmetic Override");
		}
		if (id == 7221) {
			setName("Dave");
		}
		if (id == 14870) {//905
			setName("Skilling Point Store");
		}
		if (id == 568) {
			setName("Leave Raid");
		}
		if (id == 817) {
			setName("Instance");
		}
		if (id == 291) {
			setName("Restore Special Attack");
		}
		if (id == 945) {
			setName("Harmony Guide");
		}
		if (id == 885) {
			setName("Pet Tamer");
		}
		if (id == 2725) {
			setName("Calamity Rewards Shop");
		}
		if (id == 15465) {
			setName("<col=ff0000>*100 points*</col> <col=00FF33>Random Melee Weapon</col>");
		}
		if (id == 13285) {
			setName("<col=ff0000>*100 points*</col> <col=00FF33>Random Range or Mage Weapon</col>");
		}
		if (id == 12320) {
			setName("<col=ff0000>*60 points*</col> <col=660099>Random Melee Armour</col>");
		}
		if (id == 6706) {
			setName("<col=ff0000>*60 points*</col> <col=660099>Random Range or Mage Armour</col>");
		}
		if (id == 11551) {
			setName("<col=ff0000>*50 points*</col> <col=66FFFF>5 Food items</col>");
		}
		if (id == 8329) {
			setName("<col=ff0000>*150 points*</col> <col=FF00FF>4 Potions</col>");
		}
		if (id == 1201) {
			setName("<col=ff0000>*150 points*</col> <col=FF00FF>100 of Rune & Arrow</col>");
		}
		if (id == 6537) {
			setName("Loot Beam Information");
		}
		if (id == 15584) {
			setName("Security");
		}
		if (id == 4949) {
			setName("Miss Experience");
		}
		if (id == 6653) {
			setName("Diary Expert");
		}
		if (id == 9032) {
			setName("Wild Guard");
		}
		if (id == 11750) {
			setCombatLevel(78);
		}

		if (isDead() || locked || getHitpoints() == 0) {
			return;
		}
		if (!combat.process()) { // if not under combat
			if (!isForceWalking()) {// combat still processed for attack delay
				// go down
				// random walk
				if (id == 30059 || id == 30057 || id == 3077 || id == 3709) {
					setRandomWalk(false);
				}

				if (!cantInteract) {
					if (!checkAgressivity()) {
						if (getFreezeDelay() < Utils.currentTimeMillis()) {
							if (hasRandomWalk() && World.getRotation(getPlane(), getX(), getY()) == 0 // temporary
									// fix
									&& Math.random() * 1000.0 < 100.0) {
								int moveX = (int) Math.round(Math.random() * 10.0 - 5.0);
								int moveY = (int) Math.round(Math.random() * 10.0 - 5.0);
								resetWalkSteps();
								if (getMapAreaNameHash() != -1) {
									if (!MapAreas.isAtArea(getMapAreaNameHash(), this)) {
										forceWalkRespawnTile();
										return;
									}
									addWalkSteps(getX() + moveX, getY() + moveY, 5);
								} else {
									addWalkSteps(respawnTile.getX() + moveX, respawnTile.getY() + moveY, 5);
								}
							}
						}
					}
				}
			}
		}
		if (isForceWalking()) {
			if (getFreezeDelay() < Utils.currentTimeMillis()) {
				if (getX() != forceWalk.getX() || getY() != forceWalk.getY()) {
					if (!hasWalkSteps()) {
						addWalkSteps(forceWalk.getX(), forceWalk.getY(), getSize(), true);
					}
					if (!hasWalkSteps()) { // failing finding route
						setNextWorldTile(new WorldTile(forceWalk)); // force
						// tele
						// to
						// the
						// forcewalk
						// place
						forceWalk = null; // so ofc reached forcewalk place
					}
				} else {
					// walked till forcewalk place
					forceWalk = null;
				}
			}
		}
	}

	@Override
	public void processEntity() {
		super.processEntity();
		processNPC();
	}


	public int getRespawnDirection() {
		NPCDefinitions definitions = getDefinitions();
		if (definitions.anInt853 << 32 != 0 && definitions.respawnDirection > 0
				&& definitions.respawnDirection <= 8) {
			return 4 + definitions.respawnDirection << 11;
		}
		return 0;
	}

	/*
	 * forces npc to random walk even if cache says no, used because of fake
	 * cache information
	 */

	private static boolean forceRandomWalk(int npcId) {
		switch (npcId) {
		case 11226:
		case 37615:
		case 37616:
		case 36890:
		case 36891:
		case 36918:
		case 37766:
		case 30048:
		case 14281:
		case 30163:
		case 30165:
		case 30167:
		case 39610:
		case 39609:
		case 39612:
		case 39614:
		case 29999:
		case 38794:
		case 38793:
		case 38792:
		case 38795:
		case 8491:
		case 11411:
		case 6942:
		case 3341:
		case 3342:
		case 3343:
		case 13251:
		case 3709:
		case 2340:
		case 8864:
		case 219:
		case 481:
		case 587:
		case 30083:
		case 805:
		case 2253:
		case 519:
		case 550:
		case 7950:
		case 546:
		case 549:
		case 1552:
		case 33912:
		case 30000:
			return true;
		default:
			return false;
		/*
		 * default: return NPCDefinitions.getNPCDefinitions(npcId).name .equals(
		 * "Icy Bones");
		 */

		}
	}

	public void sendSoulSplit(final Hit hit, final Entity user) {
		final NPC target = this;
		if (hit.getDamage() > 0) {
			World.sendProjectile(user, this, 2263, 11, 11, 20, 5, 0, 0);
		}
		Player player = (Player) user;
		if (player.soulsplitperk) {
			user.heal(hit.getDamage() / 4);
			} else {
			user.heal(hit.getDamage() / 5);
			}
		WorldTasksManager.schedule(new WorldTask() {
			@Override
			public void run() {
				setNextGraphics(new Graphics(2264));
				if (hit.getDamage() > 0) {
					World.sendProjectile(target, user, 2263, 11, 11, 20, 5, 0, 0);
				}
			}
		}, 1);
	}

	@Override
	public void handleIngoingHit(final Hit hit) {
		if (capDamage != -1 && hit.getDamage() > capDamage && Settings.eventdoublehits == 0) {
			hit.setDamage(capDamage);
		} else if (capDamage != -1 && hit.getDamage() > capDamage * 2 && Settings.eventdoublehits > 0) {
			hit.setDamage(capDamage * 2);
		}
		if (hit.getLook() != HitLook.MELEE_DAMAGE && hit.getLook() != HitLook.RANGE_DAMAGE
				&& hit.getLook() != HitLook.MAGIC_DAMAGE) {
			return;
		}
		Entity source = hit.getSource();
		if (source == null) {
			return;
		}
		if (source instanceof Player) {
			final Player p2 = (Player) source;
			if (p2.getPrayer().hasPrayersOn()) {
				if (p2.getPrayer().usingPrayer(1, 18)) {
					sendSoulSplit(hit, p2);
				}
				if (hit.getDamage() == 0) {
					return;
				}
				if (!p2.getPrayer().isBoostedLeech()) {
					if (hit.getLook() == HitLook.MELEE_DAMAGE) {
						if (p2.getPrayer().usingPrayer(1, 19)) {
							p2.getPrayer().setBoostedLeech(true);
							return;
						} else if (p2.getPrayer().usingPrayer(1, 1)) { // sap
							// att
							if (Utils.getRandom(4) == 0) {
								if (p2.getPrayer().reachedMax(0)) {
									p2.getPackets().sendGameMessage(
											"Your opponent has been weakened so much that your sap curse has no effect.",
											true);
								} else {
									p2.getPrayer().increaseLeechBonus(0);
									p2.getPackets().sendGameMessage(
											"Your curse drains Attack from the enemy, boosting your Attack.", true);
								}
								p2.setNextAnimation(new Animation(12569));
								p2.setNextGraphics(new Graphics(2214));
								p2.getPrayer().setBoostedLeech(true);
								World.sendProjectile(p2, this, 2215, 35, 35, 20, 5, 0, 0);
								WorldTasksManager.schedule(new WorldTask() {
									@Override
									public void run() {
										setNextGraphics(new Graphics(2216));
									}
								}, 1);
								return;
							}
						} else {
							if (p2.getPrayer().usingPrayer(1, 10)) {
								if (Utils.getRandom(7) == 0) {
									if (p2.getPrayer().reachedMax(3)) {
										p2.getPackets().sendGameMessage(
												"Your opponent has been weakened so much that your leech curse has no effect.",
												true);
									} else {
										p2.getPrayer().increaseLeechBonus(3);
										p2.getPackets().sendGameMessage(
												"Your curse drains Attack from the enemy, boosting your Attack.", true);
									}
									p2.setNextAnimation(new Animation(12575));
									p2.getPrayer().setBoostedLeech(true);
									World.sendProjectile(p2, this, 2231, 35, 35, 20, 5, 0, 0);
									WorldTasksManager.schedule(new WorldTask() {
										@Override
										public void run() {
											setNextGraphics(new Graphics(2232));
										}
									}, 1);
									return;
								}
							}
							if (p2.getPrayer().usingPrayer(1, 14)) {
								if (Utils.getRandom(7) == 0) {
									if (p2.getPrayer().reachedMax(7)) {
										p2.getPackets().sendGameMessage(
												"Your opponent has been weakened so much that your leech curse has no effect.",
												true);
									} else {
										p2.getPrayer().increaseLeechBonus(7);
										p2.getPackets().sendGameMessage(
												"Your curse drains Strength from the enemy, boosting your Strength.",
												true);
									}
									p2.setNextAnimation(new Animation(12575));
									p2.getPrayer().setBoostedLeech(true);
									World.sendProjectile(p2, this, 2248, 35, 35, 20, 5, 0, 0);
									WorldTasksManager.schedule(new WorldTask() {
										@Override
										public void run() {
											setNextGraphics(new Graphics(2250));
										}
									}, 1);
									return;
								}
							}

						}
					}
					if (hit.getLook() == HitLook.RANGE_DAMAGE) {
						if (p2.getPrayer().usingPrayer(1, 2)) { // sap range
							if (Utils.getRandom(4) == 0) {
								if (p2.getPrayer().reachedMax(1)) {
									p2.getPackets().sendGameMessage(
											"Your opponent has been weakened so much that your sap curse has no effect.",
											true);
								} else {
									p2.getPrayer().increaseLeechBonus(1);
									p2.getPackets().sendGameMessage(
											"Your curse drains Range from the enemy, boosting your Range.", true);
								}
								p2.setNextAnimation(new Animation(12569));
								p2.setNextGraphics(new Graphics(2217));
								p2.getPrayer().setBoostedLeech(true);
								World.sendProjectile(p2, this, 2218, 35, 35, 20, 5, 0, 0);
								WorldTasksManager.schedule(new WorldTask() {
									@Override
									public void run() {
										setNextGraphics(new Graphics(2219));
									}
								}, 1);
								return;
							}
						} else if (p2.getPrayer().usingPrayer(1, 11)) {
							if (Utils.getRandom(7) == 0) {
								if (p2.getPrayer().reachedMax(4)) {
									p2.getPackets().sendGameMessage(
											"Your opponent has been weakened so much that your leech curse has no effect.",
											true);
								} else {
									p2.getPrayer().increaseLeechBonus(4);
									p2.getPackets().sendGameMessage(
											"Your curse drains Range from the enemy, boosting your Range.", true);
								}
								p2.setNextAnimation(new Animation(12575));
								p2.getPrayer().setBoostedLeech(true);
								World.sendProjectile(p2, this, 2236, 35, 35, 20, 5, 0, 0);
								WorldTasksManager.schedule(new WorldTask() {
									@Override
									public void run() {
										setNextGraphics(new Graphics(2238));
									}
								});
								return;
							}
						}
					}
					if (hit.getLook() == HitLook.MAGIC_DAMAGE) {
						if (p2.getPrayer().usingPrayer(1, 3)) { // sap mage
							if (Utils.getRandom(4) == 0) {
								if (p2.getPrayer().reachedMax(2)) {
									p2.getPackets().sendGameMessage(
											"Your opponent has been weakened so much that your sap curse has no effect.",
											true);
								} else {
									p2.getPrayer().increaseLeechBonus(2);
									p2.getPackets().sendGameMessage(
											"Your curse drains Magic from the enemy, boosting your Magic.", true);
								}
								p2.setNextAnimation(new Animation(12569));
								p2.setNextGraphics(new Graphics(2220));
								p2.getPrayer().setBoostedLeech(true);
								World.sendProjectile(p2, this, 2221, 35, 35, 20, 5, 0, 0);
								WorldTasksManager.schedule(new WorldTask() {
									@Override
									public void run() {
										setNextGraphics(new Graphics(2222));
									}
								}, 1);
								return;
							}
						} else if (p2.getPrayer().usingPrayer(1, 12)) {
							if (Utils.getRandom(7) == 0) {
								if (p2.getPrayer().reachedMax(5)) {
									p2.getPackets().sendGameMessage(
											"Your opponent has been weakened so much that your leech curse has no effect.",
											true);
								} else {
									p2.getPrayer().increaseLeechBonus(5);
									p2.getPackets().sendGameMessage(
											"Your curse drains Magic from the enemy, boosting your Magic.", true);
								}
								p2.setNextAnimation(new Animation(12575));
								p2.getPrayer().setBoostedLeech(true);
								World.sendProjectile(p2, this, 2240, 35, 35, 20, 5, 0, 0);
								WorldTasksManager.schedule(new WorldTask() {
									@Override
									public void run() {
										setNextGraphics(new Graphics(2242));
									}
								}, 1);
								return;
							}
						}
					}

					// overall

					if (p2.getPrayer().usingPrayer(1, 13)) { // leech defence
						if (Utils.getRandom(10) == 0) {
							if (p2.getPrayer().reachedMax(6)) {
								p2.getPackets().sendGameMessage(
										"Your opponent has been weakened so much that your leech curse has no effect.",
										true);
							} else {
								p2.getPrayer().increaseLeechBonus(6);
								p2.getPackets().sendGameMessage(
										"Your curse drains Defence from the enemy, boosting your Defence.", true);
							}
							p2.setNextAnimation(new Animation(12575));
							p2.getPrayer().setBoostedLeech(true);
							World.sendProjectile(p2, this, 2244, 35, 35, 20, 5, 0, 0);
							WorldTasksManager.schedule(new WorldTask() {
								@Override
								public void run() {
									setNextGraphics(new Graphics(2246));
								}
							}, 1);
							return;
						}
					}
				}
			}
		}

	}

	@Override
	public void reset() {
		super.reset();
		setDirection(getRespawnDirection());
		combat.reset();
		bonuses = NPCBonuses.getBonuses(id); // back to real bonuses
		forceWalk = null;
	}

	@Override
	public void finish() {
		if (hasFinished()) {
			return;
		}
		setFinished(true);
		World.updateEntityRegion(this);
		World.removeNPC(this);
	}

	public void setRespawnTask() {
		if (!hasFinished()) {
			reset();
			setLocation(respawnTile);
			finish();
		}
		CoresManager.slowExecutor.schedule(new Runnable() {
			@Override
			public void run() {
				try {
					spawn();
				} catch (Throwable e) {
					Logger.handle(e);
				}
			}
		},  getCombatDefinitions().getRespawnDelay() * 600, TimeUnit.MILLISECONDS);
	}

	public void deserialize() {
		if (combat == null) {
			combat = new NPCCombat(this);
		}
		spawn();
	}

	public void spawn() {
		setFinished(false);
		World.addNPC(this);

		if (id == 9928) {
			Settings.ICEBOSSOPEN = 0;
		}
		if (id == 4852) {
			Settings.VAMPYREBOSSOPEN = 0;
		}
		setLastRegionId(0);
		World.updateEntityRegion(this);
		loadMapRegions();
		checkMultiArea();
	}

	public NPCCombat getCombat() {
		return combat;
	}

	public void handlePointsNPCs() {
		Player killer = getMostDamageReceivedSourcePlayer();
		switch (id) {
		}
	}

	@Override
	public void sendDeath(Entity source) {
		final NPCCombatDefinitions defs = getCombatDefinitions();
		resetWalkSteps();
		combat.removeTarget();
		setNextAnimation(null);
		Settings.monsterskilled++;
		WorldTasksManager.schedule(new WorldTask() {
			int loop;

			@Override
			public void run() {
				/*
				 * if (getId() >= 6574 || getId() <= 6601) { drop(); reset();
				 * finish(); stop(); return; }
				 */
				if (loop == 0) {
					setNextAnimation(new Animation(defs.getDeathEmote()));
				} else if (loop >= defs.getDeathDelay()) {
					if (getId() == 30002) {
						for (NPC n : World.getNPCs()) {
							if (n == null || n.getId() != 30009) {
								continue;
							}
							n.setCapDamage(5000);
						}
					}
					if (getId() == 30030) {
						Settings.canskillchopping = false;
						Settings.canskillfirepit = false;
						Settings.canskillfishing = false;
						Settings.canskillmining = true;
						Settings.canclickspawnboss = false;
						Settings.Ingenuitychopping = 0;
						Settings.Ingenuityfirepiting = 0;
						Settings.Ingenuityfishing = 0;
						Settings.Ingenuitymining = 0;
						Settings.timesclickedtospawnsboss = 0;
						World.spawnTemporaryObject(new WorldObject(3274, 10, -1, 2587, 3913, 0), 120000);
						World.spawnObject(new WorldObject(29095, 10, -1, 2588, 3912, 0), true); // firepit
						for (NPC n : World.getNPCs()) {
							if (n == null || n.getId() != 30031) {
								continue;
							}
							pet123a = Utils.random(3);
							switch (pet123a) {
							case 0:
								n.setNextForceTalk(new ForceTalk("It was my honor to fight for you champions."));
								break;
							case 1:
								n.setNextForceTalk(new ForceTalk("The time will come when you and i battle."));
								break;
							case 2:
								n.setNextForceTalk(new ForceTalk("My energy levels are low. Farewell champions."));
								break;
							case 3:
								n.setNextForceTalk(
										new ForceTalk("I must leave now, but i will return upon your call."));
								break;
							}
							n.sendDeath(n);
							// Vorago.sendDeath(n);
						}
					}
					drop();
					handlePointsNPCs();
					reset();
					setLocation(respawnTile);
					finish();
					if (!isSpawned()) {
						setRespawnTask();
					}
					stop();
				}
				loop++;
			}
		}, 0, 1);
	}

	public void drop() {
		try {
			Drop[] drops = NPCDrops.getDrops(id);

			if (drops == null) {
				return;
			}
			
			Player killer = getMostDamageReceivedSourcePlayer();
			if (killer == null) {
				return;
			}
			if (getId() == 38286) {
				if (!killer.getInventory().contains(29321)) {
					killer.sendMessage(Colors.red+"You cannot obtain loot from Skotizo without a Dark Totem!");
					return;
				} else {
					killer.getInventory().deleteItem(29321, 1);
				}
			}
			killer.npckills++;
			DungRewards.processKill(killer, this);
			if (!World.DryVsSuns(killer)) {
				PVMPointIssuer.processKill(killer, this, getX(), getY(), getPlane());
			}
			KillCounter.processKill(killer, this);
			PetDropper.processKill(killer, this);
			CalamityRewards.processKill(killer, this);
			if (getId() == 1552 && !killer.santatitle) {
				killer.sendMessage("Congratulations, you have unlocked of Christmas title. Type ;;santatitle");
				killer.santatitle = true;
				killer.getAppearence().setTitle(56);
			}
			if (getId() == 29982) {
				Magic.sendNormalTeleportSpell(killer, 0, 0, new WorldTile(2850, 3241, 0));
			}
			if (getId() == 15581) {
				killer.sendMessage("Well done. You provided the most damage this kill.");
				Settings.canteletopdemon = false;
				World.spawnTemporaryObject(new WorldObject(2079, 10, -1, 3246, 9867, 0), 120000);
			}
			if (getId() == 12878) {
				World.sendWorldMessage(Colors.orange+"<img=7>" + killer.getDisplayName() + " has obtained the loot from Blink!",
						false);
			}
			if (getId() == 30045 || getId() == 30046) {
				World.sendWorldMessage("<col=9966CC> " + killer.getDisplayName()
						+ " has killed Kuradal's brother and is now making a move with the rewards!", false);
			}
			// SlayerTask task = killer.getSlayerTask();
			if (killer.getTask() != null) {
				if (getDefinitions().name.toLowerCase().contains(killer.getTask().getName().toLowerCase())) {
					double extraxp = 0;
					int boxchance = 100000 / getMaxHitpoints();
					if (boxchance < 10) {
						boxchance = 10;
					}
					if (getId() == 31499) {
						extraxp = 55;
					}
					if (Utils.random(boxchance) == 1) {
						if (killer.getInventory().hasFreeSlots()) {
							killer.getInventory().addItem(29374, 1);
							killer.sendMessage(Colors.red + "You have obtained a slayer mystery box!");
						} else {
							killer.getBank().addItem(29374, 1, true);
							killer.sendMessage(Colors.red + "You have obtained a slayer mystery box! It has been sent to your bank!");
						}
					}
					
					killer.getSkills().addXp(Skills.SLAYER, killer.getTask().getXPAmount() + extraxp);
					killer.slayertargetkills++;
					if (killer.getEquipment().getAmmoId() == 29111 && Utils.random(10) == 0) {
						killer.getPackets().sendGameMessage("Your slaughter scroll prevents your slayer task from decreasing.", true);
					} else {
					killer.getTask().decreaseAmount();
					
					}
					if (killer.getTask().getTaskAmount() < 1) {
						killer.getSkills().addXp(Skills.SLAYER, killer.getSkills().getLevel(18) * 15);
						if (killer.getEquipment().getRingId() == 20054 || killer.getEquipment().getRingId() == 20052 || killer.getEquipment().getRingId() == 29267 || killer.getEquipment().getRingId() == 28908) {
							killer.setSlayerPoints(killer.getSlayerPoints() + 30);
							if (Settings.EVENT_SPOTLIGHT.equalsIgnoreCase("double slayer points")) {
								killer.sendMessage("<col=00ff00>Spotlight rewards you 30 slayer points, you now have " + killer.getSlayerPoints() + " slayer points.</col>");
								killer.setSlayerPoints(killer.getSlayerPoints() + 30);
							}
							if (killer.isSponsor()) {
								killer.setSlayerPoints(killer.getSlayerPoints() + 5);
							}
							for (int bosspet : Settings.PETS_WITH_PERKS) {
								if (killer.getPetManager().getNpcId() == bosspet && killer.getPetPerk().getContainer().contains(new Item(29398))) {
									killer.setSlayerPoints(killer.getSlayerPoints() + 5);
								}
								if (killer.getPetManager().getNpcId() == bosspet && killer.getPetPerk().getContainer().contains(new Item(29397))) {
									killer.setSlayerPoints(killer.getSlayerPoints() + 7);
								}
								if (killer.getPetManager().getNpcId() == bosspet && killer.getPetPerk().getContainer().contains(new Item(29396))) {
									killer.setSlayerPoints(killer.getSlayerPoints() + 10);
								}
							}
							killer.slaytask++;
							//killer.dailyslayertask++;
						//	SeasonEvent.HandleActivity(killer, "Slayer", 0);
							if (killer.challengeamount > 0 && killer.challengeid == 28 || killer.challengeid == 29 || killer.challengeid == 30) {
								DailyChallenges.UpdateChallenge(killer);
							}
							/*if (killer.dailyslayertask == 2) {
								killer.sendMessage(Colors.lightGray + "<img=6>You have completed the Daily Money Maker: Complete 2 slayer tasks! Your rewards have been placed in your bank.");
								killer.getBank().addItem(6199, 1, true);
								killer.getBank().addItem(5022, 5, true);
							}*/
							killer.getPackets().sendGameMessage(
									"You have finished your slayer task, talk to Kuradal for a new task.");
							killer.getPackets().sendGameMessage("Kuradal rewarded you 30 slayerPoints! You now have "
									+ killer.getSlayerPoints() + " slayerPoints.");
						} else {
							killer.setSlayerPoints(killer.getSlayerPoints() + 20);
							if (Settings.EVENT_SPOTLIGHT.equalsIgnoreCase("double slayer points")) {
								killer.sendMessage("<col=00ff00>Spotlight rewards you 20 slayer points, you now have " + killer.getSlayerPoints() + " slayer points.</col>");
								killer.setSlayerPoints(killer.getSlayerPoints() + 20);
							}
							if (killer.isSponsor()) {
								killer.setSlayerPoints(killer.getSlayerPoints() + 5);
							}
							for (int bosspet : Settings.PETS_WITH_PERKS) {
								if (killer.getPetManager().getNpcId() == bosspet && killer.getPetPerk().getContainer().contains(new Item(29398))) {
									killer.setSlayerPoints(killer.getSlayerPoints() + 5);
								}
								if (killer.getPetManager().getNpcId() == bosspet && killer.getPetPerk().getContainer().contains(new Item(29397))) {
									killer.setSlayerPoints(killer.getSlayerPoints() + 7);
								}
								if (killer.getPetManager().getNpcId() == bosspet && killer.getPetPerk().getContainer().contains(new Item(29396))) {
									killer.setSlayerPoints(killer.getSlayerPoints() + 10);
								}
							}
							killer.slaytask++;
							//killer.dailyslayertask++;
							//SeasonEvent.HandleActivity(killer, "Slayer", 0);
							if (killer.challengeamount > 0 && killer.challengeid == 28 || killer.challengeid == 29 || killer.challengeid == 30) {
								DailyChallenges.UpdateChallenge(killer);
							}
							/*if (killer.dailyslayertask == 2) {
								killer.sendMessage(Colors.lightGray + "<img=6>You have completed the Daily Money Maker: Complete 2 slayer tasks! Your rewards have been placed in your bank.");
								killer.getBank().addItem(6199, 1, true);
								killer.getBank().addItem(5022, 5, true);
							}*/
							killer.getPackets().sendGameMessage(
									"You have finished your slayer task, talk to Kuradal for a new task.");
							killer.getPackets().sendGameMessage("Kuradal rewarded you 20 slayerPoints! You now have "
									+ killer.getSlayerPoints() + " slayerPoints.");
						}
						killer.setTask(null);
						// return;
					}
					if (killer.getTask() != null) {
						killer.getTask().setAmountKilled(killer.getTask().getAmountKilled() + 1);
						killer.getPackets().sendGameMessage("You need to defeat " + killer.getTask().getTaskAmount()
								+ " " + killer.getTask().getName().toLowerCase() + " to complete your task.");
					}
				}
			}
			Drop[] possibleDrops = new Drop[drops.length];
			int possibleDropsCount = 0;
			for (Drop drop : drops) {
				Item item = ItemDefinitions.getItemDefinitions(drop.getItemId()).isStackable()
						? new Item(drop.getItemId(),
								drop.getMinAmount() * Settings.DROP_RATE
										+ Utils.getRandom(drop.getExtraAmount() * Settings.DROP_RATE))
						: new Item(drop.getItemId(), drop.getMinAmount() + Utils.getRandom(drop.getExtraAmount()));
				if (killer.getEquipment().getAmuletId() == 29172 && item.getDefinitions().getName().toLowerCase().contains("bones") && getDefinitions().getId() != 8596) {
					killer.getSkills().addXp(Skills.PRAYER, Burying.Bone.forId(drop.getItemId()).getExperience());
					killer.getPrayer().restorePrayer(item.getDefinitions().getName().equalsIgnoreCase("big bones") ? 2 : item.getDefinitions().getName().toLowerCase().contains("baby dragon bones") ? 3 : item.getDefinitions().getName().toLowerCase().contains("dragon bones") ? 4 : item.getDefinitions().getName().toLowerCase().contains("superior dragon bones") ? 5 : 1);
					continue;
				} else
				if (killer.getEquipment().getAmmoId() == 18337
						&& item.getDefinitions().getName().toLowerCase().contains("bones") && getDefinitions().getId() != 8596) {
					killer.getSkills().addXp(Skills.PRAYER, Burying.Bone.forId(drop.getItemId()).getExperience());
					continue;
				}
				if (killer.getInventory().containsItem(29113, 1) && killer.bonecrusherI
						&& item.getDefinitions().getName().toLowerCase().contains("bones") && !item.getDefinitions().isNoted() && getDefinitions().getId() != 8596) {
					int bonechange = 1;
					if (item.getDefinitions().getId() == 6812) {
						bonechange = 4;
					}
					if (item.getDefinitions().getId() == 29194) {
						if (Settings.DOUBLEDROPS) {
							killer.getInventory().addItemDrop(item.getDefinitions().getId() - bonechange,drop.getMinAmount() + Utils.getRandom(drop.getExtraAmount()));
						}
						killer.getInventory().addItemDrop(item.getDefinitions().getId() - bonechange,drop.getMinAmount() + Utils.getRandom(drop.getExtraAmount()));
					} else {
						if (Settings.DOUBLEDROPS) {
							killer.getInventory().addItemDrop(item.getDefinitions().getId() + bonechange,drop.getMinAmount() + Utils.getRandom(drop.getExtraAmount()));
						}
						killer.getInventory().addItemDrop(item.getDefinitions().getId() + bonechange,drop.getMinAmount() + Utils.getRandom(drop.getExtraAmount()));
					}
					continue;
				} else
				if (killer.getInventory().containsItem(29113, 1) && !killer.bonecrusherI
						&& item.getDefinitions().getName().toLowerCase().contains("bones") && !item.getDefinitions().isNoted() && getDefinitions().getId() != 8596) {
					killer.getSkills().addXp(Skills.PRAYER, Burying.Bone.forId(drop.getItemId()).getExperience());
					continue;
				} else
				if (killer.getInventory().containsItem(29172, 1) && item.getDefinitions().getName().toLowerCase().contains("bones") && !item.getDefinitions().isNoted() && getDefinitions().getId() != 8596) {
					killer.getSkills().addXp(Skills.PRAYER, Burying.Bone.forId(drop.getItemId()).getExperience());
					continue;
				} else
				if (killer.getInventory().containsItem(18337, 1)
						&& item.getDefinitions().getName().toLowerCase().contains("bones") && !item.getDefinitions().isNoted() && getDefinitions().getId() != 8596) {
					killer.getSkills().addXp(Skills.PRAYER, Burying.Bone.forId(drop.getItemId()).getExperience());
					continue;
				}
				if (killer.getInventory().containsItem(19675, 1)// Herbicide
						&& item.getDefinitions().getName().toLowerCase().contains("grimy") && !item.getDefinitions().isNoted()) {
					if (killer.getSkills().getLevelForXp(Skills.HERBLORE) >= Objects.requireNonNull(HerbCleaning.getHerb(item.getId())).getLevel()) {
						killer.getSkills().addXp(Skills.HERBLORE, Objects.requireNonNull(HerbCleaning.getHerb(drop.getItemId())).getExperience() * 2);
						continue;
					}
				}
				if (drop.getRate() == -1.0 || drop.getRate() == 100.0) {
					sendDrop(killer, drop);
					// sendDrop(killer, drop);
				} else {
					double dropRate = killer.getDropRateBonus(getName());
					int randomed =  Utils.random((int) drop.getRate() - (int) dropRate);
					if (randomed <= 1) {
						possibleDrops[possibleDropsCount++] = drop;
					}
					//System.out.println("Drop for item : "+drop.getItemId()+" - "+randomed +" - "+ (randomed <= 1 ? "dropped" : "not dropped"));
				}

			}
			if (possibleDropsCount > 0) {

				for(int i = 0; i < 1; i++) {
					Drop drop = possibleDrops[Utils.random(possibleDropsCount)];
					if (drop == null)
						continue;
					sendDrop(killer, drop);
				}
				//sendDrop(killer, possibleDrops[Utils.getRandom(possibleDropsCount - 1)]);
				if (getId() == 16387 || getId() == 16388 || getId() == 16389 || getId() == 39061 || getId() == 39615 || getId() == 39619 || getId() == 39620 || getId() == 39621) {
					sendDrop(killer, possibleDrops[Utils.getRandom(possibleDropsCount - 1)]);
				}
			}

		} catch (Exception | Error e) {
			e.printStackTrace();
		}
	}

	public void sendBox(Player player) {
		Random boxChance = new Random();
		int spin = boxChance.nextInt(99);
		if (spin < 98) {
			World.addGroundItem(new Item(29956),
					new WorldTile(getCoordFaceX(getSize()), getCoordFaceY(getSize()), getPlane()), player, false, 180,
					true);
			player.sendMessage("<col=8F4700>The gods of Harmony reward you for your efforts.");
		}
	}
	private void sendCharmDrop(Player player) {
		int charmChance = Utils.random(100);
		if (charmChance >= 0 && charmChance < 25) { //gold charm
			player.getBank().addItem(12158, 1, true);
			return;
		}
		if (charmChance >= 25 && charmChance < 50 ) { //green charm
			player.getBank().addItem(12159, 1, true);
			return;
		}
		if (charmChance >= 50 && charmChance < 75) { //crimson charm
			player.getBank().addItem(12160, 1, true);
			return;
		}
		if (charmChance >= 75 && charmChance <= 100) { //blue charm
			player.getBank().addItem(12163, 1, true);
			return;
		}
	}
	public void sendDrop(@NotNull final Player player, Drop drop) {
		if (player.isPvpMode()) {
			return;
		}
		if (player.getInventory().contains(3114) && drop.getRate() < 100) {
			player.getInventory().deleteItem(3114, 1);
		}
		int random = Utils.random(1, 100);
		final int size = getSize();
		String dropName = ItemDefinitions.getItemDefinitions(drop.getItemId()).getName().toLowerCase();
		if (dropName.contains("divine") || dropName.contains("elysian") || dropName.contains("arcane")
				|| dropName.contains("spectral") || dropName.contains("succubus") && !player.divine) {
			// player.getInterfaceManager().doAchievementInterface("Unlock sigil title");
			player.sendMessage("<col=33CC66>You have unlocked the title Sigil. ;;sigiltitle");
			player.divine = true;
		}
		
		Item item = ItemDefinitions.getItemDefinitions(drop.getItemId()).isStackable()
				? new Item(drop.getItemId(),
						drop.getMinAmount() * Settings.DROP_RATE
								+ Utils.getRandom(drop.getExtraAmount() * Settings.DROP_RATE))
				: new Item(drop.getItemId(), drop.getMinAmount() + Utils.getRandom(drop.getExtraAmount()));

		/* nieve dungeon start */
		if (World.inNieveDungeon(player) || player.dropsBankActive && !player.lootshareEnabled()) {
			player.getBank().addItem(item, true);
			sendCharmDrop(player);
			if (player.displaydrops == true) {
				player.sendMessage("<col=E633FF>"+getName()+" has dropped "+item.getAmount() * (!item.getName().toLowerCase().contains("clue scroll") && player.isHappyHour() || !item.getName().toLowerCase().contains("clue scroll") && Settings.DOUBLEDROPS == true ? 2 : 1)+" x "+item.getName());
			}
			if (Settings.DOUBLEDROPS == true || player.isHappyHour()) {
				if (ItemDefinitions.getItemDefinitions(item.getId()).getName().toLowerCase().contains("clue scroll")) {
					player.getPackets().sendGameMessage(Colors.red+"Clue scrolls do not double during double drops!", true);
				}
				if (player.getInventory().contains(29116) && RecycleCentreMain.CheckRunecoiner(player, drop.getItemId())) {
					RecycleCentreMain.ExchangeRunecoiner(player, drop.getItemId(),item.getAmount(), getX(), getY());
				} else {
					player.getBank().addItem(item, true); //extra drop for double drops/happy hour
				}
			}
			return;
		}
		/* nieve dungeon end */
		/* LootShare */
		FriendChatsManager fc = player.getCurrentFriendChat();
		if (player.lootshareEnabled()) {
			if (fc != null) {
				CopyOnWriteArrayList<Player> players = fc.getPlayers();
				CopyOnWriteArrayList<Player> playersWithLs = new CopyOnWriteArrayList<Player>();
				for (Player p : players) {
					if (p.lootshareEnabled() && p.getRegionId() == player.getRegionId()) {
						playersWithLs.add(p);
					}
					if (getId() == 30149 && !World.AssassinArea(p)) {
						playersWithLs.remove(p);
					}
					if (getId() == 10057 && !World.IcyBones(p)) {
						playersWithLs.remove(p);
					}
				}
				Player luckyPlayer = playersWithLs.get((int) (Math.random() * playersWithLs.size()));
				if (getId() == 16385 || getName().equalsIgnoreCase("zulrah") || getName().equalsIgnoreCase("vorkath")) {
					World.addGroundItem(item, new WorldTile(luckyPlayer.getX(), luckyPlayer.getY(), getPlane()), luckyPlayer, false, 180, true);
					
				} else {
				World.addGroundItem(item, new WorldTile(getCoordFaceX(size), getCoordFaceY(size), getPlane()),
						luckyPlayer, false, 180, true);
					}
				
				if (Settings.DOUBLEDROPS || Player.isHappyHour()) {
				//	System.out.println("1");
					if (ItemDefinitions.getItemDefinitions(item.getId()).getName().toLowerCase().contains("clue scroll")) {
						luckyPlayer.getPackets().sendGameMessage(Colors.red+"Clue scrolls do not double during double drops!", true);
					}
					else if (getId() == 16385 || getName().equalsIgnoreCase("zulrah") || getName().equalsIgnoreCase("vorkath")) {
						World.addGroundItem(item, new WorldTile(luckyPlayer.getX(), luckyPlayer.getY(), getPlane()),
								luckyPlayer, false, 180, true);
					} else {
					World.addGroundItem(item, new WorldTile(getCoordFaceX(size), getCoordFaceY(size), getPlane()),
							luckyPlayer, false, 180, true);
						
				}
				}
				DropLogRecorder.Track(luckyPlayer, drop);
				DropTracker.TrackNews(luckyPlayer, drop, item);
				Settings.goldenter += item.getDefinitions().getValue();
				if (item.getDefinitions().getValue() >= luckyPlayer.setLootBeam) {
					DropTracker.TrackLootBeam(luckyPlayer, getCoordFaceX(size), getCoordFaceY(size), getPlane(), luckyPlayer.LooTbeam);
				}
				if (Settings.DOUBLEDROPS || Player.isHappyHour()) {
					
					luckyPlayer.sendMessage(String.format("<col=115b0d>You received: %sx %s.</col> (" + getName() + ")",
						item.getAmount() * (item.getName().toLowerCase().contains("clue scroll") ? 1 : 2), dropName));
				} else {
					luckyPlayer.sendMessage(String.format("<col=115b0d>You received: %sx %s.</col> (" + getName() + ")",
							item.getAmount(), dropName));
				}
				if (luckyPlayer.ironman) {
					if (luckyPlayer.lootshareEnabled() && !World.IcyBones(luckyPlayer) && !World.AssassinArea(luckyPlayer)) {
						luckyPlayer.toggleLootShareIcy();
					}

				}
				for (Player p : playersWithLs) {
					if (!p.equals(luckyPlayer) && !Settings.DOUBLEDROPS) {
						p.sendMessage(String.format("%s received: %sx %s. (" + getName() + ")",
								luckyPlayer.getDisplayName(), item.getAmount(), dropName));
				} else 	if (!p.equals(luckyPlayer) && Settings.DOUBLEDROPS) {
						p.sendMessage(String.format("%s received: %sx %s. (" + getName() + ")",
							luckyPlayer.getDisplayName(), item.getAmount() *2, dropName));
				}
			}
				Random charmChance = new Random();
				int roll = charmChance.nextInt(99);
				if (roll < 18) {
					if (player.getInventory().contains(29915)|| player.getEquipment().getAmmoId() == 29915) {
						player.getInventory().addItem(12158, 1);
						// player.sendMessage("Your charm collector has found a
						// gold charm");
						return;
					} else if (getId() == 16385 || getName().equalsIgnoreCase("zulrah") || getName().equalsIgnoreCase("vorkath")) {
							World.addGroundItem(new Item(12158), new WorldTile(luckyPlayer.getX(), luckyPlayer.getY(), getPlane()), luckyPlayer, false, 180, true);
 
					} else {
						World.addGroundItem(new Item(12158),
								new WorldTile(getCoordFaceX(getSize()), getCoordFaceY(getSize()), getPlane()), player,
								false, 180, true);
					}
				} else if (roll < 28 && roll > 19) {
					if (player.getInventory().contains(29915)|| player.getEquipment().getAmmoId() == 29915) {
						player.getInventory().addItem(12159, 2);
					//	player.sendMessage("1");
						// player.sendMessage("Your charm collector has a some
						// green charm");
						return;
					} else if (getId() == 16385 || getName().equalsIgnoreCase("zulrah") || getName().equalsIgnoreCase("vorkath")) {
						World.addGroundItem(new Item(12159), new WorldTile(luckyPlayer.getX(), luckyPlayer.getY(), getPlane()), luckyPlayer, false, 180, true);
 
					} else {
						World.addGroundItem(new Item(12159, 2),
								new WorldTile(getCoordFaceX(getSize()), getCoordFaceY(getSize()), getPlane()), player,
								false, 180, true);
					}
				} else if (roll > 94) {
					if (player.getInventory().contains(29915)|| player.getEquipment().getAmmoId() == 29915) {
						player.getInventory().addItem(12163, 2);
					//	player.sendMessage("1");
						// player.sendMessage("Your charm collector has a some
						// blue charm");
						return;
					} else if (getId() == 16385 || getName().equalsIgnoreCase("zulrah") || getName().equalsIgnoreCase("vorkath")) {
						World.addGroundItem(new Item(12163), new WorldTile(luckyPlayer.getX(), luckyPlayer.getY(), getPlane()), luckyPlayer, false, 180, true);
 
					} else {
						World.addGroundItem(new Item(12163, 2),
								new WorldTile(getCoordFaceX(getSize()), getCoordFaceY(getSize()), getPlane()), player,
								false, 180, true);
					}
				} else if (roll < 90 && roll > 80) {
					if (player.getInventory().contains(29915)|| player.getEquipment().getAmmoId() == 29915) {
						player.getInventory().addItem(12160, 2);
					//	player.sendMessage("1");
						return;
						// player.sendMessage("Your charm collector has found a
						// crimson charm!");
					} else if (getId() == 16385 || getName().equalsIgnoreCase("zulrah") || getName().equalsIgnoreCase("vorkath")) {
						World.addGroundItem(new Item(12160), new WorldTile(luckyPlayer.getX(), luckyPlayer.getY(), getPlane()), luckyPlayer, false, 180, true);
 
					} else {
						World.addGroundItem(new Item(12160, 2),
								new WorldTile(getCoordFaceX(getSize()), getCoordFaceY(getSize()), getPlane()), player,
								false, 180, true);
					}
				}

				for (String itemName : Settings.RARE_DROPS) {
					if (dropName.toLowerCase().contains(itemName.toLowerCase())) {
						if (dropName.contains("brew") || dropName.contains("wine") || dropName.contains("shard")
								|| dropName.contains("rune"))
						 {
							return;
						// World.sendProjectile(drop, new
						// WorldTile(getCoordFaceX(getSize()),
						// getCoordFaceY(getSize()), getPlane()), player, 1960,
						// 0, 0, 0, 0, 0, 0);
						// player.sendMessage("<col=CCCC00>A tornado of stars
						// shine towards you.");
						// return;
						// World.sendWorldMessage("<img=7><col=ff8c38>News: " +
						// Utils.formatPlayerNameForDisplay(player.getUsername())
							// + " has received a "+ dropName + " drop</col>",
						// true);
						}

					}
				}
				return;
			}
		}
		/* End of LootShare */
		for (Player p : World.getPlayers()) {
			if (World.TheTrioRaid(p) || World.GulegaRaid(p)) {
				if (!World.TheTrioRaid(player)&& !World.GulegaRaid(player)) {
					continue;
				}
				World.addGroundItem(item, new WorldTile(getCoordFaceX(size), getCoordFaceY(size), getPlane()), p, false,
						180, true);
				if (player.displaydrops == true) {
					player.sendMessage("<col=E633FF>"+getName()+" has dropped "+item.getAmount()+" x "+item.getName());
				}
			}
		}
		if (!World.TheTrioRaid(player) && !World.GulegaRaid(player)) {
			if (getId() == 16385 || getName().equalsIgnoreCase("zulrah") || getName().equalsIgnoreCase("vorkath")) {
				if (player.getInventory().contains(29116) && RecycleCentreMain.CheckRunecoiner(player, drop.getItemId())) {
					RecycleCentreMain.ExchangeRunecoiner(player, drop.getItemId(), item.getAmount(), player.getX(), player.getY());
				} else {
				World.addGroundItem(item, new WorldTile(player.getX(), player.getY(), getPlane()),
						player, false, 180, true);
				}
			} else {
				if (player.getInventory().contains(29116) && RecycleCentreMain.CheckRunecoiner(player, drop.getItemId())) {
					RecycleCentreMain.ExchangeRunecoiner(player, drop.getItemId(),item.getAmount(), player.getX(), player.getY());
				} else {
			World.addGroundItem(item, new WorldTile(getCoordFaceX(size), getCoordFaceY(size), getPlane()), player,
					false, 180, true);
				}
			}
		}
		if (player.displaydrops == true) {
			player.sendMessage("<col=E633FF>"+getName()+" has dropped "+item.getAmount() * (!item.getName().toLowerCase().contains("clue scroll") && player.isHappyHour() || !item.getName().toLowerCase().contains("clue scroll") && Settings.DOUBLEDROPS == true ? 2 : 1)+" x "+item.getName());
		}
		if (Settings.DOUBLEDROPS == true || player.isHappyHour()) {
			if (ItemDefinitions.getItemDefinitions(item.getId()).getName().toLowerCase().contains("clue scroll")) {
				player.getPackets().sendGameMessage(Colors.red+"Clue scrolls do not double during double drops!", true);
			}
			else if (getId() == 16385 || getName().equalsIgnoreCase("zulrah") || getName().equalsIgnoreCase("vorkath")) {
				if (player.getInventory().contains(29116) && RecycleCentreMain.CheckRunecoiner(player, drop.getItemId())) {
					RecycleCentreMain.ExchangeRunecoiner(player, drop.getItemId(),item.getAmount(), player.getX(), player.getY());
				} else {
				World.addGroundItem(item, new WorldTile(player.getX(), player.getY(), getPlane()),
						player, false, 180, true);
				}
			} else {
				if (player.getInventory().contains(29116) && RecycleCentreMain.CheckRunecoiner(player, drop.getItemId())) {
					RecycleCentreMain.ExchangeRunecoiner(player, drop.getItemId(),item.getAmount(), getX(), getY());
				} else {
			World.addGroundItem(item, new WorldTile(getCoordFaceX(size), getCoordFaceY(size), getPlane()), player,
					false, 180, true); // extra
				}
			}
		}
		Settings.goldenter += item.getDefinitions().getValue();
		DropLogRecorder.Track(player, drop);
		DropTracker.TrackNews(player, drop, item);
		if (item.getDefinitions().getValue() >= player.setLootBeam) {
			DropTracker.TrackLootBeam(player, getCoordFaceX(size), getCoordFaceY(size), getPlane(), player.LooTbeam);
		}
		if (random >= 95 && random <= 100) {
			if (getId() == 116 || getId() == 4292 || getId() == 4291 || getId() == 6078
					|| getId() == 6079 || getId() == 6080 && drop.getItemId() == 8844) {
				WGuildControler.dropDefender(player, this);
				return;
			}
		}

		if (dropName.contains("brew") || dropName.contains("wine") || dropName.contains("shard")
				|| dropName.contains("rune")) {
			return;
		}
		if (dropName.contains("spin ticket")) {
			player.sendMessage("An npc has dropped a spin ticket for you.");
		}
		Random charmChance = new Random();
		int roll = charmChance.nextInt(99);
		if (World.Level3Zone(player) && roll < 50)
			roll = Utils.random(75, 99);
		//System.out.println(roll);
		if (roll < 18) {
			if (player.getInventory().contains(29915) || player.getEquipment().getAmmoId() == 29915) {
				player.getInventory().addItem(12158, 1);

				return;
			} else {
				World.addGroundItem(new Item(12158),
						new WorldTile(getCoordFaceX(getSize()), getCoordFaceY(getSize()), getPlane()), player, false,
						180, true);
			}
		} else if (roll < 28 && roll > 19) {
			if (player.getInventory().contains(29915) || player.getEquipment().getAmmoId() == 29915) {
				player.getInventory().addItem(12159, 1);

				return;
			} else {
				World.addGroundItem(new Item(12159),
						new WorldTile(getCoordFaceX(getSize()), getCoordFaceY(getSize()), getPlane()), player, false,
						180, true);
			}
		} else if (roll > 94) {
			if (player.getInventory().contains(29915) || player.getEquipment().getAmmoId() == 29915) {
				player.getInventory().addItem(12163, 1);

				return;
			} else {
				World.addGroundItem(new Item(12163),
						new WorldTile(getCoordFaceX(getSize()), getCoordFaceY(getSize()), getPlane()), player, false,
						180, true);
			}
		} else if (roll < 90 && roll > 80) {
			if (player.getInventory().contains(29915) || player.getEquipment().getAmmoId() == 29915) {
				player.getInventory().addItem(12160, 1);
				return;
			} else {
				World.addGroundItem(new Item(12160),
						new WorldTile(getCoordFaceX(getSize()), getCoordFaceY(getSize()), getPlane()), player, false,
						180, true);
			}

		}
	}

	@Override
	public int getSize() {
		return getDefinitions().size;
	}

	public int getMaxHit() {
		return getCombatDefinitions().getMaxHit();
	}

	public int[] getBonuses() {
		return bonuses;
	}

	@Override
	public double getMagePrayerMultiplier() {
		return 0;
	}

	@Override
	public double getRangePrayerMultiplier() {
		return 0;
	}

	@Override
	public double getMeleePrayerMultiplier() {
		return 0;
	}

	public WorldTile getRespawnTile() {
		return respawnTile;
	}

	public boolean isUnderCombat() {
		return combat.underCombat();
	}

	public void setNextNPCTransformation(int id) {
		setNPC(id);
		nextTransformation = new Transformation(id);
		if (getCustomCombatLevel() != -1) {
			changedCombatLevel = true;
		}
		if (getCustomName() != null) {
			changedName = true;
		}
	}

	@Override
	public void setAttackedBy(Entity target) {
		super.setAttackedBy(target);
		if (target == combat.getTarget() && !(combat.getTarget() instanceof Familiar)) {
			lastAttackedByTarget = Utils.currentTimeMillis();
		}
	}

	public boolean canBeAttackedByAutoRelatie() {
		return Utils.currentTimeMillis() - lastAttackedByTarget > lureDelay;
	}

	public boolean isForceWalking() {
		return forceWalk != null;
	}

	public void setTarget(Entity entity) {
		if (isForceWalking()) {
			return;
		}
		combat.setTarget(entity);
		lastAttackedByTarget = Utils.currentTimeMillis();
	}

	public void removeTarget() {
		if (combat.getTarget() == null) {
			return;
		}
		combat.removeTarget();
	}

	public void forceWalkRespawnTile() {
		setForceWalk(respawnTile);
	}

	public void setForceWalk(WorldTile tile) {
		resetWalkSteps();
		forceWalk = tile;
	}

	public boolean hasForceWalk() {
		return forceWalk != null;
	}

	public ArrayList<Entity> getPossibleTargets() {
		ArrayList<Entity> possibleTarget = new ArrayList<Entity>();
		for (int regionId : getMapRegionsIds()) {
			List<Integer> playerIndexes = World.getRegion(regionId).getPlayerIndexes();
			if (playerIndexes != null) {
				for (int playerIndex : playerIndexes) {
					Player player = World.getPlayers().get(playerIndex);
					if (player == null || player.isDead() || player.hasFinished() || !player.isRunning()
							|| !player.withinDistance(this,
									forceTargetDistance > 0 ? forceTargetDistance
											: getCombatDefinitions().getAttackStyle() == NPCCombatDefinitions.MELEE ? 4
													: getCombatDefinitions()
															.getAttackStyle() == NPCCombatDefinitions.SPECIAL ? 64 : 8)
							|| !forceMultiAttacked && (!isAtMultiArea() || !player.isAtMultiArea())
									&& player.getAttackedBy() != this
									&& (player.getAttackedByDelay() > Utils.currentTimeMillis()
											|| player.getFindTargetDelay() > Utils.currentTimeMillis())
							|| !clipedProjectile(player, false) || !forceAgressive && !Wilderness.isAtWild(this)
									&& player.getSkills().getCombatLevelWithSummoning() >= getCombatLevel() * 2) {
						continue;
					}
					possibleTarget.add(player);
				}
			}
		}
		return possibleTarget;
	}

	public boolean checkAgressivity() {
		// if(!(Wilderness.isAtWild(this) &&
		// getDefinitions().hasAttackOption())) {
		// System.out.println(getId());
		if (!forceAgressive) {
			NPCCombatDefinitions defs = getCombatDefinitions();
			if (defs.getAgressivenessType() == NPCCombatDefinitions.PASSIVE) {
				return false;
			}
		}
		// System.out.println(getId());
		//if (getId() == 30219)
		// }
		ArrayList<Entity> possibleTarget = getPossibleTargets();
		if (!possibleTarget.isEmpty()) {
			Entity target = possibleTarget.get(Utils.random(possibleTarget.size()));
			if (target.provoke = true) {
				setTarget(target);
				target.setAttackedBy(target);
			}
			setTarget(target);
			target.setAttackedBy(target);
			target.setFindTargetDelay(Utils.currentTimeMillis() + 10000);
			return true;
		}
		return false;
	}

	public boolean isCantInteract() {
		return cantInteract;
	}

	public void setCantInteract(boolean cantInteract) {
		this.cantInteract = cantInteract;
		if (cantInteract) {
			combat.reset();
		}
	}

	public int getCapDamage() {
		return capDamage;
	}

	public void setCapDamage(int capDamage) {
		this.capDamage = capDamage;
	}

	public int getLureDelay() {
		return lureDelay;
	}

	public void setLureDelay(int lureDelay) {
		this.lureDelay = lureDelay;
	}

	public boolean isCantFollowUnderCombat() {
		return cantFollowUnderCombat;
	}

	public void setCantFollowUnderCombat(boolean canFollowUnderCombat) {
		cantFollowUnderCombat = canFollowUnderCombat;
	}

	public Transformation getNextTransformation() {
		return nextTransformation;
	}

	@Override
	public String toString() {
		return getDefinitions().name + " - " + id + " - " + getX() + " " + getY() + " " + getPlane();
	}

	public boolean isForceAgressive() {
		return forceAgressive;
	}

	public void setForceAgressive(boolean forceAgressive) {
		this.forceAgressive = forceAgressive;
	}

	public int getForceTargetDistance() {
		return forceTargetDistance;
	}

	public void setForceTargetDistance(int forceTargetDistance) {
		this.forceTargetDistance = forceTargetDistance;
	}

	public boolean isForceFollowClose() {
		return forceFollowClose;
	}

	public void setForceFollowClose(boolean forceFollowClose) {
		this.forceFollowClose = forceFollowClose;
	}

	public boolean isForceMultiAttacked() {
		return forceMultiAttacked;
	}

	public void setForceMultiAttacked(boolean forceMultiAttacked) {
		this.forceMultiAttacked = forceMultiAttacked;
	}

	public boolean hasRandomWalk() {
		return randomwalk;
	}

	public void setRandomWalk(boolean forceRandomWalk) {
		randomwalk = forceRandomWalk;
	}

	public String getCustomName() {
		return name;
	}

	public void setName(String string) {
		name = getDefinitions().name.equals(string) ? null : string;
		changedName = true;
	}

	public int getCustomCombatLevel() {
		return combatLevel;
	}

	public int getCombatLevel() {
		return combatLevel >= 0 ? combatLevel : getDefinitions().combatLevel;
	}

	public void tripleBonuses() {
		for (int i = 0; i < bonuses.length; i++) {
			bonuses[i] = bonuses[i] * 3;
		}
	}

	public void resetBonuses() {
		bonuses = NPCBonuses.getBonuses(getId());
	}

	public void setBonuses() {
		bonuses = NPCBonuses.getBonuses(id);
		if (bonuses == null) {
			bonuses = new int[10];
			int level = getCombatLevel();
			for (int i = 0; i < bonuses.length; i++) {
				bonuses[i] = level;
			}
		}
	}

	public void setBonuses(int[] bonuses) {
		this.bonuses = bonuses;
	}

	public void setBonus(int index, int level) {
		bonuses[index] = level;
	}

	public void setAttackBonuses(int level) {
		bonuses[0] = level;
	}

	public void setRangedBonuses(int level) {
		bonuses[4] = level;
	}

	public void setDefenceBonuses(int level) {
		bonuses[5] = level;
		bonuses[6] = level;
		bonuses[7] = level;
	}

	public int getBonus(int index) {
		return bonuses[index];
	}

	public String getName() {
		return name != null ? name : getDefinitions().name;
	}

	public void setCombatLevel(int level) {
		combatLevel = getDefinitions().combatLevel == level ? -1 : level;
		changedCombatLevel = true;
	}

	public boolean hasChangedName() {
		return changedName;
	}

	public boolean hasChangedCombatLevel() {
		return changedCombatLevel;
	}

	public WorldTile getMiddleWorldTile() {
		int size = getSize();
		return new WorldTile(getCoordFaceX(size), getCoordFaceY(size), getPlane());
	}

	public boolean isSpawned() {
		return spawned;
	}

	public long isSpawnedBlinks() {
		return spawnedblinks;
	}

	public void setSpawned(boolean spawned) {
		this.spawned = spawned;
	}

	public boolean isNoDistanceCheck() {
		return noDistanceCheck;
	}

	public void setNoDistanceCheck(boolean noDistanceCheck) {
		this.noDistanceCheck = noDistanceCheck;
	}

	public boolean withinDistance(Player tile, int distance) {
		return super.withinDistance(tile, distance);
	}

	/**
	 * Gets the locked.
	 * 
	 * @return The locked.
	 */
	public boolean isLocked() {
		return locked;
	}

	/**
	 * Sets the locked.
	 * 
	 * @param locked
	 *            The locked to set.
	 */
	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public int getMaxHit(int style) {
		int maxHit = bonuses[0];
		if (style == 1) {
			maxHit = bonuses[1];
		} else if (style == 2) {
			maxHit = bonuses[2];
		}
		return maxHit / 10;
	}

	private transient boolean cantSetTargetAutoRelatio;

	public boolean isCantSetTargetAutoRelatio() {
		return cantSetTargetAutoRelatio;
	}

	public boolean isIntelligentRouteFinder() {
		return intelligentRouteFinder;
	}

	public void setIntelligentRouteFinder(boolean intelligentRouteFinder) {
		this.intelligentRouteFinder = intelligentRouteFinder;
	}

	public void setCantSetTargetAutoRelatio(boolean cantSetTargetAutoRelatio) {
		this.cantSetTargetAutoRelatio = cantSetTargetAutoRelatio;
	}

	public boolean isStunImmune() {
		return true;
	}

	public boolean isBoundImmune() {
		return true;
	}

	public boolean isPoisonImmune() {
		return true;
	}

	public WorldTile findClosestFreeTile() {
		List<WorldTile> possibleTiles = new ArrayList<WorldTile>();
		for (int x = -1; x < 1; x++) {
			for (int y = -1; y < 1; y++) {
				if (World.isFloorFree(getPlane(), getX() + x, getY() + y, getSize())) {
					possibleTiles.add(new WorldTile(getX() + x, getY() + y, getPlane()));
				}
			}
		}
		possibleTiles.add(new WorldTile(getX(), getY(), getPlane()));
		return possibleTiles.get(Utils.random(possibleTiles.size()));
	}

	public int drainLevel(int levelId, int amount) {
		if (bonuses == null) {
			return 0;
		}
		int drainLeft = amount - bonuses[levelId];
		if (drainLeft < 0) {
			drainLeft = 0;
		}
		bonuses[levelId] -= amount;
		if (bonuses[levelId] < 0) {
			bonuses[levelId] = 0;
		}
		return drainLeft;
	}

	/**
	 * Exclusively used for the Impetuous Impulses minigame.
	 */
	public void setRespawnTaskImpling() {
		if (!hasFinished()) {
			reset();
			setLocation(respawnTile);
			finish();
			if (Settings.DEBUG) {
				System.out.println("Finishing NPC: [" + toString() + "].");
			}
		}
		if (!(getX() >= 2316 && getX() <= 2354 && getY() >= 4264 && getY() <= 4285 && getPlane() == 0)) {
			id = FlyingEntities.values()[Utils.random(FlyingEntities.values().length)].getNpcId();
			setLocation(new WorldTile(Utils.random(2558 + 3, 2626 - 3), Utils.random(4285 + 3, 4354 - 3), 0));
		}
		long respawnDelay = getCombatDefinitions().getRespawnDelay() * 600;
		if (Settings.DEBUG) {
			System.out.println("Respawn task initiated: [" + toString() + "]; time: [" + respawnDelay + "].");
		}
		CoresManager.slowExecutor.schedule(new Runnable() {
			@Override
			public void run() {
				try {
					spawn();
				} catch (Throwable e) {
					Logger.handle(e);
				}
			}
		}, respawnDelay, TimeUnit.MILLISECONDS);
	}

}
