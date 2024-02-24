package com.rs.game.player.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.rs.Settings;
import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.ForceTalk;
import com.rs.game.Graphics;
import com.rs.game.Hit;
import com.rs.game.Hit.HitLook;
import com.rs.game.Region;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.npc.NPC;
import com.rs.game.npc.familiar.Familiar;
import com.rs.game.npc.familiar.Steeltitan;
import com.rs.game.npc.fightkiln.HarAken;
import com.rs.game.npc.fightkiln.HarAkenTentacle;
import com.rs.game.npc.godwars.zaros.NexMinion;
import com.rs.game.npc.pest.PestPortal;
import com.rs.game.npc.qbd.QueenBlackDragon;
import com.rs.game.player.CombatDefinitions;
import com.rs.game.player.Equipment;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.content.Combat;
import com.rs.game.player.content.Magic;
import com.rs.game.player.controlers.SlayerSurvival;
import com.rs.game.player.controlers.Wilderness;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Colors;
import com.rs.utils.MapAreas;
import com.rs.utils.Utils;

public class PlayerCombat extends Action {

	private Entity target;
	private int max_hit; // temporary constant
	private double base_mage_xp; // temporary constant
	private int mage_hit_gfx; // temporary constant
	private int magic_sound; // temporary constant
	private int max_poison_hit; // temporary constant
	private int freeze_time; // temporary constant
	private boolean reduceAttack; // temporary constant
	private boolean blood_spell; // temporary constant
	private boolean sanguinesti_spell; // temporary constant
	private boolean block_tele;
	private int spellcasterGloves;

	private boolean rubySpec;

	public PlayerCombat(Entity target) {
		this.target = target;
	}

	@Override
	public boolean start(Player player) {
		player.setNextFaceEntity(target);
		if (checkAll(player)) {
			return true;
		}
		player.setNextFaceEntity(null);
		return false;
	}

	@Override
	public boolean process(Player player) {
		return checkAll(player);
	}

	private boolean forceCheckClipAsRange(Entity target) {
		return target instanceof NexMinion || target instanceof HarAken || target instanceof PestPortal || target instanceof HarAkenTentacle || target instanceof QueenBlackDragon;
	}

	@Override
	public int processWithDelay(Player player) {
		int isRanging = isRanging(player);
		int spellId = player.getCombatDefinitions().getSpellId();
		if (spellId < 1 && hasPolyporeStaff(player)) {
			spellId = 65535;
		}
		if (spellId < 1 && hasTridentSeas(player)) {
			// System.out.println("2");
			spellId = 65536;
		}
		if (spellId < 1 && hasTridentSwamp(player)) {
			// System.out.println("2");
			spellId = 65536;
		}
		if (spellId < 1 && hasSanguinestiStaff(player)) {
			// System.out.println("1");
			spellId = 65537;
		}
		if (hasStaffScale(player)) {
			if (player.blowpipescales < 1) {
				player.sendMessage("<col=ff0000> Your staff has no scales remaining!");
				return -1;
			}
			player.blowpipescales--;
		}
		if (hasBlowpipe(player)) {
			if (player.blowpipedarts < 1) {
				player.sendMessage("<col=ff0000> Your blowpipe has no darts to shoot!");
				return -1;
			}
			if (player.blowpipescales < 1) {
				player.sendMessage("<col=ff0000> Your blowpipe has no scales remaining!");
				return -1;
			}
			int degrade = Utils.random(1, 3);
			if (degrade == 1) {
				player.blowpipescales--;
				player.blowpipedarts--;
			}
		}
		if (hasStaffofDarkness(player)) {
			if (player.wyrmstaffcharges < 1) {
				player.sendMessage("<col=ff0000> Your staff has no charges left!");
				return -1;
			}
			player.wyrmstaffcharges--;
		}
		if (hasStrykeBow(player)) {
			if (player.wyrmbowcharges < 1) {
				player.sendMessage("<col=ff0000> Your bow has no charges left!");
				return -1;
			}
			player.wyrmbowcharges--;
		}
		if (hasLavaWhip(player)) {
			if (player.wyrmwhipcharges < 1) {
				player.sendMessage("<col=ff0000> Your whip has no charges left!");
				return -1;
			}
			player.wyrmwhipcharges--;

		}
		if (hasWildyWep(player)) {
			if (player.widywepcharges < 1) {
				player.sendMessage("<col=ff0000> Your weapon has no ether charges left!");
				return -1;
			}
			player.widywepcharges--;

		}
		int maxDistance = isRanging != 0 || spellId > 0 ? 7 : 0;
		int distanceX = player.getX() - target.getX();
		int distanceY = player.getY() - target.getY();
		double multiplier = 1.0;
		if (player.getTemporaryAttributtes().get("miasmic_effect") == Boolean.TRUE) {
			multiplier = 1.5;
		}
		int size = target.getSize();
		if (!player.clipedProjectile(target, maxDistance == 0 && !forceCheckClipAsRange(target))) {
			return 0;
		}
		if (player.hasWalkSteps()) {
			maxDistance += 1;
		}
		if (distanceX > size + maxDistance || distanceX < -1 - maxDistance || distanceY > size + maxDistance || distanceY < -1 - maxDistance) {
			return 0;
		}
		if (!player.getControlerManager().keepCombating(target)) {
			return -1;
		}
		addAttackedByDelay(player);

		if (spellId > 0) {
			// System.out.println("2");
			boolean manualCast = spellId != 65535 && spellId != 65536 && spellId != 65537 && spellId >= 256;
			Item gloves = player.getEquipment().getItem(Equipment.SLOT_HANDS);
			spellcasterGloves = gloves != null && gloves.getDefinitions().getName().contains("Spellcaster glove") && player.getEquipment().getWeaponId() == -1 && new Random().nextInt(30) == 0 ? spellId : -1;
			int delay = mageAttack(player, manualCast ? spellId - 256 : spellId, !manualCast);
			if (player.getNextAnimation() != null && spellcasterGloves > 0) {
				player.setNextAnimation(new Animation(14339));
				spellcasterGloves = -1;
			}
			return delay;
		} else {
			if (isRanging == 0) {
				return (int) (meleeAttack(player) * multiplier);
			} else if (isRanging == 1) {
				player.getPackets().sendGameMessage("This ammo is not very effective with this weapon.");
				return -1;
			} else if (isRanging == 3) {
				player.getPackets().sendGameMessage("You dont have any ammo in your backpack.");
				return -1;
			} else {
				return (int) (rangeAttack(player) * multiplier);
			}
		}
	}

	private void addAttackedByDelay(Entity player) {
		target.setAttackedBy(player);
		target.setAttackedByDelay(Utils.currentTimeMillis() + 8000); // 8seconds
	}

	private int getRangeCombatDelay(int weaponId, int attackStyle) {
		int delay = 6;
		if (weaponId != -1) {
			String weaponName = ItemDefinitions.getItemDefinitions(weaponId).getName().toLowerCase();
			if (weaponName.contains("shortbow") || weaponName.contains("karil's crossbow") || weaponName.contains("craw's bow") || weaponName.contains("chinchompa")) {
				delay = 3;
			} else if (weaponName.contains("crossbow")) {
				delay = 5;
			} else if (weaponName.contains("ascension")) {
				delay = 3;
			} else if (weaponName.contains("dart") || weaponName.contains("knife") || weaponId == 29784 || weaponId == 29457 || weaponId == 29255) {
				delay = 2;
			} else {
				switch (weaponId) {
				case 4212:
					delay = 3;
					break;
				case 15241:
				case 29368:
					delay = 7;
					break;
				case 29594: // heavy ballista
				case 11235: // dark bows
				case 15701:
				case 15702:
				case 15703:
				case 15704:
					delay = 9;
					break;
				case 29782:
				case 29547:
				case 29472:
				case 24474:
				case 20173:
					delay = 4;
					break;
				case 16337:
					delay = 4;
					break;
				case 29476:
					delay = 5;
					break;
				default:
					delay = 6;
					break;
				}
			}
		}
		if (attackStyle == 1) {
			delay--;
		} else if (attackStyle == 2) {
			delay++;
		}
		return delay;
	}

	public Entity[] getMultiAttackTargets(Player player) {
		return getMultiAttackTargets(player, 1, 9);
	}

	public Entity[] getMultiAttackTargets(Player player, int maxDistance, int maxAmtTargets) {
		List<Entity> possibleTargets = new ArrayList<Entity>();
		possibleTargets.add(target);
		if (target.isAtMultiArea()) {
			y: for (int regionId : target.getMapRegionsIds()) {
				Region region = World.getRegion(regionId);
				if (target instanceof Player) {
					List<Integer> playerIndexes = region.getPlayerIndexes();
					if (playerIndexes == null) {
						continue;
					}
					for (int playerIndex : playerIndexes) {
						Player p2 = World.getPlayers().get(playerIndex);
						if (p2 == null || p2 == player || p2 == target || p2.isDead() || !p2.hasStarted() || p2.hasFinished() || !p2.isCanPvp() || !p2.isAtMultiArea() || !p2.withinDistance(target, maxDistance) || !player.getControlerManager().canHit(p2)) {
							continue;
						}
						possibleTargets.add(p2);
						if (possibleTargets.size() == maxAmtTargets) {
							break y;
						}
					}
				} else {
					List<Integer> npcIndexes = region.getNPCsIndexes();
					if (npcIndexes == null) {
						continue;
					}
					for (int npcIndex : npcIndexes) {
						NPC n = World.getNPCs().get(npcIndex);
						if (n == null || n == target || n == player.getFamiliar() || n.isDead() || n.hasFinished() || !n.isAtMultiArea() || !n.withinDistance(target, maxDistance) || !n.getDefinitions().hasAttackOption() || !player.getControlerManager().canHit(n)) {
							continue;
						}
						possibleTargets.add(n);
						if (possibleTargets.size() == maxAmtTargets) {
							break y;
						}
					}
				}
			}
		}
		return possibleTargets.toArray(new Entity[possibleTargets.size()]);
	}

