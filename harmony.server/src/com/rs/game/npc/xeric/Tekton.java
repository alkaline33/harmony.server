package com.rs.game.npc.xeric;

import com.rs.Settings;
import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.player.Player;
import com.rs.game.player.content.Magic;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Colors;
import com.rs.utils.Utils;

@SuppressWarnings("serial")
public class Tekton extends NPC {

	public Tekton(int id, WorldTile tile, int mapAreaNameHash, boolean canBeAttackFromOutOfArea, boolean spawned) {
		super(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		setLureDelay(0);
		setForceMultiArea(true);
		setForceMultiAttacked(true);
		setCapDamage(1000);
		setForceTargetDistance(64);
		setCantFollowUnderCombat(true);
		setForceFollowClose(false);
		setCanFreezeThis(false);
		//count = 0;
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
		return 0.3;
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
					transformIntoNPC(38542);
					count = 0;
					for (Player player : World.getPlayers()) {
						if (!World.TektonRaids(player)) {
							continue;
						}
						int r;
						player.osrsraidscompleted++;
						if (player.osrsraiddamagepoints < 5000) {
							player.sendMessage(player.getDisplayName() + "-" + player.osrsraiddamagepoints + "");
							player.getBank().addItem(1965, 1, true);
							player.sendMessage(Colors.brown + "You have completed " + player.osrsraidscompleted + " raids. A cabbage has been added to your bank for your leeching efforts!");
						} else {
							/*player.dailyxericraids++;
							if (player.dailyxericraids == 2) {
								player.sendMessage(Colors.lightGray + "<img=6>You have completed the Daily Money Maker: Complete 2 Xeric raids! Your rewards have been placed in your bank.");
								player.getBank().addItem(989, 2, true);
								player.getBank().addItem(29438, 1, true);
							}*/

							player.getBank().addItem(29438, 1, true);
							player.sendMessage(Colors.brown + "You have completed " + player.osrsraidscompleted + " raids. A raids casket has been added to your bank!");
							player.sendMessage("You dealt " + Utils.format(player.osrsraiddamagepoints) + " throughout the raid.");
							if (Settings.eventdoublecaskets > 0) {
								player.getBank().addItem(29438, 1, true);
								player.sendMessage(Colors.brown + "Woah! You just got an extra casket because of the Double casket event!");
							}
							//SeasonEvent.HandleActivity(player, "Chambers of xeric", 0);
						}
						Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(Settings.RESPAWN_PLAYER_LOCATION));
						if (player.gotolmpet != true && player.osrsraiddamagepoints > 5000) {
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
								World.sendWorldMessage("<img=7><col=ff8c38>News: " + player.getDisplayName() + " has received an Olmlet pet.", false);
								player.gotolmpet = true;
								player.sendMessage("<col=330000>Congratulations! Please visit the Pet Tamer at home to get your pet.</col>");
								break;
							default:
								break;
							}
						}
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