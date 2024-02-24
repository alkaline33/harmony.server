package com.rs.game.player.actions;

import com.rs.Settings;
import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.Animation;
import com.rs.game.Graphics;
import com.rs.game.Hit;
import com.rs.game.Hit.HitLook;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.Woodcutting.TreeDefinitions;
import com.rs.game.player.content.DailyChallenges;
import com.rs.game.player.content.WildernessTasks;
import com.rs.game.player.controlers.Wilderness;
import com.rs.utils.Colors;
import com.rs.utils.Utils;

public final class Woodcutting extends Action {
	public enum HatchetDefinitions {

		NOVITE(16361, 1, 1, 13118),

		BATHUS(16363, 10, 4, 13119),

		MARMAROS(16365, 20, 5, 13120),

		KRATONITE(16367, 30, 7, 13121),

		FRACTITE(16369, 40, 10, 13122),

		ZEPHYRIUM(16371, 50, 12, 13123),

		ARGONITE(16373, 60, 13, 13124),

		KATAGON(16373, 70, 15, 13125),

		GORGONITE(16375, 80, 17, 13126),

		PROMETHIUM(16379, 90, 19, 13127),

		PRIMAL(16381, 99, 21, 13128),

		BRONZE(1351, 1, 1, 879),

		IRON(1349, 5, 2, 877),

		STEEL(1353, 5, 3, 875),

		BLACK(1361, 11, 4, 873),

		MITHRIL(1355, 21, 5, 871),

		ADAMANT(1357, 31, 7, 869),

		RUNE(1359, 41, 10, 867),

		DRAGON(6739, 61, 13, 2846),

		INFERNAL(29412, 61, 13, 2846),

		INFERNO(13661, 61, 13, 10251);

		private int itemId, levelRequried, axeTime, emoteId;

		private HatchetDefinitions(int itemId, int levelRequried, int axeTime, int emoteId) {
			this.itemId = itemId;
			this.levelRequried = levelRequried;
			this.axeTime = axeTime;
			this.emoteId = emoteId;
		}

		public int getItemId() {
			return itemId;
		}

		public int getLevelRequried() {
			return levelRequried;
		}

		public int getAxeTime() {
			return axeTime;
		}

		public int getEmoteId() {
			return emoteId;
		}
	}

	private static Player player;

	public static enum TreeDefinitions {

		NORMAL(1, 25, 1511, 6, 4, 1341, 8, 0), // TODO

		EVERGREEN(1, 25, 1511, 20, 4, 57931, 8, 0),

		DEAD(1, 25, 1511, 20, 4, 12733, 8, 0),

		OAK(15, 37.5, 1521, 8, 4, 1341, 15, 15), // TODO

		WILLOW(30, 67.5, 1519, 10, 4, 1341, 51, 15), // TODO
		
		TEAK(35, 85, 6333, 11, 5, 9037, 56, 10),

		MAPLE(45, 100, 1517, 12, 16, 31057, 72, 10),
		
		MAHOGANY(50, 125, 6332, 13, 16, 9035, 72, 10),

		YEW(60, 175, 1515, 15, 17, 1341, 94, 10), // TODO

		YEW_TREE(60, 200, 1515, 15, 17, 1341, 999999999, 5),

		MAGIC_TREE(75, 260, 1513, 25, 21, 37824, 999999999, 5),

		IVY(68, 332.5, -1, 120, 17, 46319, 58, 10),

		MAGIC(75, 250, 1513, 25, 21, 1341, 121, 10),

		CURSED_MAGIC(82, 250, 1513, 150, 21, 37822, 121, 10),

		SKILLBOSS_TREE(80, 12.5, -1, 10, 5, 2023, 1, 99999),

		REDWOOD_TREE(90, 380, 29138, 20, 21, 229671, 90, 11),

		CRYSTAL_TREE(94, 434.5, -1, 25, 21, 1341, 60, 15),

		DREAM_TREE(90, 328, 9067, 75, 19, 37822, 1, 99999),

		FRUIT_TREES(1, 25, -1, 20, 4, 1341, 8, 0),
		
		AFK_TREE(1, 67.5, -1, 25, 21, -1, 1, 99999);

		private int level;
		private double xp;
		private int logsId;
		private int logBaseTime;
		private int logRandomTime;
		private int stumpId;
		private int respawnDelay;
		private int randomLifeProbability;
		long logsCut = 0;

