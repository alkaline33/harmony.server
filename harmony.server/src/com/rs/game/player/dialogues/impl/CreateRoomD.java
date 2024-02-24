package com.rs.game.player.dialogues.impl;

import com.rs.game.player.content.construction.House.RoomReference;
import com.rs.game.player.dialogues.Dialogue;

public class CreateRoomD extends Dialogue {

    private RoomReference room;
    
    @Override
    public void finish() {
	player.getHouse().previewRoom(room, true, false);
	player.getHouse().reload(room);
    }

    @Override
    public void run(int interfaceId, int componentId) {
	if(componentId == OPTION_4) {
		player.getHouse().reload(room);
	    end();
	    return;
	}
	if(componentId == OPTION_3) {
	    end();
	    player.getHouse().createRoom(room);
	    return;
	}
	player.getHouse().previewRoom(room, true, false);
	room.setRotation((room.getRotation() + (componentId == OPTION_1 ? 1 : -1)) & 0x3);
	sendPreview();
    }
    
    public void sendPreview() {
	sendOptionsDialogue("Select an Option", "Rotate clockwise", "Rotate anticlockwise.", "Build.", "Cancel");
	player.getHouse().previewRoom(room, false, false);
    }

    @Override
    public void start() {
	this.room = (RoomReference) parameters[0];
	if (room == null)
		return;
	sendOptionsDialogue("Select an Option", "Rotate clockwise", "Rotate anticlockwise.", "Build.", "Cancel");
	player.getHouse().previewRoom(room, false, true);
    }

}