	public int mageAttack(final Player player, int spellId, boolean autocast) {
		if (!autocast) {
			player.getCombatDefinitions().resetSpells(false);
			player.getActionManager().forceStop();
		}
		if (!Magic.checkCombatSpell(player, spellId, -1, true)) {
			if (autocast) {
				player.getCombatDefinitions().resetSpells(true);
			}
			return -1; // stops
		}
		Item ring = player.getEquipment().getItem(Equipment.SLOT_RING);
		Item amulet = player.getEquipment().getItem(Equipment.SLOT_AMULET);
		Item shield = player.getEquipment().getItem(Equipment.SLOT_SHIELD);
		Item weapon = player.getEquipment().getItem(Equipment.SLOT_WEAPON);
		Item helm = player.getEquipment().getItem(Equipment.SLOT_HAT);
		if (weapon != null) {
			if (weapon.getId() == 29453 && !target.getPoison().isPoisoned()) {
				int venomchance = Utils.random(1, 4);
				if (helm != null && helm.getId() == 29456 || helm.getId() == 29045 || helm.getId() == 29044 && target instanceof NPC) {
					venomchance = 1;
				}
				if (venomchance == 1) {
					target.getPoison().makePoisoned(80);
				}
			}
		}
		if (shield != null && target instanceof NPC) {
			if (shield.getDefinitions().getId() == 29527 && target instanceof NPC) {
				player.getPrayer().restorePrayer(3);
			}
		}
		if (amulet != null && target instanceof NPC) {
			if (amulet.getDefinitions().getName().equalsIgnoreCase("amulet of immense power") && target instanceof NPC) {
				NPC n = (NPC) target;
				Random soulchance = new Random();
				int soul = soulchance.nextInt(10);
				if (soul == 8) {
					player.heal(80);
					n.applyHit(new Hit(player, 80, HitLook.MAGIC_DAMAGE));
				} else if (soul != 8) {
				}
			}
		}
		if (amulet != null && target instanceof NPC) {
			if (amulet.getDefinitions().getId() == 29732 || amulet.getDefinitions().getName().equalsIgnoreCase("amulet of souls") && target instanceof NPC) {
				// player.sendMessage("Working");
				NPC n = (NPC) target;
				Random soulchance = new Random();
				int soul = soulchance.nextInt(10);
				if (soul == 8) {
					player.heal(50);
					n.applyHit(new Hit(player, 50, HitLook.MAGIC_DAMAGE));
				} else if (soul != 8) {
				}
			}
		}
		if (amulet != null && target instanceof NPC) {
			if (amulet != null && amulet.getDefinitions().getName().contains("Blood amulet of fury") && !amulet.getDefinitions().getName().contains("broken") && target instanceof NPC) {
				// player.sendMessage("Working");
				NPC n = (NPC) target;
				Random soulchance = new Random();
				int soul = soulchance.nextInt(10);
				if (soul == 8) {
					player.heal(80);
				} else if (soul != 8) {
				}
			}
		}
		if (ring != null && target instanceof NPC) {
			if (ring != null && ring.getDefinitions().getName().contains("of Death") && !ring.getDefinitions().getName().contains("broken") && target instanceof NPC) {
				NPC n = (NPC) target;
				Random damagechance = new Random();
				int dam = damagechance.nextInt(20);
				if (dam == 8) {
					n.applyHit(new Hit(player, n.getHitpoints() / (player.rodeffectincrease ? 10 : 20), HitLook.REGULAR_DAMAGE));
				} else if (dam != 8) {
				}
			}
			if (ring != null && ring.getDefinitions().getName().contains("Level 1 Ring") && !ring.getDefinitions().getName().contains("broken") && target instanceof NPC) {
				NPC n = (NPC) target;
				Random damagechance = new Random();
				int dam = damagechance.nextInt(20);
				if (dam == 8) {
					n.applyHit(new Hit(player, n.getHitpoints() / (player.rodeffectincrease ? 10 : 20), HitLook.REGULAR_DAMAGE));
				} else if (dam != 8) {
				}
			}
			if (ring != null && ring.getDefinitions().getName().contains("Mein Fuhrer Ring") && target instanceof NPC) {
				NPC n = (NPC) target;
				Random damagechance = new Random();
				int dam = damagechance.nextInt(20);
				if (dam == 8) {
					n.applyHit(new Hit(player, n.getHitpoints() / (player.rodeffectincrease ? 10 : 20), HitLook.REGULAR_DAMAGE));
				} else if (dam != 8) {
				}
			}
			if (ring != null && ring.getDefinitions().getName().contains("Raptor's ring")) {
				Random damagechance = new Random();
				int dam = damagechance.nextInt(20);
				if (dam == 8) {
					player.heal(player.getHitpoints() / 10);
				} else if (dam != 8) {
				}
			}
			if (ring != null && ring.getDefinitions().getName().contains("Immense power") && !ring.getDefinitions().getName().contains("broken") && target instanceof NPC) {
				NPC n = (NPC) target;
				Random damagechance = new Random();
				int dam = damagechance.nextInt(20);
				if (dam == 8) {
					n.applyHit(new Hit(player, n.getHitpoints() / (player.rodeffectincrease ? 10 : 20), HitLook.REGULAR_DAMAGE));
				} else if (dam != 8) {
				}
			}
			if (ring != null && ring.getDefinitions().getName().contains("Pussy Slapper") && !ring.getDefinitions().getName().contains("broken") && target instanceof NPC) {
				NPC n = (NPC) target;
				Random damagechance = new Random();
				int dam = damagechance.nextInt(20);
				if (dam == 8) {
					n.applyHit(new Hit(player, n.getHitpoints() / (player.rodeffectincrease ? 10 : 20), HitLook.REGULAR_DAMAGE));
				} else if (dam != 8) {
				}
			}
		}

		if (spellId == 65535) {
			player.setNextFaceEntity(target);
			player.setNextGraphics(new Graphics(2034));
			player.setNextAnimation(new Animation(15448));
			mage_hit_gfx = 2036;
			if (player.twofoldPerkActive && Utils.random(1, 3) == 2) {
				delayMagicHit(2, getMagicHit(player, getRandomMagicMaxHit(player, 5 * player.getSkills().getLevel(Skills.MAGIC) - 180)));
			}
			delayMagicHit(2, getMagicHit(player, getRandomMagicMaxHit(player, 5 * player.getSkills().getLevel(Skills.MAGIC) - 180)));
			World.sendProjectile(player, target, 2035, 60, 32, 50, 50, 0, 0);
			return 4;
		}
		if (spellId == 65536) {
			// System.out.println("1111111");
			player.setNextFaceEntity(target);
			// player.setNextGraphics(new Graphics(2034));
			player.setNextAnimation(new Animation(1978));
			// mage_hit_gfx = 2036;
			if (player.twofoldPerkActive && Utils.random(1, 3) == 2) {
				delayMagicHit(2, getMagicHit(player, getRandomMagicMaxHit(player, 5 * player.getSkills().getLevel(Skills.MAGIC) - 180)));
			}
			delayMagicHit(2, getMagicHit(player, getRandomMagicMaxHit(player, 5 * player.getSkills().getLevel(Skills.MAGIC) - 180)));
			World.sendProjectile(player, target, 185, 60, 32, 50, 50, 0, 0);
			return 4;
		}
		if (spellId == 65537) {
			// System.out.println("1");
			player.setNextFaceEntity(target);
			player.setNextAnimation(new Animation(1978));
			sanguinesti_spell = true;
			if (player.twofoldPerkActive && Utils.random(1, 3) == 2) {
				delayMagicHit(2, getMagicHit(player, getRandomMagicMaxHit(player, 5 * player.getSkills().getLevel(Skills.MAGIC) - 180)));
			}
			delayMagicHit(2, getMagicHit(player, getRandomMagicMaxHit(player, 5 * player.getSkills().getLevel(Skills.MAGIC) - 180)));
			World.sendProjectile(player, target, 195, 60, 32, 50, 50, 0, 0);
			return 4;
		}
		if (player.getCombatDefinitions().getSpellBook() == 192) {
			switch (spellId) {
			case 25: // air strike
				player.setNextAnimation(new Animation(14221));
				mage_hit_gfx = 2700;
				base_mage_xp = 5.5;
				int baseDamage = 20;
				if (player.getEquipment().getGlovesId() == 205) {
					baseDamage = 90;
				}
				if (player.twofoldPerkActive && Utils.random(1, 3) == 2) {
					delayMagicHit(2, getMagicHit(player, getRandomMagicMaxHit(player, baseDamage)));
				}
				delayMagicHit(2, getMagicHit(player, getRandomMagicMaxHit(player, baseDamage)));
				World.sendProjectile(player, target, 2699, 18, 18, 50, 50, 0, 0);
				return 5;
			case 28: // water strike
				player.setNextGraphics(new Graphics(2701));
				player.setNextAnimation(new Animation(14221));
				mage_hit_gfx = 2708;
				base_mage_xp = 7.5;
				baseDamage = 40;
				if (player.getEquipment().getGlovesId() == 205) {
					baseDamage = 100;
				}
				if (player.twofoldPerkActive && Utils.random(1, 3) == 2) {
					delayMagicHit(2, getMagicHit(player, getRandomMagicMaxHit(player, baseDamage)));
				}
				delayMagicHit(2, getMagicHit(player, getRandomMagicMaxHit(player, baseDamage)));
				World.sendProjectile(player, target, 2703, 18, 18, 50, 50, 0, 0);
				return 5;
			case 36:// bind
				player.setNextGraphics(new Graphics(177));
				player.setNextAnimation(new Animation(710));
				mage_hit_gfx = 181;
				base_mage_xp = 30.5;
				Hit magicHit = getMagicHit(player, getRandomMagicMaxHit(player, 20));
				delayMagicHit(2, magicHit);
				World.sendProjectile(player, target, 178, 18, 18, 50, 50, 0, 0);
				long currentTime = Utils.currentTimeMillis();
				if (magicHit.getDamage() > 0 && target.getSize() >= 2 || target.getCanFreezeThis() == false || target.getFreezeDelay() >= Utils.currentTimeMillis() || target.getFrozenBlockedDelay() >= Utils.currentTimeMillis()) {
					//System.out.println("NO STUN");
				} else {
					target.addFreezeDelay(5000, true);
				}
				return 5;
			case 55:// snare
				player.setNextGraphics(new Graphics(177));
				player.setNextAnimation(new Animation(710));
				mage_hit_gfx = 180;
				base_mage_xp = 40.1;
				Hit snareHit = getMagicHit(player, getRandomMagicMaxHit(player, 30));
				delayMagicHit(2, snareHit);
				if (snareHit.getDamage() > 0 && target.getSize() >= 2 || target.getCanFreezeThis() == false || target.getFreezeDelay() >= Utils.currentTimeMillis() || target.getFrozenBlockedDelay() >= Utils.currentTimeMillis()) {
					//System.out.println("NO STUN");
				} else {
					target.addFreezeDelay(10000, true);
				}
				World.sendProjectile(player, target, 178, 18, 18, 50, 50, 0, 0);
				return 5;
			case 81:// entangle
				player.setNextGraphics(new Graphics(177));
				player.setNextAnimation(new Animation(710));
				mage_hit_gfx = 179;
				base_mage_xp = 45.1;
				Hit entangleHit = getMagicHit(player, getRandomMagicMaxHit(player, 50));
				delayMagicHit(2, entangleHit);
				if (entangleHit.getDamage() > 0 && target.getSize() >= 2 || target.getCanFreezeThis() == false || target.getFreezeDelay() >= Utils.currentTimeMillis() || target.getFrozenBlockedDelay() >= Utils.currentTimeMillis()) {
					//System.out.println("NO STUN");
				} else {
					target.addFreezeDelay(20000, true);
				}
				World.sendProjectile(player, target, 178, 18, 18, 50, 50, 0, 0);
				return 5;
			case 30: // earth strike
				player.setNextGraphics(new Graphics(2713));
				player.setNextAnimation(new Animation(14221));
				mage_hit_gfx = 2723;
				base_mage_xp = 9.5;
				baseDamage = 60;
				if (player.getEquipment().getGlovesId() == 205) {
					baseDamage = 110;
				}
				if (player.twofoldPerkActive && Utils.random(1, 3) == 2) {
					delayMagicHit(2, getMagicHit(player, getRandomMagicMaxHit(player, baseDamage)));
				}
				delayMagicHit(2, getMagicHit(player, getRandomMagicMaxHit(player, baseDamage)));
				World.sendProjectile(player, target, 2718, 18, 18, 50, 50, 0, 0);
				return 5;
			case 32: // fire strike
				player.setNextGraphics(new Graphics(2728));
				player.setNextAnimation(new Animation(14221));
				mage_hit_gfx = 2737;
				base_mage_xp = 11.5;
				baseDamage = 80;
				if (player.getEquipment().getGlovesId() == 205) {
					baseDamage = 120;
				}
				int damage = getRandomMagicMaxHit(player, baseDamage);
				if (target instanceof NPC) {
					NPC n = (NPC) target;
					if (n.getId() == 9463 || n.getId() == 14301 || n.getId() == 9928) {
						damage *= 2;
					}
				}
				if (player.twofoldPerkActive && Utils.random(1, 3) == 2) {
					delayMagicHit(2, getMagicHit(player, damage));
				}
				delayMagicHit(2, getMagicHit(player, damage));
				World.sendProjectile(player, target, 2729, 18, 18, 50, 50, 0, 0);
				return 5;

			case 34: // air bolt
				player.setNextAnimation(new Animation(14220));
				mage_hit_gfx = 2700;
				base_mage_xp = 13.5;
				baseDamage = 90;
				if (player.getEquipment().getGlovesId() == 777) {
					baseDamage = 120;
				}
				if (player.twofoldPerkActive && Utils.random(1, 3) == 2) {
					delayMagicHit(2, getMagicHit(player, getRandomMagicMaxHit(player, baseDamage)));
				}
				delayMagicHit(2, getMagicHit(player, getRandomMagicMaxHit(player, baseDamage)));
				World.sendProjectile(player, target, 2699, 18, 18, 50, 50, 0, 0);
				return 5;
			case 39: // water bolt
				player.setNextGraphics(new Graphics(2707, 0, 100));
				player.setNextAnimation(new Animation(14220));
				mage_hit_gfx = 2709;
				base_mage_xp = 16.5;
				baseDamage = 100;
				if (player.getEquipment().getGlovesId() == 777) {
					baseDamage = 130;
				}
				if (player.twofoldPerkActive && Utils.random(1, 3) == 2) {
					delayMagicHit(2, getMagicHit(player, getRandomMagicMaxHit(player, baseDamage)));
				}
				delayMagicHit(2, getMagicHit(player, getRandomMagicMaxHit(player, baseDamage)));
				World.sendProjectile(player, target, 2704, 18, 18, 50, 50, 0, 0);
				return 5;
			case 42: // earth bolt
				player.setNextGraphics(new Graphics(2714));
				player.setNextAnimation(new Animation(14222));
				mage_hit_gfx = 2724;
				base_mage_xp = 19.5;
				baseDamage = 110;
				if (player.getEquipment().getGlovesId() == 777) {
					baseDamage = 140;
				}
				if (player.twofoldPerkActive && Utils.random(1, 3) == 2) {
					delayMagicHit(2, getMagicHit(player, getRandomMagicMaxHit(player, baseDamage)));
				}
				delayMagicHit(2, getMagicHit(player, getRandomMagicMaxHit(player, baseDamage)));
				World.sendProjectile(player, target, 2719, 18, 18, 50, 50, 0, 0);
				return 5;
			case 45: // fire bolt
				player.setNextGraphics(new Graphics(2728));
				player.setNextAnimation(new Animation(14223));
				mage_hit_gfx = 2738;
				base_mage_xp = 22.5;
				baseDamage = 120;
				if (player.getEquipment().getGlovesId() == 777) {
					baseDamage = 150;
				}
				damage = getRandomMagicMaxHit(player, baseDamage);
				if (target instanceof NPC) {
					NPC n = (NPC) target;
					if (n.getId() == 9463 || n.getId() == 14301 || n.getId() == 9928) {
						damage *= 2;
					}
				}
				if (player.twofoldPerkActive && Utils.random(1, 3) == 2) {
					delayMagicHit(2, getMagicHit(player, damage));
				}
				delayMagicHit(2, getMagicHit(player, damage));
				World.sendProjectile(player, target, 2731, 18, 18, 50, 50, 0, 0);
				return 5;
			case 49: // air blast
				player.setNextAnimation(new Animation(14221));
				mage_hit_gfx = 2700;
				base_mage_xp = 25.5;
				if (player.twofoldPerkActive && Utils.random(1, 3) == 2) {
					delayMagicHit(2, getMagicHit(player, getRandomMagicMaxHit(player, 130)));
				}
				delayMagicHit(2, getMagicHit(player, getRandomMagicMaxHit(player, 130)));
				World.sendProjectile(player, target, 2699, 18, 18, 50, 50, 0, 0);
				return 5;
			case 52: // water blast
				player.setNextGraphics(new Graphics(2701));
				player.setNextAnimation(new Animation(14220));
				mage_hit_gfx = 2710;
				base_mage_xp = 31.5;
				if (player.twofoldPerkActive && Utils.random(1, 3) == 2) {
					delayMagicHit(2, getMagicHit(player, getRandomMagicMaxHit(player, 140)));
				}
				delayMagicHit(2, getMagicHit(player, getRandomMagicMaxHit(player, 140)));
				World.sendProjectile(player, target, 2705, 18, 18, 50, 50, 0, 0);
				return 5;
			case 58: // earth blast
				player.setNextGraphics(new Graphics(2715));
				player.setNextAnimation(new Animation(14222));
				mage_hit_gfx = 2725;
				base_mage_xp = 31.5;
				if (player.twofoldPerkActive && Utils.random(1, 3) == 2) {
					delayMagicHit(2, getMagicHit(player, getRandomMagicMaxHit(player, 150)));
				}
				delayMagicHit(2, getMagicHit(player, getRandomMagicMaxHit(player, 150)));
				World.sendProjectile(player, target, 2720, 18, 18, 50, 50, 0, 0);
				return 5;
			case 63: // fire blast
				player.setNextGraphics(new Graphics(2728));
				player.setNextAnimation(new Animation(14223));
				mage_hit_gfx = 2739;
				base_mage_xp = 34.5;
				damage = getRandomMagicMaxHit(player, 160);
				if (target instanceof NPC) {
					NPC n = (NPC) target;
					if (n.getId() == 9463 || n.getId() == 14301 || n.getId() == 9928) {
						damage *= 2;
					}
				}
				if (player.twofoldPerkActive && Utils.random(1, 3) == 2) {
					delayMagicHit(2, getMagicHit(player, damage));
				}
				delayMagicHit(2, getMagicHit(player, damage));
				World.sendProjectile(player, target, 2733, 18, 18, 50, 50, 0, 0);
				return 5;
			case 66:// Saradomin Strike
				player.setNextAnimation(new Animation(811));
				mage_hit_gfx = 76;
				base_mage_xp = 34.5;
				if (player.twofoldPerkActive && Utils.random(1, 3) == 2) {
					delayMagicHit(2, getMagicHit(player, getRandomMagicMaxHit(player, 300)));
				}
				delayMagicHit(2, getMagicHit(player, getRandomMagicMaxHit(player, 300)));
				return 5;
			case 67: // Claws of Guthix
				player.setNextAnimation(new Animation(811));
				mage_hit_gfx = 77;
				base_mage_xp = 34.5;
				if (player.twofoldPerkActive && Utils.random(1, 3) == 2) {
					delayMagicHit(2, getMagicHit(player, getRandomMagicMaxHit(player, 300)));
				}
				delayMagicHit(2, getMagicHit(player, getRandomMagicMaxHit(player, 300)));
				return 5;
			case 68: // Flames of Zamorak
				player.setNextAnimation(new Animation(811));
				mage_hit_gfx = 78;
				base_mage_xp = 34.5;
				if (player.twofoldPerkActive && Utils.random(1, 3) == 2) {
					delayMagicHit(2, getMagicHit(player, getRandomMagicMaxHit(player, 300)));
				}
				delayMagicHit(2, getMagicHit(player, getRandomMagicMaxHit(player, 300)));
				return 5;
			case 70: // air wave
				player.setNextAnimation(new Animation(14221));
				mage_hit_gfx = 2700;
				base_mage_xp = 36;
				if (player.twofoldPerkActive && Utils.random(1, 3) == 2) {
					delayMagicHit(2, getMagicHit(player, getRandomMagicMaxHit(player, 170)));
				}
				delayMagicHit(2, getMagicHit(player, getRandomMagicMaxHit(player, 170)));
				World.sendProjectile(player, target, 2699, 18, 18, 50, 50, 0, 0);
				return 5;
			case 73: // water wave
				player.setNextGraphics(new Graphics(2702));
				player.setNextAnimation(new Animation(14220));
				mage_hit_gfx = 2710;
				base_mage_xp = 37.5;
				if (player.twofoldPerkActive && Utils.random(1, 3) == 2) {
					delayMagicHit(2, getMagicHit(player, getRandomMagicMaxHit(player, 180)));
				}
				delayMagicHit(2, getMagicHit(player, getRandomMagicMaxHit(player, 180)));
				World.sendProjectile(player, target, 2706, 18, 18, 50, 50, 0, 0);
				return 5;
			case 77: // earth wave
				player.setNextGraphics(new Graphics(2716));
				player.setNextAnimation(new Animation(14222));
				mage_hit_gfx = 2726;
				base_mage_xp = 42.5;
				if (player.twofoldPerkActive && Utils.random(1, 3) == 2) {
					delayMagicHit(2, getMagicHit(player, getRandomMagicMaxHit(player, 190)));
				}
				delayMagicHit(2, getMagicHit(player, getRandomMagicMaxHit(player, 190)));
				World.sendProjectile(player, target, 2721, 18, 18, 50, 50, 0, 0);
				return 5;
			case 80: // fire wave
				player.setNextGraphics(new Graphics(2728));
				player.setNextAnimation(new Animation(14223));
				mage_hit_gfx = 2740;
				base_mage_xp = 42.5;
				damage = getRandomMagicMaxHit(player, 200);
				if (target instanceof NPC) {
					NPC n = (NPC) target;
					if (n.getId() == 9463 || n.getId() == 14301 || n.getId() == 9928) {
						damage *= 2;
					}
				}
				if (player.twofoldPerkActive && Utils.random(1, 3) == 2) {
					delayMagicHit(2, getMagicHit(player, damage));
				}
				delayMagicHit(2, getMagicHit(player, damage));
				World.sendProjectile(player, target, 2735, 18, 18, 50, 50, 0, 0);
				return 5;
			case 86: // teleblock
				if (target instanceof Player && ((Player) target).getTeleBlockDelay() <= Utils.currentTimeMillis()) {
					player.setNextGraphics(new Graphics(1841));
					player.setNextAnimation(new Animation(10503));
					mage_hit_gfx = 1843;
					base_mage_xp = 80;
					block_tele = true;
					System.out.println("teleblock2");
					Hit hit = getMagicHit(player, getRandomMagicMaxHit(player, 30));
					delayMagicHit(2, hit);
					World.sendProjectile(player, target, 1842, 18, 18, 50, 50, 0, 0);
				} else {
					player.getPackets().sendGameMessage("This player is already effected by this spell.", true);
				}
				return 5;
			case 84:// air surge
				player.setNextGraphics(new Graphics(457));
				player.setNextAnimation(new Animation(10546));
				mage_hit_gfx = 2700;
				base_mage_xp = 80;
				if (player.twofoldPerkActive && Utils.random(1, 3) == 2) {
					delayMagicHit(2, getMagicHit(player, getRandomMagicMaxHit(player, 220)));
				}
				delayMagicHit(2, getMagicHit(player, getRandomMagicMaxHit(player, 220)));
				World.sendProjectile(player, target, 462, 18, 18, 50, 50, 0, 0);
				return 5;
			case 87:// water surge
				player.setNextGraphics(new Graphics(2701));
				player.setNextAnimation(new Animation(10542));
				mage_hit_gfx = 2712;
				base_mage_xp = 80;
				if (player.twofoldPerkActive && Utils.random(1, 3) == 2) {
					delayMagicHit(2, getMagicHit(player, getRandomMagicMaxHit(player, 240)));
				}
				delayMagicHit(2, getMagicHit(player, getRandomMagicMaxHit(player, 240)));
				World.sendProjectile(player, target, 2707, 18, 18, 50, 50, 3, 0);
				return 5;
			case 89:// earth surge
				player.setNextGraphics(new Graphics(2717));
				player.setNextAnimation(new Animation(14209));
				mage_hit_gfx = 2727;
				base_mage_xp = 80;
				if (player.twofoldPerkActive && Utils.random(1, 3) == 2) {
					delayMagicHit(2, getMagicHit(player, getRandomMagicMaxHit(player, 260)));
				}
				delayMagicHit(2, getMagicHit(player, getRandomMagicMaxHit(player, 260)));
				World.sendProjectile(player, target, 2722, 18, 18, 50, 50, 0, 0);
				return 5;
			case 91:// fire surge
				player.setNextGraphics(new Graphics(2728));
				player.setNextAnimation(new Animation(2791));
				mage_hit_gfx = 2741;
				base_mage_xp = 80;
				damage = getRandomMagicMaxHit(player, 280);
				if (damage > 0 && target instanceof NPC) {
					NPC n = (NPC) target;
					if (n.getId() == 9463 || n.getId() == 14301 || n.getId() == 9928) {
						damage *= 2;
					}
				}
				if (player.twofoldPerkActive && Utils.random(1, 3) == 2) {
					delayMagicHit(2, getMagicHit(player, damage));
				}
				delayMagicHit(2, getMagicHit(player, damage));
				World.sendProjectile(player, target, 2735, 18, 18, 50, 50, 3, 0);
				World.sendProjectile(player, target, 2736, 18, 18, 50, 50, 20, 0);
				World.sendProjectile(player, target, 2736, 18, 18, 50, 50, 110, 0);
				return 5;
			case 99: // Storm of armadyl
				player.setNextGraphics(new Graphics(457));
				player.setNextAnimation(new Animation(10546));
				mage_hit_gfx = 1019;
				base_mage_xp = 70;
				int boost = (player.getSkills().getLevelForXp(Skills.MAGIC) - 77) * 5;
				int hit = getRandomMagicMaxHit(player, 150 + boost);
				if (hit > 0 && hit < boost) {
					hit += boost;
				}
				if (player.twofoldPerkActive && Utils.random(1, 3) == 2) {
					delayMagicHit(2, getMagicHit(player, hit));
				}
				delayMagicHit(2, getMagicHit(player, hit));
				World.sendProjectile(player, target, 1019, 18, 18, 50, 30, 0, 0);
				return player.getEquipment().getWeaponId() == 21777 ? 3 : 4;
			}
		} else if (player.getCombatDefinitions().getSpellBook() == 193) {
			switch (spellId) {
			case 28:// Smoke Rush
				player.setNextAnimation(new Animation(1978));
				mage_hit_gfx = 385;
				base_mage_xp = 30;
				max_poison_hit = 20;
				if (player.twofoldPerkActive && Utils.random(1, 3) == 2) {
					delayMagicHit(2, getMagicHit(player, getRandomMagicMaxHit(player, 150)));
				}
				delayMagicHit(2, getMagicHit(player, getRandomMagicMaxHit(player, 150)));
				World.sendProjectile(player, target, 386, 18, 18, 50, 50, 0, 0);
				return 4;
			case 32:// Shadow Rush
				player.setNextAnimation(new Animation(1978));
				mage_hit_gfx = 379;
				base_mage_xp = 31;
				reduceAttack = true;
				if (player.twofoldPerkActive && Utils.random(1, 3) == 2) {
					delayMagicHit(2, getMagicHit(player, getRandomMagicMaxHit(player, 160)));
				}
				delayMagicHit(2, getMagicHit(player, getRandomMagicMaxHit(player, 160)));
				World.sendProjectile(player, target, 380, 18, 18, 50, 50, 0, 0);
				return 4;
			case 36: // Miasmic rush
				player.setNextAnimation(new Animation(10513));
				player.setNextGraphics(new Graphics(1845));
				mage_hit_gfx = 1847;
				base_mage_xp = 35;
				if (player.twofoldPerkActive && Utils.random(1, 3) == 2) {
					delayMagicHit(2, getMagicHit(player, getRandomMagicMaxHit(player, 200)));
				}
				delayMagicHit(2, getMagicHit(player, getRandomMagicMaxHit(player, 200)));
				World.sendProjectile(player, target, 1846, 43, 22, 51, 50, 16, 0);
				if (target.getTemporaryAttributtes().get("miasmic_immunity") == Boolean.TRUE) {
					return 4;
				}
				if (target instanceof Player) {
					((Player) target).getPackets().sendGameMessage("You feel slowed down.");
				}
				target.getTemporaryAttributtes().put("miasmic_immunity", Boolean.TRUE);
				target.getTemporaryAttributtes().put("miasmic_effect", Boolean.TRUE);
				final Entity t = target;
				WorldTasksManager.schedule(new WorldTask() {
					@Override
					public void run() {
						t.getTemporaryAttributtes().remove("miasmic_effect");
						WorldTasksManager.schedule(new WorldTask() {
							@Override
							public void run() {
								t.getTemporaryAttributtes().remove("miasmic_immunity");
								stop();
							}
						}, 15);
						stop();
					}
				}, 20);
				return 4;
			case 37: // Miasmic blitz
				player.setNextAnimation(new Animation(10524));
				player.setNextGraphics(new Graphics(1850));
				mage_hit_gfx = 1851;
				base_mage_xp = 48;
				if (player.twofoldPerkActive && Utils.random(1, 3) == 2) {
					delayMagicHit(2, getMagicHit(player, getRandomMagicMaxHit(player, 280)));
				}
				delayMagicHit(2, getMagicHit(player, getRandomMagicMaxHit(player, 280)));
				World.sendProjectile(player, target, 1852, 43, 22, 51, 50, 16, 0);
				if (target.getTemporaryAttributtes().get("miasmic_immunity") == Boolean.TRUE) {
					return 4;
				}
				if (target instanceof Player) {
					((Player) target).getPackets().sendGameMessage("You feel slowed down.");
				}
				target.getTemporaryAttributtes().put("miasmic_immunity", Boolean.TRUE);
				target.getTemporaryAttributtes().put("miasmic_effect", Boolean.TRUE);
				final Entity t0 = target;
				WorldTasksManager.schedule(new WorldTask() {
					@Override
					public void run() {
						t0.getTemporaryAttributtes().remove("miasmic_effect");
						WorldTasksManager.schedule(new WorldTask() {
							@Override
							public void run() {
								t0.getTemporaryAttributtes().remove("miasmic_immunity");
								stop();
							}
						}, 15);
						stop();
					}
				}, 60);
				return 4;
			case 38: // Miasmic burst
				player.setNextAnimation(new Animation(10516));
				player.setNextGraphics(new Graphics(1848));
				attackTarget(getMultiAttackTargets(player), new MultiAttack() {
					private boolean nextTarget;

					@Override
					public boolean attack() {
						mage_hit_gfx = 1849;
						base_mage_xp = 42;
						int damage = getRandomMagicMaxHit(player, 240);
						if (player.twofoldPerkActive && Utils.random(1, 3) == 2) {
							delayMagicHit(2, getMagicHit(player, damage));
						}
						delayMagicHit(2, getMagicHit(player, damage));
						if (target.getTemporaryAttributtes().get("miasmic_immunity") != Boolean.TRUE) {
							if (target instanceof Player) {
								((Player) target).getPackets().sendGameMessage("You feel slowed down.");
							}
							target.getTemporaryAttributtes().put("miasmic_immunity", Boolean.TRUE);
							target.getTemporaryAttributtes().put("miasmic_effect", Boolean.TRUE);
							final Entity t = target;
							WorldTasksManager.schedule(new WorldTask() {
								@Override
								public void run() {
									t.getTemporaryAttributtes().remove("miasmic_effect");
									WorldTasksManager.schedule(new WorldTask() {
										@Override
										public void run() {
											t.getTemporaryAttributtes().remove("miasmic_immunity");
											stop();
										}
									}, 15);
									stop();
								}
							}, 40);
						}
						if (!nextTarget) {
							if (damage == -1) {
								return false;
							}
							nextTarget = true;
						}
						return nextTarget;

					}
				});
				return 4;
			case 39: // Miasmic barrage
				player.setNextAnimation(new Animation(10518));
				player.setNextGraphics(new Graphics(1853));
				attackTarget(getMultiAttackTargets(player), new MultiAttack() {
					private boolean nextTarget;

					@Override
					public boolean attack() {
						mage_hit_gfx = 1854;
						base_mage_xp = 54;
						int damage = getRandomMagicMaxHit(player, 320);
						if (player.twofoldPerkActive && Utils.random(1, 3) == 2) {
							delayMagicHit(2, getMagicHit(player, damage));
						}
						delayMagicHit(2, getMagicHit(player, damage));
						if (target.getTemporaryAttributtes().get("miasmic_immunity") != Boolean.TRUE) {
							if (target instanceof Player) {
								((Player) target).getPackets().sendGameMessage("You feel slowed down.");
							}
							target.getTemporaryAttributtes().put("miasmic_immunity", Boolean.TRUE);
							target.getTemporaryAttributtes().put("miasmic_effect", Boolean.TRUE);
							final Entity t = target;
							WorldTasksManager.schedule(new WorldTask() {
								@Override
								public void run() {
									t.getTemporaryAttributtes().remove("miasmic_effect");
									WorldTasksManager.schedule(new WorldTask() {
										@Override
										public void run() {
											t.getTemporaryAttributtes().remove("miasmic_immunity");
											stop();
										}
									}, 15);
									stop();
								}
							}, 80);
						}
						if (!nextTarget) {
							if (damage == -1) {
								return false;
							}
							nextTarget = true;
						}
						return nextTarget;

					}
				});
				return 4;
			case 24:// Blood rush
				player.setNextAnimation(new Animation(1978));
				mage_hit_gfx = 373;
				base_mage_xp = 33;
				blood_spell = true;
				if (player.twofoldPerkActive && Utils.random(1, 3) == 2) {
					delayMagicHit(2, getMagicHit(player, getRandomMagicMaxHit(player, 170)));
				}
				delayMagicHit(2, getMagicHit(player, getRandomMagicMaxHit(player, 170)));
				World.sendProjectile(player, target, 374, 18, 18, 50, 50, 0, 0);
				return 4;
			case 20:// Ice rush
				player.setNextAnimation(new Animation(1978));
				mage_hit_gfx = 361;
				base_mage_xp = 34;
				freeze_time = 5000;
				if (player.twofoldPerkActive && Utils.random(1, 3) == 2) {
					delayMagicHit(2, getMagicHit(player, getRandomMagicMaxHit(player, 180)));
				}
				delayMagicHit(2, getMagicHit(player, getRandomMagicMaxHit(player, 180)));
				World.sendProjectile(player, target, 362, 18, 18, 50, 50, 0, 0);
				return 4;
			// TODO burst
			case 30:// Smoke burst
				player.setNextAnimation(new Animation(1979));
				attackTarget(getMultiAttackTargets(player), new MultiAttack() {

					private boolean nextTarget; // real target is first player on array

					@Override
					public boolean attack() {
						mage_hit_gfx = 389;
						base_mage_xp = 36;
						max_poison_hit = 20;
						int damage = getRandomMagicMaxHit(player, 190);
						if (player.twofoldPerkActive && Utils.random(1, 3) == 2) {
							delayMagicHit(2, getMagicHit(player, damage));
						}
						delayMagicHit(2, getMagicHit(player, damage));
						World.sendProjectile(player, target, 388, 18, 18, 50, 50, 0, 0);
						if (!nextTarget) {
							if (damage == -1) {
								return false;
							}
							nextTarget = true;
						}
						return nextTarget;

					}
				});
				return 4;
			case 34:// Shadow burst
				player.setNextAnimation(new Animation(1979));
				attackTarget(getMultiAttackTargets(player), new MultiAttack() {

					private boolean nextTarget; // real target is first player on array

					@Override
					public boolean attack() {
						mage_hit_gfx = 382;
						base_mage_xp = 37;
						reduceAttack = true;
						int damage = getRandomMagicMaxHit(player, 200);
						if (player.twofoldPerkActive && Utils.random(1, 3) == 2) {
							delayMagicHit(2, getMagicHit(player, damage));
						}
						delayMagicHit(2, getMagicHit(player, damage));
						if (!nextTarget) {
							if (damage == -1) {
								return false;
							}
							nextTarget = true;
						}
						return nextTarget;

					}
				});
				return 4;
			case 26:// Blood burst
				player.setNextAnimation(new Animation(1979));
				attackTarget(getMultiAttackTargets(player), new MultiAttack() {

					private boolean nextTarget; // real target is first player on array

					@Override
					public boolean attack() {
						mage_hit_gfx = 376;
						base_mage_xp = 39;
						blood_spell = true;
						int damage = getRandomMagicMaxHit(player, 210);
						if (player.twofoldPerkActive && Utils.random(1, 3) == 2) {
							delayMagicHit(2, getMagicHit(player, damage));
						}
						delayMagicHit(2, getMagicHit(player, damage));
						if (!nextTarget) {
							if (damage == -1) {
								return false;
							}
							nextTarget = true;
						}
						return nextTarget;

					}
				});
				return 4;
			case 22:// Ice burst
				player.setNextGraphics(new Graphics(366));
				player.setNextAnimation(new Animation(1979));
				attackTarget(getMultiAttackTargets(player), new MultiAttack() {

					private boolean nextTarget; // real target is first player on array

					@Override
					public boolean attack() {
						long currentTime = Utils.currentTimeMillis();
						if (target.getSize() >= 2 || target.getCanFreezeThis() == false || target.getFreezeDelay() >= currentTime || target.getFrozenBlockedDelay() >= currentTime) {
							mage_hit_gfx = 367;
						} else {
							mage_hit_gfx = 367;
							freeze_time = 15000;
						}
						base_mage_xp = 46;
						magic_sound = 169;
						int damage = getRandomMagicMaxHit(player, 220);
						if (player.twofoldPerkActive && Utils.random(1, 3) == 2) {
							delayMagicHit(2, getMagicHit(player, damage));
						}
						delayMagicHit(4, getMagicHit(player, damage));
						World.sendProjectile(player, target, 366, 43, 0, 120, 0, 50, 64);
						if (!nextTarget) {
							if (damage == -1) {
								return false;
							}
							nextTarget = true;
						}
						return nextTarget;

					}
				});
				return 4;
			case 29:// Smoke Blitz
				player.setNextAnimation(new Animation(1978));
				mage_hit_gfx = 387;
				base_mage_xp = 42;
				max_poison_hit = 40;
				if (player.twofoldPerkActive && Utils.random(1, 3) == 2) {
					delayMagicHit(2, getMagicHit(player, getRandomMagicMaxHit(player, 230)));
				}
				delayMagicHit(2, getMagicHit(player, getRandomMagicMaxHit(player, 230)));
				World.sendProjectile(player, target, 386, 18, 18, 50, 50, 0, 0);
				return 4;
			case 33:// Shadow Blitz
				player.setNextAnimation(new Animation(1978));
				mage_hit_gfx = 381;
				base_mage_xp = 43;
				reduceAttack = true;
				if (player.twofoldPerkActive && Utils.random(1, 3) == 2) {
					delayMagicHit(2, getMagicHit(player, getRandomMagicMaxHit(player, 240)));
				}
				delayMagicHit(2, getMagicHit(player, getRandomMagicMaxHit(player, 240)));
				World.sendProjectile(player, target, 380, 18, 18, 50, 50, 0, 0);
				return 4;
			case 25:// Blood Blitz
				player.setNextAnimation(new Animation(1978));
				mage_hit_gfx = 375;
				base_mage_xp = 45;
				blood_spell = true;
				if (player.twofoldPerkActive && Utils.random(1, 3) == 2) {
					delayMagicHit(2, getMagicHit(player, getRandomMagicMaxHit(player, 250)));
				}
				delayMagicHit(2, getMagicHit(player, getRandomMagicMaxHit(player, 250)));
				World.sendProjectile(player, target, 374, 18, 18, 50, 50, 0, 0);
				return 4;
			case 21:// Ice Blitz
				player.setNextGraphics(new Graphics(366));
				player.setNextAnimation(new Animation(1978));
				long currentTime = Utils.currentTimeMillis();
				if (target.getSize() >= 2 || target.getCanFreezeThis() == false || target.getFreezeDelay() >= currentTime || target.getFrozenBlockedDelay() >= currentTime) {
					mage_hit_gfx = 367;
					// player.sendMessage("No freeze");
				} else {
					player.sendMessage("Freeze");
					mage_hit_gfx = 367;
					freeze_time = 15000;
				}
				// mage_hit_gfx = 367;
				base_mage_xp = 46;
				// freeze_time = 15000;
				magic_sound = 169;
				if (player.twofoldPerkActive && Utils.random(1, 3) == 2) {
					delayMagicHit(2, getMagicHit(player, getRandomMagicMaxHit(player, 260)));
				}
				delayMagicHit(2, getMagicHit(player, getRandomMagicMaxHit(player, 260)));
				World.sendProjectile(player, target, 368, 60, 32, 50, 50, 0, 0);
				return 4;
			case 31:// Smoke barrage
				player.setNextAnimation(new Animation(1979));
				attackTarget(getMultiAttackTargets(player), new MultiAttack() {

					private boolean nextTarget; // real target is first player on array

					@Override
					public boolean attack() {
						mage_hit_gfx = 391;
						base_mage_xp = 48;
						max_poison_hit = 40;
						int damage = getRandomMagicMaxHit(player, 270);
						if (player.twofoldPerkActive && Utils.random(1, 3) == 2) {
							delayMagicHit(2, getMagicHit(player, damage));
						}
						delayMagicHit(2, getMagicHit(player, damage));
						World.sendProjectile(player, target, 390, 18, 18, 50, 50, 0, 0);
						if (!nextTarget) {
							if (damage == -1) {
								return false;
							}
							nextTarget = true;
						}
						return nextTarget;

					}
				});
				return 4;
			case 35:// shadow barrage
				player.setNextAnimation(new Animation(1979));
				attackTarget(getMultiAttackTargets(player), new MultiAttack() {

					private boolean nextTarget; // real target is first player on array

					@Override
					public boolean attack() {
						mage_hit_gfx = 383;
						base_mage_xp = 49;
						reduceAttack = true;
						int damage = getRandomMagicMaxHit(player, 280);
						if (player.twofoldPerkActive && Utils.random(1, 3) == 2) {
							delayMagicHit(2, getMagicHit(player, damage));
						}
						delayMagicHit(2, getMagicHit(player, damage));
						if (!nextTarget) {
							if (damage == -1) {
								return false;
							}
							nextTarget = true;
						}
						return nextTarget;

					}
				});
				return 4;
			case 27:// blood barrage
				player.setNextAnimation(new Animation(1979));
				attackTarget(getMultiAttackTargets(player), new MultiAttack() {
					// player.setNextAnimation(new Animation(1979));
					private boolean nextTarget; // real target is first player on array

					@Override
					public boolean attack() {
						mage_hit_gfx = 377;
						base_mage_xp = 51;
						max_poison_hit = 40;
						blood_spell = true;
						int damage = getRandomMagicMaxHit(player, 290);
						if (player.twofoldPerkActive && Utils.random(1, 3) == 2) {
							delayMagicHit(2, getMagicHit(player, damage));
						}
						delayMagicHit(2, getMagicHit(player, damage));
						World.sendProjectile(player, target, -1, 18, 18, 50, 50, 0, 0);
						if (!nextTarget) {
							if (damage == -1) {
								return false;
							}
							nextTarget = true;
						}
						return nextTarget;

					}
				});
				return 4;
			case 23: // ice barrage
				player.setNextAnimation(new Animation(1979));
				playSound(171, player, target);
				attackTarget(getMultiAttackTargets(player), new MultiAttack() {

					private boolean nextTarget; // real target is first player on array

					@Override
					public boolean attack() {
						magic_sound = 168;
						long currentTime = Utils.currentTimeMillis();
						if (target.getSize() >= 2 || target.getCanFreezeThis() == false || target.getFreezeDelay() >= currentTime || target.getFrozenBlockedDelay() >= currentTime) {
							mage_hit_gfx = 1677;
						} else {
							mage_hit_gfx = 369;
							freeze_time = 20000;
						}
						base_mage_xp = 52;
						int damage = getRandomMagicMaxHit(player, 300);
						Hit hit = getMagicHit(player, damage);
						if (player.twofoldPerkActive && Utils.random(1, 3) == 2) {
							delayMagicHit(Utils.getDistance(player, target) > 3 ? 4 : 2, hit);
						}
						delayMagicHit(Utils.getDistance(player, target) > 3 ? 4 : 2, hit);
						World.sendProjectile(player, target, 368, 60, 32, 50, 50, 0, 0);
						if (!nextTarget) {
							if (damage == -1) {
								return false;
							}
							nextTarget = true;
						}
						return nextTarget;

					}
				});
				return 4;
			}

		}
		return -1; // stops atm
	}

	public interface MultiAttack {

		public boolean attack();

	}

	public void attackTarget(Entity[] targets, MultiAttack perform) {
		Entity realTarget = target;
		for (Entity t : targets) {
			target = t;
			if (!perform.attack()) {
				break;
			}
		}
		target = realTarget;
	}

