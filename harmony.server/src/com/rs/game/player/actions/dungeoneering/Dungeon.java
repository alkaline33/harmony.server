package com.rs.game.player.actions.dungeoneering;

import java.io.Serializable;

import com.rs.cache.loaders.ObjectDefinitions;
import com.rs.game.RegionBuilder;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.player.Player;



public class Dungeon implements Serializable {

	private static final long serialVersionUID = -3866335496921765212L;

	public static final int FROZEN = 0;
	public static final int FROZEN_LOWER = 1;
	public static final int FROZEN_MEDIUM = 2;
	public static final int FROZEN_HIGH = 3;
	
	public WorldObject endLadder;

	public NPC boss;
	public NPC smuggler;

	public boolean openedStairs = false;

	private Player player;
	private int dungType;
	private int mapChunks[];
	public int deaths = 0;

	public Dungeon(Player player, int dungType) {
		this.player = player;
		this.dungType = dungType;
		bindChunksToEmptyMap();
		loadRooms(dungType);
		putPlayerAtStart();
		player.loadMapRegions();
		boss = new NPC(DungeonConstants.BOSS[dungType], getTileFromRegion(0, 2, 5, 6), -1, true, true);
		player.getControlerManager().startControler("DungeoneeringControler", this);
		player.stopAll();
		player.reset();
	}

	public int getXpForDungeon() {
		int initialXp = (100 + (dungType * 15));
		initialXp *= (dungType + 1);
		if (deaths > 0)
			initialXp /= deaths;
		return initialXp;
	}
	


	public void end(boolean nextDung) {
		RegionBuilder.destroyMap(mapChunks[0], mapChunks[1], 4, 8);
		RegionBuilder.destroyMap(mapChunks[0], mapChunks[1] + 2, 4, 8);
		player.getControlerManager().removeControlerWithoutCheck();
		if (!nextDung) {
			player.setNextWorldTile(DungeonConstants.DAMONHEIM_FLOOR);
		}
		if (!boss.hasFinished())
			boss.finish();
		//if (!smuggler.hasFinished())
		//	smuggler.finish();
		if (endLadder != null)
			World.removeObject(endLadder, false);
		player.setDungeon(null);
		player.setForceNextMapLoadRefresh(true);
	}


	public static boolean useStairs(final Player player, int objectId) {
		if (objectId == 50552) {
			player.setNextWorldTile(DungeonConstants.DAMONHEIM_LOBBY);
			return true;
		}
		return false;
	}

	public void handleObjects(WorldObject object) {
		ObjectDefinitions defs = ObjectDefinitions.getObjectDefinitions(object.getId());

		switch (defs.name.toLowerCase()) {
		case "dungeon exit":
			player.getDialogueManager().startDialogue("DungeonExit", player);
			break;

		case "boss door":
			player.setNextWorldTile(new WorldTile(player.getX(), player.getY() - 3, player.getPlane()));
			break;

		case "ladder":
			if (boss.hasFinished()) {
				player.getDialogueManager().startDialogue("DungeonCompleteD", this);
			} else {
				player.sm("You must complete the dungeon in order to continue.");
			}
			break;
		case "door":
			if (player.getY() == getTileFromRegion(0, 2, 0, 0).getY()+1) {
				player.setNextWorldTile(new WorldTile(player.getX(), player.getY()-3, player.getPlane()));
			}
			
			if (player.getY() == getTileFromRegion(0, 2, 0, 0).getY()-2) {
				player.setNextWorldTile(new WorldTile(player.getX(), player.getY()+3, player.getPlane()));
			}
			
			
			if (player.getY() == getTileFromRegion(0, 2, 0, 0).getY()+1) {
				player.setNextWorldTile(new WorldTile(player.getX(), player.getY()-3, player.getPlane()));
			}
			
			if (player.getY() == getTileFromRegion(0, 4, 0, 0).getY()+1) {
				player.setNextWorldTile(new WorldTile(player.getX(), player.getY()-3, player.getPlane()));
			}
			break;
			
		default:
			player.getPackets().sendGameMessage("Object not handled: ID: "+object.getId()+" Name: "+defs.name);
			break;
		}
	}

	public WorldTile getTileFromRegion(int offsetX, int offsetY, int tileOffsetX, int tileOffsetY) {
		return new WorldTile((mapChunks[0] << 3) + offsetX * 8 + tileOffsetX, (mapChunks[1] << 3) + offsetY * 8 + tileOffsetY, 0);
	}

	public void putPlayerAtStart() {
		player.setNextWorldTile(getTileFromRegion(0, 0, 8, 8));
	}

	public void bindChunksToEmptyMap() {
		setMapChunks(RegionBuilder.findEmptyChunkBound(14, 24));
	}
	

	
	public void loadRooms(int dungType) {
		switch (dungType) {
		case FROZEN:
			RegionBuilder.copy2RatioSquare(DungeonConstants.FROZEN_ROOMS[0][0], DungeonConstants.FROZEN_ROOMS[0][1], mapChunks[0], mapChunks[1], 2);
			RegionBuilder.copy2RatioSquare(DungeonConstants.FROZEN_ROOMS[2][0], DungeonConstants.FROZEN_ROOMS[2][1], mapChunks[0], mapChunks[1] + 2, 0);
			break;
		case FROZEN_LOWER:
			RegionBuilder.copy2RatioSquare(DungeonConstants.FROZEN_ROOMS[0][0], DungeonConstants.FROZEN_ROOMS[0][1], mapChunks[0], mapChunks[1], 2);
			RegionBuilder.copy2RatioSquare(DungeonConstants.FROZEN_ROOMS[2][0], DungeonConstants.FROZEN_ROOMS[2][1], mapChunks[0], mapChunks[1] + 2, 0);
			break;
		case FROZEN_MEDIUM:
			RegionBuilder.copy2RatioSquare(DungeonConstants.FROZEN_ROOMS[0][0], DungeonConstants.FROZEN_ROOMS[0][1], mapChunks[0], mapChunks[1], 2);
			RegionBuilder.copy2RatioSquare(DungeonConstants.FROZEN_ROOMS[2][0], DungeonConstants.FROZEN_ROOMS[2][1], mapChunks[0], mapChunks[1] + 2, 0);
			break;
		case FROZEN_HIGH:
			RegionBuilder.copy2RatioSquare(DungeonConstants.FROZEN_ROOMS[0][0], DungeonConstants.FROZEN_ROOMS[0][1], mapChunks[0], mapChunks[1], 2);
			RegionBuilder.copy2RatioSquare(DungeonConstants.FROZEN_ROOMS[2][0], DungeonConstants.FROZEN_ROOMS[2][1], mapChunks[0], mapChunks[1] + 2, 0);
			break;
		}
	}

	public int[] getMapChunks() {
		return mapChunks;
	}

	public void setMapChunks(int mapChunks[]) {
		this.mapChunks = mapChunks;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public int getDungType() {
		return dungType;
	}

	public void setDungType(int dungType) {
		this.dungType = dungType;
	}
}