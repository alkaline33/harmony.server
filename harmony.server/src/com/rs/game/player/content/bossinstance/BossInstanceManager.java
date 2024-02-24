package com.rs.game.player.content.bossinstance;

import java.util.HashSet;
import java.util.Set;

import com.rs.Settings;
import com.rs.game.player.Player;
import com.rs.utils.Colors;

public final class BossInstanceManager {

	public static final BossInstanceManager SINGLETON = new BossInstanceManager();

	private final Set<BossInstance> instances = new HashSet<>();

	public void add(BossInstance instance) {
		if (contains(instance)) {
			return;
		}
		instances.add(instance);
		instance.start();
	}

	public void join(Player player, String owner, boolean check) {
		if (inInstance(player)) {
			player.getPackets().sendGameMessage(
					"You are already in an instance!");
			return;
		}
		
		if (check) {
			if (!player.getInventory().containsItem(995, 500000)) { // 500K
			}
		}
		boolean found = false;
		for (BossInstance instance : instances) {
			if (player.getCurrentFriendChat() == null) {
				player.sendMessage("You must be in a friends chat to join active instances.");
				return;
			}
			if (!player.getCurrentFriendChat().getPlayers().contains(instance.getOwner())) {
				player.sendMessage("You cannot join an instance hosted by someone who isn't in the same friends chat as you.");
				return;
			}
			if (instance.getOwner().getUsername().equalsIgnoreCase(owner)) {
				instance.add(player);
				found = true;
			}
		}
		if (!found) {
			player.getPackets()
					.sendGameMessage(
							owner
									+ " was not found. Ensure that this player is online and is the owner of their instance!");
		}
	}

	public void remove(BossInstance instance) {
		boolean success = instances.remove(instance);
		if (success) {
			//instance.getNpc().finish();
			instance.removeMapChunks();
			instance.getPlayers().forEach(player -> {
				player.setForceMultiArea(false);
				player.setNextWorldTile(Settings.RESPAWN_PLAYER_LOCATION);
			});
			instance.getPlayers().clear();
		}
	}

	public boolean inInstance(Player player) {
		for (BossInstance instance : instances) {
			if (instance.getPlayers().contains(player)) {
				return true;
			}
		}
		return false;
	}

	public boolean contains(BossInstance instance) {
		return instances.contains(instance);
	}

}