	public int getRandomMagicMaxHit(Player player, int baseDamage) {
		int current = getMagicMaxHit(player, baseDamage);
		if (current <= 0) {
			return -1;
		}
		int hit = Utils.random(current + 1);
		if (hit > 0) {
			if (target instanceof NPC) {
				NPC n = (NPC) target;
				if (player.getEquipment().getAmmoId() == 29110 && Utils.random(6) == 0) {
					target.applyHit(new Hit(player, hit, HitLook.REGULAR_DAMAGE));
				}
				if (player.getEquipment().getAmmoId() == 29108 && Utils.random(6) == 0) {
					target.applyHit(new Hit(player, hit, HitLook.REGULAR_DAMAGE));
				}
				for (int bosspet : Settings.PETS_WITH_PERKS) {
					if (player.getPetManager().getNpcId() == bosspet && player.getPetPerk().getContainer().contains(new Item(29274))) {
						target.applyHit(new Hit(player, hit / 20, HitLook.REGULAR_DAMAGE));
					}
					if (player.getPetManager().getNpcId() == bosspet && player.getPetPerk().getContainer().contains(new Item(29273)) && Utils.random(11) == 0) {
						player.heal(hit / 4);
						// player.sendMessage("healed");
					}
					if (player.getPetManager().getNpcId() == bosspet && player.getPetPerk().getContainer().contains(new Item(29272)) && Utils.random(11) == 0) {
						player.getPrayer().restorePrayer(hit / 20);
						// player.sendMessage("restored");
					}
				}
				if (Settings.eventdoublehits > 0 && !(target instanceof Player)) {
					hit *= 2;
				}
				if (World.OsrsRaids(player) || World.TheatreofBlood(player)) {
					if (player.getEquipment().getShieldId() == 25353) {
						hit *= 1.5;
					}
				}
				if (World.OsrsRaids(player)) {
				
				if (player.osrsraiddamagepoints > 20000000) {
					player.osrsraiddamagepoints = 0;
				}
				player.osrsraiddamagepoints += hit;
				}
				player.assassineventdamage += hit;
				if (World.TheatreofBlood(player)) {
					if (player.theatreofblooddamagepoints > 20000000) {
						player.theatreofblooddamagepoints = 0;
					}
					player.theatreofblooddamagepoints += hit;
					
				}
				if (n.getId() == 9463 || n.getId() == 14301 || n.getId() == 9928 && hasFireCape(player)) {
					hit += 40;
				}
			}
		}
		return hit;
	}

	public boolean hasNoxStaff(Player player) {
		int weaponId = player.getEquipment().getWeaponId();
		return weaponId == 29783;
	}

	public boolean hasSliskeStaff(Player player) {
		int weaponId = player.getEquipment().getWeaponId();
		return weaponId == 29546;
	}

	public boolean hasAmuletofZaros(Player player) {
		int amuletId = player.getEquipment().getAmuletId();
		return amuletId == 29600;
	}

	public boolean hasSwand(Player player) {
		int weaponId = player.getEquipment().getWeaponId();
		return ItemDefinitions.getItemDefinitions(weaponId).getName().toLowerCase().startsWith("seismic wand");
	}
	public boolean hasDragonLance(Player player) {
		int weaponId = player.getEquipment().getWeaponId();
		return weaponId == 29170;
	}

	public boolean hasSorb(Player player) {
		int offhandId = player.getEquipment().getShieldId();
		return ItemDefinitions.getItemDefinitions(offhandId).getName().toLowerCase().startsWith("seismic singularity");
	}

	private int getMagicMaxHit(Player player, int baseDamage) {
		double EA = Math.round(player.getSkills().getLevel(Skills.MAGIC) * player.getPrayer().getMageMultiplier()) + 11;
		if (fullVoidEquipped(player, new int[] { 11663, 11674 })) {
			EA *= 1.3;
		}
		EA *= player.getAuraManager().getMagicAccurayMultiplier();
		double ED = 0, DB;
		if (target instanceof Player) {
			Player p2 = (Player) target;
			double EMD = Math.round(p2.getSkills().getLevel(Skills.MAGIC) * p2.getPrayer().getMageMultiplier()) + 8 + (player.getCombatDefinitions().isDefensiveCasting() ? 3 : 0);
			if (p2.getFamiliar() != null && (p2.getFamiliar().getId() == 6870 || p2.getFamiliar().getId() == 6871)) {
				EMD *= 1.05;
			}
			Math.round(EMD);
			double ERD = Math.round(p2.getSkills().getLevel(Skills.DEFENCE) * p2.getPrayer().getDefenceMultiplier()) + 8;
			EMD *= 0.7;
			ERD *= 0.3;
			ED = EMD + ERD;
			DB = p2.getCombatDefinitions().getBonuses()[CombatDefinitions.MAGIC_DEF];
		} else {
			NPC n = (NPC) target;
			DB = ED = n.getBonuses() != null ? n.getBonuses()[CombatDefinitions.MAGIC_DEF] / 1.6 : 0;
		}
		double A = EA * (1 + player.getCombatDefinitions().getBonuses()[CombatDefinitions.MAGIC_ATTACK]) / 64;
		double D = ED * (1 + DB) / 64;
		double accuracy = 0.05; // so a level 3 can hit a level 138
		if (A < D) {
			accuracy = (A - 1) / (2 * D);
		} else if (A > D) {
			accuracy = 1 - (D + 1) / (2 * A);
		}
		if (target instanceof NPC) {
			NPC dummy = (NPC) target;
			if (accuracy < Math.random() && dummy.getId() != 7891) {
				return 0;
			}
		}
		max_hit = baseDamage;
		double boost = 1 + (player.getSkills().getLevel(Skills.MAGIC) - player.getSkills().getLevelForXp(Skills.MAGIC)) * 0.03;
		if (boost > 1) {
			max_hit *= boost;
		}
		double magicPerc = player.getCombatDefinitions().getBonuses()[CombatDefinitions.MAGIC_DAMAGE];
		if (player.getTask() != null) {
			if (player.getTask() != null) {
				if (target instanceof NPC) {
					NPC n = (NPC) target;
					if (n.getDefinitions().name.toLowerCase().equalsIgnoreCase(player.getTask().getName().toLowerCase())) {
						if (hasSlayerHelm(player) && !hasFullSlayerHelm(player)) {
							baseDamage *= 1.125;
						} else if (hasFullSlayerHelm(player)) {
							baseDamage *= 1.145;
						} else if (hasCorruptedSlayerHelm(player)) {
							baseDamage *= 1.175;
						}else if (hasAncientSlayerHelm(player)) {
							baseDamage *= 1.2;
						}
					}
				}
			}
		}
		if (hasNoxStaff(player) || hasSliskeStaff(player)) {
			max_hit *= 1.19;
		}
		if (hasSwand(player) && hasSorb(player)) {
			max_hit *= 1.19;
		}
		if (spellcasterGloves > 0) {
			if (baseDamage > 60 || spellcasterGloves == 28 || spellcasterGloves == 25) {
				magicPerc += 17;
				if (target instanceof Player) {
					Player p = (Player) target;
					p.getSkills().drainLevel(0, p.getSkills().getLevel(0) / 10);
					p.getSkills().drainLevel(1, p.getSkills().getLevel(1) / 10);
					p.getSkills().drainLevel(2, p.getSkills().getLevel(2) / 10);
					p.getPackets().sendGameMessage("Your melee skills have been drained.");
					player.getPackets().sendGameMessage("Your spell weakened your enemy.");
				}
				player.getPackets().sendGameMessage("Your magic surged with extra power.");
			}
		}
		boost = magicPerc / 100 + 1;

		max_hit *= boost;
		// World.sendWorldMessage("" + Math.floor(max_hit) + boost + "", false);
		if (target instanceof NPC) {
			if (player.getEquipment().getWeaponId() == 29377 && Wilderness.isAtWild(player)) {
				max_hit += max_hit * 1.50;
			}
			if (player.getEquipment().getChestId() == 17231 && Wilderness.isAtWild(player)) {
				max_hit += max_hit * 0.10;
			}
			if (player.getEquipment().getLegsId() == 16859 && Wilderness.isAtWild(player)) {
				max_hit += max_hit * 0.10;
			}
			// System.out.,
			if (World.OsrsRaids(player) || World.TheatreofBlood(player)) {
				if (player.getEquipment().getShieldId() == 25353) {
					max_hit *= 1.5;
				}
			}
			if (Settings.eventdoublehits > 0 && !(target instanceof Player)) {
				max_hit *= 2;
			}
			NPC tg1 = (NPC) target;
			if (tg1.getId() == 7891) {
				tg1.setNextForceTalk(new ForceTalk(player.getDisplayName() + " your Magic max hit is currently <col=ff0000>" + Math.floor(max_hit)));
			}
		}
		return (int) Math.floor(max_hit);
	}

	/**
	 * Gets the magic accuracy.
	 * 
	 * @param e
	 *            The player.
	 * @param baseDamage
	 *            The base damage.
	 * @param extraDamage
	 *            The extra damage.
	 * @return The magic accuracy value.
	 */
	@SuppressWarnings("unused")
	private double getMagicAccuracy(Player e, int baseDamage, int extraDamage) {
		int magicLevel = e.getSkills().getLevel(Skills.MAGIC) + 1;
		int magicBonus = e.getCombatDefinitions().getBonuses()[CombatDefinitions.MAGIC_ATTACK];
		if (baseDamage << 2 < 60 && spellcasterGloves > 0 && spellcasterGloves != 28 && spellcasterGloves != 25) {
			magicBonus += 20;
		}
		double prayer = 1.0 * e.getPrayer().getMageMultiplier();
		double accuracy = (magicLevel + magicBonus * 4) * prayer;
		accuracy += extraDamage + baseDamage >> 1;
		if (fullVoidEquipped(e, 11663, 11674)) {
			accuracy *= 1.106;
		}

		return accuracy < 1 ? 1 : accuracy;
	}

	/**
	 * Gets the magic defence of an entity.
	 * 
	 * @param //e
	 *            The entity.
	 * @return The magic defence value.
	 */
	/*
	 * private double getMagicDefence(Entity e) { Player p = e instanceof Player ?
	 * (Player) e : null; int style = p != null ? 0 : 1; if (p != null) { int type =
	 * CombatDefinitions.getXpStyle(p.getEquipment().getWeaponId(),
	 * p.getCombatDefinitions().getAttackStyle()); style += type == Skills.DEFENCE ?
	 * 3 : type == CombatDefinitions.SHARED ? 1 : 0; } double defLvl = (p != null ?
	 * p.getSkills().getLevel(Skills.DEFENCE) : ((NPC) e).getCombatLevel() * 0.7) *
	 * 0.3; defLvl += (p != null ? p.getSkills().getLevel(Skills.MAGIC) : ((NPC)
	 * e).getCombatLevel() * 0.7) * 0.7; int defBonus = p != null ?
	 * p.getCombatDefinitions().getBonuses()[CombatDefinitions.MAGIC_DEF] : ((NPC)
	 * e).getBonuses() != null ? ((NPC) e).getBonuses()[CombatDefinitions.MAGIC_DEF]
	 * : 5; double defMult = 1.0; if (p != null) { defMult +=
	 * p.getPrayer().getDefenceMultiplier(); } double defence = ((defLvl + (defBonus
	 * << 2)) + style) * defMult; return defence < 1 ? 1 : defence; }
	 */

	private int rangeAttack(final Player player) {
		final int weaponId = player.getEquipment().getWeaponId();
		final int offHandAmmo = player.getEquipment().getShieldId();
		final int attackStyle = player.getCombatDefinitions().getAttackStyle();
		int combatDelay = getRangeCombatDelay(weaponId, attackStyle);
		int soundId = getSoundId(weaponId, attackStyle);
		int projectileSpeed = 46 + (Utils.getDistance(player.getX(), player.getY(), target.getX(), target.getY()) >= 5 ? 2 : 1 * 5);
		Item ring = player.getEquipment().getItem(Equipment.SLOT_RING);
		Item amulet = player.getEquipment().getItem(Equipment.SLOT_AMULET);
		Item shield = player.getEquipment().getItem(Equipment.SLOT_SHIELD);
		if (shield != null && target instanceof NPC) {
			if (shield.getDefinitions().getId() == 29527 && target instanceof NPC) {
				player.getPrayer().restorePrayer(3);
			}
		}
		if (amulet != null && target instanceof NPC) {
			if (amulet.getDefinitions().getName().equalsIgnoreCase("amulet of immense power") && target instanceof NPC) {
				// player.sendMessage("Working");
				NPC n = (NPC) target;
				Random soulchance = new Random();
				int soul = soulchance.nextInt(10);
				if (soul == 8) {
					player.heal(80);
					n.applyHit(new Hit(player, 80, HitLook.RANGE_DAMAGE));
				} else if (soul != 8) {
				}
			}
		}
		if (amulet != null && target instanceof NPC) {
			if (amulet.getDefinitions().getId() == 29732 || amulet.getDefinitions().getName().equalsIgnoreCase("amulet of souls") && target instanceof NPC) {
				// player.sendMessage("Working");
				NPC n = (NPC) target;
				Random soulchance = new Random();
				int soul = soulchance.nextInt(10);
				if (soul == 8) {
					player.heal(50);
					n.applyHit(new Hit(player, 50, HitLook.RANGE_DAMAGE));
				} else if (soul != 8) {
				}
			}
		}
		if (amulet != null && target instanceof NPC) {
			if (amulet != null && amulet.getDefinitions().getName().contains("Blood amulet of fury") && !amulet.getDefinitions().getName().contains("broken") && target instanceof NPC) {
				// player.sendMessage("Working");
				NPC n = (NPC) target;
				Random soulchance = new Random();
				int soul = soulchance.nextInt(10);
				if (soul == 8) {
					player.heal(80);
				} else if (soul != 8) {
				}
			}
		}
		if (offHandAmmo == 30575) {
			player.getEquipment().removeAmmo(offHandAmmo, 1);
			player.getEquipment().refresh(5);
			// player.getAppearence().generateAppearenceData();
		}
		if (ring != null && target instanceof NPC) {
			if (ring != null && ring.getDefinitions().getName().contains("of Death") && !ring.getDefinitions().getName().contains("broken") && target instanceof NPC) {
				NPC n = (NPC) target;
				Random damagechance = new Random();
				int dam = damagechance.nextInt(20);
				if (dam == 8) {
					n.applyHit(new Hit(player, n.getHitpoints() / (player.rodeffectincrease ? 10 : 20), HitLook.REGULAR_DAMAGE));
				} else if (dam != 8) {
				}
			}
			if (ring != null && ring.getDefinitions().getName().contains("Mein Fuhrer Ring") && target instanceof NPC) {
				NPC n = (NPC) target;
				Random damagechance = new Random();
				int dam = damagechance.nextInt(20);
				if (dam == 8) {
					n.applyHit(new Hit(player, n.getHitpoints() / (player.rodeffectincrease ? 10 : 20), HitLook.REGULAR_DAMAGE));
				} else if (dam != 8) {
				}
			}
			if (ring != null && ring.getDefinitions().getName().contains("Level 1 Ring") && !ring.getDefinitions().getName().contains("broken") && target instanceof NPC) {
				NPC n = (NPC) target;
				Random damagechance = new Random();
				int dam = damagechance.nextInt(20);
				if (dam == 8) {
					n.applyHit(new Hit(player, n.getHitpoints() / (player.rodeffectincrease ? 10 : 20), HitLook.REGULAR_DAMAGE));
				} else if (dam != 8) {
				}
			}
			if (ring != null && ring.getDefinitions().getName().contains("Raptor's ring")) {
				Random damagechance = new Random();
				int dam = damagechance.nextInt(20);
				if (dam == 8) {
					player.heal(player.getHitpoints() / 10);
				} else if (dam != 8) {
				}
			}
			if (ring != null && ring.getDefinitions().getName().contains("Immense power") && !ring.getDefinitions().getName().contains("broken") && target instanceof NPC) {
				NPC n = (NPC) target;
				Random damagechance = new Random();
				int dam = damagechance.nextInt(20);
				if (dam == 8) {
					n.applyHit(new Hit(player, n.getHitpoints() / (player.rodeffectincrease ? 10 : 20), HitLook.REGULAR_DAMAGE));
				} else if (dam != 8) {
				}
			}
			if (ring != null && ring.getDefinitions().getName().contains("Pussy Slapper") && !ring.getDefinitions().getName().contains("broken") && target instanceof NPC) {
				NPC n = (NPC) target;
				Random damagechance = new Random();
				int dam = damagechance.nextInt(20);
				if (dam == 8) {
					n.applyHit(new Hit(player, n.getHitpoints() / (player.rodeffectincrease ? 10 : 20), HitLook.REGULAR_DAMAGE));
				} else if (dam != 8) {
				}
			}
		}

		if (hasAscBothHand(player)) {
			int chance;
			chance = Utils.random(5);
			switch (chance) {
			case 2:
				int damage = getRandomMaxHit(player, weaponId, attackStyle, true, true, weaponId == 10034 ? 1.2 : 1.0, true);
				delayHit(1, weaponId, attackStyle, getRangeHit(player, damage));
				break;
			default:
				break;
			}
		}
		// if (weaponId == 29472) {
		// int chance;
		// chance = Utils.random(5);
		// switch (chance) {
		// case 2:
		// int damage = getRandomMaxHit(player, weaponId,
		// attackStyle, true, true,
		// weaponId == 29472 ? 1.2 : 1.0, true);
		// delayHit(1, weaponId, attackStyle,
		// getRangeHit(player, damage));
		// break;
		// default:
		// break;
		// }
		// }
		if (weaponId == 29547) { //seren godbow
			player.setNextGraphics(new Graphics(2962, 0, 100));
			player.setNextGraphics(new Graphics(2962, 0, 91));
			int hit = getRandomMaxHit(player, weaponId, attackStyle, true);
			delayHit(2, weaponId, attackStyle, getRangeHit(player, hit));
			World.sendProjectile(player, target, 1066, 37, 32, projectileSpeed - 5, 35, 16, 0);
		}
		if (weaponId == 10034) {
			attackTarget(getMultiAttackTargets(player), new MultiAttack() {

				private boolean nextTarget;

				@Override
				public boolean attack() {
					int damage = getRandomMaxHit(player, weaponId, attackStyle, true, true, weaponId == 10034 ? 1.2 : 1.0, true);
					player.setNextAnimation(new Animation(2779));
					World.sendProjectile(player, target, weaponId == 10034 ? 909 : 908, 41, 16, 31, 35, 16, 0);
					delayHit(1, weaponId, attackStyle, getRangeHit(player, damage));
					player.getEquipment().removeAmmo(weaponId, 1);
					player.getEquipment().refresh(3);
					player.getAppearence().generateAppearenceData();
					WorldTasksManager.schedule(new WorldTask() {
						@Override
						public void run() {
							player.setNextGraphics(new Graphics(2739, 0, 96 << 16));
						}
					}, 3);
					if (!nextTarget) {
						if (damage == -1) {
							return false;
						}
						nextTarget = true;
					}
					return nextTarget;

				}
			});
			return combatDelay;
		}
		if (weaponId == 29448) {
			attackTarget(getMultiAttackTargets(player), new MultiAttack() {

				private boolean nextTarget;

				@Override
				public boolean attack() {
					int damage = getRandomMaxHit(player, weaponId, attackStyle, true, true, weaponId == 29448 ? 1.2 : 1.0, true);
					player.setNextAnimation(new Animation(2779));
					World.sendProjectile(player, target, weaponId == 29448 ? 909 : 908, 41, 16, 31, 35, 16, 0);
					delayHit(1, weaponId, attackStyle, getRangeHit(player, damage));
					player.getEquipment().removeAmmo(weaponId, 1);
					player.getEquipment().refresh(3);
					player.getAppearence().generateAppearenceData();
					WorldTasksManager.schedule(new WorldTask() {
						@Override
						public void run() {
							player.setNextGraphics(new Graphics(2739, 0, 96 << 16));
						}
					}, 3);
					if (!nextTarget) {
						if (damage == -1) {
							return false;
						}
						nextTarget = true;
					}
					return nextTarget;

				}
			});
			return combatDelay;
		}
		if (player.getCombatDefinitions().isUsingSpecialAttack()) {
			int specAmt = getSpecialAmmount(weaponId);
			if (specAmt == 0) {
				player.getPackets().sendGameMessage("This weapon has no special Attack, if you still see special bar please relogin.");
				player.getCombatDefinitions().desecreaseSpecialAttack(0);
				return combatDelay;
			}
			if (player.getCombatDefinitions().hasRingOfVigour()) {
				specAmt *= 0.9;
			}
			if (player.getCombatDefinitions().getSpecialAttackPercentage() < specAmt) {
				player.getPackets().sendGameMessage("You don't have enough power left.");
				player.getCombatDefinitions().desecreaseSpecialAttack(0);
				return combatDelay;
			}
			player.getCombatDefinitions().desecreaseSpecialAttack(specAmt);
			switch (weaponId) {
			case 29782:
				if (target instanceof Player) {
					player.sendMessage(Colors.red+"You cannot use this special attack against a player!");
					break;
				}
				player.setNextAnimation(new Animation(426));
				player.setNextGraphics(new Graphics(94));
				World.sendProjectile(player, target, 100, 41, 16, projectileSpeed, 35, 16, 0);
				delayHit(1, weaponId, attackStyle, getRangeHit(player, getRandomMaxHit(player, weaponId, attackStyle, true, true, 1.4, true)));
				delayHit(1, weaponId, attackStyle, getRangeHit(player, getRandomMaxHit(player, weaponId, attackStyle, true, true, 1.4, true)));
				delayHit(1, weaponId, attackStyle, getRangeHit(player, getRandomMaxHit(player, weaponId, attackStyle, true, true, 1.4, true)));
				// dropAmmo(player, 3);
				break;
			case 29547: // seren godbow
				if (player.isCanPvp()) {
					player.sendMessage("You cannot use this special attack in a pvp zone.");
					break;
				}
				WorldTasksManager.schedule(new WorldTask() {
					int loop = 0;

					@Override
					public void run() {
						if ((target.isDead() || player.isDead() || loop > 4) && !World.getNPCs().contains(target)) {
							stop();
							return;
						}
						if (loop == 0) {
							target.setNextGraphics(new Graphics(92));
							player.setNextAnimation(new Animation(426));
							World.sendProjectile(player, target, 100, 41, 16, projectileSpeed, 35, 16, 0);
							delayHit(1, weaponId, attackStyle, getRangeHit(player, getRandomMaxHit(player, weaponId, attackStyle, true, true, 1.0, true)));
						} else if (loop == 1) {
							player.setNextAnimation(new Animation(426));
							World.sendProjectile(player, target, 100, 41, 16, projectileSpeed, 35, 16, 0);
							delayHit(1, weaponId, attackStyle, getRangeHit(player, getRandomMaxHit(player, weaponId, attackStyle, true, true, 1.0, true)));
						} else if (loop == 2) {
							player.setNextAnimation(new Animation(426));
							World.sendProjectile(player, target, 100, 41, 16, projectileSpeed, 35, 16, 0);
							delayHit(1, weaponId, attackStyle, getRangeHit(player, getRandomMaxHit(player, weaponId, attackStyle, true, true, 1.0, true)));
						} else if (loop == 3) {
							player.setNextAnimation(new Animation(426));
							World.sendProjectile(player, target, 100, 41, 16, projectileSpeed, 35, 16, 0);
							delayHit(1, weaponId, attackStyle, getRangeHit(player, getRandomMaxHit(player, weaponId, attackStyle, true, true, 1.0, true)));
						} else if (loop == 4) {
							player.setNextAnimation(new Animation(426));
							World.sendProjectile(player, target, 100, 41, 16, projectileSpeed, 35, 16, 0);
							delayHit(1, weaponId, attackStyle, getRangeHit(player, getRandomMaxHit(player, weaponId, attackStyle, true, true, 1.0, true)));
							stop();
						}
						loop++;
					}
				}, 0, 1);

				//dropAmmo(player, 5);
				break;
			case 29594:
				World.sendProjectile(player, target, 211, 41, 16, 25, 35, 16, 0);
				player.setNextAnimation(new Animation(2075));
				delayHit(5, weaponId, attackStyle, getRangeHit(player, getRandomMaxHit(player, weaponId, attackStyle, true, true, 1.25, true)));
				break;
			case 29916: // donator bow
			case 29816:
				player.setNextAnimation(new Animation(426));
				player.setNextGraphics(new Graphics(94));
				World.sendProjectile(player, target, 100, 41, 16, 25, 35, 16, 0);
				delayHit(1, weaponId, attackStyle, getRangeHit(player, getRandomMaxHit(player, weaponId, attackStyle, true, true, 1.3, true)));
				delayHit(1, weaponId, attackStyle, getRangeHit(player, getRandomMaxHit(player, weaponId, attackStyle, true, true, 1.2, true)));
				delayHit(1, weaponId, attackStyle, getRangeHit(player, getRandomMaxHit(player, weaponId, attackStyle, true, true, 1.0, true)));
				dropAmmo(player, 3);
				break;
			case 19149:// zamorak bow
			case 19151:
				player.setNextAnimation(new Animation(426));
				player.setNextGraphics(new Graphics(97));
				World.sendProjectile(player, target, 100, 41, 16, projectileSpeed, 35, 16, 0);
				delayHit(1, weaponId, attackStyle, getRangeHit(player, getRandomMaxHit(player, weaponId, attackStyle, true, true, 1.0, true)));
				dropAmmo(player, 1);
				break;
			case 19146:
			case 19148:// guthix bow
				player.setNextAnimation(new Animation(426));
				player.setNextGraphics(new Graphics(95));
				World.sendProjectile(player, target, 98, 41, 16, projectileSpeed, 35, 16, 0);
				delayHit(1, weaponId, attackStyle, getRangeHit(player, getRandomMaxHit(player, weaponId, attackStyle, true, true, 1.0, true)));
				dropAmmo(player, 1);
				break;
			case 19143:// saradomin bow
			case 19145:
				player.setNextAnimation(new Animation(426));
				player.setNextGraphics(new Graphics(96));
				World.sendProjectile(player, target, 99, 41, 16, projectileSpeed, 35, 16, 0);
				delayHit(1, weaponId, attackStyle, getRangeHit(player, getRandomMaxHit(player, weaponId, attackStyle, true, true, 1.0, true)));
				dropAmmo(player, 1);
				break;

			case 859: // magic longbow
			case 861: // magic shortbow
			case 10284: // Magic composite bow
			case 18332: // Magic longbow (sighted)
				player.setNextAnimation(new Animation(1074));
				player.setNextGraphics(new Graphics(249, 0, 100));
				World.sendProjectile(player, target, 249, 41, 16, 31, 35, 16, 0);
				World.sendProjectile(player, target, 249, 41, 16, 25, 35, 21, 0);
				delayHit(2, weaponId, attackStyle, getRangeHit(player, getRandomMaxHit(player, weaponId, attackStyle, true, true, 1.0, true)));
				delayHit(3, weaponId, attackStyle, getRangeHit(player, getRandomMaxHit(player, weaponId, attackStyle, true, true, 1.0, true)));
				dropAmmo(player, 2);
				break;
			case 15241: // Hand cannon
			case 29368:
				WorldTasksManager.schedule(new WorldTask() {
					int loop = 0;

					@Override
					public void run() {
						if ((target.isDead() || player.isDead() || loop > 1) && !World.getNPCs().contains(target)) {
							stop();
							return;
						}
						if (loop == 0) {
							player.setNextAnimation(new Animation(12174));
							player.setNextGraphics(new Graphics(2138));
							World.sendProjectile(player, target, 2143, 18, 18, 50, 50, 0, 0);
							delayHit(1, weaponId, attackStyle, getRangeHit(player, getRandomMaxHit(player, weaponId, attackStyle, true, true, 1.0, true)));
						} else if (loop == 1) {
							player.setNextAnimation(new Animation(12174));
							player.setNextGraphics(new Graphics(2138));
							World.sendProjectile(player, target, 2143, 18, 18, 50, 50, 0, 0);
							delayHit(1, weaponId, attackStyle, getRangeHit(player, getRandomMaxHit(player, weaponId, attackStyle, true, true, 1.0, true)));
							stop();
						}
						loop++;
					}
				}, 0, (int) 0.25);
				combatDelay = 9;
				break;
			case 11235: // dark bows
			case 15701:
			case 15702:
			case 15703:
			case 15704:
				int ammoId = player.getEquipment().getAmmoId();
				player.setNextAnimation(new Animation(getWeaponAttackEmote(weaponId, attackStyle, player)));
				player.setNextGraphics(new Graphics(getArrowThrowGfxId(weaponId, ammoId), 0, 100));
				if (ammoId == 11212 || ammoId == 14545) {
					int damage = getRandomMaxHit(player, weaponId, attackStyle, true, true, 1.5, true);
					if (damage < 80) {
						damage = 80;
					}
					int damage2 = getRandomMaxHit(player, weaponId, attackStyle, true, true, 1.5, true);
					if (damage2 < 80) {
						damage2 = 80;
					}
					World.sendProjectile(player, target, 1099, 41, 16, 31, 35, 16, 0);
					World.sendProjectile(player, target, 1099, 41, 16, 25, 35, 21, 0);
					delayHit(2, weaponId, attackStyle, getRangeHit(player, damage));
					delayHit(3, weaponId, attackStyle, getRangeHit(player, damage2));
					WorldTasksManager.schedule(new WorldTask() {
						@Override
						public void run() {
							target.setNextGraphics(new Graphics(1100, 0, 100));
						}
					}, 2);
				} else {
					int damage = getRandomMaxHit(player, weaponId, attackStyle, true, true, 1.3, true);
					if (damage < 50) {
						damage = 50;
					}
					int damage2 = getRandomMaxHit(player, weaponId, attackStyle, true, true, 1.3, true);
					if (damage2 < 50) {
						damage2 = 50;
					}
					World.sendProjectile(player, target, 1101, 41, 16, 31, 35, 16, 0);
					World.sendProjectile(player, target, 1101, 41, 16, 25, 35, 21, 0);
					delayHit(2, weaponId, attackStyle, getRangeHit(player, damage));
					delayHit(3, weaponId, attackStyle, getRangeHit(player, damage2));
				}
				dropAmmo(player, 2);

				break;
			case 14684: // zanik cbow
				player.setNextAnimation(new Animation(getWeaponAttackEmote(weaponId, attackStyle, player)));
				player.setNextGraphics(new Graphics(1714));
				World.sendProjectile(player, target, 2001, 41, 41, 41, 35, 0, 0);
				delayHit(2, weaponId, attackStyle, getRangeHit(player, getRandomMaxHit(player, weaponId, attackStyle, true, true, 1.0, true) + 30 + Utils.getRandom(120)));
				dropAmmo(player);
				break;
			case 13954:// morrigan javelin
			case 12955:
			case 13956:
			case 13879:
			case 13880:
			case 13881:
			case 13882:
				player.setNextGraphics(new Graphics(1836));
				player.setNextAnimation(new Animation(10501));
				World.sendProjectile(player, target, 1837, 41, 41, 41, 35, 0, 0);
				final int hit = getRandomMaxHit(player, weaponId, attackStyle, true, true, 1.0, true);
				delayHit(2, weaponId, attackStyle, getRangeHit(player, hit));
				if (hit > 0) {
					final Entity finalTarget = target;
					WorldTasksManager.schedule(new WorldTask() {
						int damage = hit;

						@Override
						public void run() {
							if (finalTarget.isDead() || finalTarget.hasFinished()) {
								stop();
								return;
							}
							if (damage > 50) {
								damage -= 50;
								finalTarget.applyHit(new Hit(player, 50, HitLook.REGULAR_DAMAGE));
							} else {
								finalTarget.applyHit(new Hit(player, damage, HitLook.REGULAR_DAMAGE));
								stop();
							}
						}
					}, 4, 2);
				}
				dropAmmo(player, -1);
				break;
			case 13883:
			case 13957:// morigan thrown axe
				player.setNextGraphics(new Graphics(1838));
				player.setNextAnimation(new Animation(10504));
				World.sendProjectile(player, target, 1839, 41, 41, 41, 35, 0, 0);
				delayHit(2, weaponId, attackStyle, getRangeHit(player, getRandomMaxHit(player, weaponId, attackStyle, true, true, 1.0, true)));
				dropAmmo(player, -1);
				break;
			default:
				player.getPackets().sendGameMessage("This weapon has no special Attack, if you still see special bar please relogin.");
				return combatDelay;
			}
		} else {
			if (weaponId != -1) {
				String weaponName = ItemDefinitions.getItemDefinitions(weaponId).getName().toLowerCase();
				// int ammoId1 = player.getEquipment().getAmmoId();
				if (weaponName.contains("throwing axe") || weaponName.contains("knife") || weaponName.contains("voraganic shard") || weaponName.contains("dart") || weaponName.contains("javelin") || weaponName.contains("thrownaxe")) {
					if (!weaponName.contains("javelin") && !weaponName.contains("thrownaxe")) {
						player.setNextGraphics(new Graphics(getKnifeThrowGfxId(weaponId), 0, 100));
					}
					World.sendProjectile(player, target, getKnifeThrowGfxId(weaponId), 41, 36, 41, 32, 15, 0);
					int hit = getRandomMaxHit(player, weaponId, attackStyle, true);
					delayHit(1, weaponId, attackStyle, getRangeHit(player, hit));
					checkSwiftGlovesEffect(player, 1, attackStyle, weaponId, hit, getKnifeThrowGfxId(weaponId), 41, 36, 41, 32, 15, 0);
					if (player.twofoldPerkActive && Utils.random(1, 3) == 2) {
						delayHit(1, weaponId, attackStyle, getRangeHit(player, getRandomMaxHit(player, weaponId, attackStyle, true)));
					}
					dropAmmo(player, -1);
					if (weaponName.contains("deathtouched dart")) {
						if (target instanceof Player) {
							Player p2 = (Player) target;
							p2.stopAll();

						} else {
							NPC n = (NPC) target;
							int oldcap = n.getCapDamage();
							World.sendProjectile(player, n, 3429, 41, 36, 50, 0, 15, 0);
							n.setNextGraphics(new Graphics(3428));
							n.setCapDamage(200000);
							n.applyHit(new Hit(player, n.getHitpoints(), HitLook.CRITICAL_DAMAGE));
							player.getAppearence().generateAppearenceData();
							n.setCapDamage(oldcap);

						}
					}

				} else if (weaponName.contains("crossbow")) {
					int damage = 0;
					int ammoId = player.getEquipment().getAmmoId();
					if (ammoId != -1 && Utils.getRandom(10) == 5) {
						switch (ammoId) {
						case 9237:
							damage = getRandomMaxHit(player, weaponId, attackStyle, true);
							target.setNextGraphics(new Graphics(755));
							if (target instanceof Player) {
								Player p2 = (Player) target;
								p2.stopAll();
							} else {
								NPC n = (NPC) target;
								n.setTarget(null);
							}
							soundId = 2914;
							break;
						case 9242:

							if (player.getEquipment().getWeaponId() == 29967 || player.getEquipment().getWeaponId() == 29806 || player.getEquipment().getWeaponId() == 29149) {
								max_hit = Short.MAX_VALUE;
								damage = (int) (target.getHitpoints() * 0.2);
								target.setNextGraphics(new Graphics(754));
								rubySpec = true;
								/*
								 * player.applyHit(new Hit(target, player .getHitpoints() > 20 ? (int) (player
								 * .getHitpoints() * 0.1) : 1, HitLook.REFLECTED_DAMAGE));
								 */
								soundId = 2912;
								break;
							} else {
								max_hit = Short.MAX_VALUE;
								damage = (int) (target.getHitpoints() * 0.2);
								target.setNextGraphics(new Graphics(754));
								player.applyHit(new Hit(target, player.getHitpoints() > 20 ? (int) (player.getHitpoints() * 0.1) : 1, HitLook.REFLECTED_DAMAGE));
								rubySpec = true;
								soundId = 2912;
								break;
							}
						case 9243:
							damage = getRandomMaxHit(player, weaponId, attackStyle, true, false, 1.15, true);
							target.setNextGraphics(new Graphics(751));
							soundId = 2913;
							break;
						case 29616:
							damage = getRandomMaxHit(player, weaponId, attackStyle, true, false, 1.0, true);
							target.getPoison().makePoisoned(52);
							target.setNextGraphics(new Graphics(367));
							target.addFreezeDelay(6000, true);
							player.sendMessage("Your bolt explodes on impact causing your target to freeze and become poisoned.");
							break;
						case 9244:
							damage = getRandomMaxHit(player, weaponId, attackStyle, true, false, !Combat.hasAntiDragProtection(target) ? 1.45 : 1.0, true);
							target.setNextGraphics(new Graphics(756));
							soundId = 2915;
							break;
						case 9245:
							damage = getRandomMaxHit(player, weaponId, attackStyle, true, false, 1.15, true);
							target.setNextGraphics(new Graphics(753));
							player.heal((int) (player.getMaxHitpoints() * 0.25));
							soundId = 2917;
							break;
						default:
							damage = getRandomMaxHit(player, weaponId, attackStyle, true);
						}
					} else {
						damage = getRandomMaxHit(player, weaponId, attackStyle, true);
						checkSwiftGlovesEffect(player, 2, attackStyle, weaponId, damage, 27, 38, 36, 41, 32, 5, 0);
						if (player.twofoldPerkActive && Utils.random(1, 3) == 2) {
							delayHit(2, weaponId, attackStyle, getRangeHit(player, getRandomMaxHit(player, weaponId, attackStyle, true)));
						}
					}
					World.sendProjectile(player, target, 27, 38, 36, 41, 32, 5, 0);
					delayHit(2, weaponId, attackStyle, getRangeHit(player, damage));
					if (weaponId == 29594) {
						// if (weaponName.contains("heavy ballista")) {
						World.sendProjectile(player, target, getKnifeThrowGfxId(211), 41, 36, 41, 32, 15, 0);
						// }
					}

					if (weaponId != 4740) {
						dropAmmo(player);
					} else {
						player.getEquipment().removeAmmo(ammoId, 1);
					}
				} else if (weaponId == 15241 || weaponId == 29368) {// handcannon29368
					/*if (Utils.getRandom(player.getSkills().getLevel(Skills.FIREMAKING) << 1) == 0 && weaponId == 15241) {
						// explode
						player.setNextGraphics(new Graphics(2140));
						player.getEquipment().getItems().set(3, null);
						player.getEquipment().refresh(3);
						player.getAppearence().generateAppearenceData();
						player.applyHit(new Hit(player, Utils.getRandom(150) + 10, HitLook.REGULAR_DAMAGE));
						player.setNextAnimation(new Animation(12175));
						return combatDelay;
					} else {*/
					player.setNextGraphics(new Graphics(2138));
					World.sendProjectile(player, target, 2143, 18, 18, 60, 30, 0, 0);
					delayHit(1, weaponId, attackStyle, getRangeHit(player, getRandomMaxHit(player, weaponId, attackStyle, true)));
					if (weaponId == 15421) {
						dropAmmo(player, -2);
					}
				} else if (weaponName.contains("crystal bow")) {
					player.setNextAnimation(new Animation(getWeaponAttackEmote(weaponId, attackStyle, player)));
					player.setNextGraphics(new Graphics(250, 0, 100));
					World.sendProjectile(player, target, 249, 41, 36, projectileSpeed, 35, 0, 0);
					int hit = getRandomMaxHit(player, weaponId, attackStyle, true);
					delayHit(2, weaponId, attackStyle, getRangeHit(player, hit));
					checkSwiftGlovesEffect(player, 2, attackStyle, weaponId, hit, 249, 41, 36, 41, 35, 0, 0);
					if (player.twofoldPerkActive && Utils.random(1, 3) == 2) {
						delayHit(2, weaponId, attackStyle, getRangeHit(player, getRandomMaxHit(player, weaponId, attackStyle, true)));
					}
				} else if (weaponId == 21365) { // Bolas
					if (!player.isDonator() && !player.isExtremeDonator()) {
						player.getPackets().sendGameMessage("You have to be donator to be using Bolas.");
						return combatDelay;
					}
					dropAmmo(player, -3);
					player.setNextAnimation(new Animation(3128));
					World.sendProjectile(player, target, 468, 41, 41, 41, 35, 0, 0);
					int delay = 15000;
					if (target instanceof Player) {
						Player p = (Player) target;
						Item weapon = p.getEquipment().getItem(3);
						boolean slashBased = weapon != null;
						if (weapon != null) {
							int slash = p.getCombatDefinitions().getBonuses()[CombatDefinitions.SLASH_ATTACK];
							for (int i = 0; i < 5; i++) {
								if (p.getCombatDefinitions().getBonuses()[i] > slash) {
									slashBased = false;
									break;
								}
							}
						}
						if (p.getInventory().containsItem(946, 1) || slashBased) {
							delay /= 2;
						}
						if (p.getPrayer().usingPrayer(0, 18) || p.getPrayer().usingPrayer(1, 8)) {
							delay /= 2;
						}
						if (delay < 5000) {
							delay = 5000;
						}
					}
					long currentTime = Utils.currentTimeMillis();
					if (getRandomMaxHit(player, weaponId, attackStyle, true) > 0 && target.getFrozenBlockedDelay() < currentTime) {
						target.addFreezeDelay(delay, true);
						WorldTasksManager.schedule(new WorldTask() {
							@Override
							public void run() {
								target.setNextGraphics(new Graphics(469, 0, 96));
							}
						}, 2);
					}
					playSound(soundId, player, target);
					return combatDelay;
				} else { // bow/default
					final int ammoId = player.getEquipment().getAmmoId();
					player.setNextGraphics(new Graphics(getArrowThrowGfxId(weaponId, ammoId), 0, 100));
					World.sendProjectile(player, target, getArrowProjectileGfxId(weaponId, ammoId), 41, 36, projectileSpeed, 35, 16, 0);
					// System.out.println("" + getArrowProjectileGfxId(weaponId, ammoId));
					int hit = getRandomMaxHit(player, weaponId, attackStyle, true);
					delayHit(2, weaponId, attackStyle, getRangeHit(player, hit));
					checkSwiftGlovesEffect(player, 2, attackStyle, weaponId, hit, getArrowProjectileGfxId(weaponId, ammoId), 41, 36, 20, 35, 16, 0);
					if (player.twofoldPerkActive && Utils.random(1, 3) == 2) {
						delayHit(2, weaponId, attackStyle, getRangeHit(player, getRandomMaxHit(player, weaponId, attackStyle, true)));
					}
					if (weaponId == 11235 || weaponId == 15701 || weaponId == 15702 || weaponId == 15703 || weaponId == 15704) { // dbows
						World.sendProjectile(player, target, getArrowProjectileGfxId(weaponId, ammoId), 41, 35, projectileSpeed, 35, 21, 0);

						delayHit(3, weaponId, attackStyle, getRangeHit(player, getRandomMaxHit(player, weaponId, attackStyle, true)));
						dropAmmo(player, 2);
					} else {
						if (weaponId != -1) {
							if (!weaponName.endsWith("bow full") && !weaponName.equals("zaryte bow") && !weaponName.equals("toxic blowpipe") && !weaponName.equals("mutated toxic blowpipe") && !weaponName.equals("crystal bow full") && !weaponName.equals("craw's bow") && !weaponName.equals("noxious bow") && !weaponName.equals("seren godbow") && !weaponName.equals("sagittarian longbow") && !weaponName.equals("strykebow")) {
								dropAmmo(player);
							}
						}
					}
				}

				player.setNextAnimation(new Animation(getWeaponAttackEmote(weaponId, attackStyle, player)));
			}
		}
		playSound(soundId, player, target);
		return combatDelay;
	}

