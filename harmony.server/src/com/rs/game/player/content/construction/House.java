package com.rs.game.player.content.construction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.cache.loaders.ObjectDefinitions;
import com.rs.cores.CoresManager;
import com.rs.game.Animation;
import com.rs.game.DynamicRegion;
import com.rs.game.Region;
import com.rs.game.RegionBuilder;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.map.MapUtils;
import com.rs.game.npc.NPC;
import com.rs.game.npc.construction.ServantNPC;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.content.DailyChallenges;
import com.rs.game.player.content.construction.HouseConstants.Builds;
import com.rs.game.player.content.construction.HouseConstants.HObject;
//import com.rs.game.player.content.construction.HouseConstants.Servant;
import com.rs.game.player.content.construction.HouseConstants.Room;
import com.rs.game.player.content.construction.HouseConstants.Servant;
//import com.rs.game.player.content.construction.HouseConstants.Room.Servant;
import com.rs.game.player.controlers.Controler;
import com.rs.game.player.controlers.HouseControler;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Logger;

/*
 * House class only contains house data + support methods to change that data
 * HouseController provides support between player interaction inside house and housemanager
 * HouseConstants handles the constants such as existing rooms, builds, roofs
 */
public class House implements Serializable {

	public static class ObjectReference implements Serializable {
		
		private static final long serialVersionUID = -22245200911725426L;
		private final int slot;
		private final Builds build;

		public ObjectReference(Builds build, int slot) {
			this.build = build;
			this.slot = slot;
		}

		public int getId() {
			return build.getPieces()[slot].getId();
		}

		public int getId(int slot2) {
			return getIds()[slot2];
		}

		public int[] getIds() {
			return build.getPieces()[slot].getIds();
		}

		public HObject getPiece() {
			return build.getPieces()[slot];
		}

	}

	public static class RoomReference implements Serializable {

		private static final long serialVersionUID = 4000732770611956015L;

		private final HouseConstants.Room room;

		private final byte x, y, plane;
		private byte rotation;
		private final List<ObjectReference> objects;

		public RoomReference(HouseConstants.Room room, int x, int y, int plane,
				int rotation) {
			this.room = room;
			this.x = (byte) x;
			this.y = (byte) y;
			this.plane = (byte) plane;
			this.rotation = (byte) rotation;
			objects = new ArrayList<ObjectReference>();
		}

		/*
		 * x,y inside the room chunk
		 */
		public ObjectReference addObject(Builds build, int slot) {
			ObjectReference ref = new ObjectReference(build, slot);
			objects.add(ref);
			return ref;
		}

		public ObjectReference getObject(WorldObject object) {
			for (ObjectReference o : objects) {
				for (int id : o.getIds())
					if (object.getId() == id)
						return o;
			}
			return null;
		}

		public int getPlane() {
			return plane;
		}

		public Room getRoom() {
			return room;
		}

		public byte getRotation() {
			return rotation;
		}

		public int getStaircaseSlot() {
			for (ObjectReference object : objects) {
				if (object.build.toString().contains("STAIRCASE"))
					return object.slot;
			}
			return -1;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		public boolean isStaircaseDown() {
			for (ObjectReference object : objects) {
				if (object.build.toString().contains("STAIRCASE_DOWN"))
					return true;
			}
			return false;
		}

		public ObjectReference removeObject(WorldObject object) {
			ObjectReference r = getObject(object);
			if (r != null) {
				objects.remove(r);
				return r;
			}
			return null;
		}

		public void setRotation(int rotation) {
			this.rotation = (byte) rotation;
		}

		public int getTrapdoorSlot() {
			for (ObjectReference object : objects) {
				if (object.build == Builds.TRAPDOOR)
					return object.slot;
			}
			return -1;
		}

	}

	public static int LOGGED_OUT = 0, KICKED = 1, TELEPORTED = 2;

	private static final long serialVersionUID = 8111719490432901786L;
	// dont name it rooms or it will null server
	private List<RoomReference> roomsR;
	private byte look;
	
	private byte paymentStage;

	private boolean buildMode;
	private boolean arriveInPortal;
	public boolean getArriveInPortal() {
		return arriveInPortal;
		
	}
	
	
	private transient Player player;
	
	private transient int challengeMode; //0 disabled,  1 - challenge method, 2 - pvp challenge method
	
	private transient ServantNPC servantInstance;
	private Servant servant;

	private transient Player owner;
	private transient boolean locked;
	// house loaded datas
	private transient List<Player> players;

	public transient int[] boundChuncks;

	private transient boolean loaded;
	public transient int litBurners;

	private byte build;

	// Used for inter 396
	private static final int[] BUILD_INDEXES = { 0, 2, 4, 6, 1, 3, 5 };

	private static final int[] DOOR_DIR_X = { -1, 0, 1, 0 };
	private static final int[] DOOR_DIR_Y = { 0, 1, 0, -1 };

	public static void enterHouse(Player player, String username) {
		if (player.isLocked())
			return;
		Player owner = World.getPlayerByDisplayName(username);
		if (owner == null || !owner.isRunning()
				|| !player.getFriendsIgnores().isOnline(owner)
				|| owner.getHouse().locked) {
			player.getPackets().sendGameMessage(
					"That player is offline, or has privacy mode enabled.");
			return;
		}
		/*
		 * if (owner.getHouse().location == null ||
		 * !player.withinDistance(owner.getHouse().location.getTile(), 16)) {
		 * player.getPackets().sendGameMessage("Your house is at " +
		 * Utils.formatPlayerNameForDisplay(owner.getHouse().location.name()) +
		 * "."); return; }
		 */
	}

	public static void leaveHouse(Player player) {
		if (player.isLocked())
			return;
		Controler controller = player.getControlerManager().getControler();
		
		if (controller == null || !(controller instanceof HouseControler) || !((HouseControler) controller).getHouse().getPlayers().contains(player)) {
			player.getPackets().sendGameMessage("You're not in a house.");
			return;
		}
		if (((HouseControler) controller).getHouse() == null)
			return;
		((HouseControler) controller).getHouse().leaveHouse(player, KICKED);
	}

	/*
	 * 0 - logout, 1 kicked/tele outside outside, 2 tele somewhere else
	 */
	@SuppressWarnings("unused")
	private final WorldTile location = new WorldTile(3977, 4181, 1);

	public House() {
		buildMode = true;
		roomsR = new ArrayList<RoomReference>();
		addRoom(HouseConstants.Room.GARDEN, 3, 3, 0, 0);
		getRoom(3, 3, 0).addObject(Builds.CENTREPIECE, 0);
	}

	public boolean addRoom(HouseConstants.Room room, int x, int y, int plane,
			int rotation) {
		return roomsR.add(new RoomReference(room, x, y, plane, rotation));
	}

