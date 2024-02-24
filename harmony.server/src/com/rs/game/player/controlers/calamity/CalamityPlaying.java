package com.rs.game.player.controlers.calamity;

import java.util.List;
import java.util.TimerTask;

import com.rs.Settings;
import com.rs.cores.CoresManager;
import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.minigames.TheCalamity;
import com.rs.game.npc.Cala;
import com.rs.game.npc.NPC;
import com.rs.game.npc.fightcaves.FightCavesNPC;
import com.rs.game.npc.fightcaves.TzKekCaves;
import com.rs.game.player.content.event.season.SeasonEvent;
import com.rs.game.player.controlers.Controler;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.CalamityBestWave;
import com.rs.utils.Logger;
import com.rs.utils.Utils;

public class CalamityPlaying extends Controler {

	private int team;
	private Stages stage;
	public boolean spawned;

	@Override
	public void start() {
		team = (int) getArguments()[0];
		if (team == 0) {
			setCurrentWave(1);
		}
		startGame(false);
		sendInterfaces();
		player.calamitykillpoints = 50;
		player.getInventory().reset();
		player.getEquipment().reset();
		player.getInventory().refresh();
		player.getEquipment().refresh();
	}
	
	private final int[][] MONSTERS = {

			{ 30200 }, { 30200, 30200 }, { 30200, 30200, 30200 }, { 30200, 30201, 30201, 30201 }, { 30200, 30201, 30201, 30201 }, { 30201, 30201, 30201, 30201, 30201, 30201 }, { 30201, 30201, 30201, 30201, 30201, 30201, 30201, 30201, 30202 }, { 30201, 30201, 30201, 30201, 30201, 30201, 30201, 30201, 30202 }, { 30201, 30201, 30201, 30201, 30201, 30201, 30202, 30202, 30202 }, { 30203 }, { 30202, 30202, 30202, 30202, 30201 }, { 30202, 30202, 30202, 30201, 30201 }, { 30203 }, { 30203, 30203, 30202, 30202, 30201, 30201, 30200, 30200 }, { 30203, 30203, 30203, 30203, 30203, 30203, 30203, 30203 }, { 30203, 30203, 30203, 30203, 30202, 30202, 30202, 30202 }, { 30204 }, { 30204, 30204 }, { 30200, 30204, 30204, 30200, 30200 }, { 30204, 30204, 30201, 30201 },
			{ 30204, 30204, 30201, 30201, 30200, 30200, 30202, 30200 }, { 30204, 30201, 30201, 30202, 30202, 30202, 30203, 30203 }, { 30205 }, { 30206 }, { 30206, 30206, 30206 }, { 30206, 30206, 30206, 30206, 30207, 30206 }, { 30206, 30206, 30206, 30207, 30207, 30207 }, { 30206, 30206, 30206, 30208, 30208, 30208 }, { 30206, 30206, 30207, 30207, 30208, 30208 }, { 30206, 30206, 30207, 30207, 30208, 30208 }, { 30209 }, { 30208, 30207, 30206 }, { 30209, 30208, 30207, 30206 }, { 30211 }, { 30212 }, { 30213 }, { 30211, 30211 }, { 30212, 30212 }, { 30213, 30213 }, { 30211, 30212, 30213 }, { 30211, 30211, 30211 }, { 30212, 30212, 30212 }, { 30213, 30213, 30213 }, { 30211, 30212, 30213, 30211, 30212, 30213 }, { 30210 }, { 30211, 30212, 30213, 30210 }, { 30200, 30201, 30206, 30212 },
			{ 30202, 30208, 30211 }, { 30203, 30207, 30213 }, { 30203, 30207, 30213, 30202, 30208, 30211, 30200, 30201, 30206, 30212 }, { 30204, 30209, 30210 }, { 30202, 30208, 30211, 30202, 30208, 30211, 30202, 30208, 30211, 30202, 30208, 30211, 30202, 30208, 30211, 30202, 30208, 30211, 30202, 30208, 30211 }, { 30203, 30207, 30213, 30203, 30207, 30213, 30203, 30207, 30213, 30203, 30207, 30213, 30203, 30207, 30213, 30203, 30207, 30213, 30203, 30207, 30213 }, { 30201, 30206, 30212, 30201, 30206, 30212, 30201, 30206, 30212, 30201, 30206, 30212, 30201, 30206, 30212, 30201, 30206, 30212, 30201, 30206, 30212 }, { 30201, 30206, 30212, 30201, 30206, 30212, 30202, 30208, 30211, 30202, 30208, 30211, 30203, 30207, 30213, 30203, 30207, 30213 },
			{ 30200, 30201, 30202, 30203, 30204, 30205, 30206, 30207, 30208, 30209, 30210, 30211, 30212, 30213, 30214 }
			,{12877}
			, { 30215 }, { 30201, 30206, 30212, 30214, 30205, 30201, 30206, 30212, 30214, 30205, 30201, 30206, 30212, 30214, 30205, 30201, 30206, 30212, 30214, 30205, 30201, 30206, 30212, 30214, 30205, 30201, 30206, 30212, 30214, 30205 }, { 30205, 30205, 30210, 30210, 30204, 30209, 30214 }, { 30214, 30214, 30214 }, { 30202, 30208, 30211, 30205, 30202, 30208, 30211, 30205, 30202, 30208, 30211, 30205, 30202, 30208, 30211, 30205 }, { 30203, 30207, 30213, 30205, 30203, 30207, 30213, 30205, 30203, 30207, 30213, 30205, 30203, 30207, 30213, 30205 }, { 30205, 30205, 30205, 30205 }, { 30215, 30214, 30215, 30214, 30215, 30214, 30211, 30207 }, { 30205, 30205, 30205, 30205, 30215, 30214, 30215, 30214, 30215, 30214, 30211, 30207 },
			{ 30205, 30205, 30205, 30205, 30215, 30214, 30215, 30214, 30215, 30214, 30211, 30207, 30202, 30208, 30211, 30205, 30202, 30208, 30211, 30205, 30202, 30208, 30211, 30205, 30202, 30208, 30211, 30205 }, { 30205, 30205, 30205, 30205, 30215, 30214, 30215, 30214, 30215, 30214, 30211, 30207, 30202, 30208, 30211, 30205, 30202, 30208, 30211, 30205, 30202, 30208, 30211, 30205, 30202, 30208, 30211, 30205 }, { 30205, 30205, 30205, 30205, 30215, 30214, 30215, 30214, 30215, 30214, 30211, 30207, 30202, 30208, 30211, 30205, 30202, 30208, 30211, 30205, 30202, 30208, 30211, 30205, 30202, 30208, 30211, 30205 },
			{ 30205, 30205, 30205, 30205, 30215, 30214, 30215, 30214, 30215, 30214, 30211, 30207, 30202, 30208, 30211, 30205, 30202, 30208, 30211, 30205, 30202, 30208, 30211, 30205, 30202, 30208, 30211, 30205 }, { 30205, 30205, 30205, 30205, 30215, 30214, 30215, 30214, 30215, 30214, 30211, 30207, 30202, 30208, 30211, 30205, 30202, 30208, 30211, 30205, 30202, 30208, 30211, 30205, 30202, 30208, 30211, 30205 }, { 30205, 30205, 30205, 30205, 30215, 30214, 30215, 30214, 30215, 30214, 30211, 30207, 30202, 30208, 30211, 30205, 30202, 30208, 30211, 30205, 30202, 30208, 30211, 30205, 30202, 30208, 30211, 30205 },
			{ 30205, 30205, 30205, 30205, 30215, 30214, 30215, 30214, 30215, 30214, 30211, 30207, 30202, 30208, 30211, 30205, 30202, 30208, 30211, 30205, 30202, 30208, 30211, 30205, 30202, 30208, 30211, 30205 }, { 30205, 30205, 30205, 30205, 30215, 30214, 30215, 30214, 30215, 30214, 30211, 30207, 30202, 30208, 30211, 30205, 30202, 30208, 30211, 30205, 30202, 30208, 30211, 30205, 30202, 30208, 30211, 30205 }, { 30205, 30205, 30205, 30205, 30215, 30214, 30215, 30214, 30215, 30214, 30211, 30207, 30202, 30208, 30211, 30205, 30202, 30208, 30211, 30205, 30202, 30208, 30211, 30205, 30202, 30208, 30211, 30205 },
			{ 30205, 30205, 30205, 30205, 30215, 30214, 30215, 30214, 30215, 30214, 30211, 30207, 30202, 30208, 30211, 30205, 30202, 30208, 30211, 30205, 30202, 30208, 30211, 30205, 30202, 30208, 30211, 30205 }, { 30215, 30215, 30215 }
	};
	//extendwavesafterbeta
	
