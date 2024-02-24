package com.rs.game.player.content.clans.clancitadels;

import com.rs.cores.CoresManager;
import com.rs.game.RegionBuilder;
import com.rs.game.WorldTile;
import com.rs.game.player.Player;
import com.rs.game.player.content.FadingScreen;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.game.World;
 
import com.rs.game.WorldObject;

/**
 * 
 * @author Josh'
 *
 */
public class ClanCitadel {

	public static int[] boundChunk;

	public static WorldTile clan;

	private static boolean owner;
	
	public enum Stages {
		LOADING, RUNNING, DESTROYING;
	}
	
	private Stages stage;

	public ClanCitadel(final Player player) {
		setOwner(true);
		loadMap(player);
	}
	
	public void loadMap(final Player player) {
		final long time = FadingScreen.fade(player);
		stage = Stages.LOADING;
		player.lock();
		CoresManager.slowExecutor.execute(new Runnable() {
			@Override
			public void run() {
			   boundChunk = RegionBuilder.findEmptyChunkBound(8, 8); 
			   RegionBuilder.copyAllPlanesMap(568, 576, boundChunk[0], boundChunk[1], 40);
			   addToCitadel(null);
			}
		});
		FadingScreen.unfade(player, time, new Runnable() {
			@Override
			public void run() {
				addToCitadel(player);
				addObjectToCitadel(player);
				player.unlock();
			}
		});
		WorldTasksManager.schedule(new WorldTask()  {
			@Override
			public void run() {
				player.unlock();
				stage = Stages.RUNNING;
			}
		}, 1);
	}
	
	public static void joinCitadel(final Player player, final Player target) {
		final long time = FadingScreen.fade(player);
		WorldTasksManager.schedule(new WorldTask() {
			
			@Override
			public void run() {
				try {
					player.getTemporaryAttributtes().put("joining_citadel_camp", Boolean.FALSE);
					FadingScreen.unfade(player, time, new Runnable() {
						@Override
						public void run() {
							player.setNextWorldTile(new WorldTile(target.getLastWorldTile()));
							target.getPackets().sendGameMessage(player.getDisplayName() + " has joined your Citadel.");
							player.getControlerManager().startControler("ClanCitadel");
							player.unlock();
						}
					});
	
				} catch (Throwable e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void DestroyCitadel(final boolean logout, final Player player) {
		player.setNextWorldTile(new WorldTile(3676, 2980, 0));
		RegionBuilder.destroyMap(boundChunk[0], boundChunk[1], 40, 40);
		player.getControlerManager().forceStop();
	}
	
	public Object getOwner(final Player player) {
		return player.getTemporaryAttributtes().get("owner_of_citadel");
	}
	
	public void removeOwner(Player owner) {
		owner.getTemporaryAttributtes().get("owner_of_citadel".equals(null));
	}
	
	public void kickFromCitadel(final Player player) {
		if (player.getDisplayName() != player.getTemporaryAttributtes().get("owner_of_citadel")) {
			player.getPackets().sendGameMessage("Only the clan owner can kick others from this citadel!");
			return;
		}
	}
	
	private static void addToCitadel(final Player player) {
		player.getControlerManager().startControler("CitadelControler");
		player.getTemporaryAttributtes().put("owner_of_citadel", player.getDisplayName());
		player.setNextFaceWorldTile(new WorldTile(player.getLastWorldTile()));
		player.setNextWorldTile(new WorldTile(getBaseX() + 242, getBaseY() + 40, 0));
	}
	
	private static void addObjectToCitadel(final Player player) {
		//player.getControlerManager().startControler("CitadelControler");
		//player.getTemporaryAttributtes().put("owner_of_citadel", player.getDisplayName());
		//player.setNextFaceWorldTile(new WorldTile(player.getLastWorldTile()));
		//player.setNextWorldTile(new WorldTile(getBaseX() + 242, getBaseY() + 40, 0));
		World.spawnObject(new WorldObject(1, 10, -1, getBaseX() + 238, getBaseY() + 40, 0), true);
		//World.spawnObject(new WorldObject(1, 10, -1, 3557, 9753, 0), true);
	}
	
	public static void leaveCitadel(Player player) {
		player.getControlerManager().forceStop();
		player.setNextWorldTile(new WorldTile(3676, 2980, 0));
		player.getPackets().sendGameMessage("You leave the Citadel.");
	}
	
	public static void openNoticeboard(Player player) {
		
	}
	
	public static int getBaseX() {
		return boundChunk[0] << 3;
	}

	public static int getBaseY() {
		return boundChunk[1] << 3;
	}
	
	public static boolean setOwner(boolean bool) {
		return owner = bool;
	}
}