	/**
	 * Handles the swift gloves effect.
	 * 
	 * @param player
	 *            The player.
	 * @param hitDelay
	 *            The delay before hitting the target.
	 * @param attackStyle
	 *            The attack style used.
	 * @param weaponId
	 *            The weapon id.
	 * @param hit
	 *            The hit done.
	 * @param gfxId
	 *            The gfx id.
	 * @param startHeight
	 *            The start height of the original projectile.
	 * @param endHeight
	 *            The end height of the original projectile.
	 * @param speed
	 *            The speed of the original projectile.
	 * @param delay
	 *            The delay of the original projectile.
	 * @param curve
	 *            The curve of the original projectile.
	 * @param startDistanceOffset
	 *            The start distance offset of the original projectile.
	 */
	private void checkSwiftGlovesEffect(Player player, int hitDelay, int attackStyle, int weaponId, int hit, int gfxId, int startHeight, int endHeight, int speed, int delay, int curve, int startDistanceOffset) {
		// System.out.println("2");
		int projectileSpeed = 46 + (Utils.getDistance(player.getX(), player.getY(), target.getX(), target.getY()) >= 5 ? 2 : 1 * 5);
		if (hit > 0 && Utils.getRandom(30) < 29) {
			return;
		}
		Item gloves = player.getEquipment().getItem(Equipment.SLOT_HANDS);
		// System.out.println("hi");
		if (gloves == null || !gloves.getDefinitions().getName().contains("Swift glove") && !gloves.getDefinitions().getName().contains("Donator glove") && !gloves.getDefinitions().getName().contains("Google glove")) {
			return;
		}
		// System.out.println("no");
		World.sendProjectile(player, target, gfxId, startHeight - 5, endHeight - 5, projectileSpeed, delay, curve - 5 < 0 ? 0 : curve - 5, startDistanceOffset);
		delayHit(hitDelay, weaponId, attackStyle, getRangeHit(player, getRandomMaxHit(player, weaponId, attackStyle, true)));
	}

	public void dropAmmo(Player player, int quantity) {
		if (quantity == -2) {
			final int ammoId = player.getEquipment().getAmmoId();
			player.getEquipment().removeAmmo(ammoId, 1);
		} else if (quantity == -1 || quantity == -3) {
			final int weaponId = player.getEquipment().getWeaponId();
			if (weaponId != -1) {
				if (quantity == -3 && Utils.getRandom(10) < 2 || quantity != -3 && Utils.getRandom(3) > 0) {
					int capeId = player.getEquipment().getCapeId();
					if (weaponId == 25202) {
						player.getEquipment().removeAmmo(weaponId, quantity);
						player.sendMessage("<col=ff8657>You use up one of your deathtouched darts!");
						player.deathtouchdartsused++;
						return;
					}
					if (capeId != -1 && ItemDefinitions.getItemDefinitions(capeId).getName().contains("Ava's") || capeId == 20767 || capeId == 29981 || capeId == 20771 || capeId == 20769 || capeId == 29717 || capeId == 29718 || capeId == 29719 || capeId == 29720 || capeId == 29056 || weaponId == 25202 || player.avaperk == true) {
						return; // nothing happens
					}
				} else {
					player.getEquipment().removeAmmo(weaponId, quantity);
					return;
				}

				player.getEquipment().removeAmmo(weaponId, quantity);
				World.updateGroundItem(new Item(weaponId, quantity), new WorldTile(target.getCoordFaceX(target.getSize()), target.getCoordFaceY(target.getSize()), target.getPlane()), player);
			}
		} else {
			final int ammoId = player.getEquipment().getAmmoId();
			if (Utils.getRandom(3) > 0) {
				int capeId = player.getEquipment().getCapeId();
				if (capeId != -1 && ItemDefinitions.getItemDefinitions(capeId).getName().contains("Ava's") || capeId == 29981 || capeId == 20771 || capeId == 20767 || capeId == 20769 || capeId == 29717 || capeId == 29718 || capeId == 29719 || capeId == 29720 || capeId == 29056 || player.avaperk == true) {
				}
			} else {
				player.getEquipment().removeAmmo(ammoId, quantity);
				return;
			}
			if (ammoId != -1) {
				int capeId = player.getEquipment().getCapeId();
				if (capeId != -1 && ItemDefinitions.getItemDefinitions(capeId).getName().contains("Ava's") || capeId == 29981 || capeId == 20771 || capeId == 20767 || capeId == 20769 || capeId == 29717 || capeId == 29718 || capeId == 29719 || capeId == 29720 || capeId == 29056 || player.avaperk == true) {
					return; // nothing happens
				} else {
				player.getEquipment().removeAmmo(ammoId, quantity);
				World.updateGroundItem(new Item(ammoId, quantity), new WorldTile(target.getCoordFaceX(target.getSize()), target.getCoordFaceY(target.getSize()), target.getPlane()), player);
				}
			}
		}
	}

	public void dropAmmo(Player player) {
		dropAmmo(player, 1);
	}

	public int getArrowThrowGfxId(int weaponId, int arrowId) {
		if (weaponId == 24474) {
			return 3195;
		} else if (arrowId == 884) {
			return 18;
		} else if (arrowId == 886) {
			return 20;
		} else if (arrowId == 888) {
			return 21;
		} else if (arrowId == 890) {
			return 22;
		} else if (arrowId == 892) {
			return 24;
		} else if (weaponId == 29457 || weaponId == 29255) {
			return 1123;
		}
		return 19; // bronze default
	}

	public int getArrowProjectileGfxId(int weaponId, int arrowId) {
		/**
		 * Arrow ID
		 */
		if (weaponId == 24474) {
			return 3196;
		} else if (arrowId == 884) {
			return 11;
		} else if (arrowId == 886) {
			return 12;
		} else if (arrowId == 888) {
			return 13;
		} else if (arrowId == 890) {
			return 14;
		} else if (arrowId == 892) {
			return 15;
		} else if (arrowId == 11212 || arrowId == 14545 || weaponId == 29476) {
			return 1120;
		} else if (weaponId == 20171 || weaponId == 29782 || weaponId == 29379 || weaponId == 29547) {
			return 1066;
		} else if (weaponId == 29457 || weaponId == 29255) {
			return 1123;
		}
		return 10;// bronze default
	}

	public static int getKnifeThrowGfxId(int weaponId) {
		// knives TODO ALL
		if (weaponId == 868 || weaponId == 29784) {
			return 225;
		} else if (weaponId == 867) {
			return 224;
		} else if (weaponId == 866) {
			return 223;
		} else if (weaponId == 865) {
			return 221;
		} else if (weaponId == 864) {
			return 219;
		} else if (weaponId == 863) {
			return 220;
		}
		// darts
		if (weaponId == 806) {
			return 232;
		} else if (weaponId == 807) {
			return 233;
		} else if (weaponId == 808) {
			return 234;
		} else if (weaponId == 3093) {
			return 273;
		} else if (weaponId == 809) {
			return 235;
		} else if (weaponId == 810) {
			return 236;
		} else if (weaponId == 811) {
			return 237;
		} else if (weaponId == 11230) {
			return 1123;
		}
		// javelins
		if (weaponId >= 13954 && weaponId <= 13956 || weaponId >= 13879 && weaponId <= 13882) {
			return 1837;
		}
		// thrownaxe
		if (weaponId == 13883 || weaponId == 13957 || weaponId == 29434) {
			return 1839;
		}
		if (weaponId == 800) {
			return 43;
		} else if (weaponId == 13883 || weaponId == 13957) {
			return 1839;
		} else if (weaponId == 13954 || weaponId == 13955 || weaponId == 13956 || weaponId == 13879 || weaponId == 13880 || weaponId == 13881 || weaponId == 13882) {
			return 1837;
		} else if (weaponId == 29457 || weaponId == 29255) {
			return 1123;
		}
		return 219;
	}

	@SuppressWarnings("unused")
	private int getRangeHitDelay(Player player) {
		return Utils.getDistance(player.getX(), player.getY(), target.getX(), target.getY()) >= 5 ? 2 : 1;
	}

