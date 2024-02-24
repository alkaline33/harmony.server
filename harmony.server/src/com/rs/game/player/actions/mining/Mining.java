package com.rs.game.player.actions.mining;

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
import com.rs.game.player.actions.mining.Mining.RockDefinitions;
import com.rs.game.player.content.DailyChallenges;
import com.rs.game.player.content.Magic;
import com.rs.game.player.content.ShootingStar;
import com.rs.game.player.content.WildernessTasks;
import com.rs.game.player.content.event.season.SeasonEvent;
import com.rs.game.player.controlers.Wilderness;
import com.rs.utils.Colors;
import com.rs.utils.Utils;

public final class Mining extends MiningBase {

	public static enum RockDefinitions {

		// level, xp, itemfromore, ######, time takes to get rock, Deadrockid, Respawn,
		// amount of ore in rock (randomised)

		Clay_Ore(1, 5, 434, 10, 1, -1, -1, -1),
		Copper_Ore(1, 17.5, 436, 10, 1, -1, -1, -1),
		Tin_Ore(1, 17.5, 438, 15, 1, -1, -1, -1),
		Iron_Ore(15, 35, 440, 15, 1, -1, -1, -1),
		Sandstone_Ore(35, 30, 6971, 30, 1, -1, -1, -1),
		Silver_Ore(20, 40, 442, 25, 1, -1, -1, -1),
		Coal_Ore(30, 50, 453, 50, 10, -1, -1, -1),
		Granite_Ore(45, 50, 6979, 50, 10, -1, -1, -1),
		Gold_Ore(40, 60, 444, 80, 20, -1, -1, -1),
		Mithril_Ore(55, 80, 447, 100, 20, -1, -1, -1),
		Adamant_Ore(70, 95, 449, 130, 25, -1, -1, -1),
		Runite_Ore(85, 125, 451, 140, 27, -1, -1, -1),
		LRC_Coal_Ore(77, 50, 453, 15, 1, -1, -1, -1),
		LRC_Gold_Ore(80, 60, 444, 15, 1, -1, -1, -1),
		Red_Standstone_Ore(81, 12, 23194, 30, 1, -1, -1, -1),
		Limestone_Ore(10, 10, 3211, 10, 5, -1, -1, -1),
		CRASHED_STAR(10, 25, 13727, 2, 30, -1, -1, -1),
		Blurite_Ore(95, 225, 29955, 2, 30, -1, -1, -1),
		LEVEL3_ORE(80, 125, 24516, 2, 30, -1, -1, -1),
		Gem_Rock(40, 50, -1, 2, 30, -1, -1, -1),
		SHEAFESSENCE(99, 141, 29635, 20, 1, -1, -1, -1),
		Dragon_Ore(95, 135, 29551, 140, 27, -1, -1, -1),
		SkillBoss_Ore(80, 5, -1, 2, 5, 11193, 2, 5000);

		private int level;
		private double xp;
		private int oreId;
		private int oreBaseTime;
		private int oreRandomTime;
		private int emptySpot;
		private int respawnDelay;
		private int randomLifeProbability;

		private RockDefinitions(int level, double xp, int oreId, int oreBaseTime, int oreRandomTime, int emptySpot, int respawnDelay, int randomLifeProbability) {
			this.level = level;
			this.xp = xp;
			this.oreId = oreId;
			this.oreBaseTime = oreBaseTime;
			this.oreRandomTime = oreRandomTime;
			this.emptySpot = emptySpot;
			this.respawnDelay = respawnDelay;
			this.randomLifeProbability = randomLifeProbability;
		}

		public int getLevel() {
			return level;
		}

		public double getXp() {
			return xp;
		}

		public int getOreId() {
			return oreId;
		}

		public int getOreBaseTime() {
			return oreBaseTime;
		}

		public int getOreRandomTime() {
			return oreRandomTime;
		}

		public int getEmptyId() {
			return emptySpot;
		}

		public int getRespawnDelay() {
			return respawnDelay;
		}

		public int getRandomLifeProbability() {
			return randomLifeProbability;
		}
	}

	private WorldObject rock;
	private RockDefinitions definitions;

	public Mining(WorldObject rock, RockDefinitions definitions) {
		this.rock = rock;
		this.definitions = definitions;
	}

	@Override
	public boolean start(Player player) {
		if (!checkAll(player)) {
			return false;
		}
		player.getPackets().sendGameMessage("You swing your pickaxe at the rock.", true);
		setActionDelay(player, getMiningDelay(player));
		return true;
	}