	public void build(int slot) {
		if (owner.getInterfaceManager().containsInterface(396)) {
			for (int i = 0; i < BUILD_INDEXES.length; i++)
				if (slot == BUILD_INDEXES[i]) {
					slot = i;
					break;
				}
		}
		final Builds build = (Builds) owner.getTemporaryAttributtes().get(
				"OpenedBuild");
		WorldObject object = (WorldObject) owner.getTemporaryAttributtes().get(
				"OpenedBuildObject");
		if (build == null || object == null || build.getPieces().length <= slot)
			return;
		int roomX = object.getChunkX() - boundChuncks[0];
		int roomY = object.getChunkY() - boundChuncks[1];
		final RoomReference room = getRoom(roomX, roomY, object.getPlane());
		if (room == null)
			return;
		final HObject piece = build.getPieces()[slot];
		if (owner.getSkills().getLevel(Skills.CONSTRUCTION) < piece.getLevel()) {
			owner.getPackets().sendGameMessage(
					"Your level of construction is too low for this build.");
			return;
		}

		if	(owner.getRights() < 2) {
			if (!owner.getInventory().containsItems(piece.getRequirements())) {
				owner.getPackets().sendGameMessage(
						"You dont have the right materials.");
				return;
			}
			if (build.isWater() ? !hasWaterCan() : (!owner.getInventory()
					.containsItem(HouseConstants.HAMMER, 1) || !owner
					.getInventory().containsItem(HouseConstants.SAW, 1))) {
				owner.getPackets()
						.sendGameMessage(
								build.isWater() ? "You will need a watering can with some water in it instead of hammer and saw to build plants."
										: "You will need a hammer and saw to build furniture.");
				return;
			}
		}

		final ObjectReference oref = room.addObject(build, slot);
		owner.closeInterfaces();
		owner.lock();
		owner.setNextAnimation(new Animation(build.isWater() ? 2293 : 3683));
		for (Item item : piece.getRequirements())
			owner.getInventory().deleteItem(item);
		WorldTasksManager.schedule(new WorldTask() {
			@Override
			public void run() {
				if (owner.challengeamount > 0 && owner.challengeid == 7) {
					DailyChallenges.UpdateChallenge(owner);
				}
				if (owner.challengeamount > 0 && owner.challengeid == 8 ) {
					DailyChallenges.UpdateChallenge(owner);
				}
				if (owner.challengeamount > 0 && owner.challengeid == 9) {
					DailyChallenges.UpdateChallenge(owner);
				}
				owner.getSkills().addXp(Skills.CONSTRUCTION, piece.getXP());
				if (build.isWater())
					owner.getSkills().addXp(Skills.FARMING, piece.getXP());
				refreshObject(room, oref, false, false);
				owner.lock(1);
			}
		}, 2);
	}

	public void buildDungeonStairs(int slotId, final Builds stair,
			final RoomReference newRoom) {
		if (owner.getInterfaceManager().containsInterface(396)) {
			for (int i = 0; i < BUILD_INDEXES.length; i++)
				if (slotId == BUILD_INDEXES[i]) {
					slotId = i;
					break;
				}
		}
		final Builds build = stair;
		if (build == null || build.getPieces().length <= slotId)
			return;
		final HObject piece = build.getPieces()[slotId];
		if (owner.getSkills().getLevel(Skills.CONSTRUCTION) < piece.getLevel()) {
			owner.getPackets().sendGameMessage(
					"Your level of construction is too low for this build.");
			return;
		}
		/*
		 * if (!player.getInventory().containsItems(piece.getRequirements())) {
		 * player
		 * .getPackets().sendGameMessage("You dont have the right materials.");
		 * return; } if (build.isWater() ? !hasWaterCan() :
		 * (!player.getInventory().containsItem(HouseConstants.HAMMER, 1) ||
		 * !player.getInventory().containsItem(HouseConstants.SAW, 1))) {
		 * player.getPackets().sendGameMessage(build.isWater() ?
		 * "You will need a watering can with some water in it instead of hammer and saw to build plants."
		 * : "You will need a hammer and saw to build furniture."); return; }
		 */
		final ObjectReference oref = newRoom.addObject(build, slotId);
		owner.closeInterfaces();
		owner.lock();
		owner.setNextAnimation(new Animation(build.isWater() ? 2293 : 3683));
		for (Item item : piece.getRequirements())
			owner.getInventory().deleteItem(item);
		WorldTasksManager.schedule(new WorldTask() {
			@Override
			public void run() {
				owner.getSkills().addXp(Skills.CONSTRUCTION, piece.getXP());
				if (build.isWater())
					owner.getSkills().addXp(Skills.FARMING, piece.getXP());
				owner.getHouse().createRoom(newRoom);
				refreshObject(newRoom, oref, false, false);
				owner.lock(1);
			}
		}, 2);
	}
	
	public void climbTrapdoor(Player player, WorldObject object, boolean up) {
		int roomX = object.getChunkX() - boundChuncks[0];
		int roomY = object.getChunkY() - boundChuncks[1];
		RoomReference room = getRoom(roomX, roomY, object.getPlane());
		if (room == null)
			return;
		if (!up && buildMode && room.plane != 1) {
			player.getPackets().sendGameMessage("You cannot add a oubliette here.");
			return;
		}
		RoomReference roomTo = getRoom(roomX, roomY, room.plane + (up ? 1 : -1));
		if (roomTo == null) {
			if (buildMode && isOwner(player) && !up)
				player.getDialogueManager().startDialogue("CreateOublietteD", room);
			else
				player.getPackets().sendGameMessage("This " + (up ? "ladder" : "trapdoor") + " does not lead anywhere.");
			if (isOwner(player))
			player.getDialogueManager().startDialogue("CreateOublietteD", room);
			return;
		}
		if (roomTo.room != Room.THRONE_ROOM && roomTo.room != Room.OUTBLIETTE && isOwner(player)) {
			player.getPackets().sendGameMessage("This " + (up ? "ladder" : "trapdoor") + " does not lead anywhere.");
			player.getDialogueManager().startDialogue("CreateOublietteD", room);
			return;
		} 
		player.useStairs(up ? 828 : 827, new WorldTile(player.getX(), player.getY(), player.getPlane() + (up ? 1 : -1)), 0, 2);
		
	}

	public void climbStaircase(WorldObject object, boolean up, Player player) {
		if(!owner.getHouse().loaded)
			return;
		int roomX = object.getChunkX() - 
				owner.getHouse().boundChuncks[0];
		int roomY = object.getChunkY() - 
				owner.getHouse().boundChuncks[1];
		RoomReference room = getRoom(roomX, roomY, object.getPlane());
		if (room == null)
			return;
		if (room.plane == (up ? 2 : 0)) {
			player.getPackets().sendGameMessage(
					"You are on the " + (up ? "highest" : "lowest")
							+ " possible level so you cannot add a room "
							+ (up ? "above" : "under") + " here.");
			return;
		}
		RoomReference roomTo = getRoom(roomX, roomY, room.plane + (up ? 1 : -1));
		if (roomTo == null) {
			if (buildMode)
				player.getDialogueManager().startDialogue("CreateRoomStairsD",
						room, up);
			else
				player.getPackets().sendGameMessage(
						"These stairs do not lead anywhere.");
			// start dialogue
			return;
		}
		if (roomTo.getStaircaseSlot() == -1
				&& ((roomTo.getRoom() != HouseConstants.Room.GARDEN) && (roomTo
						.getRoom() != HouseConstants.Room.FORMAL_GARDEN))) {
			player.getPackets().sendGameMessage(
					"These stairs do not lead anywhere.");
			return;
		}
		player.useStairs(-1,
				new WorldTile(player.getX(), player.getY(), player.getPlane()
						+ (up ? 1 : -1)), 0, 1);

		
		
	}

	
	/*
	 * called when switching challenge mode
	 */
	private void refreshChallengeMode() {
		boolean remove = !isChallengeMode();
		for (RoomReference reference : roomsR) {
			if (reference.plane != 0)
				continue;
			for (ObjectReference o : reference.objects) {
				if (o.getPiece().getNPCId() != -1)
					refreshObject(reference, o, !remove, false);
			}
		}
		if (remove)
		{
			boolean pvp = isPVPMode();
			for (Player player : new ArrayList<Player>(players)) {
				player.getPackets().sendGameMessage((pvp ? "PVP" : "Challenge") + " mode is now activated.");
				if (pvp)
					player.setCanPvp(true);
			}
		}
	}
	