	private static enum Stages {
		LOADING,
		RUNNING,
		DESTROYING
	}
	
	public void startGame(final boolean login) {
		
		CoresManager.slowExecutor.execute(new Runnable() {
			@Override
			public void run() {
				WorldTasksManager.schedule(new WorldTask()  {
					@Override
					public void run() {
					player.unlock();
					stage = Stages.RUNNING;
					}

				}, 1);
				if(!login) {
					CoresManager.fastExecutor.schedule(new TimerTask() {

						@Override
						public void run() {
							if(stage != Stages.RUNNING) {
								return;
							}
							try {
								startWave();
							} catch (Throwable t) {
								Logger.handle(t);
							}
						}
					}, 6000);
				}
			}
		});
	}
	
	public void startWave() {
		int currentWave = getCurrentWave();
		if (getCurrentWave() > player.calamitybestwave) {
			player.calamitybestwave = currentWave;
			CalamityBestWave.checkRank(player);
		}
		if(currentWave > MONSTERS.length) {
			leave();
			return;
		}
		//player.getInterfaceManager().sendTab(player.getInterfaceManager().hasRezizableScreen() ? 11 : 0, 316);
		//player.getPackets().sendConfig(639, currentWave);
		player.getPackets().sendIComponentText(24, 6, ""+currentWave+"");
		player.getPackets().sendIComponentText(24, 3, "");
		if(stage != Stages.RUNNING) {
			return;
		}
		for(int id : MONSTERS[currentWave-0]) {
			if(id == 2736) {
				new TzKekCaves(id, getSpawnTile());
			} else if (id == 2745) {
				new Cala(id, getSpawnTile(), this);
			} else {
				new FightCavesNPC(id, getSpawnTile());
			}
		}
		spawned = true;
	}
	
	
	public void nextWave() {
		setCurrentWave(getCurrentWave()+1);
		setWaveEvent();
	}