	private int meleeAttack(final Player player) {
		int weaponId = player.getEquipment().getWeaponId();
		int attackStyle = player.getCombatDefinitions().getAttackStyle();
		int combatDelay = getMeleeCombatDelay(player, weaponId);
		int soundId = getSoundId(weaponId, attackStyle);
		if (weaponId == -1) {
			Item gloves = player.getEquipment().getItem(Equipment.SLOT_HANDS);
			if (gloves != null && gloves.getDefinitions().getName().contains("Donator gloves")) {
				weaponId = -2;
			}
			if (gloves != null && gloves.getDefinitions().getName().contains("Google gloves")) {
				weaponId = -2;
			}
			if (gloves != null && gloves.getDefinitions().getName().contains("Goliath gloves")) {
				weaponId = -2;
			}
		}
		if (weaponId == 29545) {
			delayNormalHit(weaponId, attackStyle, getMeleeHit(player, getRandomMaxHit(player, weaponId, attackStyle, false, true, 0.5, true)));
		}
		if (player.twofoldPerkActive && Utils.random(1, 3) == 2) {
			delayNormalHit(weaponId, attackStyle, getMeleeHit(player, getRandomMaxHit(player, weaponId, attackStyle, false, true, 1.0, true)));
		}
		if (weaponId == 29337) {
			int targetsize = target.getSize();
			if (targetsize == 2) {
				delayNormalHit(weaponId, attackStyle, getMeleeHit(player, getRandomMaxHit(player, weaponId, attackStyle, false, true, 0.5, true)));
			} else if (targetsize > 2) {
				delayNormalHit(weaponId, attackStyle, getMeleeHit(player, getRandomMaxHit(player, weaponId, attackStyle, false, true, 0.5, true)));
				delayNormalHit(weaponId, attackStyle, getMeleeHit(player, getRandomMaxHit(player, weaponId, attackStyle, false, true, 0.25, true)));
			}
		}
		Item ring = player.getEquipment().getItem(Equipment.SLOT_RING);
		Item amulet = player.getEquipment().getItem(Equipment.SLOT_AMULET);
		Item shield = player.getEquipment().getItem(Equipment.SLOT_SHIELD);
		if (shield != null && target instanceof NPC) {
			if (shield.getDefinitions().getId() == 29527 && target instanceof NPC) {
				player.getPrayer().restorePrayer(3);
			}
		}
		if (amulet != null && target instanceof NPC) {
			if (amulet.getDefinitions().getName().equalsIgnoreCase("amulet of immense power") && target instanceof NPC) {
				// .sendMessage("Working");
				NPC n = (NPC) target;
				Random soulchance = new Random();
				int soul = soulchance.nextInt(10);
				if (soul == 8) {
					player.heal(80);
					n.applyHit(new Hit(player, 80, HitLook.MELEE_DAMAGE));
				} else if (soul != 8) {
				}
			}
		}
		if (amulet != null && target instanceof NPC) {
			if (amulet.getDefinitions().getId() == 29732 || amulet.getDefinitions().getName().equalsIgnoreCase("amulet of souls") && target instanceof NPC) {
				// .sendMessage("Working");
				NPC n = (NPC) target;
				Random soulchance = new Random();
				int soul = soulchance.nextInt(10);
				if (soul == 8) {
					player.heal(50);
					n.applyHit(new Hit(player, 50, HitLook.MELEE_DAMAGE));
				} else if (soul != 8) {
				}
			}
		}
		if (amulet != null && target instanceof NPC) {
			if (amulet != null && amulet.getDefinitions().getName().contains("Blood amulet of fury") && !amulet.getDefinitions().getName().contains("broken") && target instanceof NPC) {
				// player.sendMessage("Working");
				NPC n = (NPC) target;
				Random soulchance = new Random();
				int soul = soulchance.nextInt(10);
				if (soul == 8) {
					player.heal(80);
				} else if (soul != 8) {
				}
			}
		}
		if (ring != null && target instanceof NPC) {
			if (ring != null && ring.getDefinitions().getName().contains("of Death") && !ring.getDefinitions().getName().contains("broken") && target instanceof NPC) {
				NPC n = (NPC) target;
				Random damagechance = new Random();
				int dam = damagechance.nextInt(20);
				if (dam == 8) {
					n.applyHit(new Hit(player, n.getHitpoints() / (player.rodeffectincrease ? 10 : 20), HitLook.REGULAR_DAMAGE));
				} else if (dam != 8) {
				}
			}
			if (ring != null && ring.getDefinitions().getName().contains("Mein Fuhrer Ring") && target instanceof NPC) {
				NPC n = (NPC) target;
				Random damagechance = new Random();
				int dam = damagechance.nextInt(20);
				if (dam == 8) {
					n.applyHit(new Hit(player, n.getHitpoints() / (player.rodeffectincrease ? 10 : 20), HitLook.REGULAR_DAMAGE));
				} else if (dam != 8) {
				}
			}
			if (ring != null && ring.getDefinitions().getName().contains("Level 1 Ring") && !ring.getDefinitions().getName().contains("broken") && target instanceof NPC) {
				NPC n = (NPC) target;
				Random damagechance = new Random();
				int dam = damagechance.nextInt(20);
				if (dam == 8) {
					n.applyHit(new Hit(player, n.getHitpoints() / (player.rodeffectincrease ? 10 : 20), HitLook.REGULAR_DAMAGE));
				} else if (dam != 8) {
				}
			}
			if (ring != null && ring.getDefinitions().getName().contains("Raptor's ring")) {
				Random damagechance = new Random();
				int dam = damagechance.nextInt(20);
				if (dam == 8) {
					player.heal(player.getHitpoints() / 10);
				} else if (dam != 8) {
				}
			}
			if (ring != null && ring.getDefinitions().getName().contains("Immense power") && !ring.getDefinitions().getName().contains("broken") && target instanceof NPC) {
				NPC n = (NPC) target;
				Random damagechance = new Random();
				int dam = damagechance.nextInt(20);
				if (dam == 8) {
					n.applyHit(new Hit(player, n.getHitpoints() / (player.rodeffectincrease ? 10 : 20), HitLook.REGULAR_DAMAGE));
				} else if (dam != 8) {
				}
			}
			if (ring != null && ring.getDefinitions().getName().contains("Pussy Slapper") && !ring.getDefinitions().getName().contains("broken") && target instanceof NPC) {
				NPC n = (NPC) target;
				Random damagechance = new Random();
				int dam = damagechance.nextInt(20);
				if (dam == 8) {
					n.applyHit(new Hit(player, n.getHitpoints() / (player.rodeffectincrease ? 10 : 20), HitLook.REGULAR_DAMAGE));
				} else if (dam != 8) {
				}
			}
		}
		if (player.getCombatDefinitions().isUsingSpecialAttack()) {
			if (!specialExecute(player)) {
				return combatDelay;
			}
			switch (weaponId) {
			case 29447:
				if (target instanceof NPC) {
					player.setNextAnimation(new Animation(401));
					int chance = Utils.random(1, 100);
					if (chance < 75) {
						int hitamt = target.getHitpoints() / 20;
						target.applyHit(new Hit(player, hitamt, HitLook.MELEE_DAMAGE));
					} else {
						player.sendMessage("Your bludgeon fails to unleash its power!");
					}
				} else if (target instanceof Player) {
					// int prayerpoint = player.getPrayer().getPrayerpoints() / 100;
					player.setNextAnimation(new Animation(401));
					delayNormalHit(weaponId, attackStyle, getMeleeHit(player, getRandomMaxHit(player, weaponId, attackStyle, false, true, 1.2, true)));
				}
				break;
			case 15442:// whip start
			case 15443:
			case 15444:
			case 15441:
			case 4151:
			case 21371:
			case 23691:
				player.setNextAnimation(new Animation(11971));
				target.setNextGraphics(new Graphics(2108, 0, 100));
				if (target instanceof Player) {
					Player p2 = (Player) target;
					p2.setRunEnergy(p2.getRunEnergy() > 25 ? p2.getRunEnergy() - 25 : 0);
				}
				delayNormalHit(weaponId, attackStyle, getMeleeHit(player, getRandomMaxHit(player, weaponId, attackStyle, false, true, 1.2, true)));
				break;
			case 29270:
				player.setNextAnimation(new Animation(4841));
				if (player.getEquipment().getShieldId() == 29269 && !player.isCanPvp()) {
					delayNormalHit(weaponId, attackStyle, getMeleeHit(player, getRandomMaxHit(player, weaponId, attackStyle, false, true, 2.2, true)));
					player.sendMessage(Colors.cyan + "As you have the Off-hand Exquisite longsword equipped, your damage is increased to 220%!");
				} else {
					delayNormalHit(weaponId, attackStyle, getMeleeHit(player, getRandomMaxHit(player, weaponId, attackStyle, false, true, 1.0, true)));
				}
				break;
			case 15485: // donator trident
				player.setNextAnimation(new Animation(9602));
				player.setNextGraphics(new Graphics(278));
				delayNormalHit(weaponId, attackStyle, getMagicHit(player, getRandomMaxHit(player, weaponId, attackStyle, false, true, 2.0, true)));
				break;

			case 11730: // sara sword
			case 23690:
				player.setNextAnimation(new Animation(11993));
				target.setNextGraphics(new Graphics(1194));
				delayNormalHit(weaponId, attackStyle, getMeleeHit(player, 50 + Utils.getRandom(100)), getMeleeHit(player, getRandomMaxHit(player, weaponId, attackStyle, false, true, 1.1, true)));
				soundId = 3853;
				break;
			case 29642:
				player.setNextAnimation(new Animation(10501));
				delayNormalHit(weaponId, attackStyle, getRangeHit(player, getRandomMaxHit(player, weaponId, attackStyle, false, true, 1.0, true)));
				break;
			case 1249:// d spear
			case 1263:
			case 3176:
			case 5716:
			case 5730:
			case 13770:
			case 13772:
			case 13774:
			case 13776:
			case 11716:

				player.setNextAnimation(new Animation(12017));
				player.stopAll();
				target.setNextGraphics(new Graphics(80, 5, 60));

				if (!target.addWalkSteps(target.getX() - player.getX() + target.getX(), target.getY() - player.getY() + target.getY(), 1)) {
					player.setNextFaceEntity(target);
				}
				target.setNextFaceEntity(player);
				WorldTasksManager.schedule(new WorldTask() {
					@Override
					public void run() {
						target.setNextFaceEntity(null);
						player.setNextFaceEntity(null);
					}
				});
				if (target instanceof Player) {
					final Player other = (Player) target;
					other.lock();
					other.addFoodDelay(3000);
					other.setDisableEquip(true);
					WorldTasksManager.schedule(new WorldTask() {
						@Override
						public void run() {
							other.setDisableEquip(false);
							other.unlock();
						}
					}, 5);
				} else {
					NPC n = (NPC) target;
					n.setFreezeDelay(3000);
					n.resetCombat();
					n.setRandomWalk(false);
				}
				break;
			case 29545:
				if (target instanceof Player) {
					player.sendMessage(Colors.red+"You cannot use this special attack against a player!");
					break;
				}
				player.setNextAnimation(new Animation(11989));
				player.setNextGraphics(new Graphics(2113));
				delayNormalHit(weaponId, attackStyle, getMeleeHit(player, getRandomMaxHit(player, weaponId, attackStyle, false, true, 1.1, true)));
				delayNormalHit(weaponId, attackStyle, getMeleeHit(player, getRandomMaxHit(player, weaponId, attackStyle, false, true, 1.0, true)));
				break;
			case 29649:
				if (target instanceof Player) {
					player.sendMessage(Colors.red+"You cannot use this special attack against a player!");
					break;
				}
				if (World.isHomeArea(player)) {
					player.sendMessage("You cannot use this special attack here.");
					break;
				}
				player.setNextAnimation(new Animation(12002));
				player.stopAll();
				target.setNextGraphics(new Graphics(80, 5, 60));
				delayNormalHit(weaponId, attackStyle, getMeleeHit(player, getRandomMaxHit(player, weaponId, attackStyle, false, true, 1.0, true)));
				if (!target.addWalkSteps(target.getX() + player.getX() - target.getX(), target.getY() + player.getY() - target.getY(), 1)) {
					player.setNextFaceEntity(target);
				}
				target.setNextFaceEntity(player);
				WorldTasksManager.schedule(new WorldTask() {
					@Override
					public void run() {
						target.setNextFaceEntity(null);
						player.setNextFaceEntity(null);
					}
				});
				if (target instanceof Player) {
					final Player other = (Player) target;
					other.lock();
					other.addFoodDelay(3000);
					other.setDisableEquip(true);
					WorldTasksManager.schedule(new WorldTask() {
						@Override
						public void run() {
							other.setDisableEquip(false);
							other.unlock();
						}
					}, 5);
				} else {
					NPC n = (NPC) target;
					n.setFreezeDelay(3000);
					n.resetCombat();
					n.setRandomWalk(false);
				}
				break;
			case 11698: // sgs
			case 29884:
			case 23681:
			case 29029:
				// player.setNextAnimation(new Animation(7071));
				player.setNextGraphics(new Graphics(2109));
				int sgsdamage = getRandomMaxHit(player, weaponId, attackStyle, false, true, 1.1, true);
				player.heal(sgsdamage / 2);
				player.getPrayer().restorePrayer(sgsdamage / 4 * 10);
				delayNormalHit(weaponId, attackStyle, getMeleeHit(player, sgsdamage));
				break;
			case 29973:
			case 29972:
			case 29971:
			case 29809:
			case 29811:
			case 29813:
			case 29156:
			case 29154:
			case 29152:
				player.setNextAnimation(new Animation(1168));
				player.setNextGraphics(new Graphics(247));
				player.heal(300);
				int damage1 = getRandomMaxHit(player, weaponId, attackStyle, false, true, 1.2, true);
				delayNormalHit(weaponId, attackStyle,

						getMeleeHit(player, damage1));
				break;
			case 29783: // noxious staff
				if (target instanceof Player) {
					player.sendMessage(Colors.red+"You cannot use this special attack against a player!");
					break;
				}
				player.setNextAnimation(new Animation(9599));
				player.setNextGraphics(new Graphics(271));
				delayNormalHit(weaponId, attackStyle, getMagicHit(player, getRandomMaxHit(player, weaponId, attackStyle, false, true, 2.5, true)));
				int noxdamage = getRandomMaxHit(player, weaponId, attackStyle, false, true, 2.5, true);
				target.getPoison().makePoisoned(noxdamage / 5);

				break;
			case 29546: // staff of sliske
				if (target instanceof Player) {
					player.sendMessage(Colors.red+"You cannot use this special attack against a player!");
					break;
				}
				player.setNextAnimation(new Animation(9599));
				player.setNextGraphics(new Graphics(482));
				delayNormalHit(weaponId, attackStyle, getMagicHit(player, getRandomMaxHit(player, weaponId, attackStyle, false, true, 2.5, true)));
				int sliskedamage = getRandomMaxHit(player, weaponId, attackStyle, false, true, 2.5, true);
				player.heal(sliskedamage / 5);

				break;

			case 11696: // bgs
			case 29885:
			case 23680:
			case 29031:
				player.setNextAnimation(new Animation(11991));
				player.setNextGraphics(new Graphics(2114));
				int damage = getRandomMaxHit(player, weaponId, attackStyle, false, true, 1.2, true);
				delayNormalHit(weaponId, attackStyle, getMeleeHit(player, damage));
				if (target instanceof Player) {
					Player targetPlayer = (Player) target;
					int amountLeft;
					if ((amountLeft = targetPlayer.getSkills().drainLevel(Skills.DEFENCE, damage / 10)) > 0) {
						if ((amountLeft = targetPlayer.getSkills().drainLevel(Skills.STRENGTH, amountLeft)) > 0) {
							if ((amountLeft = targetPlayer.getSkills().drainLevel(Skills.PRAYER, amountLeft)) > 0) {
								if ((amountLeft = targetPlayer.getSkills().drainLevel(Skills.ATTACK, amountLeft)) > 0) {
									if ((amountLeft = targetPlayer.getSkills().drainLevel(Skills.MAGIC, amountLeft)) > 0) {
										if (targetPlayer.getSkills().drainLevel(Skills.RANGE, amountLeft) > 0) {
											break;
										}
									}
								}
							}
						}
					}
				}
				break;
			case 11694: // ags
			case 29886:
			case 23679:
			case 29035:
				player.setNextAnimation(new Animation(11989));
				player.setNextGraphics(new Graphics(2113));
				delayNormalHit(weaponId, attackStyle, getMeleeHit(player, getRandomMaxHit(player, weaponId, attackStyle, false, true, 1.375, true)));
				break;
			case 13899: // vls
			case 13901:
				player.setNextAnimation(new Animation(10502));
				delayNormalHit(weaponId, attackStyle, getMeleeHit(player, getRandomMaxHit(player, weaponId, attackStyle, false, true, 1.20, true)));
				break;
			case 29440: // dragonwarhammer
			case 29303:
				player.setNextAnimation(new Animation(10505));
				player.setNextGraphics(new Graphics(1840));
				int dmg1 = getRandomMaxHit(player, weaponId, attackStyle, false, true, 1.25, true);
				delayNormalHit(weaponId, attackStyle, getMeleeHit(player, dmg1));
				if (dmg1 > 0) {
					if (target instanceof Player) {
						double modifier = player.getSkills().getLevel(Skills.DEFENCE) * 0.7 + 1;
						player.getSkills().drainLevel(Skills.DEFENCE, (int) modifier);
						player.getSkills().refresh(Skills.DEFENCE);
					} else if (target instanceof NPC) {
						NPC npc = (NPC) target;
						if (npc.getBonuses() != null) {
							if (npc.getBonus(CombatDefinitions.STAB_DEF) > 1) {
								npc.setDefenceBonuses((int) (npc.getBonus(CombatDefinitions.STAB_DEF) * 0.7) + 1);
								// .setDefenceBonuses(500);
							}
						}
					}
				}
				break;
			case 13902: // statius hammer
			case 13904:
				player.setNextAnimation(new Animation(10505));
				player.setNextGraphics(new Graphics(1840));
				int dmg = getRandomMaxHit(player, weaponId, attackStyle, false, true, 1.25, true);
				delayNormalHit(weaponId, attackStyle, getMeleeHit(player, dmg));
				if (dmg > 0) {
					if (target instanceof Player) {
						double modifier = player.getSkills().getLevel(Skills.DEFENCE) * 0.7 + 1;
						player.getSkills().drainLevel(Skills.DEFENCE, (int) modifier);
						player.getSkills().refresh(Skills.DEFENCE);
					} else if (target instanceof NPC) {
						NPC npc = (NPC) target;
						npc.setDefenceBonuses((int) (npc.getBonus(CombatDefinitions.STAB_DEF) * 0.7) + 1);
					}
				}
				break;
			case 29562:
				player.setNextAnimation(new Animation(10505));
				player.setNextGraphics(new Graphics(1840));
				delayNormalHit(weaponId, attackStyle, getMeleeHit(player, getRandomMaxHit(player, weaponId, attackStyle, false, true, 1.4, true)));
				break;
			case 13905: // vesta spear
			case 13907:
				player.setNextAnimation(new Animation(10499));
				player.setNextGraphics(new Graphics(1835));
				delayNormalHit(weaponId, attackStyle, getMeleeHit(player, getRandomMaxHit(player, weaponId, attackStyle, false, true, 1.1, true)));
				break;
			case 19784: // korasi sword
			case 18786:
				player.setNextAnimation(new Animation(14788));
				int korasiDamage = getMaxHit(player, weaponId, attackStyle, false, true, 1);
				double multiplier = 0.5 + Math.random();
				max_hit = (int) (korasiDamage * 1.8);
				korasiDamage *= multiplier;
				delayNormalHit(weaponId, attackStyle, getMagicHit(player, korasiDamage));
				break;
			case 11700:
			case 29883:
			case 23682:
			case 29030:
				int zgsdamage = getRandomMaxHit(player, weaponId, attackStyle, false, true, 1.0, true);
				player.setNextAnimation(new Animation(7070));
				player.setNextGraphics(new Graphics(1221));
				if (zgsdamage != 0 && target.getSize() <= 1) { // freezes small
					// npcs
					target.setNextGraphics(new Graphics(2104));
					target.addFreezeDelay(18000); // 18seconds
				}
				delayNormalHit(weaponId, attackStyle, getMeleeHit(player, zgsdamage));
				break;
			case 14484: // d claws
			case 29908:
			case 23695:
			case 29801:
				player.setNextAnimation(new Animation(10961));
				player.setNextGraphics(new Graphics(1950));
				int[] hits = new int[] { 0, 1 };
				int hit = getRandomMaxHit(player, weaponId, attackStyle, false, true, 1.0, true);
				if (hit > 0) {
					hits = new int[] { hit, hit / 2, hit / 2 / 2, hit / 2 - hit / 2 / 2 };
				} else {
					hit = getRandomMaxHit(player, weaponId, attackStyle, false, true, 1.0, true);
					if (hit > 0) {
						hits = new int[] { 0, hit, hit / 2, hit - hit / 2 };
					} else {
						hit = getRandomMaxHit(player, weaponId, attackStyle, false, true, 1.0, true);
						if (hit > 0) {
							hits = new int[] { 0, 0, hit / 2, hit / 2 + 10 };
						} else {
							hit = getRandomMaxHit(player, weaponId, attackStyle, false, true, 1.0, true);
							if (hit > 0) {
								hits = new int[] { 0, 0, 0, (int) (hit * 1.5) };
							} else {
								hits = new int[] { 0, 0, 0, Utils.getRandom(7) };
							}
						}
					}
				}
				for (int i = 0; i < hits.length; i++) {
					if (i > 1) {
						delayHit(1, weaponId, attackStyle, getMeleeHit(player, hits[i]));
					} else {
						delayNormalHit(weaponId, attackStyle, getMeleeHit(player, hits[i]));
					}
				}
				break;
			case 10887: // anchor
				player.setNextAnimation(new Animation(5870));
				player.setNextGraphics(new Graphics(1027));
				delayNormalHit(weaponId, attackStyle, getMeleeHit(player, getRandomMaxHit(player, weaponId, attackStyle, false, false, 1.0, true)));
				break;
			case 1305: // dragon long
				player.setNextAnimation(new Animation(12033));
				player.setNextGraphics(new Graphics(2117));
				delayNormalHit(weaponId, attackStyle, getMeleeHit(player, getRandomMaxHit(player, weaponId, attackStyle, false, true, 1.25, true)));
				break;
			case 3204: // d hally
				player.setNextAnimation(new Animation(1665));
				player.setNextGraphics(new Graphics(282));
				if (target.getSize() < 3) {// giant npcs wont get stuned cuz of
					// a stupid hit
					target.setNextGraphics(new Graphics(254, 0, 100));
					target.setNextGraphics(new Graphics(80));
				}
				delayNormalHit(weaponId, attackStyle, getMeleeHit(player, getRandomMaxHit(player, weaponId, attackStyle, false, true, 1.1, true)));
				if (target.getSize() > 1) {
					delayHit(1, weaponId, attackStyle, getMeleeHit(player, getRandomMaxHit(player, weaponId, attackStyle, false, true, 1.1, true)));
				}
				break;
			case 4587: // dragon sci
				player.setNextAnimation(new Animation(12031));
				player.setNextGraphics(new Graphics(2118));
				Hit hit1 = getMeleeHit(player, getRandomMaxHit(player, weaponId, attackStyle, false, true, 1.0, true));
				if (target instanceof Player) {
					Player p2 = (Player) target;
					if (hit1.getDamage() > 0) {
						p2.setPrayerDelay(5000);// 5 seconds
					}
				}
				delayNormalHit(weaponId, attackStyle, hit1);
				soundId = 2540;
				break;
			case 29585: // Glacies dagger
				player.setNextAnimation(new Animation(1062));
				player.setNextGraphics(new Graphics(252, 0, 100));
				int glaciesdamage = getRandomMaxHit(player, weaponId, attackStyle, false, true, 1.25, true);
				int glaciesdamage1 = getRandomMaxHit(player, weaponId, attackStyle, false, true, 1.25, true);
				delayNormalHit(weaponId, attackStyle, getMeleeHit(player, glaciesdamage), getMeleeHit(player, glaciesdamage1));
				target.addFreezeDelay(glaciesdamage / 50 * 4000, true);
				// player.sendMessage(""+glaciesdamage / 50 * 4000+"");
				// player.sendMessage(""+glaciesdamage +"");
				soundId = 2537;
				break;
			case 29584: // Curare dagger
				player.setNextAnimation(new Animation(1062));
				player.setNextGraphics(new Graphics(252, 0, 100));
				int curaredamage = getRandomMaxHit(player, weaponId, attackStyle, false, true, 1.25, true);
				int curaredamage1 = getRandomMaxHit(player, weaponId, attackStyle, false, true, 1.25, true);
				delayNormalHit(weaponId, attackStyle, getMeleeHit(player, curaredamage), getMeleeHit(player, curaredamage1));
				player.heal(curaredamage / 3);
				// player.sendMessage(""+curaredamage / 3+"");
				soundId = 2537;
				break;
			case 29583: // Fortem dagger
				player.setNextAnimation(new Animation(1062));
				player.setNextGraphics(new Graphics(252, 0, 100));
				delayNormalHit(weaponId, attackStyle, getMeleeHit(player, getRandomMaxHit(player, weaponId, attackStyle, false, true, 1.25, true)));
				delayNormalHit(weaponId, attackStyle, getMeleeHit(player, getRandomMaxHit(player, weaponId, attackStyle, false, true, 1.25, true)));
				soundId = 2537;
				break;
			case 1215: // dragon dagger
			case 5698: // dds
			case 29441:
				player.setNextAnimation(new Animation(1062));
				player.setNextGraphics(new Graphics(252, 0, 100));
				delayNormalHit(weaponId, attackStyle, getMeleeHit(player, getRandomMaxHit(player, weaponId, attackStyle, false, true, 1.15, true)));
				delayNormalHit(weaponId, attackStyle, getMeleeHit(player, getRandomMaxHit(player, weaponId, attackStyle, false, true, 1.15, true)));
				soundId = 2537;
				break;
			case 1434: // dragon mace
				player.setNextAnimation(new Animation(1060));
				player.setNextGraphics(new Graphics(251));
				delayNormalHit(weaponId, attackStyle, getMeleeHit(player, getRandomMaxHit(player, weaponId, attackStyle, false, true, 1.45, true)));
				soundId = 2541;
				break;
			default:
				player.getPackets().sendGameMessage("This weapon has no special Attack, if you still see special bar please contact c.");
				return combatDelay;
			}
		} else {
			if (weaponId == -2 && new Random().nextInt(25) == 0) {
				player.setNextAnimation(new Animation(14417));
				final int attack = attackStyle;
				attackTarget(getMultiAttackTargets(player, 5, Integer.MAX_VALUE), new MultiAttack() {

					private boolean nextTarget;

					@Override
					public boolean attack() {
						target.addFreezeDelay(10000, true);
						target.setNextGraphics(new Graphics(181, 0, 96));
						final Entity t = target;
						WorldTasksManager.schedule(new WorldTask() {
							@Override
							public void run() {
								final int damage = getRandomMaxHit(player, -2, attack, false, false, 1.0, false);
								t.applyHit(new Hit(player, damage, HitLook.REGULAR_DAMAGE));

								stop();
							}
						}, 1);

						if (target instanceof Player) {
							Player p = (Player) target;
							for (int i = 0; i < 7; i++) {
								if (i != 3 && i != 5) {
									p.getSkills().drainLevel(i, 7);
								}
							}
							p.getPackets().sendGameMessage("Your stats have been drained!");
						}
						if (!nextTarget) {
							nextTarget = true;
						}
						return nextTarget;

					}
				});
				return combatDelay;
			}
			if (Utils.random(1, 100) == 1 && player.getEquipment().getCapeId() == 20771 && player.getSkills().getXp(Skills.ATTACK) >= 104273167) {
				delayNormalHit(weaponId, attackStyle, getMeleeHit(player, getRandomMaxHit(player, weaponId, attackStyle, false)));
			}
			if (Utils.random(1, 100) == 1 && player.getEquipment().getCapeId() == 29996) {
				delayNormalHit(weaponId, attackStyle, getMeleeHit(player, getRandomMaxHit(player, weaponId, attackStyle, false)));
			}
			player.setNextAnimation(new Animation(getWeaponAttackEmote(weaponId, attackStyle, player)));
			delayNormalHit(weaponId, attackStyle, getMeleeHit(player, getRandomMaxHit(player, weaponId, attackStyle, false)));
			player.setNextAnimation(new Animation(getWeaponAttackEmote(weaponId, attackStyle, player)));
		}
		playSound(soundId, player, target);
		return combatDelay;
	}

	public void playSound(int soundId, Player player, Entity target) {
		if (soundId == -1) {
			return;
		}
		player.getPackets().sendSound(soundId, 0, 1);
		if (target instanceof Player) {
			Player p2 = (Player) target;
			p2.getPackets().sendSound(soundId, 0, 1);
		}
	}

	public static int getSpecialAmmount(int weaponId) {
		switch (weaponId) {
		case 4587: // dragon sci
		case 859: // magic longbow
		case 861: // magic shortbow
		case 10284: // Magic composite bow
		case 18332: // Magic longbow (sighted)
		case 19149:// zamorak bow
		case 19151:
		case 19143:// saradomin bow
		case 19145:
		case 19146:
		case 19148:// guthix bow
			return 55;
		case 29642:
			return 1;
		case 11235: // dark bows
		case 15701:
		case 15702:
		case 15703:
		case 15704:
			return 65;
		case 13899: // vls
		case 13901:
		case 1305: // dragon long
		case 1215: // dragon dagger
		case 29441:
		case 5698: // dds
		case 1434: // dragon mace
		case 1249:// d spear
		case 1263:
		case 3176:
		case 5716:
		case 5730:
		case 13770:
		case 13772:
		case 13774:
		case 13776:
			return 25;
		case 29649:
		case 29585:
		case 29584:
		case 29583:
			return 45;
		case 15442:// whip start
		case 15443:
		case 15444:
		case 15441:
		case 4151:
		case 21371:
		case 23691:
		case 11698: // sgs
		case 29029:
		case 29030:
		case 29031:
		case 29035:
		case 29884:
		case 23681:
		case 11694: // ags
		case 29886:
		case 23679:
		case 13902: // statius hammer
		case 13904:
		case 13905: // vesta spear
		case 13907:
		case 14484: // d claws
		case 29801:
		case 29908:
		case 23695:
		case 10887: // anchor
		case 3204: // d hally
		case 4153: // granite maul
		case 14684: // zanik cbow
		case 15241: // hand cannon
		case 29368:
		case 13908:
		case 13954:// morrigan javelin
		case 13955:
		case 13956:
		case 13879:
		case 13880:
		case 29440:
		case 29303:
		case 13881:
		case 13882:
		case 13883:// morigan thrown axe
		case 29562:
		case 13957:
		case 29545:
			return 50;
		case 29594:
			return 65;
		case 11730: // ss
		case 23690:
		case 11696: // bgs
		case 29885:
		case 23680:
		case 11700: // zgs
		case 29883:
		case 23682:
		case 35:// Excalibur
		case 29973:
		case 29972:
		case 29971:
		case 29809:
		case 29811:
		case 29813:
		case 29156:
		case 29154:
		case 29152:
		case 8280:
		case 14632:
		case 1377:// dragon battle axe
		case 13472:
		case 15486:// staff of lights
		case 22207:
		case 22209:
		case 22211:
		case 22213:
		case 15485: // donator staff
		case 29270:
		case 29916: // donator crossbow
		case 29783:
		case 29816:
		case 29547:
		case 29546:
		case 29447:
			return 100;
		case 19784: // korasi sword
		case 29782:
			return 60;
		default:
			return 0;
		}
	}

	public int getRandomMaxHit(Player player, int weaponId, int attackStyle, boolean ranging) {
		return getRandomMaxHit(player, weaponId, attackStyle, ranging, true, 1.0D, false);
	}