	/*private void clearChallengeNPCs() {
		for (RoomReference rref : roomsR) {
			if (rref.getGuardians() == null)
				continue;
			List<Guard> challengeModeNPCs = rref.getGuardians();
			for (Guard n : challengeModeNPCs)
				n.finish();
			challengeModeNPCs.clear();
		}
	}*/
	
	
	public void createHouse(final boolean tp) {
		Object[][][][] data = new Object[4][8][8][];
		// sets rooms data
		for (RoomReference reference : roomsR)
			data[reference.plane][reference.x][reference.y] = new Object[] {
					reference.room.getChunkX(), reference.room.getChunkY(),
					reference.rotation, reference.room.isShowRoof() };
		// sets roof data
		if (!buildMode) { // construct roof
			for (int x = 1; x < 7; x++) {
				skipY: for (int y = 1; y < 7; y++) {
					for (int plane = 2; plane >= 1; plane--) {
						if (data[plane][x][y] != null) {
							boolean hasRoof = (boolean) data[plane][x][y][3];
							if (hasRoof) {
								byte rotation = (byte) data[plane][x][y][2];
								// TODO find best Roof
								data[plane + 1][x][y] = new Object[] {
										HouseConstants.Roof.ROOF1.getChunkX(),
										HouseConstants.Roof.ROOF1.getChunkY(),
										rotation, true };
								continue skipY;
							}
						}
					}
				}
			}
		}
		// builds data
		for (int plane = 0; plane < data.length; plane++) {
			for (int x = 0; x < data[plane].length; x++) {
				for (int y = 0; y < data[plane][x].length; y++) {
					if (data[plane][x][y] != null)
						//RegionBuilder
						RegionBuilder.copyChunk((int) data[plane][x][y][0]
								+ (look >= 4 ? 8 : 0),
								(int) data[plane][x][y][1], look & 0x3,
								boundChuncks[0] + x, boundChuncks[1] + y,
								plane, (byte) data[plane][x][y][2]);
					else if ((x == 0 || x == 7 || y == 0 || y == 7)
							&& plane == 1)
						RegionBuilder.copyChunk(HouseConstants.BLACK[0],
								HouseConstants.BLACK[1], 0,
								boundChuncks[0] + x, boundChuncks[1] + y,
								plane, 0);
					else if (plane == 1)
						RegionBuilder.copyChunk(HouseConstants.LAND[0]
								+ (look >= 4 ? 8 : 0), HouseConstants.LAND[1],
								look & 0x3, boundChuncks[0] + x,
								boundChuncks[1] + y, plane, 0);
					else if (plane == 0) {
						RegionBuilder.copyChunk(HouseConstants.DUNGEON[0]
								+ (look >= 4 ? 8 : 0),
								HouseConstants.DUNGEON[1], look & 0x3,
								boundChuncks[0] + x, boundChuncks[1] + y,
								plane, 0);
						// int regionId = RegionBuilder.getRegionId(boundChuncks[0]
						// + x, boundChuncks[1] + y);

					} else
						RegionBuilder.cutChunk(boundChuncks[0] + x,
								boundChuncks[1] + y, plane);
				}
			}
		}
		int[] regionPos = MapUtils.convert(MapUtils.Structure.CHUNK,
				MapUtils.Structure.REGION, boundChuncks);
		final Region region = World.getRegion(
				MapUtils.encode(MapUtils.Structure.REGION, regionPos), true);
		List<Integer> spawnedNPCs = region.getNPCsIndexes();
		if (spawnedNPCs != null) {
			for (Integer index : spawnedNPCs) {
				NPC npc = World.getNPCs().get(index);
				if (npc != null) {
					npc.finish();
				}
			}
		}
		owner.getTemporaryAttributtes().put("baseX", boundChuncks[0] << 3);
		owner.getTemporaryAttributtes().put("baseY", boundChuncks[1] << 3);
		List<WorldObject> spawnedObjects = region.getSpawnedObjects();
		if (spawnedObjects != null) {
			for (WorldObject object : spawnedObjects)
				World.removeObject(object);
		}
		List<WorldObject> removedObjects = region.getRemovedOriginalObjects();
		if (removedObjects != null) {
			for (WorldObject object : removedObjects)
				World.spawnObject(object);
		}
		// requires to let the region finish loading
		CoresManager.fastExecutor.schedule(new TimerTask() {
			@Override
			public void run() {
				if (region.getLoadMapStage() != 2)
					return;
				((TimerTask) this).cancel();
				for (RoomReference reference : roomsR) {
					int boundX = reference.x * 8;
					int boundY = reference.y * 8;
					for (int x = 0; x < 8; x++) {
						for (int y = 0; y < 8; y++) {
							WorldObject[] objects = region.getAllObjects(
									reference.plane, boundX + x, boundY + y);
							if (objects != null) {
								skip: for (WorldObject object : objects) {
									if (object == null)
										continue;
									if (object.getDefinitions().containsOption(
											4, "Build")
											|| object.getDefinitions().name
													.equals("Habitat space")) {
										if (isDoor(object)) {
											int objectX = object.getChunkX()
													- boundChuncks[0];// gets x
																		// of
																		// object
																		// in
																		// map
											int objectY = object.getChunkY()
													- boundChuncks[1];// gets y
																		// of
																		// object
																		// in
																		// map
											int nextRoomX = objectX
													+ DOOR_DIR_X[object
															.getRotation()];// adds
																			// the
																			// rotation
																			// of
																			// object
																			// to
																			// its
																			// x
																			// so
																			// it
																			// can
																			// go
																			// into
																			// next
																			// room
											int nextRoomY = objectY
													+ DOOR_DIR_Y[object
															.getRotation()];// adds
																			// the
																			// rotation
																			// of
																			// object
																			// to
																			// it's
																			// y
																			// so
																			// it
																			// can
																			// get
																			// the
																			// y
																			// of
																			// next
																			// room
											RoomReference room = getRoom(
													nextRoomX, nextRoomY,
													object.getPlane());
											if (!buildMode
													&& object.getPlane() != 0
													&& room == null
													&& reference.getRoom()
															.isShowRoof()) {
												WorldObject objectR = new WorldObject(
														object);
												objectR.setId(HouseConstants.WALL_IDS[look]);
												World.spawnObject(objectR);
												continue;
											}
										} else {
											for (ObjectReference o : reference.objects) {
												int slot = o.build
														.getIdSlot(object
																.getId());
												if (slot != -1) {
													WorldObject objectR = new WorldObject(
															object);
													objectR.setId(o.getId(slot));
													if (!buildMode
															&& HouseConstants
																	.getMonsterId(objectR
																			.getId()) != -1) {// 2715
														NPC npc = new NPC(
																HouseConstants
																		.getMonsterId(objectR
																				.getId()),
																new WorldTile(
																		objectR.getX(),
																		objectR.getY(),
																		objectR.getPlane()),
																-1, true);
														World.removeObject(objectR);
														if (World
																.getSpawnedObject(
																		objectR.getX(),
																		objectR.getY(),
																		objectR.getPlane()) == null)
															World.spawnNPC(npc.getId(), npc, -1, false);
														break;
													} else if (!buildMode) {
														if (objectR.getId() == 13356)
															objectR.setId(13361);
														else if (objectR
																.getId() == 13357)
															objectR.setId(13362);
														else if (objectR
																.getId() == 13358)
															objectR.setId(13363);
														else if (objectR
																.getId() == 13359)
															objectR.setId(13364);
														else if (objectR
																.getId() == 13360)
															objectR.setId(13365);
													}
													World.spawnObject(objectR);
													continue skip;
												}
											}
										}
										if (!buildMode)
											World.removeObject(object);
									} else if (object.getId() == HouseConstants.WINDOW_SPACE_ID) {
										object = new WorldObject(object);
										object.setId(HouseConstants.WINDOW_IDS[look]);
										World.spawnObject(object);
									} else if (isDoorSpace(object)) // rs doesnt
																	// even have
																	// those
																	// rofl
										World.removeObject(object);
								}
							}
						}
					}
				}
				owner.setForceNextMapLoadRefresh(true);
				owner.loadMapRegions();
				owner.lock(1);
				owner.getInterfaceManager().sendWindowPane();
				if (tp)
					refreshServant();
					teleportPlayer(owner);
				loaded = true;
				//World.spawnNPC(494, owner.getLastLoadedMapRegionTile(), 1, true, true);
				//World.spawnNPC(494, owner.getNextWorldTile(), 1, true, true);
			}
		}, 2400, 600);
	}