		private TreeDefinitions(int level, double xp, int logsId, int logBaseTime, int logRandomTime, int stumpId,
				int respawnDelay, int randomLifeProbability) {
			this.level = level;
			this.xp = xp;
			this.logsId = logsId;
			this.logBaseTime = logBaseTime;
			this.logRandomTime = logRandomTime;
			this.stumpId = stumpId;
			this.respawnDelay = respawnDelay;
			this.randomLifeProbability = randomLifeProbability;
		}

		public int getLevel() {
			return level;
		}

		public double getXp() {
			return xp;
		}

		public int getLogsId() {
			return logsId;
		}

		public int getLogBaseTime() {
			return logBaseTime;
		}

		public int getLogRandomTime() {
			return logRandomTime;
		}

		public int getStumpId() {
			return stumpId;
		}

		public int getRespawnDelay() {
			return respawnDelay;
		}

		public int getRandomLifeProbability() {
			return randomLifeProbability;
		}
	}

	private WorldObject tree;
	private TreeDefinitions definitions;

	private int emoteId;
	private int gfxId;
	private boolean usingBeaver = false;
	private int axeTime;

	public Woodcutting(WorldObject tree, TreeDefinitions definitions) {
		this.tree = tree;
		this.definitions = definitions;
	}

	@Override
	public boolean start(Player player) {
		if (!checkAll(player)) {
			return false;
		}
		player.getPackets().sendGameMessage(usingBeaver ? "Your beaver uses its strong teeth to chop down the tree..."
				: "You swing your hatchet at the " + (TreeDefinitions.IVY == definitions ? "ivy" : "tree") + "...",
				true);
		setActionDelay(player, getWoodcuttingDelay(player));
		return true;
	}

	private int getWoodcuttingDelay(Player player) {
		int summoningBonus = player.getFamiliar() != null
				? player.getFamiliar().getId() == 6808 || player.getFamiliar().getId() == 6807 ? 10 : 0 : 0;
		if (player.axeperk == true) {
			summoningBonus += 25;
		}
		int levelzone = World.Level3Zone(player) ? 10 : 0;
		int guild = World.WoodcuttingGuild(player) ? 7 : 0;
		
		int wcTimer = definitions.getLogBaseTime() - (player.getSkills().getLevel(8) + summoningBonus)
				- Utils.getRandom(axeTime) - levelzone - guild;
		if (wcTimer < 1 + definitions.getLogRandomTime()) {
			wcTimer = 1 + Utils.getRandom(definitions.getLogRandomTime());
		}
		wcTimer /= player.getAuraManager().getWoodcuttingAccurayMultiplier();
		return wcTimer;
	}

	private boolean checkAll(Player player) {
		/*if (World.isHomeArea(player)) {
			player.getPackets().sendGameMessage("I don't think Harmony would be happy if you started chopping down his trees.");
			return false;
		}*/
		if (!hasAxe(player)) {
			player.getPackets().sendGameMessage("You need a hatchet to chop down this tree.");
			return false;
		}
		if (!setAxe(player)) {
			player.getPackets().sendGameMessage("You dont have the required level to use that axe.");
			return false;
		}
		if (!hasWoodcuttingLevel(player)) {
			return false;
		}
		if (!player.getInventory().hasFreeSlots()) {
			player.getPackets().sendGameMessage("Not enough space in your inventory.");
			return false;
		}
		return true;
	}

	private boolean hasWoodcuttingLevel(Player player) {
		if (definitions.getLevel() > player.getSkills().getLevel(8)) {
			player.getPackets().sendGameMessage(
					"You need a woodcutting level of " + definitions.getLevel() + " to chop down this tree.");
			return false;
		}
		return true;
	}