	public int getRandomMaxHit(Player player, int weaponId, int attackStyle, boolean ranging, boolean defenceAffects, double specMultiplier, boolean usingSpec) {
		max_hit = getMaxHit(player, weaponId, attackStyle, ranging, usingSpec, specMultiplier);
		if (defenceAffects) {
			double att = player.getSkills().getLevel(ranging ? 4 : 0) + player.getCombatDefinitions().getBonuses()[ranging ? 4 : CombatDefinitions.getMeleeBonusStyle(weaponId, attackStyle)];
			if (weaponId == -2) {
				att += 82;
			}
			att *= ranging ? player.getPrayer().getRangeMultiplier() : player.getPrayer().getAttackMultiplier();
			if (fullVoidEquipped(player, ranging ? new int[] { 11664, 11675 } : new int[] { 11665, 11676 })) {
				att *= 1.1;
			}
			if (target instanceof NPC) {
				NPC n1 = (NPC) target;
				if (hasZamSpear(player) && n1.getId() == 8133) {
					att *= 5.0;
					// player.sendMessage("Working");
				}
				if (hasEasterCarrot(player) && n1.getId() == 29975) {
					att *= 3.0;
				}
				if (weaponId == 29432) {
					if (n1.getName().contains("dragon") || n1.getName().contains("Dragon")) {
						att *= 1.30;
					}
				}
				if (weaponId == 29326) {
					if (n1.getName().contains("demon") || n1.getName().contains("party") || n1.getName().contains("thunderous") || n1.getName().contains("sire") || n1.getName().contains("K'ril") || n1.getName().contains("Balfrug") || n1.getName().contains("Tstanon") || n1.getName().contains("Zakl'n")) {
						att *= 1.70;
					}
				}
				if (weaponId == 29326 && n1.getName().equalsIgnoreCase("Skotizo"))
				 {
					att *= 2;
				// System.out.println("boosting accuracy");
				}
				if (hasDragonLance(player)) {
					if (n1.getName().toLowerCase().contains("dragon") || n1.getName().toLowerCase().contains("wyvern") || n1.getName().equalsIgnoreCase("wyrm") || n1.getName().toLowerCase().contains("hydra") || n1.getName().toLowerCase().contains("drake")) {
						att *= 1.25;
					}
				}
				if (player.getTask() != null) {
					if (target instanceof NPC) {
						NPC n = (NPC) target;
						if (n.getDefinitions().name.toLowerCase().equalsIgnoreCase(player.getTask().getName().toLowerCase())) {
							if (hasSlayerHelm(player) && !hasFullSlayerHelm(player)) {
								att *= 1.125;
							} else if (hasFullSlayerHelm(player)) {
								att *= 1.145;
							} else if (hasCorruptedSlayerHelm(player)) {
								att *= 1.175;
							} else if (hasAncientSlayerHelm(player)) {
								att *= 1.2;
							}

						}
					}
				}
			}
			if (ranging) {
				att *= player.getAuraManager().getRangeAccurayMultiplier();
			}
			double def = 0;
			if (target instanceof Player) {
				Player p2 = (Player) target;
				def = (double) p2.getSkills().getLevel(Skills.DEFENCE) + 2 * p2.getCombatDefinitions().getBonuses()[ranging ? 9 : CombatDefinitions.getMeleeDefenceBonus(CombatDefinitions.getMeleeBonusStyle(weaponId, attackStyle))];

				def *= p2.getPrayer().getDefenceMultiplier();
				if (!ranging) {
					att *= player.getAuraManager().getBerserkerBoost(false);
					if (p2.getFamiliar() instanceof Steeltitan) {
						def *= 1.15;
					}
				}
			} else {
				NPC n = (NPC) target;
				def = n.getBonuses() != null ? n.getBonuses()[ranging ? 9 : CombatDefinitions.getMeleeDefenceBonus(CombatDefinitions.getMeleeBonusStyle(weaponId, attackStyle))] : 0;
				def *= 2;
				// System.out.println(def);
				if (n.getId() == 1160 && fullVeracsEquipped(player)) {
					def *= 0.6;
				}
			}
			if (usingSpec) {
				double multiplier = getSpecialAccuracyModifier(player);
				att *= multiplier;
			}
			if (def < 0) {
				def = -def;
				att += def;
			}
			double prob = att / def;

			if (prob > 0.90) {
				// lvl 3
				prob = 0.90;
			} else if (prob < 0.05) {
				prob = 0.05;
			}
			if (prob < Math.random()) {
				return 0;
			}
		}
		int hit = Utils.random(max_hit + 1);
		if (target instanceof NPC) {
			NPC n = (NPC) target;
			if (n.getId() == 9463 || n.getId() == 14301 || n.getId() == 9928 && hasFireCape(player)) {
				hit += 40;
			}
			if (player.getEquipment().getAmuletId() == 29217 && n.getId() == 1552) {
				hit *= 2;
			}
			if (hasEasterCarrot(player) && n.getId() == 29975) {
				hit *= 3.0;
			}
			if (n.getId() == 8133 && hasZamSpear(player)) {
				hit *= 1.0;
			}
			for (int bosspet : Settings.PETS_WITH_PERKS) {
				if (player.getPetManager().getNpcId() == bosspet && player.getPetPerk().getContainer().contains(new Item(29390))) {
					hit *= 1.03;
				}
				if (player.getPetManager().getNpcId() == bosspet && player.getPetPerk().getContainer().contains(new Item(29391))) {
					hit *= 1.02;
				}
				if (player.getPetManager().getNpcId() == bosspet && player.getPetPerk().getContainer().contains(new Item(29392))) {
					hit *= 1.01;
				}
			}
			// System.out.println(hit);
			if (player.getEquipment().getCapeId() == 29977) {
				hit *= 1.10;
			} else if (player.getEquipment().getCapeId() == 20771 && player.getSkills().getXp(Skills.STRENGTH) >= 104273167) {
				hit *= 1.10;
			}
			if (n.getId() == 30067 || n.getId() == 30066 || n.getId() == 30065 || n.getId() == 30059 || n.getId() == 30058 || n.getId() == 30057 || n.getId() == 12900 || n.getId() == 14262 && hasAmuletofZaros(player)) {
				hit += hit / 7;
			}
			if (hasNoxStaff(player) || hasSliskeStaff(player)) {
				hit += player.getSkills().getLevelForXp(Skills.MAGIC);
			}
		}
		if (player.getAuraManager().usingEquilibrium()) {
			int perc25MaxHit = (int) (max_hit * 0.25);
			hit -= perc25MaxHit;
			max_hit -= perc25MaxHit;
			if (hit < 0) {
				hit = 0;
			}
			if (hit < perc25MaxHit) {
				hit += perc25MaxHit;
			}

		}
		if (player.getEquipment().getAmmoId() == 29110 && Utils.random(6) == 0) {
			target.applyHit(new Hit(player, hit, HitLook.REGULAR_DAMAGE));
		}
		if (player.getEquipment().getAmmoId() == 29108 && Utils.random(6) == 0) {
			target.applyHit(new Hit(player, hit, HitLook.REGULAR_DAMAGE));
		}
		for (int bosspet : Settings.PETS_WITH_PERKS) {
			if (player.getPetManager().getNpcId() == bosspet && player.getPetPerk().getContainer().contains(new Item(29274))) {
				target.applyHit(new Hit(player, hit / 20, HitLook.REGULAR_DAMAGE));
			}
			if (player.getPetManager().getNpcId() == bosspet && player.getPetPerk().getContainer().contains(new Item(29273)) && Utils.random(11) == 0) {
				player.heal(hit / 4);
				// player.sendMessage("healed");
			}
			if (player.getPetManager().getNpcId() == bosspet && player.getPetPerk().getContainer().contains(new Item(29272)) && Utils.random(11) == 0) {
				player.getPrayer().restorePrayer(hit / 20);
				// player.sendMessage("restored");
			}
		}
		if (Settings.eventdoublehits > 0 && !(target instanceof Player)) {
			hit *= 2;
		}
		if (World.OsrsRaids(player) || World.TheatreofBlood(player)) {
			if (player.getEquipment().getShieldId() == 25353) {
				hit *= 1.5;
			}
		}
		if (World.OsrsRaids(player)) {
		if (player.osrsraiddamagepoints > 20000000) {
			player.osrsraiddamagepoints = 0;
		}
		player.osrsraiddamagepoints += hit;
		}
		player.assassineventdamage += hit;
		if (World.TheatreofBlood(player)) {
			if (player.theatreofblooddamagepoints > 20000000) {
				player.theatreofblooddamagepoints = 0;
			}
			player.theatreofblooddamagepoints += hit;
			
		}
		return hit;
	}

	private final int getMaxHit(Player player, int weaponId, int attackStyle, boolean ranging, boolean usingSpec, double specMultiplier) {
		if (!ranging) {

			/*
			 * //whip hiting 450 no pot no pray? lmao nty int strLvl =
			 * player.getSkills().getLevel(Skills.STRENGTH); int strBonus =
			 * player.getCombatDefinitions().getBonuses()[CombatDefinitions.STRENGTH_BONUS];
			 * double strMult = player.getPrayer().getStrengthMultiplier(); double xpStyle =
			 * CombatDefinitions.getXpStyle(weaponId, attackStyle); double style = xpStyle
			 * == Skills.STRENGTH ? 3 : xpStyle == CombatDefinitions.SHARED ? 1 : 0; int dhp
			 * = 0; double dharokMod = 1.0; double hitMultiplier = 1.0; if
			 * (fullDharokEquipped(player)) { dhp = player.getMaxHitpoints() -
			 * player.getHitpoints(); dharokMod = (dhp * 0.001) + 1; } if
			 * (fullVoidEquipped(player, 11665, 11676)) { hitMultiplier *= 1.1; }
			 * hitMultiplier *= specMultiplier; double cumulativeStr = (strLvl * strMult +
			 * style) * dharokMod; return (int) ((14 + cumulativeStr + (strBonus / 8) +
			 * ((cumulativeStr * strBonus) / 64)) * hitMultiplier);
			 */

			double strengthLvl = player.getSkills().getLevel(Skills.STRENGTH);
			int xpStyle = CombatDefinitions.getXpStyle(weaponId, attackStyle);
			double styleBonus = xpStyle == Skills.STRENGTH ? 3 : xpStyle == CombatDefinitions.SHARED ? 1 : 0;
			double otherBonus = 1;
			if (fullDharokEquipped(player)) {
				double hp = player.getHitpoints();
				double maxhp = player.getMaxHitpoints();
				double d = hp / maxhp;
				otherBonus = 2 - d;
			}
			double effectiveStrength = 8 + Math.floor(strengthLvl * player.getPrayer().getStrengthMultiplier() + styleBonus);
			if (fullVoidEquipped(player, 11665, 11676)) {
				effectiveStrength = Math.floor(effectiveStrength * 1.1);
			}
			if (BneckAndWepEquipped(player)) {
				effectiveStrength = Math.floor(effectiveStrength * 1.2);
			}
			if (weaponId == 29326) {
				if (target.getDisplayName().contains("demon") || target.getDisplayName().contains("thunderous") || target.getDisplayName().contains("sire") || target.getDisplayName().contains("K'ril") || target.getDisplayName().contains("Balfrug") || target.getDisplayName().contains("Tstanon") || target.getDisplayName().contains("Zakl'n")) {
					effectiveStrength = Math.floor(effectiveStrength * 1.70);
				}
			}
			if (weaponId == 29326 && target.getDisplayName().equalsIgnoreCase("Skotizo"))
			 {
				effectiveStrength = Math.floor(effectiveStrength * 2);
			// System.out.println("boosting max hit");
			}

			if (player.getTask() != null) {
				if (player.getTask() != null) {
					if (target instanceof NPC) {
						NPC n = (NPC) target;
						if (n.getDefinitions().name.toLowerCase().equalsIgnoreCase(player.getTask().getName().toLowerCase())) {
							if (hasSlayerHelm(player) && !hasFullSlayerHelm(player)) {
								effectiveStrength = Math.floor(effectiveStrength * 1.125);
							} else if (hasFullSlayerHelm(player)) {
								effectiveStrength = Math.floor(effectiveStrength * 1.145);
							} else if (hasCorruptedSlayerHelm(player)) {
								effectiveStrength = Math.floor(effectiveStrength * 1.175);
							} else if (hasAncientSlayerHelm(player)) {
								effectiveStrength = Math.floor(effectiveStrength * 1.2);
							}
						}
					}
				}
			}
			double strengthBonus = player.getCombatDefinitions().getBonuses()[CombatDefinitions.STRENGTH_BONUS];
			if (weaponId == -2) {
				strengthBonus += 82;
			}
			double baseDamage = 5 + effectiveStrength * (1 + strengthBonus / 64);
			if (target instanceof NPC) {
				if (weaponId == 29378 && Wilderness.isAtWild(player)) {
					baseDamage += baseDamage * otherBonus * 1.5;
				}
				if (player.getEquipment().getChestId() == 20875 && Wilderness.isAtWild(player)) {
					baseDamage += baseDamage * otherBonus * 0.1;
				}
				if (player.getEquipment().getLegsId() == 20876 && Wilderness.isAtWild(player)) {
					baseDamage += baseDamage * otherBonus * 0.1;
				}
				otherBonus *= player.getAuraManager().getBerserkerBoost(false);
				NPC tg1 = (NPC) target;
				if (hasDragonLance(player)) {
					if (tg1.getName().toLowerCase().contains("dragon") || tg1.getName().toLowerCase().contains("wyvern") || tg1.getName().equalsIgnoreCase("wyrm") || tg1.getName().toLowerCase().contains("hydra") || tg1.getName().toLowerCase().contains("drake")) {
						effectiveStrength = Math.floor(effectiveStrength * 1.25);
					}
				}
				if (tg1.getId() == 7891) {
					tg1.setNextForceTalk(new ForceTalk(player.getDisplayName() + " your Melee max hit is currently <col=ff0000>" + Math.floor(baseDamage * specMultiplier * otherBonus)));
				}
			}

			return (int) Math.floor(baseDamage * specMultiplier * otherBonus);
		} else {
			if (weaponId == 24338 && target instanceof Player) {
				player.getPackets().sendGameMessage("The royal crossbow feels weak and unresponsive against other players.");
				return 60;
			}
			double rangedLvl = player.getSkills().getLevel(Skills.RANGE);
			double styleBonus = attackStyle == 0 ? 3 : attackStyle == 1 ? 0 : 1;
			double effectiveStrenght = Math.floor(rangedLvl * player.getPrayer().getRangeMultiplier()) + styleBonus;

			if (fullVoidEquipped(player, 11664, 11675)) {
				effectiveStrenght += Math.floor(player.getSkills().getLevelForXp(Skills.RANGE) / 5 + 1.6);
			}
			if (player.getTask() != null) {
				if (player.getTask() != null) {
					if (target instanceof NPC) {
						NPC n = (NPC) target;
						if (n.getDefinitions().name.toLowerCase().equalsIgnoreCase(player.getTask().getName().toLowerCase())) {
							if (hasSlayerHelm(player) && !hasFullSlayerHelm(player)) {
								effectiveStrenght = Math.floor(effectiveStrenght * 1.125);
							} else if (hasFullSlayerHelm(player)) {
								effectiveStrenght = Math.floor(effectiveStrenght * 1.145);
							} else if (hasCorruptedSlayerHelm(player)) {
								effectiveStrenght = Math.floor(effectiveStrenght * 1.175);
							} else if (hasAncientSlayerHelm(player)) {
								effectiveStrenght = Math.floor(effectiveStrenght * 1.2);
							}
						}
					}
				}
			}
			double strengthBonus = player.getCombatDefinitions().getBonuses()[CombatDefinitions.RANGED_STR_BONUS];
			double baseDamage = 5 + (effectiveStrenght + 8) * (strengthBonus + 64) / 64;
			if (target instanceof NPC) {
				if (weaponId == 29379 && Wilderness.isAtWild(player)) {
					baseDamage += baseDamage * 1.5;
				}
				if (player.getEquipment().getChestId() == 17187 && Wilderness.isAtWild(player)) {
					baseDamage += baseDamage * 0.1;
				}
				if (player.getEquipment().getLegsId() == 17333 && Wilderness.isAtWild(player)) {
					baseDamage += baseDamage * 0.1;
				}
				if (weaponId == 29432) {
					if (target.getDisplayName().contains("dragon") || target.getDisplayName().contains("Dragon")) {
						baseDamage += baseDamage * 0.3;
					}
				}
				if (weaponId == 29547) {
					baseDamage *= 1.10;
				}
				
				if (target instanceof NPC) {
					NPC tg1 = (NPC) target;
					if (player.getEquipment().getWeaponId() == 24474 && tg1.getId() == 15581) {
						player.heal(Utils.random(1000));
						baseDamage += 1000;
					}
					if (player.getEquipment().getWeaponId() == 29472 && !World.Celestia(player)) {
						if (tg1.getBonuses() != null) {
							int magiclevel = tg1.getBonuses()[3];
							/**
							 * If targets magic level is higher than 250, damage will remain the same. CAPPED
							 */
							if (magiclevel > 250) {
								magiclevel = 250;
							}
							/**
							 * Formula for damage calculation - "140" should represent the accuracy of the bow.
							 */
							int formula2 = tg1.getBonuses()[3] - 14 / 100 - tg1.getBonuses()[3] /10 - 140 *2 /100;
							int magicbuff = magiclevel + formula2;
							/**
							 * Adds the formula & current damage together.
							 */
							baseDamage += magicbuff;
							
						}
					}
				}
				NPC tg1 = (NPC) target;
				if (tg1.getId() == 7891) {
					tg1.setNextForceTalk(new ForceTalk(player.getDisplayName() + " your Range max hit is currently <col=ff0000>" + Math.floor(baseDamage * specMultiplier)));
				}
			}
			return (int) Math.floor(baseDamage * specMultiplier);
		}
	}

	private double getSpecialAccuracyModifier(Player player) {
		Item weapon = player.getEquipment().getItem(Equipment.SLOT_WEAPON);
		if (weapon == null) {
			return 1;
		}
		String name = weapon.getDefinitions().getName().toLowerCase();
		if (name.contains("whip") || name.contains("tentacle") || name.contains("dragon longsword") || name.contains("dragon scimitar") || name.contains("dragon dagger")) {
			return 1.1;
		}
		if (name.contains("heavy ballista")) {
			return 1.25;
		}
		if (name.contains("noxious bow")) {
			return 3;
		}
		if (name.contains("wild warhammer")) {
			return 3;
		}
		if (name.contains("anchor") || name.contains("magic longbow")) {
			return 2;
		}
		if (name.contains("dragon claws") || name.contains("armadyl godsword") || name.contains("devious trident") || name.contains("devious mace") || name.contains("devious crossbow") || name.contains("noxious staff") || name.contains("staff of sliske") || name.contains("zaros godsword")) {
			return 2;
		}
		return 1;
	}

	public boolean hasFireCape(Player player) {
		int capeId = player.getEquipment().getCapeId();
		return capeId == 6570 || capeId == 20769 || capeId == 20771 || capeId == 23659 || capeId == 29855 || capeId == 29856;
	}

	public boolean hasTrioStaff(Player player) {
		int wepId = player.getEquipment().getWeaponId();
		return wepId == 29642;
	}

	private boolean hasSlayerHelm(Player player) {
		int helmId = player.getEquipment().getHatId();
		return helmId == 13263 || helmId == 8901;
	}

	private boolean hasFullSlayerHelm(Player player) {
		int helmId = player.getEquipment().getHatId();
		return helmId == 15492 || helmId == 22528 || helmId == 22534 || helmId == 22540 || helmId == 22546 || helmId == 15497;
	}

	private boolean hasCorruptedSlayerHelm(Player player) {
		int helmId = player.getEquipment().getHatId();
		return helmId == 29275;
	}
	
	private boolean hasAncientSlayerHelm(Player player) {
		int helmId = player.getEquipment().getHatId();
		return helmId == 29103;
	}

	public boolean hasZamSpear(Player player) {
		int wepId = player.getEquipment().getWeaponId();
		return wepId == 11716 || wepId == 29512 || wepId == 7809 || wepId == 23683 || wepId == 29357 || wepId == 17143;
	}
	
	public boolean hasEasterCarrot(Player player) {
		int wepId = player.getEquipment().getWeaponId();
		return wepId == 14713 || wepId == 14728;
	}

	/*
	 * public static final int getMaxHit(Player player, int weaponId, int
	 * attackStyle, boolean ranging, boolean usingSpec, double specMultiplier) { if
	 * (ranging) { return (int) (getRangeMaximum(player, attackStyle) *
	 * specMultiplier); } return (int) (getMeleeMaximum(player, weaponId,
	 * attackStyle) * specMultiplier); }
	 */

	public static final boolean fullVanguardEquipped(Player player) {
		int helmId = player.getEquipment().getHatId();
		int chestId = player.getEquipment().getChestId();
		int legsId = player.getEquipment().getLegsId();
		int weaponId = player.getEquipment().getWeaponId();
		int bootsId = player.getEquipment().getBootsId();
		int glovesId = player.getEquipment().getGlovesId();
		if (helmId == -1 || chestId == -1 || legsId == -1 || weaponId == -1 || bootsId == -1 || glovesId == -1) {
			return false;
		}
		return ItemDefinitions.getItemDefinitions(helmId).getName().contains("Vanguard") && ItemDefinitions.getItemDefinitions(chestId).getName().contains("Vanguard") && ItemDefinitions.getItemDefinitions(legsId).getName().contains("Vanguard") && ItemDefinitions.getItemDefinitions(weaponId).getName().contains("Vanguard") && ItemDefinitions.getItemDefinitions(bootsId).getName().contains("Vanguard") && ItemDefinitions.getItemDefinitions(glovesId).getName().contains("Vanguard");
	}

	public static final boolean usingGoliathGloves(Player player) {
		String name = player.getEquipment().getItem(Equipment.SLOT_SHIELD) != null ? player.getEquipment().getItem(Equipment.SLOT_SHIELD).getDefinitions().getName().toLowerCase() : "";
		if (player.getEquipment().getItem(Equipment.SLOT_HANDS) != null) {
			if (player.getEquipment().getItem(Equipment.SLOT_HANDS).getDefinitions().getName().toLowerCase().contains("goliath") || player.getEquipment().getItem(Equipment.SLOT_HANDS).getDefinitions().getName().toLowerCase().contains("Google") || player.getEquipment().getItem(Equipment.SLOT_HANDS).getDefinitions().getName().toLowerCase().contains("Donator") && player.getEquipment().getWeaponId() == -1) {
				if (name.contains("defender") && name.contains("dragonfire shield")) {
					return true;
				}
				return true;
			}
		}
		return false;
	}

	public static final boolean fullVeracsEquipped(Player player) {
		int helmId = player.getEquipment().getHatId();
		int chestId = player.getEquipment().getChestId();
		int legsId = player.getEquipment().getLegsId();
		int weaponId = player.getEquipment().getWeaponId();
		if (helmId == -1 || chestId == -1 || legsId == -1 || weaponId == -1) {
			return false;
		}
		return ItemDefinitions.getItemDefinitions(helmId).getName().contains("Verac's") && ItemDefinitions.getItemDefinitions(chestId).getName().contains("Verac's") && ItemDefinitions.getItemDefinitions(legsId).getName().contains("Verac's") && ItemDefinitions.getItemDefinitions(weaponId).getName().contains("Verac's");
	}

	public static final boolean fullGuthansEquipped(Player player) {
		int helmId = player.getEquipment().getHatId();
		int chestId = player.getEquipment().getChestId();
		int legsId = player.getEquipment().getLegsId();
		int weaponId = player.getEquipment().getWeaponId();
		if (helmId == -1 || chestId == -1 || legsId == -1 || weaponId == -1) {
			return false;
		}
		return ItemDefinitions.getItemDefinitions(helmId).getName().contains("Guthan's") && ItemDefinitions.getItemDefinitions(chestId).getName().contains("Guthan's") && ItemDefinitions.getItemDefinitions(legsId).getName().contains("Guthan's") && ItemDefinitions.getItemDefinitions(weaponId).getName().contains("Guthan's");
	}

	public static final boolean fullDharokEquipped(Player player) {
		int helmId = player.getEquipment().getHatId();
		int chestId = player.getEquipment().getChestId();
		int legsId = player.getEquipment().getLegsId();
		int weaponId = player.getEquipment().getWeaponId();
		if (helmId == -1 || chestId == -1 || legsId == -1 || weaponId == -1) {
			return false;
		}
		return ItemDefinitions.getItemDefinitions(helmId).getName().contains("Dharok's") && ItemDefinitions.getItemDefinitions(chestId).getName().contains("Dharok's") && ItemDefinitions.getItemDefinitions(legsId).getName().contains("Dharok's") && ItemDefinitions.getItemDefinitions(weaponId).getName().contains("Dharok's");
	}

	public static final boolean fullWildEquipped(Player player) {
		int helmId = player.getEquipment().getHatId();
		int chestId = player.getEquipment().getChestId();
		int legsId = player.getEquipment().getLegsId();
		int weaponId = player.getEquipment().getWeaponId();
		if (helmId == -1 || chestId == -1 || legsId == -1 || weaponId == -1) {
			return false;
		}
		return ItemDefinitions.getItemDefinitions(helmId).getId() == 29559 && ItemDefinitions.getItemDefinitions(chestId).getId() == 29560 && ItemDefinitions.getItemDefinitions(legsId).getId() == 29561 && ItemDefinitions.getItemDefinitions(weaponId).getId() == 29562;
	}

	public static final boolean BneckAndWepEquipped(Player player) {
		int neckId = player.getEquipment().getAmuletId();
		int weaponId = player.getEquipment().getWeaponId();
		if (neckId == -1 || weaponId == -1) {
			return false;
		}
		return ItemDefinitions.getItemDefinitions(neckId).getName().contains("Berserker")

				&& ItemDefinitions.getItemDefinitions(weaponId).getId() == 6523 || ItemDefinitions.getItemDefinitions(weaponId).getId() == 6525 || ItemDefinitions.getItemDefinitions(weaponId).getId() == 6527 || ItemDefinitions.getItemDefinitions(weaponId).getId() == 6528;

	}

	public static final boolean fullVoidEquipped(Player player, int... helmid) {
		boolean hasDeflector = player.getEquipment().getShieldId() == 19712;
		if (player.getEquipment().getGlovesId() != 8842) {
			if (hasDeflector) {
				hasDeflector = false;
			} else {
				return false;
			}
		}
		int legsId = player.getEquipment().getLegsId();
		boolean hasLegs = legsId != -1 && (legsId == 8840 || legsId == 19786 || legsId == 19788 || legsId == 19790);
		if (!hasLegs) {
			if (hasDeflector) {
				hasDeflector = false;
			} else {
				return false;
			}
		}
		int torsoId = player.getEquipment().getChestId();
		boolean hasTorso = torsoId != -1 && (torsoId == 8839 || torsoId == 10611 || torsoId == 19785 || torsoId == 19787 || torsoId == 19789);
		if (!hasTorso) {
			if (hasDeflector) {
				hasDeflector = false;
			} else {
				return false;
			}
		}
		if (hasDeflector) {
			return true;
		}
		int helmId = player.getEquipment().getHatId();
		if (helmId == -1) {
			return false;
		}
		boolean hasHelm = false;
		for (int id : helmid) {
			if (helmId == id) {
				hasHelm = true;
				break;
			}
		}
		if (!hasHelm) {
			return false;
		}
		return true;
	}

	public void delayNormalHit(int weaponId, int attackStyle, Hit... hits) {
		delayHit(0, weaponId, attackStyle, hits);
	}

	public Hit getMeleeHit(Player player, int damage) {
		return new Hit(player, damage, HitLook.MELEE_DAMAGE);
	}

