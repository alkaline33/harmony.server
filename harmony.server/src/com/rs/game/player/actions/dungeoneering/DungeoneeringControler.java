package com.rs.game.player.actions.dungeoneering;

import com.rs.game.Animation;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.player.Player;
import com.rs.game.player.actions.dungeoneering.Dungeon;
import com.rs.game.player.controlers.Controler;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.ShopsHandler;

public class DungeoneeringControler extends Controler {

	Dungeon dungeon;

	@Override
	public void start() {
		dungeon = (Dungeon) getArguments()[0];
		setArguments(null);
	}

	public static void stopControler(Player player) {
		player.getControlerManager().getControler();
		player.getControlerManager().forceStop();
	}

	public void openStairs() {
		WorldTile pos;
		
		switch(dungeon.getDungType()) {
		case 0:
			pos = dungeon.getTileFromRegion(0, 4, 7, -1);
			dungeon.endLadder = new WorldObject(3784, 10, 7, pos.getX(), pos.getY(), 0);
			break;
		case 1:	
			pos = dungeon.getTileFromRegion(0, 4, 7, -1);
			dungeon.endLadder = new WorldObject(3784, 10, 7, pos.getX(), pos.getY(), 0);
			break;
		case 2:	
			pos = dungeon.getTileFromRegion(0, 4, 7, -1);
			dungeon.endLadder = new WorldObject(3784, 10, 7, pos.getX(), pos.getY(), 0);
			break;
		case 3:	
			pos = dungeon.getTileFromRegion(0, 4, 7, -1);
			dungeon.endLadder = new WorldObject(3784, 10, 7, pos.getX(), pos.getY(), 0);
			break;
		case 4: 
			pos = dungeon.getTileFromRegion(0, 4, 7, -1);
			dungeon.endLadder = new WorldObject(3784, 10, 7, pos.getX(), pos.getY(), 0);
			break;
		default:
			pos = dungeon.getTileFromRegion(0, 8, 8, 0);
			dungeon.endLadder = new WorldObject(55484, 10, 3, pos.getX(), pos.getY(), 0);
			break;
		}
		
		World.spawnObject(dungeon.endLadder, false);
		dungeon.openedStairs = true;
	}

	@Override
	public void process() {
		if (dungeon != null) {
			if (dungeon.boss.hasFinished() && !dungeon.openedStairs) {
				openStairs();
			}
		} else {
			player.setDungeon(null);
			player.setNextWorldTile(DungeonConstants.DAMONHEIM_LOBBY);
			removeControler();
		}
	}

	@Override
	public boolean sendDeath() {
		player.lock(7);
		player.stopAll();
		WorldTasksManager.schedule(new WorldTask() {
			int loop;

			@Override
			public void run() {
				if (loop == 0) {
					player.setNextAnimation(new Animation(836));
				} else if (loop == 1) {
					player.sm("Oh dear, you have died.");
				} else if (loop == 3) {
					player.reset();
					dungeon.putPlayerAtStart();
					player.setNextAnimation(new Animation(-1));
					dungeon.deaths++;
					stop();
				}
				loop++;
			}
		}, 0, 1);
		return false;
	}

	@Override
	public boolean login() {
		player.setNextWorldTile(DungeonConstants.DAMONHEIM_LOBBY);
		if (dungeon != null)
			dungeon.end(false);
		return false;
	}

	@Override
	public void forceClose() {
		player.setNextWorldTile(DungeonConstants.DAMONHEIM_LOBBY);
		if (dungeon != null)
			dungeon.end(false);
	}

	@Override
	public boolean logout() {
		player.setNextWorldTile(DungeonConstants.DAMONHEIM_LOBBY);
		if (dungeon != null)
			dungeon.end(false);
		return false;
	}

	@Override
	public boolean processMagicTeleport(WorldTile toTile) {
		player.sm("You may not teleport in a dungeon. You may leave at any time from the home room.");
		return false;
	}

	@Override
	public boolean processItemTeleport(WorldTile toTile) {
		player.sm("You may not teleport in a dungeon. You may leave at any time from the home room.");
		return false;
	}

	@Override
	public boolean processNPCClick1(NPC npc) {
		switch (npc.getId()) {
		case 11226:
			ShopsHandler.openShop(player, 195);
			return true;
		default:
			player.sm("NPC not added to dungeoneering controller.");
			return true;
		}
	}

	@Override
	public boolean processNPCClick2(NPC npc) {
		switch (npc.getId()) {
		case 11226:
			ShopsHandler.openShop(player, 195);
			return true;
		default:
			player.sm("NPC not added to dungeoneering controller.");
			return true;
		}
	}

	@Override
	public boolean processObjectClick1(WorldObject object) {
		if (dungeon != null)
			dungeon.handleObjects(object);
		return false;
	}

}