	private boolean setAxe(Player player) {
		int level = player.getSkills().getLevel(8);
		int weaponId = player.getEquipment().getWeaponId();
		if (weaponId != -1) {
			switch (weaponId) {
			case 16381: // primal axe
				if (level >= 99) {
					emoteId = 13128;
					axeTime = 21;
				}
				break;
			case 29680: // dragon axe
				if (level >= 61 && player.wcskillfuanim == true) {
					emoteId = 17304;
					gfxId = 3301;
					axeTime = 15;
					return true;
				} else if (level >= 61 && player.wcanim == true) {
					emoteId = 17092;
					axeTime = 15;
					return true;
				} else if (level >= 61) {
					emoteId = 2846;
					axeTime = 15;
					return true;
				}
				break;
			case 6739: // dragon axe
			case 29412:
				if (level >= 61 && player.wcskillfuanim == true) {
					emoteId = 17304;
					gfxId = 3301;
					axeTime = 13;
					return true;
				} else if (level >= 61 && player.wcanim == true) {
					emoteId = 17092;
					axeTime = 13;
					return true;
				} else if (level >= 61) {
					emoteId = 2846;
					axeTime = 13;
					return true;
				}
				break;
			case 1359: // rune axe
				if (level >= 41 && player.wcskillfuanim == true) {
					emoteId = 17304;
					gfxId = 3301;
					axeTime = 10;
					return true;
				} else if (level >= 41 && player.wcanim == true) {
					emoteId = 17085;
					axeTime = 10;
					return true;
				} else if (level >= 41) {
					emoteId = 867;
					axeTime = 10;
					return true;
				}
				break;
			case 1357: // adam axe
				if (level >= 31 && player.wcskillfuanim == true) {
					emoteId = 17304;
					gfxId = 3301;
					axeTime = 7;
					return true;
				} else if (level >= 31) {
					emoteId = 869;
					axeTime = 7;
					return true;
				}
				break;
			case 1355: // mit axe
				if (level >= 21 && player.wcskillfuanim == true) {
					emoteId = 17304;
					gfxId = 3301;
					axeTime = 5;
					return true;
				} else if (level >= 21) {
					emoteId = 871;
					axeTime = 5;
					return true;
				}
				break;
			case 1361: // black axe
				if (level >= 11 && player.wcskillfuanim == true) {
					emoteId = 17304;
					gfxId = 3301;
					axeTime = 4;
					return true;
				} else if (level >= 11) {
					emoteId = 873;
					axeTime = 4;
					return true;
				}
				break;
			case 1353: // steel axe
				if (level >= 6 && player.wcskillfuanim == true) {
					emoteId = 17304;
					gfxId = 3301;
					axeTime = 3;
					return true;
				} else if (level >= 6) {
					emoteId = 875;
					axeTime = 3;
					return true;
				}
				break;
			case 1349: // iron axe
				if (player.wcskillfuanim == true) {
					emoteId = 17304;
					gfxId = 3301;
					axeTime = 2;
					return true;
				} else {
					emoteId = 877;
				}
				axeTime = 2;
				return true;
			case 1351: // bronze axe
				emoteId = 879;
				axeTime = 1;
				return true;
			case 13661: // Inferno adze
				if (level >= 61 && player.wcskillfuanim == true) {
					emoteId = 17304;
					gfxId = 3301;
					axeTime = 13;
					return true;
				} else if (level >= 61 && player.wcanim == true) {
					emoteId = 17093;
					axeTime = 13;
				} else if (level >= 61) {
					emoteId = 10251;
					axeTime = 13;
					return true;
				}
				break;
			}
		}
		if (player.getInventory().containsItemToolBelt(16381)) {
			if (level >= 99) {
				emoteId = 13128;
				axeTime = 21;
				return true;
			}
		}
		if (player.getInventory().containsItemToolBelt(29680)) {
			if (level >= 61 && player.wcskillfuanim == true) {
				emoteId = 17304;
				gfxId = 3301;
				axeTime = 15;
				return true;
			} else if (level >= 61 && player.wcanim == true) {
				emoteId = 17092;
				axeTime = 15;
				return true;
			} else if (level >= 61) {
				emoteId = 2846;
				axeTime = 15;
				return true;
			}
		}
		if (player.getInventory().containsItemToolBelt(29412)) {
			if (level >= 61 && player.wcskillfuanim == true) {
				emoteId = 17304;
				gfxId = 3301;
				axeTime = 13;
				return true;
			} else if (level >= 61 && player.wcanim == true) {
				emoteId = 17092;
				axeTime = 13;
				return true;
			} else if (level >= 61) {
				emoteId = 2846;
				axeTime = 13;
				return true;
			}
		}
		if (player.getInventory().containsItemToolBelt(6739)) {
			if (level >= 61 && player.wcskillfuanim == true) {
				emoteId = 17304;
				gfxId = 3301;
				axeTime = 13;
				return true;
			} else if (level >= 61 && player.wcanim == true) {
				emoteId = 17092;
				axeTime = 13;
				return true;
			} else if (level >= 61) {
				emoteId = 2846;
				axeTime = 13;
				return true;
			}
		}
		if (player.getInventory().containsItemToolBelt(1359)) {
			if (level >= 41 && player.wcskillfuanim == true) {
				emoteId = 17304;
				gfxId = 3301;
				axeTime = 10;
				return true;
			} else if (level >= 41 && player.wcanim == true) {
				emoteId = 17085;
				axeTime = 10;
				return true;
			} else if (level >= 41) {
				emoteId = 867;
				axeTime = 10;
				return true;
			}
		}
		if (player.getInventory().containsItemToolBelt(1357)) {
			if (level >= 31 && player.wcskillfuanim == true) {
				emoteId = 17304;
				gfxId = 3301;
				axeTime = 7;
				return true;
			} else if (level >= 31) {
				emoteId = 869;
				axeTime = 7;
				return true;
			}
		}
		if (player.getInventory().containsItemToolBelt(1355)) {
			if (level >= 21 && player.wcskillfuanim == true) {
				emoteId = 17304;
				gfxId = 3301;
				axeTime = 5;
				return true;
			} else if (level >= 21) {
				emoteId = 871;
				axeTime = 5;
				return true;
			}
		}
		if (player.getInventory().containsItemToolBelt(1361)) {
			if (level >= 11 && player.wcskillfuanim == true) {
				emoteId = 17304;
				gfxId = 3301;
				axeTime = 4;
				return true;
			} else if (level >= 11) {
				emoteId = 873;
				axeTime = 4;
				return true;
			}
		}
		if (player.getInventory().containsItemToolBelt(1353)) {
			if (level >= 6 && player.wcskillfuanim == true) {
				emoteId = 17304;
				gfxId = 3301;
				axeTime = 3;
				return true;
			} else if (level >= 6) {
				emoteId = 875;
				axeTime = 3;
				return true;
			}
		}
		if (player.getInventory().containsItemToolBelt(1349)) {
			emoteId = 877;
			axeTime = 2;
			return true;
		}
		if (player.getInventory().containsItemToolBelt(1351)) {
			emoteId = 879;
			axeTime = 1;
			return true;
		}
		if (player.getInventory().containsItemToolBelt(13661)) {
			if (level >= 61 && player.wcskillfuanim == true) {
				emoteId = 17304;
				gfxId = 3301;
				axeTime = 13;
				return true;
			} else if (level >= 61 && player.wcanim == true) {
				emoteId = 17093;
				axeTime = 13;
			} else if (level >= 61) {
				emoteId = 10251;
				axeTime = 13;
				return true;
			}
		}
		return false;

	}

