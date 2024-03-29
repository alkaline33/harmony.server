package com.rs.game.player.content.dungeoneering;

import java.util.ArrayList;

import com.rs.game.npc.NPC;
import com.rs.game.player.content.dungeoneering.rooms.BossRoom;
import com.rs.game.player.content.dungeoneering.rooms.HandledRoom;
import com.rs.game.player.content.dungeoneering.rooms.StartRoom;

public class VisibleRoom {

	private int[] musicId;
	private ArrayList<NPC> guardians;
	
	public VisibleRoom(int type, HandledRoom room) {
		if(room instanceof StartRoom)
			musicId = new int[] {DungeonConstants.START_ROOM_MUSICS[type]};
		else if (room instanceof BossRoom) 
			musicId = new int[] {((BossRoom)room).getMusicId()};
		else {
			musicId = new int[] {DungeonUtils.getSafeMusic(type), DungeonUtils.getDangerousMusic(type)};
			guardians = new ArrayList<NPC>();
		}
	}
	
	public int getMusicId() {
		return musicId[roomCleared() ? 0 : 1];
	}
	
	private boolean roomCleared() {
		if(guardians == null)
			return true;
		for(NPC n : guardians)
			if(!n.hasFinished() && !n.isDead())
				return false;
		return true;
	}
	public void addGuardian(NPC n) {
		guardians.add(n);
	}
	
	public boolean removeGuardians() {
		if(roomCleared()) {
			guardians = null;
			return true;
		}
		return false;
	}
}