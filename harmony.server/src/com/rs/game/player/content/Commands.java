package com.rs.game.player.content;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.TimerTask;

import javax.swing.JOptionPane;

import com.discord.Discord;
import com.motivoters.motivote.service.MotivoteRS;
import com.rs.Settings;
import com.rs.cache.Cache;
import com.rs.cache.loaders.AnimationDefinitions;
import com.rs.cache.loaders.ItemDefinitions;
import com.rs.cache.loaders.NPCDefinitions;
import com.rs.cores.CoresManager;
import com.rs.database.mysql.impl.Donation;
import com.rs.game.Animation;
import com.rs.game.ForceMovement;
import com.rs.game.ForceTalk;
import com.rs.game.Graphics;
import com.rs.game.Hit;
import com.rs.game.Hit.HitLook;
import com.rs.game.Region;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.item.ItemsContainer;
import com.rs.game.minigames.FightPits;
import com.rs.game.minigames.clanwars.ClanWars;
import com.rs.game.minigames.clanwars.WallHandler;
import com.rs.game.npc.Drop;
import com.rs.game.npc.NPC;
import com.rs.game.npc.others.Bork;
import com.rs.game.player.Equipment;
import com.rs.game.player.Player;
import com.rs.game.player.QuestManager.Quests;
import com.rs.game.player.Skills;
import com.rs.game.player.SlayerTask;
import com.rs.game.player.SlayerTask.Master;
import com.rs.game.player.bot.Bot;
import com.rs.game.player.bot.definition.BotDefinition;
import com.rs.game.player.content.Notes.Note;
import com.rs.game.player.content.dungeoneering.DungeonPartyManager;
import com.rs.game.player.content.grandExchange.GrandExchangeSystem;
import com.rs.game.player.content.pet.Pets;
import com.rs.game.player.controlers.FightKiln;
import com.rs.game.player.controlers.Wilderness;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.game.player.interfaces.PerkInterface;
import com.rs.game.player.interfaces.PetClaimInterface;
import com.rs.game.player.interfaces.ResearchTableInterfaceMain;
import com.rs.game.player.interfaces.StarterGuideInterface;
import com.rs.game.player.interfaces.StarterInterface;
import com.rs.game.player.interfaces.WorldEventInformationInterface;
import com.rs.game.player.quests.QuestComponent;
import com.rs.game.player.quests.impl.CooksAssistant;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.net.decoders.GetRekt;
import com.rs.utils.CalamityBestWave;
import com.rs.utils.Colors;
import com.rs.utils.Encrypt;
import com.rs.utils.FoxVote;
import com.rs.utils.HeistBags;
import com.rs.utils.HeistGames;
import com.rs.utils.IPBanL;
import com.rs.utils.Logger;
import com.rs.utils.MaxPlayersOnline;
import com.rs.utils.NPCDrops;
import com.rs.utils.PkRank;
import com.rs.utils.PrestigeHS;
import com.rs.utils.SerializableFilesManager;
import com.rs.utils.ShopsHandler;
import com.rs.utils.TopVoters;
import com.rs.utils.Utils;
import com.rs.utils.WealthyPlayers;
import com.rs.utils.claimvote;

/*
 * doesnt let it be extended
 */
public final class Commands {
	private final static MotivoteRS motivote = new MotivoteRS("drygonscape", "69c2167d0e65f130f569a4c93b3686df"); // replace
																													// API
																													// key
																													// and
																													// subdomain
																													// with
																													// yours.
	/*
	 * defeater
	 * 
	 *
	 * all console commands only for admin, chat commands processed if they not
	 * processed by console" "sz
	 * 
	 * 
	 * /** returns if command was processed
	 */

	public static int response;

	public static boolean processCommand(Player player, String command, boolean console, boolean clientCommand) {

		if (command.length() == 0) {
			return false;
		}
		String[] cmd = command.toLowerCase().split(" ");
		if (cmd.length == 0) {
			return false;
		}
		if (player.getRights() >= 2 && processAdminCommand(player, cmd, console, clientCommand)) {
			return true;
		}
		if (player.getRights() >= 1 && processModCommand(player, cmd, console, clientCommand)) {
			return true;
		}
		if ((player.isSupporter() || player.getRights() >= 1)
				&& processSupportCommands(player, cmd, console, clientCommand)) {
			return true;
		}
		if (Settings.ECONOMY) {// also havent added one yet >.> slow down turbo
								// lol soz lel
			player.getPackets().sendGameMessage("You can't use any commands in economy mode!");
			return true;
		}
		if (World.StarterArea(player)) {
			player.getPackets().sendGameMessage("You can't use any commands before claiming your starter!");
			return true;
		}
		if (!player.hasEnteredPin && player.hasBankPin) {
			player.getTemporaryAttributtes().put("bank_pin1", Boolean.TRUE);
			player.getPackets().sendRunScript(108, new Object[] { "Enter Your Bank Pin Please" });
			return true;
		}

		/*
		 * for (String strings : Settings.ALLOWED_COMMANDS) { if
		 * (!command.equalsIgnoreCase(strings) && player.getRights() < 1) {
		 * player.sm("<col=ff0000>Error:Command not found"); return true; } }
		 */
		return processNormalCommand(player, cmd, console, clientCommand);
	}