	private int getMiningDelay(Player player) {
		int summoningBonus = 0;
		if (player.getFamiliar() != null) {
			if (player.getFamiliar().getId() == 7342 || player.getFamiliar().getId() == 7342) {
				summoningBonus += 10;
			} else if (player.getFamiliar().getId() == 6832 || player.getFamiliar().getId() == 6831) {
				summoningBonus += 1;
			}
		}
		if (player.miningperk == true) {
			summoningBonus += 25;
		}

		if (player.getEquipment().getCapeId() == 29983) {
			summoningBonus += 10;
		} else if (player.getEquipment().getCapeId() == 20771 && player.getSkills().getXp(Skills.MINING) >= 104273167) {
			summoningBonus += 10;
		}
		int levelzone = World.Level3Zone(player) ? 10 : 0;
		// System.out.println(mineTimer);
		int mineTimer = definitions.getOreBaseTime() - (player.getSkills().getLevel(Skills.MINING) + summoningBonus) - Utils.getRandom(pickaxeTime) - levelzone;
		// System.out.println(mineTimer);
		if (mineTimer < 1 + definitions.getOreRandomTime()) {
			mineTimer = 1 + Utils.getRandom(definitions.getOreRandomTime());
		}
		mineTimer /= player.getAuraManager().getMininingAccurayMultiplier();
		return mineTimer;
	}

	private boolean checkAll(Player player) {
		if (!hasPickaxe(player)) {
			player.getPackets().sendGameMessage("You need a pickaxe to mine this rock.");
			return false;
		}
		if (definitions == RockDefinitions.CRASHED_STAR) {
			player.getShootingStar().checkIfFirst();
		}
		if (definitions == RockDefinitions.Dragon_Ore) {
			if (player.dwarvenpart < 4) {
				player.getPackets().sendGameMessage("You must've completed the dwarven discovery quest to do this.");
				return false;
			}
		}
		if (!setPickaxe(player)) {
			player.getPackets().sendGameMessage("You dont have the required level to use this pickaxe.");
			return false;
		}
		if (!hasMiningLevel(player)) {
			return false;
		}
		if (definitions == RockDefinitions.Red_Standstone_Ore && player.dailyredstone >= 75) {
			player.sendMessage("You can only mine 75 Red sandstone a day.");
			return false;
		}
		if (!player.getInventory().hasFreeSlots()) {
			player.getPackets().sendGameMessage("Not enough space in your inventory.");
			return false;
		}
		return true;
	}

	private boolean hasMiningLevel(Player player) {
		if (definitions.getLevel() > player.getSkills().getLevel(Skills.MINING)) {
			player.getPackets().sendGameMessage("You need a mining level of " + definitions.getLevel() + " to mine this rock.");
			return false;
		}
		return true;
	}

	@Override
	public boolean process(Player player) {
		player.setNextAnimation(new Animation(emoteId));
		player.setNextGraphics(new Graphics(gfxId));
		return checkRock(player);
	}

	private boolean usedDeplateAurora;