	public void setWaveEvent() {
		player.calamitytotalwavescomplete ++;
		if(getCurrentWave() == 10) {
			player.getDialogueManager().startDialogue("SimpleMessage", ""+player.getDisplayName()+" You have been rewarded 1 Reward point for making it this far!");
			player.calamityrewardpoints += Settings.EVENT_SPOTLIGHT.contains("minigame") ? 2 : 1;
		}
			if(getCurrentWave() == 20) {
			player.getDialogueManager().startDialogue("SimpleMessage", ""+player.getDisplayName()+" You have been rewarded 2 Reward points for making it this far!");
			player.calamityrewardpoints += Settings.EVENT_SPOTLIGHT.contains("minigame") ? 4 : 2;
			}
			if(getCurrentWave() == 30) {
			player.getDialogueManager().startDialogue("SimpleMessage", ""+player.getDisplayName()+" You have been rewarded 4 Reward points for making it this far!");
			player.calamityrewardpoints += Settings.EVENT_SPOTLIGHT.contains("minigame") ? 8 : 4;
			}
			if(getCurrentWave() == 40) {
				player.getDialogueManager().startDialogue("SimpleMessage", ""+player.getDisplayName()+" You have been rewarded 10 Reward points for making it this far!");
			player.calamityrewardpoints += Settings.EVENT_SPOTLIGHT.contains("minigame") ? 20 : 10;
				}
			if(getCurrentWave() == 50) {
				player.getDialogueManager().startDialogue("SimpleMessage", ""+player.getDisplayName()+" You have been rewarded 14 Reward points for making it this far!");
			player.calamityrewardpoints += Settings.EVENT_SPOTLIGHT.contains("minigame") ? 28 : 14;
				World.sendWorldMessage(""+player.getDisplayName()+" has reached wave 50 in the calamity!", false);
				}
			if(getCurrentWave() == 52) {
				player.getDialogueManager().startDialogue("SimpleMessage", ""+player.getDisplayName()+" You have been rewarded 14 Reward points for making it this far!");
			player.calamityrewardpoints += Settings.EVENT_SPOTLIGHT.contains("minigame") ? 28 : 14;
				World.sendWorldMessage(""+player.getDisplayName()+" has reached wave 52 in the calamity!", false);
				}
			if(getCurrentWave() == 55) {
				player.getDialogueManager().startDialogue("SimpleMessage", ""+player.getDisplayName()+" You have been rewarded 16 Reward points for making it this far!");
			player.calamityrewardpoints += Settings.EVENT_SPOTLIGHT.contains("minigame") ? 32 : 16;
				World.sendWorldMessage(""+player.getDisplayName()+" has reached wave 55 in the calamity!", false);
				}
			if(getCurrentWave() == 58) {
				player.getDialogueManager().startDialogue("SimpleMessage", ""+player.getDisplayName()+" You have been rewarded 16 Reward points for making it this far!");
			player.calamityrewardpoints += Settings.EVENT_SPOTLIGHT.contains("minigame") ? 32 : 16;
				World.sendWorldMessage(""+player.getDisplayName()+" has reached wave 58 in the calamity!", false);
				}
			if(getCurrentWave() >= 60) {
				player.getDialogueManager().startDialogue("SimpleMessage", ""+player.getDisplayName()+" You have been rewarded 15 Reward points for making it this far!");
				player.calamityrewardpoints += Settings.EVENT_SPOTLIGHT.contains("minigame") ? 30 : 15;
				World.sendWorldMessage(""+player.getDisplayName()+" has reached wave "+getCurrentWave()+" in the calamity!", false);
				}
			CoresManager.fastExecutor.schedule(new TimerTask() {

			@Override
			public void run() {
				try {
					if(stage != Stages.RUNNING) {
						return;
					}
					startWave();
				} catch (Throwable e) {
					Logger.handle(e);
				}
			}
		}, 600);
	}