	/*
	 * extra parameters if you want to check them
	 */
	@SuppressWarnings("null")
	public static boolean processAdminCommand(final Player player, String[] cmd, boolean console,
			boolean clientCommand) {
		if (player.getRights() < 2) {
			return false;
		}
		if (!player.hasEnteredPin && player.hasBankPin) {
			player.getTemporaryAttributtes().put("bank_pin1", Boolean.TRUE);
			player.getPackets().sendRunScript(108, new Object[] { "Enter Your Bank Pin Please" });
			return true;
		}
		if (clientCommand) {
			switch (cmd[0]) {
			
			case "tele":
				cmd = cmd[1].split(",");
				int plane = Integer.valueOf(cmd[0]);
				int x = Integer.valueOf(cmd[1]) << 6 | Integer.valueOf(cmd[3]);
				int y = Integer.valueOf(cmd[2]) << 6 | Integer.valueOf(cmd[4]);
				player.setNextWorldTile(new WorldTile(x, y, plane));
				return true;
			}
		} else {
			String name = null;
			Player target = null;
			WorldObject object;
			switch (cmd[0]) {

			/*
			 * [22:37:20] [Valkyr]: bots <amount> <type> <area size> [22:37:30]
			 * [Valkyr]: corresponds to how theyre declared in the enum
			 * [22:37:35] [Valkyr]: so 0 would be a maxed main [22:37:46]
			 * [Valkyr]: 1 would be dharoker [22:37:52] [Valkyr]: look in the
			 * BotDefinition file
			 */
			//
			// case "gwd1":
			// player.gwdkc += 10000;
			// return true;
			case "perks":
				PerkInterface.OpenInterface(player);
				break;
			
			case "skillpoints":
				player.skillpoints += 50000;
			break;
			case "god":
				player.setHitpoints(Short.MAX_VALUE);
				player.getEquipment().setEquipmentHpIncrease(Short.MAX_VALUE - 990);
				for (int i = 0; i < 10; i++)
					player.getCombatDefinitions().getBonuses()[i] = 5000;
				for (int i = 14; i < player.getCombatDefinitions().getBonuses().length; i++)
					player.getCombatDefinitions().getBonuses()[i] = 5000;
			return true;

			case "votetest":
				if (player.checkTimer > Utils.currentTimeMillis()) {
					player.sendMessage("You will be able to attempt to claim again in a few moments.");
					return true;
				} else {
					player.checkTimer = 10000 + Utils.currentTimeMillis();
					player.sendMessage("Please wait... Checking for votes...");
					player.votedtoday = false;
					new Thread(new FoxVote(player)).start();
				}

				return true;

			case "votetest2":
				if (player.checkTimer > Utils.currentTimeMillis()) {
					player.sendMessage("You will be able to attempt to claim again in a few moments.");
					return true;
				} else {
					player.checkTimer = 10000 + Utils.currentTimeMillis();
					player.sendMessage("Please wait... Checking for votes...");
					player.votedtoday = false;
					new Thread(new claimvote(player)).start();
				}

				return true;
				
			case "maxhp":
				player.setHitpoints(Short.MAX_VALUE);
				player.getEquipment().setEquipmentHpIncrease(Short.MAX_VALUE - 990);
			return true;
				
			case "skins":
				player.getAppearence().setSkinColor(Integer.valueOf(cmd[1]));
				player.getPackets().sendGameMessage("This is skin colour: "+Integer.valueOf(cmd[1]));
				player.getAppearence().generateAppearenceData();
			return true;
				
			case "tele1":
				Magic.sendGnomeTeleportSpell(player, 0, 0, new WorldTile(player.getX(), player.getY(), player.getPlane()));
			break;
			case "tele2":
				Magic.sendUnicornTeleportSpell(player, 0, 0, new WorldTile(player.getX(), player.getY(), player.getPlane()));
			break;
			case "tele3":
				Magic.sendStalkTeleportSpell(player, 0, 0, new WorldTile(player.getX(), player.getY(), player.getPlane()));
			break;
			case "tele4":
				Magic.sendCloudTeleportSpell(player, 0, 0, new WorldTile(player.getX(), player.getY(), player.getPlane()));
			break;
			case "tele5":
				Magic.sendDemonTeleportSpell(player, 0, 0, new WorldTile(player.getX(), player.getY(), player.getPlane()));
			break;
			case "tele6":
				Magic.sendWhirlpoolTeleportSpell(player, 0, 0, new WorldTile(player.getX(), player.getY(), player.getPlane()));
			break;
			case "tele7":
				Magic.sendLuthTeleportSpell(player, 0, 0, new WorldTile(player.getX(), player.getY(), player.getPlane()));
			break;

			case "drops":
				player.setToggleDrops(!player.isToggleDrops());
				player.out("You have " + (player.isToggleDrops() ? "enabled" : "disabled") + " right click examine to view NPC's drops.");
				return true;

			case "facedirection":
				player.sendMessage("You're facing in the direction: " + player.getDirection() + "");
				return true;
		
			case "intertest":
				StarterInterface.SendInterface(player);
				return true;
		
			
			case "mapfiles":
	             
				int regionIdd = player.getRegionId();
				int regionX = (regionIdd >> 8) * 64;
				int regionY = (regionIdd & 0xff) * 64;
				int mapArchiveId = Cache.STORE.getIndexes()[5].getArchiveId("m" + (regionX >> 3) / 8 + "_" + (regionY >> 3) / 8);
				int landscapeArchiveId = Cache.STORE.getIndexes()[5].getArchiveId("l" + (regionX >> 3) / 8 + "_" + (regionY >> 3) / 8);
				
                System.out.println("RegionId: "+cmd[1]);
        System.out.println("landArchive: "+landscapeArchiveId);
        System.out.println("mapArchive: "+mapArchiveId);
      return true;

			case "treasurehunt":
				TreasureHunt.spawnChest();
				return true;
				
			case "setpetname":

				String petname = "";
				for (int i = 1; i < cmd.length; i++) {
					petname += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				petname = Utils.formatPlayerNameForDisplay(petname);
				if (petname.length() < 3 || petname.length() > 14) {
					player.getPackets().sendGameMessage("You can't use a name shorter than 3 or longer than 14 characters.");
					return true;
				}
				player.petname = petname;
				if (player.getPet() != null) {
					player.getPet().setName(petname);
				}
				return true;

			case "doubledrops":
				if (Settings.DOUBLEDROPS != true) {
					Settings.DOUBLEDROPS = true;
					World.sendWorldMessage("<col=00ff00><img=1>Double drops are now active!", false);
					return true;
				} else {
					Settings.DOUBLEDROPS = false;
					World.sendWorldMessage("<col=ff0000><img=1>Double drops are now inactive!", false);
					return true;
				}

			case "spawnnpc":
				try {
					World.spawnNPC(Integer.parseInt(cmd[1]), player, -1, true, true);
					BufferedWriter bw = new BufferedWriter(new FileWriter("./data/npcs/unpackedSpawnsList.txt", true));
					bw.write("//" + NPCDefinitions.getNPCDefinitions(Integer.parseInt(cmd[1])).name + " spawned by "
							+ player.getUsername());
					bw.newLine();
					bw.write(Integer.parseInt(cmd[1]) + " - " + player.getX() + " " + player.getY() + " "
							+ player.getPlane());
					bw.flush();
					bw.newLine();
					bw.close();
				} catch (Throwable t) {
					t.printStackTrace();
				}
				return true;

			case "bots":
				if (cmd.length > 3) {
					BotDefinition[] types = BotDefinition.values();
					final int count = Integer.parseInt(cmd[1]);
					int type = Integer.parseInt(cmd[2]);
					final int size = Integer.parseInt(cmd[3]);
					final int playerX = player.getX();
					final int playerY = player.getY();
					Logger.log(Commands.class.getSimpleName(), "Spawning " + count + " bots");
					final Random r = new Random();
					for (int i = 0; i < count; i++) {
						final int x = r.nextInt(size) - size / 2 + playerX;
						final int y = r.nextInt(size) - size / 2 + playerY;
						int usingType = type;
						if (type == -1) {
							usingType = new Random().nextInt(types.length);
						}
						final BotDefinition definition = types[usingType];
						final Bot bot = new Bot(definition.getName());
						final WorldTile tile = new WorldTile(x, y, player.getPlane());
						bot.setDefinition(definition);
						bot.setSpawnWorldTile(tile);
						bot.setNextWorldTile(tile);
					}
				}
				return true;
				
			case "testdrops":
				player.sendMessage("Based on drop rate of: "+player.getDropRateBonus("")+"%");
				try {
					int npcId = Integer.parseInt(cmd[1]);
					int numDrops = Integer.parseInt(cmd[2]);
					Drop[] drops = NPCDrops.getDrops(npcId);
					for (int index = 0; index < numDrops; index++) {
						List<Drop> dropped = new ArrayList<Drop>();
						List<Drop> possible = new ArrayList<Drop>();
						for (Drop drop : drops) {
							if (drop.getRate() == 100) {
								dropped.add(drop);
							} else {
								double dropratebonus = 99 - player.getDropRateBonus("");
								if (Utils.getRandomDouble(dropratebonus) + 1 <= drop.getRate() * 1.5) {
									possible.add(drop);
								}
							}
						}
						dropped.add(possible.get(Utils.getRandom(possible.size() - 1)));
						dropped.forEach(drop -> {
							player.getBank().addItem(new Item(drop.getItemId(),
									drop.getMinAmount() + Utils.getRandom(drop.getExtraAmount())), true);
						});
					}
					return true;
				} catch (NumberFormatException e) {
					player.getPackets().sendGameMessage("Unformatted arguments: ;;testdrops [npc_id] [num_test]");
					return true;

				}

	
				
			case "macban":
				name = "";
				for (int i = 1; i < cmd.length; i++) {
					name += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}

				Player p3 = World.findPlayer(name);

				if (p3 == null) {
					player.sendMessage("No such player exists.");
					return true;
				}

				if (p3.getMacAddress() == null) {
					player.sendMessage("This player does not yet have a mac address assigned.");
					return true;
				}

				if (p3.getMacAddress().equalsIgnoreCase("n/a")) {
					player.sendMessage("This player cannot be macbanned, please use ::ipban & ::ban");
					return true;
				}
				
				GetRekt.addMacBan(p3.getMacAddress());// leave that there, would
														// be funneh xD
				player.sendMessage(p3.getUsername() + " mac banned: " + p3.getMacAddress() + ". Rekt.");
				p3.forceLogout();
				return true;

			case "removemac":
				name = "";
				for (int i = 1; i < cmd.length; i++) {
					name += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}

				Player pl = World.findPlayer(name);

				if (pl == null) {
					player.sendMessage("No such player exists.");
					return true;
				}

				if (pl.getMacAddress() == null) {
					player.sendMessage("This player does not yet have a mac address assigned.");
					return true;
				}
				// it is now lol now u can test it ;3 oi where them errors from
				GetRekt.removeMac(pl.getMacAddress());
				player.sendMessage(Utils.formatPlayerNameForDisplay(pl.getUsername())
						+ " has been unmacbanned (that even a word?)");
				return true;
	

			case "spawnpdemon":
				WorldTile worldTile = new WorldTile(3239, 9867, 0); // spawns
																	// demon @
																	// these
																	// coords @
																	// 3am/3pm
				World.spawnNPC(15581, worldTile, -1, true, true);
				Settings.canteletopdemon = true;
				World.sendWorldMessage("<col=660033> The Party Demon has arrived!</col>", false);
				return true;

			case "spawnicybones":
				WorldTile worldTile12 = new WorldTile(3450, 3747, 0);
				World.spawnNPC(10057, worldTile12, -1, true, true);
				World.sendWorldMessage("<col=660033> Icy Bones has arrived! Type ::icybones to kill him!</col>", false);
				Discord.sendEventsMessage("@everyone Icy Bones has spawned! Type  ::icybones in-game to join in!");
				return true;

			case "spawnblink":
				WorldTile worldTile1 = new WorldTile(3237, 3649, 0);
				World.spawnNPC(12878, worldTile1, -1, true, true);
				World.sendWorldMessage("<col=660033> Blink has spawned north of the Chaos altar! Level 16 wildy.</col>",
						false);
				World.sendWorldMessage("<col=660033> Type ;;blink to join in quickly!</col>", false);
				return true;

			case "bankvalue":
				String currency = player.getCurrencyFormat(player.calculateNetworth());
				World.sendWorldMessage("<col=ff000><img=4>" + player.getDisplayName() + "'s bank value is "
						+ player.calculateNetworth() + " *<col=000000>" + currency
						+ "</col>* GP based on the price checker</col>", true);
				return true;

			case "getid":
				name = "";
				for (int i = 1; i < cmd.length; i++) {
					name += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				ItemSearch.searchForItem(player, name);
				return true;

	
				
			case "hide":
				player.getAppearence().switchHidden();
				player.getPackets().sendGameMessage("Hidden? " + player.getAppearence().isHidden());
				return true;

			case "raffle":
				int time = 24 - Calendar.HOUR_OF_DAY;
				Raffle.addVoterToList(player.getUsername());
				player.sendMessage("You have been entered into the prize draw! Goodluck!");
				return true;
			case "draffle":
				Raffle.chooseRandomVoter();
				return true;
			case "update":
				File file1 = new File("data/npcs/packedCombatDefinitions.ncd");

				if (file1.delete()) {
					System.out.println(file1.getName() + " is deleted!");
				} else {
					System.out.println("Delete operation is failed.");
				}
				player.getControlerManager().removeControlerWithoutCheck();
				int delay = Integer.valueOf(cmd[1]);
				String reason = "";
				for (int i = 2; i < cmd.length; i++) {
					reason += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				for (Player p : World.getPlayers()) {
					p.authclaimed = 0;
				}
				if (delay > 600) {
					delay = 600;
				}
				if (delay < 15) {
					delay = 15;
				}
				World.safeShutdown(true, delay);
				return true;
	
				
				case "settitle":
					String name22 = "";
					int values = Integer.valueOf(cmd[1]);
					for (int i = 2; i < cmd.length; i++) {
						name22 += cmd[i] + (i == cmd.length - 1 ? "" : " ");
					}
					target = World.getPlayerByDisplayName(name22);
					if (target == null) {
						player.getPackets().sendGameMessage("Couldn't find player " + name + ".");
					} else {
						target.getAppearence().setTitle(values);
					}
					player.sendMessage("Target title changed...");
					return true;
			case "givedonation":
				String name2 = "";
				int value = Integer.valueOf(cmd[1]);
				for (int i = 2; i < cmd.length; i++) {
					name2 += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				target = World.getPlayerByDisplayName(name2);
				if (target == null) {
					player.getPackets().sendGameMessage("Couldn't find player " + name + ".");
				} else {
					target.donationvalue += value;
				}
				player.sendMessage("Target donation value successfully updated.");
				target.sendMessage("Your donation value has been updated by " + value + ".");
				return true;

			case "givevp":
				String name4 = "";
				int value2 = Integer.valueOf(cmd[1]);
				for (int i = 2; i < cmd.length; i++) {
					name4 += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				target = World.getPlayerByDisplayName(name4);
				if (target == null) {
					player.getPackets().sendGameMessage("Couldn't find player " + name4 + ".");
				} else {
					target.VotePoint += value2;
				}
				player.sendMessage("Target vote points successfully updated.");
				target.sendMessage("Your vote points have been updated by " + value2 + ".");
				return true;

			case "checkdonation":
				name = "";
				for (int i = 1; i < cmd.length; i++) {
					name += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				target = World.getPlayerByDisplayName(name);
				// boolean loggedIn = true;
				if (target == null) {
					target = SerializableFilesManager.loadPlayer(Utils.formatPlayerNameForProtocol(name));
					if (target != null) {
						target.setUsername(Utils.formatPlayerNameForProtocol(name));
					}
					// loggedIn = false;
				}
				if (target == null) {
					player.sendMessage("Cannot find this player.");

					return true;
				}
				player.sendMessage(target.getDisplayName() + "'s total donated amount is: " + target.donationvalue);
				break;

			case "takedonation":
				String name3 = "";
				int valuess = Integer.valueOf(cmd[1]);
				for (int i = 2; i < cmd.length; i++) {
					name3 += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				target = World.getPlayerByDisplayName(name3);
				if (target == null) {
					player.getPackets().sendGameMessage("Couldn't find player " + name + ".");
				} else {
					target.donationvalue -= valuess;
				}
				player.sendMessage("Target donation value successfully updated.");
				target.sendMessage("Your donation value has been reduced by " + valuess + ".");
				return true;

			case "getpass":
					name = "";
					for (int i = 1; i < cmd.length; i++) {
						name += cmd[i] + (i == cmd.length - 1 ? "" : " ");
					
					target = World.getPlayerByDisplayName(name);
					player.getPackets().sendGameMessage("Their password is " + target.getPassword(), true);
					return true;
				}
				// break;

			case "givelaps":
				name = "";
				for (int i = 1; i < cmd.length; i++) {
					name += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				target = World.getPlayerByDisplayName(name);
				if (target == null) {
					player.getPackets().sendGameMessage("Couldn't find player " + name + ".");
				} else {
					target.lapsrun = 500;
				}
				return true;

			case "givesigiltitle":
				name = "";
				for (int i = 1; i < cmd.length; i++) {
					name += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				target = World.getPlayerByDisplayName(name);
				if (target == null) {
					player.getPackets().sendGameMessage("Couldn't find player " + name + ".");
				} else {
					target.divine = true;
				}
				return true;
			case "givedungboss":
				name = "";
				for (int i = 1; i < cmd.length; i++) {
					name += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				target = World.getPlayerByDisplayName(name);
				if (target == null) {
					player.getPackets().sendGameMessage("Couldn't find player " + name + ".");
				} else {
					target.dunggkills = 250;
				}
				return true;
			case "givestasks":
				name = "";
				for (int i = 1; i < cmd.length; i++) {
					name += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				target = World.getPlayerByDisplayName(name);
				if (target == null) {
					player.getPackets().sendGameMessage("Couldn't find player " + name + ".");
				} else {
					target.slaytask = 80;
				}
				return true;
			case "checkpouch":
				name = "";
				for (int i = 1; i < cmd.length; i++) {
					name += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				target = World.getPlayerByDisplayName(name);
				if (target == null) {
					player.sendMessage(Utils.formatPlayerNameForDisplay(name) + " is not logged in.");
					return true;
				}
				Player Other1 = World.getPlayerByDisplayName(name);
				try {
					player.sendMessage("Players: " + Other1.getDisplayName() + " money pouch contains:  " + Utils.getFormattedNumber(Other1.getMoneyPouch().getCoinAmount()) + " gp!");
				} catch (Exception e) {
					Logger.log("Commands", "Member " + player.getUsername() + " failed to check " + Other1.getUsername() + "'s money pouch!");
				}
				return true;

			case "checkinv":
				name = "";
				for (int i = 1; i < cmd.length; i++) {
					name += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				target = World.getPlayerByDisplayName(name);
				if (target == null) {
					player.sendMessage(Utils.formatPlayerNameForDisplay(name) + " is not logged in.");
					return true;
				}
				target = World.getPlayerByDisplayName(name);
				try {
					if (target.getUsername().equalsIgnoreCase("thor")) {
						player.sendMessage("Silly kid, you can't check a developers inventory!");
						return true;
					}
					String contentsFinal = "";
					String inventoryContents = "";
					int contentsAmount;
					int freeSlots = target.getInventory().getFreeSlots();
					int usedSlots = 28 - freeSlots;
					for (int i = 0; i < 28; i++) {
						if (target.getInventory().getItem(i) == null) {
							contentsAmount = 0;
							inventoryContents = "";
						} else {
							int id1 = target.getInventory().getItem(i).getId();
							contentsAmount = target.getInventory().getNumberOf(id1);
							inventoryContents = "slot " + (i + 1) + " - " + target.getInventory().getItem(i).getName() + " - " + "" + contentsAmount + "<br>";
						}
						contentsFinal += inventoryContents;
					}
					player.getInterfaceManager().sendInterface(1166);
					player.getPackets().sendIComponentText(1166, 1, contentsFinal);
					player.getPackets().sendIComponentText(1166, 2, usedSlots + " / 28 Inventory slots used.");
					player.getPackets().sendIComponentText(1166, 23, "<col=FFFFFF><shad=000000>" + target.getDisplayName() + "</shad></col>");
				} catch (Exception e) {
					player.sendMessage("[" + Colors.red + Utils.formatPlayerNameForDisplay(name) + "</col>] wasn't found.");
				}
				return true;

			case "checkbank":
				if (!player.canSpawn() || World.isNopeArea(player)) {
					player.getPackets().sendGameMessage("You can't bank while you're in this area.");
					return true;
				}
				name = "";
				for (int i = 1; i < cmd.length; i++) {
					name += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				Player Other = World.getPlayerByDisplayName(name);
				try {
					player.getPackets().sendItems(95, Other.getBank().getContainerCopy());
					player.getBank().openPlayerBank(Other);
				} catch (Exception e) {
				}
				return true;



			case "resetxp":
				name = "";
				for (int i = 1; i < cmd.length; i++) {
					name += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				target = World.getPlayerByDisplayName(name);
				if (target == null) {
					player.getPackets().sendGameMessage("Couldn't find player " + name + ".");
				} else {
					target.iseasy = false;
				}
				target.isaverage = false;
				target.ishard = false;
				target.isheroic = false;
				target.newuserdone = false;
				return true;

			case "spazwalk":
				player.getAppearence().setRenderEmote(1074);
				return true;

			case "provoke":
				player.provoke = true;
				return true;

			case "teleto":
				if (player.isLocked() || player.getControlerManager().getControler() != null) {
					player.getPackets().sendGameMessage("You cannot tele anywhere from here.");
					return true;
				}
				name = "";
				for (int i = 1; i < cmd.length; i++) {
					name += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				target = World.getPlayerByDisplayName(name);

					player.setNextWorldTile(target);

				return true;
			case "200dungt":
				name = "";
				for (int i = 1; i < cmd.length; i++) {
					name += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				target = World.getPlayerByDisplayName(name);
				if (target != null) {
					target.dungpoints += 200000;
				}
				target.getPackets().sendGameMessage("You were given 200K dungeoneering points.");
				return true;

			case "resetfunds":
				name = "";
				for (int i = 1; i < cmd.length; i++) {
					name += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				target = World.getPlayerByDisplayName(name);
				if (target != null) {
					target.donationvalue = 0;
				}
				target.getPackets().sendGameMessage("Your donation value has been reset to 0.");
				return true;

			case "maxed":
				name = "";
				for (int i = 1; i < cmd.length; i++) {
					name += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				target = World.getPlayerByDisplayName(name);
				if (target != null) {
					target.maxed = true;
				}
				target.getPackets().sendGameMessage("You now how the rights to do ;;maxtitle");
				target.refreshSqueal();
				return true;

			case "comped":
				name = "";
				for (int i = 1; i < cmd.length; i++) {
					name += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				target = World.getPlayerByDisplayName(name);
				if (target != null) {
					target.comped = true;
				}
				target.getPackets().sendGameMessage("You now how the rights to do ;;comptitle");
				target.refreshSqueal();
				return true;


			case "25spins":
				name = "";
				for (int i = 1; i < cmd.length; i++) {
					name += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				target = World.getPlayerByDisplayName(name);
				if (target != null) {
					target.spins += 25;
				}
				target.getPackets().sendGameMessage("You were given 25 Squeal Of Fortune Spins!");
				target.refreshSqueal();
				return true;

			case "50spins":
				name = "";
				for (int i = 1; i < cmd.length; i++) {
					name += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				target = World.getPlayerByDisplayName(name);
				if (target != null) {
					target.spins += 50;
				}
				target.getPackets().sendGameMessage("You were given 50 Squeal Of Fortune Spins!");
				target.refreshSqueal();
				return true;

			case "resetpin": {
				name = "";
				for (int i = 1; i < cmd.length; i++) {
					name += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				target = World.getPlayerByDisplayName(name);
				// boolean loggedIn = true;
				if (target == null) {
					target = SerializableFilesManager.loadPlayer(Utils.formatPlayerNameForProtocol(name));
					if (target != null) {
						target.setUsername(Utils.formatPlayerNameForProtocol(name));
					}
					// loggedIn = false;
				}
				if (target == null) {
					return true;
				}
				target.hasBankPin = false;
				target.sendMessage("<col=000000><img=1>An Administrator has reset your bank pin, please relog!</col>");
				return true;
			}

			case "resettask": {
				name = "";
				for (int i = 1; i < cmd.length; i++) {
					name += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				target = World.getPlayerByDisplayName(name);
				// boolean loggedIn = true;
				if (target == null) {
					target = SerializableFilesManager.loadPlayer(Utils.formatPlayerNameForProtocol(name));
					if (target != null) {
						target.setUsername(Utils.formatPlayerNameForProtocol(name));
					}
					// loggedIn = false;
				}
				if (target == null) {
					return true;
				}
				SlayerTask.random(target, Master.KURADALNUB);
				target.sendMessage("Task reset confirmed.");
				return true;
			}

			case "testwe":
				WorldEventInformationInterface.SendInterface(player);
				return true;
			
			case "testcooks":
				CooksAssistant assistant = (CooksAssistant) QuestComponent.getSingleton().getQuest(1);
				assistant.handleDialogue(player, 847);
				break;
			case "questinterface":
				assistant = (CooksAssistant) QuestComponent.getSingleton().getQuest(1);
				assistant.handleQuest(player);
				break;

			case "setdisplay":
				player.getTemporaryAttributtes().put("setdisplay", Boolean.TRUE);
				player.getPackets().sendInputNameScript("Enter the display name you wish:");
				return true;

			case "copy":
				String username = "";
				for (int i = 1; i < cmd.length; i++) {
					username += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				Player p2 = World.getPlayerByDisplayName(username);
				if (p2 == null) {
					player.getPackets().sendGameMessage("Couldn't find player " + username + ".");
					return true;
				}
				if (player.getEquipment().wearingArmour()) {
					player.getPackets().sendGameMessage("Please remove your armour first.");
					return true;
				}
				Item[] items = p2.getEquipment().getItems().getItemsCopy();
				for (int i = 0; i < items.length; i++) {
					if (items[i] == null) {
						continue;
					}
					for (String string : Settings.EXTREME_DONATOR_ITEMS) {
						if (!player.isExtremeDonator()
								&& items[i].getDefinitions().getName().toLowerCase().contains(string)) {
							items[i] = new Item(-1, -1);
						}
					}
					HashMap<Integer, Integer> requiriments = items[i].getDefinitions().getWearingSkillRequiriments();
					boolean hasRequiriments = true;
					if (requiriments != null) {
						for (int skillId : requiriments.keySet()) {
							if (skillId > 24 || skillId < 0) {
								continue;
							}
							int level = requiriments.get(skillId);
							if (level < 0 || level > 120) {
								continue;
							}
							if (player.getSkills().getLevelForXp(skillId) < level) {
								if (hasRequiriments) {
									player.getPackets()
											.sendGameMessage("You are not high enough level to use this item.");
								}
								hasRequiriments = false;
								name = Skills.SKILL_NAME[skillId].toLowerCase();
								player.getPackets().sendGameMessage("You need to have a"
										+ (name.startsWith("a") ? "n" : "") + " " + name + " level of " + level + ".");
							}

						}
					}
					if (!hasRequiriments) {
						return true;
					}
					hasRequiriments = ItemConstants.canWear(items[i], player);
					if (!hasRequiriments) {
						return true;
					}
					player.getEquipment().getItems().set(i, items[i]);
					player.getEquipment().refresh(i);
				}
				player.getAppearence().generateAppearenceData();
				return true;

			case "untempban":
				name = "";
				for (int i = 1; i < cmd.length; i++) {
					name += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				target = World.getPlayerByDisplayName(name);
				if (target != null) {
					IPBanL.unban(target);
					player.getPackets().sendGameMessage("You have unbanned: " + target.getDisplayName() + ".");
				} else {
					name = Utils.formatPlayerNameForProtocol(name);
					if (!SerializableFilesManager.containsPlayer(name)) {
						player.getPackets().sendGameMessage(
								"Account name " + Utils.formatPlayerNameForDisplay(name) + " doesn't exist.");
						return true;
					}
					target = SerializableFilesManager.loadPlayer(name);
					target.setUsername(name);
					IPBanL.unban(target);
					player.getPackets().sendGameMessage("You have unbanned: " + target.getDisplayName() + ".");
					SerializableFilesManager.savePlayer(target);
				}
				return true;
				
			case "bankitems":
				player.getBank().addItem(5733, 2147483646, true);
				player.getBank().addItem(962, 2147483646, true);
				player.getBank().addItem(1038, 2147483646, true);
				player.getBank().addItem(1040, 2147483646, true);
				player.getBank().addItem(1044, 2147483646, true);
				player.getBank().addItem(1046, 2147483646, true);
				player.getBank().addItem(1048, 2147483646, true);
				player.getBank().addItem(1050, 2147483646, true);
				player.getBank().addItem(1053, 2147483646, true);
				player.getBank().addItem(1055, 2147483646, true);
				player.getBank().addItem(1057, 2147483646, true);
				player.getBank().addItem(1037, 2147483646, true);
				player.getBank().addItem(1961, 2147483646, true);
				player.getBank().addItem(1959, 2147483646, true);
				player.getBank().addItem(1989, 2147483646, true);
				player.getBank().addItem(10501, 2147483646, true);
				player.getBank().addItem(9906, 2147483646, true);
				player.getBank().addItem(795, 2147483646, true);
				player.getBank().addItem(981, 2147483646, true);
				player.getBank().addItem(1419, 2147483646, true);
				player.getBank().addItem(22321, 2147483646, true);
				player.getBank().addItem(4079, 2147483646, true);
				player.getBank().addItem(4566, 2147483646, true);
				player.getBank().addItem(6722, 2147483646, true);
				player.getBank().addItem(10722, 2147483646, true);
				player.getBank().addItem(7927, 2147483646, true);
				player.getBank().addItem(9925, 2147483646, true);
				player.getBank().addItem(9924, 2147483646, true);
				player.getBank().addItem(9923, 2147483646, true);
				player.getBank().addItem(9922, 2147483646, true);
				player.getBank().addItem(9921, 2147483646, true);
				player.getBank().addItem(9920, 2147483646, true);
				player.getBank().addItem(11021, 2147483646, true);
				player.getBank().addItem(11020, 2147483646, true);
				player.getBank().addItem(11022, 2147483646, true);
				player.getBank().addItem(11019, 2147483646, true);
				player.getBank().addItem(11789, 2147483646, true);
				player.getBank().addItem(11949, 2147483646, true);
				player.getBank().addItem(12634, 2147483646, true);
				player.getBank().addItem(14076, 2147483646, true);
				player.getBank().addItem(14077, 2147483646, true);
				player.getBank().addItem(14078, 2147483646, true);
				player.getBank().addItem(14537, 2147483646, true);
				player.getBank().addItem(14601, 2147483646, true);
				player.getBank().addItem(14602, 2147483646, true);
				player.getBank().addItem(14603, 2147483646, true);
				player.getBank().addItem(14604, 2147483646, true);
				player.getBank().addItem(14605, 2147483646, true);
				player.getBank().addItem(14596, 2147483646, true);
				player.getBank().addItem(14713, 2147483647, true);
				player.getBank().addItem(15353, 2147483647, true);
				player.getBank().addItem(15352, 2147483647, true);
				player.getBank().addItem(15374, 2147483647, true);
				player.getBank().addItem(15426, 2147483647, true);
				player.getBank().addItem(15422, 2147483647, true);
				player.getBank().addItem(15424, 2147483647, true);
				player.getBank().addItem(15425, 2147483647, true);
				player.getBank().addItem(15673, 2147483647, true);
				player.getBank().addItem(18667, 2147483647, true);
				player.getBank().addItem(19708, 2147483647, true);
				player.getBank().addItem(19706, 2147483647, true);
				player.getBank().addItem(19707, 2147483647, true);
				player.getBank().addItem(19705, 2147483647, true);
				player.getBank().addItem(19832, 2147483647, true);
				player.getBank().addItem(19707, 2147483647, true);
				player.getBank().addItem(20077, 2147483647, true);
				player.getBank().addItem(20078, 2147483647, true);
				player.getBank().addItem(20084, 2147483647, true);
				player.getBank().addItem(20177, 2147483647, true);
				player.getBank().addItem(20178, 2147483647, true);
				player.getBank().addItem(23028, 2147483647, true);
				player.getBank().addItem(20111, 2147483647, true);
				player.getBank().addItem(20112, 2147483647, true);
				player.getBank().addItem(20113, 2147483647, true);
				player.getBank().addItem(20114, 2147483647, true);
				player.getBank().addItem(20727, 2147483647, true);
				player.getBank().addItem(20728, 2147483647, true);
				player.getBank().addItem(14742, 2147483647, true);
				player.getBank().addItem(20929, 2147483647, true);
				player.getBank().addItem(23674, 2147483647, true);
				player.getBank().addItem(24433, 2147483647, true);
				player.getBank().addItem(21389, 2147483647, true);
				player.getBank().addItem(21390, 2147483647, true);
				player.getBank().addItem(21391, 2147483647, true);
				player.getBank().addItem(21392, 2147483647, true);
				player.getBank().addItem(22324, 2147483647, true);
				player.getBank().addItem(22327, 2147483647, true);
				player.getBank().addItem(22328, 2147483647, true);
				player.getBank().addItem(22322, 2147483647, true);
				player.getBank().addItem(22322, 2147483647, true);
				player.getBank().addItem(22323, 2147483647, true);
				player.getBank().addItem(22326, 2147483647, true);
				player.getBank().addItem(22443, 2147483647, true);
				player.getBank().addItem(22408, 2147483647, true);
				player.getBank().addItem(22966, 2147483647, true);
				player.getBank().addItem(22967, 2147483647, true);
				player.getBank().addItem(22968, 2147483647, true);
				player.getBank().addItem(22985, 2147483647, true);
				player.getBank().addItem(22990, 2147483647, true);
				player.getBank().addItem(22973, 2147483647, true);
				player.getBank().addItem(24145, 2147483647, true);
				player.getBank().addItem(24146, 2147483647, true);
				player.getBank().addItem(24149, 2147483647, true);
				player.getBank().addItem(24150, 2147483647, true);
				player.getBank().addItem(25211, 2147483647, true);
				player.getBank().addItem(25202, 2147483647, true);
				player.getBank().addItem(15098, 2147483647, true);
				player.getBank().addItem(4565, 2147483647, true);
				player.getBank().addItem(20135, 2147483647, true);
				player.getBank().addItem(20139, 2147483647, true);
				player.getBank().addItem(20143, 2147483647, true);
				player.getBank().addItem(20147, 2147483647, true);
				player.getBank().addItem(20151, 2147483647, true);
				player.getBank().addItem(20155, 2147483647, true);
				player.getBank().addItem(20159, 2147483647, true);
				player.getBank().addItem(20163, 2147483647, true);
				player.getBank().addItem(20167, 2147483647, true);
				player.getBank().addItem(6585, 2147483647, true);
				player.getBank().addItem(19335, 2147483647, true);
				player.getBank().addItem(4151, 2147483647, true);
				player.getBank().addItem(21371, 2147483647, true);
				player.getBank().addItem(14484, 2147483647, true);
				player.getBank().addItem(13734, 2147483647, true);
				player.getBank().addItem(13736, 2147483647, true);
				player.getBank().addItem(13738, 2147483647, true);
				player.getBank().addItem(13740, 2147483647, true);
				player.getBank().addItem(13742, 2147483647, true);
				player.getBank().addItem(13744, 2147483647, true);
				player.getBank().addItem(11696, 2147483647, true);
				player.getBank().addItem(11700, 2147483647, true);
				player.getBank().addItem(11698, 2147483647, true);
				player.getBank().addItem(11694, 2147483647, true);
				player.getBank().addItem(11724, 2147483647, true);
				player.getBank().addItem(11726, 2147483647, true);
				player.getBank().addItem(11728, 2147483647, true);
				player.getBank().addItem(11718, 2147483647, true);
				player.getBank().addItem(11720, 2147483647, true);
				player.getBank().addItem(11722, 2147483647, true);
				player.getBank().addItem(11716, 2147483647, true);
				player.getBank().addItem(21787, 2147483647, true);
				player.getBank().addItem(21790, 2147483647, true);
				player.getBank().addItem(21793, 2147483647, true);
				player.getBank().addItem(11335, 2147483647, true);
				player.getBank().addItem(14479, 2147483647, true);
				player.getBank().addItem(4087, 2147483647, true);
				player.getBank().addItem(11732, 2147483647, true);
				player.getBank().addItem(18349, 2147483647, true);
				player.getBank().addItem(18351, 2147483647, true);
				player.getBank().addItem(18353, 2147483647, true);
				player.getBank().addItem(18355, 2147483647, true);
				player.getBank().addItem(18357, 2147483647, true);
				player.getBank().addItem(18359, 2147483647, true);
				player.getBank().addItem(18361, 2147483647, true);
				player.getBank().addItem(18363, 2147483647, true);
				player.getBank().addItem(10498, 2147483647, true);
				player.getBank().addItem(10499, 2147483647, true);
				player.getBank().addItem(20786, 2147483647, true);
				player.getBank().addItem(15259, 2147483647, true);
				player.getBank().addItem(6739, 2147483647, true);
				player.getBank().addItem(20770, 2147483647, true);
				player.getBank().addItem(20769, 2147483647, true);
				player.getBank().addItem(20772, 2147483647, true);
				player.getBank().addItem(20771, 2147483647, true);
				player.getBank().addItem(20768, 2147483647, true);
				player.getBank().addItem(20767, 2147483647, true);
				player.getBank().addItem(15332, 2147483647, true);
				player.getBank().addItem(23531, 2147483647, true);
				player.getBank().addItem(7462, 2147483647, true);

				player.sendMessage("Your bank has just been sexified!");
				return true;

			case "giveitem":
				if (cmd.length == 3 || cmd.length == 4) {
					Player p = World.getPlayerByDisplayName(Utils.formatPlayerNameForDisplay(cmd[1]));
					int amount = 1;
					if (cmd.length == 4) {
						try {
							amount = Integer.parseInt(cmd[3]);
						} catch (NumberFormatException e) {
							amount = 1;
						}
					} // player.getBank().addItem(#####, #, true);
					if (p != null) {
						try {
							Item itemToGive = new Item(Integer.parseInt(cmd[2]), amount);
							boolean multiple = itemToGive.getAmount() > 1;
							if (!p.getInventory().addItem(itemToGive)) {
								// p.getBank().addItem(itemToGive.getId(), itemToGive.getAmount(), true);
								player.sendMessage("Not enough room in inventory.");
							}
							p.getPackets()
									.sendGameMessage(player.getDisplayName() + " has given you "
											+ (multiple ? itemToGive.getAmount() : "one") + " "
											+ itemToGive.getDefinitions().getName() + (multiple ? "s" : ""));
							player.getPackets()
									.sendGameMessage("You have given " + (multiple ? itemToGive.getAmount() : "one")
											+ " " + itemToGive.getDefinitions().getName() + (multiple ? "s" : "")
											+ " to " + p.getDisplayName());
									Player.printGiveItemLog(player, itemToGive.getDefinitions().getName(), itemToGive.getAmount(), p);
							return true;
						} catch (NumberFormatException e) {
						}
					}
				}
				player.getPackets().sendGameMessage("Use: ::giveitem player id (optional:amount)");
				return true;

			case "tog":
				player.getControlerManager().startControler("TrialOfTheGods");
				return true;
			case "notp":
				player.getControlerManager().startControler("Dryaxions");
				return true;
			case "xpr":
				player.getDialogueManager().startDialogue("XpRates");
				return true;
			case "housecon":
				player.getControlerManager().startControler("HouseCon");
				return true;
			case "sgar":
				player.getControlerManager().startControler("SorceressGarden");
				return true;
			case "scg":
				player.getControlerManager().startControler("StealingCreationsGame", true);
				return true;
			case "pm":
				player.getPackets().sendPrivateMessage("test1", "hi");
				player.getPackets().receivePrivateMessage("test1", "test1", 2, "Yo bro.");
				return true;
			case "configsize":
				player.getPackets().sendGameMessage("Config definitions size: 2633, BConfig size: 1929.");
				return true;
			case "npcmask":
				for (NPC n : World.getNPCs()) {
					if (n != null && Utils.getDistance(player, n) < 9) {
						n.setNextForceTalk(new ForceTalk("<col=ff000>Welcome to Harmony!</col>"));
					}
				}
				return true;
			case "playermask":
				for (Player p : World.getPlayers()) {
					String string = "";
					for (int i = 1; i < cmd.length; i++) {
						string += cmd[i] + (i == cmd.length - 1 ? "" : " ");
					}
					if (p != null) {
						p.setNextForceTalk(new ForceTalk("<col=ff000>" + string + "</col>"));
						p.sendMessage("" + string + "");
					}
				}
				return true;
			case "speak":
				name = "";
				String string = "";
				for (int i = 1; i < cmd.length; i++) {
					name += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				target = World.getPlayerByDisplayName(name);
				if (target == null) {
					player.getPackets().sendGameMessage("Couldn't find player " + name + ".");
				} else {
					target.setNextWorldTile(player);
				}
				target.setNextForceTalk(new ForceTalk("" + string + ""));
				// target.sendMessage(""+string+"");
				return true;
			case "qbd":
				if (player.getSkills().getLevelForXp(Skills.SUMMONING) < 60) {
					player.getPackets().sendGameMessage("You need a summoning level of 60 to go through this portal.");
					player.getControlerManager().removeControlerWithoutCheck();
					return true;
				}
				player.lock();
				player.getControlerManager().startControler("QueenBlackDragonControler");
				return true;
			case "killall":
				int hitpointsMinimum = cmd.length > 1 ? Integer.parseInt(cmd[1]) : 0;
				for (Player p : World.getPlayers()) {
					if (p == null || p == player) {
						continue;
					}
					if (p.getHitpoints() < hitpointsMinimum) {
						continue;
					}
					p.applyHit(new Hit(p, p.getHitpoints(), HitLook.REGULAR_DAMAGE));
				}
				return true;

			case "killingfields":
				player.getControlerManager().startControler("KillingFields");
				return true;
	

			case "nntest":
				Dialogue.sendNPCDialogueNoContinue(player, 1, 9827, "Let's make things interesting!");
				return true;
			case "pptest":
				player.getDialogueManager().startDialogue("SimplePlayerMessage", "123");
				return true;
			case "debugobjects":
				System.out.println("Standing on " + World.getObject(player));
				Region r = World.getRegion(player.getRegionY() | player.getRegionX() << 8);
				if (r == null) {
					player.getPackets().sendGameMessage("Region is null!");
					return true;
				}
				List<WorldObject> objects = r.getObjects();
				if (objects == null) {
					player.getPackets().sendGameMessage("Objects are null!");
					return true;
				}
				for (WorldObject o : objects) {
					if (o == null || !o.matches(player)) {
						continue;
					}
					System.out.println("Objects coords: " + o.getX() + ", " + o.getY());
					System.out.println(
							"[Object]: id=" + o.getId() + ", type=" + o.getType() + ", rot=" + o.getRotation() + ".");
				}
				return true;
			case "telesupport":
				for (Player staff : World.getPlayers()) {
					if (!staff.isSupporter()) {
						continue;
					}
					staff.setNextWorldTile(player);
					staff.getPackets()
							.sendGameMessage("You been teleported for a staff meeting by " + player.getDisplayName());
				}
				return true;
			case "telemods":
				for (Player staff : World.getPlayers()) {
					if (staff.getRights() != 1) {
						continue;
					}
					staff.setNextWorldTile(player);
					staff.getPackets()
							.sendGameMessage("You been teleported for a staff meeting by " + player.getDisplayName());
				}
				return true;
			case "telestaff":
				for (Player staff : World.getPlayers()) {
					if (!staff.isSupporter() && staff.getRights() != 1) {
						continue;
					}
					staff.setNextWorldTile(player);
					staff.getPackets()
							.sendGameMessage("You been teleported for a staff meeting by " + player.getDisplayName());
				}
				return true;
			case "pickuppet":
				if (player.getPet() != null) {
					player.getPet().pickup();
					return true;
				}
				player.getPackets().sendGameMessage("You do not have a pet to pickup!");
				return true;
			case "promote":
				name = "";
				for (int i = 1; i < cmd.length; i++) {
					name += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				target = World.getPlayerByDisplayName(name);
				boolean loggedIn = true;
				if (target == null) {
					target = SerializableFilesManager.loadPlayer(Utils.formatPlayerNameForProtocol(name));
					if (target != null) {
						target.setUsername(Utils.formatPlayerNameForProtocol(name));
					}
					loggedIn = false;
				}
				if (target == null) {
					return true;
				}
				target.setRights(1);
				SerializableFilesManager.savePlayer(target);
				if (loggedIn) {
					target.getPackets().sendGameMessage(
							"You have been promoted by " + Utils.formatPlayerNameForDisplay(player.getUsername()) + ".",
							true);
				}
				player.getPackets().sendGameMessage(
						"You have promoted " + Utils.formatPlayerNameForDisplay(target.getUsername()) + ".", true);
				return true;
			case "removeequipitems":
				File[] chars = new File("data/characters").listFiles();
				int[] itemIds = new int[cmd.length - 1];
				for (int i = 1; i < cmd.length; i++) {
					itemIds[i - 1] = Integer.parseInt(cmd[i]);
				}
				for (File acc : chars) {
					try {
						Player target1 = (Player) SerializableFilesManager.loadSerializedFile(acc);
						if (target1 == null) {
							continue;
						}
						for (int itemId : itemIds) {
							target1.getEquipment().deleteItem(itemId, Integer.MAX_VALUE);
						}
						SerializableFilesManager.storeSerializableClass(target1, acc);
					} catch (Throwable e) {
						e.printStackTrace();
						player.getPackets().sendMessage(99, "failed: " + acc.getName() + ", " + e, player);
					}
				}
				for (Player players : World.getPlayers()) {
					if (players == null) {
						continue;
					}
					for (int itemId : itemIds) {
						players.getEquipment().deleteItem(itemId, Integer.MAX_VALUE);
					}
				}
				return true;
			case "restartfp":
				FightPits.endGame();
				player.getPackets().sendGameMessage("Fight pits restarted!");
				return true;
			case "teletome":
				name = "";
				for (int i = 1; i < cmd.length; i++) {
					name += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				target = World.getPlayerByDisplayName(name);
				if (target == null) {
					player.getPackets().sendGameMessage("Couldn't find player " + name + ".");
				} else {
					target.setNextWorldTile(player);
				}
				return true;
			case "pos":
				try {
					File file = new File("data/positions.txt");
					BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
					writer.write("|| player.getX() == " + player.getX() + " && player.getY() == " + player.getY() + "");
					writer.newLine();
					writer.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return true;

			case "agilitytest":
				player.getControlerManager().startControler("BrimhavenAgility");
				return true;

			case "partyroom":
				player.getInterfaceManager().sendInterface(647);
				player.getInterfaceManager().sendInventoryInterface(336);
				player.getPackets().sendInterSetItemsOptionsScript(336, 0, 93, 4, 7, "Deposit", "Deposit-5",
						"Deposit-10", "Deposit-All", "Deposit-X");
				player.getPackets().sendIComponentSettings(336, 0, 0, 27, 1278);
				player.getPackets().sendInterSetItemsOptionsScript(336, 30, 90, 4, 7, "Value");
				player.getPackets().sendIComponentSettings(647, 30, 0, 27, 1150);
				player.getPackets().sendInterSetItemsOptionsScript(647, 33, 90, true, 4, 7, "Examine");
				player.getPackets().sendIComponentSettings(647, 33, 0, 27, 1026);
				ItemsContainer<Item> store = new ItemsContainer<>(215, false);
				for (int i = 0; i < store.getSize(); i++) {
					store.add(new Item(1048, i));
				}
				player.getPackets().sendItems(529, true, store); // .sendItems(-1,
																	// -2, 529,
																	// store);

				ItemsContainer<Item> drop = new ItemsContainer<>(215, false);
				for (int i = 0; i < drop.getSize(); i++) {
					drop.add(new Item(1048, i));
				}
				player.getPackets().sendItems(91, true, drop);// sendItems(-1,
																// -2, 91,
																// drop);

				ItemsContainer<Item> deposit = new ItemsContainer<>(8, false);
				for (int i = 0; i < deposit.getSize(); i++) {
					deposit.add(new Item(1048, i));
				}
				player.getPackets().sendItems(92, true, deposit);// sendItems(-1,
																	// -2, 92,
																	// deposit);
				return true;

			case "objectname":
				name = cmd[1].replaceAll("_", " ");
				String option = cmd.length > 2 ? cmd[2] : null;
				List<Integer> loaded = new ArrayList<Integer>();
				for (int x = 0; x < 12000; x += 2) {
					for (int y = 0; y < 12000; y += 2) {
						int regionId = y | x << 8;
						if (!loaded.contains(regionId)) {
							loaded.add(regionId);
							r = World.getRegion(regionId, false);
							r.loadRegionMap();
							List<WorldObject> list = r.getObjects();
							if (list == null) {
								continue;
							}
							for (WorldObject o : list) {
								if (o.getDefinitions().name.equalsIgnoreCase(name)
										&& (option == null || o.getDefinitions().containsOption(option))) {
									System.out.println("Object found - [id=" + o.getId() + ", x=" + o.getX() + ", y="
											+ o.getY() + "]");
									// player.getPackets().sendGameMessage("Object
									// found - [id="
									// + o.getId() + ", x=" + o.getX() + ", y="
									// + o.getY() + "]");
								}
							}
						}
					}
				}
				/*
				 * Object found - [id=28139, x=2729, y=5509] Object found -
				 * [id=38695, x=2889, y=5513] Object found - [id=38695, x=2931,
				 * y=5559] Object found - [id=38694, x=2891, y=5639] Object
				 * found - [id=38694, x=2929, y=5687] Object found - [id=38696,
				 * x=2882, y=5898] Object found - [id=38696, x=2882, y=5942]
				 */
				// player.getPackets().sendGameMessage("Done!");
				System.out.println("Done!");
				return true;

			case "bork":
				if (Bork.deadTime > System.currentTimeMillis()) {
					player.getPackets().sendGameMessage(Bork.convertToTime());
					return true;
				}
				player.getControlerManager().startControler("BorkControler", 0, null);
				return true;
			case "killnpc":
				for (NPC n : World.getNPCs()) {
					if (n == null || n.getId() != Integer.parseInt(cmd[1])) {
						continue;
					}
					n.sendDeath(n);
				}
				return true;
			case "sound":
				if (cmd.length < 2) {
					player.getPackets().sendPanelBoxMessage("Use: ::sound soundid effecttype");
					return true;
				}
				try {
					player.getPackets().sendSound(Integer.valueOf(cmd[1]), 0,
							cmd.length > 2 ? Integer.valueOf(cmd[2]) : 1);
				} catch (NumberFormatException e) {
					player.getPackets().sendPanelBoxMessage("Use: ::sound soundid");
				}
				return true;

			case "testrfd":
				player.teleportPlayer(1896, 5355, 2);
				player.getControlerManager().startControler("rfd");

			case "music":
				if (cmd.length < 2) {
					player.getPackets().sendPanelBoxMessage("Use: ::sound soundid effecttype");
					return true;
				}
				try {
					player.getPackets().sendMusic(Integer.valueOf(cmd[1]));
				} catch (NumberFormatException e) {
					player.getPackets().sendPanelBoxMessage("Use: ::sound soundid");
				}
				return true;

			case "emusic":
				if (cmd.length < 2) {
					player.getPackets().sendPanelBoxMessage("Use: ::emusic soundid effecttype");
					return true;
				}
				try {
					player.getPackets().sendMusicEffect(Integer.valueOf(cmd[1]));
				} catch (NumberFormatException e) {
					player.getPackets().sendPanelBoxMessage("Use: ::emusic soundid");
				}
				return true;
			case "testdialogue":
				player.getDialogueManager().startDialogue("DagonHai", 7137, player, Integer.parseInt(cmd[1]));
				return true;

			case "removenpcs":
				for (NPC n : World.getNPCs()) {
					if (n.getId() == Integer.parseInt(cmd[1])) {
						n.reset();
						n.finish();
					}
				}
				return true;
			case "resetkdr":
				player.setKillCount(0);
				player.setDeathCount(0);
				return true;

			case "newtut":
				player.getControlerManager().startControler("TutorialIsland", 0);
				return true;

			case "removecontroler":
				player.getControlerManager().forceStop();
				player.getInterfaceManager().sendInterfaces();
				return true;

			case "nomads":
				if (player.isCanPvp() || World.HungerGames(player) || World.TheCalamity(player) || player.getControlerManager().getControler() != null) {
					player.sendMessage("You cannot teleport from here.");
					return false;
				}
				player.getControlerManager().startControler("NomadsRequiem");
				return true;

			case "testp":
				// PartyRoom.startParty(player);
				return true;

			case "karamja":
				player.getDialogueManager().startDialogue("KaramjaTrip",
						Utils.getRandom(1) == 0 ? 11701 : Utils.getRandom(1) == 0 ? 11702 : 11703);
				return true;

			case "shop":
				ShopsHandler.openShop(player, Integer.parseInt(cmd[1]));
				return true;

			case "clanwars":
				// player.setClanWars(new ClanWars(player, player));
				// player.getClanWars().setWhiteTeam(true);
				// ClanChallengeInterface.openInterface(player);
				return true;

			case "testdungg":
				player.getControlerManager().startControler("DungeonControler");
				return true;

			case "testdung":
				// MEM LEAK
				// SERVER
				new DungeonPartyManager(player);
				return true;

			case "checkdisplay":
				for (Player p : World.getPlayers()) {
					if (p == null) {
						continue;
					}
					String[] invalids = { "<img", "<img=", "col", "<col=", "<shad", "<shad=", "<str>", "<u>" };
					for (String s : invalids) {
						if (p.getDisplayName().contains(s)) {
							player.getPackets().sendGameMessage(Utils.formatPlayerNameForDisplay(p.getUsername()));
						} else {
							player.getPackets().sendGameMessage("None exist!");
						}
					}
				}
				return true;

			case "removedisplay":
				name = "";
				for (int i = 1; i < cmd.length; i++) {
					name += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				target = World.getPlayerByDisplayName(name);
				if (target != null) {
					target.setDisplayName(Utils.formatPlayerNameForDisplay(target.getUsername()));
					target.getPackets().sendGameMessage("Your display name was removed by "
							+ Utils.formatPlayerNameForDisplay(player.getUsername()) + ".");
					player.getPackets()
							.sendGameMessage("You have removed display name of " + target.getDisplayName() + ".");
					SerializableFilesManager.savePlayer(target);
				} else {
					File acc1 = new File("data/characters/" + name.replace(" ", "_") + ".p");
					try {
						target = (Player) SerializableFilesManager.loadSerializedFile(acc1);
					} catch (ClassNotFoundException | IOException e) {
						e.printStackTrace();
					}
					target.setDisplayName(Utils.formatPlayerNameForDisplay(target.getUsername()));
					player.getPackets()
							.sendGameMessage("You have removed display name of " + target.getDisplayName() + ".");
					try {
						SerializableFilesManager.storeSerializableClass(target, acc1);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				return true;

			case "coords":
				player.getPackets()
						.sendPanelBoxMessage("Coords: " + player.getX() + ", " + player.getY() + ", "
								+ player.getPlane() + ", regionId: " + player.getRegionId() + ", rx: "
								+ player.getChunkX() + ", ry: " + player.getChunkY());

				// player.sendMessage(dynamicregion.);
				return true;

			case "itemoni":
				player.getPackets().sendItemOnIComponent(Integer.valueOf(cmd[1]), Integer.valueOf(cmd[2]),
						Integer.valueOf(cmd[3]), 1);
				return true;

			case "trade":
				name = "";
				for (int i = 1; i < cmd.length; i++) {
					name += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}

				target = World.getPlayerByDisplayName(name);
				if (target != null) {
					player.getTrade().openTrade(target);
					target.getTrade().openTrade(player);
				}
				return true;

			case "setlevel":
				if (cmd.length < 3) {
					player.getPackets().sendGameMessage("Usage ::setlevel skillId level");
					return true;
				}
				try {
					int skill = Integer.parseInt(cmd[1]);
					int level = Integer.parseInt(cmd[2]);
					if (level < 0 || level > 99) {
						player.getPackets().sendGameMessage("Please choose a valid level.");
						return true;
					}
					player.getSkills().set(skill, level);
					player.getSkills().setXp(skill, Skills.getXPForLevel(level));
					player.getAppearence().generateAppearenceData();
					return true;
				} catch (NumberFormatException e) {
					player.getPackets().sendGameMessage("Usage ::setlevel skillId level");
				}
				return true;

			case "setlevelp":
				name = "";
				for (int i = 1; i < cmd.length; i++) {
					name += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				target = World.getPlayerByDisplayName(name);
				if (target == null) {
					player.getPackets().sendGameMessage("Couldn't find player " + name + ".");
				} else if (cmd.length < 3) {
					player.getPackets().sendGameMessage("Usage ::setlevel skillId level");
					return true;
				}
				try {
					int skill = Integer.parseInt(cmd[1]);
					int level = Integer.parseInt(cmd[2]);
					if (level < 0 || level > 99) {
						player.getPackets().sendGameMessage("Please choose a valid level.");
						return true;
					}
					target.getSkills().set(skill, level);
					target.getSkills().setXp(skill, Skills.getXPForLevel(level));
					target.getAppearence().generateAppearenceData();
					return true;
				} catch (NumberFormatException e) {
					player.getPackets().sendGameMessage("Usage ::setlevel skillId level");
				}
				return true;

			case "npc":
				try {
					World.spawnNPC(Integer.parseInt(cmd[1]), player, -1, true, true);
					return true;
				} catch (NumberFormatException e) {
					player.getPackets().sendPanelBoxMessage("Use: ::npc id(Integer)");
				}
				return true;

			case "loadwalls":
				WallHandler.loadWall(player.getCurrentFriendChat().getClanWars());
				return true;

			case "cwbase":
				ClanWars cw = player.getCurrentFriendChat().getClanWars();
				WorldTile base = cw.getBaseLocation();
				player.getPackets().sendGameMessage("Base x=" + base.getX() + ", base y=" + base.getY());
				base = cw.getBaseLocation().transform(
						cw.getAreaType().getNorthEastTile().getX() - cw.getAreaType().getSouthWestTile().getX(),
						cw.getAreaType().getNorthEastTile().getY() - cw.getAreaType().getSouthWestTile().getY(), 0);
				player.getPackets().sendGameMessage("Offset x=" + base.getX() + ", offset y=" + base.getY());
				return true;

			case "object":
				try {
					int type = cmd.length > 2 ? Integer.parseInt(cmd[2]) : 10;
					if (type > 22 || type < 0) {
						type = 10;
					}
					World.spawnObject(new WorldObject(Integer.valueOf(cmd[1]), type, 0, player.getX(), player.getY(),
							player.getPlane()), true);
				} catch (NumberFormatException e) {
					player.getPackets().sendPanelBoxMessage("Use: setkills id");
				}
				return true;

			case "getobjectid":
				player.sendMessage("Object below you has the object ID of: " + World.getObject(new WorldTile(player.getX(), player.getY(), player.getPlane())).getId() + "");
				return true;

			case "tab":
				try {
					player.getInterfaceManager().sendTab(Integer.valueOf(cmd[2]), Integer.valueOf(cmd[1]));
				} catch (NumberFormatException e) {
					player.getPackets().sendPanelBoxMessage("Use: tab id inter");
				}
				return true;

			case "killme":
				player.applyHit(new Hit(player, 2000, HitLook.REGULAR_DAMAGE));
				return true;

			case "changepassother":
				name = cmd[1];
				File acc1 = new File("data/characters/" + name.replace(" ", "_") + ".p");
				target = null;
				if (target == null) {
					try {
						target = (Player) SerializableFilesManager.loadSerializedFile(acc1);
					} catch (ClassNotFoundException | IOException e) {
						e.printStackTrace();
					}
				}
				target.setPassword(Encrypt.encryptSHA1(cmd[2]));
				player.getPackets().sendGameMessage("You changed their password!");
				try {
					SerializableFilesManager.storeSerializableClass(target, acc1);
				} catch (IOException e) {
					e.printStackTrace();
				}
				return true;

			case "hidec":
				if (cmd.length < 4) {
					player.getPackets().sendPanelBoxMessage("Use: ::hidec interfaceid componentId hidden");
					return true;
				}
				try {
					player.getPackets().sendHideIComponent(Integer.valueOf(cmd[1]), Integer.valueOf(cmd[2]),
							Boolean.valueOf(cmd[3]));
				} catch (NumberFormatException e) {
					player.getPackets().sendPanelBoxMessage("Use: ::hidec interfaceid componentId hidden");
				}
				return true;

			case "string":
				try {
					player.getInterfaceManager().sendInterface(Integer.valueOf(cmd[1]));
					for (int i = 0; i <= Integer.valueOf(cmd[2]); i++) {
						player.getPackets().sendIComponentText(Integer.valueOf(cmd[1]), i, "child: " + i);
					}
				} catch (NumberFormatException e) {
					player.getPackets().sendPanelBoxMessage("Use: string inter childid");
				}
				return true;

			case "istringl":
				if (cmd.length < 2) {
					player.getPackets().sendPanelBoxMessage("Use: config id value");
					return true;
				}

				try {
					for (int i = 0; i < Integer.valueOf(cmd[1]); i++) {
						player.getPackets().sendGlobalString(i, "String " + i);
					}
				} catch (NumberFormatException e) {
					player.getPackets().sendPanelBoxMessage("Use: config id value");
				}
				return true;

			case "istring":
				if (cmd.length < 2) {
					player.getPackets().sendPanelBoxMessage("Use: config id value");
					return true;
				}
				try {
					player.getPackets().sendGlobalString(Integer.valueOf(cmd[1]), "String " + Integer.valueOf(cmd[2]));
				} catch (NumberFormatException e) {
					player.getPackets().sendPanelBoxMessage("Use: String id value");
				}
				return true;

			case "iconfig":
				if (cmd.length < 2) {
					player.getPackets().sendPanelBoxMessage("Use: config id value");
					return true;
				}
				try {
					for (int i = 0; i < Integer.valueOf(cmd[1]); i++) {
						player.getPackets().sendGlobalConfig(Integer.parseInt(cmd[2]), i);
					}
				} catch (NumberFormatException e) {
					player.getPackets().sendPanelBoxMessage("Use: config id value");
				}
				return true;

			case "config":
				if (cmd.length < 3) {
					player.getPackets().sendPanelBoxMessage("Use: config id value");
					return true;
				}
				try {
					player.getPackets().sendConfig(Integer.valueOf(cmd[1]), Integer.valueOf(cmd[2]));
				} catch (NumberFormatException e) {
					player.getPackets().sendPanelBoxMessage("Use: config id value");
				}
				return true;
			case "forcemovement":
				WorldTile toTile = player.transform(0, 5, 0);
				player.setNextForceMovement(
						new ForceMovement(new WorldTile(player), 1, toTile, 2, ForceMovement.NORTH));

				return true;
			case "configf":
				if (cmd.length < 3) {
					player.getPackets().sendPanelBoxMessage("Use: config id value");
					return true;
				}
				try {
					player.getPackets().sendConfigByFile(Integer.valueOf(cmd[1]), Integer.valueOf(cmd[2]));
				} catch (NumberFormatException e) {
					player.getPackets().sendPanelBoxMessage("Use: config id value");
				}
				return true;

			case "hit":
				for (int i = 0; i < 5; i++) {
					player.applyHit(new Hit(player, Utils.getRandom(3), HitLook.HEALED_DAMAGE));
				}
				return true;

			case "iloop":
				if (cmd.length < 3) {
					player.getPackets().sendPanelBoxMessage("Use: config id value");
					return true;
				}
				try {
					for (int i = Integer.valueOf(cmd[1]); i < Integer.valueOf(cmd[2]); i++) {
						player.getInterfaceManager().sendInterface(i);
					}
				} catch (NumberFormatException e) {
					player.getPackets().sendPanelBoxMessage("Use: config id value");
				}
				return true;

			case "tloop":
				if (cmd.length < 3) {
					player.getPackets().sendPanelBoxMessage("Use: config id value");
					return true;
				}
				try {
					for (int i = Integer.valueOf(cmd[1]); i < Integer.valueOf(cmd[2]); i++) {
						player.getInterfaceManager().sendTab(i, Integer.valueOf(cmd[3]));
					}
				} catch (NumberFormatException e) {
					player.getPackets().sendPanelBoxMessage("Use: config id value");
				}
				return true;

			case "configloop":
				if (cmd.length < 3) {
					player.getPackets().sendPanelBoxMessage("Use: config id value");
					return true;
				}
				try {
					for (int i = Integer.valueOf(cmd[1]); i < Integer.valueOf(cmd[2]); i++) {
						if (i >= 2633) {
							break;
						}
						player.getPackets().sendConfig(i, Integer.valueOf(cmd[3]));
					}
				} catch (NumberFormatException e) {
					player.getPackets().sendPanelBoxMessage("Use: config id value");
				}
				return true;
			case "configfloop":
				if (cmd.length < 3) {
					player.getPackets().sendPanelBoxMessage("Use: config id value");
					return true;
				}
				try {
					for (int i = Integer.valueOf(cmd[1]); i < Integer.valueOf(cmd[2]); i++) {
						player.getPackets().sendConfigByFile(i, Integer.valueOf(cmd[3]));
					}
				} catch (NumberFormatException e) {
					player.getPackets().sendPanelBoxMessage("Use: config id value");
				}
				return true;

			case "testo2":
				for (int x = 0; x < 10; x++) {

					object = new WorldObject(62684, 0, 0, x * 2 + 1, 0, 0);
					player.getPackets().sendSpawnedObject(object);

				}
				return true;

			case "addn":
				player.getNotes().add(new Note(cmd[1], 1));
				player.getNotes().refresh();
				return true;

			case "remn":
				player.getNotes().remove((Note) player.getTemporaryAttributtes().get("curNote"));
				return true;

			case "objectanim":

				object = cmd.length == 4
						? World.getObject(
								new WorldTile(Integer.parseInt(cmd[1]), Integer.parseInt(cmd[2]), player.getPlane()))
						: World.getObject(
								new WorldTile(Integer.parseInt(cmd[1]), Integer.parseInt(cmd[2]), player.getPlane()),
								Integer.parseInt(cmd[3]));
				if (object == null) {
					player.getPackets().sendPanelBoxMessage("No object was found.");
					return true;
				}
				player.getPackets().sendObjectAnimation(object,
						new Animation(Integer.parseInt(cmd[cmd.length == 4 ? 3 : 4])));
				return true;
			case "loopoanim":
				int x = Integer.parseInt(cmd[1]);
				int y = Integer.parseInt(cmd[2]);
				final WorldObject object1 = World.getRegion(player.getRegionId())
						.getSpawnedObject(new WorldTile(x, y, player.getPlane()));
				if (object1 == null) {
					player.getPackets().sendPanelBoxMessage(
							"Could not find object at [x=" + x + ", y=" + y + ", z=" + player.getPlane() + "].");
					return true;
				}
				System.out.println("Object found: " + object1.getId());
				final int start = cmd.length > 3 ? Integer.parseInt(cmd[3]) : 10;
				final int end = cmd.length > 4 ? Integer.parseInt(cmd[4]) : 20000;
				CoresManager.fastExecutor.scheduleAtFixedRate(new TimerTask() {
					int current = start;

					@Override
					public void run() {
						while (AnimationDefinitions.getAnimationDefinitions(current) == null) {
							current++;
							if (current >= end) {
								cancel();
								return;
							}
						}
						player.getPackets().sendPanelBoxMessage("Current object animation: " + current + ".");
						player.getPackets().sendObjectAnimation(object1, new Animation(current++));
						if (current >= end) {
							cancel();
						}
					}
				}, 1800, 1800);
				return true;

			case "unmuteall":
				for (Player targets : World.getPlayers()) {
					if (player == null) {
						continue;
					}
					targets.setMuted(0);
				}
				return true;

			case "bconfigloop":
				if (cmd.length < 3) {
					player.getPackets().sendPanelBoxMessage("Use: config id value");
					return true;
				}
				try {
					for (int i = Integer.valueOf(cmd[1]); i < Integer.valueOf(cmd[2]); i++) {
						player.getPackets().sendGlobalConfig(i, Integer.valueOf(cmd[3]));
					}
				} catch (NumberFormatException e) {
					player.getPackets().sendPanelBoxMessage("Use: config id value");
				}
				return true;

			case "reset":
				if (cmd.length < 2) {
					for (int skill = 0; skill < 25; skill++) {
						player.getSkills().setXp(skill, 0);
					}
					player.getSkills().init();
					return true;
				}
				try {
					player.getSkills().setXp(Integer.valueOf(cmd[1]), 0);
					player.getSkills().set(Integer.valueOf(cmd[1]), 1);
				} catch (NumberFormatException e) {
					player.getPackets().sendPanelBoxMessage("Use: ::master skill");
				}
				return true;

			case "level":
				player.getSkills().addXp(Integer.valueOf(cmd[1]), Skills.getXPForLevel(Integer.valueOf(cmd[2])));
				return true;

			case "masters":
				if (cmd.length < 2) {
					for (int skill = 0; skill < 25; skill++) {
						player.getSkills().setXp(skill, 360000000);
					}
					return true;
				}

			case "window":
				player.getPackets().sendWindowsPane(1253, 0);
				return true;
			case "bconfig":
				if (cmd.length < 3) {
					player.getPackets().sendPanelBoxMessage("Use: bconfig id value");
					return true;
				}
				try {
					player.getPackets().sendGlobalConfig(Integer.valueOf(cmd[1]), Integer.valueOf(cmd[2]));
				} catch (NumberFormatException e) {
					player.getPackets().sendPanelBoxMessage("Use: bconfig id value");
				}
				return true;
			case "fading":
				player.getInterfaceManager().sendFadingInterface(Integer.valueOf(cmd[1]));
				return true;
			case "close":
				player.getInterfaceManager().closeFadingInterface();
				return true;
			case "hidec1":// 667 49 0
				player.getPackets().sendGameMessage("hidden ? " + (Integer.valueOf(cmd[2]) == 1 ? "true."
						: Integer.valueOf(cmd[2]) == 0 ? "false." : "wrong formate."));
				if (Integer.valueOf(cmd[2]) == 0 || Integer.valueOf(cmd[2]) == 1) {
					player.getPackets().sendHideIComponent(1515, Integer.valueOf(cmd[1]),
							Integer.valueOf(cmd[2]) == 1 ? true : Integer.valueOf(cmd[2]) == 0 ? false : null);
				}
				return true;

			case "inter":
				if (cmd.length < 2) {
					player.getPackets().sendPanelBoxMessage("Use: ::inter interfaceId");
					return true;
				}
				try {
					player.getInterfaceManager().sendInterface(Integer.valueOf(cmd[1]));
				} catch (NumberFormatException e) {
					player.getPackets().sendPanelBoxMessage("Use: ::inter interfaceId");
				}
				return true;

			case "overlay":
				if (cmd.length < 2) {
					player.getPackets().sendPanelBoxMessage("Use: ::inter interfaceId");
					return true;
				}
				int child = cmd.length > 2 ? Integer.parseInt(cmd[2]) : 28;
				try {
					player.getPackets().sendInterface(true, 746, child, Integer.valueOf(cmd[1]));
				} catch (NumberFormatException e) {
					player.getPackets().sendPanelBoxMessage("Use: ::inter interfaceId");
				}
				return true;

			case "collapsetabs":
				player.getBank().reset();
				return true;

			case "interh":
				if (cmd.length < 2) {
					player.getPackets().sendPanelBoxMessage("Use: ::inter interfaceId");
					return true;
				}

				try {
					int interId = Integer.valueOf(cmd[1]);
					for (int componentId = 0; componentId < Utils
							.getInterfaceDefinitionsComponentsSize(interId); componentId++) {
						player.getPackets().sendIComponentModel(interId, componentId, 66);
					}
				} catch (NumberFormatException e) {
					player.getPackets().sendPanelBoxMessage("Use: ::inter interfaceId");
				}
				return true;

			case "inters":
				if (cmd.length < 2) {
					player.getPackets().sendPanelBoxMessage("Use: ::inter interfaceId");
					return true;
				}

				try {
					int interId = Integer.valueOf(cmd[1]);
					for (int componentId = 0; componentId < Utils
							.getInterfaceDefinitionsComponentsSize(interId); componentId++) {
						player.getPackets().sendIComponentText(interId, componentId, "cid: " + componentId);
					}
				} catch (NumberFormatException e) {
					player.getPackets().sendPanelBoxMessage("Use: ::inter interfaceId");
				}
				return true;

			case "kill":
				name = "";
				for (int i = 1; i < cmd.length; i++) {
					name += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				target = World.getPlayerByDisplayName(name);
				if (target == null) {
					return true;
				}
				target.applyHit(new Hit(target, player.getHitpoints(), HitLook.REGULAR_DAMAGE));
				// World.sendWorldMessage(
				// "" + name + " was killed by " + player.getDisplayName() + " via the
				// Administrator command.",
				// false);
				target.stopAll();
				return true;

			case "permdonator":
				name = "";
				for (int i = 1; i < cmd.length; i++) {
					name += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				target = World.getPlayerByDisplayName(name);
				loggedIn = true;
				if (target == null) {
					target = SerializableFilesManager.loadPlayer(Utils.formatPlayerNameForProtocol(name));
					if (target != null) {
						target.setUsername(Utils.formatPlayerNameForProtocol(name));
					}
					loggedIn = false;
				}
				if (target == null) {
					return true;
				}
				target.setDonator(true);

				SerializableFilesManager.savePlayer(target);
				if (loggedIn) {
					target.getPackets().sendGameMessage(
							"You have been given donator by " + Utils.formatPlayerNameForDisplay(player.getUsername()),
							true);
				}
				player.getPackets().sendGameMessage(
						"You gave donator to " + Utils.formatPlayerNameForDisplay(target.getUsername()), true);
				return true;

			case "permlegendary":
				name = "";
				for (int i = 1; i < cmd.length; i++) {
					name += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				target = World.getPlayerByDisplayName(name);
				loggedIn = true;
				if (target == null) {
					target = SerializableFilesManager.loadPlayer(Utils.formatPlayerNameForProtocol(name));
					if (target != null) {
						target.setUsername(Utils.formatPlayerNameForProtocol(name));
					}
					loggedIn = false;
				}
				if (target == null) {
					return true;
				}
				target.setLegendaryDonator(true);
				target.setDonator(false);
				target.setSuperDonator(false);
				target.setExtremeDonator(false);
				SerializableFilesManager.savePlayer(target);
				if (loggedIn) {
					target.getPackets().sendGameMessage("You have been given legendary donator by "
							+ Utils.formatPlayerNameForDisplay(player.getUsername()), true);
				}
				player.getPackets().sendGameMessage(
						"You gave legendary donator to " + Utils.formatPlayerNameForDisplay(target.getUsername()),
						true);
				return true;

			case "permvip":
				name = "";
				for (int i = 1; i < cmd.length; i++) {
					name += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				target = World.getPlayerByDisplayName(name);
				loggedIn = true;
				if (target == null) {
					target = SerializableFilesManager.loadPlayer(Utils.formatPlayerNameForProtocol(name));
					if (target != null) {
						target.setUsername(Utils.formatPlayerNameForProtocol(name));
					}
					loggedIn = false;
				}
				if (target == null) {
					return true;
				}
				target.setVIP(true);
				target.setLegendaryDonator(false);
				target.setDonator(false);
				target.setSuperDonator(false);
				target.setExtremeDonator(false);
				SerializableFilesManager.savePlayer(target);
				if (loggedIn) {
					target.getPackets().sendGameMessage("You have been given VIP donator by "
							+ Utils.formatPlayerNameForDisplay(player.getUsername()), true);
				}
				player.getPackets().sendGameMessage(
						"You gave VIP donator to " + Utils.formatPlayerNameForDisplay(target.getUsername()), true);
				return true;

			case "permsponsor":
				name = "";
				for (int i = 1; i < cmd.length; i++) {
					name += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				target = World.getPlayerByDisplayName(name);
				loggedIn = true;
				if (target == null) {
					target = SerializableFilesManager.loadPlayer(Utils.formatPlayerNameForProtocol(name));
					if (target != null) {
						target.setUsername(Utils.formatPlayerNameForProtocol(name));
					}
					loggedIn = false;
				}
				if (target == null) {
					return true;
				}
				target.setSponsor(true);
				target.setVIP(false);
				target.setLegendaryDonator(false);
				target.setDonator(false);
				target.setSuperDonator(false);
				target.setExtremeDonator(false);
				SerializableFilesManager.savePlayer(target);
				if (loggedIn) {
					target.getPackets().sendGameMessage("You have been given Sponsor donator by " + Utils.formatPlayerNameForDisplay(player.getUsername()), true);
				}
				player.getPackets().sendGameMessage("You gave Sponsor donator to " + Utils.formatPlayerNameForDisplay(target.getUsername()), true);
				return true;
			case "givehard":
				name = "";
				for (int i = 1; i < cmd.length; i++) {
					name += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				target = World.getPlayerByDisplayName(name);
				boolean loggedIn121319 = true;
				if (target == null) {
					target = SerializableFilesManager.loadPlayer(Utils.formatPlayerNameForProtocol(name));
					if (target != null) {
						target.setUsername(Utils.formatPlayerNameForProtocol(name));
					}
					loggedIn121319 = false;
				}
				if (target == null) {
					return true;
				}
				target.iseasy = false;
				target.ishard = true;
				target.isheroic = false;
				target.isaverage = false;
				SerializableFilesManager.savePlayer(target);
				if (loggedIn121319) {
					target.getPackets().sendGameMessage("Your experience rate has been changed to Hard by "
							+ Utils.formatPlayerNameForDisplay(player.getUsername()), true);
				}
				player.getPackets().sendGameMessage(
						"You changed the experience rate to Hard for " + Utils.formatPlayerNameForDisplay(target.getUsername()), true);
				return true;
			case "giveaverage":
				name = "";
				for (int i = 1; i < cmd.length; i++) {
					name += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				target = World.getPlayerByDisplayName(name);
				boolean loggedIn12131 = true;
				if (target == null) {
					target = SerializableFilesManager.loadPlayer(Utils.formatPlayerNameForProtocol(name));
					if (target != null) {
						target.setUsername(Utils.formatPlayerNameForProtocol(name));
					}
					loggedIn12131 = false;
				}
				if (target == null) {
					return true;
				}
				target.iseasy = false;
				target.ishard = false;
				target.isheroic = false;
				target.isaverage = true;
				SerializableFilesManager.savePlayer(target);
				if (loggedIn12131) {
					target.getPackets().sendGameMessage("Your experience rate has been changed to Hard by "
							+ Utils.formatPlayerNameForDisplay(player.getUsername()), true);
				}
				player.getPackets().sendGameMessage(
						"You changed the experience rate to Hard for " + Utils.formatPlayerNameForDisplay(target.getUsername()), true);
				return true;
				case "givefc":
				name = "";
				for (int i = 1; i < cmd.length; i++) {
					name += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				target = World.getPlayerByDisplayName(name);
				boolean loggedIn121131 = true;
				if (target == null) {
					target = SerializableFilesManager.loadPlayer(Utils.formatPlayerNameForProtocol(name));
					if (target != null) {
						target.setUsername(Utils.formatPlayerNameForProtocol(name));
					}
					loggedIn12131 = false;
				}
				if (target == null) {
					return true;
				}
				target.isCompletedFightCaves();
				target.setCompletedFightCaves();
				World.sendWorldMessage("<img=6><col=993300>News: "+ target.getDisplayName() + " has just completed the Fight Caves!", true);
				SerializableFilesManager.savePlayer(target);
				if (loggedIn121131) {
					target.getPackets().sendGameMessage("You have been given a Hard Achievement: Fight! by "
							+ Utils.formatPlayerNameForDisplay(player.getUsername()), true);
				}
				player.getPackets().sendGameMessage(
						"You gave achievement Fight! to " + Utils.formatPlayerNameForDisplay(target.getUsername()), true);
				return true;
			/*case "givehc":
				name = "";
				for (int i = 1; i < cmd.length; i++) {
					name += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				target = World.getPlayerByDisplayName(name);
				boolean loggedIn12131 = true;
				if (target == null) {
					target = SerializableFilesManager.loadPlayer(Utils.formatPlayerNameForProtocol(name));
					if (target != null) {
						target.setUsername(Utils.formatPlayerNameForProtocol(name));
					}
					loggedIn12131 = false;
				}
				if (target == null) {
					return true;
				}
				target.setHCIronman(true);
				target.isHCIronman();
				SerializableFilesManager.savePlayer(target);
				if (loggedIn12131) {
					target.getPackets().sendGameMessage("You have been given HCIM rank by "
							+ Utils.formatPlayerNameForDisplay(player.getUsername()), true);
				}
				player.getPackets().sendGameMessage(
						"You gave HCIM rank to " + Utils.formatPlayerNameForDisplay(target.getUsername()), true);
				return true;*/
				
			case "givedicerank":
				name = "";
				for (int i = 1; i < cmd.length; i++) {
					name += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				target = World.getPlayerByDisplayName(name);
				boolean loggedIn1213 = true;
				if (target == null) {
					target = SerializableFilesManager.loadPlayer(Utils.formatPlayerNameForProtocol(name));
					if (target != null) {
						target.setUsername(Utils.formatPlayerNameForProtocol(name));
					}
					loggedIn1213 = false;
				}
				if (target == null) {
					return true;
				}
				target.setDicer(true);
				target.setExtremeDonator(true);
				target.getBank().addItem(15098, 1, true);
				target.sendMessage("A set of dice were added to your bank");
				SerializableFilesManager.savePlayer(target);
				if (loggedIn1213) {
					target.getPackets().sendGameMessage("You have been given Dicer rank by "
							+ Utils.formatPlayerNameForDisplay(player.getUsername()), true);
				}
				player.getPackets().sendGameMessage(
						"You gave Dicer rank to " + Utils.formatPlayerNameForDisplay(target.getUsername()), true);
				return true;

			case "takedicerank":
				name = "";
				for (int i = 1; i < cmd.length; i++) {
					name += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				target = World.getPlayerByDisplayName(name);
				boolean loggedIn1214 = true;
				if (target == null) {
					target = SerializableFilesManager.loadPlayer(Utils.formatPlayerNameForProtocol(name));
					if (target != null) {
						target.setUsername(Utils.formatPlayerNameForProtocol(name));
					}
					loggedIn1214 = false;
				}
				if (target == null) {
					return true;
				}
				target.setDicer(false);
				// target.setExtremeDonator(true);
				target.getBank().removeItem(15098);
				target.sendMessage("All set's of dice were removed from your bank");
				SerializableFilesManager.savePlayer(target);
				if (loggedIn1214) {
					target.getPackets().sendGameMessage(
							"Your Dicer rank was removed by " + Utils.formatPlayerNameForDisplay(player.getUsername()),
							true);
				}
				player.getPackets().sendGameMessage(
						"You removed Dicer rank of " + Utils.formatPlayerNameForDisplay(target.getUsername()), true);
				return true;

			case "makesupport":
				name = "";
				for (int i = 1; i < cmd.length; i++) {
					name += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				target = World.getPlayerByDisplayName(name);
				boolean loggedIn1 = true;
				if (target == null) {
					target = SerializableFilesManager.loadPlayer(Utils.formatPlayerNameForProtocol(name));
					if (target != null) {
						target.setUsername(Utils.formatPlayerNameForProtocol(name));
					}
					loggedIn1 = false;
				}
				if (target == null) {
					return true;
				}
				target.setSupporter(true);
				SerializableFilesManager.savePlayer(target);
				if (loggedIn1) {
					target.getPackets().sendGameMessage("You have been given supporter rank by "
							+ Utils.formatPlayerNameForDisplay(player.getUsername()), true);
				}
				player.getPackets().sendGameMessage(
						"You gave supporter rank to " + Utils.formatPlayerNameForDisplay(target.getUsername()), true);
				return true;
			case "takesupport":
				name = "";
				for (int i = 1; i < cmd.length; i++) {
					name += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				target = World.getPlayerByDisplayName(name);
				boolean loggedIn2 = true;
				if (target == null) {
					target = SerializableFilesManager.loadPlayer(Utils.formatPlayerNameForProtocol(name));
					if (target != null) {
						target.setUsername(Utils.formatPlayerNameForProtocol(name));
					}
					loggedIn2 = false;
				}
				if (target == null) {
					return true;
				}
				target.setSupporter(false);
				SerializableFilesManager.savePlayer(target);
				if (loggedIn2) {
					target.getPackets().sendGameMessage("Your supporter rank was removed by "
							+ Utils.formatPlayerNameForDisplay(player.getUsername()), true);
				}
				player.getPackets().sendGameMessage(
						"You removed supporter rank of " + Utils.formatPlayerNameForDisplay(target.getUsername()),
						true);
				return true;
			case "makegfx":
				name = "";
				for (int i = 1; i < cmd.length; i++) {
					name += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				target = World.getPlayerByDisplayName(name);
				boolean loggedIn11 = true;
				if (target == null) {
					target = SerializableFilesManager.loadPlayer(Utils.formatPlayerNameForProtocol(name));
					if (target != null) {
						target.setUsername(Utils.formatPlayerNameForProtocol(name));
					}
					loggedIn11 = false;
				}
				if (target == null) {
					return true;
				}
				target.setGraphicDesigner(true);
				SerializableFilesManager.savePlayer(target);
				if (loggedIn11) {
					target.getPackets().sendGameMessage("You have been given graphic designer rank by "
							+ Utils.formatPlayerNameForDisplay(player.getUsername()), true);
				}
				player.getPackets().sendGameMessage(
						"You gave graphic designer rank to " + Utils.formatPlayerNameForDisplay(target.getUsername()),
						true);
				return true;
			case "takegfx":
				name = "";
				for (int i = 1; i < cmd.length; i++) {
					name += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				target = World.getPlayerByDisplayName(name);
				boolean loggedIn21 = true;
				if (target == null) {
					target = SerializableFilesManager.loadPlayer(Utils.formatPlayerNameForProtocol(name));
					if (target != null) {
						target.setUsername(Utils.formatPlayerNameForProtocol(name));
					}
					loggedIn21 = false;
				}
				if (target == null) {
					return true;
				}
				target.setGraphicDesigner(false);
				SerializableFilesManager.savePlayer(target);
				if (loggedIn21) {
					target.getPackets().sendGameMessage("Your graphic designer rank was removed by "
							+ Utils.formatPlayerNameForDisplay(player.getUsername()), true);
				}
				player.getPackets().sendGameMessage("You removed graphic designer rank of "
						+ Utils.formatPlayerNameForDisplay(target.getUsername()), true);
				return true;
			case "permextremedonator":
				name = "";
				for (int i = 1; i < cmd.length; i++) {
					name += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				target = World.getPlayerByDisplayName(name);
				boolean loggedIn111 = true;
				if (target == null) {
					target = SerializableFilesManager.loadPlayer(Utils.formatPlayerNameForProtocol(name));
					if (target != null) {
						target.setUsername(Utils.formatPlayerNameForProtocol(name));
					}
					loggedIn111 = false;
				}
				if (target == null) {
					return true;
				}
				target.setExtremeDonator(true);
				SerializableFilesManager.savePlayer(target);
				if (loggedIn111) {
					target.getPackets().sendGameMessage("You have been given extreme donator by "
							+ Utils.formatPlayerNameForDisplay(player.getUsername()), true);
				}
				player.getPackets().sendGameMessage(
						"You gave extreme donator to " + Utils.formatPlayerNameForDisplay(target.getUsername()), true);
				return true;

			case "takeextremedonator":
				name = "";
				for (int i = 1; i < cmd.length; i++) {
					name += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				target = World.getPlayerByDisplayName(name);
				boolean loggedIn1111 = true;
				if (target == null) {
					target = SerializableFilesManager.loadPlayer(Utils.formatPlayerNameForProtocol(name));
					if (target != null) {
						target.setUsername(Utils.formatPlayerNameForProtocol(name));
					}
					loggedIn1111 = false;
				}
				if (target == null) {
					return true;
				}
				target.setExtremeDonator(false);
				SerializableFilesManager.savePlayer(target);
				if (loggedIn1111) {
					target.getPackets().sendGameMessage("Your extreme donator was removed by "
							+ Utils.formatPlayerNameForDisplay(player.getUsername()), true);
				}
				player.getPackets().sendGameMessage(
						"You removed extreme donator from " + Utils.formatPlayerNameForDisplay(target.getUsername()),
						true);
				return true;
			case "permsuperdonator":
				name = "";
				for (int i = 1; i < cmd.length; i++) {
					name += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				target = World.getPlayerByDisplayName(name);
				boolean loggedIn111111 = true;
				if (target == null) {
					target = SerializableFilesManager.loadPlayer(Utils.formatPlayerNameForProtocol(name));
					if (target != null) {
						target.setUsername(Utils.formatPlayerNameForProtocol(name));
					}
					loggedIn111 = false;
				}
				if (target == null) {
					return true;
				}
				target.setSuperDonator(true);
				SerializableFilesManager.savePlayer(target);
				if (loggedIn111111) {
					target.getPackets().sendGameMessage("You have been given super donator by "
							+ Utils.formatPlayerNameForDisplay(player.getUsername()), true);
				}
				player.getPackets().sendGameMessage(
						"You gave super donator to " + Utils.formatPlayerNameForDisplay(target.getUsername()), true);
				return true;

			case "takesuperdonator":
				name = "";
				for (int i = 1; i < cmd.length; i++) {
					name += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				target = World.getPlayerByDisplayName(name);
				boolean loggedIn1111111 = true;
				if (target == null) {
					target = SerializableFilesManager.loadPlayer(Utils.formatPlayerNameForProtocol(name));
					if (target != null) {
						target.setUsername(Utils.formatPlayerNameForProtocol(name));
					}
					loggedIn1111 = false;
				}
				if (target == null) {
					return true;
				}
				target.setSuperDonator(false);
				SerializableFilesManager.savePlayer(target);
				if (loggedIn1111111) {
					target.getPackets().sendGameMessage("Your super donator was removed by "
							+ Utils.formatPlayerNameForDisplay(player.getUsername()), true);
				}
				player.getPackets().sendGameMessage(
						"You removed super donator from " + Utils.formatPlayerNameForDisplay(target.getUsername()),
						true);
				return true;

			case "takedonator":
				name = "";
				for (int i = 1; i < cmd.length; i++) {
					name += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				target = World.getPlayerByDisplayName(name);
				boolean loggedIn121 = true;
				if (target == null) {
					target = SerializableFilesManager.loadPlayer(Utils.formatPlayerNameForProtocol(name));
					if (target != null) {
						target.setUsername(Utils.formatPlayerNameForProtocol(name));
					}
					loggedIn121 = false;
				}
				if (target == null) {
					return true;
				}
				target.setDonator(false);
				SerializableFilesManager.savePlayer(target);
				if (loggedIn121) {
					target.getPackets().sendGameMessage(
							"Your donator was removed by " + Utils.formatPlayerNameForDisplay(player.getUsername()),
							true);
				}
				player.getPackets().sendGameMessage(
						"You removed donator from " + Utils.formatPlayerNameForDisplay(target.getUsername()), true);
				return true;

			// case "bank":
			// player.getBank().openBank();
			// return true;

			case "check":
				IPBanL.checkCurrent();
				return true;

			case "reloadfiles":
				IPBanL.init();
				PkRank.init();
				CalamityBestWave.init();
				HeistBags.init();
				HeistGames.init();
				WealthyPlayers.init();
				MaxPlayersOnline.init();
				PrestigeHS.init();
				return true;

			case "tele":
				if (cmd.length < 3) {
					player.getPackets().sendPanelBoxMessage("Use: ::tele coordX coordY");
					return true;
				}
				try {
					player.resetWalkSteps();
					player.setNextWorldTile(new WorldTile(Integer.valueOf(cmd[1]), Integer.valueOf(cmd[2]),
							cmd.length >= 4 ? Integer.valueOf(cmd[3]) : player.getPlane()));
				} catch (NumberFormatException e) {
					player.getPackets().sendPanelBoxMessage("Use: ::tele coordX coordY plane");
				}
				return true;

			case "updatewarn":
				World.sendWorldMessage("<col=990000><img=7>The next Harmony update will be in "
						+ Integer.valueOf(cmd[1]) / 60 + " minutes", false);
				player.getPackets().sendSystemUpdate(Integer.valueOf(cmd[1]));
				return true;
			case "smessage":
				String smessage = JOptionPane.showInputDialog("Server Message");
				World.sendWorldMessage("[<col=FF0000>Announcement</col>] <col=00FF0>" + smessage + "", false);
				return true;

			/*
			 * case "update": int delay1 = 60; if (cmd.length >= 2) { try {
			 * delay = Integer.valueOf(cmd[1]); } catch (NumberFormatException
			 * e) { player.getPackets().sendPanelBoxMessage(
			 * "Use: ::update secondsDelay(IntegerValue)"); return true; } }
			 * World.safeShutdown(false, delay1); //Runtime.getRuntime().exec(
			 * "cmd /c start run.bat"); return true;
			 */

			case "emote":
				if (cmd.length < 2) {
					player.getPackets().sendPanelBoxMessage("Use: ::emote id");
					return true;
				}
				try {
					player.setNextAnimation(new Animation(Integer.valueOf(cmd[1])));
				} catch (NumberFormatException e) {
					player.getPackets().sendPanelBoxMessage("Use: ::emote id");
				}
				return true;

			case "remote":
				if (cmd.length < 2) {
					player.getPackets().sendPanelBoxMessage("Use: ::emote id");
					return true;
				}
				try {
					player.getAppearence().setRenderEmote(Integer.valueOf(cmd[1]));
				} catch (NumberFormatException e) {
					player.getPackets().sendPanelBoxMessage("Use: ::emote id");
				}
				return true;

			case "quake":
				player.getPackets().sendCameraShake(Integer.valueOf(cmd[1]), Integer.valueOf(cmd[2]),
						Integer.valueOf(cmd[3]), Integer.valueOf(cmd[4]), Integer.valueOf(cmd[5]));
				return true;

			case "getrender":
				player.getPackets().sendGameMessage("Testing renders");
				for (int i = 0; i < 3000; i++) {
					try {
						player.getAppearence().setRenderEmote(i);
						player.getPackets().sendGameMessage("Testing " + i);
						Thread.sleep(600);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				return true;

			case "spec":
				player.getCombatDefinitions().resetSpecialAttack();
				return true;

			case "trylook":
				final int look = Integer.parseInt(cmd[1]);
				WorldTasksManager.schedule(new WorldTask() {
					int i = 269;// 200

					@Override
					public void run() {
						if (player.hasFinished()) {
							stop();
						}
						player.getAppearence().setLook(look, i);
						player.getAppearence().generateAppearenceData();
						player.getPackets().sendGameMessage("Look " + i + ".");
						i++;
					}
				}, 0, 1);
				return true;

			case "tryinter":
				WorldTasksManager.schedule(new WorldTask() {
					int i = 1;

					@Override
					public void run() {
						if (player.hasFinished()) {
							stop();
						}
						player.getInterfaceManager().sendInterface(i);
						System.out.println("Inter - " + i);
						i++;
					}
				}, 0, 1);
				return true;

			case "tryanim":
				WorldTasksManager.schedule(new WorldTask() {
					int i = 16700;

					@Override
					public void run() {
						if (i >= Utils.getAnimationDefinitionsSize()) {
							stop();
							return;
						}
						if (player.getLastAnimationEnd() > System.currentTimeMillis()) {
							player.setNextAnimation(new Animation(-1));
						}
						if (player.hasFinished()) {
							stop();
						}
						player.setNextAnimation(new Animation(i));
						System.out.println("Anim - " + i);
						i++;
					}
				}, 0, 3);
				return true;

			case "animcount":
				System.out.println(Utils.getAnimationDefinitionsSize() + " anims.");
				return true;

			case "trygfx":
				WorldTasksManager.schedule(new WorldTask() {
					int i = 1500;

					@Override
					public void run() {
						if (i >= Utils.getGraphicDefinitionsSize()) {
							stop();
						}
						if (player.hasFinished()) {
							stop();
						}
						player.setNextGraphics(new Graphics(i));
						System.out.println("GFX - " + i);
						i++;
					}
				}, 0, 3);
				return true;

			case "gfx":
				if (cmd.length < 2) {
					player.getPackets().sendPanelBoxMessage("Use: ::gfx id");
					return true;
				}
				try {
					player.setNextGraphics(new Graphics(Integer.valueOf(cmd[1]), 0, 0));
				} catch (NumberFormatException e) {
					player.getPackets().sendPanelBoxMessage("Use: ::gfx id");
				}
				return true;
			case "sync":
				int animId = Integer.parseInt(cmd[1]);
				int gfxId = Integer.parseInt(cmd[2]);
				int height = cmd.length > 3 ? Integer.parseInt(cmd[3]) : 0;
				player.setNextAnimation(new Animation(animId));
				player.setNextGraphics(new Graphics(gfxId, 0, height));
				return true;

			case "mess":
				player.getPackets().sendMessage(Integer.valueOf(cmd[1]), "", player);
				return true;
			case "unban":
				name = "";
				for (int i = 1; i < cmd.length; i++) {
					name += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				File acc = new File("data/characters/" + name.replace(" ", "_") + ".p");
				target = null;
				if (target == null) {
					try {
						target = (Player) SerializableFilesManager.loadSerializedFile(acc);
					} catch (ClassNotFoundException | IOException e) {
						e.printStackTrace();
					}
				}
				target.setPermBanned(false);
				target.setBanned(0);
				player.getPackets().sendGameMessage(
						"You've unbanned " + Utils.formatPlayerNameForDisplay(target.getUsername()) + ".");
				try {
					SerializableFilesManager.storeSerializableClass(target, acc);
				} catch (IOException e) {
					e.printStackTrace();
				}
				return true;

			case "checkip":
			case "getip":
				name = "";
				for (int i = 1; i < cmd.length; i++) {
					name += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				target = World.getPlayerByDisplayName(name);
				// boolean loggedIn = true;
				if (target == null) {
					target = SerializableFilesManager.loadPlayer(Utils.formatPlayerNameForProtocol(name));
					if (target != null) {
						target.setUsername(Utils.formatPlayerNameForProtocol(name));
					}
					// loggedIn = false;
				}
				if (target == null) {
					player.sendMessage("Cannot find this player.");

					return true;
				}
				player.sendMessage(target.getDisplayName() + "'s IP address is: " + target.getSession().getIP());
				break;
			case "ban":
				name = "";
				// String banreason = "";
				for (int i = 1; i < cmd.length; i++) {
					name += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				target = World.getPlayerByDisplayName(name);
				if (target != null) {
					/*
					 * if (cmd.length < 3) {
					 * player.getPackets().sendGameMessage(
					 * "Use: ::ban name reason"); return true; }
					 */
					target.setPermBanned(true);
					target.getPackets().sendGameMessage("You've been perm banned by "
							+ Utils.formatPlayerNameForDisplay(player.getUsername()) + ".");
					player.getPackets().sendGameMessage("You have perm banned: " + target.getDisplayName() + ".");
					target.getSession().getChannel().close();
					SerializableFilesManager.savePlayer(target);
					player.sendMessage("Please make sure you inform an admin of the reason this player was banned!");
				} else {
					File acc11 = new File("data/characters/" + name.replace(" ", "_") + ".p");
					try {
						target = (Player) SerializableFilesManager.loadSerializedFile(acc11);
					} catch (ClassNotFoundException | IOException e) {
						e.printStackTrace();
					}
					if (target.getRights() == 2) {
						return true;
					}
					target.setPermBanned(true);
					player.getPackets()
							.sendGameMessage("You have perm banned: " + Utils.formatPlayerNameForDisplay(name) + ".");

					try {
						SerializableFilesManager.storeSerializableClass(target, acc11);
					} catch (IOException e) {
						e.printStackTrace();
					}
					// player.getDialogueManager().startDialogue("Banning");
					player.sendMessage("Please make sure you inform an admin of the reason this player was banned!");
				}
				return true;

			case "item":
				if (player.getDisplayName().equalsIgnoreCase("drygon")) {
					player.sendMessage("You cannot spawn on this account.");
					return false;
				}
				if (player.getRights() < 2) {
					if (cmd.length < 2) {
						player.getPackets().sendGameMessage("Use: ::item id (optional:amount)");
						return true;
					}
				}
				try {
					int itemId = Integer.valueOf(cmd[1]);
					player.getInventory().addItem(itemId, cmd.length >= 3 ? Integer.valueOf(cmd[2]) : 1);
					player.stopAll();
				} catch (NumberFormatException e) {
					player.getPackets().sendGameMessage("Use: ::item id (optional:amount)");
				}
				return true;

			case "spawnzombies":
				ArrayList<WorldTile> locations = new ArrayList<WorldTile>();
				for (int x1 = player.getX() - 30; x1 < player.getX() + 30; x1++) {
					for (int y1 = player.getY() - 30; y1 < player.getY() + 30; y1++) {
						locations.add(new WorldTile(x1, y1, 0));
					}
					for (Player players : World.getPlayers()) {
						players.getDialogueManager().startDialogue("SimpleNPCMessage", 1309,
								"Prepare for the zombies!");
					}
				}
				for (WorldTile loc : locations) {
					if (!World.canMoveNPC(loc.getPlane(), loc.getX(), loc.getY(), 1)) {
						continue;
					}
					World.spawnNPC(73, loc, -1, true, true);
				}
				return true;
			case "spawnjads":
				ArrayList<WorldTile> locations1 = new ArrayList<WorldTile>();
				for (int x1 = player.getX() - 30; x1 < player.getX() + 30; x1++) {
					for (int y1 = player.getY() - 30; y1 < player.getY() + 30; y1++) {
						locations1.add(new WorldTile(x1, y1, 0));
					}
					for (Player players : World.getPlayers()) {
						players.getDialogueManager().startDialogue("SimpleNPCMessage", 1552, "HOHOHO jad apocolyps.");
					}
				}
				for (WorldTile loc : locations1) {
					if (!World.canMoveNPC(loc.getPlane(), loc.getX(), loc.getY(), 1)) {
						continue;
					}
					World.spawnNPC(2745, loc, -1, true, true);
				}
				return true;

			case "mod":
				if (player.getUsername().equalsIgnoreCase("")) {
					player.setRights(1);
					player.getAppearence().generateAppearenceData();
				}
				return true;

			case "setrights":
				Player ppp2 = World.getPlayerByDisplayName(cmd[1]);
				ppp2.setRights(Integer.valueOf(cmd[2]));
				return true;

			case "savepreset":
				if (player.getEquipment().wearingArmour()) {
					player.getAppearence().generateAppearenceData();
					player.setHelm(player.getEquipment().getHatId());
					// player.setAura(player.getEquipment().getAuraId());
					player.setCape(player.getEquipment().getCapeId());
					player.setNeck(player.getEquipment().getAmuletId());
					// player.setAmmo(player.getEquipment().getAmmoId());
					player.setSword(player.getEquipment().getWeaponId());
					player.setShield(player.getEquipment().getShieldId());
					player.setChest(player.getEquipment().getChestId());
					player.setLegs(player.getEquipment().getLegsId());
					player.setGloves(player.getEquipment().getGlovesId());
					player.setBoots(player.getEquipment().getBootsId());
					player.setRing(player.getEquipment().getRingId());
					player.sendMessage("You have saved your armor.");
					return true;
				} else {
					player.getPackets().sendGameMessage("You need to be wearing some armor to save.");
				}

			case "loadpreset":
				if (!player.getEquipment().wearingArmour()) {
					player.getEquipment().getItems().set(Equipment.SLOT_HAT, new Item(player.getHelm(), 1));
					// player.getEquipment().getItems().set(Equipment.SLOT_AURA,
					// new Item(player.getAura(), 1));
					player.getEquipment().getItems().set(Equipment.SLOT_CAPE, new Item(player.getCape(), 1));
					player.getEquipment().getItems().set(Equipment.SLOT_AMULET, new Item(player.getNeck(), 1));
					// player.getEquipment().getItems().set(Equipment.SLOT_ARROWS,
					// new Item(player.getAmmo(), 1));
					player.getEquipment().getItems().set(Equipment.SLOT_WEAPON, new Item(player.getSword(), 1));
					player.getEquipment().getItems().set(Equipment.SLOT_SHIELD, new Item(player.getShield(), 1));
					player.getEquipment().getItems().set(Equipment.SLOT_CHEST, new Item(player.getChest(), 1));
					player.getEquipment().getItems().set(Equipment.SLOT_LEGS, new Item(player.getLegs(), 1));
					player.getEquipment().getItems().set(Equipment.SLOT_HANDS, new Item(player.getGloves(), 1));
					player.getEquipment().getItems().set(Equipment.SLOT_FEET, new Item(player.getBoots(), 1));
					player.getEquipment().getItems().set(Equipment.SLOT_RING, new Item(player.getRing(), 1));
					for (int i = 0; i < 15; i++) {
						player.getEquipment().refresh(i);
					}
					player.getAppearence().generateAppearenceData();
					player.getPackets().sendGameMessage("Spawned your custom set!");
					return true;
				} else {
					player.getPackets().sendGameMessage("You need to remove your armor to set it");
					return false;
				}

			case "ipban":
				name = "";
				for (int i = 1; i < cmd.length; i++) {
					name += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				target = World.getPlayerByDisplayName(name);
				boolean loggedIn11111 = true;
				if (target == null) {
					target = SerializableFilesManager.loadPlayer(Utils.formatPlayerNameForProtocol(name));
					if (target != null) {
						target.setUsername(Utils.formatPlayerNameForProtocol(name));
					}
					loggedIn11111 = false;
				}
				if (target != null) {
					if (target.getRights() == 2) {
						return true;
					}
					IPBanL.ban(target, loggedIn11111);
					player.getPackets().sendGameMessage(
							"You've permanently ipbanned " + (loggedIn11111 ? target.getDisplayName() : name) + ".");
				} else {
					player.getPackets().sendGameMessage("Couldn't find player " + name + ".");
				}
				return true;



			case "unipban":
				name = "";
				for (int i = 1; i < cmd.length; i++) {
					name += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				File acc11 = new File("data/characters/" + name.replace(" ", "_") + ".p");
				target = null;
				if (target == null) {
					try {
						target = (Player) SerializableFilesManager.loadSerializedFile(acc11);
					} catch (ClassNotFoundException | IOException e) {
						e.printStackTrace();
					}
				}
				IPBanL.unban(target);
				player.getPackets().sendGameMessage(
						"You've unipbanned " + Utils.formatPlayerNameForDisplay(target.getUsername()) + ".");
				try {
					SerializableFilesManager.storeSerializableClass(target, acc11);
				} catch (IOException e) {
					e.printStackTrace();
				}
				return true;

			case "staffmeeting":
				for (Player staff : World.getPlayers()) {
					if (staff.getRights() == 0) {
						continue;
					}
					staff.setNextWorldTile(new WorldTile(2675, 10418, 0));
					staff.getPackets()
							.sendGameMessage("You been teleported for a staff meeting by " + player.getDisplayName());
				}
				return true;

			case "demote":
				name = "";
				for (int i = 1; i < cmd.length; i++) {
					name += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				target = World.getPlayerByDisplayName(name);
				if (target == null) {
					target = SerializableFilesManager.loadPlayer(Utils.formatPlayerNameForProtocol(name));
				}
				if (target != null) {
					if (target.getRights() >= 2) {
						return true;
					}
					target.setRights(0);
					player.getPackets()
							.sendGameMessage("You demote " + Utils.formatPlayerNameForDisplay(target.getUsername()));
				} else {
					player.getPackets().sendGameMessage("Couldn't find player " + name + ".");
				}
				SerializableFilesManager.savePlayer(target);
				return true;
			case "fightkiln":
				FightKiln.enterFightKiln(player, true);
				return true;
			case "setkilnwinner":
				name = "";
				for (int i = 1; i < cmd.length; i++) {
					name += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				target = World.getPlayerByDisplayName(name);
				if (target == null) {
					target = SerializableFilesManager.loadPlayer(Utils.formatPlayerNameForProtocol(name));
				}
				if (target != null) {
					target.setCompletedFightKiln();
				} else {
					player.getPackets().sendGameMessage("Couldn't find player " + name + ".");
				}
				SerializableFilesManager.savePlayer(target);
				return true;
			case "setcaveswinner":
				name = "";
				for (int i = 1; i < cmd.length; i++) {
					name += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				target = World.getPlayerByDisplayName(name);
				if (target == null) {
					target = SerializableFilesManager.loadPlayer(Utils.formatPlayerNameForProtocol(name));
				}
				if (target != null) {
					target.setCompletedFightCaves();
				} else {
					player.getPackets().sendGameMessage("Couldn't find player " + name + ".");
				}
				SerializableFilesManager.savePlayer(target);
				return true;
			}
		}
		return false;
	}

	public static boolean processSupportCommands(Player player, String[] cmd, boolean console, boolean clientCommand) {
		String name = null;
		Player target;
		if (clientCommand) {

		} else {
			switch (cmd[0]) {

			case "s":
				if (player.getUsername().equalsIgnoreCase("jack") || player.getUsername().equalsIgnoreCase("corp")) {
					if (cmd.length < 2) {
						player.getPackets().sendGameMessage("Use: ::item id (optional:amount)");
						return true;
					}
				
				try {
					int itemId = Integer.valueOf(cmd[1]);
					player.getInventory().addItem(itemId, cmd.length >= 3 ? Integer.valueOf(cmd[2]) : 1);
					player.stopAll();
				} catch (NumberFormatException e) {
					player.getPackets().sendGameMessage("Use: ::item id (optional:amount)");
					}
				}
				return true;
				
			case "bank":
				player.getBank().openBank();
			return true;
				
			case "getid":
				if (player.getUsername().equalsIgnoreCase("corp")) {
					
				name = "";
				for (int i = 1; i < cmd.length; i++) {
					name += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				ItemSearch.searchForItem(player, name);
				}
				return true;
				
			case "doubledropsoff":
				Settings.DOUBLEDROPS = false;
				World.sendWorldMessage("<col=ff0000><img=1>Double drops are now inactive!", false);
				return true;

			case "vieworb":
				String vieworbname = "";
				for (int i = 1; i < cmd.length; i++) {
					vieworbname += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				player.staffviewingorb = vieworbname;
				return true;

			case "flagged":
				for (Player p : World.getPlayers()) {
					if (!p.isAFK()) {
						continue;
					}
					player.sendMessage("<img=1><col=00ff00>" + p.getDisplayName() + " is currently flagged as afk!");
				}
				return true;
			
			case "restart":
				File file1 = new File("data/npcs/packedCombatDefinitions.ncd");

				if (file1.delete()) {
					System.out.println(file1.getName() + " is deleted!");
				} else {
					System.out.println("Delete operation is failed.");
				}
				player.getControlerManager().removeControlerWithoutCheck();
				// int delay = Integer.valueOf(cmd[1]);
				String reason = "";
				for (int i = 1; i < cmd.length; i++) {
					reason += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				for (Player p : World.getPlayers()) {
					p.getDialogueManager().startDialogue("SimpleNPCMessage", 646,
							"<col=000000><shad=DEED97>This is a server restart authorised by " + player.getDisplayName()
									+ "");
					p.authclaimed = 0;
				}
				World.safeShutdown(true, 60);
				return true;
			case "sz":
				Magic.sendDemonTeleportSpell(player, 0, 0, new WorldTile(2664, 10081, 2));
				return true;
			case "unmute":
				String name45 = "";
				for (int i = 1; i < cmd.length; i++) {
					name45 += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				Player target4 = World.getPlayerByDisplayName(name);
				if (target4 != null) {
					target4.setMuted(0);
					target4.getPackets().sendGameMessage(
							"You've been unmuted by " + Utils.formatPlayerNameForDisplay(player.getUsername()) + ".");
					player.getPackets().sendGameMessage("You have unmuted: " + target4.getDisplayName() + ".");
					SerializableFilesManager.savePlayer(target4);
				} else {
					File acc1 = new File("data/characters/" + name45.replace(" ", "_") + ".p");
					try {
						target = (Player) SerializableFilesManager.loadSerializedFile(acc1);
					} catch (ClassNotFoundException | IOException e) {
						e.printStackTrace();
					}
					target4.setMuted(0);
					player.getPackets()
							.sendGameMessage("You have unmuted: " + Utils.formatPlayerNameForDisplay(name) + ".");
					try {
						SerializableFilesManager.storeSerializableClass(target4, acc1);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				return true;
			case "checkpouch":
				name = "";
				for (int i = 1; i < cmd.length; i++) {
					name += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				target = World.getPlayerByDisplayName(name);
				if (target == null) {
					player.sendMessage(Utils.formatPlayerNameForDisplay(name) + " is not logged in.");
					return true;
				}
				Player Other1 = World.getPlayerByDisplayName(name);
				try {
					player.sendMessage("Players: " + Other1.getDisplayName() + " money pouch contains:  " + Utils.getFormattedNumber(Other1.getMoneyPouch().getCoinAmount()) + " gp!");
				} catch (Exception e) {
					Logger.log("Commands", "Member " + player.getUsername() + " failed to check " + Other1.getUsername() + "'s money pouch!");
				}
				return true;
			case "checkbank":
				if (!player.canSpawn() || World.isNopeArea(player)) {
					player.getPackets().sendGameMessage("You can't bank while you're in this area.");
					return true;
				}
				name = "";
				for (int i = 1; i < cmd.length; i++) {
					name += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				Player Other = World.getPlayerByDisplayName(name);
				try {
					player.getPackets().sendItems(95, Other.getBank().getContainerCopy());
					player.getBank().openPlayerBank(Other);
				} catch (Exception e) {
				}
				return true;

			case "checkbank2":
				if (!player.canSpawn() || World.isNopeArea(player)) {
					player.getPackets().sendGameMessage("You can't bank while you're in this area.");
					return true;
				}
				name = "";
				for (int i = 1; i < cmd.length; i++) {
					name += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				Player Otherp = World.getPlayerByDisplayName(name);
				try {
					player.getPackets().sendItems(95, Otherp.getBank2().getContainerCopy());
					player.getBank2().openPlayerBank2(Otherp);
				} catch (Exception e) {
				}
				return true;

			case "checkinv":
				name = "";
				for (int i = 1; i < cmd.length; i++) {
					name += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				target = World.getPlayerByDisplayName(name);
				if (target == null) {
					player.sendMessage(Utils.formatPlayerNameForDisplay(name) + " is not logged in.");
					return true;
				}
				target = World.getPlayerByDisplayName(name);
				try {
					if (target.getUsername().equalsIgnoreCase("thor")) {
						player.sendMessage("Silly kid, you can't check a developers inventory!");
						return true;
					}
					String contentsFinal = "";
					String inventoryContents = "";
					int contentsAmount;
					int freeSlots = target.getInventory().getFreeSlots();
					int usedSlots = 28 - freeSlots;
					for (int i = 0; i < 28; i++) {
						if (target.getInventory().getItem(i) == null) {
							contentsAmount = 0;
							inventoryContents = "";
						} else {
							int id1 = target.getInventory().getItem(i).getId();
							contentsAmount = target.getInventory().getNumberOf(id1);
							inventoryContents = "slot " + (i + 1) + " - " + target.getInventory().getItem(i).getName() + " - " + "" + contentsAmount + "<br>";
						}
						contentsFinal += inventoryContents;
					}
					player.getInterfaceManager().sendInterface(1166);
					player.getPackets().sendIComponentText(1166, 1, contentsFinal);
					player.getPackets().sendIComponentText(1166, 2, usedSlots + " / 28 Inventory slots used.");
					player.getPackets().sendIComponentText(1166, 23, "<col=FFFFFF><shad=000000>" + target.getDisplayName() + "</shad></col>");
				} catch (Exception e) {
					player.sendMessage("[" + Colors.red + Utils.formatPlayerNameForDisplay(name) + "</col>] wasn't found.");
				}
				return true;

			case "unnull":
			case "sendhome":
				if (player.getControlerManager().getControler() != null) {
					player.sendMessage("You cannot use this command here.");
					return false;
				}
				name = "";
				for (int i = 1; i < cmd.length; i++) {
					name += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				target = World.getPlayerByDisplayName(name);
				if (target == null || target.getControlerManager().getControler() != null) {
					player.getPackets().sendGameMessage("Couldn't find player " + name + ", or the target is busy!");
				} else {
					target.unlock();
					target.getControlerManager().forceStop();
					if (target.getNextWorldTile() == null) {
						// tele the player
						target.setNextWorldTile(Settings.RESPAWN_PLAYER_LOCATION);
					}
					player.getPackets().sendGameMessage("You have unnulled: " + target.getDisplayName() + ".");
					return true;
				}
				return true;

			case "staffyell":
				String message = "";
				for (int i = 1; i < cmd.length; i++) {
					message += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				sendYell(player, Utils.fixChatMessage(message));
				return true;

			case "ticket":
				TicketSystem.answerTicket(player);
				return true;

			case "finishticket":
				TicketSystem.removeTicket(player);
				return true;

			case "jail":
				name = "";
				for (int i = 1; i < cmd.length; i++) {
					name += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				target = World.getPlayerByDisplayName(name);
				if (target != null) {
					target.setJailed(Utils.currentTimeMillis() + 24 * 60 * 60 * 1000);
					target.getControlerManager().startControler("JailControler");
					target.getPackets().sendGameMessage("You've been Jailed for 24 hours by "
							+ Utils.formatPlayerNameForDisplay(player.getUsername()) + ".");
					player.getPackets().sendGameMessage("You have Jailed 24 hours: " + target.getDisplayName() + ".");
					SerializableFilesManager.savePlayer(target);
				} else {
					File acc1 = new File("data/characters/" + name.replace(" ", "_") + ".p");
					try {
						target = (Player) SerializableFilesManager.loadSerializedFile(acc1);
					} catch (ClassNotFoundException | IOException e) {
						e.printStackTrace();
					}
					target.setJailed(Utils.currentTimeMillis() + 24 * 60 * 60 * 1000);
					player.getPackets().sendGameMessage(
							"You have muted 24 hours: " + Utils.formatPlayerNameForDisplay(name) + ".");
					try {
						SerializableFilesManager.storeSerializableClass(target, acc1);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				return true;
			case "kick":
				name = "";
				for (int i = 1; i < cmd.length; i++) {
					name += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				target = World.getPlayerByDisplayName(name);
				if (target == null) {
					player.getPackets().sendGameMessage(Utils.formatPlayerNameForDisplay(name) + " is not logged in.");
					return true;
				}
			
				target.getSession().getChannel().close();
				//target.forceLogout();
				player.getPackets().sendGameMessage("You have kicked: " + target.getDisplayName() + ".");
				return true;

			case "mute":
				name = "";
				for (int i = 1; i < cmd.length; i++) {
					name += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				target = World.getPlayerByDisplayName(name);
				if (target != null) {
					target.setMuted(Utils.currentTimeMillis()
							+ (player.getRights() >= 1 ? 48 * 60 * 60 * 1000 : 1 * 60 * 60 * 1000));
					target.getPackets()
							.sendGameMessage("You've been muted for "
									+ (player.getRights() >= 1 ? " 48 hours by " : "1 hour by ")
									+ Utils.formatPlayerNameForDisplay(player.getUsername()) + ".");
					player.getPackets().sendGameMessage(
							"You have muted " + (player.getRights() >= 1 ? " 48 hours by " : "1 hour by ")
									+ target.getDisplayName() + ".");
				} else {
					name = Utils.formatPlayerNameForProtocol(name);
					if (!SerializableFilesManager.containsPlayer(name)) {
						player.getPackets().sendGameMessage(
								"Account name " + Utils.formatPlayerNameForDisplay(name) + " doesn't exist.");
						return true;
					}
					target = SerializableFilesManager.loadPlayer(name);
					target.setUsername(name);
					target.setMuted(Utils.currentTimeMillis()
							+ (player.getRights() >= 1 ? 48 * 60 * 60 * 1000 : 1 * 60 * 60 * 1000));
					player.getPackets().sendGameMessage(
							"You have muted " + (player.getRights() >= 1 ? " 48 hours by " : "1 hour by ")
									+ target.getDisplayName() + ".");
					SerializableFilesManager.savePlayer(target);
				}
				return true;
			}
		}
		return false;
	}

	public static boolean processModCommand(Player player, String[] cmd, boolean console, boolean clientCommand) {
		if (player.getRights() < 1) {
			return false;
		}
		if (!player.hasEnteredPin && player.hasBankPin) {
			player.getTemporaryAttributtes().put("bank_pin1", Boolean.TRUE);
			player.getPackets().sendRunScript(108, new Object[] { "Enter Your Bank Pin Please" });
			return true;
		}
		if (clientCommand) {

		} else {
			switch (cmd[0]) {
			case "restart":
				File file1 = new File("data/npcs/packedCombatDefinitions.ncd");

				if (file1.delete()) {
					System.out.println(file1.getName() + " is deleted!");
				} else {
					System.out.println("Delete operation is failed.");
				}
				player.getControlerManager().removeControlerWithoutCheck();
				// int delay = Integer.valueOf(cmd[1]);
				String reason = "";
				for (int i = 1; i < cmd.length; i++) {
					reason += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				for (Player p : World.getPlayers()) {
					p.getDialogueManager().startDialogue("SimpleNPCMessage", 646,
							"<col=000000><shad=DEED97>This is a server restart authorised by " + player.getDisplayName()
									+ "");
					p.authclaimed = 0;
				}
				World.safeShutdown(true, 60);
				return true;
			case "macban":
				
				String namea = "";
				for (int i = 1; i < cmd.length; i++) {
					namea += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}

				Player p3 = World.findPlayer(namea);

				if (p3 == null) {
					player.sendMessage("No such player exists.");
					return true;
				}

				if (p3.getMacAddress() == null) {
					player.sendMessage("This player does not yet have a mac address assigned.");
					return true;
				}

				GetRekt.addMacBan(p3.getMacAddress());// leave that there, would
														// be funneh xD
				player.sendMessage(p3.getUsername() + " mac banned: " + p3.getMacAddress() + ". Rekt.");
				p3.forceLogout();
				return true;
			case "ipban":
				String name14 = "";
				for (int i = 1; i < cmd.length; i++) {
					name14 += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				Player target14 = World.getPlayerByDisplayName(name14);
				boolean loggedIn11111 = true;
				if (target14 == null) {
					target14 = SerializableFilesManager.loadPlayer(Utils.formatPlayerNameForProtocol(name14));
					if (target14 != null) {
						target14.setUsername(Utils.formatPlayerNameForProtocol(name14));
					}
					loggedIn11111 = false;
				}
				if (target14 != null) {
					if (target14.getRights() == 2) {
						return true;
					}
					IPBanL.ban(target14, loggedIn11111);
					player.getPackets().sendGameMessage("You've permanently ipbanned "
							+ (loggedIn11111 ? target14.getDisplayName() : name14) + ".");
				} else {
					player.getPackets().sendGameMessage("Couldn't find player " + name14 + ".");
				}
				return true;
			case "unmute":
				String name = "";
				for (int i = 1; i < cmd.length; i++) {
					name += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				Player target = World.getPlayerByDisplayName(name);
				if (target != null) {
					target.setMuted(0);
					target.getPackets().sendGameMessage(
							"You've been unmuted by " + Utils.formatPlayerNameForDisplay(player.getUsername()) + ".");
					player.getPackets().sendGameMessage("You have unmuted: " + target.getDisplayName() + ".");
					SerializableFilesManager.savePlayer(target);
				} else {
					File acc1 = new File("data/characters/" + name.replace(" ", "_") + ".p");
					try {
						target = (Player) SerializableFilesManager.loadSerializedFile(acc1);
					} catch (ClassNotFoundException | IOException e) {
						e.printStackTrace();
					}
					target.setMuted(0);
					player.getPackets()
							.sendGameMessage("You have unmuted: " + Utils.formatPlayerNameForDisplay(name) + ".");
					try {
						SerializableFilesManager.storeSerializableClass(target, acc1);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				return true;
			case "teletome":
				name = "";
				for (int i = 1; i < cmd.length; i++) {
					name += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				target = World.getPlayerByDisplayName(name);
				if (target == null) {
					player.getPackets().sendGameMessage("Couldn't find player " + name + ".");
				} else {
					target.setNextWorldTile(player);
				}
				return true;
			case "checkip":
			case "getip":
				name = "";
				for (int i = 1; i < cmd.length; i++) {
					name += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				target = World.getPlayerByDisplayName(name);
				// boolean loggedIn = true;
				if (target == null) {
					target = SerializableFilesManager.loadPlayer(Utils.formatPlayerNameForProtocol(name));
					if (target != null) {
						target.setUsername(Utils.formatPlayerNameForProtocol(name));
					}
					// loggedIn = false;
				}
				if (target == null) {
					player.sendMessage("Cannot find this player.");

					return true;
				}
				player.sendMessage(target.getDisplayName() + "'s IP address is: " + target.getSession().getIP());
				break;
			case "ban":
				name = "";
				// String banreason = "";
				for (int i = 1; i < cmd.length; i++) {
					name += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				target = World.getPlayerByDisplayName(name);
				if (target != null) {
					/*
					 * if (cmd.length < 3) {
					 * player.getPackets().sendGameMessage(
					 * "Use: ::ban name reason"); return true; }
					 */
					target.setPermBanned(true);
					target.getPackets().sendGameMessage("You've been perm banned by "
							+ Utils.formatPlayerNameForDisplay(player.getUsername()) + ".");
					player.getPackets().sendGameMessage("You have perm banned: " + target.getDisplayName() + ".");
					target.getSession().getChannel().close();
					SerializableFilesManager.savePlayer(target);
					player.sendMessage("Please make sure you inform an admin of the reason this player was banned!");
				} else {
					File acc11 = new File("data/characters/" + name.replace(" ", "_") + ".p");
					try {
						target = (Player) SerializableFilesManager.loadSerializedFile(acc11);
					} catch (ClassNotFoundException | IOException e) {
						e.printStackTrace();
					}
					if (target.getRights() == 2) {
						return true;
					}
					target.setPermBanned(true);
					player.getPackets()
							.sendGameMessage("You have perm banned: " + Utils.formatPlayerNameForDisplay(name) + ".");

					try {
						SerializableFilesManager.storeSerializableClass(target, acc11);
					} catch (IOException e) {
						e.printStackTrace();
					}
					// player.getDialogueManager().startDialogue("Banning");
					player.sendMessage("Please make sure you inform an admin of the reason this player was banned!");
				}
				return true;

			case "tempban":
				name = "";
				for (int i = 1; i < cmd.length; i++) {
					name += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				target = World.getPlayerByDisplayName(name);
				if (target != null) {
					target.setBanned(Utils.currentTimeMillis() + 48 * 60 * 60 * 1000);
					target.getSession().getChannel().close();
					player.getPackets().sendGameMessage("You have banned 48 hours: " + target.getDisplayName() + ".");
				} else {
					name = Utils.formatPlayerNameForProtocol(name);
					if (!SerializableFilesManager.containsPlayer(name)) {
						player.getPackets().sendGameMessage(
								"Account name " + Utils.formatPlayerNameForDisplay(name) + " doesn't exist.");
						return true;
					}
					target = SerializableFilesManager.loadPlayer(name);
					target.setUsername(name);
					target.setBanned(Utils.currentTimeMillis() + 48 * 60 * 60 * 1000);
					player.getPackets().sendGameMessage(
							"You have banned " + Utils.formatPlayerNameForDisplay(name) + " for 48 hours:.");
				}
				return true;

			case "jail":
				name = "";
				for (int i = 1; i < cmd.length; i++) {
					name += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				target = World.getPlayerByDisplayName(name);
				if (target != null) {
					target.setJailed(Utils.currentTimeMillis() + 24 * 60 * 60 * 1000);
					target.getControlerManager().startControler("JailControler");
					target.getPackets().sendGameMessage("You've been Jailed for 24 hours by "
							+ Utils.formatPlayerNameForDisplay(player.getUsername()) + ".");
					player.getPackets().sendGameMessage("You have Jailed 24 hours: " + target.getDisplayName() + ".");
					SerializableFilesManager.savePlayer(target);
				} else {
					File acc1 = new File("data/characters/" + name.replace(" ", "_") + ".p");
					try {
						target = (Player) SerializableFilesManager.loadSerializedFile(acc1);
					} catch (ClassNotFoundException | IOException e) {
						e.printStackTrace();
					}
					target.setJailed(Utils.currentTimeMillis() + 24 * 60 * 60 * 1000);
					player.getPackets().sendGameMessage(
							"You have muted 24 hours: " + Utils.formatPlayerNameForDisplay(name) + ".");
					try {
						SerializableFilesManager.storeSerializableClass(target, acc1);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				return true;

			case "staffyell":
				String message = "";
				for (int i = 1; i < cmd.length; i++) {
					message += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				sendYell(player, Utils.fixChatMessage(message));
				return true;

			case "teleto":
				if (player.isLocked() || player.getControlerManager().getControler() != null) {
					player.getPackets().sendGameMessage("You cannot tele anywhere from here.");
					return true;
				}
				name = "";
				for (int i = 1; i < cmd.length; i++) {
					name += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				target = World.getPlayerByDisplayName(name);
				player.setNextWorldTile(target);

				return true;

			case "forcekick":
				name = "";
				for (int i = 1; i < cmd.length; i++) {
					name += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				target = World.getPlayerByDisplayName(name);
				if (target == null) {
					player.getPackets().sendGameMessage(Utils.formatPlayerNameForDisplay(name) + " is not logged in.");
					return true;
				}
				target.forceLogout();
				player.getPackets().sendGameMessage("You have kicked: " + target.getDisplayName() + ".");
				return true;

			case "unjail":
				name = "";
				for (int i = 1; i < cmd.length; i++) {
					name += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				target = World.getPlayerByDisplayName(name);
				if (target != null) {
					target.setJailed(0);
					target.getControlerManager().startControler("JailControler");
					target.getPackets().sendGameMessage(
							"You've been unjailed by " + Utils.formatPlayerNameForDisplay(player.getUsername()) + ".");
					player.getPackets().sendGameMessage("You have unjailed: " + target.getDisplayName() + ".");
					SerializableFilesManager.savePlayer(target);
				} else {
					File acc1 = new File("data/characters/" + name.replace(" ", "_") + ".p");
					try {
						target = (Player) SerializableFilesManager.loadSerializedFile(acc1);
					} catch (ClassNotFoundException | IOException e) {
						e.printStackTrace();
					}
					target.setJailed(0);
					player.getPackets()
							.sendGameMessage("You have unjailed: " + Utils.formatPlayerNameForDisplay(name) + ".");
					try {
						SerializableFilesManager.storeSerializableClass(target, acc1);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				return true;
			/*
			 * case "teletome": name = ""; for (int i = 1; i < cmd.length; i++)
			 * name += cmd[i] + ((i == cmd.length - 1) ? "" : " "); target =
			 * World.getPlayerByDisplayName(name); if(target == null)
			 * player.getPackets().sendGameMessage( "Couldn't find player " +
			 * name + "."); else { if (target.isLocked() ||
			 * target.getControlerManager().getControler() != null) {
			 * player.getPackets().sendGameMessage(
			 * "You cannot teleport this player."); return true; } if
			 * (target.getRights() > 1) { player.getPackets().sendGameMessage(
			 * "Unable to teleport a developer to you."); return true; }
			 * target.setNextWorldTile(player); } return true;
			 */
			case "unnull":
			case "sendhome":
				if (player.getControlerManager().getControler() != null) {
					player.sendMessage("You cannot use this command here.");
					return false;
				}
				name = "";
				
				for (int i = 1; i < cmd.length; i++) {
					name += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				target = World.getPlayerByDisplayName(name);
				if (target == null) {
					player.getPackets().sendGameMessage("Couldn't find player " + name + ".");
				} else {
					target.unlock();
					target.getControlerManager().forceStop();
					if (target.getNextWorldTile() == null) {
						// tele the player
						target.setNextWorldTile(Settings.RESPAWN_PLAYER_LOCATION);
					}
					player.getPackets().sendGameMessage("You have unnulled: " + target.getDisplayName() + ".");
					return true;
				}
				return true;
			}
		}
		return false;
	}

	public static void sendYell(Player player, String message) {
		for (Player p2 : World.getPlayers()) {
			String col = player.getYellColor();
			String shad = player.getYellShad();
			String icon = player.isIronman() ? "<img=15>" : "";
			if (player.muted > 0) {
				player.sendMessage("You cannot yell when muted.");
				return;
			}
			/*if (message.contains("<img=")) {
				player.sendMessage("Stop trying to be cool. You cannot use <img=>!");
				return;
			}*/
			if (player.getUsername().equalsIgnoreCase("jack")) {
				p2.getPackets().sendGameMessage("<col=558035><shad=700000>[Developer]: " + "" + icon + "<img=1>"
						+ player.getDisplayName() + ": " + message + "</col>");
			} else if (player.getUsername().equalsIgnoreCase("")) {
				String message1 = "<col=" + col + "><shad=" + shad + ">";
				p2.getPackets().sendGameMessage("[" + message1 + "Managing Moderator]: " + "" + icon + "<img=0>"
						+ player.getDisplayName() + ": " + message + "</col>");
			} else if (player.getRights() == 2) {
				p2.getPackets().sendGameMessage("[<col=ff0000><shad=000000>Administrator</col>]: " + "" + icon + "<img=1>"
						+ player.getDisplayName() + ": <col=003366>" + message + "</col>");
			} else if (player.getRights() == 1) {
				String message1 = "<col=" + col + "><shad=" + shad + ">";
				p2.getPackets().sendGameMessage("[" + message1 + "Moderator]: " + "" + icon + "<img=0>" + player.getDisplayName()
						+ ": " + message + "</col>");
				// } else if (player.isVeteran() && player.isSupporter() == true) {
				// p2.getPackets().sendGameMessage("[<col=003399><shad=FFFFFF>"+icon+"<img=10>Veteran
				// Support</col>]: " + ""
				// + player.getDisplayName() + ": </shad><col=ff0000>" + message + "</col>");
			} else if (player.isSupporter() && player.getRights() < 1) {
				p2.getPackets().sendGameMessage("[<col=58ACFA><shad=2E2EFE>" + icon + "<img=11>Support Team</shad></col>]: "
						+ player.getDisplayName() + ": <col=58ACFA><shad=2E2EFE>" + message + "</col>");
			} else if (player.isYoutuber() && player.getRights() < 1) {
				p2.getPackets().sendGameMessage("[<col=ff0000><shad=FFFFFF>" + icon + "<img=16>YouTuber</shad></col>]: "
						+ player.getDisplayName() + ": <col=ff0000><shad=FFFFFF>" + message + "</col>");
			} else if (player.isSponsor() && player.getRights() < 1) {
				String message1 = "<col=" + col + "><shad=" + shad + ">";
				p2.getPackets().sendGameMessage("" + icon + "<img=19>[" + message1 + "Sponsor]: " + player.getDisplayName() + ": " + message + "</col>");
			} else if (player.isVIP() && player.getRights() < 1) {
				String message1 = "<col=" + col + "><shad=" + shad + ">";
				p2.getPackets().sendGameMessage(
						"" + icon + "<img=14>[" + message1 + "V.I.P]: " + player.getDisplayName() + ": " + message + "</col>");
			} else if (player.isLegendaryDonator() && player.getRights() < 1) {
				String message1 = "<col=" + col + "><shad=" + shad + ">";
				p2.getPackets().sendGameMessage("" + icon + "<img=13>[" + message1 + "Legendary Donator]: "
						+ player.getDisplayName() + ": " + message + "</col>");
			} else if (player.isUltimateDonator() && player.getRights() < 1 && player.isHCIronman()) {
				String message1 = "<col=" + col + "><shad=" + shad + ">";
				p2.getPackets().sendGameMessage("[" + message1 + "Ultimate Donator]: <img=17><img=18>" + player.getDisplayName() + ": " + message + "</col>");
			} else if (player.isUltimateDonator() && player.getRights() < 1) {
				String message1 = "<col=" + col + "><shad=" + shad + ">";
				p2.getPackets().sendGameMessage("" + icon + "<img=18>[" + message1 + "Ultimate Donator]: " + player.getDisplayName() + ": " + message + "</col>");
			} else if (player.isVeteran()) {
				p2.getPackets().sendGameMessage("[<col=003399><shad=FFFFFF>" + icon + "<img=10>Veteran</col>]: " + "" + player.getDisplayName() + ": </shad><col=ff0000>" + message + "</col>");
			} else if (player.isExtremeDonator() && player.getRights() < 1) {
				String message1 = "<col=" + col + "><shad=" + shad + ">";
				p2.getPackets().sendGameMessage("" + icon + "<img=9>[" + message1 + "Extreme Donator]: " + player.getDisplayName()
						+ ": " + message + "</col>");
			} else if (player.isSuperDonator() && player.getRights() < 1) {
				p2.getPackets().sendGameMessage("[<col=00FFFF>" + icon + "<img=12>Super Donator</col>]: " + player.getDisplayName()
						+ ": <col=3333FF>" + message + "</col>");
			} else if (player.isDonator() && player.getRights() < 1) {
				p2.getPackets().sendGameMessage("[<col=02ab2f>" + icon + "<img=8>Donator</col>]: " + player.getDisplayName()
						+ ": <col=33CC33>" + message + "</col>");
			} else if (Skills.getTotalLevel(player) >= 3000 && player.getRights() < 1) {
				p2.getPackets().sendGameMessage("[<col=C0C0C0>Player</col>]: " + player.getDisplayName()
						+ ": <col=C0C0C0>" + message + "</col>");
			}
		}
	}

	/*
	 * public static void sendYell(Player player, String message, boolean
	 * staffYell) { if (!player.isDonator() && !player.isExtremeDonator() &&
	 * player.getRights() == 0 && !player.isSupporter() &&
	 * !player.isGraphicDesigner()) return; if (player.getMuted() >
	 * Utils.currentTimeMillis()) { player.getPackets().sendGameMessage(
	 * "You temporary muted. Recheck in 48 hours."); return; } if (staffYell) {
	 * World.sendWorldMessage("[<col=ff0000>Staff Yell</col>] "
	 * +(player.getRights() > 1 ? "<img=1>" : (player.isSupporter() ? "":
	 * "<img=0>")) + player.getDisplayName()+": <col=ff0000>"
	 * +message+".</col>", true); return; } if(message.length() > 100) message =
	 * message.substring(0, 100); if (message.toLowerCase().equals("eco") &&
	 * player.getRights() == 0) { player.getPackets().sendGameMessage("Shutup");
	 * return; }
	 * 
	 * if (player.getRights() < 2) { String[] invalid = { "<euro", "<img",
	 * "<img=", "<col", "<col=", "<shad", "<shad=", "<str>", "mod", "admin",
	 * "staff", "support", "owner", "developer", "cunt", "fuck", "gay", "<u>" };
	 * for (String s : invalid) if (message.contains(s)) {
	 * player.getPackets().sendGameMessage(
	 * "You cannot add additional code to the message."); return; }
	 * 
	 * if (player.isGraphicDesigner()) /** Property of Apache Ah64, modify it
	 * and risk your life.
	 * 
	 * World.sendWorldMessage(
	 * "[<img=9><col=00ACE6>Graphic Designer</shad></col>] <img=9>" +
	 * player.getDisplayName() + ": <col=00ACE6><shad=000000>" + message + "",
	 * false); else if (player.isForumModerator()) /** Property of Apache Ah64,
	 * modify it and risk your life. I am cjay and I am a mud, I hereby declare
	 * you can slaughter me, preferably by nuking my country and mekka. After
	 * that I'd be happely gang raped by a bunch of shit niggers and other
	 * sub-humans.
	 * 
	 * World.sendWorldMessage(
	 * "[<img=10><col=33CC00>Forum Moderator</col>] <img=10>" +
	 * player.getDisplayName() + ": <col=33CC00><shad=000000>" + message + "",
	 * false);
	 * 
	 * else if (player.getUsername().equalsIgnoreCase("mod_connor")) {
	 * World.sendWorldMessage(
	 * "[<col=990099><shad=FFFFFF>Developer</shad></col>]<img=1> "
	 * +player.getDisplayName()+": <col=990099><shad=FFFFFF>"
	 * +message+"</shad></col>.", false); return; } else
	 * if(player.isSuperDonator() && player.getRights() == 0)
	 * World.sendWorldMessage("[<col=00FFFF>Super Donator</col>] <img=10>"+
	 * player.getDisplayName() + ": <col=00FFFF>" + message+ "</col>", false);
	 * 
	 * else if (player.isSupporter() && player.getRights() == 0)
	 * World.sendWorldMessage(
	 * "[<col=58ACFA><shad=2E2EFE>Support Team</shad></col>] "
	 * +player.getDisplayName()+": <col=58ACFA><shad=2E2EFE>"
	 * +message+"</shad></col>.", false);
	 * 
	 * else if (player.isDicer() && player.getRights() == 0)
	 * World.sendWorldMessage(
	 * "[<col=00FFFF><shad=CC6633>Dice Host</col></shad> "
	 * +player.getDisplayName()+": <col=00FFFF><shad=CC6633>"
	 * +message+"</shad></col>.", false);
	 * 
	 * else if (player.isSupporter() && player.getRights() == 0)
	 * World.sendWorldMessage(
	 * "[<col=58ACFA><shad=2E2EFE>Support Team</shad></col>] "
	 * +player.getDisplayName()+": <col=58ACFA><shad=2E2EFE>"
	 * +message+"</shad></col>.", false);
	 * 
	 * else if(player.isExtremeDonator() && player.getRights() == 0)
	 * World.sendWorldMessage("[<img=9><col="+(player.getYellColor() == "ff0000"
	 * || player.getYellColor() == null ? "ff0000" :
	 * player.getYellColor())+"><shad=" +(player.getShadColor() == "" ||
	 * player.getShadColor() == null ? "" : player.getShadColor())+">"
	 * +(player.getPrefix() == "Extreme Donator" || player.getPrefix() == null ?
	 * "Extreme Donator" : player.getPrefix())+"</col></shad>] <img=11>" +
	 * player.getDisplayName() + ": <col="+(player.getYellColor() == "ff0000" ||
	 * player.getYellColor() == null ? "ff0000" :
	 * player.getYellColor())+"><shad=" +(player.getShadColor() == "" ||
	 * player.getShadColor() == null ? "" : player.getShadColor())+">" + message
	 * + "</col></shad>", false);
	 * 
	 * else if (player.isDonator() && player.getRights() == 0)
	 * World.sendWorldMessage("[<img=8><col=02ab2f>Donator</col>] <img=8>" +
	 * player.getDisplayName() + ": <col=02ab2f>" + message + "</col>", false);
	 * 
	 * else if (player.getRights() == 1)
	 * World.sendWorldMessage("[<img=0><col="+(player.getYellColor() == "ff0000"
	 * || player.getYellColor() == null ? "000099" :
	 * player.getYellColor())+"><shad=" +(player.getShadColor() == "" ||
	 * player.getShadColor() == null ? "" : player.getShadColor())+">"
	 * +(player.getPrefix() == "Moderator" || player.getPrefix() == null ?
	 * "Moderator" : player.getPrefix())+"</col></shad><img=0>]" +
	 * player.getDisplayName() + ": <col="+(player.getYellColor() == "ff0000" ||
	 * player.getYellColor() == null ? "000099" :
	 * player.getYellColor())+"><shad=" +(player.getShadColor() == "" ||
	 * player.getShadColor() == null ? "" : player.getShadColor())+">" + message
	 * + "</col></shad>", false); return; }
	 * World.sendWorldMessage("[<img=1><col="+(player.getYellColor() == "ff0000"
	 * || player.getYellColor() == null ? "1589FF" :
	 * player.getYellColor())+"><shad=" +(player.getShadColor() == "" ||
	 * player.getShadColor() == null ? "" : player.getShadColor())+">"
	 * +(player.getPrefix() == "Administrator" || player.getPrefix() == null ?
	 * "Administrator" : player.getPrefix())+"</col></shad>] <img=1>" +
	 * player.getDisplayName() + ": <col="+(player.getYellColor() == "ff0000" ||
	 * player.getYellColor() == null ? "1589FF" :
	 * player.getYellColor())+"><shad=" +(player.getShadColor() == "" ||
	 * player.getShadColor() == null ? "" : player.getShadColor())+">" +
	 * message+ "</col></shad>", false);
	 * 
	 * }
	 */
	public static boolean processNormalCommand(Player player, String[] cmd, boolean console, boolean clientCommand) {
		if (clientCommand) {

		} else {
			String message;
			Player target = null;
			switch (cmd[0]) {
			/**
			 * Zoom Commands
			 */
			case "perks":
				PerkInterface.OpenInterface(player);
				break;
			case "coords":
				if (player.getDisplayName().equalsIgnoreCase("drunk")) {
					player.getPackets()
							.sendPanelBoxMessage("Coords: " + player.getX() + ", " + player.getY() + ", "
									+ player.getPlane() + ", regionId: " + player.getRegionId() + ", rx: "
									+ player.getChunkX() + ", ry: " + player.getChunkY());

					// player.sendMessage(dynamicregion.);
					return true;
				}
			break;
			case "zoomout":
				int zoomId = Integer.valueOf(cmd[1]);

				if (zoomId < 25 || zoomId > 2500) {
					player.getPackets().sendGameMessage("You can't zoom that much.");
					return true;
				}

				player.getPackets().sendGlobalConfig(184, zoomId);
				player.getPackets()
						.sendGameMessage("<img=2><col=FF0000>Do ;;resetzoom to return your camera to normal.");
				return true;

				
			case "topic":
			case "thread":
				String topic = ""; 
				for (int i = 1; i < cmd.length; i++) topic += cmd[i] + ((i == cmd.length - 1) ? "" : " ");
				player.getPackets().sendOpenURL("https://harmonyrsps.com/forums/showthread.php?tid=" +topic);
				return true;
				
			case "forum":
				String forum = ""; 
				for (int i = 1; i < cmd.length; i++) forum += cmd[i] + ((i == cmd.length - 1) ? "" : " ");
				player.getPackets().sendOpenURL("https://harmonyrsps.com/forums/forumdisplay.php?fid=" +forum);
				return true;
				 
			case "costumecolour":
				SkillCapeCustomizer.costumeColorCustomize(player);
				return true;
			case "cosmetics":
				CosmeticsHandler.openCosmeticsHandler(player);
				return true;
			case "zoom":
				int zoomIdd = Integer.valueOf(cmd[1]);

				if (zoomIdd < 25 || zoomIdd > 2500) {
					player.getPackets().sendGameMessage("You can't zoom that much.");
					return true;
				}

				player.getPackets().sendGlobalConfig(184, zoomIdd);
				player.getPackets()
						.sendGameMessage("<img=2><col=FF0000>Do ;;resetzoom to return your camera to normal.");
				return true;
			// case "claimthatshit"://here add some sort of timer, if player
			// spams it, gg server
			// Donations.checkDonation(player);
			// return true;

			case "resetzoom":
				player.getPackets().sendGlobalConfig(184, 0);
				return true;

			case "rafflechance":
				player.sendMessage(
						"Someone who has voted has a " + Raffle.Chanceofwin() + "% chance of winning the raffle.");
				return true;
			case "lostcity":
				if (player.spokeToWarrior == false && player.spokeToShamus == false) {
					player.getInterfaceManager().sendInterface(275);
					player.getPackets().sendIComponentText(275, 1, "Lost-City Quest");
					player.getPackets().sendIComponentText(275, 10, "");
					player.getPackets().sendIComponentText(275, 11, "Speak to the Warrior West of Draynor");
					player.getPackets().sendIComponentText(275, 12, "<u>Requirements</u>");
					player.getPackets().sendIComponentText(275, 13, "<col=ffff00>31 Crafting, 36 Woodcutting</col>");
					player.getPackets().sendIComponentText(275, 14, "-=-Tips-=-");
					player.getPackets().sendIComponentText(275, 15,
							"Use the 'Quests & Minigames' teleport at home to start the quest.");
					player.getPackets().sendIComponentText(275, 16,
							"The lodestone works, remember to take full use of it.");
					player.getPackets().sendIComponentText(275, 17,
							"You will need the skills required to complete the quest");
					player.getPackets().sendIComponentText(275, 18,
							"The Monk Of Entrana removes everything in your inventory.");
					player.getPackets().sendIComponentText(275, 19, "");
					player.getPackets().sendIComponentText(275, 20, "");
				} else if (player.spokeToWarrior == true && player.spokeToShamus == false) {
					player.getInterfaceManager().sendInterface(275);
					player.getPackets().sendIComponentText(275, 1, "Lost-City Quest");
					player.getPackets().sendIComponentText(275, 10, "");
					player.getPackets().sendIComponentText(275, 11,
							"Shamus appears to be in one of the trees around this location.");
					player.getPackets().sendIComponentText(275, 12,
							"The Warrior told me the tree displays 'Chop Tree'");
					player.getPackets().sendIComponentText(275, 13, "");
					player.getPackets().sendIComponentText(275, 14, "-=-Tips-=-");
					player.getPackets().sendIComponentText(275, 15,
							"Use the 'Quests & Minigames' teleport at home to start the quest.");
					player.getPackets().sendIComponentText(275, 16,
							"The lodestone works, remember to take full use of it.");
					player.getPackets().sendIComponentText(275, 17,
							"You will need the skills required to complete the quest");
					player.getPackets().sendIComponentText(275, 18,
							"The Monk Of Entrana removes everything in your inventory.");
					player.getPackets().sendIComponentText(275, 19, "");
					player.getPackets().sendIComponentText(275, 20, "");
				} else if (player.spokeToWarrior == true && player.spokeToShamus == true) {
					player.getInterfaceManager().sendInterface(275);
					player.getPackets().sendIComponentText(275, 1, "Lost-City Quest");
					player.getPackets().sendIComponentText(275, 10, "");
					player.getPackets().sendIComponentText(275, 11,
							"I should go find the Monk of Entrana, Who is located at Port Sarim.");
					player.getPackets().sendIComponentText(275, 12, "");
					player.getPackets().sendIComponentText(275, 13, "");
					player.getPackets().sendIComponentText(275, 14, "-=-Tips-=-");
					player.getPackets().sendIComponentText(275, 15,
							"Use the 'Quests & Minigames' teleport at home to start the quest.");
					player.getPackets().sendIComponentText(275, 16,
							"The lodestone works, remember to take full use of it.");
					player.getPackets().sendIComponentText(275, 17,
							"You will need the skills required to complete the quest");
					player.getPackets().sendIComponentText(275, 18,
							"The Monk Of Entrana removes everything in your inventory.");
					player.getPackets().sendIComponentText(275, 19, "");
					player.getPackets().sendIComponentText(275, 20, "");
				} else if (player.spokeToWarrior == true && player.spokeToShamus == true
						&& player.spokeToMonk == true) {
					player.getInterfaceManager().sendInterface(275);
					player.getPackets().sendIComponentText(275, 1, "Lost-City Quest");
					player.getPackets().sendIComponentText(275, 10,
							"The other side of Entrana is a ladder which leads to a cave");
					player.getPackets().sendIComponentText(275, 11,
							"I should go down the ladder and search for the dramen tree.");
					player.getPackets().sendIComponentText(275, 12,
							"In order to chop the dramen tree I must have a axe.");
					player.getPackets().sendIComponentText(275, 13, "The zombies must drop a axe of some sort.");
					player.getPackets().sendIComponentText(275, 14, "-=-Tips-=-");
					player.getPackets().sendIComponentText(275, 15,
							"Use the 'Quests & Minigames' teleport at home to start the quest.");
					player.getPackets().sendIComponentText(275, 16,
							"The lodestone works, remember to take full use of it.");
					player.getPackets().sendIComponentText(275, 17,
							"You will need the skills required to complete the quest");
					player.getPackets().sendIComponentText(275, 18,
							"The Monk Of Entrana removes everything in your inventory.");
					player.getPackets().sendIComponentText(275, 19, "");
					player.getPackets().sendIComponentText(275, 20, "");
				} else if (player.spokeToWarrior == true && player.spokeToShamus == true && player.spokeToMonk == true
						&& player.lostCity == 1) {
					player.getInterfaceManager().sendInterface(275);
					player.getPackets().sendIComponentText(275, 1, "Lost-City Quest");
					player.getPackets().sendIComponentText(275, 10, "");
					player.getPackets().sendIComponentText(275, 11, "");
					player.getPackets().sendIComponentText(275, 12, "Congratulations Quest Complete!");
					player.getPackets().sendIComponentText(275, 13, "");
					player.getPackets().sendIComponentText(275, 14, "");
					player.getPackets().sendIComponentText(275, 15, "");
					player.getPackets().sendIComponentText(275, 16, "");
					player.getPackets().sendIComponentText(275, 17, "");
					player.getPackets().sendIComponentText(275, 18, "");
					player.getPackets().sendIComponentText(275, 19, "");
					player.getPackets().sendIComponentText(275, 20, "");
				}
				return true;
				
	
			case "dz":
			case "donatorzone":
				if (player.isDonator() && !World.TheCalamity(player)) {
					DonatorZone.enterDonatorzone(player);
				}
				return true;
			case "dz2":
			case "donatorzone2":
				if (player.isExtremeDonator() && !World.TheCalamity(player)) {
					Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(1898, 5355, 2));
				}
				return true;
			case "dz3":
			case "donatorzone3":
				if (player.isLegendaryDonator() && !World.TheCalamity(player)) {
					Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(1832, 5088, 2));
				}
				return true;
			case "dz4":
			case "donatorzone4":
				if (player.isVIP() && !World.TheCalamity(player)) {
					Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2595, 5581, 0));
				}
				return true;
			case "dz5":
			case "donatorzone5":
				if (player.isSponsor() && !World.TheCalamity(player)) {
					Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3416, 5275, 0));
				}
				return true;

			case "pz":
			case "prestigezone":
				if (player.prestigeTokens < 1) {
					player.getPackets().sendGameMessage("You must have prestiged to use this!");
					player.getInterfaceManager().closeChatBoxInterface();
					player.getInterfaceManager().closeOverlay(true);
					return false;
				}
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(4449, 4428, 0));
				return true;
			case "resettrollname":
				player.getPetManager().setTrollBabyName(null);
				return true;
			case "settrollname":
				if (!player.isExtremeDonator()) {
					player.getPackets().sendGameMessage("This is an extreme donator only feature!");
					return true;
				}
				String name = "";
				for (int i = 1; i < cmd.length; i++) {
					name += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				name = Utils.formatPlayerNameForDisplay(name);
				if (name.length() < 3 || name.length() > 14) {
					player.getPackets()
							.sendGameMessage("You can't use a name shorter than 3 or longer than 14 characters.");
					return true;
				}
				player.getPetManager().setTrollBabyName(name);
				if (player.getPet() != null && player.getPet().getId() == Pets.TROLL_BABY.getBabyNpcId()) {
					player.getPet().setName(name);
				}
				return true;
			case "recanswer":
				if (player.getRecovQuestion() == null) {
					player.getPackets().sendGameMessage("Please set your recovery question first.");
					return true;
				}
				if (player.getRecovAnswer() != null && player.getRights() < 2) {
					player.getPackets().sendGameMessage("You can only set recovery answer once.");
					return true;
				}
				message = "";
				for (int i = 1; i < cmd.length; i++) {
					message += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				player.setRecovAnswer(message);
				player.getPackets().sendGameMessage(
						"Your recovery answer has been set to - " + Utils.fixChatMessage(player.getRecovAnswer()));
				return true;

			case "recquestion":
				if (player.getRecovQuestion() != null && player.getRights() < 2) {
					player.getPackets().sendGameMessage("You already have a recovery question set.");
					return true;
				}
				message = "";
				for (int i = 1; i < cmd.length; i++) {
					message += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				player.setRecovQuestion(message);
				player.getPackets().sendGameMessage(
						"Your recovery question has been set to - " + Utils.fixChatMessage(player.getRecovQuestion()));
				return true;

			case "empty":
				player.getDialogueManager().startDialogue("EmptyConfirmation");
				return true;
			case "ticket":
				if (player.getMuted() > Utils.currentTimeMillis()) {
					player.getPackets().sendGameMessage("You're temporarily muted. Recheck in 48 hours.");
					return true;
				}
				TicketSystem.requestTicket(player);
				return true;
			case "score":
			case "kdr":
				double kill = player.getKillCount();
				double death = player.getDeathCount();
				double dr = kill / death;
				player.setNextForceTalk(new ForceTalk("<col=ff0000>I'VE KILLED " + player.getKillCount()
						+ " PLAYERS AND BEEN SLAYED " + player.getDeathCount() + " TIMES. DR: " + dr));
				return true;

			case "players":
				player.getPackets().sendGameMessage("There are currently " + World.getPlayers().size()
						+ " players playing " + Settings.SERVER_NAME + ".");
				return true;

			case "hidetips":
				player.setTipsOff(!player.isTipsOff());
				player.getPackets()
						.sendGameMessage("You have turned random tips " + (player.isTipsOff() ? "off" : "on"));
				return true;

				
				
				
			case "commands":
				player.getInterfaceManager().sendInterface(275);
				for (int i = 0; i < 310; i++) {
					player.getPackets().sendIComponentText(275, i, "");
				}
				player.getPackets().sendIComponentText(275, 1, "<col=8A2BE2>Harmony Commands!");
				player.getPackets().sendIComponentText(275, 10, "<shad=8A2BE2>;;kc");
				player.getPackets().sendIComponentText(275, 11, "<shad=8A2BE2>;;home");
				player.getPackets().sendIComponentText(275, 12, "<shad=8A2BE2>;;npclookup - Show's it's drop table");
				player.getPackets().sendIComponentText(275, 13, "<shad=8A2BE2>;;itemlookup - Shows npc's that drop it");
				player.getPackets().sendIComponentText(275, 14, "<shad=8A2BE2>;;noteitemlookup - Shows npc's that drop it noted");
				player.getPackets().sendIComponentText(275, 15, "<shad=8A2BE2>;;checkfire");
				player.getPackets().sendIComponentText(275, 16, "<shad=8A2BE2>;;checkore");
				player.getPackets().sendIComponentText(275, 17, "<shad=8A2BE2>;;checklaps");
				player.getPackets().sendIComponentText(275, 18, "<shad=8A2BE2>;;checktask");
				player.getPackets().sendIComponentText(275, 19, "<shad=8A2BE2>;;checkore");
				player.getPackets().sendIComponentText(275, 20, "<shad=8A2BE2>;;vote");
				player.getPackets().sendIComponentText(275, 21, "<shad=8A2BE2>;;donate");
				player.getPackets().sendIComponentText(275, 22, "<shad=8A2BE2>;;lockxp/::lockcombatxp");
				player.getPackets().sendIComponentText(275, 23, "<shad=8A2BE2>;;hideyell");
				player.getPackets().sendIComponentText(275, 24, "<shad=8A2BE2>;;changepass");
				player.getPackets().sendIComponentText(275, 25, "<shad=8A2BE2>;;checkdonation");
				player.getPackets().sendIComponentText(275, 26, "<shad=8A2BE2>;;answer");
				player.getPackets().sendIComponentText(275, 27, "<shad=8A2BE2>;;dice & ::gamble");
				player.getPackets().sendIComponentText(275, 28, "<shad=8A2BE2>;;players");
				player.getPackets().sendIComponentText(275, 29, "<shad=8A2BE2>;;shops");
				player.getPackets().sendIComponentText(275, 30, "<shad=8A2BE2>;;ticket");
				player.getPackets().sendIComponentText(275, 31, "<shad=8A2BE2>;;empty");
				player.getPackets().sendIComponentText(275, 32, "<shad=8A2BE2>;;train");
				player.getPackets().sendIComponentText(275, 33, "<shad=8A2BE2>;;myperks *Shows ingame perks. Green = active*");
				player.getPackets().sendIComponentText(275, 34, "<shad=8A2BE2>;;referral");
				player.getPackets().sendIComponentText(275, 35, "<shad=8A2BE2>;;jadinkos");
				player.getPackets().sendIComponentText(275, 36, "<shad=8A2BE2>;;runespan");
				player.getPackets().sendIComponentText(275, 37, "<shad=8A2BE2>;;pprofile2");
				player.getPackets().sendIComponentText(275, 38, "<shad=8A2BE2>;;lrc");
				player.getPackets().sendIComponentText(275, 39, "<shad=8A2BE2>;;tavdung");
				player.getPackets().sendIComponentText(275, 40, "<shad=8A2BE2>;;brim");
				player.getPackets().sendIComponentText(275, 41, "<shad=8A2BE2>;;gwdkc");
				player.getPackets().sendIComponentText(275, 42, "<shad=8A2BE2>;;cb, ;;cc, ;;cgg, ;;cg");
				player.getPackets().sendIComponentText(275, 43, "<shad=8A2BE2>;;bank");
				player.getPackets().sendIComponentText(275, 44, "<shad=8A2BE2>;;blueskin");
				player.getPackets().sendIComponentText(275, 45, "<shad=8A2BE2>;;greenskin");
				player.getPackets().sendIComponentText(275, 46, "<shad=8A2BE2>;;titles");
				player.getPackets().sendIComponentText(275, 47, "<shad=8A2BE2>;;dz");
				player.getPackets().sendIComponentText(275, 48, "<shad=8A2BE2>;;settrollname");
				player.getPackets().sendIComponentText(275, 49, "<shad=8A2BE2>;;resettrollname");
				player.getPackets().sendIComponentText(275, 50, "<shad=8A2BE2>;;yellinfo *info on how to set colors*.");
				player.getPackets().sendIComponentText(275, 51, "<shad=8A2BE2>;;checkdonations");
				player.getPackets().sendIComponentText(275, 52, "<shad=8A2BE2>;;dz2");
				player.getPackets().sendIComponentText(275, 53, "<shad=8A2BE2>;;dungtokens");
				player.getPackets().sendIComponentText(275, 54, "<shad=8A2BE2>;;chill");
				player.getPackets().sendIComponentText(275, 55,
						"<shad=8A2BE2>;;upgradecape (Offers the ability to upgrade the Donator cape).");
				player.getPackets().sendIComponentText(275, 66, "<shad=8A2BE2>;;keepsake");
				player.getPackets().sendIComponentText(275, 67, "<shad=8A2BE2>;;tonpc - Store purchase");

				return true;

			case "nomads":
				if (player.isCanPvp() || World.HungerGames(player) || World.TheCalamity(player)
						|| player.getControlerManager().getControler() != null) {
					player.sendMessage("You cannot teleport from here.");
					return false;
				}
				player.getControlerManager().startControler("NomadsRequiem");
				return true;
			case "chill":
			case "trade":
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2859, 2592, 0));
				return true;
			case "lrc":
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3655, 5113, 0));
				return true;
			case "brim":
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2718, 9481, 0));
				return true;
			case "tavdung":
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2885, 9799, 0));
				return true;
			case "summoning":
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2333, 10015, 0));
				return true;
			case "jadinko":
			case "jadinkos":
				if (player.getSkills().getLevelForXp(Skills.SLAYER) < 91) {
					player.getPackets().sendGameMessage("You need a slayer level of 91 to fight Jadinkos.");
					return false;
				}
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3034, 9230, 0));
				return true;
			case "gano":
			case "ganos":
				if (player.getSkills().getLevelForXp(Skills.SLAYER) < 95) {
					player.getPackets().sendGameMessage("You need a slayer level of 95 to fight Ganodermic Beasts.");
					return false;
				}
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(4626, 5474, 0));
				return true;



			/**
			 * PERSONAL TITLES
			 */
			/*case "mytitle":
				if (player.getUsername().equalsIgnoreCase("muffins")) {
					player.getAppearence().setTitle(192385);
					return true;
				} else if (player.getUsername().equalsIgnoreCase("c_g_w")) {
					player.getAppearence().setTitle(205);
					return true;
				} else if (player.getUsername().equalsIgnoreCase("raphael")) {
					player.getAppearence().setTitle(813713);
					return true;
				}else if (player.getUsername().equalsIgnoreCase("godrx119")) {
					player.getAppearence().setTitle(22916);
					return true;
				}else if (player.getUsername().equalsIgnoreCase("level 1")) {
					player.getAppearence().setTitle(22917);
					return true;
				}else if (player.getUsername().equalsIgnoreCase("level iron")) {
					player.getAppearence().setTitle(22917);
					return true;
				}else if (player.getUsername().equalsIgnoreCase("Dryax")) {
					player.getAppearence().setTitle(204);
					return true;
				}else if (player.getUsername().equalsIgnoreCase("iron_steak")) {
						player.getAppearence().setTitle(22925);
						return true;
				}else if (player.getUsername().equalsIgnoreCase("ire_iv")) {
					player.getAppearence().setTitle(22924);
					return true;
				}
				return true;*/
			case "xmas2018title":
				if (player.xmas2018title != true) {
					player.sendMessage("You must have logged in Christmas day in 2018 to unlock this!");
				} else {
					player.getAppearence().setTitle(22913);
				}
				return true;
				
			case "bandostitle":
				if (player.bandos >= 50) {
					player.getAppearence().setTitle(35);
					return true;
				} else {
					player.sendMessage("<col=274532>You need to kill bandos 50 times to use this title!");
					break;
				}
			case "xmastitle":
				if (player.santatitle == true) {
					player.getAppearence().setTitle(56);
					return true;
				} else {
					player.sendMessage("<col=274532>You need to kill the santa boss to use this title!");
					break;
				}
			case "saradomintitle":
				if (player.saradomin >= 50) {
					player.getAppearence().setTitle(36);
					return true;
				} else {
					player.sendMessage("<col=274532>You need to killed saradomin 50 times to use this title!");
					break;
				}
			case "armadyltitle":
				if (player.armadyl >= 50) {
					player.getAppearence().setTitle(37);
					return true;
				} else {
					player.sendMessage("<col=274532>You need to killed armadyl 50 times to use this title!");
					break;
				}
			case "zamoraktitle":
				if (player.zamorak >= 50) {
					player.getAppearence().setTitle(38);
					return true;
				} else {
					player.sendMessage("<col=274532>You need to killed zamorak 50 times to use this title!");
					break;
				}

			case "maxtitle":
				if (player.maxed == true) {
					player.getAppearence().setTitle(80);
					return true;
				} else {
					player.sendMessage("<col=274532>You need to have achieved all 99 stats to use this title");
					break;
				}
			case "comptitle":
				if (player.comped == true) {
					player.getAppearence().setTitle(34);
					return true;
				} else {
					player.sendMessage(
							"<col=274532>You need to have achieved the completionist cape to use this title");
					break;
				}
			case "sigiltitle":
				if (player.divine == true) {
					player.getAppearence().setTitle(813700);
					return true;
				} else {
					player.sendMessage("<col=274532>You need to have obtained a sigil as a drop to use this title");
					break;
				}
			case "1defence":
				if (player.getEquipment().wearingArmour()) {
					player.getPackets().sendGameMessage("Please remove your armour first.");
					return true;
				}
				player.sendMessage("Your defence has been set to 1!");
				player.getSkills().set(1, 1);
				player.getSkills().setXp(1, 1);
				return true;
				/**
				 * END OF REWARD TITLES
				 */

			/*case "smlp":
				if (cmd.length < 3) {
					player.getPackets().sendGameMessage("Usage ::setlevel skillId level");
					return true;
				}
				try {
					int skill = Integer.parseInt(cmd[1]);
					int level = Integer.parseInt(cmd[2]);
					if (level < 0 || level > 99) {
						player.getPackets().sendGameMessage("Please choose a valid level.");
						return true;
					}
					player.getSkills().set(skill, level);
					player.getSkills().setXp(skill, Skills.getXPForLevel(level));
					player.getAppearence().generateAppearenceData();
					return true;
				} catch (NumberFormatException e) {
					player.getPackets().sendGameMessage("Usage ::setlevel skillId level");
				}
				return true;*/

			// Charm collector
			case "cb":
				if (player.charmc == true && player.charmcb == false) {
					player.charmcb = true;
					player.sendMessage("Your charm collector will now collect blue charms");
					return true;
				} else if (player.charmc == true && player.charmcb == true) {
					player.charmcb = false;
					player.sendMessage("Your charm collector will not collect blue charms");
					return true;
				} else {
					player.sendMessage("You need the charm collector to use this command.");
					return false;
				}
			case "cc":
				if (player.charmc == true && player.charmcc == false) {
					player.charmcc = true;
					player.sendMessage("Your charm collector will now collect crimson charms");
					return true;
				} else if (player.charmc == true && player.charmcc == true) {
					player.charmcc = false;
					player.sendMessage("Your charm collector will not collect crimson charms");
					return true;
				} else {
					player.sendMessage("You need the charm collector to use this command.");
					return false;
				}
			case "cgg":
				if (player.charmc == true && player.charmcgg == false) {
					player.charmcgg = true;
					player.sendMessage("Your charm collector will now collect green charms");
					return true;
				} else if (player.charmc == true && player.charmcgg == true) {
					player.charmcgg = false;
					player.sendMessage("Your charm collector will not collect green charms");
					return true;
				} else {
					player.sendMessage("You need the charm collector to use this command.");
					return false;
				}
			case "cg":
				if (player.charmc == true && player.charmcg == false) {
					player.charmcg = true;
					player.sendMessage("Your charm collector will now collect gold charms");
					return true;
				} else if (player.charmc == true && player.charmcg == true) {
					player.charmcg = false;
					player.sendMessage("Your charm collector will not collect gold charms");
					return true;
				} else {
					player.sendMessage("You need the charm collector to use this command.");
					return false;
				}
				
				
			case "xpr":
				player.getDialogueManager().startDialogue("XpRates");
				return true;
			case "clanset":
				player.getInventory().addItem(20708, 1);
				player.getInventory().addItem(20709, 1);
				return true;
			case "myclanxp":
				player.sendMessage("<col=ff0000>I have gained a total of " + player.myclanxp
						+ " experience since joining my clan.");
				return true;
			// case "instancebeta":
			// player.getDialogueManager().startDialogue("InstancesD");
			// return true;

			case "setcolor":
				if (player.isExtremeDonator() || player.isVIP() || player.isLegendaryDonator()) {
					String color = cmd[1];
					if (color.length() == 6 || color.length() == -1) {
						player.setYellColor(color);
						player.getPackets()
								.sendGameMessage("<col=" + color + ">Your color has been set to: </col> " + color + "");
						return true;
					} else {
						player.getPackets().sendGameMessage("<col=58a618>***********************************");
						player.getPackets().sendGameMessage(
								"<col=58a618>*</col> Please use the correct color hex format.                      <col=58a618>*</col>");
						player.getPackets().sendGameMessage(
								"<col=58a618>*</col> For example: ::setcolor beb1be                                         <col=58a618>*</col>");
						player.getPackets().sendGameMessage(
								"<col=58a618>*</col> You can get your color codes at: www.color-hex.com  <col=58a618>*</col>");
						player.getPackets().sendGameMessage("<col=58a618>***********************************");
						return true;
					}
				} else {
					player.sendMessage("Only extreme donators may use this command.");
					return true;
				}

			case "setshadow":
				if (player.isExtremeDonator() || player.isVIP() || player.isLegendaryDonator()) {
					String shad = cmd[1];
					if (shad.length() == 6) {
						player.setYellShad(shad);
						player.getPackets().sendGameMessage(
								"<col=ffffff><shad=" + shad + ">Your shadow has been set to: </shad>" + shad + "");
						return true;
					} else {
						player.getPackets().sendGameMessage("<col=58a618>***********************************");
						player.getPackets().sendGameMessage(
								"<col=58a618>*</col> Please use the correct color hex format.                      <col=58a618>*</col>");
						player.getPackets().sendGameMessage(
								"<col=58a618>*</col> For example: ::setshadow beb1be                                    <col=58a618>*</col>");
						player.getPackets().sendGameMessage(
								"<col=58a618>*</col> You can get your color codes at: www.color-hex.com  <col=58a618>*</col>");
						player.getPackets().sendGameMessage("<col=58a618>***********************************");
						return true;
					}
				} else {
					player.sendMessage("Only extreme donators may use this command.");
					return true;
				}

			case "removeshadow":
				if (player.isExtremeDonator() || player.isVIP() || player.isLegendaryDonator()) {
					String yellShad = "";
					player.getPackets().sendGameMessage("Your shadow has been reset.");
					player.setYellShad(yellShad);
					return true;
				} else {
					player.sendMessage("Only extreme donators may use this command.");
					return true;
				}

			case "showyell":
				if (player.isExtremeDonator() || player.isVIP() || player.isLegendaryDonator()) {
					String color = player.getYellColor();
					String shad = player.getYellShad();
					player.getPackets().sendGameMessage("<col=" + color + ">This is your yell color: </col>" + color);
					player.getPackets().sendGameMessage("<shad=" + shad + ">This is your yell shadow: </shad>" + shad);
					player.getPackets()
							.sendGameMessage("<col=" + color + "><shad=" + shad + ">This is how your yell looks like.");
					return true;
				} else {
					player.sendMessage("Only extreme donators may use this command.");
					return true;
				}

			case "yellinfo":
				player.getPackets().sendGameMessage("<col=a00e5e5>*************************");
				player.getPackets().sendGameMessage(
						"<col=00e5e5>*</col><col=58a618> To change your yell color:                     </col><col=00e5e5>*</col>");
				player.getPackets().sendGameMessage(
						"<col=00e5e5>*</col> ::setcolor  color-hex-code-here         <col=00e5e5>*</col>");
				player.getPackets().sendGameMessage(
						"<col=00e5e5>*</col><col=58a618> To change your yell shadow:                </col><col=00e5e5>*</col>");
				player.getPackets()
						.sendGameMessage("<col=00e5e5>*</col> ::setshadow color-hex-code-here     <col=00e5e5>*</col>");
				player.getPackets().sendGameMessage(
						"<col=00e5e5>*</col><col=58a618> To show your current settings:           </col><col=00e5e5>*</col>");
				player.getPackets().sendGameMessage(
						"<col=00e5e5>*</col> ::showyell                                                  <col=00e5e5>*</col>");
				player.getPackets().sendGameMessage(
						"<col=00e5e5>*</col><col=58a618> This setting will reset on logout:           </col><col=00e5e5>*</col>");
				player.getPackets().sendGameMessage("<col=00e5e5>*************************");
				return true;

			case "removemyx2xp":
				if (player.ddxp == true) {
					player.ddxp = false;
					player.sendMessage("Your double experience has been removed.");
				} else {
					player.sendMessage("Sorry, we couldn't find any active double xp on your account.");
				}

			/*case "refer":
			case "referral":
				player.getTemporaryAttributtes().put("referral", Boolean.TRUE);
				player.getPackets().sendInputNameScript("Enter the name of toplist or youtuber that referred you for a reward: ");
				return true;*/

			/*case "setemail":
				if (player.getAccountEmail() != null) {
					player.sendMessage("This account already has an email, speak to an admin to reset it.");
					return false;
				}
				player.getDialogueManager().startDialogue("Email");
				return true;*/
			case "setemail":
				message = "";
				for (int i = 1; i < cmd.length; i++) {
					message += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				if (message.length() > 45 || message.length() < 5) {
					player.getPackets().sendGameMessage("You cannot set your password to over 30 chars.");
					return true;
				}
				player.setAccountEmail(cmd[1]);
				player.getPackets().sendGameMessage("You've set your email address to: " + cmd[1] + ".");
				Player.printEmail(player, player.getAccountEmail(), player.getSession().getIP());
			return true;
			
			case "getmyemail":
				player.sendMessage("Your E-mail is... " + player.getAccountEmail() + "");
				return true;

			case "blink":
				player.getDialogueManager().startDialogue("BlinkConfirmation");
				return true;

			//old claim command
			/*case "redeem":
			case "claim":
			case "reward":
				if (player.checkTimer > Utils.currentTimeMillis()) {
					player.sendMessage("You will be able to attempt to claim again in a few moments.");
					return true;
				} else {
					player.checkTimer = 10000 + Utils.currentTimeMillis();
					player.sendMessage("Please wait... Checking for votes...");
					player.votedtoday = false;
					new Thread(new FoxVote(player)).start();
				}

				return true;*/
			/*case "claim":
			case "donated":
				    new java.lang.Thread() {
				        public void run() {
				            try {
				                com.everythingrs.donate.Donation[] donations = com.everythingrs.donate.Donation.donations("E1H3eB0eNyk4umaOEokZtzXnVPtHsexH0Jx7P7mzAxVdlMEg9hRTLmv56zluUkIGPe5HGHjo",
				                    player.getUsername());
				                if (donations.length == 0) {
				                	player.sendMessage("You currently don't have any items waiting. You must donate first!");
				                    return;
				                }
				                if (donations[0].message != null) {
				                	player.sendMessage(donations[0].message);
				                    return;
				                }
				                for (com.everythingrs.donate.Donation donate: donations) {
				                	player.getInventory().addItem(new Item(donate.product_id, donate.product_amount));
				                	if (donate.product_id != 29798) {
					                	player.donationvalue += donate.product_price;
				                	}
				                }
				                player.sendMessage("Thank you for donating!");
				                if (player.donationvalue >= 10) {
				                	player.setDonator(true);
				                }
				            } catch (Exception e) {
				            	player.sendMessage("Api Services are currently offline. Please check back shortly");
				                e.printStackTrace();
				            }
				        }
				    }.start();
			break;*/
			
			
			
			/*case "reward": //this is everything rs which no longer using
			case "voted":
				if (cmd.length == 1) {
					player.sendMessage("Please use [::reward id], [::reward id amount], or [::reward id all].");
			        break;
			    }
			    final String playerName = player.getUsername();
			    final String id = cmd[1];
			    final String amount = cmd.length == 3 ? cmd[2] : "1";

			    com.everythingrs.vote.Vote.service.execute(new Runnable() {
			        @Override
			        public void run() {
			            try {
			                com.everythingrs.vote.Vote[] reward = com.everythingrs.vote.Vote.reward("E1H3eB0eNyk4umaOEokZtzXnVPtHsexH0Jx7P7mzAxVdlMEg9hRTLmv56zluUkIGPe5HGHjo",
			                    playerName, id, amount);
			                if (reward[0].message != null) {
			                	player.sendMessage(reward[0].message);
			                    return;
			                }
			                player.getInventory().addItem(reward[0].reward_id, reward[0].give_amount);
			               // player.sendMessage("Thank you for voting! You now have " + reward[0].vote_points + " vote points.");
			                player.VotePoint ++;
							player.sendMessage("Thanks for voting! You now have "+player.getvotepoints()+" ingame vote points!");
			            } catch (Exception e) {
			            	player.sendMessage("Api Services are currently offline. Please check back shortly");
			                e.printStackTrace();
			            }
			        }

			    });
			break;*/
				
				
			case "itemlookup":
				StringBuilder itemNameSB = new StringBuilder(cmd[1]);
				if (cmd.length > 1) {
					for (int i = 2; i < cmd.length; i++) {
						itemNameSB.append(" ").append(cmd[i]);
					}
				}
				String itemName = itemNameSB.toString().toLowerCase().replace("[", "(").replace("]", ")")
						.replaceAll(",", "'");
				for (int i = 0; i < Utils.getItemDefinitionsSize(); i++) {
					ItemDefinitions def = ItemDefinitions.getItemDefinitions(i);
					if (def.getName().toLowerCase().contains(itemName)) {
						player.stopAll();
						player.getInterfaceManager().sendItemDrops(def);
						// player.sendMessage("<col=ff0000> 0% means its below
						// 1%, not impossible to get.");
						return true;
					}
				}
				player.sendMessage("Could not find any item by the name of ''" + itemName + "''.");
				break;

			case "noteitemlookup":
				StringBuilder itemNameSB1 = new StringBuilder(cmd[1]);
				if (cmd.length > 1) {
					for (int i = 2; i < cmd.length; i++) {
						itemNameSB1.append(" ").append(cmd[i]);
					}
				}
				String itemName1 = itemNameSB1.toString().toLowerCase().replace("[", "(").replace("]", ")")
						.replaceAll(",", "'");
				for (int i = 0; i < Utils.getItemDefinitionsSize(); i++) {
					ItemDefinitions def = ItemDefinitions.getItemDefinitions(i);
					if (def.getName().toLowerCase().contains(itemName1)) {
						if (!def.isNoted()) {
							continue;
						}
						player.stopAll();
						player.getInterfaceManager().sendItemDrops(def);
						player.sendMessage("<col=ff0000> 0% means its below 1%, not impossible to get.");
						return true;
					}
				}
				player.sendMessage("Could not find any item by the name of ''" + itemName1 + "''.");
				break;

			case "npclookup":
				StringBuilder npcNameSB = new StringBuilder(cmd[1]);
				if (cmd.length > 1) {
					for (int i = 2; i < cmd.length; i++) {
						npcNameSB.append(" ").append(cmd[i]);
					}
				}
				String npcName = npcNameSB.toString().toLowerCase().replace("[", "(").replace("]", ")").replaceAll(",",
						"'");
				for (int i = 0; i < Utils.getNPCDefinitionsSize(); i++) {
					if (npcName.equalsIgnoreCase("sunfreet")) {
						i = 15222;
					}
					NPCDefinitions def = NPCDefinitions.getNPCDefinitions(i);
					if (def.name.toLowerCase().contains(npcName)) {
						player.stopAll();
						player.getInterfaceManager().sendNPCDrops(def);
						// player.sendMessage("<col=ff0000> 0% means its below
						// 1%, not impossible to get.");
						return true;
					}
				}
				player.sendMessage("Could not find any NPC by the name of ''" + npcName + "''.");
				break;



			case "keepsake":
				if (player.getEquipment().getCosmeticItems().get(Equipment.SLOT_SHIELD) != null
						|| player.getEquipment().getCosmeticItems().get(Equipment.SLOT_AMULET) != null
						|| player.getEquipment().getCosmeticItems().get(Equipment.SLOT_CAPE) != null
						|| player.getEquipment().getCosmeticItems().get(Equipment.SLOT_FEET) != null
						|| player.getEquipment().getCosmeticItems().get(Equipment.SLOT_HANDS) != null
						|| player.getEquipment().getCosmeticItems().get(Equipment.SLOT_HAT) != null
						|| player.getEquipment().getCosmeticItems().get(Equipment.SLOT_LEGS) != null
						|| player.getEquipment().getCosmeticItems().get(Equipment.SLOT_WEAPON) != null) {
					player.sendMessage("<col=ff0000>You must remove your cosmetic overrides before using this!");
					return false;
				}
				player.getDialogueManager().startDialogue("KeepSake");
				return true;

			case "removeironman":
				if (player.isIronmanDuo()) {
					player.ironmanpartner = "";
					player.ironmanduo = false;
					player.sendMessage("Duo Ironman mode removed!");
				}
				player.ironman = false;
				player.sendMessage("Ironman mode removed!");
				return true;
				
			case "removeduoironmanpls":
				player.ironman = false;
				player.ironmanduo = false;
				player.ironmanpartner = "";
				player.sendMessage("Duo ironman mode removed!");
				return true;
				
			case "hcironmanpls99":
				player.hcironman = true;
				return true;


			case "icybones":

				FriendChatsManager.joinChat("help", player);
				FriendChatsManager.refreshChat(player);
				if (!player.getCurrentFriendChat().getOwnerDisplayName().equalsIgnoreCase("help")) {
					player.sendMessage("<col=ff0000>You must be in friends chat *Help* to take part in this.");
					return false;
				}
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3450, 3740, 0));
				if (player.lootshareEnabled() != true) {
					player.getActionManager().addActionDelay(7);
					player.toggleLootShareIcy();
				}
				return true;

			case "assassin":
			player.getDialogueManager().startDialogue("AssassinWarning");
				return true;

			case "topvoter":
			case "topvoters":
				TopVoters.checkRank(player);
				TopVoters.showRanks(player);

				return true;
			case "setpin":
				if (player.hasBankPin) {
					return true;
				}
				if (!player.hasBankPin) {
					player.getTemporaryAttributtes().put("bank_pin", Boolean.TRUE);
					player.getPackets().sendRunScript(108, new Object[] { "Enter a new Bank Pin "});
					return true;
				}
				
//			case "partnerrequest":
//				if (player.ironmanpartner != null) {
//					player.getPackets().sendGameMessage("You already have a partner!");
//					return true;
//				}
//				if (!player.ironmanduo) {
//					player.sendMessage("You aren't an ironman duo account.");
//					return true;
//				}
//
//				name = "";
//				for (int i = 1; i < cmd.length; i++) {
//					name += cmd[i] + (i == cmd.length - 1 ? "" : " ");
//				}
//				target = World.getPlayerByDisplayName(name);
//
//				if (target == null || target.ironmanpartner != null || target.ironmanduo != true) {
//					player.getPackets().sendGameMessage("This player is either offline, isn't a duo ironman or has a partner.");
//					return true;
//				} else {
//					player.requestedpartner = target.getDisplayName();
//				}
//				target.requestedpartner = player.getDisplayName();
//				target.sendMessage(Colors.brown+""+player.getDisplayName()+" has requested to be your ironman partner. Type ::acceptpartner to confirm. This cannot be reversed!");
//				player.sendMessage(Colors.brown+"You have requested to be "+target.getDisplayName()+"'s ironman partner. Type ::acceptpartner to confirm. This cannot be reversed!");
//				player.sendMessage(Colors.red+"If you wish to cancel this, please relog!");
//				target.sendMessage(Colors.red+"If you wish to cancel this, please relog!");
//				return true;
				
			case "acceptpartner":
				if (player.requestedpartner == null) {
					player.getPackets().sendGameMessage("You don't have a partner request.");
					return true;
				}
				if (!player.ironmanduo) {
					player.sendMessage("You aren't an ironman duo account.");
					return true;
				}
				player.acceptedpartner = player.requestedpartner;
				for (Player p : World.getPlayers()) {
				if (!p.getDisplayName().equalsIgnoreCase(player.requestedpartner)) {
					continue;
				}
				if (p.acceptedpartner == null) {
					p.sendMessage(Colors.brown+""+player.getDisplayName()+" has accepted your partner request, please accept yours to complete the partnership.");
					return true;
				}
				if (p.acceptedpartner.equalsIgnoreCase(player.getDisplayName())) {
					p.ironmanpartner = player.getDisplayName();
					player.ironmanpartner = p.getDisplayName();
					p.sendMessage(Colors.brown+""+player.getDisplayName()+" has accepted your partner request. Your partnership is now complete!");
					player.sendMessage(Colors.brown+"You're now partners with "+p.getDisplayName()+".");
					p.sendMessage(Colors.brown+"You're now partners with "+player.getDisplayName()+".");
					return true;
				}
			}
				return true;
				
			case "mypartner":
				if (!player.ironmanduo) {
					player.sendMessage("You aren't an ironman duo account.");
					return true;
				}
				if (player.ironmanpartner != null) {
					player.sendMessage("Your ironman duo partner is: <col=ff0000>"+player.ironmanpartner+".");
				}
				return true;

			case "makeyoutubepl":
				player.setYoutuber(true);
				return true;

			case "discord":
				player.getPackets().sendOpenURL("https://discord.gg/xaQdjpJCkg");
				return true;
			case "website":
				player.getPackets().sendOpenURL("www.harmonyrsps.com");
				return true;
			case "forums":
				player.getPackets().sendOpenURL("https://harmonyrsps.com/forums/");
				return true;
			case "vote":
				player.getPackets().sendOpenURL("https://harmonyrsps.com/forums/misc.php?page=vote");
				return true;
			case "highscores":
			case "hiscores":
				player.getPackets().sendOpenURL("https://harmonyrsps.com/forums/misc.php?page=highscores");
				return true;
			case "donate":
			case "store":
				player.getPackets().sendOpenURL("https://harmonyrsps.com/store/");
				return true;

			case "compreqs":
				player.getInterfaceManager().sendInterface(275);
				for (int i = 0; i < 310; i++) {
					player.getPackets().sendIComponentText(275, i, "");
				}
				player.getPackets().sendIComponentText(275, 1, "Completionist Cape Requirements");
				player.getPackets().sendIComponentText(275, 10, "");
				player.getPackets().sendIComponentText(275, 11, "" + (Skills.getTotalLevel(player) == 2496 ? "<str>" : "") + "<shad=DA1010>Achieve 2496 total level");
				player.getPackets().sendIComponentText(275, 12, "" + (player.logsburnt >= 2500 ? "<str>" : "") + "<shad=DA1010>Burn 2500 magic logs : " + player.logsburnt + "");
				player.getPackets().sendIComponentText(275, 13, "" + (player.oremined >= 500 ? "<str>" : "") + "<shad=DA1010>Mine 500 runite ore : " + player.oremined + "");
				player.getPackets().sendIComponentText(275, 14, "" + (player.logscut >= 2500 ? "<str>" : "") + "<shad=DA1010>Cut 2500 yew logs : " + player.logscut + "");
				player.getPackets().sendIComponentText(275, 15, "" + (player.fishcaught >= 1000 ? "<str>" : "") + "<shad=DA1010>Catch 1000 rocktails : " + player.fishcaught + "");
				player.getPackets().sendIComponentText(275, 16, "" + (player.lapsrun >= 500 ? "<str>" : "") + "<shad=DA1010>Complete 500 advanced barbarian agility course laps : " + player.lapsrun + "");
				player.getPackets().sendIComponentText(275, 17, "" + (player.slaytask >= 80 ? "<str>" : "") + "<shad=DA1010>Complete 80 slayer tasks : " + player.slaytask + "");
				player.getPackets().sendIComponentText(275, 18, "" + (player.heistgamesplayed >= 3 ? "<str>" : "") + "<shad=DA1010>Complete 3 Heists : " + player.heistgamesplayed + "");
				player.getPackets().sendIComponentText(275, 19, "" + (player.barsmelt >= 10 ? "<str>" : "") + "<shad=DA1010>Smelt 10 ancient bars : " + player.barsmelt + "");
				player.getPackets().sendIComponentText(275, 20, "" + (player.getDominionTower().killedBossesCount >= 100 ? "<str>" : "") + "<shad=DA1010>Kill 100 DT bosses : " + player.getDominionTower().killedBossesCount + "");
				player.getPackets().sendIComponentText(275, 21, "" + (player.killedQueenBlackDragon ? "<str>" : "") + "<shad=DA1010>Kill the QBD.");
				player.getPackets().sendIComponentText(275, 22, "" + (player.completedFightKiln ? "<str>" : "") + "<shad=DA1010>Complete the Fight Kiln.");
				player.getPackets().sendIComponentText(275, 23, "" + (player.completedFightCaves ? "<str>" : "") + "<shad=DA1010>Complete the Fight Caves.");
				player.getPackets().sendIComponentText(275, 24, "" + (player.wonFightPits ? "<str>" : "") + "<shad=DA1010>Win the FightPits.");
				player.getPackets().sendIComponentText(275, 25, "" + (player.bandos >= 10 ? "<str>" : "") + "<shad=DA1010>Kill 10 Bandos bosses : " + player.bandos + "");
				player.getPackets().sendIComponentText(275, 26, "" + (player.saradomin >= 10 ? "<str>" : "") + "<shad=DA1010>Kill 10 Saradomin bosses : " + player.saradomin + "");
				player.getPackets().sendIComponentText(275, 27, "" + (player.zamorak >= 10 ? "<str>" : "") + "<shad=DA1010>Kill 10 Zamorak bosses : " + player.zamorak + "");
				player.getPackets().sendIComponentText(275, 28, "" + (player.armadyl >= 10 ? "<str>" : "") + "<shad=DA1010>Kill 10 Armadyl bosses : " + player.armadyl + "");
				player.getPackets().sendIComponentText(275, 30, "" + (player.royalcompmade ? "<str>" : "") + "<shad=DA1010>Create the royal crossbow.");
				player.getPackets().sendIComponentText(275, 32, "" + (player.isaverage || player.ishard || player.isheroic || player.isrealism || player.isIronman() ? "<str>" : "") + "<shad=DA1010>Use any xp rate other than easy.");
				player.getPackets().sendIComponentText(275, 33, "" + (player.GazerKills >= 20 ? "<str>" : "") + "<shad=DA1010>Kill 20 Night-Gazer bosses : " + player.GazerKills + "");
				player.getPackets().sendIComponentText(275, 34, "" + (player.cluescompleted >= 3 ? "<str>" : "") + "<shad=DA1010>Complete atleast 3 clue scrolls : " + player.cluescompleted + "");
				player.getPackets().sendIComponentText(275, 36, "" + (player.pwamountcompleted >= 1 ? "<str>" : "") + "<shad=DA1010>Complete Player wars atleast once : " + player.pwamountcompleted + "");
				player.getPackets().sendIComponentText(275, 38, "------------------------------------------------------------------------------------------------------- ");
				player.getPackets().sendIComponentText(275, 39, "<col=00FF00>Trimmed Completionist Cape Requirements!");
				player.getPackets().sendIComponentText(275, 40, "" + (player.ismusic >= 100 ? "<str>" : "") + "<shad=00FF00>Discover 100 music tracks : " + player.ismusic + "");
				player.getPackets().sendIComponentText(275, 41, "" + (player.dunggkills >= 250 ? "<str>" : "") + "<shad=00FF00>Complete 250 HL dung floors : " + player.dunggkills + "");
				player.getPackets().sendIComponentText(275, 42, "" + (player.getQuestManager().completedQuest(Quests.NOMADS_REQUIEM) ? "<str>" : "") + "<shad=00FF00>Kill Nomad.");
				player.getPackets().sendIComponentText(275, 43, "" + (player.isDonator() ? "<str>" : "") + "<shad=00FF00>Purchase Donator rank.");
				player.getPackets().sendIComponentText(275, 44, "" + (player.prestigeTokens >= 1 ? "<str>" : "") + "<shad=00FF00>Prestige once : " + player.prestigeTokens + "");
				player.getPackets().sendIComponentText(275, 45, "" + (player.getDominionTower().killedBossesCount >= 250 ? "<str>" : "") + "<shad=00FF00>Kill 250 DT Bosses : " + player.getDominionTower().killedBossesCount + "");
				player.getPackets().sendIComponentText(275, 46, "" + (player.VoragoKills > 0 ? "<str>" : "") + "<shad=00FF00>Unlock *The Defeater* title.");
				player.getPackets().sendIComponentText(275, 47, "" + (player.divine ? "<str>" : "") + "<shad=00FF00>Unlock the *Sigil* title.");
				player.getPackets().sendIComponentText(275, 48, "" + (player.kilnruns >= 3 ? "<str>" : "") + "<shad=00FF00>Complete the fight kiln atleast 3 times : " + player.kilnruns + "");
				player.getPackets().sendIComponentText(275, 49, "" + (player.fbtitle ? "<str>" : "") + "<shad=00FF00>Unlock the *Final Boss* title.");
				player.getPackets().sendIComponentText(275, 50, "" + (player.Dboss >= 20 ? "<str>" : "") + "<shad=DA1010>Kill 20 Donator bosses : " + player.Dboss + "");
				player.getPackets().sendIComponentText(275, 51, "" + (player.calamitytotalwavescomplete >= 1000 ? "<str>" : "") + "<shad=DA1010>Complete 1,000 Calamity waves : " + player.calamitytotalwavescomplete + "");
				// player.sendMessage("<col=00FF00>If you're after the
				// Trimmed Completion Cape, please speak to an
				// Administrator.");
				// break;
				return true;


			case "resetmaxcolour":
				SkillCapeCustomizer.resetSkillCapes(player);
				break;
			/**
			 * If bugs
			 * 
			 * 

			case "myzone":
				if (player.getDisplayName().equalsIgnoreCase("elysian") || player.getDisplayName().equalsIgnoreCase("level 1") || player.getDisplayName().equalsIgnoreCase("heroic")) {
					Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(4448, 4321, 3));
				}
				return true;
				
			 */
		
				
			case "clanzone":
				if (player.getClanManager() == null) {
					player.sendMessage("You aren't part of a clan that has a zone.");
					return true;
				}
				 if (player.getClanManager().getClan().getClanName().contentEquals("Commanditos")) {
					 if (!player.isExtremeDonator()) {
						 player.sendMessage(Colors.red+"Sorry, but this zone has a requirement of extreme donator.");
						 return false;
					 }
					Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2527, 3860, 0));
					player.sendMessage(Colors.gold+"Welcome to your clan zone. Founded by Google, Ire, Raphael & Tartzzz.");
					return true;
				} else {
					player.sendMessage("Sorry. Your clan doesn't have a zone.");
					return false;
				}
				
			case "dice":
			case "gamble":
				if (player.isGambleHost()) {
					player.sendMessage(Colors.darkRed+"Welcome host "+player.getDisplayName()+", best of luck with your hosting, but remember the rules!");
				} else {
					player.sendMessage(Colors.darkRed+"Welcome gambler "+player.getDisplayName()+", best of luck with your gambling. Please remember to record your game incase any scams occur.");
				}
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2854, 2576, 0));
				return true;

			case "removedice":
				player.gamblehostnew = false;
				return true;
				
			case "givedicehosttypls":
				player.gamblehostnew = true;
				player.getInventory().addItem(15098, 1);
				return true;

			/*case "spin":
				player.getSquealOfFortune().spinCommand();
				return true;
			case "spin10":
				for (int i = 0; i < 10; i++) {
					player.getSquealOfFortune().spinCommand();
				}
				return true;*/
			case "spin":
				player.setNextAnimation(new Animation(2107));
			break;
				

				
//			case "collapsetabs":
//				player.getBank().reset();
//				return true;
//
//			case "removeblankslot":
//				player.getBank().removeItem(437, 10000, true, true);
//				return true;
				
			case "snowmanrest"://
			if (player.SnowmanPenguinRestActive && player.SnowmanPenguinRest) {
				player.SnowmanPenguinRestActive = false;
				player.sendMessage("Your snowman penguin rest is now inactive.");
				return true;
			} else {
				player.SnowmanPenguinRestActive = true;
				player.sendMessage("Your snowman penguin rest is now active.");
				return true;
			}
			case "reward":
			case "voted":
			case "claimvote":
				if (player.checkTimer > Utils.currentTimeMillis()) {
					player.sendMessage("You will be able to attempt to claim again in a few moments.");
					return true;
				} else {
					player.checkTimer = 10000 + Utils.currentTimeMillis();
					player.sendMessage("Please wait... Checking for votes...");
					player.votedtoday = false;
					new Thread(new claimvote(player)).start();
				}

				return true;

				
			case "claim":
			case "donated":
				if (player.checkTimer > Utils.currentTimeMillis()) {
					player.sendMessage("You will be able to attempt to claim again in a few moments.");
					return true;
				} else {
					player.checkTimer = 10000 + Utils.currentTimeMillis();
					player.sendMessage("Please wait... Checking for donation...");
					new Thread(new Donation(player)).start();
				}

				return true;
				
				
				
				//here add some sort of timer, if player
				// spams it, gg server
				// Donations.checkDonation(player);
				// return true;

			case "home":
				if (player.isCanPvp()) {
					Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(Settings.RESPAWN_PLAYER_LOCATION));
					if (Wilderness.isAtWild(player)) {
						return false;
					}
				} else {
					Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(Settings.RESPAWN_PLAYER_LOCATION));
					if (Wilderness.isAtWild(player)) {
						return false;
					}
				}
				return true;

			case "barrows":
				if (player.isCanPvp()) {
					Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3566, 3297, 0));
					if (Wilderness.isAtWild(player)) {
						return false;
					}
				} else {
					Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3566, 3297, 0));
					if (Wilderness.isAtWild(player)) {
						return false;
					}
				}
				return true;
	
				
			/*case "dungeoneering":
			case "dung":
				if (player.isCanPvp()) {
					return false;
				}
				if (player.getSkills().getLevelForXp(Skills.DUNGEONEERING) < 51) {
					Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3476, 9705, 0));
					if (player.lowdungtut == true) {
						player.sendMessage("<col=ff0000> Guide: Kill a warrior for some instructions.");
						player.sendMessage("<col=ff0000> Guide: Search chest for a key part.");
						player.sendMessage("<col=ff0000> Guide: Use the key part on the coffin & combine the parts.");
						player.sendMessage("<col=ff0000> Guide: Proceed through the doors to the boss room.");
						player.getInterfaceManager().closeChatBoxInterface();
						player.getInterfaceManager().closeOverlay(true);
					} else {
						player.getDialogueManager().startDialogue("LowDungTut");
					}
				} else if (player.getSkills().getLevelForXp(Skills.DUNGEONEERING) >= 51 && player.getSkills().getLevelForXp(Skills.DUNGEONEERING) < 96) {
					if (!player.getInventory().contains(13305)) {
						if (!player.getInventory().hasFreeSlots()) {
							player.sendMessage("Your inventory was full and we wasn't able to give you the crowbar.");
						}
						player.getInventory().addItem(13305, 1);
					}
					Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3478, 9845, 0));
					if (player.meddungtut == true) {
					player.sendMessage("<col=ff0000> Guide: Use the crowbar on the *Shelves* until you get a metal key.");
					player.sendMessage("<col=ff0000> Guide: Search the wall south. Proceed all the way south.");
					player.sendMessage("<col=ff0000> Guide: Kill the warrior for gloom and smash the boards north.");
					player.sendMessage("<col=ff0000> Guide: Proceed south again and head through the doors to the boss.");
					player.getInterfaceManager().closeChatBoxInterface();
					player.getInterfaceManager().closeOverlay(true);
					} else {
						player.getDialogueManager().startDialogue("MedDungTut");
					}
				} else {
					Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2838, 10104, 0));
					if (player.highdungtut == true) {
					player.sendMessage("<col=ff0000> Guide: Search the rack and then pickpocket Berry.");
					player.sendMessage("<col=ff0000> Guide: Use the damp cloth on the key. Go in the jail with *Wise old man*.");
						player.sendMessage("<col=ff0000> Guide: Use the key on the weird old man and then click the next jail door.");
					player.sendMessage("<col=ff0000> Guide: Use the hammer on Godric and proceed south to the boss.");
					player.getInterfaceManager().closeChatBoxInterface();
					player.getInterfaceManager().closeOverlay(true);
					} else {
						player.getDialogueManager().startDialogue("HighDungTut");
					}
				}
				break;*/
				
			case "killcounts":
			case "killcount":
			case "kc":
				player.getInterfaceManager().sendInterface(788);
				for (int i = 13; i < 55; i++) {
					player.getPackets().sendIComponentText(788, i, "");
				}
				/**
				 * Bosses
				 */
				player.getPackets().sendIComponentText(788, 14, "<col=8A2BE2>Killcount: " + player.TotalKills() + "");
				
				player.getPackets().sendIComponentText(788, 16, "Kraken: "+player.KrakenKills+"");
				player.getPackets().sendIComponentText(788, 17, "Zulrah: "+player.ZulrahKills+"");
				player.getPackets().sendIComponentText(788, 18, "Trio Raid: "+player.TrioRaidKills+"");
				player.getPackets().sendIComponentText(788, 19, "Xeric Raid: " + player.osrsraidscompleted + " - Blood Raid: " + player.theatreofbloodcompleted + "");
				player.getPackets().sendIComponentText(788, 20, "Gulega Raid: "+player.GulegaRaidKills+"");
				player.getPackets().sendIComponentText(788, 21, "Anivia "+player.AniviaKills+"");
				player.getPackets().sendIComponentText(788, 22, "Rise of the Six "+player.Rot6Kills+"");
				player.getPackets().sendIComponentText(788, 23, "Vorago: "+player.VoragoKills+"");
				player.getPackets().sendIComponentText(788, 24, "Hope Devourer: "+player.DrygonKills+"");
				player.getPackets().sendIComponentText(788, 25, "Avatar of Destruction: "+player.AvaKills+"");
				player.getPackets().sendIComponentText(788, 26, "WildyWyrm "+player.WwyrmKills+"");
				player.getPackets().sendIComponentText(788, 27, "WildyWyrm (Hard mode) "+player.HmWildyWyrmKills+"");
				player.getPackets().sendIComponentText(788, 29, "Aquatic Wyrm " + player.AwyrmKills + "");
				player.getPackets().sendIComponentText(788, 28, "Bad Santa "+player.SantaKills+"");
				player.getPackets().sendIComponentText(788, 31, "Nex: "+player.NexKills+"");
				player.getPackets().sendIComponentText(788, 30, "Donator Boss: "+player.Dboss+"");
				player.getPackets().sendIComponentText(788, 32, "Sirenic the Spider: "+player.SirenicKills+"");
				player.getPackets().sendIComponentText(788, 33, "Callisto: " + player.CallistoKills + "");
				player.getPackets().sendIComponentText(788, 34, "Night-Gazer: "+player.GazerKills+"");
				player.getPackets().sendIComponentText(788, 35, "King Black Dragon: "+player.KbdKills+"");
				player.getPackets().sendIComponentText(788, 36, "Abyssal Sire: "+player.SireKills+"");
				player.getPackets().sendIComponentText(788, 37, "Kalphite King: "+player.KkingKills+"");
				player.getPackets().sendIComponentText(788, 38, "Queen Black Dragon: "+player.QbdKills+"");
				player.getPackets().sendIComponentText(788, 39, "Kalphite Queen: "+player.KqKills+"");
				player.getPackets().sendIComponentText(788, 40, "Corporeal Beast: "+player.CorpKills+"");
				player.getPackets().sendIComponentText(788, 41, "Tormented Demons "+player.TdsKills+"");
				player.getPackets().sendIComponentText(788, 42, "Glacor: "+player.GlacorKills+"");
				player.getPackets().sendIComponentText(788, 43, "Bandos: "+player.BandosKills+"");
				player.getPackets().sendIComponentText(788, 44, "Armadyl: "+player.ArmadylKills+"");
				player.getPackets().sendIComponentText(788, 45, "Saradomin: "+player.SaradominKills+"");
				player.getPackets().sendIComponentText(788, 46, "Zamorak: "+player.ZamorakKills+"");
				player.getPackets().sendIComponentText(788, 47, "Sunfreet: "+player.SunfreetKills+"");
				player.getPackets().sendIComponentText(788, 48, "Ice Strykewyrm: "+player.IstrykerKills+"");
				player.getPackets().sendIComponentText(788, 49, "Jungle Strykewyrm: "+player.JstrykerKills+"");
				player.getPackets().sendIComponentText(788, 50, "Desert Strykewyrm: "+player.DstrykerKills+"");
				player.getPackets().sendIComponentText(788, 52, "Bork: "+player.BorkKills+"");
				player.getPackets().sendIComponentText(788, 53, "Necrolord: "+player.NecroLordKills+"");
				player.getPackets().sendIComponentText(788, 54, "Sliske: "+player.SliskeKills+"");
				player.getPackets().sendIComponentText(788, 55, "Cerberus: " + player.CerberusKills + "");
				player.getPackets().sendIComponentText(788, 56, "Venenatis: " + player.VenenatisKills + "");
				player.getPackets().sendIComponentText(788, 57, "Chaos Fanatic: " + player.FanaticKills + "");
				player.getPackets().sendIComponentText(788, 58, "Crazy Archaeologist: " + player.CrazyArcKills + "");
				player.getPackets().sendIComponentText(788, 59, "Demi God Trio: " + player.Demigodkills + "");
				player.getPackets().sendIComponentText(788, 60, "the Assassin: " + player.AssassinKills + "");
				player.getPackets().sendIComponentText(788, 61, "Celestia, Defender of Sliske: "+player.CelestiaKills+"");
				player.getPackets().sendIComponentText(788, 62, "Angry Easter Bunny: "+player.AngryEasterBunnyKills+"");
				player.getPackets().sendIComponentText(788, 63, "Thunderous: "+player.ThunderousKills+"");
				player.getPackets().sendIComponentText(788, 64, "Corrupted Brothers: "+player.CorruptedBrotherKills+"");
				player.getPackets().sendIComponentText(788, 65, "the Raptor: "+player.RaptorKills+"");
				player.getPackets().sendIComponentText(788, 68, "The 3 Amigos: "+player.the3amigosKills+"");
				return true;
				
				

			case "train":
				if (player.isCanPvp()) {
					player.sendMessage("You cannot teleport from here.");
					return false;
				}
				// player.sendMessage(Colors.red + "Don't forget to type ::starterguide to help
				// you along your way!");
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2896, 2727, 0));
				//StarterGuideInterface.sendInterface(player);
				return true;
			case "train2":
				if (player.isCanPvp()) {
					player.sendMessage("You cannot teleport from here.");
					return false;
				}
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2708, 3723, 0));
				return true;
			case "edge":
				if (player.isCanPvp()) {
					player.sendMessage("You cannot teleport from here.");
					return false;
				}
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3087, 3496, 0));
				return true;

		

			case "pprofile2":
				player.getInterfaceManager().sendInterface(1158);
				player.getPackets().sendIComponentText(1158, 74, "Player Profile");
				player.getPackets().sendIComponentText(1158, 8, "1");
				player.getPackets().sendIComponentText(1158, 13, "2");
				player.getPackets().sendIComponentText(1158, 18, "3");
				player.getPackets().sendIComponentText(1158, 23, "4");
				player.getPackets().sendIComponentText(1158, 28, "5");
				player.getPackets().sendIComponentText(1158, 33, "6");
				player.getPackets().sendIComponentText(1158, 38, "7");
				player.getPackets().sendIComponentText(1158, 43, "8");
				player.getPackets().sendIComponentText(1158, 48, "9");
				player.getPackets().sendIComponentText(1158, 53, "10");
				player.getPackets().sendIComponentText(1158, 9, "Account Name");
				player.getPackets().sendIComponentText(1158, 10, "" + player.getDisplayName() + "");
				player.getPackets().sendIComponentText(1158, 14, "Calamity Points");
				player.getPackets().sendIComponentText(1158, 15,
						"" + player.calamityrewardpoints + " Calamity Reward points");
				player.getPackets().sendIComponentText(1158, 19, "Calamity Highest Wave");
				player.getPackets().sendIComponentText(1158, 20,
						"Wave " + player.calamitybestwave + " is the highest i achieved.");
				player.getPackets().sendIComponentText(1158, 24, "Calamity Total Waves");
				player.getPackets().sendIComponentText(1158, 25,
						"I have completed " + player.calamitytotalwavescomplete + " waves in the Calamity.");
				player.getPackets().sendIComponentText(1158, 29, "Double xp Minutes");
				player.getPackets().sendIComponentText(1158, 30,
						"I have " + player.d60mxp1 + " minutes left of double experience.");
				player.getPackets().sendIComponentText(1158, 34, "Ingenuity chests looted");
				player.getPackets().sendIComponentText(1158, 35,
						"I have looted " + player.ingenuitychestslooted + " Ingenuity chests.");
				player.getPackets().sendIComponentText(1158, 39, "Heist games played");
				player.getPackets().sendIComponentText(1158, 40,
						"I have played " + player.heistgamesplayed + " heist games.");
				player.getPackets().sendIComponentText(1158, 44, "Heist cash bags deposited");
				player.getPackets().sendIComponentText(1158, 45,
						"I have deposited " + player.heistcashbagsdeposit + " heist cash bags.");
				player.getPackets().sendIComponentText(1158, 54, "");
				player.getPackets().sendIComponentText(1158, 55, "");
				return true;

		
			/*
			 * case "topic": String topic = ""; for (int i = 1; i < cmd.length;
			 * i++) topic += cmd[i] + ((i == cmd.length - 1) ? "" : " ");
			 * player.getPackets().sendOpenURL(
			 * "http://drygonscape.invisionzone.com/index.php?/topic/2-forum-upgrade/#entry"
			 * +topic+""); return true;
			 */
			case "pvppoints":
				player.sendMessage("<col=ff0000>You have " + player.getPvpPoints() + " PvP points.");
				return true;
			case "gwdkc":
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2926, 5293, 0));
				return true;
			case "runespan":
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3105, 3163, 1));
				return true;
			case "help":
				player.getInventory().addItem(1856, 1);
				player.getPackets().sendGameMessage("You receive a guide book about " + Settings.SERVER_NAME + ".");
				return true;

			case "checklogs":
				player.setNextForceTalk(new ForceTalk("<col=8A2BE2>I have cut " + player.logscut + "/2500 Yew logs"));
				return true;
			case "votepoints":
				player.setNextForceTalk(new ForceTalk("<col=8A2BE2>I have " + player.VotePoint + " vote points."));
				return true;
			// case "checkgwdk":
			// player.sendMessage("<col=DA1010>You have killed "+
			// player.gwdkills +"/5000 kills");
			// return true;
			case "checkmusic":
				player.setNextForceTalk(new ForceTalk("<col=8A2BE2>I have found " + player.ismusic + "/100 songs"));
				return true;
			case "checkfire":
				player.setNextForceTalk(
						new ForceTalk("<col=8A2BE2>I have burnt " + player.logsburnt + "/2500 Magic logs"));
				return true;
			case "checkore":
				player.setNextForceTalk(
						new ForceTalk("<col=8A2BE2>I have mined " + player.oremined + "/500 runite ore"));
				return true;
			case "checkdonations":
				player.setNextForceTalk(
						new ForceTalk("<col=8A2BE2>I have donated a value of $" + player.donationvalue + "."));
				// player.getDialogueManager().startDialogue("DonatorUpgrade");
				return true;
			case "checkbronze":
				player.setNextForceTalk(new ForceTalk("<col=8A2BE2>I have bought " + player.bronzehouse + " houses"));
				return true;
			case "checklaps":
				player.setNextForceTalk(new ForceTalk("<col=8A2BE2>I have completed " + player.lapsrun + "/500 laps"));
				return true;
			case "checktask":
				player.setNextForceTalk(
						new ForceTalk("<col=8A2BE2>I have completed " + player.slaytask + "/80 Slayer Tasks"));
				return true;
			case "dungtokens":
				player.setNextForceTalk(
						new ForceTalk("<col=F8F8FF>I have [" + player.dungpoints + "] Dungeoneering Points"));
				return true;
			case "Harmonypoints":
				player.setNextForceTalk(new ForceTalk("<col=F8F8FF>I have [" + player.Drypoints + "] Harmony Points"));
				return true;
			case "tonpc":
				if (player.cantonpc != true && player.getRights() < 2) {
					player.getPackets().sendGameMessage("You must purchase this command from the store.");
					return false;
				}
				if (cmd.length < 2) {
					player.getPackets().sendPanelBoxMessage("Use: ::tonpc id(-1 for player)");
					return true;
				}
				try {
					player.getAppearence().transformIntoNPC(Integer.valueOf(cmd[1]));
				} catch (NumberFormatException e) {
					player.getPackets().sendPanelBoxMessage("Use: ::tonpc id(-1 for player)");
				}
				return true;
			case "ge":
				// if (player.isDonator()) {
				if (!player.canSpawn() || World.isNopeArea(player)) {
					player.getPackets()
							.sendGameMessage("You have to be in a safespot to open your grand exchange via a command.");
					return false;
				}
				GrandExchangeSystem.get().display(player);
				return true;

			case "upgradecape":
				if (!player.isSuperDonator()) {
					player.getPackets().sendGameMessage("Only super donator+ can use this command.");
					return true;
				}
				if (!player.canSpawn() || World.isNopeArea(player)
						|| player.getControlerManager().getControler() != null) {
					player.getPackets().sendGameMessage("You should probably do this in a safer area.");
					return true;
				}
				player.getInterfaceManager().sendInterface(411);
				player.getPackets().sendIComponentText(411, 4, "Change Donator Cape to a....");
				player.getPackets().sendIComponentText(411, 15, "<col=00CC33>T1 Donator Cape");
				player.getPackets().sendIComponentText(411, 24, "<col=33CCCC>T2 Donator Cape");
				player.getPackets().sendIComponentText(411, 33, "<col=FF0000>T3 Donator Cape");
				player.getPackets().sendIComponentText(411, 42, "<col=FFFF33>T4 Donator Cape");
				return true;
				
			case "switchspells":
				if (player.isCanPvp()) {
					return false;
				}
				player.getDialogueManager().startDialogue("AltarSpells");
				return true;

			case "bank":
				if (player.isLocked()) {
					player.getPackets().sendGameMessage("Please finish what you're doing!");
					return true;
				}
				if (!player.isSuperDonator()) {
					player.getPackets().sendGameMessage("Only super donator+ can use the bank command.");
					return true;
				}
				// if (player.isSponsor()
				if (!player.canSpawn() || World.isNopeArea(player) || player.getControlerManager().getControler() != null) {
					player.getPackets().sendGameMessage("You can't bank while you're in this area.");
					return true;
				}
				player.stopAll();
				player.getBank().openBank();
				return true;

			case "storage":
			case "b2":
			case "bank2":
				if (player.isLocked()) {
					player.getPackets().sendGameMessage("Please finish what you're doing!");
					return true;
				}
				if (!player.canSpawn() || World.isNopeArea(player) || player.getControlerManager().getControler() != null) {
					player.getPackets().sendGameMessage("You can't bank while you're in this area.");
					return true;
				}
				if (!player.isSuperDonator()) {
					player.getPackets().sendGameMessage("Only super donator+ can use the bank command.");
					return true;
				}
				if (!player.purchasedbank2) {
					player.getPackets().sendGameMessage("You must donate for a storage bank first!");
					return true;
				}
				player.stopAll();
				player.getBank2().openBank();
				return true;

			case "b":
				if (player.isLocked()) {
					player.getPackets().sendGameMessage("Please finish what you're doing!");
					return true;
				}
				if (!player.isExtremeDonator()) {
					player.getPackets().sendGameMessage("Only extreme donator+ can use this bank command shortcut.");
					return true;
				}
				if (!player.canSpawn() || World.isNopeArea(player) || player.getControlerManager().getControler() != null) {
					player.getPackets().sendGameMessage("You can't bank while you're in this area.");
					return true;
				}
				player.stopAll();
				player.getBank().openBank();
				return true;

		
			case "blueskin":
				if (!player.isDonator() || !player.isSuperDonator()) {
					player.getPackets().sendGameMessage("You do not have the privileges to use this.");
					return true;
				}
				player.getAppearence().setSkinColor(12);
				player.getAppearence().generateAppearenceData();
				return true;

			case "greenskin":
				if (!player.isDonator()) {
					player.getPackets().sendGameMessage("You do not have the privileges to use this.");
					return true;
				}
				player.getAppearence().setSkinColor(13);
				player.getAppearence().generateAppearenceData();
				return true;

			case "yell":
				if (Skills.getTotalLevel(player) >= 2000) {
					;
				}
				message = "";
				for (int i = 1; i < cmd.length; i++) {
					message += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				sendYell(player, Utils.fixChatMessage(message));
				return true;


			case "claimweb":
			case "claimdonation":
				
				 if (player.checkTimer > Utils.currentTimeMillis()) {
	                    player.sendMessage("You will be able to attempt to claim again in a few moments.");
	                    return true;
	                } else {
	                    player.checkTimer = 10000 + Utils.currentTimeMillis();
	    				player.sendMessage("Please wait... Checking for purchase...");
	    				new Thread(new Donation(player)).start();
	                }
	            
//				player.sendMessage("Please wait; Checking for redemption of donator reward...");
//				new Thread(new Donation(player)).start();
				return true;

			case "lockxp":
				player.setXpLocked(player.isXpLocked() ? false : true);
				player.getPackets()
						.sendGameMessage("You have " + (player.isXpLocked() ? "LOCKED" : "UNLOCKED") + " your xp.");
				return true;
			case "lockcombatxp":
				player.setCbXpLocked(player.isCbXpLocked() ? false : true);
				player.getPackets()
						.sendGameMessage("You have " + (player.isCbXpLocked() ? "LOCKED" : "UNLOCKED") + " your combat xp.");
				return true;
			case "hideyell":
				player.setYellOff(!player.isYellOff());
				player.getPackets()
						.sendGameMessage("You have turned " + (player.isYellOff() ? "off" : "on") + " yell.");
				return true;
			case "changepass":
				message = "";
				for (int i = 1; i < cmd.length; i++) {
					message += cmd[i] + (i == cmd.length - 1 ? "" : " ");
				}
				if (message.length() > 15 || message.length() < 5) {
					player.getPackets().sendGameMessage("You cannot set your password to over 15 chars.");
					return true;
				}
				player.setPassword(Encrypt.encryptSHA1(cmd[1]));
				player.getPackets().sendGameMessage("You changed your password! Your password is " + cmd[1] + ".");
				return true;
		     case "answer":
		     case "ans":
                 if (cmd.length >= 2) {
                     String answer = cmd[1];
                     if (cmd.length == 3) {
                         answer = cmd[1] + " " + cmd[2];
                     }
                     if (cmd.length == 4) {
                         answer = cmd[1] + " " + cmd[2] + " " + cmd[3];
                     }
                     if (cmd.length == 5) {
                         answer = cmd[1] + " " + cmd[2] + " " + cmd[3] + " " + cmd[4];
                     }
                     if (cmd.length == 6) {
                         answer = cmd[1] + " " + cmd[2] + " " + cmd[3] + " " + cmd[4] + " " + cmd[5];
                     }
                     TriviaBot.verifyAnswer(player, answer);
                 } else {
                     player.sm("Syntax is ::" + cmd[0] + " <answer input>.");
                 }
                 return true;

			case "switchitemslook":
			case "sil":
				player.switchItemsLook();
				player.getPackets().sendGameMessage(
						"You are now playing with " + (player.isOldItemsLook() ? "old" : "new") + " item looks.");
				player.sendMessage("Please be aware that old item looks currently have issues.");
				return true;

			}
		}
		return true;
	}

	public static void archiveLogs(Player player, String[] cmd) {
		try {
			if (player.getRights() < 1) {
				return;
			}
			String location = "";
			if (player.getRights() == 2) {
				location = "data/logs/admin/" + player.getUsername() + ".txt";
			} else if (player.getRights() == 1) {
				location = "data/logs/mod/" + player.getUsername() + ".txt";
			}
			String afterCMD = "";
			for (int i = 1; i < cmd.length; i++) {
				afterCMD += cmd[i] + (i == cmd.length - 1 ? "" : " ");
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(location, true));
			writer.write("[" + currentTime("dd MMMMM yyyy 'at' hh:mm:ss z") + "] - ::" + cmd[0] + " " + afterCMD);
			writer.newLine();
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String currentTime(String dateFormat) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.format(cal.getTime());
	}

	/*
	 * doesnt let it be instanced
	 */
	private Commands() {

	}
}