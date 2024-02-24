package com.rs.game.player.content.clans.clancitadels;

import com.rs.game.Animation;
import com.rs.game.Graphics;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.player.controlers.Controler;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;

/**
 * 
 * @author Josh'
 *
 */
public class CitadelControler extends Controler {
	
	protected static final int HOME_ANIMATION = 16385;
	protected static final int HOME_GRAPHIC = 3017;
	protected static final int DONE_ANIMATION = 16386;

	@Override
	public void start() {

	}
	
	@Override
	public void forceClose() {
		
	}
	
	@Override
	public boolean processItemTeleport(WorldTile tile) {
		player.getPackets().sendGameMessage("You can't leave Clan Citadel just like that, you need to use the Home Teleport!");
		return false;
	}
		
	@Override
	public boolean logout() {
		return true;
	}
	
	@Override
	public boolean processObjectClick1(final WorldObject object) {
		if (object.getId() == 59464) {
			//ClanCitadel.openNoticeboard(player);
			return false;
		}
		else if (object.getId() == 59462) {
			player.getDialogueManager().startDialogue("CitadelLeaveD");
			return false;
		}
		return true;
	}
	
	@Override
	public boolean processObjectClick2(final WorldObject object) {
		if (object.getId() == 59464) {
			//ClanCitadel.openNoticeboard(player);
			return false;
		}
		else if (object.getId() == 59462) {
			ClanCitadel.leaveCitadel(player);
			return false;
		}
		return true;
	}
	
	@Override
	public boolean processMagicTeleport(WorldTile tile) {
		WorldTasksManager.schedule(new WorldTask() {
			int loop;

			@Override
			public void run() {
				if (loop == 0) {
					player.setNextAnimation(new Animation(HOME_ANIMATION));
					player.setNextGraphics(new Graphics(HOME_GRAPHIC));
				} else if (loop == 10) {
					player.setNextWorldTile(new WorldTile(ClanCitadel.getBaseX() + 242, ClanCitadel.getBaseY() + 40, 0));
					player.setNextAnimation(new Animation(HOME_ANIMATION + 1));
					player.setNextGraphics(new Graphics(HOME_GRAPHIC + 1));
				} else if (loop == 13) {
					player.setNextAnimation(new Animation(-1));
					player.setNextGraphics(new Graphics(-1));
				}

				loop++;
			}
		});
		return false;
	}
	
	@Override
	public boolean sendDeath() {
		WorldTasksManager.schedule(new WorldTask() {
			int loop;
			@Override
			public void run() {
				player.stopAll();
				if (loop == 0) {
					player.setNextAnimation(new Animation(836));
				} else if (loop == 1) {
					player.getPackets().sendGameMessage("Oh dear, you have died.");
				} else if (loop == 3) {
					player.setNextAnimation(new Animation(-1));
					player.reset();
					player.setCanPvp(false);
					player.setNextWorldTile(new WorldTile(ClanCitadel.getBaseX() + 242, ClanCitadel.getBaseY() + 40, 0));
					stop();
				}
				loop++;
			}
		});
		return false;
	}
	
	
}