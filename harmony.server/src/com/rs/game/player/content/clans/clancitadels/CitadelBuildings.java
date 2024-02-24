package com.rs.game.player.content.clans.clancitadels;

import com.rs.game.World;
import com.rs.game.WorldObject;

/**
 * 
 * @author Josh'
 *
 */
public class CitadelBuildings {
	
	public CitadelBuildings(int object, int x, int y) {
		World.spawnObject(new WorldObject(object, 1, -1, x, y, 0), true);
	}
}
