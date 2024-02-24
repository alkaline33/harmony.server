
package com.rs;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import com.alex.store.Index;
import com.discord.Discord;
import com.rs.cache.Cache;
import com.rs.cache.loaders.ItemDefinitions;
import com.rs.cache.loaders.ItemsEquipIds;
import com.rs.cache.loaders.NPCDefinitions;
import com.rs.cache.loaders.ObjectDefinitions;
import com.rs.cores.CoresManager;
import com.rs.game.Region;
import com.rs.game.RegionBuilder;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScriptsHandler;
import com.rs.game.player.Player;
import com.rs.game.player.content.BossSpotlight;
import com.rs.game.player.content.EventSpotlight;
import com.rs.game.player.content.FishingSpotsHandler;
import com.rs.game.player.content.FriendChatsManager;
import com.rs.game.player.content.TreasureHunt;
import com.rs.game.player.content.hasClaimedSanta;
import com.rs.game.player.content.clans.ClansManager;
import com.rs.game.player.content.event.season.SeasonEvent;
import com.rs.game.player.content.grandExchange.GrandExchangeLoader;
import com.rs.game.player.content.raids.ClaimedReferral;
import com.rs.game.player.controlers.ControlerHandler;
import com.rs.game.player.cutscenes.CutscenesHandler;
import com.rs.game.player.dialogues.DialogueHandler;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.game.worldlist.WorldList;
import com.rs.net.ServerChannelHandler;
import com.rs.net.decoders.GetRekt;
import com.rs.utils.CalamityBestWave;
import com.rs.utils.Colors;
import com.rs.utils.DTRank;
import com.rs.utils.DisplayNames;
import com.rs.utils.HasVotedOnPoll;
import com.rs.utils.HeistBags;
import com.rs.utils.HeistGames;
import com.rs.utils.IPBanL;
import com.rs.utils.ItemBonuses;
import com.rs.utils.ItemExamines;
import com.rs.utils.Logger;
import com.rs.utils.MapArchiveKeys;
import com.rs.utils.MapAreas;
import com.rs.utils.MusicHints;
import com.rs.utils.NPCBonuses;
import com.rs.utils.NPCCombatDefinitionsL;
import com.rs.utils.NPCDrops;
import com.rs.utils.NPCExamines;
import com.rs.utils.NPCSpawns;
import com.rs.utils.ObjectSpawns;
import com.rs.utils.PkRank;
import com.rs.utils.PrestigeHS;
import com.rs.utils.SerializableFilesManager;
import com.rs.utils.ServerPollNo;
import com.rs.utils.ShopsHandler;
import com.rs.utils.TopTriviaAnswers;
import com.rs.utils.TopVoters;
import com.rs.utils.TotalBossKills;
import com.rs.utils.Utils;
import com.rs.utils.WealthyPlayers;
import com.rs.utils.YoutuberReferral;
import com.rs.utils.YoutuberReferral2;
import com.rs.utils.huffman.Huffman;
import com.rs.utils.statistics.GpSyncTracker;