	public void createRoom(int slot) {
		Room[] rooms = HouseConstants.Room.values();
		if (slot >= rooms.length)
			return;
		int[] position = (int[]) owner.getTemporaryAttributtes().get(
				"CreationRoom");
		owner.closeInterfaces();
		if (position == null)
			return;
		Room room = rooms[slot];
		if ((room == Room.DUNGEON_CORRIDOR || room == Room.DUNGEON_JUNCTION
				|| room == Room.DUNGEON_PIT || room == Room.DUNGEON_STAIRS || room == Room.TREASURE_ROOM || room == Room.OUTBLIETTE)
				&& position[2] != 0) {
			owner.getPackets().sendGameMessage(
					"That room can only be built underground.");
			return;
		}
		if ((room == Room.GARDEN || room == Room.FORMAL_GARDEN || room == Room.MENAGERIE)
				&& position[2] != 1) {
			owner.getPackets().sendGameMessage(
					"That room can only be built on ground.");
			return;
		}
		if (owner.getRights() < 2) {
			if (owner.getSkills().getLevel(Skills.CONSTRUCTION) < room.getLevel()) {//room.getLevel()) {
				owner.getPackets().sendGameMessage(
						"You need a Construction level of " + room.getLevel()
								+ " to build this room.");
				return;
			}
			if (owner.getInventory().getNumerOf(995) < room.getPrice()) {
				owner.getPackets().sendGameMessage(
						"You don't have enough coins to build this room.");
				return;
			}
		}
		owner.getDialogueManager().startDialogue(
				"CreateRoomD",
				new RoomReference(room, position[0], position[1], position[2],
						0));
	}

	public void createRoom(RoomReference room) {
		if (owner.getRights() < 2) {
			if (owner.getSkills().getLevel(Skills.CONSTRUCTION) < room.room.getLevel()) {
				owner.getPackets().sendGameMessage(
						"You need a Construction level of " + room.room.getLevel()
								+ " to build this room.");
				return;
			}
			if (owner.getInventory().getNumerOf(995) < room.room.getPrice()) { 
				owner.getPackets().sendGameMessage(
						"You don't have enough coins to build this room.");
				return;
			}
		}
		//owner.getInventory().deleteItem(
		//		new Item(995, room.room.getPrice()));
		if (roomsR.add(room)) {
			owner.getInventory().removeItemMoneyPouch(new Item(995, room.room.getPrice()));
			refreshNumberOfRooms();
			refreshHouse();
		}
	}

	public void destroyHouse() {
		final int[] boundChunksCopy = boundChuncks;
		// this way a new house can be created while current house being
		// destroyed
		loaded = false;
		litBurners = 0;
		boundChuncks = null;
		removeServant();
	//	player.setNextWorldTile(Settings.RESPAWN_PLAYER_LOCATION);
		CoresManager.slowExecutor.schedule(new Runnable() {
			@Override
			public void run() {
				RegionBuilder.destroyMap(boundChunksCopy[0], boundChunksCopy[1],
						8, 8);
				for (WorldObject object : World.getRegion(RegionBuilder.getRegionId(boundChunksCopy[0], boundChunksCopy[1])).getSpawnedObjects())
					if(object != null)
					 World.removeObject(object);
			}
		}, 1200, TimeUnit.MILLISECONDS);
	}

	public void enterMyHouse() {
		joinHouse(owner);
	}

	public void expelGuests() {
		if (!isOwnerInside()) {
			owner.getPackets()
					.sendGameMessage(
							"You can only expel guests when you are in your own house.");
			return;
		}
		kickGuests();
	}

	/*
	 * refers to logout
	 */
	public void finish() {
		kickGuests();
		// no need to leavehouse for owner, controler does that itself
	}