	@Override
	public void process() {
		if(spawned) {
			List<Integer> npcs = World.getRegion(player.getRegionId()).getNPCsIndexes();
			if(npcs == null || npcs.isEmpty() || npcs.size() == 7)  {
				spawned = false;
				nextWave();
			}
		}
	}
	
	
	
	
	
	
	public WorldTile getSpawnTile() {
		switch(Utils.random(5)) {
		case 0:
			return getWorldTile(124, 4308);
		case 1:
			return getWorldTile(123, 4315);
		case 2:
			return getWorldTile(115, 4315);
		case 3:
			return getWorldTile(115, 4307);
		case 4:
		default:
			return getWorldTile(119, 4312);
		}
	}
	
	public WorldTile getWorldTile(int mapX, int mapY) {
		return new WorldTile(mapX, mapY, 0);
	}

	
	

	public int getCurrentWave() {
		if (getArguments() == null || getArguments().length == 0) {
			return 0;
		}
		return (Integer) getArguments()[0];
	}

	public void setCurrentWave(int wave) {
		if(getArguments() == null || getArguments().length == 0) {
			setArguments(new Object[1]);
		}
		getArguments()[0] = wave;
	}
	
	@Override
	public boolean canMove(int dir) {
	return true;
	}

	@Override
	public boolean processNPCClick2(NPC n) {
		return true;
	}

	/*
	 * return process normaly
	 */
	@Override
	public boolean processButtonClick(int interfaceId, int componentId,
			int slotId, int packetId) {
		return true;
	}
	
	@Override
	public boolean canDropItem(Item item) {
		return true;
	}