public final class Launcher {
	// public static Donating md;
	public static void main(String[] arguments) throws Exception {
		long currentTime = Utils.currentTimeMillis();
		Logger.log("Launcher", "Initing Cache...");
		// Discord.startUp();
		GetRekt.init();
		ClaimedReferral.init();
		YoutuberReferral.init();
		YoutuberReferral2.init();
		hasClaimedSanta.init();
		HasVotedOnPoll.init();////
		ServerPollNo.init();
		Logger.log("Launcher", "Loading Mac Bans...");
		Cache.init();
		Huffman.init();
		DisplayNames.init();
		IPBanL.init();
		PkRank.init();
		CalamityBestWave.init();
		TopVoters.init();
		TotalBossKills.init();
		HeistBags.init();
		HeistGames.init();
		WealthyPlayers.init();
		PrestigeHS.init();
		ItemsEquipIds.init();
		DTRank.init();
		GrandExchangeLoader.initialize();
		TopTriviaAnswers.init();
		MapArchiveKeys.init();
		MapAreas.init();
		ObjectSpawns.init();
		NPCSpawns.init();
		NPCCombatDefinitionsL.init();
		NPCBonuses.init();
		NPCExamines.init();
		ClansManager.init();
		NPCDrops.init();
		ItemExamines.init();
		ItemBonuses.init();
		MusicHints.init();
		ShopsHandler.init();
		if (!Settings.DEBUG) {
		Logger.log("Launcher", "Initing Discord...");
		Discord.startUp();
		}
		Logger.log("Launcher", "Initing Fishing Spots...");
		FishingSpotsHandler.init();
		Logger.log("Launcher", "Initing NPC Combat Scripts...");
		CombatScriptsHandler.init();
		Logger.log("Launcher", "Initing Dialogues...");
		DialogueHandler.init();
		Logger.log("Launcher", "Initing Controlers...");
		ControlerHandler.init();
		Logger.log("Launcher", "Initing Cutscenes...");
		CutscenesHandler.init();
		Logger.log("Launcher", "Initing Friend Chats Manager...");
		FriendChatsManager.init();
		Logger.log("Launcher", "Initing Cores Manager...");
		CoresManager.init();
		Logger.log("Launcher", "Initing World...");
		World.init();
		Logger.log("Launcher", "Initing Region Builder...");
		RegionBuilder.init();
		Logger.log("Launcher", "Initing Server Channel Handler...");
		Logger.log("Launcher", "Initing WorldList...");
		/*for (int i = 0; i < 35000; i++) {
			EconomyPrices.getPrice(i);
			if (i == 33405)
				System.out.println("[Launcher] Custom values loaded for " + i + " items.");
		}*/
		// new Motivote<Reward>(new RewardHandler(),
		// "http://drygonscape.co.uk/voting/", "b23a8cdc").start();//you sure
		// this is correct? yh kk
		WorldList.init();
		// BountyHunterManager.init();
		try {
			ServerChannelHandler.init();
		} catch (Throwable e) {
			Logger.handle(e);
			Logger.log("Launcher", "Failed initing Server Channel Handler. Shutting down...");
			System.exit(1);
			return;
		}
		Logger.log("Launcher",
				"Harmony took " + (Utils.currentTimeMillis() - currentTime) + " milli seconds to launch.");
		addAccountsSavingTask();
		//addUpdatePlayersOnlineTask();
		addCleanMemoryTask();
		SecondCounter();
		addGroundItemTask();
		/*
		 * 
		 * bot spawns
		 */
		/*
		 * BotDefinition[] types = BotDefinition.values(); int type = 0; int
		 * usingType = type; final BotDefinition definition = types[usingType];
		 * final Bot bot = new Bot(definition.getName()); final WorldTile tile =
		 * new WorldTile(3037,2978,0); //
		 * Logger.log(Commands.class.getSimpleName(), "Spawning bots");
		 * bot.setDefinition(definition); bot.setSpawnWorldTile(tile);
		 * bot.setNextWorldTile(tile);
		 */
		/*
		 * End of bots
		 */
		// XpSorter.UpdateServerXpSort();
		// addUpdatePlayersOnlineTask();
		// Donations.init();
		//System.out.print
		//System.out.println(Arrays.deepToString(ObjectDefinitions.getObjectDefinitions(-101534820 * 1181652947).models));
	}
	
