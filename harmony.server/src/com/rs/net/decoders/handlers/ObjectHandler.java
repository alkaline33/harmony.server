package com.rs.net.decoders.handlers;

import com.rs.Settings;
import com.rs.cache.loaders.ObjectDefinitions;
import com.rs.game.Animation;
import com.rs.game.ForceMovement;
import com.rs.game.Graphics;
import com.rs.game.Hit;
import com.rs.game.Hit.HitLook;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.minigames.CastleWars;
import com.rs.game.minigames.Crucible;
import com.rs.game.minigames.CrystalChest;
import com.rs.game.minigames.DcrystalChest;
import com.rs.game.minigames.EliteCrystalKey;
import com.rs.game.minigames.FightPits;
import com.rs.game.minigames.Heist;
import com.rs.game.minigames.IngenuityReward;
import com.rs.game.minigames.PdemonChest;
import com.rs.game.minigames.PuroPuro;
import com.rs.game.minigames.Rot6Chest;
import com.rs.game.minigames.TheCalamity;
import com.rs.game.minigames.pest.Lander;
import com.rs.game.npc.others.AscensionDungeon;
import com.rs.game.npc.others.AscensionDungeon.AscDoors;
import com.rs.game.player.ClueScrolls;
import com.rs.game.player.CoordsEvent;
import com.rs.game.player.OwnedObjectManager;
import com.rs.game.player.Player;
import com.rs.game.player.QuestManager.Quests;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.Bonfire;
import com.rs.game.player.actions.BoxAction.HunterEquipment;
import com.rs.game.player.actions.BoxAction.HunterNPC;
import com.rs.game.player.actions.Cooking;
import com.rs.game.player.actions.Cooking.Cookables;
import com.rs.game.player.actions.CowMilkingAction;
import com.rs.game.player.actions.LavaFlowMine;
import com.rs.game.player.actions.PlayerCombat;
import com.rs.game.player.actions.Smithing.ForgingBar;
import com.rs.game.player.actions.Smithing.ForgingInterface;
import com.rs.game.player.actions.Summoning;
import com.rs.game.player.actions.Woodcutting;
import com.rs.game.player.actions.Woodcutting.TreeDefinitions;
import com.rs.game.player.actions.crafting.JewellerySmithing;
import com.rs.game.player.actions.crafting.OrbCharging;
import com.rs.game.player.actions.crafting.OrbCharging.Orbs;
import com.rs.game.player.actions.mining.EssenceMining;
import com.rs.game.player.actions.mining.EssenceMining.EssenceDefinitions;
import com.rs.game.player.actions.mining.Mining;
import com.rs.game.player.actions.mining.Mining.RockDefinitions;
import com.rs.game.player.actions.mining.MiningBase;
import com.rs.game.player.actions.mining.RedSandStone;
import com.rs.game.player.actions.mining.RedSandStone.Sandstone;
import com.rs.game.player.actions.runecrafting.SihponActionNodes;
import com.rs.game.player.actions.thieving.Thieving;
import com.rs.game.player.content.BonesOnAltar;
import com.rs.game.player.content.DailyChallenges;
import com.rs.game.player.content.BonesOnAltar.Bones;
import com.rs.game.player.content.Gchest;
import com.rs.game.player.content.Hunter;
import com.rs.game.player.content.Magic;
import com.rs.game.player.content.MagicChest;
import com.rs.game.player.content.MagicChest2;
import com.rs.game.player.content.MagicChest3;
import com.rs.game.player.content.PolyporeDungeon;
import com.rs.game.player.content.Runecrafting;
import com.rs.game.player.content.ShootingStar;
import com.rs.game.player.content.TreasureHunt;
import com.rs.game.player.content.WildernessTeleports;
import com.rs.game.player.content.XmasEvent2018;
import com.rs.game.player.content.agility.BarbarianOutpostAgility;
import com.rs.game.player.content.agility.GnomeAgility;
import com.rs.game.player.content.agility.WildernessAgility;
import com.rs.game.player.content.bossinstance.BossInstance;
import com.rs.game.player.content.bossinstance.BossInstanceManager;
import com.rs.game.player.content.bossinstance.impl.DungBossHigh;
import com.rs.game.player.content.bossinstance.impl.DungBossLow;
import com.rs.game.player.content.bossinstance.impl.DungBossMed;
import com.rs.game.player.content.chests.NoobChest;
import com.rs.game.player.content.chests.StarterChest;
import com.rs.game.player.content.pet.Pets;
import com.rs.game.player.content.potionflask.RobustGlassMachine;
import com.rs.game.player.controlers.Falconry;
import com.rs.game.player.controlers.FightCaves;
import com.rs.game.player.controlers.FightKiln;
import com.rs.game.player.controlers.NomadsRequiem;
import com.rs.game.player.controlers.WGuildControler;
import com.rs.game.player.controlers.Wilderness;
import com.rs.game.player.controlers.events.eviltree.EvilTreeEvent;
import com.rs.game.player.controlers.events.eviltree.EvilTreeEvent.EvilTreeDefinitions;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.game.player.dialogues.impl.MiningGuildDwarf;
import com.rs.game.player.interfaces.AchievementDiaryInter;
import com.rs.game.player.interfaces.LootInterface;
import com.rs.game.player.interfaces.ResearchTableInterfaceMain;
import com.rs.game.player.interfaces.StaffDeskInterface;
import com.rs.game.route.RouteEvent;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.io.InputStream;
import com.rs.utils.CalamityBestWave;
import com.rs.utils.Colors;
import com.rs.utils.Logger;
import com.rs.utils.PkRank;
import com.rs.utils.Utils;

public final class ObjectHandler {

	private ObjectHandler() {

	}////

	public static void handleOption(final Player player, InputStream stream, int option) {
		if (!player.hasStarted() || !player.clientHasLoadedMapRegion() || player.isDead()) {
			return;
		}
		if (player.isLocked() || player.getEmotesManager().getNextEmoteEnd() >= Utils.currentTimeMillis()) {
			return;
		}
		boolean forceRun = stream.readUnsignedByte128() == 1;
		final int id = stream.readIntLE();
		int x = stream.readUnsignedShortLE();
		int y = stream.readUnsignedShortLE128();
		final WorldTile tile = new WorldTile(x, y, player.getPlane());
		final int regionId = tile.getRegionId();
		if (!player.getMapRegionsIds().contains(regionId)) {
			return;
		}
		final WorldObject object = World.getObjectWithId(tile, id);
		if (object == null || object.getId() != id) {
			return;
		}
		if (forceRun) {
			player.setRun(forceRun);
		}
		player.setRouteEvent(new RouteEvent(object, () -> {
			player.stopAll();
			player.faceObject(object);
		}));
		switch (option) {
			case 1:
				handleOption1(player, object);

				break;
			case 2:
				handleOption2(player, object);
				break;
			case 3:
				handleOption3(player, object);
				break;
			case 4:
				handleOption4(player, object);
				break;
			case 5:
				handleOption5(player, object);
				break;
			case -1:
				handleOptionExamine(player, object);
				break;
		}
	}

