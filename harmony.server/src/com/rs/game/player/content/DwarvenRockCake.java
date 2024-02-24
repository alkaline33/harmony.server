package com.rs.game.player.content;

import com.rs.game.Animation;
import com.rs.game.ForceTalk;
import com.rs.game.Hit;
import com.rs.game.Hit.HitLook;
import com.rs.game.player.Player;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;

/**
 * @author JazzyYaYaYa | Nexon | Fuzen Seth
 * Dwarven Rock Cakes - Created 16.7.2013.
 */
public class DwarvenRockCake {

	private Player player;
	
	private boolean eating;
	
	public DwarvenRockCake(Player player) {
		this.player = player;
	}
	
	public void eat1HP() {
		if (player.getHitpoints() <= 1) {
			player.sendMessage("You don't think it wise to try that again soon!");
			return;
		}
		if (eating) {
			player.sendMessage("You are eating already a cake.");
			return;
		}
		eating = true;
		WorldTasksManager.schedule(new WorldTask() {
			int count = 0;

			@Override
			public void run() {
				switch (count) {
				case 0:
					if (player.getHitpoints() <= 1) // I've added another check because if player is in combat it would be
													// #annihilation.
						return;
					player.setNextAnimation(new Animation(829));
					player.applyHit(new Hit(player, player.getHitpoints() - 1, HitLook.REGULAR_DAMAGE));
					player.setNextForceTalk(new ForceTalk("Ow! I nearly broke a tooth!"));
					eating = false;
					break;
				}
				stop();
			}

		}, 0, 0);
	}

	public void eat() {
		if (player.getHitpoints() <= 30) {
			player.sendMessage("You don't think it wise to try that again soon!");
			return;
		}
		if (eating) {
			player.sendMessage("You are eating already a cake.");
			return;
		}
		eating = true;
		WorldTasksManager.schedule(new WorldTask() {
			int count = 0;
		       @Override
			public void run() {
			switch (count) {
			case 0:
				if (player.getHitpoints() <= 30) //I've added another check because if player is in combat it would be #annihilation.
					return;
				player.setNextAnimation(new Animation(829));			
				player.applyHit(new Hit(player, 10, HitLook.REGULAR_DAMAGE));
				player.setNextForceTalk(new ForceTalk("Ow! I nearly broke a tooth!"));
				eating = false;
				break;
			}
			stop();
			}

		}, 0, 0);
	}
	
	public boolean eatCake1HP(Player player, int itemId) {
		switch (itemId) {
		case 7509:
		case 7510:
		case 2379:
			eat1HP();
			return true;
		}
		return false;
	}
	
	public boolean eatCake(Player player, int itemId){
	switch (itemId)  {
	case 7509:
	case 7510:
	case 2379:
		eat();
		return true;
	}
		return false;
}
}