	@Override
	public boolean canEquip(int slotId, int itemId) {
		return true;
	}

	@Override
	public boolean canAttack(Entity target) {
		return true;
	}

	@Override
	public boolean processItemOnNPC(NPC npc, Item item) {
		return true;
	}

	@Override
	public boolean canHit(Entity target) {
		return true;
	}

	// You can't leave just like that!

	public void leave() {
		player.getPackets().closeInterface(
				player.getInterfaceManager().hasRezizableScreen() ? 11 : 0);
		TheCalamity.removePlayingPlayer(player, team);
	}

	@Override
	public void sendInterfaces() {
		player.getInterfaceManager().sendTab(
		player.getInterfaceManager().hasRezizableScreen() ?  11 : 0, 24);
		player.getPackets().sendIComponentText(24, 4, "");
		player.getPackets().sendIComponentText(24, 7, "Wave");
	}

	@Override
	public boolean sendDeath() {
		WorldTasksManager.schedule(new WorldTask() {
			int loop;

			@Override
			public void run() {
				if (getCurrentWave() > player.calamitybestwave) {
					player.calamitybestwave = getCurrentWave();
					CalamityBestWave.checkRank(player);
				}
				if (getCurrentWave() >= 20) {
				SeasonEvent.HandleActivity(player, "Calamity", getCurrentWave() * 20);
				}
				if (loop == 0) {
					player.lock();
					player.setNextAnimation(new Animation(836));
				} else if (loop == 1) {
					player.getPackets().sendGameMessage(
							"Oh dear, you have died.");
				} else if (loop == 3) {
					player.getInventory().reset();
					player.getEquipment().reset();
					player.getInventory().refresh();
					player.getEquipment().refresh();
					player.calamitykillpoints = 0;
					player.doublekillpointscalamity = false;
					}
				player.reset();
					player.setNextWorldTile(new WorldTile(
							team == TheCalamity.THEGODS ? TheCalamity.LOBBY
									: TheCalamity.LOBBY, 1));
					player.setNextAnimation(new Animation(-1));
					removeControler();
					leave();
					player.getInventory().reset();
					player.getEquipment().reset();
					player.getInventory().refresh();
					player.getEquipment().refresh();
					player.calamitykillpoints = 0;
					player.doublekillpointscalamity = false;
					player.unlock();
					stop();
					loop++;
			}
			
		}, 0, 1);
		return false;
	}

	@Override
	public boolean logout() {
		player.setLocation(new WorldTile(TheCalamity.LOBBY, 0));
		return true;
	}

	@Override
	public boolean processMagicTeleport(WorldTile toTile) {
		player.getDialogueManager().startDialogue("SimpleMessage",
				"You can't leave just like that!");
		return false;
	}

	@Override
	public boolean processItemTeleport(WorldTile toTile) {
		player.getDialogueManager().startDialogue("SimpleMessage",
				"You can't leave just like that!");
		return false;
	}

	@Override
	public boolean processObjectTeleport(WorldTile toTile) {
		player.getDialogueManager().startDialogue("SimpleMessage",
				"You can't leave just like that!");
		return false;
	}

	/*@Override
	public boolean processObjectClick1(WorldObject object) {
		int id = object.getId();
			removeControler();
			leave();
			player.getInventory().reset();
			player.getEquipment().reset();
			player.calamitykillpoints = 0;
			return true;
	}

	@Override
	public boolean processObjectClick2(WorldObject object) {
		int id = object.getId();
		return true;
	}*/

	@Override
	public void magicTeleported(int type) {
		removeControler();
		leave();
		player.getInventory().reset();
		player.getEquipment().reset();
		player.getInventory().refresh();
		player.getEquipment().refresh();
		player.calamitykillpoints = 0;
		player.doublekillpointscalamity = false;
	}

	@Override
	public void forceClose() {
		leave();
		removeControler();
		player.getInventory().reset();
		player.getEquipment().reset();
		player.getInventory().refresh();
		player.getEquipment().refresh();
		player.calamitykillpoints = 0;
		player.doublekillpointscalamity = false;
	}
}
