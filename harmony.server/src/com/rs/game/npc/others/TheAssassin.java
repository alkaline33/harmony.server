package com.rs.game.npc.others;

import com.discord.Discord;
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
import com.rs.utils.Utils;

@SuppressWarnings("serial")
public class TheAssassin extends NPC {

	public TheAssassin(int id, WorldTile tile, int mapAreaNameHash, boolean canBeAttackFromOutOfArea, boolean spawned) {
		super(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		setLureDelay(0);
		setCapDamage(1500);
		setForceMultiArea(true);
		setCanFreezeThis(false);
	}

	int count;
	public boolean bleeding;

	public int getCount() {
		return count;
	}

	public int setCount(int Count) {
		return count = Count;
	}

	@Override
	public double getRangePrayerMultiplier() {
		return 0.2;
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
					for (Player player : World.getPlayers()) {
						if (!World.AssassinArea(player))
							continue;
						if (player.assassineventdamage < 500) {
							player.toggleLootShareIcy();
							player.sendMessage("You have been removed from lootshare for leeching!");
							Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(Settings.RESPAWN_PLAYER_LOCATION));
							continue;
						}
						int amount = player.assassineventdamage / 2;
						if (amount > 10000)
							amount = 10000;
						player.sendMessage("You dealt enough damage to the Assassin & are rewarded with " + Utils.format(amount) + " Event tokens for your efforts!");
						player.getInventory().addItemDrop(29226, amount);
						player.assassineventdamage = 0;
						player.AssassinKills++;
						if (player.gotassassinpet != true) {
							int r;
							if (player.petperk) {
								r = Utils.random(75);
							} else {
								r = Utils.random(100);
							}
							switch (r) {
							case 0:
								World.sendWorldMessage("<img=7><col=ff8c38>News: " + player.getDisplayName() + " has received the Enforcer pet.", false);
								player.gotassassinpet = true;
								player.sendMessage("<col=330000>Congratulations! Please visit the Pet Tamer at home to get your pet.</col>");
								Discord.sendSimpleMessage("NEWS: " + player.getDisplayName() + " has obtained the Enforcer pet!");
								break;
							default:
								break;
							}
						}
					}
					drop();
					drop();
					setNextAnimation(new Animation(defs.getDeathEmote()));
				} else if (loop >= defs.getDeathDelay()) {
					reset();
					finish();
					stop();
				}
				loop++;
			}
		}, 0, 1);
	}


}