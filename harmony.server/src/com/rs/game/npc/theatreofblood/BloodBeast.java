package com.rs.game.npc.theatreofblood;

import com.rs.Settings;
import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.player.Player;
import com.rs.game.player.content.Magic;
import com.rs.game.player.content.event.season.SeasonEvent;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Colors;
import com.rs.utils.Utils;

@SuppressWarnings("serial")
public class BloodBeast extends NPC {

	public BloodBeast(int id, WorldTile tile, int mapAreaNameHash, boolean canBeAttackFromOutOfArea, boolean spawned) {
		super(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		setLureDelay(0);
		setForceMultiArea(true);
		setForceMultiAttacked(true);
		setCapDamage(700);
		setForceTargetDistance(64);
		setCantFollowUnderCombat(true);
		setForceFollowClose(false);
		// count = 0;
	}

	int count;

	public int targetx;
	public int targety;

	public int getCount() {
		return count;
	}

	public int setCount(int Count) {
		return count = Count;
	}

	@Override
	public double getMeleePrayerMultiplier() {
		return 0.1;
	}

	@Override
	public double getRangePrayerMultiplier() {
		return 0.1;
	}

	@Override
	public double getMagePrayerMultiplier() {
		return 0.1;
	}

	@Override
	public void processNPC() {
		super.processNPC();
		if (isDead()) {
			return;
		}
		int maxhp = getMaxHitpoints();
		if (maxhp > getHitpoints() && getPossibleTargets().isEmpty() && !isUnderCombat()) {
			setHitpoints(maxhp);
		}
	}

	/*
	 * gotta override else setRespawnTask override doesnt work
	 */
	@Override
	public void sendDeath(Entity source) {
		final NPCCombatDefinitions defs = getCombatDefinitions();
		resetWalkSteps();
		getCombat().removeTarget();
		setNextAnimation(null);
		WorldTasksManager.schedule(new WorldTask() {
			int loop;

			@Override
			public void run() {

				if (loop == 0) {
					// transformIntoNPC(38542);
					count = 0;
					for (NPC bombs : World.getNPCs()) {
						if (bombs.getId() == 30218) {
							bombs.finish();
						}
					}
					for (Player player : World.getPlayers()) {
						if (!World.BloodBeastRaids(player)) {
							continue;
						}
						int r;
						player.theatreofbloodcompleted++;
						if (player.theatreofblooddamagepoints < 5000) {
							player.sendMessage(player.getDisplayName() + "-" + player.theatreofblooddamagepoints + "");
							player.getBank().addItem(1965, 1, true);
							player.sendMessage(Colors.brown + "You have completed " + player.theatreofbloodcompleted + " raids. A cabbage has been added to your bank for your leeching efforts!");
						} else {
							SeasonEvent.HandleActivity(player, "Theatre of Blood", 500);
							/*player.dailytheatreofblood++;
							if (player.dailytheatreofblood == 2) {
								player.sendMessage(Colors.lightGray + "<img=6>You have completed the Daily Money Maker: Complete 2 Theatre of Blood raids! Your rewards have been placed in your bank.");
								player.getBank().addItem(29328, 1, true);
							}*/
							player.getBank().addItem(29328, 1, true);
							player.sendMessage(Colors.brown + "You have completed " + player.theatreofbloodcompleted + " raids. A raids casket has been added to your bank!");
							player.sendMessage("You dealt " + Utils.format(player.theatreofblooddamagepoints) + " throughout the raid.");
							if (Settings.eventdoublecaskets > 0) {
								player.getBank().addItem(29328, 1, true);
								player.sendMessage(Colors.brown + "Woah! You just got an extra casket because of the Double casket event!");
							}
						}
						Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(Settings.RESPAWN_PLAYER_LOCATION));
						if (player.gotbloodpet != true && player.theatreofblooddamagepoints > 5000) {
							if (player.petperk) {
								r = Utils.random(44);
							} else {
								r = Utils.random(65);
							}
							if (Settings.eventbosspetchance > 0) {
								r /= 2;
							}
							switch (r) {
							case 0:
								World.sendWorldMessage("<img=7><col=ff8c38>News: " + player.getDisplayName() + " has received a Young Beast pet.", false);
								player.gotbloodpet = true;
								player.sendMessage("<col=330000>Congratulations! Please visit the Pet Tamer at home to get your pet.</col>");
								break;
							default:
								break;
							}
						}
						player.theatreofblooddamagepoints = 0;
						player.tobkilledboss1 = false;
						player.tobkilledboss2 = false;
						player.tobkilledboss3 = false;
					}
					setNextAnimation(new Animation(defs.getDeathEmote()));
				} else if (loop >= defs.getDeathDelay()) {
					reset();
					setLocation(getRespawnTile());
					finish();
					// drop();
					// drop();
					setRespawnTask();
					stop();
				}
				loop++;
			}
		}, 0, 1);
	}

}