	@Override
	public int processWithDelay(Player player) {
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

		if (definitions == RockDefinitions.Runite_Ore || definitions == RockDefinitions.Blurite_Ore) {
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
		SeasonEvent.HandleActivity(player, "Mining", (int)definitions.getXp() / 15);
		if (Utils.random(0, 5) <= 1 &&  Settings.HWEEN) {
			player.getInventory().addItemDrop(29319, Utils.random((int)definitions.getXp() / 10));
		}
		if (definitions == RockDefinitions.Gem_Rock) {
			gem = Utils.random(100);
			switch (gem) {
			case 0:
				player.getInventory().addItem(1631, 1);
				player.getPackets().sendGameMessage("You find an uncut dragonstone.", true);
				break;
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 7:
			case 8:
			case 9:
			case 10:
			case 11:
			case 12:
			case 13:
			case 14:
			case 15:
			case 16:
			case 17:
			case 18:
			case 19:
			case 20:
			case 21:
				player.getInventory().addItem(1627, 1);
				player.getPackets().sendGameMessage("You find an uncut jade.", true);
				break;
			case 22:
			case 23:
			case 24:
			case 25:
			case 26:
			case 27:
			case 28:
			case 29:
			case 30:
			case 31:
			case 32:
			case 33:
				player.getInventory().addItem(1629, 1);
				player.getPackets().sendGameMessage("You find an uncut red topaz.", true);
				break;
			case 34:
			case 35:
			case 36:
			case 37:
			case 38:
			case 39:
			case 40:
				player.getInventory().addItem(1623, 1);
				player.getPackets().sendGameMessage("You find an uncut sapphire.", true);
				break;
			case 41:
			case 42:
			case 43:
			case 44:
				player.getInventory().addItem(1621, 1);
				player.getPackets().sendGameMessage("You find an uncut emerald.", true);
				break;
			case 45:
			case 46:
			case 47:
				player.getInventory().addItem(1619, 1);
				player.getPackets().sendGameMessage("You find an uncut ruby.", true);
				break;
			case 48:
			case 49:
				player.getInventory().addItem(1618, 1);
				player.getPackets().sendGameMessage("You find an uncut diamond.", true);
				break;
			default:
				player.getInventory().addItem(1625, 1);
				player.getPackets().sendGameMessage("You find an uncut opal.", true);
				break;
			}
		}
		addOre(player);
		if (definitions == RockDefinitions.Red_Standstone_Ore) {
			player.dailyredstone++;
		}

		if (player.getEquipment().getWeaponId() == 29411) {
			if (Utils.getRandom(3) == 0) {
				player.getSkills().addXp(Skills.SMITHING, definitions.getXp() / 2 * 1);
				player.getInventory().deleteItem(definitions.oreId, 1);
				player.getPackets().sendGameMessage("The Infernal pickaxe heat instantly incinerates the ore.");
				// player.setNextGraphics(new Graphics(1776));
			}
		}
		if (definitions.getEmptyId() != -1) {
			if (!usedDeplateAurora && 1 + Math.random() < player.getAuraManager().getChanceNotDepleteMN_WC()) {
				usedDeplateAurora = true;
			} else if (Utils.getRandom(definitions.getRandomLifeProbability()) == 0) {
				// if (rock.spawnedByEd) { // *600
				// World.spawnTemporaryObject(new WorldObject(definitions.getEmptyId(),
				// rock.getType(), rock.getRotation(), rock.getX(), rock.getY(),
				// rock.getPlane()), definitions.respawnDelay , false, rock);
				World.spawnObjectTemporary(new WorldObject(definitions.getEmptyId(), rock.getType(), rock.getRotation(), rock.getX(), rock.getY(), rock.getPlane()), definitions.respawnDelay * 600, false, true);

				// }
				player.setNextAnimation(new Animation(-1));
				player.setNextGraphics(new Graphics(-1));
				return -1;
			}
		}
		if (!player.getInventory().hasFreeSlots() && definitions.getOreId() != -1) {
			player.setNextAnimation(new Animation(-1));
			player.setNextGraphics(new Graphics(-1));
			player.getPackets().sendGameMessage("Not enough space in your inventory.");
			return -1;
		}
		return getMiningDelay(player);
	}

	private static int gem;

	private void addOre(Player player) {

		// player.refreshSpawnedObjects();
		double xpBoost = 0;
		int idSome = 0;
		if (player.challengeid == 34 && player.challengeamount > 0 && definitions == RockDefinitions.Iron_Ore) {
			DailyChallenges.UpdateChallenge(player);
		}
		if (player.challengeid == 35 && player.challengeamount > 0 && definitions == RockDefinitions.Adamant_Ore) {
			DailyChallenges.UpdateChallenge(player);
		}
		if (player.challengeid == 36 && player.challengeamount > 0 && definitions == RockDefinitions.Runite_Ore) {
			DailyChallenges.UpdateChallenge(player);
		}
		if (Utils.random(99999) == 0) {
			if (player.getPet() == null && player.miningpet != true) {
				player.getPetManager().spawnPet(29523, false);
				player.miningpet = true;
				player.sendMessage("You have a funny feeling something is following you.");
				World.sendWorldMessage("<img=7><col=339966>News: "+player.getDisplayName()+" has just found a Mining pet! ("+ Utils.getFormattedNumber((int) player.getSkills().getXp(Skills.MINING))+"XP)", false);
				} else if (player.getPet() != null && player.miningpet != true) {
				player.miningpet = true;
				player.sendMessage("Speak to the pet tamer at home to claim your pet.");
				World.sendWorldMessage("<img=7><col=339966>News: "+player.getDisplayName()+" has just found a Mining pet! ("+ Utils.getFormattedNumber((int) player.getSkills().getXp(Skills.MINING))+"XP)", false);
			} else {
				player.sendMessage("You feel like something was following you.");
			}
		}
		if (definitions == RockDefinitions.Granite_Ore) {
			idSome = Utils.getRandom(2) * 2;
			if (idSome == 2) {
				xpBoost += 10;
			} else if (idSome == 4)
			 {
				xpBoost += 25; // player.sedimentsobtained++;
			}
		} else if (definitions == RockDefinitions.SkillBoss_Ore) {
			if (Settings.canskillmining != true) {
				player.applyHit(new Hit(player, player.getMaxHitpoints() / 5, HitLook.REGULAR_DAMAGE, 15));
				return;
			} else if (Settings.Ingenuitymining >= 250) {
				Settings.canskillmining = false;
				Settings.canskillfishing = true;
				player.getPackets().sendGameMessage("The resource has no energy left.", true);
				return;
			}
			Settings.Ingenuitymining++;
			player.getPackets().sendGameMessage("A wisp of energy is sent across the island.", true);
		} else if (definitions == RockDefinitions.Sandstone_Ore) {
			idSome = Utils.getRandom(3) * 2;
			xpBoost += idSome / 2 * 10;
		} else if (definitions == RockDefinitions.CRASHED_STAR) {
			player.getShootingStar().mineCrashedStar();
			ShootingStar.stardustMined++;
		}
		double totalXp = definitions.getXp() + xpBoost;
		if (hasMiningSuit(player)) {
			totalXp *= 1.025;
		}
		player.getSkills().addXp(Skills.MINING, totalXp);
		for (int bosspet : Settings.PETS_WITH_PERKS) {
			if (player.getPetManager().getNpcId() == bosspet && player.getPetPerk().getContainer().contains(new Item(29271)) && Utils.random(21) == 0) {
				player.getSkills().addXp(Skills.SMITHING, totalXp);
				player.getPackets().sendGameMessage(Colors.cyan + "Your resource grants you some experience in Smithing.", true);
			}
		}
		if (definitions.getOreId() == 451) {
			player.oremined++;
		}
		if (player.challengeid == 1 && player.challengeamount > 0 && definitions == RockDefinitions.Coal_Ore && Wilderness.isAtWild(player)) {
			WildernessTasks.UpdateChallenge(player);
		}
		if (player.challengeid == 2 && player.challengeamount > 0 && definitions == RockDefinitions.Mithril_Ore && Wilderness.isAtWild(player)) {
			WildernessTasks.UpdateChallenge(player);
		}
		if (player.challengeid == 3 && player.challengeamount > 0 && definitions == RockDefinitions.Runite_Ore && Wilderness.isAtWild(player)) {
			WildernessTasks.UpdateChallenge(player);
		}
		if (definitions.getOreId() == 29635) {
			player.proresourcescollected++;
			if (player.proresourcescollected == 250) {
				player.proresourcescollected = 0;
				player.sendMessage("A mysterious force you removes from the area.");
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(Settings.RESPAWN_PLAYER_LOCATION));
			}
		}
		if (player.getEquipment().getAmmoId() == 29112 && definitions != RockDefinitions.Blurite_Ore && definitions != RockDefinitions.Dragon_Ore && definitions != RockDefinitions.LEVEL3_ORE && definitions != RockDefinitions.SkillBoss_Ore && definitions != RockDefinitions.Red_Standstone_Ore && definitions != RockDefinitions.Sandstone_Ore && definitions != RockDefinitions.CRASHED_STAR) {
			if (Utils.random(5) == 0) {
				idSome += 1;
			}
		}
		if (definitions.getOreId() != -1) {
			player.getInventory().addItem(definitions.getOreId() + idSome, 1);
			if (player.getInventory().contains(28933) && player.getOreBag().getContainer().freeSlots() >= 1 ) {
				if (definitions.getOreId() != -1) {
				player.getOreBag().add(new Item (definitions.getOreId() + idSome, 1));
				}
			}
			if (Settings.eventdoubleskillingresources > 0) {
				player.getInventory().addItem(definitions.getOreId() + idSome, 1);
			}
		}
		
		String oreName = ItemDefinitions.getItemDefinitions(definitions.getOreId() + idSome).getName().toLowerCase();
		player.getPackets().sendGameMessage("You mine some " + oreName + ".", true);
	}

	private boolean hasMiningSuit(Player player) {
		if (player.getEquipment().getHatId() == 20789 && player.getEquipment().getChestId() == 20791 && player.getEquipment().getLegsId() == 20790 && player.getEquipment().getBootsId() == 20788) {
			return true;
		}
		return false;
	}

	private boolean checkRock(Player player) {
		// World.sendWorldMessage("34", false);
		return World.getRegion(rock.getRegionId()).containsObject(rock.getId(), rock);
	}

}
