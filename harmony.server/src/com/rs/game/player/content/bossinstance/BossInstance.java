package com.rs.game.player.content.bossinstance;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.rs.Settings;
import com.rs.cores.CoresManager;
import com.rs.game.RegionBuilder;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.player.Player;
import com.rs.utils.Logger;

public abstract class BossInstance implements Serializable { //o god

	/**
	 * 
	 */
	private static final long serialVersionUID = 4389004855746919536L;

	protected final WorldTile bottomLeft, topRight;

	private final int height;

	private transient Player owner;
	
	private transient NPC npc;

	private final List<Player> players = new ArrayList<>();

	private final int[] boundChunks;

	private final int sizeX, sizeY;

	public long startTime;
	
	public long TimeLeft;
	
	public BossInstance(WorldTile bottomLeft, WorldTile topRight, int height,
			int sizeX, int sizeY, Player owner) {
		super();
		this.bottomLeft = bottomLeft;
		this.topRight = topRight;
		this.height = height;
		this.sizeX = sizeX;
		this.sizeY = sizeY;

		boundChunks = RegionBuilder.findEmptyChunkBound(sizeX, sizeY);
		RegionBuilder.copyAllPlanesMap(bottomLeft.getChunkX(),
				bottomLeft.getChunkY(), boundChunks[0], boundChunks[1], sizeX,
				sizeY);

		this.owner = owner;
		add(owner);
		//uponEnter(owner);
		CoresManager.slowExecutor.schedule(new RemoveTask(this), 60, TimeUnit.MINUTES); //Timeout
		owner.setForceMultiArea(true);
		owner.getInterfaceManager().sendOverlay(10, true);
		owner.getPackets().sendIComponentText(10, 1, "Minutes Left: ");
		owner.getPackets().sendIComponentText(10, 2, "60");
		Timer();
		owner.setForceMultiArea(true);
	}
	
	public final void Timer() {
		
		startTime = 60;
		CoresManager.slowExecutor.scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {
				try {
					startTime -= 1;
					owner.getPackets().sendIComponentText(10, 2, ""+startTime+"");
				} catch (Throwable e) {
					Logger.handle(e);
					
				}
			}
		}, 0,1, TimeUnit.MINUTES);
		return;
	}


	public final void add(Player player) {
		if (!players.contains(player)) {
			players.add(player);
		}
		player.getControlerManager().startControler(
				InstancedBossController.IDENTIFIER, this);
		uponEnter(player);
		player.setForceMultiArea(true);
	}

	public final void remove(Player player) {
		players.remove(player);
		uponExit(player);
		player.getPackets().sendGameMessage("You have left this instance.");
		player.instancedelay = 180;
		player.setNextWorldTile(Settings.RESPAWN_PLAYER_LOCATION);
		player.getInterfaceManager().closeOverlay(true);
		owner.setForceMultiArea(false);
		player.getControlerManager().removeControlerWithoutCheck();
		if (players.size() == 0) {
			CoresManager.slowExecutor.schedule(new Runnable() {
				@Override
				public void run() {
					RegionBuilder.destroyMap(boundChunks[0], boundChunks[1], 8, 8);
				}
			}, 1200, TimeUnit.MILLISECONDS);
			
			BossInstanceManager.SINGLETON.remove(this); // End
			
		} else {
			handleNewOwnership();
		}
	}

	private void handleNewOwnership() {
		if (!players.contains(owner)) {
			Player player = players.get(0);
			player.getPackets().sendGameMessage(
					"You are now the new owner of this instance.");
			owner = player;
		}
	}

	public abstract void uponEnter(Player player);

	public abstract void uponExit(Player player);

	public abstract void start();

	public int getHeight() {
		return height;
	}

	public WorldTile getWorldTile(int mapX, int mapY) {
		return new WorldTile(boundChunks[0] * 8 + mapX, boundChunks[1] * 8
				+ mapY, height);
	}

	public Player getOwner() {
		return owner;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void removeMapChunks() {
		CoresManager.slowExecutor.schedule(new Runnable() {
			@Override
			public void run() {
				RegionBuilder.destroyMap(boundChunks[0], boundChunks[1], sizeX,
						sizeY);
			}
		}, 1200, TimeUnit.MILLISECONDS);
	}

	public NPC getNpc() {
		return npc;
	}

	private static class RemoveTask implements Runnable {

		private final BossInstance instance;

		public RemoveTask(BossInstance instance) {
			this.instance = instance;
		}

		@Override
		public void run() {
			if (instance.getPlayers().size() != 0) {
				instance.getPlayers().forEach(player -> {
					player.getPackets().sendGameMessage("This instance has ran out of time!");
				});
			}
			BossInstanceManager.SINGLETON.remove(instance);
		}
	}

}