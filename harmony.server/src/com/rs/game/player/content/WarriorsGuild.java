package com.rs.game.player.content;

import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.player.Player;

public class WarriorsGuild {
	
	public static final int RuneHelm = 1163, RunePlate = 1127, RuneLegs = 1079;

	public void raiseArmour() {
		
	}
	
	public static void recieveArmour(Player player) {
		if (player.getInventory().getFreeSlots() > 3) {
			player.getInventory().addItem(RuneHelm, 1);
			player.getInventory().addItem(RunePlate, 1);
			player.getInventory().addItem(RuneLegs, 1);
		} else {
			World.addGroundItem(new Item(RuneHelm, 1), new WorldTile(player), player, true, 180, true);
			World.addGroundItem(new Item(RunePlate, 1), new WorldTile(player), player, true, 180, true);
			World.addGroundItem(new Item(RuneLegs, 1), new WorldTile(player), player, true, 180, true);
		
				
			
			
		}
	}
}
	
	

	