	public boolean hasAxe(Player player) {// this should fix bronze axe
		if (player.getInventory().containsOneItem(1351, 1349, 1353, 1355, 1357, 1361, 1359, 6739, 13661, 29412, 29680, 16381)
				|| player.getInventory().containsItemToolBelt(1351)) {
			return true;
		}
		int weaponId = player.getEquipment().getWeaponId();
		if (weaponId == -1 && !player.getInventory().containsItemToolBelt(1351)) {
			return false;
		}
		switch (weaponId) {
		case 1351:// Bronze Axe
		case 1349:// Iron Axe
		case 1353:// Steel Axe
		case 1361:// Black Axe
		case 1355:// Mithril Axe
		case 1357:// Adamant Axe
		case 1359:// Rune Axe
		case 6739:// Dragon Axe
		case 29680:// gilded d axe
		case 29412: // infernal axe
		case 13661: // Inferno adze
		case 16381: // primal axe
			return true;
		default:
			return false;
		}

	}

	@Override
	public boolean process(Player player) {
		player.setNextAnimation(new Animation(usingBeaver ? 1 : emoteId));
		player.setNextGraphics(new Graphics(gfxId));
		return checkTree(player);
	}

	private boolean usedDeplateAurora;

	@Override
	public int processWithDelay(Player player) {

		if (definitions == TreeDefinitions.YEW || definitions == TreeDefinitions.MAGIC) {
			int proteleport;
			proteleport = Utils.random(1000);
			switch (proteleport) {
			case 716:
				player.sm("<col=ff0000>Congratulations you found a mysterious teleport.");
				player.getBank().addItem(29629, 1, true);
				break;
			default:
				break;
			}
		}

		int communitychest;
		communitychest = Utils.random(3000);
		switch (communitychest) {
		case 109:
			player.sm("<col=ff0000>Congratulations you found a Community chest.");
			player.getBank().addItem(29655, 1, true);
			break;
		default:
			break;
		}

		addLog(player);
		if (!usedDeplateAurora && 1 + Math.random() < player.getAuraManager().getChanceNotDepleteMN_WC()) {
			usedDeplateAurora = true;
		} else if (Utils.getRandom(definitions.getRandomLifeProbability()) == 0) {
			long time = definitions.respawnDelay * 600;
			World.spawnObjectTemporary(new WorldObject(definitions.getStumpId(), tree.getType(), tree.getRotation(),
					tree.getX(), tree.getY(), tree.getPlane()), time, false, true);
			if (tree.getPlane() < 3 && definitions != TreeDefinitions.IVY) {
				WorldObject object = World
						.getObject(new WorldTile(tree.getX() - 1, tree.getY() - 1, tree.getPlane() + 1));

				if (object == null) {
					object = World.getObject(new WorldTile(tree.getX(), tree.getY() - 1, tree.getPlane() + 1));
					if (object == null) {
						object = World.getObject(new WorldTile(tree.getX() - 1, tree.getY(), tree.getPlane() + 1));
						if (object == null) {
							object = World.getObject(new WorldTile(tree.getX(), tree.getY(), tree.getPlane() + 1));
						}
					}
				}

				if (object != null) {
					World.removeTemporaryObject(object, time, false);
				}
			}
			player.setNextAnimation(new Animation(-1));
			player.setNextGraphics(new Graphics(-1));
			return -1;
		}
		if (!player.getInventory().hasFreeSlots()) {
			player.setNextAnimation(new Animation(-1));
			player.setNextGraphics(new Graphics(-1));
			player.getPackets().sendGameMessage("Not enough space in your inventory.");
			return -1;
		}
		if (definitions == TreeDefinitions.AFK_TREE && Utils.random(50) == 1) {
			player.setNextAnimation(new Animation(-1));
			player.setNextGraphics(new Graphics(-1));
			player.getPackets().sendGameMessage("Your arms grow tired of chopping...");
			return -1;
		}
		return getWoodcuttingDelay(player);
	}