	public Hit getRangeHit(Player player, int damage) {
		return new Hit(player, damage, HitLook.RANGE_DAMAGE);
	}

	public Hit getMagicHit(Player player, int damage) {
		return new Hit(player, damage, HitLook.MAGIC_DAMAGE);
	}

	private void delayMagicHit(int delay, final Hit... hits) {
		delayHit(delay, -1, -1, hits);
	}

	public void resetVariables() {
		base_mage_xp = 0;
		mage_hit_gfx = 0;
		magic_sound = 0;
		max_poison_hit = 0;
		freeze_time = 0;
		reduceAttack = false;
		blood_spell = false;
		sanguinesti_spell = false;
		block_tele = false;
	}

	private void delayHit(int delay, final int weaponId, final int attackStyle, final Hit... hits) {
		addAttackedByDelay(hits[0].getSource());

		final Entity target = this.target;
		final int max_hit = this.max_hit;
		final double base_mage_xp = this.base_mage_xp;
		final int mage_hit_gfx = this.mage_hit_gfx;
		final int magic_sound = this.magic_sound;
		final int max_poison_hit = this.max_poison_hit;
		final int freeze_time = this.freeze_time;
		@SuppressWarnings("unused")
		final boolean reduceAttack = this.reduceAttack;
		final boolean blood_spell = this.blood_spell;
		final boolean sanguinesti_spell = this.sanguinesti_spell;
		final boolean block_tele = this.block_tele;
		resetVariables();
		if (target instanceof NPC) {
			NPC tg1 = (NPC) target;
			if (tg1.getId() == 7891) {
				return;
			}
		}
		for (Hit hit : hits) {
			Player player = (Player) hit.getSource();
			if (target instanceof Player) {
				Player p2 = (Player) target;
				if (player.getPrayer().usingPrayer(1, 18)) {
					p2.sendSoulSplit(hit, player);
				}
			}
			if (player.healingBladeActive && Utils.random(0, 3) == 2) {
				int healAmount = hit.getDamage() / 4;
				player.heal(healAmount);
				player.getPrayer().restorePrayer(healAmount);
			}
			int damage = hit.getDamage() > target.getHitpoints() ? target.getHitpoints() : hit.getDamage();
			if (hit.getLook() == HitLook.RANGE_DAMAGE || hit.getLook() == HitLook.MELEE_DAMAGE) {
				if (rubySpec) {
					if (target instanceof NPC && hit.getLook() == HitLook.RANGE_DAMAGE) {
						NPC tg = (NPC) target;
						if (hit.getDamage() > tg.getCapDamage()) {
							hit.setDamage(tg.getCapDamage());
							damage = hit.getDamage() > target.getHitpoints() ? target.getHitpoints() : hit.getDamage();
						}
					}
					rubySpec = false;
				}
				double combatXp = damage / 2.5;
				if (combatXp > 0) {
					player.getAuraManager().checkSuccefulHits(hit.getDamage());
					if (hit.getLook() == HitLook.RANGE_DAMAGE) {
						if (attackStyle == 2) {

							player.getSkills().addXp(Skills.RANGE, combatXp / 2);
							player.getSkills().addXp(Skills.DEFENCE, combatXp / 2);
						} else {
							player.getSkills().addXp(Skills.RANGE, combatXp);
						}

					} else {
						int xpStyle = CombatDefinitions.getXpStyle(weaponId, attackStyle);
						if (xpStyle != CombatDefinitions.SHARED) {
							player.getSkills().addXp(xpStyle, combatXp);
						} else {
							player.getSkills().addXp(Skills.ATTACK, combatXp / 3);
							player.getSkills().addXp(Skills.STRENGTH, combatXp / 3);
							player.getSkills().addXp(Skills.DEFENCE, combatXp / 3);
						}
					}
					double hpXp = damage / 7.5;
					if (hpXp > 0) {
						player.getSkills().addXp(Skills.HITPOINTS, hpXp);
					}
				}
				if (weaponId == 29568 && target instanceof NPC) {
					player.heal(damage / 5);
				}
				if (player.getEquipment().getShieldId() == 29891 || player.getEquipment().getShieldId() == 29599) {
					if (hit.getDamage() > 20) {
						player.heal(20);
					}
					player.getPrayer().drainPrayer(10);
				}
				if (fullGuthansEquipped(player)) {
					if (Utils.random(4) == 0) {
						Player p = (Player) hit.getSource();
						if (p != null) {
							int heal = hit.getDamage();
							if (heal > 0) {
								if (player.getHitpoints() < player.getMaxHitpoints()) {
									player.heal(heal);
									target.setNextGraphics(new Graphics(398));
								}
							}
						}
					}
				}
			} else if (hit.getLook() == HitLook.MAGIC_DAMAGE) {
				if (mage_hit_gfx != 0 && damage > 0) {
					if (freeze_time > 0) {
						target.addFreezeDelay(freeze_time, freeze_time == 0);
						if (freeze_time > 0) {
							if (target instanceof Player) {
								((Player) target).stopAll(false);
							}
						}
						target.addFrozenBlockedDelay(freeze_time + 4 * 1000);// four seconds of no freeze
					}
				} else if (damage < 0) {
					damage = 0;
				}
				double combatXp = base_mage_xp * 1 + damage / 5;

				if (combatXp > 0) {
					player.getAuraManager().checkSuccefulHits(hit.getDamage());
					if (player.getCombatDefinitions().isDefensiveCasting() || hasPolyporeStaff(player) || hasSanguinestiStaff(player) || hasTridentSeas(player) || hasTridentSwamp(player) && player.getCombatDefinitions().getAttackStyle() == 1) {
						int defenceXp = (int) (damage / 7.5);
						if (defenceXp > 0) {
							combatXp -= defenceXp;
							player.getSkills().addXp(Skills.DEFENCE, defenceXp / 7.5);
						}
					}
					player.getSkills().addXp(Skills.MAGIC, combatXp);
					double hpXp = damage / 7.5;
					if (hpXp > 0) {
						player.getSkills().addXp(Skills.HITPOINTS, hpXp);
					}
				}
			}
		}

		WorldTasksManager.schedule(new WorldTask() {

			@Override
			public void run() {
				for (Hit hit : hits) {
					boolean splash = false;
					Player player = (Player) hit.getSource();
					if (player.isDead() || player.hasFinished() || target.isDead() || target.hasFinished()) {
						return;
					}
					if (hit.getDamage() > -1) {
						target.applyHit(hit); // also reduces damage if needed, pray
						// and special items affect here
					} else {
						splash = true;
						hit.setDamage(0);
					}
					doDefenceEmote();
					int damage = hit.getDamage() > target.getHitpoints() ? target.getHitpoints() : hit.getDamage();
					if (damage >= max_hit * 0.90 && (hit.getLook() == HitLook.MAGIC_DAMAGE || hit.getLook() == HitLook.RANGE_DAMAGE || hit.getLook() == HitLook.MELEE_DAMAGE)) {
						hit.setCriticalMark();
					}
					if (hit.getLook() == HitLook.RANGE_DAMAGE || hit.getLook() == HitLook.MELEE_DAMAGE) {
						double combatXp = damage / 2.5;
						if (combatXp > 0) {
							if (hit.getLook() == HitLook.RANGE_DAMAGE) {
								if (weaponId != -1) {
									String name = ItemDefinitions.getItemDefinitions(weaponId).getName();
									if (name.contains("(p++)")) {
										if (Utils.getRandom(8) == 0) {
											target.getPoison().makePoisoned(48);
										}
									} else if (name.contains("(p+)")) {
										if (Utils.getRandom(8) == 0) {
											target.getPoison().makePoisoned(38);
										}
									} else if (name.contains("(p)")) {
										if (Utils.getRandom(8) == 0) {
											target.getPoison().makePoisoned(28);
										}
									}
								}
							} else {
								if (weaponId != -1) {
									String name = ItemDefinitions.getItemDefinitions(weaponId).getName();
									if (name.contains("(p++)")) {
										if (Utils.getRandom(8) == 0) {
											target.getPoison().makePoisoned(68);
										}
									} else if (name.contains("(p+)")) {
										if (Utils.getRandom(8) == 0) {
											target.getPoison().makePoisoned(58);
										}
									} else if (name.contains("(p)")) {
										if (Utils.getRandom(8) == 0) {
											target.getPoison().makePoisoned(48);
										}
									}
									if (target instanceof Player) {
										if (((Player) target).getPolDelay() >= Utils.currentTimeMillis()) {
											target.setNextGraphics(new Graphics(2320));
										}
									}
								}
							}
						}
					} else if (hit.getLook() == HitLook.MAGIC_DAMAGE) {
						if (block_tele) {
							if (target instanceof Player) {
								Player targetPlayer = (Player) target;
								targetPlayer.setTeleBlockDelay(targetPlayer.getPrayer().usingPrayer(0, 17) || targetPlayer.getPrayer().usingPrayer(1, 7) ? 100000 : 300000);
								targetPlayer.getPackets().sendGameMessage("You have been teleblocked.", true);
							}
						}
						if (splash) {
							target.setNextGraphics(new Graphics(85, 0, 96));
							playSound(227, player, target);
						} else {
							if (mage_hit_gfx != 0) {
								target.setNextGraphics(new Graphics(mage_hit_gfx, 0, mage_hit_gfx == 369 || mage_hit_gfx == 1843 || mage_hit_gfx > 1844 && mage_hit_gfx < 1855 ? 0 : 96));
								if (blood_spell) {
									player.heal(damage / 4);
								}

							}
							if (sanguinesti_spell) {
								// System.out.println("1");
								if (Utils.random(4) == 0) {
									player.heal(damage / 2);
								}
								System.out.println("teleblock missing method");
								
							}
							if (magic_sound > 0) {
								playSound(magic_sound, player, target);
							}
						}
					}
					if (max_poison_hit > 0 && Utils.getRandom(10) == 0) {
						if (!target.getPoison().isPoisoned()) {
							target.getPoison().makePoisoned(max_poison_hit);
						}
					}
					if (target instanceof Player) {
						Player p2 = (Player) target;
						p2.closeInterfaces();
						if (p2.getCombatDefinitions().isAutoRelatie() && !p2.getActionManager().hasSkillWorking() && !p2.hasWalkSteps()) {
							p2.getActionManager().setAction(new PlayerCombat(player));
						}
					} else {
						NPC n = (NPC) target;
						if (!n.isUnderCombat() || n.canBeAttackedByAutoRelatie()) {
							n.setTarget(player);
						}
					}
					int hp = target.getHitpoints() - damage;
					if (hp > 0) {
						if (player.isCanPvp() || player.getHpTracker() != true) {
							return;
						}
						player.getInterfaceManager().sendOverlay(3024, false);
						String name = target.getDisplayName();
						//System.out.println(name);
						if (name.equalsIgnoreCase("Celestia, <col=22E92E>Defender of Sliske</col>")) {
							name = "Celestia";
						}
						player.getPackets().sendIComponentText(3024, 4, "" + name + "");
						if (target.getHitpoints() < target.getMaxHitpoints() / 2) {
							player.getPackets().sendIComponentText(3024, 5, "" + Utils.format(hp) + "");
						} else {
							player.getPackets().sendIComponentText(3024, 5, "<col=00FF00>" +  Utils.format(hp) + "");
						}
					} /*
						 * else { player.getInterfaceManager().closeOverlay(false); }
						 */

				}
			}
		}, delay);
	}

	private int getSoundId(int weaponId, int attackStyle) {
		if (weaponId != -1) {
			String weaponName = ItemDefinitions.getItemDefinitions(weaponId).getName().toLowerCase();
			if (weaponName.contains("dart") || weaponName.contains("knife") || weaponId == 29784) {
				return 2707;
			}
		}
		return -1;
	}

	public static int getWeaponAttackEmote(int weaponId, int attackStyle, Player player) {
		if (weaponId != -1) {
			if (weaponId == -2) {
				// punch/block:14393 kick:14307 spec:14417
				switch (attackStyle) {
				case 1:
					return 14307;
				default:
					return 14393;
				}
			}
			String weaponName = ItemDefinitions.getItemDefinitions(weaponId).getName().toLowerCase();
			if (weaponName != null && !weaponName.equals("null")) {
				if (weaponName.contains("crossbow")) {
					return weaponName.contains("karil's crossbow") ? 2075 : 4230;
				}
				if (weaponName.contains("ballista")) {
					return 2075;
				}
				if (weaponName.contains("blowpipe")) {
					return 17812;
				}
				if (weaponName.contains("bow")) {
					return 426;
				}
				if (weaponName.contains("chinchompa")) {
					return 2779;
				}
				if (weaponName.contains("torag's")) {
					return 2068;
				}
				if (weaponName.contains("staff of light")) {
					switch (attackStyle) {
					case 0:
						return 15072;
					case 1:
						return 15071;
					case 2:
						return 414;
					}
				}
				if (weaponName.contains("staff") || weaponName.contains("wand")) {
					return 419;
				}
				if (weaponName.contains("dart") && player.dartanim == false) {
					return 6600;
				}
				if (weaponName.contains("dart") && player.dartanim == true) {
					return 17193;
				}
				if (weaponName.contains("knife") && player.dartanim == false) {
					return 9055;
				}
				if (weaponName.contains("knife") && player.dartanim == true) {
					return 17193;
				}
				if (weaponName.contains("devious trident")) {
					return 419;
				}
				if (weaponName.contains("scimitar") || weaponName.contains("korasi's sword")) {
					switch (attackStyle) {
					case 2:
						return 15072;
					default:
						return 15071;
					}
				}
				if (weaponName.contains("granite mace")) {
					return 400;
				}
				if (weaponName.contains("harpoon")) {
					return 401;
				}
				if (weaponName.contains("mace")) {
					switch (attackStyle) {
					case 2:
						return 400;
					default:
						return 401;
					}
				}
				if (weaponName.contains("hatchet")) {
					switch (attackStyle) {
					case 2:
						return 401;
					default:
						return 395;
					}
				}
				if (weaponName.contains("warhammer")) {
					switch (attackStyle) {
					default:
						return 401;
					}
				}
				if (weaponName.contains("claws")) {
					switch (attackStyle) {
					case 2:
						return 1067;
					default:
						return 393;
					}
				}
				if (weaponName.contains("whip") || weaponName.contains("tentacle")) {
					switch (attackStyle) {
					case 1:
						return 11969;
					case 2:
						return 11970;
					default:
						return 11968;
					}
				}
				if (weaponName.contains("anchor")) {
					switch (attackStyle) {
					default:
						return 5865;
					}
				}
				if (weaponName.contains("tzhaar-ket-em")) {
					switch (attackStyle) {
					default:
						return 401;
					}
				}
				if (weaponName.contains("tzhaar-ket-om") || weaponName.contains("elder maul") || weaponName.contains("chaotic maul")) {
					switch (attackStyle) {
					default:
						return 13691;
					}
				}
				if (weaponName.contains("halberd") || weaponName.contains("scythe of")) {
					switch (attackStyle) {
					case 1:
						return 428;
					default:
						return 440;
					}
				}
				if (weaponName.contains("zamorakian spear") || weaponName.contains("anger spear") || weaponId == 29642) {
					switch (attackStyle) {
					case 1:
						return 12005;
					case 2:
						return 12009;
					default:
						return 12006;
					}
				}
				if (weaponName.contains("spear")) {
					switch (attackStyle) {
					case 1:
						return 440;
					case 2:
						return 429;
					default:
						return 428;
					}
				}
				if (weaponName.contains("flail")) {
					return 2062;
				}
				if (weaponName.contains("javelin") && player.dartanim == false) {
					return 10501;
				}
				if (weaponName.contains("javelin") && player.dartanim == true) {
					return 17193;
				}
				if (weaponName.contains("morrigan's throwing axe") && player.dartanim == false) {
					return 10504;
				}
				if (weaponName.contains("morrigan's throwing axe") && player.dartanim == true) {
					return 17193;
				}
				if (weaponName.contains("pickaxe") || weaponName.contains("briefcase")) {
					switch (attackStyle) {
					case 2:
						return 400;
					default:
						return 401;
					}
				}
				if (weaponName.contains("dagger")) {
					switch (attackStyle) {
					case 2:
						return 377;
					default:
						return 376;
					}
				}
				if (weaponName.equals("abyssal bludgeon")) {
					return 7048;
				}
				if (weaponName.contains("2h sword") || weaponName.equals("dominion sword") || weaponName.equals("thok's sword") || weaponName.equals("saradomin sword")) {
					switch (attackStyle) {
					case 2:
						return 7048;
					case 3:
						return 7049;
					default:
						return 7041;
					}
				}
				if (weaponName.contains(" sword") && !weaponName.contains("anger sword") || weaponName.contains("saber") || weaponName.contains("longsword") || weaponName.contains("sabre") || weaponName.contains("light") || weaponName.contains("excalibur")) {

					switch (attackStyle) {
					case 2:
						return 12310;
					default:
						return 12311;
					}
				}
				if (weaponName.contains("hunter lance")) {
					switch (attackStyle) {
					case 0:
						return 13049;
					case 1:
						return 13048;
					default:
						return 401;
					}
				}
				if (weaponName.contains("rapier") || weaponId == 29970 || weaponName.contains("brackish")) {
					switch (attackStyle) {
					case 2:
						return 13048;
					default:
						return 13049;
					}
				}
				if (weaponName.contains("katana")) {
					switch (attackStyle) {
					case 2:
						return 1882;
					default:
						return 1884;
					}
				}
				if (weaponName.contains("godsword") || weaponId == 29874 || weaponId == 13666 || weaponId == 29545 || weaponId == 7806) {
					switch (attackStyle) {
					case 2:
						return 11980;
					case 3:
						return 11981;
					default:
						return 11979;
					}
				}
				if (weaponName.contains("greataxe") || weaponName.contains("noxious scythe")) {
					switch (attackStyle) {
					case 2:
						return 12003;
					default:
						return 12002;
					}
				}
				if (weaponName.contains("granite maul")) {
					switch (attackStyle) {
					default:
						return 1665;
					}
				}

			}
		}
		switch (weaponId) {
		case 16405:// novite maul
		case 16407:// Bathus maul
		case 16409:// Maramaros maul
		case 16411:// Kratonite maul
		case 16413:// Fractite maul
		case 18353:// chaotic maul
		case 16415:// Zephyrium maul
		case 16417:// Argonite maul
		case 16419:// Katagon maul
		case 16421:// Gorgonite maul
		case 16423:// Promethium maul
		case 16425:// primal maul
			return 2661; // maul
		case 13883: // morrigan thrown axe
			return 10504;
		case 15241:
		case 29368:
			return 12174;
		default:
			switch (attackStyle) {
			case 1:
				return 423;
			default:
				return 422; // todo default emote
			}
		}
	}

	private void doDefenceEmote() {
		target.setNextAnimationNoPriority(new Animation(Combat.getDefenceEmote(target)));

	}

	private int getMeleeCombatDelay(Player player, int weaponId) {
		if (weaponId != -1) {
			String weaponName = ItemDefinitions.getItemDefinitions(weaponId).getName().toLowerCase();

			// Interval 2.4

			if (weaponName.equals("zaros godsword")) {
				return 3;
			}
			if (weaponName.equals("zamorakian spear") || weaponName.equals("korasi's sword")) {
				return 3;
			}
			// Interval 3.6
			if (weaponName.contains("godsword") || weaponName.contains("warhammer") || weaponName.contains("battleaxe") || weaponName.contains("maul") || weaponName.equals("dominion sword")) {
				return 5;
			}
			// Interval 4.2
			if (weaponName.contains("greataxe") || weaponName.contains("halberd") || weaponName.contains("2h sword") || weaponName.contains("two handed sword") || weaponName.equals("thok's sword")) {
				return 6;
			}
			// Interval 3.0
			if (weaponName.contains("spear") || weaponName.contains(" sword") || weaponName.contains("anger ") || weaponName.contains("scythe of ") || weaponName.contains("longsword") || weaponName.contains("light") || weaponName.contains("hatchet") || weaponName.contains("pickaxe") || weaponName.contains("mace") || weaponName.contains("hasta") || weaponName.contains("warspear") || weaponName.contains("noxious scythe") || weaponName.contains("flail") || weaponName.contains("hammers")) {
				return 4;
			}
		}
		switch (weaponId) {
		case 6527:// tzhaar-ket-em
		case 29474:
			return 4;
		case 10887:// barrelchest anchor
			return 5;
		case 15403:// balmung
		case 6528:// tzhaar-ket-om
			return 6;
		default:
			return 3;
		}
	}

	@Override
	public void stop(final Player player) {
		player.setNextFaceEntity(null);
	}