	public Player getPlayer() {
		return owner;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public WorldTile getPortal() {
		for (RoomReference room : roomsR) {
			if (room.room == HouseConstants.Room.GARDEN
					|| room.room == HouseConstants.Room.FORMAL_GARDEN) {
				for (ObjectReference o : room.objects)
					if (o.getPiece() == HouseConstants.HObject.EXIT_PORTAL)
						return new WorldTile(boundChuncks[0] * 8 + room.x * 8
								+ 3, boundChuncks[1] * 8 + room.y * 8 + 3,
								room.plane);
			}
		}
		// shouldnt happen
		int[] xyp = MapUtils.convert(MapUtils.Structure.CHUNK,
				MapUtils.Structure.TILE, boundChuncks);
		return new WorldTile(xyp[0] + 32, xyp[1] + 32, 0);
	}
	
	public WorldTile getMenagerie() {
		for (RoomReference room : roomsR) {
			if (room.room == HouseConstants.Room.MENAGERIE) {
				for (ObjectReference o : room.objects)
					if (o.getPiece() == HouseConstants.HObject.OAK_PET_HOUSE
					|| o.getPiece() == HouseConstants.HObject.TEAK_PET_HOUSE
					|| o.getPiece() == HouseConstants.HObject.MAHOGANY_PET_HOUSE
					|| o.getPiece() == HouseConstants.HObject.CONSECRATED_PET_HOUSE
					|| o.getPiece() == HouseConstants.HObject.DESECRATED_PET_HOUSE
					|| o.getPiece() == HouseConstants.HObject.NATURAL_PET_HOUSE)
						return new WorldTile(boundChuncks[0] * 8 + room.x * 8
								+ 3, boundChuncks[1] * 8 + room.y * 8 + 3,
								room.plane);
			}
		}
		// shouldnt happen
		int[] xyp = MapUtils.convert(MapUtils.Structure.CHUNK,
				MapUtils.Structure.TILE, boundChuncks);
		return new WorldTile(xyp[0] + 32, xyp[1] + 32, 0);
	}

	public int getPortalCount() {
		int count = 0;
		for (RoomReference room : roomsR) {
			if (room.room == HouseConstants.Room.GARDEN
					|| room.room == HouseConstants.Room.FORMAL_GARDEN) {
				for (ObjectReference o : room.objects)
					if (o.getPiece() == HouseConstants.HObject.EXIT_PORTAL)
						count++;
			}
		}
		return count;
	}

	public RoomReference getPortalRoom() {
		for (RoomReference room : roomsR) {
			if (room.room == HouseConstants.Room.GARDEN
					|| room.room == HouseConstants.Room.FORMAL_GARDEN) {
				for (ObjectReference o : room.objects)
					if (o.getPiece() == HouseConstants.HObject.EXIT_PORTAL)
						return room;
			}
		}
		return null;
	}

	public RoomReference getRoom(int x, int y, int plane) {
		for (RoomReference room : roomsR)
			if (room.x == x && room.y == y && room.plane == plane)
				return room;
		return null;
	}

	public boolean hasWaterCan() {
		for (int id = 5333; id <= 5340; id++)
			if (owner.getInventory().containsItem(id, 1))
				return true;
		return false;
	}

	public void init() {
		if (build == 0)
			reset();
		players = new ArrayList<Player>();
		refreshBuildMode();
		refreshArriveInPortal();
		refreshNumberOfRooms();
	}

	public boolean isBuildMode() {
		return buildMode;
	}

	public boolean isDoor(WorldObject object) {
		return object.getDefinitions().name.equalsIgnoreCase("Door hotspot");
	}

	public boolean isDoorSpace(WorldObject object) {
		return object.getDefinitions().name.equalsIgnoreCase("Door space");
	}

	public boolean isOwner(Player player) {
		return this.owner == player;
	}

	private boolean isOwnerInside() {
		return players.contains(owner);
	}

	public boolean isSky(int x, int y, int plane) {
		return buildMode
				&& plane == 2
				&& getRoom((x / 8) - boundChuncks[0],
						(y / 8) - boundChuncks[1], plane) == null;
	}

	public boolean isWindow(int id) {
		return id == 13830;
	}

	public boolean joinHouse(final Player player) {
		if (this == null || player == null)
			return false;
		if (player.isLocked())
			return false;
		player.lock(1);
		if (!isOwner(player)) { // not owner
			if (!isOwnerInside() || !loaded || owner.blockPlayers || !owner.hasHouse) {//add the has house check after you add the dialogue
				player.getPackets().sendGameMessage("That player is offline, or has privacy mode enabled."); // TODO														// message
				return false;
			}
			if (buildMode) {
				player.getPackets().sendGameMessage(
						"The owner currently has build mode turned on.");
				return false;
			}
		}
		if (owner.getHouse() == null)
			return false;
		if (this.equals(null))
			return false;
		players.add(player);
		sendStartInterface(player);
		player.getControlerManager().startControler("HouseControler", this);
		if (loaded) {
			teleportPlayer(player);
			WorldTasksManager.schedule(new WorldTask() {
				@Override
				public void run() {
					player.lock(1);
					player.getInterfaceManager().sendWindowPane();
				}
			}, 4);
		} else {
			CoresManager.slowExecutor.execute(new Runnable() {
				@Override
				public void run() {
					try { // sets bounds before finishing load therefore the
							// load boolean
						//boundChuncks = MapBuilder.findEmptyChunkBound(8, 8);//holy fucken nigger this was way too obvious
						//due to using a different method, dynamic regions were clashing lul ffs
						boundChuncks = RegionBuilder.findEmptyChunkBound(8, 8);
						createHouse(true);
					} catch (Throwable e) {
						Logger.handle(e);
					}
				}
			});
		}
		return true;
	}

	public void kickGuests() {
		if (players != null) {
			for (Player player : new ArrayList<Player>(players)) {
				if (isOwner(player))
					continue;
				leaveHouse(player, KICKED);
			}
		}
	}

	public void leaveHouse(Player player, int type) {
		if (player.getActionManager().getAction() != null)
			player.getActionManager().forceStop();
		player.stopAll(true);
		player.setCanPvp(false);
		player.setForceMultiArea(false);
		//player.getControlerManager().removeControlerWithoutCheck();
		player.getControlerManager().getControler().removeControler();
		if (type == LOGGED_OUT || type == KICKED)
			player.setNextWorldTile(new WorldTile(2851, 3343, 0));
		if (isOwner(player) && servantInstance != null)
			servantInstance.setFollowing(false);
		if (players.contains(player))
			players.remove(player);
		if (players.size() == 0)
			destroyHouse();
	}

	public void openBuildInterface(WorldObject object, final Builds build) {
		if (!buildMode) {
			owner.getDialogueManager().startDialogue("SimpleMessage",
					"You can only do that in building mode.");
			return;
		}
		int roomX = object.getChunkX() - boundChuncks[0];
		int roomY = object.getChunkY() - boundChuncks[1];
		RoomReference room = getRoom(roomX, roomY, object.getPlane());
		if (room == null)
			return;
		int interId = build.getPieces().length > 3 ? 396 : 394;
		Item[] itemArray = new Item[interId == 396 ? 7 : 3];
		for (int index = 0; index < build.getPieces().length; index++) {
			HObject piece = build.getPieces()[index];
			itemArray[interId == 396 ? BUILD_INDEXES[index] : index] = new Item(
					piece.getItemId(), 1);
			owner.getPackets().sendConfig(
					1485 + index,
					owner.getSkills().getLevel(Skills.CONSTRUCTION) >= piece
							.getLevel()
							&& owner.getInventory().containsItems(
									piece.getRequirements()) ? 1 : 0);
		}
		owner.getPackets().sendItems(8, itemArray);
		owner.getPackets().sendInterSetItemsOptionsScript(interId, 11, 8,
				interId == 396 ? 2 : 1, 4, "Build");
		owner.getPackets().sendUnlockIComponentOptionSlots(interId, 11, 0,
				interId == 396 ? 7 : 3, 0);
		owner.getInterfaceManager().sendInterface(interId);
		for (int i = 0; i < (interId == 396 ? 7 : 3); i++) {
			if (i >= build.getPieces().length) {
				owner.getPackets().sendHideIComponent(interId,
						(interId == 394 ? 29 : 49) + i, true);
				owner.getPackets().sendIComponentText(interId,
						(interId == 394 ? 32 : 56) + i, "");
				owner.getPackets()
						.sendIComponentText(interId, 14 + (5 * i), "");
				for (int i2 = 0; i2 < 4; i2++)
					owner.getPackets().sendIComponentText(interId,
							15 + i2 + (5 * i), "");
			} else {
				owner.getPackets().sendIComponentText(interId,
						(interId == 394 ? 32 : 56) + i,
						"Lvl " + build.getPieces()[i].getLevel());
				owner.getPackets().sendIComponentText(
						interId,
						14 + (5 * i),
						ItemDefinitions.getItemDefinitions(
								build.getPieces()[i].getItemId()).getName());
				for (int i2 = 0; i2 < 4; i2++)
					owner.getPackets()
							.sendIComponentText(
									interId,
									15 + i2 + (5 * i),
									build.getPieces()[i].getRequirements().length <= i2 ? ""
											: build.getPieces()[i]
													.getRequirements()[i2]
													.getName()
													+ ": "
													+ build.getPieces()[i]
															.getRequirements()[i2]
															.getAmount());
			}
		}
		owner.getTemporaryAttributtes().put("OpenedBuild", build);
		owner.getTemporaryAttributtes().put("OpenedBuildObject", object);
		owner.setCloseInterfacesEvent(new Runnable() {
			@Override
			public void run() {
				owner.getTemporaryAttributtes().remove("OpenedBuild");
				owner.getTemporaryAttributtes().remove("OpenedBuildObject");
			}

		});
	}

	public void openRemoveBuild(WorldObject object) {
		if (!buildMode) {
			owner.getDialogueManager().startDialogue("SimpleMessage",
					"You can only do that in building mode.");
			return;
		}
		if (object.getId() == HouseConstants.HObject.EXIT_PORTAL.getId()
				&& getPortalCount() <= 1) {
			owner.getDialogueManager().startDialogue("SimpleMessage",
					"Your house must have at least one exit portal.");
			return;
		}
		int roomX = object.getChunkX() - boundChuncks[0];
		int roomY = object.getChunkY() - boundChuncks[1];
		RoomReference room = getRoom(roomX, roomY, object.getPlane());
		if (room == null)
			return;
		ObjectReference ref = room.getObject(object);
		if (ref != null) {
			if (ref.build.toString().contains("STAIRCASE")) {
				if (object.getPlane() != 1) {
					RoomReference above = getRoom(roomX, roomY, 2);
					RoomReference below = getRoom(roomX, roomY, 0);
					if ((above != null && above.getStaircaseSlot() != -1)
							|| (below != null && below.getStaircaseSlot() != -1))
						owner.getDialogueManager()
								.startDialogue("SimpleMessage",
										"You cannot remove a building that is supporting this room.");
					return;
				}
			}
			owner.getDialogueManager().startDialogue("RemoveBuildD", object);
		}
	}

	/*
	 * door used to calculate where player facing to create
	 */
	public void openRoomCreationMenu(int roomX, int roomY, int plane) {
		if (!buildMode) {
			owner.getDialogueManager().startDialogue("SimpleMessage",
					"You can only do that in building mode.");
			return;
		}
		RoomReference room = getRoom(roomX, roomY, plane);
		if (room != null) {
			if (room.plane == 1
					&& getRoom(roomX, roomY, room.plane + 1) != null) {
				owner.getDialogueManager()
						.startDialogue("SimpleMessage",
								"You can't remove a room that is supporting another room.");
				return;
			}
			if ((room.room == Room.GARDEN || room.room == Room.FORMAL_GARDEN)
					&& getPortalCount() < 2) {
				if (room == getPortalRoom()) {
					owner.getDialogueManager().startDialogue("SimpleMessage",
							"Your house must have at least one exit portal.");
					return;
				}
			}
			owner.getDialogueManager().startDialogue("RemoveRoomD", room);
		} else {
			if (roomX == 0 || roomY == 0 || roomX == 7 || roomY == 7) {
				owner.getDialogueManager().startDialogue("SimpleMessage",
						"You can't create a room here.");
				return;
			}
			if (plane == 2) {
				RoomReference r = getRoom(roomX, roomY, 1);
				if (r == null
						|| (r.room == Room.GARDEN
								|| r.room == Room.FORMAL_GARDEN || r.room == Room.MENAGERIE)) {
					owner.getDialogueManager().startDialogue("SimpleMessage",
							"You can't create a room here.");
					return;
				}

			}
			for (int index = 0; index < HouseConstants.Room.values().length; index++) {
				Room refRoom = HouseConstants.Room.values()[index];
				int bugged = index + 68;//these are the prices / names that are bugged due to shit interface creation
				if (owner.getSkills().getLevel(Skills.CONSTRUCTION) >= refRoom
						.getLevel()
						&& owner.getInventory().getNumerOf(995) >= refRoom
								.getPrice())
					if (bugged != 88 && bugged != 91 && bugged != 90 && bugged != 92)
					owner.getPackets().sendIComponentText(402, 68 + index,
							"<col=008000> " + refRoom.getPrice() + " coins");
				owner.getPackets().sendIComponentText(402, 90, (owner.getInventory().getNumerOf(995) >= 10000 ? "<col=008000>10000" : "<col=cc0000>10000"));
				owner.getPackets().sendIComponentText(402, 92, (owner.getInventory().getNumerOf(995) >= 250000 ? "<col=008000>250000" : "<col=cc0000>250000"));
			}
			owner.getInterfaceManager().sendInterface(402);
			owner.getTemporaryAttributtes().put("CreationRoom",
					new int[] { roomX, roomY, plane });
			owner.setCloseInterfacesEvent(new Runnable() {
				@Override
				public void run() {
					owner.getTemporaryAttributtes().remove("CreationRoom");
				}
			});
		}
	}

	public void openRoomCreationMenu(WorldObject door) {
		int roomX = owner.getChunkX() - boundChuncks[0]; // current room
		int roomY = owner.getChunkY() - boundChuncks[1]; // current room
		int xInChunk = owner.getXInChunk();
		int yInChunk = owner.getYInChunk();
		if (xInChunk == 7)
			roomX += 1;
		else if (xInChunk == 0)
			roomX -= 1;
		else if (yInChunk == 7)
			roomY += 1;
		else if (yInChunk == 0)
			roomY -= 1;
		openRoomCreationMenu(roomX, roomY, door.getPlane());
	}

	public void pickSlotforBuild(final Builds build, RoomReference newRoom) {
		if (!buildMode) {
			owner.getDialogueManager().startDialogue("SimpleMessage",
					"You can only do that in building mode.");
			return;
		}
		int interId = build.getPieces().length > 3 ? 396 : 394;
		Item[] itemArray = new Item[interId == 396 ? 7 : 3];
		for (int index = 0; index < build.getPieces().length; index++) {
			HObject piece = build.getPieces()[index];
			itemArray[interId == 396 ? BUILD_INDEXES[index] : index] = new Item(
					piece.getItemId(), 1);
			owner.getPackets().sendConfig(
					1485 + index,
					owner.getSkills().getLevel(Skills.CONSTRUCTION) >= piece
							.getLevel()
							&& owner.getInventory().containsItems(
									piece.getRequirements()) ? 1 : 0);
		}
		owner.getPackets().sendItems(8, itemArray);
		owner.getPackets().sendInterSetItemsOptionsScript(interId, 11, 8,
				interId == 396 ? 2 : 1, 4, "Build");
		owner.getPackets().sendUnlockIComponentOptionSlots(interId, 11, 0,
				interId == 396 ? 7 : 3, 0);
		owner.getInterfaceManager().sendInterface(interId);
		for (int i = 0; i < (interId == 396 ? 7 : 3); i++) {
			if (i >= build.getPieces().length) {
				owner.getPackets().sendHideIComponent(interId,
						(interId == 394 ? 29 : 49) + i, true);
				owner.getPackets().sendIComponentText(interId,
						(interId == 394 ? 32 : 56) + i, "");
				owner.getPackets()
						.sendIComponentText(interId, 14 + (5 * i), "");
				for (int i2 = 0; i2 < 4; i2++)
					owner.getPackets().sendIComponentText(interId,
							15 + i2 + (5 * i), "");
			} else {
				owner.getPackets().sendIComponentText(interId,
						(interId == 394 ? 32 : 56) + i,
						"Lvl " + build.getPieces()[i].getLevel());
				owner.getPackets().sendIComponentText(
						interId,
						14 + (5 * i),
						ItemDefinitions.getItemDefinitions(
								build.getPieces()[i].getItemId()).getName());
				for (int i2 = 0; i2 < 4; i2++)
					owner.getPackets()
							.sendIComponentText(
									interId,
									15 + i2 + (5 * i),
									build.getPieces()[i].getRequirements().length <= i2 ? ""
											: build.getPieces()[i]
													.getRequirements()[i2]
													.getName()
													+ ": "
													+ build.getPieces()[i]
															.getRequirements()[i2]
															.getAmount());
			}
		}
		owner.getTemporaryAttributtes().put("GetSlotBuild", build);
		owner.getTemporaryAttributtes().put("GetNewRoom", newRoom);
		owner.setCloseInterfacesEvent(new Runnable() {
			@Override
			public void run() {
				owner.getTemporaryAttributtes().remove("GetSlotBuild");
				owner.getTemporaryAttributtes().remove("GetNewRoom");
			}

		});
	}

	public void previewRoom(RoomReference reference, boolean remove,
			boolean first) {
		int boundX = boundChuncks[0] * 8 + reference.x * 8;
		int boundY = boundChuncks[1] * 8 + reference.y * 8;
		int realChunkX = reference.room.getChunkX();
		int realChunkY = reference.room.getChunkY();
		Region region = World.getRegion(MapUtils.encode(
				MapUtils.Structure.REGION, realChunkX / 8, realChunkY / 8));
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				WorldObject[] objects = region.getAllObjects(reference.plane,
						(realChunkX & 0x7) * 8 + x, (realChunkY & 0x7) * 8 + y);
				if (objects != null) {
					for (WorldObject object : objects) {
						if (object == null)
							continue;
						ObjectDefinitions defs = object.getDefinitions();
						if (defs.containsOption(4, "Build")) {
							WorldObject objectR = new WorldObject(object);
							int[] coords = DynamicRegion.translate(x, y,
									reference.rotation, defs.sizeX, defs.sizeY,
									object.getRotation());
							objectR.setLocation(new WorldTile(boundX
									+ coords[0], boundY + coords[1],
									reference.plane));
							objectR.setRotation((object.getRotation() + reference.rotation) & 0x3);
							// just a preview. they're not realy there.
							if (remove) {
								World.removeObject(objectR);// removes chair for
															// example and
															// returns orginal
								// World.removeObject(objectR);// removes
								// orginal
							} else {
								World.spawnObject(objectR);// spawns object,
															// doesnt spawn
															// orginal
							}
						}
					}
				}
				if (reference.plane == 0) {
					if (!remove) {
						if (World.getSpawnedObject(boundX + x, boundY + y, 0) != null
								&& (World.getSpawnedObject(boundX + x,
										boundY + y, 0).getId() == 13068 || World
										.getSpawnedObject(boundX + x,
												boundY + y, 0).getId() == 13026)
								|| World.getSpawnedObject(boundX + x,
										boundY + y, 0).getId() == 13028
								|| World.getSpawnedObject(boundX + x,
										boundY + y, 0).getId() == 13027)// checks
																		// if
																		// spawned
																		// object
																		// here
							World.spawnObject(new WorldObject(10, 10, 0, boundX
									+ x, boundY + y, reference.plane));// spawns
																		// object
																		// 10 to
																		// get
																		// rid
																		// of
																		// tiles
					} else {
						if (World.getSpawnedObject(boundX + x, boundY + y, 0) != null
								&& World.getSpawnedObject(boundX + x,
										boundY + y, 0).getId() == 10)
							World.removeObject(World.getSpawnedObject(boundX
									+ x, boundY + y, 0));
					}
				}
			}
		}
	}

