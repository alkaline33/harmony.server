package com.rs.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import com.rs.Launcher;
import com.rs.Settings;
import com.rs.cache.loaders.ItemDefinitions;
import com.rs.cores.CoresManager;
//import com.rs.executor.GameExecutorManager;
import com.rs.game.Hit.HitLook;
import com.rs.game.item.FloorItem;
import com.rs.game.item.Item;
import com.rs.game.minigames.GodWarsBosses;
import com.rs.game.minigames.PuroPuro;
import com.rs.game.minigames.ZarosGodwars;
import com.rs.game.minigames.clanwars.FfaZone;
import com.rs.game.minigames.clanwars.RequestController;
import com.rs.game.minigames.duel.DuelControler;
import com.rs.game.npc.Drop;
import com.rs.game.npc.NPC;
import com.rs.game.npc.corp.CorporealBeast;
import com.rs.game.npc.dragons.KingBlackDragon;
import com.rs.game.npc.drygonvssuns.Drygon;
import com.rs.game.npc.drygonvssuns.Suns;
import com.rs.game.npc.godwars.GodWarMinion;
import com.rs.game.npc.godwars.armadyl.GodwarsArmadylFaction;
import com.rs.game.npc.godwars.armadyl.KreeArra;
import com.rs.game.npc.godwars.bandos.GeneralGraardor;
import com.rs.game.npc.godwars.bandos.GodwarsBandosFaction;
import com.rs.game.npc.godwars.saradomin.CommanderZilyana;
import com.rs.game.npc.godwars.saradomin.GodwarsSaradominFaction;
import com.rs.game.npc.godwars.zammorak.GodwarsZammorakFaction;
import com.rs.game.npc.godwars.zammorak.KrilTstsaroth;
import com.rs.game.npc.godwars.zaros.Nex;
import com.rs.game.npc.godwars.zaros.NexMinion;
import com.rs.game.npc.kalph.KalphiteQueen;
import com.rs.game.npc.nomad.FlameVortex;
import com.rs.game.npc.nomad.Nomad;
import com.rs.game.npc.others.AOD;
import com.rs.game.npc.others.AngryEasterbunny;
import com.rs.game.npc.others.Anivia;
import com.rs.game.npc.others.BalanceElemental;
import com.rs.game.npc.others.Blink;
import com.rs.game.npc.others.Bork;
import com.rs.game.npc.others.Celestia;
import com.rs.game.npc.others.Cerberus;
import com.rs.game.npc.others.DrygonicGod;
import com.rs.game.npc.others.DungBossHigh;
import com.rs.game.npc.others.DungBossLow;
import com.rs.game.npc.others.DungBossMed;
import com.rs.game.npc.others.Gulega;
import com.rs.game.npc.others.IcyBones;
import com.rs.game.npc.others.Ingenuity;
import com.rs.game.npc.others.ItemHunterNPC;
import com.rs.game.npc.others.KalphiteKing;
import com.rs.game.npc.others.Kraken;
import com.rs.game.npc.others.Lauren;
import com.rs.game.npc.others.LivingRock;
import com.rs.game.npc.others.Lizardman;
import com.rs.game.npc.others.Lucien;
import com.rs.game.npc.others.MercenaryMage;
import com.rs.game.npc.others.NecroLord;
import com.rs.game.npc.others.PartyDemon;
import com.rs.game.npc.others.Pker;
import com.rs.game.npc.others.ROT6;
import com.rs.game.npc.others.Revenant;
import com.rs.game.npc.others.SirenicSpider;
import com.rs.game.npc.others.Skotizo;
import com.rs.game.npc.others.SkotizoMinion;
import com.rs.game.npc.others.SummonedSoul;
import com.rs.game.npc.others.Sunfreet;
import com.rs.game.npc.others.TheAssassin;
import com.rs.game.npc.others.TheRaptor;
import com.rs.game.npc.others.TormentedDemon;
import com.rs.game.npc.others.TrioBossC;
import com.rs.game.npc.others.VoragoRS2;
import com.rs.game.npc.others.karuulm.Drake;
import com.rs.game.npc.others.karuulm.HydraBoss;
import com.rs.game.npc.others.karuulm.RegularHydra;
import com.rs.game.npc.others.osrswildy.Callisto;
import com.rs.game.npc.others.osrswildy.ChaosFanatic;
import com.rs.game.npc.others.osrswildy.CrazyArchaeologist;
import com.rs.game.npc.others.osrswildy.Scorpia;
import com.rs.game.npc.others.osrswildy.ScorpiaGuardian;
import com.rs.game.npc.others.osrswildy.Venenatis;
import com.rs.game.npc.others.osrswildy.Vetion;
import com.rs.game.npc.others.prestigedungeon.CorruptedBrothers;
import com.rs.game.npc.others.sire.Sire;
import com.rs.game.npc.others.sire.Spawn;
import com.rs.game.npc.others.vorkath.Vorkath;
import com.rs.game.npc.others.vorkath.VorkathSpawns;
import com.rs.game.npc.slayer.Strykewyrm;
import com.rs.game.npc.slayer.ThurmoSmokeDevil;
import com.rs.game.npc.sorgar.Elemental;
import com.rs.game.npc.theatreofblood.BloodBeast;
import com.rs.game.npc.theatreofblood.Justiciar;
import com.rs.game.npc.theatreofblood.Ramokee;
import com.rs.game.npc.theatreofblood.TrollRanger;
import com.rs.game.npc.vorago.VoragoEnraged;
import com.rs.game.npc.xeric.IceFiend;
import com.rs.game.npc.xeric.PitRock;
import com.rs.game.npc.xeric.Tekton;
import com.rs.game.npc.xeric.Vampyre;
import com.rs.game.npc.zulrah.Snakeling;
import com.rs.game.npc.zulrah.Zulrah;
import com.rs.game.player.OwnedObjectManager;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.BoxAction.HunterNPC;
import com.rs.game.player.bot.Bot;
import com.rs.game.player.content.ItemConstants;
import com.rs.game.player.content.LivingRockCavern;
import com.rs.game.player.content.Lottery;
import com.rs.game.player.content.ShootingStar;
import com.rs.game.player.content.Tips;
import com.rs.game.player.content.TriviaBot;
import com.rs.game.player.controlers.Wilderness;
import com.rs.game.route.Flags;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.AntiFlood;
import com.rs.utils.CalamityBestWave;
import com.rs.utils.Colors;
import com.rs.utils.HeistBags;
import com.rs.utils.HeistGames;
import com.rs.utils.IPBanL;
import com.rs.utils.Logger;
import com.rs.utils.NPCSpawning;
import com.rs.utils.PkRank;
import com.rs.utils.PrestigeHS;
import com.rs.utils.SerializableFilesManager;
import com.rs.utils.ShopsHandler;
import com.rs.utils.TopTriviaAnswers;
import com.rs.utils.TopVoters;
import com.rs.utils.TotalBossKills;
import com.rs.utils.Utils;
import com.rs.utils.Utils.EntityDirection;

public final class World {

	public static int exiting_delay;
	public static long exiting_start;

	public static Player findPlayer(String name) {
		if (name == null) {
			return null;
		}
		Player plr = getPlayerByDisplayName(name);
		if (plr == null) {
			plr = loadPlayerFromFile(name);
		}
		return plr;
	}

	public static Player loadPlayerFromFile(String name) {
		name = Utils.formatPlayerNameForProtocol(name);
		Player player = SerializableFilesManager.loadPlayer(name);
		if (player != null) { // really now ...
			player.setUsername(Utils.formatPlayerNameForProtocol(name));
		}
		return player;
	}

	private static final EntityList<Player> players = new EntityList<Player>(Settings.PLAYERS_LIMIT);

	private static final EntityList<NPC> npcs = new EntityList<NPC>(Settings.NPCS_LIMIT);
	private static final Map<Integer, Region> regions = Collections.synchronizedMap(new HashMap<Integer, Region>());

	// private static final Object lock = new Object();

	public static int star = 0;

	public static final void init() {
		//Lottery.INSTANCE.establish();
		// addLogicPacketsTask();
		addTriviaBotTask();
		addQuickTask();
		addTipsTask();
		spawnStar();
		addRestoreRunEnergyTask();
		addDrainPrayerTask();
		addRestoreHitPointsTask();
		addRestoreSkillsTask();
		// WildywyrmAnnouncer.INSTANCE.init();
		addRestoreSpecialAttackTask();
		addRestoreShopItemsTask();
		addSummoningEffectTask();
		addOwnedObjectsTask();
		LivingRockCavern.init();
		NPCSpawning.spawnNPCS();
		NPCSpawning.spawnObjects();
		PuroPuro.initPuroImplings();

	}

