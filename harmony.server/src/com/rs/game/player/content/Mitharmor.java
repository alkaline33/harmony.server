package com.rs.game.player.content;

import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.player.Player;

public class Mitharmor {
	
public static final int MithHelm = 1159, MithLegs = 1071, MithPlate = 1121;

	public void raiseArmour() {
		
	}
	
	public static void recieveArmour(Player player) {
		if (player.getInventory().getFreeSlots() > 3) {
			player.getInventory().addItem(MithHelm, 1);
			player.getInventory().addItem(MithPlate, 1);
			player.getInventory().addItem(MithLegs, 1);
		} else {
			World.addGroundItem(new Item(MithHelm, 1), new WorldTile(player), player, true, 180, true);
			World.addGroundItem(new Item(MithPlate, 1), new WorldTile(player), player, true, 180, true);
			World.addGroundItem(new Item(MithLegs, 1), new WorldTile(player), player, true, 180, true);
		
				
			
			
		}
	}
}
	
	