	public void refreshArriveInPortal() {
		owner.getPackets().sendConfigByFile(6450, arriveInPortal ? 1 : 0);
	}

	/*
	 * public void previewRoom(RoomReference reference, boolean remove) { int
	 * boundX = boundChuncks[0] * 8 + reference.x * 8; int boundY =
	 * boundChuncks[1] * 8 + reference.y * 8; int realChunkX =
	 * reference.room.getChunkX(); int realChunkY = reference.room.getChunkY();
	 * Region region = World.getRegion(MapUtils.encode(
	 * MapUtils.Structure.REGION, realChunkX / 8, realChunkY / 8)); for(int x =
	 * 0; x < 8; x++) { for(int y = 0; y < 8; y++) { WorldObject object =
	 * World.getSpawnedObject(boundX + x, boundY + y, reference.plane);
	 * if(object != null) World.removeObject(object); WorldObject[] objects =
	 * region.getAllObjects(reference.plane, (realChunkX & 0x7) * 8 + x,
	 * (realChunkY & 0x7) * 8 + y); for(WorldObject roomObject : objects) {
	 * if(roomObject == null) return; ObjectDefinitions defs =
	 * roomObject.getDefinitions(); WorldObject objectR = new
	 * WorldObject(roomObject); int[] coords = DynamicRegion.translate(x, y,
	 * reference.rotation, defs.sizeX, defs.sizeY, roomObject.getRotation());
	 * objectR.setLocation(new WorldTile(boundX + coords[0], boundY + coords[1],
	 * reference.plane)); objectR.setRotation((roomObject.getRotation() +
	 * reference.rotation) & 0x3); if(remove) World.removeObject(objectR); else
	 * World.spawnObject(objectR); }
	 * 
	 * } } }
	 */