	private boolean checkAll(Player player) {
		if (player.isDead() || player.hasFinished() || target.isDead() || target.hasFinished()) {
			return false;
		}
		int distanceX = player.getX() - target.getX();
		int distanceY = player.getY() - target.getY();
		int size = target.getSize();
		int maxDistance = 16;
		if (player.getPlane() != target.getPlane() || distanceX > size + maxDistance || distanceX < -1 - maxDistance || distanceY > size + maxDistance || distanceY < -1 - maxDistance) {
			return false;
		}
		if (player.getFreezeDelay() >= Utils.currentTimeMillis()) {
			if (player.withinDistance(target, 0)) {
				return false;
			}
			return true;
		}
		if (target instanceof Player) {
			Player p2 = (Player) target;
			if (player.isPvpMode() && !p2.isPvpMode()) {
				player.sendMessage("Sorry, but you cannot attack a player of this game mode.");
				return false;
			}
			if (!player.isPvpMode() && p2.isPvpMode()) {
				player.sendMessage("Sorry, but you cannot attack a player of this game mode.");
				return false;
			}
			if (!player.isCanPvp() || !p2.isCanPvp()) {
				return false;
			}
		} else {

			NPC n = (NPC) target;
			if (n.isCantInteract()) {
				return false;
			}
			if (n instanceof Familiar) {
				Familiar familiar = (Familiar) n;
				if (!familiar.canAttack(target)) {
					return false;
				}
			} else {
				if (!n.canBeAttackFromOutOfArea() && !MapAreas.isAtArea(n.getMapAreaNameHash(), player)) {
					return false;
				}
				if (player.getEquipment().getWeaponId() == 25202 && Settings.DOUBLEDROPS == true || player.getEquipment().getWeaponId() == 25202 && player.isHappyHour()) {
					player.getPackets().sendGameMessage("You cannot use deathtouched darts whilst double drops is active!");
					return false;

				}
				if (n.getId() == 15581 || n.getId() == 12878 || n.getId() == 30149 || n.getId() == 10057 || n.getId() == 7891) {
					if (player.getEquipment().getWeaponId() == 25202 && player.getRights() < 2) {

						player.getPackets().sendGameMessage("You cannot fight this monster with a death touch dart");
						return false;

					}
				}
				if (n.getId() >= 38792 && n.getId() <= 38795) {
					if (!Combat.hasWyvernProtection(player)) {
						player.getPackets().sendGameMessage("You cannot attack these Wyverns without either an Elemental shield, Dragonfire shield or Ancient wyvern shield.");
						return false;
					}
				}
				if (n.getId() == 8281) {
					if (player.canattackbalancelemental != true) {

						player.getPackets().sendGameMessage("You must deposit a Key of the Rich before attacking this monster.");
						return false;

					}
				}
				if (n.getId() == 39615 || n.getId() == 39620 || n.getId() == 39621) {
					if (player.getTask() == null && !(player.getControlerManager().getControler() instanceof SlayerSurvival)) {
						player.getPackets().sendGameMessage("You need to have this monster as a slayer task in order to fight it.");
						return false;
					}
					if (!(player.getControlerManager().getControler() instanceof SlayerSurvival)) {
						if (!n.getDefinitions().name.toLowerCase().contains(player.getTask().getName().toLowerCase())) {

						player.getPackets().sendGameMessage("You need to have this monster as a slayer task in order to fight it.");
						return false;

					}
					}
					if (player.getSkills().getLevel(Skills.SLAYER) < 95 && !(player.getControlerManager().getControler() instanceof SlayerSurvival)) {

						player.getPackets().sendGameMessage("You need at least a slayer level of 95 to fight this monster.");
						return false;
					}
				}
				if (n.getId() == 31498 || n.getId() == 31499) {
					if (player.getTask() == null && !(player.getControlerManager().getControler() instanceof SlayerSurvival)) {
						player.getPackets().sendGameMessage("You need to have this monster as a slayer task in order to fight it.");
						return false;
					}
					if (!(player.getControlerManager().getControler() instanceof SlayerSurvival)) {
						if (!n.getDefinitions().name.toLowerCase().contains(player.getTask().getName().toLowerCase())) {

						player.getPackets().sendGameMessage("You need to have this monster as a slayer task in order to fight it.");
						return false;

					}
					}
					if (player.getSkills().getLevel(Skills.SLAYER) < 93 && !(player.getControlerManager().getControler() instanceof SlayerSurvival)) {

						player.getPackets().sendGameMessage("You need at least a slayer level of 93 to fight this monster.");
						return false;
					}
				}
				if (n.getId() == 36862) {
					if (player.getSkills().getLevel(Skills.SLAYER) < 91 && !(player.getControlerManager().getControler() instanceof SlayerSurvival)) {

						player.getPackets().sendGameMessage("You need at least a slayer level of 91 to fight this monster.");
						return false;
					}
				}
				if (n.getId() == 39609) {
					if (player.getSkills().getLevel(Skills.SLAYER) < 95 && !(player.getControlerManager().getControler() instanceof SlayerSurvival)) {

						player.getPackets().sendGameMessage("You need at least a slayer level of 95 to fight this monster.");
						return false;
					}
				}
				if (n.getId() == 39612) {
					if (player.getSkills().getLevel(Skills.SLAYER) < 85 && !(player.getControlerManager().getControler() instanceof SlayerSurvival)) {

						player.getPackets().sendGameMessage("You need at least a slayer level of 85 to fight this monster.");
						return false;
					}
				}
				if (n.getId() == 39611 || n.getId() == 39610) {
					if (player.getSkills().getLevel(Skills.SLAYER) < 62 && !(player.getControlerManager().getControler() instanceof SlayerSurvival)) {

						player.getPackets().sendGameMessage("You need at least a slayer level of 62 to fight this monster.");
						return false;
					}
				}
				if (n.getId() == 39614) {
					if (player.getSkills().getLevel(Skills.SLAYER) < 44 && !(player.getControlerManager().getControler() instanceof SlayerSurvival)) {

						player.getPackets().sendGameMessage("You need at least a slayer level of 44 to fight this monster.");
						return false;
					}
				}
				if (n.getId() == 36886 || n.getId() == 36890 || n.getId() == 36891) {
					if (player.getTask() == null && !(player.getControlerManager().getControler() instanceof SlayerSurvival)) {
						player.getPackets().sendGameMessage("You need to have this monster as a slayer task in order to fight it.");
						return false;
					}
					if (!(player.getControlerManager().getControler() instanceof SlayerSurvival)) {
						if (!n.getDefinitions().name.toLowerCase().contains(player.getTask().getName().toLowerCase())) {

						player.getPackets().sendGameMessage("You need to have this monster as a slayer task in order to fight it.");
						return false;

					}
					}
					if (player.getSkills().getLevel(Skills.SLAYER) < 85 && !(player.getControlerManager().getControler() instanceof SlayerSurvival)) {

						player.getPackets().sendGameMessage("You need at least a slayer level of 85 to fight this monster.");
						return false;
					}
				}
				if (n.getId() == 30027) {
					if (player.getTask() == null && !(player.getControlerManager().getControler() instanceof SlayerSurvival)) {
						player.getPackets().sendGameMessage("You need to have this monster as a slayer task in order to fight it.");
						return false;
					}
					if (!(player.getControlerManager().getControler() instanceof SlayerSurvival)) {
						if (!n.getDefinitions().name.toLowerCase().equalsIgnoreCase(player.getTask().getName().toLowerCase())) {

						player.getPackets().sendGameMessage("You need to have this monster as a slayer task in order to fight it.");
						return false;

					}
					}
					if (player.getSkills().getLevel(Skills.SLAYER) < 90 && !(player.getControlerManager().getControler() instanceof SlayerSurvival)) {

						player.getPackets().sendGameMessage("You need at least a slayer level of 90 to fight this monster.");
						return false;
					}
				}
				if (n.getId() == 16385) {
					if (player.getTask() == null && !(player.getControlerManager().getControler() instanceof SlayerSurvival)) {
						player.getPackets().sendGameMessage("You need to have this monster as a slayer task in order to fight it.");
						return false;
					}
					if (!(player.getControlerManager().getControler() instanceof SlayerSurvival)) {
						if (!n.getDefinitions().name.toLowerCase().equalsIgnoreCase(player.getTask().getName().toLowerCase())) {

						player.getPackets().sendGameMessage("You need to have this monster as a slayer task in order to fight it.");
						return false;

					}
					}
					if (player.getSkills().getLevel(Skills.SLAYER) < 87 && !(player.getControlerManager().getControler() instanceof SlayerSurvival)) {

						player.getPackets().sendGameMessage("You need at least a slayer level of 87 to fight this monster.");
						return false;
					}
				}
				if (n.getId() == 15114) {
					if (player.getTask() == null && !(player.getControlerManager().getControler() instanceof SlayerSurvival)) {
						player.getPackets().sendGameMessage("You need to have this monster as a slayer task in order to fight it.");
						return false;
					}
					if (!(player.getControlerManager().getControler() instanceof SlayerSurvival)) {
						if (!n.getDefinitions().name.toLowerCase().equalsIgnoreCase(player.getTask().getName().toLowerCase())) {

						player.getPackets().sendGameMessage("You need to have this monster as a slayer task in order to fight it.");
						return false;

					}
					}
					if (player.getSkills().getLevel(Skills.SLAYER) < 97 && !(player.getControlerManager().getControler() instanceof SlayerSurvival)) {

						player.getPackets().sendGameMessage("You need at least a slayer level of 97 to fight this monster.");
						return false;
					}
				}
				if (n.getId() == 20629 || n.getId() == 20630) {
					if (player.getTask() == null && !(player.getControlerManager().getControler() instanceof SlayerSurvival)) {
						player.getPackets().sendGameMessage("You need to have this monster as a slayer task in order to fight it.");
						return false;
					}
					if (!(player.getControlerManager().getControler() instanceof SlayerSurvival)) {
						if (!n.getDefinitions().name.toLowerCase().equalsIgnoreCase(player.getTask().getName().toLowerCase())) {

						player.getPackets().sendGameMessage("You need to have this monster as a slayer task in order to fight it.");
						return false;

					}
					}
					if (player.getSkills().getLevel(Skills.SLAYER) < 94 && !(player.getControlerManager().getControler() instanceof SlayerSurvival)) {

						player.getPackets().sendGameMessage("You need at least a slayer level of 94 to fight this monster.");
						return false;
					}
				}
				if (n.getId() == 16905 || n.getId() == 16906 || n.getId() == 16907) {
					if (player.getTask() == null && !(player.getControlerManager().getControler() instanceof SlayerSurvival)) {
						player.getPackets().sendGameMessage("You need to have this monster as a slayer task in order to fight it.");
						return false;
					}
					if (!(player.getControlerManager().getControler() instanceof SlayerSurvival)) {
						if (!n.getDefinitions().name.toLowerCase().contains(player.getTask().getName().toLowerCase())) {

						player.getPackets().sendGameMessage("You need to have this monster as a slayer task in order to fight it.");
						return false;

					}
					}
					if (player.getSkills().getLevel(Skills.SLAYER) < 67 && !(player.getControlerManager().getControler() instanceof SlayerSurvival)) {

						player.getPackets().sendGameMessage("You need at least a slayer level of 67 to fight this monster.");
						return false;
					}
				}
				if (n.getId() == 1615) {
					if (player.getSkills().getLevel(Skills.SLAYER) < 85 && !(player.getControlerManager().getControler() instanceof SlayerSurvival)) {

						player.getPackets().sendGameMessage("You need at least a slayer level of 85 to fight this monster.");
						return false;

					}
				}
				if (n.getId() == 17144 || n.getId() == 17145 || n.getId() == 17146 || n.getId() == 17147) {
					if (player.getSkills().getLevel(Skills.SLAYER) < 81 && !(player.getControlerManager().getControler() instanceof SlayerSurvival)) {

						player.getPackets().sendGameMessage("You need at least a slayer level of 81 to fight this monster.");
						return false;

					}
				} else if (n.getId() == 14696 || n.getId() == 14697 || n.getId() == 14698 || n.getId() == 14699) {
					if (player.getSkills().getLevel(Skills.SLAYER) < 90 && !(player.getControlerManager().getControler() instanceof SlayerSurvival)) {

						player.getPackets().sendGameMessage("You need at least a slayer level of 90 to fight this monster.");
						return false;

					}
				} else if (n.getId() == 30008 || n.getId() == 15222 || n.getId() == 30000 || n.getId() == 30009 || n.getId() == 10106 || n.getId() == 3334 || n.getId() == 30025 || n.getId() == 30085) {
					if (player.getSkills().getLevel(Skills.SLAYER) < 95 && !(player.getControlerManager().getControler() instanceof SlayerSurvival)) {

						player.getPackets().sendGameMessage("You need at least a slayer level of 95 to fight this monster.");
						return false;

					}
				
				} else if (n.getId() == 9463) {
					if (player.getSkills().getLevel(Skills.SLAYER) < 93 && !(player.getControlerManager().getControler() instanceof SlayerSurvival)) {

						player.getPackets().sendGameMessage("You need at least a slayer level of 93 to fight this monster.");
						return false;

					}
				} else if (n.getId() == 13822) {
					if (player.getSkills().getLevel(Skills.SLAYER) < 91 && !(player.getControlerManager().getControler() instanceof SlayerSurvival)) {

						player.getPackets().sendGameMessage("You need at least a slayer level of 91 to fight this monster.");
						return false;

					}
				} else if (n.getId() == 2783) {
					if (player.getSkills().getLevel(Skills.SLAYER) < 90 && !(player.getControlerManager().getControler() instanceof SlayerSurvival)) {

						player.getPackets().sendGameMessage("You need at least a slayer level of 90 to fight this monster.");
						return false;

					}
				} else if (n.getId() == 1613) {
					if (player.getSkills().getLevel(Skills.SLAYER) < 80 && !(player.getControlerManager().getControler() instanceof SlayerSurvival)) {

						player.getPackets().sendGameMessage("You need at least a slayer level of 80 to fight this monster.");
						return false;

					}
				} else if (n.getId() == 9964) {
					if (player.getSkills().getLevel(Skills.DUNGEONEERING) < 115) {

						player.getPackets().sendGameMessage("You need at least a dungeoneering level of 115 to fight this monster.");
						return false;

					}
				}
				if (n.getId() == 14578) {
					if (player.getEquipment().getWeaponId() != 2402 && player.getCombatDefinitions().getAutoCastSpell() <= 0 && !hasPolyporeStaff(player) && !hasTridentSeas(player) && !hasSanguinestiStaff(player) && !hasTridentSwamp(player)) {
						player.getPackets().sendGameMessage("I'd better wield Silverlight first.");
						return false;
					} else {
						int slayerLevel = Combat.getSlayerLevelForNPC(n.getId());
						if (slayerLevel > player.getSkills().getLevel(Skills.SLAYER) && !(player.getControlerManager().getControler() instanceof SlayerSurvival)) {
							player.getPackets().sendGameMessage("You need at least a slayer level of " + slayerLevel + " to fight this.");
							return false;
						}
					}
				} else if (n.getId() == 6222 || n.getId() == 6223 || n.getId() == 6225 || n.getId() == 6227) {
					if (isRanging(player) == 0) {
						player.getPackets().sendGameMessage("I can't reach that!");
						return false;
					}
				}
			}
		}
		if (!(target instanceof NPC && ((NPC) target).isForceMultiAttacked())) {

			if (!target.isAtMultiArea() || !player.isAtMultiArea()) {
				if (player.getAttackedBy() != target && player.getAttackedByDelay() > Utils.currentTimeMillis()) {
					return false;
				}
				if (target.getAttackedBy() != player && target.getAttackedByDelay() > Utils.currentTimeMillis()) {
					return false;
				}
			}
		}
		int isRanging = isRanging(player);
		if (distanceX < size && distanceX > -1 && distanceY < size && distanceY > -1 && !target.hasWalkSteps()) {
			player.resetWalkSteps();
			if (!player.addWalkSteps(target.getX() + size, target.getY())) {
				player.resetWalkSteps();
				if (!player.addWalkSteps(target.getX() - 1, target.getY())) {
					player.resetWalkSteps();
					if (!player.addWalkSteps(target.getX(), target.getY() + size)) {
						player.resetWalkSteps();
						if (!player.addWalkSteps(target.getX(), target.getY() - 1)) {
							return false;
						}
					}
				}
			}
			return true;
		} else if (isRanging == 0 && target.getSize() == 1 && player.getCombatDefinitions().getSpellId() <= 0 && !hasPolyporeStaff(player) && !hasTridentSeas(player) && !hasSanguinestiStaff(player) && !hasTridentSwamp(player) && Math.abs(player.getX() - target.getX()) == 1 && Math.abs(player.getY() - target.getY()) == 1 && !target.hasWalkSteps()) {
			if (!player.addWalkSteps(target.getX(), player.getY(), 1)) {
				player.addWalkSteps(player.getX(), target.getY(), 1);
			}
			return true;
		}
		maxDistance = isRanging != 0 || player.getCombatDefinitions().getSpellId() > 0 || hasTridentSeas(player) || hasSanguinestiStaff(player) || hasTridentSwamp(player) || hasPolyporeStaff(player) ? 7 : 0;
		if (!player.clipedProjectile(target, maxDistance == 0 && !forceCheckClipAsRange(target)) || distanceX > size + maxDistance || distanceX < -1 - maxDistance || distanceY > size + maxDistance || distanceY < -1 - maxDistance) {
			if (!player.hasWalkSteps()) {
				player.resetWalkSteps();
				player.addWalkStepsInteract(target.getX(), target.getY(), player.getRun() ? 2 : 1, size, true);
			}
			return true;
		} else {
			player.resetWalkSteps();
		}
		if (player.getPolDelay() >= Utils.currentTimeMillis() && !(player.getEquipment().getWeaponId() == 15486 || player.getEquipment().getWeaponId() == 22207 || player.getEquipment().getWeaponId() == 22209 || player.getEquipment().getWeaponId() == 22211 || player.getEquipment().getWeaponId() == 22213)) {
			player.setPolDelay(0);
		}
		player.getTemporaryAttributtes().put("last_target", target);
		if (target != null) {
			target.getTemporaryAttributtes().put("last_attacker", player);
		}
		if (player.getCombatDefinitions().isInstantAttack()) {
			player.getCombatDefinitions().setInstantAttack(false);
			if (player.getCombatDefinitions().getAutoCastSpell() > 0) {
				return true;
			}
			if (player.getCombatDefinitions().isUsingSpecialAttack()) {
				if (!specialExecute(player)) {
					return true;
				}
				player.getActionManager().setActionDelay(0);
				int weaponId = player.getEquipment().getWeaponId();
				int attackStyle = player.getCombatDefinitions().getAttackStyle();
				switch (weaponId) {
				case 4153:
					player.setNextAnimation(new Animation(1667));
					player.setNextGraphics(new Graphics(340, 0, 96 << 16));
					delayNormalHit(weaponId, attackStyle, getMeleeHit(player, getRandomMaxHit(player, weaponId, attackStyle, false, true, 1.1, true)));
					break;
				}
				player.getActionManager().setActionDelay(4);
			}
			return true;
		}
		return true;
	}

	public static boolean specialExecute(Player player) {
		int weaponId = player.getEquipment().getWeaponId();
		player.getCombatDefinitions().switchUsingSpecialAttack();
		int specAmt = getSpecialAmmount(weaponId);
		if (specAmt == 0) {
			player.getPackets().sendGameMessage("This weapon has no special Attack, if you still see special bar please relogin.");
			player.getCombatDefinitions().desecreaseSpecialAttack(0);
			return false;
		}
		if (player.getCombatDefinitions().hasRingOfVigour()) {
			specAmt *= 0.9;
		}
		if (player.getCombatDefinitions().getSpecialAttackPercentage() < specAmt) {
			player.getPackets().sendGameMessage("You don't have enough power left.");
			player.getCombatDefinitions().desecreaseSpecialAttack(0);
			return false;
		}
		player.getCombatDefinitions().desecreaseSpecialAttack(specAmt);
		return true;
	}

	/*
	 * 0 not ranging, 1 invalid ammo so stops att, 2 can range, 3 no ammo
	 */
	public static final int isRanging(Player player) {
		int weaponId = player.getEquipment().getWeaponId();
		if (weaponId == -1) {
			return 0;
		}
		String name = ItemDefinitions.getItemDefinitions(weaponId).getName();
		if (name != null) { // those dont need arrows
			if (name.contains("knife") || name.contains("dart") || name.contains("javelin") || name.contains("thrownaxe") || name.contains("throwing axe") || name.contains("crystal bow") || name.equalsIgnoreCase("craw's bow") || name.equalsIgnoreCase("zaryte bow") || name.equalsIgnoreCase("toxic blowpipe") || name.equalsIgnoreCase("mutated toxic blowpipe") || name.equalsIgnoreCase("crystal bow full") || name.equalsIgnoreCase("noxious bow") || name.equalsIgnoreCase("seren godbow") || name.equalsIgnoreCase("sagittarian longbow") || name.equalsIgnoreCase("strykebow") || name.contains("chinchompa") || name.contains("Bolas") || weaponId == 29784 || weaponId == 29368) {
				return 2;
			}
		}
		int ammoId = player.getEquipment().getAmmoId();
		switch (weaponId) {
		case 15241: // Hand cannon
			switch (ammoId) {
			case -1:
				return 3;
			case 15243: // bronze arrow
				return 2;
			default:
				return 1;
			}
		case 839: // longbow
		case 841: // shortbow
			switch (ammoId) {
			case -1:
				return 3;
			case 882: // bronze arrow
			case 884: // iron arrow
				return 2;
			default:
				return 1;
			}
		case 843: // oak longbow
		case 845: // oak shortbow
			switch (ammoId) {
			case -1:
				return 3;
			case 882: // bronze arrow
			case 884: // iron arrow
			case 886: // steel arrow
				return 2;
			default:
				return 1;
			}
		case 847: // willow longbow
		case 849: // willow shortbow
		case 13541: // Willow composite bow
			switch (ammoId) {
			case -1:
				return 3;
			case 882: // bronze arrow
			case 884: // iron arrow
			case 886: // steel arrow
			case 888: // mithril arrow
				return 2;
			default:
				return 1;
			}
		case 851: // maple longbow
		case 853: // maple shortbow
		case 18331: // Maple longbow (sighted)
			switch (ammoId) {
			case -1:
				return 3;
			case 882: // bronze arrow
			case 884: // iron arrow
			case 886: // steel arrow
			case 888: // mithril arrow
			case 890: // adamant arrow
				return 2;
			default:
				return 1;
			}
		case 2883:// ogre bow
			switch (ammoId) {
			case -1:
				return 3;
			case 2866: // ogre arrow
				return 2;
			default:
				return 1;
			}
		case 4827:// Comp ogre bow
			switch (ammoId) {
			case -1:
				return 3;
			case 2866: // ogre arrow
			case 4773: // bronze brutal
			case 4778: // iron brutal
			case 4783: // steel brutal
			case 4788: // black brutal
			case 4793: // mithril brutal
			case 4798: // adamant brutal
			case 4803: // rune brutal
				return 2;
			default:
				return 1;
			}
		case 855: // yew longbow
		case 857: // yew shortbow
		case 10281: // Yew composite bow
		case 14121: // Sacred clay bow
		case 859: // magic longbow
		case 861: // magic shortbow
		case 10284: // Magic composite bow
		case 18332: // Magic longbow (sighted)
		case 6724: // seercull

			
			switch (ammoId) {
			case -1:
				return 3;
			case 882: // bronze arrow
			case 884: // iron arrow
			case 886: // steel arrow
			case 888: // mithril arrow
			case 890: // adamant arrow
			case 892: // rune arrow
				return 2;
			default:
				return 1;
			}
		case 24338: // Royal crossbow
			switch (ammoId) {
			case -1:
				return 3;
			case 877: // bronze bolts
			case 9140: // iron bolts
			case 9141: // steel bolts
			case 13083: // black bolts
			case 9142:// mithril bolts
			case 9143: // adam bolts
			case 9145: // silver bolts wtf
			case 9236: // Opal bolts (e)
			case 9238: // Pearl bolts (e)
			case 9239: // Topaz bolts (e)
			case 9240: // Sapphire bolts (e)
			case 9241: // Emerald bolts (e)
				// case 9242: // Ruby bolts (e)
			case 9243: // Diamond bolts (e)
			case 24336: // Coral bolts
				return 2;
			default:
				return 1;
			}
		case 16337:
			switch (ammoId) {
			case -1:
				return 3;
			case 882: // bronze arrow
			case 884: // iron arrow
			case 886: // steel arrow
			case 888: // mithril arrow
			case 890: // adamant arrow
			case 892: // rune arrow
			case 11212: // dragon arrow
			case 14545: // super dragon arrow
			case 16477: // sag arrows

				return 2;
			default:
				return 1;
			}
		case 11235: // dark bows
		case 15701:
		case 15702:
		case 15703:
		case 15704:
		case 29782:
		case 20171:
		case 29476:
		case 29547:
		case 24474:
		case 29472:
			// case 16337:
			switch (ammoId) {
			case -1:
				return 3;
			case 882: // bronze arrow
			case 884: // iron arrow
			case 886: // steel arrow
			case 888: // mithril arrow
			case 890: // adamant arrow
			case 892: // rune arrow
			case 11212: // dragon arrow
			case 14545: // super dragon arrow

				return 2;
			default:
				return 1;
			}
		case 29594:
			switch (ammoId) {
			case -1:
				return 3;
			case 29593: // dragon jav
			case 830: // rune jav
				return 2;
			default:
				return 1;
			}
		case 19143: // saradomin bow
			switch (ammoId) {
			case -1:
				return 3;
			case 19152: // saradomin arrow
			case 882: // bronze arrow
			case 884: // iron arrow
			case 886: // steel arrow
			case 888: // mithril arrow
			case 890: // adamant arrow
			case 892: // rune arrow
				return 2;
			default:
				return 1;
			}
		case 19146: // guthix bow
			switch (ammoId) {
			case -1:
				return 3;
			case 19157: // guthix arrow
			case 882: // bronze arrow
			case 884: // iron arrow
			case 886: // steel arrow
			case 888: // mithril arrow
			case 890: // adamant arrow
			case 892: // rune arrow
				return 2;
			default:
				return 1;
			}
		case 19149: // zamorak bow
			switch (ammoId) {
			case -1:
				return 3;
			case 19162: // zamorak arrow
			case 882: // bronze arrow
			case 884: // iron arrow
			case 886: // steel arrow
			case 888: // mithril arrow
			case 890: // adamant arrow
			case 892: // rune arrow
				return 2;
			default:
				return 1;
			}
		case 24303: // Coral crossbow
			switch (ammoId) {
			case -1:
				return 3;
			case 24304: // Coral bolts
				return 2;
			default:
				return 1;
			}
		case 4734: // karil crossbow
			switch (ammoId) {
			case -1:
				return 3;
			case 4740: // bolt rack
				return 2;
			default:
				return 1;
			}
		case 10156: // hunters crossbow
			switch (ammoId) {
			case -1:
				return 3;
			case 10158: // Kebbit bolts
			case 10159: // Long kebbit bolts
				return 2;
			default:
				return 1;
			}
		case 8880: // Dorgeshuun c'bow
			switch (ammoId) {
			case -1:
				return 3;
			case 877: // bronze bolts
			case 9140: // iron bolts
			case 8882: // bone bolts
				return 2;
			default:
				return 1;
			}
		case 14684: // zanik crossbow
			switch (ammoId) {
			case -1:
				return 3;
			case 877: // bronze bolts
			case 9140: // iron bolts
			case 9141: // steel bolts
			case 13083: // black bolts
			case 9142:// mithril bolts
			case 9143: // adam bolts
			case 9145: // silver bolts
			case 8882: // bone bolts
				return 2;
			default:
				return 1;
			}
		case 767: // phoenix crossbow
		case 837: // crossbow
			switch (ammoId) {
			case -1:
				return 3;
			case 877: // bronze bolts
				return 2;
			default:
				return 1;
			}
		case 9174: // bronze crossbow
			switch (ammoId) {
			case -1:
				return 3;
			case 877: // bronze bolts
			case 9236: // Opal bolts (e)
				return 2;
			default:
				return 1;
			}
		case 9176: // blurite crossbow
			switch (ammoId) {
			case -1:
				return 3;
			case 877: // bronze bolts
			case 9140: // iron bolts
			case 9141: // steel bolts
			case 13083: // black bolts
			case 9236: // Opal bolts (e)
			case 9238: // Pearl bolts (e)
			case 9239: // Topaz bolts (e)
			case 9139: // Blurite bolts
			case 9237: // Jade bolts (e)
				return 2;
			default:
				return 1;
			}
		case 9177: // iron crossbow
			switch (ammoId) {
			case -1:
				return 3;
			case 877: // bronze bolts
			case 9140: // iron bolts
			case 9236: // Opal bolts (e)
			case 9238: // Pearl bolts (e)
				return 2;
			default:
				return 1;
			}
		case 9179: // steel crossbow
			switch (ammoId) {
			case -1:
				return 3;
			case 877: // bronze bolts
			case 9140: // iron bolts
			case 9141: // steel bolts
			case 9236: // Opal bolts (e)
			case 9238: // Pearl bolts (e)
			case 9239: // Topaz bolts (e)
				return 2;
			default:
				return 1;
			}
		case 13081: // black crossbow
			switch (ammoId) {
			case -1:
				return 3;
			case 877: // bronze bolts
			case 9140: // iron bolts
			case 9141: // steel bolts
			case 13083: // black bolts
			case 9236: // Opal bolts (e)
			case 9238: // Pearl bolts (e)
			case 9239: // Topaz bolts (e)
				return 2;
			default:
				return 1;
			}
		case 9181: // Mith crossbow
			switch (ammoId) {
			case -1:
				return 3;
			case 877: // bronze bolts
			case 9140: // iron bolts
			case 9141: // steel bolts
			case 13083: // black bolts
			case 9142:// mithril bolts
			case 9145: // silver bolts
			case 9236: // Opal bolts (e)
			case 9238: // Pearl bolts (e)
			case 9239: // Topaz bolts (e)
			case 9240: // Sapphire bolts (e)
			case 9241: // Emerald bolts (e)
				return 2;
			default:
				return 1;
			}
		case 9183: // adam c bow
			switch (ammoId) {
			case -1:
				return 3;
			case 877: // bronze bolts
			case 9140: // iron bolts
			case 9141: // steel bolts
			case 13083: // black bolts
			case 9142:// mithril bolts
			case 9143: // adam bolts
			case 9145: // silver bolts wtf
			case 9236: // Opal bolts (e)
			case 9238: // Pearl bolts (e)
			case 9239: // Topaz bolts (e)
			case 9240: // Sapphire bolts (e)
			case 9241: // Emerald bolts (e)
			case 9242: // Ruby bolts (e)
			case 9243: // Diamond bolts (e)
				return 2;
			default:
				return 1;
			}
		case 29967:
		case 29806:
		case 33318:
		case 33319:
		case 33384:
		case 29149:
		case 33385:
		case 33450:
		case 33451:
			switch (ammoId) {
			case -1:
				return 3;
			case 877: // bronze bolts
			case 9140: // iron bolts
			case 9141: // steel bolts
			case 13083: // black bolts
			case 9142:// mithril bolts
			case 9143: // adam bolts
			case 9144: // rune bolts
			case 9145: // silver bolts wtf
			case 9236: // Opal bolts (e)
			case 9238: // Pearl bolts (e)
			case 9239: // Topaz bolts (e)
			case 9240: // Sapphire bolts (e)
			case 9241: // Emerald bolts (e)
			case 9242: // Ruby bolts (e)
			case 9243: // Diamond bolts (e)
			case 9244: // Dragon bolts (e)
			case 29616: // Drygon bolts (e)
			case 9245: // Onyx bolts (e)
			case 24116: // Bakriminel bolts
			case 29917: // Ascension bolt
				return 2;
			default:
				return 1;
			}
		case 9185: // rune c bow
		case 18357: // chaotic crossbow
		case 29406:
		case 29432:
		case 18358:
		case 25037:
			// case 29916:
			// case 29816:
			switch (ammoId) {
			case -1:
				return 3;
			case 877: // bronze bolts
			case 9140: // iron bolts
			case 9141: // steel bolts
			case 13083: // black bolts
			case 9142:// mithril bolts
			case 9143: // adam bolts
			case 9144: // rune bolts
			case 9145: // silver bolts wtf
			case 9236: // Opal bolts (e)
			case 9238: // Pearl bolts (e)
			case 9239: // Topaz bolts (e)
			case 9240: // Sapphire bolts (e)
			case 9241: // Emerald bolts (e)
			case 9242: // Ruby bolts (e)
			case 9243: // Diamond bolts (e)
			case 9244: // Dragon bolts (e)
			case 29616: // Drygon bolts (e)
			case 9245: // Onyx bolts (e)
			case 24116: // Bakriminel bolts
				return 2;
			default:
				return 1;
			}
		default:
			return 0;
		}
	}

	/**
	 * Checks if the player is wielding polypore staff.
	 * 
	 * @param player
	 *            The player.
	 * @return {@code True} if so.
	 */
	private static boolean hasPolyporeStaff(Player player) {
		int weaponId = player.getEquipment().getWeaponId();
		return weaponId == 22494 || weaponId == 22496;
	}

	private static boolean hasTridentSeas(Player player) {
		int weaponId = player.getEquipment().getWeaponId();
		// System.out.println("1");
		return weaponId == 29463;
	}

	private static boolean hasTridentSwamp(Player player) {
		int weaponId = player.getEquipment().getWeaponId();
		// System.out.println("1");
		return weaponId == 29452;
	}

	private static boolean hasSanguinestiStaff(Player player) {
		int weaponId = player.getEquipment().getWeaponId();
		// System.out.println("1");
		return weaponId == 29333;
	}

	private static boolean hasBlowpipe(Player player) {
		int weaponId = player.getEquipment().getWeaponId();
		return weaponId == 29457 || weaponId == 29255;
	}

	private static boolean hasStaffScale(Player player) {
		int weaponId = player.getEquipment().getWeaponId();
		return weaponId == 29453 || weaponId == 29452;
	}

	private static boolean hasStaffofDarkness(Player player) {
		int weaponId = player.getEquipment().getWeaponId();
		return weaponId == 29475;
	}

	private static boolean hasStrykeBow(Player player) {
		int weaponId = player.getEquipment().getWeaponId();
		return weaponId == 29476;
	}

	private static boolean hasLavaWhip(Player player) {
		int weaponId = player.getEquipment().getWeaponId();
		return weaponId == 29474;
	}

	private static boolean hasWildyWep(Player player) {
		int weaponId = player.getEquipment().getWeaponId();
		return weaponId == 29379 || weaponId == 29377 || weaponId == 29378;
	}

	private static boolean hasAscBothHand(Player player) {
		int weaponId = player.getEquipment().getWeaponId();
		int shieldId = player.getEquipment().getShieldId();
		return weaponId == 29967 && shieldId == 29918;
	}

	public Entity getTarget() {
		return target;
	}
}