	private static void addGroundItemTask() {
		CoresManager.slowExecutor.scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {
				try {
					// Blue dragon scale : taverly dungeon
					World.addGroundItem2(new Item(243, 1), new WorldTile(2897, 9802, 0));
					World.addGroundItem2(new Item(243, 1), new WorldTile(2901, 9807, 0));
					World.addGroundItem2(new Item(243, 1), new WorldTile(2907, 9810, 0));
					World.addGroundItem2(new Item(243, 1), new WorldTile(2916, 9806, 0));
					World.addGroundItem2(new Item(243, 1), new WorldTile(2912, 9799, 0));
					World.addGroundItem2(new Item(243, 1), new WorldTile(2902, 9798, 0));
					//snape grass : crafting guild
					World.addGroundItem2(new Item(231, 1), new WorldTile(2911, 3281, 0));
					World.addGroundItem2(new Item(231, 1), new WorldTile(2908, 3288, 0));
					World.addGroundItem2(new Item(231, 1), new WorldTile(2908, 3293, 0));
					World.addGroundItem2(new Item(231, 1), new WorldTile(2905, 3297, 0));
					//red spider eggs
					World.addGroundItem2(new Item(223, 1), new WorldTile(2850, 3165, 0));
					World.addGroundItem2(new Item(223, 1), new WorldTile(3124, 9952, 0));
					World.addGroundItem2(new Item(223, 1), new WorldTile(3119, 9956, 0));
					World.addGroundItem2(new Item(223, 1), new WorldTile(3120, 9960, 0));
					World.addGroundItem2(new Item(223, 1), new WorldTile(3124, 9958, 0));
					World.addGroundItem2(new Item(223, 1), new WorldTile(3127, 9955, 0));
					World.addGroundItem2(new Item(223, 1), new WorldTile(3127, 9950, 0));
				} catch (Throwable e) {
					Logger.handle(e);
				}

			}
		}, 1, 45, TimeUnit.SECONDS);
	}

	private static void setWebsitePlayersOnline(int players, String auth) throws IOException {
		URL url = new URL("http://Hydrix.com/players.php?players=" + players + "&auth=" + auth);
		//System.out.println(""+url+"");
		url.openStream().close();

	}

	private static void addUpdatePlayersOnlineTask() {
		CoresManager.slowExecutor.scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {
				try {
					String auth = "198sbdfg3";
					setWebsitePlayersOnline(World.getPlayers().size1(), auth);
				} catch (Throwable e) {
					Logger.handle(e);
				}
			}
		}, 10, 10, TimeUnit.SECONDS);
	}

	public static void SecondCounter() {
		CoresManager.slowExecutor.scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {
				try {
					Calendar calendar = Calendar.getInstance();
					if (calendar.getTime().getMinutes() == 59
							&& calendar.getTime().getSeconds() == 59) {
						BossSpotlight.GrabBoss();
					}
					if (calendar.getTime().getHours() == 04 && calendar.getTime().getMinutes() == 0 && calendar.getTime().getSeconds() == 0 || calendar.getTime().getHours() == 8 && calendar.getTime().getMinutes() == 0 && calendar.getTime().getSeconds() == 0 || calendar.getTime().getHours() == 12 && calendar.getTime().getMinutes() == 0 && calendar.getTime().getSeconds() == 0 || calendar.getTime().getHours() == 16 && calendar.getTime().getMinutes() == 0 && calendar.getTime().getSeconds() == 0 || calendar.getTime().getHours() == 20 && calendar.getTime().getMinutes() == 0 && calendar.getTime().getSeconds() == 0 || calendar.getTime().getHours() == 23 && calendar.getTime().getMinutes() == 59 && calendar.getTime().getSeconds() == 59) {
						SeasonEvent.GrabEvent();
						EventSpotlight.GrabEvent();
					}
					if (calendar.getTime().getMinutes() == 45 && calendar.getTime().getSeconds() == 0) {
						TreasureHunt.spawnChest();
					}
					if (calendar.getTime().getHours() == 5 && calendar.getTime().getMinutes() == 30 && calendar.getTime().getSeconds() == 0 || calendar.getTime().getHours() == 11 && calendar.getTime().getMinutes() == 30 && calendar.getTime().getSeconds() == 0 || calendar.getTime().getHours() == 17 && calendar.getTime().getMinutes() == 30 && calendar.getTime().getSeconds() == 0 || calendar.getTime().getHours() == 23 && calendar.getTime().getMinutes() == 30 && calendar.getTime().getSeconds() == 0) {
						World.sendWorldMessage(Colors.orange +"<img=7>The Assassin will arrive in 30 minutes.</col>", false);
					}
					if (calendar.getTime().getHours() == 6 && calendar.getTime().getMinutes() == 0 && calendar.getTime().getSeconds() == 0 || calendar.getTime().getHours() == 12 && calendar.getTime().getMinutes() == 0 && calendar.getTime().getSeconds() == 0 || calendar.getTime().getHours() == 18 && calendar.getTime().getMinutes() == 0 && calendar.getTime().getSeconds() == 0 || calendar.getTime().getHours() == 00 && calendar.getTime().getMinutes() == 0 && calendar.getTime().getSeconds() == 0) {
						WorldTile worldTile = new WorldTile(3508, 9837, 0);
						for (NPC n : World.getNPCs()) {
							if (n.getId() != 30149) {
								continue;
							}
							n.sendDeath(n);
						}
						WorldTasksManager.schedule(new WorldTask() {
							int loop;

							@Override
							public void run() {
								if (loop == 6) {
									World.spawnNPC(30149, worldTile, -1, true, true);
								} else if (loop == 7) {
									World.sendWorldMessage(Colors.orange +"<img=7>The Assassin has arrived! Type ::assassin to help kill him!</col>", false);
									Discord.sendEventsMessage(Colors.orange +"<img=7>The Assassin has spawned!");
								} else if (loop == 8) {
									stop();
								}
								loop++;
							}
						}, 0, 1);
					}
					if (calendar.getTime().getHours() == 6 && calendar.getTime().getMinutes() == 30
							&& calendar.getTime().getSeconds() == 0
							|| calendar.getTime().getHours() == 18 && calendar.getTime().getMinutes() == 30
									&& calendar.getTime().getSeconds() == 0) {
						World.sendWorldMessage(Colors.orange +"<img=7>The Party Demon will spawn in 30 minutes.</col>", false);
					}
					if (calendar.getTime().getHours() == 23 && calendar.getTime().getMinutes() == 59 && calendar.getTime().getSeconds() == 59) {
						GpSyncTracker.printGpSync(Settings.GpSyncAmount);
						Settings.GpSyncAmount = 0;
					}
					if (calendar.getTime().getHours() == 7 && calendar.getTime().getMinutes() == 0
							&& calendar.getTime().getSeconds() == 0
							|| calendar.getTime().getHours() == 19 && calendar.getTime().getMinutes() == 0
									&& calendar.getTime().getSeconds() == 0) {
						WorldTile worldTile = new WorldTile(3239, 9867, 0);
						for (NPC n : World.getNPCs()) {
							if (n.getId() != 15581) {
								continue;
							}
							n.sendDeath(n);
						}
						WorldTasksManager.schedule(new WorldTask() {
							int loop;

							@Override
							public void run() {
								if (loop == 6) {
									World.spawnNPC(15581, worldTile, -1, true, true);
									Settings.canteletopdemon = true;
								} else if (loop == 7) {
									World.sendWorldMessage(Colors.orange +"<img=7>The Party Demon has arrived!</col>", false);
									Discord.sendEventsMessage(Colors.orange +"<img=7>Party Demon has spawned!");
								} else if (loop == 8) {
									stop();
								}
								loop++;
							}
						}, 0, 1);
					}
					if (calendar.getTime().getHours() == 23 && calendar.getTime().getMinutes() == 30
							&& calendar.getTime().getSeconds() == 0
							|| calendar.getTime().getHours() == 7 && calendar.getTime().getMinutes() == 30
									&& calendar.getTime().getSeconds() == 0
							|| calendar.getTime().getHours() == 15 && calendar.getTime().getMinutes() == 30
									&& calendar.getTime().getSeconds() == 0) {
						World.sendWorldMessage(Colors.orange +"<img=7>Blink will spawn in 30 minutes.</col>", false);
					}
					if (calendar.getTime().getHours() == 23 && calendar.getTime().getMinutes() == 59
							&& calendar.getTime().getSeconds() == 59
							|| calendar.getTime().getHours() == 8 && calendar.getTime().getMinutes() == 0
									&& calendar.getTime().getSeconds() == 0
							|| calendar.getTime().getHours() == 16 && calendar.getTime().getMinutes() == 0
									&& calendar.getTime().getSeconds() == 0) {
						WorldTile worldTile = new WorldTile(3237, 3649, 0);
						for (NPC n : World.getNPCs()) {
							if (n.getId() != 12878) {
								continue;
							}
							n.sendDeath(n);
						}
						WorldTasksManager.schedule(new WorldTask() {
							int loop;

							@Override
							public void run() {
								if (loop == 6) {
									World.spawnNPC(12878, worldTile, -1, true, true);
								} else if (loop == 7) {
									World.sendWorldMessage(
											Colors.orange +"<img=7>Blink has spawned north of the Chaos altar! Level 16 wildy.</col>",
											false);
									World.sendWorldMessage(Colors.orange +"<img=7>Type ;;blink to join in quickly!</col>", false);
									Discord.sendEventsMessage(Colors.orange +"<img=7>Blink has spawned!");
								} else if (loop == 8) {
									stop();
								}
								loop++;
							}
						}, 0, 1);
					
					}

					Settings.seconds++;
					if (Settings.seconds >= 60) {
						Settings.seconds -= 60;
						Settings.minutes += 1;
					}
					if (Settings.minutes >= 60) {
						Settings.minutes -= 60;
						Settings.hours += 1;
					}
					if (Settings.hours >= 24) {
						Settings.hours -= 24;
						Settings.days += 1;
						for (Player player : World.getPlayers()) {
							player.authclaimed = 0;
						}
					}
				} catch (Throwable e) {
					Logger.handle(e);
				}

			}
		}, 1, 1, TimeUnit.SECONDS);
	}

	private static void addCleanMemoryTask() {
		CoresManager.slowExecutor.scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {
				try {
					cleanMemory(Runtime.getRuntime().freeMemory() < Settings.MIN_FREE_MEM_ALLOWED);
				} catch (Throwable e) {
					Logger.handle(e);
				}
			}
		}, 0, 10, TimeUnit.MINUTES);
	}

	private static void addAccountsSavingTask() {
		CoresManager.slowExecutor.scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {
				try {
					saveFiles();
				} catch (Throwable e) {
					Logger.handle(e);
				}

			}
		}, 3, 3, TimeUnit.SECONDS);
	}

	public static void saveFiles() {
		for (Player player : World.getPlayers()) {
			if (player == null || !player.hasStarted() || player.hasFinished()) {
				continue;
			}
			SerializableFilesManager.savePlayer(player);
		}
		DisplayNames.save();
		IPBanL.save();
		PkRank.save();
		TopVoters.save();
		TopTriviaAnswers.save();
		TotalBossKills.save();
		CalamityBestWave.save();
		HeistBags.save();
		HeistGames.save();
		WealthyPlayers.save();
		PrestigeHS.save();
		// MaxPlayersOnline.save();
		DTRank.save();
	}

	public static void cleanMemory(boolean force) {
		if (force) {
			ItemDefinitions.clearItemsDefinitions();
			// ItemDefinitions.
			NPCDefinitions.clearNPCDefinitions();
			ObjectDefinitions.clearObjectDefinitions();
			for (Region region : World.getRegions().values()) {
				// region.removeMapFromMemory();
				region.unloadMap();
			}
		}
		for (Index index : Cache.STORE.getIndexes()) {
			index.resetCachedFiles();
		}
		CoresManager.fastExecutor.purge();
		System.gc();
	}

	public static void shutdown() {
		try {
			closeServices();
		} finally {
			System.exit(0);
		}
	}

	public static void closeServices() {
		ServerChannelHandler.shutdown();
		CoresManager.shutdown();
		if (Settings.HOSTED) {
			try {
				// setWebsitePlayersOnline(0, null);
			} catch (Throwable e) {
				Logger.handle(e);
			}
		}
	}

	public static void restart() {
		closeServices();
		System.gc();
		try {
			Runtime.getRuntime().exec(
					"java -server -Xms2048m -Xmx20000m -cp bin;/data/libs/netty-3.2.7.Final.jar;/data/libs/FileStore.jar Launcher false false true false");
			System.exit(0);
		} catch (Throwable e) {
			Logger.handle(e);
		}

	}

	private Launcher() {

	}

}