	private static void handleOption1(final Player player, final WorldObject object) {
		final ObjectDefinitions objectDef = object.getDefinitions();
		final int id = object.getId();
		final int x = object.getX();
		final int y = object.getY();
		if (SihponActionNodes.siphon(player, object)) {
			return;
		}
		if (id == 43529) {
			GnomeAgility.PreSwing(player, object);
		}
		player.setCoordsEvent(new CoordsEvent(object, new Runnable() {
			@Override
			public void run() {

				player.stopAll();
				player.faceObject(object);
				if (!player.getControlerManager().processObjectClick1(object)) {
					return;
				}
				/*if (id == 26428 && player.getY() == 5333 || player.getY() == 5334) { //zamorak gwd door
					if (player.zamorakKc >= 40) {
						player.zamorakKc -= 40;
						player.setNextWorldTile(new WorldTile(2925, 5332, 0));
					} else {
						player.sendMessage("You need 40 Zamorak KC to enter.");
						return;
					}
				} else if (player.getY() == 5332) {
					player.setNextWorldTile(new WorldTile(2925, 5333, 0));
				}

				if (id == 26425 && player.getX() == 2862) { //bandos gwd door
					if (player.bandosKc >= 40) {
						player.bandosKc -= 40;
						player.setNextWorldTile(new WorldTile(2863, 5357, 0));
					} else {
						player.sendMessage("You need 40 Bandos KC to enter.");
						return;
					}
				} else if (player.getX() == 2863) {
					player.setNextWorldTile(new WorldTile(2862, 5357, 0));
				}

				if (id == 26426 && player.getY() == 5294) { //armadyl gwd door
					if (player.armadylKc >= 40) {
						player.armadylKc -= 40;
						player.setNextWorldTile(new WorldTile(2835, 5295, 0));
					} else {
						player.sendMessage("You need 40 Armadyl KC to enter.");
						return;
					}
				} else if (player.getY() == 5295) {
					player.setNextWorldTile(new WorldTile(2835, 5294, 0));
				}

				if (id == 26427 && player.getY() == 5257) { //saradomin gwd door
					if (player.saradominKc >= 40) {
						player.saradominKc -= 40;
						player.setNextWorldTile(new WorldTile(2923, 5256, 0));
					} else {
						player.sendMessage("You need 40 Saradomin KC to enter.");
						return;
					}
				} else if (player.getY() == 5256) {
					player.setNextWorldTile(new WorldTile(2923, 5257, 0));
				}*/

				if (id == 37009) {
					if (player.getInventory().contains(29355)) {
						StarterChest.isBox(29355, player);
					} else if (player.getInventory().contains(29356)) {
						NoobChest.isBox(29356, player);
					} else {
						player.sendMessage("You must have a Starter or Noob key in your inventory to open this chest");
					}
				}

				if (id == 47150 || id == 229241) {
					final int maxPrayer = player.getPrayer().maxPrayerPoints();
					if (player.homehealer > 3) {
						player.sendMessage(
								"You've used the healer as much as you can today. Build an Ornate rejuvention pool in your house for unlimited uses!");
						return;
					}

					player.reset();
					/*player.getPrayer().restorePrayer(maxPrayer);
					player.heal(player.getMaxHitpoints());
					player.getPoison().reset();
					player.getCombatDefinitions().resetSpecialAttack();
					player.setRunEnergy(100);*/
					player.sendMessage("You have been healed & cured!");
					return;
				}
				if (id == 42031) {
					if (player.isPvpMode()) {
						player.sendMessage("Sorry, you cannot use this on pvp mode.");
						return;
					}
					player.getDialogueManager().startDialogue("CalamityConfirmation");
					return;
				}
				if (id == 65819 && player.startedxmas2018 == true) {
					XmasEvent2018.HandleMainQuestPresent(object.getX(), object.getY(), player);
				}
				if (id == 43797) {
					player.getDialogueManager().startDialogue("ScoreBoards");
					return;
				}
				else if (id == 24534) {
					player.getDialogueManager().startDialogue("SimpleMessage", "Crate contains: "+player.coalstorage+"/"+player.coalstoragecap+"");
					return;
				}
				/**
				 * Start rs portals
				 */
				if (id == 2273 || id == 2474 || id == 2473 || id == 2471 || id == 2477 || id == 2469 || id == 2468 || id == 2470 || id == 2466 || id == 2467 || id == 2475 || id == 2472) {
					Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(Settings.RESPAWN_PLAYER_LOCATION));
					return;
				}
				/**
				 * End rc portals
				 */


				/**
				 * Level zone
				 */
				if (id == 359 && World.Level3Zone(player)) {
					if (player.getActionManager().getActionDelay() > 0) {
						player.sendMessage("Still climbing...");
						return;
					}
					player.getActionManager().setActionDelay(3);
					player.setNextAnimation(new Animation (839));
					player.getSkills().addXp(Skills.AGILITY, 100);
					return;
				}
				if (id == 49934 && World.Level3Zone(player)) {
					if (player.getActionManager().getActionDelay() > 0) {
						player.sendMessage("Still spinning...");
						return;
					}
					player.getActionManager().setActionDelay(3);
					player.setNextAnimation(new Animation (883));
					player.getSkills().addXp(Skills.CRAFTING, 100);
					return;
				}
				if (id == 9257 && World.Level3Zone(player)) {
					if (player.getActionManager().getActionDelay() > 0) {
						player.sendMessage("Please wait...");
						return;
					}
					if (!player.getInventory().containsItem(8783, 10)) {
						player.sendMessage(Colors.red+"You need 10 noted mahogany planks to fix this!");
						return;
					}
					player.getActionManager().setActionDelay(4);
					player.setNextAnimation(new Animation (37));
					player.getSkills().addXp(Skills.CONSTRUCTION, 150);
					player.getInventory().deleteItem(8783, 10);
					return;
				}
				if (id == 4495 && object.getX() == 3413 && object.getY() == 3540) {
					player.useStairs(-1, new WorldTile(3417, 3540, 2), 0, 1);
					return;
				}
				if (id == 233483) {
					player.useStairs(-1, new WorldTile(2662, 9634, 0), 0, 1);
					return;
				}
				if (id == 63 && World.TheatreofBlood(player)) {
					player.setNextWorldTile(new WorldTile(2565, 9629, 0));
					return;
				}
				if (id == 230847 && player.getY() > 10263) {
					player.setNextWorldTile(new WorldTile(3633, 10260, 0));
					return;
				}
				if (id == 230847 && player.getY() < 10263) {
					player.setNextWorldTile(new WorldTile(3633, 10264, 0));
					return;
				}
				if (id == 2273 && player.getX() > 3514) {
					if (player.lootshareEnabled() != true) {
						player.toggleLootShareIcy();
					}
					player.setNextWorldTile(new WorldTile(3513, 9837, 0));
					return;
				}
				if (id == 230849 && player.getY() > 10263) {
					player.setNextWorldTile(new WorldTile(3633, 10260, 0));
					return;
				}
				if (id == 230849 && player.getY() < 10263) {
					player.setNextWorldTile(new WorldTile(3633, 10264, 0));
					return;
				}
				if (id == 3044 || id == 234591) {
					// } else if (object.getDefinitions().name.equalsIgnoreCase("furnace")) {
					player.getDialogueManager().startDialogue("SmeltingD", object);
				}
				if (id == 62416) {
					player.getDialogueManager().startDialogue("SimpleMessage", "Here shall lay the remains of Drygon. R.I.P sweet child.");
					return;
				}
				if (id == 234588) {
					player.getDialogueManager().startDialogue("SimpleMessage", "Legend says that only one man can hold the hammer.");
					return;
				}
				/**
				 * Crystal Keys
				 */
				if (id == 2588) {
					if (player.getInventory().contains(989)) {
						CrystalChest.searchChest(player);
						return;
					} else if (player.getInventory().contains(29425)) {
						EliteCrystalKey.searchChest(player);
						return;
					}
				}
				if (id == 2587) {
					if (player.getInventory().contains(989)) {
						DcrystalChest.searchChest(player);
						return;
					} else if (player.getInventory().contains(29425)) {
						EliteCrystalKey.searchChest(player);
						return;
					}
				}




				/**
				 * Karuulm slayer dungeon
				 */
				if (id == 234515 && player.getY() < 10174) {
					player.setNextWorldTile(new WorldTile(1270, 10176, 0));
					return;
				} else if (id == 234515 && player.getY() > 10174) {
					player.setNextWorldTile(new WorldTile(1270, 10169, 0));
					return;
				}

				if (id == 234544 && player.getX() < 1302 && object.getX() == 1302) {
					player.setNextWorldTile(new WorldTile(1303, 10205, 0));
					return;
				} else if (id == 234544 && player.getX() > 1302 && object.getX() == 1302) {
					player.setNextWorldTile(new WorldTile(1301, 10205, 0));
					return;
				} else if (id == 234544 && player.getX() < 1321 && object.getX() == 1321) {
					player.setNextWorldTile(new WorldTile(1322, 10205, 0));
					return;
				} else if (id == 234544 && player.getX() > 1321 && object.getX() == 1321) {
					player.setNextWorldTile(new WorldTile(1320, 10205, 0));
					return;
				} else if (id == 234544 && player.getY() > 10215 && object.getX() == 1311) {
					player.setNextWorldTile(new WorldTile(1312, 10214, 0));
					return;
				} else if (id == 234544 && player.getY() < 10215 && object.getX() == 1311) {
					player.setNextWorldTile(new WorldTile(1312, 10216, 0));
					return;
				} else if (id == 234530 && player.getY() == 10205 && object.getX() == 1330) {
					player.setNextWorldTile(new WorldTile(1334, 10205, 1));
					return;
				} else if (id == 234530 && object.getY() == 10188 && object.getX() == 1314) {
					player.setNextWorldTile(new WorldTile(1318, 10188, 2));
					return;
				} else if (id == 234531 && player.getY() == 10205 && object.getX() == 1314) {
					player.setNextWorldTile(new WorldTile(1329, 10205, 0));
					return;
				} else if (id == 234531 && player.getY() == 10188 && object.getX() == 1314) {
					player.setNextWorldTile(new WorldTile(1313, 10189, 1));
					return;
				} else if (id == 234516 && player.getDirection() >= 10240 && player.getDirection() <= 14336 && object.getX() == 1330) {
					player.setNextWorldTile(new WorldTile(1336, 10238, 1));
					return;
				} else if (id == 234516 && player.getDirection() < 10240 && object.getX() == 1330) {
					player.setNextWorldTile(new WorldTile(1330, 10238, 1));
					return;
				}


				if (id == 64 && World.TheatreofBlood(player)) {
					player.setNextWorldTile(new WorldTile(2590, 9609, 0));
					return;
				}
				if (id == 14873) {
					if (player.getRights() == 0 && !player.isSupporter()) {
						player.sendMessage("Nope.");
						return;
					}
					StaffDeskInterface.OpenInterface(player);
					return;
				}
				if (id == 2465) {
					player.SlayerGemTeleport();
					return;
				}
				if (id == 228900) {
					player.getDialogueManager().startDialogue("CatacombAltarD");
					return;
				}
				if (id == 18819 && World.Level3Zone(player)) {
					if (player.getActionManager().getActionDelay() > 0) {
						player.sendMessage("Please don't spam!");
						return;
					}
					player.getActionManager().setActionDelay(3);
					player.setNextAnimation(new Animation(2273));
					player.getSkills().addXp(Skills.FARMING, 400);
					player.getInventory().addItem(24784, 1);
					return;
				}
				if (id == 29577) {
					TreasureHunt.SearchChest(player, object.getX(), object.getY(), object.getPlane());
					return;
				}
				if (id == 13291 && player.getInventory().contains(29542)) {
					if (MagicChest.isBox(id, player)) {
						return;
					}
					return;
				}
				if (id == 13291 && player.getInventory().contains(29541)) {
					if (MagicChest2.isBox(id, player)) {
						return;
					}
					return;
				}
				/**
				 * Staff viewing orb
				 */
				if (id == 9391 && World.StaffZone(player)) {
					if (player.getRights() < 1 && !player.isSupporter()) {
						player.sendMessage("Nope.");
						return;
					}

					if (player.staffviewingorb.equalsIgnoreCase("NULL")) {
						player.sendMessage("Please type ::vieworb <col=ff0000>NAMEHERE</col>");
						return;
					}
					player.sendMessage(player.staffviewingorb);
					player.getDialogueManager().startDialogue("StaffViewingOrb", player.staffviewingorb);
					return;
				}
				if (id >= 9898 && id <= 9973 && World.TempleofLight(player)) {
					/**
					 * Random teleport across 3 floors
					 */
					int[][] RANDOM_COORDS = new int[][] { { 1910, 4657, 1 }, { 1908, 4665, 1 }, { 1887, 4658, 1 }, { 1864, 4665, 1 }, { 1860, 4654, 1 }, { 1863, 4639, 1 }, { 1873, 4644, 1 }, { 1873, 4631, 1 }, { 1869, 4613, 1 }, { 1887, 4614, 1 }, { 1909, 4626, 1 }, { 1909, 4634, 0 }, { 1904, 4650, 0 }, { 1898, 4637, 0 }, { 1886, 4650, 0 }, { 1888, 4665, 0 }, { 1865, 4665, 0 }, { 1873, 4639, 0 }, { 1866, 4613, 0 }, { 1906, 4614, 0 }, { 1904, 4628, 2 }, { 1908, 4613, 2 }, { 1892, 4629, 2 }, { 1861, 4621, 2 }, { 1864, 4639, 2 }, { 1868, 4656, 2 }, { 1881, 4665, 2 } };
					int[] coords = RANDOM_COORDS[Utils.random(RANDOM_COORDS.length - 1)];
					player.setNextWorldTile(new WorldTile(coords[0], coords[1], coords[2]));
					/**
					 * Add light chest to players inventory
					 */
					if (Settings.EVENT_SPOTLIGHT.equalsIgnoreCase("Double minigame rewards")) {
						player.getInventory().addItem(29365, 1);
					}
					if (player.templeoflighttime == 0) {
						player.setNextWorldTile(new WorldTile(Settings.RESPAWN_PLAYER_LOCATION));
						player.sendMessage(Colors.cyan + "Your time in the Temple has finished!");
					}
					player.getInventory().addItem(29365, 1);
					//SeasonEvent.HandleActivity(player, "Temple of Light", 0);
					player.pillaroflighttouched++;
					player.getPackets().sendGameMessage("You have now searched " + player.pillaroflighttouched + " Pillars of Light!", true);
					return;
				}
				if (id == 24991) {
					player.getControlerManager().startControler("PuroPuro");
					return;
				}
				if (id == 17309) {
					ResearchTableInterfaceMain.OpenInterface(player);
					return;
				}
				if (object.getDefinitions().name.equalsIgnoreCase("obelisk") && object.getDefinitions().getOption(1).equalsIgnoreCase("activate")) {
					WildernessTeleports.preTeleport(player, object);
				}
				/**
				 * OsRs Raids
				 */
				if (id == 57263) {
					player.setNextAnimation(new Animation(2791));
					World.removeObject(new WorldObject(object.getId(), object.getType(), object.getRotation(), object.getX(), object.getY(), object.getPlane()));
					return;
				}
				if (id == 229789 && object.getX() == 3311 && object.getY() == 5276 && Settings.ICEBOSSOPEN > 0) {
					Settings.ICEBOSSOPEN -= 1;
					Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3312, 5280, 0));
					return;
				}
				if (id == 229789 && object.getX() == 3311 && object.getY() == 5308 && Settings.VAMPYREBOSSOPEN > 0) {
					Settings.VAMPYREBOSSOPEN -= 1;
					Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3268, 5295, 0));
					return;
				}
				if (id == 229789 && object.getX() == 3265 && object.getY() == 5295 && Settings.PITBOSSOPEN > 0) {
					Settings.PITBOSSOPEN -= 1;
					Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3277, 5265, 0));
					return;
				}
				if (id == 229789 && object.getX() == 3307 && object.getY() == 5205) {
					Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3308, 5209, 0));
					return;
				}
				/**
				 * End of OSRS raids
				 */
				if (id == 13291 && player.getInventory().contains(29540)) {
					if (MagicChest3.isBox(id, player)) {
						return;
					}
					return;
				}
				if (id == 25154 && player.ecobdpart == 4) {
					Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2837, 9653, 0));
					return;
				}
				if (id == 4420) {
					if (player.getInventory().contains(6949) && player.ecobdpart == 3) {
						player.getInventory().deleteItem(6949, 299);
						player.ecobdpart = 4;
						player.getDialogueManager().startDialogue("SimpleMessage",
								"The Judge has declared the new Law of allowing ships to take up to 20Tons of goods.");
						player.sendMessage("<col=ff0000>Please bank & gear up, as the next part involves combat.");
						return;
					}
					return;
				}
				if (id == 226763) {
					player.setNextWorldTile(new WorldTile(3233, 3949, 0));
					return;
				}
				/**
				 * Catacombs
				 */
				if (id == 228893) {
					if (player.getY() >= 10069) {
						player.setNextWorldTile(new WorldTile(1610, 10061, 0));
						return;
					} else {
						player.setNextWorldTile(new WorldTile(1613, 10070, 0));
						return;
					}
				}
				if (id == 228892) {
					if (player.getSkills().getLevel(Skills.AGILITY) < 34) {
						player.sendMessage("This shortcut requires 34 Agility to access.");
						return;
					}
					if (player.getX() == 1646) {
						player.setNextWorldTile(new WorldTile(1648, 10009, 0));
						return;
					} else if (player.getX() == 1648) {
						player.setNextWorldTile(new WorldTile(1646, 10000, 0));
						return;
					} else if (player.getX() == 1716) {
						player.setNextWorldTile(new WorldTile(1706, 10078, 0));
						return;
					} else if (player.getX() == 1706) {
						player.setNextWorldTile(new WorldTile(1716, 10056, 0));
						return;
					}
				}
				/**
				 * Fremenick dungeon
				 */
				if (id == 44339) {
					if (player.getSkills().getLevel(Skills.AGILITY) < 81) {
						player.sendMessage("This shortcut requires 81 Agility to access.");
						return;
					}
					if (player.getX() >= 2774) {
						player.setNextWorldTile(new WorldTile(2769, 10002, 0));
						return;
					} else {
						player.setNextWorldTile(new WorldTile(2775, 10002, 0));
						return;
					}
				}
				if (id == 77052) {
					if (player.getSkills().getLevel(Skills.AGILITY) < 62) {
						player.sendMessage("This shortcut requires 62 Agility to access.");
						return;
					}
					if (player.getX() >= 2735) {
						player.setNextWorldTile(new WorldTile(2730, 10007, 0));
						return;
					} else {
						player.setNextWorldTile(new WorldTile(2736, 10007, 0));
						return;
					}
				}
				/**
				 * End of fremenick dungeon
				 */
				if (id == 77424) {
					if (player.getSkills().getLevel(Skills.AGILITY) < 34) {
						player.sendMessage("This shortcut requires 34 Agility to access.");
						return;
					}
					if (player.getY() >= 9498) {
						player.setNextWorldTile(new WorldTile(2698, 9492, 0));
						return;
					} else {
						player.setNextWorldTile(new WorldTile(2698, 9499, 0));
						return;
					}
				}
				if (id == 77373) {
					if (player.getY() >= 9569) {
						player.setNextWorldTile(new WorldTile(2683, 9568, 0));
						return;
					} else {
						player.setNextWorldTile(new WorldTile(2683, 9570, 0));
						return;
					}
				}
				if (id == 2927) {
					if (player.getY() >= 9305) {
						player.setNextWorldTile(new WorldTile(2773, 9302, 0));
						return;
					} else {
						player.setNextWorldTile(new WorldTile(2780, 9305, 0));
						return;
					}
				}
				if (id == 77379) {
					if (player.getX() >= 2695) {
						player.setNextWorldTile(new WorldTile(2693, 9482, 0));
						return;
					} else {
						player.setNextWorldTile(new WorldTile(2695, 9482, 0));
						return;
					}
				}
				if (id == 77377) {
					if (player.getX() >= 2676) {
						player.setNextWorldTile(new WorldTile(2674, 9479, 0));
						return;
					} else {
						player.setNextWorldTile(new WorldTile(2676, 9479, 0));
						return;
					}
				}
				if (id == 77375) {
					if (player.getX() <= 2672) {
						player.setNextWorldTile(new WorldTile(2674, 9499, 0));
						return;
					} else {
						player.setNextWorldTile(new WorldTile(2672, 9499, 0));
						return;
					}
				}
				if (id == 77573) {
					player.setNextWorldTile(new WorldTile(2687, 9506, 0));
					return;
				}
				if (id == 77574) {
					player.setNextWorldTile(new WorldTile(2682, 9506, 0));
					return;
				}
				if (id == 77572) {
					player.setNextWorldTile(new WorldTile(2649, 9562, 0));
					return;
				}
				if (id == 77570) {
					player.setNextWorldTile(new WorldTile(2647, 9557, 0));
					return;
				}
				if (id == 21316) {
					player.setNextWorldTile(new WorldTile(2385, 10259, 1));
					return;
				}
				if (id == 9294) {
					if (player.getSkills().getLevel(Skills.AGILITY) < 80) {
						player.sendMessage("This shortcut requires 80 Agility to access.");
						return;
					}
					if (player.getX() >= 2880) {
						player.setNextWorldTile(new WorldTile(2878, 9813, 0));
						return;
					} else {
						player.setNextWorldTile(new WorldTile(2880, 9814, 0));
						return;
					}
				}
				if (id == 92415) {
					player.getDialogueManager().startDialogue("Dunghandler");
					return;
				}
				if (id == 2823) {
					player.sendMessage("You can't fit down that, maybe a wizard could help.");
					return;
				}
				if (id == 92435) {//up elf city stairs
					player.setNextWorldTile(new WorldTile(2214, 3363, 2));
					return;
				}
				if (id == 92436) {//down elf city stairs
					player.setNextWorldTile(new WorldTile(2214, 3363, 1));
					return;
				}
				if (id == 2921) {
					if (player.getY() >= 9329) {
						player.setNextWorldTile(new WorldTile(2809, 9317, 0));
						return;
					} else {
						player.setNextWorldTile(new WorldTile(2809, 9329, 0));
						return;
					}
				}
				if (id == 2912 || id == 2913) {
					if (player.getY() >= 9332) {
						player.setNextWorldTile(new WorldTile(2809, 9331, 0));
						return;
					} else {
						player.setNextWorldTile(new WorldTile(2809, 9332, 0));
						return;
					}
				}
				if (id == 2918) {
					player.setNextWorldTile(new WorldTile(2772, 9341, 0));
					return;
				}
				if (id == 32494) {
					player.setNextWorldTile(new WorldTile(2801, 9339, 0));
					return;
				}
				if (id == 2923) {
					if (player.getY() >= 9315) {
						player.setNextWorldTile(new WorldTile(2809, 9313, 0));
						return;
					} else {
						player.setNextWorldTile(new WorldTile(2809, 9315, 0));
						return;
					}
				}
				if (id == 31834 || id == 31833) {
					if (player.getY() >= 9316) {
						player.setNextWorldTile(new WorldTile(2762, 9315, 0));
						return;
					} else {
						player.setNextWorldTile(new WorldTile(2762, 9316, 0));
						return;
					}
				}
				if (id == 84909) { // vorago graveyard exit
					player.useStairs(828, new WorldTile(2972, 3431, 0), 1, 2);
					return;
				}
				if (object.getId() == 23596) {
					player.getDialogueManager().startDialogue("VoragoEntrance");
					return;
				}

				if (object.getX() == 3488 && object.getY() == 3294) {
					player.getPackets().sendGameMessage("You attempt to kick the door down....", true);
					player.setNextAnimation(new Animation(8991));
					// player.faceObject(object);
					switch (Utils.getRandom(12)) {
						case 7:
							player.sendMessage("<col=ff0000>You break the door, but hurry, it won't stay open!");
							World.spawnTemporaryObject(new WorldObject(57261, 0, 0, 3488, 3294, 0), 10000);
							break;
						default:
							player.getPackets().sendGameMessage("You door doesn't break.", true);
							break;
					}
				}
				if (id == 84702) { // enterance to the cave ascension
					if (player.getSkills().getLevel(Skills.SLAYER) >= 81) {
						player.setNextWorldTile(new WorldTile(1095, 580, 1));
					} else {
						player.getPackets().sendGameMessage("The monsters within this dungeon require at least 81 slayer to kill.");
					}
					return;
				}
				if (object.getDefinitions().name.startsWith("Laboratory ")) {
					if (player.getSkills().getLevel(Skills.SLAYER) < 95) {
						player.sendMessage("You need at least 95 Slayer to kill a Legio.");
						return;
					}
					String name = object.getDefinitions().name.replace("Laboratory ", "");
					for (AscDoors door : AscDoors.values()) {
						if (door.name().equalsIgnoreCase(name)) {
							if (object.getDefinitions().getFirstOption().equals("Enter")) {
								player.getDialogueManager().startDialogue(new Dialogue() {
									@Override
									public void start() {
										Dialogue.sendOptionsDialogue(player,"What would you like to do?", "Spawn Legio (Requires 1 Ascension Keystone " + name + ")", "Nevermind");
									}
									@Override
									public void run(int interfaceId, int componentId) {
										switch(componentId) {
											case OPTION_1:
												AscensionDungeon.enter(player, door.ordinal());
												player.getInterfaceManager().closeChatBoxInterface();
												break;
											default:
												player.getInterfaceManager().closeChatBoxInterface();
												break;

										}
									}
									@Override
									public void finish() {
									}

								});
							}
						}
					}
				}
				if (id >= 15477 && id <= 15482 || id == 93284) {
					player.getDialogueManager().startDialogue("EnterHouseD");
					return;
				}
				if (player.getFarmingManager().isFarming(id, null, 1)) {
					return;
				}
				if (Heist.handleObjects(player, id)) {
					return;
				}
				if (CastleWars.handleObjects(player, id)) {
					return;
				}
				if (TheCalamity.handleObjects(player, id)) {
					return;
				}
				if (PdemonChest.handleObjects(player, id)) {
					return;
				}
				if (RobustGlassMachine.handleObjects(player, id)) {
					return;
				}
				if (IngenuityReward.handleObjects(player, id)) {
					return;
				}
				if (id == 6 || id == 29408 || id == 29406) {
					player.getDwarfCannon().preRotationSetup(object);
				}

				/**
				 *
				 * Trio boss raid
				 *
				 */
				if (id == 2798) {
					if (player.getSkills().getLevelForXp(Skills.FARMING) != 99) {
						player.sendMessage("You require 99 farming to do this.");
						return;
					}
					if (Settings.TRIOBUSH != true) {
						player.sendMessage("There is no need to use this.");
						return;
					} else if (Settings.TRIOVINE == true) {
						player.applyHit(new Hit(player, 400, HitLook.REGULAR_DAMAGE));
						player.getPackets().sendGameMessage("Ouch! This must be wrong.", true);
						return;
					} else {
						Settings.TRIOBUSH = false;
						player.sendMessage("You have removed the spirit from this bush.");
						player.addFreezeDelay(120000);
						return;

					}
				}
				/**
				 * Slayer tower
				 */
				if (id == 82666 || id == 82668 || id == 82481) {
					player.setNextWorldTile(new WorldTile(player.getX(), player.getY(), player.getPlane() +1));
				}

				if (id == 82669 || id == 82485) {
					player.setNextWorldTile(new WorldTile(player.getX(), player.getY(), player.getPlane() -1));
				}
				if (id == 82489) {
					player.setNextWorldTile(new WorldTile(3436,3543, 3));
				}
				if (id == 82491) {
					player.setNextWorldTile(new WorldTile(3431,3543, 2));
				}
				if (id == 84826) {
					player.setNextWorldTile(new WorldTile(3239, 3242, 0));
				}
				if (id == 82488) {
					player.setNextWorldTile(new WorldTile(3411, 3542, 3));
				}
				if (id == 82605) {
					if (player.getSkills().getLevel(Skills.AGILITY) < 71) {
						player.sendMessage("This shortcut requires 71 Agility to access.");
						return;
					}
					if (player.getX() == 3415 && player.getY() == 3550) {
						player.setNextWorldTile(new WorldTile(3416, 3545, 2));
					} else if (player.getX() == 3416 && player.getY() == 3545) {
						player.setNextWorldTile(new WorldTile(3416, 3550, 2));
					} else {
						player.sendMessage("You need to be closer to walk this plank.");
					}
				}
				if (id == 82490) {
					player.setNextWorldTile(new WorldTile(3415, 3542, 2));
				}
				if (id == 82483) {
					player.getDialogueManager().startDialogue("ClimbNoEmoteStairs",
							new WorldTile(player.getX(), player.getY(), player.getPlane() + 1),
							new WorldTile(player.getX(), player.getY(), player.getPlane() - 1), "Go up the stairs.",
							"Go down the stairs.");
				}
				if (id == 82432) {
					if (player.getX() == 3431 && player.getY() == 3550) {
						player.setNextWorldTile(new WorldTile(3432, 3550, 2));
					}
					else if (player.getX() == 3432 && player.getY() == 3550) {
						player.setNextWorldTile(new WorldTile(3431, 3550, 2));
					}
					else if (player.getX() == 3431 && player.getY() == 3566) {
						player.setNextWorldTile(new WorldTile(3432, 3566, 2));
					}
					else if (player.getX() == 3432 && player.getY() == 3566) {
						player.setNextWorldTile(new WorldTile(3431, 3566, 2));
					} else {
						player.sendMessage("You must be closer to walk through a door.");
					}
				}
				/**
				 *
				 * End of slayer tower
				 */
				if (id == 4954) {
					if (player.getSkills().getLevelForXp(Skills.STRENGTH) != 99) {
						player.sendMessage("You require 99 strength to do this.");
						return;
					}
					if (Settings.TRIOLEVER != true) {
						player.sendMessage("There is no need to use this.");
						return;
					} else if (Settings.TRIOVINE == true || Settings.TRIOBUSH == true) {
						player.applyHit(new Hit(player, 400, HitLook.REGULAR_DAMAGE));
						player.getPackets().sendGameMessage("Ouch! This must be wrong.", true);
						return;
					} else {
						Settings.TRIOLEVER = false;
						Settings.TRIORAIDTASKACTIVE = false;
						player.sendMessage(
								"You have removed the spirit from this lever. You may now attack the bosses again.");
						for (Player p2 : World.getPlayers()) {
							if (World.TheTrioRaid(p2)) {
								p2.setFreezeDelay(1);
								p2.getPackets().sendGameMessage("You have been unfrozen.", false);
							}
						}
						return;
					}
				}
				if (id == 5107) {
					if (player.getSkills().getLevelForXp(Skills.WOODCUTTING) != 99) {
						player.sendMessage("You require 99 woodcutting to do this.");
						return;
					}
					if (Settings.TRIOVINE != true) {
						player.sendMessage("There is no need to use this.");
						return;
					} else {
						Settings.TRIOVINE = false;
						player.sendMessage("You have removed the spirit from this vine.");
						player.addFreezeDelay(120000);
						return;
					}
				}

				if (id == 3272) {
					if (!player.getInventory().contains(29688)) {
						player.sendMessage("You must have the correct key to use this.");
						return;
					}
					if (player.getInventory().contains(29688)) {
						if (player.canattackbalancelemental == true) {
							player.sendMessage("You still have a key activated!");
							return;
						}
						player.getInventory().deleteItem(29688, 1);
						player.canattackbalancelemental = true;
						player.sendMessage("Your key has activated, you can now fight the Balance Elemental!");
					}
				}
				if (object.getId() == 5052 && player.getInventory().contains(20730)) {
					player.getInventory().deleteItem(20730, 200);
					player.teleportPlayer(3479, 9835, 0);
					player.sendMessage("<col=00ff00>As you use the key on the wall, you're moved and the keys vanish.");
					return;
				}
				if (id == 20232) {
					if (player.getInventory().hasItem(228)) {
						player.getInventory().deleteItem(228, 1);
						Settings.WaterAdded++;
						return;
					} else if (id == 20232 && !player.getInventory().hasItem(228)) {
						player.sendMessage("<col=ff0000>I wouldn't touch this without a noted vial of water!</col>");
						return;
					}

				}

				// rot6----------Utils.getRandom(400) + 400,
				/**
				 * StaffZone
				 */

				if (id == 26807) {
					if (player.rights >= 1) {
						player.getDialogueManager().startDialogue("ModTable");
						player.sendMessage("You need to be a moderator or higher to user this");
						return;
					} else {
						player.sendMessage("You need to be a moderator or higher to user this");
					}
				}

				if (id == 57376) {
					player.setNextWorldTile(new WorldTile(1556, 4506, 0));
					player.getPackets().sendGameMessage("Welcome to the Easter Event!");
				}

				if (id == 65367) {
					player.setNextWorldTile(new WorldTile(2998, 3915, 0));
				}
				/**
				 * End of staffzone
				 *
				 */
				/**
				 * Xmas event
				 *
				 */
				/*
				 * if (id == 28296) { if (player.spoketosanta2 == false) { if
				 * (player.coldhands >= 5) { player.sendMessage(
				 * "<col=ff0000>Your hands are now too cold to collect snowballs. Burn a log to warm up again."
				 * ); return; } player.setNextAnimation(new Animation(2295));
				 * player.getInventory().addItem(22989, 1); player.coldhands ++;
				 * return; } else { player.out(
				 * "You have no use for this snow anymore."); } } /** End of
				 * Xmas event
				 */

				if (id == 44379 && player.isDonator()) { // donator boss
					player.getDialogueManager().startDialogue("DbossFee");
					return;
				}

				if (id == 67551) { // rot6 chest
					Rot6Chest.searchChest(player);
					return;
				}
				else if (id == 77834) {
					player.setNextWorldTile(new WorldTile(2270, 4692, 0));
				} else if (object.getId() == 13405) {
					player.getDialogueManager().startDialogue("LeaveHouse");
				} else if (id == 15482) {
					player.getDialogueManager().startDialogue("HouseTeleport");
				}
				/**
				 *
				 * Lava flow mine handling.
				 *
				 */
				else if (id == 57171) {
					player.getActionManager().setAction(new LavaFlowMine(object));
				} else if (id == 57180) {
					player.getActionManager().setAction(new LavaFlowMine(object));
				} else if (id == 57169) {
					player.getActionManager().setAction(new LavaFlowMine(object));
				} else if (id == 57179) {
					player.getActionManager().setAction(new LavaFlowMine(object));
				} else if (id == 57172) {
					player.getActionManager().setAction(new LavaFlowMine(object));
				} else if (id == 57170) {
					player.getActionManager().setAction(new LavaFlowMine(object));
				} else if (id == 57176) {
					player.getActionManager().setAction(new LavaFlowMine(object));
				} else if (id == 57177) {
					player.getActionManager().setAction(new LavaFlowMine(object));
				} else if (object.getX() == 3471 && object.getY() == 9710) {
					World.spawnTemporaryObject(new WorldObject(-1, 0, 0, object.getX(), object.getY(), 0), 3200, false);
				} else if (object.getX() == 3486 && object.getY() == 9710) {
					World.spawnTemporaryObject(new WorldObject(-1, 0, 0, object.getX(), object.getY(), 0), 3200, false);
				} else if (object.getX() == 3498 && object.getY() == 9710) {
					World.spawnTemporaryObject(new WorldObject(-1, 0, 0, object.getX(), object.getY(), 0), 3200, false);
				} else if (object.getX() == 3493 && object.getY() == 9712) {
					if (!player.getInventory().contains(14471)) {
						player.sendMessage("The door is locked.");
						return;
					}
					player.teleportPlayer(3493, 9713, 0);
				} else if (object.getX() == 3500 && object.getY() == 9813) {
					if (!player.getInventory().contains(20731)) {
						player.sendMessage("The door is locked.");
						return;
					}
					player.getInventory().deleteItem(20731, 2000);
					player.getInventory().deleteItem(4286, 2000);
					player.getInventory().deleteItem(13305, 2000);
					player.getInventory().deleteItem(20730, 2000);
					BossInstance instance = null;
					instance = new DungBossMed(player);
					BossInstanceManager.SINGLETON.add(instance);
				} else if (object.getX() == 3492 && object.getY() == 9726) {
					if (!player.getInventory().contains(14471)) {
						player.sendMessage("The door is locked.");
						return;
					}
					player.getInventory().deleteItem(14471, 2000);
					player.getInventory().deleteItem(14470, 2000);
					player.getInventory().deleteItem(14469, 2000);
					player.getInventory().deleteItem(14463, 2000);
					BossInstance instance = null;
					instance = new DungBossLow(player);
					BossInstanceManager.SINGLETON.add(instance);
				} else if (object.getX() == 2823 && object.getY() == 10048) {
					if (!player.getInventory().contains(20732)) {
						player.sendMessage("There seems to be a gate blocking the way.");
						return;
					}
					player.getInventory().deleteItem(20732, 2000);
					player.getInventory().deleteItem(1485, 2000);
					player.getInventory().deleteItem(13758, 2000);
					player.getInventory().deleteItem(14496, 2000);
					player.getInventory().deleteItem(17401, 2000);
					BossInstance instance = null;
					instance = new DungBossHigh(player);
					BossInstanceManager.SINGLETON.add(instance);
				} else if (object.getId() >= 4116 && object.getId() <= 4120) {
					if (player.getInventory().contains(14469) || player.getInventory().contains(14471)) {
						player.sendMessage("The chest is empty.");
						return;
					}
					player.sendMessage("<col=ff0000>You find a strange key part");
					player.getInventory().addItem(14469, 1);
					return;
				} else if (object.getId() == 3767) {
					if (player.getInventory().contains(20732)) {
						player.sendMessage("You have no need to do this.");
						return;
					}
					if (!player.getInventory().contains(17401)) {
						player.sendMessage("The gate is locked and the key hole is busted.");
						return;
					}
					player.teleportPlayer(2831, 10078, 0);
					return;
				} else if (object.getId() == 3765) {
					if (player.getInventory().contains(20732)) {
						player.sendMessage("You have no need to do this.");
						return;
					}
					if (!player.getInventory().contains(14496)) {
						player.sendMessage("The gate is locked.");
						return;
					}
					player.teleportPlayer(2831, 10082, 0);
					return;
				} else if (object.getId() == 3821) {
					if (player.getInventory().contains(20732)) {
						player.sendMessage("You have no need to do this.");
						return;
					}
					if (player.getInventory().contains(13758) || player.getInventory().contains(14496)) {
						player.sendMessage("This rack is empty.");
						return;
					}
					player.sendMessage("<col=ff0000>You find a grubby key");
					player.getInventory().addItem(13758, 1);
					return;
				} else if (object.getId() == 12772) {
					if (player.getInventory().contains(20731)) {
						return;
					}

					if (!player.getInventory().contains(4286)) {
						player.getDialogueManager().startDialogue("SimpleMessage",
								"As you approach the wood, you hear a voice.... Bring me gloom!");
						player.sendMessage(
								"<col=00ff00>Something in this room beholds the gloom he is asking for, provide it to him for the next step.");
					} else {
						player.getDialogueManager().startDialogue("SimpleMessage", "Thank you, take this. Now go!");
						player.sendMessage("<col=00ff00>Whatever it is, hands you a key.");
						player.getInventory().deleteItem(4286, 200);
						player.getInventory().addItem(20731, 1);
					}
				} else if (object.getX() == 3287 && object.getY() == 5448 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3283, 5448, 0));

				} else if (object.getX() == 3283 && object.getY() == 5448 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3287, 5448, 0));

				} else if (object.getX() == 3307 && object.getY() == 5496 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3317, 5496, 0));

				} else if (object.getX() == 3317 && object.getY() == 5496 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3307, 5496, 0));

				} else if (object.getX() == 3318 && object.getY() == 5481 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3322, 5480, 0));

				} else if (object.getX() == 3322 && object.getY() == 5480 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3318, 5481, 0));

				} else if (object.getX() == 3299 && object.getY() == 5484 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3303, 5477, 0));

				} else if (object.getX() == 3303 && object.getY() == 5477 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3299, 5484, 0));

				} else if (object.getX() == 3286 && object.getY() == 5470 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3285, 5474, 0));

				} else if (object.getX() == 3285 && object.getY() == 5474 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3286, 5470, 0));

				} else if (object.getX() == 3290 && object.getY() == 5463 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3302, 5469, 0));

				} else if (object.getX() == 3302 && object.getY() == 5469 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3290, 5463, 0));

				} else if (object.getX() == 3296 && object.getY() == 5455 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3299, 5450, 0));

				} else if (object.getX() == 3299 && object.getY() == 5450 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3296, 5455, 0));

				} else if (object.getX() == 3280 && object.getY() == 5501 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3285, 5508, 0));

				} else if (object.getX() == 3285 && object.getY() == 5508 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3280, 5501, 0));

				} else if (object.getX() == 3300 && object.getY() == 5514 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3297, 5510, 0));

				} else if (object.getX() == 3297 && object.getY() == 5510 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3300, 5514, 0));

				} else if (object.getX() == 3289 && object.getY() == 5533 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3288, 5536, 0));

				} else if (object.getX() == 3288 && object.getY() == 5536 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3289, 5533, 0));

				} else if (object.getX() == 3285 && object.getY() == 5527 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3282, 5531, 0));

				} else if (object.getX() == 3282 && object.getY() == 5531 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3285, 5527, 0));

				} else if (object.getX() == 3325 && object.getY() == 5518 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3323, 5531, 0));

				} else if (object.getX() == 3323 && object.getY() == 5531 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3325, 5518, 0));

				} else if (object.getX() == 3299 && object.getY() == 5533 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3297, 5536, 0));

				} else if (object.getX() == 3297 && object.getY() == 5536 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3299, 5533, 0));

				} else if (object.getX() == 3321 && object.getY() == 5554 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3315, 5552, 0));

				} else if (object.getX() == 3315 && object.getY() == 5552 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3321, 5554, 0));

				} else if (object.getX() == 3291 && object.getY() == 5555 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3285, 5556, 0));

				} else if (object.getX() == 3285 && object.getY() == 5556 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3291, 5555, 0));

				} else if (object.getX() == 3266 && object.getY() == 5552 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3262, 5552, 0));

				} else if (object.getX() == 3262 && object.getY() == 5552 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3266, 5552, 0));

				} else if (object.getX() == 3256 && object.getY() == 5561 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3253, 5561, 0));

				} else if (object.getX() == 3253 && object.getY() == 5561 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3256, 5561, 0));

				} else if (object.getX() == 3249 && object.getY() == 5546 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3252, 5543, 0));

				} else if (object.getX() == 3252 && object.getY() == 5543 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3249, 5546, 0));

				} else if (object.getX() == 3261 && object.getY() == 5536 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3268, 5534, 0));

				} else if (object.getX() == 3268 && object.getY() == 5534 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3261, 5536, 0));

				} else if (object.getX() == 3243 && object.getY() == 5526 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3241, 5529, 0));

				} else if (object.getX() == 3241 && object.getY() == 5529 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3243, 5526, 0));

				} else if (object.getX() == 3230 && object.getY() == 5547 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3226, 5553, 0));

				} else if (object.getX() == 3226 && object.getY() == 5553 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3230, 5547, 0));

				} else if (object.getX() == 3206 && object.getY() == 5553 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3204, 5546, 0));

				} else if (object.getX() == 3204 && object.getY() == 5546 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3206, 5553, 0));

				} else if (object.getX() == 3211 && object.getY() == 5533 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3214, 5533, 0));

				} else if (object.getX() == 3214 && object.getY() == 5533 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3211, 5533, 0));

				} else if (object.getX() == 3208 && object.getY() == 5527 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3211, 5523, 0));

				} else if (object.getX() == 3211 && object.getY() == 5523 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3208, 5527, 0));

				} else if (object.getX() == 3201 && object.getY() == 5531 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3197, 5529, 0));

				} else if (object.getX() == 3197 && object.getY() == 5529 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3201, 5531, 0));

				} else if (object.getX() == 3202 && object.getY() == 5515 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3196, 5512, 0));

				} else if (object.getX() == 3196 && object.getY() == 5512 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3202, 5515, 0));

				} else if (object.getX() == 3190 && object.getY() == 5515 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3190, 5519, 0));

				} else if (object.getX() == 3190 && object.getY() == 5519 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3190, 5515, 0));

				} else if (object.getX() == 3185 && object.getY() == 5518 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3181, 5517, 0));

				} else if (object.getX() == 3181 && object.getY() == 5517 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3185, 5518, 0));

				} else if (object.getX() == 3187 && object.getY() == 5531 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3182, 5530, 0));

				} else if (object.getX() == 3182 && object.getY() == 5530 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3187, 5531, 0));

				} else if (object.getX() == 3169 && object.getY() == 5510 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3159, 5501, 0));

				} else if (object.getX() == 3159 && object.getY() == 5501 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3169, 5510, 0));

				} else if (object.getX() == 3165 && object.getY() == 5515 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3173, 5530, 0));

				} else if (object.getX() == 3173 && object.getY() == 5530 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3165, 5515, 0));

				} else if (object.getX() == 3156 && object.getY() == 5523 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3152, 5520, 0));

				} else if (object.getX() == 3152 && object.getY() == 5520 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3156, 5523, 0));

				} else if (object.getX() == 3148 && object.getY() == 5533 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3153, 5537, 0));

				} else if (object.getX() == 3153 && object.getY() == 5537 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3148, 5533, 0));

				} else if (object.getX() == 3143 && object.getY() == 5535 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3147, 5541, 0));

				} else if (object.getX() == 3147 && object.getY() == 5541 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3143, 5535, 0));

				} else if (object.getX() == 3168 && object.getY() == 5541 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3171, 5542, 0));

				} else if (object.getX() == 3171 && object.getY() == 5542 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3168, 5541, 0));

				} else if (object.getX() == 3190 && object.getY() == 5549 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3190, 5554, 0));

				} else if (object.getX() == 3190 && object.getY() == 5554 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3190, 5549, 0));

				} else if (object.getX() == 3180 && object.getY() == 5557 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3174, 5558, 0));

				} else if (object.getX() == 3174 && object.getY() == 5558 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3180, 5557, 0));

				} else if (object.getX() == 3162 && object.getY() == 5557 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3158, 5561, 0));

				} else if (object.getX() == 3158 && object.getY() == 5561 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3162, 5557, 0));

				} else if (object.getX() == 3166 && object.getY() == 5553 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3162, 5545, 0));

				} else if (object.getX() == 3162 && object.getY() == 5545 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3166, 5553, 0));

				} else if (object.getX() == 3273 && object.getY() == 5460 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3280, 5460, 0));

				} else if (object.getX() == 3280 && object.getY() == 5460 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3273, 5460, 0));

					/**
					 * /*Chaos Tunnels Exits
					 */

				} else if (object.getX() == 3142 && object.getY() == 5545 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3115, 5528, 0));
				} else if (object.getX() == 3183 && object.getY() == 5470 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3059, 3549, 0));

				} else if (object.getX() == 3248 && object.getY() == 5490 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3120, 3571, 0));

				} else if (object.getX() == 3292 && object.getY() == 5479 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3166, 3561, 0));

				} else if (object.getX() == 3291 && object.getY() == 5538 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3166, 3618, 0));

				} else if (object.getX() == 3234 && object.getY() == 5559 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3107, 3640, 0));

					/**
					 * /*Bork Entrance
					 */
				} else if (object.getX() == 3115 && object.getY() == 5528 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3142, 5545, 0));

					/**
					 * /*
					 */
				} else if (object.getX() == 3120 && object.getY() == 3571 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3248, 5490, 0));

				} else if (object.getX() == 3129 && object.getY() == 3587 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3234, 5559, 0));
				}
				/**
				 * //End of Chaos Tunnels
				 */

				// Home stalk
				else if (id == 24819) {
					Magic.sendStalkTeleportSpell(player, 0, 0, new WorldTile(2857, 2587, 0));
				} else if (id == 65365) {
					WildernessAgility.GateWalk(player, object);
				} else if (id == 65734) {
					WildernessAgility.climbCliff(player, object);
				} else if (id == 65362) {
					WildernessAgility.enterObstaclePipe(player, object.getX(), object.getY());
				} else if (id == 64696) {
					WildernessAgility.swingOnRopeSwing(player, object);
				} else if (id == 64698) {
					WildernessAgility.walkLog(player);
				} else if (id == 64699) {
					WildernessAgility.crossSteppingPalletes(player, object);
				} else if (id == 7133) {
					player.setNextWorldTile(new WorldTile(2398, 4841, 0));
				} else if (id == 7132) {
					player.setNextWorldTile(new WorldTile(2162, 4833, 0));
				} else if (id == 7141) {
					player.setNextWorldTile(new WorldTile(2462, 4891, 1));
				} else if (id == 7129) {
					player.setNextWorldTile(new WorldTile(2584, 4836, 0));
				} else if (id == 7130) {
					player.setNextWorldTile(new WorldTile(2660, 4839, 0));
				} else if (id == 7131) {
					player.setNextWorldTile(new WorldTile(2527, 4833, 0));
				} else if (id == 7140) {
					player.setNextWorldTile(new WorldTile(2794, 4830, 0));
				} else if (id == 7139) {
					player.setNextWorldTile(new WorldTile(2845, 4832, 0));
				} else if (id == 7137) {
					player.setNextWorldTile(new WorldTile(3482, 4836, 0));
				} else if (id == 7136) {
					player.setNextWorldTile(new WorldTile(2207, 4836, 0));
				} else if (id == 7135) {
					player.setNextWorldTile(new WorldTile(2464, 4834, 0));
				} else if (id == 7134) {
					player.setNextWorldTile(new WorldTile(2269, 4843, 0));
				} else if (id == 45802) {
					player.getDialogueManager().startDialogue("DeathChat");
				} else if (id == 12327) {
					player.setNextWorldTile(new WorldTile(Settings.RESPAWN_PLAYER_LOCATION));
				}
				// LOSTCITY
				if (object.getId() == 2408) {
					player.useStairs(828, new WorldTile(2828, 9767, 0), 1, 2);
				}
				// slayer chain
				if (object.getId() == 9319) {
					player.setNextWorldTile(new WorldTile(3447, 3575, 2));
				}

				if (id == 15482) {
					player.getDialogueManager().startDialogue("HouseTeleport");
				}
				if (id == 2800) {
					player.setNextAnimation(new Animation(881));
					player.getInventory().addItem(19658, 1);
					player.sendMessage("You find a rare unique herb.");
				}
				if (id == 2802) {
					player.setNextAnimation(new Animation(881));
					player.sendMessage("This bush seems to be empty.");
				}


				else if (object.getId() == 28734) {
					player.getSkills().restoreSummoning();
					Summoning.sendInterface(player);
					player.setNextFaceWorldTile(object);
					return;

				}

				/**
				 * /* Chaos Tunnels by Brrandonnx
				 */

				if (object.getX() == 3287 && object.getY() == 5448 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3283, 5448, 0));

				} else if (object.getX() == 3283 && object.getY() == 5448 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3287, 5448, 0));

				} else if (object.getX() == 3307 && object.getY() == 5496 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3317, 5496, 0));

				} else if (object.getX() == 3317 && object.getY() == 5496 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3307, 5496, 0));

				} else if (object.getX() == 3318 && object.getY() == 5481 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3322, 5480, 0));

				} else if (object.getX() == 3322 && object.getY() == 5480 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3318, 5481, 0));

				} else if (object.getX() == 3299 && object.getY() == 5484 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3303, 5477, 0));

				} else if (object.getX() == 3303 && object.getY() == 5477 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3299, 5484, 0));

				} else if (object.getX() == 3286 && object.getY() == 5470 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3285, 5474, 0));

				} else if (object.getX() == 3285 && object.getY() == 5474 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3286, 5470, 0));

				} else if (object.getX() == 3290 && object.getY() == 5463 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3302, 5469, 0));

				} else if (object.getX() == 3302 && object.getY() == 5469 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3290, 5463, 0));

				} else if (object.getX() == 3296 && object.getY() == 5455 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3299, 5450, 0));

				} else if (object.getX() == 3299 && object.getY() == 5450 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3296, 5455, 0));

				} else if (object.getX() == 3280 && object.getY() == 5501 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3285, 5508, 0));

				} else if (object.getX() == 3285 && object.getY() == 5508 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3280, 5501, 0));

				} else if (object.getX() == 3300 && object.getY() == 5514 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3297, 5510, 0));

				} else if (object.getX() == 3297 && object.getY() == 5510 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3300, 5514, 0));

				} else if (object.getX() == 3289 && object.getY() == 5533 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3288, 5536, 0));

				} else if (object.getX() == 3288 && object.getY() == 5536 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3289, 5533, 0));

				} else if (object.getX() == 3285 && object.getY() == 5527 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3282, 5531, 0));

				} else if (object.getX() == 3282 && object.getY() == 5531 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3285, 5527, 0));

				} else if (object.getX() == 3325 && object.getY() == 5518 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3323, 5531, 0));

				} else if (object.getX() == 3323 && object.getY() == 5531 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3325, 5518, 0));

				} else if (object.getX() == 3299 && object.getY() == 5533 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3297, 5536, 0));

				} else if (object.getX() == 3297 && object.getY() == 5536 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3299, 5533, 0));

				} else if (object.getX() == 3321 && object.getY() == 5554 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3315, 5552, 0));

				} else if (object.getX() == 3315 && object.getY() == 5552 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3321, 5554, 0));

				} else if (object.getX() == 3291 && object.getY() == 5555 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3285, 5556, 0));

				} else if (object.getX() == 3285 && object.getY() == 5556 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3291, 5555, 0));

				} else if (object.getX() == 3266 && object.getY() == 5552 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3262, 5552, 0));

				} else if (object.getX() == 3262 && object.getY() == 5552 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3266, 5552, 0));

				} else if (object.getX() == 3256 && object.getY() == 5561 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3253, 5561, 0));

				} else if (object.getX() == 3253 && object.getY() == 5561 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3256, 5561, 0));

				} else if (object.getX() == 3249 && object.getY() == 5546 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3252, 5543, 0));

				} else if (object.getX() == 3252 && object.getY() == 5543 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3249, 5546, 0));

				} else if (object.getX() == 3261 && object.getY() == 5536 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3268, 5534, 0));

				} else if (object.getX() == 3268 && object.getY() == 5534 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3261, 5536, 0));

				} else if (object.getX() == 3243 && object.getY() == 5526 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3241, 5529, 0));

				} else if (object.getX() == 3241 && object.getY() == 5529 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3243, 5526, 0));

				} else if (object.getX() == 3230 && object.getY() == 5547 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3226, 5553, 0));

				} else if (object.getX() == 3226 && object.getY() == 5553 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3230, 5547, 0));

				} else if (object.getX() == 3206 && object.getY() == 5553 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3204, 5546, 0));

				} else if (object.getX() == 3204 && object.getY() == 5546 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3206, 5553, 0));

				} else if (object.getX() == 3211 && object.getY() == 5533 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3214, 5533, 0));

				} else if (object.getX() == 3214 && object.getY() == 5533 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3211, 5533, 0));

				} else if (object.getX() == 3208 && object.getY() == 5527 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3211, 5523, 0));

				} else if (object.getX() == 3211 && object.getY() == 5523 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3208, 5527, 0));

				} else if (object.getX() == 3201 && object.getY() == 5531 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3197, 5529, 0));

				} else if (object.getX() == 3197 && object.getY() == 5529 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3201, 5531, 0));

				} else if (object.getX() == 3202 && object.getY() == 5515 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3196, 5512, 0));

				} else if (object.getX() == 3196 && object.getY() == 5512 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3202, 5515, 0));

				} else if (object.getX() == 3190 && object.getY() == 5515 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3190, 5519, 0));

				} else if (object.getX() == 3190 && object.getY() == 5519 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3190, 5515, 0));

				} else if (object.getX() == 3185 && object.getY() == 5518 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3181, 5517, 0));

				} else if (object.getX() == 3181 && object.getY() == 5517 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3185, 5518, 0));

				} else if (object.getX() == 3187 && object.getY() == 5531 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3182, 5530, 0));

				} else if (object.getX() == 3182 && object.getY() == 5530 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3187, 5531, 0));

				} else if (object.getX() == 3169 && object.getY() == 5510 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3159, 5501, 0));

				} else if (object.getX() == 3159 && object.getY() == 5501 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3169, 5510, 0));

				} else if (object.getX() == 3165 && object.getY() == 5515 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3173, 5530, 0));

				} else if (object.getX() == 3173 && object.getY() == 5530 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3165, 5515, 0));

				} else if (object.getX() == 3156 && object.getY() == 5523 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3152, 5520, 0));

				} else if (object.getX() == 3152 && object.getY() == 5520 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3156, 5523, 0));

				} else if (object.getX() == 3148 && object.getY() == 5533 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3153, 5537, 0));

				} else if (object.getX() == 3153 && object.getY() == 5537 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3148, 5533, 0));

				} else if (object.getX() == 3143 && object.getY() == 5535 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3147, 5541, 0));

				} else if (object.getX() == 3147 && object.getY() == 5541 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3143, 5535, 0));

				} else if (object.getX() == 3168 && object.getY() == 5541 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3171, 5542, 0));

				} else if (object.getX() == 3171 && object.getY() == 5542 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3168, 5541, 0));

				} else if (object.getX() == 3190 && object.getY() == 5549 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3190, 5554, 0));

				} else if (object.getX() == 3190 && object.getY() == 5554 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3190, 5549, 0));

				} else if (object.getX() == 3180 && object.getY() == 5557 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3174, 5558, 0));

				} else if (object.getX() == 3174 && object.getY() == 5558 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3180, 5557, 0));

				} else if (object.getX() == 3162 && object.getY() == 5557 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3158, 5561, 0));

				} else if (object.getX() == 3158 && object.getY() == 5561 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3162, 5557, 0));

				} else if (object.getX() == 3166 && object.getY() == 5553 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3162, 5545, 0));

				} else if (object.getX() == 3162 && object.getY() == 5545 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3166, 5553, 0));

				} else if (object.getX() == 3273 && object.getY() == 5460 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3280, 5460, 0));

				} else if (object.getX() == 3280 && object.getY() == 5460 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3273, 5460, 0));

					/**
					 * /*Chaos Tunnels Exits
					 */

				} else if (object.getX() == 3142 && object.getY() == 5545 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3115, 5528, 0));
				} else if (object.getX() == 3183 && object.getY() == 5470 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3059, 3549, 0));

				} else if (object.getX() == 3248 && object.getY() == 5490 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3120, 3571, 0));

				} else if (object.getX() == 3292 && object.getY() == 5479 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3166, 3561, 0));

				} else if (object.getX() == 3291 && object.getY() == 5538 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3166, 3618, 0));

				} else if (object.getX() == 3234 && object.getY() == 5559 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3107, 3640, 0));

					/**
					 * /*Bork Entrance
					 */
				} else if (object.getX() == 3115 && object.getY() == 5528 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3142, 5545, 0));

					/**
					 * /*
					 */
				} else if (object.getX() == 3120 && object.getY() == 3571 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3248, 5490, 0));

				} else if (object.getX() == 3129 && object.getY() == 3587 && object.getPlane() == 0) {
					player.setNextWorldTile(new WorldTile(3234, 5559, 0));
				}

				/*
				 * //End of Chaos Tunnels
				 */

				else if (object.getId() == 2409 && player.spokeToWarrior == true) {
					player.getDialogueManager().startDialogue("Shamus");
				} else if (id == 2406) {
					if (player.getEquipment().getWeaponId() == 772 && player.spokeToWarrior == true
							&& player.spokeToShamus == true && player.spokeToMonk == true) {
						player.setNextWorldTile(new WorldTile(2383, 4458, player.getPlane()));
						player.lostCity();
					} else {
						// player.getPackets().sendGameMessage("You need to
						// wield the dramen staff to enter Zanaris");
					}
				} else if (object.getId() == 1292) {
					if (player.getSkills().getLevel(Skills.WOODCUTTING) < 36) {
						player.getDialogueManager().startDialogue("SimpleMessage",
								"You need a woodcutting level of 36 and an axe to cut this tree.");
					} else {
						if (player.spokeToShamus == true) {
							World.spawnNPC(655, new WorldTile(player.getX(), player.getY() + 1, 0), -1, true, true);
						}
					}
				}
				if (object.getId() == 48496) {// dung ladder
					player.getControlerManager().startControler("DungeonControler");
				}

				if (object.getId() == 19205) {
					Hunter.createLoggedObject(player, object, true);
				}
				HunterNPC hunterNpc = HunterNPC.forObjectId(id);
				if (id == 29958 || id == 4019 || id == 50205 || id == 50206 || id == 50207 || id == 53883 || id == 54650
						|| id == 55605 || id == 56083 || id == 56084 || id == 56085 || id == 56086) {
					final int maxSummoning = player.getSkills().getLevelForXp(23);
					if (player.getSkills().getLevel(23) < maxSummoning) {
						player.lock(5);
						player.getPackets().sendGameMessage("You feel the obelisk", true);
						player.setNextAnimation(new Animation(8502));
						player.setNextGraphics(new Graphics(1308));
						WorldTasksManager.schedule(new WorldTask() {

							@Override
							public void run() {
								player.getSkills().restoreSummoning();
								player.getPackets().sendGameMessage("...and recharge all your skills.", true);
							}
						}, 2);
					} else {
						player.getPackets().sendGameMessage("You already have full summoning.", true);
					}
					return;
				}

				if (hunterNpc != null) {
					if (OwnedObjectManager.removeObject(player, object)) {
						if(Utils.random(4000) == 0) {
							if (player.getPet() == null && player.hunterpet != true) {
								player.getPetManager().spawnPet(29525, false);
								player.hunterpet = true;
								player.sendMessage("You have a funny feeling something is following you.");
								World.sendWorldMessage("<col=339966>"+player.getDisplayName()+" has just found a hunter pet.", false);
							} else if (player.getPet() != null && player.hunterpet != true) {
								player.hunterpet = true;
								player.sendMessage("Speak to the pet tamer at home to claim your pet.");
								World.sendWorldMessage("<col=339966>"+player.getDisplayName()+" has just found a hunter pet.", false);
							} else {
								player.sendMessage("You feel like something was following you.");
							}
						}
						if (player.challengeid == 31 && player.challengeamount > 0 && hunterNpc.getNpcId() == 5073) {
							DailyChallenges.UpdateChallenge(player);
						}
						if (player.challengeid == 32 && player.challengeamount > 0 && hunterNpc.getNpcId() == 5080) {
							DailyChallenges.UpdateChallenge(player);
						}
						player.setNextAnimation(hunterNpc.getEquipment().getPickUpAnimation());
						if (Settings.eventdoubleskillingresources > 0) {
							player.getInventory().getItems().addAll(hunterNpc.getItems());
						}
						player.getInventory().getItems().addAll(hunterNpc.getItems());
						player.getInventory().addItem(hunterNpc.getEquipment().getId(), 1);
						player.getInventory().refresh();
						player.getSkills().addXp(Skills.HUNTER, hunterNpc.getXp());
					} else {
						player.getPackets().sendGameMessage("This isn't your trap.");
					}
				} else if (id == HunterEquipment.BOX.getObjectId() || id == 19192) {
					if (OwnedObjectManager.removeObject(player, object)) {
						player.setNextAnimation(new Animation(5208));
						player.getInventory().addItem(HunterEquipment.BOX.getId(), 1);
						// player.getInventory().refresh();
					} else {
						player.getPackets().sendGameMessage("This isn't your trap.");
					}
				} else if (id == HunterEquipment.BRID_SNARE.getObjectId() || id == 19174) {
					if (OwnedObjectManager.removeObject(player, object)) {
						player.setNextAnimation(new Animation(5207));
						player.getInventory().addItem(HunterEquipment.BRID_SNARE.getId(), 1);
						// player.getInventory().refresh();
					} else {
						player.getPackets().sendGameMessage("This isn't your trap.");
					}
				} else if (id == 2350 && object.getX() == 3352 && object.getY() == 3417 && object.getPlane() == 0) {
					player.useStairs(832, new WorldTile(3177, 5731, 0), 1, 2);
				} else if (id == 2353 && object.getX() == 3177 && object.getY() == 5730 && object.getPlane() == 0) {
					player.useStairs(828, new WorldTile(3353, 3416, 0), 1, 2);
				} else if (id == 11554 || id == 11552) {
					player.getPackets().sendGameMessage("That rock is currently unavailable.");
				} else if (id == 5282) {
					player.getEctophial().refillEctophial(player);
				} else if (id == 38279) {
					player.getDialogueManager().startDialogue("RunespanPortalD");
				} else if (id == 2491) {
					if (player.ruesaltarprogress == 1) {
						player.getInventory().addItemDrop(29013, 1);
						player.sendMessage("You collect some powerless essence.");
						return;
					}
					player.getActionManager()
							.setAction(new EssenceMining(object, player.getSkills().getLevel(Skills.MINING) < 30
									? EssenceDefinitions.Rune_Essence : EssenceDefinitions.Pure_Essence));
				} else if (id == 2478) {
					Runecrafting.craftEssence(player, 556, 1, 5, false, 11, 2, 22, 3, 34, 4, 44, 5, 55, 6, 66, 7, 77,
							88, 9, 99, 10);
				} else if (id == 2479) {
					Runecrafting.craftEssence(player, 558, 2, 5.5, false, 14, 2, 28, 3, 42, 4, 56, 5, 70, 6, 84, 7, 98,
							8);
				} else if (id == 2480) {
					Runecrafting.craftEssence(player, 555, 5, 6, false, 19, 2, 38, 3, 57, 4, 76, 5, 95, 6);
				} else if (id == 2481) {
					Runecrafting.craftEssence(player, 557, 9, 6.5, false, 26, 2, 52, 3, 78, 4);
				} else if (id == 2482) {
					Runecrafting.craftEssence(player, 554, 14, 7, false, 35, 2, 70, 3);
				} else if (id == 2483) {
					Runecrafting.craftEssence(player, 559, 20, 7.5, false, 46, 2, 92, 3);
				} else if (id == 2484) {
					Runecrafting.craftEssence(player, 564, 27, 8, true, 59, 2);
				} else if (id == 2487) {
					Runecrafting.craftEssence(player, 562, 35, 8.5, true, 74, 2);
				} else if (id == 17010) {
					Runecrafting.craftEssence(player, 9075, 40, 8.7, true, 82, 2);
				} else if (id == 2486) {
					Runecrafting.craftEssence(player, 561, 45, 9, true, 91, 2);
				} else if (id == 2485) {
					Runecrafting.craftEssence(player, 563, 50, 9.5, true);
				} else if (id == 2488) {
					Runecrafting.craftEssence(player, 560, 65, 10, true);
				} else if (id == 30624 || id == 227978) {
					Runecrafting.craftEssence(player, 565, 77, 10.5, true);
				} else if (id == 26847) {
					Runecrafting.ZMIhandler(player);
				} else if (id == 2452) {
					int hatId = player.getEquipment().getHatId();
					if (hatId == Runecrafting.AIR_TIARA || hatId == Runecrafting.OMNI_TIARA
							|| player.getInventory().containsItem(1438, 1)) {
						Runecrafting.enterAirAltar(player);
					}
				} else if (id == 2455) {
					int hatId = player.getEquipment().getHatId();
					if (hatId == Runecrafting.EARTH_TIARA || hatId == Runecrafting.OMNI_TIARA
							|| player.getInventory().containsItem(1440, 1)) {
						Runecrafting.enterEarthAltar(player);
					}
				} else if (id == 2456) {
					int hatId = player.getEquipment().getHatId();
					if (hatId == Runecrafting.FIRE_TIARA || hatId == Runecrafting.OMNI_TIARA
							|| player.getInventory().containsItem(1442, 1)) {
						Runecrafting.enterFireAltar(player);
					}
				} else if (id == 2454) {
					int hatId = player.getEquipment().getHatId();
					if (hatId == Runecrafting.WATER_TIARA || hatId == Runecrafting.OMNI_TIARA
							|| player.getInventory().containsItem(1444, 1)) {
						Runecrafting.enterWaterAltar(player);
					}
				} else if (id == 2457) {
					int hatId = player.getEquipment().getHatId();
					if (hatId == Runecrafting.BODY_TIARA || hatId == Runecrafting.OMNI_TIARA
							|| player.getInventory().containsItem(1446, 1)) {
						Runecrafting.enterBodyAltar(player);
					}
				} else if (id == 2453) {
					int hatId = player.getEquipment().getHatId();
					if (hatId == Runecrafting.MIND_TIARA || hatId == Runecrafting.OMNI_TIARA
							|| player.getInventory().containsItem(1448, 1)) {
						Runecrafting.enterMindAltar(player);
					}
				} else if (id == 47120) { // zaros altar
					// recharge if needed
					if (player.getPrayer().getPrayerpoints() < player.getSkills().getLevelForXp(Skills.PRAYER) * 10) {
						player.lock(12);
						player.setNextAnimation(new Animation(12563));
						player.getPrayer()
								.setPrayerpoints((int) (player.getSkills().getLevelForXp(Skills.PRAYER) * 10 * 1.15));
						player.getPrayer().refreshPrayerPoints();
					}
					player.getDialogueManager().startDialogue("ZarosAltar");
				} else if (ClueScrolls.objectSpot(player, object)) {
					return;
				} else if (id == 19222) {
					Falconry.beginFalconry(player);
				} else if (id == 36786) {
					player.getDialogueManager().startDialogue("Banker", 4907);
				} else if (id == 42377 || id == 42378) {
					player.getDialogueManager().startDialogue("Banker", 2759);
				} else if (id == 42217 || id == 782 || id == 34752) {
					player.getDialogueManager().startDialogue("Banker", 553);
				} else if (id == 57437) {
					player.getBank().openBank();
				} else if (id == 42425 && object.getX() == 3220 && object.getY() == 3222) { // zaros
					// portal
					player.useStairs(10256, new WorldTile(3353, 3416, 0), 4, 5,
							"And you find yourself into a digsite.");
					player.addWalkSteps(3222, 3223, -1, false);
					player.getPackets().sendGameMessage("You examine portal and it aborves you...");
				} else if (id == 9356) {
					FightCaves.enterFightCaves(player);
				} else if (id == 68107) {
					// FightKiln.enterFightKiln(player, false);
					player.getDialogueManager().startDialogue("FightKilnDialogue");
				} else if (id == 68223) {
					FightPits.enterLobby(player, false);
				} else if (id == 46500 && object.getX() == 3351 && object.getY() == 3415) { // zaros
					// portal
					player.useStairs(-1, new WorldTile(Settings.RESPAWN_PLAYER_LOCATION.getX(),
									Settings.RESPAWN_PLAYER_LOCATION.getY(), Settings.RESPAWN_PLAYER_LOCATION.getPlane()), 2, 3,
							"You found your way back to home.");
					player.addWalkSteps(3351, 3415, -1, false);
				} else if (id == 9293) {
					if (player.getSkills().getLevel(Skills.AGILITY) < 70) {
						player.getPackets().sendGameMessage("You need an agility level of 70 to use this obstacle.",
								true);
						return;
					}
					int x = player.getX() == 2886 ? 2892 : 2886;
					WorldTasksManager.schedule(new WorldTask() {
						int count = 0;

						@Override
						public void run() {
							player.setNextAnimation(new Animation(844));
							if (count++ == 1) {
								stop();
							}
						}

					}, 0, 0);
					player.setNextForceMovement(
							new ForceMovement(new WorldTile(x, 9799, 0), 3, player.getX() == 2886 ? 1 : 3));
					player.useStairs(-1, new WorldTile(x, 9799, 0), 3, 4);
				} else if (id == 29370 && (object.getX() == 3150 || object.getX() == 3153) && object.getY() == 9906) { // edgeville
					// dungeon
					// cut
					if (player.getSkills().getLevel(Skills.AGILITY) < 53) {
						player.getPackets().sendGameMessage("You need an agility level of 53 to use this obstacle.");
						return;
					}
					final boolean running = player.getRun();
					player.setRunHidden(false);
					player.lock(8);
					player.addWalkSteps(x == 3150 ? 3155 : 3149, 9906, -1, false);
					player.getPackets().sendGameMessage("You pulled yourself through the pipes.", true);
					WorldTasksManager.schedule(new WorldTask() {
						boolean secondloop;

						@Override
						public void run() {
							if (!secondloop) {
								secondloop = true;
								player.getAppearence().setRenderEmote(295);
							} else {
								player.getAppearence().setRenderEmote(-1);
								player.setRunHidden(running);
								player.getSkills().addXp(Skills.AGILITY, 7);
								stop();
							}
						}
					}, 0, 5);
				}
				if (id == 234554 || id == 234553) {
					if (player.getFamiliar() != null || player.getPet() != null || Summoning.hasPouch(player)) {
						player.getDialogueManager().startDialogue("SimpleNPCMessage", 39615,
								"This is no area for pets!");
						return;
					}
					for (Player p : World.getPlayers()) {
						if (World.HydraBossRoom(p)) {
							player.sendMessage(Colors.red+"The Hydra boss is currently in an encounter, you must wait for the room to be free! Instances will be added soon!");
							return;
						} else {
							if (player.getSkills().getLevel(Skills.SLAYER) < 95) {
								player.sendMessage(Colors.red+"You must have a slayer level of 95 to enter.");
								return;
							}
							if (player.getTask() == null) {
								player.sendMessage(Colors.red+"You must have this monster as a slayer task to enter.");
								return;
							} else if (!player.getTask().getName().equalsIgnoreCase("alchemical hydra")) {
								player.sendMessage(Colors.red+"You must have this monster as a slayer task to enter.");
								return;
							}
							player.setNextWorldTile(new WorldTile(1357, 10259, 0));
							return;
						}
					}
				}
				if (id == 13202) {
					if (Settings.canclickspawnboss != true) {
						player.getPackets().sendGameMessage("The energy sources must be depleted first!");
						return;
					} else if (Skills.getTotalLevel(player) < 1500) {
						player.getPackets().sendGameMessage("You're too weak to source this energy!");
						return;
					} else if (Settings.timesclickedtospawnsboss >= 250) {
						Settings.canclickspawnboss = false;
						WorldTile worldTile = new WorldTile(2582, 3908, 0);
						World.spawnNPC(30031, worldTile, -1, true, true);
						return;
					}
					player.canlootingenuitychest = true;
					Settings.timesclickedtospawnsboss++;
					player.getPackets().sendGameMessage("A wisp of energy is consumed into the torch.", true);
				} else if (id == 29095) { // ingenuity firepit
					if (player.getSkills().getLevel(Skills.FIREMAKING) < 80) {
						player.getPackets().sendGameMessage("You need an Firemaking level of 80 to light this.");
						return;
					}
					if (Settings.canskillfirepit != true) {
						player.applyHit(
								new Hit(player, player.getMaxHitpoints() / 25, HitLook.REGULAR_DAMAGE, 15));
						return;
					} else if (Settings.Ingenuityfirepiting >= 250) {
						Settings.canskillfirepit = false;
						Settings.canclickspawnboss = true;
						player.getPackets().sendGameMessage("The fire starts to burn..", true);
						World.spawnObject(new WorldObject(29094, 10, -1, 2588, 3912, 0)); // firepit
					}
					player.lock(8);
					player.getPackets().sendGameMessage("You attempt to light the fire...", true);
					player.setNextGraphics(new Graphics(503));
					WorldTasksManager.schedule(new WorldTask() {
						boolean secondloop;

						@Override
						public void run() {
							if (!secondloop) {
								secondloop = true;
								player.getAppearence().setRenderEmote(295);
							} else {
								player.getAppearence().setRenderEmote(-1);
								player.getSkills().addXp(Skills.FIREMAKING, 50);
								player.getPackets().sendGameMessage("A wisp of energy comes flying into the firepit!",
										true);
								Settings.Ingenuityfirepiting++;
								stop();
							}
						}
					}, 0, 5);
				}
				// start forinthry dungeon
				else if (id == 18341 && object.getX() == 3036 && object.getY() == 10172) {
					player.useStairs(-1, new WorldTile(3039, 3765, 0), 0, 1);
				} else if (id == 20599 && object.getX() == 3038 && object.getY() == 3761) {
					player.useStairs(-1, new WorldTile(3037, 10171, 0), 0, 1);
				} else if (id == 18342 && object.getX() == 3075 && object.getY() == 10057) {
					player.useStairs(-1, new WorldTile(3071, 3649, 0), 0, 1);
				} else if (id == 20600 && object.getX() == 3072 && object.getY() == 3648) {
					player.useStairs(-1, new WorldTile(3077, 10058, 0), 0, 1);
				} else if (id == 18425 && !player.getQuestManager().completedQuest(Quests.NOMADS_REQUIEM)) {
					NomadsRequiem.enterNomadsRequiem(player);
				} else if (id == 42219) {
					player.useStairs(-1, new WorldTile(1886, 3178, 0), 0, 1);
					if (player.getQuestManager().getQuestStage(Quests.NOMADS_REQUIEM) == -2) {
						// now,
						// on
						// future
						// talk
						// with
						// npc
						// +
						// quest
						// reqs
						player.getQuestManager().setQuestStageAndRefresh(Quests.NOMADS_REQUIEM, 0);
					}
				} else if (id == 8689) {
					player.getActionManager().setAction(new CowMilkingAction());
				} else if (id == 42220) {
					player.useStairs(-1, new WorldTile(3082, 3475, 0), 0, 1);
				} else if (id == 30942 && object.getX() == 3019 && object.getY() == 3450) {
					player.useStairs(828, new WorldTile(3020, 9850, 0), 1, 2);
				} else if (id == 6226 && object.getX() == 3019 && object.getY() == 9850) {
					player.useStairs(833, new WorldTile(3018, 3450, 0), 1, 2);
				} else if (id == 31002 && player.getQuestManager().completedQuest(Quests.PERIL_OF_ICE_MONTAINS)) {
					player.useStairs(833, new WorldTile(2998, 3452, 0), 1, 2);
				} else if (id == 31012 && player.getQuestManager().completedQuest(Quests.PERIL_OF_ICE_MONTAINS)) {
					player.useStairs(828, new WorldTile(2996, 9845, 0), 1, 2);
				} else if (id == 30943 && object.getX() == 3059 && object.getY() == 9776) {
					player.useStairs(-1, new WorldTile(3061, 3376, 0), 0, 1);
				} else if (id == 30944 && object.getX() == 3059 && object.getY() == 3376) {
					player.useStairs(-1, new WorldTile(3058, 9776, 0), 0, 1);
				} else if (id == 2112 && object.getX() == 3046 && object.getY() == 9756) {
					if (player.getSkills().getLevelForXp(Skills.MINING) < 60) {
						player.getDialogueManager().startDialogue("SimpleNPCMessage",
								MiningGuildDwarf.getClosestDwarfID(player),
								"Sorry, but you need level 60 Mining to go in there.");
						return;
					}
					WorldObject openedDoor = new WorldObject(object.getId(), object.getType(), object.getRotation() - 1,
							object.getX(), object.getY() + 1, object.getPlane());
					if (World.removeTemporaryObject(object, 1200, false)) {
						World.spawnTemporaryObject(openedDoor, 1200, false);
						player.lock(2);
						player.stopAll();
						player.addWalkSteps(3046, player.getY() > object.getY() ? object.getY() : object.getY() + 1, -1,
								false);
					}
				} else if (id == 2113) {
					if (player.getSkills().getLevelForXp(Skills.MINING) < 60) {
						player.getDialogueManager().startDialogue("SimpleNPCMessage",
								MiningGuildDwarf.getClosestDwarfID(player),
								"Sorry, but you need level 60 Mining to go in there.");
						return;
					}
					player.useStairs(-1, new WorldTile(3021, 9739, 0), 0, 1);
				} else if (id == 6226 && object.getX() == 3019 && object.getY() == 9740) {
					player.useStairs(828, new WorldTile(3019, 3341, 0), 1, 2);
				} else if (id == 6226 && object.getX() == 3019 && object.getY() == 9738) {
					player.useStairs(828, new WorldTile(3019, 3337, 0), 1, 2);
				} else if (id == 6226 && object.getX() == 3018 && object.getY() == 9739) {
					player.useStairs(828, new WorldTile(3017, 3339, 0), 1, 2);
				} else if (id == 6226 && object.getX() == 3020 && object.getY() == 9739) {
					player.useStairs(828, new WorldTile(3021, 3339, 0), 1, 2);
				} else if (id == 30963) {
					player.getBank().openBank();
				} else if (id == 6045) {
					player.getPackets().sendGameMessage("You search the cart but find nothing.");
				} else if (id == 5906) {
					if (player.getSkills().getLevel(Skills.AGILITY) < 42) {
						player.getPackets().sendGameMessage("You need an agility level of 42 to use this obstacle.");
						return;
					}
					player.lock();
					WorldTasksManager.schedule(new WorldTask() {
						int count = 0;

						@Override
						public void run() {
							if (count == 0) {
								player.setNextAnimation(new Animation(2594));
								WorldTile tile = new WorldTile(object.getX() + (object.getRotation() == 2 ? -2 : +2),
										object.getY(), 0);
								player.setNextForceMovement(new ForceMovement(tile, 4, Utils
										.getMoveDirection(tile.getX() - player.getX(), tile.getY() - player.getY())));
							} else if (count == 2) {
								WorldTile tile = new WorldTile(object.getX() + (object.getRotation() == 2 ? -2 : +2),
										object.getY(), 0);
								player.setNextWorldTile(tile);
							} else if (count == 5) {
								player.setNextAnimation(new Animation(2590));
								WorldTile tile = new WorldTile(object.getX() + (object.getRotation() == 2 ? -5 : +5),
										object.getY(), 0);
								player.setNextForceMovement(new ForceMovement(tile, 4, Utils
										.getMoveDirection(tile.getX() - player.getX(), tile.getY() - player.getY())));
							} else if (count == 7) {
								WorldTile tile = new WorldTile(object.getX() + (object.getRotation() == 2 ? -5 : +5),
										object.getY(), 0);
								player.setNextWorldTile(tile);
							} else if (count == 10) {
								player.setNextAnimation(new Animation(2595));
								WorldTile tile = new WorldTile(object.getX() + (object.getRotation() == 2 ? -6 : +6),
										object.getY(), 0);
								player.setNextForceMovement(new ForceMovement(tile, 4, Utils
										.getMoveDirection(tile.getX() - player.getX(), tile.getY() - player.getY())));
							} else if (count == 12) {
								WorldTile tile = new WorldTile(object.getX() + (object.getRotation() == 2 ? -6 : +6),
										object.getY(), 0);
								player.setNextWorldTile(tile);
							} else if (count == 14) {
								stop();
								player.unlock();
							}
							count++;
						}

					}, 0, 0);
					// BarbarianOutpostAgility start
				} else if (id == 20210) {
					BarbarianOutpostAgility.enterObstaclePipe(player, object);
				} else if (id == 43526) {
					BarbarianOutpostAgility.swingOnRopeSwing(player, object);
				} else if (id == 43595 && x == 2550 && y == 3546) {
					BarbarianOutpostAgility.walkAcrossLogBalance(player, object);
				} else if (id == 20211 && x == 2538 && y == 3545) {
					BarbarianOutpostAgility.climbObstacleNet(player, object);
				} else if (id == 2302 && x == 2535 && y == 3547) {
					BarbarianOutpostAgility.walkAcrossBalancingLedge(player, object);
				} else if (id == 1948) {
					BarbarianOutpostAgility.climbOverCrumblingWall(player, object);
				} else if (id == 43533) {
					BarbarianOutpostAgility.runUpWall(player, object);
				} else if (id == 43597) {
					BarbarianOutpostAgility.climbUpWall(player, object);
				} else if (id == 43587) {
					BarbarianOutpostAgility.fireSpringDevice(player, object);
				} else if (id == 43527) {
					BarbarianOutpostAgility.crossBalanceBeam(player, object);
				} else if (id == 43531) {
					BarbarianOutpostAgility.jumpOverGap(player, object);
				} else if (id == 43532) {
					BarbarianOutpostAgility.slideDownRoof(player, object);
				}
				// rock living caverns
				if (id == 45077) {
					player.lock();
					if (player.getX() != object.getX() || player.getY() != object.getY()) {
						player.addWalkSteps(object.getX(), object.getY(), -1, false);
					}
					WorldTasksManager.schedule(new WorldTask() {

						private int count;

						@Override
						public void run() {
							if (count == 0) {
								player.setNextFaceWorldTile(new WorldTile(object.getX() - 1, object.getY(), 0));
								player.setNextAnimation(new Animation(12216));
								player.unlock();
							} else if (count == 2) {
								player.setNextWorldTile(new WorldTile(3651, 5122, 0));
								player.setNextFaceWorldTile(new WorldTile(3651, 5121, 0));
								player.setNextAnimation(new Animation(12217));
							} else if (count == 3) {
								// TODO find emote
								// player.getPackets().sendObjectAnimation(new
								// WorldObject(45078, 0, 3, 3651, 5123, 0), new
								// Animation(12220));
							} else if (count == 5) {
								player.unlock();
								stop();
							}
							count++;
						}

					}, 1, 0);
					// }else if (id == 38660 || id == 38661 || id == 38662 || id
					// == 38663 || id == 38664 || id == 38665 || id == 38666 ||
					// id == 38667 || id == 38668)
					// player.getActionManager().setAction(new Mining(object,
					// RockDefinitions.CRASHED_STAR));
				} else if (id == 45076) {
					player.getActionManager().setAction(new Mining(object, RockDefinitions.LRC_Gold_Ore));
				} else if (id == 5999) {
					player.getActionManager().setAction(new Mining(object, RockDefinitions.LRC_Coal_Ore));
				} else if (id == 11194 || id == 11364) {
					player.getActionManager().setAction(new Mining(object, RockDefinitions.Gem_Rock));
				} else if (id == 4028) {
					player.getActionManager().setAction(new Mining(object, RockDefinitions.SkillBoss_Ore));
				} else if (id == 2330) {
					player.getActionManager().setAction(new Mining(object, RockDefinitions.Red_Standstone_Ore));
				} else if (id == 4027) {
					player.getActionManager().setAction(new Mining(object, RockDefinitions.Limestone_Ore));
				} else if (id == 45078) {
					player.useStairs(2413, new WorldTile(3012, 9832, 0), 2, 2);
				} else if (id == 45079) {
					player.getBank().openDepositBox();
				} else if (id == 66115 || id == 66116) {
					player.dig(player);
				} else if (id == 24357 && object.getX() == 3188 && object.getY() == 3355) {
					player.useStairs(-1, new WorldTile(3189, 3354, 1), 0, 1);
				} else if (id == 24359 && object.getX() == 3188 && object.getY() == 3355) {
					player.useStairs(-1, new WorldTile(3189, 3358, 0), 0, 1);
				} else if (id == 1805 && object.getX() == 3191 && object.getY() == 3363) {
					WorldObject openedDoor = new WorldObject(object.getId(), object.getType(), object.getRotation() - 1,
							object.getX(), object.getY(), object.getPlane());
					if (World.removeTemporaryObject(object, 1200, false)) {
						World.spawnTemporaryObject(openedDoor, 1200, false);
						player.lock(2);
						player.stopAll();
						player.addWalkSteps(3191, player.getY() >= object.getY() ? object.getY() - 1 : object.getY(),
								-1, false);
						if (player.getY() >= object.getY()) {
							player.getDialogueManager().startDialogue("SimpleNPCMessage", 198,
									"Greetings bolt adventurer. Welcome to the guild of", "Champions.");
						}
					}

				}
				/**
				 * kuradal dung
				 */
				else if (id == 47236 && object.getX() == 1634 && object.getY() == 5254
						&& player.getDirection() == 12288) {
					player.addWalkSteps(1635, 5254, -1, false);
				} else if (id == 47236 && object.getX() == 1634 && object.getY() == 5254 && player.getDirection() == 4096) {
					player.addWalkSteps(1633, 5254, -1, false);
				} else if (id == 47236 && object.getX() == 1634 && object.getY() == 5253
						&& player.getDirection() == 12288) {
					player.addWalkSteps(1635, 5253, -1, false);
				} else if (id == 47236 && object.getX() == 1634 && object.getY() == 5253 && player.getDirection() == 4096) {
					player.addWalkSteps(1633, 5253, -1, false);
				} else if (id == 47236 && object.getX() == 1634 && object.getY() == 5252
						&& player.getDirection() == 12288) {
					player.addWalkSteps(1635, 5252, -1, false);
				} else if (id == 47236 && object.getX() == 1634 && object.getY() == 5252 && player.getDirection() == 4096) {
					player.addWalkSteps(1633, 5252, -1, false);
				} else if (id == 47236 && object.getX() == 1606 && object.getY() == 5265 && player.getDirection() == 8192) {
					player.addWalkSteps(1606, 5266, -1, false);
				} else if (id == 47236 && object.getX() == 1606 && object.getY() == 5265 && player.getDirection() == 0) {
					player.addWalkSteps(1604, 5263, -1, false);
				} else if (id == 47236 && object.getX() == 1605 && object.getY() == 5265 && player.getDirection() == 8192) {
					player.addWalkSteps(1605, 5266, -1, false);
				} else if (id == 47236 && object.getX() == 1605 && object.getY() == 5265 && player.getDirection() == 0) {
					player.addWalkSteps(1605, 5263, -1, false);
				} else if (id == 47236 && object.getX() == 1604 && object.getY() == 5265 && player.getDirection() == 8192) {
					player.addWalkSteps(1604, 5266, -1, false);
				} else if (id == 47236 && object.getX() == 1604 && object.getY() == 5265 && player.getDirection() == 0) {
					player.addWalkSteps(1606, 5263, -1, false);
				} else if (id == 47236 && object.getX() == 1611 && object.getY() == 5289 && player.getDirection() == 8192) {
					player.addWalkSteps(1611, 5290, -1, false);
				} else if (id == 47236 && object.getX() == 1611 && object.getY() == 5289 && player.getDirection() == 0) {
					player.addWalkSteps(1611, 5287, -1, false);
				} else if (id == 47236 && object.getX() == 1610 && object.getY() == 5289 && player.getDirection() == 8192) {
					player.addWalkSteps(1610, 5290, -1, false);
				} else if (id == 47236 && object.getX() == 1610 && object.getY() == 5289 && player.getDirection() == 0) {
					player.addWalkSteps(1610, 5287, -1, false);
				} else if (id == 47236 && object.getX() == 1609 && object.getY() == 5289 && player.getDirection() == 8192) {
					player.addWalkSteps(1609, 5290, -1, false);
				} else if (id == 47236 && object.getX() == 1609 && object.getY() == 5289 && player.getDirection() == 0) {
					player.addWalkSteps(1609, 5287, -1, false);
				} else if (id == 47236 && object.getX() == 1625 && object.getY() == 5301
						&& player.getDirection() == 12288) {
					player.addWalkSteps(1627, 5301, -1, false);
				} else if (id == 47236 && object.getX() == 1625 && object.getY() == 5301 && player.getDirection() == 4096) {
					player.addWalkSteps(1624, 5301, -1, false);
				} else if (id == 47236 && object.getX() == 1625 && object.getY() == 5302
						&& player.getDirection() == 12288) {
					player.addWalkSteps(1627, 5302, -1, false);
				} else if (id == 47236 && object.getX() == 1625 && object.getY() == 5302 && player.getDirection() == 4096) {
					player.addWalkSteps(1624, 5301, -1, false);
				} else if (id == 47236 && object.getX() == 1625 && object.getY() == 5303
						&& player.getDirection() == 12288) {
					player.addWalkSteps(1627, 5303, -1, false);
				} else if (id == 47236 && object.getX() == 1625 && object.getY() == 5303 && player.getDirection() == 4096) {
					player.addWalkSteps(1624, 5301, -1, false);
				} else if (id == 47236 && object.getX() == 1649 && object.getY() == 5301
						&& player.getDirection() == 12288) {
					player.addWalkSteps(1651, 5301, -1, false);
				} else if (id == 47236 && object.getX() == 1649 && object.getY() == 5301 && player.getDirection() == 4096) {
					player.addWalkSteps(1648, 5303, -1, false);
				} else if (id == 47236 && object.getX() == 1649 && object.getY() == 5302
						&& player.getDirection() == 12288) {
					player.addWalkSteps(1651, 5302, -1, false);
				} else if (id == 47236 && object.getX() == 1649 && object.getY() == 5302 && player.getDirection() == 4096) {
					player.addWalkSteps(1648, 5303, -1, false);
				} else if (id == 47236 && object.getX() == 1649 && object.getY() == 5303
						&& player.getDirection() == 12288) {
					player.addWalkSteps(1651, 5303, -1, false);
				} else if (id == 47236 && object.getX() == 1649 && object.getY() == 5303 && player.getDirection() == 4096) {
					player.addWalkSteps(1648, 5303, -1, false);
				} else if (id == 47236 && object.getX() == 1650 && object.getY() == 5281 && player.getDirection() == 0) {
					player.addWalkSteps(1650, 5279, -1, false);
				} else if (id == 47236 && object.getX() == 1650 && object.getY() == 5281 && player.getDirection() == 8192) {
					player.addWalkSteps(1650, 5282, -1, false);
				} else if (id == 47236 && object.getX() == 1651 && object.getY() == 5281 && player.getDirection() == 0) {
					player.addWalkSteps(1650, 5279, -1, false);
				} else if (id == 47236 && object.getX() == 1651 && object.getY() == 5281 && player.getDirection() == 8192) {
					player.addWalkSteps(1650, 5282, -1, false);
				} else if (id == 47236 && object.getX() == 1652 && object.getY() == 5281 && player.getDirection() == 0) {
					player.addWalkSteps(1650, 5279, -1, false);
				} else if (id == 47236 && object.getX() == 1652 && object.getY() == 5281 && player.getDirection() == 8192) {
					player.addWalkSteps(1650, 5282, -1, false);
				} else if (id == 29355 && object.getX() == 3230 && object.getY() == 9904) {
					// dungeon
					// climb
					// to
					// bear
					player.useStairs(828, new WorldTile(3229, 3503, 0), 1, 2);
				} else if (id == 24264) {
					player.useStairs(833, new WorldTile(3229, 9904, 0), 1, 2);
				} else if (id == 24366) {
					player.useStairs(828, new WorldTile(3237, 3459, 0), 1, 2);
				} else if (id == 882 && object.getX() == 3237 && object.getY() == 3458) {
					player.useStairs(833, new WorldTile(3237, 9858, 0), 1, 2);
				} else if (id == 29355 && object.getX() == 3097 && object.getY() == 9867) {
					// dungeon
					// climb
					player.useStairs(828, new WorldTile(3096, 3468, 0), 1, 2);
				} else if (id == 10804 && World.DZ5(player)) {
					player.useStairs(833, new WorldTile(2429, 9824, 0), 1, 2);
				} else if (id == 227257 || id == 227258) {
					player.useStairs(833, new WorldTile(3413, 5278, 0), 1, 2);
				} else if (id == 230175 && player.getX() == 2429 || id == 230174 && player.getX() == 2429) {
					player.useStairs(833, new WorldTile(2435, 9806, 0), 1, 2);
				} else if (id == 230175 && player.getX() == 2435 || id == 230174 && player.getX() == 2435) {
					player.useStairs(833, new WorldTile(2429, 9807, 0), 1, 2);
				} else if (id == 26934) {
					player.useStairs(833, new WorldTile(3097, 9868, 0), 1, 2);
				} else if (id == 29355 && object.getX() == 3088 && object.getY() == 9971) {
					player.useStairs(828, new WorldTile(3087, 3571, 0), 1, 2);
				} else if (id == 65453) {
					player.useStairs(833, new WorldTile(3089, 9971, 0), 1, 2);
				} else if (id == 12389 && object.getX() == 3116 && object.getY() == 3452) {
					player.useStairs(833, new WorldTile(3117, 9852, 0), 1, 2);
				} else if (id == 29355 && object.getX() == 3116 && object.getY() == 9852) {
					player.useStairs(833, new WorldTile(3115, 3452, 0), 1, 2);
				} else if (id == 69526) {
					GnomeAgility.walkGnomeLog(player);
				} else if (id == 69383) {
					GnomeAgility.climbGnomeObstacleNet(player);
				} else if (id == 69508) {
					GnomeAgility.climbUpGnomeTreeBranch(player);
				} else if (id == 2312) {
					GnomeAgility.walkGnomeRope(player);
				} else if (id == 4059) {
					GnomeAgility.walkBackGnomeRope(player);
				} else if (id == 69507) {
					GnomeAgility.climbDownGnomeTreeBranch(player);
				} else if (id == 69384) {
					GnomeAgility.climbGnomeObstacleNet2(player);
				} else if (id == 69506) {
					GnomeAgility.climbUpTree(player);
				} else if (id == 69514) {
					GnomeAgility.RunGnomeBoard(player, object);
				} else if (id == 69389) {
					GnomeAgility.JumpDown(player, object);
				} else if (id == 69377 || id == 69378) {
					GnomeAgility.enterGnomePipe(player, object.getX(), object.getY());
				} else if (Wilderness.isDitch(id)) {// wild ditch
					player.getDialogueManager().startDialogue("WildernessDitch", object);
				} else if (id == 42611) {// Magic Portal
					player.getDialogueManager().startDialogue("MagicPortal");
				} else if (object.getDefinitions().name.equalsIgnoreCase("Obelisk") && object.getY() > 3525) {
					// Who the fuck removed the controler class and the code
					// from SONIC!!!!!!!!!!
					// That was an hour of collecting coords :fp: Now ima kill
					// myself.
				} else if (id == 27254) {// Edgeville portal
					player.getPackets().sendGameMessage("You enter the portal...");
					player.useStairs(12776, new WorldTile(3377, 3513, 0), 2, 3, "..and are transported to Bal'lak.");
					//player.addWalkSteps(1598, 4506, -1, false);
				} else if (id == 12202) {// mole entrance
					if (!player.getInventory().containsItem(952, 1)) {
						player.getPackets().sendGameMessage("You need a spade to dig this.");
						return;
					}
					if (player.getX() != object.getX() || player.getY() != object.getY()) {
						player.lock();
						player.addWalkSteps(object.getX(), object.getY());
						WorldTasksManager.schedule(new WorldTask() {
							@Override
							public void run() {
								player.dig(player);
							}

						}, 1);
					} else {
						player.dig(player);
					}
				} else if (id == 12230 && object.getX() == 1752 && object.getY() == 5136) {// mole
					// exit
					player.setNextWorldTile(new WorldTile(2986, 3316, 0));
				} else if (id == 15522) {// portal sign
					if (player.withinDistance(new WorldTile(1598, 4504, 0), 1)) {// PORTAL
						// 1
						player.getInterfaceManager().sendInterface(327);
						player.getPackets().sendIComponentText(327, 13, "Edgeville");
						player.getPackets().sendIComponentText(327, 14, "This portal will take you to edgeville. There "
								+ "you can multi pk once past the wilderness ditch.");
					}
					if (player.withinDistance(new WorldTile(1598, 4508, 0), 1)) {// PORTAL
						// 2
						player.getInterfaceManager().sendInterface(327);
						player.getPackets().sendIComponentText(327, 13, "Mage Bank");
						player.getPackets().sendIComponentText(327, 14, "This portal will take you to the mage bank. "
								+ "The mage bank is a 1v1 deep wilderness area.");
					}
					if (player.withinDistance(new WorldTile(1598, 4513, 0), 1)) {// PORTAL
						// 3
						player.getInterfaceManager().sendInterface(327);
						player.getPackets().sendIComponentText(327, 13, "Magic's Portal");
						player.getPackets().sendIComponentText(327, 14,
								"This portal will allow you to teleport to areas that "
										+ "will allow you to change your magic spell book.");
					}
				} else if (id == 38811 || id == 37929) {// corp beast
					if (object.getX() == 2971 && object.getY() == 4382) {
						if (player.getFamiliar() != null || player.getPet() != null || Summoning.hasPouch(player)) {
							player.getDialogueManager().startDialogue("SimpleNPCMessage", 8133,
									"This cave is no place for vermin! Get rid!");
							return;
						}
						player.getInterfaceManager().sendInterface(650);
					} else if (object.getX() == 2918 && object.getY() == 4382) {
						player.stopAll();
						player.setNextWorldTile(
								new WorldTile(player.getX() == 2921 ? 2917 : 2921, player.getY(), player.getPlane()));
					}
				} else if (id == 1722) {// home stairs
					// if (object.getX() == 2853 && object.getY() == 5093) {
					player.setNextWorldTile(new WorldTile(2854, 5096, 1));
					// }
					/*
					 * } else if (id == 1722) {// home stairs if (object.getX()
					 * == 2841 && object.getY() == 5093) {
					 * player.setNextWorldTile(new WorldTile(2854, 5096, 1)); }
					 */
				} else if (id == 37928 && object.getX() == 2883 && object.getY() == 4370) {
					player.stopAll();
					player.setNextWorldTile(new WorldTile(3214, 3782, 0));
					player.getControlerManager().startControler("Wilderness");
				} else if (id == 38815 && object.getX() == 3209 && object.getY() == 3780 && object.getPlane() == 0) {
					if (player.getSkills().getLevelForXp(Skills.WOODCUTTING) < 37
							|| player.getSkills().getLevelForXp(Skills.MINING) < 45
							|| player.getSkills().getLevelForXp(Skills.SUMMONING) < 23
							|| player.getSkills().getLevelForXp(Skills.FIREMAKING) < 47
							|| player.getSkills().getLevelForXp(Skills.PRAYER) < 55) {
						player.getPackets().sendGameMessage(
								"You need 23 Summoning, 37 Woodcutting, 45 Mining, 47 Firemaking and 55 Prayer to enter this dungeon.");
						return;
					}
					player.stopAll();
					player.setNextWorldTile(new WorldTile(2885, 4372, 2));
					player.getControlerManager().forceStop();
					// TODO all reqs, skills not added
				} else if (id == 48803 && player.isKalphiteLairSetted()) {
					player.setNextWorldTile(new WorldTile(3508, 9494, 0));
				} else if (id == 48802 && player.isKalphiteLairEntranceSetted()) {
					player.setNextWorldTile(new WorldTile(3483, 9510, 2));
				} else if (id == 3829) {
					if (object.getX() == 3483 && object.getY() == 9510) {
						player.useStairs(828, new WorldTile(3226, 3108, 0), 1, 2);
					}
				} else if (id == 3832) {
					if (object.getX() == 3508 && object.getY() == 9494) {
						player.useStairs(828, new WorldTile(3509, 9496, 2), 1, 2);
					}
				} else if (id == 25338) {
					player.setNextWorldTile(new WorldTile(1772, 5366, 0));
				} else if (id == 9369) {
					player.getControlerManager().startControler("FightPits");
				} else if (id == 54019 || id == 54020 || id == 55301) {
					PkRank.showRanks(player);
				} else if (id == 1817 && object.getX() == 2273 && object.getY() == 4680) { // kbd
					// lever
					Magic.pushLeverTeleport(player, new WorldTile(3067, 10254, 0));
				} else if (id == 1816 && object.getX() == 3067 && object.getY() == 10252) { // kbd
					// out
					// lever
					Magic.pushLeverTeleport(player, new WorldTile(2273, 4681, 0));
				} else if (id == 32015 && object.getX() == 3069 && object.getY() == 10256) { // kbd
					// stairs
					player.useStairs(828, new WorldTile(3017, 3848, 0), 1, 2);
					player.getControlerManager().startControler("Wilderness");
				} else if (id == 1765 && object.getX() == 3017 && object.getY() == 3849) { // kbd
					// out
					// stairs
					player.stopAll();
					player.setNextWorldTile(new WorldTile(3069, 10255, 0));
					player.getControlerManager().forceStop();
				} else if (id == 14315) {
					if (Lander.canEnter(player, 0)) {
						return;
					}
				} else if (id == 25631) {
					if (Lander.canEnter(player, 1)) {
						return;
					}
				} else if (id == 25632) {
					if (Lander.canEnter(player, 2)) {
						return;
					}
				} else if (id == 5959) {
					Magic.pushLeverTeleport(player, new WorldTile(2539, 4712, 0));
				} else if (id == 5960) {
					Magic.pushLeverTeleport(player, new WorldTile(3089, 3957, 0));
				} else if (id == 1814) {
					Magic.pushLeverTeleport(player, new WorldTile(3155, 3923, 0));
				} else if (id == 1815) {
					Magic.pushLeverTeleport(player, new WorldTile(2561, 3311, 0));
				} else if (id == 62675) {
					player.getCutscenesManager().play("DTPreview");
				} else if (id == 62681) {
					player.getDominionTower().viewScoreBoard();
				} else if (id == 62678 || id == 62679) {
					player.getDominionTower().openModes();
				} else if (id == 62688) {
					player.getDialogueManager().startDialogue("DTClaimRewards");
				} else if (id == 62677) {
					player.getDominionTower().talkToFace();
				} else if (id == 62680) {
					player.getDominionTower().openBankChest();
				} else if (id == 48797) {
					player.useStairs(-1, new WorldTile(3877, 5526, 1), 0, 1);
				} else if (id == 48798) {
					player.useStairs(-1, new WorldTile(3246, 3198, 0), 0, 1);
				} else if (id == 48678 && x == 3858 && y == 5533) {
					player.useStairs(-1, new WorldTile(3861, 5533, 0), 0, 1);
				} else if (id == 48678 && x == 3858 && y == 5543) {
					player.useStairs(-1, new WorldTile(3861, 5543, 0), 0, 1);
				} else if (id == 48678 && x == 3858 && y == 5533) {
					player.useStairs(-1, new WorldTile(3861, 5533, 0), 0, 1);
				} else if (id == 48677 && x == 3858 && y == 5543) {
					player.useStairs(-1, new WorldTile(3856, 5543, 1), 0, 1);
				} else if (id == 48677 && x == 3858 && y == 5533) {
					player.useStairs(-1, new WorldTile(3856, 5533, 1), 0, 1);
				} else if (id == 48679) {
					player.useStairs(-1, new WorldTile(3875, 5527, 1), 0, 1);
				} else if (id == 48688) {
					player.useStairs(-1, new WorldTile(3972, 5565, 0), 0, 1);
				} else if (id == 48683) {
					player.useStairs(-1, new WorldTile(3868, 5524, 0), 0, 1);
				} else if (id == 48682) {
					player.useStairs(-1, new WorldTile(3869, 5524, 0), 0, 1);
				} else if (id == 62676) { // dominion exit
					player.useStairs(-1, new WorldTile(3374, 3093, 0), 0, 1);
				} else if (id == 62674) { // dominion entrance
					player.useStairs(-1, new WorldTile(3744, 6405, 0), 0, 1);
				} else if (id == 3192) {
					PkRank.showRanks(player);
				} else if (id == 30205) {
					CalamityBestWave.showRanks(player);
					//TopVoters.showRanks(player);
				} else if (id == 65349) {
					player.useStairs(-1, new WorldTile(3044, 10325, 0), 0, 1);
				} else if (id == 32048 && object.getX() == 3043 && object.getY() == 10328) {
					player.useStairs(-1, new WorldTile(3045, 3927, 0), 0, 1);
				} else if (id == 26194) {
					player.getDialogueManager().startDialogue("PartyRoomLever");
				} else if (id == 61190 || id == 61191 || id == 61192 || id == 61193) {
					if (objectDef.containsOption(0, "Chop down")) {
						player.getActionManager().setAction(new Woodcutting(object, TreeDefinitions.NORMAL));
					}
				} else if (id == 11435) {
					if (objectDef.containsOption(0, "Chop")) {
						player.getActionManager()
								.setAction(new EvilTreeEvent(object, EvilTreeDefinitions.NORMAL_EVIL_TREE));
					}
				} else if (id == 11437) {
					if (objectDef.containsOption(0, "Chop")) {
						player.getActionManager()
								.setAction(new EvilTreeEvent(object, EvilTreeDefinitions.EVIL_OAK_TREE));
					}
				} else if (id == 11441) {
					if (objectDef.containsOption(0, "Chop")) {
						player.getActionManager()
								.setAction(new EvilTreeEvent(object, EvilTreeDefinitions.EVIL_WILLOW_TREE));
					}
				} else if (id == 11444) {
					if (objectDef.containsOption(0, "Chop")) {
						player.getActionManager()
								.setAction(new EvilTreeEvent(object, EvilTreeDefinitions.EVIL_MAPLE_TREE));
					}
				} else if (id == 11916) {
					if (objectDef.containsOption(0, "Chop")) {
						player.getActionManager()
								.setAction(new EvilTreeEvent(object, EvilTreeDefinitions.EVIL_YEW_TREE));
					}
				} else if (id == 11919) {
					if (objectDef.containsOption(0, "Chop")) {
						player.getActionManager()
								.setAction(new EvilTreeEvent(object, EvilTreeDefinitions.EVIL_MAGIC_TREE));
					}
				} else if (id == 11922) {
					if (objectDef.containsOption(0, "Chop")) {
						player.getActionManager()
								.setAction(new EvilTreeEvent(object, EvilTreeDefinitions.EVIL_ELDER_TREE));
					}
				} else if (id == 11923) {
					if (objectDef.containsOption(0, "Chop")) {
						player.getActionManager().setAction(new Woodcutting(object, TreeDefinitions.AFK_TREE));
					}
				} else if (id == 20573) {
					player.getControlerManager().startControler("RefugeOfFear");
				} else if (id == 67050) {
					player.useStairs(-1, new WorldTile(3359, 6110, 0), 0, 1);
				} else if (id == 67053) {
					player.useStairs(-1, new WorldTile(3120, 3519, 0), 0, 1);
				} else if (id == 67051) {
					player.getDialogueManager().startDialogue("Marv", false);
				} else if (id == 67052) {
					Crucible.enterCrucibleEntrance(player);
				} else if (id == 11354) {
					if (player.isPvpMode()) {
						return;
					}
					if (player.getFamiliar() != null || player.getPet() != null || Summoning.hasPouch(player) || Pets.hasPet(player)) {
						player.sendMessage("Pets aren't allowed here!");
						return;
					}
					player.getDialogueManager().startDialogue("TogConfirmation");
				}

				else {
					switch (object.getId()) {
						case 36000:
							if (player.templeoflightcharges < 1) {
								player.sendMessage(Colors.cyan + "You don't have any Temple of Light teleport charges!");
								player.getInterfaceManager().closeChatBoxInterface();
								player.getInterfaceManager().closeOverlay(true);
							} else {
								player.templeoflighttime = 500;
								Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(1899, 4639, 1));
								player.templeoflightcharges--;
								player.sendMessage("You have " + player.templeoflightcharges + " Temple of Light charges remaining!");
								player.sendMessage(Colors.cyan + "Your 5 minutes has started! Loot as many pillars & kill as many mobs as you can before the time runs out!");
								player.getInterfaceManager().closeChatBoxInterface();
								player.getInterfaceManager().closeOverlay(true);
							}
							break;
						case 25339:
							player.setNextWorldTile(new WorldTile(1778, 5343, 1));
							break;
						case 25340:
							player.setNextWorldTile(new WorldTile(1778, 5346, 0));
							break;
						case 13622: // Varrock
							Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3213, 3428, 0));
							break;
						case 13623: // Lumbridge
							Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3222, 3219, 0));
							break;
						case 13624: // Falador
							Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2965, 3380, 0));
							break;
						case 13625: // Camelot
							Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2726, 3485, 0));
							break;
						case 13627: // Yanille
							Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2606, 3092, 0));
							break;
						case 216687:
							player.getActionManager().setAction(new Mining(object, RockDefinitions.SHEAFESSENCE));
							break;
						// case 14971:
						// player.getDialogueManager().startDialogue("PotatoCommands");
						// break;
						case 13613:
							if (!player.getInventory().containsItem(590, 1)) {
								player.out("You need a tinder box to light the fireplace.");
							} else {
								player.firePlace = 6;
								player.out("You light the fire place.");
							}
							break;
						case 13611:
							if (!player.getInventory().containsItem(590, 1)) {
								player.out("You need a tinder box to light the fireplace.");
							} else {
								player.firePlace = 5;
								player.out("You light the fire place.");
							}
							break;
						case 45076:
							player.getActionManager().setAction(new Mining(object, RockDefinitions.LRC_Gold_Ore));
						case 13609:
							if (!player.getInventory().containsItem(590, 1)) {
								player.out("You need a tinder box to light the fireplace.");
							} else {
								player.firePlace = 4;
								player.out("You light the fire place.");
							}
							break;
					}

					/*
					 * switch (objectDef.name.toLowerCase()) { case "chair": if
					 * (id == 13581) { player.out(
					 * "This chair may break if I were to sit on it."); break; }
					 * Furniture.sitInChair(object, player);
					 * player.faceObject(object); case "throne":
					 * Furniture.sitInChair(object, player); break; }
					 */
					switch (objectDef.name.toLowerCase()) {
						case "trapdoor":
						case "manhole":
							if (objectDef.containsOption(0, "Open")) {
								if (object.getX() == 3237 && object.getY() == 3458) {
									player.sendMessage("This manhole was locked down under the order of Zaros.");
									return;
								}
								WorldObject openedHole = new WorldObject(object.getId() + 1, object.getType(),
										object.getRotation(), object.getX(), object.getY(), object.getPlane());
								// if (World.removeTemporaryObject(object, 60000,
								// true)) {
								player.faceObject(openedHole);
								World.spawnTemporaryObject(openedHole, 60000, true);
								// }
							}
							break;

						case "mysterious cape":

							player.getDialogueManager().startDialogue("MuseumGuard");
							player.getInterfaceManager().sendInterface(275);
							for (int i = 0; i < 310; i++) {
								player.getPackets().sendIComponentText(275, i, "");
							}
							player.getPackets().sendIComponentText(275, 1, "Completionist Cape Requirements");
							player.getPackets().sendIComponentText(275, 10, "");
							player.getPackets().sendIComponentText(275, 11,
									"" + (Skills.getTotalLevel(player) == 2496 ? "<str>" : "")
											+ "<shad=DA1010>Achieve 2496 total level");
							player.getPackets().sendIComponentText(275, 12, "" + (player.logsburnt >= 2500 ? "<str>" : "")
									+ "<shad=DA1010>Burn 2500 magic logs : " + player.logsburnt + "");
							player.getPackets().sendIComponentText(275, 13, "" + (player.oremined >= 500 ? "<str>" : "")
									+ "<shad=DA1010>Mine 500 runite ore : " + player.oremined + "");
							player.getPackets().sendIComponentText(275, 14, "" + (player.logscut >= 2500 ? "<str>" : "")
									+ "<shad=DA1010>Cut 2500 yew logs : " + player.logscut + "");
							player.getPackets().sendIComponentText(275, 15, "" + (player.fishcaught >= 1000 ? "<str>" : "")
									+ "<shad=DA1010>Catch 1000 rocktails : " + player.fishcaught + "");
							player.getPackets().sendIComponentText(275, 16,
									"" + (player.lapsrun >= 500 ? "<str>" : "")
											+ "<shad=DA1010>Complete 500 advanced barbarian agility course laps : "
											+ player.lapsrun + "");
							player.getPackets().sendIComponentText(275, 17, "" + (player.slaytask >= 80 ? "<str>" : "")
									+ "<shad=DA1010>Complete 80 slayer tasks : " + player.slaytask + "");
							player.getPackets().sendIComponentText(275, 18,
									"" + (player.heistgamesplayed >= 3 ? "<str>" : "")
											+ "<shad=DA1010>Complete 3 Heists : " + player.heistgamesplayed + "");
							player.getPackets().sendIComponentText(275, 19, "" + (player.barsmelt >= 10 ? "<str>" : "")
									+ "<shad=DA1010>Smelt 10 ancient bars : " + player.barsmelt + "");
							player.getPackets().sendIComponentText(275, 20,
									"" + (player.getDominionTower().killedBossesCount >= 100 ? "<str>" : "")
											+ "<shad=DA1010>Kill 100 DT bosses : "
											+ player.getDominionTower().killedBossesCount + "");
							player.getPackets().sendIComponentText(275, 21,
									"" + (player.killedQueenBlackDragon ? "<str>" : "") + "<shad=DA1010>Kill the QBD.");
							player.getPackets().sendIComponentText(275, 22, "" + (player.completedFightKiln ? "<str>" : "")
									+ "<shad=DA1010>Complete the Fight Kiln.");
							player.getPackets().sendIComponentText(275, 23, "" + (player.completedFightCaves ? "<str>" : "")
									+ "<shad=DA1010>Complete the Fight Caves.");
							player.getPackets().sendIComponentText(275, 24,
									"" + (player.wonFightPits ? "<str>" : "") + "<shad=DA1010>Win the FightPits.");
							player.getPackets().sendIComponentText(275, 25, "" + (player.bandos >= 10 ? "<str>" : "")
									+ "<shad=DA1010>Kill 10 Bandos bosses : " + player.bandos + "");
							player.getPackets().sendIComponentText(275, 26, "" + (player.saradomin >= 10 ? "<str>" : "")
									+ "<shad=DA1010>Kill 10 Saradomin bosses : " + player.saradomin + "");
							player.getPackets().sendIComponentText(275, 27, "" + (player.zamorak >= 10 ? "<str>" : "")
									+ "<shad=DA1010>Kill 10 Zamorak bosses : " + player.zamorak + "");
							player.getPackets().sendIComponentText(275, 28, "" + (player.armadyl >= 10 ? "<str>" : "")
									+ "<shad=DA1010>Kill 10 Armadyl bosses : " + player.armadyl + "");
							player.getPackets().sendIComponentText(275, 29, "" + (AchievementDiaryInter.CompletedAllDiarys(player) ? "<str>" : "") + "<shad=DA1010>Complete Achievement Diaries to Elite.");
							player.getPackets().sendIComponentText(275, 30,
									"" + (player.royalcompmade ? "<str>" : "") + "<shad=DA1010>Create the royal crossbow.");
							player.getPackets().sendIComponentText(275, 31,
									"" + (player.CompletedAllQuests() ? "<str>" : "") + "<shad=DA1010>Completed all Quests.");
							player.getPackets().sendIComponentText(275,
									32, ""
											+ (player.isaverage || player.ishard || player.isheroic
											|| player.isIronman() ? "<str>" : "")
											+ "<shad=DA1010>Use any xp rate other than easy.");
							player.getPackets().sendIComponentText(275, 33, "" + (player.GazerKills >= 20 ? "<str>" : "")
									+ "<shad=DA1010>Kill 20 Night-Gazer bosses : " + player.GazerKills + "");
							player.getPackets().sendIComponentText(275, 34, "" + (player.cluescompleted >= 3 ? "<str>" : "")
									+ "<shad=DA1010>Complete atleast 3 clue scrolls : " + player.cluescompleted + "");
							player.getPackets().sendIComponentText(275, 36, "" + (player.pwamountcompleted >= 1 ? "<str>" : "")
									+ "<shad=DA1010>Complete Player wars atleast once : " + player.pwamountcompleted + "");
							player.getPackets().sendIComponentText(275, 38,
									"------------------------------------------------------------------------------------------------------- ");
							player.getPackets().sendIComponentText(275, 39,
									"<col=00FF00>Trimmed Completionist Cape Requirements!");
							player.getPackets().sendIComponentText(275, 40, "" + (player.ismusic >= 100 ? "<str>" : "")
									+ "<shad=00FF00>Discover 100 music tracks : " + player.ismusic + "");
							player.getPackets().sendIComponentText(275, 41, "" + (player.dunggkills >= 250 ? "<str>" : "")
									+ "<shad=00FF00>Complete 250 HL dung floors : " + player.dunggkills + "");
							player.getPackets().sendIComponentText(275, 42,
									"" + (player.getQuestManager().completedQuest(Quests.NOMADS_REQUIEM) ? "<str>" : "")
											+ "<shad=00FF00>Kill Nomad in his chamber.");
							player.getPackets().sendIComponentText(275, 43,
									"" + (player.isDonator() ? "<str>" : "") + "<shad=00FF00>Purchase Donator rank.");
							player.getPackets().sendIComponentText(275, 44, "" + (player.prestigeTokens >= 1 ? "<str>" : "")
									+ "<shad=00FF00>Prestige once : " + player.prestigeTokens + "");
							player.getPackets().sendIComponentText(275, 45,
									"" + (player.getDominionTower().killedBossesCount >= 250 ? "<str>" : "")
											+ "<shad=00FF00>Kill 250 DT Bosses : "
											+ player.getDominionTower().killedBossesCount + "");
							player.getPackets().sendIComponentText(275, 46, "" + (player.VoragoKills > 0 ? "<str>" : "")
									+ "<shad=00FF00>Unlock *The Defeater* title.");
							player.getPackets().sendIComponentText(275, 47,
									"" + (player.divine ? "<str>" : "") + "<shad=00FF00>Unlock the *Sigil* title.");
							player.getPackets().sendIComponentText(275, 48, "" + (player.kilnruns >= 3 ? "<str>" : "")
									+ "<shad=00FF00>Complete the fight kiln atleast 3 times : " + player.kilnruns + "");
							player.getPackets().sendIComponentText(275, 49,
									"" + (player.fbtitle ? "<str>" : "") + "<shad=00FF00>Unlock the *Final Boss* title.");
							player.getPackets().sendIComponentText(275, 50, "" + (player.Dboss >= 20 ? "<str>" : "")
									+ "<shad=00FF00>Kill 20 Donator bosses : " + player.Dboss + "");
							player.getPackets().sendIComponentText(275, 51,
									"" + (player.calamitytotalwavescomplete >= 1000 ? "<str>" : "")
											+ "<shad=00FF00>Complete 1,000 Calamity waves : "
											+ player.calamitytotalwavescomplete + "");
							player.getPackets().sendIComponentText(275, 52, "" + (AchievementDiaryInter.CompletedAllDiarysMaster(player) ? "<str>" : "") + "<shad=00FF00>Complete Achievement Diaries to Master.");
							// player.sendMessage("<col=00FF00>If you're after the
							// Trimmed Completion Cape, please speak to an
							// Administrator.");
							break;

						case "fairy ring":
							player.getFairyRing().handleObjects(object);
							break;

						case "closed chest":
							if (objectDef.containsOption(0, "Open") && object.getId() != 37009) {
								player.setNextAnimation(new Animation(536));
								player.lock(2);
								WorldObject openedChest = new WorldObject(object.getId() + 1, object.getType(),
										object.getRotation(), object.getX(), object.getY(), object.getPlane());
								// if (World.removeTemporaryObject(object, 60000,
								// true)) {
								player.faceObject(openedChest);
								World.spawnTemporaryObject(openedChest, 60000, true);
								// }
							}
							break;
						case "open chest":
							if (objectDef.containsOption(0, "Search")) {
								player.getPackets().sendGameMessage("You search the chest but find nothing.");
							}
							break;
						case "spiderweb":
							if (object.getRotation() == 2) {
								player.lock(2);
								if (Utils.getRandom(1) == 0) {
									player.addWalkSteps(player.getX(),
											player.getY() < y ? object.getY() + 2 : object.getY() - 1, -1, false);
									player.getPackets().sendGameMessage("You squeeze though the web.");
								} else {
									player.getPackets().sendGameMessage(
											"You fail to squeeze though the web; perhaps you should try again.");
								}
							}
							break;
						case "web":
							if (objectDef.containsOption(0, "Slash")) {
								player.setNextAnimation(
										new Animation(PlayerCombat.getWeaponAttackEmote(player.getEquipment().getWeaponId(),
												player.getCombatDefinitions().getAttackStyle(), player)));
								slashWeb(player, object);
							}
							break;
						case "anvil":
							if (objectDef.containsOption(0, "Smith")) {
								ForgingBar bar = ForgingBar.getBar(player);
								if (bar != null) {
									ForgingInterface.sendSmithingInterface(player, bar);
								} else {
									player.getPackets()
											.sendGameMessage("You have no bars which you have smithing level to use.");
								}
							}
							break;
						case "tin ore rocks":
							player.getActionManager().setAction(new Mining(object, RockDefinitions.Tin_Ore));
							break;
						case "gold ore rocks":
							player.getActionManager().setAction(new Mining(object, RockDefinitions.Gold_Ore));
							break;
						case "iron ore rocks":
							player.getActionManager().setAction(new Mining(object, RockDefinitions.Iron_Ore));
							break;
						case "silver ore rocks":
							player.getActionManager().setAction(new Mining(object, RockDefinitions.Silver_Ore));
							break;
						case "coal rocks":
							player.getActionManager().setAction(new Mining(object, RockDefinitions.Coal_Ore));
							break;
						case "clay rocks":
							player.getActionManager().setAction(new Mining(object, RockDefinitions.Clay_Ore));
							break;
						case "clay rock":
							if (World.Level3Zone(player)) {
								player.getActionManager().setAction(new Mining(object, RockDefinitions.LEVEL3_ORE));
							} else {
								player.getActionManager().setAction(new Mining(object, RockDefinitions.Clay_Ore));
							}
							break;
						case "copper ore rocks":
							player.getActionManager().setAction(new Mining(object, RockDefinitions.Copper_Ore));
							break;
						case "adamantite ore rocks":
							player.getActionManager().setAction(new Mining(object, RockDefinitions.Adamant_Ore));
							break;
						case "runite ore rocks":
							player.getActionManager().setAction(new Mining(object, RockDefinitions.Runite_Ore));
							break;
						case "dragon rocks":
							player.getActionManager().setAction(new Mining(object, RockDefinitions.Dragon_Ore));
							break;
						case "ancient ore rocks":
							player.getActionManager().setAction(new Mining(object, RockDefinitions.Blurite_Ore));
							break;

						case "granite rocks":
							player.getActionManager().setAction(new Mining(object, RockDefinitions.Granite_Ore));
							break;
						case "sandstone rocks":
							player.getActionManager().setAction(new Mining(object, RockDefinitions.Sandstone_Ore));
							break;
						case "mithril ore rocks":
							player.getActionManager().setAction(new Mining(object, RockDefinitions.Mithril_Ore));
							break;
						case "crashed star":
							player.getActionManager().setAction(new Mining(object, RockDefinitions.CRASHED_STAR));
							break;
						case "rune essence rock":
							player.getActionManager().setAction(new Mining(object, RockDefinitions.SHEAFESSENCE));
							break;
						case "bank deposit box":
						case "deposit box":
							if (objectDef.containsOption(0, "Deposit")) {
								player.getBank().openDepositBox();
							}
							break;
						case "bank":
						case "bank chest":
						case "bank booth":
						case "counter":
							if (objectDef.containsOption(0, "Bank") || objectDef.containsOption(0, "Use")) {
								player.getBank().openBank();
							}
							break;
						// Woodcutting start
						case "tree":
							if (objectDef.containsOption(0, "Chop down")) {
								player.getActionManager().setAction(new Woodcutting(object, TreeDefinitions.NORMAL));
							}
							break;
						case "evergreen":
							if (objectDef.containsOption(0, "Chop down")) {
								player.getActionManager().setAction(new Woodcutting(object, TreeDefinitions.EVERGREEN));
							}
							break;
						case "dead tree":
							if (objectDef.containsOption(0, "Chop down")) {
								player.getActionManager().setAction(new Woodcutting(object, TreeDefinitions.DEAD));
							}
							break;
						case "oak":
							if (objectDef.containsOption(0, "Chop down")) {
								player.getActionManager().setAction(new Woodcutting(object, TreeDefinitions.OAK));
							}
							break;
						case "willow":
							if (objectDef.containsOption(0, "Chop down")) {
								player.getActionManager().setAction(new Woodcutting(object, TreeDefinitions.WILLOW));
							}
							break;
						case "maple tree":
							if (objectDef.containsOption(0, "Chop down")) {
								player.getActionManager().setAction(new Woodcutting(object, TreeDefinitions.MAPLE));
							}
							break;
						case "teak":
							if (objectDef.containsOption(0, "Chop down")) {
								player.getActionManager().setAction(new Woodcutting(object, TreeDefinitions.TEAK));
							}
							break;
						case "mahogany":
							if (objectDef.containsOption(0, "Chop down")) {
								player.getActionManager().setAction(new Woodcutting(object, TreeDefinitions.MAHOGANY));
							}
							break;
						case "ivy":
							if (objectDef.containsOption(0, "Chop")) {
								player.getActionManager().setAction(new Woodcutting(object, TreeDefinitions.IVY));
							}
							break;
						case "yew":
							if (objectDef.containsOption(0, "Chop down")) {
								player.getActionManager().setAction(new Woodcutting(object, TreeDefinitions.YEW));
							}
							break;
						case "magic tree":
							if (objectDef.containsOption(0, "Chop down")) {
								player.getActionManager().setAction(new Woodcutting(object, TreeDefinitions.MAGIC));
							}
							break;
						case "cursed magic tree":
							if (objectDef.containsOption(0, "Chop down")) {
								player.getActionManager().setAction(new Woodcutting(object, TreeDefinitions.CURSED_MAGIC));
							}
							break;
						case "dream tree":
							if (objectDef.containsOption(0, "Chop")) {
								player.getActionManager().setAction(new Woodcutting(object, TreeDefinitions.DREAM_TREE));
							}
							break;
						case "crystal tree shard":
							if (objectDef.containsOption(0, "Chop")) {
								player.getActionManager().setAction(new Woodcutting(object, TreeDefinitions.CRYSTAL_TREE));
							}
							break;
						case "redwood":
							if (objectDef.containsOption(0, "Cut")) {
								player.getActionManager()
										.setAction(new Woodcutting(object, TreeDefinitions.REDWOOD_TREE));
							}
							break;
						case "achey tree":
							if (objectDef.containsOption(0, "Chop")) {
								player.getActionManager()
										.setAction(new Woodcutting(object, TreeDefinitions.SKILLBOSS_TREE));
							}
							break;
						// Woodcutting end
						case "gate":
						case "large door":
							// case "metal door":
							if (object.getType() == 0 && objectDef.containsOption(0, "Open")) {
								if (!handleGate(player, object)) {
									handleDoor(player, object);
								}
							}
							break;

						case "door":
							if (object.getType() == 0
									&& (objectDef.containsOption(0, "Open") || objectDef.containsOption(0, "Unlock"))) {
								handleDoor(player, object);
							}
							break;
						case "ladder":
						case "rope ladder":
							handleLadder(player, object, 1);
							break;
						case "carved redwood":
							if (player.getPlane() == 2)  {
								player.useStairs(828, new WorldTile(1570, 3488, 1), 1, 2);
							} else {
								player.useStairs(828, new WorldTile(1570, 3488, 2), 1, 2);
							}
							break;
						case "staircase":
							handleStaircases(player, object, 1);
							break;
						case "small obelisk":
							if (objectDef.containsOption(0, "Renew-points")) {
								int summonLevel = player.getSkills().getLevelForXp(Skills.SUMMONING);
								if (player.getSkills().getLevel(Skills.SUMMONING) < summonLevel) {
									player.lock(3);
									player.setNextAnimation(new Animation(8502));
									player.getSkills().set(Skills.SUMMONING, summonLevel);
									player.getSkills().restoreSummoning();
									player.getPackets().sendGameMessage("You have recharged your Summoning points.", true);
								} else {
									player.getPackets().sendGameMessage("You already have full Summoning points.");
								}
							}
							break;

						case "whirlpool":
							Magic.sendWhirlpoolTeleportSpell(player, 0, 0, new WorldTile(1763, 5365, 1));
							break;
						case "altar":
							if (objectDef.containsOption(0, "Pray") || objectDef.containsOption(0, "Pray-at")) {
								final int maxPrayer = player.getPrayer().maxPrayerPoints();
								if (player.getPrayer().getPrayerpoints() < maxPrayer) {
									player.lock(5);
									player.getPackets().sendGameMessage("You pray to the gods...", true);
									player.setNextAnimation(new Animation(645));
									WorldTasksManager.schedule(new WorldTask() {
										@Override
										public void run() {
											player.getPrayer().restorePrayer(maxPrayer);
											player.getPackets().sendGameMessage("...and recharged your prayer.", true);
										}
									}, 2);
								} else {
									player.getPackets().sendGameMessage("You already have full prayer.");
								}
								if (id == 6552) {
									player.getDialogueManager().startDialogue("AncientAltar");
								}
							}
							break;

						case "bandos altar":
							if (objectDef.containsOption(0, "Pray") || objectDef.containsOption(0, "Pray-at")) {
								final int maxPrayer = player.getPrayer().maxPrayerPoints();
								if (player.getPrayer().getPrayerpoints() < maxPrayer) {
									player.lock(5);
									player.getPackets().sendGameMessage("You pray to the gods...", true);
									player.setNextAnimation(new Animation(645));
									WorldTasksManager.schedule(new WorldTask() {
										@Override
										public void run() {
											player.getPrayer().restorePrayer(maxPrayer);
											player.getPackets().sendGameMessage("...and recharged your prayer.", true);
										}
									}, 2);
								} else {
									player.getPackets().sendGameMessage("You already have full prayer.");
								}
								if (id == 6552) {
									player.getDialogueManager().startDialogue("AncientAltar");
								}
							}
							break;
						case "armadyl altar":
							if (objectDef.containsOption(0, "Pray") || objectDef.containsOption(0, "Pray-at")) {
								final int maxPrayer = player.getPrayer().maxPrayerPoints();
								if (player.getPrayer().getPrayerpoints() < maxPrayer) {
									player.lock(5);
									player.getPackets().sendGameMessage("You pray to the gods...", true);
									player.setNextAnimation(new Animation(645));
									WorldTasksManager.schedule(new WorldTask() {
										@Override
										public void run() {
											player.getPrayer().restorePrayer(maxPrayer);
											player.getPackets().sendGameMessage("...and recharged your prayer.", true);
										}
									}, 2);
								} else {
									player.getPackets().sendGameMessage("You already have full prayer.");
								}
								if (id == 6552) {
									player.getDialogueManager().startDialogue("AncientAltar");
								}
							}
							break;
						case "saradomin altar":
							if (objectDef.containsOption(0, "Pray") || objectDef.containsOption(0, "Pray-at")) {
								final int maxPrayer = player.getPrayer().maxPrayerPoints();
								if (player.getPrayer().getPrayerpoints() < maxPrayer) {
									player.lock(5);
									player.getPackets().sendGameMessage("You pray to the gods...", true);
									player.setNextAnimation(new Animation(645));
									WorldTasksManager.schedule(new WorldTask() {
										@Override
										public void run() {
											player.getPrayer().restorePrayer(maxPrayer);
											player.getPackets().sendGameMessage("...and recharged your prayer.", true);
										}
									}, 2);
								} else {
									player.getPackets().sendGameMessage("You already have full prayer.");
								}
								if (id == 6552) {
									player.getDialogueManager().startDialogue("AncientAltar");
								}
							}
							break;
						case "zamorak altar":
							if (objectDef.containsOption(0, "Pray") || objectDef.containsOption(0, "Pray-at")) {
								final int maxPrayer = player.getPrayer().maxPrayerPoints();
								if (player.getPrayer().getPrayerpoints() < maxPrayer) {
									player.lock(5);
									player.getPackets().sendGameMessage("You pray to the gods...", true);
									player.setNextAnimation(new Animation(645));
									WorldTasksManager.schedule(new WorldTask() {
										@Override
										public void run() {
											player.getPrayer().restorePrayer(maxPrayer);
											player.getPackets().sendGameMessage("...and recharged your prayer.", true);
										}
									}, 2);
								} else {
									player.getPackets().sendGameMessage("You already have full prayer.");
								}
								if (id == 6552) {
									player.getDialogueManager().startDialogue("AncientAltar");
								}
							}
							break;

						/**
						 * Construction Teleport Portals
						 */

						case "vine":
						case "gap":
						case "cave entrance":
						case "steps":
							PolyporeDungeon.handleObjects(object, player);
							break;

						default:

							break;

					}
				}
				if (Settings.DEBUG) {
					Logger.log("ObjectHandler",
							"clicked 1 at object id : " + id + ", " + object.getX() + ", " + object.getY() + ", "
									+ object.getPlane() + ", " + object.getType() + ", " + object.getRotation() + ", "
									+ object.getDefinitions().name);
				}
			}
		}, objectDef.getSizeX(), Wilderness.isDitch(id) ? 4 : objectDef.getSizeY(), object.getRotation()));
	}

	private static void handleOption2(final Player player, final WorldObject object) {
		final ObjectDefinitions objectDef = object.getDefinitions();
		final int id = object.getId();
		player.setCoordsEvent(new CoordsEvent(object, new Runnable() {
			@Override
			public void run() {
				player.stopAll();
				player.faceObject(object);
				if (!player.getControlerManager().processObjectClick2(object)) {
					return;
				}
				if (player.getFarmingManager().isFarming(id, null, 2)) {
					return;
				}
				if (id == 67551) {
					LootInterface.HandleInterface(player, id);
				}
				if (id == 2588) {
					LootInterface.HandleInterface(player, id);
				}
				if (id == 13291) {
					LootInterface.HandleInterface(player, id);
				}
				if (id == 92692) {
					player.getBank().openBank();
				}
				if (id == 36970) {
					player.getDialogueManager().startDialogue("SpinningD");
					return;
				}
				if (object.getDefinitions().name.startsWith("Laboratory ")) {
					if (player.getSkills().getLevel(Skills.SLAYER) < 95) {
						player.sendMessage("You need at least 95 Slayer to kill a Legio.");
						return;
					}

					String name = object.getDefinitions().name.replace("Laboratory ", "");
					for (AscDoors door : AscDoors.values()) {
						if (door.name().equalsIgnoreCase(name)) {
							if (object.getDefinitions().getFirstOption().equals("Exit")) {
								player.getDialogueManager().startDialogue(new Dialogue() {
									@Override
									public void start() {
										Dialogue.sendOptionsDialogue(player, "What would you like to do?", "Exit Room", "Spawn Legio (Requires 1 Ascension Keystone " + name + ")", "Nevermind");
									}
									@Override
									public void run(int interfaceId, int componentId) {
										switch(componentId) {
											case OPTION_1:
												AscensionDungeon.exit(player, door.ordinal(), false, false);
												player.getInterfaceManager().closeChatBoxInterface();
												break;
											case OPTION_2:
												AscensionDungeon.spawnBoss(player, door.ordinal());
												player.getInterfaceManager().closeChatBoxInterface();
												break;
											default:
												player.getInterfaceManager().closeChatBoxInterface();
												break;

										}
									}
									@Override
									public void finish() {
									}

								});
							} else if (object.getDefinitions().getSecondOption().equals("Quick exit")) {
								AscensionDungeon.exit(player, door.ordinal(), false, false);
							}
						}
					}
					return;
				}

				if (id == 409) {
					player.getDialogueManager().startDialogue("AltarSpells");
					return;
				}
				if (id == 9292) {
					if (player.getY() >= 9295) {
						player.setNextWorldTile(new WorldTile(2791, 9294, 0));
						return;
					} else {
						player.setNextWorldTile(new WorldTile(2791, 9295, 0));
						return;
					}
				}
				if (id == 17819) {// vorago graveyard
					player.useStairs(-1, new WorldTile(3039, 6182, 0), 0, 1);
					return;
				} else if (object.getDefinitions().name.equalsIgnoreCase("furnace")) {
					player.getDialogueManager().startDialogue("SmeltingD", object);
				} else if (id == 17010) {
					player.getDialogueManager().startDialogue("LunarAltar");
				}
				String THIEVING_MESSAGE = "You successfully thieve from the stall";
				Animation THIEVING_ANIMATION = new Animation(881);
				boolean THIEVING_SUCCESS = false;// Eigenlijk moet dit false
				// if (player.getControlerManager() != null && id == 13674) {
				// player.getDialogueManager().startDialogue("ChallengeModeLeverD");
				// }
				if (id == 4875) {
					if (player.getInventory().getFreeSlots() < 1) {
						player.getPackets().sendGameMessage("Not enough space in your inventory.");
						return;
					}
					if (player.getSkills().getLevel(Skills.THIEVING) >= 30) {
						THIEVING_SUCCESS = true;
						if (Settings.eventdoubleskillingresources >0) {
							player.getInventory().addItem(1963, 2);
						}
						player.getInventory().addItem(1963, 1);
						player.getSkills().addXp(17, 70);
						if (player.challengeid == 20 && player.challengeamount > 0) {
							DailyChallenges.UpdateChallenge(player);
						}
					} else {
						player.getPackets().sendGameMessage("You need at least 30 thieving to steal from this stall");
					}
					if (id >= 15477 && id <= 15482 || id == 93284) {
						if (player.hasHouse) {
							player.getHouse().setBuildMode(false);
							player.getHouse().enterMyHouse();
						} else {
							player.getDialogueManager().startDialogue("SimpleMessage",
									"You must purchase a house in order to do this, You can buy a property from any Harmony Estate Agent.");
						}
						return;
					}
				} else if (id == 4874) {
					if (player.getInventory().getFreeSlots() < 1) {
						player.getPackets().sendGameMessage("Not enough space in your inventory.");
						return;
					}
					if (player.getActionManager().getActionDelay() > 0) {
						return;
					}
					if (player.getSkills().getLevel(Skills.THIEVING) >= 1) {
						THIEVING_SUCCESS = true;
						if (player.thievperk == true) {
							switch (Utils.random(9)) {
								case 1:
									player.getInventory().addItem(1739, 1);
									player.getPackets().sendGameMessage("Your perk has granted you an extra loot.", true);

									break;
								default:
									break;
							}
						}
						if (Utils.random(1, 10) == 1 && player.getEquipment().getCapeId() == 29975) {
							player.getBank().addItem(1739, 1, true);
							player.sendMessage("Your master thieving cape perk has sent your loot to your bank.");
						} else if (Utils.random(1, 10) == 1 && player.getEquipment().getCapeId() == 20771 && player.getSkills().getXp(Skills.THIEVING) >= 104273167) {
							player.getBank().addItem(1739, 1, true);
							player.sendMessage("Your master thieving cape perk has sent your loot to your bank.");
						} else {
							if (Settings.eventdoubleskillingresources >0) {
								player.getInventory().addItem(1739, 1);
							}
							player.getInventory().addItem(1739, 1);
						}
						if (player.challengeid == 19 && player.challengeamount > 0) {
							DailyChallenges.UpdateChallenge(player);
						}
						player.getSkills().addXp(17, 55);
						player.getActionManager().addActionDelay(1);
					} else {
						player.getPackets().sendGameMessage("You need at least 1 thieving to steal from this stall");
					}
				} else if (id == 4876) {
					if (player.getInventory().getFreeSlots() < 1) {
						player.getPackets().sendGameMessage("Not enough space in your inventory.");
						return;
					}
					if (player.getActionManager().getActionDelay() > 0) {
						return;
					}
					if (player.getSkills().getLevel(Skills.THIEVING) >= 50) {
						THIEVING_SUCCESS = true;
						if (player.thievperk == true) {
							switch (Utils.random(9)) {
								case 1:
									player.getInventory().addItem(950, 1);
									player.getPackets().sendGameMessage("Your perk has granted you an extra loot.", true);

									break;
								default:
									break;
							}
						}
						if (Utils.random(1, 10) == 1 && player.getEquipment().getCapeId() == 29975) {
							player.getBank().addItem(950, 2, true);
							player.sendMessage("Your master thieving cape perk has sent your loot to your bank.");
						} else if (Utils.random(1, 10) == 1 && player.getEquipment().getCapeId() == 20771 && player.getSkills().getXp(Skills.THIEVING) >= 104273167) {
							player.getBank().addItem(1739, 1, true);
							player.sendMessage("Your master thieving cape perk has sent your loot to your bank.");
						} else {
							if (Settings.eventdoubleskillingresources >0) {
								player.getInventory().addItem(950, 2);
							}
							player.getInventory().addItem(950, 1);
						}
						if (player.challengeid == 20 && player.challengeamount > 0) {
							DailyChallenges.UpdateChallenge(player);
						}
						player.getSkills().addXp(17, 80);
						player.getActionManager().addActionDelay(1);
					} else {
						player.getPackets().sendGameMessage("You need at least 50 thieving to steal from this stall");
					}
				} else if (id == 4877) {
					if (player.getInventory().getFreeSlots() < 1) {
						player.getPackets().sendGameMessage("Not enough space in your inventory.");
						return;
					}
					if (player.getActionManager().getActionDelay() > 0) {
						return;
					}
					if (player.getSkills().getLevel(Skills.THIEVING) >= 75) {
						THIEVING_SUCCESS = true;
						if (player.thievperk == true) {
							switch (Utils.random(9)) {
								case 1:
									player.getInventory().addItem(1635, 1);
									player.getPackets().sendGameMessage("Your perk has granted you an extra loot.", true);

									break;
								default:
									break;
							}
						}
						if (Utils.random(1, 10) == 1 && player.getEquipment().getCapeId() == 29975) {
							player.getBank().addItem(1635, 1, true);
							player.sendMessage("Your master thieving cape perk has sent your loot to your bank.");
						} else if (Utils.random(1, 10) == 1 && player.getEquipment().getCapeId() == 20771 && player.getSkills().getXp(Skills.THIEVING) >= 104273167) {
							player.getBank().addItem(1739, 1, true);
							player.sendMessage("Your master thieving cape perk has sent your loot to your bank.");
						} else {
							if (Settings.eventdoubleskillingresources >0) {
								player.getInventory().addItem(1635, 1);
							}
							player.getInventory().addItem(1635, 1);
						}
						if (player.challengeid == 21 && player.challengeamount > 0) {
							DailyChallenges.UpdateChallenge(player);
						}
						player.getSkills().addXp(17, 90);
						player.getActionManager().addActionDelay(1);
					} else {
						player.getPackets().sendGameMessage("You need at least 75 thieving to steal from this stall");
					}
				} else if (id == 4878) {
					if (player.getInventory().getFreeSlots() < 1) {
						player.getPackets().sendGameMessage("Not enough space in your inventory.");
						return;
					}
					if (player.getActionManager().getActionDelay() > 0) {
						return;
					}
					if (player.getSkills().getLevel(Skills.THIEVING) >= 85) {
						THIEVING_SUCCESS = true;
						if (player.thievperk == true) {
							switch (Utils.random(9)) {
								case 1:
									player.getInventory().addItem(1635, 1);
									player.getPackets().sendGameMessage("Your perk has granted you an extra loot.", true);

									break;
								default:
									break;
							}
						}
						if (Utils.random(1, 10) == 1 && player.getEquipment().getCapeId() == 29975) {
							player.getBank().addItem(11998, 1, true);
							player.sendMessage("Your master thieving cape perk has sent your loot to your bank.");
						} else if (Utils.random(1, 10) == 1 && player.getEquipment().getCapeId() == 20771 && player.getSkills().getXp(Skills.THIEVING) >= 104273167) {
							player.getBank().addItem(1739, 1, true);
							player.sendMessage("Your master thieving cape perk has sent your loot to your bank.");
						} else {
							if (Settings.eventdoubleskillingresources >0) {
								if (World.Level3Zone(player)) {
									player.getBank().addItem(11998, 1, true);
								} else {
									player.getInventory().addItem(11998, 1);
								}
							}
							if (World.Level3Zone(player)) {
								player.getBank().addItem(11998, 1, true);
							} else {
								player.getInventory().addItem(11998, 1);
							}
						}
						player.getSkills().addXp(17, 100);
						player.getActionManager().addActionDelay(1);
					} else {
						player.getPackets().sendGameMessage("You need at least 85 thieving to steal from this stall");
					}
				}
				if (THIEVING_SUCCESS) {
					player.setNextAnimation(new Animation(881));
					player.getPackets().sendGameMessage(THIEVING_MESSAGE);
					player.lock(1);
				} else
				if (id == 62677) {
					player.getDominionTower().openRewards();
				} else if (id == 13405 && player.isOwner == true && player.hasLocked == false
						&& player.inBuildMode == false) {
					player.out("You have locked your house, no one can enter.");
					player.hasLocked = true;
				} else if (id == 13405 && player.isOwner == true && player.hasLocked == true
						&& player.inBuildMode == false) {
					player.out("You have un-locked your house, friends can now enter.");
					player.hasLocked = false;
				} else if (id == 6) {
					player.getDwarfCannon().pickUpDwarfCannon(0, object);
				} else if (id == 29408) {
					player.getDwarfCannon().pickUpDwarfCannonRoyal(0, object);
				} else if (id == 29406) {
					player.getDwarfCannon().pickUpDwarfCannonGold(0, object);
				} else if (id == 70795) {
					player.setNextWorldTile(new WorldTile(1206, 6507, 0));
				} else if (id == 70812) {
					player.getControlerManager().startControler("QueenBlackDragonControler");
				} else if (object.getId() == 170) {
					player.getControlerManager().startControler("QueenBlackDragonControler");
				} else if (id == 62688) {
					player.getDialogueManager().startDialogue("SimpleMessage",
							"You have a Dominion Factor of " + player.getDominionTower().getDominionFactor() + ".");
				} else if (id == 68107) {
					FightKiln.enterFightKiln(player, true);
				} else if (id == 34384 || id == 34383 || id == 14011 || id == 7053 || id == 34387 || id == 34386
						|| id == 34385) {
					Thieving.handleStalls(player, object);
				} else if (object.getDefinitions().name.equalsIgnoreCase("Crashed star")) {
					player.getPackets()
							.sendGameMessage("The current size of the star is " + ShootingStar.stage + ".");
				} else if (id == 378 && Gchest.isBox(378, player)) {
					return;
				}
				// return;

//				else if (id == 13291)
//					PartyRoom.openPartyChest(player);
//				else if (id == 2418)
//
//					PartyRoom.openPartyChest(player);
				else if (id == 2646) {
					World.removeTemporaryObject(object, 50000, true);
					player.getInventory().addItem(1779, 1);
					// crucible
				} else if (id == 67051) {
					player.getDialogueManager().startDialogue("Marv", true);
				} else {
					switch (objectDef.name.toLowerCase()) {
						case "cabbage":
							if (objectDef.containsOption(1, "Pick") && player.getInventory().addItem(1965, 1)) {
								player.setNextAnimation(new Animation(827));
								player.lock(2);
								World.removeTemporaryObject(object, 60000, false);
							}
							break;
						case "bank":
						case "bank chest":
						case "bank booth":
						case "counter":
							if (objectDef.containsOption(1, "Bank")) {
								player.getBank().openBank();
							}
							break;

						case "gates":
						case "gate":
							// case "metal door":
							if (object.getType() == 0 && objectDef.containsOption(1, "Open")) {
								handleGate(player, object);
							}
							break;
						case "door":
							if (object.getType() == 0 && objectDef.containsOption(1, "Open")) {
								handleDoor(player, object);
							}
							break;
						case "ladder":
							handleLadder(player, object, 2);
							break;
						case "staircase":
							handleStaircases(player, object, 2);
							break;
						default:
							break;
					}
				}
				if (Settings.DEBUG) {
					Logger.log("ObjectHandler", "clicked 2 at object id : " + id + ", " + object.getX() + ", "
							+ object.getY() + ", " + object.getPlane());
				}
			}
		}, objectDef.getSizeX(), objectDef.getSizeY(), object.getRotation()));
	}

	private static void handleOption3(final Player player, final WorldObject object) {
		final ObjectDefinitions objectDef = object.getDefinitions();
		final int id = object.getId();
		player.setCoordsEvent(new CoordsEvent(object, new Runnable() {
			@Override
			public void run() {
				player.stopAll();
				player.faceObject(object);
				if (!player.getControlerManager().processObjectClick3(object)) {
					return;
				}
				if (id >= 15477 && id <= 15482 || id == 93284) {
					if (player.hasHouse) {
						player.getHouse().setBuildMode(true);
						player.getHouse().enterMyHouse();
					} else {
						player.getDialogueManager().startDialogue("SimpleMessage",
								"You must purchase a house in order to do this, You can buy a property from any Harmony Estate Agent.");
					}
					return;
				}
				if (player.getFarmingManager().isFarming(id, null, 3)) {
					return;
				}
				if (id == 409) {
					player.getDialogueManager().startDialogue("ZarosAltar");
					return;
				}
				switch (objectDef.name.toLowerCase()) {
					case "gate":
						// case "metal door":
						if (object.getType() == 0 && objectDef.containsOption(2, "Open")) {
							handleGate(player, object);
						}
						break;
					case "door":
						if (object.getType() == 0 && objectDef.containsOption(2, "Open")) {
							handleDoor(player, object);
						}
						break;
					case "bank":
					case "bank chest":
					case "bank booth":

						if (objectDef.containsOption(2, "Storage")) {
							if (player.purchasedbank2 != true) {
								player.sendMessage(Colors.red + "You haven't unlocked the storage bank yet! You can unlock it by donating. We've opened your regular bank instead.");
								player.getBank().openBank();
							} else {
								player.getBank2().openBank();
							}
						}
						break;
					case "ladder":
						handleLadder(player, object, 3);
						break;
					case "staircase":
						handleStaircases(player, object, 3);
						break;
					default:
						break;
				}
				if (Settings.DEBUG) {
					Logger.log("ObjectHandler", "cliked 3 at object id : " + id + ", " + object.getX() + ", "
							+ object.getY() + ", " + object.getPlane() + ", ");
				}
			}
		}, objectDef.getSizeX(), objectDef.getSizeY(), object.getRotation()));
	}

	private static void handleOption4(final Player player, final WorldObject object) {
		final ObjectDefinitions objectDef = object.getDefinitions();
		final int id = object.getId();
		player.setCoordsEvent(new CoordsEvent(object, new Runnable() {
			@Override
			public void run() {
				player.stopAll();
				player.faceObject(object);
				if (!player.getControlerManager().processObjectClick4(object)) {
					return;
				}
				// living rock Caverns
				if (id >= 15477 && id <= 15482 || id == 93284) {
					if (player.isIronman()) {
						player.sm(
								"You may not visit the houses of other players in ironman mode, however, other players may still visit yours.");
						return;
					}
					player.getTemporaryAttributtes().put("teleto_house", true);
					player.getPackets().sendRunScript(109, "Enter Friends Name:");
					return;
				}

				if (id == 45076) {
					MiningBase.propect(player, "This rock contains a large concentration of gold.");
				} else if (id == 5999) {
					MiningBase.propect(player, "This rock contains a large concentration of coal.");
				} else {
					switch (objectDef.name.toLowerCase()) {
						case "altar":
							if (player.turmoilunlocked != true) {
								player.sendMessage(Colors.red+"You must unlock the range/mage turmoil from the dungeoneering store first.");
								return;
							}
							player.getDialogueManager().startDialogue("RedirectTurmoilD");
							break;
						case "bank booth":
							if (!player.canSpawn()) {
								player.getPackets().sendGameMessage("You can't use this right now.");
								return;
							}
							if (player.getEquipment().getAuraId() >= 0) {
								player.getPackets().sendGameMessage("<col=ff0000>Please remove your Aura.");
								return;
							}
							player.getDialogueManager().startDialogue("GearPresetsD");

							break;
						default:

							break;
					}
				}
				if (Settings.DEBUG) {
					Logger.log("ObjectHandler", "cliked 4 at object id : " + id + ", " + object.getX() + ", "
							+ object.getY() + ", " + object.getPlane() + ", ");
				}
			}
		}, objectDef.getSizeX(), objectDef.getSizeY(), object.getRotation()));
	}

	private static void handleOption5(final Player player, final WorldObject object) {
		final ObjectDefinitions objectDef = object.getDefinitions();
		final int id = object.getId();
		player.setCoordsEvent(new CoordsEvent(object, new Runnable() {
			@Override
			public void run() {
				player.stopAll();
				player.faceObject(object);
				if (!player.getControlerManager().processObjectClick5(object)) {
					return;
				}
				if (objectDef.containsOption("Push-through")) {
					PuroPuro.pushThrough(player, object);
					return;
				}
				if (id == -1) {
					// unused
					/*
					 * } else if (object.getId() == 15412 || object.getId() ==
					 * 15410 || object.getId() == 15411) {
					 * player.getDialogueManager().startDialogue("MakeChair"); }
					 * else if (object.getId() == 15406 || object.getId() ==
					 * 15407 || object.getId() == 15408) {
					 * player.getDialogueManager().startDialogue("MakePortal");
					 * } else if (object.getId() == 13637 || object.getId() ==
					 * 13636) { player.portalFrame = 0; player.out(
					 * "You remove the portal frames, re-enter your house for it to take effect."
					 * ); } else if (object.getId() == 13581 || object.getId()
					 * == 13583 || object.getId() == 13584 || object.getId() ==
					 * 13587) { player.chair = 0; player.out(
					 * "You remove the chairs, re-enter your house for it to take effect."
					 * ); } else if (id == 13416 || id == 13413 || id == 13414
					 * || id == 13417) { player.tree = 0; player.out(
					 * "You remove the trees, re-enter your house for it to take effect."
					 * ); } else if (id == 13671 || id == 13670 || id == 13665)
					 * { player.throne = 0; player.out(
					 * "You remove the throne, re-enter your house for it to take effect."
					 * ); } else if (id == 13613 || id == 13611 || id == 13609)
					 * { player.firePlace = 0; player.out(
					 * "You remove the fireplace, re-enter your house for it to take effect."
					 * ); //} else if (id == 15416) {
					 * //player.getDialogueManager().startDialogue("Bookcases");
					 * //player.out(
					 * "You remove the bookcase, re-enter your house for it to take effect."
					 * ); } else if (object.getId() == 15270) {
					 * player.getDialogueManager().startDialogue("Altars"); /*}
					 * else if (object.getId() == 15416) {
					 * House.makeWardrobe(object, player); } else if
					 * (object.getId() == 15270) { House.makeAltar(object,
					 * player); } else if (object.getId() == 15260) {
					 * House.makeBed(object, player); } else if (object.getId()
					 * == 15361) { House.buildPortal(object, player); } else if
					 * (object.getId() == 13581) { House.whiteChair(object,
					 * player); } else if (object.getId() == 15409) {
					 * player.getDialogueManager().startDialogue("CreatePortal")
					 * ; } else if (object.getId() == 15426) {
					 * player.getDialogueManager().startDialogue("MakeThrone");
					 * } else if (id == 15362) {
					 * player.getDialogueManager().startDialogue("MakeTrees");
					 * // } else if (id == 15416 || id == 15397) { //
					 * player.getDialogueManager().startDialogue("MakeBook"); }
					 * else if (id == 15418) {
					 * player.getDialogueManager().startDialogue("MakeFire"); }
					 * else if (id == 15313) { //
					 * player.getDialogueManager().startDialogue("RoomCreation")
					 * ;
					 *
					 *
					 * //TODO /** Removing Construction Portals
					 *
					 * } else if (id == 13622 && player.portal1 == true) {
					 * player.portalTele1 = 0; player.out(
					 * "You remove the left portal, Changes take effect one you re-enter your house."
					 * ); player.portal1 = false; } else if (id == 13622 &&
					 * player.portal2 == true) { player.portalTele2 = 0;
					 * player.out(
					 * "You remove the center portal, Changes take effect one you re-enter your house."
					 * ); player.portal2 = false; } else if (id == 13622 &&
					 * player.portal3 == true) { player.portalTele3 = 0;
					 * player.out(
					 * "You remove the right portal, Changes take effect one you re-enter your house."
					 * ); player.portal3 = false; } else if (id == 13623 &&
					 * player.portal1 == true) { player.portalTele1 = 0;
					 * player.out(
					 * "You remove the left portal, Changes take effect one you re-enter your house."
					 * ); player.portal1 = false; } else if (id == 13623 &&
					 * player.portal2 == true) { player.portalTele2 = 0;
					 * player.out(
					 * "You remove the center portal, Changes take effect one you re-enter your house."
					 * ); player.portal2 = false; } else if (id == 13623 &&
					 * player.portal3 == true) { player.portalTele3 = 0;
					 * player.out(
					 * "You remove the right portal, Changes take effect one you re-enter your house."
					 * ); player.portal3 = false; } else if (id == 13624 &&
					 * player.portal1 == true) { player.portalTele1 = 0;
					 * player.out(
					 * "You remove the left portal, Changes take effect one you re-enter your house."
					 * ); player.portal1 = false; } else if (id == 13624 &&
					 * player.portal2 == true) { player.portalTele2 = 0;
					 * player.out(
					 * "You remove the center portal, Changes take effect one you re-enter your house."
					 * ); player.portal2 = false; } else if (id == 13624 &&
					 * player.portal3 == true) { player.portalTele3 = 0;
					 * player.out(
					 * "You remove the right portal, Changes take effect one you re-enter your house."
					 * ); player.portal3 = false; } else if (id == 13625 &&
					 * player.portal1 == true) { player.portalTele1 = 0;
					 * player.out(
					 * "You remove the left portal, Changes take effect one you re-enter your house."
					 * ); player.portal1 = false; } else if (id == 13625 &&
					 * player.portal2 == true) { player.portalTele2 = 0;
					 * player.out(
					 * "You remove the center portal, Changes take effect one you re-enter your house."
					 * ); player.portal2 = false; } else if (id == 13625 &&
					 * player.portal3 == true) { player.portalTele3 = 0;
					 * player.out(
					 * "You remove the right portal, Changes take effect one you re-enter your house."
					 * ); player.portal3 = false; } else if (id == 13627 &&
					 * player.portal1 == true) { player.portalTele1 = 0;
					 * player.out(
					 * "You remove the left portal, Changes take effect one you re-enter your house."
					 * ); player.portal1 = false; } else if (id == 13627 &&
					 * player.portal2 == true) { player.portalTele2 = 0;
					 * player.out(
					 * "You remove the center portal, Changes take effect one you re-enter your house."
					 * ); player.portal2 = false; } else if (id == 13627 &&
					 * player.portal3 == true) { player.portalTele3 = 0;
					 * player.out(
					 * "You remove the right portal, Changes take effect one you re-enter your house."
					 * ); player.portal3 = false;
					 */
				} else {
					switch (objectDef.name.toLowerCase()) {

						/**
						 * Construction
						 */

						/*
						 * case "chair space":
						 * player.getDialogueManager().startDialogue("Chairs");
						 * break;
						 *
						 * case "altar space":
						 * player.getDialogueManager().startDialogue("Altars");
						 * break;
						 *
						 * case "rug space":
						 * player.getDialogueManager().startDialogue("Rugs"); break;
						 *
						 * case "bookcase space":
						 * player.getDialogueManager().startDialogue("Bookcases");
						 * break;
						 *
						 * case "clock space":
						 * player.getDialogueManager().startDialogue("Clocks");
						 * break;
						 *
						 * case "heraldry space":
						 * player.getDialogueManager().startDialogue("Heraldrys");
						 * break;
						 *
						 * case "decoration space":
						 * player.getDialogueManager().startDialogue("Decorations");
						 * break;
						 *
						 * case "clockmaking space":
						 * ClockMaking.CheckCraftTable(player); player.lock(2);
						 * break;
						 *
						 * case "dresser space": Dresser.CheckDresser(player);
						 * player.lock(2); break;
						 *
						 * case "bell pull space": BellPull.CheckBell(player);
						 * player.lock(2); break;
						 *
						 * case "bed space": Bed.CheckBed(player); player.lock(2);
						 * break;
						 *
						 * case "tool space": Tool.CheckToolSpace(player);
						 * player.lock(2); break;
						 *
						 * case "repair space":
						 * RepairSpace.CheckRepairSpace(player); player.lock(2);
						 * break;
						 *
						 * case "curtain space": Curtain.CheckCurtain(player);
						 * player.lock(2); break;
						 *
						 * case "fireplace space": FirePlace.CheckFirePlace(player);
						 * player.lock(2); break;
						 *
						 * case "wardrobe space": Wardrobe.CheckWardrobe(player);
						 * player.lock(2); break;
						 *
						 * case "door hotspot":
						 * player.getInterfaceManager().sendInterface(402); break;
						 */
						/*
						 * case "bed space":
						 * if(!player.getInventory().containsItem(8778, 4)) {
						 * player.getPackets().sendGameMessage(
						 * "You need 4 oak planks to make a bed"); } else {
						 * player.getInventory().deleteItem(8778, 4);
						 * player.getSkills().addXp(Skills.CONSTRUCTION, 10000);
						 * player.setNextAnimation(new Animation(898));
						 * player.getPackets().sendGameMessage("You make a bed");
						 *
						 * } //case "chair space": // House.makeChair(object,
						 * player); //case "chair": // House.removeChair(object,
						 * player);
						 *
						 * // case "chair space": //
						 * if(!player.getInventory().containsItem(960, 4)) { //
						 * player.getPackets().sendGameMessage(
						 * "You need 4 planks to make a chair"); // } else { //
						 * player.getInventory().deleteItem(960, 4); //
						 * player.getSkills().addXp(Skills.CONSTRUCTION, 2000); //
						 * player.setNextAnimation(new Animation(898)); //
						 * player.getPackets().sendGameMessage("You make a chair");
						 * // } //case "chair": //World.spawnObject( // new
						 * WorldObject(15412, 10, 2, // player.getX() +1,
						 * player.getY(), player // .getPlane()), true);
						 *
						 * /*case "bookcase space":
						 * if(!player.getInventory().containsItem(8780, 4)) {
						 * //player.getPackets().sendGameMessage(
						 * "You need 4 teak planks to make a bookcase"); } else {
						 * player.getInventory().deleteItem(8780, 4);
						 * player.getSkills().addXp(Skills.CONSTRUCTION, 10000);
						 * player.setNextAnimation(new Animation(898));
						 * player.getPackets().sendGameMessage("You make a bookcase"
						 * ); } case "rug space":
						 * if(!player.getInventory().containsItem(8790, 4)) {
						 * //player.getPackets().sendGameMessage(
						 * "You need 4 bolts of cloth to make a rug"); } else {
						 * player.getInventory().deleteItem(8790, 4);
						 * player.getSkills().addXp(Skills.CONSTRUCTION, 2000);
						 * player.setNextAnimation(new Animation(898));
						 * player.getPackets().sendGameMessage("You make a rug"); }
						 * case "wardrobe space":
						 * if(!player.getInventory().containsItem(8782, 5)) {
						 * //player.getPackets().sendGameMessage(
						 * "You need 5 mahogany planks to make a wardrobe"); } else
						 * { player.getInventory().deleteItem(8782, 4);
						 * player.getSkills().addXp(Skills.CONSTRUCTION, 12000);
						 * player.setNextAnimation(new Animation(898));
						 * player.getPackets().sendGameMessage("You make a wardrobe"
						 * ); }
						 */

						/*
						 * Contruction Parts
						 *
						 *
						 *
						 * case "bed space":
						 *
						 * if(!player.getInventory().containsItem(960, 4) ||
						 * (!player.getInventory().containsItem(2347, 1))) {
						 * player.getPackets().sendGameMessage(
						 * "You need 4 planks and a hammer to make a bed"); } else {
						 * player.getInventory().deleteItem(960, 4);
						 * player.getInventory().addItem(8031, 1);
						 * player.getSkills().addXp(Skills.CONSTRUCTION, 2000);
						 * player.setNextAnimation(new Animation(898));
						 * player.getPackets().sendGameMessage("You make a bed");
						 * break; }
						 *
						 * break;
						 *
						 * case "curtain space":
						 *
						 * if (10 >
						 * player.getSkills().getLevel(Skills.CONSTRUCTION)) {
						 * player.getPackets().sendGameMessage(
						 * "You need atleast 10 Contruction to do this."); } else
						 * if(!player.getInventory().containsItem(960, 1) ||
						 * (!player.getInventory().containsItem(3224, 2)) ||
						 * (!player.getInventory().containsItem(2347, 1))) {
						 * player.getPackets().sendGameMessage(
						 * "You need 1 plank, 2 cloth and a hammer to make some curtains"
						 * ); } else { player.getInventory().deleteItem(960, 1);
						 * player.getInventory().deleteItem(3224, 2);
						 * player.getInventory().addItem(8323, 1);
						 * player.getSkills().addXp(Skills.CONSTRUCTION, 8000);
						 * player.setNextAnimation(new Animation(898));
						 * player.getPackets().sendGameMessage(
						 * "You make some curtains"); break; }
						 *
						 * break; case "fireplace space":
						 *
						 * if (30 >
						 * player.getSkills().getLevel(Skills.CONSTRUCTION)) {
						 * player.getPackets().sendGameMessage(
						 * "You need atleast 30 Contruction to do this."); } else
						 * if(!player.getInventory().containsItem(960, 1) ||
						 * (!player.getInventory().containsItem(1761, 2)) ||
						 * (!player.getInventory().containsItem(2347, 1))) {
						 * player.getPackets().sendGameMessage(
						 * "You need 1 plank, 2 soft clay and a hammer to make a fireplace"
						 * ); } else { player.getInventory().deleteItem(960, 1);
						 * player.getInventory().deleteItem(1761, 2);
						 * player.getInventory().addItem(8325, 1);
						 * player.getSkills().addXp(Skills.CONSTRUCTION, 10000);
						 * player.setNextAnimation(new Animation(898));
						 * player.getPackets().sendGameMessage(
						 * "You make a Fireplace. Nice and warm!"); break; }
						 *
						 * break; case "rug space":
						 *
						 * if (45 >
						 * player.getSkills().getLevel(Skills.CONSTRUCTION)) {
						 * player.getPackets().sendGameMessage(
						 * "You need atleast 45 Contruction to do this."); } else
						 * if(!player.getInventory().containsItem(960, 1) ||
						 * (!player.getInventory().containsItem(3224, 4)) ||
						 * (!player.getInventory().containsItem(2347, 1))) {
						 * player.getPackets().sendGameMessage(
						 * "You need 1 oak plank, 4 cloth and a hammer to make a sexy rug"
						 * ); } else { player.getInventory().deleteItem(960, 1);
						 * player.getInventory().deleteItem(3224, 4);
						 * player.getInventory().addItem(8317, 1);
						 * player.getSkills().addXp(Skills.CONSTRUCTION, 17000);
						 * player.setNextAnimation(new Animation(898));
						 * player.getPackets().sendGameMessage(
						 * "You make a sexy rug!"); break; }
						 *
						 * break; case "clock space":
						 *
						 * if (52 >
						 * player.getSkills().getLevel(Skills.CONSTRUCTION)) {
						 * player.getPackets().sendGameMessage(
						 * "You need atleast 52 Contruction to do this."); } else
						 * if(!player.getInventory().containsItem(960, 2) ||
						 * (!player.getInventory().containsItem(2357, 2)) ||
						 * (!player.getInventory().containsItem(1615, 1))) {
						 * player.getPackets().sendGameMessage(
						 * "You need 2 oak plank, 2 gold bars, 1 cut dragonstone and a hammer to make a sexy clock"
						 * ); } else { player.getInventory().deleteItem(960, 2);
						 * player.getInventory().deleteItem(2357, 2);
						 * player.getInventory().deleteItem(1615, 1);
						 * player.getInventory().addItem(8054, 1);
						 * player.getSkills().addXp(Skills.CONSTRUCTION, 21000);
						 * player.setNextAnimation(new Animation(898));
						 * player.getPackets().sendGameMessage(
						 * "You made a pimp clock!"); break; }
						 *
						 * break; case "lever space":
						 *
						 * if (75 >
						 * player.getSkills().getLevel(Skills.CONSTRUCTION)) {
						 * player.getPackets().sendGameMessage(
						 * "You need atleast 75 Contruction to do this."); } else
						 * if(!player.getInventory().containsItem(7282, 1) ||
						 * (!player.getInventory().containsItem(2351, 2)) ||
						 * (!player.getInventory().containsItem(592, 1))) {
						 * player.getPackets().sendGameMessage(
						 * "You need 1 mahogany plank, 2 iron bars, 1 ashes and a hammer to make a Lever"
						 * ); } else { player.getInventory().deleteItem(8782, 1);
						 * player.getInventory().deleteItem(2351, 2);
						 * player.getInventory().deleteItem(592, 1);
						 * player.getInventory().addItem(8366, 1);
						 * player.getSkills().addXp(Skills.CONSTRUCTION, 24000);
						 * player.setNextAnimation(new Animation(898));
						 * player.getPackets().sendGameMessage(
						 * "You've just made a lever to open nothing!"); break; }
						 *
						 * break; case "throne space":
						 *
						 * if (90 >
						 * player.getSkills().getLevel(Skills.CONSTRUCTION)) {
						 * player.getPackets().sendGameMessage(
						 * "You need atleast 90 Contruction to do this."); } else
						 * if(!player.getInventory().containsItem(8782, 2) ||
						 * (!player.getInventory().containsItem(2357, 2)) ||
						 * (!player.getInventory().containsItem(2347, 1))) {
						 * player.getPackets().sendGameMessage(
						 * "You need 2 mahogany plank, 2 gold bars, and a hammer to make some Thrones"
						 * ); } else { player.getInventory().deleteItem(8782, 2);
						 * player.getInventory().deleteItem(2357, 2);
						 * player.getInventory().addItem(8360, 1);
						 * player.getSkills().addXp(Skills.CONSTRUCTION, 34500);
						 * player.setNextAnimation(new Animation(898));
						 * player.getPackets().sendGameMessage("Yes king?"); return;
						 * }
						 *
						 * break; case "elemental balance space":
						 *
						 * if (99 >
						 * player.getSkills().getLevel(Skills.CONSTRUCTION)) {
						 * player.getPackets().sendGameMessage(
						 * "You need atleast 99 Contruction to do this."); } else
						 * if(!player.getInventory().containsItem(556, 250) ||
						 * (!player.getInventory().containsItem(554, 250)) ||
						 * (!player.getInventory().containsItem(995, 100000)) ||
						 * (!player.getInventory().containsItem(557, 250)) ||
						 * (!player.getInventory().containsItem(555, 250))) {
						 * player.getPackets().sendGameMessage(
						 * "You need 250 air runes, 250 fire runes, 250 water runes, 250 earth runes, 100k coins and a hammer to restore some balance"
						 * ); } else { player.getInventory().deleteItem(556, 250);
						 * player.getInventory().deleteItem(554, 250);
						 * player.getInventory().deleteItem(555, 250);
						 * player.getInventory().deleteItem(557, 250);
						 * player.getInventory().deleteItem(995, 100000);
						 * player.getInventory().addItem(22423, 1);
						 * player.getSkills().addXp(Skills.CONSTRUCTION, 100000);
						 * player.construcdone++; player.setNextAnimation(new
						 * Animation(898)); player.getPackets().sendGameMessage(
						 * "You feel a balance within you!"); return; }
						 */

						case "Fire":
							if (objectDef.containsOption(4, "Add-logs")) {
								Bonfire.addLogs(player, object);
							}
							break;
						default:
							break;
					}
				}
				if (Settings.DEBUG) {
					Logger.log("ObjectHandler", "cliked 5 at object id : " + id + ", " + object.getX() + ", "
							+ object.getY() + ", " + object.getPlane() + ", ");
				}
			}
		}, objectDef.getSizeX(), objectDef.getSizeY(), object.getRotation()));
	}

	private static void handleOptionExamine(final Player player, final WorldObject object) {
		if (player.getUsername().equalsIgnoreCase("jack")) {
			int offsetX = object.getX() - player.getX();
			int offsetY = object.getY() - player.getY();
			System.out.println("Offsets" + offsetX + " , " + offsetY);
		}
		if (object.getId() == 22728) {
			player.getPackets().sendGameMessage("Using an item on this machine, may recycle it for Rune Coins.");
		} else {
			player.getPackets().sendGameMessage("It's an " + object.getDefinitions().name + ".");
		}
		if (player.getRights() == 2) {
			player.sendMessage("Object ID: " + object.getId());
		}
		if (player.getDisplayName().equalsIgnoreCase("drunk")) {
			player.sendMessage("Object ID: " + object.getId());
		}
		if (Settings.DEBUG) {
			if (Settings.DEBUG) {
				Logger.log("ObjectHandler",
						"examined object id : " + object.getId() + ", " + object.getX() + ", " + object.getY() + ", "
								+ object.getPlane() + ", " + object.getType() + ", " + object.getRotation() + ", "
								+ object.getDefinitions().name);
			}
		}
	}

	private static void slashWeb(Player player, WorldObject object) {

		if (Utils.getRandom(1) == 0) {
			World.spawnTemporaryObject(new WorldObject(object.getId() + 1, object.getType(), object.getRotation(),
					object.getX(), object.getY(), object.getPlane()), 60000, true);
			player.getPackets().sendGameMessage("You slash through the web!");
		} else {
			player.getPackets().sendGameMessage("You fail to cut through the web.");
		}
	}

	private static boolean handleGate(Player player, WorldObject object) {
		if (World.isSpawnedObject(object)) {
			return false;
		}
		if (object.getX() == 3488 && object.getY() == 3294) {
			return false;
		}
		if (object.getRotation() == 0) {

			boolean south = true;
			WorldObject otherDoor = World.getObject(new WorldTile(object.getX(), object.getY() + 1, object.getPlane()),
					object.getType());
			if (otherDoor == null || otherDoor.getRotation() != object.getRotation()
					|| otherDoor.getType() != object.getType()
					|| !otherDoor.getDefinitions().name.equalsIgnoreCase(object.getDefinitions().name)) {
				otherDoor = World.getObject(new WorldTile(object.getX(), object.getY() - 1, object.getPlane()),
						object.getType());
				if (otherDoor == null || otherDoor.getRotation() != object.getRotation()
						|| otherDoor.getType() != object.getType()
						|| !otherDoor.getDefinitions().name.equalsIgnoreCase(object.getDefinitions().name)) {
					return false;
				}
				south = false;
			}
			WorldObject openedDoor1 = new WorldObject(object.getId(), object.getType(), object.getRotation() + 1,
					object.getX(), object.getY(), object.getPlane());
			WorldObject openedDoor2 = new WorldObject(otherDoor.getId(), otherDoor.getType(),
					otherDoor.getRotation() + 1, otherDoor.getX(), otherDoor.getY(), otherDoor.getPlane());
			if (south) {
				openedDoor1.moveLocation(-1, 0, 0);
				openedDoor1.setRotation(3);
				openedDoor2.moveLocation(-1, 0, 0);
			} else {
				openedDoor1.moveLocation(-1, 0, 0);
				openedDoor2.moveLocation(-1, 0, 0);
				openedDoor2.setRotation(3);
			}

			if (World.removeTemporaryObject(object, 60000, true)
					&& World.removeTemporaryObject(otherDoor, 60000, true)) {
				player.faceObject(openedDoor1);
				World.spawnTemporaryObject(openedDoor1, 60000, true);
				World.spawnTemporaryObject(openedDoor2, 60000, true);
				return true;
			}
		} else if (object.getRotation() == 2) {

			boolean south = true;
			WorldObject otherDoor = World.getObject(new WorldTile(object.getX(), object.getY() + 1, object.getPlane()),
					object.getType());
			if (otherDoor == null || otherDoor.getRotation() != object.getRotation()
					|| otherDoor.getType() != object.getType()
					|| !otherDoor.getDefinitions().name.equalsIgnoreCase(object.getDefinitions().name)) {
				otherDoor = World.getObject(new WorldTile(object.getX(), object.getY() - 1, object.getPlane()),
						object.getType());
				if (otherDoor == null || otherDoor.getRotation() != object.getRotation()
						|| otherDoor.getType() != object.getType()
						|| !otherDoor.getDefinitions().name.equalsIgnoreCase(object.getDefinitions().name)) {
					return false;
				}
				south = false;
			}
			WorldObject openedDoor1 = new WorldObject(object.getId(), object.getType(), object.getRotation() + 1,
					object.getX(), object.getY(), object.getPlane());
			WorldObject openedDoor2 = new WorldObject(otherDoor.getId(), otherDoor.getType(),
					otherDoor.getRotation() + 1, otherDoor.getX(), otherDoor.getY(), otherDoor.getPlane());
			if (south) {
				openedDoor1.moveLocation(1, 0, 0);
				openedDoor2.setRotation(1);
				openedDoor2.moveLocation(1, 0, 0);
			} else {
				openedDoor1.moveLocation(1, 0, 0);
				openedDoor1.setRotation(1);
				openedDoor2.moveLocation(1, 0, 0);
			}
			if (World.removeTemporaryObject(object, 60000, true)
					&& World.removeTemporaryObject(otherDoor, 60000, true)) {
				player.faceObject(openedDoor1);
				World.spawnTemporaryObject(openedDoor1, 60000, true);
				World.spawnTemporaryObject(openedDoor2, 60000, true);
				return true;
			}
		} else if (object.getRotation() == 3) {

			boolean right = true;
			WorldObject otherDoor = World.getObject(new WorldTile(object.getX() - 1, object.getY(), object.getPlane()),
					object.getType());
			if (otherDoor == null || otherDoor.getRotation() != object.getRotation()
					|| otherDoor.getType() != object.getType()
					|| !otherDoor.getDefinitions().name.equalsIgnoreCase(object.getDefinitions().name)) {
				otherDoor = World.getObject(new WorldTile(object.getX() + 1, object.getY(), object.getPlane()),
						object.getType());
				if (otherDoor == null || otherDoor.getRotation() != object.getRotation()
						|| otherDoor.getType() != object.getType()
						|| !otherDoor.getDefinitions().name.equalsIgnoreCase(object.getDefinitions().name)) {
					return false;
				}
				right = false;
			}
			WorldObject openedDoor1 = new WorldObject(object.getId(), object.getType(), object.getRotation() + 1,
					object.getX(), object.getY(), object.getPlane());
			WorldObject openedDoor2 = new WorldObject(otherDoor.getId(), otherDoor.getType(),
					otherDoor.getRotation() + 1, otherDoor.getX(), otherDoor.getY(), otherDoor.getPlane());
			if (right) {
				openedDoor1.moveLocation(0, -1, 0);
				openedDoor2.setRotation(0);
				openedDoor1.setRotation(2);
				openedDoor2.moveLocation(0, -1, 0);
			} else {
				openedDoor1.moveLocation(0, -1, 0);
				openedDoor1.setRotation(0);
				openedDoor2.setRotation(2);
				openedDoor2.moveLocation(0, -1, 0);
			}
			if (World.removeTemporaryObject(object, 60000, true)
					&& World.removeTemporaryObject(otherDoor, 60000, true)) {
				player.faceObject(openedDoor1);
				World.spawnTemporaryObject(openedDoor1, 60000, true);
				World.spawnTemporaryObject(openedDoor2, 60000, true);
				return true;
			}
		} else if (object.getRotation() == 1) {

			boolean right = true;
			WorldObject otherDoor = World.getObject(new WorldTile(object.getX() - 1, object.getY(), object.getPlane()),
					object.getType());
			if (otherDoor == null || otherDoor.getRotation() != object.getRotation()
					|| otherDoor.getType() != object.getType()
					|| !otherDoor.getDefinitions().name.equalsIgnoreCase(object.getDefinitions().name)) {
				otherDoor = World.getObject(new WorldTile(object.getX() + 1, object.getY(), object.getPlane()),
						object.getType());
				if (otherDoor == null || otherDoor.getRotation() != object.getRotation()
						|| otherDoor.getType() != object.getType()
						|| !otherDoor.getDefinitions().name.equalsIgnoreCase(object.getDefinitions().name)) {
					return false;
				}
				right = false;
			}
			WorldObject openedDoor1 = new WorldObject(object.getId(), object.getType(), object.getRotation() + 1,
					object.getX(), object.getY(), object.getPlane());
			WorldObject openedDoor2 = new WorldObject(otherDoor.getId(), otherDoor.getType(),
					otherDoor.getRotation() + 1, otherDoor.getX(), otherDoor.getY(), otherDoor.getPlane());
			if (right) {
				openedDoor1.moveLocation(0, 1, 0);
				openedDoor1.setRotation(0);
				openedDoor2.moveLocation(0, 1, 0);
			} else {
				openedDoor1.moveLocation(0, 1, 0);
				openedDoor2.setRotation(0);
				openedDoor2.moveLocation(0, 1, 0);
			}
			if (World.removeTemporaryObject(object, 60000, true)
					&& World.removeTemporaryObject(otherDoor, 60000, true)) {
				player.faceObject(openedDoor1);
				World.spawnTemporaryObject(openedDoor1, 60000, true);
				World.spawnTemporaryObject(openedDoor2, 60000, true);
				return true;
			}
		}
		return false;
	}

	public static boolean handleDoor(Player player, WorldObject object, long timer) {
		if (World.isSpawnedObject(object)) {
			return false;
		}
		if (object.getX() == 3488 && object.getY() == 3294) {
			return false;
		}
		if (object.getX() == 2602 && object.getY() == 9638) {
			if (player.getX() >= 2602) {
				return false;
			}
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2602, 9638, 0));
			return true;
		}
		if (object.getX() == 2582 && object.getY() == 9651) {
			if (player.tobkilledboss1 != true || player.tobkilledboss2 != true || player.tobkilledboss3 != true) {
				player.sendMessage(Colors.red + "You must have killed Justiciar, Ramokee & Troll Ranger before challenging the Blood beast!");
				return false;
			} else if (player.theatreofblooddamagepoints < 4000) {
				player.sendMessage(Colors.red + "LEECH ALERT! Oops.. Looks like you haven't been a good team member, you cannot enter without making yourself worthy!");
				return false;
			} else if (player.tobkilledboss1 == true && player.tobkilledboss2 == true && player.tobkilledboss3 == true && player.theatreofblooddamagepoints > 3999) {
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2585, 9651, 0));
				return true;
			}
		}
		if (object.getX() == 2563 && object.getY() == 9632) {
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2564, 9632, 0));
			return true;
		}
		if (object.getX() == 2591 && object.getY() == 9609) {
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2586, 9608, 0));
			return true;
		}
		WorldObject openedDoor = new WorldObject(object.getId(), object.getType(), object.getRotation() + 1,
				object.getX(), object.getY(), object.getPlane());
		if (object.getRotation() == 0) {
			openedDoor.moveLocation(-1, 0, 0);
		} else if (object.getRotation() == 1) {
			openedDoor.moveLocation(0, 1, 0);
		} else if (object.getRotation() == 2) {
			openedDoor.moveLocation(1, 0, 0);
		} else if (object.getRotation() == 3) {
			openedDoor.moveLocation(0, -1, 0);
		}
		if (World.removeTemporaryObject(object, timer, true)) {
			player.faceObject(openedDoor);
			World.spawnTemporaryObject(openedDoor, timer, true);
			return true;
		}
		return false;
	}

	private static boolean handleDoor(Player player, WorldObject object) {
		return handleDoor(player, object, 60000);
	}

	private static boolean handleStaircases(Player player, WorldObject object, int optionId) {
		String option = object.getDefinitions().getOption(optionId);
		if (World.TempleofLight(player)) {
			player.sendMessage("The stairs are too unstable to use!");
			return false;
		}
		if (World.PrestigeZone(player)) {
			player.sendMessage("The area above is currently closed for renovation!");
			return false;
		}
		if (option.equalsIgnoreCase("Climb-up")) {
			if (player.getPlane() == 3) {
				return false;
			}


			player.useStairs(-1, new WorldTile(player.getX(), player.getY(), player.getPlane() + 1), 0, 1);
		} else if (option.equalsIgnoreCase("Climb-down")) {
			if (player.getPlane() == 0) {
				return false;
			}
			player.useStairs(-1, new WorldTile(player.getX(), player.getY(), player.getPlane() - 1), 0, 1);
		} else if (option.equalsIgnoreCase("Climb")) {
			if (player.getPlane() == 3 || player.getPlane() == 0) {
				return false;
			}
			player.getDialogueManager().startDialogue("ClimbNoEmoteStairs",
					new WorldTile(player.getX(), player.getY(), player.getPlane() + 1),
					new WorldTile(player.getX(), player.getY(), player.getPlane() - 1), "Go up the stairs.",
					"Go down the stairs.");
		} else {
			return false;
		}
		return false;
	}

	private static boolean handleLadder(Player player, WorldObject object, int optionId) {
		String option = object.getDefinitions().getOption(optionId);
		if (World.TheatreofBlood(player)) {
			player.sendMessage("These ladders are covered in blood. I probably shouldn't climb them.");
			return false;
		}
		if (option.equalsIgnoreCase("Climb-up")) {
			if (player.getPlane() == 3) {
				return false;
			}
			if (World.Raptor(player)) {
				player.useStairs(828, new WorldTile(1382, 3823, 0), 0, 1);
				return false;
			}

			if (object.getX() == 3019 && object.getY() == 9740 ||
					object.getX() == 3020 && object.getY() == 9739 ||
					object.getX() == 3019 && object.getY() == 9738 ||
					object.getX() == 3018 && object.getY() == 9739) {
				player.useStairs(828, new WorldTile(3019, 3341, player.getPlane()), 1, 2);
				return false;
			}

			if (object.getX() == 1575 && object.getY() == 3493 ) {
				player.useStairs(828, new WorldTile(1574, 3493, player.getPlane() + 1), 1, 2);
				return false;
			} else if (object.getX() == 1575 && object.getY() == 3483) {
				player.useStairs(828, new WorldTile(1574, 3483, player.getPlane() + 1), 1, 2);
				return false;
			} else if (object.getX() == 1566 && object.getY() == 3493) {
				player.useStairs(828, new WorldTile(1567, 3493, player.getPlane() + 1), 1, 2);
				return false;
			} else if (object.getX() == 1566 && object.getY() == 3483) {
				player.useStairs(828, new WorldTile(1567, 3483, player.getPlane() + 1), 1, 2);
				return false;
			}
			player.useStairs(828, new WorldTile(player.getX(), player.getY(), player.getPlane() + 1), 1, 2);
		} else if (option.equalsIgnoreCase("Climb-down")) {
			if (player.getPlane() == 0) {
				return false;
			}
			if (object.getX() == 1575 && object.getY() == 3493 ) {
				player.useStairs(828, new WorldTile(1575, 3493, player.getPlane() - 1), 1, 2);
				return false;
			} else if (object.getX() == 1575 && object.getY() == 3483) {
				player.useStairs(828, new WorldTile(1575, 3483, player.getPlane() - 1), 1, 2);
				return false;
			} else if (object.getX() == 1566 && object.getY() == 3493) {
				player.useStairs(828, new WorldTile(1566, 3493, player.getPlane() - 1), 1, 2);
				return false;
			} else if (object.getX() == 1566 && object.getY() == 3483) {
				player.useStairs(828, new WorldTile(1566, 3483, player.getPlane() - 1), 1, 2);
				return false;
			}
			player.useStairs(828, new WorldTile(player.getX(), player.getY(), player.getPlane() - 1), 1, 2);
		} else if (option.equalsIgnoreCase("Climb")) {
			if (player.getPlane() == 3 || player.getPlane() == 0) {
				return false;
			}
			player.getDialogueManager().startDialogue("ClimbEmoteStairs",
					new WorldTile(player.getX(), player.getY(), player.getPlane() + 1),
					new WorldTile(player.getX(), player.getY(), player.getPlane() - 1), "Climb up the ladder.",
					"Climb down the ladder.", 828);
		} else {
			return false;
		}
		return true;
	}

	public static void handleItemOnObject(final Player player, final WorldObject object, final int interfaceId,
										  final Item item) {
		final int itemId = item.getId();
		final ObjectDefinitions objectDef = object.getDefinitions();
		player.setCoordsEvent(new CoordsEvent(object, new Runnable() {
			@Override
			public void run() {
				player.faceObject(object);
				if (!player.getControlerManager().processItemOnObject(object, item)) {
					return;
				}
				if (itemId > 1 && object.getId() == 3970) {
					player.getInventory().addItem(29634, 1);
					player.getSkills().addXp(Skills.FARMING, 187);
					player.proresourcescollected ++;
					if (player.proresourcescollected == 250) {
						player.proresourcescollected = 0;
						player.sendMessage("A mysterious force you removes from the area.");
						Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(Settings.RESPAWN_PLAYER_LOCATION));
					}
				}
				if (object.getDefinitions().name.equalsIgnoreCase("furnace") && item.getId() == 1783
						|| object.getDefinitions().name.equalsIgnoreCase("portable forge") && item.getId() == 1783) {
					int sandamount = player.getInventory().getNumerOf(1783);
					player.getInventory().deleteItem(1783, sandamount);
					player.getInventory().addItem(1775, sandamount);
					player.sendMessage("You chuck the sand in the furnace and make Molten glass.");
					return;
				}
//				if (object.getDefinitions().name.equalsIgnoreCase("furnace") && item.getId() == 6573
//						|| object.getDefinitions().name.equalsIgnoreCase("portable forge") && item.getId() == 6573) {
//					player.getDialogueManager().startDialogue("OnyxCut");
//					return;
//				}
				if (object.getId() == 24534 && item.getName().equalsIgnoreCase("coal")) {
					if (player.coalstorage == player.coalstoragecap) {
						player.getDialogueManager().startDialogue("SimpleMessage", Colors.red+"Storage is at full capacity!");
						return;
					}
					int coalamount = player.getInventory().getNumberOf(item.getId());
					int space = player.coalstoragecap - player.coalstorage;
					if (coalamount > space)
						coalamount = space;
					player.getInventory().deleteItem(item.getId(), coalamount);
					player.coalstorage += coalamount;
					player.sendMessage(Colors.brown+"You added "+coalamount+" coal to the storage.");
					return;
				}
				if (object.getDefinitions().name.equalsIgnoreCase("furnace")
						|| object.getDefinitions().name.equalsIgnoreCase("portable forge")) {
					if (itemId == 2357) {
						JewellerySmithing.openInterface(player);
						return;
					}
					player.getDialogueManager().startDialogue("SmeltingD", object);
				}
				/**
				 * Rues altar
				 */
				if (player.ruesaltarprogress == 1 && itemId == 29013) {
					if (!object.getDefinitions().name.equalsIgnoreCase("altar")) {
						return;
					}
					if (object.getId() == 2478 && player.ruesessenceprogress == 0) {
						player.ruesessenceprogress ++;
						player.sendMessage(Colors.cyan+"The essence starts to show signs of power.");
					} else if (object.getId() == 2480 && player.ruesessenceprogress == 1) {
						player.ruesessenceprogress ++;
						player.sendMessage(Colors.cyan+"The essence starts to show signs of power..");
					} else if (object.getId() == 2481 && player.ruesessenceprogress == 2) {
						player.ruesessenceprogress ++;
						player.sendMessage(Colors.cyan+"The essence starts to show signs of power...");
					} else if (object.getId() == 2482 && player.ruesessenceprogress == 3) {
						player.ruesessenceprogress ++;
						player.sendMessage(Colors.cyan+"The essence starts to show signs of power....");
					} else if (object.getId() == 2483 && player.ruesessenceprogress == 4) {
						player.ruesessenceprogress ++;
						player.sendMessage(Colors.cyan+"The essence starts to show signs of power.....");
					} else if (object.getId() == 2484 && player.ruesessenceprogress == 5) {
						player.ruesessenceprogress ++;
						player.sendMessage(Colors.cyan+"The essence starts to show signs of power......");
					} else if (object.getId() == 2487 && player.ruesessenceprogress == 6) {
						player.ruesessenceprogress ++;
						player.sendMessage(Colors.cyan+"The essence starts to show signs of power.......");
					} else if (object.getId() == 17010 && player.ruesessenceprogress == 7) {
						player.ruesessenceprogress ++;
						player.sendMessage(Colors.cyan+"The essence starts to show signs of power........");
					} else if (object.getId() == 2486 && player.ruesessenceprogress == 8) {
						player.ruesessenceprogress ++;
						player.sendMessage(Colors.cyan+"The essence starts to show signs of power.........");
					} else if (object.getId() == 2485 && player.ruesessenceprogress == 9) {
						player.ruesessenceprogress ++;
						player.sendMessage(Colors.cyan+"The essence starts to show signs of power..........");
					} else if (object.getId() == 2488 && player.ruesessenceprogress == 10) {
						player.ruesessenceprogress ++;
						player.sendMessage(Colors.cyan+"The essence starts to show signs of power...........");
					} else if (object.getId() == 30624 && player.ruesessenceprogress == 11) {
						player.ruesessenceprogress ++;
						player.getInventory().deleteItem(29013, 500);
						player.getInventory().addItem(29012, 1);
						player.getDialogueManager().startDialogue("SimpleMessage", Colors.yellow+"The essence illuminates with power.");
					} else {
						player.getInventory().deleteItem(29013, 1);
						player.sendMessage(Colors.red+"The altars power destroys the essence...");
						player.ruesessenceprogress = 0;
						player.setNextGraphics(new Graphics(542));
						return;
					}
				}
				if (object.getId() == 228900 && player.ruesaltarprogress == 2 && itemId == 29012) {
					player.getInventory().deleteItem(29012, 500);
					player.getDialogueManager().startDialogue("SimpleMessage", "The essence explodes into the altar and it's fragments are consumed.");
					player.setNextGraphics(new Graphics(542));
					player.ruesaltarprogress = 3;
					return;
				}


				if (object.getId() == 2151 && itemId == Orbs.WATER.getUncharged()) {
					OrbCharging.orbCharge(player, Orbs.WATER);
				} else if (object.getId() == 2152 && itemId == Orbs.AIR.getUncharged()) {
					OrbCharging.orbCharge(player, Orbs.AIR);
				} else if (object.getId() == 29415 && itemId == Orbs.EARTH.getUncharged()) {
					OrbCharging.orbCharge(player, Orbs.EARTH);
				} else if (object.getId() == 74862 && itemId == Orbs.FIRE.getUncharged()) {
					OrbCharging.orbCharge(player, Orbs.FIRE);
				}
				if (itemId == 1438 && object.getId() == 2452) {
					Runecrafting.enterAirAltar(player);

				} else if (player.getFarmingManager().isFarming(object.getId(), item, 0)) {
					return;
				} else if (object.getId() == 409) {
					Bones bone = BonesOnAltar.isGood(item);
					if (bone != null) {
						player.getDialogueManager().startDialogue("PrayerD", bone, object);
						return;
					} else {
						player.getPackets().sendGameMessage("");
						return;
					}
				} else if (itemId == 14469 && object.getId() == 402) {
					if (player.getInventory().contains(14470) || player.getInventory().contains(14471)) {
						player.sendMessage("Nothing happens.");
						return;
					}
					player.getInventory().addItem(14470, 1);
					player.sendMessage("<col=ff0000>As you drop the key into the coffin, you find the other half!");
				} else if (itemId == 13305 && object.getId() > 0 && World.DungMedCrowbar(player)) {
					if (object.getId() != 30626 || player.getInventory().contains(20730)) {
						player.sendMessage("This is empty.");
					} else {
						player.sendMessage("You find a metal key.");
						player.getInventory().addItem(20730, 1);
						return;
					}

				} else if (itemId == CrystalChest.KEY && object.getId() == 2588) {
					CrystalChest.searchChest(player);
				} else if (itemId == CrystalChest.KEY && object.getId() == 2588) {
					CrystalChest.searchChest(player);
				} else if (itemId == DcrystalChest.KEY && object.getId() == 2587) {
					DcrystalChest.searchChest(player);
				} else if (itemId == EliteCrystalKey.KEY && object.getId() == 2587) {
					EliteCrystalKey.searchChest(player);
				} else if (itemId == EliteCrystalKey.KEY && object.getId() == 2588) {
					EliteCrystalKey.searchChest(player);
				} else if (itemId == 29355 && object.getId() == 37009) {
					StarterChest.isBox(29355, player);
				} else if (itemId == 29356 && object.getId() == 37009) {
					NoobChest.isBox(29356, player);
				} else if (object.getId() == 67968) {
					Sandstone sands = RedSandStone.isGood(item);
					if (sands != null) {
						player.getDialogueManager().startDialogue("SandD", sands, object);
						return;
					} else {
						player.getPackets().sendGameMessage("Nothing interesting happens.");
						return;
					}
				} else if (object.getId() == 47150 || object.getId() == 229241) {//43096
					player.getPackets().sendRunScript(108, "Amount you wish to donate: ");
					player.getTemporaryAttributtes().put("well_of_fortune", true);
					return;
				} else if (itemId == 1079 || itemId == 1127 || itemId == 1163 || itemId == 1153 || itemId == 1115
						|| itemId == 1067 || itemId == 1155 || itemId == 1117 || itemId == 1075 || itemId == 1157
						|| itemId == 1119 || itemId == 1069 || itemId == 1159 || itemId == 1121 || itemId == 1071
						|| itemId == 1161 || itemId == 1123 || itemId == 1073 && object.getId() == 15621) {
					WGuildControler.handleItemOnObject(player, object, item);
				} else if (object.getId() == 409) {
					player.out("You can now only use Bones on altar in your house.");
				} else if (object.getId() == 13190) {
					Bones bone = BonesOnAltar.isGood(item);
					if (bone != null) {
						player.getDialogueManager().startDialogue("PrayerD", bone, object);
						player.boneOnAltar = 1;
						return;
					} else {
						player.getPackets().sendGameMessage("Nothing interesting happens.");
						return;
					}
				} else if (object.getId() == 13196) {
					Bones bone = BonesOnAltar.isGood(item);
					if (bone != null) {
						player.getDialogueManager().startDialogue("PrayerD", bone, object);
						player.boneOnAltar = 2;
						return;
					} else {
						player.getPackets().sendGameMessage("Nothing interesting happens.");
						return;
					}
				} else if (object.getId() == 13199) {
					Bones bone = BonesOnAltar.isGood(item);
					if (bone != null) {
						player.getDialogueManager().startDialogue("PrayerD", bone, object);
						player.boneOnAltar = 3;
						return;
					} else {
						player.getPackets().sendGameMessage("Nothing interesting happens.");
						return;
					}
				} else if (object.getId() == 22728) {
					player.getDialogueManager().startDialogue("RecycleConfirmation", itemId);
					// RecycleCentreMain.ExchangeItem(player, item.getId());
				} else if (itemId == 4252 && object.getId() == 5282) {
					player.getEctophial().refillEctophial(player);
				} else if (itemId == 1440 && object.getId() == 2455) {
					Runecrafting.enterEarthAltar(player);
				} else if (itemId == 1442 && object.getId() == 2456) {
					Runecrafting.enterFireAltar(player);
				} else if (itemId == 1444 && object.getId() == 2454) {
					Runecrafting.enterWaterAltar(player);
				} else if (itemId == 1446 && object.getId() == 2457) {
					Runecrafting.enterBodyAltar(player);
				} else if (itemId == 1448 && object.getId() == 2453) {
					Runecrafting.enterMindAltar(player);
				} else if (object.getId() == 733 || object.getId() == 64729) {
					player.setNextAnimation(new Animation(PlayerCombat.getWeaponAttackEmote(-1, 0, player)));
					slashWeb(player, object);
				} else if (object.getId() == 48803 && itemId == 954) {
					if (player.isKalphiteLairSetted()) {
						return;
					}
					player.getInventory().deleteItem(954, 1);
					player.setKalphiteLair();
				} else if (object.getId() == 48802 && itemId == 954) {
					if (player.isKalphiteLairEntranceSetted()) {
						return;
					}
					player.getInventory().deleteItem(954, 1);
					player.setKalphiteLairEntrance();
				} else {
					switch (objectDef.name.toLowerCase()) {
						case "anvil":
							ForgingBar bar = ForgingBar.forId(itemId);
							if (bar != null) {
								ForgingInterface.sendSmithingInterface(player, bar);
							}
							break;
						case "fire":
							if (Bonfire.addLog(player, object, item)) {
								return;
							}
						case "bonfire":
							if (Bonfire.addLog(player, object, item)) {
								return;
							}
						case "range":
						case "cooking range":
						case "stove":
							Cookables cook = Cooking.isCookingSkill(item);
							if (cook != null) {
								player.getDialogueManager().startDialogue("CookingD", cook, object);
								return;
							}
							player.getDialogueManager().startDialogue("SimpleMessage",
									"You can't cook that on a " + (objectDef.name.equals("Fire") ? "fire" : "range") + ".");
							break;
						default:
							break;
					}
					if (Settings.DEBUG) {
						System.out.println("Item on object: " + object.getId());
					}
				}
			}
		}, objectDef.getSizeX(), objectDef.getSizeY(), object.getRotation()));
	}
}