	public void refreshBuildMode() {
		owner.getPackets().sendConfigByFile(2176, buildMode ? 1 : 0);
	}

	public void refreshHouse() {
		loaded = false;
		sendStartInterface(owner);
		createHouse(false);
	}

	public void refreshNumberOfRooms() {
		owner.getPackets().sendGlobalConfig(944, roomsR.size());
	}
	
	public void setServantOrdinal(byte ordinal) {
		if (ordinal == -1) {
			removeServant();
			servant = null;
			return;
		}
		this.servant = HouseConstants.Servant.values()[ordinal];
	}

	public boolean hasServant() {
		return servant != null;
	}
	
	private void removeServant() {
		if (servantInstance != null) {
			servantInstance.finish();
			servantInstance = null;
		}
	}

	private void addServant() {
		if (servantInstance == null && servant != null)
			servantInstance = new ServantNPC(this);
	}

	/*
	 * when switching from modes
	 */
	
	public void callServant(boolean bellPull) {
		if (bellPull) {
			owner.setNextAnimation(new Animation(3668));
			owner.lock(2);
		}
		if (servantInstance == null)
			owner.getPackets().sendGameMessage("The house has no servant.");
		else {
			servantInstance.setFollowing(true);
			servantInstance.setNextWorldTile(owner.getNextWorldTile());
			servantInstance.setNextAnimation(new Animation(858));
			owner.getDialogueManager().startDialogue("ServantD", servantInstance);
		}
	}
	
	private void refreshServant() {
		removeServant();
		addServant();
	}

	public Servant getServant() {
		return servant;
	}
	
	public ServantNPC getServantInstance() {
		return servantInstance;
	}