	private void addLog(Player player) {
		double xpBoost = 1.00;
		int logId = definitions.getLogsId();
		if (player.getEquipment().getChestId() == 10939) {
			xpBoost += 0.008;
		}
		if (player.getEquipment().getLegsId() == 10940) {
			xpBoost += 0.006;
		}
		if (player.getEquipment().getHatId() == 10941) {
			xpBoost += 0.004;
		}
		if (player.getEquipment().getBootsId() == 10933) {
			xpBoost += 0.002;
		}
		if (player.getEquipment().getChestId() == 10939 && player.getEquipment().getLegsId() == 10940
				&& player.getEquipment().getHatId() == 10941 && player.getEquipment().getBootsId() == 10933) {
			xpBoost += 0.005;
		}
		player.getSkills().addXp(8, definitions.getXp() * xpBoost);
		if (Utils.random(0, 5) <= 1 &&  Settings.HWEEN) {
			player.getInventory().addItemDrop(29319, Utils.random((int)definitions.getXp() / 10));
		}
		if (player.getEquipment().getAmmoId() == 29112) {
			if (Utils.random(5) == 0) {
				if (logId == 29138) {
					logId = logId -1;
				} else if (logId == 9067) {
					logId = 29014;
				} else {
					logId = logId +1;
				}
			}
		}
		if (Utils.random(1, 10) == 1 && player.getEquipment().getCapeId() == 29974) {
			player.getBank().addItem(logId, 1, true);
			player.sendMessage("Your master woodcutting cape perk has sent a log to your bank.");
		} else if (Utils.random(1, 10) == 1 && player.getEquipment().getCapeId() == 20771 && player.getSkills().getXp(Skills.WOODCUTTING) >= 104273167) {
			player.getBank().addItem(logId, 1, true);
			player.sendMessage("Your master woodcutting cape perk has sent a log to your bank.");
		} else if (definitions == TreeDefinitions.AFK_TREE) {
			player.getInventory().addItem(995, 5000);
		} else if (player.alwaysAdzeActive == true && !(definitions == TreeDefinitions.IVY)) {
			String logName = ItemDefinitions.getItemDefinitions(definitions.getLogsId()).getName().toLowerCase();
			player.setNextGraphics(new Graphics(1776));
			player.getSkills().addXp(Skills.FIREMAKING, definitions.getXp() / 2);
		} else {
			player.getInventory().addItem(logId, 1);
		}
		player.cut100anylog++;
		for (int bosspet : Settings.PETS_WITH_PERKS) {
			if (player.getPetManager().getNpcId() == bosspet && player.getPetPerk().getContainer().contains(new Item(29271)) && Utils.random(21) == 0) {
				player.getSkills().addXp(Skills.FIREMAKING, definitions.getXp());
				player.getPackets().sendGameMessage(Colors.cyan + "Your resource grants you some experience in Firemaking.", true);
			}
		}
		if (player.getEquipment().getWeaponId() == 29412 || player.getInventory().contains(29412) && !(definitions == TreeDefinitions.IVY)) {
			String logName = ItemDefinitions.getItemDefinitions(definitions.getLogsId()).getName().toLowerCase();
			if (Utils.getRandom(2) == 0 && player.getSkills().getLevelForXp(Skills.WOODCUTTING) >= 61) {
				player.getInventory().deleteItem(definitions.logsId, 1);
				player.getPackets().sendGameMessage("The hatchets heat instantly incinerates the " + logName + ".");
				player.setNextGraphics(new Graphics(1776));
				player.getSkills().addXp(Skills.FIREMAKING, definitions.getXp() / 2);
			}
		}
		if (player.getEquipment().getWeaponId() == 13661 || player.getInventory().contains(13661) && !(definitions == TreeDefinitions.IVY)) {
			String logName = ItemDefinitions.getItemDefinitions(definitions.getLogsId()).getName().toLowerCase();
			if (Utils.getRandom(2) == 0 && player.getSkills().getLevelForXp(Skills.WOODCUTTING) >= 61) {
				player.getInventory().deleteItem(definitions.logsId, 1);
				player.getPackets().sendGameMessage("The adze's heat instantly incinerates the " + logName + ".");
				player.setNextGraphics(new Graphics(1776));
				player.getSkills().addXp(Skills.FIREMAKING, definitions.getXp() / 2);				
			}
		}
		if (Settings.eventdoubleskillingresources > 0) {
			player.getInventory().addItem(logId, 1);
		}
		if (definitions == TreeDefinitions.YEW) {
			if (player.WoodcuttingTaskYew > 0) {
				player.WoodcuttingTaskYew -= 1;
			}
			player.logscut++;
		}
		if (player.challengeid == 49 && player.challengeamount > 0 && definitions == TreeDefinitions.OAK) {
			DailyChallenges.UpdateChallenge(player);
		}
		if (player.challengeid == 50 && player.challengeamount > 0 && definitions == TreeDefinitions.WILLOW) {
			DailyChallenges.UpdateChallenge(player);
		}
		if (player.challengeid == 51 && player.challengeamount > 0 && definitions == TreeDefinitions.MAGIC) {
			DailyChallenges.UpdateChallenge(player);
		}
		if(Utils.random(99999) == 0) {
			if (player.getPet() == null && player.woodcutpet != true) {
				player.getPetManager().spawnPet(29524, false);
				player.woodcutpet = true;
				player.sendMessage("You have a funny feeling something is following you.");
				World.sendWorldMessage("<img=7><col=339966>News: "+player.getDisplayName()+" has just found a Woodcutting pet! ("+ Utils.getFormattedNumber((int) player.getSkills().getXp(Skills.WOODCUTTING))+"XP)", false);
			} else if (player.getPet() != null && player.woodcutpet != true) {
				player.woodcutpet = true;
				player.sendMessage("Speak to the pet tamer at home to claim your pet.");
				World.sendWorldMessage("<img=7><col=339966>News: "+player.getDisplayName()+" has just found a Woodcutting pet! ("+ Utils.getFormattedNumber((int) player.getSkills().getXp(Skills.WOODCUTTING))+"XP)", false);
			} else {
				player.sendMessage("You feel like something was following you.");
			}
		}
		if (definitions == TreeDefinitions.SKILLBOSS_TREE) {
			if (Settings.canskillchopping != true) {
				player.applyHit(new Hit(player, player.getMaxHitpoints() / 5, HitLook.REGULAR_DAMAGE, 15));
				return;
			} else if (Settings.Ingenuitychopping >= 250) {
				Settings.canskillchopping = false;
				Settings.canskillfirepit = true;
				player.getPackets().sendGameMessage("The resource has no energy left.", true);
				return;
			}
			Settings.Ingenuitychopping++;
			player.getPackets().sendGameMessage("A wisp of energy is sent across the island.", true);
		}
		if (definitions == TreeDefinitions.DREAM_TREE) {
			player.dreamcut++;
			player.sendMessage("You have cut " + player.dreamcut + " dream logs.");
		}
		if (player.challengeid == 4 && player.challengeamount > 0 && definitions == TreeDefinitions.NORMAL && Wilderness.isAtWild(player)) {
			WildernessTasks.UpdateChallenge(player);
		}
		if (player.challengeid == 5 && player.challengeamount > 0 && definitions == TreeDefinitions.MAPLE && Wilderness.isAtWild(player)) {
			WildernessTasks.UpdateChallenge(player);
		}
		if (player.challengeid == 6 && player.challengeamount > 0 && definitions == TreeDefinitions.MAGIC && Wilderness.isAtWild(player)) {
			WildernessTasks.UpdateChallenge(player);
		}
		if (definitions == TreeDefinitions.IVY) {
			player.getPackets().sendGameMessage("You succesfully cut an ivy vine.", true);
			// todo gfx
		} else if (definitions == TreeDefinitions.AFK_TREE) {
				player.getPackets().sendGameMessage("You chop a portion of the tree and find coins inside.");
		} else {
			String logName = ItemDefinitions.getItemDefinitions(definitions.getLogsId()).getName().toLowerCase();
			if (definitions == TreeDefinitions.SKILLBOSS_TREE) {
				player.getPackets().sendGameMessage("A wisp of energy is sent across the island.", true);
				return;
			}
			if (definitions != TreeDefinitions.CRYSTAL_TREE || definitions != TreeDefinitions.AFK_TREE) {
				if (player.alwaysAdzeActive == true) {
					player.getPackets().sendGameMessage("Your Always Adze perk incinerates the " + logName + ", granting firemaking xp.", true);
				} else {
					player.getPackets().sendGameMessage("You get some " + logName + ".", true);
				}
			}
			player.addNest(player);
			// todo infernal adze
		}
	}