	/*
	 * private static void addLogicPacketsTask() {
	 * CoresManager.fastExecutor.scheduleAtFixedRate(new TimerTask() {
	 * 
	 * @Override public void run() { for(Player player : World.getPlayers()) {
	 * if(!player.hasStarted() || player.hasFinished()) continue;
	 * player.processLogicPackets(); } }
	 * 
	 * }, 300, 300); }
	 */
	private static final void addTipsTask() {
		CoresManager.slowExecutor.scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {
				try {
					Tips.run();
					if (Settings.eventdoublehits > 0) {
						Settings.eventdoublehits--;
					}
					if (Settings.eventdoublecaskets > 0) {
						Settings.eventdoublecaskets--;
					}
					if (Settings.eventspotlightdouble > 0) {
						Settings.eventspotlightdouble--;
					}
					if (Settings.eventbosspetchance > 0) {
						Settings.eventbosspetchance --;
					}
					if (Settings.eventdoubleskillingresources > 0) {
						Settings.eventdoubleskillingresources--;
					}
					if (Settings.eventdropeventtokens > 0) {
						Settings.eventdropeventtokens--;
					}
					for (Player p : World.getPlayers()) {
						if (p == null) {
							continue;
						}
						if (p instanceof Bot) {
							continue;
						}
						if (!p.getSession().getChannel().isReadable()) {
							System.out.println(p.getDisplayName()+" was kicked after being disconnected!");
							p.forceLogout();
						}
					}
					if (Settings.TENPDROPSTIME > 0 && Settings.TENPDROPS == true) {
						Settings.TENPDROPSTIME -= 1;
						if (Settings.TENPDROPSTIME == 0) {
							Settings.TENPDROPS = false;
							Settings.amountdonated = 0;
							World.sendWorldMessage(Colors.orange + "[News] Drop rate has resumed to normal.", false);
						}
					}
					if (Settings.amountdonated >= 25 && Settings.TENPDROPS != true) {
						Settings.TENPDROPS = true;
						Settings.TENPDROPSTIME = 15;
						World.sendWorldMessage(Colors.orange + "[News] Drop rate has been increased by 10% for 30 minutes.", false);

					}
				} catch (Throwable e) {
					Logger.handle(e);
				}
			}
		}, 0, 120, TimeUnit.SECONDS);
	}

	public static boolean isPdemonSpawn() {

		return true;
	}

	/**
	 * LOBBY
	 */
	private static final EntityList<Player> lobbyPlayers = new EntityList<Player>(Settings.PLAYERS_LIMIT);

	public static final Player getLobbyPlayerByDisplayName(String username) {
		String formatedUsername = Utils.formatPlayerNameForDisplay(username);
		for (Player player : getLobbyPlayers()) {
			if (player == null) {
				continue;
			}
			if (player.getUsername().equalsIgnoreCase(formatedUsername)
					|| player.getDisplayName().equalsIgnoreCase(formatedUsername)) {
				return player;
			}
		}
		return null;
	}

	public static final EntityList<Player> getLobbyPlayers() {
		return lobbyPlayers;
	}

	public static final void addPlayer(Player player) {
		players.add(player);
		if (World.containsLobbyPlayer(player.getUsername())) {
			World.removeLobbyPlayer(player);
			AntiFlood.remove(player.getSession().getIP());
		}
		if (!(player instanceof Bot)) {
			AntiFlood.add(player.getSession().getIP());
		}
	}

	public static final void addLobbyPlayer(Player player) {
		lobbyPlayers.add(player);
		AntiFlood.add(player.getSession().getIP());
	}

	public static final boolean containsLobbyPlayer(String username) {
		for (Player p2 : lobbyPlayers) {
			if (p2 == null) {
				continue;
			}
			if (p2.getUsername().equalsIgnoreCase(username)) {
				return true;
			}
		}
		return false;
	}

	public static void removeLobbyPlayer(Player player) {
		for (Player p : lobbyPlayers) {
			if (p.getUsername().equalsIgnoreCase(player.getUsername())) {
				if (player.getCurrentFriendChat() != null) {
					player.getCurrentFriendChat().leaveChat(player, true);
				}
				lobbyPlayers.remove(p);
			}
		}
		AntiFlood.remove(player.getSession().getIP());
	}

	public static void removePlayer(Player player) {
		for (Player p : players) {
			if (p.getUsername().equalsIgnoreCase(player.getUsername())) {
				players.remove(p);
			}
		}
		if (!(player instanceof Bot)) {
			AntiFlood.remove(player.getSession().getIP());
		}
	}

	private static final void addQuickTask() {
		CoresManager.slowExecutor.scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {
				try {
					for (Player player : getPlayers()) {
						if (player == null || player.isDead() || !player.isRunning()) {
							continue;
						}
						if (World.IngenuityBoss(player)) {
							if (Settings.canclickspawnboss == true) {
								player.applyHit(new Hit(player, player.getMaxHitpoints() / 5,
										HitLook.REGULAR_DAMAGE, 15));
							}
							player.getInterfaceManager().doIngenuityInterface();
						}
						if (!World.TheCalamity(player)) {
							// String mps = MaxPlayersOnline;

							player.getPackets().sendIComponentText(751, 16, "Loot box<br> in: "+player.dailylootboxtimer+"");
						} else {
							player.getPackets().sendIComponentText(751, 16, "Points: " + player.calamitykillpoints);
						}
						player.getPackets().sendIComponentText(506, 2, "Teleports");
					}
				} catch (Throwable e) {
					Logger.handle(e);
				}
			}
		}, 0, 3, TimeUnit.SECONDS);
	}

	private static void addOwnedObjectsTask() {
		CoresManager.slowExecutor.scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {
				try {
					OwnedObjectManager.processAll();
				} catch (Throwable e) {
					Logger.handle(e);
				}
			}
		}, 0, 1, TimeUnit.SECONDS);
	}

	public static final NPC spawnNPC(int id, WorldTile tile, int mapAreaNameHash, boolean canBeAttackFromOutOfArea,
			EntityDirection faceDirection) {
		NPC returnValue = spawnNPC(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, false);
		returnValue.setDirection(faceDirection.getValue());
		return returnValue;
	}

	private static void addRestoreShopItemsTask() {
		CoresManager.slowExecutor.scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {
				try {
					ShopsHandler.restoreShops();
				} catch (Throwable e) {
					Logger.handle(e);
				}
			}
		}, 0, 30, TimeUnit.SECONDS);
	}

	private static final void addSummoningEffectTask() {
		CoresManager.slowExecutor.scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {
				try {
					for (Player player : getPlayers()) {
						if (player == null || player.getFamiliar() == null || player.isDead() || !player.hasFinished()) {
							continue;
						}
						if (player.getFamiliar().getOriginalId() == 6814) {
							player.heal(20);
							player.setNextGraphics(new Graphics(1507));
						}
					}
				} catch (Throwable e) {
					Logger.handle(e);
				}
			}
		}, 0, 15, TimeUnit.SECONDS);
	}

	public static final void runWellTimer() {

		CoresManager.fastExecutor.schedule(new TimerTask() {

			int timer = 60;

			@Override
			public void run() {
				if (timer == 1) {
					if (Settings.WELLTIMER == 0) {
					} else {
						Settings.WELLTIMER -= 1;
						timer = 60;
					}
				}
				if (Settings.WELLTIMER == 0 && timer == 2) {
					World.sendWorldMessage(
							"<col=00ff00> You can now donate to the well of fortune for double xp again!", false);
				}
				if (Settings.WELLTIMER == 60 && Settings.WELLDOUBLE == true) {
					Settings.WELLDOUBLE = false;
					World.sendWorldMessage("Thank you for donating to the well. The double experience has now ended.",
							false);
				}

				if (timer > 0) {
					//System.out.println("4");
					timer--;
				}
			}
		}, 0L, 1000L);
	}

	private static final void addRestoreSpecialAttackTask() {

		CoresManager.fastExecutor.schedule(new TimerTask() {
			@Override
			public void run() {
				try {
					for (Player player : getPlayers()) {
						if (player == null || player.isDead() || !player.isRunning()) {
							continue;
						}
						player.getCombatDefinitions().restoreSpecialAttack();
					}
				} catch (Throwable e) {
					Logger.handle(e);
				}
			}
		}, 0, 30000);
	}

	private static boolean checkAgility;

	private static final void addTriviaBotTask() {
		CoresManager.slowExecutor.scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {
				try {
					TriviaBot.Run();
				} catch (Throwable e) {
					Logger.handle(e);
				}
			}
		}, 5, 10, TimeUnit.MINUTES);
}

	private static final void addRestoreRunEnergyTask() {
		CoresManager.fastExecutor.schedule(new TimerTask() {
			@Override
			public void run() {
				try {
					for (Player player : getPlayers()) {
						if (player == null || player.isDead() || !player.isRunning()
								|| checkAgility && player.getSkills().getLevel(Skills.AGILITY) < 70) {
							continue;
						}
						player.restoreRunEnergy();
					}
					checkAgility = !checkAgility;
				} catch (Throwable e) {
					Logger.handle(e);
				}
			}
		}, 0, 1000);
	}

	private static final void addDrainPrayerTask() {
		CoresManager.fastExecutor.schedule(new TimerTask() {
			@Override
			public void run() {
				try {
					for (Player player : getPlayers()) {
						if (player == null || player.isDead() || !player.isRunning()) {
							continue;
						}
						player.getPrayer().processPrayerDrain();
					}
				} catch (Throwable e) {
					Logger.handle(e);
				}
			}
		}, 0, 600);
	}

	private static final void addRestoreHitPointsTask() {
		CoresManager.fastExecutor.schedule(new TimerTask() {
			@Override
			public void run() {
				try {
					for (Player player : getPlayers()) {
						if (player == null || player.isDead() || !player.isRunning()) {
							continue;
						}
						player.restoreHitPoints();
					}
					for (NPC npc : npcs) {
						if (npc == null || npc.isDead() || npc.hasFinished()) {
							continue;
						}
						npc.restoreHitPoints();
					}
				} catch (Throwable e) {
					Logger.handle(e);
				}
			}
		}, 0, 6000);
	}

	private static final void addRestoreSkillsTask() {
		CoresManager.fastExecutor.schedule(new TimerTask() {
			@Override
			public void run() {
				try {
					for (Player player : getPlayers()) {
						if (player == null || !player.isRunning()) {
							continue;
						}
						int ammountTimes = player.getPrayer().usingPrayer(0, 8) ? 2 : 1;
						if (player.isResting()) {
							ammountTimes += 1;
						}
						boolean berserker = player.getPrayer().usingPrayer(1, 5);
						for (int skill = 0; skill < 25; skill++) {
							if (skill == Skills.SUMMONING) {
								continue;
							}
							for (int time = 0; time < ammountTimes; time++) {
								int currentLevel = player.getSkills().getLevel(skill);
								int normalLevel = player.getSkills().getLevelForXp(skill);
								if (currentLevel > normalLevel) {
									if (skill == Skills.ATTACK || skill == Skills.STRENGTH || skill == Skills.DEFENCE
											|| skill == Skills.RANGE || skill == Skills.MAGIC) {
										if (berserker && Utils.getRandom(100) <= 15) {
											continue;
										}
									}
									player.getSkills().set(skill, currentLevel - 1);
								} else if (currentLevel < normalLevel) {
									player.getSkills().set(skill, currentLevel + 1);
								} else {
									break;
								}
							}
						}
					}
				} catch (Throwable e) {
					Logger.handle(e);
				}
			}
		}, 0, 30000);

	}

	public static final Map<Integer, Region> getRegions() {
		// synchronized (lock) {
		return regions;
		// }
	}

	public static final Region getRegion(int id) {
		return getRegion(id, false);
	}

	public static final Region getRegion(int id, boolean load) {
		// synchronized (lock) {
		Region region = regions.get(id);
		if (region == null) {
			region = new Region(id);
			regions.put(id, region);
		}
		if (load) {
			region.checkLoadMap();
		}
		return region;
		// }

	}

	/*
	 * public static final void addPlayer(Player player) { players.add(player);
	 * AntiFlood.add(player.getSession().getIP()); }
	 * 
	 * public static void removePlayer(Player player) { players.remove(player);
	 * AntiFlood.remove(player.getSession().getIP()); }
	 */

	public static final void addNPC(NPC npc) {
		npcs.add(npc);
	}

	public static final void removeNPC(NPC npc) {
		npcs.remove(npc);
	}

	public static final NPC spawnNPC(int id, WorldTile tile, int mapAreaNameHash, boolean canBeAttackFromOutOfArea,
			boolean spawned) {
		NPC n = null;
		HunterNPC hunterNPCs = HunterNPC.forId(id);
		if (hunterNPCs != null) {
			if (id == hunterNPCs.getNpcId()) {
				n = new ItemHunterNPC(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
			}
		} else if (id >= 5533 && id <= 5558) {
			n = new Elemental(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id == 15174) {
			n = new Pker(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id == 9441) {
			n = new FlameVortex(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id >= 8832 && id <= 8834) {
			n = new LivingRock(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id >= 13465 && id <= 13481) {
			n = new Revenant(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id == 1158 || id == 1160) {
			n = new KalphiteQueen(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id >= 8528 && id <= 8532) {
			n = new Nomad(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id == 6215 || id == 6211 || id == 3406 || id == 6216 || id == 6214 || id == 6215 || id == 6212
				|| id == 6219 || id == 6221 || id == 6218) {
			n = new GodwarsZammorakFaction(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id == 6254 || id == 6259) {
			n = new GodwarsSaradominFaction(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id == 6246 || id == 6236 || id == 6232 || id == 6240 || id == 6241 || id == 6242 || id == 6235
				|| id == 6234 || id == 6243 || id == 6236 || id == 6244 || id == 6237 || id == 6246 || id == 6238
				|| id == 6239 || id == 6230) {
			n = new GodwarsArmadylFaction(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id == 6281 || id == 6282 || id == 6275 || id == 6279 || id == 9184 || id == 6268 || id == 6270
				|| id == 6274 || id == 6277 || id == 6276 || id == 6278) {
			n = new GodwarsBandosFaction(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id == 6261 || id == 6263 || id == 6265) {
			n = GodWarsBosses.graardorMinions[(id - 6261) / 2] = new GodWarMinion(id, tile, mapAreaNameHash,
					canBeAttackFromOutOfArea, spawned);
		} else if (id == 6260) {
			n = new GeneralGraardor(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id == 6222) {
			n = new KreeArra(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id == 6223 || id == 6225 || id == 6227) {
			n = GodWarsBosses.armadylMinions[(id - 6223) / 2] = new GodWarMinion(id, tile, mapAreaNameHash,
					canBeAttackFromOutOfArea, spawned);
		} else if (id == 6203) {
			n = new KrilTstsaroth(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id == 6204 || id == 6206 || id == 6208) {
			n = GodWarsBosses.zamorakMinions[(id - 6204) / 2] = new GodWarMinion(id, tile, mapAreaNameHash,
					canBeAttackFromOutOfArea, spawned);
		} else if (id == 50 || id == 2642) {
			n = new KingBlackDragon(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
			// } else if (id == 30139) {
			// n = new MinimePet(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea,
			// spawned);
		} else if (id >= 9462 && id <= 9467) {
			n = new Strykewyrm(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea);
		} else if (id == 30030) {
			n = new Ingenuity(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id == 30031) {
			n = new DrygonicGod(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id == 30057 || id == 30058 || id == 30059) {
			n = new Suns(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id == 30053 || id == 30055 || id == 30054) {
			n = new Drygon(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id == 6248 || id == 6250 || id == 6252) {
			n = GodWarsBosses.commanderMinions[(id - 6248) / 2] = new GodWarMinion(id, tile, mapAreaNameHash,
					canBeAttackFromOutOfArea, spawned);
		} else if (id == 6247) {
			n = new CommanderZilyana(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id == 8133) {
			n = new CorporealBeast(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id == 13955) {
			n = new TheRaptor(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id == 37503) {
			n = new Callisto(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id == 37504) {
			n = new Venenatis(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id == 37619) {
			n = new ChaosFanatic(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id == 37618) {
			n = new CrazyArchaeologist(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id == 37615) {
			n = new Scorpia(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id == 37617) {
			n = new ScorpiaGuardian(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id == 37611 || id == 37612 || id == 37613) {
			n = new Vetion(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id == 16385 || id == 16386) {
			n = new Kraken(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id == 31499) {
			n = new ThurmoSmokeDevil(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id == 39061) {
			n = new Vorkath(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id == 30219) {
			n = new VorkathSpawns(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id == 16390) {
			n = new Snakeling(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id == 16387 || id == 16388 || id == 16389) {
			n = new Zulrah(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id == 36886 || id == 36890 || id == 36891) {
			n = new Sire(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id == 38286) {
			n = new Skotizo(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id == 29975) {
			n = new AngryEasterbunny(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id == 38287) {
			n = new SkotizoMinion(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id == 30145) {
			n = new Justiciar(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id == 30216) {
			n = new BloodBeast(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id == 13000 || id == 13014) {
			n = new Ramokee(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id == 13711) {
			n = new TrollRanger(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id == 9928) {
			n = new IceFiend(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id == 38540 || id == 38542 || id == 38544) {
			n = new Tekton(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id == 4852) {
			n = new Vampyre(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id == 11564) {
			n = new PitRock(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id == 37766) {
			n = new Lizardman(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id == 39609) {
			n = new RegularHydra(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id == 39615 || id == 39619 || id == 39620 || id == 39621) {
			n = new HydraBoss(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id == 39612) {
			n = new Drake(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id == 15222) {
			n = new Sunfreet(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id == 30009) {
			n = new VoragoRS2(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id == 30000) {
			n = new VoragoEnraged(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id == 29993) {
			n = new KalphiteKing(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id == 29981) {
			n = new Celestia(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		/*} else if (id == 15174) {
			n = new DryaxInfo(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);*/
		} else if (id == 36862) {
			n = new Cerberus(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id == 36867 || id == 36868 || id == 36869) {
			n = new SummonedSoul(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id == 30167 || id == 30165 || id == 30163) {
			n = new CorruptedBrothers(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id == 30011 || id == 30012 || id == 30013 || id == 30014 || id == 30015 || id == 30016) {
			n = new ROT6(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id == 36918) {
			n = new Spawn(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id == 13447) {
			n = ZarosGodwars.nex = new Nex(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id == 13451) {
			n = ZarosGodwars.fumus = new NexMinion(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id == 13452) {
			n = ZarosGodwars.umbra = new NexMinion(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id == 13453) {
			n = ZarosGodwars.cruor = new NexMinion(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id == 15581) {
			n = new PartyDemon(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id == 10057) {
			n = new IcyBones(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id == 30149) {
			n = new TheAssassin(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id == 30081 || id == 30082) {
			n = new Anivia(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id == 15) {
			n = new Lauren(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id == 11750) {
			n = new DungBossLow(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id == 9766) {
			n = new DungBossMed(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id == 9911) {
			n = new DungBossHigh(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id == 30008) {
			n = new SirenicSpider(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id == 30078 || id == 30079 || id == 30080) {
			n = new TrioBossC(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id == 30087) {
			n = new Gulega(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id == 11751) {
			n = new NecroLord(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id == 12878) {
			n = new Blink(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id == 8596) {
			n = new AOD(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id == 8281) {
			n = new BalanceElemental(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id == 13454) {
			n = ZarosGodwars.glacies = new NexMinion(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id == 14256) {
			n = new Lucien(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id == 8335) {
			n = new MercenaryMage(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		} else if (id == 8349 || id == 8450 || id == 8451) {
			n = new TormentedDemon(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		/*} else if (id == 15149) {
			n = new MasterOfFear(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);*/
		} else {
			n = new NPC(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		}
		return n;
	}

	public static final NPC spawnNPC(int id, WorldTile tile, int mapAreaNameHash, boolean canBeAttackFromOutOfArea) {
		return spawnNPC(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, false);
	}

	/*
	 * check if the entity region changed because moved or teled then we update
	 * it
	 */
	public static final void updateEntityRegion(Entity entity) {
		if (entity.hasFinished()) {
			if (entity instanceof Player) {
				getRegion(entity.getLastRegionId()).removePlayerIndex(entity.getIndex());
			} else {
				getRegion(entity.getLastRegionId()).removeNPCIndex(entity.getIndex());
			}
			return;
		}
		int regionId = entity.getRegionId();
		if (entity.getLastRegionId() != regionId) { // map region entity at
			// changed
			if (entity instanceof Player) {
				if (entity.getLastRegionId() > 0) {
					getRegion(entity.getLastRegionId()).removePlayerIndex(entity.getIndex());
				}
				Region region = getRegion(regionId);
				region.addPlayerIndex(entity.getIndex());
				Player player = (Player) entity;
				int musicId = region.getRandomMusicId();
				if (musicId != -1) {
					player.getMusicsManager().checkMusic(musicId);
				}
				player.getControlerManager().moved();
				if (player.hasStarted()) {
					checkControlersAtMove(player);
				}
			} else {
				if (entity.getLastRegionId() > 0) {
					getRegion(entity.getLastRegionId()).removeNPCIndex(entity.getIndex());
				}
				getRegion(regionId).addNPCIndex(entity.getIndex());
			}
			entity.checkMultiArea();
			entity.setLastRegionId(regionId);
		} else {
			if (entity instanceof Player) {
				Player player = (Player) entity;
				player.getControlerManager().moved();
				if (player.hasStarted()) {
					checkControlersAtMove(player);
				}
			}
			entity.checkMultiArea();
		}
	}

	private static void checkControlersAtMove(Player player) {
		if (!(player.getControlerManager().getControler() instanceof RequestController)
				&& RequestController.inWarRequest(player)) {
			player.getControlerManager().startControler("clan_wars_request");
		} else if (DuelControler.isAtDuelArena(player) && !(player.getControlerManager().getControler() instanceof DuelControler)) {
			player.getControlerManager().startControler("DuelControler");
		} else if (FfaZone.inArea(player)) {
			player.getControlerManager().startControler("clan_wars_ffa");
		}
	}

	public static final boolean containsPlayer(String username) {
		for (Player p2 : players) {
			if (p2 == null) {
				continue;
			}
			if (p2.getUsername().equals(username)) {
				return true;
			}
		}
		return false;
	}

	public static Player getPlayer(String username) {
		for (Player player : getPlayers()) {
			if (player == null) {
				continue;
			}
			if (player.getUsername().equals(username)) {
				return player;
			}
		}
		return null;
	}

	public static final Player getPlayerByDisplayName(String username) {
		String formatedUsername = Utils.formatPlayerNameForDisplay(username);
		for (Player player : getPlayers()) {
			if (player == null) {
				continue;
			}
			if (player.getUsername().equalsIgnoreCase(formatedUsername)
					|| player.getDisplayName().equalsIgnoreCase(formatedUsername)) {
				return player;
			}
		}
		return null;
	}

	public static final EntityList<Player> getPlayers() {
		return players;
	}

	public static final EntityList<NPC> getNPCs() {
		return npcs;
	}

	private World() {

	}

	public static final void safeShutdown(final boolean restart, int delay) {
		if (exiting_start != 0) {
			return;
		}
		exiting_start = Utils.currentTimeMillis();
		exiting_delay = delay;
		Lottery.INSTANCE.giveLotteryPrice();
		for (Player player : World.getPlayers()) {
			if (player == null || !player.hasStarted() || player.hasFinished()) {
				continue;
			}
			player.getPackets().sendSystemUpdate(delay);
		}
		CoresManager.slowExecutor.schedule(new Runnable() {
			@Override
			public void run() {
				try {
					Lottery.INSTANCE.cancelLottery();
					for (Player player : World.getPlayers()) {
						if (player == null || !player.hasStarted()) {
							continue;
						}
						player.realFinish();
					}
					IPBanL.save();
					PkRank.save();
					CalamityBestWave.save();
					TotalBossKills.save();
					TopTriviaAnswers.save();
					TopVoters.save();
					HeistBags.save();
					HeistGames.save();
					PrestigeHS.save();
					// MaxPlayersOnline.save();
					if (restart) {
						Launcher.restart();
					} else {
						Launcher.shutdown();
					}
				} catch (Throwable e) {
					Logger.handle(e);
				}
			}
		}, delay, TimeUnit.SECONDS);
	}

	/**
	 * Spawns The Shooting Star Every 1200 Seconds.
	 */
	public static void spawnStar() {
		WorldTasksManager.schedule(new WorldTask() {
			int loop;

			@Override
			public void run() {
				if (loop == 1200) {
					ShootingStar.stage = 8;
					ShootingStar.stardustMined = 0;
					ShootingStar.spawnRandomStar();
				}
				loop++;
			}
		}, 0, 1);
	}

	/**
	 * Removes The Star Sprite After 50 Seconds.
	 */
	public static void removeStarSprite(final Player player) {
		WorldTasksManager.schedule(new WorldTask() {
			int loop;

			@Override
			public void run() {
				if (loop == 50) {
					for (NPC n : World.getNPCs()) {
						if (n == null || n.getId() != 8091) {
							continue;
						}
						n.sendDeath(n); // Removes the Star Sprite.
						spawnStar(); // Spawns Another Star.
					}
				}
				loop++;
			}
		}, 0, 1);
	}

	public static final void addGroundItem(final Item item, final WorldTile tile) {
		final FloorItem floorItem = new FloorItem(item, tile, null, false, false);
		final Region region = getRegion(tile.getRegionId());
		region.forceGetFloorItems().add(floorItem);
		int regionId = tile.getRegionId();
		for (Player player : players) {
			if (player == null || !player.hasStarted() || player.hasFinished() || player.getPlane() != tile.getPlane()
					|| !player.getMapRegionsIds().contains(regionId)) {
				continue;
			}
			player.getPackets().sendGroundItem(floorItem);
		}
	}

	public static final void addGroundItem(final Item item, final WorldTile tile,
			final Player owner/* null for default */, final boolean underGrave,
			long hiddenTime/* default 3minutes */, boolean invisible) {
		if (item.getId() == 29213 && owner.getInventory().contains(29197)) {
			if (owner.getInventory().getFreeSlots() == 0 && !owner.getInventory().containsItem(item.getId(), 1)) {
				addGroundItem(item, tile, owner, underGrave, hiddenTime, invisible, false, 60);
			} else {
				owner.getInventory().addItem(item.getId(), item.getAmount());
			}
		} else if (item.getId() == 995 && owner.coincollectorperk) {
			int amount = /*Settings.DOUBLEDROPS ? item.getAmount() * 2 :*/ item.getAmount();
			if (owner.coinamount + amount > 0) {
				int test = owner.coinamount + amount;
				if (owner.coinamount + amount > Integer.MAX_VALUE || owner.coinamount + amount < 0 || owner.coinamount >= 2147483647) {
					owner.getPackets().sendGameMessage("You can't have more then 2,147,483,647 coins in your pouch.");
					return;
				}
				owner.getPackets().sendRunScript(5561, 1, amount);
				owner.coinamount += amount;
				owner.refreshMoneyPouch();
			} else {
				addGroundItem(item, tile, owner, underGrave, hiddenTime, invisible, false, 60);
			}
		} else
		if (!item.getDefinitions().isStackable()) {
			for (int i = 0; i < item.getAmount(); i++) {
				addGroundItem(item, tile, owner, underGrave, hiddenTime, invisible, false, 60);
			}
		} else {
			addGroundItem(item, tile, owner, underGrave, hiddenTime, invisible, false, 60);
		}
	}

	public static final void addGroundItem(final Item item, final WorldTile tile,
			final Player owner/* null for default */, final boolean underGrave,
			long hiddenTime/* default 3minutes */, boolean invisible, boolean intoGold) {
		addGroundItem(item, tile, owner, underGrave, hiddenTime, invisible, intoGold, 60);
	}

	public static final void addGroundItem(final Item item, final WorldTile tile,
			final Player owner/* null for default */, final boolean underGrave,
			long hiddenTime/* default 3minutes */, boolean invisible, boolean intoGold, final int publicTime) {
		if (intoGold) {
			if (!ItemConstants.isTradeable(item)) {
				// owner.sendMessage("Only the owner can pick up this item!");
				int price = item.getDefinitions().getValue();
				if (price <= 0) {
					return;
				}
				item.setId(995);
				item.setAmount(price / 2);
			}
		}
		final FloorItem floorItem = new FloorItem(item, tile, owner, owner == null ? false : underGrave, invisible);
		final Region region = getRegion(tile.getRegionId());
		region.forceGetFloorItems().add(floorItem);
		if (invisible && hiddenTime != -1) {
			if (owner != null) {
				owner.getPackets().sendGroundItem(floorItem);
			}
			CoresManager.slowExecutor.schedule(new Runnable() {
				@Override
				public void run() {
					try {
						if (!region.forceGetFloorItems().contains(floorItem)) {
							return;
						}
						int regionId = tile.getRegionId();
						if (underGrave || !ItemConstants.isTradeable(floorItem)
								|| item.getName().contains("Dr nabanik")) {
							region.forceGetFloorItems().remove(floorItem);
							if (owner != null) {
								if (owner.getMapRegionsIds().contains(regionId) && owner.getPlane() == tile.getPlane()) {
									owner.getPackets().sendRemoveGroundItem(floorItem);
								}
							}
							return;
						}

						floorItem.setInvisible(false);
						for (Player player : players) {
							if (player == null || player == owner || !player.hasStarted() || player.hasFinished()
									|| player.getPlane() != tile.getPlane()
									|| !player.getMapRegionsIds().contains(regionId)) {
								continue;
							}
							player.getPackets().sendGroundItem(floorItem);
						}
						removeGroundItem(floorItem, publicTime);
					} catch (Throwable e) {
						Logger.handle(e);
					}
				}
			}, hiddenTime, TimeUnit.SECONDS);
			return;
		}
		int regionId = tile.getRegionId();
		for (Player player : players) {
			if (player == null || !player.hasStarted() || player.hasFinished() || player.getPlane() != tile.getPlane()
					|| !player.getMapRegionsIds().contains(regionId)) {
				continue;
			}
			player.getPackets().sendGroundItem(floorItem);
		}
		removeGroundItem(floorItem, publicTime);
	}

	public static final boolean removeGroundItem1(Player player, FloorItem floorItem) {
		return removeGroundItem(player, floorItem, false);
	}

	public static final void updateGroundItem(Item item, final WorldTile tile, final Player owner) {
		final FloorItem floorItem = World.getRegion(tile.getRegionId()).getGroundItem(item.getId(), tile, owner);
		if (floorItem == null) {
			addGroundItem(item, tile, owner, false, 360, true);
			return;
		}
		floorItem.setAmount(floorItem.getAmount() + item.getAmount());
		owner.getPackets().sendRemoveGroundItem(floorItem);
		owner.getPackets().sendGroundItem(floorItem);

	}

	private static final void removeGroundItem(final FloorItem floorItem, long publicTime) {
		if (publicTime < 0) {
			return;
		}
		CoresManager.slowExecutor.schedule(new Runnable() {
			@Override
			public void run() {
				try {
					int regionId = floorItem.getTile().getRegionId();
					Region region = getRegion(regionId);
					if (!region.forceGetFloorItems().contains(floorItem)) {
						return;
					}
					region.forceGetFloorItems().remove(floorItem);
					for (Player player : World.getPlayers()) {
						if (player == null || !player.hasStarted() || player.hasFinished()
								|| player.getPlane() != floorItem.getTile().getPlane()
								|| !player.getMapRegionsIds().contains(regionId)) {
							continue;
						}
						player.getPackets().sendRemoveGroundItem(floorItem);
					}
				} catch (Throwable e) {
					Logger.handle(e);
				}
			}
		}, publicTime, TimeUnit.SECONDS);
	}

	public static final boolean removeGroundItem(Player player, FloorItem floorItem) {
		return removeGroundItem(player, floorItem, true);
	}

	public static final boolean removeGroundItem(Player player, FloorItem floorItem, boolean add) {
		int regionId = floorItem.getTile().getRegionId();
		Region region = getRegion(regionId);
		if (!region.forceGetFloorItems().contains(floorItem)) {
			return false;
		}
		if (player.getInventory().getFreeSlots() == 0 && (!floorItem.getDefinitions().isStackable() || !player.getInventory().containsItem(floorItem.getId(), 1))) {
			player.sm("Not enough space in your inventory.");
			return false;
		}
		region.forceGetFloorItems().remove(floorItem);
		if (add) {
			if (!ItemDefinitions.getItemDefinitions(floorItem.getId()).isStackable()) {
				player.getInventory().addItem(floorItem.getId(), 1);
			} else {
				player.getInventory().addItem(floorItem.getId(), floorItem.getAmount());
			}
		}
		if (floorItem.isInvisible() || floorItem.isGrave()) {
			player.getPackets().sendRemoveGroundItem(floorItem);
			return true;
		} else {
			for (Player p2 : World.getPlayers()) {
				if (p2 == null || !p2.hasStarted() || p2.hasFinished()
						|| p2.getPlane() != floorItem.getTile().getPlane() || !p2.getMapRegionsIds().contains(regionId)) {
					continue;
				}
				p2.getPackets().sendRemoveGroundItem(floorItem);
			}
			return true;
		}
	}

	public static final void sendObjectAnimation(WorldObject object, Animation animation) {
		sendObjectAnimation(null, object, animation);
	}

	public static final void sendObjectAnimation(Entity creator, WorldObject object, Animation animation) {
		if (creator == null) {
			for (Player player : World.getPlayers()) {
				if (player == null || !player.hasStarted() || player.hasFinished() || !player.withinDistance(object)) {
					continue;
				}
				player.getPackets().sendObjectAnimation(object, animation);
			}
		} else {
			for (int regionId : creator.getMapRegionsIds()) {
				List<Integer> playersIndexes = getRegion(regionId).getPlayerIndexes();
				if (playersIndexes == null) {
					continue;
				}
				for (Integer playerIndex : playersIndexes) {
					Player player = players.get(playerIndex);
					if (player == null || !player.hasStarted() || player.hasFinished()
							|| !player.withinDistance(object)) {
						continue;
					}
					player.getPackets().sendObjectAnimation(object, animation);
				}
			}
		}
	}

	public static final void sendGraphics(Entity creator, Graphics graphics, WorldTile tile) {
		if (creator == null) {
			for (Player player : World.getPlayers()) {
				if (player == null || !player.hasStarted() || player.hasFinished() || !player.withinDistance(tile)) {
					continue;
				}
				player.getPackets().sendGraphics(graphics, tile);
			}
		} else {
			for (int regionId : creator.getMapRegionsIds()) {
				List<Integer> playersIndexes = getRegion(regionId).getPlayerIndexes();
				if (playersIndexes == null) {
					continue;
				}
				for (Integer playerIndex : playersIndexes) {
					Player player = players.get(playerIndex);
					if (player == null || !player.hasStarted() || player.hasFinished() || !player.withinDistance(tile)) {
						continue;
					}
					player.getPackets().sendGraphics(graphics, tile);
				}
			}
		}
	}

	public static final void sendProjectile(Entity shooter, WorldTile startTile, WorldTile receiver, int gfxId,
			int startHeight, int endHeight, int speed, int delay, int curve, int startDistanceOffset) {
		for (int regionId : shooter.getMapRegionsIds()) {
			List<Integer> playersIndexes = getRegion(regionId).getPlayerIndexes();
			if (playersIndexes == null) {
				continue;
			}
			for (Integer playerIndex : playersIndexes) {
				Player player = players.get(playerIndex);
				if (player == null || !player.hasStarted() || player.hasFinished()
						|| !player.withinDistance(shooter) && !player.withinDistance(receiver)) {
					continue;
				}
				player.getPackets().sendProjectile(null, startTile, receiver, gfxId, startHeight, endHeight, speed,
						delay, curve, startDistanceOffset, 1);
			}
		}
	}

	public static final void sendProjectile(Drop drop, WorldTile shooter, Entity receiver, int gfxId, int startHeight,
			int endHeight, int speed, int delay, int curve, int startDistanceOffset) {
		for (int regionId : receiver.getMapRegionsIds()) {
			List<Integer> playersIndexes = getRegion(regionId).getPlayerIndexes();
			if (playersIndexes == null) {
				continue;
			}
			for (Integer playerIndex : playersIndexes) {
				Player player = players.get(playerIndex);
				if (player == null || !player.hasStarted() || player.hasFinished()
						|| !player.withinDistance(shooter) && !player.withinDistance(receiver)) {
					continue;
				}
				player.getPackets().sendProjectile(null, shooter, receiver, gfxId, startHeight, endHeight, speed, delay,
						curve, startDistanceOffset, 1);
			}
		}
	}

	public static final void sendProjectile(Entity shooter, WorldTile receiver, int gfxId, int startHeight,
			int endHeight, int speed, int delay, int curve, int startDistanceOffset) {
		for (int regionId : shooter.getMapRegionsIds()) {
			List<Integer> playersIndexes = getRegion(regionId).getPlayerIndexes();
			if (playersIndexes == null) {
				continue;
			}
			for (Integer playerIndex : playersIndexes) {
				Player player = players.get(playerIndex);
				if (player == null || !player.hasStarted() || player.hasFinished()
						|| !player.withinDistance(shooter) && !player.withinDistance(receiver)) {
					continue;
				}
				player.getPackets().sendProjectile(null, shooter, receiver, gfxId, startHeight, endHeight, speed, delay,
						curve, startDistanceOffset, shooter.getSize());
			}
		}
	}

	public static final void sendProjectile(Entity shooter, Entity receiver, int gfxId, int startHeight, int endHeight,
			int speed, int delay, int curve, int startDistanceOffset) {
		for (int regionId : shooter.getMapRegionsIds()) {
			List<Integer> playersIndexes = getRegion(regionId).getPlayerIndexes();
			if (playersIndexes == null) {
				continue;
			}
			for (Integer playerIndex : playersIndexes) {
				Player player = players.get(playerIndex);
				if (player == null || !player.hasStarted() || player.hasFinished()
						|| !player.withinDistance(shooter) && !player.withinDistance(receiver)) {
					continue;
				}
				int size = shooter.getSize();
				player.getPackets().sendProjectile(receiver,
						new WorldTile(shooter.getCoordFaceX(size), shooter.getCoordFaceY(size), shooter.getPlane()),
						receiver, gfxId, startHeight, endHeight, speed, delay, curve, startDistanceOffset, size);
			}
		}
	}



	public static final boolean isNoCannonArea(WorldTile tile) {
		int destX = tile.getX();
		int destY = tile.getY();
		return destX >= 2912 && destX <= 2939 && destY >= 5190 && destY <= 5216 // NEX
				|| destX >= 3684 && destX <= 3709 && destY >= 5795 && destY <= 5821 //kraken
				|| OsrsRaids(tile)
				|| isHomeArea(tile)
				|| Raptor(tile)
				|| TheatreofBlood(tile)
				|| Skotizo(tile)
				|| the3Amigos(tile)
				|| Celestia(tile)
				|| DZ4(tile)
				|| Scorpia(tile)
				|| HydraBossRoom(tile)
				|| WyvernCave(tile)
				//|| destX >= 2618 && destX <= 2732 && destY >= 5051 && destY <= 5115 // drygonic
				|| destX >= 2827 && destX <= 2938 && destY >= 5194 && destY <= 5367 // GWD
				|| destX >= 2958 && destX <= 2972 && destY >= 4371 && destY <= 4390 // outside corp
				|| destX >= 995 && destX <= 1037 && destY >= 627 && destY <= 666 // legio
				|| destX >= 1092 && destX <= 1122 && destY >= 641 && destY <= 696 // legio
				|| destX >= 1146 && destX <= 1215 && destY >= 598 && destY <= 643 // legio
				|| destX >= 111 && destX <= 128 && destY >= 4303 && destY <= 4320 // thecalamity

				|| destX >= 2900 && destX <= 2924 && destY >= 9900 && destY <= 9917 // trio
				|| destX >= 3535 && destX <= 3564 && destY >= 9486 && destY <= 9516 // newvorago
				|| destX >= 2830 && destX <= 2835 && destY >= 3867 && destY <= 3872 // thecalamitywait
				|| destX >= 2824 && destX <= 2830 && destY >= 3846 && destY <= 3852 // thecalamitywait
				|| destX >= 79 && destX <= 110 && destY >= 81 && destY <= 109 // QBD
				|| destX >= 70 && destX <= 121 && destY >= 71 && destY <= 121 // KILN
				|| destX >= 1959 && destX <= 1978 && destY >= 3242 && destY <= 3261 // vorago
				|| destX >= 66 && destX <= 126 && destY >= 66 && destY <= 125; // CAVES
		// || (destX >= 2326 && destX <= 2342 && destY >= 10002 && destY <=
		// 10021); //SUMMONING
	}
	public static final boolean inNieveDungeon(WorldTile tile) {
		int destX = tile.getX();
		int destY = tile.getY();
		return destX >= 2376 && destX <= 2498 && destY >= 9765 && destY <= 9840;
	}

	public static final boolean isNopeArea(WorldTile tile) {
		int destX = tile.getX();
		int destY = tile.getY();
		return destX >= 2070 && destX <= 2136 && destY >= 3844 && destY <= 3880 // addy/rune
																					// drags
				|| destX >= 2618 && destX <= 2732 && destY >= 5051 && destY <= 5115 // drygonic
				//|| (destX >= 3453 && destX <= 3468 && destY >= 3714 && destY <= 3726 && tile.getPlane() == 1) // new yr boss lauren 2
				|| destX >= 3684 && destX <= 3709 && destY >= 5795 && destY <= 5821 //kraken
				|| destX >= 2310 && destX <= 2353 && destY >= 4356 && destY <= 4413 //Raptor
				|| destX >= 3442 && destX <= 3457 && destY >= 3737 && destY <= 3756 //icy bones
				|| destX >= 1856 && destX <= 1983 && destY >= 5056 && destY <= 5119 // CONSTRUCTION
				|| destX >= 2972 && destX <= 3005 && destY >= 4367 && destY <= 4400 // CORP
				|| destX >= 1216 && destX <= 1216 && destY >= 1279 && destY <= 1279 // cerberus
				|| OsrsRaids(tile)
				|| AssassinArea(tile)
				|| Dryax(tile) // area
				|| HydraBossRoom(tile)
				|| TheatreofBlood(tile)
				|| Raptor(tile)
				|| Skotizo(tile)
				|| the3Amigos(tile)
				|| Celestia(tile)
				|| Scorpia(tile)
				|| destX >= 2912 && destX <= 2939 && destY >= 5190 && destY <= 5216 // NEX
				|| destX >= 3466 && destX <= 3523 && destY >= 9696 && destY <= 9730 // NEX
				|| destX >= 3807 && destX <= 3819 && destY >= 3055 && destY <= 3067 // necrolord
				|| destX >= 2255 && destX <= 2304 && destY >= 3596 && destY <= 3671 // Drygon
				|| destX >= 995 && destX <= 1037 && destY >= 627 && destY <= 666 // legio
				|| destX >= 1092 && destX <= 1122 && destY >= 641 && destY <= 696 // legio
				|| destX >= 1146 && destX <= 1215 && destY >= 598 && destY <= 643 // legio
				|| destX >= 2770 && destX <= 2805 && destY >= 3768 && destY <= 3839 // Anivia area
				|| destX >= 3648 && destX <= 3678 && destY >= 5759 && destY <= 5788 // smoke devil
				|| destX >= 111 && destX <= 128 && destY >= 4303 && destY <= 4320 // thecalamity
				|| destX >= 3535 && destX <= 3564 && destY >= 9486 && destY <= 9516 // newvorago
				|| destX >= 3212 && destX <= 3264 && destY >= 9849 && destY <= 9893 // pdemon
				|| destX >= 3480 && destX <= 3505 && destY >= 9797 && destY <= 9850 // dung
																						// medlevel
				|| destX >= 2820 && destX <= 2858 && destY >= 10045 && destY <= 10114 // dung
																						// highlevel
				|| destX >= 2830 && destX <= 2835 && destY >= 3867 && destY <= 3872 // thecalamitywait
				|| destX >= 2824 && destX <= 2830 && destY >= 3846 && destY <= 3852 // thecalamitywait
				|| destX >= 2900 && destX <= 2924 && destY >= 9900 && destY <= 9917 // trio
																						// raid
				|| destX >= 2812 && destX <= 2947 && destY >= 5247 && destY <= 5378 // GWD
				|| destX >= 79 && destX <= 110 && destY >= 81 && destY <= 109 // QBD
				|| destX >= 70 && destX <= 121 && destY >= 71 && destY <= 121 // KILN
				|| destX >= 2750 && destX <= 2781 && destY >= 2960 && destY <= 2994 // dryax
				|| destX >= 1959 && destX <= 1978 && destY >= 3242 && destY <= 3261 // vorago
				|| destX >= 3347 && destX <= 3366 && destY >= 2780 && destY <= 2800// KK
				|| destX >= 3604 && destX <= 3627 && destY >= 3330 && destY <= 3352 // rot6
				|| destX >= 3378 && destX <= 3400 && destY >= 3506 && destY <= 3527// dboss
				|| destX >= 2408 && destX <= 2430 && destY >= 3521 && destY <= 3536// gazer
				|| destX >= 2637 && destX <= 2654 && destY >= 10419 && destY <= 10428// santa
				|| destX >= 66 && destX <= 126 && destY >= 66 && destY <= 125; // CAVES
	}

	public static final boolean isWarriorgArea(WorldTile tile) {
		int destX = tile.getX();
		int destY = tile.getY();
		return destX >= 2837 && destX <= 2877 && destY >= 3531 && destY <= 3553; // WARRIORG
	}

	public static final boolean isHomeArea(WorldTile tile) {
		int destX = tile.getX();
		int destY = tile.getY();
		return destX >= 2802 && destX <= 2869 && destY >= 3330 && destY <= 3392; // home
	}

	public static final boolean the3Amigos(WorldTile tile) {
		int destX = tile.getX();
		int destY = tile.getY();
		return destX >= 575 && destX <= 592 && destY >= 4544 && destY <= 4560;
	}
	
	public static final boolean isMaxGuild(WorldTile tile) {
		int destX = tile.getX();
		int destY = tile.getY();
		return destX >= 3335 && destX <= 3368 && destY >= 3332 && destY <= 3348; // home
	}

	// public static final boolean isDrygonStrong(WorldTile tile) {
	// int destX = tile.getX();
	// int destY = tile.getY();
	// return destX >= 2618 && destX <= 2732 && destY >= 5051 && destY <= 5115; //
	// drygonic
	// // kingdom
	// }

	public static final boolean isDrygon(WorldTile tile) {
		int destX = tile.getX();
		int destY = tile.getY();
		return destX >= 2255 && destX <= 2288 && destY >= 3596 && destY <= 3652; // Drygon
																					// boss
	}

	public static final boolean isMiningAreaBad(WorldTile tile) {
		int destX = tile.getX();
		int destY = tile.getY();
		return destX >= 3185 && destX <= 3198 && destY >= 9816 && destY <= 9837; // MINING
																					// AREA
	}

	public static final boolean KaneProp(WorldTile tile) {
		int destX = tile.getX();
		int destY = tile.getY();
		return destX >= 2717 && destX <= 2739 && destY >= 3350 && destY <= 3387; // Kane's
																					// house
	}

	public static final boolean TheTrioRaid(WorldTile tile) {
		int destX = tile.getX();
		int destY = tile.getY();
		return destX >= 2900 && destX <= 2924 && destY >= 9900 && destY <= 9917;
	}
	
	public static final boolean Level3Zone(WorldTile tile) {
		int destX = tile.getX();
		int destY = tile.getY();
		return destX >= 4435 && destX <= 4462 && destY >= 4307 && destY <= 4332;
	}
	
	public static final boolean CommitzoClanZone(WorldTile tile) {
		int destX = tile.getX();
		int destY = tile.getY();
		return destX >= 2493 && destX <= 2625 && destY >= 3838 && destY <= 3904;
	}
	
	public static final boolean WoodcuttingGuild(WorldTile tile) {
		int destX = tile.getX();
		int destY = tile.getY();
		return destX >= 1553 && destX <= 1602 && destY >= 3473 && destY <= 3506;
	}

	public static final boolean WyvernCave(WorldTile tile) {
		int destX = tile.getX();
		int destY = tile.getY();
		return destX >= 3584 && destX <= 3647 && destY >= 10240 && destY <= 10303;
	}

	public static final boolean GulegaRaid(WorldTile tile) {
		int destX = tile.getX();
		int destY = tile.getY();
		return destX >= 2371 && destX <= 2432 && destY >= 10236 && destY <= 10303&& tile.getPlane() == 1;
	}
	
	public static final boolean NewYRBoss2(WorldTile tile) {
		int destX = tile.getX();
		int destY = tile.getY();
		return destX >= 3453 && destX <= 3468 && destY >= 3714 && destY <= 3726;
	}
	
	public static final boolean Chill(WorldTile tile) {
		int destX = tile.getX();
		int destY = tile.getY();
		return destX >= 2833 && destX <= 2886 && destY >= 2558 && destY <= 2613;
	}
	
	public static final boolean DiceArea(WorldTile tile) {
		int destX = tile.getX();
		int destY = tile.getY();
		return destX >= 2850 && destX <= 2858 && destY >= 2571 && destY <= 2578;
	}

	public static final boolean DungMedCrowbar(WorldTile tile) {
		int destX = tile.getX();
		int destY = tile.getY();
		return destX >= 3470 && destX <= 3490 && destY >= 9836 && destY <= 9849;
	}

	public static final boolean CatacombsOSRS(WorldTile tile) {
		int destX = tile.getX();
		int destY = tile.getY();
		return destX >= 1600 && destX <= 1727 && destY >= 9984 && destY <= 10111;
	}

	public static final boolean Skotizo(WorldTile tile) {
		int destX = tile.getX();
		int destY = tile.getY();
		return destX >= 1664 && destX <= 1727 && destY >= 9856 && destY <= 9919;
	}

	public static final boolean StaffZone(WorldTile tile) {
		int destX = tile.getX();
		int destY = tile.getY();
		return destX >= 2636 && destX <= 2688 && destY >= 10058 && destY <= 10109;
	}

	public static final boolean TheatreofBlood(WorldTile tile) {
		int destX = tile.getX();
		int destY = tile.getY();
		return destX >= 2559 && destX <= 2627 && destY >= 9600 && destY <= 9664;
	}
	
	public static final boolean Raptor(WorldTile tile) {
		int destX = tile.getX();
		int destY = tile.getY();
		return destX >= 2638 && destX <= 2666 && destY >= 9628 && destY <= 9652;
	}

	public static final boolean JusticiarRaids(WorldTile tile) {
		int destX = tile.getX();
		int destY = tile.getY();
		return destX >= 2561 && destX <= 2576 && destY >= 9624 && destY <= 9635;
	}

	public static final boolean BloodBeastRaids(WorldTile tile) {
		int destX = tile.getX();
		int destY = tile.getY();
		return destX >= 2587 && destX <= 2613 && destY >= 9633 && destY <= 9653;
	}

	public static final boolean TrollRaids(WorldTile tile) {
		int destX = tile.getX();
		int destY = tile.getY();
		return destX >= 2581 && destX <= 2595 && destY >= 9603 && destY <= 9617;
	}

	public static final boolean RamokeeRaids(WorldTile tile) {
		int destX = tile.getX();
		int destY = tile.getY();
		return destX >= 2578 && destX <= 2602 && destY >= 9654 && destY <= 9664;
	}
	public static final boolean TheCalamity(WorldTile tile) {
		int destX = tile.getX();
		int destY = tile.getY();
		return destX >= 111 && destX <= 128 && destY >= 4303 && destY <= 4320;
	}

	public static final boolean TheCalamityWaiting(WorldTile tile) {
		int destX = tile.getX();
		int destY = tile.getY();
		return destX >= 2823 && destX <= 2830 && destY >= 3846 && destY <= 3853;
	}
	
	public static final boolean HungerGames(WorldTile tile) {
		int destX = tile.getX();
		int destY = tile.getY();
		return destX >= 2298 && destX <= 2425 && destY >= 3769 && destY <= 3874; // Hunger
																					// games
	}

	public static final boolean TempleofLight(WorldTile tile) {
		int destX = tile.getX();
		int destY = tile.getY();
		return destX >= 1855 && destX <= 1921 && destY >= 4606 && destY <= 4674;
	}
	
	public static final boolean Celestia(WorldTile tile) {
		int destX = tile.getX();
		int destY = tile.getY();
		return destX >= 3544 && destX <= 3578 && destY >= 9754 && destY <= 9789;
	}

	public static final boolean DrygonicPvp1(WorldTile tile) {
		int destX = tile.getX();
		int destY = tile.getY();
		return destX >= 2626 && destX <= 2661 && destY >= 5112 && destY <= 5119; // pvp
	}

	public static final boolean DrygonicPvp2(WorldTile tile) {
		int destX = tile.getX();
		int destY = tile.getY();
		return destX >= 2654 && destX <= 2668 && destY >= 5098 && destY <= 5103; // pvp
	}

	public static final boolean DrygonicPvp3(WorldTile tile) {
		int destX = tile.getX();
		int destY = tile.getY();
		return destX >= 2734 && destX <= 2749 && destY >= 5106 && destY <= 5115; // pvp
	}

	public static final boolean StarterArea(WorldTile tile) {
		int destX = tile.getX();
		int destY = tile.getY();
		return destX >= 2842 && destX <= 2855 && destY >= 3366 && destY <= 3375; // Hunger
																					// games
	}

	public static final boolean WildySkilling(WorldTile tile) {
		int destX = tile.getX();
		int destY = tile.getY();
		return destX >= 3180 && destX <= 3198 && destY >= 3920 && destY <= 3931; // WildySkilling
	}

	public static final boolean Vorago(WorldTile tile) {
		int destX = tile.getX();
		int destY = tile.getY();
		return destX >= 3535 && destX <= 3564 && destY >= 9486 && destY <= 9516; // rago
	}

	public static final boolean IngenuityBoss(WorldTile tile) {
		int destX = tile.getX();
		int destY = tile.getY();
		return destX >= 2575 && destX <= 2598 && destY >= 3902 && destY <= 3922;
	}

	public static final boolean Pdemon(WorldTile tile) {
		int destX = tile.getX();
		int destY = tile.getY();
		return destX >= 3212 && destX <= 3264 && destY >= 9849 && destY <= 9893; // pdemon
	}
	
	public static final boolean IcyBones(WorldTile tile) {
		int destX = tile.getX();
		int destY = tile.getY();
		return destX >= 3442 && destX <= 3457 && destY >= 3737 && destY <= 3756; // icy bones
	}

	public static final boolean DryVsSuns(WorldTile tile) {
		int destX = tile.getX();
		int destY = tile.getY();
		return destX >= 2239 && destX <= 2275 && destY >= 3320 && destY <= 3334; // dvs
	}
	
	public static final boolean DZ3(WorldTile tile) {
		int destX = tile.getX();
		int destY = tile.getY();
		return destX >= 1811 && destX <= 1843 && destY >= 5072 && destY <= 5105;
	}
	
	public static final boolean DZ4(WorldTile tile) {
		int destX = tile.getX();
		int destY = tile.getY();
		return destX >= 2560 && destX <= 2621 && destY >= 5567 && destY <= 5626;
	}

	public static final boolean Scorpia(WorldTile tile) {
		int destX = tile.getX();
		int destY = tile.getY();
		return destX >= 3219 && destX <= 3249 && destY >= 10329 && destY <= 10354;
	}
	
	public static final boolean PrestigeZone(WorldTile tile) {
		int destX = tile.getX();
		int destY = tile.getY();
		return destX >= 1371 && destX <= 1394 && destY >= 3814 && destY <= 3833;
	}
	
	public static final boolean DZ1(WorldTile tile) {
		int destX = tile.getX();
		int destY = tile.getY();
		return destX >= 2744 && destX <= 2777 && destY >= 4624 && destY <= 4653;
	}

	public static final boolean DZ5(WorldTile tile) {
		int destX = tile.getX();
		int destY = tile.getY();
		return destX >= 3392 && destX <= 3455 && destY >= 5248 && destY <= 5311;
	}

	public static final boolean AscDung(WorldTile tile) {
		int destX = tile.getX();
		int destY = tile.getY();
		return destX >= 991 && destX <= 1237 && destY >= 618 && destY <= 706; // dvs
	}

	public static final boolean IceFiendRaids(WorldTile tile) {
		int destX = tile.getX();
		int destY = tile.getY();
		return destX >= 3298 && destX <= 3325 && destY >= 5252 && destY <= 5277;
	}
	public static final boolean VampyreRaids(WorldTile tile) {
		int destX = tile.getX();
		int destY = tile.getY();
		return destX >= 3297 && destX <= 3326 && destY >= 5281 && destY <= 5310;
	}
	public static final boolean PitRockRaids(WorldTile tile) {
		int destX = tile.getX();
		int destY = tile.getY();
		return destX >= 3266 && destX <= 3294 && destY >= 5280 && destY <= 5311;
	}
	public static final boolean TektonRaids(WorldTile tile) {
		int destX = tile.getX();
		int destY = tile.getY();
		return destX >= 3267 && destX <= 3292 && destY >= 5250 && destY <= 5279;
	}
	public static final boolean OsrsRaids(WorldTile tile) {
		int destX = tile.getX();
		int destY = tile.getY();
		return destX >= 3264 && destX <= 3326 && destY >= 5185 && destY <= 5311;
	}

	public static final boolean AssassinArea(WorldTile tile) {
		int destX = tile.getX();
		int destY = tile.getY();
		return destX >= 3499 && destX <= 3521 && destY >= 9827 && destY <= 9850;
	}
	public static final boolean Dryax(WorldTile tile) {
		int destX = tile.getX();
		int destY = tile.getY();
		return destX >= 2750 && destX <= 2794 && destY >= 2963 && destY <= 2994;
	}
	public static final boolean PartyDemon(WorldTile tile) {
		int destX = tile.getX();
		int destY = tile.getY();
		return destX >= 3212 && destX <= 3264 && destY >= 9849 && destY <= 9893;
	}
	public static final boolean HydraBossRoom(WorldTile tile) {
		int destX = tile.getX();
		int destY = tile.getY();
		return destX >= 1356 && destX <= 1377 && destY >= 10257 && destY <= 10278;
	}
	public static final boolean CorpRoom(WorldTile tile) {
		int destX = tile.getX();
		int destY = tile.getY();
		return destX >= 2968 && destX <= 3006 && destY >= 4365 && destY <= 4401;
	}
	public static final boolean KaruulmSlayerDungeon(WorldTile tile) {
		int destX = tile.getX();
		int destY = tile.getY();
		return destX >= 1216 && destX <= 1333 && destY >= 10112 && destY <= 10282;
	}
	public static final boolean isMultiArea(WorldTile tile) {
		int destX = tile.getX();
		int destY = tile.getY();
		return destX >= 3462 && destX <= 3511 && destY >= 9481 && destY <= 9521 && tile.getPlane() == 0 // kalphite
																											// queen
																											// lair
				|| destX >= 4540 && destX <= 4799 && destY >= 5052 && destY <= 5183 && tile.getPlane() == 0 // thzaar
				
			//|| (destX >= 3453 && destX <= 3468 && destY >= 3714 && destY <= 3726 && tile.getPlane() == 1) // new yr boss lauren 2
				|| destX >= 3648 && destX <= 3678 && destY >= 5759 && destY <= 5788 // smoke devil
				|| destX >= 2770 && destX <= 2805 && destY >= 3768 && destY <= 3839 // Anivia area
				|| destX >= 3684 && destX <= 3709 && destY >= 5795 && destY <= 5821 //kraken
				|| destX >= 2310 && destX <= 2353 && destY >= 4356 && destY <= 4413 //Raptor
				|| destX >= 1216 && destX <= 1216 && destY >= 1279 && destY <= 1279 // cerberus
				|| destX >= 3442 && destX <= 3457 && destY >= 3737 && destY <= 3756 //icy bones
				|| destX >= 1721 && destX <= 1791 && destY >= 5123 && destY <= 5249 // mole
				|| destX >= 2977 && destX <= 3040 && destY >= 3875 && destY <= 3903 // HM
				|| destX >= 991 && destX <= 1237 && destY >= 618 && destY <= 706// ASC
				|| destX >= 3807 && destX <= 3819 && destY >= 3055 && destY <= 3067 // necrolord
				|| destX >= 3029 && destX <= 3374 && destY >= 3759 && destY <= 3903// wild
				|| destX >= 3157 && destX <= 3184 && destY >= 9757 && destY <= 9775// sunfreet
				|| destX >= 3328 && destX <= 3400 && destY >= 2749 && destY <= 3527// dboss
				|| destX >= 2968 && destX <= 2995 && destY >= 3933 && destY <= 3973// tectonicthebeasty
						|| destX >= 3347 && destX <= 3366 && destY >= 2780 && destY <= 2800// KK
				|| destX >= 2250 && destX <= 2280 && destY >= 4670 && destY <= 4720
				|| destX >= 111 && destX <= 128 && destY >= 4303 && destY <= 4320 // thecalamity
				|| destX >= 2915 && destX <= 2966 && destY >= 4356 && destY <= 4410// sunfreet`
				|| destX >= 3198 && destX <= 3380 && destY >= 3904 && destY <= 3970
				|| destX >= 2408 && destX <= 2430 && destY >= 3521 && destY <= 3536// gazer
				|| destX >= 2900 && destX <= 2924 && destY >= 9900 && destY <= 9917 // trio
				|| destX >= 1798 && destX <= 1816 && destY >= 3205 && destY <= 3219 //avatar of destruction
				|| isHomeArea(tile)
				|| KaruulmSlayerDungeon(tile)
				|| CommitzoClanZone(tile)
				|| Level3Zone(tile)
				|| destX >= 2371 && destX <= 2432 && destY >= 10236 && destY <= 10303&& tile.getPlane() == 1
				|| destX >= 3420 && destX <= 3427 && destY >= 3172 && destY <= 3179// clan
																						// cows
				|| destX >= 2596 && destX <= 2613 && destY >= 5597 && destY <= 5618// vip
																						// boss
				|| destX >= 3191 && destX <= 3326 && destY >= 3510 && destY <= 3759
				|| destX >= 2637 && destX <= 2654 && destY >= 10419 && destY <= 10428// santa
				|| destX >= 2987 && destX <= 3006 && destY >= 3912 && destY <= 3937
				|| destX >= 3212 && destX <= 3264 && destY >= 9849 && destY <= 9893 // pdemon
				|| destX >= 2618 && destX <= 2732 && destY >= 5051 && destY <= 5115 // drygonic
																						// kingdom
				|| destX >= 2245 && destX <= 2295 && destY >= 4675 && destY <= 4720
				// || (destX >= 2450 && destX <= 3520 && destY >= 9450 && destY
				// <= 9550)
				|| destX >= 3006 && destX <= 3071 && destY >= 3602 && destY <= 3710
				|| destX >= 2823 && destX <= 2874 && destY >= 5062 && destY <= 5114 // home
				|| destX >= 3134 && destX <= 3192 && destY >= 3519 && destY <= 3646
				|| destX >= 2750 && destX <= 2781 && destY >= 2960 && destY <= 2994 // dryax
				|| destX >= 2815 && destX <= 2966 && destY >= 5240 && destY <= 5375// wild
				|| destX >= 2840 && destX <= 2950 && destY >= 5190 && destY <= 5230 // godwars
				|| destX >= 3547 && destX <= 3555 && destY >= 9690 && destY <= 9699 // zaros
				|| destX >= 1959 && destX <= 1978 && destY >= 3242 && destY <= 3261 // vorago
				|| destX >= 3535 && destX <= 3564 && destY >= 9486 && destY <= 9516 // newvorago
				|| destX >= 3612 && destX <= 3627 && destY >= 3332 && destY <= 3352 // rot6
				|| destX >= 3270 && destX <= 3320 && destY >= 4804 && destY <= 4840 // Gladeon
																						// boss
				|| (destX >= 2255 && destX <= 2304 && destY >= 3596 && destY <= 3671) // Drygon
																						// boss
						| (destX >= 2893 && destX <= 2935 && destY >= 4432 && destY <= 4469)// DKS
		// godwars
				|| destX >= 2316 && destX <= 2329 && destY >= 3184 && destY <= 3195 // temptation
																						// clan
				|| HungerGames(tile) || DryVsSuns(tile) || KingBlackDragon.atKBD(tile) || Bork.atBork(tile) // Bork's
				|| OsrsRaids(tile) || Dryax(tile) // area
				|| TheatreofBlood(tile)
				|| Raptor(tile)
				|| Skotizo(tile)
				|| the3Amigos(tile)
				|| Celestia(tile)
				|| Scorpia(tile)
				|| AssassinArea(tile)
				|| destX >= 1600 && destX <= 1726 && destY >= 9984 && destY <= 10054 // catacombs
				|| destX >= 1616 && destX <= 1640 && destY >= 10045 && destY <= 10069 // catacombs
				|| destX >= 1641 && destX <= 1728 && destY >= 10056 && destY <= 10084 // catacombs
				|| destX >= 1667 && destX <= 1727 && destY >= 10081 && destY <= 10111 // catacombs
				|| destX >= 2970 && destX <= 3000 && destY >= 4365 && destY <= 4400// corp
				|| destX >= 3195 && destX <= 3327 && destY >= 3520 && destY <= 3970
						|| destX >= 2376 && 5127 >= destY && destX <= 2422 && 5168 <= destY
				|| destX >= 2374 && destY >= 5129 && destX <= 2424 && destY <= 5168 // pits
				|| destX >= 2622 && destY >= 5696 && destX <= 2573 && destY <= 5752 // torms
				|| destX >= 2368 && destY >= 3072 && destX <= 2431 && destY <= 3135 // castlewars
				|| destX >= 3460 && destY >= 3256 && destX <= 3527 && destY <= 3310 // Heist
		// out
				|| destX >= 2365 && destY >= 9470 && destX <= 2436 && destY <= 9532 // castlewars
				|| destX >= 2948 && destY >= 5537 && destX <= 3071 && destY <= 5631 // Risk
		// ffa.
				|| destX >= 2756 && destY >= 5537 && destX <= 2879 && destY <= 5631 // Safe
																						// ffa

				|| tile.getX() >= 3011 && tile.getX() <= 3132 && tile.getY() >= 10052 && tile.getY() <= 10175
						&& (tile.getY() >= 10066 || tile.getX() >= 3094) // fortihrny
																			// dungeon
		;
		// in

		// multi
	}

	public static final boolean isPvpArea(WorldTile tile) {
		return Wilderness.isAtWild(tile) || HungerGames(tile) || DrygonicPvp1(tile) || DrygonicPvp2(tile)
				|| Scorpia(tile) || DrygonicPvp3(tile);
	}

	public static void sendWorldMessage(String message, boolean forStaff) {
		for (Player p : World.getPlayers()) {
			if (p == null || !p.isRunning() || p.isYellOff() || forStaff && p.getRights() == 0) {
				continue;
			}
			p.getPackets().sendGameMessage(message);
		}
	}

	public static void sendIconWorldMessage(String message, boolean forStaff) {
		for (Player p : World.getPlayers()) {
			if (p == null || !p.isRunning() || p.isYellOff() || forStaff && p.getRights() == 0) {
				continue;
			}
			p.getPackets().sendGameMessage("<img=6>" + message);
		}
	}

	public static final void sendProjectile(WorldObject object, WorldTile startTile, WorldTile endTile, int gfxId,
			int startHeight, int endHeight, int speed, int delay, int curve, int startOffset) {
		for (Player pl : players) {
			if (pl == null || !pl.withinDistance(object, 20)) {
				continue;
			}
			pl.getPackets().sendProjectile(null, startTile, endTile, gfxId, startHeight, endHeight, speed, delay, curve,
					startOffset, 1);
		}
	}

	public static List<WorldTile> RESTRICTED_TILES = new ArrayList<WorldTile>();

	public static void deleteObject(WorldTile tile) {
		if (!RESTRICTED_TILES.contains(tile)) {
			RESTRICTED_TILES.add(tile);
		}
	}

	/**
	 * Construction
	 */

	public static final void spawnObject(WorldObject object) {
		getRegion(object.getRegionId()).spawnObject(object, object.getPlane(), object.getXInRegion(),
				object.getYInRegion(), false);
	}

	public static final void spawnObjectTemporary(final WorldObject object, long time) {
		spawnObject(object);
		CoresManager.slowExecutor.schedule(new Runnable() {
			@Override
			public void run() {
				try {
					// for (Player p2 : players) {
					// if (object.spawnedByEd)
					// World.sendWorldMessage("BANTWE", false);
					// p2.getPackets().sendSpawnedObject(object);
					// }
					if (!World.isSpawnedObject(object)) {
						return;
					}
					removeObject(object);
				} catch (Throwable e) {
					Logger.handle(e);
				}
			}

		}, time, TimeUnit.MILLISECONDS);
	}

	public static void spawnOverObject(WorldObject object, boolean clip) {
		int regionId = object.getRegionId();
		WorldObject placement = World.getSpawnedObject(object.getX(), object.getY(), object.getPlane());
		if (placement == null) {
			getRegion(regionId).spawnedObjects.add(object);
		} else {
			if (object.getId() == 4367) {
				return;
			}
			placement.setId(object.getId());
			placement.setRotation(object.getRotation());
			placement.setType(object.getType());
			object = placement;

		}
		synchronized (players) {
			for (Player p2 : players) {
				if (p2 == null || !p2.hasStarted() || p2.hasFinished() || !p2.getMapRegionsIds().contains(regionId)) {
					continue;
				}
				p2.getPackets().sendSpawnedObject(object);
			}
		}
	}

	public static final WorldObject getSpawnedObject(int x, int y, int plane) {
		return getObject(new WorldTile(x, y, plane));
	}

	public static final void spawnTempGroundObject(final WorldObject object, final int replaceId, long time) {
		spawnObject(object);
		CoresManager.slowExecutor.schedule(new Runnable() {
			@Override
			public void run() {
				try {
					removeObject(object);
					addGroundItem(new Item(replaceId), object, null, false, 60, false);
				} catch (Throwable e) {
					Logger.handle(e);
				}
			}
		}, time, TimeUnit.MILLISECONDS);
	}

	/*
	 * by default doesnt changeClipData
	 */
	public static final void spawnTemporaryObject(final WorldObject object, long time) {
		spawnTemporaryObject(object, time, false);
	}

	public static final void spawnTemporaryObject(final WorldObject object, long time, final boolean clip) {
		World.spawnObjectTemporary(object, time);

	}

	public static final void spawnObjectTemporary(final WorldObject object, long time,
			final boolean checkObjectInstance, boolean checkObjectBefore) {
		final WorldObject before = checkObjectBefore ? World.getObjectWithType(object, object.getType()) : null;
		spawnObject(object);
		CoresManager.slowExecutor.schedule(new Runnable() {
			@Override
			public void run() {
				try {
					if (checkObjectInstance && World.getObjectWithId(object, object.getId()) != object) {
						return;
					}
					if (before != null) {
						spawnObject(before);
					}
					else {
						removeObject(object); // this method allows to remove
												// object with just tile and
												// type actualy so the removing
												// object may be diferent and
												// still gets removed
					}
				} catch (Throwable e) {
					Logger.handle(e);
				}
			}

		}, time, TimeUnit.MILLISECONDS);
	}

	public static final void spawnTemporaryObject(final WorldObject object, long time, final boolean clip,
			final WorldObject actualObject) {
		World.spawnObjectTemporary(object, time);
	}

	public static final void spawnTemporaryObject(final WorldObject object, long time, final WorldObject actualObject) {
		spawnTemporaryObject(object, time, false, actualObject);
	}

	public static final void removeObject(WorldObject object) {
		getRegion(object.getRegionId()).removeObject(object, object.getPlane(), object.getXInRegion(),
				object.getYInRegion());
	}

	public static final boolean removeObjectTemporary(final WorldObject object, long time) {
		removeObject(object);
		CoresManager.slowExecutor.schedule(new Runnable() {
			@Override
			public void run() {
				try {
					spawnObject(object);
				} catch (Throwable e) {
					Logger.handle(e);
				}
			}

		}, time, TimeUnit.MILLISECONDS);
		return true;
	}

	public static final void removeOriginalObject(WorldObject object) {
		getRegion(object.getRegionId()).removeOriginalObject(object, object.getPlane(), object.getXInRegion(),
				object.getYInRegion());
	}

	public static final boolean removeTemporaryObject(final WorldObject object, long time, final boolean clip) {
		removeObjectTemporary(object, time);
		return true;
	}

	public static final WorldObject getObjectWithId(WorldTile tile, int id) {
		return getRegion(tile.getRegionId()).getObjectWithId(tile.getPlane(), tile.getXInRegion(), tile.getYInRegion(),
				id);
	}

	public static final WorldObject getObjectWithSlot(WorldTile tile, int slot) {
		return getRegion(tile.getRegionId()).getObjectWithSlot(tile.getPlane(), tile.getXInRegion(),
				tile.getYInRegion(), slot);
	}

	public static final WorldObject getObjectWithType(WorldTile tile, int type) {
		return getRegion(tile.getRegionId()).getObjectWithType(tile.getPlane(), tile.getXInRegion(),
				tile.getYInRegion(), type);
	}

	public static final boolean containsObjectWithId(WorldTile tile, int id) {
		return getRegion(tile.getRegionId()).containsObjectWithId(tile.getPlane(), tile.getXInRegion(),
				tile.getYInRegion(), id);
	}

	public static final boolean containsObject(WorldTile tile) {
		return getRegion(tile.getRegionId()).containsObject(tile.getPlane(), tile.getXInRegion(), tile.getYInRegion());
	}

	public static void destroySpawnedObject(WorldObject object) {
		getRegion(object.getRegionId()).removeObject(object, object.getPlane(), object.getXInRegion(),
				object.getYInRegion());
	}

	public static void destroySpawnedObject(WorldObject object, boolean clip) {
		getRegion(object.getRegionId()).removeObject(object, object.getPlane(), object.getXInRegion(),
				object.getYInRegion());
	}

	/*
	 * checks clip
	 */
	public static boolean canMoveNPC(int plane, int x, int y, int size) {
		for (int tileX = x; tileX < x + size; tileX++) {
			for (int tileY = y; tileY < y + size; tileY++) {
				if (getMask(plane, tileX, tileY) != 0) {
					return false;
				}
			}
		}
		return true;
	}

	/*
	 * checks clip
	 */
	public static boolean isNotCliped(int plane, int x, int y, int size) {
		for (int tileX = x; tileX < x + size; tileX++) {
			for (int tileY = y; tileY < y + size; tileY++) {
				if ((getMask(plane, tileX, tileY) & 2097152) != 0) {
					return false;
				}
			}
		}
		return true;
	}

	public static int getMask(int plane, int x, int y) {
		WorldTile tile = new WorldTile(x, y, plane);
		int regionId = tile.getRegionId();
		Region region = getRegion(regionId);
		if (region == null) {
			return -1;
		}
		int baseLocalX = x - (regionId >> 8) * 64;
		int baseLocalY = y - (regionId & 0xff) * 64;
		return region.getMask(tile.getPlane(), baseLocalX, baseLocalY);
	}

	public static void setMask(int plane, int x, int y, int mask) {
		WorldTile tile = new WorldTile(x, y, plane);
		int regionId = tile.getRegionId();
		Region region = getRegion(regionId);
		if (region == null) {
			return;
		}
		int baseLocalX = x - (regionId >> 8) * 64;
		int baseLocalY = y - (regionId & 0xff) * 64;
		region.setMask(tile.getPlane(), baseLocalX, baseLocalY, mask);
	}

	private static int getClipedOnlyMask(int plane, int x, int y) {
		WorldTile tile = new WorldTile(x, y, plane);
		int regionId = tile.getRegionId();
		Region region = getRegion(regionId);
		if (region == null) {
			return -1;
		}
		int baseLocalX = x - (regionId >> 8) * 64;
		int baseLocalY = y - (regionId & 0xff) * 64;
		return region.getMaskClipedOnly(tile.getPlane(), baseLocalX, baseLocalY);
	}

	public static final boolean checkProjectileStep(int plane, int x, int y, int dir, int size) {
		int xOffset = Utils.DIRECTION_DELTA_X[dir];
		int yOffset = Utils.DIRECTION_DELTA_Y[dir];
		/*
		 * int rotation = getRotation(plane,x+xOffset,y+yOffset); if(rotation !=
		 * 0) { dir += rotation; if(dir >= Utils.DIRECTION_DELTA_X.length) dir =
		 * dir - (Utils.DIRECTION_DELTA_X.length-1); xOffset =
		 * Utils.DIRECTION_DELTA_X[dir]; yOffset = Utils.DIRECTION_DELTA_Y[dir];
		 * }
		 */
		if (size == 1) {
			int mask = getClipedOnlyMask(plane, x + Utils.DIRECTION_DELTA_X[dir], y + Utils.DIRECTION_DELTA_Y[dir]);
			if (xOffset == -1 && yOffset == 0) {
				return (mask & 0x42240000) == 0;
			}
			if (xOffset == 1 && yOffset == 0) {
				return (mask & 0x60240000) == 0;
			}
			if (xOffset == 0 && yOffset == -1) {
				return (mask & 0x40a40000) == 0;
			}
			if (xOffset == 0 && yOffset == 1) {
				return (mask & 0x48240000) == 0;
			}
			if (xOffset == -1 && yOffset == -1) {
				return (mask & 0x43a40000) == 0 && (getClipedOnlyMask(plane, x - 1, y) & 0x42240000) == 0
						&& (getClipedOnlyMask(plane, x, y - 1) & 0x40a40000) == 0;
			}
			if (xOffset == 1 && yOffset == -1) {
				return (mask & 0x60e40000) == 0 && (getClipedOnlyMask(plane, x + 1, y) & 0x60240000) == 0
						&& (getClipedOnlyMask(plane, x, y - 1) & 0x40a40000) == 0;
			}
			if (xOffset == -1 && yOffset == 1) {
				return (mask & 0x4e240000) == 0 && (getClipedOnlyMask(plane, x - 1, y) & 0x42240000) == 0
						&& (getClipedOnlyMask(plane, x, y + 1) & 0x48240000) == 0;
			}
			if (xOffset == 1 && yOffset == 1) {
				return (mask & 0x78240000) == 0 && (getClipedOnlyMask(plane, x + 1, y) & 0x60240000) == 0
						&& (getClipedOnlyMask(plane, x, y + 1) & 0x48240000) == 0;
			}
		} else if (size == 2) {
			if (xOffset == -1 && yOffset == 0) {
				return (getClipedOnlyMask(plane, x - 1, y) & 0x43a40000) == 0
						&& (getClipedOnlyMask(plane, x - 1, y + 1) & 0x4e240000) == 0;
			}
			if (xOffset == 1 && yOffset == 0) {
				return (getClipedOnlyMask(plane, x + 2, y) & 0x60e40000) == 0
						&& (getClipedOnlyMask(plane, x + 2, y + 1) & 0x78240000) == 0;
			}
			if (xOffset == 0 && yOffset == -1) {
				return (getClipedOnlyMask(plane, x, y - 1) & 0x43a40000) == 0
						&& (getClipedOnlyMask(plane, x + 1, y - 1) & 0x60e40000) == 0;
			}
			if (xOffset == 0 && yOffset == 1) {
				return (getClipedOnlyMask(plane, x, y + 2) & 0x4e240000) == 0
						&& (getClipedOnlyMask(plane, x + 1, y + 2) & 0x78240000) == 0;
			}
			if (xOffset == -1 && yOffset == -1) {
				return (getClipedOnlyMask(plane, x - 1, y) & 0x4fa40000) == 0
						&& (getClipedOnlyMask(plane, x - 1, y - 1) & 0x43a40000) == 0
						&& (getClipedOnlyMask(plane, x, y - 1) & 0x63e40000) == 0;
			}
			if (xOffset == 1 && yOffset == -1) {
				return (getClipedOnlyMask(plane, x + 1, y - 1) & 0x63e40000) == 0
						&& (getClipedOnlyMask(plane, x + 2, y - 1) & 0x60e40000) == 0
						&& (getClipedOnlyMask(plane, x + 2, y) & 0x78e40000) == 0;
			}
			if (xOffset == -1 && yOffset == 1) {
				return (getClipedOnlyMask(plane, x - 1, y + 1) & 0x4fa40000) == 0
						&& (getClipedOnlyMask(plane, x - 1, y + 1) & 0x4e240000) == 0
						&& (getClipedOnlyMask(plane, x, y + 2) & 0x7e240000) == 0;
			}
			if (xOffset == 1 && yOffset == 1) {
				return (getClipedOnlyMask(plane, x + 1, y + 2) & 0x7e240000) == 0
						&& (getClipedOnlyMask(plane, x + 2, y + 2) & 0x78240000) == 0
						&& (getClipedOnlyMask(plane, x + 1, y + 1) & 0x78e40000) == 0;
			}
		} else {
			if (xOffset == -1 && yOffset == 0) {
				if ((getClipedOnlyMask(plane, x - 1, y) & 0x43a40000) != 0
						|| (getClipedOnlyMask(plane, x - 1, -1 + y + size) & 0x4e240000) != 0) {
					return false;
				}
				for (int sizeOffset = 1; sizeOffset < size - 1; sizeOffset++) {
					if ((getClipedOnlyMask(plane, x - 1, y + sizeOffset) & 0x4fa40000) != 0) {
						return false;
					}
				}
			} else if (xOffset == 1 && yOffset == 0) {
				if ((getClipedOnlyMask(plane, x + size, y) & 0x60e40000) != 0
						|| (getClipedOnlyMask(plane, x + size, y - (-size + 1)) & 0x78240000) != 0) {
					return false;
				}
				for (int sizeOffset = 1; sizeOffset < size - 1; sizeOffset++) {
					if ((getClipedOnlyMask(plane, x + size, y + sizeOffset) & 0x78e40000) != 0) {
						return false;
					}
				}
			} else if (xOffset == 0 && yOffset == -1) {
				if ((getClipedOnlyMask(plane, x, y - 1) & 0x43a40000) != 0
						|| (getClipedOnlyMask(plane, x + size - 1, y - 1) & 0x60e40000) != 0) {
					return false;
				}
				for (int sizeOffset = 1; sizeOffset < size - 1; sizeOffset++) {
					if ((getClipedOnlyMask(plane, x + sizeOffset, y - 1) & 0x63e40000) != 0) {
						return false;
					}
				}
			} else if (xOffset == 0 && yOffset == 1) {
				if ((getClipedOnlyMask(plane, x, y + size) & 0x4e240000) != 0
						|| (getClipedOnlyMask(plane, x + size - 1, y + size) & 0x78240000) != 0) {
					return false;
				}
				for (int sizeOffset = 1; sizeOffset < size - 1; sizeOffset++) {
					if ((getClipedOnlyMask(plane, x + sizeOffset, y + size) & 0x7e240000) != 0) {
						return false;
					}
				}
			} else if (xOffset == -1 && yOffset == -1) {
				if ((getClipedOnlyMask(plane, x - 1, y - 1) & 0x43a40000) != 0) {
					return false;
				}
				for (int sizeOffset = 1; sizeOffset < size; sizeOffset++) {
					if ((getClipedOnlyMask(plane, x - 1, y + -1 + sizeOffset) & 0x4fa40000) != 0
							|| (getClipedOnlyMask(plane, sizeOffset - 1 + x, y - 1) & 0x63e40000) != 0) {
						return false;
					}
				}
			} else if (xOffset == 1 && yOffset == -1) {
				if ((getClipedOnlyMask(plane, x + size, y - 1) & 0x60e40000) != 0) {
					return false;
				}
				for (int sizeOffset = 1; sizeOffset < size; sizeOffset++) {
					if ((getClipedOnlyMask(plane, x + size, sizeOffset + -1 + y) & 0x78e40000) != 0
							|| (getClipedOnlyMask(plane, x + sizeOffset, y - 1) & 0x63e40000) != 0) {
						return false;
					}
				}
			} else if (xOffset == -1 && yOffset == 1) {
				if ((getClipedOnlyMask(plane, x - 1, y + size) & 0x4e240000) != 0) {
					return false;
				}
				for (int sizeOffset = 1; sizeOffset < size; sizeOffset++) {
					if ((getClipedOnlyMask(plane, x - 1, y + sizeOffset) & 0x4fa40000) != 0
							|| (getClipedOnlyMask(plane, -1 + x + sizeOffset, y + size) & 0x7e240000) != 0) {
						return false;
					}
				}
			} else if (xOffset == 1 && yOffset == 1) {
				if ((getClipedOnlyMask(plane, x + size, y + size) & 0x78240000) != 0) {
					return false;
				}
				for (int sizeOffset = 1; sizeOffset < size; sizeOffset++) {
					if ((getClipedOnlyMask(plane, x + sizeOffset, y + size) & 0x7e240000) != 0
							|| (getClipedOnlyMask(plane, x + size, y + sizeOffset) & 0x78e40000) != 0) {
						return false;
					}
				}
			}
		}
		return true;
	}

	public static final boolean checkWalkStep(int plane, int x, int y, int dir, int size) {
		// return true;
		return checkWalkStep(plane, x, y, Utils.DIRECTION_DELTA_X[dir], Utils.DIRECTION_DELTA_Y[dir], size);
	}

	public static final boolean checkWalkStep(int plane, int x, int y, int xOffset, int yOffset, int size) {
		if (size == 1) {
			int mask = getMask(plane, x + xOffset, y + yOffset);
			if (xOffset == -1 && yOffset == 0) {
				return (mask
						& (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ | Flags.WALLOBJ_EAST)) == 0;
			}
			if (xOffset == 1 && yOffset == 0) {
				return (mask
						& (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ | Flags.WALLOBJ_WEST)) == 0;
			}
			if (xOffset == 0 && yOffset == -1) {
				return (mask
						& (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ | Flags.WALLOBJ_NORTH)) == 0;
			}
			if (xOffset == 0 && yOffset == 1) {
				return (mask
						& (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ | Flags.WALLOBJ_SOUTH)) == 0;
			}
			if (xOffset == -1 && yOffset == -1) {
				return (mask & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ | Flags.WALLOBJ_NORTH
						| Flags.WALLOBJ_EAST | Flags.CORNEROBJ_NORTHEAST)) == 0
						&& (getMask(plane, x - 1, y) & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ
								| Flags.WALLOBJ_EAST)) == 0
						&& (getMask(plane, x, y - 1) & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ
								| Flags.WALLOBJ_NORTH)) == 0;
			}
			if (xOffset == 1 && yOffset == -1) {
				return (mask & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ | Flags.WALLOBJ_NORTH
						| Flags.WALLOBJ_WEST | Flags.CORNEROBJ_NORTHWEST)) == 0
						&& (getMask(plane, x + 1, y) & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ
								| Flags.WALLOBJ_WEST)) == 0
						&& (getMask(plane, x, y - 1) & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ
								| Flags.WALLOBJ_NORTH)) == 0;
			}
			if (xOffset == -1 && yOffset == 1) {
				return (mask & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ | Flags.WALLOBJ_EAST
						| Flags.WALLOBJ_SOUTH | Flags.CORNEROBJ_SOUTHEAST)) == 0
						&& (getMask(plane, x - 1, y) & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ
								| Flags.WALLOBJ_EAST)) == 0
						&& (getMask(plane, x, y + 1) & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ
								| Flags.WALLOBJ_SOUTH)) == 0;
			}
			if (xOffset == 1 && yOffset == 1) {
				return (mask & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ | Flags.WALLOBJ_SOUTH
						| Flags.WALLOBJ_WEST | Flags.CORNEROBJ_SOUTHWEST)) == 0
						&& (getMask(plane, x + 1, y) & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ
								| Flags.WALLOBJ_WEST)) == 0
						&& (getMask(plane, x, y + 1) & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ
								| Flags.WALLOBJ_SOUTH)) == 0;
			}
		} else if (size == 2) {
			if (xOffset == -1 && yOffset == 0) {
				return (getMask(plane, x - 1, y) & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ
						| Flags.WALLOBJ_NORTH | Flags.WALLOBJ_EAST | Flags.CORNEROBJ_NORTHEAST)) == 0
						&& (getMask(plane, x - 1, y + 1)
								& (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ | Flags.WALLOBJ_EAST
										| Flags.WALLOBJ_SOUTH | Flags.CORNEROBJ_SOUTHEAST)) == 0;
			}
			if (xOffset == 1 && yOffset == 0) {
				return (getMask(plane, x + 2, y) & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ
						| Flags.WALLOBJ_NORTH | Flags.WALLOBJ_WEST | Flags.CORNEROBJ_NORTHWEST)) == 0
						&& (getMask(plane, x + 2, y + 1)
								& (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ | Flags.WALLOBJ_SOUTH
										| Flags.WALLOBJ_WEST | Flags.CORNEROBJ_SOUTHWEST)) == 0;
			}
			if (xOffset == 0 && yOffset == -1) {
				return (getMask(plane, x, y - 1) & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ
						| Flags.WALLOBJ_NORTH | Flags.WALLOBJ_EAST | Flags.CORNEROBJ_NORTHEAST)) == 0
						&& (getMask(plane, x + 1, y - 1)
								& (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ | Flags.WALLOBJ_NORTH
										| Flags.WALLOBJ_WEST | Flags.CORNEROBJ_NORTHWEST)) == 0;
			}
			if (xOffset == 0 && yOffset == 1) {
				return (getMask(plane, x, y + 2) & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ
						| Flags.WALLOBJ_EAST | Flags.WALLOBJ_SOUTH | Flags.CORNEROBJ_SOUTHEAST)) == 0
						&& (getMask(plane, x + 1, y + 2)
								& (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ | Flags.WALLOBJ_SOUTH
										| Flags.WALLOBJ_WEST | Flags.CORNEROBJ_SOUTHWEST)) == 0;
			}
			if (xOffset == -1 && yOffset == -1) {
				return (getMask(plane, x - 1, y) & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ
						| Flags.WALLOBJ_NORTH | Flags.WALLOBJ_EAST | Flags.WALLOBJ_SOUTH | Flags.CORNEROBJ_NORTHEAST
						| Flags.CORNEROBJ_SOUTHEAST)) == 0
						&& (getMask(plane, x - 1, y - 1)
								& (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ | Flags.WALLOBJ_NORTH
										| Flags.WALLOBJ_EAST | Flags.CORNEROBJ_NORTHEAST)) == 0
						&& (getMask(plane, x, y - 1) & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ
								| Flags.WALLOBJ_NORTH | Flags.WALLOBJ_EAST | Flags.WALLOBJ_WEST
								| Flags.CORNEROBJ_NORTHWEST | Flags.CORNEROBJ_NORTHEAST)) == 0;
			}
			if (xOffset == 1 && yOffset == -1) {
				return (getMask(plane, x + 1, y - 1) & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ
						| Flags.WALLOBJ_NORTH | Flags.WALLOBJ_EAST | Flags.WALLOBJ_WEST | Flags.CORNEROBJ_NORTHWEST
						| Flags.CORNEROBJ_NORTHEAST)) == 0
						&& (getMask(plane, x + 2, y - 1)
								& (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ | Flags.WALLOBJ_NORTH
										| Flags.WALLOBJ_WEST | Flags.CORNEROBJ_NORTHWEST)) == 0
						&& (getMask(plane, x + 2, y) & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ
								| Flags.WALLOBJ_NORTH | Flags.WALLOBJ_SOUTH | Flags.WALLOBJ_WEST
								| Flags.CORNEROBJ_NORTHWEST | Flags.CORNEROBJ_SOUTHWEST)) == 0;
			}
			if (xOffset == -1 && yOffset == 1) {
				return (getMask(plane, x - 1, y + 1) & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ
						| Flags.WALLOBJ_NORTH | Flags.WALLOBJ_EAST | Flags.WALLOBJ_SOUTH | Flags.CORNEROBJ_NORTHEAST
						| Flags.CORNEROBJ_SOUTHEAST)) == 0
						&& (getMask(plane, x - 1, y + 1)
								& (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ | Flags.WALLOBJ_EAST
										| Flags.WALLOBJ_SOUTH | Flags.CORNEROBJ_SOUTHEAST)) == 0
						&& (getMask(plane, x, y + 2) & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ
								| Flags.WALLOBJ_EAST | Flags.WALLOBJ_SOUTH | Flags.WALLOBJ_WEST
								| Flags.CORNEROBJ_SOUTHEAST | Flags.CORNEROBJ_SOUTHWEST)) == 0;
			}
			if (xOffset == 1 && yOffset == 1) {
				return (getMask(plane, x + 1, y + 2) & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ
						| Flags.WALLOBJ_EAST | Flags.WALLOBJ_SOUTH | Flags.WALLOBJ_WEST | Flags.CORNEROBJ_SOUTHEAST
						| Flags.CORNEROBJ_SOUTHWEST)) == 0
						&& (getMask(plane, x + 2, y + 2)
								& (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ | Flags.WALLOBJ_SOUTH
										| Flags.WALLOBJ_WEST | Flags.CORNEROBJ_SOUTHWEST)) == 0
						&& (getMask(plane, x + 1, y + 1) & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK
								| Flags.OBJ | Flags.WALLOBJ_NORTH | Flags.WALLOBJ_SOUTH | Flags.WALLOBJ_WEST
								| Flags.CORNEROBJ_NORTHWEST | Flags.CORNEROBJ_SOUTHWEST)) == 0;
			}
		} else {
			if (xOffset == -1 && yOffset == 0) {
				if ((getMask(plane, x - 1, y) & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ
						| Flags.WALLOBJ_NORTH | Flags.WALLOBJ_EAST | Flags.CORNEROBJ_NORTHEAST)) != 0
						|| (getMask(plane, x - 1, -1 + y + size)
								& (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ | Flags.WALLOBJ_EAST
										| Flags.WALLOBJ_SOUTH | Flags.CORNEROBJ_SOUTHEAST)) != 0) {
					return false;
				}
				for (int sizeOffset = 1; sizeOffset < size - 1; sizeOffset++) {
					if ((getMask(plane, x - 1, y + sizeOffset) & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK
							| Flags.OBJ | Flags.WALLOBJ_NORTH | Flags.WALLOBJ_EAST | Flags.WALLOBJ_SOUTH
							| Flags.CORNEROBJ_NORTHEAST | Flags.CORNEROBJ_SOUTHEAST)) != 0) {
						return false;
					}
				}
			} else if (xOffset == 1 && yOffset == 0) {
				if ((getMask(plane, x + size, y) & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ
						| Flags.WALLOBJ_NORTH | Flags.WALLOBJ_WEST | Flags.CORNEROBJ_NORTHWEST)) != 0
						|| (getMask(plane, x + size, y - (-size + 1))
								& (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ | Flags.WALLOBJ_SOUTH
										| Flags.WALLOBJ_WEST | Flags.CORNEROBJ_SOUTHWEST)) != 0) {
					return false;
				}
				for (int sizeOffset = 1; sizeOffset < size - 1; sizeOffset++) {
					if ((getMask(plane, x + size, y + sizeOffset) & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK
							| Flags.OBJ | Flags.WALLOBJ_NORTH | Flags.WALLOBJ_SOUTH | Flags.WALLOBJ_WEST
							| Flags.CORNEROBJ_NORTHWEST | Flags.CORNEROBJ_SOUTHWEST)) != 0) {
						return false;
					}
				}
			} else if (xOffset == 0 && yOffset == -1) {
				if ((getMask(plane, x, y - 1) & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ
						| Flags.WALLOBJ_NORTH | Flags.WALLOBJ_EAST | Flags.CORNEROBJ_NORTHEAST)) != 0
						|| (getMask(plane, x + size - 1, y - 1)
								& (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ | Flags.WALLOBJ_NORTH
										| Flags.WALLOBJ_WEST | Flags.CORNEROBJ_NORTHWEST)) != 0) {
					return false;
				}
				for (int sizeOffset = 1; sizeOffset < size - 1; sizeOffset++) {
					if ((getMask(plane, x + sizeOffset, y - 1) & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK
							| Flags.OBJ | Flags.WALLOBJ_NORTH | Flags.WALLOBJ_EAST | Flags.WALLOBJ_WEST
							| Flags.CORNEROBJ_NORTHWEST | Flags.CORNEROBJ_NORTHEAST)) != 0) {
						return false;
					}
				}
			} else if (xOffset == 0 && yOffset == 1) {
				if ((getMask(plane, x, y + size) & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ
						| Flags.WALLOBJ_EAST | Flags.WALLOBJ_SOUTH | Flags.CORNEROBJ_SOUTHEAST)) != 0
						|| (getMask(plane, x + size - 1, y + size)
								& (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ | Flags.WALLOBJ_SOUTH
										| Flags.WALLOBJ_WEST | Flags.CORNEROBJ_SOUTHWEST)) != 0) {
					return false;
				}
				for (int sizeOffset = 1; sizeOffset < size - 1; sizeOffset++) {
					if ((getMask(plane, x + sizeOffset, y + size) & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK
							| Flags.OBJ | Flags.WALLOBJ_EAST | Flags.WALLOBJ_SOUTH | Flags.WALLOBJ_WEST
							| Flags.CORNEROBJ_SOUTHEAST | Flags.CORNEROBJ_SOUTHWEST)) != 0) {
						return false;
					}
				}
			} else if (xOffset == -1 && yOffset == -1) {
				if ((getMask(plane, x - 1, y - 1) & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ
						| Flags.WALLOBJ_NORTH | Flags.WALLOBJ_EAST | Flags.CORNEROBJ_NORTHEAST)) != 0) {
					return false;
				}
				for (int sizeOffset = 1; sizeOffset < size; sizeOffset++) {
					if ((getMask(plane, x - 1, y + -1 + sizeOffset) & (Flags.FLOOR_BLOCKSWALK
							| Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ | Flags.WALLOBJ_NORTH | Flags.WALLOBJ_EAST
							| Flags.WALLOBJ_SOUTH | Flags.CORNEROBJ_NORTHEAST | Flags.CORNEROBJ_SOUTHEAST)) != 0
							|| (getMask(plane, sizeOffset - 1 + x, y - 1) & (Flags.FLOOR_BLOCKSWALK
									| Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ | Flags.WALLOBJ_NORTH | Flags.WALLOBJ_EAST
									| Flags.WALLOBJ_WEST | Flags.CORNEROBJ_NORTHWEST | Flags.CORNEROBJ_NORTHEAST)) != 0) {
						return false;
					}
				}
			} else if (xOffset == 1 && yOffset == -1) {
				if ((getMask(plane, x + size, y - 1) & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ
						| Flags.WALLOBJ_NORTH | Flags.WALLOBJ_WEST | Flags.CORNEROBJ_NORTHWEST)) != 0) {
					return false;
				}
				for (int sizeOffset = 1; sizeOffset < size; sizeOffset++) {
					if ((getMask(plane, x + size, sizeOffset + -1 + y) & (Flags.FLOOR_BLOCKSWALK
							| Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ | Flags.WALLOBJ_NORTH | Flags.WALLOBJ_SOUTH
							| Flags.WALLOBJ_WEST | Flags.CORNEROBJ_NORTHWEST | Flags.CORNEROBJ_SOUTHWEST)) != 0
							|| (getMask(plane, x + sizeOffset, y - 1) & (Flags.FLOOR_BLOCKSWALK
									| Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ | Flags.WALLOBJ_NORTH | Flags.WALLOBJ_EAST
									| Flags.WALLOBJ_WEST | Flags.CORNEROBJ_NORTHWEST | Flags.CORNEROBJ_NORTHEAST)) != 0) {
						return false;
					}
				}
			} else if (xOffset == -1 && yOffset == 1) {
				if ((getMask(plane, x - 1, y + size) & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ
						| Flags.WALLOBJ_EAST | Flags.WALLOBJ_SOUTH | Flags.CORNEROBJ_SOUTHEAST)) != 0) {
					return false;
				}
				for (int sizeOffset = 1; sizeOffset < size; sizeOffset++) {
					if ((getMask(plane, x - 1, y + sizeOffset) & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK
							| Flags.OBJ | Flags.WALLOBJ_NORTH | Flags.WALLOBJ_EAST | Flags.WALLOBJ_SOUTH
							| Flags.CORNEROBJ_NORTHEAST | Flags.CORNEROBJ_SOUTHEAST)) != 0
							|| (getMask(plane, -1 + x + sizeOffset, y + size) & (Flags.FLOOR_BLOCKSWALK
									| Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ | Flags.WALLOBJ_EAST | Flags.WALLOBJ_SOUTH
									| Flags.WALLOBJ_WEST | Flags.CORNEROBJ_SOUTHEAST | Flags.CORNEROBJ_SOUTHWEST)) != 0) {
						return false;
					}
				}
			} else if (xOffset == 1 && yOffset == 1) {
				if ((getMask(plane, x + size, y + size) & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK
						| Flags.OBJ | Flags.WALLOBJ_SOUTH | Flags.WALLOBJ_WEST | Flags.CORNEROBJ_SOUTHWEST)) != 0) {
					return false;
				}
				for (int sizeOffset = 1; sizeOffset < size; sizeOffset++) {
					if ((getMask(plane, x + sizeOffset, y + size) & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK
							| Flags.OBJ | Flags.WALLOBJ_EAST | Flags.WALLOBJ_SOUTH | Flags.WALLOBJ_WEST
							| Flags.CORNEROBJ_SOUTHEAST | Flags.CORNEROBJ_SOUTHWEST)) != 0
							|| (getMask(plane, x + size, y + sizeOffset) & (Flags.FLOOR_BLOCKSWALK
									| Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ | Flags.WALLOBJ_NORTH | Flags.WALLOBJ_SOUTH
									| Flags.WALLOBJ_WEST | Flags.CORNEROBJ_NORTHWEST | Flags.CORNEROBJ_SOUTHWEST)) != 0) {
						return false;
					}
				}
			}
		}
		return true;
	}

	public static final boolean isSpawnedObject(WorldObject object) {
		return getRegion(object.getRegionId()).getSpawnedObjects().contains(object);
	}

	public static final WorldObject getObject(WorldTile tile) {
		return getStandardObject(tile);
	}

	public static final WorldObject getStandardObject(WorldTile tile) {
		return getRegion(tile.getRegionId()).getStandartObject(tile.getPlane(), tile.getXInRegion(),
				tile.getYInRegion());
	}

	public static final WorldObject getObject(WorldTile tile, int type) {
		return getObjectWithType(tile, type);
	}

	public static boolean isFloorFree(int plane, int x, int y, int size) {
		for (int tileX = x; tileX < x + size; tileX++) {
			for (int tileY = y; tileY < y + size; tileY++) {
				if (!isFloorFree(plane, tileX, tileY)) {
					return false;
				}
			}
		}
		return true;
	}

	public static boolean isFloorFree(int plane, int x, int y) {
		return (getMask(plane, x, y) & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ)) == 0;
	}

	public static boolean isWallsFree(int plane, int x, int y) {
		return (getMask(plane, x, y) & (Flags.CORNEROBJ_NORTHEAST | Flags.CORNEROBJ_NORTHWEST
				| Flags.CORNEROBJ_SOUTHEAST | Flags.CORNEROBJ_SOUTHWEST | Flags.WALLOBJ_EAST | Flags.WALLOBJ_NORTH
				| Flags.WALLOBJ_SOUTH | Flags.WALLOBJ_WEST)) == 0;
	}

	public static final WorldObject getRemovedOrginalObject(int plane, int x, int y, int type) {
		return getRegion(new WorldTile(x, y, plane).getRegionId()).getRemovedObjectWithSlot(plane, x, y, type);
	}

	public static int getRotation(int plane, int x, int y) {
		WorldTile tile = new WorldTile(x, y, plane);
		int regionId = tile.getRegionId();
		Region region = getRegion(regionId);
		if (region == null || region instanceof DynamicRegion) {
			// in cons
			return 0;
		}
		int baseLocalX = x - (regionId >> 8) * 64;
		int baseLocalY = y - (regionId & 0xff) * 64;
		return region.getRotation(tile.getPlane(), baseLocalX, baseLocalY);
	}

	public static void spawnObject(WorldObject object, boolean clipped) {
		spawnObject(object);

	}

	public static void removeObject(WorldObject object, boolean b) {
		removeObject(object);

	}

	public static boolean isTileFree(int plane, int x, int y, int size) {
		for (int tileX = x; tileX < x + size; tileX++) {
			for (int tileY = y; tileY < y + size; tileY++) {
				if (!isFloorFree(plane, tileX, tileY) || !isWallsFree(plane, tileX, tileY)) {
					return false;
				}
			}
		}
		return true;
	}

	public static final FloorItem addWeaponPiece(Item item, final WorldTile tile) {
		int regionId = tile.getRegionId();
		final FloorItem floorItem = new FloorItem(item, tile, null, false, false);
		final Region region = getRegion(tile.getRegionId());
		region.getGroundItemsSafe().add(floorItem);
		for (Player player : getPlayers()) {
			if (player == null || !player.hasStarted() || player.hasFinished()
					|| !player.getMapRegionsIds().contains(regionId)) {
				continue;
			}
			player.getPackets().sendGroundItem(floorItem);
		}
		return floorItem;
	}




	/*
	 * used for dung
	 */
	public static final boolean removeGroundItem(final FloorItem floorItem) {
		int regionId = floorItem.getTile().getRegionId();
		Region region = getRegion(regionId);
		if (!region.getGroundItemsSafe().contains(floorItem)) {
			return false;
		}
		region.getGroundItemsSafe().remove(floorItem);
		for (Player player : World.getPlayers()) {
			if (player == null || !player.hasStarted() || player.hasFinished()
					|| !player.getMapRegionsIds().contains(regionId)) {
				continue;
			}
			player.getPackets().sendRemoveGroundItem(floorItem);
		}
		return true;
	}

	public static final void addGroundItem2(final Item item, final WorldTile tile) {
		FloorItem floorItem = World.getRegion(tile.getRegionId()).getGroundItem(item.getId(), tile, null);
		if (floorItem == null) {
			floorItem = new FloorItem(item, tile, null, false, false);
			final Region region = getRegion(tile.getRegionId());
			region.forceGetFloorItems().add(floorItem);
			int regionId = tile.getRegionId();
			for (Player player : players) {
				if (player == null || !player.hasStarted() || player.hasFinished() || player.getPlane() != tile.getPlane() || !player.getMapRegionsIds().contains(regionId)) {
					continue;
				}
				player.getPackets().sendGroundItem(floorItem);
			}
		}
	}
	
	
}