	private void refreshObject(RoomReference rref, ObjectReference oref,
			boolean remove, boolean challengeMode) {
		int boundX = rref.x * 8;
		int boundY = rref.y * 8;
		int[] regionPos = MapUtils.convert(MapUtils.Structure.CHUNK,
				MapUtils.Structure.REGION, boundChuncks);
		final Region region = World.getRegion(
				MapUtils.encode(MapUtils.Structure.REGION, regionPos), true);
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				WorldObject[] objects = region.getAllObjects(rref.plane, boundX
						+ x, boundY + y);
				if (objects != null) {
					for (WorldObject object : objects) {
						if (object == null)
							continue;
						int slot = oref.build.getIdSlot(object.getId());
						if (slot == -1)
							continue;
						// System.out.println(remove+", "+object.getX()+", "+object.getY()+"");
						if (remove)
							World.spawnObject(object);
						else {
							WorldObject objectR = new WorldObject(object);
							objectR.setId(oref.getId(slot));
							World.spawnObject(objectR);
						}
					}
				}
			}
		}
	}

	public void reload(RoomReference reference) {
		int boundX = boundChuncks[0] * 8 + reference.x * 8;
		int boundY = boundChuncks[1] * 8 + reference.y * 8;
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				if (World.getRemovedOrginalObject(0, boundX, boundY, 10) != null)
					World.spawnObject(World.getRemovedOrginalObject(0, boundX,
							boundY, 10));
			}
		}

	}

	public void removeBuild(final WorldObject object) {
		if (object == null)
			return;
		if (!buildMode) { // imagine u use settings to change while dialogue
							// open, cheater :p
			owner.getDialogueManager().startDialogue("SimpleMessage",
					"You can only do that in building mode.");
			return;
		}
		int roomX = object.getChunkX() - boundChuncks[0];
		int roomY = object.getChunkY() - boundChuncks[1];
		final RoomReference room = getRoom(roomX, roomY, object.getPlane());
		if (room == null)
			return;
		final ObjectReference oref = room.removeObject(object);
		if (oref == null)
			return;
		owner.lock();
		owner.setNextAnimation(new Animation(3685));
		WorldTasksManager.schedule(new WorldTask() {
			@Override
			public void run() {
				World.removeObject(object);
				refreshObject(room, oref, true, false);
				owner.lock(1);
			}
		}, 1);
	}

	public void removeRoom() {
		int roomX = owner.getChunkX() - boundChuncks[0]; // current room
		int roomY = owner.getChunkY() - boundChuncks[1]; // current room
		RoomReference room = getRoom(roomX, roomY, owner.getPlane());
		if (room == null)
			return;
		if (room.getPlane() != 1) {
			owner.getDialogueManager()
					.startDialogue("SimpleMessage",
							"You cannot remove a building that is supporting this room.");
			return;
		}

		RoomReference above = getRoom(roomX, roomY, 2);
		RoomReference below = getRoom(roomX, roomY, 0);

		RoomReference roomTo = above != null && above.getStaircaseSlot() != -1 ? above
				: below != null && below.getStaircaseSlot() != -1 ? below
						: null;
		if (roomTo == null) {
			owner.getDialogueManager().startDialogue("SimpleMessage",
					"These stairs do not lead anywhere.");
			return;
		}
		openRoomCreationMenu(roomTo.getX(), roomTo.getY(), roomTo.getPlane());
	}

	public void removeRoom(RoomReference room) {
		if (roomsR.remove(room)) {
			refreshNumberOfRooms();
			refreshHouse();
		}
	}

	/*
	 * temporary
	 */
	private void reset() {
		build = 1;
		buildMode = true;
		roomsR = new ArrayList<RoomReference>();
		addRoom(HouseConstants.Room.GARDEN, 3, 3, 1, 0);
		getRoom(3, 3, 1).addObject(Builds.CENTREPIECE, 0);
	}

	public void sendStartInterface(Player player) {
		player.lock();
		player.getPackets().sendWindowsPane(399, 0);
		player.getMusicsManager().playMusic(454);
		player.getPackets().sendMusicEffect(22);
	}

	public void setArriveInPortal(boolean arriveInPortal) {
		this.arriveInPortal = arriveInPortal;
		refreshArriveInPortal();

	}

	public void setBuildMode(boolean buildMode) {
		if (this.buildMode == buildMode)
			return;
		this.buildMode = buildMode;
		if (loaded) {
			expelGuests();
			if (isOwnerInside()) // since it expels all guests no point in
									// refreshing if owner not inside
				refreshHouse();
		}
		refreshBuildMode();
	}

	public void setPlayer(Player player) {
		this.owner = player;
		look = (byte) player.getHouseStyle();
		//6 = zeneviva dark stone
		//5 = fancy stone
		//4 = tropical wood
		//3 = fremmenik wood
		//2 = white washed stone (desert)
		//1 = basic stone
		//0 = basic wood (default)
	}
	
	public void switchChallengeMode(boolean pvp) {
		if (isBuildMode())
			return;
		if (isPVPMode()) {
			for (Player player : new ArrayList<Player>(players))
				player.setCanPvp(false);
		}
		challengeMode = isChallengeMode() ? 0 : pvp ? 2 : 1;
		refreshChallengeMode();
		//player.lock(2);
	}
	public boolean isChallengeMode() {
		return challengeMode != 0;
	}

	public boolean isPVPMode() {
		return challengeMode == 2;
	}

	public void sendPullLeverEmote(WorldObject object) {
		player.lock(2);
		player.setNextAnimation(new Animation(3611));
		World.sendObjectAnimation(object, new Animation(3612));
	}

	public void leverEffect(WorldObject object) {
		sendPullLeverEmote(object);
		player.getPackets().sendGameMessage("Nothing interesting happens hehe.");
	}
	
	public void pullLeverChallengeMode(WorldObject object) {
		if (isChallengeMode()) {
			player.getPackets().sendGameMessage("You turn off " + (isPVPMode() ? "pvp" : "challenge") + " mode.");
			switchChallengeMode(false);
			sendPullLeverEmote(object);
		} else {
			World.sendWorldMessage("This is not functional as of yet.", false);
		//	player.getDialogueManager().startDialogue("DrygoreCraft", object);
		//	player.getDialogueManager().startDialogue("ChallengeModeLeverD", object);
		}
	}
	
	
	
	public void changeLook(Player player) {
		this.look = (byte) player.getHouseStyle();
	}

	public void switchLock(Player player) {
		if (!isOwner(player)) {
			player.getPackets().sendGameMessage(
					"You can only lock your own house.");
			return;
		}
		locked = !locked;
		if (locked)
			player.getDialogueManager().startDialogue("SimpleMessage",
					"Your house is now locked to all visistors.");
		else if (buildMode)
			player.getDialogueManager()
					.startDialogue("SimpleMessage",
							"Visitors will be able to enter your house once you leave building mode.");
		else
			player.getDialogueManager().startDialogue("SimpleMessage",
					"Visistors can now enter your house.");
	}

	public void teleportPlayer(Player player) {
		player.setNextWorldTile(getPortal());
	}
	
	public WorldTile getMenagerie(int deviation) {
		for (RoomReference room : roomsR) {
			if (room.room == HouseConstants.Room.MENAGERIE) {
				for (ObjectReference o : room.objects)
					if (o.getPiece() == HouseConstants.HObject.OAK_PET_HOUSE
					|| o.getPiece() == HouseConstants.HObject.TEAK_PET_HOUSE
					|| o.getPiece() == HouseConstants.HObject.MAHOGANY_PET_HOUSE
					|| o.getPiece() == HouseConstants.HObject.CONSECRATED_PET_HOUSE
					|| o.getPiece() == HouseConstants.HObject.DESECRATED_PET_HOUSE
					|| o.getPiece() == HouseConstants.HObject.NATURAL_PET_HOUSE) {
						//return new WorldTile(boundChuncks[0] * 8 + (room.x * 8), boundChuncks[1] * 8 + (room.y * 8),
						//		room.plane);
						switch (deviation) {
						case 0:
							return new WorldTile(boundChuncks[0] * 8 + (room.x * 8) + 2, boundChuncks[1] * 8 + (room.y * 8) + 4, room.plane);
						case 1:
							return new WorldTile(boundChuncks[0] * 8 + (room.x * 8) + 4, boundChuncks[1] * 8 + (room.y * 8) + 5, room.plane);
						case 2:
							return new WorldTile(boundChuncks[0] * 8 + (room.x * 8) + 6, boundChuncks[1] * 8 + (room.y * 8) + 3, room.plane);
						case 3:
							return new WorldTile(boundChuncks[0] * 8 + (room.x * 8) + 6, boundChuncks[1] * 8 + (room.y * 8) + 1, room.plane);
						case 4:
						default:
							return new WorldTile(boundChuncks[0] * 8 + (room.x * 8) + 8, boundChuncks[1] * 8 + (room.y * 8) + 3, room.plane);
						}
					}
			}
		}
		// shouldnt happen
		int[] xyp = MapUtils.convert(MapUtils.Structure.CHUNK,
				MapUtils.Structure.TILE, boundChuncks);
		return new WorldTile(xyp[0] + 32, xyp[1] + 32, 0);
	}
	
	public void spawnPet(int id, int deviation) {
		NPC npc = new NPC(id, new WorldTile(getMenagerie(deviation)), -1, false);
		npc.setCombatLevel(0);
		//World.sendWorldMessage("<col=ff0000>Spawned pet: "+NPCDefinitions.getNPCDefinitions(id).getName()+", Coords: "+getMenagerie(deviation).getX()+", "+getMenagerie(deviation).getY()+".");
	}

	public int getPaymentStage() {
		return paymentStage;
	}

	public void resetPaymentStage() {
		paymentStage = 0;
	}

	public void incrementPaymentStage() {
		paymentStage++;
	}
	
}