	private boolean checkTree(Player player) {
		return World.getRegion(tree.getRegionId()).containsObject(tree.getId(), tree);
	}

	@Override
	public void stop(Player player) {
		setActionDelay(player, 3);
	}

	public static void addLog(TreeDefinitions definitions, boolean dungeoneering, boolean usingBeaver, Player player) {
		if (!usingBeaver) {
			double xpBoost = 1.00;
			if (player.getEquipment().getChestId() == 10939) {
				xpBoost += 0.008;
			}
			if (player.getEquipment().getLegsId() == 10940) {
				xpBoost += 0.006;
			}
			if (player.getEquipment().getHatId() == 10941) {
				xpBoost += 0.004;
			}
			if (player.getEquipment().getBootsId() == 10933) {
				xpBoost += 0.002;
			}
			if (player.getEquipment().getChestId() == 10939 && player.getEquipment().getLegsId() == 10940
					&& player.getEquipment().getHatId() == 10941 && player.getEquipment().getBootsId() == 10933) {
				xpBoost += 0.005;
			}
			player.getSkills().addXp(8, definitions.getXp() * xpBoost);
		}
		if (definitions.getLogsId() != -1) {
			if (usingBeaver) {
				if (player.getFamiliar() != null) {
					player.getInventory().addItemDrop(definitions.getLogsId(), 1);
				}
			} else {

				player.getInventory().addItemDrop(definitions.getLogsId(), 1);
				if (!dungeoneering && Utils.random(50) == 0) {
					player.getInventory().addItemDrop(BIRD_NESTS[Utils.random(BIRD_NESTS.length)], 1);
					player.getPackets().sendGameMessage("A bird's nest falls out of the tree!");
				}
			}
			if (definitions == TreeDefinitions.IVY) {
				player.getPackets().sendGameMessage("You succesfully cut an ivy vine.", true);
				// todo gfx
			} else if (definitions == TreeDefinitions.AFK_TREE){
				player.getPackets().sendGameMessage("You chop a portion of the tree and find coins inside.");
			} else {
				String logName = ItemDefinitions.getItemDefinitions(definitions.getLogsId()).getName().toLowerCase();
				player.getPackets().sendGameMessage("You get some " + logName + ".", true);
				// todo infernal adze
			}
		}
	}

	private static final int[] BIRD_NESTS = { 5070, 5071, 5072, 5073, 5074, 5075, 7413, 11966 };

}
