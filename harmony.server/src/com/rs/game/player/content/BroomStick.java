package com.rs.game.player.content;

import com.rs.Settings;
import com.rs.game.Animation;
import com.rs.game.WorldTile;
import com.rs.game.player.Player;

public class BroomStick {
	
	public static void Sweep(Player player) {
		if (player.getX() == 4316 && player.getY() == 5474) {
			player.sendMessage("<col=FFFF00>As you sweep the broomstick got covered in webs.");
			player.sendMessage("<col=FFFF00>You remove the webs from the broomstick.");
			player.getInventory().addItem(3064, 1);
		}
		if (player.getX() == 4324 && player.getY() == 5465) {
			player.sendMessage("<col=FFFF00>As you sweep the broomstick knocks the clock.");
			player.sendMessage("<col=FFFF00>You pick up a skeleton leg from the floor.");
			player.getInventory().addItem(29673, 1);
		}
		if (player.getX() == 4318 && player.getY() == 5468) {
			player.sendMessage("<col=FFFF00>As you sweep the broomstick knocks the table.");
			player.sendMessage("<col=FFFF00>You pick up a skeleton skull and some rusty sword from the floor.");
			player.getInventory().addItem(29674, 1);
			player.getInventory().addItem(29671, 1);
		}
		player.setNextAnimation(new Animation(10532));
		player.getPackets().sendGameMessage("You sweep, but all you get is dust in your face.", true);
		return;
	}
	
	public static void Teleport(Player player) {
		if (player.isCanPvp() || player.getControlerManager().getControler() != null) {
			player.sendMessage("You cannot teleport here.");
			return;
		}
		if (player.hasfinishedtalkingtohetty != true) {
			Magic.BroomTele(player, 0, 0, new WorldTile(4320, 5455, 0));
			return;
		} else {
			Magic.BroomTele(player, 0, 0, new WorldTile(3804, 3551, 0)); //TODO
		}
		return;
	}

}
